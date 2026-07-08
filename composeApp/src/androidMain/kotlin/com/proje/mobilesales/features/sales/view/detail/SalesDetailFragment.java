package com.proje.mobilesales.features.sales.view.detail;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseFicheFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSalesDetailFragment;
import com.proje.mobilesales.core.base.BaseSalesFragment;
import com.proje.mobilesales.core.enums.CustomerPriceType;
import com.proje.mobilesales.core.enums.DispatchGroupCode;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.CharSequenceGet;
import com.proje.mobilesales.core.interfaces.IFichePropChangeListener;
import com.proje.mobilesales.core.interfaces.ISalesDiscountFragment;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.receiver.ConnectivityReceiver;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.UnitPriceFormatter;
import com.proje.mobilesales.core.widget.DecimalDigitsInputFilter;
import com.proje.mobilesales.databinding.FragmentSalesDetailBinding;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
import com.proje.mobilesales.features.dbmodel.OrderToFicheTotal;
import com.proje.mobilesales.features.dbmodel.Price;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.LastItemPriceSqlParams;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import com.proje.mobilesales.features.sales.utils.SalesDetailWarehouseUtils;
import com.proje.mobilesales.features.sales.view.serialgroup.SalesSerialGroupActivity;
import com.proje.mobilesales.features.sales.view.serilot.SalesSeriLotActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import sun.mail.util.logging.LogManagerProperties;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;
import org.greenrobot.eventbus.EventBus;

