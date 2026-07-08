package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class Distribution implements Parcelable {

    @Column(name = "BRANCH")
    private int branch;

    @Column(name = "CAMPAIGNREFS1")
    private int campaignRef1;

    @Column(name = "CAMPAIGNREFS2")
    private int campaignRef2;

    @Column(name = "CAMPAIGNREFS3")
    private int campaignRef3;

    @Column(name = "CAMPAIGNREFS4")
    private int campaignRef4;

    @Column(name = "CAMPAIGNREFS5")
    private int campaignRef5;

    @Column(name = "CLIENTREF")
    public int clientRef;

    @Column(name = "CODE")
    public String code;

    @Column(name = "DATE_")
    private String date;

    @Column(name = "DEFINITION_")
    public String definition;

    @Column(name = "DEFINITION2")
    private String definition2;

    @Column(name = "DISTORDERREF")
    public int distorderRef;

    @Column(name = "DORDSTATUS")
    private int dordStatus;

    @Column(name = "DUEDATE")
    private String dueDate;

    @Column(name = "FACTORY")
    private int factory;

    @Column(name = "FICHENO")
    public String ficheNo;

    @Column(name = "GODATE")
    public String goDate;

    @Column(name = "ITEMREF")
    private int itemRef;

    @Column(name = "LOGICALREF")
    private int logicalRef;

    @Column(name = "ORDFICHEREF")
    private int ordFicheRef;

    @Column(name = "ORDLINEREF")
    private int ordLineRef;

    @Column(name = "POINTCAMPREF")
    private int pointCampRef;

    @Column(name = "REMAMOUNT")
    private double reAmount;

    @Column(name = "SALESMAN")
    private int salesMan;

    @Column(name = "SHIPAMOUNT")
    private double shipAmount;

    @Column(name = "SHIPINFOCODE")
    public String shipinfoCode;

    @Column(name = "SHIPINFONAME")
    public String shipinfoName;

    @Column(name = "SHIPINFOREF")
    private int shipinfoRef;

    @Column(name = "SOURCEINDEX")
    private int sourceIndex;

    @Column(name = "UOMREF")
    private int uomRef;

    @Column(name = "VARIANTREF")
    private int variantRef;
    public static final Companion Companion = new Companion(null);
    public static final Creator<Distribution> CREATOR = new Creator<Distribution>() {  
        public Distribution createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new Distribution(source);
        } 
        public Distribution[] newArray(int r1) {
            return new Distribution[r1];
        }
    };
    public Distribution() {
        this(0, null, null, null, null, null, 0, 0.0d, 0.0d, 0, 0, 0, 0, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, Integer.MAX_VALUE, null);
    }
    public int component1() {
        return this.logicalRef;
    }
    public int component10() {
        return this.itemRef;
    }
    public int component11() {
        return this.ordFicheRef;
    }
    public int component12() {
        return this.ordLineRef;
    }

    public int component13() {
        return this.clientRef;
    }

    public int component14() {
        return this.salesMan;
    }

    public String component15() {
        return this.date;
    }

    public String component16() {
        return this.dueDate;
    }

    public int component17() {
        return this.uomRef;
    }

    public int component18() {
        return this.branch;
    }

    public int component19() {
        return this.factory;
    }

    public String component2() {
        return this.code;
    }

    public int component20() {
        return this.sourceIndex;
    }

    public int component21() {
        return this.campaignRef1;
    }

    public int component22() {
        return this.campaignRef2;
    }

    public int component23() {
        return this.campaignRef3;
    }

    public int component24() {
        return this.campaignRef4;
    }

    public int component25() {
        return this.campaignRef5;
    }

    public int component26() {
        return this.pointCampRef;
    }

    public int component27() {
        return this.dordStatus;
    }

    public int component28() {
        return this.variantRef;
    }

    public int component29() {
        return this.shipinfoRef;
    }

    public String component3() {
        return this.definition;
    }

    public String component30() {
        return this.shipinfoCode;
    }

    public String component31() {
        return this.shipinfoName;
    }

    public String component4() {
        return this.definition2;
    }

    public String component5() {
        return this.goDate;
    }

    public String component6() {
        return this.ficheNo;
    }

    public int component7() {
        return this.distorderRef;
    }

    public double component8() {
        return this.shipAmount;
    }

    public double component9() {
        return this.reAmount;
    }

    public Distribution copy(int r36, String str, String str2, String str3, String str4, String str5, int r42, double d2, double d3, int r47, int r48, int r49, int r50, int r51, String str6, String str7, int r54, int r55, int r56, int r57, int r58, int r59, int r60, int r61, int r62, int r63, int r64, int r65, int r66, String str8, String str9) {
        return new Distribution(r36, str, str2, str3, str4, str5, r42, d2, d3, r47, r48, r49, r50, r51, str6, str7, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, str8, str9);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Distribution distribution)) {
            return false;
        }
        return this.logicalRef == distribution.logicalRef && Intrinsics.areEqual(this.code, distribution.code) && Intrinsics.areEqual(this.definition, distribution.definition) && Intrinsics.areEqual(this.definition2, distribution.definition2) && Intrinsics.areEqual(this.goDate, distribution.goDate) && Intrinsics.areEqual(this.ficheNo, distribution.ficheNo) && this.distorderRef == distribution.distorderRef && Double.compare(this.shipAmount, distribution.shipAmount) == 0 && Double.compare(this.reAmount, distribution.reAmount) == 0 && this.itemRef == distribution.itemRef && this.ordFicheRef == distribution.ordFicheRef && this.ordLineRef == distribution.ordLineRef && this.clientRef == distribution.clientRef && this.salesMan == distribution.salesMan && Intrinsics.areEqual(this.date, distribution.date) && Intrinsics.areEqual(this.dueDate, distribution.dueDate) && this.uomRef == distribution.uomRef && this.branch == distribution.branch && this.factory == distribution.factory && this.sourceIndex == distribution.sourceIndex && this.campaignRef1 == distribution.campaignRef1 && this.campaignRef2 == distribution.campaignRef2 && this.campaignRef3 == distribution.campaignRef3 && this.campaignRef4 == distribution.campaignRef4 && this.campaignRef5 == distribution.campaignRef5 && this.pointCampRef == distribution.pointCampRef && this.dordStatus == distribution.dordStatus && this.variantRef == distribution.variantRef && this.shipinfoRef == distribution.shipinfoRef && Intrinsics.areEqual(this.shipinfoCode, distribution.shipinfoCode) && Intrinsics.areEqual(this.shipinfoName, distribution.shipinfoName);
    }

    public int hashCode() {
        int r0 = Integer.hashCode(this.logicalRef) * 31;
        String str = this.code;
        int r02 = (r0 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.definition;
        int r03 = (r02 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.definition2;
        int r04 = (r03 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.goDate;
        int r05 = (r04 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.ficheNo;
        int r06 = (((((((((((((((((r05 + (str5 == null ? 0 : str5.hashCode())) * 31) + Integer.hashCode(this.distorderRef)) * 31) + Double.hashCode(this.shipAmount)) * 31) + Double.hashCode(this.reAmount)) * 31) + Integer.hashCode(this.itemRef)) * 31) + Integer.hashCode(this.ordFicheRef)) * 31) + Integer.hashCode(this.ordLineRef)) * 31) + Integer.hashCode(this.clientRef)) * 31) + Integer.hashCode(this.salesMan)) * 31;
        String str6 = this.date;
        int r07 = (r06 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.dueDate;
        int r08 = (((((((((((((((((((((((((((r07 + (str7 == null ? 0 : str7.hashCode())) * 31) + Integer.hashCode(this.uomRef)) * 31) + Integer.hashCode(this.branch)) * 31) + Integer.hashCode(this.factory)) * 31) + Integer.hashCode(this.sourceIndex)) * 31) + Integer.hashCode(this.campaignRef1)) * 31) + Integer.hashCode(this.campaignRef2)) * 31) + Integer.hashCode(this.campaignRef3)) * 31) + Integer.hashCode(this.campaignRef4)) * 31) + Integer.hashCode(this.campaignRef5)) * 31) + Integer.hashCode(this.pointCampRef)) * 31) + Integer.hashCode(this.dordStatus)) * 31) + Integer.hashCode(this.variantRef)) * 31) + Integer.hashCode(this.shipinfoRef)) * 31;
        String str8 = this.shipinfoCode;
        int r09 = (r08 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.shipinfoName;
        return r09 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "Distribution(logicalRef=" + this.logicalRef + ", code=" + this.code + ", definition=" + this.definition + ", definition2=" + this.definition2 + ", goDate=" + this.goDate + ", ficheNo=" + this.ficheNo + ", distorderRef=" + this.distorderRef + ", shipAmount=" + this.shipAmount + ", reAmount=" + this.reAmount + ", itemRef=" + this.itemRef + ", ordFicheRef=" + this.ordFicheRef + ", ordLineRef=" + this.ordLineRef + ", clientRef=" + this.clientRef + ", salesMan=" + this.salesMan + ", date=" + this.date + ", dueDate=" + this.dueDate + ", uomRef=" + this.uomRef + ", branch=" + this.branch + ", factory=" + this.factory + ", sourceIndex=" + this.sourceIndex + ", campaignRef1=" + this.campaignRef1 + ", campaignRef2=" + this.campaignRef2 + ", campaignRef3=" + this.campaignRef3 + ", campaignRef4=" + this.campaignRef4 + ", campaignRef5=" + this.campaignRef5 + ", pointCampRef=" + this.pointCampRef + ", dordStatus=" + this.dordStatus + ", variantRef=" + this.variantRef + ", shipinfoRef=" + this.shipinfoRef + ", shipinfoCode=" + this.shipinfoCode + ", shipinfoName=" + this.shipinfoName + ')';
    }

    public Distribution(int r4, String str, String str2, String str3, String str4, String str5, int r10, double d2, double d3, int r15, int r16, int r17, int r18, int r19, String str6, String str7, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, String str8, String str9) {
        this.logicalRef = r4;
        this.code = str;
        this.definition = str2;
        this.definition2 = str3;
        this.goDate = str4;
        this.ficheNo = str5;
        this.distorderRef = r10;
        this.shipAmount = d2;
        this.reAmount = d3;
        this.itemRef = r15;
        this.ordFicheRef = r16;
        this.ordLineRef = r17;
        this.clientRef = r18;
        this.salesMan = r19;
        this.date = str6;
        this.dueDate = str7;
        this.uomRef = r22;
        this.branch = r23;
        this.factory = r24;
        this.sourceIndex = r25;
        this.campaignRef1 = r26;
        this.campaignRef2 = r27;
        this.campaignRef3 = r28;
        this.campaignRef4 = r29;
        this.campaignRef5 = r30;
        this.pointCampRef = r31;
        this.dordStatus = r32;
        this.variantRef = r33;
        this.shipinfoRef = r34;
        this.shipinfoCode = str8;
        this.shipinfoName = str9;
    }

    public /* synthetic */ Distribution(int r33, String str, String str2, String str3, String str4, String str5, int r39, double d2, double d3, int r44, int r45, int r46, int r47, int r48, String str6, String str7, int r51, int r52, int r53, int r54, int r55, int r56, int r57, int r58, int r59, int r60, int r61, int r62, int r63, String str8, String str9, int r66, DefaultConstructorMarker defaultConstructorMarker) {
        this((r66 & 1) != 0 ? 0 : r33, (r66 & 2) != 0 ? null : str, (r66 & 4) != 0 ? null : str2, (r66 & 8) != 0 ? null : str3, (r66 & 16) != 0 ? null : str4, (r66 & 32) != 0 ? null : str5, (r66 & 64) != 0 ? 0 : r39, (r66 & 128) != 0 ? 0.0d : d2, (r66 & 256) == 0 ? d3 : 0.0d, (r66 & 512) != 0 ? 0 : r44, (r66 & 1024) != 0 ? 0 : r45, (r66 & 2048) != 0 ? 0 : r46, (r66 & 4096) != 0 ? 0 : r47, (r66 & 8192) != 0 ? 0 : r48, (r66 & 16384) != 0 ? null : str6, (r66 & 32768) != 0 ? null : str7, (r66 & 65536) != 0 ? 0 : r51, (r66 & 131072) != 0 ? 0 : r52, (r66 & 262144) != 0 ? 0 : r53, (r66 & 524288) != 0 ? 0 : r54, (r66 & 1048576) != 0 ? 0 : r55, (r66 & 2097152) != 0 ? 0 : r56, (r66 & 4194304) != 0 ? 0 : r57, (r66 & 8388608) != 0 ? 0 : r58, (r66 & 16777216) != 0 ? 0 : r59, (r66 & 33554432) != 0 ? 0 : r60, (r66 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? 0 : r61, (r66 & 134217728) != 0 ? 0 : r62, (r66 & 268435456) != 0 ? 0 : r63, (r66 & 536870912) != 0 ? null : str8, (r66 & BasicMeasure.EXACTLY) != 0 ? null : str9);
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int r1) {
        this.logicalRef = r1;
    }

    public String getDefinition2() {
        return this.definition2;
    }

    public void setDefinition2(String str) {
        this.definition2 = str;
    }

    public double getShipAmount() {
        return this.shipAmount;
    }

    public void setShipAmount(double d2) {
        this.shipAmount = d2;
    }

    public double getReAmount() {
        return this.reAmount;
    }

    public void setReAmount(double d2) {
        this.reAmount = d2;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int r1) {
        this.itemRef = r1;
    }

    public int getOrdFicheRef() {
        return this.ordFicheRef;
    }

    public void setOrdFicheRef(int r1) {
        this.ordFicheRef = r1;
    }

    public int getOrdLineRef() {
        return this.ordLineRef;
    }

    public void setOrdLineRef(int r1) {
        this.ordLineRef = r1;
    }

    public int getSalesMan() {
        return this.salesMan;
    }

    public void setSalesMan(int r1) {
        this.salesMan = r1;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String str) {
        this.dueDate = str;
    }

    public int getUomRef() {
        return this.uomRef;
    }

    public void setUomRef(int r1) {
        this.uomRef = r1;
    }

    public int getBranch() {
        return this.branch;
    }

    public void setBranch(int r1) {
        this.branch = r1;
    }

    public int getFactory() {
        return this.factory;
    }

    public void setFactory(int r1) {
        this.factory = r1;
    }

    public int getSourceIndex() {
        return this.sourceIndex;
    }

    public void setSourceIndex(int r1) {
        this.sourceIndex = r1;
    }

    public int getCampaignRef1() {
        return this.campaignRef1;
    }

    public void setCampaignRef1(int r1) {
        this.campaignRef1 = r1;
    }

    public int getCampaignRef2() {
        return this.campaignRef2;
    }

    public void setCampaignRef2(int r1) {
        this.campaignRef2 = r1;
    }

    public int getCampaignRef3() {
        return this.campaignRef3;
    }

    public void setCampaignRef3(int r1) {
        this.campaignRef3 = r1;
    }

    public int getCampaignRef4() {
        return this.campaignRef4;
    }

    public void setCampaignRef4(int r1) {
        this.campaignRef4 = r1;
    }

    public int getCampaignRef5() {
        return this.campaignRef5;
    }

    public void setCampaignRef5(int r1) {
        this.campaignRef5 = r1;
    }

    public int getPointCampRef() {
        return this.pointCampRef;
    }

    public void setPointCampRef(int r1) {
        this.pointCampRef = r1;
    }

    public int getDordStatus() {
        return this.dordStatus;
    }

    public void setDordStatus(int r1) {
        this.dordStatus = r1;
    }

    public int getVariantRef() {
        return this.variantRef;
    }

    public void setVariantRef(int r1) {
        this.variantRef = r1;
    }

    public int getShipinfoRef() {
        return this.shipinfoRef;
    }

    public void setShipinfoRef(int r1) {
        this.shipinfoRef = r1;
    }

    public Distribution(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int r16 = 0;
        this(0, null, null, null, null, null, 0, 0.0d, 0.0d, 0, 0, r16, r16, r16, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, Integer.MAX_VALUE, null);
        this.logicalRef = parcel.readInt();
        this.code = parcel.readString();
        this.definition = parcel.readString();
        this.definition2 = parcel.readString();
        this.goDate = parcel.readString();
        this.ficheNo = parcel.readString();
        this.distorderRef = parcel.readInt();
        this.shipAmount = parcel.readDouble();
        this.reAmount = parcel.readDouble();
        this.itemRef = parcel.readInt();
        this.ordFicheRef = parcel.readInt();
        this.ordLineRef = parcel.readInt();
        this.clientRef = parcel.readInt();
        this.salesMan = parcel.readInt();
        this.date = parcel.readString();
        this.dueDate = parcel.readString();
        this.uomRef = parcel.readInt();
        this.branch = parcel.readInt();
        this.factory = parcel.readInt();
        this.sourceIndex = parcel.readInt();
        this.campaignRef1 = parcel.readInt();
        this.campaignRef2 = parcel.readInt();
        this.campaignRef3 = parcel.readInt();
        this.campaignRef4 = parcel.readInt();
        this.campaignRef5 = parcel.readInt();
        this.pointCampRef = parcel.readInt();
        this.dordStatus = parcel.readInt();
        this.variantRef = parcel.readInt();
        this.shipinfoRef = parcel.readInt();
        this.shipinfoCode = parcel.readString();
        this.shipinfoName = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int r4) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.logicalRef);
        dest.writeString(this.code);
        dest.writeString(this.definition);
        dest.writeString(this.definition2);
        dest.writeString(this.goDate);
        dest.writeString(this.ficheNo);
        dest.writeInt(this.distorderRef);
        dest.writeDouble(this.shipAmount);
        dest.writeDouble(this.reAmount);
        dest.writeInt(this.itemRef);
        dest.writeInt(this.ordFicheRef);
        dest.writeInt(this.ordLineRef);
        dest.writeInt(this.clientRef);
        dest.writeInt(this.salesMan);
        dest.writeString(this.date);
        dest.writeString(this.dueDate);
        dest.writeInt(this.uomRef);
        dest.writeInt(this.branch);
        dest.writeInt(this.factory);
        dest.writeInt(this.sourceIndex);
        dest.writeInt(this.campaignRef1);
        dest.writeInt(this.campaignRef2);
        dest.writeInt(this.campaignRef3);
        dest.writeInt(this.campaignRef4);
        dest.writeInt(this.campaignRef5);
        dest.writeInt(this.pointCampRef);
        dest.writeInt(this.dordStatus);
        dest.writeInt(this.variantRef);
        dest.writeInt(this.shipinfoRef);
        dest.writeString(this.shipinfoCode);
        dest.writeString(this.shipinfoName);
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
