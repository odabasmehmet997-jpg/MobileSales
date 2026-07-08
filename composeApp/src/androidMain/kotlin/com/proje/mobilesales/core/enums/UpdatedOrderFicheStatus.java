package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class UpdatedOrderFicheStatus extends Enum<UpdatedOrderFicheStatus> {
    private static final EnumEntries ENTRIES;
    private static final UpdatedOrderFicheStatus[] VALUES;
    public static final Companion Companion;
    private int mResId;
    private int mValue;
    public static final UpdatedOrderFicheStatus WAITING_FOR_APPROVAL = new UpdatedOrderFicheStatus("WAITING_FOR_APPROVAL", 0, 1, R.string.str_waiting_for_approval);
    public static final UpdatedOrderFicheStatus UNABLE_TO_SHIP = new UpdatedOrderFicheStatus("UNABLE_TO_SHIP", 1, 2, R.string.str_unable_to_ship);
    public static final UpdatedOrderFicheStatus BEING_SHIPPED = new UpdatedOrderFicheStatus("BEING_SHIPPED", 2, 3, R.string.str_being_shipped);
    public static final UpdatedOrderFicheStatus APPROVED = new UpdatedOrderFicheStatus("APPROVED", 3, 4, R.string.str_approved);
    public static final UpdatedOrderFicheStatus SHIPPED = new UpdatedOrderFicheStatus("SHIPPED", 4, 5, R.string.str_shipped);
    public static final UpdatedOrderFicheStatus DELETED = new UpdatedOrderFicheStatus("DELETED", 5, 6, R.string.str_deleted);
    public static final UpdatedOrderFicheStatus UNKNOWN = new UpdatedOrderFicheStatus("UNKNOWN", 6, 7, R.string.str_unknown);

    private static UpdatedOrderFicheStatus[] values() {
        return new UpdatedOrderFicheStatus[]{WAITING_FOR_APPROVAL, UNABLE_TO_SHIP, BEING_SHIPPED, APPROVED, SHIPPED, DELETED, UNKNOWN};
    }

    public static EnumEntries<UpdatedOrderFicheStatus> getEntries() {
        return ENTRIES;
    }

    public static String getOrderStatus(int i2) {
        return Companion.getOrderStatus(i2);
    }

    public static UpdatedOrderFicheStatus valueOf(String str) {
        return Enum.valueOf(UpdatedOrderFicheStatus.class, str);
    }


    private UpdatedOrderFicheStatus(String str, int i2, int i3, int i4) {
        super(str,i2);
        this.mValue = i3;
        this.mResId = i4;
    }

    public int getMResId() {
        return this.mResId;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        UpdatedOrderFicheStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmResId() {
        return this.mResId;
    }

    public String getStatusString() {
        String stringResource = ContextUtils.getStringResource(this.mResId);
        Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
        return stringResource;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getOrderStatus(int i2) {
            for (UpdatedOrderFicheStatus updatedOrderFicheStatus : UpdatedOrderFicheStatus.values()) {
                if (updatedOrderFicheStatus.getMValue() == i2) {
                    String stringResource = ContextUtils.getStringResource(updatedOrderFicheStatus.getmResId());
                    Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
                    return stringResource;
                }
            }
            String stringResource2 = ContextUtils.getStringResource(UpdatedOrderFicheStatus.UNKNOWN.getmResId());
            Intrinsics.checkNotNullExpressionValue(stringResource2, "getStringResource(...)");
            return stringResource2;
        }
    }
}
