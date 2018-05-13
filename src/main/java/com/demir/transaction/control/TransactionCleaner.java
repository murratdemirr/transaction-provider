package com.demir.transaction.control;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${millisecond.max}")
    private Long maximumValidityPeriod;



    @Scheduled(fixedDelay = 1)
    public void killer(){
        final long minimumTime = System.currentTimeMillis() - maximumValidityPeriod;
        repository.deleteOldOnes(minimumTime);
    }

}
