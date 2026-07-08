package com.proje.mobilesales.features.sales.view.newadd;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSalesFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ISalesDiscountFragment;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.databinding.FragmentSalesPaymentBinding;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.repository.SalesNewRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
 
public final class SalesPaymentFragment extends BaseSalesFragment implements ISalesDiscountFragment {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_CUSTOMER_REF = BaseSalesFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    private static final String TAG = "SalesPaymentFragment";
    private FragmentSalesPaymentBinding _binding;
    private EditText edtSalesCustomerDiscountRatio;
    public EditText[] edtSalesDetailDiscountRatios;
    public EditText[] edtSalesDetailDiscountTotals;
    private boolean hasOrderReference;
    private LinearLayout lnDiscountContainer;
    private View lnDueDate;
    private LinearLayout lnPayPlan;
    private LinearLayout lnProjectCode;
    private LinearLayout lnSalesCustomerDiscount;
    private LinearLayout[] lnSalesDetailDiscounts;
    private LinearLayout lnTradeGroup;
    public AlertDialogBuilder<?> mAlertDialogBuilderTrade;
    private final double maxRatioValue;
    private final double minRatioValue;
    private final SalesNewRepository repository;
    private TextView txtDiscountCardHeader;
    private TextView txtDueDate;
    private TextView txtPayPlan;
    private TextView txtProjectCode;
    private TextView[] txtSalesDetailDiscountCards;
    private TextView txtTradeGroup;
    private final SalesNewViewModel viewModel;

    public SalesPaymentFragment(double minRatioValue) {
        this.minRatioValue = minRatioValue;
        SalesNewRepository salesNewRepository = new SalesNewRepository();
        this.repository = salesNewRepository;
        this.viewModel = new SalesNewViewModel(salesNewRepository);
        this.maxRatioValue = 100.0d;
        this.lnSalesDetailDiscounts = new LinearLayout[0];
        this.edtSalesDetailDiscountRatios = new EditText[5];
        this.edtSalesDetailDiscountTotals = new EditText[5];
        this.txtSalesDetailDiscountCards = new TextView[0];
    }

    private FragmentSalesPaymentBinding getBinding() {
        FragmentSalesPaymentBinding fragmentSalesPaymentBinding = this._binding;
        Intrinsics.checkNotNull(fragmentSalesPaymentBinding);
        return fragmentSalesPaymentBinding;
    }

    public LinearLayout getLnTradeGroup() {
        return this.lnTradeGroup;
    }

    public void setLnTradeGroup(LinearLayout linearLayout) {
        this.lnTradeGroup = linearLayout;
    }

    public TextView getTxtTradeGroup() {
        return this.txtTradeGroup;
    }

    public void setTxtTradeGroup(TextView textView) {
        this.txtTradeGroup = textView;
    }

    public LinearLayout getLnPayPlan() {
        return this.lnPayPlan;
    }

    public void setLnPayPlan(LinearLayout linearLayout) {
        this.lnPayPlan = linearLayout;
    }

    public TextView getTxtPayPlan() {
        return this.txtPayPlan;
    }

    public void setTxtPayPlan(TextView textView) {
        this.txtPayPlan = textView;
    }

    public LinearLayout getLnProjectCode() {
        return this.lnProjectCode;
    }

    public void setLnProjectCode(LinearLayout linearLayout) {
        this.lnProjectCode = linearLayout;
    }

    public TextView getTxtProjectCode() {
        return this.txtProjectCode;
    }

    public void setTxtProjectCode(TextView textView) {
        this.txtProjectCode = textView;
    }

    public TextView getTxtDiscountCardHeader() {
        return this.txtDiscountCardHeader;
    }

    public void setTxtDiscountCardHeader(TextView textView) {
        this.txtDiscountCardHeader = textView;
    }

    public LinearLayout getLnDiscountContainer() {
        return this.lnDiscountContainer;
    }

    public void setLnDiscountContainer(LinearLayout linearLayout) {
        this.lnDiscountContainer = linearLayout;
    }

    public LinearLayout[] getLnSalesDetailDiscounts() {
        return this.lnSalesDetailDiscounts;
    }

    public void setLnSalesDetailDiscounts(LinearLayout[] linearLayoutArr) {
        Intrinsics.checkNotNullParameter(linearLayoutArr, "<set-?>");
        this.lnSalesDetailDiscounts = linearLayoutArr;
    }

