package com.proje.mobilesales.features.collections.casefiche.viewmodel;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.repository.ICaseFicheRepository;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;

public final class CaseFicheViewModel extends BaseViewModel {
    private final ICaseFicheRepository repository;
    private final String tag;
    public CaseFicheViewModel(final ICaseFicheRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "CaseFicheViewModel";
    }
    public void getCaseFiche(final int i2, final ResponseListener<CaseFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.getCaseFiche(i2, listener);
        Log.i(tag, "GetCaseFiche");
    }
    public void saveCaseFiche(final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.saveCaseFiche(caseFiche, receiptType, listener);
        Log.i(tag, "SaveCaseFiche");
    }
    public void updateCaseFiche(final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        repository.updateCaseFiche(caseFiche, receiptType, listener);
        Log.i(tag, "UpdateCaseFiche");
    }
    public boolean isSalesPersonOnDesc() {
        return repository.isSalesPersonOnDesc();
    }
}
