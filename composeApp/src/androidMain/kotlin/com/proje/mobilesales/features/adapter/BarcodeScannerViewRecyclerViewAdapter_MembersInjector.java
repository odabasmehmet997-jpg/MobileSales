package com.proje.mobilesales.features.adapter;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;



public final class BarcodeScannerViewRecyclerViewAdapter_MembersInjector implements MembersInjector<BarcodeScannerViewRecyclerViewAdapter> {
    private final Provider<BaseErp> baseErpProvider;
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;
    private final Provider<ProgressDialogBuilder> mProgressDialogBuilderProvider;

    public BarcodeScannerViewRecyclerViewAdapter_MembersInjector(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3) {
        this.baseErpProvider = provider;
        this.mProgressDialogBuilderProvider = provider2;
        this.mAlertDialogBuilderProvider = provider3;
    }

    public static MembersInjector<BarcodeScannerViewRecyclerViewAdapter> create(Provider<BaseErp> provider, Provider<ProgressDialogBuilder> provider2, Provider<AlertDialogBuilder> provider3) {
        return new BarcodeScannerViewRecyclerViewAdapter_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter) {
        injectBaseErp(barcodeScannerViewRecyclerViewAdapter, this.baseErpProvider.get());
        injectMProgressDialogBuilder(barcodeScannerViewRecyclerViewAdapter, this.mProgressDialogBuilderProvider.get());
        injectMAlertDialogBuilder(barcodeScannerViewRecyclerViewAdapter, this.mAlertDialogBuilderProvider.get());
    }

    public static void injectBaseErp(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter, BaseErp baseErp) {
        barcodeScannerViewRecyclerViewAdapter.baseErp = baseErp;
    }

    public static void injectMProgressDialogBuilder(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter, ProgressDialogBuilder progressDialogBuilder) {
        barcodeScannerViewRecyclerViewAdapter.mProgressDialogBuilder = progressDialogBuilder;
    }

    public static void injectMAlertDialogBuilder(BarcodeScannerViewRecyclerViewAdapter barcodeScannerViewRecyclerViewAdapter, AlertDialogBuilder alertDialogBuilder) {
        barcodeScannerViewRecyclerViewAdapter.mAlertDialogBuilder = alertDialogBuilder;
    }
}
