package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EWaybillInfo {

    @SerializedName("TASIYICIIL")
    @Expose
    private String carrierCity;

    @SerializedName("TASIYICIULKE")
    @Expose
    private String carrierCountry;

    @SerializedName("TASIYICIILCE")
    @Expose
    private String carrierCounty;

    @SerializedName("TASIYICIADI")
    @Expose
    private String carrierName;

    @SerializedName("TASIYICIPOSTAKODU")
    @Expose
    private String carrierPostCode;

    @SerializedName("TASIYICIVKN")
    @Expose
    private String carrierTaxNr;

    @SerializedName("SOFOR1ACIKLAMA")
    @Expose
    private String firstDriverDescription;

    @SerializedName("SOFOR1TCKN")
    @Expose
    private String firstDriverIdentityNr;

    @SerializedName("SOFOR1SOYADI")
    @Expose
    private String firstDriverLastName;

    @SerializedName("SOFOR1ADI")
    @Expose
    private String firstDriverName;

    @SerializedName("DORSEPLAKA1")
    @Expose
    private String firstTrailerPlate;

    @SerializedName("PLAKA")
    @Expose
    private String plate;

    @SerializedName("SOFOR2ACIKLAMA")
    @Expose
    private String secondDriverDescription;

    @SerializedName("SOFOR2TCKN")
    @Expose
    private String secondDriverIdentityNr;

    @SerializedName("SOFOR2SOYADI")
    @Expose
    private String secondDriverLastName;

    @SerializedName("SOFOR2ADI")
    @Expose
    private String secondDriverName;

    @SerializedName("DORSEPLAKA2")
    @Expose
    private String secondTrailerPlate;

    @SerializedName("SEVKTAR")
    @Expose
    private String shipmentDate;

    @SerializedName("SOFOR3ACIKLAMA")
    @Expose
    private String thirdDriverDescription;

    @SerializedName("SOFOR3TCKN")
    @Expose
    private String thirdDriverIdentityNr;

    @SerializedName("SOFOR3SOYADI")
    @Expose
    private String thirdDriverLastName;

    @SerializedName("SOFOR3ADI")
    @Expose
    private String thirdDriverName;

    @SerializedName("DORSEPLAKA3")
    @Expose
    private String thirdTrailerPlate;

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String str) {
        this.plate = str;
    }

    public String getCarrierTaxNr() {
        return this.carrierTaxNr;
    }

    public void setCarrierTaxNr(String str) {
        this.carrierTaxNr = str;
    }

    public String getCarrierName() {
        return this.carrierName;
    }

    public void setCarrierName(String str) {
        this.carrierName = str;
    }

    public String getCarrierCounty() {
        return this.carrierCounty;
    }

    public void setCarrierCounty(String str) {
        this.carrierCounty = str;
    }

    public String getCarrierCity() {
        return this.carrierCity;
    }

    public void setCarrierCity(String str) {
        this.carrierCity = str;
    }

    public String getCarrierCountry() {
        return this.carrierCountry;
    }

    public void setCarrierCountry(String str) {
        this.carrierCountry = str;
    }

    public String getCarrierPostCode() {
        return this.carrierPostCode;
    }

    public void setCarrierPostCode(String str) {
        this.carrierPostCode = str;
    }

    public String getFirstDriverName() {
        return this.firstDriverName;
    }

    public void setFirstDriverName(String str) {
        this.firstDriverName = str;
    }

    public String getFirstDriverLastName() {
        return this.firstDriverLastName;
    }

    public void setFirstDriverLastName(String str) {
        this.firstDriverLastName = str;
    }

    public String getFirstDriverDescription() {
        return this.firstDriverDescription;
    }

    public void setFirstDriverDescription(String str) {
        this.firstDriverDescription = str;
    }

    public String getFirstDriverIdentityNr() {
        return this.firstDriverIdentityNr;
    }

    public void setFirstDriverIdentityNr(String str) {
        this.firstDriverIdentityNr = str;
    }

    public String getSecondDriverName() {
        return this.secondDriverName;
    }

    public void setSecondDriverName(String str) {
        this.secondDriverName = str;
    }

    public String getSecondDriverLastName() {
        return this.secondDriverLastName;
    }

    public void setSecondDriverLastName(String str) {
        this.secondDriverLastName = str;
    }

    public String getSecondDriverDescription() {
        return this.secondDriverDescription;
    }

    public void setSecondDriverDescription(String str) {
        this.secondDriverDescription = str;
    }

    public String getSecondDriverIdentityNr() {
        return this.secondDriverIdentityNr;
    }

    public void setSecondDriverIdentityNr(String str) {
        this.secondDriverIdentityNr = str;
    }

    public String getThirdDriverName() {
        return this.thirdDriverName;
    }

    public void setThirdDriverName(String str) {
        this.thirdDriverName = str;
    }

    public String getThirdDriverLastName() {
        return this.thirdDriverLastName;
    }

    public void setThirdDriverLastName(String str) {
        this.thirdDriverLastName = str;
    }

    public String getThirdDriverDescription() {
        return this.thirdDriverDescription;
    }

    public void setThirdDriverDescription(String str) {
        this.thirdDriverDescription = str;
    }

    public String getThirdDriverIdentityNr() {
        return this.thirdDriverIdentityNr;
    }

    public void setThirdDriverIdentityNr(String str) {
        this.thirdDriverIdentityNr = str;
    }

    public String getShipmentDate() {
        return this.shipmentDate;
    }

    public void setShipmentDate(String str) {
        this.shipmentDate = str;
    }

    public String getFirstTrailerPlate() {
        return this.firstTrailerPlate;
    }

    public void setFirstTrailerPlate(String str) {
        this.firstTrailerPlate = str;
    }

    public String getSecondTrailerPlate() {
        return this.secondTrailerPlate;
    }

    public void setSecondTrailerPlate(String str) {
        this.secondTrailerPlate = str;
    }

    public String getThirdTrailerPlate() {
        return this.thirdTrailerPlate;
    }

    public void setThirdTrailerPlate(String str) {
        this.thirdTrailerPlate = str;
    }
}
