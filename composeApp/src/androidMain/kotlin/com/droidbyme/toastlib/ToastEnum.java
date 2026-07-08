package com.droidbyme.toastlib;

public enum ToastEnum {
    SHORT (1),
    LONG (2);
    private final int id;

    ToastEnum(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }
}
