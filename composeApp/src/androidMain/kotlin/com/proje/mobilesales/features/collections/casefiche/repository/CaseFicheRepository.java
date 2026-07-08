package com.proje.mobilesales.features.collections.casefiche.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;

public final class CaseFicheRepository extends BaseRepository implements ICaseFicheRepository {
    public CaseFicheRepository() {
        super(baseRepositorybaseErp2);
    }

    public void getCaseFiche(final int i2, final ResponseListener<CaseFiche> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().getCaseFiche(i2, listener);
    }
    public void saveCaseFiche(final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().saveCaseFiche(caseFiche, receiptType, listener);
    }
    public void updateCaseFiche(final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
        Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.getSqlManager().updateCaseFiche(caseFiche, receiptType, listener);
    }
    public boolean isSalesPersonOnDesc() {
        return this.getBaseErp().getIsSalesPersonOnDesc();
    }
}
