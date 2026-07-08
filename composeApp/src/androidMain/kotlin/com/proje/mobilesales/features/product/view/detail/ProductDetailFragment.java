package com.proje.mobilesales.features.product.view.detail;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.FragmentProductDetailBinding;
import com.proje.mobilesales.features.customer.view.general.CustomerGeneralFragment;
import com.proje.mobilesales.features.customer.view.summary.CustomerSummaryFragment;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.repository.ProductDetailRepository;
import com.proje.mobilesales.features.product.view.serial.ProductSerialFragment;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;

import java.lang.ref.WeakReference;
import java.util.*;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;
import static com.proje.mobilesales.features.product.model.ProductDetail.*;

public final class ProductDetailFragment extends BaseFragment {
    private FragmentProductDetailBinding _binding;
    private TextView brand;
    private TextView groupCode;
    private boolean isService;
    private RelativeLayout mChartContentFrame;
    private FrameLayout mChartFrameMonthlySales;
    private FrameLayout mChartFrameTopTenProduct;
    private BarChart mChartMonthlySales;
    private BarChart mChartTopTenProduct;
    private int mItemLogicalRef;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private RelativeLayout mRltCustomerTopTenProduct;
    private final BarChart mRltMonthlySales;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private int maxCharCount;
    private TextView paybackKDVRate;
    private TextView paymentType;
    private LinearLayout priceLayout;
    private TextView priceTextView;
    private TextView productCode;
    private ProductDetail productDetail;
    private LinearLayout purchaseLayout;
    private TextView purchaseTextView;
    private final ProductDetailRepository repository;
    private TextView salesKDVRate;
    private TextView speCode1;
    private TextView speCode2;
    private TextView speCode3;
    private TextView speCode4;
    private TextView speCode5;
    private LinearLayout stockLayout;
    private TextView stockTextView;
    private LinearLayout unitLayout;
    private TextView unitText;
    private final ProductDetailViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String PRODUCT_CODE = ProductDetailFragment.class.getName() + ".PRODUCT_CODE";
    public static final String PRODUCT_ISSERVICE = ProductDetailFragment.class.getName() + ".PRODUCT_ISSERVICE";
    public static final String STATE_ITEM_LOGICAL_REF = ProductSerialFragment.STATE_ITEM_LOGICAL_REF;
    public static final String STATE_CONNECTED = CustomerSummaryFragment.STATE_CONNECTED;
    public static final String STATE_ITEM_ISSERVICE = "state:isService";
    public ProductDetailFragment() {
        ProductDetailRepository productDetailRepository = new ProductDetailRepository();
        this.repository = productDetailRepository;
        this.viewModel = new ProductDetailViewModel(productDetailRepository);
    }
    private FragmentProductDetailBinding getBinding() {
        FragmentProductDetailBinding fragmentProductDetailBinding = this._binding;
        Intrinsics.checkNotNull(fragmentProductDetailBinding);
        return fragmentProductDetailBinding;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentProductDetailBinding.inflate(layoutInflater, viewGroup, false);
        setHasOptionsMenu(true);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = getBinding().swipeLayout;
        Intrinsics.checkNotNullExpressionValue(appBarSwipeRefreshLayout, "swipeLayout");
        this.mSwipeRefreshLayout = appBarSwipeRefreshLayout;
        RelativeLayout relativeLayout = getBinding().rltChartContentFrame;
        Intrinsics.checkNotNullExpressionValue(relativeLayout, "rltChartContentFrame");
        this.mChartContentFrame = relativeLayout;
        TextView textView = getBinding().txtProductCode;
        Intrinsics.checkNotNullExpressionValue(textView, "txtProductCode");
        this.productCode = textView;
        TextView textView2 = getBinding().txtSpecCodeOne;
        Intrinsics.checkNotNullExpressionValue(textView2, "txtSpecCodeOne");
        this.speCode1 = textView2;
        TextView textView3 = getBinding().txtSpecCodeTwo;
        Intrinsics.checkNotNullExpressionValue(textView3, "txtSpecCodeTwo");
        this.speCode2 = textView3;
        TextView textView4 = getBinding().txtSpecCodeThree;
        Intrinsics.checkNotNullExpressionValue(textView4, "txtSpecCodeThree");
        this.speCode3 = textView4;
        TextView textView5 = getBinding().txtSpecCodeFour;
        Intrinsics.checkNotNullExpressionValue(textView5, "txtSpecCodeFour");
        this.speCode4 = textView5;
        TextView textView6 = getBinding().txtSpecCodeFive;
        Intrinsics.checkNotNullExpressionValue(textView6, "txtSpecCodeFive");
        this.speCode5 = textView6;
        TextView textView7 = getBinding().txtGroupCode;
        Intrinsics.checkNotNullExpressionValue(textView7, "txtGroupCode");
        this.groupCode = textView7;
        TextView textView8 = getBinding().txtBrand;
        Intrinsics.checkNotNullExpressionValue(textView8, "txtBrand");
        this.brand = textView8;
        TextView textView9 = getBinding().txtPaymentType;
        Intrinsics.checkNotNullExpressionValue(textView9, "txtPaymentType");
        this.paymentType = textView9;
        TextView textView10 = getBinding().txtSalesKdvRate;
        Intrinsics.checkNotNullExpressionValue(textView10, "txtSalesKdvRate");
        this.salesKDVRate = textView10;
        TextView textView11 = getBinding().txtPaybackKdvRate;
        Intrinsics.checkNotNullExpressionValue(textView11, "txtPaybackKdvRate");
        this.paybackKDVRate = textView11;
        LinearLayout linearLayout = getBinding().stockLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "stockLayout");
        this.stockLayout = linearLayout;
        TextView textView12 = getBinding().stockText;
        Intrinsics.checkNotNullExpressionValue(textView12, "stockText");
        this.stockTextView = textView12;
        LinearLayout linearLayout2 = getBinding().priceLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "priceLayout");
        this.priceLayout = linearLayout2;
        TextView textView13 = getBinding().priceText;
        Intrinsics.checkNotNullExpressionValue(textView13, "priceText");
        this.priceTextView = textView13;
        TextView textView14 = getBinding().purchaseText;
        Intrinsics.checkNotNullExpressionValue(textView14, "purchaseText");
        this.purchaseTextView = textView14;
        LinearLayout linearLayout3 = getBinding().purchaseLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout3, "purchaseLayout");
        this.purchaseLayout = linearLayout3;
        LinearLayout linearLayout4 = getBinding().unitLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout4, "unitLayout");
        this.unitLayout = linearLayout4;
        TextView textView15 = getBinding().unitText;
        Intrinsics.checkNotNullExpressionValue(textView15, "unitText");
        this.unitText = textView15;
        RelativeLayout relativeLayout2 = getBinding().rltCustomerTop10Product;
        Intrinsics.checkNotNullExpressionValue(relativeLayout2, "rltCustomerTop10Product");
        this.mRltCustomerTopTenProduct = relativeLayout2;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = null;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRltCustomerTopTenProduct");
            relativeLayout2 = null;
        }
        relativeLayout2.setVisibility(View.GONE);
        FrameLayout frameLayout = getBinding().chartFrame;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "chartFrame");
        this.mChartFrameTopTenProduct = frameLayout;
        BarChart barChart = new BarChart(getActivity());
        this.mChartTopTenProduct = barChart;
        FrameLayout frameLayout2 = this.mChartFrameTopTenProduct;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartFrameTopTenProduct");
            frameLayout2 = null;
        }
        initHorizontalChart(barChart, frameLayout2);
        RelativeLayout relativeLayout3 = getBinding().rltMonthlySales;
        Intrinsics.checkNotNullExpressionValue(relativeLayout3, "rltMonthlySales");

        if (relativeLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRltMonthlySales");
            relativeLayout3 = null;
        }
        relativeLayout3.setVisibility(View.GONE);
        FrameLayout frameLayout3 = getBinding().chartFrame2;
        Intrinsics.checkNotNullExpressionValue(frameLayout3, "chartFrame2");
        this.mChartFrameMonthlySales = frameLayout3;
        BarChart barChart2 = new BarChart(getActivity());
        this.mChartMonthlySales = barChart2;
        FrameLayout frameLayout4 = this.mChartFrameMonthlySales;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartFrameMonthlySales");
            frameLayout4 = null;
        }
        initHorizontalChart(barChart2, frameLayout4);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout3 = null;
        }
        appBarSwipeRefreshLayout3.setColorSchemeResources(R.color.white);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout4 = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout4 = null;
        }
        appBarSwipeRefreshLayout4.setProgressBackgroundColorSchemeResource(R.color.redA200);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout5 = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
        } else {
            appBarSwipeRefreshLayout2 = appBarSwipeRefreshLayout5;
        }
        appBarSwipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { 
            public void onRefresh() {
                ProductDetailFragment.onCreateViewlambda0(ProductDetailFragment.this);
            }
        });
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public static void onCreateViewlambda0(ProductDetailFragment productDetailFragment) {
        Intrinsics.checkNotNullParameter(productDetailFragment, "this0");
        if (productDetailFragment.mItemLogicalRef >= 0) {
            productDetailFragment.load();
        }
    }
    private void load() {
        if (isConnected(getContext())) {
            this.viewModel.getItemDetailsProductChart(this.mItemLogicalRef, new ProductChartsListener(this));
            return;
        }
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        appBarSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), getString(R.string.exp_23_internet_connection_not_found), Toast.LENGTH_LONG).show();
    } 
    public void onChartData(ProductDetail productDetail) {
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        appBarSwipeRefreshLayout.setRefreshing(false);
        if (productDetail != null) {
            bindCustomerTopTenProductData(productDetail.getTopProducts());
            bindMonthlySalesData(productDetail.getMonthlyProductSalesList());
        }
    } 
    public static final class ProductChartsListener implements ResponseListener<ProductDetail> {
        private final WeakReference<ProductDetailFragment> mProductDetailFragment;

        public ProductChartsListener(ProductDetailFragment productDetailFragment) {
            this.mProductDetailFragment = new WeakReference<>(productDetailFragment);
        }

        public void onResponse(PrintSlipModel productDetail) {
            if (this.mProductDetailFragment.get() != null) {
                ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                Intrinsics.checkNotNull(productDetailFragment);
                if (productDetailFragment.isAttached()) {
                    ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment2);
                    productDetailFragment2.onChartData(productDetail);
                }
            }
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductDetailFragment.get() != null) {
                ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                Intrinsics.checkNotNull(productDetailFragment);
                if (productDetailFragment.isAttached()) {
                    ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment2);
                    productDetailFragment2.onChartData(null);
                }
            }
        }
    } 
    private void bindCustomerTopTenProductData(List<? extends TopProduct> r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.detail.ProductDetailFragment.bindCustomerTopTenProductData(java.util.List):void");
    }
    private void bindMonthlySalesData(List<? extends MonthlyProductSales> list) {
        String str;
        BarChart barChart = null;
        if (list == null || list.isEmpty()) {
            BarChart relativeLayout = this.mRltMonthlySales;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mRltMonthlySales");
            } else {
                barChart = relativeLayout;
            }
            barChart.setVisibility(View.GONE);
            return;
        }
        RelativeLayout relativeLayout2 = this.mChartContentFrame;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartContentFrame");
            relativeLayout2 = null;
        }
        relativeLayout2.setVisibility(View.VISIBLE);
        RelativeLayout relativeLayout3 = this.mRltMonthlySales.getMarkerView();
        if (relativeLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRltMonthlySales");
            relativeLayout3 = null;
        }
        relativeLayout3.setVisibility(View.VISIBLE);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.maxCharCount = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            MonthlyProductSales monthlyProductSales = list.get(i);
            if (monthlyProductSales.getMonth() == null) {
                str = "";
            } else {
                str = monthlyProductSales.getMonth();
                Intrinsics.checkNotNullExpressionValue(str, "getMonth(...)");
            }
            String salesAmount = monthlyProductSales.getSalesAmount();
            Intrinsics.checkNotNullExpressionValue(salesAmount, "getSalesAmount(...)");
            arrayList2.add(i, new BarEntry(Float.parseFloat(salesAmount), i, str));
            arrayList.add(str);
            if (str.length() > this.maxCharCount) {
                this.maxCharCount = str.length();
            }
        }
        BarDataSet barDataSet = new BarDataSet(arrayList2, getString(R.string.str_customer_summary_monthly_sales));
        barDataSet.setBarBorderWidth(1.0f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(arrayList, barDataSet);
        FrameLayout frameLayout = this.mChartFrameMonthlySales;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartFrameMonthlySales");
            frameLayout = null;
        }
        setFrameHeight(frameLayout, this.maxCharCount);
        BarChart barChart2 = this.mChartMonthlySales;
        if (barChart2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartMonthlySales");
            barChart2 = null;
        }
        barChart2.setData(barData);
        BarChart barChart3 = this.mChartMonthlySales;
        if (barChart3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartMonthlySales");
            barChart3 = null;
        }
        barChart3.animateXY(2000, 2000);
        BarChart barChart4 = this.mChartMonthlySales;
        if (barChart4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartMonthlySales");
        } else {
            barChart = barChart4;
        }
        barChart.invalidate();
    }
    private void setFrameHeight(FrameLayout frameLayout, int i) {
        int i2 = i * 25;
        if (i2 < 1000) {
            i2 = 500;
        } else if (i2 > 1000) {
            i2 = 1000;
        }
        frameLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i2, getResources().getDisplayMetrics());
        Log.i("CHART", "initHorizontalChart: " + this.maxCharCount + ' ' + frameLayout.getLayoutParams().height);
    }
    private void toggleChartView() {
        RelativeLayout relativeLayout = null;
        if (!isConnected(getContext())) {
            RelativeLayout relativeLayout2 = this.mChartContentFrame;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mChartContentFrame");
            } else {
                relativeLayout = relativeLayout2;
            }
            relativeLayout.setVisibility(View.GONE);
            return;
        }
        RelativeLayout relativeLayout3 = this.mChartContentFrame;
        if (relativeLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mChartContentFrame");
        } else {
            relativeLayout = relativeLayout3;
        }
        relativeLayout.setVisibility(View.VISIBLE);
    }
    private void initHorizontalChart(BarChart barChart, FrameLayout frameLayout) {
        frameLayout.addView(barChart);
        barChart.getLayoutParams().height = -1;
        barChart.getLegend().setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        barChart.getAxisLeft().setEnabled(true);
        barChart.getAxisRight().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setLabelRotationAngle(270.0f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setTextColor(-12303292);
        xAxis.setTextSize(9.0f);
        barChart.fitScreen();
        barChart.setVisibleYRangeMaximum(150.0f, YAxis.AxisDependency.LEFT);
        barChart.setVisibleYRangeMaximum(150.0f, YAxis.AxisDependency.RIGHT);
        barChart.setDescription("");
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
    }
    private void setProductDetailInfoUI(ProductDetail productDetail) {
        TextView textView = this.productCode;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("productCode");
            textView = null;
        }
        textView.setText(getString(R.string.str_product_information) + '(' + productDetail.getCode() + ')');
        TextView textView3 = this.speCode1;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speCode1");
            textView3 = null;
        }
        textView3.setText(productDetail.getSpecode1());
        TextView textView4 = this.speCode2;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speCode2");
            textView4 = null;
        }
        textView4.setText(productDetail.getSpecode2());
        TextView textView5 = this.speCode3;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speCode3");
            textView5 = null;
        }
        textView5.setText(productDetail.getSpecode3());
        TextView textView6 = this.speCode4;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speCode4");
            textView6 = null;
        }
        textView6.setText(productDetail.getSpecode4());
        TextView textView7 = this.speCode5;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speCode5");
            textView7 = null;
        }
        textView7.setText(productDetail.getSpecode5());
        TextView textView8 = this.groupCode;
        if (textView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupCode");
            textView8 = null;
        }
        textView8.setText(productDetail.getGrupcode());
        TextView textView9 = this.brand;
        if (textView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("brand");
            textView9 = null;
        }
        textView9.setText(productDetail.getMark());
        TextView textView10 = this.paymentType;
        if (textView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentType");
            textView10 = null;
        }
        textView10.setText(productDetail.getPaymentPlan());
        TextView textView11 = this.salesKDVRate;
        if (textView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("salesKDVRate");
            textView11 = null;
        }
        textView11.setText(String.valueOf(productDetail.getVat()));
        TextView textView12 = this.paybackKDVRate;
        if (textView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paybackKDVRate");
        } else {
            textView2 = textView12;
        }
        textView2.setText(String.valueOf(productDetail.getReturnVat()));
    }
    public void onActivityCreated(Bundle bundle) {
        ProgressDialogBuilder cancelable;
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mItemLogicalRef = bundle.getInt(STATE_ITEM_LOGICAL_REF);
            this.isService = bundle.getBoolean(PRODUCT_ISSERVICE);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mItemLogicalRef = arguments.getInt(PRODUCT_CODE);
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.isService = arguments2.getBoolean(PRODUCT_ISSERVICE);
            toggleChartView();
        }
        ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait));
        if (!(message == null || (cancelable = message.setCancelable(false)) == null)) {
            cancelable.show();
        }
        this.viewModel.getItemDetailInformationForProduct(this.mItemLogicalRef, this.isService, new ProductDetailInfoResponseListener(this));
    } 
    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.createOptionsMenu(menu, menuInflater);
        if (menu != null) {
            menu.clear();
        }
    } 
    public void prepareOptionsMenu(Menu menu) {
        super.prepareOptionsMenu(menu);
        if (menu != null) {
            menu.clear();
        }
    } 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return false;
        }
        activity.onBackPressed();
        return false;
    } 
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_ITEM_LOGICAL_REF, this.mItemLogicalRef);
        bundle.putBoolean(STATE_ITEM_ISSERVICE, this.isService);
    } 
    public void onProductLoads(ArrayList<ProductDetail> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            String str = PRODUCT_CODE;
            Log.i(str, "onProductLoads: " + arrayList.size() + ' ' + arrayList.get(0).getName());
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            Intrinsics.checkNotNull(appCompatActivity);
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setTitle(arrayList.get(0).getName());
            ProductDetail productDetail = arrayList.get(0);
            this.productDetail = productDetail;
            Intrinsics.checkNotNull(productDetail);
            setProductDetailInfoUI(productDetail);
            ProductDetail productDetail2 = this.productDetail;
            Intrinsics.checkNotNull(productDetail2);
            onWareHouseLoads(productDetail2.getAmbar());
            ProductDetail productDetail3 = this.productDetail;
            Intrinsics.checkNotNull(productDetail3);
            onUnitLoads(productDetail3.getItemUnits());
            ProductDetail productDetail4 = this.productDetail;
            Intrinsics.checkNotNull(productDetail4);
            onPriceLoads(productDetail4.getItemPrices());
            onChartData(this.productDetail);
        }
        getMProgressDialogBuilder().dismiss();
        getLastCustomerSalesDateOfItemForProduct();
    }
    public void onProductsLoadError(String str) {
        getMProgressDialogBuilder().dismiss();
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
    private void onWareHouseLoads(ArrayList<Ambar> arrayList) {
        TextView textView;
        LinearLayout typeface = null;
        if (arrayList == null || arrayList.isEmpty()) {
            LinearLayout linearLayout = this.stockLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                linearLayout = null;
            }
            linearLayout.setVisibility(View.GONE);
            TextView textView2 = this.stockTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stockTextView");
                textView = null;
            } else {
                textView = textView2;
            }
            textView.setVisibility(View.GONE);
            return;
        }
        float f = requireActivity().getResources().getDisplayMetrics().density;
        int i = (int) (((float) 5) * f);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            TextView textView3 = new TextView(getActivity());
            textView3.setText(getString(R.string.str_product_detail_stock_title) + ' ' + arrayList.get(i3).getAmbarName());
            textView3.setPadding(i2, i, i2, i);
            TextView textView4 = new TextView(getActivity());
            textView4.setText(getString(R.string.str_actual_stock) + ' ' + StringUtils.formatStock(arrayList.get(i3).getFiiliStok()) + ' ' + arrayList.get(i3).getUnitCode());
            textView4.setPadding(i2, i, i2, i);
            TextView textView5 = new TextView(getActivity());
            textView5.setText(getString(R.string.str_real_stock) + ' ' + StringUtils.formatStock(arrayList.get(i3).getGercekStok()) + ' ' + arrayList.get(i3).getUnitCode());
            textView5.setPadding(i2, i, i2, i);
            LinearLayout linearLayout2 = this.stockLayout;
            LinearLayout linearLayout3 = linearLayout2;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                linearLayout3 = typeface;
            }
            linearLayout3.addView(textView3);
            if (!this.viewModel.getBaseErp().isHideActualStockAmount()) {
                LinearLayout linearLayout4 = this.stockLayout;
                LinearLayout linearLayout5 = linearLayout4;
                if (linearLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                    linearLayout5 = typeface;
                }
                linearLayout5.addView(textView4);
            }
            if (!this.viewModel.getBaseErp().isHideRealStockAmount()) {
                LinearLayout linearLayout6 = this.stockLayout;
                LinearLayout linearLayout7 = linearLayout6;
                if (linearLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                    linearLayout7 = typeface;
                }
                linearLayout7.addView(textView5);
            }
            int i4 = -1;
            int i5 = 1;
            if (arrayList.get(i3).getVariantStocks() != null) {
                List<ItemVariantStock> variantStocks = arrayList.get(i3).getVariantStocks();
                Intrinsics.checkNotNull(variantStocks);
                if (!variantStocks.isEmpty()) {
                    TextView textView6 = new TextView(getActivity());
                    textView6.setText(R.string.str_variants);
                    textView6.setTypeface(null, Typeface.BOLD);
                    textView6.setGravity(17);
                    LinearLayout linearLayout8 = this.stockLayout;
                    LinearLayout linearLayout9 = linearLayout8;
                    if (linearLayout8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                        linearLayout9 = typeface;
                    }
                    linearLayout9.addView(textView6);
                    List<ItemVariantStock> variantStocks2 = arrayList.get(i3).getVariantStocks();
                    Intrinsics.checkNotNull(variantStocks2);
                    for (ItemVariantStock itemVariantStock : variantStocks2) {
                        TextView textView7 = new TextView(getActivity());
                        textView7.setTypeface(null, Typeface.NORMAL);
                        textView7.setText(itemVariantStock.getVariantName());
                        textView7.setTextSize(2, getResources().getDimension(R.dimen.text_size_small) / f);
                        LinearLayout linearLayout10 = this.stockLayout;
                        if (linearLayout10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                            linearLayout10 = null;
                        }
                        linearLayout10.addView(textView7);
                        LinearLayout linearLayout11 = new LinearLayout(getActivity());
                        linearLayout11.setLayoutParams(new LinearLayout.LayoutParams(i4, -2, 1.0f));
                        TextView textView8 = new TextView(getActivity());
                        textView8.setText(getString(R.string.str_actual) + ' ' + itemVariantStock.getVariantActualStok() + ' ' + arrayList.get(i3).getUnitCode());
                        textView8.setLayoutParams(new LinearLayout.LayoutParams(i2, -2, 0.5f));
                        textView8.setTextSize(2, getResources().getDimension(R.dimen.text_size_small) / f);
                        if (!this.viewModel.getBaseErp().isHideActualStockAmount()) {
                            linearLayout11.addView(textView8);
                        }
                        TextView textView9 = new TextView(getActivity());
                        textView9.setText(getString(R.string.str_real_stock) + ' ' + itemVariantStock.getVariantRealStok() + ' ' + arrayList.get(i3).getUnitCode());
                        textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 0.5f));
                        textView9.setTextSize(2, getResources().getDimension(R.dimen.text_size_small) / f);
                        if (!this.viewModel.getBaseErp().isHideRealStockAmount()) {
                            linearLayout11.addView(textView9);
                        }
                        linearLayout11.setPadding(0, 0, 0, i);
                        LinearLayout linearLayout12 = this.stockLayout;
                        if (linearLayout12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                            linearLayout12 = null;
                        }
                        linearLayout12.addView(linearLayout11);
                        i3 = i3;
                        f = f;
                        i4 = -1;
                        i5 = 1;
                        i2 = 0;
                        typeface = null;
                    }
                }
            }
            View view = new View(getActivity());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (f)));
            view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.colorDivider2));
            LinearLayout linearLayout13 = this.stockLayout;
            if (linearLayout13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stockLayout");
                linearLayout13 = null;
            }
            linearLayout13.addView(view);
            i3++;
            i2 = i2;
            f = f;
            typeface = null;
        }
    } 
    public static final class LastPurchaseInfoListenerForProduct implements ResponseListener<List<? extends PurchasePriceInfo>> {
        private final WeakReference<ProductDetailFragment> mProductDetailFragment;

        public LastPurchaseInfoListenerForProduct(ProductDetailFragment productDetailFragment) {
            this.mProductDetailFragment = new WeakReference<>(productDetailFragment);
        }

        public void onResponse(PrintSlipModel list) {
            if (this.mProductDetailFragment.get() != null) {
                ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                Intrinsics.checkNotNull(productDetailFragment);
                if (productDetailFragment.isAttached()) {
                    ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment2);
                    productDetailFragment2.onLastPurchaseInfoResultForProduct(list);
                }
            }
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            Log.d("AA", "onError: " + str);
            if (this.mProductDetailFragment.get() != null) {
                ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                Intrinsics.checkNotNull(productDetailFragment);
                if (productDetailFragment.isAttached()) {
                    ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment2);
                    productDetailFragment2.onLastPurchaseInfoResultForProduct(null);
                }
            }
        }
    }
    private Unit getLastCustomerSalesDateOfItemForProduct() {
        getLastPurchaseInfoForProduct();
        return Unit.INSTANCE;
    }
    private Unit getLastPurchaseInfoForProduct() {
        String code;
        if (ContextUtils.checkConnectionWithoutMessage()) {
            ProductDetail productDetail = this.productDetail;
            this.viewModel.getBaseErp().getLastPurchaseInfo((productDetail == null || (code = productDetail.getCode()) == null) ? null : new LastPurchaseInfoSqlParams(this.mItemLogicalRef, code, null, null, null, 28, null), new LastPurchaseInfoListenerForProduct(this));
        }
        return Unit.INSTANCE;
    } 
    public void onLastPurchaseInfoResultForProduct(List<? extends PurchasePriceInfo> list) {
        TextView textView;
        int i;
        int i2;
        int i3;
        TextView textView2;
        List emptyList;
        if (list == null || list.isEmpty()) {
            LinearLayout linearLayout = this.purchaseLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("purchaseLayout");
                linearLayout = null;
            }
            linearLayout.setVisibility(View.GONE);
            TextView textView3 = this.purchaseTextView;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("purchaseTextView");
                textView = null;
            } else {
                textView = textView3;
            }
            textView.setVisibility(View.GONE);
            return;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        float f = activity.getResources().getDisplayMetrics().density;
        int i4 = (int) (((float) 5) * f);
        int size = list.size();
        int i5 = 0;
        int i6 = 0;
        while (i6 < size) {
            if (this.viewModel.getBaseErp().showLastPurchaseInformation()) {
                String localCurrencyCode = this.viewModel.getBaseErp().getLocalCurrencyCode();
                TextView textView4 = new TextView(getActivity());
                TextView textView5 = new TextView(getActivity());
                if (list.get(i5).getPrCurr() != 0) {
                    String currCode = this.viewModel.getSqlHelper().getCurrCode(list.get(0).getPrCurr());
                    StringBuilder sb = new StringBuilder();
                    i2 = size;
                    sb.append(getString(R.string.str_purchase_date));
                    sb.append(" : ");
                    textView2 = textView5;
                    i = i6;
                    String format = String.format("%s / %s ", Arrays.copyOf(new Object[]{StringUtils.formatDoubleDynamicDigitsWithDot(list.get(0).getPrPrice(), 2), currCode}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    sb.append(format);
                    textView4.setText(sb.toString());
                } else {
                    i2 = size;
                    i = i6;
                    textView2 = textView5;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(getString(R.string.str_purchase_price));
                    sb2.append(" : ");
                    PrimitiveCompanionObjects stringCompanionObject2 = PrimitiveCompanionObjects.INSTANCE;
                    String format2 = String.format("%s / %s ", Arrays.copyOf(new Object[]{StringUtils.formatDoubleDynamicDigitsWithDot(list.get(0).getPrice(), 2), localCurrencyCode}, 2));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                    sb2.append(format2);
                    textView4.setText(sb2.toString());
                }
                i3 = 0;
                textView4.setPadding(0, i4, 0, i4);
                LinearLayout linearLayout2 = this.purchaseLayout;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("purchaseLayout");
                    linearLayout2 = null;
                }
                linearLayout2.addView(textView4);
                if (!TextUtils.isEmpty(list.get(0).getDate())) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(getString(R.string.str_purchase_date));
                    sb3.append(" : ");
                    String date = list.get(0).getDate();
                    Intrinsics.checkNotNullExpressionValue(date, "getDate(...)");
                    List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date, 0);
                    if (!split.isEmpty()) {
                        ListIterator<String> listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (listIterator.previous().length() != 0) {
                                CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList = CollectionsKt.emptyList();
                    i3 = 0;
                    sb3.append(DateAndTimeUtils.convertReportDateToSimpleDate(((String[]) emptyList.toArray(new String[0]))[0]));
                    textView2.setText(sb3.toString());
                    textView2.setPadding(0, i4, 0, i4);
                    LinearLayout linearLayout3 = this.purchaseLayout;
                    if (linearLayout3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("purchaseLayout");
                        linearLayout3 = null;
                    }
                    linearLayout3.addView(textView2);
                }
            } else {
                i2 = size;
                i3 = i5;
                i = i6;
                LinearLayout linearLayout4 = this.purchaseLayout;
                if (linearLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("purchaseLayout");
                    linearLayout4 = null;
                }
                linearLayout4.setVisibility(View.GONE);
                TextView textView6 = this.purchaseTextView;
                if (textView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("purchaseTextView");
                    textView6 = null;
                }
                textView6.setVisibility(View.GONE);
            }
            View view = new View(getActivity());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (f)));
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            view.setBackgroundColor(ContextCompat.getColor(activity2, R.color.colorDivider2));
            LinearLayout linearLayout5 = this.purchaseLayout;
            if (linearLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("purchaseLayout");
                linearLayout5 = null;
            }
            linearLayout5.addView(view);
            i6 = i + 1;
            i5 = i3;
            size = i2;
        }
        load();
    } 
    private void onPriceLoads(ArrayList<ItemPrice> arrayList) {
        TextView textView = null;
        if (arrayList == null || arrayList.isEmpty()) {
            LinearLayout linearLayout = this.priceLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
                linearLayout = null;
            }
            linearLayout.setVisibility(View.GONE);
            TextView textView2 = this.priceTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceTextView");
            } else {
                textView = textView2;
            }
            textView.setVisibility(View.GONE);
            return;
        }
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            Collections.sort(arrayList, new Comparator() {
                public int compare(Object obj, Object obj2) {
                    Function2 Function2 = null;
                    int i = ProductDetailFragment.onPriceLoadslambda3(Function2, obj, obj2);
                    int i1 = i;
                    return i1;
                }
            });
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        float f = activity.getResources().getDisplayMetrics().density;
        int i = (int) (((float) 5) * f);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            TextView textView3 = new TextView(getActivity());
            textView3.setText(getString(R.string.str_code) + " : " + arrayList.get(i2).getPriceCode());
            textView3.setPadding(0, i, 0, i);
            TextView textView4 = new TextView(getActivity());
            String sb = getString(R.string.str_price) +
                    " : " +
                    (TextUtils.isEmpty(arrayList.get(i2).getCPrice()) ? Double.valueOf(arrayList.get(i2).getPrice()) : arrayList.get(i2).getCPrice());
            textView4.setText(sb);
            textView4.setPadding(0, i, 0, i);
            LinearLayout linearLayout2 = this.priceLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
                linearLayout2 = null;
            }
            linearLayout2.addView(textView3);
            LinearLayout linearLayout3 = this.priceLayout;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
                linearLayout3 = null;
            }
            linearLayout3.addView(textView4);
            if (this.viewModel.erpType() != ErpType.NETSIS) {
                ItemPrice itemPrice = arrayList.get(i2);
                Intrinsics.checkNotNullExpressionValue(itemPrice, "get(...)");
                checkIfDefFieldsExistAddView(itemPrice, i);
            } else {
                ItemPrice itemPrice2 = arrayList.get(i2);
                Intrinsics.checkNotNullExpressionValue(itemPrice2, "get(...)");
                checkIfDefFieldsExistAddViewForNetsis(itemPrice2, i);
            }
            View view = new View(getActivity());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (f)));
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            view.setBackgroundColor(ContextCompat.getColor(activity2, R.color.colorDivider2));
            LinearLayout linearLayout4 = this.priceLayout;
            if (linearLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
                linearLayout4 = null;
            }
            linearLayout4.addView(view);
        }
    } 
    public static int onPriceLoadslambda3(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "tmp0");
        return ((Number) function2.invoke((CustomerGeneralFragment) obj, (Continuation<Object>) obj2)).intValue();
    }
    private void onUnitLoads(ArrayList<DetailItemUnit> arrayList) {
        TextView textView;
        if (arrayList == null || arrayList.isEmpty()) {
            LinearLayout linearLayout = this.unitLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                linearLayout = null;
            }
            linearLayout.setVisibility(View.GONE);
            TextView textView2 = this.unitText;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unitText");
                textView = null;
            } else {
                textView = textView2;
            }
            textView.setVisibility(View.GONE);
            return;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        float f = activity.getResources().getDisplayMetrics().density;
        int i = (int) (((float) 5) * f);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : arrayList) {
            String unitDesc = ((DetailItemUnit) obj).getUnitDesc();
            Object obj2 = linkedHashMap.get(unitDesc);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(unitDesc, obj2);
            }
            ((List) obj2).add(obj);
        }
        for (Object entry : linkedHashMap.entrySet()) {
            Class<?> list = entry.getClass();
            TextView textView3 = new TextView(getActivity());
            textView3.setText(list.getName() );
            textView3.setPadding(0, i, 0, i);
            LinearLayout linearLayout2 = this.unitLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                linearLayout2 = null;
            }
            linearLayout2.addView(textView3);
            if (this.viewModel.erpType() != ErpType.NETSIS) {
                TextView textView4 = new TextView(getActivity());
                PrimitiveCompanionObjects stringCompanionObject = PrimitiveCompanionObjects.INSTANCE;
                String format = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_width),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                textView4.setText(format);
                textView4.setPadding(0, i, 0, i);
                LinearLayout linearLayout3 = this.unitLayout;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout3 = null;
                }
                linearLayout3.addView(textView4);
                TextView textView5 = new TextView(getActivity());
                String format2 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_length),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                textView5.setText(format2);
                textView5.setPadding(0, i, 0, i);
                LinearLayout linearLayout4 = this.unitLayout;
                if (linearLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout4 = null;
                }
                linearLayout4.addView(textView5);
                TextView textView6 = new TextView(getActivity());
                String format3 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_height),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                textView6.setText(format3);
                textView6.setPadding(0, i, 0, i);
                LinearLayout linearLayout5 = this.unitLayout;
                if (linearLayout5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout5 = null;
                }
                linearLayout5.addView(textView6);
                TextView textView7 = new TextView(getActivity());
                String format4 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_area),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
                textView7.setText(format4);
                textView7.setPadding(0, i, 0, i);
                LinearLayout linearLayout6 = this.unitLayout;
                if (linearLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout6 = null;
                }
                linearLayout6.addView(textView7);
                TextView textView8 = new TextView(getActivity());
                String format5 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_gross_volume),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format5, "format(...)");
                textView8.setText(format5);
                textView8.setPadding(0, i, 0, i);
                LinearLayout linearLayout7 = this.unitLayout;
                if (linearLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout7 = null;
                }
                linearLayout7.addView(textView8);
                TextView textView9 = new TextView(getActivity());
                String format6 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_net_volume),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format6, "format(...)");
                textView9.setText(format6);
                textView9.setPadding(0, i, 0, i);
                LinearLayout linearLayout8 = this.unitLayout;
                if (linearLayout8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout8 = null;
                }
                linearLayout8.addView(textView9);
                TextView textView10 = new TextView(getActivity());
                String format7 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_gross_weight),  list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format7, "format(...)");
                textView10.setText(format7);
                textView10.setPadding(0, i, 0, i);
                LinearLayout linearLayout9 = this.unitLayout;
                if (linearLayout9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout9 = null;
                }
                linearLayout9.addView(textView10);
                TextView textView11 = new TextView(getActivity());
                String format8 = String.format("%s: %s", Arrays.copyOf(new Object[]{ContextUtils.getStringResource(R.string.str_net_weight), list.getName() }, 2));
                Intrinsics.checkNotNullExpressionValue(format8, "format(...)");
                textView11.setText(format8);
                textView11.setPadding(0, i, 0, i);
                LinearLayout linearLayout10 = this.unitLayout;
                if (linearLayout10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                    linearLayout10 = null;
                }
                linearLayout10.addView(textView11);
            }
            ArrayList arrayList2 = new ArrayList();
            for (DetailItemUnit detailItemUnit : list) {
                List<String> barcode = detailItemUnit.getBarcode();
                if (barcode == null) {
                    barcode = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(arrayList2, barcode);
            }
            int i2 = 0;
            for (Object obj3 : arrayList2) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj3;
                TextView textView12 = new TextView(getActivity());
                PrimitiveCompanionObjects stringCompanionObject2 = PrimitiveCompanionObjects.INSTANCE;
                String format9 = String.format(ContextUtils.getStringResource(R.string.str_barcode) + " %d: %s", Arrays.copyOf(new Object[]{Integer.valueOf(i3), str}, 2));
                Intrinsics.checkNotNullExpressionValue(format9, "format(...)");
                textView12.setText(format9);
                textView12.setPadding(0, i, 0, i);
                if (!(str == null || str.length() == 0)) {
                    LinearLayout linearLayout11 = this.unitLayout;
                    if (linearLayout11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                        linearLayout11 = null;
                    }
                    linearLayout11.addView(textView12);
                }
                i2 = i3;
            }
            View view = new View(getActivity());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (f)));
            view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.colorDivider2));
            LinearLayout linearLayout12 = this.unitLayout;
            if (linearLayout12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
                linearLayout12 = null;
            }
            linearLayout12.addView(view);
        }
        View view2 = new View(getActivity());
        view2.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (f)));
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        view2.setBackgroundColor(ContextCompat.getColor(activity2, R.color.colorDivider2));
        LinearLayout linearLayout13 = this.unitLayout;
        if (linearLayout13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("unitLayout");
            linearLayout13 = null;
        }
        linearLayout13.addView(view2);
    }
    private void checkIfDefFieldsExistAddViewForNetsis(ItemPrice itemPrice, int i) {
        String grpCode = itemPrice.getGrpCode();
        Intrinsics.checkNotNull(grpCode);
        boolean z = true;
        boolean z2 = false;
        if (grpCode.length() > 0) {
            String string = getString(R.string.str_group_code);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            addDefFieldViewToPrice(string, String.valueOf(itemPrice.getGrpCode()), i, false);
            z2 = true;
        }
        if (itemPrice.getBegDate() != 0) {
            String convertStringDate = DateAndTimeUtils.convertStringDate(String.valueOf(itemPrice.getBegDate()), "yyyyMMdd", "dd.MM.yyyy");
            String string2 = getString(R.string.str_begin_date);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            Intrinsics.checkNotNull(convertStringDate);
            addDefFieldViewToPrice(string2, convertStringDate, i, z2);
            z2 = true;
        }
        if (itemPrice.getEndDate() != 0) {
            String convertStringDate2 = DateAndTimeUtils.convertStringDate(String.valueOf(itemPrice.getEndDate()), "yyyyMMdd", "dd.MM.yyyy");
            String string3 = getString(R.string.str_end_date);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            Intrinsics.checkNotNull(convertStringDate2);
            addDefFieldViewToPrice(string3, convertStringDate2, i, z2);
        } else {
            z = z2;
        }
        String variantCode = itemPrice.getVariantCode();
        Intrinsics.checkNotNull(variantCode);
        if (variantCode.length() > 0) {
            String string4 = getString(R.string.str_variant);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            addDefFieldViewToPrice(string4, String.valueOf(itemPrice.getVariantCode()), i, z);
        }
    }
    private void checkIfDefFieldsExistAddView(ItemPrice itemPrice, int i) {
        boolean checkItemClSpeCodeForDefField = checkItemClSpeCodeForDefField(itemPrice, i, checkItemClientCodeForDefField(itemPrice, i, checkItemGrpCodeForDefField(itemPrice, i, checkItemShpTypeForDefField(itemPrice, i, checkItemCyphCodeForDefField(itemPrice, i, false)))));
        String clspeCode2 = itemPrice.getClspeCode2();
        Intrinsics.checkNotNull(clspeCode2);
        boolean z = true;
        if (clspeCode2.length() > 0) {
            String string = getString(R.string.str_customer_specode_2);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            addDefFieldViewToPrice(string, String.valueOf(itemPrice.getClspeCode2()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String clspeCode3 = itemPrice.getClspeCode3();
        Intrinsics.checkNotNull(clspeCode3);
        if (clspeCode3.length() > 0) {
            String string2 = getString(R.string.str_customer_specode_3);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            addDefFieldViewToPrice(string2, String.valueOf(itemPrice.getClspeCode3()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String clspeCode4 = itemPrice.getClspeCode4();
        Intrinsics.checkNotNull(clspeCode4);
        if (clspeCode4.length() > 0) {
            String string3 = getString(R.string.str_customer_specode_4);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            addDefFieldViewToPrice(string3, String.valueOf(itemPrice.getClspeCode4()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String clspeCode5 = itemPrice.getClspeCode5();
        Intrinsics.checkNotNull(clspeCode5);
        if (clspeCode5.length() > 0) {
            String string4 = getString(R.string.str_customer_specode_5);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            addDefFieldViewToPrice(string4, String.valueOf(itemPrice.getClspeCode5()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String cltradingGrp = itemPrice.getCltradingGrp();
        Intrinsics.checkNotNull(cltradingGrp);
        if (cltradingGrp.length() > 0) {
            String string5 = getString(R.string.str_customer_trading_group);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            addDefFieldViewToPrice(string5, String.valueOf(itemPrice.getCltradingGrp()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String clycphCode = itemPrice.getClycphCode();
        Intrinsics.checkNotNull(clycphCode);
        if (clycphCode.length() > 0) {
            String string6 = getString(R.string.str_client_auth_code);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            addDefFieldViewToPrice(string6, String.valueOf(itemPrice.getClycphCode()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        if (itemPrice.getPayPlanRef() != 0) {
            String paymentCodeFromSqlHelper = this.viewModel.getPaymentCodeFromSqlHelper(itemPrice);
            if (paymentCodeFromSqlHelper.length() == 0) {
                paymentCodeFromSqlHelper = String.valueOf(itemPrice.getPayPlanRef());
            }
            String string7 = getString(R.string.str_customer_pay_plan);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
            addDefFieldViewToPrice(string7, paymentCodeFromSqlHelper, i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String tradingGrp = itemPrice.getTradingGrp();
        Intrinsics.checkNotNull(tradingGrp);
        if (tradingGrp.length() > 0) {
            String string8 = getString(R.string.str_trade_group);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            addDefFieldViewToPrice(string8, String.valueOf(itemPrice.getTradingGrp()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        if (itemPrice.getLeadtime() != 0) {
            String string9 = getString(R.string.str_delivery_time);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
            addDefFieldViewToPrice(string9, String.valueOf(itemPrice.getLeadtime()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        if (itemPrice.getOrdernr() != 0) {
            String string10 = getString(R.string.str_ranking);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
            addDefFieldViewToPrice(string10, String.valueOf(itemPrice.getOrdernr()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        if (itemPrice.getPriority() != 0) {
            String string11 = getString(R.string.str_priority);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
            addDefFieldViewToPrice(string11, String.valueOf(itemPrice.getPriority()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        if (itemPrice.getMarkRef() != 0) {
            String string12 = getString(R.string.str_brand_code);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
            addDefFieldViewToPrice(string12, String.valueOf(itemPrice.getMarkRef()), i, checkItemClSpeCodeForDefField);
            checkItemClSpeCodeForDefField = true;
        }
        String variantCode = itemPrice.getVariantCode();
        Intrinsics.checkNotNull(variantCode);
        if (variantCode.length() > 0) {
            String string13 = getString(R.string.str_variant);
            Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
            addDefFieldViewToPrice(string13, String.valueOf(itemPrice.getVariantCode()), i, checkItemClSpeCodeForDefField);
        } else {
            z = checkItemClSpeCodeForDefField;
        }
        String definition = itemPrice.getDefinition();
        Intrinsics.checkNotNull(definition);
        if (definition.length() > 0) {
            String string14 = getString(R.string.str_explanation);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
            addDefFieldViewToPrice(string14, String.valueOf(itemPrice.getDefinition()), i, z);
        }
    }
    private boolean checkItemClSpeCodeForDefField(ItemPrice itemPrice, int i, boolean z) {
        String clspeCode = itemPrice.getClspeCode();
        Intrinsics.checkNotNull(clspeCode);
        if (clspeCode.length() <= 0) {
            return z;
        }
        String string = getString(R.string.str_customer_specode_1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addDefFieldViewToPrice(string, String.valueOf(itemPrice.getClspeCode()), i, z);
        return true;
    }
    private boolean checkItemClientCodeForDefField(ItemPrice itemPrice, int i, boolean z) {
        String clientCode = itemPrice.getClientCode();
        Intrinsics.checkNotNull(clientCode);
        if (clientCode.length() <= 0) {
            return z;
        }
        String string = getString(R.string.str_client_code);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addDefFieldViewToPrice(string, String.valueOf(itemPrice.getClientCode()), i, z);
        return true;
    }
    private boolean checkItemGrpCodeForDefField(ItemPrice itemPrice, int i, boolean z) {
        String grpCode = itemPrice.getGrpCode();
        Intrinsics.checkNotNull(grpCode);
        if (grpCode.length() <= 0) {
            return z;
        }
        String string = getString(R.string.str_group_code);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addDefFieldViewToPrice(string, String.valueOf(itemPrice.getGrpCode()), i, z);
        return true;
    }
    private boolean checkItemShpTypeForDefField(ItemPrice itemPrice, int i, boolean z) {
        String shipTyp = itemPrice.getShipTyp();
        Intrinsics.checkNotNull(shipTyp);
        if (shipTyp.length() <= 0) {
            return z;
        }
        String string = getString(R.string.str_delivery_code);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addDefFieldViewToPrice(string, String.valueOf(itemPrice.getShipTyp()), i, z);
        return true;
    }
    private boolean checkItemCyphCodeForDefField(ItemPrice itemPrice, int i, boolean z) {
        String cyphCode = itemPrice.getCyphCode();
        Intrinsics.checkNotNull(cyphCode);
        if (cyphCode.length() <= 0) {
            return z;
        }
        String string = getString(R.string.str_auth_code);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        addDefFieldViewToPrice(string, String.valueOf(itemPrice.getCyphCode()), i, z);
        return true;
    }
    private void addDefFieldViewToPrice(String str, String str2, int i, boolean z) {
        LinearLayout linearLayout = null;
        if (!z) {
            TextView textView = new TextView(getActivity());
            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(getString(R.string.str_definition_fields));
            textView.setPadding(0, i, 0, i);
            LinearLayout linearLayout2 = this.priceLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
                linearLayout2 = null;
            }
            linearLayout2.addView(textView);
        }
        TextView textView2 = new TextView(getActivity());
        textView2.setText(new StringBuilder(str + " : " + str2));
        textView2.setPadding(0, i, 0, i);
        LinearLayout linearLayout3 = this.priceLayout;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("priceLayout");
        } else {
            linearLayout = linearLayout3;
        }
        linearLayout.addView(textView2);
    } 
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private record ProductDetailInfoResponseListener(
            WeakReference<ProductDetailFragment> mProductDetailFragment) implements ResponseListener<ArrayList<ProductDetail>> {
            private ProductDetailInfoResponseListener(ProductDetailFragment mProductDetailFragment) {
                Intrinsics.checkNotNullParameter(mProductDetailFragment, "productDetailFragment");
                this.mProductDetailFragment = new WeakReference<>(mProductDetailFragment);
            }

            public void onResponse(PrintSlipModel arrayList) {
                if (this.mProductDetailFragment.get() != null) {
                    ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment);
                    if (productDetailFragment.isAttached()) {
                        ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                        Intrinsics.checkNotNull(productDetailFragment2);
                        productDetailFragment2.onProductLoads(arrayList);
                    }
                }
            }

        public void onError(String str) {
                Intrinsics.checkNotNullParameter(str, "errorMessage");
                if (this.mProductDetailFragment.get() != null) {
                    ProductDetailFragment productDetailFragment = this.mProductDetailFragment.get();
                    Intrinsics.checkNotNull(productDetailFragment);
                    if (productDetailFragment.isAttached()) {
                        ProductDetailFragment productDetailFragment2 = this.mProductDetailFragment.get();
                        Intrinsics.checkNotNull(productDetailFragment2);
                        productDetailFragment2.onProductsLoadError(str);
                    }
                }
            }
        }
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
}
