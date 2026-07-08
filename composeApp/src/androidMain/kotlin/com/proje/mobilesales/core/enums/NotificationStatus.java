package com.proje.mobilesales.core.enums;

import java.util.NoSuchElementException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class NotificationStatus   {
    private static final  EnumEntries ENTRIES;
    private static final  NotificationStatus[] VALUES;
    public static final Companion Companion;
    private final int status;
    public static final NotificationStatus Created = new NotificationStatus("Created", 0, 0);
    public static final NotificationStatus Delivered = new NotificationStatus("Delivered", 1, 1);
    public static final NotificationStatus Read = new NotificationStatus("Read", 2, 2);
    public static final NotificationStatus Deleted = new NotificationStatus("Deleted", 3, 3);

    private static NotificationStatus[] values() {
        return new NotificationStatus[]{Created, Delivered, Read, Deleted};
    }

    public static EnumEntries<NotificationStatus> getEntries() {
        return ENTRIES;
    }

    public static NotificationStatus valueOf(String str) {
        return Enum.valueOf(NotificationStatus.class, str);
    }

    public static NotificationStatus[] values() {
        return VALUES.clone();
    }

    private NotificationStatus(String str, int i2, int i3) {
        super();
        this.status = i3;
    }

    public int getStatus() {
        return this.status;
    }

    static {
        NotificationStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int ordinal() {
        return 0;
    }

    /* compiled from: NotificationStatus.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public NotificationStatus fromValue(int i2) {
            for (NotificationStatus notificationStatus : NotificationStatus.values()) {
                if (notificationStatus.getStatus() == i2) {
                    return notificationStatus;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }
    }
}
