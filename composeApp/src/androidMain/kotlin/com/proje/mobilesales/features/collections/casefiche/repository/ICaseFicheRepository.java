package com.proje.mobilesales.features.collections.casefiche.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import kotlin.jvm.internal.Intrinsics;

public interface ICaseFicheRepository extends IBaseRepository {
    void getCaseFiche(int i2, ResponseListener<CaseFiche> responseListener);
    boolean isSalesPersonOnDesc();
    void saveCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);
    void updateCaseFiche(CaseFiche caseFiche, ReceiptType receiptType, ResponseListener<Boolean> responseListener);
    enum DefaultImpls {
        ;

        public static void getCaseFiche(final ICaseFicheRepository iCaseFicheRepository, final int i2, final ResponseListener<CaseFiche> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            iCaseFicheRepository.getSqlManager().getCaseFiche(i2, listener);
        }
        public static void saveCaseFiche(final ICaseFicheRepository iCaseFicheRepository, final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
            Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
            Intrinsics.checkNotNullParameter(receiptType, "receiptType");
            Intrinsics.checkNotNullParameter(listener, "listener");
            iCaseFicheRepository.getSqlManager().saveCaseFiche(caseFiche, receiptType, listener);
        }
        public static void updateCaseFiche(final ICaseFicheRepository iCaseFicheRepository, final CaseFiche caseFiche, final ReceiptType receiptType, final ResponseListener<Boolean> listener) {
            Intrinsics.checkNotNullParameter(caseFiche, "caseFiche");
            Intrinsics.checkNotNullParameter(receiptType, "receiptType");
            Intrinsics.checkNotNullParameter(listener, "listener");
            iCaseFicheRepository.getSqlManager().updateCaseFiche(caseFiche, receiptType, listener);
        }
        public static boolean isSalesPersonOnDesc(final ICaseFicheRepository iCaseFicheRepository) {
            return iCaseFicheRepository.getBaseErp().getIsSalesPersonOnDesc();
        }
    }
}
