package com.proje.mobilesales.features.activity;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.sql.ISqlManager;
import dagger.MembersInjector;
import javax.inject.Provider;



public final class BarcodeScannerView_MembersInjector implements MembersInjector<BarcodeScannerView> {
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<BaseErp> mBaseErpProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;
    private final Provider<ISqlManager> sqlManagerProvider;

    public BarcodeScannerView_MembersInjector(final Provider<AlertDialogBuilder> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<ISqlManager> provider3, final Provider<BaseErp> provider4) {
        mAlertDialogBuilderProvider = provider;
        mProgressDialogBuilderProvider = provider2;
        sqlManagerProvider = provider3;
        mBaseErpProvider = provider4;
    }

    public static MembersInjector<BarcodeScannerView> create(final Provider<AlertDialogBuilder> provider, final Provider<ProgressDialogBuilder> provider2, final Provider<ISqlManager> provider3, final Provider<BaseErp> provider4) {
        return new BarcodeScannerView_MembersInjector(provider, provider2, provider3, provider4);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(final BarcodeScannerView barcodeScannerView) {
        BarcodeScannerView_MembersInjector.injectMAlertDialogBuilder(barcodeScannerView, mAlertDialogBuilderProvider.get());
        BarcodeScannerView_MembersInjector.injectMProgressDialogBuilder(barcodeScannerView, mProgressDialogBuilderProvider.get());
        BarcodeScannerView_MembersInjector.injectSqlManager(barcodeScannerView, sqlManagerProvider.get());
        BarcodeScannerView_MembersInjector.injectMBaseErp(barcodeScannerView, mBaseErpProvider.get());
    }

    public static void injectMAlertDialogBuilder(final BarcodeScannerView barcodeScannerView, final AlertDialogBuilder alertDialogBuilder) {
        barcodeScannerView.mAlertDialogBuilder = alertDialogBuilder;
    }

    public static void injectMProgressDialogBuilder(final BarcodeScannerView barcodeScannerView, final ProgressDialogBuilder progressDialogBuilder) {
        barcodeScannerView.mProgressDialogBuilder = progressDialogBuilder;
    }

    public static void injectSqlManager(final BarcodeScannerView barcodeScannerView, final ISqlManager iSqlManager) {
        barcodeScannerView.sqlManager = iSqlManager;
    }

    public static void injectMBaseErp(final BarcodeScannerView barcodeScannerView, final BaseErp baseErp) {
        barcodeScannerView.mBaseErp = baseErp;
    }
}
