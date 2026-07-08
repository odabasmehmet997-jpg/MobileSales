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
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.PrintDestination;
import com.proje.mobilesales.core.enums.PrintLanguage;
import com.proje.mobilesales.core.preferences.EditTextIntegerPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.FilePath;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

import java.util.List;
import java.util.ListIterator;

import static com.proje.mobilesales.core.utils.AppUtils.hasGrantedUri;

public final class PrintSettingsFragment extends BaseSettingsFragment {
    private static final int ACTIVITY_CHOOSE_FILE = 3;
    private static final int CREATE_REQUEST_CODE = 40;
    public static final Companion Companion = new Companion(null);
    private Preference mPreferenceExportPath;
    private EditTextIntegerPref mPreferenceFontHeight;
    private ListPreference mPreferenceFontId;
    private EditTextIntegerPref mPreferenceFontMargin;
    private EditTextIntegerPref mPreferenceFontSpace;
    private EditTextIntegerPref mPreferenceFontWidth;
    private ListPreference mPreferencePrintDestination;
    private ListPreference mPreferencePrintLanguage;
    private ListPreference mPreferencePrintType;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    @SuppressLint("RestrictedApi")
    public Fragment getCallbackFragment() {
        return this;
    }
    public SharedPreferencesHelper getMSharedPreferencesHelper() {
        return mSharedPreferencesHelper;
    }
    public void setMSharedPreferencesHelper(final SharedPreferencesHelper sharedPreferencesHelper) {
        mSharedPreferencesHelper = sharedPreferencesHelper;
    }
    public void onCreatePreferences(final Bundle bundle, final String str) {
        final Bundle arguments = this.getArguments();
        Intrinsics.checkNotNull(arguments);
        this.setPreferencesFromResource(arguments.getInt(PreferenceActivity.EXTRA_PREFERENCES), str);
        this.initPreference(str);
    }
    private void initPreference(final String str) {
        final Preference findPreference;
        mSharedPreferencesHelper = new SharedPreferencesHelper();
        if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_general_key))) {
            mPreferencePrintLanguage = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_language_key));
            mPreferencePrintType = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_type_key));
            mPreferencePrintDestination = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_destination_key));
            mPreferenceExportPath = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_file_export_path_key));
            mPreferenceFontId = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_pplz_font_id_key));
            mPreferenceFontWidth = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_pplz_font_width_key));
            mPreferenceFontHeight = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_pplz_font_height_key));
            mPreferenceFontSpace = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_pplz_font_space_key));
            mPreferenceFontMargin = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_pplz_font_margin_key));
            final ListPreference listPreference = mPreferenceFontId;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.proje.mobilesales.features.settings.view.fragment.PrintSettingsFragmentExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(final Preference preference, final Object obj) {
                    final boolean initPreferencelambda0;
                    initPreferencelambda0 = initPreferencelambda0(PrintSettingsFragment.this, preference, obj);
                    return initPreferencelambda0;
                }
            });
            final ListPreference listPreference2 = mPreferencePrintLanguage;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference, final Object obj) {
                    final boolean initPreferencelambda1;
                    initPreferencelambda1 = PrintSettingsFragment.initPreferencelambda1(PrintSettingsFragment.this, preference, obj);
                    return initPreferencelambda1;
                }
            });
            final ListPreference listPreference3 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference3);
            listPreference3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference, final Object obj) {
                    final boolean initPreferencelambda4;
                    initPreferencelambda4 = initPreferencelambda4(PrintSettingsFragment.this, preference, obj);
                    return initPreferencelambda4;
                }
            });
            final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            final Uri printToFilePath = sharedPreferencesHelper.getPrintToFilePath();
            final ListPreference listPreference4 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference4);
            final String value = listPreference4.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            final PrintDestination valueOf = PrintDestination.valueOf(value);
            final PrintDestination printDestination = PrintDestination.FILE;
            if (valueOf == printDestination && null != printToFilePath) {
                final Context context = this.getContext();
                Intrinsics.checkNotNull(context);
                final DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, printToFilePath);
                Intrinsics.checkNotNull(fromTreeUri);
                if (fromTreeUri.isDirectory()) {
                    final Preference preference = mPreferenceExportPath;
                    Intrinsics.checkNotNull(preference);
                    final Context context2 = this.getContext();
                    final SharedPreferencesHelper sharedPreferencesHelper2 = mSharedPreferencesHelper;
                    Intrinsics.checkNotNull(sharedPreferencesHelper2);
                    preference.setSummary(FilePath.getPath(context2, sharedPreferencesHelper2.getPrintToFilePath()));
                    this.setPreferenceVisibility(mPreferenceExportPath, Preferences.getPrintDestination(this.getContext()) != printDestination);
                    this.setOptionsVisibility(Preferences.getPrintLanguage(this.getContext()) == PrintLanguage.STANDARD);
                }
            }
            final ListPreference listPreference5 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference5);
            listPreference5.setValueIndex(0);
            final ListPreference listPreference6 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference6);
            final ListPreference listPreference7 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference7);
            listPreference6.setSummary(listPreference7.getEntry());
            this.setPreferenceVisibility(mPreferenceExportPath, false);
            final SharedPreferencesHelper sharedPreferencesHelper3 = mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper3);
            sharedPreferencesHelper3.removePrintToFilePath();
            this.setPreferenceVisibility(mPreferenceExportPath, Preferences.getPrintDestination(this.getContext()) != printDestination);
            this.setOptionsVisibility(Preferences.getPrintLanguage(this.getContext()) == PrintLanguage.STANDARD);
        }
        if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_order_key))) {
            final ListPreference listPreference8 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_order_start_image_key));
            final ListPreference listPreference9 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_order_end_image_key));
            Intrinsics.checkNotNull(listPreference8);
            final PrintLanguage printLanguage = Preferences.getPrintLanguage(this.getContext());
            final PrintLanguage printLanguage2 = PrintLanguage.STANDARD;
            listPreference8.setVisible(printLanguage == printLanguage2);
            Intrinsics.checkNotNull(listPreference9);
            listPreference9.setVisible(Preferences.getPrintLanguage(this.getContext()) == printLanguage2);
        }
        if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_dispatch_key))) {
            final ListPreference listPreference10 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_despatch_start_image_key));
            final ListPreference listPreference11 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_despatch_end_image_key));
            Intrinsics.checkNotNull(listPreference10);
            final PrintLanguage printLanguage3 = Preferences.getPrintLanguage(this.getContext());
            final PrintLanguage printLanguage4 = PrintLanguage.STANDARD;
            listPreference10.setVisible(printLanguage3 == printLanguage4);
            Intrinsics.checkNotNull(listPreference11);
            listPreference11.setVisible(Preferences.getPrintLanguage(this.getContext()) == printLanguage4);
        }
        if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_invoice_key))) {
            final ListPreference listPreference12 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_invoice_start_image_key));
            final ListPreference listPreference13 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_invoice_end_image_key));
            Intrinsics.checkNotNull(listPreference12);
            final PrintLanguage printLanguage5 = Preferences.getPrintLanguage(this.getContext());
            final PrintLanguage printLanguage6 = PrintLanguage.STANDARD;
            listPreference12.setVisible(printLanguage5 == printLanguage6);
            Intrinsics.checkNotNull(listPreference13);
            listPreference13.setVisible(Preferences.getPrintLanguage(this.getContext()) == printLanguage6);
        }
        if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_retailinvoice_key))) {
            final ListPreference listPreference14 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_retail_invoice_start_image_key));
            final ListPreference listPreference15 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_retail_invoice_end_image_key));
            Intrinsics.checkNotNull(listPreference14);
            final PrintLanguage printLanguage7 = Preferences.getPrintLanguage(this.getContext());
            final PrintLanguage printLanguage8 = PrintLanguage.STANDARD;
            listPreference14.setVisible(printLanguage7 == printLanguage8);
            Intrinsics.checkNotNull(listPreference15);
            listPreference15.setVisible(Preferences.getPrintLanguage(this.getContext()) == printLanguage8);
        }
        if (new SharedPreferencesHelper(this.getContext()).getErpRights().getErpType() == ErpType.NETSIS) {
            if (null == str && null != (findPreference = getPreferenceScreen().findPreference(getString(R.string.pref_print_screen_retailinvoice_key)))) {
                findPreference.setVisible(false);
            }
            if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_dispatch_key))) {
                final ListPreference listPreference16 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_screen_dispatch_list_printed_matter_key));
                Intrinsics.checkNotNull(listPreference16);
                listPreference16.setEntryValues(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_values_netsis));
                listPreference16.setEntries(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_entries_netsis));
                listPreference16.setValueIndex(0);
                listPreference16.setVisible(false);
            }
            if (null != str && Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_order_key))) {
                final ListPreference listPreference17 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_screen_order_list_printed_matter_key));
                Intrinsics.checkNotNull(listPreference17);
                listPreference17.setEntryValues(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_values_netsis));
                listPreference17.setEntries(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_entries_netsis));
                listPreference17.setValueIndex(0);
                listPreference17.setVisible(false);
            }
            if (null == str || !Intrinsics.areEqual(str, this.getString(R.string.pref_print_screen_invoice_key))) {
                return;
            }
            final ListPreference listPreference18 = this.getPreferenceScreen().findPreference(this.getString(R.string.pref_print_screen_invoice_list_printed_matter_key));
            Intrinsics.checkNotNull(listPreference18);
            listPreference18.setEntryValues(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_values_netsis));
            listPreference18.setEntries(this.getResources().getStringArray(R.array.pref_print_screen_list_printed_matter_entries_netsis));
            listPreference18.setValueIndex(0);
            listPreference18.setVisible(false);
        }
    }
    public static boolean initPreferencelambda0(final PrintSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "preference");
        this0.setFontSetValue(((ListPreference) preference).findIndexOfValue((String) obj));
        return true;
    }
    public boolean initPreferencelambda4(PrintSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final String str = (String) obj;
        final Preference preference2 = this0.mPreferenceExportPath;
        Intrinsics.checkNotNull(str);
        final PrintDestination valueOf = PrintDestination.valueOf(str);
        final PrintDestination printDestination = PrintDestination.FILE;
        this0.setPreferenceVisibility(preference2, valueOf == printDestination);
        if (PrintDestination.valueOf(str) == printDestination) {
            final SharedPreferencesHelper sharedPreferencesHelper = this0.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            if (!hasGrantedUri(sharedPreferencesHelper.getPrintToFilePath())) {
                final Context context = this0.getContext();
                Intrinsics.checkNotNull(context);
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(this0.getString(R.string.str_choose_file_path_for_print));
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 
                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                        initPreferencelambda4lambda2(PrintSettingsFragment.this, dialogInterface, i2);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
                    public void onClick(final DialogInterface dialogInterface, final int i2) {
                        initPreferencelambda4lambda3(PrintSettingsFragment.this, dialogInterface, i2);
                    }
                }).setCancelable(false).create().show();
            }
        } else {
            final Preference preference3 = this0.mPreferenceExportPath;
            Intrinsics.checkNotNull(preference3);
            preference3.setSummary("");
            final SharedPreferencesHelper sharedPreferencesHelper2 = this0.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            sharedPreferencesHelper2.removePrintToFilePath();
        }
        return true;
    }
    public static void initPreferencelambda4lambda2(final PrintSettingsFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        this0.startActivityForResult(intent, 40);
        dialog.dismiss();
    }
    public static void initPreferencelambda4lambda3(final PrintSettingsFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final ListPreference listPreference = this0.mPreferencePrintDestination;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setValueIndex(0);
        final ListPreference listPreference2 = this0.mPreferencePrintDestination;
        Intrinsics.checkNotNull(listPreference2);
        final ListPreference listPreference3 = this0.mPreferencePrintDestination;
        Intrinsics.checkNotNull(listPreference3);
        listPreference2.setSummary(listPreference3.getEntry());
        this0.setPreferenceVisibility(this0.mPreferenceExportPath, false);
        final Preference preference = this0.mPreferenceExportPath;
        Intrinsics.checkNotNull(preference);
        preference.setSummary("");
        final SharedPreferencesHelper sharedPreferencesHelper = this0.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        sharedPreferencesHelper.removePrintToFilePath();
        dialog.dismiss();
    }
    private void setFontSetValue(final int i2) {
        List emptyList;
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        final String str = context.getResources().getStringArray(R.array.pref_pplz_font_values)[i2];
        Intrinsics.checkNotNull(str);
        final List<String> split = new Regex(",").split(str, 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        final EditTextIntegerPref editTextIntegerPref = mPreferenceFontWidth;
        Intrinsics.checkNotNull(editTextIntegerPref);
        editTextIntegerPref.setText(strArr[0]);
        final EditTextIntegerPref editTextIntegerPref2 = mPreferenceFontHeight;
        Intrinsics.checkNotNull(editTextIntegerPref2);
        editTextIntegerPref2.setText(strArr[1]);
        final EditTextIntegerPref editTextIntegerPref3 = mPreferenceFontSpace;
        Intrinsics.checkNotNull(editTextIntegerPref3);
        editTextIntegerPref3.setText(strArr[2]);
    }
    private void setOptionsVisibility(final boolean z) {
        this.setPreferenceVisibility(mPreferenceFontId, z);
        this.setPreferenceVisibility(mPreferenceFontHeight, z);
        this.setPreferenceVisibility(mPreferenceFontWidth, z);
        this.setPreferenceVisibility(mPreferenceFontSpace, z);
        this.setPreferenceVisibility(mPreferenceFontMargin, z);
    }
    private void setPreferenceVisibility(final Preference preference, final boolean z) {
        Intrinsics.checkNotNull(preference);
        preference.setVisible(z);
    }
    @SuppressLint("WrongConstant")
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 != i3) {
            final ListPreference listPreference = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValueIndex(0);
            final ListPreference listPreference2 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference2);
            final ListPreference listPreference3 = mPreferencePrintDestination;
            Intrinsics.checkNotNull(listPreference3);
            listPreference2.setSummary(listPreference3.getEntry());
            this.setPreferenceVisibility(mPreferenceExportPath, false);
            final Preference preference = mPreferenceExportPath;
            Intrinsics.checkNotNull(preference);
            preference.setSummary("");
            final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            sharedPreferencesHelper.removePrintToFilePath();
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
        contentResolver.takePersistableUriPermission(data, flags);
        final SharedPreferencesHelper sharedPreferencesHelper2 = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper2);
        final Uri data2 = intent.getData();
        Intrinsics.checkNotNull(data2);
        sharedPreferencesHelper2.savePrintToFilePath(data2);
        final Preference preference2 = mPreferenceExportPath;
        Intrinsics.checkNotNull(preference2);
        final Context context2 = this.getContext();
        final SharedPreferencesHelper sharedPreferencesHelper3 = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper3);
        preference2.setSummary(FilePath.getPath(context2, sharedPreferencesHelper3.getPrintToFilePath()));
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
