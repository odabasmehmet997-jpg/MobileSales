package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerClCard;
import com.proje.mobilesales.features.model.USER;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;


/* compiled from: ReportExtractCaseCashDetailActivity.kt */

public final class ReportExtractCaseCashDetailActivity extends BaseReportWcfActivity {
    private CASECASH caseCash;
    private ProgressDialog dialog;
    private int ficheRef;
    private ProgressDialog mProgressDialog;
    private AppCompatTextView tvAmount;
    private AppCompatTextView tvCustomer;
    private AppCompatTextView tvDate;
    private AppCompatTextView tvDesc;
    private AppCompatTextView tvDoCode;
    private AppCompatTextView tvFicheNo;
    private AppCompatTextView tvSpecode;

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void onPreExecute(final ProcessType processType) {
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.base.BaseErpActivity, com.proje.mobilesales.core.base.BaseInjectableActivity, com.proje.mobilesales.core.base.BaseTrackableActivity, com.proje.mobilesales.core.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        if (null != bundle && bundle.containsKey("header")) {
            caseCash = (CASECASH) bundle.getSerializable("caseCash");
        }
        this.setContentView(R.layout.extract_case_detail);
        this.setToolbar();
        final Bundle extras = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        if (extras.containsKey("sFicheRef")) {
            ficheRef = extras.getInt("sFicheRef");
        }
        if (extras.containsKey("CLREF")) {
            this.setClRef(extras.getInt("CLREF"));
        }
        this.initialize();
        if (null == this.caseCash) {
            if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.f1172GO || this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.TIGER) {
                mProgressDialog = new ProgressDialog(this);
                this.getExtractCaseCashDetail();
                return;
            }
            return;
        }
        this.setExtractDetail();
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
        tvSpecode = (AppCompatTextView) findViewById4;
        final View findViewById5 = this.findViewById(R.id.tvDesc);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDesc = (AppCompatTextView) findViewById5;
        final View findViewById6 = this.findViewById(R.id.tvDoCode);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvDoCode = (AppCompatTextView) findViewById6;
        final View findViewById7 = this.findViewById(R.id.tvAmount);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        tvAmount = (AppCompatTextView) findViewById7;
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        List emptyList;
        if (null != reportxml && null != reportxml.getResultLine()) {
            final List<REPORTLINE> resultLine = reportxml.getResultLine();
            Intrinsics.checkNotNullExpressionValue(resultLine, "getResultLine(...)");
            if (!resultLine.isEmpty()) {
                final CASECASH casecash = new CASECASH();
                final String DATE_ = reportxml.getResultLine().get(0).DATE_;
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
                casecash.setDATE_(((String[]) emptyList.toArray(new String[0]))[0]);
                casecash.setFICHENO(reportxml.getResultLine().get(0).FICHENO);
                casecash.setCLCODE(this.getReportWcfQueriesViewModel().getSqlHelper().getSingleField(CustomerClCard.class.getSimpleName(), "CODE || '/' || DEFINITION_", "LOGICALREF=" + this.getClRef(), false));
                casecash.setSPECODE(reportxml.getResultLine().get(0).SPECODE);
                casecash.setDESC(reportxml.getResultLine().get(0).LINEEXP);
                casecash.setDOCODE(reportxml.getResultLine().get(0).DOCODE);
                casecash.setAMOUNT(reportxml.getResultLine().get(0).AMOUNT);
                caseCash = casecash;
                this.setExtractDetail();
            }
        }
        if (this.isActivityDestroyed()) {
            return;
        }
        final ProgressDialog progressDialog = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.dismiss();
    }

    /* compiled from: ReportExtractCaseCashDetailActivity.kt */
    @SuppressLint("StaticFieldLeak")
    public final class Retrieve extends AsyncTask<String, Void, String> {
        public Retrieve() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            final ReportExtractCaseCashDetailActivity reportExtractCaseCashDetailActivity = ReportExtractCaseCashDetailActivity.this;
            reportExtractCaseCashDetailActivity.dialog = ProgressDialog.show(reportExtractCaseCashDetailActivity, "", reportExtractCaseCashDetailActivity.getString(R.string.str_please_wait), true);
        }

        @Override // android.os.AsyncTask
        protected String doInBackground(final String... params) {
            Intrinsics.checkNotNullParameter(params, "params");
            final ReportExtractCaseCashDetailActivity reportExtractCaseCashDetailActivity = ReportExtractCaseCashDetailActivity.this;
            reportExtractCaseCashDetailActivity.caseCash = reportExtractCaseCashDetailActivity.getExtractCaseCash();
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(final String str) {
            super.onPostExecute((Retrieve) str);
            if (null != ReportExtractCaseCashDetailActivity.this.dialog) {
                final ProgressDialog progressDialog = dialog;
                Intrinsics.checkNotNull(progressDialog);
                if (progressDialog.isShowing()) {
                    final ProgressDialog progressDialog2 = dialog;
                    Intrinsics.checkNotNull(progressDialog2);
                    progressDialog2.dismiss();
                }
            }
            if (null != ReportExtractCaseCashDetailActivity.this.caseCash) {
                setExtractDetail();
            }
        }
    }

    
    public void setExtractDetail() {
        final AppCompatTextView appCompatTextView = tvDate;
        Intrinsics.checkNotNull(appCompatTextView);
        final CASECASH casecash = caseCash;
        Intrinsics.checkNotNull(casecash);
        appCompatTextView.setText(casecash.getDATE_());
        final AppCompatTextView appCompatTextView2 = tvFicheNo;
        Intrinsics.checkNotNull(appCompatTextView2);
        final CASECASH casecash2 = caseCash;
        Intrinsics.checkNotNull(casecash2);
        appCompatTextView2.setText(casecash2.getFICHENO());
        final AppCompatTextView appCompatTextView3 = tvCustomer;
        Intrinsics.checkNotNull(appCompatTextView3);
        final CASECASH casecash3 = caseCash;
        Intrinsics.checkNotNull(casecash3);
        appCompatTextView3.setText(casecash3.getCLCODE());
        final AppCompatTextView appCompatTextView4 = tvSpecode;
        Intrinsics.checkNotNull(appCompatTextView4);
        final CASECASH casecash4 = caseCash;
        Intrinsics.checkNotNull(casecash4);
        appCompatTextView4.setText(casecash4.getSPECODE());
        final AppCompatTextView appCompatTextView5 = tvDesc;
        Intrinsics.checkNotNull(appCompatTextView5);
        final CASECASH casecash5 = caseCash;
        Intrinsics.checkNotNull(casecash5);
        appCompatTextView5.setText(casecash5.getDESC());
        final AppCompatTextView appCompatTextView6 = tvDoCode;
        Intrinsics.checkNotNull(appCompatTextView6);
        final CASECASH casecash6 = caseCash;
        Intrinsics.checkNotNull(casecash6);
        appCompatTextView6.setText(casecash6.getDOCODE());
        final AppCompatTextView appCompatTextView7 = tvAmount;
        Intrinsics.checkNotNull(appCompatTextView7);
        final CASECASH casecash7 = caseCash;
        Intrinsics.checkNotNull(casecash7);
        appCompatTextView7.setText(StringUtils.fFormat(casecash7.getAMOUNT()));
    }

    private Unit getExtractCaseCashDetail() {
        final ProgressDialog progressDialog = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(this.getString(R.string.str_please_wait));
        final ProgressDialog progressDialog2 = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        this.setSelectResult(new SelectResult());
        this.setSelectResult(this.getReportWcfQueriesViewModel().getExtractCaseCashDetail(ficheRef, this.getClRef()));
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.resultXML = "";
        }
        final SelectResult selectResult2 = this.getSelectResult();
        if (null != selectResult2) {
            selectResult2.status = "";
        }
        final SelectResult selectResult3 = this.getSelectResult();
        if (null != selectResult3) {
            selectResult3.errorString = "";
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(this.getSelectResult()).execute();
        return Unit.INSTANCE;
    }

    
    public CASECASH getExtractCaseCash() {
        final SelectResult extractCaseCashDetail = this.getReportWcfQueriesViewModel().getExtractCaseCashDetail(ficheRef, this.getClRef());
        final HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics.Param.METHOD, "getExtractCaseCash");
        final String firmano = USER.firmano;
        Intrinsics.checkNotNullExpressionValue(firmano, "firmano");
        hashMap.put("firmano", firmano);
        hashMap.put("userid", String.valueOf(USER.userid));
        final String sql = extractCaseCashDetail.sql;
        Intrinsics.checkNotNullExpressionValue(sql, "sql");
        hashMap.put("SQL", sql);
        return null;
    }

    /* compiled from: ReportExtractCaseCashDetailActivity.kt */
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
        private List<ExtractDetail> detailList;

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
            return detailList;
        }

        public void setDetailList(final List<ExtractDetail> list) {
            detailList = list;
        }

        public String getCLCODE() {
            return CLCODE;
        }

        public void setCLCODE(final String str) {
            CLCODE = str;
        }

        /* compiled from: ReportExtractCaseCashDetailActivity.kt */
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    /* compiled from: ReportExtractCaseCashDetailActivity.kt */
    public static final class ExtractDetail {
        private String VAR1;
        private float VAR2;
        private String VAR3;
        private String VAR4 = "";

        public String getVAR1() {
            return VAR1;
        }

        public void setVAR1(final String str) {
            VAR1 = str;
        }

        public float getVAR2() {
            return VAR2;
        }

        public void setVAR2(final float f2) {
            VAR2 = f2;
        }

        public String getVAR3() {
            return VAR3;
        }

        public void setVAR3(final String str) {
            VAR3 = str;
        }

        public String getVAR4() {
            return VAR4;
        }

        public void setVAR4(final String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            VAR4 = str;
        }
    }

    /* compiled from: ReportExtractCaseCashDetailActivity.kt */
    public static final class CASECASH {
        private double AMOUNT;
        private String CLCODE;
        private String DATE_;
        private String DESC;
        private String DOCODE;
        private String FICHENO;
        private String SPECODE;

        public String getDATE_() {
            return DATE_;
        }

        public void setDATE_(final String str) {
            DATE_ = str;
        }

        public String getFICHENO() {
            return FICHENO;
        }

        public void setFICHENO(final String str) {
            FICHENO = str;
        }

        public String getCLCODE() {
            return CLCODE;
        }

        public void setCLCODE(final String str) {
            CLCODE = str;
        }

        public String getSPECODE() {
            return SPECODE;
        }

        public void setSPECODE(final String str) {
            SPECODE = str;
        }

        public String getDESC() {
            return DESC;
        }

        public void setDESC(final String str) {
            DESC = str;
        }

        public String getDOCODE() {
            return DOCODE;
        }

        public void setDOCODE(final String str) {
            DOCODE = str;
        }

        public double getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(final double d2) {
            AMOUNT = d2;
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
