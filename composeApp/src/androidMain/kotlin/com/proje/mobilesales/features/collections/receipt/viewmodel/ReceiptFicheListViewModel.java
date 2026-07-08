package com.proje.mobilesales.features.collections.receipt.viewmodel;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.collections.receipt.repository.IReceiptFicheRepository;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import kotlin.jvm.internal.Intrinsics;

public final class ReceiptFicheListViewModel extends BaseViewModel {
    private final IReceiptFicheRepository repository;
    private final String tag;

    public ReceiptFicheListViewModel(final IReceiptFicheRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "ReceiptFicheListViewModel";
    }
    public   EmailReplacerModel getReceipt(final int i2, final ReceiptType receiptType) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Log.i(tag, "GetReceipt");
        return repository.getReceipt(i2, receiptType);
    }

    public   void deleteReceiptFicheLocal(final int i2, final ReceiptType receiptType, final int i3, final ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Log.i(tag, "DeleteReceiptFicheLocal");
        repository.deleteReceiptFicheLocal(i2, receiptType, i3, responseListener);
    }
}
