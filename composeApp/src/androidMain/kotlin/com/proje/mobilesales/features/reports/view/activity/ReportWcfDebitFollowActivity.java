package com.proje.mobilesales.features.reports.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.reports.adapter.ReportDebitFollowAdapter;
import java.util.List;
import java.util.ListIterator;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class ReportWcfDebitFollowActivity extends BaseReportWcfActivity {
    private ReportDebitFollowAdapter adapter;
    private ListView dataList;
    private final AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int i2, final long j2) {
            final boolean itemLongClickListenerlambda2;
            itemLongClickListenerlambda2 = itemLongClickListenerlambda2(ReportWcfDebitFollowActivity.this, adapterView, view, i2, j2);
            return itemLongClickListenerlambda2;
        }
    };
    private CustomerOperation mCustomerOperation;
    private LinearLayout progressListData;
    private REPORTXML reportxml;
    public static final Companion Companion = new Companion(null);
    private static final String STATE_CUSTOMER_REF = ReportWcfDebitFollowActivity.class.getName() + ".CUSTOMER_REF";
    private static final String STATE_CUSTOMER_OPERATION = ReportWcfDebitFollowActivity.class.getName() + ".CUSTOMER_OPERATION";

    public ListView getDataList() {
        return dataList;
    }

    public void setDataList(final ListView listView) {
        dataList = listView;
    }

    public ReportDebitFollowAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final ReportDebitFollowAdapter reportDebitFollowAdapter) {
        adapter = reportDebitFollowAdapter;
    }

    public REPORTXML getReportxml() {
        return reportxml;
    }

    public void setReportxml(final REPORTXML reportxml) {
        this.reportxml = reportxml;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.report_debit_follow);
        this.setToolbar();
        if (null != bundle) {
            this.setClRef(bundle.getInt(ReportWcfDebitFollowActivity.STATE_CUSTOMER_REF));
            mCustomerOperation = bundle.getParcelable(ReportWcfDebitFollowActivity.STATE_CUSTOMER_OPERATION);
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
        final View findViewById = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
        final LinearLayout linearLayout = (LinearLayout) findViewById;
        progressListData = linearLayout;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final View findViewById2 = this.findViewById(R.id.lvReportOrder);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById2;
        dataList = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnItemLongClickListener(itemLongClickListener);
        this.setClRef(customerRef);
        this.setType(this.CustomerToType(customerOperation.getMenuType()));
        this.setClientForTiger(new ServicesClientForTiger(this));
        this.getData();
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(0);
        final ListView listView = dataList;
        Intrinsics.checkNotNull(listView);
        listView.setEnabled(false);
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        this.fillDataList(reportxml);
    }

    private Unit getData() {
        this.setSelectResult(this.getReportWcfQueriesViewModel().getCustomerDebitTracking(this.getClRef()));
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.resultXML = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        this.setRetrieve(clientForTiger.new ServicesClientForTiger.ReportRetrieve(this.getSelectResult()));
        final ServicesClientForTiger.ReportRetrieve retrieve = this.getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
        return Unit.INSTANCE;
    }

    private void fillDataList(final REPORTXML reportxml) {
        if (null != (reportxml != null ? reportxml.reportLines : null)) {
            if (0 < reportxml.reportLines.size()) {
                this.reportxml = reportxml;
                final ReportDebitFollowAdapter reportDebitFollowAdapter = adapter;
                if (null == reportDebitFollowAdapter) {
                    final List<REPORTLINE> reportLines = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                    this.setDateReportLine(reportLines);
                    final String localCurrencyCode = baseErp.getLocalCurrencyCode();
                    final List<REPORTLINE> reportLines2 = reportxml.reportLines;
                    Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
                    Intrinsics.checkNotNull(localCurrencyCode);
                    adapter = new ReportDebitFollowAdapter(this, reportLines2, localCurrencyCode);
                    final ListView listView = dataList;
                    Intrinsics.checkNotNull(listView);
                    listView.setAdapter(adapter);
                    final ReportDebitFollowAdapter reportDebitFollowAdapter2 = adapter;
                    Intrinsics.checkNotNull(reportDebitFollowAdapter2);
                    reportDebitFollowAdapter2.notifyDataSetChanged();
                } else {
                    Intrinsics.checkNotNull(reportDebitFollowAdapter);
                    reportDebitFollowAdapter.notifyDataSetChanged();
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
        String[] strArr;
        List emptyList2;
        for (final REPORTLINE reportline : list) {
            final String DATE_ = reportline.DATE_;
            String[] strArr2 = null;
            if (null == DATE_) {
                strArr = null;
            } else {
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
                strArr = (String[]) emptyList.toArray(new String[0]);
            }
            String str = "";
            reportline.DATE_ = (null == strArr || 0 == strArr.length) ? "" : strArr[0];
            final String PROCDATE = reportline.PROCDATE;
            if (null != PROCDATE) {
                Intrinsics.checkNotNullExpressionValue(PROCDATE, "PROCDATE");
                final List<String> split2 = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(PROCDATE, 0);
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
                strArr2 = (String[]) emptyList2.toArray(new String[0]);
            }
            if (null != strArr2 && 0 != strArr2.length) {
                str = strArr2[0];
            }
            reportline.PROCDATE = str;
        }
    }

    
    public static boolean itemLongClickListenerlambda2(final ReportWcfDebitFollowActivity this0, final AdapterView adapterView, final View view, final int i2, final long j2) {
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
        dialog.setContentView(R.layout.report_debit_follow_column);
        final View findViewById = dialog.findViewById(R.id.tvProcessDate);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = dialog.findViewById(R.id.tvDate);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = dialog.findViewById(R.id.tvFicheNo);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = dialog.findViewById(R.id.tvProcess);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = dialog.findViewById(R.id.tvDebit);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = dialog.findViewById(R.id.tvCredit);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById7 = dialog.findViewById(R.id.tvRemaining);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView = dialog.findViewById(R.id.tvCurrency);
        ((AppCompatTextView) findViewById).setText(reportline.PROCDATE);
        ((AppCompatTextView) findViewById2).setText(reportline.DATE_);
        ((AppCompatTextView) findViewById3).setText(reportline.FICHENO);
        ((AppCompatTextView) findViewById4).setText(reportline.TRCODEDESC);
        final String B = reportline.f1204B;
        Intrinsics.checkNotNullExpressionValue(B, "B");
        ((AppCompatTextView) findViewById5).setText(StringUtils.fFormat(B));
        final String A = reportline.f1203A;
        Intrinsics.checkNotNullExpressionValue(A, "A");
        ((AppCompatTextView) findViewById6).setText(StringUtils.fFormat(A));
        final String K = reportline.f1205K;
        Intrinsics.checkNotNullExpressionValue(K, "K");
        ((AppCompatTextView) findViewById7).setText(StringUtils.fFormat(K));
        appCompatTextView.setText(TextUtils.isEmpty(reportline.CURCODE) ? baseErp.getLocalCurrencyCode() : reportline.CURCODE);
        dialog.show();
    }

    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable(ReportWcfDebitFollowActivity.STATE_CUSTOMER_OPERATION, mCustomerOperation);
        outState.putInt(ReportWcfDebitFollowActivity.STATE_CUSTOMER_REF, this.getClRef());
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* compiled from: ReportWcfDebitFollowActivity.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
