package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.base.BaseDbSalesFicheDetail;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.util.ArrayList;

public class InvoiceDetail extends BaseDbSalesFicheDetail {
    private int barcodeCount;
    private String deliveryCode;
    private String discountRate;
    private int distributionLineRef;
    private int distributionRef;
    private int druleRef;
    private String logoOrderDetailRef;
    private String logoOrderNo;
    private String logoOrderRef;
    private double orderAmount;
    private String seriLotNo;
    private ArrayList<Serilot> serilotList = new ArrayList<>();
    private int sipKont;
    private String sipNum;
    private int slTransRef;
    private int wareHouse;
    public int getDruleRef() {
        return this.druleRef;
    }
    public void setDruleRef(int i2) {
        this.druleRef = i2;
    }
    public double getOrderAmount() {
        return this.orderAmount;
    }
    public void setOrderAmount(double d2) {
        this.orderAmount = d2;
    }
    public String getLogoOrderRef() {
        return this.logoOrderRef;
    }
    public void setLogoOrderRef(String str) {
        this.logoOrderRef = str;
    }
    public String getLogoOrderDetailRef() {
        return this.logoOrderDetailRef;
    }
    public void setLogoOrderDetailRef(String str) {
        this.logoOrderDetailRef = str;
    }
    public String getLogoOrderNo() {
        return this.logoOrderNo;
    }
    public void setLogoOrderNo(String str) {
        this.logoOrderNo = str;
    }
    public String getSeriLotNo() {
        return this.seriLotNo;
    }
    public void setSeriLotNo(String str) {
        this.seriLotNo = str;
    }
    public int getSlTransRef() {
        return this.slTransRef;
    }
    public void setSlTransRef(int i2) {
        this.slTransRef = i2;
    }
    public void setDeliveryCode(String str) {
        this.deliveryCode = str;
    }
    public String getDeliveryCode() {
        return this.deliveryCode;
    }
    public int getBarcodeCount() {
        return this.barcodeCount;
    }
    public void setBarcodeCount(int i2) {
        this.barcodeCount = i2;
    }
    public String getDiscountRate() {
        return this.discountRate;
    }
    public void setDiscountRate(String str) {
        this.discountRate = str;
    }
    public void convertSalesDetail(SalesDetail salesDetail, int i2) {
        super.convertSalesDetail(salesDetail, i2);
        this.slTransRef = salesDetail.getSerialLotTransfer();
        this.serilotList = salesDetail.getSalesSerialLots();
        setDeliveryCode(salesDetail.getDeliveryCode().toString());
        setLogoOrderDetailRef(StringUtils.convertIntToString(salesDetail.getOrderDetailReference()));
        setOrderAmount(salesDetail.getOrderAmount());
        setLogoOrderRef(Integer.toString(salesDetail.getOrderReference()));
        setSipKont(salesDetail.getSipKont());
        setSipNum(salesDetail.getSipNum());
        setWareHouse(salesDetail.getWareHouse().getLogicalRef());
        setDistributionRef(salesDetail.getDistributionRef());
        setDistributionLineRef(salesDetail.getDistributionLineRef());
        setBarcodeCount(salesDetail.getBarcodeCount());
        setDiscountRate(salesDetail.getCampaignDiscountRatio());
    }
    public SalesDetail convertSalesFicheDetailToSalesDetail() {
        SalesDetail convertSalesFicheDetailToSalesDetail = super.convertSalesFicheDetailToSalesDetail();
        convertSalesFicheDetailToSalesDetail.setSerialLotTransfer(this.slTransRef);
        convertSalesFicheDetailToSalesDetail.setSalesSerialLots(getSerilotList());
        convertSalesFicheDetailToSalesDetail.setDeliveryCode(new FicheRefProp(-1, -1, this.deliveryCode));
        convertSalesFicheDetailToSalesDetail.setOrderDetailReference(StringUtils.convertStringToInt(getLogoOrderDetailRef()));
        convertSalesFicheDetailToSalesDetail.setOrderAmount(getOrderAmount());
        convertSalesFicheDetailToSalesDetail.setOrderReference(StringUtils.convertStringToInt(getLogoOrderRef()));
        convertSalesFicheDetailToSalesDetail.setSipKont(getSipKont());
        convertSalesFicheDetailToSalesDetail.setSipNum(getSipNum());
        convertSalesFicheDetailToSalesDetail.setWareHouse(new FicheRefProp(this.wareHouse, -1, ""));
        convertSalesFicheDetailToSalesDetail.setDistributionRef(getDistributionRef());
        convertSalesFicheDetailToSalesDetail.setDistributionLineRef(getDistributionLineRef());
        convertSalesFicheDetailToSalesDetail.setBarcodeCount(getBarcodeCount());
        convertSalesFicheDetailToSalesDetail.setCampaignDiscountRatio(getDiscountRate() != null ? getDiscountRate() : "");
        return convertSalesFicheDetailToSalesDetail;
    }
    public ArrayList<Serilot> getSerilotList() {
        return this.serilotList;
    }
    public void setSerilotList(ArrayList<Serilot> arrayList) {
        this.serilotList = arrayList;
    }
    public String getSipNum() {
        return this.sipNum;
    }
    public void setSipNum(String str) {
        this.sipNum = str;
    }
    public int getSipKont() {
        return this.sipKont;
    }
    public void setSipKont(int i2) {
        this.sipKont = i2;
    }
    public int getWareHouse() {
        return this.wareHouse;
    }
    public void setWareHouse(int i2) {
        this.wareHouse = i2;
    }
    public int getDistributionRef() {
        return this.distributionRef;
    }
    public void setDistributionRef(int i2) {
        this.distributionRef = i2;
    }
    public int getDistributionLineRef() {
        return this.distributionLineRef;
    }
    public void setDistributionLineRef(int i2) {
        this.distributionLineRef = i2;
    }
}
