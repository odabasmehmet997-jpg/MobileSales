package com.proje.mobilesales.features.sales.view.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.sales.model.database.LastOrderProducts;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class LastOrderProductsRecyclerViewAdapter extends RecyclerView.Adapter<LastOrderProductsRecyclerViewAdapter.ItemViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private final List<LastOrderProducts> mProductList = new ArrayList();
    private RecyclerView mRecyclerView;
    private OnLoadMoreListener onLoadMoreListener;
    private LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }
    private void setMLayoutInflater(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }
    private RecyclerView getMRecyclerView() {
        return this.mRecyclerView;
    }
    private void setMRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }
    private Context getMContext() {
        return this.mContext;
    }
    private void setMContext(Context context) {
        this.mContext = context;
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        setMContext(context);
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        setMLayoutInflater(createLayoutInflater(this.mContext));
    }
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        setMContext(null);
        setMLayoutInflater(null);
    }
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.last_order_product_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        LastOrderProducts item = getItem(i2);
        holder.getMProductName().setText(item.getStockName());
        holder.getMProductCode().setText(item.getStockCode());
        TextView mDate = holder.getMDate();
        StringUtils stringUtils = StringUtils.INSTANCE;
        Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        String format = String.format("%s : ", context.getString(R.string.str_date));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        mDate.setText(stringUtils.cat(format, item.getDate()));
        TextView mProductAmount = holder.getMProductAmount();
        Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        String format2 = String.format("%s : ", context2.getString(R.string.str_quantity));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        mProductAmount.setText(stringUtils.cat(format2, String.valueOf(StringUtils.toDouble(item.getAmount()))));
        TextView mShippedAmount = holder.getMShippedAmount();
        Context context3 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context3);
        String format3 = String.format("%s : ", context3.getString(R.string.str_shipped_amount));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        mShippedAmount.setText(stringUtils.cat(format3, String.valueOf(StringUtils.toDouble(item.getShippedAmount()))));
        TextView mUnitPrice = holder.getMUnitPrice();
        Context context4 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context4);
        String format4 = String.format("%s : ", context4.getString(R.string.str_unit_price));
        Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
        mUnitPrice.setText(stringUtils.cat(format4, String.valueOf(StringUtils.toDouble(item.getPrice()))));
        TextView mDiscount = holder.getMDiscount();
        Context context5 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context5);
        String format5 = String.format("%s : ", context5.getString(R.string.str_discount));
        Intrinsics.checkNotNullExpressionValue(format5, "format(...)");
        mDiscount.setText(stringUtils.cat(format5, String.valueOf(StringUtils.toDouble(item.getDiscount()))));
        TextView mNetPrice = holder.getMNetPrice();
        Context context6 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context6);
        String format6 = String.format(context6.getString(R.string.str_sales_net_total));
        Intrinsics.checkNotNullExpressionValue(format6, "format(...)");
        mNetPrice.setText(stringUtils.cat(format6, String.valueOf(StringUtils.toDouble(item.getLineNet()))));
    }
    public int getItemCount() {
        return this.mProductList.size();
    }
    private LastOrderProducts getItem(int i2) {
        return this.mProductList.get(i2);
    }
    public void restartAdapterAndScroll() {
        if (getItemCount() > 0) {
            this.mProductList.clear();
            notifyDataSetChanged();
        }
    }
    public void addItem(ArrayList<LastOrderProducts> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.mProductList.addAll(items);
        notifyDataSetChanged();
    }
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mDate;
        private final TextView mDiscount;
        private final TextView mNetPrice;
        private final TextView mProductAmount;
        private final TextView mProductCode;
        private final RelativeLayout mProductHeader;
        private final TextView mProductName;
        private final TextView mShippedAmount;
        private final TextView mUnitPrice;
        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.rlt_product_container);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.RelativeLayout");
            RelativeLayout mProductContainer = (RelativeLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.rlt_product_header);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.RelativeLayout");
            this.mProductHeader = (RelativeLayout) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.rlt_productName);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.mProductName = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.txt_productCode);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            this.mProductCode = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.txt_date);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
            this.mDate = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.txt_product_amount);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
            this.mProductAmount = (TextView) findViewById6;
            View findViewById7 = itemView.findViewById(R.id.txt_shipped_amount);
            Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
            this.mShippedAmount = (TextView) findViewById7;
            View findViewById8 = itemView.findViewById(R.id.txt_price);
            Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.TextView");
            this.mUnitPrice = (TextView) findViewById8;
            View findViewById9 = itemView.findViewById(R.id.txt_discount);
            Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.TextView");
            this.mDiscount = (TextView) findViewById9;
            View findViewById10 = itemView.findViewById(R.id.txt_net_price);
            Intrinsics.checkNotNull(findViewById10, "null cannot be cast to non-null type android.widget.TextView");
            this.mNetPrice = (TextView) findViewById10;
        }

        public TextView getMProductName() {
            return this.mProductName;
        }

        public TextView getMProductCode() {
            return this.mProductCode;
        }
        public TextView getMDate() {
            return this.mDate;
        }
        public TextView getMProductAmount() {
            return this.mProductAmount;
        }

        public TextView getMShippedAmount() {
            return this.mShippedAmount;
        }

        public TextView getMUnitPrice() {
            return this.mUnitPrice;
        }

        public TextView getMDiscount() {
            return this.mDiscount;
        }

        public TextView getMNetPrice() {
            return this.mNetPrice;
        }
    }
}
