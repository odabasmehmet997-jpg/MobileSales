package com.proje.mobilesales.core.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.*;
import android.os.Process;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.fragment.BottomAlertDialogFragment;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.proje.mobilesales.core.utils.AppUtils.exitApplication;
import static com.proje.mobilesales.core.utils.AppUtils.isMyServiceRunning;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String PID = BaseActivity.class.getName() + ".PID";
    private static boolean firstShowGps = false;
    public AnalyticsEventType analyticsEventType;
    private boolean mLocationUpdateBound = false;
    protected LocationUpdatesService mLocationUpdatesServiceService = null;
    private final ServiceConnection mLocationUpdateServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BaseActivity.this.mLocationUpdatesServiceService = ((LocationUpdatesService.LocalBinder) iBinder).getService();
            BaseActivity.this.mLocationUpdateBound = true;
            if (ErpCreator.getInstance().getmBaseErp().getErpRights().isGps() && ErpCreator.getInstance().getmBaseErp().useGps()) {
                BaseActivity.this.onLocationServiceBound();
            }
        }
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                BaseActivity baseActivity = BaseActivity.this;
                baseActivity.mLocationUpdatesServiceService = null;
                baseActivity.mLocationUpdateBound = false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };
    public double getLatitude() {
        try {
            if (MobileSales.getInstance() == null || MobileSales.getInstance().getCurrentLocation() == null) {
                return 0.0d;
            }
            return MobileSales.getInstance().getCurrentLocation().getLatitude();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public double getLongitude() {
        try {
            if (MobileSales.getInstance() == null || MobileSales.getInstance().getCurrentLocation() == null) {
                return 0.0d;
            }
            return MobileSales.getInstance().getCurrentLocation().getLongitude();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void onLocationServiceBound() {
        try {
            LocationUpdatesService locationUpdatesService;
            if (!ErpCreator.getInstance().getmBaseErp().getErpRights().isGps() || !ErpCreator.getInstance().getmBaseErp().useGps()) {
                if (isMyServiceRunning(this, LocationUpdatesService.class) && (locationUpdatesService = this.mLocationUpdatesServiceService) != null) {
                    locationUpdatesService.removeLocationUpdates();
                }
            } else if (Connectivity.isGpsConnect(this)) {
                this.mLocationUpdatesServiceService.requestLocationUpdates();
            } else if (!firstShowGps && getClass() != MainActivity.class) {
                ContextUtils.checkGpsConnection();
                firstShowGps = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    @SuppressLint("WrongConstant")
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Log.d("***", getClass().getName());
        baseLogScreenFirebaseAnalyticsData(this.analyticsEventType);
        int myPid = Process.myPid();
        if (bundle != null) {
            String str = PID;
            if (!(myPid == bundle.getInt(str) || bundle.getInt(str) == 0 || getClass() == LoginActivity.class || getClass() == PreferenceActivity.class)) {
                ContextUtils.setmContext(this);
                ContextUtils.setmActivity(this);
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(268468224);
                startActivity(intent);
                finish();
                return;
            }
        }
        overridePendingTransition(R.anim.pull_in_from_left, R.anim.pull_out_to_left);
        ContextUtils.setmContext(this);
        ContextUtils.setmActivity(this);
        checkForLanguageChange();
        resetTitles();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    protected void baseLogScreenFirebaseAnalyticsData(AnalyticsEventType analyticsEventType) {
        if (analyticsEventType != null) {
            FirebaseAnalyticsHelper.Companion.getInstance(FirebaseAnalytics.getInstance(this)).logEventFirebaseAnalyticsData(analyticsEventType);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType analyticsOperationType) {
        if (analyticsOperationType != null) {
            FirebaseAnalyticsHelper.Companion.getInstance(FirebaseAnalytics.getInstance(this)).logOperationFirebaseAnalyticsData(analyticsOperationType);
        }
    }
    private void resetTitles() {
        try {
            int i = getPackageManager().getActivityInfo(getComponentName(), 128).labelRes;
            if (i != 0) {
                setTitle(i);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void checkForLanguageChange() {
        Object object;
        if ((ContextUtils.getmActivity() instanceof LoginActivity) && (object = new SharedPreferencesHelper().getObject("changeLocale", Boolean.class)) != null && Boolean.parseBoolean(object.toString())) {
            exitApplication(ContextUtils.getmActivity());
            System.exit(0);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void attachBaseContext(Context context) {
        checkForLanguageChange();
        super.attachBaseContext(LanguageHelper.onAttach(context));
        applyOverrideConfiguration(new Configuration());
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void applyOverrideConfiguration(Configuration configuration) {
        super.applyOverrideConfiguration(updateConfigurationIfSupported(configuration));
    }
    private Configuration updateConfigurationIfSupported(Configuration configuration) {
        if (!configuration.getLocales().isEmpty()) {
            return configuration;
        }
        LocaleList localeList = new LocaleList(new Locale(LanguageHelper.getLanguage(this)));
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return configuration;
    }
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
    protected void setLanguage() {
        try {
            String applicationLanguage = new SharedPreferencesHelper(this).getApplicationLanguage();
            Resources resources = getApplicationContext().getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            if (!getResources().getConfiguration().locale.getLanguage().equals(applicationLanguage)) {
                configuration.locale = new Locale(applicationLanguage);
                configuration.setLayoutDirection(new Locale(applicationLanguage));
                resources.updateConfiguration(configuration, displayMetrics);
                recreate();
            }
        } catch (Exception e) {
            Log.e("BaseActivity", "setLanguage: ", e);
        }
    }
    public void onStart() {
        super.onStart();
        ContextUtils.setmContext(this);
        if (getClass() != LoginActivity.class && getClass() != PreferenceActivity.class) {
            bindService(new Intent(this, LocationUpdatesService.class), this.mLocationUpdateServiceConnection, 1);
        }
    }
    public void onStop() {
        super.onStop();
        if (this.mLocationUpdateBound) {
            unbindService(this.mLocationUpdateServiceConnection);
            this.mLocationUpdateBound = false;
        }
    }
    public void onDestroy() {
        super.onDestroy();
        this.mLocationUpdatesServiceService = null;
        if (ContextUtils.getmActivity() != null && ContextUtils.getmActivity().getClass().equals(getClass())) {
            ContextUtils.setmActivity(null);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        ContextUtils.setmContext(this);
        ContextUtils.setmActivity(this);
    }
    public void setToolbar() {
        setSupportActionBar( findViewById(R.id.toolbar));
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_USE_LOGO);
    }
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(PID, Process.myPid());
    } 
    public void onSaveInstanceState(@NonNull Bundle bundle, @NonNull PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
        bundle.putInt(PID, Process.myPid());
    }
    public void showAlertDialog(String str, String str2) {
        runOnUiThread(new Runnable(str2, str) { 
            public final String f1;
            public final String f2;

            {
                this.f1 = str2;
                this.f2 = str;
            }

            public void run() {
                BaseActivity.this.lambdashowAlertDialog1(this.f1, this.f2);
            }
        });
    }
    public void lambdashowAlertDialog1(String str, String str2) {
        BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setMessage(str);
        bottomAlertDialogFragment.setTitle(str2);
        bottomAlertDialogFragment.setCancelable(true);
        bottomAlertDialogFragment.setShowNegativeButton(false);
        bottomAlertDialogFragment.setPositiveButton("", new View.OnClickListener() {
            public void onClick(View view) {
                bottomAlertDialogFragment.dismiss();
            }
        });
        bottomAlertDialogFragment.show(getSupportFragmentManager(), "BottomAlertDialogFragment");
    }
    protected void onSaveInstanceState(ResourceBundle bundle) {
    }
}