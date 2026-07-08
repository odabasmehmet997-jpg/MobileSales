package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityNotificationWelcomeBinding implements ViewBinding {
    public final ItemLoadingBinding loadingBar;
    public final AppCompatImageView logo;
    private final ConstraintLayout rootView;
    private ActivityNotificationWelcomeBinding( final ConstraintLayout constraintLayout,  final ItemLoadingBinding itemLoadingBinding,  final AppCompatImageView appCompatImageView) {
        rootView = constraintLayout;
        loadingBar = itemLoadingBinding;
        logo = appCompatImageView;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static ActivityNotificationWelcomeBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityNotificationWelcomeBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityNotificationWelcomeBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_notification_welcome, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityNotificationWelcomeBinding.bind(inflate);
    }
    public static ActivityNotificationWelcomeBinding bind( final View view) {
        int i2 = R.id.loadingBar;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.loadingBar);
        if (null != findChildViewById) {
            final ItemLoadingBinding bind = ItemLoadingBinding.bind(findChildViewById);
            final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.logo);
            if (null != appCompatImageView) {
                return new ActivityNotificationWelcomeBinding((ConstraintLayout) view, bind, appCompatImageView);
            }
            i2 = R.id.logo;
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
