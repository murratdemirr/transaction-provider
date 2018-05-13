package com.demir.transaction.entity;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 23:15
 */
public class TransactionPool implements Serializable {

    private CopyOnWriteArrayList<Transaction> transactions = new CopyOnWriteArrayList<>();

    private TransactionPool() {
    }

    public static TransactionPool build() {
        TransactionPool pool = new TransactionPool();
        pool.transactions.clear();
        return pool;
    }

    public synchronized TransactionPool add(Transaction transaction) {
        transactions.add(transaction);
        return this;
    }

}
