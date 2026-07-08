package com.proje.mobilesales.features.product.model;

import android.text.TextUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProductTreeItem {
    private String code;
    private String name;
    private ProductTreeItem parentItem;

    public ProductTreeItem() {
        this(null, null, null, 7, null);
    }

    public static  ProductTreeItem copydefault(final ProductTreeItem productTreeItem, String str, String str2, ProductTreeItem productTreeItem2, final int i, final Object obj) {
        if (0 != (i & 1)) {
            str = productTreeItem.code;
        }
        if (0 != (i & 2)) {
            str2 = productTreeItem.name;
        }
        if (0 != (i & 4)) {
            productTreeItem2 = productTreeItem.parentItem;
        }
        return productTreeItem.copy(str, str2, productTreeItem2);
    }

    public String component1() {
        return code;
    }

    public String component2() {
        return name;
    }

    public ProductTreeItem component3() {
        return parentItem;
    }

    public ProductTreeItem copy(final String str, final String str2, final ProductTreeItem productTreeItem) {
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
        return new ProductTreeItem(str, str2, productTreeItem);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductTreeItem productTreeItem)) {
            return false;
        }
        return Intrinsics.areEqual(code, productTreeItem.code) && Intrinsics.areEqual(name, productTreeItem.name) && Intrinsics.areEqual(parentItem, productTreeItem.parentItem);
    }

    public int hashCode() {
        final int hashCode = ((code.hashCode() * 31) + name.hashCode()) * 31;
        final ProductTreeItem productTreeItem = parentItem;
        return hashCode + (null == productTreeItem ? 0 : productTreeItem.hashCode());
    }

    public String toString() {
        return "ProductTreeItem(code=" + code + ", name=" + name + ", parentItem=" + parentItem + ')';
    }

    public ProductTreeItem(final String str, final String str2, final ProductTreeItem productTreeItem) {
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
        code = str;
        name = str2;
        parentItem = productTreeItem;
    }

    public   ProductTreeItem(final String str, final String str2, final ProductTreeItem productTreeItem, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i & 1) ? "" : str, 0 != (i & 2) ? "" : str2, 0 != (i & 4) ? null : productTreeItem);
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        code = str;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        name = str;
    }

    public ProductTreeItem getParentItem() {
        return parentItem;
    }

    public void setParentItem(final ProductTreeItem productTreeItem) {
        parentItem = productTreeItem;
    }

    public ProductTreeItem(final String str, final String str2) {
        this(str, str2, null);
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
    }
    public ProductTreeItem(final String str, final String str2, final String str3, final String str4) {
        this(str, str2, new ProductTreeItem(str3, str4));
        Intrinsics.checkNotNullParameter(str, "code");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "parentCode");
        Intrinsics.checkNotNullParameter(str4, "parentName");
    }

    public String getDescription() {
        return TextUtils.isEmpty(name) ? code : name;
    }
}
