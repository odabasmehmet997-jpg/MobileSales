package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DialogOrderInquiryBinding implements ViewBinding {
    public final LinearLayout LinearLayout01;
    public final LinearLayout LinearLayout02;
    public final TableLayout TableLayout01;
    public final TableLayout TableLayout02;
    public final AppCompatButton btnCancel;
    public final AppCompatButton btnOk;
    public final AppCompatEditText etOrderNo;
    public final LinearLayout layoutRoot;
    public final LinearLayout lyHeader;
    private final LinearLayout rootView;
    public final AppCompatTextView tvCustomer;
    public final AppCompatTextView tvCustomerCode;
    public final AppCompatTextView tvShipmentAccount;
    public final AppCompatTextView tvShipmentAddress;
    public final AppCompatTextView tvSpace;
    private DialogOrderInquiryBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final TableLayout tableLayout, final TableLayout tableLayout2, final AppCompatButton appCompatButton, final AppCompatButton appCompatButton2, final AppCompatEditText appCompatEditText, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5) {
        rootView = linearLayout;
        LinearLayout01 = linearLayout2;
        LinearLayout02 = linearLayout3;
        TableLayout01 = tableLayout;
        TableLayout02 = tableLayout2;
        btnCancel = appCompatButton;
        btnOk = appCompatButton2;
        etOrderNo = appCompatEditText;
        layoutRoot = linearLayout4;
        lyHeader = linearLayout5;
        tvCustomer = appCompatTextView;
        tvCustomerCode = appCompatTextView2;
        tvShipmentAccount = appCompatTextView3;
        tvShipmentAddress = appCompatTextView4;
        tvSpace = appCompatTextView5;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DialogOrderInquiryBinding inflate(final LayoutInflater layoutInflater) {
        return DialogOrderInquiryBinding.inflate(layoutInflater, null, false);
    }
    public static DialogOrderInquiryBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_order_inquiry, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogOrderInquiryBinding.bind(inflate);
    }
    public static DialogOrderInquiryBinding bind(final View view) {
        int i2 = R.id.LinearLayout01;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.LinearLayout01);
        if (null != linearLayout) {
            i2 = R.id.LinearLayout02;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.LinearLayout02);
            if (null != linearLayout2) {
                i2 = R.id.TableLayout01;
                final TableLayout tableLayout = ViewBindings.findChildViewById(view, R.id.TableLayout01);
                if (null != tableLayout) {
                    i2 = R.id.TableLayout02;
                    final TableLayout tableLayout2 = ViewBindings.findChildViewById(view, R.id.TableLayout02);
                    if (null != tableLayout2) {
                        i2 = R.id.btnCancel;
                        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnCancel);
                        if (null != appCompatButton) {
                            i2 = R.id.btnOk;
                            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnOk);
                            if (null != appCompatButton2) {
                                i2 = R.id.etOrderNo;
                                final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.etOrderNo);
                                if (null != appCompatEditText) {
                                    final LinearLayout linearLayout3 = (LinearLayout) view;
                                    i2 = R.id.lyHeader;
                                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.lyHeader);
                                    if (null != linearLayout4) {
                                        i2 = R.id.tvCustomer;
                                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCustomer);
                                        if (null != appCompatTextView) {
                                            i2 = R.id.tvCustomerCode;
                                            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCustomerCode);
                                            if (null != appCompatTextView2) {
                                                i2 = R.id.tvShipmentAccount;
                                                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvShipmentAccount);
                                                if (null != appCompatTextView3) {
                                                    i2 = R.id.tvShipmentAddress;
                                                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvShipmentAddress);
                                                    if (null != appCompatTextView4) {
                                                        i2 = R.id.tvSpace;
                                                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvSpace);
                                                        if (null != appCompatTextView5) {
                                                            return new DialogOrderInquiryBinding(linearLayout3, linearLayout, linearLayout2, tableLayout, tableLayout2, appCompatButton, appCompatButton2, appCompatEditText, linearLayout3, linearLayout4, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
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
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
