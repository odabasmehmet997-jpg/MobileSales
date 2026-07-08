package com.proje.mobilesales.features.collections.chequeanddeedfiche.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ChequeAndDeedFicheRepository.kt */

public final class ChequeAndDeedFicheRepository extends BaseRepository implements IChequeAndDeedFicheRepository {
    public ChequeAndDeedFicheRepository() {
        super(baseRepositorybaseErp2);
    }

    @Override // com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.IChequeAndDeedFicheRepository
    public void getChequeDeedFiche(final int i2, final int i3, final ResponseListener<ChequeDeedFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().getChequeDeedFiche(i2, i3, listener);
    }

    @Override // com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.IChequeAndDeedFicheRepository
    public void saveChequeDeedFiche(final ChequeDeedFiche chequeDeedFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(chequeDeedFiche, "chequeDeedFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().saveChequeDeedFiche(chequeDeedFiche, receiptType, listener);
    }

    @Override // com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.IChequeAndDeedFicheRepository
    public void updateChequeDeedFiche(final ChequeDeedFiche chequeDeedFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(chequeDeedFiche, "chequeDeedFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().updateChequeDeedFiche(chequeDeedFiche, receiptType, listener);
    }
}
