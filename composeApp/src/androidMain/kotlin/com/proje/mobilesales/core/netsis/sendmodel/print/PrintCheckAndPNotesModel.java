package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import java.util.List;

public class PrintCheckAndPNotesModel extends PrintBaseModel {
    private ChequeDeedFiche mChequeDeed;
    private NetsisChequeAndDeedMain mNetsisChequeAndDeedMain;

    public boolean hasData() {
        return getNetsisChequeAndDeedMain() != null && getNetsisChequeAndDeedMain().getEvraklar() != null && getNetsisChequeAndDeedMain().getEvraklar().size() > 0;
    }

    public NetsisChequeAndDeedMain getNetsisChequeAndDeedMain() {
        return this.mNetsisChequeAndDeedMain;
    }

    public void setNetsisChequeAndDeedMain(NetsisChequeAndDeedMain netsisChequeAndDeedMain) {
        this.mNetsisChequeAndDeedMain = netsisChequeAndDeedMain;
    }

    public ChequeDeedFiche getChequeDeed() {
        return this.mChequeDeed;
    }

    public void setChequeDeed(ChequeDeedFiche chequeDeedFiche) {
        this.mChequeDeed = chequeDeedFiche;
    }

    public PrintCheckAndPNotesModel(NetsisChequeAndDeedMain netsisChequeAndDeedMain, Cari cari, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mNetsisChequeAndDeedMain = netsisChequeAndDeedMain;
        setPrintExtraInfo(list);
    }

    public PrintCheckAndPNotesModel(NetsisChequeAndDeedMain netsisChequeAndDeedMain, Cari cari, ChequeDeedFiche chequeDeedFiche, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mNetsisChequeAndDeedMain = netsisChequeAndDeedMain;
        setPrintExtraInfo(list);
        this.mChequeDeed = chequeDeedFiche;
    }
}
