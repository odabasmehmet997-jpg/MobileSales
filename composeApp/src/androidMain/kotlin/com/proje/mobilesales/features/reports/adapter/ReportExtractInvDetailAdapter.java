package com.proje.mobilesales.features.reports.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.reports.view.activity.ReportExtractInvoiceActivity;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class ReportExtractInvDetailAdapter extends BaseAdapter {
    private Activity activity;
    private ReportExtractInvoiceActivity.ExtractInvDetail detail;
    private List<ReportExtractInvoiceActivity.ExtractInvDetail> list;
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

    public void setActivity(final Activity activity) {
        this.activity = activity;
    }

    public List<ReportExtractInvoiceActivity.ExtractInvDetail> getList() {
        return list;
    }

    public void setList(final List<ReportExtractInvoiceActivity.ExtractInvDetail> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.list = list;
    }

    public ReportExtractInvDetailAdapter(final Activity activity, final List<ReportExtractInvoiceActivity.ExtractInvDetail> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.activity = activity;
        this.list = list;
    }

    public ReportExtractInvoiceActivity.ExtractInvDetail getDetail() {
        return detail;
    }

    public void setDetail(final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail) {
        detail = extractInvDetail;
    }

    public int getTrCode() {
        return trCode;
    }

    public void setTrCode(final int i2) {
        trCode = i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return list.size();
    }

    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams", "DefaultLocale", "SetTextI18n"})
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
            view2 = layoutInflater.inflate(R.layout.extract_invoice_detail_row, parent, false);
            final View findViewById = view2.findViewById(R.id.tvItem);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvItem((AppCompatTextView) findViewById);
            final View findViewById2 = view2.findViewById(R.id.tvAmount);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvAmount((AppCompatTextView) findViewById2);
            final View findViewById3 = view2.findViewById(R.id.tvPrice);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvPrice((AppCompatTextView) findViewById3);
            final View findViewById4 = view2.findViewById(R.id.tvPrPrice);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvPrPrice((AppCompatTextView) findViewById4);
            final View findViewById5 = view2.findViewById(R.id.tvKdv);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvKdv((AppCompatTextView) findViewById5);
            final View findViewById6 = view2.findViewById(R.id.tvKdvAmount);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvKdvAmount((AppCompatTextView) findViewById6);
            final View findViewById7 = view2.findViewById(R.id.tvTotal);
            Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            viewHolder.setTvTotal((AppCompatTextView) findViewById7);
            view2.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type com.proje.mobilesales.features.reports.adapter.ReportExtractInvDetailAdapter.ViewHolder");
            final ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        detail = list.get(i2);
        final AppCompatTextView tvItem = viewHolder.getTvItem();
        Intrinsics.checkNotNull(tvItem);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail = detail;
        Intrinsics.checkNotNull(extractInvDetail);
        tvItem.setText(extractInvDetail.DEFINITION_);
        final AppCompatTextView tvAmount = viewHolder.getTvAmount();
        Intrinsics.checkNotNull(tvAmount);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail2 = detail;
        Intrinsics.checkNotNull(extractInvDetail2);
        tvAmount.setText(StringUtils.fFormat(extractInvDetail2.AMOUNT));
        final AppCompatTextView tvPrice = viewHolder.getTvPrice();
        Intrinsics.checkNotNull(tvPrice);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail3 = detail;
        Intrinsics.checkNotNull(extractInvDetail3);
        tvPrice.setText(StringUtils.fFormat(extractInvDetail3.PRICE));
        final AppCompatTextView tvPrPrice = viewHolder.getTvPrPrice();
        Intrinsics.checkNotNull(tvPrPrice);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail4 = detail;
        Intrinsics.checkNotNull(extractInvDetail4);
        tvPrPrice.setText(StringUtils.fFormat(extractInvDetail4.PRPRICE));
        final AppCompatTextView tvKdv = viewHolder.getTvKdv();
        Intrinsics.checkNotNull(tvKdv);
        final StringBuilder sb = new StringBuilder();
        sb.append('%');
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail5 = detail;
        Intrinsics.checkNotNull(extractInvDetail5);
        final String str = extractInvDetail5.VAT;
        Intrinsics.checkNotNull(str);
        sb.append(StringUtils.fFormat(str));
        tvKdv.setText(sb.toString());
        final AppCompatTextView tvKdvAmount = viewHolder.getTvKdvAmount();
        Intrinsics.checkNotNull(tvKdvAmount);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail6 = detail;
        Intrinsics.checkNotNull(extractInvDetail6);
        tvKdvAmount.setText(StringUtils.fFormat(extractInvDetail6.VATAMOUNT));
        final AppCompatTextView tvTotal = viewHolder.getTvTotal();
        Intrinsics.checkNotNull(tvTotal);
        final ReportExtractInvoiceActivity.ExtractInvDetail extractInvDetail7 = detail;
        Intrinsics.checkNotNull(extractInvDetail7);
        tvTotal.setText(StringUtils.fFormat(extractInvDetail7.TOTAL));
        Intrinsics.checkNotNull(view2);
        return view2;
    }

    /* compiled from: ReportExtractInvDetailAdapter.kt */
    public static final class ViewHolder {
        private AppCompatTextView tvAmount;
        private AppCompatTextView tvItem;
        private AppCompatTextView tvKdv;
        private AppCompatTextView tvKdvAmount;
        private AppCompatTextView tvPrPrice;
        private AppCompatTextView tvPrice;
        private AppCompatTextView tvTotal;

        public AppCompatTextView getTvItem() {
            return tvItem;
        }

        public void setTvItem(final AppCompatTextView appCompatTextView) {
            tvItem = appCompatTextView;
        }

        public AppCompatTextView getTvAmount() {
            return tvAmount;
        }

        public void setTvAmount(final AppCompatTextView appCompatTextView) {
            tvAmount = appCompatTextView;
        }

        public AppCompatTextView getTvPrice() {
            return tvPrice;
        }

        public void setTvPrice(final AppCompatTextView appCompatTextView) {
            tvPrice = appCompatTextView;
        }

        public AppCompatTextView getTvPrPrice() {
            return tvPrPrice;
        }

        public void setTvPrPrice(final AppCompatTextView appCompatTextView) {
            tvPrPrice = appCompatTextView;
        }

        public AppCompatTextView getTvKdv() {
            return tvKdv;
        }

        public void setTvKdv(final AppCompatTextView appCompatTextView) {
            tvKdv = appCompatTextView;
        }

        public AppCompatTextView getTvKdvAmount() {
            return tvKdvAmount;
        }

        public void setTvKdvAmount(final AppCompatTextView appCompatTextView) {
            tvKdvAmount = appCompatTextView;
        }

        public AppCompatTextView getTvTotal() {
            return tvTotal;
        }

        public void setTvTotal(final AppCompatTextView appCompatTextView) {
            tvTotal = appCompatTextView;
        }
    }
}
