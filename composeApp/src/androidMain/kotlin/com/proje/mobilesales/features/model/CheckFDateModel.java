package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.enums.FDateField;

public class CheckFDateModel implements Parcelable {
    public static final Parcelable.Creator<CheckFDateModel> CREATOR = new Parcelable.Creator<CheckFDateModel>() {
        public CheckFDateModel createFromParcel(final Parcel parcel) {
            return new CheckFDateModel(parcel);
        }
        public CheckFDateModel[] newArray(final int i2) {
            return new CheckFDateModel[i2];
        }
    };

    private int appVersionOnWorProcess;
    private int fDateOnAddTax;
    private int fDateOnBnCard;
    private int fDateOnBnkAcc;
    private int fDateOnClCard;
    private int fDateOnClfLine;
    private int fDateOnDeletedRecs;
    private int fDateOnItems;
    private int fDateOnOrFiche;
    private int fDateOnPayPlans;
    private int fDateOnPrcList;
    private int fDateOnShipInfo;
    private int fDateOnSrvCard;
    private int fDateOnStFiche;
    private int fDateOnWorCabin;
    public int describeContents() {
        return 0;
    }
    public int getfDateOnOrFiche() {
        return fDateOnOrFiche;
    }
    public void setfDateOnOrFiche(final int i2) {
        fDateOnOrFiche = i2;
    }
    public int getfDateOnStFiche() {
        return fDateOnStFiche;
    }
    public void setfDateOnStFiche(final int i2) {
        fDateOnStFiche = i2;
    }
    public int getfDateOnClCard() {
        return fDateOnClCard;
    }
    public void setfDateOnClCard(final int i2) {
        fDateOnClCard = i2;
    }
    public int getfDateOnClfLine() {
        return fDateOnClfLine;
    }
    public void setfDateOnClfLine(final int i2) {
        fDateOnClfLine = i2;
    }
    public int getfDateOnItems() {
        return fDateOnItems;
    }
    public void setfDateOnItems(final int i2) {
        fDateOnItems = i2;
    }
    public int getfDateOnSrvCard() {
        return fDateOnSrvCard;
    }
    public void setfDateOnSrvCard(final int i2) {
        fDateOnSrvCard = i2;
    }
    public int getfDateOnPrcList() {
        return fDateOnPrcList;
    }
    public void setfDateOnPrcList(final int i2) {
        fDateOnPrcList = i2;
    }
    public int getfDateOnPayPlans() {
        return fDateOnPayPlans;
    }
    public void setfDateOnPayPlans(final int i2) {
        fDateOnPayPlans = i2;
    }
    public int getfDateOnShipInfo() {
        return fDateOnShipInfo;
    }
    public void setfDateOnShipInfo(final int i2) {
        fDateOnShipInfo = i2;
    }
    public int getfDateOnBnCard() {
        return fDateOnBnCard;
    }
    public void setfDateOnBnCard(final int i2) {
        fDateOnBnCard = i2;
    }
    public int getfDateOnBnkAcc() {
        return fDateOnBnkAcc;
    }
    public void setfDateOnBnkAcc(final int i2) {
        fDateOnBnkAcc = i2;
    }
    public int getfDateOnAddTax() {
        return fDateOnAddTax;
    }
    public void setfDateOnAddTax(final int i2) {
        fDateOnAddTax = i2;
    }
    public int getfDateOnWorCabin() {
        return fDateOnWorCabin;
    }
    public void setfDateOnWorCabin(final int i2) {
        fDateOnWorCabin = i2;
    }
    public int getfDateOnDeletedRecs() {
        return fDateOnDeletedRecs;
    }
    public void setfDateOnDeletedRecs(final int i2) {
        fDateOnDeletedRecs = i2;
    }
    public CheckFDateModel() {
    }
    protected CheckFDateModel(final Parcel parcel) {
        fDateOnOrFiche = parcel.readInt();
        fDateOnStFiche = parcel.readInt();
        fDateOnClCard = parcel.readInt();
        fDateOnClfLine = parcel.readInt();
        fDateOnItems = parcel.readInt();
        fDateOnSrvCard = parcel.readInt();
        fDateOnPrcList = parcel.readInt();
        fDateOnPayPlans = parcel.readInt();
        fDateOnShipInfo = parcel.readInt();
        fDateOnBnCard = parcel.readInt();
        fDateOnBnkAcc = parcel.readInt();
        fDateOnAddTax = parcel.readInt();
        fDateOnWorCabin = parcel.readInt();
        fDateOnDeletedRecs = parcel.readInt();
        appVersionOnWorProcess = parcel.readInt();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(fDateOnOrFiche);
        parcel.writeInt(fDateOnStFiche);
        parcel.writeInt(fDateOnClCard);
        parcel.writeInt(fDateOnClfLine);
        parcel.writeInt(fDateOnItems);
        parcel.writeInt(fDateOnSrvCard);
        parcel.writeInt(fDateOnPrcList);
        parcel.writeInt(fDateOnPayPlans);
        parcel.writeInt(fDateOnShipInfo);
        parcel.writeInt(fDateOnBnCard);
        parcel.writeInt(fDateOnBnkAcc);
        parcel.writeInt(fDateOnAddTax);
        parcel.writeInt(fDateOnWorCabin);
        parcel.writeInt(fDateOnDeletedRecs);
        parcel.writeInt(appVersionOnWorProcess);
    }
    public int getAppVersionOnWorProcess() {
        return appVersionOnWorProcess;
    }
    public void setAppVersionOnWorProcess(final int i2) {
        appVersionOnWorProcess = i2;
    }
    enum C27152 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreenumsFDateField;

        static {
            final int[] iArr = new int[FDateField.values().length];
            SwitchMapcomprojemobilesalescoreenumsFDateField = iArr;
            try {
                iArr[FDateField.CL_CARD.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.CLF_LINE.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.ITEMS.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.SERVICES.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.PRICE_LIST.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.ADD_TAX.ordinal()] = 6;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.PAY_PLANS.ordinal()] = 7;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.SHIP_INFO.ordinal()] = 8;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.BANKS.ordinal()] = 9;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.BANK_ACCOUNTS.ordinal()] = 10;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.ST_FICHE.ordinal()] = 11;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.OR_FICHE.ordinal()] = 12;
            } catch (final NoSuchFieldError unused12) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.DELETED_RECS.ordinal()] = 13;
            } catch (final NoSuchFieldError unused13) {
            }
            try {
                C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[FDateField.CABINS.ordinal()] = 14;
            } catch (final NoSuchFieldError unused14) {
            }
        }
    }
    public int getFieldValue(final FDateField fDateField) {
        switch (C27152.SwitchMapcomprojemobilesalescoreenumsFDateField[fDateField.ordinal()]) {
            case 1:
                return this.fDateOnClCard;
            case 2:
                return this.fDateOnClfLine;
            case 3:
                return this.fDateOnItems;
            case 4:
                return this.fDateOnSrvCard;
            case 5:
                return this.fDateOnPrcList;
            case 6:
                return this.fDateOnAddTax;
            case 7:
                return this.fDateOnPayPlans;
            case 8:
                return this.fDateOnShipInfo;
            case 9:
                return this.fDateOnBnCard;
            case 10:
                return this.fDateOnBnkAcc;
            case 11:
                return this.fDateOnStFiche;
            case 12:
                return this.fDateOnOrFiche;
            case 13:
                return this.fDateOnDeletedRecs;
            case 14:
                return this.fDateOnWorCabin;
            default:
                throw new IllegalArgumentException("Unknown field: " + fDateField);
        }
    }
}
