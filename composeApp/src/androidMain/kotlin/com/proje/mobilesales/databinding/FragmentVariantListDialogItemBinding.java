package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.model.VariantModel;

public abstract class FragmentVariantListDialogItemBinding extends ViewDataBinding {

    public final AppCompatCheckBox cbListItem;
    public final AppCompatEditText edtUserAmount;
    public final Guideline guideline20;
    public final Guideline guideline50;
    public final Guideline guideline70;
    public final Guideline guideline90;
    public final HorizontalScrollView hvCode;
    public final HorizontalScrollView hvName;
    protected VariantModel mVariant;
    public final TextView tvCode;
    public final TextView tvVariantName;
    public final TextView txtDefinedPrice;
    public final TextView txtProductActualStock;
    public final TextView txtProductActualStockText;
    public final TextView txtProductDefinedPrice;
    public abstract void setVariant(VariantModel variantModel);
    protected FragmentVariantListDialogItemBinding(Object obj, View view, int i2, AppCompatCheckBox appCompatCheckBox, AppCompatEditText appCompatEditText, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, HorizontalScrollView horizontalScrollView, HorizontalScrollView horizontalScrollView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i2);
        this.cbListItem = appCompatCheckBox;
        this.edtUserAmount = appCompatEditText;
        this.guideline20 = guideline;
        this.guideline50 = guideline2;
        this.guideline70 = guideline3;
        this.guideline90 = guideline4;
        this.hvCode = horizontalScrollView;
        this.hvName = horizontalScrollView2;
        this.tvCode = textView;
        this.tvVariantName = textView2;
        this.txtDefinedPrice = textView3;
        this.txtProductActualStock = textView4;
        this.txtProductActualStockText = textView5;
        this.txtProductDefinedPrice = textView6;
    }
    public VariantModel getVariant() {
        return this.mVariant;
    }
    public static FragmentVariantListDialogItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentVariantListDialogItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentVariantListDialogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_variant_list_dialog_item, viewGroup, z, obj);
    }
    public static FragmentVariantListDialogItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentVariantListDialogItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentVariantListDialogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_variant_list_dialog_item, null, false, obj);
    }
    public static FragmentVariantListDialogItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static FragmentVariantListDialogItemBinding bind(View view, Object obj) {
        return (FragmentVariantListDialogItemBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_variant_list_dialog_item);
    }
}
