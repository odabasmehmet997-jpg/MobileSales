package com.proje.mobilesales.features.collections.cashandcreditcardfiche.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashCreditFicheDetail.kt */

public final class CashCreditFicheDetail implements Parcelable {
    private FicheStringProp approvalNo;
    private int cashCreditId;
    private FicheStringProp creditCardNo;
    private String desc;
    private FicheStringProp docNo;
    private boolean isPayedOnline;
    private boolean isUse3d;
    private int logicalRef;
    private FicheDiscountRefProp payment;
    private String paymentOrderNr;
    private FicheRefProp phoneNumber;
    private String specode;
    private FicheStringProp total;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CashCreditFicheDetail> CREATOR = new Parcelable.Creator<CashCreditFicheDetail>() { // from class: com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetailCompanionCREATOR1
        CashCreditFicheDetailCompanionCREATOR1() {
        }

        
        public CashCreditFicheDetail createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CashCreditFicheDetail(source);
        }

        
        public CashCreditFicheDetail[] newArray(final int i2) {
            return new CashCreditFicheDetail[i2];
        }
    };

    public CashCreditFicheDetail() {
        this(0, 0, null, null, null, null, null, null, null, null, false, false, null, 8191, null);
    }

    public int component1() {
        return logicalRef;
    }

    public FicheRefProp component10() {
        return phoneNumber;
    }

    public boolean component11() {
        return isPayedOnline;
    }

    public boolean component12() {
        return isUse3d;
    }

    public String component13() {
        return paymentOrderNr;
    }

    public int component2() {
        return cashCreditId;
    }

    public FicheStringProp component3() {
        return total;
    }

    public FicheStringProp component4() {
        return docNo;
    }

    public FicheDiscountRefProp component5() {
        return payment;
    }

    public String component6() {
        return specode;
    }

    public String component7() {
        return desc;
    }

    public FicheStringProp component8() {
        return creditCardNo;
    }

    public FicheStringProp component9() {
        return approvalNo;
    }

    public CashCreditFicheDetail copy(final int i2, final int i3, final FicheStringProp total, final FicheStringProp docNo, final FicheDiscountRefProp payment, final String str, final String str2, final FicheStringProp creditCardNo, final FicheStringProp approvalNo, final FicheRefProp phoneNumber, final boolean z, final boolean z2, final String str3) {
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(docNo, "docNo");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(creditCardNo, "creditCardNo");
        Intrinsics.checkNotNullParameter(approvalNo, "approvalNo");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        return new CashCreditFicheDetail(i2, i3, total, docNo, payment, str, str2, creditCardNo, approvalNo, phoneNumber, z, z2, str3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CashCreditFicheDetail cashCreditFicheDetail)) {
            return false;
        }
        return logicalRef == cashCreditFicheDetail.logicalRef && cashCreditId == cashCreditFicheDetail.cashCreditId && Intrinsics.areEqual(total, cashCreditFicheDetail.total) && Intrinsics.areEqual(docNo, cashCreditFicheDetail.docNo) && Intrinsics.areEqual(payment, cashCreditFicheDetail.payment) && Intrinsics.areEqual(specode, cashCreditFicheDetail.specode) && Intrinsics.areEqual(desc, cashCreditFicheDetail.desc) && Intrinsics.areEqual(creditCardNo, cashCreditFicheDetail.creditCardNo) && Intrinsics.areEqual(approvalNo, cashCreditFicheDetail.approvalNo) && Intrinsics.areEqual(phoneNumber, cashCreditFicheDetail.phoneNumber) && isPayedOnline == cashCreditFicheDetail.isPayedOnline && isUse3d == cashCreditFicheDetail.isUse3d && Intrinsics.areEqual(paymentOrderNr, cashCreditFicheDetail.paymentOrderNr);
    }

    public int hashCode() {
        final int hashCode = ((((((((Integer.hashCode(logicalRef) * 31) + Integer.hashCode(cashCreditId)) * 31) + total.hashCode()) * 31) + docNo.hashCode()) * 31) + payment.hashCode()) * 31;
        final String str = specode;
        final int hashCode2 = (hashCode + (null == str ? 0 : str.hashCode())) * 31;
        final String str2 = desc;
        final int hashCode3 = (((((((((((hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31) + creditCardNo.hashCode()) * 31) + approvalNo.hashCode()) * 31) + phoneNumber.hashCode()) * 31) + Boolean.hashCode(isPayedOnline)) * 31) + Boolean.hashCode(isUse3d)) * 31;
        final String str3 = paymentOrderNr;
        return hashCode3 + (null != str3 ? str3.hashCode() : 0);
    }

    public String toString() {
        return "CashCreditFicheDetail(logicalRef=" + logicalRef + ", cashCreditId=" + cashCreditId + ", total=" + total + ", docNo=" + docNo + ", payment=" + payment + ", specode=" + specode + ", desc=" + desc + ", creditCardNo=" + creditCardNo + ", approvalNo=" + approvalNo + ", phoneNumber=" + phoneNumber + ", isPayedOnline=" + isPayedOnline + ", isUse3d=" + isUse3d + ", paymentOrderNr=" + paymentOrderNr + ')';
    }

    public CashCreditFicheDetail(final int i2, final int i3, final FicheStringProp total, final FicheStringProp docNo, final FicheDiscountRefProp payment, final String str, final String str2, final FicheStringProp creditCardNo, final FicheStringProp approvalNo, final FicheRefProp phoneNumber, final boolean z, final boolean z2, final String str3) {
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(docNo, "docNo");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(creditCardNo, "creditCardNo");
        Intrinsics.checkNotNullParameter(approvalNo, "approvalNo");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        logicalRef = i2;
        cashCreditId = i3;
        this.total = total;
        this.docNo = docNo;
        this.payment = payment;
        specode = str;
        desc = str2;
        this.creditCardNo = creditCardNo;
        this.approvalNo = approvalNo;
        this.phoneNumber = phoneNumber;
        isPayedOnline = z;
        isUse3d = z2;
        paymentOrderNr = str3;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public int getCashCreditId() {
        return cashCreditId;
    }

    public void setCashCreditId(final int i2) {
        cashCreditId = i2;
    }

    public CashCreditFicheDetail(final int i2, final int i3, final FicheStringProp ficheStringProp, final FicheStringProp ficheStringProp2, final FicheDiscountRefProp ficheDiscountRefProp, final String str, final String str2, final FicheStringProp ficheStringProp3, final FicheStringProp ficheStringProp4, final FicheRefProp ficheRefProp, final boolean z, final boolean z2, final String str3, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i4 & 1) ? 0 : i2, 0 != (i4 & 2) ? 0 : i3, 0 != (i4 & 4) ? new FicheStringProp() : ficheStringProp, 0 != (i4 & 8) ? new FicheStringProp() : ficheStringProp2, 0 != (i4 & 16) ? new FicheDiscountRefProp() : ficheDiscountRefProp, 0 != (i4 & 32) ? null : str, 0 != (i4 & 64) ? null : str2, 0 != (i4 & 128) ? new FicheStringProp() : ficheStringProp3, 0 != (i4 & 256) ? new FicheStringProp() : ficheStringProp4, 0 != (i4 & 512) ? new FicheRefProp() : ficheRefProp, 0 == (i4 & 1024) && z, 0 == (i4 & 2048) && z2, 0 == (i4 & 4096) ? str3 : null);
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

    public FicheDiscountRefProp getPayment() {
        return payment;
    }

    public void setPayment(final FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        payment = ficheDiscountRefProp;
    }

    public String getSpecode() {
        return specode;
    }

    public void setSpecode(final String str) {
        specode = str;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(final String str) {
        desc = str;
    }

    public FicheStringProp getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        creditCardNo = ficheStringProp;
    }

    public FicheStringProp getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(final FicheStringProp ficheStringProp) {
        Intrinsics.checkNotNullParameter(ficheStringProp, "<set-?>");
        approvalNo = ficheStringProp;
    }

    public FicheRefProp getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final FicheRefProp ficheRefProp) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "<set-?>");
        phoneNumber = ficheRefProp;
    }

    public boolean isPayedOnline() {
        return isPayedOnline;
    }

    public void setPayedOnline(final boolean z) {
        isPayedOnline = z;
    }

    public boolean isUse3d() {
        return isUse3d;
    }

    public void setUse3d(final boolean z) {
        isUse3d = z;
    }

    public String getPaymentOrderNr() {
        return paymentOrderNr;
    }

    public void setPaymentOrderNr(final String str) {
        paymentOrderNr = str;
    }

    public CashCreditFicheDetail(final Parcel parcel) {
        this(0, 0, null, null, null, null, null, null, null, null, false, false, null, 8191, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        logicalRef = parcel.readInt();
        cashCreditId = parcel.readInt();
        final FicheStringProp ficheStringProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        total = null == ficheStringProp ? new FicheStringProp() : ficheStringProp;
        final FicheStringProp ficheStringProp2 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        docNo = null == ficheStringProp2 ? new FicheStringProp() : ficheStringProp2;
        specode = parcel.readString();
        desc = parcel.readString();
        final FicheStringProp ficheStringProp3 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        creditCardNo = null == ficheStringProp3 ? new FicheStringProp() : ficheStringProp3;
        final FicheDiscountRefProp ficheDiscountRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        payment = null == ficheDiscountRefProp ? new FicheDiscountRefProp() : ficheDiscountRefProp;
        final FicheRefProp ficheRefProp = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        phoneNumber = null == ficheRefProp ? new FicheRefProp() : ficheRefProp;
        isPayedOnline = 1 == parcel.readInt();
        isUse3d = 1 == parcel.readInt();
        paymentOrderNr = parcel.readString();
        final FicheStringProp ficheStringProp4 = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        approvalNo = null == ficheStringProp4 ? new FicheStringProp() : ficheStringProp4;
    }

    public void init() {
        total = new FicheStringProp();
        docNo = new FicheStringProp();
        creditCardNo = new FicheStringProp();
        payment = new FicheDiscountRefProp(-1, -1, "", "");
        phoneNumber = new FicheRefProp(-1, -1, "");
        isPayedOnline = false;
        isUse3d = false;
        paymentOrderNr = "";
        approvalNo = new FicheStringProp();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dests, final int i2) {
        Intrinsics.checkNotNullParameter(dests, "dests");
        dests.writeInt(logicalRef);
        dests.writeInt(cashCreditId);
        dests.writeParcelable(total, i2);
        dests.writeParcelable(docNo, i2);
        dests.writeString(specode);
        dests.writeString(desc);
        dests.writeParcelable(creditCardNo, i2);
        dests.writeParcelable(payment, i2);
        dests.writeParcelable(phoneNumber, i2);
        dests.writeInt(isPayedOnline ? 1 : 0);
        dests.writeInt(isUse3d ? 1 : 0);
        dests.writeString(paymentOrderNr);
        dests.writeParcelable(approvalNo, i2);
    }

    /* compiled from: CashCreditFicheDetail.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
