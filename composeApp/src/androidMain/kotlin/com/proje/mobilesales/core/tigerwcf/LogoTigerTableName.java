package com.proje.mobilesales.core.tigerwcf;

import android.os.Parcel;
import android.os.Parcelable;



public class LogoTigerTableName implements Parcelable {
    public static final Parcelable.Creator<LogoTigerTableName> CREATOR = new Parcelable.Creator<LogoTigerTableName>() {
        
        public LogoTigerTableName createFromParcel(Parcel parcel) {
            return new LogoTigerTableName(parcel);
        }

        
        
        public LogoTigerTableName[] newArray(int i2) {
            return new LogoTigerTableName[i2];
        }
    };
    public String ADDTAX;
    public String ADDTAXLINE;
    public String BANKACC;
    public String BNCARD;
    public String BNFICHE;
    public String BNFLINE;
    public String CAMPAIGN;
    public String CAPIDEPT;
    public String CAPIDIV;
    public String CAPIFACTORY;
    public String CAPIFIRM;
    public String CAPIWHOUSE;
    public String CLCARD;
    public String CLFICHE;
    public String CLFLINE;
    public String CLRNUMS;
    public String CLTOTFIL;
    public String CMPGNLINE;
    public String CRDACREF;
    public String CSCARD;
    public String CSROLL;
    public String CSTRANS;
    public String CURRENCYLIST;
    public String DAILYEXCHANGES;
    public String DECARDS;
    public String DISTORD;
    public String DISTORDLINE;
    public String DISTVEHICLE;
    public String EARCHIVEDET;
    public String EINVOICEDET;
    public String EMFICHE;
    public String EMUHACC;
    public String FICHESTATUS;
    public String FIRMDOC;
    public String FIRMPARAMS;
    public String GNTOTCL;
    public String GNTOTCLV;
    public String INVOICE;
    public String ITEMS;
    public String ITMCLSAS;
    public String ITMUNITA;
    public String KSCARD;
    public String KSLINES;
    public String LG_EXCHANGE;
    public String LG_SLSMAN;
    public String LNGEXCSETS;
    public String LOCATION;
    public String L_CURRENCYLIST;
    public String MARK;
    public String ORFICHE;
    public String ORFLINE;
    public String PAYLINES;
    public String PAYPLANS;
    public String PAYTRANS;
    public String PRCLIST;
    public String PRCLSTDIV;
    public String PROJECT;
    public String PURCHOFFER;
    public String ROUTE;
    public String ROUTETRS;
    public String SERILOTN;
    public String SHIPINFO;
    public String SHPAGENT;
    public String SHPTYPES;
    public String SLSCLREL;
    public String SLSMAN;
    public String SLTRANS;
    public String SPECODES;
    public String SRVCARD;
    public String SRVTOT;
    public String SRVUNITA;
    public String STCOMPLN;
    public String STFICHE;
    public String STINVENS;
    public String STINVTOT;
    public String STLINE;
    public String SUPPASGN;
    public String TRADGRP;
    public String UNITBARCODE;
    public String UNITSETL;
    public String VARIANT;
    public String VRNTINVTOT;
    public int describeContents() {
        return 0;
    }

