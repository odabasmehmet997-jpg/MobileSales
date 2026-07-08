package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerClCard;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.reports.adapter.ReportExtractDetailAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;


/* compiled from: ReportExtractDetailActivity.kt */

public final class ReportExtractDetailActivity extends BaseReportWcfActivity {
    private ProgressDialog dialog;
    private int ficheRef;
    private ExtractHeader header;
    private int logicalRef;
    private ListView lvList;
    private ProgressDialog mProgressDialog;
    private int trCode;
    private AppCompatTextView tvCustomer;
    private AppCompatTextView tvDate;
    private AppCompatTextView tvDesc1;
    private AppCompatTextView tvDesc2;
    private AppCompatTextView tvDesc3;
    private AppCompatTextView tvDesc4;
    private AppCompatTextView tvEmpty;
    private AppCompatTextView tvFicheNo;
    private AppCompatTextView tvField1;
    private AppCompatTextView tvField2;
    private AppCompatTextView tvField3;
    private AppCompatTextView tvField4;
    private AppCompatTextView tvSpeCode;
    private AppCompatTextView tvTotal;

    /* compiled from: ReportExtractDetailActivity.kt */
    public static final class ExtractDetail {
        public String VAR1;
        public double VAR2;
        public String VAR3;
        public String VAR4 = "";
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        if (null != bundle && bundle.containsKey("header")) {
            header = (ExtractHeader) bundle.getSerializable("header");
        }
        this.setContentView(R.layout.extract_detail);
        this.setToolbar();
        final Bundle extras = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        if (extras.containsKey("sFicheRef")) {
            ficheRef = extras.getInt("sFicheRef");
        }
        if (extras.containsKey("TRCODE")) {
            trCode = extras.getInt("TRCODE");
        }
        if (extras.containsKey("CLREF")) {
            this.setClRef(extras.getInt("CLREF"));
        }
        if (extras.containsKey("logicalRef")) {
            logicalRef = extras.getInt("logicalRef");
        }
        this.initialize();
        if (null == this.header) {
            if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.f1172GO || this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.TIGER) {
                mProgressDialog = new ProgressDialog(this);
                this.setClientForTiger(new ServicesClientForTiger(this));
                final int i2 = trCode;
                if (61 == i2 || 62 == i2 || 63 == i2 || 64 == i2) {
                    this.getExtractDeedHeader(ficheRef);
                } else if (41 == i2 || 42 == i2) {
                    this.getExctractCashCreditHeaderDuaDate(logicalRef);
                } else if (20 == i2 || 21 == i2) {
                    this.getExtractMoneyOrderHeader(ficheRef);
                } else {
                    this.getExtractCashCreditHeader(ficheRef);
                }
            }
        } else {
            this.setExtractDetail();
        }
        this.setFicheType();
    }

    private void setFicheType() {
        final int i2 = trCode;
        if (61 == i2 || 62 == i2 || 63 == i2 || 64 == i2) {
            final AppCompatTextView appCompatTextView = tvField1;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setText(this.getString(R.string.str_expiry));
            final AppCompatTextView appCompatTextView2 = tvField2;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setText(this.getString(R.string.str_quantity));
            final AppCompatTextView appCompatTextView3 = tvField3;
            Intrinsics.checkNotNull(appCompatTextView3);
            appCompatTextView3.setText(this.getString(R.string.str_serial_number));
            final AppCompatTextView appCompatTextView4 = tvField4;
            Intrinsics.checkNotNull(appCompatTextView4);
            appCompatTextView4.setVisibility(8);
            final AppCompatTextView appCompatTextView5 = tvEmpty;
            Intrinsics.checkNotNull(appCompatTextView5);
            appCompatTextView5.setVisibility(8);
            return;
        }
        final AppCompatTextView appCompatTextView6 = tvField1;
        Intrinsics.checkNotNull(appCompatTextView6);
        appCompatTextView6.setText(this.getString(R.string.str_special_code));
        final AppCompatTextView appCompatTextView7 = tvField2;
        Intrinsics.checkNotNull(appCompatTextView7);
        appCompatTextView7.setText(this.getString(R.string.str_quantity));
        final AppCompatTextView appCompatTextView8 = tvField3;
        Intrinsics.checkNotNull(appCompatTextView8);
        appCompatTextView8.setText(this.getString(R.string.str_explanation));
        final AppCompatTextView appCompatTextView9 = tvField4;
        Intrinsics.checkNotNull(appCompatTextView9);
        appCompatTextView9.setText(this.getString(R.string.str_receipt_number));
    }

    private void initialize() {
        final View findViewById = this.findViewById(R.id.tvDate);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDate = (AppCompatTextView) findViewById;
        final View findViewById2 = this.findViewById(R.id.tvFicheNo);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvFicheNo = (AppCompatTextView) findViewById2;
        final View findViewById3 = this.findViewById(R.id.tvCustomer);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvCustomer = (AppCompatTextView) findViewById3;
        final View findViewById4 = this.findViewById(R.id.tvSpecode);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvSpeCode = (AppCompatTextView) findViewById4;
        final View findViewById5 = this.findViewById(R.id.tvDesc1);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDesc1 = (AppCompatTextView) findViewById5;
        final View findViewById6 = this.findViewById(R.id.tvDesc2);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDesc2 = (AppCompatTextView) findViewById6;
        final View findViewById7 = this.findViewById(R.id.tvDesc3);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDesc3 = (AppCompatTextView) findViewById7;
        final View findViewById8 = this.findViewById(R.id.tvDesc4);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDesc4 = (AppCompatTextView) findViewById8;
        final View findViewById9 = this.findViewById(R.id.lvList);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.ListView");
        lvList = (ListView) findViewById9;
        final View findViewById10 = this.findViewById(R.id.tvTotal);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvTotal = (AppCompatTextView) findViewById10;
        final View findViewById11 = this.findViewById(R.id.tvField1);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvField1 = (AppCompatTextView) findViewById11;
        final View findViewById12 = this.findViewById(R.id.tvField2);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvField2 = (AppCompatTextView) findViewById12;
        final View findViewById13 = this.findViewById(R.id.tvField3);
        Intrinsics.checkNotNull(findViewById13, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvField3 = (AppCompatTextView) findViewById13;
        final View findViewById14 = this.findViewById(R.id.tvField4);
        Intrinsics.checkNotNull(findViewById14, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvField4 = (AppCompatTextView) findViewById14;
        final View findViewById15 = this.findViewById(R.id.tvEmpty);
        Intrinsics.checkNotNull(findViewById15, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvEmpty = (AppCompatTextView) findViewById15;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        List emptyList;
        if (processType == ProcessType.FILLREPORTCASHCASE) {
            if (null != reportxml) {
                final List<REPORTLINE> resultLine = reportxml.getResultLine();
                Intrinsics.checkNotNullExpressionValue(resultLine, "getResultLine(...)");
                if (resultLine.isEmpty()) {
                    return;
                }
                final ExtractHeader extractHeader = new ExtractHeader();
                extractHeader.setDATE_(this.getReportLinesValues(reportxml.getResultLine().get(0).DATE_));
                extractHeader.setROLLNO(this.getReportLinesValues(reportxml.getResultLine().get(0).FICHENO));
                extractHeader.setCLCODE(this.getReportWcfQueriesViewModel().getSqlHelper().getSingleField(CustomerClCard.class.getSimpleName(), "CODE || '/' || DEFINITION_", "LOGICALREF=" + this.getClRef(), false));
                extractHeader.setSPECODE(this.getReportLinesValues(reportxml.getResultLine().get(0).SPECCODE));
                extractHeader.setGENEXP1(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP1));
                extractHeader.setGENEXP2(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP2));
                extractHeader.setGENEXP3(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP3));
                extractHeader.setGENEXP4(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP4));
                header = extractHeader;
                this.getExtractCashCreditDetail(ficheRef);
                return;
            }
            if (null == this.mProgressDialog || this.isActivityDestroyed()) {
                return;
            }
            final ProgressDialog progressDialog = mProgressDialog;
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                final ProgressDialog progressDialog2 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog2);
                progressDialog2.dismiss();
                return;
            }
            return;
        }
        if (processType == ProcessType.FILLREPORTCASH) {
            if (this.isActivityDestroyed()) {
                return;
            }
            if (null != this.mProgressDialog && !this.isActivityDestroyed()) {
                final ProgressDialog progressDialog3 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog3);
                if (progressDialog3.isShowing()) {
                    final ProgressDialog progressDialog4 = mProgressDialog;
                    Intrinsics.checkNotNull(progressDialog4);
                    progressDialog4.dismiss();
                }
            }
            if (null != reportxml) {
                final List<REPORTLINE> resultLine2 = reportxml.getResultLine();
                Intrinsics.checkNotNullExpressionValue(resultLine2, "getResultLine(...)");
                if (resultLine2.isEmpty()) {
                    return;
                }
                final ExtractHeader extractHeader2 = header;
                Intrinsics.checkNotNull(extractHeader2);
                extractHeader2.setDetailList(new ArrayList());
                for (final REPORTLINE reportline : reportxml.getResultLine()) {
                    final ExtractDetail extractDetail = new ExtractDetail();
                    extractDetail.VAR1 = this.getReportLinesValues(reportline.SPECODE);
                    extractDetail.VAR2 = reportline.AMOUNT;
                    extractDetail.VAR3 = this.getReportLinesValues(reportline.LINEEXP);
                    final int i2 = trCode;
                    if (1 == i2 || 70 == i2 || 20 == i2 || 21 == i2) {
                        extractDetail.VAR4 = this.getReportLinesValues(reportline.DOCODE);
                    }
                    final ExtractHeader extractHeader3 = header;
                    Intrinsics.checkNotNull(extractHeader3);
                    extractHeader3.getDetailList().add(extractDetail);
                }
                this.setExtractDetail();
                return;
            }
            return;
        }
        if (processType == ProcessType.FILLREPORTCHEQUE) {
            if (null != reportxml) {
                final List<REPORTLINE> resultLine3 = reportxml.getResultLine();
                Intrinsics.checkNotNullExpressionValue(resultLine3, "getResultLine(...)");
                if (resultLine3.isEmpty()) {
                    return;
                }
                final ExtractHeader extractHeader4 = new ExtractHeader();
                extractHeader4.setDATE_(this.getReportLinesValues(reportxml.getResultLine().get(0).DATE_));
                extractHeader4.setROLLNO(this.getReportLinesValues(reportxml.getResultLine().get(0).ROLLNO));
                extractHeader4.setCLCODE(this.getReportWcfQueriesViewModel().getSqlHelper().getSingleField(CustomerClCard.class.getSimpleName(), "CODE || '/' || DEFINITION_", "LOGICALREF=" + this.getClRef(), false));
                extractHeader4.setSPECODE(this.getReportLinesValues(reportxml.getResultLine().get(0).SPECODE));
                extractHeader4.setGENEXP1(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP1));
                extractHeader4.setGENEXP2(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP2));
                extractHeader4.setGENEXP3(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP3));
                extractHeader4.setGENEXP4(this.getReportLinesValues(reportxml.getResultLine().get(0).GENEXP4));
                header = extractHeader4;
                this.getExtractDeedDetail(ficheRef);
                return;
            }
            if (null == this.mProgressDialog || this.isActivityDestroyed()) {
                return;
            }
            final ProgressDialog progressDialog5 = mProgressDialog;
            Intrinsics.checkNotNull(progressDialog5);
            if (progressDialog5.isShowing()) {
                final ProgressDialog progressDialog6 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog6);
                progressDialog6.dismiss();
                return;
            }
            return;
        }
        if (processType == ProcessType.FILLREPORTDEED) {
            if (null != this.mProgressDialog && !this.isActivityDestroyed()) {
                final ProgressDialog progressDialog7 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog7);
                if (progressDialog7.isShowing()) {
                    final ProgressDialog progressDialog8 = mProgressDialog;
                    Intrinsics.checkNotNull(progressDialog8);
                    progressDialog8.dismiss();
                }
            }
            if (null != reportxml) {
                final List<REPORTLINE> resultLine4 = reportxml.getResultLine();
                Intrinsics.checkNotNullExpressionValue(resultLine4, "getResultLine(...)");
                if (resultLine4.isEmpty()) {
                    return;
                }
                final ExtractHeader extractHeader5 = header;
                Intrinsics.checkNotNull(extractHeader5);
                extractHeader5.setDetailList(new ArrayList());
                for (final REPORTLINE reportline2 : reportxml.getResultLine()) {
                    final ExtractDetail extractDetail2 = new ExtractDetail();
                    final int i3 = trCode;
                    if (1 == i3 || 70 == i3) {
                        extractDetail2.VAR1 = this.getReportLinesValues(reportline2.VAR1);
                    } else {
                        final String VAR1 = reportline2.VAR1;
                        Intrinsics.checkNotNullExpressionValue(VAR1, "VAR1");
                        final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(VAR1, 0);
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
                        extractDetail2.VAR1 = this.getReportLinesValues(DateAndTimeUtils.convertDateY(((String[]) emptyList.toArray(new String[0]))[0]));
                    }
                    extractDetail2.VAR2 = Float.parseFloat(this.getReportLinesValues(reportline2.VAR2));
                    extractDetail2.VAR3 = this.getReportLinesValues(reportline2.VAR3);
                    final int i4 = trCode;
                    if (1 == i4 || 70 == i4 || 20 == i4 || 21 == i4) {
                        extractDetail2.VAR4 = this.getReportLinesValues(reportline2.VAR4);
                    }
                    final ExtractHeader extractHeader6 = header;
                    Intrinsics.checkNotNull(extractHeader6);
                    extractHeader6.getDetailList().add(extractDetail2);
                }
                this.setExtractDetail();
            }
        }
    }

    private String getReportLinesValues(final String str) {
        return null == str ? "" : str;
    }

    /* compiled from: ReportExtractDetailActivity.kt */
    @SuppressLint("StaticFieldLeak")
    public final class Retrieve extends AsyncTask<String, Void, String> {
        public Retrieve() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            final ReportExtractDetailActivity reportExtractDetailActivity = ReportExtractDetailActivity.this;
            reportExtractDetailActivity.dialog = ProgressDialog.show(reportExtractDetailActivity, "", reportExtractDetailActivity.getString(R.string.str_please_wait), true);
        }

        @Override // android.os.AsyncTask
        protected String doInBackground(final String... params) {
            Intrinsics.checkNotNullParameter(params, "params");
            final ReportExtractDetailActivity reportExtractDetailActivity = ReportExtractDetailActivity.this;
            reportExtractDetailActivity.header = reportExtractDetailActivity.getExtractDetailList(reportExtractDetailActivity.ficheRef);
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(final String str) {
            super.onPostExecute((Retrieve) str);
            if (null != ReportExtractDetailActivity.this.dialog) {
                final ProgressDialog progressDialog = dialog;
                Intrinsics.checkNotNull(progressDialog);
                if (progressDialog.isShowing()) {
                    final ProgressDialog progressDialog2 = dialog;
                    Intrinsics.checkNotNull(progressDialog2);
                    progressDialog2.dismiss();
                }
            }
            final ListView listView = lvList;
            Intrinsics.checkNotNull(listView);
            listView.setEnabled(true);
            if (null != ReportExtractDetailActivity.this.header) {
                setExtractDetail();
            }
        }
    }

    
    public void setExtractDetail() {
        List emptyList;
        try {
            final AppCompatTextView appCompatTextView = tvDate;
            Intrinsics.checkNotNull(appCompatTextView);
            final ExtractHeader extractHeader = header;
            Intrinsics.checkNotNull(extractHeader);
            final String date_ = extractHeader.getDATE_();
            Intrinsics.checkNotNull(date_);
            final List<String> split = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(date_, 0);
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
            appCompatTextView.setText(((String[]) emptyList.toArray(new String[0]))[0]);
        } catch (final Exception unused) {
            final AppCompatTextView appCompatTextView2 = tvDate;
            Intrinsics.checkNotNull(appCompatTextView2);
            final ExtractHeader extractHeader2 = header;
            Intrinsics.checkNotNull(extractHeader2);
            appCompatTextView2.setText(extractHeader2.getDATE_());
        }
        final AppCompatTextView appCompatTextView3 = tvFicheNo;
        Intrinsics.checkNotNull(appCompatTextView3);
        final ExtractHeader extractHeader3 = header;
        Intrinsics.checkNotNull(extractHeader3);
        appCompatTextView3.setText(extractHeader3.getROLLNO());
        final AppCompatTextView appCompatTextView4 = tvCustomer;
        Intrinsics.checkNotNull(appCompatTextView4);
        final ExtractHeader extractHeader4 = header;
        Intrinsics.checkNotNull(extractHeader4);
        appCompatTextView4.setText(extractHeader4.getCLCODE());
        final AppCompatTextView appCompatTextView5 = tvSpeCode;
        Intrinsics.checkNotNull(appCompatTextView5);
        final ExtractHeader extractHeader5 = header;
        Intrinsics.checkNotNull(extractHeader5);
        appCompatTextView5.setText(extractHeader5.getSPECODE());
        final AppCompatTextView appCompatTextView6 = tvDesc1;
        Intrinsics.checkNotNull(appCompatTextView6);
        final ExtractHeader extractHeader6 = header;
        Intrinsics.checkNotNull(extractHeader6);
        appCompatTextView6.setText(extractHeader6.getGENEXP1());
        final AppCompatTextView appCompatTextView7 = tvDesc2;
        Intrinsics.checkNotNull(appCompatTextView7);
        final ExtractHeader extractHeader7 = header;
        Intrinsics.checkNotNull(extractHeader7);
        appCompatTextView7.setText(extractHeader7.getGENEXP2());
        final AppCompatTextView appCompatTextView8 = tvDesc3;
        Intrinsics.checkNotNull(appCompatTextView8);
        final ExtractHeader extractHeader8 = header;
        Intrinsics.checkNotNull(extractHeader8);
        appCompatTextView8.setText(extractHeader8.getGENEXP3());
        final AppCompatTextView appCompatTextView9 = tvDesc4;
        Intrinsics.checkNotNull(appCompatTextView9);
        final ExtractHeader extractHeader9 = header;
        Intrinsics.checkNotNull(extractHeader9);
        appCompatTextView9.setText(extractHeader9.getGENEXP4());
        final ExtractHeader extractHeader10 = header;
        Intrinsics.checkNotNull(extractHeader10);
        if (extractHeader10.getDetailList().isEmpty()) {
            return;
        }
        final ExtractHeader extractHeader11 = header;
        Intrinsics.checkNotNull(extractHeader11);
        final ReportExtractDetailAdapter reportExtractDetailAdapter = new ReportExtractDetailAdapter(this, extractHeader11.getDetailList(), trCode);
        final ListView listView = lvList;
        Intrinsics.checkNotNull(listView);
        listView.setAdapter(reportExtractDetailAdapter);
        final ExtractHeader extractHeader12 = header;
        Intrinsics.checkNotNull(extractHeader12);
        final int size = extractHeader12.getDetailList().size();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            final ExtractHeader extractHeader13 = header;
            Intrinsics.checkNotNull(extractHeader13);
            f2 += (float) extractHeader13.getDetailList().get(i2).VAR2;
        }
        final AppCompatTextView appCompatTextView10 = tvTotal;
        Intrinsics.checkNotNull(appCompatTextView10);
        appCompatTextView10.setText(StringUtils.fFormat(f2));
    }

    private void getExtractDeedHeader(final int i2) {
        final ProgressDialog progressDialog = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(this.getString(R.string.str_please_wait));
        final ProgressDialog progressDialog2 = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        this.setSelectResult(this.getReportWcfQueriesViewModel().getExtractChequeDeedHeader(i2));
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.status = "";
        }
        final SelectResult selectResult2 = this.getSelectResult();
        if (null != selectResult2) {
            selectResult2.errorString = "";
        }
        final SelectResult selectResult3 = this.getSelectResult();
        if (null != selectResult3) {
            selectResult3.resultXML = "";
        }
        final SelectResult selectResult4 = this.getSelectResult();
        if (null != selectResult4) {
            selectResult4.type = ProcessType.FILLREPORTCHEQUE;
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
    }

    private void getExtractDeedDetail(int i2) {
        setSelectResult(getReportWcfQueriesViewModel().getExtractChequeDeedDetail(i2, getClRef()));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.status = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.errorString = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.resultXML = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTDEED;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
    }

    private void getExtractCashCreditHeader(int i2) {
        ProgressDialog progressDialog = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        setSelectResult(getReportWcfQueriesViewModel().getExtractCashCreditHeader(i2));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.status = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.errorString = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.resultXML = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTCASHCASE;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
    }

    private void getExctractCashCreditHeaderDuaDate(int i2) {
        ProgressDialog progressDialog = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        setSelectResult(getReportWcfQueriesViewModel().getExtractCashCreditHeaderDueDate(i2));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.status = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.errorString = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.resultXML = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTCASHCASE;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
    }

    private void getExtractCashCreditDetail(int i2) {
        setSelectResult(getReportWcfQueriesViewModel().getExtractCashCreditDetail(i2, this.trCode, getClRef()));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.status = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.errorString = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.resultXML = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTCASH;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
    }

    private void getExtractMoneyOrderHeader(int i2) {
        ProgressDialog progressDialog = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        setSelectResult(getReportWcfQueriesViewModel().getExtractMoneyOrderHeader(i2));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.status = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.errorString = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.resultXML = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTCASHCASE;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(this.getSelectResult()).execute();
    }

    
    public ExtractHeader getExtractDetailList(final int i2) {
        final SelectResult extractCashCreditHeader = this.getReportWcfQueriesViewModel().getExtractCashCreditHeader(i2);
        final HashMap hashMap = new HashMap();
        final String firmano = USER.firmano;
        Intrinsics.checkNotNullExpressionValue(firmano, "firmano");
        hashMap.put("firmano", firmano);
        hashMap.put("userid", String.valueOf(USER.userid));
        final int i3 = trCode;
        if (61 == i3 || 62 == i3 || 63 == i3 || 64 == i3) {
            hashMap.put(FirebaseAnalytics.Param.METHOD, "getExtractChequeDeedDetail");
            hashMap.put("sFicheRef", "" + i2);
            return null;
        }
        hashMap.put(FirebaseAnalytics.Param.METHOD, "getExtractCashCreditDetail");
        final String sql = extractCashCreditHeader.sql;
        Intrinsics.checkNotNullExpressionValue(sql, "sql");
        hashMap.put("SQLHEADER", sql);
        final String sql2 = this.getReportWcfQueriesViewModel().getExtractCashCreditDetail(i2, trCode, this.getClRef()).sql;
        Intrinsics.checkNotNullExpressionValue(sql2, "sql");
        hashMap.put("SQLDETAIL", sql2);
        hashMap.put("trCode", "" + trCode);
        return null;
    }

    /* compiled from: ReportExtractDetailActivity.kt */
    public static final class ExtractHeader implements Serializable {
        public static final Companion Companion = new Companion(null);
        private static final long serialVersionUID = 1;
        private int CARDREF;
        private String CLCODE;
        private String DATE_;
        private String GENEXP1;
        private String GENEXP2;
        private String GENEXP3;
        private String GENEXP4;
        private String ROLLNO;
        private String SPECODE;
        public List<ExtractDetail> detailList;

        public String getDATE_() {
            return DATE_;
        }

        public void setDATE_(final String str) {
            DATE_ = str;
        }

        public String getROLLNO() {
            return ROLLNO;
        }

        public void setROLLNO(final String str) {
            ROLLNO = str;
        }

        public int getCARDREF() {
            return CARDREF;
        }

        public void setCARDREF(final int i2) {
            CARDREF = i2;
        }

        public String getSPECODE() {
            return SPECODE;
        }

        public void setSPECODE(final String str) {
            SPECODE = str;
        }

        public String getGENEXP1() {
            return GENEXP1;
        }

        public void setGENEXP1(final String str) {
            GENEXP1 = str;
        }

        public String getGENEXP2() {
            return GENEXP2;
        }

        public void setGENEXP2(final String str) {
            GENEXP2 = str;
        }

        public String getGENEXP3() {
            return GENEXP3;
        }

        public void setGENEXP3(final String str) {
            GENEXP3 = str;
        }

        public String getGENEXP4() {
            return GENEXP4;
        }

        public void setGENEXP4(final String str) {
            GENEXP4 = str;
        }

        public List<ExtractDetail> getDetailList() {
            final List<ExtractDetail> list = detailList;
            if (null != list) {
                return list;
            }
            Intrinsics.throwUninitializedPropertyAccessException("detailList");
            return null;
        }

        public void setDetailList(final List<ExtractDetail> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            detailList = list;
        }

        public String getCLCODE() {
            return CLCODE;
        }

        public void setCLCODE(final String str) {
            CLCODE = str;
        }

        /* compiled from: ReportExtractDetailActivity.kt */
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
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
