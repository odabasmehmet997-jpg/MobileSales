package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PdfOperation {
    private static final  EnumEntries ENTRIES;
    private static final  PdfOperation[] VALUES;
    private final int value;
    public static final PdfOperation EDocumentPdf = new PdfOperation("EDocumentPdf", 0, 0);
    public static final PdfOperation SalesPdf = new PdfOperation("SalesPdf", 1, 1);
    public static final PdfOperation EDocumentDraftPdf = new PdfOperation("EDocumentDraftPdf", 2, 2);
    public static final PdfOperation EDocumentDraftPdfThreeInc = new PdfOperation("EDocumentDraftPdfThreeInc", 3, 3);

    private static PdfOperation[] values() {
        return new PdfOperation[]{EDocumentPdf, SalesPdf, EDocumentDraftPdf, EDocumentDraftPdfThreeInc};
    }

    public static EnumEntries<PdfOperation> getEntries() {
        return ENTRIES;
    }

    public static PdfOperation valueOf(String str) {
        return Enum.valueOf(PdfOperation.class, str);
    }

    public static PdfOperation[] values() {
        return VALUES.clone();
    }

    private PdfOperation(String str, int i2, int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        PdfOperation[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
