package com.proje.mobilesales.core.sql.netsis;

import android.util.Log;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.sql.SqlCreatorImpl;

public class NetsisSqlCreator<T> extends SqlCreatorImpl<T> {
    private static final String TAG = "NetsisSqlCreator";

    public boolean useLogicalRefForUpdate() {
        return false;
    }

    public ColumnProperty getColumnProperty(Column column) {
        if (column.isAllSame()) {
            if (column.shared().useProperty()) {
                return column.shared();
            }
            return null;
        }
        if (column.netsis().useProperty()) {
            return column.netsis();
        }
        return null;
    }

    public boolean useTableUpdate(Class<?> cls) {
        try {
            if (cls.isAnnotationPresent(Tables.class)) {
                return cls.getAnnotation(Tables.class).useNetsisUpdate();
            }
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "useTableUpdate: ", e2);
            return false;
        }
    }
}
