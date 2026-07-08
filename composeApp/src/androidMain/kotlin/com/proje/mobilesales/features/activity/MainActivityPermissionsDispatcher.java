package com.proje.mobilesales.features.activity;

import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public class MainActivityPermissionsDispatcher {
    private static final String[] PERMISSION_STARTGPS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int REQUEST_STARTGPS = 2;
    static void startGpsWithPermissionCheck(final MainActivity mainActivity) {
        final String[] strArr = MainActivityPermissionsDispatcher.PERMISSION_STARTGPS;
        if (PermissionUtils.hasSelfPermissions(mainActivity, strArr)) {
            mainActivity.startGps();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(mainActivity, strArr)) {
            mainActivity.showRationaleForAllPermission(new MainActivityStartGpsPermissionRequest(mainActivity));
        } else {
            ActivityCompat.requestPermissions(mainActivity, strArr, 2);
        }
    }
    static void onRequestPermissionsResult(final MainActivity mainActivity, final int i2, final int[] iArr) {
        if (2 == i2 && PermissionUtils.verifyPermissions(iArr)) {
            mainActivity.startGps();
        }
    }
    private record MainActivityStartGpsPermissionRequest(
            WeakReference<MainActivity> weakTarget) implements PermissionRequest {
            public void cancel() {
            }

            private MainActivityStartGpsPermissionRequest(final MainActivity weakTarget) {
                this(new WeakReference<>(weakTarget));
            }
            public void proceed() {
                final MainActivity mainActivity = weakTarget.get();
                if (null == mainActivity) {
                    return;
                }
                ActivityCompat.requestPermissions(mainActivity, PERMISSION_STARTGPS, 2);
            }
        }
}
