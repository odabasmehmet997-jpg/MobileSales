package com.proje.mobilesales.features.penetration.view.list;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;
 
public final class PenetrationViewHolder extends ItemViewHolder {
    private final PenetrationView mPenetrationView;
    public PenetrationViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        View findViewById = view.findViewById(R.id.penetration_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.features.penetration.view.list.PenetrationView");
        this.mPenetrationView = (PenetrationView) findViewById;
    }
    public PenetrationView getMPenetrationView() {
        return this.mPenetrationView;
    }
}
