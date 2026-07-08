package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class OrderStatus {
    private static final  EnumEntries ENTRIES;
    private static final  OrderStatus[] VALUES;
    public static final Companion Companion;

    @StringRes
    private int mResId;
    private int mStatus;
    public static final OrderStatus ALL = new OrderStatus("ALL", 0, 0, -1);
    public static final OrderStatus OFFER = new OrderStatus("OFFER", 1, 1, R.string.str_suggestion);
    public static final OrderStatus UNDISPATCHABLE = new OrderStatus("UNDISPATCHABLE", 2, 2, R.string.str_can_not_be_shipped);
    public static final OrderStatus DISPATCHABLE = new OrderStatus("DISPATCHABLE", 3, 4, R.string.str_can_be_shipped);

    private static OrderStatus[] values() {
        return new OrderStatus[]{ALL, OFFER, UNDISPATCHABLE, DISPATCHABLE};
    }

    public static EnumEntries<OrderStatus> getEntries() {
        return ENTRIES;
    }

    public static OrderStatus valueOf(String str) {
        return Enum.valueOf(OrderStatus.class, str);
    }

    public static OrderStatus[] values() {
        return VALUES.clone();
    }

    static {
        OrderStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getMStatus() {
        return this.mStatus;
    }

    public void setMStatus(int i2) {
        this.mStatus = i2;
    }

    public int getMResId() {
        return this.mResId;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    private OrderStatus(String str, @StringRes int i2, int i3, int i4) {
        this.mStatus = i3;
        this.mResId = i4;
    }

    private OrderStatus(String str, int i2, int i3) {
        this.mStatus = i3;
        if (i3 == 1) {
            this.mResId = R.string.str_suggestion;
        } else if (i3 == 2) {
            this.mResId = R.string.str_can_be_shipped;
        } else if (i3 == 4) {
            this.mResId = R.string.str_can_not_be_shipped;
        }
    }

    public int getmStatus() {
        return this.mStatus;
    }

    public void setmStatus(int i2) {
        this.mStatus = i2;
    }

    public int getmResId() {
        return this.mResId;
    }

    public void setmResId(int i2) {
        this.mResId = i2;
    }

    /* compiled from: OrderStatus.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public OrderStatus fromOrderStatus(int i2) {
            for (OrderStatus orderStatus : OrderStatus.getEntries()) {
                if (orderStatus.getMStatus() == i2) {
                    return orderStatus;
                }
            }
            return OrderStatus.ALL;
        }
    }
}
