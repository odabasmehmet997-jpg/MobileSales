package com.proje.mobilesales.features.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class UserCountResult extends Enum<UserCountResult> {
    private static final EnumEntries ENTRIES;
    private static final UserCountResult[] VALUES;
    public static final UserCountResult CouldNotReadWorUserCount = new UserCountResult("CouldNotReadWorUserCount", 0);
    public static final UserCountResult CouldNotReadLicenseUserCount = new UserCountResult("CouldNotReadLicenseUserCount", 1);
    public static final UserCountResult WorUserCountExceedsLicenseUserCount = new UserCountResult("WorUserCountExceedsLicenseUserCount", 2);
    public static final UserCountResult UserCountInLimits = new UserCountResult("UserCountInLimits", 3);
    public static final UserCountResult UnknownError = new UserCountResult("UnknownError", 4);

    public static UserCountResult[] values() {
        return new UserCountResult[]{UserCountResult.CouldNotReadWorUserCount,
                UserCountResult.CouldNotReadLicenseUserCount, UserCountResult.WorUserCountExceedsLicenseUserCount,
                UserCountResult.UserCountInLimits, UserCountResult.UnknownError};
    }

    public static EnumEntries<UserCountResult> getEntries() {
        return UserCountResult.ENTRIES;
    }

    public static UserCountResult valueOf(final String str) {
        return valueOf(UserCountResult.class, str);
    }

    private UserCountResult(final String str, final int i) {
        super(str,i);
    }

    static {
        final UserCountResult[] values = UserCountResult.values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}