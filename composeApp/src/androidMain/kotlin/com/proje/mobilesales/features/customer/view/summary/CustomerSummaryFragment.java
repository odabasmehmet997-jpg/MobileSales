package com.proje.mobilesales.features.customer.view.summary;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.cardview.widget.CardView;
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
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.FragmentCustomerSummaryBinding;
import com.proje.mobilesales.features.customer.model.CustomerBalance;
import com.proje.mobilesales.features.customer.repository.CustomerSummaryRepository;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.model.CurrencyBalanceModel;
import com.proje.mobilesales.features.model.MonthlySales;
import com.proje.mobilesales.features.model.TopProduct;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public final class CustomerSummaryFragment extends BaseFragment {
    public static final String EXTRA_CUSTOMER_REF = "extra:customerRef";
    public static final String STATE_CONNECTED = "state:connected";
    public static final String STATE_CUSTOMER_REF = "state:customerRef";
    public static final String STATE_REFRESH = "state:refresh";
    public static final String STATE_SALES_WEEKLY = "state:salesWeekly";
    private FragmentCustomerSummaryBinding _binding;
    private FrameLayout chartFrame1;
    private FrameLayout chartFrame2;
    private boolean isHideCustomerBalance;
    private BarChart mChartHorizontalTopTenProduct;
    private BarChart mChartMonthlySales;
    private AppCompatCheckedTextView mChkedTxtViewSalesWeekly;
    private boolean mConnected;
    private RelativeLayout mContentFrame;
    private List<CustomerBalance> mCustomerBalances;
    private EmptyListBinding mEmptyView;
    private LinearLayout mLnCustomerBalanceTable;
    private int mLogicalRef;
    private List<? extends MonthlySales> mMonthlySalesList;
    private boolean[] mRefresh;
    private RelativeLayout mRltCurrency;
    private RelativeLayout mRltCurrencyContent;
    private RelativeLayout mRltSales;
    private RelativeLayout mRltTopTenProduct;
    private boolean mSalesWeekly;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private List<? extends TopProduct> mTopProducts;
    private TextView mTxtCustomerBalanceTotal;
    private TextView mTxtCustomerInvoiceTotal;
    private TextView mTxtCustomerOrderTotal;
    private TextView mTxtCustomerTotal;
    private int maxCharCount;
    private final CustomerSummaryRepository repository;
    private final CustomerSummaryViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public CustomerSummaryFragment() {
        CustomerSummaryRepository customerSummaryRepository = new CustomerSummaryRepository();
        this.repository = customerSummaryRepository;
        this.viewModel = new CustomerSummaryViewModel(customerSummaryRepository);
    }

    private FragmentCustomerSummaryBinding getBinding() {
        FragmentCustomerSummaryBinding fragmentCustomerSummaryBinding = this._binding;
        Intrinsics.checkNotNull(fragmentCustomerSummaryBinding);
        return fragmentCustomerSummaryBinding;
    }

    @Override // com.proje.mobilesales.core.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (bundle != null) {
            this.mLogicalRef = bundle.getInt("state:customerRef");
            this.mSalesWeekly = bundle.getBoolean(STATE_SALES_WEEKLY);
            this.mRefresh = bundle.getBooleanArray(STATE_REFRESH);
            this.mConnected = bundle.getBoolean(STATE_CONNECTED);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mLogicalRef = arguments.getInt("extra:customerRef");
            this.mRefresh = new boolean[4];
            this.mSalesWeekly = true;
            this.mConnected = isConnected(getContext());
        }
        this.isHideCustomerBalance = this.viewModel.getBaseErp().isHideCustomerBalance();
    } 
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentCustomerSummaryBinding.inflate(inflater, viewGroup, false);
        this.mSwipeRefreshLayout = getBinding().swipeLayout;
        this.mEmptyView = getBinding().empty;
        this.mContentFrame = getBinding().contentFrame;
        LinearLayout linearLayout = getBinding().lnCustomerBalanceTable;
        this.mLnCustomerBalanceTable = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.GONE);
        this.mTxtCustomerTotal = getBinding().txtCustomerTotal;
        this.mTxtCustomerInvoiceTotal = getBinding().txtCustomerInvoiceTotal;
        this.mTxtCustomerOrderTotal = getBinding().txtCustomerOrderTotal;
        this.mTxtCustomerBalanceTotal = getBinding().txtCustomerBalanceTotal;
        this.mRltCurrency = getBinding().rltExtractCurrencyBalance;
        this.mRltCurrencyContent = getBinding().rltCurrencyContent;
        RelativeLayout relativeLayout = this.mRltCurrency;
        Intrinsics.checkNotNull(relativeLayout);
        relativeLayout.setVisibility(View.GONE);
        RelativeLayout relativeLayout2 = getBinding().rltCustomerTop10Product;
        this.mRltTopTenProduct = relativeLayout2;
        Intrinsics.checkNotNull(relativeLayout2);
        relativeLayout2.setVisibility(View.GONE);
        this.chartFrame1 = getBinding().chartFrame;
        this.chartFrame2 = getBinding().chartFrame2;
        BarChart barChart = new BarChart(getActivity());
        this.mChartHorizontalTopTenProduct = barChart;
        Intrinsics.checkNotNull(barChart);
        initHorizontalChart(barChart, this.chartFrame1);
        RelativeLayout relativeLayout3 = getBinding().rltCustomerSales;
        this.mRltSales = relativeLayout3;
        Intrinsics.checkNotNull(relativeLayout3);
        relativeLayout3.setVisibility(View.GONE);
        BarChart barChart2 = new BarChart(getActivity());
        this.mChartMonthlySales = barChart2;
        Intrinsics.checkNotNull(barChart2);
        initHorizontalChart(barChart2, this.chartFrame2);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
        appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout3);
        appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                CustomerSummaryFragment.onCreateViewlambda0(CustomerSummaryFragment.this);
            }
        });
        AppCompatCheckedTextView appCompatCheckedTextView = getBinding().txtCustomerSalesTitle;
        this.mChkedTxtViewSalesWeekly = appCompatCheckedTextView;
        Intrinsics.checkNotNull(appCompatCheckedTextView);
        appCompatCheckedTextView.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View view) {
                CustomerSummaryFragment.onCreateViewlambda1(CustomerSummaryFragment.this, view);
            }
        });
        toggleEmptyView();
        RelativeLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    } 
    public static void onCreateViewlambda0(CustomerSummaryFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.mLogicalRef < 0) {
            return;
        }
        this0.load();
    } 
    public static void onCreateViewlambda1(CustomerSummaryFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        boolean z = this0.mSalesWeekly;
        this0.mSalesWeekly = !z;
        int i2 = z ? R.string.str_customer_summary_monthly_sales : R.string.str_customer_summary_weekly_sales;
        AppCompatCheckedTextView appCompatCheckedTextView = this0.mChkedTxtViewSalesWeekly;
        Intrinsics.checkNotNull(appCompatCheckedTextView);
        appCompatCheckedTextView.setText(this0.getString(i2));
        this0.loadSalesData();
    }

    private void toggleEmptyView() {
        if (!this.mConnected) {
            RelativeLayout relativeLayout = this.mContentFrame;
            Intrinsics.checkNotNull(relativeLayout);
            relativeLayout.setVisibility(View.GONE);
            EmptyListBinding emptyListBinding = this.mEmptyView;
            Intrinsics.checkNotNull(emptyListBinding);
            emptyListBinding.getRoot().setVisibility(View.VISIBLE);
            return;
        }
        RelativeLayout relativeLayout2 = this.mContentFrame;
        Intrinsics.checkNotNull(relativeLayout2);
        relativeLayout2.setVisibility(View.VISIBLE);
        EmptyListBinding emptyListBinding2 = this.mEmptyView;
        Intrinsics.checkNotNull(emptyListBinding2);
        emptyListBinding2.getRoot().setVisibility(View.GONE);
    }

    private void initHorizontalChart(BarChart barChart, FrameLayout frameLayout) {
        Intrinsics.checkNotNull(frameLayout);
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

    private void setFrameHeight(FrameLayout frameLayout, int i2) {
        int i3 = i2 * 25;
        if (i3 < 1000) {
            i3 = 500;
        } else if (i3 > 1000) {
            i3 = 1000;
        }
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i3, getResources().getDisplayMetrics());
        Log.i("CHART", "initHorizontalChart: " + this.maxCharCount + ' ' + frameLayout.getLayoutParams().height);
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mConnected) {
            load();
        }
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putInt("state:customerRef", this.mLogicalRef);
        outState.putBooleanArray(STATE_REFRESH, this.mRefresh);
        outState.putBoolean(STATE_SALES_WEEKLY, this.mSalesWeekly);
        outState.putBoolean(STATE_CONNECTED, this.mConnected);
    }

    private void load() {
        loadBalanceData();
        loadTopTenProduct();
        loadSalesData();
        loadExtractCurrency();
    }

    private void loadBalanceData() {
        setmRefresh(0, false);
        this.viewModel.getCustomerSummaryTotalBalance(this.mLogicalRef, customerTotalListener(this));
    }

    private void loadTopTenProduct() {
        setmRefresh(1, false);
        this.viewModel.getCustomerSummaryTopTenProduct(this.mLogicalRef, customerTopTenProductListener(this));
    }

    private void loadSalesData() {
        setmRefresh(2, false);
        this.viewModel.getCustomerSummarySales(this.mLogicalRef, this.mSalesWeekly, customerSalesProductListener(this));
    }

    private void loadExtractCurrency() {
        setmRefresh(3, false);
        this.viewModel.getLoadCurrencyBalances(this.mLogicalRef, DateAndTimeUtils.getFirstDayOfYearMDY(), DateAndTimeUtils.getLastDayOfYearMDY(), customerCurrencyBalanceListener(this));
    }

    private void setmRefresh(int i2, boolean z) {
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        zArr[i2] = z;
    }

    private void bindBalanceData() {
        if (this.mCustomerBalances == null) {
            return;
        }
        if (this.isHideCustomerBalance) {
            LinearLayout linearLayout = this.mLnCustomerBalanceTable;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            List<CustomerBalance> list = this.mCustomerBalances;
            Intrinsics.checkNotNull(list);
            Iterator<CustomerBalance> it = list.iterator();
            while (it.hasNext()) {
                String typ = it.next().getTyp();
                if (typ != null) {
                    int hashCode = typ.hashCode();
                    if (hashCode != -1616785651) {
                        if (hashCode != 75468590) {
                            if (hashCode == 378796732 && typ.equals("BALANCE")) {
                                TextView textView = this.mTxtCustomerTotal;
                                Intrinsics.checkNotNull(textView);
                                textView.setText("-");
                                TextView textView2 = this.mTxtCustomerInvoiceTotal;
                                Intrinsics.checkNotNull(textView2);
                                textView2.setText("-");
                                TextView textView3 = this.mTxtCustomerOrderTotal;
                                Intrinsics.checkNotNull(textView3);
                                textView3.setText("-");
                            }
                        } else if (typ.equals(DailyInfo.ORDER)) {
                            TextView textView4 = this.mTxtCustomerTotal;
                            Intrinsics.checkNotNull(textView4);
                            textView4.setText("-");
                            TextView textView22 = this.mTxtCustomerInvoiceTotal;
                            Intrinsics.checkNotNull(textView22);
                            textView22.setText("-");
                            TextView textView32 = this.mTxtCustomerOrderTotal;
                            Intrinsics.checkNotNull(textView32);
                            textView32.setText("-");
                        }
                    } else if (typ.equals("INVOICE")) {
                        TextView textView42 = this.mTxtCustomerTotal;
                        Intrinsics.checkNotNull(textView42);
                        textView42.setText("-");
                        TextView textView222 = this.mTxtCustomerInvoiceTotal;
                        Intrinsics.checkNotNull(textView222);
                        textView222.setText("-");
                        TextView textView322 = this.mTxtCustomerOrderTotal;
                        Intrinsics.checkNotNull(textView322);
                        textView322.setText("-");
                    }
                }
            }
            TextView textView5 = this.mTxtCustomerBalanceTotal;
            Intrinsics.checkNotNull(textView5);
            textView5.setText("-");
            return;
        }
        LinearLayout linearLayout2 = this.mLnCustomerBalanceTable;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setVisibility(View.VISIBLE);
        List<CustomerBalance> list2 = this.mCustomerBalances;
        Intrinsics.checkNotNull(list2);
        double d2 = 0.0d;
        for (CustomerBalance customerBalance : list2) {
            d2 += customerBalance.getTotal();
            String replacedefault = StringsKt.replace(StringUtils.convertStringCustomerDebitCreditText(customerBalance.getTotal()), "-", "", false);
            String typ2 = customerBalance.getTyp();
            if (typ2 != null) {
                int hashCode2 = typ2.hashCode();
                if (hashCode2 != -1616785651) {
                    if (hashCode2 != 75468590) {
                        if (hashCode2 == 378796732 && typ2.equals("BALANCE")) {
                            TextView textView6 = this.mTxtCustomerTotal;
                            Intrinsics.checkNotNull(textView6);
                            textView6.setText(replacedefault);
                        }
                    } else if (typ2.equals(DailyInfo.ORDER)) {
                        TextView textView7 = this.mTxtCustomerOrderTotal;
                        Intrinsics.checkNotNull(textView7);
                        textView7.setText(replacedefault);
                    }
                } else if (typ2.equals("INVOICE")) {
                    TextView textView8 = this.mTxtCustomerInvoiceTotal;
                    Intrinsics.checkNotNull(textView8);
                    textView8.setText(replacedefault);
                }
            }
        }
        TextView textView9 = this.mTxtCustomerBalanceTotal;
        Intrinsics.checkNotNull(textView9);
        textView9.setText(StringsKt.replace(StringUtils.convertStringCustomerDebitCreditText(d2), "-", "", false));
    }

    private void bindProductData() {
        List<? extends TopProduct> list = this.mTopProducts;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                RelativeLayout relativeLayout = this.mRltTopTenProduct;
                Intrinsics.checkNotNull(relativeLayout);
                int i2 = 0;
                relativeLayout.setVisibility(View.VISIBLE);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                this.maxCharCount = 0;
                List<? extends TopProduct> list2 = this.mTopProducts;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                while (true) {
                    String str = "";
                    if (i2 >= size) {
                        break;
                    }
                    List<? extends TopProduct> list3 = this.mTopProducts;
                    Intrinsics.checkNotNull(list3);
                    TopProduct topProduct = list3.get(i2);
                    if (topProduct.getName() != null) {
                        str = topProduct.getName();
                        Intrinsics.checkNotNullExpressionValue(str, "getName(...)");
                    }
                    String total = topProduct.getTotal();
                    Intrinsics.checkNotNullExpressionValue(total, "getTotal(...)");
                    arrayList2.add(i2, new BarEntry(Float.parseFloat(total), i2, str));
                    arrayList.add(str);
                    if (str.length() > this.maxCharCount) {
                        this.maxCharCount = str.length();
                    }
                    i2++;
                }
                List<? extends TopProduct> list4 = this.mTopProducts;
                Intrinsics.checkNotNull(list4);
                if (list4.size() < 10) {
                    List<? extends TopProduct> list5 = this.mTopProducts;
                    Intrinsics.checkNotNull(list5);
                    for (int size2 = list5.size(); size2 < 10; size2++) {
                        arrayList2.add(size2, new BarEntry(0.0f, size2, ""));
                        arrayList.add("");
                    }
                }
                BarDataSet barDataSet = new BarDataSet(arrayList2, getString(R.string.activity_title_product));
                barDataSet.setBarBorderWidth(1.0f);
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData = new BarData(arrayList, barDataSet);
                setFrameHeight(this.chartFrame1, this.maxCharCount);
                BarChart barChart = this.mChartHorizontalTopTenProduct;
                Intrinsics.checkNotNull(barChart);
                barChart.setData(barData);
                BarChart barChart2 = this.mChartHorizontalTopTenProduct;
                Intrinsics.checkNotNull(barChart2);
                barChart2.animateXY(2000, 2000);
                BarChart barChart3 = this.mChartHorizontalTopTenProduct;
                Intrinsics.checkNotNull(barChart3);
                barChart3.invalidate();
                return;
            }
        }
        RelativeLayout relativeLayout2 = this.mRltTopTenProduct;
        Intrinsics.checkNotNull(relativeLayout2);
        relativeLayout2.setVisibility(View.GONE);
    }

    private void bindSalesData() {
        BarDataSet barDataSet;
        String dtime;
        List<? extends MonthlySales> list = this.mMonthlySalesList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                RelativeLayout relativeLayout = this.mRltSales;
                Intrinsics.checkNotNull(relativeLayout);
                relativeLayout.setVisibility(View.VISIBLE);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                this.maxCharCount = 0;
                List<? extends MonthlySales> list2 = this.mMonthlySalesList;
                Intrinsics.checkNotNull(list2);
                int size = list2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    List<? extends MonthlySales> list3 = this.mMonthlySalesList;
                    Intrinsics.checkNotNull(list3);
                    MonthlySales monthlySales = list3.get(i2);
                    if (monthlySales.getDtime() == null) {
                        dtime = "";
                    } else {
                        dtime = monthlySales.getDtime();
                        Intrinsics.checkNotNullExpressionValue(dtime, "getDtime(...)");
                    }
                    String totat = monthlySales.getTotat();
                    Intrinsics.checkNotNullExpressionValue(totat, "getTotat(...)");
                    arrayList2.add(i2, new BarEntry(Float.parseFloat(totat), i2, dtime));
                    arrayList.add(dtime);
                    if (dtime.length() > this.maxCharCount) {
                        this.maxCharCount = dtime.length();
                    }
                }
                if (this.mSalesWeekly) {
                    barDataSet = new BarDataSet(arrayList2, getString(R.string.str_customer_summary_weekly_sales));
                } else {
                    barDataSet = new BarDataSet(arrayList2, getString(R.string.str_customer_summary_monthly_sales));
                }
                barDataSet.setBarBorderWidth(1.0f);
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                BarData barData = new BarData(arrayList, barDataSet);
                setFrameHeight(this.chartFrame2, this.maxCharCount);
                BarChart barChart = this.mChartMonthlySales;
                Intrinsics.checkNotNull(barChart);
                barChart.setData(barData);
                BarChart barChart2 = this.mChartMonthlySales;
                Intrinsics.checkNotNull(barChart2);
                barChart2.getXAxis().setLabelsToSkip(0);
                BarChart barChart3 = this.mChartMonthlySales;
                Intrinsics.checkNotNull(barChart3);
                barChart3.animateXY(2000, 2000);
                BarChart barChart4 = this.mChartMonthlySales;
                Intrinsics.checkNotNull(barChart4);
                barChart4.invalidate();
                return;
            }
        }
        RelativeLayout relativeLayout2 = this.mRltSales;
        Intrinsics.checkNotNull(relativeLayout2);
        relativeLayout2.setVisibility(View.GONE);
    }
    public void onBalanceLoaded(List<CustomerBalance> list) {
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        zArr[0] = true;
        disableRefresh();
        if (list != null) {
            this.mCustomerBalances = list;
            bindBalanceData();
        }
    }
    public void onTopProductsLoaded(List<? extends TopProduct> list) {
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        zArr[1] = true;
        disableRefresh();
        if (list != null) {
            this.mTopProducts = list;
            bindProductData();
        }
    }
    public void onSalesWeeklyOrMonthlyLoaded(List<? extends MonthlySales> list) {
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        zArr[2] = true;
        disableRefresh();
        if (list != null) {
            this.mMonthlySalesList = list;
            bindSalesData();
        }
    }
    public void onCurrencyBalancesLoaded(List<? extends CurrencyBalanceModel> list) {
        CardView cardView;
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        zArr[3] = true;
        disableRefresh();
        if (list != null && !list.isEmpty()) {
            HashMap<String, CurrencyBalanceModel> hashMap = new HashMap<>();
            ArrayList<CurrencyBalanceModel> arrayList = new ArrayList();
            setResultModel(list, hashMap);
            Collection<CurrencyBalanceModel> values = hashMap.values();
            Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
            arrayList.addAll(values);
            RelativeLayout relativeLayout = this.mRltCurrencyContent;
            Intrinsics.checkNotNull(relativeLayout);
            relativeLayout.removeAllViews();
            int i2 = -1;
            int i3 = -1;
            for (CurrencyBalanceModel currencyBalanceModel : arrayList) {
                RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
                int generateViewId = Companion.generateViewId();
                relativeLayout2.setId(generateViewId);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, -2);
                if (i3 == i2) {
                    i3 = R.id.txt_extractCurrencyTitle;
                }
                layoutParams.addRule(3, i3);
                relativeLayout2.setLayoutParams(layoutParams);
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_currency_balance, null);
                Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.cardview.widget.CardView");
                CardView cardView2 = (CardView) inflate;
                TextView textView = cardView2.findViewById(R.id.tv_idCurrency);
                TextView textView2 = cardView2.findViewById(R.id.tv_idBalance);
                TextView textView3 = cardView2.findViewById(R.id.tv_idDebit);
                TextView textView4 = cardView2.findViewById(R.id.tv_tcCredit);
                TextView textView5 = cardView2.findViewById(R.id.tv_lcBalance);
                TextView textView6 = cardView2.findViewById(R.id.tv_rcBalance);
                if (this.isHideCustomerBalance) {
                    textView.setText("-");
                    textView2.setText("-");
                    textView3.setText("-");
                    textView4.setText("-");
                    textView5.setText("-");
                    textView6.setText("-");
                    cardView = cardView2;
                } else {
                    textView.setText(currencyBalanceModel != null ? currencyBalanceModel.getCurrCode() : null);
                    StringBuilder sb = new StringBuilder();
                    Intrinsics.checkNotNull(currencyBalanceModel);
                    sb.append(getFirstWord(currencyBalanceModel.getTransactionSum()));
                    cardView = cardView2;
                    sb.append(getSecondWordTransaction(currencyBalanceModel.getTransactionDebit(), currencyBalanceModel.getTransactionCredit()));
                    textView2.setText(sb.toString());
                    textView3.setText(getFirstWord(currencyBalanceModel.getTransactionDebit()));
                    textView4.setText(getFirstWord(currencyBalanceModel.getTransactionCredit()));
                    textView5.setText(getFirstWord(currencyBalanceModel.getLocalSum()) + getSecondWordCurrencyBalance(currencyBalanceModel.getLocalSum(), 0.0d));
                    textView6.setText(getFirstWord(currencyBalanceModel.getReportSum()) + getSecondWordCurrencyBalance(currencyBalanceModel.getReportSum(), 0.0d));
                }
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.setMargins(10, 5, 10, 5);
                layoutParams2.addRule(3, relativeLayout2.getId());
                View view = cardView;
                view.setLayoutParams(layoutParams2);
                relativeLayout2.addView(view);
                RelativeLayout relativeLayout3 = this.mRltCurrencyContent;
                Intrinsics.checkNotNull(relativeLayout3);
                relativeLayout3.addView(relativeLayout2);
                i2 = -1;
                i3 = generateViewId;
            }
            RelativeLayout relativeLayout4 = this.mRltCurrency;
            Intrinsics.checkNotNull(relativeLayout4);
            relativeLayout4.setVisibility(View.VISIBLE);
            return;
        }
        RelativeLayout relativeLayout5 = this.mRltCurrency;
        Intrinsics.checkNotNull(relativeLayout5);
        relativeLayout5.setVisibility(View.GONE);
    }

    private String getFirstWord(double d2) {
        return d2 == 0.0d ? "" : StringUtils.dFormat(Math.abs(d2));
    }

    private String getSecondWordCurrencyBalance(double d2, double d3) {
        return d2 > d3 ? " (A)" : " (B)";
    }

    private String getSecondWordTransaction(double d2, double d3) {
        return d2 > d3 ? " (B)" : " (A)";
    }

    private void setResultModel(List<? extends CurrencyBalanceModel> list, HashMap<String, CurrencyBalanceModel> hashMap) {
        for (CurrencyBalanceModel currencyBalanceModel : list) {
            CurrencyBalanceModel currencyBalanceModel2 = new CurrencyBalanceModel();
            if (hashMap.containsKey(currencyBalanceModel.getCurrCode())) {
                currencyBalanceModel2 = hashMap.get(currencyBalanceModel.getCurrCode());
                Intrinsics.checkNotNull(currencyBalanceModel2);
                currencyBalanceModel2.setLocalSum(currencyBalanceModel2.getLocalSum() + (currencyBalanceModel.getLocalSum() * currencyBalanceModel.getSign()));
                currencyBalanceModel2.setReportSum(currencyBalanceModel2.getReportSum() + (currencyBalanceModel.getReportSum() * currencyBalanceModel.getSign()));
            } else {
                currencyBalanceModel2.setCurrCode(currencyBalanceModel.getCurrCode());
                currencyBalanceModel2.setReportSum(currencyBalanceModel.getReportSum() * currencyBalanceModel.getSign());
                currencyBalanceModel2.setLocalSum(currencyBalanceModel.getLocalSum() * currencyBalanceModel.getSign());
            }
            if (currencyBalanceModel.getSign() == -1) {
                currencyBalanceModel2.setTransactionDebit(currencyBalanceModel.getTransactionSum());
            } else {
                currencyBalanceModel2.setTransactionCredit(currencyBalanceModel.getTransactionSum());
            }
            currencyBalanceModel2.setTransactionSum(currencyBalanceModel2.getTransactionDebit() - currencyBalanceModel2.getTransactionCredit());
            String currCode = currencyBalanceModel2.getCurrCode();
            Intrinsics.checkNotNullExpressionValue(currCode, "getCurrCode(...)");
            hashMap.put(currCode, currencyBalanceModel2);
        }
    }

    private void disableRefresh() {
        boolean[] zArr = this.mRefresh;
        Intrinsics.checkNotNull(zArr);
        if (zArr[0]) {
            boolean[] zArr2 = this.mRefresh;
            Intrinsics.checkNotNull(zArr2);
            if (zArr2[1]) {
                boolean[] zArr3 = this.mRefresh;
                Intrinsics.checkNotNull(zArr3);
                if (zArr3[2]) {
                    boolean[] zArr4 = this.mRefresh;
                    Intrinsics.checkNotNull(zArr4);
                    if (zArr4[3]) {
                        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
                        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
                        appBarSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        }
    }
     private ResponseListener<List<CustomerBalance>> customerTotalListener(final CustomerSummaryFragment customerSummaryFragment) {
        return new ResponseListener<List<? extends CustomerBalance>>(customerSummaryFragment) {
            private final WeakReference<CustomerSummaryFragment> mCustomerSummaryFragment;

            {
                this.mCustomerSummaryFragment = new WeakReference<>(customerSummaryFragment);
            }
 
            public   void onResponse(PrintSlipModel list) {
                onResponse2((List<CustomerBalance>) list);
            }
 
            public void onResponse2(List<CustomerBalance> list) {
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onBalanceLoaded(list);
                    }
                }
            }
 
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onBalanceLoaded(null);
                    }
                }
            }
        };
    }
     private ResponseListener<List<TopProduct>> customerTopTenProductListener(final CustomerSummaryFragment customerSummaryFragment) {
        return new ResponseListener<List<? extends TopProduct>>(customerSummaryFragment) { 
            private final WeakReference<CustomerSummaryFragment> mCustomerSummaryFragment;
            {
                this.mCustomerSummaryFragment = new WeakReference<>(customerSummaryFragment);
            } 
            public void onResponse(PrintSlipModel list) {
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onTopProductsLoaded(list);
                    }
                }
            }
 
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onTopProductsLoaded(null);
                    }
                }
            }
        };
    }
     private ResponseListener<List<MonthlySales>> customerSalesProductListener(final CustomerSummaryFragment customerSummaryFragment) {
        return new ResponseListener<List<? extends MonthlySales>>(customerSummaryFragment) {
            private final WeakReference<CustomerSummaryFragment> mCustomerSummaryFragment;
            {
                this.mCustomerSummaryFragment = new WeakReference<>(customerSummaryFragment);
            } 
            public void onResponse(PrintSlipModel list) {
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onSalesWeeklyOrMonthlyLoaded(list);
                    }
                }
            }
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onSalesWeeklyOrMonthlyLoaded(null);
                    }
                }
            }
        };
    }
     private ResponseListener<List<? extends CurrencyBalanceModel>> customerCurrencyBalanceListener(final CustomerSummaryFragment customerSummaryFragment) {
        return new ResponseListener<List<? extends CurrencyBalanceModel>>(customerSummaryFragment) {
            private final WeakReference<CustomerSummaryFragment> mCustomerSummaryFragment;

            {
                this.mCustomerSummaryFragment = new WeakReference<>(customerSummaryFragment);
            }
            public void onResponse(PrintSlipModel list) {
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onCurrencyBalancesLoaded(list);
                    }
                }
            } 
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerSummaryFragment.get() != null) {
                    CustomerSummaryFragment customerSummaryFragment2 = this.mCustomerSummaryFragment.get();
                    Intrinsics.checkNotNull(customerSummaryFragment2);
                    if (customerSummaryFragment2.isAttached()) {
                        CustomerSummaryFragment customerSummaryFragment3 = this.mCustomerSummaryFragment.get();
                        Intrinsics.checkNotNull(customerSummaryFragment3);
                        customerSummaryFragment3.onCurrencyBalancesLoaded(null);
                    }
                }
            }
        };
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
        public int generateViewId() {
            int i2;
            int i3;
            do {
                i2 = CustomerSummaryFragment.sNextGeneratedId.get();
                i3 = i2 + 1;
                if (i3 > 16777215) {
                    i3 = 1;
                }
            } while (!CustomerSummaryFragment.sNextGeneratedId.compareAndSet(i2, i3));
            return i2;
        }
    }
}
