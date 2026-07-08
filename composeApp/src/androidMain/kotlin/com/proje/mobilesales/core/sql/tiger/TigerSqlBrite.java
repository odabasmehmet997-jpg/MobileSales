package com.proje.mobilesales.core.sql.tiger;

import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlCreator;
import com.proje.mobilesales.core.sql.SqlBrite;
public class TigerSqlBrite extends SqlBrite {

    public static final class Builder {
        private SqlBrite.Logger logger = SqlBrite.DEFAULT_LOGGER;
        private ISqlCreator mLogoSqlCreator;
        public Builder logger(SqlBrite.Logger logger) {
            if (logger == null) {
                logger = SqlBrite.DEFAULT_LOGGER;
            }
            this.logger = logger;
            return this;
        } 
        public Builder sqlCreator(ISqlCreator iSqlCreator) {
            this.mLogoSqlCreator = iSqlCreator;
            return this;
        }
        public SqlBrite build() {
            return new TigerSqlBrite(this.logger, this.mLogoSqlCreator);
        }
    }

    TigerSqlBrite(SqlBrite.Logger logger, ISqlCreator iSqlCreator) {
        super(logger, iSqlCreator);
    }
    public ISqlBriteDatabase wrapDatabaseHelper(SQLiteOpenHelper sQLiteOpenHelper) {
        return new TigerSqlBriteDatabase(sQLiteOpenHelper, this.logger, this.mLogoSqlCreator);
    }
}
