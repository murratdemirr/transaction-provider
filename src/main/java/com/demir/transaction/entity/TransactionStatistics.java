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


    public TransactionStatistics(Double sum, Double avg, Double max, Double min, Long count) {
        this.sum = sum.doubleValue();
        this.avg = avg.doubleValue();
        if (!max.isInfinite()) {
            this.max = max.doubleValue();
        } else {
            this.max = Double.valueOf(0);
        }
        if (!min.isInfinite()) {
            this.min = min.doubleValue();
        } else {
            this.min = Double.valueOf(0);
        }
        this.count = count.longValue();
    }

    public static TransactionStatistics empty() {
        return new TransactionStatistics(Double.valueOf(0), Double.valueOf(0), Double.valueOf(0), Double.valueOf(0), Long.valueOf(0));
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
