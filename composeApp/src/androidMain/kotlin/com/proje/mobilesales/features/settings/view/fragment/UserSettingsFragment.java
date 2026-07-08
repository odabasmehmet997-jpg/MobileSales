package com.proje.mobilesales.features.settings.view.fragment;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreferenceCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.di.ActivityComponent;
import com.proje.mobilesales.core.netsis.NetsisDataConverter;
import com.proje.mobilesales.core.netsis.NetsisDataHeader;
import com.proje.mobilesales.core.netsis.NetsisRestPublicApi;
import com.proje.mobilesales.core.netsis.NetsisRestPublicFactory;
import com.proje.mobilesales.core.preferences.BranchPreferences;
import com.proje.mobilesales.core.preferences.EditTextIntegerPref;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.AesEncryption;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.LanguageHelper;
import com.proje.mobilesales.core.utils.ObservableUtils;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.NetsisBranch;
import com.proje.mobilesales.features.settings.view.activity.PreferenceActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;


public final class UserSettingsFragment extends InjectableSettingFragment {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_BRANCH_CHOICE = "state:mBranchChoice";
    private static final String TAG;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private AlertDialogBuilder<?> mBranchAlertDialogBuilder;
    private int mBranchChoice;
    private NetsisRestPublicApi mNetsisRestPublicApi;
    private ListPreference mPrefCommunicationType;
    private ListPreference mPrefErpType;
    private ListPreference mPrefLanguage;
    private PreferenceCategory mPrefNetsis;
    private BranchPreferences mPrefNetsisBranchCode;
    private EditTextPreference mPrefNetsisDbName;
    private EditTextPreference mPrefNetsisDbPassword;
    private EditTextPreference mPrefNetsisDbUserName;
    private ListPreference mPrefNetsisDbtype;
    private Preference mPrefNetsisPing;
    private EditTextPreference mPrefNetsisServerAddress;
    private EditTextPreference mPrefNetsisUserName;
    private EditTextPreference mPrefNetsisUserPassword;
    private PreferenceCategory mPrefTiger;
    private EditTextIntegerPref mPrefTigerFirmNumber;
    private EditTextPreference mPrefTigerServerAddress;
    private EditTextPreference mPrefTigerWcfSecurityCode;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;

