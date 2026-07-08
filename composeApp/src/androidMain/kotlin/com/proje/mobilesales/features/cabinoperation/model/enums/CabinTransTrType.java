package com.proje.mobilesales.features.cabinoperation.model.enums;

import com.proje.mobilesales.core.enums.SalesType;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CabinTransTrType extends Enum<CabinTransTrType> {
    private static final  EnumEntries ENTRIES;
    private static final  CabinTransTrType[] VALUES;
    public static final Companion Companion;
    private final int mValue;
    private final SalesType salesType;
    public static final CabinTransTrType INVOICE = new CabinTransTrType("INVOICE", 0, 1, SalesType.INVOICE);
    public static final CabinTransTrType DISPATCH = new CabinTransTrType("DISPATCH", 1, 2, SalesType.DISPATCH);
    public static final CabinTransTrType RETURNINVOICE = new CabinTransTrType("RETURNINVOICE", 2, 3, SalesType.RETURN_INVOICE);
    public static final CabinTransTrType RETURNDISPATCH = new CabinTransTrType("RETURNDISPATCH", 3, 4, SalesType.RETURN_DISPATCH);
    public static final CabinTransTrType ONETOONECHANGE = new CabinTransTrType("ONETOONECHANGE", 4, 5, SalesType.ONE_TO_ONE_CHANGE);
    public static final CabinTransTrType RETAILINVOICE = new CabinTransTrType("RETAILINVOICE", 5, 6, SalesType.RETAIL_INVOICE);
    public static final CabinTransTrType RETAILRETURNINVOICE = new CabinTransTrType("RETAILRETURNINVOICE", 6, 7, SalesType.RETAIL_RETURN_INVOICE);

    private static CabinTransTrType[] values() {
        return new CabinTransTrType[]{INVOICE, DISPATCH, RETURNINVOICE, RETURNDISPATCH, ONETOONECHANGE, RETAILINVOICE, RETAILRETURNINVOICE};
    }

    public static EnumEntries<CabinTransTrType> getEntries() {
        return ENTRIES;
    }

    public static CabinTransTrType valueOf(String str) {
        return Enum.valueOf(CabinTransTrType.class, str);
    }

    public static CabinTransTrType[] values() {
        return VALUES.clone();
    }

    private CabinTransTrType(String str, int i2, int i3, SalesType salesType) {
        super(str,i2);
        this.mValue = i3;
        this.salesType = salesType;
    }

    static {
        CabinTransTrType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmValue() {
        return this.mValue;
    }

    public static final class Companion {

        public  class WhenMappings {
            public static final  int[] EnumSwitchMapping0;

            static {
                int[] iArr = new int[SalesType.values().length];
                try {
                    iArr[SalesType.INVOICE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[SalesType.DISPATCH.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[SalesType.RETURN_INVOICE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[SalesType.RETURN_DISPATCH.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[SalesType.ONE_TO_ONE_CHANGE.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[SalesType.RETAIL_INVOICE.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[SalesType.RETAIL_RETURN_INVOICE.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                EnumSwitchMapping0 = iArr;
            }
        }

        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public int getCabinTransTrTypeValueFromSalesType(SalesType salesType) {
            CabinTransTrType cabinTransTrType;
            switch (salesType == null ? -1 : WhenMappings.EnumSwitchMapping0[salesType.ordinal()]) {
                case 1:
                    cabinTransTrType = CabinTransTrType.INVOICE;
                    break;
                case 2:
                    cabinTransTrType = CabinTransTrType.DISPATCH;
                    break;
                case 3:
                    cabinTransTrType = CabinTransTrType.RETURNINVOICE;
                    break;
                case 4:
                    cabinTransTrType = CabinTransTrType.RETURNDISPATCH;
                    break;
                case 5:
                    cabinTransTrType = CabinTransTrType.ONETOONECHANGE;
                    break;
                case 6:
                    cabinTransTrType = CabinTransTrType.RETAILINVOICE;
                    break;
                case 7:
                    cabinTransTrType = CabinTransTrType.RETAILRETURNINVOICE;
                    break;
                default:
                    cabinTransTrType = CabinTransTrType.INVOICE;
                    break;
            }
            return cabinTransTrType.getmValue();
        }
    }
}
