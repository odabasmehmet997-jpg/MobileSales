package com.proje.mobilesales.features.userreport.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriPermission;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.PdfExportOption;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportAppearanceItemCaption;
import com.proje.mobilesales.core.reportparser.ReportColumn;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportLayoutColumn;
import com.proje.mobilesales.core.reportparser.ReportLayoutDefaultItem;
import com.proje.mobilesales.core.reportparser.ReportLayoutGroup;
import com.proje.mobilesales.core.reportparser.ReportLayoutItem;
import com.proje.mobilesales.core.reportparser.ReportLayoutViewCard;
import com.proje.mobilesales.core.reportparser.ReportSummary;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.view.general.CustomerActivity;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.view.list.ProductListActivity;
import com.proje.mobilesales.features.reports.model.enums.ReportColumnDataType;
import com.proje.mobilesales.features.reports.model.enums.ReportDisplayType;
import com.proje.mobilesales.features.reports.model.enums.ReportLayoutItemType;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import com.proje.mobilesales.features.userreport.model.enums.UserReportsType;
import com.proje.mobilesales.features.userreport.repository.UserReportRepository;
import com.proje.mobilesales.features.userreport.viewmodel.UserReportViewModel;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

import static com.proje.mobilesales.core.utils.StringUtils.*;
import static com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity.WhenMappings.EnumSwitchMapping0;

