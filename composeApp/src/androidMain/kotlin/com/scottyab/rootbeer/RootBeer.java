package com.scottyab.rootbeer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.scottyab.rootbeer.util.QLog;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RootBeer {
    private final boolean loggingEnabled = true;
    private final Context mContext;
    public RootBeer(Context context) {
        this.mContext = context;
    }
    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }
    public boolean detectTestKeys() {
        String str = Build.TAGS;
        return null != str && str.contains ("test-keys");
    }
    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }
    public boolean detectRootManagementApps(String [] strArr) {
        ArrayList<String> arrayList = new ArrayList<> (Arrays.asList (com.scottyab.rootbeer.Const.knownRootAppsPackages));
        if (null != strArr && 0 < strArr.length) {
            arrayList.addAll (Arrays.asList (strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }
    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }
    public boolean detectPotentiallyDangerousApps(String [] strArr) {
        ArrayList<String> arrayList = new ArrayList<> ();
        arrayList.addAll (Arrays.asList (com.scottyab.rootbeer.Const.knownDangerousAppsPackages));
        if (null != strArr && 0 < strArr.length) {
            arrayList.addAll (Arrays.asList (strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }
    public boolean checkForMagiskBinary() {
        return checkForBinary("magisk");
    }
    public boolean checkForBinary(String str) {
        boolean z = false;
        for (String str2 : com.scottyab.rootbeer.Const.getPaths ()) {
            String str3 = str2 + str;
            if (new File (str2, str).exists ()) {
                QLog.m584v (str3 + " binary detected!");
                z = true;
            }
        }
        return z;
    }
    private String [] propsReader() {
        try {
            InputStream inputStream = Runtime.getRuntime ().exec ("getprop").getInputStream ();
            if (null == inputStream) {
                return null;
            }
            if (Build.VERSION_CODES.UPSIDE_DOWN_CAKE <= Build.VERSION.SDK_INT) {
                return new Scanner (inputStream, StandardCharsets.UTF_8).useDelimiter ("\\A").next ().split (SqlLiteVariable._NEW_LINE);
            }
        } catch (IOException | NoSuchElementException e2) {
            QLog.m582e (e2);
            return null;
        }
        return new String[0];
    }
    private String [] mountReader() {
        try {
            InputStream inputStream = Runtime.getRuntime ().exec ("mount").getInputStream ();
            if (null == inputStream) {
                return null;
            }
            if (Build.VERSION_CODES.UPSIDE_DOWN_CAKE <= Build.VERSION.SDK_INT) {
                return new Scanner (inputStream, StandardCharsets.UTF_8).useDelimiter ("\\A").next ().split (SqlLiteVariable._NEW_LINE);
            }
        } catch (IOException | NoSuchElementException e2) {
            QLog.m582e (e2);
            return null;
        }
        return new String[0];
    }
    private boolean isAnyPackageFromListInstalled(List<String> list) {
        PackageManager packageManager = this.mContext.getPackageManager ();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo (str, 0);
                QLog.m583e (str + " ROOT management app detected!");
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }
    public boolean checkForDangerousProps() {
        HashMap hashMap = new HashMap ();
        hashMap.put ("ro.debuggable", BuildConfig.NETSIS_DEMO_PASSWORD);
        hashMap.put ("ro.secure", "0");
        String[] propsReader = propsReader();
        if (null == propsReader) {
            return false;
        }
        boolean z = false;
        for (String str : propsReader) {
            for (Object str2 : hashMap.keySet ()) {
                if (str.contains ((CharSequence) str2)) {
                    String str3 = "[" + hashMap.get (str2) + "]";
                    if (str.contains (str3)) {
                        QLog.m584v (str2 + SqlLiteVariable._EQUALS + str3 + " detected!");
                        z = true;
                    }
                }
            }
        }
        return z;
    }
    public boolean checkForRWPaths() {
        String[] mountReader = mountReader();
        int i2 = 0;
        if (null == mountReader) {
            return false;
        }
        int length = mountReader.length;
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            String str = mountReader[i3];
            String[] split = str.split (" ");
            if (6 > split.length) {
                QLog.m583e ("Error formatting mount line: " + str);
            } else {
                String str2 = split[2];
                String str3 = split[5];
                String[] strArr = com.scottyab.rootbeer.Const.pathsThatShouldNotBeWritable;
                int length2 = strArr.length;
                int i4 = i2;
                while (i4 < length2) {
                    String str4 = strArr[i4];
                    if (str2.equalsIgnoreCase (str4)) {
                        str3 = str3.replace ("(", "").replace (")", "");
                        String[] split2 = str3.split (",");
                        int length3 = split2.length;
                        int i5 = i2;
                        while (true) {
                            if (i5 >= length3) {
                                break;
                            }
                            if ("rw".equalsIgnoreCase (split2[i5])) {
                                QLog.m584v (str4 + " path is mounted with rw permissions! " + str);
                                z = true;
                                break;
                            }
                            i5++;
                        }
                    }
                    i4++;
                    i2 = 0;
                }
            }
            i3++;
            i2 = 0;
        }
        return z;
    }
    public boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime ().exec (new String[]{"which", "su"});
            boolean z = null != new BufferedReader (new InputStreamReader (process.getInputStream (), StandardCharsets.UTF_8)).readLine ();
            process.destroy ();
            return z;
        } catch (Throwable unused) {
            if (null != process) {
                process.destroy ();
            }
            return false;
        }
    }
    public boolean canLoadNativeLibrary() {
        return new RootBeerNative ().wasNativeLibraryLoaded ();
    }
    public boolean checkForRootNative() {
        if (!canLoadNativeLibrary()) {
            QLog.m583e ("We could not load the native library to test for root");
            return false;
        }
        String[] paths = com.scottyab.rootbeer.Const.getPaths ();
        int length = paths.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = paths[i2] + "su";
        }
        RootBeerNative rootBeerNative = new RootBeerNative ();
        try {
            rootBeerNative.setLogDebugMessages (this.loggingEnabled);
            return 0 < rootBeerNative.checkForRoot (strArr);
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }
}
