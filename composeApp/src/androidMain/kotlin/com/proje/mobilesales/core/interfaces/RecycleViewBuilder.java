package com.proje.mobilesales.core.interfaces;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
 
public interface RecycleViewBuilder<T extends RecyclerView> {
    RecycleViewBuilder setAdapter(RecyclerView.Adapter adapter);

    RecycleViewBuilder setDragListener(View.OnDragListener onDragListener);

    RecycleViewBuilder setFixedSize(boolean z);

    RecycleViewBuilder setItemAnimator(RecyclerView.ItemAnimator itemAnimator);

    RecycleViewBuilder setLayout(RecyclerView.LayoutManager layoutManager);

    RecycleViewBuilder setOnClickListener(View.OnClickListener onClickListener);

    RecycleViewBuilder setOnHoverListener(View.OnHoverListener onHoverListener);

    RecycleViewBuilder setOnLongClickListener(View.OnLongClickListener onLongClickListener);

    class Impl implements RecycleViewBuilder<RecyclerView> {
        private final RecyclerView mRecyclerView;

        
        public RecycleViewBuilder setAdapter(RecyclerView.Adapter adapter) {
            return null;
        }

        
        public RecycleViewBuilder setDragListener(View.OnDragListener onDragListener) {
            return null;
        }

        
        public RecycleViewBuilder setFixedSize(boolean z) {
            return null;
        }

        
        public RecycleViewBuilder setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
            return null;
        }

        
        public RecycleViewBuilder setLayout(RecyclerView.LayoutManager layoutManager) {
            return null;
        }

        
        public RecycleViewBuilder setOnClickListener(View.OnClickListener onClickListener) {
            return null;
        }

        
        public RecycleViewBuilder setOnHoverListener(View.OnHoverListener onHoverListener) {
            return null;
        }

        
        public RecycleViewBuilder setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
            return null;
        }

        public Impl(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }
    }
}
