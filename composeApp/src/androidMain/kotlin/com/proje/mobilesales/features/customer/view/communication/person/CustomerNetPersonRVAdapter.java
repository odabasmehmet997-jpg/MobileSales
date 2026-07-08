package com.proje.mobilesales.features.customer.view.communication.person;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class CustomerNetPersonRVAdapter extends RecyclerView.Adapter<CustomerNetPersonRVAdapter.ItemViewHolder> {
    private final boolean isAttached;
    private String loadingText;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    private Context mContext;
    private List<ClCardIncharge> mIncharges = new ArrayList();
    private LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;

    public CustomerNetPersonRVAdapter() {
        Context context = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context, (BaseInjectableActivity) activity);
        this.isAttached = this.mContext != null;
    }

    public List<ClCardIncharge> getMIncharges() {
        return this.mIncharges;
    }

    public void setMIncharges(List<ClCardIncharge> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mIncharges = list;
    }

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        this.loadingText = context2.getString(R.string.loading_text);
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context3 = this.mContext;
        Intrinsics.checkNotNull(context3);
        TypedArray obtainStyledAttributes = context3.obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        this.mTertiaryTextColorResId = obtainStyledAttributes.getInt(0, 0);
        this.mSecondaryTextColorResId = obtainStyledAttributes.getInt(1, 0);
        this.mCardBackgroundColorResId = obtainStyledAttributes.getInt(2, 0);
        this.mCardHighlightColorResId = obtainStyledAttributes.getInt(3, 0);
        obtainStyledAttributes.recycle();
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_customer_inchargel, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public int getItemCount() {
        return this.mIncharges.size();
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ClCardIncharge clCardIncharge = this.mIncharges.get(i2);
        clear(holder);
        holder.getMInchargeName().setText(clCardIncharge.getInCharge());
        holder.getMInChargeDefinition().setText(clCardIncharge.getDefinition());
        holder.getMInChargeEmail().setText(clCardIncharge.getEMail());
        holder.getMInChargeTel().setText(clCardIncharge.getTelNumber());
    }

    public void initDisplayOptions(Context context) {
        if (this.isAttached) {
            notifyDataSetChanged();
        }
    }

    private void clear(ItemViewHolder itemViewHolder) {
        itemViewHolder.getMInchargeName().setText(this.loadingText);
        itemViewHolder.getMInChargeDefinition().setText(this.loadingText);
        itemViewHolder.getMInChargeEmail().setText(this.loadingText);
        itemViewHolder.getMInChargeTel().setText(this.loadingText);
    }

    public boolean isAttached() {
        return this.isAttached;
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mInChargeDefinition;
        private final TextView mInChargeEmail;
        private final TextView mInChargeTel;
        private final TextView mInchargeName;
        private final LinearLayout mItemContainer;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ln_customer_incharge);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.mItemContainer = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tv_customer_incharge_name);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.mInchargeName = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tv_customer_incharge_definition);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.mInChargeDefinition = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.tv_customer_incharge_tel);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.mInChargeTel = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.tv_customer_incharge_email);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.mInChargeEmail = (TextView) findViewById5;
        }

        public TextView getMInchargeName() {
            return this.mInchargeName;
        }

        public TextView getMInChargeDefinition() {
            return this.mInChargeDefinition;
        }

        public TextView getMInChargeTel() {
            return this.mInChargeTel;
        }

        public TextView getMInChargeEmail() {
            return this.mInChargeEmail;
        }
    }
}
