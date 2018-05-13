package com.demir.transaction.control;

import com.demir.transaction.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 13:30
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionRepository.class);

    private volatile ConcurrentHashMap<String, List<Transaction>> transactionMap = new ConcurrentHashMap<>();
    private volatile ConcurrentSkipListMap<String,String> coo = new ConcurrentSkipListMap<>();


    @PostConstruct
    public void init() {
        transactionMap.clear();
        LOG.info("Transaction Repository initialized as singleton service");
    }


    public synchronized void push(final String id, Transaction transaction) {
        LOG.info(MessageFormat.format("Transaction repository got a new push request, ID:{0} {1}", id, transaction.toString()));
        if (transactionMap.containsKey(id)) {
            List<Transaction> transactions = transactionMap.get(id);
            if (transactions == null || transactions.isEmpty()) {
                transactions = new ArrayList<>();
            }
            coo.headMap("ww").clear();
            transactions.add(transaction);
            transactionMap.replace(id, transactions);
        } else {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            transactionMap.put(id, transactions);
        }
    }

}
