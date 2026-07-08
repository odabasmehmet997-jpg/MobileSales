package com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.Intrinsics;

public final class CashCreditDetail {
    public String approvalNo;
    public int cashCreditId;
    public String crediCardNo;
    public String desc;
    public String docNo;
     public int logicalRef;
     private int payedOnline;
    private String paymentCode;
     private String paymentOrderNr;
     public int paymentRef;

    @Column(name = "PHONENUMBER", netsisName = "PHONENUMBER", shared = @ColumnProperty(alterVersion = 179, type = Column.ColumnValueTypes.NVARCHAR))
    private String phoneNumber;

    @Column(name = "SPECODE")
    public String specode;

    @Column(name = "TOTAL", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double total;

    @Column(name = "USE3D", netsisName = "USE3D", shared = @ColumnProperty(alterVersion = 179, type = Column.ColumnValueTypes.INTEGER))
    private int use3D;

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(final String str) {
        paymentCode = str;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String str) {
        phoneNumber = str;
    }

    public int getPayedOnline() {
        return payedOnline;
    }

    public void setPayedOnline(final int i2) {
        payedOnline = i2;
    }

    public int getUse3D() {
        return use3D;
    }

    public void setUse3D(final int i2) {
        use3D = i2;
    }

    public String getPaymentOrderNr() {
        return paymentOrderNr;
    }

    public void setPaymentOrderNr(final String str) {
        paymentOrderNr = str;
    }

    public void convertFicheDetail(final CashCreditFicheDetail cashCreditFicheDetail) {
        Intrinsics.checkNotNullParameter(cashCreditFicheDetail, "cashCreditFicheDetail");
        logicalRef = cashCreditFicheDetail.getLogicalRef();
        cashCreditId = cashCreditFicheDetail.getCashCreditId();
        total = cashCreditFicheDetail.getTotal().getDefinitionDouble();
        docNo = cashCreditFicheDetail.getDocNo().toString();
        paymentRef = cashCreditFicheDetail.getPayment().getLogicalRef();
        paymentCode = cashCreditFicheDetail.getPayment().getCode();
        specode = cashCreditFicheDetail.getSpecode();
        desc = cashCreditFicheDetail.getDesc();
        crediCardNo = cashCreditFicheDetail.getCreditCardNo().toString();
        phoneNumber = cashCreditFicheDetail.getPhoneNumber().toString();
        payedOnline = cashCreditFicheDetail.isPayedOnline() ? 1 : 0;
        use3D = cashCreditFicheDetail.isUse3d() ? 1 : 0;
        paymentOrderNr = cashCreditFicheDetail.getPaymentOrderNr();
        approvalNo = cashCreditFicheDetail.getApprovalNo().toString();
    }

    public CashCreditFicheDetail convertFicheDetailToCashCreditFicheDetail() {
        final CashCreditFicheDetail cashCreditFicheDetail = new CashCreditFicheDetail(0, 0, null, null, null, null, null, null, null, null, false, false, null, 8191, null);
        cashCreditFicheDetail.setLogicalRef(logicalRef);
        cashCreditFicheDetail.setTotal(new FicheStringProp(String.valueOf(total)));
        cashCreditFicheDetail.setCashCreditId(cashCreditId);
        cashCreditFicheDetail.setDocNo(new FicheStringProp(docNo));
        cashCreditFicheDetail.setSpecode(specode);
        cashCreditFicheDetail.setDesc(desc);
        cashCreditFicheDetail.setCreditCardNo(new FicheStringProp(crediCardNo));
        cashCreditFicheDetail.setPayment(new FicheDiscountRefProp(paymentRef, -1, "", paymentCode));
        cashCreditFicheDetail.setPhoneNumber(new FicheRefProp(-1, -1, phoneNumber));
        cashCreditFicheDetail.setPayedOnline(1 == this.payedOnline);
        cashCreditFicheDetail.setUse3d(1 == this.use3D);
        cashCreditFicheDetail.setPaymentOrderNr(paymentOrderNr);
        cashCreditFicheDetail.setApprovalNo(new FicheStringProp(approvalNo));
        return cashCreditFicheDetail;
    }
}
