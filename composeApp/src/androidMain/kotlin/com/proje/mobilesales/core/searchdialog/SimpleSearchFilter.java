package com.proje.mobilesales.core.searchdialog;

import android.util.Pair;
import android.widget.Filter;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;



public class SimpleSearchFilter<T extends Searchable> extends BaseFilter {
    private final float mAccuracyPercentage;
    private final boolean mCheckLCS;
    private final Comparator<Pair<T, Integer>> mComparator;
    private final FilterResultListener mFilterResultListener;
    private final ArrayList<T> mItems;

    public SimpleSearchFilter(final List<T> list, @NonNull final FilterResultListener filterResultListener, final boolean z, final float f2) {
        mComparator = new Comparator<Pair<T, Integer>>() { // from class: com.proje.mobilesales.core.searchdialog.SimpleSearchFilter.1
            @Override // java.util.Comparator
            public int compare(final Pair<T, Integer> pair, final Pair<T, Integer> pair2) {
                final Object obj;
                if (null == pair || null == pair2 || null == pair.second || null == (obj = pair2.second)) {
                    return 0;
                }
                return ((Integer) obj).intValue() - pair.second.intValue();
            }
        };
        mFilterResultListener = filterResultListener;
        mCheckLCS = z;
        mAccuracyPercentage = f2;
        mItems = new ArrayList<>();
        synchronized (this) {
            mItems.addAll(list);
        }
    }

    public SimpleSearchFilter(final List<T> list, @NonNull final FilterResultListener filterResultListener) {
        this(list, filterResultListener, false, 0.0f);
    }

    @Override // android.widget.Filter
    protected Filter.FilterResults performFiltering(final CharSequence charSequence) {
        this.doBeforeFiltering();
        final String lowerCase = charSequence.toString().toLowerCase();
        final Filter.FilterResults filterResults = new Filter.FilterResults();
        if (0 >= lowerCase.length()) {
            final ArrayList<T> arrayList = mItems;
            filterResults.values = arrayList;
            filterResults.count = arrayList.size();
            return filterResults;
        }
        final ArrayList arrayList2 = new ArrayList();
        final Iterator<T> it = mItems.iterator();
        while (it.hasNext()) {
            final T next = it.next();
            if (next.getTitle().toLowerCase().contains(lowerCase)) {
                arrayList2.add(new Pair(next, Integer.valueOf(lowerCase.length())));
            } else if (mCheckLCS) {
                final int length = StringsHelper.lcs(next.getTitle(), lowerCase).length();
                if (length > next.getTitle().length() * mAccuracyPercentage) {
                    arrayList2.add(new Pair(next, Integer.valueOf(length)));
                }
            }
        }
        Collections.sort(arrayList2, mComparator);
        final ArrayList arrayList3 = new ArrayList();
        final Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(((Pair) it2.next()).first);
        }
        filterResults.values = arrayList3;
        filterResults.count = arrayList3.size();
        return filterResults;
    }

    @Override // android.widget.Filter
    protected void publishResults(final CharSequence charSequence, final Filter.FilterResults filterResults) {
        mFilterResultListener.onFilter((ArrayList) filterResults.values);
        this.doAfterFiltering();
    }
}
