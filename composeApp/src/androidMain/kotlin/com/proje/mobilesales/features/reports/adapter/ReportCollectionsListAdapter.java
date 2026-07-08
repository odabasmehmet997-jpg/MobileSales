package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportCollectionsListAdapter extends BaseAdapter {
    private final Context context;
    private final List<REPORTLINE> reportLineList;
    public ReportCollectionsListAdapter(final Context context, final List<? extends REPORTLINE> reportLineList) {
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
    @SuppressLint("SetTextI18n")
    public View getView(final int i2, View view, final ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (null == view) {
            final Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            final LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            view = layoutInflater.inflate(R.layout.report_collection_list_row, parent, false);
        }
        final View findViewById = null != view ? view.findViewById(R.id.txtViewFicheType) : null;
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = view.findViewById(R.id.txtViewDueDate);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = view.findViewById(R.id.txtViewPrecessDate);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = view.findViewById(R.id.txtViewDue);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = view.findViewById(R.id.txtViewRecordNo);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = view.findViewById(R.id.txtViewFicheNo);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById7 = view.findViewById(R.id.txtViewAmount);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById8 = view.findViewById(R.id.txtViewBalance);
        Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById8;
        final REPORTLINE reportline = reportLineList.get(i2);
        ((AppCompatTextView) findViewById).setText(reportline.f1208X);
        ((AppCompatTextView) findViewById2).setText(reportline.f1209Y);
        ((AppCompatTextView) findViewById3).setText(reportline.f1210Z);
        ((AppCompatTextView) findViewById4).setText(reportline.f1206T);
        ((AppCompatTextView) findViewById5).setText(reportline.f1204B);
        ((AppCompatTextView) findViewById6).setText(reportline.FICHENO);
        ((AppCompatTextView) findViewById7).setText(reportline.f1203A);
        if (ErpCreator.getInstance().getmBaseErp().isHideCustomerBalance()) {
            appCompatTextView.setText("-");
        } else {
            appCompatTextView.setText(reportline.f1205K);
        }
        return view;
    }
}
