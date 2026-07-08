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
 
public final class ActivityDriverInformationBinding implements ViewBinding {
    public final DriverInformationItemBinding driver1;
    public final DriverInformationItemBinding driver2;
    public final DriverInformationItemBinding driver3;
    public final LinearLayout driverInfoLayout;
    private final LinearLayout rootView;
    private ActivityDriverInformationBinding( final LinearLayout linearLayout,  final DriverInformationItemBinding driverInformationItemBinding,  final DriverInformationItemBinding driverInformationItemBinding2,  final DriverInformationItemBinding driverInformationItemBinding3,  final LinearLayout linearLayout2) {
        rootView = linearLayout;
        driver1 = driverInformationItemBinding;
        driver2 = driverInformationItemBinding2;
        driver3 = driverInformationItemBinding3;
        driverInfoLayout = linearLayout2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityDriverInformationBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityDriverInformationBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityDriverInformationBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_driver_information, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityDriverInformationBinding.bind(inflate);
    }
    public static ActivityDriverInformationBinding bind( final View view) {
        int i2 = R.id.driver1;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.driver1);
        if (null != findChildViewById) {
            final DriverInformationItemBinding bind = DriverInformationItemBinding.bind(findChildViewById);
            i2 = R.id.driver2;
            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.driver2);
            if (null != findChildViewById2) {
                final DriverInformationItemBinding bind2 = DriverInformationItemBinding.bind(findChildViewById2);
                i2 = R.id.driver3;
                final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.driver3);
                if (null != findChildViewById3) {
                    final DriverInformationItemBinding bind3 = DriverInformationItemBinding.bind(findChildViewById3);
                    i2 = R.id.driverInfoLayout;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.driverInfoLayout);
                    if (null != linearLayout) {
                        return new ActivityDriverInformationBinding((LinearLayout) view, bind, bind2, bind3, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
