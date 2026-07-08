package com.proje.mobilesales.features.model;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EInvoicePdfHeader {
    private String chassisNum1;
    private String clientAddr1;
    private String clientAddr2;
    private String clientCity;
    private String clientCode;
    private String clientDef;
    private String clientDistrict;
    private String clientEmailAddr;
    private String clientFax;
    private String clientIdentityNr;
    private String clientInCharge;
    private String clientPhone1;
    private String clientPhone2;
    private String clientPostCode;
    private int clientRef;
    private String clientTaxNr;
    private String clientTaxOffice;
    private String clientTaxOfficeCode;
    private String clientTown;
    private String clientWebAddr;
    private String despatchDate;
    private String despatchNo;
    private String docDate;
    private String driverName1;
    private String driverSurname1;
    private String driverTckNo1;
    private String genExp1;
    private String genExp2;
    private String genExp3;
    private String genExp4;
    private double grossTotal;
    private String guid;
    private String invoiceDate;
    private String invoiceNo;
    private int logicalRef;
    private double netTotal;
    private String plateNum1;
    private String shipAddressTaxNr;
    private String shipAddressTitle;
    private String shipAgentCode;
    private String shipAgentTaxNr;
    private String shipAgentTitle;
    private String shipTypeCode;
    private int sourceIndex;
    private double totalDiscount;
    private double totalVat;
    private int trCurr;
    private double trRate;
    public EInvoicePdfHeader() {
        this(0, null, null, null, null, 0, 0.0d, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, -1, 65535, null);
    }
    public int component1() {
        return logicalRef;
    }
    public String component10() {
        return clientDef;
    }
    public String component11() {
        return clientAddr1;
    }
    public String component12() {
        return clientAddr2;
    }
    public String component13() {
        return clientCity;
    }
    public String component14() {
        return clientDistrict;
    }
    public String component15() {
        return clientPhone1;
    }
    public String component16() {
        return clientPhone2;
    }
    public String component17() {
        return clientFax;
    }
    public String component18() {
        return clientTaxNr;
    }
    public String component19() {
        return clientTaxOfficeCode;
    }
    public String component2() {
        return guid;
    }

    public String component20() {
        return clientTown;
    }

    public String component21() {
        return clientTaxOffice;
    }

    public String component22() {
        return clientInCharge;
    }

    public String component23() {
        return clientEmailAddr;
    }

    public String component24() {
        return clientIdentityNr;
    }

    public String component25() {
        return clientWebAddr;
    }

    public String component26() {
        return clientPostCode;
    }

    public double component27() {
        return totalVat;
    }

    public double component28() {
        return netTotal;
    }

    public double component29() {
        return totalDiscount;
    }

    public String component3() {
        return invoiceNo;
    }

    public double component30() {
        return grossTotal;
    }

    public String component31() {
        return genExp1;
    }

    public String component32() {
        return genExp2;
    }

    public String component33() {
        return genExp3;
    }

    public String component34() {
        return genExp4;
    }

    public String component35() {
        return shipTypeCode;
    }

    public String component36() {
        return shipAgentCode;
    }

    public int component37() {
        return sourceIndex;
    }

    public String component38() {
        return despatchNo;
    }

    public String component39() {
        return despatchDate;
    }

    public String component4() {
        return invoiceDate;
    }

    public String component40() {
        return shipAgentTitle;
    }

    public String component41() {
        return shipAgentTaxNr;
    }

    public String component42() {
        return shipAddressTitle;
    }

    public String component43() {
        return shipAddressTaxNr;
    }

    public String component44() {
        return plateNum1;
    }

    public String component45() {
        return chassisNum1;
    }

    public String component46() {
        return driverName1;
    }

    public String component47() {
        return driverSurname1;
    }

    public String component48() {
        return driverTckNo1;
    }

    public String component5() {
        return docDate;
    }

    public int component6() {
        return clientRef;
    }

    public double component7() {
        return trRate;
    }

    public int component8() {
        return trCurr;
    }

    public String component9() {
        return clientCode;
    }

    public EInvoicePdfHeader copy(final int i2, final String str, final String str2, final String str3, final String str4, final int i3, final double d2, final int i4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final String str14, final String str15, final String str16, final String str17, final String str18, final String str19, final String str20, final String str21, final String str22, final double d3, final double d4, final double d5, final double d6, final String str23, final String str24, final String str25, final String str26, final String str27, final String str28, final int i5, final String str29, final String str30, final String str31, final String str32, final String str33, final String str34, final String str35, final String str36, final String str37, final String str38, final String str39) {
        return new EInvoicePdfHeader(i2, str, str2, str3, str4, i3, d2, i4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, d3, d4, d5, d6, str23, str24, str25, str26, str27, str28, i5, str29, str30, str31, str32, str33, str34, str35, str36, str37, str38, str39);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EInvoicePdfHeader eInvoicePdfHeader)) {
            return false;
        }
        return this.logicalRef == eInvoicePdfHeader.logicalRef && Intrinsics.areEqual(this.guid, eInvoicePdfHeader.guid) && Intrinsics.areEqual(this.invoiceNo, eInvoicePdfHeader.invoiceNo) && Intrinsics.areEqual(this.invoiceDate, eInvoicePdfHeader.invoiceDate) && Intrinsics.areEqual(this.docDate, eInvoicePdfHeader.docDate) && this.clientRef == eInvoicePdfHeader.clientRef && Double.compare(this.trRate, eInvoicePdfHeader.trRate) == 0 && this.trCurr == eInvoicePdfHeader.trCurr && Intrinsics.areEqual(this.clientCode, eInvoicePdfHeader.clientCode) && Intrinsics.areEqual(this.clientDef, eInvoicePdfHeader.clientDef) && Intrinsics.areEqual(this.clientAddr1, eInvoicePdfHeader.clientAddr1) && Intrinsics.areEqual(this.clientAddr2, eInvoicePdfHeader.clientAddr2) && Intrinsics.areEqual(this.clientCity, eInvoicePdfHeader.clientCity) && Intrinsics.areEqual(this.clientDistrict, eInvoicePdfHeader.clientDistrict) && Intrinsics.areEqual(this.clientPhone1, eInvoicePdfHeader.clientPhone1) && Intrinsics.areEqual(this.clientPhone2, eInvoicePdfHeader.clientPhone2) && Intrinsics.areEqual(this.clientFax, eInvoicePdfHeader.clientFax) && Intrinsics.areEqual(this.clientTaxNr, eInvoicePdfHeader.clientTaxNr) && Intrinsics.areEqual(this.clientTaxOfficeCode, eInvoicePdfHeader.clientTaxOfficeCode) && Intrinsics.areEqual(this.clientTown, eInvoicePdfHeader.clientTown) && Intrinsics.areEqual(this.clientTaxOffice, eInvoicePdfHeader.clientTaxOffice) && Intrinsics.areEqual(this.clientInCharge, eInvoicePdfHeader.clientInCharge) && Intrinsics.areEqual(this.clientEmailAddr, eInvoicePdfHeader.clientEmailAddr) && Intrinsics.areEqual(this.clientIdentityNr, eInvoicePdfHeader.clientIdentityNr) && Intrinsics.areEqual(this.clientWebAddr, eInvoicePdfHeader.clientWebAddr) && Intrinsics.areEqual(this.clientPostCode, eInvoicePdfHeader.clientPostCode) && Double.compare(this.totalVat, eInvoicePdfHeader.totalVat) == 0 && Double.compare(this.netTotal, eInvoicePdfHeader.netTotal) == 0 && Double.compare(this.totalDiscount, eInvoicePdfHeader.totalDiscount) == 0 && Double.compare(this.grossTotal, eInvoicePdfHeader.grossTotal) == 0 && Intrinsics.areEqual(this.genExp1, eInvoicePdfHeader.genExp1) && Intrinsics.areEqual(this.genExp2, eInvoicePdfHeader.genExp2) && Intrinsics.areEqual(this.genExp3, eInvoicePdfHeader.genExp3) && Intrinsics.areEqual(this.genExp4, eInvoicePdfHeader.genExp4) && Intrinsics.areEqual(this.shipTypeCode, eInvoicePdfHeader.shipTypeCode) && Intrinsics.areEqual(this.shipAgentCode, eInvoicePdfHeader.shipAgentCode) && this.sourceIndex == eInvoicePdfHeader.sourceIndex && Intrinsics.areEqual(this.despatchNo, eInvoicePdfHeader.despatchNo) && Intrinsics.areEqual(this.despatchDate, eInvoicePdfHeader.despatchDate) && Intrinsics.areEqual(this.shipAgentTitle, eInvoicePdfHeader.shipAgentTitle) && Intrinsics.areEqual(this.shipAgentTaxNr, eInvoicePdfHeader.shipAgentTaxNr) && Intrinsics.areEqual(this.shipAddressTitle, eInvoicePdfHeader.shipAddressTitle) && Intrinsics.areEqual(this.shipAddressTaxNr, eInvoicePdfHeader.shipAddressTaxNr) && Intrinsics.areEqual(this.plateNum1, eInvoicePdfHeader.plateNum1) && Intrinsics.areEqual(this.chassisNum1, eInvoicePdfHeader.chassisNum1) && Intrinsics.areEqual(this.driverName1, eInvoicePdfHeader.driverName1) && Intrinsics.areEqual(this.driverSurname1, eInvoicePdfHeader.driverSurname1) && Intrinsics.areEqual(this.driverTckNo1, eInvoicePdfHeader.driverTckNo1);
    }
    public int hashCode() {
        int hashCode = Integer.hashCode(this.logicalRef) * 31;
        String str = this.guid;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.invoiceNo;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.invoiceDate;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.docDate;
        int hashCode5 = (((((((hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31) + Integer.hashCode(this.clientRef)) * 31) + Double.hashCode(this.trRate)) * 31) + Integer.hashCode(this.trCurr)) * 31;
        String str5 = this.clientCode;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.clientDef;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.clientAddr1;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.clientAddr2;
        int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.clientCity;
        int hashCode10 = (hashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.clientDistrict;
        int hashCode11 = (hashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.clientPhone1;
        int hashCode12 = (hashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.clientPhone2;
        int hashCode13 = (hashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.clientFax;
        int hashCode14 = (hashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.clientTaxNr;
        int hashCode15 = (hashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.clientTaxOfficeCode;
        int hashCode16 = (hashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.clientTown;
        int hashCode17 = (hashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.clientTaxOffice;
        int hashCode18 = (hashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.clientInCharge;
        int hashCode19 = (hashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.clientEmailAddr;
        int hashCode20 = (hashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.clientIdentityNr;
        int hashCode21 = (hashCode20 + (str20 == null ? 0 : str20.hashCode())) * 31;
        String str21 = this.clientWebAddr;
        int hashCode22 = (hashCode21 + (str21 == null ? 0 : str21.hashCode())) * 31;
        String str22 = this.clientPostCode;
        int hashCode23 = (((((((((hashCode22 + (str22 == null ? 0 : str22.hashCode())) * 31) + Double.hashCode(this.totalVat)) * 31) + Double.hashCode(this.netTotal)) * 31) + Double.hashCode(this.totalDiscount)) * 31) + Double.hashCode(this.grossTotal)) * 31;
        String str23 = this.genExp1;
        int hashCode24 = (hashCode23 + (str23 == null ? 0 : str23.hashCode())) * 31;
        String str24 = this.genExp2;
        int hashCode25 = (hashCode24 + (str24 == null ? 0 : str24.hashCode())) * 31;
        String str25 = this.genExp3;
        int hashCode26 = (hashCode25 + (str25 == null ? 0 : str25.hashCode())) * 31;
        String str26 = this.genExp4;
        int hashCode27 = (hashCode26 + (str26 == null ? 0 : str26.hashCode())) * 31;
        String str27 = this.shipTypeCode;
        int hashCode28 = (hashCode27 + (str27 == null ? 0 : str27.hashCode())) * 31;
        String str28 = this.shipAgentCode;
        int hashCode29 = (((hashCode28 + (str28 == null ? 0 : str28.hashCode())) * 31) + Integer.hashCode(this.sourceIndex)) * 31;
        String str29 = this.despatchNo;
        int hashCode30 = (hashCode29 + (str29 == null ? 0 : str29.hashCode())) * 31;
        String str30 = this.despatchDate;
        int hashCode31 = (hashCode30 + (str30 == null ? 0 : str30.hashCode())) * 31;
        String str31 = this.shipAgentTitle;
        int hashCode32 = (hashCode31 + (str31 == null ? 0 : str31.hashCode())) * 31;
        String str32 = this.shipAgentTaxNr;
        int hashCode33 = (hashCode32 + (str32 == null ? 0 : str32.hashCode())) * 31;
        String str33 = this.shipAddressTitle;
        int hashCode34 = (hashCode33 + (str33 == null ? 0 : str33.hashCode())) * 31;
        String str34 = this.shipAddressTaxNr;
        int hashCode35 = (hashCode34 + (str34 == null ? 0 : str34.hashCode())) * 31;
        String str35 = this.plateNum1;
        int hashCode36 = (hashCode35 + (str35 == null ? 0 : str35.hashCode())) * 31;
        String str36 = this.chassisNum1;
        int hashCode37 = (hashCode36 + (str36 == null ? 0 : str36.hashCode())) * 31;
        String str37 = this.driverName1;
        int hashCode38 = (hashCode37 + (str37 == null ? 0 : str37.hashCode())) * 31;
        String str38 = this.driverSurname1;
        int hashCode39 = (hashCode38 + (str38 == null ? 0 : str38.hashCode())) * 31;
        String str39 = this.driverTckNo1;
        int hashCode40 = str39 != null ? str39.hashCode() : 0;
        return hashCode39 + hashCode40;
    }
    public String toString() {
        return "EInvoicePdfHeader(logicalRef=" + this.logicalRef + ", guid=" + this.guid + ", invoiceNo=" + this.invoiceNo + ", invoiceDate=" + this.invoiceDate + ", docDate=" + this.docDate + ", clientRef=" + this.clientRef + ", trRate=" + this.trRate + ", trCurr=" + this.trCurr + ", clientCode=" + this.clientCode + ", clientDef=" + this.clientDef + ", clientAddr1=" + this.clientAddr1 + ", clientAddr2=" + this.clientAddr2 + ", clientCity=" + this.clientCity + ", clientDistrict=" + this.clientDistrict + ", clientPhone1=" + this.clientPhone1 + ", clientPhone2=" + this.clientPhone2 + ", clientFax=" + this.clientFax + ", clientTaxNr=" + this.clientTaxNr + ", clientTaxOfficeCode=" + this.clientTaxOfficeCode + ", clientTown=" + this.clientTown + ", clientTaxOffice=" + this.clientTaxOffice + ", clientInCharge=" + this.clientInCharge + ", clientEmailAddr=" + this.clientEmailAddr + ", clientIdentityNr=" + this.clientIdentityNr + ", clientWebAddr=" + this.clientWebAddr + ", clientPostCode=" + this.clientPostCode + ", totalVat=" + this.totalVat + ", netTotal=" + this.netTotal + ", totalDiscount=" + this.totalDiscount + ", grossTotal=" + this.grossTotal + ", genExp1=" + this.genExp1 + ", genExp2=" + this.genExp2 + ", genExp3=" + this.genExp3 + ", genExp4=" + this.genExp4 + ", shipTypeCode=" + this.shipTypeCode + ", shipAgentCode=" + this.shipAgentCode + ", sourceIndex=" + this.sourceIndex + ", despatchNo=" + this.despatchNo + ", despatchDate=" + this.despatchDate + ", shipAgentTitle=" + this.shipAgentTitle + ", shipAgentTaxNr=" + this.shipAgentTaxNr + ", shipAddressTitle=" + this.shipAddressTitle + ", shipAddressTaxNr=" + this.shipAddressTaxNr + ", plateNum1=" + this.plateNum1 + ", chassisNum1=" + this.chassisNum1 + ", driverName1=" + this.driverName1 + ", driverSurname1=" + this.driverSurname1 + ", driverTckNo1=" + this.driverTckNo1 + ')';
    }
    public EInvoicePdfHeader(int i2, String str, String str2, String str3, String str4, int i3, double d2, int i4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, double d3, double d4, double d5, double d6, String str23, String str24, String str25, String str26, String str27, String str28, int i5, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39) {
        this.logicalRef = i2;
        this.guid = str;
        this.invoiceNo = str2;
        this.invoiceDate = str3;
        this.docDate = str4;
        this.clientRef = i3;
        this.trRate = d2;
        this.trCurr = i4;
        this.clientCode = str5;
        this.clientDef = str6;
        this.clientAddr1 = str7;
        this.clientAddr2 = str8;
        this.clientCity = str9;
        this.clientDistrict = str10;
        this.clientPhone1 = str11;
        this.clientPhone2 = str12;
        this.clientFax = str13;
        this.clientTaxNr = str14;
        this.clientTaxOfficeCode = str15;
        this.clientTown = str16;
        this.clientTaxOffice = str17;
        this.clientInCharge = str18;
        this.clientEmailAddr = str19;
        this.clientIdentityNr = str20;
        this.clientWebAddr = str21;
        this.clientPostCode = str22;
        this.totalVat = d3;
        this.netTotal = d4;
        this.totalDiscount = d5;
        this.grossTotal = d6;
        this.genExp1 = str23;
        this.genExp2 = str24;
        this.genExp3 = str25;
        this.genExp4 = str26;
        this.shipTypeCode = str27;
        this.shipAgentCode = str28;
        this.sourceIndex = i5;
        this.despatchNo = str29;
        this.despatchDate = str30;
        this.shipAgentTitle = str31;
        this.shipAgentTaxNr = str32;
        this.shipAddressTitle = str33;
        this.shipAddressTaxNr = str34;
        this.plateNum1 = str35;
        this.chassisNum1 = str36;
        this.driverName1 = str37;
        this.driverSurname1 = str38;
        this.driverTckNo1 = str39;
    }
    public  EInvoicePdfHeader(int i2, String str, String str2, String str3, String str4, int i3, double d2, int i4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, double d3, double d4, double d5, double d6, String str23, String str24, String str25, String str26, String str27, String str28, int i5, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i2, (i6 & 2) != 0 ? null : str, (i6 & 4) != 0 ? null : str2, (i6 & 8) != 0 ? null : str3, (i6 & 16) != 0 ? null : str4, (i6 & 32) != 0 ? 0 : i3, (i6 & 64) != 0 ? 0.0d : d2, (i6 & 128) != 0 ? 0 : i4, (i6 & 256) != 0 ? null : str5, (i6 & 512) != 0 ? null : str6, (i6 & 1024) != 0 ? null : str7, (i6 & 2048) != 0 ? null : str8, (i6 & 4096) != 0 ? null : str9, (i6 & 8192) != 0 ? null : str10, (i6 & 16384) != 0 ? null : str11, (i6 & 32768) != 0 ? null : str12, (i6 & 65536) != 0 ? null : str13, (i6 & 131072) != 0 ? null : str14, (i6 & 262144) != 0 ? null : str15, (i6 & 524288) != 0 ? null : str16, (i6 & 1048576) != 0 ? null : str17, (i6 & 2097152) != 0 ? null : str18, (i6 & 4194304) != 0 ? null : str19, (i6 & 8388608) != 0 ? null : str20, (i6 & 16777216) != 0 ? null : str21, (i6 & 33554432) != 0 ? null : str22, (i6 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0.0d : d3, (i6 & 134217728) != 0 ? 0.0d : d4, (i6 & 268435456) != 0 ? 0.0d : d5, (i6 & 536870912) != 0 ? 0.0d : d6, (i6 & BasicMeasure.EXACTLY) != 0 ? null : str23, (i6 & Integer.MIN_VALUE) != 0 ? null : str24, (i7 & 1) != 0 ? null : str25, (i7 & 2) != 0 ? null : str26, (i7 & 4) != 0 ? null : str27, (i7 & 8) != 0 ? null : str28, (i7 & 16) != 0 ? 0 : i5, (i7 & 32) != 0 ? null : str29, (i7 & 64) != 0 ? null : str30, (i7 & 128) != 0 ? null : str31, (i7 & 256) != 0 ? null : str32, (i7 & 512) != 0 ? null : str33, (i7 & 1024) != 0 ? null : str34, (i7 & 2048) != 0 ? null : str35, (i7 & 4096) != 0 ? null : str36, (i7 & 8192) != 0 ? null : str37, (i7 & 16384) != 0 ? null : str38, (i7 & 32768) != 0 ? null : str39);
    }
    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String str) {
        this.invoiceNo = str;
    }

    public String getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(String str) {
        this.invoiceDate = str;
    }

    public String getDocDate() {
        return this.docDate;
    }

    public void setDocDate(String str) {
        this.docDate = str;
    }

    public int getClientRef() {
        return this.clientRef;
    }

    public void setClientRef(int i2) {
        this.clientRef = i2;
    }

    public double getTrRate() {
        return this.trRate;
    }

    public void setTrRate(double d2) {
        this.trRate = d2;
    }

    public int getTrCurr() {
        return this.trCurr;
    }

    public void setTrCurr(int i2) {
        this.trCurr = i2;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(String str) {
        this.clientCode = str;
    }

    public String getClientDef() {
        return this.clientDef;
    }

    public void setClientDef(String str) {
        this.clientDef = str;
    }

    public String getClientAddr1() {
        return this.clientAddr1;
    }

    public void setClientAddr1(String str) {
        this.clientAddr1 = str;
    }

    public String getClientAddr2() {
        return this.clientAddr2;
    }

    public void setClientAddr2(String str) {
        this.clientAddr2 = str;
    }

    public String getClientCity() {
        return this.clientCity;
    }

    public void setClientCity(String str) {
        this.clientCity = str;
    }

    public String getClientDistrict() {
        return this.clientDistrict;
    }

    public void setClientDistrict(String str) {
        this.clientDistrict = str;
    }

    public String getClientPhone1() {
        return this.clientPhone1;
    }

    public void setClientPhone1(String str) {
        this.clientPhone1 = str;
    }

    public String getClientPhone2() {
        return this.clientPhone2;
    }

    public void setClientPhone2(String str) {
        this.clientPhone2 = str;
    }

    public String getClientFax() {
        return this.clientFax;
    }

    public void setClientFax(String str) {
        this.clientFax = str;
    }

    public String getClientTaxNr() {
        return this.clientTaxNr;
    }

    public void setClientTaxNr(String str) {
        this.clientTaxNr = str;
    }

    public String getClientTaxOfficeCode() {
        return this.clientTaxOfficeCode;
    }

    public void setClientTaxOfficeCode(String str) {
        this.clientTaxOfficeCode = str;
    }

    public String getClientTown() {
        return this.clientTown;
    }

    public void setClientTown(String str) {
        this.clientTown = str;
    }

    public String getClientTaxOffice() {
        return this.clientTaxOffice;
    }

    public void setClientTaxOffice(String str) {
        this.clientTaxOffice = str;
    }

    public String getClientInCharge() {
        return this.clientInCharge;
    }

    public void setClientInCharge(String str) {
        this.clientInCharge = str;
    }

    public String getClientEmailAddr() {
        return this.clientEmailAddr;
    }

    public void setClientEmailAddr(String str) {
        this.clientEmailAddr = str;
    }

    public String getClientIdentityNr() {
        return this.clientIdentityNr;
    }

    public void setClientIdentityNr(String str) {
        this.clientIdentityNr = str;
    }

    public String getClientWebAddr() {
        return this.clientWebAddr;
    }

    public void setClientWebAddr(String str) {
        this.clientWebAddr = str;
    }

    public String getClientPostCode() {
        return this.clientPostCode;
    }

    public void setClientPostCode(String str) {
        this.clientPostCode = str;
    }

    public double getTotalVat() {
        return this.totalVat;
    }

    public void setTotalVat(double d2) {
        this.totalVat = d2;
    }

    public double getNetTotal() {
        return this.netTotal;
    }

    public void setNetTotal(double d2) {
        this.netTotal = d2;
    }

    public double getTotalDiscount() {
        return this.totalDiscount;
    }

    public void setTotalDiscount(double d2) {
        this.totalDiscount = d2;
    }

    public double getGrossTotal() {
        return this.grossTotal;
    }

    public void setGrossTotal(double d2) {
        this.grossTotal = d2;
    }

    public String getGenExp1() {
        return this.genExp1;
    }

    public void setGenExp1(String str) {
        this.genExp1 = str;
    }

    public String getGenExp2() {
        return this.genExp2;
    }

    public void setGenExp2(String str) {
        this.genExp2 = str;
    }

    public String getGenExp3() {
        return this.genExp3;
    }

    public void setGenExp3(String str) {
        this.genExp3 = str;
    }

    public String getGenExp4() {
        return this.genExp4;
    }

    public void setGenExp4(String str) {
        this.genExp4 = str;
    }

    public String getShipTypeCode() {
        return this.shipTypeCode;
    }

    public void setShipTypeCode(String str) {
        this.shipTypeCode = str;
    }

    public String getShipAgentCode() {
        return this.shipAgentCode;
    }

    public void setShipAgentCode(String str) {
        this.shipAgentCode = str;
    }

    public int getSourceIndex() {
        return this.sourceIndex;
    }

    public void setSourceIndex(int i2) {
        this.sourceIndex = i2;
    }

    public String getDespatchNo() {
        return this.despatchNo;
    }

    public void setDespatchNo(String str) {
        this.despatchNo = str;
    }

    public String getDespatchDate() {
        return this.despatchDate;
    }

    public void setDespatchDate(String str) {
        this.despatchDate = str;
    }

    public String getShipAgentTitle() {
        return this.shipAgentTitle;
    }

    public void setShipAgentTitle(String str) {
        this.shipAgentTitle = str;
    }

    public String getShipAgentTaxNr() {
        return this.shipAgentTaxNr;
    }

    public void setShipAgentTaxNr(String str) {
        this.shipAgentTaxNr = str;
    }

    public String getShipAddressTitle() {
        return this.shipAddressTitle;
    }

    public void setShipAddressTitle(String str) {
        this.shipAddressTitle = str;
    }

    public String getShipAddressTaxNr() {
        return this.shipAddressTaxNr;
    }

    public void setShipAddressTaxNr(String str) {
        this.shipAddressTaxNr = str;
    }

    public String getPlateNum1() {
        return this.plateNum1;
    }

    public void setPlateNum1(String str) {
        this.plateNum1 = str;
    }

    public String getChassisNum1() {
        return this.chassisNum1;
    }

    public void setChassisNum1(String str) {
        this.chassisNum1 = str;
    }

    public String getDriverName1() {
        return this.driverName1;
    }

    public void setDriverName1(String str) {
        this.driverName1 = str;
    }

    public String getDriverSurname1() {
        return this.driverSurname1;
    }

    public void setDriverSurname1(String str) {
        this.driverSurname1 = str;
    }

    public String getDriverTckNo1() {
        return this.driverTckNo1;
    }

    public void setDriverTckNo1(String str) {
        this.driverTckNo1 = str;
    }
}