package com.proje.mobilesales.features.visit.adapter;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import com.proje.mobilesales.features.visit.view.VisitView;
import kotlin.jvm.internal.Intrinsics;

public final class VisitViewHolder extends ItemViewHolder {
    public final VisitView mVisitView;
    public VisitViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View findViewById = itemView.findViewById(R.id.visit_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.features.visit.view.VisitView");
        this.mVisitView = (VisitView) findViewById;
    }
}
