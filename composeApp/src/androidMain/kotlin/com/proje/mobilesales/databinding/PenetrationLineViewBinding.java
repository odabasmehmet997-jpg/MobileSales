package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class PenetrationLineViewBinding implements ViewBinding {
    public final EditText edtProductAmount;
    public final AppCompatEditText etProductPrice;
    public final ImageButton imgBtnPenetrationCam;
    public final ImageButton imgBtnPenetrationNot;
    public final ImageButton imgBtnProductMinus;
    public final ImageButton imgBtnProductPlus;
    public final Switch mProductSwitch;
    public final RelativeLayout rltContainer;
    public final RelativeLayout rltProductOperationAmount;
    private final View rootView;
    public final TextView txtProductCode;
    public final TextView txtProductName;
    private PenetrationLineViewBinding(final View view, final EditText editText, final AppCompatEditText appCompatEditText, final ImageButton imageButton, final ImageButton imageButton2, final ImageButton imageButton3, final ImageButton imageButton4, final Switch r8, final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final TextView textView, final TextView textView2) {
        rootView = view;
        edtProductAmount = editText;
        etProductPrice = appCompatEditText;
        imgBtnPenetrationCam = imageButton;
        imgBtnPenetrationNot = imageButton2;
        imgBtnProductMinus = imageButton3;
        imgBtnProductPlus = imageButton4;
        mProductSwitch = r8;
        rltContainer = relativeLayout;
        rltProductOperationAmount = relativeLayout2;
        txtProductCode = textView;
        txtProductName = textView2;
    } 
    public View getRoot() {
        return rootView;
    } 
    public static PenetrationLineViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup) {
        if (null == viewGroup) {
            throw new NullPointerException("parent");
        }
        layoutInflater.inflate(R.layout.penetration_line_view, viewGroup);
        return PenetrationLineViewBinding.bind(viewGroup);
    } 
    public static PenetrationLineViewBinding bind(final View view) {
        int i2 = R.id.edt_product_amount;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_product_amount);
        if (null != editText) {
            i2 = R.id.et_productPrice;
            final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.et_productPrice);
            if (null != appCompatEditText) {
                i2 = R.id.imgBtn_penetration_cam;
                final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.imgBtn_penetration_cam);
                if (null != imageButton) {
                    i2 = R.id.imgBtn_penetration_not;
                    final ImageButton imageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtn_penetration_not);
                    if (null != imageButton2) {
                        i2 = R.id.imgBtn_product_minus;
                        final ImageButton imageButton3 = ViewBindings.findChildViewById(view, R.id.imgBtn_product_minus);
                        if (null != imageButton3) {
                            i2 = R.id.imgBtn_product_plus;
                            final ImageButton imageButton4 = ViewBindings.findChildViewById(view, R.id.imgBtn_product_plus);
                            if (null != imageButton4) {
                                i2 = R.id.mProductSwitch;
                                final Switch r10 = ViewBindings.findChildViewById(view, R.id.mProductSwitch);
                                if (null != r10) {
                                    i2 = R.id.rlt_container;
                                    final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.rlt_container);
                                    if (null != relativeLayout) {
                                        i2 = R.id.rlt_productOperationAmount;
                                        final RelativeLayout relativeLayout2 = ViewBindings.findChildViewById(view, R.id.rlt_productOperationAmount);
                                        if (null != relativeLayout2) {
                                            i2 = R.id.txt_productCode;
                                            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_productCode);
                                            if (null != textView) {
                                                i2 = R.id.txt_productName;
                                                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_productName);
                                                if (null != textView2) {
                                                    return new PenetrationLineViewBinding(view, editText, appCompatEditText, imageButton, imageButton2, imageButton3, imageButton4, r10, relativeLayout, relativeLayout2, textView, textView2);
                                                }
                                            }
                                        }
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
