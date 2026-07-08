package com.proje.mobilesales.features.customer.model;

import kotlin.jvm.internal.Intrinsics;

public final class CustomerRiskProperty {
    private double closed;
    private double limit;
    private String name;
    private boolean risk;
    private double total;

    public String component1() {
        return this.name;
    }

    public double component2() {
        return this.limit;
    }

    public double component3() {
        return this.closed;
    }

    public double component4() {
        return this.total;
    }

    public boolean component5() {
        return this.risk;
    }

    public CustomerRiskProperty copy(String str, double d2, double d3, double d4, boolean z) {
        return new CustomerRiskProperty(str, d2, d3, d4, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerRiskProperty customerRiskProperty)) {
            return false;
        }
        return Intrinsics.areEqual(this.name, customerRiskProperty.name) && Double.compare(this.limit, customerRiskProperty.limit) == 0 && Double.compare(this.closed, customerRiskProperty.closed) == 0 && Double.compare(this.total, customerRiskProperty.total) == 0 && this.risk == customerRiskProperty.risk;
    }

    public int hashCode() {
        String str = this.name;
        return ((((((((str == null ? 0 : str.hashCode()) * 31) + Double.hashCode(this.limit)) * 31) + Double.hashCode(this.closed)) * 31) + Double.hashCode(this.total)) * 31) + Boolean.hashCode(this.risk);
    }

    public String toString() {
        return "CustomerRiskProperty(name=" + this.name + ", limit=" + this.limit + ", closed=" + this.closed + ", total=" + this.total + ", risk=" + this.risk + ')';
    }

    public CustomerRiskProperty(String str, double d2, double d3, double d4, boolean z) {
        this.name = str;
        this.limit = d2;
        this.closed = d3;
        this.total = d4;
        this.risk = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public double getLimit() {
        return this.limit;
    }

    public void setLimit(double d2) {
        this.limit = d2;
    }

    public double getClosed() {
        return this.closed;
    }

    public void setClosed(double d2) {
        this.closed = d2;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double d2) {
        this.total = d2;
    }

    public boolean getRisk() {
        return this.risk;
    }

    public void setRisk(boolean z) {
        this.risk = z;
    }
}
