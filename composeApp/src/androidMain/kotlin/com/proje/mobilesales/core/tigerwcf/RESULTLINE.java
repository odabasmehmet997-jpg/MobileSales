package com.proje.mobilesales.core.tigerwcf;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root

public class RESULTLINE implements Serializable, Parcelable {
    public static final Parcelable.Creator<RESULTLINE> CREATOR = new Parcelable.Creator<RESULTLINE>() { // from class: com.proje.mobilesales.core.tigerwcf.RESULTLINE.1
        
        
        public RESULTLINE createFromParcel(Parcel parcel) {
            return new RESULTLINE(parcel);
        }

        
        
        public RESULTLINE[] newArray(int i2) {
            return new RESULTLINE[i2];
        }
    };
    private static final long serialVersionUID = 1;

    @Element(required = false)
    public String ACCEPTEINV;

    @Element(required = false)
    public String ACCRISKLIMIT;

    @Element(required = false)
    public String ACCRISKTOTAL;

    @Element(required = false)
    public String ACCRSKBLNCED;

    @Element(required = false)
    public String ADDR1;

    @Element(required = false)
    public String ADDR2;

    @Element(required = false)
    public String ADDRESS;

    @Element(required = false)
    public String AMBAR;

    @Element(required = false)
    public String AMOUNT;

    @Element(required = false)
    public String BACODE;

    @Element(required = false)
    public String BADEFINITION;

    @Element(required = false)
    public String BAKIYE;

    @Element(required = false)
    public String BAKIYE_FLOAT;

    @Element(required = false)
    public String BANKNAME;

    @Element(required = false)
    public String BANKREF;

    @Element(required = false)
    public String BARCODE;

    @Element(required = false)
    public String BARCODESHIFT;

    @Element(required = false)
    public String BASKISONU_HEIGHT;

    @Element(required = false)
    public String BASLANGIC_Y;

    @Element(required = false)
    public String BASLIK_HEIGHT;

    @Element(required = false)
    public String BCODE;

    @Element(required = false)
    public String BDEFINITION;

    @Element(required = false)
    public String BEFOREBALANCE;

    @Element(required = false)
    public String BEGDATE;

    @Element(required = false)
    public String BEGDATEINT;

    @Element(required = false)
    public String BNACCOUNTNO;

    @Element(required = false)
    public String BNBRANCHNO;

    @Element(required = false)
    public String BOLUM;

    @Element(required = false)
    public String Barkod;

    @Element(required = false)
    public String CARDREF;

    @Element(required = false)
    public String CARDTYPE;

    @Element(required = false)
    public String CARIBAKIYE;

    @Element(required = false)
    public String CARISONBAKIYE;

    @Element(required = false)
    public String CCODE;

    @Element(required = false)
    public String CCODE2;

    @Element(required = false)
    public String CCURRENCY;

    @Element(required = false)
    public String CCYPHCODE;

    @Element(required = false)
    public String CDEFINITION;

    @Element(required = false)
    public String CHOICEPROD;

    @Element(required = false)
    public String CITY;

    @Element(required = false)
    public String CLCARDREF;

    @Element(required = false)
    public String CLCODE;

    @Element(required = false)
    public String CLCYPHCODE;

    @Element(required = false)
    public String CLIENTCODE;

    @Element(required = false)
    public String CLIENTREF;

    @Element(required = false)
    public String CLREF;

    @Element(required = false)
    public String CLSPECODE;

    @Element(required = false)
    public String CLSPECODE2;

    @Element(required = false)
    public String CLSPECODE3;

    @Element(required = false)
    public String CLSPECODE4;

    @Element(required = false)
    public String CLSPECODE5;

    @Element(required = false)
    public String CMDATE;

    @Element(required = false)
    public String CNAME;

    @Element(required = false)
    public String CNAME2;

    @Element(required = false)
    public String CODE;

    @Element(required = false)
    public String CODETYPE;

    @Element(required = false)
    public String COLORID;

    @Element(required = false)
    public String CONVFACT1;

    @Element(required = false)
    public String CONVFACT2;

    @Element(required = false)
    public String COSTGRP;

    @Element(required = false)
    public String CPRICE;

    @Element(required = false)
    public String CREDIT;

    @Element(required = false)
    public String CREDITACCOUNT;

    @Element(required = false)
    public String CREDIT_FLOAT;

    @Element(required = false)
    public String CSPECODE;

    @Element(required = false)
    public String CSTCSCIRORISKLIMIT;

    @Element(required = false)
    public String CSTCSCIRORISKTOTAL;

    @Element(required = false)
    public String CSTCSCIRORSKBLNCED;

    @Element(required = false)
    public String CSTCSOWNRISKTOTAL;

    @Element(required = false)
    public String CSTCSRISKLIMIT;

    @Element(required = false)
    public String CSTCSRSKBLNCED;

    @Element(required = false)
    public String CTRADINGGRP;

    @Element(required = false)
    public String CURCODE;

    @Element(required = false)
    public String CURNR;

    @Element(required = false)
    public String CURRCODE;

    @Element(required = false)
    public String CURRTYPE;

    @Element(required = false)
    public String CYPHCODE;

    @Element(required = false)
    public String DATE;

    @Element(required = false)
    public String DATEINT;

    @Element(required = false)
    public String DATE_;

    @Element(required = false)
    public String DCODE;

    @Element(required = false)
    public String DEBIT;

    @Element(required = false)
    public String DEBITACCOUNT;

    @Element(required = false)
    public String DEBIT_FLOAT;

    @Element(required = false)
    public String DEF;

    @Element(required = false)
    public String DEFFIELD1;

    @Element(required = false)
    public String DEFFIELD10;

    @Element(required = false)
    public String DEFFIELD2;

    @Element(required = false)
    public String DEFFIELD3;

    @Element(required = false)
    public String DEFFIELD4;

    @Element(required = false)
    public String DEFFIELD5;

    @Element(required = false)
    public String DEFFIELD6;

    @Element(required = false)
    public String DEFFIELD7;

    @Element(required = false)
    public String DEFFIELD8;

    @Element(required = false)
    public String DEFFIELD9;

    @Element(required = false)
    public String DEFINITION2;

    @Element(required = false)
    public String DEFINITION_;

    @Element(required = false)
    public String DEFPRODAMOUNT;

    @Element(required = false)
    public String DEFSALESFICHEID;

    @Element(required = false)
    public String DEFVAL;

    @Element(required = false)
    public String DELETED;

    @Element(required = false)
    public String DELIVERYFIRM;

    @Element(required = false)
    public String DELIVERYMETHOD;

    @Element(required = false)
    public String DELVRYCODE;

    @Element(required = false)
    public String DESC_;

    @Element(required = false)
    public String DESIGN;

    @Element(required = false)
    public String DESPRISKLIMIT;

