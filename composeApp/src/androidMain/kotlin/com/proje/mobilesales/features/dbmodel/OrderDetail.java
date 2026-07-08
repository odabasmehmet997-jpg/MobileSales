package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.base.BaseDbSalesFicheDetail;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.sales.model.SalesDetail;

public class OrderDetail extends BaseDbSalesFicheDetail {

    private int barcodeCount;
    private String discountRate;
    private int reserve;
    private String shipCode;
    private String shipDate;
    private int wareHouse;
    public void convertSalesDetail(SalesDetail salesDetail, int i2) {
        super.convertSalesDetail(salesDetail, i2);
        setReserve(StringUtils.convertBooleanToInt(Boolean.valueOf(salesDetail.getReserve().isSelect())));
        setShipCode(salesDetail.getDeliveryCode().toString());
        setShipDate(salesDetail.getDeliveryDate().toString());
        setWareHouse(salesDetail.getWareHouse().getLogicalRef());
        setBarcodeCount(salesDetail.getBarcodeCount());
        setDiscountRate(salesDetail.getCampaignDiscountRatio());
    }
    public SalesDetail convertSalesFicheDetailToSalesDetail() {
        SalesDetail convertSalesFicheDetailToSalesDetail = super.convertSalesFicheDetailToSalesDetail();
        convertSalesFicheDetailToSalesDetail.setReserve(new FicheBooleanProp(StringUtils.convertIntToBoolean(this.reserve)));
        convertSalesFicheDetailToSalesDetail.setDeliveryCode(new FicheRefProp(-1, -1, this.shipCode));
        convertSalesFicheDetailToSalesDetail.setDeliveryDate(new FicheDateProp(this.shipDate));
        convertSalesFicheDetailToSalesDetail.setWareHouse(new FicheRefProp(this.wareHouse, -1, ""));
        convertSalesFicheDetailToSalesDetail.setBarcodeCount(getBarcodeCount());
        convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(getDiscountRate() != null ? getDiscountRate() : "");
        return convertSalesFicheDetailToSalesDetail;
    }
    public int getBarcodeCount() {
        return this.barcodeCount;
    }
    public void setBarcodeCount(int i2) {
        this.barcodeCount = i2;
    }
    public int getReserve() {
        return this.reserve;
    }
    public void setReserve(int i2) {
        this.reserve = i2;
    }
    public String getShipCode() {
        return this.shipCode;
    }
    public void setShipCode(String str) {
        this.shipCode = str;
    }
    public String getShipDate() {
        return this.shipDate;
    }
    public void setShipDate(String str) {
        this.shipDate = str;
    }
    public int getWareHouse() {
        return this.wareHouse;
    }
    public void setWareHouse(int i2) {
        this.wareHouse = i2;
    }
    public String getDiscountRate() {
        return this.discountRate;
    }
    public void setDiscountRate(String str) {
        this.discountRate = str;
    }
}
