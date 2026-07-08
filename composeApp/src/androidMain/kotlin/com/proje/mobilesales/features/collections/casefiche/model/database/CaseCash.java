package com.proje.mobilesales.features.collections.casefiche.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.emailreplacer.EmailReplacer;
import com.proje.mobilesales.core.enums.EmailTemplateType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.Intrinsics;

public class CaseCash extends EmailReplacer {

    @Column(name = "ANDFICHENO")
    public String andFicheNo;

    @Column(name = "BOYLAM")
    private String boylam;

    @Column(name = "BRANCHNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int branchnr;

    @Column(isAllSame = false, name = "CARDREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int cardRef;

    @Column(isAllSame = false, name = "CASECODE", shared = @ColumnProperty(useProperty = false))
    public String caseCOde;

    @Column(isAllSame = false, name = "CLCODE", shared = @ColumnProperty(useProperty = false))
    public String clCode;

    @Column(name = "CLCURR", shared = @ColumnProperty(alterVersion = 180, type = Column.ColumnValueTypes.INTEGER))
    public int clCurr;

    @Column(name = "CLRATE", shared = @ColumnProperty(alterVersion = 180, type = Column.ColumnValueTypes.DOUBLE))
    public double clRate;

    @Column(isAllSame = false, name = "CLREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int clRef;

    @Column(name = "CURCODE", shared = @ColumnProperty(alterVersion = 139, type = Column.ColumnValueTypes.INTEGER))
    public int curCode;

    @Column(name = "CYPHCODE")
    public String cyphcode;

    @Column(name = "DATEINT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int dateInt;

    @Column(name = "DESC")
    public String desc;

    @Column(name = "DIVISNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int divisnr;

    @Column(name = "DOCNO")
    public String docNo;

    @Column(name = "ENLEM")
    private String enlem;

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

    @Column(name = "PRINTCOUNT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int printCount;

    @Column(name = "PROJECTREF")
    public String projectRef;

    @Column(isAllSame = EmbeddingCompat.DEBUG, name = "SALESMAN_CODE", shared = @ColumnProperty(alterVersion = 135, type = Column.ColumnValueTypes.VARCHAR))
    public String salesManCode;

    @Column(isAllSame = EmbeddingCompat.DEBUG, name = "SALESMANREF", shared = @ColumnProperty(alterVersion = 136, defaultValue = "0", type = Column.ColumnValueTypes.INTEGER))
    public int salesManRef;

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

    public String getgDate() {
        return gDate;
    }

    @Override // com.proje.mobilesales.core.emailreplacer.EmailReplacer
    public EmailObject replaceHtml(final EmailTemplateType templateType, final CustomerBeforeBalance customerBeforeBalance) {
        Intrinsics.checkNotNullParameter(templateType, "templateType");
        final EmailObject replaceCaseCashHTML = this.replaceCaseCashHTML(this, templateType, customerBeforeBalance);
        Intrinsics.checkNotNullExpressionValue(replaceCaseCashHTML, "replaceCaseCashHTML(...)");
        return replaceCaseCashHTML;
    }

    public void convertFiche(final CaseFiche caseFiche) {
        Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
        logicalRef = caseFiche.getLogicalRef();
        clRef = caseFiche.getClRef();
        clCode = caseFiche.getClCode();
        tradinggrp = caseFiche.getTradinggrp().toString();
        specode = caseFiche.getSpecode().toString();
        cyphcode = caseFiche.getCyphcode().toString();
        desc = caseFiche.getExplanation().toString();
        docNo = caseFiche.getDocNo().toString();
        isTransfer = caseFiche.isTransfer();
        total = caseFiche.getTotal().getDefinitionDouble();
        printCount = caseFiche.getPrintCount();
        ficheref = caseFiche.getFicheref();
        ficheNo = caseFiche.getFicheNo();
        divisnr = caseFiche.getDivision().getLogicalRef();
        branchnr = caseFiche.getBranch().getLogicalRef();
        enlem = StringUtils.convertDoubleToString(Double.valueOf(caseFiche.getEnlem()));
        boylam = StringUtils.convertDoubleToString(Double.valueOf(caseFiche.getBoylam()));
        cardRef = caseFiche.getCaseCode().getLogicalRef();
        caseCOde = caseFiche.getCaseCode().getCode();
        dateInt = caseFiche.getDateInt();
        gDate = caseFiche.getGDate();
        projectRef = StringUtils.convertIntToString(caseFiche.getProjectCode().getLogicalRef());
        andFicheNo = caseFiche.getAndFicheNo();
        caseCOde = caseFiche.getCaseCode().getCode();
        salesManRef = caseFiche.getSalesMan().getLogicalRef();
        salesManCode = caseFiche.getSalesMan().getCode();
        curCode = caseFiche.getCurrType().getLogicalRef();
        invoiceRef = caseFiche.getInvoiceRef();
        clRate = caseFiche.getClRate();
        clCurr = caseFiche.getClCurr().getLogicalRef();
        visitInfoId = caseFiche.getVisitInfoId();
    }

    public CaseFiche convertFicheToCaseFiche() {
        final CaseFiche caseFiche = new CaseFiche(0, 0, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0.0d, 0.0d, 0, null, 0, null, null, null, null, 0, 0.0d, null, 0, 536870911, null);
        caseFiche.setLogicalRef(logicalRef);
        caseFiche.setClRef(clRef);
        caseFiche.setClCode(clCode);
        caseFiche.setTradinggrp(new FicheRefProp(-1, -1, tradinggrp));
        caseFiche.setSpecode(new FicheRefProp(-1, -1, specode));
        caseFiche.setCyphcode(new FicheRefProp(-1, -1, cyphcode));
        caseFiche.setExplanation(new FicheStringProp(desc));
        caseFiche.setDocNo(new FicheStringProp(docNo));
        caseFiche.setTransfer(isTransfer);
        caseFiche.setTotal(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(total))));
        caseFiche.setPrintCount(printCount);
        caseFiche.setFicheref(ficheref);
        caseFiche.setFicheNo(ficheNo);
        caseFiche.setDivision(new FicheRefProp(divisnr, -1, ""));
        caseFiche.setBranch(new FicheRefProp(branchnr, -1, ""));
        caseFiche.setEnlem(StringUtils.convertStringToDouble(enlem));
        caseFiche.setBoylam(StringUtils.convertStringToDouble(boylam));
        caseFiche.setCaseCode(new FicheDiscountRefProp(cardRef, -1, "", caseCOde));
        caseFiche.setDateInt(dateInt);
        caseFiche.setGDate(gDate);
        caseFiche.setProjectCode(new FicheRefProp(StringUtils.convertStringToInt(projectRef), -1, ""));
        caseFiche.setAndFicheNo(andFicheNo);
        caseFiche.setSalesMan(new FicheDiscountRefProp(salesManRef, -1, "", salesManCode));
        caseFiche.setCurrType(new FicheRefProp(curCode, -1, ""));
        caseFiche.setInvoiceRef(invoiceRef);
        caseFiche.setClRate(clRate);
        caseFiche.setClCurr(new FicheRefProp(clCurr, -1, ""));
        caseFiche.setVisitInfoId(visitInfoId);
        return caseFiche;
    }
}
