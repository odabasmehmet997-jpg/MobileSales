package com.proje.mobilesales.features.sales.view.serialgroup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.databinding.ItemSerialGroupBinding;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesSerialGroupViewHolder.kt */

public final class SalesSerialGroupViewHolder extends RecyclerView.ViewHolder {
    private final ItemSerialGroupBinding binding;
    private final CheckBox chk_SeriGrup;
    private final TextView dedt_ExpDate;
    private final EditText edt_Amount;
    private final EditText edt_SBegNo;
    private final TextView txt_AmountVal;
    private final TextView txt_SBegNoVal;
    private final TextView txt_SEndNoVal;
    private final TextView txt_UnitVal;

    
    public SalesSerialGroupViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        ItemSerialGroupBinding inflate = ItemSerialGroupBinding.inflate(LayoutInflater.from(itemView.getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        TextView txtSBegNoVal = inflate.txtSBegNoVal;
        Intrinsics.checkNotNullExpressionValue(txtSBegNoVal, "txtSBegNoVal");
        this.txt_SBegNoVal = txtSBegNoVal;
        TextView txtSEndNoVal = this.binding.txtSEndNoVal;
        Intrinsics.checkNotNullExpressionValue(txtSEndNoVal, "txtSEndNoVal");
        this.txt_SEndNoVal = txtSEndNoVal;
        TextView txtAmountVal = this.binding.txtAmountVal;
        Intrinsics.checkNotNullExpressionValue(txtAmountVal, "txtAmountVal");
        this.txt_AmountVal = txtAmountVal;
        TextView txtUnitVal = this.binding.txtUnitVal;
        Intrinsics.checkNotNullExpressionValue(txtUnitVal, "txtUnitVal");
        this.txt_UnitVal = txtUnitVal;
        TextView dedtExpDate = this.binding.dedtExpDate;
        Intrinsics.checkNotNullExpressionValue(dedtExpDate, "dedtExpDate");
        this.dedt_ExpDate = dedtExpDate;
        EditText edtSBegNo = this.binding.edtSBegNo;
        Intrinsics.checkNotNullExpressionValue(edtSBegNo, "edtSBegNo");
        this.edt_SBegNo = edtSBegNo;
        EditText edtAmount = this.binding.edtAmount;
        Intrinsics.checkNotNullExpressionValue(edtAmount, "edtAmount");
        this.edt_Amount = edtAmount;
        CheckBox chkSeriGrup = this.binding.chkSeriGrup;
        Intrinsics.checkNotNullExpressionValue(chkSeriGrup, "chkSeriGrup");
        this.chk_SeriGrup = chkSeriGrup;
    }

    public TextView getTxt_SBegNoVal() {
        return this.txt_SBegNoVal;
    }

    public TextView getTxt_SEndNoVal() {
        return this.txt_SEndNoVal;
    }

    public TextView getTxt_AmountVal() {
        return this.txt_AmountVal;
    }

    public TextView getTxt_UnitVal() {
        return this.txt_UnitVal;
    }

    public TextView getDedt_ExpDate() {
        return this.dedt_ExpDate;
    }

    public EditText getEdt_SBegNo() {
        return this.edt_SBegNo;
    }

    public EditText getEdt_Amount() {
        return this.edt_Amount;
    }

    public CheckBox getChk_SeriGrup() {
        return this.chk_SeriGrup;
    }
}
