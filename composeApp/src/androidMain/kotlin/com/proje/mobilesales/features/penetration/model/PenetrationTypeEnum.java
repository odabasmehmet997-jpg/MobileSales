package com.proje.mobilesales.features.penetration.model;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
 
public final class PenetrationTypeEnum extends Enum<PenetrationTypeEnum> {
    private static final EnumEntries ENTRIES;
    private static final PenetrationTypeEnum[] VALUES;
    private final int resId;
    private final int value;
    public static final PenetrationTypeEnum MIKTAR = new PenetrationTypeEnum("MIKTAR", 0, 0, R.string.str_quantity);
    public static final PenetrationTypeEnum VARYOK = new PenetrationTypeEnum("VARYOK", 1, 1, R.string.str_stock_in_out);
    public static final Companion Companion = new Companion(null);

    private static PenetrationTypeEnum[] values() {
        return new PenetrationTypeEnum[]{MIKTAR, VARYOK};
    }

    public static EnumEntries<PenetrationTypeEnum> getEntries() {
        return ENTRIES;
    }

    public static PenetrationTypeEnum valueOf(String str) {
        return Enum.valueOf(PenetrationTypeEnum.class, str);
    }

    public static PenetrationTypeEnum[] values() {
        return VALUES.clone();
    }

    private PenetrationTypeEnum(String str, @StringRes int i, int i2, int i3) {
        this.value = i2;
        this.resId = i3;
    }

    public int getResId() {
        return this.resId;
    }

    public int getValue() {
        return this.value;
    }

    static {
        PenetrationTypeEnum[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public PenetrationTypeEnum getPenetRationType(int i) {
            PenetrationTypeEnum[] values = PenetrationTypeEnum.values();
            for (PenetrationTypeEnum penetrationTypeEnum : values) {
                if (penetrationTypeEnum.getValue() == i) {
                    return penetrationTypeEnum;
                }
            }
            return PenetrationTypeEnum.MIKTAR;
        }
    }
}
