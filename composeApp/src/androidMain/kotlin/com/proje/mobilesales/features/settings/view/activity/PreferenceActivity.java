package com.proje.mobilesales.features.settings.view.activity;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.settings.model.enums.PreferenceType;
import com.proje.mobilesales.features.settings.view.fragment.*;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.StringsKt;

import java.util.Arrays;

import static com.proje.mobilesales.core.utils.AppUtils.*;

public final class PreferenceActivity extends BaseInjectableActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {
    private static final int CREATE_REQUEST_CODE = 40;
    private static final int RESTORE_REQUEST_CODE = 41;
    private static final String STATE_PREFERENCE_TYPE = "state:preferenceType";
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private PreferenceType mPreferenceType;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_TITLE = PreferenceActivity.class.getName() + ".EXTRA_TITLE";
    public static final String EXTRA_PREFERENCES = PreferenceActivity.class.getName() + ".EXTRA_PREFERENCES";
    public static final String EXTRA_PREFERENCE_TYPE = PreferenceActivity.class.getName() + ".EXTRA_PREFERENCE_TYPE";
    private static final String FRAGMENT_TAG = Fragment.class.getName() + "SettingsFragment";
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.setContentView(R.layout.activity_preferences);
         this.getActivityComponent().inject(this);
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
        final Uri data = this.getIntent().getData();
        if (null != data) {
            this.getIntent().putExtra(PreferenceActivity.EXTRA_PREFERENCES, R.xml.preference_settings);
            this.getIntent().putExtra(PreferenceActivity.EXTRA_PREFERENCE_TYPE, PreferenceType.USER_SETTINGS.ordinal());
            this.getIntent().putExtra(PreferenceActivity.EXTRA_TITLE, R.string.activity_title_settings);
            if (importData(this, this.getContentResolver(), data)) {
                Toast.makeText(this, "Ayarlar \u0130\u00e7eri Al\u0131nd\u0131", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Ayarlar \u0130\u00e7eri Al\u0131n\u0131rken Hata Olu\u015ftu", Toast.LENGTH_LONG).show();
            }
        }
         this.setTitle(this.getIntent().getIntExtra(PreferenceActivity.EXTRA_TITLE, 0));
         this.setToolbar();
        if (null == bundle) {
            mPreferenceType = PreferenceType.values()[this.getIntent().getIntExtra(PreferenceActivity.EXTRA_PREFERENCE_TYPE, 0)];
            final Bundle bundle2 = new Bundle();
            final String str = PreferenceActivity.EXTRA_PREFERENCES;
            bundle2.putInt(str, this.getIntent().getIntExtra(str, 0));
            final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
            final String str2 = PreferenceActivity.FRAGMENT_TAG;
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(str2);
            if (null == findFragmentByTag) {
                findFragmentByTag = this.getPreferenceFragment(mPreferenceType);
            }
            Intrinsics.checkNotNull(findFragmentByTag);
            findFragmentByTag.setArguments(bundle2);
            final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final PreferenceType preferenceType = mPreferenceType;
            Intrinsics.checkNotNull(preferenceType);
            final String format = String.format("%s.%s", Arrays.copyOf(new Object[]{str2, preferenceType.name()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            beginTransaction.replace(R.id.content_frame, findFragmentByTag, format);
            beginTransaction.commit();
            return;
        }
        mPreferenceType = PreferenceType.values()[bundle.getInt(PreferenceActivity.STATE_PREFERENCE_TYPE, 0)];
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuItem findItem;
        final MenuItem findItem2;
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.menu_settings, menu);
        if (mPreferenceType == PreferenceType.USER_SETTINGS && null != (findItem2 = menu.findItem(R.id.menu_use_connection_key))) {
            findItem2.setVisible(true);
        }
        if (mPreferenceType == PreferenceType.TRANSFER && null != (findItem = menu.findItem(R.id.menu_reset_log_path))) {
            findItem.setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        final AlertDialogBuilder message;
        final AlertDialogBuilder negativeButton;
        final AlertDialogBuilder positiveButton;
        final Dialog create;
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.onBackPressed();
            return true;
        }
        if (R.id.menu_export_settings == itemId) {
            this.exportSettings();
        } else if (R.id.menu_share_settings != itemId) {
            switch (itemId) {
                case R.id.menu_reset_all_settings /* 2131297382 */:
                    final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
                    if (null != alertDialogBuilder && null != (message = alertDialogBuilder.setMessage(R.string.str_reset_all_settings_confirm)) && null != (negativeButton = message.setNegativeButton(R.string.cancel, null)) && null != (positiveButton = negativeButton.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.PreferenceActivityExternalSyntheticLambda1
                        public void PreferenceActivityExternalSyntheticLambda1() {
                        }

                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            PreferenceActivity.onOptionsItemSelectedlambda0(PreferenceActivity.this, dialogInterface, i2);
                        }
                    })) && null != (create = positiveButton.create())) {
                        create.show();
                        break;
                    }
                    break;
                case R.id.menu_reset_log_path /* 2131297383 */:
                    final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
                    Intrinsics.checkNotNull(sharedPreferencesHelper);
                    sharedPreferencesHelper.removeLogPath();
                    restart(this, false);
                    break;
                case R.id.menu_reset_settings_path /* 2131297384 */:
                    final SharedPreferencesHelper sharedPreferencesHelper2 = mSharedPreferencesHelper;
                    Intrinsics.checkNotNull(sharedPreferencesHelper2);
                    sharedPreferencesHelper2.removeSettingsPath();
                    Toast.makeText(this, this.getString(R.string.str_done), Toast.LENGTH_LONG).show();
                    break;
                case R.id.menu_restore_settings /* 2131297385 */:
                    this.importSettings();
                    break;
            }
        } else {
            shareSettings(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void onOptionsItemSelectedlambda0(final PreferenceActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.mPreferenceType == PreferenceType.USER_SETTINGS) {
            Preferences.reset(this0);
        } else {
            Preferences.resetWithoutConnectionSettings(this0);
        }
        restart(this0, false);
    }

    public void importSettings() {
        final Object this0 = null;
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.setType("text/*");
        this.startActivityForResult(intent, 41);
    }
    public void onBackPressed(final PreferenceActivity this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final AlertDialogBuilder message;
        final AlertDialogBuilder positiveButton;
        final Dialog create;
        final SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        final Object object = sharedPreferencesHelper.getObject("changeLocale", Boolean.TYPE);
        if (null != object && Boolean.parseBoolean(object.toString())) {
            sharedPreferencesHelper.setObject("changeLocale", Boolean.TRUE);
            final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
            if (null == alertDialogBuilder || null == (message = alertDialogBuilder.setMessage(R.string.str_app_restart_change_language)) || null == (positiveButton = message.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.PreferenceActivityExternalSyntheticLambda0
                public void PreferenceActivityExternalSyntheticLambda0() {
                }

                public void onClick(DialogInterface dialogInterface, int i2) {
                    PreferenceActivity.onBackPressedlambda1(PreferenceActivity.this, dialogInterface, i2);
                }
            })) || null == (create = positiveButton.create())) {
                return;
            }
            create.show();
            return;
        }
        this.onBackPressed();
    }

    public static void onBackPressedlambda1(final PreferenceActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        onBackPressed();
    } 
    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        final PreferenceType preferenceType = mPreferenceType;
        Intrinsics.checkNotNull(preferenceType);
        outState.putInt(PreferenceActivity.STATE_PREFERENCE_TYPE, preferenceType.ordinal());
        super.onSaveInstanceState(outState);
    }
 
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        mPreferenceType = PreferenceType.values()[savedInstanceState.getInt(PreferenceActivity.STATE_PREFERENCE_TYPE, 0)];
    }
 
    public boolean onPreferenceStartScreen(final PreferenceFragmentCompat caller, final PreferenceScreen preferenceScreen) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(preferenceScreen, "preferenceScreen");
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        final Fragment preferenceFragment = this.getPreferenceFragment(mPreferenceType);
        final Bundle bundle = new Bundle();
        bundle.putString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT", preferenceScreen.getKey());
        final String str = PreferenceActivity.EXTRA_PREFERENCES;
        bundle.putInt(str, this.getIntent().getIntExtra(str, 0));
        Intrinsics.checkNotNull(preferenceFragment);
        preferenceFragment.setArguments(bundle);
        beginTransaction.add(R.id.content_frame, preferenceFragment, preferenceScreen.getKey());
        beginTransaction.addToBackStack(preferenceScreen.getKey());
        beginTransaction.commit();
        return true;
    }

    private Fragment getPreferenceFragment(final PreferenceType preferenceType) {
        if (preferenceType == PreferenceType.PRINT) {
            return new PrintSettingsFragment();
        }
        if (preferenceType == PreferenceType.PRINTER) {
            return new PrinterSettingsFragment();
        }
        if (preferenceType == PreferenceType.TRANSFER) {
            return new TransferSettingsFragment();
        }
        if (preferenceType == PreferenceType.USER_SETTINGS) {
            return new UserSettingsFragment();
        }
        if (preferenceType == PreferenceType.PRINTER_SERVICE) {
            return new PrinterServiceSettingsFragment();
        }
        return null;
    }

    private void exportSettings() {
        final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        if (!hasGrantedUri(sharedPreferencesHelper.getSettingsPath())) {
            final Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            this.startActivityForResult(intent, 40);
            return;
        }
        final SharedPreferencesHelper sharedPreferencesHelper2 = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper2);
        saveSettingsToSelectedFolder(sharedPreferencesHelper2.getSettingsPath(), PreferenceManager.getDefaultSharedPreferences(this));
    } 
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3) {
            if (40 != i2) {
                if (41 == i2 && null != intent) {
                    final ContentResolver contentResolver = this.getContentResolver();
                    final Uri data = intent.getData();
                    Intrinsics.checkNotNull(data);
                    final String fullFileName = getFullFileName(contentResolver, data);
                    Intrinsics.checkNotNullExpressionValue(fullFileName, "getFullFileName(...)");
                    if (StringsKt.contains (fullFileName, ".mssettings", false)) {
                        importData(this, this.getContentResolver(), intent.getData());
                        restart(this, false);
                        return;
                    } else {
                        Toast.makeText(this, this.getString(R.string.exp_89_wrong_file_type_mssettings), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                return;
            }
            if (null != intent) {
                final int flags = intent.getFlags() & 3;
                final ContentResolver contentResolver2 = this.getContentResolver();
                final Uri data2 = intent.getData();
                Intrinsics.checkNotNull(data2);
                contentResolver2.takePersistableUriPermission(data2, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                final Uri data3 = intent.getData();
                Intrinsics.checkNotNull(data3);
                sharedPreferencesHelper.saveSettingsPath(data3);
                saveSettingsToSelectedFolder(intent.getData(), PreferenceManager.getDefaultSharedPreferences(this));
            }
        }
    }
    public void onDestroy() {
        super.onDestroy();
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        if (null != alertDialogBuilder) {
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.dismiss();
            mAlertDialogBuilder = null;
        }
    }

    public static final class Companion {
        public   Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getFRAGMENT_TAG() {
            return FRAGMENT_TAG;
        }
    }
}
