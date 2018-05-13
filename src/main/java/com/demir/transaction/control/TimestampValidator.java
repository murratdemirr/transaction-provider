package com.demir.transaction.control;

import com.demir.transaction.InvalidTimestampException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 21:54
 */

@Service
public class TimestampValidator {

    @Value("${millisecond.border}")
    private Long border;

    public void check(final long timestamp) throws InvalidTimestampException {
        final long limit = System.currentTimeMillis() - border;
        if (timestamp < limit) {
            throw new InvalidTimestampException();
        }
    }

}
