package com.proje.mobilesales.features.product.view.list;

import com.proje.mobilesales.features.model.SelectedVariant;
import kotlin.jvm.internal.PropertyReference1Impl;

final class C3085x7b16e9b2 extends PropertyReference1Impl {
    public static final C3085x7b16e9b2 INSTANCE = new C3085x7b16e9b2();
    C3085x7b16e9b2() {
        super(SelectedVariant.class, "amount", "getAmount()D", 0);
    }
    public Object get(Object obj) {
        return Double.valueOf(((SelectedVariant) obj).getAmount());
    }
}
