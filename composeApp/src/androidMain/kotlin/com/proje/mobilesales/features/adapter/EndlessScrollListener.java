package com.proje.mobilesales.features.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private int firstVisibleItem;
    private final LinearLayoutManager mLinearLayoutManager;
    private int totalItemCount;
    private int visibleItemCount;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private final int visibleThreshold = 5;
    private int currentPage = 0;
    private final int startingPageIndex = 0;

    public abstract void onLoadMore(final int i2);

    public EndlessScrollListener(final LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }
    public void onScrolled(final RecyclerView recyclerView, final int i2, final int i3) {
        int i4;
        super.onScrolled(recyclerView, i2, i3);
        this.visibleItemCount = recyclerView.getChildCount();
        this.totalItemCount = this.mLinearLayoutManager.getItemCount();
        int findFirstVisibleItemPosition = this.mLinearLayoutManager.findFirstVisibleItemPosition();
        this.firstVisibleItem = findFirstVisibleItemPosition;
        if (this.loading && (i4 = this.totalItemCount) > this.previousTotalItemCount) {
            this.loading = false;
            this.previousTotalItemCount = i4;
        }
        if (this.loading || this.totalItemCount - this.visibleItemCount > findFirstVisibleItemPosition + this.visibleThreshold) {
            return;
        }
        int i5 = this.currentPage + 1;
        this.currentPage = i5;
        onLoadMore(i5);
        this.loading = true;
    }

    void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.firstVisibleItem = 0;
        this.loading = true;
    }
}
