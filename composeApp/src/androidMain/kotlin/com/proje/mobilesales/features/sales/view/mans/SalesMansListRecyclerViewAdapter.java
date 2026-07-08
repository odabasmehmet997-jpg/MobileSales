package com.proje.mobilesales.features.sales.view.mans;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.sales.model.SalesMan;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class SalesMansListRecyclerViewAdapter extends RecyclerView.Adapter<SalesMansListRecyclerViewAdapter.ItemViewHolder> {
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private Context mContext;
    private ArrayList<SalesMan> mDatas;
    private LayoutInflater mLayoutInflater;
    public PopupMenu mPopupMenu = new PopupMenu.Impl();

    private LayoutInflater getMLayoutInflater() {
        return this.mLayoutInflater;
    }

    private void setMLayoutInflater(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
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
        this.mContext = context;
        if (context instanceof BaseInjectableActivity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        Context context2 = this.mContext;
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
    }

    public SalesMan getProperty(int i2) {
        ArrayList<SalesMan> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        SalesMan salesMan = arrayList.get(i2);
        Intrinsics.checkNotNullExpressionValue(salesMan, "get(...)");
        return salesMan;
    }

    public void setData(ArrayList<SalesMan> arrayList) {
        this.mDatas = arrayList;
        notifyDataSetChanged();
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }
 
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View inflate = layoutInflater.inflate(R.layout.salesman_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ItemViewHolder(this, inflate);
    }
 
    public void onBindViewHolder(ItemViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(i2), i2);
    }

    public void bindData(ItemViewHolder holder, SalesMan data, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        SalesMan r2 = data;

        holder.getTvSalesManCode().setText(data.component2());
        holder.getTvSalesMan().setText(data.component3());
        holder.getLnSalesManListContainer().setOnClickListener(new View.OnClickListener() { 
            public final   SalesMan f1 = null;

            public   void SalesMansListRecyclerViewAdapterExternalSyntheticLambda0(SalesMan data2) {
                SalesMan r2 = data2;
            }
 
            public void onClick(View view) {
                SalesMansListRecyclerViewAdapter.bindDatalambda0(SalesMansListRecyclerViewAdapter.this, r2, view);
            }
        });
    }

    public static void bindDatalambda0(SalesMansListRecyclerViewAdapter this0, SalesMan data, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(data, "data");
        SalesMansListActivity salesMansListActivity = (SalesMansListActivity) this0.mContext;
        Intrinsics.checkNotNull(salesMansListActivity);
        salesMansListActivity.SelectSalesMan(data);
    }
 
    public int getItemCount() {
        ArrayList<SalesMan> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    }
 
    public final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lnSalesManListContainer;
        final SalesMansListRecyclerViewAdapter this0;
        private final TextView tvSalesMan;
        private final TextView tvSalesManCode;
 
        public ItemViewHolder(SalesMansListRecyclerViewAdapter salesMansListRecyclerViewAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this0 = salesMansListRecyclerViewAdapter;
            View findViewById = itemView.findViewById(R.id.ln_salesman_list_container);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.lnSalesManListContainer = (LinearLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvSalesManCode);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.tvSalesManCode = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvSalesMan);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.tvSalesMan = (TextView) findViewById3;
        }

        public LinearLayout getLnSalesManListContainer() {
            return this.lnSalesManListContainer;
        }

        public TextView getTvSalesManCode() {
            return this.tvSalesManCode;
        }

        public TextView getTvSalesMan() {
            return this.tvSalesMan;
        }
    }
}
