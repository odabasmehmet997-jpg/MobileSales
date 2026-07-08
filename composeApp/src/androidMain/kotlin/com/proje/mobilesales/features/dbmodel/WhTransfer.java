package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Tables(alterVersion = 171, name = DailyInfo.WHTRANSFER)

public class WhTransfer extends BaseDbSalesFiche {

    @Column(name = "EDESPATCH", shared = @ColumnProperty(alterVersion = 173, defaultValue = "0", type = Column.ColumnValueTypes.INTEGER))
    private int eDespatch;
    private EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
    private List<WhTransferDetail> mWhTransferDetails;

    @Column(name = "SERIAL", shared = @ColumnProperty(alterVersion = 173, type = Column.ColumnValueTypes.TEXT))
    private String serial;

    @Column(name = "SRCBRANCHNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int srcBranchNr;

    @Column(name = "SRCCOSTGRP", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int srcCostGrp;

    @Column(name = "SRCDIVISNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int srcDivisNr;

    @Column(name = "SRCFACTNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int srcFactNr;

    @Column(name = "SRCWAREHOUSENR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int srcWareHouseNr;

    @Override // com.proje.mobilesales.core.base.BaseDbSalesFiche, com.proje.mobilesales.core.interfaces.ConvertSales
    public void convertSales(Sales sales) {
        super.convertSales(sales);
        if (getWhTransferDetails() == null) {
            setWhTransferDetails(new ArrayList());
        } else {
            getWhTransferDetails().clear();
        }
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        int i2 = 1;
        while (it.hasNext()) {
            SalesDetail next = it.next();
            WhTransferDetail whTransferDetail = new WhTransferDetail();
            whTransferDetail.convertSalesDetail(next, i2);
            getWhTransferDetails().add(whTransferDetail);
            i2++;
        }
        if (sales.getTransferSourceDivision() != null) {
            this.srcDivisNr = sales.getDivision().getLogicalRef();
        }
        if (sales.getTransferSourceBranch() != null) {
            this.srcBranchNr = sales.getBranch().getLogicalRef();
        }
        if (sales.getTransferSourceFactory() != null) {
            this.srcFactNr = sales.getFactory().getLogicalRef();
        }
        if (sales.getTransferSourceWareHouse() != null) {
            this.srcWareHouseNr = sales.getWareHouse().getLogicalRef();
        }
        if (sales.getTransferSourceCostGrp() != null) {
            this.srcCostGrp = sales.getTransferSourceCostGrp().getLogicalRef();
        }
        setSerial(sales.getEDocSerial().getDefinition());
        seteDespatch(sales.getEDespatch().isSelect() ? 1 : 0);
        seteDespatchAdditionalInfo(sales.getEDispatchAdditionalInfo());
    }

    @Override // com.proje.mobilesales.core.base.BaseDbSalesFiche, com.proje.mobilesales.core.interfaces.ConvertSales
    public Sales convertSalesFicheToSales() {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        Sales convertSalesFicheToSales = super.convertSalesFicheToSales();
        convertSalesFicheToSales.setTransferSourceDivision(new FicheRefProp(this.srcDivisNr, -1, ""));
        convertSalesFicheToSales.setTransferSourceBranch(new FicheRefProp(this.srcBranchNr, -1, ""));
        convertSalesFicheToSales.setTransferSourceFactory(new FicheRefProp(this.srcFactNr, -1, ""));
        convertSalesFicheToSales.setTransferSourceWareHouse(new FicheRefProp(baseErp.getErpType() == ErpType.NETSIS ? this.srcWareHouseNr : baseErp.getUser().getWarehouseNr(), -1, ""));
        convertSalesFicheToSales.setTransferSourceCostGrp(new FicheRefProp(getSrcCostGrp(), -1, ""));
        convertSalesFicheToSales.setEDocSerial(new FicheRefProp(-1, -1, getSerial()));
        convertSalesFicheToSales.setEDespatch(new FicheBooleanProp(geteDespatch() == 1));
        convertSalesFicheToSales.setEDispatchAdditionalInfo(geteDespatchAdditionalInfo());
        convertSalesFicheToSales.setInsteadOfEDespatch(new FicheBooleanProp(false));
        return convertSalesFicheToSales;
    }

    public int getSrcDivisNr() {
        return this.srcDivisNr;
    }

    public void setSrcDivisNr(int i2) {
        this.srcDivisNr = i2;
    }

    public int getSrcBranchNr() {
        return this.srcBranchNr;
    }

    public void setSrcBranchNr(int i2) {
        this.srcBranchNr = i2;
    }

    public int getSrcFactNr() {
        return this.srcFactNr;
    }

    public void setSrcFactNr(int i2) {
        this.srcFactNr = i2;
    }

    public int getSrcWareHouseNr() {
        return this.srcWareHouseNr;
    }

    public void setSrcWareHouseNr(int i2) {
        this.srcWareHouseNr = i2;
    }

    public List<WhTransferDetail> getWhTransferDetails() {
        return this.mWhTransferDetails;
    }

    public void setWhTransferDetails(List<WhTransferDetail> list) {
        this.mWhTransferDetails = list;
    }

    public int getSrcCostGrp() {
        return this.srcCostGrp;
    }

    public void setSrcCostGrp(int i2) {
        this.srcCostGrp = i2;
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

    public EDispatchAdditionalInfo geteDespatchAdditionalInfo() {
        return this.eDispatchAdditionalInfo;
    }

    public void seteDespatchAdditionalInfo(EDispatchAdditionalInfo eDispatchAdditionalInfo) {
        this.eDispatchAdditionalInfo = eDispatchAdditionalInfo;
    }
}