    public Fragment getCallbackFragment() {
        return this;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        if (null != progressDialogBuilder) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }

    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        mProgressDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        if (null != alertDialogBuilder) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        mAlertDialogBuilder = alertDialogBuilder;
    }
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final ActivityComponent activityComponent = this.getActivityComponent();
        if (null != activityComponent) {
            activityComponent.inject(this);
        }
         this.setHasOptionsMenu(true);
        final Activity activity = ContextUtils.getmActivity();
        if (activity instanceof BaseInjectableActivity baseInjectableActivity) {
            mBranchAlertDialogBuilder = new AlertDialogBuilder.Impl(this.requireContext(), baseInjectableActivity);
            this.setMAlertDialogBuilder(new AlertDialogBuilder.Impl(this.requireContext(), baseInjectableActivity));
            this.setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(this.requireContext(), baseInjectableActivity));
        }
        mBranchChoice = null != bundle ? bundle.getInt(UserSettingsFragment.STATE_BRANCH_CHOICE, 0) : 0;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (R.id.menu_use_connection_key == item.getItemId()) {
            this.showConnectionKeyDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putInt(UserSettingsFragment.STATE_BRANCH_CHOICE, mBranchChoice);
        super.onSaveInstanceState(outState);
    }
    public void onCreatePreferences(final Bundle bundle, final String str) {
        this.setPreferencesFromResource(this.requireArguments().getInt(PreferenceActivity.EXTRA_PREFERENCES), str);
        this.initPreference();
    }

    
    public boolean createService(final String str) {
        if (TextUtils.isEmpty(Preferences.getNetsisServerAddress(this.getContext())) || 0 == str.length()) {
            return false;
        }
        mNetsisRestPublicApi = new NetsisRestPublicFactory.Impl(new CommunicationModule(null, null).provideNetsisPublicCallFactory()).rxEnabled(true).create(str, NetsisRestPublicApi.class);
        return true;
    }

    private void showConnectionKeyDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.requireContext());
        builder.setTitle(this.getString(R.string.pref_connection_key_title));
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setPadding(10, 0, 10, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        EditText editText = new EditText(this.getContext());
        editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(editText);
        builder.setView(linearLayout);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                showConnectionKeyDialoglambda0(UserSettingsFragment.this, editText, dialogInterface, i2);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                showConnectionKeyDialoglambda1(dialogInterface, i2);
            }
        });
        builder.show();
    }

    
    public static void showConnectionKeyDialoglambda0(final UserSettingsFragment this0, final EditText input, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNull(this0.mPrefErpType);
        this0.changeConnectionKey(!Intrinsics.areEqual(r2.getValue(), "0"), input.getText().toString());
    }

    
    public static void showConnectionKeyDialoglambda1(final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.cancel();
    }

    private void initPreference() {
        mPrefErpType = this.findPreference(this.getString(R.string.pref_erp_type_key));
        mPrefCommunicationType = this.findPreference(this.getString(R.string.pref_communication_type_key));
        mPrefTiger = this.findPreference(this.getString(R.string.pref_category_tiger_key));
        mPrefNetsis = this.findPreference(this.getString(R.string.pref_category_netsis_key));
        mPrefLanguage = this.findPreference(this.getString(R.string.pref_language_key));
        mPrefNetsisBranchCode = this.findPreference(this.getString(R.string.pref_netsis_db_branch_code_key));
        mPrefNetsisServerAddress = this.findPreference(this.getString(R.string.pref_netsis_server_address_key));
        mPrefNetsisPing = this.findPreference(this.getString(R.string.pref_netsis_ping_key));
        mPrefNetsisUserName = this.findPreference(this.getString(R.string.pref_netsis_username_key));
        mPrefNetsisDbName = this.findPreference(this.getString(R.string.pref_netsis_db_name_key));
        mPrefNetsisUserPassword = this.findPreference(this.getString(R.string.pref_netsis_password_key));
        mPrefNetsisDbName = this.findPreference(this.getString(R.string.pref_netsis_db_name_key));
        mPrefNetsisDbUserName = this.findPreference(this.getString(R.string.pref_netsis_db_username_key));
        mPrefNetsisDbPassword = this.findPreference(this.getString(R.string.pref_netsis_db_password_key));
        mPrefNetsisDbtype = this.findPreference(this.getString(R.string.pref_netsis_db_type_key));
        mPrefTigerServerAddress = this.findPreference(this.getString(R.string.pref_tiger_server_address_key));
        mPrefTigerWcfSecurityCode = this.findPreference(this.getString(R.string.pref_tiger_security_code_key));
        mPrefTigerFirmNumber = this.findPreference(this.getString(R.string.pref_tiger_firm_number_key));
        final Preference preference = mPrefNetsisPing;
        Intrinsics.checkNotNull(preference);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference2) {
                final boolean initPreferencelambda2;
                initPreferencelambda2 = initPreferencelambda2(UserSettingsFragment.this, preference2);
                return initPreferencelambda2;
            }
        });
        final ListPreference listPreference = mPrefLanguage;
        Intrinsics.checkNotNull(listPreference);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                final boolean initPreferencelambda3;
                initPreferencelambda3 = initPreferencelambda3(UserSettingsFragment.this, preference2, obj);
                return initPreferencelambda3;
            }
        });
        final ListPreference listPreference2 = mPrefErpType;
        Intrinsics.checkNotNull(listPreference2);
        listPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                final boolean initPreferencelambda5;
                initPreferencelambda5 = initPreferencelambda5(UserSettingsFragment.this, preference2, obj);
                return initPreferencelambda5;
            }
        });
        final EditTextPreference editTextPreference = mPrefNetsisUserName;
        if (null != editTextPreference) {
            editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda7;
                    initPreferencelambda7 = initPreferencelambda7(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda7;
                }
            });
        }
        final EditTextPreference editTextPreference2 = mPrefNetsisDbUserName;
        if (null != editTextPreference2) {
            editTextPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda9;
                    initPreferencelambda9 = initPreferencelambda9(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda9;
                }
            });
        }
        final EditTextPreference editTextPreference3 = mPrefNetsisDbName;
        if (null != editTextPreference3) {
            editTextPreference3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda11;
                    initPreferencelambda11 = initPreferencelambda11(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda11;
                }
            });
        }
        final EditTextPreference editTextPreference4 = mPrefNetsisUserPassword;
        if (null != editTextPreference4) {
            editTextPreference4.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda13;
                    initPreferencelambda13 = initPreferencelambda13(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda13;
                }
            });
        }
        final EditTextPreference editTextPreference5 = mPrefNetsisDbPassword;
        if (null != editTextPreference5) {
            editTextPreference5.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda15;
                    initPreferencelambda15 = initPreferencelambda15(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda15;
                }
            });
        }
        final EditTextPreference editTextPreference6 = mPrefNetsisServerAddress;
        Intrinsics.checkNotNull(editTextPreference6);
        editTextPreference6.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference2) {
                final boolean initPreferencelambda16;
                initPreferencelambda16 = initPreferencelambda16(UserSettingsFragment.this, preference2);
                return initPreferencelambda16;
            }
        });
        final BranchPreferences branchPreferences = mPrefNetsisBranchCode;
        Intrinsics.checkNotNull(branchPreferences);
        branchPreferences.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(final Preference preference2) {
                final boolean initPreferencelambda17;
                initPreferencelambda17 = initPreferencelambda17(UserSettingsFragment.this, preference2);
                return initPreferencelambda17;
            }
        });
        final EditTextPreference editTextPreference7 = mPrefTigerServerAddress;
        if (null != editTextPreference7) {
            editTextPreference7.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda19;
                    initPreferencelambda19 = initPreferencelambda19(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda19;
                }
            });
        }
        final EditTextPreference editTextPreference8 = mPrefTigerWcfSecurityCode;
        if (null != editTextPreference8) {
            editTextPreference8.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda21;
                    initPreferencelambda21 = initPreferencelambda21(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda21;
                }
            });
        }
        final EditTextIntegerPref editTextIntegerPref = mPrefTigerFirmNumber;
        if (null != editTextIntegerPref) {
            editTextIntegerPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference2, final Object obj) {
                    final boolean initPreferencelambda23;
                    initPreferencelambda23 = initPreferencelambda23(UserSettingsFragment.this, preference2, obj);
                    return initPreferencelambda23;
                }
            });
        }
        final ListPreference listPreference3 = mPrefErpType;
        Intrinsics.checkNotNull(listPreference3);
        final String value = listPreference3.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        this.setVisibleErpType(value);
    }

    
    public static boolean initPreferencelambda2(final UserSettingsFragment this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.sendPing();
        return true;
    }

    
    public static boolean initPreferencelambda3(final UserSettingsFragment this0, final Preference preference, final Object newValue) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        this0.changeLanguage(newValue.toString());
        return true;
    }

    
    public static boolean initPreferencelambda5(final UserSettingsFragment this0, final Preference preference, final Object newValue) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(newValue, "newValue");
        final String string = this0.getString(R.string.pref_erp_type_key);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        final SharedPreferences prefs = Preferences.getPrefs(this0.requireContext(), string);
        Intrinsics.checkNotNull(prefs);
        final SharedPreferences.Editor edit = prefs.edit();
        edit.putString(string, newValue.toString());
        edit.apply();
        this0.setVisibleErpType(newValue.toString());
        return true;
    }

    
    public static boolean initPreferencelambda7(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_netsis_username_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda9(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_netsis_db_username_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda11(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_netsis_db_name_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda13(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_netsis_password_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda15(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_netsis_db_password_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda16(final UserSettingsFragment this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.createSubscriptionServerAddress();
        return true;
    }

    
    public static boolean initPreferencelambda17(final UserSettingsFragment this0, final Preference preference) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.showDialog();
        return true;
    }

    
    public static boolean initPreferencelambda19(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_tiger_server_address_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda21(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_tiger_security_code_key), (String) obj);
        edit.apply();
        return true;
    }

    
    public static boolean initPreferencelambda23(final UserSettingsFragment this0, final Preference preference, final Object obj) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(preference, "<anonymous parameter 0>");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        final SharedPreferences securePreferences = Preferences.getSecurePreferences(this0.requireContext());
        Intrinsics.checkNotNull(securePreferences);
        final SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString(this0.getString(R.string.pref_tiger_firm_number_key), (String) obj);
        edit.apply();
        return true;
    }

    @SuppressLint("CheckResult")
    private void showDialog() {
        if (TextUtils.isEmpty(Preferences.getNetsisUsername(this.getContext())) || TextUtils.isEmpty(Preferences.getNetsisDbName(this.getContext()))) {
            Toast.makeText(this.getContext(), "Empty username", Toast.LENGTH_LONG).show();
            return;
        }
        final String netsisServerAddress = Preferences.getNetsisServerAddress(this.getContext());
        Intrinsics.checkNotNullExpressionValue(netsisServerAddress, "getNetsisServerAddress(...)");
        if (this.createService(netsisServerAddress)) {
            this.getMProgressDialogBuilder().setMessage(this.getString(R.string.str_please_wait)).setCancelable(false).show();
            final NetsisRestPublicApi netsisRestPublicApi = mNetsisRestPublicApi;
            Intrinsics.checkNotNull(netsisRestPublicApi);
            final Observable<NetsisDataHeader> branchesRx = netsisRestPublicApi.getBranchesRx(Preferences.getNetsisDbName(this.getContext()), Preferences.getNetsisUsername(this.getContext()));
            UserSettingsFragmentshowDialog1 userSettingsFragmentshowDialog1 = new Function1<NetsisDataHeader, Boolean>() {
                public Boolean invoke(final Object obj) {
                    Intrinsics.checkNotNullParameter(obj, "obj");
                    return Boolean.valueOf(obj.isSuccessful());
                }
            };
            final Observable<NetsisDataHeader> filter = branchesRx.filter(new Predicate() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda0
                @Override // io.reactivex.functions.Predicate
                public boolean test(final Object obj) {
                    final boolean showDialoglambda24;
                    showDialoglambda24 = showDialoglambda24(Function1.this, obj);
                    return showDialoglambda24;
                }
            });
            UserSettingsFragmentshowDialog2 userSettingsFragmentshowDialog2 = new Function1<NetsisDataHeader, Boolean>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog2
                
                public Boolean invoke(final Object netsisDataHeader) {
                    Intrinsics.checkNotNullParameter(netsisDataHeader, "netsisDataHeader");
                    Intrinsics.checkNotNullExpressionValue(netsisDataHeader.getData(), "getData(...)");
                    return Boolean.valueOf(!r2.isEmpty());
                }
            };
            final Observable<NetsisDataHeader> filter2 = filter.filter(new Predicate() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda1
                @Override // io.reactivex.functions.Predicate
                public boolean test(final Object obj) {
                    final boolean showDialoglambda25;
                    showDialoglambda25 = showDialoglambda25(Function1.this, obj);
                    return showDialoglambda25;
                }
            });
            UserSettingsFragmentshowDialog3 userSettingsFragmentshowDialog3 = new Function1<NetsisDataHeader, List<? extends NetsisBranch>>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog3
                
                public List<NetsisBranch> invoke(final Object netsisDataHeader) {
                    Intrinsics.checkNotNullParameter(netsisDataHeader, "netsisDataHeader");
                    try {
                        return new NetsisDataConverter().convertData(NetsisBranch.class, netsisDataHeader.getData(), true);
                    } catch (final Exception e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            };
            final Observable<R> map = filter2.map(new Function() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda2
                @Override // io.reactivex.functions.Function
                public Object apply(final Object obj) {
                    final List showDialoglambda26;
                    showDialoglambda26 = showDialoglambda26(Function1.this, obj);
                    return showDialoglambda26;
                }
            });
            UserSettingsFragmentshowDialog4 userSettingsFragmentshowDialog4 = new Function1<List<? extends NetsisBranch>, Boolean>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog4
                
                public Boolean invoke(final Object list) {
                    return Boolean.valueOf(!(null == list || list.isEmpty()));
                }
            };
            final Observable filter3 = map.filter(new Predicate() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda3
                @Override // io.reactivex.functions.Predicate
                public boolean test(final Object obj) {
                    final boolean showDialoglambda27;
                    showDialoglambda27 = showDialoglambda27(Function1.this, obj);
                    return showDialoglambda27;
                }
            });
            Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog5
                {
                    super(1);
                }

                
                public Unit invoke(final Object th) {
                    this.invoke2(th);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final Throwable throwable) {
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    showToast(throwable.getMessage());
                }
            };
            final Observable subscribeOn = filter3.doOnError(new Consumer() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public void accept(final Object obj) {
                    showDialoglambda28(Function1.this, obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m636io());
            Function1<List<? extends NetsisBranch>, Unit> function12 = new Function1<List<? extends NetsisBranch>, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog6
                {
                    super(1);
                }

                
                public Unit invoke(final Object list) {
                    this.invoke2(list);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final List<? extends NetsisBranch> list) {
                    selectBranch(list);
                }
            };
            final Consumer consumer = new Consumer() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda5
                @Override // io.reactivex.functions.Consumer
                public void accept(final Object obj) {
                    showDialoglambda29(Function1.this, obj);
                }
            };
            Function1<Throwable, Unit> function13 = new Function1<Throwable, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentshowDialog7
                {
                    super(1);
                } 
                public Unit invoke(final Object th) {
                    this.invoke2(th);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final Throwable t) {
                    Intrinsics.checkNotNullParameter(t, "t");
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    getMProgressDialogBuilder().dismiss();
                }
            };
            subscribeOn.subscribe(consumer, new Consumer() { 
                public void accept(final Object obj) {
                    showDialoglambda30(Function1.this, obj);
                }
            }, new Action() { 
                public void run() {
                    showDialoglambda31(UserSettingsFragment.this);
                }
            });
            return;
        }
        Toast.makeText(this.getContext(), "server adress error", Toast.LENGTH_LONG).show();
    }
 
    public static boolean showDialoglambda24(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }

    
    public static boolean showDialoglambda25(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }

    
    public static List showDialoglambda26(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) tmp0.invoke(p0);
    }

    
    public static boolean showDialoglambda27(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }

    
    public static void showDialoglambda28(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void showDialoglambda29(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void showDialoglambda30(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void showDialoglambda31(final UserSettingsFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getMProgressDialogBuilder().dismiss();
    }

    
    public void selectBranch(List<? extends NetsisBranch> list) {
        final Dialog create;
        if (null != list && !list.isEmpty()) {
            AlertDialogBuilder<?> alertDialogBuilder = mBranchAlertDialogBuilder;
            if (null == alertDialogBuilder) {
                Intrinsics.throwUninitializedPropertyAccessException("mBranchAlertDialogBuilder");
                alertDialogBuilder = null;
            }
            final AlertDialogBuilder singleChoice = alertDialogBuilder.setSingleChoice(this.getNetsisBranchName(list), mBranchChoice, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda21
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(final DialogInterface dialogInterface, final int i2) {
                    selectBranchlambda32(UserSettingsFragment.this, list, dialogInterface, i2);
                }
            });
            if (null == singleChoice || null == (create = singleChoice.create())) {
                return;
            }
            create.show();
            return;
        }
        Toast.makeText(this.getContext(), "Bulunamad\u0131", Toast.LENGTH_LONG).show();
    }

    
    public static void selectBranchlambda32(final UserSettingsFragment this0, final List list, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        this0.mBranchChoice = i2;
        Toast.makeText(this0.getContext(), list.get(i2).toString(), Toast.LENGTH_LONG).show();
        final BranchPreferences branchPreferences = this0.mPrefNetsisBranchCode;
        Intrinsics.checkNotNull(branchPreferences);
        branchPreferences.setNetsisBranch((NetsisBranch) list.get(i2));
        dialog.dismiss();
    }

    private void setVisibleErpType(final String str) {
        final String[] stringArray = this.requireContext().getResources().getStringArray(R.array.pref_erp_type_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final SwitchPreferenceCompat switchPreferenceCompat = this.findPreference(this.getString(R.string.pref_use_demo_key));
        if (Intrinsics.areEqual(str, stringArray[0])) {
            this.setVisibleSubPreference(mPrefTiger, true);
            this.setVisibleSubPreference(mPrefNetsis, false);
            Intrinsics.checkNotNull(switchPreferenceCompat);
            switchPreferenceCompat.setVisible(true);
            this.setCommunicationList(false);
            return;
        }
        if (Intrinsics.areEqual(str, stringArray[1])) {
            this.setVisibleSubPreference(mPrefTiger, false);
            this.setVisibleSubPreference(mPrefNetsis, true);
            Intrinsics.checkNotNull(switchPreferenceCompat);
            switchPreferenceCompat.setVisible(true);
            this.setCommunicationList(true);
        }
    }

    private void setCommunicationList(final boolean z) {
        if (!z) {
            final ListPreference listPreference = mPrefCommunicationType;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setEntries(R.array.pref_communication_wcf_entries);
            final ListPreference listPreference2 = mPrefCommunicationType;
            Intrinsics.checkNotNull(listPreference2);
            listPreference2.setEntryValues(R.array.pref_communication_wcf_values);
            final ListPreference listPreference3 = mPrefCommunicationType;
            Intrinsics.checkNotNull(listPreference3);
            listPreference3.setValueIndex(0);
            return;
        }
        final ListPreference listPreference4 = mPrefCommunicationType;
        Intrinsics.checkNotNull(listPreference4);
        listPreference4.setEntries(R.array.pref_communication_rest_entries);
        final ListPreference listPreference5 = mPrefCommunicationType;
        Intrinsics.checkNotNull(listPreference5);
        listPreference5.setEntryValues(R.array.pref_communication_rest_values);
        final ListPreference listPreference6 = mPrefCommunicationType;
        Intrinsics.checkNotNull(listPreference6);
        listPreference6.setValueIndex(0);
    }

    private void setVisibleSubPreference(final PreferenceCategory preferenceCategory, final boolean z) {
        Intrinsics.checkNotNull(preferenceCategory);
        final int preferenceCount = preferenceCategory.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            preferenceCategory.getPreference(i2).setVisible(z);
        }
        preferenceCategory.setVisible(z);
    }

    private void changeLanguage(final String str) {
        final SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        Intrinsics.checkNotNull(str);
        sharedPreferencesHelper.saveApplicationLanguage(str);
        sharedPreferencesHelper.setObject("changeLocale", Boolean.TRUE);
        LanguageHelper.setLanguage(ContextUtils.getmContext(), str);
        new Handler().post(new Runnable() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public void run() {
                changeLanguagelambda33(UserSettingsFragment.this);
            }
        });
    }

    
    @SuppressLint("WrongConstant")
    public static void changeLanguagelambda33(final UserSettingsFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Intent intent = this0.requireActivity().getIntent();
        intent.addFlags(335609856);
        this0.requireActivity().overridePendingTransition(0, 0);
        this0.requireActivity().finish();
        this0.requireActivity().overridePendingTransition(0, 0);
        this0.startActivity(intent);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void setLanguage() {
        try {
            final String applicationLanguage = new SharedPreferencesHelper().getApplicationLanguage();
            final Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            final Resources resources = context.getResources();
            final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            final Configuration configuration = resources.getConfiguration();
            if (Intrinsics.areEqual(this.getResources().getConfiguration().locale.getLanguage(), applicationLanguage)) {
                return;
            }
            configuration.locale = new Locale(applicationLanguage);
            configuration.setLayoutDirection(new Locale(applicationLanguage));
            resources.updateConfiguration(configuration, displayMetrics);
            ContextUtils.getmActivity().recreate();
        } catch (final Exception e2) {
            e2.printStackTrace();
        }
    }

    private CharSequence[] getNetsisBranchName(final List<? extends NetsisBranch> list) {
        final CharSequence[] charSequenceArr = new CharSequence[list.size()];
        final int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            charSequenceArr[i2] = list.get(i2).toString();
        }
        return charSequenceArr;
    }

    @SuppressLint("CheckResult")
    private void sendPing() {
        final String netsisServerAddress = Preferences.getNetsisServerAddress(this.getContext());
        Intrinsics.checkNotNullExpressionValue(netsisServerAddress, "getNetsisServerAddress(...)");
        if (this.createService(netsisServerAddress)) {
            final NetsisRestPublicApi netsisRestPublicApi = mNetsisRestPublicApi;
            Intrinsics.checkNotNull(netsisRestPublicApi);
            final Observable<String> sendPingRx = netsisRestPublicApi.sendPingRx();
            UserSettingsFragmentsendPing1 userSettingsFragmentsendPing1 = new Function1<String, Boolean>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentsendPing1
                
                public Boolean invoke(final Object s) {
                    Intrinsics.checkNotNullParameter(s, "s");
                    return Boolean.valueOf(StringsKt.containsdefault((CharSequence) s, (CharSequence) "OK", false, 2, (Object) null));
                }
            };
            final Observable<R> map = sendPingRx.map(new Function() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda16
                @Override // io.reactivex.functions.Function
                public Object apply(final Object obj) {
                    final Boolean sendPinglambda34;
                    sendPinglambda34 = sendPinglambda34(Function1.this, obj);
                    return sendPinglambda34;
                }
            });
            UserSettingsFragmentsendPing2 userSettingsFragmentsendPing2 = new Function1<Boolean, String>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentsendPing2
                
                public String invoke(final Object bool) {
                    return invoke(bool.booleanValue());
                }

                public String invoke(final boolean z) {
                    return z ? "OK" : "FAILED";
                }
            };
            final Observable map2 = map.map(new Function() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda17
                @Override // io.reactivex.functions.Function
                public Object apply(final Object obj) {
                    final String sendPinglambda35;
                    sendPinglambda35 = sendPinglambda35(Function1.this, obj);
                    return sendPinglambda35;
                }
            });
            Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentsendPing3
                {
                    super(1);
                }

                
                public Unit invoke(final Object th) {
                    this.invoke2(th);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final Throwable throwable) {
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    showToast(throwable.getMessage());
                }
            };
            final Observable subscribeOn = map2.doOnError(new Consumer() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda18
                @Override // io.reactivex.functions.Consumer
                public void accept(final Object obj) {
                    sendPinglambda36(Function1.this, obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m636io());
            Function1<String, Unit> function12 = new Function1<String, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentsendPing4
                {
                    super(1);
                }

                
                public Unit invoke(final Object str) {
                    this.invoke2(str);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final String aBoolean) {
                    final Preference preference;
                    Intrinsics.checkNotNullParameter(aBoolean, "aBoolean");
                    preference = mPrefNetsisPing;
                    Intrinsics.checkNotNull(preference);
                    preference.setSummary("TEST " + aBoolean);
                }
            };
            final Consumer consumer = new Consumer() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda19
                @Override // io.reactivex.functions.Consumer
                public void accept(final Object obj) {
                    sendPinglambda37(Function1.this, obj);
                }
            };
            Function1<Throwable, Unit> function13 = new Function1<Throwable, Unit>() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentsendPing5
                {
                    super(1);
                }

                
                public Unit invoke(final Object th) {
                    this.invoke2(th);
                    return Unit.INSTANCE;
                }

                
                public void invoke2(final Throwable th) {
                    final Preference preference;
                    preference = mPrefNetsisPing;
                    Intrinsics.checkNotNull(preference);
                    preference.setSummary(null != th ? th.getMessage() : "");
                }
            };
            subscribeOn.subscribe(consumer, new Consumer() { // from class: com.proje.mobilesales.features.settings.view.fragment.UserSettingsFragmentExternalSyntheticLambda20
                @Override // io.reactivex.functions.Consumer
                public void accept(final Object obj) {
                    sendPinglambda38(Function1.this, obj);
                }
            });
        }
    }

    
    public static Boolean sendPinglambda34(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Boolean) tmp0.invoke(p0);
    }

    
    public static String sendPinglambda35(final Function1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (String) tmp0.invoke(p0);
    }

    
    public static void sendPinglambda36(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void sendPinglambda37(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void sendPinglambda38(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    private void createSubscriptionServerAddress() {
        final Observable<String> preferenceObserveText = ObservableUtils.preferenceObserveText(mPrefNetsisServerAddress);
        UserSettingsFragmentcreateSubscriptionServerAddress1 userSettingsFragmentcreateSubscriptionServerAddress1 = new Function1<String, String>() {
            public String invoke(final Object str) {
                Intrinsics.checkNotNull(str);
                return !StringUtils.urlControl((String) str) ? StringUtils.urlAddHtpp((String) str) : str.toString();
            }
        };
        final Observable<R> map = preferenceObserveText.map(new Function() {
            public Object apply(final Object obj) {
                final String createSubscriptionServerAddresslambda39;
                createSubscriptionServerAddresslambda39 = createSubscriptionServerAddresslambda39(userSettingsFragmentcreateSubscriptionServerAddress1, obj);
                return createSubscriptionServerAddresslambda39;
            }
        });
        Function1<String, Unit> function1 = new Function1<String, Unit>() {
            public Unit invoke(final Object str) {
                this.invoke2((String) str);
                return Unit.INSTANCE;
            }

            
            public void invoke2(final String url) {
                Intrinsics.checkNotNullParameter(url, "url");
                createService(url);
            }
        };
        final Observable doOnNext = map.doOnNext(new Consumer() {
            public void accept(final Object obj) {
                createSubscriptionServerAddresslambda40(Function1.this, obj);
            }
        });
        Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() {

            public Unit invoke(final Object th) {
                this.invoke2((Throwable) th);
                return Unit.INSTANCE;
            }

            
            public void invoke2(final Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                showToast(throwable.getMessage());
            }
        };
        final Observable subscribeOn = doOnNext.doOnError(new Consumer() {
            public void accept(final Object obj) {
                createSubscriptionServerAddresslambda41(userSettingsFragmentcreateSubscriptionServerAddress3, obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        UserSettingsFragmentcreateSubscriptionServerAddress4 userSettingsFragmentcreateSubscriptionServerAddress4 = new Function1<String, Unit>() {
            public Unit invoke(final Object str) {
                this.invoke2((String) str);
                return Unit.INSTANCE;
            }

            
            public void invoke2(final String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                Log.d(MobileSales.TAG, "createSubscriptionServerAddress: " + s);
            }
        };
        final Consumer consumer = new Consumer() {
            public void accept(final Object obj) {
                createSubscriptionServerAddresslambda42(Function1.this, obj);
            }
        };
        UserSettingsFragmentcreateSubscriptionServerAddress5 userSettingsFragmentcreateSubscriptionServerAddress5 = new Function1<Throwable, Unit>() {
            public Unit invoke(final Object th) {
                this.invoke2((Throwable) th);
                return Unit.INSTANCE;
            }

            
            public void invoke2(final Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Log.d(MobileSales.TAG, "createSubscriptionServerAddress: " + throwable.getMessage());
            }
        };
        subscribeOn.subscribe(consumer, new Consumer() {
            public void accept(final Object obj) {
                createSubscriptionServerAddresslambda43(Function1.this, obj);
            }
        });
    }

    
    public static String createSubscriptionServerAddresslambda39(final UserSettingsFragmentcreateSubscriptionServerAddress1 tmp0, final Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (String) tmp0.invoke(p0);
    }

    
    public static void createSubscriptionServerAddresslambda40(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void createSubscriptionServerAddresslambda41(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void createSubscriptionServerAddresslambda42(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void createSubscriptionServerAddresslambda43(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    public void onDestroy() {
        super.onDestroy();
        this.getMProgressDialogBuilder().dismiss();
        this.getMAlertDialogBuilder().dismiss();
        AlertDialogBuilder<?> alertDialogBuilder = mBranchAlertDialogBuilder;
        if (null == alertDialogBuilder) {
            Intrinsics.throwUninitializedPropertyAccessException("mBranchAlertDialogBuilder");
            alertDialogBuilder = null;
        }
        alertDialogBuilder.dismiss();
    }

    
    public void showToast(final String str) {
        Toast.makeText(this.getContext(), str, Toast.LENGTH_LONG).show();
    }

    private boolean changeConnectionKey(final boolean z, final String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        final String decryptConnectionKey = this.decryptConnectionKey(str);
        if (null == decryptConnectionKey) {
            this.showToast(this.getString(R.string.str_connection_key_cannot_be_decrypred));
            return false;
        }
        return this.setConnectionKeyValues(z, decryptConnectionKey);
    }

    private String decryptConnectionKey(final String str) {
        try {
            return new AesEncryption().decrypt(str);
        } catch (final Exception e2) {
            Log.e(UserSettingsFragment.TAG, "Connection Key Error", e2);
            return null;
        }
    }

    private boolean setConnectionKeyValues(final boolean z, final String str) {
        List emptyList;
        final List<String> split = new Regex("\\|").split(str, 0);
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
        if (z) {
            if (null == strArr || 7 > strArr.length) {
                return false;
            }
            final EditTextPreference editTextPreference = mPrefNetsisServerAddress;
            Intrinsics.checkNotNull(editTextPreference);
            editTextPreference.setText(strArr[0]);
            final EditTextPreference editTextPreference2 = mPrefNetsisUserName;
            Intrinsics.checkNotNull(editTextPreference2);
            editTextPreference2.setText(strArr[1]);
            final EditTextPreference editTextPreference3 = mPrefNetsisUserPassword;
            Intrinsics.checkNotNull(editTextPreference3);
            editTextPreference3.setText(strArr[2]);
            final EditTextPreference editTextPreference4 = mPrefNetsisDbName;
            Intrinsics.checkNotNull(editTextPreference4);
            editTextPreference4.setText(strArr[3]);
            final EditTextPreference editTextPreference5 = mPrefNetsisDbUserName;
            Intrinsics.checkNotNull(editTextPreference5);
            editTextPreference5.setText(strArr[4]);
            final EditTextPreference editTextPreference6 = mPrefNetsisDbPassword;
            Intrinsics.checkNotNull(editTextPreference6);
            editTextPreference6.setText(strArr[5]);
            final ListPreference listPreference = mPrefNetsisDbtype;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValue(Intrinsics.areEqual(strArr[6], "MSSQL") ? "0" : BuildConfig.NETSIS_DEMO_PASSWORD);
            final BranchPreferences branchPreferences = mPrefNetsisBranchCode;
            Intrinsics.checkNotNull(branchPreferences);
            branchPreferences.clearSelection();
            final Preference preference = mPrefNetsisPing;
            Intrinsics.checkNotNull(preference);
            preference.setSummary("");
            mBranchChoice = 0;
        } else {
            if (null == strArr || 2 > strArr.length) {
                return false;
            }
            final EditTextPreference editTextPreference7 = mPrefTigerServerAddress;
            Intrinsics.checkNotNull(editTextPreference7);
            editTextPreference7.setText(strArr[0]);
            final EditTextPreference editTextPreference8 = mPrefTigerWcfSecurityCode;
            Intrinsics.checkNotNull(editTextPreference8);
            editTextPreference8.setText(strArr[1]);
            final EditTextIntegerPref editTextIntegerPref = mPrefTigerFirmNumber;
            Intrinsics.checkNotNull(editTextIntegerPref);
            editTextIntegerPref.setText("");
        }
        return true;
    }

    public void clearConnectionKeyValues(final boolean z) {
        if (z) {
            final EditTextPreference editTextPreference = mPrefNetsisServerAddress;
            Intrinsics.checkNotNull(editTextPreference);
            editTextPreference.setText("");
            final EditTextPreference editTextPreference2 = mPrefNetsisUserName;
            Intrinsics.checkNotNull(editTextPreference2);
            editTextPreference2.setText("");
            final EditTextPreference editTextPreference3 = mPrefNetsisUserPassword;
            Intrinsics.checkNotNull(editTextPreference3);
            editTextPreference3.setText("");
            final EditTextPreference editTextPreference4 = mPrefNetsisDbName;
            Intrinsics.checkNotNull(editTextPreference4);
            editTextPreference4.setText("");
            final EditTextPreference editTextPreference5 = mPrefNetsisDbUserName;
            Intrinsics.checkNotNull(editTextPreference5);
            editTextPreference5.setText("");
            final EditTextPreference editTextPreference6 = mPrefNetsisDbPassword;
            Intrinsics.checkNotNull(editTextPreference6);
            editTextPreference6.setText("");
            final ListPreference listPreference = mPrefNetsisDbtype;
            Intrinsics.checkNotNull(listPreference);
            listPreference.setValue("0");
            final BranchPreferences branchPreferences = mPrefNetsisBranchCode;
            Intrinsics.checkNotNull(branchPreferences);
            branchPreferences.clearSelection();
            final Preference preference = mPrefNetsisPing;
            Intrinsics.checkNotNull(preference);
            preference.setSummary("");
            mBranchChoice = 0;
            return;
        }
        final EditTextPreference editTextPreference7 = mPrefTigerWcfSecurityCode;
        Intrinsics.checkNotNull(editTextPreference7);
        editTextPreference7.setText("");
        final EditTextPreference editTextPreference8 = mPrefTigerServerAddress;
        Intrinsics.checkNotNull(editTextPreference8);
        editTextPreference8.setText("");
        final EditTextIntegerPref editTextIntegerPref = mPrefTigerFirmNumber;
        Intrinsics.checkNotNull(editTextIntegerPref);
        editTextIntegerPref.setText("");
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        final String simpleName = UserSettingsFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        TAG = simpleName;
    }
}
