package com.proje.mobilesales.features.collections.chequeanddeedfiche.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;


/* compiled from: IChequeAndDeedFicheRepository.kt */

public interface IChequeAndDeedFicheRepository extends IBaseRepository {
    void getChequeDeedFiche(int i2, int i3, ResponseListener<ChequeDeedFiche> responseListener);

    void saveChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);

    void updateChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);
}
