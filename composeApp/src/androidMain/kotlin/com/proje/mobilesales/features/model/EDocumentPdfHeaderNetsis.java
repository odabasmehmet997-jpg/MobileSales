package com.proje.mobilesales.features.model;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: EDocumentPdfHeaderNetsis.kt */
@Tables

public final class EDocumentPdfHeaderNetsis {

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "BAKIYE")
    private String bakiye;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "ADDR1")
    private String clientAddr1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "CITY")
    private String clientCity;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "CCODE")
    private String clientCode;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "CDEFINITION")
    private String clientDef;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "EMAILADDR")
    private String clientEmailAddr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FAXNR")
    private String clientFax;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TELNRS1")
    private String clientPhone1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TAXNR")
    private String clientTaxNr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TAXOFFICE")
    private String clientTaxOffice;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TOWN")
    private String clientTown;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "WEBADDR")
    private String clientWebAddr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GEN_ISK1T")
    private String disc1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GEN_ISK2T")
    private String disc2;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GEN_ISK3T")
    private String disc3;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "DOCDATE")
    private String docDate;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "DPERSON1FIRSTNAME")
    private final String driverName1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "DPERSON1FAMILYNAME")
    private final String driverSurname1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "DPERSON1NID")
    private final String driverTckNo1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FICHENO")
    private String ficheNo;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMADDR1")
    private String firmAddr1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMCITY")
    private String firmCity;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMEMAILADDR")
    private String firmEmailAddr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMFAXNR")
    private String firmFax;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMTAXNR")
    private String firmTaxNr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMTAXOFFICE")
    private String firmTaxOffice;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMTITLE")
    private String firmTitle;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMTOWN")
    private String firmTown;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMWEBADDR")
    private String firmWebAddr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "FIRMTELNRS1")
    private String firmtPhone1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GENEXP1")
    private final String genExp1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GROSSTOTAL")
    private double grossTotal;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "GUID")
    private String guid;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "NETTOTAL")
    private double netTotal;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "LICENSEPLATEID")
    private String plateNum1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "SHIPADDRESSTAXNR")
    private String shipAddressTaxNr;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "SHIPADDRESSTITLE")
    private String shipAddressTitle;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "DORSEPLAKA1")
    private final String shipDorsePlaka1;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TOTALDISCOUNTS")
    private double totalDiscount;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "TOTALVAT")
    private double totalVat;

    public EDocumentPdfHeaderNetsis() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 255, null);
    }

    public String component1() {
        return guid;
    }

    public String component10() {
        return clientTown;
    }

    public String component11() {
        return clientTaxOffice;
    }

    public String component12() {
        return clientEmailAddr;
    }

    public String component13() {
        return clientWebAddr;
    }

    public double component14() {
        return totalVat;
    }

    public double component15() {
        return netTotal;
    }

    public double component16() {
        return totalDiscount;
    }

    public double component17() {
        return grossTotal;
    }

    public String component18() {
        return disc1;
    }

    public String component19() {
        return disc2;
    }

    public String component2() {
        return ficheNo;
    }

    public String component20() {
        return disc3;
    }

    public String component21() {
        return docDate;
    }

    public String component22() {
        return shipAddressTitle;
    }

    public String component23() {
        return shipAddressTaxNr;
    }

    public String component24() {
        return bakiye;
    }

    public String component25() {
        return firmTitle;
    }

    public String component26() {
        return firmAddr1;
    }

    public String component27() {
        return firmCity;
    }

    public String component28() {
        return firmtPhone1;
    }

    public String component29() {
        return firmFax;
    }

    public String component3() {
        return clientCode;
    }

    public String component30() {
        return firmTaxNr;
    }

    public String component31() {
        return firmTown;
    }

    public String component32() {
        return firmTaxOffice;
    }

    public String component33() {
        return firmEmailAddr;
    }

    public String component34() {
        return firmWebAddr;
    }

    public String component35() {
        return plateNum1;
    }

    public String component36() {
        return shipDorsePlaka1;
    }

    public String component37() {
        return driverName1;
    }

    public String component38() {
        return driverSurname1;
    }

    public String component39() {
        return driverTckNo1;
    }

    public String component4() {
        return clientDef;
    }

    public String component40() {
        return genExp1;
    }

    public String component5() {
        return clientAddr1;
    }

    public String component6() {
        return clientCity;
    }

    public String component7() {
        return clientPhone1;
    }

    public String component8() {
        return clientFax;
    }

    public String component9() {
        return clientTaxNr;
    }

    public EDocumentPdfHeaderNetsis copy(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final double d2, final double d3, final double d4, final double d5, final String str14, final String str15, final String str16, final String str17, final String str18, final String str19, final String str20, final String str21, final String str22, final String str23, final String str24, final String str25, final String str26, final String str27, final String str28, final String str29, final String str30, final String str31, final String str32, final String str33, final String str34, final String str35, final String str36) {
        return new EDocumentPdfHeaderNetsis(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, d2, d3, d4, d5, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32, str33, str34, str35, str36);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EDocumentPdfHeaderNetsis eDocumentPdfHeaderNetsis)) {
            return false;
        }
        return Intrinsics.areEqual(guid, eDocumentPdfHeaderNetsis.guid) && Intrinsics.areEqual(ficheNo, eDocumentPdfHeaderNetsis.ficheNo) && Intrinsics.areEqual(clientCode, eDocumentPdfHeaderNetsis.clientCode) && Intrinsics.areEqual(clientDef, eDocumentPdfHeaderNetsis.clientDef) && Intrinsics.areEqual(clientAddr1, eDocumentPdfHeaderNetsis.clientAddr1) && Intrinsics.areEqual(clientCity, eDocumentPdfHeaderNetsis.clientCity) && Intrinsics.areEqual(clientPhone1, eDocumentPdfHeaderNetsis.clientPhone1) && Intrinsics.areEqual(clientFax, eDocumentPdfHeaderNetsis.clientFax) && Intrinsics.areEqual(clientTaxNr, eDocumentPdfHeaderNetsis.clientTaxNr) && Intrinsics.areEqual(clientTown, eDocumentPdfHeaderNetsis.clientTown) && Intrinsics.areEqual(clientTaxOffice, eDocumentPdfHeaderNetsis.clientTaxOffice) && Intrinsics.areEqual(clientEmailAddr, eDocumentPdfHeaderNetsis.clientEmailAddr) && Intrinsics.areEqual(clientWebAddr, eDocumentPdfHeaderNetsis.clientWebAddr) && 0 == Double.compare(this.totalVat, eDocumentPdfHeaderNetsis.totalVat) && 0 == Double.compare(this.netTotal, eDocumentPdfHeaderNetsis.netTotal) && 0 == Double.compare(this.totalDiscount, eDocumentPdfHeaderNetsis.totalDiscount) && 0 == Double.compare(this.grossTotal, eDocumentPdfHeaderNetsis.grossTotal) && Intrinsics.areEqual(disc1, eDocumentPdfHeaderNetsis.disc1) && Intrinsics.areEqual(disc2, eDocumentPdfHeaderNetsis.disc2) && Intrinsics.areEqual(disc3, eDocumentPdfHeaderNetsis.disc3) && Intrinsics.areEqual(docDate, eDocumentPdfHeaderNetsis.docDate) && Intrinsics.areEqual(shipAddressTitle, eDocumentPdfHeaderNetsis.shipAddressTitle) && Intrinsics.areEqual(shipAddressTaxNr, eDocumentPdfHeaderNetsis.shipAddressTaxNr) && Intrinsics.areEqual(bakiye, eDocumentPdfHeaderNetsis.bakiye) && Intrinsics.areEqual(firmTitle, eDocumentPdfHeaderNetsis.firmTitle) && Intrinsics.areEqual(firmAddr1, eDocumentPdfHeaderNetsis.firmAddr1) && Intrinsics.areEqual(firmCity, eDocumentPdfHeaderNetsis.firmCity) && Intrinsics.areEqual(firmtPhone1, eDocumentPdfHeaderNetsis.firmtPhone1) && Intrinsics.areEqual(firmFax, eDocumentPdfHeaderNetsis.firmFax) && Intrinsics.areEqual(firmTaxNr, eDocumentPdfHeaderNetsis.firmTaxNr) && Intrinsics.areEqual(firmTown, eDocumentPdfHeaderNetsis.firmTown) && Intrinsics.areEqual(firmTaxOffice, eDocumentPdfHeaderNetsis.firmTaxOffice) && Intrinsics.areEqual(firmEmailAddr, eDocumentPdfHeaderNetsis.firmEmailAddr) && Intrinsics.areEqual(firmWebAddr, eDocumentPdfHeaderNetsis.firmWebAddr) && Intrinsics.areEqual(plateNum1, eDocumentPdfHeaderNetsis.plateNum1) && Intrinsics.areEqual(shipDorsePlaka1, eDocumentPdfHeaderNetsis.shipDorsePlaka1) && Intrinsics.areEqual(driverName1, eDocumentPdfHeaderNetsis.driverName1) && Intrinsics.areEqual(driverSurname1, eDocumentPdfHeaderNetsis.driverSurname1) && Intrinsics.areEqual(driverTckNo1, eDocumentPdfHeaderNetsis.driverTckNo1) && Intrinsics.areEqual(genExp1, eDocumentPdfHeaderNetsis.genExp1);
    }

    public int hashCode() {
        final String str = guid;
        final int hashCode = (null == str ? 0 : str.hashCode()) * 31;
        final String str2 = ficheNo;
        final int hashCode2 = (hashCode + (null == str2 ? 0 : str2.hashCode())) * 31;
        final String str3 = clientCode;
        final int hashCode3 = (hashCode2 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = clientDef;
        final int hashCode4 = (hashCode3 + (null == str4 ? 0 : str4.hashCode())) * 31;
        final String str5 = clientAddr1;
        final int hashCode5 = (hashCode4 + (null == str5 ? 0 : str5.hashCode())) * 31;
        final String str6 = clientCity;
        final int hashCode6 = (hashCode5 + (null == str6 ? 0 : str6.hashCode())) * 31;
        final String str7 = clientPhone1;
        final int hashCode7 = (hashCode6 + (null == str7 ? 0 : str7.hashCode())) * 31;
        final String str8 = clientFax;
        final int hashCode8 = (hashCode7 + (null == str8 ? 0 : str8.hashCode())) * 31;
        final String str9 = clientTaxNr;
        final int hashCode9 = (hashCode8 + (null == str9 ? 0 : str9.hashCode())) * 31;
        final String str10 = clientTown;
        final int hashCode10 = (hashCode9 + (null == str10 ? 0 : str10.hashCode())) * 31;
        final String str11 = clientTaxOffice;
        final int hashCode11 = (hashCode10 + (null == str11 ? 0 : str11.hashCode())) * 31;
        final String str12 = clientEmailAddr;
        final int hashCode12 = (hashCode11 + (null == str12 ? 0 : str12.hashCode())) * 31;
        final String str13 = clientWebAddr;
        final int hashCode13 = (((((((((hashCode12 + (null == str13 ? 0 : str13.hashCode())) * 31) + Double.hashCode(totalVat)) * 31) + Double.hashCode(netTotal)) * 31) + Double.hashCode(totalDiscount)) * 31) + Double.hashCode(grossTotal)) * 31;
        final String str14 = disc1;
        final int hashCode14 = (hashCode13 + (null == str14 ? 0 : str14.hashCode())) * 31;
        final String str15 = disc2;
        final int hashCode15 = (hashCode14 + (null == str15 ? 0 : str15.hashCode())) * 31;
        final String str16 = disc3;
        final int hashCode16 = (hashCode15 + (null == str16 ? 0 : str16.hashCode())) * 31;
        final String str17 = docDate;
        final int hashCode17 = (hashCode16 + (null == str17 ? 0 : str17.hashCode())) * 31;
        final String str18 = shipAddressTitle;
        final int hashCode18 = (hashCode17 + (null == str18 ? 0 : str18.hashCode())) * 31;
        final String str19 = shipAddressTaxNr;
        final int hashCode19 = (hashCode18 + (null == str19 ? 0 : str19.hashCode())) * 31;
        final String str20 = bakiye;
        final int hashCode20 = (hashCode19 + (null == str20 ? 0 : str20.hashCode())) * 31;
        final String str21 = firmTitle;
        final int hashCode21 = (hashCode20 + (null == str21 ? 0 : str21.hashCode())) * 31;
        final String str22 = firmAddr1;
        final int hashCode22 = (hashCode21 + (null == str22 ? 0 : str22.hashCode())) * 31;
        final String str23 = firmCity;
        final int hashCode23 = (hashCode22 + (null == str23 ? 0 : str23.hashCode())) * 31;
        final String str24 = firmtPhone1;
        final int hashCode24 = (hashCode23 + (null == str24 ? 0 : str24.hashCode())) * 31;
        final String str25 = firmFax;
        final int hashCode25 = (hashCode24 + (null == str25 ? 0 : str25.hashCode())) * 31;
        final String str26 = firmTaxNr;
        final int hashCode26 = (hashCode25 + (null == str26 ? 0 : str26.hashCode())) * 31;
        final String str27 = firmTown;
        final int hashCode27 = (hashCode26 + (null == str27 ? 0 : str27.hashCode())) * 31;
        final String str28 = firmTaxOffice;
        final int hashCode28 = (hashCode27 + (null == str28 ? 0 : str28.hashCode())) * 31;
        final String str29 = firmEmailAddr;
        final int hashCode29 = (hashCode28 + (null == str29 ? 0 : str29.hashCode())) * 31;
        final String str30 = firmWebAddr;
        final int hashCode30 = (hashCode29 + (null == str30 ? 0 : str30.hashCode())) * 31;
        final String str31 = plateNum1;
        final int hashCode31 = (hashCode30 + (null == str31 ? 0 : str31.hashCode())) * 31;
        final String str32 = shipDorsePlaka1;
        final int hashCode32 = (hashCode31 + (null == str32 ? 0 : str32.hashCode())) * 31;
        final String str33 = driverName1;
        final int hashCode33 = (hashCode32 + (null == str33 ? 0 : str33.hashCode())) * 31;
        final String str34 = driverSurname1;
        final int hashCode34 = (hashCode33 + (null == str34 ? 0 : str34.hashCode())) * 31;
        final String str35 = driverTckNo1;
        final int hashCode35 = (hashCode34 + (null == str35 ? 0 : str35.hashCode())) * 31;
        final String str36 = genExp1;
        return hashCode35 + (null != str36 ? str36.hashCode() : 0);
    }

    public String toString() {
        return "EDocumentPdfHeaderNetsis(guid=" + guid + ", ficheNo=" + ficheNo + ", clientCode=" + clientCode + ", clientDef=" + clientDef + ", clientAddr1=" + clientAddr1 + ", clientCity=" + clientCity + ", clientPhone1=" + clientPhone1 + ", clientFax=" + clientFax + ", clientTaxNr=" + clientTaxNr + ", clientTown=" + clientTown + ", clientTaxOffice=" + clientTaxOffice + ", clientEmailAddr=" + clientEmailAddr + ", clientWebAddr=" + clientWebAddr + ", totalVat=" + totalVat + ", netTotal=" + netTotal + ", totalDiscount=" + totalDiscount + ", grossTotal=" + grossTotal + ", disc1=" + disc1 + ", disc2=" + disc2 + ", disc3=" + disc3 + ", docDate=" + docDate + ", shipAddressTitle=" + shipAddressTitle + ", shipAddressTaxNr=" + shipAddressTaxNr + ", bakiye=" + bakiye + ", firmTitle=" + firmTitle + ", firmAddr1=" + firmAddr1 + ", firmCity=" + firmCity + ", firmtPhone1=" + firmtPhone1 + ", firmFax=" + firmFax + ", firmTaxNr=" + firmTaxNr + ", firmTown=" + firmTown + ", firmTaxOffice=" + firmTaxOffice + ", firmEmailAddr=" + firmEmailAddr + ", firmWebAddr=" + firmWebAddr + ", plateNum1=" + plateNum1 + ", shipDorsePlaka1=" + shipDorsePlaka1 + ", driverName1=" + driverName1 + ", driverSurname1=" + driverSurname1 + ", driverTckNo1=" + driverTckNo1 + ", genExp1=" + genExp1 + ')';
    }

    public EDocumentPdfHeaderNetsis(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final double d2, final double d3, final double d4, final double d5, final String str14, final String str15, final String str16, final String str17, final String str18, final String str19, final String str20, final String str21, final String str22, final String str23, final String str24, final String str25, final String str26, final String str27, final String str28, final String str29, final String str30, final String str31, final String str32, final String str33, final String str34, final String str35, final String str36) {
        guid = str;
        ficheNo = str2;
        clientCode = str3;
        clientDef = str4;
        clientAddr1 = str5;
        clientCity = str6;
        clientPhone1 = str7;
        clientFax = str8;
        clientTaxNr = str9;
        clientTown = str10;
        clientTaxOffice = str11;
        clientEmailAddr = str12;
        clientWebAddr = str13;
        totalVat = d2;
        netTotal = d3;
        totalDiscount = d4;
        grossTotal = d5;
        disc1 = str14;
        disc2 = str15;
        disc3 = str16;
        docDate = str17;
        shipAddressTitle = str18;
        shipAddressTaxNr = str19;
        bakiye = str20;
        firmTitle = str21;
        firmAddr1 = str22;
        firmCity = str23;
        firmtPhone1 = str24;
        firmFax = str25;
        firmTaxNr = str26;
        firmTown = str27;
        firmTaxOffice = str28;
        firmEmailAddr = str29;
        firmWebAddr = str30;
        plateNum1 = str31;
        shipDorsePlaka1 = str32;
        driverName1 = str33;
        driverSurname1 = str34;
        driverTckNo1 = str35;
        genExp1 = str36;
    }

    public EDocumentPdfHeaderNetsis(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11, final String str12, final String str13, final double d2, final double d3, final double d4, final double d5, final String str14, final String str15, final String str16, final String str17, final String str18, final String str19, final String str20, final String str21, final String str22, final String str23, final String str24, final String str25, final String str26, final String str27, final String str28, final String str29, final String str30, final String str31, final String str32, final String str33, final String str34, final String str35, final String str36, final int i2, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? null : str, 0 != (i2 & 2) ? null : str2, 0 != (i2 & 4) ? null : str3, 0 != (i2 & 8) ? null : str4, 0 != (i2 & 16) ? null : str5, 0 != (i2 & 32) ? null : str6, 0 != (i2 & 64) ? null : str7, 0 != (i2 & 128) ? null : str8, 0 != (i2 & 256) ? null : str9, 0 != (i2 & 512) ? null : str10, 0 != (i2 & 1024) ? null : str11, 0 != (i2 & 2048) ? null : str12, 0 != (i2 & 4096) ? null : str13, 0 != (i2 & 8192) ? 0.0d : d2, 0 != (i2 & 16384) ? 0.0d : d3, 0 != (32768 & i2) ? 0.0d : d4, 0 == (65536 & i2) ? d5 : 0.0d, 0 != (131072 & i2) ? null : str14, 0 != (i2 & 262144) ? null : str15, 0 != (i2 & 524288) ? null : str16, 0 != (i2 & 1048576) ? null : str17, 0 != (i2 & 2097152) ? null : str18, 0 != (i2 & 4194304) ? null : str19, 0 != (i2 & 8388608) ? null : str20, 0 != (i2 & 16777216) ? null : str21, 0 != (i2 & 33554432) ? null : str22, 0 != (i2 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str23, 0 != (i2 & 134217728) ? null : str24, 0 != (i2 & 268435456) ? null : str25, 0 != (i2 & 536870912) ? null : str26, 0 != (i2 & BasicMeasure.EXACTLY) ? null : str27, 0 != (i2 & Integer.MIN_VALUE) ? null : str28, 0 != (i3 & 1) ? null : str29, 0 != (i3 & 2) ? null : str30, 0 != (i3 & 4) ? null : str31, 0 != (i3 & 8) ? null : str32, 0 != (i3 & 16) ? null : str33, 0 != (i3 & 32) ? null : str34, 0 != (i3 & 64) ? null : str35, 0 != (i3 & 128) ? null : str36);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(final String str) {
        guid = str;
    }

    public String getFicheNo() {
        return ficheNo;
    }

    public void setFicheNo(final String str) {
        ficheNo = str;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(final String str) {
        clientCode = str;
    }

    public String getClientDef() {
        return clientDef;
    }

    public void setClientDef(final String str) {
        clientDef = str;
    }

    public String getClientAddr1() {
        return clientAddr1;
    }

    public void setClientAddr1(final String str) {
        clientAddr1 = str;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(final String str) {
        clientCity = str;
    }

    public String getClientPhone1() {
        return clientPhone1;
    }

    public void setClientPhone1(final String str) {
        clientPhone1 = str;
    }

    public String getClientFax() {
        return clientFax;
    }

    public void setClientFax(final String str) {
        clientFax = str;
    }

    public String getClientTaxNr() {
        return clientTaxNr;
    }

    public void setClientTaxNr(final String str) {
        clientTaxNr = str;
    }

    public String getClientTown() {
        return clientTown;
    }

    public void setClientTown(final String str) {
        clientTown = str;
    }

    public String getClientTaxOffice() {
        return clientTaxOffice;
    }

    public void setClientTaxOffice(final String str) {
        clientTaxOffice = str;
    }

    public String getClientEmailAddr() {
        return clientEmailAddr;
    }

    public void setClientEmailAddr(final String str) {
        clientEmailAddr = str;
    }

    public String getClientWebAddr() {
        return clientWebAddr;
    }

    public void setClientWebAddr(final String str) {
        clientWebAddr = str;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(final double d2) {
        totalVat = d2;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(final double d2) {
        netTotal = d2;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(final double d2) {
        totalDiscount = d2;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(final double d2) {
        grossTotal = d2;
    }

    public String getDisc1() {
        return disc1;
    }

    public void setDisc1(final String str) {
        disc1 = str;
    }

    public String getDisc2() {
        return disc2;
    }

    public void setDisc2(final String str) {
        disc2 = str;
    }

    public String getDisc3() {
        return disc3;
    }

    public void setDisc3(final String str) {
        disc3 = str;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(final String str) {
        docDate = str;
    }

    public String getShipAddressTitle() {
        return shipAddressTitle;
    }

    public void setShipAddressTitle(final String str) {
        shipAddressTitle = str;
    }

    public String getShipAddressTaxNr() {
        return shipAddressTaxNr;
    }

    public void setShipAddressTaxNr(final String str) {
        shipAddressTaxNr = str;
    }

    public String getBakiye() {
        return bakiye;
    }

    public void setBakiye(final String str) {
        bakiye = str;
    }

    public String getFirmTitle() {
        return firmTitle;
    }

    public void setFirmTitle(final String str) {
        firmTitle = str;
    }

    public String getFirmAddr1() {
        return firmAddr1;
    }

    public void setFirmAddr1(final String str) {
        firmAddr1 = str;
    }

    public String getFirmCity() {
        return firmCity;
    }

    public void setFirmCity(final String str) {
        firmCity = str;
    }

    public String getFirmtPhone1() {
        return firmtPhone1;
    }

    public void setFirmtPhone1(final String str) {
        firmtPhone1 = str;
    }

    public String getFirmFax() {
        return firmFax;
    }

    public void setFirmFax(final String str) {
        firmFax = str;
    }

    public String getFirmTaxNr() {
        return firmTaxNr;
    }

    public void setFirmTaxNr(final String str) {
        firmTaxNr = str;
    }

    public String getFirmTown() {
        return firmTown;
    }

    public void setFirmTown(final String str) {
        firmTown = str;
    }

    public String getFirmTaxOffice() {
        return firmTaxOffice;
    }

    public void setFirmTaxOffice(final String str) {
        firmTaxOffice = str;
    }

    public String getFirmEmailAddr() {
        return firmEmailAddr;
    }

    public void setFirmEmailAddr(final String str) {
        firmEmailAddr = str;
    }

    public String getFirmWebAddr() {
        return firmWebAddr;
    }

    public void setFirmWebAddr(final String str) {
        firmWebAddr = str;
    }

    public String getPlateNum1() {
        return plateNum1;
    }

    public void setPlateNum1(final String str) {
        plateNum1 = str;
    }

    public String getShipDorsePlaka1() {
        return shipDorsePlaka1;
    }

    public String getDriverName1() {
        return driverName1;
    }

    public String getDriverSurname1() {
        return driverSurname1;
    }

    public String getDriverTckNo1() {
        return driverTckNo1;
    }

    public String getGenExp1() {
        return genExp1;
    }
}
