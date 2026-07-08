package com.proje.mobilesales.features.sales.view.distribution;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.PopupMenu;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.features.sales.model.Distribution;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public final class DistributionListRecyclerViewAdapter extends RecyclerView.Adapter<DistributionListRecyclerViewAdapter.ItemViewHolder> {
    private AlertDialogBuilder<?> mAlertDialogBuilder;
    private Context mContext;
    private ArrayList<Distribution> mDatas;
    private LayoutInflater mLayoutInflater;
    private PopupMenu mPopupMenu;
    private SalesType mSalesType;
    private boolean result;

    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        return this.mAlertDialogBuilder;
    }

    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        this.mAlertDialogBuilder = alertDialogBuilder;
    }

    public PopupMenu getMPopupMenu() {
        return this.mPopupMenu;
    }

    public void setMPopupMenu(PopupMenu popupMenu) {
        this.mPopupMenu = popupMenu;
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
        Context context2 = ContextUtils.getmContext();
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilder = new AlertDialogBuilder.Impl(context2, (BaseInjectableActivity) activity);
        this.mPopupMenu = new PopupMenu.Impl();
    }

    public Distribution getProperty(int r1) {
        ArrayList<Distribution> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        Distribution distribution = arrayList.get(r1);
        Intrinsics.checkNotNullExpressionValue(distribution, "get(...)");
        return distribution;
    } 
    public void setData(ArrayList<Distribution> arrayList) {
        this.mDatas = arrayList;
        notifyDataSetChanged();
    }
    public void setSalesType(SalesType salesType) {
        this.mSalesType = salesType;
        this.result = true;
    }

    public boolean isAttached() {
        return this.mContext != null;
    } 
    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }
 
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int r4) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflater = this.mLayoutInflater;
        Intrinsics.checkNotNull(layoutInflater);
        View viewInflate = layoutInflater.inflate(R.layout.distribution_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
        return new ItemViewHolder(viewInflate);
    }
 
    public void onBindViewHolder(ItemViewHolder holder, int r3) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        bindData(holder, getProperty(r3), r3);
    } 
    public void bindData(final ItemViewHolder holder, final Distribution data, int r6) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        holder.getTvFicheNo().setText(data.ficheNo);
        holder.getTvDateTime().setText(DateAndTimeUtils.convertStringDate(data.goDate, "yyyy-MM-dd'T'HH:mm:ss", "dd.MM.yyyy"));
        SalesType salesType = this.mSalesType;
        Intrinsics.checkNotNull(salesType);
        if (SalesUtils.isSalesTypeFree(salesType)) {
            TextView lblCari = holder.getLblCari();
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            lblCari.setText(context.getString(R.string.str_customer_accounts));
            holder.getTvCustomer().setText(data.code + " - " + data.definition);
            holder.getBtnMore().setVisibility(View.VISIBLE);
            holder.getBtnMore().setOnClickListener(view -> DistributionListRecyclerViewAdapter.bindDatalambda0(this, holder, data, view));
            return;
        }
        TextView lblCari2 = holder.getLblCari();
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        lblCari2.setText(context2.getString(R.string.str_shippent_address));
        holder.getTvCustomer().setText(data.shipinfoCode + " - " + data.shipinfoName);
        holder.getBtnMore().setVisibility(View.GONE);
        holder.getLnDistributionListContainer().setOnClickListener(view -> DistributionListRecyclerViewAdapter.bindDatalambda1(this, data, view));
    } 
    public static void bindDatalambda0(DistributionListRecyclerViewAdapter this0, ItemViewHolder holder, Distribution data, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(data, "data");
        this0.showMoreOptions(holder.getLnDistributionListContainer(), data.clientRef, data.distorderRef);
    } 
    public static void bindDatalambda1(DistributionListRecyclerViewAdapter this0, Distribution data, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(data, "data");
        DistributionListActivity distributionListActivity = (DistributionListActivity) this0.mContext;
        Intrinsics.checkNotNull(distributionListActivity);
        distributionListActivity.goToFiche(data.clientRef, data.distorderRef, this0.mSalesType, true);
    } 
    private void showMoreOptions(View view, final int r5, final int r6) {
        PopupMenu popupMenu = this.mPopupMenu;
        Intrinsics.checkNotNull(popupMenu);
        popupMenu.create(this.mContext, view, 5).inflate(R.menu.menu_context_distribution).setMenuItemVisible(R.id.menu_new_invoice, true).setMenuItemVisible(R.id.menu_new_dispatch, true).setOnMenuItemClickListener(menuItem -> DistributionListRecyclerViewAdapter.showMoreOptionslambda2(this, r5, r6, menuItem)).show();
    }  
    public static   boolean showMoreOptionslambda2(DistributionListRecyclerViewAdapter this0, int r5, int r6, MenuItem item) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.menu_new_invoice) {
            DistributionListActivity distributionListActivity = (DistributionListActivity) this0.mContext;
            Intrinsics.checkNotNull(distributionListActivity);
            distributionListActivity.goToFiche(r5, r6, SalesType.INVOICE, false);
            return true;
        }
        if (item.getItemId() != R.id.menu_new_dispatch) {
            return false;
        }
        DistributionListActivity distributionListActivity2 = (DistributionListActivity) this0.mContext;
        Intrinsics.checkNotNull(distributionListActivity2);
        distributionListActivity2.goToFiche(r5, r6, SalesType.DISPATCH, false);
        return true;
    } 
    public int getItemCount() {
        ArrayList<Distribution> arrayList = this.mDatas;
        Intrinsics.checkNotNull(arrayList);
        return arrayList.size();
    } 
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final View btnMore;
        private final TextView lblCari;
        private final LinearLayout lnDistributionListContainer;
        private final TextView tvCustomer;
        private final TextView tvDateTime;
        private final TextView tvFicheNo;
 
        public ItemViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.ln_distribution_list_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.lnDistributionListContainer = (LinearLayout) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tvFicheNo);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tvFicheNo = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.tvDateTime);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.tvDateTime = (TextView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.tvCustomer);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.tvCustomer = (TextView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.lblCari);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.lblCari = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(R.id.button_more);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.btnMore = viewFindViewById6;
        }

        public LinearLayout getLnDistributionListContainer() {
            return this.lnDistributionListContainer;
        }

        public TextView getTvFicheNo() {
            return this.tvFicheNo;
        }

        public TextView getTvDateTime() {
            return this.tvDateTime;
        }

        public TextView getTvCustomer() {
            return this.tvCustomer;
        }

        public TextView getLblCari() {
            return this.lblCari;
        }

        public View getBtnMore() {
            return this.btnMore;
        }
    }
}
