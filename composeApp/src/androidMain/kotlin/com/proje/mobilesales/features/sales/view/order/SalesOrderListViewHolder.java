package com.proje.mobilesales.features.sales.view.order;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.databinding.SalesorderListItemBinding;
import kotlin.jvm.internal.Intrinsics;

public final class SalesOrderListViewHolder extends RecyclerView.ViewHolder {
    private final CheckBox chbOrder;
    private final LinearLayout ln_fiche_container;
    private final LinearLayout ln_fiche_detail_container;
    private final LinearLayout ln_salesman;
    private final LinearLayout ln_salesorder_detailheader;
    private final LinearLayout ln_salesorderlist_container;
    private final TextView tvAmount;
    private final TextView tvDateTime;
    private final TextView tvDetailHeader_FicheNo;
    private final TextView tvDetailHeader_OrderDate;
    private final TextView tvFicheNo;
    private final TextView tvSalesMan;
    private final TextView tvStockName;
    private final TextView tvStokCode;

    public SalesOrderListViewHolder(SalesorderListItemBinding binding) {
        super(binding.getRoot());
        Intrinsics.checkNotNullParameter(binding, "binding");
        LinearLayout lnSalesorderlistContainer = binding.lnSalesorderlistContainer;
        Intrinsics.checkNotNullExpressionValue(lnSalesorderlistContainer, "lnSalesorderlistContainer");
        this.ln_salesorderlist_container = lnSalesorderlistContainer;
        LinearLayout lnFicheDetailContainer = binding.lnFicheDetailContainer;
        Intrinsics.checkNotNullExpressionValue(lnFicheDetailContainer, "lnFicheDetailContainer");
        this.ln_fiche_detail_container = lnFicheDetailContainer;
        LinearLayout lnFicheContainer = binding.lnFicheContainer;
        Intrinsics.checkNotNullExpressionValue(lnFicheContainer, "lnFicheContainer");
        this.ln_fiche_container = lnFicheContainer;
        LinearLayout lnSalesorderDetailheader = binding.lnSalesorderDetailheader;
        Intrinsics.checkNotNullExpressionValue(lnSalesorderDetailheader, "lnSalesorderDetailheader");
        this.ln_salesorder_detailheader = lnSalesorderDetailheader;
        LinearLayout lnSalesman = binding.lnSalesman;
        Intrinsics.checkNotNullExpressionValue(lnSalesman, "lnSalesman");
        this.ln_salesman = lnSalesman;
        TextView tvFicheNo = binding.tvFicheNo;
        Intrinsics.checkNotNullExpressionValue(tvFicheNo, "tvFicheNo");
        this.tvFicheNo = tvFicheNo;
        TextView tvDateTime = binding.tvDateTime;
        Intrinsics.checkNotNullExpressionValue(tvDateTime, "tvDateTime");
        this.tvDateTime = tvDateTime;
        TextView tvSalesMan = binding.tvSalesMan;
        Intrinsics.checkNotNullExpressionValue(tvSalesMan, "tvSalesMan");
        this.tvSalesMan = tvSalesMan;
        TextView tvDetailHeaderFicheNo = binding.tvDetailHeaderFicheNo;
        Intrinsics.checkNotNullExpressionValue(tvDetailHeaderFicheNo, "tvDetailHeaderFicheNo");
        this.tvDetailHeader_FicheNo = tvDetailHeaderFicheNo;
        TextView tvDetailHeaderOrderDate = binding.tvDetailHeaderOrderDate;
        Intrinsics.checkNotNullExpressionValue(tvDetailHeaderOrderDate, "tvDetailHeaderOrderDate");
        this.tvDetailHeader_OrderDate = tvDetailHeaderOrderDate;
        TextView tvStokCode = binding.tvStokCode;
        Intrinsics.checkNotNullExpressionValue(tvStokCode, "tvStokCode");
        this.tvStokCode = tvStokCode;
        TextView tvStockName = binding.tvStockName;
        Intrinsics.checkNotNullExpressionValue(tvStockName, "tvStockName");
        this.tvStockName = tvStockName;
        TextView tvAmount = binding.tvAmount;
        Intrinsics.checkNotNullExpressionValue(tvAmount, "tvAmount");
        this.tvAmount = tvAmount;
        CheckBox chbOrder = binding.chbOrder;
        Intrinsics.checkNotNullExpressionValue(chbOrder, "chbOrder");
        this.chbOrder = chbOrder;
    }

    public LinearLayout getLn_salesorderlist_container() {
        return this.ln_salesorderlist_container;
    }

    public LinearLayout getLn_fiche_detail_container() {
        return this.ln_fiche_detail_container;
    }

    public LinearLayout getLn_fiche_container() {
        return this.ln_fiche_container;
    }

    public LinearLayout getLn_salesorder_detailheader() {
        return this.ln_salesorder_detailheader;
    }

    public LinearLayout getLn_salesman() {
        return this.ln_salesman;
    }

    public TextView getTvFicheNo() {
        return this.tvFicheNo;
    }

    public TextView getTvDateTime() {
        return this.tvDateTime;
    }

    public TextView getTvSalesMan() {
        return this.tvSalesMan;
    }

    public TextView getTvDetailHeader_FicheNo() {
        return this.tvDetailHeader_FicheNo;
    }

    public TextView getTvDetailHeader_OrderDate() {
        return this.tvDetailHeader_OrderDate;
    }

    public TextView getTvStokCode() {
        return this.tvStokCode;
    }

    public TextView getTvStockName() {
        return this.tvStockName;
    }

    public TextView getTvAmount() {
        return this.tvAmount;
    }

    public CheckBox getChbOrder() {
        return this.chbOrder;
    }
}
