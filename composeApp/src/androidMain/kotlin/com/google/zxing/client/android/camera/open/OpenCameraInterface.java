package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
import android.util.Log;

public class OpenCameraInterface {
    private static final String TAG = "com.google.zxing.client.android.camera.open.OpenCameraInterface";
    public static int getCameraId(int i2) {
        final int numberOfCameras = Camera.getNumberOfCameras();
        if (0 == numberOfCameras) {
            Log.w(OpenCameraInterface.TAG, "No cameras!");
            return -1;
        }
        final boolean z = 0 <= i2;
        if (!z) {
            i2 = 0;
            while (i2 < numberOfCameras) {
                final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i2, cameraInfo);
                if (0 == cameraInfo.facing) {
                    break;
                }
                i2++;
            }
        }
        if (i2 < numberOfCameras) {
            return i2;
        }
        if (z) {
            return -1;
        }
        return 0;
    }
    public static Camera open(final int i2) {
        final int cameraId = OpenCameraInterface.getCameraId(i2);
        if (-1 == cameraId) {
            return null;
        }
        return Camera.open(cameraId);
    }
}
