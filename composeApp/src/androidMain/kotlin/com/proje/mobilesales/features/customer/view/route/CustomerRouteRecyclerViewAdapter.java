package com.proje.mobilesales.features.customer.view.route;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.customer.model.Customer;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class CustomerRouteRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRouteRecyclerViewAdapter.ItemViewHolder> {
    private Context mContext;
    private final List<Customer> mCustomerCursor = new ArrayList();
    private LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private OnLoadMoreListener onLoadMoreListener;

    private LayoutInflater getMLayoutInflater() {
        return mLayoutInflater;
    }

    private void setMLayoutInflater(final LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    private RecyclerView getMRecyclerView() {
        return mRecyclerView;
    }

    private void setMRecyclerView(final RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    private Context getMContext() {
        return mContext;
    }

    private void setMContext(final Context context) {
        mContext = context;
    }

    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = recyclerView.getContext();
        mContext = context;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        mLayoutInflater = createLayoutInflater(mContext);
    }
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        mContext = null;
    }

    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        final LayoutInflater layoutInflater = mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        final View inflate = layoutInflater.inflate(R.layout.customer_route_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(inflate);
    }

    public void onBindViewHolder(final ItemViewHolder holder, final int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Customer item = this.getItem(i2);
        holder.getMCustomerName().setText(item.getTitle());
        holder.getMCustomerCode().setText(item.getCode());
        final TextView mShipAddress = holder.getMShipAddress();
        final Context context = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context);
        final String format = String.format("%s: ", context.getString(R.string.str_customer_ship_address));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        mShipAddress.setText(this.cat(format, item.getShipName()));
        final TextView mCustomerCity = holder.getMCustomerCity();
        final Context context2 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context2);
        final String format2 = String.format("%s: ", context2.getString(R.string.str_city));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        mCustomerCity.setText(this.cat(format2, item.getCity()));
        final TextView mCustomerTown = holder.getMCustomerTown();
        final Context context3 = ContextUtils.getmContext();
        Intrinsics.checkNotNull(context3);
        final String format3 = String.format("%s: ", context3.getString(R.string.str_town));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        mCustomerTown.setText(this.cat(format3, item.getTown()));
    }

    public int getItemCount() {
        return mCustomerCursor.size();
    }

    private String cat(final String str, final String str2) {
        if (null == str2 || TextUtils.isEmpty(str2)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ");
            final Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            sb.append(context.getString(R.string.empty_text));
            return sb.toString();
        }
        return str + " " + str2;
    }

    private Customer getItem(final int i2) {
        return mCustomerCursor.get(i2);
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        Intrinsics.checkNotNullParameter(onLoadMoreListener, "onLoadMoreListener");
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void restartAdapterAndScroll() {
        if (0 < getItemCount()) {
            mCustomerCursor.clear();
        }
    }

    public boolean isAttached() {
        return null != this.mContext;
    }

    public void initDisplayOptions(final Context context) {
        if (this.isAttached()) {
            this.notifyDataSetChanged();
        }
    }

    public void addItem(final ArrayList<Customer> arrayList) {
        if (null != arrayList) {
            mCustomerCursor.addAll(arrayList);
            this.notifyDataSetChanged();
        }
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mCustomerCity;
        private final TextView mCustomerCode;
        private final TextView mCustomerName;
        private final TextView mCustomerTown;
        private final RelativeLayout mItemContainer;
        private final RelativeLayout mItemHeader;
        private final LinearLayout mRltCityTown;
        private final RelativeLayout mRltCustomerDetail;
        private final TextView mShipAddress;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            final View findViewById = itemView.findViewById(R.id.rlt_customer_container);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.RelativeLayout");
            mItemContainer = (RelativeLayout) findViewById;
            final View findViewById2 = itemView.findViewById(R.id.rlt_customer_header);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.RelativeLayout");
            mItemHeader = (RelativeLayout) findViewById2;
            final View findViewById3 = itemView.findViewById(R.id.txt_customerName);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            mCustomerName = (TextView) findViewById3;
            final View findViewById4 = itemView.findViewById(R.id.txt_customerCode);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
            mCustomerCode = (TextView) findViewById4;
            final View findViewById5 = itemView.findViewById(R.id.txt_customer_city);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
            mCustomerCity = (TextView) findViewById5;
            final View findViewById6 = itemView.findViewById(R.id.txt_customer_town);
            Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
            mCustomerTown = (TextView) findViewById6;
            final View findViewById7 = itemView.findViewById(R.id.txt_shipAddress);
            Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
            mShipAddress = (TextView) findViewById7;
            final View findViewById8 = itemView.findViewById(R.id.rlt_customer_detail);
            Intrinsics.checkNotNull(findViewById8, "null cannot be cast to non-null type android.widget.RelativeLayout");
            mRltCustomerDetail = (RelativeLayout) findViewById8;
            final View findViewById9 = itemView.findViewById(R.id.ln_city_towm_layout);
            Intrinsics.checkNotNull(findViewById9, "null cannot be cast to non-null type android.widget.LinearLayout");
            mRltCityTown = (LinearLayout) findViewById9;
        }

        public TextView getMCustomerName() {
            return mCustomerName;
        }

        public TextView getMCustomerCode() {
            return mCustomerCode;
        }

        public TextView getMCustomerCity() {
            return mCustomerCity;
        }

        public TextView getMCustomerTown() {
            return mCustomerTown;
        }

        public TextView getMShipAddress() {
            return mShipAddress;
        }
    }
}
