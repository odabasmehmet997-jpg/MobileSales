package com.proje.mobilesales.features.gpsinfo.view.activity;

import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionRequest;

final class GpsInfoActivityInitMapPermissionRequest implements PermissionRequest {
    private final WeakReference<GpsInfoActivity> weakTarget;

    public void cancel() {
    }

    public GpsInfoActivityInitMapPermissionRequest(GpsInfoActivity target) {
        Intrinsics.checkNotNullParameter(target, "target");
        this.weakTarget = new WeakReference<>(target);
    }

    public void proceed() {
        String[] strArr;
        GpsInfoActivity gpsInfoActivity = this.weakTarget.get();
        if (gpsInfoActivity == null) {
            return;
        }
        strArr = GpsInfoActivityPermissionsDispatcher.PERMISSION_INITMAP;
        ActivityCompat.requestPermissions(gpsInfoActivity, strArr, 1);
    }
}
