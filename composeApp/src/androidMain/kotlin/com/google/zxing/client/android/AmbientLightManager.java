package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.journeyapps.barcodescanner.camera.CameraManager;
import com.journeyapps.barcodescanner.camera.CameraSettings;

public final class AmbientLightManager implements SensorEventListener {
    private final CameraManager cameraManager;
    private final CameraSettings cameraSettings;
    private final Context context;
    private final Handler handler = new Handler();
    private Sensor lightSensor;

    public void onAccuracyChanged(final Sensor sensor, final int i2) {
    }

    public AmbientLightManager(final Context context2, final CameraManager cameraManager2, final CameraSettings cameraSettings2) {
        context = context2;
        cameraManager = cameraManager2;
        cameraSettings = cameraSettings2;
    }

    public void start() {
        if (cameraSettings.isAutoTorchEnabled()) {
            final SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            final Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            lightSensor = defaultSensor;
            if (null != defaultSensor) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public void stop() {
        if (null != this.lightSensor) {
            ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE)).unregisterListener(this);
            lightSensor = null;
        }
    }

    
    public void lambdasetTorch0(final boolean z) {
        cameraManager.setTorch(z);
    }

    private void setTorch(final boolean z) {
        handler.post(new AmbientLightManagerExternalSyntheticLambda0(this, z));
    }

    public void onSensorChanged(final SensorEvent sensorEvent) {
        final float f2 = sensorEvent.values[0];
        if (null == this.cameraManager) {
            return;
        }
        if (45.0f >= f2) {
            this.setTorch(true);
        } else if (450.0f <= f2) {
            this.setTorch(false);
        }
    }
}
