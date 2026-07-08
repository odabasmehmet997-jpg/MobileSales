package com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity;

import android.Manifest;
import android.R;
import android.annotation.SuppressLint;
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
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.Scrollable;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.AppUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.core.widget.OnSwipeTouchListener;
import com.proje.mobilesales.core.widget.ViewPager;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.adapter.FichePagerAdapter;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFicheDetail;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.repository.CashAndCreditCardFicheRepository;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.fragment.CashCreditFicheDetailListFragment;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.viewmodel.CashAndCreditCardFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.ReceiptFicheDefault;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptFicheShortType;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.dbmodel.Bank;
import com.proje.mobilesales.features.dbmodel.BankAccount;
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
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import org.springframework.http.HttpHeaders;

public final class CashAndCreditCardFicheActivity extends BaseErpActivity implements MatterCheck {
    public static final int REQUEST_EDIT_DETAIL = 2;
    public static final int REQUEST_NEW_DETAIL = 1;
    private CashCreditFiche cashCreditFiche;
    private Drawable closeTotalDrawable;
    private FloatingActionButton floatingActionButton;
    private FrameLayout frmReceiptLineTotal;
    private ImageView imgReceiptLineTotalUp;
    private int invoicePaymentBankAccRef;
    private int invoicePaymentBankRef;
    private double invoicePaymentTotal;
    private LinearLayout lnCustomerBalance;
    private LinearLayout lnUnallocatedBalance;
    private FichePagerAdapter mAdapter;
    private AppBarLayout mAppBar;
    private int mAutoVisitId;
    private CoordinatorLayout mCoordinatorLayout;
    private MatterSettings mMatterSettings;
    private boolean mNetsis;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private ReceiptType mReceiptType;
    private TabLayout mTabLayout;
    private boolean mTotalFrameOpen;
    private ViewPager mViewPager;
    private Drawable openTotalDrawable;
    private ScreenControl screenControl;
    private TextView txtCustomerBalance;
    private TextView txtReceiptTotal;
    private TextView txtUnallocatedBalance;
    public static final Companion Companion = new Companion(null);
    private static final String CASH_CREDIT_DETAIL = CashAndCreditCardFicheActivity.class.getName() + ".CASH_CREDIT_DETAIL";
    private static final String CASH_CREDIT = CashAndCreditCardFicheActivity.class.getName() + ".CASH_CREDIT";
    private static final String CASH_CREDIT_DETAIL_POSITION = CashAndCreditCardFicheActivity.class.getName() + ".CASH_CREDIT_DETAIL_POSITION";
    private final CashAndCreditCardFicheRepository repository = new CashAndCreditCardFicheRepository();
    private final CashAndCreditCardFicheViewModel viewModel = new CashAndCreditCardFicheViewModel(repository);
    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        public void onClick(final DialogInterface dialogInterface, final int i2) {
            dialogClickListenerlambda5(CashAndCreditCardFicheActivity.this, dialogInterface, i2);
        }
    };
    private String r2;
    private final ChartInterface r1;
    public CashCreditFiche getCashCreditFiche() {
        return cashCreditFiche;
    }
    public double getInvoicePaymentTotal() {
        return invoicePaymentTotal;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_cash_and_credit_card_fiche);
        this.setToolbar();
        this.getExtras();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        final FicheMode ficheMode = customerOperation.getFicheMode();
        final FicheMode ficheMode2 = FicheMode.NEW;
        if ((ficheMode == ficheMode2 || customerOperation.getFicheMode() == FicheMode.COPY) && !viewModel.getBaseErp().checkRouteVisitOutOfOrder(this, customerRef, routePlanRef, routeDayRef)) {
            Toast.makeText(this, this.getString(R.string.str_comply_before_route), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        final View findViewById = this.findViewById(R.id.tab_layout);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.google.android.material.tabs.TabLayout");
        mTabLayout = (TabLayout) findViewById;
        final View findViewById2 = this.findViewById(R.id.appbar);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout");
        mAppBar = (AppBarLayout) findViewById2;
        final View findViewById3 = this.findViewById(R.id.content_frame);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        mCoordinatorLayout = (CoordinatorLayout) findViewById3;
        final View findViewById4 = this.findViewById(R.id.view_pager);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.ViewPager");
        mViewPager = (ViewPager) findViewById4;
        imgReceiptLineTotalUp = this.findViewById(R.id.img_receipttotal);
        frmReceiptLineTotal = this.findViewById(R.id.frm_ReceiptLineTotal);
        txtReceiptTotal = this.findViewById(R.id.txt_receipttotal);
        txtCustomerBalance = this.findViewById(R.id.txt_customerbalance);
        lnCustomerBalance = this.findViewById(R.id.ln_customerbalance);
        lnUnallocatedBalance = this.findViewById(R.id.ln_unallocatedbalance);
        txtUnallocatedBalance = this.findViewById(R.id.txt_unallocatedbalance);
        floatingActionButton = this.findViewById(R.id.fabDetailAdd);
        mNetsis = viewModel.getBaseErp().getErpType() == ErpType.NETSIS;
        screenControl = new ScreenControl(this);
        final ReceiptType receiptType = customerOperation.getReceiptType();
        mReceiptType = receiptType;
        if (null == receiptType) {
            final Cursor query = viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT FTYPE FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
            if (query.moveToFirst()) {
                if (Intrinsics.areEqual(viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "FTYPE", ColType.sayi).toString(), StringUtils.convertIntToString(ReceiptFicheShortType.CREDITFTYPE.getFType()))) {
                    mReceiptType = ReceiptType.CREDIT;
                    this.setTitle(this.getString(R.string.str_cash_collection));
                } else {
                    mReceiptType = ReceiptType.CASH;
                    this.setTitle(this.getString(R.string.str_cash_collection));
                }
            }
        }
        if (mReceiptType == ReceiptType.CASH) {
            this.setTitle(this.getString(R.string.str_cash_collection));
        } else {
            this.setTitle(this.getString(R.string.str_credit_card_slip));
        }
        mMatterSettings = viewModel.getBaseErp().getMatterSettings(this, customerOperation.getFicheType());
        invoicePaymentTotal = this.getIntent().getDoubleExtra(IntentExtraName.INVOICE_PAYMENTLINE_TOTAL, 0.0d);
        invoicePaymentBankRef = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_BANKREF, -1);
        invoicePaymentBankAccRef = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_BANKACCREF, -1);
        mAutoVisitId = this.getIntent().getIntExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, 0);
        if (customerOperation.getFicheMode() == ficheMode2) {
            this.startNewCashCreditFiche();
            return;
        }
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_fiche_loading)).setCancelable(false).show();
        viewModel.getCashCreditFiche(this.getIntent().getIntExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, -1), customerOperation.getReceiptType() != ReceiptType.CREDIT ? 0 : 1, new ReceiptFicheGetListener(this));
    }
    private void startNewCashCreditFiche() {
        final CashCreditFiche cashCreditFiche = new CashCreditFiche(0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, 0, 0, null, null, null, -1, null);
        cashCreditFiche.init();
        cashCreditFiche.setfType(customerOperation.getReceiptType() == ReceiptType.CREDIT ? 1 : 0);
        cashCreditFiche.setVisitInfoId(mAutoVisitId);
        this.startCashCreditFiche(cashCreditFiche);
    }
    private void startCashCreditFiche(final CashCreditFiche cashCreditFiche) {
        if (null == (cashCreditFiche != null ? cashCreditFiche.getBranch() : null) && customerOperation.getFicheMode() != FicheMode.NEW) {
            Toast.makeText(this, this.getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
            onBackPressed();
            return;
        }
        this.cashCreditFiche = cashCreditFiche;
        final int intExtra = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0);
        if (0 < intExtra) {
            final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche2);
            cashCreditFiche2.setInvoiceRef(intExtra);
            final CashCreditFiche cashCreditFiche3 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche3);
            final ArrayList<CashCreditFicheDetail> details = cashCreditFiche3.getDetails();
            Intrinsics.checkNotNull(details);
            details.clear();
            final CashCreditFiche cashCreditFiche4 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche4);
            cashCreditFiche4.setTotal(0.0d);
        }
        if (customerOperation.getFicheMode() == FicheMode.NEW) {
            this.loadDefaultValue();
        }
        if (customerOperation.getFicheMode() == FicheMode.COPY) {
            this.initCopyFiche();
        }
        if (customerOperation.getFicheMode() == FicheMode.EDIT && viewModel.getBaseErp().getLogoSqlHelper().getInvoiceReceiptRelationIfTransfered(2, false)) {
            Toast.makeText(this, this.getString(R.string.str_invoice_receipt_cannot_edit), Toast.LENGTH_SHORT).show();
            onBackPressed();
            return;
        }
        this.setTotalLayoutWithNotify();
        this.loadPager();
        final FloatingActionButton floatingActionButton = this.floatingActionButton;
        Intrinsics.checkNotNull(floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void CashAndCreditCardFicheActivityExternalSyntheticLambda2() {
            }
            public void onClick(final View view) {
                startCashCreditFichelambda0(CashAndCreditCardFicheActivity.this, view);
            }
        });
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            final FloatingActionButton floatingActionButton2 = this.floatingActionButton;
            Intrinsics.checkNotNull(floatingActionButton2);
            floatingActionButton2.setVisibility(View.GONE);
        }
        openTotalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_up_bold_circle_outline_white_36dp);
        closeTotalDrawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_down_bold_circle_outline_white_36dp);
        this.initImgOrderLineTotalClick();
        this.setImgOrderLineTotalUpDrawable();
        this.setFrameLayoutClick();
        this.setFrameLayoutFields();
    }
    public static void startCashCreditFichelambda0(final CashAndCreditCardFicheActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.addNewDetail(false);
    }
    private record ReceiptFicheGetListener( WeakReference<CashAndCreditCardFicheActivity> mAdapter) implements ResponseListener<CashCreditFiche> {
       private ReceiptFicheGetListener(final CashAndCreditCardFicheActivity mAdapter) {
        this(new WeakReference<>(mAdapter));
       }
       public void onResponse(final CashCreditFiche cashCreditFiche) {
                if (null != this.mAdapter.get()) {
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity = mAdapter.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity);
                    if (cashAndCreditCardFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity2 = mAdapter.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity2);
                    cashAndCreditCardFicheActivity2.onFicheGet(cashCreditFiche, "");
                }
            }
       public void onResponse(ArrayList<CashCreditFiche> obj) { }
       public void onResponse() { }
       public void onFailure(Throwable throwable) { }
       public void onResponse(Boolean aBoolean) { }
       public void onResponse(Sales sales) { }
       public void onResponse(TigerServiceResult tigerServiceResult) { }
       public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mAdapter.get()) {
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity = mAdapter.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity);
                    if (cashAndCreditCardFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity2 = mAdapter.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity2);
                    cashAndCreditCardFicheActivity2.onFicheGet(null, errorMessage);
                }
            }
    }
    public void onFicheGet(final Object obj, final String str) {
        mProgressDialogBuilder.dismiss();
        if (null != obj) {
            this.startCashCreditFiche((CashCreditFiche) obj);
        } else {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
    private void setFrameLayoutFields() {
        final String str;
        final TextView textView = txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        textView.setText(StringUtils.dFormat(cashCreditFiche.getTotal()));
        double clCardBalance = viewModel.getBaseErp().getLogoSqlHelper().getClCardBalance(customerRef);
        double d2 = 0.0d;
        String str2 = "(A)";
        if (0.0d > clCardBalance) {
            clCardBalance *= -1;
            str = "(A)";
        } else {
            str = "(B)";
        }
        final String str3 = StringUtils.dFormat(clCardBalance) + ' ' + str;
        final TextView textView2 = txtCustomerBalance;
        Intrinsics.checkNotNull(textView2);
        textView2.setText(str3);
        if (viewModel.getBaseErp().isHideCustomerBalance()) {
            final LinearLayout linearLayout = lnCustomerBalance;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
            final LinearLayout linearLayout2 = lnUnallocatedBalance;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.GONE);
        }
        final Cursor customerUnsentAccountBalance = viewModel.getBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(customerRef);
        if (null == customerUnsentAccountBalance || 0 >= customerUnsentAccountBalance.getCount() || !customerUnsentAccountBalance.moveToFirst()) {
            str2 = "(B)";
        } else {
            final double roundTwoDecimals = StringUtils.roundTwoDecimals(customerUnsentAccountBalance.getDouble(customerUnsentAccountBalance.getColumnIndex("ALLBALANCE")));
            if (0.0d > roundTwoDecimals) {
                d2 = roundTwoDecimals * (-1);
            } else {
                str2 = "(B)";
                d2 = roundTwoDecimals;
            }
        }
        final String str4 = StringUtils.dFormat(d2) + ' ' + str2;
        final TextView textView3 = txtUnallocatedBalance;
        Intrinsics.checkNotNull(textView3);
        textView3.setText(str4);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setFrameLayoutClick() {
        final FrameLayout frameLayout = frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.setOnTouchListener(new OnSwipeTouchListener() {
            void CashAndCreditCardFicheActivitysetFrameLayoutClick1() {
            }
            public void onBottomToTopSwipe() {
                final boolean z;
                z = mTotalFrameOpen;
                if (z) {
                    return;
                }
                openFrame();
            }
            public void onTopToBottomSwipe() {
                final boolean z;
                z = mTotalFrameOpen;
                if (z) {
                    closeFrame();
                }
            }
        });
    }
    private void initImgOrderLineTotalClick() {
        final ImageView imageView = imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            void CashAndCreditCardFicheActivityExternalSyntheticLambda0() {
            }
            public void onClick(final View view) {
                initImgOrderLineTotalClicklambda1(CashAndCreditCardFicheActivity.this, view);
            }
        });
    }
    public static void initImgOrderLineTotalClicklambda1(final CashAndCreditCardFicheActivity this0, final View view) {
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
        if (mTotalFrameOpen) {
            final ImageView imageView = imgReceiptLineTotalUp;
            Intrinsics.checkNotNull(imageView);
            imageView.setImageDrawable(closeTotalDrawable);
        } else {
            final ImageView imageView2 = imgReceiptLineTotalUp;
            Intrinsics.checkNotNull(imageView2);
            imageView2.setImageDrawable(openTotalDrawable);
        }
    }
    public void openFrame() {
        final FrameLayout frameLayout = frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        final ViewPropertyAnimator animate = frameLayout.animate();
        Intrinsics.checkNotNull(frmReceiptLineTotal);
        animate.translationY((r1.getHeight() / 3) * (-2)).setDuration(500L);
        final ImageView imageView = imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(closeTotalDrawable);
        mTotalFrameOpen = true;
    }
    public void closeFrame() {
        final FrameLayout frameLayout = frmReceiptLineTotal;
        Intrinsics.checkNotNull(frameLayout);
        frameLayout.animate().translationY(0.0f).setDuration(500L);
        final ImageView imageView = imgReceiptLineTotalUp;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(openTotalDrawable);
        mTotalFrameOpen = false;
    }
    private void initCopyFiche() {
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        cashCreditFiche.setLogicalRef(0);
        final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche2);
        cashCreditFiche2.setTransfer(0);
        final CashCreditFiche cashCreditFiche3 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche3);
        cashCreditFiche3.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
        final CashCreditFiche cashCreditFiche4 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche4);
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        cashCreditFiche4.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
        final CashCreditFiche cashCreditFiche5 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche5);
        cashCreditFiche5.setgDate(DateAndTimeUtils.nowDateTime());
        final CashCreditFiche cashCreditFiche6 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche6);
        cashCreditFiche6.setEnlem(this.getLatitude());
        final CashCreditFiche cashCreditFiche7 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche7);
        cashCreditFiche7.setBoylam(this.getLongitude());
        final CashCreditFiche cashCreditFiche8 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche8);
        cashCreditFiche8.setFicheref(0);
        final CashCreditFiche cashCreditFiche9 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche9);
        cashCreditFiche9.setFicheNo("");
        final CashCreditFiche cashCreditFiche10 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche10);
        cashCreditFiche10.setInvoiceRef(0);
        final CashCreditFiche cashCreditFiche11 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche11);
        cashCreditFiche11.setVisitInfoId(mAutoVisitId);
        final CashCreditFiche cashCreditFiche12 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche12);
        final ArrayList<CashCreditFicheDetail> details = cashCreditFiche12.getDetails();
        Intrinsics.checkNotNull(details);
        final Iterator<CashCreditFicheDetail> it = details.iterator();
        while (it.hasNext()) {
            final CashCreditFicheDetail next = it.next();
            next.setLogicalRef(0);
            next.setCashCreditId(0);
            if (next.isPayedOnline()) {
                next.setCreditCardNo(new FicheStringProp(""));
                next.setPhoneNumber(new FicheRefProp(-1, -1, ""));
                next.setUse3d(false);
                next.setPaymentOrderNr("");
                next.setApprovalNo(new FicheStringProp(""));
            }
            next.setPayedOnline(false);
        }
    }
    private void loadDefaultValue() {
        final List<Bank> banks;
        final ReceiptFicheDefault ficheDefault = viewModel.getBaseErp().getFicheDefault(mReceiptType);
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        this.loadFicheDefaultParameter(cashCreditFiche.getBranch(), StringUtils.convertStringToInt(ficheDefault.getDefaultDivision()), R.string.column_code, this.getString(R.string.qry_branch_where));
        final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche2);
        this.loadFicheDefaultParameter(cashCreditFiche2.getDivision(), StringUtils.convertStringToInt(ficheDefault.getDefaultDepartment()), R.string.column_code, this.getString(R.string.qry_division_where));
        final CashCreditFiche cashCreditFiche3 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche3);
        this.loadFicheDefaultParameter(cashCreditFiche3.getSpecode(), ficheDefault.getDefaultSpeCode());
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final CashCreditFiche cashCreditFiche4 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche4);
        baseErp.loadFicheDefaultProjectCode(cashCreditFiche4.getProjectCode(), ficheDefault.getDefaultProjectCode());
        final CashCreditFiche cashCreditFiche5 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche5);
        this.loadFicheDefaultParameter(cashCreditFiche5.getCyphcode(), ficheDefault.getDefaultCyphCode());
        final CashCreditFiche cashCreditFiche6 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche6);
        this.loadFicheDefaultParameter(cashCreditFiche6.getTradinggrp(), ficheDefault.getDefaultTradingGroup());
        if (-1 < this.invoicePaymentBankRef && -1 < this.invoicePaymentBankAccRef) {
            final CashCreditFiche cashCreditFiche7 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche7);
            this.loadFicheDefaultParameter(cashCreditFiche7.getBank(), invoicePaymentBankRef, R.string.column_name, this.getString(R.string.qry_bank_where));
            final CashCreditFiche cashCreditFiche8 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche8);
            this.loadFicheDefaultParameter(cashCreditFiche8.getBankAcc(), invoicePaymentBankAccRef, R.string.column_name, this.getString(R.string.qry_bankacc_where));
            return;
        }
        final FicheDiscountRefProp creditCardCollectionDefaultBank = Preferences.getCreditCardCollectionDefaultBank(ContextUtils.getmContext());
        final FicheDiscountRefProp creditCardCollectionDefaultBankAccount = Preferences.getCreditCardCollectionDefaultBankAccount(ContextUtils.getmContext());
        if ((null != creditCardCollectionDefaultBank || null != creditCardCollectionDefaultBankAccount) && (null == (banks = getBanks()) || !banks.isEmpty())) {
            for (final Bank bank : banks) {
                if (null != creditCardCollectionDefaultBank && bank.getLogicalRef() == creditCardCollectionDefaultBank.getLogicalRef()) {
                    final CashCreditFiche cashCreditFiche9 = this.cashCreditFiche;
                    Intrinsics.checkNotNull(cashCreditFiche9);
                    final FicheDiscountRefProp bank2 = cashCreditFiche9.getBank();
                    final CashCreditFiche cashCreditFiche10 = this.cashCreditFiche;
                    Intrinsics.checkNotNull(cashCreditFiche10);
                    this.loadDefaultBankAndBankAccountFromPreferences(bank2, cashCreditFiche10.getBankAcc(), creditCardCollectionDefaultBank, creditCardCollectionDefaultBankAccount);
                    return;
                }
            }
        }
        final CashCreditFiche cashCreditFiche11 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche11);
        final FicheDiscountRefProp bank3 = cashCreditFiche11.getBank();
        final CashCreditFiche cashCreditFiche12 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche12);
        this.loadDefaultBankAndBankAccount(bank3, cashCreditFiche12.getBankAcc(), ficheDefault.getDefaultBank());
    }
    private List<Bank> getBanks() {
        final List<Bank> table = viewModel.getBaseErp().getLogoSqlHelper().getTable(Bank.class);
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Bank>");
        return table;
    }
    private void loadDefaultBankAndBankAccount(final FicheDiscountRefProp ficheDiscountRefProp, final FicheDiscountRefProp ficheDiscountRefProp2, final String str) {
        final List<Bank> banks = this.getBanks();
        if (banks.isEmpty()) {
            return;
        }
        ficheDiscountRefProp.setLogicalRef(banks.get(0).getLogicalRef());
        FicheStringProp.setDefinition(banks.get(0).getDefinition());
        ficheDiscountRefProp.setCode(banks.get(0).getCode());
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        final ErpType erpType2 = ErpType.NETSIS;
        final List table = viewModel.getBaseErp().getLogoSqlHelper().getTable(BankAccount.class, viewModel.getBaseErp().getErpType() == erpType2 ? "BANKCODE=?" : "BANKREF=?", new String[]{erpType == erpType2 ? banks.get(0).getCode() : StringUtils.convertIntToString(banks.get(0).getLogicalRef())});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.BankAccount>");
        if (table.isEmpty()) {
            return;
        }
        ficheDiscountRefProp2.setLogicalRef(((BankAccount) table.get(0)).getLogicalRef());
        FicheStringProp.setDefinition(((BankAccount) table.get(0)).getDefinition());
        ficheDiscountRefProp2.setCode(((BankAccount) table.get(0)).getCode());
    }
    private void loadDefaultBankAndBankAccountFromPreferences(final FicheDiscountRefProp ficheDiscountRefProp, final FicheDiscountRefProp ficheDiscountRefProp2, final FicheDiscountRefProp ficheDiscountRefProp3, final FicheDiscountRefProp ficheDiscountRefProp4) {
        final String[] strArr;
        if (null != ficheDiscountRefProp3) {
            ficheDiscountRefProp.setLogicalRef(ficheDiscountRefProp3.getLogicalRef());
            FicheStringProp.setDefinition(ficheDiscountRefProp3.getDefinition());
            ficheDiscountRefProp.setCode(ficheDiscountRefProp3.getCode());
        }
        if (null != ficheDiscountRefProp4) {
            ficheDiscountRefProp2.setLogicalRef(ficheDiscountRefProp4.getLogicalRef());
            FicheStringProp.setDefinition(ficheDiscountRefProp4.getDefinition());
            ficheDiscountRefProp2.setCode(ficheDiscountRefProp4.getCode());
            return;
        }
        final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        final ErpType erpType2 = ErpType.NETSIS;
        final String str = erpType == erpType2 ? "BANKCODE=?" : "BANKREF=?";
        if (viewModel.getBaseErp().getErpType() == erpType2) {
            Intrinsics.checkNotNull(ficheDiscountRefProp3);
            strArr = new String[]{ficheDiscountRefProp3.getCode()};
        } else {
            Intrinsics.checkNotNull(ficheDiscountRefProp3);
            strArr = new String[]{StringUtils.convertIntToString(ficheDiscountRefProp3.getLogicalRef())};
        }
        final List table = logoSqlHelper.getTable(BankAccount.class, str, strArr);
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.BankAccount>");
        if (table.isEmpty()) {
            return;
        }
        ficheDiscountRefProp2.setLogicalRef(((BankAccount) table.get(0)).getLogicalRef());
        FicheStringProp.setDefinition(((BankAccount) table.get(0)).getDefinition());
        ficheDiscountRefProp2.setCode(((BankAccount) table.get(0)).getCode());
    }
    @SuppressLint(HttpHeaders.RANGE)
    private void loadFicheDefaultParameter(final FicheDiscountRefProp ficheDiscountRefProp, final int i2, @StringRes final int i3, final String str) {
        if (-1 == i2) {
            return;
        }
        ficheDiscountRefProp.setLogicalRef(i2);
        Cursor cursor = null;
        try {
            try {
                final ISqlBriteDatabase logoSqlBriteDatabase = viewModel.getBaseErp().getLogoSqlBriteDatabase();
                Intrinsics.checkNotNull(str);
                cursor = logoSqlBriteDatabase.query(str, StringUtils.convertIntToString(i2));
                if (cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this.getString(i3))));
                }
            } catch (final Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (null == cursor) {
                    return;
                }
            }
            cursor.close();
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }
    private void loadFicheDefaultParameter(final FicheRefProp ficheRefProp, final int i2, @StringRes final int i3, final String str) {
        if (-1 == i2) {
            return;
        }
        ficheRefProp.setLogicalRef(i2);
        Cursor cursor = null;
        try {
            try {
                final ISqlBriteDatabase logoSqlBriteDatabase = viewModel.getBaseErp().getLogoSqlBriteDatabase();
                Intrinsics.checkNotNull(str);
                cursor = logoSqlBriteDatabase.query(str, StringUtils.convertIntToString(i2));
                if (cursor.moveToFirst()) {
                    FicheStringProp.setDefinition(cursor.getString(cursor.getColumnIndex(this.getString(i3))));
                }
            } catch (final Exception e2) {
                Log.e(MobileSales.TAG, "loadFicheDefaultParameter: ", e2);
                if (null == cursor) {
                    return;
                }
            }
            cursor.close();
        } catch (final Throwable th) {
            if (null != cursor) {
                cursor.close();
            }
            throw th;
        }
    }
    private void loadFicheDefaultParameter(final FicheStringProp ficheStringProp, final String str) {
        if (null != str) {
            FicheStringProp.setDefinition(str);
        }
    }
    public boolean onPrepareOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (customerOperation.getFicheMode() == FicheMode.ANALYSE) {
            menu.findItem(R.id.action_save).setVisible(false);
            menu.findItem(R.id.action_detail).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.menu_cash_and_credit_card_fiche, menu);
        return true;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.home:
                this.cancelFiche();
                break;
            case R.id.action_cancel /* 2131296356 */:
                this.cancelFiche();
                break;
            case R.id.action_detail /* 2131296359 */:
                this.addNewDetail(false);
                break;
            case R.id.action_report /* 2131296369 */:
                this.startActivityForResult(new Intent(this, ReportAllActivity.class), 0);
                break;
            case R.id.action_save /* 2131296370 */:
                if (this.checkOneClick() && this.isAutoDateTimeAndZoneEnabled()) {
                    this.saveCashCreditFiche();
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void addNewDetail(final boolean z) {
        final Intent intent = new Intent(this, CashCreditFicheDetailEnterActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
        final String str = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        intent.putExtra(str, this.getIntent().getIntExtra(str, 0));
        final CashCreditFicheDetail cashCreditFicheDetail = new CashCreditFicheDetail(0, 0, null, null, null, null, null, null, null, null, false, false, null, 8191, null);
        cashCreditFicheDetail.init();
        if (z) {
            cashCreditFicheDetail.setTotal(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(invoicePaymentTotal))));
        }
        intent.putExtra(CashAndCreditCardFicheActivity.CASH_CREDIT_DETAIL, cashCreditFicheDetail);
        this.startActivityForResult(intent, 1);
    }
    private void loadPager() {
        final ViewPager viewPager = mViewPager;
        Intrinsics.checkNotNull(viewPager);
        viewPager.setPageMargin(this.getResources().getDimensionPixelOffset(R.dimen.divider));
        final ViewPager viewPager2 = mViewPager;
        Intrinsics.checkNotNull(viewPager2);
        viewPager2.setPageMarginDrawable(R.color.blackT12);
        final FichePagerAdapter fichePagerAdapter = new FichePagerAdapter(this, this.getSupportFragmentManager());
        mAdapter = fichePagerAdapter;
        Intrinsics.checkNotNull(fichePagerAdapter);
        fichePagerAdapter.setCustomerOperation(customerOperation);
        final FichePagerAdapter fichePagerAdapter2 = mAdapter;
        Intrinsics.checkNotNull(fichePagerAdapter2);
        fichePagerAdapter2.setCustomerRef(customerRef);
        final FichePagerAdapter fichePagerAdapter3 = mAdapter;
        Intrinsics.checkNotNull(fichePagerAdapter3);
        fichePagerAdapter3.setInvoiceRef(this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0));
        final ViewPager viewPager3 = mViewPager;
        Intrinsics.checkNotNull(viewPager3);
        viewPager3.setAdapter(mAdapter);
        final TabLayout tabLayout = mTabLayout;
        Intrinsics.checkNotNull(tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        final TabLayout tabLayout2 = mTabLayout;
        Intrinsics.checkNotNull(tabLayout2);
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabUnselected(final TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }
            void CashAndCreditCardFicheActivityloadPager1() {
            }
            public void onTabSelected(final TabLayout.Tab tab) {
                final ScreenControl screenControl;
                final FloatingActionButton floatingActionButton;
                final FloatingActionButton floatingActionButton2;
                Intrinsics.checkNotNullParameter(tab, "tab");
                screenControl = CashAndCreditCardFicheActivity.this.screenControl;
                Intrinsics.checkNotNull(screenControl);
                screenControl.hideKeyboard(CashAndCreditCardFicheActivity.this);
                if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
                    if (0 == tab.getPosition()) {
                        floatingActionButton2 = CashAndCreditCardFicheActivity.this.floatingActionButton;
                        Intrinsics.checkNotNull(floatingActionButton2);
                        floatingActionButton2.setVisibility(View.GONE);
                    } else {
                        floatingActionButton = CashAndCreditCardFicheActivity.this.floatingActionButton;
                        Intrinsics.checkNotNull(floatingActionButton);
                        floatingActionButton.setVisibility(View.VISIBLE);
                    }
                }
            }
            public void onTabReselected(final TabLayout.Tab tab) {
                final Object current;
                final AppBarLayout appBarLayout;
                Intrinsics.checkNotNullParameter(tab, "tab");
                current = getCurrent(Scrollable.class);
                final Scrollable scrollable = (Scrollable) current;
                if (null != scrollable) {
                    scrollable.scrollToTop();
                }
                appBarLayout = mAppBar;
                Intrinsics.checkNotNull(appBarLayout);
                appBarLayout.setExpanded(true, true);
            }
        });
        final ViewPager viewPager4 = mViewPager;
        Intrinsics.checkNotNull(viewPager4);
        viewPager4.setCurrentItem(1);
    }
    public <T> T getCurrent(final Class<T> cls) {
        final FichePagerAdapter fichePagerAdapter = mAdapter;
        if (null == fichePagerAdapter) {
            return null;
        }
        Intrinsics.checkNotNull(fichePagerAdapter);
        final ViewPager viewPager = mViewPager;
        Intrinsics.checkNotNull(viewPager);
        final T t = (T) fichePagerAdapter.getItem(viewPager.getCurrentItem());
        Intrinsics.checkNotNullExpressionValue(t, "getItem(...)");
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }
    private ReceiptType getmReceiptType() {
        return mReceiptType;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private void saveCashCreditFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche);
            if (null != cashCreditFiche.getDetails()) {
                final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
                Intrinsics.checkNotNull(cashCreditFiche2);
                final ArrayList<CashCreditFicheDetail> details = cashCreditFiche2.getDetails();
                Intrinsics.checkNotNull(details);
                if (0 < details.size()) {
                    final ReceiptType receiptType = mReceiptType;
                    final ReceiptType receiptType2 = ReceiptType.CREDIT;
                    if (receiptType == receiptType2) {
                        final CashCreditFiche cashCreditFiche3 = this.cashCreditFiche;
                        Intrinsics.checkNotNull(cashCreditFiche3);
                        if (-1 == cashCreditFiche3.getBank().getLogicalRef()) {
                            Toast.makeText(this, this.getString(R.string.str_question_select_bank), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        final CashCreditFiche cashCreditFiche4 = this.cashCreditFiche;
                        Intrinsics.checkNotNull(cashCreditFiche4);
                        if (-1 == cashCreditFiche4.getBankAcc().getLogicalRef()) {
                            Toast.makeText(this, this.getString(R.string.str_question_select_account), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (mNetsis) {
                            final CashCreditFiche cashCreditFiche5 = this.cashCreditFiche;
                            Intrinsics.checkNotNull(cashCreditFiche5);
                            final String ficheStringProp = cashCreditFiche5.getInstallmentCount().toString();
                            Intrinsics.checkNotNullExpressionValue(ficheStringProp, "toString(...)");
                            final CashCreditFiche cashCreditFiche6 = this.cashCreditFiche;
                            Intrinsics.checkNotNull(cashCreditFiche6);
                            final String code = cashCreditFiche6.getAggrCode().getCode();
                            if (0 < ficheStringProp.length()) {
                                Intrinsics.checkNotNull(code);
                                if (0 == code.length()) {
                                    Toast.makeText(this, this.getString(R.string.str_select_contract_code), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            if (!viewModel.getBaseErp().getLogoSqlHelper().sozlesmeTaksitSayisiniKontrolEt(code, ficheStringProp)) {
                                Toast.makeText(this, this.getString(R.string.str_incorrect_installment_number), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    final MatterSettings matterSettings = mMatterSettings;
                    Intrinsics.checkNotNull(matterSettings);
                    if (matterSettings.isUseMatterNo()) {
                        this.checkMatter();
                        return;
                    }
                    CashAndCreditCardFicheActivity.saveCompletedefault(this, null, 1, null);
                    if (this.mReceiptType == ReceiptType.CASH) {
                        this.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.CASH_COLLECTION);
                        return;
                    } else {
                        if (this.mReceiptType == receiptType2) {
                            this.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.CREDIT_CARD_COLLECTION);
                            return;
                        }
                        return;
                    }
                }
            }
            Toast.makeText(this, this.getString(R.string.str_collection_detail_not_added), Toast.LENGTH_SHORT).show();
            return;
        }
        this.finish();
    }
    static void saveCompletedefault(final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        cashAndCreditCardFicheActivity.saveComplete(str);
    }
    private void saveComplete(final String str) {
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        cashCreditFiche.calculateTotal();
        final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche2);
        cashCreditFiche2.setEnlem(this.getLatitude());
        final CashCreditFiche cashCreditFiche3 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche3);
        cashCreditFiche3.setBoylam(this.getLongitude());
        final CashCreditFiche cashCreditFiche4 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche4);
        cashCreditFiche4.setFicheNo(str);
        if (mNetsis) {
            final CashCreditFiche cashCreditFiche5 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche5);
            cashCreditFiche5.setClCode(viewModel.getBaseErp().getCustomerClCode(customerRef));
        } else {
            final CashCreditFiche cashCreditFiche6 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche6);
            cashCreditFiche6.setClRef(customerRef);
        }
        if (customerOperation.getFicheMode() == FicheMode.NEW || customerOperation.getFicheMode() == FicheMode.COPY) {
            final CashCreditFiche cashCreditFiche7 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche7);
            cashCreditFiche7.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            final CashCreditFiche cashCreditFiche8 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche8);
            final ErpType erpType = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            cashCreditFiche8.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
            final CashCreditFiche cashCreditFiche9 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche9);
            cashCreditFiche9.setgDate(DateAndTimeUtils.nowDateTime());
            final CashAndCreditCardFicheViewModel cashAndCreditCardFicheViewModel = viewModel;
            final CashCreditFiche cashCreditFiche10 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche10);
            final ReceiptType receiptType = mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            cashAndCreditCardFicheViewModel.saveCashCreditFiche(cashCreditFiche10, receiptType, new SalesActivityFicheSaveListener(this));
            return;
        }
        if (customerOperation.getFicheMode() == FicheMode.EDIT) {
            final CashAndCreditCardFicheViewModel cashAndCreditCardFicheViewModel2 = viewModel;
            final CashCreditFiche cashCreditFiche11 = this.cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche11);
            final ReceiptType receiptType2 = mReceiptType;
            Intrinsics.checkNotNull(receiptType2);
            cashAndCreditCardFicheViewModel2.updateCashCreditFiche(cashCreditFiche11, receiptType2, new SalesActivityFicheSaveListener(this));
        }
    }
    public void checkMatter() {
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait_get_matter_no)).show();
        viewModel.getBaseErp().getMaxMatterNo(customerOperation.getFicheType(), mMatterSettings, new MatterCheckListener(this));
    }
    public void onMatterCheck(final String str) {
        mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, this.getString(R.string.exp_45_undefined), Toast.LENGTH_LONG).show();
            CashAndCreditCardFicheActivity.saveCompletedefault(this, null, 1, null);
        } else if (null != str && this.maxMatterNoControl(str)) {
            this.saveComplete(str);
        } else if (null != str) {
            this.setNewMaxMatterNo(str);
        }
    }
    public void onMatterError(final String str) {
        mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private boolean maxMatterNoControl(final String str) {
        final MatterSettings matterSettings = mMatterSettings;
        Intrinsics.checkNotNull(matterSettings);
        return AppUtils.maxMatterNoControl(str, matterSettings.getLastMatterNo());
    }
    public void setNewMaxMatterNo(final String str) {
        ContextUtils.showMatterDialog(this, this.getString(R.string.str_matter_input_last_value_title), mMatterSettings, new ResponseListener<Object>() {
            final String oldMaxNo = "";
            void CashAndCreditCardFicheActivitysetNewMaxMatterNo1(final String str2) {
                r2 = str2;
            }
            public void onResponse(final PrintSlipModel obj) {
                final CashAndCreditCardFicheViewModel cashAndCreditCardFicheViewModel;
                final MatterSettings matterSettings;
                final String valueOf = String.valueOf(obj);
                if (!TextUtils.isEmpty(valueOf)) {
                    cashAndCreditCardFicheViewModel = viewModel;
                    cashAndCreditCardFicheViewModel.getBaseErp().setNewMatter(getApplicationContext(), customerOperation.getFicheType(), valueOf);
                    matterSettings = mMatterSettings;
                    Intrinsics.checkNotNull(matterSettings);
                    matterSettings.setLastMatterNo(valueOf);
                    checkMatter();
                    return;
                }
                setNewMaxMatterNo(r2);
            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                finish();
            }
            public void onFailure(Throwable throwable) { }
            public void onResponse(Boolean aBoolean) { }
            public void onResponse(Sales sales) { }
            public void onResponse(TigerServiceResult tigerServiceResult) { }
            public void onResponse(Object obj) { }
            public void onResponse(ArrayList<Object> obj) { }
            public void onResponse() { }
        });
    }
    private record SalesActivityFicheSaveListener( WeakReference<CashAndCreditCardFicheActivity> cashCreditFicheActivity) implements ResponseListener<Boolean> {
            private SalesActivityFicheSaveListener(final CashAndCreditCardFicheActivity cashCreditFicheActivity) {
                this(new WeakReference<>(cashCreditFicheActivity));
            }
            public void onResponse(final Boolean bool) {
                if (null != this.cashCreditFicheActivity.get()) {
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity = cashCreditFicheActivity.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity);
                    if (cashAndCreditCardFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity2 = cashCreditFicheActivity.get();
                    Intrinsics.checkNotNull(cashAndCreditCardFicheActivity2);
                    Intrinsics.checkNotNull(bool);
                    cashAndCreditCardFicheActivity2.onSaveResult(bool.booleanValue(), "");
                }
            }
            public void onResponse(ArrayList<Boolean> obj) { }
            public void onResponse() { }
            public void onResponse(Sales sales) { }
            public void onResponse(TigerServiceResult tigerServiceResult) { }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                final CashAndCreditCardFicheActivity cashAndCreditCardFicheActivity = cashCreditFicheActivity.get();
                Intrinsics.checkNotNull(cashAndCreditCardFicheActivity);
                cashAndCreditCardFicheActivity.onSaveResult(false, errorMessage);
            }
            public void onFailure(Throwable throwable) { }
    }
    public void onSaveResult(final boolean z, final String str) {
        if (z) {
            Toast.makeText(this, this.getString(R.string.str_fiche_save_successful), Toast.LENGTH_LONG).show();
            this.insertRouteProcess(customerOperation);
            this.finish();
        } else {
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s : %s", Arrays.copyOf(new Object[]{this.getString(R.string.str_fiche_save_on_error), str}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Toast.makeText(this, format, Toast.LENGTH_LONG).show();
        }
    }
    protected void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (1 == i2) {
            if (-1 == i3) {
                Intrinsics.checkNotNull(intent);
                final CashCreditFicheDetail cashCreditFicheDetail = intent.getParcelableExtra(CashAndCreditCardFicheActivity.CASH_CREDIT_DETAIL);
                final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
                Intrinsics.checkNotNull(cashCreditFiche);
                Intrinsics.checkNotNull(cashCreditFicheDetail);
                cashCreditFiche.addNewDetail(cashCreditFicheDetail);
                final FichePagerAdapter fichePagerAdapter = mAdapter;
                Intrinsics.checkNotNull(fichePagerAdapter);
                final Fragment item = fichePagerAdapter.getItem(1);
                Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
                ((CashCreditFicheDetailListFragment) item).refreshList();
                return;
            }
            return;
        }
        if (2 == i2 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            final CashCreditFicheDetail cashCreditFicheDetail2 = intent.getParcelableExtra(CashAndCreditCardFicheActivity.CASH_CREDIT_DETAIL);
            final Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            final int i4 = extras.getInt(CashAndCreditCardFicheActivity.CASH_CREDIT_DETAIL_POSITION);
            final CashCreditFiche cashCreditFiche2 = cashCreditFiche;
            Intrinsics.checkNotNull(cashCreditFiche2);
            final ArrayList<CashCreditFicheDetail> details = cashCreditFiche2.getDetails();
            Intrinsics.checkNotNull(details);
            Intrinsics.checkNotNull(cashCreditFicheDetail2);
            details.set(i4, cashCreditFicheDetail2);
            final FichePagerAdapter fichePagerAdapter2 = mAdapter;
            Intrinsics.checkNotNull(fichePagerAdapter2);
            final Fragment item2 = fichePagerAdapter2.getItem(1);
            Intrinsics.checkNotNullExpressionValue(item2, "getItem(...)");
            ((CashCreditFicheDetailListFragment) item2).refreshList();
        }
    }
    private void cancelFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            if (0 < getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0)) {
                Toast.makeText(this, this.getString(R.string.str_should_collections_save_before_leave_the_fiche), Toast.LENGTH_LONG).show();
                return;
            } else if (customerOperation.getReceiptType() == ReceiptType.CREDIT && this.hasOnlinePayedDetails()) {
                this.showAlertDialog("", this.getString(R.string.str_cannot_cancel_when_paid_online));
                return;
            } else {
                new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_close)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).show();
                return;
            }
        }
        this.finish();
    }
    private boolean hasOnlinePayedDetails() {
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        final ArrayList<CashCreditFicheDetail> details = cashCreditFiche.getDetails();
        Intrinsics.checkNotNull(details);
        final Iterator<CashCreditFicheDetail> it = details.iterator();
        while (it.hasNext()) {
            final CashCreditFicheDetail next = it.next();
            final int component1 = next.component1();
            if (next.component11() && 0 == component1) {
                return true;
            }
        }
        return false;
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }
    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda5(final CashAndCreditCardFicheActivity this0, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-1 != i2) {
            return;
        }
        this0.setResult(0);
        this0.finish();
    }
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.cancelFiche();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
    public void setTotalLayoutWithNotify() {
        final CashCreditFiche cashCreditFiche = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche);
        cashCreditFiche.calculateTotal();
        final TextView textView = txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        final CashCreditFiche cashCreditFiche2 = this.cashCreditFiche;
        Intrinsics.checkNotNull(cashCreditFiche2);
        textView.setText(StringUtils.dFormat(cashCreditFiche2.getTotal()));
    }
    public boolean dispatchTouchEvent(final MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (0 == event.getAction()) {
            final View currentFocus = this.getCurrentFocus();
            if (currentFocus instanceof EditText editText) {
                final Rect rect = new Rect();
                editText.getGlobalVisibleRect(rect);
                if (!rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    editText.clearFocus();
                    final Object systemService = this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                    ((InputMethodManager) systemService).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getCASH_CREDIT_DETAIL() {
            return CASH_CREDIT_DETAIL;
        }

        public String getCASH_CREDIT() {
            return CASH_CREDIT;
        }

        public String getCASH_CREDIT_DETAIL_POSITION() {
            return CASH_CREDIT_DETAIL_POSITION;
        }
    }
}
