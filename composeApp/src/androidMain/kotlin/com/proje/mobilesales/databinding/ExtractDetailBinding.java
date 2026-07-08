package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class ExtractDetailBinding implements ViewBinding {
    public final ListView lvList;
    private final LinearLayout rootView;
    public final AppCompatTextView tvCustomer;
    public final AppCompatTextView tvDate;
    public final AppCompatTextView tvDesc1;
    public final AppCompatTextView tvDesc2;
    public final AppCompatTextView tvDesc3;
    public final AppCompatTextView tvDesc4;
    public final AppCompatTextView tvEmpty;
    public final AppCompatTextView tvFicheNo;
    public final AppCompatTextView tvField1;
    public final AppCompatTextView tvField2;
    public final AppCompatTextView tvField3;
    public final AppCompatTextView tvField4;
    public final AppCompatTextView tvSpecode;
    public final AppCompatTextView tvTotal;
    private ExtractDetailBinding(final LinearLayout linearLayout, final ListView listView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final AppCompatTextView appCompatTextView12, final AppCompatTextView appCompatTextView13, final AppCompatTextView appCompatTextView14) {
        rootView = linearLayout;
        lvList = listView;
        tvCustomer = appCompatTextView;
        tvDate = appCompatTextView2;
        tvDesc1 = appCompatTextView3;
        tvDesc2 = appCompatTextView4;
        tvDesc3 = appCompatTextView5;
        tvDesc4 = appCompatTextView6;
        tvEmpty = appCompatTextView7;
        tvFicheNo = appCompatTextView8;
        tvField1 = appCompatTextView9;
        tvField2 = appCompatTextView10;
        tvField3 = appCompatTextView11;
        tvField4 = appCompatTextView12;
        tvSpecode = appCompatTextView13;
        tvTotal = appCompatTextView14;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractDetailBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractDetailBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractDetailBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractDetailBinding.bind(inflate);
    }
    public static ExtractDetailBinding bind(final View view) {
        int i2 = R.id.lvList;
        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvList);
        if (null != listView) {
            i2 = R.id.tvCustomer;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCustomer);
            if (null != appCompatTextView) {
                i2 = R.id.tvDate;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDate);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvDesc1;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDesc1);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvDesc2;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvDesc2);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvDesc3;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvDesc3);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvDesc4;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvDesc4);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.tvEmpty;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvEmpty);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.tvFicheNo;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                                        if (null != appCompatTextView8) {
                                            i2 = R.id.tvField1;
                                            final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.tvField1);
                                            if (null != appCompatTextView9) {
                                                i2 = R.id.tvField2;
                                                final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.tvField2);
                                                if (null != appCompatTextView10) {
                                                    i2 = R.id.tvField3;
                                                    final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.tvField3);
                                                    if (null != appCompatTextView11) {
                                                        i2 = R.id.tvField4;
                                                        final AppCompatTextView appCompatTextView12 = ViewBindings.findChildViewById(view, R.id.tvField4);
                                                        if (null != appCompatTextView12) {
                                                            i2 = R.id.tvSpecode;
                                                            final AppCompatTextView appCompatTextView13 = ViewBindings.findChildViewById(view, R.id.tvSpecode);
                                                            if (null != appCompatTextView13) {
                                                                i2 = R.id.tvTotal;
                                                                final AppCompatTextView appCompatTextView14 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                                                                if (null != appCompatTextView14) {
                                                                    return new ExtractDetailBinding((LinearLayout) view, listView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
