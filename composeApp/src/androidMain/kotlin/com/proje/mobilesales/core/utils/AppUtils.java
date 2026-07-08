package com.proje.mobilesales.core.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.*;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.*;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;
import androidx.core.net.MailTo;
import androidx.documentfile.provider.DocumentFile;
import androidx.preference.PreferenceManager;
import androidx.work.WorkManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ObjectType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.DataSyncService;
import com.proje.mobilesales.core.service.DataTransferSyncService;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.service.PrintService;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.model.UserPreferenceSettings;
import com.proje.mobilesales.features.notification.util.NotificationConstants;
import com.proje.mobilesales.features.settings.expr.MatterRegex;
import retrofit2.HttpException;

import java.io.*;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


public final class AppUtils {
    public static final String SETTINGS_EXPORT_PATH = "/exportSettings/";

    public static void lambdaalert0(DialogInterface dialogInterface, int i2) {
    }

    public static boolean matterCompare(boolean z, long j2, long j3) {
        if (z) {
            return j2 < j3;
        } else return j2 > j3;
    }

    public static boolean matterEqualCompare(long j2, long j3) {
        return j2 <= j3;
    }

    private AppUtils() {
    }

    public static void restart(Activity activity, boolean z) {
        activity.recreate();
    }

    public static void exitApplication(Activity activity) {
        activity.finish();
        Intent intent = activity.getIntent();
        intent.setFlags(268468224);
        activity.startActivity(intent);
    }

