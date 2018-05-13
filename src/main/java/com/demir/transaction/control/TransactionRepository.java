package com.demir.transaction.control;

import com.demir.transaction.entity.Transaction;
import com.demir.transaction.entity.TransactionPool;
import com.demir.transaction.entity.TransactionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 13:30
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionRepository.class);

    private volatile ConcurrentHashMap<Long, TransactionPool> transactionMap = new ConcurrentHashMap<>();

    @Value("${millisecond.max}")
    private Long maximumValidityPeriod;


    @PostConstruct
    public void init() {
        transactionMap.clear();
        LOG.info("Transaction Repository initialized as singleton service");
    }


    public synchronized void push(final Transaction transaction) {
        LOG.info("Transaction repository received a new push request");
        if (transactionMap.containsKey(transaction.getTimestamp())) {
            transactionMap.get(transaction.getTimestamp()).add(transaction);
        } else {
            transactionMap.put(transaction.getTimestamp(), TransactionPool.build().add(transaction));
        }
    }

    public synchronized void deleteOldOnes() {
        final long minimumTime = System.currentTimeMillis() - maximumValidityPeriod;
        transactionMap.keySet().removeIf(k -> k < minimumTime);
    }

    public TransactionStatistics statistics() {
        TransactionStatistics statistics = TransactionStatistics.empty();
        final long minimumTime = System.currentTimeMillis() - maximumValidityPeriod;

        List<Transaction> transactions = new ArrayList<>();
        transactionMap
                .entrySet()
                .parallelStream()
                .filter(t -> t.getKey() >= minimumTime)
                .forEach(t -> transactions.addAll(t.getValue().getData()));

        if (!transactions.isEmpty()) {
            final DoubleSummaryStatistics summary = transactions.stream().collect(Collectors.summarizingDouble(t -> t.getAmount()));
            statistics = new TransactionStatistics(summary.getSum(), summary.getAverage(), summary.getMax(), summary.getMin(), summary.getCount());
        }
        return statistics;
    }
}
