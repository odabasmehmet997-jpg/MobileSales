package com.proje.mobilesales.features.sales.model;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.core.enums.EDocStatus;
import com.proje.mobilesales.core.enums.InvoiceStatus;
import com.proje.mobilesales.core.enums.OrderStatus;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesShort {
    private int clRef;
    private String documentNo;
    private int eDocStatus;
    private int erpInvoiceType;
    private String explanation;
    private String ficheDate;
    private String ficheDefinition;
    private String ficheId;
    private String ficheNo;
    private int ficheStatus;
    private boolean isCancel;
    private boolean isDiffError;
    private boolean isEdit;
    private boolean isTransfer;
    private int logicalRef;
    private String orderFicheStatus;
    private String orderState;
    private int orderStatus;
    private String payPlan;
    private int revisionCount;
    private int salesType;
    private String shipAddressCode;
    private String shipAddressName;
    private String speCode;
    private double total;
    private String tradingGroup;
    private int type;
    private String whDesc;

    public SalesShort() {
        this(0, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, null, false, false, 0, false, null, 0, 0, null, false, 0, 0, null, 0, 0, 268435455, null);
    }

    public int component1() {
        return this.logicalRef;
    }

    public String component10() {
        return this.documentNo;
    }

    public String component11() {
        return this.payPlan;
    }

    public int component12() {
        return this.orderStatus;
    }

    public double component13() {
        return this.total;
    }

    public String component14() {
        return this.explanation;
    }

    public boolean component15() {
        return this.isTransfer;
    }

    public boolean component16() {
        return this.isEdit;
    }

    public int component17() {
        return this.type;
    }

    public boolean component18() {
        return this.isCancel;
    }

    public String component19() {
        return this.orderState;
    }

    public String component2() {
        return this.shipAddressCode;
    }

    public int component20() {
        return this.revisionCount;
    }

    public int component21() {
        return this.ficheStatus;
    }

    public String component22() {
        return this.whDesc;
    }

    public boolean component23() {
        return this.isDiffError;
    }

    public int component24() {
        return this.eDocStatus;
    }

    public int component25() {
        return this.erpInvoiceType;
    }

    public String component26() {
        return this.orderFicheStatus;
    }

    public int component27() {
        return this.salesType;
    }

    public int component28() {
        return this.clRef;
    }

    public String component3() {
        return this.shipAddressName;
    }

    public String component4() {
        return this.ficheId;
    }

    public String component5() {
        return this.ficheNo;
    }

    public String component6() {
        return this.ficheDate;
    }

    public String component7() {
        return this.ficheDefinition;
    }

    public String component8() {
        return this.tradingGroup;
    }

    public String component9() {
        return this.speCode;
    }

    public SalesShort copy(int r32, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int r43, double d2, String str11, boolean z, boolean z2, int r49, boolean z3, String str12, int r52, int r53, String str13, boolean z4, int r56, int r57, String str14, int r59, int r60) {
        return new SalesShort(r32, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, r43, d2, str11, z, z2, r49, z3, str12, r52, r53, str13, z4, r56, r57, str14, r59, r60);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesShort salesShort)) {
            return false;
        }
        return this.logicalRef == salesShort.logicalRef && Intrinsics.areEqual(this.shipAddressCode, salesShort.shipAddressCode) && Intrinsics.areEqual(this.shipAddressName, salesShort.shipAddressName) && Intrinsics.areEqual(this.ficheId, salesShort.ficheId) && Intrinsics.areEqual(this.ficheNo, salesShort.ficheNo) && Intrinsics.areEqual(this.ficheDate, salesShort.ficheDate) && Intrinsics.areEqual(this.ficheDefinition, salesShort.ficheDefinition) && Intrinsics.areEqual(this.tradingGroup, salesShort.tradingGroup) && Intrinsics.areEqual(this.speCode, salesShort.speCode) && Intrinsics.areEqual(this.documentNo, salesShort.documentNo) && Intrinsics.areEqual(this.payPlan, salesShort.payPlan) && this.orderStatus == salesShort.orderStatus && Double.compare(this.total, salesShort.total) == 0 && Intrinsics.areEqual(this.explanation, salesShort.explanation) && this.isTransfer == salesShort.isTransfer && this.isEdit == salesShort.isEdit && this.type == salesShort.type && this.isCancel == salesShort.isCancel && Intrinsics.areEqual(this.orderState, salesShort.orderState) && this.revisionCount == salesShort.revisionCount && this.ficheStatus == salesShort.ficheStatus && Intrinsics.areEqual(this.whDesc, salesShort.whDesc) && this.isDiffError == salesShort.isDiffError && this.eDocStatus == salesShort.eDocStatus && this.erpInvoiceType == salesShort.erpInvoiceType && Intrinsics.areEqual(this.orderFicheStatus, salesShort.orderFicheStatus) && this.salesType == salesShort.salesType && this.clRef == salesShort.clRef;
    }

    public int hashCode() {
        int r0 = Integer.hashCode(this.logicalRef) * 31;
        String str = this.shipAddressCode;
        int r02 = (r0 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.shipAddressName;
        int r03 = (r02 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.ficheId;
        int r04 = (r03 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.ficheNo;
        int r05 = (r04 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.ficheDate;
        int r06 = (r05 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.ficheDefinition;
        int r07 = (r06 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.tradingGroup;
        int r08 = (r07 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.speCode;
        int r09 = (r08 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.documentNo;
        int r010 = (r09 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.payPlan;
        int r011 = (((((r010 + (str10 == null ? 0 : str10.hashCode())) * 31) + Integer.hashCode(this.orderStatus)) * 31) + Double.hashCode(this.total)) * 31;
        String str11 = this.explanation;
        int r012 = (((((((((r011 + (str11 == null ? 0 : str11.hashCode())) * 31) + Boolean.hashCode(this.isTransfer)) * 31) + Boolean.hashCode(this.isEdit)) * 31) + Integer.hashCode(this.type)) * 31) + Boolean.hashCode(this.isCancel)) * 31;
        String str12 = this.orderState;
        int r013 = (((((r012 + (str12 == null ? 0 : str12.hashCode())) * 31) + Integer.hashCode(this.revisionCount)) * 31) + Integer.hashCode(this.ficheStatus)) * 31;
        String str13 = this.whDesc;
        int r014 = (((((((r013 + (str13 == null ? 0 : str13.hashCode())) * 31) + Boolean.hashCode(this.isDiffError)) * 31) + Integer.hashCode(this.eDocStatus)) * 31) + Integer.hashCode(this.erpInvoiceType)) * 31;
        String str14 = this.orderFicheStatus;
        return ((((r014 + (str14 != null ? str14.hashCode() : 0)) * 31) + Integer.hashCode(this.salesType)) * 31) + Integer.hashCode(this.clRef);
    }

    public String toString() {
        return "SalesShort(logicalRef=" + this.logicalRef + ", shipAddressCode=" + this.shipAddressCode + ", shipAddressName=" + this.shipAddressName + ", ficheId=" + this.ficheId + ", ficheNo=" + this.ficheNo + ", ficheDate=" + this.ficheDate + ", ficheDefinition=" + this.ficheDefinition + ", tradingGroup=" + this.tradingGroup + ", speCode=" + this.speCode + ", documentNo=" + this.documentNo + ", payPlan=" + this.payPlan + ", orderStatus=" + this.orderStatus + ", total=" + this.total + ", explanation=" + this.explanation + ", isTransfer=" + this.isTransfer + ", isEdit=" + this.isEdit + ", type=" + this.type + ", isCancel=" + this.isCancel + ", orderState=" + this.orderState + ", revisionCount=" + this.revisionCount + ", ficheStatus=" + this.ficheStatus + ", whDesc=" + this.whDesc + ", isDiffError=" + this.isDiffError + ", eDocStatus=" + this.eDocStatus + ", erpInvoiceType=" + this.erpInvoiceType + ", orderFicheStatus=" + this.orderFicheStatus + ", salesType=" + this.salesType + ", clRef=" + this.clRef + ')';
    }

    public SalesShort(int r4, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int r15, double d2, String str11, boolean z, boolean z2, int r21, boolean z3, String str12, int r24, int r25, String str13, boolean z4, int r28, int r29, String str14, int r31, int r32) {
        this.logicalRef = r4;
        this.shipAddressCode = str;
        this.shipAddressName = str2;
        this.ficheId = str3;
        this.ficheNo = str4;
        this.ficheDate = str5;
        this.ficheDefinition = str6;
        this.tradingGroup = str7;
        this.speCode = str8;
        this.documentNo = str9;
        this.payPlan = str10;
        this.orderStatus = r15;
        this.total = d2;
        this.explanation = str11;
        this.isTransfer = z;
        this.isEdit = z2;
        this.type = r21;
        this.isCancel = z3;
        this.orderState = str12;
        this.revisionCount = r24;
        this.ficheStatus = r25;
        this.whDesc = str13;
        this.isDiffError = z4;
        this.eDocStatus = r28;
        this.erpInvoiceType = r29;
        this.orderFicheStatus = str14;
        this.salesType = r31;
        this.clRef = r32;
    }

    public SalesShort(int r31, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int r42, double d2, String str11, boolean z, boolean z2, int r48, boolean z3, String str12, int r51, int r52, String str13, boolean z4, int r55, int r56, String str14, int r58, int r59, int r60, DefaultConstructorMarker defaultConstructorMarker) {
        this((r60 & 1) != 0 ? 0 : r31, (r60 & 2) != 0 ? null : str, (r60 & 4) != 0 ? null : str2, (r60 & 8) != 0 ? null : str3, (r60 & 16) != 0 ? null : str4, (r60 & 32) != 0 ? null : str5, (r60 & 64) != 0 ? null : str6, (r60 & 128) != 0 ? null : str7, (r60 & 256) != 0 ? null : str8, (r60 & 512) != 0 ? null : str9, (r60 & 1024) != 0 ? null : str10, (r60 & 2048) != 0 ? 0 : r42, (r60 & 4096) != 0 ? 0.0d : d2, (r60 & 8192) != 0 ? null : str11, (r60 & 16384) == 0 && z, (r60 & 32768) == 0 && z2, (r60 & 65536) != 0 ? 0 : r48, (r60 & 131072) == 0 && z3, (r60 & 262144) != 0 ? null : str12, (r60 & 524288) != 0 ? 0 : r51, (r60 & 1048576) != 0 ? 0 : r52, (r60 & 2097152) != 0 ? null : str13, (r60 & 4194304) == 0 && z4, (r60 & 8388608) != 0 ? 0 : r55, (r60 & 16777216) != 0 ? 0 : r56, (r60 & 33554432) != 0 ? null : str14, (r60 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0 : r58, (r60 & 134217728) != 0 ? 0 : r59);
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int r1) {
        this.logicalRef = r1;
    }

    public String getShipAddressCode() {
        return this.shipAddressCode;
    }

    public void setShipAddressCode(String str) {
        this.shipAddressCode = str;
    }

    public String getShipAddressName() {
        return this.shipAddressName;
    }

    public void setShipAddressName(String str) {
        this.shipAddressName = str;
    }

    public String getFicheId() {
        return this.ficheId;
    }

    public void setFicheId(String str) {
        this.ficheId = str;
    }

    public String getFicheNo() {
        return this.ficheNo;
    }

    public void setFicheNo(String str) {
        this.ficheNo = str;
    }

    public String getFicheDate() {
        return this.ficheDate;
    }

    public void setFicheDate(String str) {
        this.ficheDate = str;
    }

    public String getFicheDefinition() {
        return this.ficheDefinition;
    }

    public void setFicheDefinition(String str) {
        this.ficheDefinition = str;
    }

    public String getTradingGroup() {
        return this.tradingGroup;
    }

    public void setTradingGroup(String str) {
        this.tradingGroup = str;
    }

    public String getSpeCode() {
        return this.speCode;
    }

    public void setSpeCode(String str) {
        this.speCode = str;
    }

    public String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String str) {
        this.documentNo = str;
    }

    public String getPayPlan() {
        return this.payPlan;
    }

    public void setPayPlan(String str) {
        this.payPlan = str;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int r1) {
        this.orderStatus = r1;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double d2) {
        this.total = d2;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String str) {
        this.explanation = str;
    }

    public boolean isTransfer() {
        return this.isTransfer;
    }

    public void setTransfer(boolean z) {
        this.isTransfer = z;
    }

    public boolean isEdit() {
        return this.isEdit;
    }

    public void setEdit(boolean z) {
        this.isEdit = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int r1) {
        this.type = r1;
    }

    public boolean isCancel() {
        return this.isCancel;
    }

    public void setCancel(boolean z) {
        this.isCancel = z;
    }

    public String getOrderState() {
        return this.orderState;
    }

    public void setOrderState(String str) {
        this.orderState = str;
    }

    public int getRevisionCount() {
        return this.revisionCount;
    }

    public void setRevisionCount(int r1) {
        this.revisionCount = r1;
    }

    public int getFicheStatus() {
        return this.ficheStatus;
    }

    public void setFicheStatus(int r1) {
        this.ficheStatus = r1;
    }

    public String getWhDesc() {
        return this.whDesc;
    }

    public void setWhDesc(String str) {
        this.whDesc = str;
    }

    public boolean isDiffError() {
        return this.isDiffError;
    }

    public void setDiffError(boolean z) {
        this.isDiffError = z;
    }

    public int getEDocStatus() {
        return this.eDocStatus;
    }

    public void setEDocStatus(int r1) {
        this.eDocStatus = r1;
    }

    public int getErpInvoiceType() {
        return this.erpInvoiceType;
    }

    public void setErpInvoiceType(int r1) {
        this.erpInvoiceType = r1;
    }

    public String getOrderFicheStatus() {
        return this.orderFicheStatus;
    }

    public void setOrderFicheStatus(String str) {
        this.orderFicheStatus = str;
    }

    public int getSalesType() {
        return this.salesType;
    }

    public void setSalesType(int r1) {
        this.salesType = r1;
    }

    public int getClRef() {
        return this.clRef;
    }

    public void setClRef(int r1) {
        this.clRef = r1;
    }

    public OrderStatus getSalesOrderStatus() {
        int r1 = this.orderStatus;
        if (r1 == 1) {
            return OrderStatus.OFFER;
        }
        if (r1 == 4) {
            return OrderStatus.DISPATCHABLE;
        }
        if (r1 == 2) {
            return OrderStatus.UNDISPATCHABLE;
        }
        return OrderStatus.OFFER;
    }

    public InvoiceStatus getInvoiceStatus() {
        return this.ficheStatus == 0 ? InvoiceStatus.ACTUAL : InvoiceStatus.OFFER;
    }

    public EDocStatus getEDocumentStatus() {
        int r1 = this.eDocStatus;
        if (r1 == 0) {
            return EDocStatus.None;
        }
        if (r1 == 1) {
            return EDocStatus.Draft;
        }
        if (r1 == 2) {
            return EDocStatus.Send;
        }
        return EDocStatus.None;
    }
}
