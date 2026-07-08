package com.proje.mobilesales.features.dailyinformation.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dailyinformation.repository.IDailyInformationRepository;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class DailyInformationViewModel extends BaseViewModel {
    private final IDailyInformationRepository repository;
    private final String tag;

    public DailyInformationViewModel(IDailyInformationRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.tag = "DailyInformationViewModel";
    }

    public boolean getInvoiceCancelFiche() {
        return this.repository.getInvoiceCancelFiche();
    }

    public boolean getCashDeleteRight() {
        return this.repository.getCashDeleteRight();
    }

    public boolean getCashEditRight() {
        return this.repository.getCashEditRight();
    }

    public boolean getCashCopyRight() {
        return this.repository.getCashCopyRight();
    }

    public boolean getCaseCashDeleteRight() {
        return this.repository.getCaseCashDeleteRight();
    }

    public boolean getCaseCashEditRight() {
        return this.repository.getCaseCashEditRight();
    }

    public boolean getCaseCashCopyRight() {
        return this.repository.getCaseCashCopyRight();
    }

    public boolean getCreditCardDeleteRight() {
        return this.repository.getCreditCardDeleteRight();
    }

    public boolean getCreditCardEditRight() {
        return this.repository.getCreditCardEditRight();
    }

    public boolean getCreditCardCopyRight() {
        return this.repository.getCreditCardCopyRight();
    }

    public boolean getChequeDeleteRight() {
        return this.repository.getChequeDeleteRight();
    }

    public boolean getChequeEditRight() {
        return this.repository.getChequeEditRight();
    }

    public boolean getChequeCopyRight() {
        return this.repository.getChequeCopyRight();
    }

    public boolean getDeedDeleteRight() {
        return this.repository.getDeedDeleteRight();
    }

    public boolean getDeedEditRight() {
        return this.repository.getDeedEditRight();
    }

    public boolean getDeedCopyRight() {
        return this.repository.getDeedCopyRight();
    }

    public void getDailyInfoList(String query, ResponseListener<ArrayList<DailyInfo>> responseListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getDailyInfoList(query, responseListener);
    }

    public boolean getOrderStatusChangeFiche() {
        return this.repository.getOrderStatusChangeFiche();
    }
}
