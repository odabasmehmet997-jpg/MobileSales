package com.proje.mobilesales.core.sql.netsis;

import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlCreator;
import com.proje.mobilesales.core.sql.SqlBrite;
public class NetsisSqlBrite extends SqlBrite {

    public static final class Builder {
        private SqlBrite.Logger logger = SqlBrite.DEFAULT_LOGGER;
        private ISqlCreator mLogoSqlCreator;

        @CheckResult
        public Builder logger(@NonNull SqlBrite.Logger logger) {
            if (logger == null) {
                logger = SqlBrite.DEFAULT_LOGGER;
            }
            this.logger = logger;
            return this;
        }

        @CheckResult
        public Builder sqlCreator(@NonNull ISqlCreator iSqlCreator) {
            this.mLogoSqlCreator = iSqlCreator;
            return this;
        }
        public SqlBrite build() {
            return new NetsisSqlBrite(this.logger, this.mLogoSqlCreator);
        }
    }

    protected NetsisSqlBrite(@NonNull SqlBrite.Logger logger, ISqlCreator iSqlCreator) {
        super(logger, iSqlCreator);
    }

    public ISqlBriteDatabase wrapDatabaseHelper(@NonNull SQLiteOpenHelper sQLiteOpenHelper) {
        return new NetsisSqlBriteDatabase(sQLiteOpenHelper, this.logger, this.mLogoSqlCreator);
    }
}
