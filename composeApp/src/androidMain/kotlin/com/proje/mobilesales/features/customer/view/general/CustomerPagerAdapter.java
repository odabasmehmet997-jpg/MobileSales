package com.proje.mobilesales.features.customer.view.general;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.view.communication.CustomerCommunicationFragment;
import com.proje.mobilesales.features.customer.view.operation.CustomerOperationFragment;
import com.proje.mobilesales.features.customer.view.risk.CustomerRiskFragment;
import com.proje.mobilesales.features.customer.view.summary.CustomerSummaryFragment;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CustomerPagerAdapter.kt */

public final class CustomerPagerAdapter extends FragmentStatePagerAdapter {
    private final Context mContext;
    private final CustomerDetail mCustomerDetail;
    private final Fragment[] mFragments;
    private final String[] mPageName;

    
    public CustomerPagerAdapter(Context mContext, FragmentManager fm, String[] mPageName, CustomerDetail mCustomerDetail) {
        super(fm);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(mPageName, "mPageName");
        Intrinsics.checkNotNullParameter(mCustomerDetail, "mCustomerDetail");
        this.mContext = mContext;
        this.mPageName = mPageName;
        this.mCustomerDetail = mCustomerDetail;
        this.mFragments = new Fragment[5];
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i2) {
        Fragment fragment = this.mFragments[i2];
        if (fragment != null) {
            Intrinsics.checkNotNull(fragment);
            return fragment;
        }
        if (i2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra:customerDetail", this.mCustomerDetail);
            Fragment instantiate = Fragment.instantiate(this.mContext, CustomerGeneralFragment.class.getName(), bundle);
            Intrinsics.checkNotNull(instantiate);
            return instantiate;
        }
        if (i2 == 1) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("extra:customerDetail", this.mCustomerDetail);
            Fragment instantiate2 = Fragment.instantiate(this.mContext, CustomerCommunicationFragment.class.getName(), bundle2);
            Intrinsics.checkNotNull(instantiate2);
            return instantiate2;
        }
        if (i2 == 2) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("extra:customerRef", this.mCustomerDetail.getLogicalRef());
            bundle3.putInt(CustomerOperationFragment.EXTRA_CUSTOMER_SHIPREF, this.mCustomerDetail.getShipRef());
            bundle3.putInt(CustomerOperationFragment.EXTRA_ROUTEDAY_REF, this.mCustomerDetail.getRouteDayRef());
            bundle3.putInt(CustomerOperationFragment.EXTRA_ROUTEPLAN_REF, this.mCustomerDetail.getRoutePlanRef());
            bundle3.putInt(CustomerOperationFragment.EXTRA_ROUTEUSERCUSTOMER_REF, this.mCustomerDetail.getRouteUserCustomerRef());
            bundle3.putInt(CustomerOperationFragment.EXTRA_CABIN_REF, this.mCustomerDetail.getCabinRef());
            Fragment instantiate3 = Fragment.instantiate(this.mContext, CustomerOperationFragment.class.getName(), bundle3);
            Intrinsics.checkNotNull(instantiate3);
            return instantiate3;
        }
        if (i2 == 3) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("extra:customerRef", this.mCustomerDetail.getLogicalRef());
            Fragment instantiate4 = Fragment.instantiate(this.mContext, CustomerSummaryFragment.class.getName(), bundle4);
            Intrinsics.checkNotNull(instantiate4);
            return instantiate4;
        }
        Bundle bundle5 = new Bundle();
        bundle5.putInt("extra:customerRef", this.mCustomerDetail.getLogicalRef());
        Fragment instantiate5 = Fragment.instantiate(this.mContext, CustomerRiskFragment.class.getName(), bundle5);
        Intrinsics.checkNotNull(instantiate5);
        return instantiate5;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mFragments.length;
    }

    
    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, int i2) {
        Intrinsics.checkNotNullParameter(container, "container");
        Fragment[] fragmentArr = this.mFragments;
        Object instantiateItem = super.instantiateItem(container, i2);
        Intrinsics.checkNotNull(instantiateItem, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
        fragmentArr[i2] = instantiateItem;
        Fragment fragment = this.mFragments[i2];
        Intrinsics.checkNotNull(fragment);
        return fragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i2) {
        return this.mPageName[i2];
    }
}
