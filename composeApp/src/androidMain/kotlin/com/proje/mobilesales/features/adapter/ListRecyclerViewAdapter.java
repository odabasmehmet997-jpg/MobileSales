package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;

import static com.proje.mobilesales.core.utils.AppUtils.createLayoutInflater;

public abstract class ListRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> implements IListRecyclerView {
    private static final String STATE_LAST_SELECTION_POSITION = "state:lastSelectedPosition";
    protected int mCardElevation;
    protected int mCardRadius;
    public Context mContext;
    protected LayoutInflater mInflater;
    protected RecyclerView mRecyclerView;
    protected int mLastSelectedPosition = -1;
    protected boolean mCardViewEnabled = true;
    public SparseBooleanArray mSelected = new SparseBooleanArray(0);

    protected abstract void bindItem(VH vh);

    protected abstract void clearViewHolder(VH vh);

    protected abstract T getItem(int i2);

    protected abstract void handleItemClick(T t, VH vh);

    protected abstract boolean isItemAvailable(T t);

    protected void loadItem(int i2) {
    }

    public ListRecyclerViewAdapter() {
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
        Context context = recyclerView.getContext();
        this.mContext = context;
        this.mInflater = createLayoutInflater(context);
        this.mCardElevation = this.mContext.getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
        this.mCardRadius = this.mContext.getResources().getDimensionPixelSize(R.dimen.cardview_default_radius);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mContext = null;
        this.mRecyclerView = null;
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public final void setCardViewEnabled(boolean z) {
        this.mCardViewEnabled = z;
        notifyDataSetChanged();
    }

    public int getSelectedSize() {
        return this.mSelected.size();
    }

    public SparseBooleanArray getSelected() {
        return this.mSelected;
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public final boolean isCardViewEnabled() {
        return this.mCardViewEnabled;
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public Bundle saveState() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArray("states", null);
        bundle.putInt(STATE_LAST_SELECTION_POSITION, this.mLastSelectedPosition);
        bundle.putBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED, this.mCardViewEnabled);
        return bundle;
    }

    @Override // com.proje.mobilesales.features.adapter.IListRecyclerView
    public void restoreState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mCardViewEnabled = bundle.getBoolean(IListRecyclerView.STATE_CARD_VIEW_ENABLED, true);
        this.mLastSelectedPosition = bundle.getInt(STATE_LAST_SELECTION_POSITION);
    }

    public final boolean isAttached() {
        return this.mContext != null;
    }

    protected boolean isSelected(int i2) {
        return this.mSelected.get(i2);
    }

    public void selectAll() {
        this.mSelected.clear();
        for (int i2 = 0; i2 < getItemCount(); i2++) {
            this.mSelected.put(i2, true);
        }
        notifyDataSetChanged();
    }
}
