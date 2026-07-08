package com.proje.mobilesales.core.printutil;

import android.database.Cursor;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.TrackType;
import com.proje.mobilesales.core.exception.ErpNotFoundException;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.tigerwcf.RESULTLINE;
import com.proje.mobilesales.core.tigerwcf.RESULTXML;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.CalculateUtils;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.model.USER;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintFicheProcess {
    private static final String TAG = "PrintFicheProcess";
    private static int i11;

    public static String[] PrintOrderFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        Exception exc;
        String[] strArr;
        String[] strArr2;
        Cursor cursor;
        String str2;
        String str3;
        String str4;
        int i6;
        Cursor cursor2;
        double d2;
        String str5;
        String str6;
        int i7;
        String str7;
        String str8;
        String str9;
        boolean z3;
        int i8;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        StringBuilder sb;
        String dFormat;
        String str19;
        String str20;
        String str21 = "PNAME";
        String str22 = "PCODE";
        String str23 = "WAREHOUSENR";
        String str24 = "SPECODE";
        String str25 = "DISCTOTAL5";
        String str26 = "DISCTOTAL4";
        String str27 = "DISCTOTAL3";
        String str28 = "DISCTOTAL2";
        String str29 = "DISCTOTAL1";
        String str30 = "VATAMNT";
        String str31 = "TOTAL";
        String[] strArr3 = new String[1];
        double[] dArr = new double[5];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            Cursor orderHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getOrderHedaerForPrint(i2);
            Cursor orderDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getOrderDetailForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            Cursor cursor3 = orderDetailForPrint;
            orderHedaerForPrint.moveToPosition(0);
            int salesTrCurrFromHeader = getSalesTrCurrFromHeader(orderHedaerForPrint);
            double salesTrRateFromHeader = getSalesTrRateFromHeader(orderHedaerForPrint);
            int i9 = salesTrCurrFromHeader;
            StringBuilder sb2 = new StringBuilder();
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            ColType colType = ColType.metin;
            sb2.append(logoSqlHelper.dbVal(orderHedaerForPrint, "FICHENO", colType).toString());
            sb2.append("\n\t");
            String str32 = ((((((((((((((sb2.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FICHEDATE", colType).toString() + "\n\t") + DateAndTimeUtils.getTimeOnly(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "GDATE", colType).toString()) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DOCNO", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "SHIPTYPE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "SHIPAGENT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TRADINGGRP", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "WAREHOUSENR", colType).toString() + "\n\t";
            String userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
            String str33 = "DESC4";
            String sb3 = str32 + userCode + "\n\t" +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) +
                    "\n\t";
            String str34 = ((((((((((((((((((((sb3 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "PCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "PNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t";
            Cursor cursor4 = customerUnsentAccountBalance;
            String sb5 = str34 +
                    addAccountBalanceInfo(orderHedaerForPrint, cursor4);
            String str35 = "DISCRATE";
            String sb6 = sb5 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RADDR1", colType).toString() +
                    "\n\t";
            String str36 = ((((((((sb6 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "RDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
            ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            String str37 = "DESC3";
            ColType colType2 = ColType.virgullu;
            double d3 = StringUtils.toDouble(logoSqlHelper2.dbVal(orderHedaerForPrint, "TOTALNET", colType2).toString());
            String str38 = "DESC2";
            String str39 = "DESC1";
            String sb7 = str36 +
                    addBeforeBalanceInfo(orderHedaerForPrint, SalesType.ORDER, 0.0d, cursor4);
            String str40 = (((sb7 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "SHIPDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FLATITUTE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "FLONGITUDE", colType).toString() + "\n\t";
            String str41 = "";
            String sb8 = str40 +
                    str41 +
                    "\n\t";
            String str42 = sb8 + userCode + "\n\t";
            double d4 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "TOTALVAT", colType2).toString());
            double d5 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "DISCTOTAL", colType2).toString());
            double d6 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, "GTOTAL", colType2).toString()) - d5;
            int i10 = i3 <= 0 ? 1 : i3;
            String[] strArr4 = new String[(cursor3.getCount() / i10) + 1];
            String str43 = str42;
            String str44 = str41;
            boolean z4 = true;
            int i11 = 1;
            int i12 = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            int i13 = 0;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            double d12 = 0.0d;
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            double d16 = 0.0d;
            double d17 = 0.0d;
            double d18 = 0.0d;
            while (true) {
                try {
                    String str45 = str38;
                    if (i12 > cursor3.getCount() - 1) {
                        return strArr4;
                    }
                    Cursor cursor5 = cursor3;
                    if (cursor5.moveToPosition(i12)) {
                        strArr2 = strArr4;
                        try {
                            ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            cursor = cursor4;
                            d2 = d6;
                            ColType colType3 = ColType.metin;
                            if (StringUtils.convertStringToInt(logoSqlHelper3.dbVal(cursor5, "VARIANTREF", colType3).toString()) > 0) {
                                str18 = (str44 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VNAME", colType3).toString() + "\n\t";
                                str2 = str21;
                                str3 = str22;
                            } else {
                                String obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str22, colType3).toString();
                                String obj2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str21, colType3).toString();
                                str2 = str21;
                                if (StringUtils.paramValueNumberCheck(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode))) {
                                    String obj3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "ICUSTSUPCODE", colType3).toString();
                                    str3 = str22;
                                    String obj4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "ICUSTSUPNAME", colType3).toString();
                                    String sb10 = str44 +
                                            (obj3.isEmpty() ? obj + "\n\t" : obj3 + "\n\t");
                                    StringBuilder sb11 = new StringBuilder();
                                    sb11.append(sb10);
                                    if (obj4.isEmpty()) {
                                        sb = new StringBuilder();
                                        sb.append(obj2);
                                        sb.append("\n\t");
                                    } else {
                                        sb = new StringBuilder();
                                        sb.append(obj4);
                                        sb.append("\n\t");
                                    }
                                    sb11.append(sb.toString());
                                    str18 = sb11.toString();
                                } else {
                                    str3 = str22;
                                    str18 = (str44 + obj + "\n\t") + obj2 + "\n\t";
                                }
                            }
                            String str46 = ((((((str18 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "PAYCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "PAYNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "UCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "UCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(orderHedaerForPrint, str23, colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str24, colType3).toString() + "\n\t") + str41 + "\n\t";
                            ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType4 = ColType.virgullu;
                            double d19 = StringUtils.toDouble(logoSqlHelper4.dbVal(cursor5, "AMOUNT", colType4).toString());
                            d7 += d19;
                            String str47 = str46 + checkIsDivUnitForAmountPrintValueOffline(cursor5, d19, "\n\t");
                            double d20 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "PRICE", colType4).toString());
                            String str48 = str47 + StringUtils.dFormat(d20) + "\n\t";
                            str4 = str23;
                            String str49 = str31;
                            double d21 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str49, colType4).toString());
                            String str50 = (str48 + StringUtils.dFormat(d21) + "\n\t") + str41 + "\n\t";
                            StringBuilder sb12 = new StringBuilder();
                            sb12.append(str50);
                            str10 = str24;
                            Cursor cursor6 = orderHedaerForPrint;
                            i6 = i10;
                            sb12.append(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VAT", ColType.sayi).toString()));
                            sb12.append("\n\t");
                            String sb13 = sb12.toString();
                            String str51 = str30;
                            String sb15 = sb13 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str51, colType4).toString())) +
                                    "\n\t";
                            double d22 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VATMATRAH", colType4).toString());
                            double d23 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str51, colType4).toString());
                            double d24 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VAT", colType4).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str49, colType4).toString());
                            d8 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str49, colType4).toString());
                            d9 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "LINENET", colType4).toString());
                            d10 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str51, colType4).toString());
                            int i14 = 0;
                            while (true) {
                                if (i14 >= 5) {
                                    break;
                                }
                                double d25 = dArr[i14];
                                if (d25 == d24) {
                                    dArr2[i14] = dArr2[i14] + d23;
                                    dArr5[i14] = dArr5[i14] + d23;
                                    dArr3[i14] = dArr3[i14] + d22;
                                    break;
                                }
                                if (d25 == 0.0d) {
                                    dArr[i14] = d24;
                                    dArr2[i14] = d23;
                                    dArr4[i14] = d24;
                                    dArr5[i14] = d23;
                                    dArr3[i14] = d22;
                                    break;
                                }
                                i14++;
                            }
                            StringBuilder sb16 = new StringBuilder();
                            sb16.append(sb15);
                            ISqlHelper logoSqlHelper5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType5 = ColType.virgullu;
                            sb16.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper5.dbVal(cursor5, "DISCRATIO1", colType5).toString())));
                            sb16.append("\n\t");
                            String sb17 = sb16.toString();
                            String str52 = str29;
                            String sb19 = sb17 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str52, colType5).toString())) +
                                    "\n\t";
                            double d26 = d11 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str52, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str52, colType5).toString());
                            String str53 = sb19 + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "DISCRATIO2", colType5).toString())) + "\n\t";
                            StringBuilder sb20 = new StringBuilder();
                            sb20.append(str53);
                            str5 = str28;
                            sb20.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str5, colType5).toString())));
                            sb20.append("\n\t");
                            String sb21 = sb20.toString();
                            double d27 = d26 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str5, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str5, colType5).toString());
                            StringBuilder sb22 = new StringBuilder();
                            sb22.append(sb21);
                            str6 = str49;
                            sb22.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "DISCRATIO3", colType5).toString())));
                            sb22.append("\n\t");
                            String sb23 = sb22.toString();
                            String str54 = str27;
                            String sb25 = sb23 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str54, colType5).toString())) +
                                    "\n\t";
                            double d28 = d27 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str54, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str54, colType5).toString());
                            StringBuilder sb26 = new StringBuilder();
                            sb26.append(sb25);
                            str30 = str51;
                            sb26.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "DISCRATIO4", colType5).toString())));
                            sb26.append("\n\t");
                            String sb27 = sb26.toString();
                            String str55 = str26;
                            String sb29 = sb27 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str55, colType5).toString())) +
                                    "\n\t";
                            double d29 = d28 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str55, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str55, colType5).toString());
                            String str56 = sb29 + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "DISCRATIO5", colType5).toString())) + "\n\t";
                            String str57 = str25;
                            String sb31 = str56 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str57, colType5).toString())) +
                                    "\n\t";
                            d11 = d29 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str57, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, str57, colType5).toString());
                            StringBuilder sb32 = new StringBuilder();
                            sb32.append(sb31 + "0\n\t");
                            ISqlHelper logoSqlHelper6 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType6 = ColType.metin;
                            sb32.append(logoSqlHelper6.dbVal(cursor5, "SHIPDATE", colType6).toString());
                            sb32.append("\n\t");
                            String str58 = ((((((((((((((((sb32.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "DESC", colType6).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "GTIPCODE", colType6).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "Barkod", colType6).toString() + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "PRICE", colType6).toString() + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + str41 + "\n\t") + StringUtils.dFormat(d22) + "\n\t") + StringUtils.dFormat(d22 + d23) + "\n\t";
                            StringBuilder sb33 = new StringBuilder();
                            sb33.append(str58);
                            if (salesTrRateFromHeader == 0.0d) {
                                str14 = str41;
                                dFormat = "0.00";
                            } else {
                                str14 = str41;
                                dFormat = StringUtils.dFormat(d21 / salesTrRateFromHeader);
                            }
                            sb33.append(dFormat);
                            sb33.append("\n\t");
                            String sb34 = sb33.toString();
                            String sb35 = sb34 +
                                    (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d20 / salesTrRateFromHeader)) +
                                    "\n\t";
                            String str59 = sb35 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "PNAME2", colType6).toString() + "\n\t";
                            for (int i15 = 0; i15 < 18; i15++) {
                                str59 = str59 + "\n\t";
                            }
                            StringBuilder sb36 = new StringBuilder();
                            sb36.append(str59);
                            ISqlHelper logoSqlHelper7 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType7 = ColType.metin;
                            sb36.append(logoSqlHelper7.dbVal(cursor5, "WIDTH", colType7).toString());
                            sb36.append("\n\t");
                            String str60 = (sb36.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "LENGTH", colType7).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "HEIGHT", colType7).toString() + "\n\t\r";
                            ISqlHelper logoSqlHelper8 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType8 = ColType.virgullu;
                            d12 += StringUtils.toDouble(logoSqlHelper8.dbVal(cursor5, "GROSSWEIGHT", colType8).toString()) * d19;
                            d13 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "WEIGHT", colType8).toString()) * d19;
                            d14 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor5, "VOLUME", colType8).toString()) * d19;
                            int i16 = i12 + 1;
                            if (i16 == cursor5.getCount() || i16 == i6 * i11) {
                                if (d8 < 0.0d) {
                                    d8 = 0.0d;
                                }
                                d16 += d9 + d10;
                                d17 += d8;
                                d15 += d7;
                                String str61 = str14;
                                for (int i17 = 0; i17 < 5; i17++) {
                                    str61 = (str61 + StringUtils.dFormat(dArr[i17]) + "\n\t") + StringUtils.dFormat(dArr2[i17]) + "\n\t";
                                }
                                String str62 = (str61 + StringUtils.dFormat(d8) + "\n\t") + StringUtils.dFormat(d17) + "\n\t";
                                for (int i18 = 0; i18 < 5; i18++) {
                                    str62 = str62 + StringUtils.dFormat(dArr3[i18]) + "\n\t";
                                }
                                StringBuilder sb37 = new StringBuilder();
                                sb37.append(str62);
                                ISqlHelper logoSqlHelper9 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType9 = ColType.metin;
                                str8 = str39;
                                cursor2 = cursor6;
                                sb37.append(logoSqlHelper9.dbVal(cursor2, str8, colType9).toString());
                                sb37.append("\n\t");
                                String sb38 = sb37.toString();
                                StringBuilder sb39 = new StringBuilder();
                                sb39.append(sb38);
                                str9 = str45;
                                sb39.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str9, colType9).toString());
                                sb39.append("\n\t");
                                String sb40 = sb39.toString();
                                StringBuilder sb41 = new StringBuilder();
                                sb41.append(sb40);
                                i8 = i12;
                                str13 = str37;
                                sb41.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str13, colType9).toString());
                                sb41.append("\n\t");
                                String sb42 = sb41.toString();
                                StringBuilder sb43 = new StringBuilder();
                                sb43.append(sb42);
                                str15 = str33;
                                sb43.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str15, colType9).toString());
                                sb43.append("\n\t");
                                String str63 = (sb43.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                String sb44 = str63 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d2 / salesTrRateFromHeader)) +
                                        "\n\t";
                                String str64 = (sb44 + StringUtils.dFormat(d7) + "\n\t") + cursor5.getCount() + "\n\t";
                                String str65 = str14;
                                for (int i19 = 0; i19 <= 4; i19++) {
                                    str65 = (str65 + StringUtils.dFormat(dArr4[i19]) + "\n\t") + StringUtils.dFormat(dArr5[i19]) + "\n\t";
                                }
                                String str66 = (((((str65 + StringUtils.dFormat(d2) + "\n\t") + StringUtils.dFormat(d5) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + StringUtils.dFormat(d4) + "\n\t") + StringUtils.dFormat(d11) + "\n\t") + StringUtils.dFormat(d18) + "\n\t";
                                StringBuilder sb45 = new StringBuilder();
                                sb45.append(str66);
                                ISqlHelper logoSqlHelper10 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType10 = ColType.virgullu;
                                sb45.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper10.dbVal(cursor2, "DISCRATIO1", colType10).toString())));
                                sb45.append("\n\t");
                                String str67 = ((((((sb45.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str52, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO2", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str5, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO3", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str54, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO4", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str55, colType10).toString())) + "\n\t";
                                StringBuilder sb46 = new StringBuilder();
                                sb46.append(str67);
                                str12 = str54;
                                sb46.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO5", colType10).toString())));
                                sb46.append("\n\t");
                                String str68 = (sb46.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str57, colType10).toString())) + "\n\t") + addAccountBalanceInfo(cursor2, cursor);
                                StringBuilder sb47 = new StringBuilder();
                                sb47.append(str68);
                                cursor = cursor;
                                str17 = str52;
                                str16 = str57;
                                sb47.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d3), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                sb47.append("\n\t");
                                String str69 = (sb47.toString() + StringUtils.dFormat(d16) + "\n\t") + StringUtils.dFormat(d2) + "\n\t";
                                for (int i20 = 0; i20 < 5; i20++) {
                                    str69 = str69 + StringUtils.dFormat(dArr3[i20]) + "\n\t";
                                }
                                StringBuilder sb48 = new StringBuilder();
                                sb48.append(str69);
                                ISqlHelper logoSqlHelper11 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType11 = ColType.metin;
                                sb48.append(logoSqlHelper11.dbVal(cursor2, str8, colType11).toString());
                                sb48.append("\n\t");
                                String str70 = ((((sb48.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str9, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str13, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str15, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                StringBuilder sb49 = new StringBuilder();
                                sb49.append(str70);
                                str7 = str35;
                                sb49.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str7, colType11).toString());
                                sb49.append("\n\t");
                                String str71 = sb49.toString() + getClCardCurrencyCode(i5) + "\n\t";
                                String sb51 = str71 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(salesTrRateFromHeader)) +
                                        "\n\t";
                                StringBuilder sb52 = new StringBuilder();
                                sb52.append(sb51);
                                double d30 = d3 / salesTrRateFromHeader;
                                i7 = i9;
                                sb52.append(StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d30), i7));
                                sb52.append("\n\t");
                                String sb53 = sb52.toString();
                                String sb55 = sb53 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d30)) +
                                        "\n\t";
                                String sb56 = sb55 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d4 / salesTrRateFromHeader)) +
                                        "\n\t";
                                String str72 = ((((((((sb56 + "\n\t") + StringUtils.dFormat(d15) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.dFormat(d12) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d13) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + cursor5.getCount() + "\n\t";
                                for (int i21 = 0; i21 < 8; i21++) {
                                    str72 = str72 + "\n\t";
                                }
                                String str73 = str72 + StringUtils.dFormat(d14) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                                String str74 = (!z || z4) ? str43 : str14;
                                if (i16 == cursor5.getCount()) {
                                    str19 = str73;
                                    if (z2) {
                                        str20 = str14;
                                        String Print = printProcess.Print(i6, str74, str60, str20, str19);
                                        z3 = true;
                                        if (i4 != 1) {
                                            strArr2[i13] = StringUtils.convTrCharEN(Print);
                                        } else {
                                            strArr2[i13] = Print;
                                        }
                                        i13++;
                                        i11++;
                                        str11 = str55;
                                        str43 = str74;
                                        str44 = str14;
                                        d18 = d16;
                                        z4 = false;
                                        d7 = 0.0d;
                                        d8 = 0.0d;
                                        d9 = 0.0d;
                                        d10 = 0.0d;
                                    }
                                } else {
                                    str19 = str14;
                                }
                                str20 = str64;
                                String Print2 = printProcess.Print(i6, str74, str60, str20, str19);
                                z3 = true;
                                if (i4 != 1) {
                                }
                                i13++;
                                i11++;
                                str11 = str55;
                                str43 = str74;
                                str44 = str14;
                                d18 = d16;
                                z4 = false;
                                d7 = 0.0d;
                                d8 = 0.0d;
                                d9 = 0.0d;
                                d10 = 0.0d;
                            } else {
                                str44 = str60;
                                str7 = str35;
                                str15 = str33;
                                str8 = str39;
                                str9 = str45;
                                cursor2 = cursor6;
                                z3 = true;
                                i8 = i12;
                                str16 = str57;
                                str13 = str37;
                                str17 = str52;
                                i7 = i9;
                                str12 = str54;
                                str11 = str55;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            strArr = strArr2;
                            exc = e;
                            strArr[0] = exc.getMessage();
                            return strArr;
                        }
                    } else {
                        cursor = cursor4;
                        str2 = str21;
                        str3 = str22;
                        str4 = str23;
                        i6 = i10;
                        cursor2 = orderHedaerForPrint;
                        d2 = d6;
                        strArr2 = strArr4;
                        str5 = str28;
                        str6 = str31;
                        i7 = i9;
                        str7 = str35;
                        str8 = str39;
                        str9 = str45;
                        z3 = true;
                        i8 = i12;
                        str10 = str24;
                        str11 = str26;
                        str12 = str27;
                        str13 = str37;
                        String str75 = str29;
                        str14 = str41;
                        str15 = str33;
                        str16 = str25;
                        str17 = str75;
                    }
                    int i22 = i8 + 1;
                    i10 = i6;
                    str37 = str13;
                    str39 = str8;
                    i9 = i7;
                    str35 = str7;
                    i12 = i22;
                    cursor4 = cursor;
                    str21 = str2;
                    str22 = str3;
                    orderHedaerForPrint = cursor2;
                    str27 = str12;
                    str23 = str4;
                    str26 = str11;
                    str24 = str10;
                    str31 = str6;
                    str28 = str5;
                    strArr4 = strArr2;
                    cursor3 = cursor5;
                    str38 = str9;
                    d6 = d2;
                    String str76 = str16;
                    str33 = str15;
                    str41 = str14;
                    str29 = str17;
                    str25 = str76;
                } catch (Exception e3) {
                    e = e3;
                    strArr2 = strArr4;
                }
            }
        } catch (Exception e4) {
            exc = e4;
            strArr = strArr3;
        }
        return strArr;
    }
    private static String addAccountBalanceInfo(Cursor cursor, Cursor cursor2) {
        double roundTwoDecimals;
        double roundTwoDecimals2;
        double roundTwoDecimals3;
        double d2;
        String str = "";
        String str2 = "";
        if (cursor2 != null) {
            try {
                if (cursor2.getCount() > 0 && cursor2.moveToFirst()) {
                    roundTwoDecimals = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLDEBIT")));
                    roundTwoDecimals2 = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLCREDIT")));
                    roundTwoDecimals3 = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLBALANCE")));
                    String str3 = (StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("DEBIT_FLOAT")) + roundTwoDecimals) + "\n\t") + StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("CREDIT_FLOAT")) + roundTwoDecimals2) + "\n\t";
                    d2 = cursor.getDouble(cursor.getColumnIndex("BAKIYE_FLOAT")) + roundTwoDecimals3;
                    if (d2 < 0.0d) {
                        str = "(B)";
                    } else {
                        d2 *= -1.0d;
                        str = "(A)";
                    }
                    str2 = str3 + StringUtils.dFormat(d2) + "\n\t";
                    return str2 + str + "\n\t";
                }
            } catch (Exception e2) {
                Log.d(TAG, "addAccountBalanceInfo: ", e2);
                return str2;
            }
        }
        roundTwoDecimals = 0.0d;
        roundTwoDecimals2 = 0.0d;
        roundTwoDecimals3 = 0.0d;
        String str32 = (StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("DEBIT_FLOAT")) + roundTwoDecimals) + "\n\t") + StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("CREDIT_FLOAT")) + roundTwoDecimals2) + "\n\t";
        d2 = cursor.getDouble(cursor.getColumnIndex("BAKIYE_FLOAT")) + roundTwoDecimals3;
        if (d2 < 0.0d) {
        }
        str2 = str32 + StringUtils.dFormat(d2) + "\n\t";
        return str2 + str + "\n\t";
    }
    private static String addBeforeBalanceInfo(Cursor cursor, SalesType salesType, double d2, Cursor cursor2) {
        String str = "";
        try {
            double d3 = cursor.getDouble(cursor.getColumnIndex("BAKIYE_FLOAT"));
            Cursor customerBalanceInfoAfterDate = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerBalanceInfoAfterDate(cursor.getInt(cursor.getColumnIndex("CLREF")), cursor.getString(cursor.getColumnIndex("GDATE")));
            double d4 = 0.0d;
            double roundTwoDecimals = (customerBalanceInfoAfterDate == null || customerBalanceInfoAfterDate.getCount() <= 0 || !customerBalanceInfoAfterDate.moveToFirst()) ? 0.0d : StringUtils.roundTwoDecimals(customerBalanceInfoAfterDate.getDouble(customerBalanceInfoAfterDate.getColumnIndex("ALLBALANCE")));
            if (cursor2 != null && cursor2.getCount() > 0 && cursor2.moveToFirst()) {
                d4 = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLBALANCE")));
            }
            str = StringUtils.dFormat(Math.abs((d3 - roundTwoDecimals) + d4));
            return str + "\n\t";
        } catch (Exception e2) {
            Log.d(TAG, "addAccountBalanceInfo: ", e2);
            return str;
        }
    }
    public static String[] PrintFiche(int i2, int i3, int i4, boolean z, boolean z2, String str, int i5, int i6) {
        if (i2 == FicheType.INVOICE.getmValue()) {
            return PrintInvoiceFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.ORDER.getmValue()) {
            return PrintOrderFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.DISPATCH.getmValue()) {
            return PrintDispatchFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.CASH.getmValue()) {
            return PrintCashFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.CREDIT_CART.getmValue()) {
            return PrintCrediCardFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.CHEQUE.getmValue()) {
            return PrintCheequeFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.DEED.getmValue()) {
            return PrintDeedFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.CASE_CASH.getmValue()) {
            return PrintCaseFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.DELIVERY_NOTE.getmValue()) {
            return PrintDeliveryNoteFiche(i3, i4, z, z2, str, i5, i6);
        }
        if (i2 == FicheType.WHTRANSFER.getmValue()) {
            return PrintWhTransferFiche(i3, i4, z, z2, str, i5, i6);
        }
        return null;
    }
    private static int getSalesTrCurrFromHeader(Cursor cursor) {
        Object dbVal = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "TRCURR", ColType.sayi);
        if (dbVal == null) {
            return 0;
        }
        return Integer.valueOf(dbVal.toString()).intValue();
    }
    private static double getSalesTrRateFromHeader(Cursor cursor) {
        Object dbVal = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "TRRATE", ColType.virgullu);
        if (dbVal == null) {
            return 0.0d;
        }
        return StringUtils.toDouble(dbVal.toString());
    }
    public static String[] PrintInvoiceFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        Exception exc;
        String[] strArr;
        Cursor cursor;
        String str2;
        String str3;
        int i6;
        int i7;
        String str4;
        double d2;
        double d3;
        int i8;
        double d4;
        String str5;
        String str6;
        String str7;
        int i9;
        String str8;
        String str9;
        String str10;
        double d5;
        String str11;
        Cursor cursor2;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        StringBuilder sb;
        Cursor cursor3;
        String str17;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        String str25 = "PCODE";
        String str26 = "VATAMNT";
        String str27 = "VAT";
        String[] strArr2 = new String[1];
        double[] dArr = new double[5];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        boolean printLotDetail = ErpCreator.getInstance().getmBaseErp().getPrintLotDetail();
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            Cursor invoiceHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getInvoiceHedaerForPrint(i2);
            Cursor invoiceDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getInvoiceDetailForPrint(i2, printLotDetail, i5);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            invoiceHedaerForPrint.moveToPosition(0);
            int salesTrCurrFromHeader = getSalesTrCurrFromHeader(invoiceHedaerForPrint);
            double salesTrRateFromHeader = getSalesTrRateFromHeader(invoiceHedaerForPrint);
            int i10 = salesTrCurrFromHeader;
            StringBuilder sb2 = new StringBuilder();
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            ColType colType = ColType.metin;
            sb2.append(logoSqlHelper.dbVal(invoiceHedaerForPrint, "FICHENO", colType).toString());
            sb2.append("\n\t");
            String str28 = ((((((((((((((sb2.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString() + "\n\t") + DateAndTimeUtils.getTimeOnly(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "GDATE", colType).toString()) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DOCNO", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SHIPTYPE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SHIPAGENT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TRADINGGRP", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "WAREHOUSENR", colType).toString() + "\n\t";
            String userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
            String str29 = "DESC4";
            String sb3 = str28 + userCode + "\n\t" +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) +
                    "\n\t";
            String str30 = (sb3 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PNAME", colType).toString() + "\n\t";
            String str31 = "DESC3";
            String sb4 = str30 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FCODE", colType).toString() +
                    "\n\t";
            String str32 = (((((((((((((((((((sb4 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(invoiceHedaerForPrint, customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString() + "\n\t";
            String str33 = "";
            String sb6 = str32 +
                    str33 +
                    "\n\t";
            String str34 = "DESC2";
            String sb7 = sb6 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RADDR1", colType).toString() +
                    "\n\t";
            String str35 = ((((((sb7 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FDISTRICT", colType).toString() + "\n\t";
            if (invoiceDetailForPrint == null || invoiceDetailForPrint.getCount() == 0) {
                cursor = invoiceDetailForPrint;
            } else {
                cursor = invoiceDetailForPrint;
                if (cursor.moveToPosition(0)) {
                    str2 = "DESC1";
                    str3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "LOGO_ORDERNO", colType).toString();
                    String str36 = ((str35 + str3 + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                    ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    String str37 = "SPECODE";
                    ColType colType2 = ColType.virgullu;
                    double d6 = StringUtils.toDouble(logoSqlHelper2.dbVal(invoiceHedaerForPrint, "TOTALNET", colType2).toString());
                    String str38 = "PNAME";
                    String str39 = "WAREHOUSENR";
                    String obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "INVTYPE", colType).toString();
                    String sb9 = str36 +
                            addBeforeBalanceInfo(invoiceHedaerForPrint, !obj.equals(BuildConfig.NETSIS_DEMO_PASSWORD) ? SalesType.RETURN_INVOICE : SalesType.INVOICE, d6, customerUnsentAccountBalance);
                    String str40 = obj;
                    String sb10 = sb9 +
                            ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) +
                            "\n\t";
                    String str41 = (((sb10 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLATITUTE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLONGITUDE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TC", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CNAME2", colType).toString() + "\n\t";
                    Cursor cursor4 = customerUnsentAccountBalance;
                    double d7 = d6;
                    String receiptTotalFromInvoice = ErpCreator.getInstance().getmBaseErp().getReceiptTotalFromInvoice(i2, true, StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DATEINT", ColType.sayi).toString()));
                    String str42 = (((str41 + receiptTotalFromInvoice + "\n\t") + getPaymentDate(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PAYPLANREF", colType).toString()), ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString()) + "\n\t") + userCode + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ANDFICHENO", colType).toString() + "\n\t";
                    double d8 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TOTALVAT", colType2).toString());
                    double d9 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCTOTAL", colType2).toString());
                    double d10 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "GTOTAL", colType2).toString()) - d9;
                    int i11 = i3 > 0 ? 1 : i3;
                    String[] strArr3 = new String[(cursor.getCount() / i11) + 1];
                    String str43 = str42;
                    String str44 = receiptTotalFromInvoice;
                    double d11 = 0.0d;
                    double d12 = 0.0d;
                    double d13 = 0.0d;
                    double d14 = 0.0d;
                    double d15 = 0.0d;
                    double d16 = 0.0d;
                    double d17 = 0.0d;
                    double d18 = 0.0d;
                    double d19 = 0.0d;
                    double d20 = 0.0d;
                    double d21 = 0.0d;
                    double d22 = 0.0d;
                    i6 = 0;
                    boolean z3 = true;
                    int i12 = 0;
                    int i13 = 1;
                    String str45 = str33;
                    while (i6 < cursor.getCount()) {
                        try {
                            if (cursor.moveToPosition(i6)) {
                                d3 = d8;
                                d4 = d9;
                                d2 = d10;
                                if (cursor.getInt(cursor.getColumnIndex("LOT")) != 1) {
                                    boolean z4 = cursor.getInt(cursor.getColumnIndex("VATINC")) == 1;
                                    ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    i8 = i11;
                                    i7 = i6;
                                    ColType colType3 = ColType.metin;
                                    if (StringUtils.convertStringToInt(logoSqlHelper3.dbVal(cursor, "VARIANTREF", colType3).toString()) > 0) {
                                        str16 = (str45 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "VCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "VNAME", colType3).toString() + "\n\t";
                                        str4 = str25;
                                        str15 = "DISCTOTAL4";
                                        str6 = str38;
                                        str14 = "DISCTOTAL5";
                                    } else {
                                        String obj2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str25, colType3).toString();
                                        str4 = str25;
                                        String str46 = str38;
                                        String obj3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str46, colType3).toString();
                                        str6 = str46;
                                        str14 = "DISCTOTAL5";
                                        if (StringUtils.paramValueNumberCheck(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode))) {
                                            String obj4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "ICUSTSUPCODE", colType3).toString();
                                            str15 = "DISCTOTAL4";
                                            String obj5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "ICUSTSUPNAME", colType3).toString();
                                            String sb12 = str45 +
                                                    (obj4.isEmpty() ? obj2 + "\n\t" : obj4 + "\n\t");
                                            StringBuilder sb13 = new StringBuilder();
                                            sb13.append(sb12);
                                            if (obj5.isEmpty()) {
                                                sb = new StringBuilder();
                                                sb.append(obj3);
                                                sb.append("\n\t");
                                            } else {
                                                sb = new StringBuilder();
                                                sb.append(obj5);
                                                sb.append("\n\t");
                                            }
                                            sb13.append(sb.toString());
                                            str16 = sb13.toString();
                                        } else {
                                            str15 = "DISCTOTAL4";
                                            str16 = (str45 + obj2 + "\n\t") + obj3 + "\n\t";
                                        }
                                    }
                                    String str47 = ((((str16 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "LOGO_ORDERNO", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "PAYCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "PAYNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "UCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "UCODE", colType3).toString() + "\n\t";
                                    String str48 = str39;
                                    String sb15 = str47 +
                                            ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str48, colType3).toString() +
                                            "\n\t";
                                    String str49 = str37;
                                    String sb16 = sb15 +
                                            ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str49, colType3).toString() +
                                            "\n\t";
                                    String str50 = sb16 + str33 + "\n\t";
                                    ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    ColType colType4 = ColType.virgullu;
                                    str39 = str48;
                                    double d23 = StringUtils.toDouble(logoSqlHelper4.dbVal(cursor, "AMOUNT", colType4).toString());
                                    d11 += d23;
                                    String str51 = str50 + checkIsDivUnitForAmountPrintValueOffline(cursor, d23, "\n\t");
                                    cursor3 = invoiceHedaerForPrint;
                                    double d24 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "PRICE", colType4).toString());
                                    str10 = str49;
                                    String str52 = str27;
                                    double d25 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str52, colType4).toString());
                                    if (z4) {
                                        d24 = CalculateUtils.calculateIncludeVatPrice(d24, d25);
                                    }
                                    double d26 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "TOTAL", colType4).toString());
                                    if (z4) {
                                        d26 = d24 * d23;
                                    }
                                    String str53 = (((str51 + StringUtils.dFormat(d24) + "\n\t") + StringUtils.dFormat(d26) + "\n\t") + str33 + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str52, ColType.sayi).toString() + "\n\t";
                                    String str54 = str26;
                                    String sb18 = str53 +
                                            StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str54, colType4).toString())) +
                                            "\n\t";
                                    str13 = str33;
                                    double d27 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "VATMATRAH", colType4).toString());
                                    double d28 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str54, colType4).toString());
                                    double d29 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str52, colType4).toString());
                                    d12 += d26;
                                    d13 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "LINENET", colType4).toString());
                                    d14 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str54, colType4).toString());
                                    int i14 = 0;
                                    while (true) {
                                        if (i14 >= 5) {
                                            break;
                                        }
                                        double d30 = dArr[i14];
                                        if (d30 == d29) {
                                            dArr2[i14] = dArr2[i14] + d28;
                                            dArr5[i14] = dArr5[i14] + d28;
                                            dArr3[i14] = dArr3[i14] + d27;
                                            break;
                                        }
                                        if (d30 == 0.0d) {
                                            dArr[i14] = d29;
                                            dArr2[i14] = d28;
                                            dArr4[i14] = d29;
                                            dArr5[i14] = d28;
                                            dArr3[i14] = d27;
                                            break;
                                        }
                                        i14++;
                                    }
                                    StringBuilder sb19 = new StringBuilder();
                                    sb19.append(sb18);
                                    ISqlHelper logoSqlHelper5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    ColType colType5 = ColType.virgullu;
                                    sb19.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper5.dbVal(cursor, "DISCRATIO1", colType5).toString())));
                                    sb19.append("\n\t");
                                    String str55 = sb19.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCTOTAL1", colType5).toString())) + "\n\t";
                                    double d31 = d15 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCTOTAL1", colType5).toString());
                                    ISqlHelper logoSqlHelper6 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    ColType colType6 = ColType.metin;
                                    StringUtils.toDouble(logoSqlHelper6.dbVal(cursor, "DISCTOTAL1", colType6).toString());
                                    StringBuilder sb20 = new StringBuilder();
                                    sb20.append(str55);
                                    str5 = str52;
                                    sb20.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCRATIO2", colType5).toString())));
                                    sb20.append("\n\t");
                                    String sb21 = sb20.toString();
                                    StringBuilder sb22 = new StringBuilder();
                                    sb22.append(sb21);
                                    str17 = "DISCTOTAL2";
                                    sb22.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str17, colType5).toString())));
                                    sb22.append("\n\t");
                                    String sb23 = sb22.toString();
                                    double d32 = d31 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str17, colType5).toString());
                                    StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str17, colType5).toString());
                                    StringBuilder sb24 = new StringBuilder();
                                    sb24.append(sb23);
                                    str18 = str54;
                                    sb24.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCRATIO3", colType5).toString())));
                                    sb24.append("\n\t");
                                    String str56 = sb24.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCTOTAL3", colType5).toString())) + "\n\t";
                                    double d33 = d32 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCTOTAL3", colType5).toString());
                                    StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCTOTAL3", colType5).toString());
                                    StringBuilder sb25 = new StringBuilder();
                                    sb25.append(str56);
                                    str19 = "DISCTOTAL3";
                                    sb25.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCRATIO4", colType5).toString())));
                                    sb25.append("\n\t");
                                    String sb26 = sb25.toString();
                                    String str57 = str15;
                                    String sb28 = sb26 +
                                            StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str57, colType5).toString())) +
                                            "\n\t";
                                    double d34 = d33 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str57, colType6).toString());
                                    StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str57, colType5).toString());
                                    StringBuilder sb29 = new StringBuilder();
                                    sb29.append(sb28);
                                    str20 = str57;
                                    sb29.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DISCRATIO5", colType5).toString())));
                                    sb29.append("\n\t");
                                    String sb30 = sb29.toString();
                                    StringBuilder sb31 = new StringBuilder();
                                    sb31.append(sb30);
                                    str21 = str14;
                                    sb31.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str21, colType5).toString())));
                                    sb31.append("\n\t");
                                    String sb32 = sb31.toString();
                                    d15 = d34 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str21, colType5).toString());
                                    StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, str21, colType5).toString());
                                    String str58 = ((((((((((((((sb32 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "DESC", colType6).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "GTIP", colType6).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "Barkod", colType6).toString() + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "PRICE", colType6).toString() + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.dFormat(d27) + "\n\t") + StringUtils.dFormat(d27 + d28) + "\n\t";
                                    String sb34 = str58 +
                                            (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d26 / salesTrRateFromHeader)) +
                                            "\n\t";
                                    String sb35 = sb34 +
                                            (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d24 / salesTrRateFromHeader)) +
                                            "\n\t";
                                    String str59 = sb35 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "PNAME2", colType6).toString() + "\n\t";
                                    for (int i15 = 0; i15 < 21; i15++) {
                                        str59 = str59 + "\n\t";
                                    }
                                    StringBuilder sb36 = new StringBuilder();
                                    sb36.append(str59);
                                    ISqlHelper logoSqlHelper7 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    ColType colType7 = ColType.metin;
                                    sb36.append(logoSqlHelper7.dbVal(cursor, "WIDTH", colType7).toString());
                                    sb36.append("\n\t");
                                    str22 = (sb36.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "LENGTH", colType7).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "HEIGHT", colType7).toString() + "\n\t\r";
                                    ISqlHelper logoSqlHelper8 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    ColType colType8 = ColType.virgullu;
                                    d16 += StringUtils.toDouble(logoSqlHelper8.dbVal(cursor, "GROSSWEIGHT", colType8).toString()) * d23;
                                    d17 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "WEIGHT", colType8).toString()) * d23;
                                    d18 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "VOLUME", colType8).toString()) * d23;
                                } else {
                                    String str60 = str45 + str33 + "\n\t";
                                    int itemTrackType = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getItemTrackType(cursor.getInt(cursor.getColumnIndex("ITEMREF")));
                                    String sb38 = str60 +
                                            getLotInfo(cursor, itemTrackType == TrackType.LOT.getType()) +
                                            "\n\t";
                                    for (int i16 = 0; i16 < 37; i16++) {
                                        sb38 = sb38 + str33 + "\n\t";
                                    }
                                    i7 = i6;
                                    str4 = str25;
                                    str21 = "DISCTOTAL5";
                                    str20 = "DISCTOTAL4";
                                    i8 = i11;
                                    str17 = "DISCTOTAL2";
                                    str18 = str26;
                                    str5 = str27;
                                    str6 = str38;
                                    str10 = str37;
                                    str13 = str33;
                                    cursor3 = invoiceHedaerForPrint;
                                    str22 = sb38 + "\r";
                                    str19 = "DISCTOTAL3";
                                }
                                int i17 = i7 + 1;
                                if (i17 != cursor.getCount() && i17 != i8 * i13) {
                                    str45 = str22;
                                    str7 = str31;
                                    i9 = i10;
                                    str8 = str2;
                                    str9 = str34;
                                    cursor2 = cursor3;
                                    d5 = d7;
                                    str11 = str44;
                                    str12 = str18;
                                }
                                if (d12 < 0.0d) {
                                    d12 = 0.0d;
                                }
                                d21 += d13 + d14;
                                d20 += d12;
                                d22 += d11;
                                String str61 = str13;
                                for (int i18 = 0; i18 <= 4; i18++) {
                                    str61 = (str61 + StringUtils.dFormat(dArr[i18]) + "\n\t") + StringUtils.dFormat(dArr2[i18]) + "\n\t";
                                }
                                String str62 = (str61 + StringUtils.dFormat(d12) + "\n\t") + StringUtils.dFormat(d20) + "\n\t";
                                for (int i19 = 0; i19 <= 4; i19++) {
                                    str62 = str62 + StringUtils.dFormat(dArr3[i19]) + "\n\t";
                                }
                                StringBuilder sb39 = new StringBuilder();
                                sb39.append(str62);
                                ISqlHelper logoSqlHelper9 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType9 = ColType.metin;
                                str8 = str2;
                                cursor2 = cursor3;
                                sb39.append(logoSqlHelper9.dbVal(cursor2, str8, colType9).toString());
                                sb39.append("\n\t");
                                String sb40 = sb39.toString();
                                StringBuilder sb41 = new StringBuilder();
                                sb41.append(sb40);
                                str9 = str34;
                                sb41.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str9, colType9).toString());
                                sb41.append("\n\t");
                                String sb42 = sb41.toString();
                                StringBuilder sb43 = new StringBuilder();
                                sb43.append(sb42);
                                str7 = str31;
                                sb43.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str7, colType9).toString());
                                sb43.append("\n\t");
                                String sb44 = sb43.toString();
                                String str63 = str29;
                                String sb45 = sb44 +
                                        ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str63, colType9).toString() +
                                        "\n\t";
                                String str64 = ((((sb45 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                                String sb46 = str64 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d2 / salesTrRateFromHeader)) +
                                        "\n\t";
                                String str65 = (sb46 + StringUtils.dFormat(d11) + "\n\t") + cursor.getCount() + "\n\t";
                                String str66 = str22;
                                String str67 = str13;
                                int i20 = 0;
                                for (int i21 = 4; i20 <= i21; i21 = 4) {
                                    str67 = (str67 + StringUtils.dFormat(dArr4[i20]) + "\n\t") + StringUtils.dFormat(dArr5[i20]) + "\n\t";
                                    i20++;
                                }
                                String str68 = (((((str67 + StringUtils.dFormat(d2) + "\n\t") + StringUtils.dFormat(d4) + "\n\t") + StringUtils.dFormat(d7) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d19) + "\n\t";
                                StringBuilder sb47 = new StringBuilder();
                                sb47.append(str68);
                                ISqlHelper logoSqlHelper10 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType10 = ColType.virgullu;
                                sb47.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper10.dbVal(cursor2, "DISCRATIO1", colType10).toString())));
                                sb47.append("\n\t");
                                String str69 = ((((((((sb47.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCTOTAL1", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO2", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str17, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO3", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str19, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO4", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str20, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATIO5", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str21, colType10).toString())) + "\n\t";
                                Cursor cursor5 = cursor4;
                                String sb48 = str69 +
                                        addAccountBalanceInfo(cursor2, cursor5);
                                String str70 = ((sb48 + StringUtils.convertTotal2Text(StringUtils.dFormat(d7), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d21) + "\n\t") + StringUtils.dFormat(d2) + "\n\t";
                                for (int i22 = 0; i22 <= 4; i22++) {
                                    str70 = str70 + StringUtils.dFormat(dArr3[i22]) + "\n\t";
                                }
                                StringBuilder sb49 = new StringBuilder();
                                sb49.append(str70);
                                ISqlHelper logoSqlHelper11 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                ColType colType11 = ColType.metin;
                                sb49.append(logoSqlHelper11.dbVal(cursor2, str8, colType11).toString());
                                sb49.append("\n\t");
                                String str71 = ((((((sb49.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str9, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str7, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str63, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATE", colType11).toString() + "\n\t") + StringUtils.dFormat(d21) + "\n\t";
                                StringBuilder sb50 = new StringBuilder();
                                sb50.append(str71);
                                String str72 = str40;
                                SalesType salesType = str72.equals(BuildConfig.NETSIS_DEMO_PASSWORD) ? SalesType.RETURN_INVOICE : SalesType.INVOICE;
                                str40 = str72;
                                str29 = str63;
                                d5 = d7;
                                sb50.append(addBeforeBalanceInfo(cursor2, salesType, d5, cursor5));
                                String str73 = sb50.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t";
                                StringBuilder sb51 = new StringBuilder();
                                sb51.append(str73);
                                str11 = str44;
                                sb51.append(str11);
                                sb51.append("\n\t");
                                String str74 = sb51.toString() + getClCardCurrencyCode(i5) + "\n\t";
                                String sb53 = str74 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(salesTrRateFromHeader)) +
                                        "\n\t";
                                double d35 = d5 / salesTrRateFromHeader;
                                int i23 = i10;
                                String sb55 = sb53 +
                                        StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d35), i23) +
                                        "\n\t";
                                String sb57 = sb55 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d35)) +
                                        "\n\t";
                                String sb58 = sb57 +
                                        (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d3 / salesTrRateFromHeader)) +
                                        "\n\t";
                                String str75 = ((((sb58 + "\n\t") + StringUtils.dFormat(d22) + "\n\t") + StringUtils.dFormat(d16) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d17) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + cursor.getCount() + "\n\t";
                                for (int i24 = 0; i24 < 8; i24++) {
                                    str75 = str75 + "\n\t";
                                }
                                String str76 = str75 + StringUtils.dFormat(d18) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                                String str77 = (!z || z3) ? str43 : str13;
                                cursor4 = cursor5;
                                if (i17 == cursor.getCount()) {
                                    str23 = str76;
                                    if (z2) {
                                        str24 = str13;
                                        String Print = printProcess.Print(i8, str77, str66, str24, str23);
                                        if (i4 != 1) {
                                            strArr3[i12] = StringUtils.convTrCharEN(Print);
                                        } else {
                                            strArr3[i12] = Print;
                                        }
                                        i12++;
                                        i13++;
                                        str43 = str77;
                                        d11 = 0.0d;
                                        d12 = 0.0d;
                                        d13 = 0.0d;
                                        d14 = 0.0d;
                                        d19 = d21;
                                        str12 = str18;
                                        z3 = false;
                                        i9 = i23;
                                        str45 = str13;
                                        str44 = str11;
                                        d7 = d5;
                                        str2 = str8;
                                        str34 = str9;
                                        str31 = str7;
                                        str33 = str13;
                                        i10 = i9;
                                        i6 = i7 + 1;
                                        d8 = d3;
                                        d9 = d4;
                                        d10 = d2;
                                        str25 = str4;
                                        str38 = str6;
                                        str37 = str10;
                                        str27 = str5;
                                        str26 = str12;
                                        invoiceHedaerForPrint = cursor2;
                                        i11 = i8;
                                    }
                                } else {
                                    str23 = str13;
                                }
                                str24 = str65;
                                String Print2 = printProcess.Print(i8, str77, str66, str24, str23);
                                if (i4 != 1) {
                                }
                                i12++;
                                i13++;
                                str43 = str77;
                                d11 = 0.0d;
                                d12 = 0.0d;
                                d13 = 0.0d;
                                d14 = 0.0d;
                                d19 = d21;
                                str12 = str18;
                                z3 = false;
                                i9 = i23;
                                str45 = str13;
                                str44 = str11;
                                d7 = d5;
                                str2 = str8;
                                str34 = str9;
                                str31 = str7;
                                str33 = str13;
                                i10 = i9;
                                i6 = i7 + 1;
                                d8 = d3;
                                d9 = d4;
                                d10 = d2;
                                str25 = str4;
                                str38 = str6;
                                str37 = str10;
                                str27 = str5;
                                str26 = str12;
                                invoiceHedaerForPrint = cursor2;
                                i11 = i8;
                            } else {
                                i7 = i6;
                                str4 = str25;
                                d2 = d10;
                                d3 = d8;
                                i8 = i11;
                                d4 = d9;
                                str5 = str27;
                                str6 = str38;
                                str7 = str31;
                                i9 = i10;
                                str8 = str2;
                                str9 = str34;
                                str10 = str37;
                                d5 = d7;
                                str11 = str44;
                                cursor2 = invoiceHedaerForPrint;
                                str12 = str26;
                                str13 = str33;
                            }
                            str44 = str11;
                            d7 = d5;
                            str2 = str8;
                            str34 = str9;
                            str31 = str7;
                            str33 = str13;
                            i10 = i9;
                            i6 = i7 + 1;
                            d8 = d3;
                            d9 = d4;
                            d10 = d2;
                            str25 = str4;
                            str38 = str6;
                            str37 = str10;
                            str27 = str5;
                            str26 = str12;
                            invoiceHedaerForPrint = cursor2;
                            i11 = i8;
                        } catch (Exception e2) {
                            strArr = strArr3;
                            exc = e2;
                            strArr[0] = exc.getMessage();
                            return strArr;
                        }
                    }
                    return strArr3;
                }
            }
            str2 = "DESC1";
            str3 = str33;
            String str362 = ((str35 + str3 + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
            ISqlHelper logoSqlHelper22 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            String str372 = "SPECODE";
            ColType colType22 = ColType.virgullu;
            double d62 = StringUtils.toDouble(logoSqlHelper22.dbVal(invoiceHedaerForPrint, "TOTALNET", colType22).toString());
            String str382 = "PNAME";
            String str392 = "WAREHOUSENR";
            String obj6 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "INVTYPE", colType).toString();
            String sb92 = str362 +
                    addBeforeBalanceInfo(invoiceHedaerForPrint, !obj6.equals(BuildConfig.NETSIS_DEMO_PASSWORD) ? SalesType.RETURN_INVOICE : SalesType.INVOICE, d62, customerUnsentAccountBalance);
            String str402 = obj6;
            String sb102 = sb92 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) +
                    "\n\t";
            String str412 = (((sb102 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLATITUTE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLONGITUDE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TC", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CNAME2", colType).toString() + "\n\t";
            Cursor cursor42 = customerUnsentAccountBalance;
            double d72 = d62;
            String receiptTotalFromInvoice2 = ErpCreator.getInstance().getmBaseErp().getReceiptTotalFromInvoice(i2, true, StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DATEINT", ColType.sayi).toString()));
            String str422 = (((str412 + receiptTotalFromInvoice2 + "\n\t") + getPaymentDate(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PAYPLANREF", colType).toString()), ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString()) + "\n\t") + userCode + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ANDFICHENO", colType).toString() + "\n\t";
            double d82 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TOTALVAT", colType22).toString());
            double d92 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCTOTAL", colType22).toString());
            double d102 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "GTOTAL", colType22).toString()) - d92;
            if (i3 > 0) {
            }
            String[] strArr32 = new String[(cursor.getCount() / i11) + 1];
            String str432 = str422;
            String str442 = receiptTotalFromInvoice2;
            double d112 = 0.0d;
            double d122 = 0.0d;
            double d132 = 0.0d;
            double d142 = 0.0d;
            double d152 = 0.0d;
            double d162 = 0.0d;
            double d172 = 0.0d;
            double d182 = 0.0d;
            double d192 = 0.0d;
            double d202 = 0.0d;
            double d212 = 0.0d;
            double d222 = 0.0d;
            i6 = 0;
            boolean z32 = true;
            int i122 = 0;
            int i132 = 1;
            String str452 = str33;
            while (i6 < cursor.getCount()) {
            }
            return strArr32;
        } catch (Exception e3) {
            exc = e3;
            strArr = strArr2;
        }
    }
    private static String getClCardCurrencyCode(int i2) {
        int clCardCurrency = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getClCardCurrency(i2);
        if (clCardCurrency == 0) {
            return "";
        }
        return ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCurrCode(clCardCurrency);
    }
    private static String getLotInfo(Cursor cursor, boolean z) {
        String string = cursor.getString(cursor.getColumnIndex("CODE"));
        if (z) {
            string = (string + " - " + cursor.getDouble(cursor.getColumnIndex("LOTAMOUNT"))) + " - " + DateAndTimeUtils.convertStringDate(cursor.getString(cursor.getColumnIndex("EXPDATE")), "dd-MM-yyyy", "dd.MM.yyyy");
        }
        return string + " - " + cursor.getString(cursor.getColumnIndex("LOTNAME"));
    }
    public static String[] PrintDispatchFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        Exception exc;
        String[] strArr;
        int i6;
        String str2;
        double d2;
        double d3;
        double d4;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        boolean z3;
        int i7;
        String str11;
        int i8;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        StringBuilder sb;
        String str17;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        int i9;
        String str24;
        String str25;
        String str26 = "PCODE";
        String str27 = "WAREHOUSENR";
        String str28 = "VATAMNT";
        String str29 = "VAT";
        String[] strArr2 = new String[1];
        double[] dArr = new double[5];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        boolean printLotDetail = ErpCreator.getInstance().getmBaseErp().getPrintLotDetail();
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            Cursor invoiceHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getInvoiceHedaerForPrint(i2);
            Cursor invoiceDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getInvoiceDetailForPrint(i2, printLotDetail, i5);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            invoiceHedaerForPrint.moveToPosition(0);
            int salesTrCurrFromHeader = getSalesTrCurrFromHeader(invoiceHedaerForPrint);
            double salesTrRateFromHeader = getSalesTrRateFromHeader(invoiceHedaerForPrint);
            int i10 = salesTrCurrFromHeader;
            StringBuilder sb2 = new StringBuilder();
            String str30 = "";
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            Cursor cursor = invoiceDetailForPrint;
            ColType colType = ColType.metin;
            sb2.append(logoSqlHelper.dbVal(invoiceHedaerForPrint, "FICHENO", colType).toString());
            sb2.append("\n\t");
            String str31 = (((((((((((((sb2.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DOCNO", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SHIPTYPE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "SHIPAGENT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TRADINGGRP", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "WAREHOUSENR", colType).toString() + "\n\t";
            String userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
            String str32 = "DESC4";
            String sb3 = str31 + userCode + "\n\t" +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) +
                    "\n\t";
            String str33 = sb3 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PCODE", colType).toString() + "\n\t";
            String str34 = "PNAME";
            String sb5 = str33 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str34, colType).toString() +
                    "\n\t";
            String str35 = "DESC3";
            String sb6 = sb5 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FCODE", colType).toString() +
                    "\n\t";
            String str36 = (((((((((((((((((((((((((((((sb6 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(invoiceHedaerForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.getTimeOnly(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "GDATE", colType).toString()) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "RDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
            ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            String str37 = "DESC2";
            ColType colType2 = ColType.virgullu;
            double d5 = StringUtils.toDouble(logoSqlHelper2.dbVal(invoiceHedaerForPrint, "TOTALNET", colType2).toString());
            String str38 = "DESC1";
            String str39 = "SPECODE";
            String obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "INVTYPE", colType).toString();
            String sb7 = str36 +
                    addBeforeBalanceInfo(invoiceHedaerForPrint, obj.equals(BuildConfig.NETSIS_DEMO_PASSWORD) ? SalesType.RETURN_DISPATCH : SalesType.DISPATCH, d5, customerUnsentAccountBalance);
            String str40 = ((((((sb7 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLATITUTE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FLONGITUDE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TC", colType).toString() + "\n\t") + getPaymentDate(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "PAYPLANREF", colType).toString()), ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "FICHEDATE", colType).toString()) + "\n\t") + userCode + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "ANDFICHENO", colType).toString() + "\n\t";
            double d6 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "TOTALVAT", colType2).toString());
            double d7 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCTOTAL", colType2).toString());
            double d8 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "GTOTAL", colType2).toString()) - d7;
            int i11 = i3 <= 0 ? 1 : i3;
            String[] strArr3 = new String[(cursor.getCount() / i11) + 1];
            String str41 = str40;
            boolean z4 = true;
            int i12 = 1;
            String str42 = str30;
            double d9 = 0.0d;
            double d10 = 0.0d;
            double d11 = 0.0d;
            double d12 = 0.0d;
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            double d16 = 0.0d;
            double d17 = 0.0d;
            double d18 = 0.0d;
            double d19 = 0.0d;
            double d20 = 0.0d;
            int i13 = 0;
            int i14 = 0;
            while (true) {
                try {
                    Cursor cursor2 = customerUnsentAccountBalance;
                    if (i13 > cursor.getCount() - 1) {
                        return strArr3;
                    }
                    Cursor cursor3 = cursor;
                    if (cursor3.moveToPosition(i13)) {
                        d2 = d6;
                        d3 = d5;
                        d4 = d8;
                        i6 = i11;
                        if (cursor3.getInt(cursor3.getColumnIndex("LOT")) != 1) {
                            String str43 = str30;
                            i8 = i13;
                            str12 = str43;
                            boolean z5 = cursor3.getInt(cursor3.getColumnIndex("VATINC")) == 1;
                            ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType3 = ColType.metin;
                            if (StringUtils.convertStringToInt(logoSqlHelper3.dbVal(cursor3, "VARIANTREF", colType3).toString()) > 0) {
                                str16 = (str42 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "VCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "VNAME", colType3).toString() + "\n\t";
                                str2 = str26;
                                str15 = "DISCTOTAL3";
                                str3 = str34;
                            } else {
                                String obj2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str26, colType3).toString();
                                String obj3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str34, colType3).toString();
                                str2 = str26;
                                str3 = str34;
                                if (StringUtils.paramValueNumberCheck(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode))) {
                                    String obj4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "ICUSTSUPCODE", colType3).toString();
                                    str15 = "DISCTOTAL3";
                                    String obj5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "ICUSTSUPNAME", colType3).toString();
                                    String sb9 = str42 +
                                            (obj4.isEmpty() ? obj2 + "\n\t" : obj4 + "\n\t");
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append(sb9);
                                    if (obj5.isEmpty()) {
                                        sb = new StringBuilder();
                                        sb.append(obj3);
                                        sb.append("\n\t");
                                    } else {
                                        sb = new StringBuilder();
                                        sb.append(obj5);
                                        sb.append("\n\t");
                                    }
                                    sb10.append(sb.toString());
                                    str16 = sb10.toString();
                                } else {
                                    str15 = "DISCTOTAL3";
                                    str16 = (str42 + obj2 + "\n\t") + obj3 + "\n\t";
                                }
                            }
                            String str44 = (((((str16 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "LOGO_ORDERNO", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "PAYCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "PAYNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "UCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "UCODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str27, colType3).toString() + "\n\t";
                            String str45 = str39;
                            String sb11 = str44 +
                                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str45, colType3).toString() +
                                    "\n\t";
                            String str46 = sb11 + str12 + "\n\t";
                            ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType4 = ColType.virgullu;
                            String str47 = str27;
                            double d21 = StringUtils.toDouble(logoSqlHelper4.dbVal(cursor3, "AMOUNT", colType4).toString());
                            d9 += d21;
                            String str48 = str46 + checkIsDivUnitForAmountPrintValueOffline(cursor3, d21, "\n\t");
                            str11 = str47;
                            double d22 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "PRICE", colType4).toString());
                            str10 = str45;
                            String str49 = str29;
                            double d23 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str49, colType4).toString());
                            if (z5) {
                                d22 = CalculateUtils.calculateIncludeVatPrice(d22, d23);
                            }
                            double d24 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "TOTAL", colType4).toString());
                            if (z5) {
                                d24 = d22 * d21;
                            }
                            String str50 = (((str48 + StringUtils.dFormat(d22) + "\n\t") + StringUtils.dFormat(d24) + "\n\t") + str12 + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str49, ColType.sayi).toString() + "\n\t";
                            String str51 = str28;
                            String sb13 = str50 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str51, colType4).toString())) +
                                    "\n\t";
                            double d25 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str51, colType4).toString());
                            double d26 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str49, colType4).toString());
                            str5 = str49;
                            double d27 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "VATMATRAH", colType4).toString());
                            d10 += d24;
                            d11 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "LINENET", colType4).toString());
                            d12 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str51, colType4).toString());
                            int i15 = 0;
                            while (true) {
                                if (i15 > 4) {
                                    break;
                                }
                                double d28 = dArr[i15];
                                if (d28 == d26) {
                                    dArr2[i15] = dArr2[i15] + d25;
                                    dArr5[i15] = dArr5[i15] + d25;
                                    dArr3[i15] = dArr3[i15] + d27;
                                    break;
                                }
                                if (d28 == 0.0d) {
                                    dArr[i15] = d26;
                                    dArr2[i15] = d25;
                                    dArr4[i15] = d26;
                                    dArr5[i15] = d25;
                                    dArr3[i15] = d27;
                                    break;
                                }
                                i15++;
                            }
                            StringBuilder sb14 = new StringBuilder();
                            sb14.append(sb13);
                            ISqlHelper logoSqlHelper5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType5 = ColType.virgullu;
                            sb14.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper5.dbVal(cursor3, "DISCRATIO1", colType5).toString())));
                            sb14.append("\n\t");
                            String sb15 = sb14.toString();
                            StringBuilder sb16 = new StringBuilder();
                            sb16.append(sb15);
                            str17 = "DISCTOTAL1";
                            sb16.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str17, colType5).toString())));
                            sb16.append("\n\t");
                            String sb17 = sb16.toString();
                            double d29 = d13 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str17, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str17, colType5).toString());
                            StringBuilder sb18 = new StringBuilder();
                            sb18.append(sb17);
                            str18 = str51;
                            sb18.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCRATIO2", colType5).toString())));
                            sb18.append("\n\t");
                            String str52 = sb18.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL2", colType5).toString())) + "\n\t";
                            double d30 = d29 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL2", colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL2", colType5).toString());
                            StringBuilder sb19 = new StringBuilder();
                            sb19.append(str52);
                            str19 = "DISCTOTAL2";
                            sb19.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCRATIO3", colType5).toString())));
                            sb19.append("\n\t");
                            String sb20 = sb19.toString();
                            String str53 = str15;
                            String sb22 = sb20 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str53, colType5).toString())) +
                                    "\n\t";
                            double d31 = d30 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str53, colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, str53, colType5).toString());
                            StringBuilder sb23 = new StringBuilder();
                            sb23.append(sb22);
                            str20 = str53;
                            sb23.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCRATIO4", colType5).toString())));
                            sb23.append("\n\t");
                            String str54 = sb23.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL4", colType5).toString())) + "\n\t";
                            double d32 = d31 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL4", colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL4", colType5).toString());
                            StringBuilder sb24 = new StringBuilder();
                            sb24.append(str54);
                            str21 = "DISCTOTAL4";
                            sb24.append(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCRATIO5", colType5).toString())));
                            sb24.append("\n\t");
                            String str55 = sb24.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL5", colType5).toString())) + "\n\t";
                            d13 = d32 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL5", colType5).toString());
                            StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "DISCTOTAL5", colType5).toString());
                            StringBuilder sb25 = new StringBuilder();
                            sb25.append(str55);
                            ISqlHelper logoSqlHelper6 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            str22 = "DISCTOTAL5";
                            ColType colType6 = ColType.metin;
                            sb25.append(logoSqlHelper6.dbVal(cursor3, "DESC", colType6).toString());
                            sb25.append("\n\t");
                            String str56 = (((((((((((((((sb25.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "GTIP", colType6).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "Barkod", colType6).toString() + "\n\t") + str12 + "\n\t") + str12 + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "PRICE", colType6).toString() + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.dFormat(d27) + "\n\t") + StringUtils.dFormat(d27 + d25) + "\n\t";
                            String sb27 = str56 +
                                    (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d24 / salesTrRateFromHeader)) +
                                    "\n\t";
                            String sb28 = sb27 +
                                    (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d22 / salesTrRateFromHeader)) +
                                    "\n\t";
                            String str57 = sb28 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "PNAME2", colType6).toString() + "\n\t";
                            for (int i16 = 0; i16 < 19; i16++) {
                                str57 = str57 + "\n\t";
                            }
                            StringBuilder sb29 = new StringBuilder();
                            sb29.append(str57);
                            ISqlHelper logoSqlHelper7 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType7 = ColType.metin;
                            sb29.append(logoSqlHelper7.dbVal(cursor3, "WIDTH", colType7).toString());
                            sb29.append("\n\t");
                            str23 = (sb29.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "LENGTH", colType7).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "HEIGHT", colType7).toString() + "\n\t\r";
                            ISqlHelper logoSqlHelper8 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType8 = ColType.virgullu;
                            d14 += StringUtils.toDouble(logoSqlHelper8.dbVal(cursor3, "GROSSWEIGHT", colType8).toString()) * d21;
                            d15 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "WEIGHT", colType8).toString()) * d21;
                            d16 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor3, "VOLUME", colType8).toString()) * d21;
                        } else {
                            int itemTrackType = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getItemTrackType(cursor3.getInt(cursor3.getColumnIndex("ITEMREF")));
                            String str58 = str30;
                            String sb31 = str42 +
                                    str58 +
                                    "\n\t";
                            i8 = i13;
                            String sb33 = sb31 +
                                    getLotInfo(cursor3, itemTrackType == TrackType.LOT.getType()) +
                                    "\n\t";
                            for (int i17 = 0; i17 < 37; i17++) {
                                sb33 = sb33 + str58 + "\n\t";
                            }
                            str23 = sb33 + "\r";
                            str2 = str26;
                            str22 = "DISCTOTAL5";
                            str12 = str58;
                            str21 = "DISCTOTAL4";
                            str20 = "DISCTOTAL3";
                            str19 = "DISCTOTAL2";
                            str17 = "DISCTOTAL1";
                            str3 = str34;
                            str10 = str39;
                            str11 = str27;
                            String str59 = str29;
                            str18 = str28;
                            str5 = str59;
                        }
                        int i18 = i8 + 1;
                        if (i18 != cursor3.getCount() && i18 != i6 * i12) {
                            i7 = i10;
                            str14 = str41;
                            str6 = str35;
                            str7 = str32;
                            str8 = str37;
                            str9 = str38;
                            z3 = true;
                            String str60 = str18;
                            str13 = str23;
                            str4 = str60;
                        }
                        if (d10 < 0.0d) {
                            d10 = 0.0d;
                        }
                        d20 += d11 + d12;
                        d19 += d10;
                        d17 += d9;
                        String str61 = str12;
                        int i19 = 0;
                        while (true) {
                            if (i19 > 4) {
                                break;
                            }
                            str61 = (str61 + StringUtils.dFormat(dArr[i19]) + "\n\t") + StringUtils.dFormat(dArr2[i19]) + "\n\t";
                            i19++;
                        }
                        String str62 = (str61 + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d19) + "\n\t";
                        for (int i20 = 0; i20 <= 4; i20++) {
                            str62 = str62 + StringUtils.dFormat(dArr3[i20]) + "\n\t";
                        }
                        StringBuilder sb34 = new StringBuilder();
                        sb34.append(str62);
                        ISqlHelper logoSqlHelper9 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                        ColType colType9 = ColType.metin;
                        str9 = str38;
                        sb34.append(logoSqlHelper9.dbVal(invoiceHedaerForPrint, str9, colType9).toString());
                        sb34.append("\n\t");
                        String sb35 = sb34.toString();
                        StringBuilder sb36 = new StringBuilder();
                        sb36.append(sb35);
                        str8 = str37;
                        sb36.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str8, colType9).toString());
                        sb36.append("\n\t");
                        String sb37 = sb36.toString();
                        StringBuilder sb38 = new StringBuilder();
                        sb38.append(sb37);
                        str6 = str35;
                        sb38.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str6, colType9).toString());
                        sb38.append("\n\t");
                        String sb39 = sb38.toString();
                        StringBuilder sb40 = new StringBuilder();
                        sb40.append(sb39);
                        str7 = str32;
                        sb40.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str7, colType9).toString());
                        sb40.append("\n\t");
                        String str63 = ((((sb40.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                        String sb41 = str63 +
                                (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d4 / salesTrRateFromHeader)) +
                                "\n\t";
                        String str64 = (sb41 + StringUtils.dFormat(d9) + "\n\t") + cursor3.getCount() + "\n\t";
                        int i21 = 0;
                        String str65 = str12;
                        for (i9 = 4; i21 <= i9; i9 = 4) {
                            str65 = (str65 + StringUtils.dFormat(dArr4[i21]) + "\n\t") + StringUtils.dFormat(dArr5[i21]) + "\n\t";
                            i21++;
                        }
                        String str66 = (((((str65 + StringUtils.dFormat(d4) + "\n\t") + StringUtils.dFormat(d7) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + StringUtils.dFormat(d2) + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d18) + "\n\t";
                        StringBuilder sb42 = new StringBuilder();
                        sb42.append(str66);
                        ISqlHelper logoSqlHelper10 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                        String str67 = str23;
                        ColType colType10 = ColType.virgullu;
                        sb42.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper10.dbVal(invoiceHedaerForPrint, "DISCRATIO1", colType10).toString())));
                        sb42.append("\n\t");
                        String str68 = (((((((((sb42.toString() + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str17, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATIO2", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str19, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATIO3", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str20, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATIO4", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str21, colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATIO5", colType10).toString())) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str22, colType10).toString())) + "\n\t") + addAccountBalanceInfo(invoiceHedaerForPrint, cursor2);
                        StringBuilder sb43 = new StringBuilder();
                        sb43.append(str68);
                        cursor2 = cursor2;
                        sb43.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d3), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                        sb43.append("\n\t");
                        String str69 = ((sb43.toString() + StringUtils.dFormat(d20) + "\n\t") + str12 + "\n\t") + StringUtils.dFormat(d4) + "\n\t";
                        for (int i22 = 0; i22 < 5; i22++) {
                            str69 = str69 + StringUtils.dFormat(dArr3[i22]) + "\n\t";
                        }
                        StringBuilder sb44 = new StringBuilder();
                        sb44.append(str69);
                        ISqlHelper logoSqlHelper11 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                        ColType colType11 = ColType.metin;
                        sb44.append(logoSqlHelper11.dbVal(invoiceHedaerForPrint, str9, colType11).toString());
                        sb44.append("\n\t");
                        String str70 = (((((((sb44.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str8, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str6, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, str7, colType11).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(invoiceHedaerForPrint, "DISCRATE", colType11).toString() + "\n\t") + StringUtils.dFormat(d20) + "\n\t") + getClCardCurrencyCode(i5) + "\n\t";
                        String sb46 = str70 +
                                (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(salesTrRateFromHeader)) +
                                "\n\t";
                        StringBuilder sb47 = new StringBuilder();
                        sb47.append(sb46);
                        double d33 = d3 / salesTrRateFromHeader;
                        i7 = i10;
                        sb47.append(StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d33), i7));
                        sb47.append("\n\t");
                        String sb48 = sb47.toString();
                        String sb50 = sb48 +
                                (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d33)) +
                                "\n\t";
                        String sb51 = sb50 +
                                (salesTrRateFromHeader != 0.0d ? StringUtils.dFormat(d2 / salesTrRateFromHeader) : "0.00") +
                                "\n\t";
                        String str71 = ((((sb51 + "\n\t") + StringUtils.dFormat(d17) + "\n\t") + StringUtils.dFormat(d14) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d15) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + cursor3.getCount() + "\n\t";
                        for (int i23 = 0; i23 < 10; i23++) {
                            str71 = str71 + "\n\t";
                        }
                        String str72 = str71 + StringUtils.dFormat(d16) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                        str14 = (!z || z4) ? str41 : str12;
                        if (i18 != cursor3.getCount()) {
                            str24 = str12;
                        } else if (z2) {
                            str25 = str12;
                            str24 = str72;
                            String Print = printProcess.Print(i6, str14, str67, str25, str24);
                            z3 = true;
                            if (i4 != 1) {
                                strArr3[i14] = StringUtils.convTrCharEN(Print);
                            } else {
                                strArr3[i14] = Print;
                            }
                            i14++;
                            i12++;
                            str4 = str18;
                            d9 = 0.0d;
                            d10 = 0.0d;
                            d11 = 0.0d;
                            d12 = 0.0d;
                            d18 = d20;
                            z4 = false;
                            str13 = str12;
                        } else {
                            str24 = str72;
                        }
                        str25 = str64;
                        String Print2 = printProcess.Print(i6, str14, str67, str25, str24);
                        z3 = true;
                        if (i4 != 1) {
                        }
                        i14++;
                        i12++;
                        str4 = str18;
                        d9 = 0.0d;
                        d10 = 0.0d;
                        d11 = 0.0d;
                        d12 = 0.0d;
                        d18 = d20;
                        z4 = false;
                        str13 = str12;
                    } else {
                        i6 = i11;
                        str2 = str26;
                        d2 = d6;
                        d3 = d5;
                        d4 = d8;
                        str3 = str34;
                        str4 = str28;
                        str5 = str29;
                        str6 = str35;
                        str7 = str32;
                        str8 = str37;
                        str9 = str38;
                        str10 = str39;
                        z3 = true;
                        i7 = i10;
                        str11 = str27;
                        String str73 = str30;
                        i8 = i13;
                        str12 = str73;
                        str13 = str42;
                        str14 = str41;
                    }
                    int i24 = i8 + 1;
                    str30 = str12;
                    i10 = i7;
                    cursor = cursor3;
                    str41 = str14;
                    str38 = str9;
                    str37 = str8;
                    str35 = str6;
                    str32 = str7;
                    str42 = str13;
                    i13 = i24;
                    str27 = str11;
                    customerUnsentAccountBalance = cursor2;
                    d6 = d2;
                    d8 = d4;
                    str34 = str3;
                    str26 = str2;
                    str39 = str10;
                    str29 = str5;
                    d5 = d3;
                    str28 = str4;
                    i11 = i6;
                } catch (Exception e2) {
                    exc = e2;
                    strArr = strArr3;
                    strArr[0] = exc.getMessage();
                    return strArr;
                }
            }
        } catch (Exception e3) {
            exc = e3;
            strArr = strArr2;
        }
        return strArr;
    }
    public static String[] PrintCashFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        char c2;
        Exception exc;
        String str2;
        String str3 = "";
        String[] strArr;
        int i6 = 0;
        PrintProcess printProcess;
        double d2 = 0;
        String str4;
        String str5 = "SPECODE";
        String str6 = "TOTAL";
        String[] strArr2 = new String[1];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        try {
            Cursor caseCreditHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCaseCreditHedaerForPrint(i2);
            Cursor caseCreditDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCaseCreditDetailForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            caseCreditHedaerForPrint.moveToPosition(0);
            StringBuilder sb = new StringBuilder();
            try {
                ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                try {
                    ColType colType = ColType.metin;
                    sb.append(logoSqlHelper.dbVal(caseCreditHedaerForPrint, "FICHENO", colType).toString());
                    sb.append("\n\t");
                    String str7 = (((((((((((((((((((((((((((sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "GDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(caseCreditHedaerForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str7);
                    ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    ColType colType2 = ColType.virgullu;
                    sb2.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper2.dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString())));
                    sb2.append("\n\t");
                    String str8 = (((((((sb2 + StringUtils.convertTotal2Text(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString())), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + addBeforeBalanceInfo(caseCreditHedaerForPrint, SalesType.FREE, StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString()), customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t";
                    int i7 = i3 <= 0 ? 1 : i3;
                    strArr2 = new String[(caseCreditDetailForPrint.getCount() / i7) + 1];
                    String str9 = "";
                    double d3 = 0.0d;
                    double d4 = 0.0d;
                    boolean z3 = true;
                    int i8 = 0;
                    int i9 = 1;
                    int i10 = 0;
                    while (i8 < caseCreditDetailForPrint.getCount()) {
                        try {
                            if (caseCreditDetailForPrint.moveToPosition(i8)) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str9);
                                ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                String str10 = str8;
                                ColType colType3 = ColType.metin;
                                sb3.append(logoSqlHelper3.dbVal(caseCreditDetailForPrint, str5, colType3).toString());
                                sb3.append("\n\t");
                                String sb4 = sb3.toString();
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append(sb4);
                                ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                str2 = str5;
                                ColType colType4 = ColType.virgullu;
                                sb5.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper4.dbVal(caseCreditDetailForPrint, str6, colType4).toString())));
                                sb5.append("\n\t");
                                String sb6 = sb5.toString();
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append(sb6);
                                strArr = strArr2;
                                try {
                                    sb7.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, "DOCNO", colType3).toString());
                                    sb7.append("\n\t");
                                    String str11 = sb7.toString() + '\r';
                                    double d5 = d3 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, str6, colType4).toString());
                                    int i11 = i8 + 1;
                                    if (i11 != caseCreditDetailForPrint.getCount() && i11 != i7 * i9) {
                                        str9 = str11;
                                        str3 = str6;
                                        i6 = i8;
                                        printProcess = printProcess2;
                                        d2 = d5;
                                        str4 = str10;
                                    }
                                    if (d5 < 0.0d) {
                                        d5 = 0.0d;
                                    }
                                    d4 += d5;
                                    String str12 = ((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d4) + "\n\t";
                                    for (int i12 = 3; i12 <= 22; i12++) {
                                        try {
                                            str12 = str12 + "\n\t";
                                        } catch (Exception e2) {
                                            exc = e2;
                                            strArr2 = strArr;
                                            c2 = 0;
                                            strArr2[c2] = exc.getMessage();
                                            return strArr2;
                                        }
                                    }
                                    String str13 = (str12 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str14 = StringUtils.dFormat(d4) + "\n\t";
                                    StringBuilder sb8 = new StringBuilder();
                                    sb8.append(str14);
                                    str3 = str6;
                                    sb8.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d4), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                    sb8.append("\n\t");
                                    String sb9 = sb8.toString();
                                    for (int i13 = 2; i13 <= 22; i13++) {
                                        sb9 = sb9 + "\n\t";
                                    }
                                    String str15 = (sb9 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    str4 = (!z || z3) ? str10 : "";
                                    if (i11 != caseCreditDetailForPrint.getCount()) {
                                    }
                                    str13 = "";
                                    i6 = i8;
                                    c2 = 0;
                                    printProcess = printProcess2;
                                    try {
                                        String Print = printProcess2.Print(i7, str4, str11, str13, str15);
                                        if (i4 == 1) {
                                            strArr[i10] = StringUtils.convTrCharEN(Print);
                                        } else {
                                            strArr[i10] = Print;
                                        }
                                        i10++;
                                        i9++;
                                        str9 = "";
                                        d2 = 0.0d;
                                        z3 = false;
                                    } catch (Exception e3) {
                                        e = e3;
                                        exc = e;
                                        strArr2 = strArr;
                                        strArr2[c2] = exc.getMessage();
                                        return strArr2;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                    c2 = 0;
                                }
                            } else {
                                String str16 = str8;
                                str2 = str5;
                                str3 = str6;
                                strArr = strArr2;
                                i6 = i8;
                                printProcess = printProcess2;
                                d2 = d3;
                                str4 = str16;
                            }
                            i8 = i6 + 1;
                            str5 = str2;
                            strArr2 = strArr;
                            str6 = str3;
                            double d6 = d2;
                            printProcess2 = printProcess;
                            str8 = str4;
                            d3 = d6;
                        } catch (Exception e5) {
                            e = e5;
                            c2 = 0;
                            exc = e;
                            strArr2[c2] = exc.getMessage();
                            return strArr2;
                        }
                    }
                } catch (Exception e6) {
                    c2 = 0;
                    exc = e6;
                    strArr2 = strArr2;
                }
            } catch (Exception e7) {
                e = e7;
            }
        } catch (Exception e8) {
            e = e8;
            c2 = 0;
        }
        return strArr2;
    }
    public static String[] PrintCrediCardFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        char c2;
        Exception exc;
        String str2 = "";
        String[] strArr;
        int i6 = 0;
        PrintProcess printProcess = null;
        boolean z3;
        String str3 = "";
        String str4 = "SPECODE";
        String[] strArr2 = new String[1];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        try {
            Cursor caseCreditHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCaseCreditHedaerForPrint(i2);
            Cursor caseCreditDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCaseCreditDetailForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            caseCreditHedaerForPrint.moveToPosition(0);
            StringBuilder sb = new StringBuilder();
            try {
                ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                try {
                    ColType colType = ColType.metin;
                    sb.append(logoSqlHelper.dbVal(caseCreditHedaerForPrint, "FICHENO", colType).toString());
                    sb.append("\n\t");
                    String str5 = (((((((((((((((((((((((((((((((((sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "GDATE", colType).toString() + "\n\t") + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TRADINGGRP", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "BCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "BNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "BACODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "BANAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(caseCreditHedaerForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str5);
                    ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    ColType colType2 = ColType.virgullu;
                    sb2.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper2.dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString())));
                    sb2.append("\n\t");
                    String str6 = (((((((sb2 + StringUtils.convertTotal2Text(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString())), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + addBeforeBalanceInfo(caseCreditHedaerForPrint, SalesType.FREE, StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditHedaerForPrint, "TOTAL", colType2).toString()), customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t";
                    int i7 = i3 <= 0 ? 1 : i3;
                    strArr2 = new String[(caseCreditDetailForPrint.getCount() / i7) + 1];
                    String str7 = "";
                    boolean z4 = true;
                    int i8 = 1;
                    double d2 = 0.0d;
                    double d3 = 0.0d;
                    int i9 = 0;
                    int i10 = 0;
                    while (true) {
                        try {
                            String str8 = str6;
                            if (i9 > caseCreditDetailForPrint.getCount() - 1) {
                                break;
                            }
                            if (caseCreditDetailForPrint.moveToPosition(i9)) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str7);
                                ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                strArr = strArr2;
                                try {
                                    ColType colType3 = ColType.metin;
                                    sb3.append(logoSqlHelper3.dbVal(caseCreditDetailForPrint, str4, colType3).toString());
                                    sb3.append("\n\t");
                                    String sb4 = sb3.toString();
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append(sb4);
                                    ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    str2 = str4;
                                    ColType colType4 = ColType.virgullu;
                                    sb5.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper4.dbVal(caseCreditDetailForPrint, "TOTAL", colType4).toString())));
                                    sb5.append("\n\t");
                                    String sb6 = sb5.toString();
                                    PrintProcess printProcess3 = printProcess2;
                                    String sb7 = sb6 +
                                            ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, "PAYCODE", colType3).toString() +
                                            "\n\t";
                                    String str9 = ((sb7 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, "PAYNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, "DOCNO", colType3).toString() + "\n\t") + '\r';
                                    d2 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseCreditDetailForPrint, "TOTAL", colType4).toString());
                                    int i11 = i9 + 1;
                                    if (i11 != caseCreditDetailForPrint.getCount() && i11 != i7 * i8) {
                                        str3 = str8;
                                        str7 = str9;
                                        i6 = i9;
                                        printProcess = printProcess3;
                                        z3 = true;
                                    }
                                    if (d2 < 0.0d) {
                                        d2 = 0.0d;
                                    }
                                    d3 += d2;
                                    String str10 = ((((StringUtils.dFormat(d2) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d2), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str11 = (((StringUtils.dFormat(d3) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d3), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    str3 = (!z || z4) ? str8 : "";
                                    if (i11 != caseCreditDetailForPrint.getCount()) {
                                    }
                                    str10 = "";
                                    i6 = i9;
                                    c2 = 0;
                                    printProcess = printProcess3;
                                    try {
                                        String Print = printProcess3.Print(i7, str3, str9, str10, str11);
                                        z3 = true;
                                        if (i4 == 1) {
                                            strArr[i10] = StringUtils.convTrCharEN(Print);
                                        } else {
                                            strArr[i10] = Print;
                                        }
                                        i10++;
                                        i8++;
                                        str7 = "";
                                        d2 = 0.0d;
                                        z4 = false;
                                    } catch (Exception e2) {
                                        e = e2;
                                        exc = e;
                                        strArr2 = strArr;
                                        strArr2[c2] = exc.getMessage();
                                        return strArr2;
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    c2 = 0;
                                }
                            } else {
                                str2 = str4;
                                strArr = strArr2;
                                i6 = i9;
                                printProcess = printProcess2;
                                z3 = true;
                                str3 = str8;
                            }
                            i9 = i6 + 1;
                            strArr2 = strArr;
                            str4 = str2;
                            printProcess2 = printProcess;
                            str6 = str3;
                        } catch (Exception e4) {
                            e = e4;
                            c2 = 0;
                            exc = e;
                            strArr2[c2] = exc.getMessage();
                            return strArr2;
                        }
                    }
                } catch (Exception e5) {
                    c2 = 0;
                    exc = e5;
                    strArr2 = strArr2;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Exception e7) {
            e = e7;
            c2 = 0;
        }
        return strArr2;
    }
    public static String[] PrintCaseFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        int i6;
        Exception exc;
        Cursor caseForPrint;
        String str2;
        String obj = "";
        String userCode = "";
        String userName;
        int i7;
        int i8;
        String[] strArr = new String[0];
        String str3;
        int i9;
        Cursor cursor;
        PrintProcess printProcess;
        boolean z3;
        double d2;
        String str4;
        String str5 = "";
        String[] strArr2 = new String[1];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        Exception e;
        try {
            caseForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCaseForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            caseForPrint.moveToPosition(0);
            StringBuilder sb = new StringBuilder();
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            try {
                ColType colType = ColType.metin;
                sb.append(logoSqlHelper.dbVal(caseForPrint, "FICHENO", colType).toString());
                sb.append("\n\t");
                String str6 = ((((((((((((((((((((((((sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "GDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "DESC", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(caseForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str6);
                ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                ColType colType2 = ColType.virgullu;
                sb2.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper2.dbVal(caseForPrint, "TOTAL", colType2).toString())));
                sb2.append("\n\t");
                String sb3 = sb2.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(sb3);
                try {
                    sb4.append(StringUtils.convertTotal2Text(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TOTAL", colType2).toString())), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                    sb4.append("\n\t");
                    str2 = (sb4 + addBeforeBalanceInfo(caseForPrint, SalesType.FREE, StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TOTAL", colType2).toString()), customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t";
                    obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "SALESMAN_CODE", colType).toString();
                    userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
                    userName = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid);
                } catch (Exception e2) {
                    e = e2;
                    i6 = 0;
                }
            } catch (Exception e3) {
                e = e3;
                i6 = 0;
                exc = e;
                strArr2[i6] = exc.getMessage();
                return strArr2;
            }
        } catch (Exception e4) {
            e = e4;
            i6 = 0;
        }
        try {
            if (obj != null) {
                try {
                    if (!obj.isEmpty()) {
                        if (obj.indexOf(" - ") != -1) {
                            i7 = 0;
                            try {
                                userCode = obj.substring(0, obj.indexOf(" - "));
                            } catch (Exception e5) {
                                e = e5;
                                exc = e;
                                i6 = i7;
                                strArr2 = strArr2;
                                strArr2[i6] = exc.getMessage();
                                return strArr2;
                            }
                        } else {
                            i7 = 0;
                            userCode = "";
                        }
                        userName = obj.indexOf(" - ") != -1 ? obj.substring(obj.indexOf(" - ") + 3) : "";
                        String str7 = userName;
                        String str8 = ((((str2 + userCode + "\n\t") + str7 + "\n\t") + "\n\t") + "\n\t") + userCode + "\n\t";
                        int i10 = i3 > 0 ? 1 : i3;
                        String[] strArr3 = new String[(caseForPrint.getCount() / i10) + 1];
                        String str9 = str8;
                        i8 = i7;
                        int i11 = i8;
                        double d3 = 0.0d;
                        double d4 = 0.0d;
                        boolean z4 = true;
                        int i12 = 1;
                        String str10 = "";
                        while (i8 < caseForPrint.getCount()) {
                            try {
                                if (caseForPrint.moveToPosition(i8)) {
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append(str10);
                                    ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                    String[] strArr4 = strArr3;
                                    try {
                                        ColType colType3 = ColType.virgullu;
                                        sb5.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper3.dbVal(caseForPrint, "TOTAL", colType3).toString())));
                                        sb5.append("\n\t");
                                        String sb6 = sb5.toString();
                                        StringBuilder sb7 = new StringBuilder();
                                        sb7.append(sb6);
                                        printProcess = printProcess2;
                                        boolean z5 = z4;
                                        sb7.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "DOCNO", ColType.metin).toString());
                                        sb7.append("\n\t");
                                        String str11 = sb7.toString() + '\r';
                                        double d5 = d3 + StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(caseForPrint, "TOTAL", colType3).toString());
                                        int i13 = i8 + 1;
                                        if (i13 != caseForPrint.getCount() && i13 != i10 * i12) {
                                            strArr = strArr4;
                                            str10 = str11;
                                            str3 = str7;
                                            i9 = i8;
                                            cursor = caseForPrint;
                                            d2 = d5;
                                            str4 = str9;
                                            z3 = z5;
                                        }
                                        if (d5 < 0.0d) {
                                            d5 = 0.0d;
                                        }
                                        d4 += d5;
                                        String str12 = ((((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d4) + "\n\t") + userCode + "\n\t") + str7 + "\n\t";
                                        String str13 = StringUtils.dFormat(d4) + "\n\t";
                                        StringBuilder sb8 = new StringBuilder();
                                        sb8.append(str13);
                                        str3 = str7;
                                        sb8.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d4), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                        sb8.append("\n\t");
                                        String str14 = (sb8 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                        str4 = (!z || z5) ? str9 : "";
                                        try {
                                            if (i13 == caseForPrint.getCount() && !z2) {
                                                i9 = i8;
                                                str5 = str12;
                                                strArr = strArr4;
                                                cursor = caseForPrint;
                                                i6 = 0;
                                                String Print = printProcess.Print(i10, str4, str11, str5, str14);
                                                if (i4 != 1) {
                                                    strArr[i11] = StringUtils.convTrCharEN(Print);
                                                } else {
                                                    strArr[i11] = Print;
                                                }
                                                i11++;
                                                i12++;
                                                z3 = false;
                                                str10 = "";
                                                d2 = 0.0d;
                                            }
                                            String Print2 = printProcess.Print(i10, str4, str11, str5, str14);
                                            if (i4 != 1) {
                                            }
                                            i11++;
                                            i12++;
                                            z3 = false;
                                            str10 = "";
                                            d2 = 0.0d;
                                        } catch (Exception e6) {
                                            e = e6;
                                            exc = e;
                                            strArr2 = strArr;
                                            strArr2[i6] = exc.getMessage();
                                            return strArr2;
                                        }
                                        str5 = "";
                                        i9 = i8;
                                        strArr = strArr4;
                                        cursor = caseForPrint;
                                        i6 = 0;
                                    } catch (Exception e7) {
                                        e = e7;
                                        strArr = strArr4;
                                        i6 = 0;
                                        exc = e;
                                        strArr2 = strArr;
                                        strArr2[i6] = exc.getMessage();
                                        return strArr2;
                                    }
                                } else {
                                    str3 = str7;
                                    i9 = i8;
                                    strArr = strArr3;
                                    cursor = caseForPrint;
                                    printProcess = printProcess2;
                                    z3 = z4;
                                    d2 = d3;
                                    str4 = str9;
                                }
                                z4 = z3;
                                i8 = i9 + 1;
                                str9 = str4;
                                str7 = str3;
                                strArr3 = strArr;
                                caseForPrint = cursor;
                                d3 = d2;
                                printProcess2 = printProcess;
                            } catch (Exception e8) {
                                e = e8;
                                strArr = strArr3;
                            }
                        }
                        return strArr3;
                    }
                } catch (Exception e9) {
                    e = e9;
                    i7 = 0;
                }
            }
            String str82 = ((((str2 + userCode + "\n\t") + str7 + "\n\t") + "\n\t") + "\n\t") + userCode + "\n\t";
            if (i3 > 0) {
            }
            String[] strArr32 = new String[(caseForPrint.getCount() / i10) + 1];
            String str92 = str82;
            i8 = i7;
            int i112 = i8;
            double d32 = 0.0d;
            double d42 = 0.0d;
            boolean z42 = true;
            int i122 = 1;
            String str102 = "";
            for (int i82 = i8; i82 < caseForPrint.getCount(); i82++) {
            }
            return strArr32;
        } catch (Exception e10) {
            e = e10;
            i6 = i7;
            exc = e;
            strArr2 = strArr2;
            strArr2[i6] = exc.getMessage();
            return strArr2;
        }
        String str72 = userName;
    }
    public static String[] PrintCheequeFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        String[] strArr;
        char c2 = 0;
        Exception exc;
        String[] strArr2;
        String str2;
        String str3;
        int i6;
        PrintProcess printProcess;
        boolean z3;
        String str4;
        String str5 = "CYPHCODE";
        String str6 = "KR";
        String[] strArr3 = new String[1];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        try {
            Cursor cheequeDeedHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCheequeDeedHedaerForPrint(i2);
            Cursor cheequeDeedDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCheequeDeedDetailForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            cheequeDeedHedaerForPrint.moveToPosition(0);
            try {
                StringBuilder sb = new StringBuilder();
                ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                strArr = strArr3;
                try {
                    PrintProcess printProcess3 = printProcess2;
                    ColType colType = ColType.metin;
                    sb.append(logoSqlHelper.dbVal(cheequeDeedHedaerForPrint, "FICHENO", colType).toString());
                    sb.append("\n\t");
                    String str7 = (((((((((((((((((((((((((((sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "GDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(cheequeDeedHedaerForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str7);
                    ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    ColType colType2 = ColType.virgullu;
                    sb2.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper2.dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString())));
                    sb2.append("\n\t");
                    String str8 = (((((((sb2 + StringUtils.convertTotal2Text(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString())), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + addBeforeBalanceInfo(cheequeDeedHedaerForPrint, SalesType.FREE, StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString()), customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t";
                    int i7 = i3 <= 0 ? 1 : i3;
                    String[] strArr4 = new String[(cheequeDeedDetailForPrint.getCount() / i7) + 1];
                    String str9 = "";
                    boolean z4 = true;
                    int i8 = 1;
                    double d2 = 0.0d;
                    double d3 = 0.0d;
                    int i9 = 0;
                    int i10 = 0;
                    while (true) {
                        try {
                            String str10 = str8;
                            if (i9 > cheequeDeedDetailForPrint.getCount() - 1) {
                                return strArr4;
                            }
                            if (cheequeDeedDetailForPrint.moveToPosition(i9)) {
                                String str11 = str9 + "\n\t";
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str11);
                                ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                strArr2 = strArr4;
                                try {
                                    String str12 = str6;
                                    ColType colType3 = ColType.metin;
                                    sb3.append(logoSqlHelper3.dbVal(cheequeDeedDetailForPrint, "DUEDATE", colType3).toString());
                                    sb3.append("\n\t");
                                    String str13 = ((((((((((sb3 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "SPECODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, str5, colType3).toString() + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "SERIALNO", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "DEBITED", colType3).toString() + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "TOTAL", colType3).toString())) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "BANKNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "BANKBRANCHNAME", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "ACCNO", colType3).toString() + "\n\t") + '\r';
                                    d2 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "TOTAL", colType3).toString());
                                    int i11 = i9 + 1;
                                    if (i11 != cheequeDeedDetailForPrint.getCount() && i11 != i7 * i8) {
                                        str9 = str13;
                                        str2 = str5;
                                        i6 = i9;
                                        printProcess = printProcess3;
                                        str3 = str12;
                                        z3 = true;
                                        str8 = str10;
                                    }
                                    if (d2 < 0.0d) {
                                        d2 = 0.0d;
                                    }
                                    d3 += d2;
                                    String str14 = ((((StringUtils.dFormat(d2) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d2), "TL", str12, ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str15 = StringUtils.dFormat(d3) + "\n\t";
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(str15);
                                    str2 = str5;
                                    sb4.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d3), "TL", str12, ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                    sb4.append("\n\t");
                                    String str16 = (sb4 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str17 = (!z || z4) ? str10 : "";
                                    try {
                                        if (i11 == cheequeDeedDetailForPrint.getCount() && !z2) {
                                            str4 = str14;
                                            str3 = str12;
                                            c2 = 0;
                                            i6 = i9;
                                            printProcess = printProcess3;
                                            String Print = printProcess3.Print(i7, str17, str13, str4, str16);
                                            z3 = true;
                                            if (i4 != 1) {
                                                strArr2[i10] = StringUtils.convTrCharEN(Print);
                                            } else {
                                                strArr2[i10] = Print;
                                            }
                                            i10++;
                                            i8++;
                                            str9 = "";
                                            z4 = false;
                                            d2 = 0.0d;
                                            str8 = str17;
                                        }
                                        String Print2 = printProcess3.Print(i7, str17, str13, str4, str16);
                                        z3 = true;
                                        if (i4 != 1) {
                                        }
                                        i10++;
                                        i8++;
                                        str9 = "";
                                        z4 = false;
                                        d2 = 0.0d;
                                        str8 = str17;
                                    } catch (Exception e2) {
                                        e = e2;
                                        exc = e;
                                        strArr = strArr2;
                                        strArr[c2] = exc.getMessage();
                                        return strArr;
                                    }
                                    str4 = "";
                                    str3 = str12;
                                    c2 = 0;
                                    i6 = i9;
                                    printProcess = printProcess3;
                                } catch (Exception e3) {
                                    e = e3;
                                    c2 = 0;
                                    exc = e;
                                    strArr = strArr2;
                                    strArr[c2] = exc.getMessage();
                                    return strArr;
                                }
                            } else {
                                str2 = str5;
                                str3 = str6;
                                strArr2 = strArr4;
                                i6 = i9;
                                printProcess = printProcess3;
                                z3 = true;
                                str8 = str10;
                            }
                            i9 = i6 + 1;
                            strArr4 = strArr2;
                            str5 = str2;
                            str6 = str3;
                            printProcess3 = printProcess;
                        } catch (Exception e4) {
                            e = e4;
                            strArr2 = strArr4;
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    c2 = 0;
                    exc = e;
                    strArr[c2] = exc.getMessage();
                    return strArr;
                }
            } catch (Exception e6) {
                e = e6;
                strArr = strArr3;
            }
        } catch (Exception e7) {
            e = e7;
            strArr = strArr3;
            c2 = 0;
        }
        return strArr;
    }
    public static String[] PrintDeedFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        String[] strArr;
        char c2 = 0;
        Exception exc;
        String[] strArr2;
        String str2;
        String str3;
        int i6;
        PrintProcess printProcess;
        boolean z3;
        String str4 = "";
        String str5 = "CYPHCODE";
        String str6 = "KR";
        String[] strArr3 = new String[1];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        try {
            Cursor cheequeDeedHedaerForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCheequeDeedHedaerForPrint(i2);
            Cursor cheequeDeedDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCheequeDeedDetailForPrint(i2);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            cheequeDeedHedaerForPrint.moveToPosition(0);
            try {
                StringBuilder sb = new StringBuilder();
                ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                strArr = strArr3;
                try {
                    PrintProcess printProcess3 = printProcess2;
                    ColType colType = ColType.metin;
                    sb.append(logoSqlHelper.dbVal(cheequeDeedHedaerForPrint, "FICHENO", colType).toString());
                    sb.append("\n\t");
                    String str7 = (((((((((((((((((((((((((((sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "GDATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DESC4", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "DISCRATE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CTRADINGGRP", colType).toString() + "\n\t") + addAccountBalanceInfo(cheequeDeedHedaerForPrint, customerUnsentAccountBalance)) + DateAndTimeUtils.nowTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str7);
                    ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    ColType colType2 = ColType.virgullu;
                    sb2.append(StringUtils.dFormat(StringUtils.toDouble(logoSqlHelper2.dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString())));
                    sb2.append("\n\t");
                    String str8 = ((sb2 + StringUtils.convertTotal2Text(StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString())), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + addBeforeBalanceInfo(cheequeDeedHedaerForPrint, SalesType.FREE, StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedHedaerForPrint, "TOTAL", colType2).toString()), customerUnsentAccountBalance)) + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t";
                    String userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
                    String str9 = ((((str8 + userCode + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + userCode + "\n\t";
                    int i7 = i3 <= 0 ? 1 : i3;
                    String[] strArr4 = new String[(cheequeDeedDetailForPrint.getCount() / i7) + 1];
                    String str10 = "";
                    boolean z4 = true;
                    int i8 = 1;
                    double d2 = 0.0d;
                    double d3 = 0.0d;
                    int i9 = 0;
                    int i10 = 0;
                    while (true) {
                        try {
                            String str11 = str9;
                            if (i9 > cheequeDeedDetailForPrint.getCount() - 1) {
                                return strArr4;
                            }
                            if (cheequeDeedDetailForPrint.moveToPosition(i9)) {
                                String str12 = str10 + "\n\t";
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str12);
                                ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                                strArr2 = strArr4;
                                try {
                                    String str13 = str6;
                                    ColType colType3 = ColType.metin;
                                    sb3.append(logoSqlHelper3.dbVal(cheequeDeedDetailForPrint, "DUEDATE", colType3).toString());
                                    sb3.append("\n\t");
                                    String str14 = ((((((((((sb3 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "SPECODE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, str5, colType3).toString() + "\n\t") + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "SERIALNO", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "DEBITED", colType3).toString() + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "TOTAL", colType3).toString())) + "\n\t") + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "PAYWHERE", colType3).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "PUL", colType3).toString() + "\n\t") + '\r';
                                    d2 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cheequeDeedDetailForPrint, "TOTAL", ColType.virgullu).toString());
                                    int i11 = i9 + 1;
                                    if (i11 != cheequeDeedDetailForPrint.getCount() && i11 != i7 * i8) {
                                        str10 = str14;
                                        str2 = str5;
                                        i6 = i9;
                                        printProcess = printProcess3;
                                        str3 = str13;
                                        z3 = true;
                                        str9 = str11;
                                    }
                                    if (d2 < 0.0d) {
                                        d2 = 0.0d;
                                    }
                                    d3 += d2;
                                    String str15 = ((((StringUtils.dFormat(d2) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d2), "TL", str13, ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str16 = StringUtils.dFormat(d3) + "\n\t";
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(str16);
                                    str2 = str5;
                                    sb4.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d3), "TL", str13, ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                    sb4.append("\n\t");
                                    String str17 = (sb4 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t";
                                    String str18 = (!z || z4) ? str11 : "";
                                    try {
                                        if (i11 == cheequeDeedDetailForPrint.getCount() && !z2) {
                                            str4 = str15;
                                            str3 = str13;
                                            c2 = 0;
                                            i6 = i9;
                                            printProcess = printProcess3;
                                            String Print = printProcess3.Print(i7, str18, str14, str4, str17);
                                            z3 = true;
                                            if (i4 != 1) {
                                                strArr2[i10] = StringUtils.convTrCharEN(Print);
                                            } else {
                                                strArr2[i10] = Print;
                                            }
                                            i10++;
                                            i8++;
                                            str10 = "";
                                            z4 = false;
                                            d2 = 0.0d;
                                            str9 = str18;
                                        }
                                        String Print2 = printProcess3.Print(i7, str18, str14, str4, str17);
                                        z3 = true;
                                        if (i4 != 1) {
                                        }
                                        i10++;
                                        i8++;
                                        str10 = "";
                                        z4 = false;
                                        d2 = 0.0d;
                                        str9 = str18;
                                    } catch (Exception e2) {
                                        e = e2;
                                        exc = e;
                                        strArr = strArr2;
                                        strArr[c2] = exc.getMessage();
                                        return strArr;
                                    }
                                    str4 = "";
                                    str3 = str13;
                                    c2 = 0;
                                    i6 = i9;
                                    printProcess = printProcess3;
                                } catch (Exception e3) {
                                    e = e3;
                                    c2 = 0;
                                    exc = e;
                                    strArr = strArr2;
                                    strArr[c2] = exc.getMessage();
                                    return strArr;
                                }
                            } else {
                                str2 = str5;
                                str3 = str6;
                                strArr2 = strArr4;
                                i6 = i9;
                                printProcess = printProcess3;
                                z3 = true;
                                str9 = str11;
                            }
                            i9 = i6 + 1;
                            strArr4 = strArr2;
                            str5 = str2;
                            str6 = str3;
                            printProcess3 = printProcess;
                        } catch (Exception e4) {
                            e = e4;
                            strArr2 = strArr4;
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                    c2 = 0;
                    exc = e;
                    strArr[c2] = exc.getMessage();
                    return strArr;
                }
            } catch (Exception e6) {
                e = e6;
                strArr = strArr3;
            }
        } catch (Exception e7) {
            e = e7;
            strArr = strArr3;
            c2 = 0;
        }
    }
    public static String[] PrintDeliveryNoteFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        int i6;
        String str2;
        String str3;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            Cursor deliveryNoteDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getDeliveryNoteDetailForPrint(i2);
            String str4 = (DateAndTimeUtils.getNowDateTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getUser().getCode() + "\n\t";
            int i7 = i3 <= 0 ? 1 : i3;
            strArr = new String[(deliveryNoteDetailForPrint.getCount() / i7) + 1];
            String str5 = "";
            String str6 = str5;
            int i8 = 1;
            boolean z3 = true;
            double d2 = 0.0d;
            int i9 = 0;
            double d3 = 0.0d;
            int i10 = 0;
            while (i9 <= deliveryNoteDetailForPrint.getCount() - 1) {
                if (deliveryNoteDetailForPrint.moveToPosition(i9)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str5);
                    ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                    ColType colType = ColType.metin;
                    sb.append(logoSqlHelper.dbVal(deliveryNoteDetailForPrint, "CODE", colType).toString());
                    sb.append("\n\t");
                    String str7 = sb + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(deliveryNoteDetailForPrint, "NAME", colType).toString() + "\n\t";
                    double convertStringToDouble = StringUtils.convertStringToDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(deliveryNoteDetailForPrint, "ACTUALSTOCK", colType).toString());
                    String str8 = str7 + convertStringToDouble + '\r';
                    d2 += convertStringToDouble;
                    int i11 = i9 + 1;
                    if (i11 != deliveryNoteDetailForPrint.getCount() && i11 != i7 * i8) {
                        str5 = str8;
                    }
                    d3 += d2;
                    String str9 = StringUtils.dFormat(d2) + "\n\t";
                    String str10 = (str6 + "\n\t") + StringUtils.dFormat(d3) + "\n\t";
                    String str11 = (!z || z3) ? str4 : "";
                    if (i11 == deliveryNoteDetailForPrint.getCount()) {
                        str3 = z2 ? "" : str9;
                        str2 = str10;
                    } else {
                        str2 = "";
                        str3 = str9;
                    }
                    i6 = i9;
                    String Print = printProcess.Print(i7, str11, str8, str3, str2);
                    if (i4 == 1) {
                        strArr[i10] = StringUtils.convTrCharEN(Print);
                    } else {
                        strArr[i10] = Print;
                    }
                    i10++;
                    i82++;
                    str5 = "";
                    str4 = str11;
                    str6 = str2;
                    d2 = 0.0d;
                    z3 = false;
                    i9 = i6 + 1;
                }
                i6 = i9;
                i9 = i6 + 1;
            }
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        return strArr;
    }
    public static String[] PrintWhTransferFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, int i5) {
        Exception exc;
        String[] strArr;
        Cursor cursor;
        int i6;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        int i7;
        String str11;
        String str12;
        Cursor cursor2;
        String str13;
        boolean z3;
        String str14;
        Cursor cursor3;
        String str15;
        String str16;
        StringBuilder sb;
        StringBuilder sb2;
        String str17;
        Cursor cursor4;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23 = "SPECODE";
        String str24 = "WAREHOUSENR";
        String str25 = "DESC2";
        String str26 = "DESC1";
        String str27 = "VATAMNT";
        String str28 = "VAT";
        String str29 = "PRICE";
        String[] strArr2 = new String[1];
        double[] dArr = new double[5];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        boolean printLotDetail = ErpCreator.getInstance().getmBaseErp().getPrintLotDetail();
        PrintProcess printProcess = new PrintProcess();
        String str30 = "UCODE";
        printProcess.SprintIniFileName = str;
        try {
            Cursor whTransferHeaderForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getWhTransferHeaderForPrint(i2);
            Cursor whTransferDetailForPrint = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getWhTransferDetailForPrint(i2, printLotDetail, i5);
            Cursor customerUnsentAccountBalance = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerUnsentAccountBalance(i5);
            whTransferHeaderForPrint.moveToPosition(0);
            double salesTrRateFromHeader = getSalesTrRateFromHeader(whTransferHeaderForPrint);
            StringBuilder sb3 = new StringBuilder();
            String str31 = "";
            ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
            Cursor cursor5 = whTransferDetailForPrint;
            ColType colType = ColType.metin;
            sb3.append(logoSqlHelper.dbVal(whTransferHeaderForPrint, "DESC1", colType).toString());
            sb3.append("\n\t");
            String str32 = ((sb3.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "DESC2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "DESC3", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "DESC4", colType).toString() + "\n\t";
            String str33 = "DESC4";
            String sb4 = str32 +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "DOCNO", colType).toString() +
                    "\n\t";
            String str34 = ((((sb4 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SHIPTYPE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SHIPAGENT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "WAREHOUSENR", colType).toString() + "\n\t";
            String userCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid);
            String str35 = "DESC3";
            String sb5 = str34 + userCode + "\n\t" +
                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) +
                    "\n\t";
            String str36 = ((((((((((((((((((((((((((((((sb5 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SRCBRANCHNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SRCDIVISNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SRCFACTNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SRCWAREHOUSENR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "SPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CNAME", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CSPECODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CCYPHCODE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "ADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "ADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TOWN", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TELNRS1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TELNRS2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TAXNR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TAXOFFICE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "INCHARGE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "EMAILADDR", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "RDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FADDR1", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FADDR2", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FCITY", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CDISTRICT", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "CTAXOFFCODE", colType).toString() + "\n\t";
            String str37 = "CCODE";
            String sb6 = str36 +
                    addBeforeBalanceInfo(whTransferHeaderForPrint, SalesType.WHTRANSFER, 0.0d, customerUnsentAccountBalance);
            String str38 = (((((sb6 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FLATITUTE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FLONGITUDE", colType).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TC", colType).toString() + "\n\t") + userCode + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "FICHEDATE", colType).toString() + "\n\t";
            double d2 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, "TOTALNET", ColType.virgullu).toString());
            int i8 = i3 <= 0 ? 1 : i3;
            String[] strArr3 = new String[(cursor5.getCount() / i8) + 1];
            String str39 = str38;
            String str40 = "CNAME";
            String str41 = str31;
            String str42 = str41;
            int i9 = 0;
            boolean z4 = true;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            int i10 = 0;
            int i11 = 1;
            double d6 = 0.0d;
            double d7 = 0.0d;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (i9 < cursor5.getCount()) {
                try {
                    Cursor cursor6 = cursor5;
                    if (cursor6.moveToPosition(i9)) {
                        double d11 = d2;
                        if (cursor6.getInt(cursor6.getColumnIndex("LOT")) != 1) {
                            String str43 = str31;
                            boolean z5 = cursor6.getInt(cursor6.getColumnIndex("VATINC")) == 1;
                            ISqlHelper logoSqlHelper2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            cursor3 = customerUnsentAccountBalance;
                            str15 = str25;
                            ColType colType2 = ColType.metin;
                            if (StringUtils.convertStringToInt(logoSqlHelper2.dbVal(cursor6, "VARIANTREF", colType2).toString()) > 0) {
                                str16 = (str41 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "VCODE", colType2).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "VNAME", colType2).toString() + "\n\t";
                                i6 = i8;
                                str2 = str26;
                                str13 = str42;
                            } else {
                                String obj = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "PCODE", colType2).toString();
                                str2 = str26;
                                String obj2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "PNAME", colType2).toString();
                                str13 = str42;
                                if (StringUtils.paramValueNumberCheck(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode))) {
                                    String obj3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "ICUSTSUPCODE", colType2).toString();
                                    i6 = i8;
                                    String obj4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "ICUSTSUPNAME", colType2).toString();
                                    StringBuilder sb7 = new StringBuilder();
                                    sb7.append(str41);
                                    if (obj3.isEmpty()) {
                                        sb = new StringBuilder();
                                        sb.append(obj);
                                        sb.append("\n\t");
                                    } else {
                                        sb = new StringBuilder();
                                        sb.append(obj3);
                                        sb.append("\n\t");
                                    }
                                    sb7.append(sb.toString());
                                    String sb8 = sb7.toString();
                                    StringBuilder sb9 = new StringBuilder();
                                    sb9.append(sb8);
                                    if (obj4.isEmpty()) {
                                        sb2 = new StringBuilder();
                                        sb2.append(obj2);
                                        sb2.append("\n\t");
                                    } else {
                                        sb2 = new StringBuilder();
                                        sb2.append(obj4);
                                        sb2.append("\n\t");
                                    }
                                    sb9.append(sb2.toString());
                                    str16 = sb9.toString();
                                } else {
                                    i6 = i8;
                                    str16 = (str41 + obj + "\n\t") + obj2 + "\n\t";
                                }
                            }
                            String str44 = str30;
                            String sb10 = str16 +
                                    ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str44, colType2).toString() +
                                    "\n\t";
                            String str45 = (((sb10 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str44, colType2).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(whTransferHeaderForPrint, str24, colType2).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str23, colType2).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "DELIVERYCODE", colType2).toString() + "\n\t";
                            ISqlHelper logoSqlHelper3 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType3 = ColType.virgullu;
                            double d12 = StringUtils.toDouble(logoSqlHelper3.dbVal(cursor6, "AMOUNT", colType3).toString());
                            d3 += d12;
                            String str46 = str45 + checkIsDivUnitForAmountPrintValueOffline(cursor6, d12, "\n\t");
                            str17 = str29;
                            str9 = str23;
                            str10 = str24;
                            double d13 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str17, colType3).toString());
                            str6 = str44;
                            String str47 = str28;
                            cursor4 = whTransferHeaderForPrint;
                            double d14 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str47, colType3).toString());
                            if (z5) {
                                d13 = CalculateUtils.calculateIncludeVatPrice(d13, d14);
                            }
                            double d15 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "TOTAL", colType3).toString());
                            if (z5) {
                                d15 = d13 * d12;
                            }
                            String str48 = ((str46 + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d15) + "\n\t") + str43 + "\n\t";
                            StringBuilder sb11 = new StringBuilder();
                            sb11.append(str48);
                            str18 = str43;
                            sb11.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str47, ColType.sayi).toString());
                            sb11.append("\n\t");
                            String sb12 = sb11.toString();
                            String str49 = str27;
                            String sb14 = sb12 +
                                    StringUtils.dFormat(StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str49, colType3).toString())) +
                                    "\n\t";
                            i7 = i9;
                            double d16 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "VATMATRAH", colType3).toString());
                            double d17 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str49, colType3).toString());
                            double d18 = StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str47, colType3).toString());
                            d4 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "LINENET", colType3).toString());
                            d5 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, str49, colType3).toString());
                            int i12 = 0;
                            while (true) {
                                if (i12 >= 5) {
                                    break;
                                }
                                double d19 = dArr[i12];
                                if (d19 == d18) {
                                    dArr2[i12] = dArr2[i12] + d17;
                                    dArr5[i12] = dArr5[i12] + d17;
                                    dArr3[i12] = dArr3[i12] + d16;
                                    break;
                                }
                                if (d19 == 0.0d) {
                                    dArr[i12] = d18;
                                    dArr2[i12] = d17;
                                    dArr4[i12] = d18;
                                    dArr5[i12] = d17;
                                    dArr3[i12] = d16;
                                    break;
                                }
                                i12++;
                            }
                            String str50 = ((sb14 + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "BARKOD", ColType.metin).toString() + "\n\t") + "\n\t") + "\n\t";
                            StringBuilder sb15 = new StringBuilder();
                            sb15.append(str50);
                            ISqlHelper logoSqlHelper4 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                            ColType colType4 = ColType.virgullu;
                            str3 = str49;
                            str19 = str47;
                            sb15.append(StringUtils.toDouble(logoSqlHelper4.dbVal(cursor6, str17, colType4).toString()));
                            sb15.append("\n\t");
                            String str51 = (((sb15.toString() + "\n\t") + "\n\t") + StringUtils.dFormat(d16) + "\n\t") + StringUtils.dFormat(d16 + d17) + "\n\t";
                            String str52 = "0.00";
                            String sb17 = str51 +
                                    (salesTrRateFromHeader == 0.0d ? "0.00" : StringUtils.dFormat(d15 / salesTrRateFromHeader)) +
                                    "\n\t";
                            StringBuilder sb18 = new StringBuilder();
                            sb18.append(sb17);
                            if (salesTrRateFromHeader != 0.0d) {
                                str52 = StringUtils.dFormat(d13 / salesTrRateFromHeader);
                            }
                            sb18.append(str52);
                            sb18.append("\n\t");
                            str20 = (sb18.toString() + "\n\t") + "\n\t\r";
                            d6 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "GROSSWEIGHT", colType4).toString()) * d12;
                            d7 += StringUtils.toDouble(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor6, "WEIGHT", colType4).toString()) * d12;
                        } else {
                            String str53 = str31;
                            String sb20 = str41 +
                                    str53 +
                                    "\n\t";
                            int itemTrackType = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getItemTrackType(cursor6.getInt(cursor6.getColumnIndex("ITEMREF")));
                            String sb22 = sb20 +
                                    getLotInfo(cursor6, itemTrackType == TrackType.LOT.getType()) +
                                    "\n\t";
                            for (int i13 = 0; i13 < 37; i13++) {
                                sb22 = sb22 + str53 + "\n\t";
                            }
                            str20 = sb22 + "\r";
                            cursor3 = customerUnsentAccountBalance;
                            str18 = str53;
                            i6 = i8;
                            str15 = str25;
                            str2 = str26;
                            str13 = str42;
                            str3 = str27;
                            str19 = str28;
                            str17 = str29;
                            str6 = str30;
                            cursor4 = whTransferHeaderForPrint;
                            str9 = str23;
                            str10 = str24;
                            i7 = i9;
                        }
                        double d20 = d4;
                        String str54 = str20;
                        int i14 = i7 + 1;
                        if (i14 != cursor6.getCount() && i14 != i6 * i11) {
                            cursor = cursor3;
                            str4 = str19;
                            cursor2 = cursor4;
                            str11 = str15;
                            str7 = str37;
                            str8 = str40;
                            d2 = d11;
                            str12 = str18;
                            str5 = str17;
                            boolean z6 = z4;
                            str14 = str39;
                            z3 = z6;
                            str41 = str54;
                            d4 = d20;
                        }
                        d8 += d20 + d5;
                        d9 += d3;
                        StringBuilder sb23 = new StringBuilder();
                        sb23.append(str13);
                        ISqlHelper logoSqlHelper5 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
                        ColType colType5 = ColType.metin;
                        cursor2 = cursor4;
                        String str55 = str2;
                        sb23.append(logoSqlHelper5.dbVal(cursor2, str55, colType5).toString());
                        sb23.append("\n\t");
                        String sb24 = sb23.toString();
                        StringBuilder sb25 = new StringBuilder();
                        sb25.append(sb24);
                        str11 = str15;
                        sb25.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str11, colType5).toString());
                        sb25.append("\n\t");
                        String sb26 = sb25.toString();
                        String str56 = str35;
                        String sb28 = sb26 +
                                ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str56, colType5).toString() +
                                "\n\t";
                        String str57 = str33;
                        String sb29 = sb28 +
                                ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str57, colType5).toString() +
                                "\n\t";
                        String str58 = ((sb29 + "\n\t") + StringUtils.dFormat(d8) + "\n\t") + StringUtils.dFormat(d3) + "\n\t";
                        StringBuilder sb30 = new StringBuilder();
                        sb30.append(str58);
                        str8 = str40;
                        sb30.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str8, colType5).toString());
                        sb30.append("\n\t");
                        String sb31 = sb30.toString();
                        StringBuilder sb32 = new StringBuilder();
                        sb32.append(sb31);
                        str7 = str37;
                        sb32.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str7, colType5).toString());
                        sb32.append("\n\t");
                        String sb33 = sb32.toString();
                        StringBuilder sb34 = new StringBuilder();
                        sb34.append(sb33);
                        Cursor cursor7 = cursor3;
                        str5 = str17;
                        sb34.append(addAccountBalanceInfoWhTransfer(cursor2, cursor7));
                        sb34.append("\n\t");
                        String sb35 = sb34.toString();
                        StringBuilder sb36 = new StringBuilder();
                        sb36.append(sb35);
                        str4 = str19;
                        sb36.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid));
                        sb36.append("\n\t");
                        String str59 = ((((sb36.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + cursor6.getCount() + "\n\t";
                        StringBuilder sb37 = new StringBuilder();
                        str12 = str18;
                        sb37.append(str12);
                        sb37.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str55, colType5).toString());
                        sb37.append("\n\t");
                        String str60 = ((((((((sb37.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str11, colType5).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str56, colType5).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str57, colType5).toString() + "\n\t") + "\n\t") + StringUtils.dFormat(d8) + "\n\t") + StringUtils.dFormat(d9) + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str8, colType5).toString() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, str7, colType5).toString() + "\n\t") + addAccountBalanceInfoWhTransfer(cursor2, cursor7) + "\n\t";
                        StringBuilder sb38 = new StringBuilder();
                        sb38.append(str60);
                        str2 = str55;
                        sb38.append(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserCode(USER.userid));
                        sb38.append("\n\t");
                        String str61 = (((sb38.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getUserName(USER.userid) + "\n\t") + cursor6.getCount() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor2, "DISCRATE", colType5).toString() + "\n\t") + StringUtils.dFormat(d8) + "\n\t";
                        StringBuilder sb39 = new StringBuilder();
                        sb39.append(str61);
                        str35 = str56;
                        str33 = str57;
                        d2 = d11;
                        sb39.append(addBeforeBalanceInfo(cursor2, SalesType.WHTRANSFER, d2, cursor7));
                        String str62 = (((((((sb39.toString() + ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCustomerLastPaymentInfo(i5) + "\n\t") + "\n\t") + StringUtils.dFormat(d6) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d7) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d2), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(0.0d) + "\n\t") + StringUtils.dFormat(0.0d) + "\n\t";
                        str14 = (!z || z4) ? str39 : str12;
                        if (i14 == cursor6.getCount()) {
                            str21 = str62;
                            if (z2) {
                                str22 = str12;
                                String Print = printProcess.Print(i6, str14, str54, str22, str21);
                                if (i4 != 1) {
                                    strArr3[i10] = StringUtils.convTrCharEN(Print);
                                } else {
                                    strArr3[i10] = Print;
                                }
                                i10++;
                                i11++;
                                str13 = str22;
                                str41 = str12;
                                cursor = cursor7;
                                d10 = d8;
                                z3 = false;
                                d3 = 0.0d;
                                d4 = 0.0d;
                                d5 = 0.0d;
                            }
                        } else {
                            str21 = str12;
                        }
                        str22 = str59;
                        String Print2 = printProcess.Print(i6, str14, str54, str22, str21);
                        if (i4 != 1) {
                        }
                        i10++;
                        i11++;
                        str13 = str22;
                        str41 = str12;
                        cursor = cursor7;
                        d10 = d8;
                        z3 = false;
                        d3 = 0.0d;
                        d4 = 0.0d;
                        d5 = 0.0d;
                    } else {
                        cursor = customerUnsentAccountBalance;
                        i6 = i8;
                        str2 = str26;
                        String str63 = str42;
                        str3 = str27;
                        str4 = str28;
                        str5 = str29;
                        str6 = str30;
                        str7 = str37;
                        str8 = str40;
                        str9 = str23;
                        str10 = str24;
                        i7 = i9;
                        str11 = str25;
                        str12 = str31;
                        cursor2 = whTransferHeaderForPrint;
                        str13 = str63;
                        z3 = z4;
                        str14 = str39;
                    }
                    whTransferHeaderForPrint = cursor2;
                    str31 = str12;
                    str40 = str8;
                    str37 = str7;
                    i9 = i7 + 1;
                    str23 = str9;
                    customerUnsentAccountBalance = cursor;
                    str39 = str14;
                    str26 = str2;
                    str42 = str13;
                    i8 = i6;
                    str27 = str3;
                    z4 = z3;
                    str25 = str11;
                    str29 = str5;
                    str24 = str10;
                    str30 = str6;
                    str28 = str4;
                    cursor5 = cursor6;
                } catch (Exception e2) {
                    exc = e2;
                    strArr = strArr3;
                    strArr[0] = exc.getMessage();
                    return strArr;
                }
            }
            return strArr3;
        } catch (Exception e3) {
            exc = e3;
            strArr = strArr2;
        }
        return strArr;
    }
    private static String addAccountBalanceInfoWhTransfer(Cursor cursor, Cursor cursor2) {
        double roundTwoDecimals;
        double roundTwoDecimals2;
        double d2;
        String str = "";
        String str2 = "";
        if (cursor2 != null) {
            try {
                if (cursor2.getCount() > 0 && cursor2.moveToFirst()) {
                    roundTwoDecimals = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLCREDIT")));
                    roundTwoDecimals2 = StringUtils.roundTwoDecimals(cursor2.getDouble(cursor2.getColumnIndex("ALLBALANCE")));
                    String str3 = StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("CREDIT_FLOAT")) + roundTwoDecimals) + "\n\t";
                    d2 = cursor.getDouble(cursor.getColumnIndex("BAKIYE_FLOAT")) + roundTwoDecimals2;
                    if (d2 < 0.0d) {
                        str = "(B)";
                    } else {
                        d2 *= -1.0d;
                        str = "(A)";
                    }
                    str2 = str3 + str + "\n\t";
                    return str2 + StringUtils.dFormat(d2);
                }
            } catch (Exception e2) {
                Log.d(TAG, "addAccountBalanceInfo: ", e2);
                return str2;
            }
        }
        roundTwoDecimals = 0.0d;
        roundTwoDecimals2 = 0.0d;
        String str32 = StringUtils.dFormat(cursor.getDouble(cursor.getColumnIndex("CREDIT_FLOAT")) + roundTwoDecimals) + "\n\t";
        d2 = cursor.getDouble(cursor.getColumnIndex("BAKIYE_FLOAT")) + roundTwoDecimals2;
        if (d2 < 0.0d) {
            return str2;
        }
        str2 = str32 + str + "\n\t";
        return str2 + StringUtils.dFormat(d2);
    }
    public static String[] PrintFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, RESULTXML resultxml, RESULTXML resultxml2, RESULTXML resultxml3) {
        if (i2 == FicheType.INVOICE.getmValue()) {
            return PrintInvoiceFiche(i3, z, z2, str, i4, resultxml, resultxml2, resultxml3);
        }
        if (i2 == FicheType.ORDER.getmValue()) {
            return PrintOrderFiche(i3, z, z2, str, i4, resultxml, resultxml2, resultxml3);
        }
        if (i2 == FicheType.DISPATCH.getmValue()) {
            return PrintDispatchFiche(i3, z, z2, str, i4, resultxml, resultxml2, resultxml3);
        }
        if (i2 == FicheType.CASH.getmValue()) {
            return PrintCashFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        if (i2 == FicheType.CREDIT_CART.getmValue()) {
            return PrintCrediCardFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        if (i2 == FicheType.CHEQUE.getmValue()) {
            return PrintCheequeFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        if (i2 == FicheType.DEED.getmValue()) {
            return PrintDeedFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        if (i2 == FicheType.DELIVERY_NOTE.getmValue()) {
            return PrintDeliveryNoteFiche(i3, z, z2, str, i4, resultxml2);
        }
        if (i2 == FicheType.CASE_CASH.getmValue()) {
            return PrintCaseFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        if (i2 == FicheType.WHTRANSFER.getmValue()) {
            return PrintWhTransferFiche(i3, z, z2, str, i4, resultxml, resultxml2);
        }
        return null;
    }
    public static String[] PrintInvoiceFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2, RESULTXML resultxml3) {
        char c2;
        Exception exc;
        String str2;
        String str3;
        String str4;
        String[] strArr;
        String str5;
        String str6;
        int i4;
        RESULTLINE resultline;
        String str7;
        String str8;
        String str9;
        int i5;
        int i6;
        int i7;
        int i8;
        RESULTLINE resultline2;
        String str10;
        int i9;
        double[] dArr;
        int i10;
        int i11;
        double d2;
        String str11;
        String str12;
        String sb;
        int i12;
        double d3;
        String str13;
        String str14;
        String sb2;
        int i13;
        String str15;
        int i14;
        String str16;
        String str17;
        String str18;
        int i15;
        String str19;
        String str20;
        double d4;
        double d5;
        double d6;
        double d7;
        String str21 = "";
        String[] strArr2 = new String[1];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        double[] dArr6 = new double[5];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            RESULTLINE resultline3 = resultxml.getResultLine().get(0);
            int convertStringToInt = StringUtils.convertStringToInt(getXmlAttribute(resultline3.TRCURR));
            double d8 = StringUtils.toDouble(getXmlAttribute(resultline3.TRRATE));
            String str22 = getXmlAttribute(resultline3.FICHENO) + "\n\t";
            if (resultline3.DATE_ != null) {
                try {
                    str2 = str22 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline3.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
                } catch (Exception e2) {
                    exc = e2;
                    c2 = 0;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } else {
                str2 = str22 + "\n\t";
            }
            String str23 = ((((((((((((((((((((((((((((((((((((((str2 + getXmlAttribute(DateAndTimeUtils.intToNowTime(Integer.parseInt(resultline3.TIME_))) + "\n\t") + getXmlAttribute(resultline3.DOCODE) + "\n\t") + getXmlAttribute(resultline3.SPECODE) + "\n\t") + getXmlAttribute(resultline3.CYPHCODE) + "\n\t") + getXmlAttribute(resultline3.RCODE) + "\n\t") + getXmlAttribute(resultline3.RNAME) + "\n\t") + getXmlAttribute(resultline3.GENEXP1) + "\n\t") + getXmlAttribute(resultline3.GENEXP2) + "\n\t") + getXmlAttribute(resultline3.GENEXP3) + "\n\t") + getXmlAttribute(resultline3.GENEXP4) + "\n\t") + getXmlAttribute(resultline3.SHPTYPCOD) + "\n\t") + getXmlAttribute(resultline3.SHPAGNCOD) + "\n\t") + getXmlAttribute(resultline3.TRADINGGRP) + "\n\t") + getXmlAttribute(resultline3.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t") + getXmlAttribute(resultline3.SDEFINITION) + "\n\t") + getXmlAttribute(resultline3.PCODE) + "\n\t") + getXmlAttribute(resultline3.PDEFINITION) + "\n\t") + getXmlAttribute(resultline3.FCODE) + "\n\t") + getXmlAttribute(resultline3.FNAME) + "\n\t") + getXmlAttribute(resultline3.CCODE) + "\n\t") + getXmlAttribute(resultline3.CDEFINITION) + "\n\t") + getXmlAttribute(resultline3.CSPECODE) + "\n\t") + getXmlAttribute(resultline3.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline3.ADDR1) + "\n\t") + getXmlAttribute(resultline3.ADDR2) + "\n\t") + getXmlAttribute(resultline3.CITY) + "\n\t") + getXmlAttribute(resultline3.TOWN) + "\n\t") + getXmlAttribute(resultline3.TELNRS1) + "\n\t") + getXmlAttribute(resultline3.TELNRS2) + "\n\t") + getXmlAttribute(resultline3.FAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline3.INCHARGE) + "\n\t") + getXmlAttribute(resultline3.DISCRATE) + "\n\t") + getXmlAttribute(resultline3.EMAILADDR) + "\n\t") + getXmlAttribute(resultline3.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CREDIT))) + "\n\t";
            double d9 = StringUtils.toDouble(getXmlAttribute(resultline3.BAKIYE));
            String str24 = "(A)";
            String str25 = "(B)";
            if (d9 < 0.0d) {
                try {
                    str3 = (str23 + StringUtils.dFormat(d9 * (-1.0d)) + "\n\t") + "(B)\n\t";
                } catch (Exception e3) {
                    exc = e3;
                    strArr2 = strArr2;
                    c2 = 0;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } else {
                try {
                    str3 = (str23 + StringUtils.dFormat(d9) + "\n\t") + "(A)\n\t";
                } catch (Exception e4) {
                    e = e4;
                    c2 = 0;
                    exc = e;
                    strArr2 = strArr2;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            }
            String str26 = ((((((((((((str3 + getXmlAttribute(resultline3.IRSDATE) + "\n\t") + getXmlAttribute(resultline3.IRSNO) + "\n\t") + getXmlAttribute(resultline3.RADDR1) + "\n\t") + getXmlAttribute(resultline3.RADDR2) + "\n\t") + getXmlAttribute(resultline3.RCITY) + "\n\t") + getXmlAttribute(resultline3.RTOWN) + "\n\t") + getXmlAttribute(resultline3.SADDR1) + "\n\t") + getXmlAttribute(resultline3.SADDR2) + "\n\t") + getXmlAttribute(resultline3.SCITY) + "\n\t") + getXmlAttribute(resultline3.STOWN) + "\n\t") + getXmlAttribute(resultline3.FICHENO) + "\n\t") + getXmlAttribute(resultline3.DISTRICT) + "\n\t") + getXmlAttribute(resultline3.TAXOFFCODE) + "\n\t";
            int i16 = resultline3.TRCODE;
            double d10 = StringUtils.toDouble(getXmlAttribute(resultline3.NETTOTAL));
            double d11 = StringUtils.toDouble(getXmlAttribute(resultline3.BAKIYE));
            if (i16 == 8) {
                Math.abs(d11 - d10);
            } else {
                Math.abs(d11 + d10);
            }
            double d12 = StringUtils.toDouble(getXmlAttribute(resultline3.BEFOREBALANCE));
            int i17 = convertStringToInt;
            if (d12 < 0.0d) {
                str4 = str26 + StringUtils.dFormat(d12 * (-1.0d)) + " (A)\n\t";
            } else {
                str4 = str26 + StringUtils.dFormat(d12) + " (B)\n\t";
            }
            String str27 = ((((str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline3.LATITUTE) + "\n\t") + getXmlAttribute(resultline3.LONGITUDE) + "\n\t") + getXmlAttribute(resultline3.TCKNO) + "\n\t") + getXmlAttribute(resultline3.DEFINITION2) + "\n\t";
            try {
                String str28 = " (B)";
                String str29 = " (A)";
                int i18 = 0;
                try {
                    String receiptTotalFromInvoice = ErpCreator.getInstance().getmBaseErp().getReceiptTotalFromInvoice(StringUtils.convertStringToInt(getXmlAttribute(resultline3.INVOICEREF)), false, DateAndTimeUtils.getDateInt(DateAndTimeUtils.convertReportDateToSimpleDate(getXmlAttribute(resultline3.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0]))));
                    String str30 = (((str27 + receiptTotalFromInvoice + "\n\t") + getPaymentDate(resultline3) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t") + getXmlAttribute(resultline3.GUID) + "\n\t";
                    double d13 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALVAT));
                    double d14 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALDISCOUNTS));
                    double d15 = StringUtils.toDouble(getXmlAttribute(resultline3.GROSSTOTAL)) - d14;
                    int i19 = i2 <= 0 ? 1 : i2;
                    if (ErpCreator.getInstance().getmBaseErp().getPrintLotDetail()) {
                        try {
                            detailResultXmlLotControl(resultxml2, resultline3.INVOICEREF);
                        } catch (Exception e5) {
                            exc = e5;
                            c2 = 0;
                            strArr2 = strArr2;
                            Log.d(TAG, "PrintInvoiceFiche: ", exc);
                            strArr2[c2] = exc.getMessage();
                            return strArr2;
                        }
                    }
                    String[] strArr3 = new String[(resultxml2.getResultLine().size() / i19) + 1];
                    String str31 = str30;
                    int i20 = 0;
                    double d16 = 0.0d;
                    double d17 = 0.0d;
                    double d18 = 0.0d;
                    double d19 = 0.0d;
                    double d20 = 0.0d;
                    double d21 = 0.0d;
                    double d22 = 0.0d;
                    double d23 = 0.0d;
                    boolean z3 = true;
                    int i21 = 1;
                    String str32 = "";
                    while (true) {
                        try {
                            String[] strArr4 = strArr3;
                            try {
                                if (i18 >= resultxml2.getResultLine().size()) {
                                    return strArr4;
                                }
                                RESULTLINE resultline4 = resultxml2.getResultLine().get(i18);
                                String str33 = receiptTotalFromInvoice;
                                try {
                                    if (resultline4.lot) {
                                        str6 = str25;
                                        i4 = i18;
                                        resultline = resultline3;
                                        str5 = str24;
                                        String str34 = (str32 + str21 + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t";
                                        for (int i22 = 0; i22 < 37; i22++) {
                                            str34 = str34 + str21 + "\n\t";
                                        }
                                        str9 = str34 + "\r";
                                    } else {
                                        try {
                                            int convertStringToInt2 = StringUtils.convertStringToInt(getXmlAttribute(resultline4.LINETYPE));
                                            str5 = str24;
                                            if (convertStringToInt2 != 2) {
                                                StringUtils.convertStringToInt(getXmlAttribute(resultline4.LOGICALREF));
                                                int convertStringToInt3 = StringUtils.convertStringToInt(getXmlAttribute(resultline4.STFICHELNNO));
                                                str6 = str25;
                                                resultline = resultline3;
                                                if (StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode)) == 1) {
                                                    str8 = resultline4.ICUSTSUPCODE == null ? (str32 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str32 + getXmlAttribute(resultline4.ICUSTSUPCODE) + "\n\t") + getXmlAttribute(resultline4.ICUSTSUPNAME) + "\n\t";
                                                } else if (StringUtils.convertStringToInt(getXmlAttribute(resultline4.VARIANTREF)) > 0) {
                                                    str8 = resultline4.VCODE == null ? (str32 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str32 + getXmlAttribute(resultline4.VCODE) + "\n\t") + getXmlAttribute(resultline4.VNAME) + "\n\t";
                                                } else {
                                                    str8 = (str32 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t";
                                                }
                                                String str35 = (((((((str8 + getXmlAttribute(resultline4.FICHENO) + "\n\t") + getXmlAttribute(resultline4.PCODE) + "\n\t") + getXmlAttribute(resultline4.PDEFINITION) + "\n\t") + getXmlAttribute(resultline4.UCODE) + "\n\t") + getXmlAttribute(resultline4.UNAME) + "\n\t") + getXmlAttribute(resultline4.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline4.SPECODE) + "\n\t") + getXmlAttribute(resultline4.DELVRYCODE) + "\n\t";
                                                i4 = i18;
                                                double d24 = StringUtils.toDouble(getXmlAttribute(resultline4.AMOUNT));
                                                d16 += d24;
                                                String str36 = str35 + checkIsDivUnitForAmountPrintValueOnline(resultline4, d24, "\n\t");
                                                double d25 = StringUtils.toDouble(getXmlAttribute(resultline4.PRICE));
                                                String str37 = str36 + StringUtils.dFormat(d25) + "\n\t";
                                                double d26 = StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                                String str38 = ((str37 + StringUtils.dFormat(d26) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.DISCPER))) + "\n\t") + StringUtils.toDouble(getXmlAttribute(resultline4.VAT)) + "\n\t";
                                                LineType lineType = LineType.PROMOTION;
                                                String sb4 = str38 +
                                                        StringUtils.dFormat(convertStringToInt2 != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d) +
                                                        "\n\t";
                                                double d27 = StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH));
                                                double d28 = convertStringToInt2 != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d;
                                                double d29 = StringUtils.toDouble(getXmlAttribute(resultline4.VAT));
                                                StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                                d17 += StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                                int i23 = 0;
                                                while (true) {
                                                    if (i23 >= 5) {
                                                        break;
                                                    }
                                                    double d30 = dArr2[i23];
                                                    if (d30 == d29) {
                                                        dArr3[i23] = dArr3[i23] + d28;
                                                        dArr6[i23] = dArr6[i23] + d28;
                                                        dArr4[i23] = dArr4[i23] + d27;
                                                        break;
                                                    }
                                                    if (d30 == 0.0d) {
                                                        dArr2[i23] = d29;
                                                        dArr3[i23] = d28;
                                                        dArr5[i23] = d29;
                                                        dArr6[i23] = d28;
                                                        dArr4[i23] = d27;
                                                        break;
                                                    }
                                                    i23++;
                                                }
                                                List<RESULTLINE> lineDiscount = getLineDiscount(resultxml3.getResultLine(), convertStringToInt3 + 1);
                                                for (int i24 = 0; i24 < 5; i24++) {
                                                    if (lineDiscount.size() > i24) {
                                                        sb4 = (sb4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i24).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i24).TOTAL))) + "\n\t";
                                                        d18 += StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i24).TOTAL));
                                                        StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i24).TOTAL));
                                                    } else {
                                                        sb4 = (sb4 + "0\n\t") + "0\n\t";
                                                    }
                                                }
                                                String str39 = ((((((((((((((sb4 + getXmlAttribute(resultline4.LINEEXP) + "\n\t") + getXmlAttribute(resultline4.GTIPCODE) + "\n\t") + getXmlAttribute(resultline4.Barkod) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.formatToTwoDecimals(StringUtils.toDouble(getXmlAttribute(resultline4.PRICE))) + "\n\t") + "\n\t") + getXmlAttribute(resultline4.DCODE) + "\n\t") + getXmlAttribute(resultline4.DNAME) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) + StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t";
                                                String sb6 = str39 +
                                                        (d8 == 0.0d ? "0.00" : StringUtils.dFormat(d26 / d8)) +
                                                        "\n\t";
                                                String sb7 = sb6 +
                                                        (d8 == 0.0d ? "0.00" : StringUtils.dFormat(d25 / d8)) +
                                                        "\n\t";
                                                String str40 = sb7 + getXmlAttribute(resultline4.NAME2) + "\n\t";
                                                for (int i25 = 0; i25 < 21; i25++) {
                                                    str40 = str40 + "\n\t";
                                                }
                                                str9 = ((str40 + getXmlAttribute(resultline4.WIDTH) + "\n\t") + getXmlAttribute(resultline4.LENGTH) + "\n\t") + getXmlAttribute(resultline4.HEIGHT) + "\n\t\r";
                                                d19 += StringUtils.toDouble(getXmlAttribute(resultline4.GROSSWEIGHT)) * d24;
                                                d20 += StringUtils.toDouble(getXmlAttribute(resultline4.WEIGHT)) * d24;
                                                d21 += StringUtils.toDouble(getXmlAttribute(resultline4.VOLUME)) * d24;
                                            } else {
                                                str6 = str25;
                                                i4 = i18;
                                                resultline = resultline3;
                                                str7 = str32;
                                                i5 = i4 + 1;
                                                if (i5 != resultxml2.getResultLine().size() || i5 == i19 * i21) {
                                                    if (d17 < 0.0d) {
                                                        d17 = 0.0d;
                                                    }
                                                    double d31 = d22 + d17;
                                                    double d32 = d23 + d16;
                                                    String str41 = str21;
                                                    i6 = 0;
                                                    while (true) {
                                                        if (i6 <= 4) {
                                                            break;
                                                        }
                                                        str41 = (str41 + StringUtils.dFormat(dArr2[i6]) + "\n\t") + StringUtils.dFormat(dArr3[i6]) + "\n\t";
                                                        i6++;
                                                    }
                                                    String str42 = (str41 + StringUtils.dFormat(d17) + "\n\t") + StringUtils.dFormat(d31) + "\n\t";
                                                    for (i8 = 0; i8 <= 4; i8++) {
                                                        str42 = str42 + StringUtils.dFormat(dArr4[i8]) + "\n\t";
                                                    }
                                                    StringBuilder sb8 = new StringBuilder();
                                                    sb8.append(str42);
                                                    resultline2 = resultline;
                                                    sb8.append(getXmlAttribute(resultline2.GENEXP1));
                                                    sb8.append("\n\t");
                                                    String str43 = (((((((sb8.toString() + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                                                    String sb9 = str43 +
                                                            (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d15 / d8)) +
                                                            "\n\t";
                                                    String str44 = (sb9 + StringUtils.dFormat(d16) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                                    str10 = str21;
                                                    i9 = 0;
                                                    for (i7 = 4; i9 <= i7; i7 = 4) {
                                                        str21 = (str21 + StringUtils.dFormat(dArr5[i9]) + "\n\t") + StringUtils.dFormat(dArr6[i9]) + "\n\t";
                                                        i9++;
                                                    }
                                                    String str45 = (((((str21 + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d14) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d18) + "\n\t") + StringUtils.dFormat(d31) + "\n\t";
                                                    List<RESULTLINE> generalDiscount = getGeneralDiscount(resultxml3.getResultLine());
                                                    dArr = dArr2;
                                                    i11 = 0;
                                                    for (i10 = 5; i11 < i10; i10 = 5) {
                                                        str45 = generalDiscount.size() > i11 ? (str45 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(generalDiscount.get(i11).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(generalDiscount.get(i11).TOTAL))) + "\n\t" : (str45 + "0\n\t") + "0\n\t";
                                                        i11++;
                                                    }
                                                    String str46 = (str45 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                                                    d2 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                                                    if (d2 >= 0.0d) {
                                                        String str47 = str46 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t";
                                                        StringBuilder sb10 = new StringBuilder();
                                                        sb10.append(str47);
                                                        str11 = str6;
                                                        sb10.append(str11);
                                                        sb10.append("\n\t");
                                                        sb = sb10.toString();
                                                        str12 = str5;
                                                    } else {
                                                        str11 = str6;
                                                        String str48 = str46 + StringUtils.dFormat(d2) + "\n\t";
                                                        StringBuilder sb11 = new StringBuilder();
                                                        sb11.append(str48);
                                                        str12 = str5;
                                                        sb11.append(str12);
                                                        sb11.append("\n\t");
                                                        sb = sb11.toString();
                                                    }
                                                    StringBuilder sb12 = new StringBuilder();
                                                    sb12.append(sb);
                                                    String str49 = str12;
                                                    str6 = str11;
                                                    sb12.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d10), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                                    sb12.append("\n\t");
                                                    String str50 = (sb12.toString() + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d15) + "\n\t";
                                                    for (i12 = 0; i12 <= 4; i12++) {
                                                        str50 = str50 + StringUtils.dFormat(dArr4[i12]) + "\n\t";
                                                    }
                                                    String str51 = (((((((str50 + getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + StringUtils.dFormat(d15) + "\n\t";
                                                    d3 = StringUtils.toDouble(getXmlAttribute(resultline2.BEFOREBALANCE));
                                                    if (d3 >= 0.0d) {
                                                        double d33 = d3 * (-1.0d);
                                                        StringBuilder sb13 = new StringBuilder();
                                                        sb13.append(str51);
                                                        sb13.append(StringUtils.dFormat(d33));
                                                        str13 = str29;
                                                        sb13.append(str13);
                                                        sb13.append("\n\t");
                                                        sb2 = sb13.toString();
                                                        str14 = str28;
                                                    } else {
                                                        str13 = str29;
                                                        StringBuilder sb14 = new StringBuilder();
                                                        sb14.append(str51);
                                                        sb14.append(StringUtils.dFormat(d3));
                                                        str14 = str28;
                                                        sb14.append(str14);
                                                        sb14.append("\n\t");
                                                        sb2 = sb14.toString();
                                                    }
                                                    String str52 = sb2 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CARISONBAKIYE))) + "\n\t";
                                                    StringBuilder sb15 = new StringBuilder();
                                                    sb15.append(str52);
                                                    receiptTotalFromInvoice = str33;
                                                    sb15.append(receiptTotalFromInvoice);
                                                    sb15.append("\n\t");
                                                    String str53 = sb15.toString() + getClCardCurrencyCode(StringUtils.convertStringToInt(getXmlAttribute(resultline2.CLIENTREF))) + "\n\t";
                                                    String sb17 = str53 +
                                                            (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d8)) +
                                                            "\n\t";
                                                    double d34 = d10 / d8;
                                                    String str54 = str14;
                                                    int i26 = i17;
                                                    String sb19 = sb17 +
                                                            StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d34), i26) +
                                                            "\n\t";
                                                    String sb21 = sb19 +
                                                            (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d34)) +
                                                            "\n\t";
                                                    String sb22 = sb21 +
                                                            (d8 != 0.0d ? StringUtils.dFormat(d13 / d8) : "0.00") +
                                                            "\n\t";
                                                    String str55 = ((((sb22 + "\n\t") + StringUtils.dFormat(d32) + "\n\t") + StringUtils.dFormat(d19) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d20) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                                    for (i13 = 0; i13 < 8; i13++) {
                                                        str55 = str55 + "\n\t";
                                                    }
                                                    String str56 = str55 + StringUtils.dFormat(d21) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                                                    str15 = (z || z3) ? str31 : str10;
                                                    if (i5 != resultxml2.getResultLine().size()) {
                                                        i14 = i26;
                                                        str17 = str56;
                                                        str16 = z2 ? str10 : str44;
                                                    } else {
                                                        i14 = i26;
                                                        str16 = str44;
                                                        str17 = str10;
                                                    }
                                                    strArr = strArr4;
                                                    str18 = str13;
                                                    i15 = i14;
                                                    c2 = 0;
                                                    str19 = str49;
                                                    str20 = str54;
                                                    String Print = printProcess.Print(i19, str15, str7, str16, str17);
                                                    if (i3 != 1) {
                                                        strArr[i20] = StringUtils.convTrCharEN(Print);
                                                    } else {
                                                        strArr[i20] = Print;
                                                    }
                                                    i20++;
                                                    i21++;
                                                    z3 = false;
                                                    d4 = 0.0d;
                                                    str7 = str10;
                                                    d5 = d32;
                                                    d6 = d31;
                                                    d7 = 0.0d;
                                                } else {
                                                    dArr = dArr2;
                                                    str20 = str28;
                                                    receiptTotalFromInvoice = str33;
                                                    resultline2 = resultline;
                                                    str18 = str29;
                                                    d4 = d16;
                                                    str10 = str21;
                                                    strArr = strArr4;
                                                    str19 = str5;
                                                    d5 = d23;
                                                    d6 = d22;
                                                    d7 = d17;
                                                    i15 = i17;
                                                    str15 = str31;
                                                }
                                                strArr3 = strArr;
                                                i18 = i5;
                                                resultline3 = resultline2;
                                                str32 = str7;
                                                dArr2 = dArr;
                                                str31 = str15;
                                                str21 = str10;
                                                i17 = i15;
                                                d17 = d7;
                                                d22 = d6;
                                                str24 = str19;
                                                d23 = d5;
                                                str25 = str6;
                                                d16 = d4;
                                                str29 = str18;
                                                str28 = str20;
                                            }
                                        } catch (Exception e6) {
                                            exc = e6;
                                            strArr2 = strArr4;
                                            c2 = 0;
                                            Log.d(TAG, "PrintInvoiceFiche: ", exc);
                                            strArr2[c2] = exc.getMessage();
                                            return strArr2;
                                        }
                                    }
                                    String Print2 = printProcess.Print(i19, str15, str7, str16, str17);
                                    if (i3 != 1) {
                                    }
                                    i20++;
                                    i21++;
                                    z3 = false;
                                    d4 = 0.0d;
                                    str7 = str10;
                                    d5 = d32;
                                    d6 = d31;
                                    d7 = 0.0d;
                                    strArr3 = strArr;
                                    i18 = i5;
                                    resultline3 = resultline2;
                                    str32 = str7;
                                    dArr2 = dArr;
                                    str31 = str15;
                                    str21 = str10;
                                    i17 = i15;
                                    d17 = d7;
                                    d22 = d6;
                                    str24 = str19;
                                    d23 = d5;
                                    str25 = str6;
                                    d16 = d4;
                                    str29 = str18;
                                    str28 = str20;
                                } catch (Exception e7) {
                                    e = e7;
                                    strArr2 = strArr;
                                    exc = e;
                                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                                    strArr2[c2] = exc.getMessage();
                                    return strArr2;
                                }
                                str7 = str9;
                                i5 = i4 + 1;
                                if (i5 != resultxml2.getResultLine().size()) {
                                }
                                if (d17 < 0.0d) {
                                }
                                double d312 = d22 + d17;
                                double d322 = d23 + d16;
                                String str412 = str21;
                                i6 = 0;
                                while (true) {
                                    if (i6 <= 4) {
                                    }
                                    i6++;
                                }
                                String str422 = (str412 + StringUtils.dFormat(d17) + "\n\t") + StringUtils.dFormat(d312) + "\n\t";
                                while (i8 <= 4) {
                                }
                                StringBuilder sb82 = new StringBuilder();
                                sb82.append(str422);
                                resultline2 = resultline;
                                sb82.append(getXmlAttribute(resultline2.GENEXP1));
                                sb82.append("\n\t");
                                String str432 = (((((((sb82.toString() + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                                String sb92 = str432 +
                                        (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d15 / d8)) +
                                        "\n\t";
                                String str442 = (sb92 + StringUtils.dFormat(d16) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                str10 = str21;
                                i9 = 0;
                                while (i9 <= i7) {
                                }
                                String str452 = (((((str21 + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d14) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d18) + "\n\t") + StringUtils.dFormat(d312) + "\n\t";
                                List<RESULTLINE> generalDiscount2 = getGeneralDiscount(resultxml3.getResultLine());
                                dArr = dArr2;
                                i11 = 0;
                                while (i11 < i10) {
                                }
                                String str462 = (str452 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                                d2 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                                if (d2 >= 0.0d) {
                                }
                                StringBuilder sb122 = new StringBuilder();
                                sb122.append(sb);
                                String str492 = str12;
                                str6 = str11;
                                sb122.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d10), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                sb122.append("\n\t");
                                String str502 = (sb122.toString() + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d15) + "\n\t";
                                while (i12 <= 4) {
                                }
                                String str512 = (((((((str502 + getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + StringUtils.dFormat(d15) + "\n\t";
                                d3 = StringUtils.toDouble(getXmlAttribute(resultline2.BEFOREBALANCE));
                                if (d3 >= 0.0d) {
                                }
                                String str522 = sb2 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CARISONBAKIYE))) + "\n\t";
                                StringBuilder sb152 = new StringBuilder();
                                sb152.append(str522);
                                receiptTotalFromInvoice = str33;
                                sb152.append(receiptTotalFromInvoice);
                                sb152.append("\n\t");
                                String str532 = sb152.toString() + getClCardCurrencyCode(StringUtils.convertStringToInt(getXmlAttribute(resultline2.CLIENTREF))) + "\n\t";
                                String sb172 = str532 +
                                        (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d8)) +
                                        "\n\t";
                                double d342 = d10 / d8;
                                String str542 = str14;
                                int i262 = i17;
                                String sb192 = sb172 +
                                        StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d342), i262) +
                                        "\n\t";
                                String sb212 = sb192 +
                                        (d8 != 0.0d ? "0.00" : StringUtils.dFormat(d342)) +
                                        "\n\t";
                                String sb222 = sb212 +
                                        (d8 != 0.0d ? StringUtils.dFormat(d13 / d8) : "0.00") +
                                        "\n\t";
                                String str552 = ((((sb222 + "\n\t") + StringUtils.dFormat(d322) + "\n\t") + StringUtils.dFormat(d19) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d20) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                while (i13 < 8) {
                                }
                                String str562 = str552 + StringUtils.dFormat(d21) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                                if (z) {
                                }
                                if (i5 != resultxml2.getResultLine().size()) {
                                }
                                strArr = strArr4;
                                str18 = str13;
                                i15 = i14;
                                c2 = 0;
                                str19 = str492;
                                str20 = str542;
                            } catch (Exception e8) {
                                e = e8;
                                strArr = strArr4;
                                c2 = 0;
                                strArr2 = strArr;
                                exc = e;
                                Log.d(TAG, "PrintInvoiceFiche: ", exc);
                                strArr2[c2] = exc.getMessage();
                                return strArr2;
                            }
                        } catch (Exception e9) {
                            e = e9;
                            strArr = strArr3;
                        }
                    }
                } catch (Exception e10) {
                    e = e10;
                    c2 = 0;
                    exc = e;
                    strArr2 = strArr2;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } catch (Exception e11) {
                e = e11;
                c2 = 0;
            }
        } catch (Exception e12) {
            e = e12;
            c2 = 0;
        }
    }
    public static String[] PrintWhTransferFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        int i4;
        Exception exc;
        String str2;
        int i5;
        StringBuilder sb;
        String sb2;
        int i6;
        String str3;
        String str4;
        String str5;
        String str6;
        double d2;
        double d3;
        double[] dArr;
        String str7;
        String str8;
        String str9;
        String str10;
        String sb3;
        String str11;
        String str12;
        String str13;
        String str14;
        RESULTLINE resultline;
        double d4;
        double d5;
        int i7;
        double d6;
        double d7;
        String str15 = "";
        String[] strArr = new String[1];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        double[] dArr6 = new double[5];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            RESULTLINE resultline2 = resultxml.getResultLine().get(0);
            double d8 = StringUtils.toDouble(getXmlAttribute(resultline2.TRRATE));
            String str16 = (((((((((getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.DOCODE) + "\n\t") + getXmlAttribute(resultline2.RCODE) + "\n\t") + getXmlAttribute(resultline2.RNAME) + "\n\t") + getXmlAttribute(resultline2.SHPTYPCOD) + "\n\t") + getXmlAttribute(resultline2.SHPAGNCOD) + "\n\t") + getXmlAttribute(resultline2.SOURCEINDEX) + "\n\t";
            String xmlAttribute = getXmlAttribute(resultline2.SCODE);
            String str17 = ((((((((((((((((((((((((((((((((str16 + xmlAttribute + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline2.ISYERI) + "\n\t") + getXmlAttribute(resultline2.BOLUM) + "\n\t") + getXmlAttribute(resultline2.FABRIKA) + "\n\t") + getXmlAttribute(resultline2.AMBAR) + "\n\t") + getXmlAttribute(resultline2.SPECODE) + "\n\t") + getXmlAttribute(resultline2.CYPHCODE) + "\n\t") + getXmlAttribute(resultline2.CCODE) + "\n\t") + getXmlAttribute(resultline2.CDEFINITION) + "\n\t") + getXmlAttribute(resultline2.CSPECODE) + "\n\t") + getXmlAttribute(resultline2.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline2.ADDR1) + "\n\t") + getXmlAttribute(resultline2.ADDR2) + "\n\t") + getXmlAttribute(resultline2.CITY) + "\n\t") + getXmlAttribute(resultline2.TOWN) + "\n\t") + getXmlAttribute(resultline2.TELNRS1) + "\n\t") + getXmlAttribute(resultline2.TELNRS2) + "\n\t") + getXmlAttribute(resultline2.FAXNR) + "\n\t") + getXmlAttribute(resultline2.TAXNR) + "\n\t") + getXmlAttribute(resultline2.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline2.INCHARGE) + "\n\t") + getXmlAttribute(resultline2.EMAILADDR) + "\n\t") + getXmlAttribute(resultline2.RADDR1) + "\n\t") + getXmlAttribute(resultline2.RADDR2) + "\n\t") + getXmlAttribute(resultline2.RCITY) + "\n\t") + getXmlAttribute(resultline2.RTOWN) + "\n\t") + getXmlAttribute(resultline2.SADDR1) + "\n\t") + getXmlAttribute(resultline2.SADDR2) + "\n\t") + getXmlAttribute(resultline2.SCITY) + "\n\t") + getXmlAttribute(resultline2.STOWN) + "\n\t") + getXmlAttribute(resultline2.DISTRICT) + "\n\t") + getXmlAttribute(resultline2.TAXOFFCODE) + "\n\t";
            double d9 = StringUtils.toDouble(getXmlAttribute(resultline2.BEFOREBALANCE));
            String str18 = " (B)";
            String str19 = " (A)";
            if (d9 < 0.0d) {
                try {
                    str2 = str17 + StringUtils.dFormat(d9 * (-1.0d)) + " (A)\n\t";
                } catch (Exception e2) {
                    exc = e2;
                    i4 = 0;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
            } else {
                try {
                    str2 = str17 + StringUtils.dFormat(d9) + " (B)\n\t";
                } catch (Exception e3) {
                    e = e3;
                    i4 = 0;
                    exc = e;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
            }
            String str20 = ((((str2 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline2.LATITUTE) + "\n\t") + getXmlAttribute(resultline2.LONGITUDE) + "\n\t") + getXmlAttribute(resultline2.TCKNO) + "\n\t") + xmlAttribute + "\n\t";
            if (resultline2.DATE_ != null) {
                try {
                    sb = new StringBuilder();
                    sb.append(str20);
                    i5 = 0;
                } catch (Exception e4) {
                    e = e4;
                    i5 = 0;
                    exc = e;
                    i4 = i5;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
                try {
                    sb.append(DateAndTimeUtils.convertDateY(getXmlAttribute(resultline2.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])));
                    sb.append("\n\t");
                    sb2 = sb.toString();
                } catch (Exception e5) {
                    e = e5;
                    exc = e;
                    i4 = i5;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
            } else {
                i5 = 0;
                try {
                    sb2 = str20 + "\n\t";
                } catch (Exception e6) {
                    e = e6;
                    i4 = i5;
                    exc = e;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
            }
            StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
            double d10 = StringUtils.toDouble(getXmlAttribute(resultline2.NETTOTAL));
            double d11 = StringUtils.toDouble(getXmlAttribute(resultline2.TOTALVAT));
            double d12 = StringUtils.toDouble(getXmlAttribute(resultline2.TOTALDISCOUNTS));
            double d13 = StringUtils.toDouble(getXmlAttribute(resultline2.GROSSTOTAL)) - d12;
            int i8 = i2 <= 0 ? 1 : i2;
            if (ErpCreator.getInstance().getmBaseErp().getPrintLotDetail()) {
                detailResultXmlLotControl(resultxml2, resultline2.STFICHEREF);
            }
            strArr = new String[(resultxml2.getResultLine().size() / i8) + 1];
            String str21 = "";
            String str22 = sb2;
            int i9 = i5;
            int i10 = i9;
            double d14 = 0.0d;
            double d15 = 0.0d;
            double d16 = 0.0d;
            double d17 = 0.0d;
            double d18 = 0.0d;
            double d19 = 0.0d;
            int i11 = 1;
            int i12 = 1;
            while (true) {
                try {
                    String[] strArr2 = strArr;
                    try {
                        if (i9 >= resultxml2.getResultLine().size()) {
                            return strArr2;
                        }
                        RESULTLINE resultline3 = resultxml2.getResultLine().get(i9);
                        PrintProcess printProcess2 = printProcess;
                        if (resultline3.lot) {
                            str3 = str18;
                            i6 = i11;
                            str4 = str19;
                            String str23 = (str21 + str15 + "\n\t") + getXmlAttribute(resultline3.NAME) + "\n\t";
                            for (int i13 = i5; i13 < 37; i13++) {
                                str23 = str23 + str15 + "\n\t";
                            }
                            str5 = str23 + "\r";
                        } else {
                            try {
                                int convertStringToInt = StringUtils.convertStringToInt(getXmlAttribute(resultline3.LINETYPE));
                                i6 = i11;
                                if (convertStringToInt != 2) {
                                    str3 = str18;
                                    if (StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode)) == 1) {
                                        str6 = resultline3.ICUSTSUPCODE == null ? (str21 + getXmlAttribute(resultline3.CODE) + "\n\t") + getXmlAttribute(resultline3.NAME) + "\n\t" : (str21 + getXmlAttribute(resultline3.ICUSTSUPCODE) + "\n\t") + getXmlAttribute(resultline3.ICUSTSUPNAME) + "\n\t";
                                    } else if (StringUtils.convertStringToInt(getXmlAttribute(resultline3.VARIANTREF)) > 0) {
                                        str6 = resultline3.VCODE == null ? (str21 + getXmlAttribute(resultline3.CODE) + "\n\t") + getXmlAttribute(resultline3.NAME) + "\n\t" : (str21 + getXmlAttribute(resultline3.VCODE) + "\n\t") + getXmlAttribute(resultline3.VNAME) + "\n\t";
                                    } else {
                                        str6 = (str21 + getXmlAttribute(resultline3.CODE) + "\n\t") + getXmlAttribute(resultline3.NAME) + "\n\t";
                                    }
                                    String str24 = ((((str6 + getXmlAttribute(resultline3.UCODE) + "\n\t") + getXmlAttribute(resultline3.UNAME) + "\n\t") + getXmlAttribute(resultline3.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline3.SPECODE) + "\n\t") + getXmlAttribute(resultline3.DELVRYCODE) + "\n\t";
                                    double d20 = StringUtils.toDouble(getXmlAttribute(resultline3.AMOUNT));
                                    d14 += d20;
                                    str4 = str19;
                                    String str25 = str24 + checkIsDivUnitForAmountPrintValueOnline(resultline3, d20, "\n\t") + "\n\t";
                                    double d21 = StringUtils.toDouble(getXmlAttribute(resultline3.PRICE));
                                    String str26 = str25 + StringUtils.dFormat(d21) + "\n\t";
                                    double d22 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTAL));
                                    String str27 = ((str26 + StringUtils.dFormat(d22) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.DISCPER))) + "\n\t") + StringUtils.toDouble(getXmlAttribute(resultline3.VAT)) + "\n\t";
                                    LineType lineType = LineType.PROMOTION;
                                    String sb5 = str27 +
                                            StringUtils.dFormat(convertStringToInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline3.VATAMNT)) : 0.0d) +
                                            "\n\t";
                                    double d23 = StringUtils.toDouble(getXmlAttribute(resultline3.VATMATRAH));
                                    double d24 = convertStringToInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline3.VATAMNT)) : 0.0d;
                                    double d25 = StringUtils.toDouble(getXmlAttribute(resultline3.VAT));
                                    d15 += StringUtils.toDouble(getXmlAttribute(resultline3.TOTAL));
                                    int i14 = i5;
                                    while (true) {
                                        if (i14 >= 5) {
                                            break;
                                        }
                                        double d26 = dArr2[i14];
                                        if (d26 == d25) {
                                            dArr3[i14] = dArr3[i14] + d24;
                                            dArr6[i14] = dArr6[i14] + d24;
                                            dArr4[i14] = dArr4[i14] + d23;
                                            break;
                                        }
                                        if (d26 == 0.0d) {
                                            dArr2[i14] = d25;
                                            dArr3[i14] = d24;
                                            dArr5[i14] = d25;
                                            dArr6[i14] = d24;
                                            dArr4[i14] = d23;
                                            break;
                                        }
                                        i14++;
                                    }
                                    String str28 = (((((((sb5 + getXmlAttribute(resultline3.Barkod) + "\n\t") + "\n\t") + "\n\t") + StringUtils.formatToTwoDecimals(StringUtils.toDouble(getXmlAttribute(resultline3.PRICE))) + "\n\t") + getXmlAttribute(resultline3.DCODE) + "\n\t") + getXmlAttribute(resultline3.DNAME) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.VATMATRAH))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.VATAMNT)) + StringUtils.toDouble(getXmlAttribute(resultline3.VATMATRAH))) + "\n\t";
                                    String str29 = "0.00";
                                    String sb7 = str28 +
                                            (d8 == 0.0d ? "0.00" : StringUtils.dFormat(d22 / d8)) +
                                            "\n\t";
                                    StringBuilder sb8 = new StringBuilder();
                                    sb8.append(sb7);
                                    if (d8 != 0.0d) {
                                        str29 = StringUtils.dFormat(d21 / d8);
                                    }
                                    sb8.append(str29);
                                    sb8.append("\n\t");
                                    String str30 = (sb8 + "\n\t") + "\n\t\r";
                                    d16 += StringUtils.toDouble(getXmlAttribute(resultline3.GROSSWEIGHT)) * d20;
                                    d17 += StringUtils.toDouble(getXmlAttribute(resultline3.WEIGHT)) * d20;
                                    str5 = str30;
                                } else {
                                    str3 = str18;
                                    str4 = str19;
                                    str5 = str21;
                                }
                            } catch (Exception e7) {
                                e = e7;
                                strArr = strArr2;
                                exc = e;
                                i4 = i5;
                                Log.d(TAG, "PrintInvoiceFiche: ", exc);
                                strArr[i4] = exc.getMessage();
                                return strArr;
                            }
                        }
                        double d27 = d14;
                        i9++;
                        try {
                            if (i9 != resultxml2.getResultLine().size() && i9 != i8 * i12) {
                                str11 = str15;
                                dArr = dArr2;
                                resultline = resultline2;
                                d5 = d19;
                                str10 = str3;
                                i7 = i6;
                                i4 = i5;
                                d6 = d18;
                                d4 = d27;
                                d7 = d15;
                                str14 = str22;
                                i11 = i7;
                                str18 = str10;
                                resultline2 = resultline;
                                str21 = str5;
                                dArr2 = dArr;
                                str15 = str11;
                                str22 = str14;
                                d15 = d7;
                                d18 = d6;
                                str19 = str4;
                                d19 = d5;
                                strArr = strArr2;
                                d14 = d4;
                                printProcess = printProcess2;
                                i5 = i4;
                            }
                            String Print = printProcess2.Print(i8, str14, str5, str12, str13);
                            if (i3 == 1) {
                                strArr2[i10] = StringUtils.convTrCharEN(Print);
                            } else {
                                strArr2[i10] = Print;
                            }
                            i10++;
                            i12++;
                            d4 = 0.0d;
                            str5 = str11;
                            d5 = d2;
                            i7 = i4;
                            d6 = d3;
                            d7 = 0.0d;
                            i11 = i7;
                            str18 = str10;
                            resultline2 = resultline;
                            str21 = str5;
                            dArr2 = dArr;
                            str15 = str11;
                            str22 = str14;
                            d15 = d7;
                            d18 = d6;
                            str19 = str4;
                            d19 = d5;
                            strArr = strArr2;
                            d14 = d4;
                            printProcess = printProcess2;
                            i5 = i4;
                        } catch (Exception e8) {
                            e = e8;
                            strArr = strArr2;
                            exc = e;
                            Log.d(TAG, "PrintInvoiceFiche: ", exc);
                            strArr[i4] = exc.getMessage();
                            return strArr;
                        }
                        double d28 = d15 < 0.0d ? 0.0d : d15;
                        d2 = d19 + d28;
                        d3 = d18 + d27;
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append(str15);
                        dArr = dArr2;
                        sb9.append(getXmlAttribute(resultline2.GENEXP1));
                        sb9.append("\n\t");
                        String str31 = ((((((((sb9 + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + "\n\t") + d28 + "\n\t") + d27 + "\n\t") + getXmlAttribute(resultline2.CDEFINITION) + "\n\t") + getXmlAttribute(resultline2.CCODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                        double d29 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                        if (d29 < 0.0d) {
                            str7 = (str31 + "(B)\n\t") + StringUtils.dFormat(d29 * (-1.0d)) + "\n\t";
                        } else {
                            str7 = (str31 + "(A)\n\t") + StringUtils.dFormat(d29) + "\n\t";
                        }
                        String str32 = (((((str7 + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                        String str33 = (((((((((str15 + getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d3) + "\n\t") + getXmlAttribute(resultline2.CDEFINITION) + "\n\t") + getXmlAttribute(resultline2.CCODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                        double d30 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                        if (d30 < 0.0d) {
                            str8 = (str33 + "(A)\n\t") + StringUtils.dFormat(d30 * (-1.0d)) + "\n\t";
                        } else {
                            str8 = (str33 + "(B)\n\t") + StringUtils.dFormat(d30) + "\n\t";
                        }
                        String str34 = ((((str8 + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + StringUtils.dFormat(d13) + "\n\t";
                        double d31 = StringUtils.toDouble(getXmlAttribute(resultline2.BEFOREBALANCE));
                        if (d31 < 0.0d) {
                            double d32 = d31 * (-1.0d);
                            StringBuilder sb10 = new StringBuilder();
                            sb10.append(str34);
                            sb10.append(StringUtils.dFormat(d32));
                            str9 = str4;
                            sb10.append(str9);
                            sb10.append("\n\t");
                            sb3 = sb10.toString();
                            str10 = str3;
                        } else {
                            str9 = str4;
                            StringBuilder sb11 = new StringBuilder();
                            sb11.append(str34);
                            sb11.append(StringUtils.dFormat(d31));
                            str10 = str3;
                            sb11.append(str10);
                            sb11.append("\n\t");
                            sb3 = sb11.toString();
                        }
                        String str35 = (((sb3 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CARISONBAKIYE))) + "\n\t") + "\n\t") + StringUtils.dFormat(d16) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d17) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t";
                        StringBuilder sb12 = new StringBuilder();
                        sb12.append(str35);
                        str11 = str15;
                        str12 = str32;
                        sb12.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d10), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                        sb12.append("\n\t");
                        str13 = ((sb12 + StringUtils.dFormat(d2) + "\n\t") + StringUtils.dFormat(d11) + "\n\t") + StringUtils.dFormat(d12) + "\n\t";
                        str14 = (z && i6 == 0) ? str11 : str22;
                        if (i9 != resultxml2.getResultLine().size()) {
                            str13 = str11;
                        } else if (z2) {
                            str12 = str11;
                        }
                        resultline = resultline2;
                        str4 = str9;
                        i4 = i5;
                    } catch (Exception e9) {
                        e = e9;
                        i4 = i5;
                    }
                } catch (Exception e10) {
                    e = e10;
                    i4 = i5;
                    exc = e;
                    Log.d(TAG, "PrintInvoiceFiche: ", exc);
                    strArr[i4] = exc.getMessage();
                    return strArr;
                }
            }
        } catch (Exception e11) {
            e = e11;
            i4 = 0;
        }
    }
    private static String getPaymentDate(RESULTLINE resultline) {
        int convertStringToInt = StringUtils.convertStringToInt(resultline.PAYPLANREF);
        if (convertStringToInt == 0) {
            return "";
        }
        return DateAndTimeUtils.getDateDMY2(ErpCreator.getInstance().getmBaseErp().calculatePaymentDate(DateAndTimeUtils.convertStringToDateY(getXmlAttribute(resultline.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])), convertStringToInt));
    }
    private static String getPaymentDate(int i2, String str) {
        if (i2 == 0) {
            return "";
        }
        return DateAndTimeUtils.getDateDMY2(ErpCreator.getInstance().getmBaseErp().calculatePaymentDate(DateAndTimeUtils.convertStringToDateddMMYYYY(str), i2));
    }
    private static void detailResultXmlLotControl(RESULTXML resultxml, String str) {
        if (resultxml == null) {
            return;
        }
        int intValue;
        ArrayList arrayList = new ArrayList(resultxml.getResultLine().size());
        Iterator<RESULTLINE> it = resultxml.getResultLine().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            RESULTLINE resultline = (RESULTLINE) arrayList.get(i3);
            if (!resultline.addLot && (intValue = Integer.valueOf(getXmlAttribute(resultline.TRACKTYPE)).intValue()) != TrackType.NON_TRACK.getType()) {
                ArrayList<String> lot = getLot(resultline.ITEMREF, str, resultline.GUID, intValue == TrackType.LOT.getType());
                if (lot.size() > 0) {
                    ArrayList arrayList2 = new ArrayList(lot.size());
                    for (int i4 = 0; i4 < lot.size(); i4++) {
                        RESULTLINE resultline2 = new RESULTLINE();
                        resultline2.NAME = lot.get(i4);
                        resultline2.addLot = true;
                        resultline2.lot = true;
                        arrayList2.add(resultline2);
                    }
                    resultline.addLot = true;
                    if (z) {
                        resultxml.getResultLine().addAll(i3 + 1, arrayList2);
                    } else {
                        resultxml.getResultLine().addAll(i3 + 1 + i2, arrayList2);
                    }
                    i2 += arrayList2.size();
                    z = false;
                }
            }
        }
    }
    private static ArrayList<String> getLot(String str, String str2, String str3, boolean z) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Cursor cursor = null;
            try {
                try {
                    cursor = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_get_print_serial_lot_details), str, str2, str3);
                    if (cursor != null && cursor.moveToFirst()) {
                        for (int i2 = 0; i2 < cursor.getCount(); i2++) {
                            if (cursor.moveToPosition(i2)) {
                                String string = cursor.getString(cursor.getColumnIndex("CODE"));
                                if (z) {
                                    string = (string + " - " + cursor.getDouble(cursor.getColumnIndex("AMOUNT"))) + " - " + DateAndTimeUtils.convertStringDate(cursor.getString(cursor.getColumnIndex("EXPDATE")), "dd-MM-yyyy", "dd.MM.yyyy");
                                }
                                arrayList.add(string + " - " + cursor.getString(cursor.getColumnIndex("LOTNAME")));
                            }
                        }
                    }
                    if (cursor != null) {
                    }
                } catch (Exception e2) {
                    Log.d(TAG, "getLot: ", e2);
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        } catch (ErpNotFoundException e3) {
            Log.d(TAG, "getLot: " + e3);
        }
        return arrayList;
    }
    public static String[] PrintOrderFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2, RESULTXML resultxml3) {
        int i4;
        Exception exc;
        String str2;
        String str3;
        String str4;
        String[] strArr;
        RESULTLINE resultline;
        int i5;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        double[] dArr;
        String str13;
        String str14;
        String sb;
        double[] dArr2;
        String str15;
        String str16;
        String str17;
        int i6;
        RESULTLINE resultline2;
        PrintProcess printProcess;
        String str18;
        double d2;
        String str19;
        double d3;
        double d4;
        double d5;
        String str20 = "";
        String[] strArr2 = new String[1];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        double[] dArr6 = new double[5];
        double[] dArr7 = new double[5];
        PrintProcess printProcess2 = new PrintProcess();
        printProcess2.SprintIniFileName = str;
        int i7 = 0;
        try {
            RESULTLINE resultline3 = resultxml.getResultLine().get(0);
            int convertStringToInt = StringUtils.convertStringToInt(getXmlAttribute(resultline3.TRCURR));
            double d6 = StringUtils.toDouble(getXmlAttribute(resultline3.TRRATE));
            String str21 = getXmlAttribute(resultline3.FICHENO) + "\n\t";
            String str22 = resultline3.DATE_;
            String str23 = ExifInterface.GPS_DIRECTION_TRUE;
            if (str22 != null) {
                try {
                    str2 = str21 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline3.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
                } catch (Exception e2) {
                    e = e2;
                    exc = e;
                    i4 = i7;
                    strArr2[i4] = exc.getMessage();
                    return strArr2;
                }
            } else {
                str2 = str21 + "\n\t";
            }
            String str24 = ((((((((((((((((((((((((((((((((((((((str2 + getXmlAttribute(DateAndTimeUtils.intToNowTime(Integer.parseInt(resultline3.TIME_))) + "\n\t") + getXmlAttribute(resultline3.DOCODE) + "\n\t") + getXmlAttribute(resultline3.SPECODE) + "\n\t") + getXmlAttribute(resultline3.CYPHCODE) + "\n\t") + getXmlAttribute(resultline3.RCODE) + "\n\t") + getXmlAttribute(resultline3.RNAME) + "\n\t") + getXmlAttribute(resultline3.GENEXP1) + "\n\t") + getXmlAttribute(resultline3.GENEXP2) + "\n\t") + getXmlAttribute(resultline3.GENEXP3) + "\n\t") + getXmlAttribute(resultline3.GENEXP4) + "\n\t") + getXmlAttribute(resultline3.SHPTYPCOD) + "\n\t") + getXmlAttribute(resultline3.SHPAGNCOD) + "\n\t") + getXmlAttribute(resultline3.TRADINGGRP) + "\n\t") + getXmlAttribute(resultline3.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t") + getXmlAttribute(resultline3.SDEFINITION) + "\n\t") + getXmlAttribute(resultline3.PCODE) + "\n\t") + getXmlAttribute(resultline3.PDEFINITION) + "\n\t") + getXmlAttribute(resultline3.FCODE) + "\n\t") + getXmlAttribute(resultline3.FNAME) + "\n\t") + getXmlAttribute(resultline3.CCODE) + "\n\t") + getXmlAttribute(resultline3.CDEFINITION) + "\n\t") + getXmlAttribute(resultline3.CSPECODE) + "\n\t") + getXmlAttribute(resultline3.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline3.ADDR1) + "\n\t") + getXmlAttribute(resultline3.ADDR2) + "\n\t") + getXmlAttribute(resultline3.CITY) + "\n\t") + getXmlAttribute(resultline3.TOWN) + "\n\t") + getXmlAttribute(resultline3.TELNRS1) + "\n\t") + getXmlAttribute(resultline3.TELNRS2) + "\n\t") + getXmlAttribute(resultline3.FAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline3.INCHARGE) + "\n\t") + getXmlAttribute(resultline3.DISCRATE) + "\n\t") + getXmlAttribute(resultline3.EMAILADDR) + "\n\t") + getXmlAttribute(resultline3.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CREDIT))) + "\n\t";
            double d7 = StringUtils.toDouble(getXmlAttribute(resultline3.BAKIYE));
            String str25 = "(B)";
            String str26 = "(A)";
            if (d7 < 0.0d) {
                try {
                    str3 = (str24 + StringUtils.dFormat(d7 * (-1.0d)) + "\n\t") + "(A)\n\t";
                } catch (Exception e3) {
                    exc = e3;
                    strArr2 = strArr2;
                    i4 = 0;
                    strArr2[i4] = exc.getMessage();
                    return strArr2;
                }
            } else {
                try {
                    str3 = (str24 + StringUtils.dFormat(d7) + "\n\t") + "(B)\n\t";
                } catch (Exception e4) {
                    i4 = 0;
                    exc = e4;
                    strArr2 = strArr2;
                    strArr2[i4] = exc.getMessage();
                    return strArr2;
                }
            }
            String str27 = (((((((((str3 + getXmlAttribute(resultline3.RADDR1) + "\n\t") + getXmlAttribute(resultline3.RADDR2) + "\n\t") + getXmlAttribute(resultline3.RCITY) + "\n\t") + getXmlAttribute(resultline3.RTOWN) + "\n\t") + getXmlAttribute(resultline3.SADDR1) + "\n\t") + getXmlAttribute(resultline3.SADDR2) + "\n\t") + getXmlAttribute(resultline3.SCITY) + "\n\t") + getXmlAttribute(resultline3.STOWN) + "\n\t") + getXmlAttribute(resultline3.DISTRICT) + "\n\t") + getXmlAttribute(resultline3.TAXOFFCODE) + "\n\t";
            double d8 = StringUtils.toDouble(getXmlAttribute(resultline3.BEFOREBALANCE));
            if (d8 < 0.0d) {
                str4 = str27 + StringUtils.dFormat(d8 * (-1.0d)) + " (A)\n\t";
            } else {
                str4 = str27 + StringUtils.dFormat(d8) + " (B)\n\t";
            }
            String str28 = str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CARISONBAKIYE))) + "\n\t";
            String str29 = ((((resultline3.DUEDATE != null ? str28 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline3.DUEDATE.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t" : str28 + "\n\t") + getXmlAttribute(resultline3.LATITUTE) + "\n\t") + getXmlAttribute(resultline3.LONGITUDE) + "\n\t") + getXmlAttribute(resultline3.TCKNO) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t";
            double d9 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALVAT));
            double d10 = StringUtils.toDouble(getXmlAttribute(resultline3.NETTOTAL));
            double d11 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALDISCOUNTS));
            double d12 = StringUtils.toDouble(getXmlAttribute(resultline3.GROSSTOTAL)) - d11;
            int i8 = i2 <= 0 ? 1 : i2;
            String str30 = str29;
            String[] strArr3 = new String[(resultxml2.getResultLine().size() / i8) + 1];
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            double d16 = 0.0d;
            double d17 = 0.0d;
            double d18 = 0.0d;
            double d19 = 0.0d;
            double d20 = 0.0d;
            int i9 = 0;
            boolean z3 = true;
            int i10 = 1;
            int i11 = 0;
            String str31 = "";
            while (true) {
                try {
                    PrintProcess printProcess3 = printProcess2;
                    if (i9 >= resultxml2.getResultLine().size()) {
                        return strArr3;
                    }
                    RESULTLINE resultline4 = resultxml2.getResultLine().get(i9);
                    int i12 = convertStringToInt;
                    int parseInt = Integer.parseInt(getXmlAttribute(resultline4.LINETYPE));
                    String str32 = str25;
                    String str33 = str26;
                    if (parseInt == 2) {
                        str6 = "0";
                        i5 = i9;
                        resultline = resultline3;
                        i7 = 0;
                        str7 = str23;
                        str9 = str31;
                    } else {
                        try {
                            Integer.parseInt(getXmlAttribute(resultline4.LOGICALREF));
                            int parseInt2 = Integer.parseInt(getXmlAttribute(resultline4.LINENO_));
                            resultline = resultline3;
                            i5 = i9;
                            if (StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode)) == 1) {
                                try {
                                    str5 = resultline4.ICUSTSUPCODE == null ? (str31 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str31 + getXmlAttribute(resultline4.ICUSTSUPCODE) + "\n\t") + getXmlAttribute(resultline4.ICUSTSUPNAME) + "\n\t";
                                } catch (Exception e5) {
                                    strArr2 = strArr3;
                                    exc = e5;
                                    i4 = 0;
                                    strArr2[i4] = exc.getMessage();
                                    return strArr2;
                                }
                            } else if (Integer.parseInt(getXmlAttribute(resultline4.VARIANTREF)) > 0) {
                                str5 = resultline4.VCODE == null ? (str31 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str31 + getXmlAttribute(resultline4.VCODE) + "\n\t") + getXmlAttribute(resultline4.VNAME) + "\n\t";
                            } else {
                                str5 = (str31 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t";
                            }
                            String str34 = ((((((str5 + getXmlAttribute(resultline4.PCODE) + "\n\t") + getXmlAttribute(resultline4.PDEFINITION) + "\n\t") + getXmlAttribute(resultline4.UCODE) + "\n\t") + getXmlAttribute(resultline4.UNAME) + "\n\t") + getXmlAttribute(resultline4.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline4.SPECODE) + "\n\t") + getXmlAttribute(resultline4.DELVRYCODE) + "\n\t";
                            String str35 = str23;
                            String str36 = "0";
                            double d21 = StringUtils.toDouble(getXmlAttribute(resultline4.AMOUNT));
                            String str37 = str34 + checkIsDivUnitForAmountPrintValueOnline(resultline4, d21, "\n\t");
                            d13 += d21;
                            double d22 = StringUtils.toDouble(getXmlAttribute(resultline4.PRICE));
                            String str38 = str37 + StringUtils.dFormat(d22) + "\n\t";
                            double d23 = StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                            String str39 = ((str38 + StringUtils.dFormat(d23) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.DISCPER))) + "\n\t") + StringUtils.toDouble(getXmlAttribute(resultline4.VAT)) + "\n\t";
                            LineType lineType = LineType.PROMOTION;
                            String sb3 = str39 +
                                    StringUtils.dFormat(parseInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d) +
                                    "\n\t";
                            double d24 = StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH));
                            double d25 = parseInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d;
                            double d26 = StringUtils.toDouble(getXmlAttribute(resultline4.VAT));
                            StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                            d14 += StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                            int i13 = 0;
                            while (true) {
                                if (i13 >= 5) {
                                    break;
                                }
                                double d27 = dArr3[i13];
                                if (d27 == d26) {
                                    dArr4[i13] = dArr4[i13] + d25;
                                    dArr7[i13] = dArr7[i13] + d25;
                                    dArr5[i13] = dArr5[i13] + d24;
                                    break;
                                }
                                if (d27 == 0.0d) {
                                    dArr3[i13] = d26;
                                    dArr4[i13] = d25;
                                    dArr6[i13] = d26;
                                    dArr7[i13] = d25;
                                    dArr5[i13] = d24;
                                    break;
                                }
                                i13++;
                            }
                            List<RESULTLINE> orderLineDiscount = getOrderLineDiscount(resultxml3.getResultLine(), parseInt2 + 1);
                            int i14 = 0;
                            while (i14 < 5) {
                                if (orderLineDiscount.size() > i14) {
                                    sb3 = (sb3 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(orderLineDiscount.get(i14).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(orderLineDiscount.get(i14).TOTAL))) + "\n\t";
                                    d15 += StringUtils.toDouble(getXmlAttribute(orderLineDiscount.get(i14).TOTAL));
                                    StringUtils.toDouble(getXmlAttribute(orderLineDiscount.get(i14).TOTAL));
                                    str10 = str36;
                                } else {
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(sb3);
                                    str10 = str36;
                                    sb4.append(str10);
                                    sb4.append("\n\t");
                                    sb3 = sb4.toString() + str10 + "\n\t";
                                }
                                i14++;
                                str36 = str10;
                            }
                            str6 = str36;
                            String str40 = sb3 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.SHIPPEDAMOUNT))) + "\n\t";
                            if (resultline4.DUEDATE != null) {
                                StringBuilder sb5 = new StringBuilder();
                                sb5.append(str40);
                                str7 = str35;
                                i7 = 0;
                                try {
                                    sb5.append(DateAndTimeUtils.convertDateY(getXmlAttribute(resultline4.DUEDATE.split(str7)[0])));
                                    sb5.append("\n\t");
                                    str8 = sb5.toString();
                                } catch (Exception e6) {
                                    e = e6;
                                    strArr2 = strArr3;
                                    exc = e;
                                    i4 = i7;
                                    strArr2[i4] = exc.getMessage();
                                    return strArr2;
                                }
                            } else {
                                str7 = str35;
                                i7 = 0;
                                str8 = str40 + str20 + "\n\t";
                            }
                            String str41 = (((((((((((((((str8 + getXmlAttribute(resultline4.LINEEXP) + "\n\t") + getXmlAttribute(resultline4.GTIPCODE) + "\n\t") + getXmlAttribute(resultline4.Barkod) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.formatToTwoDecimals(StringUtils.toDouble(getXmlAttribute(resultline4.PRICE))) + "\n\t") + getXmlAttribute(resultline4.DCODE) + "\n\t") + getXmlAttribute(resultline4.DNAME) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) + StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t";
                            String sb7 = str41 +
                                    (d6 == 0.0d ? "0.00" : StringUtils.dFormat(d23 / d6)) +
                                    "\n\t";
                            String sb8 = sb7 +
                                    (d6 == 0.0d ? "0.00" : StringUtils.dFormat(d22 / d6)) +
                                    "\n\t";
                            String str42 = sb8 + getXmlAttribute(resultline4.NAME2) + "\n\t";
                            for (int i15 = i7; i15 < 18; i15++) {
                                str42 = str42 + "\n\t";
                            }
                            str9 = ((str42 + getXmlAttribute(resultline4.WIDTH) + "\n\t") + getXmlAttribute(resultline4.LENGTH) + "\n\t") + getXmlAttribute(resultline4.HEIGHT) + "\n\t\r";
                            d16 += StringUtils.toDouble(getXmlAttribute(resultline4.GROSSWEIGHT)) * d21;
                            d17 += StringUtils.toDouble(getXmlAttribute(resultline4.WEIGHT)) * d21;
                            d18 += StringUtils.toDouble(getXmlAttribute(resultline4.VOLUME)) * d21;
                        } catch (Exception e7) {
                            e = e7;
                            i7 = 0;
                        }
                    }
                    int i16 = i5 + 1;
                    try {
                        if (i16 == resultxml2.getResultLine().size() || i16 == i8 * i10) {
                            if (d14 < 0.0d) {
                                d14 = 0.0d;
                            }
                            double d28 = d19 + d14;
                            double d29 = d20 + d13;
                            String str43 = str20;
                            for (int i17 = i7; i17 <= 4; i17++) {
                                str43 = (str43 + StringUtils.dFormat(dArr3[i17]) + "\n\t") + StringUtils.dFormat(dArr4[i17]) + "\n\t";
                            }
                            String str44 = (str43 + StringUtils.dFormat(d14) + "\n\t") + StringUtils.dFormat(d28) + "\n\t";
                            for (int i18 = i7; i18 <= 4; i18++) {
                                str44 = str44 + StringUtils.dFormat(dArr5[i18]) + "\n\t";
                            }
                            RESULTLINE resultline5 = resultline;
                            String sb9 = str44 +
                                    getXmlAttribute(resultline5.GENEXP1) +
                                    "\n\t";
                            String str45 = ((((sb9 + getXmlAttribute(resultline5.GENEXP2) + "\n\t") + getXmlAttribute(resultline5.GENEXP3) + "\n\t") + getXmlAttribute(resultline5.GENEXP4) + "\n\t") + getXmlAttribute(resultline5.SCODE) + "\n\t") + getXmlAttribute(resultline5.SDEFINITION) + "\n\t";
                            String sb10 = str45 +
                                    (d6 == 0.0d ? "0.00" : StringUtils.dFormat(d12 / d6)) +
                                    "\n\t";
                            String str46 = (sb10 + StringUtils.dFormat(d13) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                            str11 = str20;
                            str12 = str7;
                            String str47 = str11;
                            for (int i19 = 0; i19 <= 4; i19++) {
                                str47 = (str47 + StringUtils.dFormat(dArr6[i19]) + "\n\t") + StringUtils.dFormat(dArr7[i19]) + "\n\t";
                            }
                            String str48 = (((((str47 + StringUtils.dFormat(d12) + "\n\t") + StringUtils.dFormat(d11) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d9) + "\n\t") + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d28) + "\n\t";
                            List<RESULTLINE> orderGeneralDiscount = getOrderGeneralDiscount(resultxml3.getResultLine());
                            dArr = dArr3;
                            int i20 = 0;
                            for (int i21 = 5; i20 < i21; i21 = 5) {
                                str48 = orderGeneralDiscount.size() > i20 ? (str48 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(orderGeneralDiscount.get(i20).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(orderGeneralDiscount.get(i20).TOTAL))) + "\n\t" : (str48 + str6 + "\n\t") + str6 + "\n\t";
                                i20++;
                            }
                            String str49 = (str48 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline5.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline5.CREDIT))) + "\n\t";
                            double d30 = StringUtils.toDouble(getXmlAttribute(resultline5.BAKIYE));
                            if (d30 < 0.0d) {
                                String str50 = str49 + StringUtils.dFormat(d30 * (-1.0d)) + "\n\t";
                                StringBuilder sb11 = new StringBuilder();
                                sb11.append(str50);
                                str13 = str33;
                                sb11.append(str13);
                                sb11.append("\n\t");
                                sb = sb11.toString();
                                str14 = str32;
                            } else {
                                str13 = str33;
                                String str51 = str49 + StringUtils.dFormat(d30) + "\n\t";
                                StringBuilder sb12 = new StringBuilder();
                                sb12.append(str51);
                                str14 = str32;
                                sb12.append(str14);
                                sb12.append("\n\t");
                                sb = sb12.toString();
                            }
                            StringBuilder sb13 = new StringBuilder();
                            sb13.append(sb);
                            str32 = str14;
                            dArr2 = dArr4;
                            sb13.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d10), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                            sb13.append("\n\t");
                            String str52 = (sb13.toString() + StringUtils.dFormat(d12) + "\n\t") + StringUtils.dFormat(d12) + "\n\t";
                            for (int i22 = 0; i22 <= 4; i22++) {
                                str52 = str52 + StringUtils.dFormat(dArr5[i22]) + "\n\t";
                            }
                            String str53 = (((((((str52 + getXmlAttribute(resultline5.GENEXP1) + "\n\t") + getXmlAttribute(resultline5.GENEXP2) + "\n\t") + getXmlAttribute(resultline5.GENEXP3) + "\n\t") + getXmlAttribute(resultline5.GENEXP4) + "\n\t") + getXmlAttribute(resultline5.SCODE) + "\n\t") + getXmlAttribute(resultline5.SDEFINITION) + "\n\t") + getXmlAttribute(resultline5.DISCRATE) + "\n\t") + getClCardCurrencyCode(StringUtils.convertStringToInt(getXmlAttribute(resultline5.CLIENTREF))) + "\n\t";
                            String sb15 = str53 +
                                    (d6 == 0.0d ? "0.00" : StringUtils.dFormat(d6)) +
                                    "\n\t";
                            double d31 = d10 / d6;
                            String sb17 = sb15 +
                                    StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d31), i12) +
                                    "\n\t";
                            String sb19 = sb17 +
                                    (d6 == 0.0d ? "0.00" : StringUtils.dFormat(d31)) +
                                    "\n\t";
                            String sb20 = sb19 +
                                    (d6 != 0.0d ? StringUtils.dFormat(d9 / d6) : "0.00") +
                                    "\n\t";
                            String str54 = ((((((((sb20 + "\n\t") + StringUtils.dFormat(d29) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.dFormat(d16) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d17) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                            for (int i23 = 0; i23 < 8; i23++) {
                                str54 = str54 + "\n\t";
                            }
                            String str55 = str54 + StringUtils.dFormat(d18) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                            str15 = (!z || z3) ? str30 : str11;
                            if (i16 == resultxml2.getResultLine().size()) {
                                str17 = str55;
                                str16 = z2 ? str11 : str46;
                                strArr = strArr3;
                            } else {
                                strArr = strArr3;
                                str16 = str46;
                                str17 = str11;
                            }
                            i6 = i12;
                            resultline2 = resultline5;
                            i4 = 0;
                            String str56 = str9;
                            printProcess = printProcess3;
                            str18 = str13;
                            try {
                                String Print = printProcess3.Print(i8, str15, str56, str16, str17);
                                if (i3 == 1) {
                                    strArr[i11] = StringUtils.convTrCharEN(Print);
                                } else {
                                    strArr[i11] = Print;
                                }
                                i11++;
                                i10++;
                                d2 = 0.0d;
                                str19 = str11;
                                d3 = d29;
                                z3 = false;
                                d4 = d28;
                                d5 = 0.0d;
                            } catch (Exception e8) {
                                e = e8;
                                strArr2 = strArr;
                                exc = e;
                                strArr2[i4] = exc.getMessage();
                                return strArr2;
                            }
                        } else {
                            str12 = str7;
                            dArr = dArr3;
                            d3 = d20;
                            str19 = str9;
                            d4 = d19;
                            printProcess = printProcess3;
                            str18 = str33;
                            dArr2 = dArr4;
                            d5 = d14;
                            i6 = i12;
                            resultline2 = resultline;
                            str15 = str30;
                            d2 = d13;
                            str11 = str20;
                            strArr = strArr3;
                        }
                        strArr3 = strArr;
                        printProcess2 = printProcess;
                        i9 = i16;
                        str31 = str19;
                        str30 = str15;
                        dArr3 = dArr;
                        str20 = str11;
                        convertStringToInt = i6;
                        str26 = str18;
                        dArr4 = dArr2;
                        str23 = str12;
                        d13 = d2;
                        resultline3 = resultline2;
                        d14 = d5;
                        d19 = d4;
                        str25 = str32;
                        d20 = d3;
                    } catch (Exception e9) {
                        e = e9;
                        strArr = strArr3;
                        i4 = i7;
                    }
                } catch (Exception e10) {
                    e = e10;
                    strArr = strArr3;
                    i4 = 0;
                }
            }
        } catch (Exception e11) {
            e = e11;
            i4 = 0;
        }
    }
    public static String[] PrintDispatchFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2, RESULTXML resultxml3) {
        char c2;
        Exception exc;
        String str2;
        String str3;
        String str4;
        String str5;
        String[] strArr;
        String str6;
        int i4;
        int i5;
        RESULTLINE resultline;
        String str7;
        String str8;
        String str9;
        int i6;
        int i7;
        RESULTLINE resultline2;
        double[] dArr;
        int i8;
        int i9;
        double[] dArr2;
        int i10;
        double d2;
        String str10;
        String str11;
        String sb;
        double[] dArr3;
        int i11;
        int i12;
        String str12;
        String str13;
        String str14;
        int i13;
        String str15;
        double d3;
        boolean z3;
        double d4;
        double d5;
        double d6;
        String[] strArr2 = new String[1];
        double[] dArr4 = new double[5];
        double[] dArr5 = new double[5];
        double[] dArr6 = new double[5];
        double[] dArr7 = new double[5];
        double[] dArr8 = new double[5];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            RESULTLINE resultline3 = resultxml.getResultLine().get(0);
            int convertStringToInt = StringUtils.convertStringToInt(getXmlAttribute(resultline3.TRCURR));
            double d7 = StringUtils.toDouble(getXmlAttribute(resultline3.TRRATE));
            String str16 = getXmlAttribute(resultline3.FICHENO) + "\n\t";
            if (resultline3.DATE_ != null) {
                try {
                    str2 = str16 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline3.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
                } catch (Exception e2) {
                    exc = e2;
                    c2 = 0;
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } else {
                str2 = str16 + "\n\t";
            }
            String str17 = (((((((((((((((((((((((((((((((((((((str2 + getXmlAttribute(resultline3.DOCODE) + "\n\t") + getXmlAttribute(resultline3.SPECODE) + "\n\t") + getXmlAttribute(resultline3.CYPHCODE) + "\n\t") + getXmlAttribute(resultline3.RCODE) + "\n\t") + getXmlAttribute(resultline3.RNAME) + "\n\t") + getXmlAttribute(resultline3.GENEXP1) + "\n\t") + getXmlAttribute(resultline3.GENEXP2) + "\n\t") + getXmlAttribute(resultline3.GENEXP3) + "\n\t") + getXmlAttribute(resultline3.GENEXP4) + "\n\t") + getXmlAttribute(resultline3.SHPTYPCOD) + "\n\t") + getXmlAttribute(resultline3.SHPAGNCOD) + "\n\t") + getXmlAttribute(resultline3.TRADINGGRP) + "\n\t") + getXmlAttribute(resultline3.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t") + getXmlAttribute(resultline3.SDEFINITION) + "\n\t") + getXmlAttribute(resultline3.PCODE) + "\n\t") + getXmlAttribute(resultline3.PDEFINITION) + "\n\t") + getXmlAttribute(resultline3.FCODE) + "\n\t") + getXmlAttribute(resultline3.FNAME) + "\n\t") + getXmlAttribute(resultline3.CCODE) + "\n\t") + getXmlAttribute(resultline3.CDEFINITION) + "\n\t") + getXmlAttribute(resultline3.CSPECODE) + "\n\t") + getXmlAttribute(resultline3.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline3.ADDR1) + "\n\t") + getXmlAttribute(resultline3.ADDR2) + "\n\t") + getXmlAttribute(resultline3.CITY) + "\n\t") + getXmlAttribute(resultline3.TOWN) + "\n\t") + getXmlAttribute(resultline3.TELNRS1) + "\n\t") + getXmlAttribute(resultline3.TELNRS2) + "\n\t") + getXmlAttribute(resultline3.FAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXNR) + "\n\t") + getXmlAttribute(resultline3.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline3.INCHARGE) + "\n\t") + getXmlAttribute(resultline3.DISCRATE) + "\n\t") + getXmlAttribute(resultline3.EMAILADDR) + "\n\t") + getXmlAttribute(resultline3.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CREDIT))) + "\n\t";
            double d8 = StringUtils.toDouble(getXmlAttribute(resultline3.BAKIYE));
            String str18 = "(B)";
            String str19 = "(A)";
            if (d8 < 0.0d) {
                try {
                    str3 = (str17 + StringUtils.dFormat(d8 * (-1.0d)) + "\n\t") + "(A)\n\t";
                } catch (Exception e3) {
                    exc = e3;
                    c2 = 0;
                    strArr2 = strArr2;
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } else {
                try {
                    str3 = (str17 + StringUtils.dFormat(d8) + "\n\t") + "(B)\n\t";
                } catch (Exception e4) {
                    e = e4;
                    c2 = 0;
                    exc = e;
                    strArr2 = strArr2;
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            }
            String str20 = ((((((((((resultline3.FTIME != null ? str3 + getXmlAttribute(DateAndTimeUtils.intToNowTime(Integer.parseInt(resultline3.FTIME))) + "\n\t" : str3 + "\n\t") + getXmlAttribute(resultline3.RADDR1) + "\n\t") + getXmlAttribute(resultline3.RADDR2) + "\n\t") + getXmlAttribute(resultline3.RCITY) + "\n\t") + getXmlAttribute(resultline3.RTOWN) + "\n\t") + getXmlAttribute(resultline3.SADDR1) + "\n\t") + getXmlAttribute(resultline3.SADDR2) + "\n\t") + getXmlAttribute(resultline3.SCITY) + "\n\t") + getXmlAttribute(resultline3.STOWN) + "\n\t") + getXmlAttribute(resultline3.DISTRICT) + "\n\t") + getXmlAttribute(resultline3.TAXOFFCODE) + "\n\t";
            double d9 = StringUtils.toDouble(getXmlAttribute(resultline3.BEFOREBALANCE));
            if (d9 < 0.0d) {
                str4 = str20 + StringUtils.dFormat(d9 * (-1.0d)) + " (A)\n\t";
            } else {
                str4 = str20 + StringUtils.dFormat(d9) + " (B)\n\t";
            }
            String str21 = ((((((str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline3.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline3.LATITUTE) + "\n\t") + getXmlAttribute(resultline3.LONGITUDE) + "\n\t") + getXmlAttribute(resultline3.TCKNO) + "\n\t") + getPaymentDate(resultline3) + "\n\t") + getXmlAttribute(resultline3.SCODE) + "\n\t") + getXmlAttribute(resultline3.GUID) + "\n\t";
            double d10 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALVAT));
            double d11 = StringUtils.toDouble(getXmlAttribute(resultline3.NETTOTAL));
            double d12 = StringUtils.toDouble(getXmlAttribute(resultline3.TOTALDISCOUNTS));
            double d13 = StringUtils.toDouble(getXmlAttribute(resultline3.GROSSTOTAL)) - d12;
            int i14 = i2 <= 0 ? 1 : i2;
            if (ErpCreator.getInstance().getmBaseErp().getPrintLotDetail()) {
                try {
                    str5 = str21;
                    detailResultXmlLotControl(resultxml2, resultline3.INVOICEREF);
                } catch (Exception e5) {
                    exc = e5;
                    strArr2 = strArr2;
                    c2 = 0;
                    strArr2[c2] = exc.getMessage();
                    return strArr2;
                }
            } else {
                str5 = str21;
            }
            try {
                String str22 = str5;
                String[] strArr3 = new String[(resultxml2.getResultLine().size() / i14) + 1];
                double d14 = 0.0d;
                double d15 = 0.0d;
                double d16 = 0.0d;
                double d17 = 0.0d;
                double d18 = 0.0d;
                double d19 = 0.0d;
                double d20 = 0.0d;
                double d21 = 0.0d;
                int i15 = 0;
                int i16 = 1;
                boolean z4 = true;
                int i17 = 0;
                String str23 = "";
                while (true) {
                    try {
                        PrintProcess printProcess2 = printProcess;
                        if (i15 >= resultxml2.getResultLine().size()) {
                            return strArr3;
                        }
                        RESULTLINE resultline4 = resultxml2.getResultLine().get(i15);
                        int i18 = convertStringToInt;
                        String str24 = str18;
                        try {
                            if (resultline4.lot) {
                                i4 = i15;
                                str6 = str19;
                                i5 = i14;
                                resultline = resultline3;
                                String str25 = (str23 + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t";
                                for (int i19 = 0; i19 < 39; i19++) {
                                    str25 = str25 + "\n\t";
                                }
                                str8 = str25 + "\r";
                            } else {
                                try {
                                    int parseInt = Integer.parseInt(getXmlAttribute(resultline4.LINETYPE));
                                    str6 = str19;
                                    if (parseInt != 2) {
                                        Integer.parseInt(getXmlAttribute(resultline4.LOGICALREF));
                                        int parseInt2 = Integer.parseInt(getXmlAttribute(resultline4.STFICHELNNO));
                                        resultline = resultline3;
                                        i5 = i14;
                                        if (StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getParamValue(ParameterTypes.ptUseTedarikciCode)) == 1) {
                                            str7 = resultline4.ICUSTSUPCODE == null ? (str23 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str23 + getXmlAttribute(resultline4.ICUSTSUPCODE) + "\n\t") + getXmlAttribute(resultline4.ICUSTSUPNAME) + "\n\t";
                                        } else if (Integer.parseInt(getXmlAttribute(resultline4.VARIANTREF)) > 0) {
                                            str7 = resultline4.VCODE == null ? (str23 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t" : (str23 + getXmlAttribute(resultline4.VCODE) + "\n\t") + getXmlAttribute(resultline4.VNAME) + "\n\t";
                                        } else {
                                            str7 = (str23 + getXmlAttribute(resultline4.CODE) + "\n\t") + getXmlAttribute(resultline4.NAME) + "\n\t";
                                        }
                                        String str26 = (((((((str7 + getXmlAttribute(resultline4.FICHENO) + "\n\t") + getXmlAttribute(resultline4.PCODE) + "\n\t") + getXmlAttribute(resultline4.PDEFINITION) + "\n\t") + getXmlAttribute(resultline4.UCODE) + "\n\t") + getXmlAttribute(resultline4.UNAME) + "\n\t") + getXmlAttribute(resultline4.SOURCEINDEX) + "\n\t") + getXmlAttribute(resultline4.SPECODE) + "\n\t") + getXmlAttribute(resultline4.DELVRYCODE) + "\n\t";
                                        double d22 = StringUtils.toDouble(getXmlAttribute(resultline4.AMOUNT));
                                        i4 = i15;
                                        String str27 = str26 + checkIsDivUnitForAmountPrintValueOnline(resultline4, d22, "\n\t");
                                        d14 += d22;
                                        double d23 = StringUtils.toDouble(getXmlAttribute(resultline4.PRICE));
                                        String str28 = str27 + StringUtils.dFormat(d23) + "\n\t";
                                        double d24 = StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                        String str29 = ((str28 + StringUtils.dFormat(d24) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.DISCPER))) + "\n\t") + StringUtils.toDouble(getXmlAttribute(resultline4.VAT)) + "\n\t";
                                        LineType lineType = LineType.PROMOTION;
                                        String sb3 = str29 +
                                                StringUtils.dFormat(parseInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d) +
                                                "\n\t";
                                        double d25 = StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH));
                                        double d26 = parseInt != lineType.value ? StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) : 0.0d;
                                        double d27 = StringUtils.toDouble(getXmlAttribute(resultline4.VAT));
                                        StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                        d15 += StringUtils.toDouble(getXmlAttribute(resultline4.TOTAL));
                                        int i20 = 5;
                                        int i21 = 0;
                                        while (true) {
                                            if (i21 >= i20) {
                                                break;
                                            }
                                            double d28 = dArr4[i21];
                                            if (d28 == d27) {
                                                dArr5[i21] = dArr5[i21] + d26;
                                                dArr8[i21] = dArr8[i21] + d26;
                                                dArr6[i21] = dArr6[i21] + d25;
                                                break;
                                            }
                                            if (d28 == 0.0d) {
                                                dArr4[i21] = d27;
                                                dArr5[i21] = d26;
                                                dArr7[i21] = d27;
                                                dArr8[i21] = d26;
                                                dArr6[i21] = d25;
                                                break;
                                            }
                                            i21++;
                                            i20 = 5;
                                        }
                                        List<RESULTLINE> lineDiscount = getLineDiscount(resultxml3.getResultLine(), parseInt2 + 1);
                                        int i22 = 0;
                                        for (int i23 = 5; i22 < i23; i23 = 5) {
                                            if (lineDiscount.size() > i22) {
                                                str9 = (sb3 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i22).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i22).TOTAL))) + "\n\t";
                                                d16 += StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i22).TOTAL));
                                                StringUtils.toDouble(getXmlAttribute(lineDiscount.get(i22).TOTAL));
                                            } else {
                                                str9 = (sb3 + "0\n\t") + "0\n\t";
                                            }
                                            sb3 = str9;
                                            i22++;
                                        }
                                        String str30 = ((((((((((((((((sb3 + getXmlAttribute(resultline4.LINEEXP) + "\n\t") + getXmlAttribute(resultline4.GTIPCODE) + "\n\t") + getXmlAttribute(resultline4.Barkod) + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + "\n\t") + StringUtils.formatToTwoDecimals(StringUtils.toDouble(getXmlAttribute(resultline4.PRICE))) + "\n\t") + "\n\t") + getXmlAttribute(resultline4.DCODE) + "\n\t") + getXmlAttribute(resultline4.DNAME) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline4.VATAMNT)) + StringUtils.toDouble(getXmlAttribute(resultline4.VATMATRAH))) + "\n\t";
                                        String sb5 = str30 +
                                                (d7 == 0.0d ? "0.00" : StringUtils.dFormat(d24 / d7)) +
                                                "\n\t";
                                        String sb6 = sb5 +
                                                (d7 == 0.0d ? "0.00" : StringUtils.dFormat(d23 / d7)) +
                                                "\n\t";
                                        String str31 = sb6 + getXmlAttribute(resultline4.NAME2) + "\n\t";
                                        for (int i24 = 0; i24 < 19; i24++) {
                                            str31 = str31 + "\n\t";
                                        }
                                        str8 = ((str31 + getXmlAttribute(resultline4.WIDTH) + "\n\t") + getXmlAttribute(resultline4.LENGTH) + "\n\t") + getXmlAttribute(resultline4.HEIGHT) + "\n\t\r";
                                        d17 += StringUtils.toDouble(getXmlAttribute(resultline4.GROSSWEIGHT)) * d22;
                                        d18 += StringUtils.toDouble(getXmlAttribute(resultline4.WEIGHT)) * d22;
                                        d19 += StringUtils.toDouble(getXmlAttribute(resultline4.VOLUME)) * d22;
                                    } else {
                                        i4 = i15;
                                        i5 = i14;
                                        resultline = resultline3;
                                        i15 = i4 + 1;
                                        if (i15 != resultxml2.getResultLine().size() || i15 == i5 * i16) {
                                            if (d15 < 0.0d) {
                                                d15 = 0.0d;
                                            }
                                            double d29 = d20 + d15;
                                            double d30 = d21 + d14;
                                            String str32 = "";
                                            for (i6 = 0; i6 <= 4; i6++) {
                                                str32 = (str32 + StringUtils.dFormat(dArr4[i6]) + "\n\t") + StringUtils.dFormat(dArr5[i6]) + "\n\t";
                                            }
                                            String str33 = (str32 + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d29) + "\n\t";
                                            for (i7 = 0; i7 <= 4; i7++) {
                                                str33 = str33 + StringUtils.dFormat(dArr6[i7]) + "\n\t";
                                            }
                                            StringBuilder sb7 = new StringBuilder();
                                            sb7.append(str33);
                                            resultline2 = resultline;
                                            sb7.append(getXmlAttribute(resultline2.GENEXP1));
                                            sb7.append("\n\t");
                                            String str34 = (((((((sb7.toString() + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                                            String sb8 = str34 +
                                                    (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d13 / d7)) +
                                                    "\n\t";
                                            String str35 = (sb8 + StringUtils.dFormat(d14) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                            dArr = dArr4;
                                            i9 = 0;
                                            String str36 = "";
                                            for (i8 = 5; i9 < i8; i8 = 5) {
                                                str36 = (str36 + StringUtils.dFormat(dArr7[i9]) + "\n\t") + StringUtils.dFormat(dArr8[i9]) + "\n\t";
                                                i9++;
                                            }
                                            String str37 = (((((str36 + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d12) + "\n\t") + StringUtils.dFormat(d11) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d16) + "\n\t") + StringUtils.dFormat(d29) + "\n\t";
                                            List<RESULTLINE> generalDiscount = getGeneralDiscount(resultxml3.getResultLine());
                                            dArr2 = dArr5;
                                            for (i10 = 0; i10 < 5; i10++) {
                                                str37 = generalDiscount.size() > i10 ? (str37 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(generalDiscount.get(i10).DISCPER))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(generalDiscount.get(i10).TOTAL))) + "\n\t" : (str37 + "0\n\t") + "0\n\t";
                                            }
                                            String str38 = (str37 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                                            d2 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                                            if (d2 >= 0.0d) {
                                                String str39 = str38 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t";
                                                StringBuilder sb9 = new StringBuilder();
                                                sb9.append(str39);
                                                str10 = str6;
                                                sb9.append(str10);
                                                sb9.append("\n\t");
                                                sb = sb9.toString();
                                                str11 = str24;
                                            } else {
                                                str10 = str6;
                                                String str40 = str38 + StringUtils.dFormat(d2) + "\n\t";
                                                StringBuilder sb10 = new StringBuilder();
                                                sb10.append(str40);
                                                str11 = str24;
                                                sb10.append(str11);
                                                sb10.append("\n\t");
                                                sb = sb10.toString();
                                            }
                                            StringBuilder sb11 = new StringBuilder();
                                            sb11.append(sb);
                                            String str41 = str11;
                                            dArr3 = dArr7;
                                            sb11.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d11), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                                            sb11.append("\n\t");
                                            String str42 = ((sb11.toString() + StringUtils.dFormat(d13) + "\n\t") + "\n\t") + StringUtils.dFormat(d13) + "\n\t";
                                            for (i11 = 0; i11 < 5; i11++) {
                                                str42 = str42 + StringUtils.dFormat(dArr6[i11]) + "\n\t";
                                            }
                                            String str43 = ((((((((str42 + getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + getClCardCurrencyCode(StringUtils.convertStringToInt(getXmlAttribute(resultline2.CLIENTREF))) + "\n\t";
                                            String sb13 = str43 +
                                                    (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d7)) +
                                                    "\n\t";
                                            double d31 = d11 / d7;
                                            String sb15 = sb13 +
                                                    StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d31), i18) +
                                                    "\n\t";
                                            String sb17 = sb15 +
                                                    (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d31)) +
                                                    "\n\t";
                                            String sb18 = sb17 +
                                                    (d7 != 0.0d ? StringUtils.dFormat(d10 / d7) : "0.00") +
                                                    "\n\t";
                                            String str44 = ((((sb18 + "\n\t") + StringUtils.dFormat(d30) + "\n\t") + StringUtils.dFormat(d17) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d18) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                                            for (i12 = 0; i12 < 10; i12++) {
                                                str44 = str44 + "\n\t";
                                            }
                                            String str45 = str44 + StringUtils.dFormat(d19) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                                            String str46 = (z || z4) ? str22 : "";
                                            if (i15 != resultxml2.getResultLine().size()) {
                                                str12 = str45;
                                                if (z2) {
                                                    str14 = str41;
                                                    str13 = "";
                                                    i13 = i18;
                                                    strArr = strArr3;
                                                    c2 = 0;
                                                    str15 = str10;
                                                    String Print = printProcess2.Print(i5, str46, str23, str13, str12);
                                                    if (i3 == 1) {
                                                        strArr[i17] = StringUtils.convTrCharEN(Print);
                                                    } else {
                                                        strArr[i17] = Print;
                                                    }
                                                    i17++;
                                                    i16++;
                                                    str23 = "";
                                                    str22 = str46;
                                                    d3 = 0.0d;
                                                    z3 = false;
                                                    d4 = d30;
                                                    d5 = d29;
                                                    d6 = 0.0d;
                                                }
                                            } else {
                                                str12 = "";
                                            }
                                            str13 = str35;
                                            str14 = str41;
                                            i13 = i18;
                                            strArr = strArr3;
                                            c2 = 0;
                                            str15 = str10;
                                            String Print2 = printProcess2.Print(i5, str46, str23, str13, str12);
                                            if (i3 == 1) {
                                            }
                                            i17++;
                                            i16++;
                                            str23 = "";
                                            str22 = str46;
                                            d3 = 0.0d;
                                            z3 = false;
                                            d4 = d30;
                                            d5 = d29;
                                            d6 = 0.0d;
                                        } else {
                                            z3 = z4;
                                            d3 = d14;
                                            str15 = str6;
                                            resultline2 = resultline;
                                            i13 = i18;
                                            dArr = dArr4;
                                            dArr2 = dArr5;
                                            d4 = d21;
                                            d5 = d20;
                                            d6 = d15;
                                            dArr3 = dArr7;
                                            str14 = str24;
                                            strArr = strArr3;
                                        }
                                        resultline3 = resultline2;
                                        str18 = str14;
                                        convertStringToInt = i13;
                                        dArr4 = dArr;
                                        dArr5 = dArr2;
                                        dArr7 = dArr3;
                                        d15 = d6;
                                        d20 = d5;
                                        strArr3 = strArr;
                                        printProcess = printProcess2;
                                        str19 = str15;
                                        d21 = d4;
                                        z4 = z3;
                                        d14 = d3;
                                        i14 = i5;
                                    }
                                } catch (Exception e6) {
                                    strArr2 = strArr3;
                                    exc = e6;
                                    c2 = 0;
                                    strArr2[c2] = exc.getMessage();
                                    return strArr2;
                                }
                            }
                            String Print22 = printProcess2.Print(i5, str46, str23, str13, str12);
                            if (i3 == 1) {
                            }
                            i17++;
                            i16++;
                            str23 = "";
                            str22 = str46;
                            d3 = 0.0d;
                            z3 = false;
                            d4 = d30;
                            d5 = d29;
                            d6 = 0.0d;
                            resultline3 = resultline2;
                            str18 = str14;
                            convertStringToInt = i13;
                            dArr4 = dArr;
                            dArr5 = dArr2;
                            dArr7 = dArr3;
                            d15 = d6;
                            d20 = d5;
                            strArr3 = strArr;
                            printProcess = printProcess2;
                            str19 = str15;
                            d21 = d4;
                            z4 = z3;
                            d14 = d3;
                            i14 = i5;
                        } catch (Exception e7) {
                            e = e7;
                            exc = e;
                            strArr2 = strArr;
                            strArr2[c2] = exc.getMessage();
                            return strArr2;
                        }
                        str23 = str8;
                        i15 = i4 + 1;
                        if (i15 != resultxml2.getResultLine().size()) {
                        }
                        if (d15 < 0.0d) {
                        }
                        double d292 = d20 + d15;
                        double d302 = d21 + d14;
                        String str322 = "";
                        while (i6 <= 4) {
                        }
                        String str332 = (str322 + StringUtils.dFormat(d15) + "\n\t") + StringUtils.dFormat(d292) + "\n\t";
                        while (i7 <= 4) {
                        }
                        StringBuilder sb72 = new StringBuilder();
                        sb72.append(str332);
                        resultline2 = resultline;
                        sb72.append(getXmlAttribute(resultline2.GENEXP1));
                        sb72.append("\n\t");
                        String str342 = (((((((sb72.toString() + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + "\n\t") + "\n\t") + "\n\t";
                        String sb82 = str342 +
                                (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d13 / d7)) +
                                "\n\t";
                        String str352 = (sb82 + StringUtils.dFormat(d14) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                        dArr = dArr4;
                        i9 = 0;
                        String str362 = "";
                        while (i9 < i8) {
                        }
                        String str372 = (((((str362 + StringUtils.dFormat(d13) + "\n\t") + StringUtils.dFormat(d12) + "\n\t") + StringUtils.dFormat(d11) + "\n\t") + StringUtils.dFormat(d10) + "\n\t") + StringUtils.dFormat(d16) + "\n\t") + StringUtils.dFormat(d292) + "\n\t";
                        List<RESULTLINE> generalDiscount2 = getGeneralDiscount(resultxml3.getResultLine());
                        dArr2 = dArr5;
                        while (i10 < 5) {
                        }
                        String str382 = (str372 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
                        d2 = StringUtils.toDouble(getXmlAttribute(resultline2.BAKIYE));
                        if (d2 >= 0.0d) {
                        }
                        StringBuilder sb112 = new StringBuilder();
                        sb112.append(sb);
                        String str412 = str11;
                        dArr3 = dArr7;
                        sb112.append(StringUtils.convertTotal2Text(StringUtils.dFormat(d11), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                        sb112.append("\n\t");
                        String str422 = ((sb112.toString() + StringUtils.dFormat(d13) + "\n\t") + "\n\t") + StringUtils.dFormat(d13) + "\n\t";
                        while (i11 < 5) {
                        }
                        String str432 = ((((((((str422 + getXmlAttribute(resultline2.GENEXP1) + "\n\t") + getXmlAttribute(resultline2.GENEXP2) + "\n\t") + getXmlAttribute(resultline2.GENEXP3) + "\n\t") + getXmlAttribute(resultline2.GENEXP4) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + StringUtils.dFormat(d13) + "\n\t") + getClCardCurrencyCode(StringUtils.convertStringToInt(getXmlAttribute(resultline2.CLIENTREF))) + "\n\t";
                        String sb132 = str432 +
                                (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d7)) +
                                "\n\t";
                        double d312 = d11 / d7;
                        String sb152 = sb132 +
                                StringUtils.convertTotal2TextByCurrency(StringUtils.dFormat(d312), i18) +
                                "\n\t";
                        String sb172 = sb152 +
                                (d7 != 0.0d ? "0.00" : StringUtils.dFormat(d312)) +
                                "\n\t";
                        String sb182 = sb172 +
                                (d7 != 0.0d ? StringUtils.dFormat(d10 / d7) : "0.00") +
                                "\n\t";
                        String str442 = ((((sb182 + "\n\t") + StringUtils.dFormat(d302) + "\n\t") + StringUtils.dFormat(d17) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.dFormat(d18) + ContextUtils.getStringResource(R.string.kg_main_unit_code) + "\n\t") + StringUtils.convertIntToString(resultxml2.getResultLine().size()) + "\n\t";
                        while (i12 < 10) {
                        }
                        String str452 = str442 + StringUtils.dFormat(d19) + ContextUtils.getStringResource(R.string.lt_main_unit_code) + "\n\t";
                        if (z) {
                        }
                        if (i15 != resultxml2.getResultLine().size()) {
                        }
                        str13 = str352;
                        str14 = str412;
                        i13 = i18;
                        strArr = strArr3;
                        c2 = 0;
                        str15 = str10;
                    } catch (Exception e8) {
                        e = e8;
                        strArr = strArr3;
                        c2 = 0;
                    }
                }
            } catch (Exception e9) {
                e = e9;
                c2 = 0;
                exc = e;
                strArr2 = strArr2;
                strArr2[c2] = exc.getMessage();
                return strArr2;
            }
        } catch (Exception e10) {
            c2 = 0;
            exc = e10;
        }
    }
    public static String[] PrintCashFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        String str2;
        String str3;
        int i4;
        boolean z3;
        String str4;
        String str5;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        if (resultxml.getResultLine().size() == 0) {
            strArr[0] = "Fi? Bulunamad?";
            return strArr;
        }
        RESULTLINE resultline = resultxml.getResultLine().get(0);
        String str6 = getXmlAttribute(resultline.FICHENO) + "\n\t";
        if (resultline.DATE_ != null) {
            str2 = str6 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
        } else {
            str2 = str6 + "\n\t";
        }
        String str7 = (((((str2 + getXmlAttribute(resultline.SPECODE) + "\n\t") + getXmlAttribute(resultline.CYPHCODE) + "\n\t") + getXmlAttribute(resultline.GENEXP1) + "\n\t") + getXmlAttribute(resultline.GENEXP2) + "\n\t") + getXmlAttribute(resultline.GENEXP3) + "\n\t") + getXmlAttribute(resultline.GENEXP4) + "\n\t";
        double d2 = 0.0d;
        if (resultxml2.getResultLine().size() > 0) {
            RESULTLINE resultline2 = resultxml2.getResultLine().get(0);
            String str8 = ((((((((((((((((((str7 + getXmlAttribute(resultline2.CCODE) + "\n\t") + getXmlAttribute(resultline2.CDEFINITION) + "\n\t") + getXmlAttribute(resultline2.CSPECODE) + "\n\t") + getXmlAttribute(resultline2.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline2.ADDR1) + "\n\t") + getXmlAttribute(resultline2.ADDR2) + "\n\t") + getXmlAttribute(resultline2.CITY) + "\n\t") + getXmlAttribute(resultline2.TOWN) + "\n\t") + getXmlAttribute(resultline2.TELNRS1) + "\n\t") + getXmlAttribute(resultline2.TELNRS2) + "\n\t") + getXmlAttribute(resultline2.FAXNR) + "\n\t") + getXmlAttribute(resultline2.TAXNR) + "\n\t") + getXmlAttribute(resultline2.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline2.INCHARGE) + "\n\t") + getXmlAttribute(resultline2.DISCRATE) + "\n\t") + getXmlAttribute(resultline2.EMAILADDR) + "\n\t") + getXmlAttribute(resultline2.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CREDIT))) + "\n\t";
            double d3 = StringUtils.toDouble(getXmlAttribute(resultline2.DEBITACCOUNT));
            if (d3 < 0.0d) {
                str4 = (str8 + StringUtils.dFormat(d3 * (-1.0d)) + "\n\t") + "(A)\n\t";
            } else {
                str4 = (str8 + StringUtils.dFormat(d3) + "\n\t") + "(B)\n\t";
            }
            String str9 = ((str4 + DateAndTimeUtils.nowTime2() + "\n\t") + getXmlAttribute(resultline2.DISTRICT) + "\n\t") + getXmlAttribute(resultline2.TAXOFFCODE) + "\n\t";
            Integer.parseInt(getXmlAttribute(resultline2.CLIENTREF));
            String str10 = (str9 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.AMOUNT))) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(getXmlAttribute(resultline2.AMOUNT)), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
            StringUtils.toDouble(getXmlAttribute(resultline2.AMOUNT));
            StringUtils.toDouble(getXmlAttribute(resultline2.DEBITACCOUNT));
            double d4 = StringUtils.toDouble(getXmlAttribute(resultline2.BEFOREBALANCE));
            if (d4 < 0.0d) {
                str5 = str10 + StringUtils.dFormat(d4 * (-1.0d)) + " (A)\n\t";
            } else {
                str5 = str10 + StringUtils.dFormat(d4) + " (B)\n\t";
            }
            str7 = str5 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline2.CARISONBAKIYE))) + "\n\t";
        }
        String str11 = ((((str7 + getXmlAttribute(resultline.SCODE) + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t") + getXmlAttribute(resultline.TCKNO) + "\n\t") + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t";
        int i5 = i2 <= 0 ? 1 : i2;
        strArr = new String[(resultxml2.getResultLine().size() / i5) + 1];
        String str12 = "";
        boolean z4 = true;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        double d5 = 0.0d;
        double d6 = 0.0d;
        while (i7 < resultxml2.getResultLine().size()) {
            String str13 = (((str12 + getXmlAttribute(resultxml2.getResultLine().get(i7).SPECODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultxml2.getResultLine().get(i7).AMOUNT))) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).DOCODE) + "\n\t") + '\r';
            d5 += StringUtils.toDouble(resultxml2.getResultLine().get(i7).AMOUNT);
            int i9 = i7 + 1;
            if (i9 != resultxml2.getResultLine().size() && i9 != i5 * i6) {
                str3 = str11;
                i4 = i9;
                str12 = str13;
                z3 = true;
                str11 = str3;
                i7 = i4;
                d2 = 0.0d;
            }
            if (d5 < d2) {
                d5 = d2;
            }
            d6 += d5;
            String str14 = ((((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d6) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t";
            String str15 = (((getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t") + StringUtils.dFormat(d6) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d6), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
            str3 = (!z || z4) ? str11 : "";
            if (i9 != resultxml2.getResultLine().size()) {
            }
            str14 = "";
            i4 = i9;
            String Print = printProcess.Print(i5, str3, str13, str14, str15);
            z3 = true;
            if (i3 == 1) {
                strArr[i8] = StringUtils.convTrCharEN(Print);
            } else {
                strArr[i8] = Print;
            }
            i8++;
            i6++;
            str12 = "";
            d5 = 0.0d;
            z4 = false;
            str11 = str3;
            i7 = i4;
            d2 = 0.0d;
        }
        return strArr;
    }
    public static String[] PrintCrediCardFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i4;
        boolean z3;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        if (resultxml.getResultLine().size() == 0) {
            strArr[0] = "Fis Baslik Bulunamadi";
            return strArr;
        }
        if (resultxml2.getResultLine().size() == 0) {
            strArr[0] = "Fis Detay Bulunamadi";
            return strArr;
        }
        RESULTLINE resultline = resultxml.getResultLine().get(0);
        String str7 = getXmlAttribute(resultline.FICHENO) + "\n\t";
        if (resultline.DATE_ != null) {
            str2 = str7 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
        } else {
            str2 = str7 + "\n\t";
        }
        String str8 = ((((((((((((((((((((((((((((((str2 + getXmlAttribute(DateAndTimeUtils.intToNowTime(Integer.parseInt(resultline.TIME))) + "\n\t") + getXmlAttribute(resultline.SPECODE) + "\n\t") + getXmlAttribute(resultline.CYPHCODE) + "\n\t") + getXmlAttribute(resultline.GENEXP1) + "\n\t") + getXmlAttribute(resultline.GENEXP2) + "\n\t") + getXmlAttribute(resultline.GENEXP3) + "\n\t") + getXmlAttribute(resultline.GENEXP4) + "\n\t") + getXmlAttribute(resultline.TRADINGGRP) + "\n\t") + getXmlAttribute(resultline.BCODE) + "\n\t") + getXmlAttribute(resultline.BDEFINITION) + "\n\t") + getXmlAttribute(resultline.BACODE) + "\n\t") + getXmlAttribute(resultline.BADEFINITION) + "\n\t") + getXmlAttribute(resultline.CCODE) + "\n\t") + getXmlAttribute(resultline.CDEFINITION) + "\n\t") + getXmlAttribute(resultline.CSPECODE) + "\n\t") + getXmlAttribute(resultline.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline.ADDR1) + "\n\t") + getXmlAttribute(resultline.ADDR2) + "\n\t") + getXmlAttribute(resultline.CITY) + "\n\t") + getXmlAttribute(resultline.TOWN) + "\n\t") + getXmlAttribute(resultline.TELNRS1) + "\n\t") + getXmlAttribute(resultline.TELNRS2) + "\n\t") + getXmlAttribute(resultline.FAXNR) + "\n\t") + getXmlAttribute(resultline.TAXNR) + "\n\t") + getXmlAttribute(resultline.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline.INCHARGE) + "\n\t") + getXmlAttribute(resultline.DISCRATE) + "\n\t") + getXmlAttribute(resultline.EMAILADDR) + "\n\t") + getXmlAttribute(resultline.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CREDIT))) + "\n\t";
        double d2 = StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE));
        if (d2 < 0.0d) {
            str3 = (str8 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t") + "(A)\n\t";
        } else {
            str3 = (str8 + StringUtils.dFormat(d2) + "\n\t") + "(B)\n\t";
        }
        String str9 = ((str3 + DateAndTimeUtils.intToNowTimeLogo(StringUtils.convertStringToInt(getXmlAttribute(resultline.TIME))) + "\n\t") + getXmlAttribute(resultline.DISTRICT) + "\n\t") + getXmlAttribute(resultline.TAXOFFCODE) + "\n\t";
        double generalTotalCreditCard = getGeneralTotalCreditCard(resultxml2);
        String str10 = (str9 + StringUtils.dFormat(generalTotalCreditCard) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(generalTotalCreditCard), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
        Math.abs(StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE)) + generalTotalCreditCard);
        double d3 = StringUtils.toDouble(getXmlAttribute(resultline.BEFOREBALANCE));
        if (d3 < 0.0d) {
            str4 = str10 + StringUtils.dFormat(d3 * (-1.0d)) + " (A)\n\t";
        } else {
            str4 = str10 + StringUtils.dFormat(d3) + " (B)\n\t";
        }
        String str11 = str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CARISONBAKIYE))) + "\n\t";
        String xmlAttribute = getXmlAttribute(resultline.SCODE);
        String str12 = ((((str11 + xmlAttribute + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t") + getXmlAttribute(resultline.TCKNO) + "\n\t") + "\n\t") + xmlAttribute + "\n\t";
        int i5 = i2 <= 0 ? 1 : i2;
        strArr = new String[(resultxml2.getResultLine().size() / i5) + 1];
        String str13 = "";
        boolean z4 = true;
        int i6 = 1;
        double d4 = 0.0d;
        double d5 = 0.0d;
        int i7 = 0;
        int i8 = 0;
        while (i7 < resultxml2.getResultLine().size()) {
            String str14 = ((((str13 + getXmlAttribute(resultxml2.getResultLine().get(i7).SPECODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultxml2.getResultLine().get(i7).AMOUNT))) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).PCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).PDEFINITION) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).DOCODE) + '\r';
            d4 += StringUtils.toDouble(resultxml2.getResultLine().get(i7).AMOUNT);
            int i9 = i7 + 1;
            if (i9 != resultxml2.getResultLine().size() && i9 != i5 * i6) {
                str5 = str12;
                i4 = i9;
                z3 = true;
                str13 = str14;
                str12 = str5;
                i7 = i4;
            }
            if (d4 < 0.0d) {
                d4 = 0.0d;
            }
            d5 += d4;
            String str15 = str12;
            String sb = StringUtils.dFormat(d4) +
                    "\n\t";
            String str16 = (((sb + StringUtils.convertTotal2Text(StringUtils.dFormat(d4), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d5) + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t";
            String str17 = (((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t";
            str5 = (!z || z4) ? str15 : "";
            if (i9 == resultxml2.getResultLine().size() && !z2) {
                str6 = str16;
                i4 = i9;
                String Print = printProcess.Print(i5, str5, str14, str6, str17);
                z3 = true;
                if (i3 != 1) {
                    strArr[i8] = StringUtils.convTrCharEN(Print);
                } else {
                    strArr[i8] = Print;
                }
                i8++;
                i6++;
                str13 = "";
                d4 = 0.0d;
                z4 = false;
                str12 = str5;
                i7 = i4;
            }
            str6 = "";
            i4 = i9;
            String Print2 = printProcess.Print(i5, str5, str14, str6, str17);
            z3 = true;
            if (i3 != 1) {
                return strArr;
            }
            i8++;
            i6++;
            str13 = "";
            d4 = 0.0d;
            z4 = false;
            str12 = str5;
            i7 = i4;
        }
        return strArr;
    }
    private static double getGeneralTotalCreditCard(RESULTXML resultxml) {
        double d2 = 0.0d;
        try {
            Iterator<RESULTLINE> it = resultxml.getResultLine().iterator();
            while (it.hasNext()) {
                d2 += StringUtils.toDouble(it.next().AMOUNT);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return d2;
    }
    public static String[] PrintCheequeFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        int i4;
        boolean z3;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        char c2 = 0;
        try {
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        if (resultxml.getResultLine().size() == 0) {
            strArr[0] = "Fis Baslik Bulunamadi";
            return strArr;
        }
        if (resultxml2.getResultLine().size() == 0) {
            strArr[0] = "Fis Detay Bulunamadi";
            return strArr;
        }
        RESULTLINE resultline = resultxml.getResultLine().get(0);
        String str8 = getXmlAttribute(resultline.FICHENO) + "\n\t";
        if (resultline.DATE_ != null) {
            str2 = str8 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
        } else {
            str2 = str8 + "\n\t";
        }
        String str9 = ((((((((((((((((((((((((str2 + getXmlAttribute(resultline.SPECODE) + "\n\t") + getXmlAttribute(resultline.CYPHCODE) + "\n\t") + getXmlAttribute(resultline.GENEXP1) + "\n\t") + getXmlAttribute(resultline.GENEXP2) + "\n\t") + getXmlAttribute(resultline.GENEXP3) + "\n\t") + getXmlAttribute(resultline.GENEXP4) + "\n\t") + getXmlAttribute(resultline.CCODE) + "\n\t") + getXmlAttribute(resultline.CDEFINITION) + "\n\t") + getXmlAttribute(resultline.CSPECODE) + "\n\t") + getXmlAttribute(resultline.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline.ADDR1) + "\n\t") + getXmlAttribute(resultline.ADDR2) + "\n\t") + getXmlAttribute(resultline.CITY) + "\n\t") + getXmlAttribute(resultline.TOWN) + "\n\t") + getXmlAttribute(resultline.TELNRS1) + "\n\t") + getXmlAttribute(resultline.TELNRS2) + "\n\t") + getXmlAttribute(resultline.FAXNR) + "\n\t") + getXmlAttribute(resultline.TAXNR) + "\n\t") + getXmlAttribute(resultline.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline.INCHARGE) + "\n\t") + getXmlAttribute(resultline.DISCRATE) + "\n\t") + getXmlAttribute(resultline.EMAILADDR) + "\n\t") + getXmlAttribute(resultline.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CREDIT))) + "\n\t";
        double d2 = StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE));
        if (d2 < 0.0d) {
            str3 = (str9 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t") + "(A)\n\t";
        } else {
            str3 = (str9 + StringUtils.dFormat(d2) + "\n\t") + "(B)\n\t";
        }
        String str10 = ((((str3 + DateAndTimeUtils.nowTime2() + "\n\t") + getXmlAttribute(resultline.DISTRICT) + "\n\t") + getXmlAttribute(resultline.TAXOFFCODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.TOTAL))) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(getXmlAttribute(resultline.TOTAL)), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
        Math.abs(StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE)) + StringUtils.toDouble(getXmlAttribute(resultline.TOTAL)));
        double d3 = StringUtils.toDouble(getXmlAttribute(resultline.BEFOREBALANCE));
        if (d3 < 0.0d) {
            str4 = str10 + StringUtils.dFormat(d3 * (-1.0d)) + " (A)\n\t";
        } else {
            str4 = str10 + StringUtils.dFormat(d3) + " (B)\n\t";
        }
        String str11 = (((((str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t") + getXmlAttribute(resultline.TCKNO) + "\n\t") + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t";
        int i5 = i2 <= 0 ? 1 : i2;
        strArr = new String[(resultxml2.getResultLine().size() / i5) + 1];
        String str12 = "";
        boolean z4 = true;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        double d4 = 0.0d;
        double d5 = 0.0d;
        while (i7 < resultxml2.getResultLine().size()) {
            String str13 = str12 + getXmlAttribute(resultxml2.getResultLine().get(i7).PORTFOYNO) + "\n\t";
            if (resultxml2.getResultLine().get(i7).DUEDATE != null) {
                str5 = str13 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultxml2.getResultLine().get(i7).DUEDATE.split(ExifInterface.GPS_DIRECTION_TRUE)[c2])) + "\n\t";
            } else {
                str5 = str13 + "\n\t";
            }
            String str14 = (((((((((str5 + getXmlAttribute(resultxml2.getResultLine().get(i7).SPECODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).CYPHCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).MUHABIR) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).CITY) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).NEWSERINO) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).OWING) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultxml2.getResultLine().get(i7).AMOUNT))) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).BANKNAME) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).BNBRANCHNO) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).BNACCOUNTNO) + '\r';
            d4 += StringUtils.toDouble(resultxml2.getResultLine().get(i7).AMOUNT);
            int i9 = i7 + 1;
            if (i9 != resultxml2.getResultLine().size() && i9 != i5 * i6) {
                str6 = str11;
                i4 = i9;
                z3 = true;
                str12 = str14;
                str11 = str6;
                i7 = i4;
                c2 = 0;
            }
            if (d4 < 0.0d) {
                d4 = 0.0d;
            }
            d5 += d4;
            String str15 = str11;
            String sb = StringUtils.dFormat(d4) +
                    "\n\t";
            String str16 = (((sb + StringUtils.convertTotal2Text(StringUtils.dFormat(d4), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d5) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t";
            String str17 = (((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t";
            str6 = (!z || z4) ? str15 : "";
            if (i9 == resultxml2.getResultLine().size() && !z2) {
                str7 = str16;
                i4 = i9;
                String Print = printProcess.Print(i5, str6, str14, str7, str17);
                z3 = true;
                if (i3 != 1) {
                    strArr[i8] = StringUtils.convTrCharEN(Print);
                } else {
                    strArr[i8] = Print;
                }
                i8++;
                i6++;
                str12 = "";
                d4 = 0.0d;
                z4 = false;
                str11 = str6;
                i7 = i4;
                c2 = 0;
            }
            str7 = "";
            i4 = i9;
            String Print2 = printProcess.Print(i5, str6, str14, str7, str17);
            z3 = true;
            if (i3 != 1) {
            }
            i8++;
            i6++;
            str12 = "";
            d4 = 0.0d;
            z4 = false;
            str11 = str6;
            i7 = i4;
            c2 = 0;
        }
        return strArr;
    }
    public static String[] PrintDeedFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        int i4;
        boolean z3;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        char c2 = 0;
        try {
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        if (resultxml.getResultLine().size() == 0) {
            strArr[0] = "Fi? Baslik Bulunamad?";
            return strArr;
        }
        if (resultxml2.getResultLine().size() == 0) {
            strArr[0] = "Fi? Detay Bulunamad?";
            return strArr;
        }
        RESULTLINE resultline = resultxml.getResultLine().get(0);
        String str8 = getXmlAttribute(resultline.ROLLNO) + "\n\t";
        if (resultline.DATE_ != null) {
            str2 = str8 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
        } else {
            str2 = str8 + "\n\t";
        }
        String str9 = ((((((((((((((((((((((((str2 + getXmlAttribute(resultline.SPECODE) + "\n\t") + getXmlAttribute(resultline.CYPHCODE) + "\n\t") + getXmlAttribute(resultline.GENEXP1) + "\n\t") + getXmlAttribute(resultline.GENEXP2) + "\n\t") + getXmlAttribute(resultline.GENEXP3) + "\n\t") + getXmlAttribute(resultline.GENEXP4) + "\n\t") + getXmlAttribute(resultline.CCODE) + "\n\t") + getXmlAttribute(resultline.CDEFINITION) + "\n\t") + getXmlAttribute(resultline.CSPECODE) + "\n\t") + getXmlAttribute(resultline.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline.ADDR1) + "\n\t") + getXmlAttribute(resultline.ADDR2) + "\n\t") + getXmlAttribute(resultline.CITY) + "\n\t") + getXmlAttribute(resultline.TOWN) + "\n\t") + getXmlAttribute(resultline.TELNRS1) + "\n\t") + getXmlAttribute(resultline.TELNRS2) + "\n\t") + getXmlAttribute(resultline.FAXNR) + "\n\t") + getXmlAttribute(resultline.TAXNR) + "\n\t") + getXmlAttribute(resultline.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline.INCHARGE) + "\n\t") + getXmlAttribute(resultline.DISCRATE) + "\n\t") + getXmlAttribute(resultline.EMAILADDR) + "\n\t") + getXmlAttribute(resultline.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CREDIT))) + "\n\t";
        double d2 = StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE));
        if (d2 < 0.0d) {
            str3 = (str9 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t") + "(A)\n\t";
        } else {
            str3 = (str9 + StringUtils.dFormat(d2) + "\n\t") + "(B)\n\t";
        }
        String str10 = ((((str3 + DateAndTimeUtils.nowTime2() + "\n\t") + getXmlAttribute(resultline.DISTRICT) + "\n\t") + getXmlAttribute(resultline.TAXOFFCODE) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.TOTAL))) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(getXmlAttribute(resultline.TOTAL)), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
        Math.abs(StringUtils.toDouble(getXmlAttribute(resultline.BAKIYE)) + StringUtils.toDouble(getXmlAttribute(resultline.TOTAL)));
        double d3 = StringUtils.toDouble(getXmlAttribute(resultline.BEFOREBALANCE));
        if (d3 < 0.0d) {
            str4 = str10 + StringUtils.dFormat(d3 * (-1.0d)) + " (A)\n\t";
        } else {
            str4 = str10 + StringUtils.dFormat(d3) + " (B)\n\t";
        }
        String str11 = (((((str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t") + getXmlAttribute(resultline.SDEFINITION) + "\n\t") + getXmlAttribute(resultline.TCKNO) + "\n\t") + "\n\t") + getXmlAttribute(resultline.SCODE) + "\n\t";
        int i5 = i2 <= 0 ? 1 : i2;
        strArr = new String[(resultxml2.getResultLine().size() / i5) + 1];
        String str12 = "";
        boolean z4 = true;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        double d4 = 0.0d;
        double d5 = 0.0d;
        while (i7 < resultxml2.getResultLine().size()) {
            String str13 = str12 + getXmlAttribute(resultxml2.getResultLine().get(i7).PORTFOYNO) + "\n\t";
            if (resultxml2.getResultLine().get(i7).DUEDATE != null) {
                str5 = str13 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultxml2.getResultLine().get(i7).DUEDATE.split(ExifInterface.GPS_DIRECTION_TRUE)[c2])) + "\n\t";
            } else {
                str5 = str13 + "\n\t";
            }
            String str14 = (((((((((str5 + getXmlAttribute(resultxml2.getResultLine().get(i7).SPECODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).CYPHCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).MUHABIR) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).CITY) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).NEWSERINO) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).OWING) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultxml2.getResultLine().get(i7).AMOUNT))) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SETDATE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).KEFIL) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).STAMP) + '\r';
            d4 += StringUtils.toDouble(resultxml2.getResultLine().get(i7).AMOUNT);
            int i9 = i7 + 1;
            if (i9 != resultxml2.getResultLine().size() && i9 != i5 * i6) {
                str6 = str11;
                i4 = i9;
                z3 = true;
                str12 = str14;
                str11 = str6;
                i7 = i4;
                c2 = 0;
            }
            if (d4 < 0.0d) {
                d4 = 0.0d;
            }
            d5 += d4;
            String str15 = str11;
            String sb = StringUtils.dFormat(d4) +
                    "\n\t";
            String str16 = (((sb + StringUtils.convertTotal2Text(StringUtils.dFormat(d4), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d5) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t";
            String str17 = (((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SCODE) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).SDEFINITION) + "\n\t";
            str6 = (!z || z4) ? str15 : "";
            if (i9 == resultxml2.getResultLine().size() && !z2) {
                str7 = str16;
                i4 = i9;
                String Print = printProcess.Print(i5, str6, str14, str7, str17);
                z3 = true;
                if (i3 != 1) {
                    strArr[i8] = StringUtils.convTrCharEN(Print);
                } else {
                    strArr[i8] = Print;
                }
                i8++;
                i6++;
                str12 = "";
                d4 = 0.0d;
                z4 = false;
                str11 = str6;
                i7 = i4;
                c2 = 0;
            }
            str7 = "";
            i4 = i9;
            String Print2 = printProcess.Print(i5, str6, str14, str7, str17);
            z3 = true;
            if (i3 != 1) {
            }
            i8++;
            i6++;
            str12 = "";
            d4 = 0.0d;
            z4 = false;
            str11 = str6;
            i7 = i4;
            c2 = 0;
        }
        return strArr;
    }
    public static String[] PrintCaseFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml, RESULTXML resultxml2) {
        String str2;
        RESULTLINE resultline;
        String str3;
        String str4;
        String str5;
        int i4;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
        } catch (Exception e2) {
            strArr[0] = e2.getMessage();
        }
        if (resultxml.getResultLine().size() == 0) {
            strArr[0] = "Fis Bulunamadi";
            return strArr;
        }
        RESULTLINE resultline2 = resultxml.getResultLine().get(0);
        String str6 = getXmlAttribute(resultline2.FICHENO) + "\n\t";
        if (resultline2.DATE_ != null) {
            str2 = str6 + DateAndTimeUtils.convertDateY(getXmlAttribute(resultline2.DATE_.split(ExifInterface.GPS_DIRECTION_TRUE)[0])) + "\n\t";
        } else {
            str2 = str6 + "\n\t";
        }
        String str7 = ((str2 + getXmlAttribute(resultline2.SPECODE) + "\n\t") + getXmlAttribute(resultline2.CYPHCODE) + "\n\t") + getXmlAttribute(resultline2.LINEEXP) + "\n\t";
        if (resultxml2.getResultLine().size() > 0) {
            resultline = resultxml2.getResultLine().get(0);
        } else {
            resultline = new RESULTLINE();
        }
        String str8 = ((((((((((((((((((str7 + getXmlAttribute(resultline.CCODE) + "\n\t") + getXmlAttribute(resultline.CDEFINITION) + "\n\t") + getXmlAttribute(resultline.CSPECODE) + "\n\t") + getXmlAttribute(resultline.CCYPHCODE) + "\n\t") + getXmlAttribute(resultline.ADDR1) + "\n\t") + getXmlAttribute(resultline.ADDR2) + "\n\t") + getXmlAttribute(resultline.CITY) + "\n\t") + getXmlAttribute(resultline.TOWN) + "\n\t") + getXmlAttribute(resultline.TELNRS1) + "\n\t") + getXmlAttribute(resultline.TELNRS2) + "\n\t") + getXmlAttribute(resultline.FAXNR) + "\n\t") + getXmlAttribute(resultline.TAXNR) + "\n\t") + getXmlAttribute(resultline.TAXOFFICE) + "\n\t") + getXmlAttribute(resultline.INCHARGE) + "\n\t") + getXmlAttribute(resultline.DISCRATE) + "\n\t") + getXmlAttribute(resultline.EMAILADDR) + "\n\t") + getXmlAttribute(resultline.CTRADINGGRP) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.DEBIT))) + "\n\t") + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CREDIT))) + "\n\t";
        double d2 = StringUtils.toDouble(getXmlAttribute(resultline.DEBITACCOUNT));
        double d3 = 0.0d;
        if (d2 < 0.0d) {
            str3 = (str8 + StringUtils.dFormat(d2 * (-1.0d)) + "\n\t") + "(A)\n\t";
        } else {
            str3 = (str8 + StringUtils.dFormat(d2) + "\n\t") + "(B)\n\t";
        }
        String str9 = ((str3 + DateAndTimeUtils.nowTime2() + "\n\t") + getXmlAttribute(resultline.DISTRICT) + "\n\t") + getXmlAttribute(resultline.TAXOFFCODE) + "\n\t";
        Integer.parseInt(getXmlAttribute(resultline.CLIENTREF));
        String str10 = (str9 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.AMOUNT))) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(getXmlAttribute(resultline.AMOUNT)), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t";
        Math.abs(StringUtils.toDouble(getXmlAttribute(resultline.DEBITACCOUNT)) + StringUtils.toDouble(getXmlAttribute(resultline.AMOUNT)));
        double d4 = StringUtils.toDouble(getXmlAttribute(resultline.BEFOREBALANCE));
        if (d4 < 0.0d) {
            str4 = str10 + StringUtils.dFormat(d4 * (-1.0d)) + " (A)\n\t";
        } else {
            str4 = str10 + StringUtils.dFormat(d4) + " (B)\n\t";
        }
        String str11 = (((((str4 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultline.CARISONBAKIYE))) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t") + getXmlAttribute(resultline.TCKNO) + "\n\t") + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t";
        int i5 = i2 <= 0 ? 1 : i2;
        strArr = new String[(resultxml2.getResultLine().size() / i5) + 1];
        String str12 = "";
        boolean z3 = true;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        double d5 = 0.0d;
        double d6 = 0.0d;
        while (i7 < resultxml2.getResultLine().size()) {
            String str13 = (str12 + StringUtils.dFormat(StringUtils.toDouble(getXmlAttribute(resultxml2.getResultLine().get(i7).AMOUNT))) + "\n\t") + getXmlAttribute(resultxml2.getResultLine().get(i7).DOCODE) + '\r';
            d5 += StringUtils.toDouble(resultxml2.getResultLine().get(i7).AMOUNT);
            int i9 = i7 + 1;
            if (i9 != resultxml2.getResultLine().size() && i9 != i5 * i6) {
                i4 = i9;
                str12 = str13;
                i7 = i4;
                d3 = 0.0d;
            }
            if (d5 < d3) {
                d5 = d3;
            }
            d6 += d5;
            String str14 = ((((StringUtils.dFormat(d5) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d5), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + StringUtils.dFormat(d6) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t";
            String str15 = (((StringUtils.dFormat(d6) + "\n\t") + StringUtils.convertTotal2Text(StringUtils.dFormat(d6), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + "\n\t") + getXmlAttribute(resultline2.SCODE) + "\n\t") + getXmlAttribute(resultline2.SDEFINITION) + "\n\t";
            String str16 = (!z || z3) ? str11 : "";
            if (i9 == resultxml2.getResultLine().size() && !z2) {
                str5 = str14;
                i4 = i9;
                String Print = printProcess.Print(i5, str16, str13, str5, str15);
                if (i3 != 1) {
                    strArr[i8] = StringUtils.convTrCharEN(Print);
                } else {
                    strArr[i8] = Print;
                }
                i8++;
                i6++;
                str12 = "";
                str11 = str16;
                d5 = 0.0d;
                z3 = false;
                i7 = i4;
                d3 = 0.0d;
            }
            str5 = "";
            i4 = i9;
            String Print2 = printProcess.Print(i5, str16, str13, str5, str15);
            if (i3 != 1) {
                return strArr;
            }
            i8++;
            i6++;
            str12 = "";
            str11 = str16;
            d5 = 0.0d;
            z3 = false;
            i7 = i4;
            d3 = 0.0d;
        }
        return strArr;
    }
    public static String[] PrintDeliveryNoteFiche(int i2, boolean z, boolean z2, String str, int i3, RESULTXML resultxml) {
        String str2;
        String str3;
        String[] strArr = new String[1];
        PrintProcess printProcess = new PrintProcess();
        if (resultxml == null) {
            return strArr;
        }
        printProcess.SprintIniFileName = str;
        try {
            String str4 = (DateAndTimeUtils.getNowDateTime() + "\n\t") + ErpCreator.getInstance().getmBaseErp().getUser().getCode() + "\n\t";
            int i4 = i2 <= 0 ? 1 : i2;
            strArr = new String[(resultxml.getResultLine().size() / i4) + 1];
            String str5 = "";
            String str6 = str5;
            int i5 = 1;
            boolean z3 = true;
            int i6 = 0;
            double d2 = 0.0d;
            double d3 = 0.0d;
            int i7 = 0;
            while (i6 < resultxml.getResultLine().size()) {
                RESULTLINE resultline = resultxml.getResultLine().get(i6);
                String str7 = (str5 + getXmlAttribute(resultline.CODE) + "\n\t") + getXmlAttribute(resultline.NAME) + "\n\t";
                double convertStringToDouble = StringUtils.convertStringToDouble(getXmlAttribute(resultline.ONHAND));
                String str8 = str7 + convertStringToDouble + '\r';
                d2 += convertStringToDouble;
                int i8 = i6 + 1;
                if (i8 != resultxml.getResultLine().size() && i8 != i4 * i5) {
                    str5 = str8;
                    i6 = i8;
                }
                d3 += d2;
                String str9 = StringUtils.dFormat(d2) + "\n\t";
                String str10 = (str6 + "\n\t") + StringUtils.dFormat(d3) + "\n\t";
                String str11 = (!z || z3) ? str4 : "";
                if (i8 == resultxml.getResultLine().size()) {
                    str3 = z2 ? "" : str9;
                    str2 = str10;
                } else {
                    str2 = "";
                    str3 = str9;
                }
                String Print = printProcess.Print(i4, str11, str8, str3, str2);
                if (i3 == 1) {
                    strArr[i7] = StringUtils.convTrCharEN(Print);
                } else {
                    strArr[i7] = Print;
                }
                i7++;
                i5++;
                str5 = "";
                str4 = str11;
                str6 = str2;
                d2 = 0.0d;
                z3 = false;
                i6 = i8;
            }
        } catch (Exception e2) {
            Log.d(TAG, "PrintDeliveryNoteFiche: " + e2.getMessage());
        }
        return strArr;
    }
    private static String getXmlAttribute(String str) {
        return str == null ? "" : str;
    }
    private static List<RESULTLINE> getOrderGeneralDiscount(List<RESULTLINE> list) {
        ArrayList arrayList = new ArrayList();
        for (RESULTLINE resultline : list) {
            if (resultline.GLOBTRANS.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                RESULTLINE resultline2 = new RESULTLINE();
                resultline2.LOGICALREF = resultline.LOGICALREF;
                resultline2.DISCPER = resultline.DISCPER;
                resultline2.LINETYPE = resultline.LINETYPE;
                resultline2.TOTAL = resultline.TOTAL;
                arrayList.add(resultline2);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (Integer.parseInt(((RESULTLINE) arrayList.get(i2)).LINETYPE) != 2) {
                arrayList.remove(i2);
                i2--;
            }
        }
        return arrayList;
    }
    private static List<RESULTLINE> getOrderLineDiscount(List<RESULTLINE> list, int i2) {
        ArrayList arrayList = new ArrayList();
        for (RESULTLINE resultline : list) {
            if (resultline.GLOBTRANS.equals("0")) {
                RESULTLINE resultline2 = new RESULTLINE();
                resultline2.LOGICALREF = resultline.LOGICALREF;
                resultline2.DISCPER = resultline.DISCPER;
                resultline2.LINETYPE = resultline.LINETYPE;
                resultline2.TOTAL = resultline.TOTAL;
                resultline2.LINENO_ = resultline.LINENO_;
                arrayList.add(resultline2);
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int parseInt = Integer.parseInt(((RESULTLINE) arrayList.get(i3)).LINENO_);
            int parseInt2 = Integer.parseInt(((RESULTLINE) arrayList.get(i3)).LINETYPE);
            if (parseInt != i2) {
                arrayList.remove(i3);
            } else if (parseInt2 == 2) {
                i2++;
            } else {
                arrayList.remove(i3);
            }
            i3--;
        }
        return arrayList;
    }
    private static List<RESULTLINE> getGeneralDiscount(List<RESULTLINE> list) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (RESULTLINE resultline : list) {
            if (resultline.GLOBTRANS.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                RESULTLINE resultline2 = new RESULTLINE();
                resultline2.LOGICALREF = resultline.LOGICALREF;
                resultline2.DISCPER = resultline.DISCPER;
                resultline2.STFICHELNNO = resultline.STFICHELNNO;
                resultline2.LINETYPE = resultline.LINETYPE;
                resultline2.TOTAL = resultline.TOTAL;
                resultline2.LINENO_ = resultline.LINENO_;
                arrayList.add(resultline2);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (Integer.parseInt(((RESULTLINE) arrayList.get(i2)).LINETYPE) != 2) {
                arrayList.remove(i2);
                i2--;
            }
        }
        return arrayList;
    }
    private static List<RESULTLINE> getLineDiscount(List<RESULTLINE> list, int i2) {
        ArrayList arrayList = new ArrayList();
        for (RESULTLINE resultline : list) {
            if (resultline.GLOBTRANS.equals("0")) {
                RESULTLINE resultline2 = new RESULTLINE();
                resultline2.LOGICALREF = resultline.LOGICALREF;
                resultline2.DISCPER = resultline.DISCPER;
                resultline2.STFICHELNNO = resultline.STFICHELNNO;
                resultline2.LINETYPE = resultline.LINETYPE;
                resultline2.TOTAL = resultline.TOTAL;
                arrayList.add(resultline2);
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int parseInt = Integer.parseInt(((RESULTLINE) arrayList.get(i3)).STFICHELNNO);
            int parseInt2 = Integer.parseInt(((RESULTLINE) arrayList.get(i3)).LINETYPE);
            if (parseInt != i2) {
                arrayList.remove(i3);
            } else if (parseInt2 == 2) {
                i2++;
            } else {
                arrayList.remove(i3);
            }
        }
        return arrayList;
    }
    private static String checkIsDivUnitForAmountPrintValueOnline(RESULTLINE resultline, double d2, String str) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Units.class, "LOGICALREF=?", new String[]{String.valueOf(resultline.UOMREF)});
        if (table == null || table.isEmpty()) {
            return str;
        }
        if (((Units) table.get(0)).getDivUnit() != 1) {
            return StringUtils.convertIntToString(StringUtils.convertDoubleToInteger(d2)) + str;
        }
        return StringUtils.dFormat(d2) + str;
    }
    private static String checkIsDivUnitForAmountPrintValueOffline(Cursor cursor, double d2, String str) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Units.class, "LOGICALREF=?", new String[]{String.valueOf(((Integer) ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().dbVal(cursor, "UNIT", ColType.sayi)).intValue())});
        if (table == null || table.isEmpty()) {
            return str;
        }
        if (((Units) table.get(0)).getDivUnit() != 1) {
            return StringUtils.convertIntToString(StringUtils.convertDoubleToInteger(d2)) + str;
        }
        return StringUtils.dFormat(d2) + str;
    }
}
