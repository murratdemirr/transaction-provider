package com.demir.transaction.control;

import com.demir.transaction.entity.Transaction;
import com.demir.transaction.entity.TransactionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

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


    @PostConstruct
    public void init() {
        transactionMap.clear();
        LOG.info("Transaction Repository initialized as singleton service");
    }


    public synchronized void push(final Transaction transaction) {
        LOG.info("Transaction repository got a new push request, {0}", transaction.toString());
        if (transactionMap.containsKey(transaction.getTimestamp())) {
            transactionMap.get(transaction.getTimestamp()).add(transaction);
        } else {
            transactionMap.put(transaction.getTimestamp(), TransactionPool.build().add(transaction));
        }
    }

    public synchronized void deleteOldOnes(final long minimumTime) {
        transactionMap.keySet().removeIf(k -> k < minimumTime);
    }
}
