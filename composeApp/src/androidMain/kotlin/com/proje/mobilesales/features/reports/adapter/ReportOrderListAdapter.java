package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.Orders;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportOrderListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Orders> reportLineList;
    public ReportOrderListAdapter(final Context context, final List<? extends Orders> reportLineList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reportLineList, "reportLineList");
        this.context = context;
        this.reportLineList = (List<Orders>) reportLineList;
    }
    public int getCount() {
        return reportLineList.size();
    }
    public Object getItem(final int i2) {
        return reportLineList.get(i2);
    }
    public long getItemId(final int i2) {
        return reportLineList.get(i2).getLogicalRef();
    }
    public View getView(final int i2, View view, final ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (null == view) {
            final Context context = this.context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            final LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            view = layoutInflater.inflate(R.layout.report_order_list_row, parent, false);
        }
        final View findViewById = null != view ? view.findViewById(R.id.txtViewType) : null;
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById2 = view.findViewById(R.id.txtViewState);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById3 = view.findViewById(R.id.txtViewStatus);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById4 = view.findViewById(R.id.txtViewDate);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById5 = view.findViewById(R.id.txtViewFicheNo);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final View findViewById6 = view.findViewById(R.id.txtViewAmount);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
        final Orders orders = reportLineList.get(i2);
        ((AppCompatTextView) findViewById4).setText(orders.getDate());
        ((AppCompatTextView) findViewById).setText(orders.getType());
        ((AppCompatTextView) findViewById2).setText(orders.getState());
        ((AppCompatTextView) findViewById3).setText(orders.getStatus());
        ((AppCompatTextView) findViewById5).setText(orders.getFicheNo());
        ((AppCompatTextView) findViewById6).setText(orders.getAmount());
        return view;
    }
}
