package com.proje.mobilesales.features.sales.view.detail;

import android.R;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.UnitPriceFormatter;
import com.proje.mobilesales.databinding.ActivityListBinding;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDateProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.product.view.detail.ProductDetailFragment;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;

public final class SalesDetailActivity extends BaseFicheActivity {
    public static final int DELETE_PRODUCT = 104;
    private static final String STATE_BRANCH_REF = "state:mBranchRef";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_FACTORY_REF = "state:mFactoryRef";
    private static final String STATE_FICHE_DETAIL_FIELDS = "state:ficheDetailFields";
    private static final String STATE_FICHE_USER_RIGHTS = "state:ficheDetailUserRights";
    private static final String STATE_HEADER_FIRSTDISCOUNT_CARD_CODE = "state:mHeaderFirstDiscountCardCode";
    private static final String STATE_NOT_USE_GATTRIBKDV = "state:mIsNotUseGattribKdv";
    private static final String STATE_PAYMENT_REF = "state:mPaymentRef";
    private static final String STATE_PROJECT_REF = "state:mProjectRef";
    private static final String STATE_SALES_CREATED_DATE = "state:mSalesCreatedDate";
    private static final String STATE_SALES_DETAIL = "state:salesDetail";
    private static final String STATE_SALES_DETAIL_LIST_SIZE = "state:salesDetailListSize";
    private static final String STATE_SALES_DETAIL_POSITION = "state:salesDetailPosition";
    private static final String STATE_SALES_FICHE_MODE = "state:salesFicheMode";
    private static final String STATE_SALES_WITHOUT_DETAILS = "state:mSalesWithoutDetails";
    private static final String STATE_TRADE_GROUP = "state:mTradeGroup";
    private static final String STATE_VAT_CHANGE = "state:mVatCanBeChange";
    private static final String STATE_VAT_DEFAULT = "state:mVatDefaultChecked";
    private static final String STATE_WAREHOUSE_NR = "state:wareHouseNr";
    private ActivityListBinding binding;
    private int mBranchRef;
    private int mFactoryRef;
    private FicheMode mFicheMode;
    private String mHeaderFirstDiscountCardCode;
    private boolean mIsNotUseGattribKdv;
    private String mSalesCreatedDate;
    private SalesDetail mSalesDetail;
    private boolean mSalesDetailListSize;
    private int mSalesDetailPosition;
    private SalesFicheDetailFields mSalesFicheDetailFields;
    private boolean mVatCanBeChange;
    private boolean mVatDefaultChecked;
    private int mWareHouseNr;
    private int paymentRef;
    private int projectRef;
    private final SalesDetailRepository repository;
    private SalesFicheUserRights salesFicheUserRights;
    private Sales salesWithoutDetails;
    private String tradeGroup;
    private final SalesDetailViewModel viewModel;
    public static final Companion Companion = new Companion(null);
    public static final String SALES_DETAIL_FRAGMENT_TAG = SalesDetailActivity.class.getName() + ".SALES_DETAIL_FRAGMENT_TAG";
    public static final String EXTRA_SALES_DETAIL = SalesDetailActivity.class.getName() + ".EXTRA_SALES_DETAIL";
    public static final String EXTRA_SALES_DETAIL_POSITION = SalesDetailActivity.class.getName() + ".EXTRA_SALES_DETAIL_POSITION";
    public static final String EXTRA_CUSTOMER_REF = SalesDetailActivity.class.getName() + ".EXTRA_CUSTOMER_REF";
    public static final String EXTRA_SALES_DETAIL_LIST_SIZE = SalesDetailActivity.class.getName() + ".EXTRA_SALES_DETAIL_LIST_SIZE";
    public static final String EXTRA_FICHE_DETAIL_FIELDS = SalesDetailActivity.class.getName() + ".EXTRA_FICHE_DETAIL_FIELDS";
    public static final String EXTRA_FICHE_USER_RIGHTS = SalesDetailActivity.class.getName() + ".EXTRA_FICHE_USER_RIGHTS";
    public static final String EXTRA_SALES_FICHE_MODE = SalesDetailActivity.class.getName() + ".EXTRA_SALES_FICHE_MODE";
    public static final String EXTRA_WAREHOUSE_NR = SalesDetailActivity.class.getName() + ".EXTRA_WAREHOUSE_NR";
    public static final String EXTRA_PAYMENT_REF = SalesDetailActivity.class.getName() + ".EXTRA_PAYMENT_REF";
    public static final String EXTRA_PROJECT_REF = SalesDetailActivity.class.getName() + ".EXTRA_PROJECT_REF";
    public static final String EXTRA_TRADE_GROUP = SalesDetailActivity.class.getName() + ".EXTRA_TRADE_GROUP";
    public static final String EXTRA_VAT_CHANGE = SalesDetailActivity.class.getName() + ".EXTRA_VAT_CHANGE";
    public static final String EXTRA_VAT_DEFAULT = SalesDetailActivity.class.getName() + ".EXTRA_VAT_DEFAULT";
    public static final String EXTRA_NOT_USE_GATTRIBKDV = SalesDetailActivity.class.getName() + ".EXTRA_NOT_USE_GATTRIBKDV";
    public static final String EXTRA_SALES_CREATED_DATE = SalesDetailActivity.class.getName() + ".EXTRA_SALES_CREATED_DATE";
    public static final String EXTRA_FACTORY_REF = SalesDetailActivity.class.getName() + ".EXTRA_FACTORY_REF";
    public static final String EXTRA_BRANCH_REF = SalesDetailActivity.class.getName() + ".EXTRA_BRANCH_REF";
    public static final String EXTRA_HEADER_FIRSTDISCOUNT_CARD_CODE = SalesDetailActivity.class.getName() + ".EXTRA_HEADER_FIRSTDISCOUNT_CARD_CODE";
    public static final String EXTRA_SALES_WITHOUT_DETAILS = SalesDetailActivity.class.getName() + ".EXTRA_SALES_WITHOUT_DETAILS";
    private boolean checkNull(Object obj) {
        return obj == null;
    }
    protected void initFiche() {
    }
    protected void saveFiche() {
    }
    public SalesDetailActivity() {
        SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        this.repository = salesDetailRepository;
        this.viewModel = new SalesDetailViewModel(salesDetailRepository);
        this.mSalesDetail = new SalesDetail(0L, 0, 0, null, 0, 0, null, 0, false, false, 0, 0, null, 0, 0, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0, null, 0.0d, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, 0, false, 0, 0.0d, 0.0d, 0, null, 0, 0, 0, 0, false, null, null, 0, false, null, null, null, 0, null, -1, -1, Integer.MAX_VALUE, null);
        this.mSalesFicheDetailFields = new SalesFicheDetailFields();
        this.salesFicheUserRights = new SalesFicheUserRights(false, false, false, false, false, false, false, false, false, false, false, 0, 0, 0, 0, false, false, false, false, 0, false, false, false, false, false, false, false, false, 268435455, null);
        this.mFicheMode = FicheMode.ANALYSE;
        this.salesWithoutDetails = new Sales(0, 0, null, 0, null, null, 0, 0, null, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, 0, null, false, false, null, null, null, null, null, null, null, null, null, 0.0d, 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null, null, null, -1, -1, -1, 1, null);
    }
    public SalesDetail getMSalesDetail() {
        return this.mSalesDetail;
    }
    public void setMSalesDetail(SalesDetail salesDetail) {
        Intrinsics.checkNotNullParameter(salesDetail, "<set-?>");
        this.mSalesDetail = salesDetail;
    }
    public SalesFicheDetailFields getMSalesFicheDetailFields() {
        return this.mSalesFicheDetailFields;
    }
    public void setMSalesFicheDetailFields(SalesFicheDetailFields salesFicheDetailFields) {
        Intrinsics.checkNotNullParameter(salesFicheDetailFields, "<set-?>");
        this.mSalesFicheDetailFields = salesFicheDetailFields;
    }
    public SalesFicheUserRights getSalesFicheUserRights() {
        return this.salesFicheUserRights;
    }
    public void setSalesFicheUserRights(SalesFicheUserRights salesFicheUserRights) {
        Intrinsics.checkNotNullParameter(salesFicheUserRights, "<set-?>");
        this.salesFicheUserRights = salesFicheUserRights;
    }
    public int getMSalesDetailPosition() {
        return this.mSalesDetailPosition;
    }
    public void setMSalesDetailPosition(int i2) {
        this.mSalesDetailPosition = i2;
    }
    public boolean getMSalesDetailListSize() {
        return this.mSalesDetailListSize;
    }
    public void setMSalesDetailListSize(boolean z) {
        this.mSalesDetailListSize = z;
    }
    public FicheMode getMFicheMode() {
        return this.mFicheMode;
    }
    public void setMFicheMode(FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(ficheMode, "<set-?>");
        this.mFicheMode = ficheMode;
    }
    public int getMWareHouseNr() {
        return this.mWareHouseNr;
    }
    public void setMWareHouseNr(int i2) {
        this.mWareHouseNr = i2;
    }
    public int getPaymentRef() {
        return this.paymentRef;
    }
    public void setPaymentRef(int i2) {
        this.paymentRef = i2;
    }
    public String getTradeGroup() {
        return this.tradeGroup;
    }
    public void setTradeGroup(String str) {
        this.tradeGroup = str;
    }
    public int getProjectRef() {
        return this.projectRef;
    }
    public void setProjectRef(int i2) {
        this.projectRef = i2;
    }
    public boolean getMVatCanBeChange() {
        return this.mVatCanBeChange;
    }
    public void setMVatCanBeChange(boolean z) {
        this.mVatCanBeChange = z;
    }
    public boolean getMVatDefaultChecked() {
        return this.mVatDefaultChecked;
    }
    public void setMVatDefaultChecked(boolean z) {
        this.mVatDefaultChecked = z;
    }
    public boolean getMIsNotUseGattribKdv() {
        return this.mIsNotUseGattribKdv;
    }
    public void setMIsNotUseGattribKdv(boolean z) {
        this.mIsNotUseGattribKdv = z;
    }
    public String getMSalesCreatedDate() {
        return this.mSalesCreatedDate;
    }
    public void setMSalesCreatedDate(String str) {
        this.mSalesCreatedDate = str;
    }
    public int getMFactoryRef() {
        return this.mFactoryRef;
    }
    public void setMFactoryRef(int i2) {
        this.mFactoryRef = i2;
    }
    public int getMBranchRef() {
        return this.mBranchRef;
    }
    public void setMBranchRef(int i2) {
        this.mBranchRef = i2;
    }
    public String getMHeaderFirstDiscountCardCode() {
        return this.mHeaderFirstDiscountCardCode;
    }
    public void setMHeaderFirstDiscountCardCode(String str) {
        this.mHeaderFirstDiscountCardCode = str;
    }
    public Sales getSalesWithoutDetails() {
        return this.salesWithoutDetails;
    }
    public void setSalesWithoutDetails(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "<set-?>");
        this.salesWithoutDetails = sales;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        ActivityListBinding inflate = ActivityListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        setToolbar();
        if (bundle != null) {
            this.customerRef = bundle.getInt("state:customerRef");
            Parcelable parcelable = bundle.getParcelable(STATE_SALES_DETAIL);
            Intrinsics.checkNotNull(parcelable);
            this.mSalesDetail = (SalesDetail) parcelable;
            Parcelable parcelable2 = bundle.getParcelable(STATE_FICHE_DETAIL_FIELDS);
            Intrinsics.checkNotNull(parcelable2);
            this.mSalesFicheDetailFields = (SalesFicheDetailFields) parcelable2;
            this.mSalesDetailPosition = bundle.getInt(STATE_SALES_DETAIL_POSITION);
            Parcelable parcelable3 = bundle.getParcelable(STATE_FICHE_USER_RIGHTS);
            Intrinsics.checkNotNull(parcelable3);
            this.salesFicheUserRights = (SalesFicheUserRights) parcelable3;
            this.mSalesDetailListSize = bundle.getBoolean(STATE_SALES_DETAIL_LIST_SIZE);
            this.mFicheMode = FicheMode.values()[bundle.getInt(STATE_SALES_FICHE_MODE, 0)];
            this.mWareHouseNr = bundle.getInt(STATE_WAREHOUSE_NR);
            this.paymentRef = bundle.getInt(STATE_PAYMENT_REF);
            this.projectRef = bundle.getInt(STATE_PROJECT_REF);
            this.tradeGroup = bundle.getString(STATE_TRADE_GROUP);
            this.mVatCanBeChange = bundle.getBoolean(STATE_VAT_CHANGE);
            this.mVatDefaultChecked = bundle.getBoolean(STATE_VAT_DEFAULT);
            this.mIsNotUseGattribKdv = bundle.getBoolean(STATE_NOT_USE_GATTRIBKDV);
            this.mSalesCreatedDate = bundle.getString(STATE_SALES_CREATED_DATE);
            this.mFactoryRef = bundle.getInt(STATE_FACTORY_REF);
            this.mBranchRef = bundle.getInt(STATE_BRANCH_REF);
            this.mHeaderFirstDiscountCardCode = bundle.getString(STATE_HEADER_FIRSTDISCOUNT_CARD_CODE);
            Parcelable parcelable4 = bundle.getParcelable(STATE_SALES_WITHOUT_DETAILS);
            Intrinsics.checkNotNull(parcelable4);
            this.salesWithoutDetails = (Sales) parcelable4;
            return;
        }
        this.customerRef = getIntent().getIntExtra(EXTRA_CUSTOMER_REF, 0);
        Parcelable parcelableExtra = getIntent().getParcelableExtra(EXTRA_SALES_DETAIL);
        Intrinsics.checkNotNull(parcelableExtra);
        this.mSalesDetail = (SalesDetail) parcelableExtra;
        Parcelable parcelableExtra2 = getIntent().getParcelableExtra(EXTRA_FICHE_DETAIL_FIELDS);
        Intrinsics.checkNotNull(parcelableExtra2);
        this.mSalesFicheDetailFields = (SalesFicheDetailFields) parcelableExtra2;
        this.mSalesDetailPosition = getIntent().getIntExtra(EXTRA_SALES_DETAIL_POSITION, 0);
        Parcelable parcelableExtra3 = getIntent().getParcelableExtra(EXTRA_FICHE_USER_RIGHTS);
        Intrinsics.checkNotNull(parcelableExtra3);
        this.salesFicheUserRights = (SalesFicheUserRights) parcelableExtra3;
        this.mSalesDetailListSize = getIntent().getBooleanExtra(EXTRA_SALES_DETAIL_LIST_SIZE, false);
        Serializable serializableExtra = getIntent().getSerializableExtra(EXTRA_SALES_FICHE_MODE);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.proje.mobilesales.core.enums.FicheMode");
        this.mFicheMode = (FicheMode) serializableExtra;
        this.mWareHouseNr = getIntent().getIntExtra(EXTRA_WAREHOUSE_NR, -1);
        this.paymentRef = getIntent().getIntExtra(EXTRA_PAYMENT_REF, 0);
        this.projectRef = getIntent().getIntExtra(EXTRA_PROJECT_REF, 0);
        this.tradeGroup = getIntent().getStringExtra(EXTRA_TRADE_GROUP);
        this.mVatCanBeChange = getIntent().getBooleanExtra(EXTRA_VAT_CHANGE, false);
        this.mVatDefaultChecked = getIntent().getBooleanExtra(EXTRA_VAT_DEFAULT, false);
        this.mIsNotUseGattribKdv = getIntent().getBooleanExtra(EXTRA_NOT_USE_GATTRIBKDV, false);
        this.mSalesCreatedDate = getIntent().getStringExtra(EXTRA_SALES_CREATED_DATE);
        this.mFactoryRef = getIntent().getIntExtra(EXTRA_FACTORY_REF, 0);
        this.mBranchRef = getIntent().getIntExtra(EXTRA_BRANCH_REF, 0);
        this.mHeaderFirstDiscountCardCode = getIntent().getStringExtra(EXTRA_HEADER_FIRSTDISCOUNT_CARD_CODE);
        Parcelable parcelableExtra4 = getIntent().getParcelableExtra(EXTRA_SALES_WITHOUT_DETAILS);
        Intrinsics.checkNotNull(parcelableExtra4);
        this.salesWithoutDetails = (Sales) parcelableExtra4;
        if (this.mFicheMode == FicheMode.NEW) {
            initSalesDetail();
            initPrice();
            initReserve();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.list, instantiateSalesDetailFragment(), SALES_DETAIL_FRAGMENT_TAG).commit();
    }
    private void initSalesDetail() {
        if (checkNull(this.mSalesDetail.getUnit())) {
            this.mSalesDetail.setUnit(new FicheDiscountRefProp());
        }
        if (checkNull(this.mSalesDetail.getSelectedPrice())) {
            this.mSalesDetail.setSelectedPrice(new FicheDiscountRefProp());
        }
        if (checkNull(this.mSalesDetail.getSelectedPriceType())) {
            this.mSalesDetail.setSelectedPriceType(new FicheRefProp());
        }
        if (checkNull(this.mSalesDetail.getPromotion())) {
            this.mSalesDetail.setPromotion(new FicheBooleanProp());
        }
        if (checkNull(this.mSalesDetail.getVariant())) {
            this.mSalesDetail.setVariant(new FicheDiscountRefProp());
        }
        if (checkNull(this.mSalesDetail.getVat())) {
            this.mSalesDetail.setVat(new FicheStringProp());
        }
        if (checkNull(this.mSalesDetail.getIncludeVat())) {
            this.mSalesDetail.setIncludeVat(new FicheBooleanProp());
        }
        if (checkNull(this.mSalesDetail.getDeliveryDate())) {
            this.mSalesDetail.setDeliveryDate(new FicheDateProp());
        }
        if (checkNull(this.mSalesDetail.getReserve())) {
            this.mSalesDetail.setReserve(new FicheBooleanProp());
        }
        if (checkNull(this.mSalesDetail.getPayment())) {
            this.mSalesDetail.setPayment(new FicheDiscountRefProp());
        }
        if (checkNull(this.mSalesDetail.getSpeCode())) {
            this.mSalesDetail.setSpeCode(new FicheRefProp());
        }
        if (checkNull(this.mSalesDetail.getDeliveryCode())) {
            this.mSalesDetail.setDeliveryCode(new FicheRefProp());
        }
        if (checkNull(this.mSalesDetail.getExplanation())) {
            this.mSalesDetail.setExplanation(new FicheStringProp());
        }
        if (checkNull(this.mSalesDetail.getDueDate())) {
            this.viewModel.setDueDateForSalesDetail(this.mSalesDetail, this.mCustomerRef);
        }
    }
    private void initReserve() {
        if (SalesUtils.isSalesTypeOrderOrDemand(this.mSalesDetail.getmSalesType()) && TextUtils.isEmpty(this.mSalesDetail.getReserve().toString())) {
            if (this.salesFicheUserRights.isReserve()) {
                this.mSalesDetail.getReserve().setSelect(true);
                FicheStringProp.setDefinition(getString(R.string.str_yes));
            } else {
                this.mSalesDetail.getReserve().setSelect(false);
                FicheStringProp.setDefinition(getString(R.string.str_no));
            }
        }
    }
    private Fragment instantiateSalesDetailFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(SalesDetailFragment.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        Fragment instantiate = Fragment.instantiate(this, SalesDetailFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(instantiate, "instantiate(...)");
        return instantiate;
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putParcelable(STATE_SALES_DETAIL, this.mSalesDetail);
        outState.putInt("state:customerRef", this.customerRef);
        outState.putParcelable(STATE_FICHE_DETAIL_FIELDS, this.mSalesFicheDetailFields);
        outState.putInt(STATE_SALES_DETAIL_POSITION, this.mSalesDetailPosition);
        outState.putParcelable(STATE_FICHE_USER_RIGHTS, this.salesFicheUserRights);
        outState.putSerializable(STATE_SALES_FICHE_MODE, this.mFicheMode);
        outState.putInt(STATE_WAREHOUSE_NR, this.mWareHouseNr);
        outState.putInt(STATE_PAYMENT_REF, this.paymentRef);
        outState.putString(STATE_TRADE_GROUP, this.tradeGroup);
        outState.putInt(STATE_PROJECT_REF, this.projectRef);
        outState.putBoolean(STATE_VAT_CHANGE, this.mVatCanBeChange);
        outState.putBoolean(STATE_VAT_DEFAULT, this.mVatDefaultChecked);
        outState.putString(STATE_SALES_CREATED_DATE, this.mSalesCreatedDate);
        outState.putInt(STATE_FACTORY_REF, this.mFactoryRef);
        outState.putInt(STATE_BRANCH_REF, this.mBranchRef);
        outState.putString(STATE_HEADER_FIRSTDISCOUNT_CARD_CODE, this.mHeaderFirstDiscountCardCode);
        super.onSaveInstanceState(outState);
    }
    private SalesDetailFragment getSalesDetailFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        return (SalesDetailFragment) supportFragmentManager.findFragmentByTag(SALES_DETAIL_FRAGMENT_TAG);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (this.mFicheMode != FicheMode.ANALYSE) {
            getMenuInflater().inflate(R.menu.menu_sales_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finishProduct(-1);
        }
        if (item.getItemId() == R.id.menu_product_information) {
            Bundle bundle = new Bundle();
            bundle.putInt(ProductDetailFragment.PRODUCT_CODE, this.mSalesDetail.getItemRef());
            bundle.putBoolean(ProductDetailFragment.PRODUCT_ISSERVICE, !this.mSalesDetail.isProduct());
            getSupportFragmentManager().beginTransaction().replace(R.id.list, Fragment.instantiate(this, ProductDetailFragment.class.getName(), bundle), "PRODUCT DETAIL").addToBackStack("PRODUCT").commit();
        }
        if (item.getItemId() == R.id.menu_product_cancel) {
            finishProduct(0);
        }
        if (item.getItemId() == R.id.menu_product_delete) {
            finishProduct(104);
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        super.onBackPressed();
        finishProduct(-1);
    }
    private void finishProduct(int i2) {
        if (this.mFicheMode != FicheMode.ANALYSE) {
            SalesDetailFragment salesDetailFragment = getSalesDetailFragment();
            Intrinsics.checkNotNull(salesDetailFragment);
            salesDetailFragment.checkDiscount();
            Intent intent = new Intent();
            intent.putExtra(SalesDetailListFragment.EXTRA_SALES_DETAIL, this.mSalesDetail);
            intent.putExtra(SalesDetailListFragment.EXTRA_SALES_DETAIL_POSITION, this.mSalesDetailPosition);
            setResult(i2, intent);
        }
        finish();
    }
    private void initPrice() {
        if (this.mSalesDetail.getSelectedPrice().getLogicalRef() > 0) {
            try {
                Cursor query = this.viewModel.getSqlBriteDatabase().query(getString(this.viewModel.getSqlHelper().getSalesProductSetPriceSql()), String.valueOf(this.mSalesDetail.getItemRef()), StringUtils.convertIntToString(this.mSalesDetail.getSelectedPrice().getLogicalRef()));
                UnitPriceFormatter unitPriceFormatter = UnitPriceFormatter.getInstance(this.viewModel.getCentOfUnitPriceDigit());
                if (query != null) {
                    if (query.moveToFirst()) {
                        this.mSalesDetail.getSelectedPrice().setLogicalRef(query.getInt(query.getColumnIndex(getString(R.string.column_id))));
                        this.mSalesDetail.setEnteryPrice(query.getDouble(query.getColumnIndex("PRICE")));
                        if (query.getInt(query.getColumnIndex("UNITCONVERT")) == 1) {
                            double d2 = query.getDouble(query.getColumnIndex("CONVFACT1"));
                            double d3 = query.getDouble(query.getColumnIndex("CONVFACT2"));
                            SalesDetail salesDetail = this.mSalesDetail;
                            salesDetail.setEnteryPrice(CalculateUtils.convertUnitPrice(salesDetail.getEnteryPrice(), this.mSalesDetail.getConvFact1(), this.mSalesDetail.getConvFact2(), d2, d3));
                        }
                        this.mSalesDetail.curCodeStr = query.getString(query.getColumnIndex("CURCODE"));
                        this.mSalesDetail.prCurrType = query.getInt(query.getColumnIndex("CURNR"));
                        SalesDetail salesDetail2 = this.mSalesDetail;
                        salesDetail2.setPriceWithDigit(unitPriceFormatter.getFormattedPrice(salesDetail2.getEnteryPrice()));
                        SalesDetail salesDetail3 = this.mSalesDetail;
                        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                        String format = String.format("%s / %s", Arrays.copyOf(new Object[]{salesDetail3.getPriceWithDigit(), query.getString(query.getColumnIndex("CURCODE"))}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        salesDetail3.setPriceWithCurCode(format);
                        FicheStringProp.setDefinition(this.mSalesDetail.getPriceWithCurCode());
                        this.mSalesDetail.setPrRate(query.getDouble(query.getColumnIndex("RATE")));
                    }
                    if (query.isClosed()) {
                        return;
                    }
                    query.close();
                }
            } catch (Exception e2) {
                Log.e("AA", "initPrice: ", e2);
            }
        }
    }
    public int getmCustomerRef() {
        return this.customerRef;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
