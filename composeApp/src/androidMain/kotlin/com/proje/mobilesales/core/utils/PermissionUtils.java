package com.proje.mobilesales.core.utils;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.proje.mobilesales.R;

public class PermissionUtils {
    public static final int PU_ACCESS_FINE_LOCATION = 1072;
    public static final int PU_BLUETOOTH = 1073;
    public static final int PU_CAMERA = 1074;
    public static final int PU_READ_EXTERNAL_STORAGE = 1071;
    public static final int PU_READ_PHONE_STATE = 1075;
    public static final int PU_UNKNOWN = 1070;
    private static final String TAG = "PermissionUtils";
    private static final String[] PERMISSION_LOGIN = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH"};
    private static final String[] PERMISSIONP_LOGIN = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH", "android.permission.READ_PHONE_STATE"};
    private static final String[] PERMISSIONS_LOGIN = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
    public static boolean checkPermission(Context context, String str, String str2) {
        if (ContextCompat.checkSelfPermission(context, str) == 0) {
            return true;
        }
        Activity activity = (Activity) context;
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
            showExplanation(activity, "", str2, str, getPermissionCode(str));
            return false;
        }
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        if (sharedPreferencesHelper.permissionHasBeenAskedBefore(str)) {
            askPermissionAgain(context, getPermissionName(context, str));
            return false;
        }
        sharedPreferencesHelper.savePermissionAskedValue(str);
        ActivityCompat.requestPermissions(activity, new String[]{str}, getPermissionCode(str));
        return false;
    }
    private static void showExplanation(final Activity activity, String str, String str2, final String str3, final int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(str).setMessage(str2).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i3) {
                ActivityCompat.requestPermissions(activity, new String[]{str3}, i2);
            }
        });
        builder.create().show();
    }
    public static boolean checkPermissionFromFragment(Fragment fragment, String str, String str2) {
        if (ContextCompat.checkSelfPermission(fragment.getActivity(), str) == 0) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(), str)) {
            showExplanationForFragment(fragment, "", str2, str, getPermissionCode(str));
            return false;
        }
        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        if (sharedPreferencesHelper.permissionHasBeenAskedBefore(str)) {
            askPermissionAgain(fragment.getActivity(), getPermissionName(fragment.getActivity(), str));
            return false;
        }
        sharedPreferencesHelper.savePermissionAskedValue(str);
        fragment.requestPermissions(new String[]{str}, getPermissionCode(str));
        return false;
    }
    private static void showExplanationForFragment(final Fragment fragment, String str, String str2, final String str3, final int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i3) {
                fragment.requestPermissions(new String[]{str3}, i2);
            }
        });
        builder.create().show();
    }
    public static StringBuilder checkLoginPermissions(Context context) {
        StringBuilder sb = new StringBuilder();
        for (String str : PERMISSION_LOGIN) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                String permissionName = getPermissionName(context, str);
                if (sb.indexOf(permissionName) == -1) {
                    sb.append(permissionName);
                    sb.append(", ");
                }
            }
        }
        if (sb.toString().trim().length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb;
    }
    public static StringBuilder checkLoginPPermissions(Context context) {
        StringBuilder sb = new StringBuilder();
        for (String str : PERMISSIONP_LOGIN) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                String permissionName = getPermissionName(context, str);
                if (sb.indexOf(permissionName) == -1) {
                    sb.append(permissionName);
                    sb.append(", ");
                }
            }
        }
        if (sb.toString().trim().length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb;
    }
    public static StringBuilder checkLoginSPermissions(Context context) {
        StringBuilder sb = new StringBuilder();
        for (String str : PERMISSIONS_LOGIN) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                String permissionName = getPermissionName(context, str);
                if (sb.indexOf(permissionName) == -1) {
                    sb.append(permissionName);
                    sb.append(", ");
                }
            }
        }
        if (sb.toString().trim().length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb;
    }
    public static void askPermissionAgain(final Context context, String str) {
        try {
            String stringResource = ContextUtils.getStringResource(R.string.str_for_permission_never_ask, str);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(stringResource);
            builder.setPositiveButton(R.string.str_settings, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    PermissionUtils.lambdaaskPermissionAgain0(context, dialogInterface, i2);
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        } catch (Exception e2) {
            Log.e(TAG, "checkConnection: ", e2);
        }
    }
    public static void lambdaaskPermissionAgain0(Context context, DialogInterface dialogInterface, int i2) {
        context.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:com.proje.mobilesales")));
    }
    public static String getPermissionName(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "android.permission.ACCESS_FINE_LOCATION":
                return context.getString(R.string.str_location_access);
            case "android.permission.BLUETOOTH":
                return context.getString(R.string.str_bluetooth_access);
            case "android.permission.READ_EXTERNAL_STORAGE":
                return context.getString(R.string.str_disk_read);
            case "android.permission.READ_PHONE_STATE":
                return context.getString(R.string.str_phone_access);
            case "android.permission.CAMERA":
                return context.getString(R.string.str_camera_access);
            default:
                return "";
        }
    }
    public static int getPermissionCode(String str) {
        str.hashCode();
        switch (str) {
            case "android.permission.ACCESS_FINE_LOCATION":
                return 1072;
            case "android.permission.BLUETOOTH":
                return 1073;
            case "android.permission.READ_EXTERNAL_STORAGE":
                return 1071;
            case "android.permission.READ_PHONE_STATE":
                return PU_READ_PHONE_STATE;
            case "android.permission.CAMERA":
                return PU_CAMERA;
            default:
                return PU_UNKNOWN;
        }
    }
}
