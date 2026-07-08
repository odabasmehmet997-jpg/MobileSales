package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.netsis.NetsisTokenManager;
import com.proje.mobilesales.core.reportparser.ReportLayoutColumn;
import com.proje.mobilesales.core.reportparser.ReportLayoutDefaultItem;
import com.proje.mobilesales.core.reportparser.ReportLayoutGroup;
import com.proje.mobilesales.core.reportparser.ReportLayoutItem;
import com.proje.mobilesales.core.reportparser.ReportLayoutRow;
import com.proje.mobilesales.core.reportparser.ReportLayoutTabbedGroup;
import com.proje.mobilesales.core.reportparser.ReportLayoutViewCard;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.ErpRights;
import com.proje.mobilesales.features.model.TransferGet;
import com.proje.mobilesales.features.model.UserMenuRights;
import com.proje.mobilesales.features.model.UserSettings;
import java.lang.reflect.Type;
import java.util.HashMap;

public class SharedPreferencesHelper {
    private static final String TAG = "LogoSharedPrerences";
    private static final String _CUSTOMER_SORT_TYPE_PREF = "CustomerSortPref";
    private static final String _EDESPATCH_PDF_PATH = "EDespatchPdfPath";
    private static final String _IS_PRIVACYPOLICY_READ = "IsPrivacyPolicyRead";
    private static final String _LOG_PATH = "LogPath";
    private static final String _NETSIS_TOKEN_MANAGER = "NetsisTokenManager";
    private static final String _PREF_NAME = "CommonPrefs";
    private static final String _PRINT_TO_FILE_PATH = "PrintToFilePath";
    private static final String _PRODUCT_SORT_TYPE_PREF = "ProductSortPref";
    private static final String _REPORT_PDF_PATH = "ReportPdfPath";
    private static final String _SALES_PDF_PATH = "SalesPdfPath";
    private static final String _SETTINGS_PATH = "SettingsPath";
    private static final String _USER_APPLICATION_LANGUAGE = "UserLanguage";
    private static final String _USER_CLCARD_LAST_ACTION_DATE = "ClCardLastActionDate";
    private static final String _USER_CUSTOMER_SELECT_ROUTE = "UserCustomerSelectRoute";
    private static final String _USER_CUSTOMER_SELECT_TYPE = "UserCustomerSelectType";
    private static final String _USER_ERP_RIGHTS_PREF = "ErpRights";
    private static final String _USER_ITEM_LAST_ACTION_DATE = "ItemLastActionDate";
    private static final String _USER_MENU_RIGHTS_PREF = "MenuRights";
    private static final String _USER_PREF = "User";
    private static final String _USER_PRINTER_CHOICE = "UserPrinterChoice";
    private static final String _USER_REMEMBER_ME = "RememberMe";
    private static final String _USER_SETTINGS_PREF = "UserSettings";
    private static final String _USER_SETTINGS_PREF_TEMP = "UserSettingsTemp";
    private static final String _USER_TRANSFER_GET = "TransferGet";
    private static final String _WCF_VERSION = "WcfVersion";
    private final SharedPreferences mSharedPreferences = MobileSales.getInstance().getmContext().getSharedPreferences("CommonPrefs", 0);
    public SharedPreferencesHelper(Context context) {
    }
    public SharedPreferencesHelper() {
    }
    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }
    public User getUser() {
        String preferences = getPreferences(_USER_PREF);
        User user = new User();
        try {
            User user2 = new Gson().fromJson(preferences, User.class);
            return user2 == null ? new User() : user2;
        } catch (Exception e2) {
            Log.e(TAG, "getUser: ", e2);
            return user;
        }
    }
    public void saveUser(User user) {
        try {
            setPreferences(_USER_PREF, new Gson().toJson(user));
        } catch (Exception e2) {
            Log.e(TAG, "saveUser: ", e2);
        }
    }
    public boolean saveUserSettings(UserSettings userSettings) {
        try {
            setPreferences(_USER_SETTINGS_PREF, new Gson().toJson(userSettings));
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "saveUserSettings: ", e2);
            return false;
        }
    }
    public boolean saveUserSettingsTemp(UserSettings userSettings) {
        try {
            setPreferences(_USER_SETTINGS_PREF_TEMP, new Gson().toJson(userSettings));
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "saveUserSettings: ", e2);
            return false;
        }
    }
    public String getApplicationLanguage() {
        String preferences = getPreferences(_USER_APPLICATION_LANGUAGE);
        if (!preferences.equals("")) {
            return preferences;
        }
        saveApplicationLanguage("tr");
        return "tr";
    }
    public void saveRememberMe(boolean z) {
        setPreferences(_USER_REMEMBER_ME, String.valueOf(z));
    }
    public boolean getRememberMe() {
        String preferences = getPreferences(_USER_REMEMBER_ME);
        if (preferences.isEmpty()) {
            saveRememberMe(false);
            return false;
        }
        return Boolean.parseBoolean(preferences);
    }
    public void saveApplicationLanguage(String str) {
        setPreferences(_USER_APPLICATION_LANGUAGE, str);
    }
    public void saveTransferGet(TransferGet transferGet) {
        try {
            setPreferences(_USER_TRANSFER_GET, new Gson().toJson(transferGet));
        } catch (Exception e2) {
            Log.e(TAG, "saveTransferGet: ", e2);
        }
    }
    public TransferGet getTransferGet() {
        String preferences = getPreferences(_USER_TRANSFER_GET);
        if (preferences.isEmpty()) {
            return new TransferGet();
        }
        TransferGet transferGet = new TransferGet();
        try {
            TransferGet transferGet2 = new Gson().fromJson(preferences, TransferGet.class);
            return transferGet2 == null ? new TransferGet() : transferGet2;
        } catch (Exception e2) {
            Log.e(TAG, "getTransferGet: ", e2);
            return transferGet;
        }
    }
    public UserMenuRights getUserMenuRights() {
        String preferences = getPreferences(_USER_MENU_RIGHTS_PREF);
        UserMenuRights userMenuRights = new UserMenuRights();
        try {
            UserMenuRights userMenuRights2 = new Gson().fromJson(preferences, UserMenuRights.class);
            return userMenuRights2 == null ? new UserMenuRights() : userMenuRights2;
        } catch (Exception e2) {
            Log.e(TAG, "getUserMenuRights: ", e2);
            return userMenuRights;
        }
    }
    public void saveUserMenuRights(UserMenuRights userMenuRights) {
        try {
            setPreferences(_USER_MENU_RIGHTS_PREF, new Gson().toJson(userMenuRights));
        } catch (Exception e2) {
            Log.e(TAG, "saveUserMenuRights: ", e2);
        }
    }
    public void saveLastDate(boolean z, double d2) {
        try {
            if (z) {
                setPreferences(_USER_CLCARD_LAST_ACTION_DATE, d2);
            } else {
                setPreferences(_USER_ITEM_LAST_ACTION_DATE, d2);
            }
        } catch (Exception e2) {
            Log.e(TAG, "saveErpRights: ", e2);
        }
    }
    public double getLastDate(boolean z) {
        double preferencesDouble;
        try {
            if (z) {
                preferencesDouble = getPreferencesDouble(_USER_CLCARD_LAST_ACTION_DATE);
            } else {
                preferencesDouble = getPreferencesDouble(_USER_ITEM_LAST_ACTION_DATE);
            }
            return preferencesDouble;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }
    public ErpRights getErpRights() {
        String preferences = getPreferences(_USER_ERP_RIGHTS_PREF);
        ErpRights erpRights = new ErpRights();
        try {
            ErpRights erpRights2 = new Gson().fromJson(preferences, ErpRights.class);
            return erpRights2 == null ? new ErpRights() : erpRights2;
        } catch (Exception e2) {
            Log.e(TAG, "getErpRights: ", e2);
            return erpRights;
        }
    }
    public void saveErpRights(ErpRights erpRights) {
        try {
            setPreferences(_USER_ERP_RIGHTS_PREF, new Gson().toJson(erpRights));
        } catch (Exception e2) {
            Log.e(TAG, "saveErpRights: ", e2);
        }
    }
    public String getUserCustomerSelectType() {
        try {
            String preferences = getPreferences(_USER_CUSTOMER_SELECT_TYPE);
            return preferences == null ? "" : preferences;
        } catch (Exception e2) {
            Log.e(TAG, "getUserMenuRights: ", e2);
            return "";
        }
    }
    public void saveUserCustomerSelectType(String str) {
        try {
            setPreferences(_USER_CUSTOMER_SELECT_TYPE, str);
        } catch (Exception e2) {
            Log.e(TAG, "saveUserMenuRights: ", e2);
        }
    }
    public String getUserCustomerSelectRoute() {
        try {
            String preferences = getPreferences(_USER_CUSTOMER_SELECT_ROUTE);
            return preferences == null ? "" : preferences;
        } catch (Exception e2) {
            Log.e(TAG, "getUserMenuRights: ", e2);
            return "";
        }
    }
    public void saveUserCustomerSelectRoute(String str) {
        try {
            setPreferences(_USER_CUSTOMER_SELECT_ROUTE, str);
        } catch (Exception e2) {
            Log.e(TAG, "saveUserMenuRights: ", e2);
        }
    }
    public HashMap<String, Boolean> getPrinterChoice() {
        String preferences = getPreferences(_USER_PRINTER_CHOICE);
        HashMap<String, Boolean> hashMap = new HashMap<>();
        try {
            try {
                HashMap<String, Boolean> hashMap2 = (HashMap) new Gson().fromJson(preferences, HashMap.class);
                if (hashMap2 == null || hashMap2.size() > 0) {
                    return hashMap2;
                }
            } catch (Exception e2) {
                Log.e(TAG, "getErpRights: ", e2);
                if (hashMap.size() > 0) {
                    return hashMap;
                }
            }
            return null;
        } catch (Throwable th) {
            hashMap.size();
            throw th;
        }
    }
    public boolean savePrinterChoice(HashMap<String, Boolean> hashMap) {
        try {
            setPreferences(_USER_PRINTER_CHOICE, new Gson().toJson(hashMap));
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "savePrinterChoice: ", e2);
            return false;
        }
    }
    public void deletePrinterChoice() {
        try {
            removePreferences(_USER_PRINTER_CHOICE);
        } catch (Exception e2) {
            Log.e(TAG, "deletePrinterChoice: ", e2);
        }
    }
    public int getProductSortChoice() {
        try {
            return getPreferencesInt(_PRODUCT_SORT_TYPE_PREF);
        } catch (Exception e2) {
            Log.e(TAG, "getProductSortChoice: ", e2);
            return 0;
        }
    }
    public void saveProductSortChoice(int i2) {
        setPreferences(_PRODUCT_SORT_TYPE_PREF, i2);
    }
    public int getCustomerSortChoice() {
        try {
            return getPreferencesInt(_CUSTOMER_SORT_TYPE_PREF);
        } catch (Exception e2) {
            Log.e(TAG, "getCustomerSortChoice: ", e2);
            return -1;
        }
    }
    public void saveCustomerSortChoice(int i2) {
        setPreferences(_CUSTOMER_SORT_TYPE_PREF, i2);
    }
    public void saveNetsisTokenManager(NetsisTokenManager netsisTokenManager) {
        try {
            setPreferences(_NETSIS_TOKEN_MANAGER, new Gson().toJson(netsisTokenManager));
        } catch (Exception e2) {
            Log.e(TAG, "saveNetsisTokenManager: ", e2);
        }
    }
    public NetsisTokenManager getNetsisTokenManager() {
        try {
            NetsisTokenManager netsisTokenManager = new Gson().fromJson(getPreferences(_NETSIS_TOKEN_MANAGER), NetsisTokenManager.class);
            return netsisTokenManager == null ? new NetsisTokenManager() : netsisTokenManager;
        } catch (Exception e2) {
            Log.e(TAG, "getTokenManager: ", e2);
            return new NetsisTokenManager();
        } finally {
            new NetsisTokenManager();
        }
    }
    private String getPreferences(String str) {
        try {
            return this.mSharedPreferences.getString(str, "");
        } catch (Exception e2) {
            Log.e(TAG, "getPreferences: ", e2);
            return "";
        }
    }
    private int getPreferencesInt(String str) {
        try {
            return this.mSharedPreferences.getInt(str, 0);
        } catch (Exception e2) {
            Log.e(TAG, "getPreferences: ", e2);
            return 0;
        }
    }
    private double getPreferencesDouble(String str) {
        long j2 = 0;
        try {
            j2 = this.mSharedPreferences.getLong(str, 0L);
        } catch (Exception e2) {
            Log.e(TAG, "getPreferences: ", e2);
        }
        return Double.longBitsToDouble(j2);
    }
    private void setPreferences(String str, int i2) {
        try {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.putInt(str, i2);
            edit.apply();
        } catch (Exception e2) {
            Log.e(TAG, "setPreferences: ", e2);
        }
    }
    private void setPreferences(String str, double d2) {
        try {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.putLong(str, Double.doubleToRawLongBits(d2));
            edit.apply();
        } catch (Exception e2) {
            Log.e(TAG, "setPreferences: ", e2);
        }
    }
    void setPreferences(String str, String str2) {
        try {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.putString(str, str2);
            edit.apply();
        } catch (Exception e2) {
            Log.e(TAG, "setPreferences: ", e2);
        }
    }
    private void removePreferences(String str) {
        try {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.remove(str);
            edit.apply();
        } catch (Exception e2) {
            Log.e(TAG, "removePreferences: ", e2);
        }
    }
    private void removePreferencesByCommit(String str) {
        try {
            SharedPreferences.Editor edit = this.mSharedPreferences.edit();
            edit.remove(str);
            edit.commit();
        } catch (Exception e2) {
            Log.e(TAG, "removePreferences: ", e2);
        }
    }
    public void removeTransferGet() {
        removePreferences(_USER_TRANSFER_GET);
    }
    public <T> T getObject(String str, Type type) {
        T t;
        String json = getPreferences(str);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            t = new Gson().fromJson(json, type);
        } catch (Exception e2) {
            Log.e(TAG, "getObject: ", e2);
            t = null;
        }
        return t;
    }
    public <T> void setObject(String str, T t) {
        try {
            setPreferences(str, new Gson().toJson(t));
        } catch (Exception e2) {
            Log.e(TAG, "setObject : ", e2);
        }
    }
    public <T> T getUserDefinedReportObject(String str, Type type) {
        try {
            return new GsonBuilder().registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(ReportLayoutItem.class, "type").registerSubtype(ReportLayoutTabbedGroup.class).registerSubtype(ReportLayoutGroup.class).registerSubtype(ReportLayoutColumn.class).registerSubtype(ReportLayoutViewCard.class).registerSubtype(ReportLayoutDefaultItem.class).registerSubtype(ReportLayoutRow.class)).create().fromJson(getPreferences(str), type);
        } catch (Exception e2) {
            Log.e(TAG, "getObject: ", e2);
            return null;
        }
    }
    public <T> void setUserDefinedReportObject(String str, T t) {
        try {
            setPreferences(str, new GsonBuilder().registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(ReportLayoutItem.class, "type").registerSubtype(ReportLayoutTabbedGroup.class).registerSubtype(ReportLayoutGroup.class).registerSubtype(ReportLayoutColumn.class).registerSubtype(ReportLayoutViewCard.class).registerSubtype(ReportLayoutDefaultItem.class).registerSubtype(ReportLayoutRow.class)).create().toJson(t));
        } catch (Exception e2) {
            Log.e(TAG, "setObject : ", e2);
        }
    }
    public void saveWCFVersion(String str) {
        setPreferences(_WCF_VERSION, str);
    }
    public String getWCFVersion() {
        String preferences = getPreferences(_WCF_VERSION);
        return preferences == null ? "1.0.0.0" : preferences;
    }
    public void saveReportPdfPath(Uri uri) {
        if (uri != null) {
            setPreferences(_REPORT_PDF_PATH, uri.toString());
        }
    }
    public void removeReportPdfPath() {
        removePreferences(_REPORT_PDF_PATH);
    }
    public Uri getReportPdfPath() {
        try {
            String preferences = getPreferences(_REPORT_PDF_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "getReportPdfPath", e2);
            return null;
        }
    }
    public void saveEDespatchPdfPath(Uri uri) {
        if (uri != null) {
            setPreferences(_EDESPATCH_PDF_PATH, uri.toString());
        }
    }
    public void removeEDespatchPdfPath() {
        removePreferences(_EDESPATCH_PDF_PATH);
    }
    public Uri getEDespatchPdfPath() {
        try {
            String preferences = getPreferences(_EDESPATCH_PDF_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "getEDespatchPdfPath", e2);
            return null;
        }
    }
    public void saveSalesPdfPath(Uri uri) {
        if (uri != null) {
            setPreferences(_SALES_PDF_PATH, uri.toString());
        }
    }
    public void removeSalesPdfPath() {
        removePreferences(_SALES_PDF_PATH);
    }
    public Uri getSalesPdfPath() {
        try {
            String preferences = getPreferences(_SALES_PDF_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "getSalesPdfPath", e2);
            return null;
        }
    }
    public void saveSettingsPath(Uri uri) {
        if (uri != null) {
            setPreferences(_SETTINGS_PATH, uri.toString());
        }
    }
    public void removeSettingsPath() {
        removePreferences(_SETTINGS_PATH);
    }
    public Uri getSettingsPath() {
        try {
            String preferences = getPreferences(_SETTINGS_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "settingsPath", e2);
            return null;
        }
    }
    public void saveLogPath(Uri uri) {
        if (uri != null) {
            setPreferences(_LOG_PATH, uri.toString());
        }
    }
    public void removeLogPath() {
        removePreferences(_LOG_PATH);
    }
    public Uri getLogPath() {
        try {
            String preferences = getPreferences(_LOG_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "logPath", e2);
            return null;
        }
    }
    public void savePrintToFilePath(Uri uri) {
        if (uri != null) {
            setPreferences(_PRINT_TO_FILE_PATH, uri.toString());
        }
    }
    public void removePrintToFilePath() {
        removePreferences(_PRINT_TO_FILE_PATH);
    }
    public Uri getPrintToFilePath() {
        try {
            String preferences = getPreferences(_PRINT_TO_FILE_PATH);
            if (preferences == null) {
                return null;
            }
            return Uri.parse(preferences);
        } catch (Exception e2) {
            Log.e(TAG, "printToFilePath", e2);
            return null;
        }
    }
    public boolean permissionHasBeenAskedBefore(String str) {
        try {
            String preferences = getPreferences(str);
            if (preferences == null) {
                return false;
            }
            return preferences.equals(BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "permissionHasBeenAskedBefore: ", e2);
            return false;
        }
    }
    public void savePermissionAskedValue(String str) {
        try {
            setPreferences(str, BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "savePermissionAskedValue: ", e2);
        }
    }
    public void markPrivacyPolicyAsRead() {
        try {
            setPreferences(_IS_PRIVACYPOLICY_READ, BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "markPrivacyPolicyAsRead: ", e2);
        }
    }
    public boolean isPrivacyPolicyRead() {
        try {
            return getPreferences(_IS_PRIVACYPOLICY_READ).equals(BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e(TAG, "isPrivacyPolicyRead: ", e2);
            return false;
        }
    }
}
