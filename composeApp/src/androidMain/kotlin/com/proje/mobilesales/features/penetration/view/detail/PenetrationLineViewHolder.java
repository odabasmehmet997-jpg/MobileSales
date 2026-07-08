package com.proje.mobilesales.features.penetration.view.detail;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;
 
public final class PenetrationLineViewHolder extends ItemViewHolder {
    public final PenetrationLineView mPenetrationLineView;
    public PenetrationLineViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        View findViewById = view.findViewById(R.id.penetration_line_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.features.penetration.view.detail.PenetrationLineView");
        this.mPenetrationLineView = (PenetrationLineView) findViewById;
    }
}