    public LogoTigerTableName(String str, String str2, String str3) {
        this.ITEMS = str + "LG_" + str2 + "_ITEMS";
        this.MARK = str + "LG_" + str2 + "_MARK";
        this.STINVTOT = str + "LV_" + str2 + "_" + str3 + "_STINVTOT";
        this.ORFICHE = str + "LG_" + str2 + "_" + str3 + "_ORFICHE";
        this.INVOICE = str + "LG_" + str2 + "_" + str3 + "_INVOICE";
        this.STFICHE = str + "LG_" + str2 + "_" + str3 + "_STFICHE";
        this.CLFICHE = str + "LG_" + str2 + "_" + str3 + "_CLFICHE";
        this.CSROLL = str + "LG_" + str2 + "_" + str3 + "_CSROLL";
        this.KSLINES = str + "LG_" + str2 + "_" + str3 + "_KSLINES";
        String sb = str +
                "LG_" +
                str2 +
                "_PROJECT";
        this.PROJECT = sb;
        this.SLSMAN = "LG_SLSMAN";
        this.ROUTE = str + "LG_" + str2 + "_ROUTE";
        this.SLSCLREL = str + "LG_" + str2 + "_SLSCLREL";
        this.UNITBARCODE = str + "LG_" + str2 + "_UNITBARCODE";
        this.STCOMPLN = str + "LG_" + str2 + "_STCOMPLN";
        this.ITMUNITA = str + "LG_" + str2 + "_ITMUNITA";
        this.UNITSETL = str + "LG_" + str2 + "_UNITSETL";
        this.VARIANT = str + "LG_" + str2 + "_VARIANT";
        this.SRVCARD = str + "LG_" + str2 + "_SRVCARD";
        this.SRVTOT = str + "LG_" + str2 + "_" + str3 + "_SRVTOT";
        this.CAPIWHOUSE = "L_CAPIWHOUSE";
        this.CAPIDEPT = "L_CAPIDEPT";
        this.CAPIDIV = "L_CAPIDIV";
        this.CAPIFACTORY = "L_CAPIFACTORY";
        this.SPECODES = str + "LG_" + str2 + "_SPECODES";
        this.PAYPLANS = str + "LG_" + str2 + "_PAYPLANS";
        this.PAYLINES = str + "LG_" + str2 + "_PAYLINES";
        this.TRADGRP = "L_TRADGRP";
        this.SHPTYPES = "L_SHPTYPES";
        this.SHPAGENT = "L_SHPAGENT";
        this.BNCARD = str + "LG_" + str2 + "_BNCARD";
        this.BANKACC = str + "LG_" + str2 + "_BANKACC";
        this.KSCARD = str + "LG_" + str2 + "_KSCARD";
        this.DAILYEXCHANGES = "L_DAILYEXCHANGES";
        this.CURRENCYLIST = "L_CURRENCYLIST";
        this.CLRNUMS = str + "LG_" + str2 + "_" + str3 + "_CLRNUMS";
        String sb2 = str +
                "LG_" +
                str2 +
                "_CRDACREF";
        this.CRDACREF = sb2;
        this.EMUHACC = str + "LG_" + str2 + "_EMUHACC";
        this.LNGEXCSETS = str + "LG_" + str2 + "_LNGEXCSETS";
        this.CLTOTFIL = str + "LV_" + str2 + "_" + str3 + "_CLTOTFIL";
        String sb3 = str +
                "LG_" +
                str2 +
                "_CLCARD";
        this.CLCARD = sb3;
        this.SRVUNITA = str + "LG_" + str2 + "_SRVUNITA";
        this.PRCLIST = str + "LG_" + str2 + "_PRCLIST";
        this.DECARDS = str + "LG_" + str2 + "_DECARDS";
        this.SHIPINFO = str + "LG_" + str2 + "_SHIPINFO";
        this.FIRMPARAMS = "L_FIRMPARAMS";
        this.CLFLINE = str + "LG_" + str2 + "_" + str3 + "_CLFLINE";
        this.ORFLINE = str + "LG_" + str2 + "_" + str3 + "_ORFLINE";
        this.STLINE = str + "LG_" + str2 + "_" + str3 + "_STLINE";
        this.SLTRANS = str + "LG_" + str2 + "_" + str3 + "_SLTRANS";
        this.SERILOTN = str + "LG_" + str2 + "_" + str3 + "_SERILOTN";
        this.EMFICHE = str + "LG_" + str2 + "_" + str3 + "_EMFICHE";
        String sb4 = str +
                "LG_" +
                str2 +
                "_PURCHOFFER";
        this.PURCHOFFER = sb4;
        this.L_CURRENCYLIST = "L_CURRENCYLIST";
        this.CAPIFIRM = "L_CAPIFIRM";
        this.ROUTETRS = str + "LG_" + str2 + "_ROUTETRS";
        this.PAYTRANS = str + "LG_" + str2 + "_" + str3 + "_PAYTRANS";
        this.BNFLINE = str + "LG_" + str2 + "_" + str3 + "_BNFLINE";
        this.BNFICHE = str + "LG_" + str2 + "_" + str3 + "_BNFICHE";
        this.LG_SLSMAN = "LG_SLSMAN";
        this.GNTOTCL = str + "LG_" + str2 + "_" + str3 + "_GNTOTCL";
        this.GNTOTCLV = str + "LV_" + str2 + "_" + str3 + "_GNTOTCL";
        this.CSCARD = str + "LG_" + str2 + "_" + str3 + "_CSCARD";
        this.CSTRANS = str + "LG_" + str2 + "_" + str3 + "_CSTRANS";
        String sb5 = str +
                "LG_" +
                str2 +
                "_SUPPASGN";
        this.SUPPASGN = sb5;
        this.LG_EXCHANGE = str + "LG_EXCHANGE_" + str2;
        this.CAMPAIGN = str + "LG_" + str2 + "_CAMPAIGN";
        this.CMPGNLINE = str + "LG_" + str2 + "_CMPGNLINE";
        this.STINVENS = str + "LV_" + str2 + "_" + str3 + "_STINVENS";
        String sb6 = str +
                "LG_" +
                str2 +
                "_FIRMDOC";
        this.FIRMDOC = sb6;
        this.FICHESTATUS = str + "LG_" + str2 + "_FICHESTATUS";
        this.DISTORD = str + "LG_" + str2 + "_" + str3 + "_DISTORD";
        this.DISTORDLINE = str + "LG_" + str2 + "_" + str3 + "_DISTORDLINE";
        String sb7 = str +
                "LG_" +
                str2 +
                "_DISTVEHICLE";
        this.DISTVEHICLE = sb7;
        this.ADDTAX = str + "LG_" + str2 + "_ADDTAX";
        this.ADDTAXLINE = str + "LG_" + str2 + "_ADDTAXLINE";
        this.LOCATION = str + "LG_" + str2 + "_LOCATION";
        this.ITMCLSAS = str + "LG_" + str2 + "_ITMCLSAS";
        this.VRNTINVTOT = str + "LV_" + str2 + "_" + str3 + "_VRNTINVTOT";
        this.EARCHIVEDET = str + "LG_" + str2 + "_" + str3 + "_EARCHIVEDET";
        this.EINVOICEDET = str + "LG_" + str2 + "_" + str3 + "_EINVOICEDET";
        String sb8 = str +
                "LG_" +
                str2 +
                "_PRCLSTDIV";
        this.PRCLSTDIV = sb8;
    }

