package com.proje.mobilesales.features.reports.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.tigerwcf.REPORTXML;
import com.proje.mobilesales.core.tigerwcf.SelectResult;
import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.model.AvgCalcItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class ReportWcfAvgCalTimeActivity extends BaseReportWcfActivity {
    private AppCompatImageButton imageButtonGetData;
    private LinearLayout progressListData;
    private AppCompatTextView txtViewCreditCalDate;
    private AppCompatTextView txtViewCreditCalDay;
    private AppCompatTextView txtViewCreditCalTotal;
    private AppCompatTextView txtViewDebitCalDay;
    private AppCompatTextView txtViewDebitCalTotal;
    private AppCompatTextView txtViewDebitDate;

    public void onCreate(final Bundle bundle) {
        analyticsEventType = AnalyticsEventType.REPORTS;
        super.onCreate(bundle);
        this.setContentView(R.layout.report_calavgtime);
        this.setToolbar();
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
        final View findViewById5 = this.findViewById(R.id.imgList);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageButton");
        imageButtonGetData = (AppCompatImageButton) findViewById5;
        final View findViewById6 = this.findViewById(R.id.linearProgress);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.LinearLayout");
        progressListData = (LinearLayout) findViewById6;
        final View findViewById7 = this.findViewById(R.id.txtViewDebitDate);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById7;
        txtViewDebitDate = appCompatTextView;
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setText(this.getString(R.string.str_date) + ':');
        final View findViewById8 = this.findViewById(R.id.txtViewDebitCalDay);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView2 = (AppCompatTextView) findViewById8;
        txtViewDebitCalDay = appCompatTextView2;
        Intrinsics.checkNotNull(appCompatTextView2);
        appCompatTextView2.setText(this.getString(R.string.str_day) + ": 0");
        final View findViewById9 = this.findViewById(R.id.txtViewDebitCalTotal);
        Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView3 = (AppCompatTextView) findViewById9;
        txtViewDebitCalTotal = appCompatTextView3;
        Intrinsics.checkNotNull(appCompatTextView3);
        appCompatTextView3.setText(this.getString(R.string.str_customer_total_debit) + ": 0");
        final View findViewById10 = this.findViewById(R.id.txtViewCreditCalDate);
        Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView4 = (AppCompatTextView) findViewById10;
        txtViewCreditCalDate = appCompatTextView4;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setText(this.getString(R.string.str_date) + ':');
        final View findViewById11 = this.findViewById(R.id.txtViewCreditCalDay);
        Intrinsics.checkNotNull(findViewById11, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView5 = (AppCompatTextView) findViewById11;
        txtViewCreditCalDay = appCompatTextView5;
        Intrinsics.checkNotNull(appCompatTextView5);
        appCompatTextView5.setText(this.getString(R.string.str_day) + ": 0");
        final View findViewById12 = this.findViewById(R.id.txtViewCreditCalTotal);
        Intrinsics.checkNotNull(findViewById12, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView6 = (AppCompatTextView) findViewById12;
        txtViewCreditCalTotal = appCompatTextView6;
        Intrinsics.checkNotNull(appCompatTextView6);
        appCompatTextView6.setText(this.getString(R.string.str_customer_total_credit) + ": 0");
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.GONE);
        final LinearLayout linearLayout2 = linearDate1;
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setOnClickListener(this);
        final LinearLayout linearLayout3 = linearDate2;
        Intrinsics.checkNotNull(linearLayout3);
        linearLayout3.setOnClickListener(this);
        final AppCompatImageButton appCompatImageButton = imageButtonGetData;
        Intrinsics.checkNotNull(appCompatImageButton);
        appCompatImageButton.setOnClickListener(this);
        this.setMyCalendar(Calendar.getInstance());
        this.setClientForTiger(new ServicesClientForTiger(this));
        this.initDateTxtView();
        this.getExtras();
        this.setClRef(customerRef);
        this.setType(this.CustomerToType(customerOperation.getMenuType()));
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (16908332 == item.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
     public void onPreExecute(final ProcessType processType) {
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
    }
     public void processFinish(final REPORTXML reportxml, final ProcessType processType) {
        this.fillData(reportxml);
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
        final String dateTime = DateAndTimeUtils.getDateTime(Integer.parseInt(strArr[2]), Integer.parseInt(strArr[1]), parseInt, 23, 59, 59, 0);
        Intrinsics.checkNotNullExpressionValue(dateTime, "getDateTime(...)");
        return dateTime;
    }
    protected void clearFields() {
        final AppCompatTextView appCompatTextView = txtViewDebitDate;
        if (null != appCompatTextView) {
            appCompatTextView.setText(this.getString(R.string.str_date) + ':');
        }
        final AppCompatTextView appCompatTextView2 = txtViewDebitCalDay;
        if (null != appCompatTextView2) {
            appCompatTextView2.setText(this.getString(R.string.str_day) + ": 0");
        }
        final AppCompatTextView appCompatTextView3 = txtViewDebitCalTotal;
        if (null != appCompatTextView3) {
            appCompatTextView3.setText(this.getString(R.string.str_customer_total_debit) + ": 0");
        }
        final AppCompatTextView appCompatTextView4 = txtViewCreditCalDate;
        if (null != appCompatTextView4) {
            appCompatTextView4.setText(this.getString(R.string.str_date) + ':');
        }
        final AppCompatTextView appCompatTextView5 = txtViewCreditCalDay;
        if (null != appCompatTextView5) {
            appCompatTextView5.setText(this.getString(R.string.str_day) + ": 0");
        }
        final AppCompatTextView appCompatTextView6 = txtViewCreditCalTotal;
        if (null == appCompatTextView6) {
            return;
        }
        appCompatTextView6.setText(this.getString(R.string.str_customer_total_credit) + ": 0");
    }
    private Unit getData() {
        setSelectResult(getReportWcfQueriesViewModel().getAverageTotalList(getClRef(), getDateTxtView(true), getDateTxtView(false)));
        SelectResult selectResult = getSelectResult();
        if (selectResult != null) {
            selectResult.resultXML = "";
        }
        ServicesClientForTiger clientForTiger = getClientForTiger();
        Intrinsics.checkNotNull(clientForTiger);
        setRetrieve(clientForTiger.new ReportRetrieve(getSelectResult()));
        ServicesClientForTiger.ReportRetrieve retrieve = getRetrieve();
        Intrinsics.checkNotNull(retrieve);
        retrieve.execute();
        return Unit.INSTANCE;
    }

    private void fillData(final REPORTXML reportxml) {
        if (reportxml != null && reportxml.reportLines != null && reportxml.reportLines.size() > 0) {
            if (reportxml.reportLines.size() <= 0) {
                Toast.makeText(this, "Hesaplanacak Veri Bulunamadi", Toast.LENGTH_SHORT).show();
            } else {
                final List<REPORTLINE> reportLines = reportxml.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines, "reportLines");
                calculateData(getSignedList(reportLines, false), true);
                final List<REPORTLINE> reportLines2 = reportxml.reportLines;
                Intrinsics.checkNotNullExpressionValue(reportLines2, "reportLines");
                calculateData(getSignedList(reportLines2, true), false);
            }
        }
        final LinearLayout linearLayout = progressListData;
        Intrinsics.checkNotNull(linearLayout);
        linearLayout.setVisibility(View.GONE);
    }

    private List<REPORTLINE> getSignedList(final List<? extends REPORTLINE> list, final boolean z) {
        final ArrayList<REPORTLINE> arrayList = new ArrayList<>();
        for (final REPORTLINE reportline : list) {
            if (false) {
                arrayList.add(reportline);
            }
        }
        return arrayList;
    }

    private void calculateData(List<? extends REPORTLINE> list, boolean z) {
        List listEmptyList;
        ArrayList arrayList = new ArrayList();
        for (REPORTLINE r1 : list) {
            String PROCDATE = r1.PROCDATE;
            Intrinsics.checkNotNullExpressionValue(PROCDATE, "PROCDATE");
            List<String> listSplit = new Regex(ExifInterface.GPS_DIRECTION_TRUE).split(PROCDATE, 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            String str = ((String[]) listEmptyList.toArray(new String[0]))[0];
            String TOTAL = r1.TOTAL;
            Intrinsics.checkNotNullExpressionValue(TOTAL, "TOTAL");
            arrayList.add(new AvgCalcItem(str, StringUtils.toDouble(TOTAL)));
        }
        HashMap<String, String> mapCalculateDate = DateAndTimeUtils.calculateDate(arrayList);
        if (z) {
            AppCompatTextView appCompatTextView = this.txtViewDebitDate;
            Intrinsics.checkNotNull(appCompatTextView);
            appCompatTextView.setText(getString(R.string.str_date) + ": " + mapCalculateDate.get("date"));
            AppCompatTextView appCompatTextView2 = this.txtViewDebitCalTotal;
            Intrinsics.checkNotNull(appCompatTextView2);
            appCompatTextView2.setText(getString(R.string.str_customer_total_debit) + ": " + mapCalculateDate.get("total"));
            AppCompatTextView appCompatTextView3 = this.txtViewDebitCalDay;
            Intrinsics.checkNotNull(appCompatTextView3);
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.str_day));
            sb.append(": ");
            String str2 = mapCalculateDate.get("day");
            sb.append(str2 != null ? StringUtils.roundDouble(str2) : null);
            appCompatTextView3.setText(sb.toString());
            return;
        }
        AppCompatTextView appCompatTextView4 = this.txtViewCreditCalDate;
        Intrinsics.checkNotNull(appCompatTextView4);
        appCompatTextView4.setText(getString(R.string.str_date) + ": " + mapCalculateDate.get("date"));
        AppCompatTextView appCompatTextView5 = this.txtViewCreditCalTotal;
        Intrinsics.checkNotNull(appCompatTextView5);
        appCompatTextView5.setText(getString(R.string.str_customer_total_credit) + ": " + mapCalculateDate.get("total"));
        AppCompatTextView appCompatTextView6 = this.txtViewCreditCalDay;
        Intrinsics.checkNotNull(appCompatTextView6);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.str_day));
        sb2.append(": ");
        String str3 = mapCalculateDate.get("day");
        sb2.append(str3 != null ? StringUtils.roundDouble(str3) : null);
        appCompatTextView6.setText(sb2.toString());
    }

    public void onClick(View view) {
        Integer numValueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.linearDate1) {
            showDatePicker(true);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.linearDate2) {
            showDatePicker(false);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.imgList) {
            clearFields();
            if (!validateDates()) {
                Toast.makeText(this, getString(R.string.exp_64_begin_date_bigger_than_end_date), Toast.LENGTH_LONG).show();
            } else {
                getData();
            }
        }
    }
}
