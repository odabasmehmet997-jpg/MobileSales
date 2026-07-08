package com.proje.mobilesales.core.netsis;


public class DataResponse<T> extends Response {
    private T data;
    public T getData() {
        return this.data;
    }
    public void setData(T t) {
        this.data = t;
    }
}
