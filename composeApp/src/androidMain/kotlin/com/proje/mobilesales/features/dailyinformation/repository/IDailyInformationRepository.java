package com.proje.mobilesales.features.dailyinformation.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import java.util.ArrayList;

public interface IDailyInformationRepository extends IBaseRepository {
    boolean getCaseCashCopyRight();

    boolean getCaseCashDeleteRight();

    boolean getCaseCashEditRight();

    boolean getCashCopyRight();

    boolean getCashDeleteRight();

    boolean getCashEditRight();

    boolean getChequeCopyRight();

    boolean getChequeDeleteRight();

    boolean getChequeEditRight();

    boolean getCreditCardCopyRight();

    boolean getCreditCardDeleteRight();

    boolean getCreditCardEditRight();

    void getDailyInfoList(String str, ResponseListener<ArrayList<DailyInfo>> responseListener);

    boolean getDeedCopyRight();

    boolean getDeedDeleteRight();

    boolean getDeedEditRight();

    boolean getInvoiceCancelFiche();

    boolean getOrderStatusChangeFiche();
}
