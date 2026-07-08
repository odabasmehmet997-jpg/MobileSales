package com.proje.mobilesales.features.customer.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerCampaignPoint {

    private double amount;
    private String campaignCode;
    private String campaignName;
    private double campaignPoint;

    public CustomerCampaignPoint() {
        this(null, null, 0.0d, 0.0d, 15, null);
    }

    public static   CustomerCampaignPoint copy(CustomerCampaignPoint customerCampaignPoint, String str, String str2, double d2, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customerCampaignPoint.campaignCode;
        }
        if ((i2 & 2) != 0) {
            str2 = customerCampaignPoint.campaignName;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            d2 = customerCampaignPoint.amount;
        }
        double d4 = d2;
        if ((i2 & 8) != 0) {
            d3 = customerCampaignPoint.campaignPoint;
        }
        return customerCampaignPoint.copy(str, str3, d4, d3);
    }

    public   String component1() {
        return this.campaignCode;
    }

    public  String component2() {
        return this.campaignName;
    }

    public   double component3() {
        return this.amount;
    }

    public  double component4() {
        return this.campaignPoint;
    }

    public   CustomerCampaignPoint copy(String str, String str2, double d2, double d3) {
        return new CustomerCampaignPoint(str, str2, d2, d3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerCampaignPoint customerCampaignPoint)) {
            return false;
        }
        return Intrinsics.areEqual(this.campaignCode, customerCampaignPoint.campaignCode) && Intrinsics.areEqual(this.campaignName, customerCampaignPoint.campaignName) && Double.compare(this.amount, customerCampaignPoint.amount) == 0 && Double.compare(this.campaignPoint, customerCampaignPoint.campaignPoint) == 0;
    }

    public int hashCode() {
        String str = this.campaignCode;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.campaignName;
        return ((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Double.hashCode(this.amount)) * 31) + Double.hashCode(this.campaignPoint);
    }

    public String toString() {
        return "CustomerCampaignPoint(campaignCode=" + this.campaignCode + ", campaignName=" + this.campaignName + ", amount=" + this.amount + ", campaignPoint=" + this.campaignPoint + ')';
    }

    public CustomerCampaignPoint(String str, String str2, double d2, double d3) {
        this.campaignCode = str;
        this.campaignName = str2;
        this.amount = d2;
        this.campaignPoint = d3;
    }

    public  CustomerCampaignPoint(String str, String str2, double d2, double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) == 0 ? str2 : null, (i2 & 4) != 0 ? 0.0d : d2, (i2 & 8) != 0 ? 0.0d : d3);
    }

    public   String getCampaignCode() {
        return this.campaignCode;
    }

    public   void setCampaignCode(String str) {
        this.campaignCode = str;
    }

    public   String getCampaignName() {
        return this.campaignName;
    }

    public   void setCampaignName(String str) {
        this.campaignName = str;
    }

    public   double getAmount() {
        return this.amount;
    }

    public   void setAmount(double d2) {
        this.amount = d2;
    }

    public   double getCampaignPoint() {
        return this.campaignPoint;
    }

    public   void setCampaignPoint(double d2) {
        this.campaignPoint = d2;
    }
}
