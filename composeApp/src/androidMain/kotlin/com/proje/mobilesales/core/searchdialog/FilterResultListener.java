package com.proje.mobilesales.core.searchdialog;

import java.util.ArrayList;



public interface FilterResultListener<T> {
    void onFilter(ArrayList<T> arrayList);
}
