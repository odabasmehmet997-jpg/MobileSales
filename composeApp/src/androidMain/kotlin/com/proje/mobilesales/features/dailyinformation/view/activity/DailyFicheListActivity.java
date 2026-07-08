package com.proje.mobilesales.features.dailyinformation.view.activity;

import android.Manifest;
import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.asynctask.TransferAutoAsyncTask;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseFicheActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.OnOptionsMenu;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.casefiche.view.activity.CaseFicheActivity;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.view.activity.CashAndCreditCardFicheActivity;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.view.activity.ChequeAndDeedFicheActivity;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptFicheShortType;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.dailyinformation.adapter.DailyInfoRecyclerViewAdapter;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.dailyinformation.repository.DailyInformationRepository;
import com.proje.mobilesales.features.dailyinformation.viewmodel.DailyInformationViewModel;
import com.proje.mobilesales.features.model.TigerSqlModel;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.view.activity.VisitEnterActivityNew;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.greenrobot.eventbus.EventBus;
import org.springframework.http.HttpHeaders;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static com.proje.mobilesales.core.utils.AppUtils.calculateTotals;

public final class DailyFicheListActivity extends BaseErpActivity {
    private static final int CANCEL = 5;
    private static final int COPY = 6;
    public static final Companion Companion = new Companion(null);
    private static final int DELETE = 1;
    private static final int EDIT = 0;
    private static final int PRINTED = 4;
    private static final String STATE_GET_PRINT_FICHE = "state:getPrintFiche";
    private static final String STATE_LOGO_TRANSFER_FICHE_REF = "state:logoTransferFicheRef";
    private static final String TAG = "DailyFicheListActivity";
    private List<DailyInfo> dailyInfoList;
    private DialogInterface.OnClickListener dialogClickListener;
    private final DialogInterface.OnClickListener dialogClickListenerSaleCondition;
    private final DialogInterface.OnClickListener dialogClickListenerSaleConditionLocal;
    private LinearLayout emptyView;
    private ArrayList<String> fisType;
    private int logoFicheRef;
    private int mCopyRight;
    private DailyInfo mDailyInfo;
    private DailyInfoRecyclerViewAdapter mDailyInfoRecyclerViewAdapter;
    private int mDeletionRight;
    private final DialogInterface.OnClickListener mDialogClickListenerCancel;
    private int mEditRight;
    private int mGetPrintFiche;
    private FicheMode mLastFicheMode;
    private ReceiptType mLastReceiptType;
    private ProgressDialogBuilder<?> mTotalProgressDialogBuilder;
    private MenuItem mnCancel;
    private MenuItem mnCopy;
    private MenuItem mnDelete;
    private MenuItem mnEdit;
    private MenuItem mnPrinted;
    private ProgressDialogBuilder<?> progressDialogBuilder;
    private final DailyInformationRepository repository;
    private RecyclerView rvDailyInfo;
    private AppCompatSpinner spnFicheType;
    private final DailyInformationViewModel viewModel;
 
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;
        public static final int[] EnumSwitchMapping1;

