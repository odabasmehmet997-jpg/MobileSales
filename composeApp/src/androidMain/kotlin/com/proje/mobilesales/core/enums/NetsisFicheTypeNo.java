package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.Intrinsics;

public final class NetsisFicheTypeNo  {
    private static final  EnumEntries ENTRIES;
    private static final  NetsisFicheTypeNo[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final NetsisFicheTypeNo INVOICE = new NetsisFicheTypeNo("INVOICE", 0, 1);
    public static final NetsisFicheTypeNo DISPATCH = new NetsisFicheTypeNo("DISPATCH", 1, 3);
    public static final NetsisFicheTypeNo WHTRANSFER = new NetsisFicheTypeNo(DailyInfo.WHTRANSFER, 2, 8);
    private static  NetsisFicheTypeNo[] values() {
        return new NetsisFicheTypeNo[]{INVOICE, DISPATCH, WHTRANSFER};
    }
    public static int fromSalesType(SalesType salesType) {
        return Companion.fromSalesType(salesType);
    }
    public static Enum valueOf(String str) {
        Class<Enum> NetsisFicheTypeNo = null;
        return Enum.valueOf(NetsisFicheTypeNo, str);
    }
    private NetsisFicheTypeNo(String str, int i2, int i3) {
        this.mValue = i3;
    }
    public int getMValue() {
        return this.mValue;
    }
    public void setMValue(int i2) {
        this.mValue = i2;
    }
    static {
        VALUES = values();
        ENTRIES = enumEntries();
        Companion = new Companion();
    }
    private static EnumEntries enumEntries() {
        return null;
    }
    public static final class Companion {
        public static class WhenMappings {
            public static final  int[] EnumSwitchMapping0;
            static {
                int[] iArr = new int[SalesType.values().length];
                try {
                    iArr[SalesType.DISPATCH.ordinal()] = 1;
                } catch (NoSuchFieldError _) {
                }
                try {
                    iArr[SalesType.WHTRANSFER.ordinal()] = 2;
                } catch (NoSuchFieldError _) {
                }
                EnumSwitchMapping0 = iArr;
            }
        }
        private Companion() {
        }
        public int fromSalesType(SalesType salesType) {
            Intrinsics.checkNotNullParameter(salesType, "salesType");
            int i2 = WhenMappings.EnumSwitchMapping0[salesType.ordinal()];
            if (i2 == 1) {
                return NetsisFicheTypeNo.DISPATCH.getMValue();
            }
            if (i2 == 2) {
                return NetsisFicheTypeNo.WHTRANSFER.getMValue();
            }
            return NetsisFicheTypeNo.INVOICE.getMValue();
        }
    }
}
