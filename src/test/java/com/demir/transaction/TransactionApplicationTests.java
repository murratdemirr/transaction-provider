package com.demir.transaction;

import com.demir.transaction.control.TransactionService;
import com.demir.transaction.entity.Transaction;
import com.demir.transaction.entity.TransactionStatistics;
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

    @Test
    public void commitAndCheckTest() throws ExpiredTimestampException{
        transactionService.commit(new Transaction(Double.valueOf(10), System.currentTimeMillis()));
        transactionService.commit(new Transaction(Double.valueOf(20), System.currentTimeMillis()));
        transactionService.commit(new Transaction(Double.valueOf(30), System.currentTimeMillis()));
        transactionService.commit(new Transaction(Double.valueOf(40), System.currentTimeMillis()));
        TransactionStatistics statistics = transactionService.fetchStatistics();
        Assert.assertTrue(statistics.getCount().intValue() == 4);
        Assert.assertTrue(statistics.getMax().doubleValue() == Double.valueOf(40).doubleValue());
        Assert.assertTrue(statistics.getMin().doubleValue() == Double.valueOf(10).doubleValue());
        Assert.assertTrue(statistics.getSum().doubleValue() == Double.valueOf(100).doubleValue());
        Assert.assertTrue(statistics.getAvg().doubleValue() == Double.valueOf(25).doubleValue());
    }

}
