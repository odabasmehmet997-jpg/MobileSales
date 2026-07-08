package com.proje.mobilesales.core.searchdialog;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;

import java.util.ArrayList;
import java.util.List;



public class SearchDialogAdapter<T extends Searchable> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    private boolean mHighlightPartsInCommon;
    private List<T> mItems;
    private final int mLayout;
    private final LayoutInflater mLayoutInflater;
    private BaseSearchDialogCompat mSearchDialog;
    private SearchResultListener mSearchResultListener;
    private String mSearchTag;
    private AdapterViewBinder<T> mViewBinder;

    public interface AdapterViewBinder<T> {
        void bind(ViewHolder viewHolder, T t, int i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(final int i2) {
        return i2;
    }

    public SearchDialogAdapter(final Context context, @LayoutRes final int i2, final List<T> list) {
        this(context, i2, null, list);
    }

    public SearchDialogAdapter(final Context context, final AdapterViewBinder<T> adapterViewBinder, @LayoutRes final int i2, final List<T> list) {
        this(context, i2, adapterViewBinder, list);
    }

    public SearchDialogAdapter(final Context context, @LayoutRes final int i2, @Nullable final AdapterViewBinder<T> adapterViewBinder, final List<T> list) {
        mItems = new ArrayList();
        mHighlightPartsInCommon = true;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = list;
        mLayout = i2;
        mViewBinder = adapterViewBinder;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void setItems(final List<T> list) {
        mItems = list;
        this.notifyDataSetChanged();
    }

    public T getItem(final int i2) {
        return mItems.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        final List<T> list = mItems;
        if (null != list) {
            return list.size();
        }
        return 0;
    }

    public SearchDialogAdapter<T> setViewBinder(final AdapterViewBinder<T> adapterViewBinder) {
        mViewBinder = adapterViewBinder;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i2) {
        final View inflate = mLayoutInflater.inflate(mLayout, viewGroup, false);
        inflate.getLayoutParams().width = ((RecyclerView) viewGroup).getLayoutManager().getWidth();
        inflate.setTag(new ViewHolder(inflate));
        return (ViewHolder) inflate.getTag();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int i2) {
        this.initializeViews(this.getItem(i2), viewHolder, i2);
    }

    private void initializeViews(T t, final ViewHolder viewHolder, int i2) {
        final AdapterViewBinder<T> adapterViewBinder = mViewBinder;
        if (null != adapterViewBinder) {
            adapterViewBinder.bind(viewHolder, t, i2);
        }
        final AppCompatCheckedTextView appCompatCheckedTextView = viewHolder.getViewById(R.id.text1);
        appCompatCheckedTextView.setTextColor(this.getColor(R.color.searchDialogResultColor));
        appCompatCheckedTextView.setChecked(t.isChecked());
        if (null != this.mSearchTag && mHighlightPartsInCommon) {
            appCompatCheckedTextView.setText(StringsHelper.highlightLCS(t.getTitle(), this.mSearchTag, this.getColor(R.color.searchDialogResultHighlightColor)));
        } else {
            appCompatCheckedTextView.setText(t.getTitle());
        }
        if (null != this.mSearchResultListener) {
            viewHolder.getBaseView().setOnClickListener(new View.OnClickListener() { // from class: com.proje.mobilesales.core.searchdialog.SearchDialogAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    mSearchResultListener.onSelected(mSearchDialog, t, i2);
                }
            });
        }
    }

    public SearchResultListener getSearchResultListener() {
        return mSearchResultListener;
    }

    public void setSearchResultListener(final SearchResultListener searchResultListener) {
        mSearchResultListener = searchResultListener;
    }

    public String getSearchTag() {
        return mSearchTag;
    }

    public SearchDialogAdapter setSearchTag(final String str) {
        mSearchTag = str;
        return this;
    }

    public boolean isHighlightPartsInCommon() {
        return mHighlightPartsInCommon;
    }

    public SearchDialogAdapter setHighlightPartsInCommon(final boolean z) {
        mHighlightPartsInCommon = z;
        return this;
    }

    public SearchDialogAdapter setSearchDialog(final BaseSearchDialogCompat baseSearchDialogCompat) {
        mSearchDialog = baseSearchDialogCompat;
        return this;
    }

    private int getColor(@ColorRes final int i2) {
        return mContext.getResources().getColor(i2, null);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View mBaseView;

        public ViewHolder(final View view) {
            super(view);
            mBaseView = view;
        }

        public View getBaseView() {
            return mBaseView;
        }

        public <T> T getViewById(@IdRes final int i2) {
            return (T) mBaseView.findViewById(i2);
        }

        public void clearAnimation(@IdRes final int i2) {
            mBaseView.findViewById(i2).clearAnimation();
        }
    }
}
