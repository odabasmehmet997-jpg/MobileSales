package com.proje.mobilesales.features.model;

public class PriceChangeEvent {
    boolean change;
    public PriceChangeEvent(final boolean z) {
        change = z;
    }
    public boolean isChange() {
        return change;
    }
    public void setChange(final boolean z) {
        change = z;
    }
}
