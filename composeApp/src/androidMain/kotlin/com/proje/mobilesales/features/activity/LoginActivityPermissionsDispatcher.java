package com.proje.mobilesales.features.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

public class LoginActivityPermissionsDispatcher {
    private static final String[] PERMISSION_LOGIN = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH"};
    private static final String[] PERMISSION_LOGINP = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH", "android.permission.READ_PHONE_STATE"};
    private static final String[] PERMISSION_LOGINS = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
    private static final int REQUEST_LOGIN = 2;
    private static final int REQUEST_LOGINP = 3;
    private static final int REQUEST_LOGINS = 4;
    static void loginSWithPermissionCheck(@NonNull final LoginActivity loginActivity) {
        final String[] strArr = LoginActivityPermissionsDispatcher.PERMISSION_LOGINS;
        if (PermissionUtils.hasSelfPermissions(loginActivity, strArr)) {
            loginActivity.loginS();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, strArr)) {
            loginActivity.showRationaleForAllPermissionS(new LoginActivityLoginSPermissionRequest(loginActivity));
        } else {
            ActivityCompat.requestPermissions(loginActivity, strArr, 4);
        }
    }
    static void loginPWithPermissionCheck(@NonNull final LoginActivity loginActivity) {
        final String[] strArr = LoginActivityPermissionsDispatcher.PERMISSION_LOGINP;
        if (PermissionUtils.hasSelfPermissions(loginActivity, strArr)) {
            loginActivity.loginP();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, strArr)) {
            loginActivity.showRationaleForAllPermissionP(new LoginActivityLoginPPermissionRequest(loginActivity));
        } else {
            ActivityCompat.requestPermissions(loginActivity, strArr, 3);
        }
    }
    static void loginWithPermissionCheck(@NonNull final LoginActivity loginActivity) {
        final String[] strArr = LoginActivityPermissionsDispatcher.PERMISSION_LOGIN;
        if (PermissionUtils.hasSelfPermissions(loginActivity, strArr)) {
            loginActivity.login();
        } else if (PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, strArr)) {
            loginActivity.showRationaleForAllPermission(new LoginActivityLoginPermissionRequest(loginActivity));
        } else {
            ActivityCompat.requestPermissions(loginActivity, strArr, 2);
        }
    }
    static void onRequestPermissionsResult(@NonNull final LoginActivity loginActivity, final int i2, final int[] iArr) {
        if (2 == i2) {
            if (PermissionUtils.verifyPermissions(iArr)) {
                loginActivity.login();
                return;
            } else if (!PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, LoginActivityPermissionsDispatcher.PERMISSION_LOGIN)) {
                loginActivity.showNeverAskForStorage();
                return;
            } else {
                loginActivity.showDeniedForStorage();
                return;
            }
        }
        if (3 == i2) {
            if (PermissionUtils.verifyPermissions(iArr)) {
                loginActivity.loginP();
                return;
            } else if (!PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, LoginActivityPermissionsDispatcher.PERMISSION_LOGINP)) {
                loginActivity.showNeverAskForStorageP();
                return;
            } else {
                loginActivity.showDeniedForStorageP();
                return;
            }
        }
        if (4 != i2) {
            return;
        }
        if (PermissionUtils.verifyPermissions(iArr)) {
            loginActivity.loginS();
        } else if (!PermissionUtils.shouldShowRequestPermissionRationale(loginActivity, LoginActivityPermissionsDispatcher.PERMISSION_LOGINS)) {
            loginActivity.showNeverAskForBluetoothS();
        } else {
            loginActivity.showDeniedForBluetoothS();
        }
    }
    private record LoginActivityLoginSPermissionRequest(
            WeakReference<LoginActivity> weakTarget) implements PermissionRequest {
            private LoginActivityLoginSPermissionRequest(@NonNull final LoginActivity weakTarget) {
                this(new WeakReference<>(weakTarget));
            }

        public void proceed() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                ActivityCompat.requestPermissions(loginActivity, PERMISSION_LOGINS, 4);
            }

        public void cancel() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                loginActivity.showDeniedForBluetoothS();
            }
        }
    private record LoginActivityLoginPPermissionRequest(
            WeakReference<LoginActivity> weakTarget) implements PermissionRequest {
            private LoginActivityLoginPPermissionRequest(@NonNull final LoginActivity weakTarget) {
                this(new WeakReference<>(weakTarget));
            }

        public void proceed() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                ActivityCompat.requestPermissions(loginActivity, PERMISSION_LOGINP, 3);
            }

        public void cancel() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                loginActivity.showDeniedForStorageP();
            }
        }
    private record LoginActivityLoginPermissionRequest(
            WeakReference<LoginActivity> weakTarget) implements PermissionRequest {
            private LoginActivityLoginPermissionRequest( final LoginActivity weakTarget) {
                this(new WeakReference<>(weakTarget));
            }

            public void proceed() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                ActivityCompat.requestPermissions(loginActivity, PERMISSION_LOGIN, 2);
            }

            public void cancel() {
                final LoginActivity loginActivity = weakTarget.get();
                if (null == loginActivity) {
                    return;
                }
                loginActivity.showDeniedForStorage();
            }
        }
}
