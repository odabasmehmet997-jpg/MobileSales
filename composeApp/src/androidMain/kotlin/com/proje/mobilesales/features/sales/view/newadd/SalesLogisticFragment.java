package com.proje.mobilesales.features.sales.view.newadd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseSalesFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.databinding.FragmentSalesLogisticsBinding;
import com.proje.mobilesales.features.customer.view.communication.ship.CustomerShipAddressActivity;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.repository.SalesNewRepository;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;

public final class SalesLogisticFragment extends BaseSalesFragment {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_CUSTOMER_REF = BaseSalesFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final int SHIP_ACCOUNT_CODE = 999;
    public static final int SHIP_ADDRESS_CODE = 998;
    private FragmentSalesLogisticsBinding _binding;
    private ImageView imgDeleteShipAccount;
    private ImageView imgDeleteShipAddress;
    private LinearLayout lnInvoiceAddress;
    private LinearLayout lnShipAccount;
    private LinearLayout lnShipAddress;
    private LinearLayout lnShipAgent;
    private LinearLayout lnShipType;
    private LinearLayout lnTotalGrossVolume;
    private LinearLayout lnTotalGrossWeight;
    private LinearLayout lnTotalNetVolume;
    private LinearLayout lnTotalNetWeight;
    private final SalesNewRepository repository;
    private TextView txtInvoiceAddress;
    private TextView txtShipAccount;
    private TextView txtShipAddress;
    private TextView txtShipAgent;
    private TextView txtShipType;
    private TextView txtTotalGrossVolume;
    private TextView txtTotalGrossWeight;
    private TextView txtTotalNetVolume;
    private TextView txtTotalNetWeight;
    private final SalesNewViewModel viewModel;

    public SalesLogisticFragment() {
        SalesNewRepository salesNewRepository = new SalesNewRepository();
        this.repository = salesNewRepository;
        this.viewModel = new SalesNewViewModel(salesNewRepository);
    }

