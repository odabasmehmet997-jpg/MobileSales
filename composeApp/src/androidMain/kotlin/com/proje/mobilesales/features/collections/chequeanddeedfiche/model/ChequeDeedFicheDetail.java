package com.proje.mobilesales.features.collections.chequeanddeedfiche.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeDeedFicheDetail.kt */

public final class ChequeDeedFicheDetail implements Parcelable {
    private FicheStringProp accNo;
    private FicheStringProp bankBranchName;
    private FicheStringProp bankName;
    private String branch;
    private int chequeDeedId;
    private String cyphCode;
    private FicheStringProp debited;
    private FicheDateProp dueDate;
    private String ficheNo;
    private FicheStringProp inCharge;
    private int logicalRef;
    private FicheStringProp payWhere;
    private FicheStringProp pul;
    private FicheStringProp serialNo;
    private String specode;
    private FicheStringProp total;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ChequeDeedFicheDetail> CREATOR = new Parcelable.Creator<ChequeDeedFicheDetail>() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetailCompanionCREATOR1
        void ChequeDeedFicheDetailCompanionCREATOR1() {
        }

        
        public ChequeDeedFicheDetail createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new ChequeDeedFicheDetail(source);
        }

        
        public ChequeDeedFicheDetail[] newArray(final int i2) {
            return new ChequeDeedFicheDetail[i2];
        }
    };

    public ChequeDeedFicheDetail() {
        this(0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
    }

    public int component1() {
        return logicalRef;
    }

    public FicheStringProp component10() {
        return debited;
    }

    public FicheStringProp component11() {
        return accNo;
    }

    public FicheStringProp component12() {
        return inCharge;
    }

    public FicheStringProp component13() {
        return payWhere;
    }

    public FicheStringProp component14() {
        return pul;
    }

    public String component15() {
        return branch;
    }

    public String component16() {
        return ficheNo;
    }

    public int component2() {
        return chequeDeedId;
    }

    public FicheDateProp component3() {
        return dueDate;
    }

    public FicheStringProp component4() {
        return total;
    }

    public String component5() {
        return specode;
    }

    public String component6() {
        return cyphCode;
    }

    public FicheStringProp component7() {
        return bankName;
    }

    public FicheStringProp component8() {
        return bankBranchName;
    }

    public FicheStringProp component9() {
        return serialNo;
    }

    public ChequeDeedFicheDetail copy(final int i2, final int i3, final FicheDateProp dueDate, final FicheStringProp total, final String str, final String str2, final FicheStringProp bankName, final FicheStringProp bankBranchName, final FicheStringProp serialNo, final FicheStringProp debited, final FicheStringProp accNo, final FicheStringProp inCharge, final FicheStringProp payWhere, final FicheStringProp pul, final String str3, final String str4) {
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(bankName, "bankName");
        Intrinsics.checkNotNullParameter(bankBranchName, "bankBranchName");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Intrinsics.checkNotNullParameter(debited, "debited");
        Intrinsics.checkNotNullParameter(accNo, "accNo");
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(payWhere, "payWhere");
        Intrinsics.checkNotNullParameter(pul, "pul");
        return new ChequeDeedFicheDetail(i2, i3, dueDate, total, str, str2, bankName, bankBranchName, serialNo, debited, accNo, inCharge, payWhere, pul, str3, str4);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChequeDeedFicheDetail chequeDeedFicheDetail)) {
            return false;
        }
        return logicalRef == chequeDeedFicheDetail.logicalRef && chequeDeedId == chequeDeedFicheDetail.chequeDeedId && Intrinsics.areEqual(dueDate, chequeDeedFicheDetail.dueDate) && Intrinsics.areEqual(total, chequeDeedFicheDetail.total) && Intrinsics.areEqual(specode, chequeDeedFicheDetail.specode) && Intrinsics.areEqual(cyphCode, chequeDeedFicheDetail.cyphCode) && Intrinsics.areEqual(bankName, chequeDeedFicheDetail.bankName) && Intrinsics.areEqual(bankBranchName, chequeDeedFicheDetail.bankBranchName) && Intrinsics.areEqual(serialNo, chequeDeedFicheDetail.serialNo) && Intrinsics.areEqual(debited, chequeDeedFicheDetail.debited) && Intrinsics.areEqual(accNo, chequeDeedFicheDetail.accNo) && Intrinsics.areEqual(inCharge, chequeDeedFicheDetail.inCharge) && Intrinsics.areEqual(payWhere, chequeDeedFicheDetail.payWhere) && Intrinsics.areEqual(pul, chequeDeedFicheDetail.pul) && Intrinsics.areEqual(branch, chequeDeedFicheDetail.branch) && Intrinsics.areEqual(ficheNo, chequeDeedFicheDetail.ficheNo);
    }

    public int hashCode() {
        final int hashCode = ((((((Integer.hashCode(logicalRef) * 31) + Integer.hashCode(chequeDeedId)) * 31) + dueDate.hashCode()) * 31) + total.hashCode()) * 31;
        final String str = specode;
        final int hashCode2 = (hashCode + (null == str ? 0 : str.hashCode())) * 31;
        final String str2 = cyphCode;
        final int hashCode3 = (((((((((((((((((hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31) + bankName.hashCode()) * 31) + bankBranchName.hashCode()) * 31) + serialNo.hashCode()) * 31) + debited.hashCode()) * 31) + accNo.hashCode()) * 31) + inCharge.hashCode()) * 31) + payWhere.hashCode()) * 31) + pul.hashCode()) * 31;
        final String str3 = branch;
        final int hashCode4 = (hashCode3 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = ficheNo;
        return hashCode4 + (null != str4 ? str4.hashCode() : 0);
    }

    public String toString() {
        return "ChequeDeedFicheDetail(logicalRef=" + logicalRef + ", chequeDeedId=" + chequeDeedId + ", dueDate=" + dueDate + ", total=" + total + ", specode=" + specode + ", cyphCode=" + cyphCode + ", bankName=" + bankName + ", bankBranchName=" + bankBranchName + ", serialNo=" + serialNo + ", debited=" + debited + ", accNo=" + accNo + ", inCharge=" + inCharge + ", payWhere=" + payWhere + ", pul=" + pul + ", branch=" + branch + ", ficheNo=" + ficheNo + ')';
    }

    public ChequeDeedFicheDetail(final int i2, final int i3, final FicheDateProp dueDate, final FicheStringProp total, final String str, final String str2, final FicheStringProp bankName, final FicheStringProp bankBranchName, final FicheStringProp serialNo, final FicheStringProp debited, final FicheStringProp accNo, final FicheStringProp inCharge, final FicheStringProp payWhere, final FicheStringProp pul, final String str3, final String str4) {
        Intrinsics.checkNotNullParameter(dueDate, "dueDate");
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(bankName, "bankName");
        Intrinsics.checkNotNullParameter(bankBranchName, "bankBranchName");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Intrinsics.checkNotNullParameter(debited, "debited");
        Intrinsics.checkNotNullParameter(accNo, "accNo");
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(payWhere, "payWhere");
        Intrinsics.checkNotNullParameter(pul, "pul");
        logicalRef = i2;
        chequeDeedId = i3;
        this.dueDate = dueDate;
        this.total = total;
        specode = str;
        cyphCode = str2;
        this.bankName = bankName;
        this.bankBranchName = bankBranchName;
        this.serialNo = serialNo;
        this.debited = debited;
        this.accNo = accNo;
        this.inCharge = inCharge;
        this.payWhere = payWhere;
        this.pul = pul;
        branch = str3;
        ficheNo = str4;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public int getChequeDeedId() {
        return chequeDeedId;
    }

    public void setChequeDeedId(final int i2) {
        chequeDeedId = i2;
    }

    public ChequeDeedFicheDetail(final int i2, final int i3, final FicheDateProp ficheDateProp, final FicheStringProp ficheStringProp, final String str, final String str2, final FicheStringProp ficheStringProp2, final FicheStringProp ficheStringProp3, final FicheStringProp ficheStringProp4, final FicheStringProp ficheStringProp5, final FicheStringProp ficheStringProp6, final FicheStringProp ficheStringProp7, final FicheStringProp ficheStringProp8, final FicheStringProp ficheStringProp9, final String str3, final String str4, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i4 & 1) ? 0 : i2, 0 == (i4 & 2) ? i3 : 0, 0 != (i4 & 4) ? new FicheDateProp() : ficheDateProp, 0 != (i4 & 8) ? new FicheStringProp() : ficheStringProp, 0 != (i4 & 16) ? null : str, 0 != (i4 & 32) ? null : str2, 0 != (i4 & 64) ? new FicheStringProp() : ficheStringProp2, 0 != (i4 & 128) ? new FicheStringProp() : ficheStringProp3, 0 != (i4 & 256) ? new FicheStringProp() : ficheStringProp4, 0 != (i4 & 512) ? new FicheStringProp() : ficheStringProp5, 0 != (i4 & 1024) ? new FicheStringProp() : ficheStringProp6, 0 != (i4 & 2048) ? new FicheStringProp() : ficheStringProp7, 0 != (i4 & 4096) ? new FicheStringProp() : ficheStringProp8, 0 != (i4 & 8192) ? new FicheStringProp() : ficheStringProp9, 0 != (i4 & 16384) ? null : str3, 0 != (i4 & 32768) ? null : str4);
    }

    public FicheDateProp getDueDate() {
        return dueDate;
    }

    public void setDueDate(final FicheDateProp ficheDateProp) {
        Intrinsics.checkNotNullParameter(ficheDateProp, "<set-?>");
        dueDate = ficheDateProp;
    }

    public FicheStringProp getTotal() {
        return total;
    }

    public void setTotal(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        total = ficheStringProp;
    }

    public String getSpecode() {
        return specode;
    }

    public void setSpecode(final String str) {
        specode = str;
    }

    public String getCyphCode() {
        return cyphCode;
    }

    public void setCyphCode(final String str) {
        cyphCode = str;
    }

    public FicheStringProp getBankName() {
        return bankName;
    }

    public void setBankName(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        bankName = ficheStringProp;
    }

    public FicheStringProp getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        bankBranchName = ficheStringProp;
    }

    public FicheStringProp getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        serialNo = ficheStringProp;
    }

    public FicheStringProp getDebited() {
        return debited;
    }

    public void setDebited(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        debited = ficheStringProp;
    }

    public FicheStringProp getAccNo() {
        return accNo;
    }

    public void setAccNo(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        accNo = ficheStringProp;
    }

    public FicheStringProp getInCharge() {
        return inCharge;
    }

    public void setInCharge(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        inCharge = ficheStringProp;
    }

    public FicheStringProp getPayWhere() {
        return payWhere;
    }

    public void setPayWhere(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        payWhere = ficheStringProp;
    }

    public FicheStringProp getPul() {
        return pul;
    }

    public void setPul(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        pul = ficheStringProp;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String str) {
        branch = str;
    }

    public String getFicheNo() {
        return ficheNo;
    }

    public void setFicheNo(final String str) {
        ficheNo = str;
    }

    public ChequeDeedFicheDetail(final Parcel parcel) {
        this(0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        logicalRef = parcel.readInt();
        chequeDeedId = parcel.readInt();
        final FicheStringProp ficheStringProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        total = null == ficheStringProp ? new FicheStringProp() : ficheStringProp;
        final FicheStringProp ficheStringProp2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        bankName = null == ficheStringProp2 ? new FicheStringProp() : ficheStringProp2;
        specode = parcel.readString();
        branch = parcel.readString();
        final FicheStringProp ficheStringProp3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        bankBranchName = null == ficheStringProp3 ? new FicheStringProp() : ficheStringProp3;
        final FicheDateProp ficheDateProp = parcel.readParcelable(FicheDateProp.class.getClassLoader());
        dueDate = null == ficheDateProp ? new FicheDateProp() : ficheDateProp;
        cyphCode = parcel.readString();
        final FicheStringProp ficheStringProp4 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        serialNo = null == ficheStringProp4 ? new FicheStringProp() : ficheStringProp4;
        final FicheStringProp ficheStringProp5 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        debited = null == ficheStringProp5 ? new FicheStringProp() : ficheStringProp5;
        final FicheStringProp ficheStringProp6 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        accNo = null == ficheStringProp6 ? new FicheStringProp() : ficheStringProp6;
        final FicheStringProp ficheStringProp7 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        inCharge = null == ficheStringProp7 ? new FicheStringProp() : ficheStringProp7;
        final FicheStringProp ficheStringProp8 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        payWhere = null == ficheStringProp8 ? new FicheStringProp() : ficheStringProp8;
        final FicheStringProp ficheStringProp9 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        pul = null == ficheStringProp9 ? new FicheStringProp() : ficheStringProp9;
        branch = parcel.readString();
        ficheNo = parcel.readString();
    }

    public void init() {
        total = new FicheStringProp();
        bankName = new FicheStringProp();
        bankBranchName = new FicheStringProp();
        dueDate = new FicheDateProp(DateAndTimeUtils.nowDate());
        serialNo = new FicheStringProp();
        debited = new FicheStringProp();
        accNo = new FicheStringProp();
        inCharge = new FicheStringProp();
        payWhere = new FicheStringProp();
        pul = new FicheStringProp();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dests, final int i2) {
        Intrinsics.checkNotNullParameter(dests, "dests");
        dests.writeInt(logicalRef);
        dests.writeInt(chequeDeedId);
        dests.writeParcelable(total, i2);
        dests.writeParcelable(bankName, i2);
        dests.writeString(specode);
        dests.writeString(branch);
        dests.writeParcelable(bankBranchName, i2);
        dests.writeParcelable(dueDate, i2);
        dests.writeString(cyphCode);
        dests.writeParcelable(serialNo, i2);
        dests.writeParcelable(debited, i2);
        dests.writeParcelable(accNo, i2);
        dests.writeParcelable(inCharge, i2);
        dests.writeParcelable(payWhere, i2);
        dests.writeParcelable(pul, i2);
        dests.writeString(branch);
        dests.writeString(ficheNo);
    }

    /* compiled from: ChequeDeedFicheDetail.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
