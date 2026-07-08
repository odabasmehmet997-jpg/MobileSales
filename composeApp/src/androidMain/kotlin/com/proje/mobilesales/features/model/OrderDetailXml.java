package com.proje.mobilesales.features.model;

public class OrderDetailXml {
    private String auxilCode;
    private int canConfig;
    private int composite;
    private double currPrice;
    private String deliveryCode;
    private String dueDate;
    private String masterCode;
    private int orderReserve;
    private String paymentCode;
    private double pcPrice;
    private double price;
    private double quantity;
    private double rcXRate;
    private String salesmanCode;
    private String transDescription;
    private int type;
    private String unitCode;
    private double unitConv1;
    private double unitConv2;
    private String variantCode;
    private String variantName;
    private int vatIncluded;
    private double vatRate;

    public int getType() {
        return type;
    }

    public void setType(final int i2) {
        type = i2;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(final String str) {
        masterCode = str;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public void setVariantCode(final String str) {
        variantCode = str;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(final String str) {
        variantName = str;
    }

    public int getCanConfig() {
        return canConfig;
    }

    public void setCanConfig(final int i2) {
        canConfig = i2;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(final double d2) {
        vatRate = d2;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(final double d2) {
        quantity = d2;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(final String str) {
        unitCode = str;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double d2) {
        price = d2;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(final String str) {
        salesmanCode = str;
    }

    public double getUnitConv1() {
        return unitConv1;
    }

    public void setUnitConv1(final double d2) {
        unitConv1 = d2;
    }

    public double getUnitConv2() {
        return unitConv2;
    }

    public void setUnitConv2(final double d2) {
        unitConv2 = d2;
    }

    public double getPcPrice() {
        return pcPrice;
    }

    public void setPcPrice(final double d2) {
        pcPrice = d2;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(final double d2) {
        currPrice = d2;
    }

    public double getRcXRate() {
        return rcXRate;
    }

    public void setRcXRate(final double d2) {
        rcXRate = d2;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(final String str) {
        dueDate = str;
    }

    public int getOrderReserve() {
        return orderReserve;
    }

    public void setOrderReserve(final int i2) {
        orderReserve = i2;
    }

    public int getVatIncluded() {
        return vatIncluded;
    }

    public void setVatIncluded(final int i2) {
        vatIncluded = i2;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(final String str) {
        transDescription = str;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(final String str) {
        paymentCode = str;
    }

    public String getAuxilCode() {
        return auxilCode;
    }

    public void setAuxilCode(final String str) {
        auxilCode = str;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(final String str) {
        deliveryCode = str;
    }

    public int getComposite() {
        return composite;
    }

    public void setComposite(final int i2) {
        composite = i2;
    }
}
