package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.model.SpinnerItem;
import com.proje.mobilesales.features.reports.adapter.ReportCollectionsListAdapter;
import com.proje.mobilesales.features.reports.model.AvgCalcItem;
import com.proje.mobilesales.features.reports.model.enums.ReportDebitFilterType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class ReportWcfCollectionsListActivity extends BaseReportWcfActivity {
    private ReportCollectionsListAdapter adapter;
    private ListView dataList;
    private AppCompatImageButton imageButtonSearchUser;
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWcfCollectionsListActivityExternalSyntheticLambda0
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda6;
            itemLongClickListenerlambda6 = itemLongClickListenerlambda6(ReportWcfCollectionsListActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda6;
        }
    };
    private LinearLayout linearFilterDiv;
    private Menu menu;
    private LinearLayout progressListData;
    private LinearLayout progressSpinUser;
    private REPORTXML reportxml;
    private ScreenControl screenControl;
    private AppCompatSpinner spinnerClose;
    private AppCompatTextView txtCalAmount;
    private AppCompatTextView txtCalDate;
    private AppCompatTextView txtCalDay;
    private int userType;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_CUSTOMER_REF = ReportWcfCollectionsListActivity.class.getName() + ".CUSTOMER_REF";
    private static final String STATE_CUSTOMER_OPERATION = ReportWcfCollectionsListActivity.class.getName() + ".CUSTOMER_OPERATION";

    public LinearLayout getLinearFilterDiv() {
        return linearFilterDiv;
    }

    public void setLinearFilterDiv(final LinearLayout linearLayout) {
        linearFilterDiv = linearLayout;
    }
    public ListView m1475getDataList() {
        return dataList;
    }

    public void setDataList(final ListView listView) {
        dataList = listView;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(final int i2) {
        userType = i2;
    }

    public ReportCollectionsListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final ReportCollectionsListAdapter reportCollectionsListAdapter) {
        adapter = reportCollectionsListAdapter;
    }

    public REPORTXML getReportxml() {
        return reportxml;
    }

    public void setReportxml(final REPORTXML reportxml) {
        this.reportxml = reportxml;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint("SetTextI18n")
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.report_collections_list);
        this.setToolbar();
        tvDate1 = this.findViewById(R.id.tvDate1);
        tvDate2 = this.findViewById(R.id.tvDate2);
        linearFilterDiv = this.findViewById(R.id.linearFilterDiv);
        linearDate1 = this.findViewById(R.id.linearDate1);
        linearDate2 = this.findViewById(R.id.linearDate2);
        spinnerClose = this.findViewById(R.id.spClose);
        progressSpinUser = this.findViewById(R.id.linearProgressSpin);
        final LinearLayout linearLayout = this.findViewById(R.id.linearProgress);
        progressListData = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        imageButtonSearchUser = this.findViewById(R.id.imgList);
        final ListView listView = this.findViewById(R.id.lvReportOrder);
        dataList = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnItemLongClickListener(itemLongClickListener);
        final AppCompatTextView appCompatTextView = this.findViewById(R.id.txtViewCalDate);
        txtCalDate = appCompatTextView;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(this.getString(R.string.str_date) + ": ");
        final AppCompatTextView appCompatTextView2 = this.findViewById(R.id.txtViewCalDay);
        txtCalDay = appCompatTextView2;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(this.getString(R.string.str_day) + ": 0");
        final AppCompatTextView appCompatTextView3 = this.findViewById(R.id.txtViewCalTotal);
        txtCalAmount = appCompatTextView3;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setText(this.getString(R.string.str_total_quantity) + ": 0");
        final AppCompatImageButton appCompatImageButton = imageButtonSearchUser;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this);
        final LinearLayout linearLayout2 = linearDate1;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(this);
        final LinearLayout linearLayout3 = linearDate2;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setOnClickListener(this);
        this.setSpin();
        this.getExtras();
        this.setClRef(customerRef);
        this.setType(this.CustomerToType(customerOperation.getMenuType()));
        final Window window = this.getWindow();
        window.clearFlags(2048);
        window.clearFlags(2048);
        window.addFlags(1024);
        screenControl = new ScreenControl(this);
        this.setMyCalendar(Calendar.getInstance());
        this.setClientForTiger(new ServicesClientForTiger(this));
        final ScreenControl screenControl = this.screenControl;
        Intrinsics.checkNotNull(screenControl);
        screenControl.isFullScreen = true;
        this.initDateTxtView();
        this.getDataList();
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
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
            linearLayout.setVisibility(8);
        } else {
            final LinearLayout linearLayout2 = linearFilterDiv;
            Intrinsics.checkNotNull(linearLayout2);
            linearLayout2.setVisibility(0);
        }
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity
    public void initDateTxtView() {
        final Date time;
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final AppCompatTextView appCompatTextView = tvDate2;
        if (null != appCompatTextView) {
            final Calendar myCalendar = this.getMyCalendar();
            appCompatTextView.setText((null == myCalendar || null == (time = myCalendar.getTime())) ? null : simpleDateFormat.format(time));
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5));
        final AppCompatTextView appCompatTextView2 = tvDate1;
        if (null == appCompatTextView2) {
            return;
        }
        appCompatTextView2.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity
    public String getDateTxtView(final boolean z) {
        List emptyList;
        final String[] strArr;
        List emptyList2;
        if (z) {
            final AppCompatTextView appCompatTextView = tvDate1;
            Intrinsics.checkNotNull(appCompatTextView);
            final List<String> split = new Regex("/").split(appCompatTextView.getText().toString(), 0);
            if (!split.isEmpty()) {
                final ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (0 != listIterator.previous().length()) {
                        emptyList2 = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList2 = CollectionsKt.emptyList();
            strArr = (String[]) emptyList2.toArray(new String[0]);
        } else {
            final AppCompatTextView appCompatTextView2 = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView2);
            final List<String> split2 = new Regex("/").split(appCompatTextView2.getText().toString(), 0);
            if (!split2.isEmpty()) {
                final ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                while (listIterator2.hasPrevious()) {
                    if (0 != listIterator2.previous().length()) {
                        emptyList = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            strArr = (String[]) emptyList.toArray(new String[0]);
        }
        final int parseInt = Integer.parseInt(strArr[0]);
        final int parseInt2 = Integer.parseInt(strArr[1]);
        final int parseInt3 = Integer.parseInt(strArr[2]);
        if (z) {
            final String dateTime = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 0, 0, 0, 0);
            Intrinsics.checkNotNullExpressionValue(dateTime, "getDateTime(...)");
            return dateTime;
        }
        final String dateTime2 = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 23, 59, 59, 599);
        Intrinsics.checkNotNullExpressionValue(dateTime2, "getDateTime(...)");
        return dateTime2;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(0);
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        if (processType == ProcessType.FILLREPORTCOLLECTIONSLIST) {
            this.fillDataList(reportxml);
        } else if (null != reportxml) {
            final List<REPORTLINE> reportLines = reportxml.reportLines;
            Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
            this.fillDateDataList(reportLines);
        }
    }

    private void fillDataList(final REPORTXML reportxml) {
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            this.reportxml = reportxml;
            if (0 < reportxml.reportLines.size()) {
                final ReportCollectionsListAdapter reportCollectionsListAdapter = adapter;
                if (null == reportCollectionsListAdapter) {
                    if (ReportDebitFilterType.UNCOVERED_CLOSE == getDebitFilterValue()) {
                        this.updateLinesForUncovered(reportxml);
                    }
                    final List<REPORTLINE> reportLines = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                    this.setDateReportLine(reportLines);
                    final List<REPORTLINE> reportLines2 = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
                    adapter = new ReportCollectionsListAdapter(this, reportLines2);
                    final ListView listView = dataList;
                    Intrinsics.checkNotNull(listView);
                    listView.setAdapter(adapter);
                    final ReportCollectionsListAdapter reportCollectionsListAdapter2 = adapter;
                    Intrinsics.checkNotNull(reportCollectionsListAdapter2);
                    reportCollectionsListAdapter2.notifyDataSetChanged();
                    this.getDateDataList();
                } else {
                    Intrinsics.checkNotNull(reportCollectionsListAdapter);
                    reportCollectionsListAdapter.notifyDataSetChanged();
                }
            } else {
                final ListView listView2 = dataList;
                Intrinsics.checkNotNull(listView2);
                listView2.setAdapter(null);
            }
        } else {
            final ListView listView3 = dataList;
            Intrinsics.checkNotNull(listView3);
            listView3.setAdapter(null);
        }
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final ListView listView4 = dataList;
        Intrinsics.checkNotNull(listView4);
        listView4.setEnabled(true);
    }

    private void updateLinesForUncovered(final REPORTXML reportxml) {
        final ArrayList arrayList = new ArrayList();
        final List<REPORTLINE> reportLines = reportxml.reportLines;
        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
        final GroupedLineList groupReportLines = this.groupReportLines(reportLines);
        for (final REPORTLINE reportline : groupReportLines.getDebitLines()) {
            float convertStringToFloat = StringUtils.convertStringToFloat(reportline.f1203A);
            final float convertStringToFloat2 = StringUtils.convertStringToFloat(reportline.f1203A);
            if (0.0f < groupReportLines.getTotalCredit()) {
                convertStringToFloat -= groupReportLines.getTotalCredit();
                groupReportLines.setTotalCredit(groupReportLines.getTotalCredit() - convertStringToFloat2);
            }
            if (0.0f < convertStringToFloat) {
                reportline.f1203A = String.valueOf(convertStringToFloat);
                arrayList.add(reportline);
            }
        }
        reportxml.reportLines = arrayList;
    }

    private GroupedLineList groupReportLines(final List<? extends REPORTLINE> list) {
        final GroupedLineList groupedLineList = new GroupedLineList();
        for (final REPORTLINE reportline : list) {
            if (0 == reportline.SIGN) {
                groupedLineList.getDebitLines().add(reportline);
            } else {
                groupedLineList.setTotalCredit(groupedLineList.getTotalCredit() + StringUtils.convertStringToFloat(reportline.f1203A));
                groupedLineList.getCreditLines().add(reportline);
            }
        }
        return groupedLineList;
    }

    private void fillDateDataList(final List<? extends REPORTLINE> list) {
        if (list.isEmpty()) {
            return;
        }
        this.setAvgDate(list);
    }

    private Unit getDateDataList() {
        this.setSelectResult(this.getReportWcfQueriesViewModel().getNotPaymentInvoice(this.getClRef(), this.getDateTxtView(true), this.getDateTxtView(false), this.getDebitFilterValue()));
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.resultXML = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()));
        ServicesClientForTiger.ReportRetrieve retrieve = getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
        return Unit.INSTANCE;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, android.view.View.OnClickListener
    @SuppressLint({"SetTextI18n"})
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.linearDate1) {
            showDatePicker(true);
            return;
        }
        if (view != null && view.getId() == R.id.linearDate2) {
            showDatePicker(false);
            return;
        }
        if (view == null || view.getId() != R.id.imgList) {
            return;
        }
        this.adapter = null;
        ListView listView = this.dataList;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(null);
        AppCompatTextView appCompatTextView = this.txtCalDate;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(getString(R.string.str_date) + ": ");
        AppCompatTextView appCompatTextView2 = this.txtCalDay;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(getString(R.string.str_day) + ": 0");
        AppCompatTextView appCompatTextView3 = this.txtCalAmount;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setText(getString(R.string.str_total_quantity) + ": 0");
        if (!validateDates()) {
            Toast.makeText(this, getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
        } else {
            getDataList();
        }
    }

    private void setDateReportLine(List<? extends REPORTLINE> list) {
        float convertStringToFloat;
        List emptyList;
        List emptyList2;
        float f2 = 0.0f;
        for (REPORTLINE reportline : list) {
            if (reportline.SIGN == 1) {
                convertStringToFloat = StringUtils.convertStringToFloat(reportline.f1203A) * (-1);
            } else {
                convertStringToFloat = StringUtils.convertStringToFloat(reportline.f1203A);
            }
            f2 += convertStringToFloat;
            reportline.f1205K = StringUtils.fFormat(f2);
            String A = reportline.f1203A;
            Intrinsics.checkNotNullExpressionValue(A, "A");
            reportline.f1203A = StringUtils.fFormat(A);
            String Y = reportline.f1209Y;
            Intrinsics.checkNotNullExpressionValue(Y, "Y");
            List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(Y, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt.emptyList();
            reportline.f1209Y = DateAndTimeUtils.convertDateY(((String[]) emptyList.toArray(new String[0]))[0]);
            String Z = reportline.f1210Z;
            Intrinsics.checkNotNullExpressionValue(Z, "Z");
            List<String> split2 = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(Z, 0);
            if (!split2.isEmpty()) {
                ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                while (listIterator2.hasPrevious()) {
                    if (listIterator2.previous().length() != 0) {
                        emptyList2 = CollectionsKt.take(split2, listIterator2.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList2 = CollectionsKt.emptyList();
            reportline.f1210Z = DateAndTimeUtils.convertDateY(((String[]) emptyList2.toArray(new String[0]))[0]);
        }
    }

    private ReportDebitFilterType getDebitFilterValue() {
        ReportDebitFilterType.Companion companion = ReportDebitFilterType.Companion;
        AppCompatSpinner appCompatSpinner = this.spinnerClose;
        Intrinsics.checkNotNull(appCompatSpinner);
        Object selectedItem = appCompatSpinner.getSelectedItem();
        Intrinsics.checkNotNull(selectedItem, "null cannot be cast to non-null type com.proje.mobilesales.features.model.SpinnerItem");
        String logicalRef = ((SpinnerItem) selectedItem).logicalRef;
        Intrinsics.checkNotNullExpressionValue(logicalRef, "logicalRef");
        return companion.fromReportDebitFilterType(Integer.parseInt(logicalRef));
    }

    private void getDataList() {
        setSelectResult(getReportWcfQueriesViewModel().getCollectionsListQuery(getClRef(), getDateTxtView(true), getDateTxtView(false), getDebitFilterValue()));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.resultXML = "";
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        setRetrieve(clientForTiger.new ServicesClientForTiger.ReportRetrieve(this.getSelectResult()));
        final ServicesClientForTiger.ReportRetrieve retrieve = this.getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }

    private void setSpin() {
        final ArrayList arrayList = new ArrayList();
        final SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.logicalRef = "0";
        spinnerItem.value = this.getString(R.string.str_no_proceeding);
        final SpinnerItem spinnerItem2 = new SpinnerItem();
        spinnerItem2.logicalRef = BuildConfig.NETSIS_DEMO_PASSWORD;
        spinnerItem2.value = this.getString(R.string.str_close_outstandings);
        arrayList.add(spinnerItem);
        arrayList.add(spinnerItem2);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner = spinnerClose;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(arrayAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void setAvgDate(final List<? extends REPORTLINE> list) {
        List emptyList;
        double d2;
        final ArrayList arrayList = new ArrayList();
        for (final REPORTLINE reportline : list) {
            final String DATE_ = reportline.DATE_;
            if (null != DATE_) {
                Intrinsics.checkNotNullExpressionValue(DATE_, "DATE_");
                final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(DATE_, 0);
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
                final String str = ((String[]) emptyList.toArray(new String[0]))[0];
                if (1 == reportline.SIGN) {
                    final String TOTAL = reportline.TOTAL;
                    Intrinsics.checkNotNullExpressionValue(TOTAL, "TOTAL");
                    d2 = StringUtils.toDouble(TOTAL) * (-1);
                } else {
                    final String TOTAL2 = reportline.TOTAL;
                    Intrinsics.checkNotNullExpressionValue(TOTAL2, "TOTAL");
                    d2 = StringUtils.toDouble(TOTAL2);
                }
                arrayList.add(new AvgCalcItem(str, d2));
            }
        }
        final HashMap<String, String> calculateDateForCollectionList = DateAndTimeUtils.calculateDateForCollectionList(arrayList);
        final AppCompatTextView appCompatTextView = txtCalDate;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(this.getString(R.string.str_date) + ": " + calculateDateForCollectionList.get("date"));
        final AppCompatTextView appCompatTextView2 = txtCalDay;
        Intrinsics.checkNotNull(appCompatTextView2);
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getString(R.string.str_day));
        sb.append(": ");
        final String str2 = calculateDateForCollectionList.get("day");
        Intrinsics.checkNotNull(str2);
        sb.append(StringUtils.formatDouble(str2));
        appCompatTextView2.setText(sb.toString());
        final AppCompatTextView appCompatTextView3 = txtCalAmount;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setText(this.getString(R.string.str_total_quantity) + ": " + calculateDateForCollectionList.get("total"));
    }

    
    public static boolean itemLongClickListenerlambda6(final ReportWcfCollectionsListActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportxml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        this0.showDetail(reportline);
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void showDetail(final REPORTLINE reportline) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_collections_list_column);
        final AppCompatTextView appCompatTextView = dialog.findViewById(R.id.tvFicheType);
        final AppCompatTextView appCompatTextView2 = dialog.findViewById(R.id.tvDueDate);
        final AppCompatTextView appCompatTextView3 = dialog.findViewById(R.id.tvProcessDate);
        final AppCompatTextView appCompatTextView4 = dialog.findViewById(R.id.tvViewDue);
        final AppCompatTextView appCompatTextView5 = dialog.findViewById(R.id.tvRecordNo);
        final AppCompatTextView appCompatTextView6 = dialog.findViewById(R.id.tvFicheNo);
        final AppCompatTextView appCompatTextView7 = dialog.findViewById(R.id.tvAmount);
        final AppCompatTextView appCompatTextView8 = dialog.findViewById(R.id.tvBalance);
        appCompatTextView.setText(reportline.f1208X);
        appCompatTextView2.setText(reportline.f1209Y);
        appCompatTextView3.setText(reportline.f1210Z);
        appCompatTextView4.setText(reportline.f1206T);
        appCompatTextView5.setText(reportline.f1204B);
        appCompatTextView6.setText(reportline.FICHENO);
        appCompatTextView7.setText(reportline.f1203A);
        if (baseErp.isHideCustomerBalance()) {
            appCompatTextView8.setText("-");
        } else {
            appCompatTextView8.setText(reportline.f1205K);
        }
        dialog.show();
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.finish();
            return true;
        }
        if (R.id.reportFullScreen == itemId) {
            this.setScreen();
            return true;
        }
        if (R.id.reportRefresh == itemId) {
            this.getDataList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* compiled from: ReportWcfCollectionsListActivity.kt */
    public final class GroupedLineList {
        private float totalCredit;
        private List<REPORTLINE> debitLines = new ArrayList();
        private List<REPORTLINE> creditLines = new ArrayList();

        public GroupedLineList() {
        }

        public List<REPORTLINE> getDebitLines() {
            return debitLines;
        }

        public void setDebitLines(final List<REPORTLINE> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            debitLines = list;
        }

        public List<REPORTLINE> getCreditLines() {
            return creditLines;
        }

        public void setCreditLines(final List<REPORTLINE> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            creditLines = list;
        }

        public float getTotalCredit() {
            return totalCredit;
        }

        public void setTotalCredit(final float f2) {
            totalCredit = f2;
        }
    }

    /* compiled from: ReportWcfCollectionsListActivity.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static void getSTATE_CUSTOMER_OPERATIONannotations() {
        }

        private static void getSTATE_CUSTOMER_REFannotations() {
        }

        private Companion() {
        }
    }
}
