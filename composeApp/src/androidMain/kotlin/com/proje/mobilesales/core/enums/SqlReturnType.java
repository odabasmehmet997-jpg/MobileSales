package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.sql.SqlLiteVariable;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;


public final class SqlReturnType {
    private static final  EnumEntries ENTRIES;
    private static final  SqlReturnType[] VALUES;
    public static final SqlReturnType INT = new SqlReturnType("INT", 0);
    public static final SqlReturnType STRING = new SqlReturnType("STRING", 1);
    public static final SqlReturnType BOOL = new SqlReturnType("BOOL", 2);
    public static final SqlReturnType DOUBLE = new SqlReturnType(SqlLiteVariable._DOUBLE, 3);
    public static final SqlReturnType FLOAT = new SqlReturnType("FLOAT", 4);
    public static final SqlReturnType DATE = new SqlReturnType("DATE", 5);
    public static final SqlReturnType BLOB = new SqlReturnType(SqlLiteVariable._BLOB, 6);

    private static SqlReturnType[] values() {
        return new SqlReturnType[]{INT, STRING, BOOL, DOUBLE, FLOAT, DATE, BLOB};
    }

    public static EnumEntries<SqlReturnType> getEntries() {
        return ENTRIES;
    }

    public static SqlReturnType valueOf(String str) {
        return Enum.valueOf(SqlReturnType.class, str);
    }

    public static SqlReturnType[] values() {
        return VALUES.clone();
    }

    private SqlReturnType(String str, int i2) {
    }

    static {
        SqlReturnType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
