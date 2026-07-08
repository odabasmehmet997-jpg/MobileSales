package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.core.annotation.SafeType;
import java.io.Serializable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@SafeType
@Root

public class REPORTLINE implements Serializable {
    private static final long serialVersionUID = 1;

    @Element(required = false)
    public double AMOUNT;

    @Element(required = false)
    public double BAKIYE;

    @Element(required = false)
    public String BRANCH;

    @Element(required = false)
    public String CLCODE;

    @Element(required = false)
    public String CPRICE;

    @Element(required = false)
    public double CREDIT;

    @Element(required = false)
    public String CURCODE;

    @Element(required = false)
    public int CURTYPE;

    @Element(required = false)
    public String CUSTOMER;

    @Element(required = false)
    public String CYPHCODE;

    @Element(required = false)
    public String DATE_;

    @Element(required = false)
    public double DEBIT;

    @Element(required = false)
    public String DEPARTMENT;

    @Element(required = false)
    public String DESC;

    @Element(required = false)
    public String DESC1;

    @Element(required = false)
    public String DESC2;

    @Element(required = false)
    public String DESC3;

    @Element(required = false)
    public String DESC4;

    @Element(required = false)
    public String DOCODE;

    @Element(required = false)
    public String DUEDATE;

    @Element(required = false)
    public String FACTORY;

    @Element(required = false)
    public String FTIME;

    @Element(required = false)
    public int GLOBTRANS;

    @Element(required = false)
    public int INCVAT;

    @Element(required = false)
    public String LINEEXP;

    @Element(required = false)
    public int LINETYPE;

    @Element(required = false)
    public int LOGICALREF;

    @Element(required = false)
    public int MAINUNITREF;

    @Element(required = false)
    public int MODULENR;

    @Element(required = false)
    public String PAYMENT;

    @Element(required = false)
    public String PRICE;

    @Element(required = false)
    public String PROCDATE;

    @Element(required = false)
    public String PROJECTCODE;

    @Element(required = false)
    public double PRPRICE;

    @Element(required = false)
    public int PTRNS_FICHEREF;

    @Element(required = false)
    public int PTRNS_MODULENR;

    @Element(required = false)
    public int PTRNS_REF;

    @Element(required = false)
    public double REPORTNET;

    @Element(required = false)
    public String SALESMAN;

    @Element(required = false)
    public String SHIPACCOUNT;

    @Element(required = false)
    public String SHIPADDRESS;

    @Element(required = false)
    public String SHIPAGENT;

    @Element(required = false)
    public String SHIPDELIVERYMETHOD;

    @Element(required = false)
    public String SHIPTRANSTYPE;

    @Element(required = false)
    public int SIGN;

    @Element(required = false)
    public int SOURCEFREF;

    @Element(required = false)
    public int SOURCEUNITREF;

    @Element(required = false)
    public String SPECODE;

    @Element(required = false)
    public int STTRANSREF;

    @Element(required = false)
    public String TOTAL;

    @Element(required = false)
    public double TOTAL_CREDIT;

    @Element(required = false)
    public double TOTAL_DEBIT;

    @Element(required = false)
    public String TRADINGGRP;

    @Element(required = false)
    public int TRCODE;

    @Element(required = false)
    public String TRCODEDESC;

    @Element(required = false)
    public double TRCURR;

    @Element(required = false)
    public double TRNET;

    @Element(required = false)
    public String VAT;

    @Element(required = false)
    public double VATAMOUNT;

    @Element(required = false)
    public String WAREHOUSE;

    @Element(required = false)
    public String FICHENO = "";

    @Element(required = false)
    public String TOTALDISCOUNTS = "";

    @Element(required = false)
    public String GENEXP1 = "";

    @Element(required = false)
    public String GENEXP2 = "";

    @Element(required = false)
    public String GENEXP3 = "";

    @Element(required = false)
    public String GENEXP4 = "";

    @Element(required = false)
    public String ROLLNO = "";

    @Element(required = false)
    public String CODE = "";

    @Element(required = false)
    public String DEFINITION_ = "";

    @Element(required = false)
    public String GROSSTOTAL = "";

    @Element(required = false)
    public String NETTOTAL = "";

    @Element(required = false)
    public String UNIT = "";

    /* renamed from: X */
    @Element(required = false)
    public String f1208X = "";

    /* renamed from: Y */
    @Element(required = false)
    public String f1209Y = "";

    /* renamed from: Z */
    @Element(required = false)
    public String f1210Z = "";

    /* renamed from: W */
    @Element(required = false)
    public String f1207W = "";

    /* renamed from: T */
    @Element(required = false)
    public String f1206T = "";

    /* renamed from: B */
    @Element(required = false)
    public String f1204B = "";

    /* renamed from: A */
    @Element(required = false)
    public String f1203A = "";

    /* renamed from: K */
    @Element(required = false)
    public String f1205K = "";

    @Element(required = false)
    public String SPECCODE = "";

    @Element(required = false)
    public double BALANCE = 0.0d;

    @Element(required = false)
    public boolean isCheck = false;

    @Element(required = false)
    public String VAR1 = "";

    @Element(required = false)
    public String VAR2 = "";

    @Element(required = false)
    public String VAR3 = "";

    @Element(required = false)
    public String VAR4 = "";
}
