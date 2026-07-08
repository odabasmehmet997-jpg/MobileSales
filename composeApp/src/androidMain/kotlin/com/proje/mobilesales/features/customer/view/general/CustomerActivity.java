package com.proje.mobilesales.features.customer.view.general;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.receiver.ConnectivityReceiver;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.widget.ViewPager;
import com.proje.mobilesales.databinding.ActivityCustomerBinding;
import com.proje.mobilesales.features.activity.MarketingMessageListActivity;
import com.proje.mobilesales.features.customer.model.CustomerCampaignPoint;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.newadd.CustomerNewActivity;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.model.CheckAutoVisitItem;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.*;
import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public final class CustomerActivity extends BaseErpActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_CABIN_REF = "extra:customerCabinRef";
    public static final String EXTRA_CUSTOMER_CODE = "extra:customerCode";
    public static final String EXTRA_CUSTOMER_NAME = "extra:customerName";
    public static final String EXTRA_CUSTOMER_REF = "extra:clRef";
    public static final String EXTRA_CUSTOMER_SHIPREF = "extra:shipRef";
    public static final String STATE_CUSTOMER_CABIN_REF = "state:customerCabinRef";
    public static final String STATE_CUSTOMER_CODE = "state:customerCode";
    public static final String STATE_CUSTOMER_DETAIL = "state:customerDetail";
    public static final String STATE_CUSTOMER_NAME = "state:customerName";
    public static final String STATE_CUSTOMER_REF = "state:customerRef";
    public static final String STATE_CUSTOMER_TAB_NAME = "state:customerTabName";
    private ActivityCustomerBinding binding;
    private final CoroutineScope customScope;
    private CustomerPagerAdapter mAdapter;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private AppBarLayout mAppBar;
    public CoordinatorLayout mCoordinatorLayout;
    private int mCustomerCabinRef;
    private String mCustomerCode;
    CustomerDetail mCustomerDetail;
    private String mCustomerName;
    private String[] mCustomerTabName;
    private int mLogicalRef;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private int mShipRef;
    private TabLayout mTabLayout;
    private TabLayout.ViewPagerOnTabSelectedListener mTabSelectListener;
    private ViewPager mViewPager;
    private MenuItem menuItemCustomerCampaignPointPoll;
    private MenuItem menuItemCustomerUpdate;
    private final BaseCustomerRepository repository;
    final CustomerGeneralViewModel viewModel;

    public CustomerActivity() {
        CompletableJob Job;
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        this.repository = baseCustomerRepository;
        this.viewModel = new CustomerGeneralViewModel(baseCustomerRepository);
        MainCoroutineDispatcher main = Dispatchers.getMain();
        Job = JobKt.Job(null);
        this.customScope = CoroutineScopeKt.CoroutineScope(main.plus(Job ));
        this.mTabSelectListener = new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager) {             public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }
             public void onTabUnselected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

             public void onTabReselected(TabLayout.Tab tab) {
                Object current;
                AppBarLayout appBarLayout;
                Intrinsics.checkNotNullParameter(tab, "tab");
                current = CustomerActivity.this.getCurrent(Scrollable.class);
                Scrollable scrollable = (Scrollable) current;
                if (scrollable != null) {
                    scrollable.scrollToTop();
                }
                appBarLayout = CustomerActivity.this.mAppBar;
                if (appBarLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mAppBar");
                    appBarLayout = null;
                }
                appBarLayout.setExpanded(true, true);
            }
        };
    }

    public CoordinatorLayout getMCoordinatorLayout() {
        CoordinatorLayout coordinatorLayout = this.mCoordinatorLayout;
        if (coordinatorLayout != null) {
            return coordinatorLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCoordinatorLayout");
        return null;
    }

    public void setMCoordinatorLayout(CoordinatorLayout coordinatorLayout) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "<set-?>");
        this.mCoordinatorLayout = coordinatorLayout;
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

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public MenuItem getMenuItemCustomerUpdate() {
        return this.menuItemCustomerUpdate;
    }

    public void setMenuItemCustomerUpdate(MenuItem menuItem) {
        this.menuItemCustomerUpdate = menuItem;
    }

    public MenuItem getMenuItemCustomerCampaignPointPoll() {
        return this.menuItemCustomerCampaignPointPoll;
    }

    public void setMenuItemCustomerCampaignPointPoll(MenuItem menuItem) {
        this.menuItemCustomerCampaignPointPoll = menuItem;
    } 
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (Intrinsics.areEqual(intent != null ? Boolean.valueOf(intent.getBooleanExtra(IntentExtraName.EXTRA_CUSTOMER_REF_CHANGED, false)) : null, Boolean.TRUE)) {
            Intent intent2 = new Intent();
            intent2.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF_CHANGED, true);
            setResult(IntentExtraName.NEW_CUSTOMERREF_CHANGED_RESULT_CODE, intent2);
            finish();
        }
    }
     public void onCreate(Bundle bundle) {
        String string;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        ActivityCustomerBinding inflate = ActivityCustomerBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        setToolbar();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        ActivityCustomerBinding activityCustomerBinding = this.binding;
        if (activityCustomerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCustomerBinding = null;
        }
        TabLayout tabLayout = activityCustomerBinding.tabLayout;
        Intrinsics.checkNotNullExpressionValue(tabLayout, "tabLayout");
        this.mTabLayout = tabLayout;
        ActivityCustomerBinding activityCustomerBinding2 = this.binding;
        if (activityCustomerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCustomerBinding2 = null;
        }
        AppBarLayout appbar = activityCustomerBinding2.appbar;
        Intrinsics.checkNotNullExpressionValue(appbar, "appbar");
        this.mAppBar = appbar;
        ActivityCustomerBinding activityCustomerBinding3 = this.binding;
        if (activityCustomerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCustomerBinding3 = null;
        }
        CoordinatorLayout contentFrame = activityCustomerBinding3.contentFrame;
        Intrinsics.checkNotNullExpressionValue(contentFrame, "contentFrame");
        setMCoordinatorLayout(contentFrame);
        ActivityCustomerBinding activityCustomerBinding4 = this.binding;
        if (activityCustomerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCustomerBinding4 = null;
        }
        this.mViewPager = activityCustomerBinding4.viewPager;
        if (bundle != null) {
            this.mLogicalRef = bundle.getInt("state:customerRef");
            this.mCustomerCode = bundle.getString(STATE_CUSTOMER_CODE);
            this.mCustomerName = bundle.getString(STATE_CUSTOMER_NAME);
            this.mCustomerTabName = bundle.getStringArray(STATE_CUSTOMER_TAB_NAME);
            this.mCustomerDetail = bundle.getParcelable("state:customerDetail");
            this.mCustomerCabinRef = bundle.getInt(STATE_CUSTOMER_CABIN_REF);
        } else {
            Bundle extras = getIntent().getExtras();
            this.mLogicalRef = extras != null ? extras.getInt(EXTRA_CUSTOMER_REF, -1) : -1;
            Bundle extras2 = getIntent().getExtras();
            this.mCustomerName = extras2 != null ? extras2.getString(EXTRA_CUSTOMER_NAME, "NONAME") : null;
            this.mCustomerTabName = getResources().getStringArray(R.array.array_customer_tab);
            Bundle extras3 = getIntent().getExtras();
            this.mShipRef = extras3 != null ? extras3.getInt(EXTRA_CUSTOMER_SHIPREF, -1) : -1;
            Preferences.setRouteShipRef(ContextUtils.getmContext(), this.mShipRef);
            Bundle extras4 = getIntent().getExtras();
            this.mCustomerCabinRef = extras4 != null ? extras4.getInt(EXTRA_CUSTOMER_CABIN_REF, 0) : 0;
            Bundle extras5 = getIntent().getExtras();
            String str = "";
            if (extras5 != null && (string = extras5.getString(EXTRA_CUSTOMER_CODE, "")) != null) {
                str = string;
            }
            this.mCustomerCode = str;
        }
        CustomerDetail customerDetail = this.mCustomerDetail;
        if (customerDetail == null) {
            BuildersKt.launch(this.customScope, null, null, new CustomerActivityonCreate1(this, null), 3, null);
        } else {
            bindData(customerDetail);
        }
        showCustomerMarketingMessages();
    }

    public String getOneCustomerSql() {
        String sb = "SELECT S.CODE AS SSCODE, S.ADDR1 AS SSADDR1 ,S.ADDR2 AS SSADDR2 , S.CITY AS SSCITY , S.LOGICALREF AS SHIPREF, C.* FROM CLCARD C LEFT JOIN SHIPADDRESS S ON " +
                (this.viewModel.erpType() == ErpType.NETSIS ? " S.CODE = C.CODE " : " S.CLREF = C.LOGICALREF ") +
                "WHERE C.CODE='" +
                this.mCustomerCode +
                "' LIMIT 1 ";
        return sb;
    }

    private void showCustomerMarketingMessages() {
        Cursor rowQueryInReadableDatabase = this.viewModel.getRowQueryInReadableDatabase(getString(R.string.qry_markettingMessageList), null);
        if (rowQueryInReadableDatabase.getCount() > 0) {
            startActivity(MarketingMessageListActivity.class);
        }
        if (rowQueryInReadableDatabase.isClosed()) {
            return;
        }
        rowQueryInReadableDatabase.close();
    }
 
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        this.menuItemCustomerUpdate = menu.findItem(R.id.menu_update);
        this.menuItemCustomerCampaignPointPoll = menu.findItem(R.id.menu_poll_campaign_point_customer);
        if (menu.findItem(R.id.menu_photo) != null) {
            menu.findItem(R.id.menu_photo).setVisible(!Preferences.isDemo(this) && this.viewModel.erpType() != ErpType.NETSIS);
        }
        return super.onCreateOptionsMenu(menu);
    } 
    public boolean onPrepareOptionsMenu(Menu menu) {
        setMenuItem(isConnected(this));
        return super.onPrepareOptionsMenu(menu);
    } 
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.menu_update) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_customer_information_updating)).setCancelable(false).show();
            BuildersKt.launch(this.customScope, null, null, new CustomerActivityonOptionsItemSelected1(this, null));
        }
        if (item.getItemId() == R.id.menu_poll_campaign_point_customer) {
            this.mProgressDialogBuilder.setMessage(getString(R.string.type_get_customer_campaign_point)).show();
            this.viewModel.getCustomerCampaignPoint(this.mLogicalRef);
        }
        if (item.getItemId() == R.id.menu_open_location) {
            openCustomerLocation();
        }
        if (item.getItemId() == R.id.menu_photo) {
            this.viewModel.getCustomerSqlManager(this.mLogicalRef, new CustomerGetListener(this));
        }
        return super.onOptionsItemSelected(item);
    }

    private void openCustomerLocation() {
        if (PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", getString(R.string.str_location_permission_for_customer_on_map)) || PermissionUtils.checkPermission(this, "android.permission.ACCESS_COARSE_LOCATION", getString(R.string.str_location_permission_for_customer_on_map))) {
            Intent intent = new Intent(this, GpsInfoActivity.class);
            String str = GpsInfoActivity.EXTRA_CUSTOMER_REF;
            CustomerDetail customerDetail = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail);
            Intent putExtra = intent.putExtra(str, customerDetail.getLogicalRef());
            GpsInfoActivity.Companion companion = GpsInfoActivity.Companion;
            String extra_customer_title = companion.getEXTRA_CUSTOMER_TITLE();
            CustomerDetail customerDetail2 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail2);
            Intent putExtra2 = putExtra.putExtra(extra_customer_title, customerDetail2.getTitle());
            String extra_customer_code = companion.getEXTRA_CUSTOMER_CODE();
            CustomerDetail customerDetail3 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail3);
            startActivity(putExtra2.putExtra(extra_customer_code, customerDetail3.getCode()));
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
 
    public void baseResultEvent(BaseSelectResult<?> baseResult) {
        Intrinsics.checkNotNullParameter(baseResult, "baseResult");
        if (!isActivityDestroyed()) {
            this.mProgressDialogBuilder.dismiss();
        }
        if (baseResult.isSuccess()) {
            if (baseResult.getProcessType() == ProcessType.GETCLCARD) {
                Toast.makeText(this, getString(R.string.str_customer_information_updated), 1).show();
                return;
            }
            if (baseResult.getProcessType() == ProcessType.GETCUSTOMERCAMPAIGN) {
                if (baseResult.getDataList().size() > 0) {
                    List<?> dataList = baseResult.getDataList();
                    Intrinsics.checkNotNull(dataList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.CustomerCampaignPoint>");
                    showCustomerCampaignPoint(dataList);
                    return;
                }
                Toast.makeText(this, getString(R.string.str_customer_campaing_detail_not_found), 1).show();
                return;
            }
            return;
        }
        Toast.makeText(this, baseResult.getErrorString(), 1).show();
    }

    private void showCustomerCampaignPoint(List<CustomerCampaignPoint> list) {
        try {
            View inflate = LayoutInflater.from(this).inflate(R.layout.fichelist, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.str_customer_campaing_detail_title));
            builder.setView(inflate);
            View findViewById = inflate.findViewById(R.id.list_fiche_list);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
            ListView listView = (ListView) findViewById;
            String[] strArr = {"CODE", "NAME", "AMOUNT", "CAMPOINT"};
            int[] iArr = {R.id.FVCODE, R.id.FVNAME, R.id.FVAMOUNT, R.id.FVPOINT};
            ArrayList arrayList = new ArrayList();
            for (CustomerCampaignPoint customerCampaignPoint : list) {
                HashMap hashMap = new HashMap();
                hashMap.put("CODE", customerCampaignPoint.getCampaignCode());
                hashMap.put("NAME", customerCampaignPoint.getCampaignName());
                hashMap.put("AMOUNT", StringUtils.formatDouble(customerCampaignPoint.getAmount()));
                hashMap.put("CAMPOINT", StringUtils.formatDouble(customerCampaignPoint.getCampaignPoint()));
                arrayList.add(hashMap);
            }
            listView.setAdapter(new SimpleAdapter(this, arrayList, R.layout.campaign_info, strArr, iArr));
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { 
                public void onClick(DialogInterface dialogInterface, int i2) {
                    CustomerActivity.showCustomerCampaignPointlambda0(dialogInterface, i2);
                }
            });
            builder.show();
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "showCustomerCampaignPoint: ", e2);
        }
    }

    public static void showCustomerCampaignPointlambda0(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }
     public void onBackPressed() {
        ViewPager viewPager = this.mViewPager;
        Intrinsics.checkNotNull(viewPager);
        if (viewPager.getCurrentItem() == 2) {
            CustomerPagerAdapter customerPagerAdapter = this.mAdapter;
            Intrinsics.checkNotNull(customerPagerAdapter);
            ViewPager viewPager2 = this.mViewPager;
            Intrinsics.checkNotNull(viewPager2);
            ActivityResultCaller item = customerPagerAdapter.getItem(viewPager2.getCurrentItem());
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.proje.mobilesales.core.interfaces.FragmentBackHandler");
            if (((FragmentBackHandler) item).onBackPressedFragment()) {
                return;
            }
            checkAndCompleteAutomaticVisit();
            return;
        }
        checkAndCompleteAutomaticVisit();
    }

    private void checkAndCompleteAutomaticVisit() {
        CheckAutoVisitItem checkIsExistingAutoVisitToComplete = AutoVisitUtils.checkIsExistingAutoVisitToComplete(this.mLogicalRef);
        Intrinsics.checkNotNullExpressionValue(checkIsExistingAutoVisitToComplete, "checkIsExistingAutoVisitToComplete(...)");
        String str = this.mCustomerName;
        if (Intrinsics.areEqual(str, "")) {
            str = this.baseErp.getCustomerClCode(this.mLogicalRef);
        }
        if (checkIsExistingAutoVisitToComplete.isExisting()) {
            new AlertDialog.Builder(ContextUtils.getmContext()).setMessage(ContextUtils.getStringResource(R.string.str_visit_will_complete, str)).setPositiveButton(R.string.str_complete_visit, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.customer.view.general.CustomerActivityExternalSyntheticLambda1
                public final  CustomerActivity f1 = null;

                public void onClick(DialogInterface dialogInterface, int i2) {
                    CustomerActivity.checkAndCompleteAutomaticVisitlambda1(CheckAutoVisitItem.this, this0, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.str_form_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i2) {
                    CustomerActivity.checkAndCompleteAutomaticVisitlambda2(dialogInterface, i2);
                }
            }).create().show();
        } else {
            setResultForReturningToList();
            super.onBackPressed();
        }
    }

    public void checkAndCompleteAutomaticVisitlambda1(CheckAutoVisitItem checkAutoVisitItem, CustomerActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(checkAutoVisitItem, "checkAutoVisitItem");
        Intrinsics.checkNotNullParameter(this0, "this0");
        dialogInterface.dismiss();
        new AutoVisitUtils.finishAutoVisitWithWorProcess(checkAutoVisitItem.getAutoVisitId(), this0.mLogicalRef, false, new ResponseListener<Object>() { // from class: com.proje.mobilesales.features.customer.view.general.CustomerActivitycheckAndCompleteAutomaticVisit11
            void CustomerActivitycheckAndCompleteAutomaticVisit11() {
            }
            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                CustomerActivity.this.superOnBackPress();
            }
            public void onResponse(PrintSlipModel obj) {
                CustomerActivity.this.setResultForReturningToList();
                CustomerActivity.this.superOnBackPress();
            }
        }).execute();
    }

    public static void checkAndCompleteAutomaticVisitlambda2(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        Toast.makeText(ContextUtils.getmContext(), R.string.exp_91_error_complete_other_active_visit, 1).show();
    }

    public void superOnBackPress() {
        super.onBackPressed();
    }

    public void setResultForReturningToList() {
        Intent intent = new Intent();
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.mLogicalRef);
        setResult(IntentExtraName.BACK_TO_CUSTOMER_LIST_RESULT_CODE, intent);
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putInt("state:customerRef", this.mLogicalRef);
        outState.putString(STATE_CUSTOMER_CODE, this.mCustomerCode);
        outState.putString(STATE_CUSTOMER_NAME, this.mCustomerName);
        outState.putStringArray(STATE_CUSTOMER_TAB_NAME, this.mCustomerTabName);
        outState.putParcelable("state:customerDetail", this.mCustomerDetail);
        outState.putInt(STATE_CUSTOMER_CABIN_REF, this.mCustomerCabinRef);
        super.onSaveInstanceState(outState);
    }

    public <T> T getCurrent(Class<T> cls) {
        Fragment fragment;
        CustomerPagerAdapter customerPagerAdapter = this.mAdapter;
        if (customerPagerAdapter == null) {
            return null;
        }
        if (customerPagerAdapter != null) {
            ViewPager viewPager = this.mViewPager;
            Integer valueOf = viewPager != null ? Integer.valueOf(viewPager.getCurrentItem()) : null;
            Intrinsics.checkNotNull(valueOf);
            fragment = customerPagerAdapter.getItem(valueOf.intValue());
        } else {
            fragment = null;
        }
        if (cls.isInstance(fragment)) {
            return (T) fragment;
        }
        return null;
    }

    private void bindData(CustomerDetail customerDetail) {
        this.mCustomerDetail = customerDetail;
        if (customerDetail != null) {
            customerDetail.setShipRef(this.mShipRef);
            customerDetail.setRouteDayRef(this.routeDayRef);
            customerDetail.setRoutePlanRef(this.routePlanRef);
            customerDetail.setRouteUserCustomerRef(this.routeUserCustomerRef);
            customerDetail.setCabinRef(this.mCustomerCabinRef);
        }
        if (this.mCustomerDetail == null) {
            Snackbar.make(getMCoordinatorLayout(), getString(R.string.exp_26_customer_ref_not_found), 0).show();
            return;
        }
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            CustomerDetail customerDetail2 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail2);
            CustomerGeneralViewModel customerGeneralViewModel = this.viewModel;
            CustomerDetail customerDetail3 = this.mCustomerDetail;
            Intrinsics.checkNotNull(customerDetail3);
            customerDetail2.setPerson(customerGeneralViewModel.getCustomerInCharge(customerDetail3.getCode()));
        }
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        CustomerDetail customerDetail4 = this.mCustomerDetail;
        Intrinsics.checkNotNull(customerDetail4);
        String title = customerDetail4.getTitle();
        Intrinsics.checkNotNull(title);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String upperCase = title.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        supportActionBar.setTitle(upperCase);
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.divider));
        }
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            viewPager2.setPageMarginDrawable(R.color.blackT12);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        String[] strArr = this.mCustomerTabName;
        Intrinsics.checkNotNull(strArr);
        Intrinsics.checkNotNull(customerDetail);
        CustomerPagerAdapter customerPagerAdapter = new CustomerPagerAdapter(this, supportFragmentManager, strArr, customerDetail);
        this.mAdapter = customerPagerAdapter;
        ViewPager viewPager3 = this.mViewPager;
        if (viewPager3 != null) {
            viewPager3.setAdapter(customerPagerAdapter);
        }
        TabLayout tabLayout = this.mTabLayout;
        TabLayout tabLayout2 = null;
        if (tabLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTabLayout");
            tabLayout = null;
        }
        tabLayout.setupWithViewPager(this.mViewPager);
        TabLayout tabLayout3 = this.mTabLayout;
        if (tabLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTabLayout");
        } else {
            tabLayout2 = tabLayout3;
        }
        tabLayout2.addOnTabSelectedListener(this.mTabSelectListener);
        ViewPager viewPager4 = this.mViewPager;
        if (viewPager4 == null) {
            return;
        }
        viewPager4.setCurrentItem(2);
    }

    public void onNetworkConnectionChanged(boolean z) {
        setMenuItem(z);
    }

    private void setMenuItem(boolean z) {
        ContextUtils.setMenuItemOnlineVisible(this.menuItemCustomerUpdate, z);
        ContextUtils.setMenuItemOnlineVisible(this.menuItemCustomerCampaignPointPoll, z && this.viewModel.erpType() != ErpType.NETSIS);
    }

    public void onCustomerLoaded(CustomerDetail customerDetail) {
        this.mProgressDialogBuilder.dismiss();
        bindData(customerDetail);
    }
    public void onCustomerError() {
        Snackbar.make(getMCoordinatorLayout(), getString(R.string.exp_26_customer_ref_not_found), 0).show();
    }

    protected void onPostResume() {
        super.onPostResume();
    }

    static final class CustomerResponseListener implements ResponseListener<CustomerDetail> {
        private final WeakReference<CustomerActivity> mCustomerActivity;

        public CustomerResponseListener(CustomerActivity customerActivity) {
            this.mCustomerActivity = new WeakReference<>(customerActivity);
        }
        public void onResponse(PrintSlipModel customerDetail) {
            if (this.mCustomerActivity.get() != null) {
                CustomerActivity customerActivity = this.mCustomerActivity.get();
                Intrinsics.checkNotNull(customerActivity);
                if (customerActivity.isActivityDestroyed()) {
                    return;
                }
                CustomerActivity customerActivity2 = this.mCustomerActivity.get();
                Intrinsics.checkNotNull(customerActivity2);
                customerActivity2.onCustomerLoaded(customerDetail);
            }
        }

        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Log.d(MobileSales.TAG, "onError: " + errorMessage);
            if (this.mCustomerActivity.get() != null) {
                CustomerActivity customerActivity = this.mCustomerActivity.get();
                Intrinsics.checkNotNull(customerActivity);
                if (customerActivity.isActivityDestroyed()) {
                    return;
                }
                CustomerActivity customerActivity2 = this.mCustomerActivity.get();
                Intrinsics.checkNotNull(customerActivity2);
                customerActivity2.onCustomerError();
            }
        }
    }

    private record CustomerGetListener(
            WeakReference<CustomerActivity> mCustomerActivityWeakReference) implements ResponseListener<CustomerNew> {
            private CustomerGetListener(CustomerActivity mCustomerActivityWeakReference) {
                this.mCustomerActivityWeakReference = new WeakReference<>(mCustomerActivityWeakReference);
            }

        public void onResponse(PrintSlipModel customerNew) {
                if (this.mCustomerActivityWeakReference.get() != null) {
                    CustomerActivity customerActivity = this.mCustomerActivityWeakReference.get();
                    Intrinsics.checkNotNull(customerActivity);
                    if (customerActivity.isActivityDestroyed()) {
                        return;
                    }
                    CustomerActivity customerActivity2 = this.mCustomerActivityWeakReference.get();
                    Intrinsics.checkNotNull(customerActivity2);
                    customerActivity2.onCustomerGet(customerNew, "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mCustomerActivityWeakReference.get() != null) {
                    CustomerActivity customerActivity = this.mCustomerActivityWeakReference.get();
                    Intrinsics.checkNotNull(customerActivity);
                    if (customerActivity.isActivityDestroyed()) {
                        return;
                    }
                    CustomerActivity customerActivity2 = this.mCustomerActivityWeakReference.get();
                    Intrinsics.checkNotNull(customerActivity2);
                    customerActivity2.onCustomerGet(null, errorMessage);
                }
            }
        }
    public void onCustomerGet(CustomerNew customerNew, String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, 1).show();
        }
        if (customerNew != null) {
            openNewCustomer(customerNew);
        } else {
            Toast.makeText(this, R.string.exp_18_database_read_error, 1).show();
        }
    }
    private void openNewCustomer(CustomerNew customerNew) {
        Intent intent = new Intent(this, CustomerNewActivity.class);
        intent.putExtra(CustomerNewActivity.EXTRA_ADD_PHOTO, true);
        intent.putExtra(CustomerNewActivity.EXTRA_CUSTOMER, customerNew);
        startActivity(intent);
    }
    public TabLayout.ViewPagerOnTabSelectedListener getMTabSelectListener() {
        return this.mTabSelectListener;
    }
    public void setMTabSelectListener(TabLayout.ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener) {
        Intrinsics.checkNotNullParameter(viewPagerOnTabSelectedListener, "<set-?>");
        this.mTabSelectListener = viewPagerOnTabSelectedListener;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
