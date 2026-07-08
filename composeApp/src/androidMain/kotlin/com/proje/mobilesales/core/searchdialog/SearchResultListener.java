package com.proje.mobilesales.core.searchdialog;



public interface SearchResultListener<T> {
    void onCancelled(BaseSearchDialogCompat baseSearchDialogCompat);

    void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, T t, int i2);
}
