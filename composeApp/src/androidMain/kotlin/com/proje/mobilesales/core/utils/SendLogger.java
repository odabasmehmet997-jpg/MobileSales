package com.proje.mobilesales.core.utils;

import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ProcessType;
import java.util.UUID;

import static com.proje.mobilesales.core.utils.AppUtils.saveLogToSelectedFolder;

public class SendLogger {
    private static final String _EXT = ".xml";
    private static final String _SPR = "_";
    public void log(DataObjectType dataObjectType, String str, int i2, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = UUID.randomUUID().toString();
            }
            saveLogToSelectedFolder(dataObjectType.tag.trim() + _SPR + dataObjectType.tag + _SPR + str.trim() + _SPR + i2 + _SPR + str2 + _EXT, str3);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "log: ", e2);
        }
    }
    public void log(DataObjectType dataObjectType, String str, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = UUID.randomUUID().toString();
            }
            if (TextUtils.isEmpty(str)) {
                str2 = "UNKNOWN";
            }
            saveLogToSelectedFolder(dataObjectType.tag.trim() + _SPR + str.trim() + _SPR + str2 + _EXT, str3);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "log: ", e2);
        }
    }
    public void log(ProcessType processType, String str, String str2) {
        try {
            saveLogToSelectedFolder(processType.getaClass().getSimpleName().trim() + _SPR + str.trim() + _EXT, str2);
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "log: ", e2);
        }
    }
    public void log(DataObjectType dataObjectType, String str, int i2, String str2) {
        log(dataObjectType, str, i2, "", str2);
    }
}
