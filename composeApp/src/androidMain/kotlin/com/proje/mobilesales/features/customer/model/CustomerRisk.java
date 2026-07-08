package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerRisk implements Parcelable {
    public static final Parcelable.Creator<CustomerRisk> CREATOR = new Creator();

    private int accRisk;
    private double accRiskClosed;
    private double accRiskLimit;
    private double accRiskTotal;
    private double cekAsilRisk;
    private double cekCiroRisk;
    private int csTcsCiroRisk;
    private double csTcsCiroRiskClosed;
    private double csTcsCiroRiskLimit;
    private double csTcsCiroRiskTotal;
    private int csTcsRisk;
    private double csTcsRiskClosed;
    private double csTcsRiskLimit;
    private double csTcsRiskTotal;
    private int despRisk;
    private double despRiskClosed;
    private double despRiskLimit;
    private double despRiskTotal;
    private int despSugRisk;
    private double despSugRiskClosed;
    private double despSugRiskLimit;
    private double despSugRiskTotal;
    private int fatRiskDavran;
    private int irsRiskDavran;
    private double irsaliyeRiski;
    private int myCsRisk;
    private double myCsRiskClosed;
    private double myCsRiskLimit;
    private double myRiskTotal;
    private int ordRisk;
    private double ordRiskClosed;
    private double ordRiskLimit;
    private double ordRiskTotal;
    private int ordSugRisk;
    private double ordSugRiskClosed;
    private double ordSugRiskLimit;
    private double ordSugRiskTotal;
    private double riskClosed;
    private double riskLimit;
    private double riskTeminat;
    private double riskToplami;
    private double riskTotal;
    private double senetAsilRisk;
    private double senetCiroRisk;
    private int sevkRiskDavran;
    private double sevkRiski;
    private int sipRiskDavran;
    private double siparisRiski;
    private int yukRiskDavran;
    private double yuklemeRiski;
    public static final class Creator implements Parcelable.Creator<CustomerRisk> {

        public CustomerRisk createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerRisk(parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readDouble(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
        }
        public CustomerRisk[] newArray(int i2) {
            return new CustomerRisk[i2];
        }
    }

    public CustomerRisk() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0, 0, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -1, 262143, null);
    }

    public static  CustomerRisk copydefault(CustomerRisk customerRisk, double d2, double d3, double d4, double d5, double d6, int i2, double d7, double d8, double d9, int i3, double d10, double d11, double d12, int i4, double d13, double d14, double d15, int i5, double d16, double d17, double d18, int i6, double d19, double d20, double d21, int i7, double d22, double d23, double d24, int i8, double d25, double d26, double d27, int i9, double d28, int i10, int i11, int i12, int i13, int i14, double d29, double d30, double d31, double d32, double d33, double d34, double d35, double d36, double d37, double d38, int i15, int i16, Object obj) {
        double d39 = (i15 & 1) != 0 ? customerRisk.riskLimit : d2;
        double d40 = (i15 & 2) != 0 ? customerRisk.riskClosed : d3;
        double d41 = (i15 & 4) != 0 ? customerRisk.riskTotal : d4;
        double d42 = (i15 & 8) != 0 ? customerRisk.accRiskLimit : d5;
        double d43 = (i15 & 16) != 0 ? customerRisk.accRiskClosed : d6;
        int i17 = (i15 & 32) != 0 ? customerRisk.accRisk : i2;
        double d44 = (i15 & 64) != 0 ? customerRisk.accRiskTotal : d7;
        double d45 = (i15 & 128) != 0 ? customerRisk.myCsRiskLimit : d8;
        double d46 = (i15 & 256) != 0 ? customerRisk.myCsRiskClosed : d9;
        int i18 = (i15 & 512) != 0 ? customerRisk.myCsRisk : i3;
        double d47 = (i15 & 1024) != 0 ? customerRisk.myRiskTotal : d10;
        double d48 = (i15 & 2048) != 0 ? customerRisk.csTcsRiskLimit : d11;
        double d49 = (i15 & 4096) != 0 ? customerRisk.csTcsRiskClosed : d12;
        int i19 = (i15 & 8192) != 0 ? customerRisk.csTcsRisk : i4;
        double d50 = (i15 & 16384) != 0 ? customerRisk.csTcsRiskTotal : d13;
        double d51 = (i15 & 32768) != 0 ? customerRisk.csTcsCiroRiskLimit : d14;
        double d52 = (i15 & 65536) != 0 ? customerRisk.csTcsCiroRiskClosed : d15;
        int i20 = (i15 & 131072) != 0 ? customerRisk.csTcsCiroRisk : i5;
        double d53 = (262144 & i15) != 0 ? customerRisk.csTcsCiroRiskTotal : d16;
        double d54 = (i15 & 524288) != 0 ? customerRisk.despRiskLimit : d17;
        double d55 = (i15 & 1048576) != 0 ? customerRisk.despRiskClosed : d18;
        int i21 = (i15 & 2097152) != 0 ? customerRisk.despRisk : i6;
        double d56 = (4194304 & i15) != 0 ? customerRisk.despRiskTotal : d19;
        double d57 = (i15 & 8388608) != 0 ? customerRisk.despSugRiskLimit : d20;
        double d58 = (i15 & 16777216) != 0 ? customerRisk.despSugRiskClosed : d21;
        int i22 = (i15 & 33554432) != 0 ? customerRisk.despSugRisk : i7;
        double d59 = (67108864 & i15) != 0 ? customerRisk.despSugRiskTotal : d22;
        double d60 = (i15 & 134217728) != 0 ? customerRisk.ordRiskLimit : d23;
        double d61 = (i15 & 268435456) != 0 ? customerRisk.ordRiskClosed : d24;
        int i23 = (i15 & 536870912) != 0 ? customerRisk.ordRisk : i8;
        double d62 = (1073741824 & i15) != 0 ? customerRisk.ordRiskTotal : d25;
        double d63 = (i15 & Integer.MIN_VALUE) != 0 ? customerRisk.ordSugRiskLimit : d26;
        double d64 = (i16 & 1) != 0 ? customerRisk.ordSugRiskClosed : d27;
        return customerRisk.copy(d39, d40, d41, d42, d43, i17, d44, d45, d46, i18, d47, d48, d49, i19, d50, d51, d52, i20, d53, d54, d55, i21, d56, d57, d58, i22, d59, d60, d61, i23, d62, d63, d64, (i16 & 2) != 0 ? customerRisk.ordSugRisk : i9, (i16 & 4) != 0 ? customerRisk.ordSugRiskTotal : d28, (i16 & 8) != 0 ? customerRisk.sipRiskDavran : i10, (i16 & 16) != 0 ? customerRisk.sevkRiskDavran : i11, (i16 & 32) != 0 ? customerRisk.yukRiskDavran : i12, (i16 & 64) != 0 ? customerRisk.irsRiskDavran : i13, (i16 & 128) != 0 ? customerRisk.fatRiskDavran : i14, (i16 & 256) != 0 ? customerRisk.riskTeminat : d29, (i16 & 512) != 0 ? customerRisk.senetAsilRisk : d30, (i16 & 1024) != 0 ? customerRisk.senetCiroRisk : d31, (i16 & 2048) != 0 ? customerRisk.cekAsilRisk : d32, (i16 & 4096) != 0 ? customerRisk.cekCiroRisk : d33, (i16 & 8192) != 0 ? customerRisk.siparisRiski : d34, (i16 & 16384) != 0 ? customerRisk.sevkRiski : d35, (i16 & 32768) != 0 ? customerRisk.yuklemeRiski : d36, (i16 & 65536) != 0 ? customerRisk.irsaliyeRiski : d37, (i16 & 131072) != 0 ? customerRisk.riskToplami : d38);
    }

    public double component1() {
        return this.riskLimit;
    }

    public int component10() {
        return this.myCsRisk;
    }

    public double component11() {
        return this.myRiskTotal;
    }

    public double component12() {
        return this.csTcsRiskLimit;
    }

    public double component13() {
        return this.csTcsRiskClosed;
    }

    public int component14() {
        return this.csTcsRisk;
    }

    public double component15() {
        return this.csTcsRiskTotal;
    }

    public double component16() {
        return this.csTcsCiroRiskLimit;
    }

    public double component17() {
        return this.csTcsCiroRiskClosed;
    }

    public int component18() {
        return this.csTcsCiroRisk;
    }

    public double component19() {
        return this.csTcsCiroRiskTotal;
    }

    public double component2() {
        return this.riskClosed;
    }

    public double component20() {
        return this.despRiskLimit;
    }

    public double component21() {
        return this.despRiskClosed;
    }

    public int component22() {
        return this.despRisk;
    }

    public double component23() {
        return this.despRiskTotal;
    }

    public double component24() {
        return this.despSugRiskLimit;
    }

    public double component25() {
        return this.despSugRiskClosed;
    }

    public int component26() {
        return this.despSugRisk;
    }

    public double component27() {
        return this.despSugRiskTotal;
    }

    public double component28() {
        return this.ordRiskLimit;
    }

    public double component29() {
        return this.ordRiskClosed;
    }

    public double component3() {
        return this.riskTotal;
    }

    public int component30() {
        return this.ordRisk;
    }

    public double component31() {
        return this.ordRiskTotal;
    }

    public double component32() {
        return this.ordSugRiskLimit;
    }

    public double component33() {
        return this.ordSugRiskClosed;
    }

    public int component34() {
        return this.ordSugRisk;
    }

    public double component35() {
        return this.ordSugRiskTotal;
    }

    public int component36() {
        return this.sipRiskDavran;
    }

    public int component37() {
        return this.sevkRiskDavran;
    }

    public int component38() {
        return this.yukRiskDavran;
    }

    public int component39() {
        return this.irsRiskDavran;
    }

    public double component4() {
        return this.accRiskLimit;
    }

    public int component40() {
        return this.fatRiskDavran;
    }

    public double component41() {
        return this.riskTeminat;
    }

    public double component42() {
        return this.senetAsilRisk;
    }

    public double component43() {
        return this.senetCiroRisk;
    }

    public double component44() {
        return this.cekAsilRisk;
    }

    public double component45() {
        return this.cekCiroRisk;
    }

    public double component46() {
        return this.siparisRiski;
    }

    public double component47() {
        return this.sevkRiski;
    }

    public double component48() {
        return this.yuklemeRiski;
    }

    public double component49() {
        return this.irsaliyeRiski;
    }

    public double component5() {
        return this.accRiskClosed;
    }

    public double component50() {
        return this.riskToplami;
    }

    public int component6() {
        return this.accRisk;
    }

    public double component7() {
        return this.accRiskTotal;
    }

    public double component8() {
        return this.myCsRiskLimit;
    }

    public double component9() {
        return this.myCsRiskClosed;
    }

    public CustomerRisk copy(double d2, double d3, double d4, double d5, double d6, int i2, double d7, double d8, double d9, int i3, double d10, double d11, double d12, int i4, double d13, double d14, double d15, int i5, double d16, double d17, double d18, int i6, double d19, double d20, double d21, int i7, double d22, double d23, double d24, int i8, double d25, double d26, double d27, int i9, double d28, int i10, int i11, int i12, int i13, int i14, double d29, double d30, double d31, double d32, double d33, double d34, double d35, double d36, double d37, double d38) {
        return new CustomerRisk(d2, d3, d4, d5, d6, i2, d7, d8, d9, i3, d10, d11, d12, i4, d13, d14, d15, i5, d16, d17, d18, i6, d19, d20, d21, i7, d22, d23, d24, i8, d25, d26, d27, i9, d28, i10, i11, i12, i13, i14, d29, d30, d31, d32, d33, d34, d35, d36, d37, d38);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerRisk customerRisk)) {
            return false;
        }
        return Double.compare(this.riskLimit, customerRisk.riskLimit) == 0 && Double.compare(this.riskClosed, customerRisk.riskClosed) == 0 && Double.compare(this.riskTotal, customerRisk.riskTotal) == 0 && Double.compare(this.accRiskLimit, customerRisk.accRiskLimit) == 0 && Double.compare(this.accRiskClosed, customerRisk.accRiskClosed) == 0 && this.accRisk == customerRisk.accRisk && Double.compare(this.accRiskTotal, customerRisk.accRiskTotal) == 0 && Double.compare(this.myCsRiskLimit, customerRisk.myCsRiskLimit) == 0 && Double.compare(this.myCsRiskClosed, customerRisk.myCsRiskClosed) == 0 && this.myCsRisk == customerRisk.myCsRisk && Double.compare(this.myRiskTotal, customerRisk.myRiskTotal) == 0 && Double.compare(this.csTcsRiskLimit, customerRisk.csTcsRiskLimit) == 0 && Double.compare(this.csTcsRiskClosed, customerRisk.csTcsRiskClosed) == 0 && this.csTcsRisk == customerRisk.csTcsRisk && Double.compare(this.csTcsRiskTotal, customerRisk.csTcsRiskTotal) == 0 && Double.compare(this.csTcsCiroRiskLimit, customerRisk.csTcsCiroRiskLimit) == 0 && Double.compare(this.csTcsCiroRiskClosed, customerRisk.csTcsCiroRiskClosed) == 0 && this.csTcsCiroRisk == customerRisk.csTcsCiroRisk && Double.compare(this.csTcsCiroRiskTotal, customerRisk.csTcsCiroRiskTotal) == 0 && Double.compare(this.despRiskLimit, customerRisk.despRiskLimit) == 0 && Double.compare(this.despRiskClosed, customerRisk.despRiskClosed) == 0 && this.despRisk == customerRisk.despRisk && Double.compare(this.despRiskTotal, customerRisk.despRiskTotal) == 0 && Double.compare(this.despSugRiskLimit, customerRisk.despSugRiskLimit) == 0 && Double.compare(this.despSugRiskClosed, customerRisk.despSugRiskClosed) == 0 && this.despSugRisk == customerRisk.despSugRisk && Double.compare(this.despSugRiskTotal, customerRisk.despSugRiskTotal) == 0 && Double.compare(this.ordRiskLimit, customerRisk.ordRiskLimit) == 0 && Double.compare(this.ordRiskClosed, customerRisk.ordRiskClosed) == 0 && this.ordRisk == customerRisk.ordRisk && Double.compare(this.ordRiskTotal, customerRisk.ordRiskTotal) == 0 && Double.compare(this.ordSugRiskLimit, customerRisk.ordSugRiskLimit) == 0 && Double.compare(this.ordSugRiskClosed, customerRisk.ordSugRiskClosed) == 0 && this.ordSugRisk == customerRisk.ordSugRisk && Double.compare(this.ordSugRiskTotal, customerRisk.ordSugRiskTotal) == 0 && this.sipRiskDavran == customerRisk.sipRiskDavran && this.sevkRiskDavran == customerRisk.sevkRiskDavran && this.yukRiskDavran == customerRisk.yukRiskDavran && this.irsRiskDavran == customerRisk.irsRiskDavran && this.fatRiskDavran == customerRisk.fatRiskDavran && Double.compare(this.riskTeminat, customerRisk.riskTeminat) == 0 && Double.compare(this.senetAsilRisk, customerRisk.senetAsilRisk) == 0 && Double.compare(this.senetCiroRisk, customerRisk.senetCiroRisk) == 0 && Double.compare(this.cekAsilRisk, customerRisk.cekAsilRisk) == 0 && Double.compare(this.cekCiroRisk, customerRisk.cekCiroRisk) == 0 && Double.compare(this.siparisRiski, customerRisk.siparisRiski) == 0 && Double.compare(this.sevkRiski, customerRisk.sevkRiski) == 0 && Double.compare(this.yuklemeRiski, customerRisk.yuklemeRiski) == 0 && Double.compare(this.irsaliyeRiski, customerRisk.irsaliyeRiski) == 0 && Double.compare(this.riskToplami, customerRisk.riskToplami) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((Double.hashCode(this.riskLimit) * 31) + Double.hashCode(this.riskClosed)) * 31) + Double.hashCode(this.riskTotal)) * 31) + Double.hashCode(this.accRiskLimit)) * 31) + Double.hashCode(this.accRiskClosed)) * 31) + Integer.hashCode(this.accRisk)) * 31) + Double.hashCode(this.accRiskTotal)) * 31) + Double.hashCode(this.myCsRiskLimit)) * 31) + Double.hashCode(this.myCsRiskClosed)) * 31) + Integer.hashCode(this.myCsRisk)) * 31) + Double.hashCode(this.myRiskTotal)) * 31) + Double.hashCode(this.csTcsRiskLimit)) * 31) + Double.hashCode(this.csTcsRiskClosed)) * 31) + Integer.hashCode(this.csTcsRisk)) * 31) + Double.hashCode(this.csTcsRiskTotal)) * 31) + Double.hashCode(this.csTcsCiroRiskLimit)) * 31) + Double.hashCode(this.csTcsCiroRiskClosed)) * 31) + Integer.hashCode(this.csTcsCiroRisk)) * 31) + Double.hashCode(this.csTcsCiroRiskTotal)) * 31) + Double.hashCode(this.despRiskLimit)) * 31) + Double.hashCode(this.despRiskClosed)) * 31) + Integer.hashCode(this.despRisk)) * 31) + Double.hashCode(this.despRiskTotal)) * 31) + Double.hashCode(this.despSugRiskLimit)) * 31) + Double.hashCode(this.despSugRiskClosed)) * 31) + Integer.hashCode(this.despSugRisk)) * 31) + Double.hashCode(this.despSugRiskTotal)) * 31) + Double.hashCode(this.ordRiskLimit)) * 31) + Double.hashCode(this.ordRiskClosed)) * 31) + Integer.hashCode(this.ordRisk)) * 31) + Double.hashCode(this.ordRiskTotal)) * 31) + Double.hashCode(this.ordSugRiskLimit)) * 31) + Double.hashCode(this.ordSugRiskClosed)) * 31) + Integer.hashCode(this.ordSugRisk)) * 31) + Double.hashCode(this.ordSugRiskTotal)) * 31) + Integer.hashCode(this.sipRiskDavran)) * 31) + Integer.hashCode(this.sevkRiskDavran)) * 31) + Integer.hashCode(this.yukRiskDavran)) * 31) + Integer.hashCode(this.irsRiskDavran)) * 31) + Integer.hashCode(this.fatRiskDavran)) * 31) + Double.hashCode(this.riskTeminat)) * 31) + Double.hashCode(this.senetAsilRisk)) * 31) + Double.hashCode(this.senetCiroRisk)) * 31) + Double.hashCode(this.cekAsilRisk)) * 31) + Double.hashCode(this.cekCiroRisk)) * 31) + Double.hashCode(this.siparisRiski)) * 31) + Double.hashCode(this.sevkRiski)) * 31) + Double.hashCode(this.yuklemeRiski)) * 31) + Double.hashCode(this.irsaliyeRiski)) * 31) + Double.hashCode(this.riskToplami);
    }

    public String toString() {
        return "CustomerRisk(riskLimit=" + this.riskLimit + ", riskClosed=" + this.riskClosed + ", riskTotal=" + this.riskTotal + ", accRiskLimit=" + this.accRiskLimit + ", accRiskClosed=" + this.accRiskClosed + ", accRisk=" + this.accRisk + ", accRiskTotal=" + this.accRiskTotal + ", myCsRiskLimit=" + this.myCsRiskLimit + ", myCsRiskClosed=" + this.myCsRiskClosed + ", myCsRisk=" + this.myCsRisk + ", myRiskTotal=" + this.myRiskTotal + ", csTcsRiskLimit=" + this.csTcsRiskLimit + ", csTcsRiskClosed=" + this.csTcsRiskClosed + ", csTcsRisk=" + this.csTcsRisk + ", csTcsRiskTotal=" + this.csTcsRiskTotal + ", csTcsCiroRiskLimit=" + this.csTcsCiroRiskLimit + ", csTcsCiroRiskClosed=" + this.csTcsCiroRiskClosed + ", csTcsCiroRisk=" + this.csTcsCiroRisk + ", csTcsCiroRiskTotal=" + this.csTcsCiroRiskTotal + ", despRiskLimit=" + this.despRiskLimit + ", despRiskClosed=" + this.despRiskClosed + ", despRisk=" + this.despRisk + ", despRiskTotal=" + this.despRiskTotal + ", despSugRiskLimit=" + this.despSugRiskLimit + ", despSugRiskClosed=" + this.despSugRiskClosed + ", despSugRisk=" + this.despSugRisk + ", despSugRiskTotal=" + this.despSugRiskTotal + ", ordRiskLimit=" + this.ordRiskLimit + ", ordRiskClosed=" + this.ordRiskClosed + ", ordRisk=" + this.ordRisk + ", ordRiskTotal=" + this.ordRiskTotal + ", ordSugRiskLimit=" + this.ordSugRiskLimit + ", ordSugRiskClosed=" + this.ordSugRiskClosed + ", ordSugRisk=" + this.ordSugRisk + ", ordSugRiskTotal=" + this.ordSugRiskTotal + ", sipRiskDavran=" + this.sipRiskDavran + ", sevkRiskDavran=" + this.sevkRiskDavran + ", yukRiskDavran=" + this.yukRiskDavran + ", irsRiskDavran=" + this.irsRiskDavran + ", fatRiskDavran=" + this.fatRiskDavran + ", riskTeminat=" + this.riskTeminat + ", senetAsilRisk=" + this.senetAsilRisk + ", senetCiroRisk=" + this.senetCiroRisk + ", cekAsilRisk=" + this.cekAsilRisk + ", cekCiroRisk=" + this.cekCiroRisk + ", siparisRiski=" + this.siparisRiski + ", sevkRiski=" + this.sevkRiski + ", yuklemeRiski=" + this.yuklemeRiski + ", irsaliyeRiski=" + this.irsaliyeRiski + ", riskToplami=" + this.riskToplami + ')';
    }
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.riskLimit);
        out.writeDouble(this.riskClosed);
        out.writeDouble(this.riskTotal);
        out.writeDouble(this.accRiskLimit);
        out.writeDouble(this.accRiskClosed);
        out.writeInt(this.accRisk);
        out.writeDouble(this.accRiskTotal);
        out.writeDouble(this.myCsRiskLimit);
        out.writeDouble(this.myCsRiskClosed);
        out.writeInt(this.myCsRisk);
        out.writeDouble(this.myRiskTotal);
        out.writeDouble(this.csTcsRiskLimit);
        out.writeDouble(this.csTcsRiskClosed);
        out.writeInt(this.csTcsRisk);
        out.writeDouble(this.csTcsRiskTotal);
        out.writeDouble(this.csTcsCiroRiskLimit);
        out.writeDouble(this.csTcsCiroRiskClosed);
        out.writeInt(this.csTcsCiroRisk);
        out.writeDouble(this.csTcsCiroRiskTotal);
        out.writeDouble(this.despRiskLimit);
        out.writeDouble(this.despRiskClosed);
        out.writeInt(this.despRisk);
        out.writeDouble(this.despRiskTotal);
        out.writeDouble(this.despSugRiskLimit);
        out.writeDouble(this.despSugRiskClosed);
        out.writeInt(this.despSugRisk);
        out.writeDouble(this.despSugRiskTotal);
        out.writeDouble(this.ordRiskLimit);
        out.writeDouble(this.ordRiskClosed);
        out.writeInt(this.ordRisk);
        out.writeDouble(this.ordRiskTotal);
        out.writeDouble(this.ordSugRiskLimit);
        out.writeDouble(this.ordSugRiskClosed);
        out.writeInt(this.ordSugRisk);
        out.writeDouble(this.ordSugRiskTotal);
        out.writeInt(this.sipRiskDavran);
        out.writeInt(this.sevkRiskDavran);
        out.writeInt(this.yukRiskDavran);
        out.writeInt(this.irsRiskDavran);
        out.writeInt(this.fatRiskDavran);
        out.writeDouble(this.riskTeminat);
        out.writeDouble(this.senetAsilRisk);
        out.writeDouble(this.senetCiroRisk);
        out.writeDouble(this.cekAsilRisk);
        out.writeDouble(this.cekCiroRisk);
        out.writeDouble(this.siparisRiski);
        out.writeDouble(this.sevkRiski);
        out.writeDouble(this.yuklemeRiski);
        out.writeDouble(this.irsaliyeRiski);
        out.writeDouble(this.riskToplami);
    }

    public CustomerRisk(double d2, double d3, double d4, double d5, double d6, int i2, double d7, double d8, double d9, int i3, double d10, double d11, double d12, int i4, double d13, double d14, double d15, int i5, double d16, double d17, double d18, int i6, double d19, double d20, double d21, int i7, double d22, double d23, double d24, int i8, double d25, double d26, double d27, int i9, double d28, int i10, int i11, int i12, int i13, int i14, double d29, double d30, double d31, double d32, double d33, double d34, double d35, double d36, double d37, double d38) {
        this.riskLimit = d2;
        this.riskClosed = d3;
        this.riskTotal = d4;
        this.accRiskLimit = d5;
        this.accRiskClosed = d6;
        this.accRisk = i2;
        this.accRiskTotal = d7;
        this.myCsRiskLimit = d8;
        this.myCsRiskClosed = d9;
        this.myCsRisk = i3;
        this.myRiskTotal = d10;
        this.csTcsRiskLimit = d11;
        this.csTcsRiskClosed = d12;
        this.csTcsRisk = i4;
        this.csTcsRiskTotal = d13;
        this.csTcsCiroRiskLimit = d14;
        this.csTcsCiroRiskClosed = d15;
        this.csTcsCiroRisk = i5;
        this.csTcsCiroRiskTotal = d16;
        this.despRiskLimit = d17;
        this.despRiskClosed = d18;
        this.despRisk = i6;
        this.despRiskTotal = d19;
        this.despSugRiskLimit = d20;
        this.despSugRiskClosed = d21;
        this.despSugRisk = i7;
        this.despSugRiskTotal = d22;
        this.ordRiskLimit = d23;
        this.ordRiskClosed = d24;
        this.ordRisk = i8;
        this.ordRiskTotal = d25;
        this.ordSugRiskLimit = d26;
        this.ordSugRiskClosed = d27;
        this.ordSugRisk = i9;
        this.ordSugRiskTotal = d28;
        this.sipRiskDavran = i10;
        this.sevkRiskDavran = i11;
        this.yukRiskDavran = i12;
        this.irsRiskDavran = i13;
        this.fatRiskDavran = i14;
        this.riskTeminat = d29;
        this.senetAsilRisk = d30;
        this.senetCiroRisk = d31;
        this.cekAsilRisk = d32;
        this.cekCiroRisk = d33;
        this.siparisRiski = d34;
        this.sevkRiski = d35;
        this.yuklemeRiski = d36;
        this.irsaliyeRiski = d37;
        this.riskToplami = d38;
    }
    public  CustomerRisk(double d2, double d3, double d4, double d5, double d6, int i2, double d7, double d8, double d9, int i3, double d10, double d11, double d12, int i4, double d13, double d14, double d15, int i5, double d16, double d17, double d18, int i6, double d19, double d20, double d21, int i7, double d22, double d23, double d24, int i8, double d25, double d26, double d27, int i9, double d28, int i10, int i11, int i12, int i13, int i14, double d29, double d30, double d31, double d32, double d33, double d34, double d35, double d36, double d37, double d38, int i15, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        this(r5, r7, r9, r11, r13, r2, r16, r3, r20, r179, r22, r24, r26, r28, r29, r31, r34, r37, r38, r40, r42, r44, r45, r47, r49, r51, r52, r54, r56, r58, r59, r61, r63, r0, r65, r67, r68, r69, r70, r177, r71, r73, r75, r77, r79, r81, r83, (r15 & i16) != 0 ? 0.0d : d36, (i16 & 65536) != 0 ? 0.0d : d37, (i16 & 131072) != 0 ? 0.0d : d38);
        double d39;
        int i17;
        double d40 = (i15 & 1) != 0 ? 0.0d : d2;
        double d41 = (i15 & 2) != 0 ? 0.0d : d3;
        double d42 = (i15 & 4) != 0 ? 0.0d : d4;
        double d43 = (i15 & 8) != 0 ? 0.0d : d5;
        double d44 = (i15 & 16) != 0 ? 0.0d : d6;
        int i18 = (i15 & 32) != 0 ? 0 : i2;
        double d45 = (i15 & 64) != 0 ? 0.0d : d7;
        double d46 = (i15 & 128) != 0 ? 0.0d : d8;
        double d47 = (i15 & 256) != 0 ? 0.0d : d9;
        int i19 = (i15 & 512) != 0 ? 0 : i3;
        double d48 = (i15 & 1024) != 0 ? 0.0d : d10;
        double d49 = (i15 & 2048) != 0 ? 0.0d : d11;
        double d50 = (i15 & 4096) != 0 ? 0.0d : d12;
        int i20 = (i15 & 8192) != 0 ? 0 : i4;
        double d51 = (i15 & 16384) != 0 ? 0.0d : d13;
        double d52 = (i15 & 32768) != 0 ? 0.0d : d14;
        double d53 = (i15 & 65536) != 0 ? 0.0d : d15;
        int i21 = (i15 & 131072) != 0 ? 0 : i5;
        double d54 = (i15 & 262144) != 0 ? 0.0d : d16;
        double d55 = (i15 & 524288) != 0 ? 0.0d : d17;
        double d56 = (i15 & 1048576) != 0 ? 0.0d : d18;
        int i22 = (i15 & 2097152) != 0 ? 0 : i6;
        double d57 = (i15 & 4194304) != 0 ? 0.0d : d19;
        double d58 = (i15 & 8388608) != 0 ? 0.0d : d20;
        double d59 = (i15 & 16777216) != 0 ? 0.0d : d21;
        int i23 = (i15 & 33554432) != 0 ? 0 : i7;
        double d60 = (i15 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0.0d : d22;
        double d61 = (i15 & 134217728) != 0 ? 0.0d : d23;
        double d62 = (i15 & 268435456) != 0 ? 0.0d : d24;
        int i24 = (i15 & 536870912) != 0 ? 0 : i8;
        double d63 = (i15 & BasicMeasure.EXACTLY) != 0 ? 0.0d : d25;
        double d64 = (i15 & Integer.MIN_VALUE) != 0 ? 0.0d : d26;
        double d65 = (i16 & 1) != 0 ? 0.0d : d27;
        int i25 = (i16 & 2) != 0 ? 0 : i9;
        double d66 = (i16 & 4) != 0 ? 0.0d : d28;
        int i26 = (i16 & 8) != 0 ? 0 : i10;
        int i27 = (i16 & 16) != 0 ? 0 : i11;
        int i28 = (i16 & 32) != 0 ? 0 : i12;
        int i29 = (i16 & 64) != 0 ? 0 : i13;
        int i30 = (i16 & 128) != 0 ? 0 : i14;
        double d67 = (i16 & 256) != 0 ? 0.0d : d29;
        double d68 = (i16 & 512) != 0 ? 0.0d : d30;
        double d69 = (i16 & 1024) != 0 ? 0.0d : d31;
        double d70 = (i16 & 2048) != 0 ? 0.0d : d32;
        double d71 = (i16 & 4096) != 0 ? 0.0d : d33;
        double d72 = (i16 & 8192) != 0 ? 0.0d : d34;
        if ((i16 & 16384) != 0) {
            i17 = 32768;
            d39 = 0.0d;
        } else {
            d39 = d35;
            i17 = 32768;
        }
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

    public int getAccRisk() {
        return this.accRisk;
    }

    public void setAccRisk(int i2) {
        this.accRisk = i2;
    }

    public double getAccRiskTotal() {
        return this.accRiskTotal;
    }

    public void setAccRiskTotal(double d2) {
        this.accRiskTotal = d2;
    }

    public double getMyCsRiskLimit() {
        return this.myCsRiskLimit;
    }

    public void setMyCsRiskLimit(double d2) {
        this.myCsRiskLimit = d2;
    }

    public double getMyCsRiskClosed() {
        return this.myCsRiskClosed;
    }

    public void setMyCsRiskClosed(double d2) {
        this.myCsRiskClosed = d2;
    }

    public int getMyCsRisk() {
        return this.myCsRisk;
    }

    public void setMyCsRisk(int i2) {
        this.myCsRisk = i2;
    }

    public double getMyRiskTotal() {
        return this.myRiskTotal;
    }

    public void setMyRiskTotal(double d2) {
        this.myRiskTotal = d2;
    }

    public double getCsTcsRiskLimit() {
        return this.csTcsRiskLimit;
    }

    public void setCsTcsRiskLimit(double d2) {
        this.csTcsRiskLimit = d2;
    }

    public double getCsTcsRiskClosed() {
        return this.csTcsRiskClosed;
    }

    public void setCsTcsRiskClosed(double d2) {
        this.csTcsRiskClosed = d2;
    }

    public int getCsTcsRisk() {
        return this.csTcsRisk;
    }

    public void setCsTcsRisk(int i2) {
        this.csTcsRisk = i2;
    }

    public double getCsTcsRiskTotal() {
        return this.csTcsRiskTotal;
    }

    public void setCsTcsRiskTotal(double d2) {
        this.csTcsRiskTotal = d2;
    }

    public double getCsTcsCiroRiskLimit() {
        return this.csTcsCiroRiskLimit;
    }

    public void setCsTcsCiroRiskLimit(double d2) {
        this.csTcsCiroRiskLimit = d2;
    }

    public double getCsTcsCiroRiskClosed() {
        return this.csTcsCiroRiskClosed;
    }

    public void setCsTcsCiroRiskClosed(double d2) {
        this.csTcsCiroRiskClosed = d2;
    }

    public int getCsTcsCiroRisk() {
        return this.csTcsCiroRisk;
    }

    public void setCsTcsCiroRisk(int i2) {
        this.csTcsCiroRisk = i2;
    }

    public double getCsTcsCiroRiskTotal() {
        return this.csTcsCiroRiskTotal;
    }

    public void setCsTcsCiroRiskTotal(double d2) {
        this.csTcsCiroRiskTotal = d2;
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

    public int getDespRisk() {
        return this.despRisk;
    }

    public void setDespRisk(int i2) {
        this.despRisk = i2;
    }

    public double getDespRiskTotal() {
        return this.despRiskTotal;
    }

    public void setDespRiskTotal(double d2) {
        this.despRiskTotal = d2;
    }

    public double getDespSugRiskLimit() {
        return this.despSugRiskLimit;
    }

    public void setDespSugRiskLimit(double d2) {
        this.despSugRiskLimit = d2;
    }

    public double getDespSugRiskClosed() {
        return this.despSugRiskClosed;
    }

    public void setDespSugRiskClosed(double d2) {
        this.despSugRiskClosed = d2;
    }

    public int getDespSugRisk() {
        return this.despSugRisk;
    }

    public void setDespSugRisk(int i2) {
        this.despSugRisk = i2;
    }

    public double getDespSugRiskTotal() {
        return this.despSugRiskTotal;
    }

    public void setDespSugRiskTotal(double d2) {
        this.despSugRiskTotal = d2;
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

    public int getOrdRisk() {
        return this.ordRisk;
    }

    public void setOrdRisk(int i2) {
        this.ordRisk = i2;
    }

    public double getOrdRiskTotal() {
        return this.ordRiskTotal;
    }

    public void setOrdRiskTotal(double d2) {
        this.ordRiskTotal = d2;
    }

    public double getOrdSugRiskLimit() {
        return this.ordSugRiskLimit;
    }

    public void setOrdSugRiskLimit(double d2) {
        this.ordSugRiskLimit = d2;
    }

    public double getOrdSugRiskClosed() {
        return this.ordSugRiskClosed;
    }

    public void setOrdSugRiskClosed(double d2) {
        this.ordSugRiskClosed = d2;
    }

    public int getOrdSugRisk() {
        return this.ordSugRisk;
    }

    public void setOrdSugRisk(int i2) {
        this.ordSugRisk = i2;
    }

    public double getOrdSugRiskTotal() {
        return this.ordSugRiskTotal;
    }

    public void setOrdSugRiskTotal(double d2) {
        this.ordSugRiskTotal = d2;
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

    public double getRiskTeminat() {
        return this.riskTeminat;
    }

    public void setRiskTeminat(double d2) {
        this.riskTeminat = d2;
    }

    public double getSenetAsilRisk() {
        return this.senetAsilRisk;
    }

    public void setSenetAsilRisk(double d2) {
        this.senetAsilRisk = d2;
    }

    public double getSenetCiroRisk() {
        return this.senetCiroRisk;
    }

    public void setSenetCiroRisk(double d2) {
        this.senetCiroRisk = d2;
    }

    public double getCekAsilRisk() {
        return this.cekAsilRisk;
    }

    public void setCekAsilRisk(double d2) {
        this.cekAsilRisk = d2;
    }

    public double getCekCiroRisk() {
        return this.cekCiroRisk;
    }

    public void setCekCiroRisk(double d2) {
        this.cekCiroRisk = d2;
    }

    public double getSiparisRiski() {
        return this.siparisRiski;
    }

    public void setSiparisRiski(double d2) {
        this.siparisRiski = d2;
    }

    public double getSevkRiski() {
        return this.sevkRiski;
    }

    public void setSevkRiski(double d2) {
        this.sevkRiski = d2;
    }

    public double getYuklemeRiski() {
        return this.yuklemeRiski;
    }

    public void setYuklemeRiski(double d2) {
        this.yuklemeRiski = d2;
    }

    public double getIrsaliyeRiski() {
        return this.irsaliyeRiski;
    }

    public void setIrsaliyeRiski(double d2) {
        this.irsaliyeRiski = d2;
    }

    public double getRiskToplami() {
        return this.riskToplami;
    }

    public void setRiskToplami(double d2) {
        this.riskToplami = d2;
    }
}
