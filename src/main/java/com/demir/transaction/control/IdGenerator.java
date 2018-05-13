package com.demir.transaction.control;

import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * User: muratdemir
 * Date: 13.05.2018
 * Time: 13:22
 */

@Service
public class IdGenerator {

    private static final String ID_DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss";

    public String id(Long timestamp) {
        final Instant timeAsSecond = Instant.ofEpochSecond(timestamp);


        return "";
    }

}
