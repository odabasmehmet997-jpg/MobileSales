package com.proje.mobilesales.core.sql;

import android.content.Context;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManager;
import com.proje.mobilesales.core.sql.tiger.TigerSqlManager;
import com.proje.mobilesales.core.utils.ContextUtils;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SqlManagerCreator extends BaseSqlManagerFactory {
    public static final Companion Companion = new Companion(null);
    private static final Object LOCK = new Object();
    private static SqlManagerCreator instance;
    private static final SqlManager<Object> mSqlManager = null;

    public ISqlManager<Object> getSqlManager() {
        Context context = ContextUtils.getmContext();
        Scheduler m636io = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(m636io, "io(...)");
        return getmSqlManager(context, m636io);
    }

    private SqlManager<Object> getmSqlManager(Context context, Scheduler scheduler) {
        SqlManager<Object> sqlManager = mSqlManager;
        if (sqlManager != null) {
            return sqlManager;
        }
        ErpType erpType = Preferences.getErpType(context);
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        return getSql(erpType, scheduler);
    }
    public SqlManager<Object> getSql(ErpType erpType, Scheduler ioSchedulers) {
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        Intrinsics.checkNotNullParameter(ioSchedulers, "ioSchedulers");
        return erpType == ErpType.NETSIS ? new NetsisSqlManager(ioSchedulers) : new TigerSqlManager(ioSchedulers);
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public SqlManagerCreator getInstance() {
            synchronized (SqlManagerCreator.LOCK) {
                try {
                    if (SqlManagerCreator.instance == null) {
                        SqlManagerCreator.instance = new SqlManagerCreator();
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return SqlManagerCreator.instance;
        }
    }
}
