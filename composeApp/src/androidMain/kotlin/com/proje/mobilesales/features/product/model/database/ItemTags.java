package com.proje.mobilesales.features.product.model.database;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ItemTags {

    private String itemCode;
    private String tag;
    public ItemTags() {
        this(null, null, 3, null);
    }

    public static  ItemTags copy(final ItemTags itemTags, String str, String str2, final int i, final Object obj) {
        if (0 != (i & 1)) {
            str = itemTags.itemCode;
        }
        if (0 != (i & 2)) {
            str2 = itemTags.tag;
        }
        return itemTags.copy(str, str2);
    }

    public String component1() {
        return itemCode;
    }

    public String component2() {
        return tag;
    }

    public ItemTags copy(final String str, final String str2) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "tag");
        return new ItemTags(str, str2);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ItemTags itemTags)) {
            return false;
        }
        return Intrinsics.areEqual(itemCode, itemTags.itemCode) && Intrinsics.areEqual(tag, itemTags.tag);
    }

    public int hashCode() {
        return (itemCode.hashCode() * 31) + tag.hashCode();
    }

    public String toString() {
        return "ItemTags(itemCode=" + itemCode + ", tag=" + tag + ')';
    }

    public ItemTags(final String str, final String str2) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "tag");
        itemCode = str;
        tag = str2;
    }

    public ItemTags(final String str, final String str2, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i & 1) ? "" : str, 0 != (i & 2) ? "" : str2);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        itemCode = str;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        tag = str;
    }
}
