package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat;
import com.proje.mobilesales.core.searchdialog.SearchResultListener;
import com.proje.mobilesales.core.searchdialog.SimpleSearchDialogCompat;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.model.BaseSearchModel;
import com.proje.mobilesales.features.model.SpinnerItem;
import com.proje.mobilesales.features.reports.adapter.ReportAdapter;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class ReportWCFAllReportActivity extends BaseReportWcfActivity {
    private ReportAdapter adapter;
    private ArrayAdapter<SpinnerItem> adapterSpinner;
    private int[] arrVisibility;
    private Bundle bundle;
    private AppCompatImageButton imgList;
    private boolean isFourColumn;
    private LinearLayout linearFilterDiv;
    private LinearLayout linearProgress;
    private LinearLayout linearProgressSpin;
    private ListView lvReportOrder;
    private Menu menu;
    private String paramNo;
    private REPORTXML reportSpinXml;
    private REPORTXML reportXml;
    private ScreenControl screenControl;
    private static BaseSearchModel selectedSalesMan;
    private static TextView spUser;
    private int spinSelectLogicalRef;
    private AppCompatTextView tvCode;
    private AppCompatTextView tvDefinition_;
    private AppCompatTextView tvFicheNo;
    private AppCompatTextView tvGrossTotal;
    private AppCompatTextView tvNetTotal;
    private TextView tvOrderState;
    private AppCompatTextView tvShowing;
    private AppCompatTextView tvTotal;
    private int userType;
    private boolean loadingMore = true;
    private String createDateStart = "";
    private String createDateEnd = "";
    private ArrayList<BaseSearchModel> salesManList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    private final View.OnClickListener clickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFAllReportActivityExternalSyntheticLambda0
        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            clickListenerlambda1(ReportWCFAllReportActivity.this, view);
        }
    };
    private final AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFAllReportActivityscrollListener1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(final AbsListView view, final int i2) {
            Intrinsics.checkNotNullParameter(view, "view");
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(final AbsListView view, final int i2, final int i3, final int i4) {
            final boolean z;
            final REPORTXML reportxml;
            Intrinsics.checkNotNullParameter(view, "view");
            final int i5 = i2 + i3;
            if (i5 == i4) {
                z = loadingMore;
                if (!z || 0 >= i5) {
                    return;
                }
                loadingMore = false;
                final ReportWCFAllReportActivity reportWCFAllReportActivity = ReportWCFAllReportActivity.this;
                reportxml = reportWCFAllReportActivity.reportXml;
                Intrinsics.checkNotNull(reportxml);
                reportWCFAllReportActivity.exeRetrieve(reportxml.reportLines.size());
            }
        }
    };
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFAllReportActivityExternalSyntheticLambda1
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda4;
            itemLongClickListenerlambda4 = itemLongClickListenerlambda4(ReportWCFAllReportActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda4;
        }
    };

    private void setUserRight() {
    }

    public ArrayAdapter<SpinnerItem> getAdapterSpinner() {
        return adapterSpinner;
    }

    public void setAdapterSpinner(final ArrayAdapter<SpinnerItem> arrayAdapter) {
        adapterSpinner = arrayAdapter;
    }

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
        this.getExtras();
        this.setClRef(customerRef);
        final Bundle extras = this.getIntent().getExtras();
        this.bundle = extras;
        if (0 == this.customerRef) {
            Intrinsics.checkNotNull(extras);
            this.setType((ProcessType) extras.get("ProcessType"));
            final ProcessType type = this.getType();
            Intrinsics.checkNotNull(type);
            this.setTitle(this.getString(type.resId));
        } else {
            this.setTitle(customerOperation.getOperationName());
            this.setType(this.CustomerToType(customerOperation.getMenuType()));
        }
        isFourColumn = this.getType() == ProcessType.FILLREPORTCASH || this.getType() == ProcessType.FILLREPORTCREDIT || this.getType() == ProcessType.FILLREPORTCASHCASE || this.getType() == ProcessType.FILLREPORTCHEQUE || this.getType() == ProcessType.FILLREPORTDEED;
        this.setContentView(R.layout.report_order);
        this.setToolbar();
        this.initialize();
        this.setCurrentDateOnView();
        this.setUserRight();
        this.processFinish(reportXml, this.getType());
        final REPORTXML reportxml = reportSpinXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            if (null != reportxml.reportLines) {
                final REPORTXML reportxml2 = reportSpinXml;
                Intrinsics.checkNotNull(reportxml2);
                this.processFinish(reportxml2, ProcessType.FILLSPIN);
                this.setColumnVisibility();
            }
        }
        this.exeRetrieveSpin();
        this.setColumnVisibility();
    }

    private void initialize() {
        List emptyList;
        int[] iArr;
        linearDate1 = this.findViewById(R.id.linearDate1);
        linearDate2 = this.findViewById(R.id.linearDate2);
        imgList = this.findViewById(R.id.imgList);
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
        spUser = this.findViewById(R.id.spUser);
        tvDate1 = this.findViewById(R.id.tvDate1);
        tvDate2 = this.findViewById(R.id.tvDate2);
        final ListView listView = this.findViewById(R.id.lvReportOrder);
        lvReportOrder = listView;
        if (null != listView) {
            listView.setOnItemLongClickListener(itemLongClickListener);
        }
        final ListView listView2 = lvReportOrder;
        if (null != listView2) {
            listView2.setOnScrollListener(scrollListener);
        }
        tvTotal = this.findViewById(R.id.tvTotal);
        linearProgress = this.findViewById(R.id.linearProgress);
        linearProgressSpin = this.findViewById(R.id.linearProgressSpin);
        final LinearLayout linearLayout3 = linearProgress;
        if (null != linearLayout3) {
            linearLayout3.setVisibility(8);
        }
        tvShowing = this.findViewById(R.id.tvShowing);
        this.setClientForTiger(new ServicesClientForTiger(this));
        tvFicheNo = this.findViewById(R.id.tvFicheNo);
        tvCode = this.findViewById(R.id.tvCode);
        tvDefinition_ = this.findViewById(R.id.tvDefinition_);
        tvOrderState = this.findViewById(R.id.tvOrderState);
        if (this.isReportOrder()) {
            final TextView textView = tvOrderState;
            if (null != textView) {
                textView.setVisibility(0);
            }
        } else {
            final TextView textView2 = tvOrderState;
            if (null != textView2) {
                textView2.setVisibility(8);
            }
        }
        tvGrossTotal = this.findViewById(R.id.tvGrossTotal);
        tvNetTotal = this.findViewById(R.id.tvNetTotal);
        final AppCompatTextView appCompatTextView = tvFicheNo;
        if (null != appCompatTextView) {
            appCompatTextView.setOnClickListener(clickListener);
        }
        final AppCompatTextView appCompatTextView2 = tvNetTotal;
        if (null != appCompatTextView2) {
            appCompatTextView2.setOnClickListener(clickListener);
        }
        final AppCompatTextView appCompatTextView3 = tvDefinition_;
        if (null != appCompatTextView3) {
            appCompatTextView3.setOnClickListener(clickListener);
        }
        final AppCompatTextView appCompatTextView4 = tvCode;
        if (null != appCompatTextView4) {
            appCompatTextView4.setOnClickListener(clickListener);
        }
        final AppCompatTextView appCompatTextView5 = tvGrossTotal;
        if (null != appCompatTextView5) {
            appCompatTextView5.setOnClickListener(clickListener);
        }
        linearFilterDiv = this.findViewById(R.id.linearFilterDiv);
        if (isFourColumn) {
            paramNo = BuildConfig.NETSIS_DEMO_PASSWORD;
            arrVisibility = new int[]{1, 1, 1, 1, 0};
        } else {
            paramNo = "0";
            arrVisibility = new int[]{1, 1, 1, 1, 1};
        }
        final String singleField = this.getReportWcfQueriesViewModel().getSqlHelper().getSingleField("REPORTPARAM", "PARAMVALUE", "PARAMNO=" + paramNo, false);
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
            int i2 = 0;
            while (true) {
                iArr = null;
                if (i2 >= length) {
                    break;
                }
                final int[] iArr2 = arrVisibility;
                if (null == iArr2) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr = iArr2;
                }
                iArr[i2] = StringUtils.convertStringToInt(strArr[i2]);
                i2++;
            }
            if (isFourColumn) {
                final int[] iArr3 = arrVisibility;
                if (null == iArr3) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr = iArr3;
                }
                iArr[4] = 0;
            }
        }
        screenControl = new ScreenControl(this);
    }

    
    public static void clickListenerlambda1(final ReportWCFAllReportActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        int[] iArr = null;
        int[] iArr2 = null;
        int[] iArr3 = null;
        int[] iArr4 = null;
        int[] iArr5 = null;
        switch (view.getId()) {
            case R.id.imgList /* 2131296890 */:
                this0.adapter = null;
                final ListView listView = this0.lvReportOrder;
                Intrinsics.checkNotNull(listView);
                listView.setAdapter(null);
                this0.reportXml = null;
                final AppCompatTextView appCompatTextView = this0.tvTotal;
                Intrinsics.checkNotNull(appCompatTextView);
                appCompatTextView.setText(StringUtils.fFormat(0.0f));
                final AppCompatTextView appCompatTextView2 = this0.tvShowing;
                Intrinsics.checkNotNull(appCompatTextView2);
                appCompatTextView2.setText("0");
                final AppCompatTextView appCompatTextView3 = this0.tvDate1;
                final String valueOf = String.valueOf(null != appCompatTextView3 ? appCompatTextView3.getText() : null);
                final AppCompatTextView appCompatTextView4 = this0.tvDate2;
                if (!DateAndTimeUtils.compareDates(valueOf, String.valueOf(null != appCompatTextView4 ? appCompatTextView4.getText() : null), "dd.MM.yyyy")) {
                    Toast.makeText(this0, this0.getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
                    break;
                } else {
                    this0.getList();
                    break;
                }
            case R.id.linearDate1 /* 2131296958 */:
                this0.dateIsFirst = true;
                this0.setDate();
                this0.showDialog(998);
                break;
            case R.id.linearDate2 /* 2131296959 */:
                this0.dateIsFirst = false;
                this0.setDate();
                this0.showDialog(999);
                break;
            case R.id.tvCode /* 2131297812 */:
                final int[] iArr6 = this0.arrVisibility;
                if (null == iArr6) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr5 = iArr6;
                }
                iArr5[1] = 0;
                this0.setColumnVisibility();
                break;
            case R.id.tvDefinition_ /* 2131297826 */:
                final int[] iArr7 = this0.arrVisibility;
                if (null == iArr7) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr4 = iArr7;
                }
                iArr4[2] = 0;
                this0.setColumnVisibility();
                break;
            case R.id.tvFicheNo /* 2131297849 */:
                final int[] iArr8 = this0.arrVisibility;
                if (null == iArr8) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr3 = iArr8;
                }
                iArr3[0] = 0;
                this0.setColumnVisibility();
                break;
            case R.id.tvGrossTotal /* 2131297856 */:
                final int[] iArr9 = this0.arrVisibility;
                if (null == iArr9) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr2 = iArr9;
                }
                iArr2[3] = 0;
                this0.setColumnVisibility();
                break;
            case R.id.tvNetTotal /* 2131297871 */:
                final int[] iArr10 = this0.arrVisibility;
                if (null == iArr10) {
                    Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                } else {
                    iArr = iArr10;
                }
                iArr[4] = 0;
                this0.setColumnVisibility();
                break;
        }
    }

    private Unit getList() {
        List emptyList;
        List emptyList2;
        if (null == this.reportXml) {
            final AppCompatTextView appCompatTextView = tvDate1;
            final List<String> split = new Regex("\\.").split(String.valueOf(null != appCompatTextView ? appCompatTextView.getText() : null), 0);
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
            final List<String> split2 = new Regex("\\.").split(String.valueOf(null != appCompatTextView2 ? appCompatTextView2.getText() : null), 0);
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
            createDateEnd = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr2[2]), StringUtils.convertStringToInt(strArr2[1]), StringUtils.convertStringToInt(strArr2[0]), 23, 59, 59, 0);
            final BaseSearchModel baseSearchModel = selectedSalesMan;
            if (null != baseSearchModel) {
                Intrinsics.checkNotNull(baseSearchModel);
                spinSelectLogicalRef = baseSearchModel.getLogicalRef();
                this.exeRetrieve(0);
            }
        }
        return Unit.INSTANCE;
    }

    
    public void exeRetrieve(final int i2) {
        if (this.getType() == ProcessType.FILLREPORTORDER || this.getType() == ProcessType.FILLREPORTINVOICE || this.getType() == ProcessType.FILLREPORTRETURNINVOICE) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
            final String str = createDateStart;
            Intrinsics.checkNotNull(str);
            final String str2 = createDateEnd;
            Intrinsics.checkNotNull(str2);
            final int i3 = spinSelectLogicalRef;
            final ProcessType type = this.getType();
            Intrinsics.checkNotNull(type);
            this.setSelectResult(reportWcfQueriesViewModel.getOrderInvoice(i2, str, str2, i3, type, this.getClRef()));
        } else if (this.getType() == ProcessType.FILLREPORTCASH || this.getType() == ProcessType.FILLREPORTCREDIT) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel2 = this.getReportWcfQueriesViewModel();
            final String str3 = createDateStart;
            Intrinsics.checkNotNull(str3);
            final String str4 = createDateEnd;
            Intrinsics.checkNotNull(str4);
            final int i4 = spinSelectLogicalRef;
            final ProcessType type2 = this.getType();
            Intrinsics.checkNotNull(type2);
            this.setSelectResult(reportWcfQueriesViewModel2.getCashCredit(i2, str3, str4, i4, type2, this.getClRef()));
        } else if (this.getType() == ProcessType.FILLREPORTCASHCASE) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel3 = this.getReportWcfQueriesViewModel();
            final String str5 = createDateStart;
            Intrinsics.checkNotNull(str5);
            final String str6 = createDateEnd;
            Intrinsics.checkNotNull(str6);
            this.setSelectResult(reportWcfQueriesViewModel3.getCashCase(i2, str5, str6, spinSelectLogicalRef, this.getType(), this.getClRef()));
        } else if (this.getType() == ProcessType.FILLREPORTCHEQUE || this.getType() == ProcessType.FILLREPORTDEED) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel4 = this.getReportWcfQueriesViewModel();
            final String str7 = createDateStart;
            Intrinsics.checkNotNull(str7);
            final String str8 = createDateEnd;
            Intrinsics.checkNotNull(str8);
            final int i5 = spinSelectLogicalRef;
            final ProcessType type3 = this.getType();
            Intrinsics.checkNotNull(type3);
            this.setSelectResult(reportWcfQueriesViewModel4.getChequeDeed(i2, str7, str8, i5, type3, this.getClRef()));
        }
        final SelectResult selectResult = this.getSelectResult();
        Intrinsics.checkNotNull(selectResult);
        selectResult.resultXML = "";
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ReportRetrieve(getSelectResult()));
        ServicesClientForTiger.ReportRetrieve retrieve = getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }

    private void exeRetrieveSpin() {
        String str;
        String type = getReportWcfQueriesViewModel().getBaseErp().getUser().getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        int parseInt = Integer.parseInt(type);
        this.userType = parseInt;
        if (parseInt == 2) {
            str = "";
        } else {
            str = getReportWcfQueriesViewModel().getBaseErp().getUser().getCode();
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
        setRetrieve(clientForTiger.new ReportRetrieve(this.getSelectResult()));
        final ServicesClientForTiger.ReportRetrieve retrieve = this.getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
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

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    @SuppressLint("SetTextI18n")
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            if (null != (reportxml != null ? reportxml.reportLines : null)) {
                final int size = reportxml.reportLines.size();
                if (0 < size) {
                    loadingMore = 50000 == size;
                    if (null == this.adapter) {
                        reportXml = reportxml;
                        final REPORTXML reportxml2 = reportXml;
                        Intrinsics.checkNotNull(reportxml2);
                        final List<REPORTLINE> reportLines = reportxml2.reportLines;
                        Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                        Intrinsics.checkNotNull(processType);
                        adapter = new ReportAdapter(this, reportLines, processType);
                        final ListView listView = lvReportOrder;
                        Intrinsics.checkNotNull(listView);
                        listView.setAdapter(adapter);
                        this.adapterNotifyDataSetChanged();
                    } else {
                        final int size2 = reportxml.reportLines.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            final REPORTXML reportxml3 = reportXml;
                            Intrinsics.checkNotNull(reportxml3);
                            reportxml3.reportLines.add(reportxml.reportLines.get(i2));
                        }
                        final ReportAdapter reportAdapter = adapter;
                        Intrinsics.checkNotNull(reportAdapter);
                        reportAdapter.notifyDataSetChanged();
                    }
                    final AppCompatTextView appCompatTextView = tvTotal;
                    Intrinsics.checkNotNull(appCompatTextView);
                    appCompatTextView.setText(StringUtils.fFormat(this.getTotal()));
                    final AppCompatTextView appCompatTextView2 = tvShowing;
                    Intrinsics.checkNotNull(appCompatTextView2);
                    final StringBuilder sb = new StringBuilder();
                    final ReportAdapter reportAdapter2 = adapter;
                    Intrinsics.checkNotNull(reportAdapter2);
                    sb.append(reportAdapter2.getCount());
                    appCompatTextView2.setText(sb.toString());
                } else {
                    loadingMore = false;
                    final ListView listView2 = lvReportOrder;
                    Intrinsics.checkNotNull(listView2);
                    listView2.setAdapter(null);
                }
            } else {
                loadingMore = false;
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

    private float getTotal() {
        final REPORTXML reportxml = reportXml;
        Intrinsics.checkNotNull(reportxml);
        final int size = reportxml.reportLines.size();
        int i2 = 0;
        float f2 = 0.0f;
        if (this.getType() == ProcessType.FILLREPORTORDER || this.getType() == ProcessType.FILLREPORTINVOICE || this.getType() == ProcessType.FILLREPORTRETURNINVOICE) {
            while (i2 < size) {
                final REPORTXML reportxml2 = reportXml;
                Intrinsics.checkNotNull(reportxml2);
                final String NETTOTAL = reportxml2.reportLines.get(i2).NETTOTAL;
                Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
                f2 += (float) StringUtils.toDouble(NETTOTAL);
                i2++;
            }
        } else {
            while (i2 < size) {
                final REPORTXML reportxml3 = reportXml;
                Intrinsics.checkNotNull(reportxml3);
                final String GROSSTOTAL = reportxml3.reportLines.get(i2).GROSSTOTAL;
                Intrinsics.checkNotNullExpressionValue(GROSSTOTAL, "GROSSTOTAL");
                f2 += (float) StringUtils.toDouble(GROSSTOTAL);
                i2++;
            }
        }
        return f2;
    }

    
    public static boolean itemLongClickListenerlambda4(final ReportWCFAllReportActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final REPORTXML reportxml = this0.reportXml;
        Intrinsics.checkNotNull(reportxml);
        final REPORTLINE reportline = reportxml.reportLines.get(i2);
        Intrinsics.checkNotNull(reportline);
        this0.showDetail(reportline);
        return true;
    }

    private void showDetail(final REPORTLINE reportline) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_item_detail_vertical);
        final AppCompatTextView appCompatTextView = dialog.findViewById(R.id.tvCode);
        final AppCompatTextView appCompatTextView2 = dialog.findViewById(R.id.tvFicheNo);
        final AppCompatTextView appCompatTextView3 = dialog.findViewById(R.id.tvDefinition_);
        final AppCompatTextView appCompatTextView4 = dialog.findViewById(R.id.tvOrderState);
        final LinearLayout linearLayout = dialog.findViewById(R.id.lnOrderStateVertical);
        if (this.isReportOrder()) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
        final AppCompatTextView appCompatTextView5 = dialog.findViewById(R.id.tvGrossTotal);
        final AppCompatTextView appCompatTextView6 = dialog.findViewById(R.id.tvNetTotal);
        appCompatTextView.setText(reportline.CODE);
        appCompatTextView2.setText(reportline.FICHENO);
        appCompatTextView3.setText(reportline.DEFINITION_);
        appCompatTextView4.setText(reportline.f1208X);
        final String GROSSTOTAL = reportline.GROSSTOTAL;
        Intrinsics.checkNotNullExpressionValue(GROSSTOTAL, "GROSSTOTAL");
        appCompatTextView5.setText(StringUtils.fFormat(GROSSTOTAL));
        final String NETTOTAL = reportline.NETTOTAL;
        Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
        appCompatTextView6.setText(StringUtils.fFormat(NETTOTAL));
        dialog.show();
    }

    private void fillSpinnerUsers(final REPORTXML reportxml) {
        final ArrayList<BaseSearchModel> arrayList = new ArrayList<>();
        salesManList = arrayList;
        arrayList.add(new BaseSearchModel(0, this.getString(R.string.str_salesman) + SqlLiteVariable._OPEN_BRACKET + this.getString(R.string.str_all) + " )", false));
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            reportSpinXml = reportxml;
            final int size = reportxml.reportLines.size();
            for (int i2 = 0; i2 < size; i2++) {
                salesManList.add(new BaseSearchModel(StringUtils.convertStringToInt(reportxml.reportLines.get(i2).f1208X), reportxml.reportLines.get(i2).f1209Y, false));
            }
        }
        final LinearLayout linearLayout = linearProgressSpin;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(4);
        if (2 != this.userType) {
            salesManList.remove(0);
        }
        if (!salesManList.isEmpty()) {
            final TextView textView = spUser;
            Intrinsics.checkNotNull(textView);
            final BaseSearchModel baseSearchModel = salesManList.get(0);
            Intrinsics.checkNotNull(baseSearchModel);
            textView.setText(baseSearchModel.getCode());
            selectedSalesMan = salesManList.get(0);
        }
        final TextView textView2 = spUser;
        Intrinsics.checkNotNull(textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                fillSpinnerUserslambda5(ReportWCFAllReportActivity.this, view);
            }
        });
    }
 
    public static void fillSpinnerUserslambda5(ReportWCFAllReportActivity this0, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        new SimpleSearchDialogCompat(this0, "", "", null, this0.salesManList, new BaseSearchModelSearchResultListener()).show();
    }
     public void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            outState.putSerializable("reportXml", reportxml);
        }
        final REPORTXML reportxml2 = reportSpinXml;
        if (null != reportxml2) {
            outState.putSerializable("reportSpinXml", reportxml2);
        }
        outState.putBoolean("loadingMore", loadingMore);
        final AppCompatTextView appCompatTextView = tvDate1;
        outState.putString("strDate1", String.valueOf(null != appCompatTextView ? appCompatTextView.getText() : null));
        final AppCompatTextView appCompatTextView2 = tvDate2;
        outState.putString("strDate2", String.valueOf(null != appCompatTextView2 ? appCompatTextView2.getText() : null));
        outState.putString("createDateStart", createDateStart);
        outState.putString("createDateEnd", createDateEnd);
        outState.putInt("userType", userType);
        this.saveReportViewSetting();
        super.onSaveInstanceState(outState);
    }

    
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        this.restoreActivityBundle(savedInstanceState);
    }

    private void setColumnVisibility() {
        int i2;
        final int[] iArr;
        int[] iArr2 = arrVisibility;
        int[] iArr3 = null;
        if (null == iArr2) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr2 = null;
        }
        if (0 == iArr2[0]) {
            final AppCompatTextView appCompatTextView = tvFicheNo;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setVisibility(8);
            i2 = 1;
        } else {
            final AppCompatTextView appCompatTextView2 = tvFicheNo;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setVisibility(0);
            i2 = 0;
        }
        int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr4 = null;
        }
        if (0 == iArr4[1]) {
            final AppCompatTextView appCompatTextView3 = tvCode;
            Intrinsics.checkNotNull(appCompatTextView3);
            appCompatTextView3.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView4 = tvCode;
            Intrinsics.checkNotNull(appCompatTextView4);
            appCompatTextView4.setVisibility(0);
        }
        int[] iArr5 = arrVisibility;
        if (null == iArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr5 = null;
        }
        if (0 == iArr5[2]) {
            final AppCompatTextView appCompatTextView5 = tvDefinition_;
            Intrinsics.checkNotNull(appCompatTextView5);
            appCompatTextView5.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView6 = tvDefinition_;
            Intrinsics.checkNotNull(appCompatTextView6);
            appCompatTextView6.setVisibility(0);
        }
        int[] iArr6 = arrVisibility;
        if (null == iArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr6 = null;
        }
        if (0 == iArr6[3]) {
            final AppCompatTextView appCompatTextView7 = tvGrossTotal;
            Intrinsics.checkNotNull(appCompatTextView7);
            appCompatTextView7.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView8 = tvGrossTotal;
            Intrinsics.checkNotNull(appCompatTextView8);
            appCompatTextView8.setVisibility(0);
        }
        int[] iArr7 = arrVisibility;
        if (null == iArr7) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr7 = null;
        }
        if (0 == iArr7[4]) {
            final AppCompatTextView appCompatTextView9 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView9);
            appCompatTextView9.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView10 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView10);
            appCompatTextView10.setVisibility(0);
        }
        final int[] iArr8 = arrVisibility;
        if (null == iArr8) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr3 = iArr8;
        }
        if (i2 == iArr3.length) {
            if (isFourColumn) {
                iArr = new int[]{1, 1, 1, 1, 0};
            } else {
                iArr = new int[]{1, 1, 1, 1, 1};
            }
            arrVisibility = iArr;
            this.setColumnVisibility();
        }
        if (null != this.adapter) {
            this.adapterNotifyDataSetChanged();
        }
    }

    private void adapterNotifyDataSetChanged() {
        final ReportAdapter reportAdapter = adapter;
        Intrinsics.checkNotNull(reportAdapter);
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        reportAdapter.setColumnVisibility(iArr);
        final ReportAdapter reportAdapter2 = adapter;
        Intrinsics.checkNotNull(reportAdapter2);
        reportAdapter2.notifyDataSetChanged();
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        this.menu = menu;
        return true;
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int[] iArr;
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.onBackPressed();
        } else if (R.id.reportBackLoad == itemId) {
            if (isFourColumn) {
                iArr = new int[]{1, 1, 1, 1, 0};
            } else {
                iArr = new int[]{1, 1, 1, 1, 1};
            }
            arrVisibility = iArr;
            this.setColumnVisibility();
            if (null != this.adapter) {
                this.adapterNotifyDataSetChanged();
            }
        } else if (R.id.reportFullScreen == itemId) {
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
        } else {
            return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void restoreActivityBundle(final Bundle bundle) {
        if (bundle.containsKey("reportXml")) {
            reportXml = (REPORTXML) bundle.getSerializable("reportXml");
        }
        if (bundle.containsKey("reportSpinXml")) {
            reportSpinXml = (REPORTXML) bundle.getSerializable("reportSpinXml");
        }
        if (bundle.containsKey("loadingMore")) {
            loadingMore = bundle.getBoolean("loadingMore");
        }
        if (bundle.containsKey("strDate1")) {
            final String string = bundle.getString("strDate1", "");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            strDate1 = string;
        }
        if (bundle.containsKey("strDate2")) {
            final String string2 = bundle.getString("strDate2", "");
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
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

    @Override // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
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
        this.getReportWcfQueriesViewModel().getSqlHelper().addParamDbTwo("REPORTPARAM", paramNo, substring);
    }

    private boolean isReportOrder() {
        return this.getType() == ProcessType.FILLREPORTORDER;
    }

    private static class BaseSearchModelSearchResultListener implements SearchResultListener<BaseSearchModel> {
        public   void onSelected(final BaseSearchDialogCompat baseSearchDialogCompat, final BaseSearchModel baseSearchModel, final int i2) {
            this.onSelected2((BaseSearchDialogCompat<?>) baseSearchDialogCompat, baseSearchModel, i2);
        }

        public void onSelected2(final BaseSearchDialogCompat<?> dialog, final BaseSearchModel baseSearchModel, final int i2) {
            final TextView textView;
            Intrinsics.checkNotNullParameter(dialog, "dialog");
            textView = spUser;
            Intrinsics.checkNotNull(textView);
            textView.setText(null != baseSearchModel ? baseSearchModel.getTitle() : null);
            selectedSalesMan = baseSearchModel;
            dialog.dismiss();
        }

        public void onCancelled(final BaseSearchDialogCompat dialog) {
            Intrinsics.checkNotNullParameter(dialog, "dialog");
            dialog.dismiss();
        }
    }
}
