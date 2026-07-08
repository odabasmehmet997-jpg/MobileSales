package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceCategory;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.StringRes;
import androidx.exifinterface.media.ExifInterface;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyLibParams;
import com.google.gson.Gson;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.CommunicationType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.PrintDestination;
import com.proje.mobilesales.core.enums.PrintLanguage;
import com.proje.mobilesales.core.enums.PrintType;
import com.proje.mobilesales.core.enums.TransferGetOptionType;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.ActivePeriod;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.UserSettings;
import com.proje.mobilesales.features.settings.preferences.DevicePreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Preferences {
    private static final String ENCRYPTED_PREFS_NAME = "secret_shared_prefs";
    static final List<String> SENSITIVE_KEYS = Arrays.asList("pref_tiger_server_address_key", "pref_tiger_security_code_key", "pref_tiger_firm_number_key", "pref_netsis_server_address_key", "pref_netsis_username_key", "pref_netsis_password_key", "pref_netsis_db_name_key", "pref_netsis_db_username_key", "pref_netsis_db_password_key", "pref_netsis_db_type_key", "pref_use_demo_key", "pref_erp_type_key", "pref_language_key");
    private static final String TAG = "Preferences";
    public static int getCasePrintMatterArea() {
        return 1;
    }
    public static int getCashPrintMatterArea() {
        return 1;
    }
    public static int getChequePrintMatterArea() {
        return 1;
    }
    public static int getCreditCardPrintMatterArea() {
        return 1;
    }
    public static int getDeedPrintMatterArea() {
        return 1;
    }
    private static boolean isSensitiveKey(String str) {
        return SENSITIVE_KEYS.contains(str);
    }
    public static SharedPreferences getSecurePreferences(Context context) {
        try {
            return EncryptedSharedPreferences.create(context, ENCRYPTED_PREFS_NAME, new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e2) {
            throw new RuntimeException("Secure Preferences could not be initialized", e2);
        }
    }
    public static void writeConstantsToPrefsIfNeeded(Context context) {
        SharedPreferences securePreferences = getSecurePreferences(context);
        if (securePreferences.getBoolean("constants_written", false)) {
            return;
        }
        SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString("NETSIS_APPUSERNAME", "Demo");
        edit.putString("NETSIS_APP_P", ExifInterface.GPS_MEASUREMENT_2D);
        edit.putString("NETSIS_DBUSER", "TEMELSET");
        edit.putString("NETSIS_USERNAME", "Demo");
        edit.putString("NETSIS_DBNAME", "ENTERPRISE9");
        edit.putString("NETSIS_BRANCH", "0");
        edit.putString("NETSIS_ENTCODE", BuildConfig.NETSIS_DEMO_PASSWORD);
        edit.putString("NETSIS_DBTYPE", "0");
        edit.putString("USERNAME", "SCMDEMO");
        edit.putString("APPVERSION_ADDED_VERSION", "2.14.00.00");
        edit.putBoolean("constants_written", true);
        edit.apply();
    }
    public static void writeSecretsToPrefsIfNeeded(Context context) {
        SharedPreferences securePreferences = getSecurePreferences(context);
        if (securePreferences.getBoolean("secrets_written", false)) {
            return;
        }
        SharedPreferences.Editor edit = securePreferences.edit();
        edit.putString("ENC_STRING", BuildConfig.ENCRYPTIONKEY);
        edit.putString("CLIENTID", BuildConfig.CLIENTID);
        edit.putString("ENCKEY", BuildConfig.ENCKEY);
        edit.putString("MACKEY", BuildConfig.MACKEY);
        edit.putString("CLIENT_SECRET", BuildConfig.CLIENT_SECRET);
        edit.putString("TIGER_DEMO_P", BuildConfig.TIGER_DEMO_PASSWORD);
        edit.putString("NETSIS_DEMO_P", BuildConfig.NETSIS_DEMO_PASSWORD);
        edit.putString("NETSIS_ENCRYPTION_VECTOR", BuildConfig.NETSIS_ENCRYPTION_VECTOR);
        edit.putString("NETSIS_ENCRYPTION_KEY", BuildConfig.NETSIS_ENCRYPTION_KEY);
        edit.putString("ENCRYPTION_ALGORITHM", BuildConfig.ENCRYPTION_ALGORITHM);
        edit.putString("ENCRYPTION_ALGORITHM_NO_PADDING", BuildConfig.ENCRYPTION_ALGORITHM_NO_PADDING);
        edit.putBoolean("secrets_written", true);
        edit.apply();
    }
    public static SharedPreferences getPrefs(Context context, String str) {
        if (isSensitiveKey(str)) {
            return getSecurePreferences(context);
        }
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static boolean getBoolean(Context context, String str, boolean z) {
        return getPrefs(context, str).getBoolean(str, z);
    }
    public static void migrateSensitivePrefs(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences securePreferences = getSecurePreferences(context);
        for (String str : SENSITIVE_KEYS) {
            if (defaultSharedPreferences.contains(str)) {
                Object obj = defaultSharedPreferences.getAll().get(str);
                SharedPreferences.Editor edit = securePreferences.edit();
                if (obj instanceof String) {
                    edit.putString(str, (String) obj);
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Integer) {
                    edit.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    edit.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Long) {
                    edit.putLong(str, ((Long) obj).longValue());
                } else {
                    edit.putString(str, obj != null ? obj.toString() : "");
                }
                edit.apply();
                defaultSharedPreferences.edit().remove(str).apply();
            }
        }
    }
    public static String getEncString(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("ENC_STRING", "");
    }
    public static String getNetsisAppDemoUsername(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_APPUSERNAME", "");
    }
    public static String getNetsisAppDemoP(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_APP_P", "");
    }
    public static String getNetsisDemoDbUser(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_DBUSER", "");
    }
    public static String getNetsisDemoUsername(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_USERNAME", "");
    }
    public static String getNetsisDemoDbName(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_DBNAME", "");
    }
    public static String getNetsisDemoBranch(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_BRANCH", "");
    }
    public static String getNetsisDemoEntCode(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_ENTCODE", "");
    }
    public static String getNetsisDemoDbType(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_DBTYPE", "");
    }
    public static String getTigerDemoUsername(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("USERNAME", "");
    }
    public static String getAppVersionAddedVersion(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("APPVERSION_ADDED_VERSION", "");
    }
    public static String getClientId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("CLIENTID", "");
    }
    public static String getEncKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("ENCKEY", "");
    }
    public static String getMacKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("MACKEY", "");
    }
    public static String getClientSecret(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("CLIENT_SECRET", "");
    }
    public static String getNetsisEncryptionKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_ENCRYPTION_KEY", "");
    }
    public static String getNetsisEncryptionVector(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_ENCRYPTION_VECTOR", "");
    }
    public static String getEncryptionAlgorithm(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("ENCRYPTION_ALGORITHM", "");
    }
    public static String getEncryptionAlgorithmNoPadding(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("ENCRYPTION_ALGORITHM_NO_PADDING", "");
    }
    public static String getTigerDemoP(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("TIGER_DEMO_P", "");
    }
    public static String getNetsisDemoP(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("NETSIS_DEMO_P", "");
    }
    public static void sync(PreferenceManager preferenceManager) {
        Iterator<String> it = preferenceManager.getSharedPreferences().getAll().keySet().iterator();
        while (it.hasNext()) {
            sync(preferenceManager, it.next());
        }
    }
    public static void sync(PreferenceManager preferenceManager, String str) {
        Preference findPreference = preferenceManager.findPreference(str);
        if (findPreference instanceof ListPreference) {
            findPreference.setSummary(((ListPreference) findPreference).getEntry());
            return;
        }
        if (findPreference instanceof EditTextPasswordPref) {
            findPreference.setSummary(toStars(((EditTextPreference) findPreference).getText()));
            return;
        }
        if (findPreference instanceof EditTextIntPasswordPref) {
            findPreference.setSummary(toStars(((EditTextPreference) findPreference).getText()));
            return;
        }
        if (findPreference instanceof EditTextPreference) {
            findPreference.setSummary(((EditTextPreference) findPreference).getText());
            return;
        }
        if (findPreference instanceof DevicePreference) {
            findPreference.setSummary(findPreference.getSummary());
        } else if (findPreference instanceof BranchPreferences) {
            findPreference.setSummary(findPreference.getSummary());
        } else if (findPreference != null) {
            findPreference.setSummary(findPreference.getSummary());
        }
    }
    static String toStars(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            sb.append('*');
        }
        return sb.toString();
    }
    public static void updatePrefSummary(android.preference.Preference preference) {
        String str;
        if (preference instanceof android.preference.ListPreference listPreference) {
            if (listPreference.getEntry() == null) {
                str = "";
            } else {
                str = listPreference.getEntry().toString().replaceAll("%", "%%");
            }
            preference.setSummary(str);
        }
        if (preference instanceof LogoDatePreference) {
            preference.setSummary(DateAndTimeUtils.toDateWithMonth(((LogoDatePreference) preference).getDate()));
        }
        if (preference instanceof LogoCheckPreference) {
            preference.setSummary(((LogoCheckPreference) preference).getEntry());
        }
        if (preference instanceof android.preference.EditTextPreference) {
            preference.setSummary(((android.preference.EditTextPreference) preference).getText());
        }
    }
    public static void initSummary(android.preference.Preference preference) {
        if (preference instanceof PreferenceCategory preferenceCategory) {
            for (int i2 = 0; i2 < preferenceCategory.getPreferenceCount(); i2++) {
                initSummary(preferenceCategory.getPreference(i2));
            }
            return;
        }
        updatePrefSummary(preference);
    }
    public static void migrate(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply();
    }
    public static void reset(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();
    }
    public static void resetWithoutConnectionSettings(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList arrayList = new ArrayList(Arrays.asList("pref_language_key", "pref_erp_type_key", "pref_communication_type_key", "pref_use_demo_key", "pref_category_tiger_key", "pref_tiger_server_address_key", "pref_tiger_security_code_key", "pref_tiger_firm_number_key", "pref_category_netsis_key", "pref_netsis_server_address_key", "pref_netsis_username_key", "pref_netsis_password_key", "pref_netsis_db_name_key", "pref_netsis_db_username_key", "pref_netsis_db_password_key", "pref_netsis_db_type_key", "pref_netsis_db_branch_code_key", "pref_netsis_ping_key"));
        defaultSharedPreferences.edit();
        Map<String, ?> all = defaultSharedPreferences.getAll();
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        for (String str : all.keySet()) {
            if (!arrayList.contains(str)) {
                edit.remove(str);
            }
        }
        edit.commit();
    }
    public static void reset(PreferenceManager preferenceManager) {
        preferenceManager.getSharedPreferences().edit().clear().apply();
    }
    public static boolean getTransferGetOptionsType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_tranfer_type), context.getString(R.string.str_delete_and_transfer)).equals(context.getString(R.string.str_delete_and_transfer));
    }
    public static void setTransferGetOptionsType(Context context, TransferGetOptionType transferGetOptionType) {
        String string;
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String string2 = context.getString(R.string.pref_tranfer_type);
        if (transferGetOptionType == TransferGetOptionType.GET_ONLY_CHANGED) {
            string = context.getString(R.string.str_changed_transfer);
        } else {
            string = context.getString(R.string.str_delete_and_transfer);
        }
        edit.putString(string2, string);
        edit.apply();
    }
    public static String getDriverInfo(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_driver_info), "");
    }
    public static void setDriverInfo(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_driver_info), str);
        edit.apply();
    }
    public static FicheDiscountRefProp getCreditCardCollectionDefaultBank(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_credit_card_collection_bank), "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new Gson().fromJson(string, FicheDiscountRefProp.class);
    }
    public static void setCreditCardCollectionDefaultBank(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_credit_card_collection_bank), str);
        edit.apply();
        edit.commit();
    }
    public static FicheDiscountRefProp getCreditCardCollectionDefaultBankAccount(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_credit_card_collection_bank_account), "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new Gson().fromJson(string, FicheDiscountRefProp.class);
    }
    public static void setCreditCardCollectionDefaultBankAccount(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_credit_card_collection_bank_account), str);
        edit.apply();
        edit.commit();
    }
    public static void setTransferGetOptionsType(Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_tranfer_type), context.getString(R.string.str_changed_transfer));
        edit.apply();
        edit.commit();
    }
    public static int getTransferPartSize(Context context) {
        return StringUtils.convertStringToInt(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_tranfer_part_size), context.getString(R.string.pref_transfer_part_size_default)));
    }
    public static boolean getTransferUseLog(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_use_transfer_log_key), false);
    }
    public static String getPrinterOneFullAddress(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_one_key), "");
    }
    public static String getPrinterOneAddress(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_one_key), "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            String[] split = string.split("\\n");
            return split.length >= 1 ? split[0] : "";
        } catch (Exception e2) {
            Log.e(TAG, "getPrinterOneAddress: ", e2);
            return "";
        }
    }
    public static String getPrinterOneName(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_one_key), "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            String[] split = string.split("\\n");
            return split.length == 2 ? split[1] : "";
        } catch (Exception e2) {
            Log.e(TAG, "getPrinterOneName: ", e2);
            return "";
        }
    }
    public static String getPrinterTwoFullAddress(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_two_key), "");
    }
    public static String getPrinterTwoAddress(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_two_key), "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            String[] split = string.split("\\n");
            return split.length >= 1 ? split[0] : "";
        } catch (Exception e2) {
            Log.e(TAG, "getPrinterTwoAddress: ", e2);
            return "";
        }
    }
    public static String getPrinterTwoName(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_printer_two_key), "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            String[] split = string.split("\\n");
            return split.length == 2 ? split[1] : "";
        } catch (Exception e2) {
            Log.e(TAG, "getPrinterTwoName: ", e2);
            return "";
        }
    }
    public static int getShowPrintPreviewFontSize(Context context) {
        return getInt(context, R.string.pref_printer_font_size, R.string.pref_printer_font_size_default);
    }
    public static boolean setShowPrintPreviewFontSize(Context context, int i2) {
        return setInt(context, R.string.pref_printer_font_size, R.string.pref_printer_font_size_default, i2);
    }
    public static boolean getShowPrintPreview(Context context) {
        return get(context, R.string.pref_print_screen_general_show_preview_key, false);
    }
    public static int getPrintPreviewUseTurkishCharacter(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_general_list_turkish_options_key, "0"));
    }
    public static int getPrintPreviewWaitTime(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_general_wait_time_key, R.string.pref_print_screen_general_wait_time_key_default));
    }
    public static String getPrintPreviewStartCode(Context context) {
        return get(context, R.string.pref_print_screen_general_start_code_key, "");
    }
    public static String getPrintPreviewEndCode(Context context) {
        return get(context, R.string.pref_print_screen_general_end_code_key, "");
    }
    public static int getOrderDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_order_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getOrderPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_order_show_header_key, false);
    }
    public static boolean getOrderPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_order_show_page_footer_key, false);
    }
    public static boolean getOrderPrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_order_use_printed_matter_key, false);
    }
    public static String getOrderPrintFirstPrintedMatter(Context context) {
        return getOrderPrintUseMatter(context) ? get(context, R.string.pref_print_screen_order_first_printed_matter_key, "") : "";
    }
    public static boolean setOrderPrintFirstPrintedMatter(Context context, String str) {
        if (getOrderPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_order_first_printed_matter_key, "", str);
        }
        return false;
    }
    public static String getOrderPrintLastPrintedMatter(Context context) {
        return getOrderPrintUseMatter(context) ? get(context, R.string.pref_print_screen_order_last_printed_matter_key, "") : "";
    }
    public static boolean setOrderPrintLastPrintedMatter(Context context, String str) {
        if (getOrderPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_order_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getOrderPrintMatterArea(Context context) {
        if (getOrderPrintUseMatter(context)) {
            return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_order_list_printed_matter_key, "0"));
        }
        return 0;
    }
    public static int getInvoiceDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_invoice_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getInvoicePrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_invoice_show_header_key, false);
    }
    public static boolean getInvoicePrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_invoice_show_page_footer_key, false);
    }
    public static boolean getInvoicePrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_invoice_use_printed_matter_key, false);
    }
    public static String getInvoicePrintFirstPrintedMatter(Context context) {
        return getInvoicePrintUseMatter(context) ? get(context, R.string.pref_print_screen_invoice_first_printed_matter_key, "") : "";
    }
    public static boolean setInvoicePrintFirstPrintedMatter(Context context, String str) {
        if (getInvoicePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_invoice_first_printed_matter_key, "", str);
        }
        return false;
    }
    public static String getInvoicePrintLastPrintedMatter(Context context) {
        return getInvoicePrintUseMatter(context) ? get(context, R.string.pref_print_screen_invoice_last_printed_matter_key, "") : "";
    }
    public static boolean setInvoicePrintLastPrintedMatter(Context context, String str) {
        if (getInvoicePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_invoice_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getInvoicePrintMatterArea(Context context) {
        if (getInvoicePrintUseMatter(context)) {
            return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_invoice_list_printed_matter_key, "0"));
        }
        return 0;
    }
    public static int getRetailInvoiceDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_retailinvoice_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getWhTransferPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_whTransfer_show_header_key, false);
    }
    public static boolean getWhTransferPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_whTransfer_show_page_footer_key, false);
    }
    public static int getWhTransferDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_whTransfer_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getRetailInvoicePrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_retailinvoice_show_header_key, false);
    }
    public static boolean getRetailInvoicePrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_retailinvoice_show_page_footer_key, false);
    }
    public static boolean getRetailInvoicePrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_retailinvoice_use_printed_matter_key, false);
    }
    public static String getRetailInvoicePrintFirstPrintedMatter(Context context) {
        return getRetailInvoicePrintUseMatter(context) ? get(context, R.string.pref_print_screen_retailinvoice_first_printed_matter_key, "") : "";
    }
    public static boolean setRetailInvoicePrintFirstPrintedMatter(Context context, String str) {
        if (getRetailInvoicePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_retailinvoice_first_printed_matter_key, "", str);
        }
        return false;
    }
    public static String getRetailInvoicePrintLastPrintedMatter(Context context) {
        return getRetailInvoicePrintUseMatter(context) ? get(context, R.string.pref_print_screen_retailinvoice_last_printed_matter_key, "") : "";
    }
    public static boolean setRetailInvoicePrintLastPrintedMatter(Context context, String str) {
        if (getRetailInvoicePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_retailinvoice_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getRetailInvoicePrintMatterArea(Context context) {
        if (getRetailInvoicePrintUseMatter(context)) {
            return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_retailinvoice_list_printed_matter_key, "0"));
        }
        return 0;
    }
    public static int getDispatchDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_dispatch_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getDispatchPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_dispatch_show_header_key, false);
    }
    public static boolean getDispatchPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_dispatch_show_page_footer_key, false);
    }
    public static boolean getDispatchPrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_dispatch_use_printed_matter_key, false);
    }
    public static String getDispatchPrintFirstPrintedMatter(Context context) {
        return getDispatchPrintUseMatter(context) ? get(context, R.string.pref_print_screen_dispatch_first_printed_matter_key, "") : "";
    }
    public static boolean setDispatchPrintFirstPrintedMatter(Context context, String str) {
        if (getDispatchPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_dispatch_first_printed_matter_key, "", str);
        }
        return false;
    }
    public static String getDispatchPrintLastPrintedMatter(Context context) {
        return getDispatchPrintUseMatter(context) ? get(context, R.string.pref_print_screen_dispatch_last_printed_matter_key, "") : "";
    }
    public static boolean setDispatchPrintLastPrintedMatter(Context context, String str) {
        if (getDispatchPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_dispatch_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getDispatchPrintMatterArea(Context context) {
        if (getDispatchPrintUseMatter(context)) {
            return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_dispatch_list_printed_matter_key, "0"));
        }
        return 0;
    }
    public static int getCashDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_cash_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getCashPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_cash_show_header_key, false);
    }
    public static boolean getCashPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_cash_show_page_footer_key, false);
    }
    public static boolean getCashPrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_cash_use_printed_matter_key, false);
    }
    public static String getCashPrintFirstPrintedMatter(Context context) {
        return getCashPrintUseMatter(context) ? get(context, R.string.pref_print_screen_cash_first_printed_matter_key, "") : "";
    }
    public static String getCashPrintLastPrintedMatter(Context context) {
        return getCashPrintUseMatter(context) ? get(context, R.string.pref_print_screen_cash_last_printed_matter_key, "") : "";
    }
    public static boolean setCashPrintLastPrintedMatter(Context context, String str) {
        if (getCashPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_cash_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getCreditCardDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_credit_card_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getCreditCardPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_credit_card_show_header_key, false);
    }
    public static boolean getCreditCardPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_credit_card_show_page_footer_key, false);
    }
    public static boolean getCreditCardPrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_credit_card_use_printed_matter_key, false);
    }
    public static String getCreditCardPrintFirstPrintedMatter(Context context) {
        return getCreditCardPrintUseMatter(context) ? get(context, R.string.pref_print_screen_credit_card_first_printed_matter_key, "") : "";
    }
    public static String getCreditCardPrintLastPrintedMatter(Context context) {
        return getCreditCardPrintUseMatter(context) ? get(context, R.string.pref_print_screen_credit_card_last_printed_matter_key, "") : "";
    }
    public static boolean setCreditCardPrintLastPrintedMatter(Context context, String str) {
        if (getCreditCardPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_credit_card_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getChequeDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_cheque_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getChequePrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_cheque_show_header_key, false);
    }
    public static boolean getChequePrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_cheque_show_page_footer_key, false);
    }
    public static boolean getChequePrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_cheque_use_printed_matter_key, false);
    }
    public static String getChequePrintFirstPrintedMatter(Context context) {
        return getChequePrintUseMatter(context) ? get(context, R.string.pref_print_screen_cheque_first_printed_matter_key, "") : "";
    }
    public static String getChequePrintLastPrintedMatter(Context context) {
        return getChequePrintUseMatter(context) ? get(context, R.string.pref_print_screen_cheque_last_printed_matter_key, "") : "";
    }
    public static boolean setChequePrintLastPrintedMatter(Context context, String str) {
        if (getChequePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_cheque_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getDeedDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_deed_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getDeedPrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_deed_show_header_key, false);
    }
    public static boolean getDeedPrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_deed_show_page_footer_key, false);
    }
    public static boolean getDeedPrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_deed_use_printed_matter_key, false);
    }
    public static String getDeedPrintFirstPrintedMatter(Context context) {
        return getDeedPrintUseMatter(context) ? get(context, R.string.pref_print_screen_deed_first_printed_matter_key, "") : "";
    }
    public static String getDeedPrintLastPrintedMatter(Context context) {
        return getDeedPrintUseMatter(context) ? get(context, R.string.pref_print_screen_deed_last_printed_matter_key, "") : "";
    }
    public static boolean setDeedPrintLastPrintedMatter(Context context, String str) {
        if (getDeedPrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_deed_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getCaseDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_case_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getCasePrintShowHeader(Context context) {
        return get(context, R.string.pref_print_screen_case_show_header_key, false);
    }
    public static boolean getCasePrintShowFooter(Context context) {
        return get(context, R.string.pref_print_screen_case_show_page_footer_key, false);
    }
    public static boolean getCasePrintUseMatter(Context context) {
        return get(context, R.string.pref_print_screen_case_use_printed_matter_key, false);
    }
    public static String getCasePrintFirstPrintedMatter(Context context) {
        return getCasePrintUseMatter(context) ? get(context, R.string.pref_print_screen_case_first_printed_matter_key, "") : "";
    }
    public static String getCasePrintLastPrintedMatter(Context context) {
        return getCasePrintUseMatter(context) ? get(context, R.string.pref_print_screen_case_last_printed_matter_key, "") : "";
    }
    public static boolean setCasePrintLastPrintedMatter(Context context, String str) {
        if (getCasePrintUseMatter(context)) {
            return set(context, R.string.pref_print_screen_case_last_printed_matter_key, "", str);
        }
        return false;
    }
    public static int getDeliveryNoteDetailPrintLineSize(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_print_screen_delivery_note_detail_line_size_key, R.string.pref_print_screen_detail_line_size_default));
    }
    public static boolean getOrderPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_order_list_printer_key, "0").equals("0");
    }
    public static boolean getInvoicePrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_invoice_list_printer_key, "0").equals("0");
    }
    public static boolean getRetailInvoicePrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_retailinvoice_list_printer_key, "0").equals("0");
    }
    public static boolean getDispatchPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_dispatch_list_printer_key, "0").equals("0");
    }
    public static boolean getWhTransferPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_whTransfer_list_printer_key, "0").equals("0");
    }
    public static boolean getCashPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_cash_list_printer_key, "0").equals("0");
    }
    public static boolean getCreditCardPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_credit_card_list_printer_key, "0").equals("0");
    }
    public static boolean getChequePrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_cheque_list_printer_key, "0").equals("0");
    }
    public static boolean getDeedPrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_deed_list_printer_key, "0").equals("0");
    }
    public static boolean getCasePrinterChoice(Context context) {
        return get(context, R.string.pref_print_screen_case_list_printer_key, "0").equals("0");
    }
    public static boolean getUsePPLZCommand(Context context) {
        return get(context, R.string.pref_use_pplz_command_key, false);
    }
    public static boolean getUseRemoveBlankLines(Context context) {
        return get(context, R.string.pref_print_remove_blank_lines_key, false);
    }
    public static String getPPLZFontId(Context context) {
        return context.getResources().getStringArray(R.array.pref_pplz_font_ids)[StringUtils.convertStringToInt(get(context, R.string.pref_pplz_font_id_key, "0"))];
    }
    public static String getPPLZEncoding(Context context) {
        return context.getResources().getStringArray(R.array.pref_pplz_encoding_values)[StringUtils.convertStringToInt(get(context, R.string.pref_pplz_encoding_key, "0"))];
    }
    public static PrintLanguage getPrintLanguage(Context context) {
        return PrintLanguage.valueOf(get(context, R.string.pref_print_language_key, "STANDARD"));
    }
    public static PrintType getPrintType(Context context) {
        return PrintType.valueOf(get(context, R.string.pref_print_type_key, SqlLiteVariable._TEXT));
    }
    public static PrintDestination getPrintDestination(Context context) {
        return PrintDestination.valueOf(get(context, R.string.pref_print_destination_key, "PRINTER"));
    }
    public static int getPPLZFontWidth(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_pplz_font_width_key, R.string.pref_pplz_font_width_default));
    }
    public static int getPPLZFontHeight(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_pplz_font_height_key, R.string.pref_pplz_font_height_default));
    }
    public static int getPPLZFontSpace(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_pplz_font_space_key, R.string.pref_pplz_font_space_default));
    }
    public static int getPPLZFontMargin(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_pplz_font_margin_key, R.string.pref_pplz_font_margin_default));
    }
    public static String getPrintImage(Context context, int i2) {
        return get(context, i2, context.getString(R.string.pref_print_image_default));
    }
    public static ErpType getErpType(Context context) {
        return ErpType.values()[StringUtils.convertStringToInt(get(context, R.string.pref_erp_type_key, R.string.pref_erp_type_default))];
    }
    public static CommunicationType getCommunicationType(Context context) {
        return CommunicationType.values()[StringUtils.convertStringToInt(get(context, R.string.pref_communication_type_key, 0))];
    }
    public static boolean isDemo(Context context) {
        return get(context, R.string.pref_use_demo_key, false);
    }
    public static String getTigerServerAddress(Context context) {
        return get(context, R.string.pref_tiger_server_address_key, "");
    }
    public static String getTigerWcfSecurityCode(Context context) {
        return get(context, R.string.pref_tiger_security_code_key, "");
    }
    public static int getTigerFirmNumber(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_tiger_firm_number_key, ""));
    }
    public static String getNetsisServerAddress(Context context) {
        return get(context, R.string.pref_netsis_server_address_key, "");
    }
    public static String getNetsisUsername(Context context) {
        return get(context, R.string.pref_netsis_username_key, "");
    }
    public static String getNetsisPassword(Context context) {
        return get(context, R.string.pref_netsis_password_key, "");
    }
    public static String getNetsisDbName(Context context) {
        return get(context, R.string.pref_netsis_db_name_key, "");
    }
    public static String getNetsisDbUsername(Context context) {
        return get(context, R.string.pref_netsis_db_username_key, "");
    }
    public static String getNetsisDbPassword(Context context) {
        return get(context, R.string.pref_netsis_db_password_key, "");
    }
    public static int getNetsisDbType(Context context) {
        return StringUtils.convertStringToInt(get(context, R.string.pref_netsis_db_type_key, R.string.pref_netsis_db_type_default));
    }
    public static String getNetsisBranch(Context context, boolean z) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_netsis_db_branch_code_key), "");
        if (TextUtils.isEmpty(string)) {
            return string;
        }
        String[] split = string.split("\\n");
        return z ? split[1] : split[0];
    }
    private static boolean get(Context context, @StringRes int i2, boolean z) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(i2), z);
    }
    private static int getInt(Context context, @StringRes int i2, @StringRes int i3) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(i2), StringUtils.convertStringToInt(context.getString(i3)));
    }
    private static boolean setInt(Context context, @StringRes int i2, @StringRes int i3, int i4) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(context.getString(i2), i4).apply();
        return edit.commit();
    }
    private static String get(Context context, @StringRes int i2, @StringRes int i3) {
        if (context == null) {
            return "";
        }
        String string = context.getString(i2);
        return getPrefs(context, string).getString(string, context.getString(i3));
    }
    private static boolean setString(Context context, @StringRes int i2, @StringRes int i3, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(i2), str).apply();
        return edit.commit();
    }
    public static String get(Context context, String str, String str2) {
        return getPrefs(context, str).getString(str, str2);
    }
    public static String get(Context context, @StringRes int i2, String str) {
        return get(context, context.getString(i2), str);
    }
    private static boolean set(Context context, @StringRes int i2, String str, String str2) {
        if (context == null) {
            return false;
        }
        return set(context, context.getString(i2), str, str2);
    }
    public static boolean set(Context context, String str, String str2, String str3) {
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor edit = getPrefs(context, str).edit();
        edit.putString(str, str3).apply();
        boolean commit = edit.commit();
        Log.d(TAG, "set: " + commit);
        if (!commit) {
            edit.putString(str, str2).apply();
            commit = edit.commit();
        }
        Log.d(TAG, "set: " + commit);
        return commit;
    }
    public static boolean set(Context context, String str, Boolean bool, Boolean bool2) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(str, bool2.booleanValue()).apply();
        boolean commit = edit.commit();
        Log.d(TAG, "set: " + commit);
        if (!commit) {
            edit.putBoolean(str, bool.booleanValue()).apply();
            commit = edit.commit();
        }
        Log.d(TAG, "set: " + commit);
        return commit;
    }
    public static class TigerUserSettings implements UserSettings {
        Context mContext;

        public TigerUserSettings(Context context) {
            this.mContext = context;
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public ErpType getErpType() {
            return Preferences.getErpType(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public CommunicationType getCommunicationType() {
            return Preferences.getCommunicationType(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public boolean isDemo() {
            return Preferences.isDemo(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public String getServerAddress() {
            return isDemo() ? this.mContext.getString(R.string.WCF_ADDRESS) : Preferences.getTigerServerAddress(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public int getFirmNumber() {
            return isDemo() ? StringUtils.convertStringToInt(this.mContext.getString(R.string.FIRM_NO)) : Preferences.getTigerFirmNumber(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public String getDatabaseName() {
            return StringUtils.formatFirmNumber(getFirmNumber());
        }

        public String getSecurityCode() {
            return isDemo() ? this.mContext.getString(R.string.WCF_SECURITY_CODE) : Preferences.getTigerWcfSecurityCode(this.mContext);
        }
    }
    public static class NetsisUserSettings implements UserSettings {
        Context mContext;

        public NetsisUserSettings(Context context) {
            this.mContext = context;
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public ErpType getErpType() {
            return Preferences.getErpType(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public CommunicationType getCommunicationType() {
            return Preferences.getCommunicationType(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public boolean isDemo() {
            return Preferences.isDemo(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public String getServerAddress() {
            return isDemo() ? this.mContext.getString(R.string.NETSIS_WCF_ADDRESS) : Preferences.getNetsisServerAddress(this.mContext);
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public int getFirmNumber() {
            return getEnterpriseCode();
        }

        @Override // com.proje.mobilesales.features.model.UserSettings
        public String getDatabaseName() {
            return String.format("%d_%d", Integer.valueOf(getEnterpriseCode()), Integer.valueOf(getBranchCode()));
        }

        public String getUsername() {
            return isDemo() ? Preferences.getNetsisAppDemoUsername(Preferences.getSecurePreferences(this.mContext)) : Preferences.getNetsisUsername(this.mContext);
        }

        public String getPassword() {
            return isDemo() ? Preferences.getNetsisAppDemoP(Preferences.getSecurePreferences(this.mContext)) : Preferences.getNetsisPassword(this.mContext);
        }

        public String getDbUsername() {
            return isDemo() ? Preferences.getNetsisDemoDbUser(Preferences.getSecurePreferences(this.mContext)) : Preferences.getNetsisDbUsername(this.mContext);
        }

        public String getDbPassword() {
            return isDemo() ? "" : Preferences.getNetsisDbPassword(this.mContext);
        }

        public String getDbName() {
            return isDemo() ? Preferences.getNetsisDemoDbName(Preferences.getSecurePreferences(this.mContext)) : Preferences.getNetsisDbName(this.mContext);
        }

        public int getDbType() {
            return isDemo() ? StringUtils.convertStringToInt(Preferences.getNetsisDemoDbType(Preferences.getSecurePreferences(this.mContext))) : Preferences.getNetsisDbType(this.mContext);
        }

        public int getBranchCode() {
            return isDemo() ? StringUtils.convertStringToInt(Preferences.getNetsisDemoBranch(Preferences.getSecurePreferences(this.mContext))) : getCode(true);
        }

        public int getEnterpriseCode() {
            return isDemo() ? StringUtils.convertStringToInt(Preferences.getNetsisDemoEntCode(Preferences.getSecurePreferences(this.mContext))) : getCode(false);
        }

        private int getCode(boolean z) {
            return StringUtils.convertStringToInt(Preferences.getNetsisBranch(this.mContext, z).trim().split("-")[0].trim());
        }
    }
    public static boolean getUsePrinterService(Context context) {
        return get(context, R.string.pref_use_printer_service_key, false);
    }
    public static String getPrinterServiceAddress(Context context) {
        return get(context, R.string.pref_printer_service_address_key, "");
    }
    public static String getDefaultPrinter(Context context) {
        return getUsePrinterService(context) ? get(context, R.string.pref_default_printer_key, "") : "";
    }
    public static ActivePeriod getActivePeriod(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_active_period), "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new Gson().fromJson(string, ActivePeriod.class);
    }
    public static void setActivePeriod(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_active_period), str);
        edit.apply();
    }
    public static int getRouteShipRef(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.pref_route_ship_ref), 0);
    }
    public static void setRouteShipRef(Context context, int i2) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(context.getString(R.string.pref_route_ship_ref), i2);
        edit.apply();
    }
    public static String getLastPolicyCheckDate(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_last_policy_check_date), "");
    }
    public static void setLastPolicyCheckDate(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_last_policy_check_date), str);
        edit.apply();
    }
    public static PrivacyPolicyLibParams getAgreementParams(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_agreement_params), "");
        if (string != null && !string.isEmpty()) {
            try {
                return new Gson().fromJson(string, PrivacyPolicyLibParams.class);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    public static void setPrivacyPolicyParams(Context context, String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(context.getString(R.string.pref_agreement_params), str);
        edit.apply();
    }
}
