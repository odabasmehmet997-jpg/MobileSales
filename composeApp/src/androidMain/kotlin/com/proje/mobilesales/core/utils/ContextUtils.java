package com.proje.mobilesales.core.utils;

import android.Manifest;
import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.service.DataSyncService;
import com.proje.mobilesales.features.model.UserCountModel;
import com.proje.mobilesales.features.model.UserCountResult;
import com.proje.mobilesales.features.settings.model.MatterSettings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import static com.proje.mobilesales.core.utils.AppUtils.matterCompare;
import static com.proje.mobilesales.core.utils.AppUtils.matterParseNumber;

public class ContextUtils {
    private static final String TAG = "ContextUtils";
    private static Activity mActivity;
    private static Context mContext;
    public static Activity getmActivity() {
        return mActivity;
    }
    public static void setmActivity(Activity activity) {
        mActivity = activity;
    }
    public static String getStringResource(int i2) {
        if (i2 == 0) {
            return "";
        }
        try {
            return MobileSales.getInstance().getmContext().getString(i2);
        } catch (Exception e2) {
            Log.e(TAG, "getStringResource: ", e2);
            return "";
        }
    }
    public static String getStringResource(int i2, Object... objArr) {
        String str = "";
        try {
            str = MobileSales.getInstance().getmContext().getString(i2);
            return String.format(Locale.ENGLISH, str, objArr);
        } catch (Exception e2) {
            Log.e(TAG, "getStringResource: ", e2);
            return str;
        }
    }
    public static String[] getStringArrayResource(int i2) {
        String[] strArr = {""};
        try {
            return MobileSales.getInstance().getmContext().getResources().getStringArray(i2);
        } catch (Exception e2) {
            Log.e(TAG, "getStringResource: ", e2);
            return strArr;
        }
    }
    public static Context getmContext() {
        return mContext;
    }
    public static void setmContext(Context context) {
        mContext = context;
    }
    @SuppressLint("MissingPermission")
    public static String getDeviceSerialNo() {
        String str = "";
        try {
            if (Build.VERSION.SDK_INT <= 28 && ContextCompat.checkSelfPermission(getmContext(), "android.permission.READ_PHONE_STATE") == 0) {
                str = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                Log.d(TAG, "getDeviceSerialNo() returned: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Settings.Secure.getString(getmContext().getApplicationContext().getContentResolver(), "android_id");
            Log.d(TAG, "getDeviceSerialNo() returned: " + str);
            return str;
        } catch (Exception e2) {
            Log.e(TAG, "getDeviceSerialNo: ", e2);
            return str;
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public static boolean checkInternetConnection() {
        if (Connectivity.isConnected(mContext)) {
            return true;
        }
        try {
            new AlertDialogBuilder.Impl(mContext, (BaseInjectableActivity) mActivity).setMessage(R.string.str_connect_internet_message).setPositiveButton(R.string.str_connect_internet_settings_title, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    ContextUtils.lambdacheckInternetConnection0(dialogInterface, i2);
                }
            }).create().show();
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "checkConnection: ", e2);
            return false;
        }
    }
    public static  void lambdacheckInternetConnection0(DialogInterface dialogInterface, int i2) {
        mContext.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public static boolean checkConnectionWithoutMessage() {
        return Connectivity.isConnected(mContext);
    }
    public static boolean checkGpsConnection() {
        if (Connectivity.isGpsConnect(mContext)) {
            return true;
        }
        try {
            new AlertDialogBuilder.Impl(mContext, (BaseInjectableActivity) mActivity).setMessage(R.string.str_connect_location_message).setPositiveButton(R.string.str_connect_internet_settings_title, new DialogInterface.OnClickListener() {
                void DialogInterfaceOnClickListener() {
                }
                public void onClick(DialogInterface dialogInterface, int i2) {
                    ContextUtils.mContext.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            }).create().show();
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "checkConnection: ", e2);
            return false;
        }
    }
    static class DialogInterfaceOnClickListener implements DialogInterface.OnClickListener {
         DialogInterfaceOnClickListener() {
        }
        public void onClick(DialogInterface dialogInterface, int i2) {
            ContextUtils.mContext.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }
    public static int getLanguageId() {
        try {
            String applicationLanguage = new SharedPreferencesHelper(getmContext()).getApplicationLanguage();
            if (applicationLanguage.equals("tr")) {
                return 1;
            }
            if (applicationLanguage.equals("en")) {
                return 2;
            }
            return applicationLanguage.equals("ru") ? 5 : 1;
        } catch (Exception e2) {
            Log.e(TAG, "getLanguageId: ", e2);
            return 1;
        }
    }
    public static String getTextAssets(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MobileSales.getInstance().getmContext().getAssets().open(str), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (Exception e2) {
            Log.e(TAG, "getTextAssets: ", e2);
            return "";
        }
    }
    public static void createFile(String str, String str2, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileWriter fileWriter = new FileWriter(new File(file, str2));
            fileWriter.append((CharSequence) str3);
            fileWriter.flush();
            Log.d(TAG, "createFile: " + ("File generated with name " + str2 + ".txt"));
            fileWriter.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    public static void migratePreviousDbFilesIfExists(String str) {
        File[] listFiles;
        String str2 = Environment.getExternalStorageDirectory().getPath() + "/MobileSalesEx/";
        File databasePath = getmContext().getDatabasePath(str);
        if (databasePath.exists()) {
            return;
        }
        File file = new File(str2);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles(new FilenameFilter() { 
            public String f0;

            public void ContextUtilsExternalSyntheticLambda4(String str3) {
                f0 = str3;
            } 
            public boolean accept(File file2, String str3) {
                boolean lambdamigratePreviousDbFilesIfExists1;
                lambdamigratePreviousDbFilesIfExists1 = ContextUtils.lambdamigratePreviousDbFilesIfExists1(f0, file2, str3);
                return lambdamigratePreviousDbFilesIfExists1;
            }
        })) != null && listFiles.length != 0) {
            boolean z = false;
            for (File file2 : listFiles) {
                z = MsFileUtils.copyFile(str2, file2.getName(), databasePath.getParent() + "/");
                if (!z) {
                    break;
                }
            }
            if (z) {
                for (File file3 : listFiles) {
                    file3.delete();
                }
            }
            Log.d("DbFiles", String.valueOf(listFiles.length));
        }
    }
    public static   boolean lambdamigratePreviousDbFilesIfExists1(String str, File file, String str2) {
        return str2.startsWith(str);
    }
    public static void deleteNoteOnSD(String str) {
        try {
            if (new File(str).delete()) {
                Log.d(TAG, "deleteNoteOnSD: File " + str + " deleted successfully.");
            } else {
                Log.e(TAG, "deleteNoteOnSD: Failed to delete file " + str);
            }
        } catch (Exception e2) {
            Log.e(TAG, "deleteNoteOnSD: Error occurred while deleting file " + str, e2);
        }
    } 
    private static Drawable getDrawable(  int i2) {
        try {
            return ContextCompat.getDrawable(mContext, i2);
        } catch (Exception e2) {
            Log.e(TAG, "getDrawable: ", e2);
            return null;
        }
    }
    public static void toggleMenuItemVisible(MenuItem menuItem, boolean z) {
        if (menuItem == null) {
            return;
        }
        try {
            menuItem.setVisible(z);
        } catch (Exception e2) {
            Log.e(TAG, "toggleMenuItemVisible: ", e2);
        }
    }
    public static void setMenuItemOnlineVisible(MenuItem menuItem, boolean z) {
        if (menuItem == null) {
            return;
        }
        try {
            toggleMenuItemVisible(menuItem, z);
        } catch (Exception e2) {
            Log.e(TAG, "setMenuOnlineTotal: ", e2);
        }
    }
    public static void startDataSyncService() {
        if (isMyServiceRunning()) {
            return;
        }
        mContext.startService(new Intent(mContext, DataSyncService.class));
    }
    private static boolean isMyServiceRunning() {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (DataSyncService.class.getName().equals(it.next().service.getClassName())) {
                Log.d(TAG, "isMyServiceRunning() called with: serviceClass = [" + DataSyncService.class + "] service is running");
                return true;
            }
        }
        Log.d(TAG, "isMyServiceRunning() called with: serviceClass = [" + DataSyncService.class + "] service is not running");
        return false;
    }
    public static void showToast(String str) {
        Toast.makeText(MobileSales.getInstance().getmContext(), str, Toast.LENGTH_LONG).show();
    }
    public static void showToast(int i2) {
        Toast.makeText(MobileSales.getInstance().getmContext(), i2, Toast.LENGTH_LONG).show();
    }
    public static String formatStringEnglish(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }
    public static String formatStringEnglish(int i2, Object... objArr) {
        return String.format(Locale.ENGLISH, MobileSales.getInstance().getmContext().getString(i2), objArr);
    }
    public static boolean isRtl() {
        return mContext.getResources().getConfiguration().getLayoutDirection() == 128;
    }
    @SuppressLint("ResourceType")
    public static void showMatterDialog(Context context, String str, MatterSettings matterSettings, ResponseListener responseListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_custom_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(inflate);
        (( TextView ) inflate.findViewById(R.id.txtViewOldMatter)).setText(String.format("%s - %s", matterSettings.getFirstMatterNo(), matterSettings.getLastMatterNo()));
        (( TextView ) inflate.findViewById(R.id.txtViewTitle)).setText(str);
        builder.setCancelable(false).setPositiveButton(17039370, new DialogInterface.OnClickListener((EditText) inflate.findViewById(R.id.edtDialogUserInput), matterSettings, responseListener) {
            public final EditText f0 = null;
            public final MatterSettings f1 = null;
            public final ResponseListener f2 = null;

            public   void onClick(DialogInterface dialogInterface, int i) {
                ContextUtils.lambdashowMatterDialog2(this.f0, this.f1, this.f2, dialogInterface, i);
            }
        }).setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            public   void onClick(DialogInterface dialogInterface, int i) {
                ContextUtils.lambdashowMatterDialog3(responseListener, dialogInterface, i);
            }
        });
        builder.create().show();
    }
    public static  void lambdashowMatterDialog2(EditText editText, MatterSettings matterSettings, ResponseListener responseListener, DialogInterface dialogInterface, int i2) {
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            if (matterCompare(true, matterParseNumber(matterSettings.getLastMatterNo()), matterParseNumber(editText.getText().toString()))) {
                responseListener.onResponse(editText.getText().toString());
                dialogInterface.dismiss();
                return;
            } else {
                responseListener.onResponse("");
                Context context = mContext;
                Toast.makeText(context, context.getString(R.string.str_matter_last_value_less), Toast.LENGTH_LONG).show();
                return;
            }
        }
        responseListener.onResponse("");
        Context context2 = mContext;
        Toast.makeText(context2, context2.getString(R.string.str_matter_input_last_value_title), Toast.LENGTH_LONG).show();
    }
    public static  void lambdashowMatterDialog3(ResponseListener responseListener, DialogInterface dialogInterface, int i2) {
        responseListener.onError("");
        dialogInterface.dismiss();
    }
    @SuppressLint("MissingPermission")
    public static double getLatitude() {
        if (MobileSales.getInstance() == null || MobileSales.getInstance().getCurrentLocation() == null) {
            return 0.0d;
        }
        return MobileSales.getInstance().getCurrentLocation().getLatitude();
    }
    public static double getLongitude() {
        if (MobileSales.getInstance() == null || MobileSales.getInstance().getCurrentLocation() == null) {
            return 0.0d;
        }
        return MobileSales.getInstance().getCurrentLocation().getLongitude();
    }
    public static boolean isAutoTimeAndTimeZoneEnabled() {
        Context context = mContext;
        if (context == null || context.getContentResolver() == null) {
            return true;
        }
        int i2 = Settings.Global.getInt(mContext.getContentResolver(), "auto_time", 0);
        int i3 = Settings.Global.getInt(mContext.getContentResolver(), "auto_time_zone", 1);
        if (i2 == 1 && i3 == 0) {
            Date convertStringToDateTime = DateAndTimeUtils.convertStringToDateTime(ErpCreator.getInstance().getmBaseErp().getServerLongTime());
            i3 = StringUtils.convertBooleanToInt(Boolean.valueOf(DateAndTimeUtils.isDateBetweenDates(DateAndTimeUtils.convertStringToDateTime(DateAndTimeUtils.nowDateTime()), DateAndTimeUtils.addTime(convertStringToDateTime, 12, -60), DateAndTimeUtils.addTime(convertStringToDateTime, 12, 60))));
        }
        return i2 == 1 && i3 == 1;
    }
    public static boolean checkAutoTimeAndTimeZoneEnabledAndShowDialog() {
        Context context;
        boolean isAutoTimeAndTimeZoneEnabled = isAutoTimeAndTimeZoneEnabled();
        if (!isAutoTimeAndTimeZoneEnabled && (context = mContext) != null) {
            new AlertDialog.Builder(context).setMessage(R.string.str_auto_time_enabled_error).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        return isAutoTimeAndTimeZoneEnabled;
    }
    static  class C25792 {
        static final   int[] SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult;
        static {
            int[] iArr = new int[UserCountResult.values().length];
            SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult = iArr;
            try {
                iArr[UserCountResult.CouldNotReadWorUserCount.ordinal()] = 1;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult[UserCountResult.CouldNotReadLicenseUserCount.ordinal()] = 2;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult[UserCountResult.WorUserCountExceedsLicenseUserCount.ordinal()] = 3;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult[UserCountResult.UnknownError.ordinal()] = 4;
            } catch (NoSuchFieldError _) {
            }
        }
    }
    public static String getUserCountErrorString(UserCountModel userCountModel) {
        int i2 = C25792.SwitchMapcomprojemobilesalesfeaturesmodelUserCountResult[userCountModel.userCountResult().ordinal()];
        if (i2 == 1) {
            return mContext.getString(R.string.str_could_not_read_wor_user_count);
        }
        if (i2 == 2) {
            return mContext.getString(R.string.str_could_not_read_license_user_count);
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return mContext.getString(R.string.str_unknown_error_on_check_user_count);
            }
            return "";
        }
        return mContext.getString(R.string.str_wor_user_count_exceeds_license_user_count) + System.getProperty("line.separator") + System.getProperty("line.separator") + mContext.getString(R.string.str_license_user_count) + userCountModel.licenseUserCount() + System.getProperty("line.separator") + mContext.getString(R.string.str_wor_user_count) + userCountModel.worUserCount();
    }
}
