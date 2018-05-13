package com.demir.transaction.control;

import com.demir.transaction.InvalidTimestampException;
import com.demir.transaction.entity.Transaction;
import com.demir.transaction.entity.TransactionStatistics;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Instant;


/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:43
 */


@Service
public class TransactionService {

    @Inject
    IdGenerator idGenerator;
    @Inject
    TransactionRepository repository;

    @Async
    public void commit(Transaction transaction) throws InvalidTimestampException {
        final String id = idGenerator.id(transaction.getTimestamp());
        repository.push(id, transaction);
    }


    @Async
    public TransactionStatistics snapshot() {
        final long timestamp = Instant.now().getEpochSecond();
        TransactionStatistics statistics = TransactionStatistics.build();
        return statistics;
    }

}
