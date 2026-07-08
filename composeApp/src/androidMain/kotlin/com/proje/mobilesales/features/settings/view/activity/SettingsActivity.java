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
import android.view.View;
import android.widget.Toast;
import androidx.preference.PreferenceManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.settings.model.enums.PreferenceType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.*;

public final class SettingsActivity extends BaseErpActivity {
    private static final int CREATE_REQUEST_CODE = 40;
    public static final Companion Companion = new Companion(null);
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
         getActivityComponent().inject(this);
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
         this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
         this.mSharedPreferencesHelper = new SharedPreferencesHelper(this);
         setContentView(R.layout.activity_settings);
         setToolbar();
         findViewById(R.id.drawer_printer_settings).setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.SettingsActivityExternalSyntheticLambda1
            public void SettingsActivityExternalSyntheticLambda1() {
            } 
            public void onClick(View view) {
                SettingsActivity.onCreatelambda0(SettingsActivity.this, view);
            }
        });
         findViewById(R.id.drawer_print_settings).setOnClickListener(new View.OnClickListener() {
            public void SettingsActivityExternalSyntheticLambda2() {
            }

            public void onClick(View view) {
                SettingsActivity.onCreatelambda1(SettingsActivity.this, view);
            }
        });
         findViewById(R.id.drawer_transfer_settings).setOnClickListener(new View.OnClickListener() {
            public void SettingsActivityExternalSyntheticLambda3() {
            }
            public void onClick(View view) {
                SettingsActivity.onCreatelambda2(SettingsActivity.this, view);
            }
        });
         findViewById(R.id.drawer_printer_service_settings).setOnClickListener(new View.OnClickListener() {
            public void SettingsActivityExternalSyntheticLambda4() {
            }
            public void onClick(View view) {
                SettingsActivity.onCreatelambda3(SettingsActivity.this, view);
            }
        });
    }
    public static void onCreatelambda0(SettingsActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityType(R.string.activity_title_printer_settings, R.xml.preference_printer_settings, PreferenceType.PRINTER);
    }
    public static void onCreatelambda1(SettingsActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityType(R.string.activity_title_print_settings, R.xml.preference_print_settings_new, PreferenceType.PRINT);
    }
    public static void onCreatelambda2(SettingsActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityType(R.string.activity_title_transfer_get_settings, R.xml.preferences_transfer_get_options, PreferenceType.TRANSFER);
    }
    public static void onCreatelambda3(SettingsActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startActivityType(R.string.activity_title_printer_service_settings, R.xml.preference_printer_service_settings, PreferenceType.PRINTER_SERVICE);
    }
    private void startActivityType(int i2, int i3, PreferenceType preferenceType) {
        startActivity(new Intent(this, PreferenceActivity.class).putExtra(PreferenceActivity.EXTRA_TITLE, i2).putExtra(PreferenceActivity.EXTRA_PREFERENCES, i3).putExtra(PreferenceActivity.EXTRA_PREFERENCE_TYPE, preferenceType.ordinal()));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialogBuilder message;
        AlertDialogBuilder negativeButton;
        AlertDialogBuilder positiveButton;
        Dialog create;
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_export_settings /* 2131297336 */:
                exportSettings();
                break;
            case R.id.menu_reset_all_settings /* 2131297382 */:
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
                if (null != alertDialogBuilder && null != (message = alertDialogBuilder.setMessage(R.string.str_reset_all_settings_confirm)) && null != (negativeButton = message.setNegativeButton(R.string.cancel, null)) && null != (positiveButton = negativeButton.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.activity.SettingsActivityExternalSyntheticLambda0
                    public void SettingsActivityExternalSyntheticLambda0() {
                    }

                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                        onOptionsItemSelectedlambda4(SettingsActivity.this, dialogInterface, i2);
                    }
                })) && null != (create = positiveButton.create())) {
                    create.show();
                    break;
                }
                break;
            case R.id.menu_reset_settings_path /* 2131297384 */:
                SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                sharedPreferencesHelper.removeSettingsPath();
                Toast.makeText(this, getString(R.string.str_done), Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_share_settings /* 2131297404 */:
                shareSettings(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void onOptionsItemSelectedlambda4(SettingsActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Preferences.resetWithoutConnectionSettings(this0);
        restart(this0, false);
    }
    private void exportSettings() {
        SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        if (!hasGrantedUri(sharedPreferencesHelper.getSettingsPath())) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivityForResult(intent, 40);
            return;
        }
        SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper2);
        Uri settingsPath = sharedPreferencesHelper2.getSettingsPath();
        if (null != settingsPath) {
            saveSettingsToSelectedFolder(settingsPath, PreferenceManager.getDefaultSharedPreferences(this));
        }
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3 && 40 == i2 && null != intent) {
            int flags = intent.getFlags() & 3;
            ContentResolver contentResolver = getContentResolver();
            Uri data = intent.getData();
            Intrinsics.checkNotNull(data);
            assert data != null;
            contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            Uri data2 = intent.getData();
            Intrinsics.checkNotNull(data2);
            assert data2 != null;
            sharedPreferencesHelper.saveSettingsPath(data2);
            saveSettingsToSelectedFolder(intent.getData(), PreferenceManager.getDefaultSharedPreferences(this));
        }
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
