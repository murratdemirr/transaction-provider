package com.demir.transaction.boundary;

import com.demir.transaction.ExpiredTimestampException;
import com.demir.transaction.control.TransactionService;
import com.demir.transaction.entity.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:43
 */
@CrossOrigin
@RestController
@RequestMapping("transactions")
public class TransactionResource {

    @Inject
    TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> commit(@Validated @RequestBody Transaction transaction) {
        HttpStatus responseStatus;
        try {
            transactionService.commit(transaction);
            responseStatus = HttpStatus.CREATED;
        } catch (ExpiredTimestampException ex) {
            responseStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(responseStatus).build();
    }


}