    public TextView[] getTxtSalesDetailDiscountCards() {
        return this.txtSalesDetailDiscountCards;
    }

    public void setTxtSalesDetailDiscountCards(TextView[] textViewArr) {
        Intrinsics.checkNotNullParameter(textViewArr, "<set-?>");
        this.txtSalesDetailDiscountCards = textViewArr;
    }

    public LinearLayout getLnSalesCustomerDiscount() {
        return this.lnSalesCustomerDiscount;
    }

    public void setLnSalesCustomerDiscount(LinearLayout linearLayout) {
        this.lnSalesCustomerDiscount = linearLayout;
    }

    public EditText getEdtSalesCustomerDiscountRatio() {
        return this.edtSalesCustomerDiscountRatio;
    }

    public void setEdtSalesCustomerDiscountRatio(EditText editText) {
        this.edtSalesCustomerDiscountRatio = editText;
    }

    public View getLnDueDate() {
        return this.lnDueDate;
    }

    public void setLnDueDate(View view) {
        this.lnDueDate = view;
    }

    public TextView getTxtDueDate() {
        return this.txtDueDate;
    }

    public void setTxtDueDate(TextView textView) {
        this.txtDueDate = textView;
    }
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context contextRequireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderTrade = new AlertDialogBuilder.Impl(contextRequireContext, (BaseInjectableActivity) activity);
    } 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentSalesPaymentBinding.inflate(inflater, viewGroup, false);
        this.lnTradeGroup = getBinding().lnSalesTradeGroup;
        this.txtTradeGroup = getBinding().txtSalesTradeGroup;
        this.lnPayPlan = getBinding().lnSalesPayPlan;
        this.txtPayPlan = getBinding().txtSalesPayPlan;
        this.lnProjectCode = getBinding().lnSalesProjectCode;
        this.txtProjectCode = getBinding().txtSalesProjectCode;
        this.lnDiscountContainer = getBinding().lnDiscountContainer;
        LinearLayout[] linearLayoutArr = new LinearLayout[5];
        this.lnSalesDetailDiscounts = linearLayoutArr;
        this.txtSalesDetailDiscountCards = new TextView[5];
        linearLayoutArr[0] = getBinding().lnSalesDetailDiscount1;
        this.edtSalesDetailDiscountRatios[0] = getBinding().edtSalesDiscountRatio1;
        this.edtSalesDetailDiscountTotals[0] = getBinding().edtSalesDiscountTotal1;
        this.txtSalesDetailDiscountCards[0] = getBinding().txtSalesDiscountCart1;
        this.lnSalesDetailDiscounts[1] = getBinding().lnSalesDiscount2;
        this.edtSalesDetailDiscountRatios[1] = getBinding().edtSalesDiscountRatio2;
        this.edtSalesDetailDiscountTotals[1] = getBinding().edtSalesDiscountTotal2;
        this.txtSalesDetailDiscountCards[1] = getBinding().txtSalesDiscountCart2;
        this.lnSalesDetailDiscounts[2] = getBinding().lnSalesDiscount3;
        this.edtSalesDetailDiscountRatios[2] = getBinding().edtSalesDiscountRatio3;
        this.edtSalesDetailDiscountTotals[2] = getBinding().edtSalesDiscountTotal3;
        this.txtSalesDetailDiscountCards[2] = getBinding().txtSalesDiscountCart3;
        this.lnSalesDetailDiscounts[3] = getBinding().lnSalesDiscount4;
        this.edtSalesDetailDiscountRatios[3] = getBinding().edtSalesDiscountRatio4;
        this.edtSalesDetailDiscountTotals[3] = getBinding().edtSalesDiscountTotal4;
        this.txtSalesDetailDiscountCards[3] = getBinding().txtSalesDiscountCart4;
        this.lnSalesDetailDiscounts[4] = getBinding().lnSalesDiscount5;
        this.edtSalesDetailDiscountRatios[4] = getBinding().edtSalesDiscountRatio5;
        this.edtSalesDetailDiscountTotals[4] = getBinding().edtSalesDiscountTotal5;
        this.txtSalesDetailDiscountCards[4] = getBinding().txtSalesDiscountCart5;
        this.lnSalesCustomerDiscount = getBinding().lnCustomerDiscountRate;
        this.edtSalesCustomerDiscountRatio = getBinding().edtCustomerDiscountRatio;
        this.txtDiscountCardHeader = getBinding().txtDiscountCardHeader;
        this.lnDueDate = getBinding().lnDueDate;
        this.txtDueDate = getBinding().txtDueDate;
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    } 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    } 
    public void onResume() {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4;
        boolean z5;
        int visibility;
        super.onResume();
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            TextView textView = this.txtDiscountCardHeader;
            Intrinsics.checkNotNull(textView);
            textView.setText(R.string.str_condition);
        }
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        boolean zHasOrderReference = sales.hasOrderReference();
        this.hasOrderReference = zHasOrderReference;
        FicheMode salesFicheMode = zHasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
        Intrinsics.checkNotNull(salesFicheMode);
        View view = this.lnTradeGroup;
        TextView textView2 = this.txtTradeGroup;
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        FicheRefProp tradeGroup = sales2.getTradeGroup();
        SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields);
        createSingleAlertCursorSalesTrade(salesFicheMode, view, textView2, tradeGroup, salesFicheHeaderFields.isTradeGroup(), R.string.str_select_trade_group, R.string.str_trade_group_not_found, R.string.qry_trade_group, R.string.column_code, -1, true);
        SalesFicheUserRights salesFicheUserRights = getSalesFicheUserRights();
        Intrinsics.checkNotNull(salesFicheUserRights);
        if (salesFicheUserRights.isEnterPaymentPlan()) {
            FicheMode salesFicheMode2 = getSalesFicheMode();
            Intrinsics.checkNotNullExpressionValue(salesFicheMode2, "getSalesFicheMode(...)");
            View view2 = this.lnPayPlan;
            TextView textView3 = this.txtPayPlan;
            Sales sales3 = getmSales();
            Intrinsics.checkNotNull(sales3);
            FicheDiscountRefProp payPlan = sales3.getPayPlan();
            SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
            Intrinsics.checkNotNull(salesFicheHeaderFields2);
            createSearchableAlertCursorSalesPayPlan(salesFicheMode2, view2, textView3, payPlan, salesFicheHeaderFields2.isPayment(), R.string.str_select_pay_plan, R.string.str_payment_plan_not_found, R.string.qry_pay_plan, R.string.column_name, false, new PriceChangeEvent(true));
        }
        FicheMode salesFicheMode3 = getSalesFicheMode();
        View view3 = this.lnProjectCode;
        TextView textView4 = this.txtProjectCode;
        Sales sales4 = getmSales();
        Intrinsics.checkNotNull(sales4);
        FicheDiscountRefProp projectCode = sales4.getProjectCode();
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        createSingleAlertCursorSales(salesFicheMode3, view3, textView4, projectCode, salesFicheHeaderFields3.isProject(), R.string.str_select_project_code, R.string.str_project_code_not_found, R.string.qry_project_code, R.string.column_name, false, new PriceChangeEvent(true));
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        if (!salesFicheHeaderFields4.isDoSalesDiscount()) {
            setViewVisibilityGone(this.lnDiscountContainer);
        } else {
            Sales sales5 = getmSales();
            Intrinsics.checkNotNull(sales5);
            int discountLength = sales5.getDiscountLength();
            int r12 = 0;
            while (r12 < discountLength) {
                FicheMode salesFicheMode4 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
                EditText editText = this.edtSalesDetailDiscountRatios[r12];
                Sales sales6 = getmSales();
                Intrinsics.checkNotNull(sales6);
                FicheDiscountProp discountRatio = sales6.getDiscountRatio(r12);
                Sales sales7 = getmSales();
                Intrinsics.checkNotNull(sales7);
                FicheDiscountProp discountTotal = sales7.getDiscountTotal(r12);
                EditText editText2 = this.edtSalesDetailDiscountTotals[r12];
                Sales sales8 = getmSales();
                Intrinsics.checkNotNull(sales8);
                FicheDiscountRefProp discountCard = sales8.getDiscountCard(r12);
                TextView textView5 = this.txtSalesDetailDiscountCards[r12];
                double d2 = this.minRatioValue;
                double d3 = this.maxRatioValue;
                SalesFicheHeaderFields salesFicheHeaderFields5 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields5);
                if (salesFicheHeaderFields5.isDoDiscountRatio(r12)) {
                    Sales sales9 = getmSales();
                    Intrinsics.checkNotNull(sales9);
                    z = TextUtils.isEmpty(sales9.getDiscountRatio(r12).getCampaignCode());
                }
                int r18 = discountLength;
                int r19 = r12;
                createDiscountEditTextSetTextChangeListener(salesFicheMode4, null, editText, discountRatio, discountTotal, editText2, discountCard, textView5, d2, d3, z);
                FicheMode salesFicheMode5 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
                EditText editText3 = this.edtSalesDetailDiscountTotals[r19];
                Sales sales10 = getmSales();
                Intrinsics.checkNotNull(sales10);
                FicheDiscountProp discountTotal2 = sales10.getDiscountTotal(r19);
                Sales sales11 = getmSales();
                Intrinsics.checkNotNull(sales11);
                FicheDiscountProp discountRatio2 = sales11.getDiscountRatio(r19);
                EditText editText4 = this.edtSalesDetailDiscountRatios[r19];
                Sales sales12 = getmSales();
                Intrinsics.checkNotNull(sales12);
                FicheDiscountRefProp discountCard2 = sales12.getDiscountCard(r19);
                TextView textView6 = this.txtSalesDetailDiscountCards[r19];
                SalesFicheHeaderFields salesFicheHeaderFields6 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields6);
                if (salesFicheHeaderFields6.isDoDiscountTotal(r19)) {
                    Sales sales13 = getmSales();
                    Intrinsics.checkNotNull(sales13);
                    z2 = TextUtils.isEmpty(sales13.getDiscountTotal(r19).getCampaignCode());
                }
                createDiscountEditTextSetTextChangeListener(salesFicheMode5, null, editText3, discountTotal2, discountRatio2, editText4, discountCard2, textView6, z2);
                ErpType erpType = this.viewModel.erpType();
                ErpType erpType2 = ErpType.NETSIS;
                int r10 = erpType == erpType2 ? R.string.str_select_condition : R.string.str_select_discount_card;
                int r11 = this.viewModel.erpType() == erpType2 ? R.string.str_condition_code_not_found : R.string.str_discount_code_not_found;
                String str = this.viewModel.erpType() == erpType2 ? "0" : "1";
                FicheMode salesFicheMode6 = this.hasOrderReference ? FicheMode.ANALYSE : getSalesFicheMode();
                TextView textView7 = this.txtSalesDetailDiscountCards[r19];
                Sales sales14 = getmSales();
                Intrinsics.checkNotNull(sales14);
                FicheDiscountRefProp discountCard3 = sales14.getDiscountCard(r19);
                Sales sales15 = getmSales();
                Intrinsics.checkNotNull(sales15);
                FicheDiscountProp discountRatio3 = sales15.getDiscountRatio(r19);
                EditText editText5 = this.edtSalesDetailDiscountRatios[r19];
                Sales sales16 = getmSales();
                Intrinsics.checkNotNull(sales16);
                FicheDiscountProp discountTotal3 = sales16.getDiscountTotal(r19);
                EditText editText6 = this.edtSalesDetailDiscountTotals[r19];
                SalesFicheHeaderFields salesFicheHeaderFields7 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields7);
                if (salesFicheHeaderFields7.isDoDiscountCard(r19)) {
                    Sales sales17 = getmSales();
                    Intrinsics.checkNotNull(sales17);
                    z3 = TextUtils.isEmpty(sales17.getDiscountCard(r19).getCampaignCode());
                }
                createSingleAlertCursorDiscountSales(salesFicheMode6, textView7, textView7, discountCard3, discountRatio3, editText5, discountTotal3, editText6, z3, r10, r11, R.string.qry_discount_header, R.string.column_disc, str);
                r12 = r19 + 1;
                discountLength = r18;
            }
        }
        Sales sales18 = getmSales();
        Intrinsics.checkNotNull(sales18);
        if (sales18.getCustomerDiscRatio() == 0.0d) {
            setViewVisibilityGone(this.lnSalesCustomerDiscount);
            z5 = false;
            z4 = true;
        } else {
            ErpType erpType3 = this.viewModel.erpType();
            ErpType erpType4 = ErpType.NETSIS;
            if (erpType3 != erpType4) {
                EditText editText7 = this.edtSalesCustomerDiscountRatio;
                Intrinsics.checkNotNull(editText7);
                z4 = true;
                editText7.setEnabled(true);
                FicheMode salesFicheMode7 = getSalesFicheMode();
                EditText editText8 = this.edtSalesCustomerDiscountRatio;
                Sales sales19 = getmSales();
                createEditTextAddTextChangedListener(salesFicheMode7, null, editText8, sales19 != null ? sales19.getCustomerDisc() : null, this.viewModel.erpType() != erpType4, true, 0.0d, 100.0d, 8192, true);
            } else {
                z4 = true;
            }
            if (this.viewModel.erpType() == erpType4) {
                TextView[] textViewArr = this.txtSalesDetailDiscountCards;
                if (textViewArr.length == 0 && z4) {
                    z5 = false;
                } else {
                    z5 = false;
                    TextView textView8 = textViewArr[0];
                    Intrinsics.checkNotNull(textView8);
                    textView8.setEnabled(false);
                }
                EditText[] editTextArr = this.edtSalesDetailDiscountTotals;
                int visibility2 = 4;
                if (editTextArr.length == 0 ? z4 : z5 ? 1 : 0) {
                } else {
                    EditText editText9 = editTextArr[z5 ? 1 : 0];
                    if (editText9 != null) {
                        editText9.setEnabled(z5);
                    }
                    EditText editText10 = this.edtSalesDetailDiscountTotals[z5 ? 1 : 0];
                    if (editText10 != null) {
                        if (getSalesFicheMode() == FicheMode.ANALYSE) {
                            visibility = 4;
                        } else {
                            EditText editText11 = this.edtSalesDetailDiscountTotals[z5 ? 1 : 0];
                            Intrinsics.checkNotNull(editText11);
                            visibility = editText11.getVisibility();
                        }
                        editText10.setVisibility(visibility);
                    }
                }
                EditText[] editTextArr2 = this.edtSalesDetailDiscountRatios;
                if (!(editTextArr2.length == 0 ? z4 : z5 )) {
                    EditText editText12 = editTextArr2[z5 ? 1 : 0];
                    if (editText12 != null) {
                        editText12.setEnabled(z5);
                    }
                    EditText editText13 = this.edtSalesDetailDiscountRatios[z5 ? 1 : 0];
                    if (editText13 != null) {
                        if (getSalesFicheMode() != FicheMode.ANALYSE) {
                            EditText editText14 = this.edtSalesDetailDiscountRatios[z5 ? 1 : 0];
                            Intrinsics.checkNotNull(editText14);
                            visibility2 = editText14.getVisibility();
                        }
                        editText13.setVisibility(visibility2);
                    }
                }
            } else {
                z5 = false;
            }
        }
        FicheMode salesFicheMode8 = getSalesFicheMode();
        View view4 = this.lnDueDate;
        TextView textView9 = this.txtDueDate;
        Sales sales20 = getmSales();
        Intrinsics.checkNotNull(sales20);
        FicheDateProp dueDate = sales20.getDueDate();
        if (this.viewModel.erpType() != ErpType.NETSIS || this.viewModel.getBaseErp().isHideDueDate().booleanValue()) {
            z4 = z5;
        }
        createDateAlertDialog(salesFicheMode8, view4, textView9, dueDate, z4, false, R.string.str_due_date);
        load();
    }

    @Override // com.proje.mobilesales.core.base.BaseFicheFragment
    public void load() {
        TextView textView = this.txtTradeGroup;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        String string = sales.getTradeGroup().toString();
        SalesFicheHeaderFields salesFicheHeaderFields = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields);
        loadTextView(textView, string, salesFicheHeaderFields.isTradeGroup());
        TextView textView2 = this.txtPayPlan;
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        String string2 = sales2.getPayPlan().toString();
        SalesFicheHeaderFields salesFicheHeaderFields2 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields2);
        loadTextView(textView2, string2, salesFicheHeaderFields2.isPayment());
        TextView textView3 = this.txtProjectCode;
        Sales sales3 = getmSales();
        Intrinsics.checkNotNull(sales3);
        String string3 = sales3.getProjectCode().toString();
        SalesFicheHeaderFields salesFicheHeaderFields3 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields3);
        loadTextView(textView3, string3, salesFicheHeaderFields3.isProject());
        SalesFicheHeaderFields salesFicheHeaderFields4 = getSalesFicheHeaderFields();
        Intrinsics.checkNotNull(salesFicheHeaderFields4);
        if (salesFicheHeaderFields4.isDoSalesDiscount()) {
            Sales sales4 = getmSales();
            Intrinsics.checkNotNull(sales4);
            int discountLength = sales4.getDiscountLength();
            for (int r1 = 0; r1 < discountLength; r1++) {
                EditText editText = this.edtSalesDetailDiscountRatios[r1];
                Sales sales5 = getmSales();
                Intrinsics.checkNotNull(sales5);
                String string4 = sales5.getDiscountRatio(r1).toString();
                SalesFicheHeaderFields salesFicheHeaderFields5 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields5);
                loadTextView(editText, string4, salesFicheHeaderFields5.isDoDiscountRatio(r1));
                EditText editText2 = this.edtSalesDetailDiscountTotals[r1];
                Sales sales6 = getmSales();
                Intrinsics.checkNotNull(sales6);
                String string5 = sales6.getDiscountTotal(r1).toString();
                SalesFicheHeaderFields salesFicheHeaderFields6 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields6);
                loadTextView(editText2, string5, salesFicheHeaderFields6.isDoDiscountTotal(r1));
                TextView textView4 = this.txtSalesDetailDiscountCards[r1];
                Sales sales7 = getmSales();
                Intrinsics.checkNotNull(sales7);
                String string6 = sales7.getDiscountCard(r1).toString();
                SalesFicheHeaderFields salesFicheHeaderFields7 = getSalesFicheHeaderFields();
                Intrinsics.checkNotNull(salesFicheHeaderFields7);
                loadTextView(textView4, string6, salesFicheHeaderFields7.isDoDiscountCard(r1));
            }
        }
        EditText editText3 = this.edtSalesCustomerDiscountRatio;
        Sales sales8 = getmSales();
        Intrinsics.checkNotNull(sales8);
        loadTextView(editText3, StringUtils.convertDoubleToString(Double.valueOf(sales8.getCustomerDiscRatio())));
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            TextView textView5 = this.txtDueDate;
            Sales sales9 = getmSales();
            Intrinsics.checkNotNull(sales9);
            loadTextView(textView5, sales9.getDueDate().toString(), true);
        }
    }

    private void createSearchableAlertCursorSalesPayPlan(FicheMode ficheMode, View view, final TextView textView, final FicheDiscountRefProp ficheDiscountRefProp, boolean z, @StringRes final int r19, @StringRes final int r20, @StringRes final int r21, @StringRes final int r22, final boolean z2, final Object obj, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else {
            if (ficheDiscountRefProp == null) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheDiscountRefProp.toString());
            requireView();
            view.setOnClickListener(new View.OnClickListener() { 
                public void onClick(View view2) {
                    SalesPaymentFragment.createSearchableAlertCursorSalesPayPlanlambda0(SalesPaymentFragment.this, r21, strArr, r20, r19, z2, ficheDiscountRefProp, r22, textView, obj, view2);
                }
            });
        }
    } 
    public static void createSearchableAlertCursorSalesPayPlanlambda0(final SalesPaymentFragment this0, int r15, String[] args, int r17, int r18, boolean z, final FicheDiscountRefProp ficheDiscountRefProp, int r21, final TextView textView, final Object obj, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            Cursor cursor = this0.getCursor(this0.getString(r15), Arrays.copyOf(args, args.length));
            if (cursor != null && cursor.getCount() != 0) {
                if (this0.getSalesFicheMode() == FicheMode.EDIT) {
                    SalesType salesType = this0.getSalesType();
                    Intrinsics.checkNotNull(salesType);
                    if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(salesType)) {
                        Sales sales = this0.getmSales();
                        Intrinsics.checkNotNull(sales);
                        Cursor cursor2 = this0.getCursor(this0.getString(R.string.qry_select_receipt_invoice_transfered, Integer.valueOf(sales.getLogicalRef())));
                        if (cursor2 != null && cursor2.moveToFirst() && cursor2.getInt(0) > 0) {
                            Toast.makeText(this0.getActivity(), this0.getString(R.string.str_receipt_fiche_transferred) + this0.getString(R.string.str_payment_plan_not_change), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                String string = this0.getString(r18);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (z) {
                    string = StringUtils.catStringSpace(this0.getString(r18), this0.getString(R.string.str_select_text));
                }
                String str = string;
                final String[] strArr = {ficheDiscountRefProp.getCode() == null ? "" : ficheDiscountRefProp.getCode(), ""};
                final ArrayList<BaseSearchModel> baseSearchModelsFromCursor = this0.getBaseSearchModelsFromCursor(cursor, r21, ficheDiscountRefProp, Arrays.copyOf(args, args.length));
                new SimpleSearchDialogCompat(this0.getActivity(), str, StringUtils.empty(), null, baseSearchModelsFromCursor, new SearchResultListener<BaseSearchModel>() { 
                    public   void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, BaseSearchModel baseSearchModel, int r3) {
                        try {
                            onSelected2((BaseSearchDialogCompat<?>) baseSearchDialogCompat, baseSearchModel, r3);
                        } catch (CloneNotSupportedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    public void onSelected2(BaseSearchDialogCompat<?> dialog, BaseSearchModel item, int r7) throws CloneNotSupportedException {
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                        Intrinsics.checkNotNullParameter(item, "item");
                        FicheDiscountRefProp ficheDiscountRefPropMo1244clone = ficheDiscountRefProp.clone();
                        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone, "clone(...)");
                        ficheDiscountRefProp.setWhich(this0.findSearchModelPosition(baseSearchModelsFromCursor, item.getLogicalRef()));
                        ficheDiscountRefProp.setLogicalRef(item.getLogicalRef());
                        FicheStringProp.setDefinition(item.getTitle());
                        ficheDiscountRefProp.setCode(item.getCode());
                        TextView textView2 = textView;
                        Intrinsics.checkNotNull(textView2);
                        textView2.setText(ficheDiscountRefProp.getDefinition());
                        strArr[1] = item.getCode();
                        this0.assignHeaderPayPlanToDetails(ficheDiscountRefPropMo1244clone, ficheDiscountRefProp);
                        Object obj2 = obj;
                        if (obj2 != null) {
                            EventBus.getDefault().post(obj2);
                        }
                        dialog.dismiss();
                        SalesActivityNew salesActivity = this0.getSalesActivity();
                        Intrinsics.checkNotNull(salesActivity);
                        salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(strArr);
                    } 
                    public void onCancelled(BaseSearchDialogCompat dialog) {
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                        strArr[1] = "";
                        this0.assignHeaderPayPlanToDetails(ficheDiscountRefProp, null);
                        ficheDiscountRefProp.reset();
                        TextView textView2 = textView;
                        Intrinsics.checkNotNull(textView2);
                        textView2.setText(ficheDiscountRefProp.toString());
                        Object obj2 = obj;
                        if (obj2 != null) {
                            EventBus.getDefault().post(obj2);
                        }
                        dialog.dismiss();
                        SalesActivityNew salesActivity = this0.getSalesActivity();
                        Intrinsics.checkNotNull(salesActivity);
                        salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(strArr);
                    }
                }).show();
                return;
            }
            if (r17 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r17), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(TAG, "createSearchableAlertCursorSalesPayPlan: ", e2);
        }
    }

    
    public void assignHeaderPayPlanToDetails(FicheDiscountRefProp ficheDiscountRefProp, FicheDiscountRefProp ficheDiscountRefProp2) {
        boolean z = ficheDiscountRefProp2 == null;
        try {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            Iterator<SalesDetail> it = mSalesDetailList.iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                if (this.viewModel.getProductPayPlanRef(next.getItemRef()) == 0 && (next.getPayment().getLogicalRef() == ficheDiscountRefProp.getLogicalRef() || Intrinsics.areEqual(next.getPayment().getCode(), ficheDiscountRefProp.getCode()))) {
                    if (z) {
                        next.setPayment(new FicheDiscountRefProp());
                    } else {
                        FicheDiscountRefProp ficheDiscountRefProp3 = new FicheDiscountRefProp();
                        Intrinsics.checkNotNull(ficheDiscountRefProp2);
                        ficheDiscountRefProp3.setCode(ficheDiscountRefProp2.getCode());
                        ficheDiscountRefProp3.setLogicalRef(ficheDiscountRefProp2.getLogicalRef());
                        ficheDiscountRefProp3.setWhich(ficheDiscountRefProp2.getWhich());
                        FicheStringProp.setDefinition(ficheDiscountRefProp2.getDefinition());
                        next.setPayment(ficheDiscountRefProp3);
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "assignHeaderPayPlanToDetails", e2);
        }
    }

    private void createSingleAlertCursorSalesTrade(FicheMode ficheMode, View view, final TextView textView, final FicheRefProp ficheRefProp, boolean z, @StringRes final int r18, @StringRes final int r19, @StringRes final int r20, @StringRes final int r21, @StringRes int r22, final boolean z2, final String... strArr) {
        if (!z) {
            setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            setViewDisabled(textView);
        } else {
            if (ficheRefProp == null) {
                setViewDisabled(textView);
                return;
            }
            loadTextView(textView, ficheRefProp.toString());
            requireView();
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view2) {
                    SalesPaymentFragment.createSingleAlertCursorSalesTradelambda3(SalesPaymentFragment.this, r20, strArr, r19, r18, z2, ficheRefProp, r21, textView, view2);
                }
            });
        }
    } 
    public static   void createSingleAlertCursorSalesTradelambda3(final SalesPaymentFragment this0, int r15, String[] args, int r17, int r18, boolean z, final FicheRefProp ficheRefProp, final int r21, final TextView textView, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilderTrade;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.dismiss();
            final Cursor cursor = this0.getCursor(this0.getString(r15), Arrays.copyOf(args, args.length));
            if (cursor != null && cursor.getCount() != 0) {
                String string = this0.getString(r18);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (z) {
                    string = StringUtils.catStringSpace(this0.getString(r18), this0.getString(R.string.str_select_text));
                }
                final String[] strArr = {ficheRefProp.getDefinition(), ""};
                AlertDialogBuilder<?> alertDialogBuilder2 = this0.mAlertDialogBuilderTrade;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(r21), new DialogInterface.OnClickListener() {  
                    public   void onClick(DialogInterface dialogInterface, int r10) {
                        SalesPaymentFragment.createSingleAlertCursorSalesTradelambda3lambda1(cursor, ficheRefProp, this0, r21, strArr, textView, dialogInterface, r10);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
                    public   void onClick(DialogInterface dialogInterface, int r9) {
                        SalesPaymentFragment.createSingleAlertCursorSalesTradelambda3lambda2(strArr, ficheRefProp, this0, textView, cursor, dialogInterface, r9);
                    }
                }).create().show();
                return;
            }
            if (r17 != -1) {
                Toast.makeText(this0.getActivity(), this0.getString(r17), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e2);
        }
    }
    public static void createSingleAlertCursorSalesTradelambda3lambda1(Cursor cursor, FicheRefProp ficheRefProp, SalesPaymentFragment this0, int r4, String[] valueList, TextView textView, DialogInterface dialog, int r8) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(valueList, "valueList");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor == null) {
            return;
        }   
        if (cursor.moveToPosition(r8)) {
            ficheRefProp.setWhich(r8);
            try {
                int r82 = cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_gattrib)));
                Sales sales = this0.getmSales();
                Intrinsics.checkNotNull(sales);
                sales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(r82));
            } catch (Exception e) {
                Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
                return;
            }
            try {
                ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            } catch (Exception e) {
                Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
                return;
            }
            try {
                String string = cursor.getString(cursor.getColumnIndex(this0.getString(r4)));
                FicheStringProp.setDefinition(string);
                valueList[1] = string;
            } catch (Exception e) {
                Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
                return;
            }
            try {
                Intrinsics.checkNotNull(textView);
                textView.setText(ficheRefProp.getDefinition());
                EventBus.getDefault().post(new PriceChangeEvent(true));
            } catch (Exception e) {
                Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
                return;
            }
        }
        cursor.close();
        dialog.dismiss();
        try {
            SalesActivityNew salesActivity = this0.getSalesActivity();
            Intrinsics.checkNotNull(salesActivity);
            salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(valueList);
        } catch (Exception e) {
            Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
        }
    } 
    public static void createSingleAlertCursorSalesTradelambda3lambda2(String[] valueList, FicheRefProp ficheRefProp, SalesPaymentFragment this0, TextView textView, Cursor cursor, DialogInterface dialog, int r8) {
        Intrinsics.checkNotNullParameter(valueList, "valueList");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        valueList[1] = "";
        ficheRefProp.reset();
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        sales.setNotUseGattribKdv(false);
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        EventBus.getDefault().post(new PriceChangeEvent(true));
        dialog.dismiss();
        try {
            SalesActivityNew salesActivity = this0.getSalesActivity();
            Intrinsics.checkNotNull(salesActivity);
            salesActivity.notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(valueList);
        } catch (Exception e) {
            Log.e(MobileSales.TAG, "createSingleAlertCursorSalesTrade: ", e);
        }
    } 
    public Sales getClonedSales() {
        return getmSales();
    } 
    public EditText[] getEdtSalesDetailDiscountRatios() {
        return this.edtSalesDetailDiscountRatios;
    } 
    public EditText[] getEdtSalesDetailDiscountTotals() {
        return this.edtSalesDetailDiscountTotals;
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

        public String getEXTRA_CUSTOMER_REF() {
            return SalesPaymentFragment.EXTRA_CUSTOMER_REF;
        }
    }
}
