package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.privacy_policy_lib.R;
 
public final class ContractItemBinding implements ViewBinding {
    
    public final CheckBox chkContract;
    public final LinearLayout lnContract;
    private final LinearLayout rootView;
    public final TextView txtContract;
    private ContractItemBinding( LinearLayout linearLayout,  CheckBox checkBox,  LinearLayout linearLayout2,  TextView textView) {
        this.rootView = linearLayout;
        this.chkContract = checkBox;
        this.lnContract = linearLayout2;
        this.txtContract = textView;
    }
    public LinearLayout getRoot() {
        return this.rootView;
    }
    public static ContractItemBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }
    public static ContractItemBinding inflate( LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.contract_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ContractItemBinding bind( View view) {
        int i2 = R.id.chk_contract;
        CheckBox checkBox = ViewBindings.findChildViewById(view, i2);
        if (checkBox != null) {
            i2 = R.id.ln_contract;
            LinearLayout linearLayout = ViewBindings.findChildViewById(view, i2);
            if (linearLayout != null) {
                i2 = R.id.txt_contract;
                TextView textView = ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    return new ContractItemBinding((LinearLayout) view, checkBox, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }
}
