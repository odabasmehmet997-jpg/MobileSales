package com.proje.mobilesales.features.customer.view.communication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.widget.TintableTextView;
import com.proje.mobilesales.databinding.FragmentCustomerCommunicationBinding;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import com.proje.mobilesales.features.customer.view.communication.person.CustomerNetPersonActivity;
import com.proje.mobilesales.features.customer.view.communication.person.CustomerPersonActivity;
import com.proje.mobilesales.features.customer.view.communication.ship.CustomerShipAddressActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerCommunicationFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_DETAIL = "extra:customerDetail";
    public static final String STATE_CUSTOMER_DETAIL = "state:customerDetail";
    private FragmentCustomerCommunicationBinding _binding;
    private CustomerDetail mCustomerDetail;
    private ImageButton mImgCustomerInvoiceAddress;
    private ImageButton mImgCustomerShipAddress;
    private LinearLayout mLnCustomerInvoiceAddress;
    private LinearLayout mLnCustomerShipAddress;
    private TintableTextView mTxtCustomerAllPerson;
    private TintableTextView mTxtCustomerInvoiceAddress;
    private AppCompatTextView mTxtCustomerPerson;
    private TintableTextView mTxtCustomerShipAddress;
    private final BaseCustomerRepository repository;
    private final BaseCustomerViewModel viewModel;

    public CustomerCommunicationFragment() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        this.repository = baseCustomerRepository;
        this.viewModel = new BaseCustomerViewModel(baseCustomerRepository);
    }

    private FragmentCustomerCommunicationBinding getBinding() {
        FragmentCustomerCommunicationBinding fragmentCustomerCommunicationBinding = this._binding;
        Intrinsics.checkNotNull(fragmentCustomerCommunicationBinding);
        return fragmentCustomerCommunicationBinding;
    }

    public void onCreate(Bundle bundle) {
        CustomerDetail customerDetail;
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (bundle != null) {
            customerDetail = bundle.getParcelable("state:customerDetail");
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            customerDetail = arguments.getParcelable("extra:customerDetail");
        }
        this.mCustomerDetail = customerDetail;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentCustomerCommunicationBinding.inflate(inflater, viewGroup, false);
        this.mTxtCustomerPerson = getBinding().txtCustomerPerson;
        TintableTextView tintableTextView = getBinding().txtAllPerson;
        this.mTxtCustomerAllPerson = tintableTextView;
        if (tintableTextView != null) {
            tintableTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomerCommunicationFragment.onCreateViewlambda0(CustomerCommunicationFragment.this, view);
                }
            });
        }
        LinearLayout linearLayout = getBinding().lnCustomerShipAddress;
        this.mLnCustomerShipAddress = linearLayout;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomerCommunicationFragment.onCreateViewlambda1(CustomerCommunicationFragment.this, view);
                }
            });
        }
        this.mImgCustomerShipAddress = getBinding().imgCustomerShipAddress;
        this.mTxtCustomerShipAddress = getBinding().txtCustomerShipAddress;
        this.mLnCustomerInvoiceAddress = getBinding().lnCustomerInvoiceAddress;
        this.mImgCustomerInvoiceAddress = getBinding().imgCustomerInvoiceAddress;
        this.mTxtCustomerInvoiceAddress = getBinding().txtCustomerInvoiceAddress;
        loadData();
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
    public static void onCreateViewlambda0(CustomerCommunicationFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.viewModel.erpType() == ErpType.NETSIS) {
            Intent intent = new Intent(this0.getActivity(), CustomerNetPersonActivity.class);
            String str = IntentExtraName.EXTRA_CUSTOMER_REF;
            CustomerDetail customerDetail = this0.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail);
            intent.putExtra(str, customerDetail.getLogicalRef());
            this0.startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(this0.getActivity(), CustomerPersonActivity.class);
        intent2.putExtra("extra:customerDetail", this0.mCustomerDetail);
        this0.startActivity(intent2);
    }

    public static void onCreateViewlambda1(CustomerCommunicationFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = new Intent(this0.getActivity(), CustomerShipAddressActivity.class);
        CustomerDetail customerDetail = this0.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail);
        intent.putExtra("extra:customerRef", customerDetail.getLogicalRef());
        this0.startActivity(intent);
    }

    private void loadData() {
        CustomerDetail customerDetail;
        CustomerDetail customerDetail2;
        CustomerDetail customerDetail3 = this.mCustomerDetail;
        if (customerDetail3 == null) {
            return;
        }
        Intrinsics.checkNotNull(customerDetail3);
        if (customerDetail3.getPerson() != null) {
            CustomerDetail customerDetail4 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail4);
            if (!TextUtils.isEmpty(customerDetail4.getPerson())) {
                AppCompatTextView appCompatTextView = this.mTxtCustomerPerson;
                Intrinsics.checkNotNull(appCompatTextView);
                CustomerDetail customerDetail5 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail5);
                appCompatTextView.setText(customerDetail5.getPerson());
                customerDetail = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail);
                if (customerDetail.getShipAddress() != null) {
                    CustomerDetail customerDetail6 = this.mCustomerDetail;
                    Intrinsics.checkNotNull(customerDetail6);
                    if (!TextUtils.isEmpty(customerDetail6.getShipAddress())) {
                        TintableTextView tintableTextView = this.mTxtCustomerShipAddress;
                        Intrinsics.checkNotNull(tintableTextView);
                        CustomerDetail customerDetail7 = this.mCustomerDetail;
                        Intrinsics.checkNotNull(customerDetail7);
                        tintableTextView.setText(customerDetail7.getShipAddress());
                        customerDetail2 = this.mCustomerDetail;
                        Intrinsics.checkNotNull(customerDetail2);
                        if (customerDetail2.getInvoiceAddress() != null) {
                            CustomerDetail customerDetail8 = this.mCustomerDetail;
                            Intrinsics.checkNotNull(customerDetail8);
                            if (!TextUtils.isEmpty(customerDetail8.getInvoiceAddress())) {
                                TintableTextView tintableTextView2 = this.mTxtCustomerInvoiceAddress;
                                Intrinsics.checkNotNull(tintableTextView2);
                                CustomerDetail customerDetail9 = this.mCustomerDetail;
                                Intrinsics.checkNotNull(customerDetail9);
                                tintableTextView2.setText(customerDetail9.getInvoiceAddress());
                                return;
                            }
                        }
                        TintableTextView tintableTextView3 = this.mTxtCustomerInvoiceAddress;
                        Intrinsics.checkNotNull(tintableTextView3);
                        tintableTextView3.setText(getString(R.string.empty_text));
                        ImageButton imageButton = this.mImgCustomerInvoiceAddress;
                        Intrinsics.checkNotNull(imageButton);
                        imageButton.setVisibility(View.GONE);
                    }
                }
                TintableTextView tintableTextView4 = this.mTxtCustomerShipAddress;
                Intrinsics.checkNotNull(tintableTextView4);
                tintableTextView4.setText(getString(R.string.empty_text));
                ImageButton imageButton2 = this.mImgCustomerShipAddress;
                Intrinsics.checkNotNull(imageButton2);
                imageButton2.setVisibility(View.GONE);
                customerDetail2 = this.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail2);
                if (customerDetail2.getInvoiceAddress() != null) {
                }
                TintableTextView tintableTextView32 = this.mTxtCustomerInvoiceAddress;
                Intrinsics.checkNotNull(tintableTextView32);
                tintableTextView32.setText(getString(R.string.empty_text));
                ImageButton imageButton3 = this.mImgCustomerInvoiceAddress;
                Intrinsics.checkNotNull(imageButton3);
                imageButton3.setVisibility(View.GONE);
            }
        }
        AppCompatTextView appCompatTextView2 = this.mTxtCustomerPerson;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(getString(R.string.empty_text));
        customerDetail = this.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail);
        if (customerDetail.getShipAddress() != null) {
        }
        TintableTextView tintableTextView42 = this.mTxtCustomerShipAddress;
        Intrinsics.checkNotNull(tintableTextView42);
        tintableTextView42.setText(getString(R.string.empty_text));
        ImageButton imageButton22 = this.mImgCustomerShipAddress;
        Intrinsics.checkNotNull(imageButton22);
        imageButton22.setVisibility(View.GONE);
        customerDetail2 = this.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail2);
        if (customerDetail2.getInvoiceAddress() != null) {
        }
        TintableTextView tintableTextView322 = this.mTxtCustomerInvoiceAddress;
        Intrinsics.checkNotNull(tintableTextView322);
        tintableTextView322.setText(getString(R.string.empty_text));
        ImageButton imageButton32 = this.mImgCustomerInvoiceAddress;
        Intrinsics.checkNotNull(imageButton32);
        imageButton32.setVisibility(View.GONE);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable("state:customerDetail", this.mCustomerDetail);
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
