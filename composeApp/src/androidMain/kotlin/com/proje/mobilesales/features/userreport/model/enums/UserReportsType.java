package com.proje.mobilesales.features.userreport.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class UserReportsType {
    private static final EnumEntries ENTRIES;
    private static final UserReportsType[] VALUES;
    public static final Companion Companion;
    public static final UserReportsType general = new UserReportsType();
    public static final UserReportsType customer = new UserReportsType();
    public static final UserReportsType item = new UserReportsType();
    public static final UserReportsType dashboard = new UserReportsType();
    public static   UserReportsType[] values() {
        return new UserReportsType[]{general, customer, item, dashboard};
    }
    public static   UserReportsType getEnum(int r1) {
        return Companion.getEnum(r1);
    }
    static {
        VALUES = values();
        ENTRIES = enumEntries();
        Companion = new Companion(null);
    }
    private static EnumEntries enumEntries() {
        final EnumEntries<? extends Enum<? extends Enum<?>>> enums;
        enums = EnumEntriesKt.enumEntries(VALUES);
        return enums;
    }
    public int ordinal() {
        return 0;
    }
    public int getItemId() {
        return 0;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        public Companion() {
        }

        public   UserReportsType getEnum(int r1) {
            if (r1 == 4) {
                return UserReportsType.general;
            }
            if (r1 == 5) {
                return UserReportsType.customer;
            }
            if (r1 == 6) {
                return UserReportsType.item;
            }
            if (r1 != 7) {
                return null;
            }
            return UserReportsType.dashboard;
        }
    }
}
