package com.proje.mobilesales.features.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: BottomAlertDialogFragment.kt */

public final class BottomAlertDialogFragment extends BottomSheetDialogFragment {
    private AppCompatButton btnCancel;
    private AppCompatButton btnOk;
    private View lnCancel;
    private BottomSheetDialog mBottomSheetDialog;
    private String message;
    private String negativeButtonText;
    private String positiveButtonText;
    private String title;
    private boolean showNegativeButton = true;
    private View.OnClickListener positiveButtonOnClickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.BottomAlertDialogFragmentExternalSyntheticLambda1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BottomAlertDialogFragment._init_lambda0(BottomAlertDialogFragment.this, view);
        }
    };
    private View.OnClickListener negativeButtonOnClickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.fragment.BottomAlertDialogFragmentExternalSyntheticLambda2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BottomAlertDialogFragment._init_lambda1(BottomAlertDialogFragment.this, view);
        }
    };

    
    public static void _init_lambda0(BottomAlertDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
    }

    
    public static void _init_lambda1(BottomAlertDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.dismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_bottom_alert_dialog, viewGroup, false);
        this.btnCancel = inflate.findViewById(R.id.btnCancel);
        this.btnOk = inflate.findViewById(R.id.btnOk);
        this.lnCancel = inflate.findViewById(R.id.lnCancel);
        AppCompatTextView appCompatTextView = inflate.findViewById(R.id.txtDialogDescription);
        AppCompatTextView appCompatTextView2 = inflate.findViewById(R.id.txtDialogTitle);
        appCompatTextView.setText(!TextUtils.isEmpty(this.message) ? this.message : "");
        appCompatTextView.setVisibility(TextUtils.isEmpty(this.message) ? 8 : 0);
        appCompatTextView2.setText(TextUtils.isEmpty(this.title) ? "" : this.title);
        appCompatTextView2.setVisibility(TextUtils.isEmpty(this.title) ? 8 : 0);
        if (!TextUtils.isEmpty(this.positiveButtonText)) {
            AppCompatButton appCompatButton = this.btnOk;
            Intrinsics.checkNotNull(appCompatButton);
            appCompatButton.setText(this.positiveButtonText);
        }
        if (this.positiveButtonOnClickListener != null) {
            AppCompatButton appCompatButton2 = this.btnOk;
            Intrinsics.checkNotNull(appCompatButton2);
            appCompatButton2.setOnClickListener(this.positiveButtonOnClickListener);
        }
        if (!TextUtils.isEmpty(this.negativeButtonText)) {
            AppCompatButton appCompatButton3 = this.btnCancel;
            Intrinsics.checkNotNull(appCompatButton3);
            appCompatButton3.setText(this.negativeButtonText);
        }
        if (this.negativeButtonOnClickListener != null) {
            AppCompatButton appCompatButton4 = this.btnCancel;
            Intrinsics.checkNotNull(appCompatButton4);
            appCompatButton4.setOnClickListener(this.negativeButtonOnClickListener);
        }
        if (!this.showNegativeButton) {
            View view = this.lnCancel;
            Intrinsics.checkNotNull(view);
            view.setVisibility(8);
        }
        return inflate;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setPositiveButton(String str, View.OnClickListener onClickListener) {
        this.positiveButtonText = str;
        this.positiveButtonOnClickListener = onClickListener;
    }

    public void setNegativeButton(String str, View.OnClickListener onClickListener) {
        this.negativeButtonText = str;
        this.negativeButtonOnClickListener = onClickListener;
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
        if (view.findViewById(R.id.testid).getHeight() >= windowHeight) {
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
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNullExpressionValue(onCreateDialog, "onCreateDialog(...)");
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.fragment.BottomAlertDialogFragmentExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                BottomAlertDialogFragment.onCreateDialoglambda2(BottomAlertDialogFragment.this, dialogInterface);
            }
        });
        return onCreateDialog;
    }

    
    public static void onCreateDialoglambda2(BottomAlertDialogFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
        this0.mBottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        this0.setupFullHeight(bottomSheetDialog);
    }

    public void setShowNegativeButton(boolean z) {
        this.showNegativeButton = z;
    }
}
