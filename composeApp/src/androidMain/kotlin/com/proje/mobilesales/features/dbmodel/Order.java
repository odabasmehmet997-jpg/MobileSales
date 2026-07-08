package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.enums.OrderStatus;
import com.proje.mobilesales.core.enums.UpdatedOrderFicheStatus;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order extends BaseDbSalesFiche {

    private String custOrderNo;
    private int insteadOfEDespatch;
    private List<OrderDetail> orderDetails;
    private String orderFicheStatus;
    private int orderType;
    private int payed;
    private int reserved;
    private String shipDate;
    private String sourceWareHouseName;
    private int sourceWareHouseNr;
    private int status;
    private int visitInfoId;
    public void convertSales(Sales sales) {
        super.convertSales(sales);
        this.ficheStatus = sales.getSalesStatus();
        setStatus((sales.getSalesStatus() == 1 ? OrderStatus.OFFER : OrderStatus.DISPATCHABLE).getmStatus());
        setPayed(StringUtils.convertBooleanToInt(Boolean.valueOf(sales.getPaymentOrder().isSelect())));
        setShipDate(sales.getDeliveryDate().toString());
        setCustOrderNo(sales.getCustomerOrderNo().toString());
        setOrderType(sales.getOrderType());
        setReserved(StringUtils.convertBooleanToInt(Boolean.valueOf(sales.getReserved().isSelect())));
        setVisitInfoId(sales.getVisitInfoId());
        setSourceWareHouseNr(sales.getSourceWareHouse().getLogicalRef());
        setSourceWareHouseName(sales.getSourceWareHouse().getDefinition());
        setInsteadOfEDespatch(sales.getInsteadOfEDespatch().isSelect() ? 1 : 0);
        setOrderFicheStatus(ContextUtils.getStringResource(UpdatedOrderFicheStatus.UNKNOWN.getmResId()));
        List<OrderDetail> list = this.orderDetails;
        if (list == null) {
            this.orderDetails = new ArrayList();
        } else {
            list.clear();
        }
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        int i2 = 1;
        while (it.hasNext()) {
            SalesDetail next = it.next();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.convertSalesDetail(next, i2);
            i2++;
            this.orderDetails.add(orderDetail);
        }
    }
    public List<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }
    public void setOrderDetails(List<OrderDetail> list) {
        this.orderDetails = list;
    }
    public int getPayed() {
        return this.payed;
    }
    public void setPayed(int i2) {
        this.payed = i2;
    }
    public String getCustOrderNo() {
        return this.custOrderNo;
    }
    public void setCustOrderNo(String str) {
        this.custOrderNo = str;
    }
    public String getShipDate() {
        return this.shipDate;
    }
    public void setShipDate(String str) {
        this.shipDate = str;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int i2) {
        this.status = i2;
    }
    public int getOrderType() {
        return this.orderType;
    }
    public void setOrderType(int i2) {
        this.orderType = i2;
    }
    public int getVisitInfoId() {
        return this.visitInfoId;
    }
    public void setVisitInfoId(int i2) {
        this.visitInfoId = i2;
    }
    public int getSourceWareHouseNr() {
        return this.sourceWareHouseNr;
    }
    public void setSourceWareHouseNr(int i2) {
        this.sourceWareHouseNr = i2;
    }
    public String getSourceWareHouseName() {
        return this.sourceWareHouseName;
    }
    public void setSourceWareHouseName(String str) {
        this.sourceWareHouseName = str;
    }
    public int getReserved() {
        return this.reserved;
    }
    public void setReserved(int i2) {
        this.reserved = i2;
    }
    public int getInsteadOfEDespatch() {
        return this.insteadOfEDespatch;
    }
    public void setInsteadOfEDespatch(int i2) {
        this.insteadOfEDespatch = i2;
    }
    public String getOrderFicheStatus() {
        return this.orderFicheStatus;
    }
    public void setOrderFicheStatus(String str) {
        this.orderFicheStatus = str;
    }
    public Sales convertSalesFicheToSales() {
        Sales convertSalesFicheToSales = super.convertSalesFicheToSales();
        convertSalesFicheToSales.setSalesStatus(this.ficheStatus);
        convertSalesFicheToSales.setPaymentOrder(new FicheBooleanProp(StringUtils.convertIntToBoolean(this.payed)));
        convertSalesFicheToSales.setDeliveryDate(new FicheDateProp(this.shipDate));
        convertSalesFicheToSales.setCustomerOrderNo(new FicheStringProp(this.custOrderNo));
        convertSalesFicheToSales.setOrderType(this.orderType);
        convertSalesFicheToSales.setEDocSerial(new FicheRefProp(-1, -1, ""));
        convertSalesFicheToSales.setEDespatch(new FicheBooleanProp(false));
        convertSalesFicheToSales.setReserved(new FicheBooleanProp(StringUtils.convertIntToBoolean(this.reserved)));
        convertSalesFicheToSales.setVisitInfoId(this.visitInfoId);
        convertSalesFicheToSales.setSourceWareHouse(new FicheRefProp(this.sourceWareHouseNr, -1, ""));
        convertSalesFicheToSales.setInsteadOfEDespatch(new FicheBooleanProp(getInsteadOfEDespatch() == 1));
        convertSalesFicheToSales.setOrderFicheStatus(this.orderFicheStatus);
        return convertSalesFicheToSales;
    }
}
