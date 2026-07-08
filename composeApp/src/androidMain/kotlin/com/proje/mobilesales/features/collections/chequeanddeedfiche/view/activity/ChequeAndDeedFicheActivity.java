package com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity;

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
import android.view.KeyEvent;
import android.view.Menu;
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
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.interfaces.Scrollable;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
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
import com.proje.mobilesales.features.adapter.FichePagerAdapter;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.repository.ChequeAndDeedFicheRepository;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.fragment.ChequeDeedFicheDetailListFragment;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.viewmodel.ChequeAndDeedFicheViewModel;
import com.proje.mobilesales.features.collections.receipt.model.ReceiptFicheDefault;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import org.springframework.http.HttpHeaders;


public final class ChequeAndDeedFicheActivity extends BaseErpActivity implements MatterCheck {
    public static final int REQUEST_EDIT_DETAIL = 2;
    public static final int REQUEST_NEW_DETAIL = 1;
    private ChequeDeedFiche chequeDeedFiche;
    private Drawable closeTotalDrawable;
    private FloatingActionButton floatingActionButton;
    private FrameLayout frmReceiptLineTotal;
    private ImageView imgReceiptLineTotalUp;
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
    private static final String CHEQUE_DEED_DETAIL = ChequeAndDeedFicheActivity.class.getName() + ".CHEQUE_DEED_DETAIL";
    private static final String CHEQUE_DEED = ChequeAndDeedFicheActivity.class.getName() + ".CHEQUE_DEED";
    private static final String CHEQUE_DEED_DETAIL_POSITION = ChequeAndDeedFicheActivity.class.getName() + ".CHEQUE_DEED_DETAIL_POSITION";
    private final ChequeAndDeedFicheRepository repository = new ChequeAndDeedFicheRepository();
    private final ChequeAndDeedFicheViewModel viewModel = new ChequeAndDeedFicheViewModel(repository);
    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        public void ChequeAndDeedFicheActivityExternalSyntheticLambda1() {
        } 
        public void onClick(final DialogInterface dialogInterface, final int i2) {
            dialogClickListenerlambda4(ChequeAndDeedFicheActivity.this, dialogInterface, i2);
        }
    };
    private String r2;
    private final ChartInterface r1;
    public ChequeDeedFiche getChequeDeedFiche() {
        return chequeDeedFiche;
    }
    public double getInvoicePaymentTotal() {
        return invoicePaymentTotal;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        this.setContentView(R.layout.activity_cheque_and_deed_fiche);
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
        if (receiptType == ReceiptType.CHEQUE) {
            this.setTitle(this.getString(R.string.str_check_collection));
        } else {
            this.setTitle(this.getString(R.string.str_payroll_note_collection));
        }
        mMatterSettings = viewModel.getBaseErp().getMatterSettings(this, customerOperation.getFicheType());
        invoicePaymentTotal = this.getIntent().getDoubleExtra(IntentExtraName.INVOICE_PAYMENTLINE_TOTAL, 0.0d);
        mAutoVisitId = this.getIntent().getIntExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, 0);
        if (customerOperation.getFicheMode() == ficheMode2) {
            this.startNewChequeDeedFiche();
            return;
        }
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_fiche_loading)).setCancelable(false).show();
        viewModel.getChequeDeedFiche(this.getIntent().getIntExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, -1), customerOperation.getReceiptType() != ReceiptType.DEED ? 0 : 1, new ReceiptFicheGetListener(this));
    }
    private void startNewChequeDeedFiche() {
        final ChequeDeedFiche chequeDeedFiche = new ChequeDeedFiche(0, 0, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0.0d, 0, 0, null, 0.0d, 0.0d, 0, null, null, null, 0, 0, null, Integer.MAX_VALUE, null);
        chequeDeedFiche.init();
        chequeDeedFiche.setfType(customerOperation.getReceiptType() == ReceiptType.DEED ? 1 : 0);
        chequeDeedFiche.setVisitInfoId(mAutoVisitId);
        this.startChequeDeedFiche(chequeDeedFiche);
    }
    private void startChequeDeedFiche(final ChequeDeedFiche chequeDeedFiche) {
        if (null == chequeDeedFiche && customerOperation.getFicheMode() != FicheMode.NEW) {
            Toast.makeText(this, this.getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
            onBackPressed();
            return;
        }
        this.chequeDeedFiche = chequeDeedFiche;
        final int intExtra = this.getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0);
        if (0 < intExtra) {
            final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche2);
            chequeDeedFiche2.setInvoiceRef(intExtra);
            final ChequeDeedFiche chequeDeedFiche3 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche3);
            final ArrayList<ChequeDeedFicheDetail> details = chequeDeedFiche3.getDetails();
            Intrinsics.checkNotNull(details);
            details.clear();
            final ChequeDeedFiche chequeDeedFiche4 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche4);
            chequeDeedFiche4.setTotal(0.0d);
        }
        if (customerOperation.getFicheMode() == FicheMode.NEW) {
            this.loadDefaultValue();
        }
        if (customerOperation.getFicheMode() == FicheMode.COPY) {
            this.initCopyFiche();
        }
        if (customerOperation.getFicheMode() == FicheMode.EDIT) {
            final ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            final ChequeDeedFiche chequeDeedFiche5 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche5);
            if (logoSqlHelper.getInvoiceReceiptRelationIfTransfered(chequeDeedFiche5.getInvoiceRef(), false)) {
                Toast.makeText(this, this.getString(R.string.str_invoice_receipt_cannot_edit), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
        }
        this.setTotalLayoutWithNotify();
        this.loadPager();
        final FloatingActionButton floatingActionButton = this.floatingActionButton;
        Intrinsics.checkNotNull(floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() { 
            public void ChequeAndDeedFicheActivityExternalSyntheticLambda0() {
            } 
            public void onClick(final View view) {
                startChequeDeedFichelambda0(ChequeAndDeedFicheActivity.this, view);
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
    public static void startChequeDeedFichelambda0(final ChequeAndDeedFicheActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.addNewDetail(false);
    }
    private record ReceiptFicheGetListener(
            WeakReference<ChequeAndDeedFicheActivity> mAdapter) implements ResponseListener<ChequeDeedFiche> {
            private ReceiptFicheGetListener(final ChequeAndDeedFicheActivity mAdapter) {
                this(new WeakReference<>(mAdapter));
            }
            public void onResponse(final ChequeDeedFiche chequeDeedFiche) {
                if (null != this.mAdapter.get()) {
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity = mAdapter.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity);
                    if (chequeAndDeedFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity2 = mAdapter.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity2);
                    chequeAndDeedFicheActivity2.onFicheGet(chequeDeedFiche, "");
                }
            } 
            public void onResponse(ArrayList<ChequeDeedFiche> obj) {

            } 
            public void onResponse() {

            }
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mAdapter.get()) {
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity = mAdapter.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity);
                    if (chequeAndDeedFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity2 = mAdapter.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity2);
                    chequeAndDeedFicheActivity2.onFicheGet(null, errorMessage);
                }
            } 
            public void onFailure(Throwable throwable) { } 
            public void onResponse(Boolean aBoolean) { } 
            public void onResponse(Sales sales) { } 
            public void onResponse(TigerServiceResult tigerServiceResult) { }
        }
    public void onFicheGet(final Object obj, final String str) {
        mProgressDialogBuilder.dismiss();
        if (null != obj) {
            this.startChequeDeedFiche((ChequeDeedFiche) obj);
        } else {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    } 
    private void setFrameLayoutFields() {
        final String str;
        final TextView textView = txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche);
        textView.setText(StringUtils.dFormat(chequeDeedFiche.getTotal()));
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
            void ChequeAndDeedFicheActivitysetFrameLayoutClick1() {
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
            public void ChequeAndDeedFicheActivityExternalSyntheticLambda2() {
            } 
            public void onClick(final View view) {
                initImgOrderLineTotalClicklambda1(ChequeAndDeedFicheActivity.this, view);
            }
        });
    }
    public static void initImgOrderLineTotalClicklambda1(final ChequeAndDeedFicheActivity this0, final View view) {
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
        final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche);
        chequeDeedFiche.setLogicalRef(0);
        final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche2);
        chequeDeedFiche2.setTransfer(0);
        final ChequeDeedFiche chequeDeedFiche3 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche3);
        chequeDeedFiche3.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
        final ChequeDeedFiche chequeDeedFiche4 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche4);
        final ErpType erpType = viewModel.getBaseErp().getErpType();
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        chequeDeedFiche4.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
        final ChequeDeedFiche chequeDeedFiche5 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche5);
        chequeDeedFiche5.setgDate(DateAndTimeUtils.nowDateTime());
        final ChequeDeedFiche chequeDeedFiche6 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche6);
        chequeDeedFiche6.setEnlem(this.getLatitude());
        final ChequeDeedFiche chequeDeedFiche7 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche7);
        chequeDeedFiche7.setBoylam(this.getLongitude());
        final ChequeDeedFiche chequeDeedFiche8 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche8);
        chequeDeedFiche8.setFicheref(0);
        final ChequeDeedFiche chequeDeedFiche9 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche9);
        chequeDeedFiche9.setFicheNo("");
        final ChequeDeedFiche chequeDeedFiche10 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche10);
        chequeDeedFiche10.setInvoiceRef(0);
        final ChequeDeedFiche chequeDeedFiche11 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche11);
        chequeDeedFiche11.setVisitInfoId(mAutoVisitId);
        final ChequeDeedFiche chequeDeedFiche12 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche12);
        final ArrayList<ChequeDeedFicheDetail> details = chequeDeedFiche12.getDetails();
        Intrinsics.checkNotNull(details);
        final Iterator<ChequeDeedFicheDetail> it = details.iterator();
        while (it.hasNext()) {
            final ChequeDeedFicheDetail next = it.next();
            next.setLogicalRef(0);
            next.setChequeDeedId(0);
        }
    }
    private void loadDefaultValue() {
        final ReceiptFicheDefault ficheDefault = viewModel.getBaseErp().getFicheDefault(mReceiptType);
        final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche);
        this.loadFicheDefaultParameter(chequeDeedFiche.getBranch(), StringUtils.convertStringToInt(ficheDefault.getDefaultDivision()), R.string.column_code, this.getString(R.string.qry_branch_where));
        final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche2);
        this.loadFicheDefaultParameter(chequeDeedFiche2.getDivision(), StringUtils.convertStringToInt(ficheDefault.getDefaultDepartment()), R.string.column_code, this.getString(R.string.qry_division_where));
        final ChequeDeedFiche chequeDeedFiche3 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche3);
        this.loadFicheDefaultParameter(chequeDeedFiche3.getSpecode(), ficheDefault.getDefaultSpeCode());
        final BaseErp<?> baseErp = viewModel.getBaseErp();
        final ChequeDeedFiche chequeDeedFiche4 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche4);
        baseErp.loadFicheDefaultProjectCode(chequeDeedFiche4.getProjectCode(), ficheDefault.getDefaultProjectCode());
        final ChequeDeedFiche chequeDeedFiche5 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche5);
        this.loadFicheDefaultParameter(chequeDeedFiche5.getCyphcode(), ficheDefault.getDefaultCyphCode());
        final ChequeDeedFiche chequeDeedFiche6 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche6);
        this.loadFicheDefaultParameter(chequeDeedFiche6.getTradinggrp(), ficheDefault.getDefaultTradingGroup());
    }
    @SuppressLint(HttpHeaders.RANGE)
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
                    this.saveChequeDeedFiche();
                    break;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void addNewDetail(final boolean z) {
        final Intent intent = new Intent(this, ChequeDeedFicheDetailEnterActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, customerRef);
        final String str = IntentExtraName.INVOICE_PAYMENTLINE_REF;
        intent.putExtra(str, this.getIntent().getIntExtra(str, 0));
        final ChequeDeedFicheDetail chequeDeedFicheDetail = new ChequeDeedFicheDetail(0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
        chequeDeedFicheDetail.init();
        if (z) {
            chequeDeedFicheDetail.setTotal(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(invoicePaymentTotal))));
        }
        intent.putExtra(ChequeAndDeedFicheActivity.CHEQUE_DEED_DETAIL, chequeDeedFicheDetail);
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
        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabUnselected(final TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }
            void ChequeAndDeedFicheActivityloadPager1() {
            }
            public void onTabSelected(final TabLayout.Tab tab) {
                final ScreenControl screenControl;
                final FloatingActionButton floatingActionButton;
                final FloatingActionButton floatingActionButton2;
                Intrinsics.checkNotNullParameter(tab, "tab");
                screenControl = ChequeAndDeedFicheActivity.this.screenControl;
                Intrinsics.checkNotNull(screenControl);
                screenControl.hideKeyboard(ChequeAndDeedFicheActivity.this);
                if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
                    if (0 == tab.getPosition()) {
                        floatingActionButton2 = ChequeAndDeedFicheActivity.this.floatingActionButton;
                        Intrinsics.checkNotNull(floatingActionButton2);
                        floatingActionButton2.setVisibility(View.GONE);
                    } else {
                        floatingActionButton = ChequeAndDeedFicheActivity.this.floatingActionButton;
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
                if (scrollable != null) {
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
    public ReceiptType getmReceiptType() {
        return mReceiptType;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private void saveChequeDeedFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche);
            if (!TextUtils.isEmpty(chequeDeedFiche.getChequeDeedDate().toString())) {
                final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
                Intrinsics.checkNotNull(chequeDeedFiche2);
                if (null != chequeDeedFiche2.getDetails()) {
                    final ChequeDeedFiche chequeDeedFiche3 = this.chequeDeedFiche;
                    Intrinsics.checkNotNull(chequeDeedFiche3);
                    final ArrayList<ChequeDeedFicheDetail> details = chequeDeedFiche3.getDetails();
                    Intrinsics.checkNotNull(details);
                    if (0 < details.size()) {
                        final MatterSettings matterSettings = mMatterSettings;
                        Intrinsics.checkNotNull(matterSettings);
                        if (matterSettings.isUseMatterNo()) {
                            this.checkMatter();
                            return;
                        }
                        ChequeAndDeedFicheActivity.saveCompletedefault(this, null, 1, null);
                        final ReceiptType receiptType = mReceiptType;
                        if (receiptType == ReceiptType.CHEQUE) {
                            this.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.CHEQUE_COLLECTION);
                            return;
                        } else {
                            if (receiptType == ReceiptType.DEED) {
                                this.baseLogOperationFirebaseAnalyticsData(AnalyticsOperationType.DEED_COLLECTION);
                                return;
                            }
                            return;
                        }
                    }
                }
                Toast.makeText(this, this.getString(R.string.str_collection_detail_not_added), Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, this.getString(R.string.exp_payroll_date), Toast.LENGTH_SHORT).show();
            return;
        }
        this.finish();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    static void saveCompletedefault(final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity, String str, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = "";
        }
        chequeAndDeedFicheActivity.saveComplete(str);
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private void saveComplete(final String str) {
        final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche);
        chequeDeedFiche.calculateTotal();
        final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche2);
        chequeDeedFiche2.setEnlem(this.getLatitude());
        final ChequeDeedFiche chequeDeedFiche3 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche3);
        chequeDeedFiche3.setBoylam(this.getLongitude());
        final ChequeDeedFiche chequeDeedFiche4 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche4);
        chequeDeedFiche4.setFicheNo(str);
        if (mNetsis) {
            final ChequeDeedFiche chequeDeedFiche5 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche5);
            chequeDeedFiche5.setClCode(viewModel.getBaseErp().getCustomerClCode(customerRef));
        } else {
            final ChequeDeedFiche chequeDeedFiche6 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche6);
            chequeDeedFiche6.setClRef(customerRef);
        }
        if (customerOperation.getFicheMode() == FicheMode.NEW || customerOperation.getFicheMode() == FicheMode.COPY) {
            final ChequeDeedFiche chequeDeedFiche7 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche7);
            chequeDeedFiche7.setDateInt(DateAndTimeUtils.getDateInt(DateAndTimeUtils.nowDate()));
            final ChequeDeedFiche chequeDeedFiche8 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche8);
            final ErpType erpType = viewModel.getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            chequeDeedFiche8.setAndFicheNo(StringUtils.getCreateAndFicheNo(erpType, 3));
            final ChequeDeedFiche chequeDeedFiche9 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche9);
            chequeDeedFiche9.setgDate(DateAndTimeUtils.nowDateTime());
            final ChequeAndDeedFicheViewModel chequeAndDeedFicheViewModel = viewModel;
            final ChequeDeedFiche chequeDeedFiche10 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche10);
            final ReceiptType receiptType = mReceiptType;
            Intrinsics.checkNotNull(receiptType);
            chequeAndDeedFicheViewModel.saveChequeDeedFiche(chequeDeedFiche10, receiptType, new SalesActivityFicheSaveListener(this));
            return;
        }
        if (customerOperation.getFicheMode() == FicheMode.EDIT) {
            final ChequeAndDeedFicheViewModel chequeAndDeedFicheViewModel2 = viewModel;
            final ChequeDeedFiche chequeDeedFiche11 = this.chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche11);
            final ReceiptType receiptType2 = mReceiptType;
            Intrinsics.checkNotNull(receiptType2);
            chequeAndDeedFicheViewModel2.updateChequeDeedFiche(chequeDeedFiche11, receiptType2, new SalesActivityFicheSaveListener(this));
        }
    }
    public void checkMatter() {
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait_get_matter_no)).show();
        viewModel.getBaseErp().getMaxMatterNo(customerOperation.getFicheType(), mMatterSettings, new MatterCheckListener(this));
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onMatterCheck(final String str) {
        mProgressDialogBuilder.dismiss();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, this.getString(R.string.exp_45_undefined), Toast.LENGTH_LONG).show();
            ChequeAndDeedFicheActivity.saveCompletedefault(this, null, 1, null);
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
        ContextUtils.showMatterDialog(this, this.getString(R.string.str_matter_input_last_value_title), mMatterSettings, new ResponseListener<Object>() { // from class: com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivitysetNewMaxMatterNo1
            private final String oldMaxNo = "";
            void ChequeAndDeedFicheActivitysetNewMaxMatterNo1(final String str2) {
                r2 = str2;
            }
            public void onResponse(final PrintSlipModel obj) {
                final ChequeAndDeedFicheViewModel chequeAndDeedFicheViewModel;
                final MatterSettings matterSettings;
                final String valueOf = String.valueOf(obj);
                if (!TextUtils.isEmpty(valueOf)) {
                    chequeAndDeedFicheViewModel = viewModel;
                    chequeAndDeedFicheViewModel.getBaseErp().setNewMatter(getApplicationContext(), customerOperation.getFicheType(), valueOf);
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
    private record SalesActivityFicheSaveListener(
            WeakReference<ChequeAndDeedFicheActivity> chequeDeedFicheActivity) implements ResponseListener<Boolean> {
            private SalesActivityFicheSaveListener(final ChequeAndDeedFicheActivity chequeDeedFicheActivity) {
                this(new WeakReference<>(chequeDeedFicheActivity));
            } 
            
            public void onResponse(final Boolean bool) {
                if (null != this.chequeDeedFicheActivity.get()) {
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity = chequeDeedFicheActivity.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity);
                    if (chequeAndDeedFicheActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity2 = chequeDeedFicheActivity.get();
                    Intrinsics.checkNotNull(chequeAndDeedFicheActivity2);
                    Intrinsics.checkNotNull(bool);
                    chequeAndDeedFicheActivity2.onSaveResult(bool.booleanValue(), "");
                }
            }

        
        public void onResponse(ArrayList<Boolean> obj) {

        }

        
        public void onResponse() {

        }

        
        public void onResponse(Sales sales) {

        }

        
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }

        public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                final ChequeAndDeedFicheActivity chequeAndDeedFicheActivity = chequeDeedFicheActivity.get();
                Intrinsics.checkNotNull(chequeAndDeedFicheActivity);
                chequeAndDeedFicheActivity.onSaveResult(false, errorMessage);
            } 
        public void onFailure(Throwable throwable) {
            
        }
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
                final ChequeDeedFicheDetail chequeDeedFicheDetail = intent.getParcelableExtra(ChequeAndDeedFicheActivity.CHEQUE_DEED_DETAIL);
                final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
                Intrinsics.checkNotNull(chequeDeedFiche);
                Intrinsics.checkNotNull(chequeDeedFicheDetail);
                chequeDeedFiche.addNewDetail(chequeDeedFicheDetail);
                final FichePagerAdapter fichePagerAdapter = mAdapter;
                Intrinsics.checkNotNull(fichePagerAdapter);
                final Fragment item = fichePagerAdapter.getItem(1);
                Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
                ((ChequeDeedFicheDetailListFragment) item).refreshList();
                return;
            }
            return;
        }
        if (2 == i2 && -1 == i3) {
            Intrinsics.checkNotNull(intent);
            final ChequeDeedFicheDetail chequeDeedFicheDetail2 = intent.getParcelableExtra(ChequeAndDeedFicheActivity.CHEQUE_DEED_DETAIL);
            final Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            final int i4 = extras.getInt(ChequeAndDeedFicheActivity.CHEQUE_DEED_DETAIL_POSITION);
            final ChequeDeedFiche chequeDeedFiche2 = chequeDeedFiche;
            Intrinsics.checkNotNull(chequeDeedFiche2);
            final ArrayList<ChequeDeedFicheDetail> details = chequeDeedFiche2.getDetails();
            Intrinsics.checkNotNull(details);
            Intrinsics.checkNotNull(chequeDeedFicheDetail2);
            details.set(i4, chequeDeedFicheDetail2);
            final FichePagerAdapter fichePagerAdapter2 = mAdapter;
            Intrinsics.checkNotNull(fichePagerAdapter2);
            final Fragment item2 = fichePagerAdapter2.getItem(1);
            Intrinsics.checkNotNullExpressionValue(item2, "getItem(...)");
            ((ChequeDeedFicheDetailListFragment) item2).refreshList();
        }
    }
    private void cancelFiche() {
        if (customerOperation.getFicheMode() != FicheMode.ANALYSE) {
            if (0 < getIntent().getIntExtra(IntentExtraName.INVOICE_PAYMENTLINE_REF, 0)) {
                Toast.makeText(this, this.getString(R.string.str_should_collections_save_before_leave_the_fiche), Toast.LENGTH_LONG).show();
                return;
            } else {
                new AlertDialog.Builder(this).setMessage(this.getString(R.string.str_question_want_close)).setPositiveButton(this.getString(R.string.str_yes), dialogClickListener).setNegativeButton(this.getString(R.string.str_no), dialogClickListener).show();
                return;
            }
        }
        this.finish();
    }
    public DialogInterface.OnClickListener getDialogClickListener() {
        return dialogClickListener;
    }
    public void setDialogClickListener(final DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        dialogClickListener = onClickListener;
    }
    public static void dialogClickListenerlambda4(final ChequeAndDeedFicheActivity this0, final DialogInterface dialogInterface, final int i2) {
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
        final ChequeDeedFiche chequeDeedFiche = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche);
        chequeDeedFiche.calculateTotal();
        final TextView textView = txtReceiptTotal;
        Intrinsics.checkNotNull(textView);
        final ChequeDeedFiche chequeDeedFiche2 = this.chequeDeedFiche;
        Intrinsics.checkNotNull(chequeDeedFiche2);
        textView.setText(StringUtils.dFormat(chequeDeedFiche2.getTotal()));
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

        public String getCHEQUE_DEED_DETAIL() {
            return CHEQUE_DEED_DETAIL;
        }

        public String getCHEQUE_DEED() {
            return CHEQUE_DEED;
        }

        public String getCHEQUE_DEED_DETAIL_POSITION() {
            return CHEQUE_DEED_DETAIL_POSITION;
        }
    }
}
