package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ItemVisitOrderInfoBinding implements ViewBinding {

   
    public final ImageView imgFicheTransfer;

   
    public final LinearLayout lnCustomerInfo;

   
    public final LinearLayout lnDebitedOrVisitReason;

   
    public final LinearLayout lnFicheHeader;

   
    public final LinearLayout lnReceiptInfo;

   
    public final LinearLayout lnTotalLayout;

   
    private final RelativeLayout rootView;

   
    public final AppCompatTextView tvAccountNumber;

   
    public final AppCompatTextView tvAccountNumberNote;

   
    public final AppCompatTextView tvBranch;

   
    public final AppCompatTextView tvBranchNote;

   
    public final AppCompatTextView tvCustomerCode;

   
    public final AppCompatTextView tvCustomerName;

   
    public final AppCompatTextView tvDebitedOrVisitReason;

   
    public final AppCompatTextView tvDebitedOrVisitReasonExplanation;

   
    public final AppCompatTextView tvNote;

   
    public final AppCompatTextView tvNoteColon;

   
    public final AppCompatTextView tvNoteExplanation;

   
    public final AppCompatTextView tvShipAddressOrBankExplanation;

   
    public final AppCompatTextView tvTime;

   
    public final AppCompatTextView tvTotal;

   
    public final AppCompatTextView tvTotalColon;

   
    public final AppCompatTextView tvTotalExplanation;

    private ItemVisitOrderInfoBinding(final RelativeLayout relativeLayout, final ImageView imageView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8, final AppCompatTextView appCompatTextView9, final AppCompatTextView appCompatTextView10, final AppCompatTextView appCompatTextView11, final AppCompatTextView appCompatTextView12, final AppCompatTextView appCompatTextView13, final AppCompatTextView appCompatTextView14, final AppCompatTextView appCompatTextView15, final AppCompatTextView appCompatTextView16) {
        rootView = relativeLayout;
        imgFicheTransfer = imageView;
        lnCustomerInfo = linearLayout;
        lnDebitedOrVisitReason = linearLayout2;
        lnFicheHeader = linearLayout3;
        lnReceiptInfo = linearLayout4;
        lnTotalLayout = linearLayout5;
        tvAccountNumber = appCompatTextView;
        tvAccountNumberNote = appCompatTextView2;
        tvBranch = appCompatTextView3;
        tvBranchNote = appCompatTextView4;
        tvCustomerCode = appCompatTextView5;
        tvCustomerName = appCompatTextView6;
        tvDebitedOrVisitReason = appCompatTextView7;
        tvDebitedOrVisitReasonExplanation = appCompatTextView8;
        tvNote = appCompatTextView9;
        tvNoteColon = appCompatTextView10;
        tvNoteExplanation = appCompatTextView11;
        tvShipAddressOrBankExplanation = appCompatTextView12;
        tvTime = appCompatTextView13;
        tvTotal = appCompatTextView14;
        tvTotalColon = appCompatTextView15;
        tvTotalExplanation = appCompatTextView16;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static ItemVisitOrderInfoBinding inflate(final LayoutInflater layoutInflater) {
        return ItemVisitOrderInfoBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemVisitOrderInfoBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_visit_order_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemVisitOrderInfoBinding.bind(inflate);
    }

   
    public static ItemVisitOrderInfoBinding bind(final View view) {
        int i2 = R.id.img_fiche_transfer;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_fiche_transfer);
        if (null != imageView) {
            i2 = R.id.ln_customerInfo;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerInfo);
            if (null != linearLayout) {
                i2 = R.id.ln_debitedOrVisitReason;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_debitedOrVisitReason);
                if (null != linearLayout2) {
                    i2 = R.id.ln_ficheHeader;
                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_ficheHeader);
                    if (null != linearLayout3) {
                        i2 = R.id.ln_receiptInfo;
                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_receiptInfo);
                        if (null != linearLayout4) {
                            i2 = R.id.ln_totalLayout;
                            final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.ln_totalLayout);
                            if (null != linearLayout5) {
                                i2 = R.id.tv_accountNumber;
                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tv_accountNumber);
                                if (null != appCompatTextView) {
                                    i2 = R.id.tv_accountNumberNote;
                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tv_accountNumberNote);
                                    if (null != appCompatTextView2) {
                                        i2 = R.id.tv_branch;
                                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tv_branch);
                                        if (null != appCompatTextView3) {
                                            i2 = R.id.tv_branchNote;
                                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tv_branchNote);
                                            if (null != appCompatTextView4) {
                                                i2 = R.id.tv_customerCode;
                                                final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tv_customerCode);
                                                if (null != appCompatTextView5) {
                                                    i2 = R.id.tv_customerName;
                                                    final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tv_customerName);
                                                    if (null != appCompatTextView6) {
                                                        i2 = R.id.tv_debitedOrVisitReason;
                                                        final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tv_debitedOrVisitReason);
                                                        if (null != appCompatTextView7) {
                                                            i2 = R.id.tv_debitedOrVisitReasonExplanation;
                                                            final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tv_debitedOrVisitReasonExplanation);
                                                            if (null != appCompatTextView8) {
                                                                i2 = R.id.tv_note;
                                                                final AppCompatTextView appCompatTextView9 = ViewBindings.findChildViewById(view, R.id.tv_note);
                                                                if (null != appCompatTextView9) {
                                                                    i2 = R.id.tv_noteColon;
                                                                    final AppCompatTextView appCompatTextView10 = ViewBindings.findChildViewById(view, R.id.tv_noteColon);
                                                                    if (null != appCompatTextView10) {
                                                                        i2 = R.id.tv_noteExplanation;
                                                                        final AppCompatTextView appCompatTextView11 = ViewBindings.findChildViewById(view, R.id.tv_noteExplanation);
                                                                        if (null != appCompatTextView11) {
                                                                            i2 = R.id.tv_shipAddressOrBankExplanation;
                                                                            final AppCompatTextView appCompatTextView12 = ViewBindings.findChildViewById(view, R.id.tv_shipAddressOrBankExplanation);
                                                                            if (null != appCompatTextView12) {
                                                                                i2 = R.id.tv_time;
                                                                                final AppCompatTextView appCompatTextView13 = ViewBindings.findChildViewById(view, R.id.tv_time);
                                                                                if (null != appCompatTextView13) {
                                                                                    i2 = R.id.tv_total;
                                                                                    final AppCompatTextView appCompatTextView14 = ViewBindings.findChildViewById(view, R.id.tv_total);
                                                                                    if (null != appCompatTextView14) {
                                                                                        i2 = R.id.tv_totalColon;
                                                                                        final AppCompatTextView appCompatTextView15 = ViewBindings.findChildViewById(view, R.id.tv_totalColon);
                                                                                        if (null != appCompatTextView15) {
                                                                                            i2 = R.id.tv_totalExplanation;
                                                                                            final AppCompatTextView appCompatTextView16 = ViewBindings.findChildViewById(view, R.id.tv_totalExplanation);
                                                                                            if (null != appCompatTextView16) {
                                                                                                return new ItemVisitOrderInfoBinding((RelativeLayout) view, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14, appCompatTextView15, appCompatTextView16);
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
