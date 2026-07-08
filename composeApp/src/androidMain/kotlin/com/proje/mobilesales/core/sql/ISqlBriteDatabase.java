package com.proje.mobilesales.core.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import java.util.List;
import java.util.Set;

public interface ISqlBriteDatabase<T> {
    int delete(String str,String str2,String... strArr);
    boolean delete(Class<?> cls, String str);
    boolean delete(Class<?> cls,String str,String... strArr);
    boolean delete(T t, String str);
    boolean delete(T t,String str,String... strArr);
    boolean delete(List<T> list, String str);
    void execute(String str);
    void execute(String str, Object... objArr);
    void executeAndTrigger(String str, String str2);
    void executeAndTrigger(String str, String str2, Object... objArr);
    void executeAndTrigger(Set<String> set, String str);
    void executeAndTrigger(Set<String> set, String str, Object... objArr);
    long executeInsert(String str, ContentValues contentValues);
    long executeInsert(String str, SQLiteStatement sQLiteStatement);
    long executeInsert(Set<String> set, SQLiteStatement sQLiteStatement);
    void executeMultipleStatements(String str);
    int executeUpdateDelete(String str, SQLiteStatement sQLiteStatement);
    int executeUpdateDelete(Set<String> set, SQLiteStatement sQLiteStatement);
    SQLiteDatabase getReadableDatabase();
    ISqlCreator getSqlCreator();
    SQLiteDatabase getWritableDatabase();
    SQLiteDatabase getWriteableDatabase();
    long insert(T t);
    long insert(T t, boolean z);
    long insert(String str, ContentValues contentValues);
    long insert(String str, ContentValues contentValues, int i2);
    boolean insert(List<T> list);
    boolean insert(List<T> list, boolean z);
    boolean insertOrUpdate(List<T> list, boolean z);
    Cursor query(String str, String... strArr);
    void setLoggingEnabled(boolean z);
    int update(Class<?> cls, ContentValues contentValues,String str,String... strArr);
    int update(String str, ContentValues contentValues, int i2,String str2,String... strArr);
    int update(String str, ContentValues contentValues,String str2,String... strArr);
    long update(T t);
    long update(T t, String str, int i2);
    boolean update(List<T> list);
    boolean update(List<T> list, String str, int i2);
    int updateWithT(T t, ContentValues contentValues,String str,String... strArr);
    static String conflictSql(int i2) {
        if (i2 == 0) {
            return " ";
        }
        if (i2 == 1) {
            return SqlLiteVariable._ROLLBACK;
        }
        if (i2 == 2) {
            return SqlLiteVariable._ABORT;
        }
        if (i2 == 3) {
            return SqlLiteVariable._FAIL;
        }
        if (i2 == 4) {
            return SqlLiteVariable._IGNORE;
        }
        if (i2 == 5) {
            return SqlLiteVariable._REPLACE;
        }
        return "UNKNOWN (" + i2 + ')';
    }
}
