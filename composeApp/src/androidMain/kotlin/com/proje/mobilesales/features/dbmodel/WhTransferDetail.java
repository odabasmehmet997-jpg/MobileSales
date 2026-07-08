package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.base.BaseDbSalesFicheDetail;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.util.ArrayList;


@Tables(alterVersion = 171, name = "WHTRANSFERDETAIL")

public class WhTransferDetail extends BaseDbSalesFicheDetail {

    @Column(name = "DELIVERYCODE")
    private String deliveryCode;
    private ArrayList<Serilot> serilotList = new ArrayList<>();

    @Override // com.proje.mobilesales.core.base.BaseDbSalesFicheDetail, com.proje.mobilesales.core.interfaces.ConvertSalesDetail
    public void convertSalesDetail(SalesDetail salesDetail, int i2) {
        super.convertSalesDetail(salesDetail, i2);
        this.serilotList = salesDetail.getSalesSerialLots();
        setDeliveryCode(salesDetail.getDeliveryCode().toString());
    }

    @Override // com.proje.mobilesales.core.base.BaseDbSalesFicheDetail, com.proje.mobilesales.core.interfaces.ConvertSalesDetail
    public SalesDetail convertSalesFicheDetailToSalesDetail() {
        SalesDetail convertSalesFicheDetailToSalesDetail = super.convertSalesFicheDetailToSalesDetail();
        convertSalesFicheDetailToSalesDetail.setSalesSerialLots(getSerilotList());
        convertSalesFicheDetailToSalesDetail.setDeliveryCode(new FicheRefProp(-1, -1, this.deliveryCode));
        return convertSalesFicheDetailToSalesDetail;
    }

    public ArrayList<Serilot> getSerilotList() {
        return this.serilotList;
    }

    public void setSerilotList(ArrayList<Serilot> arrayList) {
        this.serilotList = arrayList;
    }

    public void setDeliveryCode(String str) {
        this.deliveryCode = str;
    }

    public String getDeliveryCode() {
        return this.deliveryCode;
    }
}
