package com.proje.mobilesales.features.customer.model.database;

import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.BinaryType;
import com.proje.mobilesales.core.enums.CustomerRestriction;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ConvertDb;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.io.IOException;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class ClCard extends ConvertBinaries implements ConvertDb<CustomerNew> {
    private double accRiskLimit;
    private int accRiskOver;
    private double accRiskTotal;
    private double accRskBlnced;
    private int acceptDesp;
    private int acceptInv;
    private int active;
    private String addr1;
    private String addr2;
    private double bakiye;
    private double bakiyeFloat;
    private int cCurrency;
    private String cardType;
    private String cityCode;
    private String cityDefinition;
    private byte[] clPhoto;
    private double cmDate;
    private String code;
    private String conditionCode;
    private String countryCode;
    private String countryDefinition;
    private double credit;
    private double creditFloat;
    private double cstcRiskLimit;
    private double cstciroRiskLimit;
    private int cstcsCiroRiskOver;
    private int cstcsRiskOver;
    private double cstcsiroRiskTotal;
    private double cstcsownRiskTotal;
    private double cstcsrirorskBlnced;
    private double cstcsrkBlnced;
    private int currencyCustomer;
    private String customerName;
    private double customerSalesVat;
    private String customerSurname;
    private String cyphcode;
    private double debit;
    private double debitFloat;
    private String definition;
    private String definition2;
    private String deliveryFirm;
    private String deliveryMethod;
    private double despRiskLimit;
    private double despRiskLimitSug;
    private int despRiskOver;
    private int despRiskOverSug;
    private double despRiskTotal;
    private double despRiskTotalSug;
    private double desprskBlnced;
    private double desprskBlncedSug;
    private int discType;
    private double discrate;
    private String district;
    private String dspSendEmailAddr;
    private String dueDate;
    private int dueDateControlInv;
    private int dueDateControlOrd;
    private int dueDateCount;
    private double dueDateLimit;
    private int dueDateTrack;
    private int eInvoiceTyp;
    private String ediNo;
    private String emailAddr;
    private String emailAddr2;
    private String explanation1;
    private String extSendEmailAddr;
    private int fatRiskDavran;
    private String faxNr;
    private String groupCode;
    private String inCharge;
    private String inCharge2;
    private String inCharge3;
    private int insteadOfDesp;
    private String invSendEmailAddr;
    private int irsRiskDavran;
    private int isPerCurr;
    private int isTransfer;
    private int logicalRef;
    private String mainClCode;
    private String muhAccCode;
    private int myCsRiskOver;
    private double myCsRiskTotal;
    private double mycsRiskLimit;
    private double mycsrskBlnced;
    private double ordRiskLimit;
    private double ordRiskLimitSugg;
    private int ordRiskOver;
    private int ordRiskOverSugg;
    private double ordRiskTotal;
    private double ordRiskTotalSugg;
    private String ordSendEmailAddr;
    private double ordrskBlnced;
    private double ordrskBlncedSug;
    private String paymentCode;
    private int paymentRef;
    private int paymentType;
    private int personalCompany;
    private String postCode;
    private String priceGrp;
    private int profileId;
    private int profileIdDesp;
    private String projectCode;
    private String restriction;
    private int riskOver;
    private int routeRef;
    private int salesDistribution;
    private int sendMod;
    private int sevkRiskDavran;
    private int sipRiskDavran;
    private String specode;
    private String specode2;
    private String specode3;
    private String specode4;
    private String specode5;
    private String taxNr;
    private String taxOffCode;
    private String taxOffice;
    private String tckNo;
    private String telCodes1;
    private String telCodes2;
    private String telNrs1;
    private String telNrs2;
    private String townCode;
    private String townDefinition;
    private String tradinggrp;
    private int useCustomerBasedVat;
    private int visitDay;
    private String warrantyCode;
    private String webAddr;
    private int yukRiskDavran;
    public ClCard() {
        this(0, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, 0, null, null, null, null, null, null, null, null, 0, 0, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, null, null, null, null, null, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0d, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, 0, null, 0, null, 0, 0, null, null, null, null, null, 0, 0, null, 0, null, null, 0, 0.0d, null, null, null, 0, -1, -1, -1, -1, 3, null);
    }
    public static ClCard copydefault(ClCard clCard, int i2, String str, String str2, double d2, double d3, double d4, double d5, double d6, String str3, int i3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i4, int i5, String str12, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, double d23, double d24, double d25, double d26, double d27, double d28, double d29, int i6, double d30, double d31, double d32, String str13, String str14, String str15, String str16, String str17, double d33, String str18, String str19, String str20, String str21, String str22, String str23, byte[] bArr, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, int i7, String str36, String str37, int i8, String str38, String str39, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, double d34, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, String str40, String str41, String str42, String str43, int i32, String str44, int i33, String str45, int i34, int i35, String str46, String str47, String str48, String str49, String str50, int i36, int i37, String str51, int i38, String str52, String str53, int i39, double d35, String str54, String str55, String str56, int i40, int i41, int i42, int i43, int i44, int i45, Object obj) {
        int i46 = (i41 & 1) != 0 ? clCard.logicalRef : i2;
        String str57 = (i41 & 2) != 0 ? clCard.code : str;
        String str58 = (i41 & 4) != 0 ? clCard.definition : str2;
        double d36 = (i41 & 8) != 0 ? clCard.accRiskLimit : d2;
        double d37 = (i41 & 16) != 0 ? clCard.debit : d3;
        double d38 = (i41 & 32) != 0 ? clCard.credit : d4;
        double d39 = (i41 & 64) != 0 ? clCard.bakiye : d5;
        double d40 = (i41 & 128) != 0 ? clCard.discrate : d6;
        String str59 = (i41 & 256) != 0 ? clCard.tradinggrp : str3;
        int i47 = (i41 & 512) != 0 ? clCard.paymentRef : i3;
        String str60 = (i41 & 1024) != 0 ? clCard.specode : str4;
        String str61 = (i41 & 2048) != 0 ? clCard.emailAddr : str5;
        String str62 = (i41 & 4096) != 0 ? clCard.emailAddr2 : str6;
        String str63 = (i41 & 8192) != 0 ? clCard.cityDefinition : str7;
        String str64 = (i41 & 16384) != 0 ? clCard.cityCode : str8;
        String str65 = (i41 & 32768) != 0 ? clCard.telNrs1 : str9;
        String str66 = (i41 & 65536) != 0 ? clCard.faxNr : str10;
        String str67 = (i41 & 131072) != 0 ? clCard.addr1 : str11;
        int i48 = (i41 & 262144) != 0 ? clCard.visitDay : i4;
        int i49 = (i41 & 524288) != 0 ? clCard.routeRef : i5;
        String str68 = str59;
        String str69 = (i41 & 1048576) != 0 ? clCard.taxNr : str12;
        double d41 = (i41 & 2097152) != 0 ? clCard.mycsRiskLimit : d7;
        double d42 = (i41 & 4194304) != 0 ? clCard.cstcRiskLimit : d8;
        double d43 = (i41 & 8388608) != 0 ? clCard.cstciroRiskLimit : d9;
        double d44 = (i41 & 16777216) != 0 ? clCard.despRiskLimit : d10;
        double d45 = (i41 & 33554432) != 0 ? clCard.despRiskLimitSug : d11;
        double d46 = (i41 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? clCard.ordRiskLimit : d12;
        double d47 = (i41 & 134217728) != 0 ? clCard.ordRiskLimitSugg : d13;
        double d48 = (i41 & 268435456) != 0 ? clCard.accRskBlnced : d14;
        double d49 = (i41 & 536870912) != 0 ? clCard.mycsrskBlnced : d15;
        double d50 = (i41 & BasicMeasure.EXACTLY) != 0 ? clCard.cstcsrkBlnced : d16;
        double d51 = (i41 & Integer.MIN_VALUE) != 0 ? clCard.cstcsrirorskBlnced : d17;
        double d52 = (i42 & 1) != 0 ? clCard.desprskBlnced : d18;
        double d53 = (i42 & 2) != 0 ? clCard.desprskBlncedSug : d19;
        double d54 = (i42 & 4) != 0 ? clCard.ordrskBlnced : d20;
        double d55 = (i42 & 8) != 0 ? clCard.ordrskBlncedSug : d21;
        double d56 = (i42 & 16) != 0 ? clCard.accRiskTotal : d22;
        double d57 = (i42 & 32) != 0 ? clCard.myCsRiskTotal : d23;
        double d58 = (i42 & 64) != 0 ? clCard.cstcsownRiskTotal : d24;
        double d59 = (i42 & 128) != 0 ? clCard.cstcsiroRiskTotal : d25;
        double d60 = (i42 & 256) != 0 ? clCard.despRiskTotal : d26;
        double d61 = (i42 & 512) != 0 ? clCard.despRiskTotalSug : d27;
        double d62 = (i42 & 1024) != 0 ? clCard.ordRiskTotal : d28;
        double d63 = (i42 & 2048) != 0 ? clCard.ordRiskTotalSugg : d29;
        int i50 = (i42 & 4096) != 0 ? clCard.cCurrency : i6;
        double d64 = d63;
        double d65 = (i42 & 8192) != 0 ? clCard.debitFloat : d30;
        double d66 = (i42 & 16384) != 0 ? clCard.creditFloat : d31;
        double d67 = (i42 & 32768) != 0 ? clCard.bakiyeFloat : d32;
        String str70 = (i42 & 65536) != 0 ? clCard.taxOffCode : str13;
        String str71 = (i42 & 131072) != 0 ? clCard.district : str14;
        String str72 = (i42 & 262144) != 0 ? clCard.inCharge : str15;
        String str73 = (i42 & 524288) != 0 ? clCard.inCharge2 : str16;
        String str74 = (i42 & 1048576) != 0 ? clCard.inCharge3 : str17;
        double d68 = d67;
        double d69 = (i42 & 2097152) != 0 ? clCard.cmDate : d33;
        String str75 = (i42 & 4194304) != 0 ? clCard.taxOffice : str18;
        String str76 = (8388608 & i42) != 0 ? clCard.telNrs2 : str19;
        String str77 = (i42 & 16777216) != 0 ? clCard.addr2 : str20;
        String str78 = (i42 & 33554432) != 0 ? clCard.postCode : str21;
        String str79 = (i42 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? clCard.customerName : str22;
        String str80 = (i42 & 134217728) != 0 ? clCard.customerSurname : str23;
        byte[] bArr2 = (i42 & 268435456) != 0 ? clCard.clPhoto : bArr;
        String str81 = (i42 & 536870912) != 0 ? clCard.townDefinition : str24;
        String str82 = (i42 & BasicMeasure.EXACTLY) != 0 ? clCard.townCode : str25;
        return clCard.copy(i46, str57, str58, d36, d37, d38, d39, d40, str68, i47, str60, str61, str62, str63, str64, str65, str66, str67, i48, i49, str69, d41, d42, d43, d44, d45, d46, d47, d48, d49, d50, d51, d52, d53, d54, d55, d56, d57, d58, d59, d60, d61, d62, d64, i50, d65, d66, d68, str70, str71, str72, str73, str74, d69, str75, str76, str77, str78, str79, str80, bArr2, str81, str82, (i42 & Integer.MIN_VALUE) != 0 ? clCard.specode2 : str26, (i43 & 1) != 0 ? clCard.specode3 : str27, (i43 & 2) != 0 ? clCard.specode4 : str28, (i43 & 4) != 0 ? clCard.specode5 : str29, (i43 & 8) != 0 ? clCard.groupCode : str30, (i43 & 16) != 0 ? clCard.warrantyCode : str31, (i43 & 32) != 0 ? clCard.cyphcode : str32, (i43 & 64) != 0 ? clCard.deliveryMethod : str33, (i43 & 128) != 0 ? clCard.deliveryFirm : str34, (i43 & 256) != 0 ? clCard.ediNo : str35, (i43 & 512) != 0 ? clCard.acceptInv : i7, (i43 & 1024) != 0 ? clCard.definition2 : str36, (i43 & 2048) != 0 ? clCard.tckNo : str37, (i43 & 4096) != 0 ? clCard.personalCompany : i8, (i43 & 8192) != 0 ? clCard.telCodes1 : str38, (i43 & 16384) != 0 ? clCard.cardType : str39, (i43 & 32768) != 0 ? clCard.active : i9, (i43 & 65536) != 0 ? clCard.accRiskOver : i10, (i43 & 131072) != 0 ? clCard.cstcsCiroRiskOver : i11, (i43 & 262144) != 0 ? clCard.cstcsRiskOver : i12, (i43 & 524288) != 0 ? clCard.despRiskOver : i13, (i43 & 1048576) != 0 ? clCard.despRiskOverSug : i14, (i43 & 2097152) != 0 ? clCard.myCsRiskOver : i15, (i43 & 4194304) != 0 ? clCard.ordRiskOver : i16, (i43 & 8388608) != 0 ? clCard.ordRiskOverSugg : i17, (i43 & 16777216) != 0 ? clCard.riskOver : i18, (i43 & 33554432) != 0 ? clCard.discType : i19, (i43 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? clCard.sendMod : i20, (i43 & 134217728) != 0 ? clCard.profileId : i21, (i43 & 268435456) != 0 ? clCard.dueDateLimit : d34, (i43 & 536870912) != 0 ? clCard.dueDateCount : i22, (1073741824 & i43) != 0 ? clCard.dueDateTrack : i23, (i43 & Integer.MIN_VALUE) != 0 ? clCard.dueDateControlInv : i24, (i44 & 1) != 0 ? clCard.dueDateControlOrd : i25, (i44 & 2) != 0 ? clCard.sipRiskDavran : i26, (i44 & 4) != 0 ? clCard.sevkRiskDavran : i27, (i44 & 8) != 0 ? clCard.yukRiskDavran : i28, (i44 & 16) != 0 ? clCard.irsRiskDavran : i29, (i44 & 32) != 0 ? clCard.fatRiskDavran : i30, (i44 & 64) != 0 ? clCard.eInvoiceTyp : i31, (i44 & 128) != 0 ? clCard.ordSendEmailAddr : str40, (i44 & 256) != 0 ? clCard.dspSendEmailAddr : str41, (i44 & 512) != 0 ? clCard.invSendEmailAddr : str42, (i44 & 1024) != 0 ? clCard.extSendEmailAddr : str43, (i44 & 2048) != 0 ? clCard.isTransfer : i32, (i44 & 4096) != 0 ? clCard.muhAccCode : str44, (i44 & 8192) != 0 ? clCard.paymentType : i33, (i44 & 16384) != 0 ? clCard.dueDate : str45, (i44 & 32768) != 0 ? clCard.currencyCustomer : i34, (i44 & 65536) != 0 ? clCard.isPerCurr : i35, (i44 & 131072) != 0 ? clCard.conditionCode : str46, (i44 & 262144) != 0 ? clCard.countryDefinition : str47, (i44 & 524288) != 0 ? clCard.countryCode : str48, (i44 & 1048576) != 0 ? clCard.priceGrp : str49, (i44 & 2097152) != 0 ? clCard.mainClCode : str50, (i44 & 4194304) != 0 ? clCard.acceptDesp : i36, (i44 & 8388608) != 0 ? clCard.profileIdDesp : i37, (i44 & 16777216) != 0 ? clCard.webAddr : str51, (i44 & 33554432) != 0 ? clCard.insteadOfDesp : i38, (i44 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? clCard.telCodes2 : str52, (i44 & 134217728) != 0 ? clCard.paymentCode : str53, (i44 & 268435456) != 0 ? clCard.useCustomerBasedVat : i39, (i44 & 536870912) != 0 ? clCard.customerSalesVat : d35, (i44 & BasicMeasure.EXACTLY) != 0 ? clCard.projectCode : str54, (i44 & Integer.MIN_VALUE) != 0 ? clCard.explanation1 : str55, (i45 & 1) != 0 ? clCard.restriction : str56, (i45 & 2) != 0 ? clCard.salesDistribution : i40);
    }

    public int component1() {
        return this.logicalRef;
    }

    public int component10() {
        return this.paymentRef;
    }

    public int component100() {
        return this.yukRiskDavran;
    }

    public int component101() {
        return this.irsRiskDavran;
    }

    public int component102() {
        return this.fatRiskDavran;
    }

    public int component103() {
        return this.eInvoiceTyp;
    }

    public String component104() {
        return this.ordSendEmailAddr;
    }

    public String component105() {
        return this.dspSendEmailAddr;
    }

    public String component106() {
        return this.invSendEmailAddr;
    }

    public String component107() {
        return this.extSendEmailAddr;
    }

    private int component108() {
        return this.isTransfer;
    }

    public String component109() {
        return this.muhAccCode;
    }

    public String component11() {
        return this.specode;
    }

    public int component110() {
        return this.paymentType;
    }

    public String component111() {
        return this.dueDate;
    }

    public int component112() {
        return this.currencyCustomer;
    }

    public int component113() {
        return this.isPerCurr;
    }

    public String component114() {
        return this.conditionCode;
    }

    public String component115() {
        return this.countryDefinition;
    }

    public String component116() {
        return this.countryCode;
    }

    public String component117() {
        return this.priceGrp;
    }

    public String component118() {
        return this.mainClCode;
    }

    public int component119() {
        return this.acceptDesp;
    }

    public String component12() {
        return this.emailAddr;
    }

    public int component120() {
        return this.profileIdDesp;
    }

    public String component121() {
        return this.webAddr;
    }

    public int component122() {
        return this.insteadOfDesp;
    }

    public String component123() {
        return this.telCodes2;
    }

    public String component124() {
        return this.paymentCode;
    }

    public int component125() {
        return this.useCustomerBasedVat;
    }

    public double component126() {
        return this.customerSalesVat;
    }

    public String component127() {
        return this.projectCode;
    }

    public String component128() {
        return this.explanation1;
    }

    public String component129() {
        return this.restriction;
    }

    public String component13() {
        return this.emailAddr2;
    }

    public int component130() {
        return this.salesDistribution;
    }

    public String component14() {
        return this.cityDefinition;
    }

    public String component15() {
        return this.cityCode;
    }

    public String component16() {
        return this.telNrs1;
    }

    public String component17() {
        return this.faxNr;
    }

    public String component18() {
        return this.addr1;
    }

    public int component19() {
        return this.visitDay;
    }

    public String component2() {
        return this.code;
    }

    public int component20() {
        return this.routeRef;
    }

    public String component21() {
        return this.taxNr;
    }

    public double component22() {
        return this.mycsRiskLimit;
    }

    public double component23() {
        return this.cstcRiskLimit;
    }

    public double component24() {
        return this.cstciroRiskLimit;
    }

    public double component25() {
        return this.despRiskLimit;
    }

    public double component26() {
        return this.despRiskLimitSug;
    }

    public double component27() {
        return this.ordRiskLimit;
    }

    public double component28() {
        return this.ordRiskLimitSugg;
    }

    public double component29() {
        return this.accRskBlnced;
    }

    public String component3() {
        return this.definition;
    }

    public double component30() {
        return this.mycsrskBlnced;
    }

    public double component31() {
        return this.cstcsrkBlnced;
    }

    public double component32() {
        return this.cstcsrirorskBlnced;
    }

    public double component33() {
        return this.desprskBlnced;
    }

    public double component34() {
        return this.desprskBlncedSug;
    }

    public double component35() {
        return this.ordrskBlnced;
    }

    public double component36() {
        return this.ordrskBlncedSug;
    }

    public double component37() {
        return this.accRiskTotal;
    }

    public double component38() {
        return this.myCsRiskTotal;
    }

    public double component39() {
        return this.cstcsownRiskTotal;
    }

    public double component4() {
        return this.accRiskLimit;
    }

    public double component40() {
        return this.cstcsiroRiskTotal;
    }

    public double component41() {
        return this.despRiskTotal;
    }

    public double component42() {
        return this.despRiskTotalSug;
    }

    public double component43() {
        return this.ordRiskTotal;
    }

    public double component44() {
        return this.ordRiskTotalSugg;
    }

    public int component45() {
        return this.cCurrency;
    }

    public double component46() {
        return this.debitFloat;
    }

    public double component47() {
        return this.creditFloat;
    }

    public double component48() {
        return this.bakiyeFloat;
    }

    public String component49() {
        return this.taxOffCode;
    }

    public double component5() {
        return this.debit;
    }

    public String component50() {
        return this.district;
    }

    public String component51() {
        return this.inCharge;
    }

    public String component52() {
        return this.inCharge2;
    }

    public String component53() {
        return this.inCharge3;
    }

    public double component54() {
        return this.cmDate;
    }

    public String component55() {
        return this.taxOffice;
    }

    public String component56() {
        return this.telNrs2;
    }

    public String component57() {
        return this.addr2;
    }

    public String component58() {
        return this.postCode;
    }

    public String component59() {
        return this.customerName;
    }

    public double component6() {
        return this.credit;
    }

    public String component60() {
        return this.customerSurname;
    }

    public byte[] component61() {
        return this.clPhoto;
    }

    public String component62() {
        return this.townDefinition;
    }

    public String component63() {
        return this.townCode;
    }

    public String component64() {
        return this.specode2;
    }

    public String component65() {
        return this.specode3;
    }

    public String component66() {
        return this.specode4;
    }

    public String component67() {
        return this.specode5;
    }

    public String component68() {
        return this.groupCode;
    }

    public String component69() {
        return this.warrantyCode;
    }

    public double component7() {
        return this.bakiye;
    }

    public String component70() {
        return this.cyphcode;
    }

    public String component71() {
        return this.deliveryMethod;
    }

    public String component72() {
        return this.deliveryFirm;
    }

    public String component73() {
        return this.ediNo;
    }

    public int component74() {
        return this.acceptInv;
    }

    public String component75() {
        return this.definition2;
    }

    public String component76() {
        return this.tckNo;
    }

    public int component77() {
        return this.personalCompany;
    }

    public String component78() {
        return this.telCodes1;
    }

    public String component79() {
        return this.cardType;
    }

    public double component8() {
        return this.discrate;
    }

    public int component80() {
        return this.active;
    }

    public int component81() {
        return this.accRiskOver;
    }

    public int component82() {
        return this.cstcsCiroRiskOver;
    }

    public int component83() {
        return this.cstcsRiskOver;
    }

    public int component84() {
        return this.despRiskOver;
    }

    public int component85() {
        return this.despRiskOverSug;
    }

    public int component86() {
        return this.myCsRiskOver;
    }

    public int component87() {
        return this.ordRiskOver;
    }

    public int component88() {
        return this.ordRiskOverSugg;
    }

    public int component89() {
        return this.riskOver;
    }

    public String component9() {
        return this.tradinggrp;
    }

    public int component90() {
        return this.discType;
    }

    public int component91() {
        return this.sendMod;
    }

    public int component92() {
        return this.profileId;
    }

    public double component93() {
        return this.dueDateLimit;
    }

    public int component94() {
        return this.dueDateCount;
    }

    public int component95() {
        return this.dueDateTrack;
    }

    public int component96() {
        return this.dueDateControlInv;
    }

    public int component97() {
        return this.dueDateControlOrd;
    }

    public int component98() {
        return this.sipRiskDavran;
    }
    public int component99() {
        return this.sevkRiskDavran;
    }
    public ClCard copy(int i2, String code, String definition, double d2, double d3, double d4, double d5, double d6, String tradinggrp, int i3, String specode, String emailAddr, String emailAddr2, String cityDefinition, String cityCode, String telNrs1, String faxNr, String addr1, int i4, int i5, String taxNr, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, double d23, double d24, double d25, double d26, double d27, double d28, double d29, int i6, double d30, double d31, double d32, String taxOffCode, String district, String inCharge, String inCharge2, String inCharge3, double d33, String taxOffice, String telNrs2, String addr2, String postCode, String customerName, String customerSurname, byte[] bArr, String townDefinition, String townCode, String specode2, String specode3, String specode4, String specode5, String groupCode, String warrantyCode, String cyphcode, String deliveryMethod, String deliveryFirm, String ediNo, int i7, String definition2, String tckNo, int i8, String telCodes1, String cardType, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, double d34, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, String ordSendEmailAddr, String dspSendEmailAddr, String invSendEmailAddr, String extSendEmailAddr, int i32, String muhAccCode, int i33, String dueDate, int i34, int i35, String conditionCode, String countryDefinition, String countryCode, String priceGrp, String mainClCode, int i36, int i37, String webAddr, int i38, String telCodes2, String paymentCode, int i39, double d35, String projectCode, String explanation1, String restriction, int i40) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(emailAddr, "emailAddr");
        Intrinsics.checkNotNullParameter(emailAddr2, "emailAddr2");
        Intrinsics.checkNotNullParameter(cityDefinition, "cityDefinition");
        Intrinsics.checkNotNullParameter(cityCode, "cityCode");
        Intrinsics.checkNotNullParameter(telNrs1, "telNrs1");
        Intrinsics.checkNotNullParameter(faxNr, "faxNr");
        Intrinsics.checkNotNullParameter(addr1, "addr1");
        Intrinsics.checkNotNullParameter(taxNr, "taxNr");
        Intrinsics.checkNotNullParameter(taxOffCode, "taxOffCode");
        Intrinsics.checkNotNullParameter(district, "district");
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(inCharge2, "inCharge2");
        Intrinsics.checkNotNullParameter(inCharge3, "inCharge3");
        Intrinsics.checkNotNullParameter(taxOffice, "taxOffice");
        Intrinsics.checkNotNullParameter(telNrs2, "telNrs2");
        Intrinsics.checkNotNullParameter(addr2, "addr2");
        Intrinsics.checkNotNullParameter(postCode, "postCode");
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        Intrinsics.checkNotNullParameter(customerSurname, "customerSurname");
        Intrinsics.checkNotNullParameter(townDefinition, "townDefinition");
        Intrinsics.checkNotNullParameter(townCode, "townCode");
        Intrinsics.checkNotNullParameter(specode2, "specode2");
        Intrinsics.checkNotNullParameter(specode3, "specode3");
        Intrinsics.checkNotNullParameter(specode4, "specode4");
        Intrinsics.checkNotNullParameter(specode5, "specode5");
        Intrinsics.checkNotNullParameter(groupCode, "groupCode");
        Intrinsics.checkNotNullParameter(warrantyCode, "warrantyCode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(deliveryMethod, "deliveryMethod");
        Intrinsics.checkNotNullParameter(deliveryFirm, "deliveryFirm");
        Intrinsics.checkNotNullParameter(ediNo, "ediNo");
        Intrinsics.checkNotNullParameter(definition2, "definition2");
        Intrinsics.checkNotNullParameter(tckNo, "tckNo");
        Intrinsics.checkNotNullParameter(telCodes1, "telCodes1");
        Intrinsics.checkNotNullParameter(cardType, "cardType");
        Intrinsics.checkNotNullParameter(ordSendEmailAddr, "ordSendEmailAddr");
        Intrinsics.checkNotNullParameter(dspSendEmailAddr, "dspSendEmailAddr");
        Intrinsics.checkNotNullParameter(invSendEmailAddr, "invSendEmailAddr");
        Intrinsics.checkNotNullParameter(extSendEmailAddr, "extSendEmailAddr");
        Intrinsics.checkNotNullParameter(muhAccCode, "muhAccCode");
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(conditionCode, "conditionCode");
        Intrinsics.checkNotNullParameter(countryDefinition, "countryDefinition");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(priceGrp, "priceGrp");
        Intrinsics.checkNotNullParameter(mainClCode, "mainClCode");
        Intrinsics.checkNotNullParameter(webAddr, "webAddr");
        Intrinsics.checkNotNullParameter(telCodes2, "telCodes2");
        Intrinsics.checkNotNullParameter(paymentCode, "paymentCode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(restriction, "restriction");
        return new ClCard(i2, code, definition, d2, d3, d4, d5, d6, tradinggrp, i3, specode, emailAddr, emailAddr2, cityDefinition, cityCode, telNrs1, faxNr, addr1, i4, i5, taxNr, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27, d28, d29, i6, d30, d31, d32, taxOffCode, district, inCharge, inCharge2, inCharge3, d33, taxOffice, telNrs2, addr2, postCode, customerName, customerSurname, bArr, townDefinition, townCode, specode2, specode3, specode4, specode5, groupCode, warrantyCode, cyphcode, deliveryMethod, deliveryFirm, ediNo, i7, definition2, tckNo, i8, telCodes1, cardType, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, d34, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, ordSendEmailAddr, dspSendEmailAddr, invSendEmailAddr, extSendEmailAddr, i32, muhAccCode, i33, dueDate, i34, i35, conditionCode, countryDefinition, countryCode, priceGrp, mainClCode, i36, i37, webAddr, i38, telCodes2, paymentCode, i39, d35, projectCode, explanation1, restriction, i40);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClCard clCard)) {
            return false;
        }
        return this.logicalRef == clCard.logicalRef && Intrinsics.areEqual(this.code, clCard.code) && Intrinsics.areEqual(this.definition, clCard.definition) && Double.compare(this.accRiskLimit, clCard.accRiskLimit) == 0 && Double.compare(this.debit, clCard.debit) == 0 && Double.compare(this.credit, clCard.credit) == 0 && Double.compare(this.bakiye, clCard.bakiye) == 0 && Double.compare(this.discrate, clCard.discrate) == 0 && Intrinsics.areEqual(this.tradinggrp, clCard.tradinggrp) && this.paymentRef == clCard.paymentRef && Intrinsics.areEqual(this.specode, clCard.specode) && Intrinsics.areEqual(this.emailAddr, clCard.emailAddr) && Intrinsics.areEqual(this.emailAddr2, clCard.emailAddr2) && Intrinsics.areEqual(this.cityDefinition, clCard.cityDefinition) && Intrinsics.areEqual(this.cityCode, clCard.cityCode) && Intrinsics.areEqual(this.telNrs1, clCard.telNrs1) && Intrinsics.areEqual(this.faxNr, clCard.faxNr) && Intrinsics.areEqual(this.addr1, clCard.addr1) && this.visitDay == clCard.visitDay && this.routeRef == clCard.routeRef && Intrinsics.areEqual(this.taxNr, clCard.taxNr) && Double.compare(this.mycsRiskLimit, clCard.mycsRiskLimit) == 0 && Double.compare(this.cstcRiskLimit, clCard.cstcRiskLimit) == 0 && Double.compare(this.cstciroRiskLimit, clCard.cstciroRiskLimit) == 0 && Double.compare(this.despRiskLimit, clCard.despRiskLimit) == 0 && Double.compare(this.despRiskLimitSug, clCard.despRiskLimitSug) == 0 && Double.compare(this.ordRiskLimit, clCard.ordRiskLimit) == 0 && Double.compare(this.ordRiskLimitSugg, clCard.ordRiskLimitSugg) == 0 && Double.compare(this.accRskBlnced, clCard.accRskBlnced) == 0 && Double.compare(this.mycsrskBlnced, clCard.mycsrskBlnced) == 0 && Double.compare(this.cstcsrkBlnced, clCard.cstcsrkBlnced) == 0 && Double.compare(this.cstcsrirorskBlnced, clCard.cstcsrirorskBlnced) == 0 && Double.compare(this.desprskBlnced, clCard.desprskBlnced) == 0 && Double.compare(this.desprskBlncedSug, clCard.desprskBlncedSug) == 0 && Double.compare(this.ordrskBlnced, clCard.ordrskBlnced) == 0 && Double.compare(this.ordrskBlncedSug, clCard.ordrskBlncedSug) == 0 && Double.compare(this.accRiskTotal, clCard.accRiskTotal) == 0 && Double.compare(this.myCsRiskTotal, clCard.myCsRiskTotal) == 0 && Double.compare(this.cstcsownRiskTotal, clCard.cstcsownRiskTotal) == 0 && Double.compare(this.cstcsiroRiskTotal, clCard.cstcsiroRiskTotal) == 0 && Double.compare(this.despRiskTotal, clCard.despRiskTotal) == 0 && Double.compare(this.despRiskTotalSug, clCard.despRiskTotalSug) == 0 && Double.compare(this.ordRiskTotal, clCard.ordRiskTotal) == 0 && Double.compare(this.ordRiskTotalSugg, clCard.ordRiskTotalSugg) == 0 && this.cCurrency == clCard.cCurrency && Double.compare(this.debitFloat, clCard.debitFloat) == 0 && Double.compare(this.creditFloat, clCard.creditFloat) == 0 && Double.compare(this.bakiyeFloat, clCard.bakiyeFloat) == 0 && Intrinsics.areEqual(this.taxOffCode, clCard.taxOffCode) && Intrinsics.areEqual(this.district, clCard.district) && Intrinsics.areEqual(this.inCharge, clCard.inCharge) && Intrinsics.areEqual(this.inCharge2, clCard.inCharge2) && Intrinsics.areEqual(this.inCharge3, clCard.inCharge3) && Double.compare(this.cmDate, clCard.cmDate) == 0 && Intrinsics.areEqual(this.taxOffice, clCard.taxOffice) && Intrinsics.areEqual(this.telNrs2, clCard.telNrs2) && Intrinsics.areEqual(this.addr2, clCard.addr2) && Intrinsics.areEqual(this.postCode, clCard.postCode) && Intrinsics.areEqual(this.customerName, clCard.customerName) && Intrinsics.areEqual(this.customerSurname, clCard.customerSurname) && Intrinsics.areEqual(this.clPhoto, clCard.clPhoto) && Intrinsics.areEqual(this.townDefinition, clCard.townDefinition) && Intrinsics.areEqual(this.townCode, clCard.townCode) && Intrinsics.areEqual(this.specode2, clCard.specode2) && Intrinsics.areEqual(this.specode3, clCard.specode3) && Intrinsics.areEqual(this.specode4, clCard.specode4) && Intrinsics.areEqual(this.specode5, clCard.specode5) && Intrinsics.areEqual(this.groupCode, clCard.groupCode) && Intrinsics.areEqual(this.warrantyCode, clCard.warrantyCode) && Intrinsics.areEqual(this.cyphcode, clCard.cyphcode) && Intrinsics.areEqual(this.deliveryMethod, clCard.deliveryMethod) && Intrinsics.areEqual(this.deliveryFirm, clCard.deliveryFirm) && Intrinsics.areEqual(this.ediNo, clCard.ediNo) && this.acceptInv == clCard.acceptInv && Intrinsics.areEqual(this.definition2, clCard.definition2) && Intrinsics.areEqual(this.tckNo, clCard.tckNo) && this.personalCompany == clCard.personalCompany && Intrinsics.areEqual(this.telCodes1, clCard.telCodes1) && Intrinsics.areEqual(this.cardType, clCard.cardType) && this.active == clCard.active && this.accRiskOver == clCard.accRiskOver && this.cstcsCiroRiskOver == clCard.cstcsCiroRiskOver && this.cstcsRiskOver == clCard.cstcsRiskOver && this.despRiskOver == clCard.despRiskOver && this.despRiskOverSug == clCard.despRiskOverSug && this.myCsRiskOver == clCard.myCsRiskOver && this.ordRiskOver == clCard.ordRiskOver && this.ordRiskOverSugg == clCard.ordRiskOverSugg && this.riskOver == clCard.riskOver && this.discType == clCard.discType && this.sendMod == clCard.sendMod && this.profileId == clCard.profileId && Double.compare(this.dueDateLimit, clCard.dueDateLimit) == 0 && this.dueDateCount == clCard.dueDateCount && this.dueDateTrack == clCard.dueDateTrack && this.dueDateControlInv == clCard.dueDateControlInv && this.dueDateControlOrd == clCard.dueDateControlOrd && this.sipRiskDavran == clCard.sipRiskDavran && this.sevkRiskDavran == clCard.sevkRiskDavran && this.yukRiskDavran == clCard.yukRiskDavran && this.irsRiskDavran == clCard.irsRiskDavran && this.fatRiskDavran == clCard.fatRiskDavran && this.eInvoiceTyp == clCard.eInvoiceTyp && Intrinsics.areEqual(this.ordSendEmailAddr, clCard.ordSendEmailAddr) && Intrinsics.areEqual(this.dspSendEmailAddr, clCard.dspSendEmailAddr) && Intrinsics.areEqual(this.invSendEmailAddr, clCard.invSendEmailAddr) && Intrinsics.areEqual(this.extSendEmailAddr, clCard.extSendEmailAddr) && this.isTransfer == clCard.isTransfer && Intrinsics.areEqual(this.muhAccCode, clCard.muhAccCode) && this.paymentType == clCard.paymentType && Intrinsics.areEqual(this.dueDate, clCard.dueDate) && this.currencyCustomer == clCard.currencyCustomer && this.isPerCurr == clCard.isPerCurr && Intrinsics.areEqual(this.conditionCode, clCard.conditionCode) && Intrinsics.areEqual(this.countryDefinition, clCard.countryDefinition) && Intrinsics.areEqual(this.countryCode, clCard.countryCode) && Intrinsics.areEqual(this.priceGrp, clCard.priceGrp) && Intrinsics.areEqual(this.mainClCode, clCard.mainClCode) && this.acceptDesp == clCard.acceptDesp && this.profileIdDesp == clCard.profileIdDesp && Intrinsics.areEqual(this.webAddr, clCard.webAddr) && this.insteadOfDesp == clCard.insteadOfDesp && Intrinsics.areEqual(this.telCodes2, clCard.telCodes2) && Intrinsics.areEqual(this.paymentCode, clCard.paymentCode) && this.useCustomerBasedVat == clCard.useCustomerBasedVat && Double.compare(this.customerSalesVat, clCard.customerSalesVat) == 0 && Intrinsics.areEqual(this.projectCode, clCard.projectCode) && Intrinsics.areEqual(this.explanation1, clCard.explanation1) && Intrinsics.areEqual(this.restriction, clCard.restriction) && this.salesDistribution == clCard.salesDistribution;
    }
    public int hashCode() {
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.logicalRef) * 31) + this.code.hashCode()) * 31) + this.definition.hashCode()) * 31) + Double.hashCode(this.accRiskLimit)) * 31) + Double.hashCode(this.debit)) * 31) + Double.hashCode(this.credit)) * 31) + Double.hashCode(this.bakiye)) * 31) + Double.hashCode(this.discrate)) * 31) + this.tradinggrp.hashCode()) * 31) + Integer.hashCode(this.paymentRef)) * 31) + this.specode.hashCode()) * 31) + this.emailAddr.hashCode()) * 31) + this.emailAddr2.hashCode()) * 31) + this.cityDefinition.hashCode()) * 31) + this.cityCode.hashCode()) * 31) + this.telNrs1.hashCode()) * 31) + this.faxNr.hashCode()) * 31) + this.addr1.hashCode()) * 31) + Integer.hashCode(this.visitDay)) * 31) + Integer.hashCode(this.routeRef)) * 31) + this.taxNr.hashCode()) * 31) + Double.hashCode(this.mycsRiskLimit)) * 31) + Double.hashCode(this.cstcRiskLimit)) * 31) + Double.hashCode(this.cstciroRiskLimit)) * 31) + Double.hashCode(this.despRiskLimit)) * 31) + Double.hashCode(this.despRiskLimitSug)) * 31) + Double.hashCode(this.ordRiskLimit)) * 31) + Double.hashCode(this.ordRiskLimitSugg)) * 31) + Double.hashCode(this.accRskBlnced)) * 31) + Double.hashCode(this.mycsrskBlnced)) * 31) + Double.hashCode(this.cstcsrkBlnced)) * 31) + Double.hashCode(this.cstcsrirorskBlnced)) * 31) + Double.hashCode(this.desprskBlnced)) * 31) + Double.hashCode(this.desprskBlncedSug)) * 31) + Double.hashCode(this.ordrskBlnced)) * 31) + Double.hashCode(this.ordrskBlncedSug)) * 31) + Double.hashCode(this.accRiskTotal)) * 31) + Double.hashCode(this.myCsRiskTotal)) * 31) + Double.hashCode(this.cstcsownRiskTotal)) * 31) + Double.hashCode(this.cstcsiroRiskTotal)) * 31) + Double.hashCode(this.despRiskTotal)) * 31) + Double.hashCode(this.despRiskTotalSug)) * 31) + Double.hashCode(this.ordRiskTotal)) * 31) + Double.hashCode(this.ordRiskTotalSugg)) * 31) + Integer.hashCode(this.cCurrency)) * 31) + Double.hashCode(this.debitFloat)) * 31) + Double.hashCode(this.creditFloat)) * 31) + Double.hashCode(this.bakiyeFloat)) * 31) + this.taxOffCode.hashCode()) * 31) + this.district.hashCode()) * 31) + this.inCharge.hashCode()) * 31) + this.inCharge2.hashCode()) * 31) + this.inCharge3.hashCode()) * 31) + Double.hashCode(this.cmDate)) * 31) + this.taxOffice.hashCode()) * 31) + this.telNrs2.hashCode()) * 31) + this.addr2.hashCode()) * 31) + this.postCode.hashCode()) * 31) + this.customerName.hashCode()) * 31) + this.customerSurname.hashCode()) * 31;
        byte[] bArr = this.clPhoto;
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((hashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31) + this.townDefinition.hashCode()) * 31) + this.townCode.hashCode()) * 31) + this.specode2.hashCode()) * 31) + this.specode3.hashCode()) * 31) + this.specode4.hashCode()) * 31) + this.specode5.hashCode()) * 31) + this.groupCode.hashCode()) * 31) + this.warrantyCode.hashCode()) * 31) + this.cyphcode.hashCode()) * 31) + this.deliveryMethod.hashCode()) * 31) + this.deliveryFirm.hashCode()) * 31) + this.ediNo.hashCode()) * 31) + Integer.hashCode(this.acceptInv)) * 31) + this.definition2.hashCode()) * 31) + this.tckNo.hashCode()) * 31) + Integer.hashCode(this.personalCompany)) * 31) + this.telCodes1.hashCode()) * 31) + this.cardType.hashCode()) * 31) + Integer.hashCode(this.active)) * 31) + Integer.hashCode(this.accRiskOver)) * 31) + Integer.hashCode(this.cstcsCiroRiskOver)) * 31) + Integer.hashCode(this.cstcsRiskOver)) * 31) + Integer.hashCode(this.despRiskOver)) * 31) + Integer.hashCode(this.despRiskOverSug)) * 31) + Integer.hashCode(this.myCsRiskOver)) * 31) + Integer.hashCode(this.ordRiskOver)) * 31) + Integer.hashCode(this.ordRiskOverSugg)) * 31) + Integer.hashCode(this.riskOver)) * 31) + Integer.hashCode(this.discType)) * 31) + Integer.hashCode(this.sendMod)) * 31) + Integer.hashCode(this.profileId)) * 31) + Double.hashCode(this.dueDateLimit)) * 31) + Integer.hashCode(this.dueDateCount)) * 31) + Integer.hashCode(this.dueDateTrack)) * 31) + Integer.hashCode(this.dueDateControlInv)) * 31) + Integer.hashCode(this.dueDateControlOrd)) * 31) + Integer.hashCode(this.sipRiskDavran)) * 31) + Integer.hashCode(this.sevkRiskDavran)) * 31) + Integer.hashCode(this.yukRiskDavran)) * 31) + Integer.hashCode(this.irsRiskDavran)) * 31) + Integer.hashCode(this.fatRiskDavran)) * 31) + Integer.hashCode(this.eInvoiceTyp)) * 31) + this.ordSendEmailAddr.hashCode()) * 31) + this.dspSendEmailAddr.hashCode()) * 31) + this.invSendEmailAddr.hashCode()) * 31) + this.extSendEmailAddr.hashCode()) * 31) + Integer.hashCode(this.isTransfer)) * 31) + this.muhAccCode.hashCode()) * 31) + Integer.hashCode(this.paymentType)) * 31) + this.dueDate.hashCode()) * 31) + Integer.hashCode(this.currencyCustomer)) * 31) + Integer.hashCode(this.isPerCurr)) * 31) + this.conditionCode.hashCode()) * 31) + this.countryDefinition.hashCode()) * 31) + this.countryCode.hashCode()) * 31) + this.priceGrp.hashCode()) * 31) + this.mainClCode.hashCode()) * 31) + Integer.hashCode(this.acceptDesp)) * 31) + Integer.hashCode(this.profileIdDesp)) * 31) + this.webAddr.hashCode()) * 31) + Integer.hashCode(this.insteadOfDesp)) * 31) + this.telCodes2.hashCode()) * 31) + this.paymentCode.hashCode()) * 31) + Integer.hashCode(this.useCustomerBasedVat)) * 31) + Double.hashCode(this.customerSalesVat)) * 31) + this.projectCode.hashCode()) * 31) + this.explanation1.hashCode()) * 31) + this.restriction.hashCode()) * 31) + Integer.hashCode(this.salesDistribution);
    }
    public String toString() {
        return "ClCard(logicalRef=" + this.logicalRef + ", code=" + this.code + ", definition=" + this.definition + ", accRiskLimit=" + this.accRiskLimit + ", debit=" + this.debit + ", credit=" + this.credit + ", bakiye=" + this.bakiye + ", discrate=" + this.discrate + ", tradinggrp=" + this.tradinggrp + ", paymentRef=" + this.paymentRef + ", specode=" + this.specode + ", emailAddr=" + this.emailAddr + ", emailAddr2=" + this.emailAddr2 + ", cityDefinition=" + this.cityDefinition + ", cityCode=" + this.cityCode + ", telNrs1=" + this.telNrs1 + ", faxNr=" + this.faxNr + ", addr1=" + this.addr1 + ", visitDay=" + this.visitDay + ", routeRef=" + this.routeRef + ", taxNr=" + this.taxNr + ", mycsRiskLimit=" + this.mycsRiskLimit + ", cstcRiskLimit=" + this.cstcRiskLimit + ", cstciroRiskLimit=" + this.cstciroRiskLimit + ", despRiskLimit=" + this.despRiskLimit + ", despRiskLimitSug=" + this.despRiskLimitSug + ", ordRiskLimit=" + this.ordRiskLimit + ", ordRiskLimitSugg=" + this.ordRiskLimitSugg + ", accRskBlnced=" + this.accRskBlnced + ", mycsrskBlnced=" + this.mycsrskBlnced + ", cstcsrkBlnced=" + this.cstcsrkBlnced + ", cstcsrirorskBlnced=" + this.cstcsrirorskBlnced + ", desprskBlnced=" + this.desprskBlnced + ", desprskBlncedSug=" + this.desprskBlncedSug + ", ordrskBlnced=" + this.ordrskBlnced + ", ordrskBlncedSug=" + this.ordrskBlncedSug + ", accRiskTotal=" + this.accRiskTotal + ", myCsRiskTotal=" + this.myCsRiskTotal + ", cstcsownRiskTotal=" + this.cstcsownRiskTotal + ", cstcsiroRiskTotal=" + this.cstcsiroRiskTotal + ", despRiskTotal=" + this.despRiskTotal + ", despRiskTotalSug=" + this.despRiskTotalSug + ", ordRiskTotal=" + this.ordRiskTotal + ", ordRiskTotalSugg=" + this.ordRiskTotalSugg + ", cCurrency=" + this.cCurrency + ", debitFloat=" + this.debitFloat + ", creditFloat=" + this.creditFloat + ", bakiyeFloat=" + this.bakiyeFloat + ", taxOffCode=" + this.taxOffCode + ", district=" + this.district + ", inCharge=" + this.inCharge + ", inCharge2=" + this.inCharge2 + ", inCharge3=" + this.inCharge3 + ", cmDate=" + this.cmDate + ", taxOffice=" + this.taxOffice + ", telNrs2=" + this.telNrs2 + ", addr2=" + this.addr2 + ", postCode=" + this.postCode + ", customerName=" + this.customerName + ", customerSurname=" + this.customerSurname + ", clPhoto=" + Arrays.toString(this.clPhoto) + ", townDefinition=" + this.townDefinition + ", townCode=" + this.townCode + ", specode2=" + this.specode2 + ", specode3=" + this.specode3 + ", specode4=" + this.specode4 + ", specode5=" + this.specode5 + ", groupCode=" + this.groupCode + ", warrantyCode=" + this.warrantyCode + ", cyphcode=" + this.cyphcode + ", deliveryMethod=" + this.deliveryMethod + ", deliveryFirm=" + this.deliveryFirm + ", ediNo=" + this.ediNo + ", acceptInv=" + this.acceptInv + ", definition2=" + this.definition2 + ", tckNo=" + this.tckNo + ", personalCompany=" + this.personalCompany + ", telCodes1=" + this.telCodes1 + ", cardType=" + this.cardType + ", active=" + this.active + ", accRiskOver=" + this.accRiskOver + ", cstcsCiroRiskOver=" + this.cstcsCiroRiskOver + ", cstcsRiskOver=" + this.cstcsRiskOver + ", despRiskOver=" + this.despRiskOver + ", despRiskOverSug=" + this.despRiskOverSug + ", myCsRiskOver=" + this.myCsRiskOver + ", ordRiskOver=" + this.ordRiskOver + ", ordRiskOverSugg=" + this.ordRiskOverSugg + ", riskOver=" + this.riskOver + ", discType=" + this.discType + ", sendMod=" + this.sendMod + ", profileId=" + this.profileId + ", dueDateLimit=" + this.dueDateLimit + ", dueDateCount=" + this.dueDateCount + ", dueDateTrack=" + this.dueDateTrack + ", dueDateControlInv=" + this.dueDateControlInv + ", dueDateControlOrd=" + this.dueDateControlOrd + ", sipRiskDavran=" + this.sipRiskDavran + ", sevkRiskDavran=" + this.sevkRiskDavran + ", yukRiskDavran=" + this.yukRiskDavran + ", irsRiskDavran=" + this.irsRiskDavran + ", fatRiskDavran=" + this.fatRiskDavran + ", eInvoiceTyp=" + this.eInvoiceTyp + ", ordSendEmailAddr=" + this.ordSendEmailAddr + ", dspSendEmailAddr=" + this.dspSendEmailAddr + ", invSendEmailAddr=" + this.invSendEmailAddr + ", extSendEmailAddr=" + this.extSendEmailAddr + ", isTransfer=" + this.isTransfer + ", muhAccCode=" + this.muhAccCode + ", paymentType=" + this.paymentType + ", dueDate=" + this.dueDate + ", currencyCustomer=" + this.currencyCustomer + ", isPerCurr=" + this.isPerCurr + ", conditionCode=" + this.conditionCode + ", countryDefinition=" + this.countryDefinition + ", countryCode=" + this.countryCode + ", priceGrp=" + this.priceGrp + ", mainClCode=" + this.mainClCode + ", acceptDesp=" + this.acceptDesp + ", profileIdDesp=" + this.profileIdDesp + ", webAddr=" + this.webAddr + ", insteadOfDesp=" + this.insteadOfDesp + ", telCodes2=" + this.telCodes2 + ", paymentCode=" + this.paymentCode + ", useCustomerBasedVat=" + this.useCustomerBasedVat + ", customerSalesVat=" + this.customerSalesVat + ", projectCode=" + this.projectCode + ", explanation1=" + this.explanation1 + ", restriction=" + this.restriction + ", salesDistribution=" + this.salesDistribution + ')';
    }
    public ClCard(int i2, String str, String str2, double d2, double d3, double d4, double d5, double d6, String str3, int i3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i4, int i5, String str12, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, double d23, double d24, double d25, double d26, double d27, double d28, double d29, int i6, double d30, double d31, double d32, String str13, String str14, String str15, String str16, String str17, double d33, String str18, String str19, String str20, String str21, String str22, String str23, byte[] bArr, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, int i7, String str36, String str37, int i8, String str38, String str39, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, double d34, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, String str40, String str41, String str42, String str43, int i32, String str44, int i33, String str45, int i34, int i35, String str46, String str47, String str48, String str49, String str50, int i36, int i37, String str51, int i38, String str52, String str53, int i39, double d35, String str54, String str55, String str56, int i40, int i41, int i42, int i43, int i44, int i45, DefaultConstructorMarker defaultConstructorMarker) {
        this(r5, r7, r9, r13, r15, r17, r19, r21, r10, r6, r11, r12, r24, r25, r8, r27, r29, r31, r33, r34, r35, r36, r38, r40, r42, r44, r46, r48, r50, r52, r54, r56, r58, r60, r62, r64, r66, r68, r70, r72, r74, r76, r78, r80, r327, r82, r84, r86, r0, r88, r89, r90, r91, r92, r94, r95, r96, r97, r98, r99, r100, r101, r102, r1, (i43 & 1) != 0 ? r23 : str27, (i43 & 2) != 0 ? r23 : str28, (i43 & 4) != 0 ? r23 : str29, (i43 & 8) != 0 ? r23 : str30, (i43 & 16) != 0 ? r23 : str31, (i43 & 32) != 0 ? r23 : str32, (i43 & 64) != 0 ? r23 : str33, (i43 & 128) != 0 ? r23 : str34, (i43 & 256) != 0 ? r23 : str35, (i43 & 512) != 0 ? 0 : i7, (i43 & 1024) != 0 ? r23 : str36, (i43 & 2048) != 0 ? r23 : str37, (i43 & 4096) != 0 ? 0 : i8, (i43 & 8192) != 0 ? r23 : str38, (i43 & 16384) != 0 ? r23 : str39, (i43 & 32768) != 0 ? 0 : i9, (i43 & 65536) != 0 ? 0 : i10, (i43 & 131072) != 0 ? 0 : i11, (i43 & 262144) != 0 ? 0 : i12, (i43 & 524288) != 0 ? 0 : i13, (i43 & 1048576) != 0 ? 0 : i14, (i43 & 2097152) != 0 ? 0 : i15, (i43 & 4194304) != 0 ? 0 : i16, (i43 & 8388608) != 0 ? 0 : i17, (i43 & 16777216) != 0 ? 0 : i18, (i43 & 33554432) != 0 ? 0 : i19, (i43 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0 : i20, (i43 & 134217728) != 0 ? 0 : i21, (i43 & 268435456) != 0 ? 0.0d : d34, (i43 & 536870912) != 0 ? 0 : i22, (i43 & BasicMeasure.EXACTLY) != 0 ? 0 : i23, (i43 & Integer.MIN_VALUE) != 0 ? 0 : i24, (i44 & 1) != 0 ? 0 : i25, (i44 & 2) != 0 ? 0 : i26, (i44 & 4) != 0 ? 0 : i27, (i44 & 8) != 0 ? 0 : i28, (i44 & 16) != 0 ? 0 : i29, (i44 & 32) != 0 ? 0 : i30, (i44 & 64) != 0 ? 0 : i31, (i44 & 128) != 0 ? r23 : str40, (i44 & 256) != 0 ? r23 : str41, (i44 & 512) != 0 ? r23 : str42, (i44 & 1024) != 0 ? r23 : str43, (i44 & 2048) != 0 ? 0 : i32, (i44 & 4096) != 0 ? r23 : str44, (i44 & 8192) != 0 ? 0 : i33, (i44 & 16384) != 0 ? r23 : str45, (i44 & 32768) != 0 ? 0 : i34, (i44 & 65536) != 0 ? 0 : i35, (i44 & 131072) != 0 ? r23 : str46, (i44 & 262144) != 0 ? r23 : str47, (i44 & 524288) != 0 ? r23 : str48, (i44 & 1048576) != 0 ? r23 : str49, (i44 & 2097152) != 0 ? r23 : str50, (i44 & 4194304) != 0 ? 0 : i36, (i44 & 8388608) != 0 ? 0 : i37, (i44 & 16777216) != 0 ? r23 : str51, (i44 & 33554432) != 0 ? 0 : i38, (i44 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? r23 : str52, (i44 & 134217728) != 0 ? r23 : str53, (i44 & 268435456) != 0 ? 0 : i39, (i44 & 536870912) != 0 ? 0.0d : d35, (i44 & BasicMeasure.EXACTLY) != 0 ? r23 : str54, (i44 & Integer.MIN_VALUE) != 0 ? r23 : str55, (i45 & 1) == 0 ? str56 : "", (i45 & 2) != 0 ? 0 : i40);
        String str57;
        int i46 = (i41 & 1) != 0 ? 0 : i2;
        String str58 = (i41 & 2) != 0 ? "" : str;
        String str59 = (i41 & 4) != 0 ? "" : str2;
        double d36 = (i41 & 8) != 0 ? 0.0d : d2;
        double d37 = (i41 & 16) != 0 ? 0.0d : d3;
        double d38 = (i41 & 32) != 0 ? 0.0d : d4;
        double d39 = (i41 & 64) != 0 ? 0.0d : d5;
        double d40 = (i41 & 128) != 0 ? 0.0d : d6;
        String str60 = (i41 & 256) != 0 ? "" : str3;
        int i47 = (i41 & 512) != 0 ? 0 : i3;
        String str61 = (i41 & 1024) != 0 ? "" : str4;
        String str62 = (i41 & 2048) != 0 ? "" : str5;
        str57 = "";
        String str63 = (i41 & 4096) != 0 ? str57 : str6;
        String str64 = (i41 & 8192) != 0 ? str57 : str7;
        String str65 = (i41 & 16384) != 0 ? str57 : str8;
        String str66 = (i41 & 32768) != 0 ? str57 : str9;
        String str67 = (i41 & 65536) != 0 ? str57 : str10;
        String str68 = (i41 & 131072) != 0 ? str57 : str11;
        int i48 = (i41 & 262144) != 0 ? 0 : i4;
        int i49 = (i41 & 524288) != 0 ? 0 : i5;
        String str69 = (i41 & 1048576) != 0 ? str57 : str12;
        double d41 = (i41 & 2097152) != 0 ? 0.0d : d7;
        double d42 = (i41 & 4194304) != 0 ? 0.0d : d8;
        double d43 = (i41 & 8388608) != 0 ? 0.0d : d9;
        double d44 = (i41 & 16777216) != 0 ? 0.0d : d10;
        double d45 = (i41 & 33554432) != 0 ? 0.0d : d11;
        double d46 = (i41 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0.0d : d12;
        double d47 = (i41 & 134217728) != 0 ? 0.0d : d13;
        double d48 = (i41 & 268435456) != 0 ? 0.0d : d14;
        double d49 = (i41 & 536870912) != 0 ? 0.0d : d15;
        double d50 = (i41 & BasicMeasure.EXACTLY) != 0 ? 0.0d : d16;
        double d51 = (i41 & Integer.MIN_VALUE) != 0 ? 0.0d : d17;
        double d52 = (i42 & 1) != 0 ? 0.0d : d18;
        double d53 = (i42 & 2) != 0 ? 0.0d : d19;
        double d54 = (i42 & 4) != 0 ? 0.0d : d20;
        double d55 = (i42 & 8) != 0 ? 0.0d : d21;
        double d56 = (i42 & 16) != 0 ? 0.0d : d22;
        double d57 = (i42 & 32) != 0 ? 0.0d : d23;
        double d58 = (i42 & 64) != 0 ? 0.0d : d24;
        double d59 = (i42 & 128) != 0 ? 0.0d : d25;
        double d60 = (i42 & 256) != 0 ? 0.0d : d26;
        double d61 = (i42 & 512) != 0 ? 0.0d : d27;
        double d62 = (i42 & 1024) != 0 ? 0.0d : d28;
        double d63 = (i42 & 2048) != 0 ? 0.0d : d29;
        int i50 = (i42 & 4096) != 0 ? 0 : i6;
        double d64 = (i42 & 8192) != 0 ? 0.0d : d30;
        double d65 = (i42 & 16384) != 0 ? 0.0d : d31;
        double d66 = (i42 & 32768) != 0 ? 0.0d : d32;
        String str70 = (i42 & 65536) != 0 ? str57 : str13;
        String str71 = (i42 & 131072) != 0 ? str57 : str14;
        String str72 = (i42 & 262144) != 0 ? str57 : str15;
        String str73 = (i42 & 524288) != 0 ? str57 : str16;
        String str74 = (i42 & 1048576) != 0 ? str57 : str17;
        double d67 = (i42 & 2097152) != 0 ? 0.0d : d33;
        String str75 = (i42 & 4194304) != 0 ? str57 : str18;
        String str76 = (i42 & 8388608) != 0 ? str57 : str19;
        String str77 = (i42 & 16777216) != 0 ? str57 : str20;
        String str78 = (i42 & 33554432) != 0 ? str57 : str21;
        String str79 = (i42 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? str57 : str22;
        String str80 = (i42 & 134217728) != 0 ? str57 : str23;
        byte[] bArr2 = (i42 & 268435456) != 0 ? null : bArr;
        String str81 = (i42 & 536870912) != 0 ? str57 : str24;
        String str82 = (i42 & BasicMeasure.EXACTLY) != 0 ? str57 : str25;
        String str83 = (i42 & Integer.MIN_VALUE) != 0 ? str57 : str26;
    }
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.code = str;
    }
    public String getDefinition() {
        return this.definition;
    }
    public void setDefinition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.definition = str;
    }
    public double getAccRiskLimit() {
        return this.accRiskLimit;
    }
    public void setAccRiskLimit(double d2) {
        this.accRiskLimit = d2;
    }
    public double getDebit() {
        return this.debit;
    }
    public void setDebit(double d2) {
        this.debit = d2;
    }
    public double getCredit() {
        return this.credit;
    }
    public void setCredit(double d2) {
        this.credit = d2;
    }
    public double getBakiye() {
        return this.bakiye;
    }
    public void setBakiye(double d2) {
        this.bakiye = d2;
    }
    public double getDiscrate() {
        return this.discrate;
    }
    public void setDiscrate(double d2) {
        this.discrate = d2;
    }
    public String getTradinggrp() {
        return this.tradinggrp;
    }
    public void setTradinggrp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tradinggrp = str;
    }
    public int getPaymentRef() {
        return this.paymentRef;
    }
    public void setPaymentRef(int i2) {
        this.paymentRef = i2;
    }
    public String getSpecode() {
        return this.specode;
    }
    public void setSpecode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.specode = str;
    }
    public String getEmailAddr() {
        return this.emailAddr;
    }
    public void setEmailAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.emailAddr = str;
    }
    public String getEmailAddr2() {
        return this.emailAddr2;
    }
    public void setEmailAddr2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.emailAddr2 = str;
    }
    public String getCityDefinition() {
        return this.cityDefinition;
    }
    public void setCityDefinition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cityDefinition = str;
    }
    public String getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cityCode = str;
    }
    public String getTelNrs1() {
        return this.telNrs1;
    }
    public void setTelNrs1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.telNrs1 = str;
    }
    public String getFaxNr() {
        return this.faxNr;
    }
    public void setFaxNr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.faxNr = str;
    }

    public String getAddr1() {
        return this.addr1;
    }

    public void setAddr1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.addr1 = str;
    }

    public int getVisitDay() {
        return this.visitDay;
    }

    public void setVisitDay(int i2) {
        this.visitDay = i2;
    }

    public int getRouteRef() {
        return this.routeRef;
    }

    public void setRouteRef(int i2) {
        this.routeRef = i2;
    }

    public String getTaxNr() {
        return this.taxNr;
    }

    public void setTaxNr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.taxNr = str;
    }

    public double getMycsRiskLimit() {
        return this.mycsRiskLimit;
    }

    public void setMycsRiskLimit(double d2) {
        this.mycsRiskLimit = d2;
    }

    public double getCstcRiskLimit() {
        return this.cstcRiskLimit;
    }

    public void setCstcRiskLimit(double d2) {
        this.cstcRiskLimit = d2;
    }

    public double getCstciroRiskLimit() {
        return this.cstciroRiskLimit;
    }

    public void setCstciroRiskLimit(double d2) {
        this.cstciroRiskLimit = d2;
    }

    public double getDespRiskLimit() {
        return this.despRiskLimit;
    }

    public void setDespRiskLimit(double d2) {
        this.despRiskLimit = d2;
    }

    public double getDespRiskLimitSug() {
        return this.despRiskLimitSug;
    }

    public void setDespRiskLimitSug(double d2) {
        this.despRiskLimitSug = d2;
    }

    public double getOrdRiskLimit() {
        return this.ordRiskLimit;
    }

    public void setOrdRiskLimit(double d2) {
        this.ordRiskLimit = d2;
    }

    public double getOrdRiskLimitSugg() {
        return this.ordRiskLimitSugg;
    }

    public void setOrdRiskLimitSugg(double d2) {
        this.ordRiskLimitSugg = d2;
    }

    public double getAccRskBlnced() {
        return this.accRskBlnced;
    }

    public void setAccRskBlnced(double d2) {
        this.accRskBlnced = d2;
    }

    public double getMycsrskBlnced() {
        return this.mycsrskBlnced;
    }

    public void setMycsrskBlnced(double d2) {
        this.mycsrskBlnced = d2;
    }

    public double getCstcsrkBlnced() {
        return this.cstcsrkBlnced;
    }

    public void setCstcsrkBlnced(double d2) {
        this.cstcsrkBlnced = d2;
    }

    public double getCstcsrirorskBlnced() {
        return this.cstcsrirorskBlnced;
    }

    public void setCstcsrirorskBlnced(double d2) {
        this.cstcsrirorskBlnced = d2;
    }

    public double getDesprskBlnced() {
        return this.desprskBlnced;
    }

    public void setDesprskBlnced(double d2) {
        this.desprskBlnced = d2;
    }

    public double getDesprskBlncedSug() {
        return this.desprskBlncedSug;
    }

    public void setDesprskBlncedSug(double d2) {
        this.desprskBlncedSug = d2;
    }

    public double getOrdrskBlnced() {
        return this.ordrskBlnced;
    }

    public void setOrdrskBlnced(double d2) {
        this.ordrskBlnced = d2;
    }

    public double getOrdrskBlncedSug() {
        return this.ordrskBlncedSug;
    }

    public void setOrdrskBlncedSug(double d2) {
        this.ordrskBlncedSug = d2;
    }

    public double getAccRiskTotal() {
        return this.accRiskTotal;
    }

    public void setAccRiskTotal(double d2) {
        this.accRiskTotal = d2;
    }

    public double getMyCsRiskTotal() {
        return this.myCsRiskTotal;
    }

    public void setMyCsRiskTotal(double d2) {
        this.myCsRiskTotal = d2;
    }

    public double getCstcsownRiskTotal() {
        return this.cstcsownRiskTotal;
    }

    public void setCstcsownRiskTotal(double d2) {
        this.cstcsownRiskTotal = d2;
    }

    public double getCstcsiroRiskTotal() {
        return this.cstcsiroRiskTotal;
    }

    public void setCstcsiroRiskTotal(double d2) {
        this.cstcsiroRiskTotal = d2;
    }

    public double getDespRiskTotal() {
        return this.despRiskTotal;
    }

    public void setDespRiskTotal(double d2) {
        this.despRiskTotal = d2;
    }

    public double getDespRiskTotalSug() {
        return this.despRiskTotalSug;
    }

    public void setDespRiskTotalSug(double d2) {
        this.despRiskTotalSug = d2;
    }

    public double getOrdRiskTotal() {
        return this.ordRiskTotal;
    }

    public void setOrdRiskTotal(double d2) {
        this.ordRiskTotal = d2;
    }

    public double getOrdRiskTotalSugg() {
        return this.ordRiskTotalSugg;
    }

    public void setOrdRiskTotalSugg(double d2) {
        this.ordRiskTotalSugg = d2;
    }

    public int getCCurrency() {
        return this.cCurrency;
    }

    public void setCCurrency(int i2) {
        this.cCurrency = i2;
    }

    public double getDebitFloat() {
        return this.debitFloat;
    }

    public void setDebitFloat(double d2) {
        this.debitFloat = d2;
    }

    public double getCreditFloat() {
        return this.creditFloat;
    }

    public void setCreditFloat(double d2) {
        this.creditFloat = d2;
    }

    public double getBakiyeFloat() {
        return this.bakiyeFloat;
    }

    public void setBakiyeFloat(double d2) {
        this.bakiyeFloat = d2;
    }

    public String getTaxOffCode() {
        return this.taxOffCode;
    }

    public void setTaxOffCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.taxOffCode = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.district = str;
    }

    public String getInCharge() {
        return this.inCharge;
    }

    public void setInCharge(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inCharge = str;
    }

    public String getInCharge2() {
        return this.inCharge2;
    }

    public void setInCharge2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inCharge2 = str;
    }

    public String getInCharge3() {
        return this.inCharge3;
    }

    public void setInCharge3(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inCharge3 = str;
    }

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d2) {
        this.cmDate = d2;
    }

    public String getTaxOffice() {
        return this.taxOffice;
    }

    public void setTaxOffice(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.taxOffice = str;
    }

    public String getTelNrs2() {
        return this.telNrs2;
    }

    public void setTelNrs2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.telNrs2 = str;
    }

    public String getAddr2() {
        return this.addr2;
    }

    public void setAddr2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.addr2 = str;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.postCode = str;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerName = str;
    }

    public String getCustomerSurname() {
        return this.customerSurname;
    }

    public void setCustomerSurname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerSurname = str;
    }

    public byte[] getClPhoto() {
        return this.clPhoto;
    }

    public void setClPhoto(byte[] bArr) {
        this.clPhoto = bArr;
    }

    public String getTownDefinition() {
        return this.townDefinition;
    }

    public void setTownDefinition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.townDefinition = str;
    }

    public String getTownCode() {
        return this.townCode;
    }

    public void setTownCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.townCode = str;
    }

    public String getSpecode2() {
        return this.specode2;
    }

    public void setSpecode2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.specode2 = str;
    }

    public String getSpecode3() {
        return this.specode3;
    }

    public void setSpecode3(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.specode3 = str;
    }

    public String getSpecode4() {
        return this.specode4;
    }

    public void setSpecode4(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.specode4 = str;
    }

    public String getSpecode5() {
        return this.specode5;
    }

    public void setSpecode5(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.specode5 = str;
    }

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.groupCode = str;
    }

    public String getWarrantyCode() {
        return this.warrantyCode;
    }

    public void setWarrantyCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.warrantyCode = str;
    }

    public String getCyphcode() {
        return this.cyphcode;
    }

    public void setCyphcode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cyphcode = str;
    }

    public String getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public void setDeliveryMethod(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deliveryMethod = str;
    }

    public String getDeliveryFirm() {
        return this.deliveryFirm;
    }

    public void setDeliveryFirm(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deliveryFirm = str;
    }

    public String getEdiNo() {
        return this.ediNo;
    }

    public void setEdiNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ediNo = str;
    }

    public int getAcceptInv() {
        return this.acceptInv;
    }

    public void setAcceptInv(int i2) {
        this.acceptInv = i2;
    }

    public String getDefinition2() {
        return this.definition2;
    }

    public void setDefinition2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.definition2 = str;
    }

    public String getTckNo() {
        return this.tckNo;
    }

    public void setTckNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tckNo = str;
    }

    public int getPersonalCompany() {
        return this.personalCompany;
    }

    public void setPersonalCompany(int i2) {
        this.personalCompany = i2;
    }

    public String getTelCodes1() {
        return this.telCodes1;
    }

    public void setTelCodes1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.telCodes1 = str;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cardType = str;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int i2) {
        this.active = i2;
    }

    public int getAccRiskOver() {
        return this.accRiskOver;
    }

    public void setAccRiskOver(int i2) {
        this.accRiskOver = i2;
    }

    public int getCstcsCiroRiskOver() {
        return this.cstcsCiroRiskOver;
    }

    public void setCstcsCiroRiskOver(int i2) {
        this.cstcsCiroRiskOver = i2;
    }

    public int getCstcsRiskOver() {
        return this.cstcsRiskOver;
    }

    public void setCstcsRiskOver(int i2) {
        this.cstcsRiskOver = i2;
    }

    public int getDespRiskOver() {
        return this.despRiskOver;
    }

    public void setDespRiskOver(int i2) {
        this.despRiskOver = i2;
    }

    public int getDespRiskOverSug() {
        return this.despRiskOverSug;
    }

    public void setDespRiskOverSug(int i2) {
        this.despRiskOverSug = i2;
    }

    public int getMyCsRiskOver() {
        return this.myCsRiskOver;
    }

    public void setMyCsRiskOver(int i2) {
        this.myCsRiskOver = i2;
    }

    public int getOrdRiskOver() {
        return this.ordRiskOver;
    }

    public void setOrdRiskOver(int i2) {
        this.ordRiskOver = i2;
    }

    public int getOrdRiskOverSugg() {
        return this.ordRiskOverSugg;
    }

    public void setOrdRiskOverSugg(int i2) {
        this.ordRiskOverSugg = i2;
    }

    public int getRiskOver() {
        return this.riskOver;
    }

    public void setRiskOver(int i2) {
        this.riskOver = i2;
    }

    public int getDiscType() {
        return this.discType;
    }

    public void setDiscType(int i2) {
        this.discType = i2;
    }

    public int getSendMod() {
        return this.sendMod;
    }

    public void setSendMod(int i2) {
        this.sendMod = i2;
    }

    public int getProfileId() {
        return this.profileId;
    }

    public void setProfileId(int i2) {
        this.profileId = i2;
    }

    public double getDueDateLimit() {
        return this.dueDateLimit;
    }

    public void setDueDateLimit(double d2) {
        this.dueDateLimit = d2;
    }

    public int getDueDateCount() {
        return this.dueDateCount;
    }

    public void setDueDateCount(int i2) {
        this.dueDateCount = i2;
    }

    public int getDueDateTrack() {
        return this.dueDateTrack;
    }

    public void setDueDateTrack(int i2) {
        this.dueDateTrack = i2;
    }

    public int getDueDateControlInv() {
        return this.dueDateControlInv;
    }

    public void setDueDateControlInv(int i2) {
        this.dueDateControlInv = i2;
    }

    public int getDueDateControlOrd() {
        return this.dueDateControlOrd;
    }

    public void setDueDateControlOrd(int i2) {
        this.dueDateControlOrd = i2;
    }

    public int getSipRiskDavran() {
        return this.sipRiskDavran;
    }

    public void setSipRiskDavran(int i2) {
        this.sipRiskDavran = i2;
    }

    public int getSevkRiskDavran() {
        return this.sevkRiskDavran;
    }

    public void setSevkRiskDavran(int i2) {
        this.sevkRiskDavran = i2;
    }

    public int getYukRiskDavran() {
        return this.yukRiskDavran;
    }

    public void setYukRiskDavran(int i2) {
        this.yukRiskDavran = i2;
    }

    public int getIrsRiskDavran() {
        return this.irsRiskDavran;
    }

    public void setIrsRiskDavran(int i2) {
        this.irsRiskDavran = i2;
    }

    public int getFatRiskDavran() {
        return this.fatRiskDavran;
    }

    public void setFatRiskDavran(int i2) {
        this.fatRiskDavran = i2;
    }

    public int getEInvoiceTyp() {
        return this.eInvoiceTyp;
    }

    public void setEInvoiceTyp(int i2) {
        this.eInvoiceTyp = i2;
    }

    public String getOrdSendEmailAddr() {
        return this.ordSendEmailAddr;
    }

    public void setOrdSendEmailAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ordSendEmailAddr = str;
    }

    public String getDspSendEmailAddr() {
        return this.dspSendEmailAddr;
    }

    public void setDspSendEmailAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dspSendEmailAddr = str;
    }

    public String getInvSendEmailAddr() {
        return this.invSendEmailAddr;
    }

    public void setInvSendEmailAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.invSendEmailAddr = str;
    }

    public String getExtSendEmailAddr() {
        return this.extSendEmailAddr;
    }

    public void setExtSendEmailAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.extSendEmailAddr = str;
    }

    private int isTransfer() {
        return this.isTransfer;
    }

    private void setTransfer(int i2) {
        this.isTransfer = i2;
    }

    public String getMuhAccCode() {
        return this.muhAccCode;
    }

    public void setMuhAccCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.muhAccCode = str;
    }

    public int getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(int i2) {
        this.paymentType = i2;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dueDate = str;
    }

    public int getCurrencyCustomer() {
        return this.currencyCustomer;
    }

    public void setCurrencyCustomer(int i2) {
        this.currencyCustomer = i2;
    }

    public int isPerCurr() {
        return this.isPerCurr;
    }

    public void setPerCurr(int i2) {
        this.isPerCurr = i2;
    }

    public String getConditionCode() {
        return this.conditionCode;
    }

    public void setConditionCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.conditionCode = str;
    }

    public String getCountryDefinition() {
        return this.countryDefinition;
    }

    public void setCountryDefinition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryDefinition = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public String getPriceGrp() {
        return this.priceGrp;
    }

    public void setPriceGrp(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.priceGrp = str;
    }

    public String getMainClCode() {
        return this.mainClCode;
    }

    public void setMainClCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainClCode = str;
    }

    public int getAcceptDesp() {
        return this.acceptDesp;
    }

    public void setAcceptDesp(int i2) {
        this.acceptDesp = i2;
    }

    public int getProfileIdDesp() {
        return this.profileIdDesp;
    }

    public void setProfileIdDesp(int i2) {
        this.profileIdDesp = i2;
    }

    public String getWebAddr() {
        return this.webAddr;
    }

    public void setWebAddr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.webAddr = str;
    }

    public int getInsteadOfDesp() {
        return this.insteadOfDesp;
    }

    public void setInsteadOfDesp(int i2) {
        this.insteadOfDesp = i2;
    }

    public String getTelCodes2() {
        return this.telCodes2;
    }

    public void setTelCodes2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.telCodes2 = str;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.paymentCode = str;
    }

    public int getUseCustomerBasedVat() {
        return this.useCustomerBasedVat;
    }

    public void setUseCustomerBasedVat(int i2) {
        this.useCustomerBasedVat = i2;
    }

    public double getCustomerSalesVat() {
        return this.customerSalesVat;
    }

    public void setCustomerSalesVat(double d2) {
        this.customerSalesVat = d2;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.projectCode = str;
    }

    public String getExplanation1() {
        return this.explanation1;
    }

    public void setExplanation1(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.explanation1 = str;
    }

    public String getRestriction() {
        return this.restriction;
    }

    public void setRestriction(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.restriction = str;
    }

    public int getSalesDistribution() {
        return this.salesDistribution;
    }

    public void setSalesDistribution(int i2) {
        this.salesDistribution = i2;
    }

    public ClCard(int i2, String code, String definition, double d2, double d3, double d4, double d5, double d6, String tradinggrp, int i3, String specode, String emailAddr, String emailAddr2, String cityDefinition, String cityCode, String telNrs1, String faxNr, String addr1, int i4, int i5, String taxNr, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, double d20, double d21, double d22, double d23, double d24, double d25, double d26, double d27, double d28, double d29, int i6, double d30, double d31, double d32, String taxOffCode, String district, String inCharge, String inCharge2, String inCharge3, double d33, String taxOffice, String telNrs2, String addr2, String postCode, String customerName, String customerSurname, byte[] bArr, String townDefinition, String townCode, String specode2, String specode3, String specode4, String specode5, String groupCode, String warrantyCode, String cyphcode, String deliveryMethod, String deliveryFirm, String ediNo, int i7, String definition2, String tckNo, int i8, String telCodes1, String cardType, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, double d34, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31, String ordSendEmailAddr, String dspSendEmailAddr, String invSendEmailAddr, String extSendEmailAddr, int i32, String muhAccCode, int i33, String dueDate, int i34, int i35, String conditionCode, String countryDefinition, String countryCode, String priceGrp, String mainClCode, int i36, int i37, String webAddr, int i38, String telCodes2, String paymentCode, int i39, double d35, String projectCode, String explanation1, String restriction, int i40) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(emailAddr, "emailAddr");
        Intrinsics.checkNotNullParameter(emailAddr2, "emailAddr2");
        Intrinsics.checkNotNullParameter(cityDefinition, "cityDefinition");
        Intrinsics.checkNotNullParameter(cityCode, "cityCode");
        Intrinsics.checkNotNullParameter(telNrs1, "telNrs1");
        Intrinsics.checkNotNullParameter(faxNr, "faxNr");
        Intrinsics.checkNotNullParameter(addr1, "addr1");
        Intrinsics.checkNotNullParameter(taxNr, "taxNr");
        Intrinsics.checkNotNullParameter(taxOffCode, "taxOffCode");
        Intrinsics.checkNotNullParameter(district, "district");
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(inCharge2, "inCharge2");
        Intrinsics.checkNotNullParameter(inCharge3, "inCharge3");
        Intrinsics.checkNotNullParameter(taxOffice, "taxOffice");
        Intrinsics.checkNotNullParameter(telNrs2, "telNrs2");
        Intrinsics.checkNotNullParameter(addr2, "addr2");
        Intrinsics.checkNotNullParameter(postCode, "postCode");
        Intrinsics.checkNotNullParameter(customerName, "customerName");
        Intrinsics.checkNotNullParameter(customerSurname, "customerSurname");
        Intrinsics.checkNotNullParameter(townDefinition, "townDefinition");
        Intrinsics.checkNotNullParameter(townCode, "townCode");
        Intrinsics.checkNotNullParameter(specode2, "specode2");
        Intrinsics.checkNotNullParameter(specode3, "specode3");
        Intrinsics.checkNotNullParameter(specode4, "specode4");
        Intrinsics.checkNotNullParameter(specode5, "specode5");
        Intrinsics.checkNotNullParameter(groupCode, "groupCode");
        Intrinsics.checkNotNullParameter(warrantyCode, "warrantyCode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(deliveryMethod, "deliveryMethod");
        Intrinsics.checkNotNullParameter(deliveryFirm, "deliveryFirm");
        Intrinsics.checkNotNullParameter(ediNo, "ediNo");
        Intrinsics.checkNotNullParameter(definition2, "definition2");
        Intrinsics.checkNotNullParameter(tckNo, "tckNo");
        Intrinsics.checkNotNullParameter(telCodes1, "telCodes1");
        Intrinsics.checkNotNullParameter(cardType, "cardType");
        Intrinsics.checkNotNullParameter(ordSendEmailAddr, "ordSendEmailAddr");
        Intrinsics.checkNotNullParameter(dspSendEmailAddr, "dspSendEmailAddr");
        Intrinsics.checkNotNullParameter(invSendEmailAddr, "invSendEmailAddr");
        Intrinsics.checkNotNullParameter(extSendEmailAddr, "extSendEmailAddr");
        Intrinsics.checkNotNullParameter(muhAccCode, "muhAccCode");
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(conditionCode, "conditionCode");
        Intrinsics.checkNotNullParameter(countryDefinition, "countryDefinition");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(priceGrp, "priceGrp");
        Intrinsics.checkNotNullParameter(mainClCode, "mainClCode");
        Intrinsics.checkNotNullParameter(webAddr, "webAddr");
        Intrinsics.checkNotNullParameter(telCodes2, "telCodes2");
        Intrinsics.checkNotNullParameter(paymentCode, "paymentCode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(restriction, "restriction");
        this.logicalRef = i2;
        this.code = code;
        this.definition = definition;
        this.accRiskLimit = d2;
        this.debit = d3;
        this.credit = d4;
        this.bakiye = d5;
        this.discrate = d6;
        this.tradinggrp = tradinggrp;
        this.paymentRef = i3;
        this.specode = specode;
        this.emailAddr = emailAddr;
        this.emailAddr2 = emailAddr2;
        this.cityDefinition = cityDefinition;
        this.cityCode = cityCode;
        this.telNrs1 = telNrs1;
        this.faxNr = faxNr;
        this.addr1 = addr1;
        this.visitDay = i4;
        this.routeRef = i5;
        this.taxNr = taxNr;
        this.mycsRiskLimit = d7;
        this.cstcRiskLimit = d8;
        this.cstciroRiskLimit = d9;
        this.despRiskLimit = d10;
        this.despRiskLimitSug = d11;
        this.ordRiskLimit = d12;
        this.ordRiskLimitSugg = d13;
        this.accRskBlnced = d14;
        this.mycsrskBlnced = d15;
        this.cstcsrkBlnced = d16;
        this.cstcsrirorskBlnced = d17;
        this.desprskBlnced = d18;
        this.desprskBlncedSug = d19;
        this.ordrskBlnced = d20;
        this.ordrskBlncedSug = d21;
        this.accRiskTotal = d22;
        this.myCsRiskTotal = d23;
        this.cstcsownRiskTotal = d24;
        this.cstcsiroRiskTotal = d25;
        this.despRiskTotal = d26;
        this.despRiskTotalSug = d27;
        this.ordRiskTotal = d28;
        this.ordRiskTotalSugg = d29;
        this.cCurrency = i6;
        this.debitFloat = d30;
        this.creditFloat = d31;
        this.bakiyeFloat = d32;
        this.taxOffCode = taxOffCode;
        this.district = district;
        this.inCharge = inCharge;
        this.inCharge2 = inCharge2;
        this.inCharge3 = inCharge3;
        this.cmDate = d33;
        this.taxOffice = taxOffice;
        this.telNrs2 = telNrs2;
        this.addr2 = addr2;
        this.postCode = postCode;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.clPhoto = bArr;
        this.townDefinition = townDefinition;
        this.townCode = townCode;
        this.specode2 = specode2;
        this.specode3 = specode3;
        this.specode4 = specode4;
        this.specode5 = specode5;
        this.groupCode = groupCode;
        this.warrantyCode = warrantyCode;
        this.cyphcode = cyphcode;
        this.deliveryMethod = deliveryMethod;
        this.deliveryFirm = deliveryFirm;
        this.ediNo = ediNo;
        this.acceptInv = i7;
        this.definition2 = definition2;
        this.tckNo = tckNo;
        this.personalCompany = i8;
        this.telCodes1 = telCodes1;
        this.cardType = cardType;
        this.active = i9;
        this.accRiskOver = i10;
        this.cstcsCiroRiskOver = i11;
        this.cstcsRiskOver = i12;
        this.despRiskOver = i13;
        this.despRiskOverSug = i14;
        this.myCsRiskOver = i15;
        this.ordRiskOver = i16;
        this.ordRiskOverSugg = i17;
        this.riskOver = i18;
        this.discType = i19;
        this.sendMod = i20;
        this.profileId = i21;
        this.dueDateLimit = d34;
        this.dueDateCount = i22;
        this.dueDateTrack = i23;
        this.dueDateControlInv = i24;
        this.dueDateControlOrd = i25;
        this.sipRiskDavran = i26;
        this.sevkRiskDavran = i27;
        this.yukRiskDavran = i28;
        this.irsRiskDavran = i29;
        this.fatRiskDavran = i30;
        this.eInvoiceTyp = i31;
        this.ordSendEmailAddr = ordSendEmailAddr;
        this.dspSendEmailAddr = dspSendEmailAddr;
        this.invSendEmailAddr = invSendEmailAddr;
        this.extSendEmailAddr = extSendEmailAddr;
        this.isTransfer = i32;
        this.muhAccCode = muhAccCode;
        this.paymentType = i33;
        this.dueDate = dueDate;
        this.currencyCustomer = i34;
        this.isPerCurr = i35;
        this.conditionCode = conditionCode;
        this.countryDefinition = countryDefinition;
        this.countryCode = countryCode;
        this.priceGrp = priceGrp;
        this.mainClCode = mainClCode;
        this.acceptDesp = i36;
        this.profileIdDesp = i37;
        this.webAddr = webAddr;
        this.insteadOfDesp = i38;
        this.telCodes2 = telCodes2;
        this.paymentCode = paymentCode;
        this.useCustomerBasedVat = i39;
        this.customerSalesVat = d35;
        this.projectCode = projectCode;
        this.explanation1 = explanation1;
        this.restriction = restriction;
        this.salesDistribution = i40;
    }

    @Override // com.proje.mobilesales.core.interfaces.ConvertDb
    public void convertDbType(CustomerNew customerNew) {
        Intrinsics.checkNotNullParameter(customerNew, "customerNew");
        Boolean active = customerNew.getActive();
        Intrinsics.checkNotNull(active);
        this.active = !active.booleanValue() ? 1 : 0;
        this.code = String.valueOf(customerNew.getCode());
        this.definition = String.valueOf(customerNew.getName());
        this.telNrs1 = String.valueOf(customerNew.getTel1());
        this.telNrs2 = String.valueOf(customerNew.getTel2());
        this.faxNr = String.valueOf(customerNew.getFax());
        this.postCode = String.valueOf(customerNew.getZipCode());
        this.logicalRef = customerNew.getLogicalRef();
        this.specode = String.valueOf(customerNew.getSpeCode());
        this.specode2 = String.valueOf(customerNew.getSpeCode2());
        this.specode3 = String.valueOf(customerNew.getSpeCode3());
        this.specode4 = String.valueOf(customerNew.getSpeCode4());
        this.specode5 = String.valueOf(customerNew.getSpeCode5());
        this.groupCode = String.valueOf(customerNew.getGroupCode());
        this.warrantyCode = String.valueOf(customerNew.getWarrantyCode());
        this.customerName = String.valueOf(customerNew.getCustomerName());
        this.customerSurname = String.valueOf(customerNew.getCustomerSurname());
        this.emailAddr = String.valueOf(customerNew.getEmail());
        this.emailAddr2 = String.valueOf(customerNew.getEmail2());
        this.taxNr = String.valueOf(customerNew.getTaxNo());
        this.taxOffice = String.valueOf(customerNew.getTaxOffice());
        this.taxOffice = String.valueOf(customerNew.getTaxOffice());
        this.addr1 = String.valueOf(customerNew.getAddress1());
        this.addr2 = String.valueOf(customerNew.getAddress2());
        this.inCharge = String.valueOf(customerNew.getRelatedPerson());
        FicheRefProp payPlan = customerNew.getPayPlan();
        Intrinsics.checkNotNull(payPlan);
        this.paymentRef = payPlan.getLogicalRef();
        FicheImageProp image = customerNew.getImage();
        Intrinsics.checkNotNull(image);
        this.clPhoto = image.getImageArray();
        this.tckNo = String.valueOf(customerNew.getTCNo());
        FicheBooleanProp personalCompany = customerNew.getPersonalCompany();
        Intrinsics.checkNotNull(personalCompany);
        this.personalCompany = personalCompany.isSelect() ? 1 : 0;
        FicheRefProp payType = customerNew.getPayType();
        Intrinsics.checkNotNull(payType);
        this.paymentType = payType.getLogicalRef();
        this.dueDate = String.valueOf(customerNew.getVade());
        FicheStringProp iskontoOran = customerNew.getIskontoOran();
        Intrinsics.checkNotNull(iskontoOran);
        this.discrate = iskontoOran.getDefinitionDouble();
        this.countryDefinition = String.valueOf(customerNew.getCountry());
        this.cityDefinition = String.valueOf(customerNew.getCity());
        this.townDefinition = String.valueOf(customerNew.getTown());
        if (ErpCreator.getInstance().getmBaseErp().getErpType() != ErpType.NETSIS) {
            FicheDiscountRefProp country = customerNew.getCountry();
            Intrinsics.checkNotNull(country);
            String code = country.getCode();
            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
            this.countryCode = code;
            FicheDiscountRefProp city = customerNew.getCity();
            Intrinsics.checkNotNull(city);
            String code2 = city.getCode();
            Intrinsics.checkNotNullExpressionValue(code2, "getCode(...)");
            this.cityCode = code2;
            FicheDiscountRefProp town = customerNew.getTown();
            Intrinsics.checkNotNull(town);
            String code3 = town.getCode();
            Intrinsics.checkNotNullExpressionValue(code3, "getCode(...)");
            this.townCode = code3;
        } else {
            FicheDiscountRefProp country2 = customerNew.getCountry();
            Intrinsics.checkNotNull(country2);
            String ficheStringProp = country2.toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
            this.countryCode = ficheStringProp;
            FicheDiscountRefProp city2 = customerNew.getCity();
            Intrinsics.checkNotNull(city2);
            String ficheStringProp2 = city2.toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp2, "toString(...)");
            this.cityCode = ficheStringProp2;
            FicheDiscountRefProp town2 = customerNew.getTown();
            Intrinsics.checkNotNull(town2);
            String ficheStringProp3 = town2.toString();
            Intrinsics.checkNotNullExpressionValue(ficheStringProp3, "toString(...)");
            this.townCode = ficheStringProp3;
        }
        FicheRefProp payPlan2 = customerNew.getPayPlan();
        Intrinsics.checkNotNull(payPlan2);
        String definition = payPlan2.getDefinition();
        Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
        this.paymentCode = definition;
    }

    @Override // com.proje.mobilesales.core.interfaces.ConvertDb
    public CustomerNew convert() {
        CustomerNew customerNew = new CustomerNew(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, -1, 255, null);
        customerNew.setLogicalRef(this.logicalRef);
        customerNew.setActive(Boolean.valueOf(this.active == 0));
        customerNew.setCode(new FicheStringProp(this.code));
        customerNew.setName(new FicheStringProp(this.definition));
        customerNew.setAddress1(new FicheStringProp(this.addr1));
        customerNew.setAddress2(new FicheStringProp(this.addr2));
        customerNew.setCustomerName(new FicheStringProp(this.customerName));
        customerNew.setCustomerSurname(new FicheStringProp(this.customerSurname));
        customerNew.setEmail(new FicheStringProp(this.emailAddr));
        customerNew.setEmail2(new FicheStringProp(this.emailAddr2));
        customerNew.setFax(new FicheStringProp(this.faxNr));
        customerNew.setTaxOffice(new FicheStringProp(this.taxOffice));
        customerNew.setTaxNo(new FicheStringProp(this.taxNr));
        customerNew.setRelatedPerson(new FicheStringProp(this.inCharge));
        customerNew.setSpeCode(new FicheStringProp(this.specode));
        customerNew.setSpeCode2(new FicheStringProp(this.specode2));
        customerNew.setSpeCode3(new FicheStringProp(this.specode3));
        customerNew.setSpeCode4(new FicheStringProp(this.specode4));
        customerNew.setSpeCode5(new FicheStringProp(this.specode5));
        customerNew.setGroupCode(new FicheStringProp(this.groupCode));
        customerNew.setWarrantyCode(new FicheStringProp(this.warrantyCode));
        customerNew.setTel1(new FicheStringProp(this.telNrs1));
        customerNew.setTel2(new FicheStringProp(this.telNrs2));
        customerNew.setZipCode(new FicheStringProp(this.postCode));
        customerNew.setPayPlan(new FicheRefProp());
        FicheRefProp payPlan = customerNew.getPayPlan();
        Intrinsics.checkNotNull(payPlan);
        payPlan.setLogicalRef(this.paymentRef);
        FicheRefProp payPlan2 = customerNew.getPayPlan();
        Intrinsics.checkNotNull(payPlan2);
        FicheStringProp.setDefinition(this.paymentCode);
        customerNew.setImage(new FicheImageProp(this.clPhoto));
        customerNew.setTCNo(new FicheStringProp(this.tckNo));
        customerNew.setPersonalCompany(new FicheBooleanProp());
        customerNew.setPayType(new FicheRefProp());
        FicheRefProp payType = customerNew.getPayType();
        Intrinsics.checkNotNull(payType);
        payType.setLogicalRef(this.paymentType);
        customerNew.setVade(new FicheStringProp(this.dueDate));
        customerNew.setIskontoOran(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(this.discrate))));
        customerNew.setCountry(new FicheDiscountRefProp());
        customerNew.setCity(new FicheDiscountRefProp());
        customerNew.setTown(new FicheDiscountRefProp());
        return customerNew;
    }

    public Customer convertCustomer() {
        Customer customer = new Customer();
        customer.setLogicalRef(this.logicalRef);
        customer.setTitle(this.definition);
        customer.setCode(this.code);
        customer.setCredit(this.credit);
        customer.setDebit(this.debit);
        customer.setBalance(this.bakiye);
        customer.setTel(this.telCodes1 + this.telNrs1);
        customer.setEmail(this.emailAddr);
        customer.setEmail2(this.emailAddr2);
        customer.setPerson(this.inCharge);
        customer.setTaxNr(this.taxNr);
        customer.setCountry(this.countryDefinition);
        customer.setCountryCode(this.countryCode);
        customer.setTown(this.townDefinition);
        customer.setTownCode(this.townCode);
        customer.setCity(this.cityDefinition);
        customer.setCityCode(this.cityCode);
        customer.setPostCode(this.postCode);
        customer.setName(this.customerName);
        customer.setSurname(this.customerSurname);
        return customer;
    }

    @Override // com.proje.mobilesales.features.dbmodel.ConvertBinaries
    public void checkBinaries() {
        try {
            this.clPhoto = Convert(BinaryType.btIMAGE, this.clPhoto);
        } catch (IOException e2) {
            String message = e2.getMessage();
            Intrinsics.checkNotNull(message);
            Log.e("CustomerImage.ClCard", message);
        }
    }

    public CustomerRestriction getCustomerRestriction() {
        return CustomerRestriction.Companion.getCustomerRestriction(this.restriction);
    }
}
