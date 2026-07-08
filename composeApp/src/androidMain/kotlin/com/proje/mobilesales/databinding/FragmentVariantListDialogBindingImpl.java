package com.proje.mobilesales.databinding;


import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.VariantHeaderModel;
import com.proje.mobilesales.features.model.VariantModel;
import java.util.List;

public class FragmentVariantListDialogBindingImpl extends FragmentVariantListDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.llProgressBar, 4);
        sparseIntArray.put(R.id.btnCancel, 5);
        sparseIntArray.put(R.id.tvProduct, 6);
        sparseIntArray.put(R.id.btnDone, 7);
        sparseIntArray.put(R.id.edtSearch, 8);
        sparseIntArray.put(R.id.imgBtnClearSearch, 9);
        sparseIntArray.put(R.id.imgBtnSearch, 10);
        sparseIntArray.put(R.id.tvSerialNo, 11);
        sparseIntArray.put(R.id.tvUserAmount, 12);
        sparseIntArray.put(R.id.seperator, 13);
        sparseIntArray.put(R.id.list, 14);
    }

    public FragmentVariantListDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }
    private FragmentVariantListDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        AppCompatImageButton appCompatImageButton = (AppCompatImageButton) objArr[5];
        AppCompatImageButton appCompatImageButton2 = (AppCompatImageButton) objArr[7];
        AppCompatImageButton appCompatImageButton3 = (AppCompatImageButton) objArr[1];
        AppCompatImageButton appCompatImageButton4 = (AppCompatImageButton) objArr[2];
        AppCompatEditText appCompatEditText = (AppCompatEditText) objArr[8];
        AppCompatImageButton appCompatImageButton5 = (AppCompatImageButton) objArr[9];
        AppCompatImageButton appCompatImageButton6 = (AppCompatImageButton) objArr[10];
        RecyclerView recyclerView = (RecyclerView) objArr[14];
        Object obj = objArr[4];
        super(dataBindingComponent, view, 2, appCompatImageButton, appCompatImageButton2, appCompatImageButton3, appCompatImageButton4, appCompatEditText, appCompatImageButton5, appCompatImageButton6, recyclerView, obj != null ? LoadingItemBinding.bind((View) obj) : null, (View) objArr[13], (AppCompatTextView) objArr[3], (AppCompatTextView) objArr[6], (AppCompatTextView) objArr[11], (AppCompatTextView) objArr[12]);
        this.mDirtyFlags = -1L;
        this.btnSelectAll.setTag(null);
        this.btnUnSelectAll.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.tvCheckedCount.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }
    public boolean hasPendingBindings() {
        synchronized (this) {
            try {
                return this.mDirtyFlags != 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public boolean setVariable(int i2, @Nullable Object obj) {
        if (6 != i2) {
            return false;
        }
        setVariantHeader((VariantHeaderModel) obj);
        return true;
    }
    public void setVariantHeader(@Nullable VariantHeaderModel variantHeaderModel) {
        updateRegistration(1, variantHeaderModel);
        this.mVariantHeader = variantHeaderModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        if (i2 == 0) {
            return onChangeVariantHeaderSelectedItemCount((ObservableField) obj, i3);
        }
        if (i2 != 1) {
            return false;
        }
        return onChangeVariantHeader((VariantHeaderModel) obj, i3);
    }

    private boolean onChangeVariantHeaderSelectedItemCount(ObservableField<Integer> observableField, int i2) {
        if (i2 != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVariantHeader(VariantHeaderModel variantHeaderModel, int i2) {
        if (i2 != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }
    protected void executeBindings() {
        long j2;
        int i2;
        ObservableField<Integer> selectedItemCount;
        List<VariantModel> mVariantModelList;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VariantHeaderModel variantHeaderModel = this.mVariantHeader;
        long j3 = j2 & 7;
        int i3 = 0;
        if (j3 != 0) {
            if (variantHeaderModel != null) {
                mVariantModelList = variantHeaderModel.getMVariantModelList();
                selectedItemCount = variantHeaderModel.getSelectedItemCount();
            } else {
                selectedItemCount = null;
                mVariantModelList = null;
            }
            updateRegistration(0, selectedItemCount);
            int size = mVariantModelList != null ? mVariantModelList.size() : 0;
            Integer num = selectedItemCount != null ? selectedItemCount.get() : null;
            int iSafeUnbox = ViewDataBinding.safeUnbox(num);
            string = num != null ? num.toString() : null;
            boolean z = iSafeUnbox == size;
            if (j3 != 0) {
                j2 |= z ? 80L : 40L;
            }
            i2 = z ? 0 : 8;
            if (z) {
                i3 = 8;
            }
        } else {
            i2 = 0;
        }
        if ((j2 & 7) != 0) {
            this.btnSelectAll.setVisibility(i3);
            this.btnUnSelectAll.setVisibility(i2);
            TextViewBindingAdapter.setText(this.tvCheckedCount, string);
        }
    }
}
