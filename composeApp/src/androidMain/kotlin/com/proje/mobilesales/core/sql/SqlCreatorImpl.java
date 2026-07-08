package com.proje.mobilesales.core.sql;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public abstract class SqlCreatorImpl<T> implements ISqlCreator<T> {
    private static final String TAG = "SqlCreatorImpl";
    public String getTableName(T t) {
        return getTableName(t.getClass());
    }
    public String getTableName(Class<?> cls) {
        if (!cls.isAnnotationPresent(Tables.class)) {
            return "";
        }
        return cls.getAnnotation(Tables.class).name();
    }
    public String crateTableFromClass(T t, int i2) {
        return crateTableFromClass(t.getClass(), i2);
    }
    public String crateTableFromClass(Class<?> cls, int i2) {
        Column column;
        ColumnProperty columnProperty;
        String tableName = getTableName(cls);
        if (tableName == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder(SqlLiteVariable._CREATE_TABLE_IF_NOT_EXISTS);
            sb.append(tableName);
            sb.append(SqlLiteVariable._OPEN_BRACKET);
            boolean z = true;
            do {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class) && (columnProperty = getColumnProperty((column = field.getAnnotation(Column.class)))) != null && columnProperty.alterVersion() <= i2) {
                        if (!z) {
                            sb.append(SqlLiteVariable._COMMA_SEP);
                        }
                        sb.append(column.name());
                        sb.append(" ");
                        sb.append(columnProperty.type().getType());
                        if (columnProperty.isNotNull()) {
                            sb.append(SqlLiteVariable._NOT_NULL);
                        }
                        if (columnProperty.isPrimaryKey()) {
                            sb.append(SqlLiteVariable._PRIMARY_KEY);
                        }
                        if (columnProperty.isAutoIncrement()) {
                            sb.append(SqlLiteVariable._AUTOINCREMENT);
                        }
                        if (columnProperty.isUnique()) {
                            sb.append(SqlLiteVariable._UNIQUE);
                        }
                        if (!columnProperty.defaultValue().isEmpty()) {
                            sb.append(SqlLiteVariable._DEFAULT);
                            sb.append(columnProperty.defaultValue());
                            if (!columnProperty.isNotNull()) {
                                sb.append(SqlLiteVariable._NOT_NULL);
                            }
                        }
                        z = false;
                    }
                }
                cls = cls.getSuperclass();
            } while (cls != null);
            sb.append(SqlLiteVariable._CLOSE_BRACKET);
            sb.append(SqlLiteVariable._SEP);
            return sb.toString();
        } catch (Exception e2) {
            Log.e(TAG, "crateTableFromClass: ", e2);
            return "";
        }
    }
    public List<String> alterTableFromClass(T t, int i2, int i3) {
        return alterTableFromClass(t.getClass(), i2, i3);
    }
    public List<String> alterTableFromClass(Class<?> cls, int i2, int i3) {
        Column column;
        ColumnProperty columnProperty;
        ArrayList arrayList = new ArrayList();
        String tableName = getTableName(cls);
        if (tableName == null) {
            return arrayList;
        }
        do {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class) && (columnProperty = getColumnProperty((column = field.getAnnotation(Column.class)))) != null && columnProperty.alterVersion() > i2) {
                        StringBuilder sb = new StringBuilder(SqlLiteVariable._ALTER_TABLE);
                        sb.append(tableName);
                        sb.append(" ");
                        sb.append(SqlLiteVariable._ADD);
                        sb.append(column.name());
                        sb.append(" ");
                        sb.append(columnProperty.type().getType());
                        sb.append(" ");
                        if (!columnProperty.defaultValue().isEmpty()) {
                            sb.append(SqlLiteVariable._DEFAULT);
                            sb.append(columnProperty.defaultValue());
                            if (!columnProperty.isNotNull()) {
                                sb.append(SqlLiteVariable._NOT_NULL);
                            }
                            sb.append(" ");
                        }
                        sb.append(SqlLiteVariable._SEP);
                        try {
                            arrayList.add(sb.toString());
                        } catch (Exception e2) {
                            Log.e(TAG, "alterTableFromClass: ", e2);
                        }
                    }
                }
                cls = cls.getSuperclass();
            } catch (Exception e3) {
                Log.e(TAG, "alterTableFromClass: ", e3);
            }
        } while (cls != null);
        return arrayList;
    }
    public String deleteTableFromClass(T t) {
        return deleteTableFromClass(t.getClass());
    }
    public String deleteTableFromClass(Class<?> cls) {
        try {
            String tableName = getTableName(cls);
            if (tableName == null) {
                return "";
            }
            return SqlLiteVariable._DROP_TABLE_IF_EXISTS + tableName;
        } catch (Exception e2) {
            Log.e(TAG, "deleteTableFromClass: ", e2);
            return "";
        }
    }
    public boolean useTableUpdate(T t) {
        return useTableUpdate(t.getClass());
    }
    public String getTriggerSqlDelete(@StringRes int i2, Class<?> cls, Class<?> cls2, @StringRes int i3, @StringRes int i4) {
        String str = "";
        try {
            str = ((((((SqlLiteVariable._CREATE_TRIGGER + ContextUtils.getStringResource(i2) + SqlLiteVariable._NEW_LINE) + SqlLiteVariable._AFTER_DELETE_ON + getTableName(cls) + SqlLiteVariable._NEW_LINE) + " FOR EACH ROW \n") + " BEGIN \n") + SqlLiteVariable._DELETE + getTableName(cls2) + SqlLiteVariable._WHERE + ContextUtils.getStringResource(i3) + SqlLiteVariable._EQUALS + SqlLiteVariable._OLD + ContextUtils.getStringResource(i4)) + SqlLiteVariable._SEP) + SqlLiteVariable._END;
            return str + SqlLiteVariable._SEP;
        } catch (Exception e2) {
            Log.e(TAG, "getTriggerSqlDelete: ", e2);
            return str;
        }
    }
    public String getTriggerSqlDelete(int i2, Class<?> cls, Class<?> cls2, int i3, int i4, int i5, String str) {
        String str2 = "";
        try {
            str2 = (((((((SqlLiteVariable._CREATE_TRIGGER + ContextUtils.getStringResource(i2) + SqlLiteVariable._NEW_LINE) + SqlLiteVariable._AFTER_DELETE_ON + getTableName(cls) + SqlLiteVariable._NEW_LINE) + " FOR EACH ROW \n") + " BEGIN \n") + SqlLiteVariable._DELETE + getTableName(cls2) + SqlLiteVariable._WHERE + ContextUtils.getStringResource(i3) + SqlLiteVariable._EQUALS + SqlLiteVariable._OLD + ContextUtils.getStringResource(i4)) + SqlLiteVariable._AND + ContextUtils.getStringResource(i5) + SqlLiteVariable._EQUALS + str) + SqlLiteVariable._SEP) + SqlLiteVariable._END;
            return str2 + SqlLiteVariable._SEP;
        } catch (Exception e2) {
            Log.e(TAG, "getTriggerSqlDelete: ", e2);
            return str2;
        }
    }
    public String getCreateIndex(T t) {
        return getCreateIndex(t.getClass());
    }
    public String getCreateIndex(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        try {
            if (cls.isAnnotationPresent(TableIndex.class)) {
                TableIndex tableIndex = cls.getAnnotation(TableIndex.class);
                sb.append(SqlLiteVariable._CREATE);
                if (tableIndex.isUnique()) {
                    sb.append(SqlLiteVariable._UNIQUE);
                }
                sb.append(SqlLiteVariable._INDEX);
                sb.append(SqlLiteVariable._IF_NOT_EXISTS);
                sb.append(" ");
                sb.append(tableIndex.name());
                sb.append("   ON " + getTableName(cls) + SqlLiteVariable._OPEN_BRACKET);
                do {
                    try {
                        for (Field field : cls.getDeclaredFields()) {
                            if (field.getAnnotation(ColumnIndex.class) != null) {
                                Column column = field.getAnnotation(Column.class);
                                if (getColumnProperty(column) != null) {
                                    sb.append(column.name());
                                    sb.append(" ");
                                    sb.append(SqlLiteVariable._COMMA_SEP);
                                }
                            }
                        }
                        cls = cls.getSuperclass();
                    } catch (Exception e2) {
                        Log.e(TAG, "getCreateIndex: ", e2);
                    }
                } while (cls != null);
            }
            if (!TextUtils.isEmpty(sb.toString())) {
                sb.delete(sb.length() - 3, sb.length() - 1);
            }
            sb.append(" )  ; ");
        } catch (Exception e3) {
            Log.e(TAG, "getCreateIndex: ", e3);
        }
        return sb.toString();
    }
    public HashMap<String, String> getTableColumnName(Class<T> cls) {
        HashMap<String, String> hashMap = new HashMap<>();
        do {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        hashMap.put(column.name(), field.getName());
                    }
                }
                cls = (Class<T>) cls.getSuperclass();
            } catch (Exception e2) {
                Log.e(TAG, "getTableColumnName: ", e2);
            }
        } while (cls != null);
        return hashMap;
    }
    public String getTriggerSqlDelete(@StringRes int i2, T t, T t2, @StringRes int i3, @StringRes int i4) {
        return getTriggerSqlDelete(i2, t.getClass(), t2.getClass(), i3, i4);
    }
    public String insertTableFromSql(T t, int i2) throws IllegalAccessException {
        return insertTableFromSql(t.getClass(), i2);
    }
    public String insertTableFromSql(Class<?> cls, int i2) throws IllegalAccessException {
        Column column;
        ColumnProperty columnProperty;
        String str = SqlLiteVariable._INSERT + ISqlBriteDatabase.conflictSql(i2) + SqlLiteVariable._INTO + getTableName(cls) + " " + SqlLiteVariable._OPEN_BRACKET;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        do {
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class) && (columnProperty = getColumnProperty((column = field.getAnnotation(Column.class)))) != null && !columnProperty.isAutoIncrement()) {
                    sb2.append(column.name());
                    sb2.append(SqlLiteVariable._COMMA_SEP);
                    sb.append(" ?  , ");
                }
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        if (sb2.toString().endsWith(SqlLiteVariable._COMMA_SEP)) {
            StringBuilder sb3 = new StringBuilder(sb2.substring(0, sb2.length() - 3));
            sb = new StringBuilder(sb.substring(0, sb.length() - 3));
            sb2 = sb3;
        }
        return (((str + sb2) + " )  ") + " VALUES ( ") + sb + SqlLiteVariable._CLOSE_BRACKET + SqlLiteVariable._SEP;
    }
    public String updateTableFromClassTigerListString(T t, String str, int i2) throws IllegalAccessException {
        return updateTableFromClassTigerListString(t.getClass(), str, i2);
    }
    public String updateTableFromClassTigerListString(T t) throws IllegalAccessException {
        return updateTableFromClassTigerListString(t.getClass());
    }
    public String updateTableFromClassTigerListString(Class<?> cls, String str, int i2) throws IllegalAccessException {
        Column column;
        ColumnProperty columnProperty;
        StringBuilder sb = new StringBuilder(SqlLiteVariable._UPDATE + getTableName(cls) + " " + SqlLiteVariable._SET + " ");
        do {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class) && (columnProperty = getColumnProperty((column = field.getAnnotation(Column.class)))) != null && !columnProperty.isAutoIncrement() && !column.name().equals(str)) {
                        sb.append(column.name());
                        sb.append(SqlLiteVariable._EQUALS);
                        sb.append(" ?  , ");
                    }
                }
            } catch (SecurityException e2) {
                Log.e(TAG, "updateTableFromClassTigerListString: ", e2);
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        if (sb.toString().endsWith(SqlLiteVariable._COMMA_SEP)) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 3));
        }
        sb.append("  WHERE ");
        sb.append(str);
        sb.append(SqlLiteVariable._EQUALS);
        sb.append(i2);
        return sb.toString();
    }
    public String updateTableFromClassTigerListString(Class<?> cls) throws IllegalAccessException {
        ColumnProperty columnProperty;
        StringBuilder sb = new StringBuilder(SqlLiteVariable._UPDATE + getTableName(cls) + " " + SqlLiteVariable._SET + " ");
        StringBuilder sb2 = new StringBuilder();
        boolean z = true;
        do {
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column != null && field.isAnnotationPresent(Column.class) && (columnProperty = getColumnProperty(field.getAnnotation(Column.class))) != null) {
                    if (!columnProperty.isAutoIncrement() && !columnProperty.useUpdate()) {
                        sb.append(column.name());
                        sb.append(SqlLiteVariable._EQUALS);
                        sb.append(" ?  , ");
                    } else if (useLogicalRefForUpdate() || columnProperty.useWhereStatement()) {
                        if (z) {
                            sb2.append(SqlLiteVariable._WHERE);
                            sb2.append(column.name());
                            sb2.append(SqlLiteVariable._EQUALS);
                            sb2.append(SqlLiteVariable._QUEST);
                            z = !z;
                        } else {
                            sb2.append(SqlLiteVariable._AND);
                            sb2.append(column.name());
                            sb2.append(SqlLiteVariable._EQUALS);
                            sb2.append(SqlLiteVariable._QUEST);
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        if (sb.toString().endsWith(SqlLiteVariable._COMMA_SEP)) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 3));
        }
        sb.append(sb2);
        return sb.toString();
    }
}
