package com.proje.mobilesales.features.collections.cashandcreditcardfiche.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.ICashAndCreditCardFicheRepository;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashAndCreditCardFicheViewModel.kt */

public final class CashAndCreditCardFicheViewModel extends BaseViewModel {
    private final ICashAndCreditCardFicheRepository repository;
    private final String tag;

    
    public CashAndCreditCardFicheViewModel(final ICashAndCreditCardFicheRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "CashAndCreditCardFicheViewModel";
    }

    @SuppressLint("LongLogTag")
    public void getCashCreditFiche(final int i2, final int i3, final ResponseListener<CashCreditFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.getCashCreditFiche(i2, i3, listener);
        Log.i(tag, "GetCashCreditFiche");
    }

    @SuppressLint("LongLogTag")
    public void saveCashCreditFiche(final CashCreditFiche cashCreditFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(cashCreditFiche, "cashCreditFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.saveCashCreditFiche(cashCreditFiche, receiptType, listener);
        Log.i(tag, "SaveCashCreditFiche");
    }

    @SuppressLint("LongLogTag")
    public void updateCashCreditFiche(final CashCreditFiche cashCreditFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(cashCreditFiche, "cashCreditFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.updateCashCreditFiche(cashCreditFiche, receiptType, listener);
        Log.i(tag, "UpdateCashCreditFiche");
    }
}