    @Element(required = false)
    public String DESPRISKLIMITSUG;

    @Element(required = false)
    public String DESPRISKTOTAL;

    @Element(required = false)
    public String DESPRISKTOTALSUG;

    @Element(required = false)
    public String DESPRSKBLNCED;

    @Element(required = false)
    public String DESPRSKBLNCEDSUG;

    @Element(required = false)
    public String DETAY_HEIGHT;

    @Element(required = false)
    public String DETAY_SATIRSAYISI;

    @Element(required = false)
    public String DISCPER;

    @Element(required = false)
    public String DISCRATE;

    @Element(required = false)
    public String DISTRICT;

    @Element(required = false)
    public String DIVISNR;

    @Element(required = false)
    public String DNAME;

    @Element(required = false)
    public int DOCID;

    @Element(required = false)
    public String DOCODE;

    @Element(required = false)
    public int DOCREF;

    @Element(required = false)
    public String DORESERVE;

    @Element(required = false)
    public String DOSHIP;

    @Element(required = false)
    public String DTYPE;

    @Element(required = false)
    public String DUEDATE;

    @Element(required = false)
    public String EDINO;

    @Element(required = false)
    public String EMAILADDR;

    @Element(required = false)
    public String ENDDATE;

    @Element(required = false)
    public String ENDDATEINT;

    @Element(required = false)
    public String ENTERPRODAMOUNT;

    @Element(required = false)
    public String ENTID;

    @Element(required = false)
    public String ENTPRICE;

    @Element(required = false)
    public String FABRIKA;

    @Element(required = false)
    public String FACTNR;

    @Element(required = false)
    public String FAXNR;

    @Element(required = false)
    public String FCODE;

    @Element(required = false)
    public String FICHENO;

    @Element(required = false)
    public String FIELDCONT;

    @Element(required = false)
    public int FIELDID;

    @Element(required = false)
    public String FIRMNR;

    @Element(required = false)
    public String FNAME;

    @Element(required = false)
    public String FORMGROUP;

    @Element(required = false)
    public String FORMULA;

    @Element(required = false)
    public String FTIME;

    @Element(required = false)
    public String GATTRIB;

    @Element(required = false)
    public String GENEXP1;

    @Element(required = false)
    public String GENEXP2;

    @Element(required = false)
    public String GENEXP3;

    @Element(required = false)
    public String GENEXP4;

    @Element(required = false)
    public String GLOBTRANS;

    @Element(required = false)
    public String GROSSTOTAL;

    @Element(required = false)
    public String GROSSWEIGHT;

    @Element(required = false)
    public String GROUP_;

    @Element(required = false)
    public String GTIPCODE;

    @Element(required = false)
    public String GUID;

    @Element(required = false)
    public String HEIGHT;

    @Element(required = false)
    public String ICUSTSUPCODE;

    @Element(required = false)
    public String ICUSTSUPNAME;

    /* renamed from: ID */
    @Element(required = false)
    public String f1211ID;

    @Element(required = false)
    public String INCHARGE;

    @Element(required = false)
    public String INCHARGE2;

    @Element(required = false)
    public String INCHARGE3;

    @Element(required = false)
    public String INCVAT;

    @Element(required = false)
    public String INDIRIM;

    @Element(required = false)
    public String INVENNO;

    @Element(required = false)
    public String INVOICEREF;

    @Element(required = false)
    public String IRSDATE;

    @Element(required = false)
    public String IRSNO;

    @Element(required = false)
    public String ISMUST;

    @Element(required = false)
    public String ISPERSCOMP;

    @Element(required = false)
    public String ISREAD;

    @Element(required = false)
    public String ISSLCTUNIT;

    @Element(required = false)
    public String ISTRANSFER;

    @Element(required = false)
    public String ISVARYANT;

    @Element(required = false)
    public String ISYERI;

    @Element(required = false)
    public String ITEMREF;

    @Element(required = false)
    public String KASA;

    @Element(required = false)
    public String KEFIL;

    @Element(required = false)
    public int LANGID;

    @Element(required = false)
    public String LATITUDE;

    @Element(required = false)
    public String LATITUTE;

    @Element(required = false)
    public String LENGTH;

    @Element(required = false)
    public String LINEEXP;

    @Element(required = false)
    public String LINENO_;

    @Element(required = false)
    public String LINENR;

    @Element(required = false)
    public String LINETYPE;

    @Element(required = false)
    public String LOGICALREF;

    @Element(required = false)
    public String LONGITUDE;

    @Element(required = false)
    public String LONGTITUDE;

    @Element(required = false)
    public String MAINCREF;

    @Element(required = false)
    public String MARK;

    @Element(required = false)
    public String MARKREF;

    @Element(required = false)
    public String MAXAMOUNT;

    @Element(required = false)
    public String MCODE;

    @Element(required = false)
    public String MINAMOUNT;

    @Element(required = false)
    public String MINSTOCK;

    @Element(required = false)
    public String MSGDATE;

    @Element(required = false)
    public String MSGTYPE;

    @Element(required = false)
    public String MTRLCLAS;

    @Element(required = false)
    public String MUHABIR;

    @Element(required = false)
    public String MYCSRISKLIMIT;

    @Element(required = false)
    public String MYCSRISKTOTAL;

    @Element(required = false)
    public String MYCSRSKBLNCED;

    @Element(required = false)
    public String NAME;

    @Element(required = false)
    public String NAME2;

    @Element(required = false)
    public String NETTOTAL;

    @Element(required = false)
    public String NEWSERINO;

    @Element(required = false)
    public String NOTE;

    /* renamed from: NR */
    @Element(required = false)
    public String f1212NR;

    @Element(required = false)
    public String ODEMEPLAN;

    @Element(required = false)
    public String ONHAND;

    @Element(required = false)
    public String ORDFICHEREF;

    @Element(required = false)
    public String ORDRISKLIMIT;

    @Element(required = false)
    public String ORDRISKLIMITSUGG;

    @Element(required = false)
    public String ORDRISKTOTAL;

    @Element(required = false)
    public String ORDRISKTOTALSUGG;

    @Element(required = false)
    public String ORDRSKBLNCED;

    @Element(required = false)
    public String ORDRSKBLNCEDSUG;

    @Element(required = false)
    public String ORFICHEREF;

    @Element(required = false)
    public int ORGLOGICREF;

    @Element(required = false)
    public String OWING;

    @Element(required = false)
    public String PARAMNO;

    @Element(required = false)
    public String PARAMVALUE;

    @Element(required = false)
    public String PASSWORD;

    @Element(required = false)
    public String PAYMENTREF;

    @Element(required = false)
    public String PAYPLANREF;

    @Element(required = false)
    public String PCODE;

    @Element(required = false)
    public String PDEFINITION;

