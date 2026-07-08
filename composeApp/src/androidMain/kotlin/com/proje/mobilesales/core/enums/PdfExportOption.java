package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PdfExportOption extends Enum<PdfExportOption> {
    private static final   EnumEntries ENTRIES;
    private static final   PdfExportOption[] VALUES;
    private final int value = 0;
    public static final PdfExportOption Preview = new PdfExportOption();
    public static final PdfExportOption Share = new PdfExportOption();
    public static final PdfExportOption Download = new PdfExportOption();

    public static PdfExportOption[] values() {
        return new PdfExportOption[]{Preview, Share, Download};
    }

    public static EnumEntries<PdfExportOption> getEntries() {
        return ENTRIES;
    }

    public static PdfExportOption valueOf(String str) {
        PdfExportOption pdfExportOption = Enum.valueOf(PdfExportOption.class, str);
        return pdfExportOption;
    }

    public int getValue() {
        return this.value;
    }

    static {
        PdfExportOption[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

}
