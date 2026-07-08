package com.proje.mobilesales.features.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.ColorRes;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: BottomOptionListFragment.kt */

public final class BottomOptionListFragment extends BottomSheetDialogFragment {
    private BottomSheetDialog mBottomSheetDialog;
    private final ArrayList<OptionButton> mOptionButtons = new ArrayList<>();
    private String message;
    private String title;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_bottom_option_list, viewGroup, false);
        LinearLayout linearLayout = inflate.findViewById(R.id.lnButtonList);
        Intrinsics.checkNotNull(linearLayout);
        createButtons(linearLayout);
        AppCompatTextView appCompatTextView = inflate.findViewById(R.id.txtDialogDescription);
        AppCompatTextView appCompatTextView2 = inflate.findViewById(R.id.txtDialogTitle);
        appCompatTextView.setText(!TextUtils.isEmpty(this.message) ? this.message : "");
        appCompatTextView.setVisibility(TextUtils.isEmpty(this.message) ? 8 : 0);
        appCompatTextView2.setText(TextUtils.isEmpty(this.title) ? "" : this.title);
        appCompatTextView2.setVisibility(TextUtils.isEmpty(this.title) ? 8 : 0);
        return inflate;
    }

    private void createButtons(LinearLayout linearLayout) {
        if (this.mOptionButtons.isEmpty()) {
            return;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), 2131952467);
        Iterator<OptionButton> it = this.mOptionButtons.iterator();
        while (it.hasNext()) {
            OptionButton next = it.next();
            AppCompatButton appCompatButton = new AppCompatButton(contextThemeWrapper, null, 2131952467);
            appCompatButton.setGravity(17);
            appCompatButton.setText(next.getText());
            if (next.getTextColor() != -1) {
                appCompatButton.setTextColor(getResources().getColor(next.getTextColor()));
            }
            appCompatButton.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            appCompatButton.setOnClickListener(next.getOnClickListener());
            linearLayout.addView(appCompatButton);
        }
    }

    public void addOptionButton(String str, @ColorRes int i2, View.OnClickListener onClickListener) {
        this.mOptionButtons.add(new OptionButton(str, i2, onClickListener));
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setMessage(String str) {
        this.message = str;
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
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.proje.mobilesales.features.fragment.BottomOptionListFragmentExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                BottomOptionListFragment.onCreateDialoglambda0(BottomOptionListFragment.this, dialogInterface);
            }
        });
        return onCreateDialog;
    }

    
    public static void onCreateDialoglambda0(BottomOptionListFragment this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
        this0.mBottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        this0.setupFullHeight(bottomSheetDialog);
    }

    /* compiled from: BottomOptionListFragment.kt */
    private final class OptionButton {
        private View.OnClickListener onClickListener;
        private String text;

        @ColorRes
        private int textColor;

        public OptionButton(String str, @ColorRes int i2, View.OnClickListener onClickListener) {
            this.text = str;
            this.textColor = i2;
            this.onClickListener = onClickListener;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public int getTextColor() {
            return this.textColor;
        }

        public void setTextColor(int i2) {
            this.textColor = i2;
        }

        public View.OnClickListener getOnClickListener() {
            return this.onClickListener;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }
}
