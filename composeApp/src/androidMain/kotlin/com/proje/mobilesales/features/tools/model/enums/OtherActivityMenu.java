package com.proje.mobilesales.features.tools.model.enums;

import com.proje.mobilesales.R;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.tools.view.activity.AverageCalcActivity;
import com.proje.mobilesales.features.tools.view.activity.StartInfoEnterActivity;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class OtherActivityMenu extends Enum<OtherActivityMenu> {
    private static final EnumEntries ENTRIES;
    private static final OtherActivityMenu[] VALUES;
    private Class<?> destActivity;
    private int stringMenuId;
    public static final OtherActivityMenu GPS_KONUM_BILGILERI = new OtherActivityMenu("GPS_KONUM_BILGILERI", 0, R.string.activity_title_other_gps_location, GpsInfoActivity.class);
    public static final OtherActivityMenu START_INFO = new OtherActivityMenu("START_INFO", 1, R.string.activity_title_other_start_info, StartInfoEnterActivity.class);
    public static final OtherActivityMenu AVERAGE_CALC = new OtherActivityMenu("AVERAGE_CALC", 2, R.string.activity_title_other_average_calc, AverageCalcActivity.class);
    public static final OtherActivityMenu DB_EXPORT = new OtherActivityMenu("DB_EXPORT", 3, R.string.str_export_database, null);
    private static OtherActivityMenu[] values() {
        return new OtherActivityMenu[]{GPS_KONUM_BILGILERI, START_INFO, AVERAGE_CALC, DB_EXPORT};
    }
    public static EnumEntries<OtherActivityMenu> getEntries() {
        return ENTRIES;
    }
    public static OtherActivityMenu valueOf(String str) {
        return Enum.valueOf(OtherActivityMenu.class, str);
    }
    private OtherActivityMenu(String gpsKonumBilgileri, int stringMenuId, int activity_title_other_gps_location, Class<?> destActivity) {
        super(gpsKonumBilgileri,activity_title_other_gps_location);
        this.stringMenuId = stringMenuId;
        this.destActivity = destActivity;
    }
    public int getStringMenuId() {
        return this.stringMenuId;
    }
    public void setStringMenuId(int stringMenuId) {
        this.stringMenuId = stringMenuId;
    }
    public Class<?> getDestActivity() {
        return this.destActivity;
    }
    public void setDestActivity(Class<?> destActivity) {
        this.destActivity = destActivity;
    }
    static {
        OtherActivityMenu[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
