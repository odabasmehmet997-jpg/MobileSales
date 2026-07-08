package com.proje.mobilesales.features.adapter;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.core.interfaces.OnLoadMoreListener;
import com.proje.mobilesales.features.visit.model.VisitInfoShort;

import java.util.ArrayList;

public abstract class EndlessListRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends ListRecyclerViewAdapter {
    private static final String TAG = "EndlessRecyclerAdapter";
    protected final int VIEW_TYPE_ITEM = 0;
    protected final int VIEW_TYPE_LOADING = 1;
    protected EndlessScrollListener mEndlessScrollListener;
    protected OnLoadMoreListener onLoadMoreListener;

    public abstract void addItem(ArrayList<ArrayList<VisitInfoShort>> list);

    public abstract void insertProgressBar();

    public abstract void removeProgressBar();

    protected abstract void restartAdapterAndScroll();

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        C26311 c26311 = new C26311(linearLayoutManager);
        this.mEndlessScrollListener = c26311;
        this.mRecyclerView.addOnScrollListener(c26311);
    }

    /* renamed from: com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter1 */
    class C26311 extends EndlessScrollListener {
        C26311(LinearLayoutManager linearLayoutManager) {
            super(linearLayoutManager);
        }

        @Override // com.proje.mobilesales.features.adapter.EndlessScrollListener
        public void onLoadMore(int i2) {
            EndlessListRecyclerViewAdapter.this.mRecyclerView.post(new Runnable() { // from class: com.proje.mobilesales.features.adapter.EndlessListRecyclerViewAdapter1ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public void run() {
                    EndlessListRecyclerViewAdapter.C26311.this.lambdaonLoadMore0();
                }
            });
            OnLoadMoreListener onLoadMoreListener = EndlessListRecyclerViewAdapter.this.onLoadMoreListener;
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore(50, i2 * 50);
            }
        }


        public void lambdaonLoadMore0() {
            Log.d(EndlessListRecyclerViewAdapter.TAG, "onLoadMore: notify post");
            EndlessListRecyclerViewAdapter.this.insertProgressBar();
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override // com.proje.mobilesales.features.adapter.ListRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return getItem(i2) == null ? 1 : 0;
    }

    public void restartScroll() {
        restartAdapterAndScroll();
        this.onLoadMoreListener = null;
        this.mEndlessScrollListener.resetState();
    }
}
