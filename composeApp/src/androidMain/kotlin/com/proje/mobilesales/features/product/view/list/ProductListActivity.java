package com.proje.mobilesales.features.product.view.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProductListShowType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.FragmentBackHandler;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.model.BarcodeResult;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
 
public final class ProductListActivity extends BaseListActivity {
    public static final String EXTRA_BARCODE_FILTER;
    public static final String EXTRA_CUSTOMER_REF;
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
    private static final String productListActivityClassName;
    private ArrayList<BarcodeResult> mBarcodeFilter;
    private int mCustomerRef;
    private int mDefOrderId;
    private int mDivisionNr;
    private String mFilter;
    private int mPaymentRef;
    private String mPaymentTradeGroup;
    private String mProductGroupCode;
    private boolean mProductSelectType;
    private int mReportItemRef;
    private SalesType mSalesType;
    private int mSelectedProductSize;
    private int mSourceWareHouseNr;
    private String mSpecode;
    private int mWareHouseNr;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_FILTER = "state:filter";
    private static final String STATE_BARCODE_FILTER = "state:barcodeFilter";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_PRODUCT_SELECT_TYPE = "state:productSelectType";
    private static final String STATE_SALES_TYPE = "state:salesType";
    private static final String STATE_WAREHOUSE_NR = "state:wareHouseNr";
    private static final String STATE_DEF_ORDER_ID = "state:defOrderId";
    private static final String STATE_PAYMENT_TRADE_GROUP = "state:paymentTradeGroup";
    private static final String STATE_PAYMENT_REF = "state:paymentRef";
    private static final String STATE_SELECTED_PRODUCT_SIZE = "state:selectedProductSize";
    private static final String STATE_ITEM_ID = "state:itemId";
    private static final String STATE_PRODUCT_GROUP_CODE = "state:productGroupCode";
    private static final String STATE_DIVISION_NR = "state:divisionNr";
    private ProductListShowType mLastType = ProductListShowType.PRODUCT;
    private String mLastFilter = "";
    public boolean isSearchable() {
        return false;
    }
    public ArrayList<BarcodeResult> getMBarcodeFilter() {
        return this.mBarcodeFilter;
    }
    public void setMBarcodeFilter(ArrayList<BarcodeResult> arrayList) {
        this.mBarcodeFilter = arrayList;
    }
    public ProductListShowType getMLastType() {
        return this.mLastType;
    }
    public void setMLastType(ProductListShowType productListShowType) {
        Intrinsics.checkNotNullParameter(productListShowType, "<set-?>");
        this.mLastType = productListShowType;
    }
    public String getMLastFilter() {
        return this.mLastFilter;
    }
    public void setMLastFilter(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mLastFilter = str;
    }
    public int getMReportItemRef() {
        return this.mReportItemRef;
    }
    public void setMReportItemRef(int i) {
        this.mReportItemRef = i;
    }
    public String getMPaymentTradeGroup() {
        return this.mPaymentTradeGroup;
    }
    public void setMPaymentTradeGroup(String str) {
        this.mPaymentTradeGroup = str;
    }
    @SuppressLint("ResourceType")
    public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.PRODUCTS;
        super.onCreate(bundle);
        if (bundle != null) {
            this.mFilter = bundle.getString(STATE_FILTER);
            this.mProductSelectType = bundle.getBoolean(STATE_PRODUCT_SELECT_TYPE);
            this.mSalesType = SalesType.Companion.fromSalesType(bundle.getInt(STATE_SALES_TYPE, -1));
            this.mWareHouseNr = bundle.getInt(STATE_WAREHOUSE_NR);
            this.mDefOrderId = bundle.getInt(STATE_DEF_ORDER_ID, 0);
            this.mCustomerRef = bundle.getInt(STATE_CUSTOMER_REF, 0);
            this.mPaymentTradeGroup = bundle.getString(STATE_PAYMENT_TRADE_GROUP, "");
            this.mPaymentRef = bundle.getInt(STATE_PAYMENT_REF, 0);
            this.mBarcodeFilter = bundle.getParcelableArrayList(STATE_BARCODE_FILTER);
            this.mSelectedProductSize = bundle.getInt(STATE_SELECTED_PRODUCT_SIZE, 0);
            this.mReportItemRef = bundle.getInt(STATE_ITEM_ID, 0);
            this.mProductGroupCode = bundle.getString(STATE_PRODUCT_GROUP_CODE, "");
            this.mDivisionNr = bundle.getInt(STATE_DIVISION_NR, -99);
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            String str = BaseListActivity.LIST_FRAGMENT_TAG;
            if (supportFragmentManager.findFragmentByTag(str) == null) {
                getSupportFragmentManager().beginTransaction().replace(16908298, instantiateListFragment(), str).commit();
                return;
            }
            return;
        }
        this.mProductSelectType = getIntent().getBooleanExtra(EXTRA_PRODUCT_SELECT_TYPE, false);
        SalesType salesType = (SalesType) getIntent().getSerializableExtra(EXTRA_SALES_TYPE);
        this.mSalesType = salesType;
        if (salesType == null) {
            this.mSalesType = SalesType.FREE;
        }
        this.mWareHouseNr = getIntent().getIntExtra(EXTRA_WAREHOUSE_NR, 0);
        this.mSourceWareHouseNr = getIntent().getIntExtra(EXTRA_SOURCE_WAREHOUSE_NR, 0);
        this.mFilter = getIntent().getStringExtra(EXTRA_FILTER);
        this.mDefOrderId = getIntent().getIntExtra(EXTRA_DEF_ORDER_ID, 0);
        this.mCustomerRef = getIntent().getIntExtra(EXTRA_CUSTOMER_REF, 0);
        this.mPaymentTradeGroup = getIntent().getStringExtra(EXTRA_PAYMENT_TRADE_GROUP);
        this.mPaymentRef = getIntent().getIntExtra(EXTRA_PAYMENT_REF, 0);
        this.mBarcodeFilter = getIntent().getParcelableArrayListExtra(EXTRA_BARCODE_FILTER);
        this.mSelectedProductSize = getIntent().getIntExtra(EXTRA_SELECTED_PRODUCT_SIZE, 0);
        this.mReportItemRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ITEM_ID, 0);
        this.mProductGroupCode = getIntent().getStringExtra(EXTRA_PRODUCT_GROUP_CODE);
        this.mDivisionNr = getIntent().getIntExtra(EXTRA_DIVISION_NR, -99);
        this.mSpecode = getIntent().getStringExtra(EXTRA_SPECODE);
        controlReportItemRef();
        getSupportFragmentManager().beginTransaction().replace(16908298, instantiateListFragment(), BaseListActivity.LIST_FRAGMENT_TAG).commit();
    }
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
    private void setTitleForItemReports() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntryAt = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
            Intrinsics.checkNotNullExpressionValue(backStackEntryAt, "getBackStackEntryAt(...)");
            if (backStackEntryAt.getName() != null && StringsKt.equals(backStackEntryAt.getName(), "ITEM REPORTS", false)) {
                ActionBar supportActionBar = getSupportActionBar();
                Intrinsics.checkNotNull(supportActionBar);
                supportActionBar.setTitle(getString(R.string.str_stock_reports));
            }
        }
    }
    private boolean fragmentHandle() {
        setTitleForItemReports();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
        if (findFragmentByTag != null) {
            return ((FragmentBackHandler) findFragmentByTag).onBackPressedFragment();
        }
        return false;
    }
    public String getDefaultTitle() {
        String string = getString(R.string.activity_title_product);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
    protected Fragment instantiateListFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(ProductListFragment.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        bundle.putString(ProductListFragment.EXTRA_FILTER, this.mFilter);
        bundle.putBoolean(ProductListFragment.EXTRA_PRODUCT_SELECT_TYPE, this.mProductSelectType);
        String str = ProductListFragment.EXTRA_SALES_TYPE;
        SalesType salesType = this.mSalesType;
        Intrinsics.checkNotNull(salesType);
        bundle.putInt(str, salesType.getmValue());
        bundle.putInt(ProductListFragment.EXTRA_WAREHOUSE_NR, this.mWareHouseNr);
        bundle.putInt(ProductListFragment.EXTRA_SOURCE_WAREHOUSE_NR, this.mSourceWareHouseNr);
        bundle.putInt(ProductListFragment.EXTRA_DEF_ORDER_ID, this.mDefOrderId);
        bundle.putString(ProductListFragment.EXTRA_PAYMENT_TRADE_GROUP, this.mPaymentTradeGroup);
        bundle.putInt(ProductListFragment.EXTRA_PAYMENT_REF, this.mPaymentRef);
        bundle.putParcelableArrayList(ProductListFragment.EXTRA_BARCODE_FILTER, this.mBarcodeFilter);
        bundle.putInt(ProductListFragment.EXTRA_SELECTED_PRODUCT_SIZE, this.mSelectedProductSize);
        bundle.putInt(IntentExtraName.EXTRA_ITEM_ID, this.mReportItemRef);
        bundle.putString(ProductListFragment.EXTRA_PRODUCT_GROUP_CODE, this.mProductGroupCode);
        bundle.putInt(ProductListFragment.EXTRA_DIVISION_NR, this.mDivisionNr);
        bundle.putString(ProductListFragment.EXTRA_SPECODE, this.mSpecode);
        Fragment instantiate = Fragment.instantiate(this, ProductListFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putString(STATE_FILTER, this.mFilter);
        bundle.putBoolean(STATE_PRODUCT_SELECT_TYPE, this.mProductSelectType);
        String str = STATE_SALES_TYPE;
        SalesType salesType = this.mSalesType;
        Intrinsics.checkNotNull(salesType);
        bundle.putInt(str, salesType.getmValue());
        bundle.putInt(STATE_WAREHOUSE_NR, this.mWareHouseNr);
        bundle.putInt(STATE_DEF_ORDER_ID, this.mDefOrderId);
        bundle.putInt(STATE_CUSTOMER_REF, this.mCustomerRef);
        bundle.putString(STATE_PAYMENT_TRADE_GROUP, this.mPaymentTradeGroup);
        bundle.putInt(STATE_PAYMENT_REF, this.mPaymentRef);
        bundle.putParcelableArrayList(STATE_BARCODE_FILTER, this.mBarcodeFilter);
        bundle.putInt(STATE_SELECTED_PRODUCT_SIZE, this.mSelectedProductSize);
        bundle.putInt(STATE_ITEM_ID, this.mReportItemRef);
        bundle.putString(STATE_PRODUCT_GROUP_CODE, this.mProductGroupCode);
        bundle.putInt(STATE_DIVISION_NR, this.mDivisionNr);
        super.onSaveInstanceState(bundle);
    }
    private void controlReportItemRef() {
        int i = this.mReportItemRef;

        if (i == 0) {
            return;
        }
        this.mReportItemRef = i;
    }
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Intrinsics.checkNotNull(intent);
        if (intent.hasExtra("query")) {
            String stringExtra = intent.getStringExtra("query");
            this.mFilter = stringExtra;
            if (TextUtils.equals(stringExtra, "")) {
                this.mFilter = null;
            }
            ActionBar supportActionBar = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar);
            supportActionBar.setSubtitle(this.mFilter);
            ProductListFragment productListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag(BaseListActivity.LIST_FRAGMENT_TAG);
            if (productListFragment != null) {
                String str = this.mFilter;
                Intrinsics.checkNotNull(str);
                productListFragment.filter(str);
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public void onBackPressed() {
        if (fragmentHandle()) {
            super.onBackPressed();
        }
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    static {
        String name = ProductListActivity.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        productListActivityClassName = name;
        EXTRA_FILTER = name + ".EXTRA_FILTER";
        EXTRA_BARCODE_FILTER = name + ".EXTRA_BARCODE_FILTER";
        EXTRA_CUSTOMER_REF = name + ".EXTRA_CUSTOMER_REF";
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
