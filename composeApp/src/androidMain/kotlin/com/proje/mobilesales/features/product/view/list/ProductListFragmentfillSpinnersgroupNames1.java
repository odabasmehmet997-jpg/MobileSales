package com.proje.mobilesales.features.product.view.list;
import com.proje.mobilesales.features.model.FormGroupModel;
import kotlin.jvm.internal.PropertyReference1Impl;

final class ProductListFragmentfillSpinnersgroupNames1 extends PropertyReference1Impl {
    public static final ProductListFragmentfillSpinnersgroupNames1 INSTANCE = new ProductListFragmentfillSpinnersgroupNames1();
    ProductListFragmentfillSpinnersgroupNames1() {
        super(FormGroupModel.class, "groupName", "getGroupName()Ljava/lang/String;", 0);
    }
    public Object get(Object obj) {
        return ((FormGroupModel) obj).getGroupName();
    }
}
