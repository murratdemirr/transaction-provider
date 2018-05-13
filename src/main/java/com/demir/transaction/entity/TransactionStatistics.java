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

    private TransactionStatistics() {
    }

    public static TransactionStatistics build() {
        return new TransactionStatistics();
    }

    public TransactionStatistics sum(Double sum) {
        this.sum = sum;
        return this;
    }

    public TransactionStatistics avg(Double avg) {
        this.avg = avg;
        return this;
    }

    public TransactionStatistics max(Double max) {
        this.max = max;
        return this;
    }

    public TransactionStatistics min(Double min) {
        this.min = min;
        return this;
    }

    public TransactionStatistics count(Long count) {
        this.count = count;
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
