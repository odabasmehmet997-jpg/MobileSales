package com.proje.mobilesales.core.enums;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PrintDesignSection {
    private static final  EnumEntries ENTRIES;
    private static final  PrintDesignSection[] VALUES;
    private final String sectionNr;
    public static final PrintDesignSection HEADER = new PrintDesignSection("HEADER", 0, BuildConfig.NETSIS_DEMO_PASSWORD);
    public static final PrintDesignSection DETAIL = new PrintDesignSection("DETAIL", 1, ExifInterface.GPS_MEASUREMENT_2D);
    public static final PrintDesignSection SUMMARY = new PrintDesignSection("SUMMARY", 2, ExifInterface.GPS_MEASUREMENT_3D);
    public static final PrintDesignSection FOOTER = new PrintDesignSection("FOOTER", 3, "4");

    private static PrintDesignSection[] values() {
        return new PrintDesignSection[]{HEADER, DETAIL, SUMMARY, FOOTER};
    }

    public static EnumEntries<PrintDesignSection> getEntries() {
        return ENTRIES;
    }

    public static PrintDesignSection valueOf(String str) {
        return Enum.valueOf(PrintDesignSection.class, str);
    }

    public static PrintDesignSection[] values() {
        return VALUES.clone();
    }

    private PrintDesignSection(String str, int i2, String str2) {
        this.sectionNr = str2;
    }

    static {
        PrintDesignSection[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public String getSectionNumber() {
        return this.sectionNr;
    }
}
