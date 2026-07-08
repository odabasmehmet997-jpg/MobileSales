package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.tigerwcf.RESULTXML;

public class PrintValueMultiple<T> implements Parcelable {
    public static final Parcelable.Creator<PrintValueMultiple> CREATOR = new Parcelable.Creator<PrintValueMultiple>() { // from class: com.proje.mobilesales.features.model.PrintValueMultiple.1

        public PrintValueMultiple createFromParcel(final Parcel parcel) {
            return new PrintValueMultiple(parcel);
        }

        public PrintValueMultiple[] newArray(final int i2) {
            return new PrintValueMultiple[i2];
        }
    };
    RESULTXML detailResultXml;
    RESULTXML discountResultXml;
    RESULTXML headerResultXml;

    public int describeContents() {
        return 0;
    }

    public PrintValueMultiple(final RESULTXML resultxml, final RESULTXML resultxml2, final RESULTXML resultxml3) {
        headerResultXml = resultxml;
        detailResultXml = resultxml2;
        discountResultXml = resultxml3;
    }

    public RESULTXML getHeaderResultXml() {
        return headerResultXml;
    }

    public void setHeaderResultXml(final RESULTXML resultxml) {
        headerResultXml = resultxml;
    }

    public RESULTXML getDetailResultXml() {
        return detailResultXml;
    }

    public void setDetailResultXml(final RESULTXML resultxml) {
        detailResultXml = resultxml;
    }

    public RESULTXML getDiscountResultXml() {
        return discountResultXml;
    }

    public void setDiscountResultXml(final RESULTXML resultxml) {
        discountResultXml = resultxml;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeParcelable(headerResultXml, i2);
        parcel.writeParcelable(detailResultXml, i2);
        parcel.writeParcelable(discountResultXml, i2);
    }

    public PrintValueMultiple() {
    }

    protected PrintValueMultiple(final Parcel parcel) {
        headerResultXml = parcel.readParcelable(RESULTXML.class.getClassLoader());
        detailResultXml = parcel.readParcelable(RESULTXML.class.getClassLoader());
        discountResultXml = parcel.readParcelable(RESULTXML.class.getClassLoader());
    }
}