public final class SalesDetailFragment extends BaseSalesDetailFragment implements ConnectivityReceiver.ConnectivityReceiverListener, ISalesDiscountFragment {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_REF = BaseSalesFragment.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final int REQUEST_SERIAL_LOT_NO = 201;
    private static final String TAG = "SalesDetailFragment";
    private FragmentSalesDetailBinding _binding;
    private double beforeChangeAmountValue;
    private int centOfUnitPriceDigit;
    private CheckBox chkSalesDetailDoReserve;
    private CheckBox chkSalesDetailIncludeVat;
    private CheckBox chkSalesDetailPromotion;
    private View currPriceDivider;
    private View currencyRateDivider;
    private View currencyTypeDivider;
    private View deliveryDateDivider;
    private View detailVatDivider;
    private View dueDateDivider;
    private View dvdLastCustomerSalesDate;
    private View dvdSecondAmount;
    private View dvdSecondUnit;
    private EditText edtSalesCustomerDiscountRatio;
    private EditText edtSalesDetailAmount;
    private EditText[] edtSalesDetailDiscountRatios;
    private EditText[] edtSalesDetailDiscountTotals;
    private EditText edtSalesDetailExplanation;
    private EditText edtSalesDetailPrice;
    private EditText edtSalesDetailSecondAmount;
    private EditText edtSalesDetailVat;
    private EditText edtSurplusAmount;
    private boolean hasOrderReference;
    private View includeVatDivider;
    private View lastPurchaseDateDivider;
    private View lastPurchaseDivider;
    private LinearLayout lnDiscountContainer;
    private View lnDueDate;
    private View lnLastCustomerSalesDate;
    private View lnLastPurchaseDate;
    private View lnLastPurchasePrice;
    private LinearLayout lnSalesCustomerDiscount;
    private LinearLayout lnSalesDetailAmount;
    private LinearLayout lnSalesDetailCurrPrice;
    private LinearLayout lnSalesDetailDeliveryCode;
    private LinearLayout lnSalesDetailDeliveryDate;
    private LinearLayout[] lnSalesDetailDiscounts;
    private LinearLayout lnSalesDetailDoReserve;
    private LinearLayout lnSalesDetailExplanation;
    private LinearLayout lnSalesDetailIncludeVat;
    private LinearLayout lnSalesDetailName;
    private LinearLayout lnSalesDetailPayment;
    private LinearLayout lnSalesDetailPrice;
    private LinearLayout lnSalesDetailPromotion;
    private LinearLayout lnSalesDetailReference;
    private LinearLayout lnSalesDetailSecondAmount;
    private LinearLayout lnSalesDetailSecondUnit;
    private LinearLayout lnSalesDetailSelectablePrice;
    private LinearLayout lnSalesDetailSelectedCurrRate;
    private LinearLayout lnSalesDetailSelectedCurrType;
    private LinearLayout lnSalesDetailSelectedPriceType;
    private LinearLayout lnSalesDetailSerialLotNo;
    private LinearLayout lnSalesDetailSpeCode;
    private LinearLayout lnSalesDetailUnit;
    private LinearLayout lnSalesDetailVariant;
    private LinearLayout lnSalesDetailVat;
    private LinearLayout lnSalesDetailWareHouse;
    private View lnSurplusAmount;
    private int localCurrType;
    private AlertDialogBuilder<?> mPriceAlertDialogBuilder;
    private AlertDialogBuilder<?> mPriceAlertDialogBuilderOnline;
    private AlertDialogBuilder<?> mPriceAlertDialogBuilderType;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private AlertDialogBuilder<?> mUnitAlertDialogBuilder;
    private UnitPriceFormatter mUnitPriceFormatter;
    private final double maxRatioValue;
    private final double minRatioValue = 0;
    private boolean muhasebeReferans;
    private View paymentDivider;
    private View priceDivider;
    private View priceSelectTypeDivider;
    private View promotionDivider;
    private View referenceCodeDivider;
    private final SalesDetailRepository repository;
    private View reserveDivider;
    private View selectablePriceDivider;
    private View seriLotDivider;
    private Spinner spnLineType;
    private View surplusAmountDivider;
    private TextView txtDiscountCardHeader;
    private TextView txtDueDate;
    private TextView txtLastCustomerSalesDate;
    private TextView txtLastPurchaseDate;
    private TextView txtLastPurchasePrice;
    private TextView txtSalesDetailCurrPrice;
    private TextView txtSalesDetailDeliveryCode;
    private TextView txtSalesDetailDeliveryDate;
    private TextView[] txtSalesDetailDiscountCards;
    private TextView txtSalesDetailName;
    private TextView txtSalesDetailPayment;
    private TextView txtSalesDetailReference;
    private TextView txtSalesDetailSecondUnit;
    private TextView txtSalesDetailSelectablePrice;
    private TextView txtSalesDetailSelectedCurrRate;
    private TextView txtSalesDetailSelectedCurrType;
    private TextView txtSalesDetailSelectedPriceType;
    private TextView txtSalesDetailSerialLotNo;
    private TextView txtSalesDetailSerialLotNoHeader;
    private TextView txtSalesDetailSpeCode;
    private TextView txtSalesDetailUnit;
    private TextView txtSalesDetailVariant;
    private TextView txtSalesDetailWareHouse;
    private TextView txtSalesDetailWareHouseHeader;
    private View variantDivider;
    private final SalesDetailViewModel viewModel;
    private View warehouseDivider;
    public SalesDetailFragment() {
        final SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        repository = salesDetailRepository;
        viewModel = new SalesDetailViewModel(salesDetailRepository);
        edtSalesDetailDiscountRatios = new EditText[5];
        edtSalesDetailDiscountTotals = new EditText[5];
        maxRatioValue = 100.0d;
    }
    private FragmentSalesDetailBinding getBinding() {
        final FragmentSalesDetailBinding fragmentSalesDetailBinding = _binding;
        Intrinsics.checkNotNull(fragmentSalesDetailBinding);
        return fragmentSalesDetailBinding;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return mProgressDialogBuilder;
    }
    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(final Bundle bundle) {
        final Context requireContext = this.requireContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity);
        final Context requireContext2 = this.requireContext();
        final Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mPriceAlertDialogBuilderOnline = new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2);
        final Context requireContext3 = this.requireContext();
        final Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mPriceAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext3, (BaseInjectableActivity) activity3);
        final Context requireContext4 = this.requireContext();
        final Activity activity4 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mUnitAlertDialogBuilder = new AlertDialogBuilder.Impl(requireContext4, (BaseInjectableActivity) activity4);
        final Context requireContext5 = this.requireContext();
        final Activity activity5 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mPriceAlertDialogBuilderType = new AlertDialogBuilder.Impl(requireContext5, (BaseInjectableActivity) activity5);
        super.onCreate(bundle);
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        _binding = FragmentSalesDetailBinding.inflate(this.getLayoutInflater());
        lnSalesDetailName = this.getBinding().lnSalesDetailName;
        txtSalesDetailName = this.getBinding().txtSalesDetailName;
        warehouseDivider = this.getBinding().wareHouseDivider;
        if (this.getWarehouseVisibility()) {
            final LinearLayout linearLayout = this.getBinding().lnSalesDetailWareHouse;
            lnSalesDetailWareHouse = linearLayout;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            final TextView textView = this.getBinding().txtSalesDetailWareHouseHeader;
            txtSalesDetailWareHouseHeader = textView;
            Intrinsics.checkNotNull(textView);
            textView.setText(SalesUtils.isSalesTypeDemand(this.getSalesDetail().getmSalesType()) ? R.string.str_source_ware_house : R.string.str_ware_house);
            txtSalesDetailWareHouse = this.getBinding().txtSalesDetailWareHouse;
            final boolean areEqual = Intrinsics.areEqual(viewModel.getSqlHelper().getLogoParamValue("MUHASEBE_REFERANS"), ExifInterface.LONGITUDE_EAST);
            muhasebeReferans = areEqual;
            if (areEqual) {
                final LinearLayout linearLayout2 = this.getBinding().lnSalesDetailReferenceCode;
                lnSalesDetailReference = linearLayout2;
                Intrinsics.checkNotNull(linearLayout2);
                linearLayout2.setVisibility(View.VISIBLE);
                txtSalesDetailReference = this.getBinding().txtSalesDetailReferenceCode;
            }
        } else {
            final View view = warehouseDivider;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
        }
        lnSalesDetailAmount = this.getBinding().lnSalesDetailAmount;
        edtSalesDetailAmount = this.getBinding().edtSalesDetailAmount;
        lnSalesDetailUnit = this.getBinding().lnSalesDetailUnit;
        txtSalesDetailUnit = this.getBinding().txtSalesDetailUnit;
        lnSalesDetailPrice = this.getBinding().lnSalesDetailPrice;
        edtSalesDetailPrice = this.getBinding().edtSalesDetailPrice;
        lnSalesDetailSelectablePrice = this.getBinding().lnSalesDetailSelectablePrice;
        txtSalesDetailSelectablePrice = this.getBinding().txtSalesDetailSelectablePrice;
        lnSalesDetailSelectedPriceType = this.getBinding().lnSalesDetailPriceSelectType;
        txtSalesDetailSelectedPriceType = this.getBinding().txtSalesDetailPriceSelectType;
        lnSalesDetailSerialLotNo = this.getBinding().lnSalesDetailSerialLotNo;
        txtSalesDetailSerialLotNoHeader = this.getBinding().txtSalesDetailSerialLotNoHeader;
        txtSalesDetailSerialLotNo = this.getBinding().txtSalesDetailSerialLotNo;
        lnSalesDetailPromotion = this.getBinding().lnSalesDetailPromotion;
        chkSalesDetailPromotion = this.getBinding().chkSalesDetailPromotion;
        spnLineType = this.getBinding().spnLineType;
        lnSalesDetailVariant = this.getBinding().lnSalesDetailVariant;
        txtSalesDetailVariant = this.getBinding().txtSalesDetailVariant;
        lnSalesDetailVat = this.getBinding().lnSalesDetailVat;
        edtSalesDetailVat = this.getBinding().edtSalesDetailVat;
        lnSalesDetailIncludeVat = this.getBinding().lnSalesDetailVatInclude;
        chkSalesDetailIncludeVat = this.getBinding().chkSalesDetailVatInclude;
        lnSalesDetailDeliveryDate = this.getBinding().lnSalesDetailDeliveryDate;
        txtSalesDetailDeliveryDate = this.getBinding().txtSalesDetailDeliveryDate;
        lnSalesDetailDoReserve = this.getBinding().lnSalesDetailReserve;
        chkSalesDetailDoReserve = this.getBinding().chkSalesDetailReserve;
        lnSalesDetailPayment = this.getBinding().lnSalesDetailPayment;
        txtSalesDetailPayment = this.getBinding().txtSalesDetailPayment;
        lnSalesDetailSpeCode = this.getBinding().lnSalesDetailSpeCode;
        txtSalesDetailSpeCode = this.getBinding().txtSalesDetailSpeCode;
        lnSalesDetailDeliveryCode = this.getBinding().lnSalesDetailDeliveryCode;
        txtSalesDetailDeliveryCode = this.getBinding().txtSalesDetailDeliveryCode;
        lnSalesDetailExplanation = this.getBinding().lnSalesDetailExplanation;
        edtSalesDetailExplanation = this.getBinding().edtSalesDetailExplanation;
        lnDiscountContainer = this.getBinding().lnDiscountContainer;
        edtSalesDetailDiscountRatios = new EditText[5];
        edtSalesDetailDiscountTotals = new EditText[5];
        final LinearLayout[] linearLayoutArr = new LinearLayout[5];
        lnSalesDetailDiscounts = linearLayoutArr;
        txtSalesDetailDiscountCards = new TextView[5];
        linearLayoutArr[0] = this.getBinding().lnSalesDetailDiscount1;
        edtSalesDetailDiscountRatios[0] = this.getBinding().edtSalesDetailDiscountRatio1;
        edtSalesDetailDiscountTotals[0] = this.getBinding().edtSalesDetailDiscountTotal1;
        TextView[] textViewArr = txtSalesDetailDiscountCards;
        TextView[] textViewArr2 = null;
        if (null == textViewArr) {
            Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
            textViewArr = null;
        }
        textViewArr[0] = this.getBinding().txtSalesDetailDiscountCart1;
        LinearLayout[] linearLayoutArr2 = lnSalesDetailDiscounts;
        if (null == linearLayoutArr2) {
            Intrinsics.throwUninitializedPropertyAccessException("lnSalesDetailDiscounts");
            linearLayoutArr2 = null;
        }
        linearLayoutArr2[1] = this.getBinding().lnSalesDetailDiscount2;
        edtSalesDetailDiscountRatios[1] = this.getBinding().edtSalesDetailDiscountRatio2;
        edtSalesDetailDiscountTotals[1] = this.getBinding().edtSalesDetailDiscountTotal2;
        TextView[] textViewArr3 = txtSalesDetailDiscountCards;
        if (null == textViewArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
            textViewArr3 = null;
        }
        textViewArr3[1] = this.getBinding().txtSalesDetailDiscountCart2;
        LinearLayout[] linearLayoutArr3 = lnSalesDetailDiscounts;
        if (null == linearLayoutArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("lnSalesDetailDiscounts");
            linearLayoutArr3 = null;
        }
        linearLayoutArr3[2] = this.getBinding().lnSalesDetailDiscount3;
        edtSalesDetailDiscountRatios[2] = this.getBinding().edtSalesDetailDiscountRatio3;
        edtSalesDetailDiscountTotals[2] = this.getBinding().edtSalesDetailDiscountTotal3;
        TextView[] textViewArr4 = txtSalesDetailDiscountCards;
        if (null == textViewArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
            textViewArr4 = null;
        }
        textViewArr4[2] = this.getBinding().txtSalesDetailDiscountCart3;
        LinearLayout[] linearLayoutArr4 = lnSalesDetailDiscounts;
        if (null == linearLayoutArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("lnSalesDetailDiscounts");
            linearLayoutArr4 = null;
        }
        linearLayoutArr4[3] = this.getBinding().lnSalesDetailDiscount4;
        edtSalesDetailDiscountRatios[3] = this.getBinding().edtSalesDetailDiscountRatio4;
        edtSalesDetailDiscountTotals[3] = this.getBinding().edtSalesDetailDiscountTotal4;
        TextView[] textViewArr5 = txtSalesDetailDiscountCards;
        if (null == textViewArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
            textViewArr5 = null;
        }
        textViewArr5[3] = this.getBinding().txtSalesDetailDiscountCart4;
        LinearLayout[] linearLayoutArr5 = lnSalesDetailDiscounts;
        if (null == linearLayoutArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("lnSalesDetailDiscounts");
            linearLayoutArr5 = null;
        }
        linearLayoutArr5[4] = this.getBinding().lnSalesDetailDiscount5;
        edtSalesDetailDiscountRatios[4] = this.getBinding().edtSalesDetailDiscountRatio5;
        edtSalesDetailDiscountTotals[4] = this.getBinding().edtSalesDetailDiscountTotal5;
        final TextView[] textViewArr6 = txtSalesDetailDiscountCards;
        if (null == textViewArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
        } else {
            textViewArr2 = textViewArr6;
        }
        textViewArr2[4] = this.getBinding().txtSalesDetailDiscountCart5;
        lnSalesCustomerDiscount = this.getBinding().lnCustomerDiscountRate;
        edtSalesCustomerDiscountRatio = this.getBinding().edtCustomerDiscountRatio;
        lnSalesDetailSelectedCurrType = this.getBinding().lnSalesDetailCurrSelectType;
        txtSalesDetailSelectedCurrType = this.getBinding().txtSalesDetailCurrSelectType;
        lnSalesDetailSelectedCurrRate = this.getBinding().lnSalesDetailCurrRate;
        txtSalesDetailSelectedCurrRate = this.getBinding().txtSalesDetailCurrRate;
        lnSalesDetailCurrPrice = this.getBinding().lnSalesDetailCurrPrice;
        txtSalesDetailCurrPrice = this.getBinding().txtSalesDetailCurrPrice;
        lnSalesDetailSecondAmount = this.getBinding().lnSalesDetailSecondAmount;
        edtSalesDetailSecondAmount = this.getBinding().edtSalesDetailSecondAmount;
        dvdSecondAmount = this.getBinding().dvdAmount2;
        lnSalesDetailSecondUnit = this.getBinding().lnSalesDetailSecondUnit;
        txtSalesDetailSecondUnit = this.getBinding().txtSalesDetailSecondUnit;
        dvdSecondUnit = this.getBinding().dvdSecondUnit;
        txtDiscountCardHeader = this.getBinding().txtDiscountCardHeader;
        txtLastPurchasePrice = this.getBinding().txtLastPurchasePrice;
        txtLastPurchaseDate = this.getBinding().txtLastPurchaseDate;
        lnLastPurchasePrice = this.getBinding().lnLastPurchasePrice;
        lnLastPurchaseDate = this.getBinding().lnLastPurchaseDate;
        lastPurchaseDivider = this.getBinding().lastPurchaseDivider;
        lastPurchaseDateDivider = this.getBinding().lastPurchaseDateDivider;
        currencyTypeDivider = this.getBinding().currencyTypeDivider;
        currencyRateDivider = this.getBinding().currencyRateDivider;
        selectablePriceDivider = this.getBinding().selectablePriceDivider;
        priceDivider = this.getBinding().priceDivider;
        currPriceDivider = this.getBinding().currPriceDivider;
        priceSelectTypeDivider = this.getBinding().priceSelectTypeDivider;
        detailVatDivider = this.getBinding().detailVatDivider;
        includeVatDivider = this.getBinding().includeVatDivider;
        promotionDivider = this.getBinding().promotionDivider;
        reserveDivider = this.getBinding().reserveDivider;
        seriLotDivider = this.getBinding().seriLotDivider;
        paymentDivider = this.getBinding().paymentDivider;
        referenceCodeDivider = this.getBinding().referenceCodeDivider;
        deliveryDateDivider = this.getBinding().deliveryDateDivider;
        variantDivider = this.getBinding().variantDivider;
        txtLastCustomerSalesDate = this.getBinding().txtLastCustomerSalesDate;
        lnLastCustomerSalesDate = this.getBinding().lnLastCustomerSalesDate;
        dvdLastCustomerSalesDate = this.getBinding().lastCustomerSalesDateDivider;
        lnDueDate = this.getBinding().lnDueDate;
        txtDueDate = this.getBinding().txtDueDate;
        dueDateDivider = this.getBinding().dueDateDivider;
        lnSurplusAmount = this.getBinding().lnSurplusAmount;
        edtSurplusAmount = this.getBinding().edtSurplusAmount;
        surplusAmountDivider = this.getBinding().dvdSurplusAmount;
        final FrameLayout root = this.getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
    public void onViewCreated(final View view, final Bundle bundle) {
        final int i2;
        final String[] strArr;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        final ErpType erpType = viewModel.erpType();
        final ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            final TextView textView = txtDiscountCardHeader;
            Intrinsics.checkNotNull(textView);
            textView.setText(R.string.str_condition);
        }
        hasOrderReference = 0 != getSalesDetail().getOrderDetailReference();
        mCustomerRef = this.getSalesDetailActivity().getmCustomerRef();
        final TextView textView2 = txtSalesDetailName;
        Intrinsics.checkNotNull(textView2);
        textView2.setText(this.getSalesDetail().getName());
        if (!hasOrderReference) {
            this.createEditTextAddTextChangedListener(this.getmFicheMode(), edtSalesDetailAmount, this.getSalesDetail().getAmount(), this.getSalesDetail().isDivUnit() ? 8194 : 2);
            i2 = 2;
        } else {
            i2 = 2;
            this.createAmountTextChangedListenerForOrderReference(this.getmFicheMode(), null, edtSalesDetailAmount, this.getSalesDetail().getAmount(), true, true, 0.0d, this.getSalesDetail().getOrderAvailableAmount(), this.getSalesDetail().isDivUnit() ? 8194 : 2);
        }
        this.createEditTextAddTextChangedListener(this.getmFicheMode(), edtSalesDetailSecondAmount, this.getSalesDetail().getSecondAmount(), this.getSalesDetail().isDivUnit() ? 8194 : i2);
        if (this.getWarehouseVisibility()) {
            final FicheMode ficheMode = hasOrderReference ? FicheMode.ANALYSE : this.getmFicheMode();
            final LinearLayout linearLayout = lnSalesDetailWareHouse;
            final TextView textView3 = txtSalesDetailWareHouse;
            final FicheRefProp wareHouse = this.getSalesDetail().getWareHouse();
            final int i3 = this.getSalesFicheUserRights().isDivFactWareHouseControl() ? R.string.qry_warehouse_div_fact : R.string.qry_warehouse;
            if (this.getSalesFicheUserRights().isDivFactWareHouseControl()) {
                strArr = new String[i2];
                strArr[0] = StringUtils.convertIntToString(this.getSalesDetailActivity().getMFactoryRef());
                strArr[1] = StringUtils.convertIntToString(this.getSalesDetailActivity().getMBranchRef());
            } else {
                strArr = new String[0];
            }
            this.createSingleAlertCursorSales(ficheMode, linearLayout, textView3, wareHouse, true, R.string.str_ware_house, i3, R.string.column_code, Arrays.copyOf(strArr, strArr.length));
            if (muhasebeReferans) {
                this.createSingleAlertCursorSales(this.getmFicheMode(), lnSalesDetailReference, txtSalesDetailReference, this.getSalesDetail().getReferenceCode(), true, R.string.str_reference_code, R.string.qry_reference_code, R.string.column_name);
            }
            this.getSalesDetail().getWareHouse().setPropChangeListener(new IFichePropChangeListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda2
                @Override // com.proje.mobilesales.core.interfaces.IFichePropChangeListener
                public void onChange() {
                    onViewCreatedlambda0(SalesDetailFragment.this);
                }
            });
        }
        this.createSingleAlertCursorSalesUnit(this.getmFicheMode(), lnSalesDetailUnit, txtSalesDetailUnit, this.getSalesDetail().getUnit(), this.getSalesDetail(), true, R.string.str_units, R.string.str_product_unit_not_found, viewModel.getSqlHelper().getSalesDetailProductUnitSql(this.getSalesDetail().isProduct()), R.string.column_unit, true, StringUtils.convertIntToString(this.getSalesDetail().getItemRef()));
        this.createSingleAlertCursorSalesSecondUnit(this.getmFicheMode(), lnSalesDetailSecondUnit, txtSalesDetailSecondUnit, this.getSalesDetail().getSecondUnit(), this.getSalesDetail(), true, R.string.str_units, R.string.str_product_unit_not_found, viewModel.getSqlHelper().getSalesDetailProductUnitSql(this.getSalesDetail().isProduct()), R.string.column_unit, true, StringUtils.convertIntToString(this.getSalesDetail().getItemRef()));
        this.initSeriLot();
        this.initEdtPrice();
        this.createPriceSelectTypeSingleChoiceSales(this.getmFicheMode(), lnSalesDetailSelectedPriceType, txtSalesDetailSelectedPriceType, this.getSalesDetail().getSelectedPriceType(), true, R.string.str_selected_price_type, this.getSalesFicheUserRights().getCustomerSelectedPriceType(), true);
        this.initPromotion();
        if (!hasOrderReference) {
            this.createEditTextAddTextChangedListener(this.getmFicheMode(), lnSalesDetailVat, edtSalesDetailVat, this.getSalesDetail().getVat(), this.getSalesFicheUserRights().isChangeVat(), true, 0.0d, 100.0d);
        } else {
            this.createEditTextAddTextChangedListener(this.getmFicheMode(), lnSalesDetailVat, edtSalesDetailVat, this.getSalesDetail().getVat(), this.getSalesFicheUserRights().isChangeVat(), true, this.getSalesDetail().getVat().getDefinitionDouble(), this.getSalesDetail().getVat().getDefinitionDouble());
        }
        final EditText editText = edtSalesDetailVat;
        Intrinsics.checkNotNull(editText);
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(i2)});
        if (!hasOrderReference) {
            this.createCheckboxSetCheckedChangeListener(this.getmFicheMode(), lnSalesDetailIncludeVat, chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat(), this.getSalesFicheDetailFields().isIncludeVat(), this.getSalesDetailActivity().getMVatDefaultChecked(), this.getSalesDetailActivity().getMVatCanBeChange() && !SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.getSalesDetail().getmSalesType()));
        } else {
            this.createCheckboxSetCheckedChangeListener(this.getmFicheMode(), lnSalesDetailIncludeVat, chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat(), this.getSalesFicheDetailFields().isIncludeVat(), this.getSalesDetailActivity().getMVatDefaultChecked(), false);
        }
        final FicheMode ficheMode2 = this.getmFicheMode();
        final LinearLayout linearLayout2 = lnSalesDetailSpeCode;
        final TextView textView4 = txtSalesDetailSpeCode;
        final FicheRefProp speCode = this.getSalesDetail().getSpeCode();
        final boolean isSpeCode = this.getSalesFicheDetailFields().isSpeCode();
        final SalesDetailViewModel salesDetailViewModel = viewModel;
        final SalesType salesType = this.getSalesFicheDetailFields().getSalesType();
        Intrinsics.checkNotNull(salesType);
        this.createSingleAlertCursorSales(ficheMode2, linearLayout2, textView4, speCode, isSpeCode, R.string.str_auxiliary_sprecial_code, R.string.str_specode_not_found, R.string.qry_sales_detail_spe_code, R.string.column_code, BuildConfig.NETSIS_DEMO_PASSWORD, salesDetailViewModel.getSpeCodeTypeDetailFromErp(salesType));
        this.createDateAlertDialog(this.getmFicheMode(), lnSalesDetailDeliveryDate, txtSalesDetailDeliveryDate, this.getSalesDetail().getDeliveryDate(), this.getSalesFicheDetailFields().isDeliveryDate(), R.string.str_delivery_date, false);
        this.initReserve();
        this.initPaymentPlan();
        this.createSingleAlertCursorSales(this.getmFicheMode(), lnSalesDetailDeliveryCode, txtSalesDetailDeliveryCode, this.getSalesDetail().getDeliveryCode(), this.getSalesFicheDetailFields().isDeliveryCode(), R.string.str_delivery_code, R.string.str_delivery_code_not_found, R.string.qry_sales_detail_delivery_code, R.string.column_code);
        this.createEditTextAddTextChangedListener(this.getmFicheMode(), lnSalesDetailExplanation, edtSalesDetailExplanation, this.getSalesDetail().getExplanation(), this.getSalesFicheDetailFields().isExplanation());
        this.initDiscount();
        if (0.0d == getSalesDetail().getCustomerDiscRatio()) {
            this.setViewVisibilityGone(lnSalesCustomerDiscount);
        }
        localCurrType = viewModel.getGetLocalCurrType();
        final int centOfUnitPriceDigit = viewModel.getCentOfUnitPriceDigit();
        this.centOfUnitPriceDigit = centOfUnitPriceDigit;
        if (0 == centOfUnitPriceDigit) {
            this.centOfUnitPriceDigit = 4;
        }
        this.initCurrType();
        if (!this.getSalesFicheDetailFields().isIncludeVat()) {
            this.setViewVisibilityGone(includeVatDivider);
        }
        if (!this.getSalesFicheUserRights().isChangeVat()) {
            this.setViewVisibilityGone(detailVatDivider);
        }
        if (this.getSalesDetail().isFoundByStoredProcedure()) {
            this.setViewDisabled(edtSalesDetailAmount);
            this.setViewDisabled(edtSalesDetailSecondAmount);
            this.setViewDisabled(lnSalesDetailUnit);
            this.setViewDisabled(lnSalesDetailSecondUnit);
        }
        this.createDateAlertDialog(this.getmFicheMode(), lnDueDate, txtDueDate, this.getSalesDetail().getDueDate(), viewModel.erpType() == erpType2, false, R.string.str_due_date);
        this.createEditTextAddTextChangedListener(this.getmFicheMode(), lnSurplusAmount, edtSurplusAmount, this.getSalesDetail().getSurplusAmount(), this.getSalesFicheUserRights().isSurplusAmountEnabled(), this.getSalesDetail().isDivUnit() ? 8194 : i2);
        if (viewModel.erpType() != erpType2) {
            final EditText editText2 = edtSalesCustomerDiscountRatio;
            Intrinsics.checkNotNull(editText2);
            editText2.setEnabled(true);
            this.createEditTextAddTextChangedListener(this.getmFicheMode(), null, edtSalesCustomerDiscountRatio, this.getSalesDetail().getCustomerDisc(), viewModel.erpType() != erpType2, true, 0.0d, 100.0d, 8192, true);
        }
    }
    public static void onViewCreatedlambda0(final SalesDetailFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getSalesDetail().onWarehouseChange();
    }
    private void setVariant() {
        if (viewModel.erpType() == ErpType.NETSIS) {
            final FicheMode ficheMode = this.getmFicheMode();
            final LinearLayout linearLayout = lnSalesDetailVariant;
            final TextView textView = txtSalesDetailVariant;
            final FicheDiscountRefProp variant = this.getSalesDetail().getVariant();
            final boolean isHasVariant = this.getSalesDetail().isHasVariant();
            final String code = this.getSalesDetail().getVariant().getCode();
            final String code2 = this.getSalesDetail().getCode();
            Intrinsics.checkNotNull(code2);
            this.initVariant(ficheMode, linearLayout, textView, variant, isHasVariant, R.string.str_variant, R.string.str_product_variant_not_found, R.string.net_qry_sales_detail_variant, R.string.column_name, true, code, code2);
        } else {
            this.initVariant(this.getmFicheMode(), lnSalesDetailVariant, txtSalesDetailVariant, this.getSalesDetail().getVariant(), this.getSalesDetail().isHasVariant(), R.string.str_variant, R.string.str_product_variant_not_found, R.string.qry_sales_detail_variant, R.string.column_name, true, this.getSalesDetail().getVariant().getCode(), StringUtils.convertIntToString(this.getSalesDetail().getItemRef()));
        }
        if (this.getSalesDetail().isHasVariant()) {
            return;
        }
        this.setViewVisibilityGone(variantDivider);
    }
    public void onGrossTotalResult(final OrderToFicheTotal orderToFicheTotal, final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (null == orderToFicheTotal) {
            return;
        }
        this.getSalesDetail().calculateFiche(this.getSalesDetailActivity().getMIsNotUseGattribKdv());
        final int discountLength = this.getSalesDetail().getDiscountLength();
        int i2 = 0;
        while (i2 < discountLength) {
            final FicheDiscountProp discountTotal = this.getSalesDetail().getDiscountTotal(i2);
            final String guid = discountTotal.getGuid();
            Intrinsics.checkNotNullExpressionValue(guid, "getGuid(...)");
            if (0 < guid.length()) {
                final String definition = discountTotal.getDefinition();
                Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
                if (0 < definition.length() && 0.0d != discountTotal.getDefinitionDouble()) {
                    final double definitionDouble = (discountTotal.getDefinitionDouble() * this.getSalesDetail().getAmount().getDefinitionDouble()) / beforeChangeAmountValue;
                    FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf(definitionDouble)));
                    final EditText editText = edtSalesDetailDiscountTotals[i2];
                    Intrinsics.checkNotNull(editText);
                    editText.setText(StringUtils.convertDoubleToStringDot(Double.valueOf(definitionDouble)));
                }
            }
            final FicheDiscountProp discountTotal2 = i2 == this.getSalesDetail().getDiscountLength() + (-1) ? null : this.getSalesDetail().getDiscountTotal(i2 + 1);
            final String definition2 = discountTotal.getDefinition();
            Intrinsics.checkNotNullExpressionValue(definition2, "getDefinition(...)");
            if (0 < definition2.length()) {
                if (null != discountTotal2) {
                    final String definition3 = discountTotal2.getDefinition();
                    Intrinsics.checkNotNullExpressionValue(definition3, "getDefinition(...)");
                    if (0 != definition3.length() && 0.0d != discountTotal2.getDefinitionDouble()) {
                    }
                }
                final String guid2 = discountTotal.getGuid();
                Intrinsics.checkNotNullExpressionValue(guid2, "getGuid(...)");
                if (0 == guid2.length()) {
                    final double productTotal = (this.getSalesDetail().getProductTotal() / orderToFicheTotal.getGrossTotal()) * orderToFicheTotal.getAddDiscounts();
                    FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf(productTotal)));
                    final EditText editText2 = edtSalesDetailDiscountTotals[i2];
                    Intrinsics.checkNotNull(editText2);
                    editText2.setText(StringUtils.convertDoubleToStringDot(Double.valueOf(productTotal)));
                }
            }
            i2++;
        }
    }
    public Unit getOrderGrossTotal() {
        if (hasOrderReference) {
            final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
            this.getOrderGrossTotalOnline();
        }
        return Unit.INSTANCE;
    }
    private Unit getOrderGrossTotalOnline() {
        viewModel.getOrderGrossTotalOnlineResponse(this.getSalesDetail().getOrderDetailReference(), new SalesOrderGrossTotalListener(this));
        return Unit.INSTANCE;
    } 
    public Sales getClonedSales() {
        final Sales salesWithoutDetails = this.getSalesDetailActivity().getSalesWithoutDetails();
        final ArrayList<SalesDetail> mSalesDetailList = salesWithoutDetails.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        mSalesDetailList.clear();
        try {
            final ArrayList<SalesDetail> mSalesDetailList2 = salesWithoutDetails.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            mSalesDetailList2.add(this.getSalesDetail().m1481clone());
            return salesWithoutDetails;
        } catch (final Exception e2) {
            Log.e(SalesDetailFragment.TAG, "Error on cloning detail", e2);
            return null;
        }
    }
        private class SalesOrderGrossTotalListener(
            WeakReference<SalesDetailFragment> mSalesDetailFragment) implements ResponseListener<List<? extends OrderToFicheTotal>> {
            private final WeakReference<SalesDetailFragment> mSalesDetailFragment;
            private SalesOrderGrossTotalListener(final SalesDetailFragment mSalesDetailFragment) {
                this.mSalesDetailFragment = new WeakReference<>(mSalesDetailFragment);
            }
            public void onResponse(final PrintSlipModel list) {
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        Intrinsics.checkNotNull(list);
                        salesDetailFragment2.onGrossTotalResult(list.get(0), "");
                    }
                }
            }
            public void onFailure(Throwable throwable) {
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onGrossTotalResult(null, errorMessage);
                    }
                }
            }
        }
    public void createAmountTextChangedListenerForOrderReference(final FicheMode ficheMode, final View view, EditText editText, FicheStringProp ficheStringProp, final boolean z, boolean z2, double d2, double d3, final int i2) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        if (!z) {
            if (null != view) {
                this.setViewVisibilityGone(view);
                return;
            } else {
                this.setViewDisabled(editText);
                return;
            }
        }
        if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(editText);
            return;
        }
        if (null == ficheStringProp) {
            this.setViewDisabled(view);
            return;
        }
        if (0 != i2) {
            Intrinsics.checkNotNull(editText);
            editText.setInputType(i2);
        }
        Intrinsics.checkNotNull(editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda12
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(final TextView textView, final int i3, final KeyEvent keyEvent) {
                final boolean createAmountTextChangedListenerForOrderReferencelambda1;
                createAmountTextChangedListenerForOrderReferencelambda1 = createAmountTextChangedListenerForOrderReferencelambda1(FicheStringProp.this, editText, textView, i3, keyEvent);
                return createAmountTextChangedListenerForOrderReferencelambda1;
            }
        });
        editText.addTextChangedListener(new TextWatcher() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentcreateAmountTextChangedListenerForOrderReference2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(final CharSequence charSequence, final int i3, final int i4, final int i5) {
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(final CharSequence charSequence, final int i3, final int i4, final int i5) {
                Intrinsics.checkNotNullParameter(charSequence, "charSequence");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(final Editable editable) {
                final SalesFicheDetailFields salesFicheDetailFields;
                final SalesFicheDetailFields salesFicheDetailFields2;
                Intrinsics.checkNotNullParameter(editable, "editable");
                if (z2) {
                    if (getSalesDetail().isDivUnit() && (Intrinsics.areEqual(editable.toString(), ".0") || Intrinsics.areEqual(editable.toString(), ".00") || Intrinsics.areEqual(editable.toString(), ficheStringProp.getDefinition()))) {
                        return;
                    }
                    if (getSalesDetail().isDivUnit() || !(0 == editable.toString().length() || Intrinsics.areEqual(editable.toString(), StringUtils.convertIntToString((int) ficheStringProp.getDefinitionDouble())))) {
                        final double convertStringToDouble = StringUtils.convertStringToDouble(editable.toString());
                        this.beforeChangeAmountValue = ficheStringProp.getDefinitionDouble();
                        final String obj = getSalesDetail().isDivUnit() ? editable.toString() : String.valueOf((int) convertStringToDouble);
                        if (convertStringToDouble < d2) {
                            Toast.makeText(getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(d2))) + ' ' + getString(R.string.exp_16_value_low), Toast.LENGTH_LONG).show();
                            editText.setText(ficheStringProp.getDefinition());
                            return;
                        }
                        if (0 == ((int) convertStringToDouble)) {
                            editText.setText(ficheStringProp.getDefinition());
                            return;
                        }
                        if (convertStringToDouble > d3) {
                            Toast.makeText(getActivity(), StringUtils.catStringSpace(StringUtils.convertDoubleToString(Double.valueOf(d3))) + ' ' + getString(R.string.exp_15_value_high), Toast.LENGTH_LONG).show();
                            editText.setText(ficheStringProp.getDefinition());
                            return;
                        }
                        FicheStringProp.setDefinition(obj);
                        salesFicheDetailFields2 = getSalesFicheDetailFields();
                        if (salesFicheDetailFields2.isDoSalesDiscount()) {
                            getOrderGrossTotal();
                            return;
                        }
                        return;
                    }
                    return;
                }
                FicheStringProp.setDefinition(getSalesDetail().isDivUnit() ? editable.toString() : String.valueOf(Integer.parseInt(editable.toString())));
                salesFicheDetailFields = getSalesFicheDetailFields();
                if (salesFicheDetailFields.isDoSalesDiscount()) {
                    getOrderGrossTotal();
                }
            }
        });
    }
    public static boolean createAmountTextChangedListenerForOrderReferencelambda1(final FicheStringProp ficheStringProp, final EditText editText, final TextView textView, final int i2, final KeyEvent keyEvent) {
        if (5 != i2 && 6 != i2) {
            return false;
        }
        FicheStringProp.setDefinition(editText.getText().toString());
        return false;
    }
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.getActivityComponent().inject(this);
        mUnitPriceFormatter = UnitPriceFormatter.getInstance(viewModel.getCentOfUnitPriceDigit());
        this.getLastCustomerSalesDateOfItem();
    }
    private void setPrice() {
        if (SalesUtils.isSalesTypeDemandOrWhTransfer(this.getSalesDetail().getmSalesType())) {
            return;
        }
        if (viewModel.getProductOnlinePrice()) {
            if (this.getmFicheMode() != FicheMode.ANALYSE) {
                if (TextUtils.isEmpty(this.getSalesDetail().getSelectedPriceType().getDefinition())) {
                    final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
                    Intrinsics.checkNotNull(progressDialogBuilder);
                    progressDialogBuilder.setMessage(this.getString(R.string.type_get_online_price)).show();
                    this.getPriceOnline();
                    return;
                }
                this.getCustomerSelectedPriceTypeOnline();
                return;
            }
            return;
        }
        final ProgressDialogBuilder<?> progressDialogBuilder2 = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder2);
        progressDialogBuilder2.dismiss();
        this.getPriceOffline();
    }
    private Unit getPriceOffline() {
        final FicheMode ficheMode = this.getmFicheMode();
        final LinearLayout linearLayout = lnSalesDetailSelectablePrice;
        final TextView textView = txtSalesDetailSelectablePrice;
        final FicheDiscountRefProp selectedPrice = this.getSalesDetail().getSelectedPrice();
        final String salesDetailProductPriceSql = viewModel.getSqlHelper().getSalesDetailProductPriceSql(this.getContext(), this.getSalesDetailActivity().getmCustomerRef(), this.getSalesDetail().isProduct(), this.getSalesDetail().getVariant().getCode(), this.getSalesDetailActivity().getMBranchRef());
        Intrinsics.checkNotNullExpressionValue(salesDetailProductPriceSql, "getSalesDetailProductPriceSql(...)");
        final String convertIntToString = StringUtils.convertIntToString(this.getSalesDetail().getItemRef());
        final String convertIntToString2 = StringUtils.convertIntToString(this.getSalesDetailActivity().getmCustomerRef());
        final String valueOf = String.valueOf(this.getSalesDetail().getPType());
        final String convertIntToString3 = StringUtils.convertIntToString(this.getSalesDetail().getUnit().getLogicalRef());
        final String convertIntToString4 = 0 >= getSalesDetail().getPayment().getLogicalRef() ? StringUtils.convertIntToString(this.getSalesDetailActivity().getPaymentRef()) : StringUtils.convertIntToString(this.getSalesDetail().getPayment().getLogicalRef());
        final String tradeGroup = this.getSalesDetailActivity().getTradeGroup();
        Intrinsics.checkNotNull(tradeGroup);
        final String clCardTradingGrp = viewModel.getSqlHelper().getClCardTradingGrp(mCustomerRef);
        Intrinsics.checkNotNullExpressionValue(clCardTradingGrp, "getClCardTradingGrp(...)");
        this.createSingleAlertCursorSalesPrice(ficheMode, linearLayout, textView, selectedPrice, true, R.string.str_price, R.string.str_product_price_not_found, salesDetailProductPriceSql, R.string.column_unit_price, R.string.column_curr_nr, true, convertIntToString, convertIntToString2, valueOf, convertIntToString3, convertIntToString4, tradeGroup, clCardTradingGrp);
        return Unit.INSTANCE;
    }
    private void setVisible() {
        if (!this.isSelectedPriceTypeShouldBeVisible()) {
            this.setViewVisibilityGone(lnSalesDetailSelectedPriceType);
            this.setViewVisibilityGone(priceSelectTypeDivider);
        } else {
            this.setViewVisible(lnSalesDetailSelectedPriceType);
        }
        if (SalesUtils.isSalesTypeOrder(this.getSalesDetail().getmSalesType())) {
            if (!this.getSalesFicheDetailFields().isReserve()) {
                this.setViewVisibilityGone(lnSalesDetailDoReserve);
            }
        } else {
            this.setViewVisibilityGone(lnSalesDetailDoReserve);
        }
        if (viewModel.getIsSalesDetailCurrTypeChange(this.getSalesDetailActivity().getmCustomerRef(), this.getSalesDetail().getItemRef()) && viewModel.erpType() != ErpType.TIGER) {
            this.setViewVisible(lnSalesDetailCurrPrice);
        } else {
            this.setViewVisibilityGone(lnSalesDetailCurrPrice);
        }
        if (SalesUtils.isSalesTypeDemandOrWhTransfer(this.getSalesDetail().getmSalesType())) {
            this.setViewVisibilityGone(lnSalesDetailSelectablePrice);
            this.setViewVisibilityGone(currPriceDivider);
            this.setViewVisibilityGone(referenceCodeDivider);
            this.setViewVisibilityGone(deliveryDateDivider);
            this.setViewVisibilityGone(paymentDivider);
            this.setViewVisibilityGone(selectablePriceDivider);
        } else {
            this.setViewVisible(lnSalesDetailSelectablePrice);
        }
        if (viewModel.erpType() != ErpType.NETSIS || !Intrinsics.areEqual(viewModel.getSqlHelper().getLogoParamValue("OLCUBIRIMITABLODAN"), "D")) {
            final LinearLayout linearLayout = lnSalesDetailSecondAmount;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
            final View view = dvdSecondAmount;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
            final LinearLayout linearLayout2 = lnSalesDetailSecondUnit;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
            final View view2 = dvdSecondUnit;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(View.GONE);
        }
        if (!viewModel.isDueDateByLineEnabled()) {
            final View view3 = lnDueDate;
            Intrinsics.checkNotNull(view3);
            view3.setVisibility(View.GONE);
            final View view4 = dueDateDivider;
            Intrinsics.checkNotNull(view4);
            view4.setVisibility(View.GONE);
        }
        if (this.getSalesFicheUserRights().isSurplusAmountEnabled()) {
            return;
        }
        final View view5 = lnSurplusAmount;
        Intrinsics.checkNotNull(view5);
        view5.setVisibility(View.GONE);
        final View view6 = surplusAmountDivider;
        Intrinsics.checkNotNull(view6);
        view6.setVisibility(View.GONE);
    }
    private void initSeriLot() {
        if (!SalesUtils.isSalesTypeOrderOrDemand(this.getSalesDetail().getmSalesType())) {
            if (0 != getSalesDetail().getTrackType()) {
                this.setViewVisible(lnSalesDetailSerialLotNo);
                this.setViewDisabled(edtSalesDetailAmount);
                this.setViewDisabled(edtSurplusAmount);
                final int trackType = this.getSalesDetail().getTrackType();
                if (1 == trackType) {
                    final TextView textView = txtSalesDetailSerialLotNoHeader;
                    Intrinsics.checkNotNull(textView);
                    textView.setText(this.getString(R.string.str_lot_number));
                } else if (2 == trackType) {
                    final TextView textView2 = txtSalesDetailSerialLotNoHeader;
                    Intrinsics.checkNotNull(textView2);
                    textView2.setText(this.getString(R.string.str_serial_number));
                } else if (3 == trackType) {
                    final TextView textView3 = txtSalesDetailSerialLotNoHeader;
                    Intrinsics.checkNotNull(textView3);
                    textView3.setText(this.getString(R.string.str_serial_group));
                }
                this.setClickSeriLot();
                return;
            }
            this.setViewVisibilityGone(lnSalesDetailSerialLotNo);
            this.setViewVisibilityGone(seriLotDivider);
            if (this.getmFicheMode() != FicheMode.ANALYSE) {
                this.setViewEnabled(edtSalesDetailAmount);
                return;
            }
            return;
        }
        this.setViewVisibilityGone(lnSalesDetailSerialLotNo);
    }
    private boolean isSelectedPriceTypeShouldBeVisible() {
        if (0 == getSalesFicheUserRights().getCustomerSelectedPriceType().length) {
            return false;
        }
        if (viewModel.erpType() != ErpType.NETSIS) {
            return true;
        }
        final String paramValue = viewModel.getSqlHelper().getParamValue(ParameterTypes.ptCanChangeSelectedPrice);
        Intrinsics.checkNotNullExpressionValue(paramValue, "getParamValue(...)");
        return StringUtils.paramValueTrueCheck(paramValue);
    }
    private void setClickSeriLot() {
        if (this.getmFicheMode() != FicheMode.ANALYSE) {
            final LinearLayout linearLayout = lnSalesDetailSerialLotNo;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    setClickSeriLotlambda2(SalesDetailFragment.this, view);
                }
            });
        }
    }
    public static void setClickSeriLotlambda2(final SalesDetailFragment this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final double orderAvailableAmount = 0.0d != this0.getSalesDetail().getConvFact1() ? (this0.getSalesDetail().getOrderAvailableAmount() * this0.getSalesDetail().getConvFact2()) / this0.getSalesDetail().getConvFact1() : 0.0d;
        if (this0.getSalesDetail().getTrackType() != TrackType.SERIAL_GROUP.getType()) {
            final Intent intent = new Intent(this0.getActivity(), SalesSeriLotActivity.class);
            intent.putParcelableArrayListExtra(SalesSeriLotActivity.SERILOT_LIST, this0.getSalesDetail().getSalesSerialLots());
            intent.putExtra(SalesSeriLotActivity.EXTRA_TRACK_TYPE, this0.getSalesDetail().getTrackType());
            intent.putExtra(SalesSeriLotActivity.EXTRA_ITEM_REF, this0.getSalesDetail().getItemRef());
            intent.putExtra(SalesSeriLotActivity.EXTRA_SALES_TYPE, this0.getSalesDetail().getmSalesType());
            intent.putExtra(SalesSeriLotActivity.EXTRA_WAREHOUSE_NR, this0.getWareHouseNr());
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_ORDER_REF, this0.hasOrderReference);
            intent.putExtra(SalesSeriLotActivity.EXTRA_ORDER_AVAILABLE_AMOUNT, orderAvailableAmount);
            intent.putExtra("INVENNO", this0.getWareHouseNr());
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_VARIANT, this0.getSalesDetail().isHasVariant());
            intent.putExtra(SalesSeriLotActivity.EXTRA_VARIANT_REF, this0.getSalesDetail().getVariant().getLogicalRef());
            intent.putExtra(SalesSeriLotActivity.EXTRA_HAS_STOCK_TRACK, this0.getSalesDetail().isLocTracking());
            intent.putExtra(SalesSeriLotActivity.EXTRA_IS_DIV_UNIT, this0.getSalesDetail().isDivUnit());
            final String str = SalesSeriLotActivity.EXTRA_SURPLUS_AMOUNT;
            final FicheStringProp surplusAmount = this0.getSalesDetail().getSurplusAmount();
            Intrinsics.checkNotNull(surplusAmount);
            intent.putExtra(str, surplusAmount.getDefinitionDouble());
            this0.startActivityForResult(intent, 201);
            return;
        }
        final Intent intent2 = new Intent(this0.getActivity(), SalesSerialGroupActivity.class);
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_ITEM_REF, this0.getSalesDetail().getItemRef());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_WAREHOUSE_NR, this0.getWareHouseNr());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_SALES_TYPE, this0.getSalesDetail().getmSalesType());
        intent2.putExtra(SalesSeriLotActivity.EXTRA_HAS_ORDER_REF, this0.hasOrderReference);
        intent2.putExtra(SalesSeriLotActivity.EXTRA_ORDER_AVAILABLE_AMOUNT, orderAvailableAmount);
        intent2.putExtra(SalesSeriLotActivity.EXTRA_WAREHOUSE_NR, this0.getWareHouseNr());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_HAS_VARIANT, this0.getSalesDetail().isHasVariant());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_VARIANT_REF, this0.getSalesDetail().getVariant().getLogicalRef());
        intent2.putExtra(SalesSerialGroupActivity.EXTRA_HAS_STOCK_TRACK, this0.getSalesDetail().isLocTracking());
        intent2.putParcelableArrayListExtra(SalesSeriLotActivity.SERILOT_LIST, this0.getSalesDetail().getSalesSerialLots());
        this0.startActivityForResult(intent2, 201);
    }
    private Unit getLastCustomerSalesDateOfItem() {
        if (SalesUtils.isSalesTypeOrder(this.getSalesDetail().getmSalesType()) && viewModel.erpType() != ErpType.NETSIS) {
            final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.getString(R.string.str_get_last_customer_sales_date_of_item)).show();
            viewModel.getLastCustomerSalesDateOfItem(this.getSalesDetail().getItemRef(), this.getSalesDetailActivity().getmCustomerRef(), new LastCustomerSalesDateOfItemListener(this));
        } else {
            final View view = lnLastCustomerSalesDate;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
            final View view2 = dvdLastCustomerSalesDate;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(View.GONE);
            this.getLastPurchaseInfo();
        }
        return Unit.INSTANCE;
    }
    public void onLastCustomerSalesDateOfItemResult(final List<? extends KeyValuePair> list, final String str) {
        List emptyList;
        final TextView textView = txtLastCustomerSalesDate;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Log.e(SalesDetailFragment.TAG, str);
        }
        if (null != list && !list.isEmpty()) {
            final TextView textView2 = txtLastCustomerSalesDate;
            Intrinsics.checkNotNull(textView2);
            final String value = list.get(0).getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(value, 0);
            if (!split.isEmpty()) {
                final ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (0 != listIterator.previous().length()) {
                        emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            textView2.setText(DateAndTimeUtils.convertReportDateToSimpleDate(((String[]) emptyList.toArray(new String[0]))[0]));
        }
        this.getLastPurchaseInfo();
    }
    private class LastCustomerSalesDateOfItemListener(
            WeakReference<SalesDetailFragment> mSalesDetailFragment) implements ResponseListener<List<? extends KeyValuePair>> {
            private LastCustomerSalesDateOfItemListener(final SalesDetailFragment mSalesDetailFragment) {
                this.mSalesDetailFragment = new WeakReference<>(mSalesDetailFragment);
            }
            public void onResponse(final PrintSlipModel list) {
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onLastCustomerSalesDateOfItemResult(list, "");
                    }
                }
            }
        public void onFailure(Throwable throwable) {
            Log.d("AA", "onFailure: " + throwable.getMessage());
        }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onLastCustomerSalesDateOfItemResult(null, errorMessage);
                    }
                }
            }
        }

    private Unit getLastPurchaseInfo() {
        if (!SalesUtils.isSalesTypeWhTransfer(this.getSalesDetail().getmSalesType()) && viewModel.getShowLastPurchaseInformation() && ContextUtils.checkConnectionWithoutMessage()) {
            if (viewModel.erpType() == ErpType.NETSIS) {
                final View view = lnLastPurchaseDate;
                Intrinsics.checkNotNull(view);
                view.setVisibility(View.GONE);
                final View view2 = lastPurchaseDateDivider;
                Intrinsics.checkNotNull(view2);
                view2.setVisibility(View.GONE);
            }
            final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.setMessage(this.getString(R.string.str_get_last_purchase_info)).show();
            final int itemRef = this.getSalesDetail().getItemRef();
            final String code = this.getSalesDetail().getCode();
            Intrinsics.checkNotNull(code);
            viewModel.getLastPurchaseInfo(new LastPurchaseInfoSqlParams(itemRef, code, Integer.valueOf(this.getSalesDetail().getVariant().getLogicalRef()), TextUtils.isEmpty(this.getSalesDetail().getVariant().getCode()) ? "" : this.getSalesDetail().getVariant().getCode(), Integer.valueOf(this.getSalesDetail().getWareHouse().getLogicalRef())), new LastPurchaseInfoListener(this));
        } else {
            final View view3 = lnLastPurchaseDate;
            Intrinsics.checkNotNull(view3);
            view3.setVisibility(View.GONE);
            final View view4 = lnLastPurchasePrice;
            Intrinsics.checkNotNull(view4);
            view4.setVisibility(View.GONE);
            final View view5 = lastPurchaseDivider;
            Intrinsics.checkNotNull(view5);
            view5.setVisibility(View.GONE);
            final View view6 = lastPurchaseDateDivider;
            Intrinsics.checkNotNull(view6);
            view6.setVisibility(View.GONE);
            this.loadAfterLastPurchaseInfo();
        }
        return Unit.INSTANCE;
    }
    public void onLastPurchaseInfoResult(final List<? extends PurchasePriceInfo> list, final String str) {
        List emptyList;
        final TextView textView = txtLastPurchaseDate;
        Intrinsics.checkNotNull(textView);
        textView.setText("");
        final TextView textView2 = txtLastPurchasePrice;
        Intrinsics.checkNotNull(textView2);
        textView2.setText("");
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Log.e(SalesDetailFragment.TAG, str);
        }
        if (null != list && !list.isEmpty()) {
            if (!TextUtils.isEmpty(list.get(0).getDate())) {
                final TextView textView3 = txtLastPurchaseDate;
                Intrinsics.checkNotNull(textView3);
                final String date = list.get(0).getDate();
                Intrinsics.checkNotNullExpressionValue(date, "getDate(...)");
                final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date, 0);
                if (!split.isEmpty()) {
                    final ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        if (0 != listIterator.previous().length()) {
                            emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList = CollectionsKt.emptyList();
                textView3.setText(DateAndTimeUtils.convertReportDateToSimpleDate(((String[]) emptyList.toArray(new String[0]))[0]));
            }
            final String localCurrencyCode;
            try {
                localCurrencyCode = viewModel.getLocalCurrencyCode();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (0 != list.get(0).getPrCurr()) {
                final String currCode = viewModel.getSqlHelper().getCurrCode(list.get(0).getPrCurr());
                Intrinsics.checkNotNullExpressionValue(currCode, "getCurrCode(...)");
                final TextView textView4 = txtLastPurchasePrice;
                Intrinsics.checkNotNull(textView4);
                final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                final String format = String.format("%s / %s ", Arrays.copyOf(new Object[]{StringUtils.formatDoubleDynamicDigitsWithDot(list.get(0).getPrPrice(), 2), currCode}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                textView4.setText(format);
            } else {
                final TextView textView5 = txtLastPurchasePrice;
                Intrinsics.checkNotNull(textView5);
                final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                final String format2 = String.format("%s / %s ", Arrays.copyOf(new Object[]{StringUtils.formatDoubleDynamicDigitsWithDot(list.get(0).getPrice(), 2), localCurrencyCode}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                textView5.setText(format2);
            }
        }
        this.loadAfterLastPurchaseInfo();
    }

    private void loadAfterLastPurchaseInfo() {
        if (!SalesUtils.isSalesTypeDemandOrWhTransfer(this.getSalesDetail().getmSalesType())) {
            this.setPrice();
        }
        this.setVariant();
        this.setVisible();
        this.load();
    }
    private class LastPurchaseInfoListener(
            WeakReference<SalesDetailFragment> mSalesDetailFragment) implements ResponseListener<List<? extends PurchasePriceInfo>> {
            private LastPurchaseInfoListener(final SalesDetailFragment mSalesDetailFragment) {
                this.mSalesDetailFragment = new WeakReference<>(mSalesDetailFragment);
            }
            public void onResponse(final PrintSlipModel list) {
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onLastPurchaseInfoResult(list, "");
                    }
                }
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onLastPurchaseInfoResult(null, errorMessage);
                    }
                }
            }
        }
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        final String convertIntToString;
        super.onActivityResult(i2, i3, intent);
        if (201 == i2) {
            if (-1 != i3) {
                if (0 != i3) {
                    return;
                }
                this.getSalesDetail().getSalesSerialLots().clear();
                this.getSalesDetail().setSerialLotTransfer(-1);
                this.getSalesDetail().setSerialLotCodeList("");
                this.getSalesDetail();
                FicheStringProp.setDefinition("");
                this.loadTextView(txtSalesDetailSerialLotNo, this.getSalesDetail().getSerialLotCodeList());
                this.loadTextView(edtSalesDetailAmount, this.getSalesDetail().getAmount().toString());
                Toast.makeText(this.getActivity(), this.getString(R.string.str_not_found_serial_number), Toast.LENGTH_SHORT).show();
                return;
            }
            Intrinsics.checkNotNull(intent);
            final Bundle extras = intent.getExtras();
            if (null == extras || !extras.containsKey(SalesSeriLotActivity.SERILOT_LIST)) {
                return;
            }
            final ArrayList<Serilot> parcelableArrayList = extras.getParcelableArrayList(SalesSeriLotActivity.SERILOT_LIST);
            if (null != parcelableArrayList) {
                this.getSalesDetail().setSalesSerialLots(parcelableArrayList);
            }
            final SalesDetail salesDetail = this.getSalesDetail();
            Intrinsics.checkNotNull(parcelableArrayList);
            salesDetail.setSerialLotCodeList(StringUtils.getSerialLotCode(parcelableArrayList, this.getSalesDetail().getTrackType()));
            this.loadTextView(txtSalesDetailSerialLotNo, this.getSalesDetail().getSerialLotCodeList());
            this.getSalesDetail().setSerialLotTransfer(parcelableArrayList.get(0).logicalRef);
            final double calculateSeriLotAmount = CalculateUtils.calculateSeriLotAmount(viewModel.getItemUnits(this.getSalesDetail().getItemRef()), this.getSalesDetail().getSalesSerialLots());
            final double d2 = extras.getDouble(SalesSeriLotActivity.SURPLUS_AMOUNT, 0.0d);
            final FicheStringProp surplusAmount = this.getSalesDetail().getSurplusAmount();
            Intrinsics.checkNotNull(surplusAmount);
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(d2)));
            final EditText editText = edtSurplusAmount;
            if (this.getSalesDetail().isDivUnit()) {
                final FicheStringProp surplusAmount2 = this.getSalesDetail().getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount2);
                convertIntToString = StringUtils.convertDoubleToString(Double.valueOf(surplusAmount2.getDefinitionDouble()));
            } else {
                final FicheStringProp surplusAmount3 = this.getSalesDetail().getSurplusAmount();
                Intrinsics.checkNotNull(surplusAmount3);
                convertIntToString = StringUtils.convertIntToString((int) surplusAmount3.getDefinitionDouble());
            }
            this.setViewText(editText, convertIntToString);
            this.getSalesDetail();
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(calculateSeriLotAmount - d2)));
            this.setViewText(edtSalesDetailAmount, this.getSalesDetail().isDivUnit() ? StringUtils.convertDoubleToString(Double.valueOf(this.getSalesDetail().getAmount().getDefinitionDouble())) : StringUtils.convertIntToString((int) this.getSalesDetail().getAmount().getDefinitionDouble()));
        }
    }
    private Unit getPriceOnline() {
        viewModel.getOnlinePrice(this.getSalesDetail().getItemRef(), mCustomerRef, this.getSalesDetail().getUnit().getLogicalRef(), this.getSalesDetail().getIncludeVat().isSelect(), 0 >= getSalesDetail().getPayment().getLogicalRef() ? this.getSalesDetailActivity().getPaymentRef() : this.getSalesDetail().getPayment().getLogicalRef(), 0 >= getSalesDetailActivity().getProjectRef() ? -1 : this.getSalesDetailActivity().getProjectRef(), this.getSalesDetailActivity().getTradeGroup(), this.getSalesDetail().getPType(), new SalesDetailPriceListener(this), this.getSalesDetail().getVariant().getCode(), this.getSalesDetailActivity().getMBranchRef());
        return Unit.INSTANCE;
    }
    protected void load() {
        final List<MuhRefCodes> tableForMuhRefCodesFromSqlHelper;
        final TextView textView = txtSalesDetailName;
        Intrinsics.checkNotNull(textView);
        textView.setText(this.getSalesDetail().getName());
        final String ficheStringProp = this.getSalesDetail().isDivUnit() ? this.getSalesDetail().getAmount().toString() : StringUtils.convertIntToString((int) StringUtils.convertStringToDouble(this.getSalesDetail().getAmount().toString()));
        Intrinsics.checkNotNull(ficheStringProp);
        String ficheStringProp2 = this.getSalesDetail().isDivUnit() ? this.getSalesDetail().getSecondAmount().toString() : StringUtils.convertIntToString((int) StringUtils.convertStringToDouble(this.getSalesDetail().getSecondAmount().toString()));
        Intrinsics.checkNotNull(ficheStringProp2);
        String valueOf = this.getSalesDetail().isDivUnit() ? String.valueOf(this.getSalesDetail().getSurplusAmount()) : StringUtils.convertIntToString((int) StringUtils.convertStringToDouble(String.valueOf(this.getSalesDetail().getSurplusAmount())));
        this.loadTextView(edtSalesDetailAmount, ficheStringProp);
        this.loadTextView(txtSalesDetailUnit, this.getSalesDetail().getUnit().toString());
        this.loadTextView(edtSalesDetailPrice, this.getSalesDetail().getPrice().toString());
        this.loadCheckView(chkSalesDetailPromotion, this.getSalesDetail().getPromotion().isSelect());
        this.loadTextView(txtSalesDetailVariant, this.getSalesDetail().getVariant().toString());
        this.loadTextView(edtSalesDetailVat, this.getSalesDetail().getVat().toString());
        this.loadTextView(txtSalesDetailSerialLotNo, this.getSalesDetail().getSerialLotCodeList());
        this.loadCheckView(chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat().isSelect(), this.getSalesFicheDetailFields().isIncludeVat());
        this.loadTextView(txtSalesDetailPayment, this.getSalesDetail().getPayment().toString(), this.getSalesFicheDetailFields().isPayment());
        this.loadTextView(txtSalesDetailSpeCode, this.getSalesDetail().getSpeCode().toString(), this.getSalesFicheDetailFields().isSpeCode());
        this.loadTextView(edtSalesDetailExplanation, this.getSalesDetail().getExplanation().toString(), this.getSalesFicheDetailFields().isExplanation());
        this.loadTextView(txtSalesDetailSelectedPriceType, this.getSalesDetail().getSelectedPriceType().toString());
        this.loadTextView(txtSalesDetailSelectablePrice, this.getSalesDetail().getSelectedPrice().toString());
        if (this.getWarehouseVisibility()) {
            this.loadDetailWarehouses();
            if (muhasebeReferans && 0 < getSalesDetail().getReferenceCode().getLogicalRef() && null != (tableForMuhRefCodesFromSqlHelper = this.viewModel.getTableForMuhRefCodesFromSqlHelper(MuhRefCodes.class, "LOGICALREF=?", new String[]{String.valueOf(getSalesDetail().getReferenceCode().getLogicalRef())})) && !tableForMuhRefCodesFromSqlHelper.isEmpty()) {
                this.loadTextView(txtSalesDetailReference, tableForMuhRefCodesFromSqlHelper.get(0).getCode() + " - " + tableForMuhRefCodesFromSqlHelper.get(0).getDefinition());
            }
        }
        if (this.getSalesFicheDetailFields().isDoSalesDiscount()) {
            final int discountLength = this.getSalesDetail().getDiscountLength();
            for (int i2 = 0; i2 < discountLength; i2++) {
                this.loadTextView(edtSalesDetailDiscountRatios[i2], this.getSalesDetail().getDiscountRatio(i2).toString(), this.getSalesFicheDetailFields().isDoDiscountRatio(i2));
                this.loadTextView(edtSalesDetailDiscountTotals[i2], this.getSalesDetail().getDiscountTotal(i2).toString(), this.getSalesFicheDetailFields().isDoDiscountTotal(i2));
                TextView[] textViewArr = txtSalesDetailDiscountCards;
                if (null == textViewArr) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
                    textViewArr = null;
                }
                this.loadTextView(textViewArr[i2], this.getSalesDetail().getDiscountCard(i2).toString(), this.getSalesFicheDetailFields().isDoDiscountCard(i2));
            }
        }
        final SalesType salesType = this.getSalesFicheDetailFields().getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrderOrDemand(salesType)) {
            this.loadCheckView(chkSalesDetailDoReserve, this.getSalesDetail().getReserve().isSelect(), true);
            this.loadTextView(txtSalesDetailDeliveryDate, this.getSalesDetail().getDeliveryDate().toString(), this.getSalesFicheDetailFields().isDeliveryDate());
        }
        this.loadTextView(txtSalesDetailDeliveryCode, this.getSalesDetail().getDeliveryCode().toString(), this.getSalesFicheDetailFields().isDeliveryCode());
        this.loadTextView(edtSalesCustomerDiscountRatio, StringUtils.convertDoubleToString(Double.valueOf(this.getSalesDetail().getCustomerDiscRatio())));
        this.loadTextView(txtSalesDetailSelectedCurrType, this.getSalesDetail().getCurrType().toString());
        this.loadCurrPrice();
        final EditText editText = edtSalesDetailSecondAmount;
        if (0.0d == getSalesDetail().getSecondAmount().getDefinitionDouble()) {
            ficheStringProp2 = "";
        }
        this.loadTextView(editText, ficheStringProp2);
        this.loadTextView(txtSalesDetailSecondUnit, this.getSalesDetail().getSecondUnit().toString());
        this.loadTextView(txtDueDate, String.valueOf(this.getSalesDetail().getDueDate()));
        final EditText editText2 = edtSurplusAmount;
        final FicheStringProp surplusAmount = this.getSalesDetail().getSurplusAmount();
        Intrinsics.checkNotNull(surplusAmount);
        if (0.0d == surplusAmount.getDefinitionDouble()) {
            valueOf = "";
        }
        this.loadTextView(editText2, valueOf);
        this.loadTextView(txtSalesDetailSelectedCurrRate, this.getCurrRate(this.getSalesDetail().getCurrType()));
    }

    private void loadDetailWarehouses() {
        if (this.isWarehouseLogicalRefInvalid()) {
            this.loadWarehouseFromDatabase();
            return;
        }
        if (this.isWarehouseNameEmpty()) {
            if (this.getmFicheMode() == FicheMode.ANALYSE) {
                if (SalesUtils.isSalesTypeOrderOrDemand(this.getSalesDetail().getmSalesType())) {
                    this.loadTextView(txtSalesDetailWareHouse, SalesDetailWarehouseUtils.getDetailWarehouseName("OrderDetail", this.getSalesDetail()));
                    return;
                } else {
                    this.loadTextView(txtSalesDetailWareHouse, SalesDetailWarehouseUtils.getDetailWarehouseName("InvoiceDetail", this.getSalesDetail()));
                    return;
                }
            }
            this.loadWarehouseNameFromLogicalRef();
            return;
        }
        this.loadTextView(txtSalesDetailWareHouse, this.getSalesDetail().getWareHouse().toString());
    }

    private boolean isWarehouseLogicalRefInvalid() {
        return 0 > getSalesDetail().getWareHouse().getLogicalRef();
    }

    private boolean isWarehouseNameEmpty() {
        final String ficheStringProp = this.getSalesDetail().getWareHouse().toString();
        Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
        return 0 == ficheStringProp.length();
    }

    private void loadWarehouseFromDatabase() {
        final List<WareHouse> tableForWarehouseFromSqlHelper = viewModel.getTableForWarehouseFromSqlHelper(WareHouse.class, "LOGICALREF=?", new String[]{String.valueOf(this.getWareHouseNr())});
        if (null != tableForWarehouseFromSqlHelper && !tableForWarehouseFromSqlHelper.isEmpty()) {
            this.updateSalesDetailWarehouse(tableForWarehouseFromSqlHelper.get(0));
        } else {
            this.loadTextView(txtSalesDetailWareHouse, this.getSalesDetail().getWareHouse().toString());
        }
    }

    private void loadWarehouseNameFromLogicalRef() {
        final List<WareHouse> tableForWarehouseFromSqlHelper = viewModel.getTableForWarehouseFromSqlHelper(WareHouse.class, "NR=?", new String[]{String.valueOf(this.getSalesDetail().getWareHouse().getLogicalRef())});
        if (null == tableForWarehouseFromSqlHelper || tableForWarehouseFromSqlHelper.isEmpty()) {
            return;
        }
        this.loadTextView(txtSalesDetailWareHouse, tableForWarehouseFromSqlHelper.get(0).getAmbar());
    }

    private void updateSalesDetailWarehouse(final WareHouse wareHouse) {
        this.loadTextView(txtSalesDetailWareHouse, wareHouse.getAmbar());
        this.getSalesDetail().getWareHouse().setLogicalRef(wareHouse.getLogicalRef());
    }

    public void checkDiscount() {
        final int discountLength = this.getSalesDetail().getDiscountLength();
        for (int i2 = 0; i2 < discountLength; i2++) {
            this.setDiscountEditable(edtSalesDetailDiscountRatios[i2], this.getSalesDetail().getDiscountRatio(i2), this.getSalesFicheDetailFields().isDoDiscountRatio(i2));
            this.setDiscountEditable(edtSalesDetailDiscountTotals[i2], this.getSalesDetail().getDiscountTotal(i2), this.getSalesFicheDetailFields().isDoDiscountTotal(i2));
            TextView[] textViewArr = txtSalesDetailDiscountCards;
            if (null == textViewArr) {
                Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
                textViewArr = null;
            }
            this.setDiscountEditable(textViewArr[i2], this.getSalesDetail().getDiscountCard(i2), this.getSalesFicheDetailFields().isDoDiscountCard(i2));
        }
    }

    private void setDiscountEditable(final TextView textView, final FicheStringProp ficheStringProp, final boolean z) {
        if (z) {
            Intrinsics.checkNotNull(textView);
            FicheStringProp.setDefinition(textView.getText().toString());
        }
    }

    private void initPromotion() {
        if (this.getProductSizeHighOne()) {
            if (hasOrderReference || this.getSalesDetail().hasCampaign()) {
                this.setViewDisabled(chkSalesDetailPromotion);
                this.setViewDisabled(spnLineType);
                if (this.getSalesDetail().hasCampaign()) {
                    this.setViewDisabled(edtSalesDetailAmount);
                }
                this.setPriceEntriesEnabled(!this.getSalesDetail().getPromotion().isSelect() || viewModel.isPromotionItemPriceEnabled());
            } else {
                this.createCheckboxSetCheckedChangeListener(this.getmFicheMode(), lnSalesDetailPromotion, chkSalesDetailPromotion, this.getSalesDetail().getPromotion(), this.getSalesFicheUserRights().isDoPromotion(), false, true, new BaseFicheFragment.OnCheckBoxClickInterface() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda9
                    @Override // com.proje.mobilesales.core.base.BaseFicheFragment.OnCheckBoxClickInterface
                    public void onCheckBoxClicked(final boolean z) {
                        initPromotionlambda5(SalesDetailFragment.this, z);
                    }
                });
            }
            this.initGlobalLineType();
            return;
        }
        this.setViewVisibilityGone(lnSalesDetailPromotion);
        this.setViewVisibilityGone(promotionDivider);
    }

    
    public static void initPromotionlambda5(final SalesDetailFragment this0, final boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final boolean z2 = !this0.getSalesDetail().getPromotion().isSelect() || this0.viewModel.isPromotionItemPriceEnabled();
        if (!z2) {
            this0.getSalesDetail().resetPromotionPrice();
            final TextView textView = this0.txtSalesDetailSelectablePrice;
            Intrinsics.checkNotNull(textView);
            textView.setText(this0.getSalesDetail().getSelectedPrice().getDefinition());
            this0.loadTextView(this0.edtSalesDetailPrice, "");
        }
        this0.setPriceEntriesEnabled(z2);
    }

    private void setPriceEntriesEnabled(final boolean z) {
        final EditText editText = edtSalesDetailPrice;
        Intrinsics.checkNotNull(editText);
        editText.setEnabled(z);
        final LinearLayout linearLayout = lnSalesDetailSelectablePrice;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setEnabled(z);
        final TextView textView = txtSalesDetailSelectablePrice;
        Intrinsics.checkNotNull(textView);
        textView.setEnabled(z);
    }

    private void initGlobalLineType() {
        final Context context = this.getContext();
        Intrinsics.checkNotNull(context);
        final Context context2 = this.getContext();
        Intrinsics.checkNotNull(context2);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.layout_product_spinner_item, context2.getResources().getStringArray(R.array.array_global_line_type));
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        final Spinner spinner = spnLineType;
        Intrinsics.checkNotNull(spinner);
        spinner.setAdapter(arrayAdapter);
        final Spinner spinner2 = spnLineType;
        Intrinsics.checkNotNull(spinner2);
        spinner2.setSelection(this.getSalesDetail().getGlobalLineType());
        if (this.getmFicheMode() == FicheMode.ANALYSE || hasOrderReference) {
            return;
        }
        final Spinner spinner3 = spnLineType;
        Intrinsics.checkNotNull(spinner3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentinitGlobalLineType1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i2, final long j2) {
                Intrinsics.checkNotNullParameter(view, "view");
                final SalesDetail salesDetail = getSalesDetail();
                final Context context3 = getContext();
                Intrinsics.checkNotNull(context3);
                salesDetail.setGlobalLineType(context3.getResources().getIntArray(R.array.array_global_line_values)[i2]);
            }
        });
    }

    private void initEdtPrice() {
        boolean z = true;
        if (this.getSalesFicheDetailFields().getSalesType() != SalesType.DISPATCH || this.getSalesFicheDetailFields().getSalesType() != SalesType.RETURN_DISPATCH) {
            final EditText editText = edtSalesDetailPrice;
            Intrinsics.checkNotNull(editText);
            editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(viewModel.getCentOfUnitPriceDigit())});
            final FicheMode ficheMode = this.getmFicheMode();
            final LinearLayout linearLayout = lnSalesDetailPrice;
            final EditText editText2 = edtSalesDetailPrice;
            final FicheRefProp price = this.getSalesDetail().getPrice();
            final BaseErp<?> baseErp = viewModel.getBaseErp();
            final boolean isEnterPrice = this.getSalesFicheUserRights().isEnterPrice();
            final boolean isChangePrice = this.getSalesFicheUserRights().isChangePrice();
            final String definition = this.getSalesDetail().getSelectedPrice().getDefinition();
            this.createPriceEditTextAddTextChangedListener(ficheMode, linearLayout, editText2, price, baseErp.isPriceCanBeEnter(isEnterPrice, isChangePrice, null == definition || 0 == definition.length()));
        }
        final BaseErp<?> baseErp2 = viewModel.getBaseErp();
        final boolean isEnterPrice2 = this.getSalesFicheUserRights().isEnterPrice();
        final boolean isChangePrice2 = this.getSalesFicheUserRights().isChangePrice();
        final String definition2 = this.getSalesDetail().getSelectedPrice().getDefinition();
        if (null != definition2 && 0 != definition2.length()) {
            z = false;
        }
        if (baseErp2.isPriceCanBeEnter(isEnterPrice2, isChangePrice2, z)) {
            return;
        }
        this.setViewVisibilityGone(priceDivider);
    }

    private void initReserve() {
        if (!this.getSalesFicheUserRights().isNotChangeReserve()) {
            this.createCheckboxSetCheckedChangeListener(this.getmFicheMode(), lnSalesDetailDoReserve, chkSalesDetailDoReserve, this.getSalesDetail().getReserve(), this.getSalesFicheDetailFields().isReserve(), false, this.isDoReserveEnabled());
            Log.d("AA", "initReserve: acildi");
        } else {
            Log.d("AA", "initReserve: kapatildi");
            this.setViewDisabled(chkSalesDetailDoReserve);
        }
        if (this.getSalesFicheDetailFields().isReserve()) {
            return;
        }
        this.setViewVisibilityGone(reserveDivider);
    }

    private boolean isDoReserveEnabled() {
        return viewModel.erpType() != ErpType.NETSIS || Intrinsics.areEqual(viewModel.getSqlHelper().getLogoParamValue("STOK_AYIR"), ExifInterface.LONGITUDE_EAST);
    }

    private void initPaymentPlan() {
        if (this.getSalesFicheUserRights().isEnterPaymentPlan()) {
            this.createSingleAlertCursorSales(hasOrderReference ? FicheMode.ANALYSE : this.getmFicheMode(), lnSalesDetailPayment, txtSalesDetailPayment, this.getSalesDetail().getPayment(), this.getSalesFicheDetailFields().isPayment(), R.string.str_payment_type, R.string.str_payment_plan_not_found, R.string.qry_pay_plan, R.string.column_name, new PriceChangeEvent(true));
        } else {
            this.setViewVisibilityGone(lnSalesDetailPayment);
            this.setViewVisibilityGone(paymentDivider);
        }
    }

    private void initDiscount() {
        if (!this.getSalesFicheDetailFields().isDoSalesDiscount()) {
            this.setViewVisibilityGone(lnDiscountContainer);
            return;
        }
        int discountLength = this.getSalesDetail().getDiscountLength();
        int i2 = 0;
        while (i2 < discountLength) {
            final FicheMode ficheMode = hasOrderReference ? FicheMode.ANALYSE : this.getmFicheMode();
            final EditText editText = edtSalesDetailDiscountRatios[i2];
            final FicheDiscountProp discountRatio = this.getSalesDetail().getDiscountRatio(i2);
            final FicheDiscountProp discountTotal = this.getSalesDetail().getDiscountTotal(i2);
            final EditText editText2 = edtSalesDetailDiscountTotals[i2];
            final FicheDiscountRefProp discountCard = this.getSalesDetail().getDiscountCard(i2);
            TextView[] textViewArr = txtSalesDetailDiscountCards;
            TextView[] textViewArr2 = null;
            if (null == textViewArr) {
                Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
                textViewArr = null;
            }
            final int i3 = discountLength;
            final int i4 = i2;
            this.createDiscountEditTextSetTextChangeListener(ficheMode, null, editText, discountRatio, discountTotal, editText2, discountCard, textViewArr[i2], minRatioValue, maxRatioValue, this.getSalesFicheDetailFields().isDoDiscountRatio(i2) && TextUtils.isEmpty(this.getSalesDetail().getDiscountRatio(i2).getCampaignCode()));
            final FicheMode ficheMode2 = hasOrderReference ? FicheMode.ANALYSE : this.getmFicheMode();
            final EditText editText3 = edtSalesDetailDiscountTotals[i4];
            final FicheDiscountProp discountTotal2 = this.getSalesDetail().getDiscountTotal(i4);
            final FicheDiscountProp discountRatio2 = this.getSalesDetail().getDiscountRatio(i4);
            final EditText editText4 = edtSalesDetailDiscountRatios[i4];
            final FicheDiscountRefProp discountCard2 = this.getSalesDetail().getDiscountCard(i4);
            TextView[] textViewArr3 = txtSalesDetailDiscountCards;
            if (null == textViewArr3) {
                Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
                textViewArr3 = null;
            }
            this.createDiscountEditTextSetTextChangeListener(ficheMode2, null, editText3, discountTotal2, discountRatio2, editText4, discountCard2, textViewArr3[i4], this.getSalesFicheDetailFields().isDoDiscountTotal(i4) && TextUtils.isEmpty(this.getSalesDetail().getDiscountTotal(i4).getCampaignCode()));
            final ErpType erpType = viewModel.erpType();
            final ErpType erpType2 = ErpType.NETSIS;
            final int i5 = erpType == erpType2 ? R.string.str_select_condition : R.string.str_select_discount_card;
            final int i6 = viewModel.erpType() == erpType2 ? R.string.str_condition_code_not_found : R.string.str_discount_code_not_found;
            final FicheMode ficheMode3 = (hasOrderReference || (viewModel.erpType() == erpType2 && TextUtils.isEmpty(this.getSalesDetailActivity().getMHeaderFirstDiscountCardCode()) && TextUtils.isEmpty(this.getSalesDetail().getDiscountCard(0).getCode()))) ? FicheMode.ANALYSE : this.getmFicheMode();
            TextView[] textViewArr4 = txtSalesDetailDiscountCards;
            if (null == textViewArr4) {
                Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
                textViewArr4 = null;
            }
            final TextView textView = textViewArr4[i4];
            final TextView[] textViewArr5 = txtSalesDetailDiscountCards;
            if (null == textViewArr5) {
                Intrinsics.throwUninitializedPropertyAccessException("txtSalesDetailDiscountCards");
            } else {
                textViewArr2 = textViewArr5;
            }
            this.createSingleAlertCursorDiscountSales(ficheMode3, textView, textViewArr2[i4], this.getSalesDetail().getDiscountCard(i4), this.getSalesDetail().getDiscountRatio(i4), edtSalesDetailDiscountRatios[i4], this.getSalesDetail().getDiscountTotal(i4), edtSalesDetailDiscountTotals[i4], this.getSalesFicheDetailFields().isDoDiscountCard(i4) && TextUtils.isEmpty(this.getSalesDetail().getDiscountCard(i4).getCampaignCode()), i5, i6, R.string.qry_discount_header, R.string.column_disc, "0");
            i2 = i4 + 1;
            discountLength = i3;
        }
    }

    private void createSingleAlertCursorSalesPrice(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, final boolean z, @StringRes int i2, @StringRes int i3, String str, @StringRes int i4, @StringRes final int i5, boolean z2, String... strArr) {
        if (!z) {
            this.setViewVisibilityGone(view);
            final View view2 = selectablePriceDivider;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(View.GONE);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(textView);
            return;
        }
        final String definition = this.getSalesDetail().getPrice().getDefinition();
        Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
        if (0 == definition.length() && ((!this.getSalesDetail().isHasVariant() || -1 != getSalesDetail().getVariant().getLogicalRef()) && !SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.getSalesDetail().getmSalesType()))) {
            if (0 >= ficheDiscountRefProp.getLogicalRef()) {
                this.setFirstPrice(str, strArr);
            } else {
                this.setSelectedPricePosition(str, strArr);
            }
        }
        this.loadTextView(textView, ficheDiscountRefProp.toString());
        final String paramValue = viewModel.getSqlHelper().getParamValue(ParameterTypes.ptCanChangeSelectedPrice);
        Intrinsics.checkNotNullExpressionValue(paramValue, "getParamValue(...)");
        if (StringUtils.paramValueTrueCheck(paramValue)) {
            this.requireView();
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(final View view3) {
                    createSingleAlertCursorSalesPricelambda8(SalesDetailFragment.this, str, strArr, i3, i2, z2, ficheDiscountRefProp, i4, textView, view3);
                }
            });
        }
    } 
    public static void createSingleAlertCursorSalesPricelambda8(SalesDetailFragment this0, final String query, final String[] args, final int i2, final int i3, final boolean z, FicheDiscountRefProp ficheRefProp, final int i4, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mPriceAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        Cursor cursor = this0.getCursor(query, Arrays.copyOf(args, args.length));
        if (null == cursor || 0 == cursor.getCount() || (this0.getSalesDetail().isHasVariant() && -1 == this0.getSalesDetail().getVariant().getLogicalRef())) {
            if (-1 != i2) {
                Toast.makeText(this0.getActivity(), this0.getString(i2), Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String string = this0.getString(i3);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i3), this0.getString(R.string.str_select_text));
        }
        final Context context = this0.getContext();
        Intrinsics.checkNotNull(context);
        new AlertDialog.Builder(context, R.style.PriceAlertDialogStyle).setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(i4), new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i5) {
                createSingleAlertCursorSalesPricelambda8lambda6(SalesDetailFragment, cursor, ficheRefProp, textView, dialogInterface, i5);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i5) {
                createSingleAlertCursorSalesPricelambda8lambda7(FicheDiscountRefProp.this, this0, textView, cursor, dialogInterface, i5);
            }
        }).create().show();
    }
 
    public static void createSingleAlertCursorSalesPricelambda8lambda6(final SalesDetailFragment this0, final Cursor cursor, final FicheDiscountRefProp ficheRefProp, final TextView textView, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this0.getSalesDetail().getmSalesType())) {
            Intrinsics.checkNotNull(cursor);
            this0.setRetailPrice(cursor, i2);
        } else {
            Intrinsics.checkNotNull(cursor);
            this0.setPrice(cursor, ficheRefProp, i2, textView);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }
 
    public static void createSingleAlertCursorSalesPricelambda8lambda7(final FicheDiscountRefProp ficheRefProp, final SalesDetailFragment this0, final TextView textView, final Cursor cursor, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.reset();
        this0.getSalesDetail().setPriceWithDigit("");
        this0.getSalesDetail().setPriceWithCurCode("");
        this0.getSalesDetail().setEnteryPrice(0.0d);
        this0.getSalesDetail().setUsePrice(0.0d);
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.getDefinition());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }

    private void setFirstPrice(final String str, final String[] strArr) {
        final Cursor cursor = this.getCursor(str, Arrays.copyOf(strArr, strArr.length));
        if (null == cursor || 0 == cursor.getCount()) {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
            if (!SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.getSalesDetail().getmSalesType()) && viewModel.getGetUseVatIncForProductsDontHavePriceCard() && 0 == getSalesDetail().getLogicalRef() && TextUtils.isEmpty(this.getSalesDetail().getIncludeVat().getDefinition()) && this.getmFicheMode() != FicheMode.COPY) {
                this.getSalesDetail().getIncludeVat().setSelect(true);
                this.loadCheckView(chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat().isSelect(), this.getSalesFicheDetailFields().isIncludeVat());
                return;
            }
            return;
        }
        this.setPrice(cursor, this.getSalesDetail().getSelectedPrice(), 0, txtSalesDetailSelectablePrice);
        if (cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    private void setSelectedPricePosition(final String str, final String[] strArr) {
        int i2;
        final boolean z;
        final Cursor cursor = this.getCursor(str, Arrays.copyOf(strArr, strArr.length));
        if (null == cursor || 0 == cursor.getCount()) {
            if (null == cursor || cursor.isClosed()) {
                return;
            }
            cursor.close();
            return;
        }
        if (cursor.moveToFirst()) {
            i2 = 0;
            while (true) {
                if (this.getSalesDetail().getSelectedPrice().getLogicalRef() == cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id)))) {
                    z = true;
                    break;
                }
                i2++;
                if (!cursor.moveToNext()) {
                    z = false;
                    break;
                }
            }
        } else {
            i2 = 0;
            z = false;
        }
        this.setPrice(cursor, this.getSalesDetail().getSelectedPrice(), z ? i2 : 0, txtSalesDetailSelectablePrice);
        if (cursor.isClosed()) {
            return;
        }
        cursor.close();
    }
    private void setPrice(final Cursor cursor, final FicheDiscountRefProp ficheDiscountRefProp, final int i2, final TextView textView) {
        final String str;
        ficheDiscountRefProp.setWhich(i2);
        if (cursor.moveToPosition(i2)) {
            ficheDiscountRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id))));
            ficheDiscountRefProp.setCode(String.valueOf(cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id)))));
            this.getSalesDetail().setEnteryPrice(cursor.getDouble(cursor.getColumnIndex("PRICE")));
            if (1 == cursor.getInt(cursor.getColumnIndex("UNITCONVERT"))) {
                this.getSalesDetail().setEnteryPrice(CalculateUtils.convertUnitPrice(this.getSalesDetail().getEnteryPrice(), this.getSalesDetail().getConvFact1(), this.getSalesDetail().getConvFact2(), cursor.getDouble(cursor.getColumnIndex("CONVFACT1")), cursor.getDouble(cursor.getColumnIndex("CONVFACT2"))));
            }
            this.getSalesDetail().getCurrType().reset();
            this.getSalesDetail().curCodeStr = cursor.getString(cursor.getColumnIndex("CURCODE"));
            final SalesDetail salesDetail = this.getSalesDetail();
            final UnitPriceFormatter unitPriceFormatter = mUnitPriceFormatter;
            Intrinsics.checkNotNull(unitPriceFormatter);
            salesDetail.setPriceWithDigit(unitPriceFormatter.getFormattedPrice(this.getSalesDetail().getEnteryPrice()));
            final SalesDetail salesDetail2 = this.getSalesDetail();
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String priceWithDigit = this.getSalesDetail().getPriceWithDigit();
            if (TextUtils.isEmpty(this.getSalesDetail().getCurCodeStr())) {
                str = "";
            } else {
                str = "/ " + this.getSalesDetail().getCurCodeStr();
            }
            final String format = String.format("%s %s", Arrays.copyOf(new Object[]{priceWithDigit, str}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            salesDetail2.setPriceWithCurCode(format);
            FicheStringProp.setDefinition(this.getSalesDetail().getPriceWithCurCode());
            this.getSalesDetail().setPrRate(cursor.getDouble(cursor.getColumnIndex("RATE")));
            this.getSalesDetail().prCurrType = cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_curr_nr)));
            this.getSalesDetail().getCurrType().setLogicalRef(this.getSalesDetail().getPrCurrType());
            this.getSalesDetail();
            FicheStringProp.setDefinition(this.getSalesDetail().getCurCodeStr());
            this.loadTextView(txtSalesDetailSelectedCurrType, this.getSalesDetail().getCurrType().getDefinition());
            if (viewModel.erpType() != ErpType.NETSIS) {
                this.getSalesDetail().getIncludeVat().setSelect(StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("INCVAT"))));
                this.loadCheckView(chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat().isSelect(), this.getSalesFicheDetailFields().isIncludeVat());
            }
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheDiscountRefProp.getDefinition());
            this.loadTextView(edtSalesDetailPrice, "");
            this.loadCurrPrice();
        }
    }
    private void setRetailPrice(final Cursor cursor, final int i2) {
        if (cursor.moveToPosition(i2)) {
            this.resetPrice();
            this.getSalesDetail();
            FicheStringProp.setDefinition(String.valueOf(CalculateUtils.calculatePriceAddVat(cursor.getDouble(cursor.getColumnIndex("PRICE")), this.getSalesDetail().getVat().getDefinitionDouble(), StringUtils.convertIntToBoolean(cursor.getInt(cursor.getColumnIndex("INCVAT"))))));
            this.loadTextView(edtSalesDetailPrice, this.getSalesDetail().getPrice().toString());
        }
    }
    private void createSingleAlertCursorSalesPrice(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, final boolean z, @StringRes int i2, @StringRes int i3, @StringRes final int i4, boolean z2, List<? extends Price> list) {
        if (!z) {
            this.setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE || hasOrderReference) {
            this.setViewDisabled(textView);
            return;
        }
        boolean z3 = true;
        if ((null == list || list.isEmpty()) && !SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.getSalesDetail().getmSalesType()) && viewModel.getGetUseVatIncForProductsDontHavePriceCard() && 0 == getSalesDetail().getLogicalRef() && TextUtils.isEmpty(this.getSalesDetail().getIncludeVat().getDefinition()) && this.getmFicheMode() != FicheMode.COPY) {
            this.getSalesDetail().getIncludeVat().setSelect(true);
            this.loadCheckView(chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat().isSelect(), this.getSalesFicheDetailFields().isIncludeVat());
        }
        if (null != list && !list.isEmpty() && (!this.getSalesDetail().isHasVariant() || -1 != getSalesDetail().getVariant().getLogicalRef())) {
            final String definition = this.getSalesDetail().getPrice().getDefinition();
            Intrinsics.checkNotNullExpressionValue(definition, "getDefinition(...)");
            if (0 == definition.length() && !SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this.getSalesDetail().getmSalesType())) {
                if (0 >= ficheDiscountRefProp.getLogicalRef()) {
                    final Price price = list.get(0);
                    Intrinsics.checkNotNull(price);
                    this.setFirstPrice(price, 0, ficheDiscountRefProp, textView);
                } else {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= list.size()) {
                            z3 = false;
                            break;
                        }
                        final int logicalRef = ficheDiscountRefProp.getLogicalRef();
                        final Price price2 = list.get(i5);
                        Intrinsics.checkNotNull(price2);
                        if (logicalRef == price2.getPriceRef()) {
                            break;
                        } else {
                            i5++;
                        }
                    }
                    final Price price3 = list.get(z3 ? i5 : 0);
                    Intrinsics.checkNotNull(price3);
                    this.setFirstPrice(price3, z3 ? i5 : 0, ficheDiscountRefProp, textView);
                }
            }
        }
        this.loadTextView(textView, ficheDiscountRefProp.toString());
        final String paramValue = viewModel.getSqlHelper().getParamValue(ParameterTypes.ptCanChangeSelectedPrice);
        Intrinsics.checkNotNullExpressionValue(paramValue, "getParamValue(...)");
        if (StringUtils.paramValueTrueCheck(paramValue)) {
            this.requireView();
            view.setOnClickListener(new View.OnClickListener() {  
                public void onClick(final View view2) {
                    createSingleAlertCursorSalesPricelambda11(SalesDetailFragment.this, list, i2, z2, ficheDiscountRefProp, i3, textView, view2);
                }
            });
        }
    }
    public static void createSingleAlertCursorSalesPricelambda11(SalesDetailFragment this0, List list, final int i2, final boolean z, FicheDiscountRefProp ficheRefProp, final int i3, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mPriceAlertDialogBuilderOnline;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        if (null == list || list.isEmpty() || (this0.getSalesDetail().isHasVariant() && -1 == this0.getSalesDetail().getVariant().getLogicalRef())) {
            Toast.makeText(this0.getActivity(), this0.getString(R.string.str_product_price_not_found), Toast.LENGTH_LONG).show();
            return;
        }
        String string = this0.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i2), this0.getString(R.string.str_select_text));
        }
        final AlertDialogBuilder<?> alertDialogBuilder2 = this0.mPriceAlertDialogBuilderOnline;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setTitle(string).setSingleChoiceItems((List<CharSequenceGet>) list, ficheRefProp.getWhich(), this0.getString(i3), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda14

            public void onClick(final DialogInterface dialogInterface, final int i4) {
                createSingleAlertCursorSalesPricelambda11lambda9(list, this0, ficheRefProp, textView, dialogInterface, i4);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i4) {
                createSingleAlertCursorSalesPricelambda11lambda10(ficheRefProp, this0, textView, dialogInterface, i4);
            }
        }).create().show();
    }
    private static void createSingleAlertCursorSalesPricelambda11lambda9(final List list, final SalesDetailFragment this0, final FicheDiscountRefProp ficheRefProp, final TextView textView, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final Price price = (Price) list.get(i2);
        if (null != price) {
            if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(this0.getSalesDetail().getmSalesType())) {
                this0.setRetailPrice(price);
            } else {
                this0.setFirstPrice(price, i2, ficheRefProp, textView);
            }
        }
        dialog.dismiss();
    }
    public static void createSingleAlertCursorSalesPricelambda11lambda10(final FicheDiscountRefProp ficheRefProp, final SalesDetailFragment this0, final TextView textView, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.reset();
        this0.getSalesDetail().setPriceWithDigit("");
        this0.getSalesDetail().setPriceWithCurCode("");
        this0.getSalesDetail().setEnteryPrice(0.0d);
        this0.getSalesDetail().setUsePrice(0.0d);
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.getDefinition());
        dialog.dismiss();
    }
    private void setFirstPrice(final Price price, final int i2, final FicheDiscountRefProp ficheDiscountRefProp, final TextView textView) {
        if (!this.getSalesDetail().getPromotion().isSelect() || viewModel.isPromotionItemPriceEnabled()) {
            ficheDiscountRefProp.setWhich(i2);
            ficheDiscountRefProp.setLogicalRef(price.getPriceRef());
            if (viewModel.erpType() == ErpType.NETSIS) {
                ficheDiscountRefProp.setCode(StringUtils.convertIntToString(price.getPriceRef()));
            }
            this.getSalesDetail().setEnteryPrice(StringUtils.convertStringToDouble(price.getPrice()));
            if (1 == price.getUnitConvert()) {
                this.getSalesDetail().setEnteryPrice(CalculateUtils.convertUnitPrice(StringUtils.convertStringToDouble(price.getPrice()), this.getSalesDetail().getConvFact1(), this.getSalesDetail().getConvFact2(), price.getConvFact1(), price.getConvFact2()));
            }
            this.getSalesDetail().getCurrType().reset();
            this.getSalesDetail().curCodeStr = price.getCurCode();
            final SalesDetail salesDetail = this.getSalesDetail();
            final UnitPriceFormatter unitPriceFormatter = mUnitPriceFormatter;
            Intrinsics.checkNotNull(unitPriceFormatter);
            salesDetail.setPriceWithDigit(unitPriceFormatter.getFormattedPrice(this.getSalesDetail().getEnteryPrice()));
            final SalesDetail salesDetail2 = this.getSalesDetail();
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s / %s ", Arrays.copyOf(new Object[]{this.getSalesDetail().getPriceWithDigit(), this.getSalesDetail().getCurCodeStr()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            salesDetail2.setPriceWithCurCode(format);
            FicheStringProp.setDefinition(this.getSalesDetail().getPriceWithCurCode());
            final SalesDetail salesDetail3 = this.getSalesDetail();
            final SalesDetailViewModel salesDetailViewModel = viewModel;
            final String curCode = price.getCurCode();
            Intrinsics.checkNotNullExpressionValue(curCode, "getCurCode(...)");
            salesDetail3.setPrRate(salesDetailViewModel.getLastCurRate(curCode, StringUtils.convertStringToInt(price.getCurType())));
            this.getSalesDetail().prCurrType = StringUtils.convertStringToInt(price.getCurType());
            this.getSalesDetail().getCurrType().setLogicalRef(this.getSalesDetail().getPrCurrType());
            this.getSalesDetail();
            FicheStringProp.setDefinition(this.getSalesDetail().getCurCodeStr());
            if (!this.getSalesDetail().getIncludeVat().isSelect()) {
                this.getSalesDetail().getIncludeVat().setSelect(StringUtils.convertIntToBoolean(price.getInvcat()));
                this.loadCheckView(chkSalesDetailIncludeVat, this.getSalesDetail().getIncludeVat().isSelect(), this.getSalesFicheDetailFields().isIncludeVat());
            }
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheDiscountRefProp.getDefinition());
            this.getSalesDetail().getPrice().reset();
            this.loadTextView(edtSalesDetailPrice, "");
        }
    }
    private void setRetailPrice(final Price price) {
        this.resetPrice();
        this.getSalesDetail();
        FicheStringProp.setDefinition(String.valueOf(CalculateUtils.calculatePriceAddVat(StringUtils.convertStringToDouble(price.getPrice()), this.getSalesDetail().getVat().getDefinitionDouble(), StringUtils.convertIntToBoolean(price.getInvcat()))));
        this.loadTextView(edtSalesDetailPrice, this.getSalesDetail().getPrice().toString());
    }
    private void initVariant(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheDiscountRefProp, final boolean z, @StringRes int i2, @StringRes int i3, @StringRes int i4, @StringRes int i5, boolean z2, final String str, String... strArr) {
        final int i6;
        if (!z) {
            this.setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE || hasOrderReference) {
            this.setViewDisabled(textView);
            return;
        }
        if (null != str && 0 != str.length()) {
            final Cursor cursor = this.getCursor(this.getString(i4), Arrays.copyOf(strArr, strArr.length));
            if (null != cursor && cursor.moveToFirst()) {
                int i7 = 0;
                while (true) {
                    if (Intrinsics.areEqual(cursor.getString(cursor.getColumnIndex(this.getString(R.string.column_code))), str)) {
                        i6 = i7;
                        break;
                    }
                    i7++;
                    if (!cursor.moveToNext()) {
                        i6 = -1;
                        break;
                    }
                }
                this.setVariantPosition(textView, ficheDiscountRefProp, i5, cursor, i6);
                this.setPrice();
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        this.loadTextView(textView, ficheDiscountRefProp.toString());
        this.requireView();
        view.setOnClickListener(new View.OnClickListener() { 
            public void onClick(final View view2) {
                initVariantlambda14(SalesDetailFragment.this, i4, strArr, i3, i2, z2, ficheDiscountRefProp, i5, textView, view2);
            }
        });
    }
    public static void initVariantlambda14(SalesDetailFragment this0, final int i2, final String[] args, final int i3, final int i4, final boolean z, FicheDiscountRefProp ficheRefProp, int i5, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null == cursor || 0 == cursor.getCount()) {
            if (-1 != i3) {
                Toast.makeText(this0.getActivity(), this0.getString(i3), Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String string = this0.getString(i4);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text));
        }
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mPriceAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(i5), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda16
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i6) {
                initVariantlambda14lambda12(cursor, this0, i5, ficheRefProp, textView, dialogInterface, i6);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda17
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i6) {
                initVariantlambda14lambda13(FicheDiscountRefProp.this, textView, cursor, this0, dialogInterface, i6);
            }
        }).create().show();
    } 
    public static void initVariantlambda14lambda12(final Cursor cursor, final SalesDetailFragment this0, final int i2, final FicheDiscountRefProp ficheRefProp, final TextView textView, final DialogInterface dialog, final int i3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(i3) && !Intrinsics.areEqual(cursor.getString(cursor.getColumnIndex(this0.getString(i2))), ficheRefProp.getDefinition())) {
            Intrinsics.checkNotNull(cursor);
            this0.setVariantPosition(textView, ficheRefProp, i2, cursor, i3);
            this0.setPrice();
            this0.getSalesDetail().setActualStock(this0.viewModel.getVariantRealStock(ficheRefProp.getLogicalRef()));
        }
        cursor.close();
        dialog.dismiss();
    } 
    private static void initVariantlambda14lambda13(final FicheDiscountRefProp ficheRefProp, final TextView textView, final Cursor cursor, final SalesDetailFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        final boolean z = -1 < ficheRefProp.getWhich();
        ficheRefProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        if (!cursor.isClosed()) {
            cursor.close();
        }
        this0.createSingleAlertCursorSalesUnit(this0.getmFicheMode(), this0.lnSalesDetailUnit, this0.txtSalesDetailUnit, this0.getSalesDetail().getUnit(), this0.getSalesDetail(), true, R.string.str_units, R.string.str_product_unit_not_found, R.string.qry_sales_detail_unit, R.string.column_unit, true, StringUtils.convertIntToString(this0.getSalesDetail().getItemRef()));
        if (z) {
            this0.resetPrice();
            this0.setPrice();
        }
        dialog.dismiss();
    }
    private void setVariantPosition(final TextView textView, final FicheDiscountRefProp ficheDiscountRefProp, @StringRes final int i2, final Cursor cursor, final int i3) {
        ficheDiscountRefProp.setWhich(i3);
        ficheDiscountRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id))));
        FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this.getString(i2))));
        ficheDiscountRefProp.setCode(cursor.getString(cursor.getColumnIndex(this.getString(R.string.column_code))));
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheDiscountRefProp.getDefinition());
    }
    private class SalesDetailPriceListener(
            WeakReference<SalesDetailFragment> mSalesDetailFragment) implements ResponseListener<List<? extends Price>> {
            private SalesDetailPriceListener(final SalesDetailFragment mSalesDetailFragment) {
                this.mSalesDetailFragment = new WeakReference<>(mSalesDetailFragment);
            }
            public void onResponse(final PrintSlipModel list) {
                LogManagerProperties mSalesDetailFragment;
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onPriceResult(list, "");
                    }
                }
            }
        public void onFailure(Throwable throwable) {
        }

        public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (null != this.mSalesDetailFragment.get()) {
                    final SalesDetailFragment salesDetailFragment = mSalesDetailFragment.get();
                    Intrinsics.checkNotNull(salesDetailFragment);
                    if (salesDetailFragment.isAttached()) {
                        final SalesDetailFragment salesDetailFragment2 = mSalesDetailFragment.get();
                        Intrinsics.checkNotNull(salesDetailFragment2);
                        salesDetailFragment2.onPriceResult(null, errorMessage);
                    }
                }
            }
        }
    public void onPriceResult(final List<? extends Price> list, final String str) {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (null != list) {
            this.createSingleAlertCursorSalesPrice(this.getmFicheMode(), lnSalesDetailSelectablePrice, txtSalesDetailSelectablePrice, this.getSalesDetail().getSelectedPrice(), true, R.string.str_price, R.string.column_curr_price, R.string.column_curr_type, true, list);
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this.getActivity(), str, Toast.LENGTH_LONG).show();
        }
        this.getPriceOffline();
    } 
    public void onNetworkConnectionChanged(final boolean z) {
        if (z) {
            return;
        }
        Toast.makeText(this.getActivity(), this.getString(R.string.exp_23_internet_connection_not_found), Toast.LENGTH_LONG).show();
    }
    public void createPriceSelectTypeSingleChoiceSales(final FicheMode ficheMode, final View view, TextView textView, FicheRefProp ficheRefProp, final boolean z, @StringRes int i2, CharSequence[] charSequences, boolean z2) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(charSequences, "charSequences");
        if (!z) {
            this.setViewVisibilityGone(view);
            return;
        }
        this.loadTextView(textView, ficheRefProp.toString());
        if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(view);
        } else {
            this.requireView();
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view2) {
                    createPriceSelectTypeSingleChoiceSaleslambda17(SalesDetailFragment.this, i2, z2, charSequences, ficheRefProp, textView, view2);
                }
            });
        }
    } 
    private static void createPriceSelectTypeSingleChoiceSaleslambda17(SalesDetailFragment this0, final int i2, final boolean z, CharSequence[] charSequences, FicheRefProp ficheRefProp, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(charSequences, "charSequences");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        String string = this0.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i2), this0.getString(R.string.str_select_text));
        }
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mPriceAlertDialogBuilderType;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(string).setSingleChoice(charSequences, ficheRefProp.getWhich(), new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                createPriceSelectTypeSingleChoiceSaleslambda17lambda15(ficheRefProp, charSequences, textView, this0, dialogInterface, i3);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i3) {
                createPriceSelectTypeSingleChoiceSaleslambda17lambda16(ficheRefProp, textView, this0, dialogInterface, i3);
            }
        }).create().show();
    }
    public static void createPriceSelectTypeSingleChoiceSaleslambda17lambda15(final FicheRefProp ficheRefProp, final CharSequence[] charSequences, final TextView textView, final SalesDetailFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(charSequences, "charSequences");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.setWhich(i2);
        ficheRefProp.setLogicalRef(i2);
        FicheStringProp.setDefinition(charSequences[i2].toString());
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.getDefinition());
        this0.getCustomerSelectedPriceTypeOnline();
        dialog.dismiss();
    }
    public static void createPriceSelectTypeSingleChoiceSaleslambda17lambda16(final FicheRefProp ficheRefProp, final TextView textView, final SalesDetailFragment this0, final DialogInterface dialog, final int i2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        ficheRefProp.reset();
        Intrinsics.checkNotNull(textView);
        textView.setText(ficheRefProp.toString());
        this0.getCustomerSelectedPriceTypeOnline();
        dialog.dismiss();
    }
    private void getCustomerSelectedPriceTypeOnline() {
        DispatchGroupCode dispatchGroupCode;
        int i2;
        boolean z = true;
        if (ContextUtils.checkConnectionWithoutMessage()) {
            if (this.getmFicheMode() != FicheMode.ANALYSE) {
                final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder);
                progressDialogBuilder.setMessage(this.getString(R.string.type_get_online_price)).show();
                DispatchGroupCode dispatchGroupCode2 = DispatchGroupCode.SALES;
                final int i3 = this.getSalesDetailActivity().getmCustomerRef();
                if (TextUtils.isEmpty(this.getSalesDetail().getSelectedPriceType().getDefinition())) {
                    z = false;
                } else {
                    if (!Intrinsics.areEqual(this.getString(CustomerPriceType.GET_ALL_CUSTOMER_LAST_SELL_PRICE.getmResourceId()), this.getSalesDetail().getSelectedPriceType().getDefinition())) {
                        if (Intrinsics.areEqual(this.getString(CustomerPriceType.GET_ALL_CUSTOMER_LAST_BUY_PRICE.getmResourceId()), this.getSalesDetail().getSelectedPriceType().getDefinition())) {
                            dispatchGroupCode2 = DispatchGroupCode.BUYING;
                        } else if (Intrinsics.areEqual(this.getString(CustomerPriceType.GET_CUSTOMER_LAST_BUY_PRICE.getmResourceId()), this.getSalesDetail().getSelectedPriceType().getDefinition())) {
                            dispatchGroupCode2 = DispatchGroupCode.BUYING;
                        }
                    }
                    dispatchGroupCode = dispatchGroupCode2;
                    i2 = -1;
                    if (!z) {
                        final SalesDetailViewModel salesDetailViewModel = viewModel;
                        final int itemRef = this.getSalesDetail().getItemRef();
                        final String code = this.getSalesDetail().getCode();
                        Intrinsics.checkNotNull(code);
                        final int logicalRef = this.getSalesDetail().getVariant().getLogicalRef();
                        final String code2 = TextUtils.isEmpty(this.getSalesDetail().getVariant().getCode()) ? "" : this.getSalesDetail().getVariant().getCode();
                        Intrinsics.checkNotNull(code2);
                        salesDetailViewModel.getSalesFicheSelectedItemPrice(new LastItemPriceSqlParams(itemRef, code, logicalRef, code2, i2, "", dispatchGroupCode, this.getSalesDetail().getWareHouse().getLogicalRef()), new SalesDetailPriceListener(this));
                    } else {
                        this.setPrice();
                    }
                }
                i2 = i3;
                if (!z) {
                }
            }
        } else {
            Toast.makeText(this.getActivity(), this.getString(R.string.str_not_internet_connection_price_get_local), Toast.LENGTH_LONG).show();
            this.getPriceOffline();
        }
    }
    private void createSingleAlertCursorSalesSecondUnit(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheRefProp, SalesDetail mSalesDetail, final boolean z, @StringRes int i2, @StringRes int i3, @StringRes int i4, @StringRes int i5, boolean z2, String... args) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        Intrinsics.checkNotNullParameter(args, "args");
        if (!z) {
            this.setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE || hasOrderReference) {
            this.setViewDisabled(textView);
            return;
        }
        this.loadTextView(textView, ficheRefProp.toString());
        if (mSalesDetail.isNotUnitChange()) {
            return;
        }
        this.requireView();
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view2) {
                createSingleAlertCursorSalesSecondUnitlambda19(SalesDetailFragment.this, i4, args, i3, i2, z2, ficheRefProp, i5, mSalesDetail, textView, view2);
            }
        });
    } 
    public static void createSingleAlertCursorSalesSecondUnitlambda19(SalesDetailFragment this0, final int i2, final String[] args, final int i3, final int i4, final boolean z, FicheDiscountRefProp ficheRefProp, int i5, SalesDetail mSalesDetail, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mUnitAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null == cursor || 0 == cursor.getCount()) {
            if (-1 != i3) {
                Toast.makeText(this0.getActivity(), this0.getString(i3), Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String string = this0.getString(i4);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text));
        }
        final AlertDialogBuilder<?> alertDialogBuilder2 = this0.mUnitAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(i5), new DialogInterface.OnClickListener() {  
            public void onClick(final DialogInterface dialogInterface, final int i6) {
                createSingleAlertCursorSalesSecondUnitlambda19lambda18(cursor, ficheRefProp, this0, i5, mSalesDetail, textView, dialogInterface, i6);
            }
        }).create().show();
    }
    public static void createSingleAlertCursorSalesSecondUnitlambda19lambda18(final Cursor cursor, final FicheDiscountRefProp ficheRefProp, final SalesDetailFragment this0, final int i2, final SalesDetail mSalesDetail, final TextView textView, final DialogInterface dialog, final int i3) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(i3)) {
            ficheRefProp.setWhich(i3);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(i2))));
            ficheRefProp.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
            mSalesDetail.setNetVolume(cursor.getDouble(cursor.getColumnIndex("NETVOLUME")));
            mSalesDetail.setGrossVolume(cursor.getDouble(cursor.getColumnIndex("GROSSVOLUME")));
            mSalesDetail.setNetWeight(cursor.getDouble(cursor.getColumnIndex("NETWEIGHT")));
            mSalesDetail.setGrossWeight(cursor.getDouble(cursor.getColumnIndex("GROSSWEIGHT")));
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheRefProp.getDefinition());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }
    private void createSingleAlertCursorSalesUnit(final FicheMode ficheMode, final View view, TextView textView, FicheDiscountRefProp ficheRefProp, SalesDetail mSalesDetail, final boolean z, @StringRes int i2, @StringRes int i3, @StringRes int i4, @StringRes int i5, boolean z2, String... args) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        Intrinsics.checkNotNullParameter(args, "args");
        if (!z) {
            this.setViewVisibilityGone(view);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE || hasOrderReference) {
            this.setViewDisabled(textView);
            return;
        }
        this.loadTextView(textView, ficheRefProp.toString());
        if (mSalesDetail.isNotUnitChange()) {
            return;
        }
        this.requireView();
        view.setOnClickListener(new View.OnClickListener() {  
            public void onClick(final View view2) {
                createSingleAlertCursorSalesUnitlambda21(SalesDetailFragment.this, i4, args, i3, i2, z2, ficheRefProp, i5, mSalesDetail, textView, view2);
            }
        });
    }
    public static void createSingleAlertCursorSalesUnitlambda21(SalesDetailFragment this0, final int i2, final String[] args, final int i3, final int i4, final boolean z, FicheDiscountRefProp ficheRefProp, int i5, SalesDetail mSalesDetail, TextView textView, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        final AlertDialogBuilder<?> alertDialogBuilder = this0.mUnitAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.dismiss();
        Cursor cursor = this0.getCursor(this0.getString(i2), Arrays.copyOf(args, args.length));
        if (null == cursor || 0 == cursor.getCount()) {
            if (-1 != i3) {
                Toast.makeText(this0.getActivity(), this0.getString(i3), Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String string = this0.getString(i4);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (z) {
            string = StringUtils.catStringSpace(this0.getString(i4), this0.getString(R.string.str_select_text));
        }
        final AlertDialogBuilder<?> alertDialogBuilder2 = this0.mUnitAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder2);
        alertDialogBuilder2.setTitle(string).setSingleChoiceItems(cursor, ficheRefProp.getWhich(), this0.getString(i5), new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentExternalSyntheticLambda0
 
            public void onClick(final DialogInterface dialogInterface, final int i6) {
                createSingleAlertCursorSalesUnitlambda21lambda20(cursor, ficheRefProp, this0, i5, mSalesDetail, textView, dialogInterface, i6);
            }
        }).create().show();
    } 
    public static void createSingleAlertCursorSalesUnitlambda21lambda20(final Cursor cursor, final FicheDiscountRefProp ficheRefProp, final SalesDetailFragment this0, final int i2, final SalesDetail mSalesDetail, final TextView textView, final DialogInterface dialog, final int i3) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(mSalesDetail, "mSalesDetail");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (cursor.moveToPosition(i3)) {
            ficheRefProp.setWhich(i3);
            ficheRefProp.setLogicalRef(cursor.getInt(cursor.getColumnIndex(this0.getString(R.string.column_id))));
            FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this0.getString(i2))));
            ficheRefProp.setCode(cursor.getString(cursor.getColumnIndex("CODE")));
            mSalesDetail.setNetVolume(cursor.getDouble(cursor.getColumnIndex("NETVOLUME")));
            mSalesDetail.setGrossVolume(cursor.getDouble(cursor.getColumnIndex("GROSSVOLUME")));
            mSalesDetail.setNetWeight(cursor.getDouble(cursor.getColumnIndex("NETWEIGHT")));
            mSalesDetail.setGrossWeight(cursor.getDouble(cursor.getColumnIndex("GROSSWEIGHT")));
            final double convFact1 = mSalesDetail.getConvFact1();
            final double convFact2 = mSalesDetail.getConvFact2();
            mSalesDetail.setConvFact1(cursor.getDouble(cursor.getColumnIndex("CONVFACT1")));
            mSalesDetail.setConvFact2(cursor.getDouble(cursor.getColumnIndex("CONVFACT2")));
            mSalesDetail.setActualStock(CalculateUtils.reCalculateActualStock(mSalesDetail.getActualStock(), convFact1, convFact2, mSalesDetail.getConvFact1(), mSalesDetail.getConvFact2()));
            if (0 != mSalesDetail.getSalesSerialLots().size()) {
                this0.setViewText(this0.edtSalesDetailAmount, this0.getSalesDetail().isDivUnit() ? StringUtils.convertDoubleToString(Double.valueOf(this0.getSalesDetail().getAmount().getDefinitionDouble())) : StringUtils.convertIntToString((int) this0.getSalesDetail().getAmount().getDefinitionDouble()));
            }
            Intrinsics.checkNotNull(textView);
            textView.setText(ficheRefProp.getDefinition());
            EventBus.getDefault().post(new PriceChangeEvent(true));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        dialog.dismiss();
    }
    public void priceChangeEvent(final PriceChangeEvent priceChange) {
        Intrinsics.checkNotNullParameter(priceChange, "priceChange");
        Log.d(SalesDetailFragment.TAG, "priceChangeEvent: " + priceChange.isChange());
        if (priceChange.isChange()) {
            this.resetPrice();
            this.setPrice();
        }
    }
    private void resetPrice() {
        this.getSalesDetail().resetSelectedPrice();
        final TextView textView = txtSalesDetailSelectablePrice;
        Intrinsics.checkNotNull(textView);
        textView.setText(this.getSalesDetail().getSelectedPrice().getDefinition());
    }
    private void createPriceEditTextAddTextChangedListener(final FicheMode ficheMode, final View view, EditText editText, FicheStringProp ficheStringProp, final boolean z) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        if (!z) {
            if (null != view) {
                this.setViewVisibilityGone(view);
                final View view2 = priceDivider;
                Intrinsics.checkNotNull(view2);
                view2.setVisibility(View.GONE);
                return;
            }
            this.setViewDisabled(editText);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE || hasOrderReference) {
            this.setViewDisabled(editText);
        } else {
            if (null == ficheStringProp) {
                this.setViewDisabled(view);
                return;
            }
            Intrinsics.checkNotNull(editText);
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { 
                public boolean onEditorAction(final TextView textView, final int i2, final KeyEvent keyEvent) {
                    final boolean createPriceEditTextAddTextChangedListenerlambda22;
                    createPriceEditTextAddTextChangedListenerlambda22 = createPriceEditTextAddTextChangedListenerlambda22(FicheStringProp.this, editText, textView, i2, keyEvent);
                    return createPriceEditTextAddTextChangedListenerlambda22;
                }
            });
            editText.addTextChangedListener(new TextWatcher() {  
                public void beforeTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
                    Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                }
                public void onTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
                    Intrinsics.checkNotNullParameter(charSequence, "charSequence");
                } 
                public void afterTextChanged(final Editable editable) {
                    Intrinsics.checkNotNullParameter(editable, "editable");
                    FicheStringProp.setDefinition(editable.toString());
                    if (0.0d < StringUtils.convertStringToDouble(editable.toString())) {
                        resetPrice();
                    }
                    loadCurrPrice();
                }
            });
        }
    } 
    public static boolean createPriceEditTextAddTextChangedListenerlambda22(final FicheStringProp ficheStringProp, final EditText editText, final TextView textView, final int i2, final KeyEvent keyEvent) {
        if (5 != i2 && 6 != i2) {
            return false;
        }
        FicheStringProp.setDefinition(editText.getText().toString());
        return false;
    }
    private boolean getWarehouseVisibility() {
        if (SalesUtils.isSalesTypeWhTransfer(this.getSalesDetail().getmSalesType())) {
            return false;
        }
        if (viewModel.erpType() != ErpType.TIGER) {
            return true;
        }
        if (SalesUtils.isSalesTypeDemand(this.getSalesDetail().getmSalesType())) {
            return viewModel.changeDemandLinesWarehouse();
        }
        return viewModel.changeSalesLinesWarehouse();
    }
    private void initCurrType() {
        if (SalesUtils.isSalesTypeWhTransfer(this.getSalesDetail().getmSalesType())) {
            final LinearLayout linearLayout = lnSalesDetailSelectedCurrType;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
            final View view = currencyTypeDivider;
            Intrinsics.checkNotNull(view);
            view.setVisibility(View.GONE);
            final LinearLayout linearLayout2 = lnSalesDetailSelectedCurrRate;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
            final View view2 = currencyRateDivider;
            Intrinsics.checkNotNull(view2);
            view2.setVisibility(View.GONE);
            return;
        }
        this.createSearchDialogCursorSales(this.getmFicheMode(), lnSalesDetailSelectedCurrType, lnSalesDetailSelectedCurrRate, txtSalesDetailSelectedCurrType, txtSalesDetailSelectedCurrRate, this.getSalesDetail().getCurrType(), viewModel.getIsSalesDetailCurrTypeChange(this.getSalesDetailActivity().getmCustomerRef(), this.getSalesDetail().getItemRef()), R.string.str_report_currency_unit_transaction, R.string.qry_get_curr_type, R.string.column_code);
    }
    private void createSearchDialogCursorSales(final FicheMode ficheMode, final View view, final View view2, TextView textView, TextView textView2, FicheRefProp ficheRefProp, final boolean z, @StringRes int i2, @StringRes int i3, @StringRes int i4) {
        if (!z) {
            this.setViewVisibilityGone(view);
            this.setViewVisibilityGone(view2);
            return;
        }
        if (ficheMode == FicheMode.ANALYSE) {
            this.setViewDisabled(textView);
            this.setViewDisabled(textView2);
        } else if (null == ficheRefProp) {
            this.setViewDisabled(textView);
            this.setViewDisabled(textView2);
        } else {
            this.loadTextView(textView, ficheRefProp.toString());
            this.loadTextView(textView2, this.getCurrRate(ficheRefProp));
            this.requireView();
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view3) {
                    createSearchDialogCursorSaleslambda23(SalesDetailFragment.this, i3, i2, i4, ficheRefProp, textView, textView2, view3);
                }
            });
        }
    }
    public static void createSearchDialogCursorSaleslambda23(SalesDetailFragment this0, final int i2, final int i3, final int i4, FicheRefProp ficheRefProp, TextView textView, TextView textView2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        try {
            final Cursor cursor = this0.getCursor(this0.getString(i2));
            if (null != cursor && 0 != cursor.getCount()) {
                final String catStringSpace = StringUtils.catStringSpace(this0.getString(i3), this0.getString(R.string.str_select_text));
                ArrayList<BaseSearchModel> baseSearchModelsFromCursor = this0.getBaseSearchModelsFromCursor(cursor, i4, ficheRefProp);
                new SimpleSearchDialogCompat(this0.getActivity(), catStringSpace, "", null, baseSearchModelsFromCursor, new SearchResultListener<BaseSearchModel>() { // from class: com.proje.mobilesales.features.sales.view.detail.SalesDetailFragmentcreateSearchDialogCursorSales1dialog1
                    @Override // com.proje.mobilesales.core.searchdialog.SearchResultListener
                    public void onCancelled(final BaseSearchDialogCompat<?> dialog) {
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                    }

                    @Override // com.proje.mobilesales.core.searchdialog.SearchResultListener
                    public void onSelected(final BaseSearchDialogCompat baseSearchDialogCompat, final BaseSearchModel baseSearchModel, final int i5) {
                        this.onSelected2((BaseSearchDialogCompat<?>) baseSearchDialogCompat, baseSearchModel, i5);
                    }

                    /* renamed from: onSelected, reason: avoid collision after fix types in other method */
                    public void onSelected2(final BaseSearchDialogCompat<?> dialog, final BaseSearchModel baseSearchModel, final int i5) {
                        final String currRate;
                        Intrinsics.checkNotNullParameter(dialog, "dialog");
                        if (null != baseSearchModel) {
                            final FicheRefProp ficheRefProp2 = ficheRefProp;
                            ficheRefProp2.setWhich(this0.findSearchModelPosition(baseSearchModelsFromCursor, baseSearchModel.getLogicalRef()));
                            ficheRefProp2.setLogicalRef(baseSearchModel.getLogicalRef());
                            FicheStringProp.setDefinition(baseSearchModel.getTitle());
                        }
                        final TextView textView3 = textView;
                        Intrinsics.checkNotNull(textView3);
                        textView3.setText(ficheRefProp.getDefinition());
                        this0.setNewCurrType();
                        final TextView textView4 = textView2;
                        Intrinsics.checkNotNull(textView4);
                        currRate = this0.getCurrRate(ficheRefProp);
                        textView4.setText(currRate);
                        dialog.dismiss();
                    }
                }, Boolean.FALSE).show();
                return;
            }
            Toast.makeText(this0.getActivity(), this0.getString(R.string.empty_text), Toast.LENGTH_SHORT).show();
        } catch (final Exception e2) {
            Log.e(SalesDetailFragment.TAG, "createSearchDialogCursorSales: ", e2);
        }
    }
    public String getCurrRate(final FicheRefProp ficheRefProp) {
        final Cursor query;
        String str = "";
        if (null != ficheRefProp.getDefinition() && null != (query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query(getString(R.string.qry_get_curr_rate), ficheRefProp.getDefinition()))) {
            if (query.moveToFirst()) {
                str = String.valueOf(query.getDouble(query.getColumnIndex("RATE")));
                final TextView textView = txtSalesDetailSelectedCurrRate;
                Intrinsics.checkNotNull(textView);
                textView.setText(String.valueOf(query.getDouble(query.getColumnIndex("RATE"))));
            }
            if (!query.isClosed()) {
                query.close();
            }
        }
        return str;
    }
    private ArrayList<BaseSearchModel> getBaseSearchModelsFromCursor(final Cursor cursor, @StringRes final int i2, final FicheRefProp ficheRefProp, final String... strArr) {
        boolean z;
        final ArrayList<BaseSearchModel> arrayList = new ArrayList<>();
        try {
            if (null != cursor) {
                try {
                    if (0 < cursor.getCount() && cursor.moveToFirst()) {
                        int i3 = 0;
                        do {
                            final int i4 = cursor.getInt(cursor.getColumnIndex(this.getString(R.string.column_id)));
                            final String string = cursor.getString(cursor.getColumnIndex(this.getString(i2)));
                            if (i3 != ficheRefProp.getWhich() && i4 != ficheRefProp.getLogicalRef()) {
                                z = false;
                                arrayList.add(new BaseSearchModel(i4, string, z));
                                i3++;
                            }
                            z = true;
                            arrayList.add(new BaseSearchModel(i4, string, z));
                            i3++;
                        } while (cursor.moveToNext());
                    }
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (final Exception e2) {
                    Log.e(SalesDetailFragment.TAG, "getBaseSearchModelsFromCursor: ", e2);
                    cursor.close();
                    return arrayList;
                }
            }
        } catch (final Throwable th) {
            cursor.close();
            throw th;
        }
        return arrayList;
    }
    public void setNewCurrType() {
        final String formatDoubleDynamicDigitsWithDot;
        final double currRateWithDate = viewModel.getSqlHelper().getCurrRateWithDate(this.getSalesDetail().getCurrType().getLogicalRef(), this.getSalesDetailActivity().getMSalesCreatedDate());
        this.getSalesDetail().curCodeStr = this.getSalesDetail().getCurrType().getDefinition();
        this.getSalesDetail().prCurrType = this.getSalesDetail().getCurrType().getLogicalRef();
        this.getSalesDetail().setPrRate(currRateWithDate);
        StringUtils.convertDoubleToString(Double.valueOf(this.getSalesDetail().getEnteryPrice()));
        if (this.getSalesDetail().getCurrType().getLogicalRef() == localCurrType) {
            formatDoubleDynamicDigitsWithDot = StringUtils.convertDoubleToString(Double.valueOf(this.getSalesDetail().getCalculateCurrPrice()));
        } else {
            formatDoubleDynamicDigitsWithDot = 0.0d == currRateWithDate ? "0" : StringUtils.formatDoubleDynamicDigitsWithDot(this.getSalesDetail().getCalculateCurrPrice() / currRateWithDate, centOfUnitPriceDigit);
        }
        final EditText editText = edtSalesDetailPrice;
        Intrinsics.checkNotNull(editText);
        editText.setText(formatDoubleDynamicDigitsWithDot);
        this.getSalesDetail();
        FicheStringProp.setDefinition(formatDoubleDynamicDigitsWithDot);
        if (-1 != getSalesDetail().getSelectedPrice().getLogicalRef()) {
            this.resetPrice();
        }
        this.loadCurrPrice();
    }
    public void loadCurrPrice() {
        double convertStringToDouble = StringUtils.convertStringToDouble(this.getSalesDetail().getPrice().toString());
        if (0.0d >= convertStringToDouble) {
            convertStringToDouble = this.getSalesDetail().getEnteryPrice();
        }
        if (localCurrType != this.getSalesDetail().getPrCurrType()) {
            convertStringToDouble *= 0 == getSalesDetail().getPrCurrType() ? 1 : viewModel.getSqlHelper().getCurrRateWithDate(this.getSalesDetail().getPrCurrType(), this.getSalesDetailActivity().getMSalesCreatedDate());
        }
        this.getSalesDetail().setCalculateCurrPrice(convertStringToDouble);
        this.loadTextView(txtSalesDetailSelectedCurrType, this.getSalesDetail().getCurrType().toString());
        this.loadTextView(txtSalesDetailSelectedCurrRate, this.getCurrRate(this.getSalesDetail().getCurrType()));
        this.loadTextView(txtSalesDetailCurrPrice, String.valueOf(StringUtils.roundTwoDecimals(convertStringToDouble)), viewModel.getIsSalesDetailCurrTypeChange(this.getSalesDetailActivity().getmCustomerRef(), this.getSalesDetail().getItemRef()));
    }
    public EditText[] getEdtSalesDetailDiscountRatios() {
        return edtSalesDetailDiscountRatios;
    }
    public EditText[] getEdtSalesDetailDiscountTotals() {
        return edtSalesDetailDiscountTotals;
    }
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
