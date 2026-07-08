package com.proje.mobilesales.core.searchdialog;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;

import java.util.ArrayList;

public class SimpleSearchDialogCompat<T extends Searchable> extends BaseSearchDialogCompat<T> {
    private AppCompatButton mCancelButton;
    private boolean mCancelOnTouchOutside;
    private Handler mHandler;
    private boolean mIsEnabledCancelButton;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private EditText mSearchBox;
    private String mSearchHint;
    private SearchResultListener<T> mSearchResultListener;
    private String mTitle;
    private TextView mTxtTitle;

    protected int getLayoutResId() {
        return R.layout.search_dialog_compat;
    }
    protected int getRecyclerViewId() {
        return R.id.rv_items;
    }
    protected int getSearchBoxId() {
        return R.id.txt_search;
    }

    public SimpleSearchDialogCompat(final Context context, final String str, final String str2, @Nullable final Filter filter, final ArrayList<T> arrayList, final SearchResultListener<T> searchResultListener) {
        super(context, arrayList, filter, null, null);
        mCancelOnTouchOutside = true;
        mIsEnabledCancelButton = true;
        this.init(str, str2, searchResultListener);
    }

    public SimpleSearchDialogCompat(final Context context, final String str, final String str2, @Nullable final Filter filter, final ArrayList<T> arrayList, final SearchResultListener<T> searchResultListener, final Boolean bool) {
        super(context, arrayList, filter, null, null);
        mCancelOnTouchOutside = true;
        mIsEnabledCancelButton = true;
        mIsEnabledCancelButton = bool.booleanValue();
        this.init(str, str2, searchResultListener);
    }

    private void init(final String str, final String str2, final SearchResultListener<T> searchResultListener) {
        mTitle = str;
        mSearchHint = str2;
        mSearchResultListener = searchResultListener;
        this.setFilterResultListener(new FilterResultListener<T>() {
            public void onFilter(final ArrayList<T> arrayList) {
                ((SearchDialogAdapter) getAdapter()).setSearchTag(mSearchBox.getText().toString()).setItems(arrayList);
            }
        });
        mHandler = new Handler();
    }

    protected void getView(final View view) {
        this.setContentView(view);
        this.setCancelable(mIsEnabledCancelButton);
        this.setCanceledOnTouchOutside(mIsEnabledCancelButton);
        mTxtTitle = view.findViewById(R.id.txt_title);
        mSearchBox = view.findViewById(this.getSearchBoxId());
        mRecyclerView = view.findViewById(this.getRecyclerViewId());
        mProgressBar = view.findViewById(R.id.progress);
        mCancelButton = view.findViewById(R.id.no);
        mTxtTitle.setText(mTitle);
        mSearchBox.setHint(mSearchHint);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(8);
        final SearchDialogAdapter searchDialogAdapter = new SearchDialogAdapter(this.getContext(), R.layout.simple_list_item_single_choice, this.getItems());
        searchDialogAdapter.setHighlightPartsInCommon(false);
        searchDialogAdapter.setSearchResultListener(mSearchResultListener);
        searchDialogAdapter.setSearchDialog(this);
        this.setFilterResultListener(this.getFilterResultListener());
        this.setAdapter(searchDialogAdapter);
        mSearchBox.requestFocus();
        ((BaseFilter) this.getFilter()).setOnPerformFilterListener(new OnPerformFilterListener() {
            public void doAfterFiltering() {
            }
            public void doBeforeFiltering() {
            }
        });
        if (mIsEnabledCancelButton) {
            mCancelButton.setVisibility(View.VISIBLE);
            mCancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view2) {
                    lambdagetView0(view2);
                }
            });
        } else {
            mCancelButton.setVisibility(8);
        }
    }

    public void lambdagetView0(final View view) {
        mSearchResultListener.onCancelled(this);
    }

    public SimpleSearchDialogCompat setSearchHint(final String str) {
        mSearchHint = str;
        if (null != this.mSearchBox) {
            mHandler.post(new Runnable() {
                public void run() {
                    mSearchBox.setHint(mSearchHint);
                }
            });
        }
        return this;
    }

    public void setLoading(boolean z) {
        mHandler.post(new Runnable() {
            public void run() {
                if (null != SimpleSearchDialogCompat.this.mProgressBar) {
                    mRecyclerView.setVisibility(!z ? View.VISIBLE : View.GONE);
                }
                if (null != SimpleSearchDialogCompat.this.mRecyclerView) {
                    mProgressBar.setVisibility(z ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    public SimpleSearchDialogCompat setSearchResultListener(final SearchResultListener<T> searchResultListener) {
        mSearchResultListener = searchResultListener;
        return this;
    }

    public EditText getSearchBox() {
        return mSearchBox;
    }

    public String getTitle() {
        return mTitle;
    }

    public SimpleSearchDialogCompat setTitle(final String str) {
        mTitle = str;
        if (null != this.mTxtTitle) {
            mHandler.post(new Runnable() {
                public void run() {
                    mTxtTitle.setText(mTitle);
                }
            });
        }
        return this;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    public TextView getTitleTextView() {
        return mTxtTitle;
    }

    public boolean willCancelOnTouchOutside() {
        return mCancelOnTouchOutside;
    }

    public void setCancelOnTouchOutside(final boolean z) {
        mCancelOnTouchOutside = z;
    }
}
