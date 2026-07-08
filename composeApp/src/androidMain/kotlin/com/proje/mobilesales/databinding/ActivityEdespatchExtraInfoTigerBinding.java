package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.FichePropertyEditView;
 
public final class ActivityEdespatchExtraInfoTigerBinding implements ViewBinding {
    public final FichePropertyEditView fpeCity;
    public final FichePropertyEditView fpeCountry;
    public final FichePropertyEditView fpeCounty;
    public final FichePropertyEditView fpeFirstDriverDescription;
    public final FichePropertyEditView fpeFirstDriverIdentityNr;
    public final FichePropertyEditView fpeFirstDriverName;
    public final FichePropertyEditView fpeFirstDriverSurname;
    public final FichePropertyEditView fpeFirstTrailerPlate;
    public final FichePropertyEditView fpePlateNr;
    public final FichePropertyEditView fpePostalCode;
    public final FichePropertyEditView fpeTaxNr;
    public final FichePropertyEditView fpeTitle;
    private final LinearLayout rootView;
    public final TextView txtFirstDriver;
    public final TextView txtFirstTrailerInfo;
    private ActivityEdespatchExtraInfoTigerBinding( final LinearLayout linearLayout,  final FichePropertyEditView fichePropertyEditView,  final FichePropertyEditView fichePropertyEditView2,  final FichePropertyEditView fichePropertyEditView3,  final FichePropertyEditView fichePropertyEditView4,  final FichePropertyEditView fichePropertyEditView5,  final FichePropertyEditView fichePropertyEditView6,  final FichePropertyEditView fichePropertyEditView7,  final FichePropertyEditView fichePropertyEditView8,  final FichePropertyEditView fichePropertyEditView9,  final FichePropertyEditView fichePropertyEditView10,  final FichePropertyEditView fichePropertyEditView11,  final FichePropertyEditView fichePropertyEditView12,  final TextView textView,  final TextView textView2) {
        rootView = linearLayout;
        fpeCity = fichePropertyEditView;
        fpeCountry = fichePropertyEditView2;
        fpeCounty = fichePropertyEditView3;
        fpeFirstDriverDescription = fichePropertyEditView4;
        fpeFirstDriverIdentityNr = fichePropertyEditView5;
        fpeFirstDriverName = fichePropertyEditView6;
        fpeFirstDriverSurname = fichePropertyEditView7;
        fpeFirstTrailerPlate = fichePropertyEditView8;
        fpePlateNr = fichePropertyEditView9;
        fpePostalCode = fichePropertyEditView10;
        fpeTaxNr = fichePropertyEditView11;
        fpeTitle = fichePropertyEditView12;
        txtFirstDriver = textView;
        txtFirstTrailerInfo = textView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityEdespatchExtraInfoTigerBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityEdespatchExtraInfoTigerBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityEdespatchExtraInfoTigerBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_edespatch_extra_info_tiger, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityEdespatchExtraInfoTigerBinding.bind(inflate);
    }
    public static ActivityEdespatchExtraInfoTigerBinding bind( final View view) {
        int i2 = R.id.fpe_City;
        final FichePropertyEditView fichePropertyEditView = ViewBindings.findChildViewById(view, R.id.fpe_City);
        if (null != fichePropertyEditView) {
            i2 = R.id.fpe_Country;
            final FichePropertyEditView fichePropertyEditView2 = ViewBindings.findChildViewById(view, R.id.fpe_Country);
            if (null != fichePropertyEditView2) {
                i2 = R.id.fpe_County;
                final FichePropertyEditView fichePropertyEditView3 = ViewBindings.findChildViewById(view, R.id.fpe_County);
                if (null != fichePropertyEditView3) {
                    i2 = R.id.fpe_firstDriverDescription;
                    final FichePropertyEditView fichePropertyEditView4 = ViewBindings.findChildViewById(view, R.id.fpe_firstDriverDescription);
                    if (null != fichePropertyEditView4) {
                        i2 = R.id.fpe_firstDriverIdentityNr;
                        final FichePropertyEditView fichePropertyEditView5 = ViewBindings.findChildViewById(view, R.id.fpe_firstDriverIdentityNr);
                        if (null != fichePropertyEditView5) {
                            i2 = R.id.fpe_firstDriverName;
                            final FichePropertyEditView fichePropertyEditView6 = ViewBindings.findChildViewById(view, R.id.fpe_firstDriverName);
                            if (null != fichePropertyEditView6) {
                                i2 = R.id.fpe_firstDriverSurname;
                                final FichePropertyEditView fichePropertyEditView7 = ViewBindings.findChildViewById(view, R.id.fpe_firstDriverSurname);
                                if (null != fichePropertyEditView7) {
                                    i2 = R.id.fpe_firstTrailerPlate;
                                    final FichePropertyEditView fichePropertyEditView8 = ViewBindings.findChildViewById(view, R.id.fpe_firstTrailerPlate);
                                    if (null != fichePropertyEditView8) {
                                        i2 = R.id.fpe_plateNr;
                                        final FichePropertyEditView fichePropertyEditView9 = ViewBindings.findChildViewById(view, R.id.fpe_plateNr);
                                        if (null != fichePropertyEditView9) {
                                            i2 = R.id.fpe_postalCode;
                                            final FichePropertyEditView fichePropertyEditView10 = ViewBindings.findChildViewById(view, R.id.fpe_postalCode);
                                            if (null != fichePropertyEditView10) {
                                                i2 = R.id.fpe_TaxNr;
                                                final FichePropertyEditView fichePropertyEditView11 = ViewBindings.findChildViewById(view, R.id.fpe_TaxNr);
                                                if (null != fichePropertyEditView11) {
                                                    i2 = R.id.fpe_Title;
                                                    final FichePropertyEditView fichePropertyEditView12 = ViewBindings.findChildViewById(view, R.id.fpe_Title);
                                                    if (null != fichePropertyEditView12) {
                                                        i2 = R.id.txtFirstDriver;
                                                        final TextView textView = ViewBindings.findChildViewById(view, R.id.txtFirstDriver);
                                                        if (null != textView) {
                                                            i2 = R.id.txtFirstTrailerInfo;
                                                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txtFirstTrailerInfo);
                                                            if (null != textView2) {
                                                                return new ActivityEdespatchExtraInfoTigerBinding((LinearLayout) view, fichePropertyEditView, fichePropertyEditView2, fichePropertyEditView3, fichePropertyEditView4, fichePropertyEditView5, fichePropertyEditView6, fichePropertyEditView7, fichePropertyEditView8, fichePropertyEditView9, fichePropertyEditView10, fichePropertyEditView11, fichePropertyEditView12, textView, textView2);
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
