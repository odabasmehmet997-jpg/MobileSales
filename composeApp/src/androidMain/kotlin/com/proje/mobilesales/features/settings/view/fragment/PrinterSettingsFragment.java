package com.proje.mobilesales.features.settings.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.BluetoothUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.FirebaseAnalyticsHelper;
import com.proje.mobilesales.features.settings.preferences.DevicePreference;
import com.proje.mobilesales.features.settings.view.activity.BluetoothDeviceListActivity;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;

public final class PrinterSettingsFragment extends BaseSettingsFragment {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_PRINTER_ONE = 999;
    public static final int REQUEST_PRINTER_TWO = 998;
    private final Preference.OnPreferenceClickListener onPreferenceClickOne = new Preference.OnPreferenceClickListener() {
        public boolean onPreferenceClick(final Preference preference) {
            final boolean onPreferenceClickOnelambda0;
            onPreferenceClickOnelambda0 = onPreferenceClickOnelambda0(PrinterSettingsFragment.this, preference);
            return onPreferenceClickOnelambda0;
        }
    };
    private final Preference.OnPreferenceClickListener onPreferenceClickTwo = new Preference.OnPreferenceClickListener() {
        public boolean onPreferenceClick(final Preference preference) {
            final boolean onPreferenceClickTwolambda1;
            onPreferenceClickTwolambda1 = onPreferenceClickTwolambda1(PrinterSettingsFragment.this, preference);
            return onPreferenceClickTwolambda1;
        }
    };
    private DevicePreference printerOnePref;
    private DevicePreference printerTwoPref;
    public void onCreatePreferences(final Bundle bundle, final String str) {
        Log.d(MobileSales.TAG, "onPreferenceStartScreen: " + str);
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        this.addPreferencesFromResource(arguments.getInt(PreferenceActivity.EXTRA_PREFERENCES));
        final DevicePreference devicePreference = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_printer_one_key));
        printerOnePref = devicePreference;
        Intrinsics.checkNotNull(devicePreference);
        devicePreference.setOnPreferenceClickListener(onPreferenceClickOne);
        final DevicePreference devicePreference2 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_printer_two_key));
        printerTwoPref = devicePreference2;
        Intrinsics.checkNotNull(devicePreference2);
        devicePreference2.setOnPreferenceClickListener(onPreferenceClickTwo);
    }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (998 == i2) {
            if (-1 == i3) {
                this.setDevicePref(printerTwoPref, intent, false, firebaseAnalytics);
            }
        } else if (999 == i2 && -1 == i3) {
            this.setDevicePref(printerOnePref, intent, true, firebaseAnalytics);
        }
    }
    private void setDevicePref(final DevicePreference devicePreference, final Intent intent, final boolean z, FirebaseAnalytics firebaseAnalytics) {
        Intrinsics.checkNotNull(intent);
        final BluetoothDeviceListActivity.Companion companion = BluetoothDeviceListActivity.Companion;
        final String stringExtra = intent.getStringExtra(companion.getEXTRA_DEVICE_NAME());
        final String stringExtra2 = intent.getStringExtra(companion.getEXTRA_DEVICE_ADDRESS());
        Intrinsics.checkNotNull(devicePreference);
        final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        final String format = String.format("%s\n%s", Arrays.copyOf(new Object[]{stringExtra, stringExtra2}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        devicePreference.setFullDeviceAddress(format);
        devicePreference.setmDeviceName(stringExtra);
        devicePreference.setmDeviceAddress(stringExtra2);
        BluetoothUtil.setPrinterAddress(z, stringExtra2);
        BluetoothUtil.setPrinterName(z, stringExtra);
        final FirebaseAnalyticsHelper.Companion companion2 = FirebaseAnalyticsHelper.Companion;
        final Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(firebaseAnalytics, "getInstance(...)");
        final FirebaseAnalyticsHelper companion3 = companion2.getInstance(firebaseAnalytics);
        Intrinsics.checkNotNull(stringExtra);
        companion3.logPrinterModelsFirebaseAnalyticsData(stringExtra);
    }
    public static boolean onPreferenceClickOnelambda0(final PrinterSettingsFragment this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (!Intrinsics.areEqual(preference, this0.printerOnePref)) {
            return false;
        }
        final Intent intent = new Intent(this0.getContext(), BluetoothDeviceListActivity.class);
        final DevicePreference devicePreference = this0.printerOnePref;
        Intrinsics.checkNotNull(devicePreference);
        devicePreference.setIntent(intent);
        this0.startActivityForResult(intent, 999);
        return true;
    }
    public static boolean onPreferenceClickTwolambda1(final PrinterSettingsFragment this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (!Intrinsics.areEqual(preference, this0.printerTwoPref)) {
            return false;
        }
        final Intent intent = new Intent(this0.getContext(), BluetoothDeviceListActivity.class);
        final DevicePreference devicePreference = this0.printerTwoPref;
        Intrinsics.checkNotNull(devicePreference);
        devicePreference.setIntent(intent);
        this0.startActivityForResult(intent, 998);
        return true;
    }
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
