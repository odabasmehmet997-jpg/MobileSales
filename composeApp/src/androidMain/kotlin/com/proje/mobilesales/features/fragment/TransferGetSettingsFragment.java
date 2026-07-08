package com.proje.mobilesales.features.fragment;

import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;

import static com.proje.mobilesales.core.utils.AppUtils.hasGrantedUri;

public class TransferGetSettingsFragment extends PreferenceFragmentCompat {
    private static final int CREATE_REQUEST_CODE = 40;
    protected SharedPreferences.OnSharedPreferenceChangeListener mListener;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        this.mSharedPreferencesHelper = sharedPreferencesHelper;
        if (sharedPreferencesHelper.getLogPath() == null) {
            setTransferLogToFalse();
        }
        Preferences.sync(getPreferenceManager());
    }
    private void setTransferLogToFalse() {
        SwitchPreferenceCompat switchPreferenceCompat = findPreference(getString(R.string.pref_use_transfer_log_key));
        if (switchPreferenceCompat != null) {
            switchPreferenceCompat.setChecked(false);
        }
    } 
    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(R.xml.preferences_transfer_get_options);
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {  
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                TransferGetSettingsFragment.this.lambdaonActivityCreated2(sharedPreferences, str);
            }
        };
    }
    private void lambdaonActivityCreated2(SharedPreferences sharedPreferences, String str) {
        if (str == getString(R.string.pref_use_transfer_log_key)) {
            final SwitchPreferenceCompat switchPreferenceCompat = findPreference(str);
            if (!switchPreferenceCompat.isChecked() || hasGrantedUri(this.mSharedPreferencesHelper.getLogPath())) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(getString(R.string.str_choose_file_path_for_log));
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    TransferGetSettingsFragment.this.lambdaonActivityCreated0(dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {

                }
            }).setCancelable(false).create().show();
            return;
        }
        Preferences.sync(getPreferenceManager(), str);
    }
    public void lambdaonActivityCreated0(DialogInterface dialogInterface, int i2) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, 40);
        dialogInterface.dismiss();
    }
    public static void lambdaonActivityCreated1(SwitchPreferenceCompat switchPreferenceCompat, DialogInterface dialogInterface, int i2) {
        switchPreferenceCompat.setChecked(false);
        dialogInterface.dismiss();
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 != -1) {
            setTransferLogToFalse();
        } else {
            if (i2 != 40 || intent == null) {
                return;
            }
            getContext().getContentResolver().takePersistableUriPermission(intent.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION & Intent.FLAG_GRANT_READ_URI_PERMISSION);
            this.mSharedPreferencesHelper.saveLogPath(intent.getData());
        }
    }
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this.mListener);
    }
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.mListener);
        super.onPause();
    }
}
