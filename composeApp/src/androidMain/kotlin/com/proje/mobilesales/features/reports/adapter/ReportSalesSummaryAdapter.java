package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportSalesSummaryAdapter extends BaseAdapter {
    private final Activity activity;
    private int[] arrVisibility;
    private final int[] ficheType;
    private final List<REPORTLINE> list;
    private REPORTLINE reportline;

    public Object getItem(final int i2) {
        return null;
    }

    public long getItemId(final int i2) {
        return 0L;
    }
    public ReportSalesSummaryAdapter(final Activity activity, final List<? extends REPORTLINE> list) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        this.activity = activity;
        this.list = (List<REPORTLINE>) list;
        ficheType = new int[]{R.string.str_order, R.string.str_invoice, R.string.str_return_invoice, R.string.str_cash_collection, R.string.str_credit_card_slip, R.string.str_safe_deposit, R.string.str_check_collection, R.string.str_payroll_note_collection, R.string.exp_45_undefined};
    }

    public void setColumnVisibility(final int[] arrVisibility) {
        Intrinsics.checkNotNullParameter(arrVisibility, "arrVisibility");
        this.arrVisibility = arrVisibility;
    }
    public int getCount() {
        return list.size();
    }
    public View getView(final int i2, final View view, final ViewGroup parent) {
        final View view2;
        final ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        final LayoutInflater layoutInflater = activity.getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        if (null == view) {
            viewHolder = new ViewHolder();
            view2 = layoutInflater.inflate(R.layout.report_sales_summary_row, parent, false);
            final View findViewById = view2.findViewById(R.id.tvFicheType);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvFicheType((AppCompatTextView) findViewById);
            final View findViewById2 = view2.findViewById(R.id.tvCount);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvCount((AppCompatTextView) findViewById2);
            final View findViewById3 = view2.findViewById(R.id.tvTotal);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvTotal((AppCompatTextView) findViewById3);
            final View findViewById4 = view2.findViewById(R.id.tvTotalVat);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvTotalVat((AppCompatTextView) findViewById4);
            final View findViewById5 = view2.findViewById(R.id.tvTotalDiscount);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvTotalDiscount((AppCompatTextView) findViewById5);
            final View findViewById6 = view2.findViewById(R.id.tvNetTotal);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvNetTotal((AppCompatTextView) findViewById6);
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportSalesSummaryAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        reportline = list.get(i2);
        final AppCompatTextView tvFicheType = viewHolder.getTvFicheType();
        Intrinsics.checkNotNull(tvFicheType);
        tvFicheType.setText("");
        final AppCompatTextView tvCount = viewHolder.getTvCount();
        Intrinsics.checkNotNull(tvCount);
        final REPORTLINE reportline = this.reportline;
        Intrinsics.checkNotNull(reportline);
        tvCount.setText(reportline.f1208X);
        final AppCompatTextView tvTotal = viewHolder.getTvTotal();
        Intrinsics.checkNotNull(tvTotal);
        final REPORTLINE reportline2 = this.reportline;
        Intrinsics.checkNotNull(reportline2);
        tvTotal.setText(reportline2.f1209Y);
        final AppCompatTextView tvTotalVat = viewHolder.getTvTotalVat();
        Intrinsics.checkNotNull(tvTotalVat);
        final REPORTLINE reportline3 = this.reportline;
        Intrinsics.checkNotNull(reportline3);
        tvTotalVat.setText(reportline3.f1210Z);
        final AppCompatTextView tvTotalDiscount = viewHolder.getTvTotalDiscount();
        Intrinsics.checkNotNull(tvTotalDiscount);
        final REPORTLINE reportline4 = this.reportline;
        Intrinsics.checkNotNull(reportline4);
        tvTotalDiscount.setText(reportline4.f1207W);
        final AppCompatTextView tvNetTotal = viewHolder.getTvNetTotal();
        Intrinsics.checkNotNull(tvNetTotal);
        final REPORTLINE reportline5 = this.reportline;
        Intrinsics.checkNotNull(reportline5);
        tvNetTotal.setText(reportline5.f1206T);
        int[] iArr = arrVisibility;
        int[] iArr2 = null;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        if (0 == iArr[0]) {
            final AppCompatTextView tvCount2 = viewHolder.getTvCount();
            Intrinsics.checkNotNull(tvCount2);
            tvCount2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvCount3 = viewHolder.getTvCount();
            Intrinsics.checkNotNull(tvCount3);
            tvCount3.setVisibility(View.VISIBLE);
        }
        int[] iArr3 = arrVisibility;
        if (null == iArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr3 = null;
        }
        if (0 == iArr3[1]) {
            final AppCompatTextView tvTotal2 = viewHolder.getTvTotal();
            Intrinsics.checkNotNull(tvTotal2);
            tvTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvTotal3 = viewHolder.getTvTotal();
            Intrinsics.checkNotNull(tvTotal3);
            tvTotal3.setVisibility(View.VISIBLE);
        }
        int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr4 = null;
        }
        if (0 == iArr4[2]) {
            final AppCompatTextView tvTotalVat2 = viewHolder.getTvTotalVat();
            Intrinsics.checkNotNull(tvTotalVat2);
            tvTotalVat2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvTotalVat3 = viewHolder.getTvTotalVat();
            Intrinsics.checkNotNull(tvTotalVat3);
            tvTotalVat3.setVisibility(View.VISIBLE);
        }
        int[] iArr5 = arrVisibility;
        if (null == iArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr5 = null;
        }
        if (0 == iArr5[3]) {
            final AppCompatTextView tvTotalDiscount2 = viewHolder.getTvTotalDiscount();
            Intrinsics.checkNotNull(tvTotalDiscount2);
            tvTotalDiscount2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvTotalDiscount3 = viewHolder.getTvTotalDiscount();
            Intrinsics.checkNotNull(tvTotalDiscount3);
            tvTotalDiscount3.setVisibility(View.VISIBLE);
        }
        final int[] iArr6 = arrVisibility;
        if (null == iArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr2 = iArr6;
        }
        if (0 == iArr2[4]) {
            final AppCompatTextView tvNetTotal2 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal2);
            tvNetTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvNetTotal3 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal3);
            tvNetTotal3.setVisibility(View.VISIBLE);
        }
        if (i2 < ficheType.length) {
            final AppCompatTextView tvFicheType2 = viewHolder.getTvFicheType();
            Intrinsics.checkNotNull(tvFicheType2);
            tvFicheType2.setText(activity.getString(ficheType[i2]));
        } else {
            final AppCompatTextView tvFicheType3 = viewHolder.getTvFicheType();
            Intrinsics.checkNotNull(tvFicheType3);
            final Activity activity = this.activity;
            final int[] iArr7 = ficheType;
            tvFicheType3.setText(activity.getString(iArr7[iArr7.length - 1]));
        }
        Intrinsics.checkNotNull(view2);
        return view2;
    }
    public static final class ViewHolder {
        private AppCompatTextView tvCount;
        private AppCompatTextView tvFicheType;
        private AppCompatTextView tvNetTotal;
        private AppCompatTextView tvTotal;
        private AppCompatTextView tvTotalDiscount;
        private AppCompatTextView tvTotalVat;

        public AppCompatTextView getTvFicheType() {
            return tvFicheType;
        }

        public void setTvFicheType(final AppCompatTextView appCompatTextView) {
            tvFicheType = appCompatTextView;
        }

        public AppCompatTextView getTvCount() {
            return tvCount;
        }

        public void setTvCount(final AppCompatTextView appCompatTextView) {
            tvCount = appCompatTextView;
        }

        public AppCompatTextView getTvTotal() {
            return tvTotal;
        }

        public void setTvTotal(final AppCompatTextView appCompatTextView) {
            tvTotal = appCompatTextView;
        }

        public AppCompatTextView getTvTotalVat() {
            return tvTotalVat;
        }

        public void setTvTotalVat(final AppCompatTextView appCompatTextView) {
            tvTotalVat = appCompatTextView;
        }

        public AppCompatTextView getTvTotalDiscount() {
            return tvTotalDiscount;
        }

        public void setTvTotalDiscount(final AppCompatTextView appCompatTextView) {
            tvTotalDiscount = appCompatTextView;
        }

        public AppCompatTextView getTvNetTotal() {
            return tvNetTotal;
        }

        public void setTvNetTotal(final AppCompatTextView appCompatTextView) {
            tvNetTotal = appCompatTextView;
        }
    }
}
