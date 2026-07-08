package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import java.util.List;

public class PrintMixedReceiptModel extends PrintBaseModel {
    private MixedReceiptsMain mMixedReceiptsMain;

    public MixedReceiptsMain getMixedReceiptsMain() {
        return this.mMixedReceiptsMain;
    }

    public void setMixedReceiptsMain(MixedReceiptsMain mixedReceiptsMain) {
        this.mMixedReceiptsMain = mixedReceiptsMain;
    }

    public PrintMixedReceiptModel(MixedReceiptsMain mixedReceiptsMain, Cari cari, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mMixedReceiptsMain = mixedReceiptsMain;
        setPrintExtraInfo(list);
    }

    public boolean hasData() {
        return getMixedReceiptsMain() != null && getMixedReceiptsMain().getTahsilats() != null && getMixedReceiptsMain().getTahsilats().size() > 0;
    }
}
