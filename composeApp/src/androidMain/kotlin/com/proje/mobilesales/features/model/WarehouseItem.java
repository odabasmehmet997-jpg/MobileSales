package com.proje.mobilesales.features.model;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class WarehouseItem {

    private int cardType;
    private double convFact1;
    private double convFact2;
    private int divUnit;
    private String expDate;
    private String grpBegCode;
    private String grpEndCode;
    private String itemCode;
    private String itemName;
    private String lineExp;
    private String locCode;

    private int lockTracking;

    private double mainAmount;

    private double onHand;

    @SerializedName("SERILOTN")
    @Column(name = "SERILOTN")
    private String seriLotn;

    @SerializedName("SLTLOGICALREF")
    @Column(name = "SLTLOGICALREF")
    private int sltLogicalRef;

    @SerializedName("STTRANSREF")
    @Column(name = "STTRANSREF")
    private int stTransRef;

    @SerializedName("STOCKREF")
    @Column(name = "STOCKREF")
    private int stockRef;

    @SerializedName("TRACKTYPE")
    @Column(name = "TRACKTYPE")
    private int trackType;

    @SerializedName("UNITCODE")
    @Column(name = "UNITCODE")
    private String unitCode;

    @SerializedName("UNITREF")
    @Column(name = "UNITREF")
    private int unitRef;

    @SerializedName("USEDAMOUNT")
    @Column(name = "USEDAMOUNT")
    private double usedAmount;

    @SerializedName("USEDGRPBEGCODE")
    @Column(name = "USEDGRPBEGCODE")
    private String usedGrpBegCode;

    @SerializedName("USEDGRPENDCODE")
    @Column(name = "USEDGRPENDCODE")
    private String usedGrpEndCode;

    @SerializedName("VARIANTCODE")
    @Column(name = "VARIANTCODE")
    private String variantCode;

    @SerializedName("VARIANTNAME")
    @Column(name = "VARIANTNAME")
    private String variantName;

    @SerializedName("VARIANTREF")
    @Column(name = "VARIANTREF")
    private int variantRef;

    public WarehouseItem() {
        this(0, 0, 0, 0, null, 0, null, 0, 0, null, null, null, null, null, 0, 0.0d, 0.0d, 0.0d, 0.0d, null, null, 0.0d, null, null, 0, null, null, 134217727, null);
    }

    public static WarehouseItem copydefault(final WarehouseItem warehouseItem, final int i2, final int i3, final int i4, final int i5, final String str, final int i6, final String str2, final int i7, final int i8, final String str3, final String str4, final String str5, final String str6, final String str7, final int i9, final double d2, final double d3, final double d4, final double d5, final String str8, final String str9, final double d6, final String str10, final String str11, final int i10, final String str12, final String str13, final int i11, final Object obj) {
        final int i12 = 0 != (i11 & 1) ? warehouseItem.trackType : i2;
        final int i13 = 0 != (i11 & 2) ? warehouseItem.lockTracking : i3;
        final int i14 = 0 != (i11 & 4) ? warehouseItem.stTransRef : i4;
        final int i15 = 0 != (i11 & 8) ? warehouseItem.sltLogicalRef : i5;
        final String str14 = 0 != (i11 & 16) ? warehouseItem.locCode : str;
        final int i16 = 0 != (i11 & 32) ? warehouseItem.stockRef : i6;
        final String str15 = 0 != (i11 & 64) ? warehouseItem.itemCode : str2;
        final int i17 = 0 != (i11 & 128) ? warehouseItem.cardType : i7;
        final int i18 = 0 != (i11 & 256) ? warehouseItem.variantRef : i8;
        final String str16 = 0 != (i11 & 512) ? warehouseItem.variantCode : str3;
        final String str17 = 0 != (i11 & 1024) ? warehouseItem.seriLotn : str4;
        final String str18 = 0 != (i11 & 2048) ? warehouseItem.expDate : str5;
        final String str19 = 0 != (i11 & 4096) ? warehouseItem.lineExp : str6;
        return warehouseItem.copy(i12, i13, i14, i15, str14, i16, str15, i17, i18, str16, str17, str18, str19, 0 != (i11 & 8192) ? warehouseItem.unitCode : str7, 0 != (i11 & 16384) ? warehouseItem.unitRef : i9, 0 != (i11 & 32768) ? warehouseItem.convFact1 : d2, 0 != (i11 & 65536) ? warehouseItem.convFact2 : d3, 0 != (i11 & 131072) ? warehouseItem.onHand : d4, 0 != (i11 & 262144) ? warehouseItem.mainAmount : d5, 0 != (i11 & 524288) ? warehouseItem.grpBegCode : str8, 0 != (1048576 & i11) ? warehouseItem.grpEndCode : str9, 0 != (i11 & 2097152) ? warehouseItem.usedAmount : d6, 0 != (i11 & 4194304) ? warehouseItem.usedGrpBegCode : str10, 0 != (8388608 & i11) ? warehouseItem.usedGrpEndCode : str11, 0 != (i11 & 16777216) ? warehouseItem.divUnit : i10, 0 != (i11 & 33554432) ? warehouseItem.itemName : str12, 0 != (i11 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? warehouseItem.variantName : str13);
    }

    public int component1() {
        return trackType;
    }

    public String component10() {
        return variantCode;
    }

    public String component11() {
        return seriLotn;
    }

    public String component12() {
        return expDate;
    }

    public String component13() {
        return lineExp;
    }

    public String component14() {
        return unitCode;
    }

    public int component15() {
        return unitRef;
    }

    public double component16() {
        return convFact1;
    }

    public double component17() {
        return convFact2;
    }

    public double component18() {
        return onHand;
    }

    public double component19() {
        return mainAmount;
    }

    public int component2() {
        return lockTracking;
    }

    public String component20() {
        return grpBegCode;
    }

    public String component21() {
        return grpEndCode;
    }

    public double component22() {
        return usedAmount;
    }

    public String component23() {
        return usedGrpBegCode;
    }

    public String component24() {
        return usedGrpEndCode;
    }

    public int component25() {
        return divUnit;
    }

    public String component26() {
        return itemName;
    }

    public String component27() {
        return variantName;
    }

    public int component3() {
        return stTransRef;
    }

    public int component4() {
        return sltLogicalRef;
    }

    public String component5() {
        return locCode;
    }

    public int component6() {
        return stockRef;
    }

    public String component7() {
        return itemCode;
    }

    public int component8() {
        return cardType;
    }

    public int component9() {
        return variantRef;
    }

    public WarehouseItem copy(final int i2, final int i3, final int i4, final int i5, final String str, final int i6, final String str2, final int i7, final int i8, final String str3, final String str4, final String expDate, final String str5, final String str6, final int i9, final double d2, final double d3, final double d4, final double d5, final String str7, final String str8, final double d6, final String str9, final String str10, final int i10, final String str11, final String str12) {
        Intrinsics.checkNotNullParameter(expDate, "expDate");
        return new WarehouseItem(i2, i3, i4, i5, str, i6, str2, i7, i8, str3, str4, expDate, str5, str6, i9, d2, d3, d4, d5, str7, str8, d6, str9, str10, i10, str11, str12);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WarehouseItem warehouseItem)) {
            return false;
        }
        return trackType == warehouseItem.trackType && lockTracking == warehouseItem.lockTracking && stTransRef == warehouseItem.stTransRef && sltLogicalRef == warehouseItem.sltLogicalRef && Intrinsics.areEqual(locCode, warehouseItem.locCode) && stockRef == warehouseItem.stockRef && Intrinsics.areEqual(itemCode, warehouseItem.itemCode) && cardType == warehouseItem.cardType && variantRef == warehouseItem.variantRef && Intrinsics.areEqual(variantCode, warehouseItem.variantCode) && Intrinsics.areEqual(seriLotn, warehouseItem.seriLotn) && Intrinsics.areEqual(expDate, warehouseItem.expDate) && Intrinsics.areEqual(lineExp, warehouseItem.lineExp) && Intrinsics.areEqual(unitCode, warehouseItem.unitCode) && unitRef == warehouseItem.unitRef && 0 == Double.compare(this.convFact1, warehouseItem.convFact1) && 0 == Double.compare(this.convFact2, warehouseItem.convFact2) && 0 == Double.compare(this.onHand, warehouseItem.onHand) && 0 == Double.compare(this.mainAmount, warehouseItem.mainAmount) && Intrinsics.areEqual(grpBegCode, warehouseItem.grpBegCode) && Intrinsics.areEqual(grpEndCode, warehouseItem.grpEndCode) && 0 == Double.compare(this.usedAmount, warehouseItem.usedAmount) && Intrinsics.areEqual(usedGrpBegCode, warehouseItem.usedGrpBegCode) && Intrinsics.areEqual(usedGrpEndCode, warehouseItem.usedGrpEndCode) && divUnit == warehouseItem.divUnit && Intrinsics.areEqual(itemName, warehouseItem.itemName) && Intrinsics.areEqual(variantName, warehouseItem.variantName);
    }

    public int hashCode() {
        final int hashCode = ((((((Integer.hashCode(trackType) * 31) + Integer.hashCode(lockTracking)) * 31) + Integer.hashCode(stTransRef)) * 31) + Integer.hashCode(sltLogicalRef)) * 31;
        final String str = locCode;
        final int hashCode2 = (((hashCode + (null == str ? 0 : str.hashCode())) * 31) + Integer.hashCode(stockRef)) * 31;
        final String str2 = itemCode;
        final int hashCode3 = (((((hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31) + Integer.hashCode(cardType)) * 31) + Integer.hashCode(variantRef)) * 31;
        final String str3 = variantCode;
        final int hashCode4 = (hashCode3 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = seriLotn;
        final int hashCode5 = (((hashCode4 + (null == str4 ? 0 : str4.hashCode())) * 31) + expDate.hashCode()) * 31;
        final String str5 = lineExp;
        final int hashCode6 = (hashCode5 + (null == str5 ? 0 : str5.hashCode())) * 31;
        final String str6 = unitCode;
        final int hashCode7 = (((((((((((hashCode6 + (null == str6 ? 0 : str6.hashCode())) * 31) + Integer.hashCode(unitRef)) * 31) + Double.hashCode(convFact1)) * 31) + Double.hashCode(convFact2)) * 31) + Double.hashCode(onHand)) * 31) + Double.hashCode(mainAmount)) * 31;
        final String str7 = grpBegCode;
        final int hashCode8 = (hashCode7 + (null == str7 ? 0 : str7.hashCode())) * 31;
        final String str8 = grpEndCode;
        final int hashCode9 = (((hashCode8 + (null == str8 ? 0 : str8.hashCode())) * 31) + Double.hashCode(usedAmount)) * 31;
        final String str9 = usedGrpBegCode;
        final int hashCode10 = (hashCode9 + (null == str9 ? 0 : str9.hashCode())) * 31;
        final String str10 = usedGrpEndCode;
        final int hashCode11 = (((hashCode10 + (null == str10 ? 0 : str10.hashCode())) * 31) + Integer.hashCode(divUnit)) * 31;
        final String str11 = itemName;
        final int hashCode12 = (hashCode11 + (null == str11 ? 0 : str11.hashCode())) * 31;
        final String str12 = variantName;
        return hashCode12 + (null != str12 ? str12.hashCode() : 0);
    }

    public String toString() {
        return "WarehouseItem(trackType=" + trackType + ", lockTracking=" + lockTracking + ", stTransRef=" + stTransRef + ", sltLogicalRef=" + sltLogicalRef + ", locCode=" + locCode + ", stockRef=" + stockRef + ", itemCode=" + itemCode + ", cardType=" + cardType + ", variantRef=" + variantRef + ", variantCode=" + variantCode + ", seriLotn=" + seriLotn + ", expDate=" + expDate + ", lineExp=" + lineExp + ", unitCode=" + unitCode + ", unitRef=" + unitRef + ", convFact1=" + convFact1 + ", convFact2=" + convFact2 + ", onHand=" + onHand + ", mainAmount=" + mainAmount + ", grpBegCode=" + grpBegCode + ", grpEndCode=" + grpEndCode + ", usedAmount=" + usedAmount + ", usedGrpBegCode=" + usedGrpBegCode + ", usedGrpEndCode=" + usedGrpEndCode + ", divUnit=" + divUnit + ", itemName=" + itemName + ", variantName=" + variantName + ')';
    }

    public WarehouseItem(final int i2, final int i3, final int i4, final int i5, final String str, final int i6, final String str2, final int i7, final int i8, final String str3, final String str4, final String expDate, final String str5, final String str6, final int i9, final double d2, final double d3, final double d4, final double d5, final String str7, final String str8, final double d6, final String str9, final String str10, final int i10, final String str11, final String str12) {
        Intrinsics.checkNotNullParameter(expDate, "expDate");
        trackType = i2;
        lockTracking = i3;
        stTransRef = i4;
        sltLogicalRef = i5;
        locCode = str;
        stockRef = i6;
        itemCode = str2;
        cardType = i7;
        variantRef = i8;
        variantCode = str3;
        seriLotn = str4;
        this.expDate = expDate;
        lineExp = str5;
        unitCode = str6;
        unitRef = i9;
        convFact1 = d2;
        convFact2 = d3;
        onHand = d4;
        mainAmount = d5;
        grpBegCode = str7;
        grpEndCode = str8;
        usedAmount = d6;
        usedGrpBegCode = str9;
        usedGrpEndCode = str10;
        divUnit = i10;
        itemName = str11;
        variantName = str12;
    }

    public int getTrackType() {
        return trackType;
    }

    public void setTrackType(final int i2) {
        trackType = i2;
    }

    public int getLockTracking() {
        return lockTracking;
    }

    public void setLockTracking(final int i2) {
        lockTracking = i2;
    }

    public int getStTransRef() {
        return stTransRef;
    }

    public void setStTransRef(final int i2) {
        stTransRef = i2;
    }

    public int getSltLogicalRef() {
        return sltLogicalRef;
    }

    public void setSltLogicalRef(final int i2) {
        sltLogicalRef = i2;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(final String str) {
        locCode = str;
    }

    public int getStockRef() {
        return stockRef;
    }

    public void setStockRef(final int i2) {
        stockRef = i2;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(final String str) {
        itemCode = str;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(final int i2) {
        cardType = i2;
    }

    public int getVariantRef() {
        return variantRef;
    }

    public void setVariantRef(final int i2) {
        variantRef = i2;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public void setVariantCode(final String str) {
        variantCode = str;
    }

    public String getSeriLotn() {
        return seriLotn;
    }

    public void setSeriLotn(final String str) {
        seriLotn = str;
    }

    public WarehouseItem(final int i2, final int i3, final int i4, final int i5, final String str, final int i6, final String str2, final int i7, final int i8, final String str3, final String str4, final String str5, final String str6, final String str7, final int i9, final double d2, final double d3, final double d4, final double d5, final String str8, final String str9, final double d6, final String str10, final String str11, final int i10, final String str12, final String str13, final int i11, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i11 & 1) ? 0 : i2, 0 != (i11 & 2) ? 0 : i3, 0 != (i11 & 4) ? 0 : i4, 0 != (i11 & 8) ? 0 : i5, 0 != (i11 & 16) ? null : str, 0 != (i11 & 32) ? 0 : i6, 0 != (i11 & 64) ? null : str2, 0 != (i11 & 128) ? 0 : i7, 0 != (i11 & 256) ? 0 : i8, 0 != (i11 & 512) ? null : str3, 0 != (i11 & 1024) ? null : str4, 0 != (i11 & 2048) ? "" : str5, 0 != (i11 & 4096) ? null : str6, 0 != (i11 & 8192) ? null : str7, 0 != (i11 & 16384) ? 0 : i9, 0 != (i11 & 32768) ? 0.0d : d2, 0 != (i11 & 65536) ? 0.0d : d3, 0 != (i11 & 131072) ? 0.0d : d4, 0 != (i11 & 262144) ? 0.0d : d5, 0 != (i11 & 524288) ? null : str8, 0 != (i11 & 1048576) ? null : str9, 0 == (i11 & 2097152) ? d6 : 0.0d, 0 != (i11 & 4194304) ? null : str10, 0 != (i11 & 8388608) ? null : str11, 0 != (i11 & 16777216) ? 0 : i10, 0 != (i11 & 33554432) ? null : str12, 0 != (i11 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str13);
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        expDate = str;
    }

    public String getLineExp() {
        return lineExp;
    }

    public void setLineExp(final String str) {
        lineExp = str;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(final String str) {
        unitCode = str;
    }

    public int getUnitRef() {
        return unitRef;
    }

    public void setUnitRef(final int i2) {
        unitRef = i2;
    }

    public double getConvFact1() {
        return convFact1;
    }

    public void setConvFact1(final double d2) {
        convFact1 = d2;
    }

    public double getConvFact2() {
        return convFact2;
    }

    public void setConvFact2(final double d2) {
        convFact2 = d2;
    }

    public double getOnHand() {
        return onHand;
    }

    public void setOnHand(final double d2) {
        onHand = d2;
    }

    public double getMainAmount() {
        return mainAmount;
    }

    public void setMainAmount(final double d2) {
        mainAmount = d2;
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

    public int getDivUnit() {
        return divUnit;
    }

    public void setDivUnit(final int i2) {
        divUnit = i2;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String str) {
        itemName = str;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(final String str) {
        variantName = str;
    }
}
