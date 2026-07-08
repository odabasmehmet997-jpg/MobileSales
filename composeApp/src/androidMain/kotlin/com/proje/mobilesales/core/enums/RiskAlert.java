package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class RiskAlert extends Enum<RiskAlert> {
    private static final  EnumEntries ENTRIES;
    private static final  RiskAlert[] VALUES;
    public static final Companion Companion;
    private int value;
    public static final RiskAlert UNDEFINED = new RiskAlert("UNDEFINED", 0, -1);
    public static final RiskAlert CONTINUE = new RiskAlert("CONTINUE", 1, 0);
    public static final RiskAlert NOTIFY = new RiskAlert("NOTIFY", 2, 1);
    public static final RiskAlert ABORT = new RiskAlert("ABORT", 3, 2);

    private static RiskAlert[] values() {
        return new RiskAlert[]{UNDEFINED, CONTINUE, NOTIFY, ABORT};
    }

    public static RiskAlert fromRiskAlert(int i2) {
        return Companion.fromRiskAlert(i2);
    }

    public static EnumEntries<RiskAlert> getEntries() {
        return ENTRIES;
    }

    public static RiskAlert valueOf(String str) {
        return Enum.valueOf(RiskAlert.class, str);
    }

    public static RiskAlert[] values() {
        return VALUES.clone();
    }

    private RiskAlert(String str, int i2, int i3) {
        super(str,i2);
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i2) {
        this.value = i2;
    }

    static {
        RiskAlert[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }
    public int ordinal() {
        return super.ordinal()  ;
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public RiskAlert fromRiskAlert(int i2) {
            for (RiskAlert riskAlert : RiskAlert.getEntries()) {
                if (riskAlert.getValue() == i2) {
                    return riskAlert;
                }
            }
            return RiskAlert.UNDEFINED;
        }
    }
}
