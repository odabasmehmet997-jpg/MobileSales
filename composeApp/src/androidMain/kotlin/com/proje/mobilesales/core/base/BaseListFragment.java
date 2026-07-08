package com.proje.mobilesales.core.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View; 
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.FragmentBackHandler;
import com.proje.mobilesales.core.interfaces.Scrollable;
import com.proje.mobilesales.core.utils.KeyDelegate;
import com.proje.mobilesales.core.view.SnappyLinearLayoutManager;
import com.proje.mobilesales.features.adapter.IListRecyclerView;

public abstract class BaseListFragment extends BaseFragment implements Scrollable, FragmentBackHandler {
    private static final String STATE_ADAPTER = "state:adapter";
    protected RecyclerView mRecyclerView;
    private KeyDelegate.RecyclerViewHelper mScrollableHelper;
    protected abstract IListRecyclerView getAdapter();
    public boolean onBackPressedFragment() {
        return false;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public void onCreate( Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        if (bundle != null) {
            getAdapter().restoreState(bundle.getBundle("state:adapter"));
        } else {
            getAdapter().setCardViewEnabled(true);
        }
    }
    public void onViewCreated(View view,   Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView.setLayoutManager(new SnappyLinearLayoutManager(getActivity()));
        this.mRecyclerView.setHasFixedSize(true);
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.cardview_vertical_margin);
        final int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.cardview_horizontal_margin);
        final int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.divider);
        this.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.proje.mobilesales.core.base.BaseListFragment.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view2, RecyclerView recyclerView, RecyclerView.State state) {
                if (BaseListFragment.this.getAdapter().isCardViewEnabled()) {
                    int i2 = dimensionPixelSize2;
                    rect.set(i2, dimensionPixelSize, i2, 0);
                } else {
                    rect.set(0, 0, 0, dimensionPixelSize3);
                }
            }
        });
    }
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mRecyclerView.setAdapter((RecyclerView.Adapter) getAdapter());
        this.mScrollableHelper = new KeyDelegate.RecyclerViewHelper(this.mRecyclerView, 1);
    }
    protected void createOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.createOptionsMenu(menu, menuInflater);
    }
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (getAdapter() != null) {
            bundle.putBundle("state:adapter", getAdapter().saveState());
        }
    }
    public void onDetach() {
        super.onDetach();
    }
    public void scrollToTop() {
        this.mScrollableHelper.scrollToTop();
    }
    public boolean scrollToPrevious() {
        return this.mScrollableHelper.scrollToPrevious();
    }
    public boolean scrollToNext() {
        return this.mScrollableHelper.scrollToNext();
    }
}
