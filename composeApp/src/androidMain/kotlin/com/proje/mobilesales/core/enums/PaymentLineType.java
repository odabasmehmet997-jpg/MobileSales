package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PaymentLineType {
    private static final  EnumEntries ENTRIES;
    private static final  PaymentLineType[] VALUES;
    private final int value;
    public static final PaymentLineType ISLEMYAPILMAYACAK = new PaymentLineType("ISLEMYAPILMAYACAK", 0, 0);
    public static final PaymentLineType NAKIT = new PaymentLineType("NAKIT", 1, 1);
    public static final PaymentLineType CEK = new PaymentLineType(DailyInfo.CEK, 2, 2);
    public static final PaymentLineType SENET = new PaymentLineType(DailyInfo.SENET, 3, 3);
    public static final PaymentLineType KREDIKARTI = new PaymentLineType("KREDIKARTI", 4, 4);
    public static final PaymentLineType MAGAZAKARTI = new PaymentLineType("MAGAZAKARTI", 5, 5);
    public static final PaymentLineType TAKSIT = new PaymentLineType("TAKSIT", 6, 6);
    public static final PaymentLineType DBS = new PaymentLineType("DBS", 7, 7);
    public static final PaymentLineType BANKAHAVALEEFT = new PaymentLineType("BANKAHAVALEEFT", 8, 8);

    private static PaymentLineType[] values() {
        return new PaymentLineType[]{ISLEMYAPILMAYACAK, NAKIT, CEK, SENET, KREDIKARTI, MAGAZAKARTI, TAKSIT, DBS, BANKAHAVALEEFT};
    }

    public static EnumEntries<PaymentLineType> getEntries() {
        return ENTRIES;
    }

    public static PaymentLineType valueOf(String str) {
        return Enum.valueOf(PaymentLineType.class, str);
    }

    public static PaymentLineType[] values() {
        return VALUES.clone();
    }

    private PaymentLineType(String str, int i2, int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        PaymentLineType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