    @Element(required = false)
    public String PENETPRICE;

    @Element(required = false)
    public String PENETTYPE;

    @Element(required = false)
    public String PERC;

    @Element(required = false)
    public String PERIODNR;

    @Element(required = false)
    public String PID;

    @Element(required = false)
    public String PNAME;

    @Element(required = false)
    public String PNTGROUP;

    @Element(required = false)
    public String PNTID;

    @Element(required = false)
    public String PORTFOYNO;

    @Element(required = false)
    public String PRICE;

    @Element(required = false)
    public String PRIORITY;

    @Element(required = false)
    public String PRODUCTCLASSREF;

    @Element(required = false)
    public String PROJE;

    @Element(required = false)
    public String PRTYPE;

    @Element(required = false)
    public String PTYPE;

    @Element(required = false)
    public String PURCHCLAS;

    @Element(required = false)
    public String PVALUE;

    @Element(required = false)
    public String QID;

    @Element(required = false)
    public String RADDR1;

    @Element(required = false)
    public String RADDR2;

    @Element(required = false)
    public String RATE;

    @Element(required = false)
    public String RCITY;

    @Element(required = false)
    public String RCODE;

    @Element(required = false)
    public String REALSTOCK;

    @Element(required = false)
    public String RECIPIENT;

    @Element(required = false)
    public int RECSTATUS;

    @Element(required = false)
    public String RECVREF;

    @Element(required = false)
    public String REPORTTYPE;

    @Element(required = false)
    public String RESERVED;

    @Element(required = false)
    public String RETURNVAT;

    @Element(required = false)
    public String RNAME;

    @Element(required = false)
    public String ROLLNO;

    @Element(required = false)
    public String ROUTEREF;

    @Element(required = false)
    public String RTOWN;

    @Element(required = false)
    public String SADDR1;

    @Element(required = false)
    public String SADDR2;

    @Element(required = false)
    public String SALESCLAS;

    @Element(required = false)
    public String SALESMANNAME;

    @Element(required = false)
    public String SALESMUST;

    @Element(required = false)
    public String SAYFASONU_HEIGHT;

    @Element(required = false)
    public String SCITY;

    @Element(required = false)
    public String SCODE;

    @Element(required = false)
    public String SDEFINITION;

    @Element(required = false)
    public String SECTNR;

    @Element(required = false)
    public String SENDER;

    @Element(required = false)
    public String SETDATE;

    @Element(required = false)
    public String SHIPADDRREF;

    @Element(required = false)
    public String SHIPINFOREF;

    @Element(required = false)
    public String SHIPPEDAMOUNT;

    @Element(required = false)
    public String SHIPTYP;

    @Element(required = false)
    public String SHPAGNCOD;

    @Element(required = false)
    public String SHPTYPCOD;

    @Element(required = false)
    public String SID;

    @Element(required = false)
    public int SITEID;

    @Element(required = false)
    public String SLSCODE;

    @Element(required = false)
    public String SNAME;

    @Element(required = false)
    public String SOURCEINDEX;

    @Element(required = false)
    public String SPECCODE;

    @Element(required = false)
    public String SPECODE;

    @Element(required = false)
    public String SPECODE2;

    @Element(required = false)
    public String SPECODE3;

    @Element(required = false)
    public String SPECODE4;

    @Element(required = false)
    public String SPECODE5;

    @Element(required = false)
    public String SPECODETYPE;

    @Element(required = false)
    public String SRVREF;

    @Element(required = false)
    public String STAMP;

    @Element(required = false)
    public String STATUS;

    @Element(required = false)
    public String STFICHELNNO;

    @Element(required = false)
    public String STFICHEREF;

    @Element(required = false)
    public String STGRPCODE;

    @Element(required = false)
    public String STOCKREF;

    @Element(required = false)
    public String STOWN;

    @Element(required = false)
    public String STYPE;

    @Element(required = false)
    public String SUBJECT;

    @Element(required = false)
    public String TAXNR;

    @Element(required = false)
    public String TAXOFFCODE;

    @Element(required = false)
    public String TAXOFFICE;

    @Element(required = false)
    public String TCKNO;

    @Element(required = false)
    public String TELNRS1;

    @Element(required = false)
    public String TELNRS2;

    @Element(required = false)
    public String TIME;

    @Element(required = false)
    public String TIME_;

    @Element(required = false)
    public String TITLE;

    @Element(required = false)
    public String TOTAL;

    @Element(required = false)
    public String TOTALDISCOUNTS;

    @Element(required = false)
    public String TOTALTEXT;

    @Element(required = false)
    public String TOTALVAT;

    @Element(required = false)
    public String TOWN;

    @Element(required = false)
    public String TRACKTYPE;

    @Element(required = false)
    public String TRADINGGRP;

    @Element(required = false)
    public int TRCODE;

    @Element(required = false)
    public String TRCURR;

    @Element(required = false)
    public String TRRATE;

    @Element(required = false)
    public String TYP;

    @Element(required = false)
    public String TYPE;

    @Element(required = false)
    public String UCODE;

    @Element(required = false)
    public String UNAME;

    @Element(required = false)
    public String UNIT;

    @Element(required = false)
    public String UNITCONVERT;

    @Element(required = false)
    public String UNITREF;

    @Element(required = false)
    public String UNITSETREF;

    @Element(required = false)
    public String UOMREF;

    @Element(required = false)
    public String USEMINSTOCK;

    @Element(required = false)
    public String USEPENET;

    @Element(required = false)
    public String USERID;

    @Element(required = false)
    public String USERNAME;

    @Element(required = false)
    public String USREF;

    @Element(required = false)
    public String VALUE;

    @Element(required = false)
    public String VARIANTREF;

    @Element(required = false)
    public String VAT;

    @Element(required = false)
    public String VATAMNT;

    @Element(required = false)
    public String VATINC;

    @Element(required = false)
    public String VATMATRAH;

    @Element(required = false)
    public String VCODE;

    @Element(required = false)
    public String VIEWTYPE;

    @Element(required = false)
    public String VISITDAY;

    @Element(required = false)
    public String VNAME;

    @Element(required = false)
    public String VOLUME;

    @Element(required = false)
    public String WAREHOUSENR;

    @Element(required = false)
    public String WEIGHT;

    @Element(required = false)
    public String WHOUSE;

    @Element(required = false)
    public String WIDTH;

    @Element(required = false)
    public String WORKTIME;

