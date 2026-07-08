package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.VariantHeaderModel;

public abstract class FragmentVariantListDialogBinding extends ViewDataBinding {

    @NonNull
    public final AppCompatImageButton btnCancel;

    @NonNull
    public final AppCompatImageButton btnDone;

    @NonNull
    public final AppCompatImageButton btnSelectAll;

    @NonNull
    public final AppCompatImageButton btnUnSelectAll;

    @NonNull
    public final AppCompatEditText edtSearch;

    @NonNull
    public final AppCompatImageButton imgBtnClearSearch;

    @NonNull
    public final AppCompatImageButton imgBtnSearch;

    @NonNull
    public final RecyclerView list;

    @NonNull
    public final LoadingItemBinding llProgressBar;

    @Bindable
    protected VariantHeaderModel mVariantHeader;

    @NonNull
    public final View seperator;

    @NonNull
    public final AppCompatTextView tvCheckedCount;

    @NonNull
    public final AppCompatTextView tvProduct;

    @NonNull
    public final AppCompatTextView tvSerialNo;

    @NonNull
    public final AppCompatTextView tvUserAmount;

    public abstract void setVariantHeader(@Nullable VariantHeaderModel variantHeaderModel);

    protected FragmentVariantListDialogBinding(Object obj, View view, int i2, AppCompatImageButton appCompatImageButton, AppCompatImageButton appCompatImageButton2, AppCompatImageButton appCompatImageButton3, AppCompatImageButton appCompatImageButton4, AppCompatEditText appCompatEditText, AppCompatImageButton appCompatImageButton5, AppCompatImageButton appCompatImageButton6, RecyclerView recyclerView, LoadingItemBinding loadingItemBinding, View view2, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4) {
        super(obj, view, i2);
        this.btnCancel = appCompatImageButton;
        this.btnDone = appCompatImageButton2;
        this.btnSelectAll = appCompatImageButton3;
        this.btnUnSelectAll = appCompatImageButton4;
        this.edtSearch = appCompatEditText;
        this.imgBtnClearSearch = appCompatImageButton5;
        this.imgBtnSearch = appCompatImageButton6;
        this.list = recyclerView;
        this.llProgressBar = loadingItemBinding;
        this.seperator = view2;
        this.tvCheckedCount = appCompatTextView;
        this.tvProduct = appCompatTextView2;
        this.tvSerialNo = appCompatTextView3;
        this.tvUserAmount = appCompatTextView4;
    }

    @Nullable
    public VariantHeaderModel getVariantHeader() {
        return this.mVariantHeader;
    }

    @NonNull
    public static FragmentVariantListDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVariantListDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        final com.proje.mobilesales.databinding.FragmentVariantListDialogBinding fragmentVariantListDialogBinding = (FragmentVariantListDialogBinding) inflateInternal(layoutInflater, R.layout.fragment_variant_list_dialog, viewGroup, z, obj);
        return fragmentVariantListDialogBinding;
    }

    @NonNull
    public static FragmentVariantListDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentVariantListDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        final com.proje.mobilesales.databinding.FragmentVariantListDialogBinding fragmentVariantListDialogBinding = (FragmentVariantListDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_variant_list_dialog, null, false, obj);
        return fragmentVariantListDialogBinding;
    }

    public static FragmentVariantListDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVariantListDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentVariantListDialogBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_variant_list_dialog);
    }
}
