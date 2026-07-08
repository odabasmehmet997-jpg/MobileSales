package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
import com.proje.mobilesales.core.widget.FichePropertyTextView;

public final class FragmentCustomerNewBinding implements ViewBinding {
    public final EditText edtFchInchargeTelCode;
    public final EditText edtFchInchargeTelNum;
    public final FichePropertyEditView fchAddress1;
    public final FichePropertyEditView fchAddress2;
    public final FichePropertyTextView fchCode;
    public final FichePropertyTextView fchCountry;
    public final FichePropertyEditView fchCustomerName;
    public final FichePropertyEditView fchCustomerSurname;
    public final FichePropertyEditView fchDiscount;
    public final FichePropertyTextView fchDistrict;
    public final FichePropertyEditView fchEmail;
    public final FichePropertyEditView fchEmail2;
    public final FichePropertyEditView fchExpiry;
    public final FichePropertyEditView fchFax;
    public final FichePropertyEditView fchGroupCode;
    public final FichePropertyEditView fchIdNo;
    public final FichePropertyEditView fchInchargeDefinition;
    public final FichePropertyEditView fchInchargeEmail;
    public final FichePropertyEditView fchInchargeName;
    public final FichePropertyEditView fchName;
    public final FichePropertyTextView fchPayPlan;
    public final FichePropertyTextView fchPayType;
    public final CheckBox fchPersonalCompany;
    public final FichePropertyEditView fchPhone1;
    public final FichePropertyEditView fchPhone2;
    public final FichePropertyTextView fchProvince;
    public final FichePropertyEditView fchRelatedPerson;
    public final FichePropertyEditView fchSpeCode;
    public final FichePropertyEditView fchSpeCode2;
    public final FichePropertyEditView fchSpeCode3;
    public final FichePropertyEditView fchSpeCode4;
    public final FichePropertyEditView fchSpeCode5;
    public final FichePropertyEditView fchTaxNo;
    public final FichePropertyEditView fchTaxOffice;
    public final FichePropertyEditView fchWarrantyCode;
    public final FichePropertyEditView fchZipCode;
    public final ImageView imgCustomer;
    public final LinearLayout lnCustomerHeader;
    public final LinearLayout lnCustomerInchargeHeader;
    public final LinearLayout lnFicheProperty;
    public final LinearLayout lnPersonalCompany;
    public final View personalCompanyDivider;
    private final LinearLayout rootView;
    public final TextView txtFchInchargeTel;
    private FragmentCustomerNewBinding(final LinearLayout linearLayout, final EditText editText, final EditText editText2, final FichePropertyEditView fichePropertyEditView, final FichePropertyEditView fichePropertyEditView2, final FichePropertyTextView fichePropertyTextView, final FichePropertyTextView fichePropertyTextView2, final FichePropertyEditView fichePropertyEditView3, final FichePropertyEditView fichePropertyEditView4, final FichePropertyEditView fichePropertyEditView5, final FichePropertyTextView fichePropertyTextView3, final FichePropertyEditView fichePropertyEditView6, final FichePropertyEditView fichePropertyEditView7, final FichePropertyEditView fichePropertyEditView8, final FichePropertyEditView fichePropertyEditView9, final FichePropertyEditView fichePropertyEditView10, final FichePropertyEditView fichePropertyEditView11, final FichePropertyEditView fichePropertyEditView12, final FichePropertyEditView fichePropertyEditView13, final FichePropertyEditView fichePropertyEditView14, final FichePropertyEditView fichePropertyEditView15, final FichePropertyTextView fichePropertyTextView4, final FichePropertyTextView fichePropertyTextView5, final CheckBox checkBox, final FichePropertyEditView fichePropertyEditView16, final FichePropertyEditView fichePropertyEditView17, final FichePropertyTextView fichePropertyTextView6, final FichePropertyEditView fichePropertyEditView18, final FichePropertyEditView fichePropertyEditView19, final FichePropertyEditView fichePropertyEditView20, final FichePropertyEditView fichePropertyEditView21, final FichePropertyEditView fichePropertyEditView22, final FichePropertyEditView fichePropertyEditView23, final FichePropertyEditView fichePropertyEditView24, final FichePropertyEditView fichePropertyEditView25, final FichePropertyEditView fichePropertyEditView26, final FichePropertyEditView fichePropertyEditView27, final ImageView imageView, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5, final View view, final TextView textView) {
        rootView = linearLayout;
        edtFchInchargeTelCode = editText;
        edtFchInchargeTelNum = editText2;
        fchAddress1 = fichePropertyEditView;
        fchAddress2 = fichePropertyEditView2;
        fchCode = fichePropertyTextView;
        fchCountry = fichePropertyTextView2;
        fchCustomerName = fichePropertyEditView3;
        fchCustomerSurname = fichePropertyEditView4;
        fchDiscount = fichePropertyEditView5;
        fchDistrict = fichePropertyTextView3;
        fchEmail = fichePropertyEditView6;
        fchEmail2 = fichePropertyEditView7;
        fchExpiry = fichePropertyEditView8;
        fchFax = fichePropertyEditView9;
        fchGroupCode = fichePropertyEditView10;
        fchIdNo = fichePropertyEditView11;
        fchInchargeDefinition = fichePropertyEditView12;
        fchInchargeEmail = fichePropertyEditView13;
        fchInchargeName = fichePropertyEditView14;
        fchName = fichePropertyEditView15;
        fchPayPlan = fichePropertyTextView4;
        fchPayType = fichePropertyTextView5;
        fchPersonalCompany = checkBox;
        fchPhone1 = fichePropertyEditView16;
        fchPhone2 = fichePropertyEditView17;
        fchProvince = fichePropertyTextView6;
        fchRelatedPerson = fichePropertyEditView18;
        fchSpeCode = fichePropertyEditView19;
        fchSpeCode2 = fichePropertyEditView20;
        fchSpeCode3 = fichePropertyEditView21;
        fchSpeCode4 = fichePropertyEditView22;
        fchSpeCode5 = fichePropertyEditView23;
        fchTaxNo = fichePropertyEditView24;
        fchTaxOffice = fichePropertyEditView25;
        fchWarrantyCode = fichePropertyEditView26;
        fchZipCode = fichePropertyEditView27;
        imgCustomer = imageView;
        lnCustomerHeader = linearLayout2;
        lnCustomerInchargeHeader = linearLayout3;
        lnFicheProperty = linearLayout4;
        lnPersonalCompany = linearLayout5;
        personalCompanyDivider = view;
        txtFchInchargeTel = textView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FragmentCustomerNewBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentCustomerNewBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentCustomerNewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_customer_new, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentCustomerNewBinding.bind(inflate);
    }
    public static FragmentCustomerNewBinding bind(final View view) {
        int i2 = R.id.edt_fch_incharge_tel_code;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_fch_incharge_tel_code);
        if (null != editText) {
            i2 = R.id.edt_fch_incharge_tel_num;
            final EditText editText2 = ViewBindings.findChildViewById(view, R.id.edt_fch_incharge_tel_num);
            if (null != editText2) {
                i2 = R.id.fch_address1;
                final FichePropertyEditView fichePropertyEditView = ViewBindings.findChildViewById(view, R.id.fch_address1);
                if (null != fichePropertyEditView) {
                    i2 = R.id.fch_address2;
                    final FichePropertyEditView fichePropertyEditView2 = ViewBindings.findChildViewById(view, R.id.fch_address2);
                    if (null != fichePropertyEditView2) {
                        i2 = R.id.fch_code;
                        final FichePropertyTextView fichePropertyTextView = ViewBindings.findChildViewById(view, R.id.fch_code);
                        if (null != fichePropertyTextView) {
                            i2 = R.id.fch_country;
                            final FichePropertyTextView fichePropertyTextView2 = ViewBindings.findChildViewById(view, R.id.fch_country);
                            if (null != fichePropertyTextView2) {
                                i2 = R.id.fch_customer_name;
                                final FichePropertyEditView fichePropertyEditView3 = ViewBindings.findChildViewById(view, R.id.fch_customer_name);
                                if (null != fichePropertyEditView3) {
                                    i2 = R.id.fch_customer_surname;
                                    final FichePropertyEditView fichePropertyEditView4 = ViewBindings.findChildViewById(view, R.id.fch_customer_surname);
                                    if (null != fichePropertyEditView4) {
                                        i2 = R.id.fch_discount;
                                        final FichePropertyEditView fichePropertyEditView5 = ViewBindings.findChildViewById(view, R.id.fch_discount);
                                        if (null != fichePropertyEditView5) {
                                            i2 = R.id.fch_district;
                                            final FichePropertyTextView fichePropertyTextView3 = ViewBindings.findChildViewById(view, R.id.fch_district);
                                            if (null != fichePropertyTextView3) {
                                                i2 = R.id.fch_email;
                                                final FichePropertyEditView fichePropertyEditView6 = ViewBindings.findChildViewById(view, R.id.fch_email);
                                                if (null != fichePropertyEditView6) {
                                                    i2 = R.id.fch_email2;
                                                    final FichePropertyEditView fichePropertyEditView7 = ViewBindings.findChildViewById(view, R.id.fch_email2);
                                                    if (null != fichePropertyEditView7) {
                                                        i2 = R.id.fch_expiry;
                                                        final FichePropertyEditView fichePropertyEditView8 = ViewBindings.findChildViewById(view, R.id.fch_expiry);
                                                        if (null != fichePropertyEditView8) {
                                                            i2 = R.id.fch_fax;
                                                            final FichePropertyEditView fichePropertyEditView9 = ViewBindings.findChildViewById(view, R.id.fch_fax);
                                                            if (null != fichePropertyEditView9) {
                                                                i2 = R.id.fch_group_code;
                                                                final FichePropertyEditView fichePropertyEditView10 = ViewBindings.findChildViewById(view, R.id.fch_group_code);
                                                                if (null != fichePropertyEditView10) {
                                                                    i2 = R.id.fch_id_no;
                                                                    final FichePropertyEditView fichePropertyEditView11 = ViewBindings.findChildViewById(view, R.id.fch_id_no);
                                                                    if (null != fichePropertyEditView11) {
                                                                        i2 = R.id.fch_incharge_definition;
                                                                        final FichePropertyEditView fichePropertyEditView12 = ViewBindings.findChildViewById(view, R.id.fch_incharge_definition);
                                                                        if (null != fichePropertyEditView12) {
                                                                            i2 = R.id.fch_incharge_email;
                                                                            final FichePropertyEditView fichePropertyEditView13 = ViewBindings.findChildViewById(view, R.id.fch_incharge_email);
                                                                            if (null != fichePropertyEditView13) {
                                                                                i2 = R.id.fch_incharge_name;
                                                                                final FichePropertyEditView fichePropertyEditView14 = ViewBindings.findChildViewById(view, R.id.fch_incharge_name);
                                                                                if (null != fichePropertyEditView14) {
                                                                                    i2 = R.id.fch_name;
                                                                                    final FichePropertyEditView fichePropertyEditView15 = ViewBindings.findChildViewById(view, R.id.fch_name);
                                                                                    if (null != fichePropertyEditView15) {
                                                                                        i2 = R.id.fch_pay_plan;
                                                                                        final FichePropertyTextView fichePropertyTextView4 = ViewBindings.findChildViewById(view, R.id.fch_pay_plan);
                                                                                        if (null != fichePropertyTextView4) {
                                                                                            i2 = R.id.fch_pay_type;
                                                                                            final FichePropertyTextView fichePropertyTextView5 = ViewBindings.findChildViewById(view, R.id.fch_pay_type);
                                                                                            if (null != fichePropertyTextView5) {
                                                                                                i2 = R.id.fch_personal_company;
                                                                                                final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.fch_personal_company);
                                                                                                if (null != checkBox) {
                                                                                                    i2 = R.id.fch_phone1;
                                                                                                    final FichePropertyEditView fichePropertyEditView16 = ViewBindings.findChildViewById(view, R.id.fch_phone1);
                                                                                                    if (null != fichePropertyEditView16) {
                                                                                                        i2 = R.id.fch_phone2;
                                                                                                        final FichePropertyEditView fichePropertyEditView17 = ViewBindings.findChildViewById(view, R.id.fch_phone2);
                                                                                                        if (null != fichePropertyEditView17) {
                                                                                                            i2 = R.id.fch_province;
                                                                                                            final FichePropertyTextView fichePropertyTextView6 = ViewBindings.findChildViewById(view, R.id.fch_province);
                                                                                                            if (null != fichePropertyTextView6) {
                                                                                                                i2 = R.id.fch_related_person;
                                                                                                                final FichePropertyEditView fichePropertyEditView18 = ViewBindings.findChildViewById(view, R.id.fch_related_person);
                                                                                                                if (null != fichePropertyEditView18) {
                                                                                                                    i2 = R.id.fch_spe_code;
                                                                                                                    final FichePropertyEditView fichePropertyEditView19 = ViewBindings.findChildViewById(view, R.id.fch_spe_code);
                                                                                                                    if (null != fichePropertyEditView19) {
                                                                                                                        i2 = R.id.fch_spe_code2;
                                                                                                                        final FichePropertyEditView fichePropertyEditView20 = ViewBindings.findChildViewById(view, R.id.fch_spe_code2);
                                                                                                                        if (null != fichePropertyEditView20) {
                                                                                                                            i2 = R.id.fch_spe_code3;
                                                                                                                            final FichePropertyEditView fichePropertyEditView21 = ViewBindings.findChildViewById(view, R.id.fch_spe_code3);
                                                                                                                            if (null != fichePropertyEditView21) {
                                                                                                                                i2 = R.id.fch_spe_code4;
                                                                                                                                final FichePropertyEditView fichePropertyEditView22 = ViewBindings.findChildViewById(view, R.id.fch_spe_code4);
                                                                                                                                if (null != fichePropertyEditView22) {
                                                                                                                                    i2 = R.id.fch_spe_code5;
                                                                                                                                    final FichePropertyEditView fichePropertyEditView23 = ViewBindings.findChildViewById(view, R.id.fch_spe_code5);
                                                                                                                                    if (null != fichePropertyEditView23) {
                                                                                                                                        i2 = R.id.fch_tax_no;
                                                                                                                                        final FichePropertyEditView fichePropertyEditView24 = ViewBindings.findChildViewById(view, R.id.fch_tax_no);
                                                                                                                                        if (null != fichePropertyEditView24) {
                                                                                                                                            i2 = R.id.fch_tax_office;
                                                                                                                                            final FichePropertyEditView fichePropertyEditView25 = ViewBindings.findChildViewById(view, R.id.fch_tax_office);
                                                                                                                                            if (null != fichePropertyEditView25) {
                                                                                                                                                i2 = R.id.fch_warranty_code;
                                                                                                                                                final FichePropertyEditView fichePropertyEditView26 = ViewBindings.findChildViewById(view, R.id.fch_warranty_code);
                                                                                                                                                if (null != fichePropertyEditView26) {
                                                                                                                                                    i2 = R.id.fch_zip_code;
                                                                                                                                                    final FichePropertyEditView fichePropertyEditView27 = ViewBindings.findChildViewById(view, R.id.fch_zip_code);
                                                                                                                                                    if (null != fichePropertyEditView27) {
                                                                                                                                                        i2 = R.id.img_customer;
                                                                                                                                                        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_customer);
                                                                                                                                                        if (null != imageView) {
                                                                                                                                                            i2 = R.id.ln_customer_header;
                                                                                                                                                            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customer_header);
                                                                                                                                                            if (null != linearLayout) {
                                                                                                                                                                i2 = R.id.ln_customer_incharge_header;
                                                                                                                                                                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_customer_incharge_header);
                                                                                                                                                                if (null != linearLayout2) {
                                                                                                                                                                    i2 = R.id.ln_fiche_property;
                                                                                                                                                                    final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_fiche_property);
                                                                                                                                                                    if (null != linearLayout3) {
                                                                                                                                                                        i2 = R.id.ln_personal_company;
                                                                                                                                                                        final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_personal_company);
                                                                                                                                                                        if (null != linearLayout4) {
                                                                                                                                                                            i2 = R.id.personalCompanyDivider;
                                                                                                                                                                            final View findChildViewById = ViewBindings.findChildViewById(view, R.id.personalCompanyDivider);
                                                                                                                                                                            if (null != findChildViewById) {
                                                                                                                                                                                i2 = R.id.txt_fch_incharge_tel;
                                                                                                                                                                                final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_fch_incharge_tel);
                                                                                                                                                                                if (null != textView) {
                                                                                                                                                                                    return new FragmentCustomerNewBinding((LinearLayout) view, editText, editText2, fichePropertyEditView, fichePropertyEditView2, fichePropertyTextView, fichePropertyTextView2, fichePropertyEditView3, fichePropertyEditView4, fichePropertyEditView5, fichePropertyTextView3, fichePropertyEditView6, fichePropertyEditView7, fichePropertyEditView8, fichePropertyEditView9, fichePropertyEditView10, fichePropertyEditView11, fichePropertyEditView12, fichePropertyEditView13, fichePropertyEditView14, fichePropertyEditView15, fichePropertyTextView4, fichePropertyTextView5, checkBox, fichePropertyEditView16, fichePropertyEditView17, fichePropertyTextView6, fichePropertyEditView18, fichePropertyEditView19, fichePropertyEditView20, fichePropertyEditView21, fichePropertyEditView22, fichePropertyEditView23, fichePropertyEditView24, fichePropertyEditView25, fichePropertyEditView26, fichePropertyEditView27, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, findChildViewById, textView);
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
