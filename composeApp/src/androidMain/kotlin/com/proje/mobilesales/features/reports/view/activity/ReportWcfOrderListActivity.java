package com.proje.mobilesales.features.reports.view.activity;

import android.R;
import android.app.Dialog;
import android.os.Bundle;
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
import com.proje.mobilesales.features.model.Orders;
import com.proje.mobilesales.features.model.SpinnerItem;
import com.proje.mobilesales.features.reports.adapter.ReportOrderListAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;


/* compiled from: ReportWcfOrderListActivity.kt */

public final class ReportWcfOrderListActivity extends BaseReportWcfActivity {
    private ReportOrderListAdapter adapter;
    private ListView dataList;
    private AppCompatImageButton imageButtonGetData;
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWcfOrderListActivityExternalSyntheticLambda0
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda4;
            itemLongClickListenerlambda4 = itemLongClickListenerlambda4(ReportWcfOrderListActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda4;
        }
    };
    private LinearLayout progressListData;
    private AppCompatSpinner spOrderStatus;
    private AppCompatSpinner spStatus;
    private int userType;
    private List<Orders> wcfCollectionses;

    /* renamed from: getDataList, reason: collision with other method in class */
    public ListView m1477getDataList() {
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

    public ReportOrderListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final ReportOrderListAdapter reportOrderListAdapter) {
        adapter = reportOrderListAdapter;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.report_order_list);
        this.setToolbar();
        this.getExtras();
        this.setClRef(customerRef);
        this.setType(this.CustomerToType(customerOperation.getMenuType()));
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
        final View findViewById5 = this.findViewById(R.id.spStatus);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spStatus = (AppCompatSpinner) findViewById5;
        final View findViewById6 = this.findViewById(R.id.spOrderStatus);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatSpinner");
        spOrderStatus = (AppCompatSpinner) findViewById6;
        final View findViewById7 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.LinearLayout");
        final LinearLayout linearLayout = (LinearLayout) findViewById7;
        progressListData = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final View findViewById8 = this.findViewById(R.id.imgList);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        imageButtonGetData = (AppCompatImageButton) findViewById8;
        final View findViewById9 = this.findViewById(R.id.lvReportOrder);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById9;
        dataList = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnItemLongClickListener(itemLongClickListener);
        final AppCompatImageButton appCompatImageButton = imageButtonGetData;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this);
        final LinearLayout linearLayout2 = linearDate1;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(this);
        final LinearLayout linearLayout3 = linearDate2;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setOnClickListener(this);
        this.setMyCalendar(Calendar.getInstance());
        this.setClientForTiger(new ServicesClientForTiger(this));
        this.fillStatus();
        this.fillOrderStatus();
        this.initDateTxtView();
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
        if (z) {
            final String dateTime = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 0, 0, 0, 0);
            Intrinsics.checkNotNull(dateTime);
            return dateTime;
        }
        final String dateTime2 = DateAndTimeUtils.getDateTime(parseInt3, parseInt2, parseInt, 23, 59, 59, 0);
        Intrinsics.checkNotNull(dateTime2);
        return dateTime2;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, android.view.View.OnClickListener
    public void onClick(final View view) {
        if (null != view && R.id.linearDate1 == view.getId()) {
            this.showDatePicker(true);
            return;
        }
        if (null != view && R.id.linearDate2 == view.getId()) {
            this.showDatePicker(false);
            return;
        }
        if (null == view || R.id.imgList != view.getId()) {
            return;
        }
        adapter = null;
        if (!this.validateDates()) {
            Toast.makeText(this, this.getString(R.string.exp_64_begin_date_bigger_than_end_date), 1).show();
        } else {
            this.getDataList();
        }
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(0);
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            final List<Orders> dataList = this.setDataList(reportxml.reportLines);
            if (null != dataList) {
                this.fillDataList(CollectionsKt.toList(dataList));
            }
        } else {
            final ListView listView = dataList;
            Intrinsics.checkNotNull(listView);
            listView.setAdapter(null);
            Toast.makeText(this.getApplicationContext(), this.getString(R.string.str_data_not_found), 0);
        }
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
    }

    private List<Orders> setDataList(final List<? extends REPORTLINE> list) {
        List emptyList;
        wcfCollectionses = new ArrayList();
        if (null != list) {
            for (final REPORTLINE reportline : list) {
                final Orders orders = new Orders();
                orders.setFicheNo(reportline.FICHENO);
                orders.setLogicalRef(reportline.LOGICALREF);
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
                orders.setDate(((String[]) emptyList.toArray(new String[0]))[0]);
                final String NETTOTAL = reportline.NETTOTAL;
                Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
                orders.setAmount(StringUtils.fFormat(NETTOTAL));
                final String A = reportline.f1203A;
                Intrinsics.checkNotNullExpressionValue(A, "A");
                if (0 < A.length()) {
                    orders.setType("F");
                } else {
                    orders.setType(" ");
                }
                final String B = reportline.f1204B;
                Intrinsics.checkNotNullExpressionValue(B, "B");
                final int parseInt = Integer.parseInt(B);
                if (1 == parseInt) {
                    orders.setState(this.getString(R.string.str_suggestion));
                } else if (2 == parseInt) {
                    orders.setState(this.getString(R.string.str_can_not_be_shipped));
                } else if (4 == parseInt) {
                    orders.setState(this.getString(R.string.str_can_be_shipped));
                }
                final String X = reportline.f1208X;
                Intrinsics.checkNotNullExpressionValue(X, "X");
                final String fFormat = StringUtils.fFormat(X);
                final String Y = reportline.f1209Y;
                Intrinsics.checkNotNullExpressionValue(Y, "Y");
                final String fFormat2 = StringUtils.fFormat(Y);
                if (Intrinsics.areEqual(fFormat, "0,00")) {
                    orders.setStatus("Tamami Sevk Edildi");
                } else if (Intrinsics.areEqual(fFormat, fFormat2)) {
                    orders.setStatus("Hic Sevk Edilmemis");
                } else if (!Intrinsics.areEqual(fFormat, fFormat2)) {
                    orders.setStatus("Bakiye Siparis");
                }
                final List<Orders> list2 = wcfCollectionses;
                if (null != list2) {
                    list2.add(orders);
                }
            }
        }
        return wcfCollectionses;
    }

    private void fillDataList(final List<? extends Orders> list) {
        if (!list.isEmpty()) {
            final ReportOrderListAdapter reportOrderListAdapter = adapter;
            if (null == reportOrderListAdapter) {
                adapter = new ReportOrderListAdapter(this, list);
                final ListView listView = dataList;
                Intrinsics.checkNotNull(listView);
                listView.setAdapter(adapter);
                final ReportOrderListAdapter reportOrderListAdapter2 = adapter;
                Intrinsics.checkNotNull(reportOrderListAdapter2);
                reportOrderListAdapter2.notifyDataSetChanged();
            } else {
                Intrinsics.checkNotNull(reportOrderListAdapter);
                reportOrderListAdapter.notifyDataSetChanged();
            }
        } else {
            final ListView listView2 = dataList;
            Intrinsics.checkNotNull(listView2);
            listView2.setAdapter(null);
        }
        final ListView listView3 = dataList;
        Intrinsics.checkNotNull(listView3);
        listView3.setEnabled(true);
    }

    private void getDataList() {
        this.setSelectResult(this.getReportWcfQueriesViewModel().getOrderReportQuery(this.getClRef(), baseErp.getUser().getSalesPersonRef(), this.getDateTxtView(true), this.getDateTxtView(false), this.getStatus(false), this.getStatus(true)));
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.resultXML = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ReportRetrieve(this.getSelectResult()));
        final ServicesClientForTiger.ReportRetrieve retrieve = this.getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
    }

    private int getStatus(final boolean z) {
        if (z) {
            final AppCompatSpinner appCompatSpinner = spStatus;
            Intrinsics.checkNotNull(appCompatSpinner);
            final Object selectedItem = appCompatSpinner.getSelectedItem();
            Intrinsics.checkNotNull(selectedItem, "null cannot be cast to non-null type com.proje.mobilesales.features.model.SpinnerItem");
            final String logicalRef = ((SpinnerItem) selectedItem).logicalRef;
            Intrinsics.checkNotNullExpressionValue(logicalRef, "logicalRef");
            return Integer.parseInt(logicalRef);
        }
        final AppCompatSpinner appCompatSpinner2 = spOrderStatus;
        Intrinsics.checkNotNull(appCompatSpinner2);
        final Object selectedItem2 = appCompatSpinner2.getSelectedItem();
        Intrinsics.checkNotNull(selectedItem2, "null cannot be cast to non-null type com.proje.mobilesales.features.model.SpinnerItem");
        final String logicalRef2 = ((SpinnerItem) selectedItem2).logicalRef;
        Intrinsics.checkNotNullExpressionValue(logicalRef2, "logicalRef");
        return Integer.parseInt(logicalRef2);
    }

    private void fillStatus() {
        final ArrayList arrayList = new ArrayList();
        final SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.logicalRef = "-1";
        spinnerItem.value = this.getString(R.string.menu_all);
        final SpinnerItem spinnerItem2 = new SpinnerItem();
        spinnerItem2.logicalRef = BuildConfig.NETSIS_DEMO_PASSWORD;
        spinnerItem2.value = this.getString(R.string.str_all_shipped);
        final SpinnerItem spinnerItem3 = new SpinnerItem();
        spinnerItem3.logicalRef = ExifInterface.GPS_MEASUREMENT_2D;
        spinnerItem3.value = this.getString(R.string.str_balance_order);
        final SpinnerItem spinnerItem4 = new SpinnerItem();
        spinnerItem4.logicalRef = ExifInterface.GPS_MEASUREMENT_3D;
        spinnerItem4.value = this.getString(R.string.str_never_shipped);
        arrayList.add(spinnerItem);
        arrayList.add(spinnerItem2);
        arrayList.add(spinnerItem3);
        arrayList.add(spinnerItem4);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner = spStatus;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(arrayAdapter);
    }

    private void fillOrderStatus() {
        final ArrayList arrayList = new ArrayList();
        final SpinnerItem spinnerItem = new SpinnerItem();
        spinnerItem.logicalRef = "-1";
        spinnerItem.value = this.getString(R.string.menu_all);
        final SpinnerItem spinnerItem2 = new SpinnerItem();
        spinnerItem2.logicalRef = BuildConfig.NETSIS_DEMO_PASSWORD;
        spinnerItem2.value = this.getString(R.string.str_suggestion);
        final SpinnerItem spinnerItem3 = new SpinnerItem();
        spinnerItem3.logicalRef = "4";
        spinnerItem3.value = this.getString(R.string.str_can_be_shipped);
        final SpinnerItem spinnerItem4 = new SpinnerItem();
        spinnerItem4.logicalRef = ExifInterface.GPS_MEASUREMENT_2D;
        spinnerItem4.value = this.getString(R.string.str_can_not_be_shipped);
        arrayList.add(spinnerItem);
        arrayList.add(spinnerItem2);
        arrayList.add(spinnerItem3);
        arrayList.add(spinnerItem4);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        final AppCompatSpinner appCompatSpinner = spOrderStatus;
        Intrinsics.checkNotNull(appCompatSpinner);
        appCompatSpinner.setAdapter(arrayAdapter);
    }

    
    public static boolean itemLongClickListenerlambda4(final ReportWcfOrderListActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final List<Orders> list = this0.wcfCollectionses;
        Intrinsics.checkNotNull(list);
        this0.showDetail(list.get(i2));
        return true;
    }

    private void showDetail(final Orders orders) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.report_order_list_column);
        final View findViewById = dialog.findViewById(R.id.tvType);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = dialog.findViewById(R.id.tvState);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = dialog.findViewById(R.id.tvStatus);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = dialog.findViewById(R.id.tvDate);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = dialog.findViewById(R.id.tvFicheNo);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = dialog.findViewById(R.id.tvAmount);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        ((AppCompatTextView) findViewById).setText(orders.getType());
        ((AppCompatTextView) findViewById2).setText(orders.getState());
        ((AppCompatTextView) findViewById3).setText(orders.getStatus());
        ((AppCompatTextView) findViewById4).setText(orders.getDate());
        ((AppCompatTextView) findViewById5).setText(orders.getFicheNo());
        ((AppCompatTextView) findViewById6).setText(orders.getAmount());
        dialog.show();
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
