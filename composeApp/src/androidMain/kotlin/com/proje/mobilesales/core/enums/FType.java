package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
public final class FType extends Enum<FType> {
    private static final  EnumEntries ENTRIES;
    private static final  FType[] VALUES;
    public static final Companion Companion;
    private int value;
    public static final FType siparis = new FType("siparis", 0, 2);
    public static final FType irsaliye = new FType("irsaliye", 1, 8);
    public static final FType fatura = new FType("fatura", 2, 1);
    public static final FType nakit = new FType("nakit", 3, 3);
    public static final FType kredikarti = new FType("kredikarti", 4, 4);
    public static final FType cek = new FType("cek", 5, 5);
    public static final FType senet = new FType("senet", 6, 6);
    public static final FType sevkirsaliyesi = new FType("sevkirsaliyesi", 7, 7);
    public static final FType nakitkasa = new FType("nakitkasa", 8, 10);
    public static final FType ziyaret = new FType("ziyaret", 9, 9);
    public static final FType Gorev = new FType("Gorev", 10, 11);
    public static final FType Mesaj = new FType("Mesaj", 11, 19);
    public static final FType YeniMusteri = new FType("YeniMusteri", 12, 12);
    public static final FType birebir = new FType("birebir", 13, 13);
    public static final FType calismabilgisi = new FType("calismabilgisi", 14, 14);
    public static final FType anket = new FType("anket", 15, 15);
    public static final FType penetrasyon = new FType("penetrasyon", 16, 16);
    public static final FType talep = new FType("talep", 17, 17);
    public static final FType all = new FType("all", 18, 18);
    public static final FType perakendefatura = new FType("perakendefatura", 19, 20);
    public static final FType cabin = new FType("cabin", 20, 21);
    public static final FType whtransfer = new FType("whtransfer", 21, 22);

    private static FType[] values() {
        return new FType[]{siparis, irsaliye, fatura, nakit, kredikarti, cek, senet, sevkirsaliyesi, nakitkasa, ziyaret, Gorev, Mesaj, YeniMusteri, birebir, calismabilgisi, anket, penetrasyon, talep, all, perakendefatura, cabin, whtransfer};
    }

    public static EnumEntries<FType> getEntries() {
        return ENTRIES;
    }

    public static FType valueOf(String str) {
        return Enum.valueOf(FType.class, str);
    }

    public static FType[] values() {
        return VALUES.clone();
    }

    private FType(String str, int i2, int i3) {
        super(str, i2);
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i2) {
        this.value = i2;
    }

    static {
        FType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    /* compiled from: FType.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public FType fromFType(int i2) {
            for (FType fType : FType.getEntries()) {
                if (fType.getValue() == i2) {
                    return fType;
                }
            }
            return FType.all;
        }
    }
}
