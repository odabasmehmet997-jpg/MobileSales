package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class RiskControlType {
    private static final  EnumEntries ENTRIES;
    private static final  RiskControlType[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final RiskControlType BASED_ON_TOTALS = new RiskControlType("BASED_ON_TOTALS", 0, 0);
    public static final RiskControlType BASED_ON_FICHE_TYPE = new RiskControlType("BASED_ON_FICHE_TYPE", 1, 1);

    private static RiskControlType[] values() {
        return new RiskControlType[]{BASED_ON_TOTALS, BASED_ON_FICHE_TYPE};
    }

    public static EnumEntries<RiskControlType> getEntries() {
        return ENTRIES;
    }

    public static RiskControlType setRiskControlType(int i2) {
        return Companion.setRiskControlType(i2);
    }

    public static RiskControlType valueOf(String str) {
        return Enum.valueOf(RiskControlType.class, str);
    }

    public static RiskControlType[] values() {
        return VALUES.clone();
    }

    private RiskControlType(String str, int i2, int i3) {
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        RiskControlType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    /* compiled from: RiskControlType.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public RiskControlType setRiskControlType(int i2) {
            for (RiskControlType riskControlType : RiskControlType.values()) {
                if (riskControlType.getMValue() == i2) {
                    return riskControlType;
                }
            }
            return RiskControlType.BASED_ON_TOTALS;
        }
    }
}
