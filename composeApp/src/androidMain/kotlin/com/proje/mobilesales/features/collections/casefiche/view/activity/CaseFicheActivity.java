package com.proje.mobilesales.features.collections.casefiche.view.activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.AppUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.widget.OnSwipeTouchListener;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.repository.CaseFicheRepository;
import com.proje.mobilesales.features.collections.casefiche.view.fragment.CaseFicheFragment;
import com.proje.mobilesales.features.collections.casefiche.viewmodel.CaseFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.ReceiptFicheDefault;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.view.info.CustomerCurrencyInfoActivity;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.settings.interfaces.MatterCheck;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import com.proje.mobilesales.R;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class CaseFicheActivity extends BaseErpActivity implements MatterCheck {
    private static final int CustomerCurrencyInfo = 3;
    private static final int Kaydet = 0;
    private static final int Rapor = 1;
    private static final int Vazgec = 2;
    private CaseFiche caseFiche;
    private Drawable closeTotalDrawable;
    private FrameLayout frmReceiptLineTotal;
    private ImageView imgReceiptLineTotalUp;
    private LinearLayout lnCustomerBalance;
    private LinearLayout lnUnallocatedBalance;
    private int mAutoVisitId;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private boolean mTotalFrameOpen;
    private Drawable openTotalDrawable;
    private TextView txtCustomerBalance;
    private TextView txtReceiptTotal;
    private TextView txtUnallocatedBalance;
    public static final Companion Companion = new Companion(null);
    private static final String CASE_FICHE_FRAGMENT_TAG = CaseFicheActivity.class.getName() + ".CASE_FICHE_FRAGMENT_TAG";
    private final CaseFicheRepository repository = new CaseFicheRepository();
    private final CaseFicheViewModel viewModel = new CaseFicheViewModel(this.repository);
    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        private CaseFicheActivity f0;

        public void onClick(DialogInterface dialogInterface, int i2) {
            CaseFicheActivity.dialogClickListenerlambda3(this.f0, dialogInterface, i2);
        }
    };
    private final ChartInterface r1;

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        ReceiptType receiptTypeFromReceiptType;
        super.onCreate(bundle);
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_case_fiche);
        setToolbar();
        getExtras();
        this.mAutoVisitId = getIntent().getIntExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, 0);
        if (!this.viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, this.customerRef, this.routePlanRef, this.routeDayRef)) {
            Toast.makeText(this, getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }
        this.mNetsis = this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        CustomerOperation customerOperation = this.customerOperation;
        if (customerOperation != null) {
            receiptTypeFromReceiptType = customerOperation.getReceiptType();
        } else {
            receiptTypeFromReceiptType = ReceiptType.Companion.fromReceiptType(FTypeControlUtils.getMainFType().getValue());
        }
        this.mReceiptType = receiptTypeFromReceiptType;
        this.mMatterSettings = this.viewModel.getBaseErp().getMatterSettings(this, this.customerOperation.getFicheType());
        this.imgReceiptLineTotalUp = findViewById(R.id.img_receipttotal);
        this.frmReceiptLineTotal = findViewById(R.id.frm_ReceiptLineTotal);
        this.txtReceiptTotal = findViewById(R.id.txt_receipttotal);
        this.txtCustomerBalance = findViewById(R.id.txt_customerbalance);
        this.lnCustomerBalance = findViewById(R.id.ln_customerbalance);
        this.lnUnallocatedBalance = findViewById(R.id.ln_unallocatedbalance);
        this.txtUnallocatedBalance = findViewById(R.id.txt_unallocatedbalance);
        FloatingActionButton floatingActionButton = findViewById(R.id.fabFicheSave);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            private CaseFicheActivity f0;

            @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
            public void onClick(View view) {
                CaseFicheActivity.onCreatelambda0(this.f0, view);
            }
        });
        if (this.customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            floatingActionButton.setVisibility(View.GONE);
        }
        if (MainActivity.sFicheRef <= 0) {
            Cursor cursorQuery = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT MAX(LOGICALREF) AS LOGICALREF FROM CASECASH");
            if (cursorQuery.moveToFirst()) {
                do {
                    MainActivity.sFicheRef = cursorQuery.getInt(0);
                } while (cursorQuery.moveToNext());
            }
            cursorQuery.close();
        }
        if (this.customerOperation.getFicheMode() == FicheMode.NEW) {
            startNewCaseFiche();
        } else {
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_fiche_loading)).setCancelable(false).show();
            this.viewModel.getCaseFiche(getIntent().getIntExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, -1), new ReceiptFicheGetListener(this));
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public static void onCreatelambda0(CaseFicheActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.checkOneClick() && this0.isAutoDateTimeAndZoneEnabled()) {
            MatterSettings matterSettings = this0.mMatterSettings;
            Intrinsics.checkNotNull(matterSettings);
            if (matterSettings.isUseMatterNo()) {
                this0.checkMatter();
            } else {
                this0.saveComplete("");
            }
        }
    }
    private void startNewCaseFiche() {
        startCaseFiche(new CaseFiche(0, 0, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, null, 0.0d, 0.0d, 0, null, 0, null, null, null, null, 0, 0.0d, null, 0, 536870911, null));
    }
    private void startCaseFiche(CaseFiche caseFiche) {
        if ((caseFiche != null ? caseFiche.getBranch() : null) == null && this.customerOperation.getFicheMode() != FicheMode.NEW) {
            Toast.makeText(this, getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
            super.onBackPressed();
            return;
        }
        this.caseFiche = caseFiche;
        Intent intent = getIntent();
        String str = IntentExtraName.INVOICE_PAYMENTLINE_TOTAL;
        CaseFiche caseFiche2 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche2);
        double doubleExtra = intent.getDoubleExtra(str, caseFiche2.getTotal().getDefinitionDouble());
        Intent intent2 = getIntent();
        String str2 = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        CaseFiche caseFiche3 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche3);
        int intExtra = intent2.getIntExtra(str2, caseFiche3.getInvoiceRef());
        CaseFiche caseFiche4 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche4);
        caseFiche4.setTotal(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(doubleExtra))));
        CaseFiche caseFiche5 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche5);
        caseFiche5.setInvoiceRef(intExtra);
        CaseFiche caseFiche6 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche6);
        caseFiche6.setVisitInfoId(this.mAutoVisitId);
        if (this.customerOperation.getFicheMode() == FicheMode.EDIT) {
            ISqlHelper logoSqlHelper = this.viewModel.getBaseErp().getLogoSqlHelper();
            CaseFiche caseFiche7 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche7);
            if (logoSqlHelper.getInvoiceReceiptRelationIfTransfered(caseFiche7.getInvoiceRef(), false)) {
                Toast.makeText(this, getString(R.string.str_invoice_receipt_cannot_edit), Toast.LENGTH_SHORT).show();
                super.onBackPressed();
                return;
            }
        }
        if (this.customerOperation.getFicheMode() == FicheMode.NEW) {
            loadDefaultValue();
        }
        if (this.customerOperation.getFicheMode() == FicheMode.COPY) {
            initCopyFiche();
        }
        getSupportFragmentManager().beginTransaction().replace(android.R.id.list, instantiateCaseFicheFragment(), CASE_FICHE_FRAGMENT_TAG).commit();
        this.openTotalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_up_bold_circle_outline_white_36dp);
        this.closeTotalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_down_bold_circle_outline_white_36dp);
        initImgOrderLineTotalClick();
        setImgOrderLineTotalUpDrawable();
        setFrameLayoutClick();
        setFrameLayoutFields();
    }
    public record ReceiptFicheGetListener(WeakReference<CaseFicheActivity> mAdapter) implements ResponseListener<CaseFiche> {
            private ReceiptFicheGetListener(CaseFicheActivity mAdapter) {
                this(new WeakReference<>(mAdapter));
            }
        public void onResponse(CaseFiche caseFiche) {
                if (this.mAdapter.get() != null) {
                    CaseFicheActivity caseFicheActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(caseFicheActivity);
                    if (caseFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    caseFicheActivity.onFicheGet(caseFiche, "");
                }
            }
        public void onResponse(ArrayList<CaseFiche> obj) {}
        public void onResponse() {}
        public void onResponse(Boolean aBoolean) {}
        public void onResponse(Sales sales) {}
        public void onResponse(TigerServiceResult tigerServiceResult) {}
        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    CaseFicheActivity caseFicheActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(caseFicheActivity);
                    if (caseFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    caseFicheActivity.onFicheGet(null, errorMessage);
                }
            }
        public void onFailure(Throwable throwable) { }
    }
    public void onFicheGet(Object obj, String str) {
        invalidateOptionsMenu();
        this.mProgressDialogBuilder.dismiss();
        if (obj != null) {
            startCaseFiche((CaseFiche) obj);
        } else {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
    private void setFrameLayoutFields() {
        String str;
        TextView textView = this.txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        CaseFiche caseFiche = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche);
        String string = caseFiche.getTotal().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        textView.setText(StringUtils.dFormat(string));
        double clCardBalance = this.viewModel.getBaseErp().getLogoSqlHelper().getClCardBalance(this.customerRef);
        double d2 = 0.0d;
        String str2 = "(A)";
        if (clCardBalance < 0.0d) {
            clCardBalance *= -1;
            str = "(A)";
        } else {
            str = "(B)";
        }
        String str3 = StringUtils.dFormat(clCardBalance) + ' ' + str;
        TextView textView2 = this.txtCustomerBalance;
        Intrinsics.checkNotNull(textView2);
        textView2.setText(str3);
        if (this.viewModel.getBaseErp().isHideCustomerBalance()) {
            LinearLayout linearLayout = this.lnCustomerBalance;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
            LinearLayout linearLayout2 = this.lnUnallocatedBalance;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
        }
        Cursor customerUnsentAccountBalance = this.viewModel.getBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(this.customerRef);
        if (customerUnsentAccountBalance == null || customerUnsentAccountBalance.getCount() <= 0 || !customerUnsentAccountBalance.moveToFirst()) {
            str2 = "(B)";
        } else {
            double dRoundTwoDecimals = StringUtils.roundTwoDecimals(customerUnsentAccountBalance.getDouble(customerUnsentAccountBalance.getColumnIndex("ALLBALANCE")));
            if (dRoundTwoDecimals < 0.0d) {
                d2 = dRoundTwoDecimals * ((double) (-1));
            } else {
                str2 = "(B)";
                d2 = dRoundTwoDecimals;
            }
        }
        String str4 = StringUtils.dFormat(d2) + ' ' + str2;
        TextView textView3 = this.txtUnallocatedBalance;
        Intrinsics.checkNotNull(textView3);
        textView3.setText(str4);
    }
    private void setFrameLayoutClick() {
        FrameLayout frameLayout = this.frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.setOnTouchListener(new OnSwipeTouchListener() {
            public void onBottomToTopSwipe() {
                if (CaseFicheActivity.this.mTotalFrameOpen) {
                    return;
                }
                CaseFicheActivity.this.openFrame();
            }
            public void onTopToBottomSwipe() {
                if (CaseFicheActivity.this.mTotalFrameOpen) {
                    CaseFicheActivity.this.closeFrame();
                }
            }
        });
    }
    private void initImgOrderLineTotalClick() {
        ImageView imageView = this.imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            private CaseFicheActivity f0;
            public void onClick(View view) {
                CaseFicheActivity.initImgOrderLineTotalClicklambda1(this.f0, view);
            }
        });
    }
    public static void initImgOrderLineTotalClicklambda1(CaseFicheActivity this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (this0.viewModel.getBaseErp().isHideCustomerBalance()) {
            return;
        }
        if (this0.mTotalFrameOpen) {
            this0.closeFrame();
        } else {
            this0.openFrame();
        }
    }
    private void setImgOrderLineTotalUpDrawable() {
        if (this.mTotalFrameOpen) {
            ImageView imageView = this.imgReceiptLineTotalUp;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageDrawable(this.closeTotalDrawable);
        } else {
            ImageView imageView2 = this.imgReceiptLineTotalUp;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setImageDrawable(this.openTotalDrawable);
        }
    }
    public void openFrame() {
        FrameLayout frameLayout = this.frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        ViewPropertyAnimator viewPropertyAnimatorAnimate = frameLayout.animate();
        Intrinsics.checkNotNull(this.frmReceiptLineTotal);
        viewPropertyAnimatorAnimate.translationY((r1.getHeight() / 3) * (-2));
        viewPropertyAnimatorAnimate.setDuration(500L);
        final ViewPropertyAnimator viewPropertyAnimator = viewPropertyAnimatorAnimate;
        ImageView imageView = this.imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(this.closeTotalDrawable);
        this.mTotalFrameOpen = true;
    }
    public void closeFrame() {
        FrameLayout frameLayout = this.frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.animate().translationY(0.0f).setDuration(500L);
        ImageView imageView = this.imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(this.openTotalDrawable);
        this.mTotalFrameOpen = false;
    }
    public void setTotalLayoutWithNotify() {
        TextView textView = this.txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        CaseFiche caseFiche = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche);
        String string = caseFiche.getTotal().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        textView.setText(StringUtils.dFormat(string));
    }
    private Fragment instantiateCaseFicheFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, this.customerOperation);
        bundle.putInt(IntentExtraName.EXTRA_CUSTOMER_REF, this.customerRef);
        Fragment fragmentInstantiate = Fragment.instantiate(this, CaseFicheFragment.class.getName(), bundle);
        Intrinsics.checkNotNullExpressionValue(fragmentInstantiate, "instantiate(...)");
        return fragmentInstantiate;
    }
    private void initCopyFiche() {
        CaseFiche caseFiche = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche);
        caseFiche.setLogicalRef(0);
        CaseFiche caseFiche2 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche2);
        caseFiche2.setTransfer(0);
        CaseFiche caseFiche3 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche3);
        caseFiche3.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
        CaseFiche caseFiche4 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche4);
        ErpType erpType = this.viewModel.getBaseErp().getErpType();
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        caseFiche4.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
        CaseFiche caseFiche5 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche5);
        caseFiche5.setGDate(DateAndTimeUtils.nowDateTime());
        CaseFiche caseFiche6 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche6);
        caseFiche6.setEnlem(getLatitude());
        CaseFiche caseFiche7 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche7);
        caseFiche7.setBoylam(getLongitude());
        CaseFiche caseFiche8 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche8);
        caseFiche8.setFicheref(0);
        CaseFiche caseFiche9 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche9);
        caseFiche9.setFicheNo("");
        CaseFiche caseFiche10 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche10);
        caseFiche10.setInvoiceRef(0);
        CaseFiche caseFiche11 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche11);
        caseFiche11.setClRate(0.0d);
        CaseFiche caseFiche12 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche12);
        caseFiche12.setClCurr(new FicheRefProp());
        CaseFiche caseFiche13 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche13);
        caseFiche13.setVisitInfoId(this.mAutoVisitId);
    }
    private void loadDefaultValue() {
        ReceiptFicheDefault ficheDefault = this.viewModel.getBaseErp().getFicheDefault(this.mReceiptType);
        CaseFiche caseFiche = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche);
        loadFicheDefaultParameter(caseFiche.getBranch(), StringUtils.convertStringToInt(ficheDefault.getDefaultDivision()), R.string.column_code, getString(R.string.qry_branch_where));
        CaseFiche caseFiche2 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche2);
        loadFicheDefaultParameter(caseFiche2.getDivision(), StringUtils.convertStringToInt(ficheDefault.getDefaultDepartment()), R.string.column_code, getString(R.string.qry_division_where));
        CaseFiche caseFiche3 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche3);
        FicheDiscountRefProp caseCode = caseFiche3.getCaseCode();
        int loadFicheDefaultCaseSql = this.viewModel.getBaseErp().getLoadFicheDefaultCaseSql();
        String loadFicheDefaultCaseFirmNr = this.viewModel.getBaseErp().getLoadFicheDefaultCaseFirmNr();
        Intrinsics.checkNotNullExpressionValue(loadFicheDefaultCaseFirmNr, "getLoadFicheDefaultCaseFirmNr(...)");
        loadFicheDefaultParameter(caseCode, R.string.column_code, loadFicheDefaultCaseSql, ficheDefault.getDefaultCaseWithoutFirmOrBranch(loadFicheDefaultCaseFirmNr));
        CaseFiche caseFiche4 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche4);
        loadFicheDefaultParameter(caseFiche4.getSpecode(), ficheDefault.getDefaultSpeCode());
        BaseErp<?> baseErp = this.viewModel.getBaseErp();
        CaseFiche caseFiche5 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche5);
        baseErp.loadFicheDefaultProjectCode(caseFiche5.getProjectCode(), ficheDefault.getDefaultProjectCode());
        CaseFiche caseFiche6 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche6);
        loadFicheDefaultParameter(caseFiche6.getCyphcode(), ficheDefault.getDefaultCyphCode());
        CaseFiche caseFiche7 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche7);
        loadFicheDefaultParameter(caseFiche7.getTradinggrp(), ficheDefault.getDefaultTradingGroup());
        CaseFiche caseFiche8 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche8);
        if (caseFiche8.getCaseCode().getLogicalRef() != -1) {
            BaseErp<?> baseErp2 = this.viewModel.getBaseErp();
            CaseFiche caseFiche9 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche9);
            int i2 = baseErp2.getUserCase(caseFiche9.getCaseCode().getLogicalRef()).getcCurrency();
            CaseFiche caseFiche10 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche10);
            loadFicheDefaultParameter(caseFiche10.getCurrType(), i2, R.string.column_code, getString(R.string.qry_get_curr_type_where));
        }
        if (this.viewModel.getBaseErp().getErpType() != ErpType.NETSIS) {
            CaseFiche caseFiche11 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche11);
            loadFicheDefaultParameter(caseFiche11.getExplanation(), this.viewModel.isSalesPersonOnDesc() ? this.viewModel.getBaseErp().getUser().getName() : StringUtils.empty());
        }
    }
    public void loadFicheDefaultParameter(FicheRefProp ficheRefProp, int i2, @StringRes int i3, String str) {
        Intrinsics.checkNotNullParameter(ficheRefProp, "ficheRefProp");
        if (i2 == -1) {
            return;
        }
        ficheRefProp.setLogicalRef(i2);
        Cursor cursorQuery = null;
        try {
            try {
                ISqlBriteDatabase logoSqlBriteDatabase = this.viewModel.getBaseErp().getLogoSqlBriteDatabase();
                Intrinsics.checkNotNull(str);
                cursorQuery = logoSqlBriteDatabase.query(str, StringUtils.convertIntToString(i2));
                if (cursorQuery.moveToFirst()) {
                    FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(getString(i3))));
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
    private void loadFicheDefaultParameter(FicheDiscountRefProp ficheDiscountRefProp, @StringRes int i2, @StringRes int i3, String... strArr) {
        if (TextUtils.isEmpty(ficheDiscountRefProp.getDefinition())) {
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query(getString(i3), Arrays.copyOf(strArr, strArr.length));
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        ficheDiscountRefProp.setLogicalRef(cursorQuery.getInt(cursorQuery.getColumnIndex(getString(R.string.column_id))));
                        FicheStringProp.setDefinition(cursorQuery.getString(cursorQuery.getColumnIndex(getString(R.string.column_name))));
                        ficheDiscountRefProp.setCode(cursorQuery.getString(cursorQuery.getColumnIndex(getString(i2))));
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
    }
    private void loadFicheDefaultParameter(FicheStringProp ficheStringProp, String str) {
        if (str != null) {
            FicheStringProp.setDefinition(str);
        }
    }
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        final com.proje.mobilesales.features.collections.casefiche.model.CaseFiche caseFiche;
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.add(0, 0, 0, com.proje.mobilesales.core.utils.ContextUtils.getStringResource(R.string.str_save)).setIcon(android.R.drawable.ic_menu_save);
        menu.add(0, 1, 0, com.proje.mobilesales.core.utils.ContextUtils.getStringResource(R.string.str_reports)).setIcon(android.R.drawable.ic_menu_directions);
        if (viewModel.getBaseErp().getErpType() != com.proje.mobilesales.core.enums.ErpType.NETSIS) {
            final com.proje.mobilesales.core.enums.FicheMode ficheMode = customerOperation.getFicheMode();
            final com.proje.mobilesales.core.enums.FicheMode ficheMode2 = com.proje.mobilesales.core.enums.FicheMode.ANALYSE;
            if (ficheMode == ficheMode2) {
                if (customerOperation.getFicheMode() == ficheMode2 && null != (caseFiche = this.caseFiche)) {
                    Intrinsics.checkNotNull(caseFiche);
                }
            }
            menu.add(0, 3, 0, com.proje.mobilesales.core.utils.ContextUtils.getStringResource(R.string.str_customer_currency_info));
        }
        menu.add(0, 2, 0, com.proje.mobilesales.core.utils.ContextUtils.getStringResource(R.string.str_close)).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId != 0) {
            if (itemId == 1) {
                startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
            } else if (itemId == 2) {
                cancelFiche();
            } else if (itemId == 3) {
                Intent intent = new Intent(this, CustomerCurrencyInfoActivity.class);
                intent.putExtra(IntentExtraName.EXTRA_CASE_FICHE, this.caseFiche);
                intent.putExtra(IntentExtraName.EXTRA_FICHE_MODE, this.customerOperation.getFicheMode());
                intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.customerRef);
                startActivityForResult(intent, IntentExtraName.CUSTOMER_CURRENCY_INFO);
            } else if (itemId == 16908332) {
                cancelFiche();
                return true;
            }
        } else if (checkOneClick()) {
            MatterSettings matterSettings = this.mMatterSettings;
            Intrinsics.checkNotNull(matterSettings);
            if (matterSettings.isUseMatterNo()) {
                checkMatter();
            } else {
                saveComplete("");
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void cancelFiche() {
        if (this.customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            if (getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0) > 0) {
                Toast.makeText(this, getString(R.string.str_should_collections_save_before_leave_the_fiche), Toast.LENGTH_LONG).show();
                return;
            } else {
                new AlertDialog.Builder(this).setMessage(ContextUtils.getStringResource(R.string.str_question_want_close)).setPositiveButton(ContextUtils.getStringResource(R.string.str_yes), this.dialogClickListener).setNegativeButton(ContextUtils.getStringResource(R.string.str_no), this.dialogClickListener).show();
                return;
            }
        }
        finish();
    }
    public boolean onKeyDown(int i2, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (i2 == 4) {
            cancelFiche();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return this.dialogClickListener;
    }
    public void setDialogClickListener(DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        this.dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda3(CaseFicheActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != -1) {
            return;
        }
        this0.finish();
    }
    public void checkMatter() {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait_get_matter_no)).show();
        this.viewModel.getBaseErp().getMaxMatterNo(this.customerOperation.getFicheType(), this.mMatterSettings, new MatterCheck.MatterCheckListener(this));
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onMatterCheck(String str) {
        this.mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, getString(R.string.exp_45_undefined), Toast.LENGTH_LONG).show();
            saveCompletedefault(this, null, 1, null);
        } else if (str != null && maxMatterNoControl(str)) {
            saveComplete(str);
        } else if (str != null) {
            setNewMaxMatterNo(str);
        }
    }
    public void onMatterError(String str) {
        this.mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private boolean maxMatterNoControl(String str) {
        MatterSettings matterSettings = this.mMatterSettings;
        Intrinsics.checkNotNull(matterSettings);
        return AppUtils.maxMatterNoControl(str, matterSettings.getLastMatterNo());
    }
    public void setNewMaxMatterNo(final String str) {
        ContextUtils.showMatterDialog(this, getString(R.string.str_matter_input_last_value_title), this.mMatterSettings, new ResponseListener<Object>() {
            public void onResponse(Object obj) {
                String strValueOf = String.valueOf(obj);
                if (TextUtils.isEmpty(strValueOf)) {
                    CaseFicheActivity.this.setNewMaxMatterNo(str);
                    return;
                }
                CaseFicheActivity.this.viewModel.getBaseErp().setNewMatter(CaseFicheActivity.this.getApplicationContext(), CaseFicheActivity.this.customerOperation.getFicheType(), strValueOf);
                MatterSettings matterSettings = CaseFicheActivity.this.mMatterSettings;
                Intrinsics.checkNotNull(matterSettings);
                matterSettings.setLastMatterNo(strValueOf);
                CaseFicheActivity.this.checkMatter();
            }

            @Override
            public void onResponse(ArrayList<Object> obj) {

            }

            @Override
            public void onResponse() {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                CaseFicheActivity.this.finish();
            }

            @Override
            public void onFailure(Throwable throwable) {
                
            }

            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onResponse(Sales sales) {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }
        });
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    static void saveCompletedefault(CaseFicheActivity caseFicheActivity, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        caseFicheActivity.saveComplete(str);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private void saveComplete(String str) {
        CaseFiche caseFiche = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche);
        if (caseFiche.getTotal().getDefinitionDouble() <= 0.0d) {
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_question_enter_amount), Toast.LENGTH_SHORT).show();
            return;
        }
        CaseFiche caseFiche2 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche2);
        if (caseFiche2.getCaseCode().getLogicalRef() == -1) {
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_question_select_safe), Toast.LENGTH_SHORT).show();
            return;
        }
        CaseFiche caseFiche3 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche3);
        caseFiche3.setFicheNo(str);
        CaseFiche caseFiche4 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche4);
        caseFiche4.setEnlem(getLatitude());
        CaseFiche caseFiche5 = this.caseFiche;
        Intrinsics.checkNotNull(caseFiche5);
        caseFiche5.setBoylam(getLongitude());
        if (this.mNetsis) {
            CaseFiche caseFiche6 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche6);
            caseFiche6.setClCode(this.viewModel.getBaseErp().getCustomerClCode(this.customerRef));
        } else {
            CaseFiche caseFiche7 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche7);
            caseFiche7.setClRef(this.customerRef);
        }
        if (this.customerOperation.getFicheMode() == FicheMode.NEW || this.customerOperation.getFicheMode() == FicheMode.COPY) {
            CaseFiche caseFiche8 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche8);
            caseFiche8.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            CaseFiche caseFiche9 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche9);
            ErpType erpType = this.viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            caseFiche9.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
            CaseFiche caseFiche10 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche10);
            caseFiche10.setGDate(DateAndTimeUtils.nowDateTime());
            CaseFicheViewModel caseFicheViewModel = this.viewModel;
            CaseFiche caseFiche11 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche11);
            ReceiptType receiptType = this.mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            caseFicheViewModel.saveCaseFiche(caseFiche11, receiptType, new SalesActivityFicheSaveListener(this));
            baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.SAFE_DEPOSIT_COLLECTION);
            return;
        }
        if (this.customerOperation.getFicheMode() == FicheMode.EDIT) {
            CaseFicheViewModel caseFicheViewModel2 = this.viewModel;
            CaseFiche caseFiche12 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche12);
            ReceiptType receiptType2 = this.mReceiptType;
            Intrinsics.checkNotNull(receiptType2);
            caseFicheViewModel2.updateCaseFiche(caseFiche12, receiptType2, new SalesActivityFicheSaveListener(this));
        }
    }
    private record SalesActivityFicheSaveListener(
            WeakReference<CaseFicheActivity> caseFicheActivityWeakReference) implements ResponseListener<Boolean> {
        private SalesActivityFicheSaveListener(CaseFicheActivity caseFicheActivityWeakReference) {
                this(new WeakReference<>(caseFicheActivityWeakReference));
            }
        public void onResponse(Boolean bool) {
                if (this.caseFicheActivityWeakReference.get() != null) {
                    CaseFicheActivity caseFicheActivity = this.caseFicheActivityWeakReference.get();
                    Intrinsics.checkNotNull(caseFicheActivity);
                    if (caseFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    CaseFicheActivity caseFicheActivity2 = this.caseFicheActivityWeakReference.get();
                    Intrinsics.checkNotNull(caseFicheActivity2);
                    Intrinsics.checkNotNull(bool);
                    caseFicheActivity2.onSaveResult(bool.booleanValue(), "");
                }
            } 
        public void onResponse(ArrayList<Boolean> obj) { } 
        public void onResponse() { } 
        public void onResponse(Sales sales) { } 
        public void onResponse(TigerServiceResult tigerServiceResult) { }
        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                CaseFicheActivity caseFicheActivity = this.caseFicheActivityWeakReference.get();
                Intrinsics.checkNotNull(caseFicheActivity);
                caseFicheActivity.onSaveResult(false, errorMessage);
            } 
        public void onFailure(Throwable throwable) {
            
        }
    }
    public void onSaveResult(boolean z, String str) {
        if (z) {
            Toast.makeText(this, getString(R.string.str_fiche_save_successful), Toast.LENGTH_LONG).show();
            insertRouteProcess(this.customerOperation);
            finish();
        } else {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format("%s : %s", Arrays.copyOf(new Object[]{getString(R.string.str_fiche_save_on_error), str}, 2));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            Toast.makeText(this, str2, Toast.LENGTH_LONG).show();
        }
    }
    public boolean dispatchTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof EditText editText) {
                Rect rect = new Rect();
                editText.getGlobalVisibleRect(rect);
                if (!rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    editText.clearFocus();
                    Object systemService = getSystemService(Context.INPUT_METHOD_SERVICE);
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                    ((InputMethodManager) systemService).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 12347) {
            if (i3 != -1) {
                if (i3 != 104) {
                    return;
                }
                CaseFiche caseFiche = this.caseFiche;
                Intrinsics.checkNotNull(caseFiche);
                caseFiche.setClRate(0.0d);
                CaseFiche caseFiche2 = this.caseFiche;
                Intrinsics.checkNotNull(caseFiche2);
                caseFiche2.setClCurr(new FicheRefProp());
                return;
            }
            Intrinsics.checkNotNull(intent);
            CaseFiche caseFiche3 = intent.getParcelableExtra(IntentExtraName.EXTRA_CASE_FICHE);
            CaseFiche caseFiche4 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche4);
            Intrinsics.checkNotNull(caseFiche3);
            caseFiche4.setClRate(caseFiche3.getClRate());
            CaseFiche caseFiche5 = this.caseFiche;
            Intrinsics.checkNotNull(caseFiche5);
            caseFiche5.setClCurr(caseFiche3.getClCurr());
        }
    }
    public CaseFiche getCaseFiche() {
        return this.caseFiche;
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public String getCASE_FICHE_FRAGMENT_TAG() {
            return CaseFicheActivity.CASE_FICHE_FRAGMENT_TAG;
        }
    }
}
