package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportDebitFollowAdapter extends BaseAdapter {
    private final Context context;
    private final String localCurrency;
    private final List<REPORTLINE> reportLineList;

    public ReportDebitFollowAdapter(final Context context, final List<? extends REPORTLINE> reportLineList, final String localCurrency) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reportLineList, "reportLineList");
        Intrinsics.checkNotNullParameter(localCurrency, "localCurrency");
        this.context = context;
        this.reportLineList = (List<REPORTLINE>) reportLineList;
        this.localCurrency = localCurrency;
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

    public View getView(final int i2, View view, final ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (null == view) {
            final Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            final LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            view = layoutInflater.inflate(R.layout.report_debit_follow_row, parent, false);
        }
        final View findViewById = null != view ? view.findViewById(R.id.txtViewProcessDate) : null;
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = view.findViewById(R.id.txtViewDate);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById2;
        final View findViewById3 = view.findViewById(R.id.txtViewFischeNumber);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = view.findViewById(R.id.txtViewExplanation);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = view.findViewById(R.id.txtViewDebit);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = view.findViewById(R.id.txtViewCredit);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById7 = view.findViewById(R.id.txtViewRemaining);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView2 = view.findViewById(R.id.txtCurrency);
        final REPORTLINE reportline = reportLineList.get(i2);
        appCompatTextView.setText(reportline.DATE_);
        ((AppCompatTextView) findViewById).setText(reportline.PROCDATE);
        appCompatTextView.setText(reportline.DATE_);
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
        appCompatTextView2.setText(TextUtils.isEmpty(reportline.CURCODE) ? localCurrency : reportline.CURCODE);
        return view;
    }
}
