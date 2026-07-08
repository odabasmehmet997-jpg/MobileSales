package com.proje.mobilesales.core.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.DecimalFormat;

public class UnitPriceFormatter implements Parcelable {
    private static UnitPriceFormatter sInstance;
    private final DecimalFormat mDecimalFormat;
    private static final Object lock = new Object();
    public static final Parcelable.Creator<UnitPriceFormatter> CREATOR = new Parcelable.Creator<UnitPriceFormatter>() {
        public UnitPriceFormatter createFromParcel(Parcel parcel) {
            return new UnitPriceFormatter(parcel);
        }
        public UnitPriceFormatter[] newArray(int i2) {
            return new UnitPriceFormatter[i2];
        }
    };
    public int describeContents() {
        return 0;
    }
    private UnitPriceFormatter() {
        this.mDecimalFormat = new DecimalFormat();
    }
    public static UnitPriceFormatter getInstance(int i2) {
        UnitPriceFormatter unitPriceFormatter;
        synchronized (lock) {
            if (sInstance == null) {
                sInstance = new UnitPriceFormatter();
                sInstance.setMinimumFractionDigits(i2);
                unitPriceFormatter = sInstance;
            } else {
                unitPriceFormatter = sInstance;
                sInstance.setMinimumFractionDigits(i2);
            }
        }
        return unitPriceFormatter;
    }
    private void setMinimumFractionDigits(int i2) {
        this.mDecimalFormat.setMinimumFractionDigits(i2);
    }
    public String getFormattedPrice(String str) {
        return getFormattedPrice(StringUtils.convertStringToDouble(str));
    }
    public String getFormattedPrice(double d2) {
        return this.mDecimalFormat.format(d2);
    }
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.mDecimalFormat);
    }
    protected UnitPriceFormatter(Parcel parcel) {
        this.mDecimalFormat = (DecimalFormat) parcel.readSerializable();
    }
}
