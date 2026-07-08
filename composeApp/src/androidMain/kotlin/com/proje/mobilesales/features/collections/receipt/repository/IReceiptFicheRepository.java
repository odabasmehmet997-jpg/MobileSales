package com.proje.mobilesales.features.collections.receipt.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.model.EmailReplacerModel;

public interface IReceiptFicheRepository extends IBaseRepository {
    void deleteReceiptFicheLocal(int i2, ReceiptType receiptType, int i3, ResponseListener<Integer> responseListener);

    EmailReplacerModel getReceipt(int i2, ReceiptType receiptType);
}
