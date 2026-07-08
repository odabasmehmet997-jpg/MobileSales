package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class RowtodolistBinding implements ViewBinding {

   
    public final AppCompatTextView TAYRAC2;

   
    public final AppCompatTextView TBEGDATE;

   
    public final AppCompatTextView TDESC;

   
    public final AppCompatTextView TENDDATE;

   
    public final AppCompatTextView TODOID;

   
    public final AppCompatTextView TPRIORITYSTR;

   
    public final AppCompatTextView TSTATUSSTR;

   
    public final AppCompatTextView VAYRAC;

   
    private final RelativeLayout rootView;

    private RowtodolistBinding(final RelativeLayout relativeLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        rootView = relativeLayout;
        TAYRAC2 = appCompatTextView;
        TBEGDATE = appCompatTextView2;
        TDESC = appCompatTextView3;
        TENDDATE = appCompatTextView4;
        TODOID = appCompatTextView5;
        TPRIORITYSTR = appCompatTextView6;
        TSTATUSSTR = appCompatTextView7;
        VAYRAC = appCompatTextView8;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static RowtodolistBinding inflate(final LayoutInflater layoutInflater) {
        return RowtodolistBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowtodolistBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.rowtodolist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowtodolistBinding.bind(inflate);
    }

   
    public static RowtodolistBinding bind(final View view) {
        int i2 = R.id.TAYRAC2;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.TAYRAC2);
        if (null != appCompatTextView) {
            i2 = R.id.TBEGDATE;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.TBEGDATE);
            if (null != appCompatTextView2) {
                i2 = R.id.TDESC;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.TDESC);
                if (null != appCompatTextView3) {
                    i2 = R.id.TENDDATE;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.TENDDATE);
                    if (null != appCompatTextView4) {
                        i2 = R.id.TODOID;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.TODOID);
                        if (null != appCompatTextView5) {
                            i2 = R.id.TPRIORITYSTR;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.TPRIORITYSTR);
                            if (null != appCompatTextView6) {
                                i2 = R.id.TSTATUSSTR;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.TSTATUSSTR);
                                if (null != appCompatTextView7) {
                                    i2 = R.id.VAYRAC;
                                    final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.VAYRAC);
                                    if (null != appCompatTextView8) {
                                        return new RowtodolistBinding((RelativeLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
