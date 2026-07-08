package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.BuildConfig;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PrintDestination {
    private static final  EnumEntries ENTRIES;
    private static final  PrintDestination[] VALUES;
    public static final Companion Companion;
    private String value;
    public static final PrintDestination PRINTER = new PrintDestination("PRINTER", 0, "0");
    public static final PrintDestination FILE = new PrintDestination("FILE", 1, BuildConfig.NETSIS_DEMO_PASSWORD);

    private static PrintDestination[] values() {
        return new PrintDestination[]{PRINTER, FILE};
    }

    public static EnumEntries<PrintDestination> getEntries() {
        return ENTRIES;
    }

    public static PrintDestination valueOf(String str) {
        return Enum.valueOf(PrintDestination.class, str);
    }

    public static PrintDestination[] values() {
        return VALUES.clone();
    }

    private PrintDestination(String str, int i2, String str2) {
        this.value = str2;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    static {
        PrintDestination[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    /* compiled from: PrintDestination.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public PrintDestination fromSalesType(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            for (PrintDestination printDestination : PrintDestination.getEntries()) {
                if (Intrinsics.areEqual(printDestination.getValue(), value)) {
                    return printDestination;
                }
            }
            return PrintDestination.PRINTER;
        }
    }
}
