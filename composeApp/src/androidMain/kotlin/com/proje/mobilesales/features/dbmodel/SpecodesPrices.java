package com.proje.mobilesales.features.dbmodel;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SpecodesPrices {

    private String branchCode;
    private String specode1;
    private String specode11;
    private String specode12;
    private String specode21;
    private String specode22;
    private String specode31;
    private String specode32;
    private String specode41;
    private String specode42;
    public SpecodesPrices() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }
    public String component1() {
        return this.specode1;
    }
    public String component10() {
        return this.branchCode;
    }
    public String component2() {
        return this.specode11;
    }
    public String component3() {
        return this.specode12;
    }
    public String component4() {
        return this.specode21;
    }
    public String component5() {
        return this.specode22;
    }
    public String component6() {
        return this.specode31;
    }
    public String component7() {
        return this.specode32;
    }
    public String component8() {
        return this.specode41;
    }
    public String component9() {
        return this.specode42;
    }
    public SpecodesPrices copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        return new SpecodesPrices(str, str2, str3, str4, str5, str6, str7, str8, str9, str10);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpecodesPrices specodesPrices)) {
            return false;
        }
        return Intrinsics.areEqual(this.specode1, specodesPrices.specode1) && Intrinsics.areEqual(this.specode11, specodesPrices.specode11) && Intrinsics.areEqual(this.specode12, specodesPrices.specode12) && Intrinsics.areEqual(this.specode21, specodesPrices.specode21) && Intrinsics.areEqual(this.specode22, specodesPrices.specode22) && Intrinsics.areEqual(this.specode31, specodesPrices.specode31) && Intrinsics.areEqual(this.specode32, specodesPrices.specode32) && Intrinsics.areEqual(this.specode41, specodesPrices.specode41) && Intrinsics.areEqual(this.specode42, specodesPrices.specode42) && Intrinsics.areEqual(this.branchCode, specodesPrices.branchCode);
    }
    public int hashCode() {
        String str = this.specode1;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.specode11;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.specode12;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.specode21;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.specode22;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.specode31;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.specode32;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.specode41;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.specode42;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.branchCode;
        return hashCode9 + (str10 != null ? str10.hashCode() : 0);
    }
    public String toString() {
        return "SpecodePrices(specode1=" + this.specode1 + ", specode11=" + this.specode11 + ", specode12=" + this.specode12 + ", specode21=" + this.specode21 + ", specode22=" + this.specode22 + ", specode31=" + this.specode31 + ", specode32=" + this.specode32 + ", specode41=" + this.specode41 + ", specode42=" + this.specode42 + ", branchCode=" + this.branchCode + ')';
    }
    public SpecodesPrices(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.specode1 = str;
        this.specode11 = str2;
        this.specode12 = str3;
        this.specode21 = str4;
        this.specode22 = str5;
        this.specode31 = str6;
        this.specode32 = str7;
        this.specode41 = str8;
        this.specode42 = str9;
        this.branchCode = str10;
    }
    public SpecodesPrices(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : str9, (i2 & 512) == 0 ? str10 : null);
    }
    public String getSpecode1() {
        return this.specode1;
    }
    public void setSpecode1(String str) {
        this.specode1 = str;
    }
    public String getSpecode11() {
        return this.specode11;
    }
    public void setSpecode11(String str) {
        this.specode11 = str;
    }
    public String getSpecode12() {
        return this.specode12;
    }
    public void setSpecode12(String str) {
        this.specode12 = str;
    }
    public String getSpecode21() {
        return this.specode21;
    }
    public void setSpecode21(String str) {
        this.specode21 = str;
    }
    public String getSpecode22() {
        return this.specode22;
    }
    public void setSpecode22(String str) {
        this.specode22 = str;
    }
    public String getSpecode31() {
        return this.specode31;
    }
    public void setSpecode31(String str) {
        this.specode31 = str;
    }
    public String getSpecode32() {
        return this.specode32;
    }
    public void setSpecode32(String str) {
        this.specode32 = str;
    }
    public String getSpecode41() {
        return this.specode41;
    }
    public void setSpecode41(String str) {
        this.specode41 = str;
    }
    public String getSpecode42() {
        return this.specode42;
    }
    public void setSpecode42(String str) {
        this.specode42 = str;
    }
    public String getBranchCode() {
        return this.branchCode;
    }
    public void setBranchCode(String str) {
        this.branchCode = str;
    }
}
