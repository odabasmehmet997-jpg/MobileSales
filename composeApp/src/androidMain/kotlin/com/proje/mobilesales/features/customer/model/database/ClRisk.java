package com.proje.mobilesales.features.customer.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.enums.RiskControlType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ClRisk {
    private double accRiskClosed;
    private double accRiskLimit;
    private double accRiskTotal;
    private int clRef;
    private String code;
    private double despRiskClosed;
    private double despRiskClosedSug;
    private double despRiskLimit;
    private double despRiskLimitSug;
    private double despRiskTotal;
    private double despRiskTotalSug;
    private double ordRiskClosed;
    private double ordRiskClosedSug;
    private double ordRiskLimit;
    private double ordRiskLimitSug;
    private double ordRiskTotal;
    private double ordRiskTotalSug;
    private double riskClosed;
    private int riskControlType;
    private double riskLimit;
    private double riskTotal;
    public ClRisk() {
        this(0, null, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2097151, null);
    }

    public int component1() {
        return this.clRef;
    }

    public double component10() {
        return this.despRiskLimit;
    }

    public double component11() {
        return this.despRiskClosed;
    }

    public double component12() {
        return this.despRiskTotal;
    }

    public double component13() {
        return this.despRiskLimitSug;
    }

    public double component14() {
        return this.despRiskClosedSug;
    }

    public double component15() {
        return this.despRiskTotalSug;
    }

    public double component16() {
        return this.ordRiskLimit;
    }

    public double component17() {
        return this.ordRiskClosed;
    }

    public double component18() {
        return this.ordRiskTotal;
    }

    public double component19() {
        return this.ordRiskLimitSug;
    }

    public String component2() {
        return this.code;
    }

    public double component20() {
        return this.ordRiskClosedSug;
    }

    public double component21() {
        return this.ordRiskTotalSug;
    }

    public double component3() {
        return this.riskLimit;
    }

    public double component4() {
        return this.riskClosed;
    }

    public double component5() {
        return this.riskTotal;
    }

    public int component6() {
        return this.riskControlType;
    }

    public double component7() {
        return this.accRiskLimit;
    }

    public double component8() {
        return this.accRiskClosed;
    }

    public double component9() {
        return this.accRiskTotal;
    }

    public ClRisk copy(int i2, String str, double d2, double d3, double d4, int i3, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19) {
        return new ClRisk(i2, str, d2, d3, d4, i3, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClRisk clRisk)) {
            return false;
        }
        return this.clRef == clRisk.clRef && Intrinsics.areEqual(this.code, clRisk.code) && Double.compare(this.riskLimit, clRisk.riskLimit) == 0 && Double.compare(this.riskClosed, clRisk.riskClosed) == 0 && Double.compare(this.riskTotal, clRisk.riskTotal) == 0 && this.riskControlType == clRisk.riskControlType && Double.compare(this.accRiskLimit, clRisk.accRiskLimit) == 0 && Double.compare(this.accRiskClosed, clRisk.accRiskClosed) == 0 && Double.compare(this.accRiskTotal, clRisk.accRiskTotal) == 0 && Double.compare(this.despRiskLimit, clRisk.despRiskLimit) == 0 && Double.compare(this.despRiskClosed, clRisk.despRiskClosed) == 0 && Double.compare(this.despRiskTotal, clRisk.despRiskTotal) == 0 && Double.compare(this.despRiskLimitSug, clRisk.despRiskLimitSug) == 0 && Double.compare(this.despRiskClosedSug, clRisk.despRiskClosedSug) == 0 && Double.compare(this.despRiskTotalSug, clRisk.despRiskTotalSug) == 0 && Double.compare(this.ordRiskLimit, clRisk.ordRiskLimit) == 0 && Double.compare(this.ordRiskClosed, clRisk.ordRiskClosed) == 0 && Double.compare(this.ordRiskTotal, clRisk.ordRiskTotal) == 0 && Double.compare(this.ordRiskLimitSug, clRisk.ordRiskLimitSug) == 0 && Double.compare(this.ordRiskClosedSug, clRisk.ordRiskClosedSug) == 0 && Double.compare(this.ordRiskTotalSug, clRisk.ordRiskTotalSug) == 0;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.clRef) * 31;
        String str = this.code;
        return ((((((((((((((((((((((((((((((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Double.hashCode(this.riskLimit)) * 31) + Double.hashCode(this.riskClosed)) * 31) + Double.hashCode(this.riskTotal)) * 31) + Integer.hashCode(this.riskControlType)) * 31) + Double.hashCode(this.accRiskLimit)) * 31) + Double.hashCode(this.accRiskClosed)) * 31) + Double.hashCode(this.accRiskTotal)) * 31) + Double.hashCode(this.despRiskLimit)) * 31) + Double.hashCode(this.despRiskClosed)) * 31) + Double.hashCode(this.despRiskTotal)) * 31) + Double.hashCode(this.despRiskLimitSug)) * 31) + Double.hashCode(this.despRiskClosedSug)) * 31) + Double.hashCode(this.despRiskTotalSug)) * 31) + Double.hashCode(this.ordRiskLimit)) * 31) + Double.hashCode(this.ordRiskClosed)) * 31) + Double.hashCode(this.ordRiskTotal)) * 31) + Double.hashCode(this.ordRiskLimitSug)) * 31) + Double.hashCode(this.ordRiskClosedSug)) * 31) + Double.hashCode(this.ordRiskTotalSug);
    }

    public String toString() {
        return "ClRisk(clRef=" + this.clRef + ", code=" + this.code + ", riskLimit=" + this.riskLimit + ", riskClosed=" + this.riskClosed + ", riskTotal=" + this.riskTotal + ", riskControlType=" + this.riskControlType + ", accRiskLimit=" + this.accRiskLimit + ", accRiskClosed=" + this.accRiskClosed + ", accRiskTotal=" + this.accRiskTotal + ", despRiskLimit=" + this.despRiskLimit + ", despRiskClosed=" + this.despRiskClosed + ", despRiskTotal=" + this.despRiskTotal + ", despRiskLimitSug=" + this.despRiskLimitSug + ", despRiskClosedSug=" + this.despRiskClosedSug + ", despRiskTotalSug=" + this.despRiskTotalSug + ", ordRiskLimit=" + this.ordRiskLimit + ", ordRiskClosed=" + this.ordRiskClosed + ", ordRiskTotal=" + this.ordRiskTotal + ", ordRiskLimitSug=" + this.ordRiskLimitSug + ", ordRiskClosedSug=" + this.ordRiskClosedSug + ", ordRiskTotalSug=" + this.ordRiskTotalSug + ')';
    }

    public ClRisk(int i2, String str, double d2, double d3, double d4, int i3, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19) {
        this.clRef = i2;
        this.code = str;
        this.riskLimit = d2;
        this.riskClosed = d3;
        this.riskTotal = d4;
        this.riskControlType = i3;
        this.accRiskLimit = d5;
        this.accRiskClosed = d6;
        this.accRiskTotal = d7;
        this.despRiskLimit = d8;
        this.despRiskClosed = d9;
        this.despRiskTotal = d10;
        this.despRiskLimitSug = d11;
        this.despRiskClosedSug = d12;
        this.despRiskTotalSug = d13;
        this.ordRiskLimit = d14;
        this.ordRiskClosed = d15;
        this.ordRiskTotal = d16;
        this.ordRiskLimitSug = d17;
        this.ordRiskClosedSug = d18;
        this.ordRiskTotalSug = d19;
    }

    public ClRisk(int i2, String str, double d2, double d3, double d4, int i3, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17, double d18, double d19, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i2, (i4 & 2) != 0 ? null : str, (i4 & 4) != 0 ? 0.0d : d2, (i4 & 8) != 0 ? 0.0d : d3, (i4 & 16) != 0 ? 0.0d : d4, (i4 & 32) == 0 ? i3 : 0, (i4 & 64) != 0 ? 0.0d : d5, (i4 & 128) != 0 ? 0.0d : d6, (i4 & 256) != 0 ? 0.0d : d7, (i4 & 512) != 0 ? 0.0d : d8, (i4 & 1024) != 0 ? 0.0d : d9, (i4 & 2048) != 0 ? 0.0d : d10, (i4 & 4096) != 0 ? 0.0d : d11, (i4 & 8192) != 0 ? 0.0d : d12, (i4 & 16384) != 0 ? 0.0d : d13, (32768 & i4) != 0 ? 0.0d : d14, (65536 & i4) != 0 ? 0.0d : d15, (131072 & i4) != 0 ? 0.0d : d16, (262144 & i4) != 0 ? 0.0d : d17, (524288 & i4) != 0 ? 0.0d : d18, (i4 & 1048576) == 0 ? d19 : 0.0d);
    }

    public int getClRef() {
        return this.clRef;
    }

    public void setClRef(int i2) {
        this.clRef = i2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public double getRiskLimit() {
        return this.riskLimit;
    }

    public void setRiskLimit(double d2) {
        this.riskLimit = d2;
    }

    public double getRiskClosed() {
        return this.riskClosed;
    }

    public void setRiskClosed(double d2) {
        this.riskClosed = d2;
    }

    public double getRiskTotal() {
        return this.riskTotal;
    }

    public void setRiskTotal(double d2) {
        this.riskTotal = d2;
    }

    public int getRiskControlType() {
        return this.riskControlType;
    }

    public void setRiskControlType(int i2) {
        this.riskControlType = i2;
    }

    public double getAccRiskLimit() {
        return this.accRiskLimit;
    }

    public void setAccRiskLimit(double d2) {
        this.accRiskLimit = d2;
    }

    public double getAccRiskClosed() {
        return this.accRiskClosed;
    }

    public void setAccRiskClosed(double d2) {
        this.accRiskClosed = d2;
    }

    public double getAccRiskTotal() {
        return this.accRiskTotal;
    }

    public void setAccRiskTotal(double d2) {
        this.accRiskTotal = d2;
    }

    public double getDespRiskLimit() {
        return this.despRiskLimit;
    }

    public void setDespRiskLimit(double d2) {
        this.despRiskLimit = d2;
    }

    public double getDespRiskClosed() {
        return this.despRiskClosed;
    }

    public void setDespRiskClosed(double d2) {
        this.despRiskClosed = d2;
    }

    public double getDespRiskTotal() {
        return this.despRiskTotal;
    }

    public void setDespRiskTotal(double d2) {
        this.despRiskTotal = d2;
    }

    public double getDespRiskLimitSug() {
        return this.despRiskLimitSug;
    }

    public void setDespRiskLimitSug(double d2) {
        this.despRiskLimitSug = d2;
    }

    public double getDespRiskClosedSug() {
        return this.despRiskClosedSug;
    }

    public void setDespRiskClosedSug(double d2) {
        this.despRiskClosedSug = d2;
    }

    public double getDespRiskTotalSug() {
        return this.despRiskTotalSug;
    }

    public void setDespRiskTotalSug(double d2) {
        this.despRiskTotalSug = d2;
    }

    public double getOrdRiskLimit() {
        return this.ordRiskLimit;
    }

    public void setOrdRiskLimit(double d2) {
        this.ordRiskLimit = d2;
    }

    public double getOrdRiskClosed() {
        return this.ordRiskClosed;
    }

    public void setOrdRiskClosed(double d2) {
        this.ordRiskClosed = d2;
    }

    public double getOrdRiskTotal() {
        return this.ordRiskTotal;
    }

    public void setOrdRiskTotal(double d2) {
        this.ordRiskTotal = d2;
    }

    public double getOrdRiskLimitSug() {
        return this.ordRiskLimitSug;
    }

    public void setOrdRiskLimitSug(double d2) {
        this.ordRiskLimitSug = d2;
    }

    public double getOrdRiskClosedSug() {
        return this.ordRiskClosedSug;
    }

    public void setOrdRiskClosedSug(double d2) {
        this.ordRiskClosedSug = d2;
    }

    public double getOrdRiskTotalSug() {
        return this.ordRiskTotalSug;
    }

    public void setOrdRiskTotalSug(double d2) {
        this.ordRiskTotalSug = d2;
    }

    public RiskControlType getRiskType() {
        return RiskControlType.Companion.setRiskControlType(this.riskControlType);
    }
}
