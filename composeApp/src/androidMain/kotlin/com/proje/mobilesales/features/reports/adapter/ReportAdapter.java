package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.tigerwcf.REPORTLINE;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportAdapter extends BaseAdapter {
    private Activity activity;
    private int[] arrVisibility;
    private boolean isCashCredit;
    private List<? extends REPORTLINE> list;
    private REPORTLINE reportLine;
    private ProcessType type;

    public Object getItem(final int i2) {
        return null;
    }
    public long getItemId(final int i2) {
        return 0L;
    }

    public Activity getActivity() {
        return activity;
    }

    public List<REPORTLINE> getList() {
        return (List<REPORTLINE>) list;
    }

    public ProcessType getType() {
        return type;
    }

    public void setActivity(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.activity = activity;
    }

    public void setList(final List<? extends REPORTLINE> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public void setType(final ProcessType processType) {
        Intrinsics.checkNotNullParameter(processType, "<set-?>");
        type = processType;
    }

    public ReportAdapter(final Activity activity, final List<? extends REPORTLINE> list, final ProcessType type) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(type, "type");
        this.activity = activity;
        this.list = list;
        this.type = type;
        arrVisibility = new int[0];
        if (type == ProcessType.FILLREPORTCASH || type == ProcessType.FILLREPORTCREDIT) {
            isCashCredit = true;
        }
    }

    public REPORTLINE getReportLine() {
        return reportLine;
    }

    public void setReportLine(final REPORTLINE reportline) {
        reportLine = reportline;
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
        if (null == view) {
            viewHolder = new ViewHolder();
            final Activity activity = ContextUtils.getmActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.app.Activity");
            final LayoutInflater layoutInflater = activity.getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            view2 = layoutInflater.inflate(R.layout.report_row, parent, false);
            viewHolder.setTvFicheNo(view2.findViewById(R.id.tvFicheNo));
            viewHolder.setTvCode(view2.findViewById(R.id.tvCode));
            viewHolder.setTvDefinition_(view2.findViewById(R.id.tvDefinition_));
            viewHolder.setTvOrderState(view2.findViewById(R.id.tvOrderState));
            viewHolder.setTvGrossTotal(view2.findViewById(R.id.tvGrossTotal));
            viewHolder.setTvNetTotal(view2.findViewById(R.id.tvNetTotal));
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        reportLine = list.get(i2);
        final AppCompatTextView tvFicheNo = viewHolder.getTvFicheNo();
        Intrinsics.checkNotNull(tvFicheNo);
        final REPORTLINE reportline = reportLine;
        Intrinsics.checkNotNull(reportline);
        tvFicheNo.setText(reportline.FICHENO);
        final AppCompatTextView tvCode = viewHolder.getTvCode();
        Intrinsics.checkNotNull(tvCode);
        final REPORTLINE reportline2 = reportLine;
        Intrinsics.checkNotNull(reportline2);
        tvCode.setText(reportline2.CODE);
        final AppCompatTextView tvDefinition_ = viewHolder.getTvDefinition_();
        Intrinsics.checkNotNull(tvDefinition_);
        final REPORTLINE reportline3 = reportLine;
        Intrinsics.checkNotNull(reportline3);
        tvDefinition_.setText(reportline3.DEFINITION_);
        final AppCompatTextView tvOrderState = viewHolder.getTvOrderState();
        Intrinsics.checkNotNull(tvOrderState);
        final REPORTLINE reportline4 = reportLine;
        Intrinsics.checkNotNull(reportline4);
        tvOrderState.setText(reportline4.f1208X);
        final AppCompatTextView tvGrossTotal = viewHolder.getTvGrossTotal();
        Intrinsics.checkNotNull(tvGrossTotal);
        final REPORTLINE reportline5 = reportLine;
        Intrinsics.checkNotNull(reportline5);
        final String GROSSTOTAL = reportline5.GROSSTOTAL;
        Intrinsics.checkNotNullExpressionValue(GROSSTOTAL, "GROSSTOTAL");
        tvGrossTotal.setText(StringUtils.fFormat(GROSSTOTAL));
        final AppCompatTextView tvNetTotal = viewHolder.getTvNetTotal();
        Intrinsics.checkNotNull(tvNetTotal);
        final REPORTLINE reportline6 = reportLine;
        Intrinsics.checkNotNull(reportline6);
        final String NETTOTAL = reportline6.NETTOTAL;
        Intrinsics.checkNotNullExpressionValue(NETTOTAL, "NETTOTAL");
        tvNetTotal.setText(StringUtils.fFormat(NETTOTAL));
        if (0 == this.arrVisibility[0]) {
            final AppCompatTextView tvFicheNo2 = viewHolder.getTvFicheNo();
            Intrinsics.checkNotNull(tvFicheNo2);
            tvFicheNo2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvFicheNo3 = viewHolder.getTvFicheNo();
            Intrinsics.checkNotNull(tvFicheNo3);
            tvFicheNo3.setVisibility(View.VISIBLE);
        }
        if (0 == this.arrVisibility[1]) {
            final AppCompatTextView tvCode2 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode2);
            tvCode2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvCode3 = viewHolder.getTvCode();
            Intrinsics.checkNotNull(tvCode3);
            tvCode3.setVisibility(View.VISIBLE);
        }
        if (0 == this.arrVisibility[2]) {
            final AppCompatTextView tvDefinition_2 = viewHolder.getTvDefinition_();
            Intrinsics.checkNotNull(tvDefinition_2);
            tvDefinition_2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvDefinition_3 = viewHolder.getTvDefinition_();
            Intrinsics.checkNotNull(tvDefinition_3);
            tvDefinition_3.setVisibility(View.VISIBLE);
        }
        if (0 == this.arrVisibility[3]) {
            final AppCompatTextView tvGrossTotal2 = viewHolder.getTvGrossTotal();
            Intrinsics.checkNotNull(tvGrossTotal2);
            tvGrossTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvGrossTotal3 = viewHolder.getTvGrossTotal();
            Intrinsics.checkNotNull(tvGrossTotal3);
            tvGrossTotal3.setVisibility(View.VISIBLE);
        }
        if (0 == this.arrVisibility[4]) {
            final AppCompatTextView tvNetTotal2 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal2);
            tvNetTotal2.setVisibility(View.GONE);
        } else {
            final AppCompatTextView tvNetTotal3 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal3);
            tvNetTotal3.setVisibility(View.VISIBLE);
        }
        if (isCashCredit) {
            final AppCompatTextView tvNetTotal4 = viewHolder.getTvNetTotal();
            Intrinsics.checkNotNull(tvNetTotal4);
            tvNetTotal4.setVisibility(View.GONE);
        }
        if (type == ProcessType.FILLREPORTORDER) {
            final AppCompatTextView tvOrderState2 = viewHolder.getTvOrderState();
            Intrinsics.checkNotNull(tvOrderState2);
            tvOrderState2.setVisibility(View.VISIBLE);
        } else {
            final AppCompatTextView tvOrderState3 = viewHolder.getTvOrderState();
            Intrinsics.checkNotNull(tvOrderState3);
            tvOrderState3.setVisibility(View.GONE);
        }
        Intrinsics.checkNotNull(view2);
        return view2;
    }

    /* compiled from: ReportAdapter.kt */
    public static final class ViewHolder {
        private AppCompatTextView tvCode;
        private AppCompatTextView tvDefinition_;
        private AppCompatTextView tvFicheNo;
        private AppCompatTextView tvGrossTotal;
        private AppCompatTextView tvNetTotal;
        private AppCompatTextView tvOrderState;

        public AppCompatTextView getTvFicheNo() {
            return tvFicheNo;
        }

        public void setTvFicheNo(final AppCompatTextView appCompatTextView) {
            tvFicheNo = appCompatTextView;
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

        public AppCompatTextView getTvOrderState() {
            return tvOrderState;
        }

        public void setTvOrderState(final AppCompatTextView appCompatTextView) {
            tvOrderState = appCompatTextView;
        }
    }
}
