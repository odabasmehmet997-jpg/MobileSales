package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.base.BaseCommunication;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.Tiger;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ExtractReportStartDate;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.CreatePdf;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.FilePath;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.SharedPreferencesHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.model.ActivePeriod;
import com.proje.mobilesales.features.reports.adapter.ReportExtractAdapter;
import com.proje.mobilesales.features.reports.model.enums.ReportCurrencyUnit;
import com.proje.mobilesales.features.reports.model.enums.ReportTransactionType;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

public final class ReportExtractActivity extends BaseReportWcfActivity {
    private static final int CREATE_REQUEST_CODE = 40;
    public static final int DATE_DIALOG_ID1 = 998;
    public static final int DATE_DIALOG_ID2 = 999;
    private static final int OPEN_REQUEST_CODE = 41;
    private static final int PICK_DOCUMENT_REQUEST_CODE = 43;
    private static final String STATE_REPORT_CURRENCY_UNIT = "state:reportCurrencyUnit";
    private static final String TAG = "ExtractActivity";
    private ReportExtractAdapter adapter;
    private int[] arrVisibility;
    private int blackColor;
    private final Bundle bundle;
    private String cacheReportPdfFilePath;
    private String cacheReportPdfFolderPath;
    private String createDateEnd;
    private String createDateStart;
    private ProgressDialog dialog;
    private AppCompatImageButton imgList;
    private boolean isFirst;
    private LinearLayout linearFilterDiv;
    private LinearLayout linearProgress;
    private LinearLayout lnBalance;
    private View lnClearSavePathPdf;
    private LinearLayout lnDebitCredit;
    private View lnDownloadPdf;
    private View lnSharePdf;
    private View lnShowPdf;
    private ListView lvList;
    private BottomSheetDialog mBottomSheetDialog;
    private CustomerOperation mCustomerOperation;
    private ReportCurrencyUnit mReportCurrencyUnit;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private AppCompatSpinner mSpnReportCurrencyType;
    private Menu menu;
    private int redColor;
    private REPORTXML reportXml;
    private ScreenControl screenControl;
    private AppCompatTextView tvBalance;
    private AppCompatTextView tvDescription;
    private AppCompatTextView tvDueDate;
    private AppCompatTextView txtFicheTotalBalance;
    private AppCompatTextView txtFicheTotalCredit;
    private AppCompatTextView txtFicheTotalDebit;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_CUSTOMER_REF = ReportExtractActivity.class.getName() + ".CUSTOMER_REF";
    private static final String STATE_CUSTOMER_OPERATION = ReportExtractActivity.class.getName() + ".CUSTOMER_OPERATION";
    private final String paramNo = "4";
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda4
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda6;
            itemLongClickListenerlambda6 = itemLongClickListenerlambda6(ReportExtractActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda6;
        }
    };
    private final AdapterView.OnItemLongClickListener netsisItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda5
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean netsisItemLongClickListenerlambda7;
            netsisItemLongClickListenerlambda7 = netsisItemLongClickListenerlambda7(ReportExtractActivity.this, adapterView, view, i2, j2);
            return netsisItemLongClickListenerlambda7;
        }
    };

    @SuppressLint("SetTextI18n")
    private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda6
        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
            datePickerListenerlambda8(ReportExtractActivity.this, datePicker, i2, i3, i4);
        }
    };

    @SuppressLint("SetTextI18n")
    private final DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda7
        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(final DatePicker datePicker, final int i2, final int i3, final int i4) {
            datePickerListener2lambda9(ReportExtractActivity.this, datePicker, i2, i3, i4);
        }
    };
    private final View.OnClickListener clickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda8
        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            clickListenerlambda10(ReportExtractActivity.this, view);
        }
    };
    private final AdapterView.OnItemClickListener netsisItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda9
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            netsisItemClickListenerlambda15(ReportExtractActivity.this, adapterView, view, i2, j2);
        }
    };
    private final AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda10
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            itemClickListenerlambda16(ReportExtractActivity.this, adapterView, view, i2, j2);
        }
    };

    private int getTrCodeDescId(final int i2) {
        if (12 == i2) {
            return R.string.str_special_operation;
        }
        if (14 == i2) {
            return R.string.str_opening_slip;
        }
        if (56 == i2) {
            return R.string.str_collections;
        }
        if (81 == i2) {
            return R.string.str_prepayment_order;
        }
        if (99 == i2) {
            return R.string.str_invoice_amount_account;
        }
        if (999 == i2) {
            return R.string.str_report_extract_transfer;
        }
        if (20 == i2) {
            return R.string.str_incoming_remittance;
        }
        if (21 == i2) {
            return R.string.str_sent_remittance;
        }
        if (24 == i2) {
            return R.string.str_foreign_exchange_certificate_purchasing;
        }
        if (25 == i2) {
            return R.string.str_foreign_exchange_certificate_selling;
        }
        switch (i2) {
            case 1:
                return R.string.str_cash_collection;
            case 2:
                return R.string.str_cash_payment;
            case 3:
                return R.string.str_debt_note;
            case 4:
                return R.string.str_credit_note;
            case 5:
                return R.string.str_remittance_operation;
            case 6:
                return R.string.str_exchange_rate_difference_operation;
            default:
                switch (i2) {
                    case 31:
                        return R.string.str_goods_purchase_invoice;
                    case 32:
                        return R.string.str_retail_sales_return_invoice;
                    case 33:
                        return R.string.str_wholesale_sales_return_invoice;
                    case 34:
                        return R.string.str_service_purchase_return_invoice;
                    case 35:
                        return R.string.str_purchase_pro_forma_invoice;
                    case 36:
                        return R.string.str_buying_return_receipt;
                    case 37:
                        return R.string.str_retail_sales_invoice;
                    case 38:
                        return R.string.str_wholesale_sales_invoice;
                    case 39:
                        return R.string.str_given_service_sales_invoice;
                    case 40:
                        return R.string.str_given_pro_forma_sales_invoice;
                    case 41:
                        return R.string.str_due_date_diff_invoice_issued;
                    case 42:
                        return R.string.str_due_date_diff_invoice_received;
                    case 43:
                        return R.string.str_purchase_price_difference_invoice;
                    case 44:
                    case 45:
                        return R.string.str_sales_price_difference_invoice;
                    case 46:
                        return R.string.str_received_self_employment_receipt;
                    default:
                        switch (i2) {
                            case 61:
                                return R.string.str_cheque_entry;
                            case 62:
                                return R.string.str_deed_entry;
                            case 63:
                                return R.string.str_check_issued_account;
                            case 64:
                                return R.string.str_promissory_note_issued;
                            default:
                                switch (i2) {
                                    case 70:
                                        return R.string.str_credit_card_fiche;
                                    case 71:
                                        return R.string.str_credit_card_return_slip;
                                    case 72:
                                        return R.string.str_firm_credit_card_fiche;
                                    case 73:
                                        return R.string.str_firm_credit_card_return_fiche;
                                    default:
                                        return -1;
                                }
                        }
                }
        }
    }

    
    public static void onCreatelambda4lambda3(final DialogInterface dialogInterface, final int i2) {
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint("InflateParams")
    public void onCreate(final Bundle bundle) {
        final ReportCurrencyUnit reportCurrencyUnit;
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.NETSIS) {
            this.setContentView(R.layout.account_extract);
        } else {
            this.setContentView(R.layout.account_extract_tiger);
        }
        this.setToolbar();
        if (null != bundle) {
            this.setClRef(bundle.getInt(ReportExtractActivity.STATE_CUSTOMER_REF));
            mCustomerOperation = bundle.getParcelable(ReportExtractActivity.STATE_CUSTOMER_OPERATION);
        } else {
            final Bundle extras = this.getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            mCustomerOperation = extras.getParcelable(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE);
            final Bundle extras2 = this.getIntent().getExtras();
            Intrinsics.checkNotNull(extras2);
            this.setClRef(extras2.getInt(IntentExtraName.EXTRA_CUSTOMER_REF));
        }
        final Bundle extras3 = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras3);
        this.setType((ProcessType) extras3.get("TYPE"));
        this.setClRef(extras3.getInt("CLREF"));
        this.getExtras();
        this.setClRef(customerRef);
        if (0 == getClRef()) {
            this.setType((ProcessType) extras3.get("ProcessType"));
            final ProcessType type = this.getType();
            Intrinsics.checkNotNull(type);
            this.setTitle(this.getString(type.resId));
        } else {
            this.setType(this.CustomerToType(customerOperation.getMenuType()));
            this.setTitle(customerOperation.getOperationName());
        }
        this.initialize();
        if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.TIGER) {
            try {
                final BaseErp<?> baseErp = this.getReportWcfQueriesViewModel().getBaseErp();
                Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Tiger");
                final ActivePeriod activePeriod = ((Tiger) baseErp).getActivePeriod();
                createDateStart = activePeriod.getBeginDate();
                createDateEnd = activePeriod.getEndDate();
                this.setCurrentDateOnViewByActivePeriod();
            } catch (final Exception e2) {
                this.setCurrentDateOnView();
                Toast.makeText(this, ContextUtils.getStringResource(R.string.exp_active_period), Toast.LENGTH_SHORT).show();
                Log.e("ReportExtractActivity", "getActivePeriod", e2);
            }
        } else {
            if (ExtractReportStartDate.Companion.isFirstDayOfYear(this.getReportWcfQueriesViewModel().getBaseErp())) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(6, 1);
                final String calendarDateDMY = DateAndTimeUtils.calendarDateDMY(calendar);
                Intrinsics.checkNotNullExpressionValue(calendarDateDMY, "calendarDateDMY(...)");
                strDate1 = calendarDateDMY;
            }
            this.setCurrentDateOnView();
        }
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            if (null != reportxml.reportLines) {
                final REPORTXML reportxml2 = reportXml;
                Intrinsics.checkNotNull(reportxml2);
                final List<REPORTLINE> reportLines = reportxml2.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                if (!reportLines.isEmpty()) {
                    this.setExtractList();
                }
            }
        }
        if (null != bundle) {
            reportCurrencyUnit = ReportCurrencyUnit.Companion.fromReportCurrencyUnit(bundle.getInt(ReportExtractActivity.STATE_REPORT_CURRENCY_UNIT, 0));
        } else {
            reportCurrencyUnit = ReportCurrencyUnit.LOCAL;
        }
        mReportCurrencyUnit = reportCurrencyUnit;
        this.setSpin();
        this.setColumnVisibility();
        this.refresh();
        final ScreenControl screenControl = this.screenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.isFullScreen = true;
        final Window window = this.getWindow();
        window.clearFlags(2048);
        window.clearFlags(2048);
        window.addFlags(1024);
        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
        cacheReportPdfFolderPath = this.getCacheDir().getAbsolutePath() + "/reportPdf";
        this.clearExistingReports();
        final View inflate = this.getLayoutInflater().inflate(R.layout.fragment_pdf_bottom_sheet_dialog, null);
        lnSharePdf = inflate.findViewById(R.id.ln_share_pdf);
        lnDownloadPdf = inflate.findViewById(R.id.ln_download_pdf);
        lnShowPdf = inflate.findViewById(R.id.ln_show_pdf);
        lnClearSavePathPdf = inflate.findViewById(R.id.ln_clear_pdf_path);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.setContentView(inflate);
        final View view = lnShowPdf;
        Intrinsics.checkNotNull(view);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(final View view2) {
                onCreatelambda0(ReportExtractActivity.this, view2);
            }
        });
        final View view2 = lnSharePdf;
        Intrinsics.checkNotNull(view2);
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view3) {
                onCreatelambda1(ReportExtractActivity.this, view3);
            }
        });
        final View view3 = lnDownloadPdf;
        Intrinsics.checkNotNull(view3);
        view3.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view4) {
                onCreatelambda2(ReportExtractActivity.this, view4);
            }
        });
        final View view4 = lnClearSavePathPdf;
        Intrinsics.checkNotNull(view4);
        view4.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view5) {
                onCreatelambda4(ReportExtractActivity.this, view5);
            }
        });
    }

    
    public static void onCreatelambda0(final ReportExtractActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.exportPdf(PdfExportOption.Preview);
        final BottomSheetDialog bottomSheetDialog = this0.mBottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }

    
    public static void onCreatelambda1(final ReportExtractActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.exportPdf(PdfExportOption.Share);
        final BottomSheetDialog bottomSheetDialog = this0.mBottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }

    
    public static void onCreatelambda2(final ReportExtractActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.exportPdf(PdfExportOption.Download);
        final BottomSheetDialog bottomSheetDialog = this0.mBottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }

    
    public static void onCreatelambda4(final ReportExtractActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final SharedPreferencesHelper sharedPreferencesHelper = this0.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        sharedPreferencesHelper.removeReportPdfPath();
        final BottomSheetDialog bottomSheetDialog = this0.mBottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
        this0.mBaseAlertDialogBuilder.setMessage(this0.getString(R.string.str_pdf_save_path_cleared)).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivityExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onCreatelambda4lambda3(dialogInterface, i2);
            }
        }).create().show();
    }

    private void clearExistingReports() {
        try {
            final File file = new File(cacheReportPdfFolderPath);
            if (file.exists()) {
                final File[] listFiles = file.listFiles();
                Intrinsics.checkNotNull(listFiles);
                for (final File file2 : listFiles) {
                    file2.delete();
                }
                return;
            }
            file.mkdir();
        } catch (final Exception e2) {
            Log.e("ReportExtractActivity", "clearExistingReports", e2);
        }
    }
    private String generatePdfName() {
        final String str;
        final String str2 =  this.getTitle().toString() + '_' + DateAndTimeUtils.nowDate("dd.MM.yyyy_HHmmss");
        if (0 >= this.customerRef) {
            str = "";
        } else {
            final ClCard customerInfo = this.getReportWcfQueriesViewModel().getBaseErp().getCustomerInfo(this.customerRef);
            if (null == customerInfo) {
                return str2;
            }
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            str = String.format("(%s)%s_%s", Arrays.copyOf(new Object[]{customerInfo.getCode(), StringUtils.removeSlashFromWord(customerInfo.getDefinition()), str2}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (127 > str.length()) {
            return str;
        }
        final String substring = str.substring(0, 126);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    private void exportPdf(PdfExportOption pdfExportOption) {
        final String generateHTML = this.generateHTML();
        final String generatePdfName = this.generatePdfName();
        if (TextUtils.isEmpty(generateHTML)) {
            Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
            return;
        }
        cacheReportPdfFilePath = "";
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
        try {
            final CreatePdf contentBaseUrl = new CreatePdf(this).setPdfName(generatePdfName).openPrintDialog(false).setContentBaseUrl(null);
            final PrintAttributes.MediaSize ISO_A4 = PrintAttributes.MediaSize.ISO_A4;
            Intrinsics.checkNotNullExpressionValue(ISO_A4, "ISO_A4");
            final CreatePdf content = contentBaseUrl.setPageSize(ISO_A4).setContent(generateHTML);
            final String str = cacheReportPdfFolderPath;
            Intrinsics.checkNotNull(str);
            content.setFilePath(str).setCallbackListener(new CreatePdf.PdfCallbackListener() {  
                public enum WhenMappings {
                    ;
                    public static final  int[] EnumSwitchMapping0;

                    static {
                        final int[] iArr = new int[PdfExportOption.values().length];
                        try {
                            iArr[PdfExportOption.Share.ordinal()] = 1;
                        } catch (final NoSuchFieldError unused) {
                        }
                        try {
                            iArr[PdfExportOption.Preview.ordinal()] = 2;
                        } catch (final NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[PdfExportOption.Download.ordinal()] = 3;
                        } catch (final NoSuchFieldError unused3) {
                        }
                        EnumSwitchMapping0 = iArr;
                    }
                }
                public void onFailure(final String s) {
                    final ProgressDialogBuilder progressDialogBuilder;
                    Intrinsics.checkNotNullParameter(s, "s");
                    progressDialogBuilder = ReportExtractActivity.this.mProgressDialogBuilder;
                    progressDialogBuilder.dismiss();
                    final ReportExtractActivity reportExtractActivity = ReportExtractActivity.this;
                    Toast.makeText(reportExtractActivity, reportExtractActivity.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                } 
                public void onSuccess(final String s) {
                    final ProgressDialogBuilder progressDialogBuilder;
                    Intrinsics.checkNotNullParameter(s, "s");
                    progressDialogBuilder = ReportExtractActivity.this.mProgressDialogBuilder;
                    progressDialogBuilder.dismiss();
                    cacheReportPdfFilePath = s;
                    final Uri uriForFile = FileProvider.getUriForFile(ReportExtractActivity.this, "com.proje.mobilesales.fileprovider", new File(s));
                    final PdfExportOption pdfExportOption2 = pdfExportOption;
                    final int i2 = null == pdfExportOption2 ? -1 : WhenMappings.EnumSwitchMapping0[pdfExportOption2.ordinal()];
                    if (1 == i2) {
                        final Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("application/pdf");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(uriForFile, "application/pdf");
                        intent.putExtra("android.intent.extra.STREAM", uriForFile);
                        final ReportExtractActivity reportExtractActivity = ReportExtractActivity.this;
                        reportExtractActivity.startActivity(Intent.createChooser(intent, reportExtractActivity.getString(R.string.menu_pdf_share)));
                        return;
                    }
                    if (2 != i2) {
                        if (3 != i2) {
                            return;
                        }
                        createPdfToSelectedFolder();
                        return;
                    }
                    try {
                        final Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW").setPackage(/* TODO: provide the application ID. For example: */ getPackageName()).setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        Intent intent = intent2.setDataAndType(uriForFile, "application/pdf");
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                    } catch (final ActivityNotFoundException e2) {
                        Toast.makeText(ReportExtractActivity.this, R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
                        Log.e("ReportExtractActivity", "Activity not found for pdf", e2);
                    }
                }
            }).create();
        } catch (final Exception e2) {
            Log.e("ReportExtractActivity", "exportPdf", e2);
            mProgressDialogBuilder.dismiss();
            Toast.makeText(this, this.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void initialize() {
        final AppCompatTextView appCompatTextView;
        linearDate1 = this.findViewById(R.id.linearDate1);
        linearDate2 = this.findViewById(R.id.linearDate2);
        lnBalance = this.findViewById(R.id.lnFicheTotalBalance);
        lnDebitCredit = this.findViewById(R.id.lnDebitCredit);
        imgList = this.findViewById(R.id.imgList);
        tvDate1 = this.findViewById(R.id.tvDate1);
        tvDate2 = this.findViewById(R.id.tvDate2);
        lvList = this.findViewById(R.id.lvList);
        tvDescription = this.findViewById(R.id.tvDescription);
        tvBalance = this.findViewById(R.id.tvBalance);
        tvDueDate = this.findViewById(R.id.tvDueDate);
        if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
            final AppCompatTextView appCompatTextView2 = tvBalance;
            if (null != appCompatTextView2) {
                appCompatTextView2.setVisibility(View.GONE);
            }
            if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.NETSIS) {
                final AppCompatTextView appCompatTextView3 = tvDueDate;
                final ViewGroup.LayoutParams layoutParams = null != appCompatTextView3 ? appCompatTextView3.getLayoutParams() : null;
                final LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? layoutParams : null;
                if (null != layoutParams2) {
                    layoutParams2.weight = 7.0f;
                }
                final AppCompatTextView appCompatTextView4 = tvDueDate;
                if (null != appCompatTextView4) {
                    appCompatTextView4.setLayoutParams(layoutParams2);
                }
            }
        }
        linearFilterDiv = this.findViewById(R.id.linearFilterDiv);
        linearProgress = this.findViewById(R.id.linearProgress);
        txtFicheTotalCredit = this.findViewById(R.id.txt_ficheTotalCredit);
        txtFicheTotalDebit = this.findViewById(R.id.txt_ficheTotalDebit);
        txtFicheTotalBalance = this.findViewById(R.id.txt_ficheTotalBalance);
        mSpnReportCurrencyType = this.findViewById(R.id.spReportCurrencyType);
        final ErpType erpType = this.getReportWcfQueriesViewModel().getBaseErp().getErpType();
        final ErpType erpType2 = ErpType.NETSIS;
        if (erpType == erpType2 && null != (appCompatTextView = this.tvDescription)) {
            appCompatTextView.setText(R.string.str_extract_report_fiche_description_procdate);
        }
        final AppCompatTextView appCompatTextView5 = tvDescription;
        if (null != appCompatTextView5) {
            appCompatTextView5.setOnClickListener(clickListener);
        }
        final AppCompatTextView appCompatTextView6 = tvBalance;
        if (null != appCompatTextView6) {
            appCompatTextView6.setOnClickListener(clickListener);
        }
        final LinearLayout linearLayout = linearDate1;
        if (null != linearLayout) {
            linearLayout.setOnClickListener(clickListener);
        }
        final LinearLayout linearLayout2 = linearDate2;
        if (null != linearLayout2) {
            linearLayout2.setOnClickListener(clickListener);
        }
        final AppCompatImageButton appCompatImageButton = imgList;
        if (null != appCompatImageButton) {
            appCompatImageButton.setOnClickListener(clickListener);
        }
        screenControl = new ScreenControl(this);
        this.setArrVisibility();
        final ListView listView = lvList;
        if (null != listView) {
            listView.setOnItemClickListener(this.getReportWcfQueriesViewModel().getBaseErp().getErpType() != erpType2 ? itemClickListener : netsisItemClickListener);
        }
        final ListView listView2 = lvList;
        if (null != listView2) {
            listView2.setOnItemLongClickListener(this.getReportWcfQueriesViewModel().getBaseErp().getErpType() != erpType2 ? itemLongClickListener : netsisItemLongClickListener);
        }
        this.setClientForTiger(new ServicesClientForTiger(this));
        final Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        redColor = ContextCompat.getColor(context, R.color.colorPrimary);
        final Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        blackColor = ContextCompat.getColor(context2, R.color.black);
    }

    private void setArrVisibility() {
        List emptyList;
        arrVisibility = new int[]{1, 1};
        final String singleField = this.getReportWcfQueriesViewModel().getSqlHelper().getSingleField("REPORTPARAM", "PARAMVALUE", "PARAMNO=" + paramNo, false);
        if (Intrinsics.areEqual(singleField, "")) {
            return;
        }
        Intrinsics.checkNotNull(singleField);
        final List<String> split = new Regex(",").split(singleField, 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        final int length = strArr.length;
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        if (length == iArr.length) {
            final int length2 = strArr.length;
            for (int i2 = 0; i2 < length2; i2++) {
                int[] iArr2 = arrVisibility;
                if (null == iArr2) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    iArr2 = null;
                }
                iArr2[i2] = StringUtils.convertStringToInt(strArr[i2]);
            }
        }
    }

    private void setSpin() {
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, this.getResources().getStringArray(R.array.array_report_currency_unit));
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner = mSpnReportCurrencyType;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(arrayAdapter);
        final AppCompatSpinner appCompatSpinner2 = mSpnReportCurrencyType;
        Intrinsics.checkNotNull(appCompatSpinner2);
        appCompatSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractActivitysetSpin1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i2, final long j2) {
                Intrinsics.checkNotNullParameter(view, "view");
                final ReportExtractActivity reportExtractActivity = ReportExtractActivity.this;
                reportExtractActivity.mReportCurrencyUnit = ReportCurrencyUnit.Companion.fromReportCurrencyUnit(reportExtractActivity.getResources().getIntArray(R.array.array_report_currency_unit_values)[i2]);
                refresh();
            }
        });
        final AppCompatSpinner appCompatSpinner3 = mSpnReportCurrencyType;
        Intrinsics.checkNotNull(appCompatSpinner3);
        final ReportCurrencyUnit reportCurrencyUnit = mReportCurrencyUnit;
        Intrinsics.checkNotNull(reportCurrencyUnit);
        appCompatSpinner3.setSelection(reportCurrencyUnit.getValue());
    }

    
    public static boolean itemLongClickListenerlambda6(final ReportExtractActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportXml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        this0.showDetail(reportline);
        return true;
    }

    
    public static boolean netsisItemLongClickListenerlambda7(final ReportExtractActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportXml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        this0.showNetsisFicheDetail(reportline);
        return true;
    }

    private void showNetsisFicheDetail(final REPORTLINE reportline) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_extract_column);
        final AppCompatTextView appCompatTextView = dialog.findViewById(R.id.tvProcessDate);
        final AppCompatTextView appCompatTextView2 = dialog.findViewById(R.id.tvFicheNo);
        final AppCompatTextView appCompatTextView3 = dialog.findViewById(R.id.tvFicheType);
        final AppCompatTextView appCompatTextView4 = dialog.findViewById(R.id.tvDebit);
        final AppCompatTextView appCompatTextView5 = dialog.findViewById(R.id.tvCredit);
        final AppCompatTextView appCompatTextView6 = dialog.findViewById(R.id.tvBakiye);
        ((AppCompatTextView) dialog.findViewById(R.id.tvFicheTypeHeader)).setText(R.string.str_explanation);
        appCompatTextView.setText(reportline.DATE_);
        appCompatTextView2.setText(reportline.FICHENO);
        appCompatTextView3.setText(reportline.DESC);
        appCompatTextView4.setText(StringUtils.ffFormat(reportline.DEBIT));
        appCompatTextView5.setText(StringUtils.ffFormat(reportline.CREDIT));
        if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
            appCompatTextView6.setText("-");
        } else {
            final double d2 = reportline.BALANCE;
            if (0.0d > d2) {
                final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                final String format = String.format("%s(B)", Arrays.copyOf(new Object[]{StringUtils.ffFormat((-1) * d2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                appCompatTextView6.setText(format);
            } else if (0.0d < d2) {
                final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                final String format2 = String.format("%s(A)", Arrays.copyOf(new Object[]{StringUtils.ffFormat(d2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                appCompatTextView6.setText(format2);
            } else {
                appCompatTextView6.setText(StringUtils.ffFormat(d2));
            }
        }
        dialog.show();
    }

    private void showDetail(final REPORTLINE reportline) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_extract_column);
        final AppCompatTextView appCompatTextView = dialog.findViewById(R.id.tvProcessDate);
        final AppCompatTextView appCompatTextView2 = dialog.findViewById(R.id.tvFicheNo);
        final AppCompatTextView appCompatTextView3 = dialog.findViewById(R.id.tvFicheType);
        final AppCompatTextView appCompatTextView4 = dialog.findViewById(R.id.tvDebit);
        final AppCompatTextView appCompatTextView5 = dialog.findViewById(R.id.tvCredit);
        final AppCompatTextView appCompatTextView6 = dialog.findViewById(R.id.tvBakiye);
        appCompatTextView.setText(reportline.DATE_);
        appCompatTextView2.setText(reportline.FICHENO);
        appCompatTextView3.setText(this.getResources().getString(this.getTrCodeDescId(reportline.TRCODE)));
        final int i2 = reportline.SIGN;
        if (0 == i2) {
            appCompatTextView4.setText(StringUtils.ffFormat(reportline.AMOUNT));
            appCompatTextView5.setText("");
        } else if (1 == i2) {
            appCompatTextView5.setText(StringUtils.ffFormat(reportline.AMOUNT));
            appCompatTextView4.setText("");
        } else {
            appCompatTextView4.setText(StringUtils.ffFormat(reportline.DEBIT));
            appCompatTextView5.setText(StringUtils.ffFormat(reportline.CREDIT));
        }
        if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
            appCompatTextView6.setText("-");
        } else {
            final double d2 = reportline.BALANCE;
            if (0.0d > d2) {
                final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                final String format = String.format("%s(B)", Arrays.copyOf(new Object[]{StringUtils.ffFormat((-1) * d2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                appCompatTextView6.setText(format);
            } else if (0.0d < d2) {
                final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                final String format2 = String.format("%s(A)", Arrays.copyOf(new Object[]{StringUtils.ffFormat(d2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                appCompatTextView6.setText(format2);
            } else {
                appCompatTextView6.setText(StringUtils.ffFormat(d2));
            }
        }
        dialog.show();
    }

    private void setCurrentDateOnViewByActivePeriod() {
        final Date convertDateSqlStringToDate = DateAndTimeUtils.convertDateSqlStringToDate(createDateStart);
        final Calendar gregorianCalendar = DateAndTimeUtils.getGregorianCalendar(DateAndTimeUtils.convertDateSqlStringToDate(createDateEnd));
        this.setYear(gregorianCalendar.get(1));
        this.setMonth(gregorianCalendar.get(2));
        this.setMonth(this.getMonth() + 1);
        this.setDay(gregorianCalendar.get(5));
        final String fillFormat = StringUtils.fillFormat(4, this.getYear());
        final String fillFormat2 = StringUtils.fillFormat(2, this.getMonth());
        final String fillFormat3 = StringUtils.fillFormat(2, this.getDay());
        if (Intrinsics.areEqual(strDate2, "")) {
            final AppCompatTextView appCompatTextView = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView);
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat3, fillFormat2, fillFormat}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
        } else {
            final AppCompatTextView appCompatTextView2 = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setText(strDate2);
        }
        final Calendar gregorianCalendar2 = DateAndTimeUtils.getGregorianCalendar(convertDateSqlStringToDate);
        this.setYear(gregorianCalendar2.get(1));
        this.setMonth(gregorianCalendar2.get(2));
        this.setMonth(this.getMonth() + 1);
        this.setDay(gregorianCalendar2.get(5));
        final String fillFormat4 = StringUtils.fillFormat(4, this.getYear());
        final String fillFormat5 = StringUtils.fillFormat(2, this.getDay());
        final String fillFormat6 = StringUtils.fillFormat(2, this.getMonth());
        if (Intrinsics.areEqual(strDate1, "")) {
            final AppCompatTextView appCompatTextView3 = tvDate1;
            Intrinsics.checkNotNull(appCompatTextView3);
            final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
            final String format2 = String.format("%s.%s.%s", Arrays.copyOf(new Object[]{fillFormat5, fillFormat6, fillFormat4}, 3));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            appCompatTextView3.setText(format2);
            return;
        }
        final AppCompatTextView appCompatTextView4 = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setText(strDate1);
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, android.app.Activity
    protected Dialog onCreateDialog(final int i2) {
        if (998 == i2) {
            return new DatePickerDialog(this, datePickerListener, this.getYear(), this.getMonth(), this.getDay());
        }
        if (999 != i2) {
            return null;
        }
        return new DatePickerDialog(this, datePickerListener2, this.getYear(), this.getMonth(), this.getDay());
    }

    
    public static void datePickerListenerlambda8(final ReportExtractActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.setYear(i2);
        this0.setMonth(i3);
        this0.setDay(i4);
        final int month = this0.getMonth() + 1;
        final String fillFormat = StringUtils.fillFormat(4, this0.getYear());
        final String fillFormat2 = StringUtils.fillFormat(2, month);
        final String fillFormat3 = StringUtils.fillFormat(2, this0.getDay());
        if (this0.isFirst) {
            final AppCompatTextView appCompatTextView = this0.tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setText(fillFormat3 + '.' + fillFormat2 + '.' + fillFormat);
            return;
        }
        final AppCompatTextView appCompatTextView2 = this0.tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(fillFormat3 + '.' + fillFormat2 + '.' + fillFormat);
    }

    
    public static void datePickerListener2lambda9(final ReportExtractActivity this0, final DatePicker datePicker, final int i2, final int i3, final int i4) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.setYear(i2);
        this0.setMonth(i3);
        this0.setDay(i4);
        final int month = this0.getMonth() + 1;
        final String fillFormat = StringUtils.fillFormat(4, this0.getYear());
        final String fillFormat2 = StringUtils.fillFormat(2, month);
        final String fillFormat3 = StringUtils.fillFormat(2, this0.getDay());
        if (this0.isFirst) {
            final AppCompatTextView appCompatTextView = this0.tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setText(fillFormat3 + '.' + fillFormat2 + '.' + fillFormat);
            return;
        }
        final AppCompatTextView appCompatTextView2 = this0.tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(fillFormat3 + '.' + fillFormat2 + '.' + fillFormat);
    }

    
    public static void clickListenerlambda10(final ReportExtractActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        int[] iArr = null;
        switch (view.getId()) {
            case R.id.imgList /* 2131296890 */:
                this0.refresh();
                break;
            case R.id.linearDate1 /* 2131296958 */:
                this0.isFirst = true;
                this0.dateIsFirst = true;
                this0.setDate();
                this0.showDialog(998);
                break;
            case R.id.linearDate2 /* 2131296959 */:
                this0.isFirst = false;
                this0.dateIsFirst = false;
                this0.setDate();
                this0.showDialog(999);
                break;
            case R.id.tvBalance /* 2131297806 */:
                if (this0.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    final AppCompatTextView appCompatTextView = this0.tvBalance;
                    Intrinsics.checkNotNull(appCompatTextView);
                    appCompatTextView.setClickable(false);
                }
                final int[] iArr2 = this0.arrVisibility;
                if (null == iArr2) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr = iArr2;
                }
                iArr[1] = 0;
                this0.setColumnVisibility();
                break;
            case R.id.tvDescription /* 2131297833 */:
                if (this0.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    final AppCompatTextView appCompatTextView2 = this0.tvDescription;
                    Intrinsics.checkNotNull(appCompatTextView2);
                    appCompatTextView2.setClickable(false);
                }
                final int[] iArr3 = this0.arrVisibility;
                if (null == iArr3) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr = iArr3;
                }
                iArr[0] = 0;
                this0.setColumnVisibility();
                break;
        }
    }

    
    public void refresh() {
        List emptyList;
        List emptyList2;
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            if (null != reportxml.reportLines) {
                final REPORTXML reportxml2 = reportXml;
                Intrinsics.checkNotNull(reportxml2);
                reportxml2.reportLines.clear();
                final ReportExtractAdapter reportExtractAdapter = adapter;
                Intrinsics.checkNotNull(reportExtractAdapter);
                reportExtractAdapter.notifyDataSetChanged();
            }
        }
        final AppCompatTextView appCompatTextView = txtFicheTotalDebit;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(this.getString(R.string.empty_text));
        final AppCompatTextView appCompatTextView2 = txtFicheTotalCredit;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(this.getString(R.string.empty_text));
        final AppCompatTextView appCompatTextView3 = txtFicheTotalBalance;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setText(this.getString(R.string.empty_text));
        final AppCompatTextView appCompatTextView4 = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView4);
        final List<String> split = new Regex("\\.").split(appCompatTextView4.getText().toString(), 0);
        if (!split.isEmpty()) {
            final ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (0 != listIterator.previous().length()) {
                    emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        final String[] strArr = (String[]) emptyList.toArray(new String[0]);
        final int convertStringToInt = StringUtils.convertStringToInt(strArr[0]);
        createDateStart = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr[2]), StringUtils.convertStringToInt(strArr[1]), convertStringToInt, 0, 0, 0, 0);
        final AppCompatTextView appCompatTextView5 = tvDate2;
        Intrinsics.checkNotNull(appCompatTextView5);
        final List<String> split2 = new Regex("\\.").split(appCompatTextView5.getText().toString(), 0);
        if (!split2.isEmpty()) {
            final ListIterator<String> listIterator2 = split2.listIterator(split2.size());
            while (listIterator2.hasPrevious()) {
                if (0 != listIterator2.previous().length()) {
                    emptyList2 = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList2 = CollectionsKt.emptyList();
        final String[] strArr2 = (String[]) emptyList2.toArray(new String[0]);
        final int convertStringToInt2 = StringUtils.convertStringToInt(strArr2[0]);
        createDateEnd = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr2[2]), StringUtils.convertStringToInt(strArr2[1]), convertStringToInt2, 23, 59, 59, 0);
        dialog = ProgressDialog.show(this, "", this.getString(R.string.str_please_wait), true);
        if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.GO || this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.TIGER) {
            this.exeRetrieve(this.getClRef());
        } else {
            this.executeReportForNetsis();
        }
    }

    private void executeReportForNetsis() {
        final String code = this.getReportWcfQueriesViewModel().getBaseErp().getUser().getCode();
        final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
        final int clRef = this.getClRef();
        final String str = createDateStart;
        Intrinsics.checkNotNull(str);
        final String str2 = createDateEnd;
        Intrinsics.checkNotNull(str2);
        Intrinsics.checkNotNull(code);
        reportWcfQueriesViewModel.getCustomerExtractReport(clRef, str, str2, code, new ReportExtractResponseListener(this));
    } 
        private record ReportExtractResponseListener(
            WeakReference<ReportExtractActivity> mReportExtractActivity) implements ResponseListener<List<? extends REPORTLINE>> {
            private ReportExtractResponseListener(final ReportExtractActivity mReportExtractActivity) {
                this(new WeakReference<>(mReportExtractActivity));
            } 
            public void onResponse(final List<? extends REPORTLINE> list) {
                if (null != this.mReportExtractActivity.get()) {
                    final ReportExtractActivity reportExtractActivity = mReportExtractActivity.get();
                    Intrinsics.checkNotNull(reportExtractActivity);
                    if (reportExtractActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ReportExtractActivity reportExtractActivity2 = mReportExtractActivity.get();
                    Intrinsics.checkNotNull(reportExtractActivity2);
                    reportExtractActivity2.onReportExtractResponse(list, "");
                }
            } 
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mReportExtractActivity.get()) {
                    final ReportExtractActivity reportExtractActivity = mReportExtractActivity.get();
                    Intrinsics.checkNotNull(reportExtractActivity);
                    if (reportExtractActivity.isActivityDestroyed()) {
                        return;
                    }
                    Log.d(TAG, "onError: " + errorMessage);
                    final ReportExtractActivity reportExtractActivity2 = mReportExtractActivity.get();
                    Intrinsics.checkNotNull(reportExtractActivity2);
                    reportExtractActivity2.onReportExtractResponse(null, errorMessage);
                }
            }
        } 
    public void onReportExtractResponse(final List<? extends REPORTLINE> list, final String str) {
        if (null == this.dialog || this.isActivityDestroyed()) {
            return;
        }
        if (null != list && !list.isEmpty()) {
            final REPORTXML reportxml = new REPORTXML();
            reportXml = reportxml;
            Intrinsics.checkNotNull(reportxml);
            reportxml.reportLines = (List<REPORTLINE>) list;
            final REPORTXML reportxml2 = reportXml;
            Intrinsics.checkNotNull(reportxml2);
            final int size = reportxml2.reportLines.size();
            double d2 = 0.0d;
            double d3 = 0.0d;
            for (int i2 = 0; i2 < size; i2++) {
                final REPORTXML reportxml3 = reportXml;
                Intrinsics.checkNotNull(reportxml3);
                d2 += reportxml3.reportLines.get(i2).DEBIT;
                final REPORTXML reportxml4 = reportXml;
                Intrinsics.checkNotNull(reportxml4);
                d3 += reportxml4.reportLines.get(i2).CREDIT;
                final REPORTXML reportxml5 = reportXml;
                Intrinsics.checkNotNull(reportxml5);
                reportxml5.reportLines.get(i2).TOTAL_CREDIT = d3;
                final REPORTXML reportxml6 = reportXml;
                Intrinsics.checkNotNull(reportxml6);
                reportxml6.reportLines.get(i2).TOTAL_DEBIT = d2;
                final REPORTXML reportxml7 = reportXml;
                Intrinsics.checkNotNull(reportxml7);
                reportxml7.reportLines.get(i2).BALANCE = d3 - d2;
                final REPORTXML reportxml8 = reportXml;
                Intrinsics.checkNotNull(reportxml8);
                final REPORTLINE reportline = reportxml8.reportLines.get(i2);
                final REPORTXML reportxml9 = reportXml;
                Intrinsics.checkNotNull(reportxml9);
                final String DATE_ = reportxml9.reportLines.get(i2).DATE_;
                Intrinsics.checkNotNullExpressionValue(DATE_, "DATE_");
                reportline.DATE_ = this.buildReportDate(DATE_);
                final REPORTXML reportxml10 = reportXml;
                Intrinsics.checkNotNull(reportxml10);
                final REPORTLINE reportline2 = reportxml10.reportLines.get(i2);
                final REPORTXML reportxml11 = reportXml;
                Intrinsics.checkNotNull(reportxml11);
                final String PROCDATE = reportxml11.reportLines.get(i2).PROCDATE;
                Intrinsics.checkNotNullExpressionValue(PROCDATE, "PROCDATE");
                reportline2.PROCDATE = this.buildReportDate(PROCDATE);
            }
            this.setArrVisibility();
            final REPORTXML reportxml12 = reportXml;
            Intrinsics.checkNotNull(reportxml12);
            final List<REPORTLINE> reportLines = reportxml12.reportLines;
            Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
            final ErpType erpType = this.getReportWcfQueriesViewModel().getBaseErp().getErpType();
            Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
            final ReportExtractAdapter reportExtractAdapter = new ReportExtractAdapter(this, reportLines, erpType);
            adapter = reportExtractAdapter;
            Intrinsics.checkNotNull(reportExtractAdapter);
            int[] iArr = arrVisibility;
            if (null == iArr) {
                Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                iArr = null;
            }
            reportExtractAdapter.setColumnVisibility(iArr);
            final ListView listView = lvList;
            Intrinsics.checkNotNull(listView);
            listView.setAdapter(adapter);
            this.setTotals();
            this.setColumnVisibility();
            this.adapterNotifyDataSetChanged();
        }
        final ProgressDialog progressDialog = dialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.dismiss();
    }

    private void setExtractList() {
        this.setArrVisibility();
        this.reOrganize();
        final REPORTXML reportxml = reportXml;
        Intrinsics.checkNotNull(reportxml);
        final List<REPORTLINE> reportLines = reportxml.reportLines;
        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
        final ErpType erpType = this.getReportWcfQueriesViewModel().getBaseErp().getErpType();
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        final ReportExtractAdapter reportExtractAdapter = new ReportExtractAdapter(this, reportLines, erpType);
        adapter = reportExtractAdapter;
        Intrinsics.checkNotNull(reportExtractAdapter);
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        reportExtractAdapter.setColumnVisibility(iArr);
        final ListView listView = lvList;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(adapter);
        this.setTotals();
        this.setColumnVisibility();
        this.adapterNotifyDataSetChanged();
    }

    private void reOrganize() {
        List emptyList;
        final REPORTLINE reportline = new REPORTLINE();
        final REPORTXML reportxml = new REPORTXML();
        reportxml.setResultLine(new ArrayList());
        reportline.TRCODE = 999;
        final REPORTXML reportxml2 = reportXml;
        if (null != reportxml2) {
            Intrinsics.checkNotNull(reportxml2);
            if (null != reportxml2.reportLines) {
                final AppCompatTextView appCompatTextView = tvDate1;
                Intrinsics.checkNotNull(appCompatTextView);
                final List<String> split = new Regex("\\.").split(appCompatTextView.getText().toString(), 0);
                if (!split.isEmpty()) {
                    final ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        if (0 != listIterator.previous().length()) {
                            emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList = CollectionsKt.emptyList();
                final String[] strArr = (String[]) emptyList.toArray(new String[0]);
                final int convertStringToInt = StringUtils.convertStringToInt(strArr[0]);
                final int convertStringToInt2 = StringUtils.convertStringToInt(strArr[1]) - 1;
                final int convertStringToInt3 = StringUtils.convertStringToInt(strArr[2]);
                final Date date = DateAndTimeUtils.getDate(convertStringToInt3, convertStringToInt2, convertStringToInt);
                reportline.SIGN = 2;
                reportline.DATE_ = DateAndTimeUtils.getDateDMY2(date);
                final Date date2 = DateAndTimeUtils.getDate(convertStringToInt3, convertStringToInt2, convertStringToInt - 1);
                reportxml.getResultLine().add(reportline);
                final REPORTXML reportxml3 = reportXml;
                Intrinsics.checkNotNull(reportxml3);
                final int size = reportxml3.reportLines.size();
                double d2 = 0.0d;
                boolean z = false;
                double d3 = 0.0d;
                for (int i2 = 0; i2 < size; i2++) {
                    final REPORTXML reportxml4 = reportXml;
                    Intrinsics.checkNotNull(reportxml4);
                    final String DATE_ = reportxml4.reportLines.get(i2).DATE_;
                    Intrinsics.checkNotNullExpressionValue(DATE_, "DATE_");
                    final String buildReportDate = this.buildReportDate(DATE_);
                    final REPORTXML reportxml5 = reportXml;
                    Intrinsics.checkNotNull(reportxml5);
                    reportxml5.reportLines.get(i2).DATE_ = buildReportDate;
                    final REPORTXML reportxml6 = reportXml;
                    Intrinsics.checkNotNull(reportxml6);
                    d2 += reportxml6.reportLines.get(i2).DEBIT;
                    final REPORTXML reportxml7 = reportXml;
                    Intrinsics.checkNotNull(reportxml7);
                    d3 += reportxml7.reportLines.get(i2).CREDIT;
                    final REPORTXML reportxml8 = reportXml;
                    Intrinsics.checkNotNull(reportxml8);
                    reportxml8.reportLines.get(i2).TOTAL_CREDIT = d3;
                    final REPORTXML reportxml9 = reportXml;
                    Intrinsics.checkNotNull(reportxml9);
                    reportxml9.reportLines.get(i2).TOTAL_DEBIT = d2;
                    final REPORTXML reportxml10 = reportXml;
                    Intrinsics.checkNotNull(reportxml10);
                    reportxml10.reportLines.get(i2).BALANCE = d3 - d2;
                    if (DateAndTimeUtils.getDate(buildReportDate, "dd-MM-yyyy").after(date2)) {
                        final List<REPORTLINE> resultLine = reportxml.getResultLine();
                        final REPORTXML reportxml11 = reportXml;
                        Intrinsics.checkNotNull(reportxml11);
                        resultLine.add(reportxml11.getResultLine().get(i2));
                    } else {
                        final REPORTXML reportxml12 = reportXml;
                        Intrinsics.checkNotNull(reportxml12);
                        reportline.DEBIT = reportxml12.reportLines.get(i2).TOTAL_DEBIT;
                        final REPORTXML reportxml13 = reportXml;
                        Intrinsics.checkNotNull(reportxml13);
                        reportline.CREDIT = reportxml13.reportLines.get(i2).TOTAL_CREDIT;
                        final REPORTXML reportxml14 = reportXml;
                        Intrinsics.checkNotNull(reportxml14);
                        reportline.BALANCE = reportxml14.reportLines.get(i2).BALANCE;
                        z = true;
                    }
                }
                if (!z) {
                    reportxml.getResultLine().remove(0);
                }
                final REPORTXML reportxml15 = reportXml;
                Intrinsics.checkNotNull(reportxml15);
                reportxml15.getResultLine().clear();
                final REPORTXML reportxml16 = reportXml;
                Intrinsics.checkNotNull(reportxml16);
                reportxml16.setResultLine(reportxml.getResultLine());
            }
        }
    }

    private String buildReportDate(String str) {
        List emptyList;
        try {
            if (!TextUtils.isEmpty(str) && !StringsKt.startsWith(str, "1900", false)) {
                if (StringsKt.contains(str, ExifInterface.GPS_DIRECTION_TRUE, false)) {
                    final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(str, 0);
                    if (!split.isEmpty()) {
                        final ListIterator<String> listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (0 != listIterator.previous().length()) {
                                emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                break;
                            }
                        }
                    }
                    emptyList = CollectionsKt.emptyList();
                    str = ((String[]) emptyList.toArray(new String[0]))[0];
                }
                final String convertDateY = DateAndTimeUtils.convertDateY(str);
                Intrinsics.checkNotNullExpressionValue(convertDateY, "convertDateY(...)");
                return convertDateY;
            }
            return "";
        } catch (final Exception e2) {
            Log.e(ReportExtractActivity.TAG, "buildReportDate: ", e2);
            return str;
        }
    }

    private void setColumnVisibility() {
        int i2;
        int[] iArr = arrVisibility;
        int[] iArr2 = null;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        if (0 == iArr[0]) {
            final AppCompatTextView appCompatTextView = tvDescription;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setVisibility(View.GONE);
            i2 = 1;
        } else {
            final AppCompatTextView appCompatTextView2 = tvDescription;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setVisibility(View.VISIBLE);
            i2 = 0;
        }
        int[] iArr3 = arrVisibility;
        if (null == iArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr3 = null;
        }
        if (0 == iArr3[1]) {
            final AppCompatTextView appCompatTextView3 = tvBalance;
            Intrinsics.checkNotNull(appCompatTextView3);
            appCompatTextView3.setVisibility(View.GONE);
            i2++;
        } else if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
            final AppCompatTextView appCompatTextView4 = tvBalance;
            Intrinsics.checkNotNull(appCompatTextView4);
            appCompatTextView4.setVisibility(View.VISIBLE);
        }
        final int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr2 = iArr4;
        }
        if (i2 == iArr2.length) {
            arrVisibility = new int[]{1, 1};
            this.setColumnVisibility();
        }
        if (null != this.adapter) {
            this.adapterNotifyDataSetChanged();
        }
    }

    private void adapterNotifyDataSetChanged() {
        final ReportExtractAdapter reportExtractAdapter = adapter;
        Intrinsics.checkNotNull(reportExtractAdapter);
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        reportExtractAdapter.setColumnVisibility(iArr);
        final ReportExtractAdapter reportExtractAdapter2 = adapter;
        Intrinsics.checkNotNull(reportExtractAdapter2);
        reportExtractAdapter2.notifyDataSetChanged();
    } 
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        menu.findItem(R.id.reportSendMail).setVisible(true);
        menu.findItem(R.id.menu_pdf).setVisible(true);
        menu.findItem(R.id.reportDownloadExcel).setVisible(true);
        this.menu = menu;
        return true;
    }
    private void setScreen() {
        final ScreenControl screenControl = this.screenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.setFullScreen();
        final ScreenControl screenControl2 = this.screenControl;
        Intrinsics.checkNotNull(screenControl2);
        screenControl2.updateMenuTitles(menu);
        final ScreenControl screenControl3 = this.screenControl;
        Intrinsics.checkNotNull(screenControl3);
        if (screenControl3.isFullScreen) {
            final LinearLayout linearLayout = linearFilterDiv;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(View.GONE);
        } else {
            final LinearLayout linearLayout2 = linearFilterDiv;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(View.VISIBLE);
        }
    }
    private String prepareCsv() {
        String PROCDATE;
        final REPORTXML reportxml = reportXml;
        if (null == reportxml) {
            return "";
        }
        Intrinsics.checkNotNull(reportxml);
        if (null == reportxml.reportLines) {
            return "";
        }
        final REPORTXML reportxml2 = reportXml;
        Intrinsics.checkNotNull(reportxml2);
        final List<REPORTLINE> reportLines = reportxml2.reportLines;
        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
        if (!reportLines.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            final String stringResource = ContextUtils.getStringResource(R.string.str_extract_report_fiche_description_debit_credit);
            Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
            sb.append(StringUtils.clearTurkishChars(stringResource));
            sb.append(";");
            if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.NETSIS) {
                if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    sb.append(";");
                } else {
                    sb.append(";");
                    sb.append(ContextUtils.getStringResource(R.string.str_balance) + ';');
                }
                sb.append(ContextUtils.getStringResource(R.string.str_due_date) + ";\n");
            } else if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                sb.append(";\n");
            } else {
                sb.append(";");
                sb.append(ContextUtils.getStringResource(R.string.str_balance) + ";\n");
            }
            final REPORTXML reportxml3 = reportXml;
            Intrinsics.checkNotNull(reportxml3);
            for (final REPORTLINE reportline : reportxml3.reportLines) {
                final ErpType erpType = this.getReportWcfQueriesViewModel().getBaseErp().getErpType();
                final ErpType erpType2 = ErpType.NETSIS;
                if (erpType != erpType2) {
                    final StringBuilder sb2 = new StringBuilder();
                    final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                    final String str = reportline.DATE_;
                    final String str2 = reportline.FICHENO;
                    final String string = this.getResources().getString(this.getTrCodeDescId(reportline.TRCODE));
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    final String format = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{str, str2, StringUtils.clearTurkishChars(string)}, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    sb2.append(format);
                    sb2.append("; \n");
                    sb.append(sb2);
                } else {
                    final StringBuilder sb3 = new StringBuilder();
                    final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                    final String str3 = reportline.DATE_;
                    final String str4 = reportline.FICHENO;
                    final String DESC = reportline.DESC;
                    Intrinsics.checkNotNullExpressionValue(DESC, "DESC");
                    final String format2 = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{str3, str4, StringUtils.clearTurkishChars(DESC)}, 3));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                    sb3.append(format2);
                    sb3.append(";\n");
                    sb.append(sb3);
                }
                sb.append(StringUtils.ffFormat(reportline.DEBIT) + ';');
                sb.append(StringUtils.ffFormat(reportline.CREDIT) + ';');
                if (baseErp.getErpType() == erpType2) {
                    if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                        final double d2 = reportline.BALANCE;
                        if (0.0d > d2) {
                            sb.append(StringUtils.ffFormat((-1) * reportline.BALANCE) + " (B);");
                        } else if (0.0d < d2) {
                            sb.append(StringUtils.ffFormat(reportline.BALANCE) + " (A);");
                        } else {
                            sb.append(StringUtils.ffFormat(reportline.BALANCE) + ';');
                        }
                    }
                    if (null != reportline.PROCDATE && (Intrinsics.areEqual(reportline.f1208X, ReportTransactionType.CHEQUE_ENTRY.getType()) || Intrinsics.areEqual(reportline.f1208X, ReportTransactionType.DEED_ENTRY.getType()))) {
                        PROCDATE = reportline.PROCDATE;
                        Intrinsics.checkNotNullExpressionValue(PROCDATE, "PROCDATE");
                    } else {
                        PROCDATE = "-";
                    }
                    sb.append(PROCDATE + ";\n");
                } else if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    sb.append(SqlLiteVariable._NEW_LINE);
                } else {
                    final double d3 = reportline.BALANCE;
                    if (0.0d > d3) {
                        sb.append(StringUtils.ffFormat((-1) * reportline.BALANCE) + " (B);\n");
                    } else if (0.0d < d3) {
                        sb.append(StringUtils.ffFormat(reportline.BALANCE) + " (A);\n");
                    } else {
                        sb.append(StringUtils.ffFormat(reportline.BALANCE) + ";\n");
                    }
                }
            }
            final REPORTXML reportxml4 = reportXml;
            Intrinsics.checkNotNull(reportxml4);
            final int size = reportxml4.reportLines.size() - 1;
            final REPORTXML reportxml5 = reportXml;
            Intrinsics.checkNotNull(reportxml5);
            final REPORTLINE reportline2 = reportxml5.reportLines.get(size);
            Intrinsics.checkNotNullExpressionValue(reportline2, "get(...)");
            final REPORTLINE reportline3 = reportline2;
            if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                sb.append(StringUtils.ffFormat(reportline3.TOTAL_DEBIT) + ';');
                sb.append(StringUtils.ffFormat(reportline3.TOTAL_CREDIT) + ';');
                final double d4 = reportline3.BALANCE;
                if (0.0d > d4) {
                    sb.append(StringUtils.ffFormat((-1) * reportline3.BALANCE) + "(B);\n");
                } else if (0.0d < d4) {
                    sb.append(StringUtils.ffFormat(reportline3.BALANCE) + "(A);\n");
                } else {
                    sb.append(StringUtils.ffFormat(reportline3.BALANCE) + ";\n");
                }
            }
            final String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "toString(...)");
            return sb4;
        }
        return "";
    }

    private void saveDocumentToDirectory(final Uri uri) {
        final String str = this.generatePdfName() + ".csv";
        try {
            Intrinsics.checkNotNull(uri);
            final DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this, uri);
            Intrinsics.checkNotNull(fromTreeUri);
            final DocumentFile createFile = fromTreeUri.createFile("text/csv", str);
            final ContentResolver contentResolver = this.getContentResolver();
            Intrinsics.checkNotNull(createFile);
            final OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
            Intrinsics.checkNotNull(openOutputStream);
            final byte[] bytes = this.prepareCsv().getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            openOutputStream.write(bytes);
            openOutputStream.close();
            final Context context = ContextUtils.getmContext();
            final Uri uri2 = createFile.getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
            Toast.makeText(context, this.getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
        } catch (final IOException e2) {
            e2.printStackTrace();
            Toast.makeText(this, this.getString(R.string.exp_98_error_save_csv), Toast.LENGTH_SHORT).show();
        }
    }

    private String generateHTML() {
        Iterator<REPORTLINE> it;
        String str;
        ErpType erpType;
        String PROCDATE;
        final REPORTXML reportxml = reportXml;
        if (null == reportxml) {
            return "";
        }
        Intrinsics.checkNotNull(reportxml);
        if (null == reportxml.reportLines) {
            return "";
        }
        final REPORTXML reportxml2 = reportXml;
        Intrinsics.checkNotNull(reportxml2);
        final List<REPORTLINE> reportLines = reportxml2.reportLines;
        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
        if (!reportLines.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>");
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            sb.append("<style> body { font-family: Arial, Helvetica, sans-serif;} table { border: 1px solid #dddddd; border-collapse: collapse;} td, th { border: 1px solid #dddddd; text-align: left; padding: 8px;} table  { page-break-inside:auto }tr { page-break-inside:avoid; page-break-after:auto }.alnright { text-align: right;} </style>");
            sb.append("<table>");
            sb.append("<col width=\"250\">");
            sb.append("<col width=\"250\">");
            sb.append("<col width=\"200\">");
            sb.append("<tbody>");
            sb.append("<tr>");
            sb.append("<th colspan=\"2\" style=\"text-align: center;\">" + ContextUtils.getStringResource(R.string.str_extract_report_fiche_description_debit_credit) + "</th>");
            if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                sb.append("<th style=\"text-align: center;\">" + ContextUtils.getStringResource(R.string.str_balance) + "</th>");
            }
            if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.NETSIS) {
                sb.append("<th style=\"text-align: center;\">" + ContextUtils.getStringResource(R.string.str_due_date) + "</th>");
            }
            sb.append("</tr>");
            final REPORTXML reportxml3 = reportXml;
            Intrinsics.checkNotNull(reportxml3);
            Iterator<REPORTLINE> it2 = reportxml3.reportLines.iterator();
            while (it2.hasNext()) {
                final REPORTLINE next = it2.next();
                sb.append("<tr>");
                sb.append("<td colspan=\"2\">");
                sb.append("<table width=\"100%\">");
                sb.append("<tr>");
                final ErpType erpType2 = this.getReportWcfQueriesViewModel().getBaseErp().getErpType();
                final ErpType erpType3 = ErpType.NETSIS;
                if (erpType2 != erpType3) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("<td colspan=\"2\">");
                    final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
                    it = it2;
                    erpType = erpType3;
                    str = "<td class='alnright' style=\"color:grey\">";
                    final String format = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{next.DATE_, next.FICHENO, this.getResources().getString(this.getTrCodeDescId(next.TRCODE))}, 3));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    sb2.append(format);
                    sb2.append("</td>");
                    sb.append(sb2);
                } else {
                    it = it2;
                    str = "<td class='alnright' style=\"color:grey\">";
                    erpType = erpType3;
                    final StringBuilder sb3 = new StringBuilder();
                    sb3.append("<td colspan=\"2\">");
                    final PrimitiveCompanionObjects primitiveCompanionObjects2 = PrimitiveCompanionObjects.INSTANCE;
                    final String format2 = String.format("%s / %s - %s", Arrays.copyOf(new Object[]{next.DATE_, next.FICHENO, next.DESC}, 3));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                    sb3.append(format2);
                    sb3.append("</td>");
                    sb.append(sb3);
                }
                sb.append("</tr>");
                sb.append("<tr>");
                if (0.0d < next.DEBIT) {
                    sb.append("<td class='alnright' style=\"color:red\" width=\"50%\">" + StringUtils.ffFormat(next.DEBIT) + "</td>");
                } else {
                    sb.append("<td class='alnright' width=\"50%\">" + StringUtils.ffFormat(next.DEBIT) + "</td>");
                }
                sb.append("<td class='alnright' width=\"50%\">" + StringUtils.ffFormat(next.CREDIT) + "</td>");
                sb.append("</tr>");
                sb.append("</table>");
                sb.append("</td>");
                if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    final double d2 = next.BALANCE;
                    if (0.0d > d2) {
                        sb.append("<td class='alnright' style=\"color:red\">" + StringUtils.ffFormat((-1) * next.BALANCE) + " (B) </td>");
                    } else if (0.0d < d2) {
                        sb.append("<td class='alnright'>" + StringUtils.ffFormat(next.BALANCE) + " (A) </td>");
                    } else {
                        sb.append(str + StringUtils.ffFormat(next.BALANCE) + "</td>");
                    }
                }
                if (baseErp.getErpType() == erpType) {
                    if (null != next.PROCDATE && (Intrinsics.areEqual(next.f1208X, ReportTransactionType.CHEQUE_ENTRY.getType()) || Intrinsics.areEqual(next.f1208X, ReportTransactionType.DEED_ENTRY.getType()))) {
                        PROCDATE = next.PROCDATE;
                        Intrinsics.checkNotNullExpressionValue(PROCDATE, "PROCDATE");
                    } else {
                        PROCDATE = "-";
                    }
                    sb.append("<td class='alnright'>" + PROCDATE + "</td>");
                }
                sb.append("</tr>");
                it2 = it;
            }
            sb.append("<tr>");
            final REPORTXML reportxml4 = reportXml;
            Intrinsics.checkNotNull(reportxml4);
            final int size = reportxml4.reportLines.size() - 1;
            final REPORTXML reportxml5 = reportXml;
            Intrinsics.checkNotNull(reportxml5);
            final REPORTLINE reportline = reportxml5.reportLines.get(size);
            if (!this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                sb.append("<td class='alnright' style=\"color:red\">" + StringUtils.ffFormat(reportline.TOTAL_DEBIT) + "</td>");
                sb.append("<td class='alnright'>" + StringUtils.ffFormat(reportline.TOTAL_CREDIT) + "</td>");
                final double d3 = reportline.BALANCE;
                if (0.0d > d3) {
                    sb.append("<td class='alnright' style=\"color:red\">" + StringUtils.ffFormat((-1) * reportline.BALANCE) + "(B)</td>");
                } else if (0.0d < d3) {
                    sb.append("<td class='alnright'>" + StringUtils.ffFormat(reportline.BALANCE) + "(A)</td>");
                } else {
                    sb.append("<td class='alnright' style=\"color:grey\">" + StringUtils.ffFormat(reportline.BALANCE) + "</td>");
                }
            }
            sb.append("</tr>");
            sb.append("</tbody>");
            sb.append("</table>");
            sb.append("</body>");
            sb.append("</html>");
            final String sb4 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb4, "toString(...)");
            return sb4;
        }
        return "";
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        final String str;
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            case R.id.menu_pdf /* 2131297366 */:
                final REPORTXML reportxml = reportXml;
                if (null != reportxml) {
                    Intrinsics.checkNotNull(reportxml);
                    if (null != reportxml.reportLines) {
                        final REPORTXML reportxml2 = reportXml;
                        Intrinsics.checkNotNull(reportxml2);
                        final List<REPORTLINE> reportLines = reportxml2.reportLines;
                        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                        if (!reportLines.isEmpty()) {
                            final BottomSheetDialog bottomSheetDialog = mBottomSheetDialog;
                            Intrinsics.checkNotNull(bottomSheetDialog);
                            bottomSheetDialog.show();
                            return true;
                        }
                    }
                }
                Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reportBackLoad /* 2131297561 */:
                arrVisibility = new int[]{1, 1};
                this.setColumnVisibility();
                if (null != this.adapter) {
                    this.adapterNotifyDataSetChanged();
                }
                return true;
            case R.id.reportDownloadExcel /* 2131297565 */:
                this.openFilePicker();
                return super.onOptionsItemSelected(item);
            case R.id.reportFullScreen /* 2131297568 */:
                this.setScreen();
                return true;
            case R.id.reportRefresh /* 2131297570 */:
                this.refresh();
                return true;
            case R.id.reportSendMail /* 2131297571 */:
                final List table = this.getReportWcfQueriesViewModel().getBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(customerRef)});
                if (null == table || 1 != table.size()) {
                    str = "";
                } else {
                    if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.NETSIS) {
                        this.getReportWcfQueriesViewModel().getBaseErp().setCustomerInchargeEmailAddress((ClCard) table.get(0));
                    }
                    str = ((ClCard) table.get(0)).getExtSendEmailAddr();
                }
                if (Intrinsics.areEqual(str, "")) {
                    Toast.makeText(this, R.string.str_not_found_email_address, Toast.LENGTH_LONG).show();
                    return true;
                }
                final EmailObject emailObject = new EmailObject();
                emailObject.setTo(new String[]{str});
                emailObject.setSubject(this.getTitle().toString());
                emailObject.setSendHTMLContent(true);
                emailObject.setHtml(this.generateHTML());
                try {
                    this.getReportWcfQueriesViewModel().getBaseErp().sendMail(emailObject, false);
                } catch (final Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openFilePicker() {
        this.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 43);
    }

    private void saveReportViewSetting() {
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        final int length = iArr.length;
        String str = "";
        for (int i2 = 0; i2 < length; i2++) {
            final StringBuilder sb = new StringBuilder();
            sb.append(str);
            int[] iArr2 = arrVisibility;
            if (null == iArr2) {
                Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                iArr2 = null;
            }
            sb.append(iArr2[i2]);
            sb.append(',');
            str = sb.toString();
        }
        final String substring = str.substring(0, str.length() - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        this.getReportWcfQueriesViewModel().getSqlHelper().addParamDbTwo("REPORTPARAM", paramNo, substring);
    }

    @Override // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        this.saveReportViewSetting();
    }

    @SuppressLint("SetTextI18n")
    private void setTotals() {
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            if (null != reportxml.reportLines) {
                final REPORTXML reportxml2 = reportXml;
                Intrinsics.checkNotNull(reportxml2);
                final List<REPORTLINE> reportLines = reportxml2.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                if (reportLines.isEmpty()) {
                    return;
                }
                final REPORTXML reportxml3 = reportXml;
                Intrinsics.checkNotNull(reportxml3);
                final int size = reportxml3.reportLines.size() - 1;
                final REPORTXML reportxml4 = reportXml;
                Intrinsics.checkNotNull(reportxml4);
                final REPORTLINE reportline = reportxml4.reportLines.get(size);
                if (this.getReportWcfQueriesViewModel().getBaseErp().isHideCustomerBalance()) {
                    final LinearLayout linearLayout = lnBalance;
                    Intrinsics.checkNotNull(linearLayout);
                    linearLayout.setVisibility(View.GONE);
                    final LinearLayout linearLayout2 = lnDebitCredit;
                    Intrinsics.checkNotNull(linearLayout2);
                    linearLayout2.setVisibility(View.GONE);
                    return;
                }
                if (0.0d != reportline.TOTAL_DEBIT) {
                    final AppCompatTextView appCompatTextView = txtFicheTotalDebit;
                    Intrinsics.checkNotNull(appCompatTextView);
                    appCompatTextView.setText(StringUtils.ffFormat(reportline.TOTAL_DEBIT));
                } else {
                    final AppCompatTextView appCompatTextView2 = txtFicheTotalDebit;
                    Intrinsics.checkNotNull(appCompatTextView2);
                    appCompatTextView2.setText(this.getString(R.string.empty_text));
                }
                if (0.0d != reportline.TOTAL_CREDIT) {
                    final AppCompatTextView appCompatTextView3 = txtFicheTotalCredit;
                    Intrinsics.checkNotNull(appCompatTextView3);
                    appCompatTextView3.setText(StringUtils.ffFormat(reportline.TOTAL_CREDIT));
                } else {
                    final AppCompatTextView appCompatTextView4 = txtFicheTotalCredit;
                    Intrinsics.checkNotNull(appCompatTextView4);
                    appCompatTextView4.setText(this.getString(R.string.empty_text));
                }
                final double d2 = reportline.BALANCE;
                if (0.0d > d2) {
                    final AppCompatTextView appCompatTextView5 = txtFicheTotalBalance;
                    Intrinsics.checkNotNull(appCompatTextView5);
                    appCompatTextView5.setText(StringUtils.ffFormat((-1) * reportline.BALANCE) + "(B)");
                    final AppCompatTextView appCompatTextView6 = txtFicheTotalBalance;
                    Intrinsics.checkNotNull(appCompatTextView6);
                    appCompatTextView6.setTextColor(redColor);
                    return;
                }
                if (0.0d < d2) {
                    final AppCompatTextView appCompatTextView7 = txtFicheTotalBalance;
                    Intrinsics.checkNotNull(appCompatTextView7);
                    appCompatTextView7.setText(StringUtils.ffFormat(reportline.BALANCE) + "(A)");
                    final AppCompatTextView appCompatTextView8 = txtFicheTotalBalance;
                    Intrinsics.checkNotNull(appCompatTextView8);
                    appCompatTextView8.setTextColor(blackColor);
                    return;
                }
                final AppCompatTextView appCompatTextView9 = txtFicheTotalBalance;
                Intrinsics.checkNotNull(appCompatTextView9);
                appCompatTextView9.setText(StringUtils.ffFormat(reportline.BALANCE));
                final AppCompatTextView appCompatTextView10 = txtFicheTotalBalance;
                Intrinsics.checkNotNull(appCompatTextView10);
                final Context context = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context);
                appCompatTextView10.setTextColor(ContextCompat.getColor(context, R.color.grey800));
            }
        }
    }

    
    public static void netsisItemClickListenerlambda15(final ReportExtractActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportXml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        if (!this0.canDrillDownToNetsisFiche(reportline)) {
            this0.showNetsisFicheDetail(reportline);
            return;
        }
        final BaseCommunication baseCommunication = this0.getReportWcfQueriesViewModel().getBaseErp().getBaseCommunication();
        Intrinsics.checkNotNull(baseCommunication, "null cannot be cast to non-null type com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask");
        final NetsisRestAsyncTask netsisRestAsyncTask = (NetsisRestAsyncTask) baseCommunication;
        final String buildNetsisFicheKey = this0.buildNetsisFicheKey(reportline);
        final Sales initSalesForNetsis = this0.initSalesForNetsis(reportline);
        this0.dialog = ProgressDialog.show(this0, "", this0.getString(R.string.str_please_wait), true);
        final String FICHENO = reportline.FICHENO;
        Intrinsics.checkNotNullExpressionValue(FICHENO, "FICHENO");
        if (this0.isFicheFromLocalData(FICHENO)) {
            return;
        }
        netsisRestAsyncTask.readInvoiceFiche(buildNetsisFicheKey, initSalesForNetsis, new ReportNetsisFicheListenerBuilder().setmContent(this0).createReportNetsisFicheListener());
    }

    private Sales initSalesForNetsis(final REPORTLINE reportline) {
        final SalesType salesType = new CustomerOperation(this.getSalesTypeFromNetsisReportLine(reportline), this.getFicheTypeFromNetsisReportLine(reportline), FicheMode.ANALYSE).getSalesType();
        Intrinsics.checkNotNull(salesType);
        final Sales sales = new Sales(salesType.getmValue());
        MainActivity.fType = FType.fatura;
        sales.setClRef(customerRef);
        final String customerClCode = this.getReportWcfQueriesViewModel().getBaseErp().getCustomerClCode(customerRef);
        Intrinsics.checkNotNullExpressionValue(customerClCode, "getCustomerClCode(...)");
        sales.setClCode(customerClCode);
        return sales;
    }

    private SalesType getSalesTypeFromNetsisReportLine(final REPORTLINE reportline) {
        final int netsisFicheType = this.getNetsisFicheType(reportline);
        if (1 == netsisFicheType) {
            return SalesType.INVOICE;
        }
        if (2 == netsisFicheType) {
            return SalesType.RETURN_INVOICE;
        }
        if (3 == netsisFicheType) {
            return SalesType.DISPATCH;
        }
        if (4 == netsisFicheType) {
            return SalesType.RETURN_DISPATCH;
        }
        return SalesType.INVOICE;
    }

    private FicheType getFicheTypeFromNetsisReportLine(final REPORTLINE reportline) {
        final int netsisFicheType = this.getNetsisFicheType(reportline);
        if (1 == netsisFicheType || 2 == netsisFicheType) {
            return FicheType.INVOICE;
        }
        if (3 == netsisFicheType || 4 == netsisFicheType) {
            return FicheType.DISPATCH;
        }
        return FicheType.INVOICE;
    }

    private int getNetsisFicheType(final REPORTLINE reportline) {
        final String str = reportline.DOCODE;
        Intrinsics.checkNotNull(str);
        final String substring = str.substring(2, 3);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return StringUtils.convertStringToInt(substring);
    }

    private String buildNetsisFicheKey(final REPORTLINE reportline) {
        final int netsisFicheType = this.getNetsisFicheType(reportline);
        final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        final String format = String.format("%s;%s;%s", Arrays.copyOf(new Object[]{Integer.valueOf(netsisFicheType - 1), reportline.FICHENO, this.getReportWcfQueriesViewModel().getBaseErp().getCustomerClCode(customerRef)}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private boolean canDrillDownToNetsisFiche(final REPORTLINE reportline) {
        final String str = reportline.DOCODE;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(reportline.f1208X)) {
            return false;
        }
        return (Intrinsics.areEqual(reportline.f1208X, "B") || Intrinsics.areEqual(reportline.f1208X, "C")) && 3 <= str.length();
    }

    
    public static void itemClickListenerlambda16(final ReportExtractActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        final Intent intent;
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportXml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        final int i3 = reportline.TRCODE;
        if (38 == i3 || 33 == i3) {
            final Sales initSales = this0.initSales(this0.customerRef, 33 == i3);
            this0.dialog = ProgressDialog.show(this0, "", this0.getString(R.string.str_please_wait), true);
            if (this0.isFicheFromLocalData(String.valueOf(reportline.SOURCEFREF))) {
                return;
            }
            this0.getReportWcfQueriesViewModel().getBaseErp().readInvoiceFiche(reportline.SOURCEFREF, DataObjectType.ADDINVOICE, initSales, new ReportInvoiceFicheListener(this0));
            return;
        }
        if (13 == i3 || 31 == i3 || 32 == i3 || 34 == i3 || 36 == i3 || 37 == i3 || 39 == i3 || 43 == i3 || 44 == i3 || 81 == i3) {
            this0.setIntent(new Intent(this0, ReportExtractInvoiceActivity.class));
            final Intent intent2 = this0.getIntent();
            Intrinsics.checkNotNull(intent2);
            intent2.putExtra("sFicheRef", reportline.SOURCEFREF);
            final Intent intent3 = this0.getIntent();
            Intrinsics.checkNotNull(intent3);
            intent3.putExtra("invoice", 81 != reportline.TRCODE);
        } else {
            final int i4 = reportline.MODULENR;
            if (82 == i4) {
                return;
            }
            if (10 != i4) {
                intent = new Intent(this0, ReportExtractDetailActivity.class);
            } else {
                intent = new Intent(this0, ReportExtractCaseCashDetailActivity.class);
            }
            this0.setIntent(intent);
            final Intent intent4 = this0.getIntent();
            Intrinsics.checkNotNull(intent4);
            intent4.putExtra("sFicheRef", reportline.SOURCEFREF);
            final Intent intent5 = this0.getIntent();
            Intrinsics.checkNotNull(intent5);
            intent5.putExtra("CLREF", this0.getClRef());
            final Intent intent6 = this0.getIntent();
            Intrinsics.checkNotNull(intent6);
            intent6.putExtra("TRCODE", reportline.TRCODE);
            final Intent intent7 = this0.getIntent();
            Intrinsics.checkNotNull(intent7);
            intent7.putExtra("logicalRef", reportline.LOGICALREF);
        }
        this0.startActivity(this0.getIntent());
    }

    private boolean isFicheFromLocalData(final String str) {
        final List table = this.getReportWcfQueriesViewModel().getBaseErp().getLogoSqlHelper().getTable(Invoice.class, baseErp.getErpType() == ErpType.NETSIS ? "FICHENO=? " : "FICHEREF=? ", new String[]{str});
        if (null == table || table.isEmpty()) {
            return false;
        }
        this.getReportWcfQueriesViewModel().getSqlManager().getSalesInvoiceExam(((Invoice) table.get(0)).logicalRef,
                new ReportInvoiceFicheListenerBuilder().setmContent(this).createReportInvoiceFicheListener());
        return true;
    }
     public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            outState.putSerializable("reportXml", reportxml);
        }
        final AppCompatTextView appCompatTextView = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView);
        outState.putString("strDate1", appCompatTextView.getText().toString());
        final AppCompatTextView appCompatTextView2 = tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        outState.putString("strDate2", appCompatTextView2.getText().toString());
        outState.putInt(ReportExtractActivity.STATE_CUSTOMER_REF, this.getClRef());
        outState.putParcelable(ReportExtractActivity.STATE_CUSTOMER_OPERATION, mCustomerOperation);
        final ReportCurrencyUnit reportCurrencyUnit = mReportCurrencyUnit;
        Intrinsics.checkNotNull(reportCurrencyUnit);
        outState.putInt(ReportExtractActivity.STATE_REPORT_CURRENCY_UNIT, reportCurrencyUnit.getValue());
        this.saveReportViewSetting();
    }

    private void restoreActivityBundle(final Bundle bundle) {
        if (bundle.containsKey("reportXml")) {
            reportXml = (REPORTXML) bundle.getSerializable("reportXml");
        }
        if (bundle.containsKey("strDate1")) {
            final String string = bundle.getString("strDate1");
            Intrinsics.checkNotNull(string);
            strDate1 = string;
        }
        if (bundle.containsKey("strDate2")) {
            final String string2 = bundle.getString("strDate2");
            Intrinsics.checkNotNull(string2);
            strDate2 = string2;
        }
    }

    private void exeRetrieve(final int i2) {
        final SelectResult selectResult;
        final int salesPersonRef = this.getReportWcfQueriesViewModel().getBaseErp().getUser().getSalesPersonRef();
        final ReportCurrencyUnit reportCurrencyUnit = mReportCurrencyUnit;
        if (null != reportCurrencyUnit) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
            final String str = createDateStart;
            Intrinsics.checkNotNull(str);
            final String str2 = createDateEnd;
            Intrinsics.checkNotNull(str2);
            selectResult = reportWcfQueriesViewModel.getExtractList(i2, str, str2, reportCurrencyUnit, salesPersonRef);
        } else {
            selectResult = null;
        }
        this.setSelectResult(selectResult);
        final SelectResult selectResult2 = this.getSelectResult();
        if (null != selectResult2) {
            selectResult2.resultXML = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ReportRetrieve(getSelectResult()));
        ServicesClientForTiger.ReportRetrieve retrieve = getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }
     public void processFinish(REPORTXML reportxml, ProcessType processType) {
        if (this.dialog != null && !isActivityDestroyed()) {
            ProgressDialog progressDialog = this.dialog;
            Intrinsics.checkNotNull(progressDialog);
            progressDialog.dismiss();
        }
        if (processType == ProcessType.REPORTEXTRACT) {
            if ((reportxml != null ? reportxml.reportLines : null) != null) {
                List<REPORTLINE> reportLines = reportxml.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                if (!reportLines.isEmpty()) {
                    this.reportXml = reportxml;
                    setExtractList();
                }
            }
            getTotalDebitCredit();
        }
    }
    private Unit getTotalDebitCredit() {
        setSelectResult(getReportWcfQueriesViewModel().getTotalCreditDebit(getClRef(), this.createDateStart, this.createDateEnd));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.resultXML = "";
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        setRetrieve(clientForTiger.new ReportRetrieve(this.getSelectResult()));
        final ServicesClientForTiger.ReportRetrieve retrieve = this.getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
        return Unit.INSTANCE;
    }
    record ReportNetsisFicheListener(
            WeakReference<ReportExtractActivity> mContent) implements ResponseListener<Sales> {
                ReportNetsisFicheListener(final ReportExtractActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }
            public void onResponse(final Sales sales) {
                if (null != this.mContent.get()) {
                    final ReportExtractActivity reportExtractActivity = mContent.get();
                    Intrinsics.checkNotNull(reportExtractActivity);
                    if (!reportExtractActivity.isActivityDestroyed()) {
                        final ReportExtractActivity reportExtractActivity2 = mContent.get();
                        Intrinsics.checkNotNull(reportExtractActivity2);
                        final ProgressDialog progressDialog = reportExtractActivity2.dialog;
                        Intrinsics.checkNotNull(progressDialog);
                        progressDialog.dismiss();
                    }
                }
                if (null == this.mContent.get() || null == sales) {
                    return;
                }
                final ReportExtractActivity reportExtractActivity3 = mContent.get();
                Intrinsics.checkNotNull(reportExtractActivity3);
                reportExtractActivity3.openSalesFiche(sales, FicheMode.ANALYSE);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mContent.get()) {
                    final ReportExtractActivity reportExtractActivity = mContent.get();
                    Intrinsics.checkNotNull(reportExtractActivity);
                    if (!reportExtractActivity.isActivityDestroyed()) {
                        final ReportExtractActivity reportExtractActivity2 = mContent.get();
                        Intrinsics.checkNotNull(reportExtractActivity2);
                        final ProgressDialog progressDialog = reportExtractActivity2.dialog;
                        Intrinsics.checkNotNull(progressDialog);
                        progressDialog.dismiss();
                    }
                }
                if (null != this.mContent.get()) {
                    Toast.makeText(mContent.get(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        }
        private record ReportInvoiceFicheListener(
            WeakReference<ReportExtractActivity> mContent) implements ResponseListener<Sales> {
            private ReportInvoiceFicheListener(final ReportExtractActivity mContent) {
                Intrinsics.checkNotNullParameter(mContent, "mContent");
                this.mContent = new WeakReference<>(mContent);
            }
            public void onResponse(final Sales sales) {
                if (null != this.mContent.get()) {
                    final ReportExtractActivity reportExtractActivity = mContent.get();
                    Intrinsics.checkNotNull(reportExtractActivity);
                    if (!reportExtractActivity.isActivityDestroyed()) {
                        final ReportExtractActivity reportExtractActivity2 = mContent.get();
                        Intrinsics.checkNotNull(reportExtractActivity2);
                        final ProgressDialog progressDialog = reportExtractActivity2.dialog;
                        Intrinsics.checkNotNull(progressDialog);
                        progressDialog.dismiss();
                    }
                }
                if (null == this.mContent.get() || null == sales) {
                    return;
                }
                final ReportExtractActivity reportExtractActivity3 = mContent.get();
                Intrinsics.checkNotNull(reportExtractActivity3);
                reportExtractActivity3.openSalesFiche(sales, FicheMode.ANALYSE);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mContent.get()) {
                    Toast.makeText(mContent.get(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        }

    private Sales initSales(final int i2, final boolean z) {
        final SalesType salesType = new CustomerOperation(z ? SalesType.RETURN_INVOICE : SalesType.INVOICE, FicheType.INVOICE, FicheMode.ANALYSE).getSalesType();
        Intrinsics.checkNotNull(salesType);
        final Sales sales = new Sales(salesType.getmValue());
        FTypeControlUtils.setMainFType(FType.fatura);
        sales.setClRef(i2);
        final String customerClCode = this.getReportWcfQueriesViewModel().getBaseErp().getCustomerClCode(i2);
        Intrinsics.checkNotNullExpressionValue(customerClCode, "getCustomerClCode(...)");
        sales.setClCode(customerClCode);
        return sales;
    }

    
    public void openSalesFiche(final Sales sales, final FicheMode ficheMode) {
        final CustomerOperation customerOperation = new CustomerOperation(sales.getmSalesType(), sales.getFicheType(), ficheMode);
        final Intent intent = new Intent(this, SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, sales.getClRef());
        intent.putExtra("bigdata:synccode", this.getReportWcfQueriesViewModel().getBaseErp().saveObject(sales));
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
        intent.putExtra(SalesActivityNew.EXTRA_OPEN_FROM_EXTRACT, true);
        this.startActivity(intent);
    }

    
    public void createPdfToSelectedFolder() {
        if (!this.hasGrantedUri()) {
            final Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            this.startActivityForResult(intent, 40);
            return;
        }
        final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        this.copyPdfFromCacheToSelectedFolder(sharedPreferencesHelper.getReportPdfPath());
    }

    private void copyPdfFromCacheToSelectedFolder(final Uri uri) {
        try {
            try {
                final File file = new File(cacheReportPdfFilePath);
                final int length = (int) file.length();
                final byte[] bArr = new byte[length];
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                bufferedInputStream.read(bArr, 0, length);
                bufferedInputStream.close();
                Intrinsics.checkNotNull(uri);
                final DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this, uri);
                Intrinsics.checkNotNull(fromTreeUri);
                final DocumentFile createFile = fromTreeUri.createFile("application/pdf", file.getName());
                final ContentResolver contentResolver = this.getContentResolver();
                Intrinsics.checkNotNull(createFile);
                final OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                Intrinsics.checkNotNull(openOutputStream);
                openOutputStream.write(bArr);
                openOutputStream.close();
                try {
                    file.delete();
                } catch (final Exception e2) {
                    Log.e("SalesListFragment", "copyPdfFromCacheToSelectedFolder", e2);
                }
                final Context context = ContextUtils.getmContext();
                final Uri uri2 = createFile.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                Toast.makeText(context, this.getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
            } catch (final IOException e3) {
                Log.e("v", "copyPdfFromCacheToSelectedFolder", e3);
            } catch (final Exception e4) {
                Log.e("ReportExtractActivity", "copyPdfFromCacheToSelectedFolder", e4);
            }
        } finally {
            cacheReportPdfFilePath = "";
        }
    }
    private String getPdfDownloadPath(final Uri uri) {
        String str;
        try {
            str = FilePath.getPath(this, uri);
        } catch (final Exception e2) {
            Log.e("ReportExtractActivity", "getPdfDownloadPath", e2);
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            Intrinsics.checkNotNull(str);
            return str;
        }
        final String string = this.getString(R.string.str_pdf_report_saved);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private boolean hasGrantedUri() {
        final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        final Uri reportPdfPath = sharedPreferencesHelper.getReportPdfPath();
        if (null == reportPdfPath) {
            return false;
        }
        final List<UriPermission> persistedUriPermissions = this.getContentResolver().getPersistedUriPermissions();
        Intrinsics.checkNotNullExpressionValue(persistedUriPermissions, "getPersistedUriPermissions(...)");
        final Iterator<UriPermission> it = persistedUriPermissions.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getUri().toString(), reportPdfPath.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint("WrongConstant")
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (-1 == i3) {
            if (40 != i2) {
                if (41 != i2) {
                    if (43 != i2 || null == intent) {
                        return;
                    }
                    this.saveDocumentToDirectory(intent.getData());
                    return;
                }
                try {
                    new File(cacheReportPdfFilePath).delete();
                    cacheReportPdfFilePath = "";
                    return;
                } catch (final Exception e2) {
                    Log.e("ReportExtractActivity", "deleteCache", e2);
                    return;
                }
            }
            if (null != intent) {
                final int flags = intent.getFlags() & 3;
                final ContentResolver contentResolver = this.getContentResolver();
                final Uri data = intent.getData();
                Intrinsics.checkNotNull(data);
                contentResolver.takePersistableUriPermission(data, flags);
                final SharedPreferencesHelper sharedPreferencesHelper = mSharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                final Uri data2 = intent.getData();
                Intrinsics.checkNotNull(data2);
                sharedPreferencesHelper.saveReportPdfPath(data2);
                this.copyPdfFromCacheToSelectedFolder(intent.getData());
            }
        }
    }

    /* compiled from: ReportExtractActivity.kt */
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
