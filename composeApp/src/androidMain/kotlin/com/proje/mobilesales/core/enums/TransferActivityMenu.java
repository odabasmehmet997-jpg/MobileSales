package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.R;
import com.proje.mobilesales.features.activity.TransferGetActivity;
import com.proje.mobilesales.features.activity.TransferSendActivity;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class TransferActivityMenu extends Enum<TransferActivityMenu> {
    private static final EnumEntries ENTRIES;
    private static final TransferActivityMenu[] VALUES;
    public Class<?> destActivity;

    public int stringMenuId;
    public static final TransferActivityMenu TRANSFER_GET = new TransferActivityMenu("TRANSFER_GET", 0, R.string.activity_title_transfer_get, TransferGetActivity.class);
    public static final TransferActivityMenu TRANSFER_SEND = new TransferActivityMenu("TRANSFER_SEND", 1, R.string.activity_title_transfer_send, TransferSendActivity.class);
    public static final TransferActivityMenu TRANSFER_DELETE = new TransferActivityMenu("TRANSFER_DELETE", 2, R.string.str_activity_title_transfer_delete, TransferGetActivity.class);

    public static TransferActivityMenu[] values() {
        return new TransferActivityMenu[]{TRANSFER_GET, TRANSFER_SEND, TRANSFER_DELETE};
    }

    public static EnumEntries<TransferActivityMenu> getEntries() {
        return ENTRIES;
    }

    public static TransferActivityMenu valueOf(String str) {
        return Enum.valueOf(TransferActivityMenu.class, str);
    }


    private TransferActivityMenu( String str, int i2, int i3, Class cls) {
        super(str,i2);
        this.stringMenuId = i3;
        this.destActivity = cls;
    }

    static {
        TransferActivityMenu[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

}
