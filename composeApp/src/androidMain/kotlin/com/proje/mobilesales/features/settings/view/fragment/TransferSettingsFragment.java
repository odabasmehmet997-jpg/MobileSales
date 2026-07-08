package com.proje.mobilesales.features.settings.view.fragment;

import android.R;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.hasGrantedUri;

public final class TransferSettingsFragment extends BaseSettingsFragment {
    private static final int CREATE_REQUEST_CODE = 40;
    public static final Companion Companion = new Companion(null);
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private SwitchPreferenceCompat transferLogPreference;
    @SuppressLint("RestrictedApi")
    public Fragment getCallbackFragment() {
        return this;
    }
    public void onCreatePreferences(final Bundle bundle, final String str) {
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        this.setPreferencesFromResource(arguments.getInt(PreferenceActivity.EXTRA_PREFERENCES), str);
        mSharedPreferencesHelper = new SharedPreferencesHelper();
        final SwitchPreferenceCompat switchPreferenceCompat = this.findPreference(this.getString(R.string.pref_use_transfer_log_key));
        transferLogPreference = switchPreferenceCompat;
        if (null != switchPreferenceCompat) {
            final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            if (null == sharedPreferencesHelper.getLogPath()) {
                final SwitchPreferenceCompat switchPreferenceCompat2 = transferLogPreference;
                Intrinsics.checkNotNull(switchPreferenceCompat2);
                switchPreferenceCompat2.setChecked(false);
            }
            final SwitchPreferenceCompat switchPreferenceCompat3 = transferLogPreference;
            Intrinsics.checkNotNull(switchPreferenceCompat3);
            switchPreferenceCompat3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {  
                public boolean onPreferenceChange(final Preference preference, final Object obj) {
                    final boolean onCreatePreferenceslambda2;
                    onCreatePreferenceslambda2 = onCreatePreferenceslambda2(TransferSettingsFragment.this, preference, obj);
                    return onCreatePreferenceslambda2;
                }
            });
        }
    }
    public static boolean onCreatePreferenceslambda2(TransferSettingsFragment this0, final Preference preference, final Object newValue) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        if (!((Boolean) newValue).booleanValue()) {
            return true;
        }
        final SharedPreferencesHelper sharedPreferencesHelper = this0.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        if (hasGrantedUri(sharedPreferencesHelper.getLogPath())) {
            return true;
        }
        final Context context = this0.getContext();
        Intrinsics.checkNotNull(context);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(this0.getString(R.string.str_choose_file_path_for_log));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onCreatePreferenceslambda2lambda0(TransferSettingsFragment.this, dialogInterface, i2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { 
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onCreatePreferenceslambda2lambda1(TransferSettingsFragment.this, dialogInterface, i2);
            }
        }).setCancelable(false).create().show();
        return true;
    }
    public static void onCreatePreferenceslambda2lambda0(final TransferSettingsFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        this0.startActivityForResult(intent, 40);
        dialog.dismiss();
    }
    public static void onCreatePreferenceslambda2lambda1(final TransferSettingsFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final SwitchPreferenceCompat switchPreferenceCompat = this0.transferLogPreference;
        Intrinsics.checkNotNull(switchPreferenceCompat);
        switchPreferenceCompat.setChecked(false);
        dialog.dismiss();
    }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 != i3) {
            final SwitchPreferenceCompat switchPreferenceCompat = transferLogPreference;
            if (null != switchPreferenceCompat) {
                Intrinsics.checkNotNull(switchPreferenceCompat);
                switchPreferenceCompat.setChecked(false);
                return;
            }
            return;
        }
        if (40 != i2 || null == intent) {
            return;
        }
        final int flags = intent.getFlags() & 3;
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri data = intent.getData();
        Intrinsics.checkNotNull(data);
        contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        final Uri data2 = intent.getData();
        Intrinsics.checkNotNull(data2);
        sharedPreferencesHelper.saveLogPath(data2);
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
    }
}
