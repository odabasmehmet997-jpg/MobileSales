package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class SerialLotsBinding implements ViewBinding {
    public final AppCompatCheckBox cbListItem;
    public final AppCompatEditText edLotNo;
    public final EditText edtSurplusAmount;
    public final AppCompatImageButton imgBtnSearch;
    public final LinearLayout linearProgress;
    public final LinearLayout lnSurplusAmount;
    public final RecyclerView lvSerialLotNos;
    private final LinearLayout rootView;
    public final AppCompatTextView tvAmount;
    public final AppCompatTextView tvCheckedCount;
    public final AppCompatTextView tvSerialNo;
    public final AppCompatTextView tvUnit;
    public final AppCompatTextView tvUserAmount;
    private SerialLotsBinding(final LinearLayout linearLayout, final AppCompatCheckBox appCompatCheckBox, final AppCompatEditText appCompatEditText, final EditText editText, final AppCompatImageButton appCompatImageButton, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final RecyclerView recyclerView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5) {
        rootView = linearLayout;
        cbListItem = appCompatCheckBox;
        edLotNo = appCompatEditText;
        edtSurplusAmount = editText;
        imgBtnSearch = appCompatImageButton;
        linearProgress = linearLayout2;
        lnSurplusAmount = linearLayout3;
        lvSerialLotNos = recyclerView;
        tvAmount = appCompatTextView;
        tvCheckedCount = appCompatTextView2;
        tvSerialNo = appCompatTextView3;
        tvUnit = appCompatTextView4;
        tvUserAmount = appCompatTextView5;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static SerialLotsBinding inflate(final LayoutInflater layoutInflater) {
        return SerialLotsBinding.inflate(layoutInflater, null, false);
    }
    public static SerialLotsBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.serial_lots, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SerialLotsBinding.bind(inflate);
    }
    public static SerialLotsBinding bind(final View view) {
        int i2 = R.id.cbListItem;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.cbListItem);
        if (null != appCompatCheckBox) {
            i2 = R.id.edLotNo;
            final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edLotNo);
            if (null != appCompatEditText) {
                i2 = R.id.edt_surplusAmount;
                final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_surplusAmount);
                if (null != editText) {
                    i2 = R.id.imgBtnSearch;
                    final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgBtnSearch);
                    if (null != appCompatImageButton) {
                        i2 = R.id.linearProgress;
                        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearProgress);
                        if (null != linearLayout) {
                            i2 = R.id.ln_surplusAmount;
                            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_surplusAmount);
                            if (null != linearLayout2) {
                                i2 = R.id.lvSerialLotNos;
                                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.lvSerialLotNos);
                                if (null != recyclerView) {
                                    i2 = R.id.tvAmount;
                                    final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
                                    if (null != appCompatTextView) {
                                        i2 = R.id.tvCheckedCount;
                                        final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCheckedCount);
                                        if (null != appCompatTextView2) {
                                            i2 = R.id.tvSerialNo;
                                            final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvSerialNo);
                                            if (null != appCompatTextView3) {
                                                i2 = R.id.tvUnit;
                                                final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvUnit);
                                                if (null != appCompatTextView4) {
                                                    i2 = R.id.tvUserAmount;
                                                    final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvUserAmount);
                                                    if (null != appCompatTextView5) {
                                                        return new SerialLotsBinding((LinearLayout) view, appCompatCheckBox, appCompatEditText, editText, appCompatImageButton, linearLayout, linearLayout2, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
