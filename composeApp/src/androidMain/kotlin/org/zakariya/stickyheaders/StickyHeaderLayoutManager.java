package org.zakariya.stickyheaders;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class StickyHeaderLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "StickyHeaderLayoutManager";
    private SectioningAdapter adapter;
    private int firstViewAdapterPosition;
    private int firstViewTop;
    private SavedState pendingSavedState;
    private final HashSet<View> headerViews = new HashSet<>();
    private final HashMap<Integer, HeaderPosition> headerPositionsBySection = new HashMap<>();
    private int scrollTargetAdapterPosition = -1;
    public enum HeaderPosition {
        NONE,
        NATURAL,
        STICKY,
        TRAILING
    }
    public boolean canScrollVertically() {
        return true;
    }
    public void onAdapterChanged(final RecyclerView.Adapter adapter, final RecyclerView.Adapter adapter2) {
        super.onAdapterChanged(adapter, adapter2);
        try {
            this.adapter = (SectioningAdapter) adapter2;
            this.removeAllViews();
            headerViews.clear();
            headerPositionsBySection.clear();
        } catch (final ClassCastException unused) {
            throw new ClassCastException("StickyHeaderLayoutManager must be used with a RecyclerView where the adapter is a kind of SectioningAdapter");
        }
    }
    public void onAttachedToWindow(final RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        try {
            adapter = (SectioningAdapter) recyclerView.getAdapter();
        } catch (final ClassCastException unused) {
            throw new ClassCastException("StickyHeaderLayoutManager must be used with a RecyclerView where the adapter is a kind of SectioningAdapter");
        }
    }
    public void onDetachedFromWindow(final RecyclerView recyclerView, final RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        this.updateFirstAdapterPosition();
    }
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = pendingSavedState;
        if (null != savedState) {
            return savedState;
        }
        if (null != this.adapter) {
            this.updateFirstAdapterPosition();
        }
        final SavedState savedState2 = new SavedState();
        savedState2.firstViewAdapterPosition = firstViewAdapterPosition;
        savedState2.firstViewTop = firstViewTop;
        return savedState2;
    }
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (null == parcelable) {
            return;
        }
        if (parcelable instanceof SavedState) {
            pendingSavedState = (SavedState) parcelable;
            this.requestLayout();
            return;
        }
        Log.e(StickyHeaderLayoutManager.TAG, "onRestoreInstanceState: invalid saved state class, expected: " + SavedState.class.getCanonicalName() + " got: " + parcelable.getClass().getCanonicalName());
    }
    public void onLayoutChildren(final RecyclerView.Recycler recycler, final RecyclerView.State state) {
        View view;
        int decoratedMeasuredHeight;
        if (null == this.adapter) {
            return;
        }
        Log.i(StickyHeaderLayoutManager.TAG, "onLayoutChildren: getChildCount: " + this.getChildCount() + " adapter count: " + adapter.getItemCount());
        final int i2 = scrollTargetAdapterPosition;
        if (0 <= i2) {
            firstViewAdapterPosition = i2;
            firstViewTop = 0;
            scrollTargetAdapterPosition = -1;
        } else {
            final SavedState savedState = pendingSavedState;
            if (null != savedState && savedState.isValid()) {
                final SavedState savedState2 = pendingSavedState;
                firstViewAdapterPosition = savedState2.firstViewAdapterPosition;
                firstViewTop = savedState2.firstViewTop;
                pendingSavedState = null;
            } else {
                this.updateFirstAdapterPosition();
            }
        }
        final int i3 = firstViewTop;
        headerViews.clear();
        headerPositionsBySection.clear();
        this.detachAndScrapAttachedViews(recycler);
        final int paddingLeft = this.getPaddingLeft();
        final int width = this.getWidth() - this.getPaddingRight();
        final int height = this.getHeight() - this.getPaddingBottom();
        if (firstViewAdapterPosition >= state.getItemCount()) {
            firstViewAdapterPosition = state.getItemCount() - 1;
        }
        int i4 = i3;
        int i5 = firstViewAdapterPosition;
        int i6 = 0;
        while (i5 < state.getItemCount()) {
            final View viewForPosition = recycler.getViewForPosition(i5);
            this.addView(viewForPosition);
            this.measureChildWithMargins(viewForPosition, 0, 0);
            final int viewBaseType = this.getViewBaseType(viewForPosition);
            if (0 == viewBaseType) {
                headerViews.add(viewForPosition);
                decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(viewForPosition);
                final int i7 = i4 + decoratedMeasuredHeight;
                final int i8 = i4;
                view = viewForPosition;
                this.layoutDecorated(viewForPosition, paddingLeft, i8, width, i7);
                i5++;
                final View viewForPosition2 = recycler.getViewForPosition(i5);
                this.addView(viewForPosition2);
                this.layoutDecorated(viewForPosition2, paddingLeft, i8, width, i7);
            } else {
                view = viewForPosition;
                if (1 == viewBaseType) {
                    final View viewForPosition3 = recycler.getViewForPosition(i5 - 1);
                    headerViews.add(viewForPosition3);
                    this.addView(viewForPosition3);
                    this.measureChildWithMargins(viewForPosition3, 0, 0);
                    decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(viewForPosition3);
                    final int i9 = i4 + decoratedMeasuredHeight;
                    final int i10 = i4;
                    this.layoutDecorated(viewForPosition3, paddingLeft, i10, width, i9);
                    this.layoutDecorated(view, paddingLeft, i10, width, i9);
                } else {
                    decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(view);
                    this.layoutDecorated(view, paddingLeft, i4, width, i4 + decoratedMeasuredHeight);
                }
            }
            i4 += decoratedMeasuredHeight;
            i6 += decoratedMeasuredHeight;
            if (view.getBottom() >= height) {
                break;
            } else {
                i5++;
            }
        }
        final int i11 = i6;
        final int height2 = this.getHeight() - (this.getPaddingTop() + this.getPaddingBottom());
        if (i11 < height2) {
            this.scrollVerticallyBy(i11 - height2, recycler, null);
        } else {
            this.updateHeaderPositions(recycler);
        }
    }
    private View createSectionHeaderIfNeeded(final RecyclerView.Recycler recycler, final int i2) {
        if (adapter.doesSectionHaveHeader(i2)) {
            final int childCount = this.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                final View childAt = this.getChildAt(i3);
                if (0 == getViewBaseType(childAt) && this.getViewSectionIndex(childAt) == i2) {
                    return childAt;
                }
            }
            final View viewForPosition = recycler.getViewForPosition(adapter.getAdapterPositionForSectionHeader(i2));
            headerViews.add(viewForPosition);
            this.addView(viewForPosition);
            this.measureChildWithMargins(viewForPosition, 0, 0);
            return viewForPosition;
        }
        throw new IllegalStateException("createSectionHeaderIfNeeded should not be called for a section which does not have a header");
    }
    public int scrollVerticallyBy(final int i2, final RecyclerView.Recycler recycler, final RecyclerView.State state) {
        int i3;
        View viewForPosition;
        final int decoratedMeasuredHeight;
        if (0 == getChildCount()) {
            return 0;
        }
        final int paddingLeft = this.getPaddingLeft();
        final int width = this.getWidth() - this.getPaddingRight();
        int i4 = 1;
        if (0 > i2) {
            View topmostChildView = this.getTopmostChildView();
            if (null != topmostChildView) {
                i3 = 0;
                while (i3 > i2) {
                    final int min = Math.min(i3 - i2, Math.max(-this.getDecoratedTop(topmostChildView), 0));
                    final int i5 = i3 - min;
                    this.offsetChildrenVertical(min);
                    final int i6 = firstViewAdapterPosition;
                    if (0 < i6 && i5 > i2) {
                        final int i7 = i6 - 1;
                        firstViewAdapterPosition = i7;
                        int itemViewBaseType = adapter.getItemViewBaseType(i7);
                        if (0 == itemViewBaseType) {
                            final int i8 = firstViewAdapterPosition - 1;
                            firstViewAdapterPosition = i8;
                            if (0 <= i8) {
                                itemViewBaseType = adapter.getItemViewBaseType(i8);
                                if (0 == itemViewBaseType) {
                                }
                            }
                        }
                        final View viewForPosition2 = recycler.getViewForPosition(firstViewAdapterPosition);
                        this.addView(viewForPosition2, 0);
                        final int decoratedTop = this.getDecoratedTop(topmostChildView);
                        if (1 == itemViewBaseType) {
                            decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(this.createSectionHeaderIfNeeded(recycler, adapter.getSectionForAdapterPosition(firstViewAdapterPosition)));
                        } else {
                            this.measureChildWithMargins(viewForPosition2, 0, 0);
                            decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(viewForPosition2);
                        }
                        this.layoutDecorated(viewForPosition2, paddingLeft, decoratedTop - decoratedMeasuredHeight, width, decoratedTop);
                        i3 = i5;
                        topmostChildView = viewForPosition2;
                    }
                    i3 = i5;
                    break;
                }
            } else {
                return 0;
            }
        } else {
            final int height = this.getHeight();
            View bottommostChildView = this.getBottommostChildView();
            if (null == bottommostChildView) {
                return 0;
            }
            i3 = 0;
            while (i3 < i2) {
                final int i9 = -Math.min(i2 - i3, Math.max(this.getDecoratedBottom(bottommostChildView) - height, 0));
                final int i10 = i3 - i9;
                this.offsetChildrenVertical(i9);
                final int viewAdapterPosition = this.getViewAdapterPosition(bottommostChildView);
                final int i11 = viewAdapterPosition + 1;
                if (i10 >= i2 || i11 >= state.getItemCount()) {
                    i3 = i10;
                    break;
                }
                final int decoratedBottom = this.getDecoratedBottom(bottommostChildView);
                final int itemViewBaseType2 = adapter.getItemViewBaseType(i11);
                if (0 == itemViewBaseType2) {
                    final View createSectionHeaderIfNeeded = this.createSectionHeaderIfNeeded(recycler, adapter.getSectionForAdapterPosition(i11));
                    final int decoratedMeasuredHeight2 = this.getDecoratedMeasuredHeight(createSectionHeaderIfNeeded);
                    this.layoutDecorated(createSectionHeaderIfNeeded, paddingLeft, 0, width, decoratedMeasuredHeight2);
                    final View viewForPosition3 = recycler.getViewForPosition(viewAdapterPosition + 2);
                    this.addView(viewForPosition3);
                    this.layoutDecorated(viewForPosition3, paddingLeft, decoratedBottom, width, decoratedBottom + decoratedMeasuredHeight2);
                    bottommostChildView = viewForPosition3;
                } else {
                    if (itemViewBaseType2 == i4) {
                        final View createSectionHeaderIfNeeded2 = this.createSectionHeaderIfNeeded(recycler, adapter.getSectionForAdapterPosition(i11));
                        final int decoratedMeasuredHeight3 = this.getDecoratedMeasuredHeight(createSectionHeaderIfNeeded2);
                        this.layoutDecorated(createSectionHeaderIfNeeded2, paddingLeft, 0, width, decoratedMeasuredHeight3);
                        viewForPosition = recycler.getViewForPosition(i11);
                        this.addView(viewForPosition);
                        this.layoutDecorated(viewForPosition, paddingLeft, decoratedBottom, width, decoratedBottom + decoratedMeasuredHeight3);
                    } else {
                        viewForPosition = recycler.getViewForPosition(i11);
                        this.addView(viewForPosition);
                        this.measureChildWithMargins(viewForPosition, 0, 0);
                        this.layoutDecorated(viewForPosition, paddingLeft, decoratedBottom, width, decoratedBottom + this.getDecoratedMeasuredHeight(viewForPosition));
                    }
                    bottommostChildView = viewForPosition;
                }
                i3 = i10;
                i4 = 1;
            }
        }
        final View topmostChildView2 = this.getTopmostChildView();
        if (null != topmostChildView2) {
            firstViewTop = this.getDecoratedTop(topmostChildView2);
        }
        this.updateHeaderPositions(recycler);
        this.recycleViewsOutOfBounds(recycler);
        return i3;
    }
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-1, -2);
    }
    public void scrollToPosition(final int i2) {
        if (0 > i2 || i2 > this.getItemCount()) {
            throw new IndexOutOfBoundsException("adapter position out of range");
        }
        scrollTargetAdapterPosition = i2;
        pendingSavedState = null;
        this.requestLayout();
    }
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView.State state, final int i2) {
        if (0 > i2 || i2 > this.getItemCount()) {
            throw new IndexOutOfBoundsException("adapter position out of range");
        }
        pendingSavedState = null;
        final View childAt = recyclerView.getChildAt(0);
        int abs = Math.abs((recyclerView.getChildAdapterPosition(childAt) - i2) * this.getEstimatedItemHeightForSmoothScroll(recyclerView));
        if (0 == abs) {
            abs = (int) Math.abs(childAt.getY());
        }
        final SmoothScroller smoothScroller = new SmoothScroller(recyclerView.getContext(), abs);
        smoothScroller.setTargetPosition(i2);
        this.startSmoothScroll(smoothScroller);
    }
    private int getEstimatedItemHeightForSmoothScroll(final RecyclerView recyclerView) {
        final int childCount = recyclerView.getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            i2 = Math.max(this.getDecoratedMeasuredHeight(recyclerView.getChildAt(i3)), i2);
        }
        return i2;
    }
    public int computeScrollVectorForPosition(final int i2) {
        this.updateFirstAdapterPosition();
        final int i3 = firstViewAdapterPosition;
        if (i2 > i3) {
            return 1;
        }
        return i2 < i3 ? -1 : 0;
    }
    private void recycleViewsOutOfBounds(final RecyclerView.Recycler recycler) {
        final int height = this.getHeight();
        final int childCount = this.getChildCount();
        final HashSet hashSet = new HashSet();
        final HashSet hashSet2 = new HashSet();
        for (int i2 = 0; i2 < childCount; i2++) {
            final View childAt = this.getChildAt(i2);
            if (!this.isViewRecycled(childAt) && 0 != getViewBaseType(childAt)) {
                if (0 > getDecoratedBottom(childAt) || this.getDecoratedTop(childAt) > height) {
                    hashSet2.add(childAt);
                } else {
                    hashSet.add(Integer.valueOf(this.getViewSectionIndex(childAt)));
                }
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            final View childAt2 = this.getChildAt(i3);
            if (!this.isViewRecycled(childAt2)) {
                final int viewSectionIndex = this.getViewSectionIndex(childAt2);
                if (0 == getViewBaseType(childAt2) && !hashSet.contains(Integer.valueOf(viewSectionIndex))) {
                    final float translationY = childAt2.getTranslationY();
                    if (0.0f > getDecoratedBottom(childAt2) + translationY || this.getDecoratedTop(childAt2) + translationY > height) {
                        hashSet2.add(childAt2);
                        headerViews.remove(childAt2);
                        headerPositionsBySection.remove(Integer.valueOf(viewSectionIndex));
                    }
                }
            }
        }
        final Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            this.removeAndRecycleView((View) it.next(), recycler);
        }
        this.updateFirstAdapterPosition();
    }
    private View getTopmostChildView() {
        int decoratedTop;
        View view = null;
        if (0 == getChildCount()) {
            return null;
        }
        final int childCount = this.getChildCount();
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            final View childAt = this.getChildAt(i3);
            if (-1 != getViewAdapterPosition(childAt) && 0 != getViewBaseType(childAt) && (decoratedTop = this.getDecoratedTop(childAt)) < i2) {
                view = childAt;
                i2 = decoratedTop;
            }
        }
        return view;
    }
    View getBottommostChildView() {
        int decoratedBottom;
        View view = null;
        if (0 == getChildCount()) {
            return null;
        }
        final int childCount = this.getChildCount();
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            final View childAt = this.getChildAt(i3);
            if (-1 != getViewAdapterPosition(childAt) && 0 != getViewBaseType(childAt) && (decoratedBottom = this.getDecoratedBottom(childAt)) > i2) {
                view = childAt;
                i2 = decoratedBottom;
            }
        }
        return view;
    }
    private int updateFirstAdapterPosition() {
        if (0 == getChildCount()) {
            firstViewAdapterPosition = 0;
            final int paddingTop = this.getPaddingTop();
            firstViewTop = paddingTop;
            return paddingTop;
        }
        final View topmostChildView = this.getTopmostChildView();
        if (null != topmostChildView) {
            firstViewAdapterPosition = this.getViewAdapterPosition(topmostChildView);
            final int min = Math.min(topmostChildView.getTop(), this.getPaddingTop());
            firstViewTop = min;
            return min;
        }
        return firstViewTop;
    }
    private void updateHeaderPositions(final RecyclerView.Recycler recycler) {
        int decoratedTop;
        int decoratedTop2;
        int viewBaseType;
        final HashSet hashSet = new HashSet();
        final int childCount = this.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            final int viewSectionIndex = this.getViewSectionIndex(this.getChildAt(i2));
            if (hashSet.add(Integer.valueOf(viewSectionIndex)) && adapter.doesSectionHaveHeader(viewSectionIndex)) {
                this.createSectionHeaderIfNeeded(recycler, viewSectionIndex);
            }
        }
        final int paddingLeft = this.getPaddingLeft();
        final int width = this.getWidth() - this.getPaddingRight();
        final Iterator<View> it = headerViews.iterator();
        while (it.hasNext()) {
            final View next = it.next();
            final int viewSectionIndex2 = this.getViewSectionIndex(next);
            final int childCount2 = this.getChildCount();
            View view = null;
            View view2 = null;
            for (int i3 = 0; i3 < childCount2; i3++) {
                final View childAt = this.getChildAt(i3);
                if (!this.isViewRecycled(childAt) && 0 != (viewBaseType = getViewBaseType(childAt))) {
                    final int viewSectionIndex3 = this.getViewSectionIndex(childAt);
                    if (viewSectionIndex3 == viewSectionIndex2) {
                        if (1 == viewBaseType) {
                            view = childAt;
                        }
                    } else if (viewSectionIndex3 == viewSectionIndex2 + 1 && null == view2) {
                        view2 = childAt;
                    }
                }
            }
            final int decoratedMeasuredHeight = this.getDecoratedMeasuredHeight(next);
            int paddingTop = this.getPaddingTop();
            HeaderPosition headerPosition = HeaderPosition.STICKY;
            if (null != view && (decoratedTop2 = this.getDecoratedTop(view)) >= paddingTop) {
                headerPosition = HeaderPosition.NATURAL;
                paddingTop = decoratedTop2;
            }
            if (null != view2 && (decoratedTop = this.getDecoratedTop(view2) - decoratedMeasuredHeight) < paddingTop) {
                headerPosition = HeaderPosition.TRAILING;
                paddingTop = decoratedTop;
            }
            next.bringToFront();
            this.layoutDecorated(next, paddingLeft, paddingTop, width, paddingTop + decoratedMeasuredHeight);
            this.recordHeaderPositionAndNotify(viewSectionIndex2, next, headerPosition);
        }
    }
    private void recordHeaderPositionAndNotify(final int i2, final View view, final HeaderPosition headerPosition) {
        if (headerPositionsBySection.containsKey(Integer.valueOf(i2))) {
            if (headerPositionsBySection.get(Integer.valueOf(i2)) != headerPosition) {
                headerPositionsBySection.put(Integer.valueOf(i2), headerPosition);
                return;
            }
            return;
        }
        headerPositionsBySection.put(Integer.valueOf(i2), headerPosition);
    }
    private boolean isViewRecycled(final View view) {
        return -1 == getViewAdapterPosition(view);
    }
    private int getViewBaseType(final View view) {
        return adapter.getItemViewBaseType(this.getViewAdapterPosition(view));
    }
    private int getViewSectionIndex(final View view) {
        return adapter.getSectionForAdapterPosition(this.getViewAdapterPosition(view));
    }
    private SectioningAdapter.ViewHolder getViewViewHolder(final View view) {
        return (SectioningAdapter.ViewHolder) view.getTag(id.sectioning_adapter_tag_key_view_viewholder);
    }
    int getViewAdapterPosition(final View view) {
        return this.getViewViewHolder(view).getAdapterPosition();
    }
    private class SmoothScroller extends LinearSmoothScroller {
        private final float distanceInPixels;
        private final float duration;

        SmoothScroller(final Context context, final int i2) {
            super(context);
            distanceInPixels = i2;
            duration = 10000 > i2 ? (int) (Math.abs(i2) * this.calculateSpeedPerPixel(context.getResources().getDisplayMetrics())) : 1000.0f;
        }
        public PointF computeScrollVectorForPosition(final int i2) {
            return new PointF(0.0f, StickyHeaderLayoutManager.this.computeScrollVectorForPosition(i2));
        }
        protected int calculateTimeForScrolling(final int i2) {
            return (int) (duration * (i2 / distanceInPixels));
        }
    }
    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: org.zakariya.stickyheaders.StickyHeaderLayoutManager.SavedState.1
            public SavedState createFromParcel(final Parcel parcel) {
                return new SavedState(parcel);
            }
            public SavedState[] newArray(final int i2) {
                return new SavedState[i2];
            }
        };
        int firstViewAdapterPosition;
        int firstViewTop;
        public int describeContents() {
            return 0;
        }

        SavedState() {
            firstViewAdapterPosition = -1;
            firstViewTop = 0;
        }

        SavedState(final Parcel parcel) {
            firstViewAdapterPosition = -1;
            firstViewTop = 0;
            firstViewAdapterPosition = parcel.readInt();
            firstViewTop = parcel.readInt();
        }
        boolean isValid() {
            return 0 <= this.firstViewAdapterPosition;
        }
        public String toString() {
            return "<" + this.getClass().getCanonicalName() + " firstViewAdapterPosition: " + firstViewAdapterPosition + " firstViewTop: " + firstViewTop + ">";
        }
        public void writeToParcel(final Parcel parcel, final int i2) {
            parcel.writeInt(firstViewAdapterPosition);
            parcel.writeInt(firstViewTop);
        }
    }
}