public class UserReportsActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private boolean _mainContainer;
    private BottomSheetDialog bottomSheetDialog;
    private String cacheReportPdfFilePath;
    private String cacheReportPdfFolderPath;
    private ArrayList<GenericData> data;
    private boolean hasOnlyStaticParams;
    private StringBuilder html;
    private boolean isShowMailButton;
    private UserReportActivityListener listener;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private AlertDialogBuilder<?> mClearPathDialogBuilder;
    private int mCustomerRef;
    private int mItemRef;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private Report report;
    private int syncCode;
    private final UserReportRepository repository = new UserReportRepository() {
        public boolean getVATDefaultValue() {
            return false;
        }
        public boolean setDeliveryDateAsToday() {
            return false;
        }
    };
    private final UserReportViewModel viewModel = new UserReportViewModel(this.repository);
    private final String _DETAILREFERENCE = "DETAILREFERENCE";
    private final String _DETAILCOLUMN = "DETAILCOLUMN";
    private final String _DETAILNAME = "DETAILNAME";
    private final String _DETAILPARAMETER = "DETAILPARAMETER";
    private final String _REPORT = "REPORT";
    private final String _ARP = "ARP";
    private final String _ITEM = "ITEM";
    private final String _INVOICE = "INVOICE";
    private final String _RETURNINVOICE = "RETURNINVOICE";
    private final String _ORDER = DailyInfo.ORDER;
    private final String _RETAILINVOICE = "RETAILINVOICE";
    private final String _RETAILRETURNINVOICE = "RETAILRETURNINVOICE";
    private String mailAddress = "";
    private Function1 Function1;

    public interface UserReportActivityListener {
        void dataError(String message);
        void dataReady();
    }
    public static class WhenMappings {
        public static int[] EnumSwitchMapping0 = new int[0];
        public static int[] EnumSwitchMapping1 = new int[0];
        public final int[] EnumSwitchMapping2;
        {
            int[] iArr = new int[ReportLayoutItemType.values().length];
            try {
                iArr[ReportLayoutItemType.Column.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportLayoutItemType.TabbedGroup.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportLayoutItemType.LayoutGroup.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReportLayoutItemType.Label.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReportLayoutItemType.Row.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            EnumSwitchMapping0 = iArr;
            int[] iArr2 = new int[ReportDisplayType.values().length];
            try {
                iArr2[ReportDisplayType.Form.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ReportDisplayType.Grid.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ReportDisplayType.Chart.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            EnumSwitchMapping1 = iArr2;
            int[] iArr3 = new int[UserReportsType.values().length];
            try {
                iArr3[UserReportsType.general.ordinal()] = 1;
            } catch (NoSuchFieldError ignored) {
            }
            try {
                iArr3[UserReportsType.customer.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[UserReportsType.item.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr3[UserReportsType.dashboard.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            EnumSwitchMapping2 = iArr3;
        }
    }
    public static void onCreatelambda4lambda3(DialogInterface dialogInterface, int i2) {
    }
    public void exportPdf(PdfExportOption pdfExportOption) {
    }
    public final AlertDialogBuilder<?> getMAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }
    public final void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.bottomSheetDialog;
    }
    public final void setBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
        this.bottomSheetDialog = bottomSheetDialog;
    }
    public final ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public final void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
    public final Report getReport() {
        return this.report;
    }
    public final void setReport(Report report) {
        this.report = report;
    }
    public final ArrayList<GenericData> getData() {
        return this.data;
    }
    public final void setData(ArrayList<GenericData> arrayList) {
        this.data = arrayList;
    }
    public final StringBuilder getHtml() {
        return this.html;
    }
    public final void setHtml(StringBuilder sb) {
        this.html = sb;
    }
    public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity));
        Context context2 = ContextUtils.getmContext();
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mClearPathDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity2);
        Context context3 = ContextUtils.getmContext();
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context3, (BaseInjectableActivity) activity3);
        this.html = new StringBuilder();
        this.isShowMailButton = getIntent().getBooleanExtra("ShowMailButton", false);
        this.syncCode = getIntent().getIntExtra("bigdata:synccode", -1);
        Object object = this.viewModel.getBaseErp().getObject(this.syncCode, false);
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.Report");
        this.report = (Report) object;
        this.mCustomerRef = getIntent().getIntExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -9999);
        this.mItemRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ITEM_ID, 0);
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{convertIntToString(this.mCustomerRef)});
        if (table != null && !table.isEmpty()) {
            if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                this.viewModel.getBaseErp().setCustomerInchargeEmailAddress((ClCard) table.get(0));
            }
            this.mailAddress = ((ClCard) table.get(0)).getExtSendEmailAddr();
        }
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        this.hasOnlyStaticParams = ReportUtil.checkOnlyStaticParams(report.getReportParams());
        runQuery(true);
        this.mSharedPreferencesHelper = new SharedPreferencesHelper(this);
        this.cacheReportPdfFolderPath = getCacheDir().getAbsolutePath() + "/reportPdf";
        clearExistingReports();
        View inflate = getLayoutInflater().inflate(R.layout.fragment_pdf_bottom_sheet_dialog, null);
        View lnSharePdf = inflate.findViewById(R.id.ln_share_pdf);
        View lnDownloadPdf = inflate.findViewById(R.id.ln_download_pdf);
        View lnShowPdf = inflate.findViewById(R.id.ln_show_pdf);
        View lnClearSavePathPdf = inflate.findViewById(R.id.ln_clear_pdf_path);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        this.bottomSheetDialog = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.setContentView(inflate);
        View view = lnShowPdf;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view2) {
                    UserReportsActivity.onCreatelambda0(UserReportsActivity.this, view2);
                }
            });
        }
        View view2 = lnSharePdf;
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view3) {
                    UserReportsActivity.onCreatelambda1(UserReportsActivity.this, view3);
                }
            });
        }
        View view3 = lnDownloadPdf;
        if (view3 != null) {
            view3.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsActivityExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public void onClick(View view4) {
                    UserReportsActivity.onCreatelambda2(UserReportsActivity.this, view4);
                }
            });
        }
        View view4 = lnClearSavePathPdf;
        if (view4 != null) {
            view4.setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.userreport.view.activity.UserReportsActivityExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public void onClick(View view5) {
                    UserReportsActivity.onCreatelambda4(UserReportsActivity.this, view5);
                }
            });
        }
        if (!this.hasOnlyStaticParams) {
            Report report2 = this.report;
            Intrinsics.checkNotNull(report2);
            if (report2.getReportParams() != null) {
                navigateFilterPage();
            }
        }
    }
    public static final void onCreatelambda0(UserReportsActivity userReportsActivity, View view) {
        Intrinsics.checkNotNullParameter(userReportsActivity, "this0");
        userReportsActivity.exportPdf(PdfExportOption.Preview);
        BottomSheetDialog bottomSheetDialog = userReportsActivity.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }
    public static final void onCreatelambda1(UserReportsActivity userReportsActivity, View view) {
        Intrinsics.checkNotNullParameter(userReportsActivity, "this0");
        userReportsActivity.exportPdf(PdfExportOption.Share);
        BottomSheetDialog bottomSheetDialog = userReportsActivity.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }
    public static final void onCreatelambda2(UserReportsActivity userReportsActivity, View view) {
        Intrinsics.checkNotNullParameter(userReportsActivity, "this0");
        userReportsActivity.exportPdf(PdfExportOption.Download);
        BottomSheetDialog bottomSheetDialog = userReportsActivity.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
    }
    @SuppressLint("ResourceType")
    public static final void onCreatelambda4(UserReportsActivity userReportsActivity, View view) {
        Intrinsics.checkNotNullParameter(userReportsActivity, "this0");
        SharedPreferencesHelper sharedPreferencesHelper = userReportsActivity.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        sharedPreferencesHelper.removeReportPdfPath();
        BottomSheetDialog bottomSheetDialog = userReportsActivity.bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        bottomSheetDialog.dismiss();
        AlertDialogBuilder<?> alertDialogBuilder = userReportsActivity.mClearPathDialogBuilder;
        if (alertDialogBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearPathDialogBuilder");
            alertDialogBuilder = null;
        }
            alertDialogBuilder.setMessage(userReportsActivity.getString(R.string.str_pdf_save_path_cleared)).setCancelable(false).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                UserReportsActivity.onCreatelambda4lambda3(dialogInterface, i2);
            }
        }).create().show();
    }
    private void clearExistingReports() {
        try {
            String str = this.cacheReportPdfFolderPath;
            File file = str != null ? new File(str) : null;
            Intrinsics.checkNotNull(file);
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
            Log.e("UserReports", "clearExistingReports", e2);
        }
    }
    private String convertType(GenericDataPrimitive genericDataPrimitive, ReportColumnDataType reportColumnDataType) {
        if (reportColumnDataType == ReportColumnDataType.String) {
            return genericDataPrimitive.getValue().toString();
        }
        if (reportColumnDataType == ReportColumnDataType.Decimal) {
            return formatDouble(genericDataPrimitive.getValue().toString());
        }
        if (reportColumnDataType != ReportColumnDataType.DateTime) {
            return genericDataPrimitive.getValue().toString();
        }
        String convertReportDateToSimpleDate = DateAndTimeUtils.convertReportDateToSimpleDate(genericDataPrimitive.getValue().toString());
        Intrinsics.checkNotNull(convertReportDateToSimpleDate);
        return convertReportDateToSimpleDate;
    }
    private String addAlign(ReportColumnDataType reportColumnDataType) {
        if (reportColumnDataType == ReportColumnDataType.Decimal || reportColumnDataType == ReportColumnDataType.Numeric) {
            return " class='alnright'";
        }
        return "";
    }
    private void generateGrid(Function1 Function1) {
        List list;
        StringBuilder sb = this.html;
        Intrinsics.checkNotNull(sb);
        sb.append("<table>");
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        String columnWidths = report.getColumnWidths();
        Intrinsics.checkNotNullExpressionValue(columnWidths, "getColumnWidths(...)");
        List<String> split = new Regex("\\|").split(columnWidths, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        list = CollectionsKt.emptyList();
        String[] strArr = (String[]) list.toArray(new String[0]);
        Report report2 = this.report;
        Intrinsics.checkNotNull(report2);
        List<ReportColumn> visibleColumns = report2.getReportLayout().getVisibleColumns();
        int sum = visibleColumns.stream().mapToInt(new ToIntFunction() {
            public int applyAsInt(Object obj) {
                final int i = UserReportsActivity.generateGridlambda7(Function1, obj);
                return i;
            }
        }).sum();
        for (ReportColumn reportColumn : visibleColumns) {
            double width = ((double) (reportColumn.getWidth() * 100)) / ((double) sum);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.2f", Arrays.copyOf(new Object[]{Double.valueOf(width)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            String replacedefault = StringsKt.replace(format, ",", ".", false);
            StringBuilder sb2 = this.html;
            Intrinsics.checkNotNull(sb2);
            sb2.append("<col width=\"" + replacedefault + "%\">");
        }
        StringBuilder sb3 = this.html;
        Intrinsics.checkNotNull(sb3);
        sb3.append("<tbody>");
        StringBuilder sb4 = this.html;
        Intrinsics.checkNotNull(sb4);
        sb4.append("<tr>");
        Iterator<ReportColumn> it = visibleColumns.iterator();
        while (it.hasNext()) {
            StringBuilder sb5 = this.html;
            Intrinsics.checkNotNull(sb5);
            sb5.append("<th>" + it.next().getFieldName() + "</th>");
        }
        StringBuilder sb6 = this.html;
        Intrinsics.checkNotNull(sb6);
        sb6.append("</tr>");
        ArrayList<GenericData> arrayList = this.data;
        Intrinsics.checkNotNull(arrayList);
        Iterator<GenericData> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            GenericData next = it2.next();
            StringBuilder sb7 = this.html;
            Intrinsics.checkNotNull(sb7);
            sb7.append("<tr>");
            for (ReportColumn reportColumn2 : visibleColumns) {
                boolean z = false;
                for (GenericDataPrimitive genericDataPrimitive : next.getGenericDataPrimitives()) {
                    if (Intrinsics.areEqual(reportColumn2.getTranslation() != null ? reportColumn2.getTranslation().getEnConvertedKey() : reportColumn2.getFieldName(), genericDataPrimitive.getName())) {
                        Intrinsics.checkNotNull(genericDataPrimitive);
                        ReportColumnDataType columnDataType = getColumnDataType(genericDataPrimitive);
                        StringBuilder sb8 = this.html;
                        Intrinsics.checkNotNull(sb8);
                        sb8.append("<td" + addAlign(columnDataType) + '>' + convertType(genericDataPrimitive, columnDataType) + "</td>");
                        z = true;
                    }
                }
                if (!z) {
                    StringBuilder sb9 = this.html;
                    Intrinsics.checkNotNull(sb9);
                    sb9.append("<td></td>");
                }
            }
            StringBuilder sb10 = this.html;
            Intrinsics.checkNotNull(sb10);
            sb10.append("</tr>");
        }
        Report report3 = this.report;
        Intrinsics.checkNotNull(report3);
        if (report3.getSummaries() != null) {
            Report report4 = this.report;
            Intrinsics.checkNotNull(report4);
            List<ReportSummary> summaries = report4.getSummaries();
            Intrinsics.checkNotNullExpressionValue(summaries, "getSummaries(...)");
            if (!summaries.isEmpty()) {
                addReportSummaryHtml(true, Function1);
                addReportSummaryHtml(false, Function1);
            }
        }
        StringBuilder sb11 = this.html;
        Intrinsics.checkNotNull(sb11);
        sb11.append("</tbody>");
        StringBuilder sb12 = this.html;
        Intrinsics.checkNotNull(sb12);
        sb12.append("</table>");
    }
    public static int generateGridlambda7(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Number) function1.invoke(obj)).intValue();
    }
    private void addReportSummaryHtml(boolean z, Function1 Function1) {
        StringBuilder sb = this.html;
        Intrinsics.checkNotNull(sb);
        sb.append("<tr>");
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        for (ReportColumn reportColumn : report.getReportLayout().getVisibleColumns()) {
            Report report2 = this.report;
            Intrinsics.checkNotNull(report2);
            List list = (List) report2.getSummaries().stream().filter(new Predicate() {
                public boolean test(Object obj) {
                    return UserReportsActivity.addReportSummaryHtmllambda8(Function1, obj);
                }
            }).collect(Collectors.toList());
            if (list == null || list.isEmpty()) {
                StringBuilder sb2 = this.html;
                Intrinsics.checkNotNull(sb2);
                sb2.append("<td></td>");
            } else if (z) {
                StringBuilder sb3 = this.html;
                Intrinsics.checkNotNull(sb3);
                sb3.append("<td style='font-weight:bold'>" + ((ReportSummary) list.get(0)).getText() + "</td>");
            } else {
                StringBuilder sb4 = this.html;
                Intrinsics.checkNotNull(sb4);
                StringBuilder sb5 = new StringBuilder();
                sb5.append("<td");
                ReportColumnDataType columnDataType = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType, "getColumnDataType(...)");
                sb5.append(addAlign(columnDataType));
                sb5.append(" style='font-weight:bold'>");
                sb5.append(((ReportSummary) list.get(0)).getCalculatedValue());
                sb5.append("</td>");
                sb4.append(sb5);
            }
        }
        StringBuilder sb6 = this.html;
        Intrinsics.checkNotNull(sb6);
        sb6.append("</tr>");
    }
    public static final boolean addReportSummaryHtmllambda8(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }
    private  ReportColumnDataType getColumnDataType(GenericDataPrimitive genericDataPrimitive) {
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        for (ReportColumn reportColumn : report.getReportLayout().getReportColumns()) {
            if (Intrinsics.areEqual(reportColumn.getFieldName(), genericDataPrimitive.getName())) {
                if (reportColumn.getColumnDataType() == null) {
                    return ReportColumnDataType.String;
                }
                ReportColumnDataType columnDataType = reportColumn.getColumnDataType();
                Intrinsics.checkNotNullExpressionValue(columnDataType, "getColumnDataType(...)");
                return columnDataType;
            }
        }
        return ReportColumnDataType.String;
    }
    private  String getValueFromData(GenericData genericData, ReportColumn reportColumn) {
        for (GenericDataPrimitive genericDataPrimitive : genericData.getGenericDataPrimitives()) {
            if (Intrinsics.areEqual(genericDataPrimitive.getName(), reportColumn.getFieldName())) {
                return ReportUtil.formatReportColumnValue(reportColumn, genericDataPrimitive.getValue().toString());
            }
        }
        return "";
    }
    private void createText(String str, float f2, ReportAppearanceItemCaption reportAppearanceItemCaption) {
        StringBuilder sb = new StringBuilder();
        if (reportAppearanceItemCaption != null) {
            if (reportAppearanceItemCaption.isBold() && reportAppearanceItemCaption.isItalic()) {
                sb.append(" font-weight:bold; font-style:italic;");
            } else if (reportAppearanceItemCaption.isBold()) {
                sb.append(" font-weight:bold;");
            } else if (reportAppearanceItemCaption.isItalic()) {
                sb.append(" font-style:italic;");
            }
            String foreColor = reportAppearanceItemCaption.getForeColor();
            Intrinsics.checkNotNullExpressionValue(foreColor, "getForeColor(...)");
            if (foreColor.length() > 0) {
                try {
                    sb.append(" color: " + reportAppearanceItemCaption.getForeColor() + ';');
                } catch (Exception e2) {
                    Log.d("UserFromReportSetColor", "Unknown color error : " + reportAppearanceItemCaption.getForeColor() + ' ' + e2.getMessage());
                }
            }
            if (reportAppearanceItemCaption.isUnderline()) {
                sb.append("text-decoration : underlien;");
            }
            double fontSize = reportAppearanceItemCaption.getFontSize();
            if (0.0d > fontSize || fontSize > 8.0d) {
                double fontSize2 = reportAppearanceItemCaption.getFontSize();
                if (9.0d > fontSize2 || fontSize2 > 12.0d) {
                    double fontSize3 = reportAppearanceItemCaption.getFontSize();
                    if (13.0d <= fontSize3 && fontSize3 <= 17.0d) {
                        sb.append(" font-size:18px;");
                    } else if (reportAppearanceItemCaption.getFontSize() >= 18.0f) {
                        sb.append(" font-size:22px;");
                    }
                } else {
                    sb.append(" font-size:14px;");
                }
            } else {
                sb.append(" font-size:10px;");
            }
        }
        StringBuilder sb2 = this.html;
        Intrinsics.checkNotNull(sb2);
        sb2.append("<td width='" + (f2 * ((float) 100)) + "%' style=\"" + sb + "\">");
        StringBuilder sb3 = this.html;
        Intrinsics.checkNotNull(sb3);
        sb3.append(str);
        StringBuilder sb4 = this.html;
        Intrinsics.checkNotNull(sb4);
        sb4.append("</td>");
    }
    private void createItem(ReportLayoutItem reportLayoutItem, GenericData genericData, boolean z, int i2) {
        int i3 = 0;
        if (reportLayoutItem.getItemType() != null) {
            ReportLayoutItemType itemType = reportLayoutItem.getItemType();
            int i4 = itemType == null ? -1 : EnumSwitchMapping0[itemType.ordinal()];
            if (i4 == 1) {
                Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutColumn");
                ReportLayoutColumn reportLayoutColumn = (ReportLayoutColumn) reportLayoutItem;
                if (reportLayoutColumn.isFormTextVisible()) {
                    String formText = reportLayoutColumn.getFormText();
                    Intrinsics.checkNotNullExpressionValue(formText, "getFormText(...)");
                    createText(formText, reportLayoutColumn.getWeight(), reportLayoutColumn.getTextAppearanceItemCaption());
                    ReportColumn reportColumn = reportLayoutColumn.getReportColumn();
                    Intrinsics.checkNotNullExpressionValue(reportColumn, "getReportColumn(...)");
                    createText(getValueFromData(genericData, reportColumn), reportLayoutColumn.getWeight(), reportLayoutColumn.getValueAppearanceItemCaption());
                    return;
                }
                ReportColumn reportColumn2 = reportLayoutColumn.getReportColumn();
                Intrinsics.checkNotNullExpressionValue(reportColumn2, "getReportColumn(...)");
                createText(getValueFromData(genericData, reportColumn2), reportLayoutColumn.getWeight(), reportLayoutColumn.getValueAppearanceItemCaption());
            } else if (i4 == 2) {
                StringBuilder sb = this.html;
                Intrinsics.checkNotNull(sb);
                sb.append("<tr>");
                for (int i5 = 0; i5 < reportLayoutItem.getChildItems().size(); i5++) {
                    ReportLayoutItem reportLayoutItem2 = reportLayoutItem.getChildItems().get(i5);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem2, "get(...)");
                    createItem(reportLayoutItem2, genericData, false, reportLayoutItem.getChildItems().size());
                }
                StringBuilder sb2 = this.html;
                Intrinsics.checkNotNull(sb2);
                sb2.append("</tr>");
            } else if (i4 == 3) {
                Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutGroup");
                ReportLayoutGroup reportLayoutGroup = (ReportLayoutGroup) reportLayoutItem;
                StringBuilder sb3 = this.html;
                Intrinsics.checkNotNull(sb3);
                sb3.append("<td width=\"" + (100 / i2) + "%\" class=\"tab_item\">");
                StringBuilder sb4 = this.html;
                Intrinsics.checkNotNull(sb4);
                sb4.append("<div class=\"title\">" + reportLayoutGroup.getFormText() + "</div>");
                StringBuilder sb5 = this.html;
                Intrinsics.checkNotNull(sb5);
                sb5.append("<table width=\"100%\">");
                while (i3 < reportLayoutGroup.getChildItems().size()) {
                    ReportLayoutItem reportLayoutItem3 = reportLayoutGroup.getChildItems().get(i3);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem3, "get(...)");
                    createItem(reportLayoutItem3, genericData, true, reportLayoutGroup.getChildItems().size());
                    i3++;
                }
                StringBuilder sb6 = this.html;
                Intrinsics.checkNotNull(sb6);
                sb6.append("</table>");
                StringBuilder sb7 = this.html;
                Intrinsics.checkNotNull(sb7);
                sb7.append("</td>");
            } else if (i4 == 4) {
                Intrinsics.checkNotNull(reportLayoutItem, "null cannot be cast to non-null type com.proje.mobilesales.core.reportparser.ReportLayoutDefaultItem");
                ReportLayoutDefaultItem reportLayoutDefaultItem = (ReportLayoutDefaultItem) reportLayoutItem;
                String formText2 = reportLayoutDefaultItem.getFormText();
                Intrinsics.checkNotNullExpressionValue(formText2, "getFormText(...)");
                createText(formText2, reportLayoutDefaultItem.getWeight(), reportLayoutDefaultItem.getTextAppearanceItemCaption());
            } else if (i4 == 5) {
                if (!z) {
                    for (int i6 = 0; i6 < reportLayoutItem.getChildItems().size(); i6++) {
                        ReportLayoutItem reportLayoutItem4 = reportLayoutItem.getChildItems().get(i6);
                        Intrinsics.checkNotNullExpressionValue(reportLayoutItem4, "get(...)");
                        createItem(reportLayoutItem4, genericData, false, reportLayoutItem.getChildItems().size());
                    }
                    return;
                }
                StringBuilder sb8 = this.html;
                Intrinsics.checkNotNull(sb8);
                sb8.append("<tr>");
                StringBuilder sb9 = this.html;
                Intrinsics.checkNotNull(sb9);
                String sb10 = "<td " +
                        (this._mainContainer ? "class=\"main_container\"" : "") +
                        '>';
                sb9.append(sb10);
                this._mainContainer = false;
                StringBuilder sb11 = this.html;
                Intrinsics.checkNotNull(sb11);
                sb11.append("<table width=\"100%\">");
                for (int i7 = 0; i7 < reportLayoutItem.getChildItems().size(); i7++) {
                    ReportLayoutItem reportLayoutItem5 = reportLayoutItem.getChildItems().get(i7);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem5, "get(...)");
                    createItem(reportLayoutItem5, genericData, false, reportLayoutItem.getChildItems().size());
                }
                StringBuilder sb12 = this.html;
                Intrinsics.checkNotNull(sb12);
                sb12.append("</table>");
                StringBuilder sb13 = this.html;
                Intrinsics.checkNotNull(sb13);
                sb13.append("</td>");
                StringBuilder sb14 = this.html;
                Intrinsics.checkNotNull(sb14);
                sb14.append("</tr>");
            }
        } else {
            List<ReportLayoutItem> childItems = reportLayoutItem.getChildItems();
            Intrinsics.checkNotNullExpressionValue(childItems, "getChildItems(...)");
            if (!childItems.isEmpty()) {
                int size = reportLayoutItem.getChildItems().size();
                while (i3 < size) {
                    ReportLayoutItem reportLayoutItem6 = reportLayoutItem.getChildItems().get(i3);
                    Intrinsics.checkNotNullExpressionValue(reportLayoutItem6, "get(...)");
                    createItem(reportLayoutItem6, genericData, true, reportLayoutItem.getChildItems().size());
                    i3++;
                }
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "getMenuInflater(...)");
        menuInflater.inflate(R.menu.menu_user_reports, menu);
        boolean z = false;
        menu.findItem(R.id.menu_run_query).setVisible(false);
        menu.findItem(R.id.menu_send_mail).setVisible(this.isShowMailButton);
        menu.findItem(R.id.menu_pdf).setVisible(false);
        menu.findItem(R.id.menu_close).setVisible(false);
        MenuItem findItem = menu.findItem(R.id.menu_filter);
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        findItem.setVisible(!ReportUtil.checkOnlyStaticParams(report.getReportParams()));
        MenuItem findItem2 = menu.findItem(R.id.action_excel);
        Report report2 = this.report;
        Intrinsics.checkNotNull(report2);
        if (report2.getDisplayType() == ReportDisplayType.Grid) {
            z = true;
        }
        findItem2.setVisible(z);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            case R.id.action_excel /* 2131296361 */:
                openFilePicker();
                return true;
            case R.id.action_pdf /* 2131296368 */:
                ArrayList<GenericData> arrayList = this.data;
                if (arrayList != null) {
                    Intrinsics.checkNotNull(arrayList);
                    if (!arrayList.isEmpty()) {
                        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
                        Intrinsics.checkNotNull(bottomSheetDialog);
                        bottomSheetDialog.show();
                        return true;
                    }
                }
                Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_filter /* 2131297344 */:
                navigateFilterPage();
                return true;
            case R.id.menu_send_mail /* 2131297401 */:
                ArrayList<GenericData> arrayList2 = this.data;
                Intrinsics.checkNotNull(arrayList2);
                if (!arrayList2.isEmpty()) {
                    sendMail(null);
                } else {
                    Toast.makeText(this, ContextUtils.getStringResource(R.string.str_empty_list_text), Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    private  void openFilePicker() {
        startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 43);
    }
    private  void navigateFilterPage() {
        Intent intent = new Intent(this, UserReportsParametersActivity.class);
        intent.putExtra("bigdata:synccode", this.syncCode);
        intent.putExtra("ReportName", getIntent().getStringExtra("ReportName"));
        intent.putExtra("ReportConstParamProp", getIntent().getSerializableExtra("ReportConstParamProp"));
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, 42);
    }
    private  void generateForm() {
        StringBuilder sb = this.html;
        Intrinsics.checkNotNull(sb);
        sb.append("<table width=700>");
        ArrayList<GenericData> arrayList = this.data;
        Intrinsics.checkNotNull(arrayList);
        Iterator<GenericData> it = arrayList.iterator();
        while (it.hasNext()) {
            GenericData next = it.next();
            StringBuilder sb2 = this.html;
            Intrinsics.checkNotNull(sb2);
            sb2.append("<tr>");
            StringBuilder sb3 = this.html;
            Intrinsics.checkNotNull(sb3);
            sb3.append("<td class=\"main_container\">");
            StringBuilder sb4 = this.html;
            Intrinsics.checkNotNull(sb4);
            sb4.append("<table width=\"100%\">");
            this._mainContainer = false;
            Report report = this.report;
            Intrinsics.checkNotNull(report);
            ReportLayoutViewCard card = report.getReportLayout().getCard();
            Intrinsics.checkNotNullExpressionValue(card, "getCard(...)");
            Intrinsics.checkNotNull(next);
            createItem(card, next, true, 0);
            StringBuilder sb5 = this.html;
            Intrinsics.checkNotNull(sb5);
            sb5.append("</table>");
            StringBuilder sb6 = this.html;
            Intrinsics.checkNotNull(sb6);
            sb6.append("</td>");
            StringBuilder sb7 = this.html;
            Intrinsics.checkNotNull(sb7);
            sb7.append("</tr>");
        }
        StringBuilder sb8 = this.html;
        Intrinsics.checkNotNull(sb8);
        sb8.append("</table>");
    }
    private void generateChart(List<String> list) {
        if (list != null) {
            for (String str : list) {
                StringBuilder sb = this.html;
                Intrinsics.checkNotNull(sb);
                sb.append("<div class=\"container\">");
                StringBuilder sb2 = this.html;
                Intrinsics.checkNotNull(sb2);
                sb2.append("<img src=\"data:image/png;base64,");
                sb2.append(str);
                sb2.append("\"/>");
                StringBuilder sb3 = this.html;
                Intrinsics.checkNotNull(sb3);
                sb3.append("</div>");
            }
        }
    }
    public final void sendMail(List<String> list) {
        List list2;
        boolean z = true;
        if (Intrinsics.areEqual(this.mailAddress, "")) {
            Toast.makeText(this, R.string.str_not_found_email_address, Toast.LENGTH_LONG).show();
            return;
        }
        prepareHtml(list, Function1);
        EmailObject emailObject = new EmailObject();
        List<String> split = new Regex(";").split(this.mailAddress, 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    list2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                    break;
                }
            }
        }
        list2 = CollectionsKt.emptyList();
        emailObject.setTo((String[]) list2.toArray(new String[0]));
        emailObject.setHtml(String.valueOf(this.html));
        emailObject.setSubject(getTitle().toString());
        emailObject.setFiles(list);
        if (list != null && !list.isEmpty()) {
            z = false;
        }
        emailObject.setSendHTMLContent(z);
        this.viewModel.getBaseErp().sendMail(emailObject, false);
    }
    private  String prepareCsv(Function1 Function1) {
        ReportColumnDataType reportColumnDataType;
        Object obj;
        ArrayList<GenericData> arrayList = this.data;
        Intrinsics.checkNotNull(arrayList);
        Iterator<GenericData> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int size = it.next().getGenericDataPrimitives().size();
            if (size > i2) {
                i2 = size;
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = new ArrayList();
        ArrayList<GenericData> arrayList3 = this.data;
        Intrinsics.checkNotNull(arrayList3);
        int size2 = arrayList3.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                break;
            }
            ArrayList<GenericData> arrayList4 = this.data;
            Intrinsics.checkNotNull(arrayList4);
            if (arrayList4.get(i3).getGenericDataPrimitives().size() == i2) {
                for (int i4 = 0; i4 < i2; i4++) {
                    ArrayList<GenericData> arrayList5 = this.data;
                    Intrinsics.checkNotNull(arrayList5);
                    arrayList2.add(arrayList5.get(i3).getGenericDataPrimitives().get(i4).getName());
                    StringBuilder sb2 = new StringBuilder();
                    ArrayList<GenericData> arrayList6 = this.data;
                    Intrinsics.checkNotNull(arrayList6);
                    String name = arrayList6.get(i3).getGenericDataPrimitives().get(i4).getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    sb2.append(clearTurkishChars(name));
                    sb2.append(';');
                    sb.append(sb2);
                }
            } else {
                i3++;
            }
        }
        sb.append(SqlLiteVariable._NEW_LINE);
        ArrayList<GenericData> arrayList7 = this.data;
        Intrinsics.checkNotNull(arrayList7);
        int size3 = arrayList7.size();
        for (int i5 = 0; i5 < size3; i5++) {
            int size4 = arrayList2.size();
            for (int i6 = 0; i6 < size4; i6++) {
                ArrayList<GenericData> arrayList8 = this.data;
                Intrinsics.checkNotNull(arrayList8);
                List list = (List) arrayList8.get(i5).getGenericDataPrimitives().stream().filter(new Predicate() {
                    public boolean test(Object obj2) {
                        return UserReportsActivity.prepareCsvlambda10(Function1, obj2);
                    }
                }).collect(Collectors.toList());
                Intrinsics.checkNotNull(list);
                if (!list.isEmpty()) {
                    Object value = ((GenericDataPrimitive) list.get(0)).getValue();
                    if (DateAndTimeUtils.isDateFormatValid(value.toString(), "yyyy-MM-dd'T'HH:mm:ss")) {
                        sb.append(DateAndTimeUtils.getDateDMY(DateAndTimeUtils.convertDateSqlStringToDate(value.toString())) + ';');
                    } else {
                        Object obj2 = arrayList2.get(i6);
                        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                        String str = (String) obj2;
                        Report report = this.report;
                        Intrinsics.checkNotNull(report);
                        List<ReportColumn> reportColumns = report.getReportLayout().getReportColumns();
                        Intrinsics.checkNotNullExpressionValue(reportColumns, "getReportColumns(...)");
                        Iterator<ReportColumn> it2 = reportColumns.iterator();
                        while (true) {
                            reportColumnDataType = null;
                            if (!it2.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it2.next();
                            if (Intrinsics.areEqual(((ReportColumn) obj).getFieldName(), str)) {
                                break;
                            }
                        }
                        ReportColumn reportColumn = (ReportColumn) obj;
                        if (reportColumn != null) {
                            reportColumnDataType = reportColumn.getColumnDataType();
                        }
                        if (reportColumnDataType == ReportColumnDataType.Decimal
                                || reportColumnDataType == ReportColumnDataType.Numeric) {
                            final StringBuilder append = sb.append((INSTANCE.formatForCsv(value)) + "';'");
                        } else {
                            sb.append(clearTurkishChars(value.toString()) + "';");
                        }
                    }
                } else {
                    sb.append(";");
                }
            }
            sb.append(SqlLiteVariable._NEW_LINE);
        }
        String sb3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
        return sb3;
    }
    public static   boolean prepareCsvlambda10(Function1 Function1, Object obj) {
        Intrinsics.checkNotNullParameter(Function1, "tmp0");
        return ((Boolean) Function1.invoke(obj)).booleanValue();
    }
    private  void prepareHtml(List<String> list, Function1 Function1) {
        this.html = new StringBuilder();
        if (list != null && !list.isEmpty()) {
            String str = list.get(0);
            Intrinsics.checkNotNull(str);
            if (StringsKt.endsWith(str, ".png", false)) {
                StringBuilder sb = this.html;
                Intrinsics.checkNotNull(sb);
                sb.append(getTitle().toString());
                return;
            }
        }
        StringBuilder sb2 = this.html;
        Intrinsics.checkNotNull(sb2);
        sb2.append("<!DOCTYPE html>");
        StringBuilder sb3 = this.html;
        Intrinsics.checkNotNull(sb3);
        sb3.append("<html>");
        StringBuilder sb4 = this.html;
        Intrinsics.checkNotNull(sb4);
        sb4.append("<head>");
        StringBuilder sb5 = this.html;
        Intrinsics.checkNotNull(sb5);
        sb5.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        ReportDisplayType displayType = report.getDisplayType();
        int i2 = displayType == null ? -1 : WhenMappings.EnumSwitchMapping1[displayType.ordinal()];
        if (i2 == 1) {
            StringBuilder sb6 = this.html;
            Intrinsics.checkNotNull(sb6);
            sb6.append("<style> body { font-family: Arial, Helvetica, sans-serif; } .main_container { border: 1px solid #dddddd; } .tab_item { border: 1px solid #dddddd; } .title { display: block; width: 100%; text-align: center; font-weight: bold; padding-top: 2px; padding-bottom: 2px; } table td { vertical-align: top; }table  { page-break-inside:auto }tr { page-break-inside:avoid; page-break-after:auto }</style>");
            StringBuilder sb7 = this.html;
            Intrinsics.checkNotNull(sb7);
            sb7.append("</head>");
            StringBuilder sb8 = this.html;
            Intrinsics.checkNotNull(sb8);
            sb8.append("<body>");
            generateForm();
        } else if (i2 == 2) {
            StringBuilder sb9 = this.html;
            Intrinsics.checkNotNull(sb9);
            sb9.append("<style> body { font-family: Arial, Helvetica, sans-serif; } table { border: 1px solid black; border-collapse: collapse;} td, th { border: 1px solid #dddddd; text-align: left; padding: 8px; } .alnright { text-align: right; } </style>");
            StringBuilder sb10 = this.html;
            Intrinsics.checkNotNull(sb10);
            sb10.append("</head>");
            StringBuilder sb11 = this.html;
            Intrinsics.checkNotNull(sb11);
            sb11.append("<body>");
            generateGrid(Function1);
        } else if (i2 == 3) {
            StringBuilder sb12 = this.html;
            Intrinsics.checkNotNull(sb12);
            sb12.append("<style> .container {\ndisplay: block;\nmargin:30px;\n}\n .container img {\nwidth: 400px;\nheight: 400px;\n}</style>");
            StringBuilder sb13 = this.html;
            Intrinsics.checkNotNull(sb13);
            sb13.append("</head>");
            StringBuilder sb14 = this.html;
            Intrinsics.checkNotNull(sb14);
            sb14.append("<body>");
            generateChart(list);
        }
        StringBuilder sb15 = this.html;
        Intrinsics.checkNotNull(sb15);
        sb15.append("</body>");
        StringBuilder sb16 = this.html;
        Intrinsics.checkNotNull(sb16);
        sb16.append("</html>");
    }
    public final void setListener(UserReportActivityListener userReportActivityListener) {
        this.listener = userReportActivityListener;
    }
    public final void dismisprogress() {
        this.mProgressDialogBuilder.dismiss();
    }
    public final void showMessage(String str) {
        this.mProgressDialogBuilder.dismiss();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    private  String generatePdfName() {
        String str;
        String str2 = (getTitle()).toString() + '_' + DateAndTimeUtils.nowDate("dd.MM.yyyy_HHmmss");
        if (this.mCustomerRef > 0) {
            ClCard customerInfo = this.viewModel.getBaseErp().getCustomerInfo(this.mCustomerRef);
            if (customerInfo == null) {
                return str2;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            str = String.format("(%s)%s_%s", Arrays.copyOf(new Object[]{customerInfo.getCode(), customerInfo.getDefinition(), str2}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        } else if (this.mItemRef > 0) {
            Item itemInfo = this.viewModel.getBaseErp().getItemInfo(this.mItemRef);
            if (itemInfo == null) {
                return str2;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            str = String.format("(%s)%s_%s", Arrays.copyOf(new Object[]{itemInfo.code, itemInfo.name, str2}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (str.length() < 127) {
            return str;
        }
        String substring = str.substring(0, 126);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
    public final AssetManager getAssets() {
        AssetManager assets = getResources().getAssets();
        Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
        return assets;
    }
    public final void exportPdf(List<String> list, PdfExportOption pdfExportOption) {
        this.cacheReportPdfFilePath = "";
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        String generatePdfName = generatePdfName();
        try {
            prepareHtml(list, Function1);
            CreatePdf contentBaseUrl = new CreatePdf(this).setPdfName(generatePdfName).openPrintDialog(false).setContentBaseUrl(null);
            PrintAttributes.MediaSize mediaSize = PrintAttributes.MediaSize.ISO_A4;
            Intrinsics.checkNotNullExpressionValue(mediaSize, "ISO_A4");
            CreatePdf content = contentBaseUrl.setPageSize(mediaSize).setContent(String.valueOf(this.html));
            String str = this.cacheReportPdfFolderPath;
            Intrinsics.checkNotNull(str);
            content.setFilePath(str);
            content.setCallbackListener(new CreatePdf.PdfCallbackListener(this, pdfExportOption) {
                final PdfExportOption option = null;
                final UserReportsActivity this0 = null;

                class WhenMappings {
                    public int[] EnumSwitchMapping0 = new int[PdfExportOption.values().length];
                    public final int[] EnumSwitchMapping1 = new int[0];
                    {
                        int[] iArr = EnumSwitchMapping0;
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

                public void onFailure(String str2) {
                    Intrinsics.checkNotNullParameter(str2, "s");
                    if (this.this0.mProgressDialogBuilder.isShowing()) {
                        this.this0.mProgressDialogBuilder.dismiss();
                    }
                    UserReportsActivity userReportsActivity = this.this0;
                    Toast.makeText(userReportsActivity, userReportsActivity.getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
                    Log.e("UserReportsActivity", "PDF creation failed: " + str2);
                }

                public void onSuccess(String str2) {
                    Intrinsics.checkNotNullParameter(str2, "s");
                    if (this.this0.mProgressDialogBuilder.isShowing()) {
                        this.this0.mProgressDialogBuilder.dismiss();
                    }
                    this.this0.cacheReportPdfFilePath = str2;
                    Uri uriForFile = FileProvider.getUriForFile(this.this0, "com.proje.mobilesales.fileprovider", new File(str2));
                    PdfExportOption pdfExportOption2 = this.option;
                    int i2;
                    if (pdfExportOption2 == null) i2 = -1;
                    else i2 = EnumSwitchMapping0[pdfExportOption2.ordinal()];
                    if (i2 == -1) {
                        throw new NotImplementedError(null);
                    } else if (i2 == 1) {
                        UserReportsActivity userReportsActivity = this.this0;
                        Intrinsics.checkNotNull(uriForFile);
                        userReportsActivity.sharePdf(uriForFile);
                    } else if (i2 == 2) {
                        UserReportsActivity userReportsActivity2 = this.this0;
                        Intrinsics.checkNotNull(uriForFile);
                        userReportsActivity2.previewPdf(uriForFile);
                    } else if (i2 == 3) {
                        this.this0.createPdfToSelectedFolder();
                    }
                }
            });
            content.create();
        } catch (Exception e2) {
            Log.e("UserReportsActivity", "exportPdf", e2);
            if (this.mProgressDialogBuilder.isShowing()) {
                this.mProgressDialogBuilder.dismiss();
            }
            Toast.makeText(this, getString(R.string.str_get_print_value_error), Toast.LENGTH_SHORT).show();
        }
    }
    public final void sharePdf(Uri uri) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf");
        intent.putExtra("android.intent.extra.STREAM", uri);
        startActivity(Intent.createChooser(intent, getString(R.string.menu_pdf_share)));
    }
    public final void previewPdf(Uri uri) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            Toast.makeText(this, R.string.str_no_application_found_to_open_pdf, Toast.LENGTH_LONG).show();
            Log.e("UserReportsActivity", "Activity not found for PDF preview", e2);
        }
    }
    public final void createPdfToSelectedFolder() {
        if (!hasGrantedUri()) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            startActivityForResult(intent, 40);
            return;
        }
        SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        Uri reportPdfPath = sharedPreferencesHelper.getReportPdfPath();
        Intrinsics.checkNotNull(reportPdfPath);
        try {
            copyPdfFromCacheToSelectedFolder(reportPdfPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final void copyPdfFromCacheToSelectedFolder(Uri uri) throws IOException {
        Serializable file;
        DocumentFile fromTreeUri = null;
        try {
            String str = this.cacheReportPdfFilePath;
            Throwable th = null;
            file = (str != null) ? new File(str) : th;
            fromTreeUri = DocumentFile.fromTreeUri(this, uri);
            if (fromTreeUri != null) {
                Intrinsics.checkNotNull(file);
                if (file != null ) {
                    DocumentFile createFile = fromTreeUri.createFile("application/pdf", String.valueOf(file.getClass()));
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream((File) file));
                    try {
                        ContentResolver contentResolver = getContentResolver();
                        Intrinsics.checkNotNull(createFile);
                        OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                        long length = ((File) file).length();
                        byte[] bArr = new byte[(int) length];
                        bufferedInputStream.read(bArr, 0, (int) length);
                        Intrinsics.checkNotNull(openOutputStream);
                        openOutputStream.write(bArr);
                        if (!((File) file).delete()) {
                            Log.e("UserReports", "File deletion failed");
                        }
                        Context context = ContextUtils.getmContext();
                        Uri uri2 = createFile.getUri();
                        Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                        Toast.makeText(context, getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
                        Unit unit = Unit.INSTANCE;
                        this.cacheReportPdfFilePath = "";

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Log.e("UserReports", "Picked directory or file not found.");
            this.cacheReportPdfFilePath = "";
        } catch (Throwable th2) {
            this.cacheReportPdfFilePath = "";
            throw th2;
        }
    }
    private  String getPdfDownloadPath(Uri uri) {
        String str;
        try {
            str = FilePath.getPath(this, uri);
        } catch (Exception e2) {
            Log.e("UserReports", "getPdfDownloadPath", e2);
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            String string = getString(R.string.str_pdf_report_saved);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }
    private  boolean hasGrantedUri() {
        SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
        Intrinsics.checkNotNull(sharedPreferencesHelper);
        Uri reportPdfPath = sharedPreferencesHelper.getReportPdfPath();
        if (reportPdfPath == null) {
            return false;
        }
        List<UriPermission> persistedUriPermissions = getContentResolver().getPersistedUriPermissions();
        Intrinsics.checkNotNullExpressionValue(persistedUriPermissions, "getPersistedUriPermissions(...)");
        for (UriPermission uriPermission : persistedUriPermissions) {
            if (Intrinsics.areEqual(uriPermission.getUri().toString(), reportPdfPath.toString())) {
                return true;
            }
        }
        return false;
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 != -1) {
            return;
        }
        if (i2 == 40) {
            if (intent != null) {
                ContentResolver contentResolver = getContentResolver();
                Uri data = intent.getData();
                Intrinsics.checkNotNull(data);
                contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION & Intent.FLAG_GRANT_READ_URI_PERMISSION);
                SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
                Intrinsics.checkNotNull(sharedPreferencesHelper);
                Uri data2 = intent.getData();
                Intrinsics.checkNotNull(data2);
                sharedPreferencesHelper.saveReportPdfPath(data2);
                Uri data3 = intent.getData();
                Intrinsics.checkNotNull(data3);
                try {
                    copyPdfFromCacheToSelectedFolder(data3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (i2 == 41) {
            try {
                String str = this.cacheReportPdfFilePath;
                File file = str != null ? new File(str) : null;
                Intrinsics.checkNotNull(file);
                file.delete();
                this.cacheReportPdfFilePath = "";
            } catch (Exception e2) {
                Log.e("UserReports", "deleteCache", e2);
            }
        } else if (i2 == 42) {
            runQuery(false);
        } else if (i2 == 43 && intent != null) {
            Uri data4 = intent.getData();
            Intrinsics.checkNotNull(data4);
            saveDocumentToDirectory(data4, Function1);
        }
    }
    private  void saveDocumentToDirectory(Uri uri, Function1 Function1) {
        String str = generatePdfName() + ".csv";
        try {
            DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this, uri);
            if (fromTreeUri != null) {
                DocumentFile createFile = fromTreeUri.createFile("text/csv", str);
                ContentResolver contentResolver = getContentResolver();
                Intrinsics.checkNotNull(createFile);
                OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                if (openOutputStream != null) {
                    byte[] bytes = prepareCsv(Function1).getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    openOutputStream.write(bytes);
                    Context context = ContextUtils.getmContext();
                    Uri uri2 = createFile.getUri();
                    Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                    Toast.makeText(context, getPdfDownloadPath(uri2), Toast.LENGTH_SHORT).show();
                    Unit unit = Unit.INSTANCE;
                } else {
                    Log.e("SaveDocument", "Output stream is null.");
                }
                CloseableKt.closeFinally(openOutputStream, null);
                return;
            }
            Log.e("SaveDocument", "Picked directory is null.");
        } catch (IOException e2) {
            Log.e("SaveDocument", "Error saving CSV file", e2);
            Toast.makeText(this, getString(R.string.exp_98_error_save_csv), Toast.LENGTH_SHORT).show();
        }
    }
    private  void runQuery(boolean z) {
        if (!z || this.hasOnlyStaticParams) {
            Report report = this.report;
            if (report != null) {
                Intrinsics.checkNotNull(report);
                if (report.getSql() != null) {
                    Report report2 = this.report;
                    Intrinsics.checkNotNull(report2);
                    if (!Intrinsics.areEqual(report2.getSql(), "")) {
                        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                        Report report3 = this.report;
                        Intrinsics.checkNotNull(report3);
                        ReportUtil.replaceParameters(report3, ReportConstParamProp.None, -1);
                        UserReportViewModel userReportViewModel = this.viewModel;
                        UserDefinedResponseListener userDefinedResponseListener = new UserDefinedResponseListener(this);
                        Report report4 = this.report;
                        Intrinsics.checkNotNull(report4);
                        String sql = report4.getSql();
                        Intrinsics.checkNotNullExpressionValue(sql, "getSql(...)");
                        Report report5 = this.report;
                        Intrinsics.checkNotNull(report5);
                        userReportViewModel.runUserDefinedSQL(userDefinedResponseListener, sql, report5.getWcfOrderBy());
                        return;
                    }
                }
            }
            Toast.makeText(this, getString(R.string.str_error_report_query_could_not_be_executed), Toast.LENGTH_SHORT).show();
        }
    }
    public static final class UserDefinedResponseListener implements ResponseListener<BaseSelectResult<?>> {
        private final WeakReference<UserReportsActivity> mContent;
        public UserDefinedResponseListener(UserReportsActivity userReportsActivity) {
            this.mContent = new WeakReference<>(userReportsActivity);
        }
        public void onResponse(PrintSlipModel baseSelectResult) {
            Intrinsics.checkNotNull(baseSelectResult);
            if (!baseSelectResult.isSuccess()) {
                if (this.mContent.get() != null) {
                    UserReportsActivity userReportsActivity = this.mContent.get();
                    Intrinsics.checkNotNull(userReportsActivity);
                    userReportsActivity.dismisprogress();
                    Log.d("AA", "onError: " + baseSelectResult.getErrorString());
                    UserReportsActivity userReportsActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(userReportsActivity2);
                    userReportsActivity2.showMessage(ContextUtils.getStringResource(R.string.str_error_report_query_could_not_be_executed));
                    UserReportsActivity userReportsActivity3 = this.mContent.get();
                    Intrinsics.checkNotNull(userReportsActivity3);
                    UserReportActivityListener userReportActivityListener = userReportsActivity3.listener;
                    Intrinsics.checkNotNull(userReportActivityListener);
                    String stringResource = ContextUtils.getStringResource(R.string.str_error_report_query_could_not_be_executed);
                    Intrinsics.checkNotNullExpressionValue(stringResource, "getStringResource(...)");
                    userReportActivityListener.dataError(stringResource);
                }
            } else if (this.mContent.get() != null) {
                UserReportsActivity userReportsActivity4 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity4);
                List<?> dataList = baseSelectResult.getDataList();
                Intrinsics.checkNotNull(dataList, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.model.GenericData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.model.GenericData> }");
                userReportsActivity4.setData((ArrayList) dataList);
                UserReportsActivity userReportsActivity5 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity5);
                userReportsActivity5.dismisprogress();
                UserReportsActivity userReportsActivity6 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity6);
                UserReportActivityListener userReportActivityListener2 = userReportsActivity6.listener;
                Intrinsics.checkNotNull(userReportActivityListener2);
                userReportActivityListener2.dataReady();
            }
        }
        public void onFailure(Throwable throwable) {

        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mContent.get() != null) {
                UserReportsActivity userReportsActivity = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity);
                userReportsActivity.dismisprogress();
                Log.d("AA", "onError: " + str);
                UserReportsActivity userReportsActivity2 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity2);
                userReportsActivity2.showMessage(str);
                UserReportsActivity userReportsActivity3 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity3);
                UserReportActivityListener userReportActivityListener = userReportsActivity3.listener;
                Intrinsics.checkNotNull(userReportActivityListener);
                userReportActivityListener.dataError(str);
            }
        }
    }
    public static final class ReportFicheListener implements ResponseListener<Sales> {
        private final WeakReference<UserReportsActivity> mContent;

        public ReportFicheListener(UserReportsActivity userReportsActivity) {
            this.mContent = new WeakReference<>(userReportsActivity);
        }

        public void onResponse(PrintSlipModel sales) {
            if (this.mContent.get() != null) {
                UserReportsActivity userReportsActivity = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity);
                if (userReportsActivity.mProgressDialogBuilder != null) {
                    UserReportsActivity userReportsActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(userReportsActivity2);
                    userReportsActivity2.mProgressDialogBuilder.dismiss();
                }
            }
            if (this.mContent.get() != null && sales != null) {
                UserReportsActivity userReportsActivity3 = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity3);
                userReportsActivity3.openSalesFiche(sales, FicheMode.ANALYSE);
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mContent.get() != null) {
                UserReportsActivity userReportsActivity = this.mContent.get();
                Intrinsics.checkNotNull(userReportsActivity);
                if (userReportsActivity.mProgressDialogBuilder != null) {
                    UserReportsActivity userReportsActivity2 = this.mContent.get();
                    Intrinsics.checkNotNull(userReportsActivity2);
                    userReportsActivity2.mProgressDialogBuilder.dismiss();
                }
                Toast.makeText(this.mContent.get(), str, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private  Sales initSales(int i2, boolean z) {
        SalesType salesType = new CustomerOperation(z ? SalesType.RETURN_INVOICE : SalesType.INVOICE, FicheType.INVOICE, FicheMode.ANALYSE).getSalesType();
        Intrinsics.checkNotNull(salesType);
        Sales sales = new Sales(salesType.getmValue());
        FTypeControlUtils.setMainFType(FType.fatura);
        sales.setClRef(i2);
        String customerClCode = this.viewModel.getBaseErp().getCustomerClCode(i2);
        Intrinsics.checkNotNullExpressionValue(customerClCode, "getCustomerClCode(...)");
        sales.setClCode(customerClCode);
        return sales;
    }
    private  Sales initRetailSales(int i2, boolean z) {
        SalesType salesType = new CustomerOperation(z ? SalesType.RETAIL_RETURN_INVOICE : SalesType.RETAIL_INVOICE, FicheType.RETAILINVOICE, FicheMode.ANALYSE).getSalesType();
        Intrinsics.checkNotNull(salesType);
        Sales sales = new Sales(salesType.getmValue());
        FTypeControlUtils.setMainFType(FType.perakendefatura);
        sales.setClRef(i2);
        String customerClCode = this.viewModel.getBaseErp().getCustomerClCode(i2);
        Intrinsics.checkNotNullExpressionValue(customerClCode, "getCustomerClCode(...)");
        sales.setClCode(customerClCode);
        return sales;
    }
    private  Sales initOrder() {
        SalesType salesType = new CustomerOperation(SalesType.ORDER, FicheType.ORDER, FicheMode.ANALYSE).getSalesType();
        Intrinsics.checkNotNull(salesType);
        Sales sales = new Sales(salesType.getmValue());
        FTypeControlUtils.setMainFType(FType.siparis);
        return sales;
    }
    public final void openSalesFiche(Sales sales, FicheMode ficheMode) {
        CustomerOperation customerOperation = new CustomerOperation(sales.getmSalesType(), sales.getFicheType(), ficheMode);
        Intent intent = new Intent(this, SalesActivityNew.class);
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_REF, sales.getClRef());
        intent.putExtra("bigdata:synccode", this.viewModel.getBaseErp().saveObject(sales));
        intent.putExtra(SalesActivityNew.EXTRA_CUSTOMER_OPERATION, customerOperation);
        startActivity(intent);
    }
    private  void openGeneralReport(Hashtable<String, String> r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity.openGeneralReport(java.util.Hashtable):void");
    }
    private  void openInvoice(Hashtable<String, String> hashtable, boolean z) {
        try {
            String str = hashtable.get(this._DETAILREFERENCE);
            Integer valueOf = str != null ? Integer.valueOf(Integer.parseInt(str)) : null;
            this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
            Sales initSales = initSales(-9999, z);
            BaseErp<?> baseErp = this.viewModel.getBaseErp();
            Intrinsics.checkNotNull(valueOf);
            baseErp.readInvoiceFiche(valueOf.intValue(), DataObjectType.ADDINVOICE, initSales, new ReportFicheListener(this));
        } catch (Exception unused) {
            Toast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_96_error_open_invoice_report_detail), Toast.LENGTH_LONG).show();
        }
    }
    private  void openInvoiceForNetsis(Hashtable<String, String> hashtable, boolean z) {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        Sales initSales = initSales(-9999, z);
        String sb2 = String.valueOf((z ? NetsisSlipType.ftAFat : NetsisSlipType.ftSFat).ordinal()) +
                ';' +
                (TextUtils.isEmpty(hashtable.get(this._DETAILREFERENCE)) ? "" : hashtable.get(this._DETAILREFERENCE));
        BaseErp<?> baseErp = this.viewModel.getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Netsis");
        ((Netsis) baseErp).readInvoiceFiche(sb2, initSales, new ReportFicheListener(this));
    }
    private  void openRetailInvoice(Hashtable<String, String> hashtable, boolean z) {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        Sales initRetailSales = initRetailSales(-9999, z);
        String str = hashtable.get(this._DETAILREFERENCE);
        if (str != null) {
            this.viewModel.getBaseErp().readInvoiceFiche(Integer.parseInt(str), DataObjectType.ADDINVOICE, initRetailSales, new ReportFicheListener(this));
        }
    }
    private  void openCustomer(Hashtable<String, String> hashtable) {
        int i2;
        if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
            i2 = this.viewModel.getBaseErp().getLogoSqlHelper().getClLogicalRef(hashtable.get(this._DETAILREFERENCE));
        } else {
            String str = hashtable.get(this._DETAILREFERENCE);
            Integer valueOf = str != null ? Integer.valueOf(Integer.parseInt(str)) : null;
            Intrinsics.checkNotNull(valueOf);
            i2 = valueOf.intValue();
        }
        Intent putExtra = new Intent(this, CustomerActivity.class).putExtra(CustomerActivity.EXTRA_CUSTOMER_REF, i2);
        Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
        startActivity(putExtra);
    }
    private  void openItem(Hashtable<String, String> hashtable) {
        int i2;
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra(ProductListActivity.EXTRA_WAREHOUSE_NR, -1);
        if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
            i2 = this.viewModel.getBaseErp().getLogoSqlHelper().getItemLogicalRef(hashtable.get(this._DETAILREFERENCE));
        } else {
            String str = hashtable.get(this._DETAILREFERENCE);
            Integer valueOf = str != null ? Integer.valueOf(Integer.parseInt(str)) : null;
            Intrinsics.checkNotNull(valueOf);
            i2 = valueOf.intValue();
        }
        intent.putExtra(IntentExtraName.EXTRA_ITEM_ID, i2);
        startActivity(intent);
    }
    private  void openOrder(Hashtable<String, String> hashtable) {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        Sales initOrder = initOrder();
        HashSet hashSet = new HashSet();
        hashSet.add(hashtable.get(this._DETAILREFERENCE));
        this.viewModel.getBaseErp().readOrderFiche(new ArrayList(hashSet), DataObjectType.ADDORDER, initOrder, null, new ReportFicheListener(this));
    }
    private  void openOrderForNetsis(Hashtable<String, String> hashtable) {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
        Sales initOrder = initOrder();
        HashSet hashSet = new HashSet();
        String sb = String.valueOf(NetsisSlipType.ftSSip.ordinal()) +
                ';' +
                (TextUtils.isEmpty(hashtable.get(this._DETAILREFERENCE)) ? "" : hashtable.get(this._DETAILREFERENCE));
        hashSet.add(sb);
        this.viewModel.getBaseErp().readOrderFiche(new ArrayList(hashSet), DataObjectType.ADDORDER, initOrder, null, new ReportFicheListener(this));
    }
    public final void getReportDetail(int i2) {
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        if (report.getReportDetails() != null) {
            Report report2 = this.report;
            Intrinsics.checkNotNull(report2);
            Hashtable<String, String> reportDetails = report2.getReportDetails();
            Intrinsics.checkNotNullExpressionValue(reportDetails, "getReportDetails(...)");
            if (!reportDetails.isEmpty()) {
                Report report3 = this.report;
                Intrinsics.checkNotNull(report3);
                if (report3.getCloneReportDetails() != null) {
                    Report report4 = this.report;
                    Intrinsics.checkNotNull(report4);
                    report4.getCloneReportDetails().clear();
                }
                Report report5 = this.report;
                Intrinsics.checkNotNull(report5);
                Report report6 = this.report;
                Intrinsics.checkNotNull(report6);
                report5.setCloneReportDetails(new Hashtable<>(report6.getReportDetails()));
                ArrayList<GenericData> arrayList = this.data;
                Intrinsics.checkNotNull(arrayList);
                Iterator<GenericDataPrimitive> it = arrayList.get(i2).getGenericDataPrimitives().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    GenericDataPrimitive next = it.next();
                    String name = next.getName();
                    Report report7 = this.report;
                    Intrinsics.checkNotNull(report7);
                    if (StringsKt.equals(name, report7.getCloneReportDetails().get(this._DETAILREFERENCE), true)) {
                        Report report8 = this.report;
                        Intrinsics.checkNotNull(report8);
                        report8.getCloneReportDetails().remove(this._DETAILREFERENCE);
                        Report report9 = this.report;
                        Intrinsics.checkNotNull(report9);
                        Hashtable<String, String> cloneReportDetails = report9.getCloneReportDetails();
                        Intrinsics.checkNotNullExpressionValue(cloneReportDetails, "getCloneReportDetails(...)");
                        cloneReportDetails.put(this._DETAILREFERENCE, next.getValue().toString());
                        break;
                    }
                }
                Report report10 = this.report;
                Intrinsics.checkNotNull(report10);
                String _DETAILTYPE = "DETAILTYPE";
                String str = report10.getCloneReportDetails().get(_DETAILTYPE);
                if (Intrinsics.areEqual(str, this._INVOICE)) {
                    if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                        Report report11 = this.report;
                        Intrinsics.checkNotNull(report11);
                        Hashtable<String, String> cloneReportDetails2 = report11.getCloneReportDetails();
                        Intrinsics.checkNotNullExpressionValue(cloneReportDetails2, "getCloneReportDetails(...)");
                        openInvoiceForNetsis(cloneReportDetails2, false);
                        return;
                    }
                    Report report12 = this.report;
                    Intrinsics.checkNotNull(report12);
                    Hashtable<String, String> cloneReportDetails3 = report12.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails3, "getCloneReportDetails(...)");
                    openInvoice(cloneReportDetails3, false);
                    return;
                } else if (Intrinsics.areEqual(str, this._RETURNINVOICE)) {
                    ErpType erpType = this.viewModel.getBaseErp().getErpType();
                    ErpType erpType2 = ErpType.NETSIS;
                    if (erpType == erpType2) {
                        Report report13 = this.report;
                        Intrinsics.checkNotNull(report13);
                        Hashtable<String, String> cloneReportDetails4 = report13.getCloneReportDetails();
                        Intrinsics.checkNotNullExpressionValue(cloneReportDetails4, "getCloneReportDetails(...)");
                        openInvoiceForNetsis(cloneReportDetails4, true);
                    } else {
                        Report report14 = this.report;
                        Intrinsics.checkNotNull(report14);
                        Hashtable<String, String> cloneReportDetails5 = report14.getCloneReportDetails();
                        Intrinsics.checkNotNullExpressionValue(cloneReportDetails5, "getCloneReportDetails(...)");
                        openInvoice(cloneReportDetails5, true);
                    }
                    if (this.viewModel.getBaseErp().getErpType() == erpType2) {
                        showSelectedRowInfo(i2);
                        return;
                    }
                    Report report15 = this.report;
                    Intrinsics.checkNotNull(report15);
                    Hashtable<String, String> cloneReportDetails6 = report15.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails6, "getCloneReportDetails(...)");
                    openRetailInvoice(cloneReportDetails6, false);
                    return;
                } else if (Intrinsics.areEqual(str, this._RETAILINVOICE)) {
                    if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                        showSelectedRowInfo(i2);
                        return;
                    }
                    Report report16 = this.report;
                    Intrinsics.checkNotNull(report16);
                    Hashtable<String, String> cloneReportDetails7 = report16.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails7, "getCloneReportDetails(...)");
                    openRetailInvoice(cloneReportDetails7, false);
                    return;
                } else if (Intrinsics.areEqual(str, this._RETAILRETURNINVOICE)) {
                    if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                        showSelectedRowInfo(i2);
                        return;
                    }
                    Report report17 = this.report;
                    Intrinsics.checkNotNull(report17);
                    Hashtable<String, String> cloneReportDetails8 = report17.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails8, "getCloneReportDetails(...)");
                    openRetailInvoice(cloneReportDetails8, true);
                    return;
                } else if (Intrinsics.areEqual(str, this._ORDER)) {
                    if (this.viewModel.getBaseErp().getErpType() == ErpType.NETSIS) {
                        Report report18 = this.report;
                        Intrinsics.checkNotNull(report18);
                        Hashtable<String, String> cloneReportDetails9 = report18.getCloneReportDetails();
                        Intrinsics.checkNotNullExpressionValue(cloneReportDetails9, "getCloneReportDetails(...)");
                        openOrderForNetsis(cloneReportDetails9);
                        return;
                    }
                    Report report19 = this.report;
                    Intrinsics.checkNotNull(report19);
                    Hashtable<String, String> cloneReportDetails10 = report19.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails10, "getCloneReportDetails(...)");
                    openOrder(cloneReportDetails10);
                    return;
                } else if (Intrinsics.areEqual(str, this._REPORT)) {
                    Report report20 = this.report;
                    Intrinsics.checkNotNull(report20);
                    Hashtable<String, String> cloneReportDetails11 = report20.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails11, "getCloneReportDetails(...)");
                    openGeneralReport(cloneReportDetails11);
                    return;
                } else if (Intrinsics.areEqual(str, this._ARP)) {
                    Report report21 = this.report;
                    Intrinsics.checkNotNull(report21);
                    Hashtable<String, String> cloneReportDetails12 = report21.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails12, "getCloneReportDetails(...)");
                    openCustomer(cloneReportDetails12);
                    return;
                } else if (Intrinsics.areEqual(str, this._ITEM)) {
                    Report report22 = this.report;
                    Intrinsics.checkNotNull(report22);
                    Hashtable<String, String> cloneReportDetails13 = report22.getCloneReportDetails();
                    Intrinsics.checkNotNullExpressionValue(cloneReportDetails13, "getCloneReportDetails(...)");
                    openItem(cloneReportDetails13);
                    return;
                } else {
                    return;
                }
            }
        }
        showSelectedRowInfo(i2);
    }
    @SuppressLint("ResourceType")
    private  void showSelectedRowInfo(int i2) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_report_selected_row, null);
        LinearLayout linearLayout = inflate.findViewById(R.id.dialog_row_layout);
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        int size = report.getReportLayout().getReportColumns().size();
        for (int i3 = 0; i3 < size; i3++) {
            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            Report report2 = this.report;
            Intrinsics.checkNotNull(report2);
            ReportColumn reportColumn = report2.getReportLayout().getReportColumns().get(i3);
            if (reportColumn.isVisible()) {
                String enConvertedKey = reportColumn.getTranslation() != null ? reportColumn.getTranslation().getEnConvertedKey() : reportColumn.getFieldName();
                TextView dialogTextView = getDialogTextView(true);
                TextView dialogTextView2 = getDialogTextView(false);
                dialogTextView.setText(getColumnText(i3));
                ArrayList<GenericData> arrayList = this.data;
                Intrinsics.checkNotNull(arrayList);
                Iterator<GenericDataPrimitive> it = arrayList.get(i2).getGenericDataPrimitives().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    GenericDataPrimitive next = it.next();
                    if (StringsKt.equals(next.getName(), enConvertedKey, true)) {
                        Intrinsics.checkNotNull(reportColumn);
                        dialogTextView2.setText(ReportUtil.formatReportColumnValue(reportColumn, next.getValue().toString()));
                        break;
                    }
                }
                linearLayout2.addView(dialogTextView);
                linearLayout2.addView(dialogTextView2);
                linearLayout.addView(linearLayout2);
            }
        }
        getMAlertDialogBuilder().setView(inflate).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i4) {
                UserReportsActivity.showSelectedRowInfolambda20(dialogInterface, i4);
            }
        }).create().show();
    }
    public static final void showSelectedRowInfolambda20(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        dialogInterface.dismiss();
    }

    private  TextView getDialogTextView(boolean z) {
        float f2;
        TextView textView = new TextView(this);
        if (z) {
            textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            f2 = 0.4f;
        } else {
            f2 = 0.6f;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, f2);
        layoutParams.setMargins(10, 4, 10, 4);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setPadding(6, 4, 6, 4);
        return textView;
    }

    public  String getColumnText(int i2) {
        Report report = this.report;
        Intrinsics.checkNotNull(report);
        ReportColumn reportColumn = report.getReportLayout().getReportColumns().get(i2);
        if (reportColumn.getTranslation() != null) {
            SharedPreferencesHelper sharedPreferencesHelper = this.mSharedPreferencesHelper;
            Intrinsics.checkNotNull(sharedPreferencesHelper);
            String applicationLanguage = sharedPreferencesHelper.getApplicationLanguage();
            if (applicationLanguage != null) {
                int hashCode = applicationLanguage.hashCode();
                if (hashCode != 3241) {
                    if (hashCode != 3651) {
                        if (hashCode == 3710 && applicationLanguage.equals("tr")) {
                            String tr = reportColumn.getTranslation().getTr();
                            Intrinsics.checkNotNullExpressionValue(tr, "getTr(...)");
                            return tr;
                        }
                    } else if (applicationLanguage.equals("ru")) {
                        String ru = reportColumn.getTranslation().getRu();
                        Intrinsics.checkNotNullExpressionValue(ru, "getRu(...)");
                        return ru;
                    }
                } else if (applicationLanguage.equals("en")) {
                    String en = reportColumn.getTranslation().getEn();
                    Intrinsics.checkNotNullExpressionValue(en, "getEn(...)");
                    return en;
                }
            }
            Intrinsics.checkNotNull(reportColumn);
            return getColumnEditorFieldName(reportColumn);
        }
        Intrinsics.checkNotNull(reportColumn);
        return getColumnEditorFieldName(reportColumn);
    }

    private  String getColumnEditorFieldName(ReportColumn reportColumn) {
        String str;
        if (reportColumn.getColumnEditName() != null) {
            String columnEditName = reportColumn.getColumnEditName();
            Intrinsics.checkNotNullExpressionValue(columnEditName, "getColumnEditName(...)");
            if (columnEditName.length() > 0) {
                str = reportColumn.getColumnEditName();
                Intrinsics.checkNotNull(str);
                return str;
            }
        }
        str = reportColumn.getFieldName();
        Intrinsics.checkNotNull(str);
        return str;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
