package com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.IChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeAndDeedFicheViewModel.kt */

public final class ChequeAndDeedFicheViewModel extends BaseViewModel {
    private final IChequeAndDeedFicheRepository repository;
    private final String tag;

    
    public ChequeAndDeedFicheViewModel(final IChequeAndDeedFicheRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "ChequeAndDeedFicheViewModel";
    }

    @SuppressLint("LongLogTag")
    public void getChequeDeedFiche(final int i2, final int i3, final ResponseListener<ChequeDeedFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.getChequeDeedFiche(i2, i3, listener);
        Log.i(tag, "GetChequeDeedFiche");
    }

    @SuppressLint("LongLogTag")
    public void saveChequeDeedFiche(final ChequeDeedFiche chequeDeedFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(chequeDeedFiche, "chequeDeedFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.saveChequeDeedFiche(chequeDeedFiche, receiptType, listener);
        Log.i(tag, "SaveChequeDeedFiche");
    }

    @SuppressLint("LongLogTag")
    public void updateChequeDeedFiche(final ChequeDeedFiche chequeDeedFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(chequeDeedFiche, "chequeDeedFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.updateChequeDeedFiche(chequeDeedFiche, receiptType, listener);
        Log.i(tag, "UpdateChequeDeedFiche");
    }
}
