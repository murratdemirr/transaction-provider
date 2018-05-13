package com.demir.transaction.control;

import com.demir.transaction.ExpiredTimestampException;
import com.demir.transaction.entity.Transaction;
import com.demir.transaction.entity.TransactionStatistics;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:43
 */


@Service
public class TransactionService {

    @Inject
    TransactionRepository repository;
    @Inject
    TimestampValidator timestampValidator;

    public void commit(Transaction transaction) throws ExpiredTimestampException {
        timestampValidator.check(transaction.getTimestamp());
        repository.push(transaction);
    }


    public TransactionStatistics fetchStatistics() {
        return repository.statistics();
    }

}