    protected LogoTigerTableName(Parcel parcel) {
        this.ITEMS = parcel.readString();
        this.MARK = parcel.readString();
        this.STINVTOT = parcel.readString();
        this.ORFICHE = parcel.readString();
        this.INVOICE = parcel.readString();
        this.STFICHE = parcel.readString();
        this.CLFICHE = parcel.readString();
        this.CSROLL = parcel.readString();
        this.KSLINES = parcel.readString();
        this.PROJECT = parcel.readString();
        this.SLSMAN = parcel.readString();
        this.ROUTE = parcel.readString();
        this.SLSCLREL = parcel.readString();
        this.UNITBARCODE = parcel.readString();
        this.STCOMPLN = parcel.readString();
        this.ITMUNITA = parcel.readString();
        this.UNITSETL = parcel.readString();
        this.VARIANT = parcel.readString();
        this.SRVCARD = parcel.readString();
        this.SRVTOT = parcel.readString();
        this.CAPIWHOUSE = parcel.readString();
        this.CAPIDEPT = parcel.readString();
        this.CAPIDIV = parcel.readString();
        this.CAPIFACTORY = parcel.readString();
        this.SPECODES = parcel.readString();
        this.PAYPLANS = parcel.readString();
        this.PAYLINES = parcel.readString();
        this.TRADGRP = parcel.readString();
        this.SHPTYPES = parcel.readString();
        this.SHPAGENT = parcel.readString();
        this.BNCARD = parcel.readString();
        this.BANKACC = parcel.readString();
        this.KSCARD = parcel.readString();
        this.DAILYEXCHANGES = parcel.readString();
        this.CURRENCYLIST = parcel.readString();
        this.CLRNUMS = parcel.readString();
        this.CRDACREF = parcel.readString();
        this.EMUHACC = parcel.readString();
        this.CLTOTFIL = parcel.readString();
        this.CLCARD = parcel.readString();
        this.SRVUNITA = parcel.readString();
        this.PRCLIST = parcel.readString();
        this.DECARDS = parcel.readString();
        this.SHIPINFO = parcel.readString();
        this.FIRMPARAMS = parcel.readString();
        this.CLFLINE = parcel.readString();
        this.ORFLINE = parcel.readString();
        this.STLINE = parcel.readString();
        this.SLTRANS = parcel.readString();
        this.SERILOTN = parcel.readString();
        this.EMFICHE = parcel.readString();
        this.PURCHOFFER = parcel.readString();
        this.L_CURRENCYLIST = parcel.readString();
        this.CAPIFIRM = parcel.readString();
        this.ROUTETRS = parcel.readString();
        this.PAYTRANS = parcel.readString();
        this.BNFLINE = parcel.readString();
        this.BNFICHE = parcel.readString();
        this.LG_SLSMAN = parcel.readString();
        this.LNGEXCSETS = parcel.readString();
        this.GNTOTCL = parcel.readString();
        this.GNTOTCLV = parcel.readString();
        this.CSCARD = parcel.readString();
        this.CSTRANS = parcel.readString();
        this.SUPPASGN = parcel.readString();
        this.LG_EXCHANGE = parcel.readString();
        this.CAMPAIGN = parcel.readString();
        this.CMPGNLINE = parcel.readString();
        this.STINVENS = parcel.readString();
        this.FIRMDOC = parcel.readString();
        this.FICHESTATUS = parcel.readString();
        this.DISTORD = parcel.readString();
        this.DISTORDLINE = parcel.readString();
        this.DISTVEHICLE = parcel.readString();
        this.ADDTAX = parcel.readString();
        this.ADDTAXLINE = parcel.readString();
        this.LOCATION = parcel.readString();
        this.ITMCLSAS = parcel.readString();
        this.VRNTINVTOT = parcel.readString();
        this.EARCHIVEDET = parcel.readString();
        this.EINVOICEDET = parcel.readString();
        this.PRCLSTDIV = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.ITEMS);
        parcel.writeString(this.MARK);
        parcel.writeString(this.STINVTOT);
        parcel.writeString(this.ORFICHE);
        parcel.writeString(this.INVOICE);
        parcel.writeString(this.STFICHE);
        parcel.writeString(this.CLFICHE);
        parcel.writeString(this.CSROLL);
        parcel.writeString(this.KSLINES);
        parcel.writeString(this.PROJECT);
        parcel.writeString(this.SLSMAN);
        parcel.writeString(this.ROUTE);
        parcel.writeString(this.SLSCLREL);
        parcel.writeString(this.UNITBARCODE);
        parcel.writeString(this.STCOMPLN);
        parcel.writeString(this.ITMUNITA);
        parcel.writeString(this.UNITSETL);
        parcel.writeString(this.VARIANT);
        parcel.writeString(this.SRVCARD);
        parcel.writeString(this.SRVTOT);
        parcel.writeString(this.CAPIWHOUSE);
        parcel.writeString(this.CAPIDEPT);
        parcel.writeString(this.CAPIDIV);
        parcel.writeString(this.CAPIFACTORY);
        parcel.writeString(this.SPECODES);
        parcel.writeString(this.PAYPLANS);
        parcel.writeString(this.PAYLINES);
        parcel.writeString(this.TRADGRP);
        parcel.writeString(this.SHPTYPES);
        parcel.writeString(this.SHPAGENT);
        parcel.writeString(this.BNCARD);
        parcel.writeString(this.BANKACC);
        parcel.writeString(this.KSCARD);
        parcel.writeString(this.DAILYEXCHANGES);
        parcel.writeString(this.CURRENCYLIST);
        parcel.writeString(this.CLRNUMS);
        parcel.writeString(this.CRDACREF);
        parcel.writeString(this.EMUHACC);
        parcel.writeString(this.CLTOTFIL);
        parcel.writeString(this.CLCARD);
        parcel.writeString(this.SRVUNITA);
        parcel.writeString(this.PRCLIST);
        parcel.writeString(this.DECARDS);
        parcel.writeString(this.SHIPINFO);
        parcel.writeString(this.FIRMPARAMS);
        parcel.writeString(this.CLFLINE);
        parcel.writeString(this.ORFLINE);
        parcel.writeString(this.STLINE);
        parcel.writeString(this.SLTRANS);
        parcel.writeString(this.SERILOTN);
        parcel.writeString(this.EMFICHE);
        parcel.writeString(this.PURCHOFFER);
        parcel.writeString(this.L_CURRENCYLIST);
        parcel.writeString(this.CAPIFIRM);
        parcel.writeString(this.ROUTETRS);
        parcel.writeString(this.PAYTRANS);
        parcel.writeString(this.BNFLINE);
        parcel.writeString(this.BNFICHE);
        parcel.writeString(this.LG_SLSMAN);
        parcel.writeString(this.LNGEXCSETS);
        parcel.writeString(this.GNTOTCL);
        parcel.writeString(this.GNTOTCLV);
        parcel.writeString(this.CSCARD);
        parcel.writeString(this.CSTRANS);
        parcel.writeString(this.SUPPASGN);
        parcel.writeString(this.LG_EXCHANGE);
        parcel.writeString(this.CAMPAIGN);
        parcel.writeString(this.CMPGNLINE);
        parcel.writeString(this.STINVENS);
        parcel.writeString(this.FIRMDOC);
        parcel.writeString(this.FICHESTATUS);
        parcel.writeString(this.DISTORD);
        parcel.writeString(this.DISTORDLINE);
        parcel.writeString(this.DISTVEHICLE);
        parcel.writeString(this.ADDTAX);
        parcel.writeString(this.ADDTAXLINE);
        parcel.writeString(this.LOCATION);
        parcel.writeString(this.ITMCLSAS);
        parcel.writeString(this.VRNTINVTOT);
        parcel.writeString(this.EARCHIVEDET);
        parcel.writeString(this.EINVOICEDET);
        parcel.writeString(this.PRCLSTDIV);
    }
}
