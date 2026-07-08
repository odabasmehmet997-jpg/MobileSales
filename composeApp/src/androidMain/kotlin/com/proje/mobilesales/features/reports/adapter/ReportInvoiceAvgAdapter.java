package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
public final class ReportInvoiceAvgAdapter extends BaseAdapter {
    private final Context context;
    private final List<REPORTLINE> reportLineList;

    public ReportInvoiceAvgAdapter(final Context context, final List<? extends REPORTLINE> reportLineList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reportLineList, "reportLineList");
        this.context = context;
        this.reportLineList = (List<REPORTLINE>) reportLineList;
    }

    public int getCount() {
        return reportLineList.size();
    }

    public Object getItem(final int i2) {
        return reportLineList.get(i2);
    }
    public long getItemId(final int i2) {
        return reportLineList.get(i2).LOGICALREF;
    }
    public View getView(int i2, View view, final ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (null == view) {
            final Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            final LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            view = layoutInflater.inflate(R.layout.report_order_avg_row, parent, false);
        }
        Intrinsics.checkNotNull(view);
        final View findViewById = view.findViewById(R.id.chkBoxReportAvg);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatCheckBox");
        final AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) findViewById;
        appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
                getViewlambda0(ReportInvoiceAvgAdapter.this, i2, compoundButton, z);
            }
        });
        final View findViewById2 = view.findViewById(R.id.txtViewDate);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = view.findViewById(R.id.txtViewFischeNumber);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = view.findViewById(R.id.txtViewAmount);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = view.findViewById(R.id.txtViewDiscount);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = view.findViewById(R.id.txtViewNetAmount);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById7 = view.findViewById(R.id.txtViewExplanation);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final REPORTLINE reportline = reportLineList.get(i2);
        appCompatCheckBox.setChecked(reportline.isCheck);
        ((AppCompatTextView) findViewById2).setText(reportline.DATE_);
        ((AppCompatTextView) findViewById3).setText(reportline.FICHENO);
        final String GROSSTOTAL = reportline.GROSSTOTAL;
        Intrinsics.checkNotNullExpressionValue(GROSSTOTAL, "GROSSTOTAL");
        ((AppCompatTextView) findViewById4).setText(StringUtils.fFormat(GROSSTOTAL));
        final String NETTOTAL = reportline.NETTOTAL;
        Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
        ((AppCompatTextView) findViewById6).setText(StringUtils.fFormat(NETTOTAL));
        final String TOTALDISCOUNTS = reportline.TOTALDISCOUNTS;
        Intrinsics.checkNotNullExpressionValue(TOTALDISCOUNTS, "TOTALDISCOUNTS");
        ((AppCompatTextView) findViewById5).setText(StringUtils.fFormat(TOTALDISCOUNTS));
        ((AppCompatTextView) findViewById7).setText(reportline.GENEXP1);
        return view;
    }

    
    public static void getViewlambda0(final ReportInvoiceAvgAdapter this0, final int i2, final CompoundButton compoundButton, final boolean z) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.reportLineList.get(i2).isCheck = z;
    }

    public boolean isCheckReportLine(final int i2) {
        return reportLineList.get(i2).isCheck;
    }

    public ArrayList<Integer> getCheckedReportLine() {
        final ArrayList<Integer> arrayList = new ArrayList<>();
        for (final REPORTLINE reportline : reportLineList) {
            if (reportline.isCheck) {
                arrayList.add(Integer.valueOf(reportline.LOGICALREF));
            }
        }
        return arrayList;
    }

    public REPORTLINE getReportLine(final int i2) {
        return reportLineList.get(i2);
    }
}
