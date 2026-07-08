package com.proje.mobilesales.features.customer.view.operation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.FragmentListNonSwipeBinding;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerOperationFragment extends BaseListFragment {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CABIN_REF = "extra:cabinRef";
    public static final String EXTRA_CUSTOMER_REF = "extra:customerRef";
    public static final String EXTRA_CUSTOMER_SHIPREF = "extra:customerShipRef";
    public static final String EXTRA_ROUTEDAY_REF = "extra:routeDayRef";
    public static final String EXTRA_ROUTEPLAN_REF = "extra:routePlanRef";
    public static final String EXTRA_ROUTEUSERCUSTOMER_REF = "extra:routeUserCustomerRef";
    public static final String STATE_CABIN_REF = "state:cabinRef";
    public static final String STATE_CUSTOMER_REF = "state:customerRef";
    public static final String STATE_CUSTOMER_SHIPREF = "state:customerShipRef";
    public static final String STATE_ROUTEDAY_REF = "state:routeDayRef";
    public static final String STATE_ROUTEPlAN_REF = "state:routePlanRef";
    public static final String STATE_ROUTEUSERCUSTOMER_REF = "state:routeUserCustomerRef";
    private FragmentListNonSwipeBinding _binding;
    private int cabinRef;
    private CustomerOperationRecyclerViewAdapter mAdapter = new CustomerOperationRecyclerViewAdapter();
    private int mCustomerRef;
    private EmptyListBinding mEmptyView;
    private int mShipRef;
    private int routeDayRef;
    private int routePlanRef;
    private int routeUserCustomerRef;

    private FragmentListNonSwipeBinding getBinding() {
        FragmentListNonSwipeBinding fragmentListNonSwipeBinding = this._binding;
        Intrinsics.checkNotNull(fragmentListNonSwipeBinding);
        return fragmentListNonSwipeBinding;
    }

    public CustomerOperationRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public void setMAdapter(CustomerOperationRecyclerViewAdapter customerOperationRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(customerOperationRecyclerViewAdapter, "<set-?>");
        this.mAdapter = customerOperationRecyclerViewAdapter;
    }
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (bundle != null) {
            this.mCustomerRef = bundle.getInt("state:customerRef");
            this.mShipRef = bundle.getInt(STATE_CUSTOMER_SHIPREF);
            this.routePlanRef = bundle.getInt(STATE_ROUTEPlAN_REF);
            this.routeDayRef = bundle.getInt(STATE_ROUTEDAY_REF);
            this.routeUserCustomerRef = bundle.getInt(STATE_ROUTEUSERCUSTOMER_REF);
            this.cabinRef = bundle.getInt(STATE_CABIN_REF);
            return;
        }
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        this.mCustomerRef = arguments.getInt("extra:customerRef");
        Bundle arguments2 = getArguments();
        Intrinsics.checkNotNull(arguments2);
        this.mShipRef = arguments2.getInt(EXTRA_CUSTOMER_SHIPREF);
        Bundle arguments3 = getArguments();
        Intrinsics.checkNotNull(arguments3);
        this.routePlanRef = arguments3.getInt(EXTRA_ROUTEPLAN_REF);
        Bundle arguments4 = getArguments();
        Intrinsics.checkNotNull(arguments4);
        this.routeDayRef = arguments4.getInt(EXTRA_ROUTEDAY_REF);
        Bundle arguments5 = getArguments();
        Intrinsics.checkNotNull(arguments5);
        this.routeUserCustomerRef = arguments5.getInt(EXTRA_ROUTEUSERCUSTOMER_REF);
        Bundle arguments6 = getArguments();
        Intrinsics.checkNotNull(arguments6);
        this.cabinRef = arguments6.getInt(EXTRA_CABIN_REF);
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentListNonSwipeBinding.inflate(inflater, viewGroup, false);
        this.mRecyclerView = getBinding().rcwList;
        EmptyListBinding emptyListBinding = getBinding().empty;
        this.mEmptyView = emptyListBinding;
        Intrinsics.checkNotNull(emptyListBinding);
        emptyListBinding.getRoot().setVisibility(4);
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
     public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mAdapter.setmCustomerRef(this.mCustomerRef);
        this.mAdapter.setMShipRef(this.mShipRef);
        this.mAdapter.setRouteRef(this.routeDayRef, this.routePlanRef, this.routeUserCustomerRef);
        this.mAdapter.setCabinRef(this.cabinRef);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt("state:customerRef", this.mCustomerRef);
        outState.putInt(STATE_CUSTOMER_SHIPREF, this.mShipRef);
        outState.putInt(STATE_ROUTEPlAN_REF, this.routeDayRef);
        outState.putInt(STATE_ROUTEDAY_REF, this.routePlanRef);
        outState.putInt(STATE_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        outState.putInt(STATE_CABIN_REF, this.cabinRef);
    }
     public void onDetach() {
        super.onDetach();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }
     public boolean onBackPressedFragment() {
        super.onBackPressedFragment();
        if (!this.mAdapter.getOpenSubMenu()) {
            return false;
        }
        this.mAdapter.toggleOpenSubMenu(null);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        super.onOptionsItemSelected(item);
        if (item.getItemId() != 16908332 || !this.mAdapter.getOpenSubMenu()) {
            return false;
        }
        this.mAdapter.toggleOpenSubMenu(null);
        return true;
    }

    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
