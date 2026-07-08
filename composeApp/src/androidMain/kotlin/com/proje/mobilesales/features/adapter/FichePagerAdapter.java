package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailListFragment;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheHeaderFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheDetailListFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheHeaderFragment;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;

public class FichePagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "FichePagerAdapter";
    private CustomerOperation customerOperation;
    private int customerRef;
    private int invoiceRef;
    private final Context mContext;
    private final Fragment[] mFragments;
    private final String[] mPageName;

    public FichePagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragments = new Fragment[2];
        this.mContext = context;
        this.mPageName = context.getResources().getStringArray(R.array.array_fiche_tab);
    }

    public void setCustomerOperation(CustomerOperation customerOperation) {
        this.customerOperation = customerOperation;
    }

    public void setCustomerRef(int i2) {
        this.customerRef = i2;
    }

    public void setInvoiceRef(int i2) {
        this.invoiceRef = i2;
    }
    public Fragment getItem(int i2) {
        Fragment fragment = this.mFragments[i2];
        if (fragment != null) {
            return fragment;
        }
        if (i2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, this.customerOperation);
            bundle.putInt(IntentExtraName.EXTRA_CUSTOMER_REF, this.customerRef);
            if (this.customerOperation.getReceiptType() == ReceiptType.CASH || this.customerOperation.getReceiptType() == ReceiptType.CREDIT) {
                return Fragment.instantiate(this.mContext, CashCreditFicheHeaderFragment.class.getName(), bundle);
            }
            return Fragment.instantiate(this.mContext, ChequeDeedFicheHeaderFragment.class.getName(), bundle);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, this.customerOperation);
        bundle2.putInt(IntentExtraName.EXTRA_CUSTOMER_REF, this.customerRef);
        bundle2.putInt(IntentExtraName.INVOICE_PAYMENTLINE_REF, this.invoiceRef);
        if (this.customerOperation.getReceiptType() == ReceiptType.CASH || this.customerOperation.getReceiptType() == ReceiptType.CREDIT) {
            return Fragment.instantiate(this.mContext, CashCreditFicheDetailListFragment.class.getName(), bundle2);
        }
        return Fragment.instantiate(this.mContext, ChequeDeedFicheDetailListFragment.class.getName(), bundle2);
    }
    public int getCount() {
        return this.mFragments.length;
    }
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        this.mFragments[i2] = (Fragment) super.instantiateItem(viewGroup, i2);
        return this.mFragments[i2];
    }
    public CharSequence getPageTitle(int i2) {
        return this.mPageName[i2];
    }
}
