package com.proje.mobilesales.features.gpsinfo.view.activity;

import android.widget.CompoundButton;
import androidx.core.app.ActivityCompat;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.GrantableRequest;
import permissions.dispatcher.PermissionUtils;

public final class GpsInfoActivityPermissionsDispatcher {
    private static GrantableRequest PENDING_CLICKCOMPOUNDBUTTON = null;
    static final String[] PERMISSION_CLICKCOMPOUNDBUTTON = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    static final String[] PERMISSION_INITMAP = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int REQUEST_CLICKCOMPOUNDBUTTON = 0;
    private static final int REQUEST_INITMAP = 1;

    public static void clickCompoundButtonWithPermissionCheck(GpsInfoActivity gpsInfoActivity, CompoundButton compoundButton) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "<this>");
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        String[] strArr = PERMISSION_CLICKCOMPOUNDBUTTON;
        if (PermissionUtils.hasSelfPermissions(gpsInfoActivity, Arrays.copyOf(strArr, strArr.length))) {
            gpsInfoActivity.clickCompoundButton(compoundButton);
            return;
        }
        PENDING_CLICKCOMPOUNDBUTTON = new GpsInfoActivityClickCompoundButtonPermissionRequest(gpsInfoActivity, compoundButton);
        if (PermissionUtils.shouldShowRequestPermissionRationale(gpsInfoActivity, Arrays.copyOf(strArr, strArr.length))) {
            GrantableRequest grantableRequest = PENDING_CLICKCOMPOUNDBUTTON;
            if (grantableRequest != null) {
                gpsInfoActivity.showRationaleForAllPermission(grantableRequest);
                return;
            }
            return;
        }
        ActivityCompat.requestPermissions(gpsInfoActivity, strArr, 0);
    }

    public static void initMapWithPermissionCheck(GpsInfoActivity gpsInfoActivity) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "<this>");
        String[] strArr = PERMISSION_INITMAP;
        if (PermissionUtils.hasSelfPermissions(gpsInfoActivity, Arrays.copyOf(strArr, strArr.length))) {
            gpsInfoActivity.initMap();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(gpsInfoActivity, Arrays.copyOf(strArr, strArr.length))) {
            gpsInfoActivity.showRationaleForAllPermission(new GpsInfoActivityInitMapPermissionRequest(gpsInfoActivity));
        } else {
            ActivityCompat.requestPermissions(gpsInfoActivity, strArr, 1);
        }
    }

    public static void onRequestPermissionsResult(GpsInfoActivity gpsInfoActivity, int i2, int[] grantResults) {
        GrantableRequest grantableRequest;
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "<this>");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        if (i2 == 0) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(grantResults, grantResults.length)) && (grantableRequest = PENDING_CLICKCOMPOUNDBUTTON) != null) {
                grantableRequest.grant();
            }
            PENDING_CLICKCOMPOUNDBUTTON = null;
            return;
        }
        if (i2 == 1 && PermissionUtils.verifyPermissions(Arrays.copyOf(grantResults, grantResults.length))) {
            gpsInfoActivity.initMap();
        }
    }
}
