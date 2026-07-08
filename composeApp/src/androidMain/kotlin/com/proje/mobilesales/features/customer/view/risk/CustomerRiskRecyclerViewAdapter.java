package com.proje.mobilesales.features.customer.view.risk;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.adapter.IListRecyclerView;
import com.proje.mobilesales.features.customer.model.CustomerRiskProperty;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class CustomerRiskRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRiskRecyclerViewAdapter.ItemViewHolder> implements IListRecyclerView {
    private final boolean isAttached;
    private String loadingText;
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    private Context mContext;
    private ArrayList<CustomerRiskProperty> mCustomerRiskProperties;
    private LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;
    private final BaseCustomerRepository repository;
    private final CustomerRiskViewModel viewModel;

    public boolean isCardViewEnabled() {
        return false;
    }
    public void restoreState(Bundle bundle) {
    }
    public void setCardViewEnabled(boolean z) {
    }

    public CustomerRiskRecyclerViewAdapter() {
        BaseCustomerRepository baseCustomerRepository = new BaseCustomerRepository();
        this.repository = baseCustomerRepository;
        this.viewModel = new CustomerRiskViewModel(baseCustomerRepository);
        this.mCustomerRiskProperties = new ArrayList<>();
        this.isAttached = this.mContext != null;
    }

    public LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    public void setMLayoutInflater(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public Context getMContext() {
        return this.mContext;
    }

    public void setMContext(Context context) {
        this.mContext = context;
    }

    public int getMTertiaryTextColorResId() {
        return this.mTertiaryTextColorResId;
    }

    public void setMTertiaryTextColorResId(int i2) {
        this.mTertiaryTextColorResId = i2;
    }

    public int getMSecondaryTextColorResId() {
        return this.mSecondaryTextColorResId;
    }

    public void setMSecondaryTextColorResId(int i2) {
        this.mSecondaryTextColorResId = i2;
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

    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        CustomerRiskProperty customerProperty = getCustomerProperty(i2);
        clear(holder);
        bindData(holder, customerProperty);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public final  CustomerRiskProperty f1 = null;
            CustomerRiskProperty r2;
            public  void CustomerRiskRecyclerViewAdapterExternalSyntheticLambda0(CustomerRiskProperty customerProperty2) {
                  r2 = customerProperty2;
            }
            public boolean onLongClick(View view) {
                boolean onBindViewHolderlambda0;
                onBindViewHolderlambda0 = CustomerRiskRecyclerViewAdapter.onBindViewHolderlambda0(CustomerRiskRecyclerViewAdapter.this, r2, view);
                return onBindViewHolderlambda0;
            }
        });
    }

    public static boolean onBindViewHolderlambda0(CustomerRiskRecyclerViewAdapter this0, CustomerRiskProperty customerRiskProperty, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(customerRiskProperty, "customerRiskProperty");
        AlertDialogBuilder<?> alertDialogBuilder = this0.mAlertDialogBuilder;
        Intrinsics.checkNotNull(alertDialogBuilder);
        alertDialogBuilder.setTitle(customerRiskProperty.getName()).setMessage(StringUtils.formatDouble(customerRiskProperty.getTotal())).create().show();
        return true;
    }

    public int getItemCount() {
        return this.mCustomerRiskProperties.size();
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.item_customer_risk_view, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }

    public Bundle saveState() {
        return new Bundle();
    }

    public void bindData(ItemViewHolder holder, CustomerRiskProperty property) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(property, "property");
        holder.getTxtRiskName().setText(property.getName());
        holder.getTxtRiskLimit().setText(StringUtils.formatDouble(property.getLimit()));
        holder.getTxtRiskClosed().setText(StringUtils.formatDouble(property.getClosed()));
        if (this.viewModel.getBaseErp().isHideCustomerBalance()) {
            holder.getTxtRiskTotal().setText(" - ");
        } else {
            holder.getTxtRiskTotal().setText(StringUtils.formatDouble(property.getTotal()));
        }
        holder.getChkRisk().setChecked(property.getRisk());
    }

    public void clear(ItemViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTxtRiskName().setText(this.loadingText);
        holder.getTxtRiskClosed().setText(this.loadingText);
        holder.getTxtRiskLimit().setText(this.loadingText);
        holder.getTxtRiskTotal().setText(this.loadingText);
        holder.getChkRisk().setChecked(false);
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox chkRisk;
        private final LinearLayout lnRiskContainer;
        private final TextView txtRiskClosed;
        private final TextView txtRiskLimit;
        private final TextView txtRiskName;
        private final TextView txtRiskTotal;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ln_riskContainer);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.lnRiskContainer = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.txt_riskName);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.txtRiskName = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.txt_riskLimit);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.txtRiskLimit = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.txt_riskClosed);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.txtRiskClosed = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.txt_riskTotal);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.txtRiskTotal = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.chk_risk);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
            this.chkRisk = (CheckBox) findViewById6;
        }

        public LinearLayout getLnRiskContainer() {
            return this.lnRiskContainer;
        }

        public TextView getTxtRiskName() {
            return this.txtRiskName;
        }

        public TextView getTxtRiskLimit() {
            return this.txtRiskLimit;
        }

        public TextView getTxtRiskClosed() {
            return this.txtRiskClosed;
        }

        public TextView getTxtRiskTotal() {
            return this.txtRiskTotal;
        }

        public CheckBox getChkRisk() {
            return this.chkRisk;
        }
    }

    public void initDisplayOptions(Context context) {
        if (this.isAttached) {
            notifyDataSetChanged();
        }
    }

    public boolean isAttached() {
        return this.isAttached;
    }

    private CustomerRiskProperty getCustomerProperty(int i2) {
        CustomerRiskProperty customerRiskProperty = this.mCustomerRiskProperties.get(i2);
        Intrinsics.checkNotNullExpressionValue(customerRiskProperty, "get(...)");
        return customerRiskProperty;
    }

    public void setCustomerRiskProperties(ArrayList<CustomerRiskProperty> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.mCustomerRiskProperties = arrayList;
        notifyDataSetChanged();
    }
}
