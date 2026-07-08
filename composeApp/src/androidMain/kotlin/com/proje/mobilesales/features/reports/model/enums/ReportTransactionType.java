package com.proje.mobilesales.features.reports.model.enums;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ReportTransactionType {
    CASH_COLLECTION ("D", R.string.str_cash_collection),
    DEED_ENTRY (ExifInterface.LONGITUDE_EAST, R.string.str_deed_entry),
    CHEQUE_ENTRY ("G", R.string.str_cheque_entry);

    private static final EnumEntries<ReportTransactionType> ENTRIES;

    static {
        ReportTransactionType[] values = null;
        EnumEntriesKt.enumEntries(values);
        values = new ReportTransactionType[0];
        ReportTransactionType.ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    private final int stringResId;
    private final String type;

    ReportTransactionType(final String str, final int i2) {
        type = str;
        stringResId = i2;
    }

    public static EnumEntries<ReportTransactionType> getEntries() {
        return ReportTransactionType.ENTRIES;
    }

    public final int getStringResId() {
        return stringResId;
    }

    public final String getType() {
        return type;
    }
}
