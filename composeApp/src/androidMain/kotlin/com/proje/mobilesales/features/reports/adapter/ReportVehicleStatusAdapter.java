package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportVehicleStatusAdapter extends BaseAdapter {
    private final Activity activity;
    private final List<REPORTLINE> list;
    private REPORTLINE reportline;
    private ProcessType type;
    public Object getItem(final int i2) {
        return null;
    }
    public long getItemId(final int i2) {
        return 0L;
    }
    public ReportVehicleStatusAdapter(final Activity activity, final List<? extends REPORTLINE> list) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        this.activity = activity;
        this.list = (List<REPORTLINE>) list;
    }
    public ProcessType getType() {
        return type;
    }
    public void setType(final ProcessType processType) {
        type = processType;
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
            view2 = layoutInflater.inflate(R.layout.report_vehicle_status_row, parent, false);
            final View findViewById = view2.findViewById(R.id.tvCode);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvCode((AppCompatTextView) findViewById);
            final View findViewById2 = view2.findViewById(R.id.tvDefinition_);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvDefinition_((AppCompatTextView) findViewById2);
            final View findViewById3 = view2.findViewById(R.id.tvStock);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvStock((AppCompatTextView) findViewById3);
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportVehicleStatusAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        reportline = list.get(i2);
        final AppCompatTextView tvCode = viewHolder.getTvCode();
        Intrinsics.checkNotNull(tvCode);
        final REPORTLINE reportline = this.reportline;
        Intrinsics.checkNotNull(reportline);
        tvCode.setText(reportline.f1208X);
        final AppCompatTextView tvDefinition_ = viewHolder.getTvDefinition_();
        Intrinsics.checkNotNull(tvDefinition_);
        final REPORTLINE reportline2 = this.reportline;
        Intrinsics.checkNotNull(reportline2);
        tvDefinition_.setText(reportline2.f1209Y);
        final AppCompatTextView tvStock = viewHolder.getTvStock();
        Intrinsics.checkNotNull(tvStock);
        final REPORTLINE reportline3 = this.reportline;
        Intrinsics.checkNotNull(reportline3);
        final String Z = reportline3.f1210Z;
        Intrinsics.checkNotNullExpressionValue(Z, "Z");
        tvStock.setText(StringUtils.fFormat(Z));
        Intrinsics.checkNotNull(view2);
        return view2;
    }
    public static final class ViewHolder {
        private AppCompatTextView tvCode;
        private AppCompatTextView tvDefinition_;
        private AppCompatTextView tvStock;

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

        public AppCompatTextView getTvStock() {
            return tvStock;
        }

        public void setTvStock(final AppCompatTextView appCompatTextView) {
            tvStock = appCompatTextView;
        }
    }
}
