package com.demir.transaction.control;

import com.demir.transaction.ExpiredTimestampException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 21:54
 */

@Service
public class TimestampValidator {

    @Value("${millisecond.max}")
    private Long maximumValidityPeriod;

    public void check(final long timestamp) throws ExpiredTimestampException {
        final long minimumTime = System.currentTimeMillis() - maximumValidityPeriod;
        if (timestamp < minimumTime) {
            throw new ExpiredTimestampException();
        }
    }

}
