package com.proje.mobilesales.features.userreport.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.core.reportparser.ReportColumn;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import com.proje.mobilesales.features.reports.util.ReportUtil;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class UserReportsGridRecyclerAdapter extends RecyclerView.Adapter<UserReportsGridRecyclerAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<GenericData> mDatas = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private Report mReport;
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mLayoutInflater = createLayoutInflater(context);
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_reports_grid_row, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getLnGridRow().setHasTransientState(true);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        Report report = this.mReport;
        Intrinsics.checkNotNull(report);
        int size = report.getReportLayout().getReportColumns().size();
        for (int i3 = 0; i3 < size; i3++) {
            Report report2 = this.mReport;
            Intrinsics.checkNotNull(report2);
            if (report2.getReportLayout().getReportColumns().get(i3).isVisible()) {
                TextView textView = new TextView(this.mContext);
                setBodyTextViewAttr(i2, i3, textView);
                linearLayout.addView(textView);
            }
        }
        holder.getLnGridRow().addView(linearLayout);
        linearLayout.setOnLongClickListener(getLongClickListener(i2));
        addDivider(holder.getLnGridRow());
    }
    public int getItemCount() {
        return this.mDatas.size();
    }
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lnGridRow;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.userReportsGridRow);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.lnGridRow = (LinearLayout) findViewById;
        }

        public LinearLayout getLnGridRow() {
            return this.lnGridRow;
        }
    }
    public void setData(ArrayList<GenericData> arrayList, Report report) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.mDatas = arrayList;
        this.mReport = report;
        notifyDataSetChanged();
    }
    private void setBodyTextViewAttr(int i2, int i3, TextView textView) {
        Report report = this.mReport;
        Intrinsics.checkNotNull(report);
        ReportColumn reportColumn = report.getReportLayout().getReportColumns().get(i3);
        Intrinsics.checkNotNull(reportColumn);
        setTextViewAttr(reportColumn, textView);
        List<GenericDataPrimitive> genericDataPrimitives = this.mDatas.get(i2).getGenericDataPrimitives();
        String enConvertedKey = reportColumn.getTranslation() != null ? reportColumn.getTranslation().getEnConvertedKey() : reportColumn.getFieldName();
        for (GenericDataPrimitive genericDataPrimitive : genericDataPrimitives) {
            if (StringsKt.equals(genericDataPrimitive.getName(), enConvertedKey, true)) {
                textView.setText(ReportUtil.formatReportColumnValue(reportColumn, genericDataPrimitive.getValue().toString()));
                textView.setGravity(ReportUtil.getGridReportColumnAlignment(reportColumn));
                return;
            }
        }
    }
    private void setTextViewAttr(ReportColumn reportColumn, TextView textView) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(reportColumn.getCalculatedWidth(), -2);
        layoutParams.setMargins(10, 4, 10, 4);
        textView.setLayoutParams(layoutParams);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        textView.setTextColor(context.getResources().getColor(R.color.black));
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setMaxLines(1);
        textView.setPadding(6, 4, 6, 4);
    }
    private View.OnLongClickListener getLongClickListener(final int i2) {
        return new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                boolean longClickListenerlambda0;
                longClickListenerlambda0 = UserReportsGridRecyclerAdapter.getLongClickListenerlambda0(UserReportsGridRecyclerAdapter.this, i2, view);
                return longClickListenerlambda0;
            }
        };
    }
    public static boolean getLongClickListenerlambda0(UserReportsGridRecyclerAdapter this0, int i2, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Context context = this0.mContext;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.features.userreport.view.activity.UserReportsGridActivity");
        ((UserReportsGridActivity) context).getReportDetail(i2);
        return false;
    }
    private void addDivider(LinearLayout linearLayout) {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        View view = new View(this.mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (1 * f2)));
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        view.setBackgroundColor(ContextCompat.getColor(context2, R.color.line_color));
        linearLayout.addView(view);
    }
}
