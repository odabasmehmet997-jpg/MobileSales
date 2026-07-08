package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.features.sales.model.Sales;

import java.util.ArrayList;

public interface ResponseListener<T> {
    void onError(String str);
    void onFailure(Throwable throwable);
    void onResponse(Boolean aBoolean);
    void onResponse(Sales sales);
    void onResponse(TigerServiceResult tigerServiceResult);
    void onResponse(T obj);
    void onResponse(ArrayList<T> obj);

    void onResponse();

    void onResponse(Integer integer);
}
