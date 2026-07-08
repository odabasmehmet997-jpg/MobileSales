package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import java.util.List;

public class OrderXml {
    private int affectRisk;
    private String arpCode;
    private String arpCodeShip;
    private String arpName;
    private String authCode;
    private String auxilCode;
    private String campaignCode;
    private int clRef;
    private int currTransaction;
    private int currselDetails;
    private int currselTotal;
    private String custOrderNo;
    private int department;
    private int discRate;
    private int divisionNr;
    private String docNo;
    private String docTrackNr;
    private int factNr;
    private String ficheDate;
    private int ficheRef;
    private String gDate;
    private int logicalRef;
    private String notes1;
    private String notes2;
    private String notes3;
    private String notes4;
    private String number;
    private List<OrderDetailXml> orderDetailXmls;
    private int orderStatus;
    private String paymentCode;
    private String projectCode;
    private int rcRate;
    private String salesmanCode;
    private String shipLocCode;
    private String shipmentType;
    private String shippingAgent;
    private int sourceCostGrp;
    private int sourceWh;
    private int tcRate;
    private int time;
    private String tradingGrp;
    private int withPayment;

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public int getFicheRef() {
        return ficheRef;
    }

    public void setFicheRef(final int i2) {
        ficheRef = i2;
    }

    public String getgDate() {
        return gDate;
    }

    public int getgDateLogo() {
        return DateAndTimeUtils.getLogoDateTimeCode(gDate);
    }

    public void setgDate(final String str) {
        gDate = str;
    }

    public String getFicheDate() {
        return ficheDate;
    }

    public void setFicheDate(final String str) {
        ficheDate = str;
    }

    public String getArpName() {
        return arpName;
    }

    public void setArpName(final String str) {
        arpName = str;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String str) {
        number = str;
    }

    public int getClRef() {
        return clRef;
    }

    public void setClRef(final int i2) {
        clRef = i2;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(final String str) {
        docNo = str;
    }

    public String getDocTrackNr() {
        return docTrackNr;
    }

    public void setDocTrackNr(final String str) {
        docTrackNr = str;
    }

    public int getTime() {
        return time;
    }

    public void setTime(final int i2) {
        time = i2;
    }

    public String getAuxilCode() {
        return auxilCode;
    }

    public void setAuxilCode(final String str) {
        auxilCode = str;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(final String str) {
        authCode = str;
    }

    public String getArpCode() {
        return arpCode;
    }

    public void setArpCode(final String str) {
        arpCode = str;
    }

    public String getArpCodeShip() {
        return arpCodeShip;
    }

    public void setArpCodeShip(final String str) {
        arpCodeShip = str;
    }

    public int getSourceWh() {
        return sourceWh;
    }

    public void setSourceWh(final int i2) {
        sourceWh = i2;
    }

    public int getSourceCostGrp() {
        return sourceCostGrp;
    }

    public void setSourceCostGrp(final int i2) {
        sourceCostGrp = i2;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(final String str) {
        notes1 = str;
    }

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(final String str) {
        notes2 = str;
    }

    public String getNotes3() {
        return notes3;
    }

    public void setNotes3(final String str) {
        notes3 = str;
    }

    public String getNotes4() {
        return notes4;
    }

    public void setNotes4(final String str) {
        notes4 = str;
    }

    public String getShipLocCode() {
        return shipLocCode;
    }

    public void setShipLocCode(final String str) {
        shipLocCode = str;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(final String str) {
        paymentCode = str;
    }

    public int getDivisionNr() {
        return divisionNr;
    }

    public void setDivisionNr(final int i2) {
        divisionNr = i2;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(final String str) {
        salesmanCode = str;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(final String str) {
        shipmentType = str;
    }

    public String getShippingAgent() {
        return shippingAgent;
    }

    public void setShippingAgent(final String str) {
        shippingAgent = str;
    }

    public String getTradingGrp() {
        return tradingGrp;
    }

    public void setTradingGrp(final String str) {
        tradingGrp = str;
    }

    public int getFactNr() {
        return factNr;
    }

    public void setFactNr(final int i2) {
        factNr = i2;
    }

    public String getCustOrderNo() {
        return custOrderNo;
    }

    public void setCustOrderNo(final String str) {
        custOrderNo = str;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(final String str) {
        projectCode = str;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(final String str) {
        campaignCode = str;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(final int i2) {
        department = i2;
    }

    public int getWithPayment() {
        return withPayment;
    }

    public void setWithPayment(final int i2) {
        withPayment = i2;
    }

    public int getAffectRisk() {
        return affectRisk;
    }

    public void setAffectRisk(final int i2) {
        affectRisk = i2;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final int i2) {
        orderStatus = i2;
    }

    public int getCurrselTotal() {
        return currselTotal;
    }

    public void setCurrselTotal(final int i2) {
        currselTotal = i2;
    }

    public int getCurrselDetails() {
        return currselDetails;
    }

    public void setCurrselDetails(final int i2) {
        currselDetails = i2;
    }

    public int getCurrTransaction() {
        return currTransaction;
    }

    public void setCurrTransaction(final int i2) {
        currTransaction = i2;
    }

    public int getTcRate() {
        return tcRate;
    }

    public void setTcRate(final int i2) {
        tcRate = i2;
    }

    public int getRcRate() {
        return rcRate;
    }

    public void setRcRate(final int i2) {
        rcRate = i2;
    }

    public int getDiscRate() {
        return discRate;
    }

    public void setDiscRate(final int i2) {
        discRate = i2;
    }

    public boolean isUpdate() {
        return 0 < this.ficheRef;
    }

    public List<OrderDetailXml> getOrderDetailXmls() {
        return orderDetailXmls;
    }

    public void setOrderDetailXmls(final List<OrderDetailXml> list) {
        orderDetailXmls = list;
    }
}
