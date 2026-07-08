package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BarcodeScannerProductViewActivity_MembersInjector implements MembersInjector<BarcodeScannerProductViewActivity> {
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<BaseErp> mBaseErpProvider;
    private final Provider<ISqlManager> sqlManagerProvider;
    public BarcodeScannerProductViewActivity_MembersInjector(final Provider<AlertDialogBuilder> provider, final Provider<ISqlManager> provider2, final Provider<BaseErp> provider3) {
        mAlertDialogBuilderProvider = provider;
        sqlManagerProvider = provider2;
        mBaseErpProvider = provider3;
    }
    public static MembersInjector<BarcodeScannerProductViewActivity> create(final Provider<AlertDialogBuilder> provider, final Provider<ISqlManager> provider2, final Provider<BaseErp> provider3) {
        return new BarcodeScannerProductViewActivity_MembersInjector(provider, provider2, provider3);
    }
    public void injectMembers(final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity) {
        BarcodeScannerProductViewActivity_MembersInjector.injectMAlertDialogBuilder(barcodeScannerProductViewActivity, mAlertDialogBuilderProvider.get());
        BarcodeScannerProductViewActivity_MembersInjector.injectSqlManager(barcodeScannerProductViewActivity, sqlManagerProvider.get());
        BarcodeScannerProductViewActivity_MembersInjector.injectMBaseErp(barcodeScannerProductViewActivity, mBaseErpProvider.get());
    }
    public static void injectMAlertDialogBuilder(final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity, final AlertDialogBuilder alertDialogBuilder) {
        barcodeScannerProductViewActivity.mAlertDialogBuilder = alertDialogBuilder;
    }
    public static void injectSqlManager(final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity, final ISqlManager iSqlManager) {
        barcodeScannerProductViewActivity.sqlManager = iSqlManager;
    }
    public static void injectMBaseErp(final BarcodeScannerProductViewActivity barcodeScannerProductViewActivity, final BaseErp baseErp) {
        barcodeScannerProductViewActivity.mBaseErp = baseErp;
    }
}
