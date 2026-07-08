package com.proje.mobilesales.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;
public class AnimatedExpandableListView extends ExpandableListView {
    private AnimatedExpandableListAdapter adapter = null;
    public int getAnimationDuration() {
        return 300;
    }
    public AnimatedExpandableListView(Context context) {
        super(context);
    }
    public AnimatedExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public AnimatedExpandableListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        super.setAdapter(expandableListAdapter);
        if (expandableListAdapter instanceof AnimatedExpandableListAdapter animatedExpandableListAdapter) {
            this.adapter = animatedExpandableListAdapter;
            animatedExpandableListAdapter.setParent(this);
        } else {
            throw new ClassCastException(expandableListAdapter.toString() + " must implement AnimatedExpandableListAdapter");
        }
    }
    public void expandGroupWithAnimation(int i2) {
        int firstVisiblePosition;
        if (i2 == this.adapter.getGroupCount() - 1 && i2 > 0) {
            expandGroup(i2, true);
            return;
        }
        int flatListPosition = getFlatListPosition(ExpandableListView.getPackedPositionForGroup(i2));
        if (flatListPosition != -1 && (firstVisiblePosition = flatListPosition - getFirstVisiblePosition()) < getChildCount() && getChildAt(firstVisiblePosition).getBottom() >= getBottom()) {
            this.adapter.notifyGroupExpanded(i2);
            expandGroup(i2);
            return;
        }
        this.adapter.startExpandAnimation(i2, 0);
        expandGroup(i2);
    }
    public void collapseGroupWithAnimation(int i2) {
        int flatListPosition = getFlatListPosition(ExpandableListView.getPackedPositionForGroup(i2));
        if (flatListPosition != -1) {
            int firstVisiblePosition = flatListPosition - getFirstVisiblePosition();
            if (firstVisiblePosition >= 0 && firstVisiblePosition < getChildCount()) {
                if (getChildAt(firstVisiblePosition).getBottom() >= getBottom()) {
                    collapseGroup(i2);
                    return;
                }
            } else {
                collapseGroup(i2);
                return;
            }
        }
        long expandableListPosition = getExpandableListPosition(getFirstVisiblePosition());
        int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
        if (packedPositionChild == -1 || packedPositionGroup != i2) {
            packedPositionChild = 0;
        }
        this.adapter.startCollapseAnimation(i2, packedPositionChild);
        this.adapter.notifyDataSetChanged();
        isGroupExpanded(i2);
    }
    private static class GroupInfo {
        boolean animating;
        int dummyHeight;
        boolean expanding;
        int firstChildPosition;

        private GroupInfo() {
            this.animating = false;
            this.expanding = false;
            this.dummyHeight = -1;
        }
    }
    public static abstract class AnimatedExpandableListAdapter extends BaseExpandableListAdapter {
        private static final int STATE_COLLAPSING = 2;
        private static final int STATE_EXPANDING = 1;
        private static final int STATE_IDLE = 0;
        private final SparseArray<GroupInfo> groupInfo = new SparseArray<>();
        private AnimatedExpandableListView parent;
        public int getRealChildType() {
            return getRealChildType(0, 0);
        }
        public int getRealChildType(int groupPosition, int childPosition) {
            return 0;
        }
        public int getRealChildTypeCount() {
            return 1;
        }
        public abstract View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent);
        public abstract int getRealChildrenCount(int groupPosition);
        public void setParent(AnimatedExpandableListView animatedExpandableListView) {
            this.parent = animatedExpandableListView;
        }
        private GroupInfo getGroupInfo(int groupPosition) {
            GroupInfo groupInfo = this.groupInfo.get(groupPosition);
            if (groupInfo != null) {
                return groupInfo;
            }
            GroupInfo groupInfo2 = new GroupInfo();
            this.groupInfo.put(groupPosition, groupInfo2);
            return groupInfo2;
        }
        public void notifyGroupExpanded(int groupPosition) {
            getGroupInfo(groupPosition).dummyHeight = -1;
        }
        public void startExpandAnimation(int groupPosition, int firstChildPosition) {
            GroupInfo groupInfo = getGroupInfo(groupPosition);
            groupInfo.animating = true;
            groupInfo.firstChildPosition = firstChildPosition;
            groupInfo.expanding = true;
        }
        public void startCollapseAnimation(int groupPosition, int firstChildPosition) {
            GroupInfo groupInfo = getGroupInfo(groupPosition);
            groupInfo.animating = true;
            groupInfo.firstChildPosition = firstChildPosition;
            groupInfo.expanding = false;
        }
        public void stopAnimation(int groupPosition) {
            getGroupInfo(groupPosition).animating = false;
        }
        public final int getChildType(int groupPosition, int childPosition) {
            if (getGroupInfo(groupPosition).animating) {
                return 0;
            }
            return getRealChildType(groupPosition, childPosition) + 1;
        }
        public final int getChildTypeCount() {
            return getRealChildTypeCount() + 1;
        }

        protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(-1, -2, 0);
        }
        public final View getChildView(final int i2, int i3, boolean z, View view, ViewGroup viewGroup) {
            int i4;
            int i5;
            final GroupInfo groupInfo = getGroupInfo(i2);
            if (groupInfo.animating) {
                View view2 = view;
                boolean z2 = false;
                if (!(view2 instanceof DummyView)) {
                    view2 = new DummyView(viewGroup.getContext());
                    view2.setLayoutParams(new LayoutParams(-1, 0));
                }
                View view3 = view2;
                if (i3 < groupInfo.firstChildPosition) {
                    view3.getLayoutParams().height = 0;
                    return view3;
                }
                final ExpandableListView expandableListView = (ExpandableListView) viewGroup;
                final DummyView dummyView = (DummyView) view3;
                dummyView.clearViews();
                dummyView.setDivider(expandableListView.getDivider(), viewGroup.getMeasuredWidth(), expandableListView.getDividerHeight());
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), MeasureSpec.EXACTLY);
                int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                int height = viewGroup.getHeight();
                int realChildrenCount = getRealChildrenCount(i2);
                int i6 = groupInfo.firstChildPosition;
                int i7 = 0;
                while (true) {
                    if (i6 >= realChildrenCount) {
                        i4 = 1;
                        i5 = i7;
                        break;
                    }
                    boolean z3 = i6 == realChildrenCount - 1;
                    i4 = 1;
                    int i8 = i6;
                    int i9 = i6;
                    boolean z4 = z3;
                    View realChildView = getRealChildView(i2, i8, z4, null, viewGroup);
                    LayoutParams layoutParams = (LayoutParams) realChildView.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = generateDefaultLayoutParams();
                        realChildView.setLayoutParams(layoutParams);
                    }
                    int i12 = layoutParams.height;
                    realChildView.measure(makeMeasureSpec, i12 > 0 ? MeasureSpec.makeMeasureSpec(i12, MeasureSpec.EXACTLY) : makeMeasureSpec2);
                    int measuredHeight = i7 + realChildView.getMeasuredHeight();
                    int i11 = height;
                    if (measuredHeight < i11) {
                        dummyView.addFakeView(realChildView);
                        i6 = i9 + 1;
                        i7 = measuredHeight;
                    } else {
                        dummyView.addFakeView(realChildView);
                        int i10 = realChildrenCount;
                        i5 = measuredHeight + (((i10 - i9) - 1) * (measuredHeight / (i9 + 1)));
                        break;
                    }
                }
                Object tag = dummyView.getTag();
                int intValue = tag == null ? 0 : (Integer) tag;
                boolean z5 = groupInfo.expanding;
                if (z5 && intValue != i4) {
                    ExpandAnimation expandAnimation = new ExpandAnimation(dummyView, 0, i5, groupInfo);
                    expandAnimation.setDuration(this.parent.getAnimationDuration());
                    expandAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }
                        public void onAnimationStart(Animation animation) {
                        }
                        public void onAnimationEnd(Animation animation) {
                            AnimatedExpandableListAdapter.this.stopAnimation(i2);
                            AnimatedExpandableListAdapter.this.notifyDataSetChanged();
                            dummyView.setTag(0);
                        }
                    });
                    dummyView.startAnimation(expandAnimation);
                    dummyView.setTag(i4);
                } else if (!z5 && intValue != 2) {
                    if (groupInfo.dummyHeight == -1) {
                        groupInfo.dummyHeight = i5;
                    }
                    ExpandAnimation expandAnimation2 = new ExpandAnimation(dummyView, groupInfo.dummyHeight, 0, groupInfo);
                    expandAnimation2.setDuration(this.parent.getAnimationDuration());
                    expandAnimation2.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }
                        public void onAnimationStart(Animation animation) {
                        }
                        public void onAnimationEnd(Animation animation) {
                            AnimatedExpandableListAdapter.this.stopAnimation(i2);
                            expandableListView.collapseGroup(i2);
                            AnimatedExpandableListAdapter.this.notifyDataSetChanged();
                            groupInfo.dummyHeight = -1;
                            dummyView.setTag(0);
                        }
                    });
                    dummyView.startAnimation(expandAnimation2);
                    dummyView.setTag(2);
                }
                return view3;
            }
            return getRealChildView(i2, i3, z, view, viewGroup);
        }
        public final int getChildrenCount(int i2) {
            GroupInfo groupInfo = getGroupInfo(i2);
            if (groupInfo.animating) {
                return groupInfo.firstChildPosition + 1;
            }
            return getRealChildrenCount(i2);
        }
    }
    private static class DummyView extends View {
        private Drawable divider;
        private int dividerHeight;
        private int dividerWidth;
        private final List<View> views = new ArrayList<>();
        public DummyView(Context context) {
            super(context);
        }
        public void setDivider(Drawable drawable, int dividerWidth, int dividerHeight) {
            if (drawable != null) {
                this.divider = drawable;
                this.dividerWidth = dividerWidth;
                this.dividerHeight = dividerHeight;
                drawable.setBounds(0, 0, dividerWidth, dividerHeight);
            }
        }
        public void addFakeView(View view) {
            view.layout(0, 0, getWidth(), view.getMeasuredHeight());
            this.views.add(view);
        }
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            for (View view : this.views) {
                view.layout(left, top, view.getMeasuredWidth() + left, view.getMeasuredHeight() + top);
            }
        }
        public void clearViews() {
            this.views.clear();
        }
        public void dispatchDraw(Canvas canvas) {
            canvas.save();
            Drawable drawable = this.divider;
            if (drawable != null) {
                drawable.setBounds(0, 0, this.dividerWidth, this.dividerHeight);
            }
            for (View view : this.views) {
                canvas.save();
                canvas.clipRect(0, 0, getWidth(), view.getMeasuredHeight());
                view.draw(canvas);
                canvas.restore();
                Drawable drawable2 = this.divider;
                if (drawable2 != null) {
                    drawable2.draw(canvas);
                    canvas.translate(0.0f, this.dividerHeight);
                }
                canvas.translate(0.0f, view.getMeasuredHeight());
            }
            canvas.restore();
        }
    }
    private static class ExpandAnimation extends Animation {
        private final int baseHeight;
        private final int delta;
        private final GroupInfo groupInfo;
        private final View view;
        private ExpandAnimation(View view, int baseHeight, int delta, GroupInfo groupInfo) {
            this.baseHeight = baseHeight;
            this.delta = delta;
            this.view = view;
            this.groupInfo = groupInfo;
            view.getLayoutParams().height = baseHeight;
            this.view.requestLayout();
        }
        protected void applyTransformation(float f2, Transformation transformation) {
            super.applyTransformation(f2, transformation);
            if (f2 < 1.0f) {
                int i2 = this.baseHeight + (int) (this.delta * f2);
                this.view.getLayoutParams().height = i2;
                this.groupInfo.dummyHeight = i2;
                this.view.requestLayout();
                return;
            }
            int i3 = this.baseHeight + this.delta;
            this.view.getLayoutParams().height = i3;
            this.groupInfo.dummyHeight = i3;
            this.view.requestLayout();
        }
    }
}
