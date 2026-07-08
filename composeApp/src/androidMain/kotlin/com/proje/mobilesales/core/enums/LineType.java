package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.R;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class LineType {
    private static final EnumEntries ENTRIES;
    private static final LineType[] VALUES;
    public static final Companion Companion;
    private static Class<? extends T> LineType;
    private int resId;
    public int value;
    public static final LineType PRODUCT = new LineType("PRODUCT", 0, 0, R.string.str_material);
    public static final LineType PROMOTION = new LineType("PROMOTION", 1, 1, R.string.str_promotion);
    public static final LineType DISCOUNT = new LineType("DISCOUNT", 2, 2, R.string.str_discount);
    public static final LineType EXPENSE = new LineType("EXPENSE", 3, 3, R.string.str_expense);
    public static final LineType SERVICE = new LineType("SERVICE", 4, 4, R.string.str_service);
    public static final LineType DEPOZIT = new LineType("DEPOZIT", 5, 5, R.string.str_deposit);
    public static final LineType COMPOSITE_COLI = new LineType("COMPOSITE_COLI", 6, 6, R.string.str_composite_package);
    public static final LineType COMPOSITE_COLI_DETAIL = new LineType("COMPOSITE_COLI_DETAIL", 7, 7, R.string.str_composite_package_detail);
    public static final LineType FIXED_ASSET = new LineType("FIXED_ASSET", 8, 8, R.string.str_fixed_asset);
    public static final LineType ADDITION_PRODUCT = new LineType("ADDITION_PRODUCT", 9, 9, R.string.str_addition_product);
    public static final LineType PRODUCT_CLASS = new LineType("PRODUCT_CLASS", 10, 10, R.string.str_product_class);
    public static final LineType FASON = new LineType("FASON", 11, 11, R.string.str_contract);
    private static  LineType[] values() {
        return new LineType[]{PRODUCT, PROMOTION, DISCOUNT, EXPENSE, SERVICE, DEPOZIT, COMPOSITE_COLI, COMPOSITE_COLI_DETAIL, FIXED_ASSET, ADDITION_PRODUCT, PRODUCT_CLASS, FASON};
    }
    private LineType(String str, int r2, int r3, int r4) {
        this.value = r3;
        this.resId = r4;
    }
    public int getResId() {
        return this.resId;
    }
    public void setResId(int r1) {
        this.resId = r1;
    }
    static {
        LineType[] lineTypeArrvalues;
        lineTypeArrvalues = values();
        VALUES = lineTypeArrvalues;
        ENTRIES = enumEntries();
        Companion = new Companion(null);
    }
    private static EnumEntries enumEntries() {
        return null;
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }

    }
}
