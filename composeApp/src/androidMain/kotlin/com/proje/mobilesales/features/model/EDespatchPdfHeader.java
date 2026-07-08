package com.proje.mobilesales.features.model;

import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class EDespatchPdfHeader {

    private String chassisNum1;
    private String clientAddr1;
    private String clientAddr2;
    private String clientCity;
    private String clientCode;
    private String clientDef;
    private String clientDistrict;
    private String clientEmailAddr;
    private String clientFax;
    private String clientIdentityNr;
    private String clientInCharge;
    private String clientPhone1;
    private String clientPhone2;
    private int clientRef;
    private String clientTaxNr;
    private String clientTaxOffice;
    private String clientTaxOfficeCode;
    private String clientTown;
    private String clientWebAddr;
    private String date;
    private String docDate;
    private int docTime;
    private String driverName1;
    private String driverSurname1;
    private String driverTckNo1;
    private int fTime;
    private String ficheNo;
    private String genExp1;
    private String genExp2;
    private String genExp3;
    private String genExp4;
    private double grossTotal;
    private String guid;
    private int logicalRef;
    private double netTotal;
    private String plateNum1;
    private String shipAddressTitle;
    private String shipAgentCode;
    private String shipAgentTaxNr;
    private String shipAgentTitle;
    private String shipDate;
    private int shipTime;
    private String shipTypeCode;
    private int sourceIndex;
    private double totalDiscount;
    private double totalVat;
    private int trCurr;
    private double trRate;

    public String getPlateNum1() {
        return plateNum1;
    }

    public void setPlateNum1(final String str) {
        plateNum1 = str;
    }

    public String getChassisNum1() {
        return chassisNum1;
    }

    public void setChassisNum1(final String str) {
        chassisNum1 = str;
    }

    public String getDriverName1() {
        return driverName1;
    }

    public void setDriverName1(final String str) {
        driverName1 = str;
    }

    public String getDriverSurname1() {
        return driverSurname1;
    }

    public void setDriverSurname1(final String str) {
        driverSurname1 = str;
    }

    public String getDriverTckNo1() {
        return driverTckNo1;
    }

    public void setDriverTckNo1(final String str) {
        driverTckNo1 = str;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(final String str) {
        guid = str;
    }

    public String getFicheNo() {
        return ficheNo;
    }

    public void setFicheNo(final String str) {
        ficheNo = str;
    }

    public int getfTime() {
        return fTime;
    }

    public void setfTime(final int i2) {
        fTime = i2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String str) {
        date = str;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(final String str) {
        docDate = str;
    }

    public int getDocTime() {
        return docTime;
    }

    public void setDocTime(final int i2) {
        docTime = i2;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(final String str) {
        shipDate = str;
    }

    public int getShipTime() {
        return shipTime;
    }

    public void setShipTime(final int i2) {
        shipTime = i2;
    }

    public int getClientRef() {
        return clientRef;
    }

    public void setClientRef(final int i2) {
        clientRef = i2;
    }

    public double getTrRate() {
        return trRate;
    }

    public void setTrRate(final double d2) {
        trRate = d2;
    }

    public int getTrCurr() {
        return trCurr;
    }

    public void setTrCurr(final int i2) {
        trCurr = i2;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(final String str) {
        clientCode = str;
    }

    public String getClientDef() {
        return clientDef;
    }

    public void setClientDef(final String str) {
        clientDef = str;
    }

    public String getClientAddr1() {
        return clientAddr1;
    }

    public void setClientAddr1(final String str) {
        clientAddr1 = str;
    }

    public String getClientAddr2() {
        return clientAddr2;
    }

    public void setClientAddr2(final String str) {
        clientAddr2 = str;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(final String str) {
        clientCity = str;
    }

    public String getClientDistrict() {
        return clientDistrict;
    }

    public void setClientDistrict(final String str) {
        clientDistrict = str;
    }

    public String getClientPhone1() {
        return clientPhone1;
    }

    public void setClientPhone1(final String str) {
        clientPhone1 = str;
    }

    public String getClientPhone2() {
        return clientPhone2;
    }

    public void setClientPhone2(final String str) {
        clientPhone2 = str;
    }

    public String getClientFax() {
        return clientFax;
    }

    public void setClientFax(final String str) {
        clientFax = str;
    }

    public String getClientTaxNr() {
        return clientTaxNr;
    }

    public void setClientTaxNr(final String str) {
        clientTaxNr = str;
    }

    public String getClientTaxOfficeCode() {
        return clientTaxOfficeCode;
    }

    public void setClientTaxOfficeCode(final String str) {
        clientTaxOfficeCode = str;
    }

    public String getClientTown() {
        return clientTown;
    }

    public void setClientTown(final String str) {
        clientTown = str;
    }

    public String getClientTaxOffice() {
        return clientTaxOffice;
    }

    public void setClientTaxOffice(final String str) {
        clientTaxOffice = str;
    }

    public String getClientInCharge() {
        return clientInCharge;
    }

    public void setClientInCharge(final String str) {
        clientInCharge = str;
    }

    public String getClientEmailAddr() {
        return clientEmailAddr;
    }

    public void setClientEmailAddr(final String str) {
        clientEmailAddr = str;
    }

    public String getClientIdentityNr() {
        return clientIdentityNr;
    }

    public void setClientIdentityNr(final String str) {
        clientIdentityNr = str;
    }

    public String getClientWebAddr() {
        return clientWebAddr;
    }

    public void setClientWebAddr(final String str) {
        clientWebAddr = str;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(final double d2) {
        totalVat = d2;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(final double d2) {
        netTotal = d2;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(final double d2) {
        totalDiscount = d2;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(final double d2) {
        grossTotal = d2;
    }

    public String getGenExp1() {
        return genExp1;
    }

    public void setGenExp1(final String str) {
        genExp1 = str;
    }

    public String getGenExp2() {
        return genExp2;
    }

    public void setGenExp2(final String str) {
        genExp2 = str;
    }

    public String getGenExp3() {
        return genExp3;
    }

    public void setGenExp3(final String str) {
        genExp3 = str;
    }

    public String getGenExp4() {
        return genExp4;
    }

    public void setGenExp4(final String str) {
        genExp4 = str;
    }

    public String getShipTypeCode() {
        return shipTypeCode;
    }

    public void setShipTypeCode(final String str) {
        shipTypeCode = str;
    }

    public String getShipAgentCode() {
        return shipAgentCode;
    }

    public void setShipAgentCode(final String str) {
        shipAgentCode = str;
    }

    public int getSourceIndex() {
        return sourceIndex;
    }

    public void setSourceIndex(final int i2) {
        sourceIndex = i2;
    }

    public String getShipAgentTitle() {
        return shipAgentTitle;
    }

    public void setShipAgentTitle(final String str) {
        shipAgentTitle = str;
    }

    public String getShipAgentTaxNr() {
        return shipAgentTaxNr;
    }

    public void setShipAgentTaxNr(final String str) {
        shipAgentTaxNr = str;
    }

    public String getShipAddressTitle() {
        return shipAddressTitle;
    }

    public void setShipAddressTitle(final String str) {
        shipAddressTitle = str;
    }
}
