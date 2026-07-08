package com.proje.mobilesales.core.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.proje.mobilesales.core.enums.NotifyType;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.LanguageHelper;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;

import static com.proje.mobilesales.core.utils.AppUtils.exitApplication;
import static com.proje.mobilesales.core.utils.AppUtils.restartBaseErp;

public abstract class BaseErpActivityPreferences extends AppCompatDelagateBase {

    protected BaseErp baseErp;
    public String customerCode;
    public CustomerOperation customerOperation;
    public int customerRef;
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (this.baseErp == null) {
            restartBaseErp(this);
        }
        resetTitles();
    }
    public void getExtras() {
        this.customerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, 0);
        this.customerOperation = getIntent().getParcelableExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
    }
    protected void onPause() {
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        restartBaseErp(this);
    }
    private void resetTitles() {
        try {
            int i2 = getPackageManager().getActivityInfo(getComponentName(), 128).labelRes;
            if (i2 != 0) {
                setTitle(i2);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }
    protected void attachBaseContext(Context context) {
        Object object;
        if ((ContextUtils.getmActivity() instanceof LoginActivity) && (object = new SharedPreferencesHelper().getObject("changeLocale", Boolean.class)) != null && Boolean.parseBoolean(object.toString())) {
            exitApplication(ContextUtils.getmActivity());
            System.exit(0);
        }
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }
    public void update(NotifyType notifyType) {
        Log.d("BaseErpAct", "update() called with: notifyType = [" + notifyType + "]");
    }
}
