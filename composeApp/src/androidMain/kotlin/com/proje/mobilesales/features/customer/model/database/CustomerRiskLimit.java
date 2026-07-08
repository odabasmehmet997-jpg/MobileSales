package com.proje.mobilesales.features.customer.model.database;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CustomerRiskLimit {

    private double credit;
    private double debit;
    private double total;

    public CustomerRiskLimit() {
        this(0.0d, 0.0d, 0.0d, 7, null);
    }

    public static  CustomerRiskLimit copydefault(CustomerRiskLimit customerRiskLimit, double d2, double d3, double d4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d2 = customerRiskLimit.debit;
        }
        double d5 = d2;
        if ((i2 & 2) != 0) {
            d3 = customerRiskLimit.credit;
        }
        double d6 = d3;
        if ((i2 & 4) != 0) {
            d4 = customerRiskLimit.total;
        }
        return customerRiskLimit.copy(d5, d6, d4);
    }

    public double component1() {
        return this.debit;
    }

    public double component2() {
        return this.credit;
    }

    public double component3() {
        return this.total;
    }

    public CustomerRiskLimit copy(double d2, double d3, double d4) {
        return new CustomerRiskLimit(d2, d3, d4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerRiskLimit customerRiskLimit)) {
            return false;
        }
        return Double.compare(this.debit, customerRiskLimit.debit) == 0 && Double.compare(this.credit, customerRiskLimit.credit) == 0 && Double.compare(this.total, customerRiskLimit.total) == 0;
    }

    public int hashCode() {
        return (((Double.hashCode(this.debit) * 31) + Double.hashCode(this.credit)) * 31) + Double.hashCode(this.total);
    }
    public String toString() {
        return "CustomerRiskLimit(debit=" + this.debit + ", credit=" + this.credit + ", total=" + this.total + ')';
    }
    public CustomerRiskLimit(double d2, double d3, double d4) {
        this.debit = d2;
        this.credit = d3;
        this.total = d4;
    }
    public  CustomerRiskLimit(double d2, double d3, double d4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0d : d2, (i2 & 2) != 0 ? 0.0d : d3, (i2 & 4) != 0 ? 0.0d : d4);
    }

    public double getDebit() {
        return this.debit;
    }

    public void setDebit(double d2) {
        this.debit = d2;
    }

    public double getCredit() {
        return this.credit;
    }

    public void setCredit(double d2) {
        this.credit = d2;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double d2) {
        this.total = d2;
    }
}
