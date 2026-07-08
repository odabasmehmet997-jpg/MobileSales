package com.proje.mobilesales.features.gpsinfo.view.activity;

import android.widget.CompoundButton;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.GrantableRequest;

final class GpsInfoActivityClickCompoundButtonPermissionRequest implements GrantableRequest {
    private final CompoundButton compoundButton;
    private final WeakReference<GpsInfoActivity> weakTarget;

    public void cancel() {
    }

    public GpsInfoActivityClickCompoundButtonPermissionRequest(GpsInfoActivity target, CompoundButton compoundButton) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        this.compoundButton = compoundButton;
        this.weakTarget = new WeakReference<>(target);
    }
    public void proceed() {
        String[] strArr;
        GpsInfoActivity gpsInfoActivity = this.weakTarget.get();
        if (gpsInfoActivity == null) {
            return;
        }
        strArr = GpsInfoActivityPermissionsDispatcher.PERMISSION_CLICKCOMPOUNDBUTTON;
        ActivityCompat.requestPermissions(gpsInfoActivity, strArr, 0);
    }
    public void grant() {
        GpsInfoActivity gpsInfoActivity = this.weakTarget.get();
        if (gpsInfoActivity == null) {
            return;
        }
        gpsInfoActivity.clickCompoundButton(this.compoundButton);
    }
}
