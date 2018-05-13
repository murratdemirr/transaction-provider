package com.demir.transaction.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:46
 */
public class Transaction implements Serializable {

    @NotNull(message = "Amount can't be null")
    private Double amount;
    @NotNull(message = "Timestamp can't be null")
    private Long timestamp;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
