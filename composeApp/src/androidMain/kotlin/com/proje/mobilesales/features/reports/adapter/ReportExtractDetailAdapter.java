package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractDetailActivity;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportExtractDetailAdapter extends BaseAdapter {
    private Activity activity;
    private ReportExtractDetailActivity.ExtractDetail detail;
    private List<ReportExtractDetailActivity.ExtractDetail> list;
    private int trCode;

    public Object getItem(final int i2) {
        return null;
    }

    public long getItemId(final int i2) {
        return 0L;
    }

    public Activity getActivity() {
        return activity;
    }

    public List<ReportExtractDetailActivity.ExtractDetail> getList() {
        return list;
    }

    public void setActivity(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.activity = activity;
    }

    public void setList(final List<ReportExtractDetailActivity.ExtractDetail> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public int getTrCode() {
        return trCode;
    }

    public void setTrCode(final int i2) {
        trCode = i2;
    }

    public ReportExtractDetailAdapter(final Activity activity, final List<ReportExtractDetailActivity.ExtractDetail> list, final int i2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "list");
        this.activity = activity;
        this.list = list;
        trCode = i2;
    }

    public ReportExtractDetailActivity.ExtractDetail getDetail() {
        return detail;
    }

    public void setDetail(final ReportExtractDetailActivity.ExtractDetail extractDetail) {
        detail = extractDetail;
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
            view2 = layoutInflater.inflate(R.layout.extract_detail_row, parent, false);
            final View findViewById = view2.findViewById(R.id.tvField1);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvField1((AppCompatTextView) findViewById);
            final View findViewById2 = view2.findViewById(R.id.tvField2);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvField2((AppCompatTextView) findViewById2);
            final View findViewById3 = view2.findViewById(R.id.tvField3);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvField3((AppCompatTextView) findViewById3);
            final View findViewById4 = view2.findViewById(R.id.tvField4);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvField4((AppCompatTextView) findViewById4);
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportExtractDetailAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        detail = list.get(i2);
        final AppCompatTextView tvField1 = viewHolder.getTvField1();
        Intrinsics.checkNotNull(tvField1);
        final ReportExtractDetailActivity.ExtractDetail extractDetail = detail;
        Intrinsics.checkNotNull(extractDetail);
        tvField1.setText(extractDetail.VAR1);
        final AppCompatTextView tvField2 = viewHolder.getTvField2();
        Intrinsics.checkNotNull(tvField2);
        final ReportExtractDetailActivity.ExtractDetail extractDetail2 = detail;
        Intrinsics.checkNotNull(extractDetail2);
        tvField2.setText(StringUtils.fFormat(extractDetail2.VAR2));
        final AppCompatTextView tvField3 = viewHolder.getTvField3();
        Intrinsics.checkNotNull(tvField3);
        final ReportExtractDetailActivity.ExtractDetail extractDetail3 = detail;
        Intrinsics.checkNotNull(extractDetail3);
        tvField3.setText(extractDetail3.VAR3);
        final AppCompatTextView tvField4 = viewHolder.getTvField4();
        Intrinsics.checkNotNull(tvField4);
        final ReportExtractDetailActivity.ExtractDetail extractDetail4 = detail;
        Intrinsics.checkNotNull(extractDetail4);
        tvField4.setText(extractDetail4.VAR4);
        final int i3 = trCode;
        if (61 == i3 || 62 == i3 || 63 == i3 || 64 == i3) {
            final AppCompatTextView tvField42 = viewHolder.getTvField4();
            Intrinsics.checkNotNull(tvField42);
            tvField42.setVisibility(View.GONE);
        }
        Intrinsics.checkNotNull(view2);
        return view2;
    }

    /* compiled from: ReportExtractDetailAdapter.kt */
    public static final class ViewHolder {
        private AppCompatTextView tvField1;
        private AppCompatTextView tvField2;
        private AppCompatTextView tvField3;
        private AppCompatTextView tvField4;

        public AppCompatTextView getTvField1() {
            return tvField1;
        }

        public void setTvField1(final AppCompatTextView appCompatTextView) {
            tvField1 = appCompatTextView;
        }

        public AppCompatTextView getTvField2() {
            return tvField2;
        }

        public void setTvField2(final AppCompatTextView appCompatTextView) {
            tvField2 = appCompatTextView;
        }

        public AppCompatTextView getTvField3() {
            return tvField3;
        }

        public void setTvField3(final AppCompatTextView appCompatTextView) {
            tvField3 = appCompatTextView;
        }

        public AppCompatTextView getTvField4() {
            return tvField4;
        }

        public void setTvField4(final AppCompatTextView appCompatTextView) {
            tvField4 = appCompatTextView;
        }
    }
}
