package com.demir.transaction.control;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 23:00
 */
@Service
public class TransactionCleaner {

    @Inject
    TransactionRepository repository;

    @Scheduled(fixedDelay = 1000)
    public void killer(){
        repository.deleteOldOnes();
    }

}
