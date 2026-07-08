package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportSalesOrdInvAdapter extends BaseAdapter {
    private final Activity activity;
    private int[] arrVisibility;
    private final List<REPORTLINE> list;
    private REPORTLINE reportline;

    public Object getItem(final int i2) {
        return null;
    }

    public long getItemId(final int i2) {
        return 0L;
    }

    public ReportSalesOrdInvAdapter(final Activity activity, final List<? extends REPORTLINE> list) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        this.activity = activity;
        this.list = (List<REPORTLINE>) list;
    }

    public void setColumnVisibility(final int[] arrVisibility) {
        Intrinsics.checkNotNullParameter(arrVisibility, "arrVisibility");
        this.arrVisibility = arrVisibility;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return list.size();
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams", "DefaultLocale"})
    public View getView(final int i2, final View view, final ViewGroup parent) {
        final View view2;
        final ViewHolder viewHolder;
        Intrinsics.checkNotNullParameter(parent, "parent");
        final LayoutInflater layoutInflater = activity.getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        if (null == view) {
            viewHolder = new ViewHolder();
            view2 = layoutInflater.inflate(R.layout.report_sales_ord_inv_row, parent, false);
            viewHolder.setTvLineType(view2.findViewById(R.id.tvLineType));
            viewHolder.setTvCode(view2.findViewById(R.id.tvCode));
            viewHolder.setTvDefinition_(view2.findViewById(R.id.tvDefinition_));
            viewHolder.setTvQuantity(view2.findViewById(R.id.tvQuantity));
            viewHolder.setTvGrossTotal(view2.findViewById(R.id.tvGrossTotal));
            viewHolder.setTvNetTotal(view2.findViewById(R.id.tvNetTotal));
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportSalesOrdInvAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        reportline = list.get(i2);
        final AppCompatTextView tvLineType = viewHolder.getTvLineType();
        Intrinsics.checkNotNull(tvLineType);
        final Activity activity = this.activity;
        final LineType.Companion companion = LineType.Companion;
        final REPORTLINE reportline = this.reportline;
        Intrinsics.checkNotNull(reportline);
        tvLineType.setText(activity.getString(companion.fromLineType(StringUtils.convertStringToInt(reportline.f1203A)).getResId()));
        final AppCompatTextView tvCode = viewHolder.getTvCode();
        Intrinsics.checkNotNull(tvCode);
        final REPORTLINE reportline2 = this.reportline;
        Intrinsics.checkNotNull(reportline2);
        tvCode.setText(reportline2.f1208X);
        final AppCompatTextView tvDefinition_ = viewHolder.getTvDefinition_();
        Intrinsics.checkNotNull(tvDefinition_);
        final REPORTLINE reportline3 = this.reportline;
        Intrinsics.checkNotNull(reportline3);
        tvDefinition_.setText(reportline3.f1209Y);
        final AppCompatTextView tvQuantity = viewHolder.getTvQuantity();
        Intrinsics.checkNotNull(tvQuantity);
        final REPORTLINE reportline4 = this.reportline;
        Intrinsics.checkNotNull(reportline4);
        tvQuantity.setText(reportline4.f1210Z);
        final AppCompatTextView tvGrossTotal = viewHolder.getTvGrossTotal();
        Intrinsics.checkNotNull(tvGrossTotal);
        final REPORTLINE reportline5 = this.reportline;
        Intrinsics.checkNotNull(reportline5);
        tvGrossTotal.setText(reportline5.f1207W);
        final AppCompatTextView tvNetTotal = viewHolder.getTvNetTotal();
        Intrinsics.checkNotNull(tvNetTotal);
        final REPORTLINE reportline6 = this.reportline;
        Intrinsics.checkNotNull(reportline6);
        tvNetTotal.setText(reportline6.f1206T);
        int[] iArr = arrVisibility;
        int[] iArr2 = null;
        if (null == iArr) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr = null;
        }
        if (0 == iArr[0]) {
            final AppCompatTextView tvLineType2 = viewHolder.getTvLineType();
            Intrinsics.checkNotNull(tvLineType2);
            tvLineType2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvLineType3 = viewHolder.getTvLineType();
            Intrinsics.checkNotNull(tvLineType3);
            tvLineType3.setVisibility(View.VISIBLE);
        }
        int[] iArr3 = arrVisibility;
        if (null == iArr3) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr3 = null;
        }
        if (0 == iArr3[1]) {
            final AppCompatTextView tvCode2 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode2);
            tvCode2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvCode3 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode3);
            tvCode3.setVisibility(View.VISIBLE);
        }
        int[] iArr4 = arrVisibility;
        if (null == iArr4) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr4 = null;
        }
        if (0 == iArr4[2]) {
            final AppCompatTextView tvDefinition_2 = viewHolder.getTvDefinition_();
            Intrinsics.checkNotNull(tvDefinition_2);
            tvDefinition_2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvDefinition_3 = viewHolder.getTvDefinition_();
            Intrinsics.checkNotNull(tvDefinition_3);
            tvDefinition_3.setVisibility(View.VISIBLE);
        }
        int[] iArr5 = arrVisibility;
        if (null == iArr5) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr5 = null;
        }
        if (0 == iArr5[3]) {
            final AppCompatTextView tvQuantity2 = viewHolder.getTvQuantity();
            Intrinsics.checkNotNull(tvQuantity2);
            tvQuantity2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvQuantity3 = viewHolder.getTvQuantity();
            Intrinsics.checkNotNull(tvQuantity3);
            tvQuantity3.setVisibility(View.VISIBLE);
        }
        int[] iArr6 = arrVisibility;
        if (null == iArr6) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
            iArr6 = null;
        }
        if (0 == iArr6[4]) {
            final AppCompatTextView tvGrossTotal2 = viewHolder.getTvGrossTotal();
            Intrinsics.checkNotNull(tvGrossTotal2);
            tvGrossTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvGrossTotal3 = viewHolder.getTvGrossTotal();
            Intrinsics.checkNotNull(tvGrossTotal3);
            tvGrossTotal3.setVisibility(View.VISIBLE);
        }
        final int[] iArr7 = arrVisibility;
        if (null == iArr7) {
            Intrinsics.throwUninitializedPropertyAccessException("arrVisibility");
        } else {
            iArr2 = iArr7;
        }
        if (0 == iArr2[5]) {
            final AppCompatTextView tvNetTotal2 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal2);
            tvNetTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvNetTotal3 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal3);
            tvNetTotal3.setVisibility(View.VISIBLE);
        }
        Intrinsics.checkNotNull(view2);
        return view2;
    }

    /* compiled from: ReportSalesOrdInvAdapter.kt */
    public static final class ViewHolder {
        private AppCompatTextView tvCode;
        private AppCompatTextView tvDefinition_;
        private AppCompatTextView tvGrossTotal;
        private AppCompatTextView tvLineType;
        private AppCompatTextView tvNetTotal;
        private AppCompatTextView tvQuantity;

        public AppCompatTextView getTvLineType() {
            return tvLineType;
        }

        public void setTvLineType(final AppCompatTextView appCompatTextView) {
            tvLineType = appCompatTextView;
        }

        public AppCompatTextView getTvCode() {
            return tvCode;
        }

        public void setTvCode(final AppCompatTextView appCompatTextView) {
            tvCode = appCompatTextView;
        }

        public AppCompatTextView getTvDefinition_() {
            return tvDefinition_;
        }

        public void setTvDefinition_(final AppCompatTextView appCompatTextView) {
            tvDefinition_ = appCompatTextView;
        }

        public AppCompatTextView getTvQuantity() {
            return tvQuantity;
        }

        public void setTvQuantity(final AppCompatTextView appCompatTextView) {
            tvQuantity = appCompatTextView;
        }

        public AppCompatTextView getTvGrossTotal() {
            return tvGrossTotal;
        }

        public void setTvGrossTotal(final AppCompatTextView appCompatTextView) {
            tvGrossTotal = appCompatTextView;
        }

        public AppCompatTextView getTvNetTotal() {
            return tvNetTotal;
        }

        public void setTvNetTotal(final AppCompatTextView appCompatTextView) {
            tvNetTotal = appCompatTextView;
        }
    }
}
