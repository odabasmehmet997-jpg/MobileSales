package com.proje.mobilesales.features.reports.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportConstParamProp;
import com.proje.mobilesales.core.reportparser.ReportDesignerParser;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.features.customer.view.info.CustomerInfoActivity;
import com.proje.mobilesales.features.reports.adapter.AdapterListView;
import com.proje.mobilesales.features.reports.model.ReportListRowModel;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

import static kotlin.jvm.internal.Intrinsics.checkNotNullParameter;

public final class ReportAllActivity extends BaseErpActivity {
    private List<String> expandableListTitle;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    private HashMap<String, ArrayList<ReportListRowModel>> reportListRowModels = new HashMap<>();
    public List<String> getExpandableListTitle() {
        return expandableListTitle;
    }
    public void setExpandableListTitle(final List<String> list) {
        expandableListTitle = list;
    }
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
         this.getActivityComponent().inject(this);
         this.setContentView(R.layout.allreport);
         this.setToolbar();
        final Context context = ContextUtils.getmContext();
        final Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
         this.getExtras();
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this.findViewById(R.id.report_swipe_layout);
        mSwipeRefreshLayout = appBarSwipeRefreshLayout;
        if (null != appBarSwipeRefreshLayout) {
            appBarSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        }
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
        if (null != appBarSwipeRefreshLayout2) {
            appBarSwipeRefreshLayout2.setProgressBackgroundColorSchemeResource(R.color.redA200);
        }
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout3 = mSwipeRefreshLayout;
        if (null != appBarSwipeRefreshLayout3) {
            appBarSwipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                public void ReportAllActivityExternalSyntheticLambda0() {
                }
                public void onRefresh() {
                    onCreatelambda0(ReportAllActivity.this);
                }
            });
        }
         this.getMenu();
    }
    public static void onCreatelambda0(final ReportAllActivity this0) {
        checkNotNullParameter(this0, "this0");
        this0.getOnlineReports();
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = this0.mSwipeRefreshLayout;
        if (null == appBarSwipeRefreshLayout) {
            return;
        }
        appBarSwipeRefreshLayout.setRefreshing(false);
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    public void onResume() {
        super.onResume();
    }

    private String getSubTypeName(final int i2) {
        if (1 == i2) {
            final String string = this.getString(R.string.str_sales_reports);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (2 == i2) {
            final String string2 = this.getString(R.string.str_customer_reports);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        }
        if (3 == i2) {
            final String string3 = this.getString(R.string.str_collection_reports);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            return string3;
        }
        if (4 == i2) {
            final String string4 = this.getString(R.string.str_other_reports);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            return string4;
        }
        return "";
    }

    private Unit getMenu() {
        final ArrayList<ReportListRowModel> arrayList;
        reportListRowModels = new HashMap<>();
        final ArrayList<UserReports> userReports = baseErp.getLogoSqlHelper().getUserReports(ContextUtils.getStringResource(R.string.get_user_reports));
        Intrinsics.checkNotNull(userReports, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.userreport.model.database.UserReports>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.userreport.model.database.UserReports> }");
        ArrayList<ReportListRowModel> arrayList2 = new ArrayList<>();
        if (!userReports.isEmpty()) {
            int i2 = userReports.get(0).subType;
            final Iterator<UserReports> it = userReports.iterator();
            while (it.hasNext()) {
                final UserReports next = it.next();
                if (i2 != next.subType) {
                    reportListRowModels.put(this.getSubTypeName(i2), arrayList2);
                    arrayList2 = new ArrayList<>();
                    i2 = next.subType;
                }
                arrayList2.add(new ReportListRowModel(next.f1283id + 25, next));
            }
            if (!arrayList2.isEmpty()) {
                reportListRowModels.put(this.getSubTypeName(i2), arrayList2);
            }
        }
        if ((baseErp.getErpType() == ErpType.GO || baseErp.getErpType() == ErpType.TIGER) && null != (arrayList = this.reportListRowModels.get(getSubTypeName(1)))) {
            if (0 < this.customerRef) {
                for (int i3 = 0; 8 > i3; i3++) {
                    arrayList.add(new ReportListRowModel(i3));
                }
            }
            for (int i4 = 12; 23 > i4; i4++) {
                arrayList.add(new ReportListRowModel(i4));
            }
            if (0 >= this.customerRef) {
                arrayList.add(new ReportListRowModel(23));
                arrayList.add(new ReportListRowModel(24));
            }
        }
        expandableListTitle = new ArrayList(reportListRowModels.keySet());
        final View findViewById = this.findViewById(R.id.grid_allreport);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ExpandableListView");
        final ExpandableListView expandableListView = (ExpandableListView) findViewById;
        final List<String> list = expandableListTitle;
        expandableListView.setAdapter(null != list ? new AdapterListView(this, list, reportListRowModels, 2) : null);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { 
            public void ReportAllActivityExternalSyntheticLambda1() {
            }
            public boolean onChildClick(final ExpandableListView expandableListView2, final View view, final int i5, final int i6, final long j2) {
                final boolean _get_menu_lambda2;
                _get_menu_lambda2 = _get_menu_lambda2(ReportAllActivity.this, expandableListView2, view, i5, i6, j2);
                return _get_menu_lambda2;
            }
        });
        return Unit.INSTANCE;
    }

    public static boolean _get_menu_lambda2(final ReportAllActivity this0, final ExpandableListView expandableListView, final View view, final int i2, final int i3, final long j2) {
        checkNotNullParameter(this0, "this0");
        if (this0.baseErp.getErpRights().isDemo()) {
            Toast.makeText(this0, "Demo Kullanimda Online Raporlar Kullanamazsiniz", Toast.LENGTH_SHORT).show();
            return false;
        }
        this0.mProgressDialogBuilder.setMessage(this0.getString(R.string.str_please_wait)).show();
        final BaseErp baseErp = this0.baseErp;
        final WorkTimeControlProcessType workTimeControlProcessType = WorkTimeControlProcessType.Report;
        baseErp.checkRemoteWorkTimeControl(workTimeControlProcessType, new CheckWorkTimeListener(this0, workTimeControlProcessType, i2, i3));
        return false;
    }

    public String getReportXML(final int i2) {
        final String reportXML = baseErp.getLogoSqlHelper().getReportXML(i2);
        Intrinsics.checkNotNullExpressionValue(reportXML, "getReportXML(...)");
        return reportXML;
    }

    private Unit getOnlineReports() {
        mProgressDialogBuilder.setMessage(this.getString(R.string.str_please_wait)).show();
        final BaseErp baseErp = this.baseErp;
        final WorkTimeControlProcessType workTimeControlProcessType = WorkTimeControlProcessType.TransferGet;
        baseErp.checkRemoteWorkTimeControl(workTimeControlProcessType, new CheckWorkTimeListener(this, workTimeControlProcessType, 0, 0));
        return Unit.INSTANCE;
    } 
        private record CheckWorkTimeListener(WeakReference<ReportAllActivity> mActivity,
                                             WorkTimeControlProcessType mWorkTimeControlProcessType, int mGroupPosition,
                                             int mChildPosition) implements ResponseListener<String> {
            
            public   enum WhenMappings {
                ;
                public static final  int[] EnumSwitchMapping0;

                static {
                    final int[] iArr = new int[WorkTimeControlProcessType.values().length];
                    try {
                        iArr[WorkTimeControlProcessType.TransferGet.ordinal()] = 1;
                    } catch (final NoSuchFieldError unused) {
                    }
                    try {
                        iArr[WorkTimeControlProcessType.Report.ordinal()] = 2;
                    } catch (final NoSuchFieldError unused2) {
                    }
                    EnumSwitchMapping0 = iArr;
                }
            }
            public CheckWorkTimeListener(final ReportAllActivity mActivity, final WorkTimeControlProcessType mWorkTimeControlProcessType, final int mGroupPosition, final int mChildPosition) {
                checkNotNullParameter(mWorkTimeControlProcessType, "mWorkTimeControlProcessType");
                this.mWorkTimeControlProcessType = mWorkTimeControlProcessType;
                this.mGroupPosition = mGroupPosition;
                this.mChildPosition = mChildPosition;
                this.mActivity = new WeakReference<>(mActivity);
            }
            public void onResponse(final String str) {
                final ReportListRowModel reportListRowModel;
                final ProcessType processType;
                if (null == mActivity.get()) {
                    return;
                }
                final ReportAllActivity reportAllActivity = mActivity.get();
                Intrinsics.checkNotNull(reportAllActivity);
                if (reportAllActivity.isActivityDestroyed()) {
                    return;
                }
                final ReportAllActivity reportAllActivity2 = mActivity.get();
                Intrinsics.checkNotNull(reportAllActivity2);
                reportAllActivity2.mProgressDialogBuilder.dismiss();
                if (!TextUtils.isEmpty(str)) {
                    final ReportAllActivity reportAllActivity3 = mActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity3);
                    final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = reportAllActivity3.mSwipeRefreshLayout;
                    Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
                    if (appBarSwipeRefreshLayout.isRefreshing()) {
                        final ReportAllActivity reportAllActivity4 = mActivity.get();
                        Intrinsics.checkNotNull(reportAllActivity4);
                        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = reportAllActivity4.mSwipeRefreshLayout;
                        Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
                        appBarSwipeRefreshLayout2.setRefreshing(false);
                    }
                    Toast.makeText(mActivity.get(), str, 0).show();
                    return;
                }
                final int i2 = WhenMappings.EnumSwitchMapping0[mWorkTimeControlProcessType.ordinal()];
                if (1 == i2) {
                    final ReportAllActivity reportAllActivity5 = mActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity5);
                    reportAllActivity5.baseErp.getReportOnline(new ReportOnlineUpdateListener(mActivity.get()));
                    return;
                }
                int i3 = 2;
                if (2 != i2) {
                    return;
                }
                final ReportAllActivity reportAllActivity6 = mActivity.get();
                Intent intent = null;
                final List<String> expandableListTitle = null != reportAllActivity6 ? reportAllActivity6.getExpandableListTitle() : null;
                final int i4 = mGroupPosition;
                final int i5 = mChildPosition;
                final HashMap hashMap = null != reportAllActivity6 ? reportAllActivity6.reportListRowModels : null;
                if (null != hashMap) {
                    final ArrayList arrayList = (ArrayList) hashMap.get(null != expandableListTitle ? expandableListTitle.get(i4) : null);
                    if (null != arrayList) {
                        reportListRowModel = (ReportListRowModel) arrayList.get(i5);
                        Intrinsics.checkNotNull(reportListRowModel);
                        final int i6 = reportListRowModel.f1271id;
                        if (!reportListRowModel.isUserDefined) {
                            final ReportDesignerParser reportDesignerParser = new ReportDesignerParser();
                            final ReportAllActivity reportAllActivity7 = mActivity.get();
                            Intrinsics.checkNotNull(reportAllActivity7);
                            final UserReports userReports = reportListRowModel.userReport;
                            Intrinsics.checkNotNull(userReports);
                            final Report parseReportXml = reportDesignerParser.parseReportXml(reportAllActivity7.getReportXML(userReports.f1283id));
                            if (null == parseReportXml) {
                                final ReportAllActivity reportAllActivity8 = mActivity.get();
                                final ReportAllActivity reportAllActivity9 = mActivity.get();
                                Intrinsics.checkNotNull(reportAllActivity9);
                                Toast.makeText(reportAllActivity8, reportAllActivity9.getString(R.string.exp_84_unsupported_report_type), 0).show();
                                return;
                            }
                            final ReportAllActivity reportAllActivity10 = mActivity.get();
                            Intrinsics.checkNotNull(reportAllActivity10);
                            final int saveObject = reportAllActivity10.baseErp.saveObject(parseReportXml);
                            final Intent intent2 = new Intent(mActivity.get(), ReportUtil.getReportFromType(parseReportXml, ReportConstParamProp.None, -1));
                            intent2.putExtra("bigdata:synccode", saveObject);
                            final UserReports userReports2 = reportListRowModel.userReport;
                            Intrinsics.checkNotNull(userReports2);
                            intent2.putExtra("ReportName", userReports2.reportName);
                            final ReportAllActivity reportAllActivity11 = mActivity.get();
                            Intrinsics.checkNotNull(reportAllActivity11);
                            reportAllActivity11.startActivity(intent2);
                            return;
                        }
                        Class cls = ReportWCFSalesOrdInvActivity.class;
                        switch (i6) {
                            case 0:
                                final Intent intent3 = new Intent(mActivity.get(), ReportExtractActivity.class);
                                final ReportAllActivity reportAllActivity12 = mActivity.get();
                                Intrinsics.checkNotNull(reportAllActivity12);
                                intent3.putExtra(CustomerInfoActivity.EXTRAS_CLREF, reportAllActivity12.customerRef);
                                final ReportAllActivity reportAllActivity13 = mActivity.get();
                                Intrinsics.checkNotNull(reportAllActivity13);
                                reportAllActivity13.startActivity(intent3);
                                break;
                            case 1:
                                processType = ProcessType.FILLREPORTDEBITFOLLOW;
                                cls = ReportWcfDebitFollowActivity.class;
                                if (null != cls) {
                                    intent = new Intent(mActivity.get(), (Class<?>) cls);
                                    intent.putExtra("ProcessType", processType);
                                    final ReportAllActivity reportAllActivity14 = mActivity.get();
                                    Intrinsics.checkNotNull(reportAllActivity14);
                                    intent.putExtra("CLREF", reportAllActivity14.customerRef);
                                    intent.putExtra("ReportId", i3);
                                }
                                if (null != intent) {
                                    final ReportAllActivity reportAllActivity15 = mActivity.get();
                                    Intrinsics.checkNotNull(reportAllActivity15);
                                    reportAllActivity15.startActivityForResult(intent, 0);
                                    break;
                                }
                                break;
                            case 2:
                                processType = ProcessType.FILLREPORTCOLLECTIONSLIST;
                                i3 = 3;
                                cls = ReportWcfCollectionsListActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 3:
                                processType = ProcessType.FILLREPORTORDERLIST;
                                i3 = 17;
                                cls = ReportWcfOrderListActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 4:
                                processType = ProcessType.FILLREPORTINVOICEAVGTIME;
                                i3 = 18;
                                cls = ReportWcfInvoiceAvgTimeActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 5:
                                processType = ProcessType.FILLREPORTAVGCALC;
                                i3 = 19;
                                cls = ReportWcfAvgCalTimeActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 6:
                                i3 = 14;
                                processType = null;
                                cls = null;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 7:
                                processType = null;
                                cls = null;
                                i3 = 20;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            default:
                                i3 = 0;
                                processType = null;
                                cls = null;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 12:
                                processType = ProcessType.FILLREPORTORDER;
                                i3 = 4;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 13:
                                processType = ProcessType.FILLREPORTINVOICE;
                                i3 = 5;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 14:
                                processType = ProcessType.FILLREPORTRETURNINVOICE;
                                i3 = 6;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 15:
                                processType = ProcessType.FILLREPORTCASH;
                                i3 = 7;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 16:
                                processType = ProcessType.FILLREPORTCREDIT;
                                i3 = 8;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 17:
                                processType = ProcessType.FILLREPORTCASHCASE;
                                i3 = 9;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 18:
                                processType = ProcessType.FILLREPORTCHEQUE;
                                i3 = 10;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 19:
                                processType = ProcessType.FILLREPORTDEED;
                                i3 = 11;
                                cls = ReportWCFAllReportActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 20:
                                processType = ProcessType.FILLREPORTVEHICLESTATUS;
                                i3 = 16;
                                cls = ReportWCFVehicleStatusActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 21:
                                processType = ProcessType.FILLREPORTSALESUMMARY;
                                i3 = 12;
                                cls = ReportWCFSalesSummaryActivity.class;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 22:
                                processType = ProcessType.FILLREPORTSALESORD;
                                i3 = 13;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 23:
                                i3 = 14;
                                processType = ProcessType.FILLREPORTSALESINV;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                            case 24:
                                processType = ProcessType.FILLREPORTORDERTOTAL;
                                i3 = 20;
                                if (null != cls) {
                                }
                                if (null != intent) {
                                }
                                break;
                        }
                        return;
                    }
                }
                reportListRowModel = null;
                Intrinsics.checkNotNull(reportListRowModel);
                final int i62 = reportListRowModel.f1271id;
                if (!reportListRowModel.isUserDefined) {
                }
            } 
            public void onError(final String errorMessage) {
                checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mActivity.get()) {
                    final ReportAllActivity reportAllActivity = mActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity);
                    if (reportAllActivity.isActivityDestroyed()) {
                        return;
                    }
                    final ReportAllActivity reportAllActivity2 = mActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity2);
                    reportAllActivity2.mProgressDialogBuilder.dismiss();
                }
            }
        } 
        private record ReportOnlineUpdateListener(
            WeakReference<ReportAllActivity> mReportAllActivity) implements ResponseListener<Boolean> {
            private ReportOnlineUpdateListener(final ReportAllActivity mReportAllActivity) {
                this(new WeakReference<>(mReportAllActivity));
            } 
            public void onResponse(final Boolean bool) {
                if (null != this.mReportAllActivity.get()) {
                    final ReportAllActivity reportAllActivity = mReportAllActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity);
                    Intrinsics.checkNotNull(bool);
                    reportAllActivity.onOnlineReportUpdateDone(bool.booleanValue(), "");
                }
            }
 
            public void onError(final String errorMessage) {
                checkNotNullParameter(errorMessage, "errorMessage");
                if (null != this.mReportAllActivity.get()) {
                    Log.d("AA", "onError: " + errorMessage);
                    final ReportAllActivity reportAllActivity = mReportAllActivity.get();
                    Intrinsics.checkNotNull(reportAllActivity);
                    reportAllActivity.onOnlineReportUpdateDone(false, errorMessage);
                }
            }
        }

    public void onOnlineReportUpdateDone(final boolean z, final String str) {
        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = mSwipeRefreshLayout;
        Intrinsics.checkNotNull(appBarSwipeRefreshLayout);
        if (appBarSwipeRefreshLayout.isRefreshing()) {
            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout2 = mSwipeRefreshLayout;
            Intrinsics.checkNotNull(appBarSwipeRefreshLayout2);
            appBarSwipeRefreshLayout2.setRefreshing(false);
        }
        this.getMenu();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 1).show();
    } 
    public boolean onKeyDown(final int i2, final KeyEvent event) {
        checkNotNullParameter(event, "event");
        if (4 == i2) {
            this.finish();
            return true;
        }
        return super.onKeyDown(i2, event);
    }
}
