package com.proje.mobilesales.core.sql;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import java.util.HashMap;
import java.util.List;

public interface ISqlCreator<T> {
    List<String> alterTableFromClass(Class<?> cls, int i2, int i3);

    List<String> alterTableFromClass(T t, int i2, int i3);

    String crateTableFromClass(Class<?> cls, int i2);

    String crateTableFromClass(T t, int i2);

    String deleteTableFromClass(Class<?> cls);

    String deleteTableFromClass(T t);

    ColumnProperty getColumnProperty(Column column);

    String getCreateIndex(Class<?> cls);

    String getCreateIndex(T t); 
    HashMap<String, String> getTableColumnName(Class<T> cls);

    String getTableName(Class<?> cls);

    String getTableName(T t);

    String getTriggerSqlDelete(int i2, Class<?> cls, Class<?> cls2, int i3, int i4);

    String getTriggerSqlDelete(int i2, Class<?> cls, Class<?> cls2, int i3, int i4, int i5, String str);

    String getTriggerSqlDelete(int i2, T t, T t2, int i3, int i4);

    String insertTableFromSql(Class<?> cls, int i2) throws IllegalAccessException;

    String insertTableFromSql(T t, int i2) throws IllegalAccessException;

    String updateTableFromClassTigerListString(Class<?> cls) throws IllegalAccessException;

    String updateTableFromClassTigerListString(Class<?> cls, String str, int i2) throws IllegalAccessException;

    String updateTableFromClassTigerListString(T t) throws IllegalAccessException;

    String updateTableFromClassTigerListString(T t, String str, int i2) throws IllegalAccessException;

    boolean useLogicalRefForUpdate();

    boolean useTableUpdate(Class<?> cls);

    boolean useTableUpdate(T t);
}
