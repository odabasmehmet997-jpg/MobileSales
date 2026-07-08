package com.proje.mobilesales.features.adapter;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.AlternativeItemAmountChangeListener;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.product.model.Product;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public class AlternativePromotionListAdapter  {
    private ArrayList<Product> alternativePromotionItems = new ArrayList<>();
    private AlternativeItemAmountChangeListener listener;
    private String loadingText;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;
    private double mainPromotionItemAmount;
    private int mainPromotionItemRef;

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
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
        super.notifyAll();
        this.mContext = null;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(this.mLayoutInflater.inflate(R.layout.item_alternative_promotion, viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        final Product product = this.alternativePromotionItems.get(i2);
        itemViewHolder.promotionCode.setText(product.getCode());
        itemViewHolder.promotionName.setText(product.getName());
        itemViewHolder.promotionStock.setText(StringUtils.convertDoubleToString(Double.valueOf(product.getActualStock())));
        itemViewHolder.promotionAmount.setText(product.getAmount() == 0.0d ? "" : StringUtils.convertDoubleToString(Double.valueOf(product.getAmount())));
        itemViewHolder.promotionAmount.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }
            public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }
            public void afterTextChanged(Editable editable) {
                product.setAmount(StringUtils.convertStringToDouble(editable.toString()));
                AlternativePromotionListAdapter.this.listener.getAlternativeItemAmountChangeListener(AlternativePromotionListAdapter.this.alternativePromotionItems);
            }
        });
        itemViewHolder.setIsRecyclable(false);
    }
    public int getItemCount() {
        return this.alternativePromotionItems.size();
    }

    public void initDisplayOptions(Context context) {
        if (isAttached()) {
            notifyDataSetChanged();
        }
    }

    private void notifyDataSetChanged() {
    }

    public boolean isAttached() {
        return this.mContext != null;
    }

    public void setProducts(ArrayList<Product> arrayList, int i2, double d2, AlternativeItemAmountChangeListener alternativeItemAmountChangeListener) {
        this.alternativePromotionItems = arrayList;
        this.mainPromotionItemRef = i2;
        this.mainPromotionItemAmount = d2;
        this.listener = alternativeItemAmountChangeListener;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final EditText promotionAmount;
        final TextView promotionCode;
        final TextView promotionName;
        final TextView promotionStock;

        public ItemViewHolder(View view) {
            super(view);
            this.promotionCode = view.findViewById(R.id.tv_promotion_code);
            this.promotionName = view.findViewById(R.id.tv_promotion_name);
            this.promotionAmount = view.findViewById(R.id.tv_promotion_amount);
            this.promotionStock = view.findViewById(R.id.tv_promotion_stock);
        }
    }

    public ArrayList<Product> getProducts() {
        return this.alternativePromotionItems;
    }
}
