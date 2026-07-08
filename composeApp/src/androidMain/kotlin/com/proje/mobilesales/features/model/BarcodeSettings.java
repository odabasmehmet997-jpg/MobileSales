package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BarcodeSettings implements Parcelable {
    public static final Parcelable.Creator<BarcodeSettings> CREATOR = new Parcelable.Creator<BarcodeSettings>() {
        public BarcodeSettings createFromParcel(final Parcel parcel) {
            return new BarcodeSettings(parcel);
        }

        public BarcodeSettings[] newArray(final int i2) {
            return new BarcodeSettings[i2];
        }
    };
    int amountStartCharacter;
    int amountTotalCharacter;
    int barcodeStartCharacter;
    int barcodeTotalCharacter;
    int decimalPoint;
    boolean useEanStandard;
    public int describeContents() {
        return 0;
    }
    public int getBarcodeStartCharacter() {
        return barcodeStartCharacter;
    }
    public void setBarcodeStartCharacter(final int i2) {
        barcodeStartCharacter = i2;
    }
    public int getBarcodeTotalCharacter() {
        return barcodeTotalCharacter;
    }
    public void setBarcodeTotalCharacter(final int i2) {
        barcodeTotalCharacter = i2;
    }
    public int getAmountStartCharacter() {
        return amountStartCharacter;
    }
    public void setAmountStartCharacter(final int i2) {
        amountStartCharacter = i2;
    }
    public int getAmountTotalCharacter() {
        return amountTotalCharacter;
    }
    public void setAmountTotalCharacter(final int i2) {
        amountTotalCharacter = i2;
    }
    public int getDecimalPoint() {
        return decimalPoint;
    }
    public void setDecimalPoint(final int i2) {
        decimalPoint = i2;
    }
    public boolean isUseEanStandard() {
        return useEanStandard;
    }
    public void setUseEanStandard(final boolean z) {
        useEanStandard = z;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(barcodeStartCharacter);
        parcel.writeInt(barcodeTotalCharacter);
        parcel.writeInt(amountStartCharacter);
        parcel.writeInt(amountTotalCharacter);
        parcel.writeInt(decimalPoint);
        parcel.writeByte(useEanStandard ? (byte) 1 : (byte) 0);
    }
    public BarcodeSettings() {
    }

    protected BarcodeSettings(final Parcel parcel) {
        barcodeStartCharacter = parcel.readInt();
        barcodeTotalCharacter = parcel.readInt();
        amountStartCharacter = parcel.readInt();
        amountTotalCharacter = parcel.readInt();
        decimalPoint = parcel.readInt();
        useEanStandard = 0 != parcel.readByte();
    }
}
