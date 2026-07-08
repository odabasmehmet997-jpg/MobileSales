package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipLine;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import io.reactivex.Observable;

import java.util.Iterator;
import java.util.List;

public class PrintSlipModel extends PrintBaseModel {
    private ItemSlip mItemSlip;
    private Cari mShippingCustomer;
    public ItemSlip getItemSlip() {
        return this.mItemSlip;
    }
    public void setItemSlip(ItemSlip itemSlip) {
        this.mItemSlip = itemSlip;
    }
    public PrintSlipModel(ItemSlip itemSlip, Cari cari, Cari cari2, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mItemSlip = itemSlip;
        this.mShippingCustomer = cari2;
        setPrintExtraInfo(list);
    }
    public PrintSlipModel(ItemSlip itemSlip, List<PrintExtraInfo> list) {
        this.mItemSlip = itemSlip;
        setPrintExtraInfo(list);
    }
    public Cari getShippingCustomer() {
        return this.mShippingCustomer;
    }
    public void setShippingCustomer(Cari cari) {
        this.mShippingCustomer = cari;
    }
    public void changeItemSlipFicheNo(String str) {
        getItemSlip().getSlipHeader().setSlipNo(str);
        Iterator<ItemSlipLine> it = getItemSlip().getLines().iterator();
        while (it.hasNext()) {
            it.next().setSTraFATIRSNO(str);
        }
    }
    public boolean hasData() {
        return getItemSlip() != null && getItemSlip().getLines() != null && getItemSlip().getLines().size() > 0;
    }
    public boolean isEmpty() {
        return false;
    }
    public Object get(int i) {
        return null;
    }
    public boolean booleanValue() {
        return false;
    }
    public Object getCode() {
        return null;
    }
    public Object getPayPlan() {
        return null;
    }
    public double getGrossTotal() {
        return 0;
    }
    public SalesDetail findDetailByLineNr(int sira) {
        return null;
    }
    public MixedReceiptsMain getMixedReceiptsMain() {
        return null;
    }
    public int size() {
        return 0;
    }
    public String getFicheNo() {
        return null;
    }
    public boolean isSuccess() {
        return false;
    }
    public void setCaseFiche(CaseFiche caseFiche) {
    }
    public boolean getClRef() {
        return false;
    }
    public FicheType getFicheType() {
        return null;
    }
    public char[] getTotal() {
        return new char[0];
    }

    public <T> Observable<T> stream() {
        return null;
    }
}
