package com.proje.mobilesales.databinding;
import android.annotation.SuppressLint;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;

import static androidx.databinding.adapters.TextViewBindingAdapter.setText;
public class FragmentNotifiedUserListDialogItemBindingImpl extends FragmentNotifiedUserListDialogItemBinding {
     static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView1;
    private NotifiedUserModel mNotifiedUser;
    protected boolean onFieldChange(final int i2, final Object obj, final int i3) {
        return false;
    }
    static {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tvStatus, 3);
        sparseIntArray.put(R.id.guideline50, 4);
    }
    public FragmentNotifiedUserListDialogItemBindingImpl(@Nullable final DataBindingComponent dataBindingComponent, final View view) {
        this(dataBindingComponent, view, FragmentNotifiedUserListDialogItemBindingImpl.mapBindings(dataBindingComponent, view, 5, FragmentNotifiedUserListDialogItemBindingImpl.sIncludes, FragmentNotifiedUserListDialogItemBindingImpl.sViewsWithIds));
    }
    static Object[] mapBindings(final DataBindingComponent dataBindingComponent, final View view, final int i, final ViewDataBinding.IncludedLayouts sIncludes, final SparseIntArray sViewsWithIds) {
        return new Object[0];
    }
    private FragmentNotifiedUserListDialogItemBindingImpl(final DataBindingComponent dataBindingComponent, final View view, final Object[] objArr) {
        setVariable(dataBindingComponent, view, 0, (Guideline) objArr[4], (TextView) objArr[3], (TextView) objArr[2]);
        mDirtyFlags = -1L;
        final ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        final TextView textView = (TextView) objArr[1];
        mboundView1 = textView;
        textView.setTag(null);
        tvStatusDate.setTag(null);
        this.setRootTag(view);
        this.invalidateAll();
    }
    private void setVariable(DataBindingComponent dataBindingComponent, View view, int i, Guideline guideline, TextView textView, TextView textView1) {
    }
    private void setRootTag(final View view) {
    }
    public void invalidateAll() {
        synchronized (this) {
            mDirtyFlags = 2L;
        }
        this.requestRebind();
    }
    private void requestRebind() {
    }
    public boolean hasPendingBindings() {
        synchronized (this) {
            try {
                return 0 != this.mDirtyFlags;
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    public boolean setVariable(final int i2, @Nullable final Object obj) {
        if (4 != i2) {
            return false;
        }
        this.setNotifiedUser((NotifiedUserModel) obj);
        return true;
    }
    public void setNotifiedUser(@Nullable final NotifiedUserModel notifiedUserModel) {
        this.mNotifiedUser = notifiedUserModel;
        synchronized (this) {
            mDirtyFlags |= 1;
        }
        this.requestRebind();
    }
    @SuppressLint("RestrictedApi")
    protected void executeBindings() {
        final long j2;
        final String str;
        final String str2;
        synchronized (this) {
            j2 = mDirtyFlags;
            mDirtyFlags = 0L;
        }
        final NotifiedUserModel notifiedUserModel = this.mNotifiedUser;
        final long j3 = j2 & 3;
        if (0 == j3 || null == notifiedUserModel) {
            str = null;
            str2 = null;
        } else {
            str = notifiedUserModel.getFormattedDateAccordingToStatus();
            str2 = notifiedUserModel.getReceiverName();
        }

        if (0 != j3) {
            setText(mboundView1, str2);
            setText(tvStatusDate, str);
        }
    }
}
