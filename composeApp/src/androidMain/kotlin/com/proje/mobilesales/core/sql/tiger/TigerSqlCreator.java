package com.proje.mobilesales.core.sql.tiger;

import android.util.Log;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.sql.SqlCreatorImpl;
public class TigerSqlCreator<T> extends SqlCreatorImpl<T> {
    private static final String TAG = "TigerSqlCreator";

    public boolean useLogicalRefForUpdate() {
        return true;
    }
    public ColumnProperty getColumnProperty(Column column) {
        if (column.isAllSame() || column.shared().useProperty()) {
            return column.shared();
        }
        return null;
    }

    public boolean useTableUpdate(Class<?> cls) {
        try {
            if (cls.isAnnotationPresent(Tables.class)) {
                return cls.getAnnotation(Tables.class).useUpdate();
            }
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "useTableUpdate: ", e2);
            return false;
        }
    }
}
