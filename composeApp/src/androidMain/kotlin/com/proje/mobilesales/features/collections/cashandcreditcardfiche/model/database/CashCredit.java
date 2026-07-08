package com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class CashCredit {
    public String aggrCode;
    public String andFicheNo;
    private String bankAccCode;
    public int bankAccRef;
    public String bankCode;
    public String bankDescription;
    public int bankRef;

    private double boylam;
    public int branchnr;
    public List<CashCreditDetail> cashCreditDetails;
    public String clCode;
    public int clRef;
    public String cyphcode;
    private int dateInt;

    public String desc1;
    public String desc2;
    public String desc3;
    public String desc4;
    public int divisnr;
    private double enlem;
    private int fType;
    public String ficheNo;
    public int ficheref;
    private String gDate;
    private int installmentCount;
    private int invoiceRef;
    public int isTransfer;
     public int logicalRef;
    private int printCount;
    public String projectRef;
    public String specode;
    public double total;
    public String tradinggrp;
    public int visitInfoId;
    public String getBankAccCode() {
        return bankAccCode;
    }
    public void setBankAccCode(final String str) {
        bankAccCode = str;
    }
    public int getPrintCount() {
        return printCount;
    }
    public void setPrintCount(final int i2) {
        printCount = i2;
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

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void convertFiche(final CashCreditFiche cashCreditFiche) {
        Intrinsics.checkNotNullParameter(cashCreditFiche, "cashCreditFiche");
        logicalRef = cashCreditFiche.getLogicalRef();
        clRef = cashCreditFiche.getClRef();
        clCode = cashCreditFiche.getClCode();
        tradinggrp = cashCreditFiche.getTradinggrp().toString();
        fType = cashCreditFiche.getfType();
        specode = cashCreditFiche.getSpecode().toString();
        cyphcode = cashCreditFiche.getCyphcode().toString();
        bankRef = cashCreditFiche.getBank().getLogicalRef();
        bankCode = cashCreditFiche.getBank().getCode();
        bankAccRef = cashCreditFiche.getBankAcc().getLogicalRef();
        bankAccCode = cashCreditFiche.getBankAcc().getCode();
        desc1 = cashCreditFiche.getExplanation1().toString();
        desc2 = cashCreditFiche.getExplanation2().toString();
        desc3 = cashCreditFiche.getExplanation3().toString();
        desc4 = cashCreditFiche.getExplanation4().toString();
        isTransfer = cashCreditFiche.isTransfer();
        total = cashCreditFiche.getTotal();
        printCount = cashCreditFiche.getPrintCount();
        ficheref = cashCreditFiche.getFicheref();
        ficheNo = cashCreditFiche.getFicheNo();
        divisnr = cashCreditFiche.getDivision().getLogicalRef();
        branchnr = cashCreditFiche.getBranch().getLogicalRef();
        enlem = cashCreditFiche.getEnlem();
        boylam = cashCreditFiche.getBoylam();
        dateInt = cashCreditFiche.getDateInt();
        gDate = cashCreditFiche.getgDate();
        projectRef = StringUtils.convertIntToString(cashCreditFiche.getProjectCode().getLogicalRef());
        andFicheNo = cashCreditFiche.getAndFicheNo();
        aggrCode = cashCreditFiche.getAggrCode().getCode();
        installmentCount = StringUtils.convertStringToInt(cashCreditFiche.getInstallmentCount().getDefinition());
        invoiceRef = cashCreditFiche.getInvoiceRef();
        visitInfoId = cashCreditFiche.getVisitInfoId();
        final List<CashCreditDetail> list = cashCreditDetails;
        if (null == list) {
            cashCreditDetails = new ArrayList();
        } else {
            Intrinsics.checkNotNull(list);
            list.clear();
        }
        final ArrayList<CashCreditFicheDetail> details = cashCreditFiche.getDetails();
        Intrinsics.checkNotNull(details);
        final Iterator<CashCreditFicheDetail> it = details.iterator();
        while (it.hasNext()) {
            final CashCreditFicheDetail next = it.next();
            final CashCreditDetail cashCreditDetail = new CashCreditDetail();
            Intrinsics.checkNotNull(next);
            cashCreditDetail.convertFicheDetail(next);
            final List<CashCreditDetail> list2 = cashCreditDetails;
            Intrinsics.checkNotNull(list2);
            list2.add(cashCreditDetail);
        }
    }

    public CashCreditFiche convertFicheToCashCreditFiche() {
        final CashCreditFiche cashCreditFiche = new CashCreditFiche(0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, 0, 0, null, null, null, -1, null);
        cashCreditFiche.setLogicalRef(logicalRef);
        cashCreditFiche.setClRef(clRef);
        cashCreditFiche.setClCode(clCode);
        cashCreditFiche.setTradinggrp(new FicheRefProp(-1, -1, tradinggrp));
        cashCreditFiche.setfType(fType);
        cashCreditFiche.setSpecode(new FicheRefProp(-1, -1, specode));
        cashCreditFiche.setCyphcode(new FicheRefProp(-1, -1, cyphcode));
        cashCreditFiche.setBank(new FicheDiscountRefProp(bankRef, -1, "", bankCode));
        cashCreditFiche.setBankAcc(new FicheDiscountRefProp(bankAccRef, -1, "", bankAccCode));
        cashCreditFiche.setExplanation1(new FicheStringProp(desc1));
        cashCreditFiche.setExplanation2(new FicheStringProp(desc2));
        cashCreditFiche.setExplanation3(new FicheStringProp(desc3));
        cashCreditFiche.setExplanation4(new FicheStringProp(desc4));
        cashCreditFiche.setTransfer(isTransfer);
        cashCreditFiche.setTotal(total);
        cashCreditFiche.setPrintCount(printCount);
        cashCreditFiche.setFicheref(ficheref);
        cashCreditFiche.setFicheNo(ficheNo);
        cashCreditFiche.setDivision(new FicheRefProp(divisnr, -1, ""));
        cashCreditFiche.setBranch(new FicheRefProp(branchnr, -1, ""));
        cashCreditFiche.setEnlem(enlem);
        cashCreditFiche.setBoylam(boylam);
        cashCreditFiche.setDateInt(dateInt);
        cashCreditFiche.setgDate(gDate);
        cashCreditFiche.setProjectCode(new FicheRefProp(StringUtils.convertStringToInt(projectRef), -1, ""));
        cashCreditFiche.setAndFicheNo(andFicheNo);
        cashCreditFiche.setAggrCode(new FicheDiscountRefProp(-1, -1, "", aggrCode));
        cashCreditFiche.setInstallmentCount(new FicheStringProp(StringUtils.convertIntToString(installmentCount)));
        cashCreditFiche.setInvoiceRef(invoiceRef);
        cashCreditFiche.setVisitInfoId(visitInfoId);
        return cashCreditFiche;
    }
}
