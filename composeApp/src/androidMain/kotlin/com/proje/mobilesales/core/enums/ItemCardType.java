package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class ItemCardType {
    private static final  EnumEntries ENTRIES;
    private static final  ItemCardType[] VALUES;
    private final int value;
    public static final ItemCardType HEPSI = new ItemCardType("HEPSI", 0, -1);
    public static final ItemCardType TICARIMAL = new ItemCardType("TICARIMAL", 1, 1);
    public static final ItemCardType KARMAKOLI = new ItemCardType("KARMAKOLI", 2, 2);
    public static final ItemCardType DEPOZITOLUMAL = new ItemCardType("DEPOZITOLUMAL", 3, 3);
    public static final ItemCardType SABITKIYMET = new ItemCardType("SABITKIYMET", 4, 4);
    public static final ItemCardType HAMMADDE = new ItemCardType("HAMMADDE", 5, 10);
    public static final ItemCardType YARIMAMUL = new ItemCardType("YARIMAMUL", 6, 11);
    public static final ItemCardType MAMUL = new ItemCardType("MAMUL", 7, 12);
    public static final ItemCardType TUKETIMMALI = new ItemCardType("TUKETIMMALI", 8, 13);
    public static final ItemCardType MALZEMESINIFI_GENEL = new ItemCardType("MALZEMESINIFI_GENEL", 9, 20);
    public static final ItemCardType MALZEMESINIFI_TABLOLU = new ItemCardType("MALZEMESINIFI_TABLOLU", 10, 21);
    public static final ItemCardType MALZEMESINIFI_FIRMAACILIRKENEKLENENDEFAULTSINIF = new ItemCardType("MALZEMESINIFI_FIRMAACILIRKENEKLENENDEFAULTSINIF", 11, 22);

    private static ItemCardType[] values() {
        return new ItemCardType[]{HEPSI, TICARIMAL, KARMAKOLI, DEPOZITOLUMAL, SABITKIYMET, HAMMADDE, YARIMAMUL, MAMUL, TUKETIMMALI, MALZEMESINIFI_GENEL, MALZEMESINIFI_TABLOLU, MALZEMESINIFI_FIRMAACILIRKENEKLENENDEFAULTSINIF};
    }

    public static EnumEntries<ItemCardType> getEntries() {
        return ENTRIES;
    }

    public static ItemCardType valueOf(String str) {
        return Enum.valueOf(ItemCardType.class, str);
    }

    public static ItemCardType[] values() {
        return VALUES.clone();
    }

    private ItemCardType(String str, int i2, int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        ItemCardType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
