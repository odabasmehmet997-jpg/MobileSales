package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Invoice extends BaseDbSalesFiche {

    private int consignee;
    private int eDespatch;
    private EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
    private int ftype;
    private int insteadOfEDespatch;
    private int invType;
    private List<InvoiceDetail> mInvoiceDetails;
    private String serial;
    private int visitInfoId;
    public void convertSales(Sales sales) {
        super.convertSales(sales);
        SalesType salesType = sales.getmSalesType();
        if (SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            setFtype(1);
            setInvType(0);
        } else if (SalesUtils.isSalesTypeOnlyReturnInvoice(salesType)) {
            setFtype(1);
            setInvType(1);
        } else if (SalesUtils.isSalesTypeDispatch(salesType)) {
            setFtype(0);
            setInvType(0);
        } else if (SalesUtils.isSalesTypeReturnDispatch(salesType)) {
            setFtype(0);
            setInvType(1);
        } else if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            setFtype(2);
            setInvType(0);
        } else if (SalesUtils.isSalesTypeOnlyRetailInvoice(salesType)) {
            setFtype(3);
            setInvType(0);
        } else if (SalesUtils.isSalesTypeOnlyRetailReturnInvoice(salesType)) {
            setFtype(3);
            setInvType(1);
        }
        setConsignee(sales.getConsignee().isSelect() ? 1 : 0);
        setSerial(sales.getEDocSerial().getDefinition());
        seteDespatch(sales.getEDespatch().isSelect() ? 1 : 0);
        seteDespatchAdditionalInfo(sales.getEDispatchAdditionalInfo());
        setVisitInfoId(sales.getVisitInfoId());
        setInsteadOfEDespatch(sales.getInsteadOfEDespatch().isSelect() ? 1 : 0);
        if (getInvoiceDetails() == null) {
            setInvoiceDetails(new ArrayList());
        } else {
            getInvoiceDetails().clear();
        }
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        int i2 = 1;
        while (it.hasNext()) {
            SalesDetail next = it.next();
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.convertSalesDetail(next, i2);
            getInvoiceDetails().add(invoiceDetail);
            i2++;
        }
    }
    public Sales convertSalesFicheToSales() {
        Sales convertSalesFicheToSales = super.convertSalesFicheToSales();
        convertSalesFicheToSales.setConsignee(new FicheBooleanProp(getConsignee() == 1));
        convertSalesFicheToSales.setEDocSerial(new FicheRefProp(-1, -1, getSerial()));
        convertSalesFicheToSales.setEDespatch(new FicheBooleanProp(geteDespatch() == 1));
        convertSalesFicheToSales.setInsteadOfEDespatch(new FicheBooleanProp(getInsteadOfEDespatch() == 1));
        convertSalesFicheToSales.setEDispatchAdditionalInfo(geteDespatchAdditionalInfo());
        convertSalesFicheToSales.setVisitInfoId(this.visitInfoId);
        return convertSalesFicheToSales;
    }
    public List<InvoiceDetail> getmInvoiceDetails() {
        return getInvoiceDetails();
    }
    public void setInvoiceDetails(List<InvoiceDetail> list) {
        this.mInvoiceDetails = list;
    }
    public int getFtype() {
        return this.ftype;
    }
    public void setFtype(int i2) {
        this.ftype = i2;
    }
    public int getInvType() {
        return this.invType;
    }
    public void setInvType(int i2) {
        this.invType = i2;
    }
    public int getConsignee() {
        return this.consignee;
    }
    public void setConsignee(int i2) {
        this.consignee = i2;
    }
    public List<InvoiceDetail> getInvoiceDetails() {
        return this.mInvoiceDetails;
    }
    public String getSerial() {
        return this.serial;
    }
    public void setSerial(String str) {
        this.serial = str;
    }
    public int geteDespatch() {
        return this.eDespatch;
    }
    public void seteDespatch(int i2) {
        this.eDespatch = i2;
    }
    public int getVisitInfoId() {
        return this.visitInfoId;
    }
    public void setVisitInfoId(int i2) {
        this.visitInfoId = i2;
    }
    public int getInsteadOfEDespatch() {
        return this.insteadOfEDespatch;
    }
    public void setInsteadOfEDespatch(int i2) {
        this.insteadOfEDespatch = i2;
    }
    public EDispatchAdditionalInfo geteDespatchAdditionalInfo() {
        return this.eDispatchAdditionalInfo;
    }
    public void seteDespatchAdditionalInfo(EDispatchAdditionalInfo eDispatchAdditionalInfo) {
        this.eDispatchAdditionalInfo = eDispatchAdditionalInfo;
    }
}