    @Element(required = false)
    public String WORKTYPE;
    public boolean addLot;
    public boolean lot;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RESULTLINE() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.WEIGHT);
        parcel.writeString(this.GROSSWEIGHT);
        parcel.writeString(this.VOLUME);
        parcel.writeString(this.WIDTH);
        parcel.writeString(this.LENGTH);
        parcel.writeString(this.HEIGHT);
        parcel.writeString(this.LOGICALREF);
        parcel.writeString(this.USERID);
        parcel.writeString(this.NAME);
        parcel.writeString(this.NAME2);
        parcel.writeString(this.DOSHIP);
        parcel.writeString(this.SALESMANNAME);
        parcel.writeString(this.PARAMNO);
        parcel.writeString(this.PARAMVALUE);
        parcel.writeString(this.CURRTYPE);
        parcel.writeString(this.CURRCODE);
        parcel.writeString(this.VAT);
        parcel.writeString(this.RETURNVAT);
        parcel.writeString(this.STGRPCODE);
        parcel.writeString(this.PAYMENTREF);
        parcel.writeString(this.MARKREF);
        parcel.writeString(this.CARDTYPE);
        parcel.writeString(this.MARK);
        parcel.writeString(this.TRACKTYPE);
        parcel.writeString(this.GTIPCODE);
        parcel.writeString(this.CMDATE);
        parcel.writeString(this.SPECODE2);
        parcel.writeString(this.SPECODE3);
        parcel.writeString(this.SPECODE4);
        parcel.writeString(this.SPECODE5);
        parcel.writeString(this.ISVARYANT);
        parcel.writeString(this.PROJE);
        parcel.writeString(this.LINENO_);
        parcel.writeString(this.VISITDAY);
        parcel.writeString(this.CLREF);
        parcel.writeString(this.ITEMREF);
        parcel.writeString(this.CONVFACT1);
        parcel.writeString(this.CONVFACT2);
        parcel.writeString(this.UNITSETREF);
        parcel.writeString(this.LINENR);
        parcel.writeString(this.UNITREF);
        parcel.writeString(this.BARCODE);
        parcel.writeString(this.TYP);
        parcel.writeString(this.BARCODESHIFT);
        parcel.writeString(this.VARIANTREF);
        parcel.writeString(this.STOCKREF);
        parcel.writeString(this.AMOUNT);
        parcel.writeString(this.PRICE);
        parcel.writeString(this.PERC);
        parcel.writeString(this.MAINCREF);
        parcel.writeString(this.UOMREF);
        parcel.writeString(this.f1212NR);
        parcel.writeString(this.AMBAR);
        parcel.writeString(this.DIVISNR);
        parcel.writeString(this.FACTNR);
        parcel.writeString(this.COSTGRP);
        parcel.writeString(this.BOLUM);
        parcel.writeString(this.ISYERI);
        parcel.writeString(this.FABRIKA);
        parcel.writeString(this.CODETYPE);
        parcel.writeString(this.SPECODETYPE);
        parcel.writeString(this.ODEMEPLAN);
        parcel.writeString(this.GATTRIB);
        parcel.writeString(this.DEF);
        parcel.writeString(this.TITLE);
        parcel.writeString(this.BANKREF);
        parcel.writeString(this.DESC_);
        parcel.writeString(this.SENDER);
        parcel.writeString(this.BEGDATE);
        parcel.writeString(this.ENDDATE);
        parcel.writeString(this.PRIORITY);
        parcel.writeString(this.NOTE);
        parcel.writeString(this.STATUS);
        parcel.writeString(this.SALESMUST);
        parcel.writeString(this.RECIPIENT);
        parcel.writeString(this.ISREAD);
        parcel.writeString(this.MSGTYPE);
        parcel.writeString(this.ENTID);
        parcel.writeString(this.ISTRANSFER);
        parcel.writeString(this.DELETED);
        parcel.writeString(this.MSGDATE);
        parcel.writeString(this.VIEWTYPE);
        parcel.writeString(this.f1211ID);
        parcel.writeString(this.WHOUSE);
        parcel.writeString(this.STYPE);
        parcel.writeString(this.TYPE);
        parcel.writeString(this.WORKTYPE);
        parcel.writeString(this.FORMGROUP);
        parcel.writeString(this.PRODUCTCLASSREF);
        parcel.writeString(this.USEMINSTOCK);
        parcel.writeString(this.MINSTOCK);
        parcel.writeString(this.USEPENET);
        parcel.writeString(this.PENETTYPE);
        parcel.writeString(this.PENETPRICE);
        parcel.writeString(this.FIRMNR);
        parcel.writeString(this.DEFSALESFICHEID);
        parcel.writeString(this.GROUP_);
        parcel.writeString(this.MINAMOUNT);
        parcel.writeString(this.MAXAMOUNT);
        parcel.writeString(this.ISSLCTUNIT);
        parcel.writeString(this.UNIT);
        parcel.writeString(this.MCODE);
        parcel.writeString(this.COLORID);
        parcel.writeString(this.LINETYPE);
        parcel.writeString(this.BEGDATEINT);
        parcel.writeString(this.ENDDATEINT);
        parcel.writeString(this.SUBJECT);
        parcel.writeString(this.CHOICEPROD);
        parcel.writeString(this.ENTERPRODAMOUNT);
        parcel.writeString(this.DEFPRODAMOUNT);
        parcel.writeString(this.SID);
        parcel.writeString(this.DTYPE);
        parcel.writeString(this.DEFVAL);
        parcel.writeString(this.ISMUST);
        parcel.writeString(this.QID);
        parcel.writeString(this.PNTGROUP);
        parcel.writeString(this.PTYPE);
        parcel.writeString(this.ENTPRICE);
        parcel.writeString(this.DEFFIELD1);
        parcel.writeString(this.DEFFIELD2);
        parcel.writeString(this.DEFFIELD3);
        parcel.writeString(this.DEFFIELD4);
        parcel.writeString(this.DEFFIELD5);
        parcel.writeString(this.DEFFIELD6);
        parcel.writeString(this.DEFFIELD7);
        parcel.writeString(this.DEFFIELD8);
        parcel.writeString(this.DEFFIELD9);
        parcel.writeString(this.DEFFIELD10);
        parcel.writeString(this.PNTID);
        parcel.writeString(this.PRTYPE);
        parcel.writeString(this.PID);
        parcel.writeString(this.PNAME);
        parcel.writeString(this.PVALUE);
        parcel.writeString(this.KASA);
        parcel.writeString(this.DNAME);
        parcel.writeString(this.SECTNR);
        parcel.writeString(this.VALUE);
        parcel.writeString(this.DATEINT);
        parcel.writeString(this.RATE);
        parcel.writeString(this.CREDIT);
        parcel.writeString(this.BAKIYE);
        parcel.writeString(this.ADDR1);
        parcel.writeString(this.ROUTEREF);
        parcel.writeString(this.INCHARGE2);
        parcel.writeString(this.INCHARGE3);
        parcel.writeString(this.DEBIT_FLOAT);
        parcel.writeString(this.CREDIT_FLOAT);
        parcel.writeString(this.BAKIYE_FLOAT);
        parcel.writeString(this.CCURRENCY);
        parcel.writeString(this.MYCSRISKLIMIT);
        parcel.writeString(this.CSTCSRISKLIMIT);
        parcel.writeString(this.CSTCSCIRORISKLIMIT);
        parcel.writeString(this.DESPRISKLIMIT);
        parcel.writeString(this.DESPRISKLIMITSUG);
        parcel.writeString(this.ORDRISKLIMIT);
        parcel.writeString(this.ORDRISKLIMITSUGG);
        parcel.writeString(this.ACCRSKBLNCED);
        parcel.writeString(this.MYCSRSKBLNCED);
        parcel.writeString(this.CSTCSRSKBLNCED);
        parcel.writeString(this.CSTCSCIRORSKBLNCED);
        parcel.writeString(this.DESPRSKBLNCED);
        parcel.writeString(this.DESPRSKBLNCEDSUG);
        parcel.writeString(this.ORDRSKBLNCED);
        parcel.writeString(this.ORDRSKBLNCEDSUG);
        parcel.writeString(this.ACCRISKTOTAL);
        parcel.writeString(this.MYCSRISKTOTAL);
        parcel.writeString(this.CSTCSOWNRISKTOTAL);
        parcel.writeString(this.CSTCSCIRORISKTOTAL);
        parcel.writeString(this.DESPRISKTOTAL);
        parcel.writeString(this.DESPRISKTOTALSUG);
        parcel.writeString(this.ORDRISKTOTAL);
        parcel.writeString(this.ORDRISKTOTALSUGG);
        parcel.writeString(this.DELIVERYMETHOD);
        parcel.writeString(this.DELIVERYFIRM);
        parcel.writeString(this.EDINO);
        parcel.writeString(this.USERNAME);
        parcel.writeString(this.PASSWORD);
        parcel.writeString(this.PERIODNR);
        parcel.writeString(this.WORKTIME);
        parcel.writeString(this.SRVREF);
        parcel.writeString(this.CPRICE);
        parcel.writeString(this.CURCODE);
        parcel.writeString(this.CURNR);
        parcel.writeString(this.INCVAT);
        parcel.writeString(this.CLIENTCODE);
        parcel.writeString(this.CLSPECODE);
        parcel.writeString(this.CLSPECODE2);
        parcel.writeString(this.CLSPECODE3);
        parcel.writeString(this.CLSPECODE4);
        parcel.writeString(this.CLSPECODE5);
        parcel.writeString(this.SHIPTYP);
        parcel.writeString(this.PAYPLANREF);
        parcel.writeString(this.UNITCONVERT);
        parcel.writeString(this.CLCYPHCODE);
        parcel.writeString(this.WAREHOUSENR);
        parcel.writeString(this.REALSTOCK);
        parcel.writeString(this.ONHAND);
        parcel.writeString(this.INDIRIM);
        parcel.writeString(this.FORMULA);
        parcel.writeString(this.ADDRESS);
        parcel.writeString(this.LATITUTE);
        parcel.writeString(this.LONGITUDE);
        parcel.writeString(this.BASLANGIC_Y);
        parcel.writeString(this.DETAY_SATIRSAYISI);
        parcel.writeString(this.BASLIK_HEIGHT);
        parcel.writeString(this.DETAY_HEIGHT);
        parcel.writeString(this.SAYFASONU_HEIGHT);
        parcel.writeString(this.BASKISONU_HEIGHT);
        parcel.writeString(this.DEFINITION_);
        parcel.writeString(this.DEFINITION2);
        parcel.writeString(this.ACCRISKLIMIT);
        parcel.writeString(this.MTRLCLAS);
        parcel.writeString(this.PURCHCLAS);
        parcel.writeString(this.SALESCLAS);
        parcel.writeString(this.DATE);
        parcel.writeString(this.SLSCODE);
        parcel.writeString(this.CCODE);
        parcel.writeString(this.CNAME);
        parcel.writeString(this.CCODE2);
        parcel.writeString(this.CNAME2);
        parcel.writeString(this.SNAME);
        parcel.writeString(this.FICHENO);
        parcel.writeString(this.TOTAL);
        parcel.writeInt(this.DOCREF);
        parcel.writeInt(this.DOCID);
        parcel.writeInt(this.FIELDID);
        parcel.writeString(this.FIELDCONT);
        parcel.writeInt(this.SITEID);
        parcel.writeInt(this.RECSTATUS);
        parcel.writeInt(this.ORGLOGICREF);
        parcel.writeInt(this.LANGID);
        parcel.writeString(this.DESIGN);
        parcel.writeString(this.REPORTTYPE);
        parcel.writeString(this.TCKNO);
        parcel.writeString(this.ISPERSCOMP);
        parcel.writeString(this.TOTALTEXT);
        parcel.writeString(this.INVOICEREF);
        parcel.writeString(this.DATE_);
        parcel.writeString(this.TIME_);
        parcel.writeString(this.DOCODE);
        parcel.writeString(this.SPECODE);
        parcel.writeString(this.CYPHCODE);
        parcel.writeString(this.IRSDATE);
        parcel.writeString(this.IRSNO);
        parcel.writeString(this.SHIPINFOREF);
        parcel.writeString(this.RECVREF);
        parcel.writeString(this.CLIENTREF);
        parcel.writeString(this.CODE);
        parcel.writeString(this.CDEFINITION);
        parcel.writeString(this.CSPECODE);
        parcel.writeString(this.CCYPHCODE);
        parcel.writeString(this.ADDR2);
        parcel.writeString(this.CITY);
        parcel.writeString(this.DISTRICT);
        parcel.writeString(this.TELNRS1);
        parcel.writeString(this.TELNRS2);
        parcel.writeString(this.FAXNR);
        parcel.writeString(this.TAXNR);
        parcel.writeString(this.TAXOFFCODE);
        parcel.writeString(this.TOWN);
        parcel.writeString(this.TAXOFFICE);
        parcel.writeString(this.INCHARGE);
        parcel.writeString(this.DISCRATE);
        parcel.writeString(this.EMAILADDR);
        parcel.writeString(this.CTRADINGGRP);
        parcel.writeString(this.RCODE);
        parcel.writeString(this.RNAME);
        parcel.writeString(this.DEBIT);
        parcel.writeString(this.DEBITACCOUNT);
        parcel.writeString(this.CREDITACCOUNT);
        parcel.writeString(this.TOTALVAT);
        parcel.writeString(this.NETTOTAL);
        parcel.writeString(this.TOTALDISCOUNTS);
        parcel.writeString(this.GROSSTOTAL);
        parcel.writeString(this.GENEXP1);
        parcel.writeString(this.GENEXP2);
        parcel.writeString(this.GENEXP3);
        parcel.writeString(this.GENEXP4);
        parcel.writeString(this.SHPTYPCOD);
        parcel.writeString(this.TRADINGGRP);
        parcel.writeString(this.SOURCEINDEX);
        parcel.writeString(this.SCODE);
        parcel.writeString(this.SDEFINITION);
        parcel.writeString(this.PCODE);
        parcel.writeString(this.PDEFINITION);
        parcel.writeString(this.FCODE);
        parcel.writeString(this.FNAME);
        parcel.writeString(this.SHPAGNCOD);
        parcel.writeString(this.STFICHELNNO);
        parcel.writeString(this.DCODE);
        parcel.writeString(this.UCODE);
        parcel.writeString(this.UNAME);
        parcel.writeString(this.USREF);
        parcel.writeString(this.DELVRYCODE);
        parcel.writeString(this.DISCPER);
        parcel.writeString(this.VATAMNT);
        parcel.writeString(this.VATMATRAH);
        parcel.writeString(this.LINEEXP);
        parcel.writeString(this.GLOBTRANS);
        parcel.writeString(this.Barkod);
        parcel.writeString(this.SADDR1);
        parcel.writeString(this.SADDR2);
        parcel.writeString(this.SCITY);
        parcel.writeString(this.STOWN);
        parcel.writeString(this.RADDR1);
        parcel.writeString(this.RADDR2);
        parcel.writeString(this.RCITY);
        parcel.writeString(this.RTOWN);
        parcel.writeString(this.CARIBAKIYE);
        parcel.writeString(this.CARISONBAKIYE);
        parcel.writeString(this.ICUSTSUPCODE);
        parcel.writeString(this.ICUSTSUPNAME);
        parcel.writeString(this.VCODE);
        parcel.writeString(this.VNAME);
        parcel.writeString(this.DUEDATE);
        parcel.writeString(this.SHIPPEDAMOUNT);
        parcel.writeString(this.ORFICHEREF);
        parcel.writeString(this.ORDFICHEREF);
        parcel.writeString(this.VATINC);
        parcel.writeString(this.DORESERVE);
        parcel.writeString(this.STFICHEREF);
        parcel.writeString(this.FTIME);
        parcel.writeString(this.SPECCODE);
        parcel.writeString(this.TIME);
        parcel.writeString(this.BCODE);
        parcel.writeString(this.BDEFINITION);
        parcel.writeString(this.BACODE);
        parcel.writeString(this.BADEFINITION);
        parcel.writeString(this.MUHABIR);
        parcel.writeString(this.BANKNAME);
        parcel.writeString(this.BNBRANCHNO);
        parcel.writeString(this.BNACCOUNTNO);
        parcel.writeString(this.OWING);
        parcel.writeString(this.ROLLNO);
        parcel.writeString(this.PORTFOYNO);
        parcel.writeString(this.NEWSERINO);
        parcel.writeString(this.SETDATE);
        parcel.writeString(this.KEFIL);
        parcel.writeString(this.STAMP);
        parcel.writeString(this.CARDREF);
        parcel.writeString(this.SHIPADDRREF);
        parcel.writeString(this.LATITUDE);
        parcel.writeString(this.LONGTITUDE);
        parcel.writeString(this.CLCODE);
        parcel.writeString(this.ACCEPTEINV);
        parcel.writeString(this.CLCARDREF);
        parcel.writeString(this.INVENNO);
        parcel.writeString(this.RESERVED);
        parcel.writeString(this.GUID);
        parcel.writeByte(this.addLot ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.lot ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.TRCODE);
        parcel.writeString(this.BEFOREBALANCE);
        parcel.writeString(this.TRRATE);
        parcel.writeString(this.TRCURR);
    }

    protected RESULTLINE(Parcel parcel) {
        this.WEIGHT = parcel.readString();
        this.GROSSWEIGHT = parcel.readString();
        this.VOLUME = parcel.readString();
        this.WIDTH = parcel.readString();
        this.LENGTH = parcel.readString();
        this.HEIGHT = parcel.readString();
        this.LOGICALREF = parcel.readString();
        this.USERID = parcel.readString();
        this.NAME = parcel.readString();
        this.NAME2 = parcel.readString();
        this.DOSHIP = parcel.readString();
        this.SALESMANNAME = parcel.readString();
        this.PARAMNO = parcel.readString();
        this.PARAMVALUE = parcel.readString();
        this.CURRTYPE = parcel.readString();
        this.CURRCODE = parcel.readString();
        this.VAT = parcel.readString();
        this.RETURNVAT = parcel.readString();
        this.STGRPCODE = parcel.readString();
        this.PAYMENTREF = parcel.readString();
        this.MARKREF = parcel.readString();
        this.CARDTYPE = parcel.readString();
        this.MARK = parcel.readString();
        this.TRACKTYPE = parcel.readString();
        this.GTIPCODE = parcel.readString();
        this.CMDATE = parcel.readString();
        this.SPECODE2 = parcel.readString();
        this.SPECODE3 = parcel.readString();
        this.SPECODE4 = parcel.readString();
        this.SPECODE5 = parcel.readString();
        this.ISVARYANT = parcel.readString();
        this.PROJE = parcel.readString();
        this.LINENO_ = parcel.readString();
        this.VISITDAY = parcel.readString();
        this.CLREF = parcel.readString();
        this.ITEMREF = parcel.readString();
        this.CONVFACT1 = parcel.readString();
        this.CONVFACT2 = parcel.readString();
        this.UNITSETREF = parcel.readString();
        this.LINENR = parcel.readString();
        this.UNITREF = parcel.readString();
        this.BARCODE = parcel.readString();
        this.TYP = parcel.readString();
        this.BARCODESHIFT = parcel.readString();
        this.VARIANTREF = parcel.readString();
        this.STOCKREF = parcel.readString();
        this.AMOUNT = parcel.readString();
        this.PRICE = parcel.readString();
        this.PERC = parcel.readString();
        this.MAINCREF = parcel.readString();
        this.UOMREF = parcel.readString();
        this.f1212NR = parcel.readString();
        this.AMBAR = parcel.readString();
        this.DIVISNR = parcel.readString();
        this.FACTNR = parcel.readString();
        this.COSTGRP = parcel.readString();
        this.BOLUM = parcel.readString();
        this.ISYERI = parcel.readString();
        this.FABRIKA = parcel.readString();
        this.CODETYPE = parcel.readString();
        this.SPECODETYPE = parcel.readString();
        this.ODEMEPLAN = parcel.readString();
        this.GATTRIB = parcel.readString();
        this.DEF = parcel.readString();
        this.TITLE = parcel.readString();
        this.BANKREF = parcel.readString();
        this.DESC_ = parcel.readString();
        this.SENDER = parcel.readString();
        this.BEGDATE = parcel.readString();
        this.ENDDATE = parcel.readString();
        this.PRIORITY = parcel.readString();
        this.NOTE = parcel.readString();
        this.STATUS = parcel.readString();
        this.SALESMUST = parcel.readString();
        this.RECIPIENT = parcel.readString();
        this.ISREAD = parcel.readString();
        this.MSGTYPE = parcel.readString();
        this.ENTID = parcel.readString();
        this.ISTRANSFER = parcel.readString();
        this.DELETED = parcel.readString();
        this.MSGDATE = parcel.readString();
        this.VIEWTYPE = parcel.readString();
        this.f1211ID = parcel.readString();
        this.WHOUSE = parcel.readString();
        this.STYPE = parcel.readString();
        this.TYPE = parcel.readString();
        this.WORKTYPE = parcel.readString();
        this.FORMGROUP = parcel.readString();
        this.PRODUCTCLASSREF = parcel.readString();
        this.USEMINSTOCK = parcel.readString();
        this.MINSTOCK = parcel.readString();
        this.USEPENET = parcel.readString();
        this.PENETTYPE = parcel.readString();
        this.PENETPRICE = parcel.readString();
        this.FIRMNR = parcel.readString();
        this.DEFSALESFICHEID = parcel.readString();
        this.GROUP_ = parcel.readString();
        this.MINAMOUNT = parcel.readString();
        this.MAXAMOUNT = parcel.readString();
        this.ISSLCTUNIT = parcel.readString();
        this.UNIT = parcel.readString();
        this.MCODE = parcel.readString();
        this.COLORID = parcel.readString();
        this.LINETYPE = parcel.readString();
        this.BEGDATEINT = parcel.readString();
        this.ENDDATEINT = parcel.readString();
        this.SUBJECT = parcel.readString();
        this.CHOICEPROD = parcel.readString();
        this.ENTERPRODAMOUNT = parcel.readString();
        this.DEFPRODAMOUNT = parcel.readString();
        this.SID = parcel.readString();
        this.DTYPE = parcel.readString();
        this.DEFVAL = parcel.readString();
        this.ISMUST = parcel.readString();
        this.QID = parcel.readString();
        this.PNTGROUP = parcel.readString();
        this.PTYPE = parcel.readString();
        this.ENTPRICE = parcel.readString();
        this.DEFFIELD1 = parcel.readString();
        this.DEFFIELD2 = parcel.readString();
        this.DEFFIELD3 = parcel.readString();
        this.DEFFIELD4 = parcel.readString();
        this.DEFFIELD5 = parcel.readString();
        this.DEFFIELD6 = parcel.readString();
        this.DEFFIELD7 = parcel.readString();
        this.DEFFIELD8 = parcel.readString();
        this.DEFFIELD9 = parcel.readString();
        this.DEFFIELD10 = parcel.readString();
        this.PNTID = parcel.readString();
        this.PRTYPE = parcel.readString();
        this.PID = parcel.readString();
        this.PNAME = parcel.readString();
        this.PVALUE = parcel.readString();
        this.KASA = parcel.readString();
        this.DNAME = parcel.readString();
        this.SECTNR = parcel.readString();
        this.VALUE = parcel.readString();
        this.DATEINT = parcel.readString();
        this.RATE = parcel.readString();
        this.CREDIT = parcel.readString();
        this.BAKIYE = parcel.readString();
        this.ADDR1 = parcel.readString();
        this.ROUTEREF = parcel.readString();
        this.INCHARGE2 = parcel.readString();
        this.INCHARGE3 = parcel.readString();
        this.DEBIT_FLOAT = parcel.readString();
        this.CREDIT_FLOAT = parcel.readString();
        this.BAKIYE_FLOAT = parcel.readString();
        this.CCURRENCY = parcel.readString();
        this.MYCSRISKLIMIT = parcel.readString();
        this.CSTCSRISKLIMIT = parcel.readString();
        this.CSTCSCIRORISKLIMIT = parcel.readString();
        this.DESPRISKLIMIT = parcel.readString();
        this.DESPRISKLIMITSUG = parcel.readString();
        this.ORDRISKLIMIT = parcel.readString();
        this.ORDRISKLIMITSUGG = parcel.readString();
        this.ACCRSKBLNCED = parcel.readString();
        this.MYCSRSKBLNCED = parcel.readString();
        this.CSTCSRSKBLNCED = parcel.readString();
        this.CSTCSCIRORSKBLNCED = parcel.readString();
        this.DESPRSKBLNCED = parcel.readString();
        this.DESPRSKBLNCEDSUG = parcel.readString();
        this.ORDRSKBLNCED = parcel.readString();
        this.ORDRSKBLNCEDSUG = parcel.readString();
        this.ACCRISKTOTAL = parcel.readString();
        this.MYCSRISKTOTAL = parcel.readString();
        this.CSTCSOWNRISKTOTAL = parcel.readString();
        this.CSTCSCIRORISKTOTAL = parcel.readString();
        this.DESPRISKTOTAL = parcel.readString();
        this.DESPRISKTOTALSUG = parcel.readString();
        this.ORDRISKTOTAL = parcel.readString();
        this.ORDRISKTOTALSUGG = parcel.readString();
        this.DELIVERYMETHOD = parcel.readString();
        this.DELIVERYFIRM = parcel.readString();
        this.EDINO = parcel.readString();
        this.USERNAME = parcel.readString();
        this.PASSWORD = parcel.readString();
        this.PERIODNR = parcel.readString();
        this.WORKTIME = parcel.readString();
        this.SRVREF = parcel.readString();
        this.CPRICE = parcel.readString();
        this.CURCODE = parcel.readString();
        this.CURNR = parcel.readString();
        this.INCVAT = parcel.readString();
        this.CLIENTCODE = parcel.readString();
        this.CLSPECODE = parcel.readString();
        this.CLSPECODE2 = parcel.readString();
        this.CLSPECODE3 = parcel.readString();
        this.CLSPECODE4 = parcel.readString();
        this.CLSPECODE5 = parcel.readString();
        this.SHIPTYP = parcel.readString();
        this.PAYPLANREF = parcel.readString();
        this.UNITCONVERT = parcel.readString();
        this.CLCYPHCODE = parcel.readString();
        this.WAREHOUSENR = parcel.readString();
        this.REALSTOCK = parcel.readString();
        this.ONHAND = parcel.readString();
        this.INDIRIM = parcel.readString();
        this.FORMULA = parcel.readString();
        this.ADDRESS = parcel.readString();
        this.LATITUTE = parcel.readString();
        this.LONGITUDE = parcel.readString();
        this.BASLANGIC_Y = parcel.readString();
        this.DETAY_SATIRSAYISI = parcel.readString();
        this.BASLIK_HEIGHT = parcel.readString();
        this.DETAY_HEIGHT = parcel.readString();
        this.SAYFASONU_HEIGHT = parcel.readString();
        this.BASKISONU_HEIGHT = parcel.readString();
        this.DEFINITION_ = parcel.readString();
        this.DEFINITION2 = parcel.readString();
        this.ACCRISKLIMIT = parcel.readString();
        this.MTRLCLAS = parcel.readString();
        this.PURCHCLAS = parcel.readString();
        this.SALESCLAS = parcel.readString();
        this.DATE = parcel.readString();
        this.SLSCODE = parcel.readString();
        this.CCODE = parcel.readString();
        this.CNAME = parcel.readString();
        this.CCODE2 = parcel.readString();
        this.CNAME2 = parcel.readString();
        this.SNAME = parcel.readString();
        this.FICHENO = parcel.readString();
        this.TOTAL = parcel.readString();
        this.DOCREF = parcel.readInt();
        this.DOCID = parcel.readInt();
        this.FIELDID = parcel.readInt();
        this.FIELDCONT = parcel.readString();
        this.SITEID = parcel.readInt();
        this.RECSTATUS = parcel.readInt();
        this.ORGLOGICREF = parcel.readInt();
        this.LANGID = parcel.readInt();
        this.DESIGN = parcel.readString();
        this.REPORTTYPE = parcel.readString();
        this.TCKNO = parcel.readString();
        this.ISPERSCOMP = parcel.readString();
        this.TOTALTEXT = parcel.readString();
        this.INVOICEREF = parcel.readString();
        this.DATE_ = parcel.readString();
        this.TIME_ = parcel.readString();
        this.DOCODE = parcel.readString();
        this.SPECODE = parcel.readString();
        this.CYPHCODE = parcel.readString();
        this.IRSDATE = parcel.readString();
        this.IRSNO = parcel.readString();
        this.SHIPINFOREF = parcel.readString();
        this.RECVREF = parcel.readString();
        this.CLIENTREF = parcel.readString();
        this.CODE = parcel.readString();
        this.CDEFINITION = parcel.readString();
        this.CSPECODE = parcel.readString();
        this.CCYPHCODE = parcel.readString();
        this.ADDR2 = parcel.readString();
        this.CITY = parcel.readString();
        this.DISTRICT = parcel.readString();
        this.TELNRS1 = parcel.readString();
        this.TELNRS2 = parcel.readString();
        this.FAXNR = parcel.readString();
        this.TAXNR = parcel.readString();
        this.TAXOFFCODE = parcel.readString();
        this.TOWN = parcel.readString();
        this.TAXOFFICE = parcel.readString();
        this.INCHARGE = parcel.readString();
        this.DISCRATE = parcel.readString();
        this.EMAILADDR = parcel.readString();
        this.CTRADINGGRP = parcel.readString();
        this.RCODE = parcel.readString();
        this.RNAME = parcel.readString();
        this.DEBIT = parcel.readString();
        this.DEBITACCOUNT = parcel.readString();
        this.CREDITACCOUNT = parcel.readString();
        this.TOTALVAT = parcel.readString();
        this.NETTOTAL = parcel.readString();
        this.TOTALDISCOUNTS = parcel.readString();
        this.GROSSTOTAL = parcel.readString();
        this.GENEXP1 = parcel.readString();
        this.GENEXP2 = parcel.readString();
        this.GENEXP3 = parcel.readString();
        this.GENEXP4 = parcel.readString();
        this.SHPTYPCOD = parcel.readString();
        this.TRADINGGRP = parcel.readString();
        this.SOURCEINDEX = parcel.readString();
        this.SCODE = parcel.readString();
        this.SDEFINITION = parcel.readString();
        this.PCODE = parcel.readString();
        this.PDEFINITION = parcel.readString();
        this.FCODE = parcel.readString();
        this.FNAME = parcel.readString();
        this.SHPAGNCOD = parcel.readString();
        this.STFICHELNNO = parcel.readString();
        this.DCODE = parcel.readString();
        this.UCODE = parcel.readString();
        this.UNAME = parcel.readString();
        this.USREF = parcel.readString();
        this.DELVRYCODE = parcel.readString();
        this.DISCPER = parcel.readString();
        this.VATAMNT = parcel.readString();
        this.VATMATRAH = parcel.readString();
        this.LINEEXP = parcel.readString();
        this.GLOBTRANS = parcel.readString();
        this.Barkod = parcel.readString();
        this.SADDR1 = parcel.readString();
        this.SADDR2 = parcel.readString();
        this.SCITY = parcel.readString();
        this.STOWN = parcel.readString();
        this.RADDR1 = parcel.readString();
        this.RADDR2 = parcel.readString();
        this.RCITY = parcel.readString();
        this.RTOWN = parcel.readString();
        this.CARIBAKIYE = parcel.readString();
        this.CARISONBAKIYE = parcel.readString();
        this.ICUSTSUPCODE = parcel.readString();
        this.ICUSTSUPNAME = parcel.readString();
        this.VCODE = parcel.readString();
        this.VNAME = parcel.readString();
        this.DUEDATE = parcel.readString();
        this.SHIPPEDAMOUNT = parcel.readString();
        this.ORFICHEREF = parcel.readString();
        this.ORDFICHEREF = parcel.readString();
        this.VATINC = parcel.readString();
        this.DORESERVE = parcel.readString();
        this.STFICHEREF = parcel.readString();
        this.FTIME = parcel.readString();
        this.SPECCODE = parcel.readString();
        this.TIME = parcel.readString();
        this.BCODE = parcel.readString();
        this.BDEFINITION = parcel.readString();
        this.BACODE = parcel.readString();
        this.BADEFINITION = parcel.readString();
        this.MUHABIR = parcel.readString();
        this.BANKNAME = parcel.readString();
        this.BNBRANCHNO = parcel.readString();
        this.BNACCOUNTNO = parcel.readString();
        this.OWING = parcel.readString();
        this.ROLLNO = parcel.readString();
        this.PORTFOYNO = parcel.readString();
        this.NEWSERINO = parcel.readString();
        this.SETDATE = parcel.readString();
        this.KEFIL = parcel.readString();
        this.STAMP = parcel.readString();
        this.CARDREF = parcel.readString();
        this.SHIPADDRREF = parcel.readString();
        this.LATITUDE = parcel.readString();
        this.LONGTITUDE = parcel.readString();
        this.CLCODE = parcel.readString();
        this.ACCEPTEINV = parcel.readString();
        this.CLCARDREF = parcel.readString();
        this.INVENNO = parcel.readString();
        this.RESERVED = parcel.readString();
        this.GUID = parcel.readString();
        this.addLot = parcel.readByte() != 0;
        this.lot = parcel.readByte() != 0;
        this.TRCODE = parcel.readInt();
        this.BEFOREBALANCE = parcel.readString();
        this.TRRATE = parcel.readString();
        this.TRCURR = parcel.readString();
    }
}
