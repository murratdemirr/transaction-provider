package com.demir.transaction;

import com.demir.transaction.control.TransactionService;
import com.demir.transaction.entity.Transaction;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionApplication.class, loader = AnnotationConfigWebContextLoader.class)
@TestPropertySource("classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionApplicationTests {

    @Inject
    TransactionService transactionService;


    @Test
    public void expiredTimestamp() {
        try {
            transactionService.commit(new Transaction(Double.valueOf(14), System.currentTimeMillis() - 70000));
            Assert.fail();
        } catch (ExpiredTimestampException ex) {

        }
    }

}
