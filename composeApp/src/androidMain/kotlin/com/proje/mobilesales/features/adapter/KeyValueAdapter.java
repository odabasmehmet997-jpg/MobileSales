package com.proje.mobilesales.features.adapter;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.features.model.KeyValueProperty;

import java.util.ArrayList;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public class KeyValueAdapter extends RecyclerView.Adapter<ItemViewHolder> implements IListRecyclerView {
    private String loadingText;
 
    AlertDialogBuilder mAlertDialogBuilder;
    private int mCardBackgroundColorResId;
    private int mCardHighlightColorResId;
    protected Context mContext;
    private ArrayList<KeyValueProperty> mKeyValueProperties = new ArrayList<>();
    protected LayoutInflater mLayoutInflater;
    private int mSecondaryTextColorResId;
    private int mTertiaryTextColorResId;
 
    public boolean isCardViewEnabled() {
        return false;
    }
 
    public void restoreState(Bundle bundle) {
    }
 
    public void setCardViewEnabled(boolean z) {
    }
 
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        this.mContext = context;
        if (context instanceof BaseInjectableActivity) {
            ((BaseInjectableActivity) context).getActivityComponent().inject(this);
        }
        this.loadingText = this.mContext.getString(R.string.loading_text);
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
 
    @NonNull
    public com.proje.mobilesales.features.adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(com.proje.mobilesales.features.adapter.ItemViewHolder holder, int position) {

    }
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
        KeyValueProperty property = getProperty(i2);
        clear(itemViewHolder);
        bindData(itemViewHolder, property);
    }

    void bindData(ItemViewHolder itemViewHolder, KeyValueProperty keyValueProperty) {
        itemViewHolder.txtKey.setText(keyValueProperty.getKey());
        itemViewHolder.txtValue.setText(keyValueProperty.getValue());
    }

    void clear(ItemViewHolder itemViewHolder) {
        itemViewHolder.txtKey.setText(this.loadingText);
        itemViewHolder.txtValue.setText(this.loadingText);
    }

    public KeyValueProperty getProperty(int i2) {
        return this.mKeyValueProperties.get(i2);
    }

    public boolean isAttached() {
        return this.mContext != null;
    }
 
    public Bundle saveState() {
        return new Bundle();
    }
 
    public int getItemCount() {
        return this.mKeyValueProperties.size();
    }

    public void setKeyValueProperties(ArrayList<KeyValueProperty> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.mKeyValueProperties = arrayList;
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout lyKeyValueContainer;
        final TextView txtKey;
        final TextView txtValue;

        public ItemViewHolder(View view) {
            super(view);
            this.lyKeyValueContainer = view.findViewById(R.id.lyKeyValueContainer);
            this.txtKey = view.findViewById(R.id.txtKey);
            this.txtValue = view.findViewById(R.id.txtValue);
        }
    }
}
