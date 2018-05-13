package com.demir.transaction.boundary;

import com.demir.transaction.InvalidTimestampException;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> commit(@Validated @RequestBody Transaction transaction) {
        try {
            transactionService.commit(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (InvalidTimestampException ite) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


}
