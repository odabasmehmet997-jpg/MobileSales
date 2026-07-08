package com.proje.mobilesales.features.penetration.view.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.penetration.model.Penetration;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class PenetrationFragment extends BaseListFragment {
    public static final Companion Companion = new Companion(null);
    private static final String PENETRATION_FRAGMENT_TAG = PenetrationFragment.class.getName() + "PenetrationFragment";
    private PenetrationLineRecyclerViewAdapter mAdapter;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFabPenetration;

    public   CoordinatorLayout getMCoordinatorLayout() {
        return this.mCoordinatorLayout;
    }

    public   void setMCoordinatorLayout(CoordinatorLayout coordinatorLayout) {
        this.mCoordinatorLayout = coordinatorLayout;
    }

    public   FloatingActionButton getMFabPenetration() {
        return this.mFabPenetration;
    }

    public   void setMFabPenetration(FloatingActionButton floatingActionButton) {
        this.mFabPenetration = floatingActionButton;
    }

    public   PenetrationLineRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public   void setMAdapter(PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter) {
        this.mAdapter = penetrationLineRecyclerViewAdapter;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_penetration, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cord_frame);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        this.mCoordinatorLayout = (CoordinatorLayout) findViewById;
        View findViewById2 = inflate.findViewById(R.id.rcwList);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        this.mRecyclerView = (RecyclerView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.fab_penetration);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type com.google.android.material.floatingactionbutton.FloatingActionButton");
        this.mFabPenetration = (FloatingActionButton) findViewById3;
        return inflate;
    }
    protected IListRecyclerView getAdapter() {
        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
        return penetrationLineRecyclerViewAdapter;
    }

    public void setPenetrationLine() {
        PenetrationLineRecyclerViewAdapter penetrationLineRecyclerViewAdapter = this.mAdapter;
        Intrinsics.checkNotNull(penetrationLineRecyclerViewAdapter);
        Penetration penetration = getPenetration();
        penetrationLineRecyclerViewAdapter.setPenetrationLines(penetration != null ? penetration.getPenetrations() : null);
    }

    public Penetration getPenetration() {
        PenetrationActivity penetrationActivity = (PenetrationActivity) getActivity();
        Intrinsics.checkNotNull(penetrationActivity);
        return penetrationActivity.getPenetration();
    }

    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getPENETRATION_FRAGMENT_TAG() {
            return PenetrationFragment.PENETRATION_FRAGMENT_TAG;
        }
    }
}
