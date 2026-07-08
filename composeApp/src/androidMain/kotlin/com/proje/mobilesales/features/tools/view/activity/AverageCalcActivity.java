package com.proje.mobilesales.features.tools.view.activity;

import android.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivityPreferences;
import com.proje.mobilesales.core.preferences.LogoDatePreference;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.settings.interfaces.PreferenceClear;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AverageCalcActivity extends BaseErpActivityPreferences implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceClear {
    private static final int CALCULATE = 0;
    public static final Companion Companion = new Companion(null);
    private LogoDatePreference dpTarih1;
    private LogoDatePreference dpTarih10;
    private LogoDatePreference dpTarih2;
    private LogoDatePreference dpTarih3;
    private LogoDatePreference dpTarih4;
    private LogoDatePreference dpTarih5;
    private LogoDatePreference dpTarih6;
    private LogoDatePreference dpTarih7;
    private LogoDatePreference dpTarih8;
    private LogoDatePreference dpTarih9;
    private SharedPreferences f1281sp;
    public SharedPreferences getSp() {
        return f1281sp;
    }
    public void setSp(final SharedPreferences sharedPreferences) {
        f1281sp = sharedPreferences;
    }
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.addPreferencesFromResource(R.xml.preference_average_calc);
        f1281sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.createControls();
        final LogoDatePreference logoDatePreference = dpTarih1;
        Intrinsics.checkNotNull(logoDatePreference);
        logoDatePreference.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference2 = dpTarih2;
        Intrinsics.checkNotNull(logoDatePreference2);
        logoDatePreference2.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference3 = dpTarih3;
        Intrinsics.checkNotNull(logoDatePreference3);
        logoDatePreference3.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference4 = dpTarih4;
        Intrinsics.checkNotNull(logoDatePreference4);
        logoDatePreference4.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference5 = dpTarih5;
        Intrinsics.checkNotNull(logoDatePreference5);
        logoDatePreference5.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference6 = dpTarih6;
        Intrinsics.checkNotNull(logoDatePreference6);
        logoDatePreference6.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference7 = dpTarih7;
        Intrinsics.checkNotNull(logoDatePreference7);
        logoDatePreference7.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference8 = dpTarih8;
        Intrinsics.checkNotNull(logoDatePreference8);
        logoDatePreference8.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference9 = dpTarih9;
        Intrinsics.checkNotNull(logoDatePreference9);
        logoDatePreference9.setDate(DateAndTimeUtils.nowDate());
        final LogoDatePreference logoDatePreference10 = dpTarih10;
        Intrinsics.checkNotNull(logoDatePreference10);
        logoDatePreference10.setDate(DateAndTimeUtils.nowDate());
        Preferences.initSummary(dpTarih1);
        Preferences.initSummary(dpTarih2);
        Preferences.initSummary(dpTarih3);
        Preferences.initSummary(dpTarih4);
        Preferences.initSummary(dpTarih5);
        Preferences.initSummary(dpTarih6);
        Preferences.initSummary(dpTarih7);
        Preferences.initSummary(dpTarih8);
        Preferences.initSummary(dpTarih9);
        Preferences.initSummary(dpTarih10);
    }
    private void createControls() {
        final Preference findPreference = this.findPreference("dpTarih1");
        Intrinsics.checkNotNull(findPreference, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih1 = (LogoDatePreference) findPreference;
        final Preference findPreference2 = this.findPreference("dpTarih2");
        Intrinsics.checkNotNull(findPreference2, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih2 = (LogoDatePreference) findPreference2;
        final Preference findPreference3 = this.findPreference("dpTarih3");
        Intrinsics.checkNotNull(findPreference3, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih3 = (LogoDatePreference) findPreference3;
        final Preference findPreference4 = this.findPreference("dpTarih4");
        Intrinsics.checkNotNull(findPreference4, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih4 = (LogoDatePreference) findPreference4;
        final Preference findPreference5 = this.findPreference("dpTarih5");
        Intrinsics.checkNotNull(findPreference5, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih5 = (LogoDatePreference) findPreference5;
        final Preference findPreference6 = this.findPreference("dpTarih6");
        Intrinsics.checkNotNull(findPreference6, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih6 = (LogoDatePreference) findPreference6;
        final Preference findPreference7 = this.findPreference("dpTarih7");
        Intrinsics.checkNotNull(findPreference7, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih7 = (LogoDatePreference) findPreference7;
        final Preference findPreference8 = this.findPreference("dpTarih8");
        Intrinsics.checkNotNull(findPreference8, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih8 = (LogoDatePreference) findPreference8;
        final Preference findPreference9 = this.findPreference("dpTarih9");
        Intrinsics.checkNotNull(findPreference9, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih9 = (LogoDatePreference) findPreference9;
        final Preference findPreference10 = this.findPreference("dpTarih10");
        Intrinsics.checkNotNull(findPreference10, "null cannot be cast to non-null type com.proje.mobilesales.core.preferences.LogoDatePreference");
        dpTarih10 = (LogoDatePreference) findPreference10;
    }
    protected void onResume() {
        super.onResume();
        this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    protected void onPause() {
        super.onPause();
        this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String str) {
        Preferences.updatePrefSummary(this.findPreference(str));
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, this.getString(R.string.str_calculate)).setIcon(R.drawable.ic_menu_recent_history);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (0 == itemId) {
            this.OVHesapla();
        } else if (16908332 == itemId) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        super.onBackPressed();
        this.clearEditor();
        this.finish();
    }
    private void OVHesapla() {
        final Calendar calendar = Calendar.getInstance();
        double[] dArr = new double[10];
        final int[] iArr = new int[10];
        int i2 = 0;
        double d2 = 0.0d;
        int i3 = 0;
        while (true) {
            if (10 <= i3) {
                break;
            }
            final SharedPreferences sharedPreferences = f1281sp;
            final double convertStringToDouble = StringUtils.convertStringToDouble(null != sharedPreferences ? sharedPreferences.getString("etTutar" + (i3 + 1), "0") : null);
            if (0.0d < convertStringToDouble) {
                d2 += convertStringToDouble;
                dArr[i3] = convertStringToDouble;
                final SharedPreferences sharedPreferences2 = f1281sp;
                Intrinsics.checkNotNull(sharedPreferences2);
                calendar.setTime(DateAndTimeUtils.toDate(sharedPreferences2.getString("dpTarih" + (i3 + 1), DateAndTimeUtils.nowDate())));
                iArr[i3] = calendar.get(6);
            }
            i3++;
        }
        calendar.setTime(DateAndTimeUtils.toDate(DateAndTimeUtils.nowDate()));
        double d3 = 0.0d;
        for (int i4 = 10; i2 < i4; i4 = 10) {
            d3 += dArr[i2] * (iArr[i2] - calendar.get(6));
            i2++;
            dArr = dArr;
        }
        if (0.0d == d3 && 0.0d == d2) {
            return;
        }
        int i5 = (int) (d3 / d2);
        final Date date = DateAndTimeUtils.toDate(DateAndTimeUtils.nowDate());
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.add(5, i5);
        final String format = simpleDateFormat.format(calendar2.getTime());
        if (0 > i5) {
            i5 *= -1;
        }
        final View inflate = LayoutInflater.from(this).inflate(R.layout.avaragecalcdialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getString(R.string.str_average_expiry_information));
        builder.setView(inflate);
        final View findViewById = inflate.findViewById(R.id.tvHistory);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = inflate.findViewById(R.id.tvTotal);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = inflate.findViewById(R.id.tvDay);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        ((AppCompatTextView) findViewById).setText(format);
        ((AppCompatTextView) findViewById2).setText(String.valueOf(d2));
        ((AppCompatTextView) findViewById3).setText(String.valueOf(i5));
        builder.show();
    }
    public void clearEditor() {
        final SharedPreferences sharedPreferences = f1281sp;
        Intrinsics.checkNotNull(sharedPreferences);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        for (int i2 = 1; 11 > i2; i2++) {
            edit.remove("etTutar" + i2);
            edit.remove("dpTarih" + i2);
        }
        edit.apply();
    }
    public static final class Companion {
        public    Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
