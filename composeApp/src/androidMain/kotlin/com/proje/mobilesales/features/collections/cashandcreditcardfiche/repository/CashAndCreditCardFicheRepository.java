package com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CashAndCreditCardFicheRepository.kt */

public final class CashAndCreditCardFicheRepository extends BaseRepository implements ICashAndCreditCardFicheRepository {
    public CashAndCreditCardFicheRepository() {
        super(baseRepositorybaseErp2);
    }

    @Override // com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.ICashAndCreditCardFicheRepository
    public void getCashCreditFiche(final int i2, final int i3, final ResponseListener<CashCreditFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().getCashCreditFiche(i2, i3, listener);
    }

    @Override // com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.ICashAndCreditCardFicheRepository
    public void saveCashCreditFiche(final CashCreditFiche cashCreditFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(cashCreditFiche, "cashCreditFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().saveCashCreditFiche(cashCreditFiche, receiptType, listener);
    }

    @Override // com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.ICashAndCreditCardFicheRepository
    public void updateCashCreditFiche(final CashCreditFiche cashCreditFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(cashCreditFiche, "cashCreditFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().updateCashCreditFiche(cashCreditFiche, receiptType, listener);
    }
}
