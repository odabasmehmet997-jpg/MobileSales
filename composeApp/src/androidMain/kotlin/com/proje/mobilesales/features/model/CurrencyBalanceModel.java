package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
public class CurrencyBalanceModel implements Parcelable {
    public static final Parcelable.Creator<CurrencyBalanceModel> CREATOR = new Parcelable.Creator<CurrencyBalanceModel>() {

        public CurrencyBalanceModel createFromParcel(final Parcel parcel) {
            return new CurrencyBalanceModel(parcel);
        }

        public CurrencyBalanceModel[] newArray(final int i2) {
            return new CurrencyBalanceModel[i2];
        }
    };

    private String currCode;
    private double localSum;
    private double reportSum;
    private int sign;
    private double transactionCredit;
    private double transactionDebit;

    private double transactionSum;
    public int describeContents() {
        return 0;
    }

    public CurrencyBalanceModel() {
    }

    protected CurrencyBalanceModel(final Parcel parcel) {
        localSum = parcel.readDouble();
        transactionSum = parcel.readDouble();
        reportSum = parcel.readDouble();
        sign = parcel.readInt();
        currCode = parcel.readString();
        transactionDebit = parcel.readDouble();
        transactionCredit = parcel.readDouble();
    }

    public double getLocalSum() {
        return localSum;
    }

    public void setLocalSum(final double d2) {
        localSum = d2;
    }

    public double getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(final double d2) {
        transactionSum = d2;
    }

    public double getReportSum() {
        return reportSum;
    }

    public void setReportSum(final double d2) {
        reportSum = d2;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(final int i2) {
        sign = i2;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(final String str) {
        currCode = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeDouble(localSum);
        parcel.writeDouble(transactionSum);
        parcel.writeDouble(reportSum);
        parcel.writeInt(sign);
        parcel.writeString(currCode);
        parcel.writeDouble(transactionDebit);
        parcel.writeDouble(transactionCredit);
    }

    public double getTransactionDebit() {
        return transactionDebit;
    }

    public void setTransactionDebit(final double d2) {
        transactionDebit = d2;
    }

    public double getTransactionCredit() {
        return transactionCredit;
    }

    public void setTransactionCredit(final double d2) {
        transactionCredit = d2;
    }
}
