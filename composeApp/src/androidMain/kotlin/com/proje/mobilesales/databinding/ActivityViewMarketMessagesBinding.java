package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityViewMarketMessagesBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final RecyclerView rvMarketingMessages;
    private ActivityViewMarketMessagesBinding(final LinearLayout linearLayout, final RecyclerView recyclerView) {
        rootView = linearLayout;
        rvMarketingMessages = recyclerView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityViewMarketMessagesBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityViewMarketMessagesBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityViewMarketMessagesBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_view_market_messages, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityViewMarketMessagesBinding.bind(inflate);
    }
    public static ActivityViewMarketMessagesBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rv_marketing_messages);
        if (null != recyclerView) {
            return new ActivityViewMarketMessagesBinding((LinearLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rv_marketing_messages));
    }
}
