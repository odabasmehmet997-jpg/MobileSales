package com.proje.mobilesales.features.collections.cashandcreditcardfiche.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashCreditFiche.kt */

public final class CashCreditFiche implements Parcelable {
    private FicheDiscountRefProp aggrCode;
    private String andFicheNo;
    private FicheDiscountRefProp bank;
    private FicheDiscountRefProp bankAcc;
    private double boylam;
    private FicheRefProp branch;
    private String clCode;
    private int clRef;
    private FicheRefProp cyphcode;
    private int dateInt;
    private ArrayList<CashCreditFicheDetail> details;
    private FicheRefProp division;
    private double enlem;
    private FicheStringProp explanation1;
    private FicheStringProp explanation2;
    private FicheStringProp explanation3;
    private FicheStringProp explanation4;
    private int fType;
    private FicheRefProp factory;
    private String ficheNo;
    private int ficheref;
    private String gDate;
    private FicheStringProp installmentCount;
    private int invoiceRef;
    private int isTransfer;
    private int logicalRef;
    private int printCount;
    private FicheRefProp projectCode;
    private FicheRefProp specode;
    private double total;
    private FicheRefProp tradinggrp;
    private int visitInfoId;
    public static final Companion Companion = new Companion(null);
    private static final Parcelable.Creator<CashCreditFiche> CREATOR = new Parcelable.Creator<CashCreditFiche>() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheCompanionCREATOR1
        
        
        public CashCreditFiche createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CashCreditFiche(source);
        }

        
        
        public CashCreditFiche[] newArray(final int i2) {
            return new CashCreditFiche[i2];
        }
    };

    public CashCreditFiche() {
        this(0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, 0, 0, null, null, null, -1, null);
    }

    public static CashCreditFiche copydefault(final CashCreditFiche cashCreditFiche, final int i2, final int i3, final String str, final int i4, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final FicheRefProp ficheRefProp3, final FicheRefProp ficheRefProp4, final FicheRefProp ficheRefProp5, final FicheRefProp ficheRefProp6, final FicheRefProp ficheRefProp7, final FicheDiscountRefProp ficheDiscountRefProp, final FicheDiscountRefProp ficheDiscountRefProp2, final FicheDiscountRefProp ficheDiscountRefProp3, final FicheStringProp ficheStringProp, final FicheStringProp ficheStringProp2, final FicheStringProp ficheStringProp3, final FicheStringProp ficheStringProp4, final int i5, final double d2, final int i6, final int i7, final String str2, final double d3, final double d4, final int i8, final String str3, final int i9, final int i10, final String str4, final FicheStringProp ficheStringProp5, final ArrayList arrayList, final int i11, final Object obj) {
        final int i12 = 0 != (i11 & 1) ? cashCreditFiche.logicalRef : i2;
        final int i13 = 0 != (i11 & 2) ? cashCreditFiche.clRef : i3;
        final String str5 = 0 != (i11 & 4) ? cashCreditFiche.clCode : str;
        final int i14 = 0 != (i11 & 8) ? cashCreditFiche.fType : i4;
        final FicheRefProp ficheRefProp8 = 0 != (i11 & 16) ? cashCreditFiche.branch : ficheRefProp;
        final FicheRefProp ficheRefProp9 = 0 != (i11 & 32) ? cashCreditFiche.division : ficheRefProp2;
        final FicheRefProp ficheRefProp10 = 0 != (i11 & 64) ? cashCreditFiche.factory : ficheRefProp3;
        final FicheRefProp ficheRefProp11 = 0 != (i11 & 128) ? cashCreditFiche.tradinggrp : ficheRefProp4;
        final FicheRefProp ficheRefProp12 = 0 != (i11 & 256) ? cashCreditFiche.specode : ficheRefProp5;
        final FicheRefProp ficheRefProp13 = 0 != (i11 & 512) ? cashCreditFiche.cyphcode : ficheRefProp6;
        final FicheRefProp ficheRefProp14 = 0 != (i11 & 1024) ? cashCreditFiche.projectCode : ficheRefProp7;
        final FicheDiscountRefProp ficheDiscountRefProp4 = 0 != (i11 & 2048) ? cashCreditFiche.aggrCode : ficheDiscountRefProp;
        final FicheDiscountRefProp ficheDiscountRefProp5 = 0 != (i11 & 4096) ? cashCreditFiche.bank : ficheDiscountRefProp2;
        return cashCreditFiche.copy(i12, i13, str5, i14, ficheRefProp8, ficheRefProp9, ficheRefProp10, ficheRefProp11, ficheRefProp12, ficheRefProp13, ficheRefProp14, ficheDiscountRefProp4, ficheDiscountRefProp5, 0 != (i11 & 8192) ? cashCreditFiche.bankAcc : ficheDiscountRefProp3, 0 != (i11 & 16384) ? cashCreditFiche.explanation1 : ficheStringProp, 0 != (i11 & 32768) ? cashCreditFiche.explanation2 : ficheStringProp2, 0 != (i11 & 65536) ? cashCreditFiche.explanation3 : ficheStringProp3, 0 != (i11 & 131072) ? cashCreditFiche.explanation4 : ficheStringProp4, 0 != (i11 & 262144) ? cashCreditFiche.isTransfer : i5, 0 != (i11 & 524288) ? cashCreditFiche.total : d2, 0 != (i11 & 1048576) ? cashCreditFiche.printCount : i6, 0 != (2097152 & i11) ? cashCreditFiche.ficheref : i7, 0 != (i11 & 4194304) ? cashCreditFiche.ficheNo : str2, 0 != (i11 & 8388608) ? cashCreditFiche.enlem : d3, 0 != (i11 & 16777216) ? cashCreditFiche.boylam : d4, 0 != (i11 & 33554432) ? cashCreditFiche.dateInt : i8, 0 != (67108864 & i11) ? cashCreditFiche.gDate : str3, 0 != (i11 & 134217728) ? cashCreditFiche.invoiceRef : i9, 0 != (i11 & 268435456) ? cashCreditFiche.visitInfoId : i10, 0 != (i11 & 536870912) ? cashCreditFiche.andFicheNo : str4, 0 != (i11 & BasicMeasure.EXACTLY) ? cashCreditFiche.installmentCount : ficheStringProp5, 0 != (i11 & Integer.MIN_VALUE) ? cashCreditFiche.details : arrayList);
    }

    public int component1() {
        return logicalRef;
    }

    public FicheRefProp component10() {
        return cyphcode;
    }

    public FicheRefProp component11() {
        return projectCode;
    }

    public FicheDiscountRefProp component12() {
        return aggrCode;
    }

    public FicheDiscountRefProp component13() {
        return bank;
    }

    public FicheDiscountRefProp component14() {
        return bankAcc;
    }

    public FicheStringProp component15() {
        return explanation1;
    }

    public FicheStringProp component16() {
        return explanation2;
    }

    public FicheStringProp component17() {
        return explanation3;
    }

    public FicheStringProp component18() {
        return explanation4;
    }

    public int component19() {
        return isTransfer;
    }

    public int component2() {
        return clRef;
    }

    public double component20() {
        return total;
    }

    public int component21() {
        return printCount;
    }

    public int component22() {
        return ficheref;
    }

    public String component23() {
        return ficheNo;
    }

    public double component24() {
        return enlem;
    }

    public double component25() {
        return boylam;
    }

    public int component26() {
        return dateInt;
    }

    public String component27() {
        return gDate;
    }

    public int component28() {
        return invoiceRef;
    }

    public int component29() {
        return visitInfoId;
    }

    public String component3() {
        return clCode;
    }

    public String component30() {
        return andFicheNo;
    }

    public FicheStringProp component31() {
        return installmentCount;
    }

    public ArrayList<CashCreditFicheDetail> component32() {
        return details;
    }

    public int component4() {
        return fType;
    }

    public FicheRefProp component5() {
        return branch;
    }

    public FicheRefProp component6() {
        return division;
    }

    public FicheRefProp component7() {
        return factory;
    }

    public FicheRefProp component8() {
        return tradinggrp;
    }

    public FicheRefProp component9() {
        return specode;
    }

    public CashCreditFiche copy(final int i2, final int i3, final String str, final int i4, final FicheRefProp branch, final FicheRefProp division, final FicheRefProp factory, final FicheRefProp tradinggrp, final FicheRefProp specode, final FicheRefProp cyphcode, final FicheRefProp projectCode, final FicheDiscountRefProp aggrCode, final FicheDiscountRefProp bank, final FicheDiscountRefProp bankAcc, final FicheStringProp explanation1, final FicheStringProp explanation2, final FicheStringProp explanation3, final FicheStringProp explanation4, final int i5, final double d2, final int i6, final int i7, final String str2, final double d3, final double d4, final int i8, final String str3, final int i9, final int i10, final String str4, final FicheStringProp installmentCount, final ArrayList<CashCreditFicheDetail> arrayList) {
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(aggrCode, "aggrCode");
        Intrinsics.checkNotNullParameter(bank, "bank");
        Intrinsics.checkNotNullParameter(bankAcc, "bankAcc");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(explanation2, "explanation2");
        Intrinsics.checkNotNullParameter(explanation3, "explanation3");
        Intrinsics.checkNotNullParameter(explanation4, "explanation4");
        Intrinsics.checkNotNullParameter(installmentCount, "installmentCount");
        return new CashCreditFiche(i2, i3, str, i4, branch, division, factory, tradinggrp, specode, cyphcode, projectCode, aggrCode, bank, bankAcc, explanation1, explanation2, explanation3, explanation4, i5, d2, i6, i7, str2, d3, d4, i8, str3, i9, i10, str4, installmentCount, arrayList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CashCreditFiche cashCreditFiche)) {
            return false;
        }
        return logicalRef == cashCreditFiche.logicalRef && clRef == cashCreditFiche.clRef && Intrinsics.areEqual(clCode, cashCreditFiche.clCode) && fType == cashCreditFiche.fType && Intrinsics.areEqual(branch, cashCreditFiche.branch) && Intrinsics.areEqual(division, cashCreditFiche.division) && Intrinsics.areEqual(factory, cashCreditFiche.factory) && Intrinsics.areEqual(tradinggrp, cashCreditFiche.tradinggrp) && Intrinsics.areEqual(specode, cashCreditFiche.specode) && Intrinsics.areEqual(cyphcode, cashCreditFiche.cyphcode) && Intrinsics.areEqual(projectCode, cashCreditFiche.projectCode) && Intrinsics.areEqual(aggrCode, cashCreditFiche.aggrCode) && Intrinsics.areEqual(bank, cashCreditFiche.bank) && Intrinsics.areEqual(bankAcc, cashCreditFiche.bankAcc) && Intrinsics.areEqual(explanation1, cashCreditFiche.explanation1) && Intrinsics.areEqual(explanation2, cashCreditFiche.explanation2) && Intrinsics.areEqual(explanation3, cashCreditFiche.explanation3) && Intrinsics.areEqual(explanation4, cashCreditFiche.explanation4) && isTransfer == cashCreditFiche.isTransfer && 0 == Double.compare(this.total, cashCreditFiche.total) && printCount == cashCreditFiche.printCount && ficheref == cashCreditFiche.ficheref && Intrinsics.areEqual(ficheNo, cashCreditFiche.ficheNo) && 0 == Double.compare(this.enlem, cashCreditFiche.enlem) && 0 == Double.compare(this.boylam, cashCreditFiche.boylam) && dateInt == cashCreditFiche.dateInt && Intrinsics.areEqual(gDate, cashCreditFiche.gDate) && invoiceRef == cashCreditFiche.invoiceRef && visitInfoId == cashCreditFiche.visitInfoId && Intrinsics.areEqual(andFicheNo, cashCreditFiche.andFicheNo) && Intrinsics.areEqual(installmentCount, cashCreditFiche.installmentCount) && Intrinsics.areEqual(details, cashCreditFiche.details);
    }

    public int hashCode() {
        final int hashCode = ((Integer.hashCode(logicalRef) * 31) + Integer.hashCode(clRef)) * 31;
        final String str = clCode;
        final int hashCode2 = (((((((((((((((((((((((((((((((((((((((hashCode + (null == str ? 0 : str.hashCode())) * 31) + Integer.hashCode(fType)) * 31) + branch.hashCode()) * 31) + division.hashCode()) * 31) + factory.hashCode()) * 31) + tradinggrp.hashCode()) * 31) + specode.hashCode()) * 31) + cyphcode.hashCode()) * 31) + projectCode.hashCode()) * 31) + aggrCode.hashCode()) * 31) + bank.hashCode()) * 31) + bankAcc.hashCode()) * 31) + explanation1.hashCode()) * 31) + explanation2.hashCode()) * 31) + explanation3.hashCode()) * 31) + explanation4.hashCode()) * 31) + Integer.hashCode(isTransfer)) * 31) + Double.hashCode(total)) * 31) + Integer.hashCode(printCount)) * 31) + Integer.hashCode(ficheref)) * 31;
        final String str2 = ficheNo;
        final int hashCode3 = (((((((hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31) + Double.hashCode(enlem)) * 31) + Double.hashCode(boylam)) * 31) + Integer.hashCode(dateInt)) * 31;
        final String str3 = gDate;
        final int hashCode4 = (((((hashCode3 + (null == str3 ? 0 : str3.hashCode())) * 31) + Integer.hashCode(invoiceRef)) * 31) + Integer.hashCode(visitInfoId)) * 31;
        final String str4 = andFicheNo;
        final int hashCode5 = (((hashCode4 + (null == str4 ? 0 : str4.hashCode())) * 31) + installmentCount.hashCode()) * 31;
        final ArrayList<CashCreditFicheDetail> arrayList = details;
        return hashCode5 + (null != arrayList ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "CashCreditFiche(logicalRef=" + logicalRef + ", clRef=" + clRef + ", clCode=" + clCode + ", fType=" + fType + ", branch=" + branch + ", division=" + division + ", factory=" + factory + ", tradinggrp=" + tradinggrp + ", specode=" + specode + ", cyphcode=" + cyphcode + ", projectCode=" + projectCode + ", aggrCode=" + aggrCode + ", bank=" + bank + ", bankAcc=" + bankAcc + ", explanation1=" + explanation1 + ", explanation2=" + explanation2 + ", explanation3=" + explanation3 + ", explanation4=" + explanation4 + ", isTransfer=" + isTransfer + ", total=" + total + ", printCount=" + printCount + ", ficheref=" + ficheref + ", ficheNo=" + ficheNo + ", enlem=" + enlem + ", boylam=" + boylam + ", dateInt=" + dateInt + ", gDate=" + gDate + ", invoiceRef=" + invoiceRef + ", visitInfoId=" + visitInfoId + ", andFicheNo=" + andFicheNo + ", installmentCount=" + installmentCount + ", details=" + details + ')';
    }

    public CashCreditFiche(final int i2, final int i3, final String str, final int i4, final FicheRefProp branch, final FicheRefProp division, final FicheRefProp factory, final FicheRefProp tradinggrp, final FicheRefProp specode, final FicheRefProp cyphcode, final FicheRefProp projectCode, final FicheDiscountRefProp aggrCode, final FicheDiscountRefProp bank, final FicheDiscountRefProp bankAcc, final FicheStringProp explanation1, final FicheStringProp explanation2, final FicheStringProp explanation3, final FicheStringProp explanation4, final int i5, final double d2, final int i6, final int i7, final String str2, final double d3, final double d4, final int i8, final String str3, final int i9, final int i10, final String str4, final FicheStringProp installmentCount, final ArrayList<CashCreditFicheDetail> arrayList) {
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(aggrCode, "aggrCode");
        Intrinsics.checkNotNullParameter(bank, "bank");
        Intrinsics.checkNotNullParameter(bankAcc, "bankAcc");
        Intrinsics.checkNotNullParameter(explanation1, "explanation1");
        Intrinsics.checkNotNullParameter(explanation2, "explanation2");
        Intrinsics.checkNotNullParameter(explanation3, "explanation3");
        Intrinsics.checkNotNullParameter(explanation4, "explanation4");
        Intrinsics.checkNotNullParameter(installmentCount, "installmentCount");
        logicalRef = i2;
        clRef = i3;
        clCode = str;
        fType = i4;
        this.branch = branch;
        this.division = division;
        this.factory = factory;
        this.tradinggrp = tradinggrp;
        this.specode = specode;
        this.cyphcode = cyphcode;
        this.projectCode = projectCode;
        this.aggrCode = aggrCode;
        this.bank = bank;
        this.bankAcc = bankAcc;
        this.explanation1 = explanation1;
        this.explanation2 = explanation2;
        this.explanation3 = explanation3;
        this.explanation4 = explanation4;
        isTransfer = i5;
        total = d2;
        printCount = i6;
        ficheref = i7;
        ficheNo = str2;
        enlem = d3;
        boylam = d4;
        dateInt = i8;
        gDate = str3;
        invoiceRef = i9;
        visitInfoId = i10;
        andFicheNo = str4;
        this.installmentCount = installmentCount;
        details = arrayList;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public int getClRef() {
        return clRef;
    }

    public void setClRef(final int i2) {
        clRef = i2;
    }

    public String getClCode() {
        return clCode;
    }

    public void setClCode(final String str) {
        clCode = str;
    }

    public int getFType() {
        return fType;
    }

    public void setFType(final int i2) {
        fType = i2;
    }

    public CashCreditFiche(final int i2, final int i3, final String str, final int i4, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final FicheRefProp ficheRefProp3, final FicheRefProp ficheRefProp4, final FicheRefProp ficheRefProp5, final FicheRefProp ficheRefProp6, final FicheRefProp ficheRefProp7, final FicheDiscountRefProp ficheDiscountRefProp, final FicheDiscountRefProp ficheDiscountRefProp2, final FicheDiscountRefProp ficheDiscountRefProp3, final FicheStringProp ficheStringProp, final FicheStringProp ficheStringProp2, final FicheStringProp ficheStringProp3, final FicheStringProp ficheStringProp4, final int i5, final double d2, final int i6, final int i7, final String str2, final double d3, final double d4, final int i8, final String str3, final int i9, final int i10, final String str4, final FicheStringProp ficheStringProp5, final ArrayList arrayList, final int i11, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i11 & 1) ? 0 : i2, 0 != (i11 & 2) ? 0 : i3, 0 != (i11 & 4) ? null : str, 0 != (i11 & 8) ? 0 : i4, 0 != (i11 & 16) ? new FicheRefProp() : ficheRefProp, 0 != (i11 & 32) ? new FicheRefProp() : ficheRefProp2, 0 != (i11 & 64) ? new FicheRefProp() : ficheRefProp3, 0 != (i11 & 128) ? new FicheRefProp() : ficheRefProp4, 0 != (i11 & 256) ? new FicheRefProp() : ficheRefProp5, 0 != (i11 & 512) ? new FicheRefProp() : ficheRefProp6, 0 != (i11 & 1024) ? new FicheRefProp() : ficheRefProp7, 0 != (i11 & 2048) ? new FicheDiscountRefProp() : ficheDiscountRefProp, 0 != (i11 & 4096) ? new FicheDiscountRefProp() : ficheDiscountRefProp2, 0 != (i11 & 8192) ? new FicheDiscountRefProp() : ficheDiscountRefProp3, 0 != (i11 & 16384) ? new FicheStringProp() : ficheStringProp, 0 != (i11 & 32768) ? new FicheStringProp() : ficheStringProp2, 0 != (i11 & 65536) ? new FicheStringProp() : ficheStringProp3, 0 != (i11 & 131072) ? new FicheStringProp() : ficheStringProp4, 0 != (i11 & 262144) ? 0 : i5, 0 != (i11 & 524288) ? 0.0d : d2, 0 != (i11 & 1048576) ? 0 : i6, 0 != (i11 & 2097152) ? 0 : i7, 0 != (i11 & 4194304) ? null : str2, 0 != (i11 & 8388608) ? 0.0d : d3, 0 == (i11 & 16777216) ? d4 : 0.0d, 0 != (i11 & 33554432) ? 0 : i8, 0 != (i11 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? null : str3, 0 != (i11 & 134217728) ? 0 : i9, 0 != (i11 & 268435456) ? 0 : i10, 0 != (i11 & 536870912) ? null : str4, 0 != (i11 & BasicMeasure.EXACTLY) ? new FicheStringProp() : ficheStringProp5, 0 != (i11 & Integer.MIN_VALUE) ? new ArrayList() : arrayList);
    }

    public FicheRefProp getBranch() {
        return branch;
    }

    public void setBranch(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        branch = ficheRefProp;
    }

    public FicheRefProp getDivision() {
        return division;
    }

    public void setDivision(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        division = ficheRefProp;
    }

    public FicheRefProp getFactory() {
        return factory;
    }

    public void setFactory(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        factory = ficheRefProp;
    }

    public FicheRefProp getTradinggrp() {
        return tradinggrp;
    }

    public void setTradinggrp(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        tradinggrp = ficheRefProp;
    }

    public FicheRefProp getSpecode() {
        return specode;
    }

    public void setSpecode(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        specode = ficheRefProp;
    }

    public FicheRefProp getCyphcode() {
        return cyphcode;
    }

    public void setCyphcode(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        cyphcode = ficheRefProp;
    }

    public FicheRefProp getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        projectCode = ficheRefProp;
    }

    public FicheDiscountRefProp getAggrCode() {
        return aggrCode;
    }

    public void setAggrCode(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        aggrCode = ficheDiscountRefProp;
    }

    public FicheDiscountRefProp getBank() {
        return bank;
    }

    public void setBank(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        bank = ficheDiscountRefProp;
    }

    public FicheDiscountRefProp getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        bankAcc = ficheDiscountRefProp;
    }

    public FicheStringProp getExplanation1() {
        return explanation1;
    }

    public void setExplanation1(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        explanation1 = ficheStringProp;
    }

    public FicheStringProp getExplanation2() {
        return explanation2;
    }

    public void setExplanation2(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        explanation2 = ficheStringProp;
    }

    public FicheStringProp getExplanation3() {
        return explanation3;
    }

    public void setExplanation3(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        explanation3 = ficheStringProp;
    }

    public FicheStringProp getExplanation4() {
        return explanation4;
    }

    public void setExplanation4(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        explanation4 = ficheStringProp;
    }

    public int isTransfer() {
        return isTransfer;
    }

    public void setTransfer(final int i2) {
        isTransfer = i2;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(final double d2) {
        total = d2;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(final int i2) {
        printCount = i2;
    }

    public int getFicheref() {
        return ficheref;
    }

    public void setFicheref(final int i2) {
        ficheref = i2;
    }

    public String getFicheNo() {
        return ficheNo;
    }

    public void setFicheNo(final String str) {
        ficheNo = str;
    }

    public double getEnlem() {
        return enlem;
    }

    public void setEnlem(final double d2) {
        enlem = d2;
    }

    public double getBoylam() {
        return boylam;
    }

    public void setBoylam(final double d2) {
        boylam = d2;
    }

    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(final int i2) {
        dateInt = i2;
    }

    public String getGDate() {
        return gDate;
    }

    public void setGDate(final String str) {
        gDate = str;
    }

    public int getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(final int i2) {
        invoiceRef = i2;
    }

    public int getVisitInfoId() {
        return visitInfoId;
    }

    public void setVisitInfoId(final int i2) {
        visitInfoId = i2;
    }

    public String getAndFicheNo() {
        return andFicheNo;
    }

    public void setAndFicheNo(final String str) {
        andFicheNo = str;
    }

    public FicheStringProp getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        installmentCount = ficheStringProp;
    }

    public ArrayList<CashCreditFicheDetail> getDetails() {
        return details;
    }

    public void setDetails(final ArrayList<CashCreditFicheDetail> arrayList) {
        details = arrayList;
    }


    public CashCreditFiche(final Parcel parcel) {
        this(0, 0, null, 0, null, null, null, null, null, null, null, null, null, r16, r16, r16, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, 0, 0, null, null, null, -1, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        final FicheDiscountRefProp ficheDiscountRefProp = null;
        logicalRef = parcel.readInt();
        clRef = parcel.readInt();
        clCode = parcel.readString();
        fType = parcel.readInt();
        final FicheRefProp ficheRefProp = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        branch = null == ficheRefProp ? new FicheRefProp() : ficheRefProp;
        final FicheRefProp ficheRefProp2 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        division = null == ficheRefProp2 ? new FicheRefProp() : ficheRefProp2;
        final FicheRefProp ficheRefProp3 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        factory = null == ficheRefProp3 ? new FicheRefProp() : ficheRefProp3;
        final FicheRefProp ficheRefProp4 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        tradinggrp = null == ficheRefProp4 ? new FicheRefProp() : ficheRefProp4;
        final FicheRefProp ficheRefProp5 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        specode = null == ficheRefProp5 ? new FicheRefProp() : ficheRefProp5;
        final FicheRefProp ficheRefProp6 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        cyphcode = null == ficheRefProp6 ? new FicheRefProp() : ficheRefProp6;
        final FicheRefProp ficheRefProp7 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        projectCode = null == ficheRefProp7 ? new FicheRefProp() : ficheRefProp7;
        final FicheDiscountRefProp ficheDiscountRefProp2 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        aggrCode = null == ficheDiscountRefProp2 ? new FicheDiscountRefProp() : ficheDiscountRefProp2;
        final FicheDiscountRefProp ficheDiscountRefProp3 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        bank = null == ficheDiscountRefProp3 ? new FicheDiscountRefProp() : ficheDiscountRefProp3;
        final FicheDiscountRefProp ficheDiscountRefProp4 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        bankAcc = null == ficheDiscountRefProp4 ? new FicheDiscountRefProp() : ficheDiscountRefProp4;
        final FicheStringProp ficheStringProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        explanation1 = null == ficheStringProp ? new FicheStringProp() : ficheStringProp;
        final FicheStringProp ficheStringProp2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        explanation2 = null == ficheStringProp2 ? new FicheStringProp() : ficheStringProp2;
        final FicheStringProp ficheStringProp3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        explanation3 = null == ficheStringProp3 ? new FicheStringProp() : ficheStringProp3;
        final FicheStringProp ficheStringProp4 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        explanation4 = null == ficheStringProp4 ? new FicheStringProp() : ficheStringProp4;
        isTransfer = parcel.readInt();
        total = parcel.readDouble();
        printCount = parcel.readInt();
        ficheref = parcel.readInt();
        ficheNo = parcel.readString();
        enlem = parcel.readDouble();
        boylam = parcel.readDouble();
        dateInt = parcel.readInt();
        gDate = parcel.readString();
        andFicheNo = parcel.readString();
        final FicheStringProp ficheStringProp5 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        installmentCount = null == ficheStringProp5 ? new FicheStringProp() : ficheStringProp5;
        details = parcel.createTypedArrayList(CashCreditFicheDetail.CREATOR);
        invoiceRef = parcel.readInt();
        visitInfoId = parcel.readInt();
    }

    public void init() {
        branch = new FicheRefProp(0, -1, "");
        division = new FicheRefProp(0, -1, "");
        factory = new FicheRefProp();
        tradinggrp = new FicheRefProp();
        specode = new FicheRefProp();
        cyphcode = new FicheRefProp();
        projectCode = new FicheRefProp();
        aggrCode = new FicheDiscountRefProp();
        bank = new FicheDiscountRefProp();
        bankAcc = new FicheDiscountRefProp();
        explanation1 = new FicheStringProp();
        explanation2 = new FicheStringProp();
        explanation3 = new FicheStringProp();
        explanation4 = new FicheStringProp();
        installmentCount = new FicheStringProp();
    }

    public int getfType() {
        return fType;
    }

    public String getgDate() {
        return gDate;
    }

    public void setfType(final int i2) {
        fType = i2;
    }

    public void setgDate(final String str) {
        gDate = str;
    }

    public void addNewDetail(final CashCreditFicheDetail newDetail) {
        Intrinsics.checkNotNullParameter(newDetail, "newDetail");
        final ArrayList<CashCreditFicheDetail> arrayList = details;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(newDetail);
    }

    public void calculateTotal() {
        final ArrayList<CashCreditFicheDetail> arrayList = details;
        Intrinsics.checkNotNull(arrayList);
        final Iterator<CashCreditFicheDetail> it = arrayList.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 += it.next().component3().getDefinitionDouble();
        }
        total = d2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dests, final int i2) {
        Intrinsics.checkNotNullParameter(dests, "dests");
        dests.writeInt(logicalRef);
        dests.writeInt(clRef);
        dests.writeString(clCode);
        dests.writeInt(fType);
        dests.writeParcelable(branch, i2);
        dests.writeParcelable(division, i2);
        dests.writeParcelable(factory, i2);
        dests.writeParcelable(tradinggrp, i2);
        dests.writeParcelable(specode, i2);
        dests.writeParcelable(cyphcode, i2);
        dests.writeParcelable(projectCode, i2);
        dests.writeParcelable(aggrCode, i2);
        dests.writeParcelable(bank, i2);
        dests.writeParcelable(bankAcc, i2);
        dests.writeParcelable(explanation1, i2);
        dests.writeParcelable(explanation2, i2);
        dests.writeParcelable(explanation3, i2);
        dests.writeParcelable(explanation4, i2);
        dests.writeParcelable(installmentCount, i2);
        dests.writeInt(isTransfer);
        dests.writeDouble(total);
        dests.writeInt(printCount);
        dests.writeInt(ficheref);
        dests.writeString(ficheNo);
        dests.writeDouble(enlem);
        dests.writeDouble(boylam);
        dests.writeInt(dateInt);
        dests.writeString(gDate);
        dests.writeString(andFicheNo);
        dests.writeTypedList(details);
        dests.writeInt(invoiceRef);
        dests.writeInt(visitInfoId);
    }

    /* compiled from: CashCreditFiche.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Parcelable.Creator<CashCreditFiche> getCREATOR() {
            return CREATOR;
        }
    }
}
