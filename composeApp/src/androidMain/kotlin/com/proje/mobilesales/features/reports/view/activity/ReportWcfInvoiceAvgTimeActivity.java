package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.SpinnerItem;
import com.proje.mobilesales.features.reports.adapter.ReportInvoiceAvgAdapter;
import com.proje.mobilesales.features.reports.model.AvgCalcItem;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PrimitiveCompanionObjects;
import kotlin.text.Regex;

public final class ReportWcfInvoiceAvgTimeActivity extends BaseReportWcfActivity {
    private ReportInvoiceAvgAdapter adapter;
    private AppCompatImageButton btnCalAvgInvoice;
    private ListView dataList;
    private AppCompatImageButton imageButtonSearchUser;
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWcfInvoiceAvgTimeActivityExternalSyntheticLambda0
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda3;
            itemLongClickListenerlambda3 = itemLongClickListenerlambda3(ReportWcfInvoiceAvgTimeActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda3;
        }
    };
    private Calendar myCalender;
    private LinearLayout progressListData;
    private LinearLayout progressSpinUser;
    private REPORTXML reportxml;
    private int spinSelectLogicalRef;
    private AppCompatSpinner spinnerUser;
    private Menu threeDotMenu;
    private AppCompatTextView txtCalAmount;
    private AppCompatTextView txtCalDate;
    private AppCompatTextView txtCalDay;
    private int userType;
    public ListView m1476getDataList() {
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

    public int getSpinSelectLogicalRef() {
        return spinSelectLogicalRef;
    }

    public void setSpinSelectLogicalRef(final int i2) {
        spinSelectLogicalRef = i2;
    }

    public ReportInvoiceAvgAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter) {
        adapter = reportInvoiceAvgAdapter;
    }

    public REPORTXML getReportxml() {
        return reportxml;
    }

    public void setReportxml(final REPORTXML reportxml) {
        this.reportxml = reportxml;
    }
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.order_avg);
        this.setToolbar();
        final View findViewById = this.findViewById(R.id.tvDate1);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate1 = (AppCompatTextView) findViewById;
        final View findViewById2 = this.findViewById(R.id.tvDate2);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate2 = (AppCompatTextView) findViewById2;
        final View findViewById3 = this.findViewById(R.id.linearDate1);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate1 = (LinearLayout) findViewById3;
        final View findViewById4 = this.findViewById(R.id.linearDate2);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate2 = (LinearLayout) findViewById4;
        final View findViewById5 = this.findViewById(R.id.spUser);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spinnerUser = (AppCompatSpinner) findViewById5;
        final View findViewById6 = this.findViewById(R.id.linearProgressSpin);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.LinearLayout");
        progressSpinUser = (LinearLayout) findViewById6;
        final View findViewById7 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.LinearLayout");
        final LinearLayout linearLayout = (LinearLayout) findViewById7;
        progressListData = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final View findViewById8 = this.findViewById(R.id.imgList);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        imageButtonSearchUser = (AppCompatImageButton) findViewById8;
        final View findViewById9 = this.findViewById(R.id.lvReportOrder);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById9;
        dataList = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnItemLongClickListener(itemLongClickListener);
        final View findViewById10 = this.findViewById(R.id.txtViewCalDate);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        txtCalDate = (AppCompatTextView) findViewById10;
        final View findViewById11 = this.findViewById(R.id.txtViewCalDay);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        txtCalDay = (AppCompatTextView) findViewById11;
        final View findViewById12 = this.findViewById(R.id.txtViewCalTotal);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        txtCalAmount = (AppCompatTextView) findViewById12;
        final View findViewById13 = this.findViewById(R.id.btnCalAvgInv);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        btnCalAvgInvoice = (AppCompatImageButton) findViewById13;
        final AppCompatImageButton appCompatImageButton = imageButtonSearchUser;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this);
        final LinearLayout linearLayout2 = linearDate1;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(this);
        final LinearLayout linearLayout3 = linearDate2;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setOnClickListener(this);
        final AppCompatImageButton appCompatImageButton2 = btnCalAvgInvoice;
        Intrinsics.checkNotNull(appCompatImageButton2);
        appCompatImageButton2.setOnClickListener(this);
        myCalender = Calendar.getInstance();
        this.setClientForTiger(new ServicesClientForTiger(this));
        this.clearAvgTotalDateDay();
        this.getSpinUser();
        this.initDateTxtView();
        this.getExtras();
        this.setClRef(customerRef);
        this.setType(this.CustomerToType(customerOperation.getMenuType()));
    }
    public void onClick(final View view) {
        final Integer valueOf = null != view ? Integer.valueOf(view.getId()) : null;
        if (null != valueOf && R.id.linearDate1 == valueOf.intValue()) {
            this.showDatePicker(true);
            return;
        }
        if (null != valueOf && R.id.linearDate2 == valueOf.intValue()) {
            this.showDatePicker(false);
            return;
        }
        if (null != valueOf && R.id.imgList == valueOf.intValue()) {
            adapter = null;
            final ListView listView = dataList;
            Intrinsics.checkNotNull(listView);
            listView.setAdapter(null);
            this.clearAvgTotalDateDay();
            if (!this.validateDates()) {
                Toast.makeText(this, this.getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
                return;
            } else {
                this.getDataList();
                return;
            }
        }
        if (null != valueOf && R.id.btnCalAvgInv == valueOf.intValue()) {
            this.getAvgLine();
        }
    }
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
        if (!z) {
            final String dateTime = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 23, 59, 59, 0);
            Intrinsics.checkNotNullExpressionValue(dateTime, "getDateTime(...)");
            return dateTime;
        }
        final String dateTime2 = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 0, 0, 0, 0);
        Intrinsics.checkNotNullExpressionValue(dateTime2, "getDateTime(...)");
        return dateTime2;
    }

    private int getSelectedSpinUserLogicalRef() {
        final AppCompatSpinner appCompatSpinner = spinnerUser;
        Intrinsics.checkNotNull(appCompatSpinner);
        final Object selectedItem = appCompatSpinner.getSelectedItem();
        Intrinsics.checkNotNull(selectedItem, "null cannot be cast to non-null type com.proje.mobilesales.features.model.SpinnerItem");
        return StringUtils.convertStringToInt(((SpinnerItem) selectedItem).logicalRef);
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        threeDotMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            final LinearLayout linearLayout = progressListData;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(0);
            final ListView listView = dataList;
            Intrinsics.checkNotNull(listView);
            listView.setEnabled(false);
        }
    }

    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            if (processType == ProcessType.FILLREPORTAVGCALC) {
                this.fillAvgDataList(reportxml);
                return;
            } else {
                this.fillDataList(reportxml);
                return;
            }
        }
        this.fillSpinnerUsers(reportxml);
    }

    private Unit getSpinUser() {
        final String str;
        final String type = this.getReportWcfQueriesViewModel().getBaseErp().getUser().getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        final int parseInt = Integer.parseInt(type);
        userType = parseInt;
        if (2 == parseInt) {
            str = "";
        } else {
            str = this.getReportWcfQueriesViewModel().getBaseErp().getUser().getCode();
        }
        final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
        Intrinsics.checkNotNull(str);
        final int i2 = userType;
        final String[] salesManagerSalesmanFilter = this.getReportWcfQueriesViewModel().getBaseErp().getSalesManagerSalesmanFilter();
        Intrinsics.checkNotNullExpressionValue(salesManagerSalesmanFilter, "getSalesManagerSalesmanFilter(...)");
        this.setSelectResult(reportWcfQueriesViewModel.getSalesmans(str, i2, salesManagerSalesmanFilter));
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
        AppCompatSpinner appCompatSpinner = this.spinnerUser;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWcfInvoiceAvgTimeActivityspinUser1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j2) {
                if (view == null) {
                    return;
                }
                ReportWcfInvoiceAvgTimeActivity.this.setAdapter(null);
                ReportWcfInvoiceAvgTimeActivity.this.clearAvgTotalDateDay();
                ReportWcfInvoiceAvgTimeActivity.this.getDataList();
            }
        });
        return Unit.INSTANCE;
    }

    
    public void clearAvgTotalDateDay() {
        AppCompatTextView appCompatTextView = this.txtCalDate;
        Intrinsics.checkNotNull(appCompatTextView);
        PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
        String format = String.format("%s: ", Arrays.copyOf(new Object[]{getString(R.string.str_date)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        appCompatTextView.setText(format);
        AppCompatTextView appCompatTextView2 = this.txtCalDay;
        Intrinsics.checkNotNull(appCompatTextView2);
        String format2 = String.format("%s: 0", Arrays.copyOf(new Object[]{getString(R.string.str_day)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        appCompatTextView2.setText(format2);
        AppCompatTextView appCompatTextView3 = this.txtCalAmount;
        Intrinsics.checkNotNull(appCompatTextView3);
        String format3 = String.format("%s: 0", Arrays.copyOf(new Object[]{getString(R.string.str_total_quantity)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        appCompatTextView3.setText(format3);
    }

    private void fillSpinnerUsers(REPORTXML reportxml) {
        this.reportxml = reportxml;
        ArrayList arrayList = new ArrayList();
        SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.logicalRef = "0";
        spinnerItem.value = '<' + getString(R.string.str_salesman) + '(' + getString(R.string.str_all) + ")>";
        arrayList.add(spinnerItem);
        if ((reportxml != null ? reportxml.reportLines : null) != null) {
            int size = reportxml.reportLines.size();
            for (int i2 = 0; i2 < size; i2++) {
                SpinnerItem spinnerItem2 = new SpinnerItem();
                spinnerItem2.logicalRef = reportxml.reportLines.get(i2).f1208X;
                spinnerItem2.value = reportxml.reportLines.get(i2).f1209Y;
                arrayList.add(spinnerItem2);
            }
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        AppCompatSpinner appCompatSpinner = this.spinnerUser;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(arrayAdapter);
        LinearLayout linearLayout = this.progressSpinUser;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(4);
        if (this.userType != 2) {
            arrayList.remove(0);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    
    public void getDataList() {
        ReportWcfQueriesViewModel reportWcfQueriesViewModel = getReportWcfQueriesViewModel();
        String dateTxtView = getDateTxtView(true);
        String dateTxtView2 = getDateTxtView(false);
        int selectedSpinUserLogicalRef = getSelectedSpinUserLogicalRef();
        ProcessType type = getType();
        Intrinsics.checkNotNull(type);
        setSelectResult(reportWcfQueriesViewModel.getOrderInvoice(0, dateTxtView, dateTxtView2, selectedSpinUserLogicalRef, type, getClRef()));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.resultXML = "";
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        setRetrieve(clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()));
        ServicesClientForTiger.ReportRetrieve retrieve = getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }

    private Unit getAvgLine() {
        ArrayList<Integer> arrayList;
        ReportInvoiceAvgAdapter reportInvoiceAvgAdapter = this.adapter;
        if (reportInvoiceAvgAdapter != null) {
            Intrinsics.checkNotNull(reportInvoiceAvgAdapter);
            arrayList = reportInvoiceAvgAdapter.getCheckedReportLine();
        } else {
            arrayList = new ArrayList<>();
        }
        if (!arrayList.isEmpty()) {
            setSelectResult(getReportWcfQueriesViewModel().getInvoiceAvgLinePayPlan(arrayList));
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
        } else {
            this.clearAvgTotalDateDay();
            Toast.makeText(this.getApplicationContext(), this.getString(R.string.str_question_make_choice), 0).show();
        }
        return Unit.INSTANCE;
    }

    private void fillAvgDataList(final REPORTXML reportxml) {
        if (null != (reportxml != null ? reportxml.reportLines : null) && 0 < reportxml.reportLines.size()) {
            final ArrayList arrayList = new ArrayList();
            for (final REPORTLINE reportline : reportxml.reportLines) {
                final String str = reportline.f1209Y;
                final String TOTAL = reportline.TOTAL;
                Intrinsics.checkNotNullExpressionValue(TOTAL, "TOTAL");
                arrayList.add(new AvgCalcItem(str, StringUtils.toDouble(TOTAL)));
            }
            final HashMap<String, String> calculateDate = DateAndTimeUtils.calculateDate(arrayList);
            final AppCompatTextView appCompatTextView = txtCalDate;
            Intrinsics.checkNotNull(appCompatTextView);
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_date), calculateDate.get("date")}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
            final AppCompatTextView appCompatTextView2 = txtCalDay;
            Intrinsics.checkNotNull(appCompatTextView2);
            final String format2 = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_day), calculateDate.get("day")}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            appCompatTextView2.setText(format2);
            final AppCompatTextView appCompatTextView3 = txtCalAmount;
            Intrinsics.checkNotNull(appCompatTextView3);
            final String format3 = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_total_quantity), calculateDate.get("total")}, 2));
            Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
            appCompatTextView3.setText(format3);
        }
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final ListView listView = dataList;
        Intrinsics.checkNotNull(listView);
        listView.setEnabled(true);
    }

    private void fillDataList(final REPORTXML reportxml) {
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            if (0 < reportxml.reportLines.size()) {
                final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter = adapter;
                if (null == reportInvoiceAvgAdapter) {
                    final List<REPORTLINE> reportLines = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                    this.setDateReportLine(reportLines);
                    final List<REPORTLINE> reportLines2 = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
                    adapter = new ReportInvoiceAvgAdapter(this, reportLines2);
                    final ListView listView = dataList;
                    Intrinsics.checkNotNull(listView);
                    listView.setAdapter(adapter);
                    final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter2 = adapter;
                    Intrinsics.checkNotNull(reportInvoiceAvgAdapter2);
                    reportInvoiceAvgAdapter2.notifyDataSetChanged();
                } else {
                    Intrinsics.checkNotNull(reportInvoiceAvgAdapter);
                    reportInvoiceAvgAdapter.notifyDataSetChanged();
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

    private void setDateReportLine(final List<? extends REPORTLINE> list) {
        List emptyList;
        for (final REPORTLINE reportline : list) {
            final String DATE_ = reportline.DATE_;
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
            reportline.DATE_ = ((String[]) emptyList.toArray(new String[0]))[0];
        }
    }

    private void setAvgDate() {
        final ArrayList<REPORTLINE> arrayList = new ArrayList();
        final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter = adapter;
        if (null == reportInvoiceAvgAdapter) {
            return;
        }
        Intrinsics.checkNotNull(reportInvoiceAvgAdapter);
        final int count = reportInvoiceAvgAdapter.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter2 = adapter;
            Intrinsics.checkNotNull(reportInvoiceAvgAdapter2);
            if (reportInvoiceAvgAdapter2.isCheckReportLine(i2)) {
                final ReportInvoiceAvgAdapter reportInvoiceAvgAdapter3 = adapter;
                Intrinsics.checkNotNull(reportInvoiceAvgAdapter3);
                arrayList.add(reportInvoiceAvgAdapter3.getReportLine(i2));
            }
        }
        if (!arrayList.isEmpty()) {
            final ArrayList arrayList2 = new ArrayList();
            for (final REPORTLINE reportline : arrayList) {
                final String str = reportline.DATE_;
                final String NETTOTAL = reportline.NETTOTAL;
                Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
                arrayList2.add(new AvgCalcItem(str, StringUtils.toDouble(NETTOTAL)));
            }
            final HashMap<String, String> calculateDate = DateAndTimeUtils.calculateDate(arrayList2);
            final AppCompatTextView appCompatTextView = txtCalDate;
            Intrinsics.checkNotNull(appCompatTextView);
            final PrimitiveCompanionObjects primitiveCompanionObjects = PrimitiveCompanionObjects.INSTANCE;
            final String format = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_date), calculateDate.get("date")}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            appCompatTextView.setText(format);
            final AppCompatTextView appCompatTextView2 = txtCalDay;
            Intrinsics.checkNotNull(appCompatTextView2);
            final String format2 = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_day), calculateDate.get("day")}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            appCompatTextView2.setText(format2);
            final AppCompatTextView appCompatTextView3 = txtCalAmount;
            Intrinsics.checkNotNull(appCompatTextView3);
            final String format3 = String.format("%s:%s", Arrays.copyOf(new Object[]{this.getString(R.string.str_total_quantity), calculateDate.get("total")}, 2));
            Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
            appCompatTextView3.setText(format3);
            return;
        }
        Toast.makeText(this.getApplicationContext(), this.getString(R.string.str_question_make_choice), 0).show();
    }

    
    public static boolean itemLongClickListenerlambda3(final ReportWcfInvoiceAvgTimeActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportxml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        this0.showDetail(reportline);
        return true;
    }

    private void showDetail(final REPORTLINE reportline) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_order_avg_column);
        final View findViewById = dialog.findViewById(R.id.tvDate);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = dialog.findViewById(R.id.tvFicheNo);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = dialog.findViewById(R.id.tvAmount);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = dialog.findViewById(R.id.tvDiscount);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = dialog.findViewById(R.id.tvNetAmount);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = dialog.findViewById(R.id.tvExplanation);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        ((AppCompatTextView) findViewById).setText(reportline.DATE_);
        ((AppCompatTextView) findViewById2).setText(reportline.FICHENO);
        final String GROSSTOTAL = reportline.GROSSTOTAL;
        Intrinsics.checkNotNullExpressionValue(GROSSTOTAL, "GROSSTOTAL");
        ((AppCompatTextView) findViewById3).setText(StringUtils.fFormat(GROSSTOTAL));
        final String TOTALDISCOUNTS = reportline.TOTALDISCOUNTS;
        Intrinsics.checkNotNullExpressionValue(TOTALDISCOUNTS, "TOTALDISCOUNTS");
        ((AppCompatTextView) findViewById4).setText(StringUtils.fFormat(TOTALDISCOUNTS));
        final String NETTOTAL = reportline.NETTOTAL;
        Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
        ((AppCompatTextView) findViewById5).setText(StringUtils.fFormat(NETTOTAL));
        ((AppCompatTextView) findViewById6).setText(reportline.GENEXP1);
        dialog.show();
    }
}
