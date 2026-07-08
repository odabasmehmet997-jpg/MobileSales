package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import java.util.List;

public class PrintSafeDepositModel extends PrintBaseModel {
    private CaseFiche mCaseFiche;
    private SafeDeposit mSafeDeposit;

    public PrintSafeDepositModel(SafeDeposit safeDeposit, Cari cari, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mSafeDeposit = safeDeposit;
        setPrintExtraInfo(list);
    }

    public PrintSafeDepositModel(SafeDeposit safeDeposit, Cari cari, CaseFiche caseFiche, List<PrintExtraInfo> list) {
        setCustomer(cari);
        this.mSafeDeposit = safeDeposit;
        setPrintExtraInfo(list);
        this.mCaseFiche = caseFiche;
    }

    public boolean hasData() {
        return getSafeDeposit() != null;
    }

    public SafeDeposit getSafeDeposit() {
        return this.mSafeDeposit;
    }

    public void setSafeDeposit(SafeDeposit safeDeposit) {
        this.mSafeDeposit = safeDeposit;
    }

    public CaseFiche getCaseFiche() {
        return this.mCaseFiche;
    }

    public void setCaseFiche(CaseFiche caseFiche) {
        this.mCaseFiche = caseFiche;
    }
}
