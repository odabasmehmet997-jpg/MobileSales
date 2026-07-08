package com.proje.mobilesales.features.product.view.list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.integration.android.IntentIntegrator;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseListFragment;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.ProductListShowType;
import com.proje.mobilesales.core.enums.ProductTreeGroupListType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.AsyncReportResponse;
import com.proje.mobilesales.core.interfaces.GetLoaderSqlText;
import com.proje.mobilesales.core.interfaces.ItemSurplusDiscountListener;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.RecyclerViewClickListener;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.LogoTigerTableName;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.CounterFab;
import com.proje.mobilesales.core.view.RecyclerViewTouchListener;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.core.widget.SlideInLeftAnimator;
import com.proje.mobilesales.databinding.EmptyListBinding;
import com.proje.mobilesales.databinding.EmptySearchBinding;
import com.proje.mobilesales.databinding.FragmentProductListBinding;
import com.proje.mobilesales.features.activity.BarcodeScannerView;
import com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter;
import com.proje.mobilesales.features.adapter.SurplusDiscountListAdapter;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductGroupModel;
import com.proje.mobilesales.features.product.model.ProductInfo;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.ProductOperationDiscount;
import com.proje.mobilesales.features.product.model.ProductTreeItem;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import com.proje.mobilesales.features.product.repository.ProductListRepository;
import com.proje.mobilesales.features.product.view.detail.ProductDetailFragment;
import com.proje.mobilesales.features.product.view.tree.ProductTreeRecyclerViewAdapter;
import com.proje.mobilesales.features.reports.view.fragment.ItemReportsFragment;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailActivity;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class ProductListFragment extends BaseListFragment implements SearchView.OnQueryTextListener, GetLoaderSqlText, ProductRecyclerViewAdapter.ItemCountListener, AsyncReportResponse, ItemSurplusDiscountListener, ProductTreeRecyclerViewAdapter.ProductTreeItemSelectedListener {
    public static final String EXTRA_BARCODE_FILTER;
    public static final String EXTRA_DEF_ORDER_ID;
    public static final String EXTRA_DIVISION_NR;
    public static final String EXTRA_FILTER;
    public static final String EXTRA_PAYMENT_REF;
    public static final String EXTRA_PAYMENT_TRADE_GROUP;
    public static final String EXTRA_PRODUCT_GROUP_CODE;
    public static final String EXTRA_PRODUCT_SELECT_TYPE;
    public static final String EXTRA_SALES_TYPE;
    public static final String EXTRA_SELECTED_PRODUCT_SIZE;
    public static final String EXTRA_SOURCE_WAREHOUSE_NR;
    public static final String EXTRA_SPECODE;
    public static final String EXTRA_WAREHOUSE_NR;
    private static final String TAG = "ProductListFragment";
    private static final String productListFragmentClassName;
    private FragmentProductListBinding _binding;
    private boolean finishAddProduct;
    private View frameForm;
    private View frameGroup;
    private AppCompatSpinner frmGroupSpinner;
    private AppCompatSpinner frmSpinner;
    private boolean isBarcodeSearch;
    public ActionViewResolver mActionViewResolver;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private ArrayList<BarcodeResult> mBarcodeFilter;
    private BarcodeItem mBarcodeItem;
    private MenuItem mBarcodeMenuItem;
    private boolean mBarcodeSingleSelect;
    private int mCustomerRef;
    private int mDefOrderId;
    private int mDivisionNr;
    private MenuItem mDoneMenuItem;
    private EmptySearchBinding mEmptySearchView;
    private EmptyListBinding mEmptyView;
    private CounterFab mFabCheckAll;
    private CounterFab mFabDone;
    private String mFilter;
    private MenuItem mFilterByGroup;
    private FrameLayout mFrameCheckAllFab;
    private FrameLayout mFrameFab;
    private int mPaymentRef;
    private String mPaymentTradeGroup;
    private boolean mPriceGet;
    private MenuItem mPrintMenuItem;
    private String mProductGroupCode;
    private ProductListParameter mProductListParameter;
    private ProductListShowType mProductListShowType;
    private boolean mProductSelectType;
    private RecyclerView mProductTreeRecycleView;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    public ProgressDialogBuilder<?> mProgressSurplusDialogBuilder;
    private int mReportItemRef;
    private MenuItem mSearchMenuItem;
    private boolean mSearchViewExpanded;
    private int mSelectedProductSize;
    private ProductTreeItem mSelectedProductTreeItem;
    private MenuItem mShowAll;
    private MenuItem mShowOnlyProduct;
    private MenuItem mShowOnlyService;
    private MenuItem mSortMenuItem;
    private int mSourceWareHouseNr;
    private String mSpecode;
    private boolean mStockGet;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTwFabCheckAllText;
    private int mWareHouseNr;
    private MenuItem menuRecommendedProducts;
    private ArrayList<RecommendedProducts> recommendedProductList;
    private final ProductListRepository repository;
    private SearchView searchView;
    private ArrayList<SeriLotItem> seriLotItems;
    private boolean useProductGroupTree;
    private final ProductListViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    private static final Object lock = new Object();
    public static final String EXTRA_CUSTOMER_REF = SalesDetailActivity.class.getName() + ".EXTRA_CUSTOMER_REF";
    private static final String STATE_PRODUCT_SELECT_TYPE = "state:customerSelectType";
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_BARCODE_FILTER = "state:barcodeFilter";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_SEARCH_VIEW_EXPANDED = "state:searchViewExpanded";
    private static final String STATE_SALES_TYPE = "state:sales_type";
    private static final String STATE_WAREHOUSE_NR = "state:warehouse_nr";
    private static final String STATE_STOCK_GET = "state:stockGet";
    private static final String STATE_PRICE_GET = "state:priceGet";
    private static final String STATE_DEF_ORDER_ID = "state:defOrderId";
    private static final String STATE_FINISH_ADD_PRODUCT = "state:finishAddProduct";
    private static final String STATE_PAYMENT_TRADE_GROUP = "state:paymentTradeGroup";
    private static final String STATE_PAYMENT_REF = "state:paymentRef";
    private static final String STATE_BARCODE_SINGLE_SELECT = "state:mBarcodeSingleSelect";
    private static final String STATE_PRODUCT_LIST_PARAMETER = "state:mProductListParameter";
    private static final String STATE_PRODUCT_LIST_SHOW_TYPE = "state:productListShowType";
    private static final String STATE_SELECTED_PRODUCT_SIZE = "state:selectedProductSize";
    private static final String STATE_ITEM_ID = "state:itemId";
    private static final String STATE_PRODUCT_GROUP_CODE = "state:productGroupCode";
    private static final String STATE_DIVISION_NR = "state:division_nr";
    private final String treeListCrumb = "treeList";
    private final String doneCrumb = "done";
    private final String productListCrumb = "productListCrumb";
    private boolean isFirstResponse = true;
    private SalesType mSalesType = SalesType.FREE;
    private ArrayList<ProductTreeItem> mProductTreeData = new ArrayList<>();
    private final ArrayList<String> mGroupCodeList = new ArrayList<>();
    private List<FormGroupModel> formGroupModelList = new ArrayList();
    private final ArrayList<String> mCrumbList = new ArrayList<>();
    private final ArrayList<Disposable> mSubscriptionGetProduct = new ArrayList<>();
    private ArrayList<Product> tempBarcodeProductList = new ArrayList<>();
    private final ArrayList<Integer> seriItemRefList = new ArrayList<>();
    private ProductRecyclerViewAdapter mAdapter = new ProductRecyclerViewAdapter(this);
    private final ProductTreeRecyclerViewAdapter mProductTreeRecyclerViewAdapter = new ProductTreeRecyclerViewAdapter(this);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void onPreExecute(ProcessType processType) {
        Intrinsics.checkNotNullParameter(processType, "type");
    }
    public boolean onQueryTextChange(String str) {
        Intrinsics.checkNotNullParameter(str, "newText");
        return true;
    } 
    public boolean onQueryTextSubmit(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        return false;
    }
    public ProductListFragment() {
        ProductListRepository productListRepository = new ProductListRepository();
        this.repository = productListRepository;
        this.viewModel = new ProductListViewModel(productListRepository);
    }
    private FragmentProductListBinding getBinding() {
        FragmentProductListBinding fragmentProductListBinding = this._binding;
        Intrinsics.checkNotNull(fragmentProductListBinding);
        return fragmentProductListBinding;
    }
    public boolean getFinishAddProduct() {
        return this.finishAddProduct;
    }
    public void setFinishAddProduct(boolean z) {
        this.finishAddProduct = z;
    }
    public ProductRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }
    public void setMAdapter(ProductRecyclerViewAdapter productRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(productRecyclerViewAdapter, "<set-?>");
        this.mAdapter = productRecyclerViewAdapter;
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

    public ProgressDialogBuilder<?> getMProgressSurplusDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressSurplusDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressSurplusDialogBuilder");
        return null;
    }

    public void setMProgressSurplusDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressSurplusDialogBuilder = progressDialogBuilder;
    }

    public ActionViewResolver getMActionViewResolver() {
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        if (actionViewResolver != null) {
            return actionViewResolver;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mActionViewResolver");
        return null;
    }

    public void setMActionViewResolver(ActionViewResolver actionViewResolver) {
        Intrinsics.checkNotNullParameter(actionViewResolver, "<set-?>");
        this.mActionViewResolver = actionViewResolver;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
    public void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        if (bundle != null) {
            z = bundle.getBoolean(STATE_PRODUCT_SELECT_TYPE);
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            z = arguments.getBoolean(EXTRA_PRODUCT_SELECT_TYPE, false);
        }
        this.mProductSelectType = z;
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressSurplusDialogBuilder(new ProgressDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2));
        Context requireContext3 = requireContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(requireContext3, (BaseInjectableActivity) activity3));
        setMActionViewResolver(new ActionViewResolver());
    }
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        this._binding = FragmentProductListBinding.inflate(layoutInflater, viewGroup, false);
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = getBinding().swipeLayout;
        Intrinsics.checkNotNullExpressionValue(appBarSwipeRefreshLayout, "swipeLayout");
        this.mSwipeRefreshLayout = appBarSwipeRefreshLayout;
        this.mRecyclerView = getBinding().rcwList;
        FrameLayout frameLayout = getBinding().frameFab;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameFab");
        this.mFrameFab = frameLayout;
        FrameLayout frameLayout2 = getBinding().frameCheckAllFab;
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "frameCheckAllFab");
        this.mFrameCheckAllFab = frameLayout2;
        CounterFab counterFab = getBinding().fabDone;
        Intrinsics.checkNotNullExpressionValue(counterFab, "fabDone");
        this.mFabDone = counterFab;
        CounterFab counterFab2 = getBinding().fabCheckAllDone;
        Intrinsics.checkNotNullExpressionValue(counterFab2, "fabCheckAllDone");
        this.mFabCheckAll = counterFab2;
        AppCompatTextView appCompatTextView = getBinding().twFabCheckAllText;
        Intrinsics.checkNotNullExpressionValue(appCompatTextView, "twFabCheckAllText");
        this.mTwFabCheckAllText = appCompatTextView;
        EmptyListBinding emptyListBinding = getBinding().empty;
        Intrinsics.checkNotNullExpressionValue(emptyListBinding, "empty");
        this.mEmptyView = emptyListBinding;
        EmptySearchBinding emptySearchBinding = getBinding().emptySearch;
        Intrinsics.checkNotNullExpressionValue(emptySearchBinding, "emptySearch");
        this.mEmptySearchView = emptySearchBinding;
        EmptyListBinding emptyListBinding2 = this.mEmptyView;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = null;
        if (emptyListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            emptyListBinding2 = null;
        }
        emptyListBinding2.getRoot().setVisibility(4);
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
                ProductListFragment.onCreateViewlambda0(ProductListFragment.this);
            }
        });
        RecyclerView recyclerView = getBinding().rwProductTree;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "rwProductTree");
        this.mProductTreeRecycleView = recyclerView;
        AppCompatSpinner appCompatSpinner = getBinding().frmGroupSpinner;
        Intrinsics.checkNotNullExpressionValue(appCompatSpinner, "frmGroupSpinner");
        this.frmGroupSpinner = appCompatSpinner;
        AppCompatSpinner appCompatSpinner2 = getBinding().frmSpinner;
        Intrinsics.checkNotNullExpressionValue(appCompatSpinner2, "frmSpinner");
        this.frmSpinner = appCompatSpinner2;
        FrameLayout frameLayout3 = getBinding().frameGroup;
        Intrinsics.checkNotNullExpressionValue(frameLayout3, "frameGroup");
        this.frameGroup = frameLayout3;
        FrameLayout frameLayout4 = getBinding().frameForm;
        Intrinsics.checkNotNullExpressionValue(frameLayout4, "frameForm");
        this.frameForm = frameLayout4;
        FrameLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }
 
    public static void onCreateViewlambda0(ProductListFragment productListFragment) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        productListFragment.loadProduct();
    }
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = this.mProductTreeRecycleView;
        CounterFab counterFab = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        RecyclerView recyclerView2 = this.mProductTreeRecycleView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
            recyclerView2 = null;
        }
        recyclerView2.setHasFixedSize(true);
        RecyclerView recyclerView3 = this.mProductTreeRecycleView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
            recyclerView3 = null;
        }
        recyclerView3.setItemAnimator(new SlideInLeftAnimator(new OvershootInterpolator(2.0f)));
        ProductTreeRecyclerViewAdapter productTreeRecyclerViewAdapter = this.mProductTreeRecyclerViewAdapter;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        productTreeRecyclerViewAdapter.initDisplayOptions(requireActivity);
        isProductSelectType();
        CounterFab counterFab2 = this.mFabDone;
        if (counterFab2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab2 = null;
        }
        counterFab2.setOnClickListener(new DialogInterface.OnClickListener() {
            public void onClick(View view2) {
                ProductListFragment.onViewCreatedlambda1(ProductListFragment.this, view2);
            }
        });
        CounterFab counterFab3 = this.mFabCheckAll;
        if (counterFab3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabCheckAll");
        } else {
            counterFab = counterFab3;
        }
        counterFab.setOnClickListener(new DialogInterface.OnClickListener() {
            public void onClick(View view2) {
                ProductListFragment.onViewCreatedlambda2(ProductListFragment.this, view2);
            }
        });
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(this) { 
            final  ProductListFragment this0;
            {
                this.this0 = r1;
            }
            public void onScrolled(RecyclerView recyclerView4, int i, int i2) {
                Intrinsics.checkNotNullParameter(recyclerView4, "recyclerView");
                if (i2 > 0 || i2 < 0) {
                    CounterFab counterFab4 = this.this0.mFabDone;
                    FrameLayout frameLayout = null;
                    if (counterFab4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
                        counterFab4 = null;
                    }
                    if (counterFab4.isShown()) {
                        CounterFab counterFab5 = this.this0.mFabDone;
                        if (counterFab5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
                            counterFab5 = null;
                        }
                        counterFab5.hide();
                    }
                    CounterFab counterFab6 = this.this0.mFabCheckAll;
                    if (counterFab6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFabCheckAll");
                        counterFab6 = null;
                    }
                    if (counterFab6.isShown()) {
                        FrameLayout frameLayout2 = this.this0.mFrameCheckAllFab;
                        if (frameLayout2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
                        } else {
                            frameLayout = frameLayout2;
                        }
                        frameLayout.setVisibility(8);
                    }
                }
            }
 
            public void onScrollStateChanged(RecyclerView recyclerView4, int i) {
                Intrinsics.checkNotNullParameter(recyclerView4, "recyclerView");
                this.this0.controlScrollStateChanged(i);
                super.onScrollStateChanged(recyclerView4, i);
            }
        });
    } 
    public static void onViewCreatedlambda1(ProductListFragment productListFragment, View view) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        productListFragment.hideKeyboardFrom();
        productListFragment.toggleAdapter();
        productListFragment.addCrumb(productListFragment.doneCrumb);
    } 
    public static void onViewCreatedlambda2(ProductListFragment productListFragment, View view) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        productListFragment.clickCheckAll();
    } 
    public void controlScrollStateChanged(int i) {
        FrameLayout frameLayout = null;
        if (i == 0 && !this.finishAddProduct) {
            CounterFab counterFab = this.mFabDone;
            if (counterFab == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
                counterFab = null;
            }
            counterFab.show();
            if (this.mDefOrderId > 0) {
                FrameLayout frameLayout2 = this.mFrameCheckAllFab;
                if (frameLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
                    frameLayout2 = null;
                }
                frameLayout2.setVisibility(0);
            }
        }
        if (i == 1 && !this.finishAddProduct) {
            CounterFab counterFab2 = this.mFabDone;
            if (counterFab2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
                counterFab2 = null;
            }
            counterFab2.hide();
            if (this.mDefOrderId > 0) {
                FrameLayout frameLayout3 = this.mFrameCheckAllFab;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
                } else {
                    frameLayout = frameLayout3;
                }
                frameLayout.setVisibility(8);
            }
        }
    }

    private void clickCheckAll() {
        TextView textView = this.mTwFabCheckAllText;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTwFabCheckAllText");
            textView = null;
        }
        if (!TextUtils.isEmpty(textView.getText())) {
            TextView textView3 = this.mTwFabCheckAllText;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTwFabCheckAllText");
                textView3 = null;
            }
            if (Intrinsics.areEqual(textView3.getText(), getString(R.string.str_select_all))) {
                this.mAdapter.checkAllProducts(true);
                TextView textView4 = this.mTwFabCheckAllText;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTwFabCheckAllText");
                } else {
                    textView2 = textView4;
                }
                textView2.setText(R.string.str_remove_all);
                return;
            }
            this.mAdapter.checkAllProducts(false);
            TextView textView5 = this.mTwFabCheckAllText;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTwFabCheckAllText");
            } else {
                textView2 = textView5;
            }
            textView2.setText(R.string.str_select_all);
        }
    }
    private void isProductSelectType() {
        if (!this.mProductSelectType) {
            this.mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), this.mRecyclerView, new RecyclerViewClickListener(this) { // from class: com.proje.mobilesales.features.product.view.list.ProductListFragmentisProductSelectType1
                final ProductListFragment this0;
                {
                    this.this0 = r1;
                } 
                public void onClick(View view, int i) {
                    Intrinsics.checkNotNullParameter(view, "v");
                    Product product = this.this0.getMAdapter().getProducts().get(i);
                    if (product != null) {
                        this.this0.openItemReportsActivity(product);
                    }
                } 
                public void onClickLong(View view, int i) {
                    Intrinsics.checkNotNullParameter(view, "v");
                    if (this.this0.getMAdapter().getProducts().get(i) != null) {
                        ProductListFragment productListFragment = this.this0;
                        Product product = productListFragment.getMAdapter().getProducts().get(i);
                        Intrinsics.checkNotNull(product);
                        int logicalRef = product.getLogicalRef();
                        Product product2 = this.this0.getMAdapter().getProducts().get(i);
                        Intrinsics.checkNotNull(product2);
                        productListFragment.openDetailFragment(logicalRef, product2.getService());
                    }
                }
            }, 1000));
        }
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mCustomerRef = bundle.getInt(STATE_CUSTOMER_REF);
            this.mSearchViewExpanded = bundle.getBoolean(STATE_SEARCH_VIEW_EXPANDED);
            this.mSalesType = SalesType.Companion.fromSalesType(bundle.getInt(STATE_SALES_TYPE, -1));
            this.mWareHouseNr = bundle.getInt(STATE_WAREHOUSE_NR);
            this.mPriceGet = bundle.getBoolean(STATE_PRICE_GET);
            this.mStockGet = bundle.getBoolean(STATE_STOCK_GET);
            this.mDefOrderId = bundle.getInt(STATE_DEF_ORDER_ID, 0);
            this.finishAddProduct = bundle.getBoolean(STATE_FINISH_ADD_PRODUCT, false);
            this.mPaymentTradeGroup = bundle.getString(STATE_PAYMENT_TRADE_GROUP);
            this.mPaymentRef = bundle.getInt(STATE_PAYMENT_REF, 0);
            this.mBarcodeFilter = bundle.getParcelableArrayList(STATE_BARCODE_FILTER);
            this.mBarcodeSingleSelect = bundle.getBoolean(STATE_BARCODE_SINGLE_SELECT, false);
            this.mProductListParameter = bundle.getParcelable(STATE_PRODUCT_LIST_PARAMETER);
            this.mProductListShowType = ProductListShowType.values()[bundle.getInt(STATE_PRODUCT_LIST_SHOW_TYPE, 0)];
            this.mSelectedProductSize = bundle.getInt(STATE_SELECTED_PRODUCT_SIZE, 0);
            this.mReportItemRef = bundle.getInt(STATE_ITEM_ID, 0);
            this.mProductGroupCode = bundle.getString(STATE_PRODUCT_GROUP_CODE, "");
            this.mDivisionNr = bundle.getInt(STATE_DIVISION_NR, -99);
            getMProgressDialogBuilder().dismiss();
        } else {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.mCustomerRef = arguments.getInt(EXTRA_CUSTOMER_REF, 0);
            this.mFilter = controlLastFilter();
            SalesType.Companion companion = SalesType.Companion;
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.mSalesType = companion.fromSalesType(arguments2.getInt(EXTRA_SALES_TYPE, -1));
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            this.mWareHouseNr = arguments3.getInt(EXTRA_WAREHOUSE_NR, 0);
            Bundle arguments4 = getArguments();
            Intrinsics.checkNotNull(arguments4);
            this.mSourceWareHouseNr = arguments4.getInt(EXTRA_SOURCE_WAREHOUSE_NR, 0);
            Bundle arguments5 = getArguments();
            Intrinsics.checkNotNull(arguments5);
            this.mDefOrderId = arguments5.getInt(EXTRA_DEF_ORDER_ID, 0);
            Bundle arguments6 = getArguments();
            Intrinsics.checkNotNull(arguments6);
            this.mPaymentTradeGroup = arguments6.getString(EXTRA_PAYMENT_TRADE_GROUP, "");
            Bundle arguments7 = getArguments();
            Intrinsics.checkNotNull(arguments7);
            this.mPaymentRef = arguments7.getInt(EXTRA_PAYMENT_REF, 0);
            Bundle arguments8 = getArguments();
            Intrinsics.checkNotNull(arguments8);
            this.mBarcodeFilter = arguments8.getParcelableArrayList(EXTRA_BARCODE_FILTER);
            this.mProductListParameter = this.viewModel.getProductListParameter(this.mSalesType);
            this.mProductListShowType = controlListShowType();
            Bundle arguments9 = getArguments();
            Intrinsics.checkNotNull(arguments9);
            this.mSelectedProductSize = arguments9.getInt(EXTRA_SELECTED_PRODUCT_SIZE, 0);
            Bundle arguments10 = getArguments();
            Intrinsics.checkNotNull(arguments10);
            String str = IntentExtraName.EXTRA_ITEM_ID;
            this.mReportItemRef = arguments10.getInt(str, 0);
            Bundle arguments11 = getArguments();
            Intrinsics.checkNotNull(arguments11);
            this.mProductGroupCode = arguments11.getString(EXTRA_PRODUCT_GROUP_CODE, "");
            Bundle arguments12 = getArguments();
            Intrinsics.checkNotNull(arguments12);
            this.mDivisionNr = arguments12.getInt(EXTRA_DIVISION_NR, -99);
            Bundle arguments13 = getArguments();
            Intrinsics.checkNotNull(arguments13);
            this.mSpecode = arguments13.getString(EXTRA_SPECODE, "");
            Bundle arguments14 = getArguments();
            Intrinsics.checkNotNull(arguments14);
            arguments14.putInt(str, 0);
        }
        View view = this.frameForm;
        RecyclerView recyclerView = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameForm");
            view = null;
        }
        view.setVisibility(setFrameVisibility());
        View view2 = this.frameGroup;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameGroup");
            view2 = null;
        }
        view2.setVisibility(setFrameVisibility());
        this.useProductGroupTree = this.viewModel.getUseProductGroupTree() && !SalesUtils.isSalesTypeWhTransfer(this.mSalesType);
        controlProductSelectType();
        controlBarcodeFilter();
        if (this.mReportItemRef > 0 || this.mDefOrderId > 0) {
            this.useProductGroupTree = false;
        }
        controlSalesType();
        ProductRecyclerViewAdapter productRecyclerViewAdapter = this.mAdapter;
        ProductListParameter productListParameter = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter);
        productRecyclerViewAdapter.setDefaultAmount(productListParameter.getDefaultAmount());
        ProductRecyclerViewAdapter productRecyclerViewAdapter2 = this.mAdapter;
        ProductListParameter productListParameter2 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter2);
        ProductOperationDiscount productOperationDiscount = productListParameter2.getProductOperationDiscount();
        Intrinsics.checkNotNull(productOperationDiscount);
        productRecyclerViewAdapter2.setProductOperationDiscount(productOperationDiscount);
        ProductListParameter productListParameter3 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter3);
        this.mAdapter.showExplanation(productListParameter3.isExplanation());
        ProductListParameter productListParameter4 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter4);
        this.mAdapter.setDefaultExplanation(productListParameter4.getDefaultExplanation());
        ProductRecyclerViewAdapter productRecyclerViewAdapter3 = this.mAdapter;
        ProductListParameter productListParameter5 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter5);
        productRecyclerViewAdapter3.setProductShowType(productListParameter5.isShowStockExist());
        this.mAdapter.setSalesType(this.mSalesType);
        this.mAdapter.setSelectProductShow(this.finishAddProduct);
        this.mAdapter.setmProductSelectType(this.mProductSelectType);
        this.mAdapter.setAddedSelectedProductsize(this.mSelectedProductSize);
        this.mAdapter.setCustomerRef(this.mCustomerRef);
        ProductRecyclerViewAdapter productRecyclerViewAdapter4 = this.mAdapter;
        int i = this.mCustomerRef;
        int i2 = this.mPaymentRef;
        String str2 = this.mPaymentTradeGroup;
        Intrinsics.checkNotNull(str2);
        productRecyclerViewAdapter4.setVariantPriceParams(new VariantPriceParams(i, i2, str2, this.viewModel.getISqlHelperClCardTradingGrp(this.mCustomerRef)));
        this.mAdapter.setWhNr(this.mWareHouseNr);
        this.mAdapter.setDivisionNr(this.mDivisionNr);
        this.mAdapter.setItemSurplusDiscountListener(this);
        toggleFab();
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        appBarSwipeRefreshLayout.setVisibility(setRefreshLayoutVisibilty());
        RecyclerView recyclerView2 = this.mProductTreeRecycleView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.setVisibility(setProductTreeVisibility());
        controlProductGroupTree();
        controlReportItemRef();
    }
    private int setProductTreeVisibility() {
        return this.useProductGroupTree ? 0 : 4;
    }
    private int setRefreshLayoutVisibilty() {
        return this.useProductGroupTree ? 4 : 0;
    }
    private void controlSalesType() {
        if (SalesUtils.isSalesTypeWhTransfer(this.mSalesType)) {
            this.mWareHouseNr = this.viewModel.getUserInformation().getWarehouseNr();
        }
    }
    private void controlBarcodeFilter() {
        ArrayList<BarcodeResult> arrayList = this.mBarcodeFilter;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                this.isBarcodeSearch = true;
            }
        }
    }
    private int setFrameVisibility() {
        return this.mDefOrderId > 0 ? 0 : 8;
    }
    private ProductListShowType controlListShowType() {
        ProductListActivity productListActivity = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity);
        ProductListShowType mLastType = productListActivity.getMLastType();
        ProductListShowType productListShowType = ProductListShowType.PRODUCT;
        if (mLastType == productListShowType) {
            return productListShowType;
        }
        ProductListActivity productListActivity2 = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity2);
        return productListActivity2.getMLastType();
    }
    private String controlLastFilter() {
        ProductListActivity productListActivity = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity);
        if (Intrinsics.areEqual(productListActivity.getMLastFilter(), "")) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            return arguments.getString(EXTRA_FILTER, "");
        }
        ProductListActivity productListActivity2 = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity2);
        return productListActivity2.getMLastFilter();
    }
    private void controlProductSelectType() {
        FrameLayout frameLayout = null;
        if (this.mProductSelectType) {
            FrameLayout frameLayout2 = this.mFrameFab;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFrameFab");
            } else {
                frameLayout = frameLayout2;
            }
            frameLayout.setVisibility(0);
            showCheckAllFab();
            return;
        }
        this.useProductGroupTree = false;
        FrameLayout frameLayout3 = this.mFrameFab;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFrameFab");
            frameLayout3 = null;
        }
        frameLayout3.setVisibility(8);
        FrameLayout frameLayout4 = this.mFrameCheckAllFab;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
        } else {
            frameLayout = frameLayout4;
        }
        frameLayout.setVisibility(8);
        ProductListParameter productListParameter = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter);
        productListParameter.setShowOnlyStock(false);
        ProductListParameter productListParameter2 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter2);
        productListParameter2.setProductTreeGroupListType(ProductTreeGroupListType.All);
    }
    private void controlProductGroupTree() {
        if (this.useProductGroupTree) {
            requireActivity().setTitle(R.string.str_activity_title_product_groups);
            refreshProductTreeList();
        } else if (this.mDefOrderId > 0) {
            ProductListShowType productListShowType = ProductListShowType.ALL;
            this.mProductListShowType = productListShowType;
            ProductListActivity productListActivity = (ProductListActivity) getActivity();
            Intrinsics.checkNotNull(productListActivity);
            productListActivity.setMLastType(productListShowType);
            loadFormSpinners();
        } else {
            loadProduct();
        }
    }
    private void controlReportItemRef() {
        int i = this.mReportItemRef;
        if (i > 0) {
            openDetailFragment(this.mReportItemRef, !this.viewModel.getServiceTypeTableFromLogoSqlHelper(Service.class, "LOGICALREF=?", new String[]{String.valueOf(i)}).isEmpty());
            this.mReportItemRef = 0;
        }
    }
    private <ProductListFragmentExternalSyntheticLambda15> void loadFormSpinners() {
        if (this.mDefOrderId > 0 && !SalesUtils.isSalesTypeWhTransfer(getmSalesType())) {
            int i = !SalesUtils.isSalesTypeOrderOrDemand(getmSalesType()) ? 1 : 0;
            FormGroupResponseListener formGroupResponseListener = new FormGroupResponseListener(this);
            Observable<Resource<List<FormGroupModel>>> observeOn = Objects.requireNonNull(this.viewModel.getFormGroupForDisposable(i)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            ZProductListFragmentExternalSyntheticLambda15 productListFragmentExternalSyntheticLambda15 = new Consumer() {
                public void accept(Object obj) {
                    ProductListFragment.loadFormSpinnerslambda3(FormGroupResponseListener.this, (Resource) obj);
                }
            };
            ProductListFragmentExternalSyntheticLambda16 productListFragmentExternalSyntheticLambda16 = new Consumer() { 
                public void accept(Object obj) {
                    ProductListFragment.loadFormSpinnerslambda4(FormGroupResponseListener.this, (Throwable) obj);
                }
            };
            Intrinsics.checkNotNull(productListFragmentExternalSyntheticLambda16, "null cannot be cast to non-null type io.reactivex.functions.Consumer<kotlin.Throwable>");
            Disposable subscribe = observeOn.subscribe(productListFragmentExternalSyntheticLambda15, productListFragmentExternalSyntheticLambda16);
            Intrinsics.checkNotNullExpressionValue(subscribe, "subscribe(...)");
            this.compositeDisposable.add(subscribe);
        }
    } 
    public static void loadFormSpinnerslambda3(FormGroupResponseListener formGroupResponseListener, Resource resource) {
        Intrinsics.checkNotNullParameter(formGroupResponseListener, "responseListener");
        Intrinsics.checkNotNullParameter(resource, "response");
        formGroupResponseListener.onResponse((Resource<List<FormGroupModel>>) resource);
    }
 
    public static void loadFormSpinnerslambda4(FormGroupResponseListener formGroupResponseListener, Throwable th) {
        Intrinsics.checkNotNullParameter(formGroupResponseListener, "responseListener");
        Intrinsics.checkNotNullParameter(th, "throwable");
        String message = th.getMessage();
        Intrinsics.checkNotNull(message);
        formGroupResponseListener.onError(message);
    }

    private void showProductGroups() {
        ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait));
        if (message != null) {
            message.show();
        }
        this.viewModel.getProductGroups(getString(R.string.qry_get_product_groups), new ProductGroupListener(this));
    }

    private void showRecommendedProducts() {
        ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait));
        if (message != null) {
            message.show();
        }
        this.viewModel.getRecommendedProducts(this.mCustomerRef, new RecommendedProductsListener(this));
    } 
    public static final class RecommendedProductsListener implements ResponseListener<ArrayList<RecommendedProducts>> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public RecommendedProductsListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onRecommendedProducts(arrayList, "");
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onRecommendedProducts(null, str);
                }
            }
        }
    } 
    public void onRecommendedProducts(ArrayList<RecommendedProducts> arrayList, String str) {
        getMProgressDialogBuilder().dismiss();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 1).show();
            return;
        }
        if (arrayList != null) {
            this.recommendedProductList = new ArrayList<>();
            Iterator<RecommendedProducts> it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList<RecommendedProducts> arrayList2 = this.recommendedProductList;
                Intrinsics.checkNotNull(arrayList2);
                arrayList2.add(it.next());
            }
        }
        restartLoader();
    }
 
    public static final class ProductGroupListener implements ResponseListener<ArrayList<ProductGroupModel>> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductGroupListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onGotProductGroup(arrayList, "");
                }
            }
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onGotProductGroup(null, str);
                }
            }
        }
    } 
    public void onGotProductGroup(ArrayList<ProductGroupModel> arrayList, String str) {
        int i;
        getMProgressDialogBuilder().dismiss();
        if (!TextUtils.isEmpty(str)) {
            Log.d(TAG, "onError: " + str);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        String string = getString(R.string.menu_all);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        arrayList2.add(string);
        if (arrayList == null || arrayList.size() <= 1) {
            i = 0;
        } else {
            int size = arrayList.size();
            i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                String grpCode = arrayList.get(i2).getGrpCode();
                arrayList2.add(arrayList.get(i2).getGrpDescription());
                if (Intrinsics.areEqual(this.mProductGroupCode, grpCode)) {
                    i = i2 + 1;
                }
            }
        }
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.str_product_groups);
        builder.setCancelable(false);
        builder.setSingleChoiceItems((CharSequence[]) arrayList2.toArray(new CharSequence[0]), i, new DialogInterface.OnClickListener(arrayList2) { // from class: com.proje.mobilesales.features.product.view.list.ProductListFragmentExternalSyntheticLambda0
            public final List f1;

            {
                this.f1 = r2;
            } 
            public void onClick(DialogInterface dialogInterface, int i3) {
                ProductListFragment.onGotProductGrouplambda7(ProductListFragment.this, this.f1, dialogInterface, i3);
            }
        });
        AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.show();
    } 
    public static void onGotProductGrouplambda7(ProductListFragment productListFragment, List list, DialogInterface dialogInterface, int i) {
        String str;
        List list2;
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        Intrinsics.checkNotNullParameter(list, "productGroups");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        if (i == 0) {
            str = "";
        } else {
            List<String> split = new Regex(SqlLiteVariable._COMMA_SEP).split((CharSequence) list.get(i), 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        list2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            list2 = CollectionsKt.emptyList();
            str = ((String[]) list2.toArray(new String[0]))[0];
        }
        productListFragment.mProductGroupCode = str;
        Log.d(TAG, "clicked product group:" + productListFragment.mProductGroupCode);
        dialogInterface.dismiss();
        productListFragment.restartLoader();
    }

    private void showCheckAllFab() {
        FrameLayout frameLayout = null;
        if (this.mDefOrderId <= 0 || this.finishAddProduct) {
            FrameLayout frameLayout2 = this.mFrameCheckAllFab;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
            } else {
                frameLayout = frameLayout2;
            }
            frameLayout.setVisibility(8);
            return;
        }
        FrameLayout frameLayout3 = this.mFrameCheckAllFab;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
        } else {
            frameLayout = frameLayout3;
        }
        frameLayout.setVisibility(View.VISIBLE);
    }
 
    @SuppressLint("ResourceType")
    public void openDetailFragment(int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt(ProductDetailFragment.PRODUCT_CODE, i);
        bundle.putBoolean(ProductDetailFragment.PRODUCT_ISSERVICE, z);
        requireActivity().getSupportFragmentManager().beginTransaction().add(16908298, Fragment.instantiate(requireActivity(), ProductDetailFragment.class.getName(), bundle), "PRODUCT DETAIL").addToBackStack("PRODUCT").commit();
    }
 
    public void openItemReportsActivity(Product product) {
        String code = product.getCode();
        Intrinsics.checkNotNull(code);
        String name = product.getName();
        Intrinsics.checkNotNull(name);
        requireActivity().getSupportFragmentManager().beginTransaction().add(16908298, ItemReportsFragment.Companion.newInstance(new ProductInfo(code, name, product.getLogicalRef(), product.getService())), "ITEM REPORTS").addToBackStack("PRODUCT").commit();
    }
 
    private void loadProduct() {
        
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListFragment.loadProduct():void");
    }

    private void getOnlineProductGet() {
        ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_get_product_please_wait));
        if (message != null) {
            message.show();
        }
        this.viewModel.getOnlineItemsProduct(new ProductOnlineGetListener(this));
    }

    public void filter(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        this.mSearchViewExpanded = false;
        this.mFilter = str;
        ProductListActivity productListActivity = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity);
        String str2 = this.mFilter;
        Intrinsics.checkNotNull(str2);
        productListActivity.setMLastFilter(str2);
        restartLoader();
    }
  
    public void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem findItem = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem, "findItem(...)");
        createSearchView(findItem);
        menuInflater.inflate(R.menu.menu_product, menu);
        if (this.viewModel.getUserInformation().isDoShip() && !this.mProductSelectType) {
            menuInflater.inflate(R.menu.menu_print, menu);
        }
        MenuItem findItem2 = menu.findItem(R.id.menu_search);
        Intrinsics.checkNotNullExpressionValue(findItem2, "findItem(...)");
        this.mSearchMenuItem = findItem2;
        this.mSortMenuItem = menu.findItem(R.id.menu_sort);
        this.mDoneMenuItem = menu.findItem(R.id.menu_done);
        this.mBarcodeMenuItem = menu.findItem(R.id.menu_barcode);
        this.mPrintMenuItem = menu.findItem(R.id.menu_print);
        this.mShowAll = menu.findItem(R.id.menu_all);
        this.mShowOnlyProduct = menu.findItem(R.id.menu_product);
        this.mShowOnlyService = menu.findItem(R.id.menu_service);
        this.mFilterByGroup = menu.findItem(R.id.menu_filter_by_group);
        this.menuRecommendedProducts = menu.findItem(R.id.menu_recommended_products);
        super.createOptionsMenu(menu, menuInflater);
    }
 
    public void prepareOptionsMenu(Menu menu) {
        MenuItem menuItem = this.mDoneMenuItem;
        Intrinsics.checkNotNull(menuItem);
        menuItem.setVisible(this.mProductSelectType);
        if (this.mAdapter.getItemCount() > 0) {
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            if (productListParameter.getSortType() == 0) {
                MenuItem menuItem2 = this.mSortMenuItem;
                Intrinsics.checkNotNull(menuItem2);
                menuItem2.setTitle(R.string.menu_sort_code);
            } else {
                MenuItem menuItem3 = this.mSortMenuItem;
                Intrinsics.checkNotNull(menuItem3);
                menuItem3.setTitle(R.string.menu_sort_name);
            }
        }
        setMenuItemVisible();
        super.prepareOptionsMenu(menu);
    }
 
    private void setMenuItemVisible() {
      
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListFragment.setMenuItemVisible():void");
    }

    private void setMenuItemVisible(MenuItem menuItem, boolean z) {
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
    } 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        hideKeyboardFrom();
        ArrayList<RecommendedProducts> arrayList = this.recommendedProductList;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (menuItem.getItemId() == R.id.menu_sort) {
            toggleSort(menuItem);
        } else if (menuItem.getItemId() == R.id.menu_done) {
            if (this.finishAddProduct) {
                finishActivityResult();
            } else if (selectProductControl()) {
                toggleAdapter();
                addCrumb(this.doneCrumb);
            } else {
                finishActivityResult();
            }
        } else if (menuItem.getItemId() == R.id.menu_barcode) {
            createBarcodeDialog();
        } else if (menuItem.getItemId() == R.id.menu_print) {
            ProductListViewModel productListViewModel = this.viewModel;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            productListViewModel.getPrintTransportDispatchNote(requireContext, FicheType.DELIVERY_NOTE, -1);
        } else if (menuItem.getItemId() == R.id.menu_all) {
            setProductListShowType(ProductListShowType.ALL);
        } else if (menuItem.getItemId() == R.id.menu_product) {
            setProductListShowType(ProductListShowType.PRODUCT);
        } else if (menuItem.getItemId() == R.id.menu_service) {
            setProductListShowType(ProductListShowType.SERVICE);
        } else if (menuItem.getItemId() == R.id.menu_filter_by_group) {
            showProductGroups();
        } else if (menuItem.getItemId() == R.id.menu_recommended_products) {
            showRecommendedProducts();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setProductListShowType(ProductListShowType productListShowType) {
        this.mProductListShowType = productListShowType;
        ProductListActivity productListActivity = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity);
        productListActivity.setMLastType(productListShowType);
        restartLoader();
    }

    private void createBarcodeDialog() {
        if (this.mDefOrderId > 0) {
            getMAlertDialogBuilder().setTitle(R.string.str_select_barcode_scan_type).setSingleChoice(R.array.barcode_select_type_values, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ProductListFragment.createBarcodeDialoglambda8(ProductListFragment.this, dialogInterface, i);
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() {  
                public void onCancel(DialogInterface dialogInterface) {
                    ProductListFragment.createBarcodeDialoglambda9(ProductListFragment.this, dialogInterface);
                }
            }).create().show();
            return;
        }
        this.mBarcodeSingleSelect = true;
        scanBarcodeFromFragment();
    } 
    public static void createBarcodeDialoglambda8(ProductListFragment productListFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        if (i != -1) {
            productListFragment.mBarcodeSingleSelect = i == 0;
            productListFragment.scanBarcodeFromFragment();
        }
        dialogInterface.dismiss();
    }
 
    public static void createBarcodeDialoglambda9(ProductListFragment productListFragment, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        productListFragment.mBarcodeSingleSelect = true;
        dialogInterface.dismiss();
    } 
    public boolean onBackPressedFragment() {
        if (!this.useProductGroupTree) {
            return productListOnBack("");
        }
        String dequeueLastCrumbItem = dequeueLastCrumbItem();
        if (StringsKt.startsWith(dequeueLastCrumbItem, this.treeListCrumb, false)) {
            return productGroupTreeOnBack(false, getTreeCrumbIndex(dequeueLastCrumbItem));
        }
        if (Intrinsics.areEqual(dequeueLastCrumbItem, "")) {
            return productGroupTreeOnBack(true, getTreeCrumbIndex(dequeueLastCrumbItem));
        }
        return productListOnBack(dequeueLastCrumbItem);
    }

    private boolean productListOnBack(String str) {
        if (this.finishAddProduct) {
            backAdapter();
        } else if (!this.useProductGroupTree) {
            return true;
        } else {
            SearchView searchView = this.searchView;
            SearchView searchView2 = null;
            if (searchView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView = null;
            }
            if (!searchView.isIconified()) {
                this.mFilter = "";
                SearchView searchView3 = this.searchView;
                if (searchView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("searchView");
                    searchView3 = null;
                }
                searchView3.setQuery("", false);
                AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                Intrinsics.checkNotNull(appCompatActivity);
                ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
                Intrinsics.checkNotNull(supportActionBar);
                supportActionBar.setSubtitle(this.mFilter);
                SearchView searchView4 = this.searchView;
                if (searchView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("searchView");
                } else {
                    searchView2 = searchView4;
                }
                searchView2.onActionViewCollapsed();
                toggleEmptyView(false, "");
            }
            hideProductList();
            productGroupTreeOnBack(false, getTreeCrumbIndex(str));
        }
        return false;
    }

    private boolean productGroupTreeOnBack(boolean z, int i) {
        SearchView searchView = this.searchView;
        if (searchView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView = null;
        }
        if (!searchView.isIconified()) {
            this.mFilter = "";
            SearchView searchView2 = this.searchView;
            if (searchView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView2 = null;
            }
            searchView2.setQuery("", false);
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            Intrinsics.checkNotNull(appCompatActivity);
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
            SearchView searchView3 = this.searchView;
            if (searchView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView3 = null;
            }
            searchView3.onActionViewCollapsed();
            toggleEmptyView(false, "");
        }
        if (this.mGroupCodeList.size() != 0) {
            if (i != this.mGroupCodeList.size()) {
                ArrayList<String> arrayList = this.mGroupCodeList;
                arrayList.remove(arrayList.size() - 1);
            }
            setSelectedProductTreeItemOnBack();
            refreshProductTreeList();
            return false;
        } else if (z) {
            RecyclerView recyclerView = this.mProductTreeRecycleView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
                recyclerView = null;
            }
            recyclerView.setAdapter(null);
            return true;
        } else {
            refreshProductTreeList();
            return false;
        }
    }

    private void setSelectedProductTreeItemOnBack() {
        ProductTreeItem productTreeItem = null;
        if (this.mGroupCodeList.size() == 0) {
            this.mSelectedProductTreeItem = null;
            return;
        }
        ArrayList<String> arrayList = this.mGroupCodeList;
        String str = arrayList.get(arrayList.size() - 1);
        ProductTreeItem productTreeItem2 = this.mSelectedProductTreeItem;
        Intrinsics.checkNotNull(productTreeItem2);
        if (!Intrinsics.areEqual(str, productTreeItem2.getCode())) {
            ProductTreeItem productTreeItem3 = this.mSelectedProductTreeItem;
            if (productTreeItem3 != null) {
                Intrinsics.checkNotNull(productTreeItem3);
                if (productTreeItem3.getParentItem() != null) {
                    ProductTreeItem productTreeItem4 = this.mSelectedProductTreeItem;
                    Intrinsics.checkNotNull(productTreeItem4);
                    productTreeItem = productTreeItem4.getParentItem();
                }
            }
            this.mSelectedProductTreeItem = productTreeItem;
        }
    }
    private int getTreeCrumbIndex(String str) {
        if (!StringsKt.startsWith(str, this.treeListCrumb, false)) {
            return -1;
        }
        try {
            Integer valueOf = Integer.valueOf(StringsKt.replace(str, this.treeListCrumb, "", false));
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            return valueOf.intValue();
        } catch (Exception e) {
            Log.e(TAG, "Error on parsing crumb", e);
            return -1;
        }
    }
    private void toggleAdapter() {
        boolean z = true;
        this.finishAddProduct = !this.finishAddProduct;
        toggleFab();
        this.mAdapter.setSelectProductShow(this.finishAddProduct);
        if (this.finishAddProduct) {
            if (this.mAdapter.getmSelectedProducts().size() != 0) {
                z = false;
            }
            toggleEmptyView(z, "");
        } else {
            if (this.mAdapter.getProducts().size() != 0) {
                z = false;
            }
            toggleEmptyView(z, "");
        }
        setMenuItemVisible();
        toggleSpinners();
    }
    private void toggleFab() {
        boolean z = false;
        CounterFab counterFab = null;
        if (!this.finishAddProduct) {
            counterFab = this.mFabDone;
            counterFab.setVisibility(0);
            showCheckAllFab();
            return;
        }
        CounterFab counterFab3 = this.mFabDone;
        if (counterFab3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab3 = null;
        }
        counterFab3.setVisibility(8);
        FrameLayout frameLayout = this.mFrameCheckAllFab;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFrameCheckAllFab");
            frameLayout = null;
        }
        frameLayout.setVisibility(8);
        CounterFab counterFab4 = this.mFabDone;
        if (counterFab4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
        } else {
            counterFab = counterFab4;
        }
        if (counterFab.getCount() > 0) {
            z = true;
        }
        hideProductTree(z);
    }
    private void backAdapter() {
        this.finishAddProduct = false;
        CounterFab counterFab = this.mFabDone;
        if (counterFab == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab = null;
        }
        counterFab.setVisibility(0);
        showCheckAllFab();
        this.mAdapter.setSelectProductShow(this.finishAddProduct);
        setMenuItemVisible();
        toggleSpinners();
        restartLoader();
    }
    private boolean selectProductControl() {
        return this.mAdapter.getmSelectedProductsSize();
    }
    private void finishActivityResult() {
        this.isBarcodeSearch = false;
        Intent intent = new Intent();
        intent.putExtra("bigdata:synccode", this.viewModel.getSaveObjectForSelectedProducts(this.mAdapter.getmSelectedProducts()));
        requireActivity().setResult(-1, intent);
        requireActivity().finishActivity(100);
        requireActivity().finish();
    }
    private void toggleSort(MenuItem menuItem) {
        ProductListParameter productListParameter = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter);
        productListParameter.setSortChanged(true);
        ProductListParameter productListParameter2 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter2);
        if (productListParameter2.getSortType() == 0) {
            ProductListParameter productListParameter3 = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter3);
            productListParameter3.setSortType(1);
            menuItem.setTitle(R.string.menu_sort_code);
        } else {
            ProductListParameter productListParameter4 = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter4);
            if (productListParameter4.getSortType() == 1) {
                ProductListParameter productListParameter5 = this.mProductListParameter;
                Intrinsics.checkNotNull(productListParameter5);
                productListParameter5.setSortType(0);
                menuItem.setTitle(R.string.menu_sort_name);
            }
        }
        ProductListViewModel productListViewModel = this.viewModel;
        ProductListParameter productListParameter6 = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter6);
        productListViewModel.getSavedProductsForToggleSortBySortType(productListParameter6.getSortType());
        restartLoader();
    } 
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putString(STATE_FILTER, this.mFilter);
        bundle.putInt(STATE_SALES_TYPE, this.mSalesType.getmValue());
        bundle.putInt(STATE_WAREHOUSE_NR, this.mWareHouseNr);
        bundle.putBoolean(STATE_PRODUCT_SELECT_TYPE, this.mProductSelectType);
        bundle.putBoolean(STATE_SEARCH_VIEW_EXPANDED, this.mSearchViewExpanded);
        bundle.putBoolean(STATE_PRICE_GET, this.mPriceGet);
        bundle.putBoolean(STATE_STOCK_GET, this.mStockGet);
        bundle.putInt(STATE_DEF_ORDER_ID, this.mDefOrderId);
        bundle.putBoolean(STATE_FINISH_ADD_PRODUCT, this.finishAddProduct);
        bundle.putInt(STATE_CUSTOMER_REF, this.mCustomerRef);
        bundle.putString(STATE_PAYMENT_TRADE_GROUP, this.mPaymentTradeGroup);
        bundle.putInt(STATE_PAYMENT_REF, this.mPaymentRef);
        bundle.putParcelableArrayList(STATE_BARCODE_FILTER, this.mBarcodeFilter);
        bundle.putBoolean(STATE_BARCODE_SINGLE_SELECT, this.mBarcodeSingleSelect);
        bundle.putParcelable(STATE_PRODUCT_LIST_PARAMETER, this.mProductListParameter);
        String str = STATE_PRODUCT_LIST_SHOW_TYPE;
        ProductListShowType productListShowType = this.mProductListShowType;
        Intrinsics.checkNotNull(productListShowType);
        bundle.putInt(str, productListShowType.ordinal());
        bundle.putInt(STATE_SELECTED_PRODUCT_SIZE, this.mSelectedProductSize);
        bundle.putInt(STATE_ITEM_ID, this.mReportItemRef);
        bundle.putInt(STATE_DIVISION_NR, this.mDivisionNr);
        super.onSaveInstanceState(bundle);
    }
    private void createSearchView(MenuItem menuItem) {
        Drawable drawable;
        View actionView = getMActionViewResolver().getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        this.searchView = searchView;
        SearchView searchView2 = null;
        if (searchView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView = null;
        }
        searchView.setQueryHint(getString(R.string.hint_search_product));
        SearchView searchView3 = this.searchView;
        if (searchView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView3 = null;
        }
        Object systemService = requireActivity().getSystemService(FirebaseAnalytics.Event.SEARCH);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        searchView3.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(requireActivity().getComponentName()));
        SearchView searchView4 = this.searchView;
        if (searchView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView4 = null;
        }
        searchView4.setIconified(!this.mSearchViewExpanded);
        SearchView searchView5 = this.searchView;
        if (searchView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView5 = null;
        }
        searchView5.setQuery(this.mFilter, false);
        SearchView searchView6 = this.searchView;
        if (searchView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView6 = null;
        }
        searchView6.setOnQueryTextListener(this);
        SearchView searchView7 = this.searchView;
        if (searchView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView7 = null;
        }
        View findViewById = searchView7.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(requireContext(), 17170443));
        if (Build.VERSION.SDK_INT >= 29 && (drawable = searchAutoComplete.getTextCursorDrawable()) != null) {
            drawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(drawable);
        }
        SearchView searchView8 = this.searchView;
        if (searchView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView8 = null;
        }
        searchView8.setOnSearchClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductListFragment.createSearchViewlambda10(ProductListFragment.this, view);
            }
        });
        SearchView searchView9 = this.searchView;
        if (searchView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView9 = null;
        }
        searchView9.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                return ProductListFragment.createSearchViewlambda11(ProductListFragment.this);
            }
        });
        SearchView searchView10 = this.searchView;
        if (searchView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
        } else {
            searchView2 = searchView10;
        }
        searchView2.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                ProductListFragment.createSearchViewlambda13(ProductListFragment.this, view, z);
            }
        });
    }  
    public static void createSearchViewlambda10(ProductListFragment productListFragment, View view) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        Intrinsics.checkNotNullParameter(view, "v");
        productListFragment.mSearchViewExpanded = true;
        view.requestFocus();
    } 
    public static boolean createSearchViewlambda11(ProductListFragment productListFragment) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        productListFragment.mFilter = "";
        ProductListActivity productListActivity = (ProductListActivity) productListFragment.getActivity();
        Intrinsics.checkNotNull(productListActivity);
        String str = productListFragment.mFilter;
        Intrinsics.checkNotNull(str);
        productListActivity.setMLastFilter(str);
        productListFragment.mBarcodeSingleSelect = false;
        productListFragment.isBarcodeSearch = false;
        AppCompatActivity appCompatActivity = (AppCompatActivity) productListFragment.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(productListFragment.mFilter);
        if (productListFragment.useProductGroupTree) {
            RecyclerView recyclerView = productListFragment.mProductTreeRecycleView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
                recyclerView = null;
            }
            if (recyclerView.getVisibility() == 0) {
                return false;
            }
        }
        productListFragment.restartLoader();
        return false;
    } 
    public void createSearchViewlambda13(ProductListFragment productListFragment, View view, boolean z) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        if (z) {
            view.postDelayed(new Runnable(view) {
                public final View f1 = null;
                public void run() {
                    ProductListFragment.createSearchViewlambda13lambda12(ProductListFragment.this, this.f1);
                }
            }, 100);
        }
    }
 
    public static void createSearchViewlambda13lambda12(ProductListFragment productListFragment, View view) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        productListFragment.showInputMethod(view.findFocus());
    } 
    public ListRecyclerViewAdapter<?, ?> getAdapter() {
        return this.mAdapter;
    }
    public void restartLoader() {
        Iterator<Disposable> it = this.mSubscriptionGetProduct.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        if (!this.finishAddProduct) {
            this.mAdapter.restartScroll();
            ArrayList<Disposable> arrayList = this.mSubscriptionGetProduct;
            ProductListViewModel productListViewModel = this.viewModel;
            String loaderSqlText = getLoaderSqlText(50, 0);
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            arrayList.add(productListViewModel.getProductList(loaderSqlText, productListParameter.getUnitPriceDigit(), new ProductListResponseListener(this, true), this.mSpecode));
            this.mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                public void onLoadMore(int i, int i2) {
                    ProductListFragment.restartLoaderlambda14(ProductListFragment.this, i, i2);
                }
            });
        }
    } 
    public static void restartLoaderlambda14(ProductListFragment productListFragment, int i, int i2) {
        Intrinsics.checkNotNullParameter(productListFragment, "this0");
        productListFragment.callLoader(i, i2);
    }
    private void callLoader(int i, int i2) {
        if (!this.finishAddProduct) {
            ProductListViewModel productListViewModel = this.viewModel;
            String loaderSqlText = getLoaderSqlText(i, i2);
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            productListViewModel.getProductList(loaderSqlText, productListParameter.getUnitPriceDigit(), new ProductListResponseListener(this, false), this.mSpecode);
        }
    } 
    public void onProductLoads(ArrayList<Product> arrayList) {
        this.mAdapter.addItem(arrayList);
        if (this.mProductSelectType) {
            controlBarcodeFilter(arrayList);
            controlBarcodeSingleSelect(arrayList);
        }
        if (!isDetached()) {
            toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
        }
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = null;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
            if (appBarSwipeRefreshLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            } else {
                appBarSwipeRefreshLayout2 = appBarSwipeRefreshLayout3;
            }
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        setCheckAllTextForFormSelectionAfterProductsLoad();
        getMProgressDialogBuilder().dismiss();
    }
    private void controlBarcodeSingleSelect(ArrayList<Product> arrayList) {
        if (this.mBarcodeSingleSelect) {
            setSelectedProductSeri(arrayList);
        } else if (!TextUtils.isEmpty(this.mFilter)) {
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            if (productListParameter.isAutoAddProduct()) {
                this.mAdapter.selectAllProduct();
                clearFilter();
            }
        }
    }

    private void setSelectedProductSeri(ArrayList<Product> arrayList) {
        ArrayList<SeriLotItem> arrayList2;
        if (arrayList != null && arrayList.size() > 0 && this.mBarcodeItem != null) {
            BarcodeItem barcodeItem = this.mBarcodeItem;
            Intrinsics.checkNotNull(barcodeItem);
            arrayList.get(0).setAmount(barcodeItem.getKey().getAmount());
            setBarcodeItemProduct(arrayList);
            if (this.isBarcodeSearch && (arrayList2 = this.seriLotItems) != null) {
                Intrinsics.checkNotNull(arrayList2);
                if (arrayList2.size() > 0) {
                    ProductRecyclerViewAdapter productRecyclerViewAdapter = this.mAdapter;
                    int logicalRef = arrayList.get(0).getLogicalRef();
                    ArrayList<SeriLotItem> arrayList3 = this.seriLotItems;
                    Intrinsics.checkNotNull(arrayList3);
                    Serilot serilot = arrayList3.get(0).getSerilot();
                    Intrinsics.checkNotNull(serilot);
                    productRecyclerViewAdapter.selectProductWithSeri(logicalRef, serilot);
                    finishActivityResult();
                }
            }
        }
    }

    private void setBarcodeItemProduct(ArrayList<Product> arrayList) {
        ProductListParameter productListParameter = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter);
        if (!productListParameter.isAutoAddProduct()) {
            BarcodeItem barcodeItem = this.mBarcodeItem;
            Intrinsics.checkNotNull(barcodeItem);
            if (barcodeItem.getProduct() == null) {
                return;
            }
        }
        BarcodeItem barcodeItem2 = this.mBarcodeItem;
        Intrinsics.checkNotNull(barcodeItem2);
        if (barcodeItem2.getProduct() != null) {
            try {
                BarcodeItem barcodeItem3 = this.mBarcodeItem;
                Intrinsics.checkNotNull(barcodeItem3);
                Object clone = barcodeItem3.getProduct().clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.product.model.Product");
                arrayList.set(0, (Product) clone);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        ProductRecyclerViewAdapter productRecyclerViewAdapter = this.mAdapter;
        Product product = arrayList.get(0);
        Intrinsics.checkNotNullExpressionValue(product, "get(...)");
        productRecyclerViewAdapter.selectOne(product);
    }

    private void controlBarcodeFilter(ArrayList<Product> arrayList) {
        ArrayList<BarcodeResult> arrayList2 = this.mBarcodeFilter;
        if (arrayList2 != null) {
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.size() > 0) {
                barcodeMapping(arrayList);
                ProductListParameter productListParameter = this.mProductListParameter;
                Intrinsics.checkNotNull(productListParameter);
                if (productListParameter.isAutoAddProduct()) {
                    this.mAdapter.selectAllProduct();
                }
                controlBarcodeFilterSearch();
            }
        }
    }

    private void controlBarcodeFilterSearch() {
        ArrayList<SeriLotItem> arrayList;
        if (this.isBarcodeSearch && (arrayList = this.seriLotItems) != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                this.mAdapter.selectAllProduct();
                ProductRecyclerViewAdapter productRecyclerViewAdapter = this.mAdapter;
                ArrayList<SeriLotItem> arrayList2 = this.seriLotItems;
                Intrinsics.checkNotNull(arrayList2);
                productRecyclerViewAdapter.setSeriToProducts(arrayList2);
                ArrayList<SeriLotItem> arrayList3 = this.seriLotItems;
                Intrinsics.checkNotNull(arrayList3);
                arrayList3.clear();
                ArrayList<BarcodeResult> seriBarcodeList = getSeriBarcodeList();
                Intrinsics.checkNotNull(seriBarcodeList);
                int size = seriBarcodeList.size();
                ArrayList<BarcodeResult> arrayList4 = this.mBarcodeFilter;
                Intrinsics.checkNotNull(arrayList4);
                if (size == arrayList4.size()) {
                    finishActivityResult();
                }
            }
        }
    }

    private void barcodeMapping(ArrayList<Product> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Product> it = arrayList.iterator();
            while (it.hasNext()) {
                Product next = it.next();
                ArrayList<BarcodeResult> arrayList2 = this.mBarcodeFilter;
                Intrinsics.checkNotNull(arrayList2);
                Iterator<BarcodeResult> it2 = arrayList2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        BarcodeResult next2 = it2.next();
                        if (next.getLogicalRef() == next2.getItemRef()) {
                            next.setAmount(next2.getAmount());
                            break;
                        }
                    }
                }
            }
        }
    }

    private void toggleEmptyView(boolean z, String str) {
        EmptySearchBinding emptySearchBinding = null;
        EmptyListBinding emptyListBinding = null;
        EmptySearchBinding emptySearchBinding2 = null;
        if (!z) {
            EmptyListBinding emptyListBinding2 = this.mEmptyView;
            if (emptyListBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding2 = null;
            }
            emptyListBinding2.getRoot().setVisibility(4);
            EmptySearchBinding emptySearchBinding3 = this.mEmptySearchView;
            if (emptySearchBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
            } else {
                emptySearchBinding = emptySearchBinding3;
            }
            emptySearchBinding.getRoot().setVisibility(4);
        } else if (TextUtils.isEmpty(str)) {
            EmptySearchBinding emptySearchBinding4 = this.mEmptySearchView;
            if (emptySearchBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                emptySearchBinding4 = null;
            }
            emptySearchBinding4.getRoot().setVisibility(4);
            EmptyListBinding emptyListBinding3 = this.mEmptyView;
            if (emptyListBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding3 = null;
            }
            emptyListBinding3.getRoot().setVisibility(0);
            EmptyListBinding emptyListBinding4 = this.mEmptyView;
            if (emptyListBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
            } else {
                emptyListBinding = emptyListBinding4;
            }
            emptyListBinding.getRoot().bringToFront();
        } else {
            EmptyListBinding emptyListBinding5 = this.mEmptyView;
            if (emptyListBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyView");
                emptyListBinding5 = null;
            }
            emptyListBinding5.getRoot().setVisibility(4);
            EmptySearchBinding emptySearchBinding5 = this.mEmptySearchView;
            if (emptySearchBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
                emptySearchBinding5 = null;
            }
            emptySearchBinding5.getRoot().setVisibility(0);
            EmptySearchBinding emptySearchBinding6 = this.mEmptySearchView;
            if (emptySearchBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptySearchView");
            } else {
                emptySearchBinding2 = emptySearchBinding6;
            }
            emptySearchBinding2.getRoot().bringToFront();
        }
    } 
    public void onProductsLoadError(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getContext(), str, 1).show();
        }
    } 
    public static final class SeriLotItem implements Serializable, Parcelable {
        public static final Companion Companion = new Companion(null);
        private static final long serialVersionUID = 1;
        private final Creator<SeriLotItem> CREATOR = new ProductListFragmentSeriLotItemCREATOR1();
        private int itemRef;
        private Serilot serilot;
 
        public int describeContents() {
            return 0;
        }

        public Serilot getSerilot() {
            return this.serilot;
        }

        public void setSerilot(Serilot serilot) {
            this.serilot = serilot;
        }

        public int getItemRef() {
            return this.itemRef;
        }

        public void setItemRef(int i) {
            this.itemRef = i;
        }
 
        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeParcelable(this.serilot, i);
            parcel.writeInt(this.itemRef);
        }

        public SeriLotItem(Serilot serilot, int i) {
            this.serilot = serilot;
            this.itemRef = i;
        }
 
        public SeriLotItem(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            this.serilot = parcel.readParcelable(Serilot.class.getClassLoader());
            this.itemRef = parcel.readInt();
        }

        public Creator<SeriLotItem> getCREATOR() {
            return this.CREATOR;
        }
 
        public static final class Companion {
            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    } 
    public void processFinish(REPORTXML reportxml, ProcessType processType) {
        List emptyList;
        Intrinsics.checkNotNullParameter(reportxml, "reportxml");
        this.seriLotItems = new ArrayList<>();
        this.seriItemRefList.clear();
        List<REPORTLINE> list = reportxml.reportLines;
        if (list != null) {
            int size = list.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    REPORTLINE reportline = reportxml.reportLines.get(i);
                    Intrinsics.checkNotNullExpressionValue(reportline, "get(...)");
                    REPORTLINE reportline2 = reportline;
                    Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                    serilot.logicalRef = StringUtils.convertStringToInt(reportline2.f1204X);
                    serilot.code = reportline2.f1205Y;
                    String str = reportline2.f1206Z;
                    Intrinsics.checkNotNullExpressionValue(str, "Z");
                    serilot.reAmount = Float.parseFloat(str);
                    String str2 = reportline2.f1206Z;
                    Intrinsics.checkNotNullExpressionValue(str2, "Z");
                    serilot.amount = Float.parseFloat(str2);
                    serilot.unit = reportline2.f1203W;
                    String str3 = reportline2.f1199A;
                    Intrinsics.checkNotNullExpressionValue(str3, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                    List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(str3, 0);
                    if (!split.isEmpty()) {
                        ListIterator<String> listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (listIterator.previous().length() != 0) {
                                emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList = CollectionsKt.emptyList();
                    serilot.expDate = DateAndTimeUtils.convertYMDToDMY(((String[]) emptyList.toArray(new String[0]))[0]);
                    String str4 = reportline2.f1200B;
                    Intrinsics.checkNotNullExpressionValue(str4, "B");
                    serilot.name = str4;
                    SeriLotItem seriLotItem = new SeriLotItem(serilot, StringUtils.convertStringToInt(reportline2.f1201K));
                    ArrayList<SeriLotItem> arrayList = this.seriLotItems;
                    Intrinsics.checkNotNull(arrayList);
                    arrayList.add(seriLotItem);
                    ArrayList<Integer> arrayList2 = this.seriItemRefList;
                    ArrayList<SeriLotItem> arrayList3 = this.seriLotItems;
                    Intrinsics.checkNotNull(arrayList3);
                    arrayList2.add(Integer.valueOf(arrayList3.get(i).getItemRef()));
                }
                this.isFirstResponse = false;
                restartLoader();
                return;
            }
            onProductLoads(this.tempBarcodeProductList);
            return;
        }
        onProductLoads(this.tempBarcodeProductList);
    }

    private SelectResult checkSeriLotListWCF(String str) {
        LogoTigerTableName logoTigerTableName = new LogoTigerTableName(this.viewModel.getUserInformation().getDbName(), USER.firmano, USER.periodNr);
        SelectResult selectResult = new SelectResult();
        String sb2 = "AND (ST.INVENNO = " +
                this.mWareHouseNr +
                ") ";
        String sb3 = "AND (SL.CODE IN (" +
                str +
                ") ) ";
        String sb = "SELECT  ST.LOGICALREF [X], ST.AMOUNT[Z] , SL.CODE [Y], USLINE.CODE[W] , ST.EXPDATE [A] , SL.NAME [B] , ST.ITEMREF [K] FROM " + logoTigerTableName.SLTRANS + " ST WITH(NOLOCK) LEFT OUTER JOIN " + logoTigerTableName.SERILOTN + " SL WITH(NOLOCK) ON  (ST.SLREF  =  SL.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.STFICHE + " STFIC WITH(NOLOCK) ON  (ST.STFICHEREF  =  STFIC.LOGICALREF) LEFT OUTER JOIN " + logoTigerTableName.UNITSETL + " USLINE WITH(NOLOCK)  ON (ST.UOMREF  =  USLINE.LOGICALREF) WHERE (ST.CANCELLED = 0) AND (ST.LPRODSTAT = 0) " +
                sb2 +
                "AND (ST.EXIMFCTYPE IN ( 0 , 4 , 7 )) AND (ST.STATUS = 0) AND (ST.DATE_ <= CONVERT(dateTime, GETDATE(), 101)) AND (ST.IOCODE IN (1,2)) " +
                sb3 +
                "AND (ST.SLTYPE=" + TrackType.SERIAL.getType() + SqlLiteVariable._CLOSE_BRACKET +
                "AND (ST.REMAMOUNT > 0) AND (SL.STATE=1) ";
        selectResult.sql = sb;
        selectResult.orderByText = "ORDER BY Y,X ";
        selectResult.type = ProcessType.GETCHECKSERILOT;
        return selectResult;
    }
 
    public void checkSeriLotAfterBarcode(ArrayList<BarcodeResult> arrayList) {
        String str;
        if (TextUtils.isEmpty(StringUtils.getBarcodeText(arrayList))) {
            str = '\'' + this.mFilter + '\'';
        } else {
            str = StringUtils.getBarcodeText(arrayList);
        }
        new ServicesClientForTiger.ReportRetrieve(checkSeriLotListWCF(str)).execute();
    } 
    public ArrayList<BarcodeResult> getSeriBarcodeList() {
        ArrayList<BarcodeResult> arrayList = new ArrayList<>();
        if (this.isBarcodeSearch) {
            if (this.mBarcodeSingleSelect) {
                controlBarcodeItemOnSeriBarcodeList(arrayList);
            } else {
                controlBarcodeFilterOnSeriBarcodeList(arrayList);
            }
        }
        return arrayList;
    }

    private void controlBarcodeItemOnSeriBarcodeList(ArrayList<BarcodeResult> arrayList) {
        BarcodeItem barcodeItem = this.mBarcodeItem;
        if (barcodeItem != null) {
            Intrinsics.checkNotNull(barcodeItem);
            if (barcodeItem.getKey().getItemRef() == 0) {
                BarcodeItem barcodeItem2 = this.mBarcodeItem;
                Intrinsics.checkNotNull(barcodeItem2);
                arrayList.add(barcodeItem2.getKey());
            }
        }
    }

    private void controlBarcodeFilterOnSeriBarcodeList(ArrayList<BarcodeResult> arrayList) {
        ArrayList<BarcodeResult> arrayList2 = this.mBarcodeFilter;
        if (arrayList2 != null) {
            Intrinsics.checkNotNull(arrayList2);
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                ArrayList<BarcodeResult> arrayList3 = this.mBarcodeFilter;
                Intrinsics.checkNotNull(arrayList3);
                if (arrayList3.get(i).getItemRef() == 0) {
                    ArrayList<BarcodeResult> arrayList4 = this.mBarcodeFilter;
                    Intrinsics.checkNotNull(arrayList4);
                    arrayList.add(arrayList4.get(i));
                }
            }
        }
    }
 
    public String getLoaderSqlText(int i, int i2) {
        String str;
        long nanoTime = System.nanoTime();
        ProductListViewModel productListViewModel = this.viewModel;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        String productSortListSql = productListViewModel.getProductSortListSql(requireContext, this.mProductListParameter, this.mDefOrderId);
        ProductListShowType productListShowType = this.mProductListShowType;
        if (productListShowType == ProductListShowType.ALL) {
            PrimitiveCompanionObjects stringCompanionObject = PrimitiveCompanionObjects.INSTANCE;
            str = String.format("%s UNION %s %s %s", Arrays.copyOf(new Object[]{getProductSql(productSortListSql), getServiceSql(productSortListSql), productSortListSql, ContextUtils.formatStringEnglish(getString(R.string.qry_limit), Integer.valueOf(i), Integer.valueOf(i2))}, 4));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        } else {
            PrimitiveCompanionObjects stringCompanionObject2 = PrimitiveCompanionObjects.INSTANCE;
            str = String.format("%s %s %s", Arrays.copyOf(new Object[]{productListShowType == ProductListShowType.PRODUCT ? getProductSql(productSortListSql) : getServiceSql(productSortListSql), productSortListSql, ContextUtils.formatStringEnglish(getString(R.string.qry_limit), Integer.valueOf(i), Integer.valueOf(i2))}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        }
        String format = String.format("GET SQL  (%sms)", Arrays.copyOf(new Object[]{Long.valueOf(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        Log.d(TAG, format);
        String replace = new Regex("\\s*[\\r\\n]+\\s*").replace(str, "");
        int length = replace.length() - 1;
        int i3 = 0;
        boolean z = false;
        while (i3 <= length) {
            boolean z2 = Intrinsics.compare(replace.charAt(!z ? i3 : length), 32) <= 0;
            if (!z) {
                if (!z2) {
                    z = true;
                } else {
                    i3++;
                }
            } else if (!z2) {
                break;
            } else {
                length--;
            }
        }
        return replace.subSequence(i3, length + 1).toString();
    }

    private String getProductSql(String str) {
        int i;
        ArrayList<BarcodeResult> arrayList;
        if (!TextUtils.isEmpty(this.mFilter) && (arrayList = this.mBarcodeFilter) != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                this.mBarcodeSingleSelect = true;
            }
        }
        ProductListParameter productListParameter = this.mProductListParameter;
        Intrinsics.checkNotNull(productListParameter);
        productListParameter.setProductTreeList(this.mGroupCodeList);
        if (SalesUtils.isSalesTypeDemand(this.mSalesType)) {
            i = this.mSourceWareHouseNr;
        } else {
            i = this.mWareHouseNr;
        }
        return this.viewModel.getProductListSql(new ProductListViewModel.ProductSqlDataModel(getContext(), this.mProductListParameter, this.mCustomerRef, this.mPaymentTradeGroup, i, this.mBarcodeFilter, this.mFilter, this.mDefOrderId, str, this.isBarcodeSearch, this.seriItemRefList, this.mPaymentRef, "", this.mProductGroupCode, this.mDivisionNr, this.recommendedProductList));
    }

    private String getServiceSql(String str) {
        ArrayList<BarcodeResult> arrayList;
        if (!TextUtils.isEmpty(this.mFilter) && (arrayList = this.mBarcodeFilter) != null) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.size() > 0) {
                this.mBarcodeSingleSelect = true;
            }
        }
        return this.viewModel.getServiceListSql(new ProductListViewModel.ProductSqlDataModel(requireContext(), this.mProductListParameter, this.mCustomerRef, this.mPaymentTradeGroup, this.mWareHouseNr, this.mBarcodeFilter, this.mFilter, this.mDefOrderId, str, false, null, this.mPaymentRef, "", null, 0, this.recommendedProductList, 26112, null));
    }
 
    public static final class ProductListResponseListener implements ResponseListener<ArrayList<Product>> {
        private final boolean isFirstLoader;
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductListResponseListener(ProductListFragment productListFragment, boolean z) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
            this.isFirstLoader = z;
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    if (!isBarcodeSerialCheck()) {
                        ArrayList arrayList2 = new ArrayList();
                        ProductListFragment productListFragment2 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment2);
                        arrayList2.addAll(productListFragment2.tempBarcodeProductList);
                        Intrinsics.checkNotNull(arrayList);
                        arrayList2.addAll(arrayList);
                        ProductListFragment productListFragment3 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment3);
                        productListFragment3.tempBarcodeProductList.clear();
                        ProductListFragment productListFragment4 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment4);
                        productListFragment4.onProductLoads(arrayList2);
                    } else if (this.isFirstLoader) {
                        ProductListFragment productListFragment5 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment5);
                        Intrinsics.checkNotNull(arrayList);
                        productListFragment5.tempBarcodeProductList = arrayList;
                        ProductListFragment productListFragment6 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment6);
                        ProductListFragment productListFragment7 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment7);
                        productListFragment6.checkSeriLotAfterBarcode(productListFragment7.getSeriBarcodeList());
                    } else {
                        ProductListFragment productListFragment8 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment8);
                        productListFragment8.onProductLoads(arrayList);
                    }
                    ProductListFragment productListFragment9 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment9);
                    ProductListFragment productListFragment10 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment10);
                    productListFragment9.addCrumb(productListFragment10.productListCrumb);
                    ProductListFragment productListFragment11 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment11);
                    productListFragment11.hideProductTree(true);
                }
            }
        }

        private boolean isBarcodeSerialCheck() {
            ProductListFragment productListFragment = this.mProductListFragment.get();
            Intrinsics.checkNotNull(productListFragment);
            if (productListFragment.isBarcodeSearch) {
                ProductListFragment productListFragment2 = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment2);
                if (SalesUtils.isSalesTypeInvoiceOrRetailInvoiceOrDispatch(productListFragment2.getmSalesType())) {
                    ProductListFragment productListFragment3 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment3);
                    if (productListFragment3.getSeriBarcodeList().size() > 0) {
                        ProductListFragment productListFragment4 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment4);
                        if (productListFragment4.isFirstResponse) {
                            ProductListFragment productListFragment5 = this.mProductListFragment.get();
                            Intrinsics.checkNotNull(productListFragment5);
                            return productListFragment5.viewModel.erpType() == ErpType.TIGER;
                        }
                    }
                }
            }
            return false;
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    Log.d("AA", "onError: " + str);
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onProductsLoadError(str);
                }
            }
        }
    } 
    public void onOnlineStockGetDone(boolean z, String str) {
        this.mStockGet = true;
        checkCompleteGet();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 1).show();
        }
        if (z) {
            this.viewModel.getSaveStockLastTransDate();
        }
    }
 
    public void onOnlinePriceGetDone(boolean z, String str) {
        if (z) {
            this.mPriceGet = true;
            checkCompleteGet();
            if (!TextUtils.isEmpty(str)) {
                Toast.makeText(getActivity(), str, 1).show();
            }
        }
    }

    private void checkCompleteGet() {
        synchronized (lock) {
            try {
                ProductListParameter productListParameter = this.mProductListParameter;
                Intrinsics.checkNotNull(productListParameter);
                if (productListParameter.isOnlineStockGet()) {
                    ProductListParameter productListParameter2 = this.mProductListParameter;
                    Intrinsics.checkNotNull(productListParameter2);
                    if (productListParameter2.isOnlinePriceGet() && this.mStockGet && this.mPriceGet) {
                        setStockAndPrice();
                        Unit unit = Unit.INSTANCE;
                    }
                }
                if (this.mStockGet || this.mPriceGet) {
                    setStockAndPrice();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setStockAndPrice() {
        getMProgressDialogBuilder().dismiss();
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = null;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
            if (appBarSwipeRefreshLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            } else {
                appBarSwipeRefreshLayout2 = appBarSwipeRefreshLayout3;
            }
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        this.mStockGet = false;
        this.mPriceGet = false;
        restartLoader();
    } 
    public static final class ProductOnlineGetListener implements ResponseListener<Boolean> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductOnlineGetListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel bool) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    Intrinsics.checkNotNull(bool);
                    productListFragment2.onOnlineProductGetDone(bool.booleanValue(), "");
                }
            }
        }
 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    Log.d("AA", "onError: " + str);
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onOnlineProductGetDone(false, str);
                }
            }
        }
    } 
    public void onOnlineProductGetDone(boolean z, String str) {
        if (z) {
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            if (productListParameter.isOnlinePriceGet()) {
                this.viewModel.getOnlinePriceAllByProductGetDone(new ProductOnlinePriceGetListener(this));
            }
            ProductListParameter productListParameter2 = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter2);
            if (!productListParameter2.isOnlineStockGet() || this.mProductListShowType == ProductListShowType.SERVICE) {
                this.mStockGet = true;
            } else {
                this.viewModel.getOnlineStockAllByProductGetDone(new ProductOnlineStockGetListener(this));
            }
        } else {
            getMProgressDialogBuilder().dismiss();
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
            AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = null;
            if (appBarSwipeRefreshLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
                appBarSwipeRefreshLayout = null;
            }
            if (appBarSwipeRefreshLayout.isRefreshing()) {
                AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = this.mSwipeRefreshLayout;
                if (appBarSwipeRefreshLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
                } else {
                    appBarSwipeRefreshLayout2 = appBarSwipeRefreshLayout3;
                }
                appBarSwipeRefreshLayout2.setRefreshing(false);
            }
            Toast.makeText(getActivity(), R.string.exp_42_transfer_product_get_error, 1).show();
            restartLoader();
        }
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 1).show();
        }
    }
 
    public static final class ProductOnlineStockGetListener implements ResponseListener<Boolean> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductOnlineStockGetListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel bool) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    Intrinsics.checkNotNull(bool);
                    productListFragment2.onOnlineStockGetDone(bool.booleanValue(), "");
                }
            }
        }
 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    Log.d("AA", "onError: " + str);
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onOnlineStockGetDone(false, str);
                }
            }
        }
    }
 
    public static final class ProductOnlinePriceGetListener implements ResponseListener<Boolean> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductOnlinePriceGetListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel bool) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    Intrinsics.checkNotNull(bool);
                    productListFragment2.onOnlinePriceGetDone(bool.booleanValue(), "");
                }
            }
        } 
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    Log.d("AA", "onError: " + str);
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.onOnlinePriceGetDone(false, str);
                }
            }
        }
    }

    public void scanBarcodeFromFragment() {
        if (PermissionUtils.checkPermissionFromFragment(this, "android.permission.CAMERA", getString(R.string.str_camera_permission_for_barcode))) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(IntentIntegrator.ONE_D_CODE_TYPES.toString());
            arrayList.add("QR_CODE");
            boolean z = false;
            IntentIntegrator addExtra = IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).setDesiredBarcodeFormats(arrayList).setCaptureActivity(BarcodeScannerView.class).addExtra(IntentExtraName.EXTRA_SELECT_TYPE, Boolean.valueOf(this.mBarcodeSingleSelect));
            String str = IntentExtraName.BARCODE_FOR_SEARCH;
            if (this.mWareHouseNr == -1) {
                z = true;
            }
            addExtra.addExtra(str, Boolean.valueOf(z)).addExtra(BarcodeScannerView.EXTRA_CUSTOMER_REF, Integer.valueOf(this.mCustomerRef)).addExtra(BarcodeScannerView.EXTRA_WAREHOUSE_NR, Integer.valueOf(this.mWareHouseNr)).addExtra(BarcodeScannerView.EXTRA_SALES_TYPE, Integer.valueOf(this.mSalesType.getmValue())).addExtra(BarcodeScannerView.EXTRA_DEF_ORDER_ID, Integer.valueOf(this.mDefOrderId)).addExtra(BarcodeScannerView.EXTRA_PAYMENT_TRADE_GROUP, this.mPaymentTradeGroup).addExtra(BarcodeScannerView.EXTRA_PAYMENT_REF, Integer.valueOf(this.mPaymentRef)).addExtra(BarcodeScannerView.EXTRA_PRODUCT_GROUP_CODE, this.mProductGroupCode).addExtra(BarcodeScannerView.EXTRA_DIVISION_NR, Integer.valueOf(this.mDivisionNr)).addExtra(BarcodeScannerView.EXTRA_SPECODE, this.mSpecode).initiateScan();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        if (i == 1074) {
            if ((iArr.length == 0) || iArr[0] != 0) {
                Toast.makeText(getContext(), getString(R.string.str_permissions_denied), 1).show();
            } else {
                scanBarcodeFromFragment();
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
 
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 49374 || i2 != -1) {
            return;
        }
        if (intent != null) {
            Log.d(TAG, "onActivityResult: " + this.mBarcodeSingleSelect);
            setBarcodeSingleSelectWithData(intent, i2);
            return;
        }
        Log.d(TAG, "onActivityResult: barcode result null");
    }

    private void setBarcodeSingleSelectWithData(Intent intent, int i) {
        if (this.mBarcodeSingleSelect) {
            BarcodeItem barcodeItem = intent.getParcelableExtra("SCAN_RESULT");
            this.mBarcodeItem = barcodeItem;
            if (barcodeItem != null && barcodeItem.getKey() != null) {
                setSearchViewWithItem(barcodeItem);
                return;
            }
            return;
        }
        setBarcodeFilterResult(i, intent);
    }

    private void setBarcodeFilterResult(int i, Intent intent) {
        ArrayList<BarcodeResult> parcelableArrayListExtra;
        if (i == -1 && (parcelableArrayListExtra = intent.getParcelableArrayListExtra("SCAN_RESULT")) != null && parcelableArrayListExtra.size() > 0) {
            this.mBarcodeFilter = parcelableArrayListExtra;
            this.isBarcodeSearch = true;
            restartLoader();
        }
    }

    private void setSearchViewWithItem(BarcodeItem barcodeItem) {
        if (!TextUtils.isEmpty(barcodeItem.getKey().getBarcode())) {
            Log.i(TAG, "onActivityResult: " + barcodeItem.getKey().getBarcode());
            this.mFilter = barcodeItem.getProduct() != null ? barcodeItem.getProduct().getCode() : barcodeItem.getKey().getBarcode();
            ProductListActivity productListActivity = (ProductListActivity) getActivity();
            Intrinsics.checkNotNull(productListActivity);
            String str = this.mFilter;
            Intrinsics.checkNotNull(str);
            productListActivity.setMLastFilter(str);
            SearchView searchView = this.searchView;
            SearchView searchView2 = null;
            if (searchView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView = null;
            }
            searchView.setQuery(this.mFilter, false);
            SearchView searchView3 = this.searchView;
            if (searchView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
                searchView3 = null;
            }
            searchView3.setIconified(false);
            SearchView searchView4 = this.searchView;
            if (searchView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
            } else {
                searchView2 = searchView4;
            }
            searchView2.clearFocus();
            this.mBarcodeSingleSelect = true;
            this.isBarcodeSearch = true;
            restartLoader();
            return;
        }
        Log.d(TAG, "onActivityResult: barcode result null");
    }

    private void clearFilter() {
        this.mFilter = "";
        ProductListActivity productListActivity = (ProductListActivity) getActivity();
        Intrinsics.checkNotNull(productListActivity);
        String str = this.mFilter;
        Intrinsics.checkNotNull(str);
        productListActivity.setMLastFilter(str);
        this.mBarcodeSingleSelect = false;
        SearchView searchView = this.searchView;
        SearchView searchView2 = null;
        if (searchView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView = null;
        }
        searchView.setQuery(this.mFilter, false);
        SearchView searchView3 = this.searchView;
        if (searchView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView3 = null;
        }
        searchView3.setIconified(false);
        SearchView searchView4 = this.searchView;
        if (searchView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView4 = null;
        }
        searchView4.clearFocus();
        SearchView searchView5 = this.searchView;
        if (searchView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
        } else {
            searchView2 = searchView5;
        }
        searchView2.requestFocus();
    }

    private void showInputMethod(View view) {
        if (isAttached()) {
            Object systemService = requireActivity().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).showSoftInput(view, 0);
        }
    }

    public SalesType getmSalesType() {
        return this.mSalesType;
    }

    public void getItemSurplusDiscount(int i, String str) { 
        if (ContextUtils.checkInternetConnection()) {
            getMProgressSurplusDialogBuilder().setMessage(getString(R.string.str_please_wait)).show();
            ProductListViewModel productListViewModel = this.viewModel;
            Intrinsics.checkNotNull(str);
            productListViewModel.getSurplusDiscountForItem(i, str, new ProductInfoResponseListener(this));
        }
    }
 
    public static final class ProductInfoResponseListener implements ResponseListener<ArrayList<SurplusDiscount>> {
        private final WeakReference<ProductListFragment> mProductListFragment;

        public ProductInfoResponseListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "productListFragment");
            this.mProductListFragment = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.getMProgressSurplusDialogBuilder().dismiss();
                    if (arrayList == null || arrayList.size() <= 0) {
                        ProductListFragment productListFragment3 = this.mProductListFragment.get();
                        Intrinsics.checkNotNull(productListFragment3);
                        Toast.makeText(productListFragment3.getContext(), R.string.str_no_condition_for_product, 0).show();
                        return;
                    }
                    ProductListFragment productListFragment4 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment4);
                    productListFragment4.showSurplusDiscountList(arrayList);
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mProductListFragment.get() != null) {
                ProductListFragment productListFragment = this.mProductListFragment.get();
                Intrinsics.checkNotNull(productListFragment);
                if (productListFragment.isAttached()) {
                    ProductListFragment productListFragment2 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    productListFragment2.getMProgressSurplusDialogBuilder().dismiss();
                    ProductListFragment productListFragment3 = this.mProductListFragment.get();
                    Intrinsics.checkNotNull(productListFragment3);
                    Toast.makeText(productListFragment3.getContext(), str, 0).show();
                }
            }
        }
    }
    public void showSurplusDiscountList(ArrayList<SurplusDiscount> arrayList) {
        try {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.recyclerviewlist, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(inflate);
            RecyclerView recyclerView = inflate.findViewById(R.id.recyclerViewList);
            recyclerView.setLayoutManager(new SnappyLinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            SurplusDiscountListAdapter surplusDiscountListAdapter = new SurplusDiscountListAdapter();
            recyclerView.setAdapter(surplusDiscountListAdapter);
            surplusDiscountListAdapter.setSurplusDiscounts(CollectionsKt.toList(arrayList));
            builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception e) {
            Log.e("AA", "showSurplusDiscountList: ", e);
        }
    }
    private void refreshProductTreeList() {
        ProgressDialogBuilder message = getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait));
        if (message != null) {
            message.show();
        }
        ProductListViewModel productListViewModel = this.viewModel;
        ArrayList<String> arrayList = this.mGroupCodeList;
        ProductTreeItem productTreeItem = this.mSelectedProductTreeItem;
        Intrinsics.checkNotNull(productTreeItem);
        productListViewModel.getProductTreeListItems(arrayList, productTreeItem, new ProductTreeListener(this));
    }
    public void setProductTreeData(ArrayList<ProductTreeItem> arrayList) {
        String str;
        if (arrayList.size() == 0) {
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            productListParameter.setProductTreeGroupListType(ProductTreeGroupListType.All);
            loadProduct();
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        RecyclerView recyclerView = null;
        if (arrayList.get(0).getParentItem() != null) {
            ProductTreeItem parentItem = arrayList.get(0).getParentItem();
            str = parentItem != null ? parentItem.getDescription() : null;
        } else {
            str = getString(R.string.str_activity_title_product_groups);
        }
        requireActivity.setTitle(str);
        this.mProductTreeData = arrayList;
        this.mProductTreeRecyclerViewAdapter.setData(arrayList);
        RecyclerView recyclerView2 = this.mProductTreeRecycleView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.setAdapter(this.mProductTreeRecyclerViewAdapter);
        hideProductList();
        boolean z = this.mProductTreeData.size() == 0;
        String str2 = this.mFilter;
        Intrinsics.checkNotNull(str2);
        toggleEmptyView(z, str2);
        this.finishAddProduct = false;
        this.mAdapter.setSelectProductShow(false);
        toggleFab();
    }
    public void hideProductTree(boolean z) {
        RecyclerView recyclerView = this.mProductTreeRecycleView;
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
            recyclerView = null;
        }
        recyclerView.setVisibility(4);
        if (z) {
            appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
            if (appBarSwipeRefreshLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
                appBarSwipeRefreshLayout = null;
            }
            if (appBarSwipeRefreshLayout.getVisibility() == 4) {
                appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
                if (appBarSwipeRefreshLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
                }
                appBarSwipeRefreshLayout.setVisibility(0);
                setMenuItemVisible();
            }
        }
    }

    private void hideProductList() {
        AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.mSwipeRefreshLayout;
        RecyclerView recyclerView = null;
        if (appBarSwipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSwipeRefreshLayout");
            appBarSwipeRefreshLayout = null;
        }
        appBarSwipeRefreshLayout.setVisibility(4);
        setProductTreeMenuItemVisible();
        RecyclerView recyclerView2 = this.mProductTreeRecycleView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductTreeRecycleView");
        } else {
            recyclerView = recyclerView2;
        }
        recyclerView.setVisibility(0);
        addCrumb(this.treeListCrumb);
    }
    private void setProductTreeMenuItemVisible() {
        MenuItem menuItem = this.mSearchMenuItem;
        if (menuItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSearchMenuItem");
            menuItem = null;
        }
        setMenuItemVisible(menuItem, true);
        setMenuItemVisible(this.mSortMenuItem, false);
        setMenuItemVisible(this.mBarcodeMenuItem, true);
        setMenuItemVisible(this.mShowAll, false);
        setMenuItemVisible(this.mShowOnlyProduct, false);
        setMenuItemVisible(this.mShowOnlyService, false);
        setMenuItemVisible(this.mFilterByGroup, false);
        setMenuItemVisible(this.menuRecommendedProducts, false);
    }
    public void showProductTreeMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "errorMessage");
        dismissProductTreeProgress();
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
    public void dismissProductTreeProgress() {
        getMProgressDialogBuilder().dismiss();
    }
    public static final class ProductTreeListener implements ResponseListener<ArrayList<ProductTreeItem>> {
        private final WeakReference<ProductListFragment> mContent;

        public ProductTreeListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "mContent");
            this.mContent = new WeakReference<>(productListFragment);
        }

        public void onResponse(PrintSlipModel arrayList) {
            if (this.mContent.get() != null) {
                ProductListFragment productListFragment = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment);
                Intrinsics.checkNotNull(arrayList);
                productListFragment.setProductTreeData(arrayList);
                ProductListFragment productListFragment2 = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment2);
                productListFragment2.dismissProductTreeProgress();
            }
        }

        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mContent.get() != null) {
                ProductListFragment productListFragment = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment);
                productListFragment.dismissProductTreeProgress();
                Log.d(ProductListFragment.TAG, "onError: " + str);
                ProductListFragment productListFragment2 = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment2);
                productListFragment2.showProductTreeMessage(str);
            }
        }
    }
    public void addCrumb(String str) {
        if (Intrinsics.areEqual(str, this.treeListCrumb)) {
            str = this.treeListCrumb + this.mGroupCodeList.size();
        } else {
            requireActivity().setTitle(R.string.activity_title_product);
        }
        if (!this.mCrumbList.contains(str)) {
            this.mCrumbList.add(str);
        }
    }
    private String dequeueLastCrumbItem() {
        if (this.mCrumbList.size() == 0) {
            return "";
        }
        ArrayList<String> arrayList = this.mCrumbList;
        arrayList.remove(arrayList.size() - 1);
        if (this.mCrumbList.size() == 0) {
            return "";
        }
        ArrayList<String> arrayList2 = this.mCrumbList;
        String str = arrayList2.get(arrayList2.size() - 1);
        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
        return str;
    }
    public class FormGroupResponseListener implements ResponseListener<Resource<List<? extends FormGroupModel>>> {
        private final WeakReference<ProductListFragment> mContent;
        public class WhenMappings {
            public static final int[] EnumSwitchMapping0;

            static {
                int[] iArr = new int[Status.values().length];
                try {
                    iArr[Status.LOADING.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Status.ERROR.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Status.SUCCESS.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                EnumSwitchMapping0 = iArr;
            }
        }
        public FormGroupResponseListener(ProductListFragment productListFragment) {
            Intrinsics.checkNotNullParameter(productListFragment, "mContent");
            this.mContent = new WeakReference<>(productListFragment);
        }

        public void onResponse(Resource<List<FormGroupModel>> resource) {
            if (this.mContent.get() != null) {
                Intrinsics.checkNotNull(resource);
                int i = WhenMappings.EnumSwitchMapping0[resource.getStatus().ordinal()];
                if (i == 1) {
                    ProductListFragment productListFragment = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment);
                    ProgressDialogBuilder<?> mProgressDialogBuilder = productListFragment.getMProgressDialogBuilder();
                    ProductListFragment productListFragment2 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment2);
                    ProgressDialogBuilder message = mProgressDialogBuilder.setMessage(productListFragment2.getString(R.string.str_please_wait));
                    if (message != null) {
                        message.show();
                    }
                } else if (i == 2) {
                    ProductListFragment productListFragment3 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment3);
                    productListFragment3.getMProgressDialogBuilder().dismiss();
                    ProductListFragment productListFragment4 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment4);
                    Toast.makeText(productListFragment4.getContext(), resource.getException(), 0).show();
                } else if (i == 3) {
                    ProductListFragment productListFragment5 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment5);
                    List<FormGroupModel> data = resource.getData();
                    Intrinsics.checkNotNull(data);
                    productListFragment5.formGroupModelList = data;
                    ProductListFragment productListFragment6 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment6);
                    productListFragment6.fillSpinners();
                    ProductListFragment productListFragment7 = this.mContent.get();
                    Intrinsics.checkNotNull(productListFragment7);
                    productListFragment7.getMProgressDialogBuilder().dismiss();
                }
            }
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mContent.get() != null) {
                ProductListFragment productListFragment = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment);
                productListFragment.getMProgressDialogBuilder().dismiss();
                ProductListFragment productListFragment2 = this.mContent.get();
                Intrinsics.checkNotNull(productListFragment2);
                Toast.makeText(productListFragment2.getContext(), str, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void fillSpinners() {
        List list = (List) this.formGroupModelList.stream().map(new Function() {
            public Object apply(Object obj) {
                return ProductListFragment.fillSpinnerslambda20(Function1.this, obj);
            }
        }).collect(Collectors.toList());
        int indexOf = list.indexOf(getString(R.string.str_other_forms));
        if (indexOf != -1) {
            list.remove(indexOf);
            list.add(getString(R.string.str_other_forms));
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        @SuppressLint("ResourceType") ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext, 17367048, list);
        arrayAdapter.setDropDownViewResource(17367049);
        AppCompatSpinner appCompatSpinner = this.frmGroupSpinner;
        AppCompatSpinner appCompatSpinner2 = null;
        if (appCompatSpinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frmGroupSpinner");
            appCompatSpinner = null;
        }
        appCompatSpinner.setAdapter(arrayAdapter);
        AppCompatSpinner appCompatSpinner3 = this.frmGroupSpinner;
        if (appCompatSpinner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frmGroupSpinner");
        } else {
            appCompatSpinner2 = appCompatSpinner3;
        }
        appCompatSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
            final ProductListFragment this0 = null;
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Intrinsics.checkNotNullParameter(view, "view");
                AppCompatSpinner appCompatSpinner4 = this.this0.frmGroupSpinner;
                if (appCompatSpinner4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frmGroupSpinner");
                    appCompatSpinner4 = null;
                }
                this.this0.fillFormSpinner(appCompatSpinner4.getSelectedItem().toString());
            }
        });
    }
    public static String fillSpinnerslambda20(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return (String) function1.invoke(obj);
    }
    public void fillFormSpinner(String str) {
        this.formGroupModelList.stream().filter(new Predicate() {
            public boolean test(Object obj) {
                return ProductListFragment.fillFormSpinnerlambda21(Function1.this, obj);
            }
        }).findFirst().ifPresent(new java.util.function.Consumer() {
            public void accept(Object obj) {
                ProductListFragment.fillFormSpinnerlambda22(Function1.this, obj);
            }
        });
    }
    public static boolean fillFormSpinnerlambda21(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }
    public static void fillFormSpinnerlambda22(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        function1.invoke(obj);
    }
    private void toggleSpinners() {
        View view = this.frameForm;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameForm");
            view2 = null;
        } else {
            view2 = view;
        }
        int i = View.GONE;
        view.setVisibility((this.mDefOrderId <= 0 || this.finishAddProduct) ? View.GONE : View.VISIBLE);
        View view3 = this.frameGroup;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("frameGroup");
            view2 = null;
        } else {
            view2 = view3;
        }
        if (this.mDefOrderId > 0 && !this.finishAddProduct) {
            i = View.VISIBLE;
        }
        view2.setVisibility(i);
    }
    private void setCheckAllTextForFormSelectionAfterProductsLoad() {
        TextView textView;
        if (this.mDefOrderId > 0) {
            ArrayList<Product> products = this.mAdapter.getProducts();
            ArrayList<Integer> arrayList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(products, 10));
            Iterator<Product> it = products.iterator();
            while (true) {
                textView = null;
                Integer num = null;
                if (!it.hasNext()) {
                    break;
                }
                Product product = it.next();
                if (product != null) {
                    num = Integer.valueOf(product.getLogicalRef());
                }
                arrayList.add(num);
            }
            ArrayList<Product> arrayList2 = this.mAdapter.getmSelectedProducts();
            ArrayList arrayList3 = new ArrayList();
            for (Product product2 : arrayList2) {
                Intrinsics.checkNotNull(product2);
                if (arrayList.contains(Integer.valueOf(product2.getLogicalRef()))) {
                    arrayList3.add(product2);
                }
            }
            int size = arrayList3.size();
            TextView textView2 = this.mTwFabCheckAllText;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTwFabCheckAllText");
            } else {
                textView = textView2;
            }
            textView.setText(arrayList.size() == size ? R.string.str_remove_all : R.string.str_select_all);
        }
    }
    public void update(int i) {
        CounterFab counterFab = this.mFabDone;
        if (counterFab == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab = null;
        }
        counterFab.setCount(i);
    }
    public void increase() {
        CounterFab counterFab = this.mFabDone;
        if (counterFab == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab = null;
        }
        counterFab.increase();
    }
    public void decrease() {
        CounterFab counterFab = this.mFabDone;
        if (counterFab == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFabDone");
            counterFab = null;
        }
        counterFab.decrease();
    }
    public void productTreeItemSelected(ProductTreeItem productTreeItem) {
        Intrinsics.checkNotNullParameter(productTreeItem, "item");
        this.mSelectedProductTreeItem = productTreeItem;
        String code = productTreeItem.getCode();
        if (Intrinsics.areEqual(code, getString(R.string.str_all))) {
            ProductListParameter productListParameter = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter);
            productListParameter.setProductTreeGroupListType(ProductTreeGroupListType.All);
            loadProduct();
        } else if (Intrinsics.areEqual(code, getString(R.string.str_others))) {
            ProductListParameter productListParameter2 = this.mProductListParameter;
            Intrinsics.checkNotNull(productListParameter2);
            productListParameter2.setProductTreeGroupListType(ProductTreeGroupListType.Others);
            loadProduct();
        } else if (Intrinsics.areEqual(code, getString(R.string.str_back_to_top))) {
            this.mGroupCodeList.clear();
            this.mCrumbList.clear();
            this.mSelectedProductTreeItem = null;
            refreshProductTreeList();
        } else {
            this.mGroupCodeList.add(productTreeItem.getCode());
            refreshProductTreeList();
        }
    }
    public void onDetach() {
        super.onDetach();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }
    public void onDestroy() {
        this.compositeDisposable.clear();
        super.onDestroy();
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    static {
        String name = ProductListFragment.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        productListFragmentClassName = name;
        EXTRA_FILTER = name + ".EXTRA_FILTER";
        EXTRA_BARCODE_FILTER = name + ".EXTRA_BARCODE_FILTER";
        EXTRA_PRODUCT_SELECT_TYPE = name + ".EXTRA_PRODUCT_SELECT_TYPE";
        EXTRA_SALES_TYPE = name + ".EXTRA_SALES_TYPE";
        EXTRA_WAREHOUSE_NR = name + ".EXTRA_WAREHOUSE_NR";
        EXTRA_SOURCE_WAREHOUSE_NR = name + ".EXTRA_SOURCE_WAREHOUSE_NR";
        EXTRA_DEF_ORDER_ID = name + ".EXTRA_DEF_ORDER_ID";
        EXTRA_PAYMENT_TRADE_GROUP = name + ".EXTRA_PAYMENT_TRADE_GROUP";
        EXTRA_PAYMENT_REF = name + ".EXTRA_PAYMENT_REF";
        EXTRA_SELECTED_PRODUCT_SIZE = name + ".EXTRA_SELECTED_PRODUCT_SIZE";
        EXTRA_PRODUCT_GROUP_CODE = name + ".EXTRA_PRODUCT_GROUP_CODE";
        EXTRA_DIVISION_NR = name + ".EXTRA_DIVISION_NR";
        EXTRA_SPECODE = name + ".EXTRA_SPECODE";
    }
}
