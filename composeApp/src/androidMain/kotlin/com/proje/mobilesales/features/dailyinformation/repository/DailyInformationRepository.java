package com.proje.mobilesales.features.dailyinformation.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
 
public final class DailyInformationRepository extends BaseRepository implements IDailyInformationRepository {

    public DailyInformationRepository() {
        super(baseRepositorybaseErp2);
    }

    public boolean getInvoiceCancelFiche() {
        return getBaseErp().getInvoiceCancelFiche();
    }

    
    public boolean getCashDeleteRight() {
        return getBaseErp().getCashDeleteRight();
    }

    
    public boolean getCashEditRight() {
        return getBaseErp().getCashEditRight();
    }

    
    public boolean getCashCopyRight() {
        return getBaseErp().getCaseCashCopyRight();
    }

    
    public boolean getCaseCashDeleteRight() {
        return getBaseErp().getCaseCashDeleteRight();
    }

    
    public boolean getCaseCashEditRight() {
        return getBaseErp().getCaseCashEditRight();
    }

    
    public boolean getCaseCashCopyRight() {
        return getBaseErp().getCaseCashCopyRight();
    }

    
    public boolean getCreditCardDeleteRight() {
        return getBaseErp().getCreditCardDeleteRight();
    }

    
    public boolean getCreditCardEditRight() {
        return getBaseErp().getCreditCardEditRight();
    }

    
    public boolean getCreditCardCopyRight() {
        return getBaseErp().getCreditCardCopyRight();
    }

    
    public boolean getChequeDeleteRight() {
        return getBaseErp().getChequeDeleteRight();
    }

    
    public boolean getChequeEditRight() {
        return getBaseErp().getChequeEditRight();
    }

    
    public boolean getChequeCopyRight() {
        return getBaseErp().getChequeCopyRight();
    }

    
    public boolean getDeedDeleteRight() {
        return getBaseErp().getDeedDeleteRight();
    }

    
    public boolean getDeedEditRight() {
        return getBaseErp().getDeedEditRight();
    }

    
    public boolean getDeedCopyRight() {
        return getBaseErp().getDeedCopyRight();
    }

    
    public void getDailyInfoList(String query, ResponseListener<ArrayList<DailyInfo>> responseListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getDailyInfoList(query, responseListener);
    }

    
    public boolean getOrderStatusChangeFiche() {
        return getBaseErp().getOrderStatusChangeFiche();
    }
}
