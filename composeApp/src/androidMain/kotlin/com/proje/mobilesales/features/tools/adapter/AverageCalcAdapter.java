package com.proje.mobilesales.features.tools.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.RecyclerViewClickListener;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.tools.model.AverageCalcItem;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class AverageCalcAdapter extends RecyclerView.Adapter<AverageCalcAdapter.AverageHolder> {
    private final List<AverageCalcItem> averageCalcItems;
    private final RecyclerViewClickListener recyclerViewClickListener;
    public AverageCalcAdapter(final RecyclerViewClickListener recyclerViewClickListener, final List<AverageCalcItem> averageCalcItems) {
        Intrinsics.checkNotNullParameter(recyclerViewClickListener, "recyclerViewClickListener");
        Intrinsics.checkNotNullParameter(averageCalcItems, "averageCalcItems");
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.averageCalcItems = averageCalcItems;
    }
    public AverageHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_average_calc, parent, false);
        Intrinsics.checkNotNull(inflate);
        return new AverageHolder(inflate);
    }
    public void onBindViewHolder(final AverageHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final AverageCalcItem averageCalcItem = averageCalcItems.get(i2);
        holder.getTwAmountMobileSales_release().setText(StringUtils.convertDoubleToString(Double.valueOf(averageCalcItem.getAmount())));
        holder.getTwAverageDate().setText(averageCalcItem.getDate());
        holder.getLnAmount().setOnClickListener(view -> onBindViewHolderlambda0(AverageCalcAdapter.this, i2, view));
        holder.getLnDate().setOnClickListener(view -> onBindViewHolderlambda1(AverageCalcAdapter.this, i2, view));
    }
    public static void onBindViewHolderlambda0(final AverageCalcAdapter this0, final int i2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.recyclerViewClickListener.onClick(view, i2);
    }
    public static void onBindViewHolderlambda1(final AverageCalcAdapter this0, final int i2, final View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.recyclerViewClickListener.onClick(view, i2);
    }
    public int getItemCount() {
        return averageCalcItems.size();
    }
    public static final class AverageHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lnAmount;
        private final LinearLayout lnDate;
        private final AppCompatTextView twAmount;
        private final AppCompatTextView twAverageDate;

        public AverageHolder(final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            final View findViewById = itemView.findViewById(R.id.lnDate);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.LinearLayout");
            lnDate = (LinearLayout) findViewById;
            final View findViewById2 = itemView.findViewById(R.id.twAverageDate);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            twAverageDate = (AppCompatTextView) findViewById2;
            final View findViewById3 = itemView.findViewById(R.id.lnAmount);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.LinearLayout");
            lnAmount = (LinearLayout) findViewById3;
            final View findViewById4 = itemView.findViewById(R.id.twAmount);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatTextView");
            twAmount = (AppCompatTextView) findViewById4;
        }

        public LinearLayout getLnDate() {
            return lnDate;
        }

        public AppCompatTextView getTwAverageDate() {
            return twAverageDate;
        }

        public LinearLayout getLnAmount() {
            return lnAmount;
        }

        public AppCompatTextView getTwAmountMobileSales_release() {
            return twAmount;
        }
    }
}
