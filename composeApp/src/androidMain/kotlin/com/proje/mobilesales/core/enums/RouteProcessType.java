package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RouteProcessType {
    private static final  EnumEntries ENTRIES;
    private static final  RouteProcessType[] VALUES;
    public static final Companion Companion;
    private final Class<?> mDatabaseClass;
    private final int mValue;
    public static final RouteProcessType INVOICE = new RouteProcessType("INVOICE", 0, 0, Invoice.class);
    public static final RouteProcessType ORDER = new RouteProcessType(DailyInfo.ORDER, 1, 1, Order.class);
    public static final RouteProcessType DISPATCH = new RouteProcessType("DISPATCH", 2, 2, Invoice.class);
    public static final RouteProcessType CASH = new RouteProcessType(DailyInfo.CASH, 3, 3, CashCredit.class);
    public static final RouteProcessType CREDITCARD = new RouteProcessType("CREDITCARD", 4, 4, CashCredit.class);
    public static final RouteProcessType DEED = new RouteProcessType("DEED", 5, 5, Chequedeed.class);
    public static final RouteProcessType CHEQUE = new RouteProcessType("CHEQUE", 6, 6, Chequedeed.class);
    public static final RouteProcessType CASE = new RouteProcessType(DailyInfo.CASE, 7, 7, CaseCash.class);
    public static final RouteProcessType PENETRATION = new RouteProcessType("PENETRATION", 8, 8, UserPenetration.class);
    public static final RouteProcessType VISIT = new RouteProcessType(DailyInfo.VISIT, 9, 9, VisitInfo.class);
    public static final RouteProcessType RETURNINVOICE = new RouteProcessType("RETURNINVOICE", 10, 10, Invoice.class);
    public static final RouteProcessType RETURNDISPATCH = new RouteProcessType("RETURNDISPATCH", 11, 11, Invoice.class);
    public static final RouteProcessType ONETOONECHANGE = new RouteProcessType("ONETOONECHANGE", 12, 12, Invoice.class);
    public static final RouteProcessType RETAILINVOICE = new RouteProcessType("RETAILINVOICE", 13, 13, Invoice.class);
    public static final RouteProcessType RETAILRETURNINVOICE = new RouteProcessType("RETAILRETURNINVOICE", 14, 14, Invoice.class);

    private static RouteProcessType[] values() {
        return new RouteProcessType[]{INVOICE, ORDER, DISPATCH, CASH, CREDITCARD, DEED, CHEQUE, CASE, PENETRATION, VISIT, RETURNINVOICE, RETURNDISPATCH, ONETOONECHANGE, RETAILINVOICE, RETAILRETURNINVOICE};
    }

    public static EnumEntries<RouteProcessType> getEntries() {
        return ENTRIES;
    }

    public static RouteProcessType getRouteProcessType(FicheType ficheType, SalesType salesType) {
        return Companion.getRouteProcessType(ficheType, salesType);
    }

    public static RouteProcessType getRouteProcessType(TransferOperationName transferOperationName) {
        return Companion.getRouteProcessType(transferOperationName);
    }

    public static RouteProcessType valueOf(String str) {
        return Enum.valueOf(RouteProcessType.class, str);
    }

    public static RouteProcessType[] values() {
        return VALUES.clone();
    }

    private RouteProcessType(String str, int i2, int i3, Class cls) {
        this.mValue = i3;
        this.mDatabaseClass = cls;
    }

    static {
        RouteProcessType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmValue() {
        return this.mValue;
    }

    public Class<?> getmDatabaseClass() {
        return this.mDatabaseClass;
    }

    /* compiled from: RouteProcessType.kt */
    public static final class Companion {

        /* compiled from: RouteProcessType.kt */
        public  class WhenMappings {
            public static final  int[] EnumSwitchMapping0;
            public static final  int[] EnumSwitchMapping1;

            static {
                int[] iArr = new int[FicheType.values().length];
                try {
                    iArr[FicheType.ONE_TO_ONE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[FicheType.INVOICE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[FicheType.DISPATCH.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[FicheType.ORDER.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[FicheType.CASH.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[FicheType.CREDIT_CART.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[FicheType.CASE_CASH.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[FicheType.CHEQUE.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[FicheType.DEED.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[FicheType.VISIT.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[FicheType.PENETRATION.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[FicheType.RETAILINVOICE.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                EnumSwitchMapping0 = iArr;
                int[] iArr2 = new int[TransferOperationName.values().length];
                try {
                    iArr2[TransferOperationName.INVOICE.ordinal()] = 1;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr2[TransferOperationName.RETURN_INVOICE.ordinal()] = 2;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr2[TransferOperationName.DISPATCH.ordinal()] = 3;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr2[TransferOperationName.RETURN_DISPATCH.ordinal()] = 4;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr2[TransferOperationName.ORDER.ordinal()] = 5;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr2[TransferOperationName.ONE_TO_ONE_CHANGE.ordinal()] = 6;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr2[TransferOperationName.CASH.ordinal()] = 7;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr2[TransferOperationName.CREDIT_CARD.ordinal()] = 8;
                } catch (NoSuchFieldError unused20) {
                }
                try {
                    iArr2[TransferOperationName.CASE_CASH.ordinal()] = 9;
                } catch (NoSuchFieldError unused21) {
                }
                try {
                    iArr2[TransferOperationName.DEED.ordinal()] = 10;
                } catch (NoSuchFieldError unused22) {
                }
                try {
                    iArr2[TransferOperationName.CHEQUE.ordinal()] = 11;
                } catch (NoSuchFieldError unused23) {
                }
                try {
                    iArr2[TransferOperationName.VISIT.ordinal()] = 12;
                } catch (NoSuchFieldError unused24) {
                }
                try {
                    iArr2[TransferOperationName.PENETRATION.ordinal()] = 13;
                } catch (NoSuchFieldError unused25) {
                }
                try {
                    iArr2[TransferOperationName.RETAIL_INVOICE.ordinal()] = 14;
                } catch (NoSuchFieldError unused26) {
                }
                try {
                    iArr2[TransferOperationName.RETAIL_RETURN_INVOICE.ordinal()] = 15;
                } catch (NoSuchFieldError unused27) {
                }
                EnumSwitchMapping1 = iArr2;
            }
        }

        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public RouteProcessType getRouteProcessType(FicheType ficheType, SalesType salesType) {
            Intrinsics.checkNotNullParameter(ficheType, "ficheType");
            Intrinsics.checkNotNullParameter(salesType, "salesType");
            RouteProcessType routeProcessType = RouteProcessType.INVOICE;
            switch (WhenMappings.EnumSwitchMapping0[ficheType.ordinal()]) {
                case 1:
                    return RouteProcessType.ONETOONECHANGE;
                case 2:
                    return SalesUtils.isSalesTypeOnlyInvoice(salesType) ? routeProcessType : SalesUtils.isSalesTypeOnlyReturnInvoice(salesType) ? RouteProcessType.RETURNINVOICE : SalesUtils.isSalesTypeOneToOne(salesType) ? RouteProcessType.ONETOONECHANGE : routeProcessType;
                case 3:
                    return SalesUtils.isSalesTypeDispatch(salesType) ? RouteProcessType.DISPATCH : SalesUtils.isSalesTypeReturnDispatch(salesType) ? RouteProcessType.RETURNDISPATCH : routeProcessType;
                case 4:
                    return RouteProcessType.ORDER;
                case 5:
                    return RouteProcessType.CASH;
                case 6:
                    return RouteProcessType.CREDITCARD;
                case 7:
                    return RouteProcessType.CASE;
                case 8:
                    return RouteProcessType.CHEQUE;
                case 9:
                    return RouteProcessType.DEED;
                case 10:
                    return RouteProcessType.VISIT;
                case 11:
                    return RouteProcessType.PENETRATION;
                case 12:
                    if (SalesUtils.isSalesTypeOnlyRetailInvoice(salesType)) {
                        return RouteProcessType.RETAILINVOICE;
                    }
                    return SalesUtils.isSalesTypeOnlyRetailReturnInvoice(salesType) ? RouteProcessType.RETAILRETURNINVOICE : routeProcessType;
                default:
                    return routeProcessType;
            }
        }

        public RouteProcessType getRouteProcessType(TransferOperationName transferOperationName) {
            Intrinsics.checkNotNullParameter(transferOperationName, "transferOperationName");
            RouteProcessType routeProcessType = RouteProcessType.INVOICE;
            switch (WhenMappings.EnumSwitchMapping1[transferOperationName.ordinal()]) {
                case 1:
                default:
                    return routeProcessType;
                case 2:
                    return RouteProcessType.RETURNINVOICE;
                case 3:
                    return RouteProcessType.DISPATCH;
                case 4:
                    return RouteProcessType.RETURNDISPATCH;
                case 5:
                    return RouteProcessType.ORDER;
                case 6:
                    return RouteProcessType.ONETOONECHANGE;
                case 7:
                    return RouteProcessType.CASH;
                case 8:
                    return RouteProcessType.CREDITCARD;
                case 9:
                    return RouteProcessType.CASE;
                case 10:
                    return RouteProcessType.DEED;
                case 11:
                    return RouteProcessType.CHEQUE;
                case 12:
                    return RouteProcessType.VISIT;
                case 13:
                    return RouteProcessType.PENETRATION;
                case 14:
                    return RouteProcessType.RETAILINVOICE;
                case 15:
                    return RouteProcessType.RETAILRETURNINVOICE;
            }
        }
    }
}
