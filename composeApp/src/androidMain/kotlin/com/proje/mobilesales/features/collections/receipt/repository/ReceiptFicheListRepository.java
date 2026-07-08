package com.proje.mobilesales.features.collections.receipt.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import kotlin.jvm.internal.Intrinsics;

public final class ReceiptFicheListRepository extends BaseRepository implements IReceiptFicheRepository {
    public ReceiptFicheListRepository() {
        super(baseRepositorybaseErp2);
    }

    public EmailReplacerModel getReceipt(final int i2, final ReceiptType receiptType) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        final EmailReplacerModel receipt = this.getBaseErp().getReceipt(i2, receiptType);
        Intrinsics.checkNotNullExpressionValue(receipt, "getReceipt(...)");
        return receipt;
    }

    public void deleteReceiptFicheLocal(final int i2, final ReceiptType receiptType, final int i3, final ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getSqlManager().deleteReceiptFicheLocal(i2, receiptType, i3, responseListener);
    }
}
