package com.proje.mobilesales.features.adapter;

import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class KeyValueAdapter_MembersInjector implements MembersInjector<KeyValueAdapter> {
    private final Provider<AlertDialogBuilder> mAlertDialogBuilderProvider;

    public KeyValueAdapter_MembersInjector(Provider<AlertDialogBuilder> provider) {
        this.mAlertDialogBuilderProvider = provider;
    }

    public static MembersInjector<KeyValueAdapter> create(Provider<AlertDialogBuilder> provider) {
        return new KeyValueAdapter_MembersInjector(provider);
    }
    public void injectMembers(KeyValueAdapter keyValueAdapter) {
        injectMAlertDialogBuilder(keyValueAdapter, this.mAlertDialogBuilderProvider.get());
    }

    public static void injectMAlertDialogBuilder(KeyValueAdapter keyValueAdapter, AlertDialogBuilder alertDialogBuilder) {
        keyValueAdapter.mAlertDialogBuilder = alertDialogBuilder;
    }
}
