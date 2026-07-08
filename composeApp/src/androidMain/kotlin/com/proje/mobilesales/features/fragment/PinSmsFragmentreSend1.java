package com.proje.mobilesales.features.fragment;

import android.app.ProgressDialog;
import androidx.fragment.app.FragmentActivity;
import cardtek.masterpass.interfaces.ResendOtpListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ResendOtpResult;
import com.proje.mobilesales.R;
import kotlin.jvm.internal.Intrinsics;

public final class PinSmsFragmentreSend1 implements ResendOtpListener {
    final   PinSmsFragment this0;
    PinSmsFragmentreSend1(PinSmsFragment pinSmsFragment) {
        this.this0 = pinSmsFragment;
    }
    public void onSuccess(ResendOtpResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        ProgressDialog mProgressDialog = this.this0.getMProgressDialog();
        Intrinsics.checkNotNull(mProgressDialog);
        mProgressDialog.dismiss();
        PinSmsFragment pinSmsFragment = this.this0;
        String string = pinSmsFragment.getString(R.string.str_masterpass);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = this.this0.getString(R.string.str_new_sms_send);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        pinSmsFragment.showMasterPassDialog(string, string2);
        FragmentActivity activity = this.this0.getActivity();
        Intrinsics.checkNotNull(activity);
        final PinSmsFragment pinSmsFragment2 = this.this0;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                PinSmsFragmentreSend1.onSuccesslambda0(pinSmsFragment);
            }
        });
    }
    public static void onSuccesslambda0(PinSmsFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        PinSmsFragment.Companion.startTimer(this0.getResendTimer(), this0.getBtnResend(), this0.getString(R.string.str_resend_sms_desc));
    }
    public void onServiceError(ServiceError result) {
        Intrinsics.checkNotNullParameter(result, "result");
        ProgressDialog mProgressDialog = this.this0.getMProgressDialog();
        Intrinsics.checkNotNull(mProgressDialog);
        mProgressDialog.dismiss();
        PinSmsFragment pinSmsFragment = this.this0;
        String responseCode = result.getResponseCode();
        Intrinsics.checkNotNullExpressionValue(responseCode, "getResponseCode(...)");
        String responseDesc = result.getResponseDesc();
        Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
        pinSmsFragment.showMasterPassDialog(responseCode, responseDesc);
    } 
    public void onInternalError(InternalError result) {
        Intrinsics.checkNotNullParameter(result, "result");
        ProgressDialog mProgressDialog = this.this0.getMProgressDialog();
        Intrinsics.checkNotNull(mProgressDialog);
        mProgressDialog.dismiss();
        PinSmsFragment pinSmsFragment = this.this0;
        String errorCode = result.getErrorCode();
        Intrinsics.checkNotNullExpressionValue(errorCode, "getErrorCode(...)");
        String errorDesc = result.getErrorDesc();
        Intrinsics.checkNotNullExpressionValue(errorDesc, "getErrorDesc(...)");
        pinSmsFragment.showMasterPassDialog(errorCode, errorDesc);
    }
}
