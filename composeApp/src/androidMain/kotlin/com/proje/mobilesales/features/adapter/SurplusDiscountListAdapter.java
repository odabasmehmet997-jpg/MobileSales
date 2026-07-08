package com.proje.mobilesales.features.adapter;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.SurplusDiscount;

import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public class SurplusDiscountListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private String loadingText;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;
    private List<SurplusDiscount> surplusDiscounts = new ArrayList(); 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.loadingText = context.getString(R.string.loading_text);
        this.mLayoutInflater = createLayoutInflater(this.mContext);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.textColorTertiary, R.attr.textColorSecondary, R.attr.colorCardBackground, R.attr.colorCardHighlight});
        this.mTertiaryTextColorResId = obtainStyledAttributes.getInt(0, 0);
        this.mSecondaryTextColorResId = obtainStyledAttributes.getInt(1, 0);
        this.mCardBackgroundColorResId = obtainStyledAttributes.getInt(2, 0);
        this.mCardHighlightColorResId = obtainStyledAttributes.getInt(3, 0);
        obtainStyledAttributes.recycle();
    } 
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
    } 
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.item_surplus_discount, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull com.proje.mobilesales.features.adapter.ItemViewHolder holder, int position) {
        
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        SurplusDiscount surplusDiscount = this.surplusDiscounts.get(i2);
        itemViewHolder.txtDiscountCode.setText(surplusDiscount.getKosulKod());
        itemViewHolder.txtDiscountDefinition.setText(surplusDiscount.getAciklama());
        itemViewHolder.txtSurplusStockName.setText(surplusDiscount.getMalfStokKodu());
        itemViewHolder.txtSurplusStockAmount.setText(StringUtils.dFormat(surplusDiscount.getMalfMiktar1()));
        itemViewHolder.txtSurplusExtraAmount.setText(StringUtils.dFormat(surplusDiscount.getMalFazlasi1()));
        itemViewHolder.txtSurplusDiscount1.setText(StringUtils.dFormat(surplusDiscount.getInd1()));
        itemViewHolder.txtSurplusDiscount2.setText(StringUtils.dFormat(surplusDiscount.getInd2()));
        itemViewHolder.txtSurplusDiscount3.setText(StringUtils.dFormat(surplusDiscount.getInd3()));
        itemViewHolder.txtSurplusDiscount4.setText(StringUtils.dFormat(surplusDiscount.getInd4()));
        itemViewHolder.txtSurplusDiscount5.setText(StringUtils.dFormat(surplusDiscount.getInd5()));
        itemViewHolder.txtSurplusDiscount6.setText(StringUtils.dFormat(surplusDiscount.getInd6()));
    }
 
    public int getItemCount() {
        return this.surplusDiscounts.size();
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void setSurplusDiscounts(List<SurplusDiscount> list) {
        this.surplusDiscounts = list;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout mItemContainer;
        final TextView txtDiscountCode;
        final TextView txtDiscountDefinition;
        final TextView txtSurplusDiscount1;
        final TextView txtSurplusDiscount2;
        final TextView txtSurplusDiscount3;
        final TextView txtSurplusDiscount4;
        final TextView txtSurplusDiscount5;
        final TextView txtSurplusDiscount6;
        final TextView txtSurplusExtraAmount;
        final TextView txtSurplusStockAmount;
        final TextView txtSurplusStockName;

        public ItemViewHolder(View view) {
            super(view);
            this.mItemContainer = view.findViewById(R.id.ln_item_surplus_discount);
            this.txtDiscountCode = view.findViewById(R.id.tv_discount_code);
            this.txtDiscountDefinition = view.findViewById(R.id.tv_discount_definition);
            this.txtSurplusStockName = view.findViewById(R.id.tv_surplus_stock_name);
            this.txtSurplusStockAmount = view.findViewById(R.id.tv_surplus_stock_amount);
            this.txtSurplusExtraAmount = view.findViewById(R.id.tv_surplus_extra_amount);
            this.txtSurplusDiscount1 = view.findViewById(R.id.tv_surplus_discount1);
            this.txtSurplusDiscount2 = view.findViewById(R.id.tv_surplus_discount2);
            this.txtSurplusDiscount3 = view.findViewById(R.id.tv_surplus_discount3);
            this.txtSurplusDiscount4 = view.findViewById(R.id.tv_surplus_discount4);
            this.txtSurplusDiscount5 = view.findViewById(R.id.tv_surplus_discount5);
            this.txtSurplusDiscount6 = view.findViewById(R.id.tv_surplus_discount6);
        }
    }
}
