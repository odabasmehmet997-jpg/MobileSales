package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.R;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.google.android.material.textfield.TextInputLayout;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.AesEncryption;
import com.proje.mobilesales.core.utils.BluetoothUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.work.PeriodicBackgroundNotification;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.Response;
import com.proje.mobilesales.features.notification.util.NotificationChannelContent;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.notification.util.NotificationUtils;
import com.proje.mobilesales.features.settings.model.enums.PreferenceType;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import com.scottyab.rootbeer.RootBeer;
import java.util.concurrent.TimeUnit;

import org.greenrobot.eventbus.EventBus;
import permissions.dispatcher.PermissionRequest;
 
public class LoginActivity extends BaseInjectableActivity {
    private static final int REQUEST_SETTINGS = 1;
    private static final String STATE_PASSWORD = "state:password";
    private static final String STATE_USERNAME = "state:userName";
    private static final String TAG = "LoginActivity";
    AppCompatButton btnAyarlar;
    AppCompatButton btnGiris;
    AppCompatEditText etPassword;
    AppCompatEditText etUserName;
    SharedPreferencesHelper mSharedPreferencesHelper;
    SharedPreferences prefs;
    AppCompatCheckBox swtRememberMe;
    TextInputLayout txtInptPassword;
    TextInputLayout txtInptUserName;
    TextView txtVersion;
    private Exception e;
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK, Manifest.permission.BLUETOOTH_CONNECT})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_login);
        this.getActivityComponent().inject(this);
        etUserName = this.findViewById(R.id.etUserName);
        etPassword = this.findViewById(R.id.etPwd);
        swtRememberMe = this.findViewById(R.id.swtRememberMe);
        txtInptUserName = this.findViewById(R.id.txtInptUserName);
        txtInptPassword = this.findViewById(R.id.txtInptPassword);
        btnGiris = this.findViewById(R.id.btnGiris);
        btnAyarlar = this.findViewById(R.id.btnAyarlar);
        txtVersion = this.findViewById(R.id.txtVersion);
        prefs = Preferences.getSecurePreferences(this);
        if (null != bundle) {
            this.restoreUserNamePassword(bundle.getString(LoginActivity.STATE_USERNAME), bundle.getString(LoginActivity.STATE_PASSWORD));
        } else {
            this.initRemember();
            if (!this.checkSettingsForLogin()) {
                this.openSettings();
            }
        }
        if (!TextUtils.isEmpty(Preferences.getPrinterOneFullAddress(this))) {
            BluetoothUtil.openBluetooth(true);
        }
        if (!TextUtils.isEmpty(Preferences.getPrinterTwoFullAddress(this))) {
            BluetoothUtil.openBluetooth(false);
        }
        if (Preferences.isDemo(this) && Preferences.getErpType(this) == ErpType.TIGER) {
            Toast.makeText(this, this.getString(R.string.str_demo_username_password), Toast.LENGTH_LONG).show();
        }
        if (Preferences.isDemo(this) && Preferences.getErpType(this) == ErpType.NETSIS) {
            Toast.makeText(this, this.getString(R.string.str_netsis_demo_username_password), Toast.LENGTH_LONG).show();
        }
        txtVersion.setText(BuildConfig.VERSION_NAME);
        btnAyarlar.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lambdaonCreate0(view);
            }
        });
        btnGiris.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                lambdaonCreate1(view);
            }
        });
        this.checkAutoLogin();
    }
    private void lambdaonCreate0(final View view) {
        this.openSettings();
    }
    private void lambdaonCreate1(final View view) {
        this.startLogin();
    }
    private void checkAutoLogin() {
        final String decrypt;
        try {
            final User user = mSharedPreferencesHelper.getUser();
            if (null != user && user.isLoggedIn()) {
                if (mSharedPreferencesHelper.getRememberMe()) {
                    decrypt = etPassword.getText().toString();
                } else {
                    decrypt = new AesEncryption().decrypt(user.getPassword());
                }
                etPassword.setText(decrypt);
                if (TextUtils.isEmpty(etUserName.getText()) || TextUtils.isEmpty(etPassword.getText()) || !this.checkSettingsForLogin()) {
                    return;
                }
                this.startLogin();
            }
        } catch (final Exception e2) {
            Log.e(LoginActivity.TAG, "checkAutoLogin", e2);
        }
    }
    private void startLogin() {
        if (new RootBeer(this).isRooted()) {
            Toast.makeText(this, this.getString(R.string.exp_rooted_device), Toast.LENGTH_LONG).show();
            return;
        }
        final int i2 = Build.VERSION.SDK_INT;
        if (28 >= i2) {
            LoginActivityPermissionsDispatcher.loginPWithPermissionCheck(this);
        } else if (31 <= i2) {
            LoginActivityPermissionsDispatcher.loginSWithPermissionCheck(this);
        } else {
            LoginActivityPermissionsDispatcher.loginWithPermissionCheck(this);
        }
    }
    private boolean checkSettingsForLogin() {
        if (Preferences.isDemo(this)) {
            return true;
        }
        final int i2 = C26091.SwitchMapcomprojemobilesalescoreenumsErpType[Preferences.getErpType(this).ordinal()];
        if (1 == i2) {
            return this.checkNetsisSettings();
        }
        if (2 != i2) {
            return true;
        }
        return this.checkTigerSettings();
    } 
    enum C26091 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreenumsErpType;

        static {
            final int[] iArr = new int[ErpType.values().length];
            SwitchMapcomprojemobilesalescoreenumsErpType = iArr;
            try {
                iArr[ErpType.NETSIS.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C26091.SwitchMapcomprojemobilesalescoreenumsErpType[ErpType.TIGER.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
        }
    }
    private boolean checkNetsisSettings() {
        return !TextUtils.isEmpty(Preferences.getNetsisServerAddress(this)) && !TextUtils.isEmpty(Preferences.getNetsisUsername(this)) && !TextUtils.isEmpty(Preferences.getNetsisPassword(this)) && !TextUtils.isEmpty(Preferences.getNetsisDbName(this)) && !TextUtils.isEmpty(Preferences.getNetsisDbUsername(this)) && !TextUtils.isEmpty(Preferences.getNetsisBranch(this, false));
    }
    private boolean checkTigerSettings() {
        return !TextUtils.isEmpty(Preferences.getTigerServerAddress(this)) && !TextUtils.isEmpty(Preferences.getTigerWcfSecurityCode(this)) && !TextUtils.isEmpty(Preferences.get(this, R.string.pref_tiger_firm_number_key, ""));
    }
    private void restoreUserNamePassword(String str, String str2) {
        final AppCompatEditText appCompatEditText = etUserName;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        appCompatEditText.setText(str);
        final AppCompatEditText appCompatEditText2 = etPassword;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        appCompatEditText2.setText(str2);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
     void initRemember() {
        String str = "";
        String str2;
        final String str3 = "";
        final boolean rememberMe = mSharedPreferencesHelper.getRememberMe();
        String str4 = "";
        try {
            try {
                if (Preferences.isDemo(this)) {
                    final ErpType erpType = Preferences.getErpType(this);
                    final ErpType erpType2 = ErpType.TIGER;
                    str = erpType == erpType2 ? Preferences.getTigerDemoUsername(prefs) : Preferences.getNetsisDemoUsername(prefs);
                    try {
                        str4 = Preferences.getErpType(this) == erpType2 ? Preferences.getTigerDemoP(prefs) : Preferences.getNetsisDemoP(prefs);
                    } catch (final Exception e2) {
                        str2 = str;
                        this.e = e2;
                        Log.e(LoginActivity.TAG, "initRemember: ", this.e);
                        etUserName.setText(str2);
                        etPassword.setText(str4);
                        swtRememberMe.setChecked(rememberMe);
                    } catch (Throwable th) {
                        th = th;
                        etUserName.setText(str);
                        etPassword.setText(str4);
                        throw th;
                    }
                } else if (rememberMe) {
                    final AesEncryption aesEncryption = new AesEncryption();
                    str2 = mSharedPreferencesHelper.getUser().getUserName();
                    try {
                        str4 = aesEncryption.decrypt(mSharedPreferencesHelper.getUser().getPassword());
                        str = str2;
                    } catch (final Exception e3) {
                        this.e = e3;
                        Log.e(LoginActivity.TAG, "initRemember: ", this.e);
                        etUserName.setText(str2);
                        etPassword.setText(str4);
                        swtRememberMe.setChecked(rememberMe);
                    }
                } else {
                    str = mSharedPreferencesHelper.getUser().getUserName();
                }
                etUserName.setText(str);
            } catch (final Exception e4) {
                this.e = e4;
                str2 = "";
            } catch (final Throwable th2) {
                str = "";
            }
            etPassword.setText(str4);
            swtRememberMe.setChecked(rememberMe);
        } catch (final Throwable th3) {
            str = str3;
        }
    } 
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public synchronized void onResume() {
        try {
            super.onResume();
            txtInptUserName.setHint(this.getString(R.string.str_username));
            txtInptPassword.setHint(this.getString(R.string.str_password));
            swtRememberMe.setText(this.getString(R.string.str_remember));
            btnGiris.setText(this.getString(R.string.str_login));
            btnAyarlar.setText(this.getString(R.string.str_settings_non_underline));
            if (Preferences.isDemo(this)) {
                final AppCompatEditText appCompatEditText = etUserName;
                final ErpType erpType = Preferences.getErpType(this);
                final ErpType erpType2 = ErpType.TIGER;
                appCompatEditText.setText(erpType == erpType2 ? Preferences.getTigerDemoUsername(prefs) : Preferences.getNetsisDemoUsername(prefs));
                etPassword.setText(Preferences.getErpType(this) == erpType2 ? Preferences.getTigerDemoP(prefs) : Preferences.getNetsisDemoP(prefs));
            } else {
                this.initRemember();
            }
        } catch (final Throwable th) {
            throw th;
        }
    } 
    protected synchronized void onPause() {
        super.onPause();
    }

    public void openSettings() {
        this.startActivityForResult(new Intent(this, PreferenceActivity.class).putExtra(PreferenceActivity.EXTRA_TITLE, R.string.activity_title_settings).putExtra(PreferenceActivity.EXTRA_PREFERENCES, R.xml.preference_settings).putExtra(PreferenceActivity.EXTRA_PREFERENCE_TYPE, PreferenceType.USER_SETTINGS.ordinal()), 1);
    } 
    protected void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (1 == i2) {
            this.recreate();
        }
    }

    private boolean validate() {
        txtInptPassword.setErrorEnabled(false);
        txtInptUserName.setErrorEnabled(false);
        if (0 == this.etUserName.length()) {
            txtInptUserName.setError(this.getString(R.string.str_username_required));
        }
        if (0 == this.etPassword.length()) {
            txtInptPassword.setError(this.getString(R.string.str_password_required));
        }
        return 0 < this.etUserName.length() && 0 < this.etPassword.length();
    }

    public void loginS() {
        this.executeLoginFlow();
    }

    public void loginP() {
        this.executeLoginFlow();
    }

    public void login() {
        this.executeLoginFlow();
    }

    private void executeLoginFlow() {
        String str;
        if (this.validate() && ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            final String obj = etUserName.getText().toString();
            final String obj2 = etPassword.getText().toString();
            try {
                final BaseErp erp = ErpCreator.getInstance().getErp(Preferences.getErpType(this));
                if (!erp.checkUserSettings()) {
                    Toast.makeText(this, this.getString(R.string.exp_1_erp_null), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    str = new AesEncryption().encrypt(obj2);
                } catch (final Exception e2) {
                    Log.e(LoginActivity.TAG, "login: ", e2);
                    str = "";
                }
                erp.clearLocalDataStore();
                erp.login(obj, str, swtRememberMe.isChecked(), Preferences.isDemo(this));
            } catch (final Exception e3) {
                Log.e(LoginActivity.TAG, "login: ", e3);
                Toast.makeText(this, this.getString(R.string.exp_1_erp_null), Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("WrongConstant")
    void startMainActivity() {
        final User user = mSharedPreferencesHelper.getUser();
        if (null != user) {
            user.setLoggedIn(true);
            mSharedPreferencesHelper.saveUser(user);
        }
        this.startPeriodicNotificationWorker();
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(335577088);
        this.startActivity(intent);
    } 
    public void loginResult(final BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            Preferences.setTransferGetOptionsType(this, ErpCreator.getInstance().getmBaseErp().getTransferGetOptionType());
            this.startMainActivity();
        } else {
            this.showLoginError(baseResult);
        }
    } 
    public void loginResult(final Response response) {
        if (response.isSuccess()) {
            Preferences.setTransferGetOptionsType(this, ErpCreator.getInstance().getmBaseErp().getTransferGetOptionType());
            this.startMainActivity();
        } else {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void showLoginError(final BaseResult baseResult) {
        if (baseResult.isHasLicenseError()) {
            new AlertDialog.Builder(this).setMessage(baseResult.getErrorString()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            Toast.makeText(this, baseResult.getErrorString(), Toast.LENGTH_LONG).show();
        }
    }
     public void onDestroy() {
        mSharedPreferencesHelper.saveRememberMe(swtRememberMe.isChecked());
        super.onDestroy();
        mSharedPreferencesHelper = null;
    }

    void showRationaleForAllPermission(PermissionRequest permissionRequest) {
        final StringBuilder checkLoginPermissions = PermissionUtils.checkLoginPermissions(this);
        checkLoginPermissions.append(" ");
        checkLoginPermissions.append(this.getString(R.string.str_permission_are_required));
        new AlertDialog.Builder(this).setMessage(checkLoginPermissions).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.proceed();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.cancel();
            }
        }).show();
    }
    void showRationaleForAllPermissionP(PermissionRequest permissionRequest) {
        final StringBuilder checkLoginPPermissions = PermissionUtils.checkLoginPPermissions(this);
        checkLoginPPermissions.append(" ");
        checkLoginPPermissions.append(this.getString(R.string.str_permission_are_required));
        new AlertDialog.Builder(this).setMessage(checkLoginPPermissions).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.proceed();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.cancel();
            }
        }).show();
    }

    void showRationaleForAllPermissionS(PermissionRequest permissionRequest) {
        final StringBuilder checkLoginSPermissions = PermissionUtils.checkLoginSPermissions(this);
        checkLoginSPermissions.append(" ");
        checkLoginSPermissions.append(this.getString(R.string.str_permission_are_required));
        new AlertDialog.Builder(this).setMessage(checkLoginSPermissions).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.proceed();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                permissionRequest.cancel();
            }
        }).show();
    }

    void showDeniedForStorage() {
        Toast.makeText(this, ContextUtils.getStringResource(R.string.str_for_permission_deny, PermissionUtils.checkLoginPermissions(this)), Toast.LENGTH_SHORT).show();
    }

    void showDeniedForStorageP() {
        Toast.makeText(this, ContextUtils.getStringResource(R.string.str_for_permission_deny, PermissionUtils.checkLoginPPermissions(this)), Toast.LENGTH_SHORT).show();
    }

    void showDeniedForBluetoothS() {
        Toast.makeText(this, ContextUtils.getStringResource(R.string.str_for_permission_deny, PermissionUtils.checkLoginSPermissions(this)), Toast.LENGTH_SHORT).show();
    }

    void showNeverAskForStorage() {
        PermissionUtils.askPermissionAgain(this, PermissionUtils.checkLoginPermissions(this).toString());
    }

    void showNeverAskForStorageP() {
        PermissionUtils.askPermissionAgain(this, PermissionUtils.checkLoginPPermissions(this).toString());
    }

    void showNeverAskForBluetoothS() {
        PermissionUtils.askPermissionAgain(this, PermissionUtils.checkLoginSPermissions(this).toString());
    } 
    public void onRequestPermissionsResult(final int i2, @NonNull final String[] strArr, @NonNull final int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        LoginActivityPermissionsDispatcher.onRequestPermissionsResult(this, i2, iArr);
    }
 
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putString(LoginActivity.STATE_USERNAME, etUserName.getText().toString());
        bundle.putString(LoginActivity.STATE_PASSWORD, etPassword.getText().toString());
        super.onSaveInstanceState(bundle);
    }
 
    protected void onRestoreInstanceState(final Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.restoreUserNamePassword(bundle.getString(LoginActivity.STATE_USERNAME), bundle.getString(LoginActivity.STATE_PASSWORD));
    }

    private void startPeriodicNotificationWorker() {
        if (Preferences.isDemo(this)) {
            return;
        }
        NotificationUtils.NotificationHelper.createNotificationChannel(this.getApplicationContext(), new NotificationChannelContent(NotificationConstants.PERIODIC_NOTIFICATION_CHANNEL_ID, this.getString(R.string.str_periodic_notification_channel_name), this.getString(R.string.str_periodic_notification_channel_desc), 4));
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(NotificationConstants.PERIODIC_WORK_REQUEST_TAG, ExistingPeriodicWorkPolicy.REPLACE, new PeriodicWorkRequest.Builder(PeriodicBackgroundNotification.class, 15L, TimeUnit.MINUTES).addTag(NotificationConstants.PERIODIC_WORK_REQUEST_TAG).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build());
    }
}
