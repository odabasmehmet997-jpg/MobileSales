package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemSerialGroupBinding implements ViewBinding {

   
    public final CheckBox chkSeriGrup;

   
    public final TextView dedtExpDate;

   
    public final EditText edtAmount;

   
    public final EditText edtSBegNo;

   
    private final CardView rootView;

   
    public final TextView txtAmountVal;

   
    public final TextView txtSBegNoVal;

   
    public final TextView txtSEndNoVal;

   
    public final TextView txtUnitVal;

    private ItemSerialGroupBinding(final CardView cardView, final CheckBox checkBox, final TextView textView, final EditText editText, final EditText editText2, final TextView textView2, final TextView textView3, final TextView textView4, final TextView textView5) {
        rootView = cardView;
        chkSeriGrup = checkBox;
        dedtExpDate = textView;
        edtAmount = editText;
        edtSBegNo = editText2;
        txtAmountVal = textView2;
        txtSBegNoVal = textView3;
        txtSEndNoVal = textView4;
        txtUnitVal = textView5;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemSerialGroupBinding inflate(final LayoutInflater layoutInflater) {
        return ItemSerialGroupBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemSerialGroupBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_serial_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemSerialGroupBinding.bind(inflate);
    }

   
    public static ItemSerialGroupBinding bind(final View view) {
        int i2 = R.id.chk_SeriGrup;
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.chk_SeriGrup);
        if (null != checkBox) {
            i2 = R.id.dedt_Exp_Date;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.dedt_Exp_Date);
            if (null != textView) {
                i2 = R.id.edt_Amount;
                final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_Amount);
                if (null != editText) {
                    i2 = R.id.edt_SBegNo;
                    final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_SBegNo);
                    if (null != editText2) {
                        i2 = R.id.txt_AmountVal;
                        final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_AmountVal);
                        if (null != textView2) {
                            i2 = R.id.txt_SBegNoVal;
                            final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_SBegNoVal);
                            if (null != textView3) {
                                i2 = R.id.txt_SEndNoVal;
                                final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txt_SEndNoVal);
                                if (null != textView4) {
                                    i2 = R.id.txt_UnitVal;
                                    final TextView textView5 = ViewBindings.findChildViewById(view, R.id.txt_UnitVal);
                                    if (null != textView5) {
                                        return new ItemSerialGroupBinding((CardView) view, checkBox, textView, editText, editText2, textView2, textView3, textView4, textView5);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
