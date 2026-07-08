package com.journeyapps.barcodescanner.camera;

public class CameraSettings {
    private int requestedCameraId = -1;
    private final boolean scanInverted = false;
    private final boolean barcodeSceneModeEnabled = false;
    private final boolean meteringEnabled = false;
    private final boolean autoFocusEnabled = true;
    private final boolean continuousFocusEnabled = false;
    private final boolean exposureEnabled = false;
    private final boolean autoTorchEnabled = false;
    private final FocusMode focusMode = FocusMode.AUTO;
    public enum FocusMode {
        AUTO,
        CONTINUOUS,
        INFINITY,
        MACRO
    }
    public int getRequestedCameraId() {
        return this.requestedCameraId;
    }
    public void setRequestedCameraId(int requestedCameraId) {
        this.requestedCameraId = requestedCameraId;
    }
    public boolean isScanInverted() {
        return this.scanInverted;
    }
    public boolean isBarcodeSceneModeEnabled() {
        return this.barcodeSceneModeEnabled;
    }
    public boolean isExposureEnabled() {
        return this.exposureEnabled;
    }
    public boolean isMeteringEnabled() {
        return this.meteringEnabled;
    }
    public boolean isAutoFocusEnabled() {
        return this.autoFocusEnabled;
    }
    public FocusMode getFocusMode() {
        return this.focusMode;
    }
    public boolean isAutoTorchEnabled() {
        return this.autoTorchEnabled;
    }
}
