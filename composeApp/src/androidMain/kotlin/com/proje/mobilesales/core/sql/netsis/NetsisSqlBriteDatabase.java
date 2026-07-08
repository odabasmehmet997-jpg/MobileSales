package com.proje.mobilesales.core.sql.netsis;

import android.database.sqlite.SQLiteOpenHelper;
import com.proje.mobilesales.core.sql.ISqlCreator;
import com.proje.mobilesales.core.sql.SqlBrite;
import com.proje.mobilesales.core.sql.SqlBriteDatabase;
public class NetsisSqlBriteDatabase<T> extends SqlBriteDatabase<T> {
    private static final String TAG = "NetsisSqlBriteDatabase";

    NetsisSqlBriteDatabase(SQLiteOpenHelper sQLiteOpenHelper, SqlBrite.Logger logger, ISqlCreator iSqlCreator) {
        super(sQLiteOpenHelper, logger, iSqlCreator);
    }
}
