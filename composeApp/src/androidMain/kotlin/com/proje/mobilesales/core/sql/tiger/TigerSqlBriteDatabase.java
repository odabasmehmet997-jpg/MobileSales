package com.proje.mobilesales.core.sql.tiger;

import android.database.sqlite.SQLiteOpenHelper;
import com.proje.mobilesales.core.sql.ISqlCreator;
import com.proje.mobilesales.core.sql.SqlBrite;
import com.proje.mobilesales.core.sql.SqlBriteDatabase;
public class TigerSqlBriteDatabase<T> extends SqlBriteDatabase<T> {
    private static final String TAG = "TigerSqlBriteDatabase";

    TigerSqlBriteDatabase(SQLiteOpenHelper sQLiteOpenHelper, SqlBrite.Logger logger, ISqlCreator iSqlCreator) {
        super(sQLiteOpenHelper, logger, iSqlCreator);
    }
}
