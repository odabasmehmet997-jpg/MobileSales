package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CheckSeriGroup implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CheckSeriGroup> CREATOR = new Parcelable.Creator<CheckSeriGroup>() {
        public CheckSeriGroup createFromParcel(final Parcel parcel) {
            return new CheckSeriGroup(parcel);
        }
        public CheckSeriGroup[] newArray(final int i2) {
            return new CheckSeriGroup[i2];
        }
    };
    private double availableAmount;
    private boolean checked;
    private int detailRef;
    private String expDate;
    private String grpBegCode;
    private String grpEndCode;
    private int f1253id;
    private String locationCode;
    private int logicalRef;
    private String name;
    private double orgAmount;
    private String orgGrpBegCode;
    private String orgGrpEndCode;
    private double remAmount;
    private String seriLotNo;
    private int sourceUnitRef;
    private int stTransRef;
    private String unit;
    private double usedAmount;
    private String usedGrpBegCode;
    private String usedGrpEndCode;
    public int describeContents() {
        return 0;
    }
    public CheckSeriGroup() {
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
    public double getOrgAmount() {
        return orgAmount;
    }
    public void setOrgAmount(final double d2) {
        orgAmount = d2;
    }
    public double getRemAmount() {
        return remAmount;
    }
    public void setRemAmount(final double d2) {
        remAmount = d2;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(final String str) {
        unit = str;
    }
    public String getExpDate() {
        return expDate;
    }
    public void setExpDate(final String str) {
        expDate = str;
    }
    public String getGrpBegCode() {
        return grpBegCode;
    }
    public void setGrpBegCode(final String str) {
        grpBegCode = str;
    }
    public String getGrpEndCode() {
        return grpEndCode;
    }
    public void setGrpEndCode(final String str) {
        grpEndCode = str;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public double getUsedAmount() {
        return usedAmount;
    }
    public void setUsedAmount(final double d2) {
        usedAmount = d2;
    }
    public String getUsedGrpBegCode() {
        return usedGrpBegCode;
    }
    public void setUsedGrpBegCode(final String str) {
        usedGrpBegCode = str;
    }
    public String getUsedGrpEndCode() {
        return usedGrpEndCode;
    }
    public void setUsedGrpEndCode(final String str) {
        usedGrpEndCode = str;
    }
    public double getAvailableAmount() {
        return availableAmount;
    }
    public void setAvailableAmount(final double d2) {
        availableAmount = d2;
    }
    public String getOrgGrpBegCode() {
        return orgGrpBegCode;
    }
    public void setOrgGrpBegCode(final String str) {
        orgGrpBegCode = str;
    }
    public String getOrgGrpEndCode() {
        return orgGrpEndCode;
    }
    public void setOrgGrpEndCode(final String str) {
        orgGrpEndCode = str;
    }
    public CheckSeriGroup clone() throws CloneNotSupportedException {
        return clone();
    }
    protected CheckSeriGroup(final Parcel parcel) {
        logicalRef = parcel.readInt();
        orgAmount = parcel.readDouble();
        remAmount = parcel.readDouble();
        unit = parcel.readString();
        expDate = parcel.readString();
        grpBegCode = parcel.readString();
        grpEndCode = parcel.readString();
        orgGrpBegCode = parcel.readString();
        orgGrpEndCode = parcel.readString();
        name = parcel.readString();
        usedAmount = parcel.readDouble();
        usedGrpBegCode = parcel.readString();
        usedGrpEndCode = parcel.readString();
        availableAmount = parcel.readDouble();
        f1253id = parcel.readInt();
        detailRef = parcel.readInt();
        locationCode = parcel.readString();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(logicalRef);
        parcel.writeDouble(orgAmount);
        parcel.writeDouble(remAmount);
        parcel.writeString(unit);
        parcel.writeString(expDate);
        parcel.writeString(grpBegCode);
        parcel.writeString(grpEndCode);
        parcel.writeString(orgGrpBegCode);
        parcel.writeString(orgGrpEndCode);
        parcel.writeString(name);
        parcel.writeDouble(usedAmount);
        parcel.writeString(usedGrpBegCode);
        parcel.writeString(usedGrpEndCode);
        parcel.writeDouble(availableAmount);
        parcel.writeInt(f1253id);
        parcel.writeInt(detailRef);
        parcel.writeString(locationCode);
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(final boolean z) {
        checked = z;
    }
    public int getId() {
        return f1253id;
    }
    public void setId(final int i2) {
        f1253id = i2;
    }
    public int getDetailRef() {
        return detailRef;
    }
    public void setDetailRef(final int i2) {
        detailRef = i2;
    }
    public String getLocationCode() {
        return locationCode;
    }
    public void setLocationCode(final String str) {
        locationCode = str;
    }
    public int getStTransRef() {
        return stTransRef;
    }
    public void setStTransRef(final int i2) {
        stTransRef = i2;
    }
    public String getSeriLotNo() {
        return seriLotNo;
    }
    public void setSeriLotNo(final String str) {
        seriLotNo = str;
    }
    public int getSourceUnitRef() {
        return sourceUnitRef;
    }
    public void setSourceUnitRef(final int i2) {
        sourceUnitRef = i2;
    }
}
