package com.proje.mobilesales.features.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import cardtek.masterpass.MasterPassServices;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.ValidateTransactionListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.ValidateTransactionResult;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.MasterPassPinOption;
import com.proje.mobilesales.core.masterpass.Constants;
import com.proje.mobilesales.core.masterpass.MasterPassResult;
import com.proje.mobilesales.core.masterpass.TokenGenerator;
import com.proje.mobilesales.core.utils.ContextUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PinSmsFragment.kt */

public final class PinSmsFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    public AppCompatButton btnCancel;
    public AppCompatButton btnResend;
    private AppCompatButton btnValidate;
    private AppCompatTextView dialogDescription;
    private BottomSheetDialog mBottomSheetDialog;
    private ProgressDialog mProgressDialog;
    private MasterPassResult masterPassResult;
    private MasterPassServices masterPassServices;
    private MasterPassEditText pin;
    private MasterPassPinOption pinOption;
    public AppCompatTextView resendTimer;
    private ServiceResult serviceResult;

    public MasterPassEditText getPin() {
        return this.pin;
    }

    public void setPin(MasterPassEditText masterPassEditText) {
        this.pin = masterPassEditText;
    }

    public AppCompatButton getBtnResend() {
        AppCompatButton appCompatButton = this.btnResend;
        if (appCompatButton != null) {
            return appCompatButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btnResend");
        return null;
    }

    public void setBtnResend(AppCompatButton appCompatButton) {
        Intrinsics.checkNotNullParameter(appCompatButton, "<set-?>");
        this.btnResend = appCompatButton;
    }

    public AppCompatButton getBtnCancel() {
        AppCompatButton appCompatButton = this.btnCancel;
        if (appCompatButton != null) {
            return appCompatButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
        return null;
    }

    public void setBtnCancel(AppCompatButton appCompatButton) {
        Intrinsics.checkNotNullParameter(appCompatButton, "<set-?>");
        this.btnCancel = appCompatButton;
    }

    public AppCompatTextView getResendTimer() {
        AppCompatTextView appCompatTextView = this.resendTimer;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resendTimer");
        return null;
    }

    public void setResendTimer(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.resendTimer = appCompatTextView;
    }

    public ProgressDialog getMProgressDialog() {
        return this.mProgressDialog;
    }

    public void setMProgressDialog(ProgressDialog progressDialog) {
        this.mProgressDialog = progressDialog;
    }

    public ServiceResult getServiceResult() {
        return this.serviceResult;
    }

    public MasterPassResult getMasterPassResult() {
        return this.masterPassResult;
    }

    public PinSmsFragment() {
    }

    public PinSmsFragment(MasterPassServices masterPassServices) {
        this.masterPassServices = masterPassServices;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_pin_sms, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.btnValidate);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.btnValidate = (AppCompatButton) findViewById;
        View findViewById2 = inflate.findViewById(R.id.btnResend);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        setBtnResend((AppCompatButton) findViewById2);
        View findViewById3 = inflate.findViewById(R.id.btnCancel);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        setBtnCancel((AppCompatButton) findViewById3);
        this.pin = inflate.findViewById(R.id.pin);
        View findViewById4 = inflate.findViewById(R.id.resendTimer);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        setResendTimer((AppCompatTextView) findViewById4);
        View findViewById5 = inflate.findViewById(R.id.txtDialogDescription);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.dialogDescription = (AppCompatTextView) findViewById5;
        ProgressDialog progressDialog = new ProgressDialog(ContextUtils.getmContext());
        this.mProgressDialog = progressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setCancelable(false);
        Companion.startTimer(getResendTimer(), getBtnResend(), getString(R.string.str_resend_sms_desc));
        getBtnCancel().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PinSmsFragment.onCreateViewlambda0(PinSmsFragment.this, view);
            }
        });
        AppCompatButton appCompatButton = this.btnValidate;
        AppCompatTextView appCompatTextView = null;
        if (appCompatButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnValidate");
            appCompatButton = null;
        }
        appCompatButton.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PinSmsFragment.onCreateViewlambda1(PinSmsFragment.this, view);
            }
        });
        getBtnResend().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PinSmsFragment.onCreateViewlambda2(PinSmsFragment.this, view);
            }
        });
        this.pinOption = MasterPassPinOption.NONE;
        if (getTag() == Constants.PIN_ENTER_SMS) {
            AppCompatTextView appCompatTextView2 = this.dialogDescription;
            if (appCompatTextView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogDescription");
            } else {
                appCompatTextView = appCompatTextView2;
            }
            appCompatTextView.setText(R.string.str_enter_pin_sms_verification_code);
        } else if (getTag() == Constants.PIN_ENTER_SMS_MPASS) {
            AppCompatTextView appCompatTextView3 = this.dialogDescription;
            if (appCompatTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogDescription");
            } else {
                appCompatTextView = appCompatTextView3;
            }
            appCompatTextView.setText(R.string.str_enter_pin_sms_masterpass_verification_code);
        } else if (getTag() == Constants.PIN_ENTER_SMS_FOR_PURCHASE) {
            AppCompatTextView appCompatTextView4 = this.dialogDescription;
            if (appCompatTextView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogDescription");
            } else {
                appCompatTextView = appCompatTextView4;
            }
            appCompatTextView.setText(R.string.str_enter_pin_sms_for_purchase);
            this.pinOption = MasterPassPinOption.VALIDATESMSPURCHASE;
        }
        return inflate;
    }

    
    public static void onCreateViewlambda0(PinSmsFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
    }

    
    public static void onCreateViewlambda1(PinSmsFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.validate();
    }

    
    public static void onCreateViewlambda2(PinSmsFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.reSend();
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout frameLayout = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        Intrinsics.checkNotNull(frameLayout);
        BottomSheetBehavior<?> from = BottomSheetBehavior.from(frameLayout);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        from.setDraggable(false);
        from.setSkipCollapsed(true);
        from.setHideable(false);
        keepFullScreen(frameLayout, from);
    }

    private void keepFullScreen(View view, BottomSheetBehavior<?> bottomSheetBehavior) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int windowHeight = getWindowHeight();
        if (view.findViewById(R.id.message_content_view).getHeight() >= windowHeight) {
            if (layoutParams != null) {
                layoutParams.height = (int) (windowHeight * 0.8d);
            }
            view.setLayoutParams(layoutParams);
            bottomSheetBehavior.setPeekHeight((int) (windowHeight * 0.8d));
        }
        bottomSheetBehavior.setState(3);
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "onCreateDialog(...)");
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                PinSmsFragment.onCreateDialoglambda3(PinSmsFragment.this, dialogInterface);
            }
        });
        return onCreateDialog;
    }

    
    public static void onCreateDialoglambda3(PinSmsFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
        this0.mBottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        this0.setupFullHeight(bottomSheetDialog);
    }

    private void validate() {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialog progressDialog = this.mProgressDialog;
            Intrinsics.checkNotNull(progressDialog);
            progressDialog.show();
            MasterPassServices masterPassServices = this.masterPassServices;
            Intrinsics.checkNotNull(masterPassServices);
            masterPassServices.validateTransaction(this.pin, TokenGenerator.generateReqRefNo(), new ValidateTransactionListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentvalidate1
                @Override // cardtek.masterpass.interfaces.ValidateTransactionListener
                public void onSuccess(ValidateTransactionResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    ProgressDialog mProgressDialog = PinSmsFragment.this.getMProgressDialog();
                    Intrinsics.checkNotNull(mProgressDialog);
                    mProgressDialog.dismiss();
                    PinSmsFragment.this.masterPassResult = new MasterPassResult();
                    MasterPassResult masterPassResult = PinSmsFragment.this.getMasterPassResult();
                    Intrinsics.checkNotNull(masterPassResult);
                    masterPassResult.setValidateTransactionResult(result);
                    PinSmsFragment.this.dismiss();
                }

                @Override // cardtek.masterpass.interfaces.ValidateTransactionListener
                public void onVerifyUser(ServiceResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    ProgressDialog mProgressDialog = PinSmsFragment.this.getMProgressDialog();
                    Intrinsics.checkNotNull(mProgressDialog);
                    mProgressDialog.dismiss();
                    PinSmsFragment.this.serviceResult = result;
                    PinSmsFragment.this.dismiss();
                }

                @Override // cardtek.masterpass.interfaces.ValidateTransactionListener
                public void onServiceError(ServiceError result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    ProgressDialog mProgressDialog = PinSmsFragment.this.getMProgressDialog();
                    Intrinsics.checkNotNull(mProgressDialog);
                    mProgressDialog.dismiss();
                    PinSmsFragment pinSmsFragment = PinSmsFragment.this;
                    String responseCode = result.getResponseCode();
                    Intrinsics.checkNotNullExpressionValue(responseCode, "getResponseCode(...)");
                    String responseDesc = result.getResponseDesc();
                    Intrinsics.checkNotNullExpressionValue(responseDesc, "getResponseDesc(...)");
                    pinSmsFragment.showMasterPassDialog(responseCode, responseDesc);
                }

                @Override // cardtek.masterpass.interfaces.ValidateTransactionListener
                public void onInternalError(InternalError result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    ProgressDialog mProgressDialog = PinSmsFragment.this.getMProgressDialog();
                    Intrinsics.checkNotNull(mProgressDialog);
                    mProgressDialog.dismiss();
                    PinSmsFragment pinSmsFragment = PinSmsFragment.this;
                    String errorCode = result.getErrorCode();
                    Intrinsics.checkNotNullExpressionValue(errorCode, "getErrorCode(...)");
                    String errorDesc = result.getErrorDesc();
                    Intrinsics.checkNotNullExpressionValue(errorDesc, "getErrorDesc(...)");
                    pinSmsFragment.showMasterPassDialog(errorCode, errorDesc);
                }
            });
        }
    }

    private void reSend() {
        if (ContextUtils.checkInternetConnection()) {
            ProgressDialog progressDialog = this.mProgressDialog;
            Intrinsics.checkNotNull(progressDialog);
            progressDialog.show();
            MasterPassServices masterPassServices = this.masterPassServices;
            Intrinsics.checkNotNull(masterPassServices);
            masterPassServices.resendOtp(TokenGenerator.generateReqRefNo(), new PinSmsFragmentreSend1(this));
        }
    }

    
    public void showMasterPassDialog(final String str, final String str2) {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        activity.runOnUiThread(new Runnable() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public void run() {
                PinSmsFragment.showMasterPassDialoglambda5(str2, str, this);
            }
        });
    }

    
    public static void showMasterPassDialoglambda5(String message, String title, PinSmsFragment this0) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(this0, "this0");
        final BottomAlertDialogFragment bottomAlertDialogFragment = new BottomAlertDialogFragment();
        bottomAlertDialogFragment.setShowNegativeButton(false);
        bottomAlertDialogFragment.setMessage(message);
        bottomAlertDialogFragment.setTitle(title);
        bottomAlertDialogFragment.setCancelable(true);
        bottomAlertDialogFragment.setShowNegativeButton(false);
        bottomAlertDialogFragment.setPositiveButton("", new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PinSmsFragment.showMasterPassDialoglambda5lambda4(BottomAlertDialogFragment.this, view);
            }
        });
        FragmentActivity activity = this0.getActivity();
        Intrinsics.checkNotNull(activity);
        bottomAlertDialogFragment.show(activity.getSupportFragmentManager(), "BottomAlertDialogFragment");
    }

    
    public static void showMasterPassDialoglambda5lambda4(BottomAlertDialogFragment errorDialog, View view) {
        Intrinsics.checkNotNullParameter(errorDialog, "errorDialog");
        errorDialog.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Intent intent = new Intent();
        intent.putExtra("ServiceResult", this.serviceResult);
        intent.putExtra("MasterPassResult", this.masterPassResult);
        intent.putExtra("MasterPassPinOption", this.pinOption);
        Fragment targetFragment = getTargetFragment();
        Intrinsics.checkNotNull(targetFragment);
        targetFragment.onActivityResult(getTargetRequestCode(), -1, intent);
    }

    /* compiled from: PinSmsFragment.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public void startTimer(final TextView resendTimer, final Button btnResend, final String str) {
            Intrinsics.checkNotNullParameter(resendTimer, "resendTimer");
            Intrinsics.checkNotNullParameter(btnResend, "btnResend");
            btnResend.setClickable(false);
            btnResend.setEnabled(false);
            btnResend.setAlpha(0.5f);
            new CountDownTimer() { // from class: com.proje.mobilesales.features.fragment.PinSmsFragmentCompanionstartTimer1
                
                {
                    super(180000L, 1000L);
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                    resendTimer.setText(String.valueOf(j2 / 1000));
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    resendTimer.setText(str);
                    btnResend.setClickable(true);
                    btnResend.setEnabled(true);
                    btnResend.setAlpha(1.0f);
                }
            }.start();
        }
    }
}
