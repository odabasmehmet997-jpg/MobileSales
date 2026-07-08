package com.proje.mobilesales.core.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.core.interfaces.ConvertSales;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.Sales;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public class BaseDbSalesFiche implements ConvertSales, Parcelable {
    public static final Companion Companion = new Companion (null);
    public static final Parcelable.Creator<BaseDbSalesFiche> CREATOR = new Creator ();
    public String clCode;
    public int fact;
    public  String ficheNo;
    public int ficheStatus;
    public int isTransfer;
    public int logicalRef;
    public int salesType;
    public String shipAccCode;
    private  String andFicheNo;
    private String beginDate;
    private double boylam;
    private int branchNr;
    private int cabinRef;
    private  String campaignRefs;
    private int canceled;
    private int clRef;
    private  String custDiscCamp;
    private  String custDiscGuid;
    private double custDiscRatio;
    private  String cyphcode;
    private int dateInt;
    private  String desc1;
    private  String desc10;
    private  String desc2;
    private  String desc3;
    private  String desc4;
    private  String desc5;
    private  String desc6;
    private  String desc7;
    private  String desc8;
    private  String desc9;
    private  String discCamp1;
    private  String discCamp2;
    private  String discCamp3;
    private  String discCamp4;
    private  String discCamp5;
    private  String discCardCode1;
    private  String discCardCode2;
    private  String discCardCode3;
    private  String discCardCode4;
    private  String discCardCode5;
    private  String discGuid1;
    private  String discGuid2;
    private  String discGuid3;
    private  String discGuid4;
    private  String discGuid5;
    private double discRatio1;
    private double discRatio2;
    private double discRatio3;
    private double discRatio4;
    private double discRatio5;
    private double discTotal;
    private double discTotal1;
    private double discTotal2;
    private double discTotal3;
    private double discTotal4;
    private double discTotal5;
    private int divisNr;
    private  String docNo;
    private  String docTracingNr;
    private  String dueDate;
    private  String eInvoiceSGKDocumentNo;
    private int eInvoiceTypSGK;
    private int edocStatus;
    private String endDate;
    private double enlem;
    private int erpInvoiceType;
    private int factNr;
    private  String ficheDate;
    private int ficheRef;
    private  String gDate;
    private double gTotal;
    private int includeVat;
    private int insType;
    private int isEdit;
    private String paymentCode;
    private int paymentRef;
    private int printCount;
    private String projectCode;
    private int projectRef;
    private  String returnWareHouseName;
    private int returnWareHouseNr;
    private int setCampaign;
    private int setSalesCondition;
    private int shipAccRef;
    private String shipAddrCode;
    private int shipAddrRef;
    private  String shipAgent;
    private  String shipType;
    private  String speCode1;
    private  String speCode2;
    private  String specode;
    private  String taxPayerCode;
    private  String taxPayerName;
    private double total;
    private double totalExpenses;
    private double totalNet;
    private double totalVat;
    private int trCurr;
    private double trRate;
    private  String tradinggrp;
    private  String wareHouseName;
    private int wareHouseNr;
    public BaseDbSalesFiche() {
        this (0, 0, null, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, null, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, 0.0d, 0, 0, null, null, 0, 0, 0, 0, 0.0d, 0, 0.0d, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0.0d, 0, 0, null, 0, null, 0, -1, -1, -1, 127, null);
    }
    public BaseDbSalesFiche(int i, int i2, String str, int i3, int i4, int i5, int i6, String str2, String str3, String str4, int i7, String str5, String str6, String str7, String str8, String str9, int i8, String str10, int i9, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, int i10, int i11, int i12, String str42, double d11, double d12, double d13, double d14, double d15, int i13, String str43, double d16, double d17, int i14, int i15, String str44, String str45, int i16, int i17, int i18, int i19, double d18, int i20, double d19, int i21, int i22, String str46, String str47, String str48, int i23, String str49, String str50, int i24, int i25, String str51, String str52, double d20, int i26, int i27, String str53, int i28, String str54, int i29) {
        this.logicalRef = i;
        this.clRef = i2;
        this.clCode = str;
        this.divisNr = i3;
        this.branchNr = i4;
        this.factNr = i5;
        this.wareHouseNr = i6;
        this.wareHouseName = str2;
        this.returnWareHouseName = str3;
        this.ficheDate = str4;
        this.paymentRef = i7;
        this.paymentCode = str5;
        this.tradinggrp = str6;
        this.docNo = str7;
        this.specode = str8;
        this.cyphcode = str9;
        this.shipAccRef = i8;
        this.shipAccCode = str10;
        this.shipAddrRef = i9;
        this.shipAddrCode = str11;
        this.shipType = str12;
        this.shipAgent = str13;
        this.desc1 = str14;
        this.desc2 = str15;
        this.desc3 = str16;
        this.desc4 = str17;
        this.desc5 = str18;
        this.desc6 = str19;
        this.desc7 = str20;
        this.desc8 = str21;
        this.desc9 = str22;
        this.desc10 = str23;
        this.discTotal1 = d;
        this.discRatio1 = d2;
        this.discTotal2 = d3;
        this.discRatio2 = d4;
        this.discTotal3 = d5;
        this.discRatio3 = d6;
        this.discTotal4 = d7;
        this.discRatio4 = d8;
        this.discTotal5 = d9;
        this.discRatio5 = d10;
        this.discCardCode1 = str24;
        this.discCardCode2 = str25;
        this.discCardCode3 = str26;
        this.discCardCode4 = str27;
        this.discCardCode5 = str28;
        this.discGuid1 = str29;
        this.discGuid2 = str30;
        this.discGuid3 = str31;
        this.discGuid4 = str32;
        this.discGuid5 = str33;
        this.custDiscGuid = str34;
        this.discCamp1 = str35;
        this.discCamp2 = str36;
        this.discCamp3 = str37;
        this.discCamp4 = str38;
        this.discCamp5 = str39;
        this.custDiscCamp = str40;
        this.docTracingNr = str41;
        this.isTransfer = i10;
        this.printCount = i11;
        this.ficheRef = i12;
        this.ficheNo = str42;
        this.total = d11;
        this.gTotal = d12;
        this.totalVat = d13;
        this.totalNet = d14;
        this.discTotal = d15;
        this.projectRef = i13;
        this.projectCode = str43;
        this.enlem = d16;
        this.boylam = d17;
        this.insType = i14;
        this.dateInt = i15;
        this.gDate = str44;
        this.andFicheNo = str45;
        this.setCampaign = i16;
        this.setSalesCondition = i17;
        this.salesType = i18;
        this.fact = i19;
        this.custDiscRatio = d18;
        this.isEdit = i20;
        this.totalExpenses = d19;
        this.canceled = i21;
        this.includeVat = i22;
        this.taxPayerCode = str46;
        this.taxPayerName = str47;
        this.eInvoiceSGKDocumentNo = str48;
        this.eInvoiceTypSGK = i23;
        this.beginDate = str49;
        this.endDate = str50;
        this.ficheStatus = i24;
        this.cabinRef = i25;
        this.speCode1 = str51;
        this.speCode2 = str52;
        this.trRate = d20;
        this.trCurr = i26;
        this.erpInvoiceType = i27;
        this.dueDate = str53;
        this.edocStatus = i28;
        this.campaignRefs = str54;
        this.returnWareHouseNr = i29;
    }
    public BaseDbSalesFiche(int i, int i2, String str, int i3, int i4, int i5, int i6, String str2, String str3, String str4, int i7, String str5, String str6, String str7, String str8, String str9, int i8, String str10, int i9, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, int i10, int i11, int i12, String str42, double d11, double d12, double d13, double d14, double d15, int i13, String str43, double d16, double d17, int i14, int i15, String str44, String str45, int i16, int i17, int i18, int i19, double d18, int i20, double d19, int i21, int i22, String str46, String str47, String str48, int i23, String str49, String str50, int i24, int i25, String str51, String str52, double d20, int i26, int i27, String str53, int i28, String str54, int i29, int i30, int i31, int i32, int i33, DefaultConstructorMarker defaultConstructorMarker) {
        this (0 != (i30 & 1) ? 0 : i, 0 != (i30 & 2) ? 0 : i2, 0 != (i30 & 4) ? null : str, 0 != (i30 & 8) ? 0 : i3, 0 != (i30 & 16) ? 0 : i4, 0 != (i30 & 32) ? 0 : i5, 0 != (i30 & 64) ? 0 : i6, 0 != (i30 & 128) ? null : str2, 0 != (i30 & 256) ? null : str3, 0 != (i30 & 512) ? null : str4, 0 != (i30 & 1024) ? 0 : i7, 0 != (i30 & 2048) ? null : str5, 0 != (i30 & 4096) ? null : str6, 0 != (i30 & 8192) ? null : str7, 0 != (i30 & 16384) ? null : str8, 0 != (i30 & 32768) ? null : str9, 0 != (i30 & 65536) ? 0 : i8, 0 != (i30 & 131072) ? null : str10, 0 != (i30 & 262144) ? 0 : i9, 0 != (i30 & 524288) ? null : str11, 0 != (i30 & 1048576) ? null : str12, 0 != (i30 & 2097152) ? null : str13, 0 != (i30 & 4194304) ? null : str14, 0 != (i30 & 8388608) ? null : str15, 0 != (i30 & 16777216) ? null : str16, 0 != (i30 & 33554432) ? null : str17, 0 != (i30 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str18, 0 != (i30 & 134217728) ? null : str19, 0 != (i30 & 268435456) ? null : str20, 0 != (i30 & 536870912) ? null : str21, 0 != (i30 & BasicMeasure.EXACTLY) ? null : str22, 0 != (i30 & Integer.MIN_VALUE) ? null : str23, 0 != (i31 & 1) ? 0.0d : d, 0 != (i31 & 2) ? 0.0d : d2, 0 != (i31 & 4) ? 0.0d : d3, 0 != (i31 & 8) ? 0.0d : d4, 0 != (i31 & 16) ? 0.0d : d5, 0 != (i31 & 32) ? 0.0d : d6, 0 != (i31 & 64) ? 0.0d : d7, 0 != (i31 & 128) ? 0.0d : d8, 0 != (i31 & 256) ? 0.0d : d9, 0 != (i31 & 512) ? 0.0d : d10, 0 != (i31 & 1024) ? null : str24, 0 != (i31 & 2048) ? null : str25, 0 != (i31 & 4096) ? null : str26, 0 != (i31 & 8192) ? null : str27, 0 != (i31 & 16384) ? null : str28, 0 != (i31 & 32768) ? null : str29, 0 != (i31 & 65536) ? null : str30, 0 != (i31 & 131072) ? null : str31, 0 != (i31 & 262144) ? null : str32, 0 != (i31 & 524288) ? null : str33, 0 != (i31 & 1048576) ? null : str34, 0 != (i31 & 2097152) ? null : str35, 0 != (i31 & 4194304) ? null : str36, 0 != (i31 & 8388608) ? null : str37, 0 != (i31 & 16777216) ? null : str38, 0 != (i31 & 33554432) ? null : str39, 0 != (i31 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str40, 0 != (i31 & 134217728) ? null : str41, 0 != (i31 & 268435456) ? 0 : i10, 0 != (i31 & 536870912) ? 0 : i11, 0 != (i31 & BasicMeasure.EXACTLY) ? 0 : i12, 0 != (i31 & Integer.MIN_VALUE) ? null : str42, 0 != (i32 & 1) ? 0.0d : d11, 0 != (i32 & 2) ? 0.0d : d12, 0 != (i32 & 4) ? 0.0d : d13, 0 != (i32 & 8) ? 0.0d : d14, 0 != (i32 & 16) ? 0.0d : d15, 0 != (i32 & 32) ? 0 : i13, 0 != (i32 & 64) ? null : str43, 0 != (i32 & 128) ? 0.0d : d16, 0 != (i32 & 256) ? 0.0d : d17, 0 != (i32 & 512) ? 0 : i14, 0 != (i32 & 1024) ? 0 : i15, 0 != (i32 & 2048) ? null : str44, 0 != (i32 & 4096) ? null : str45, 0 != (i32 & 8192) ? 0 : i16, 0 != (i32 & 16384) ? 0 : i17, 0 != (i32 & 32768) ? 0 : i18, 0 != (i32 & 65536) ? 0 : i19, 0 != (i32 & 131072) ? 0.0d : d18, 0 != (i32 & 262144) ? 0 : i20, 0 != (i32 & 524288) ? 0.0d : d19, 0 != (i32 & 1048576) ? 0 : i21, 0 != (i32 & 2097152) ? 0 : i22, 0 != (i32 & 4194304) ? null : str46, 0 != (i32 & 8388608) ? null : str47, 0 != (i32 & 16777216) ? null : str48, 0 != (i32 & 33554432) ? 0 : i23, 0 != (i32 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str49, 0 != (i32 & 134217728) ? null : str50, 0 != (i32 & 268435456) ? 0 : i24, 0 != (i32 & 536870912) ? 0 : i25, 0 != (i32 & BasicMeasure.EXACTLY) ? null : str51, 0 != (i32 & Integer.MIN_VALUE) ? null : str52, 0 == (i33 & 1) ? d20 : 0.0d, 0 != (i33 & 2) ? 0 : i26, 0 != (i33 & 4) ? 0 : i27, 0 != (i33 & 8) ? null : str53, 0 != (i33 & 16) ? 0 : i28, 0 != (i33 & 32) ? null : str54, 0 != (i33 & 64) ? 0 : i29);
    }
    protected BaseDbSalesFiche(Parcel parcel) {
        this (0, 0, null, 0, 0, 0, 0, null, null, null, 0, null, null, null, null, null, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, 0.0d, 0, 0, null, null, 0, 0, 0, 0, 0.0d, 0, 0.0d, 0, 0, null, null, null, 0, null, null, 0, 0, null, null, 0.0d, 0, 0, null, 0, null, 0, -1, -1, -1, 127, null);
        Intrinsics.checkNotNullParameter (parcel, "parcel");
        this.logicalRef = parcel.readInt ();
        this.clRef = parcel.readInt ();
        this.divisNr = parcel.readInt ();
        this.branchNr = parcel.readInt ();
        this.factNr = parcel.readInt ();
        this.wareHouseNr = parcel.readInt ();
        this.wareHouseName = parcel.readString ();
        this.returnWareHouseName = parcel.readString ();
        this.returnWareHouseNr = parcel.readInt ();
        this.ficheDate = parcel.readString ();
        this.paymentRef = parcel.readInt ();
        this.tradinggrp = parcel.readString ();
        this.docNo = parcel.readString ();
        this.specode = parcel.readString ();
        this.cyphcode = parcel.readString ();
        this.shipAccRef = parcel.readInt ();
        this.shipAddrRef = parcel.readInt ();
        this.shipType = parcel.readString ();
        this.shipAgent = parcel.readString ();
        this.desc1 = parcel.readString ();
        this.desc2 = parcel.readString ();
        this.desc3 = parcel.readString ();
        this.desc4 = parcel.readString ();
        this.desc5 = parcel.readString ();
        this.desc6 = parcel.readString ();
        this.desc7 = parcel.readString ();
        this.desc8 = parcel.readString ();
        this.desc9 = parcel.readString ();
        this.desc10 = parcel.readString ();
        this.discTotal1 = parcel.readDouble ();
        this.discRatio1 = parcel.readDouble ();
        this.discTotal2 = parcel.readDouble ();
        this.discRatio2 = parcel.readDouble ();
        this.discTotal3 = parcel.readDouble ();
        this.discRatio3 = parcel.readDouble ();
        this.discTotal4 = parcel.readDouble ();
        this.discRatio4 = parcel.readDouble ();
        this.discTotal5 = parcel.readDouble ();
        this.discRatio5 = parcel.readDouble ();
        this.discCardCode1 = parcel.readString ();
        this.discCardCode2 = parcel.readString ();
        this.discCardCode3 = parcel.readString ();
        this.discCardCode4 = parcel.readString ();
        this.discCardCode5 = parcel.readString ();
        this.discGuid1 = parcel.readString ();
        this.discGuid2 = parcel.readString ();
        this.discGuid3 = parcel.readString ();
        this.discGuid4 = parcel.readString ();
        this.discGuid5 = parcel.readString ();
        this.custDiscGuid = parcel.readString ();
        this.discCamp1 = parcel.readString ();
        this.discCamp2 = parcel.readString ();
        this.discCamp3 = parcel.readString ();
        this.discCamp4 = parcel.readString ();
        this.discCamp5 = parcel.readString ();
        this.custDiscCamp = parcel.readString ();
        this.docTracingNr = parcel.readString ();
        this.isTransfer = parcel.readInt ();
        this.printCount = parcel.readInt ();
        this.ficheRef = parcel.readInt ();
        this.ficheNo = parcel.readString ();
        this.total = parcel.readDouble ();
        this.gTotal = parcel.readDouble ();
        this.totalVat = parcel.readDouble ();
        this.totalNet = parcel.readDouble ();
        this.discTotal = parcel.readDouble ();
        this.projectRef = parcel.readInt ();
        this.enlem = parcel.readDouble ();
        this.boylam = parcel.readDouble ();
        this.insType = parcel.readInt ();
        this.dateInt = parcel.readInt ();
        this.gDate = parcel.readString ();
        this.andFicheNo = parcel.readString ();
        this.setCampaign = parcel.readInt ();
        this.setSalesCondition = parcel.readInt ();
        this.salesType = parcel.readInt ();
        this.fact = parcel.readInt ();
        this.custDiscRatio = parcel.readDouble ();
        this.isEdit = parcel.readInt ();
        this.totalExpenses = parcel.readDouble ();
        this.includeVat = parcel.readInt ();
        this.taxPayerName = parcel.readString ();
        this.taxPayerCode = parcel.readString ();
        this.eInvoiceTypSGK = parcel.readInt ();
        this.eInvoiceSGKDocumentNo = parcel.readString ();
        this.ficheStatus = parcel.readInt ();
        this.cabinRef = parcel.readInt ();
        this.speCode1 = parcel.readString ();
        this.speCode2 = parcel.readString ();
        this.trRate = parcel.readDouble ();
        this.trCurr = parcel.readInt ();
        this.erpInvoiceType = parcel.readInt ();
        this.dueDate = parcel.readString ();
        this.edocStatus = parcel.readInt ();
        this.campaignRefs = parcel.readString ();
    }
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter (parcel, "out");
        Companion.write (this, parcel, i);
    }
    public final int getClRef() {
        return this.clRef;
    }
    public final void setClRef(int i) {
        this.clRef = i;
    }
    public final int getDivisNr() {
        return this.divisNr;
    }
    public final void setDivisNr(int i) {
        this.divisNr = i;
    }
    public final int getBranchNr() {
        return this.branchNr;
    }
    public final void setBranchNr(int i) {
        this.branchNr = i;
    }
    public final int getFactNr() {
        return this.factNr;
    }
    public final void setFactNr(int i) {
        this.factNr = i;
    }
    public final int getWareHouseNr() {
        return this.wareHouseNr;
    }
    public final void setWareHouseNr(int i) {
        this.wareHouseNr = i;
    }
    public final String getWareHouseName() {
        return this.wareHouseName;
    }
    public final void setWareHouseName(String str) {
        this.wareHouseName = str;
    }
    public final String getReturnWareHouseName() {
        return this.returnWareHouseName;
    }
    public final void setReturnWareHouseName(String str) {
        this.returnWareHouseName = str;
    }
    public final String getFicheDate() {
        return this.ficheDate;
    }
    public final void setFicheDate(String str) {
        this.ficheDate = str;
    }
    public final int getPaymentRef() {
        return this.paymentRef;
    }
    public final void setPaymentRef(int i) {
        this.paymentRef = i;
    }
    public final String getPaymentCode() {
        return this.paymentCode;
    }
    public final void setPaymentCode(String str) {
        this.paymentCode = str;
    }
    public final String getTradinggrp() {
        return this.tradinggrp;
    }
    public final void setTradinggrp(String str) {
        this.tradinggrp = str;
    }
    public final String getDocNo() {
        return this.docNo;
    }
    public final void setDocNo(String str) {
        this.docNo = str;
    }
    public final String getSpecode() {
        return this.specode;
    }
    public final void setSpecode(String str) {
        this.specode = str;
    }
    public final String getCyphcode() {
        return this.cyphcode;
    }
    public final void setCyphcode(String str) {
        this.cyphcode = str;
    }
    public final int getShipAccRef() {
        return this.shipAccRef;
    }
    public final void setShipAccRef(int i) {
        this.shipAccRef = i;
    }
    public final int getShipAddrRef() {
        return this.shipAddrRef;
    }
    public final void setShipAddrRef(int i) {
        this.shipAddrRef = i;
    }
    public final String getShipType() {
        return this.shipType;
    }
    public final void setShipType(String str) {
        this.shipType = str;
    }
    public final String getShipAgent() {
        return this.shipAgent;
    }
    public final void setShipAgent(String str) {
        this.shipAgent = str;
    }
    public final String getDesc1() {
        return this.desc1;
    }
    public final void setDesc1(String str) {
        this.desc1 = str;
    }
    public final String getDesc2() {
        return this.desc2;
    }
    public final void setDesc2(String str) {
        this.desc2 = str;
    }
    public final String getDesc3() {
        return this.desc3;
    }
    public final void setDesc3(String str) {
        this.desc3 = str;
    }
    public final String getDesc4() {
        return this.desc4;
    }
    public final void setDesc4(String str) {
        this.desc4 = str;
    }
    public final String getDesc5() {
        return this.desc5;
    }
    public final void setDesc5(String str) {
        this.desc5 = str;
    }
    public final String getDesc6() {
        return this.desc6;
    }
    public final void setDesc6(String str) {
        this.desc6 = str;
    }
    public final String getDesc7() {
        return this.desc7;
    }
    public final void setDesc7(String str) {
        this.desc7 = str;
    }
    public final String getDesc8() {
        return this.desc8;
    }

    public final void setDesc8(String str) {
        this.desc8 = str;
    }

    public final String getDesc9() {
        return this.desc9;
    }

    public final void setDesc9(String str) {
        this.desc9 = str;
    }

    public final String getDesc10() {
        return this.desc10;
    }

    public final void setDesc10(String str) {
        this.desc10 = str;
    }

    public final double getDiscTotal1() {
        return this.discTotal1;
    }

    public final void setDiscTotal1(double d) {
        this.discTotal1 = d;
    }

    public final double getDiscRatio1() {
        return this.discRatio1;
    }

    public final void setDiscRatio1(double d) {
        this.discRatio1 = d;
    }

    public final double getDiscTotal2() {
        return this.discTotal2;
    }

    public final void setDiscTotal2(double d) {
        this.discTotal2 = d;
    }

    public final double getDiscRatio2() {
        return this.discRatio2;
    }

    public final void setDiscRatio2(double d) {
        this.discRatio2 = d;
    }

    public final double getDiscTotal3() {
        return this.discTotal3;
    }

    public final void setDiscTotal3(double d) {
        this.discTotal3 = d;
    }

    public final double getDiscRatio3() {
        return this.discRatio3;
    }

    public final void setDiscRatio3(double d) {
        this.discRatio3 = d;
    }

    public final double getDiscTotal4() {
        return this.discTotal4;
    }

    public final void setDiscTotal4(double d) {
        this.discTotal4 = d;
    }

    public final double getDiscRatio4() {
        return this.discRatio4;
    }

    public final void setDiscRatio4(double d) {
        this.discRatio4 = d;
    }

    public final double getDiscTotal5() {
        return this.discTotal5;
    }

    public final void setDiscTotal5(double d) {
        this.discTotal5 = d;
    }

    public final double getDiscRatio5() {
        return this.discRatio5;
    }

    public final void setDiscRatio5(double d) {
        this.discRatio5 = d;
    }

    public final String getDiscCardCode1() {
        return this.discCardCode1;
    }

    public final void setDiscCardCode1(String str) {
        this.discCardCode1 = str;
    }

    public final String getDiscCardCode2() {
        return this.discCardCode2;
    }

    public final void setDiscCardCode2(String str) {
        this.discCardCode2 = str;
    }

    public final String getDiscCardCode3() {
        return this.discCardCode3;
    }

    public final void setDiscCardCode3(String str) {
        this.discCardCode3 = str;
    }

    public final String getDiscCardCode4() {
        return this.discCardCode4;
    }

    public final void setDiscCardCode4(String str) {
        this.discCardCode4 = str;
    }

    public final String getDiscCardCode5() {
        return this.discCardCode5;
    }

    public final void setDiscCardCode5(String str) {
        this.discCardCode5 = str;
    }

    public final String getDiscGuid1() {
        return this.discGuid1;
    }

    public final void setDiscGuid1(String str) {
        this.discGuid1 = str;
    }

    public final String getDiscGuid2() {
        return this.discGuid2;
    }

    public final void setDiscGuid2(String str) {
        this.discGuid2 = str;
    }

    public final String getDiscGuid3() {
        return this.discGuid3;
    }

    public final void setDiscGuid3(String str) {
        this.discGuid3 = str;
    }

    public final String getDiscGuid4() {
        return this.discGuid4;
    }

    public final void setDiscGuid4(String str) {
        this.discGuid4 = str;
    }

    public final String getDiscGuid5() {
        return this.discGuid5;
    }

    public final void setDiscGuid5(String str) {
        this.discGuid5 = str;
    }

    public final String getCustDiscGuid() {
        return this.custDiscGuid;
    }

    public final void setCustDiscGuid(String str) {
        this.custDiscGuid = str;
    }

    public final String getDiscCamp1() {
        return this.discCamp1;
    }

    public final void setDiscCamp1(String str) {
        this.discCamp1 = str;
    }

    public final String getDiscCamp2() {
        return this.discCamp2;
    }

    public final void setDiscCamp2(String str) {
        this.discCamp2 = str;
    }

    public final String getDiscCamp3() {
        return this.discCamp3;
    }

    public final void setDiscCamp3(String str) {
        this.discCamp3 = str;
    }

    public final String getDiscCamp4() {
        return this.discCamp4;
    }

    public final void setDiscCamp4(String str) {
        this.discCamp4 = str;
    }

    public final String getDiscCamp5() {
        return this.discCamp5;
    }

    public final void setDiscCamp5(String str) {
        this.discCamp5 = str;
    }

    public final String getCustDiscCamp() {
        return this.custDiscCamp;
    }

    public final void setCustDiscCamp(String str) {
        this.custDiscCamp = str;
    }

    public final String getDocTracingNr() {
        return this.docTracingNr;
    }

    public final void setDocTracingNr(String str) {
        this.docTracingNr = str;
    }

    public final int getPrintCount() {
        return this.printCount;
    }

    public final void setPrintCount(int i) {
        this.printCount = i;
    }

    public final int getFicheRef() {
        return this.ficheRef;
    }

    public final void setFicheRef(int i) {
        this.ficheRef = i;
    }

    public final double getTotal() {
        return this.total;
    }

    public final void setTotal(double d) {
        this.total = d;
    }

    public final double getGTotal() {
        return this.gTotal;
    }

    public final void setGTotal(double d) {
        this.gTotal = d;
    }

    public final double getTotalVat() {
        return this.totalVat;
    }

    public final void setTotalVat(double d) {
        this.totalVat = d;
    }

    public final double getTotalNet() {
        return this.totalNet;
    }

    public final void setTotalNet(double d) {
        this.totalNet = d;
    }

    public final double getDiscTotal() {
        return this.discTotal;
    }

    public final void setDiscTotal(double d) {
        this.discTotal = d;
    }

    public final int getProjectRef() {
        return this.projectRef;
    }

    public final void setProjectRef(int i) {
        this.projectRef = i;
    }

    public final String getProjectCode() {
        return this.projectCode;
    }

    public final void setProjectCode(String str) {
        this.projectCode = str;
    }

    public final double getEnlem() {
        return this.enlem;
    }

    public final void setEnlem(double d) {
        this.enlem = d;
    }

    public final double getBoylam() {
        return this.boylam;
    }

    public final void setBoylam(double d) {
        this.boylam = d;
    }

    public final int getInsType() {
        return this.insType;
    }

    public final void setInsType(int i) {
        this.insType = i;
    }

    public final int getDateInt() {
        return this.dateInt;
    }

    public final void setDateInt(int i) {
        this.dateInt = i;
    }

    public final String getGDate() {
        return this.gDate;
    }

    public final void setGDate(String str) {
        this.gDate = str;
    }

    public final String getAndFicheNo() {
        return this.andFicheNo;
    }

    public final void setAndFicheNo(String str) {
        this.andFicheNo = str;
    }

    public final int getSetCampaign() {
        return this.setCampaign;
    }

    public final void setSetCampaign(int i) {
        this.setCampaign = i;
    }

    public final int getSetSalesCondition() {
        return this.setSalesCondition;
    }

    public final void setSetSalesCondition(int i) {
        this.setSalesCondition = i;
    }

    public final double getCustDiscRatio() {
        return this.custDiscRatio;
    }

    public final void setCustDiscRatio(double d) {
        this.custDiscRatio = d;
    }

    public final int isEdit() {
        return this.isEdit;
    }

    public final void setEdit(int i) {
        this.isEdit = i;
    }

    public final double getTotalExpenses() {
        return this.totalExpenses;
    }

    public final void setTotalExpenses(double d) {
        this.totalExpenses = d;
    }

    public final int getCanceled() {
        return this.canceled;
    }

    public final void setCanceled(int i) {
        this.canceled = i;
    }

    public final int getIncludeVat() {
        return this.includeVat;
    }

    public final void setIncludeVat(int i) {
        this.includeVat = i;
    }

    public final String getBeginDate() {
        return this.beginDate;
    }

    public final void setBeginDate(String str) {
        this.beginDate = str;
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public final void setEndDate(String str) {
        this.endDate = str;
    }

    public final int getCabinRef() {
        return this.cabinRef;
    }

    public final void setCabinRef(int i) {
        this.cabinRef = i;
    }

    public final String getSpeCode1() {
        return this.speCode1;
    }

    public final void setSpeCode1(String str) {
        this.speCode1 = str;
    }

    public final String getSpeCode2() {
        return this.speCode2;
    }

    public final void setSpeCode2(String str) {
        this.speCode2 = str;
    }

    public final double getTrRate() {
        return this.trRate;
    }

    public final void setTrRate(double d) {
        this.trRate = d;
    }

    public final int getTrCurr() {
        return this.trCurr;
    }

    public final void setTrCurr(int i) {
        this.trCurr = i;
    }

    public final int getErpInvoiceType() {
        return this.erpInvoiceType;
    }

    public final void setErpInvoiceType(int i) {
        this.erpInvoiceType = i;
    }

    public final String getDueDate() {
        return this.dueDate;
    }

    public final void setDueDate(String str) {
        this.dueDate = str;
    }

    public final int getEdocStatus() {
        return this.edocStatus;
    }

    public final void setEdocStatus(int i) {
        this.edocStatus = i;
    }

    public final String getCampaignRefs() {
        return this.campaignRefs;
    }

    public final void setCampaignRefs(String str) {
        this.campaignRefs = str;
    }

    public final int getReturnWareHouseNr() {
        return this.returnWareHouseNr;
    }

    public final void setReturnWareHouseNr(int i) {
        this.returnWareHouseNr = i;
    }

    public void convertSales(Sales sales) {
        int i;
        Intrinsics.checkNotNullParameter (sales, "sales");
        this.logicalRef = sales.getLogicalRef ();
        this.salesType = sales.getMSalesType ();
        this.clRef = sales.getClRef ();
        this.clCode = sales.getClCode ();
        this.andFicheNo = sales.getAndFicheNo ();
        this.ficheNo = sales.getFicheNo ();
        this.ficheDate = sales.getFicheCreateDate ();
        this.isTransfer = sales.getTransferCount ();
        this.printCount = sales.getPrintCount ();
        this.dateInt = sales.getFicheCreateDateInt ();
        this.gDate = sales.getGDate ();
        this.divisNr = sales.getDivision ().getLogicalRef ();
        this.branchNr = sales.getBranch ().getLogicalRef ();
        this.factNr = sales.getFactory ().getLogicalRef ();
        this.wareHouseNr = sales.getWareHouse ().getLogicalRef ();
        this.wareHouseName = sales.getWareHouse ().getDefinition ();
        this.returnWareHouseName = sales.getReturnWareHouse ().getDefinition ();
        this.returnWareHouseNr = sales.getReturnWareHouse ().getLogicalRef ();
        this.paymentRef = sales.getPayPlan ().getLogicalRef ();
        this.paymentCode = sales.getPayPlan ().getCode ();
        this.tradinggrp = sales.getTradeGroup ().toString ();
        this.docNo = sales.getDocumentNo ().toString ();
        this.specode = sales.getSpeCode ().toString ();
        this.cyphcode = sales.getWarrantyCode ().toString ();
        this.shipAccRef = sales.getShipAccount ().getLogicalRef ();
        this.shipAccCode = sales.getShipAccount ().getCode ();
        this.shipAddrRef = sales.getShipAddress ().getLogicalRef ();
        this.shipAddrCode = sales.getShipAddress ().getCode ();
        this.shipType = sales.getShipType ().toString ();
        this.shipAgent = sales.getShipAgent ().toString ();
        this.docTracingNr = sales.getDocumentTrackingNo ().toString ();
        this.projectRef = sales.getProjectCode ().getLogicalRef ();
        this.projectCode = sales.getProjectCode ().getCode ();
        this.setCampaign = sales.getCampaign ();
        this.setSalesCondition = sales.getSalesCondition ();
        this.desc1 = sales.getExplanation1 ().toString ();
        this.desc2 = sales.getExplanation2 ().toString ();
        this.desc3 = sales.getExplanation3 ().toString ();
        this.desc4 = sales.getExplanation4 ().toString ();
        this.desc5 = sales.getExplanation5 ().toString ();
        this.desc6 = sales.getExplanation6 ().toString ();
        this.desc7 = sales.getExplanation7 ().toString ();
        this.desc8 = sales.getExplanation8 ().toString ();
        this.desc9 = sales.getExplanation9 ().toString ();
        this.desc10 = sales.getExplanation10 ().toString ();
        this.enlem = sales.getLatitude ();
        this.boylam = sales.getLongitude ();
        this.discRatio1 = sales.getDiscountRatio (0).getDefinitionDouble ();
        this.discTotal1 = sales.getDiscountTotal (0).getDefinitionDouble ();
        this.discCardCode1 = sales.getDiscountCard (0).getCode ();
        this.discGuid1 = sales.getDiscountGuid (0);
        this.discCamp1 = sales.getDiscountCampaign (0);
        this.discRatio2 = sales.getDiscountRatio (1).getDefinitionDouble ();
        this.discTotal2 = sales.getDiscountTotal (1).getDefinitionDouble ();
        this.discCardCode2 = sales.getDiscountCard (1).getCode ();
        this.discGuid2 = sales.getDiscountGuid (1);
        this.discCamp2 = sales.getDiscountCampaign (1);
        this.discRatio3 = sales.getDiscountRatio (2).getDefinitionDouble ();
        this.discTotal3 = sales.getDiscountTotal (2).getDefinitionDouble ();
        this.discCardCode3 = sales.getDiscountCard (2).getCode ();
        this.discGuid3 = sales.getDiscountGuid (2);
        this.discCamp3 = sales.getDiscountCampaign (2);
        this.discRatio4 = sales.getDiscountRatio (3).getDefinitionDouble ();
        this.discTotal4 = sales.getDiscountTotal (3).getDefinitionDouble ();
        this.discCardCode4 = sales.getDiscountCard (3).getCode ();
        this.discGuid4 = sales.getDiscountGuid (3);
        this.discCamp4 = sales.getDiscountCampaign (3);
        this.discRatio5 = sales.getDiscountRatio (4).getDefinitionDouble ();
        this.discTotal5 = sales.getDiscountTotal (4).getDefinitionDouble ();
        this.discCardCode5 = sales.getDiscountCard (4).getCode ();
        this.discGuid5 = sales.getDiscountGuid (4);
        this.discCamp5 = sales.getDiscountCampaign (4);
        this.custDiscRatio = sales.getCustomerDiscRatio ();
        this.custDiscGuid = sales.getCustomerDiscGuid ();
        this.custDiscCamp = sales.getCustomerCampaignCode ();
        this.total = sales.getTotal ();
        this.totalNet = sales.getTotalNet ();
        this.totalVat = sales.getTotalVat ();
        this.discTotal = sales.getDiscTotal ();
        this.gTotal = sales.getGrossTotal ();
        this.isEdit = sales.isEdit ();
        this.ficheRef = sales.getFicheRef ();
        this.totalExpenses = sales.getTotalExpenses ();
        this.includeVat = sales.getIncludeVat ().isSelect () ? 1 : 0;
        this.eInvoiceSGKDocumentNo = sales.getEInvoiceSGKDocumentNo ().toString ();
        this.taxPayerCode = sales.getTaxPayerCode ().toString ();
        this.taxPayerName = sales.getTaxPayerName ().toString ();
        this.eInvoiceTypSGK = sales.getEInvoiceTypSgk ().getLogicalRef ();
        this.beginDate = sales.getBeginDate ().toString ();
        this.endDate = sales.getEndDate ().toString ();
        this.ficheStatus = sales.getSalesStatus ();
        this.cabinRef = sales.getCabin ().getLogicalRef ();
        this.speCode1 = sales.getFirstSpeCode ().getCode ();
        this.speCode2 = sales.getSecondSpeCode ().getCode ();
        this.trRate = sales.getTrRate ();
        this.trCurr = sales.getTrCurr ();
        if (null != sales.getErpInvoiceType ()) {
            FicheRefProp erpInvoiceType = sales.getErpInvoiceType ();
            Intrinsics.checkNotNull (erpInvoiceType);
            i = erpInvoiceType.getLogicalRef ();
        } else {
            i = -1;
        }
        this.erpInvoiceType = i;
        this.dueDate = sales.getDueDate ().toString ();
        this.campaignRefs = sales.getCampaingRefs ();
    }

    public Sales convertSalesFicheToSales() {
        Sales sales = new Sales (0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
        sales.setMSalesType (this.salesType);
        sales.setLogicalRef (this.logicalRef);
        sales.setClRef (this.clRef);
        String str = this.clCode;
        if (null == str) {
            str = "";
        }
        sales.setClCode (str);
        sales.setFicheNo (this.ficheNo);
        sales.setDivision (new FicheRefProp (this.divisNr, -1, ""));
        sales.setBranch (new FicheRefProp (this.branchNr, -1, ""));
        sales.setFactory (new FicheRefProp (this.factNr, -1, ""));
        sales.setWareHouse (new FicheRefProp (this.wareHouseNr, -1, ""));
        FicheStringProp.setDefinition(this.wareHouseName);
        FicheStringProp.setDefinition(this.returnWareHouseName);
        sales.setReturnWareHouse (new FicheRefProp (this.returnWareHouseNr, -1, ""));
        sales.setFicheCreateDate (this.ficheDate);
        sales.setPayPlan (new FicheDiscountRefProp (this.paymentRef, -1, "", this.paymentCode));
        sales.setTradeGroup (new FicheRefProp (-1, -1, this.tradinggrp));
        sales.setDocumentNo (new FicheStringProp (this.docNo));
        sales.setSpeCode (new FicheRefProp (-1, -1, this.specode));
        sales.setWarrantyCode (new FicheRefProp (-1, -1, this.cyphcode));
        sales.setShipAccount (new FicheDiscountRefProp (this.shipAccRef, -1, "", this.shipAccCode));
        sales.setShipAddress (new FicheDiscountRefProp (this.shipAddrRef, -1, "", this.shipAddrCode));
        sales.setShipType (new FicheRefProp (-1, -1, this.shipType));
        sales.setShipAgent (new FicheRefProp (-1, -1, this.shipAgent));
        sales.setExplanation1 (new FicheStringProp (this.desc1));
        sales.setExplanation2 (new FicheStringProp (this.desc2));
        sales.setExplanation3 (new FicheStringProp (this.desc3));
        sales.setExplanation4 (new FicheStringProp (this.desc4));
        sales.setExplanation5 (new FicheStringProp (this.desc5));
        sales.setExplanation6 (new FicheStringProp (this.desc6));
        sales.setExplanation7 (new FicheStringProp (this.desc7));
        sales.setExplanation8 (new FicheStringProp (this.desc8));
        sales.setExplanation9 (new FicheStringProp (this.desc9));
        sales.setExplanation10 (new FicheStringProp (this.desc10));
        boolean z = false;
        sales.getDiscountTotal(0);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discTotal1)));
        sales.getDiscountRatio(0);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discRatio1)));
        sales.getDiscountCard (0).setCode (this.discCardCode1);
        if (!TextUtils.isEmpty (this.discCardCode1) && 0.0d != discRatio1) {
            sales.getDiscountRatio (0).setBoundedToCard (true);
        }
        sales.setDiscountGuid (0, this.discGuid1);
        sales.setDiscountCampaign (0, this.discCamp1);
        sales.getDiscountTotal(1);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discTotal2)));
        sales.getDiscountRatio(1);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discRatio2)));
        sales.getDiscountCard (1).setCode (this.discCardCode2);
        if (!TextUtils.isEmpty (this.discCardCode2) && 0.0d != discRatio2) {
            sales.getDiscountRatio (1).setBoundedToCard (true);
        }
        sales.setDiscountGuid (1, this.discGuid2);
        sales.setDiscountCampaign (1, this.discCamp2);
        sales.getDiscountTotal(2);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discTotal3)));
        sales.getDiscountRatio(2);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discRatio3)));
        sales.getDiscountCard (2).setCode (this.discCardCode3);
        if (!TextUtils.isEmpty (this.discCardCode3) && 0.0d != discRatio3) {
            sales.getDiscountRatio (2).setBoundedToCard (true);
        }
        sales.setDiscountGuid (2, this.discGuid3);
        sales.setDiscountCampaign (2, this.discCamp3);
        sales.getDiscountTotal(3);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discTotal4)));
        sales.getDiscountRatio(3);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discRatio4)));
        sales.getDiscountCard (3).setCode (this.discCardCode4);
        if (!TextUtils.isEmpty (this.discCardCode4) && 0.0d != discRatio4) {
            sales.getDiscountRatio (3).setBoundedToCard (true);
        }
        sales.setDiscountGuid (3, this.discGuid4);
        sales.setDiscountCampaign (3, this.discCamp4);
        sales.getDiscountTotal(4);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discTotal5)));
        sales.getDiscountRatio(4);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString (Double.valueOf (this.discRatio5)));
        sales.getDiscountCard (4).setCode (this.discCardCode5);
        if (!TextUtils.isEmpty (this.discCardCode5) && 0.0d != discRatio5) {
            sales.getDiscountRatio (4).setBoundedToCard (true);
        }
        sales.setDiscountGuid (4, this.discGuid5);
        sales.setDiscountCampaign (4, this.discCamp5);
        sales.setCustomerDiscRatio (this.custDiscRatio);
        sales.setCustomerDiscGuid (this.custDiscGuid);
        sales.setCustomerCampaignCode (this.custDiscCamp);
        sales.setDocumentTrackingNo (new FicheStringProp (this.docTracingNr));
        sales.setTransferCount (this.isTransfer);
        sales.setPrintCount (this.printCount);
        sales.setFicheRef (this.ficheRef);
        sales.setTotal (this.total);
        sales.setGrossTotal (this.gTotal);
        sales.setTotalVat (this.totalVat);
        sales.setTotalNet (this.totalNet);
        sales.setDiscTotal (this.discTotal);
        sales.setProjectCode (new FicheDiscountRefProp (this.projectRef, -1, "", this.projectCode));
        sales.setFicheCreateDateInt (this.dateInt);
        sales.setGDate (this.gDate);
        sales.setAndFicheNo (this.andFicheNo);
        sales.setCampaign (this.setCampaign);
        sales.setSalesCondition (this.setSalesCondition);
        sales.setLatitude (this.enlem);
        sales.setLongitude (this.boylam);
        sales.setDeliveryDate (new FicheDateProp ());
        sales.setEdit (this.isEdit);
        sales.setTotalExpenses (this.totalExpenses);
        if (1 == includeVat) {
            z = true;
        }
        sales.setIncludeVat (new FicheBooleanProp (z));
        sales.setTaxPayerName (new FicheStringProp (this.taxPayerName));
        sales.setTaxPayerCode (new FicheStringProp (this.taxPayerCode));
        sales.setEInvoiceSGKDocumentNo (new FicheStringProp (this.eInvoiceSGKDocumentNo));
        sales.setEInvoiceTypSgk (new FicheRefProp (this.eInvoiceTypSGK, -1, ""));
        sales.setBeginDate (new FicheDateProp (this.beginDate));
        sales.setEndDate (new FicheDateProp (this.endDate));
        sales.setSalesStatus (this.ficheStatus);
        sales.setCabin (new FicheDiscountRefProp (this.cabinRef, -1, "", ""));
        sales.setFirstSpeCode (new FicheDiscountRefProp (-1, -1, "", this.speCode1));
        sales.setSecondSpeCode (new FicheDiscountRefProp (-1, -1, "", this.speCode2));
        sales.setTrCurr (this.trCurr);
        sales.setTrRate (this.trRate);
        sales.setErpInvoiceType (new FicheRefProp (this.erpInvoiceType, -1, ""));
        sales.setDueDate (new FicheDateProp (this.dueDate));
        sales.setCampaingRefs (this.campaignRefs);
        return sales;
    }

    public static final class Creator implements Parcelable.Creator<BaseDbSalesFiche> {
        public BaseDbSalesFiche createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter (parcel, "parcel");
            return BaseDbSalesFiche.Companion.create (parcel);
        }

        public BaseDbSalesFiche[] newArray(int i) {
            return new BaseDbSalesFiche[i];
        }
    }

    public static final class Companion implements Parceler<BaseDbSalesFiche> {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private Companion() {
        }
        public BaseDbSalesFiche  [] newArray() {
            return DefaultImpls.newArray();
        }
        public void write(BaseDbSalesFiche baseDbSalesFiche, Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter (baseDbSalesFiche, "<this>");
            Intrinsics.checkNotNullParameter (parcel, "dest");
            parcel.writeInt (baseDbSalesFiche.logicalRef);
            parcel.writeInt (baseDbSalesFiche.getClRef ());
            parcel.writeInt (baseDbSalesFiche.getDivisNr ());
            parcel.writeInt (baseDbSalesFiche.getBranchNr ());
            parcel.writeInt (baseDbSalesFiche.getFactNr ());
            parcel.writeInt (baseDbSalesFiche.getWareHouseNr ());
            parcel.writeString (baseDbSalesFiche.getWareHouseName ());
            parcel.writeString (baseDbSalesFiche.getReturnWareHouseName ());
            parcel.writeInt (baseDbSalesFiche.getReturnWareHouseNr ());
            parcel.writeString (baseDbSalesFiche.getFicheDate ());
            parcel.writeInt (baseDbSalesFiche.getPaymentRef ());
            parcel.writeString (baseDbSalesFiche.getTradinggrp ());
            parcel.writeString (baseDbSalesFiche.getDocNo ());
            parcel.writeString (baseDbSalesFiche.getSpecode ());
            parcel.writeString (baseDbSalesFiche.getCyphcode ());
            parcel.writeInt (baseDbSalesFiche.getShipAccRef ());
            parcel.writeInt (baseDbSalesFiche.getShipAddrRef ());
            parcel.writeString (baseDbSalesFiche.getShipType ());
            parcel.writeString (baseDbSalesFiche.getShipAgent ());
            parcel.writeString (baseDbSalesFiche.getDesc1 ());
            parcel.writeString (baseDbSalesFiche.getDesc2 ());
            parcel.writeString (baseDbSalesFiche.getDesc3 ());
            parcel.writeString (baseDbSalesFiche.getDesc4 ());
            parcel.writeString (baseDbSalesFiche.getDesc5 ());
            parcel.writeString (baseDbSalesFiche.getDesc6 ());
            parcel.writeString (baseDbSalesFiche.getDesc7 ());
            parcel.writeString (baseDbSalesFiche.getDesc8 ());
            parcel.writeString (baseDbSalesFiche.getDesc9 ());
            parcel.writeString (baseDbSalesFiche.getDesc10 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal1 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscRatio1 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal2 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscRatio2 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal3 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscRatio3 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal4 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscRatio4 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal5 ());
            parcel.writeDouble (baseDbSalesFiche.getDiscRatio5 ());
            parcel.writeString (baseDbSalesFiche.getDiscCardCode1 ());
            parcel.writeString (baseDbSalesFiche.getDiscCardCode2 ());
            parcel.writeString (baseDbSalesFiche.getDiscCardCode3 ());
            parcel.writeString (baseDbSalesFiche.getDiscCardCode4 ());
            parcel.writeString (baseDbSalesFiche.getDiscCardCode5 ());
            parcel.writeString (baseDbSalesFiche.getDiscGuid1 ());
            parcel.writeString (baseDbSalesFiche.getDiscGuid2 ());
            parcel.writeString (baseDbSalesFiche.getDiscGuid3 ());
            parcel.writeString (baseDbSalesFiche.getDiscGuid4 ());
            parcel.writeString (baseDbSalesFiche.getDiscGuid5 ());
            parcel.writeString (baseDbSalesFiche.getCustDiscGuid ());
            parcel.writeString (baseDbSalesFiche.getDiscCamp1 ());
            parcel.writeString (baseDbSalesFiche.getDiscCamp2 ());
            parcel.writeString (baseDbSalesFiche.getDiscCamp3 ());
            parcel.writeString (baseDbSalesFiche.getDiscCamp4 ());
            parcel.writeString (baseDbSalesFiche.getDiscCamp5 ());
            parcel.writeString (baseDbSalesFiche.getCustDiscCamp ());
            parcel.writeString (baseDbSalesFiche.getDocTracingNr ());
            parcel.writeInt (baseDbSalesFiche.isTransfer);
            parcel.writeInt (baseDbSalesFiche.getPrintCount ());
            parcel.writeInt (baseDbSalesFiche.getFicheRef ());
            parcel.writeString (baseDbSalesFiche.ficheNo);
            parcel.writeDouble (baseDbSalesFiche.getTotal ());
            parcel.writeDouble (baseDbSalesFiche.getGTotal ());
            parcel.writeDouble (baseDbSalesFiche.getTotalVat ());
            parcel.writeDouble (baseDbSalesFiche.getTotalNet ());
            parcel.writeDouble (baseDbSalesFiche.getDiscTotal ());
            parcel.writeInt (baseDbSalesFiche.getProjectRef ());
            parcel.writeDouble (baseDbSalesFiche.getEnlem ());
            parcel.writeDouble (baseDbSalesFiche.getBoylam ());
            parcel.writeInt (baseDbSalesFiche.getInsType ());
            parcel.writeInt (baseDbSalesFiche.getDateInt ());
            parcel.writeString (baseDbSalesFiche.getGDate ());
            parcel.writeString (baseDbSalesFiche.getAndFicheNo ());
            parcel.writeInt (baseDbSalesFiche.getSetCampaign ());
            parcel.writeInt (baseDbSalesFiche.getSetSalesCondition ());
            parcel.writeInt (baseDbSalesFiche.salesType);
            parcel.writeInt (baseDbSalesFiche.fact);
            parcel.writeDouble (baseDbSalesFiche.getCustDiscRatio ());
            parcel.writeInt (baseDbSalesFiche.isEdit ());
            parcel.writeDouble (baseDbSalesFiche.getTotalExpenses ());
            parcel.writeInt (baseDbSalesFiche.getIncludeVat ());
            parcel.writeString (baseDbSalesFiche.taxPayerCode);
            parcel.writeString (baseDbSalesFiche.taxPayerName);
            parcel.writeString (baseDbSalesFiche.eInvoiceSGKDocumentNo);
            parcel.writeInt (baseDbSalesFiche.eInvoiceTypSGK);
            parcel.writeInt (baseDbSalesFiche.ficheStatus);
            parcel.writeInt (baseDbSalesFiche.getCabinRef ());
            parcel.writeString (baseDbSalesFiche.getSpeCode1 ());
            parcel.writeString (baseDbSalesFiche.getSpeCode2 ());
            parcel.writeDouble (baseDbSalesFiche.getTrRate ());
            parcel.writeInt (baseDbSalesFiche.getTrCurr ());
            parcel.writeInt (baseDbSalesFiche.getErpInvoiceType ());
            parcel.writeString (baseDbSalesFiche.getDueDate ());
            parcel.writeInt (baseDbSalesFiche.getEdocStatus ());
            parcel.writeString (baseDbSalesFiche.getCampaignRefs ());
        }

        public BaseDbSalesFiche create(Parcel parcel) {
            Intrinsics.checkNotNullParameter (parcel, "parcel");
            return new BaseDbSalesFiche (parcel);
        }
    }
}