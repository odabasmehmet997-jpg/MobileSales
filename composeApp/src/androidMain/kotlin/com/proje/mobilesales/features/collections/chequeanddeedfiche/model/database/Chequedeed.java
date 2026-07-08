package com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: Chequedeed.kt */
@Tables(name = "CHEQUEDEED")

public final class Chequedeed {

    @Column(name = "ANDFICHENO")
    public String andFicheNo;

    @Column(name = "BOYLAM")
    private String boylam;

    @Column(name = "BRANCHNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int branchnr;
    public List<ChequedeedDetail> chequeDeedFicheDetails;

    @Column(isAllSame = false, name = "CLCODE", shared = @ColumnProperty(useProperty = false))
    public String clCode;

    @Column(isAllSame = false, name = "CLREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int clRef;

    @Column(name = "CYPHCODE")
    public String cyphcode;

    @Column(name = "CHEQUEDEEDDATE")
    public String date;

    @Column(name = "DATEINT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int dateInt;

    @Column(name = "DESC1")
    public String desc1;

    @Column(name = "DESC2")
    public String desc2;

    @Column(name = "DESC3")
    public String desc3;

    @Column(name = "DESC4")
    public String desc4;

    @Column(name = "DIVISNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int divisnr;

    @Column(name = "DOCNO", shared = @ColumnProperty(alterVersion = 125, type = Column.ColumnValueTypes.VARCHAR))
    public String docNo;

    @Column(name = "ENLEM")
    private String enlem;

    @Column(name = "FTYPE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int fType;

    @Column(name = "FICHENO")
    public String ficheNo;

    @Column(name = "FICHEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int ficheref;

    @Column(name = "GDATE")
    private String gDate;

    @Column(name = "INVOICEREF", shared = @ColumnProperty(alterVersion = 146, type = Column.ColumnValueTypes.INTEGER))
    private int invoiceRef;

    @Column(name = "ISTRANSFER", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int isTransfer;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int logicalRef;

    @Column(name = "NUMBER", shared = @ColumnProperty(alterVersion = 124, type = Column.ColumnValueTypes.VARCHAR))
    public String number;

    @Column(name = "PRINTCOUNT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int printCount;

    @Column(name = "PROJECTREF")
    public String projectRef;

    @Column(name = "SPECODE")
    public String specode;

    @Column(name = "TOTAL", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double total;

    @Column(name = "TRADINGGRP")
    public String tradinggrp;

    @Column(name = "VISITINFOID", shared = @ColumnProperty(alterVersion = 219, type = Column.ColumnValueTypes.INTEGER))
    public int visitInfoId;

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(final int i2) {
        printCount = i2;
    }

    public String getEnlem() {
        return enlem;
    }

    public void setEnlem(final String str) {
        enlem = str;
    }

    public String getBoylam() {
        return boylam;
    }

    public void setBoylam(final String str) {
        boylam = str;
    }

    public int getDateInt() {
        return dateInt;
    }

    public void setDateInt(final int i2) {
        dateInt = i2;
    }

    public int getfType() {
        return fType;
    }

    public String getgDate() {
        return gDate;
    }

    public void convertFiche(final ChequeDeedFiche chequeDeedFiche) {
        Intrinsics.checkNotNullParameter(chequeDeedFiche, "chequeDeedFiche");
        logicalRef = chequeDeedFiche.getLogicalRef();
        clRef = chequeDeedFiche.getClRef();
        clCode = chequeDeedFiche.getClCode();
        date = chequeDeedFiche.getChequeDeedDate().toString();
        tradinggrp = chequeDeedFiche.getTradinggrp().toString();
        fType = chequeDeedFiche.getfType();
        specode = chequeDeedFiche.getSpecode().toString();
        cyphcode = chequeDeedFiche.getCyphcode().toString();
        desc1 = chequeDeedFiche.getExplanation1().toString();
        desc2 = chequeDeedFiche.getExplanation2().toString();
        desc3 = chequeDeedFiche.getExplanation3().toString();
        desc4 = chequeDeedFiche.getExplanation4().toString();
        isTransfer = chequeDeedFiche.isTransfer();
        total = chequeDeedFiche.getTotal();
        printCount = chequeDeedFiche.getPrintCount();
        ficheref = chequeDeedFiche.getFicheref();
        ficheNo = chequeDeedFiche.getFicheNo();
        divisnr = chequeDeedFiche.getDivision().getLogicalRef();
        branchnr = chequeDeedFiche.getBranch().getLogicalRef();
        enlem = StringUtils.convertDoubleToString(Double.valueOf(chequeDeedFiche.getEnlem()));
        boylam = StringUtils.convertDoubleToString(Double.valueOf(chequeDeedFiche.getBoylam()));
        dateInt = chequeDeedFiche.getDateInt();
        gDate = chequeDeedFiche.getgDate();
        projectRef = StringUtils.convertIntToString(chequeDeedFiche.getProjectCode().getLogicalRef());
        andFicheNo = chequeDeedFiche.getAndFicheNo();
        number = chequeDeedFiche.getNumber();
        docNo = chequeDeedFiche.getDocNo().toString();
        invoiceRef = chequeDeedFiche.getInvoiceRef();
        visitInfoId = chequeDeedFiche.getVisitInfoId();
        final List<ChequedeedDetail> list = chequeDeedFicheDetails;
        if (null == list) {
            chequeDeedFicheDetails = new ArrayList();
        } else {
            Intrinsics.checkNotNull(list);
            list.clear();
        }
        final ArrayList<ChequeDeedFicheDetail> details = chequeDeedFiche.getDetails();
        Intrinsics.checkNotNull(details);
        final Iterator<ChequeDeedFicheDetail> it = details.iterator();
        while (it.hasNext()) {
            final ChequeDeedFicheDetail next = it.next();
            final ChequedeedDetail chequedeedDetail = new ChequedeedDetail();
            Intrinsics.checkNotNull(next);
            chequedeedDetail.convertFicheDetail(next);
            final List<ChequedeedDetail> list2 = chequeDeedFicheDetails;
            Intrinsics.checkNotNull(list2);
            list2.add(chequedeedDetail);
        }
    }

    public ChequeDeedFiche convertFicheToCashCreditFiche() {
        final ChequeDeedFiche chequeDeedFiche = new ChequeDeedFiche(0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, null, null, 0, 0, null, Integer.MAX_VALUE, null);
        chequeDeedFiche.setLogicalRef(logicalRef);
        chequeDeedFiche.setClRef(clRef);
        chequeDeedFiche.setClCode(clCode);
        chequeDeedFiche.setChequeDeedDate(new FicheDateProp(date));
        chequeDeedFiche.setTradinggrp(new FicheRefProp(-1, -1, tradinggrp));
        chequeDeedFiche.setfType(fType);
        chequeDeedFiche.setSpecode(new FicheRefProp(-1, -1, specode));
        chequeDeedFiche.setCyphcode(new FicheRefProp(-1, -1, cyphcode));
        chequeDeedFiche.setExplanation1(new FicheStringProp(desc1));
        chequeDeedFiche.setExplanation2(new FicheStringProp(desc2));
        chequeDeedFiche.setExplanation3(new FicheStringProp(desc3));
        chequeDeedFiche.setExplanation4(new FicheStringProp(desc4));
        chequeDeedFiche.setTransfer(isTransfer);
        chequeDeedFiche.setTotal(total);
        chequeDeedFiche.setPrintCount(printCount);
        chequeDeedFiche.setFicheref(ficheref);
        chequeDeedFiche.setFicheNo(ficheNo);
        chequeDeedFiche.setDivision(new FicheRefProp(divisnr, -1, ""));
        chequeDeedFiche.setBranch(new FicheRefProp(branchnr, -1, ""));
        chequeDeedFiche.setEnlem(StringUtils.convertStringToDouble(enlem));
        chequeDeedFiche.setBoylam(StringUtils.convertStringToDouble(boylam));
        chequeDeedFiche.setDateInt(dateInt);
        chequeDeedFiche.setgDate(gDate);
        chequeDeedFiche.setProjectCode(new FicheRefProp(StringUtils.convertStringToInt(projectRef), -1, ""));
        chequeDeedFiche.setAndFicheNo(andFicheNo);
        chequeDeedFiche.setNumber(number);
        chequeDeedFiche.setDocNo(new FicheStringProp(docNo));
        chequeDeedFiche.setChequeDeedDate(new FicheDateProp(date));
        chequeDeedFiche.setInvoiceRef(invoiceRef);
        chequeDeedFiche.setVisitInfoId(visitInfoId);
        return chequeDeedFiche;
    }
}
