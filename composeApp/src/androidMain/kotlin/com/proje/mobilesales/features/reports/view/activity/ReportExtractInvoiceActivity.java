package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.widget.ViewPager;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.reports.adapter.ReportExtractPagerAdapter;
import com.proje.mobilesales.features.reports.repository.ReportWcfQueriesRepository;
import com.proje.mobilesales.features.reports.viewmodel.ReportWcfQueriesViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReportExtractInvoiceActivity extends BaseReportWcfActivity {
    private ProgressDialog dialog;
    private int ficheRef;
    private ExtractInvHeader header;
    private ReportExtractPagerAdapter mAdapter;
    private AppBarLayout mAppBar;
    private CoordinatorLayout mCoordinatorLayout;
    private boolean mInvoice;
    private ProgressDialog mProgressDialog;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar toolbar;
    @Override
    public void onPreExecute(final ProcessType processType) {
    }
    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.report_extract_detail_layout);
        final View findViewById = this.findViewById(R.id.tab_layout);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.google.android.material.tabs.TabLayout");
        mTabLayout = (TabLayout) findViewById;
        final View findViewById2 = this.findViewById(R.id.appbar);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout");
        mAppBar = (AppBarLayout) findViewById2;
        final View findViewById3 = this.findViewById(R.id.content_frame);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        mCoordinatorLayout = (CoordinatorLayout) findViewById3;
        final View findViewById4 = this.findViewById(R.id.view_pager);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type com.proje.mobilesales.core.widget.ViewPager");
        mViewPager = (ViewPager) findViewById4;
        final View findViewById5 = this.findViewById(R.id.toolbar);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.Toolbar");
        final Toolbar toolbar = (Toolbar) findViewById5;
        this.toolbar = toolbar;
        this.setSupportActionBar(toolbar);
        final Bundle extras = this.getIntent().getExtras();
        Intrinsics.checkNotNull(extras);
        if (extras.containsKey("sFicheRef")) {
            ficheRef = extras.getInt("sFicheRef");
        }
        if (extras.containsKey("invoice")) {
            mInvoice = extras.getBoolean("invoice");
        }
        this.initialize();
        if (this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.f1172GO || this.getReportWcfQueriesViewModel().getBaseErp().getErpType() == ErpType.TIGER) {
            this.setClientForTiger(new ServicesClientForTiger(this));
            mProgressDialog = new ProgressDialog(this);
            this.getExtractInvHeader();
        }
        final ActionBar supportActionBar = this.getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        this.setReportWcfQueriesRepository(new ReportWcfQueriesRepository());
        this.setReportWcfQueriesViewModel(new ReportWcfQueriesViewModel(this.getReportWcfQueriesRepository()));
    }

    @Override // com.proje.mobilesales.features.reports.view.activity.BaseReportWcfActivity, com.proje.mobilesales.core.interfaces.AsyncReportResponse
    public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        final ProgressDialog progressDialog;
        final ProgressDialog progressDialog2;
        if (processType == ProcessType.FILLREPORTINVOICE) {
            if (null != reportxml) {
                final List<REPORTLINE> reportLines = reportxml.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                if (reportLines.isEmpty()) {
                    return;
                }
                final ExtractInvHeader extractInvHeader = new ExtractInvHeader();
                extractInvHeader.setFICHENO(this.getReportLinesValues(reportxml.getResultLine().get(0).FICHENO));
                extractInvHeader.setDATE_(this.getReportLinesValues(reportxml.getResultLine().get(0).DATE_));
                extractInvHeader.setCUSTOMER(this.getReportLinesValues(reportxml.getResultLine().get(0).CUSTOMER));
                extractInvHeader.setBRANCH(this.getReportLinesValues(reportxml.getResultLine().get(0).BRANCH));
                extractInvHeader.setDEPARTMENT(this.getReportLinesValues(reportxml.getResultLine().get(0).DEPARTMENT));
                extractInvHeader.setFACTORY(this.getReportLinesValues(reportxml.getResultLine().get(0).FACTORY));
                extractInvHeader.setWAREHOUSE(this.getReportLinesValues(reportxml.getResultLine().get(0).WAREHOUSE));
                extractInvHeader.setPAYMENT(this.getReportLinesValues(reportxml.getResultLine().get(0).PAYMENT));
                extractInvHeader.setTRADINGGRP(this.getReportLinesValues(reportxml.getResultLine().get(0).TRADINGGRP));
                extractInvHeader.setDOCODE(this.getReportLinesValues(reportxml.getResultLine().get(0).DOCODE));
                extractInvHeader.setSPECODE(this.getReportLinesValues(reportxml.getResultLine().get(0).SPECODE));
                extractInvHeader.setCYPHCODE(this.getReportLinesValues(reportxml.getResultLine().get(0).CYPHCODE));
                extractInvHeader.setSALESMAN(this.getReportLinesValues(reportxml.getResultLine().get(0).SALESMAN));
                extractInvHeader.setPROJECTCODE(this.getReportLinesValues(reportxml.getResultLine().get(0).PROJECTCODE));
                extractInvHeader.setSHIPACCOUNT(this.getReportLinesValues(reportxml.getResultLine().get(0).SHIPACCOUNT));
                extractInvHeader.setSHIPADDRESS(this.getReportLinesValues(reportxml.getResultLine().get(0).SHIPADDRESS));
                extractInvHeader.setSHIPDELIVERYMETHOD(this.getReportLinesValues(reportxml.getResultLine().get(0).SHIPDELIVERYMETHOD));
                extractInvHeader.setSHIPAGENT(this.getReportLinesValues(reportxml.getResultLine().get(0).SHIPAGENT));
                extractInvHeader.setSHIPTRANSTYPE(this.getReportLinesValues(reportxml.getResultLine().get(0).SHIPTRANSTYPE));
                extractInvHeader.setDESC1(this.getReportLinesValues(reportxml.getResultLine().get(0).DESC1));
                extractInvHeader.setDESC2(this.getReportLinesValues(reportxml.getResultLine().get(0).DESC2));
                extractInvHeader.setDESC3(this.getReportLinesValues(reportxml.getResultLine().get(0).DESC3));
                extractInvHeader.setDESC4(this.getReportLinesValues(reportxml.getResultLine().get(0).DESC4));
                header = extractInvHeader;
                this.getExtractInvDetail();
                return;
            }
            if (this.isActivityDestroyed() || null == (progressDialog2 = this.mProgressDialog)) {
                return;
            }
            Intrinsics.checkNotNull(progressDialog2);
            if (progressDialog2.isShowing()) {
                final ProgressDialog progressDialog3 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog3);
                progressDialog3.dismiss();
                return;
            }
            return;
        }
        if (!this.isActivityDestroyed() && null != (progressDialog = this.mProgressDialog)) {
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                final ProgressDialog progressDialog4 = mProgressDialog;
                Intrinsics.checkNotNull(progressDialog4);
                progressDialog4.dismiss();
            }
        }
        if (null != reportxml) {
            final List<REPORTLINE> reportLines2 = reportxml.reportLines;
            Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
            if (reportLines2.isEmpty()) {
                return;
            }
            final ArrayList<ExtractInvDetail> arrayList = new ArrayList<>();
            for (final REPORTLINE reportline : reportxml.getResultLine()) {
                final ExtractInvDetail extractInvDetail = new ExtractInvDetail();
                extractInvDetail.DEFINITION_ = this.getReportLinesValues(reportline.DEFINITION_);
                extractInvDetail.AMOUNT = reportline.AMOUNT;
                final String PRICE = reportline.PRICE;
                Intrinsics.checkNotNullExpressionValue(PRICE, "PRICE");
                extractInvDetail.PRICE = Float.parseFloat(PRICE);
                extractInvDetail.PRPRICE = reportline.PRPRICE;
                final String TOTAL = reportline.TOTAL;
                Intrinsics.checkNotNullExpressionValue(TOTAL, "TOTAL");
                extractInvDetail.TOTAL = Float.parseFloat(TOTAL);
                extractInvDetail.VAT = reportline.VAT;
                extractInvDetail.VATAMOUNT = reportline.VATAMOUNT;
                extractInvDetail.setLINETYPE(reportline.LINETYPE);
                extractInvDetail.setGLOBTRANS(reportline.GLOBTRANS);
                if (2 == extractInvDetail.getLINETYPE() && 0 == extractInvDetail.getGLOBTRANS()) {
                    extractInvDetail.DEFINITION_ = this.getString(R.string.str_line_discount);
                } else if (2 == extractInvDetail.getLINETYPE() && 1 == extractInvDetail.getGLOBTRANS()) {
                    extractInvDetail.DEFINITION_ = this.getString(R.string.str_general_discount);
                }
                arrayList.add(extractInvDetail);
            }
            final ExtractInvHeader extractInvHeader2 = header;
            if (null != extractInvHeader2) {
                Intrinsics.checkNotNull(extractInvHeader2);
                extractInvHeader2.setDetailList(arrayList);
                final ViewPager viewPager = mViewPager;
                Intrinsics.checkNotNull(viewPager);
                viewPager.setPageMargin(this.getResources().getDimensionPixelOffset(R.dimen.divider));
                final ViewPager viewPager2 = mViewPager;
                Intrinsics.checkNotNull(viewPager2);
                viewPager2.setPageMarginDrawable(R.color.blackT12);
                final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
                final String string = this.getString(R.string.str_title);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                final String string2 = this.getString(R.string.str_detail);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                final ExtractInvHeader extractInvHeader3 = header;
                Intrinsics.checkNotNull(extractInvHeader3);
                mAdapter = new ReportExtractPagerAdapter(this, supportFragmentManager, new String[]{string, string2}, extractInvHeader3);
                final ViewPager viewPager3 = mViewPager;
                Intrinsics.checkNotNull(viewPager3);
                viewPager3.setAdapter(mAdapter);
                final TabLayout tabLayout = mTabLayout;
                Intrinsics.checkNotNull(tabLayout);
                tabLayout.setupWithViewPager(mViewPager);
            }
        }
    }

    private String getReportLinesValues(final String str) {
        return null == str ? "" : str;
    }

    /* compiled from: ReportExtractInvoiceActivity.kt */
    @SuppressLint("StaticFieldLeak")
    public final class Retrieve extends AsyncTask<String, Void, String> {
        public Retrieve() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            final ReportExtractInvoiceActivity reportExtractInvoiceActivity = ReportExtractInvoiceActivity.this;
            reportExtractInvoiceActivity.dialog = ProgressDialog.show(reportExtractInvoiceActivity, "", reportExtractInvoiceActivity.getString(R.string.str_please_wait), true);
        }

        @Override // android.os.AsyncTask
        protected String doInBackground(final String... params) {
            Intrinsics.checkNotNullParameter(params, "params");
            final ReportExtractInvoiceActivity reportExtractInvoiceActivity = ReportExtractInvoiceActivity.this;
            reportExtractInvoiceActivity.header = reportExtractInvoiceActivity.getExtractInvoice();
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(final String str) {
            super.onPostExecute((Retrieve) str);
            if (null != ReportExtractInvoiceActivity.this.dialog) {
                final ProgressDialog progressDialog = dialog;
                Intrinsics.checkNotNull(progressDialog);
                if (progressDialog.isShowing()) {
                    final ProgressDialog progressDialog2 = dialog;
                    Intrinsics.checkNotNull(progressDialog2);
                    progressDialog2.dismiss();
                }
            }
            if (null != ReportExtractInvoiceActivity.this.header) {
                final ViewPager viewPager = mViewPager;
                Intrinsics.checkNotNull(viewPager);
                viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.divider));
                final ViewPager viewPager2 = mViewPager;
                Intrinsics.checkNotNull(viewPager2);
                viewPager2.setPageMarginDrawable(R.color.blackT12);
                final ReportExtractInvoiceActivity reportExtractInvoiceActivity = ReportExtractInvoiceActivity.this;
                final Context applicationContext = reportExtractInvoiceActivity.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                final FragmentManager supportFragmentManager = getSupportFragmentManager();
                final String string = getString(R.string.str_title);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                final String string2 = getString(R.string.str_detail);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                final ExtractInvHeader extractInvHeader = header;
                Intrinsics.checkNotNull(extractInvHeader);
                reportExtractInvoiceActivity.mAdapter = new ReportExtractPagerAdapter(applicationContext, supportFragmentManager, new String[]{string, string2}, extractInvHeader);
                final ViewPager viewPager3 = mViewPager;
                Intrinsics.checkNotNull(viewPager3);
                viewPager3.setAdapter(mAdapter);
                final TabLayout tabLayout = mTabLayout;
                Intrinsics.checkNotNull(tabLayout);
                tabLayout.setupWithViewPager(mViewPager);
            }
        }
    }

    private Unit getExtractInvHeader() {
        final SelectResult extractOrderHeader;
        final ProgressDialog progressDialog = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(this.getString(R.string.str_please_wait));
        final ProgressDialog progressDialog2 = mProgressDialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.show();
        this.setSelectResult(new SelectResult());
        if (mInvoice) {
            extractOrderHeader = this.getReportWcfQueriesViewModel().getExtractInvoiceHeader(ficheRef);
        } else {
            extractOrderHeader = this.getReportWcfQueriesViewModel().getExtractOrderHeader(ficheRef);
        }
        this.setSelectResult(extractOrderHeader);
        final SelectResult selectResult = this.getSelectResult();
        if (null != selectResult) {
            selectResult.errorString = "";
        }
        final SelectResult selectResult2 = this.getSelectResult();
        if (null != selectResult2) {
            selectResult2.resultXML = "";
        }
        final SelectResult selectResult3 = this.getSelectResult();
        if (null != selectResult3) {
            selectResult3.status = "";
        }
        final SelectResult selectResult4 = this.getSelectResult();
        if (null != selectResult4) {
            selectResult4.type = ProcessType.FILLREPORTINVOICE;
        }
        final ServicesClientForTiger clientForTiger = this.getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(getSelectResult()).execute();
        return Unit.INSTANCE;
    }

    private Unit getExtractInvDetail() {
        SelectResult extractOrderDetail;
        setSelectResult(new SelectResult());
        if (this.mInvoice) {
            extractOrderDetail = getReportWcfQueriesViewModel().getExtractInvoiceDetail(this.ficheRef);
        } else {
            extractOrderDetail = getReportWcfQueriesViewModel().getExtractOrderDetail(this.ficheRef);
        }
        setSelectResult(extractOrderDetail);
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.errorString = "";
        }
        SelectResult selectResult2 = getSelectResult();
        if (selectResult2 != null) {
            selectResult2.resultXML = "";
        }
        SelectResult selectResult3 = getSelectResult();
        if (selectResult3 != null) {
            selectResult3.status = "";
        }
        SelectResult selectResult4 = getSelectResult();
        if (selectResult4 != null) {
            selectResult4.type = ProcessType.FILLREPORTAVGCALC;
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        clientForTiger.new ServicesClientForTiger.ReportRetrieve(this.getSelectResult()).execute();
        return Unit.INSTANCE;
    }

    
    public ExtractInvHeader getExtractInvoice() {
        final SelectResult extractInvoiceHeader = this.getReportWcfQueriesViewModel().getExtractInvoiceHeader(ficheRef);
        final SelectResult extractInvoiceDetail = this.getReportWcfQueriesViewModel().getExtractInvoiceDetail(ficheRef);
        final HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics.Param.METHOD, "getExtractInvoice");
        final String firmano = USER.firmano;
        Intrinsics.checkNotNullExpressionValue(firmano, "firmano");
        hashMap.put("firmano", firmano);
        hashMap.put("userid", String.valueOf(USER.userid));
        final String sql = extractInvoiceHeader.sql;
        Intrinsics.checkNotNullExpressionValue(sql, "sql");
        hashMap.put("SQLHEADER", sql);
        final String sql2 = extractInvoiceDetail.sql;
        Intrinsics.checkNotNullExpressionValue(sql2, "sql");
        hashMap.put("SQLDETAIL", sql2);
        return null;
    }

    /* compiled from: ReportExtractInvoiceActivity.kt */
    public static final class ExtractInvHeader implements Parcelable {
        private String BRANCH;
        private String CUSTOMER;
        private String CYPHCODE;
        private String DATE_;
        private String DEPARTMENT;
        private String DESC1;
        private String DESC2;
        private String DESC3;
        private String DESC4;
        private String DOCODE;
        private String FACTORY;
        private String FICHENO;
        private String PAYMENT;
        private String PROJECTCODE;
        private String SALESMAN;
        private String SHIPACCOUNT;
        private String SHIPADDRESS;
        private String SHIPAGENT;
        private String SHIPDELIVERYMETHOD;
        private String SHIPTRANSTYPE;
        private String SPECODE;
        private String TRADINGGRP;
        private String WAREHOUSE;
        public ArrayList<ExtractInvDetail> detailList;
        public static final Companion Companion = new Companion(null);
        private static final Parcelable.Creator<ExtractInvHeader> CREATOR = new Parcelable.Creator<ExtractInvHeader>() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceActivityExtractInvHeaderCompanionCREATOR1
            
            
            public ReportExtractInvoiceActivity.ExtractInvHeader createFromParcel(final Parcel source) {
                Intrinsics.checkNotNullParameter(source, "source");
                return new ReportExtractInvoiceActivity.ExtractInvHeader(source);
            }

            
            
            public ReportExtractInvoiceActivity.ExtractInvHeader[] newArray(final int i2) {
                return new ReportExtractInvoiceActivity.ExtractInvHeader[i2];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getFICHENO() {
            return FICHENO;
        }

        public void setFICHENO(final String str) {
            FICHENO = str;
        }

        public String getDATE_() {
            return DATE_;
        }

        public void setDATE_(final String str) {
            DATE_ = str;
        }

        public String getCUSTOMER() {
            return CUSTOMER;
        }

        public void setCUSTOMER(final String str) {
            CUSTOMER = str;
        }

        public String getBRANCH() {
            return BRANCH;
        }

        public void setBRANCH(final String str) {
            BRANCH = str;
        }

        public String getDEPARTMENT() {
            return DEPARTMENT;
        }

        public void setDEPARTMENT(final String str) {
            DEPARTMENT = str;
        }

        public String getFACTORY() {
            return FACTORY;
        }

        public void setFACTORY(final String str) {
            FACTORY = str;
        }

        public String getWAREHOUSE() {
            return WAREHOUSE;
        }

        public void setWAREHOUSE(final String str) {
            WAREHOUSE = str;
        }

        public String getPAYMENT() {
            return PAYMENT;
        }

        public void setPAYMENT(final String str) {
            PAYMENT = str;
        }

        public String getTRADINGGRP() {
            return TRADINGGRP;
        }

        public void setTRADINGGRP(final String str) {
            TRADINGGRP = str;
        }

        public String getDOCODE() {
            return DOCODE;
        }

        public void setDOCODE(final String str) {
            DOCODE = str;
        }

        public String getSPECODE() {
            return SPECODE;
        }

        public void setSPECODE(final String str) {
            SPECODE = str;
        }

        public String getCYPHCODE() {
            return CYPHCODE;
        }

        public void setCYPHCODE(final String str) {
            CYPHCODE = str;
        }

        public String getSALESMAN() {
            return SALESMAN;
        }

        public void setSALESMAN(final String str) {
            SALESMAN = str;
        }

        public String getPROJECTCODE() {
            return PROJECTCODE;
        }

        public void setPROJECTCODE(final String str) {
            PROJECTCODE = str;
        }

        public String getSHIPACCOUNT() {
            return SHIPACCOUNT;
        }

        public void setSHIPACCOUNT(final String str) {
            SHIPACCOUNT = str;
        }

        public String getSHIPADDRESS() {
            return SHIPADDRESS;
        }

        public void setSHIPADDRESS(final String str) {
            SHIPADDRESS = str;
        }

        public String getSHIPDELIVERYMETHOD() {
            return SHIPDELIVERYMETHOD;
        }

        public void setSHIPDELIVERYMETHOD(final String str) {
            SHIPDELIVERYMETHOD = str;
        }

        public String getSHIPAGENT() {
            return SHIPAGENT;
        }

        public void setSHIPAGENT(final String str) {
            SHIPAGENT = str;
        }

        public String getSHIPTRANSTYPE() {
            return SHIPTRANSTYPE;
        }

        public void setSHIPTRANSTYPE(final String str) {
            SHIPTRANSTYPE = str;
        }

        public String getDESC1() {
            return DESC1;
        }

        public void setDESC1(final String str) {
            DESC1 = str;
        }

        public String getDESC2() {
            return DESC2;
        }

        public void setDESC2(final String str) {
            DESC2 = str;
        }

        public String getDESC3() {
            return DESC3;
        }

        public void setDESC3(final String str) {
            DESC3 = str;
        }

        public String getDESC4() {
            return DESC4;
        }

        public void setDESC4(final String str) {
            DESC4 = str;
        }

        public ArrayList<ExtractInvDetail> getDetailList() {
            final ArrayList<ExtractInvDetail> arrayList = detailList;
            if (null != arrayList) {
                return arrayList;
            }
            Intrinsics.throwUninitializedPropertyAccessException("detailList");
            return null;
        }

        public void setDetailList(final ArrayList<ExtractInvDetail> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            detailList = arrayList;
        }

        public ExtractInvHeader() {
        }

        @Override // android.os.Parcelable
        public void writeToParcel(final Parcel dest, final int i2) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(FICHENO);
            dest.writeString(DATE_);
            dest.writeString(CUSTOMER);
            dest.writeString(BRANCH);
            dest.writeString(DEPARTMENT);
            dest.writeString(FACTORY);
            dest.writeString(WAREHOUSE);
            dest.writeString(PAYMENT);
            dest.writeString(TRADINGGRP);
            dest.writeString(DOCODE);
            dest.writeString(SPECODE);
            dest.writeString(CYPHCODE);
            dest.writeString(SALESMAN);
            dest.writeString(PROJECTCODE);
            dest.writeString(SHIPACCOUNT);
            dest.writeString(SHIPADDRESS);
            dest.writeString(SHIPDELIVERYMETHOD);
            dest.writeString(SHIPAGENT);
            dest.writeString(SHIPTRANSTYPE);
            dest.writeString(DESC1);
            dest.writeString(DESC2);
            dest.writeString(DESC3);
            dest.writeString(DESC4);
            dest.writeTypedList(this.getDetailList());
        }

        private ExtractInvHeader(final Parcel in) {
            Intrinsics.checkNotNullParameter(in, "in");
            FICHENO = in.readString();
            DATE_ = in.readString();
            CUSTOMER = in.readString();
            BRANCH = in.readString();
            DEPARTMENT = in.readString();
            FACTORY = in.readString();
            WAREHOUSE = in.readString();
            PAYMENT = in.readString();
            TRADINGGRP = in.readString();
            DOCODE = in.readString();
            SPECODE = in.readString();
            CYPHCODE = in.readString();
            SALESMAN = in.readString();
            PROJECTCODE = in.readString();
            SHIPACCOUNT = in.readString();
            SHIPADDRESS = in.readString();
            SHIPDELIVERYMETHOD = in.readString();
            SHIPAGENT = in.readString();
            SHIPTRANSTYPE = in.readString();
            DESC1 = in.readString();
            DESC2 = in.readString();
            DESC3 = in.readString();
            DESC4 = in.readString();
            final ArrayList<ExtractInvDetail> createTypedArrayList = in.createTypedArrayList(ExtractInvDetail.Companion.getCREATOR());
            Intrinsics.checkNotNull(createTypedArrayList, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceActivity.ExtractInvDetail>");
            this.setDetailList(createTypedArrayList);
        }

        /* compiled from: ReportExtractInvoiceActivity.kt */
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public Parcelable.Creator<ExtractInvHeader> getCREATOR() {
                return CREATOR;
            }
        }
    }

    /* compiled from: ReportExtractInvoiceActivity.kt */
    public static final class ExtractInvDetail implements Parcelable {
        public double AMOUNT;
        public String DEFINITION_;
        private int GLOBTRANS;
        private int LINETYPE;
        public double PRICE;
        public double PRPRICE;
        public double TOTAL;
        public String VAT;
        public double VATAMOUNT;
        public static final Companion Companion = new Companion(null);
        private static final Parcelable.Creator<ExtractInvDetail> CREATOR = new Parcelable.Creator<ExtractInvDetail>() { // from class: com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceActivityExtractInvDetailCompanionCREATOR1
            
            
            public ReportExtractInvoiceActivity.ExtractInvDetail createFromParcel(final Parcel source) {
                Intrinsics.checkNotNullParameter(source, "source");
                return new ReportExtractInvoiceActivity.ExtractInvDetail(source);
            }

            
            
            public ReportExtractInvoiceActivity.ExtractInvDetail[] newArray(final int i2) {
                return new ReportExtractInvoiceActivity.ExtractInvDetail[i2];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getLINETYPE() {
            return LINETYPE;
        }

        public void setLINETYPE(final int i2) {
            LINETYPE = i2;
        }

        public int getGLOBTRANS() {
            return GLOBTRANS;
        }

        public void setGLOBTRANS(final int i2) {
            GLOBTRANS = i2;
        }

        public ExtractInvDetail() {
        }

        @Override // android.os.Parcelable
        public void writeToParcel(final Parcel dest, final int i2) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(DEFINITION_);
            dest.writeDouble(AMOUNT);
            dest.writeDouble(PRICE);
            dest.writeDouble(PRPRICE);
            dest.writeDouble(TOTAL);
            dest.writeString(VAT);
            dest.writeDouble(VATAMOUNT);
            dest.writeInt(LINETYPE);
            dest.writeInt(GLOBTRANS);
        }

        private ExtractInvDetail(final Parcel in) {
            Intrinsics.checkNotNullParameter(in, "in");
            DEFINITION_ = in.readString();
            AMOUNT = in.readDouble();
            PRICE = in.readDouble();
            PRPRICE = in.readDouble();
            TOTAL = in.readDouble();
            VAT = in.readString();
            VATAMOUNT = in.readDouble();
            LINETYPE = in.readInt();
            GLOBTRANS = in.readInt();
        }

        /* compiled from: ReportExtractInvoiceActivity.kt */
        public static final class Companion {
            public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public Parcelable.Creator<ExtractInvDetail> getCREATOR() {
                return CREATOR;
            }
        }
    }
}
