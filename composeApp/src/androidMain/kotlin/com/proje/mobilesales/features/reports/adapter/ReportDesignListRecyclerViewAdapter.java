package com.proje.mobilesales.features.reports.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.reports.model.ReportItem;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: ReportDesignListRecyclerViewAdapter.kt */

public final class ReportDesignListRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private Activity mActivity;
    private final LayoutInflater mLayoutInflater;
    private final List<ReportItem> reportDesigns;

    public ReportDesignListRecyclerViewAdapter(final Context context, final List<ReportItem> reportDesigns) {
        Intrinsics.checkNotNullParameter(reportDesigns, "reportDesigns");
        final LayoutInflater from = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        mLayoutInflater = from;
        new ArrayList();
        this.reportDesigns = reportDesigns;
    }

    public void setmActivity(final Activity activity) {
        mActivity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final View inflate = mLayoutInflater.inflate(R.layout.report_design_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.setData(reportDesigns.get(i2));
        holder.getMCardView().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.features.reports.adapter.ReportDesignListRecyclerViewAdapterExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                onBindViewHolderlambda0(ReportDesignListRecyclerViewAdapter.this, i2, view);
            }
        });
    }

    
    public static void onBindViewHolderlambda0(final ReportDesignListRecyclerViewAdapter this0, final int i2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        final Intent intent = new Intent();
        final ReportItem reportItem = this0.reportDesigns.get(i2);
        Intrinsics.checkNotNull(reportItem, "null cannot be cast to non-null type android.os.Parcelable");
        intent.putExtra("999", reportItem);
        final Activity activity = this0.mActivity;
        Intrinsics.checkNotNull(activity);
        activity.setResult(-1, intent);
        final Activity activity2 = this0.mActivity;
        Intrinsics.checkNotNull(activity2);
        activity2.finish();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return reportDesigns.size();
    }

    /* compiled from: ReportDesignListRecyclerViewAdapter.kt */
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final CardView mCardView;
        private final TextView mTxtReportDesign;

        
        public ItemViewHolder(final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            mCardView = (CardView) itemView;
            final View findViewById = itemView.findViewById(R.id.txt_report_design);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            mTxtReportDesign = (TextView) findViewById;
        }

        public CardView getMCardView() {
            return mCardView;
        }

        public void setData(final ReportItem report) {
            Intrinsics.checkNotNullParameter(report, "report");
            mTxtReportDesign.setText(report.description);
        }
    }
}
