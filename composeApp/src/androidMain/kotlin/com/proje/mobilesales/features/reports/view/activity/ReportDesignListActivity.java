package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.base.BaseListActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.PrintResponse;
import com.proje.mobilesales.core.service.PrinterPublicFactory;
import com.proje.mobilesales.core.service.PrinterServiceApi;
import com.proje.mobilesales.features.reports.adapter.ReportDesignListRecyclerViewAdapter;
import com.proje.mobilesales.features.reports.model.ReportItem;
import com.proje.mobilesales.features.reports.model.ReportResponse;
import com.proje.mobilesales.features.reports.model.ResponseError;
import com.proje.mobilesales.features.reports.model.enums.ReportPlatformType;
import com.proje.mobilesales.features.reports.model.enums.ReportType;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public final class ReportDesignListActivity extends BaseListActivity {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_GET_REPORTS = 999;
    private ReportDesignListRecyclerViewAdapter adapter;
    private PrinterServiceApi mPrintersApi;
    private RecyclerView recyclerView;

    protected Fragment instantiateListFragment() {
        return null;
    }
    public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        setContentView(R.layout.activity_report_design_list);
        setToolbar();
        ReportDesignListRecyclerViewAdapter reportDesignListRecyclerViewAdapter = new ReportDesignListRecyclerViewAdapter(this, new ArrayList());
        this.adapter = reportDesignListRecyclerViewAdapter;
        Intrinsics.checkNotNull(reportDesignListRecyclerViewAdapter);
        reportDesignListRecyclerViewAdapter.setmActivity(this);
        View findViewById = findViewById(R.id.rwReportDesignList);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        this.recyclerView = recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerView2 = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setLayoutManager(linearLayoutManager);
        getReportDesigns();
    }

    private boolean createService(String str) {
        if (TextUtils.isEmpty(Preferences.getPrinterServiceAddress(this)) || str.length() == 0) {
            return false;
        }
        try {
            Call.Factory providePrintersPublicCallFactory = new CommunicationModule(null, null).providePrintersPublicCallFactory();
            Intrinsics.checkNotNullExpressionValue(providePrintersPublicCallFactory, "providePrintersPublicCallFactory(...)");
            this.mPrintersApi = new PrinterPublicFactory.Impl(providePrintersPublicCallFactory).rxEnabled(true).create(str, PrinterServiceApi.class);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
    @SuppressLint({"CheckResult"})
    private void getReportDesigns() {
        try {
            String printerServiceAddress = Preferences.getPrinterServiceAddress(this);
            Intrinsics.checkNotNullExpressionValue(printerServiceAddress, "getPrinterServiceAddress(...)");
            if (createService(printerServiceAddress)) {
                this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait)).show();
                PrinterServiceApi printerServiceApi = this.mPrintersApi;
                Single<ReportResponse> report = printerServiceApi != null ? printerServiceApi.getReport() : null;
                Intrinsics.checkNotNull(report);
                final ReportDesignListActivitygetReportDesigns1 reportDesignListActivitygetReportDesigns1 = new MutablePropertyReference1Impl() {
                    public Object get(Object obj) {
                        return Boolean.valueOf(((ReportResponse) obj).isSuccess());
                    }

                    public void set(Object obj, Object obj2) {
                        ((ReportResponse) obj).setSuccess(((Boolean) obj2).booleanValue());
                    }
                };
                Maybe<ReportResponse> filter = report.filter(new Predicate() {
                    public boolean test(Object obj) {
                        boolean reportDesignslambda0;
                        reportDesignslambda0 = ReportDesignListActivity.getReportDesignslambda0(Function1.this, obj);
                        return reportDesignslambda0;
                    }
                });
                final ReportDesignListActivitygetReportDesigns2 reportDesignListActivitygetReportDesigns2 = new Function1<ReportResponse, ArrayList<ReportItem>>() {
                    public ArrayList<ReportItem> invoke(Object reportResponse) {
                        Intrinsics.checkNotNullParameter(reportResponse, "reportResponse");
                        ArrayList<ReportItem> arrayList = new ArrayList<>();
                        List<ReportItem> result = reportResponse.getResult();
                        Intrinsics.checkNotNull(result);
                        for (ReportItem reportItem : result) {
                            if (reportItem.getReportType() == ReportType.DESIGN.getValue() && reportItem.getPlatformType() == ReportPlatformType.MOBILE_SALES.getValue()) {
                                arrayList.add(reportItem);
                            }
                        }
                        return arrayList;
                    }
                };
                Maybe subscribeOn = filter.map(new Function() {
                    public Object apply(Object obj) {
                        ArrayList reportDesignslambda1;
                        reportDesignslambda1 = ReportDesignListActivity.getReportDesignslambda1(Function1.this, obj);
                        return reportDesignslambda1;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
                final ReportDesignListActivitygetReportDesigns3 reportDesignListActivitygetReportDesigns3 = new ReportDesignListActivitygetReportDesigns3(this);
                Consumer consumer = new Consumer() {
                    public void accept(Object obj) {
                        ReportDesignListActivity.getReportDesignslambda2(Function1.this, obj);
                    }
                };
                final ReportDesignListActivitygetReportDesigns4 reportDesignListActivitygetReportDesigns4 = new ReportDesignListActivitygetReportDesigns4(this);
                subscribeOn.subscribe(consumer, new Consumer() {
                    public void accept(Object obj) {
                        ReportDesignListActivity.getReportDesignslambda3(Function1.this, obj);
                    }
                });
                return;
            }
            Toast.makeText(this, R.string.str_could_not_get_designs, Toast.LENGTH_LONG).show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    
    public static boolean getReportDesignslambda0(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return ((Boolean) tmp0.invoke(p0)).booleanValue();
    }

    
    public static ArrayList getReportDesignslambda1(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) tmp0.invoke(p0);
    }

    
    public static void getReportDesignslambda2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public static void getReportDesignslambda3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }

    
    public void handleResults(List<ReportItem> list) {
        this.mProgressDialogBuilder.dismiss();
        if (list.isEmpty()) {
            setResult(0, new Intent());
            finish();
            return;
        }
        ReportDesignListRecyclerViewAdapter reportDesignListRecyclerViewAdapter = new ReportDesignListRecyclerViewAdapter(this, list);
        this.adapter = reportDesignListRecyclerViewAdapter;
        Intrinsics.checkNotNull(reportDesignListRecyclerViewAdapter);
        reportDesignListRecyclerViewAdapter.setmActivity(this);
        RecyclerView recyclerView = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setAdapter(this.adapter);
    }

    
    public void handleError(Throwable th) {
        ResponseBody errorBody;
        this.mProgressDialogBuilder.dismiss();
        if (th instanceof SocketTimeoutException) {
            Toast.makeText(this, R.string.exp_28_socket_timeout, Toast.LENGTH_LONG).show();
            return;
        }
        if (th instanceof HttpException httpException) {
            if (httpException.response() != null) {
                Response<?> response = httpException.response();
                Intrinsics.checkNotNull(response);
                if (response.errorBody() != null) {
                    Gson gson = new Gson();
                    Response<?> response2 = httpException.response();
                    ArrayList<ResponseError> errors = gson.fromJson((response2 == null || (errorBody = response2.errorBody()) == null) ? null : errorBody.charStream(), PrintResponse.class).getErrors();
                    Intrinsics.checkNotNull(errors);
                    Toast.makeText(this, errors.get(0).getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
        Toast.makeText(this, th.getMessage(), Toast.LENGTH_LONG).show();
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.proje.mobilesales.core.base.BaseListActivity
    public String getDefaultTitle() {
        String string = getString(R.string.str_report_designs);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // com.proje.mobilesales.core.base.BaseInjectableActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* compiled from: ReportDesignListActivity.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
