package com.proje.mobilesales.features.collections.casefiche.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CaseFiche implements Parcelable {
    private String andFicheNo;
    private double boylam;
    private FicheRefProp branch;
    private int cardRef;
    private FicheDiscountRefProp caseCode;
    private String clCode;
    private FicheRefProp clCurr;
    private double clRate;
    private int clRef;
    private FicheRefProp currType;
    private FicheRefProp cyphcode;
    private int dateInt;
    private FicheRefProp division;
    private FicheStringProp docNo;
    private double enlem;
    private FicheStringProp explanation;
    private String ficheNo;
    private int ficheref;
    private String gDate;
    private int invoiceRef;
    private int isTransfer;
    private int logicalRef;
    private int printCount;
    private FicheRefProp projectCode;
    private FicheDiscountRefProp salesMan;
    private FicheRefProp specode;
    private FicheStringProp total;
    private FicheRefProp tradinggrp;
    private int visitInfoId;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CaseFiche> CREATOR = new Parcelable.Creator<CaseFiche>() {

        public CaseFiche createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CaseFiche(source);
        }
        public CaseFiche[] newArray(final int i2) {
            return new CaseFiche[i2];
        }
    };

    public CaseFiche() {
        this(0, 0, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0.0d, 0.0d, 0, null, 0, null, null, null, null, 0, 0.0d, null, 0, 536870911, null);
    }

    public static  CaseFiche copydefault(final CaseFiche caseFiche, final int i2, final int i3, final String str, final FicheStringProp ficheStringProp, final FicheStringProp ficheStringProp2, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final FicheRefProp ficheRefProp3, final FicheRefProp ficheRefProp4, final FicheRefProp ficheRefProp5, final FicheRefProp ficheRefProp6, final FicheStringProp ficheStringProp3, final int i4, final int i5, final int i6, final String str2, final double d2, final double d3, final int i7, final FicheDiscountRefProp ficheDiscountRefProp, final int i8, final String str3, final String str4, final FicheDiscountRefProp ficheDiscountRefProp2, final FicheRefProp ficheRefProp7, final int i9, final double d4, final FicheRefProp ficheRefProp8, final int i10, final int i11, final Object obj) {
        final int i12 = 0 != (i11 & 1) ? caseFiche.logicalRef : i2;
        final int i13 = 0 != (i11 & 2) ? caseFiche.clRef : i3;
        final String str5 = 0 != (i11 & 4) ? caseFiche.clCode : str;
        final FicheStringProp ficheStringProp4 = 0 != (i11 & 8) ? caseFiche.total : ficheStringProp;
        final FicheStringProp ficheStringProp5 = 0 != (i11 & 16) ? caseFiche.docNo : ficheStringProp2;
        final FicheRefProp ficheRefProp9 = 0 != (i11 & 32) ? caseFiche.branch : ficheRefProp;
        final FicheRefProp ficheRefProp10 = 0 != (i11 & 64) ? caseFiche.division : ficheRefProp2;
        final FicheRefProp ficheRefProp11 = 0 != (i11 & 128) ? caseFiche.tradinggrp : ficheRefProp3;
        final FicheRefProp ficheRefProp12 = 0 != (i11 & 256) ? caseFiche.specode : ficheRefProp4;
        final FicheRefProp ficheRefProp13 = 0 != (i11 & 512) ? caseFiche.cyphcode : ficheRefProp5;
        final FicheRefProp ficheRefProp14 = 0 != (i11 & 1024) ? caseFiche.projectCode : ficheRefProp6;
        final FicheStringProp ficheStringProp6 = 0 != (i11 & 2048) ? caseFiche.explanation : ficheStringProp3;
        final int i14 = 0 != (i11 & 4096) ? caseFiche.isTransfer : i4;
        return caseFiche.copy(i12, i13, str5, ficheStringProp4, ficheStringProp5, ficheRefProp9, ficheRefProp10, ficheRefProp11, ficheRefProp12, ficheRefProp13, ficheRefProp14, ficheStringProp6, i14, 0 != (i11 & 8192) ? caseFiche.printCount : i5, 0 != (i11 & 16384) ? caseFiche.ficheref : i6, 0 != (i11 & 32768) ? caseFiche.ficheNo : str2, 0 != (i11 & 65536) ? caseFiche.enlem : d2, 0 != (i11 & 131072) ? caseFiche.boylam : d3, 0 != (i11 & 262144) ? caseFiche.cardRef : i7, 0 != (524288 & i11) ? caseFiche.caseCode : ficheDiscountRefProp, 0 != (i11 & 1048576) ? caseFiche.dateInt : i8, 0 != (i11 & 2097152) ? caseFiche.gDate : str3, 0 != (i11 & 4194304) ? caseFiche.andFicheNo : str4, 0 != (i11 & 8388608) ? caseFiche.salesMan : ficheDiscountRefProp2, 0 != (i11 & 16777216) ? caseFiche.currType : ficheRefProp7, 0 != (i11 & 33554432) ? caseFiche.invoiceRef : i9, 0 != (i11 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? caseFiche.clRate : d4, 0 != (i11 & 134217728) ? caseFiche.clCurr : ficheRefProp8, 0 != (i11 & 268435456) ? caseFiche.visitInfoId : i10);
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

    public FicheStringProp component12() {
        return explanation;
    }

    public int component13() {
        return isTransfer;
    }

    public int component14() {
        return printCount;
    }

    public int component15() {
        return ficheref;
    }

    public String component16() {
        return ficheNo;
    }

    public double component17() {
        return enlem;
    }

    public double component18() {
        return boylam;
    }

    public int component19() {
        return cardRef;
    }

    public int component2() {
        return clRef;
    }

    public FicheDiscountRefProp component20() {
        return caseCode;
    }

    public int component21() {
        return dateInt;
    }

    public String component22() {
        return gDate;
    }

    public String component23() {
        return andFicheNo;
    }

    public FicheDiscountRefProp component24() {
        return salesMan;
    }

    public FicheRefProp component25() {
        return currType;
    }

    public int component26() {
        return invoiceRef;
    }

    public double component27() {
        return clRate;
    }

    public FicheRefProp component28() {
        return clCurr;
    }

    public int component29() {
        return visitInfoId;
    }

    public String component3() {
        return clCode;
    }

    public FicheStringProp component4() {
        return total;
    }

    public FicheStringProp component5() {
        return docNo;
    }

    public FicheRefProp component6() {
        return branch;
    }

    public FicheRefProp component7() {
        return division;
    }

    public FicheRefProp component8() {
        return tradinggrp;
    }

    public FicheRefProp component9() {
        return specode;
    }

    public CaseFiche copy(final int i2, final int i3, final String str, final FicheStringProp total, final FicheStringProp docNo, final FicheRefProp branch, final FicheRefProp division, final FicheRefProp tradinggrp, final FicheRefProp specode, final FicheRefProp cyphcode, final FicheRefProp projectCode, final FicheStringProp explanation, final int i4, final int i5, final int i6, final String str2, final double d2, final double d3, final int i7, final FicheDiscountRefProp caseCode, final int i8, final String str3, final String str4, final FicheDiscountRefProp salesMan, final FicheRefProp currType, final int i9, final double d4, final FicheRefProp clCurr, final int i10) {
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(docNo, "docNo");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(explanation, "explanation");
        Intrinsics.checkNotNullParameter(caseCode, "caseCode");
        Intrinsics.checkNotNullParameter(salesMan, "salesMan");
        Intrinsics.checkNotNullParameter(currType, "currType");
        Intrinsics.checkNotNullParameter(clCurr, "clCurr");
        return new CaseFiche(i2, i3, str, total, docNo, branch, division, tradinggrp, specode, cyphcode, projectCode, explanation, i4, i5, i6, str2, d2, d3, i7, caseCode, i8, str3, str4, salesMan, currType, i9, d4, clCurr, i10);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CaseFiche caseFiche)) {
            return false;
        }
        return logicalRef == caseFiche.logicalRef && clRef == caseFiche.clRef && Intrinsics.areEqual(clCode, caseFiche.clCode) && Intrinsics.areEqual(total, caseFiche.total) && Intrinsics.areEqual(docNo, caseFiche.docNo) && Intrinsics.areEqual(branch, caseFiche.branch) && Intrinsics.areEqual(division, caseFiche.division) && Intrinsics.areEqual(tradinggrp, caseFiche.tradinggrp) && Intrinsics.areEqual(specode, caseFiche.specode) && Intrinsics.areEqual(cyphcode, caseFiche.cyphcode) && Intrinsics.areEqual(projectCode, caseFiche.projectCode) && Intrinsics.areEqual(explanation, caseFiche.explanation) && isTransfer == caseFiche.isTransfer && printCount == caseFiche.printCount && ficheref == caseFiche.ficheref && Intrinsics.areEqual(ficheNo, caseFiche.ficheNo) && 0 == Double.compare(this.enlem, caseFiche.enlem) && 0 == Double.compare(this.boylam, caseFiche.boylam) && cardRef == caseFiche.cardRef && Intrinsics.areEqual(caseCode, caseFiche.caseCode) && dateInt == caseFiche.dateInt && Intrinsics.areEqual(gDate, caseFiche.gDate) && Intrinsics.areEqual(andFicheNo, caseFiche.andFicheNo) && Intrinsics.areEqual(salesMan, caseFiche.salesMan) && Intrinsics.areEqual(currType, caseFiche.currType) && invoiceRef == caseFiche.invoiceRef && 0 == Double.compare(this.clRate, caseFiche.clRate) && Intrinsics.areEqual(clCurr, caseFiche.clCurr) && visitInfoId == caseFiche.visitInfoId;
    }

    public int hashCode() {
        final int hashCode = ((Integer.hashCode(logicalRef) * 31) + Integer.hashCode(clRef)) * 31;
        final String str = clCode;
        final int hashCode2 = (((((((((((((((((((((((((hashCode + (null == str ? 0 : str.hashCode())) * 31) + total.hashCode()) * 31) + docNo.hashCode()) * 31) + branch.hashCode()) * 31) + division.hashCode()) * 31) + tradinggrp.hashCode()) * 31) + specode.hashCode()) * 31) + cyphcode.hashCode()) * 31) + projectCode.hashCode()) * 31) + explanation.hashCode()) * 31) + Integer.hashCode(isTransfer)) * 31) + Integer.hashCode(printCount)) * 31) + Integer.hashCode(ficheref)) * 31;
        final String str2 = ficheNo;
        final int hashCode3 = (((((((((((hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31) + Double.hashCode(enlem)) * 31) + Double.hashCode(boylam)) * 31) + Integer.hashCode(cardRef)) * 31) + caseCode.hashCode()) * 31) + Integer.hashCode(dateInt)) * 31;
        final String str3 = gDate;
        final int hashCode4 = (hashCode3 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = andFicheNo;
        return ((((((((((((hashCode4 + (null != str4 ? str4.hashCode() : 0)) * 31) + salesMan.hashCode()) * 31) + currType.hashCode()) * 31) + Integer.hashCode(invoiceRef)) * 31) + Double.hashCode(clRate)) * 31) + clCurr.hashCode()) * 31) + Integer.hashCode(visitInfoId);
    }

    public String toString() {
        return "CaseFiche(logicalRef=" + logicalRef + ", clRef=" + clRef + ", clCode=" + clCode + ", total=" + total + ", docNo=" + docNo + ", branch=" + branch + ", division=" + division + ", tradinggrp=" + tradinggrp + ", specode=" + specode + ", cyphcode=" + cyphcode + ", projectCode=" + projectCode + ", explanation=" + explanation + ", isTransfer=" + isTransfer + ", printCount=" + printCount + ", ficheref=" + ficheref + ", ficheNo=" + ficheNo + ", enlem=" + enlem + ", boylam=" + boylam + ", cardRef=" + cardRef + ", caseCode=" + caseCode + ", dateInt=" + dateInt + ", gDate=" + gDate + ", andFicheNo=" + andFicheNo + ", salesMan=" + salesMan + ", currType=" + currType + ", invoiceRef=" + invoiceRef + ", clRate=" + clRate + ", clCurr=" + clCurr + ", visitInfoId=" + visitInfoId + ')';
    }

    public CaseFiche(final int i2, final int i3, final String str, final FicheStringProp total, final FicheStringProp docNo, final FicheRefProp branch, final FicheRefProp division, final FicheRefProp tradinggrp, final FicheRefProp specode, final FicheRefProp cyphcode, final FicheRefProp projectCode, final FicheStringProp explanation, final int i4, final int i5, final int i6, final String str2, final double d2, final double d3, final int i7, final FicheDiscountRefProp caseCode, final int i8, final String str3, final String str4, final FicheDiscountRefProp salesMan, final FicheRefProp currType, final int i9, final double d4, final FicheRefProp clCurr, final int i10) {
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(docNo, "docNo");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(division, "division");
        Intrinsics.checkNotNullParameter(tradinggrp, "tradinggrp");
        Intrinsics.checkNotNullParameter(specode, "specode");
        Intrinsics.checkNotNullParameter(cyphcode, "cyphcode");
        Intrinsics.checkNotNullParameter(projectCode, "projectCode");
        Intrinsics.checkNotNullParameter(explanation, "explanation");
        Intrinsics.checkNotNullParameter(caseCode, "caseCode");
        Intrinsics.checkNotNullParameter(salesMan, "salesMan");
        Intrinsics.checkNotNullParameter(currType, "currType");
        Intrinsics.checkNotNullParameter(clCurr, "clCurr");
        logicalRef = i2;
        clRef = i3;
        clCode = str;
        this.total = total;
        this.docNo = docNo;
        this.branch = branch;
        this.division = division;
        this.tradinggrp = tradinggrp;
        this.specode = specode;
        this.cyphcode = cyphcode;
        this.projectCode = projectCode;
        this.explanation = explanation;
        isTransfer = i4;
        printCount = i5;
        ficheref = i6;
        ficheNo = str2;
        enlem = d2;
        boylam = d3;
        cardRef = i7;
        this.caseCode = caseCode;
        dateInt = i8;
        gDate = str3;
        andFicheNo = str4;
        this.salesMan = salesMan;
        this.currType = currType;
        invoiceRef = i9;
        clRate = d4;
        this.clCurr = clCurr;
        visitInfoId = i10;
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

    public CaseFiche(final int i2, final int i3, final String str, final FicheStringProp ficheStringProp, final FicheStringProp ficheStringProp2, final FicheRefProp ficheRefProp, final FicheRefProp ficheRefProp2, final FicheRefProp ficheRefProp3, final FicheRefProp ficheRefProp4, final FicheRefProp ficheRefProp5, final FicheRefProp ficheRefProp6, final FicheStringProp ficheStringProp3, final int i4, final int i5, final int i6, final String str2, final double d2, final double d3, final int i7, final FicheDiscountRefProp ficheDiscountRefProp, final int i8, final String str3, final String str4, final FicheDiscountRefProp ficheDiscountRefProp2, final FicheRefProp ficheRefProp7, final int i9, final double d4, final FicheRefProp ficheRefProp8, final int i10, final int i11, final DefaultConstructorMarker defaultConstructorMarker) {
        this(r1, r3, r4, r6, r7, r8, r11, r12, r13, r14, r15, r67, r30, r16, r29, r17, r21, r23, r18, r25, r26, r27, r28, r9, 0 != (16777216 & i11) ? new FicheRefProp() : ficheRefProp7, 0 != (33554432 & i11) ? r2 : i9, 0 == (i11 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) ? d4 : 0.0d, 0 != (i11 & 134217728) ? new FicheRefProp() : ficheRefProp8, 0 == (i11 & 268435456) ? i10 : r2);
        final int i12;
        final FicheStringProp ficheStringProp4;
        final int i13;
        final int i14;
        final FicheDiscountRefProp ficheDiscountRefProp3;
        final int i15 = 0 != (i11 & 1) ? 0 : i2;
        final int i16 = 0 != (i11 & 2) ? 0 : i3;
        final String str5 = 0 != (i11 & 4) ? null : str;
        final FicheStringProp ficheStringProp5 = 0 != (i11 & 8) ? new FicheStringProp() : ficheStringProp;
        final FicheStringProp ficheStringProp6 = 0 != (i11 & 16) ? new FicheStringProp() : ficheStringProp2;
        final FicheRefProp ficheRefProp9 = 0 != (i11 & 32) ? new FicheRefProp(0, -1, "") : ficheRefProp;
        final FicheRefProp ficheRefProp10 = 0 != (i11 & 64) ? new FicheRefProp(0, -1, "") : ficheRefProp2;
        final FicheRefProp ficheRefProp11 = 0 != (i11 & 128) ? new FicheRefProp() : ficheRefProp3;
        final FicheRefProp ficheRefProp12 = 0 != (i11 & 256) ? new FicheRefProp() : ficheRefProp4;
        final FicheRefProp ficheRefProp13 = 0 != (i11 & 512) ? new FicheRefProp() : ficheRefProp5;
        final FicheRefProp ficheRefProp14 = 0 != (i11 & 1024) ? new FicheRefProp() : ficheRefProp6;
        final FicheStringProp ficheStringProp7 = 0 != (i11 & 2048) ? new FicheStringProp() : ficheStringProp3;
        final int i17 = 0 != (i11 & 4096) ? 0 : i4;
        final int i18 = 0 != (i11 & 8192) ? 0 : i5;
        final int i19 = 0 != (i11 & 16384) ? 0 : i6;
        final String str6 = 0 != (i11 & 32768) ? null : str2;
        final double d5 = 0 != (i11 & 65536) ? 0.0d : d2;
        final double d6 = 0 != (i11 & 131072) ? 0.0d : d3;
        final int i20 = 0 != (i11 & 262144) ? 0 : i7;
        final FicheDiscountRefProp ficheDiscountRefProp4 = 0 != (i11 & 524288) ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        final int i21 = 0 != (i11 & 1048576) ? 0 : i8;
        final String str7 = 0 != (i11 & 2097152) ? null : str3;
        final String str8 = 0 != (i11 & 4194304) ? null : str4;
        if (0 != (i11 & 8388608)) {
            i13 = i19;
            i12 = i17;
            ficheStringProp4 = ficheStringProp7;
            i14 = 0;
            ficheDiscountRefProp3 = new FicheDiscountRefProp(0, -1, "", "");
        } else {
            i12 = i17;
            ficheStringProp4 = ficheStringProp7;
            i13 = i19;
            i14 = 0;
            ficheDiscountRefProp3 = ficheDiscountRefProp2;
        }
    }

    public FicheStringProp getTotal() {
        return total;
    }

    public void setTotal(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        total = ficheStringProp;
    }

    public FicheStringProp getDocNo() {
        return docNo;
    }

    public void setDocNo(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        docNo = ficheStringProp;
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

    public FicheStringProp getExplanation() {
        return explanation;
    }

    public void setExplanation(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        explanation = ficheStringProp;
    }

    public int isTransfer() {
        return isTransfer;
    }

    public void setTransfer(final int i2) {
        isTransfer = i2;
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

    public int getCardRef() {
        return cardRef;
    }

    public void setCardRef(final int i2) {
        cardRef = i2;
    }

    public FicheDiscountRefProp getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        caseCode = ficheDiscountRefProp;
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

    public String getAndFicheNo() {
        return andFicheNo;
    }

    public void setAndFicheNo(final String str) {
        andFicheNo = str;
    }

    public FicheDiscountRefProp getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        salesMan = ficheDiscountRefProp;
    }

    public FicheRefProp getCurrType() {
        return currType;
    }

    public void setCurrType(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        currType = ficheRefProp;
    }

    public int getInvoiceRef() {
        return invoiceRef;
    }

    public void setInvoiceRef(final int i2) {
        invoiceRef = i2;
    }

    public double getClRate() {
        return clRate;
    }

    public void setClRate(final double d2) {
        clRate = d2;
    }

    public FicheRefProp getClCurr() {
        return clCurr;
    }

    public void setClCurr(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        clCurr = ficheRefProp;
    }

    public int getVisitInfoId() {
        return visitInfoId;
    }

    public void setVisitInfoId(final int i2) {
        visitInfoId = i2;
    }

    public CaseFiche(final Parcel parcel) {
        this(0, 0, null, null, null, null, null, null, null, null, null, null, 0, r16, r16, null, 0.0d, 0.0d, 0, null, 0, null, null, null, null, 0, 0.0d, null, 0, 536870911, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        final int i2 = 0;
        logicalRef = parcel.readInt();
        clRef = parcel.readInt();
        clCode = parcel.readString();
        final FicheStringProp ficheStringProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        total = null == ficheStringProp ? new FicheStringProp() : ficheStringProp;
        final FicheStringProp ficheStringProp2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        docNo = null == ficheStringProp2 ? new FicheStringProp() : ficheStringProp2;
        final FicheRefProp ficheRefProp = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        branch = null == ficheRefProp ? new FicheRefProp() : ficheRefProp;
        final FicheRefProp ficheRefProp2 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        division = null == ficheRefProp2 ? new FicheRefProp() : ficheRefProp2;
        final FicheRefProp ficheRefProp3 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        tradinggrp = null == ficheRefProp3 ? new FicheRefProp() : ficheRefProp3;
        final FicheRefProp ficheRefProp4 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        specode = null == ficheRefProp4 ? new FicheRefProp() : ficheRefProp4;
        final FicheRefProp ficheRefProp5 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        cyphcode = null == ficheRefProp5 ? new FicheRefProp() : ficheRefProp5;
        final FicheRefProp ficheRefProp6 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        projectCode = null == ficheRefProp6 ? new FicheRefProp() : ficheRefProp6;
        final FicheStringProp ficheStringProp3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        explanation = null == ficheStringProp3 ? new FicheStringProp() : ficheStringProp3;
        isTransfer = parcel.readInt();
        printCount = parcel.readInt();
        ficheref = parcel.readInt();
        ficheNo = parcel.readString();
        enlem = parcel.readDouble();
        boylam = parcel.readDouble();
        cardRef = parcel.readInt();
        final FicheDiscountRefProp ficheDiscountRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        caseCode = null == ficheDiscountRefProp ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        dateInt = parcel.readInt();
        gDate = parcel.readString();
        andFicheNo = parcel.readString();
        final FicheDiscountRefProp ficheDiscountRefProp2 = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        salesMan = null == ficheDiscountRefProp2 ? new FicheDiscountRefProp() : ficheDiscountRefProp2;
        final FicheRefProp ficheRefProp7 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        currType = null == ficheRefProp7 ? new FicheRefProp() : ficheRefProp7;
        invoiceRef = parcel.readInt();
        clRate = parcel.readDouble();
        final FicheRefProp ficheRefProp8 = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        clCurr = null == ficheRefProp8 ? new FicheRefProp() : ficheRefProp8;
        visitInfoId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dest, final int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(logicalRef);
        dest.writeInt(clRef);
        dest.writeString(clCode);
        dest.writeParcelable(total, i2);
        dest.writeParcelable(docNo, i2);
        dest.writeParcelable(branch, i2);
        dest.writeParcelable(division, i2);
        dest.writeParcelable(tradinggrp, i2);
        dest.writeParcelable(specode, i2);
        dest.writeParcelable(cyphcode, i2);
        dest.writeParcelable(projectCode, i2);
        dest.writeParcelable(explanation, i2);
        dest.writeInt(isTransfer);
        dest.writeInt(printCount);
        dest.writeInt(ficheref);
        dest.writeString(ficheNo);
        dest.writeDouble(enlem);
        dest.writeDouble(boylam);
        dest.writeInt(cardRef);
        dest.writeParcelable(caseCode, i2);
        dest.writeInt(dateInt);
        dest.writeString(gDate);
        dest.writeString(andFicheNo);
        dest.writeParcelable(salesMan, i2);
        dest.writeParcelable(currType, i2);
        dest.writeInt(invoiceRef);
        dest.writeDouble(clRate);
        dest.writeParcelable(clCurr, i2);
        dest.writeInt(visitInfoId);
    }

    /* compiled from: CaseFiche.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
