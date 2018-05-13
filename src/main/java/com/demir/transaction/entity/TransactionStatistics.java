package com.demir.transaction.entity;

import java.io.Serializable;

/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:48
 */
public class TransactionStatistics implements Serializable {

    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;

    private TransactionStatistics(Double sum, Double avg, Double max, Double min, Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public static TransactionStatistics build() {
        return new TransactionStatistics(Double.valueOf(0), Double.valueOf(0), Double.valueOf(0), Double.valueOf(0), Long.valueOf(0));
    }

    public TransactionStatistics add(Transaction transaction) {
        this.sum += transaction.getAmount();
        this.count += 1;
        if (transaction.getAmount().doubleValue() > this.max) {
            this.max = transaction.getAmount().doubleValue();
        }
        if (transaction.getAmount().doubleValue() < this.min) {
            this.min = transaction.getAmount().doubleValue();
        }
        this.avg = this.sum / this.count;
        return this;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Long getCount() {
        return count;
    }
}
