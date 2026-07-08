package com.proje.mobilesales.features.sales.view.newadd;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.data.TigerXmlParserForUpdate;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.Scrollable;
import com.proje.mobilesales.core.netsis.NetsisServiceResult;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.receiver.ConnectivityReceiver;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.core.widget.ViewPager;
import com.proje.mobilesales.databinding.ActivitySalesNewBinding;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.cabinoperation.model.enums.CabinTransTrType;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.driverinformation.model.DriverInfos;
import com.proje.mobilesales.features.driverinformation.model.NEDispatchInfoModel;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.driverinformation.view.activity.DriverInformationActivity;
import com.proje.mobilesales.features.driverinformation.view.activity.EDispatchExtraInfoActivity;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.helper.PdfHelper;
import com.proje.mobilesales.features.sales.model.Distribution;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.sales.repository.SalesNewRepository;
import com.proje.mobilesales.features.sales.utils.PurchasePriceUtils;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragment;
import com.proje.mobilesales.features.sales.view.distribution.DistributionListActivity;
import com.proje.mobilesales.features.sales.view.list.LastOrderProductsActivity;
import com.proje.mobilesales.features.sales.view.order.SalesOrderListActivity;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import kotlin.*;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.*;
import okio.Buffer;
import okio.internal._FileSystemKtcommonListRecursively1;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.proje.mobilesales.core.utils.AppUtils.calculateTotals;
import static com.proje.mobilesales.core.utils.AppUtils.isConnected;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class SalesActivityNew extends BaseFicheActivity implements ConnectivityReceiver.ConnectivityReceiverListener, SalesHeaderFragment.IVatChangeListener {
    private static final String STATE_CABIN_REF = "state:cabinRef";
    private static final String STATE_CUSTOMER_DISCOUNT = "state:customerDiscount";
    private static final String STATE_DUE_DATE = "state:dueDate";
    private static final String STATE_ISDETAIL_ORDER = "state:isDetailOrder";
    private static final String STATE_MATTER_SETTINGS = "state:matterSettings";
    private static final String STATE_RISK_ALERT = "state:riskAlert";
    private static final String STATE_SALES_FICHE_PARAMETERS = "state:salesFicheHeaders";
    private static final String STATE_SALES_TAB_NAME = "state:salesTabName";
    private static final String STATE_UNIT_DECIMAL_FORMATTER = "state:unitDecimalFormatter";
    private static final String TAG = "SalesActivityNew";
    private final Lazy bindingdelegate = LazyKt.lazy(new Function0<ActivitySalesNewBinding>() {
        private Activity this0;

        public ActivitySalesNewBinding invoke() {
            return ActivitySalesNewBinding.inflate(this.this0.getLayoutInflater());
        }
    });
    private int cabinRef;
    private final CoroutineScope customScope;
    private CustomerDiscount customerDiscount;
    private String documentNumberForAppliedCampagin;
    private boolean isOpenFromReportExtract;
    private SalesPagerAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private AlertDialogBuilder<?> mAlertDialogBuilderAlert;
    private AlertDialogBuilder<?> mAlertDialogBuilderCampaign;
    private AlertDialogBuilder<?> mAlertDialogBuilderControl;
    private AlertDialogBuilder<?> mAlertDialogBuilderNotify;
    private AppBarLayout mAppBar;
    private int mAutoVisitId;
    private ProgressDialogBuilder<?> mCampaignProgressDialogBuilder;
    private ProgressDialogBuilder<?> mCheckRateProgressDialogBuilder;
    private CoordinatorLayout mCoordinatorLayout;
    private int mCustomerShipRef;
    private DueDate mDueDate;
    private int mInsteadOfDesp;
    private boolean mIsDetailOrder;
    private FicheMode mLastFicheMode;
    private ReceiptType mLastReceiptType;
    private int mLastSalesDetailLocationPosition;
    private MatterSettings mMatterSettings;
    private ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ProgressDialogBuilder<?> mProgressDialogPrice;
    private RiskAlert mRiskAlert;
    private Sales mSales;
    private SalesFicheParameters mSalesFicheParameters;
    private String[] mSalesTabName;
    private ScreenControl mScreenControl;
    private TabLayout mTabLayout;
    private UnitPriceFormatter mUnitPriceFormatter;
    private ViewPager mViewPager;
    private MenuItem menuAddAllWarehouseItems;
    private MenuItem menuApplyCampaign;
    private MenuItem menuClearCampaign;
    private MenuItem menuDoSalesCondition;
    private MenuItem menuEDespatchInfo;
    private MenuItem menuEDocumentPdf;
    private MenuItem menuLastSalesProducts;
    private MenuItem menuOnlineTotal;
    private MenuItem menuPdf;
    private MenuItem menuReports;
    private MenuItem menuSalesDistributionTransfer;
    private MenuItem menuSalesDriverInformation;
    private MenuItem menuSalesOrderDetailTransfer;
    private MenuItem menuSalesOrderTransfer;
    private MenuItem menuSalesShowCurrencyPrice;
    private MenuItem menuUpdateStockAmounts;
    private PdfHelper pdfHelper;
    private final SalesNewRepository repository;
    private boolean salesDetailsLocationChecked;
    private final SalesNewViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_REF = BaseFicheActivity.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final String EXTRA_CUSTOMER_OPERATION = BaseFicheActivity.class.getName() + ".EXTRA_CUSTOMER_OPERATION";
    public static final String EXTRA_OPEN_FROM_EXTRACT = BaseFicheActivity.class.getName() + ".EXTRA_OPEN_FROM_EXTRACT";
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] r0 = new int[ErpInvoiceType.values().length];
            try {
                r0[ErpInvoiceType.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                r0[ErpInvoiceType.PaperInvoice.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                r0[ErpInvoiceType.EInvoice.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                r0[ErpInvoiceType.EArchiveInvoice.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                r0[ErpInvoiceType.EArchiveInternetInvoice.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = r0;
        }
    }
    protected void initFiche() {
    }

    public SalesActivityNew() {
        SalesNewRepository salesNewRepository = new SalesNewRepository();
        this.repository = salesNewRepository;
        this.viewModel = new SalesNewViewModel(salesNewRepository);
        this.mSales = new Sales(0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
        this.documentNumberForAppliedCampagin = "";
        this.customScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(JobKt.Job(null)));
    }

    private ActivitySalesNewBinding getBinding() {
        return (ActivitySalesNewBinding) this.bindingdelegate.getValue();
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilderCampaign() {
        return this.mAlertDialogBuilderCampaign;
    }

    public void setMAlertDialogBuilderCampaign(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilderCampaign = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilderControl() {
        return this.mAlertDialogBuilderControl;
    }

    public void setMAlertDialogBuilderControl(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilderControl = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilderNotify() {
        return this.mAlertDialogBuilderNotify;
    }

    public void setMAlertDialogBuilderNotify(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilderNotify = alertDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilderAlert() {
        return this.mAlertDialogBuilderAlert;
    }

    public void setMAlertDialogBuilderAlert(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilderAlert = alertDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return this.mProgressDialogBuilder;
    }

    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogBuilder = progressDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMCampaignProgressDialogBuilder() {
        return this.mCampaignProgressDialogBuilder;
    }

    public void setMCampaignProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mCampaignProgressDialogBuilder = progressDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMProgressDialogPrice() {
        return this.mProgressDialogPrice;
    }

    public void setMProgressDialogPrice(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mProgressDialogPrice = progressDialogBuilder;
    }

    public ProgressDialogBuilder<?> getMCheckRateProgressDialogBuilder() {
        return this.mCheckRateProgressDialogBuilder;
    }

    public void setMCheckRateProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        this.mCheckRateProgressDialogBuilder = progressDialogBuilder;
    }

    public CustomerDiscount getCustomerDiscount() {
        return this.customerDiscount;
    }
    public void onCreate(Bundle bundle) throws JsonSyntaxException, Resources.NotFoundException {
        Sales sales;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        FicheMode ficheMode2 = FicheMode.NEW;
        try {
            if ((ficheMode == ficheMode2 || this.mCustomerOperation.getFicheMode() == FicheMode.COPY) && !this.viewModel.checkRouteVisitOutOfOrder(this, this.mCustomerRef, this.routePlanRef, this.routeDayRef)) {
                Toast.makeText(this, getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
                super.onBackPressed();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setContentView(getBinding().getRoot());
        setToolbar();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderCampaign = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderControl = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity3);
        Activity activity4 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderNotify = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity4);
        Activity activity5 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity5, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderAlert = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity5);
        Activity activity6 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity6, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity6);
        Activity activity7 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity7, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mCampaignProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity7);
        Activity activity8 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity8, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogPrice = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity8);
        Activity activity9 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity9, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mCheckRateProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity9);
        this.mScreenControl = new ScreenControl(this);
        this.mTabLayout = getBinding().tabLayout;
        this.mAppBar = getBinding().appbar;
        this.mCoordinatorLayout = getBinding().contentFrame;
        this.mViewPager = getBinding().viewPager;
        if (SalesUtils.isSalesTypeOrder(getSalesType())) {
            ViewPager viewPager = this.mViewPager;
            Intrinsics.checkNotNull(viewPager);
            viewPager.setSwipeEnabled(false);
        }
        if (bundle == null) {
            int intExtra = getIntent().getIntExtra("bigdata:synccode", -1);
            try {
                this.mSales = this.viewModel.getObjectForSales(intExtra, true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.viewModel.clearObject(intExtra);
            this.mSalesTabName = getResources().getStringArray(R.array.array_sales_tab);
            this.mSalesFicheParameters = this.viewModel.getSalesFichePayPlanTypeCash(String.valueOf(this.mCustomerOperation.getSalesType()));
            try {
                this.mRiskAlert = this.viewModel.getCustomerRiskAlert(this.mCustomerOperation.getSalesType(), this.mCustomerRef);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SalesNewViewModel salesNewViewModel = this.viewModel;
            CustomerOperation customerOperation = this.mCustomerOperation;
            SalesType salesType = customerOperation.getSalesType();
            Intrinsics.checkNotNull(salesType);
            try {
                this.mMatterSettings = salesNewViewModel.getMatterSettings(this, customerOperation.getFicheType(salesType));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                this.customerDiscount = this.viewModel.getCustomerDiscountRate(this.mCustomerRef);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.mInsteadOfDesp = this.viewModel.getSqlHelper().getClCardEArchiveInsteadOfDesp(this.mCustomerRef);
            try {
                this.mUnitPriceFormatter = UnitPriceFormatter.getInstance(this.viewModel.getCentOfUnitPriceDigit());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                this.mDueDate = this.viewModel.getDueDate(this.mCustomerOperation.getSalesType(), getmCustomerRef());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.mCustomerShipRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_SHIPREF, 0);
            this.cabinRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CABIN_REF, 0);
            this.mAutoVisitId = getIntent().getIntExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, 0);
            this.isOpenFromReportExtract = getIntent().getBooleanExtra(EXTRA_OPEN_FROM_EXTRACT, false);
            if (this.mSales == null && this.mCustomerOperation.getFicheMode() != ficheMode2) {
                Toast.makeText(this, getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
                super.onBackPressed();
                return;
            }
            if (this.mCustomerOperation.getFicheMode() == ficheMode2) {
                initSales();
                loadDefaultValue();
                loadDefaultFicheValue();
                if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoiceOrDispatchOrOrder(this.mCustomerOperation.getFicheType())) {
                    SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
                    Intrinsics.checkNotNull(salesFicheParameters);
                    if (salesFicheParameters.getMSalesFicheUserRights().isDivFactWareHouseControl()) {
                        warehouseInvoiceLoadControl();
                    }
                }
            } else if (this.mCustomerOperation.getFicheMode() == FicheMode.COPY) {
                initSalesCopy();
            } else if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT && (sales = this.mSales) != null) {
                Intrinsics.checkNotNull(sales);
                if (sales.hasOrderReference()) {
                    setOrderAvailableAmounts();
                }
            }
            FicheMode ficheMode3 = this.mCustomerOperation.getFicheMode();
            FicheMode ficheMode4 = FicheMode.EDIT;
            if (ficheMode3 == ficheMode4) {
                SalesType salesType2 = this.mCustomerOperation.getSalesType();
                Intrinsics.checkNotNull(salesType2);
                if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(salesType2)) {
                    ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
                    Sales sales2 = this.mSales;
                    Intrinsics.checkNotNull(sales2);
                    if (sqlHelper.getInvoiceReceiptRelationIfTransfered(sales2.getLogicalRef(), true)) {
                        Toast.makeText(this, getString(R.string.str_invoice_receipt_cannot_edit), Toast.LENGTH_SHORT).show();
                        super.onBackPressed();
                        return;
                    }
                }
            }
            if (this.mCustomerOperation.getFicheMode() == ficheMode4) {
                Sales sales3 = this.mSales;
                Intrinsics.checkNotNull(sales3);
                try {
                    sales3.setSaleVatCanBeChange(this.viewModel.getVATEnabled());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if ((getSalesType() == SalesType.INVOICE || getSalesType() == SalesType.DISPATCH || getSalesType() == SalesType.WHTRANSFER) && this.viewModel.erpType() == ErpType.TIGER) {
                SalesNewViewModel salesNewViewModel2 = this.viewModel;
                Sales sales4 = this.mSales;
                Intrinsics.checkNotNull(sales4);
                try {
                    if (salesNewViewModel2.getUseEDispatch(sales4.getBranch().getLogicalRef())) {
                        if (getSalesFicheMode() == ficheMode4 || getSalesFicheMode() == FicheMode.ANALYSE) {
                            fillPreviousDriverInfos();
                        } else if (getSalesFicheMode() == ficheMode2) {
                            fillDriverInfoFromSharedPreferences();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            initInvoiceAddress();
            if (getSalesFicheUserRights().getApplyCampaign() == 1) {
                Sales sales5 = this.mSales;
                Intrinsics.checkNotNull(sales5);
                sales5.setCampaign(1);
            }
        } else {
            this.mSalesTabName = bundle.getStringArray(STATE_SALES_TAB_NAME);
            int r1 = bundle.getInt("bigdata:synccode", -1);
            try {
                this.mSales = this.viewModel.getObjectForSales(r1, true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.viewModel.clearObject(r1);
            this.mSalesFicheParameters = bundle.getParcelable(STATE_SALES_FICHE_PARAMETERS);
            this.mRiskAlert = RiskAlert.Companion.fromRiskAlert(bundle.getInt(STATE_RISK_ALERT));
            this.mMatterSettings = bundle.getParcelable(STATE_MATTER_SETTINGS);
            this.customerDiscount = bundle.getParcelable(STATE_CUSTOMER_DISCOUNT);
            this.mUnitPriceFormatter = bundle.getParcelable(STATE_UNIT_DECIMAL_FORMATTER);
            this.mDueDate = bundle.getParcelable(STATE_DUE_DATE);
            this.mIsDetailOrder = bundle.getBoolean(STATE_ISDETAIL_ORDER, false);
            this.cabinRef = bundle.getInt("state:cabinRef", 0);
        }
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setTitle(getSalesTitle());
        initPriceDialog();
        loadPager();
        initCampaignProgressDialogBuilder();
        if (this.mSales == null) {
            Toast.makeText(this, getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
            super.onBackPressed();
            return;
        }
        if (getmSales() != null) {
            Sales sales6 = getmSales();
            Intrinsics.checkNotNull(sales6);
            if (sales6.hasCampaignApplied()) {
                Sales sales7 = getmSales();
                Intrinsics.checkNotNull(sales7);
                String definition = sales7.getDocumentNo().getDefinition();
                checkNotNullExpressionValue(definition, "getDefinition(...)");
                this.documentNumberForAppliedCampagin = definition;
            }
        }
        Bundle extras = getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        distributionToFiche(extras.getParcelableArrayList(IntentExtraName.EXTRA_DISTRIBUTION_FICHE_ID));
    }
    private void fillDriverInfoFromSharedPreferences() throws JsonSyntaxException {
        EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
        String driverInfo = Preferences.getDriverInfo(ContextUtils.getmContext());
        checkNotNullExpressionValue(driverInfo, "getDriverInfo(...)");
        if (driverInfo.length() > 0) {
            Type type = new TypeToken<ArrayList<DriverInfos>>() {
            }.getType();
            checkNotNullExpressionValue(type, "getType(...)");
            Object objFromJson = new Gson().fromJson(driverInfo, type);
            checkNotNullExpressionValue(objFromJson, "fromJson(...)");
            List list = (List) objFromJson;
            for (int r2 = 0; r2 < 3; r2++) {
                if (r2 == 0) {
                    eDispatchAdditionalInfo.firstDriverName = ((DriverInfos) list.get(r2)).driverName;
                    eDispatchAdditionalInfo.firstDriverLastName = ((DriverInfos) list.get(r2)).driverSurname;
                    eDispatchAdditionalInfo.firstDriverIdentityNr = ((DriverInfos) list.get(r2)).driverIdentityNr;
                    eDispatchAdditionalInfo.firstDriverPlate = ((DriverInfos) list.get(r2)).plateNr;
                    eDispatchAdditionalInfo.firstTrailerPlate = ((DriverInfos) list.get(r2)).trailerPlateNr;
                }
                if (r2 == 1) {
                    eDispatchAdditionalInfo.secondDriverName = ((DriverInfos) list.get(r2)).driverName;
                    eDispatchAdditionalInfo.secondDriverLastName = ((DriverInfos) list.get(r2)).driverSurname;
                    eDispatchAdditionalInfo.secondDriverIdentityNr = ((DriverInfos) list.get(r2)).driverIdentityNr;
                    eDispatchAdditionalInfo.secondDriverPlate = ((DriverInfos) list.get(r2)).plateNr;
                    eDispatchAdditionalInfo.secondTrailerPlate = ((DriverInfos) list.get(r2)).trailerPlateNr;
                }
                if (r2 == 2) {
                    eDispatchAdditionalInfo.thirdDriverName = ((DriverInfos) list.get(r2)).driverName;
                    eDispatchAdditionalInfo.thirdDriverLastName = ((DriverInfos) list.get(r2)).driverSurname;
                    eDispatchAdditionalInfo.thirdDriverIdentityNr = ((DriverInfos) list.get(r2)).driverIdentityNr;
                    eDispatchAdditionalInfo.thirdDriverPlate = ((DriverInfos) list.get(r2)).plateNr;
                    eDispatchAdditionalInfo.thirdTrailerPlate = ((DriverInfos) list.get(r2)).trailerPlateNr;
                }
            }
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            sales.setEDispatchAdditionalInfo(eDispatchAdditionalInfo);
        }
    }

    private void initPriceDialog() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogPrice;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(getString(R.string.str_please_wait));
    }

    private void initCampaignProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.setMessage(getString(R.string.str_sales_fiche_show_campaign_please_wait)).setCancelable(false);
    }

    private void setOrderAvailableAmounts() {
        int r1;
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (true) {
            if (!it.hasNext()) {
                r1 = 0;
                break;
            } else {
                r1 = (int) it.next().component1();
                if (r1 != 0) {
                    break;
                }
            }
        }
        if (r1 != 0) {
            this.viewModel.getOrderAvailableAmountsFromDetailRef(r1, new SetOrderAvailableAmountListener(this));
        }
    }

    private String getSalesTitle() {
        String upperCase;
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        try {
            try {
                if (ficheMode == FicheMode.COPY || ficheMode == FicheMode.NEW) {
                    String string = getString(R.string.str_new);
                    SalesType salesType = this.mCustomerOperation.getSalesType();
                    Intrinsics.checkNotNull(salesType);
                    String strCatStringSpace = StringUtils.catStringSpace(string, getString(salesType.getmResId()));
                    Locale locale = Locale.getDefault();
                    checkNotNullExpressionValue(locale, "getDefault(...)");
                    upperCase = strCatStringSpace.toUpperCase(locale);
                    checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                } else if (ficheMode == FicheMode.EDIT) {
                    String string2 = getString(R.string.str_edit);
                    SalesType salesType2 = this.mCustomerOperation.getSalesType();
                    Intrinsics.checkNotNull(salesType2);
                    String strCatStringSpace2 = StringUtils.catStringSpace(string2, getString(salesType2.getmResId()));
                    Locale locale2 = Locale.getDefault();
                    checkNotNullExpressionValue(locale2, "getDefault(...)");
                    upperCase = strCatStringSpace2.toUpperCase(locale2);
                    checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                } else {
                    if (ficheMode != FicheMode.ANALYSE) {
                        return "";
                    }
                    String string3 = getString(R.string.str_analyze);
                    SalesType salesType3 = this.mCustomerOperation.getSalesType();
                    Intrinsics.checkNotNull(salesType3);
                    String strCatStringSpace3 = StringUtils.catStringSpace(string3, getString(salesType3.getmResId()));
                    Locale locale3 = Locale.getDefault();
                    checkNotNullExpressionValue(locale3, "getDefault(...)");
                    upperCase = strCatStringSpace3.toUpperCase(locale3);
                    checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                }
                return upperCase;
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "getSalesTitle: ", e2);
                return "";
            }
        } catch (Throwable unused) {
            return "";
        }
    }
    private void loadDefaultValue() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        FicheRefProp branch = sales.getBranch();
        SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters);
        SalesFicheHeaderFields mSalesFicheHeaderFields = salesFicheParameters.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields);
        loadFicheDefaultParameter(branch, mSalesFicheHeaderFields.getDefaultBranch(), R.string.column_code, getString(R.string.qry_branch_where));
        Sales sales2 = this.mSales;
        Intrinsics.checkNotNull(sales2);
        FicheRefProp division = sales2.getDivision();
        SalesFicheParameters salesFicheParameters2 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters2);
        SalesFicheHeaderFields mSalesFicheHeaderFields2 = salesFicheParameters2.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields2);
        loadFicheDefaultParameter(division, mSalesFicheHeaderFields2.getDefaultDivision(), R.string.column_code, getString(R.string.qry_division_where));
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        FicheRefProp wareHouse = sales3.getWareHouse();
        SalesFicheParameters salesFicheParameters3 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters3);
        SalesFicheHeaderFields mSalesFicheHeaderFields3 = salesFicheParameters3.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields3);
        loadFicheDefaultParameter(wareHouse, mSalesFicheHeaderFields3.getDefaultWareHouse(), R.string.column_code, getString(R.string.qry_warehouse_nr_where));
        Sales sales4 = this.mSales;
        Intrinsics.checkNotNull(sales4);
        FicheRefProp returnWareHouse = sales4.getReturnWareHouse();
        SalesFicheParameters salesFicheParameters4 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters4);
        SalesFicheHeaderFields mSalesFicheHeaderFields4 = salesFicheParameters4.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields4);
        loadFicheDefaultParameter(returnWareHouse, mSalesFicheHeaderFields4.getDefaultReturnWareHouse(), R.string.column_code, getString(R.string.qry_warehouse_nr_where));
        Sales sales5 = this.mSales;
        Intrinsics.checkNotNull(sales5);
        FicheRefProp sourceWareHouse = sales5.getSourceWareHouse();
        SalesFicheParameters salesFicheParameters5 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters5);
        SalesFicheHeaderFields mSalesFicheHeaderFields5 = salesFicheParameters5.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields5);
        loadFicheDefaultParameter(sourceWareHouse, mSalesFicheHeaderFields5.getDefaultWareHouse(), R.string.column_code, getString(R.string.qry_warehouse_nr_where));
        Sales sales6 = this.mSales;
        Intrinsics.checkNotNull(sales6);
        FicheRefProp factory = sales6.getFactory();
        SalesFicheParameters salesFicheParameters6 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters6);
        SalesFicheHeaderFields mSalesFicheHeaderFields6 = salesFicheParameters6.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields6);
        loadFicheDefaultParameter(factory, mSalesFicheHeaderFields6.getDefaultFactory(), R.string.column_code, getString(R.string.qry_factory_where));
        Sales sales7 = this.mSales;
        Intrinsics.checkNotNull(sales7);
        FicheStringProp documentNo = sales7.getDocumentNo();
        SalesFicheParameters salesFicheParameters7 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters7);
        SalesFicheHeaderFields mSalesFicheHeaderFields7 = salesFicheParameters7.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields7);
        loadFicheDefaultParameter(documentNo, mSalesFicheHeaderFields7.getDefaultDocumentNo());
        Sales sales8 = this.mSales;
        Intrinsics.checkNotNull(sales8);
        FicheStringProp explanation1 = sales8.getExplanation1();
        SalesFicheParameters salesFicheParameters8 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters8);
        SalesFicheHeaderFields mSalesFicheHeaderFields8 = salesFicheParameters8.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields8);
        loadFicheDefaultParameter(explanation1, mSalesFicheHeaderFields8.getDefaultExplanation1());
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales9 = this.mSales;
        Intrinsics.checkNotNull(sales9);
        FicheDiscountRefProp projectCode = sales9.getProjectCode();
        SalesFicheParameters salesFicheParameters9 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters9);
        SalesFicheHeaderFields mSalesFicheHeaderFields9 = salesFicheParameters9.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields9);
        salesNewViewModel.loadFicheDefaultProjectCode(projectCode, mSalesFicheHeaderFields9.getDefaultProject());
        Sales sales10 = this.mSales;
        Intrinsics.checkNotNull(sales10);
        FicheRefProp speCode = sales10.getSpeCode();
        SalesFicheParameters salesFicheParameters10 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters10);
        SalesFicheHeaderFields mSalesFicheHeaderFields10 = salesFicheParameters10.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields10);
        String defaultSpeCode = mSalesFicheHeaderFields10.getDefaultSpeCode();
        SalesNewViewModel salesNewViewModel2 = this.viewModel;
        Sales sales11 = getmSales();
        String speCodeTypeHeader = salesNewViewModel2.getSpeCodeTypeHeader(sales11 != null ? sales11.getmSalesType() : null);
        SalesFicheParameters salesFicheParameters11 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters11);
        SalesFicheHeaderFields mSalesFicheHeaderFields11 = salesFicheParameters11.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields11);
        loadFicheRefPropDefaultParameterByCode(speCode, defaultSpeCode, R.string.column_id, R.string.qry_spe_code_byCode, speCodeTypeHeader, mSalesFicheHeaderFields11.getDefaultSpeCode());
        Sales sales12 = this.mSales;
        Intrinsics.checkNotNull(sales12);
        FicheRefProp tradeGroup = sales12.getTradeGroup();
        SalesFicheParameters salesFicheParameters12 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters12);
        SalesFicheHeaderFields mSalesFicheHeaderFields12 = salesFicheParameters12.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields12);
        loadFicheDefaultTrade(tradeGroup, mSalesFicheHeaderFields12.getDefaultTradeGroup(), R.string.column_code, getString(R.string.qry_trade_group_code));
        Sales sales13 = this.mSales;
        Intrinsics.checkNotNull(sales13);
        FicheRefProp warrantyCode = sales13.getWarrantyCode();
        SalesFicheParameters salesFicheParameters13 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters13);
        SalesFicheHeaderFields mSalesFicheHeaderFields13 = salesFicheParameters13.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields13);
        loadFicheDefaultParameter(warrantyCode, mSalesFicheHeaderFields13.getDefaultWarrantyCode());
        Sales sales14 = this.mSales;
        Intrinsics.checkNotNull(sales14);
        FicheStringProp taxPayerCode = sales14.getTaxPayerCode();
        SalesFicheParameters salesFicheParameters14 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters14);
        SalesFicheHeaderFields mSalesFicheHeaderFields14 = salesFicheParameters14.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields14);
        loadFicheDefaultParameter(taxPayerCode, mSalesFicheHeaderFields14.getDefaultTaxPayerCode());
        Sales sales15 = this.mSales;
        Intrinsics.checkNotNull(sales15);
        FicheStringProp taxPayerName = sales15.getTaxPayerName();
        SalesFicheParameters salesFicheParameters15 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters15);
        SalesFicheHeaderFields mSalesFicheHeaderFields15 = salesFicheParameters15.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields15);
        loadFicheDefaultParameter(taxPayerName, mSalesFicheHeaderFields15.getDefaultTaxPayerName());
        Sales sales16 = this.mSales;
        Intrinsics.checkNotNull(sales16);
        FicheRefProp eInvoiceTypSgk = sales16.getEInvoiceTypSgk();
        SalesFicheParameters salesFicheParameters16 = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters16);
        SalesFicheHeaderFields mSalesFicheHeaderFields16 = salesFicheParameters16.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields16);
        loadFicheDefaultParameterStringArray(eInvoiceTypSgk, mSalesFicheHeaderFields16.getDefaultEInvoiceTypSGK(), R.array.additional_invoice_type_values);
        Sales sales17 = this.mSales;
        Intrinsics.checkNotNull(sales17);
        loadFicheDefaultParameter(sales17.getShipAddress(), this.mCustomerShipRef, R.string.column_code, R.string.column_name, getString(R.string.qry_shipaddress_where));
        if (this.mCustomerShipRef > 0) {
            if (this.viewModel.erpType() == ErpType.NETSIS) {
                Sales sales18 = this.mSales;
                Intrinsics.checkNotNull(sales18);
                loadFicheDefaultParameter(sales18.getShipAccount(), this.mCustomerShipRef, R.string.column_code, R.string.column_name, getString(R.string.net_qry_shipaccount_where));
            } else {
                Sales sales19 = this.mSales;
                Intrinsics.checkNotNull(sales19);
                loadFicheDefaultParameter(sales19.getShipAccount(), this.mCustomerRef, R.string.column_code, R.string.column_name, getString(R.string.qry_shipaccount_where));
            }
        }
        if (!SalesUtils.isSalesTypeOrderOrDemand(getSalesType())) {
            Sales sales20 = this.mSales;
            Intrinsics.checkNotNull(sales20);
            loadFicheDefaultParameter(sales20.getCabin(), this.cabinRef, R.string.column_code, R.string.column_name, getString(R.string.qry_cabin_where));
        }
        String paramValue = this.viewModel.getSqlHelper().getParamValue(ParameterTypes.ptSpeCodeUsage);
        checkNotNullExpressionValue(paramValue, "getParamValue(...)");
        String[] strArrSplitInitValue = StringUtils.splitInitValue(paramValue, ",", 2);
        ArrayList arrayList = new ArrayList();
        for (String str : strArrSplitInitValue) {
            Intrinsics.checkNotNull(str);
            int length = str.length() - 1;
            int r9 = 0;
            boolean z = false;
            while (r9 <= length) {
                boolean z2 = Intrinsics.compare(str.charAt(!z ? r9 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    r9++;
                } else {
                    z = true;
                }
            }
            arrayList.add(str.subSequence(r9, length + 1).toString());
        }
        if (arrayList.contains("1")) {
            Sales sales21 = this.mSales;
            Intrinsics.checkNotNull(sales21);
            loadDefaultSpecode(sales21.getFirstSpeCode(), 1, this.viewModel.getGetDefaultFirstSpeCode());
        }
        if (arrayList.contains(ExifInterface.GPS_MEASUREMENT_2D)) {
            Sales sales22 = this.mSales;
            Intrinsics.checkNotNull(sales22);
            loadDefaultSpecode(sales22.getSecondSpeCode(), 2, this.viewModel.getGetDefaultSecondSpeCode());
        }
        setDefaultShipAddress();
        setErpInvoiceType();
    }

    private void loadDefaultFicheValue() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        loadFicheDefaultLocal(sales.getBranch(), R.string.column_code, R.string.qry_branch_where, StringUtils.convertIntToString(0));
        Sales sales2 = this.mSales;
        Intrinsics.checkNotNull(sales2);
        loadFicheDefaultLocal(sales2.getDivision(), R.string.column_code, R.string.qry_division);
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        loadFicheDefaultLocal(sales3.getFactory(), R.string.column_code, R.string.qry_factory);
        Sales sales4 = this.mSales;
        Intrinsics.checkNotNull(sales4);
        loadFicheDefaultLocal(sales4.getWareHouse(), R.string.column_code, R.string.qry_warehouse_div_fact, StringUtils.convertIntToString(0), StringUtils.convertIntToString(0));
        Sales sales5 = this.mSales;
        Intrinsics.checkNotNull(sales5);
        loadFicheDefaultLocal(sales5.getReturnWareHouse(), R.string.column_code, R.string.qry_warehouse_div_fact, StringUtils.convertIntToString(0), StringUtils.convertIntToString(0));
        Sales sales6 = this.mSales;
        Intrinsics.checkNotNull(sales6);
        loadFicheDefaultLocalTrade(sales6.getTradeGroup(), R.string.column_code, R.string.qry_trade_group_clcard, StringUtils.convertIntToString(this.mCustomerRef));
        Sales sales7 = this.mSales;
        Intrinsics.checkNotNull(sales7);
        loadFicheDefaultLocal(sales7.getProjectCode(), R.string.column_definition_, R.string.column_code, R.string.qry_project_code_clcard, StringUtils.convertIntToString(this.mCustomerRef));
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            Sales sales8 = this.mSales;
            Intrinsics.checkNotNull(sales8);
            loadFicheDefaultLocal(sales8.getPayPlan(), R.string.column_definition_, R.string.column_code, R.string.net_qry_pay_plan_clcard, StringUtils.convertIntToString(this.mCustomerRef));
        } else {
            Sales sales9 = this.mSales;
            Intrinsics.checkNotNull(sales9);
            loadFicheDefaultLocal(sales9.getPayPlan(), R.string.column_code, R.string.qry_pay_plan_clcard, StringUtils.convertIntToString(this.mCustomerRef));
        }
        Sales sales10 = this.mSales;
        Intrinsics.checkNotNull(sales10);
        loadFicheDefaultLocal(sales10.getShipAgent(), R.string.column_code, R.string.qry_ship_agent_clcard, StringUtils.convertIntToString(this.mCustomerRef));
        if (this.viewModel.erpType() == erpType2) {
            Sales sales11 = this.mSales;
            Intrinsics.checkNotNull(sales11);
            loadFicheDefaultParameter(sales11.getDiscountCard(0), this.mCustomerRef, R.string.column_code, R.string.column_disc, getString(R.string.qry_default_condtion_code));
        }
    }

    private void loadFicheDefaultLocalTrade(FicheRefProp ficheRefProp, @StringRes int r5, @StringRes int r6, String... strArr) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(r6), Arrays.copyOf(strArr, strArr.length));
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    ficheRefProp.setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_id))));
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(getString(r5))));
                    Sales sales = getmSales();
                    Intrinsics.checkNotNull(sales);
                    sales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_gattrib)))));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private void loadFicheDefaultTrade(FicheRefProp ficheRefProp, String str, @StringRes int r6, String str2) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        if (str == null || TextUtils.isEmpty(str)) {
            return;
        }
        Cursor cursorQuery = null;
        try {
            try {
                ISqlBriteDatabase<?> sqlBriteDatabase = this.viewModel.getSqlBriteDatabase();
                Intrinsics.checkNotNull(str2);
                cursorQuery = sqlBriteDatabase.query(str2, str);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(str);
                    ficheRefProp.setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(r6))));
                    Sales sales = getmSales();
                    Intrinsics.checkNotNull(sales);
                    sales.setNotUseGattribKdv(CalculateUtils.isGattribNotUseKdv(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_gattrib)))));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private void loadDefaultSpecode(FicheDiscountRefProp prop, int r4, String str) {
        Intrinsics.checkNotNullParameter(prop, "prop");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.viewModel.getSqlBriteDatabase().query("SELECT LOGICALREF AS _id, SPECODE AS CODE, DEFINITION AS NAME FROM SPECODES WHERE CODETYPE=1 AND SPECODETYPE=? AND CODE=?", String.valueOf(r4), str);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    prop.setCode(str);
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex("NAME")));
                    prop.setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex("_id")));
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "loadDefaultFirstSpecode: ", e2);
                if (cursorQuery == null) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
    private void warehouseInvoiceLoadControl() {
        int logicalRef;
        int r7 = 0;
        if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(getSalesType())) {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            logicalRef = sales.getReturnWareHouse().getLogicalRef();
        } else {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            logicalRef = sales2.getWareHouse().getLogicalRef();
        }
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        int logicalRef2 = sales3.getFactory().getLogicalRef();
        Sales sales4 = this.mSales;
        Intrinsics.checkNotNull(sales4);
        int logicalRef3 = sales4.getBranch().getLogicalRef();
        Cursor cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_warehouse_fact_nr), String.valueOf(logicalRef));
        int r6 = 0;
        if (cursorQuery != null) {
            try {
                r7 = cursorQuery.moveToFirst() ? cursorQuery.getInt(cursorQuery.getColumnIndex("FACTNR")) : 0;
                if (cursorQuery != null) {
                }
            } catch (Exception e2) {
                Log.e(MobileSales.TAG, "warehouseInvoiceLoadControl: ", e2);
                cursorQuery.close();
                r7 = 0;
            }
        }
        Log.d(TAG, "warehouseInvoiceLoadControl: correctFactNr : " + r7);
        if (r7 != logicalRef2) {
            Sales sales5 = this.mSales;
            Intrinsics.checkNotNull(sales5);
            loadFicheDefaultParameter(sales5.getFactory(), r7, R.string.column_code, getString(R.string.qry_factory_where));
        }
        cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_warehouse_division_nr), String.valueOf(logicalRef));
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    r6 = cursorQuery.getInt(cursorQuery.getColumnIndex("DIVISNR"));
                }
            } catch (Exception e3) {
                Log.e(MobileSales.TAG, "warehouseInvoiceLoadControl: ", e3);
            }
            if (cursorQuery != null) {
            }
        } else if (cursorQuery != null) {
        }
        Log.d(TAG, "warehouseInvoiceLoadControl: correctDivisionNr : " + r6);
        if (r6 != logicalRef3) {
            Sales sales6 = this.mSales;
            Intrinsics.checkNotNull(sales6);
            loadFicheDefaultParameter(sales6.getBranch(), r6, R.string.column_code, getString(R.string.qry_branch_where));
        }
    }

    private void initSalesCopy() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        if (!TextUtils.isEmpty(sales.getCampaingRefs())) {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            sales2.setCampaingRefs("");
        }
        clearCampaign();
        SalesType salesType = this.mCustomerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrder(salesType)) {
            Sales sales3 = this.mSales;
            Intrinsics.checkNotNull(sales3);
            sales3.setOrderType(0);
        } else {
            SalesType salesType2 = this.mCustomerOperation.getSalesType();
            Intrinsics.checkNotNull(salesType2);
            if (SalesUtils.isSalesTypeDemand(salesType2)) {
                Sales sales4 = this.mSales;
                Intrinsics.checkNotNull(sales4);
                sales4.setOrderType(1);
            }
        }
        Sales sales5 = this.mSales;
        Intrinsics.checkNotNull(sales5);
        sales5.setLogicalRef(0);
        Sales sales6 = this.mSales;
        Intrinsics.checkNotNull(sales6);
        sales6.setFicheRef(0);
        Sales sales7 = this.mSales;
        Intrinsics.checkNotNull(sales7);
        sales7.setAndFicheNo(getCreateAndFicheNo());
        Sales sales8 = this.mSales;
        Intrinsics.checkNotNull(sales8);
        sales8.setFicheNo("");
        Sales sales9 = this.mSales;
        Intrinsics.checkNotNull(sales9);
        sales9.setFicheCreateDate(DateAndTimeUtils.nowDate());
        Sales sales10 = this.mSales;
        Intrinsics.checkNotNull(sales10);
        sales10.setGDate(DateAndTimeUtils.getNowDateTime());
        Sales sales11 = this.mSales;
        Intrinsics.checkNotNull(sales11);
        Sales sales12 = this.mSales;
        Intrinsics.checkNotNull(sales12);
        sales11.setFicheCreateDateInt(DateAndTimeUtils.getDateToInt(sales12.getFicheCreateDate()));
        Sales sales13 = this.mSales;
        Intrinsics.checkNotNull(sales13);
        sales13.setLatitude(getLatitude());
        Sales sales14 = this.mSales;
        Intrinsics.checkNotNull(sales14);
        sales14.setLongitude(getLongitude());
        Sales sales15 = this.mSales;
        Intrinsics.checkNotNull(sales15);
        sales15.setTransferCount(0);
        Sales sales16 = this.mSales;
        Intrinsics.checkNotNull(sales16);
        sales16.setPrintCount(0);
        Sales sales17 = this.mSales;
        Intrinsics.checkNotNull(sales17);
        sales17.setVisitInfoId(this.mAutoVisitId);
        Sales sales18 = this.mSales;
        Intrinsics.checkNotNull(sales18);
        ArrayList<SalesDetail> mSalesDetailList = sales18.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        while (it.hasNext()) {
            SalesDetail next = it.next();
            next.setSalesFicheId(0);
            next.setLogicalRef(0);
            Sales sales19 = this.mSales;
            Intrinsics.checkNotNull(sales19);
            next.setSalesType(sales19.getMSalesType());
            String string = UUID.randomUUID().toString();
            checkNotNullExpressionValue(string, "toString(...)");
            next.setGuid(string);
            next.setSipNum("");
            next.setSipKont(0);
            next.getSalesSerialLots().clear();
            next.setSerialLotCodeList("");
            if (next.getSearchBarcodes() != null) {
                ArrayList<String> searchBarcodes = next.getSearchBarcodes();
                Intrinsics.checkNotNull(searchBarcodes);
                searchBarcodes.clear();
            }
        }
        Sales sales20 = this.mSales;
        Intrinsics.checkNotNull(sales20);
        try {
            sales20.setSaleVatCanBeChange(this.viewModel.getVATEnabled());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales21 = this.mSales;
        Intrinsics.checkNotNull(sales21);
        if (sales21.getErpInvoiceType().getLogicalRef() < 0) {
            setErpInvoiceType();
        }
        clearFirstDiscountIfCustomerDiscountExistsForNetsis();
    }
    private void clearFirstDiscountIfCustomerDiscountExistsForNetsis() {
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            Sales sales = getmSales();
            if (Intrinsics.areEqual(sales != null ? Double.valueOf(sales.getCustomerDiscRatio()) : null, 0.0d)) {
                return;
            }
            Sales sales2 = getmSales();
            Integer numValueOf = sales2 != null ? Integer.valueOf(sales2.getDiscountLength()) : null;
            Intrinsics.checkNotNull(numValueOf);
            if (numValueOf.intValue() >= 1) {
                Sales sales3 = getmSales();
                FicheDiscountProp discountRatio = sales3 != null ? sales3.getDiscountRatio(0) : null;
                if (discountRatio != null) {
                    discountRatio.setBoundedToCard(false);
                }
                Sales sales4 = getmSales();
                FicheDiscountProp discountRatio2 = sales4 != null ? sales4.getDiscountRatio(0) : null;
                if (discountRatio2 != null) {
                    FicheStringProp.setDefinition("");
                }
                Sales sales5 = getmSales();
                FicheDiscountProp discountRatio3 = sales5 != null ? sales5.getDiscountRatio(0) : null;
                if (discountRatio3 != null) {
                    discountRatio3.setCampaignCode("");
                }
                Sales sales6 = getmSales();
                FicheDiscountProp discountRatio4 = sales6 != null ? sales6.getDiscountRatio(0) : null;
                if (discountRatio4 != null) {
                    discountRatio4.setCampaignLineNo("");
                }
                Sales sales7 = getmSales();
                FicheDiscountProp discountTotal = sales7 != null ? sales7.getDiscountTotal(0) : null;
                if (discountTotal != null) {
                    discountTotal.setBoundedToCard(false);
                }
                Sales sales8 = getmSales();
                FicheDiscountProp discountTotal2 = sales8 != null ? sales8.getDiscountTotal(0) : null;
                if (discountTotal2 != null) {
                    FicheStringProp.setDefinition("");
                }
                Sales sales9 = getmSales();
                FicheDiscountProp discountTotal3 = sales9 != null ? sales9.getDiscountTotal(0) : null;
                if (discountTotal3 != null) {
                    discountTotal3.setCampaignCode("");
                }
                Sales sales10 = getmSales();
                FicheDiscountProp discountTotal4 = sales10 != null ? sales10.getDiscountTotal(0) : null;
                if (discountTotal4 == null) {
                    return;
                }
                discountTotal4.setCampaignLineNo("");
            }
        }
    } 
    private   void initSales() {
        double currRateWithDate;
        this.mSales = new Sales(getSalesType().getmValue());
        if (SalesUtils.isSalesTypeOrder(getSalesType())) {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            sales.setOrderType(0);
        } else if (SalesUtils.isSalesTypeDemand(getSalesType())) {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            sales2.setOrderType(1);
        }
        if (SalesUtils.isSalesTypeOrderOrDemand(getSalesType())) {
            FTypeControlUtils.setMainFType(FType.siparis);
        } else if (SalesUtils.isSalesTypeDispatchOrReturnDispatch(getSalesType())) {
            FTypeControlUtils.setMainFType(FType.irsaliye);
        } else if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(getSalesType())) {
            FTypeControlUtils.setMainFType(FType.perakendefatura);
        } else if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
            FTypeControlUtils.setMainFType(FType.whtransfer);
        } else {
            FTypeControlUtils.setMainFType(FType.fatura);
        }
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        sales3.setClRef(this.mCustomerRef);
        Sales sales4 = this.mSales;
        Intrinsics.checkNotNull(sales4);
        sales4.setFicheNo("");
        Sales sales5 = this.mSales;
        Intrinsics.checkNotNull(sales5);
        sales5.setAndFicheNo(getCreateAndFicheNo());
        Sales sales6 = this.mSales;
        Intrinsics.checkNotNull(sales6);
        sales6.setFicheCreateDate(DateAndTimeUtils.nowDate());
        Sales sales7 = this.mSales;
        Intrinsics.checkNotNull(sales7);
        sales7.setGDate(DateAndTimeUtils.getNowDateTime());
        Sales sales8 = this.mSales;
        Intrinsics.checkNotNull(sales8);
        Sales sales9 = this.mSales;
        Intrinsics.checkNotNull(sales9);
        sales8.setFicheCreateDateInt(DateAndTimeUtils.getDateToInt(sales9.getFicheCreateDate()));
        Sales sales10 = this.mSales;
        Intrinsics.checkNotNull(sales10);
        sales10.setLatitude(getLatitude());
        Sales sales11 = this.mSales;
        Intrinsics.checkNotNull(sales11);
        sales11.setLongitude(getLongitude());
        Sales sales12 = this.mSales;
        Intrinsics.checkNotNull(sales12);
        sales12.setTransferCount(0);
        Sales sales13 = this.mSales;
        Intrinsics.checkNotNull(sales13);
        sales13.setPrintCount(0);
        Sales sales14 = this.mSales;
        Intrinsics.checkNotNull(sales14);
        sales14.setVisitInfoId(this.mAutoVisitId);
        CustomerDiscount customerDiscount = this.customerDiscount;
        Intrinsics.checkNotNull(customerDiscount);
        if (customerDiscount.getType() == 0) {
            Sales sales15 = this.mSales;
            Intrinsics.checkNotNull(sales15);
            CustomerDiscount customerDiscount2 = this.customerDiscount;
            Intrinsics.checkNotNull(customerDiscount2);
            sales15.setCustomerDiscRatio(customerDiscount2.getRatio());
        }
        Sales sales16 = this.mSales;
        Intrinsics.checkNotNull(sales16);
        try {
            sales16.setClCode(this.viewModel.getCustomerClCode(this.mCustomerRef));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales17 = this.mSales;
        Intrinsics.checkNotNull(sales17);
        sales17.setBranch(new FicheRefProp());
        Sales sales18 = this.mSales;
        Intrinsics.checkNotNull(sales18);
        sales18.setDivision(new FicheRefProp());
        Sales sales19 = this.mSales;
        Intrinsics.checkNotNull(sales19);
        sales19.setFactory(new FicheRefProp());
        Sales sales20 = this.mSales;
        Intrinsics.checkNotNull(sales20);
        sales20.setDivision(new FicheRefProp());
        Sales sales21 = this.mSales;
        Intrinsics.checkNotNull(sales21);
        sales21.setWareHouse(new FicheRefProp());
        Sales sales22 = this.mSales;
        Intrinsics.checkNotNull(sales22);
        sales22.setReturnWareHouse(new FicheRefProp());
        Sales sales23 = this.mSales;
        Intrinsics.checkNotNull(sales23);
        sales23.setSourceWareHouse(new FicheRefProp());
        Sales sales24 = this.mSales;
        Intrinsics.checkNotNull(sales24);
        sales24.setSpeCode(new FicheRefProp());
        Sales sales25 = this.mSales;
        Intrinsics.checkNotNull(sales25);
        sales25.setWarrantyCode(new FicheRefProp());
        Sales sales26 = this.mSales;
        Intrinsics.checkNotNull(sales26);
        sales26.setDocumentNo(new FicheStringProp());
        Sales sales27 = this.mSales;
        Intrinsics.checkNotNull(sales27);
        sales27.setDocumentTrackingNo(new FicheStringProp());
        Sales sales28 = this.mSales;
        Intrinsics.checkNotNull(sales28);
        sales28.setCustomerOrderNo(new FicheStringProp());
        Sales sales29 = this.mSales;
        Intrinsics.checkNotNull(sales29);
        sales29.setDeliveryDate(new FicheDateProp());
        if (getSalesType() == SalesType.DEMAND) {
            Sales sales30 = this.mSales;
            Intrinsics.checkNotNull(sales30);
            sales30.setDeliveryDate(new FicheDateProp(DateAndTimeUtils.nowDate()));
        } else {
            Sales sales31 = this.mSales;
            Intrinsics.checkNotNull(sales31);
            sales31.setDeliveryDate(new FicheDateProp());
        }
        Sales sales32 = this.mSales;
        Intrinsics.checkNotNull(sales32);
        sales32.setPaymentOrder(new FicheBooleanProp(this.viewModel.getBaseErp().getPaymentOrderParam() == 1));
        Sales sales33 = this.mSales;
        Intrinsics.checkNotNull(sales33);
        sales33.setExplanation1(new FicheStringProp());
        Sales sales34 = this.mSales;
        Intrinsics.checkNotNull(sales34);
        sales34.setExplanation2(new FicheStringProp());
        Sales sales35 = this.mSales;
        Intrinsics.checkNotNull(sales35);
        sales35.setExplanation3(new FicheStringProp());
        Sales sales36 = this.mSales;
        Intrinsics.checkNotNull(sales36);
        sales36.setExplanation4(new FicheStringProp());
        Sales sales37 = this.mSales;
        Intrinsics.checkNotNull(sales37);
        sales37.setShipAccount(new FicheDiscountRefProp());
        Sales sales38 = this.mSales;
        Intrinsics.checkNotNull(sales38);
        sales38.setShipAddress(new FicheDiscountRefProp());
        Sales sales39 = this.mSales;
        Intrinsics.checkNotNull(sales39);
        sales39.setShipAgent(new FicheRefProp());
        Sales sales40 = this.mSales;
        Intrinsics.checkNotNull(sales40);
        sales40.setShipType(new FicheRefProp());
        Sales sales41 = this.mSales;
        Intrinsics.checkNotNull(sales41);
        sales41.setTradeGroup(new FicheRefProp());
        Sales sales42 = this.mSales;
        Intrinsics.checkNotNull(sales42);
        sales42.setPayPlan(new FicheDiscountRefProp());
        Sales sales43 = this.mSales;
        Intrinsics.checkNotNull(sales43);
        sales43.setProjectCode(new FicheDiscountRefProp());
        Sales sales44 = this.mSales;
        Intrinsics.checkNotNull(sales44);
        sales44.setLatitude(getLatitude());
        Sales sales45 = this.mSales;
        Intrinsics.checkNotNull(sales45);
        sales45.setLongitude(getLongitude());
        Sales sales46 = this.mSales;
        Intrinsics.checkNotNull(sales46);
        sales46.setConsignee(new FicheBooleanProp());
        Sales sales47 = this.mSales;
        Intrinsics.checkNotNull(sales47);
        sales47.setIncludeVat(new FicheBooleanProp());
        Sales sales48 = this.mSales;
        Intrinsics.checkNotNull(sales48);
        try {
            sales48.setSaleVatCanBeChange(this.viewModel.getVATEnabled());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales49 = this.mSales;
        Intrinsics.checkNotNull(sales49);
        try {
            sales49.setSaleVatDefaultChecked(this.viewModel.getVATDefaultValue());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales50 = this.mSales;
        Intrinsics.checkNotNull(sales50);
        sales50.setEInvoiceSGKDocumentNo(new FicheStringProp());
        Sales sales51 = this.mSales;
        Intrinsics.checkNotNull(sales51);
        sales51.setTaxPayerCode(new FicheStringProp());
        Sales sales52 = this.mSales;
        Intrinsics.checkNotNull(sales52);
        sales52.setTaxPayerName(new FicheStringProp());
        Sales sales53 = this.mSales;
        Intrinsics.checkNotNull(sales53);
        sales53.setEInvoiceTypSgk(new FicheRefProp());
        Sales sales54 = this.mSales;
        Intrinsics.checkNotNull(sales54);
        sales54.setBeginDate(new FicheDateProp());
        Sales sales55 = this.mSales;
        Intrinsics.checkNotNull(sales55);
        sales55.setEndDate(new FicheDateProp());
        Sales sales56 = this.mSales;
        Intrinsics.checkNotNull(sales56);
        sales56.setCabin(new FicheDiscountRefProp());
        Sales sales57 = this.mSales;
        Intrinsics.checkNotNull(sales57);
        sales57.setFirstSpeCode(new FicheDiscountRefProp());
        Sales sales58 = this.mSales;
        Intrinsics.checkNotNull(sales58);
        sales58.setSecondSpeCode(new FicheDiscountRefProp());
        Sales sales59 = this.mSales;
        Intrinsics.checkNotNull(sales59);
        sales59.setInsteadOfEDespatch(new FicheBooleanProp());
        ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
        Sales sales60 = this.mSales;
        Intrinsics.checkNotNull(sales60);
        int clCardCurrency = sqlHelper.getClCardCurrency(sales60.getClRef());
        Sales sales61 = this.mSales;
        Intrinsics.checkNotNull(sales61);
        sales61.setTrCurr(clCardCurrency);
        Sales sales62 = this.mSales;
        Intrinsics.checkNotNull(sales62);
        if (clCardCurrency == 0) {
            currRateWithDate = 0.0d;
        } else {
            ISqlHelper<?> sqlHelper2 = this.viewModel.getSqlHelper();
            Sales sales63 = this.mSales;
            Intrinsics.checkNotNull(sales63);
            currRateWithDate = sqlHelper2.getCurrRateWithDate(clCardCurrency, sales63.getFicheCreateDate());
        }
        sales62.setTrRate(currRateWithDate);
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            Sales sales64 = this.mSales;
            Intrinsics.checkNotNull(sales64);
            try {
                sales64.setEDespatch(new FicheBooleanProp(getSalesType() == SalesType.DISPATCH && this.viewModel.getFirmUseEDespatch()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Sales sales65 = this.mSales;
            Intrinsics.checkNotNull(sales65);
            if (getSalesType() == SalesType.WHTRANSFER || getSalesType() == SalesType.INVOICE || getSalesType() == SalesType.DISPATCH) {
                SalesNewViewModel salesNewViewModel = this.viewModel;
                Sales sales66 = this.mSales;
                Intrinsics.checkNotNull(sales66);
                boolean z = false;
                try {
                    z = salesNewViewModel.getUseEDispatch(sales66.getBranch().getLogicalRef());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sales65.setEDespatch(new FicheBooleanProp(z));
            }
        }
        Sales sales67 = this.mSales;
        Intrinsics.checkNotNull(sales67);
        sales67.setEDocSerial(new FicheRefProp());
        try {
            if (getSalesType() == SalesType.DISPATCH && this.viewModel.erpType() == erpType2 && this.viewModel.getFirmUseEDespatch()) {
                fillPreviousEdespatchInfo();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (getSalesType() == SalesType.WHTRANSFER && this.viewModel.erpType() == ErpType.TIGER) {
            SalesNewViewModel salesNewViewModel2 = this.viewModel;
            Sales sales68 = this.mSales;
            Intrinsics.checkNotNull(sales68);
            try {
                if (salesNewViewModel2.getUseEDispatch(sales68.getBranch().getLogicalRef())) {
                    fillPreviousEdespatchInfo();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        setDueDate();
        setSalesReservedValue();
        setDeliveryDate();
    }

    private void setDeliveryDate() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        try {
            if (sales.getmSalesType() == SalesType.ORDER && this.viewModel.erpType() != ErpType.NETSIS && this.viewModel.setDeliveryDateAsToday()) {
                Sales sales2 = this.mSales;
                Intrinsics.checkNotNull(sales2);
                sales2.setDeliveryDate(new FicheDateProp(DateAndTimeUtils.nowDateDMY()));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDefaultShipAddress() {
        ShipAddress defaultShipAddress;
        try {
            if (this.viewModel.erpType() == ErpType.NETSIS || this.mCustomerShipRef > 0 || (defaultShipAddress = this.viewModel.getDefaultShipAddress(this.mCustomerRef)) == null) {
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setShipAddress(new FicheDiscountRefProp(defaultShipAddress.getLogicalRef(), -1, defaultShipAddress.toString(), defaultShipAddress.getCode()));
    }
    private void setSalesReservedValue() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setReserved(new FicheBooleanProp());
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            if (sales2.getmSalesType() == SalesType.ORDER && Intrinsics.areEqual(this.viewModel.getSqlHelper().getLogoParamValue("STOK_AYIR"), ExifInterface.LONGITUDE_EAST) && this.viewModel.getGetOrderDoReserve()) {
                Sales sales3 = this.mSales;
                Intrinsics.checkNotNull(sales3);
                sales3.getReserved().setSelect(true);
            }
        }
    }

    private   void setDueDate() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setDueDate(new FicheDateProp());
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            return;
        }
        ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
        Sales sales2 = this.mSales;
        Intrinsics.checkNotNull(sales2);
        Date dateDateAdd = DateAndTimeUtils.dateAdd(sqlHelper.getClCardDueDate(sales2.getClRef()));
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        sales3.setDueDate(new FicheDateProp(DateAndTimeUtils.getDateDMY(dateDateAdd)));
    }

    private   void setErpInvoiceType() {
        String string;
        ErpInvoiceType erpTypeFromSales = this.viewModel.getErpTypeFromSales(getmSales());
        if (erpTypeFromSales == ErpInvoiceType.None) {
            return;
        }
        int r1 = WhenMappings.EnumSwitchMapping0[erpTypeFromSales.ordinal()];
        if (r1 == 1 || r1 == 2) {
            string = getString(R.string.str_paper_invoice_type_for_erp);
            checkNotNullExpressionValue(string, "getString(...)");
        } else if (r1 == 3) {
            string = getString(R.string.str_eInvoice_invoice_type_for_erp);
            checkNotNullExpressionValue(string, "getString(...)");
        } else if (r1 == 4) {
            string = getString(R.string.str_eArchiveInvoice_invoice_type_for_erp);
            checkNotNullExpressionValue(string, "getString(...)");
        } else if (r1 == 5) {
            string = getString(R.string.str_eArchiveInternetInvoice_invoice_type_for_erp);
            checkNotNullExpressionValue(string, "getString(...)");
        } else {
            string = getString(R.string.str_paper_invoice_type_for_erp);
            checkNotNullExpressionValue(string, "getString(...)");
        }
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setErpInvoiceType(new FicheRefProp(erpTypeFromSales.getmValue(), -1, string));
    }

    private void fillPreviousEdespatchInfo() {
        NEDispatchInfoModel lastEDespatchInfoModel = this.viewModel.getLastEDespatchInfoModel(getSalesType());
        if (lastEDespatchInfoModel == null) {
            return;
        }
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setEDocSerial(new FicheRefProp(-1, -1, lastEDespatchInfoModel.getSerial()));
        Sales sales2 = this.mSales;
        Intrinsics.checkNotNull(sales2);
        sales2.setEDispatchAdditionalInfo(lastEDespatchInfoModel.getAdditionalInfo());
    }

    private void initInvoiceAddress() {
        try {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            String string = getString(R.string.qry_customerInvoiceAddress);
            checkNotNullExpressionValue(string, "getString(...)");
            sales.setInvoiceAddress(loadValue(string, R.string.column_addr1, R.string.column_addr2, R.string.column_city, R.string.column_town));
        } catch (Exception e2) {
            Log.e(TAG, "initInvoiceAddress : ", e2);
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            sales2.setInvoiceAddress("");
        }
    }
     public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
     public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    } 
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_sales, menu);
        this.menuDoSalesCondition = menu.findItem(R.id.menu_sales_do_sales_condition);
        this.menuSalesShowCurrencyPrice = menu.findItem(R.id.menu_sales_show_currency_price);
        this.menuSalesDriverInformation = menu.findItem(R.id.menu_sales_driver_information);
        this.menuApplyCampaign = menu.findItem(R.id.menu_sales_do_apply_campaign);
        this.menuClearCampaign = menu.findItem(R.id.menu_sales_do_clear_campaign);
        this.menuOnlineTotal = menu.findItem(R.id.menu_fiche_online_total);
        this.menuReports = menu.findItem(R.id.menu_fiche_reports);
        this.menuSalesOrderTransfer = menu.findItem(R.id.menu_sales_order_transfer);
        this.menuSalesOrderDetailTransfer = menu.findItem(R.id.menu_sales_order_detail_transfer);
        this.menuSalesDistributionTransfer = menu.findItem(R.id.menu_sales_distribution_transfer);
        this.menuLastSalesProducts = menu.findItem(R.id.menu_last_orders_products);
        this.menuEDespatchInfo = menu.findItem(R.id.menu_edespatch_additional);
        this.menuAddAllWarehouseItems = menu.findItem(R.id.menu_add_all_whouse_items);
        this.menuUpdateStockAmounts = menu.findItem(R.id.menu_update_stock_amounts);
        this.menuPdf = menu.findItem(R.id.menu_pdf);
        this.menuEDocumentPdf = menu.findItem(R.id.menu_contextual_edocument_pdf);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        Intrinsics.checkNotNullParameter(menu, "menu");
        boolean z2 = true;
        if (this.mCustomerOperation.getFicheMode() != FicheMode.ANALYSE) {
            if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
                ContextUtils.setMenuItemOnlineVisible(this.menuDoSalesCondition, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuSalesShowCurrencyPrice, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuSalesDriverInformation, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuApplyCampaign, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuClearCampaign, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuOnlineTotal, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuSalesOrderTransfer, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuSalesOrderDetailTransfer, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuSalesDistributionTransfer, false);
                ContextUtils.setMenuItemOnlineVisible(this.menuReports, isConnected(this));
                ContextUtils.setMenuItemOnlineVisible(this.menuAddAllWarehouseItems, isConnected(this) && this.viewModel.erpType() == ErpType.TIGER);
            } else {
                setSalesOrderFicheTransferMenu();
                setSalesCampaignAndSalesCondition();
                ContextUtils.setMenuItemOnlineVisible(this.menuReports, isConnected(this));
                ContextUtils.setMenuItemOnlineVisible(this.menuOnlineTotal, isConnected(this) && !SalesUtils.isSalesTypeDemand(getSalesType()));
            }
            ContextUtils.setMenuItemOnlineVisible(this.menuPdf, false);
            ContextUtils.setMenuItemOnlineVisible(this.menuEDocumentPdf, false);
        } else {
            int size = menu.size();
            for (int r1 = 0; r1 < size; r1++) {
                ContextUtils.setMenuItemOnlineVisible(menu.getItem(r1), false);
            }
            if (this.isOpenFromReportExtract) {
                ContextUtils.setMenuItemOnlineVisible(this.menuPdf, true);
                ContextUtils.setMenuItemOnlineVisible(this.menuEDocumentPdf, true);
            }
        }
        MenuItem menuItem = this.menuEDespatchInfo;
        if (this.viewModel.erpType() != ErpType.NETSIS || getmSales() == null) {
            z = false;
        } else {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            if (sales.getEDespatch().isSelect()) {
                z = true;
            }
        }
        ContextUtils.setMenuItemOnlineVisible(menuItem, z);
        MenuItem menuItem2 = this.menuSalesDriverInformation;
        if (this.viewModel.erpType() != ErpType.TIGER || getmSales() == null) {
            z2 = false;
        } else {
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            if (!sales2.getEDespatch().isSelect()) {
            }
        }
        ContextUtils.setMenuItemOnlineVisible(menuItem2, z2);
        ContextUtils.setMenuItemOnlineVisible(this.menuLastSalesProducts, SalesUtils.isSalesTypeOrder(getSalesType()));
        return super.onPrepareOptionsMenu(menu);
    }

    private void setSalesOrderFicheTransferMenu() {
        if (this.mCustomerOperation.getFicheMode() != FicheMode.ANALYSE) {
            if (SalesUtils.isSalesOrderFicheTransferMenu(getSalesType()) || !isConnected(this)) {
                ContextUtils.toggleMenuItemVisible(this.menuSalesOrderTransfer, false);
                ContextUtils.toggleMenuItemVisible(this.menuSalesOrderDetailTransfer, false);
                ContextUtils.toggleMenuItemVisible(this.menuSalesDistributionTransfer, false);
            } else {
                ContextUtils.toggleMenuItemVisible(this.menuSalesOrderTransfer, true);
                ContextUtils.toggleMenuItemVisible(this.menuSalesOrderDetailTransfer, true);
                ContextUtils.toggleMenuItemVisible(this.menuSalesDistributionTransfer, this.viewModel.erpType() != ErpType.NETSIS);
            }
        }
    }

    private void setSalesCampaignAndSalesCondition() {
        if (getSalesFicheUserRights().isDoCampaignSalesCondition()) {
            if (getSalesFicheUserRights().getApplySalesCondition() != 2) {
                ContextUtils.toggleMenuItemVisible(this.menuDoSalesCondition, false);
            } else {
                Sales sales = getmSales();
                Intrinsics.checkNotNull(sales);
                if (sales.getSalesCondition() == 1) {
                    MenuItem menuItem = this.menuDoSalesCondition;
                    Intrinsics.checkNotNull(menuItem);
                    menuItem.setTitle(getString(R.string.menu_sales_cancel_sales_condition));
                } else {
                    MenuItem menuItem2 = this.menuDoSalesCondition;
                    Intrinsics.checkNotNull(menuItem2);
                    menuItem2.setTitle(getString(R.string.menu_sales_do_sales_condition));
                }
            }
            if (getSalesFicheUserRights().getApplyCampaign() <= 0) {
                ContextUtils.toggleMenuItemVisible(this.menuApplyCampaign, false);
                ContextUtils.toggleMenuItemVisible(this.menuClearCampaign, false);
                return;
            }
            return;
        }
        ContextUtils.toggleMenuItemVisible(this.menuDoSalesCondition, false);
        ContextUtils.toggleMenuItemVisible(this.menuApplyCampaign, false);
        ContextUtils.toggleMenuItemVisible(this.menuClearCampaign, false);
    }

    private boolean shouldCampaignReApplied() {
        String string;
        EditText editTextDocumentNoFromHeaderFragment = getEditTextDocumentNoFromHeaderFragment();
        Editable text = editTextDocumentNoFromHeaderFragment != null ? editTextDocumentNoFromHeaderFragment.getText() : null;
        if (text == null || text.length() == 0) {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            string = sales.getDocumentNo().toString();
            checkNotNullExpressionValue(string, "toString(...)");
        } else {
            EditText editTextDocumentNoFromHeaderFragment2 = getEditTextDocumentNoFromHeaderFragment();
            Intrinsics.checkNotNull(editTextDocumentNoFromHeaderFragment2);
            string = editTextDocumentNoFromHeaderFragment2.getText().toString();
        }
        if (getmSales() != null) {
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            return sales2.hasCampaignApplied() && !Intrinsics.areEqual(this.documentNumberForAppliedCampagin, string);
        }
        return false;
    }

    public void notifyReApplyCampaignIfAppliedBeforeIfValuesHaveChanged(String[] valueControlList) {
        Intrinsics.checkNotNullParameter(valueControlList, "valueControlList");
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        if (sales.hasCampaignApplied() && !Intrinsics.areEqual(valueControlList[0], valueControlList[1])) {
            notifyReApplyCampaign();
        }
    }

    public void notifyReApplyCampaign() {
        ScreenControl screenControl = this.mScreenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.hideKeyboard(this);
        try {
            openSalesCampaignDialog();
        } catch (Exception e2) {
            Log.e(TAG, "Error on checkShouldReApplyCampaign", e2);
        }
    }

    private void controlToSaveFiche() {
        if (isNegativeBalanceShouldBeShown()) {
            checkHasNegativeBalance();
        } else {
            checkPrice();
        }
    }

    private boolean isNegativeBalanceShouldBeShown() {
        boolean zShowNegativeBalance = this.viewModel.getBaseErp().showNegativeBalance();
        boolean zStopNegativeBalance = this.viewModel.getBaseErp().stopNegativeBalance();
        return this.viewModel.erpType() == ErpType.NETSIS && ((SalesUtils.INSTANCE.isSales(getSalesType()) && zShowNegativeBalance && !zStopNegativeBalance) || (SalesUtils.isSalesTypeOrder(getSalesType()) && (zShowNegativeBalance || zStopNegativeBalance)));
    }

    private void checkHasNegativeBalance() {
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        Iterator<SalesDetail> it = mSalesDetailList.iterator();
        String string = "";
        while (it.hasNext()) {
            SalesDetail next = it.next();
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            Intrinsics.checkNotNull(next);
            sb.append(sales2.exceptionNegativeBalanceMessage(next));
            string = sb.toString();
            if (!TextUtils.isEmpty(string)) {
                string = string + '\n';
            }
        }
        checkExceptionMessage(string);
    }
    final class C31051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Ref.ObjectRef<String> exceptionMessage;
        Object L0;
        Object L1;
        int label;

        C31051(Ref.ObjectRef refObjectRef, Continuation<? super C31051> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.exceptionMessage = refObjectRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesActivityNew.this.new C31051(this.exceptionMessage, (Continuation<? super C31051>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public   Object invokeSuspend(Object obj)  {
            Iterator<SalesDetail> it;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                int lastPurchasePriceParam = SalesActivityNew.this.viewModel.getBaseErp().getLastPurchasePriceParam(SalesActivityNew.this.getSalesType());
                PurchasePriceParamValues purchasePriceParamValues = PurchasePriceParamValues.WARN_USER;
                if (lastPurchasePriceParam != purchasePriceParamValues.getValue()) {
                    if (SalesActivityNew.this.viewModel.getBaseErp().getDefinedPurchasePriceParam(SalesActivityNew.this.getSalesType()) == PurchasePriceParamValues.BLOCK.getValue() || SalesActivityNew.this.viewModel.getBaseErp().getDefinedPurchasePriceParam(SalesActivityNew.this.getSalesType()) != purchasePriceParamValues.getValue()) {
                        SalesActivityNew.this.saveFicheWithRateControl();
                    } else {
                        PurchasePriceUtils purchasePriceUtils = PurchasePriceUtils.INSTANCE;
                        Sales sales = SalesActivityNew.this.getmSales();
                        Intrinsics.checkNotNull(sales);
                        purchasePriceUtils.initialize(sales);
                        Sales sales2 = SalesActivityNew.this.getmSales();
                        Intrinsics.checkNotNull(sales2);
                        ArrayList<SalesDetail> mSalesDetailList = sales2.getMSalesDetailList();
                        Intrinsics.checkNotNull(mSalesDetailList);
                        Iterator<SalesDetail> it2 = mSalesDetailList.iterator();
                        while (it2.hasNext()) {
                            SalesDetail next = it2.next();
                            double definedPurchasePrice = SalesActivityNew.this.viewModel.getDefinedPurchasePrice(next.getItemRef());
                            PurchasePriceUtils purchasePriceUtils2 = PurchasePriceUtils.INSTANCE;
                            Intrinsics.checkNotNull(next);
                            if (purchasePriceUtils2.getSalesDetailPrice(next) < definedPurchasePrice) {
                                Ref.ObjectRef<String> refObjectRef = this.exceptionMessage;
                                StringBuilder sb = new StringBuilder();
                                sb.append(this.exceptionMessage.element);
                                Context context = ContextUtils.getmContext();
                                Intrinsics.checkNotNull(context);
                                sb.append(context.getString(R.string.exp_price_below_defined_purchase_price, next.getCode(), Boxing.boxDouble(definedPurchasePrice)));
                                refObjectRef.element = sb.toString();
                                if (!TextUtils.isEmpty(this.exceptionMessage.element)) {
                                    this.exceptionMessage.element = this.exceptionMessage.element + '\n';
                                }
                            }
                        }
                        SalesActivityNew.this.checkExceptionMessage(this.exceptionMessage.element);
                    }
                } else {
                    PurchasePriceUtils purchasePriceUtils3 = PurchasePriceUtils.INSTANCE;
                    Sales sales3 = SalesActivityNew.this.getmSales();
                    Intrinsics.checkNotNull(sales3);
                    purchasePriceUtils3.initialize(sales3);
                    if (ContextUtils.checkInternetConnection()) {
                        Sales sales4 = SalesActivityNew.this.getmSales();
                        Intrinsics.checkNotNull(sales4);
                        ArrayList<SalesDetail> mSalesDetailList2 = sales4.getMSalesDetailList();
                        Intrinsics.checkNotNull(mSalesDetailList2);
                        it = mSalesDetailList2.iterator();
                        if (it.hasNext()) {
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SalesDetail next2 = (SalesDetail) this.L1;
            it = (Iterator) this.L0;
            ResultKt.throwOnFailure(obj);
            double dDoubleValue = ((Number) obj).doubleValue();
            PurchasePriceUtils purchasePriceUtils4 = PurchasePriceUtils.INSTANCE;
            Intrinsics.checkNotNull(next2);
            if (purchasePriceUtils4.getSalesDetailPrice(next2) <= dDoubleValue) {
                Ref.ObjectRef<String> refObjectRef2 = this.exceptionMessage;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.exceptionMessage.element);
                Context context2 = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context2);
                sb2.append(context2.getString(R.string.exp_price_below_last_purchase_price, next2.getCode(), Boxing.boxDouble(purchasePriceUtils4.getPurchasePrice())));
                refObjectRef2.element = sb2.toString();
                if (!TextUtils.isEmpty(this.exceptionMessage.element)) {
                    this.exceptionMessage.element = this.exceptionMessage.element + '\n';
                }
            }
            if (it.hasNext()) {
                next2 = it.next();
                PurchasePriceUtils purchasePriceUtils5 = PurchasePriceUtils.INSTANCE;
                Intrinsics.checkNotNull(next2);
                this.L0 = it;
                this.L1 = next2;
                this.label = 1;
                try {
                    obj = purchasePriceUtils5.getLastPurchasePrice(next2, this);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
                if (obj == obj2) {
                    return obj2;
                }
                double dDoubleValue2 = ((Number) obj).doubleValue();
                PurchasePriceUtils purchasePriceUtils42 = PurchasePriceUtils.INSTANCE;
                Intrinsics.checkNotNull(next2);
                if (purchasePriceUtils42.getSalesDetailPrice(next2) <= dDoubleValue2) {
                }
                if (it.hasNext()) {
                    SalesActivityNew.this.checkExceptionMessage(this.exceptionMessage.element);
                    return Unit.INSTANCE;
                }
            }
            return obj2;
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
    private void checkPrice() {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        BuildersKt.launch(this.customScope, null, null, new C31051(refObjectRef, null));
    }
    public void checkExceptionMessage(String str) {
        if (!TextUtils.isEmpty(str)) {
            warnUserAlertDialog(str);
        } else {
            saveFicheWithRateControl();
        }
    }

    private void warnUserAlertDialog(String str) {
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        builder.setTitle(context2.getString(R.string.str_sales_save_detail_error_message_title)).setMessage(str).setPositiveButton(R.string.str_save, (dialogInterface, r2) -> SalesActivityNew.warnUserAlertDialoglambda1(this, dialogInterface, r2)).setNegativeButton(R.string.str_form_dialog_cancel, (dialogInterface, r2) -> SalesActivityNew.warnUserAlertDialoglambda2(dialogInterface, r2)).show();
    }
    public static void warnUserAlertDialoglambda1(SalesActivityNew this0, DialogInterface alertDialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(alertDialog, "alertDialog");
        this0.saveFicheWithRateControl();
        alertDialog.dismiss();
    }
    public static void warnUserAlertDialoglambda2(DialogInterface alertDialog, int r1) {
        Intrinsics.checkNotNullParameter(alertDialog, "alertDialog");
        alertDialog.dismiss();
    }
    public boolean onOptionsItemSelected(MenuItem item) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        if (shouldCampaignReApplied()) {
            notifyReApplyCampaign();
            return false;
        }
        if (item.getItemId() == R.id.menu_fiche_save && checkOneClick()) {
            controlToSaveFiche();
        }
        if (Intrinsics.areEqual(item, this.menuSalesDriverInformation)) {
            Intent intent = new Intent(this, DriverInformationActivity.class);
            String str = IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO;
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            intent.putExtra(str, sales.getEDispatchAdditionalInfo());
            intent.putExtra(EDispatchExtraInfoActivity.Companion.getEXTRA_SALES_FICHE_MODE(), getSalesFicheMode());
            startActivityForResult(intent, IntentExtraName.EDESPATCH_EXTRA_INFO);
        }
        if (Intrinsics.areEqual(item, this.menuDoSalesCondition)) {
            openSalesConditionDialog();
        }
        if (Intrinsics.areEqual(item, this.menuSalesShowCurrencyPrice)) {
            showCurrencyPrice();
        }
        if (Intrinsics.areEqual(item, this.menuApplyCampaign)) {
            try {
                openSalesCampaignDialog();
            } catch (CloneNotSupportedException e2) {
                e2.printStackTrace();
            }
        }
        if (Intrinsics.areEqual(item, this.menuClearCampaign)) {
            clearCampaign();
            ViewPager viewPager = this.mViewPager;
            Intrinsics.checkNotNull(viewPager);
            viewPager.setCurrentItem(1);
            SalesPagerAdapter salesPagerAdapter = this.mAdapter;
            Intrinsics.checkNotNull(salesPagerAdapter);
            salesPagerAdapter.updateFragments();
        }
        if (Intrinsics.areEqual(item, this.menuOnlineTotal)) {
            try {
                clearCampaign();
                onlineTotalShow();
            } catch (CloneNotSupportedException e3) {
                e3.printStackTrace();
            }
        }
        if (Intrinsics.areEqual(item, this.menuEDespatchInfo)) {
            Intent intent2 = new Intent(this, EDispatchExtraInfoActivity.class);
            String str2 = IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO;
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            intent2.putExtra(str2, sales2.getEDispatchAdditionalInfo());
            intent2.putExtra(EDispatchExtraInfoActivity.Companion.getEXTRA_SALES_FICHE_MODE(), getSalesFicheMode());
            startActivityForResult(intent2, IntentExtraName.EDESPATCH_EXTRA_INFO);
        }
        if (Intrinsics.areEqual(item, this.menuReports)) {
            startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
        }
        if (Intrinsics.areEqual(item, this.menuSalesOrderTransfer) || Intrinsics.areEqual(item, this.menuSalesOrderDetailTransfer)) {
            Intent intent3 = new Intent(this, SalesOrderListActivity.class);
            String str3 = IntentExtraName.SALES_TYPE;
            Sales sales3 = this.mSales;
            Intrinsics.checkNotNull(sales3);
            intent3.putExtra(str3, sales3.getmSalesType());
            ArrayList<Integer> arrayList = new ArrayList<>();
            Sales sales4 = getmSales();
            Intrinsics.checkNotNull(sales4);
            ArrayList<SalesDetail> mSalesDetailList = sales4.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            Iterator<SalesDetail> it = mSalesDetailList.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().getOrderDetailReference()));
            }
            intent3.putIntegerArrayListExtra(IntentExtraName.ORDER_DETAIL_REF_LIST, arrayList);
            intent3.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
            String str4 = IntentExtraName.EXTRA_WAREHOUSE_REF;
            Sales sales5 = this.mSales;
            Intrinsics.checkNotNull(sales5);
            intent3.putExtra(str4, sales5.getWareHouse().getLogicalRef());
            boolean zAreEqual = Intrinsics.areEqual(item, this.menuSalesOrderDetailTransfer);
            this.mIsDetailOrder = zAreEqual;
            intent3.putExtra(IntentExtraName.EXTRA_SALES_ORDER_DETAIL_LIST, zAreEqual);
            startActivityForResult(intent3, 1071);
        }
        if (Intrinsics.areEqual(item, this.menuSalesDistributionTransfer)) {
            Intent intent4 = new Intent(this, DistributionListActivity.class);
            intent4.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
            String str5 = IntentExtraName.EXTRA_SALES_TYPE;
            Sales sales6 = this.mSales;
            Intrinsics.checkNotNull(sales6);
            intent4.putExtra(str5, sales6.getmSalesType());
            startActivityForResult(intent4, 1072);
        }
        if (item.getItemId() == R.id.menu_fiche_cancel) {
            onBackPressed();
        }
        if (Intrinsics.areEqual(item, this.menuLastSalesProducts)) {
            Intent intent5 = new Intent(this, LastOrderProductsActivity.class);
            intent5.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
            startActivity(intent5);
        }
        if (item.getItemId() == R.id.menu_add_all_whouse_items) {
            Sales sales7 = getmSales();
            Intrinsics.checkNotNull(sales7);
            ArrayList<SalesDetail> mSalesDetailList2 = sales7.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            if (mSalesDetailList2.size() > 0) {
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
                Intrinsics.checkNotNull(alertDialogBuilder);
                alertDialogBuilder.setMessage(getString(R.string.str_existing_items_will_deleted)).setNegativeButton(R.string.cancel, (dialogInterface, r2) -> SalesActivityNew.onOptionsItemSelectedlambda3(dialogInterface, r2)).setPositiveButton(R.string.ok, (dialogInterface, r2) -> SalesActivityNew.onOptionsItemSelectedlambda4(this, dialogInterface, r2)).create().show();
            } else {
                startAddingWarehouseItems();
            }
        }
        if (Intrinsics.areEqual(item, this.menuUpdateStockAmounts)) {
            ViewPager viewPager2 = this.mViewPager;
            Intrinsics.checkNotNull(viewPager2);
            viewPager2.setCurrentItem(1);
            SalesPagerAdapter salesPagerAdapter2 = this.mAdapter;
            Intrinsics.checkNotNull(salesPagerAdapter2);
            salesPagerAdapter2.updateStockAmounts();
        }
        PdfHelper pdfHelper = null;
        if (Intrinsics.areEqual(item, this.menuPdf)) {
            Sales sales8 = this.mSales;
            Intrinsics.checkNotNull(sales8);
            PdfHelper pdfHelper2 = new PdfHelper(this, sales8);
            this.pdfHelper = pdfHelper2;
            pdfHelper2.setSelectedSalesItemPdfOperation(PdfOperation.SalesPdf);
            PdfHelper pdfHelper3 = this.pdfHelper;
            if (pdfHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pdfHelper");
                pdfHelper3 = null;
            }
            pdfHelper3.setupBottomSheetDialog(this);
        }
        if (Intrinsics.areEqual(item, this.menuEDocumentPdf)) {
            Sales sales9 = this.mSales;
            Intrinsics.checkNotNull(sales9);
            PdfHelper pdfHelper4 = new PdfHelper(this, sales9);
            this.pdfHelper = pdfHelper4;
            pdfHelper4.setSelectedSalesItemPdfOperation(PdfOperation.EDocumentPdf);
            PdfHelper pdfHelper5 = this.pdfHelper;
            if (pdfHelper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pdfHelper");
            } else {
                pdfHelper = pdfHelper5;
            }
            pdfHelper.setupBottomSheetDialog(this);
        }
        return super.onOptionsItemSelected(item);
    }
    public static   void onOptionsItemSelectedlambda3(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public static   void onOptionsItemSelectedlambda4(SalesActivityNew this0, DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.startAddingWarehouseItems();
    }

    private   void startAddingWarehouseItems() {
        SalesNewViewModel salesNewViewModel = this.viewModel;
        String warehouseUnsentDataTypes = salesNewViewModel.getWarehouseUnsentDataTypes(salesNewViewModel.user().getWarehouseNr());
        if (!TextUtils.isEmpty(warehouseUnsentDataTypes)) {
            AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setMessage(getString(R.string.str_customer_unsent_data, warehouseUnsentDataTypes)).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.startAddingWarehouseItemslambda5(dialogInterface, r2);
                }
            }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                private SalesActivityNew f0;

                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.startAddingWarehouseItemslambda6(this.f0);
                }
            }).create().show();
        } else {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            SalesNewViewModel salesNewViewModel2 = this.viewModel;
            salesNewViewModel2.getWarehouseItems(salesNewViewModel2.user().getWarehouseNr(), new GetAllWarehouseItemsListener(this));
        }
    }
    public static   void startAddingWarehouseItemslambda5(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public static   void startAddingWarehouseItemslambda6(SalesActivityNew this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mProgressDialogBuilder.setMessage(this0.getString(R.string.str_please_wait)).show();
        SalesNewViewModel salesNewViewModel = this0.viewModel;
        salesNewViewModel.getWarehouseItems(salesNewViewModel.user().getWarehouseNr(), new GetAllWarehouseItemsListener(this0));
    }
    private   void onlineTotalShow() throws CloneNotSupportedException {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        if (mSalesDetailList.size() > 0) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            SalesNewViewModel salesNewViewModel = this.viewModel;
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            salesNewViewModel.showOnlineTotal(sales2.clone(), new SalesActivityCalculateTotalListener(this));
            return;
        }
        Toast.makeText(this, getString(R.string.str_question_select_material), Toast.LENGTH_LONG).show();
    }
    public   void saveFicheWithRateControl() {
        if (this.viewModel.erpType() == ErpType.TIGER) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mCheckRateProgressDialogBuilder;
            ProgressDialogBuilder message = progressDialogBuilder != null ? progressDialogBuilder.setMessage(getString(R.string.str_checking_exchange_rates)) : null;
            Intrinsics.checkNotNull(message);
            message.show();
            try {
                this.viewModel.checkSalesHasExchangeRates(this.mSales.clone(), new SalesActivityCheckRateFicheListener(this));
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        saveFicheWithNegativeAndRiskControl();
    }
    public   void onCheckRateFiche(String str, String str2) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCheckRateProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            saveFicheWithNegativeAndRiskControl();
        } else {
            showControlMessage(R.string.str_sales_save_detail_error_message_title, str);
        }
    }

    private record SalesActivityCheckRateFicheListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<String> {
            private SalesActivityCheckRateFicheListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel str) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCheckRateFiche(str, "");
                }
            }

        public void onFailure(Throwable throwable) {
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "SalesActivityCheckRateFicheListener: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCheckRateFiche("", errorMessage);
                }
            }
        }

    private record SalesActivityCampaignSaveFicheListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<TigerServiceResult> {
            private SalesActivityCampaignSaveFicheListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        @Override
            public void onResponse(PrintSlipModel tigerServiceResult) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCampaignResultSaveFiche(tigerServiceResult, "");
                }
            }

        @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCampaignResultSaveFiche(null, errorMessage);
                }
            }
        }
    public void onCampaignResultSaveFiche(TigerServiceResult tigerServiceResult, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (tigerServiceResult != null) {
            if (tigerServiceResult.isSuccess()) {
                try {
                    new TigerXmlParserForUpdate().parseXml(tigerServiceResult);
                } catch (Exception e2) {
                    String message = e2.getMessage();
                    Intrinsics.checkNotNull(message);
                    Log.e("TAG", message);
                }
                saveFicheWithNegativeAndRiskControl();
            } else {
                Toast.makeText(this, tigerServiceResult.getErrorString(), Toast.LENGTH_SHORT).show();
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private void saveFicheWithNegativeAndRiskControl() {
        if (this.viewModel.getCheckCustomerRiskAffect() && this.viewModel.getCheckCustomerRiskControl()) {
            if (this.viewModel.erpType() == ErpType.NETSIS) {
                doNetsisRiskControl();
                return;
            } else {
                doRiskControls();
                return;
            }
        }
        saveFicheWithMatterNoControl();
    }
    private void doNetsisRiskControl() {
        if (this.mRiskAlert == RiskAlert.UNDEFINED) {
            AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderAlert;
            Intrinsics.checkNotNull(alertDialogBuilder);
            AlertDialogBuilder title = alertDialogBuilder.setTitle(R.string.str_risk_over_title);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%s %s", Arrays.copyOf(new Object[]{getString(R.string.str_not_found_customer_risk), getString(R.string.str_question_cancel_fiche)}, 2));
            checkNotNullExpressionValue(str, "format(...)");
            title.setMessage(str).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
                private SalesActivityNew f0;

                public   void onClick(DialogInterface dialogInterface, int r2) {
                    doNetsisRiskControllambda7(this.f0, dialogInterface, r2);
                }
            }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() {
                public   void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.doNetsisRiskControllambda8(dialogInterface, r2);
                }
            }).create().show();
            return;
        }
        doRiskControls();
    }
    public void doNetsisRiskControllambda7(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onBackPressed();
        dialog.dismiss();
    }
    public static   void doNetsisRiskControllambda8(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    private   void doRiskControls() {
        if (!SalesUtils.isSalesTypeDemandOrWhTransfer(getSalesType())) {
            if (SalesUtils.isSalesTypeOrder(getSalesType())) {
                Sales sales = this.mSales;
                Intrinsics.checkNotNull(sales);
                if (sales.getSalesStatus() != OrderStatus.UNDISPATCHABLE.getmStatus()) {
                    RiskAlert riskAlert = this.mRiskAlert;
                    if (riskAlert != RiskAlert.CONTINUE && riskAlert != RiskAlert.UNDEFINED) {
                        this.mProgressDialogBuilder.setMessage(getString(R.string.str_check_risk_limit_please_wait)).setCancelable(false).show();
                        this.viewModel.getCustomerRiskCalculate(getmCustomerRef(), new CustomerRiskInfoUtils.CommonRiskResponseListener(this, new Function2<SalesActivityNew, List<? extends ClRisk>, Unit>() {
                            @Override
                            public Unit invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                                return null;
                            }

                            public Unit invoke(SalesActivityNew salesActivityNew, List<? extends ClRisk> list) {
                                invoke2(salesActivityNew, (List<ClRisk>) list);
                                return Unit.INSTANCE;
                            }
                            public void invoke2(SalesActivityNew activity, List<ClRisk> list) {
                                Intrinsics.checkNotNullParameter(activity, "activity");
                                if (activity.isActivityDestroyed()) {
                                    return;
                                }
                                activity.onRiskCalculateResponse(list, "");
                            }
                        }, new Function2<SalesActivityNew, String, Unit>() {
                            @Override
                            public Unit invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                                return null;
                            }

                            public Unit invoke(SalesActivityNew salesActivityNew, String str) {
                                invoke2(salesActivityNew, str);
                                return Unit.INSTANCE;
                            }
                            public void invoke2(SalesActivityNew activity, String errorMessage) {
                                Intrinsics.checkNotNullParameter(activity, "activity");
                                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                                if (activity.isActivityDestroyed()) {
                                    return;
                                }
                                activity.onRiskCalculateResponse(null, errorMessage);
                            }
                        }));
                        return;
                    }
                }
            }
        }
        saveFicheWithMatterNoControl();
    }
    public void onRiskCalculateResponse(List<ClRisk> list, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (list != null) {
            this.baseErp.updateCustomerRiskTotals(list.get(0), "", getmCustomerRef());
        }
        controlRisk();
    }
    public   void saveFicheWithMatterNoControl() {
        if (this.mCustomerOperation.getFicheMode() != FicheMode.EDIT) {
            MatterSettings matterSettings = this.mMatterSettings;
            Intrinsics.checkNotNull(matterSettings);
            if (!matterSettings.isUseMatterNo()) {
                saveFicheWithDocNoControl();
                return;
            }
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait_get_matter_no)).show();
            SalesNewViewModel salesNewViewModel = this.viewModel;
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            salesNewViewModel.getMaxMatterNo(sales.getFicheType(this.mCustomerOperation.getSalesType()), this.mMatterSettings, new SalesActivityMatterListener(this));
            return;
        }
        saveFicheWithDocNoControl();
    }

    private   void saveFicheWithDocNoControl() {
        SalesNewViewModel salesNewViewModel = this.viewModel;
        FicheType ficheType = this.mCustomerOperation.getFicheType();
        Intrinsics.checkNotNull(ficheType);
        try {
            if (!salesNewViewModel.getUseDocNoUniqueControl(ficheType)) {
                saveFicheWithDateTrackControl();
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        if (!TextUtils.isEmpty(sales.getDocumentNo().getDefinition())) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait_check_doc_no)).show();
            SalesNewViewModel salesNewViewModel2 = this.viewModel;
            FicheType ficheType2 = this.mCustomerOperation.getFicheType();
            Intrinsics.checkNotNull(ficheType2);
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            String definition = sales2.getDocumentNo().getDefinition();
            checkNotNullExpressionValue(definition, "getDefinition(...)");
            salesNewViewModel2.docNoUniqueControl(ficheType2, definition, new SalesActivityDocNoListener(this));
            return;
        }
        saveFicheWithDateTrackControl();
    }

    private   void saveFicheWithDateTrackControl() {
        DueDate dueDate = this.mDueDate;
        Intrinsics.checkNotNull(dueDate);
        if (dueDate.isControl()) {
            DueDate dueDate2 = this.mDueDate;
            Intrinsics.checkNotNull(dueDate2);
            if (dueDate2.getDueDateAlert() != RiskAlert.CONTINUE) {
                if (SalesUtils.isSalesTypeOrder(getSalesType())) {
                    Sales sales = this.mSales;
                    Intrinsics.checkNotNull(sales);
                    if (!sales.getPaymentOrder().isSelect()) {
                        saveFiche();
                        return;
                    }
                }
                this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait_due_date_control)).show();
                SalesNewViewModel salesNewViewModel = this.viewModel;
                int r1 = getmCustomerRef();
                DueDate dueDate3 = this.mDueDate;
                Intrinsics.checkNotNull(dueDate3);
                salesNewViewModel.getPaySumCustomerForDueDate(r1, dueDate3.getDueDateCount(), new SalesActivityDueDateTrackListener(this));
                return;
            }
        }
        saveFiche();
    }
    protected void saveFiche() {
        try {
            if (isAutoDateTimeAndZoneEnabled()) {
                Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
                refObjectRef.element = "";
                Sales sales = this.mSales;
                if (sales != null) {
                    Intrinsics.checkNotNull(sales);
                    if (sales.salesDetailSizeControl()) {
                        Sales sales2 = this.mSales;
                        Intrinsics.checkNotNull(sales2);
                        ValidationResult validationResultHasDetailsWithZeroQuantity = sales2.hasDetailsWithZeroQuantity();
                        if (validationResultHasDetailsWithZeroQuantity.isSuccess()) {
                            if (!this.salesDetailsLocationChecked) {
                                Sales sales3 = getmSales();
                                Intrinsics.checkNotNull(sales3);
                                if (!sales3.hasStockTracking() || this.viewModel.erpType() != ErpType.TIGER) {
                                    this.salesDetailsLocationChecked = false;
                                    String CustomerEInvoiceFieldsControl = customerEInvoiceFieldsControl();
                                    refObjectRef.element = CustomerEInvoiceFieldsControl;
                                    if (TextUtils.isEmpty(CustomerEInvoiceFieldsControl)) {
                                        String   CustomerEDespatchFieldsControl = customerEDespatchFieldsControl();
                                        refObjectRef.element = CustomerEDespatchFieldsControl;
                                        if (TextUtils.isEmpty(CustomerEDespatchFieldsControl)) {
                                            Sales sales4 = this.mSales;
                                            Intrinsics.checkNotNull(sales4);
                                            String   CheckRequiredFields = sales4.checkRequiredFields(this.viewModel.erpType());
                                            refObjectRef.element = CheckRequiredFields;
                                            if (TextUtils.isEmpty(CheckRequiredFields)) {
                                                Sales sales5 = this.mSales;
                                                Intrinsics.checkNotNull(sales5);
                                                String   SalesHeaderControl = sales5.salesHeaderControl(this.viewModel.erpType());
                                                refObjectRef.element = SalesHeaderControl;
                                                if (TextUtils.isEmpty(SalesHeaderControl)) {
                                                    BuildersKt.launch(this.customScope, null, null, new C31121(refObjectRef, this, null));
                                                } else {
                                                    ViewPager viewPager = this.mViewPager;
                                                    Intrinsics.checkNotNull(viewPager);
                                                    viewPager.setCurrentItem(0);
                                                    showControlMessage(R.string.str_sales_save_header_error_message_title, (String) refObjectRef.element);
                                                }
                                            } else {
                                                showControlMessage(R.string.str_sales_save_header_error_message_title, (String) refObjectRef.element);
                                            }
                                        } else {
                                            showControlMessage(R.string.str_sales_save_header_error_message_title, (String) refObjectRef.element);
                                        }
                                    } else {
                                        showControlMessage(R.string.str_sales_save_header_error_message_title, (String) refObjectRef.element);
                                    }
                                } else if (ContextUtils.checkConnectionWithoutMessage()) {
                                    checkSeriItemLocation(0);
                                } else {
                                    Toast.makeText(this, getString(R.string.str_stock_location_not_suitable), Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            showDetailsWithZeroQuantityMessage(validationResultHasDetailsWithZeroQuantity);
                        }
                    } else {
                        Toast.makeText(this, getString(R.string.exp_31_fiche_not_add_product), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.exp_32_sales_null), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "saveFiche: ", e2);
        }
    }
    static final class C31121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Ref.ObjectRef<String> exceptionMessage;
        Object L0;
        int label;
        final SalesActivityNew this0;
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
        public class WhenMappings {
            public static final int[] EnumSwitchMapping0;

            static {
                int[] r0 = new int[SalesType.values().length];
                try {
                    r0[SalesType.INVOICE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    r0[SalesType.RETURN_INVOICE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    r0[SalesType.RETAIL_INVOICE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    r0[SalesType.RETAIL_RETURN_INVOICE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    r0[SalesType.DISPATCH.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    r0[SalesType.RETURN_DISPATCH.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    r0[SalesType.ONE_TO_ONE_CHANGE.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    r0[SalesType.ORDER.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                EnumSwitchMapping0 = r0;
            }
        }
        C31121(Ref.ObjectRef<String> refObjectRef, SalesActivityNew salesActivityNew, Continuation<? super C31121> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.exceptionMessage = refObjectRef;
            this.this0 = salesActivityNew;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31121(this.exceptionMessage, this.this0, (Continuation<? super C31121>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public   Object invokeSuspend(Object obj)  {
            Ref.ObjectRef<String> refObjectRef;
            String t;
            SalesType salesType;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            int r3 = 1;
            try {
            } catch (Exception e2) {
                Log.e("saveFiche", e2.toString());
            }
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                refObjectRef = this.exceptionMessage;
                Sales sales = this.this0.mSales;
                Intrinsics.checkNotNull(sales);
                SalesFicheParameters salesFicheParameters = this.this0.mSalesFicheParameters;
                Intrinsics.checkNotNull(salesFicheParameters);
                boolean zIsCheckPriceEnter = salesFicheParameters.getMSalesFicheUserRights().isCheckPriceEnter();
                ErpType erpType = this.this0.viewModel.erpType();
                SalesType salesType2 = this.this0.getSalesType();
                this.L0 = refObjectRef;
                this.label = 1;
                Object objSalesDetailListControl = sales.salesDetailListControl(zIsCheckPriceEnter, erpType, salesType2, this);
                t = objSalesDetailListControl.toString();
                if (objSalesDetailListControl == obj2) {
                    return obj2;
                }
            } else {
                if (r1 != 1) {
                    if (r1 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (( this.this0).getmCustomerOperation().getFicheMode() != FicheMode.NEW || (  this.this0).getmCustomerOperation()  .getFicheMode() == FicheMode.COPY) {
                        SalesNewViewModel salesNewViewModel = this.this0.viewModel;
                        Sales sales2 = this.this0.getmSales();
                        Intrinsics.checkNotNull(sales2);
                        salesNewViewModel.saveSalesFicheFromSqlManager(sales2, (  this.this0).mCustomerOperation.getSalesType(), new SalesActivityFicheSaveListener(this.this0));
                        salesType = this.this0.getmCustomerOperation().getSalesType();
                        switch (salesType != null ? -1 : WhenMappings.EnumSwitchMapping0[salesType.ordinal()]) {
                            case 1:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.INVOICE);
                                break;
                            case 2:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.RETURN_INVOICE);
                                break;
                            case 3:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.RETAIL_INVOICE);
                                break;
                            case 4:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.RETAIL_RETURN_INVOICE);
                                break;
                            case 5:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.DISPATCH);
                                break;
                            case 6:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.RETURN_DISPATCH);
                                break;
                            case 7:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.EXCHANGE);
                                break;
                            case 8:
                                this.this0.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.SALES_ORDERS);
                                break;
                        }
                    } else if (( this.this0).getmCustomerOperation().getFicheMode() == FicheMode.EDIT) {
                        Sales sales3 = this.this0.getmSales();
                        Intrinsics.checkNotNull(sales3);
                        try {
                            if (!SalesUtils.isSalesTypeOrder(this.this0.getSalesType()) || !this.this0.viewModel.getOrderChangeOffer()) {
                                r3 = 0;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        sales3.setEdit(r3);
                        SalesNewViewModel salesNewViewModel2 = this.this0.viewModel;
                        Sales sales4 = this.this0.getmSales();
                        Intrinsics.checkNotNull(sales4);
                        salesNewViewModel2.updateSalesFicheFromSqlManager(sales4, this.this0.getmCustomerOperation().getSalesType(), new SalesActivityFicheSaveListener(this.this0));
                    }
                    return Unit.INSTANCE;
                }
                refObjectRef = (Ref.ObjectRef) this.L0;
                ResultKt.throwOnFailure(obj);
                t = obj.toString();
            }
            refObjectRef.element = t;
            if (!TextUtils.isEmpty(StringsKt.replace(this.exceptionMessage.element, SqlLiteVariable._NEW_LINE, "", false))) {
                this.this0.showControlMessage(R.string.str_sales_save_detail_error_message_title, this.exceptionMessage.element);
                return Unit.INSTANCE;
            }
            Sales sales5 = this.this0.getmSales();
            Intrinsics.checkNotNull(sales5);
            sales5.calculateSalesTotal();
            Sales sales6 = this.this0.getmSales();
            Intrinsics.checkNotNull(sales6);
            sales6.setLatitude(this.this0.getLatitude());
            Sales sales7 = this.this0.getmSales();
            Intrinsics.checkNotNull(sales7);
            sales7.setLongitude(this.this0.getLongitude());
            Sales sales8 = this.this0.mSales;
            Intrinsics.checkNotNull(sales8);
            if (SalesUtils.isSalesTypeWhTransfer(sales8.getmSalesType())) {
                SalesNewViewModel salesNewViewModel3 = this.this0.viewModel;
                Sales sales9 = this.this0.mSales;
                Intrinsics.checkNotNull(sales9);
                this.L0 = null;
                this.label = 2;
                try {
                    if (salesNewViewModel3.fillTransferUserWhInfo(sales9, this) == obj2) {
                        return obj2;
                    }
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            if ((this.this0).getmCustomerOperation().getFicheMode() != FicheMode.NEW) {
                SalesNewViewModel salesNewViewModel4 = this.this0.viewModel;
                Sales sales22 = this.this0.getmSales();
                Intrinsics.checkNotNull(sales22);
                salesNewViewModel4.saveSalesFicheFromSqlManager(sales22, this.this0.getmCustomerOperation().getSalesType(), new SalesActivityFicheSaveListener(this.this0));
                salesType = this.this0.getmCustomerOperation().getSalesType();
                switch (salesType != null ? -1 : WhenMappings.EnumSwitchMapping0[salesType.ordinal()]) {
                }
            }
            return Unit.INSTANCE;
        }
    }
    private void showDetailsWithZeroQuantityMessage(ValidationResult validationResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(validationResult.getSpannableErrorMessage(getString(R.string.str_lines_with_zero_quantity_will_be_deleted))).setCancelable(false).setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.showDetailsWithZeroQuantityMessagelambda9(dialogInterface, r2);
            }
        }).setPositiveButton(R.string.str_okey, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.showDetailsWithZeroQuantityMessagelambda10(this.f0, dialogInterface, r2);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
    }
    public static   void showDetailsWithZeroQuantityMessagelambda9(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public static   void showDetailsWithZeroQuantityMessagelambda10(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Sales sales = this0.mSales;
        Intrinsics.checkNotNull(sales);
        sales.removeLinesWithZeroQuantity();
        SalesPagerAdapter salesPagerAdapter = this0.getmAdapter();
        Intrinsics.checkNotNull(salesPagerAdapter);
        salesPagerAdapter.updateFragments();
        this0.saveFicheWithRateControl();
        dialog.dismiss();
    }

    public     void showControlMessage(@StringRes int r1, String str) {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderControl;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(r1).setMessage(str).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.showControlMessagelambda11(dialogInterface, r2);
            }
        }).create().show();
    }

    public static   void showControlMessagelambda11(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public void openSalesConditionDialog() {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        if (sales.getSalesCondition() == 0) {
            AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderCampaign;
            Intrinsics.checkNotNull(alertDialogBuilder);
            alertDialogBuilder.setTitle(R.string.str_do_sales_condition_quest).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                private SalesActivityNew f0;

                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.openSalesConditionDialoglambda12(this.f0, dialogInterface, r2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.openSalesConditionDialoglambda13(dialogInterface, r2);
                }
            }).create().show();
        } else {
            AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilderCampaign;
            Intrinsics.checkNotNull(alertDialogBuilder2);
            alertDialogBuilder2.setTitle(R.string.str_cancel_sales_condition_quest).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                private SalesActivityNew f0;

                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.openSalesConditionDialoglambda14(this.f0, dialogInterface, r2);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int r2) {
                    SalesActivityNew.openSalesConditionDialoglambda15(dialogInterface, r2);
                }
            }).create().show();
        }
    }
    public static void openSalesConditionDialoglambda12(SalesActivityNew this0, DialogInterface dialogInterface, int r3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (r3 == -1) {
            Sales sales = this0.mSales;
            Intrinsics.checkNotNull(sales);
            sales.setSalesCondition(1);
            MenuItem menuItem = this0.menuDoSalesCondition;
            Intrinsics.checkNotNull(menuItem);
            menuItem.setTitle(this0.getString(R.string.menu_sales_cancel_sales_condition));
            dialogInterface.dismiss();
        }
    }
    public static void openSalesConditionDialoglambda13(DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (r2 == -2) {
            dialogInterface.dismiss();
        }
    }
    public static void openSalesConditionDialoglambda14(SalesActivityNew this0, DialogInterface dialogInterface, int r3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (r3 == -1) {
            Sales sales = this0.mSales;
            Intrinsics.checkNotNull(sales);
            sales.setSalesCondition(0);
            MenuItem menuItem = this0.menuDoSalesCondition;
            Intrinsics.checkNotNull(menuItem);
            menuItem.setTitle(this0.getString(R.string.menu_sales_do_sales_condition));
            dialogInterface.dismiss();
        }
    }
    public static void openSalesConditionDialoglambda15(DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (r2 == -2) {
            dialogInterface.dismiss();
        }
    }
    public   void showCurrencyPrice() {
        SalesPagerAdapter salesPagerAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesPagerAdapter);
        salesPagerAdapter.showCurrencyPriceTextView();
    }

    public   void openSalesCampaignDialog() throws Resources.NotFoundException, CloneNotSupportedException {
        showOnlineCampaignDetail();
    }

    public   void showOnlineCampaignDetail() throws Resources.NotFoundException, CloneNotSupportedException {
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        if (mSalesDetailList.size() > 0) {
            if (this.viewModel.erpType() == ErpType.NETSIS) {
                if (ContextUtils.checkInternetConnection()) {
                    ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
                    Intrinsics.checkNotNull(progressDialogBuilder);
                    progressDialogBuilder.show();
                    SalesNewViewModel salesNewViewModel = this.viewModel;
                    Sales sales2 = this.mSales;
                    Intrinsics.checkNotNull(sales2);
                    salesNewViewModel.showOnlineCampaign(sales2, new NetsisSalesActivityCampaignListener(this));
                    return;
                }
                return;
            }
            if (ContextUtils.checkConnectionWithoutMessage()) {
                ProgressDialogBuilder<?> progressDialogBuilder2 = this.mCampaignProgressDialogBuilder;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.show();
                try {
                    if (this.viewModel.canApplySelectedCampaign()) {
                        SalesNewViewModel salesNewViewModel2 = this.viewModel;
                        Sales sales3 = this.mSales;
                        Intrinsics.checkNotNull(sales3);
                        salesNewViewModel2.getUsableCampaignCards(sales3, new UsableCampaignCardsListener(this));
                        return;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                applyCampaign();
                return;
            }
            clearCampaign();
            final StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.str_campaign_info_cleared));
            sb.append(System.getProperty("line.separator"));
            sb.append(getString(R.string.str_connect_internet_message));
            ViewPager viewPager = this.mViewPager;
            Intrinsics.checkNotNull(viewPager);
            viewPager.setCurrentItem(1);
            SalesPagerAdapter salesPagerAdapter = getmAdapter();
            Intrinsics.checkNotNull(salesPagerAdapter);
            salesPagerAdapter.updateFragments();
            try {
                runOnUiThread(new Runnable() {
                    private SalesActivityNew f0;
                    public void run() {
                        SalesActivityNew.showOnlineCampaignDetaillambda16(this.f0, sb);
                    }
                });
                return;
            } catch (Exception e2) {
                String message = e2.getMessage();
                Intrinsics.checkNotNull(message);
                Log.e(TAG, message);
                return;
            }
        }
        Toast.makeText(this, getString(R.string.str_question_select_material), Toast.LENGTH_SHORT).show();
    }
    public static  void showOnlineCampaignDetaillambda16(SalesActivityNew this0, StringBuilder builder) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Toast.makeText(this0, builder.toString(), Toast.LENGTH_LONG).show();
    }

    private  void clearCampaign() {
        this.documentNumberForAppliedCampagin = "";
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        sales.clearCampaign();
    }
    public void onBackPressed() {
        if (getSalesFicheMode() == FicheMode.ANALYSE) {
            super.onBackPressed();
        } else {
            cancelFicheDialog();
        }
    }

    private  void cancelFicheDialog() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(R.string.str_cancel_sales_fiche_title).setMessage(R.string.str_return_sales_fiche_question).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public   void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.cancelFicheDialoglambda17(this.f0, dialogInterface, r2);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public   void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.cancelFicheDialoglambda18(dialogInterface, r2);
            }
        }).setNeutralButton(R.string.str_save, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public   void onClick(DialogInterface dialogInterface, int r2) {
                SalesActivityNew.cancelFicheDialoglambda19(this.f0, dialogInterface, r2);
            }
        }).create().show();
    }
    public static   void cancelFicheDialoglambda17(SalesActivityNew this0, DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
        this0.cancelFiche();
    }
    public static   void cancelFicheDialoglambda18(DialogInterface dialogInterface, int r1) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static   void cancelFicheDialoglambda19(SalesActivityNew this0, DialogInterface dialogInterface, int r3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        if (-3 == r3) {
            dialogInterface.dismiss();
            this0.saveFicheWithRateControl();
        }
    }
    public void cancelFiche() {
        this.mSales = null;
        super.onBackPressed();
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putStringArray(STATE_SALES_TAB_NAME, this.mSalesTabName);
         try {
             outState.putInt("bigdata:synccode", this.viewModel.getSaveObjectWithSales(this.mSales));
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         outState.putParcelable(STATE_SALES_FICHE_PARAMETERS, this.mSalesFicheParameters);
        RiskAlert riskAlert = this.mRiskAlert;
        Intrinsics.checkNotNull(riskAlert);
        outState.putInt(STATE_RISK_ALERT, riskAlert.getValue());
        outState.putParcelable(STATE_MATTER_SETTINGS, this.mMatterSettings);
        outState.putParcelable(STATE_CUSTOMER_DISCOUNT, this.customerDiscount);
        outState.putParcelable(STATE_UNIT_DECIMAL_FORMATTER, this.mUnitPriceFormatter);
        outState.putParcelable(STATE_DUE_DATE, this.mDueDate);
        outState.putBoolean(STATE_ISDETAIL_ORDER, this.mIsDetailOrder);
        outState.putInt("state:cabinRef", this.cabinRef);
        super.onSaveInstanceState(outState);
    }
    public <T> T getCurrent(Class<T> cls) {
        SalesPagerAdapter salesPagerAdapter = this.mAdapter;
        if (salesPagerAdapter == null) {
            return null;
        }
        Intrinsics.checkNotNull(salesPagerAdapter);
        ViewPager viewPager = this.mViewPager;
        Intrinsics.checkNotNull(viewPager);
        T t = (T) salesPagerAdapter.getItem(viewPager.getCurrentItem());
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }
    protected void onPostResume() {
        super.onPostResume();
    }

    private void loadPager() throws Resources.NotFoundException {
        if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
            this.mSalesTabName = getResources().getStringArray(R.array.array_sales_transfer_tab);
        } else {
            this.mSalesTabName = getResources().getStringArray(R.array.array_sales_tab);
        }
        ViewPager viewPager = this.mViewPager;
        Intrinsics.checkNotNull(viewPager);
        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.divider));
        ViewPager viewPager2 = this.mViewPager;
        Intrinsics.checkNotNull(viewPager2);
        viewPager2.setPageMarginDrawable(R.color.blackT12);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        String[] strArr = this.mSalesTabName;
        Intrinsics.checkNotNull(strArr);
        this.mAdapter = new SalesPagerAdapter(this, supportFragmentManager, strArr, this.mCustomerRef);
        ViewPager viewPager3 = this.mViewPager;
        Intrinsics.checkNotNull(viewPager3);
        viewPager3.setAdapter(this.mAdapter);
        TabLayout tabLayout = this.mTabLayout;
        Intrinsics.checkNotNull(tabLayout);
        tabLayout.setupWithViewPager(this.mViewPager);
        TabLayout tabLayout2 = this.mTabLayout;
        Intrinsics.checkNotNull(tabLayout2);
        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                if (tab.getPosition() == 0) {
                    SalesActivityNew.this.setEdtDocumentNoOnFocusChangeListener(false);
                }
            }
            public void onTabUnselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                if (tab.getPosition() == 0) {
                    SalesActivityNew.this.setEdtDocumentNoOnFocusChangeListener(true);
                }
            }
            public void onTabReselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                Scrollable scrollable = SalesActivityNew.this.getCurrent(Scrollable.class);
                if (scrollable != null) {
                    scrollable.scrollToTop();
                }
                AppBarLayout appBarLayout = SalesActivityNew.this.mAppBar;
                Intrinsics.checkNotNull(appBarLayout);
                appBarLayout.setExpanded(true, true);
            }
        });
        ViewPager viewPager4 = this.mViewPager;
        Intrinsics.checkNotNull(viewPager4);
        viewPager4.setCurrentItem(1);
    }

    private  EditText getEditTextDocumentNoFromHeaderFragment() {
        ViewPager viewPager = this.mViewPager;
        Intrinsics.checkNotNull(viewPager);
        SalesPagerAdapter salesPagerAdapter = (SalesPagerAdapter) viewPager.getAdapter();
        Intrinsics.checkNotNull(salesPagerAdapter);
        Fragment item = salesPagerAdapter.getItem(0);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.newadd.SalesHeaderFragment");
        return ((SalesHeaderFragment) item).getEdtDocumentNo();
    }
    public   void setEdtDocumentNoOnFocusChangeListener(boolean z) {
        final EditText editTextDocumentNoFromHeaderFragment = getEditTextDocumentNoFromHeaderFragment();
        if (editTextDocumentNoFromHeaderFragment != null) {
            if (z) {
                if (getmSales() != null) {
                    Sales sales = getmSales();
                    Intrinsics.checkNotNull(sales);
                    if (sales.hasCampaignApplied() && !Intrinsics.areEqual(this.documentNumberForAppliedCampagin, editTextDocumentNoFromHeaderFragment.getText().toString())) {
                        notifyReApplyCampaign();
                    }
                }
                editTextDocumentNoFromHeaderFragment.setOnFocusChangeListener(null);
                return;
            }
            editTextDocumentNoFromHeaderFragment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                private SalesActivityNew f0;

                public void onFocusChange(View view, boolean z2) {
                    SalesActivityNew.setEdtDocumentNoOnFocusChangeListenerlambda20(this.f0, editTextDocumentNoFromHeaderFragment, view, z2);
                }
            });
        }
    }

    public static   void setEdtDocumentNoOnFocusChangeListenerlambda20(SalesActivityNew this0, EditText editText, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (z || this0.getmSales() == null) {
            return;
        }
        Sales sales = this0.getmSales();
        Intrinsics.checkNotNull(sales);
        if (!sales.hasCampaignApplied() || Intrinsics.areEqual(this0.documentNumberForAppliedCampagin, editText.getText().toString())) {
            return;
        }
        this0.notifyReApplyCampaign();
    }

    private   String loadValue(String str, @StringRes int... r3) {
        String str2 = str + this.mCustomerRef;
        ISqlBriteDatabase<?> sqlBriteDatabase = this.viewModel.getSqlBriteDatabase();
        Intrinsics.checkNotNull(str2);
        return StringUtils.catStringNewLineCursor(sqlBriteDatabase.query(str2), Arrays.copyOf(r3, r3.length));
    }

    public   Sales getmSales() {
        return this.mSales;
    }

    public   void notifyViewPagerDataSetChanged() {
        Log.d(MobileSales.TAG, "\nnotifyDataSetChanged()");
        SalesPagerAdapter salesPagerAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesPagerAdapter);
        salesPagerAdapter.notifyDataSetChanged();
    }

    public   SalesPagerAdapter getmAdapter() {
        return this.mAdapter;
    }

    public   SalesType getSalesType() {
        SalesType salesType = this.mCustomerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        return salesType;
    }

    public   SalesFicheHeaderFields getmSalesFicheHeaderFields() {
        SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters);
        return salesFicheParameters.getMSalesFicheHeaderFields();
    }

    public   SalesFicheDetailFields getmSalesFicheDetailFields() {
        SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters);
        return salesFicheParameters.getMSalesFicheDetailFields();
    }

    public   SalesFicheUserRights getSalesFicheUserRights() {
        SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters);
        return salesFicheParameters.getMSalesFicheUserRights();
    }

    public void onNetworkConnectionChanged(boolean z) {
        if (z) {
            ContextUtils.setMenuItemOnlineVisible(this.menuOnlineTotal, z);
            ContextUtils.setMenuItemOnlineVisible(this.menuSalesOrderTransfer, z);
            ContextUtils.setMenuItemOnlineVisible(this.menuSalesOrderDetailTransfer, z);
            ContextUtils.setMenuItemOnlineVisible(this.menuSalesDistributionTransfer, z);
            ContextUtils.setMenuItemOnlineVisible(this.menuReports, z);
        }
    }

    public   void onSaveResult(boolean z, String str) {
        if (z) {
            Toast.makeText(this, getString(R.string.str_fiche_save_successful), Toast.LENGTH_LONG).show();
            insertRouteProcess(this.mCustomerOperation);
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            if (sales.getCabin().getLogicalRef() > 0) {
                insertOrUpdateCabinTrans();
            }
            if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(getSalesType())) {
                SalesNewViewModel salesNewViewModel = this.viewModel;
                Sales sales2 = this.mSales;
                Intrinsics.checkNotNull(sales2);
                SalesFicheParameters salesFichePayPlanTypeCash = salesNewViewModel.getSalesFichePayPlanTypeCash(StringUtils.convertIntToString(sales2.getPayPlan().getLogicalRef()));
                if (salesFichePayPlanTypeCash != null) {
                    try {
                        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                        if (ContextUtils.checkConnectionWithoutMessage()) {
                            SalesNewViewModel salesNewViewModel2 = this.viewModel;
                            Sales sales3 = this.mSales;
                            Intrinsics.checkNotNull(sales3);
                            salesNewViewModel2.showOnlineTotal(sales3.clone(), new SalesActivityInvoicePaymentListener(this, salesFichePayPlanTypeCash));
                        } else {
                            onInvoicePayment(this.mSales, salesFichePayPlanTypeCash, "");
                        }
                        return;
                    } catch (CloneNotSupportedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                finish();
                return;
            }
            finish();
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%s : %s", Arrays.copyOf(new Object[]{getString(R.string.str_fiche_save_on_error), str}, 2));
        checkNotNullExpressionValue(str2, "format(...)");
        Toast.makeText(this, str2, Toast.LENGTH_LONG).show();
    }
    public void onHeaderVatChange() {
        SalesPagerAdapter salesPagerAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesPagerAdapter);
        Fragment item = salesPagerAdapter.getItem(1);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.detail.SalesDetailListFragment");
        ((SalesDetailListFragment) item).setTotalLayoutWithNotify();
    }

    private record SalesActivityFicheSaveListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<Boolean> {
            private SalesActivityFicheSaveListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel bool) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    Intrinsics.checkNotNull(bool);
                    salesActivityNew2.onSaveResult(bool.booleanValue(), "");
                }
            }

        public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onSaveResult(false, errorMessage);
                }
            }
        }

    private record SalesActivityCalculateTotalListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<Sales> {
            private SalesActivityCalculateTotalListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel sales) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCalculateSales(sales, "");
                }
            }

        @Override
            public void onFailure(Throwable throwable) {
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCalculateSales(null, errorMessage);
                }
            }
        }
    public void onCalculateSales(Sales sales, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (sales != null) {
            calculateTotals(String.valueOf(sales.getTotal()), String.valueOf(sales.getTotalVat()), String.valueOf(sales.getDiscTotal()), String.valueOf(sales.getTotalNet()));
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private record SalesActivityInvoicePaymentListener(WeakReference<SalesActivityNew> mSalesActivity,
                                                       PaymentLine paymentLine) implements ResponseListener<Sales> {
            private SalesActivityInvoicePaymentListener(SalesActivityNew mSalesActivity, PaymentLine paymentLine) {
                Intrinsics.checkNotNullParameter(paymentLine, "paymentLine");
                this.paymentLine = paymentLine;
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel sales) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onInvoicePayment(sales, this.paymentLine, "");
                }
            }

        @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onInvoicePayment(null, this.paymentLine, errorMessage);
                }
            }
        }
    public void onInvoicePayment(Sales sales, PaymentLine paymentLine, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            return;
        }
        if (paymentLine.getPaymentType() == PaymentLineType.NAKIT.getValue()) {
            openCaseCashFiche(sales);
            return;
        }
        if (paymentLine.getPaymentType() == PaymentLineType.KREDIKARTI.getValue()) {
            openCreditCardFiche(sales, paymentLine);
            return;
        }
        if (paymentLine.getPaymentType() == PaymentLineType.CEK.getValue()) {
            openChequeFiche(sales);
        } else if (paymentLine.getPaymentType() == PaymentLineType.SENET.getValue()) {
            openDeedFiche(sales);
        } else {
            finish();
        }
    }
    private   void openCaseCashFiche(Sales sales) {
        this.mLastReceiptType = ReceiptType.CASE;
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        FicheMode ficheMode2 = FicheMode.EDIT;
        if (ficheMode == ficheMode2) {
            this.mLastFicheMode = ficheMode2;
            SalesNewViewModel salesNewViewModel = this.viewModel;
            ReceiptType receiptType = this.mLastReceiptType;
            Intrinsics.checkNotNull(receiptType);
            Intrinsics.checkNotNull(sales);
            Cursor receiptFicheRefFromInvoiceRef = salesNewViewModel.getReceiptFicheRefFromInvoiceRef(receiptType, sales.getLogicalRef());
            if (receiptFicheRefFromInvoiceRef != null && receiptFicheRefFromInvoiceRef.moveToFirst()) {
                boolean zConvertIntToBoolean = StringUtils.convertIntToBoolean(receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("ISTRANSFER")));
                int r0 = receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("LOGICALREF"));
                if (zConvertIntToBoolean) {
                    Toast.makeText(this, R.string.str_receipt_fiche_transferred, Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                } else {
                    startCaseCashFiche(sales, r0, ficheMode2);
                    return;
                }
            }
            deleteOldReceiptIfExists(sales.getLogicalRef());
            startCaseCashFichedefault(this, sales, 0, null, 6, null);
            return;
        }
        startCaseCashFichedefault(this, sales, 0, null, 6, null);
    }
    static void startCaseCashFichedefault(SalesActivityNew salesActivityNew, Sales sales, int r2, FicheMode ficheMode, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            ficheMode = FicheMode.NEW;
        }
        salesActivityNew.startCaseCashFiche(sales, r2, ficheMode);
    }
    private   void startCaseCashFiche(Sales sales, int r6, FicheMode ficheMode) {
        CustomerOperation.Companion companion = CustomerOperation.Companion;
        CustomerOperation mCustomerOperation = this.mCustomerOperation;
        checkNotNullExpressionValue(mCustomerOperation, "mCustomerOperation");
        CustomerOperation customerOperationBuild = companion.newBuilder(mCustomerOperation).build();
        customerOperationBuild.setReceiptType(ReceiptType.CASE);
        customerOperationBuild.setFicheType(FicheType.CASE_CASH);
        customerOperationBuild.setActivity(CaseFicheActivity.class);
        customerOperationBuild.setOperationName(getString(R.string.str_case_receipt));
        int logicalRef = MainActivity.sFicheRef;
        MainActivity.sFicheRef = -1;
        customerOperationBuild.setFicheMode(ficheMode);
        Intent intent = new Intent(this, customerOperationBuild.getActivity());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperationBuild);
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, r6);
        String str = IntentExtraName.INVOICE_PAYMENTLINE_TOTAL;
        Intrinsics.checkNotNull(sales);
        intent.putExtra(str, sales.getTotalNet());
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT) {
            logicalRef = sales.getLogicalRef();
        }
        intent.putExtra(str2, logicalRef);
        startActivityForResult(intent, IntentExtraName.OPEN_RECEIPT_FICHE_REQUEST_CODE);
    }

    private void openCreditCardFiche(Sales sales, PaymentLine paymentLine) {
        this.mLastReceiptType = ReceiptType.CREDIT;
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        FicheMode ficheMode2 = FicheMode.EDIT;
        if (ficheMode == ficheMode2) {
            this.mLastFicheMode = ficheMode2;
            SalesNewViewModel salesNewViewModel = this.viewModel;
            ReceiptType receiptType = this.mLastReceiptType;
            Intrinsics.checkNotNull(receiptType);
            Intrinsics.checkNotNull(sales);
            Cursor receiptFicheRefFromInvoiceRef = salesNewViewModel.getReceiptFicheRefFromInvoiceRef(receiptType, sales.getLogicalRef());
            if (receiptFicheRefFromInvoiceRef != null && receiptFicheRefFromInvoiceRef.moveToFirst()) {
                boolean zConvertIntToBoolean = StringUtils.convertIntToBoolean(receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("ISTRANSFER")));
                int r1 = receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("LOGICALREF"));
                if (zConvertIntToBoolean) {
                    Toast.makeText(this, R.string.str_receipt_fiche_transferred, Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                } else {
                    startCreditCardFiche(sales, paymentLine, r1, ficheMode2);
                    return;
                }
            }
            deleteOldReceiptIfExists(sales.getLogicalRef());
            startCreditCardFichedefault(this, sales, paymentLine, 0, null, 12, null);
            return;
        }
        startCreditCardFichedefault(this, sales, paymentLine, 0, null, 12, null);
    }

    static void startCreditCardFichedefault(SalesActivityNew salesActivityNew, Sales sales, PaymentLine paymentLine, int r3, FicheMode ficheMode, int r5, Object obj) {
        if ((r5 & 4) != 0) {
            r3 = 0;
        }
        if ((r5 & 8) != 0) {
            ficheMode = FicheMode.NEW;
        }
        salesActivityNew.startCreditCardFiche(sales, paymentLine, r3, ficheMode);
    }

    private void startCreditCardFiche(Sales sales, PaymentLine paymentLine, int r7, FicheMode ficheMode) {
        CustomerOperation.Companion companion = CustomerOperation.Companion;
        CustomerOperation mCustomerOperation = this.mCustomerOperation;
        checkNotNullExpressionValue(mCustomerOperation, "mCustomerOperation");
        CustomerOperation customerOperationBuild = companion.newBuilder(mCustomerOperation).build();
        customerOperationBuild.setReceiptType(ReceiptType.CREDIT);
        customerOperationBuild.setFicheType(FicheType.CREDIT_CART);
        customerOperationBuild.setActivity(CashAndCreditCardFicheActivity.class);
        customerOperationBuild.setOperationName(getString(R.string.str_credit_cart_receipt));
        int logicalRef = MainActivity.sFicheRef;
        MainActivity.sFicheRef = -1;
        customerOperationBuild.setFicheMode(ficheMode);
        Intent intent = new Intent(this, customerOperationBuild.getActivity());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperationBuild);
        String str = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT) {
            Intrinsics.checkNotNull(sales);
            logicalRef = sales.getLogicalRef();
        }
        intent.putExtra(str, logicalRef);
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_TOTAL;
        Intrinsics.checkNotNull(sales);
        intent.putExtra(str2, sales.getTotalNet());
        intent.putExtra(IntentExtraName.INVOICE_PAYMENTLINE_BANKREF, paymentLine.getBankRef());
        intent.putExtra(IntentExtraName.INVOICE_PAYMENTLINE_BANKACCREF, paymentLine.getBankAccRef());
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, r7);
        startActivityForResult(intent, IntentExtraName.OPEN_RECEIPT_FICHE_REQUEST_CODE);
    }

    private void openChequeFiche(Sales sales) {
        this.mLastReceiptType = ReceiptType.CHEQUE;
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        FicheMode ficheMode2 = FicheMode.EDIT;
        if (ficheMode == ficheMode2) {
            this.mLastFicheMode = ficheMode2;
            SalesNewViewModel salesNewViewModel = this.viewModel;
            ReceiptType receiptType = this.mLastReceiptType;
            Intrinsics.checkNotNull(receiptType);
            Intrinsics.checkNotNull(sales);
            Cursor receiptFicheRefFromInvoiceRef = salesNewViewModel.getReceiptFicheRefFromInvoiceRef(receiptType, sales.getLogicalRef());
            if (receiptFicheRefFromInvoiceRef != null && receiptFicheRefFromInvoiceRef.moveToFirst()) {
                boolean zConvertIntToBoolean = StringUtils.convertIntToBoolean(receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("ISTRANSFER")));
                int r0 = receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("LOGICALREF"));
                if (zConvertIntToBoolean) {
                    Toast.makeText(this, R.string.str_receipt_fiche_transferred, Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                } else {
                    startChequeFiche(sales, r0, ficheMode2);
                    return;
                }
            }
            deleteOldReceiptIfExists(sales.getLogicalRef());
            startChequeFichedefault(this, sales, 0, null, 6, null);
            return;
        }
        startChequeFichedefault(this, sales, 0, null, 6, null);
    }

    static void startChequeFichedefault(SalesActivityNew salesActivityNew, Sales sales, int r2, FicheMode ficheMode, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            ficheMode = FicheMode.NEW;
        }
        salesActivityNew.startChequeFiche(sales, r2, ficheMode);
    }

    private void startChequeFiche(Sales sales, int r6, FicheMode ficheMode) {
        CustomerOperation.Companion companion = CustomerOperation.Companion;
        CustomerOperation mCustomerOperation = this.mCustomerOperation;
        checkNotNullExpressionValue(mCustomerOperation, "mCustomerOperation");
        CustomerOperation customerOperationBuild = companion.newBuilder(mCustomerOperation).build();
        customerOperationBuild.setReceiptType(ReceiptType.CHEQUE);
        customerOperationBuild.setFicheType(FicheType.CHEQUE);
        customerOperationBuild.setActivity(ChequeAndDeedFicheActivity.class);
        customerOperationBuild.setOperationName(getString(R.string.str_cheque_receipt));
        int logicalRef = MainActivity.sFicheRef;
        MainActivity.sFicheRef = -1;
        customerOperationBuild.setFicheMode(ficheMode);
        Intent intent = new Intent(this, customerOperationBuild.getActivity());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperationBuild);
        String str = IntentExtraName.INVOICE_PAYMENTLINE_TOTAL;
        Intrinsics.checkNotNull(sales);
        intent.putExtra(str, sales.getTotalNet());
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT) {
            logicalRef = sales.getLogicalRef();
        }
        intent.putExtra(str2, logicalRef);
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, r6);
        startActivityForResult(intent, IntentExtraName.OPEN_RECEIPT_FICHE_REQUEST_CODE);
    }

    private void openDeedFiche(Sales sales) {
        this.mLastReceiptType = ReceiptType.DEED;
        FicheMode ficheMode = this.mCustomerOperation.getFicheMode();
        FicheMode ficheMode2 = FicheMode.EDIT;
        if (ficheMode == ficheMode2) {
            this.mLastFicheMode = ficheMode2;
            SalesNewViewModel salesNewViewModel = this.viewModel;
            ReceiptType receiptType = this.mLastReceiptType;
            Intrinsics.checkNotNull(receiptType);
            Intrinsics.checkNotNull(sales);
            Cursor receiptFicheRefFromInvoiceRef = salesNewViewModel.getReceiptFicheRefFromInvoiceRef(receiptType, sales.getLogicalRef());
            if (receiptFicheRefFromInvoiceRef != null && receiptFicheRefFromInvoiceRef.moveToFirst()) {
                boolean zConvertIntToBoolean = StringUtils.convertIntToBoolean(receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("ISTRANSFER")));
                int r0 = receiptFicheRefFromInvoiceRef.getInt(receiptFicheRefFromInvoiceRef.getColumnIndex("LOGICALREF"));
                if (zConvertIntToBoolean) {
                    Toast.makeText(this, R.string.str_receipt_fiche_transferred, Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                } else {
                    startDeedFiche(sales, r0, ficheMode2);
                    return;
                }
            }
            deleteOldReceiptIfExists(sales.getLogicalRef());
            startDeedFichedefault(this, sales, 0, null, 6, null);
            return;
        }
        startDeedFichedefault(this, sales, 0, null, 6, null);
    }

    static void startDeedFichedefault(SalesActivityNew salesActivityNew, Sales sales, int r2, FicheMode ficheMode, int r4, Object obj) {
        if ((r4 & 2) != 0) {
            r2 = 0;
        }
        if ((r4 & 4) != 0) {
            ficheMode = FicheMode.NEW;
        }
        salesActivityNew.startDeedFiche(sales, r2, ficheMode);
    }

    private void startDeedFiche(Sales sales, int r6, FicheMode ficheMode) {
        CustomerOperation.Companion companion = CustomerOperation.Companion;
        CustomerOperation mCustomerOperation = this.mCustomerOperation;
        checkNotNullExpressionValue(mCustomerOperation, "mCustomerOperation");
        CustomerOperation customerOperationBuild = companion.newBuilder(mCustomerOperation).build();
        customerOperationBuild.setReceiptType(ReceiptType.DEED);
        customerOperationBuild.setFicheType(FicheType.DEED);
        customerOperationBuild.setActivity(ChequeAndDeedFicheActivity.class);
        customerOperationBuild.setOperationName(getString(R.string.str_bill_receipt));
        int logicalRef = MainActivity.sFicheRef;
        MainActivity.sFicheRef = -1;
        customerOperationBuild.setFicheMode(ficheMode);
        Intent intent = new Intent(this, customerOperationBuild.getActivity());
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperationBuild);
        String str = IntentExtraName.INVOICE_PAYMENTLINE_TOTAL;
        Intrinsics.checkNotNull(sales);
        intent.putExtra(str, sales.getTotalNet());
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT) {
            logicalRef = sales.getLogicalRef();
        }
        intent.putExtra(str2, logicalRef);
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, r6);
        startActivityForResult(intent, IntentExtraName.OPEN_RECEIPT_FICHE_REQUEST_CODE);
    }
    public void calculateRisklambda25(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");

        this0.calculateRisk();
    }
    private void calculateRisk() {
    }

    private record NetsisSalesActivityCampaignListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<NetsisServiceResult<?, ?>> {
            private NetsisSalesActivityCampaignListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel netsisServiceResult) throws Resources.NotFoundException {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onNetsisCampaignResult(netsisServiceResult, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) throws Resources.NotFoundException {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onNetsisCampaignResult(null, errorMessage);
                }
            }
        }
    public void onNetsisCampaignResult(NetsisServiceResult<?, ?> netsisServiceResult, String str) throws Resources.NotFoundException {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (netsisServiceResult != null) {
            if (netsisServiceResult.isSuccess()) {
                ViewPager viewPager = this.mViewPager;
                Intrinsics.checkNotNull(viewPager);
                viewPager.setCurrentItem(1);
                SalesPagerAdapter salesPagerAdapter = getmAdapter();
                Intrinsics.checkNotNull(salesPagerAdapter);
                salesPagerAdapter.updateFragments();
            } else {
                Toast.makeText(this, netsisServiceResult.getErrorString(), Toast.LENGTH_SHORT).show();
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private record SalesActivityCampaignListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<TigerServiceResult> {
            private SalesActivityCampaignListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel tigerServiceResult) throws Resources.NotFoundException {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCampaignResult(tigerServiceResult, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String errorMessage) throws Resources.NotFoundException {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onCampaignResult(null, errorMessage);
                }
            }
        }
    public void onCampaignResult(TigerServiceResult tigerServiceResult, String str) throws Resources.NotFoundException {
        String definition;
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (tigerServiceResult != null) {
            if (tigerServiceResult.isSuccess()) {
                try {
                    new TigerXmlParserForUpdate().parseXml(tigerServiceResult);
                    if (tigerServiceResult.getData() != null) {
                        Object data = tigerServiceResult.getData();
                        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
                        Sales sales = (Sales) data;
                        if (sales.getMSalesDetailList() != null && sales.getmSalesType() == SalesType.ORDER) {
                            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
                            Intrinsics.checkNotNull(mSalesDetailList);
                            Iterator<SalesDetail> it = mSalesDetailList.iterator();
                            while (it.hasNext()) {
                                SalesDetail next = it.next();
                                if (next.getLineType() == LineType.PROMOTION.value && getSalesFicheUserRights().isReserve()) {
                                    next.setReserve(new FicheBooleanProp(true));
                                }
                            }
                        }
                    }
                } catch (Exception e2) {
                    String message = e2.getMessage();
                    Intrinsics.checkNotNull(message);
                    Log.e("TAG", message);
                }
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                if (sales2.hasCampaignApplied()) {
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    definition = sales3.getDocumentNo().getDefinition();
                    Intrinsics.checkNotNull(definition);
                } else {
                    definition = "";
                }
                this.documentNumberForAppliedCampagin = definition;
                ViewPager viewPager = this.mViewPager;
                Intrinsics.checkNotNull(viewPager);
                viewPager.setCurrentItem(1);
                SalesPagerAdapter salesPagerAdapter = getmAdapter();
                Intrinsics.checkNotNull(salesPagerAdapter);
                salesPagerAdapter.updateFragments();
            } else {
                Toast.makeText(this, tigerServiceResult.getErrorString(), Toast.LENGTH_SHORT).show();
            }
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int r3, int r4, Intent intent) {
        super.onActivityResult(r3, r4, intent);
        if (r3 == 1071) {
            if (r4 == -1) {
                initSalesCopy();
                Sales sales = this.mSales;
                Intrinsics.checkNotNull(sales);
                int discountLength = sales.getDiscountLength();
                for (int r42 = 0; r42 < discountLength; r42++) {
                    Sales sales2 = this.mSales;
                    Intrinsics.checkNotNull(sales2);
                    sales2.getDiscountTotal(r42).setGuid("");
                }
                this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                if (this.mIsDetailOrder) {
                    SalesNewViewModel salesNewViewModel = this.viewModel;
                    Intrinsics.checkNotNull(intent);
                    salesNewViewModel.getOrderAvailableAmountsFromDetailWithRefs(intent.getStringArrayListExtra(IntentExtraName.EXTRA_FICHE_ID), new OrderAvailableAmountListener(this));
                    return;
                } else {
                    SalesNewViewModel salesNewViewModel2 = this.viewModel;
                    Intrinsics.checkNotNull(intent);
                    salesNewViewModel2.getOrderAvailableAmounts(intent.getStringArrayListExtra(IntentExtraName.EXTRA_FICHE_ID), new OrderAvailableAmountListener(this));
                    return;
                }
            }
            return;
        }
        if (r3 == 1072) {
            if (r4 == -1) {
                Intrinsics.checkNotNull(intent);
                Bundle extras = intent.getExtras();
                Intrinsics.checkNotNull(extras);
                distributionToFiche(extras.getParcelableArrayList(IntentExtraName.EXTRA_DISTRIBUTION_FICHE_ID));
                return;
            }
            return;
        }
        if (r3 == 1235) {
            finish();
            return;
        }
        if (r3 == 1999) {
            if (r4 == -1) {
                Intrinsics.checkNotNull(intent);
                EDispatchAdditionalInfo eDispatchAdditionalInfo = intent.getParcelableExtra(IntentExtraName.EXTRA_EDESPATCH_EXTRA_INFO);
                if (eDispatchAdditionalInfo != null) {
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    sales3.setEDispatchAdditionalInfo(eDispatchAdditionalInfo);
                    return;
                }
                return;
            }
            return;
        }
        if (r3 == 7879 && intent != null) {
            int flags = intent.getFlags() & 3;
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            ContentResolver contentResolver = context.getContentResolver();
            Uri data = intent.getData();
            Intrinsics.checkNotNull(data);
            contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            PdfHelper pdfHelper = this.pdfHelper;
            PdfHelper pdfHelper2 = null;
            if (pdfHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pdfHelper");
                pdfHelper = null;
            }
            SharedPreferencesHelper mSharedPreferencesHelper = pdfHelper.getMSharedPreferencesHelper();
            Intrinsics.checkNotNull(mSharedPreferencesHelper);
            Uri data2 = intent.getData();
            Intrinsics.checkNotNull(data2);
            mSharedPreferencesHelper.saveSalesPdfPath(data2);
            PdfHelper pdfHelper3 = this.pdfHelper;
            if (pdfHelper3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pdfHelper");
            } else {
                pdfHelper2 = pdfHelper3;
            }
            pdfHelper2.copyPdfFromCacheToSelectedFolder(intent.getData());
        }
    }

    private void distributionToFiche(ArrayList<Distribution> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        initSalesCopy();
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        int discountLength = sales.getDiscountLength();
        for (int r1 = 0; r1 < discountLength; r1++) {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            sales2.getDiscountTotal(r1).setGuid("");
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<Distribution> it = arrayList.iterator();
        while (it.hasNext()) {
            Distribution next = it.next();
            OrderAvailableAmount orderAvailableAmount = new OrderAvailableAmount();
            orderAvailableAmount.setOrderAmount(next.getShipAmount());
            orderAvailableAmount.setOrderLineLogicalRef(next.getOrdLineRef());
            orderAvailableAmount.setOrderLogicalRef(next.getOrdFicheRef());
            orderAvailableAmount.setAvailableAmount(next.getReAmount());
            orderAvailableAmount.setDitributionLineRef(next.getLogicalRef());
            orderAvailableAmount.setDitributionRef(next.distorderRef);
            arrayList2.add(orderAvailableAmount);
        }
        readSalesOrderFiche(arrayList2);
        getIntent().putExtra(IntentExtraName.EXTRA_DISTRIBUTION_FICHE_ID, new ArrayList());
    }

    public void setData(Sales sales) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            private SalesActivityNew f0;

            @Override
            public void run() {
                SalesActivityNew.setDatalambda21(this.f0);
            }
        });
    }

    @SuppressLint("WrongConstant")
    public static void setDatalambda21(SalesActivityNew this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intent intent = this0.getIntent();
        try {
            intent.putExtra("bigdata:synccode", this0.viewModel.getSaveObjectWithSales(this0.mSales));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this0.mCustomerOperation.setFicheMode(FicheMode.COPY);
        intent.putExtra(EXTRA_CUSTOMER_OPERATION, this0.mCustomerOperation);
        intent.addFlags(335609856);
        this0.overridePendingTransition(0, 0);
        this0.finish();
        this0.overridePendingTransition(0, 0);
        this0.startActivity(intent);
    }

    private record SalesActivityOrderFicheListener(
            WeakReference<SalesActivityNew> mContent) implements ResponseListener<Sales> {
            private SalesActivityOrderFicheListener(SalesActivityNew mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }

            public void onResponse(PrintSlipModel sales) {
                boolean z = false;
                if (this.mContent.get() != null) {
                    SalesActivityNew salesActivityNew = this.mContent.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    salesActivityNew.dismisprogress();
                    Intrinsics.checkNotNull(sales);
                    SalesActivityNew salesActivityNew2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    if (salesActivityNew2.getSalesType() == SalesType.DISPATCH) {
                        SalesActivityNew salesActivityNew3 = this.mContent.get();
                        Intrinsics.checkNotNull(salesActivityNew3);
                        if (salesActivityNew3.viewModel.erpType() == ErpType.NETSIS) {
                            SalesActivityNew salesActivityNew4 = this.mContent.get();
                            Intrinsics.checkNotNull(salesActivityNew4);
                            try {
                                z = salesActivityNew4.viewModel.getFirmUseEDespatch();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    sales.setEDespatch(new FicheBooleanProp(z));
                    SalesActivityNew salesActivityNew5 = this.mContent.get();
                    Intrinsics.checkNotNull(salesActivityNew5);
                    salesActivityNew5.setData(sales);
                    ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
                    Intrinsics.checkNotNull(mSalesDetailList);
                    Iterator<SalesDetail> it = mSalesDetailList.iterator();
                    while (it.hasNext()) {
                        SalesDetail next = it.next();
                        if (next.getBarcodeCount() == 0) {
                            next.setBarcodeCount(1);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mContent.get() != null) {
                    SalesActivityNew salesActivityNew = this.mContent.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    salesActivityNew.dismisprogress();
                    Log.d("AA", "onError: " + errorMessage);
                    SalesActivityNew salesActivityNew2 = this.mContent.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.showMessage(errorMessage);
                }
            }
        }

    public void readSalesOrderFiche(List<? extends OrderAvailableAmount> list) {
        if (list == null || list.isEmpty()) {
            this.mProgressDialogBuilder.dismiss();
            return;
        }
        HashSet hashSet = new HashSet();
        for (OrderAvailableAmount orderAvailableAmount : list) {
            ErpType erpType = this.viewModel.erpType();
            ErpType erpType2 = ErpType.NETSIS;
            Intrinsics.checkNotNull(orderAvailableAmount);
            String code = erpType == erpType2 ? orderAvailableAmount.getCode() : String.valueOf(orderAvailableAmount.getOrderLogicalRef());
            Intrinsics.checkNotNull(code);
            hashSet.add(code);
        }
        SalesNewViewModel salesNewViewModel = this.viewModel;
        ArrayList<?> arrayList = new ArrayList<>(hashSet);
        DataObjectType dataObjectType = DataObjectType.ADDORDER;
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        salesNewViewModel.readOrderFiche(arrayList, dataObjectType, sales, list, new SalesActivityOrderFicheListener(this));
    }
    static final class OrderAvailableAmountListener implements ResponseListener<List<? extends OrderAvailableAmount>> {
        private final WeakReference<SalesActivityNew> mContent;

        public OrderAvailableAmountListener(SalesActivityNew mContent) {
            Intrinsics.checkNotNullParameter(mContent, "mContent");
            this.mContent = new WeakReference<>(mContent);
        }
        public void onResponse(PrintSlipModel list) {
            if (this.mContent.get() != null) {
                SalesActivityNew salesActivityNew = this.mContent.get();
                Intrinsics.checkNotNull(salesActivityNew);
                salesActivityNew.readSalesOrderFiche(list);
            }
        }

        public void onFailure(Throwable throwable) {

        }
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            if (this.mContent.get() != null) {
                SalesActivityNew salesActivityNew = this.mContent.get();
                Intrinsics.checkNotNull(salesActivityNew);
                salesActivityNew.dismisprogress();
                Log.d("AA", "onError: " + errorMessage);
                SalesActivityNew salesActivityNew2 = this.mContent.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.showMessage(errorMessage);
            }
        }
    }

    static final class SetOrderAvailableAmountListener implements ResponseListener<List<? extends OrderAvailableAmount>> {
        private final WeakReference<SalesActivityNew> mContent;

        public SetOrderAvailableAmountListener(SalesActivityNew mContent) {
            Intrinsics.checkNotNullParameter(mContent, "mContent");
            this.mContent = new WeakReference<>(mContent);
        }

        public void onResponse(PrintSlipModel list) {
            if (this.mContent.get() == null || list == null || list.isEmpty()) {
                return;
            }
            SalesActivityNew salesActivityNew = this.mContent.get();
            Intrinsics.checkNotNull(salesActivityNew);
            Sales sales = salesActivityNew.mSales;
            Intrinsics.checkNotNull(sales);
            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            Iterator<SalesDetail> it = mSalesDetailList.iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                if (next.getOrderDetailReference() != 0) {
                    for (OrderAvailableAmount orderAvailableAmount : list) {
                        if (orderAvailableAmount.getOrderLineLogicalRef() == next.getOrderDetailReference()) {
                            next.setOrderAmount(orderAvailableAmount.getOrderAmount());
                            next.setOrderAvailableAmount(orderAvailableAmount.getAvailableAmount());
                        }
                    }
                }
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            if (this.mContent.get() != null) {
                SalesActivityNew salesActivityNew = this.mContent.get();
                Intrinsics.checkNotNull(salesActivityNew);
                salesActivityNew.dismisprogress();
                Log.d("AA", "onError: " + errorMessage);
                SalesActivityNew salesActivityNew2 = this.mContent.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.showMessage(errorMessage);
            }
        }
    }

    public void showMessage(String str) {
        dismisprogress();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void dismisprogress() {
        this.mProgressDialogBuilder.dismiss();
    }

    private void controlRisk() {
        this.mProgressDialogBuilder.dismiss();
        CustomerRiskInfoUtils customerRiskInfoUtils = CustomerRiskInfoUtils.INSTANCE;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        String clCode = sales.getClCode();
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        List<ClRisk> clRiskList = customerRiskInfoUtils.getClRiskList(clCode, sales2.getClRef());
        if (!clRiskList.isEmpty()) {
            if (this.viewModel.erpType() == ErpType.TIGER && clRiskList.get(0).getRiskType() == RiskControlType.BASED_ON_FICHE_TYPE) {
                if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(getSalesType())) {
                    double accRiskLimit = clRiskList.get(0).getAccRiskLimit();
                    double accRiskClosed = clRiskList.get(0).getAccRiskClosed();
                    double accRiskTotal = clRiskList.get(0).getAccRiskTotal();
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    int logicalRef = sales3.getLogicalRef();
                    SalesType salesType = getSalesType();
                    InvoiceStatus salesStatus = this.viewModel.getBaseErp().getSalesStatus(getSalesType());
                    checkNotNullExpressionValue(salesStatus, "getSalesStatus(...)");
                    calculateRisk(accRiskLimit, accRiskClosed, accRiskTotal, getNotTransferredSalesTotal(logicalRef, getTotalNet(salesType, salesStatus)));
                    return;
                }
                if (SalesUtils.isSalesTypeDispatch(getSalesType())) {
                    if (this.viewModel.getBaseErp().getSalesStatus(getSalesType()) == InvoiceStatus.OFFER) {
                        double despRiskLimitSug = clRiskList.get(0).getDespRiskLimitSug();
                        double despRiskClosedSug = clRiskList.get(0).getDespRiskClosedSug();
                        double despRiskTotalSug = clRiskList.get(0).getDespRiskTotalSug();
                        Sales sales4 = getmSales();
                        Intrinsics.checkNotNull(sales4);
                        int logicalRef2 = sales4.getLogicalRef();
                        SalesType salesType2 = getSalesType();
                        InvoiceStatus salesStatus2 = this.viewModel.getBaseErp().getSalesStatus(getSalesType());
                        checkNotNullExpressionValue(salesStatus2, "getSalesStatus(...)");
                        calculateRisk(despRiskLimitSug, despRiskClosedSug, despRiskTotalSug, getNotTransferredSalesTotal(logicalRef2, getTotalNet(salesType2, salesStatus2)));
                        return;
                    }
                    double despRiskLimit = clRiskList.get(0).getDespRiskLimit();
                    double despRiskClosed = clRiskList.get(0).getDespRiskClosed();
                    double despRiskTotal = clRiskList.get(0).getDespRiskTotal();
                    Sales sales5 = getmSales();
                    Intrinsics.checkNotNull(sales5);
                    int logicalRef3 = sales5.getLogicalRef();
                    SalesType salesType3 = getSalesType();
                    InvoiceStatus salesStatus3 = this.viewModel.getBaseErp().getSalesStatus(getSalesType());
                    checkNotNullExpressionValue(salesStatus3, "getSalesStatus(...)");
                    calculateRisk(despRiskLimit, despRiskClosed, despRiskTotal, getNotTransferredSalesTotal(logicalRef3, getTotalNet(salesType3, salesStatus3)));
                    return;
                }
                if (SalesUtils.isSalesTypeOrder(getSalesType())) {
                    if (this.viewModel.getBaseErp().getSalesStatus(getSalesType()) == InvoiceStatus.OFFER) {
                        double ordRiskLimitSug = clRiskList.get(0).getOrdRiskLimitSug();
                        double ordRiskClosedSug = clRiskList.get(0).getOrdRiskClosedSug();
                        double ordRiskTotalSug = clRiskList.get(0).getOrdRiskTotalSug();
                        Sales sales6 = getmSales();
                        Intrinsics.checkNotNull(sales6);
                        int logicalRef4 = sales6.getLogicalRef();
                        SalesType salesType4 = getSalesType();
                        InvoiceStatus salesStatus4 = this.viewModel.getBaseErp().getSalesStatus(getSalesType());
                        checkNotNullExpressionValue(salesStatus4, "getSalesStatus(...)");
                        calculateRisk(ordRiskLimitSug, ordRiskClosedSug, ordRiskTotalSug, getNotTransferredOrderTotal(logicalRef4, getTotalNet(salesType4, salesStatus4)));
                        return;
                    }
                    double ordRiskLimit = clRiskList.get(0).getOrdRiskLimit();
                    double ordRiskClosed = clRiskList.get(0).getOrdRiskClosed();
                    double ordRiskTotal = clRiskList.get(0).getOrdRiskTotal();
                    Sales sales7 = getmSales();
                    Intrinsics.checkNotNull(sales7);
                    int logicalRef5 = sales7.getLogicalRef();
                    SalesType salesType5 = getSalesType();
                    InvoiceStatus salesStatus5 = this.viewModel.getBaseErp().getSalesStatus(getSalesType());
                    checkNotNullExpressionValue(salesStatus5, "getSalesStatus(...)");
                    calculateRisk(ordRiskLimit, ordRiskClosed, ordRiskTotal, getNotTransferredOrderTotal(logicalRef5, getTotalNet(salesType5, salesStatus5)));
                    return;
                }
                return;
            }
            double riskLimit = clRiskList.get(0).getRiskLimit();
            double riskClosed = clRiskList.get(0).getRiskClosed();
            double riskTotal = clRiskList.get(0).getRiskTotal();
            Sales sales8 = getmSales();
            Intrinsics.checkNotNull(sales8);
            calculateRisk(riskLimit, riskClosed, riskTotal, getNotTransferredFichesTotalNet(sales8.getLogicalRef()));
            return;
        }
        saveFicheWithMatterNoControl();
    }

    private void calculateRisk(double d2, double d3, double d4, double d5) {
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        double dCustomerOverRiskAmount = CalculateUtils.customerOverRiskAmount(d2, d3, d4, sales.getTotalNet(), d5);
        Sales sales2 = getmSales();
        Intrinsics.checkNotNull(sales2);
        double dCustomerOverRiskAmount2 = CalculateUtils.customerOverRiskAmount(dCustomerOverRiskAmount, sales2.getTotalNet());
        Sales sales3 = getmSales();
        Intrinsics.checkNotNull(sales3);
        if (CalculateUtils.customerOverRisk(d2, d3, d4, sales3.getTotalNet(), d5)) {
            RiskAlert riskAlert = this.mRiskAlert;
            if (riskAlert == RiskAlert.UNDEFINED) {
                saveFicheWithMatterNoControl();
                return;
            }
            RiskAlert riskAlert2 = RiskAlert.NOTIFY;
            int r10 = R.string.exp_53_risk_over_netsis_error;
            if (riskAlert == riskAlert2) {
                AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderNotify;
                Intrinsics.checkNotNull(alertDialogBuilder);
                AlertDialogBuilder title = alertDialogBuilder.setTitle(R.string.str_risk_over_title);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                if (this.viewModel.erpType() == ErpType.TIGER) {
                    r10 = R.string.exp_43_risk_over_error;
                }
                String str = String.format("%s %s", Arrays.copyOf(new Object[]{getString(r10, StringUtils.formatDouble(dCustomerOverRiskAmount), StringUtils.formatDouble(dCustomerOverRiskAmount2)), getString(R.string.str_question_save_fiche)}, 2));
                checkNotNullExpressionValue(str, "format(...)");
                title.setMessage(str).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() {
                    public   void onClick(DialogInterface dialogInterface, int r2) {
                        SalesActivityNew.calculateRisklambda22(dialogInterface, r2);
                    }
                }).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
                    private SalesActivityNew f0;

                    public   void onClick(DialogInterface dialogInterface, int r2) {
                        SalesActivityNew.calculateRisklambda23(this.f0, dialogInterface, r2);
                    }
                }).create().show();
                return;
            }
            if (riskAlert == RiskAlert.ABORT) {
                AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilderAlert;
                Intrinsics.checkNotNull(alertDialogBuilder2);
                AlertDialogBuilder title2 = alertDialogBuilder2.setTitle(R.string.str_risk_over_title);
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                if (this.viewModel.erpType() == ErpType.TIGER) {
                    r10 = R.string.exp_43_risk_over_error;
                }
                String str2 = String.format("%s %s", Arrays.copyOf(new Object[]{getString(r10, StringUtils.formatDouble(dCustomerOverRiskAmount), StringUtils.formatDouble(dCustomerOverRiskAmount2)), getString(R.string.str_question_cancel_fiche)}, 2));
                checkNotNullExpressionValue(str2, "format(...)");
                title2.setMessage(str2).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
                    private SalesActivityNew f0;

                    public   void onClick(DialogInterface dialogInterface, int r2) {
                        SalesActivityNew.calculateRisklambda24(this.f0, dialogInterface, r2);
                    }
                }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() {
                    public   void onClick(DialogInterface dialogInterface, int r2) {
                        SalesActivityNew.calculateRisklambda25(dialogInterface, r2);
                    }
                }).create().show();
                return;
            }
            return;
        }
        saveFicheWithMatterNoControl();
    }
    public static void calculateRisklambda22(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
    public static void calculateRisklambda23(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        this0.saveFicheWithMatterNoControl();
    }
    public static void calculateRisklambda24(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        this0.onBackPressed();
        dialog.dismiss();
    }

    public static   void calculateRisklambda25(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private   List<Invoice> getNotTransferredSales() {
        List<Invoice> table = this.baseErp.getLogoSqlHelper().getTable(Invoice.class, "ISTRANSFER=0 AND CLREF=?", new String[]{String.valueOf(this.mCustomerRef)});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
        return table;
    }
    private   double getNotTransferredSalesTotal(int r4, double d2) {
        List<Invoice> notTransferredSales = getNotTransferredSales();
        ArrayList arrayList = new ArrayList();
        for (Object obj : notTransferredSales) {
            if (((Invoice) obj).logicalRef == r4) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d2 -= ((Invoice) it.next()).getTotalNet();
            }
        }
        return d2;
    }

    private   List<Order> getNotTransferredOrders() {
        List<Order> table = this.baseErp.getLogoSqlHelper().getTable(Order.class, "ISTRANSFER=0 AND CLREF=?", new String[]{String.valueOf(this.mCustomerRef)});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
        return table;
    }

    private   double getNotTransferredOrderTotal(int r4, double d2) {
        List<Order> notTransferredOrders = getNotTransferredOrders();
        ArrayList arrayList = new ArrayList();
        for (Object obj : notTransferredOrders) {
            if (((Order) obj).logicalRef == r4) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d2 -= ((Order) it.next()).getTotalNet();
            }
        }
        return d2;
    }

    private   double getNotTransferredFichesTotalNet(int r13) {
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        String str = erpType == erpType2 ? "CLCODE" : "CLREF";
        String clCode = this.viewModel.erpType() == erpType2 ? this.viewModel.getBaseErp().getLogoSqlHelper().getClCode(this.mCustomerRef) : String.valueOf(this.mCustomerRef);
        List table = this.baseErp.getLogoSqlHelper().getTable(Order.class, "ISTRANSFER=0 AND " + str + "=?", new String[]{clCode});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
        Iterator it = table.iterator();
        double totalNet = 0.0d;
        double totalNet2 = 0.0d;
        while (it.hasNext()) {
            totalNet2 += ((Order) it.next()).getTotalNet();
        }
        List table2 = this.baseErp.getLogoSqlHelper().getTable(Invoice.class, "ISTRANSFER=0 AND " + str + "=?", new String[]{clCode});
        Intrinsics.checkNotNull(table2, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
        Iterator it2 = table2.iterator();
        while (it2.hasNext()) {
            totalNet += ((Invoice) it2.next()).getTotalNet();
        }
        if (SalesUtils.isSalesTypeOrder(getSalesType())) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : table) {
                if (((Order) obj).logicalRef == r13) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    totalNet2 -= ((Order) it3.next()).getTotalNet();
                }
            }
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : table2) {
                if (((Invoice) obj2).logicalRef == r13) {
                    arrayList2.add(obj2);
                }
            }
            if (!arrayList2.isEmpty()) {
                Iterator it4 = arrayList2.iterator();
                while (it4.hasNext()) {
                    totalNet -= ((Invoice) it4.next()).getTotalNet();
                }
            }
        }
        return totalNet + totalNet2;
    }
    private  double getTotalNet(SalesType salesType, InvoiceStatus invoiceStatus) {
        Cursor cursorQuery;
        try {
            String str = "";
            if (SalesUtils.isSalesTypeOrder(salesType)) {
                cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(R.string.get_not_transferred_order_total, String.valueOf(this.mCustomerRef), String.valueOf(invoiceStatus.getStatus())));
            } else {
                if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(salesType)) {
                    str = " AND FTYPE IN (" + CollectionsKt.joinToString(CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(FTypes.INVOICE.getmFType()), Integer.valueOf(FTypes.RETAIL_INVOICE.getmFType())}), ",", null, null, 0, null, null) + ") ";
                } else if (SalesUtils.isSalesTypeDispatch(salesType)) {
                    str = (" AND FTYPE = " + FTypes.DISPATCH.getmFType()) + " AND FICHESTATUS = " + invoiceStatus.getStatus();
                }
                cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(R.string.get_not_transferred_sales_total, String.valueOf(this.mCustomerRef), str));
            }
            if (cursorQuery != null && cursorQuery.moveToFirst())
                return cursorQuery.getDouble(cursorQuery.getColumnIndex(getString(R.string.column_total)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return 0.0d;
    }

    private record SalesActivityMatterListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<String> {
            private SalesActivityMatterListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel str) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onMatterResult(str, "");
                }
            }

        public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onMatterResult(null, errorMessage);
                }
            }
        }
    public void onMatterResult(String str, String str2) {
        this.mProgressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            if (maxMatterNoControl(str)) {
                MatterSettings matterSettings = this.mMatterSettings;
                Intrinsics.checkNotNull(matterSettings);
                if (matterSettings.getMatterArea() == MatterArea.FICHE_NO) {
                    Sales sales = getmSales();
                    Intrinsics.checkNotNull(sales);
                    sales.setFicheNo(str);
                } else {
                    Sales sales2 = getmSales();
                    Intrinsics.checkNotNull(sales2);
                    FicheStringProp.setDefinition(str);
                }
                saveFicheWithDocNoControl();
            } else {
                setNewMaxMatterNo(str);
            }
        } else {
            saveFicheWithDocNoControl();
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Toast.makeText(this, str2, Toast.LENGTH_LONG).show();
        saveFicheWithDocNoControl();
    }

    private boolean maxMatterNoControl(String str) {
        MatterSettings matterSettings = this.mMatterSettings;
        Intrinsics.checkNotNull(matterSettings);
        return AppUtils.maxMatterNoControl(str, matterSettings.getLastMatterNo());
    }
    public void setNewMaxMatterNo(final String str) {
        ContextUtils.showMatterDialog(this, getString(R.string.str_matter_input_last_value_title), this.mMatterSettings, new ResponseListener<Object>() {
            public void onResponse(PrintSlipModel obj) {
                String strValueOf = String.valueOf(obj);
                if (!TextUtils.isEmpty(strValueOf)) {
                    SalesNewViewModel salesNewViewModel = SalesActivityNew.this.viewModel;
                    Context applicationContext = SalesActivityNew.this.getApplicationContext();
                    checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    FicheType ficheType = SalesActivityNew.this.getmCustomerOperation().getFicheType();
                    Intrinsics.checkNotNull(ficheType);
                    salesNewViewModel.setNewMatter(applicationContext, ficheType, strValueOf);
                    MatterSettings matterSettings = SalesActivityNew.this.mMatterSettings;
                    Intrinsics.checkNotNull(matterSettings);
                    matterSettings.setLastMatterNo(strValueOf);
                    SalesActivityNew.this.saveFicheWithMatterNoControl();
                    return;
                }
                SalesActivityNew.this.setNewMaxMatterNo(str);
            }
            @Override
            public void onFailure(Throwable throwable) {

            }
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                SalesActivityNew.this.cancelFiche();
            }
        });
    }
    static final class SalesActivityDocNoListener implements ResponseListener<List<? extends DocNo>> {
        private final WeakReference<SalesActivityNew> mSalesActivity;

        public SalesActivityDocNoListener(SalesActivityNew salesActivityNew) {
            this.mSalesActivity = new WeakReference<>(salesActivityNew);
        }
        public void onResponse(PrintSlipModel list) {
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onDocNoResult(list, "");
            }
        }
        @Override
        public void onFailure(Throwable throwable) {

        }
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onDocNoResult(null, errorMessage);
            }
        }
    }
    public void onDocNoResult(List<? extends DocNo> list, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (list != null && !list.isEmpty()) {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            Toast.makeText(this, getString(R.string.exp_46_doc_no_unique, sales.getDocumentNo()), Toast.LENGTH_LONG).show();
        } else {
            saveFiche();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        saveFiche();
    }

    @Subscribe
    public void onPriceChangeEvent(PriceChangeEvent priceChangeEvent) {
        Intrinsics.checkNotNullParameter(priceChangeEvent, "priceChangeEvent");
        Log.d(TAG, "onPriceChangeEvent: " + priceChangeEvent.isChange());
        if (priceChangeEvent.isChange()) {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            if (mSalesDetailList.size() > 0) {
                startSetProductPrice(false);
            }
        }
    }

    public void startSetProductPrice(int r7) {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogPrice;
            Intrinsics.checkNotNull(progressDialogBuilder);
            int r1 = 0;
            progressDialogBuilder.setCancelable(false).show();
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            if (mSalesDetailList.size() == 0) {
                ProgressDialogBuilder<?> progressDialogBuilder2 = this.mProgressDialogPrice;
                Intrinsics.checkNotNull(progressDialogBuilder2);
                progressDialogBuilder2.dismiss();
                return;
            }
            ArrayList arrayList = new ArrayList();
            while (r1 < r7) {
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                ArrayList<SalesDetail> mSalesDetailList2 = sales2.getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList2);
                r1++;
                int size = mSalesDetailList2.size() - r1;
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                ArrayList<SalesDetail> mSalesDetailList3 = sales3.getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList3);
                Sales sales4 = getmSales();
                Intrinsics.checkNotNull(sales4);
                Intrinsics.checkNotNull(sales4.getMSalesDetailList());
                SalesDetail salesDetail = null;
                checkNotNullExpressionValue(salesDetail, "get(...)");
                SalesDetail salesDetail2 = salesDetail;
                if (!salesDetail2.getPromotion().isSelect() || this.viewModel.isPromotionItemPriceEnabled()) {
                    if (this.viewModel.erpType() != ErpType.TIGER || !salesDetail2.isHasVariant() || salesDetail2.getVariant().getLogicalRef() != -1) {
                        arrayList.add(Integer.valueOf(size));
                    }
                }
            }
            if (arrayList.size() == 0) {
                ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogPrice;
                Intrinsics.checkNotNull(progressDialogBuilder3);
                progressDialogBuilder3.dismiss();
            } else {
                SalesNewViewModel salesNewViewModel = this.viewModel;
                Sales sales5 = getmSales();
                Intrinsics.checkNotNull(sales5);
                salesNewViewModel.getSalesProductLinesPrice(sales5, arrayList, new SalesDetailListSetPriceFicheListener(this));
            }
        }
    }

    public void startSetProductPrice(boolean z) {
        WcfPriceType wcfPriceType;
        if (ContextUtils.checkConnectionWithoutMessage()) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogPrice;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.show();
            if (z) {
                Sales sales = getmSales();
                Intrinsics.checkNotNull(sales);
                ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList);
                if (mSalesDetailList.size() == 0) {
                    ProgressDialogBuilder<?> progressDialogBuilder2 = this.mProgressDialogPrice;
                    Intrinsics.checkNotNull(progressDialogBuilder2);
                    progressDialogBuilder2.dismiss();
                    return;
                }
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                ArrayList<SalesDetail> mSalesDetailList2 = sales2.getMSalesDetailList();
                Intrinsics.checkNotNull(mSalesDetailList2);
                Sales sales3 = getmSales();
                Intrinsics.checkNotNull(sales3);
                Intrinsics.checkNotNull(sales3.getMSalesDetailList());
                Buffer r0 = null;
                SalesDetail salesDetail = mSalesDetailList2.get((int) (r0.size() - 1));
                checkNotNullExpressionValue(salesDetail, "get(...)");
                SalesDetail salesDetail2 = salesDetail;
                if (salesDetail2.getPromotion().isSelect() && !this.viewModel.isPromotionItemPriceEnabled()) {
                    return;
                }
                if (this.viewModel.erpType() == ErpType.TIGER && salesDetail2.isHasVariant() && salesDetail2.getVariant().getLogicalRef() == -1) {
                    ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogPrice;
                    Intrinsics.checkNotNull(progressDialogBuilder3);
                    progressDialogBuilder3.dismiss();
                    return;
                }
                String definition = salesDetail2.getSelectedPriceType().getDefinition();
                if (Intrinsics.areEqual(definition, getString(CustomerPriceType.GET_ALL_CUSTOMER_LAST_SELL_PRICE.getmResourceId()))) {
                    wcfPriceType = WcfPriceType.LAST_SALES_ALL_CUSTOMER;
                } else if (Intrinsics.areEqual(definition, getString(CustomerPriceType.GET_CUSTOMER_LAST_SELL_PRICE.getmResourceId()))) {
                    wcfPriceType = WcfPriceType.LAST_SALES_CUSTOMER;
                } else if (Intrinsics.areEqual(definition, getString(CustomerPriceType.GET_ALL_CUSTOMER_LAST_BUY_PRICE.getmResourceId()))) {
                    wcfPriceType = WcfPriceType.LAST_PURCHASE_ALL_CUSTOMER;
                } else if (Intrinsics.areEqual(definition, getString(CustomerPriceType.GET_CUSTOMER_LAST_BUY_PRICE.getmResourceId()))) {
                    wcfPriceType = WcfPriceType.LAST_PURCHASE_CUSTOMER;
                } else {
                    wcfPriceType = WcfPriceType.DEFINE_SALES_PRICE;
                }
                SalesNewViewModel salesNewViewModel = this.viewModel;
                Sales sales4 = getmSales();
                Intrinsics.checkNotNull(sales4);
                Sales sales5 = getmSales();
                Intrinsics.checkNotNull(sales5);
                Intrinsics.checkNotNull(sales5.getMSalesDetailList());
                Buffer r2 = null;
                salesNewViewModel.getSalesProductLinePrice(sales4, (int) (r2.size() - 1), wcfPriceType, new SalesDetailListSetPriceFicheListener(this));
                return;
            }
            SalesNewViewModel salesNewViewModel2 = this.viewModel;
            Sales sales6 = getmSales();
            Intrinsics.checkNotNull(sales6);
            salesNewViewModel2.getSalesAllProductLinePrice(sales6, WcfPriceType.DEFINE_SALES_PRICE, new SalesDetailListSetPriceFicheListener(this));
        }
    }
    public void onAllProductPriceSetResult(List<? extends ResultPrice> list, String str) {
        if (list == null) {
            ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogPrice;
            Intrinsics.checkNotNull(progressDialogBuilder);
            progressDialogBuilder.dismiss();
            FicheTypeControlUtils.isFicheTypeDemand(this.mCustomerOperation.getFicheType());
        } else {
            setResultXml(list);
            ProgressDialogBuilder<?> progressDialogBuilder2 = this.mProgressDialogPrice;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.dismiss();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ProgressDialogBuilder<?> progressDialogBuilder3 = this.mProgressDialogPrice;
        Intrinsics.checkNotNull(progressDialogBuilder3);
        progressDialogBuilder3.dismiss();
        if (FicheTypeControlUtils.isFicheTypeDemand(this.mCustomerOperation.getFicheType())) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private String getPriceCode(SalesDetail salesDetail) {
        List table = this.baseErp.getLogoSqlHelper().getTable(ItemPrice.class, "ITEMREF=? AND CYPHCODE=?", new String[]{String.valueOf(salesDetail.getItemRef()), this.viewModel.getBaseErp().getUser().getCyphCode()});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.product.model.database.ItemPrice>");
        if (table.isEmpty()) {
            return "";
        }
        String str = ((ItemPrice) table.get(0)).priceCode;
        Intrinsics.checkNotNull(str);
        return str;
    }

    private void setResultXml(List<? extends ResultPrice> list) {
        String strConvertNullToEmpty;
        Log.d(MobileSales.TAG, "setResultXml: size : " + list.size());
        if (getmSales() != null) {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            if (sales.getMSalesDetailList() == null) {
                return;
            }
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            ArrayList<SalesDetail> mSalesDetailList = sales2.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList);
            Iterator<SalesDetail> it = mSalesDetailList.iterator();
            while (it.hasNext()) {
                SalesDetail next = it.next();
                Iterator<? extends ResultPrice> it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        ResultPrice next2 = it2.next();
                        if (Intrinsics.areEqual(next2.getMasterCode(), next.getCode()) && next.getUnit().getCode() != null && Intrinsics.areEqual(next2.getUnitCode(), next.getUnit().getCode()) && Intrinsics.areEqual(next2.getVariantCode(), next.getVariant().getCode())) {
                            if (next.getPrice().getDefinition() != null) {
                                String definition = next.getPrice().getDefinition();
                                checkNotNullExpressionValue(definition, "getDefinition(...)");
                                if (definition.length() > 0) {
                                    return;
                                }
                            }
                            Cursor cursorQuery = null;
                            try {
                                try {
                                    if (this.viewModel.isSalesPersonWarrantyCodePriceFirst()) {
                                        String code = next.getPayment().getCode();
                                        checkNotNullExpressionValue(code, "getCode(...)");
                                        if (code.length() == 0) {
                                            Intrinsics.checkNotNull(next);
                                            strConvertNullToEmpty = getPriceCode(next);
                                        } else {
                                            Intrinsics.checkNotNull(next);
                                            if (getPriceListWithPayment(next).isEmpty()) {
                                                strConvertNullToEmpty = StringUtils.empty();
                                            } else {
                                                String priceCode = next2.getPriceCode();
                                                checkNotNullExpressionValue(priceCode, "getPriceCode(...)");
                                                strConvertNullToEmpty = StringUtils.convertNullToEmpty(priceCode);
                                            }
                                        }
                                    } else {
                                        String priceCode2 = next2.getPriceCode();
                                        checkNotNullExpressionValue(priceCode2, "getPriceCode(...)");
                                        strConvertNullToEmpty = StringUtils.convertNullToEmpty(priceCode2);
                                    }
                                    cursorQuery = this.viewModel.getSqlBriteDatabase().query(getString(R.string.qry_get_price_value, !next.isProduct() ? "SERVICEUNITS" : "ITEMUNITS"), String.valueOf(next.getItemRef()), strConvertNullToEmpty, String.valueOf(next.getPType()));
                                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                                        next.getSelectedPrice().reset();
                                        next.setUsePrice(0.0d);
                                        next.setCalculateCurrPrice(0.0d);
                                        boolean z = true;
                                        next.setPriceSet(true);
                                        if (cursorQuery.getInt(cursorQuery.getColumnIndex("UNITCONVERT")) != 1) {
                                            z = false;
                                        }
                                        double d2 = cursorQuery.getDouble(cursorQuery.getColumnIndex("PRICE"));
                                        if (z) {
                                            next.setEnteryPrice(CalculateUtils.convertUnitPrice(d2, next.getConvFact1(), next.getConvFact2(), cursorQuery.getDouble(cursorQuery.getColumnIndex("CONVFACT1")), cursorQuery.getDouble(cursorQuery.getColumnIndex("CONVFACT2"))));
                                        } else {
                                            next.setEnteryPrice(d2);
                                        }
                                        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(next2.getVat())));
                                        next.getSelectedPrice().setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_id))));
                                        next.getCurrType().reset();
                                        next.curCodeStr = cursorQuery.getString(cursorQuery.getColumnIndex("CURCODE"));
                                        UnitPriceFormatter unitPriceFormatter = this.mUnitPriceFormatter;
                                        Intrinsics.checkNotNull(unitPriceFormatter);
                                        next.setPriceWithDigit(unitPriceFormatter.getFormattedPrice(next.getEnteryPrice()));
                                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                        String str = String.format("%s / %s", Arrays.copyOf(new Object[]{next.getPriceWithDigit(), next.getCurCodeStr()}, 2));
                                        checkNotNullExpressionValue(str, "format(...)");
                                        next.setPriceWithCurCode(str);
                                        FicheStringProp.setDefinition(next.getPriceWithCurCode());
                                        double d3 = cursorQuery.getDouble(cursorQuery.getColumnIndex("RATE"));
                                        if (d3 == 0.0d) {
                                            d3 = 1.0d;
                                        }
                                        next.setPrRate(d3);
                                        next.prCurrType = cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_curr_nr)));
                                        next.getCurrType().setLogicalRef(next.getPrCurrType());
                                        FicheStringProp.setDefinition(next.getCurCodeStr());
                                        next.getIncludeVat().setSelect(StringUtils.convertIntToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("INCVAT"))));
                                        Sales sales3 = getmSales();
                                        Intrinsics.checkNotNull(sales3);
                                        if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(sales3.getmSalesType())) {
                                            next.resetSelectedPrice();
                                            FicheStringProp.setDefinition(String.valueOf(next2.getPrice()));
                                        }
                                        Log.d(MobileSales.TAG, "setResultXml: " + next.getIncludeVat());
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    if (true) {
                                    }
                                }
                                if (cursorQuery != null && !cursorQuery.isClosed()) {
                                    cursorQuery.close();
                                }
                            } catch (Throwable th) {
                                if (false) {
                                    cursorQuery.close();
                                }
                                throw th;
                            }
                        }
                    }
                }
            }
            Sales sales4 = getmSales();
            Intrinsics.checkNotNull(sales4);
            ArrayList<SalesDetail> mSalesDetailList2 = sales4.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            if (mSalesDetailList2.size() > 0) {
                Sales sales5 = getmSales();
                Intrinsics.checkNotNull(sales5);
                sales5.moveGeneralPromotionLineToTheLast();
                SalesPagerAdapter salesPagerAdapter = this.mAdapter;
                Intrinsics.checkNotNull(salesPagerAdapter);
                salesPagerAdapter.notifyDataSetChanged();
            }
        }
    }

    private List<ItemPrice> getPriceListWithPayment(SalesDetail salesDetail) {
        List<ItemPrice> table = this.baseErp.getLogoSqlHelper().getTable(ItemPrice.class, "ITEMREF=? AND CYPHCODE=? AND PAYPLANREF=?", new String[]{String.valueOf(salesDetail.getItemRef()), this.viewModel.getBaseErp().getUser().getCyphCode(), String.valueOf(salesDetail.getPayment().getLogicalRef())});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.product.model.database.ItemPrice>");
        return table;
    }

    private record SalesDetailListSetPriceFicheListener(
            WeakReference<SalesActivityNew> mSalesActivityNewWeakReference) implements ResponseListener<List<? extends ResultPrice>> {
            private SalesDetailListSetPriceFicheListener(SalesActivityNew mSalesActivityNewWeakReference) {
                this.mSalesActivityNewWeakReference = new WeakReference<>(mSalesActivityNewWeakReference);
            }

        public void onResponse(PrintSlipModel list) {
                if (this.mSalesActivityNewWeakReference.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivityNewWeakReference.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivityNewWeakReference.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onAllProductPriceSetResult(list, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (this.mSalesActivityNewWeakReference.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivityNewWeakReference.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivityNewWeakReference.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onAllProductPriceSetResult(null, errorMessage);
                }
            }
        }
    static final class SalesActivityDueDateTrackListener implements ResponseListener<List<? extends DueDateTotal>> {
        private final WeakReference<SalesActivityNew> mSalesActivity;

        public SalesActivityDueDateTrackListener(SalesActivityNew salesActivityNew) {
            this.mSalesActivity = new WeakReference<>(salesActivityNew);
        }
        public void onResponse(PrintSlipModel list) {
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onDateTrackResult(list, "");
            }
        }
        public void onFailure(Throwable throwable) {

        }
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onDateTrackResult(null, errorMessage);
            }
        }
    }
    public void onDateTrackResult(List<? extends DueDateTotal> list, String str) {
        this.mProgressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
        if (list != null) {
            if (!list.isEmpty()) {
                DueDateTotal dueDateTotal = list.get(0);
                double total = dueDateTotal.getTotal();
                DueDate dueDate = this.mDueDate;
                Intrinsics.checkNotNull(dueDate);
                if (total > dueDate.getDueDateLimit()) {
                    DueDate dueDate2 = this.mDueDate;
                    Intrinsics.checkNotNull(dueDate2);
                    if (dueDate2.getDueDateAlert() == RiskAlert.NOTIFY) {
                        double total2 = dueDateTotal.getTotal();
                        DueDate dueDate3 = this.mDueDate;
                        Intrinsics.checkNotNull(dueDate3);
                        String str2 = StringUtils.formatDouble(total2 - dueDate3.getDueDateLimit());
                        DueDate dueDate4 = this.mDueDate;
                        Intrinsics.checkNotNull(dueDate4);
                        String string = getString(R.string.exp_50_due_date_over_error, str2, StringUtils.formatDouble(dueDate4.getDueDateLimit()));
                        checkNotNullExpressionValue(string, "getString(...)");
                        createNotifyAlertDialog(R.string.str_due_date_limit_over_title, string);
                        return;
                    }
                    DueDate dueDate5 = this.mDueDate;
                    Intrinsics.checkNotNull(dueDate5);
                    if (dueDate5.getDueDateAlert() == RiskAlert.ABORT) {
                        double total3 = dueDateTotal.getTotal();
                        DueDate dueDate6 = this.mDueDate;
                        Intrinsics.checkNotNull(dueDate6);
                        String str3 = StringUtils.formatDouble(total3 - dueDate6.getDueDateLimit());
                        DueDate dueDate7 = this.mDueDate;
                        Intrinsics.checkNotNull(dueDate7);
                        String string2 = getString(R.string.exp_50_due_date_over_error, str3, StringUtils.formatDouble(dueDate7.getDueDateLimit()));
                        checkNotNullExpressionValue(string2, "getString(...)");
                        createAbortAlertDialog(R.string.str_due_date_limit_over_title, string2);
                        return;
                    }
                    return;
                }
                saveFiche();
                return;
            }
            saveFiche();
            return;
        }
        saveFiche();
    }

    private void createNotifyAlertDialog(@StringRes int r2, String str) {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderNotify;
        Intrinsics.checkNotNull(alertDialogBuilder);
        if (alertDialogBuilder.isShowing()) {
            AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilderNotify;
            Intrinsics.checkNotNull(alertDialogBuilder2);
            alertDialogBuilder2.dismiss();
        }
        AlertDialogBuilder<?> alertDialogBuilder3 = this.mAlertDialogBuilderNotify;
        Intrinsics.checkNotNull(alertDialogBuilder3);
        AlertDialogBuilder title = alertDialogBuilder3.setTitle(r2);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%s%s", Arrays.copyOf(new Object[]{str, getString(R.string.str_question_save_fiche)}, 2));
        checkNotNullExpressionValue(str2, "format(...)");
        title.setMessage(str2).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r22) {
                SalesActivityNew.createNotifyAlertDialoglambda32(dialogInterface, r22);
            }
        }).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public void onClick(DialogInterface dialogInterface, int r22) {
                SalesActivityNew.createNotifyAlertDialoglambda33(this.f0, dialogInterface, r22);
            }
        }).create().show();
    }
    public static void createNotifyAlertDialoglambda32(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public static void createNotifyAlertDialoglambda33(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        this0.saveFiche();
    }

    private void createAbortAlertDialog(@StringRes int r2, String str) {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilderNotify;
        Intrinsics.checkNotNull(alertDialogBuilder);
        if (alertDialogBuilder.isShowing()) {
            AlertDialogBuilder<?> alertDialogBuilder2 = this.mAlertDialogBuilderNotify;
            Intrinsics.checkNotNull(alertDialogBuilder2);
            alertDialogBuilder2.dismiss();
        }
        AlertDialogBuilder<?> alertDialogBuilder3 = this.mAlertDialogBuilderNotify;
        Intrinsics.checkNotNull(alertDialogBuilder3);
        AlertDialogBuilder title = alertDialogBuilder3.setTitle(r2);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%s%s", Arrays.copyOf(new Object[]{str, getString(R.string.str_question_cancel_fiche)}, 2));
        checkNotNullExpressionValue(str2, "format(...)");
        title.setMessage(str2).setPositiveButton(R.string.str_yes, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public void onClick(DialogInterface dialogInterface, int r22) {
                SalesActivityNew.createAbortAlertDialoglambda34(this.f0, dialogInterface, r22);
            }
        }).setNegativeButton(R.string.str_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int r22) {
                SalesActivityNew.createAbortAlertDialoglambda35(dialogInterface, r22);
            }
        }).create().show();
    }
    public static void createAbortAlertDialoglambda34(SalesActivityNew this0, DialogInterface dialog, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        this0.onBackPressed();
        dialog.dismiss();
    }

    public static void createAbortAlertDialoglambda35(DialogInterface dialog, int r1) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    public boolean isCustomerEInvoiceSgkType() {
        try {
            if (!SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(getSalesType()) && (!SalesUtils.isSalesTypeOrder(getSalesType()) || !this.viewModel.canOrderCanTransferEInvoiceOrEArchive())) {
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        if (sqlHelper.getClEInvoiceUser(sales.getClRef()) == 1) {
            return EInvoiceTyp.Companion.fromEInvoiceTyp(this.viewModel.getSqlHelper().getClEInvoiceTyp(getmCustomerRef())) == EInvoiceTyp.Sgk;
        }
        return false;
    }

    private String customerEInvoiceFieldsControl() throws InterruptedException {
        String string = "";
        if (isCustomerEInvoiceSgkType()) {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            if (sales.getEInvoiceTypSgk().getLogicalRef() <= 0) {
                string = getString(R.string.str_select_additional_invoice_type);
                checkNotNullExpressionValue(string, "getString(...)");
            } else {
                Sales sales2 = this.mSales;
                Intrinsics.checkNotNull(sales2);
                String string2 = sales2.getTaxPayerCode().toString();
                checkNotNullExpressionValue(string2, "toString(...)");
                if (string2.length() == 0) {
                    string = getString(R.string.exp_60_not_empty_taxpayer_code);
                    checkNotNullExpressionValue(string, "getString(...)");
                } else {
                    Sales sales3 = this.mSales;
                    Intrinsics.checkNotNull(sales3);
                    String string3 = sales3.getTaxPayerName().toString();
                    checkNotNullExpressionValue(string3, "toString(...)");
                    if (string3.length() == 0) {
                        string = getString(R.string.exp_61_not_empty_taxpayer_name);
                        checkNotNullExpressionValue(string, "getString(...)");
                    } else {
                        Sales sales4 = this.mSales;
                        Intrinsics.checkNotNull(sales4);
                        String string4 = sales4.getEInvoiceSGKDocumentNo().toString();
                        checkNotNullExpressionValue(string4, "toString(...)");
                        if (string4.length() == 0) {
                            string = getString(R.string.exp_65_file_number);
                            checkNotNullExpressionValue(string, "getString(...)");
                        } else {
                            Sales sales5 = this.mSales;
                            Intrinsics.checkNotNull(sales5);
                            String string5 = sales5.getBeginDate().toString();
                            checkNotNullExpressionValue(string5, "toString(...)");
                            if (string5.length() == 0) {
                                string = getString(R.string.exp_62_begin_date);
                                checkNotNullExpressionValue(string, "getString(...)");
                            } else {
                                Sales sales6 = this.mSales;
                                Intrinsics.checkNotNull(sales6);
                                String string6 = sales6.getEndDate().toString();
                                checkNotNullExpressionValue(string6, "toString(...)");
                                if (string6.length() == 0) {
                                    string = getString(R.string.exp_63_end_date);
                                    checkNotNullExpressionValue(string, "getString(...)");
                                } else {
                                    string = "";
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            return string;
        }
        Sales sales7 = this.mSales;
        Intrinsics.checkNotNull(sales7);
        if (sales7.getmSalesType() != SalesType.INVOICE) {
            return string;
        }
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales8 = this.mSales;
        Intrinsics.checkNotNull(sales8);
        EDocInfoModel eDocInfo = salesNewViewModel.getEDocInfo(sales8.getClRef(), -1);
        Sales sales9 = this.mSales;
        Intrinsics.checkNotNull(sales9);
        if (!TextUtils.isEmpty(sales9.getEDocSerial().getDefinition())) {
            return string;
        }
        if (!eDocInfo.isAcceptEArchive() && !eDocInfo.isAcceptEInvoice()) {
            return string;
        }
        String string7 = getString(eDocInfo.isAcceptEInvoice() ? R.string.exp_82_einvoice_serial : R.string.exp_83_earchive_serial);
        checkNotNullExpressionValue(string7, "getString(...)");
        return string7;
    }

    private String customerEDespatchFieldsControl() {
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2) {
            Sales sales = getmSales();
            Intrinsics.checkNotNull(sales);
            if (sales.getmSalesType() == SalesType.DISPATCH) {
                Sales sales2 = getmSales();
                Intrinsics.checkNotNull(sales2);
                if (sales2.getEDespatch().isSelect()) {
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    EDispatchAdditionalInfo eDispatchAdditionalInfo = sales3.getEDispatchAdditionalInfo();
                    StringBuilder sb = new StringBuilder();
                    Sales sales4 = getmSales();
                    Intrinsics.checkNotNull(sales4);
                    if (TextUtils.isEmpty(sales4.getEDocSerial().getDefinition())) {
                        sb.append(getString(R.string.exp_70_edespatch_serial));
                    }
                    if (eDispatchAdditionalInfo == null) {
                        sb.append(getString(R.string.exp_71_edespatch_info));
                        String string = sb.toString();
                        checkNotNullExpressionValue(string, "toString(...)");
                        return string;
                    }
                    if (TextUtils.isEmpty(eDispatchAdditionalInfo.firstDriverPlate)) {
                        sb.append(getString(R.string.exp_72_edespatch_plate));
                    }
                    if (TextUtils.isEmpty(eDispatchAdditionalInfo.carrierTaxNr)) {
                        sb.append(getString(R.string.exp_73_edespatch_tax_number));
                    }
                    if (TextUtils.isEmpty(eDispatchAdditionalInfo.firstDriverName)) {
                        sb.append(getString(R.string.exp_79_edespatch_first_driver_name));
                    }
                    String string2 = sb.toString();
                    checkNotNullExpressionValue(string2, "toString(...)");
                    return string2;
                }
            }
        }
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales5 = this.mSales;
        Intrinsics.checkNotNull(sales5);
        try {
            if (!salesNewViewModel.getUseEDispatch(sales5.getBranch().getLogicalRef()) || this.viewModel.erpType() == erpType2) {
                return "";
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
            Sales sales6 = getmSales();
            Intrinsics.checkNotNull(sales6);
            if (sales6.getEDespatch().isSelect()) {
                Sales sales7 = this.mSales;
                Intrinsics.checkNotNull(sales7);
                if (sales7.getShipAgent().getLogicalRef() <= 0) {
                    String string3 = getString(R.string.exp_ship_agent);
                    checkNotNullExpressionValue(string3, "getString(...)");
                    return string3;
                }
            }
        }
        if ((!SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(getSalesType()) && !SalesUtils.isSalesTypeDispatch(getSalesType())) || !isShipAgentInvalid()) {
            return "";
        }
        String string4 = getString(R.string.exp_ship_agent);
        checkNotNullExpressionValue(string4, "getString(...)");
        return string4;
    }

    private boolean isShipAgentInvalid() {
        SalesFicheParameters salesFicheParameters = this.mSalesFicheParameters;
        Intrinsics.checkNotNull(salesFicheParameters);
        SalesFicheHeaderFields mSalesFicheHeaderFields = salesFicheParameters.getMSalesFicheHeaderFields();
        Intrinsics.checkNotNull(mSalesFicheHeaderFields);
        if (mSalesFicheHeaderFields.isShipAgent()) {
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            return sales.getShipAgent().getLogicalRef() <= 0 && (this.mInsteadOfDesp != 1 || SalesUtils.isSalesTypeDispatch(getSalesType()));
        }
        return false;
    }

    public int getmCustomerShipRef() {
        return this.mCustomerShipRef;
    }

    private void deleteOldReceiptIfExists(int r3) {
        this.viewModel.getSqlBriteDatabase().executeMultipleStatements(getString(R.string.qry_delete_oldreceipt_where_invoice, Integer.valueOf(r3)));
    }

    private void checkSeriItemLocation(int r5) {
        int logicalRef;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        ArrayList<SalesDetail> mSalesDetailList = sales.getMSalesDetailList();
        Intrinsics.checkNotNull(mSalesDetailList);
        if (r5 < mSalesDetailList.size() && SalesUtils.isSalesSeriCheckLocationTracking(getSalesType())) {
            this.mLastSalesDetailLocationPosition = r5;
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            ArrayList<SalesDetail> mSalesDetailList2 = sales2.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            SalesDetail salesDetail = mSalesDetailList2.get(r5);
            checkNotNullExpressionValue(salesDetail, "get(...)");
            SalesDetail salesDetail2 = salesDetail;
            if (salesDetail2.isLocTracking() && salesDetail2.hasSeriLotWithoutLocCode() && salesDetail2.getTrackType() == TrackType.NON_TRACK.getType() && isConnected(this)) {
                SalesNewViewModel salesNewViewModel = this.viewModel;
                if (SalesUtils.isSalesTypeWhTransfer(getSalesType())) {
                    logicalRef = this.viewModel.user().getWarehouseNr();
                } else {
                    Sales sales3 = getmSales();
                    Intrinsics.checkNotNull(sales3);
                    logicalRef = sales3.getWareHouse().getLogicalRef();
                }
                salesNewViewModel.getSalesDetailStockTracking(salesDetail2, logicalRef, new SalesActivityStockTrackingListener(this));
                return;
            }
            checkSeriItemLocation(r5 + 1);
            return;
        }
        this.salesDetailsLocationChecked = true;
        saveFiche();
    }

    private record SalesActivityStockTrackingListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<List<? extends LocationStockTracking>> {
            private SalesActivityStockTrackingListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

            public void onResponse(PrintSlipModel list) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.processStockTracking(list, "");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.processStockTracking(null, errorMessage);
                }
            }
        }
    public void processStockTracking(List<? extends LocationStockTracking> list, String str) {
        ArrayList<Serilot> arrayList = new ArrayList<>();
        if (str.length() > 0) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        } else if (list != null && !list.isEmpty()) {
            int size = list.size();
            double d2 = 0.0d;
            for (int r4 = 0; r4 < size; r4++) {
                LocationStockTracking locationStockTracking = list.get(r4);
                Serilot serilot = new Serilot(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
                serilot.logicalRef = locationStockTracking.getLogicalRef();
                serilot.unit = locationStockTracking.getUnit();
                serilot.expDate = DateAndTimeUtils.convertYMDToDMY(locationStockTracking.getExpDate());
                String locCode = locationStockTracking.getLocCode();
                checkNotNullExpressionValue(locCode, "getLocCode(...)");
                serilot.locationCode = locCode;
                double remAmount = locationStockTracking.getRemAmount();
                Sales sales = getmSales();
                ArrayList<SalesDetail> mSalesDetailList = sales != null ? sales.getMSalesDetailList() : null;
                Intrinsics.checkNotNull(mSalesDetailList);
                SalesDetail salesDetail = mSalesDetailList.get(this.mLastSalesDetailLocationPosition);
                checkNotNullExpressionValue(salesDetail, "get(...)");
                SalesDetail salesDetail2 = salesDetail;
                serilot.mainUnitRef = this.viewModel.getSqlHelper().getUnitLogicalRef(1, salesDetail2.getItemRef());
                double definitionDouble = salesDetail2.getConvFact1() == 0.0d ? 0.0d : (salesDetail2.getAmount().getDefinitionDouble() * salesDetail2.getConvFact2()) / salesDetail2.getConvFact1();
                serilot.sourceUnitRef = locationStockTracking.getUomRef();
                serilot.stTransRef = locationStockTracking.getStTransRef();
                if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(getSalesType())) {
                    remAmount = definitionDouble;
                }
                if (d2 + remAmount <= definitionDouble) {
                    serilot.amount = remAmount;
                    d2 += remAmount;
                } else {
                    double d3 = definitionDouble - d2;
                    serilot.amount = d3;
                    d2 += d3;
                }
                arrayList.add(serilot);
                if (d2 == definitionDouble) {
                    break;
                }
            }
            Sales sales2 = getmSales();
            Intrinsics.checkNotNull(sales2);
            ArrayList<SalesDetail> mSalesDetailList2 = sales2.getMSalesDetailList();
            Intrinsics.checkNotNull(mSalesDetailList2);
            mSalesDetailList2.get(this.mLastSalesDetailLocationPosition).setSalesSerialLots(arrayList);
        }
        int r1 = this.mLastSalesDetailLocationPosition + 1;
        this.mLastSalesDetailLocationPosition = r1;
        checkSeriItemLocation(r1);
    }

    private void insertOrUpdateCabinTrans() {
        SalesType salesType = this.mCustomerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrderOrDemand(salesType)) {
            return;
        }
        if (this.mCustomerOperation.getFicheMode() == FicheMode.NEW || this.mCustomerOperation.getFicheMode() == FicheMode.COPY) {
            SalesNewViewModel salesNewViewModel = this.viewModel;
            Sales sales = this.mSales;
            Intrinsics.checkNotNull(sales);
            salesNewViewModel.insertCabinTrans(sales.getCabin().getLogicalRef(), this.mCustomerRef, MainActivity.sFicheRef, CabinTransTrType.Companion.getCabinTransTrTypeValueFromSalesType(this.mCustomerOperation.getSalesType()));
            return;
        }
        if (this.mCustomerOperation.getFicheMode() == FicheMode.EDIT) {
            SalesNewViewModel salesNewViewModel2 = this.viewModel;
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            int logicalRef = sales2.getCabin().getLogicalRef();
            int r2 = this.mCustomerRef;
            Sales sales3 = this.mSales;
            Intrinsics.checkNotNull(sales3);
            salesNewViewModel2.updateCabinTrans(logicalRef, r2, sales3.getLogicalRef(), CabinTransTrType.Companion.getCabinTransTrTypeValueFromSalesType(this.mCustomerOperation.getSalesType()));
        }
    }

    public void onGetWarehouseItems(List<WarehouseItem> list, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mProgressDialogBuilder.dismiss();
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
        if (list != null && !list.isEmpty()) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            new C31111(list).start();
        } else {
            this.mProgressDialogBuilder.dismiss();
            Toast.makeText(this, getString(R.string.str_data_not_found), Toast.LENGTH_LONG).show();
        }
    }
    public final class C31111 extends Thread {
        final List<WarehouseItem> response;
        private boolean isOperationSuccess;

        C31111(List<WarehouseItem> list) {
            this.response = list;
        }

        public boolean isOperationSuccess() {
            return this.isOperationSuccess;
        }

        public void setOperationSuccess(boolean z) {
            this.isOperationSuccess = z;
        }
        public void run() {
            Sales sales = SalesActivityNew.this.getmSales();
            Intrinsics.checkNotNull(sales);
            this.isOperationSuccess = sales.convertWhouseItemsToSalesDetails(this.response);
            try {
                final SalesActivityNew salesActivityNew = SalesActivityNew.this;
                salesActivityNew.runOnUiThread(new Runnable() {
                    public void run() {
                        C31111.runlambda0(C31111.this, salesActivityNew);
                    }
                });
            } catch (Exception e2) {
                SalesActivityNew.this.mProgressDialogBuilder.dismiss();
                String message = e2.getMessage();
                Intrinsics.checkNotNull(message);
                Log.e(SalesActivityNew.TAG, message);
            }
        }
        public static void runlambda0(C31111 this0, SalesActivityNew this1) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(this1, "this1");
            if (!this0.isOperationSuccess) {
                this1.mProgressDialogBuilder.dismiss();
                Toast.makeText(this1, this1.getString(R.string.exp_19_transfer_update_error), Toast.LENGTH_LONG).show();
            } else {
                SalesPagerAdapter salesPagerAdapter = this1.getmAdapter();
                Intrinsics.checkNotNull(salesPagerAdapter);
                salesPagerAdapter.updateFragments();
                this1.mProgressDialogBuilder.dismiss();
            }
        }
    }

    static final class GetAllWarehouseItemsListener implements ResponseListener<List<? extends WarehouseItem>> {
        private final WeakReference<SalesActivityNew> mSalesActivity;

        public GetAllWarehouseItemsListener(SalesActivityNew salesActivityNew) {
            this.mSalesActivity = new WeakReference<>(salesActivityNew);
        }
        public   void onResponse(PrintSlipModel list) {
            onResponse2((List<WarehouseItem>) list);
        }
        public void onFailure(Throwable throwable) {

        }
        public void onResponse2(List<WarehouseItem> list) {
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onGetWarehouseItems(list, "");
            }
        }
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d(SalesActivityNew.TAG, "GetAllWarehouseItemsListener: " + errorMessage);
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onGetWarehouseItems(null, errorMessage);
            }
        }
    }
    static final class UsableCampaignCardsListener implements ResponseListener<List<? extends UsableCampaignCard>> {
        private final WeakReference<SalesActivityNew> mSalesActivity;

        public UsableCampaignCardsListener(SalesActivityNew salesActivityNew) {
            this.mSalesActivity = new WeakReference<>(salesActivityNew);
        }
        public void onResponse(PrintSlipModel list) {
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onGetUsableCampaigns(list, "");
            }
        }
        public void onFailure(Throwable throwable) {

        }
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
            if (this.mSalesActivity.get() != null) {
                SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew);
                if (salesActivityNew.isActivityDestroyed()) {
                    return;
                }
                SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                Intrinsics.checkNotNull(salesActivityNew2);
                salesActivityNew2.onGetUsableCampaigns(null, errorMessage);
            }
        }
    }

    private   void applyCampaign() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        if (!progressDialogBuilder.isShowing()) {
            ProgressDialogBuilder<?> progressDialogBuilder2 = this.mCampaignProgressDialogBuilder;
            Intrinsics.checkNotNull(progressDialogBuilder2);
            progressDialogBuilder2.show();
        }
        clearCampaign();
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        salesNewViewModel.showOnlineCampaign(sales, new SalesActivityCampaignListener(this));
    }
    public <C3110x18c894b4> void onGetUsableCampaigns(final List<? extends UsableCampaignCard> list, String str) {
        List listEmptyList;
        int size;
        int r4 = 0;
        ProgressDialogBuilder<?> progressDialogBuilder = this.mCampaignProgressDialogBuilder;
        Intrinsics.checkNotNull(progressDialogBuilder);
        progressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            return;
        }
        if (list == null || list.isEmpty()) {
            Toast.makeText(this, getString(R.string.exp_campaign_not_found), Toast.LENGTH_LONG).show();
            return;
        }
        Stream<? extends UsableCampaignCard> stream = list.stream();
        final C3110x18c894b4 c3110x18c894b4;
        c3110x18c894b4 = new C3110x18c894b4();
        Object[] array = stream.map(new Function() {
            public Object apply(Object obj) {
                return SalesActivityNew.onGetUsableCampaignslambda36(c3110x18c894b4, obj);
            }
        }).toArray();
        boolean[] zArr = new boolean[list.size()];
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        if (!TextUtils.isEmpty(sales.getCampaingRefs())) {
            Sales sales2 = this.mSales;
            Intrinsics.checkNotNull(sales2);
            String campaingRefs = sales2.getCampaingRefs();
            Intrinsics.checkNotNull(campaingRefs);
            List<String> listSplit = new Regex(";").split(campaingRefs, 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
                String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
                List listAsList = Arrays.asList(Arrays.copyOf(strArr, strArr.length));
                size = list.size();
                for (r4 = 0; r4 < size; r4++) {
                    zArr[r4] = listAsList.contains(String.valueOf(list.get(r4).getLogicalRef()));
                    list.get(r4).setSelected(zArr[r4]);
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
                String[] strArr2 = (String[]) listEmptyList.toArray(new String[0]);
                List listAsList2 = Arrays.asList(Arrays.copyOf(strArr2, strArr2.length));
                size = list.size();
                while (r4 < size) {
                }
            }
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder title = builder.setTitle(getString(R.string.str_applicable_campaigns));
        Intrinsics.checkNotNull(array);
        ArrayList arrayList = new ArrayList(array.length);
        for (Object obj : array) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.CharSequence");
            arrayList.add( obj);
        }
        title.setMultiChoiceItems((CharSequence[]) arrayList.toArray(new CharSequence[0]), zArr, new DialogInterface.OnMultiChoiceClickListener() {
            public  void onClick(DialogInterface dialogInterface, int r2, boolean z) {
                SalesActivityNew.onGetUsableCampaignslambda39(list, dialogInterface, r2, z);
            }
        }).setNeutralButton(getString(R.string.str_apply_all), new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public   void onClick(DialogInterface dialogInterface, int r3) {
                SalesActivityNew.onGetUsableCampaignslambda40(this.f0, list, dialogInterface, r3);
            }
        }).setPositiveButton(getString(R.string.str_apply), new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public   void onClick(DialogInterface dialogInterface, int r42) {
                SalesActivityNew.onGetUsableCampaignslambda41(this.f0, list, builder, dialogInterface, r42);
            }
        }).setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            private SalesActivityNew f0;

            public   void onClick(DialogInterface dialogInterface, int r2) throws Resources.NotFoundException {
                SalesActivityNew.onGetUsableCampaignslambda43(this.f0, dialogInterface, r2);
            }
        }).setCancelable(false).create().show();
    }

    private static <C3110x18c894b4> Object onGetUsableCampaignslambda36(C3110x18c894b4 c3110x18c894b4, Object obj) {
        return obj;
    }

    public static   void onGetUsableCampaignslambda39(List list, DialogInterface dialogInterface, int r2, boolean z) {
        ((UsableCampaignCard) list.get(r2)).setSelected(z);
    }
    public static   void onGetUsableCampaignslambda40(SalesActivityNew this0, List list, DialogInterface dialogInterface, int r3) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Sales sales = this0.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setCampaingRefs(this0.getSelectedCampaignRefs(list, true));
        this0.applyCampaign();
    }

    private static   void onGetUsableCampaignslambda43(SalesActivityNew this0, DialogInterface dialogInterface, int r2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        dialogInterface.dismiss();
    }
    public static   void onGetUsableCampaignslambda41(SalesActivityNew this0, List list, AlertDialog.Builder builder, DialogInterface dialog, int r5) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Sales sales = this0.mSales;
        Intrinsics.checkNotNull(sales);
        sales.setCampaingRefs(this0.getSelectedCampaignRefs(list, false));
        Sales sales2 = this0.mSales;
        Intrinsics.checkNotNull(sales2);
        if (TextUtils.isEmpty(sales2.getCampaingRefs())) {
            Toast.makeText(this0, this0.getString(R.string.str_not_selected_any_campaigns), Toast.LENGTH_LONG).show();
            builder.show();
        } else {
            dialog.dismiss();
            this0.applyCampaign();
        }
    }

    private String getSelectedCampaignRefs(List list, boolean b) {
        return "";
    }

    private static Object getSelectedCampaignRefslambda45(SalesActivityNewgetSelectedCampaignRefsselectedRefs1 salesActivityNewgetSelectedCampaignRefsselectedRefs1, Object obj) {
        return obj;
    }
    public   void resetPrices() {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_prices_transferring)).setCancelable(false).show();
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales = getmSales();
        Intrinsics.checkNotNull(sales);
        salesNewViewModel.resetPricesOnDivisionChange(sales, new ResetPricesListener(this));
    }

    private record ResetPricesListener(
            WeakReference<SalesActivityNew> mSalesActivity) implements ResponseListener<String> {
            private ResetPricesListener(SalesActivityNew mSalesActivity) {
                this.mSalesActivity = new WeakReference<>(mSalesActivity);
            }

        public void onResponse(PrintSlipModel str) {
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onResetPricesResponse("");
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(SalesActivityNew.TAG, "onError: " + errorMessage);
                if (this.mSalesActivity.get() != null) {
                    SalesActivityNew salesActivityNew = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew);
                    if (salesActivityNew.isActivityDestroyed()) {
                        return;
                    }
                    SalesActivityNew salesActivityNew2 = this.mSalesActivity.get();
                    Intrinsics.checkNotNull(salesActivityNew2);
                    salesActivityNew2.onResetPricesResponse(errorMessage);
                }
            }
        }
    public   void onResetPricesResponse(String str) {
        this.mProgressDialogBuilder.dismiss();
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            return;
        }
        SalesPagerAdapter salesPagerAdapter = this.mAdapter;
        Intrinsics.checkNotNull(salesPagerAdapter);
        salesPagerAdapter.updateFragments();
    }
    private   void fillPreviousDriverInfos() {
        SalesNewViewModel salesNewViewModel = this.viewModel;
        Sales sales = this.mSales;
        Intrinsics.checkNotNull(sales);
        String strValueOf = String.valueOf(sales.getLogicalRef());
        Sales sales2 = this.mSales;
        Intrinsics.checkNotNull(sales2);
        List<EDispatchAdditionalInfo> serviceTypeTableFromLogoSqlHelper = salesNewViewModel.getServiceTypeTableFromLogoSqlHelper(EDispatchAdditionalInfo.class, "SALESFICHEID=? AND SALESTYPE=?", new String[]{strValueOf, String.valueOf(sales2.getmSalesType().getmValue())});
        if (serviceTypeTableFromLogoSqlHelper == null || serviceTypeTableFromLogoSqlHelper.isEmpty()) {
            return;
        }
        Sales sales3 = this.mSales;
        Intrinsics.checkNotNull(sales3);
        sales3.setEDispatchAdditionalInfo(serviceTypeTableFromLogoSqlHelper.get(0));
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
