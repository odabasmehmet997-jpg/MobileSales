package com.proje.mobilesales.core.sql;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
public interface ISqlBrite {
    ISqlBriteDatabase wrapDatabaseHelper(  SQLiteOpenHelper sQLiteOpenHelper);
}
