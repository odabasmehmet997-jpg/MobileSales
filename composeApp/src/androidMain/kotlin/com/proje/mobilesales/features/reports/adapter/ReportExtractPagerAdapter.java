package com.proje.mobilesales.features.reports.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceDetailActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceHeaderActivity;
import kotlin.jvm.internal.Intrinsics;

public final class ReportExtractPagerAdapter extends FragmentStatePagerAdapter {
    private final ReportExtractInvoiceActivity.ExtractInvHeader extractInvDetail;
    private final Context mContext;
    private final Fragment[] mFragments;
    private final String[] mPageName;

    public Parcelable saveState() {
        return null;
    }

    public ReportExtractPagerAdapter(final Context mContext, final FragmentManager fragmentManager, final String[] mPageName, final ReportExtractInvoiceActivity.ExtractInvHeader extractInvDetail) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mPageName, "mPageName");
        Intrinsics.checkNotNullParameter(extractInvDetail, "extractInvDetail");
        Intrinsics.checkNotNull(fragmentManager);
        this.mContext = mContext;
        this.mPageName = mPageName;
        this.extractInvDetail = extractInvDetail;
        mFragments = new Fragment[2];
    }

    public Fragment getItem(final int i2) {
        final Fragment fragment = mFragments[i2];
        if (null != fragment) {
            Intrinsics.checkNotNull(fragment);
            return fragment;
        }
        if (0 == i2) {
            final Bundle bundle = new Bundle();
            bundle.putParcelable("INVOICE", extractInvDetail);
            final Fragment instantiate = Fragment.instantiate(mContext, ReportExtractInvoiceHeaderActivity.class.getName(), bundle);
            Intrinsics.checkNotNull(instantiate);
            return instantiate;
        }
        final Bundle bundle2 = new Bundle();
        bundle2.putParcelable("INVOICE", extractInvDetail);
        final Fragment instantiate2 = Fragment.instantiate(mContext, ReportExtractInvoiceDetailActivity.class.getName(), bundle2);
        Intrinsics.checkNotNull(instantiate2);
        return instantiate2;
    }

    public int getCount() {
        return mFragments.length;
    }

    public Object instantiateItem(final ViewGroup container, final int i2) {
        Intrinsics.checkNotNullParameter(container, "container");
        final Fragment[] fragmentArr = mFragments;
        final Object instantiateItem = super.instantiateItem(container, i2);
        Intrinsics.checkNotNull(instantiateItem, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
        fragmentArr[i2] = (Fragment) instantiateItem;
        final Fragment fragment = mFragments[i2];
        Intrinsics.checkNotNull(fragment);
        return fragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(final int i2) {
        return mPageName[i2];
    }
}