    private FragmentSalesLogisticsBinding getBinding() {
        FragmentSalesLogisticsBinding fragmentSalesLogisticsBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSalesLogisticsBinding);
        return fragmentSalesLogisticsBinding;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSalesLogisticsBinding.inflate(inflater, viewGroup, false);
        this.lnShipAccount = getBinding().lnSalesShipAddressCustomer;
        this.txtShipAccount = getBinding().txtSalesShipAddressAccount;
        this.imgDeleteShipAccount = getBinding().imgSalesShipAddressAccountClear;
        this.lnShipAddress = getBinding().lnSalesShipAddress;
        this.txtShipAddress = getBinding().txtSalesShipAddress;
        this.imgDeleteShipAddress = getBinding().imgSalesShipAddressClear;
        this.lnInvoiceAddress = getBinding().lnSalesInvoiceAddress;
        this.txtInvoiceAddress = getBinding().txtSalesInvoiceAddress;
        this.lnShipAgent = getBinding().lnShipAgent;
        this.txtShipAgent = getBinding().txtSalesShipAgent;
        this.lnShipType = getBinding().lnShipType;
        this.txtShipType = getBinding().txtSalesShipType;
        this.lnTotalNetVolume = getBinding().lnTotalNetVolume;
        this.txtTotalNetVolume = getBinding().txtTotalNetVolume;
        this.lnTotalGrossVolume = getBinding().lnTotalGrossVolume;
        this.txtTotalGrossVolume = getBinding().txtTotalGrossVolume;
        this.lnTotalNetWeight = getBinding().lnTotalNetWeight;
        this.txtTotalNetWeight = getBinding().txtTotalNetWeight;
        this.lnTotalGrossWeight = getBinding().lnTotalGrossWeight;
        this.txtTotalGrossWeight = getBinding().txtTotalGrossWeight;
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FicheMode salesFicheMode = getSalesFicheMode();
        FicheMode ficheMode = FicheMode.ANALYSE;
        if (salesFicheMode != ficheMode) {
            SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields);
            if (salesFicheHeaderFields.isShipAccount()) {
                LinearLayout linearLayout = this.lnShipAccount;
                Intrinsics.checkNotNull(linearLayout);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    public  void SalesLogisticFragmentExternalSyntheticLambda0() {
                    }
                    public void onClick(View view2) {
                        SalesLogisticFragment.onViewCreatedlambda0(SalesLogisticFragment.this, view2);
                    }
                });
                ImageView imageView = this.imgDeleteShipAccount;
                Intrinsics.checkNotNull(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    public   void SalesLogisticFragmentExternalSyntheticLambda1() {
                    }
                    public void onClick(View view2) {
                        SalesLogisticFragment.onViewCreatedlambda1(SalesLogisticFragment.this, view2);
                    }
                });
            } else {
                setViewVisibilityGone(this.lnShipAccount);
            }
            SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields2);
            if (salesFicheHeaderFields2.isShipAddress() || this.viewModel.isDifferentShipAddress()) {
                LinearLayout linearLayout2 = this.lnShipAddress;
                Intrinsics.checkNotNull(linearLayout2);
                linearLayout2.setOnClickListener(new View.OnClickListener() {
                    public   void SalesLogisticFragmentExternalSyntheticLambda2() {
                    }
 
                    public void onClick(View view2) {
                        SalesLogisticFragment.onViewCreatedlambda2(SalesLogisticFragment.this, view2);
                    }
                });
                ImageView imageView2 = this.imgDeleteShipAddress;
                Intrinsics.checkNotNull(imageView2);
                imageView2.setOnClickListener(new View.OnClickListener() {
                    public  void SalesLogisticFragmentExternalSyntheticLambda3() {
                    }
 
                    public void onClick(View view2) {
                        SalesLogisticFragment.onViewCreatedlambda3(SalesLogisticFragment.this, view2);
                    }
                });
            } else {
                setViewVisibilityGone(this.lnShipAddress);
            }
        } else if (getSalesFicheMode() == ficheMode) {
            ImageView imageView3 = this.imgDeleteShipAccount;
            Intrinsics.checkNotNull(imageView3);
            imageView3.setVisibility(View.GONE);
            ImageView imageView4 = this.imgDeleteShipAddress;
            Intrinsics.checkNotNull(imageView4);
            imageView4.setVisibility(View.GONE);
        }
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        if (SalesUtils.isSalesTypeDemandOrWhTransfer(sales.getmSalesType())) {
            LinearLayout linearLayout3 = this.lnInvoiceAddress;
            Intrinsics.checkNotNull(linearLayout3);
            linearLayout3.setVisibility(View.GONE);
            setViewVisibilityGone(this.lnShipAccount);
            setViewVisibilityGone(this.lnShipAddress);
            setViewVisibilityGone(this.lnTotalNetVolume);
            setViewVisibilityGone(this.lnTotalGrossVolume);
            setViewVisibilityGone(this.lnTotalGrossWeight);
            setViewVisibilityGone(this.lnTotalNetWeight);
        }
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        try {
            if (!salesNewViewModel.getShowWeightAndVolume(sales2.getmSalesType())) {
                setViewVisibilityGone(this.lnTotalNetVolume);
                setViewVisibilityGone(this.lnTotalGrossVolume);
                setViewVisibilityGone(this.lnTotalGrossWeight);
                setViewVisibilityGone(this.lnTotalNetWeight);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        if (salesActivity.getmCustomerShipRef() > 0) {
            setViewDisabled(this.lnShipAccount);
            setViewDisabled(this.lnShipAddress);
        }
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            setViewVisibilityGone(this.lnTotalGrossWeight);
            setViewVisibilityGone(this.lnTotalGrossVolume);
        }
        FicheMode salesFicheMode2 = getSalesFicheMode();
        LinearLayout linearLayout4 = this.lnShipAgent;
        TextView textView = this.txtShipAgent;
        Sales sales3 = getmSales();
        Intrinsics.checkNotNull(sales3);
        FicheRefProp shipAgent = sales3.getShipAgent();
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        createSingleAlertCursorSales(salesFicheMode2, linearLayout4, textView, shipAgent, salesFicheHeaderFields3.isShipAgent(), R.string.str_shipAgent, R.string.qry_shipAgent, R.string.column_code);
        FicheMode salesFicheMode3 = getSalesFicheMode();
        LinearLayout linearLayout5 = this.lnShipType;
        TextView textView2 = this.txtShipType;
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        FicheRefProp shipType = sales4.getShipType();
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        createSingleAlertCursorSales(salesFicheMode3, linearLayout5, textView2, shipType, salesFicheHeaderFields4.isShipType(), R.string.str_shipType, R.string.qry_shipType, R.string.column_code);
    }

    public static void onViewCreatedlambda0(SalesLogisticFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.viewModel.erpType() == ErpType.NETSIS && !ContextUtils.checkConnectionWithoutMessage()) {
            Toast.makeText(this0.getActivity(), this0.getString(R.string.exp_23_internet_connection_not_found), Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this0.getActivity(), CustomerListActivity.class);
        intent.putExtra(CustomerListActivity.EXTRA_CUSTOMER_SELECT_TYPE, 1);
        String str = CustomerListActivity.EXTRA_SALES_CUSTOMER_CODE;
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        intent.putExtra(str, sales.getClCode());
        this0.startActivityForResult(intent, 999);
    }

    public static void onViewCreatedlambda1(SalesLogisticFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.getShipAccount().reset();
        TextView textView = this0.txtShipAccount;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        ImageView imageView = this0.imgDeleteShipAccount;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(View.GONE);
    }

    public static void onViewCreatedlambda2(SalesLogisticFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = new Intent(this0.getActivity(), CustomerShipAddressActivity.class);
        intent.putExtra(CustomerShipAddressActivity.EXTRA_SALES_SELECT, true);
        intent.putExtra("extra:customerRef", this0.mCustomerRef);
        this0.startActivityForResult(intent, 998);
    }

    public static void onViewCreatedlambda3(SalesLogisticFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.getShipAddress().reset();
        TextView textView = this0.txtShipAddress;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        ImageView imageView = this0.imgDeleteShipAddress;
        Intrinsics.checkNotNull(imageView);
        imageView.setVisibility(View.GONE);
    }
 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        load();
    } 
    public void load() {
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        String ficheStringProp = sales.getShipAccount().toString();
        if (ficheStringProp.length() == 0) {
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            ficheStringProp = sales2.getShipAccount().getCode();
        }
        TextView textView = this.txtShipAccount;
        SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields);
        loadTextView(textView, ficheStringProp, salesFicheHeaderFields.isShipAccount());
        TextView textView2 = this.txtShipAddress;
        Sales sales3 = getmSales();
        Intrinsics.checkNotNull(sales3);
        String ficheStringProp2 = sales3.getShipAddress().toString();
        SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields2);
        loadTextView(textView2, ficheStringProp2, salesFicheHeaderFields2.isShipAddress() || this.viewModel.isDifferentShipAddress());
        TextView textView3 = this.txtInvoiceAddress;
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        loadTextView(textView3, String.valueOf(sales4.getInvoiceAddress()));
        TextView textView4 = this.txtShipType;
        Sales sales5 = getmSales();
        Intrinsics.checkNotNull(sales5);
        String ficheStringProp3 = sales5.getShipType().toString();
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        loadTextView(textView4, ficheStringProp3, salesFicheHeaderFields3.isShipAgent());
        TextView textView5 = this.txtShipAgent;
        Sales sales6 = getmSales();
        Intrinsics.checkNotNull(sales6);
        String ficheStringProp4 = sales6.getShipAgent().toString();
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        loadTextView(textView5, ficheStringProp4, salesFicheHeaderFields4.isShipType());
        TextView textView6 = this.txtTotalNetVolume;
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        Sales sales7 = getmSales();
        Intrinsics.checkNotNull(sales7);
        Double valueOf = Double.valueOf(sales7.getTotalNetVolume());
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.TIGER;
        String format = String.format("%.2f %s", Arrays.copyOf(new Object[]{valueOf, erpType == erpType2 ? getString(R.string.lt_main_unit_code) : ""}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        loadTextView(textView6, format);
        TextView textView7 = this.txtTotalGrossVolume;
        Sales sales8 = getmSales();
        Intrinsics.checkNotNull(sales8);
        String format2 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales8.getTotalGrossVolume()), getString(R.string.lt_main_unit_code)}, 2));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        loadTextView(textView7, format2);
        TextView textView8 = this.txtTotalNetWeight;
        Sales sales9 = getmSales();
        Intrinsics.checkNotNull(sales9);
        String format3 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales9.getTotalNetWeight()), this.viewModel.erpType() == erpType2 ? getString(R.string.kg_main_unit_code) : ""}, 2));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        loadTextView(textView8, format3);
        TextView textView9 = this.txtTotalGrossWeight;
        Sales sales10 = getmSales();
        Intrinsics.checkNotNull(sales10);
        String format4 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales10.getTotalGrossWeight()), getString(R.string.kg_main_unit_code)}, 2));
        Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
        loadTextView(textView9, format4);
        loadDeleteView();
    }
 
    private void loadDeleteView() {
        TextView textView;
        if (getSalesFicheMode() != FicheMode.ANALYSE) {
            TextView textView2 = this.txtShipAccount;
            Intrinsics.checkNotNull(textView2);
            if (!TextUtils.isEmpty(textView2.getText().toString())) {
                SalesActivityNew salesActivity = getSalesActivity();
                Intrinsics.checkNotNull(salesActivity);
                if (salesActivity.getmCustomerShipRef() <= 0) {
                    ImageView imageView = this.imgDeleteShipAccount;
                    Intrinsics.checkNotNull(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    textView = this.txtShipAddress;
                    Intrinsics.checkNotNull(textView);
                    if (!TextUtils.isEmpty(textView.getText().toString())) {
                        SalesActivityNew salesActivity2 = getSalesActivity();
                        Intrinsics.checkNotNull(salesActivity2);
                        if (salesActivity2.getmCustomerShipRef() <= 0) {
                            ImageView imageView2 = this.imgDeleteShipAddress;
                            Intrinsics.checkNotNull(imageView2);
                            imageView2.setVisibility(View.VISIBLE);
                            return;
                        }
                    }
                    ImageView imageView3 = this.imgDeleteShipAddress;
                    Intrinsics.checkNotNull(imageView3);
                    imageView3.setVisibility(View.GONE);
                }
            }
            ImageView imageView4 = this.imgDeleteShipAccount;
            Intrinsics.checkNotNull(imageView4);
            imageView4.setVisibility(View.GONE);
            textView = this.txtShipAddress;
            Intrinsics.checkNotNull(textView);
            if (!TextUtils.isEmpty(textView.getText().toString())) {
            }
            ImageView imageView32 = this.imgDeleteShipAddress;
            Intrinsics.checkNotNull(imageView32);
            imageView32.setVisibility(View.GONE);
        }
    }

    public void update() {
        try {
            if (getmSales() != null) {
                TextView textView = this.txtTotalNetVolume;
                PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                Sales sales = getmSales();
                Intrinsics.checkNotNull(sales);
                Double valueOf = Double.valueOf(sales.getTotalNetVolume());
                ErpType erpType = this.viewModel.erpType();
                ErpType erpType2 = ErpType.TIGER;
                String format = String.format("%.2f %s", Arrays.copyOf(new Object[]{valueOf, erpType == erpType2 ? getString(R.string.lt_main_unit_code) : ""}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                loadTextView(textView, format);
                TextView textView2 = this.txtTotalGrossVolume;
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                String format2 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales2.getTotalGrossVolume()), getString(R.string.lt_main_unit_code)}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                loadTextView(textView2, format2);
                TextView textView3 = this.txtTotalNetWeight;
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                String format3 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales3.getTotalNetWeight()), this.viewModel.erpType() == erpType2 ? getString(R.string.kg_main_unit_code) : ""}, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                loadTextView(textView3, format3);
                TextView textView4 = this.txtTotalGrossWeight;
                Sales sales4 = getmSales();
                Intrinsics.checkNotNull(sales4);
                String format4 = String.format("%.2f %s", Arrays.copyOf(new Object[]{Double.valueOf(sales4.getTotalGrossWeight()), getString(R.string.kg_main_unit_code)}, 2));
                Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
                loadTextView(textView4, format4);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 999 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            int i4 = extras.getInt(IntentExtraName.EXTRA_CUSTOMER_REF);
            Bundle extras2 = intent.getExtras();
            Intrinsics.checkNotNull(extras2);
            String string = extras2.getString(IntentExtraName.EXTRA_CUSTOMER_TITLE);
            Bundle extras3 = intent.getExtras();
            Intrinsics.checkNotNull(extras3);
            String string2 = extras3.getString(IntentExtraName.EXTRA_CUSTOMER_CODE);
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            sales.getShipAccount().setLogicalRef(i4);
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            FicheStringProp.setDefinition(string);
            Sales sales3 = getmSales();
            Intrinsics.checkNotNull(sales3);
            sales3.getShipAccount().setCode(string2);
            Sales sales4 = getmSales();
            Intrinsics.checkNotNull(sales4);
            String ficheStringProp = sales4.getShipAccount().toString();
            if (ficheStringProp.length() == 0) {
                Sales sales5 = getmSales();
                Intrinsics.checkNotNull(sales5);
                ficheStringProp = sales5.getShipAccount().getCode();
            }
            TextView textView = this.txtShipAccount;
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheStringProp);
            Sales sales6 = getmSales();
            Intrinsics.checkNotNull(sales6);
            if (sales6.getShipAccount().getLogicalRef() <= 0) {
                Sales sales7 = getmSales();
                Intrinsics.checkNotNull(sales7);
            }
            ImageView imageView = this.imgDeleteShipAccount;
            Intrinsics.checkNotNull(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
        if (i2 == 998 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            Bundle extras4 = intent.getExtras();
            Intrinsics.checkNotNull(extras4);
            int i5 = extras4.getInt(IntentExtraName.EXTRA_REF);
            Bundle extras5 = intent.getExtras();
            Intrinsics.checkNotNull(extras5);
            String string3 = extras5.getString(IntentExtraName.EXTRA_DEFINITION);
            Bundle extras6 = intent.getExtras();
            Intrinsics.checkNotNull(extras6);
            String string4 = extras6.getString(IntentExtraName.EXTRA_CODE);
            Sales sales8 = getmSales();
            Intrinsics.checkNotNull(sales8);
            sales8.getShipAddress().setLogicalRef(i5);
            Sales sales9 = getmSales();
            Intrinsics.checkNotNull(sales9);
            FicheStringProp.setDefinition(string3);
            Sales sales10 = getmSales();
            Intrinsics.checkNotNull(sales10);
            sales10.getShipAddress().setCode(string4);
            TextView textView2 = this.txtShipAddress;
            Intrinsics.checkNotNull(textView2);
            Sales sales11 = getmSales();
            Intrinsics.checkNotNull(sales11);
            textView2.setText(sales11.getShipAddress().toString());
            Sales sales12 = getmSales();
            Intrinsics.checkNotNull(sales12);
            if (sales12.getShipAddress().getLogicalRef() > 0) {
                ImageView imageView2 = this.imgDeleteShipAddress;
                Intrinsics.checkNotNull(imageView2);
                imageView2.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_CUSTOMER_REF() {
            return SalesLogisticFragment.EXTRA_CUSTOMER_REF;
        }
    }
}
