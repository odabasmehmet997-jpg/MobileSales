package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseCommunication;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.view.ScreenControl;
import com.proje.mobilesales.features.model.SpinnerItem;
import com.proje.mobilesales.features.model.WAREHOUSE;
import com.proje.mobilesales.features.model.WorUserModel;
import com.proje.mobilesales.features.reports.adapter.ReportSalesOrdInvAdapter;
import com.proje.mobilesales.features.reports.model.ReportListParameter;
import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.proje.mobilesales.core.utils.AppUtils.alert;


/* compiled from: ReportWCFSalesOrdInvActivity.kt */

public final class ReportWCFSalesOrdInvActivity extends BaseReportWcfActivity {
    private ReportSalesOrdInvAdapter adapter;
    private ArrayAdapter<SpinnerItem> adapterSpinner;
    private int[] arrVisibility;
    private Bundle bundle;
    private AppCompatCheckBox chGroup;
    private AppCompatImageButton imgList;
    private InvoiceType invType;
    private LinearLayout linearFilterDiv;
    private LinearLayout linearProgress;
    private LinearLayout linearProgressSpin;
    private ListView lvReportOrder;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private Menu menu;
    private List<String> ordInvFicheTypeList;
    private OrderStatus orderType;
    private String paramNo;
    private String reportHtml;
    private REPORTXML reportSpinXml;
    private REPORTXML reportXml;
    private ScreenControl screenControl;
    private AppCompatSpinner spOrderInvType;
    private ArrayAdapter<String> spOrderTypeAdapter;
    private AppCompatSpinner spUser;
    private AppCompatSpinner spWareHouse;
    private ArrayAdapter<String> spWareHouseAdapter;
    private int spinSelectLogicalRef;
    private AppCompatTextView tvCode;
    private AppCompatTextView tvDefinition;
    private AppCompatTextView tvGrossTotal;
    private AppCompatTextView tvLineType;
    private AppCompatTextView tvNetTotal;
    private AppCompatTextView tvQuantity;
    private AppCompatTextView tvShowing;
    private AppCompatTextView tvTotal;
    private int userType;
    private List<String> wareHouseList;
    private int wareHouseNo;
    private boolean loadingMore = true;
    private String createDateStart = "";
    private String createDateEnd = "";
    private final View.OnClickListener clickListener = new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFSalesOrdInvActivityclickListener1
        @Override // android.view.View.OnClickListener
        @SuppressLint("SetTextI18n")
        public void onClick(final View v) {
            final int[] iArr;
            final int[] iArr2;
            final int[] iArr3;
            final int[] iArr4;
            final int[] iArr5;
            final int[] iArr6;
            Intrinsics.checkNotNullParameter(v, "v");
            int[] iArr7 = null;
            switch (v.getId()) {
                case R.id.imgList /* 2131296890 */:
                    executeReport();
                    break;
                case R.id.linearDate1 /* 2131296958 */:
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity = ReportWCFSalesOrdInvActivity.this;
                    reportWCFSalesOrdInvActivity.dateIsFirst = true;
                    reportWCFSalesOrdInvActivity.setDate();
                    showDialog(998);
                    break;
                case R.id.linearDate2 /* 2131296959 */:
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity2 = ReportWCFSalesOrdInvActivity.this;
                    reportWCFSalesOrdInvActivity2.dateIsFirst = false;
                    reportWCFSalesOrdInvActivity2.setDate();
                    showDialog(999);
                    break;
                case R.id.tvCode /* 2131297812 */:
                    iArr = arrVisibility;
                    if (null == iArr) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr;
                    }
                    iArr7[1] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvDefinition_ /* 2131297826 */:
                    iArr2 = arrVisibility;
                    if (null == iArr2) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr2;
                    }
                    iArr7[2] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvGrossTotal /* 2131297856 */:
                    iArr3 = arrVisibility;
                    if (null == iArr3) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr3;
                    }
                    iArr7[4] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvLineType /* 2131297866 */:
                    iArr4 = arrVisibility;
                    if (null == iArr4) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr4;
                    }
                    iArr7[0] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvNetTotal /* 2131297871 */:
                    iArr5 = arrVisibility;
                    if (null == iArr5) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr5;
                    }
                    iArr7[5] = 0;
                    setColumnVisibility();
                    break;
                case R.id.tvQuantity /* 2131297883 */:
                    iArr6 = arrVisibility;
                    if (null == iArr6) {
                        Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                    } else {
                        iArr7 = iArr6;
                    }
                    iArr7[3] = 0;
                    setColumnVisibility();
                    break;
            }
        }
    };
    private final AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFSalesOrdInvActivityscrollListener1
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
                final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity = ReportWCFSalesOrdInvActivity.this;
                reportxml = reportWCFSalesOrdInvActivity.reportXml;
                Intrinsics.checkNotNull(reportxml);
                reportWCFSalesOrdInvActivity.exeRetrieve(reportxml.reportLines.size());
            }
        }
    };
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFSalesOrdInvActivityExternalSyntheticLambda0
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda4;
            itemLongClickListenerlambda4 = itemLongClickListenerlambda4(ReportWCFSalesOrdInvActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda4;
        }
    };

    private void setUserRight() {
    }

    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        final ProgressDialogBuilder<?> progressDialogBuilder = mProgressDialogBuilder;
        if (null != progressDialogBuilder) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }

    public void setMProgressDialogBuilder(final ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        mProgressDialogBuilder = progressDialogBuilder;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        final AlertDialogBuilder<?> alertDialogBuilder = mAlertDialogBuilder;
        if (null != alertDialogBuilder) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }

    public void setMAlertDialogBuilder(final AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        mAlertDialogBuilder = alertDialogBuilder;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.getActivityComponent().inject(this);
        if (null != bundle) {
            this.restoreActivityBundle(bundle);
        }
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        final Context context2 = ContextUtils.getmContext();
        final Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.setMAlertDialogBuilder(new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity2));
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
        this.setContentView(R.layout.report_sales_order_invoice);
        this.setToolbar();
        this.initialize();
        this.setValues();
        this.setCurrentDateOnView();
        this.setUserRight();
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

    private void setValues() {
        List emptyList;
        final List<String> list = baseErp.getLogoSqlHelper().getList(WAREHOUSE.class, "AMBAR", "", false);
        wareHouseList = list;
        if (null != list) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                final List<String> list2 = wareHouseList;
                Intrinsics.checkNotNull(list2);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list2);
                spWareHouseAdapter = arrayAdapter;
                Intrinsics.checkNotNull(arrayAdapter);
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                final AppCompatSpinner appCompatSpinner = spWareHouse;
                Intrinsics.checkNotNull(appCompatSpinner);
                appCompatSpinner.setAdapter(spWareHouseAdapter);
            }
        }
        ordInvFicheTypeList = new ArrayList();
        if (this.getType() == ProcessType.FILLREPORTSALESORD || this.getType() == ProcessType.FILLREPORTORDERTOTAL) {
            final List<String> list3 = ordInvFicheTypeList;
            if (null != list3) {
                final String string = this.getString(R.string.str_all);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                list3.add(string);
            }
            final List<String> list4 = ordInvFicheTypeList;
            if (null != list4) {
                final String string2 = this.getString(R.string.str_suggestion);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                list4.add(string2);
            }
            final List<String> list5 = ordInvFicheTypeList;
            if (null != list5) {
                final String string3 = this.getString(R.string.str_can_be_shipped);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                list5.add(string3);
            }
            final List<String> list6 = ordInvFicheTypeList;
            if (null != list6) {
                final String string4 = this.getString(R.string.str_can_not_be_shipped);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                list6.add(string4);
            }
            if (this.getType() == ProcessType.FILLREPORTORDERTOTAL) {
                final AppCompatSpinner appCompatSpinner2 = spWareHouse;
                Intrinsics.checkNotNull(appCompatSpinner2);
                appCompatSpinner2.setVisibility(8);
            }
        } else if (this.getType() == ProcessType.FILLREPORTSALESINV) {
            final List<String> list7 = ordInvFicheTypeList;
            if (null != list7) {
                final String string5 = this.getString(R.string.str_sales);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                list7.add(string5);
            }
            final List<String> list8 = ordInvFicheTypeList;
            if (null != list8) {
                final String string6 = this.getString(R.string.str_return);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                list8.add(string6);
            }
            final AppCompatCheckBox appCompatCheckBox = chGroup;
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setVisibility(8);
        }
        final List<String> list9 = ordInvFicheTypeList;
        Intrinsics.checkNotNull(list9, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.simple_spinner_item, (ArrayList) list9);
        spOrderTypeAdapter = arrayAdapter2;
        Intrinsics.checkNotNull(arrayAdapter2);
        arrayAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner3 = spOrderInvType;
        Intrinsics.checkNotNull(appCompatSpinner3);
        appCompatSpinner3.setAdapter(spOrderTypeAdapter);
        paramNo = ExifInterface.GPS_MEASUREMENT_3D;
        arrVisibility = new int[]{1, 1, 1, 1, 1, 1};
        final String singleField = baseErp.getLogoSqlHelper().getSingleField("REPORTPARAM", "PARAMVALUE", "PARAMNO=" + paramNo, false);
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
        for (int i2 = 0; i2 < length; i2++) {
            int[] iArr = arrVisibility;
            if (null == iArr) {
                Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
                iArr = null;
            }
            iArr[i2] = StringUtils.convertStringToInt(strArr[i2]);
        }
    }

    private void initialize() {
        final View findViewById = this.findViewById(R.id.linearDate1);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate1 = (LinearLayout) findViewById;
        final View findViewById2 = this.findViewById(R.id.linearDate2);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearDate2 = (LinearLayout) findViewById2;
        final View findViewById3 = this.findViewById(R.id.imgList);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        imgList = (AppCompatImageButton) findViewById3;
        final LinearLayout linearLayout = linearDate1;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setOnClickListener(clickListener);
        final LinearLayout linearLayout2 = linearDate2;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(clickListener);
        final AppCompatImageButton appCompatImageButton = imgList;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(clickListener);
        final View findViewById4 = this.findViewById(R.id.spUser);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spUser = (AppCompatSpinner) findViewById4;
        final View findViewById5 = this.findViewById(R.id.tvDate1);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate1 = (AppCompatTextView) findViewById5;
        final View findViewById6 = this.findViewById(R.id.tvDate2);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate2 = (AppCompatTextView) findViewById6;
        final View findViewById7 = this.findViewById(R.id.lvReportOrder);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById7;
        lvReportOrder = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnItemLongClickListener(itemLongClickListener);
        final ListView listView2 = lvReportOrder;
        Intrinsics.checkNotNull(listView2);
        listView2.setOnScrollListener(scrollListener);
        final View findViewById8 = this.findViewById(R.id.tvTotal);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvTotal = (AppCompatTextView) findViewById8;
        final View findViewById9 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearProgress = (LinearLayout) findViewById9;
        final View findViewById10 = this.findViewById(R.id.linearProgressSpin);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearProgressSpin = (LinearLayout) findViewById10;
        final LinearLayout linearLayout3 = linearProgress;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setVisibility(8);
        final View findViewById11 = this.findViewById(R.id.tvShowing);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvShowing = (AppCompatTextView) findViewById11;
        this.setClientForTiger(new ServicesClientForTiger(this));
        final View findViewById12 = this.findViewById(R.id.tvLineType);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvLineType = (AppCompatTextView) findViewById12;
        final View findViewById13 = this.findViewById(R.id.tvQuantity);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvQuantity = (AppCompatTextView) findViewById13;
        final View findViewById14 = this.findViewById(R.id.tvCode);
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvCode = (AppCompatTextView) findViewById14;
        final View findViewById15 = this.findViewById(R.id.tvDefinition_);
        Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDefinition = (AppCompatTextView) findViewById15;
        final View findViewById16 = this.findViewById(R.id.tvGrossTotal);
        Intrinsics.checkNotNull(findViewById16, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvGrossTotal = (AppCompatTextView) findViewById16;
        final View findViewById17 = this.findViewById(R.id.tvNetTotal);
        Intrinsics.checkNotNull(findViewById17, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvNetTotal = (AppCompatTextView) findViewById17;
        final AppCompatTextView appCompatTextView = tvQuantity;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView2 = tvNetTotal;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView3 = tvDefinition;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView4 = tvCode;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setOnClickListener(clickListener);
        final AppCompatTextView appCompatTextView5 = tvGrossTotal;
        Intrinsics.checkNotNull(appCompatTextView5);
        appCompatTextView5.setOnClickListener(clickListener);
        final View findViewById18 = this.findViewById(R.id.linearFilterDiv);
        Intrinsics.checkNotNull(findViewById18, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearFilterDiv = (LinearLayout) findViewById18;
        final View findViewById19 = this.findViewById(R.id.spWareHouse);
        Intrinsics.checkNotNull(findViewById19, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spWareHouse = (AppCompatSpinner) findViewById19;
        final View findViewById20 = this.findViewById(R.id.spOrderType);
        Intrinsics.checkNotNull(findViewById20, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spOrderInvType = (AppCompatSpinner) findViewById20;
        final View findViewById21 = this.findViewById(R.id.chGroup);
        Intrinsics.checkNotNull(findViewById21, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatCheckBox");
        chGroup = (AppCompatCheckBox) findViewById21;
        screenControl = new ScreenControl(this);
    }

    
    public void executeReport() {
        adapter = null;
        final ListView listView = lvReportOrder;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(null);
        reportXml = null;
        final AppCompatTextView appCompatTextView = tvTotal;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(StringUtils.fFormat(0.0f));
        final AppCompatTextView appCompatTextView2 = tvShowing;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText("0");
        final AppCompatTextView appCompatTextView3 = tvDate1;
        Intrinsics.checkNotNull(appCompatTextView3);
        final String obj = appCompatTextView3.getText().toString();
        final AppCompatTextView appCompatTextView4 = tvDate2;
        Intrinsics.checkNotNull(appCompatTextView4);
        if (!DateAndTimeUtils.compareDates(obj, appCompatTextView4.getText().toString(), "dd.MM.yyyy")) {
            Toast.makeText(this, this.getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
        } else {
            this.getList();
        }
    }

    private Unit getList() {
        List emptyList;
        List emptyList2;
        List emptyList3;
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
            createDateEnd = DateAndTimeUtils.getDateTime(StringUtils.convertStringToInt(strArr2[2]), StringUtils.convertStringToInt(strArr2[1]), StringUtils.convertStringToInt(strArr2[0]), 23, 59, 59, 999);
            final AppCompatSpinner appCompatSpinner = spUser;
            final Object selectedItem = null != appCompatSpinner ? appCompatSpinner.getSelectedItem() : null;
            final SpinnerItem spinnerItem = selectedItem instanceof SpinnerItem ? (SpinnerItem) selectedItem : null;
            if (null != spinnerItem) {
                spinSelectLogicalRef = StringUtils.convertStringToInt(spinnerItem.logicalRef);
            }
            final AppCompatSpinner appCompatSpinner2 = spWareHouse;
            final Object selectedItem2 = null != appCompatSpinner2 ? appCompatSpinner2.getSelectedItem() : null;
            final String str = selectedItem2 instanceof String ? (String) selectedItem2 : null;
            if (null != str) {
                final List<String> split3 = new Regex(",").split(str, 0);
                if (!split3.isEmpty()) {
                    final ListIterator<String> listIterator3 = split3.listIterator(split3.size());
                    while (listIterator3.hasPrevious()) {
                        if (0 != listIterator3.previous().length()) {
                            emptyList3 = CollectionsKt.take(split3, listIterator3.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList3 = CollectionsKt.emptyList();
                wareHouseNo = StringUtils.convertStringToInt(((String[]) emptyList3.toArray(new String[0]))[0]);
            }
            final AppCompatSpinner appCompatSpinner3 = spOrderInvType;
            Intrinsics.checkNotNull(appCompatSpinner3);
            final int selectedItemPosition = appCompatSpinner3.getSelectedItemPosition();
            if (0 == selectedItemPosition) {
                orderType = OrderStatus.ALL;
                invType = InvoiceType.NORMAL;
            } else if (1 == selectedItemPosition) {
                orderType = OrderStatus.OFFER;
                invType = InvoiceType.RETURN;
            } else if (2 == selectedItemPosition) {
                orderType = OrderStatus.DISPATCHABLE;
            } else if (3 == selectedItemPosition) {
                orderType = OrderStatus.UNDISPATCHABLE;
            }
            this.exeRetrieve(0);
        }
        return Unit.INSTANCE;
    }

    
    public void exeRetrieve(final int i2) {
        final SelectResult salesInv;
        final String str = createDateStart;
        Intrinsics.checkNotNull(str);
        final String str2 = createDateEnd;
        Intrinsics.checkNotNull(str2);
        final int i3 = spinSelectLogicalRef;
        final int i4 = wareHouseNo;
        final ReportSortType reportSortType = this.getReportSortType();
        Intrinsics.checkNotNull(reportSortType);
        final ReportListParameter reportListParameter = new ReportListParameter(str, str2, i3, i4, reportSortType);
        if (this.getType() == ProcessType.FILLREPORTSALESORD) {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
            final ProcessType type = this.getType();
            final OrderStatus orderStatus = orderType;
            Intrinsics.checkNotNull(orderStatus);
            final AppCompatCheckBox appCompatCheckBox = chGroup;
            Intrinsics.checkNotNull(appCompatCheckBox);
            salesInv = reportWcfQueriesViewModel.getSalesOrd(reportListParameter, i2, type, orderStatus, appCompatCheckBox.isChecked(), this.getClRef());
        } else if (this.getType() == ProcessType.FILLREPORTORDERTOTAL) {
            reportListParameter.setWarehouseNo(-1);
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel2 = this.getReportWcfQueriesViewModel();
            final ProcessType type2 = this.getType();
            final OrderStatus orderStatus2 = orderType;
            Intrinsics.checkNotNull(orderStatus2);
            final AppCompatCheckBox appCompatCheckBox2 = chGroup;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            salesInv = reportWcfQueriesViewModel2.getSalesOrd(reportListParameter, i2, type2, orderStatus2, appCompatCheckBox2.isChecked(), this.getClRef());
        } else {
            final ReportWcfQueriesViewModel reportWcfQueriesViewModel3 = this.getReportWcfQueriesViewModel();
            final ProcessType type3 = this.getType();
            final InvoiceType invoiceType = invType;
            Intrinsics.checkNotNull(invoiceType);
            salesInv = reportWcfQueriesViewModel3.getSalesInv(reportListParameter, i2, type3, invoiceType, this.getClRef());
        }
        this.setSelectResult(salesInv);
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.resultXML = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ReportRetrieve(getSelectResult()));
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
        setRetrieve(clientForTiger.new ReportRetrieve(this.getSelectResult()));
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
            reportHtml = "";
            if (null != (reportxml != null ? reportxml.reportLines : null)) {
                final int size = reportxml.reportLines.size();
                if (0 < size) {
                    final List<REPORTLINE> reportLines = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                    this.buildReportHtml(reportLines);
                    loadingMore = 50000 == size;
                    if (null == this.adapter) {
                        reportXml = reportxml;
                        final REPORTXML reportxml2 = reportXml;
                        Intrinsics.checkNotNull(reportxml2);
                        final List<REPORTLINE> reportLines2 = reportxml2.reportLines;
                        Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
                        adapter = new ReportSalesOrdInvAdapter(this, reportLines2);
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
                        final ReportSalesOrdInvAdapter reportSalesOrdInvAdapter = adapter;
                        Intrinsics.checkNotNull(reportSalesOrdInvAdapter);
                        reportSalesOrdInvAdapter.notifyDataSetChanged();
                    }
                    final AppCompatTextView appCompatTextView = tvTotal;
                    Intrinsics.checkNotNull(appCompatTextView);
                    appCompatTextView.setText(StringUtils.fFormat(this.getTotal()));
                    final AppCompatTextView appCompatTextView2 = tvShowing;
                    Intrinsics.checkNotNull(appCompatTextView2);
                    final StringBuilder sb = new StringBuilder();
                    final ReportSalesOrdInvAdapter reportSalesOrdInvAdapter2 = adapter;
                    Intrinsics.checkNotNull(reportSalesOrdInvAdapter2);
                    sb.append(reportSalesOrdInvAdapter2.getCount());
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

    private void buildReportHtml(final List<? extends REPORTLINE> list) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>");
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            sb.append("<style>");
            sb.append("body {");
            sb.append("font-family: Arial, Helvetica, sans-serif;");
            sb.append("}");
            sb.append("table {");
            sb.append("border: 1px solid black; border-collapse: collapse;");
            sb.append("}");
            sb.append("td, th {");
            sb.append("border: 1px solid #dddddd;");
            sb.append("text-align: left;");
            sb.append("padding: 8px;");
            sb.append("}");
            sb.append(".alnright {");
            sb.append("text-align: right;");
            sb.append("}");
            sb.append("</style></head><body><table><tbody>");
            sb.append("<tr>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_line_type));
            sb.append("</th>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_code));
            sb.append("</th>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_explanation));
            sb.append("</th>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_quantity));
            sb.append("</th>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_quantity));
            sb.append("</th>");
            sb.append("<th>");
            sb.append(this.getString(R.string.str_net_total));
            sb.append("</th>");
            sb.append("</tr>");
            for (final REPORTLINE reportline : list) {
                sb.append("<tr>");
                sb.append("<td>");
                sb.append(this.getString(LineType.Companion.fromLineType(StringUtils.convertStringToInt(reportline.f1203A)).getResId()));
                sb.append("</td>");
                sb.append("<td>");
                sb.append(reportline.f1208X);
                sb.append("</td>");
                sb.append("<td>");
                sb.append(reportline.f1209Y);
                sb.append("</td>");
                sb.append("<td class=\"alnright\">");
                sb.append(reportline.f1210Z);
                sb.append("</td>");
                sb.append("<td class=\"alnright\">");
                sb.append(reportline.f1207W);
                sb.append("</td>");
                sb.append("<td class=\"alnright\">");
                sb.append(reportline.f1206T);
                sb.append("</td>");
                sb.append("</tr>");
            }
            sb.append("<tr>");
            sb.append("<td colspan=\"1\" style=\"font-weight: bold;\">");
            sb.append(this.getString(R.string.str_sales_line_count));
            sb.append("</td>");
            sb.append("<td colspan=\"1\" class=\"alnright\" style=\"font-weight: bold;\">");
            sb.append(list.size());
            sb.append("</td>");
            sb.append("<td colspan=\"2\">");
            sb.append("</td>");
            sb.append("<td colspan=\"1\" style=\"font-weight: bold;\">");
            sb.append(this.getString(R.string.str_sales_fiche_total));
            sb.append("</td>");
            sb.append("<td colspan=\"1\" class=\"alnright\" style=\"font-weight: bold;\">");
            sb.append(StringUtils.fFormat(this.getTotalFromReportLines(list)));
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</tbody></table></body></html>");
            reportHtml = sb.toString();
        } catch (final Exception e2) {
            Log.e("BuildReportHtml", Constants.IPC_BUNDLE_KEY_SEND_ERROR, e2);
            reportHtml = "";
        }
    }

    private float getTotalFromReportLines(final List<? extends REPORTLINE> list) {
        final int size = list.size();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            if (LineType.Companion.fromLineType(StringUtils.convertStringToInt(list.get(i2).f1203A)) != LineType.PROMOTION) {
                final String T = list.get(i2).f1206T;
                Intrinsics.checkNotNullExpressionValue(T, "T");
                f2 += (float) StringUtils.toDouble2(T);
            }
        }
        return f2;
    }

    private float getTotal() {
        final REPORTXML reportxml = reportXml;
        Intrinsics.checkNotNull(reportxml);
        final int size = reportxml.reportLines.size();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            final LineType.Companion companion = LineType.Companion;
            final REPORTXML reportxml2 = reportXml;
            Intrinsics.checkNotNull(reportxml2);
            if (companion.fromLineType(StringUtils.convertStringToInt(reportxml2.reportLines.get(i2).f1203A)) != LineType.PROMOTION) {
                final REPORTXML reportxml3 = reportXml;
                Intrinsics.checkNotNull(reportxml3);
                final String T = reportxml3.reportLines.get(i2).f1206T;
                Intrinsics.checkNotNullExpressionValue(T, "T");
                f2 += (float) StringUtils.toDouble2(T);
            }
        }
        return f2;
    }
    public static boolean itemLongClickListenerlambda4(final ReportWCFSalesOrdInvActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
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
        dialog.setContentView(R.layout.report_ord_inv_detail_vertical);
        final View findViewById = dialog.findViewById(R.id.tvLineType);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = dialog.findViewById(R.id.tvCode);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = dialog.findViewById(R.id.tvDefinition_);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = dialog.findViewById(R.id.tvQuantity);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = dialog.findViewById(R.id.tvGrossTotal);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = dialog.findViewById(R.id.tvNetTotal);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        ((AppCompatTextView) findViewById).setText(this.getString(LineType.Companion.fromLineType(StringUtils.convertStringToInt(reportline.f1203A)).getResId()));
        ((AppCompatTextView) findViewById2).setText(reportline.f1208X);
        ((AppCompatTextView) findViewById3).setText(reportline.f1209Y);
        ((AppCompatTextView) findViewById4).setText(reportline.f1210Z);
        ((AppCompatTextView) findViewById5).setText(reportline.f1207W);
        ((AppCompatTextView) findViewById6).setText(reportline.f1206T);
        dialog.show();
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

    public void onSaveInstanceState(final Bundle outState) {
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
        outState.putBoolean("loadingMore", loadingMore);
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

    
    public void setColumnVisibility() {
        int i2;
        int[] iArr = arrVisibility;
        int[] iArr2 = null;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        if (0 == iArr[0]) {
            final AppCompatTextView appCompatTextView = tvLineType;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setVisibility(8);
            i2 = 1;
        } else {
            final AppCompatTextView appCompatTextView2 = tvLineType;
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
            final AppCompatTextView appCompatTextView3 = tvCode;
            Intrinsics.checkNotNull(appCompatTextView3);
            appCompatTextView3.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView4 = tvCode;
            Intrinsics.checkNotNull(appCompatTextView4);
            appCompatTextView4.setVisibility(0);
        }
        int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr4 = null;
        }
        if (0 == iArr4[2]) {
            final AppCompatTextView appCompatTextView5 = tvDefinition;
            Intrinsics.checkNotNull(appCompatTextView5);
            appCompatTextView5.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView6 = tvDefinition;
            Intrinsics.checkNotNull(appCompatTextView6);
            appCompatTextView6.setVisibility(0);
        }
        int[] iArr5 = arrVisibility;
        if (null == iArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr5 = null;
        }
        if (0 == iArr5[3]) {
            final AppCompatTextView appCompatTextView7 = tvQuantity;
            Intrinsics.checkNotNull(appCompatTextView7);
            appCompatTextView7.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView8 = tvQuantity;
            Intrinsics.checkNotNull(appCompatTextView8);
            appCompatTextView8.setVisibility(0);
        }
        int[] iArr6 = arrVisibility;
        if (null == iArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr6 = null;
        }
        if (0 == iArr6[4]) {
            final AppCompatTextView appCompatTextView9 = tvGrossTotal;
            Intrinsics.checkNotNull(appCompatTextView9);
            appCompatTextView9.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView10 = tvGrossTotal;
            Intrinsics.checkNotNull(appCompatTextView10);
            appCompatTextView10.setVisibility(0);
        }
        int[] iArr7 = arrVisibility;
        if (null == iArr7) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr7 = null;
        }
        if (0 == iArr7[5]) {
            final AppCompatTextView appCompatTextView11 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView11);
            appCompatTextView11.setVisibility(8);
            i2++;
        } else {
            final AppCompatTextView appCompatTextView12 = tvNetTotal;
            Intrinsics.checkNotNull(appCompatTextView12);
            appCompatTextView12.setVisibility(0);
        }
        final int[] iArr8 = arrVisibility;
        if (null == iArr8) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr2 = iArr8;
        }
        if (i2 == iArr2.length) {
            arrVisibility = new int[]{1, 1, 1, 1, 1, 1};
            this.setColumnVisibility();
        }
        if (null != this.adapter) {
            this.adapterNotifyDataSetChanged();
        }
    }

    private void adapterNotifyDataSetChanged() {
        final ReportSalesOrdInvAdapter reportSalesOrdInvAdapter = adapter;
        Intrinsics.checkNotNull(reportSalesOrdInvAdapter);
        int[] iArr = arrVisibility;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        reportSalesOrdInvAdapter.setColumnVisibility(iArr);
        final ReportSalesOrdInvAdapter reportSalesOrdInvAdapter2 = adapter;
        Intrinsics.checkNotNull(reportSalesOrdInvAdapter2);
        reportSalesOrdInvAdapter2.notifyDataSetChanged();
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        menu.findItem(R.id.reportSendMail).setVisible(true);
        menu.findItem(R.id.menu_sort_customer_name).setVisible(true);
        this.menu = menu;
        return true;
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            case R.id.menu_sort_customer_name /* 2131297406 */:
                this.toggleSort(item);
                this.executeReport();
                return true;
            case R.id.reportBackLoad /* 2131297561 */:
                arrVisibility = new int[]{1, 1, 1, 1, 1, 1};
                this.setColumnVisibility();
                if (null != this.adapter) {
                    this.adapterNotifyDataSetChanged();
                }
                return true;
            case R.id.reportFullScreen /* 2131297568 */:
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
            case R.id.reportSendMail /* 2131297571 */:
                if (TextUtils.isEmpty(reportHtml)) {
                    Toast.makeText(this, R.string.str_empty_reportContent, 1).show();
                    return true;
                }
                if (ContextUtils.checkInternetConnection()) {
                    mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
                    baseErp.getSalesManagers(new SalesManagerResponseListener(this));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    public void onSalesManagerResponse(List<? extends WorUserModel> list) {
        mProgressDialogBuilder.dismiss();
        if (null == list || list.isEmpty()) {
            alert(this.getString(R.string.str_no_sales_manager_with_email));
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (final WorUserModel worUserModel : list) {
            if (!TextUtils.isEmpty(worUserModel.getEmail())) {
                final String name = worUserModel.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                arrayList.add(name);
            }
        }
        int[] iArr = {-1};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_select_salesManager);
        builder.setSingleChoiceItems((CharSequence[]) arrayList.toArray(new CharSequence[0]), -1, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onSalesManagerResponselambda5(iArr, dialogInterface, i2);
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int i2) {
                onSalesManagerResponselambda6(iArr, ReportWCFSalesOrdInvActivity.this, list, dialogInterface, i2);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        final AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        create.show();
    }
    public static void onSalesManagerResponselambda5(final int[] clickedIndex, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(clickedIndex, "clickedIndex");
        clickedIndex[0] = i2;
    }

    public static void onSalesManagerResponselambda6(final int[] clickedIndex, final ReportWCFSalesOrdInvActivity this0, final List list, final DialogInterface dialogInterface, final int i2) {
        Intrinsics.checkNotNullParameter(clickedIndex, "clickedIndex");
        Intrinsics.checkNotNullParameter(this0, "this0");
        if (-1 == clickedIndex[0] || TextUtils.isEmpty(this0.reportHtml)) {
            return;
        }
        final WorUserModel worUserModel = (WorUserModel) list.get(clickedIndex[0]);
        final EmailObject emailObject = new EmailObject();
        emailObject.setHtml(this0.reportHtml);
        emailObject.setTo(new String[]{worUserModel.getEmail()});
        emailObject.setSubject(this0.getTitle() + " - " + this0.baseErp.getUser().getCode());
        emailObject.setSendHTMLContent(true);
        this0.new SendReportMail(emailObject).execute();
    }
        private record SalesManagerResponseListener(
            WeakReference<ReportWCFSalesOrdInvActivity> mReportActivity) implements ResponseListener<List<? extends WorUserModel>> {
            private SalesManagerResponseListener(final ReportWCFSalesOrdInvActivity mReportActivity) {
                this(new WeakReference<>(mReportActivity));
            }

            public void onResponse(final List<? extends WorUserModel> list) {
                if (null != this.mReportActivity.get()) {
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity = mReportActivity.get();
                    Intrinsics.checkNotNull(reportWCFSalesOrdInvActivity);
                    if (reportWCFSalesOrdInvActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity2 = mReportActivity.get();
                    Intrinsics.checkNotNull(reportWCFSalesOrdInvActivity2);
                    reportWCFSalesOrdInvActivity2.onSalesManagerResponse(list);
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(final String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mReportActivity.get()) {
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity = mReportActivity.get();
                    Intrinsics.checkNotNull(reportWCFSalesOrdInvActivity);
                    if (reportWCFSalesOrdInvActivity.isActivityDestroyed()) {
                        return;
                    }
                    Log.d("SalesOrdInvActivity", "onError: " + errorMessage);
                    final ReportWCFSalesOrdInvActivity reportWCFSalesOrdInvActivity2 = mReportActivity.get();
                    Intrinsics.checkNotNull(reportWCFSalesOrdInvActivity2);
                    reportWCFSalesOrdInvActivity2.onSalesManagerResponse(null);
                }
            }
        }

    private final class SendReportMail extends AsyncTask<Void, Void, Void> {
        private final Object data = null;
        private final EmailObject emailObject;
        private String msg;
        private final DataObjectType objectType = null;

        public SendReportMail(final EmailObject emailObject) {
            this.emailObject = emailObject;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_sending_email)).setOnCancelClickListener().setCancelable(false).show();
        }

        
        public static void onPreExecutelambda0(final SendReportMail this0, final DialogInterface dialogInterface) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this0.cancel(true);
        }

        @Override // android.os.AsyncTask
        protected Void doInBackground(final Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            final BaseCommunication baseCommunication = ReportWCFSalesOrdInvActivity.this.baseErp.getBaseCommunication();
            try {
                final EmailObject emailObject = this.emailObject;
                if (null == emailObject) {
                    return null;
                }
                msg = baseCommunication.sendMail(emailObject);
                return null;
            } catch (final Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(final Void r3) {
            super.onPostExecute( r3);
            mProgressDialogBuilder.dismiss();
            try {
                final String str = msg;
                if (null != str) {
                    Intrinsics.checkNotNull(str);
                    if (0 < str.length()) {
                        Toast.makeText(ContextUtils.getmContext(), msg, 1).show();
                    }
                }
            } catch (final Exception e2) {
                Log.e("SendReportMail", "checkConnection: ", e2);
            }
        }
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
