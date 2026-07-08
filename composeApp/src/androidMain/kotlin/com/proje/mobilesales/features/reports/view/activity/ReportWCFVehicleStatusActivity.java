package com.proje.mobilesales.features.reports.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.features.reports.adapter.ReportVehicleStatusAdapter;
import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ReportWCFVehicleStatusActivity.kt */

public final class ReportWCFVehicleStatusActivity extends BaseReportWcfActivity {
    private ReportVehicleStatusAdapter adapter;
    private Bundle bundle;
    private LinearLayout linearProgress;
    private ListView lvReportVehicleStatus;
    private Menu menu;
    private REPORTXML reportXml;
    private boolean loadingMore = true;
    private final AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportWCFVehicleStatusActivityscrollListener1
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
                final ReportWCFVehicleStatusActivity reportWCFVehicleStatusActivity = ReportWCFVehicleStatusActivity.this;
                reportxml = reportWCFVehicleStatusActivity.reportXml;
                Intrinsics.checkNotNull(reportxml);
                reportWCFVehicleStatusActivity.exeRetrieve(reportxml.reportLines.size());
            }
        }
    };

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        if (null != bundle) {
            this.restoreActivityBundle(bundle);
        }
        this.getExtras();
        if (0 == this.customerRef) {
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
        this.setContentView(R.layout.report_vehicle_status);
        this.setToolbar();
        this.initialize();
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            Intrinsics.checkNotNull(reportxml);
            final ProcessType type2 = this.getType();
            Intrinsics.checkNotNull(type2);
            this.processFinish(reportxml, type2);
            return;
        }
        this.exeRetrieve(0);
    }

    
    public boolean onCreateOptionsMenu(final Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.getMenuInflater().inflate(R.menu.report_backload, menu);
        menu.findItem(R.id.reportRefresh).setVisible(false);
        menu.findItem(R.id.reportBackLoad).setVisible(false);
        menu.findItem(R.id.reportFullScreen).setVisible(false);
        menu.findItem(R.id.menu_sort_customer_name).setVisible(true);
        this.menu = menu;
        return true;
    }

    private void initialize() {
        final View findViewById = this.findViewById(R.id.lvReportVehicleStatus);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ListView");
        final ListView listView = (ListView) findViewById;
        lvReportVehicleStatus = listView;
        Intrinsics.checkNotNull(listView);
        listView.setOnScrollListener(scrollListener);
        final View findViewById2 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.LinearLayout");
        linearProgress = (LinearLayout) findViewById2;
        this.setClientForTiger(new ServicesClientForTiger(this));
    }

    
    public void exeRetrieve(final int i2) {
        final int warehouseNr = baseErp.isWhTransfer() ? baseErp.getUser().getWarehouseNr() : baseErp.getSalesFicheHeaderDefaultDispatchWareHouse();
        final ReportWcfQueriesViewModel reportWcfQueriesViewModel = this.getReportWcfQueriesViewModel();
        final ProcessType type = this.getType();
        final ReportSortType reportSortType = this.getReportSortType();
        Intrinsics.checkNotNull(reportSortType);
        this.setSelectResult(reportWcfQueriesViewModel.getVehicleStatus(i2, type, warehouseNr, reportSortType));
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
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
        if (processType != ProcessType.FILLSPIN) {
            final LinearLayout linearLayout = linearProgress;
            Intrinsics.checkNotNull(linearLayout);
            linearLayout.setVisibility(0);
            final ListView listView = lvReportVehicleStatus;
            Intrinsics.checkNotNull(listView);
            listView.setEnabled(false);
        }
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
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
                    adapter = new ReportVehicleStatusAdapter(this, reportLines);
                    final ListView listView = lvReportVehicleStatus;
                    Intrinsics.checkNotNull(listView);
                    listView.setAdapter(adapter);
                } else {
                    final int size2 = reportxml.reportLines.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        final REPORTXML reportxml3 = reportXml;
                        Intrinsics.checkNotNull(reportxml3);
                        reportxml3.reportLines.add(reportxml.reportLines.get(i2));
                    }
                    final ReportVehicleStatusAdapter reportVehicleStatusAdapter = adapter;
                    Intrinsics.checkNotNull(reportVehicleStatusAdapter);
                    reportVehicleStatusAdapter.notifyDataSetChanged();
                }
            } else {
                loadingMore = false;
                final ListView listView2 = lvReportVehicleStatus;
                Intrinsics.checkNotNull(listView2);
                listView2.setAdapter(null);
            }
        } else {
            loadingMore = false;
            final ListView listView3 = lvReportVehicleStatus;
            Intrinsics.checkNotNull(listView3);
            listView3.setAdapter(null);
        }
        final LinearLayout linearLayout = linearProgress;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(8);
        final ListView listView4 = lvReportVehicleStatus;
        Intrinsics.checkNotNull(listView4);
        listView4.setEnabled(true);
    }

    @Override // com.proje.mobilesales.core.base.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        final REPORTXML reportxml = reportXml;
        if (null != reportxml) {
            outState.putSerializable("reportXml", reportxml);
        }
        outState.putBoolean("loadingMore", loadingMore);
    }

    
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        this.restoreActivityBundle(savedInstanceState);
    }

    private void restoreActivityBundle(final Bundle bundle) {
        if (bundle.containsKey("reportXml")) {
            reportXml = (REPORTXML) bundle.getSerializable("reportXml");
        }
        if (bundle.containsKey("loadingMore")) {
            loadingMore = bundle.getBoolean("loadingMore");
        }
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemId = item.getItemId();
        if (16908332 == itemId) {
            this.finish();
            return true;
        }
        if (R.id.menu_sort_customer_name == itemId) {
            this.toggleSort(item);
            adapter = null;
            final ListView listView = lvReportVehicleStatus;
            Intrinsics.checkNotNull(listView);
            listView.setAdapter(null);
            reportXml = null;
            this.exeRetrieve(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