        static {
            int[] iArr = new int[ReceiptFicheShortType.values().length];
            try {
                iArr[ReceiptFicheShortType.CASHFTYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReceiptFicheShortType.CREDITFTYPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReceiptFicheShortType.CASEFTYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReceiptFicheShortType.CHEQUEFTYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReceiptFicheShortType.DEEDFTYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = iArr;
            int[] iArr2 = new int[ReceiptType.values().length];
            try {
                iArr2[ReceiptType.CASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ReceiptType.CREDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ReceiptType.CASE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ReceiptType.CHEQUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[ReceiptType.DEED.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            EnumSwitchMapping1 = iArr2;
        }
    }

    public DailyFicheListActivity() {
        DailyInformationRepository dailyInformationRepository = new DailyInformationRepository();
        this.repository = dailyInformationRepository;
        this.viewModel = new DailyInformationViewModel(dailyInformationRepository);
        this.mCopyRight = 1;
        this.dialogClickListener = new DialogInterface.OnClickListener() {
            public void DailyFicheListActivityExternalSyntheticLambda0() {
            }

            public void onClick(DialogInterface dialogInterface, int i2) {
                DailyFicheListActivity.dialogClickListenerlambda5(DailyFicheListActivity.this, dialogInterface, i2);
            }
        };
        this.dialogClickListenerSaleCondition = new DialogInterface.OnClickListener() {
            public void DailyFicheListActivityExternalSyntheticLambda1() {
            }

            public void onClick(DialogInterface dialogInterface, int i2) {
                DailyFicheListActivity.dialogClickListenerSaleConditionlambda6(DailyFicheListActivity.this, dialogInterface, i2);
            }
        };
        this.dialogClickListenerSaleConditionLocal = new DialogInterface.OnClickListener() {
            public void DailyFicheListActivityExternalSyntheticLambda2() {
            }
            public void onClick(DialogInterface dialogInterface, int i2) {
                DailyFicheListActivity.dialogClickListenerSaleConditionLocallambda7(DailyFicheListActivity.this, dialogInterface, i2);
            }
        };
        this.mDialogClickListenerCancel = new DialogInterface.OnClickListener() {
            public void DailyFicheListActivityExternalSyntheticLambda3() {
            }
            public void onClick(DialogInterface dialogInterface, int i2) {
                DailyFicheListActivity.mDialogClickListenerCancellambda9(DailyFicheListActivity.this, dialogInterface, i2);
            }
        };
    }

    public DailyInformationRepository getRepository() {
        return this.repository;
    }

    public DailyInformationViewModel getViewModel() {
        return this.viewModel;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.listbycomboaddbutton);
        setToolbar();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.progressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity);
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mTotalProgressDialogBuilder = new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        if (bundle != null) {
            this.mGetPrintFiche = bundle.getInt(STATE_GET_PRINT_FICHE, -1);
            this.logoFicheRef = bundle.getInt(STATE_LOGO_TRANSFER_FICHE_REF, -1);
        } else {
            this.mGetPrintFiche = -1;
            this.logoFicheRef = -1;
        }
        this.mDailyInfoRecyclerViewAdapter = new DailyInfoRecyclerViewAdapter(this);
        MainActivity.sFicheRef = -1;
        findViewById(R.id.btnAdd).setVisibility(View.GONE);
        this.rvDailyInfo = findViewById(R.id.lvList_bycombo);
        this.emptyView = findViewById(R.id.noEntry);
        DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter = this.mDailyInfoRecyclerViewAdapter;
        Intrinsics.checkNotNull(dailyInfoRecyclerViewAdapter);
        dailyInfoRecyclerViewAdapter.getOptions(new OnOptionsMenu() {
            public void DailyFicheListActivityExternalSyntheticLambda4() {
            }
            public void chooseOptions(int i2) {
                DailyFicheListActivity.onCreatelambda0(DailyFicheListActivity.this, i2);
            }
        });
        loadFicheList();
        loadFicheType();
        setFicheTypeSpin();
        setTitle();
    }

    public static void onCreatelambda0(DailyFicheListActivity this0, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.chooseOption(i2);
    }

    private void chooseOption(int i2) {
        MenuItem menuItem;
        MenuItem menuItem2;
        MenuItem menuItem3;
        MenuItem menuItem4;
        List emptyList;
        MenuItem menuItem5;
        MenuItem menuItem6;
        List emptyList2;
        List<DailyInfo> list = this.dailyInfoList;
        Intrinsics.checkNotNull(list);
        MainActivity.sFicheRef = list.get(i2).logicalRef;
        List<DailyInfo> list2 = this.dailyInfoList;
        Intrinsics.checkNotNull(list2);
        this.logoFicheRef = list2.get(i2).ficheRef;
        List<DailyInfo> list3 = this.dailyInfoList;
        Intrinsics.checkNotNull(list3);
        this.mDailyInfo = list3.get(i2);
        MenuItem menuItem7 = this.mnEdit;
        if (menuItem7 != null) {
            Intrinsics.checkNotNull(menuItem7);
            menuItem7.setVisible(true);
        }
        MenuItem menuItem8 = this.mnDelete;
        if (menuItem8 != null) {
            Intrinsics.checkNotNull(menuItem8);
            menuItem8.setVisible(true);
        }
        if (FTypeControlUtils.isMainFTypeOneToOne() || FTypeControlUtils.isMainFTypeWhTransfer()) {
            MenuItem menuItem9 = this.mnPrinted;
            if (menuItem9 != null) {
                Intrinsics.checkNotNull(menuItem9);
                menuItem9.setVisible(false);
            }
        } else {
            MenuItem menuItem10 = this.mnPrinted;
            if (menuItem10 != null) {
                Intrinsics.checkNotNull(menuItem10);
                menuItem10.setVisible(true);
            }
        }
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.openOptionsMenu();
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            Cursor query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT STATUS FROM ORDERS WHERE LOGICALREF=?", String.valueOf(MainActivity.sFicheRef));
            if (query == null) {
                return;
            }
            query.moveToPosition(0);
            if (query.getCount() != 0) {
                int convertStringToInt = StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().dbVal(query, "STATUS", ColType.sayi).toString());
                List arrayList = new ArrayList();
                try {
                    String upVal = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("19");
                    Intrinsics.checkNotNullExpressionValue(upVal, "upVal(...)");
                    int length = upVal.length() - 1;
                    int i3 = 0;
                    boolean z = false;
                    while (i3 <= length) {
                        boolean z2 = Intrinsics.compare(upVal.charAt(!z ? i3 : length), 32) <= 0;
                        if (z) {
                            if (!z2) {
                                break;
                            } else {
                                length--;
                            }
                        } else if (z2) {
                            i3++;
                        } else {
                            z = true;
                        }
                    }
                    List<String> split = new Regex("\\s*,\\s*").split(upVal.subSequence(i3, length + 1).toString(), 0);
                    if (!split.isEmpty()) {
                        ListIterator<String> listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (listIterator.previous().length() != 0) {
                                emptyList2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList2 = CollectionsKt.emptyList();
                    String[] strArr = (String[]) emptyList2.toArray(new String[0]);
                    arrayList = CollectionsKt.listOf(Arrays.copyOf(strArr, strArr.length));
                } catch (Exception e2) {
                    Log.e(TAG, "Error parsing parameter 19", e2);
                }
                if (convertStringToInt != 1) {
                    if (convertStringToInt == 2) {
                        if (!arrayList.contains(ExifInterface.GPS_MEASUREMENT_2D) && (menuItem5 = this.mnEdit) != null) {
                            Intrinsics.checkNotNull(menuItem5);
                            menuItem5.setVisible(false);
                        }
                    } else if (convertStringToInt == 4 && !arrayList.contains(BuildConfig.NETSIS_DEMO_PASSWORD) && (menuItem6 = this.mnEdit) != null) {
                        Intrinsics.checkNotNull(menuItem6);
                        menuItem6.setVisible(false);
                    }
                } else if (!arrayList.contains("0") && (menuItem = this.mnEdit) != null) {
                    Intrinsics.checkNotNull(menuItem);
                    menuItem.setVisible(false);
                }
                List arrayList2 = new ArrayList();
                try {
                    String upVal2 = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("18");
                    Intrinsics.checkNotNullExpressionValue(upVal2, "upVal(...)");
                    int length2 = upVal2.length() - 1;
                    int i4 = 0;
                    boolean z3 = false;
                    while (i4 <= length2) {
                        boolean z4 = Intrinsics.compare(upVal2.charAt(!z3 ? i4 : length2), 32) <= 0;
                        if (z3) {
                            if (!z4) {
                                break;
                            } else {
                                length2--;
                            }
                        } else if (z4) {
                            i4++;
                        } else {
                            z3 = true;
                        }
                    }
                    List<String> split2 = new Regex("\\s*,\\s*").split(upVal2.subSequence(i4, length2 + 1).toString(), 0);
                    if (!split2.isEmpty()) {
                        ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                        while (listIterator2.hasPrevious()) {
                            if (listIterator2.previous().length() != 0) {
                                emptyList = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList = CollectionsKt.emptyList();
                    String[] strArr2 = (String[]) emptyList.toArray(new String[0]);
                    arrayList2 = CollectionsKt.listOf(Arrays.copyOf(strArr2, strArr2.length));
                } catch (Exception e3) {
                    Log.e(TAG, "Error on parsing parameter 18", e3);
                }
                if (convertStringToInt != 1) {
                    if (convertStringToInt == 2) {
                        if (!arrayList2.contains(ExifInterface.GPS_MEASUREMENT_2D) && (menuItem3 = this.mnDelete) != null) {
                            Intrinsics.checkNotNull(menuItem3);
                            menuItem3.setVisible(false);
                        }
                    } else if (convertStringToInt == 4 && !arrayList2.contains("4") && (menuItem4 = this.mnDelete) != null) {
                        Intrinsics.checkNotNull(menuItem4);
                        menuItem4.setVisible(false);
                    }
                } else if (!arrayList2.contains("0") && (menuItem2 = this.mnDelete) != null) {
                    Intrinsics.checkNotNull(menuItem2);
                    menuItem2.setVisible(false);
                }
            }
        } else {
            if (this.mDeletionRight == 0) {
                MenuItem menuItem11 = this.mnDelete;
                Intrinsics.checkNotNull(menuItem11);
                menuItem11.setVisible(true);
            } else {
                MenuItem menuItem12 = this.mnDelete;
                Intrinsics.checkNotNull(menuItem12);
                menuItem12.setVisible(false);
            }
            if (this.mEditRight == 0) {
                MenuItem menuItem13 = this.mnEdit;
                Intrinsics.checkNotNull(menuItem13);
                menuItem13.setVisible(true);
            } else {
                MenuItem menuItem14 = this.mnEdit;
                Intrinsics.checkNotNull(menuItem14);
                menuItem14.setVisible(false);
            }
            if (this.mCopyRight == 0) {
                MenuItem menuItem15 = this.mnCopy;
                Intrinsics.checkNotNull(menuItem15);
                menuItem15.setVisible(true);
            } else {
                MenuItem menuItem16 = this.mnCopy;
                Intrinsics.checkNotNull(menuItem16);
                menuItem16.setVisible(false);
            }
        }
        if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice()) {
            MenuItem menuItem17 = this.mnCancel;
            Intrinsics.checkNotNull(menuItem17);
            menuItem17.setVisible(this.viewModel.getInvoiceCancelFiche());
        } else {
            MenuItem menuItem18 = this.mnCancel;
            Intrinsics.checkNotNull(menuItem18);
            menuItem18.setVisible(false);
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onResume() {
        super.onResume();
        loadFicheList();
        if (this.mDailyInfo == null || !FTypeControlUtils.isMainFTypeCashOrCreditOrCaseReceipt()) {
            return;
        }
        DailyInfo dailyInfo = this.mDailyInfo;
        Intrinsics.checkNotNull(dailyInfo);
        if (dailyInfo.isTransfer()) {
            return;
        }
        DailyInfo dailyInfo2 = this.mDailyInfo;
        Intrinsics.checkNotNull(dailyInfo2);
        TransferAutoAsyncTask.autoFicheTransfer(dailyInfo2.ficheRef);
    }

    public void setTitle() {
        if (FTypeControlUtils.isMainFTypeAll()) {
            setTitle(getString(R.string.str_select_all));
            return;
        }
        if (FTypeControlUtils.isMainFTypeOrder()) {
            setTitle(getString(R.string.str_orders));
            return;
        }
        if (FTypeControlUtils.isMainFTypeDemand()) {
            setTitle(getString(R.string.str_demands));
            return;
        }
        if (FTypeControlUtils.isMainFTypeDispatchOrOneToOne()) {
            if (FTypeControlUtils.isMainFTypeOneToOne()) {
                setTitle(getString(R.string.str_exchange_entry));
            } else if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                setTitle(getString(R.string.str_dispatches));
            } else {
                setTitle(getString(R.string.str_return_dispatch));
            }
            String upVal = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("44");
            Intrinsics.checkNotNullExpressionValue(upVal, "upVal(...)");
            this.mDeletionRight = StringUtils.paramValueTrueCheck(upVal) ? 1 : 0;
            String upVal2 = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("45");
            Intrinsics.checkNotNullExpressionValue(upVal2, "upVal(...)");
            this.mEditRight = StringUtils.paramValueTrueCheck(upVal2) ? 1 : 0;
            return;
        }
        if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice()) {
            if (FTypeControlUtils.isMainFTypeInvoice()) {
                if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                    setTitle(getString(R.string.str_invoices));
                } else {
                    setTitle(getString(R.string.str_return_invoice));
                }
            } else if (FTypeControlUtils.isMainFTypeRetailInvoice()) {
                if (FTypeControlUtils.isMainFatIrsTuruNormal()) {
                    setTitle(getString(R.string.str_sales_retail_invoice));
                } else {
                    setTitle(getString(R.string.str_sales_retail_return_invoice));
                }
            }
            String upVal3 = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("67");
            Intrinsics.checkNotNullExpressionValue(upVal3, "upVal(...)");
            this.mDeletionRight = StringUtils.paramValueTrueCheck(upVal3) ? 1 : 0;
            String upVal4 = this.viewModel.getBaseErp().getLogoSqlHelper().upVal("68");
            Intrinsics.checkNotNullExpressionValue(upVal4, "upVal(...)");
            this.mEditRight = StringUtils.paramValueTrueCheck(upVal4) ? 1 : 0;
            boolean mCancel = this.viewModel.getInvoiceCancelFiche();
            return;
        }
        if (FTypeControlUtils.isMainFTypeCashReceipt()) {
            setTitle(getString(R.string.str_cash_collections));
            this.mDeletionRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCashDeleteRight()));
            this.mEditRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCashEditRight()));
            this.mCopyRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCashCopyRight()));
            return;
        }
        if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            setTitle(getString(R.string.str_case_collections));
            this.mDeletionRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCaseCashDeleteRight()));
            this.mEditRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCaseCashEditRight()));
            this.mCopyRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCaseCashCopyRight()));
            return;
        }
        if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
            setTitle(getString(R.string.str_credit_cart_collections));
            this.mDeletionRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCreditCardDeleteRight()));
            this.mEditRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCreditCardEditRight()));
            this.mCopyRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getCreditCardCopyRight()));
            return;
        }
        if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
            setTitle(getString(R.string.str_check_collections));
            this.mDeletionRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getChequeDeleteRight()));
            this.mEditRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getChequeEditRight()));
            this.mCopyRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getChequeCopyRight()));
            return;
        }
        if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
            setTitle(getString(R.string.str_payroll_note_collections));
            this.mDeletionRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getDeedDeleteRight()));
            this.mEditRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getDeedEditRight()));
            this.mCopyRight = StringUtils.convertBooleanToIntInverse(Boolean.valueOf(this.viewModel.getDeedCopyRight()));
            return;
        }
        if (FTypeControlUtils.isMainFTypeVisit()) {
            setTitle(getString(R.string.str_visit));
        } else if (FTypeControlUtils.isMainFTypeWhTransfer()) {
            setTitle(getString(R.string.str_sales_transfer));
        }
    }

    private void setFicheTypeSpin() {
        String str;
        if (FTypeControlUtils.isMainFTypeOrder()) {
            str = getString(R.string.str_order);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeInvoice()) {
            if (FTypeControlUtils.isMainFatIrsTuruIade()) {
                str = getString(R.string.str_return_invoice);
                Intrinsics.checkNotNull(str);
            } else {
                str = getString(R.string.str_invoice);
                Intrinsics.checkNotNull(str);
            }
        } else if (FTypeControlUtils.isMainFTypeDispatch()) {
            if (FTypeControlUtils.isMainFatIrsTuruIade()) {
                str = getString(R.string.str_return_dispatch);
                Intrinsics.checkNotNull(str);
            } else {
                str = getString(R.string.str_dispatch);
                Intrinsics.checkNotNull(str);
            }
        } else if (FTypeControlUtils.isMainFTypeCashReceipt()) {
            str = getString(R.string.str_cash_collection);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            str = getString(R.string.str_safe_deposit);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
            str = getString(R.string.str_credit_card_slip);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
            str = getString(R.string.str_check_collection);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
            str = getString(R.string.str_payroll_note_collection);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeOneToOne()) {
            str = getString(R.string.str_exchange);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeDemand()) {
            str = getString(R.string.str_demand);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        } else if (FTypeControlUtils.isMainFTypeRetailInvoice()) {
            if (FTypeControlUtils.isMainFatIrsTuruIade()) {
                str = getString(R.string.str_sales_retail_return_invoice);
                Intrinsics.checkNotNull(str);
            } else {
                str = getString(R.string.str_sales_retail_invoice);
                Intrinsics.checkNotNull(str);
            }
        } else if (!FTypeControlUtils.isMainFTypeWhTransfer()) {
            str = "";
        } else {
            str = getString(R.string.str_sales_transfer);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        ArrayList<String> arrayList = this.fisType;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<String> arrayList2 = this.fisType;
            Intrinsics.checkNotNull(arrayList2);
            if (Intrinsics.areEqual(arrayList2.get(i2), str)) {
                AppCompatSpinner appCompatSpinner = this.spnFicheType;
                Intrinsics.checkNotNull(appCompatSpinner);
                appCompatSpinner.setSelection(i2);
                return;
            }
        }
    }

    private void loadFicheType() {
        boolean z;
        this.spnFicheType = findViewById(R.id.spnFicheType);
        this.fisType = new ArrayList<>();
        boolean z2 = false;
        if (this.viewModel.getBaseErp().isVisit()) {
            ArrayList<String> arrayList = this.fisType;
            Intrinsics.checkNotNull(arrayList);
            arrayList.add(getString(R.string.str_visit));
            z = true;
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isOrder()) {
            ArrayList<String> arrayList2 = this.fisType;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.add(getString(R.string.str_order));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isInvoice()) {
            ArrayList<String> arrayList3 = this.fisType;
            Intrinsics.checkNotNull(arrayList3);
            arrayList3.add(getString(R.string.str_invoice));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isReturnInvoice()) {
            ArrayList<String> arrayList4 = this.fisType;
            Intrinsics.checkNotNull(arrayList4);
            arrayList4.add(getString(R.string.str_return_invoice));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isRetailInvoice()) {
            ArrayList<String> arrayList5 = this.fisType;
            Intrinsics.checkNotNull(arrayList5);
            arrayList5.add(getString(R.string.str_sales_retail_invoice));
            ArrayList<String> arrayList6 = this.fisType;
            Intrinsics.checkNotNull(arrayList6);
            arrayList6.add(getString(R.string.str_sales_retail_return_invoice));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isDispatch()) {
            ArrayList<String> arrayList7 = this.fisType;
            Intrinsics.checkNotNull(arrayList7);
            arrayList7.add(getString(R.string.str_dispatch));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isReturnDispatch()) {
            ArrayList<String> arrayList8 = this.fisType;
            Intrinsics.checkNotNull(arrayList8);
            arrayList8.add(getString(R.string.str_return_dispatch));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isCash()) {
            ArrayList<String> arrayList9 = this.fisType;
            Intrinsics.checkNotNull(arrayList9);
            arrayList9.add(getString(R.string.str_cash_collection));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isCase()) {
            ArrayList<String> arrayList10 = this.fisType;
            Intrinsics.checkNotNull(arrayList10);
            arrayList10.add(getString(R.string.str_safe_deposit));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isCreditCard()) {
            ArrayList<String> arrayList11 = this.fisType;
            Intrinsics.checkNotNull(arrayList11);
            arrayList11.add(getString(R.string.str_credit_card_slip));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isCheque()) {
            ArrayList<String> arrayList12 = this.fisType;
            Intrinsics.checkNotNull(arrayList12);
            arrayList12.add(getString(R.string.str_check_collection));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isDeed()) {
            ArrayList<String> arrayList13 = this.fisType;
            Intrinsics.checkNotNull(arrayList13);
            arrayList13.add(getString(R.string.str_payroll_note_collection));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isOneToOneChange()) {
            ArrayList<String> arrayList14 = this.fisType;
            Intrinsics.checkNotNull(arrayList14);
            arrayList14.add(getString(R.string.str_exchange));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isDemand()) {
            ArrayList<String> arrayList15 = this.fisType;
            Intrinsics.checkNotNull(arrayList15);
            arrayList15.add(getString(R.string.str_demand));
        } else {
            z = false;
        }
        if (this.viewModel.getBaseErp().isWhTransfer()) {
            ArrayList<String> arrayList16 = this.fisType;
            Intrinsics.checkNotNull(arrayList16);
            arrayList16.add(getString(R.string.str_sales_transfer));
            z2 = z;
        }
        if (z2) {
            ArrayList<String> arrayList17 = this.fisType;
            Intrinsics.checkNotNull(arrayList17);
            arrayList17.add(getString(R.string.str_select_all));
        }
        ArrayList<String> arrayList18 = this.fisType;
        Intrinsics.checkNotNull(arrayList18);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, arrayList18);
        AppCompatSpinner appCompatSpinner = this.spnFicheType;
        if (appCompatSpinner != null) {
            appCompatSpinner.setAdapter(arrayAdapter);
        }
        if (appCompatSpinner == null) {
            return;
        }
        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
            void DailyFicheListActivityloadFicheType1() {
            } 
            @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
            public void onItemSelected(AdapterView<?> adapterView, View arg1, int i2, long j2) {
                ArrayList arrayList19;
                ArrayList arrayList20;
                ArrayList arrayList21;
                ArrayList arrayList22;
                ArrayList arrayList23;
                ArrayList arrayList24;
                ArrayList arrayList25;
                ArrayList arrayList26;
                ArrayList arrayList27;
                ArrayList arrayList28;
                ArrayList arrayList29;
                ArrayList arrayList30;
                ArrayList arrayList31;
                ArrayList arrayList32;
                ArrayList arrayList33;
                ArrayList arrayList34;
                ArrayList arrayList35;
                Intrinsics.checkNotNullParameter(arg1, "arg1");
                FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Normal);
                arrayList19 = DailyFicheListActivity.this.fisType;
                Intrinsics.checkNotNull(arrayList19);
                if (!Intrinsics.areEqual(arrayList19.get(i2), DailyFicheListActivity.this.getString(R.string.str_order))) {
                    arrayList20 = DailyFicheListActivity.this.fisType;
                    Intrinsics.checkNotNull(arrayList20);
                    if (!Intrinsics.areEqual(arrayList20.get(i2), DailyFicheListActivity.this.getString(R.string.str_invoice))) {
                        arrayList21 = DailyFicheListActivity.this.fisType;
                        Intrinsics.checkNotNull(arrayList21);
                        if (!Intrinsics.areEqual(arrayList21.get(i2), DailyFicheListActivity.this.getString(R.string.str_return_invoice))) {
                            arrayList22 = DailyFicheListActivity.this.fisType;
                            Intrinsics.checkNotNull(arrayList22);
                            if (!Intrinsics.areEqual(arrayList22.get(i2), DailyFicheListActivity.this.getString(R.string.str_dispatch))) {
                                arrayList23 = DailyFicheListActivity.this.fisType;
                                Intrinsics.checkNotNull(arrayList23);
                                if (!Intrinsics.areEqual(arrayList23.get(i2), DailyFicheListActivity.this.getString(R.string.str_return_dispatch))) {
                                    arrayList24 = DailyFicheListActivity.this.fisType;
                                    Intrinsics.checkNotNull(arrayList24);
                                    if (!Intrinsics.areEqual(arrayList24.get(i2), DailyFicheListActivity.this.getString(R.string.str_cash_collection))) {
                                        arrayList25 = DailyFicheListActivity.this.fisType;
                                        Intrinsics.checkNotNull(arrayList25);
                                        if (!Intrinsics.areEqual(arrayList25.get(i2), DailyFicheListActivity.this.getString(R.string.str_safe_deposit))) {
                                            arrayList26 = DailyFicheListActivity.this.fisType;
                                            Intrinsics.checkNotNull(arrayList26);
                                            if (!Intrinsics.areEqual(arrayList26.get(i2), DailyFicheListActivity.this.getString(R.string.str_credit_card_slip))) {
                                                arrayList27 = DailyFicheListActivity.this.fisType;
                                                Intrinsics.checkNotNull(arrayList27);
                                                if (!Intrinsics.areEqual(arrayList27.get(i2), DailyFicheListActivity.this.getString(R.string.str_check_collection))) {
                                                    arrayList28 = DailyFicheListActivity.this.fisType;
                                                    Intrinsics.checkNotNull(arrayList28);
                                                    if (!Intrinsics.areEqual(arrayList28.get(i2), DailyFicheListActivity.this.getString(R.string.str_payroll_note_collection))) {
                                                        arrayList29 = DailyFicheListActivity.this.fisType;
                                                        Intrinsics.checkNotNull(arrayList29);
                                                        if (!Intrinsics.areEqual(arrayList29.get(i2), DailyFicheListActivity.this.getString(R.string.str_exchange))) {
                                                            arrayList30 = DailyFicheListActivity.this.fisType;
                                                            Intrinsics.checkNotNull(arrayList30);
                                                            if (!Intrinsics.areEqual(arrayList30.get(i2), DailyFicheListActivity.this.getString(R.string.str_demand))) {
                                                                arrayList31 = DailyFicheListActivity.this.fisType;
                                                                Intrinsics.checkNotNull(arrayList31);
                                                                if (!Intrinsics.areEqual(arrayList31.get(i2), DailyFicheListActivity.this.getString(R.string.str_visit))) {
                                                                    arrayList32 = DailyFicheListActivity.this.fisType;
                                                                    Intrinsics.checkNotNull(arrayList32);
                                                                    if (!Intrinsics.areEqual(arrayList32.get(i2), DailyFicheListActivity.this.getString(R.string.str_select_all))) {
                                                                        arrayList33 = DailyFicheListActivity.this.fisType;
                                                                        Intrinsics.checkNotNull(arrayList33);
                                                                        if (!Intrinsics.areEqual(arrayList33.get(i2), DailyFicheListActivity.this.getString(R.string.str_sales_retail_invoice))) {
                                                                            arrayList34 = DailyFicheListActivity.this.fisType;
                                                                            Intrinsics.checkNotNull(arrayList34);
                                                                            if (!Intrinsics.areEqual(arrayList34.get(i2), DailyFicheListActivity.this.getString(R.string.str_sales_retail_return_invoice))) {
                                                                                arrayList35 = DailyFicheListActivity.this.fisType;
                                                                                Intrinsics.checkNotNull(arrayList35);
                                                                                if (Intrinsics.areEqual(arrayList35.get(i2), DailyFicheListActivity.this.getString(R.string.str_sales_transfer))) {
                                                                                    FTypeControlUtils.setMainFType(FType.whtransfer);
                                                                                }
                                                                            } else {
                                                                                FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
                                                                                FTypeControlUtils.setMainFType(FType.perakendefatura);
                                                                            }
                                                                        } else {
                                                                            FTypeControlUtils.setMainFType(FType.perakendefatura);
                                                                        }
                                                                    } else {
                                                                        FTypeControlUtils.setMainFType(FType.all);
                                                                    }
                                                                } else {
                                                                    FTypeControlUtils.setMainFType(FType.ziyaret);
                                                                }
                                                            } else {
                                                                FTypeControlUtils.setMainFType(FType.talep);
                                                            }
                                                        } else {
                                                            FTypeControlUtils.setMainFType(FType.birebir);
                                                        }
                                                    } else {
                                                        FTypeControlUtils.setMainFType(FType.senet);
                                                    }
                                                } else {
                                                    FTypeControlUtils.setMainFType(FType.cek);
                                                }
                                            } else {
                                                FTypeControlUtils.setMainFType(FType.kredikarti);
                                            }
                                        } else {
                                            FTypeControlUtils.setMainFType(FType.nakitkasa);
                                        }
                                    } else {
                                        FTypeControlUtils.setMainFType(FType.nakit);
                                    }
                                } else {
                                    FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
                                    FTypeControlUtils.setMainFType(FType.irsaliye);
                                }
                            } else {
                                FTypeControlUtils.setMainFType(FType.irsaliye);
                            }
                        } else {
                            FTypeControlUtils.setMainFatirsTuru(FaturaIrsaliyeTuru.Iade);
                            FTypeControlUtils.setMainFType(FType.fatura);
                        }
                    } else {
                        FTypeControlUtils.setMainFType(FType.fatura);
                    }
                } else {
                    FTypeControlUtils.setMainFType(FType.siparis);
                }
                MainActivity.sFicheRef = -1;
                DailyFicheListActivity.this.setTitle();
                DailyFicheListActivity.this.loadFicheList();
            }
        });
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onDestroy() {
        super.onDestroy();
        MainActivity.sFicheRef = -1;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.sFicheRef = -1;
        finish();
    }

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public void loadFicheList() {
        DailyInformationViewModel dailyInformationViewModel = this.viewModel;
        String dailyInfoListSql = dailyInformationViewModel.getBaseErp().getLogoSqlHelper().getDailyInfoListSql(this, this.mGetPrintFiche);
        Intrinsics.checkNotNullExpressionValue(dailyInfoListSql, "getDailyInfoListSql(...)");
        dailyInformationViewModel.getDailyInfoList(dailyInfoListSql, new DailyInfoResponseListener(this));
    }

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (!FTypeControlUtils.isMainFTypeDemand() && this.viewModel.getBaseErp().getErpRights().isYaziciBaski()) {
            MenuItem add = menu.add(0, 4, 0, getString(R.string.str_printed));
            this.mnPrinted = add;
            if (add != null) {
                add.setIcon(R.drawable.ic_menu_report_image);
            }
        }
        MenuItem add2 = menu.add(0, 0, 0, getString(R.string.str_edit));
        this.mnEdit = add2;
        if (add2 != null) {
            add2.setIcon(R.drawable.ic_menu_edit);
        }
        MenuItem add3 = menu.add(0, 1, 0, getString(R.string.str_delete));
        this.mnDelete = add3;
        if (add3 != null) {
            add3.setIcon(R.drawable.ic_menu_delete);
        }
        MenuItem add4 = menu.add(0, 5, 0, getString(R.string.str_cancel));
        this.mnCancel = add4;
        if (add4 != null) {
            add4.setIcon(R.drawable.ic_menu_delete);
        }
        MenuItem add5 = menu.add(0, 6, 0, getString(R.string.menu_copy));
        this.mnCopy = add5;
        if (add5 != null) {
            add5.setIcon(R.drawable.ic_menu_delete);
        }
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return super.onPrepareOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId != 0) {
            if (itemId != 1) {
                if (itemId == 4) {
                    getPrintFiche();
                } else if (itemId != 5) {
                    if (itemId != 6) {
                        if (itemId == 16908332) {
                            finish();
                        }
                    } else {
                        if (!isFicheSelected()) {
                            return super.onOptionsItemSelected(item);
                        }
                        CopySelectedFiche();
                    }
                } else {
                    if (!isFicheSelected()) {
                        return super.onOptionsItemSelected(item);
                    }
                    if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice()) {
                        if (isCancelInvoiceFiche()) {
                            Toast.makeText(this, getString(R.string.str_invoice_already_cancelled), Toast.LENGTH_LONG).show();
                            return super.onOptionsItemSelected(item);
                        }
                        if (!isFicheTransferred()) {
                            Toast.makeText(this, R.string.str_fiche_not_transferred, Toast.LENGTH_LONG).show();
                            return super.onOptionsItemSelected(item);
                        }
                        ISqlHelper logoSqlHelper = this.viewModel.getBaseErp().getLogoSqlHelper();
                        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseErpActivity");
                        if (!logoSqlHelper.isEInvoiceOrEArchiveCustomer(this.customerRef)) {
                            Toast.makeText(this, R.string.str_invoice_fiche_not_cancel_einvoice, Toast.LENGTH_LONG).show();
                            return super.onOptionsItemSelected(item);
                        }
                        this.viewModel.getBaseErp().cancelInvoiceFiche(MainActivity.sFicheRef, new CancelInvoiceFicheListener(this));
                    }
                }
            } else {
                if (!isFicheSelected()) {
                    return super.onOptionsItemSelected(item);
                }
                new AlertDialog.Builder(this).setMessage(getString(R.string.str_question_want_delete)).setPositiveButton(getString(R.string.str_yes), this.dialogClickListener).setNegativeButton(getString(R.string.str_no), this.dialogClickListener).show();
            }
        } else {
            if (!isFicheSelected()) {
                return super.onOptionsItemSelected(item);
            }
            if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice() && isInvoiceCanceled()) {
                Toast.makeText(this, getString(R.string.str_invoice_already_cancelled), Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
            }
            boolean isFicheTransferred = isFicheTransferred();
            if (FTypeControlUtils.isMainFTypeOrder()) {
                if (this.viewModel.getOrderStatusChangeFiche()) {
                    if (isFicheTransferred) {
                        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
                        if (progressDialogBuilder == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
                            progressDialogBuilder = null;
                        }
                        progressDialogBuilder.setMessage(getString(R.string.str_fiche_status_control)).setCancelable(false).show();
                        this.viewModel.getBaseErp().getOrderStatus(this.logoFicheRef);
                        return true;
                    }
                } else if (isFicheTransferred) {
                    Toast.makeText(this, getString(R.string.str_fiche_transferred), Toast.LENGTH_LONG).show();
                    return false;
                }
            } else if (isFicheTransferred) {
                Toast.makeText(this, getString(R.string.str_fiche_transferred), Toast.LENGTH_LONG).show();
                return false;
            }
            yesOrNoResult(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    private Unit getPrintFiche() {
        int i2 = this.mGetPrintFiche;
        if (i2 == -1 || i2 == 0) {
            this.mGetPrintFiche = 1;
            MenuItem menuItem = this.mnPrinted;
            if (menuItem != null) {
                menuItem.setTitle(getString(R.string.str_not_printed));
            }
        } else if (i2 == 1) {
            this.mGetPrintFiche = 0;
            MenuItem menuItem2 = this.mnPrinted;
            if (menuItem2 != null) {
                menuItem2.setTitle(getString(R.string.str_printed));
            }
        }
        loadFicheList();
        return Unit.INSTANCE;
    }

    public DialogInterface.OnClickListener getDialogClickListener() {
        return this.dialogClickListener;
    }

    public void setDialogClickListener(DialogInterface.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
        this.dialogClickListener = onClickListener;
    }

    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    public static void dialogClickListenerlambda5(DailyFicheListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != -1) {
            return;
        }
        this0.fisSil();
        this0.loadFicheList();
        MainActivity.sFicheRef = -1;
    }

    public static void dialogClickListenerSaleConditionlambda6(DailyFicheListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != -1) {
            return;
        }
        this0.reviewFiche();
    }

    private boolean isFicheSelected() {
        if (MainActivity.sFicheRef > 0) {
            return true;
        }
        Toast.makeText(this, getString(R.string.str_question_select_fiche), Toast.LENGTH_LONG).show();
        return false;
    }

    private boolean isFicheTransferred() {
        Cursor cursor;
        int i2;
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM ORDERS WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeDispatchOrInvoiceOrRetailInvoiceOrOneToOne()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM INVOICE WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCashOrCreditReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM CASECASH WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCheckOrDeedReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM CHEQUEDEED WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeVisit()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM VISITINFO WHERE ID=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeWhTransfer()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM WHTRANSFER WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else {
            cursor = null;
        }
        if (cursor == null || !cursor.moveToFirst()) {
            i2 = 0;
        } else {
            do {
                i2 = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return i2 != 0;
    }

    private int getCustomerRef() {
        Cursor cursor;
        int i2;
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM ORDERS WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeDispatchOrInvoiceOrRetailInvoiceOrOneToOne()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM INVOICE WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCashOrCreditReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM CASECASH WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeCheckOrDeedReceipt()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM CHEQUEDEED WHERE LOGICALREF=" + MainActivity.sFicheRef);
        } else if (FTypeControlUtils.isMainFTypeVisit()) {
            cursor = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CLREF FROM VISITINFO WHERE ID=" + MainActivity.sFicheRef);
        } else {
            cursor = null;
        }
        if (cursor == null || !cursor.moveToFirst()) {
            return 0;
        }
        do {
            i2 = cursor.getInt(0);
        } while (cursor.moveToNext());
        return i2;
    }

    private void setCustomerRef(int i2) {
        this.customerRef = i2;
    }

    private boolean isCancelInvoiceFiche() {
        int i2;
        Cursor query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT CANCELLED FROM INVOICE WHERE LOGICALREF=" + MainActivity.sFicheRef);
        Intrinsics.checkNotNull(query);
        if (query.moveToFirst()) {
            do {
                i2 = query.getInt(0);
            } while (query.moveToNext());
        } else {
            i2 = 0;
        }
        return i2 != 0;
    }

    private boolean isInvoiceCanceled() {
        int i2;
        Cursor query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM INVOICE WHERE LOGICALREF=" + MainActivity.sFicheRef);
        Intrinsics.checkNotNull(query);
        if (query.moveToFirst()) {
            do {
                i2 = query.getInt(0);
            } while (query.moveToNext());
        } else {
            i2 = 0;
        }
        return i2 == 2;
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1) {
            loadFicheList();
        }
    }

    private void fisSil() {
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM ORDERDETAIL WHERE SALESFICHEID=" + MainActivity.sFicheRef);
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM ORDERS WHERE LOGICALREF=" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeDispatchOrInvoiceOrRetailInvoiceOrOneToOne()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM INVOICEDETAIL WHERE SALESFICHEID=" + MainActivity.sFicheRef);
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM INVOICE WHERE LOGICALREF=" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCashOrCreditReceipt()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CASHCREDITDETAIL WHERE CASHCREDITID=" + MainActivity.sFicheRef);
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CASHCREDIT WHERE LOGICALREF=" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CASECASH WHERE LOGICALREF=" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCheckOrDeedReceipt()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CHEQUEDEEDDETAIL WHERE CHEQUEDEEDID=" + MainActivity.sFicheRef);
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM CHEQUEDEED WHERE LOGICALREF=" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeVisit()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM VISITINFO WHERE ID =" + MainActivity.sFicheRef);
            return;
        }
        if (FTypeControlUtils.isMainFTypeWhTransfer()) {
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM WHTRANSFERDETAIL WHERE SALESFICHEID=" + MainActivity.sFicheRef);
            this.viewModel.getBaseErp().getLogoSqlBriteDatabase().execute("DELETE FROM WHTRANSFER WHERE LOGICALREF=" + MainActivity.sFicheRef);
        }
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        String[] stringArray;
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(menuInfo, "menuInfo");
        if (v.getId() == R.id.lvList_bycombo && FTypeControlUtils.isMainFTypeDailyFicheCreateContextMenu()) {
            List<DailyInfo> list = this.dailyInfoList;
            Intrinsics.checkNotNull(list);
            Object obj = list.get(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.database.Cursor");
            Cursor cursor = (Cursor) obj;
            menu.setHeaderTitle(this.viewModel.getBaseErp().getLogoSqlHelper().dbVal(cursor, "DEFINITION_", ColType.metin).toString());
            ISqlHelper logoSqlHelper = this.viewModel.getBaseErp().getLogoSqlHelper();
            ColType colType = ColType.sayi;
            MainActivity.sFicheRef = StringUtils.convertStringToInt(logoSqlHelper.dbVal(cursor, "LOGICALREF", colType).toString());
            this.logoFicheRef = StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().dbVal(cursor, "FICHEREF", colType).toString());
            if (FTypeControlUtils.isMainFTypeOrder()) {
                stringArray = getResources().getStringArray(R.array.menu_order_list);
            } else if (FTypeControlUtils.isMainFTypeDemand()) {
                stringArray = getResources().getStringArray(R.array.menu_demand_list);
            } else if (FTypeControlUtils.isMainFTypeDispatchOrOneToOne()) {
                stringArray = getResources().getStringArray(R.array.menu_dispatch_list);
            } else {
                stringArray = FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice() ? getResources().getStringArray(R.array.menu_invoice_list) : null;
            }
            Intrinsics.checkNotNull(stringArray);
            int length = stringArray.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (!this.viewModel.getBaseErp().getDoCampaignSalesCondition() ? (i2 != 2 || !FTypeControlUtils.isMainFTypeOrder() || StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("9")) == 2) && ((i2 != 2 || !FTypeControlUtils.isMainFTypeDispatch() || StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("34")) == 2) && (i2 != 2 || !FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoiceOrOneToOne() || StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("34")) == 2)) : i2 != 2 && i2 != 3) {
                    menu.add(0, i2, i2, stringArray[i2]);
                }
            }
        }
    }

    
    public boolean onContextItemSelected(MenuItem item) {
        boolean fisSatisKosuluUygulandiMi;
        Intrinsics.checkNotNullParameter(item, "item");
        if (FTypeControlUtils.isMainFTypeDemand()) {
            if (item.getItemId() == 0) {
                if (!isFicheTransferred()) {
                    Toast.makeText(this, getString(R.string.str_fiche_not_transferred), Toast.LENGTH_LONG).show();
                    return false;
                }
                reviewFiche();
            }
        } else if (item.getItemId() == 0) {
            try {
                onlineTotalShow(FicheType.Companion.fromFicheType(FTypeControlUtils.getMainFType().getValue()), MainActivity.sFicheRef);
            } catch (Exception e2) {
                Toast.makeText(this, e2.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (item.getItemId() == 1) {
            if (!isFicheTransferred()) {
                Toast.makeText(this, getString(R.string.str_fiche_not_transferred), Toast.LENGTH_LONG).show();
                return false;
            }
            reviewFiche();
        } else if (item.getItemId() == 2) {
            if (this.viewModel.getBaseErp().getDoCampaignSalesCondition()) {
                Toast.makeText(this, getString(R.string.str_no_campaign), Toast.LENGTH_LONG).show();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeOrder()) {
                if (StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("9")) != 2) {
                    Toast.makeText(this, getString(R.string.str_no_campaign), Toast.LENGTH_LONG).show();
                    return false;
                }
            } else if (FTypeControlUtils.isMainFTypeDispatch()) {
                if (StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("34")) != 2) {
                    Toast.makeText(this, getString(R.string.str_no_campaign), Toast.LENGTH_LONG).show();
                    return false;
                }
            } else if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoiceOrOneToOne() && StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("59")) != 2) {
                Toast.makeText(this, getString(R.string.str_no_campaign), Toast.LENGTH_LONG).show();
                return false;
            }
            if (!isFicheTransferred()) {
                Toast.makeText(this, getString(R.string.str_fiche_not_transferred), Toast.LENGTH_LONG).show();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice() && isInvoiceCanceled()) {
                Toast.makeText(this, getString(R.string.str_invoice_already_cancelled), Toast.LENGTH_LONG).show();
                return false;
            }
            if (!Intrinsics.areEqual(null, "")) {
                Toast.makeText(ContextUtils.getmContext(), null, Toast.LENGTH_SHORT).show();
                return false;
            }
            reviewFiche();
        } else if (item.getItemId() == 3) {
            if (StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("174")) == 1) {
                Toast.makeText(this, getString(R.string.str_no_condition), Toast.LENGTH_LONG).show();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeOrder()) {
                if (StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("64")) != 2) {
                    Toast.makeText(this, getString(R.string.str_no_condition), Toast.LENGTH_LONG).show();
                    return false;
                }
            } else if (StringUtils.convertStringToInt(this.viewModel.getBaseErp().getLogoSqlHelper().upVal("66")) != 2) {
                Toast.makeText(this, getString(R.string.str_no_condition), Toast.LENGTH_LONG).show();
                return false;
            }
            if (!isFicheTransferred()) {
                saleConditionApplyLocal();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice() && isInvoiceCanceled()) {
                Toast.makeText(this, getString(R.string.str_invoice_already_cancelled), Toast.LENGTH_LONG).show();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeOrder()) {
                fisSatisKosuluUygulandiMi = this.viewModel.getBaseErp().getLogoSqlHelper().fisSatisKosuluUygulandiMi(MainActivity.sFicheRef, 0);
            } else {
                fisSatisKosuluUygulandiMi = this.viewModel.getBaseErp().getLogoSqlHelper().fisSatisKosuluUygulandiMi(MainActivity.sFicheRef, 1);
            }
            if (fisSatisKosuluUygulandiMi) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.str_question_apply_sales_condition_again)).setPositiveButton(getString(R.string.str_yes), this.dialogClickListenerSaleCondition).setNegativeButton(getString(R.string.str_no), this.dialogClickListenerSaleCondition).show();
            } else {
                if (!Intrinsics.areEqual(null, "")) {
                    Toast.makeText(ContextUtils.getmContext(), null, Toast.LENGTH_SHORT).show();
                    return false;
                }
                reviewFiche();
                if (FTypeControlUtils.isMainFTypeOrder()) {
                    this.viewModel.getBaseErp().getLogoSqlHelper().addSalesCond(MainActivity.sFicheRef, 0);
                } else {
                    this.viewModel.getBaseErp().getLogoSqlHelper().addSalesCond(MainActivity.sFicheRef, 1);
                }
            }
        } else if (item.getItemId() == 4) {
            if (!isFicheTransferred()) {
                Toast.makeText(this, getString(R.string.str_fiche_not_transferred), Toast.LENGTH_LONG).show();
                return false;
            }
            if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice() && isInvoiceCanceled()) {
                Toast.makeText(this, getString(R.string.str_invoice_already_cancelled), Toast.LENGTH_LONG).show();
                return false;
            }
            new AlertDialog.Builder(this).setMessage(getString(R.string.str_question_want_cancel)).setPositiveButton(getString(R.string.str_yes), this.mDialogClickListenerCancel).setNegativeButton(getString(R.string.str_no), this.mDialogClickListenerCancel).show();
        }
        return true;
    }

    private void saleConditionApplyLocal() {
        boolean fisSatisKosuluUygulandiMi;
        if (FTypeControlUtils.isMainFTypeOrder()) {
            fisSatisKosuluUygulandiMi = this.viewModel.getBaseErp().getLogoSqlHelper().fisSatisKosuluUygulandiMi(MainActivity.sFicheRef, 0);
        } else {
            fisSatisKosuluUygulandiMi = this.viewModel.getBaseErp().getLogoSqlHelper().fisSatisKosuluUygulandiMi(MainActivity.sFicheRef, 1);
        }
        if (fisSatisKosuluUygulandiMi) {
            new AlertDialog.Builder(this).setMessage(getString(R.string.str_question_apply_sales_condition_again)).setPositiveButton(getString(R.string.str_yes), this.dialogClickListenerSaleConditionLocal).setNegativeButton(getString(R.string.str_no), this.dialogClickListenerSaleConditionLocal).show();
            return;
        }
        FTypeControlUtils.isMainFTypeOrder();
        reviewFicheLocal();
        if (FTypeControlUtils.isMainFTypeOrder()) {
            this.viewModel.getBaseErp().getLogoSqlHelper().addSalesCond(MainActivity.sFicheRef, 0);
        } else {
            this.viewModel.getBaseErp().getLogoSqlHelper().addSalesCond(MainActivity.sFicheRef, 1);
        }
    }

    public static void dialogClickListenerSaleConditionLocallambda7(DailyFicheListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != -1) {
            return;
        }
        FTypeControlUtils.isMainFTypeOrder();
        this0.reviewFicheLocal();
    }

    private void reviewFicheLocal() {
        this.mLastFicheMode = FicheMode.EDIT;
        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            this.viewModel.getSqlManager().getSalesOrderOne(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
        } else {
            this.viewModel.getSqlManager().getSalesInvoiceExam(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
        }
    }

    public static void reviewFicheLocallambda8(DailyFicheListActivity this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mLastFicheMode = null;
        dialogInterface.dismiss();
    }

    public static void mDialogClickListenerCancellambda9(DailyFicheListActivity this0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (i2 != -1) {
            return;
        }
        Toast.makeText(ContextUtils.getmContext(), this0.getString(R.string.str_invoice_cancelled), Toast.LENGTH_SHORT).show();
        this0.loadFicheList();
    }

    private void reviewFiche() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.fichelist, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (FTypeControlUtils.isMainFTypeOrder()) {
            builder.setTitle(getString(R.string.str_order_details));
        } else if (FTypeControlUtils.isMainFTypeDemand()) {
            builder.setTitle(getString(R.string.str_demand_detail));
        } else if (FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice()) {
            builder.setTitle(getString(R.string.str_invoice_details));
        } else if (FTypeControlUtils.isMainFTypeDispatchOrOneToOne()) {
            builder.setTitle(getString(R.string.str_dispatch_details));
        }
        builder.setView(inflate);
        Intrinsics.checkNotNull(inflate);
        if (listFicheDetail(inflate)) {
            builder.show();
        }
    }

    @SuppressLint({HttpHeaders.RANGE})
    private boolean listFicheDetail(View view) {
        String str;
        if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
            str = "SELECT ID AS _id , CODE , NAME , UNIT , AMOUNT , PRICE , VAT , NETTOTAL  FROM ORDERVIEW WHERE ID='" + this.logoFicheRef + '\'';
        } else if (FTypeControlUtils.isMainFTypeDispatchOrDeliveryNoteOrOneToOne()) {
            str = "SELECT ID AS _id , CODE , NAME , UNIT , AMOUNT , PRICE , VAT , NETTOTAL FROM DISPATCHVIEW WHERE ID=" + this.logoFicheRef;
        } else {
            if (!FTypeControlUtils.isMainFTypeInvoiceOrRetailInvoice()) {
                return false;
            }
            str = "SELECT ID AS _id , CODE , NAME , UNIT , AMOUNT , PRICE , VAT , NETTOTAL FROM INVOICEVIEW WHERE ID=" + this.logoFicheRef;
        }
        try {
            Cursor query = this.viewModel.getBaseErp().getLogoSqlBriteDatabase().query(str);
            if (query != null) {
                int count = query.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    if (query.moveToPosition(i2)) {
                        Log.d("CASS", "fisDetayListele: " + query.getString(query.getColumnIndex("CODE")));
                    }
                }
            }
            ListView listView = view.findViewById(R.id.list_fiche_list);
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.rowfichedetailview, query, new String[]{"CODE", "NAME", "UNIT", "AMOUNT", "PRICE", "VAT", "NETTOTAL"}, new int[]{R.id.FVCODE, R.id.FVNAME, R.id.FVUNIT, R.id.FVAMOUNT, R.id.FVPRICE, R.id.FVVAT, R.id.FVNETTOTAL});
            simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() { // from class: com.proje.mobilesales.features.dailyinformation.view.activity.DailyFicheListActivityExternalSyntheticLambda7
                @Override // android.widget.SimpleCursorAdapter.ViewBinder
                public boolean setViewValue(View view2, Cursor cursor, int i3) {
                    boolean listFicheDetaillambda10;
                    listFicheDetaillambda10 = DailyFicheListActivity.listFicheDetaillambda10(view2, cursor, i3);
                    return listFicheDetaillambda10;
                }
            });
            listView.setAdapter(simpleCursorAdapter);
        } catch (Exception e2) {
            Log.e("AA", "fisDetayListele: ", e2);
        }
        return true;
    }

    public static boolean listFicheDetaillambda10(View view, Cursor cursor, int i2) {
        try {
            if (i2 == 4) {
                AppCompatTextView appCompatTextView = view.findViewById(R.id.FVAMOUNT);
                String string = cursor.getString(i2);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                appCompatTextView.setText(StringUtils.dFormat(string));
                return true;
            }
            if (i2 == 5) {
                AppCompatTextView appCompatTextView2 = view.findViewById(R.id.FVPRICE);
                String string2 = cursor.getString(i2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                appCompatTextView2.setText(StringUtils.dFormat(string2));
                return true;
            }
            if (i2 == 6) {
                AppCompatTextView appCompatTextView3 = view.findViewById(R.id.FVVAT);
                String string3 = cursor.getString(i2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                appCompatTextView3.setText(StringUtils.dFormat(string3));
                return true;
            }
            if (i2 != 7) {
                return false;
            }
            AppCompatTextView appCompatTextView4 = view.findViewById(R.id.FVNETTOTAL);
            String string4 = cursor.getString(i2);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            appCompatTextView4.setText(StringUtils.dFormat(string4));
            return true;
        } catch (Exception e2) {
            Log.e("AA", "setViewValue: ", e2);
            return false;
        }
    }

    private void yesOrNoResult(boolean z) {
        if (z) {
            CustomerOperation customerOperation = new CustomerOperation(null, 0, null, false, null, null, null, null, null, null, 1023, null);
            FicheMode ficheMode = FicheMode.EDIT;
            customerOperation.setFicheMode(ficheMode);
            if (FTypeControlUtils.isMainFTypeSalesFiche()) {
                this.mLastFicheMode = ficheMode;
                ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
                if (progressDialogBuilder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
                    progressDialogBuilder = null;
                }
                progressDialogBuilder.setMessage(getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
                if (FTypeControlUtils.isMainFTypeOrderOrDemand()) {
                    this.viewModel.getSqlManager().getSalesOrderOne(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                    return;
                } else if (FTypeControlUtils.isMainFTypeWhTransfer()) {
                    this.viewModel.getSqlManager().getSalesWhTransfer(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                    return;
                } else {
                    this.viewModel.getSqlManager().getSalesInvoiceExam(MainActivity.sFicheRef, new SalesRecyclerSalesFicheGet(this));
                    return;
                }
            }
            if (FTypeControlUtils.isMainFTypeCashReceipt()) {
                getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CASHFTYPE, ficheMode);
                return;
            }
            if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
                getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CREDITFTYPE, ficheMode);
                return;
            }
            if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
                getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CASEFTYPE, ficheMode);
                return;
            }
            if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
                getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CHEQUEFTYPE, ficheMode);
            } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
                getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.DEEDFTYPE, ficheMode);
            } else if (FTypeControlUtils.isMainFTypeVisit()) {
                getVisitFiche(MainActivity.sFicheRef, ficheMode);
            }
        }
    }

    public static void yesOrNoResultlambda11(DailyFicheListActivity this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mLastFicheMode = null;
        dialogInterface.dismiss();
    }

    private void CopySelectedFiche() {
        if (FTypeControlUtils.isMainFTypeCashReceipt()) {
            getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CASHFTYPE, FicheMode.COPY);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCreditReceipt()) {
            getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CREDITFTYPE, FicheMode.COPY);
            return;
        }
        if (FTypeControlUtils.isMainFTypeCaseReceipt()) {
            getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CASEFTYPE, FicheMode.COPY);
        } else if (FTypeControlUtils.isMainFTypeCheckReceipt()) {
            getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.CHEQUEFTYPE, FicheMode.COPY);
        } else if (FTypeControlUtils.isMainFTypeDeedReceipt()) {
            getReceiptFiche(MainActivity.sFicheRef, ReceiptFicheShortType.DEEDFTYPE, FicheMode.COPY);
        }
    }

    private void getReceiptFiche(int i2, ReceiptFicheShortType receiptFicheShortType, FicheMode ficheMode) {
        ReceiptType receiptType;
        this.mLastFicheMode = ficheMode;
        int i3 = WhenMappings.EnumSwitchMapping0[receiptFicheShortType.ordinal()];
        if (i3 == 1 || i3 == 2) {
            receiptType = receiptFicheShortType == ReceiptFicheShortType.CASHFTYPE ? ReceiptType.CASH : ReceiptType.CREDIT;
        } else if (i3 == 3) {
            receiptType = ReceiptType.CASE;
        } else {
            if (i3 != 4 && i3 != 5) {
                throw new NoWhenBranchMatchedException();
            }
            receiptType = receiptFicheShortType == ReceiptFicheShortType.CHEQUEFTYPE ? ReceiptType.CHEQUE : ReceiptType.DEED;
        }
        this.mLastReceiptType = receiptType;
        openReceiptFiche(i2, this.mLastFicheMode, receiptType);
    }

    private void getVisitFiche(int i2, FicheMode ficheMode) {
        this.mLastFicheMode = ficheMode;
        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.setMessage(getString(R.string.str_fiche_loading)).setOnCancelClickListener().show();
        this.viewModel.getSqlManager().getVisitExam(i2, new VisitRecyclerSalesFicheGet(this));
    }

    public static void getVisitFichelambda12(DailyFicheListActivity this0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        this0.mLastFicheMode = null;
        dialogInterface.dismiss();
    }
     public void onStart() {
        EventBus.getDefault().register(this);
    }
     public void onStop() {
         super.onStop(null);
         EventBus.getDefault().unregister(this);
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putInt(STATE_GET_PRINT_FICHE, this.mGetPrintFiche);
        outState.putInt(STATE_LOGO_TRANSFER_FICHE_REF, this.logoFicheRef);
        super.onSaveInstanceState(outState);
    }

    public void orderStatusMessage(BaseSelectResult<?> baseResult) {
        Intrinsics.checkNotNullParameter(baseResult, "baseResult");
        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (baseResult.isSuccess()) {
            if (baseResult.getProcessType() == ProcessType.GETORDERSTATUS) {
                List<?> dataList = baseResult.getDataList();
                Intrinsics.checkNotNullExpressionValue(dataList, "getDataList(...)");
                if (!dataList.isEmpty()) {
                    Object obj = baseResult.getDataList().get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.proje.mobilesales.features.model.TigerSqlModel");
                    if (Intrinsics.areEqual(((TigerSqlModel) obj).getA(), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                        yesOrNoResult(true);
                        return;
                    } else {
                        Toast.makeText(this, getString(R.string.str_fiche_status_not_offer), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(this, getString(R.string.str_fiche_delete_erp), Toast.LENGTH_LONG).show();
                return;
            }
            return;
        }
        Toast.makeText(this, baseResult.getErrorString(), Toast.LENGTH_LONG).show();
    }
    public void onError(String str) {
        RecyclerView recyclerView = this.rvDailyInfo;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setVisibility(View.GONE);
        LinearLayout linearLayout = this.emptyView;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    public  void onDailyInfoLoads(PrintSlipModel arrayList) {
        RecyclerView recyclerView = this.rvDailyInfo;
        Intrinsics.checkNotNull(recyclerView);
        if (recyclerView.getVisibility() == View.GONE) {
            RecyclerView recyclerView2 = this.rvDailyInfo;
            Intrinsics.checkNotNull(recyclerView2);
            recyclerView2.setVisibility(View.VISIBLE);
            LinearLayout linearLayout = this.emptyView;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
        }
        List<DailyInfo> list = this.dailyInfoList;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            list.clear();
        }
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.isEmpty()) {
            this.dailyInfoList = new ArrayList();
            RecyclerView recyclerView3 = this.rvDailyInfo;
            Intrinsics.checkNotNull(recyclerView3);
            recyclerView3.setVisibility(View.GONE);
            LinearLayout linearLayout2 = this.emptyView;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.VISIBLE);
        }
        this.dailyInfoList = (List<DailyInfo>) arrayList;
        DailyInfoRecyclerViewAdapter dailyInfoRecyclerViewAdapter = this.mDailyInfoRecyclerViewAdapter;
        Intrinsics.checkNotNull(dailyInfoRecyclerViewAdapter);
        List<DailyInfo> list2 = this.dailyInfoList;
        Intrinsics.checkNotNull(list2);
        dailyInfoRecyclerViewAdapter.setDailyInfo(list2);
        RecyclerView recyclerView4 = this.rvDailyInfo;
        Intrinsics.checkNotNull(recyclerView4);
        recyclerView4.setLayoutManager(new StickyHeaderLayoutManager());
        RecyclerView recyclerView5 = this.rvDailyInfo;
        Intrinsics.checkNotNull(recyclerView5);
        recyclerView5.setAdapter(this.mDailyInfoRecyclerViewAdapter);
    }

    private record DailyInfoResponseListener(
            WeakReference<DailyFicheListActivity> mMainActivityWeakReference) implements ResponseListener<ArrayList<DailyInfo>> {
            private DailyInfoResponseListener(DailyFicheListActivity mMainActivityWeakReference) {
                this.mMainActivityWeakReference = new WeakReference<>(mMainActivityWeakReference);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mMainActivityWeakReference.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mMainActivityWeakReference.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mMainActivityWeakReference.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onDailyInfoLoads(arrayList);
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("AA", "onError: " + errorMessage);
                if (this.mMainActivityWeakReference.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mMainActivityWeakReference.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mMainActivityWeakReference.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onError(errorMessage);
                }
            }

        public void onFailure(Throwable throwable) {
        }

        public void onResponse(Boolean aBoolean) {
        }

        public void onResponse(Sales sales) {
        }

        public void onResponse(TigerServiceResult tigerServiceResult) {
        }

        public void onResponse(ArrayList<ArrayList<DailyInfo>> obj) {
        }

        public void onResponse() {
        }

        }
    private void onlineTotalShow(FicheType ficheType, int i2) throws CloneNotSupportedException {
        Sales sales;
        ProgressDialogBuilder<?> progressDialogBuilder = null;
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            Sales sales2 = this.viewModel.getSqlManager().getSalesList(i2).get(0);
            Intrinsics.checkNotNull(sales2, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales2;
        } else if (FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType) && FTypeControlUtils.isMainFatIrsTuruNormal()) {
            Sales sales3 = this.viewModel.getSqlManager().getInvoice(i2).get(0);
            Intrinsics.checkNotNull(sales3, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales3;
        } else if (FicheTypeControlUtils.isFicheTypeOnlyInvoice(ficheType) && FTypeControlUtils.isMainFatIrsTuruIade()) {
            Sales sales4 = this.viewModel.getSqlManager().getReturnInvoice(i2).get(0);
            Intrinsics.checkNotNull(sales4, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales4;
        } else if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType) && FTypeControlUtils.isMainFatIrsTuruNormal()) {
            Sales sales5 = this.viewModel.getSqlManager().getDispatch(i2).get(0);
            Intrinsics.checkNotNull(sales5, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales5;
        } else if (FicheTypeControlUtils.isFicheTypeDispatch(ficheType) && FTypeControlUtils.isMainFatIrsTuruIade()) {
            Sales sales6 = this.viewModel.getSqlManager().getReturnDispatch(i2).get(0);
            Intrinsics.checkNotNull(sales6, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales6;
        } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType) && FTypeControlUtils.isMainFatIrsTuruNormal()) {
            Sales sales7 = this.viewModel.getSqlManager().getRetailInvoice(i2).get(0);
            Intrinsics.checkNotNull(sales7, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales7;
        } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice(ficheType) && FTypeControlUtils.isMainFatIrsTuruIade()) {
            Sales sales8 = this.viewModel.getSqlManager().getRetailReturnInvoice(i2).get(0);
            Intrinsics.checkNotNull(sales8, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.Sales");
            sales = sales8;
        } else {
            sales = null;
        }
        ProgressDialogBuilder<?> progressDialogBuilder2 = this.mTotalProgressDialogBuilder;
        if (progressDialogBuilder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTotalProgressDialogBuilder");
        } else {
            progressDialogBuilder = progressDialogBuilder2;
        }
        progressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        this.viewModel.getBaseErp().showOnlineTotal(sales, new SalesActivityCalculateTotalListener(this));
    }

    private record SalesActivityCalculateTotalListener(
            WeakReference<DailyFicheListActivity> mDailyFicheListActivity) implements ResponseListener<Sales> {
            private SalesActivityCalculateTotalListener(DailyFicheListActivity mDailyFicheListActivity) {
                this.mDailyFicheListActivity = new WeakReference<>(mDailyFicheListActivity);
            }

        public void onResponse(PrintSlipModel sales) {
                if (this.mDailyFicheListActivity.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mDailyFicheListActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mDailyFicheListActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onCalculateSales(sales, "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mDailyFicheListActivity.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mDailyFicheListActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(DailyFicheListActivity.TAG, "onError: " + errorMessage);
                    DailyFicheListActivity dailyFicheListActivity2 = this.mDailyFicheListActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onCalculateSales(null, errorMessage);
                }
            }

        public void onFailure(Throwable throwable) {
        }

        public void onResponse(Boolean aBoolean) {
        }

        public void onResponse(Sales sales) {
        }

        public void onResponse(ArrayList<Sales> obj) {
        }

        public void onResponse() {
        }

        public void onResponse(TigerServiceResult tigerServiceResult) {
        }

        }
    public void onCalculateSales(PrintSlipModel sales, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mTotalProgressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTotalProgressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (sales != null) {
            calculateTotals(String.valueOf(sales.getTotal()), String.valueOf(sales.getTotalVat()), String.valueOf(sales.getDiscTotal()), String.valueOf(sales.getTotalNet()));
        } else {
            Toast.makeText(this, R.string.exp_45_undefined, Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private  void openSalesFiche( PrintSlipModel sales, FicheMode ficheMode) {
        CustomerOperation customerOperation = new CustomerOperation(sales.getmSalesType(), sales.getFicheType(), ficheMode);
        Intent intent = new Intent(this, SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, sales.getClRef());
        intent.putExtra("bigdata:synccode", this.viewModel.getBaseErp().saveObject(sales));
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
        startActivity(intent);
    }
    @SuppressLint({"IntentWithNullActionLaunch"})
    private void openReceiptFiche(int i2, FicheMode ficheMode, ReceiptType receiptType) {
        String simpleName;
        CustomerOperation customerOperation = new CustomerOperation(null, 0, null, false, null, null, null, null, null, null, 1023, null);
        customerOperation.setFicheMode(ficheMode);
        customerOperation.setReceiptType(receiptType);
        Intent intent = new Intent();
        int i3 = receiptType == null ? -1 : WhenMappings.EnumSwitchMapping1[receiptType.ordinal()];
        if (i3 == 1 || i3 == 2) {
            intent = new Intent(this, CashAndCreditCardFicheActivity.class);
            simpleName = CashCredit.class.getSimpleName();
        } else if (i3 == 3) {
            intent = new Intent(this, CaseFicheActivity.class);
            simpleName = CaseCash.class.getSimpleName();
        } else if (i3 == 4 || i3 == 5) {
            intent = new Intent(this, ChequeAndDeedFicheActivity.class);
            simpleName = Chequedeed.class.getSimpleName();
        } else {
            simpleName = "";
        }
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, this.viewModel.getBaseErp().getFicheCustomerRef(simpleName, i2));
        intent.putExtra(IntentExtraName.EXTRA_RECEIPT_FICHE_REF, i2);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        startActivity(intent);
    }
    private void openVisitFiche(PrintSlipModel visit, FicheMode ficheMode) {
        CustomerOperation customerOperation = new CustomerOperation(null, 0, null, false, null, null, null, null, null, null, 1023, null);
        customerOperation.setFicheMode(FicheMode.EDIT);
        Intent intent = new Intent(this, VisitEnterActivityNew.class);
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_REF, visit.getClRef());
        intent.putExtra(VisitEnterActivityNew.EXTRA_VISIT, visit.size());
        intent.putExtra(BaseFicheActivity.EXTRA_CUSTOMER_OPERATION, customerOperation);
        startActivity(intent);
    }

    private record SalesRecyclerSalesFicheGet(
            WeakReference<DailyFicheListActivity> mAdapter) implements ResponseListener<Sales> {
            private SalesRecyclerSalesFicheGet(DailyFicheListActivity mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel sales) {
                if (this.mAdapter.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onSalesGet(sales, "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onSalesGet(null, errorMessage);
                }
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
            public void onResponse(ArrayList<Sales> obj) {

            }

            @Override
            public void onResponse() {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }
        }
    public void onSalesGet(PrintSlipModel sales, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (sales != null) {
            FicheMode ficheMode = this.mLastFicheMode;
            if (ficheMode != null) {
                Intrinsics.checkNotNull(ficheMode);
                openSalesFiche(sales, ficheMode);
                return;
            } else {
                Toast.makeText(this, getString(R.string.str_cancel_process), Toast.LENGTH_LONG).show();
                return;
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private record VisitRecyclerSalesFicheGet(
            WeakReference<DailyFicheListActivity> mAdapter) implements ResponseListener<Visit> {
            private VisitRecyclerSalesFicheGet(DailyFicheListActivity mAdapter) {
                this.mAdapter = new WeakReference<>(mAdapter);
            }

        public void onResponse(PrintSlipModel visit) {
                if (this.mAdapter.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onVisitGet(visit, "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mAdapter.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mAdapter.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.onVisitGet(null, errorMessage);
                }
            }

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

            @Override
            public void onResponse(Visit obj) {

            }

            @Override
            public void onResponse(ArrayList<Visit> obj) {

            }

            @Override
            public void onResponse() {

            }
        }
    public void onVisitGet(PrintSlipModel visit, String str) {
        ProgressDialogBuilder<?> progressDialogBuilder = this.progressDialogBuilder;
        if (progressDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialogBuilder");
            progressDialogBuilder = null;
        }
        progressDialogBuilder.dismiss();
        if (visit != null) {
            FicheMode ficheMode = this.mLastFicheMode;
            if (ficheMode != null) {
                openVisitFiche(visit, ficheMode);
                return;
            } else {
                Toast.makeText(this, getString(R.string.str_cancel_process), Toast.LENGTH_LONG).show();
                return;
            }
        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private record CancelInvoiceFicheListener(
            WeakReference<DailyFicheListActivity> mActivity) implements ResponseListener<Boolean> {
            private CancelInvoiceFicheListener(DailyFicheListActivity mActivity) {
                this.mActivity = new WeakReference<>(mActivity);
            }

        public void onResponse(PrintSlipModel bool) {
                if (this.mActivity.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed() || bool == null) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.cancelFicheResult(bool.booleanValue(), "");
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mActivity.get() != null) {
                    DailyFicheListActivity dailyFicheListActivity = this.mActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity);
                    if (dailyFicheListActivity.isActivityDestroyed()) {
                        return;
                    }
                    DailyFicheListActivity dailyFicheListActivity2 = this.mActivity.get();
                    Intrinsics.checkNotNull(dailyFicheListActivity2);
                    dailyFicheListActivity2.cancelFicheResult(false, errorMessage);
                }
            }

        public void onFailure(Throwable throwable) {
        }

        public void onResponse(Boolean aBoolean) {
        }

        public void onResponse(ArrayList<Boolean> obj) {
        }

        public void onResponse() {
        }

        public void onResponse(Sales sales) {
        }

        public void onResponse(TigerServiceResult tigerServiceResult) {
        }
        }
    public void cancelFicheResult(boolean z, String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
        if (z) {
            Toast.makeText(this, R.string.str_fiche_cancel_success, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.str_fiche_cancel_failed, Toast.LENGTH_LONG).show();
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
