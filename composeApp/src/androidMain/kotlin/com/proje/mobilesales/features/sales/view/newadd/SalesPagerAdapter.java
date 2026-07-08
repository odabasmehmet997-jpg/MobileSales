package com.proje.mobilesales.features.sales.view.newadd;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.proje.mobilesales.core.interfaces.UpdateFragmentView;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragment;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesPagerAdapter extends FragmentPagerAdapter {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SalesPagerAdapter";
    private final Context mContext;
    private final int mCustomerRef;
    private final Fragment[] mFragments;
    private final String[] mPageName;

    public SalesPagerAdapter(Context mContext, FragmentManager fm, String[] mPageName, int i2) {
        super(fm);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(mPageName, "mPageName");
        this.mContext = mContext;
        this.mPageName = mPageName;
        this.mCustomerRef = i2;
        this.mFragments = new Fragment[mPageName.length];
    }
    public Fragment getItem(int i2) {
        Fragment fragment = this.mFragments[i2];
        if (fragment != null) {
            Intrinsics.checkNotNull(fragment);
            return fragment;
        }
        if (i2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt(SalesHeaderFragment.Companion.getEXTRA_CUSTOMER_REF(), this.mCustomerRef);
            Fragment instantiate = Fragment.instantiate(this.mContext, SalesHeaderFragment.class.getName(), bundle);
            Intrinsics.checkNotNull(instantiate);
            return instantiate;
        }
        if (i2 == 1) {
            Fragment instantiate2 = Fragment.instantiate(this.mContext, SalesDetailListFragment.class.getName(), new Bundle());
            Intrinsics.checkNotNull(instantiate2);
            return instantiate2;
        }
        if (i2 == 2) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt(SalesLogisticFragment.Companion.getEXTRA_CUSTOMER_REF(), this.mCustomerRef);
            Fragment instantiate3 = Fragment.instantiate(this.mContext, SalesLogisticFragment.class.getName(), bundle2);
            Intrinsics.checkNotNull(instantiate3);
            return instantiate3;
        }
        Bundle bundle3 = new Bundle();
        bundle3.putInt(SalesPaymentFragment.Companion.getEXTRA_CUSTOMER_REF(), this.mCustomerRef);
        Fragment instantiate4 = Fragment.instantiate(this.mContext, SalesPaymentFragment.class.getName(), bundle3);
        Intrinsics.checkNotNull(instantiate4);
        return instantiate4;
    }

    public int getItemPosition(Object fObject) {
        Intrinsics.checkNotNullParameter(fObject, "fObject");
        if (fObject instanceof SalesLogisticFragment) {
            ((SalesLogisticFragment) fObject).update();
        } else if (fObject instanceof SalesDetailListFragment) {
            ((SalesDetailListFragment) fObject).update();
        }
        return super.getItemPosition(fObject);
    }
    public void destroyItem(ViewGroup container, int i2, Object obj) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(obj, "obj");
        super.destroyItem(container, i2, obj);
        Log.d(TAG, "destroyItem(" + i2 + ')');
    }

    public int getCount() {
        return this.mFragments.length;
    }

    public Object instantiateItem(ViewGroup container, int i2) {
        Intrinsics.checkNotNullParameter(container, "container");
        Fragment[] fragmentArr = this.mFragments;
        Object instantiateItem = super.instantiateItem(container, i2);
        Intrinsics.checkNotNull(instantiateItem, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
        fragmentArr[i2] = (Fragment) instantiateItem;
        Fragment fragment = this.mFragments[i2];
        Intrinsics.checkNotNull(fragment);
        return fragment;
    }
    public CharSequence getPageTitle(int i2) {
        return this.mPageName[i2];
    }

    public void updateFragments() {
        for (Object obj : this.mFragments) {
            if (obj instanceof UpdateFragmentView) {
                ((UpdateFragmentView) obj).update();
            }
        }
    }

    public void updateStockAmounts() {
        for (Fragment fragment : this.mFragments) {
            if (fragment instanceof SalesDetailListFragment) {
                ((SalesDetailListFragment) fragment).updateStockAmounts();
            }
        }
    }

    public void showCurrencyPriceTextView() {
        for (Fragment fragment : this.mFragments) {
            if (fragment instanceof SalesDetailListFragment) {
                ((SalesDetailListFragment) fragment).showCurrencyPriceTextView();
            }
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
