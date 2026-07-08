package com.proje.mobilesales.features.adapter;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
    public final CardView mCardView;

    public ItemViewHolder(View view) {
        super(view);
        this.mCardView = (CardView) view;
    }
}
