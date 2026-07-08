package com.proje.mobilesales.core.sql;
import android.util.Log;
import androidx.annotation.NonNull;
public abstract class SqlBrite implements ISqlBrite {
    protected static final Logger DEFAULT_LOGGER = new Logger() {
        public void log(String str) {
            Log.d("SqlBrite", str);
        }
    };
    protected final Logger logger;
    protected final ISqlCreator mLogoSqlCreator;
    public interface Logger {
        void log(String str);
    }
    protected SqlBrite(Logger logger, ISqlCreator iSqlCreator) {
        this.logger = logger;
        this.mLogoSqlCreator = iSqlCreator;
    }
}
