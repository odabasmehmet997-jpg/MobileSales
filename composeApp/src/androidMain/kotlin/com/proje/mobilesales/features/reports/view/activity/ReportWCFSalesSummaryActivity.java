package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.proje.mobilesales.core.design.ErpCreator;
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
import com.proje.mobilesales.features.reports.adapter.ReportSalesSummaryAdapter;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class ReportWCFSalesSummaryActivity extends BaseReportWcfActivity {
    private ReportSalesSummaryAdapter adapter;
    private ArrayAdapter<SpinnerItem> adapterSpinner;
    private int[] arrVisibility;
    private Bundle bundle;
    private AppCompatImageButton imgList;
    private LinearLayout linearFilterDiv;
    private LinearLayout linearProgress;
    private LinearLayout linearProgressSpin;
    private ListView lvReportOrder;
    private Menu menu;
    private String paramNo;
    private REPORTXML reportSpinXml;
    private REPORTXML reportXml;
    private ScreenControl screenControl;
    private AppCompatSpinner spUser;
    private int spinSelectLogicalRef;
    private AppCompatTextView tvCount;
    private AppCompatTextView tvNetTotal;
    private AppCompatTextView tvTotal;
    private AppCompatTextView tvTotalDiscount;
    private AppCompatTextView tvTotalVat;
    private int userType;
    private String createDateStart = "";
    private String createDateEnd = "";
    private final View.OnClickListener clickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFSalesSummaryActivityclickListener1
        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            final ListView listView;
            final int[] iArr;
            final int[] iArr2;
            final int[] iArr3;
            final int[] iArr4;
            final int[] iArr5;
            Intrinsics.checkNotNullParameter(v, "v");
            int[] iArr6 = null;
            switch (v.getId()) {
                case R.id.imgList /* 2131296890 */:
                    adapter = null;
                    listView = lvReportOrder;
                    Intrinsics.checkNotNull(listView);
                    listView.setAdapter(null);
                    reportXml = null;
                    final AppCompatTextView appCompatTextView = tvDate1;
                    Intrinsics.checkNotNull(appCompatTextView);
                    final String obj = appCompatTextView.getText().toString();
                    final AppCompatTextView appCompatTextView2 = tvDate2;
                    Intrinsics.checkNotNull(appCompatTextView2);
                    if (DateAndTimeUtils.compareDates(obj, appCompatTextView2.getText().toString(), "dd.MM.yyyy")) {
                        getList();
                        break;
                    } else {
                        final ReportWCFSalesSummaryActivity reportWCFSalesSummaryActivity = ReportWCFSalesSummaryActivity.this;
                        Toast.makeText(reportWCFSalesSummaryActivity, reportWCFSalesSummaryActivity.getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
                        break;
                    }
                case R.id.linearDate1 /* 2131296958 */:
                    final ReportWCFSalesSummaryActivity reportWCFSalesSummaryActivity2 = ReportWCFSalesSummaryActivity.this;
                    reportWCFSalesSummaryActivity2.dateIsFirst = true;
                    reportWCFSalesSummaryActivity2.setDate();
                    showDialog(998);
                    break;
                case R.id.linearDate2 /* 2131296959 */:
                    final ReportWCFSalesSummaryActivity reportWCFSalesSummaryActivity3 = ReportWCFSalesSummaryActivity.this;
                    reportWCFSalesSummaryActivity3.dateIsFirst = false;
                    reportWCFSalesSummaryActivity3.setDate();
                    showDialog(999);
                    break;
                case R.id.tvCount /* 2131297813 */:
                    iArr = arrVisibility;
                    if (null == iArr) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr6 = iArr;
                    }
                    iArr6[0] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvNetTotal /* 2131297871 */:
                    iArr2 = arrVisibility;
                    if (null == iArr2) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr6 = iArr2;
                    }
                    iArr6[4] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvTotal /* 2131297925 */:
                    iArr3 = arrVisibility;
                    if (null == iArr3) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr6 = iArr3;
                    }
                    iArr6[1] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvTotalDiscount /* 2131297926 */:
                    iArr4 = arrVisibility;
                    if (null == iArr4) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr6 = iArr4;
                    }
                    iArr6[3] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvTotalVat /* 2131297933 */:
                    iArr5 = arrVisibility;
                    if (null == iArr5) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr6 = iArr5;
                    }
                    iArr6[2] = 0;
                    setColumnVisibility();
                    break;
            }
        }
    };

    public String getParamNo() {
        return paramNo;
    }

    public void setParamNo(final String str) {
        paramNo = str;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        if (null != bundle) {
            this.restoreActivityBundle(bundle);
        }
        this.setContentView(R.layout.report_sales_summary);
        this.setToolbar();
        this.initialize();
        this.setCurrentDateOnView();
        this.getExtras();
        this.setClRef(customerRef);
        if (0 == getClRef()) {
            final Bundle extras = this.getIntent().getExtras();
            this.bundle = extras;
            Intrinsics.checkNotNull(extras);
            this.setType((ProcessType) extras.get("ProcessType"));
            final ProcessType type = this.getType();
            Intrinsics.checkNotNull(type);
            this.setTitle(this.getString(type.resId));
        } else {
            this.setType(this.CustomerToType(customerOperation.getMenuType()));
            this.setTitle(customerOperation.getOperationName());
        }
        this.processFinish(reportXml, this.getType());
        final REPORTXML reportxml = reportSpinXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            if (null != reportxml.reportLines) {
                this.processFinish(reportSpinXml, ProcessType.FILLSPIN);
                this.setColumnVisibility();
            }
        }
        this.exeRetrieveSpin();
        this.setColumnVisibility();
    }

    private void initialize() {
        List emptyList;
        final View findViewById = this.findViewById(R.id.tvCount);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvCount = (AppCompatTextView) findViewById;
        final View findViewById2 = this.findViewById(R.id.tvTotal);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvTotal = (AppCompatTextView) findViewById2;
        final View findViewById3 = this.findViewById(R.id.tvTotalVat);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvTotalVat = (AppCompatTextView) findViewById3;
        final View findViewById4 = this.findViewById(R.id.tvTotalDiscount);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvTotalDiscount = (AppCompatTextView) findViewById4;
        final View findViewById5 = this.findViewById(R.id.tvNetTotal);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvNetTotal = (AppCompatTextView) findViewById5;
        final AppCompatTextView appCompatTextView = tvCount;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView2 = tvTotal;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView3 = tvTotalVat;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView4 = tvTotalDiscount;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView5 = tvNetTotal;
        Intrinsics.checkNotNull(appCompatTextView5);
        appCompatTextView5.setOnClickListener(clickListener);
        final View findViewById6 = this.findViewById(R.id.linearDate1);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate1 = (LinearLayout) findViewById6;
        final View findViewById7 = this.findViewById(R.id.linearDate2);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate2 = (LinearLayout) findViewById7;
        final View findViewById8 = this.findViewById(R.id.imgList);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        imgList = (AppCompatImageButton) findViewById8;
        final LinearLayout linearLayout = linearDate1;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setOnClickListener(clickListener);
        final LinearLayout linearLayout2 = linearDate2;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(clickListener);
        final AppCompatImageButton appCompatImageButton = imgList;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(clickListener);
        final View findViewById9 = this.findViewById(R.id.spUser);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spUser = (AppCompatSpinner) findViewById9;
        final View findViewById10 = this.findViewById(R.id.tvDate1);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate1 = (AppCompatTextView) findViewById10;
        final View findViewById11 = this.findViewById(R.id.tvDate2);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate2 = (AppCompatTextView) findViewById11;
        final View findViewById12 = this.findViewById(R.id.lvReportOrder);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type android.widget.ListView");
        lvReportOrder = (ListView) findViewById12;
        final View findViewById13 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearProgress = (LinearLayout) findViewById13;
        final View findViewById14 = this.findViewById(R.id.linearProgressSpin);
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearProgressSpin = (LinearLayout) findViewById14;
        final LinearLayout linearLayout3 = linearProgress;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setVisibility(8);
        this.setClientForTiger(new ServicesClientForTiger(this));
        final View findViewById15 = this.findViewById(R.id.linearFilterDiv);
        Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearFilterDiv = (LinearLayout) findViewById15;
        arrVisibility = new int[]{1, 1, 1, 1, 1};
        paramNo = ExifInterface.GPS_MEASUREMENT_2D;
        final String singleField = baseErp.getLogoSqlHelper().getSingleField("REPORTPARAM", "PARAMVALUE", "PARAMNO=" + paramNo, false);
        if (!Intrinsics.areEqual(singleField, "")) {
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
            for (int i2 = 0; i2 < length; i2++) {
                int[] iArr = arrVisibility;
                if (null == iArr) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    iArr = null;
                }
                iArr[i2] = StringUtils.convertStringToInt(strArr[i2]);
            }
        }
        screenControl = new ScreenControl(this);
    }
    public Unit getList() {
        List emptyList;
        List emptyList2;
        if (null == this.reportXml) {
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
            createDateStart = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr[2]), StringUtils.convertStringToInt(strArr[1]), StringUtils.convertStringToInt(strArr[0]), 0, 0, 0, 0);
            final AppCompatTextView appCompatTextView2 = tvDate2;
            Intrinsics.checkNotNull(appCompatTextView2);
            final List<String> split2 = new Regex("\\.").split(appCompatTextView2.getText().toString(), 0);
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
            createDateEnd = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr2[2]), StringUtils.convertStringToInt(strArr2[1]), StringUtils.convertStringToInt(strArr2[0]), 23, 59, 59, 998);
            final AppCompatSpinner appCompatSpinner = spUser;
            final Object selectedItem = null != appCompatSpinner ? appCompatSpinner.getSelectedItem() : null;
            final SpinnerItem spinnerItem = selectedItem instanceof SpinnerItem ? (SpinnerItem) selectedItem : null;
            if (null != spinnerItem) {
                spinSelectLogicalRef = StringUtils.convertStringToInt(spinnerItem.logicalRef);
                this.exeRetrieve();
            }
        }
        return Unit.INSTANCE;
    }

    private void exeRetrieve() {
        this.setSelectResult(this.getReportWcfQueriesViewModel().getSalesSummary(createDateStart, createDateEnd, spinSelectLogicalRef, this.getType(), this.getClRef()));
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
    }

    private void exeRetrieveSpin() {
        String str;
        String type = ErpCreator.getInstance().getmBaseErp().getUser().getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        int parseInt = Integer.parseInt(type);
        this.userType = parseInt;
        if (parseInt == 2) {
            str = "";
        } else {
            str = ErpCreator.getInstance().getmBaseErp().getUser().getCode();
        }
        ReportWcfQueriesViewModel reportWcfQueriesViewModel = getReportWcfQueriesViewModel();
        Intrinsics.checkNotNull(str);
        int i2 = this.userType;
        String[] salesManagerSalesmanFilter = ErpCreator.getInstance().getmBaseErp().getSalesManagerSalesmanFilter();
        Intrinsics.checkNotNullExpressionValue(salesManagerSalesmanFilter, "getSalesManagerSalesmanFilter(...)");
        setSelectResult(reportWcfQueriesViewModel.getSalesmans(str, i2, salesManagerSalesmanFilter));
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
    public void onPreExecute(final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            final LinearLayout linearLayout = linearProgress;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(0);
            final ListView listView = lvReportOrder;
            Intrinsics.checkNotNull(listView);
            listView.setEnabled(false);
        }
    }
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            if (null != (reportxml != null ? reportxml.reportLines : null)) {
                if (0 < reportxml.reportLines.size()) {
                    if (null == this.adapter) {
                        reportXml = reportxml;
                        final REPORTXML reportxml2 = reportXml;
                        Intrinsics.checkNotNull(reportxml2);
                        final List<REPORTLINE> reportLines = reportxml2.reportLines;
                        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                        adapter = new ReportSalesSummaryAdapter(this, reportLines);
                        final ListView listView = lvReportOrder;
                        Intrinsics.checkNotNull(listView);
                        listView.setAdapter(adapter);
                        this.adapterNotifyDataSetChanged();
                    } else {
                        final int size = reportxml.reportLines.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            final REPORTXML reportxml3 = reportXml;
                            Intrinsics.checkNotNull(reportxml3);
                            reportxml3.reportLines.add(reportxml.reportLines.get(i2));
                        }
                        final ReportSalesSummaryAdapter reportSalesSummaryAdapter = adapter;
                        Intrinsics.checkNotNull(reportSalesSummaryAdapter);
                        reportSalesSummaryAdapter.notifyDataSetChanged();
                    }
                } else {
                    final ListView listView2 = lvReportOrder;
                    Intrinsics.checkNotNull(listView2);
                    listView2.setAdapter(null);
                }
            } else {
                final ListView listView3 = lvReportOrder;
                Intrinsics.checkNotNull(listView3);
                listView3.setAdapter(null);
            }
            final LinearLayout linearLayout = linearProgress;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(8);
            final ListView listView4 = lvReportOrder;
            Intrinsics.checkNotNull(listView4);
            listView4.setEnabled(true);
            return;
        }
        this.fillSpinnerUsers(reportxml);
    }

    private void fillSpinnerUsers(final REPORTXML reportxml) {
        final ArrayList arrayList = new ArrayList();
        final SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.logicalRef = "0";
        spinnerItem.value = '<' + this.getString(R.string.str_salesman) + '(' + this.getString(R.string.str_all) + ")>";
        arrayList.add(spinnerItem);
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            reportSpinXml = reportxml;
            final int size = reportxml.reportLines.size();
            for (int i2 = 0; i2 < size; i2++) {
                final SpinnerItem spinnerItem2 = new SpinnerItem();
                spinnerItem2.logicalRef = reportxml.reportLines.get(i2).f1208X;
                spinnerItem2.value = reportxml.reportLines.get(i2).f1209Y;
                arrayList.add(spinnerItem2);
            }
        }
        final ArrayAdapter<SpinnerItem> arrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, arrayList);
        adapterSpinner = arrayAdapter;
        Intrinsics.checkNotNull(arrayAdapter);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner = spUser;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(adapterSpinner);
        final LinearLayout linearLayout = linearProgressSpin;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(4);
        if (2 != this.userType) {
            arrayList.remove(0);
            final ArrayAdapter<SpinnerItem> arrayAdapter2 = adapterSpinner;
            Intrinsics.checkNotNull(arrayAdapter2);
            arrayAdapter2.notifyDataSetChanged();
        }
    }
    protected void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            outState.putSerializable("reportXml", reportxml);
        }
        final REPORTXML reportxml2 = reportSpinXml;
        if (null != reportxml2) {
            outState.putSerializable("reportSpinXml", reportxml2);
        }
        final AppCompatTextView appCompatTextView = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView);
        outState.putString("strDate1", appCompatTextView.getText().toString());
        final AppCompatTextView appCompatTextView2 = tvDate2;
        Intrinsics.checkNotNull(appCompatTextView2);
        outState.putString("strDate2", appCompatTextView2.getText().toString());
        outState.putString("createDateStart", createDateStart);
        outState.putString("createDateEnd", createDateEnd);
        outState.putInt("userType", userType);
        this.saveReportViewSetting();
    }
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        this.restoreActivityBundle(savedInstanceState);
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
            final AppCompatTextView appCompatTextView = tvCount;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setVisibility(8);
            i2 = 1;
        } else {
            final AppCompatTextView appCompatTextView2 = tvCount;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setVisibility(0);
            i2 = 0;
        }
        int[] iArr3 = arrVisibility;
        if (null == iArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr3 = null;
        }
        if (0 == iArr3[1]) {
            final AppCompatTextView appCompatTextView3 = tvTotal;
            Intrinsics.checkNotNull(appCompatTextView3);
            appCompatTextView3.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView4 = tvTotal;
            Intrinsics.checkNotNull(appCompatTextView4);
            appCompatTextView4.setVisibility(0);
        }
        int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr4 = null;
        }
        if (0 == iArr4[2]) {
            final AppCompatTextView appCompatTextView5 = tvTotalVat;
            Intrinsics.checkNotNull(appCompatTextView5);
            appCompatTextView5.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView6 = tvTotalVat;
            Intrinsics.checkNotNull(appCompatTextView6);
            appCompatTextView6.setVisibility(0);
        }
        int[] iArr5 = arrVisibility;
        if (null == iArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr5 = null;
        }
        if (0 == iArr5[3]) {
            final AppCompatTextView appCompatTextView7 = tvTotalDiscount;
            Intrinsics.checkNotNull(appCompatTextView7);
            appCompatTextView7.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView8 = tvTotalDiscount;
            Intrinsics.checkNotNull(appCompatTextView8);
            appCompatTextView8.setVisibility(0);
        }
        int[] iArr6 = arrVisibility;
        if (null == iArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr6 = null;
        }
        if (0 == iArr6[4]) {
            final AppCompatTextView appCompatTextView9 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView9);
            appCompatTextView9.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView10 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView10);
            appCompatTextView10.setVisibility(0);
        }
        final int[] iArr7 = arrVisibility;
        if (null == iArr7) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr2 = iArr7;
        }
        if (i2 == iArr2.length) {
            arrVisibility = new int[]{1, 1, 1, 1, 1};
            this.setColumnVisibility();
        }
        if (null != this.adapter) {
            this.adapterNotifyDataSetChanged();
        }
    }

    private void adapterNotifyDataSetChanged() {
        final ReportSalesSummaryAdapter reportSalesSummaryAdapter = adapter;
        Intrinsics.checkNotNull(reportSalesSummaryAdapter);
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        reportSalesSummaryAdapter.setColumnVisibility(iArr);
        final ReportSalesSummaryAdapter reportSalesSummaryAdapter2 = adapter;
        Intrinsics.checkNotNull(reportSalesSummaryAdapter2);
        reportSalesSummaryAdapter2.notifyDataSetChanged();
    }
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        this.menu = menu;
        return true;
    }
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.finish();
            return true;
        }
        if (R.id.reportBackLoad == itemId) {
            arrVisibility = new int[]{1, 1, 1, 1, 1};
            this.setColumnVisibility();
            if (null != this.adapter) {
                this.adapterNotifyDataSetChanged();
            }
            return true;
        }
        if (R.id.reportFullScreen == itemId) {
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restoreActivityBundle(final Bundle bundle) {
        if (bundle.containsKey("reportXml")) {
            reportXml = (REPORTXML) bundle.getSerializable("reportXml");
        }
        if (bundle.containsKey("reportSpinXml")) {
            reportSpinXml = (REPORTXML) bundle.getSerializable("reportSpinXml");
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
        if (bundle.containsKey("createDateStart")) {
            createDateStart = bundle.getString("createDateStart");
        }
        if (bundle.containsKey("createDateEnd")) {
            createDateEnd = bundle.getString("createDateEnd");
        }
        if (bundle.containsKey("userType")) {
            userType = bundle.getInt("userType");
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        this.saveReportViewSetting();
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
        baseErp.getLogoSqlHelper().addParamDbTwo("REPORTPARAM", paramNo, substring);
    }
}
