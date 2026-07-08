package com.proje.mobilesales.features.customer.view.general;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
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
import com.proje.mobilesales.features.customer.model.CustomerProperty;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class CustomerGeneralRecyclerViewAdapter extends RecyclerView.Adapter<CustomerGeneralRecyclerViewAdapter.ItemViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final String STATE_EDIT_MODE = "state:editMode";
    private String loadingText;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    private Context mContext;
    private final ArrayList<CustomerProperty> mCustomerProperties = new ArrayList<>();
    private boolean mEditMode;
    private LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;

    public boolean getMEditMode() {
        return this.mEditMode;
    }

    public void setMEditMode(boolean z) {
        this.mEditMode = z;
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
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        Context context3 = this.mContext;
        Intrinsics.checkNotNull(context3);
        this.loadingText = context3.getString(R.string.loading_text);
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context4 = this.mContext;
        Intrinsics.checkNotNull(context4);
        TypedArray obtainStyledAttributes = context4.obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
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
        View inflate = layoutInflater.inflate(R.layout.item_customer_general, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final CustomerProperty customerProperty = getCustomerProperty(i2);
        clear(holder);
        holder.getMItemContainerPropKey().setText(customerProperty.getKey());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                boolean onBindViewHolderlambda0;
                onBindViewHolderlambda0 = CustomerGeneralRecyclerViewAdapter.onBindViewHolderlambda0(CustomerGeneralRecyclerViewAdapter.this, customerProperty, view);
                return onBindViewHolderlambda0;
            }
        });
        holder.getMItemContainerPropValue().setText(customerProperty.getValue());
    }
    public static boolean onBindViewHolderlambda0(CustomerGeneralRecyclerViewAdapter this0, CustomerProperty customerProperty, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(customerProperty, "customerProperty");
        AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(customerProperty.getKey()).setMessage(customerProperty.getValue()).create().show();
        return true;
    }

    public int getItemCount() {
        return this.mCustomerProperties.size();
    }

    private void clear(ItemViewHolder itemViewHolder) {
        itemViewHolder.getMItemContainerPropKey().setText(this.loadingText);
        itemViewHolder.getMItemContainerPropValue().setText(this.loadingText);
    }

    private CustomerProperty getCustomerProperty(int i2) {
        CustomerProperty customerProperty = this.mCustomerProperties.get(i2);
        Intrinsics.checkNotNullExpressionValue(customerProperty, "get(...)");
        return customerProperty;
    }

    public void setCustomerProperties(ArrayList<CustomerProperty> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.mCustomerProperties.clear();
        this.mCustomerProperties.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("state:editMode", this.mEditMode);
        return bundle;
    }

    public void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mEditMode = bundle.getBoolean("state:editMode", false);
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mItemContainer;
        private final TextView mItemContainerPropKey;
        private final TextView mItemContainerPropValue;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ln_customerGeneralProp);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.mItemContainer = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.txt_customerGeneralPropKey);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.mItemContainerPropKey = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.txt_customerGeneralPropValue);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.mItemContainerPropValue = (TextView) findViewById3;
        }

        public TextView getMItemContainerPropKey() {
            return this.mItemContainerPropKey;
        }

        public TextView getMItemContainerPropValue() {
            return this.mItemContainerPropValue;
        }
    }

    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
