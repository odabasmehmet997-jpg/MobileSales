package com.proje.mobilesales.core;
import android.content.Context;
import android.util.Log;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ToastBuilder;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManager;
import com.proje.mobilesales.core.sql.tiger.TigerSqlManager;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.KeyDelegate;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
public class ActivityModule {
    private static final String TAG = "ActivityModule";
    private final Context mContext;
    public ActivityModule(Context context) {
        this.mContext = context;
    }
    public Context provideContext() {
        return this.mContext;
    }
    public ActionViewResolver provideActionViewResolver() {
        return new ActionViewResolver();
    }
    public AlertDialogBuilder provideAlertDialogBuilder(Context context) {
        return new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) ContextUtils.getmActivity());
    }
    public PopupMenu providePopupMenu() {
        return new PopupMenu.Impl();
    }
    public ToastBuilder provideToastBuilder(Context context) {
        return new ToastBuilder.Impl(context);
    }
    public ProgressDialogBuilder provideProgressDialogBuilder(Context context) {
        return new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) ContextUtils.getmActivity());
    }
    public BaseErp provideBaseErp() {
        try {
            return ErpCreator.getInstance().getmBaseErp();
        } catch (Exception e2) {
            Log.e(TAG, "onCreate: ", e2);
            return null;
        }
    }
    public ErpType provideErpType(Context context) {
        return Preferences.getErpType(context);
    }
    public SharedPreferencesHelper provideLogoSharedPrerences(Context context) {
        return new SharedPreferencesHelper(context);
    }
    public KeyDelegate provideKeyDelegate() {
        return new KeyDelegate();
    }
    public ISqlManager provideSqlManager(Scheduler scheduler, ErpType erpType) {
        return erpType == ErpType.NETSIS ? new NetsisSqlManager(scheduler) : new TigerSqlManager(scheduler);
    }
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }
}
