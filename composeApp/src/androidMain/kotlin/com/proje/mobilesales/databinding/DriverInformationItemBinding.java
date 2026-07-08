package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
public final class DriverInformationItemBinding implements ViewBinding {
    public final FichePropertyEditView driverIdentityNr;
    public final FichePropertyEditView driverName;
    public final FichePropertyEditView driverPlateNr;
    public final FichePropertyEditView driverSurname;
    public final FichePropertyEditView driverTrailerPlateNr;
    private final LinearLayout rootView;
    private DriverInformationItemBinding(final LinearLayout linearLayout, final FichePropertyEditView fichePropertyEditView, final FichePropertyEditView fichePropertyEditView2, final FichePropertyEditView fichePropertyEditView3, final FichePropertyEditView fichePropertyEditView4, final FichePropertyEditView fichePropertyEditView5) {
        rootView = linearLayout;
        driverIdentityNr = fichePropertyEditView;
        driverName = fichePropertyEditView2;
        driverPlateNr = fichePropertyEditView3;
        driverSurname = fichePropertyEditView4;
        driverTrailerPlateNr = fichePropertyEditView5;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DriverInformationItemBinding inflate(final LayoutInflater layoutInflater) {
        return DriverInformationItemBinding.inflate(layoutInflater, null, false);
    }
    public static DriverInformationItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.driver_information_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DriverInformationItemBinding.bind(inflate);
    }
    public static DriverInformationItemBinding bind(final View view) {
        int i2 = R.id.driverIdentityNr;
        final FichePropertyEditView fichePropertyEditView = ViewBindings.findChildViewById(view, R.id.driverIdentityNr);
        if (null != fichePropertyEditView) {
            i2 = R.id.driverName;
            final FichePropertyEditView fichePropertyEditView2 = ViewBindings.findChildViewById(view, R.id.driverName);
            if (null != fichePropertyEditView2) {
                i2 = R.id.driverPlateNr;
                final FichePropertyEditView fichePropertyEditView3 = ViewBindings.findChildViewById(view, R.id.driverPlateNr);
                if (null != fichePropertyEditView3) {
                    i2 = R.id.driverSurname;
                    final FichePropertyEditView fichePropertyEditView4 = ViewBindings.findChildViewById(view, R.id.driverSurname);
                    if (null != fichePropertyEditView4) {
                        i2 = R.id.driverTrailerPlateNr;
                        final FichePropertyEditView fichePropertyEditView5 = ViewBindings.findChildViewById(view, R.id.driverTrailerPlateNr);
                        if (null != fichePropertyEditView5) {
                            return new DriverInformationItemBinding((LinearLayout) view, fichePropertyEditView, fichePropertyEditView2, fichePropertyEditView3, fichePropertyEditView4, fichePropertyEditView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
