package com.proje.mobilesales.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.VariantModel;

import static androidx.databinding.adapters.TextViewBindingAdapter.getTextString;
import static androidx.databinding.adapters.TextViewBindingAdapter.setText;

public class FragmentVariantListDialogItemBindingImpl extends FragmentVariantListDialogItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final InverseBindingListener cbListItemandroidCheckedAttrChanged;
    private final InverseBindingListener edtUserAmountandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    static {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.hvCode, 7);
        sparseIntArray.put(R.id.hvName, 8);
        sparseIntArray.put(R.id.txt_product_actual_stock_text, 9);
        sparseIntArray.put(R.id.txt_product_defined_price, 10);
        sparseIntArray.put(R.id.guideline70, 11);
        sparseIntArray.put(R.id.guideline90, 12);
        sparseIntArray.put(R.id.guideline20, 13);
        sparseIntArray.put(R.id.guideline50, 14);
    }

    private VariantModel mVariant;

    class C25981 implements InverseBindingListener {
        C25981() {
        }
        public void onChange() {
            final ObservableField<Boolean> checked;
            final boolean isChecked = cbListItem.isChecked();
            final VariantModel variantModel = FragmentVariantListDialogItemBindingImpl.this.mVariant;
            if (null == variantModel || null == (checked = variantModel.getChecked())) {
                return;
            }
            checked.set(Boolean.valueOf(isChecked));
        }
    }
    class C25992 implements InverseBindingListener {
        C25992() {
        }
        public void onChange() {
            final ObservableField<String> amount;
            final String textString = getTextString(edtUserAmount);
            final VariantModel variantModel = FragmentVariantListDialogItemBindingImpl.this.mVariant;
            if (null == variantModel || null == (amount = variantModel.getAmount())) {
                return;
            }
            amount.set(textString);
        }
    }
    public FragmentVariantListDialogItemBindingImpl(final DataBindingComponent dataBindingComponent, final View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 15, FragmentVariantListDialogItemBindingImpl.sIncludes, FragmentVariantListDialogItemBindingImpl.sViewsWithIds));
    }
    private FragmentVariantListDialogItemBindingImpl(final DataBindingComponent dataBindingComponent, final View view, final Object[] objArr) {
        super(dataBindingComponent, view, 7, (AppCompatCheckBox) objArr[4], (AppCompatEditText) objArr[3], (Guideline) objArr[13], (Guideline) objArr[14], (Guideline) objArr[11], (Guideline) objArr[12], (HorizontalScrollView) objArr[7], (HorizontalScrollView) objArr[8], (TextView) objArr[1], (TextView) objArr[2], (TextView) objArr[6], (TextView) objArr[5], (TextView) objArr[9], (TextView) objArr[10]);
        cbListItemandroidCheckedAttrChanged = new InverseBindingListener() {
            void C25981() {
            }
            public void onChange() {
                final ObservableField<Boolean> checked;
                final boolean isChecked = cbListItem.isChecked();
                final VariantModel variantModel = FragmentVariantListDialogItemBindingImpl.this.mVariant;
                if (null == variantModel || null == (checked = variantModel.getChecked())) {
                    return;
                }
                checked.set(Boolean.valueOf(isChecked));
            }
        };
        edtUserAmountandroidTextAttrChanged = new InverseBindingListener() {
            void C25992() {
            }
            public void onChange() {
                final ObservableField<String> amount;
                final String textString = getTextString(edtUserAmount);
                final VariantModel variantModel = FragmentVariantListDialogItemBindingImpl.this.mVariant;
                if (null == variantModel || null == (amount = variantModel.getAmount())) {
                    return;
                }
                amount.set(textString);
            }
        };
        mDirtyFlags = -1L;
        cbListItem.setTag(null);
        edtUserAmount.setTag(null);
        final ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        tvCode.setTag(null);
        tvVariantName.setTag(null);
        txtDefinedPrice.setTag(null);
        txtProductActualStock.setTag(null);
        setRootTag(view);
        this.invalidateAll();
    }
    public void invalidateAll() {
        synchronized (this) {
            mDirtyFlags = 128L;
        }
        requestRebind();
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
    public boolean setVariable(final int i2, final Object obj) {
        if (5 != i2) {
            return false;
        }
        this.setVariant((VariantModel) obj);
        return true;
    }
    public void setVariant(final VariantModel variantModel) {
        updateRegistration(3, variantModel.getChecked());
        this.mVariant = variantModel;
        synchronized (this) {
            mDirtyFlags |= 8;
        }
        variantModel.notifyPropertyChanged(5);
        super.toString();
    }
    private void updateRegistration(int i, ObservableField<Boolean> variantModel) {
    }
    protected boolean onFieldChange(final int i2, final Object obj, final int i3) {
        switch (i2) {
            case 0:
                return this.onChangeVariantChecked((ObservableField) obj, i3);
            case 1:
                return this.onChangeVariantDefinedPrice((ObservableField) obj, i3);
            case 2:
                return this.onChangeVariantCode((ObservableField) obj, i3);
            case 3:
                return this.onChangeVariant((VariantModel) obj, i3);
            case 4:
                return this.onChangeVariantActualStock((ObservableField) obj, i3);
            case 5:
                return this.onChangeVariantAmount((ObservableField) obj, i3);
            case 6:
                return this.onChangeVariantName((ObservableField) obj, i3);
            default:
                return false;
        }
    }
    private boolean onChangeVariantChecked(final ObservableField<Boolean> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 1;
        }
        return true;
    }
    private boolean onChangeVariantDefinedPrice(final ObservableField<String> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 2;
        }
        return true;
    }
    private boolean onChangeVariantCode(final ObservableField<String> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 4;
        }
        return true;
    }
    private boolean onChangeVariant(final VariantModel variantModel, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 8;
        }
        return true;
    }
    private boolean onChangeVariantActualStock(final ObservableField<String> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 16;
        }
        return true;
    }
    private boolean onChangeVariantAmount(final ObservableField<String> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 32;
        }
        return true;
    }
    private boolean onChangeVariantName(final ObservableField<String> observableField, final int i2) {
        if (0 != i2) {
            return false;
        }
        synchronized (this) {
            mDirtyFlags |= 64;
        }
        return true;
    }
    protected void executeBindings() {
        final long j2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        synchronized (this) {
            j2 = mDirtyFlags;
            mDirtyFlags = 0L;
        }
        final VariantModel variantModel = this.mVariant;
        boolean z = false;
        if (0 != (255 & j2)) {
            if (0 != (j2 & 137)) {
                final ObservableField<Boolean> checked = null != variantModel ? variantModel.getChecked() : null;
                updateRegistration(0, checked);
                z = ViewDataBinding.safeUnbox(null != checked ? checked.get() : null);
            }
            if (0 != (j2 & 138)) {
                final ObservableField<String> definedPrice = null != variantModel ? variantModel.getDefinedPrice() : null;
                updateRegistration(1, definedPrice);
                if (null != definedPrice) {
                    str2 = definedPrice.get();
                    if (0 != (j2 & 140)) {
                        final ObservableField<String> code = null != variantModel ? variantModel.getCode() : null;
                        updateRegistration(2, code);
                        if (null != code) {
                            str4 = code.get();
                            if (0 != (j2 & 152)) {
                                final ObservableField<String> actualStock = null != variantModel ? variantModel.getActualStock() : null;
                                updateRegistration(4, actualStock);
                                if (null != actualStock) {
                                    str5 = actualStock.get();
                                    if (0 != (j2 & 168)) {
                                        final ObservableField<String> amount = null != variantModel ? variantModel.getAmount() : null;
                                        updateRegistration(5, amount);
                                        if (null != amount) {
                                            str3 = amount.get();
                                            if (0 != (j2 & 200)) {
                                                final ObservableField<String> name = null != variantModel ? variantModel.getName() : null;
                                                updateRegistration(6, name);
                                                if (null != name) {
                                                    str = name.get();
                                                }
                                            }
                                            str = null;
                                        }
                                    }
                                    str3 = null;
                                    if (0 != (j2 & 200)) {
                                    }
                                    str = null;
                                }
                            }
                            str5 = null;
                            if (0 != (j2 & 168)) {
                            }
                            str3 = null;
                            if (0 != (j2 & 200)) {
                            }
                            str = null;
                        }
                    }
                    str4 = null;
                    if (0 != (j2 & 152)) {
                    }
                    str5 = null;
                    if (0 != (j2 & 168)) {
                    }
                    str3 = null;
                    if (0 != (j2 & 200)) {
                    }
                    str = null;
                }
            }
            str2 = null;
            if (0 != (j2 & 140)) {
            }
            str4 = null;
            if (0 != (j2 & 152)) {
            }
            str5 = null;
            if (0 != (j2 & 168)) {
            }
            str3 = null;
            if (0 != (j2 & 200)) {
            }
            str = null;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        if (0 != (j2 & 137)) {
            CompoundButtonBindingAdapter.setChecked(cbListItem, z);
            edtUserAmount.setEnabled(z);
        }
        if (0 != (128 & j2)) {
            CompoundButtonBindingAdapter.setListeners(cbListItem, null, cbListItemandroidCheckedAttrChanged);
            TextViewBindingAdapter.setTextWatcher(edtUserAmount, null, null, null, edtUserAmountandroidTextAttrChanged);
        }
        if (0 != (j2 & 168)) {
            setText(edtUserAmount, str3);
        }
        if (0 != (140 & j2)) {
            setText(tvCode, str4);
        }
        if (0 != (200 & j2)) {
            setText(tvVariantName, str);
        }
        if (0 != (138 & j2)) {
            setText(txtDefinedPrice, str2);
        }
        if (0 != (j2 & 152)) {
            setText(txtProductActualStock, str5);
        }
    }
}
