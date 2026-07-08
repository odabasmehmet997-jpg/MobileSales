package com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;


/* compiled from: ICashAndCreditCardFicheRepository.kt */

public interface ICashAndCreditCardFicheRepository extends IBaseRepository {
    void getCashCreditFiche(int i2, int i3, ResponseListener<CashCreditFiche> responseListener);

    void saveCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void updateCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);
}