    public static void restartBaseErp(Context context) {
        if (ErpCreator.getInstance().getmBaseErp() == null) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(268468224);
            context.startActivity(intent);
        }
    }

    public static void exitApplication(Activity activity, Class<?> cls) {
        Preferences.setActivePeriod(ContextUtils.getmContext(), new Gson().toJson(null));
        WorkManager.getInstance(activity).cancelAllWorkByTag(NotificationConstants.PERIODIC_WORK_REQUEST_TAG);
        stopMyService(activity, LocationUpdatesService.class);
        stopMyService(activity, PrintService.class);
        stopMyService(activity, DataTransferSyncService.class);
        stopMyService(activity, DataSyncService.class);
        NotificationManagerCompat.from(activity.getApplicationContext()).cancel(NotificationConstants.PERIODIC_WORK_NOTIFICATION_ID);
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        User user = sharedPreferencesHelper.getUser();
        if (user != null) {
            user.setLoggedIn(false);
            if (!sharedPreferencesHelper.getRememberMe()) {
                user.setPassword("");
            }
            sharedPreferencesHelper.saveUser(user);
        }
        Intent intent = new Intent(activity, cls);
        intent.setFlags(335577088);
        activity.startActivity(intent);
        activity.finish();
    }

    public static LayoutInflater createLayoutInflater(Context context) {
        return LayoutInflater.from(new ContextThemeWrapper(context, R.style.LogoTheme));
    }

    public static void startActivityNew(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }

    public static int getThemedResId(Context context, @AttrRes int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i2});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static float getDimension(Context context, @StyleRes int i2, @AttrRes int i3) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i2, new int[]{i3});
        float dimension = obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        return dimension;
    }

    public static Intent makeEmailIntent(@Nullable String[] strArr) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        return intent;
    }

    public static Intent makeContactIntent(@NonNull String str) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + str));
        return intent;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"})
    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = Connectivity.getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isMyServiceRunning(Context context, Class<?> cls) {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (cls.getName().equals(it.next().service.getClassName())) {
                Log.d("ContentValues", "isMyServiceRunning() called with: serviceClass = [" + cls + "] service is running");
                return true;
            }
        }
        Log.d("ContentValues", "isMyServiceRunning() called with: serviceClass = [" + cls + "] service is not running");
        return false;
    }

    public static boolean startMyService(Context context, Class<?> cls) {
        context.startService(new Intent(context, cls));
        return isMyServiceRunning(context, cls);
    }

    public static void stopMyService(Context context, Class<?> cls) {
        if (isMyServiceRunning(context, cls)) {
            context.stopService(new Intent(context, cls));
        }
    }

    public static int convertDpToPixel(int i2, int i3) {
        return i2 * (i3 / 160);
    }

    public static int convertPixelToDp(int i2, int i3) {
        return i2 / (i3 / 160);
    }

    public static int getScreenDensityDp(Context context) {
        return context.getResources().getConfiguration().densityDpi;
    }

    public static int getScreenWidthDp(Context context) {
        return context.getResources().getConfiguration().screenWidthDp;
    }

    public static int getScreenHeightDp(Context context) {
        return context.getResources().getConfiguration().screenHeightDp;
    }

    public static int getDimensionInDp(Context context, @DimenRes int i2) {
        return (int) (context.getResources().getDimension(i2) / context.getResources().getDisplayMetrics().density);
    }

    public static long matterParseNumber(String str) {
        ArrayList<String> matterFind = new MatterRegex(ContextUtils.getmContext().getString(R.string.matter_parse_regex)).getMatterFind(str);
        if (matterFind.size() > 0) {
            return StringUtils.convertStringToLong(matterFind.get(matterFind.size() - 1));
        }
        return 0L;
    }

    public static boolean maxMatterNoControl(String str, String str2) {
        return matterEqualCompare(matterParseNumber(str), matterParseNumber(str2));
    }

    public static String getFullFileName(ContentResolver contentResolver, @NonNull Uri uri) {
        Cursor query = contentResolver.query(uri, null, null, null, null);
        try {
            try {
                int columnIndex = query.getColumnIndex("_display_name");
                query.moveToFirst();
                return query.getString(columnIndex);
            } catch (Exception e2) {
                Log.e("ContentValues", e2.getLocalizedMessage());
                query.close();
                return null;
            }
        } finally {
            query.close();
        }
    }

    public static String getImageFileName() {
        return String.format("MSI_%s", new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date()));
    }

    public static String getTextFileName() {
        return String.format("MST_%s", new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date()));
    }

    public static String getSettingFileName() {
        return String.format("settings_%s", new SimpleDateFormat("yyyyMMddHHmm'.mssettings'").format(new Date()));
    }

    public static void handleNetworkErrorWithMessage(Throwable th) {
        ContextUtils.showToast(handleNetworkError(th));
    }

    public static String handleNetworkError(Throwable th) {
        if (th instanceof HttpException) {
            return ContextUtils.getStringResource(R.string.exp_45_undefined);
        }
        if ((th instanceof SocketTimeoutException) || (th instanceof SocketException)) {
            return ContextUtils.getStringResource(R.string.exp_28_socket_timeout);
        }
        if (th instanceof IOException) {
            return ContextUtils.getStringResource(R.string.exp_29_io);
        }
        return th.getMessage();
    }

    public static Boolean alert(CharSequence charSequence) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ContextUtils.getmContext());
        builder.setMessage(charSequence).setCancelable(false).setNegativeButton(ContextUtils.getStringResource(R.string.str_okey), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.core.utils.AppUtilsExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                lambdaalert0(dialogInterface, i2);
            }
        });
        builder.create().show();
        return Boolean.FALSE;
    }

    public static void calculateTotals(String str, String str2, String str3, String str4) {
        View inflate = LayoutInflater.from(ContextUtils.getmContext()).inflate(R.layout.totaldialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(ContextUtils.getmContext());
        if (FTypeControlUtils.isMainFTypeOrder()) {
            builder.setTitle(ContextUtils.getStringResource(R.string.str_order_totals));
        } else if (FTypeControlUtils.isMainFTypeDispatch()) {
            builder.setTitle(ContextUtils.getStringResource(R.string.str_dispatch_totals));
        } else {
            builder.setTitle(ContextUtils.getStringResource(R.string.str_invoice_totals));
        }
        builder.setView(inflate);
        AppCompatTextView appCompatTextView = inflate.findViewById(R.id.tvTotal);
        AppCompatTextView appCompatTextView2 = inflate.findViewById(R.id.tvTotalVat);
        AppCompatTextView appCompatTextView3 = inflate.findViewById(R.id.tvTotalDiscount);
        AppCompatTextView appCompatTextView4 = inflate.findViewById(R.id.tvTotalNet);
        appCompatTextView.setText(StringUtils.dFormat(StringUtils.toDouble(str)));
        appCompatTextView2.setText(StringUtils.dFormat(StringUtils.toDouble(str2)));
        appCompatTextView3.setText(StringUtils.dFormat(StringUtils.toDouble(str3)));
        appCompatTextView4.setText(StringUtils.dFormat(StringUtils.toDouble(str4)));
        builder.show();
    }

    public static String exportSettings(SharedPreferences sharedPreferences) {
        String settingFileName = getSettingFileName();
        String str = ContextUtils.getmContext().getCacheDir() + SETTINGS_EXPORT_PATH + settingFileName;
        Map<String, ?> all = sharedPreferences.getAll();
        StringBuilder sb = new StringBuilder();
        for (String str2 : all.keySet()) {
            if (str2.startsWith("pref")) {
                Object obj = all.get(str2);
                Log.d("ContentValues", "exportSettings: key : " + str2 + " value : " + obj + " type : " + obj.getClass().getName());
                try {
                    sb.append(str2);
                    sb.append("\t");
                    sb.append(obj);
                    sb.append("\t");
                    sb.append(StringUtils.getObjectType(obj.getClass()).getValue());
                    sb.append(SqlLiteVariable._NEW_LINE);
                } catch (IOException e2) {
                    Log.d("ContentValues", "exportSettings: ", e2);
                }
            }
        }
        ContextUtils.createFile(ContextUtils.getmContext().getCacheDir() + SETTINGS_EXPORT_PATH, settingFileName, sb.toString());
        return str;
    }

    public static boolean importData(Context context, ContentResolver contentResolver, Uri uri) {
        try {
            InputStream openInputStream = contentResolver.openInputStream(uri);
            StringBuilder sb = new StringBuilder();
            if (openInputStream != null) {
                while (true) {
                    int read = openInputStream.read();
                    if (read == -1) {
                        break;
                    }
                    char c2 = (char) read;
                    String sb2 = "s : " +
                            c2;
                    Log.d(MobileSales.TAG, sb2);
                    sb.append(c2);
                }
                openInputStream.close();
            }
            if (TextUtils.isEmpty(sb)) {
                return false;
            }
            ArrayList<UserPreferenceSettings> arrayList = new ArrayList();
            for (String str : sb.toString().split(SqlLiteVariable._NEW_LINE)) {
                UserPreferenceSettings userPreferenceSettings = new UserPreferenceSettings();
                String[] split = str.split("\t");
                if (split.length == 3) {
                    userPreferenceSettings.setKey(split[0]);
                    userPreferenceSettings.setValue(split[1]);
                    userPreferenceSettings.setObjectType(ObjectType.fromObjectType(StringUtils.convertStringToInt(split[2])));
                }
                arrayList.add(userPreferenceSettings);
            }
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            for (UserPreferenceSettings userPreferenceSettings2 : arrayList) {
                if (userPreferenceSettings2.getObjectType() == ObjectType.STRING) {
                    edit.putString(userPreferenceSettings2.getKey(), StringUtils.checkNullValueString(userPreferenceSettings2.getValue()));
                } else if (userPreferenceSettings2.getObjectType() == ObjectType.INT) {
                    edit.putInt(userPreferenceSettings2.getKey(), StringUtils.convertStringToInt(userPreferenceSettings2.getValue()));
                } else if (userPreferenceSettings2.getObjectType() == ObjectType.FLOAT) {
                    edit.putFloat(userPreferenceSettings2.getKey(), StringUtils.convertStringToFloat(userPreferenceSettings2.getValue()));
                } else if (userPreferenceSettings2.getObjectType() == ObjectType.DOUBLE) {
                    edit.putFloat(userPreferenceSettings2.getKey(), StringUtils.convertDoubleToFloat(StringUtils.convertStringToDouble(userPreferenceSettings2.getValue())));
                } else if (userPreferenceSettings2.getObjectType() == ObjectType.BOOL) {
                    edit.putBoolean(userPreferenceSettings2.getKey(), StringUtils.convertStringToBoolean(userPreferenceSettings2.getValue()));
                }
            }
            edit.apply();
            return true;
        } catch (FileNotFoundException e2) {
            Log.e("ContentValues", "importData: ", e2);
            return false;
        } catch (IOException e3) {
            Log.e("ContentValues", "importData: ", e3);
            return false;
        }
    }

    public static void shareSettings(Context context) {
        new Intent().setAction("android.intent.action.SEND");
        Uri uriForFile = FileProvider.getUriForFile(context, "com.proje.mobilesales.fileprovider", new File(exportSettings(PreferenceManager.getDefaultSharedPreferences(context))));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/*");
        intent.setFlags(1);
        intent.setDataAndType(uriForFile, "text/*");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        context.startActivity(Intent.createChooser(intent, context.getResources().getText(R.string.str_share_settings)));
    }

    public static MediaPlayer playBeepSound() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(2);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.proje.mobilesales.core.utils.AppUtils.1
            C25721() {
            }

            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                mediaPlayer2.stop();
                mediaPlayer2.release();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.proje.mobilesales.core.utils.AppUtils.2
            C25732() {
            }

            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i2, int i3) {
                Log.w("TAG", "Failed to beep " + i2 + ", " + i3);
                mediaPlayer2.stop();
                mediaPlayer2.release();
                return true;
            }
        });
        try {
            AssetFileDescriptor openRawResourceFd = ContextUtils.getmContext().getResources().openRawResourceFd(R.raw.zxing_beep);
            try {
                mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                openRawResourceFd.close();
                mediaPlayer.setVolume(100.0f, 100.0f);
                mediaPlayer.prepare();
                mediaPlayer.start();
                return mediaPlayer;
            } catch (Throwable th) {
                openRawResourceFd.close();
                throw th;
            }
        } catch (IOException e2) {
            Log.w("TAG", e2);
            mediaPlayer.release();
            return null;
        }
    }

    /* renamed from: com.proje.mobilesales.core.utils.AppUtils1 */
    class C25721 implements MediaPlayer.OnCompletionListener {
        C25721() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer2) {
            mediaPlayer2.stop();
            mediaPlayer2.release();
        }
    }

    /* renamed from: com.proje.mobilesales.core.utils.AppUtils2 */
    class C25732 implements MediaPlayer.OnErrorListener {
        C25732() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer2, int i2, int i3) {
            Log.w("TAG", "Failed to beep " + i2 + ", " + i3);
            mediaPlayer2.stop();
            mediaPlayer2.release();
            return true;
        }
    }

    public static boolean hasGrantedUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        Iterator<UriPermission> it = ContextUtils.getmContext().getContentResolver().getPersistedUriPermissions().iterator();
        while (it.hasNext()) {
            if (it.next().getUri().toString().equals(uri.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String getSettingsForExport(SharedPreferences sharedPreferences) {
        Map<String, ?> all = sharedPreferences.getAll();
        StringBuilder sb = new StringBuilder();
        for (String str : all.keySet()) {
            if (str.startsWith("pref")) {
                Object obj = all.get(str);
                Log.d("ContentValues", "exportSettings: key : " + str + " value : " + obj + " type : " + obj.getClass().getName());
                try {
                    sb.append(str);
                    sb.append("\t");
                    sb.append(obj);
                    sb.append("\t");
                    sb.append(StringUtils.getObjectType(obj.getClass()).getValue());
                    sb.append(SqlLiteVariable._NEW_LINE);
                } catch (IOException e2) {
                    Log.d("ContentValues", "exportSettings: ", e2);
                }
            }
        }
        return sb.toString();
    }

    public static String getDownloadPath(Uri uri) {
        String str;
        try {
            str = FilePath.getPath(ContextUtils.getmContext(), uri);
        } catch (Exception e2) {
            Log.e("getDownloadPath", "getDownloadPath", e2);
            str = "";
        }
        return TextUtils.isEmpty(str) ? ContextUtils.getStringResource(R.string.menu_done) : str;
    }

    public static void saveSettingsToSelectedFolder(Uri uri, SharedPreferences sharedPreferences) {
        try {
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
            String settingFileName = getSettingFileName();
            String settingsForExport = getSettingsForExport(sharedPreferences);
            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(ContextUtils.getmContext(), uri);
            if (fromTreeUri.isDirectory()) {
                DocumentFile createFile = fromTreeUri.createFile("text/*", settingFileName);
                OutputStream openOutputStream = ContextUtils.getmContext().getContentResolver().openOutputStream(createFile.getUri());
                openOutputStream.write(settingsForExport.getBytes());
                openOutputStream.close();
                Toast.makeText(ContextUtils.getmContext(), getDownloadPath(createFile.getUri()), 0).show();
            } else {
                sharedPreferencesHelper.removeSettingsPath();
            }
        } catch (FileNotFoundException e2) {
            Log.e("SettingsExport", "saveSettingsToSelectedFolder", e2);
        } catch (IOException e3) {
            Log.e("SettingsExport", "saveSettingsToSelectedFolder", e3);
        } catch (Exception e4) {
            Log.e("SettingsExport", "saveSettingsToSelectedFolder", e4);
        }
    }

    public static void saveLogToSelectedFolder(String str, String str2) {
        try {
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
            Uri logPath = sharedPreferencesHelper.getLogPath();
            if (logPath != null) {
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(ContextUtils.getmContext(), logPath);
                if (fromTreeUri.isDirectory()) {
                    OutputStream openOutputStream = ContextUtils.getmContext().getContentResolver().openOutputStream(fromTreeUri.createFile("application/xml", str).getUri());
                    openOutputStream.write(str2.getBytes());
                    openOutputStream.close();
                }
            } else {
                sharedPreferencesHelper.removeLogPath();
            }
        } catch (FileNotFoundException e2) {
            Log.e("SaveLog", "saveLogToSelectedFolder", e2);
        } catch (IOException e3) {
            Log.e("SaveLog", "saveLogToSelectedFolder", e3);
        } catch (Exception e4) {
            Log.e("SaveLog", "saveLogToSelectedFolder", e4);
        }
    }

    public static void printToFolder(String str, String str2) {
        try {
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
            Uri printToFilePath = sharedPreferencesHelper.getPrintToFilePath();
            if (printToFilePath != null) {
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(ContextUtils.getmContext(), printToFilePath);
                if (fromTreeUri.isDirectory()) {
                    OutputStream openOutputStream = ContextUtils.getmContext().getContentResolver().openOutputStream(fromTreeUri.createFile("text/plain", str).getUri());
                    openOutputStream.write(str2.getBytes());
                    openOutputStream.close();
                }
            } else {
                sharedPreferencesHelper.removePrintToFilePath();
            }
        } catch (FileNotFoundException e2) {
            Log.e("printToFolder", "printToFolder", e2);
        } catch (IOException e3) {
            Log.e("printToFolder", "printToFolder", e3);
        } catch (Exception e4) {
            Log.e("printToFolder", "printToFolder", e4);
        }
    }
}
