package com.proje.mobilesales.features.sales.view.list;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.*;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.base.ActionViewResolver;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseFicheListFragment;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.*;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.CustomerRiskLimit;
import com.proje.mobilesales.features.customer.utils.CustomerRestrictionUtil;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Firm;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.model.EDocumentPdfContent;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import com.proje.mobilesales.features.reports.model.ReportItem;
import com.proje.mobilesales.features.reports.model.ResponseError;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import com.proje.mobilesales.features.sales.model.SalesShort;
import com.proje.mobilesales.features.sales.repository.SalesListRepository;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.UnknownNullability;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static androidx.appcompat.widget.SearchView.*;
import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public final class SalesListFragment extends BaseFicheListFragment implements LoaderManager.LoaderCallbacks<Cursor>, OnQueryTextListener, TransferAfterRefreshList, ActionModeDelegate {
    private static final int CREATE_REQUEST_CODE = 40;
    public static final Companion Companion = new Companion(null);
    private static final int OPEN_REQUEST_CODE = 41;
    private static final String STATE_SALES_FICHE_MENU_RIGHTS = "state:salesFicheMenuRights";
    private static final String STATE_SHOW_DETAIL = "state:showDetail";
    private final String PdfEmailToAddress;
    private BottomSheetDialog bottomSheetDialog;
    private String cacheReportPdfFilePath;
    private String cacheReportPdfFolderPath;
    private String cacheSalesPdfFilePath;
    private String cacheSalesPdfFolderPath;
    private double debtAmount;
    private final List<Integer> inTransferFicheList;
    private boolean isEArchiveCustomer;
    private View lnClearSavePathPdf;
    private View lnDownloadPdf;
    private View lnSharePdf;
    private View lnShowPdf;
    private ActionMode mActionMode;
    private SalesRecyclerViewAdapter mAdapter;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mAutoVisitId;
    public AlertDialogBuilder<?> mClearPathDialogBuilder;
    private int mCustomerShipRef;
    private boolean mDisplayOptions;
    private PdfExportOption mPdfExportOption;
    private PrinterServiceApi mPrintersApi;
    private BroadcastReceiver mReceiver;
    private SalesFicheMenuRights mSalesFicheMenuRights;
    private String mSalesPdfName;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private ReportItem reportItem;
    private final SalesListRepository repository;
    private SalesShort selectedSalesItem;
    private PdfOperation selectedSalesItemPdfOperation;
    private final SalesListViewModel viewModel;

    public static void onCreatelambda4lambda3(DialogInterface dialogInterface, int i2) {
    }

    public boolean onQueryTextSubmit(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    public SalesListFragment() {
        SalesListRepository salesListRepository = new SalesListRepository();
        this.repository = salesListRepository;
        this.viewModel = new SalesListViewModel(salesListRepository);
        this.inTransferFicheList = new ArrayList();
        this.selectedSalesItemPdfOperation = PdfOperation.EDocumentPdf;
        this.mPdfExportOption = PdfExportOption.Preview;
        this.PdfEmailToAddress = "pdf@pdf.com";
        this.mSalesPdfName = "";
        this.mAdapter = new SalesRecyclerViewAdapter(this);
        this.mReceiver = new BroadcastReceiver() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentmReceiver1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_STATUS, false);
                boolean booleanExtra = intent.getBooleanExtra(IntentExtraName.EXTRA_TRANSFER_CLREF, false);
                SalesListFragment.this.refreshTransferAfterList(true);
                if (booleanExtra) {
                    FragmentActivity activity = SalesListFragment.this.getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.list.SalesListActivity");
                    ((SalesListActivity) activity).customerRefChanged();
                }
            }
        };
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

    public AlertDialogBuilder<?> getMClearPathDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mClearPathDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mClearPathDialogBuilder");
        return null;
    }

    public   void setMClearPathDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mClearPathDialogBuilder = alertDialogBuilder;
    }

    public   BottomSheetDialog getBottomSheetDialog() {
        return this.bottomSheetDialog;
    }

    public   void setBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
        this.bottomSheetDialog = bottomSheetDialog;
    }

    public   SalesShort getSelectedSalesItem() {
        return this.selectedSalesItem;
    }

    public   void setSelectedSalesItem(SalesShort salesShort) {
        this.selectedSalesItem = salesShort;
    }

    public   PdfOperation getSelectedSalesItemPdfOperation() {
        return this.selectedSalesItemPdfOperation;
    }

    public    void setSelectedSalesItemPdfOperation(PdfOperation pdfOperation) {
        Intrinsics.checkNotNullParameter(pdfOperation, "<set-?>");
        this.selectedSalesItemPdfOperation = pdfOperation;
    }

    public  SalesRecyclerViewAdapter getMAdapter() {
        return this.mAdapter;
    }

    public   void setMAdapter(SalesRecyclerViewAdapter salesRecyclerViewAdapter) {
        Intrinsics.checkNotNullParameter(salesRecyclerViewAdapter, "<set-?>");
        this.mAdapter = salesRecyclerViewAdapter;
    }

    public   int getMAutoVisitId() {
        return this.mAutoVisitId;
    }

    public   void setMAutoVisitId(int i2) {
        this.mAutoVisitId = i2;
    }

    public   BroadcastReceiver getMReceiver() {
        return this.mReceiver;
    }

    public   void setMReceiver(BroadcastReceiver broadcastReceiver) {
        Intrinsics.checkNotNullParameter(broadcastReceiver, "<set-?>");
        this.mReceiver = broadcastReceiver;
    }

    public boolean isFicheInTransfer(int i2) {
        if (this.inTransferFicheList.contains(Integer.valueOf(i2))) {
            return true;
        }
        this.inTransferFicheList.add(Integer.valueOf(i2));
        return false;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context requireContext = requireContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(requireContext, (BaseInjectableActivity) activity));
        Context requireContext2 = requireContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMClearPathDialogBuilder(new AlertDialogBuilder.Impl(requireContext2, (BaseInjectableActivity) activity2));
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(this.mReceiver, new IntentFilter(IntentExtraName.ACTION_TRANSFER_FICHE));
        this.mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        this.cacheReportPdfFolderPath = requireActivity().getCacheDir().getAbsolutePath() + "/eDespatchPdf";
        this.cacheSalesPdfFolderPath = requireActivity().getCacheDir().getAbsolutePath() + "/salesPdf";
        String str = this.cacheReportPdfFolderPath;
        Intrinsics.checkNotNull(str);
        clearExistingReports(str);
        String str2 = this.cacheSalesPdfFolderPath;
        Intrinsics.checkNotNull(str2);
        clearExistingReports(str2);
        View inflate = getLayoutInflater().inflate(R.layout.fragment_pdf_bottom_sheet_dialog, null);
        this.lnSharePdf = inflate.findViewById(R.id.ln_share_pdf);
        this.lnDownloadPdf = inflate.findViewById(R.id.ln_download_pdf);
        this.lnShowPdf = inflate.findViewById(R.id.ln_show_pdf);
        this.lnClearSavePathPdf = inflate.findViewById(R.id.ln_clear_pdf_path);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity());
        this.bottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.setContentView(inflate);
        View view = this.lnShowPdf;
        if (view != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view2) {
                    SalesListFragment.onCreatelambda0(SalesListFragment.this, view2);
                }
            });
        }
        View view2 = this.lnSharePdf;
        if (view2 != null) {
            view2.setOnClickListener(new OnClickListener() {
                public void onClick(View view3) {
                    SalesListFragment.onCreatelambda1(SalesListFragment.this, view3);
                }
            });
        }
        View view3 = this.lnDownloadPdf;
        if (view3 != null) {
            view3.setOnClickListener(new OnClickListener() {
                public void onClick(View view4) {
                    SalesListFragment.onCreatelambda2(SalesListFragment.this, view4);
                }
            });
        }
        View view4 = this.lnClearSavePathPdf;
        if (view4 != null) {
            view4.setOnClickListener(new OnClickListener() {
                public void onClick(View view5) {
                    SalesListFragment.onCreatelambda4(SalesListFragment.this, view5);
                }
            });
        }
    } 
    public static   void onCreatelambda0(SalesListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Preview;
        PdfOperation pdfOperation = this0.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf || pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            this0.getEDocumentSalesForPdf();
        } else {
            this0.getSalesFicheForPdf();
        }
        BottomSheetDialog bottomSheetDialog = this0.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }
    public static   void onCreatelambda1(SalesListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Share;
        PdfOperation pdfOperation = this0.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf || pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            this0.getEDocumentSalesForPdf();
        } else {
            this0.getSalesFicheForPdf();
        }
        BottomSheetDialog bottomSheetDialog = this0.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }

    public static   void onCreatelambda2(SalesListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mPdfExportOption = PdfExportOption.Download;
        PdfOperation pdfOperation = this0.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf || pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            this0.getEDocumentSalesForPdf();
        } else {
            this0.getSalesFicheForPdf();
        }
        BottomSheetDialog bottomSheetDialog = this0.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }

    public static void onCreatelambda4(SalesListFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        PdfOperation pdfOperation = this0.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
            SharedPreferencesHelper sharedPreferencesHelper = this0.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            sharedPreferencesHelper.removeEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this0.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            sharedPreferencesHelper2.removeSalesPdfPath();
        }
        BottomSheetDialog bottomSheetDialog = this0.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
        this0.getMClearPathDialogBuilder().setMessage(this0.getString(R.string.str_pdf_save_path_cleared)).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentExternalSyntheticLambda4

            public void onClick(DialogInterface dialogInterface, int i2) {
                SalesListFragment.onCreatelambda4lambda3(dialogInterface, i2);
            }
        }).create().show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View onCreateView = super.onCreateView(inflater, viewGroup, bundle);
        getMSwipeRefreshLayout().setEnabled(true);
        getMSwipeRefreshLayout().setRefreshing(false);
        getMSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                SalesListFragment.onCreateViewlambda5(SalesListFragment.this);
            }
        });
        return onCreateView;
    }

    public static void onCreateViewlambda5(SalesListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.restartLoader();
    }
     public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mSalesFicheMenuRights = bundle.getParcelable(STATE_SALES_FICHE_MENU_RIGHTS);
            this.mDisplayOptions = bundle.getBoolean(STATE_SHOW_DETAIL);
            return;
        }
        this.mSalesFicheMenuRights = this.viewModel.getSalesFicheMenuRights();
        SalesListViewModel salesListViewModel = this.viewModel;
        CustomerOperation customerOperation = this.mCustomerOperation;
        this.mDisplayOptions = salesListViewModel.getSalesShowDetail(customerOperation != null ? customerOperation.getFicheType() : null);
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        this.mCustomerShipRef = arguments.getInt(IntentExtraName.EXTRA_CUSTOMER_SHIPREF);
    }

    public boolean startActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.mActionMode != null) {
            return true;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        this.mActionMode = appCompatActivity.startSupportActionMode(callback);
        return true;
    }

    public boolean isInActionMode() {
        return this.mActionMode != null;
    }

    public void stopActionMode() {
        this.mActionMode = null;
    }
     public void onDetach() {
        super.onDetach();
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            Intrinsics.checkNotNull(actionMode);
            actionMode.finish();
        }
    }
 
    public void onResume() {
        super.onResume();
        restartLoader();
    }
 
    public void onDestroy() {
        super.onDestroy();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this.mReceiver);
    }
     public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SHOW_DETAIL, this.mDisplayOptions);
        outState.putParcelable(STATE_SALES_FICHE_MENU_RIGHTS, this.mSalesFicheMenuRights);
    }
 
    protected void createOptionsMenu(Menu menu, MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.menu_search, menu);
        createSearchView(menu.findItem(R.id.menu_search));
        inflater.inflate(R.menu.menu_sales_list, menu);
        super.createOptionsMenu(menu, inflater);
    } 
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.menu_new) {
            checkCustomerCreateFicheControls();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isCustomerAbleToCreateFiche() {
        CustomerRestrictionUtil customerRestrictionUtil = CustomerRestrictionUtil.INSTANCE;
        int i2 = this.mCustomerRef;
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        SalesType salesType = customerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        return !customerRestrictionUtil.hasCustomerRestriction(i2, salesType) && !hasCustomerOverdueDebts(this.mCustomerRef);
    }

    private void checkCustomerCreateFicheControls() {
        SuspicionsActivityUtils suspicionsActivityUtils = SuspicionsActivityUtils.INSTANCE;
        int i2 = this.mCustomerRef;
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        SalesType salesType = customerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (suspicionsActivityUtils.isPassedSuspiciousActivityControlsForSales(i2, salesType, this.repository, this.viewModel) && isCustomerAbleToCreateFiche()) {
            checkAutoVisit();
        }
    }

    private void checkAutoVisit() {
        if (this.viewModel.getBaseErp().isActiveAutoVisit()) {
            SalesUtils salesUtils = SalesUtils.INSTANCE;
            CustomerOperation customerOperation = this.mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation);
            SalesType salesType = customerOperation.getSalesType();
            Intrinsics.checkNotNull(salesType);
            if (salesUtils.isSalesOrOrder(salesType)) {
                AutoVisitUtils.checkAndCreateAutoVisit(this.mCustomerRef, new MyResponseListener());
                return;
            }
        }
        checkAutoTimeAndTimeZoneEnabledAndShowDialog();
    }

    private boolean hasCustomerOverdueDebts(int i2) {
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            return false;
        }
        ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
        this.viewModel.getCustomerRiskLimit(i2, new CheckOverdueDebtListener(this));
        return true;
    } 
    public void checkOverdueDebt(CustomerRiskLimit customerRiskLimit) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        SalesType salesType = customerOperation.getSalesType();
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoiceOrDispatch(salesType)) {
            Boolean isOverdueDebt = this.viewModel.getBaseErp().isOverdueDebt();
            Intrinsics.checkNotNullExpressionValue(isOverdueDebt, "isOverdueDebt(...)");
            if (isOverdueDebt.booleanValue()) {
                ErpType erpType = this.viewModel.erpType();
                ErpType erpType2 = ErpType.NETSIS;
                if ((erpType == erpType2 && customerRiskLimit.getDebit() > customerRiskLimit.getCredit()) || (this.viewModel.erpType() == ErpType.TIGER && customerRiskLimit.getTotal() > 0.0d)) {
                    if (this.viewModel.erpType() == erpType2) {
                        this.debtAmount = customerRiskLimit.getDebit() - customerRiskLimit.getCredit();
                    } else {
                        this.debtAmount = customerRiskLimit.getTotal();
                    }
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity);
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                    Context context = getContext();
                    Intrinsics.checkNotNull(context);
                    String string = context.getString(R.string.exp_has_overdue_debt);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{StringUtils.roundTwoDecimals(this.debtAmount) + ' ' + this.viewModel.getBaseErp().getLocalCurrencyCode()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    builder.setMessage(format).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentExternalSyntheticLambda5
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            SalesListFragment.checkOverdueDebtlambda6(dialogInterface, i2);
                        }
                    }).create().show();
                    return;
                }
            }
        }
        checkAutoVisit();
    } 
    public static void checkOverdueDebtlambda6(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    private record CheckOverdueDebtListener(
            WeakReference<SalesListFragment> mFragment) implements ResponseListener<ArrayList<CustomerRiskLimit>> {
            private CheckOverdueDebtListener(SalesListFragment mFragment) {
                Intrinsics.checkNotNullParameter(mFragment, "fragment");
                this.mFragment = new WeakReference<>(mFragment);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        SalesListFragment salesListFragment3 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment3);
                        Intrinsics.checkNotNull(arrayList);
                        CustomerRiskLimit customerRiskLimit = arrayList.get(0);
                        Intrinsics.checkNotNullExpressionValue(customerRiskLimit, "get(...)");
                        salesListFragment3.checkOverdueDebt(customerRiskLimit);
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }
    public void checkAutoTimeAndTimeZoneEnabledAndShowDialog() {
        if (ContextUtils.checkAutoTimeAndTimeZoneEnabledAndShowDialog()) {
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.viewModel.getBaseErp().checkRemoteWorkTimeControl(getWorkTimeControlProcessTypeFromSalesType(), new CheckWorkTimeListener(this));
        }
    }

    private WorkTimeControlProcessType getWorkTimeControlProcessTypeFromSalesType() {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        return customerOperation.getSalesType() == SalesType.ORDER ? WorkTimeControlProcessType.Order : WorkTimeControlProcessType.Sales;
    }

    private record CheckWorkTimeListener(
            WeakReference<SalesListFragment> mFragment) implements ResponseListener<String> {
            private CheckWorkTimeListener(SalesListFragment mFragment) {
                this.mFragment = new WeakReference<>(mFragment);
            }

        public void onResponse(PrintSlipModel str) {
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        if (!TextUtils.isEmpty(str)) {
                            SalesListFragment salesListFragment3 = this.mFragment.get();
                            Intrinsics.checkNotNull(salesListFragment3);
                            Toast.makeText(salesListFragment3.getActivity(), str, Toast.LENGTH_SHORT).show();
                        } else {
                            SalesListFragment salesListFragment4 = this.mFragment.get();
                            Intrinsics.checkNotNull(salesListFragment4);
                            salesListFragment4.startSalesActivity();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                    }
                }
            }
        }
    public void startSalesActivity() {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.NEW);
        Intent intent = new Intent(getActivity(), SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_SHIPREF, this.mCustomerShipRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CABIN_REF, getCabinRef());
        intent.putExtra(IntentExtraName.EXTRA_AUTO_VISIT_ID, this.mAutoVisitId);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }
    protected void createSearchView(MenuItem menuItem) {
        Drawable textCursorDrawable = null;
        ActionViewResolver actionViewResolver = this.mActionViewResolver;
        Intrinsics.checkNotNull(actionViewResolver);
        View actionView = actionViewResolver.getActionView(menuItem);
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        if (customerOperation.getSalesType() == SalesType.DEMAND) {
            searchView.setQueryHint(getString(R.string.hint_search_demand));
        } else {
            searchView.setQueryHint(getString(R.string.hint_search_sales));
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Object systemService = activity.getSystemService(Context.SEARCH_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.SearchManager");
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        searchView.setSearchableInfo(((SearchManager) systemService).getSearchableInfo(activity2.getComponentName()));
        searchView.setIconified(!this.mSearchViewExpanded);
        searchView.setQuery(this.mFilter, false);
        searchView.setOnQueryTextListener(this);
        View findViewById = searchView.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView.SearchAutoComplete");
        @SuppressLint("RestrictedApi") SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById;
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= 29 && textCursorDrawable != null) {
            textCursorDrawable.setColorFilter(ContextCompat.getColor(requireContext(), R.color.custom_cursor_color), PorterDuff.Mode.SRC_IN);
            searchAutoComplete.setTextCursorDrawable(textCursorDrawable);
        }
        searchView.setOnSearchClickListener(new OnClickListener() { 
            public void onClick(View view) {
                SalesListFragment.createSearchViewlambda7(SalesListFragment.this, view);
            }
        });
        searchView.setOnCloseListener(new OnCloseListener() {   
            public boolean onClose() {
                boolean createSearchViewlambda8;
                createSearchViewlambda8 = SalesListFragment.createSearchViewlambda8(SalesListFragment.this);
                return createSearchViewlambda8;
            }
        });
    } 
    public static void createSearchViewlambda7(SalesListFragment this0, View v) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(v, "v");
        this0.mSearchViewExpanded = true;
        v.requestFocus();
    } 
    public static boolean createSearchViewlambda8(SalesListFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.mFilter = "";
        AppCompatActivity appCompatActivity = (AppCompatActivity) this0.getActivity();
        Intrinsics.checkNotNull(appCompatActivity);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setSubtitle(this0.mFilter);
        this0.restartLoader();
        return false;
    } 
    protected IListRecyclerView getAdapter() {
        return this.mAdapter;
    }
 
    public boolean onQueryTextChange(String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        restartLoader();
        return false;
    } 
    public void restartLoader() {
        getMProgressDialogBuilder().setMessage("Loading").show();
        getLoaderManager().restartLoader(0, null, this);
    }
 
    public Loader<Cursor> onCreateLoader(int i2, Bundle bundle) {
        return new ISqlManager.Loader(getActivity(), getSalesSql());
    }
 
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        Intrinsics.checkNotNullParameter(data, "data");
        this.viewModel.getSalesCursorToList(data, new SalesListResponseListener(this));
    }
 
    public void onLoaderReset(Loader<Cursor> loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        swapCursor(null);
    }
 
    public void swapCursor(List<SalesShort> list) {
        this.mAdapter.setSalesList(list);
        this.mAdapter.setmShowSalesDetail(this.mDisplayOptions);
        this.mAdapter.setmSalesListFragment(this);
        this.mAdapter.setmSalesFicheMenuRights(this.mSalesFicheMenuRights);
        try {
            this.mAdapter.setEDocInfoModel(this.viewModel.getEDocInfo(this.mCustomerRef, -1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.mAdapter.setEArchiveCustomer(Intrinsics.areEqual(this.viewModel.getSqlHelper().getLogoParamValue("USEEARCHIVE"), BuildConfig.NETSIS_DEMO_PASSWORD) && this.viewModel.getSqlHelper().getClEInvoiceUser(this.mCustomerRef) != 1);
        if (!isDetached()) {
            toggleEmptyView(this.mAdapter.getItemCount() == 0, this.mFilter);
        }
        getMProgressDialogBuilder().dismiss();
        if (getMSwipeRefreshLayout().isRefreshing()) {
            getMSwipeRefreshLayout().setRefreshing(false);
        }
    }

    private String getSalesSql() {
        String salesListSql = this.viewModel.getSqlHelper().getSalesListSql(getContext(), this.mCustomerRef, this.mCustomerOperation, this.mFilter);
        Intrinsics.checkNotNullExpressionValue(salesListSql, "getSalesListSql(...)");
        return salesListSql;
    }

    public void openSalesFiche(Sales sales, FicheMode ficheMode) {
        Intrinsics.checkNotNullParameter(ficheMode, "ficheMode");
        if (ficheMode == FicheMode.ANALYSE) {
            openSalesFicheAnalyzeMode(sales);
            return;
        }
        if (ficheMode == FicheMode.COPY) {
            openSalesFicheCopyMode(sales);
            return;
        }
        if (ficheMode == FicheMode.EDIT) {
            openSalesFicheEditMode(sales);
        } else {
            if (ficheMode != FicheMode.SENDMAIL || sales == null) {
                return;
            }
            prepareToSendEmail(sales);
        }
    }

    private void prepareToSendEmail(final Sales sales) {
        try {
            if (this.viewModel.getTableForEmailTemplateFromSqlHelper(EmailTemplate.class, "FORMTYPE=?", new String[]{String.valueOf(sales.getEmailTemplateType().getmValue())}).isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.str_not_found_any_email_form_design), Toast.LENGTH_LONG).show();
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_email_input, null);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(inflate);
        final TextView textView = inflate.findViewById(R.id.txtViewError);
        textView.setVisibility(View.GONE);
        final EditText editText = inflate.findViewById(R.id.edtDialogUserInput);
        editText.setText(this.viewModel.getBaseErp().getCustomerInfo(this.mCustomerRef).getEmailAddr());
        Intrinsics.checkNotNull(editText);
        selectAllAndFocusOfEditText(editText);
        Intrinsics.checkNotNull(textView);
        editText.addTextChangedListener(new EmailDialogTextChangeListener(this, textView));
        builder.setPositiveButton(R.string.ok, null).setNegativeButton(R.string.cancel, null);
        final AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        final String str = "(([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))(((;|,|; | ;| ; | , | ,){1}([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4}))*)";
        create.setOnShowListener(new DialogInterface.OnShowListener() {  
            public void onShow(DialogInterface dialogInterface) {
                SalesListFragment.prepareToSendEmaillambda10(  editText, str, textView, SalesListFragment.this, sales, dialogInterface);
            }
        });
        create.show();
    } 
    public static void prepareToSendEmaillambda10(final EditText editText, final String regEx, final TextView textView, final SalesListFragment this0, final Sales sales, final DialogInterface dialog) {
        android.app.AlertDialog alertDialog = null;
        Intrinsics.checkNotNullParameter(alertDialog, "alertDialog");
        Intrinsics.checkNotNullParameter(regEx, "regEx");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        alertDialog.getButton(-1).setOnClickListener(new OnClickListener() { 
            public void onClick(View view) {
                SalesListFragment.prepareToSendEmaillambda10lambda9(editText, regEx, textView, this0, dialog, sales, view);
            }
        });
    } 
    public static void prepareToSendEmaillambda10lambda9(EditText editText, String regEx, TextView textView, SalesListFragment this0, DialogInterface dialog, Sales sales, View view) {
        Intrinsics.checkNotNullParameter(regEx, "regEx");
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(sales, "sales");
        String obj = editText.getText().toString();
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            if (!Pattern.matches(regEx, editText.getText())) {
                textView.setText(this0.getString(R.string.str_invalid_email_address));
                textView.setVisibility(View.VISIBLE);
                return;
            }
            dialog.dismiss();
            ProgressDialogBuilder<?> mProgressDialogBuilder = this0.getMProgressDialogBuilder();
            Context context = this0.getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            Class cls = SalesUtils.isSalesTypeOrderOrDemand(sales.getmSalesType()) ? Order.class : Invoice.class;
            EmailReplacerModel emailReplacerModel = new EmailReplacerModel();
            emailReplacerModel.setEmailReplacer(sales);
            emailReplacerModel.setFicheRef(sales.getFicheRef());
            emailReplacerModel.setClRef(sales.getClRef());
            emailReplacerModel.setEmail(obj);
            this0.viewModel.getCustomerNowAndBeforeBalance(sales.getClRef(), cls, sales.getFicheRef(), new CustomerNowAndBeforeResponseListener(this0, emailReplacerModel));
            return;
        }
        textView.setText(this0.getString(R.string.str_email_address_required));
        textView.setVisibility(View.VISIBLE);
    }

    private record CustomerNowAndBeforeResponseListener(
            WeakReference<SalesListFragment> mSalesListFragmentWeakReference,
            WeakReference<EmailReplacerModel> mSalesWeakReference) implements ResponseListener<ArrayList<CustomerBeforeBalance>> {
            private CustomerNowAndBeforeResponseListener(SalesListFragment mSalesListFragmentWeakReference, EmailReplacerModel mSalesWeakReference) {
                Intrinsics.checkNotNullParameter(mSalesWeakReference, "emailReplacerModel");
                this.mSalesListFragmentWeakReference = new WeakReference<>(mSalesListFragmentWeakReference);
                this.mSalesWeakReference = new WeakReference<>(mSalesWeakReference);
            }

        public void onResponse(PrintSlipModel arrayList) {
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        SalesListFragment salesListFragment3 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment3);
                        salesListFragment3.onCustomerNowAndBefore(this.mSalesWeakReference.get(), arrayList);
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        Log.d("CustomerNowAndBefore", "onError: " + errorMessage);
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        SalesListFragment salesListFragment3 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment3);
                        salesListFragment3.onCustomerNowAndBefore(this.mSalesWeakReference.get(), null);
                    }
                }
            }
        }
    public void onCustomerNowAndBefore(EmailReplacerModel emailReplacerModel, ArrayList<CustomerBeforeBalance> arrayList) {
        CustomerBeforeBalance customerBeforeBalance;
        if (emailReplacerModel != null) {
            getMProgressDialogBuilder().setMessage(getString(R.string.str_applying_email_form_design)).show();
            SalesListViewModel salesListViewModel = this.viewModel;
            if (arrayList == null || arrayList.size() == 0) {
                customerBeforeBalance = new CustomerBeforeBalance(0.0d, 0.0d, 3, null);
            } else {
                CustomerBeforeBalance customerBeforeBalance2 = arrayList.get(0);
                Intrinsics.checkNotNullExpressionValue(customerBeforeBalance2, "get(...)");
                customerBeforeBalance = customerBeforeBalance2;
            }
            salesListViewModel.replaceSalesFicheHtml(emailReplacerModel, customerBeforeBalance, new ReplaceHtmlResponseListener(this));
            return;
        }
        Toast.makeText(getContext(), getString(R.string.str_get_print_value_error), Toast.LENGTH_LONG).show();
    }

    private record ReplaceHtmlResponseListener(
            WeakReference<SalesListFragment> mSalesListFragmentWeakReference) implements ResponseListener<EmailObject> {
            private ReplaceHtmlResponseListener(SalesListFragment mSalesListFragmentWeakReference) {
                this.mSalesListFragmentWeakReference = new WeakReference<>(mSalesListFragmentWeakReference);
            }

        public void onResponse(PrintSlipModel emailObject) {
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        SalesListFragment salesListFragment3 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment3);
                        salesListFragment3.onReplaceHtml("", emailObject);
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        Log.d("CustomerNowAndBefore", "onError: " + errorMessage);
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.getMProgressDialogBuilder().dismiss();
                        SalesListFragment salesListFragment3 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment3);
                        salesListFragment3.onReplaceHtml(errorMessage, null);
                    }
                }
            }
        }
    public void onReplaceHtml(String str, EmailObject emailObject) {
        if (TextUtils.isEmpty(str) && emailObject != null) {
            if (emailObject.getTo() != null) {
                String[] to = emailObject.getTo();
                Intrinsics.checkNotNullExpressionValue(to, "getTo(...)");
                if (!(to.length == 0) && Intrinsics.areEqual(emailObject.getTo()[0], this.PdfEmailToAddress)) {
                    exportSalesPdf(emailObject.getHtml());
                    return;
                }
            }
            BaseErpActivity baseErpActivity = (BaseErpActivity) getActivity();
            Intrinsics.checkNotNull(baseErpActivity);
            baseErpActivity.sendFicheMail(emailObject);
            return;
        }
        Toast.makeText(getContext(), getString(R.string.str_send_email_failed), Toast.LENGTH_LONG).show();
    }
 
    private final class EmailDialogTextChangeListener implements TextWatcher {
        private TextView _errorTextView;
        final  SalesListFragment this0;
 
        public void afterTextChanged(Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
        }
 
        public void beforeTextChanged(CharSequence s, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        public EmailDialogTextChangeListener(SalesListFragment salesListFragment, TextView _errorTextView) {
            Intrinsics.checkNotNullParameter(_errorTextView, "_errorTextView");
            this.this0 = salesListFragment;
            this._errorTextView = _errorTextView;
        }

        public TextView get_errorTextView() {
            return this._errorTextView;
        }

        public void set_errorTextView(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this._errorTextView = textView;
        } 
        public void onTextChanged(CharSequence s, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(s, "s");
            this._errorTextView.setVisibility(View.GONE);
            this._errorTextView.setText("");
        }
    }

    private void openSalesFicheAnalyzeMode(Sales sales) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.ANALYSE);
        Intent intent = new Intent(getActivity(), SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CABIN_REF, getCabinRef());
        try {
            intent.putExtra("bigdata:synccode", this.viewModel.getSaveObjectWithSales(sales));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }

    private void openSalesFicheEditMode(Sales sales) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.EDIT);
        Intent intent = new Intent(getActivity(), SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CABIN_REF, getCabinRef());
        try {
            intent.putExtra("bigdata:synccode", this.viewModel.getSaveObjectWithSales(sales));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    }

    private void openSalesFicheCopyMode(Sales sales) {
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        customerOperation.setFicheMode(FicheMode.COPY);
        Intent intent = new Intent(getActivity(), SalesActivityNew.class);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEPLAN_REF, this.routePlanRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEDAY_REF, this.routeDayRef);
        intent.putExtra(IntentExtraName.EXTRA_ROUTEUSERCUSTOMER_REF, this.routeUserCustomerRef);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, this.mCustomerRef);
        intent.putExtra(IntentExtraName.EXTRA_CABIN_REF, getCabinRef());
        try {
            intent.putExtra("bigdata:synccode", this.viewModel.getSaveObjectWithSales(sales));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, this.mCustomerOperation);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.startActivity(intent);
    } 
    public void refreshTransferAfterList(boolean z) {
        if (!z || isDetached()) {
            return;
        }
        this.inTransferFicheList.clear();
        restartLoader();
    }

    public   boolean getCustomerEInvoice() {
        return this.viewModel.getSqlHelper().isEInvoiceOrEArchiveCustomer(this.mCustomerRef);
    }

    private record SalesListResponseListener(
            WeakReference<SalesListFragment> mSalesListFragmentWeakReference) implements ResponseListener<List<? extends SalesShort>> {
            private SalesListResponseListener(SalesListFragment mSalesListFragmentWeakReference) {
                this.mSalesListFragmentWeakReference = new WeakReference<>(mSalesListFragmentWeakReference);
            }

        public void onResponse(PrintSlipModel list) {
                onResponse2((List<SalesShort>) list);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onResponse2(List<SalesShort> list) {
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.swapCursor(list);
                    }
                }
            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mSalesListFragmentWeakReference.get() != null) {
                    SalesListFragment salesListFragment = this.mSalesListFragmentWeakReference.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        Log.d("AA", "onError: " + errorMessage);
                        SalesListFragment salesListFragment2 = this.mSalesListFragmentWeakReference.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onSalesLoadError(errorMessage);
                    }
                }
            }
        }
    public   void onSalesLoadError(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    private void clearExistingReports(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                Intrinsics.checkNotNull(listFiles);
                for (File file2 : listFiles) {
                    file2.delete();
                }
                return;
            }
            file.mkdir();
        } catch (Exception e2) {
            Log.e("SalesListActivity", "clearExistingReports", e2);
        }
    }
 
    public void onSalesGet(Sales sales, String str) {
        getMProgressDialogBuilder().dismiss();
        if (sales != null) {
            try {
                if (this.viewModel.getTableForEmailTemplateFromSqlHelper(EmailTemplate.class, "FORMTYPE=?", new String[]{String.valueOf(sales.getEmailTemplateType().getmValue())}).isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.str_not_found_any_email_form_design), Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            this.mSalesPdfName = sales.getFicheNo();
            Class cls = SalesUtils.isSalesTypeOrderOrDemand(sales.getmSalesType()) ? Order.class : Invoice.class;
            EmailReplacerModel emailReplacerModel = new EmailReplacerModel();
            emailReplacerModel.setEmailReplacer(sales);
            emailReplacerModel.setFicheRef(sales.getFicheRef());
            emailReplacerModel.setClRef(sales.getClRef());
            emailReplacerModel.setEmail(this.PdfEmailToAddress);
            this.viewModel.getCustomerNowAndBeforeBalance(sales.getClRef(), cls, sales.getFicheRef(), new CustomerNowAndBeforeResponseListener(this, emailReplacerModel));
            return;
        }
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    public Unit getSalesFicheForPdf() {
        ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        mProgressDialogBuilder.setMessage(context.getString(R.string.str_fiche_loading)).setCancelable(false).show();
        SalesType.Companion companion = SalesType.Companion;
        SalesShort salesShort = this.selectedSalesItem;
        Intrinsics.checkNotNull(salesShort);
        if (SalesUtils.isSalesTypeOrder(companion.fromSalesType(salesShort.getType()))) {
            SalesListViewModel salesListViewModel = this.viewModel;
            SalesShort salesShort2 = this.selectedSalesItem;
            Intrinsics.checkNotNull(salesShort2);
            salesListViewModel.getSalesOrderOne(salesShort2.getLogicalRef(), new SalesFicheGet(this));
        } else {
            SalesListViewModel salesListViewModel2 = this.viewModel;
            SalesShort salesShort3 = this.selectedSalesItem;
            Intrinsics.checkNotNull(salesShort3);
            salesListViewModel2.getSalesInvoiceExam(salesShort3.getLogicalRef(), new SalesFicheGet(this));
        }
        return Unit.INSTANCE;
    }

    private record SalesFicheGet(WeakReference<SalesListFragment> mFragment) implements ResponseListener<Sales> {
            private SalesFicheGet(SalesListFragment mFragment) {
                this.mFragment = new WeakReference<>(mFragment);
            }

        public void onResponse(PrintSlipModel sales) {
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onSalesGet(sales, "");
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onSalesGet(null, errorMessage);
                    }
                }
            }
        }

    public void exportSalesPdf(String str) {
        this.cacheSalesPdfFilePath = "";
        getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).show();
        try {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            CreatePdf createPdf = new CreatePdf(activity);
            String str2 = this.mSalesPdfName;
            Intrinsics.checkNotNull(str2);
            CreatePdf contentBaseUrl = createPdf.setPdfName(str2).openPrintDialog(false).setContentBaseUrl(null);
            PrintAttributes.MediaSize ISO_A4 = PrintAttributes.MediaSize.ISO_A4;
            Intrinsics.checkNotNullExpressionValue(ISO_A4, "ISO_A4");
            CreatePdf pageSize = contentBaseUrl.setPageSize(ISO_A4);
            Intrinsics.checkNotNull(str);
            CreatePdf content = pageSize.setContent(str);
            String str3 = this.cacheSalesPdfFolderPath;
            Intrinsics.checkNotNull(str3);
            content.setFilePath(str3).setCallbackListener(new CreatePdf.PdfCallbackListener() { 
 
                public  class WhenMappings {
                    public static final   int[] EnumSwitchMapping0;

                    static {
                        int[] iArr = new int[PdfExportOption.values().length];
                        try {
                            iArr[PdfExportOption.Share.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[PdfExportOption.Preview.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[PdfExportOption.Download.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        EnumSwitchMapping0 = iArr;
                    }
                }
 
                public void onFailure(String s) {
                    Intrinsics.checkNotNullParameter(s, "s");
                    SalesListFragment.this.getMProgressDialogBuilder().dismiss();
                    Toast.makeText(SalesListFragment.this.getActivity(), SalesListFragment.this.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                }
 
                public void onSuccess(String s) {
                    PdfExportOption pdfExportOption;
                    Intrinsics.checkNotNullParameter(s, "s");
                    SalesListFragment.this.getMProgressDialogBuilder().dismiss();
                    SalesListFragment.this.cacheSalesPdfFilePath = s;
                    FragmentActivity activity2 = SalesListFragment.this.getActivity();
                    Intrinsics.checkNotNull(activity2);
                    Uri uriForFile = FileProvider.getUriForFile(activity2, "com.proje.mobilesales.fileprovider", new File(s));
                    pdfExportOption = SalesListFragment.this.mPdfExportOption;
                    int i2 = WhenMappings.EnumSwitchMapping0[pdfExportOption.ordinal()];
                    if (i2 == 1) {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("application/pdf");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(uriForFile, "application/pdf");
                        intent.putExtra("android.intent.extra.STREAM", uriForFile);
                        FragmentActivity activity3 = SalesListFragment.this.getActivity();
                        Intrinsics.checkNotNull(activity3);
                        activity3.startActivity(Intent.createChooser(intent, SalesListFragment.this.getString(R.string.menu_pdf_share)));
                        return;
                    }
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                        SalesListFragment.this.createPdfToSelectedFolder();
                        return;
                    }
                    try {
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW").setClassName(getPackageName(), "com.proje.mobilesales.features.settings.view.activity.PreferenceActivity");
                        intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent2.setDataAndType(uriForFile, "application/pdf");
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        FragmentActivity activity4 = SalesListFragment.this.getActivity();
                        Intrinsics.checkNotNull(activity4);
                        activity4.startActivity(intent2);
                    } catch (ActivityNotFoundException e2) {
                        Toast.makeText(SalesListFragment.this.getActivity(), R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
                        Log.e("UserReportsActivity", "Activity not found for pdf", e2);
                    }
                }
            }).create();
        } catch (Exception e2) {
            Log.e("SalesFragment", "exportPdf", e2);
            getMProgressDialogBuilder().dismiss();
            Toast.makeText(getActivity(), getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }

    private Context getPackageName() {
        return null;
    }

    public Unit getEDocumentSalesForPdf() {
        boolean z = false;
        getMProgressDialogBuilder().setMessage(getString(R.string.str_fiche_loading)).setCancelable(false).show();
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentDraftPdf) {
            CustomerOperation customerOperation = this.mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation);
            if (customerOperation.getSalesType() == SalesType.DISPATCH) {
                if (this.viewModel.erpType() == ErpType.TIGER) {
                    if (isConnected(getContext())) {
                        SalesShort salesShort = this.selectedSalesItem;
                        Intrinsics.checkNotNull(salesShort);
                    }
                    getEDocumentContentFromLocal("edespatch_html_template", "edespatch_line_template");
                }
                getEDocumentContent("edespatch_html_template", "edespatch_line_template", PrintAttributes.MediaSize.ISO_A4, this.mAdapter.isEArchiveCustomer());
            } else {
                CustomerOperation customerOperation2 = this.mCustomerOperation;
                Intrinsics.checkNotNull(customerOperation2);
                if (customerOperation2.getSalesType() == SalesType.INVOICE) {
                    if (this.mAdapter.isEArchiveCustomer()) {
                        if (this.viewModel.erpType() == ErpType.TIGER) {
                            if (isConnected(getContext())) {
                                SalesShort salesShort2 = this.selectedSalesItem;
                                Intrinsics.checkNotNull(salesShort2);
                            }
                            getEDocumentContentFromLocal("earchive_invoice_html_template", "einvoice_line_template");
                        }
                        getEDocumentContent("earchive_invoice_html_template", "einvoice_line_template", PrintAttributes.MediaSize.ISO_A4, this.mAdapter.isEArchiveCustomer());
                    } else {
                        if (this.viewModel.erpType() == ErpType.TIGER) {
                            if (isConnected(getContext())) {
                                SalesShort salesShort3 = this.selectedSalesItem;
                                Intrinsics.checkNotNull(salesShort3);
                            }
                            getEDocumentContentFromLocal("einvoice_html_template", "einvoice_line_template");
                        }
                        getEDocumentContent("einvoice_html_template", "einvoice_line_template", PrintAttributes.MediaSize.ISO_A4, this.mAdapter.isEArchiveCustomer());
                    }
                }
            }
        } else if (pdfOperation == PdfOperation.EDocumentDraftPdfThreeInc) {
            CustomerOperation customerOperation3 = this.mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation3);
            if (customerOperation3.getSalesType() == SalesType.INVOICE) {
                if (this.mAdapter.isEArchiveCustomer()) {
                    getEDocumentContent("earchive_invoice_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.mAdapter.isEArchiveCustomer());
                } else {
                    getEDocumentContent("einvoice_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.mAdapter.isEArchiveCustomer());
                }
            } else {
                CustomerOperation customerOperation4 = this.mCustomerOperation;
                Intrinsics.checkNotNull(customerOperation4);
                if (customerOperation4.getSalesType() == SalesType.DISPATCH) {
                    if (this.viewModel.erpType() == ErpType.NETSIS) {
                        getEDocumentContent("edespatch_3_inc_html_template_netsis", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.mAdapter.isEArchiveCustomer());
                    } else {
                        getEDocumentContent("edespatch_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.mAdapter.isEArchiveCustomer());
                    }
                } else {
                    CustomerOperation customerOperation5 = this.mCustomerOperation;
                    Intrinsics.checkNotNull(customerOperation5);
                    if (customerOperation5.getSalesType() == SalesType.WHTRANSFER) {
                        if (Intrinsics.areEqual(this.viewModel.getSqlHelper().getLogoParamValue("USEEARCHIVE"), BuildConfig.NETSIS_DEMO_PASSWORD)) {
                            ISqlHelper<?> sqlHelper = this.viewModel.getSqlHelper();
                            SalesShort salesShort4 = this.selectedSalesItem;
                            Intrinsics.checkNotNull(salesShort4);
                            if (sqlHelper.getClEInvoiceUser(salesShort4.getClRef()) != 1) {
                                z = true;
                            }
                        }
                        this.isEArchiveCustomer = z;
                        if (this.viewModel.erpType() == ErpType.NETSIS) {
                            getEDocumentContent("edespatch_3_inc_html_template_netsis", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.mAdapter.isEArchiveCustomer());
                        } else {
                            getEDocumentContent("edespatch_3_inc_html_template", "edocument_line_template_for_3_inc", setThreeIncPdfMediaSize(), this.isEArchiveCustomer);
                        }
                    }
                }
            }
        } else {
            SalesListViewModel salesListViewModel = this.viewModel;
            SalesShort salesShort5 = this.selectedSalesItem;
            Intrinsics.checkNotNull(salesShort5);
            int logicalRef = salesShort5.getLogicalRef();
            CustomerOperation customerOperation6 = this.mCustomerOperation;
            Intrinsics.checkNotNull(customerOperation6);
            salesListViewModel.showEDocument(logicalRef, customerOperation6.getFicheType(), new ShowEDocumentContentListener(this));
        }
        return Unit.INSTANCE;
    }

    private void getEDocumentContent(String str, String str2, PrintAttributes.MediaSize mediaSize, boolean z) {
        SalesListViewModel salesListViewModel = this.viewModel;
        SalesShort salesShort = this.selectedSalesItem;
        Intrinsics.checkNotNull(salesShort);
        String ficheId = salesShort.getFicheId();
        Intrinsics.checkNotNull(ficheId);
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        EDocumentPdfContentListener eDocumentPdfContentListener = new EDocumentPdfContentListener(this, str, str2, mediaSize, customerOperation.getSalesType());
        CustomerOperation customerOperation2 = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        salesListViewModel.getEDocumentContent(ficheId, eDocumentPdfContentListener, customerOperation2.getSalesType(), Boolean.valueOf(z));
    }

    private void getEDocumentContentFromLocal(String str, String str2) {
        SalesListViewModel salesListViewModel = this.viewModel;
        SalesShort salesShort = this.selectedSalesItem;
        Intrinsics.checkNotNull(salesShort);
        int logicalRef = salesShort.getLogicalRef();
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        PrintAttributes.MediaSize mediaSize = PrintAttributes.MediaSize.ISO_A4;
        CustomerOperation customerOperation = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation);
        EDocumentPdfContentListener eDocumentPdfContentListener = new EDocumentPdfContentListener(this, str, str2, mediaSize, customerOperation.getSalesType());
        CustomerOperation customerOperation2 = this.mCustomerOperation;
        Intrinsics.checkNotNull(customerOperation2);
        salesListViewModel.getEDocumentContentFromLocal(logicalRef, eDocumentPdfContentListener, customerOperation2.getSalesType());
    }

    private PrintAttributes.MediaSize setThreeIncPdfMediaSize() {
        return new PrintAttributes.MediaSize(BuildConfig.NETSIS_DEMO_PASSWORD, BuildConfig.NETSIS_DEMO_PASSWORD, 2900, 7800);
    }

    
    public void onShowEDocumentContent(ShowEDocumentResponse showEDocumentResponse, String str) {
        getMProgressDialogBuilder().dismiss();
        if (showEDocumentResponse != null) {
            if (!showEDocumentResponse.isSuccess()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                new AlertDialog.Builder(activity).setMessage(showEDocumentResponse.getErrorDesc()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentExternalSyntheticLambda7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        SalesListFragment.onShowEDocumentContentlambda11(dialogInterface, i2);
                    }
                }).create().show();
                return;
            }
            if (showEDocumentResponse.isSuccess() && TextUtils.isEmpty(showEDocumentResponse.getHtmlContent())) {
                Toast.makeText(getContext(), getString(R.string.str_get_print_value_not_found), Toast.LENGTH_LONG).show();
                return;
            }
            try {
                this.cacheReportPdfFilePath = "";
                getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).setCancelable(true).show();
                String ettn = showEDocumentResponse.getEttn();
                Intrinsics.checkNotNullExpressionValue(ettn, "getEttn(...)");
                String htmlContent = showEDocumentResponse.getHtmlContent();
                Intrinsics.checkNotNullExpressionValue(htmlContent, "getHtmlContent(...)");
                PrintAttributes.MediaSize ISO_A4 = PrintAttributes.MediaSize.ISO_A4;
                Intrinsics.checkNotNullExpressionValue(ISO_A4, "ISO_A4");
                createPdfFromHtmlContent(ettn, htmlContent, ISO_A4);
                return;
            } catch (Exception e2) {
                Log.e("SalesFragment", "exportPdf", e2);
                getMProgressDialogBuilder().dismiss();
                Toast.makeText(getActivity(), getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    
    public static void onShowEDocumentContentlambda11(DialogInterface dialog, int i2) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
    }

    /* compiled from: SalesListFragment.kt */
        private record ShowEDocumentContentListener(
            WeakReference<SalesListFragment> mFragment) implements ResponseListener<ShowEDocumentResponse> {
            private ShowEDocumentContentListener(SalesListFragment mFragment) {
                this.mFragment = new WeakReference<>(mFragment);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(PrintSlipModel showEDocumentResponse) {
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onShowEDocumentContent(showEDocumentResponse, "");
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onShowEDocumentContent(null, errorMessage);
                    }
                }
            }
        }

    
    public void onGetEDocumentPdfContent(EDocumentPdfContent eDocumentPdfContent, String str, String str2, String str3, PrintAttributes.MediaSize mediaSize, SalesType salesType) {
        getMProgressDialogBuilder().dismiss();
        if (eDocumentPdfContent != null) {
            if (!TextUtils.isEmpty(eDocumentPdfContent.getErrorDesc())) {
                Toast.makeText(getContext(), eDocumentPdfContent.getErrorDesc(), Toast.LENGTH_LONG).show();
                return;
            }
            if (this.viewModel.erpType() != ErpType.NETSIS) {
                SalesListViewModel salesListViewModel = this.viewModel;
                String firmNr = salesListViewModel.user().getFirmNr();
                Intrinsics.checkNotNullExpressionValue(firmNr, "getFirmNr(...)");
                List<Firm> tableForFirmFromSqlHelper = null;
                try {
                    tableForFirmFromSqlHelper = salesListViewModel.getTableForFirmFromSqlHelper(Firm.class, "NR=?", new String[]{firmNr});
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                eDocumentPdfContent.setFirm((tableForFirmFromSqlHelper == null || tableForFirmFromSqlHelper.get(0) == null) ? new Firm() : tableForFirmFromSqlHelper.get(0));
            }
            try {
                eDocumentPdfContent.setLocalCurrencyCode(this.viewModel.getLocalCurrencyCode());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Intrinsics.checkNotNull(mediaSize);
            exportEDocumentPdf(eDocumentPdfContent, str2, str3, mediaSize, salesType);
            return;
        }
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    private record EDocumentPdfContentListener(WeakReference<SalesListFragment> mFragment, String mTemplateType,
                                               String mAssetName, PrintAttributes.MediaSize mMediaSize,
                                               SalesType mSalesType) implements ResponseListener<EDocumentPdfContent> {
            private EDocumentPdfContentListener(SalesListFragment mFragment, String mTemplateType, String mAssetName, PrintAttributes.MediaSize mMediaSize, SalesType mSalesType) {
                Intrinsics.checkNotNullParameter(mTemplateType, "templateType");
                Intrinsics.checkNotNullParameter(mAssetName, "assetName");
                this.mFragment = new WeakReference<>(mFragment);
                this.mTemplateType = mTemplateType;
                this.mMediaSize = mMediaSize;
                this.mAssetName = mAssetName;
                this.mSalesType = mSalesType;
            }

            public void onResponse(PrintSlipModel eDocumentPdfContent) {
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onGetEDocumentPdfContent(eDocumentPdfContent, "", this.mTemplateType, this.mAssetName, this.mMediaSize, this.mSalesType);
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (this.mFragment.get() != null) {
                    SalesListFragment salesListFragment = this.mFragment.get();
                    Intrinsics.checkNotNull(salesListFragment);
                    if (salesListFragment.isAttached()) {
                        SalesListFragment salesListFragment2 = this.mFragment.get();
                        Intrinsics.checkNotNull(salesListFragment2);
                        salesListFragment2.onGetEDocumentPdfContent(null, errorMessage, this.mTemplateType, this.mAssetName, this.mMediaSize, this.mSalesType);
                    }
                }
            }
        }

    public void exportEDocumentPdf(EDocumentPdfContent content, String str, String str2, PrintAttributes.MediaSize mediaSize, SalesType salesType) {
        String ficheNo;
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(mediaSize, "mediaSize");
        this.cacheReportPdfFilePath = "";
        getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).show();
        if (this.viewModel.erpType() == ErpType.NETSIS) {
            ficheNo = content.getEDocumentPdfHeaderNetsis().getFicheNo();
        } else if (salesType == SalesType.DISPATCH || salesType == SalesType.WHTRANSFER) {
            ficheNo = content.getEDespatchPdfHeader().getFicheNo();
        } else {
            ficheNo = salesType == SalesType.INVOICE ? content.getEInvoicePdfHeader().getInvoiceNo() : null;
        }
        try {
            String prepareHtml = new EDocumentHtmlReplacer(content).prepareHtml(str, str2, salesType);
            String str3 = this.cacheReportPdfFolderPath;
            Intrinsics.checkNotNull(str3);
            clearExistingReports(str3);
            if (ficheNo == null) {
                ficheNo = StringUtils.empty();
            }
            Intrinsics.checkNotNull(prepareHtml);
            createPdfFromHtmlContent(ficheNo, prepareHtml, mediaSize);
        } catch (Exception e2) {
            Log.e("SalesFragment", "exportPdf", e2);
            getMProgressDialogBuilder().dismiss();
            Toast.makeText(getActivity(), getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void createPdfFromHtmlContent(String str, String str2, PrintAttributes.MediaSize mediaSize) {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        CreatePdf content = new CreatePdf(activity).setPdfName(str).openPrintDialog(false).setContentBaseUrl(null).setPageSize(mediaSize).setContent(str2);
        String str3 = this.cacheReportPdfFolderPath;
        Intrinsics.checkNotNull(str3);
        content.setFilePath(str3).setCallbackListener(new CreatePdf.PdfCallbackListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentcreatePdfFromHtmlContent1

            /* compiled from: SalesListFragment.kt */
            public class WhenMappings {
                public static final int[] EnumSwitchMapping0;

                static {
                    int[] iArr = new int[PdfExportOption.values().length];
                    try {
                        iArr[PdfExportOption.Share.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[PdfExportOption.Preview.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[PdfExportOption.Download.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    EnumSwitchMapping0 = iArr;
                }
            }

            @Override // com.proje.mobilesales.core.utils.CreatePdf.PdfCallbackListener
            public void onFailure(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                SalesListFragment.this.getMProgressDialogBuilder().dismiss();
                Toast.makeText(SalesListFragment.this.getActivity(), SalesListFragment.this.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
            }

            @Override // com.proje.mobilesales.core.utils.CreatePdf.PdfCallbackListener
            public void onSuccess(String s) {
                PdfExportOption pdfExportOption;
                Intrinsics.checkNotNullParameter(s, "s");
                SalesListFragment.this.getMProgressDialogBuilder().dismiss();
                SalesListFragment.this.cacheReportPdfFilePath = s;
                FragmentActivity activity2 = SalesListFragment.this.getActivity();
                Intrinsics.checkNotNull(activity2);
                Uri uriForFile = FileProvider.getUriForFile(activity2, "com.proje.mobilesales.fileprovider", new File(s));
                pdfExportOption = SalesListFragment.this.mPdfExportOption;
                int i2 = WhenMappings.EnumSwitchMapping0[pdfExportOption.ordinal()];
                if (i2 == 1) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("application/pdf");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(uriForFile, "application/pdf");
                    intent.putExtra("android.intent.extra.STREAM", uriForFile);
                    FragmentActivity activity3 = SalesListFragment.this.getActivity();
                    Intrinsics.checkNotNull(activity3);
                    activity3.startActivity(Intent.createChooser(intent, SalesListFragment.this.getString(R.string.menu_pdf_share)));
                    return;
                }
                if (i2 != 2) {
                    if (i2 != 3) {
                        return;
                    }
                    SalesListFragment.this.createPdfToSelectedFolder();
                    return;
                }
                try {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW").setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.proje.mobilesales.features.settings.view.activity.PreferenceActivity");
                    intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent2.setDataAndType(uriForFile, "application/pdf");
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    FragmentActivity activity4 = SalesListFragment.this.getActivity();
                    Intrinsics.checkNotNull(activity4);
                    activity4.startActivity(intent2);
                } catch (ActivityNotFoundException e2) {
                    Toast.makeText(SalesListFragment.this.getActivity(), R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
                    Log.e("UserReportsActivity", "Activity not found for pdf", e2);
                }
            }
        }).create();
    }
    public void createPdfToSelectedFolder() {
        Uri eDespatchPdfPath;
        if (!hasGrantedUri()) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivityForResult(intent, 40);
            return;
        }
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            eDespatchPdfPath = sharedPreferencesHelper.getEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            eDespatchPdfPath = sharedPreferencesHelper2.getSalesPdfPath();
        }
        copyPdfFromCacheToSelectedFolder(eDespatchPdfPath);
    }
    private void copyPdfFromCacheToSelectedFolder(Uri uri) {
        PdfOperation pdfOperation = null;
        String str;
        try {
            try {
                pdfOperation = this.selectedSalesItemPdfOperation;
            } catch (Exception e3) {
                Log.e("SalesListFragment", "copyPdfFromCacheToSelectedFolder", e3);
            }
            if (pdfOperation != PdfOperation.EDocumentPdf && pdfOperation != PdfOperation.EDocumentDraftPdf) {
                str = this.cacheSalesPdfFilePath;
                Intrinsics.checkNotNull(str);
                File file = new File(str);
                int length = (int) file.length();
                byte[] bArr = new byte[length];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedInputStream.read(bArr, 0, length);
                bufferedInputStream.close();
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                Intrinsics.checkNotNull(uri);
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(activity, uri);
                Intrinsics.checkNotNull(fromTreeUri);
                DocumentFile createFile = fromTreeUri.createFile("application/pdf", file.getName());
                FragmentActivity activity2 = getActivity();
                Intrinsics.checkNotNull(activity2);
                ContentResolver contentResolver = activity2.getContentResolver();
                Intrinsics.checkNotNull(createFile);
                OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                Intrinsics.checkNotNull(openOutputStream);
                openOutputStream.write(bArr);
                openOutputStream.close();
                file.delete();
                Context context = ContextUtils.getmContext();
                Uri uri2 = createFile.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                Toast.makeText(context, getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
                this.cacheReportPdfFilePath = "";
                this.cacheSalesPdfFilePath = "";
            }
            str = this.cacheReportPdfFilePath;
            Intrinsics.checkNotNull(str);
            File file2 = new File(str);
            int length2 = (int) file2.length();
            byte[] bArr2 = new byte[length2];
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
            bufferedInputStream2.read(bArr2, 0, length2);
            bufferedInputStream2.close();
            FragmentActivity activity3 = getActivity();
            Intrinsics.checkNotNull(activity3);
            Intrinsics.checkNotNull(uri);
            DocumentFile fromTreeUri2 = DocumentFile.fromTreeUri(activity3, uri);
            Intrinsics.checkNotNull(fromTreeUri2);
            DocumentFile createFile2 = fromTreeUri2.createFile("application/pdf", file2.getName());
            FragmentActivity activity22 = getActivity();
            Intrinsics.checkNotNull(activity22);
            ContentResolver contentResolver2 = activity22.getContentResolver();
            Intrinsics.checkNotNull(createFile2);
            OutputStream openOutputStream2 = contentResolver2.openOutputStream(createFile2.getUri());
            Intrinsics.checkNotNull(openOutputStream2);
            openOutputStream2.write(bArr2);
            openOutputStream2.close();
            file2.delete();
            Context context2 = ContextUtils.getmContext();
            Uri uri22 = createFile2.getUri();
            Intrinsics.checkNotNullExpressionValue(uri22, "getUri(...)");
            Toast.makeText(context2, getPdfDownloadPath(uri22), Toast.LENGTH_SHORT).show();
            this.cacheReportPdfFilePath = "";
            this.cacheSalesPdfFilePath = "";
        } catch (Throwable th) {
            this.cacheReportPdfFilePath = "";
            this.cacheSalesPdfFilePath = "";
            try {
                throw th;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getPdfDownloadPath(Uri uri) {
        String str;
        try {
            str = FilePath.getPath(getActivity(), uri);
        } catch (Exception e2) {
            Log.e("SalesListFragment", "getPdfDownloadPath", e2);
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNull(str);
            return str;
        }
        String string = getString(R.string.str_pdf_report_saved);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private boolean hasGrantedUri() {
        Uri eDespatchPdfPath;
        PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
        if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            eDespatchPdfPath = sharedPreferencesHelper.getEDespatchPdfPath();
        } else {
            SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper2);
            eDespatchPdfPath = sharedPreferencesHelper2.getSalesPdfPath();
        }
        if (eDespatchPdfPath == null) {
            return false;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        List<UriPermission> persistedUriPermissions = activity.getContentResolver().getPersistedUriPermissions();
        Intrinsics.checkNotNullExpressionValue(persistedUriPermissions, "getPersistedUriPermissions(...)");
        Iterator<UriPermission> it = persistedUriPermissions.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getUri().toString(), eDespatchPdfPath.toString())) {
                return true;
            }
        }
        return false;
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        String str;
        if (i3 != -1) {
            Toast.makeText(getContext(), getString(R.string.str_report_must_be_designed), Toast.LENGTH_LONG).show();
            return;
        }
        if (i2 == 40) {
            if (intent != null) {
                int flags = intent.getFlags() & 3;
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                ContentResolver contentResolver = activity.getContentResolver();
                Uri data = intent.getData();
                Intrinsics.checkNotNull(data);
                contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                PdfOperation pdfOperation = this.selectedSalesItemPdfOperation;
                if (pdfOperation == PdfOperation.EDocumentPdf || pdfOperation == PdfOperation.EDocumentDraftPdf) {
                    SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
                    Intrinsics.checkNotNull(sharedPreferencesHelper);
                    Uri data2 = intent.getData();
                    Intrinsics.checkNotNull(data2);
                    sharedPreferencesHelper.saveEDespatchPdfPath(data2);
                } else {
                    SharedPreferencesHelper sharedPreferencesHelper2 = this.mSharedPreferencesHelper;
                    Intrinsics.checkNotNull(sharedPreferencesHelper2);
                    Uri data3 = intent.getData();
                    Intrinsics.checkNotNull(data3);
                    sharedPreferencesHelper2.saveSalesPdfPath(data3);
                }
                copyPdfFromCacheToSelectedFolder(intent.getData());
                return;
            }
            return;
        }
        if (i2 == 41) {
            try {
                PdfOperation pdfOperation2 = this.selectedSalesItemPdfOperation;
                if (pdfOperation2 != PdfOperation.EDocumentPdf && pdfOperation2 != PdfOperation.EDocumentDraftPdf) {
                    str = this.cacheSalesPdfFilePath;
                    Intrinsics.checkNotNull(str);
                    new File(str).delete();
                    this.cacheReportPdfFilePath = "";
                    this.cacheSalesPdfFilePath = "";
                    return;
                }
                str = this.cacheReportPdfFilePath;
                Intrinsics.checkNotNull(str);
                new File(str).delete();
                this.cacheReportPdfFilePath = "";
                this.cacheSalesPdfFilePath = "";
                return;
            } catch (Exception e2) {
                Log.e("SalesListFragment", "deleteCache", e2);
                return;
            }
        }
        if (i2 != 999) {
            return;
        }
        Intrinsics.checkNotNull(intent);
        this.reportItem = intent.getParcelableExtra(String.valueOf(999));
        AlertDialogBuilder<?> mAlertDialogBuilder = getMAlertDialogBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.trimIndent("\n                    " + getString(R.string.pref_default_printer) + ": \n                    \n                    "));
        sb.append(Preferences.getDefaultPrinter(getContext()));
        sb.append("\n\n");
        sb.append(getString(R.string.str_report_designs));
        sb.append(": \n");
        ReportItem reportItem = this.reportItem;
        Intrinsics.checkNotNull(reportItem);
        sb.append(reportItem.description);
        mAlertDialogBuilder.setMessage(sb.toString()).setPositiveButton(R.string.str_print, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                SalesListFragment.onActivityResultlambda12(SalesListFragment.this, dialogInterface, i4);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.sales.view.list.SalesListFragmentExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    
    public static void onActivityResultlambda12(SalesListFragment this0, DialogInterface dialogInterface, int i2) {
        String valueOf;
        Intrinsics.checkNotNullParameter(this0, "this0");
        ArrayList<PrintParameters> arrayList = new ArrayList<>();
        PrintParameters printParameters = new PrintParameters(null, null, 3, null);
        ErpType erpType = this0.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        printParameters.setKey(erpType == erpType2 ? "Fisno" : "Id");
        if (this0.viewModel.erpType() == erpType2) {
            SalesShort salesShort = this0.selectedSalesItem;
            Intrinsics.checkNotNull(salesShort);
            valueOf = String.valueOf(salesShort.getFicheId());
        } else {
            SalesShort salesShort2 = this0.selectedSalesItem;
            Intrinsics.checkNotNull(salesShort2);
            valueOf = String.valueOf(salesShort2.getLogicalRef());
        }
        printParameters.setValue(valueOf);
        arrayList.add(printParameters);
        PrintItem printItem = new PrintItem(0, null, 0, null, null, 31, null);
        ReportItem reportItem = this0.reportItem;
        Intrinsics.checkNotNull(reportItem);
        printItem.setReportId(reportItem.getId());
        printItem.setPrinterName(Preferences.getDefaultPrinter(this0.getContext()));
        printItem.setNumberOfCopies(1);
        printItem.setCulture(new SharedPreferencesHelper().getApplicationLanguage());
        printItem.setParameters(arrayList);
        this0.print(printItem);
        dialogInterface.dismiss();
    }

    private boolean createService(String str) {
        if (TextUtils.isEmpty(Preferences.getPrinterServiceAddress(getContext())) || str.length() == 0) {
            return false;
        }
        try {
            Call.Factory providePrintersPublicCallFactory = new CommunicationModule(null, null).providePrintersPublicCallFactory();
            Intrinsics.checkNotNullExpressionValue(providePrintersPublicCallFactory, "providePrintersPublicCallFactory(...)");
            this.mPrintersApi = new PrinterPublicFactory.Impl(providePrintersPublicCallFactory).rxEnabled(true).create(str, PrinterServiceApi.class);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"CheckResult"})
    private void print(PrintItem printItem) {
        if (this.reportItem == null) {
            Toast.makeText(getContext(), getString(R.string.str_report_must_be_designed), Toast.LENGTH_LONG).show();
        }
        String printerServiceAddress = Preferences.getPrinterServiceAddress(getContext());
        Intrinsics.checkNotNullExpressionValue(printerServiceAddress, "getPrinterServiceAddress(...)");
        if (createService(printerServiceAddress)) {
            ProgressDialogBuilder<?> mProgressDialogBuilder = getMProgressDialogBuilder();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_please_wait)).show();
            PrinterServiceApi printerServiceApi = this.mPrintersApi;
            Intrinsics.checkNotNull(printerServiceApi);
            Single<PrintResponse> print = printerServiceApi.print(printItem);
            final SalesListFragmentprint1 salesListFragmentprint1 = new SalesListFragmentprint1() {
                public Unit invoke(Object th) {
                    invoke2((Throwable) th);
                    return Unit.INSTANCE;
                }

                public void invoke2(Throwable th) {
                    String message = th.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    Log.d("mPrintersApi.print()", message);
                }
            };
            Single subscribeOn = print.doOnError(new Consumer() {  
                public void accept(Object obj) {
                    SalesListFragment.printlambda14( this, obj);
                }

                @Override
                public Object invoke(Object obj) {

                    return obj;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            final SalesListFragmentprint2 salesListFragmentprint2 = new SalesListFragmentprint2(this);
            Consumer<? super PrintResponse> consumer = new Consumer() {
                public void accept(Object obj) {
                    SalesListFragment.printlambda15( this, obj);
                }

                @Override
                public Object invoke(Object obj) {

                    return obj;
                }
            };
            final SalesListFragmentprint3 salesListFragmentprint3 = new SalesListFragmentprint3(this);
            subscribeOn.subscribe(consumer, new Consumer() {
                public void accept(Object obj) {
                    SalesListFragment.printlambda16( this, obj);
                }

                @Override
                public Object invoke(Object obj) {

                    return obj;
                }
            });
            return;
        }
        Toast.makeText(getContext(), getString(R.string.str_could_not_get_designs), Toast.LENGTH_LONG).show();
    } 
    public static void printlambda14(Consumer tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    } 
    public static void printlambda15(Consumer tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
 
    public static void printlambda16(Consumer tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    } 
    public void handleResults(PrintResponse printResponse) {
        getMProgressDialogBuilder().dismiss();
        if (printResponse.isResult() && printResponse.isSuccess()) {
            Toast.makeText(getContext(), R.string.str_document_printed_success, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.str_document_failed_to_print), Toast.LENGTH_LONG).show();
        }
    }
 
    public void handleError(Throwable th) {
        ResponseBody errorBody;
        getMProgressDialogBuilder().dismiss();
        if (th instanceof SocketTimeoutException) {
            Toast.makeText(getContext(), R.string.exp_28_socket_timeout, Toast.LENGTH_LONG).show();
            return;
        }
        if (th instanceof HttpException httpException) {
            if (httpException.response() != null) {
                Response<?> response = httpException.response();
                if ((response != null ? response.errorBody() : null) != null) {
                    Gson gson = new Gson();
                    Response<?> response2 = httpException.response();
                    Object fromJson = gson.fromJson((response2 == null || (errorBody = response2.errorBody()) == null) ?
                            null : errorBody.charStream(), (Class<Object>) PrintResponse.class);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
                    Context context = getContext();
                    ArrayList<ResponseError> errors = ((PrintResponse) fromJson).getErrors();
                    ResponseError responseError = errors != null ? errors.get(0) : null;
                    Intrinsics.checkNotNull(responseError);
                    Toast.makeText(context, responseError.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        Toast.makeText(getContext(), th.getMessage(), Toast.LENGTH_LONG).show();
    } 
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private class MyResponseListener implements ResponseListener<Object> {
        public void onResponse(@Nullable @SuppressLint({"KotlinNullnessAnnotation"}) @UnknownNullability PrintSlipModel obj) {
            SalesListFragment salesListFragment = SalesListFragment.this;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            salesListFragment.setMAutoVisitId(((Integer) obj).intValue());
            if (SalesListFragment.this.getMAutoVisitId() > 0) {
                SalesListFragment.this.checkAutoTimeAndTimeZoneEnabledAndShowDialog();
            }
        }

        @Override
        public void onFailure(Throwable throwable) {
            
        }

        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            Toast.makeText(SalesListFragment.this.getContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
