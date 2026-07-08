package com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequedeedDetail.kt */
@Tables(name = "CHEQUEDEEDDETAIL")

public final class ChequedeedDetail {

    @Column(name = "ACCNO")
    public String accNo;

    @Column(name = "BANKBRANCHNAME")
    public String bankBranchName;

    @Column(name = "BANKNAME")
    public String bankName;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "CHEQUEDEEDID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int chequedeedId;

    @Column(name = "CYPHCODE")
    public String cyphcode;

    @Column(name = "DUEDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String date;

    @Column(name = "DEBITED")
    public String debited;

    @Column(name = "FICHENO", shared = @ColumnProperty(alterVersion = 187))
    public String ficheNo;

    @Column(name = "INCHARGE")
    public String inCharge;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int logicalRef;

    @Column(name = "PAYWHERE")
    public String payWhere;

    @Column(name = "PUL", shared = @ColumnProperty(type = Column.ColumnValueTypes.DOUBLE))
    public float pul;

    @Column(name = "SERIALNO")
    public String serialNo;

    @Column(name = "SPECODE")
    public String specode;

    @Column(name = "TOTAL", shared = @ColumnProperty(type = Column.ColumnValueTypes.DOUBLE))
    public double total;

    public String getBranch() {
        return branch;
    }

    public void setBranch(final String str) {
        branch = str;
    }

    public void convertFicheDetail(final ChequeDeedFicheDetail chequeDeedFicheDetail) {
        Intrinsics.checkNotNullParameter(chequeDeedFicheDetail, "chequeDeedFicheDetail");
        logicalRef = chequeDeedFicheDetail.getLogicalRef();
        chequedeedId = chequeDeedFicheDetail.getChequeDeedId();
        date = chequeDeedFicheDetail.getDueDate().toString();
        total = chequeDeedFicheDetail.getTotal().getDefinitionDouble();
        specode = chequeDeedFicheDetail.getSpecode();
        cyphcode = chequeDeedFicheDetail.getCyphCode();
        bankName = chequeDeedFicheDetail.getBankName().toString();
        bankBranchName = chequeDeedFicheDetail.getBankBranchName().toString();
        serialNo = chequeDeedFicheDetail.getSerialNo().toString();
        debited = chequeDeedFicheDetail.getDebited().toString();
        accNo = chequeDeedFicheDetail.getAccNo().toString();
        inCharge = chequeDeedFicheDetail.getInCharge().toString();
        payWhere = chequeDeedFicheDetail.getPayWhere().toString();
        branch = chequeDeedFicheDetail.getBranch();
        pul = chequeDeedFicheDetail.getPul().getDefinitionFloat();
        ficheNo = chequeDeedFicheDetail.getFicheNo();
    }

    public ChequeDeedFicheDetail convertFicheDetailToCashCreditFicheDetail() {
        final byte b2 = 0 == true ? 1 : 0;
        final ChequeDeedFicheDetail chequeDeedFicheDetail = new ChequeDeedFicheDetail(0, 0, null, null, null, null, null, null, null, null, null, null, null, null, b2, null, 65535, null);
        chequeDeedFicheDetail.setLogicalRef(logicalRef);
        chequeDeedFicheDetail.setChequeDeedId(chequedeedId);
        chequeDeedFicheDetail.setTotal(new FicheStringProp(String.valueOf(total)));
        chequeDeedFicheDetail.setSpecode(specode);
        chequeDeedFicheDetail.setCyphCode(cyphcode);
        chequeDeedFicheDetail.setBankName(new FicheStringProp(bankName));
        chequeDeedFicheDetail.setBankBranchName(new FicheStringProp(bankBranchName));
        chequeDeedFicheDetail.setSerialNo(new FicheStringProp(serialNo));
        chequeDeedFicheDetail.setDebited(new FicheStringProp(debited));
        chequeDeedFicheDetail.setAccNo(new FicheStringProp(accNo));
        chequeDeedFicheDetail.setInCharge(new FicheStringProp(inCharge));
        chequeDeedFicheDetail.setPayWhere(new FicheStringProp(payWhere));
        chequeDeedFicheDetail.setBranch(branch);
        chequeDeedFicheDetail.setDueDate(new FicheDateProp(date));
        chequeDeedFicheDetail.setPul(new FicheStringProp(String.valueOf(pul)));
        chequeDeedFicheDetail.setFicheNo(ficheNo);
        return chequeDeedFicheDetail;
    }
}
