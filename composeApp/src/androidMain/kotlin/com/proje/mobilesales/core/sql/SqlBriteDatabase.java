package com.proje.mobilesales.core.sql;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
public abstract class SqlBriteDatabase<T> implements Closeable, ISqlBriteDatabase<T> {
    private static final String TAG = "SqlBriteDatabase";
    private final ConcurrentHashMap<String, List<SqlColumnMap>> ColumnMap = new ConcurrentHashMap<>();
    private final SQLiteOpenHelper helper;
    private final SqlBrite.Logger logger;
    private volatile boolean logging;
    private final ISqlCreator mLogoSqlCreator;
    private SQLException e;
    public @interface ConflictAlgorithm {
    }
    protected SqlBriteDatabase(SQLiteOpenHelper sQLiteOpenHelper, SqlBrite.Logger logger, ISqlCreator iSqlCreator) {
        this.helper = sQLiteOpenHelper;
        this.logger = logger;
        this.mLogoSqlCreator = iSqlCreator;
    }
    public ISqlCreator getSqlCreator() {
        return this.mLogoSqlCreator;
    }
    public void setLoggingEnabled(boolean z) {
        this.logging = z;
    }
    public SQLiteDatabase getReadableDatabase() {
        return this.helper.getReadableDatabase();
    }
    public SQLiteDatabase getWriteableDatabase() {
        return this.helper.getWritableDatabase();
    }
    public SQLiteDatabase getWritableDatabase() {
        return this.helper.getWritableDatabase();
    }
    public void close() {
        this.helper.close();
    }
    public Cursor query(String str, String... strArr) {
        long nanoTime = System.nanoTime();
        Cursor rawQuery = getReadableDatabase().rawQuery(str, strArr);
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
        if (this.logging) {
            log("QUERY (%sms)\n  sql: %s\n  args: %s", Long.valueOf(millis), indentSql(str), Arrays.toString(strArr));
        }
        return rawQuery;
    }
    public long insert(String str, ContentValues contentValues) {
        return insert(str, contentValues, 0);
    }
    public long insert(String str, ContentValues contentValues, int i2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (this.logging) {
            log("INSERT\n  table: %s\n  values: %s\n  conflictAlgorithm: %s", str, contentValues, conflictString(i2));
        }
        long insertWithOnConflict = writableDatabase.insertWithOnConflict(str, null, contentValues, i2);
        if (this.logging) {
            log("INSERT id: %s", Long.valueOf(insertWithOnConflict));
        }
        return insertWithOnConflict;
    }
    public int delete(String str, String str2, String... strArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (this.logging) {
            log("DELETE\n  table: %s\n  whereClause: %s\n  whereArgs: %s", str, str2, Arrays.toString(strArr));
        }
        int delete = writableDatabase.delete(str, str2, strArr);
        if (this.logging) {
            log("DELETE affected %s %s", Integer.valueOf(delete), delete != 1 ? "rows" : "row");
        }
        return delete;
    }
    public int updateWithT(T t, ContentValues contentValues, String str, String... strArr) {
        return update(this.mLogoSqlCreator.getTableName(t), contentValues, 0, str, strArr);
    }
    public int update(Class<?> cls, ContentValues contentValues, String str, String... strArr) {
        return update(this.mLogoSqlCreator.getTableName(cls), contentValues, 0, str, strArr);
    }
    public int update(String str, ContentValues contentValues, String str2, String... strArr) {
        return update(str, contentValues, 0, str2, strArr);
    }
    public int update(String str, ContentValues contentValues, int i2, String str2, String... strArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (this.logging) {
            log("UPDATE\n  table: %s\n  values: %s\n  whereClause: %s\n  whereArgs: %s\n  conflictAlgorithm: %s", str, contentValues, str2, Arrays.toString(strArr), conflictString(i2));
        }
        int updateWithOnConflict = writableDatabase.updateWithOnConflict(str, contentValues, str2, strArr, i2);
        if (this.logging) {
            log("UPDATE affected %s %s", Integer.valueOf(updateWithOnConflict), updateWithOnConflict != 1 ? "rows" : "row");
        }
        return updateWithOnConflict;
    }
    public void execute(String str) {
        if (this.logging) {
            log("EXECUTE\n  sql: %s", str);
        }
        getWritableDatabase().execSQL(str);
    }
    public void execute(String str, Object... objArr) {
        if (this.logging) {
            log("EXECUTE\n  sql: %s\n  args: %s", str, Arrays.toString(objArr));
        }
        getWritableDatabase().execSQL(str, objArr);
    }
    public void executeMultipleStatements(String str) {
        if (this.logging) {
            log("EXECUTE\n  sql: %s", str);
        }
        try {
            for (String str2 : str.split(";")) {
                getWritableDatabase().execSQL(str2);
            }
        } catch (Exception e2) {
            Log.e(TAG, "executeMultipleStatements: ", e2);
        }
    }
    public void executeAndTrigger(String str, String str2) {
        executeAndTrigger(Collections.singleton(str), str2);
    }
    public void executeAndTrigger(Set<String> set, String str) {
        execute(str);
    }
    public void executeAndTrigger(String str, String str2, Object... objArr) {
        executeAndTrigger(Collections.singleton(str), str2, objArr);
    }
    public void executeAndTrigger(Set<String> set, String str, Object... objArr) {
        execute(str, objArr);
    }
    public int executeUpdateDelete(String str, SQLiteStatement sQLiteStatement) {
        return executeUpdateDelete(Collections.singleton(str), sQLiteStatement);
    }
    public int executeUpdateDelete(Set<String> set, SQLiteStatement sQLiteStatement) {
        if (this.logging) {
            log("EXECUTE\n %s", sQLiteStatement);
        }
        return sQLiteStatement.executeUpdateDelete();
    }
    public long executeInsert(String str, SQLiteStatement sQLiteStatement) {
        return executeInsert(Collections.singleton(str), sQLiteStatement);
    }
    public long executeInsert(Set<String> set, SQLiteStatement sQLiteStatement) {
        if (this.logging) {
            log("EXECUTE\n %s", sQLiteStatement);
        }
        return sQLiteStatement.executeInsert();
    }
    public long executeInsert(String str, ContentValues contentValues) {
        if (this.logging) {
            log("EXECUTE\n %s", str);
        }
        return getReadableDatabase().insertOrThrow(str, null, contentValues);
    }
    static String indentSql(String str) {
        return str.replace(SqlLiteVariable._NEW_LINE, "\n       ");
    }
    void log(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        this.logger.log(str);
    }
    private static String conflictString(int i2) {
        if (i2 == 0) {
            return "none";
        }
        if (i2 == 1) {
            return "rollback";
        }
        if (i2 == 2) {
            return "abort";
        }
        if (i2 == 3) {
            return "fail";
        }
        if (i2 == 4) {
            return "ignore";
        }
        if (i2 == 5) {
            return "replace";
        }
        return "unknown (" + i2 + ')';
    }
    public boolean insertOrUpdate(List<T> list, boolean z) {
        if (list.size() == 0) {
            return true;
        }
        if (z) {
            return insert(list);
        }
        if (this.mLogoSqlCreator.useTableUpdate(list.get(0))) {
            return update(list);
        }
        return insert(list, 5);
    }
    public boolean insert(List<T> list, boolean z) {
        if (list.size() == 0) {
            return true;
        }
        if (z) {
            if (delete(list, null)) {
                return insert(list);
            }
            return false;
        }
        return insert(list, 5);
    }
    public boolean insert(List<T> list) {
        if (list.size() == 0) {
            return true;
        }
        return insert(list, 0);
    }
    public long insert(T t) {
        return insert((T) t, 0);
    }
    public long insert(T t, boolean z) {
        if (z) {
            if (delete((List<T>) t, (String) null)) {
                return insert((T) t);
            }
            return -1L;
        }
        return insert((T) t, 5);
    }
    private void checkErpTypeNetsisAndTableClCard(List<T> list) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (baseErp.getErpType() == ErpType.NETSIS && list.get(0).getClass() == ClCard.class) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((ClCard) it.next()).getCode());
            }
            String formatList = StringUtils.formatList(arrayList, true);
            baseErp.getLogoSqlBriteDatabase().delete(ClCard.class, "CODE IN " + formatList);
        }
    }
    private boolean insert(List<T> list, int i2) {
        try {
            checkErpTypeNetsisAndTableClCard(list);
            String insertTableFromSql = this.mLogoSqlCreator.insertTableFromSql(list.get(0), i2);
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteStatement compileStatement = writableDatabase.compileStatement(insertTableFromSql);
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    String createOperationKey = createOperationKey(list.size() > 0 ? list.get(0) : null);
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        insertWithoutRowId(compileStatement, it.next(), createOperationKey);
                        writableDatabase.yieldIfContendedSafely();
                    }
                    writableDatabase.setTransactionSuccessful();
                    this.ColumnMap.remove(createOperationKey);
                    if (this.logging) {
                        log("INSERT Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                    if (writableDatabase.inTransaction()) {
                        writableDatabase.endTransaction();
                    }
                    return true;
                } catch (Throwable th) {
                    if (writableDatabase.inTransaction()) {
                        writableDatabase.endTransaction();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                Log.e(TAG, "insert: ", e2);
                if (!writableDatabase.inTransaction()) {
                    return false;
                }
                writableDatabase.endTransaction();
                return false;
            }
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "insert: ", e3);
            return false;
        }
    }
    private String createOperationKey(T t) {
        return String.format("%s_%s", t != null ? t.getClass().getName() : "", UUID.randomUUID());
    }
    private long insert(T t, int i2) {
        long j2 = -1;
        try {
            String insertTableFromSql = this.mLogoSqlCreator.insertTableFromSql(t, i2);
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteStatement compileStatement = writableDatabase.compileStatement(insertTableFromSql);
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    j2 = insert(compileStatement, (T) t);
                    writableDatabase.setTransactionSuccessful();
                    if (this.logging) {
                        log("INSERT Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (Exception e2) {
                Log.e(TAG, "insert: ", e2);
            }
            return j2;
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "insert: ", e3);
            return -1L;
        }
    }
    private void insertWithoutRowId(SQLiteStatement sQLiteStatement, T t, String str) {
        try {
            setStatementBindingForInsert(t, sQLiteStatement, str);
            sQLiteStatement.execute();
            sQLiteStatement.clearBindings();
        } catch (Exception e2) {
            Log.e(TAG, "insert: ", e2);
        }
    }
    private long insert(SQLiteStatement sQLiteStatement, T t) {
        try {
            setStatementBinding(t, sQLiteStatement, 1);
            long executeInsert = sQLiteStatement.executeInsert();
            sQLiteStatement.clearBindings();
            return executeInsert;
        } catch (Exception e2) {
            Log.e(TAG, "insert: ", e2);
            return -1L;
        }
    }
    public boolean update(List<T> list) {
        try {
            String updateTableFromClassTigerListString = this.mLogoSqlCreator.updateTableFromClassTigerListString(list.get(0));
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteStatement compileStatement = writableDatabase.compileStatement(updateTableFromClassTigerListString);
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        update(compileStatement, it.next());
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (this.logging) {
                        log("UPDATE Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                    writableDatabase.endTransaction();
                    return true;
                } catch (Exception e2) {
                    Log.e(TAG, "update: ", e2);
                    writableDatabase.endTransaction();
                    return false;
                }
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (IllegalAccessException e3) {
            log(e3.getMessage());
            return false;
        }
    }
    public long update(T t) {
        long j2 = -1;
        try {
            String updateTableFromClassTigerListString = this.mLogoSqlCreator.updateTableFromClassTigerListString(t);
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteStatement compileStatement = writableDatabase.compileStatement(updateTableFromClassTigerListString);
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    j2 = update(compileStatement, t);
                    writableDatabase.setTransactionSuccessful();
                    if (this.logging) {
                        log("UPDATE Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (Exception e2) {
                Log.e(TAG, "update: ", e2);
            }
            return j2;
        } catch (IllegalAccessException e3) {
            log(e3.getMessage());
            return -1L;
        }
    }
    public boolean update(List<T> list, String str, int i2) {
        if (list.size() == 0) {
            return true;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            SQLiteStatement compileStatement = writableDatabase.compileStatement(this.mLogoSqlCreator.updateTableFromClassTigerListString(list.get(0), str, i2));
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        setStatementBinding(it.next(), compileStatement, 1, false, str);
                        compileStatement.executeUpdateDelete();
                        compileStatement.clearBindings();
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (this.logging) {
                        log("UPDATE Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                    writableDatabase.endTransaction();
                    return true;
                } catch (Exception e2) {
                    log(e2.getMessage());
                    writableDatabase.endTransaction();
                    return false;
                }
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (IllegalAccessException e3) {
            log(e3.getMessage());
            return false;
        }
    }
    public long update(T t, String str, int i2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        long j2 = -1;
        try {
            SQLiteStatement compileStatement = writableDatabase.compileStatement(this.mLogoSqlCreator.updateTableFromClassTigerListString(t, str, i2));
            writableDatabase.beginTransaction();
            try {
                try {
                    long nanoTime = System.nanoTime();
                    setStatementBinding(t, compileStatement, 1, false, str);
                    j2 = compileStatement.executeUpdateDelete();
                    writableDatabase.setTransactionSuccessful();
                    if (this.logging) {
                        log("UPDATE Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                    }
                } catch (Exception e2) {
                    log(e2.getMessage());
                }
                return j2;
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (IllegalAccessException e3) {
            log(e3.getMessage());
            return -1L;
        }
    }
    private long update(SQLiteStatement sQLiteStatement, T t) {
        try {
            setStatementBinding(t, sQLiteStatement, 1, true);
            long executeUpdateDelete = sQLiteStatement.executeUpdateDelete();
            sQLiteStatement.clearBindings();
            return executeUpdateDelete;
        } catch (Exception e2) {
            Log.e(TAG, "update: ", e2);
            return -1L;
        }
    }
    public boolean delete(T t, String str, String... strArr) {
        return delete(t.getClass(), str, strArr);
    }
    public boolean delete(Class<?> cls, String str, String... strArr) {
        return delete(this.mLogoSqlCreator.getTableName(cls), str, strArr) > 0;
    }
    public boolean delete(List<T> list, String str) {
        return delete((T) list.get(0), str);
    }
    public boolean delete(T t, String str) {
        return delete(t.getClass(), str);
    }
    public boolean delete(Class<?> cls, String str) {
        int i2;
        int i3 = 0;
        long nanoTime = 0;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                nanoTime = System.nanoTime();
                String tableName = this.mLogoSqlCreator.getTableName(cls);
                if (this.logging) {
                    log("DELETE table %s", tableName);
                }
                if (this.logging) {
                    log("Delete condition %s", str);
                }
                i3 = writableDatabase.delete(tableName, str, null);
            } catch (SQLException e2) {
                e = e2;
                i2 = -1;
            }
            try {
                if (this.logging) {
                    log("DELETE Completed time (%sms)", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime)));
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (SQLException e3) {
                i2 = i3;
                e = e3;
                Log.e(TAG, "deleteColumn: ", e);
                writableDatabase.endTransaction();
                i3 = i2;
                if (i3 <= -1) {
                }
            }
            return i3 <= -1;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
    private int setStatementBinding(T t, SQLiteStatement sQLiteStatement, int i2) {
        return setStatementBinding(t, sQLiteStatement, i2, false, "");
    }
    private int setStatementBinding(T t, SQLiteStatement sQLiteStatement, int i2, boolean z) {
        return setStatementBinding(t, sQLiteStatement, i2, z, "");
    }
    private void setStatementBindingForInsert(T t, SQLiteStatement sQLiteStatement, String str) {
        int i2;
        try {
            Class<?> cls = t.getClass();
            if (this.ColumnMap.containsKey(str)) {
                for (SqlColumnMap sqlColumnMap : this.ColumnMap.get(str)) {
                    setObjectBindingForInsert(t, sqlColumnMap.DataField, sqlColumnMap.ColumnType, sQLiteStatement, sqlColumnMap.Count);
                }
                return;
            }
            ArrayList arrayList = new ArrayList();
            int i3 = 1;
            Class<?> cls2 = cls;
            while (true) {
                int i4 = i3;
                for (Field field : cls2.getDeclaredFields()) {
                    try {
                    } catch (Exception e2) {
                        e = (SQLException) e2;
                        i2 = i4;
                    }
                    if (field.isAnnotationPresent(Column.class)) {
                        ColumnProperty columnProperty = getSqlCreator().getColumnProperty(field.getAnnotation(Column.class));
                        if (columnProperty != null) {
                            if (!columnProperty.isAutoIncrement()) {
                                int i5 = i4;
                                try {
                                    setObjectBinding(t, field, columnProperty.type(), sQLiteStatement, i5);
                                    SqlColumnMap sqlColumnMap2 = new SqlColumnMap();
                                    sqlColumnMap2.ColumnType = columnProperty.type();
                                    i2 = i5;
                                    try {
                                        sqlColumnMap2.Count = i2;
                                        sqlColumnMap2.DataField = field.getName();
                                        arrayList.add(sqlColumnMap2);
                                        i4 = i2 + 1;
                                    } catch (Exception e3) {
                                        e = (SQLException) e3;
                                        Log.e(TAG, "setStatementBinding: ", e);
                                        i4 = i2;
                                    }
                                } catch (Exception e4) {
                                    e = (SQLException) e4;
                                    i2 = i5;
                                }
                            }
                        }
                    }
                    i2 = i4;
                    i4 = i2;
                }
                int i6 = i4;
                cls2 = cls2.getSuperclass();
                if (cls2 == null) {
                    this.ColumnMap.putIfAbsent(str, arrayList);
                    return;
                }
                i3 = i6;
            }
        } catch (Exception e5) {
            Log.e(TAG, "setStatementBinding: ", e5);
        }
    }
    private int setStatementBinding(T t, SQLiteStatement sQLiteStatement, int i2, boolean z, String str) {
        int i3 = 0;
        Column column;
        ColumnProperty columnProperty;
        int i4 = i2;
        boolean z2 = i4 > 1;
        try {
            Class<?> cls = t.getClass();
            do {
                Field[] declaredFields = cls.getDeclaredFields();
                int length = declaredFields.length;
                int i5 = 0;
                while (i5 < length) {
                    Field field = declaredFields[i5];
                    try {
                    } catch (Exception e2) {
                        e = (SQLException) e2;
                        i3 = i5;
                    }
                    if (field.isAnnotationPresent(Column.class) && (columnProperty = getSqlCreator().getColumnProperty((column = field.getAnnotation(Column.class)))) != null && !columnProperty.isAutoIncrement()) {
                        if (z) {
                            if (!str.equals(column.name()) && columnProperty.useUpdate() == z2) {
                                i3 = i5;
                                try {
                                    setObjectBinding(t, field, columnProperty.type(), sQLiteStatement, i4);
                                } catch (Exception e3) {
                                    e = (SQLException) e3;
                                    Log.e(TAG, "setStatementBinding: ", e);
                                    i5 = i3 + 1;
                                }
                            }
                        } else {
                            i3 = i5;
                            if (str.equals(column.name())) {
                                i5 = i3 + 1;
                            } else {
                                setObjectBinding(t, field, columnProperty.type(), sQLiteStatement, i4);
                            }
                        }
                        i4++;
                        i5 = i3 + 1;
                    }
                    i3 = i5;
                    i5 = i3 + 1;
                }
                cls = cls.getSuperclass();
            } while (cls != null);
        } catch (Exception e4) {
            Log.e(TAG, "setStatementBinding: ", e4);
            i4 = 0;
        }
        return (!z || z2) ? i4 : setStatementBinding(t, sQLiteStatement, i4, z, str);
    }
    private void setObjectBinding(T t, Field field, Column.ColumnValueTypes columnValueTypes, SQLiteStatement sQLiteStatement, int i2) throws IllegalAccessException {
        byte[] bArr;
        field.setAccessible(true);
        if (columnValueTypes == Column.ColumnValueTypes.INTEGER) {
            sQLiteStatement.bindString(i2, StringUtils.convertIntToString(field.getInt(t)));
            return;
        }
        if (columnValueTypes == Column.ColumnValueTypes.NVARCHAR || columnValueTypes == Column.ColumnValueTypes.VARCHAR || columnValueTypes == Column.ColumnValueTypes.TEXT) {
            sQLiteStatement.bindString(i2, StringUtils.convertNullToString(field.get(t)));
            return;
        }
        if (columnValueTypes == Column.ColumnValueTypes.REAL || columnValueTypes == Column.ColumnValueTypes.DOUBLE) {
            try {
                sQLiteStatement.bindDouble(i2, field.getDouble(t));
            } catch (IllegalAccessException unused) {
                sQLiteStatement.bindDouble(i2, field.getFloat(t));
            }
        } else {
            if (columnValueTypes != Column.ColumnValueTypes.BLOB || (bArr = (byte[]) field.get(t)) == null) {
                return;
            }
            sQLiteStatement.bindBlob(i2, bArr);
        }
    }
    private void setObjectBindingForInsert(T t, String str, Column.ColumnValueTypes columnValueTypes, SQLiteStatement sQLiteStatement, int i2) throws IllegalAccessException {
        byte[] bArr;
        Class<?> cls = t.getClass();
        Field field = null;
        do {
            try {
                try {
                    field = cls.getDeclaredField(str);
                    field.setAccessible(true);
                    break;
                } catch (Exception unused) {
                    cls = cls.getSuperclass();
                }
            } catch (Exception e2) {
                Log.e(TAG, "setStatementBinding: ", e2);
                return;
            }
        } while (!cls.getName().equals(Object.class.getName()));
        if (columnValueTypes == Column.ColumnValueTypes.INTEGER) {
            sQLiteStatement.bindString(i2, StringUtils.convertIntToString(field.getInt(t)));
            return;
        }
        if (columnValueTypes != Column.ColumnValueTypes.NVARCHAR && columnValueTypes != Column.ColumnValueTypes.VARCHAR && columnValueTypes != Column.ColumnValueTypes.TEXT) {
            if (columnValueTypes != Column.ColumnValueTypes.REAL && columnValueTypes != Column.ColumnValueTypes.DOUBLE) {
                if (columnValueTypes != Column.ColumnValueTypes.BLOB || (bArr = (byte[]) field.get(t)) == null) {
                    return;
                }
                sQLiteStatement.bindBlob(i2, bArr);
                return;
            }
            try {
                sQLiteStatement.bindDouble(i2, field.getDouble(t));
                return;
            } catch (IllegalAccessException unused2) {
                sQLiteStatement.bindDouble(i2, field.getFloat(t));
                return;
            }
        }
        sQLiteStatement.bindString(i2, StringUtils.convertNullToString(field.get(t)));
    }
}
