package com.proje.mobilesales.core.printutil;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintExtraInfo;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipValues;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipHeader;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipLine;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipSerialLine;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;

public class NPrintSlipFiche {
    private static final String Line = "\n\t";
    private static final String TAG = "NPrintSlipFiche";
    public static String[] PrintSlipFiche(int i2, boolean z, boolean z2, String str, int i3, PrintSlipModel printSlipModel) {
        String printInvoiceLine;
        double doubleValue;
        double doubleValue2;
        int i4 = i2;
        PrintSlipValues printSlipValues = new PrintSlipValues(z, z2, i3, i4);
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        try {
            if (printSlipModel.getItemSlip().getSlipHeader().getSlipType() == NetsisSlipType.ftLokalDepo) {
                printSlipValues.setHeaderStr(printWhTransferHeader(printSlipModel));
            } else {
                printSlipValues.setHeaderStr(printInvoiceHeader(printSlipModel));
            }
            int i5 = 1;
            if (i4 <= 0) {
                i4 = 1;
            }
            ItemSlip itemSlip = printSlipModel.getItemSlip();
            printSlipValues.setLineSize(getLineSize(itemSlip));
            printSlipValues.setReturnValues(new String[(printSlipValues.getLineSize() / i4) + 1]);
            printSlipValues.setDetailStr("");
            int i6 = 0;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (i6 < itemSlip.getLines().size()) {
                ItemSlipLine itemSlipLine = itemSlip.getLines().get(i6);
                if (printSlipModel.getItemSlip().getSlipType() == NetsisSlipType.ftLokalDepo) {
                    printInvoiceLine = printWhTransferLine(itemSlipLine, printSlipValues, printSlipModel);
                } else {
                    printInvoiceLine = printInvoiceLine(itemSlipLine, printSlipValues, printSlipModel);
                }
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + printInvoiceLine);
                printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + i5);
                double sTraNF = itemSlipLine.getSTraNF() * itemSlipLine.getSTraGCMIK() * (itemSlipLine.getSTraKDV() / 100.0d);
                double sTraKDV = itemSlipLine.getSTraKDV();
                if (itemSlipLine.getSTraFATIRSNO() != null) {
                    double d4 = getWeight(itemSlipLine.getSTraFATIRSNO(), itemSlipLine.getSira(), itemSlip.getSlipType()).netWeight;
                    doubleValue2 = getWeight(itemSlipLine.getSTraFATIRSNO(), itemSlipLine.getSira(), itemSlip.getSlipType()).netVolume;
                    doubleValue = d4;
                } else {
                    doubleValue = itemSlipLine.getWeight().doubleValue();
                    doubleValue2 = itemSlipLine.getVolume().doubleValue();
                }
                d3 += doubleValue * itemSlipLine.getSTraGCMIK();
                d2 += doubleValue2 * itemSlipLine.getSTraGCMIK();
                int i7 = 0;
                while (true) {
                    if (i7 >= printSlipValues.getVats().length) {
                        break;
                    }
                    if (printSlipValues.getVats()[i7] == sTraKDV) {
                        printSlipValues.getVatsamount()[i7] = printSlipValues.getVatsamount()[i7] + sTraNF;
                        double[] totvatsamount = printSlipValues.getTotvatsamount();
                        totvatsamount[i7] = totvatsamount[i7] + sTraNF;
                        break;
                    }
                    if (printSlipValues.getVats()[i7] == 0.0d) {
                        printSlipValues.getVats()[i7] = sTraKDV;
                        printSlipValues.getVatsamount()[i7] = sTraNF;
                        printSlipValues.getTotvats()[i7] = sTraKDV;
                        printSlipValues.getTotvatsamount()[i7] = sTraNF;
                        break;
                    }
                    i7++;
                }
                printSlipValues.setWeight(d3);
                printSlipValues.setGrossWeight(d3);
                printSlipValues.setNetVolume(d2);
                printSlipValues.setPageTotal(printSlipValues.getPageTotal() + (itemSlipLine.getSTraNF() * itemSlipLine.getSTraGCMIK()));
                checkSummaryAndFooter(printSlipValues, printSlipModel, printProcess);
                printInvoiceLineSerials(itemSlipLine, printSlipValues, printSlipModel, printProcess);
                i6++;
                i5 = 1;
            }
        } catch (Exception e2) {
            Log.d(TAG, "PrintInvoiceFiche: ", e2);
            printSlipValues.getReturnValues()[0] = e2.getMessage();
        }
        return printSlipValues.getReturnValues();
    }
    @SuppressLint({HttpHeaders.RANGE})
    public static ItemUnit getWeight(String str, int i2, NetsisSlipType netsisSlipType) {
        String stringResource = netsisSlipType == NetsisSlipType.ftSSip ? ContextUtils.getStringResource(R.string.net_order_detail_item_weight) : ContextUtils.getStringResource(R.string.net_invoice_detail_item_weight);
        ItemUnit itemUnit = new ItemUnit();
        try {
            Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(stringResource, str, String.valueOf(i2));
            if (query != null && query.getCount() > 0 && query.moveToFirst()) {
                itemUnit.netWeight = query.getDouble(query.getColumnIndex("WEIGHT"));
                itemUnit.width = query.getString(query.getColumnIndex("WIDTH"));
                itemUnit.length = query.getString(query.getColumnIndex("LENGTH"));
                itemUnit.height = query.getString(query.getColumnIndex("HEIGHT"));
                itemUnit.netVolume = query.getDouble(query.getColumnIndex("VOLUME"));
            }
        } catch (Exception e2) {
            Log.e("AA", "getLocalInvoiceForPrint: ", e2);
        }
        return itemUnit;
    }
    static void checkSummaryAndFooter(PrintSlipValues printSlipValues, PrintSlipModel printSlipModel, PrintProcess printProcess) {
        String printInvoiceFooter;
        if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize() || printSlipValues.getPrintLineCount() == printSlipValues.getDetailRowCount() * printSlipValues.getSayi()) {
            if (printSlipValues.getPageTotal() < 0.0d) {
                printSlipValues.setPageTotal(0.0d);
            }
            printSlipValues.setNakliyeYekun(printSlipValues.getNakliyeYekun() + printSlipValues.getPageTotal());
            printSlipValues.setSummaryStr("");
            NetsisSlipType slipType = printSlipModel.getItemSlip().getSlipType();
            NetsisSlipType netsisSlipType = NetsisSlipType.ftLokalDepo;
            if (slipType == netsisSlipType) {
                printWhTransferSummary(printSlipValues, printSlipModel);
            } else {
                printInvoiceSummary(printSlipValues, printSlipModel);
            }
            printSlipValues.setAmountTotal(printSlipValues.getPageAmountTotal() + printSlipValues.getAmountTotal());
            if (printSlipModel.getItemSlip().getSlipType() == netsisSlipType) {
                printInvoiceFooter = printWhTransferFooter(printSlipModel.getItemSlip().getSlipHeader(), printSlipValues);
            } else {
                printInvoiceFooter = printInvoiceFooter(printSlipModel.getItemSlip().getSlipHeader(), printSlipValues, printSlipModel.getCustomer());
            }
            printSlipValues.setFooterStr(printInvoiceFooter);
            if (printSlipValues.isShowFooter() && !printSlipValues.isIlkSayfa()) {
                printSlipValues.setHeaderStr("");
            }
            if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize()) {
                if (printSlipValues.isShowFooter()) {
                    printSlipValues.setSummaryStr("");
                }
            } else {
                printSlipValues.setFooterStr("");
            }
            String Print = printProcess.Print(printSlipValues.getDetailRowCount(), printSlipValues.getHeaderStr(), printSlipValues.getDetailStr(), printSlipValues.getSummaryStr(), printSlipValues.getFooterStr());
            if (printSlipValues.getConverttren() == 1) {
                printSlipValues.getReturnValues()[printSlipValues.getCount()] = StringUtils.convTrCharEN(Print);
            } else {
                printSlipValues.getReturnValues()[printSlipValues.getCount()] = Print;
            }
            printSlipValues.setCount(printSlipValues.getCount() + 1);
            printSlipValues.setSayi(printSlipValues.getSayi() + 1);
            printSlipValues.setDetailStr("");
            printSlipValues.setPageTotal(0.0d);
            printSlipValues.setPageAmountTotal(0.0d);
            printSlipValues.setIlkSayfa(false);
        }
    }
    static String printInvoiceSummary(PrintSlipValues printSlipValues, PrintSlipModel printSlipModel) {
        for (int i2 = 0; i2 <= printSlipValues.getVats().length - 1; i2++) {
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getVats()[i2]) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getVatsamount()[i2]) + Line);
        }
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSlipModel.getItemSlip().getSlipHeader().getPlaCode() + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + getPlasiyerName(printSlipModel) + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSlipValues.getPageAmountTotal() + Line);
        if (printSlipModel.getItemSlip().getSlipHeader().getSlipType() == NetsisSlipType.ftAIrs || printSlipModel.getItemSlip().getSlipHeader().getSlipType() == NetsisSlipType.ftSIrs) {
            for (int i3 = 0; i3 < 10; i3++) {
                printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + Line);
            }
        }
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSlipValues.getLineSize() + Line);
        return printSlipValues.getSummaryStr();
    }
    static String printWhTransferSummary(PrintSlipValues printSlipValues, PrintSlipModel printSlipModel) {
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSlipValues.getLineSize() + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + getPlasiyerName(printSlipModel) + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSlipValues.getPageAmountTotal() + Line);
        printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
        return printSlipValues.getSummaryStr();
    }
    static int getLineSize(ItemSlip itemSlip) {
        int size = itemSlip.getLines().size();
        if (ErpCreator.getInstance().getmBaseErp().getPrintLotDetail()) {
            for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
                if (itemSlipLine.getKalemSeri() != null && itemSlipLine.getKalemSeri().size() > 0) {
                    size += itemSlipLine.getKalemSeri().size();
                }
            }
        }
        return size;
    }
    static String getPlasiyerName(PrintSlipModel printSlipModel) {
        if (printSlipModel.getPrintExtraInfo() != null && printSlipModel.getPrintExtraInfo().size() > 0) {
            return printSlipModel.getPrintExtraInfo().get(0).getPlasiyerName();
        }
        return "";
    }
    static String getUuid(PrintSlipModel printSlipModel) {
        if (printSlipModel.getPrintExtraInfo() != null && printSlipModel.getPrintExtraInfo().size() > 0) {
            return printSlipModel.getPrintExtraInfo().get(0).getUuid();
        }
        return "";
    }
    static String printInvoiceHeader(PrintSlipModel printSlipModel) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ItemSlipHeader slipHeader = printSlipModel.getItemSlip().getSlipHeader();
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(slipHeader.getCustomerCode());
        String currCode = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCurrCode(slipHeader.getCurrencyType());
        Cari customer = printSlipModel.getCustomer();
        Cari shippingCustomer = printSlipModel.getShippingCustomer();
        double debit = customer.getCariTemelBilgi().getDebit() - customer.getCariTemelBilgi().getCredit();
        String str6 = (((printSlipModel.getItemSlip().getLineCount() + Line) + slipHeader.getBranchCode() + Line) + slipHeader.getCustomerCode() + Line) + StringUtils.checkNullValueString(slipHeader.getSlipNo()) + Line;
        if (slipHeader.getActualDate() != null) {
            str = str6 + DateAndTimeUtils.convertDateY(slipHeader.getActualDate()) + Line;
        } else {
            str = str6 + Line;
        }
        String str7 = ((((str + StringUtils.checkNullValueString(slipHeader.getCode1()) + Line) + StringUtils.checkNullValueString(slipHeader.getCode2()) + Line) + StringUtils.checkNullValueString(slipHeader.getExplanation()) + Line) + StringUtils.checkNullValueString(slipHeader.getOrderNumber()) + Line) + slipHeader.getPaymentDay() + Line;
        if (slipHeader.getPaymentDate() != null) {
            str2 = str7 + DateAndTimeUtils.convertDateY(slipHeader.getPaymentDate()) + Line;
        } else {
            str2 = str7 + Line;
        }
        if (slipHeader.getIntegrationDate() != null) {
            str3 = str2 + DateAndTimeUtils.convertDateY(slipHeader.getIntegrationDate()) + Line;
        } else {
            str3 = str2 + Line;
        }
        String str8 = ((((str3 + StringUtils.checkNullValueString(slipHeader.getCustomerCode2()) + Line) + StringUtils.checkNullValueString(slipHeader.getPlaCode()) + Line) + currCode + Line) + StringUtils.checkNullValueString(slipHeader.getProjectCode()) + Line) + StringUtils.checkNullValueString(slipHeader.getDiscountCode()) + Line;
        if (slipHeader.getPriceDate() != null) {
            str4 = str8 + DateAndTimeUtils.convertDateY(slipHeader.getPriceDate()) + Line;
        } else {
            str4 = str8 + Line;
        }
        if (slipHeader.getDiscountDate() != null) {
            str5 = str4 + DateAndTimeUtils.convertDateY(slipHeader.getDiscountDate()) + Line;
        } else {
            str5 = str4 + Line;
        }
        String str9 = ((((((((((((((((str5 + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation1()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation2()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation3()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation4()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation5()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation6()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation7()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation8()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation9()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation10()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation11()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation12()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation13()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation14()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation15()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation16()) + Line) + StringUtils.checkNullValueString(slipHeader.getCustomerCode2()) + Line;
        String sb = str9 +
                (shippingCustomer != null ? shippingCustomer.getCariTemelBilgi().getDefinition() : "") +
                Line;
        String str10 = (((((((((((((((sb + slipHeader.getCustomerCode() + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getDefinition()) + Line) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_adres()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCity()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_ilce()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelnrs1()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getFaxNr()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffice()) + Line) + customerInCharge + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getISKONTO_ORANI()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getEmailAddr()) + Line;
        String sb2 = str10 +
                (shippingCustomer != null ? StringUtils.checkNullValueString(shippingCustomer.getCariTemelBilgi().getCari_adres()) : "") +
                Line;
        String str11 = sb2 + Line;
        String sb4 = str11 +
                (shippingCustomer != null ? StringUtils.checkNullValueString(shippingCustomer.getCariTemelBilgi().getCity()) : "") +
                Line;
        String sb5 = sb4 +
                (shippingCustomer != null ? StringUtils.checkNullValueString(shippingCustomer.getCariTemelBilgi().getCari_ilce()) : "") +
                Line;
        return (((((sb5 + Line) + Line) + StringUtils.checkNullValueString(customer.getCariEkBilgi().getTckimlikno()) + Line) + StringUtils.checkNullValueString(slipHeader.getPlaCode()) + Line) + StringUtils.checkNullValueString(getUuid(printSlipModel)) + Line) + StringUtils.dFormat(debit) + Line;
    }
    static String printWhTransferHeader(PrintSlipModel printSlipModel) {
        ItemSlipHeader slipHeader = printSlipModel.getItemSlip().getSlipHeader();
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(slipHeader.getCustomerCode());
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((printSlipModel.getItemSlip().getLines().size() + Line) + slipHeader.getBranchCode() + Line) + slipHeader.getCustomerCode() + Line) + StringUtils.checkNullValueString(slipHeader.getDate()) + Line) + getPlasiyerName(printSlipModel) + Line) + StringUtils.checkNullValueString(slipHeader.getProjectCode()) + Line) + StringUtils.checkNullValueString(slipHeader.getDiscountCode()) + Line) + StringUtils.checkNullValueString(slipHeader.getPriceDate()) + Line) + StringUtils.checkNullValueString(slipHeader.getDiscountDate()) + Line) + StringUtils.checkNullValueString(slipHeader.getOrderNumber()) + Line) + slipHeader.getCustomerCode() + Line) + StringUtils.checkNullValueString(null) + Line) + Line) + Line) + StringUtils.checkNullValueString(slipHeader.getExplanation()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation1()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation2()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation3()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation4()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation5()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation6()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation7()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation8()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation9()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation10()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation11()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation12()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation13()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation14()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation15()) + Line) + StringUtils.checkNullValueString(slipHeader.getAppendixExplanation16()) + Line) + StringUtils.checkNullValueString(slipHeader.getSlipNo()) + Line) + Line) + StringUtils.checkNullValueString(slipHeader.getCustomerCode2()) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + customerInCharge + Line) + Line) + Line) + Line) + Line) + Line) + Line) + Line) + StringUtils.checkNullValueString(null) + Line) + StringUtils.checkNullValueString(slipHeader.getPlaCode()) + Line;
    }
    static String getItemCode(List<PrintExtraInfo> list, final ItemSlipLine itemSlipLine) {
        List list2;
        if (list == null || list.size() <= 0 || (list2 = (List) list.stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.printutil.NPrintSlipFicheExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public boolean test(Object obj) {
                boolean lambdagetItemCode0;
                lambdagetItemCode0 = NPrintSlipFiche.lambdagetItemCode0(ItemSlipLine.this, (PrintExtraInfo) obj);
                return lambdagetItemCode0;
            }
        }).collect(Collectors.toList())) == null || list2.size() <= 0) {
            return itemSlipLine.getStokKodu();
        }
        return (list2.size() <= 0 || TextUtils.isEmpty(((PrintExtraInfo) list2.get(0)).getCustomerItemCode())) ? itemSlipLine.getStokKodu() : ((PrintExtraInfo) list2.get(0)).getCustomerItemCode();
    }
    public static boolean lambdagetItemCode0(ItemSlipLine itemSlipLine, PrintExtraInfo printExtraInfo) {
        return printExtraInfo.getItemCode() != null && printExtraInfo.getItemCode().equals(itemSlipLine.getStokKodu());
    }
    static String getItemName(List<PrintExtraInfo> list, final ItemSlipLine itemSlipLine) {
        if (list != null && list.size() > 0) {
            List list2 = (List) list.stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.printutil.NPrintSlipFicheExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    boolean lambdagetItemName1;
                    lambdagetItemName1 = NPrintSlipFiche.lambdagetItemName1(ItemSlipLine.this, (PrintExtraInfo) obj);
                    return lambdagetItemName1;
                }
            }).collect(Collectors.toList());
            return (list2 == null || list2.size() <= 0 || TextUtils.isEmpty(((PrintExtraInfo) list2.get(0)).getCustomerItemName())) ? itemSlipLine.getMALADI() : ((PrintExtraInfo) list2.get(0)).getCustomerItemName();
        }
        return itemSlipLine.getMALADI();
    }
    public static boolean lambdagetItemName1(ItemSlipLine itemSlipLine, PrintExtraInfo printExtraInfo) {
        return printExtraInfo.getItemCode() != null && printExtraInfo.getItemCode().equals(itemSlipLine.getStokKodu());
    }
    static String printInvoiceLine(ItemSlipLine itemSlipLine, PrintSlipValues printSlipValues, PrintSlipModel printSlipModel) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = ((getItemCode(printSlipModel.getPrintExtraInfo(), itemSlipLine) + Line) + itemSlipLine.getSTraFATIRSNO() + Line) + StringUtils.formatDoubleFourDigits(itemSlipLine.getSTraGCMIK()) + Line;
        printSlipValues.setPageAmountTotal(printSlipValues.getPageAmountTotal() + itemSlipLine.getSTraGCMIK());
        String str8 = (str7 + StringUtils.formatDoubleFourDigits(itemSlipLine.getSTraGCMIK2()) + Line) + StringUtils.dFormat(itemSlipLine.getCEVRIM()) + Line;
        if (itemSlipLine.getSTraTAR() != null) {
            str = str8 + DateAndTimeUtils.convertDateY(itemSlipLine.getSTraTAR()) + Line;
        } else {
            str = str8 + Line;
        }
        String str9 = ((((((((((((((str + StringUtils.dFormat(itemSlipLine.getSTraNF()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraBF()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraKDV()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraSatIsk()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraSatIsk2()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraMALFISK()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraDovizAdi()) + Line) + itemSlipLine.getPROMASYONKODU() + Line) + StringUtils.dFormat(itemSlipLine.getSTraDOVFIAT()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraKOD1()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraKOD2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraSIPNUM()) + Line) + itemSlipLine.getPlasiyerKodu() + Line) + StringUtils.dFormat(itemSlipLine.getStraOtv()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getIrsaliyeno()) + Line;
        if (itemSlipLine.getIrsaliyetar() != null) {
            str2 = str9 + DateAndTimeUtils.convertDateY(itemSlipLine.getIrsaliyetar()) + Line;
        } else {
            str2 = str9 + Line;
        }
        String str10 = str2 + StringUtils.checkNullValueString(itemSlipLine.getStraKosulK()) + Line;
        int olcubr = itemSlipLine.getOlcubr();
        if (olcubr == 1) {
            str3 = str10 + StringUtils.checkNullValueString(itemSlipLine.getOBR1()) + Line;
        } else if (olcubr == 2) {
            str3 = str10 + StringUtils.checkNullValueString(itemSlipLine.getOBR2()) + Line;
        } else {
            str3 = str10 + StringUtils.checkNullValueString(itemSlipLine.getOBR3()) + Line;
        }
        if (itemSlipLine.getVadetar() != null) {
            str4 = str3 + DateAndTimeUtils.convertDateY(itemSlipLine.getVadetar()) + Line;
        } else {
            str4 = str3 + Line;
        }
        String str11 = ((((((((((((((((((((str4 + StringUtils.dFormat(itemSlipLine.getBrCevrim1()) + Line) + StringUtils.dFormat(itemSlipLine.getBrCevrim2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getProjeKodu()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraCARIKOD()) + Line) + itemSlipLine.getDEPOKODU() + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraACIK()) + Line) + itemSlipLine.getStraSubeKodu() + Line) + StringUtils.checkNullValueString(getItemName(printSlipModel.getPrintExtraInfo(), itemSlipLine)) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR1()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR3()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar1()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar2()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar3()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar4()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar5()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar6()) + Line) + StringUtils.dFormat(itemSlipLine.getFiyatlar7()) + Line) + StringUtils.dFormat(itemSlipLine.getSatisKDVOran()) + Line) + StringUtils.dFormat(itemSlipLine.getAlisKDVOran()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraSatIsk3()) + Line;
        int fiatBirimi = itemSlipLine.getFiatBirimi();
        if (fiatBirimi == 1) {
            str5 = str11 + itemSlipLine.getOBR1() + Line;
        } else if (fiatBirimi == 2) {
            str5 = str11 + itemSlipLine.getOBR2() + Line;
        } else {
            str5 = str11 + itemSlipLine.getOBR3() + Line;
        }
        String str12 = (((((((((str5 + StringUtils.dFormat(itemSlipLine.getSTraSatIsk4()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraSatIsk5()) + Line) + StringUtils.dFormat(itemSlipLine.getSTraSatIsk6()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getYapKod()) + Line) + buildLineDescription(itemSlipLine.getSatirBaziAciks()) + Line) + StringUtils.dFormat(itemSlipLine.getKalemGenIskOran1()) + Line) + StringUtils.dFormat(itemSlipLine.getKalemGenIskOran2()) + Line) + StringUtils.dFormat(itemSlipLine.getKalemGenIskOran3()) + Line) + calculateLineAmountsByUnit(itemSlipLine, printSlipValues)) + Line;
        if (itemSlipLine.getSTraFATIRSNO() != null) {
            str6 = ((str12 + getWeight(itemSlipLine.getSTraFATIRSNO(), itemSlipLine.getSira(), printSlipModel.getItemSlip().getSlipType()).width + Line) + getWeight(itemSlipLine.getSTraFATIRSNO(), itemSlipLine.getSira(), printSlipModel.getItemSlip().getSlipType()).length + Line) + getWeight(itemSlipLine.getSTraFATIRSNO(), itemSlipLine.getSira(), printSlipModel.getItemSlip().getSlipType()).height + Line;
        } else {
            str6 = ((str12 + itemSlipLine.getWidth() + Line) + itemSlipLine.getLength() + Line) + itemSlipLine.getHeight() + Line;
        }
        return str6 + '\r';
    }
    static String printWhTransferLine(ItemSlipLine itemSlipLine, PrintSlipValues printSlipValues, PrintSlipModel printSlipModel) {
        String str;
        String str2;
        String str3;
        String str4 = ((itemSlipLine.getStokKodu() + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraFATIRSNO()) + Line) + StringUtils.formatDoubleFourDigits(itemSlipLine.getSTraGCMIK()) + Line;
        printSlipValues.setPageAmountTotal(printSlipValues.getPageAmountTotal() + itemSlipLine.getSTraGCMIK());
        String str5 = str4 + StringUtils.formatDoubleFourDigits(itemSlipLine.getSTraGCMIK2()) + Line;
        int olcubr = itemSlipLine.getOlcubr();
        if (olcubr == 1) {
            str = str5 + StringUtils.checkNullValueString(itemSlipLine.getOBR1()) + Line;
        } else if (olcubr == 2) {
            str = str5 + StringUtils.checkNullValueString(itemSlipLine.getOBR2()) + Line;
        } else {
            str = str5 + StringUtils.checkNullValueString(itemSlipLine.getOBR3()) + Line;
        }
        String str6 = str + StringUtils.checkNullValueString(getItemName(printSlipModel.getPrintExtraInfo(), itemSlipLine)) + Line;
        if (itemSlipLine.getSTraTAR() != null) {
            str2 = str6 + DateAndTimeUtils.convertDateY(itemSlipLine.getSTraTAR()) + Line;
        } else {
            str2 = str6 + Line;
        }
        String str7 = ((((str2 + StringUtils.checkNullValueString(itemSlipLine.getSTraKOD1()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraKOD2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraSIPNUM()) + Line) + itemSlipLine.getPlasiyerKodu() + Line) + StringUtils.checkNullValueString(itemSlipLine.getIrsaliyeno()) + Line;
        if (itemSlipLine.getIrsaliyetar() != null) {
            str3 = str7 + DateAndTimeUtils.convertDateY(itemSlipLine.getIrsaliyetar()) + Line;
        } else {
            str3 = str7 + Line;
        }
        return (((((((((((((((str3 + StringUtils.checkNullValueString(itemSlipLine.getStraKosulK()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR1()) + Line) + StringUtils.dFormat(itemSlipLine.getBrCevrim1()) + Line) + StringUtils.dFormat(itemSlipLine.getBrCevrim2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getProjeKodu()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraCARIKOD()) + Line) + itemSlipLine.getDEPOKODU() + Line) + StringUtils.checkNullValueString(itemSlipLine.getSTraACIK()) + Line) + itemSlipLine.getStraSubeKodu() + Line) + StringUtils.checkNullValueString(getItemName(printSlipModel.getPrintExtraInfo(), itemSlipLine)) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR1()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR2()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getOBR3()) + Line) + StringUtils.checkNullValueString(itemSlipLine.getYapKod()) + Line) + buildLineDescription(itemSlipLine.getSatirBaziAciks()) + Line) + '\r';
    }
    static String buildLineDescription(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(StringUtils.checkNullValueString(it.next()));
        }
        return sb.toString();
    }
    static String printInvoiceFooter(ItemSlipHeader itemSlipHeader, PrintSlipValues printSlipValues, Cari cari) {
        double debit = cari.getCariTemelBilgi().getDebit() - cari.getCariTemelBilgi().getCredit();
        String str = ((((((((((((((StringUtils.dFormat(itemSlipHeader.getGrossTotal()) + Line) + StringUtils.dFormat(itemSlipHeader.getVat()) + Line) + StringUtils.dFormat(itemSlipHeader.getCurrencyTotal()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralTotal()) + Line) + StringUtils.dFormat(itemSlipHeader.getRounding()) + Line) + StringUtils.dFormat(itemSlipHeader.getFazDiscount()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscountRatio()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscountRatio2()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscountRatio3()) + Line) + StringUtils.dFormat(itemSlipHeader.getInvDown1()) + Line) + StringUtils.dFormat(itemSlipHeader.getInvDown2()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscount()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscount2()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralDiscount3()) + Line) + itemSlipHeader.getPaymentCode() + Line;
        String sb2 = str +
                StringUtils.dFormat(itemSlipHeader.getVatIt() == 0.0d ? printSlipValues.getTotvatsamount()[0] : itemSlipHeader.getVatIt()) +
                Line;
        String sb4 = sb2 +
                StringUtils.dFormat(itemSlipHeader.getVat10() == 0.0d ? printSlipValues.getTotvats()[0] : itemSlipHeader.getVat10()) +
                Line;
        String sb6 = sb4 +
                StringUtils.dFormat(itemSlipHeader.getVat2O() == 0.0d ? printSlipValues.getTotvats()[1] : itemSlipHeader.getVat2O()) +
                Line;
        String sb8 = sb6 +
                StringUtils.dFormat(itemSlipHeader.getVat2T() == 0.0d ? printSlipValues.getTotvatsamount()[1] : itemSlipHeader.getVat2T()) +
                Line;
        String sb10 = sb8 +
                StringUtils.dFormat(itemSlipHeader.getVat3O() == 0.0d ? printSlipValues.getTotvats()[2] : itemSlipHeader.getVat3O()) +
                Line;
        String sb12 = sb10 +
                StringUtils.dFormat(itemSlipHeader.getVat3T() == 0.0d ? printSlipValues.getTotvatsamount()[2] : itemSlipHeader.getVat3T()) +
                Line;
        String sb14 = sb12 +
                StringUtils.dFormat(itemSlipHeader.getVat4O() == 0.0d ? printSlipValues.getTotvats()[3] : itemSlipHeader.getVat4O()) +
                Line;
        String sb16 = sb14 +
                StringUtils.dFormat(itemSlipHeader.getVat4T() == 0.0d ? printSlipValues.getTotvatsamount()[3] : itemSlipHeader.getVat4T()) +
                Line;
        String sb18 = sb16 +
                StringUtils.dFormat(itemSlipHeader.getVat5O() == 0.0d ? printSlipValues.getTotvats()[4] : itemSlipHeader.getVat5O()) +
                Line;
        String sb20 = sb18 +
                StringUtils.dFormat(itemSlipHeader.getVat5T() == 0.0d ? printSlipValues.getTotvatsamount()[4] : itemSlipHeader.getVat5T()) +
                Line;
        String sb21 = sb20 +
                (itemSlipHeader.iseInvoiceCustomer() ? "Evet" : "Hay\u0131r") +
                Line;
        String str2 = ((((((sb21 + StringUtils.dFormat(itemSlipHeader.getOtv()) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(itemSlipHeader.getGeneralTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[0]).replace(".", ",") + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[1]).replace(".", ",") + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[2]).replace(".", ",") + Line) + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getAmountTotal()).replace(".", ",") + Line;
        if (itemSlipHeader.getSlipType() == NetsisSlipType.ftSFat || itemSlipHeader.getSlipType() == NetsisSlipType.ftAFat) {
            str2 = str2 + StringUtils.dFormat(debit) + Line;
        }
        if (itemSlipHeader.getSlipType() == NetsisSlipType.ftAIrs || itemSlipHeader.getSlipType() == NetsisSlipType.ftSIrs) {
            for (int i2 = 0; i2 < 30; i2++) {
                str2 = str2 + Line;
            }
        }
        String str3 = (((((str2 + StringUtils.dFormat(printSlipValues.getWeight()) + Line) + StringUtils.dFormat(printSlipValues.getGrossWeight()) + Line) + printSlipValues.getLineSize() + Line) + Line) + Line) + Line;
        if (itemSlipHeader.getSlipType() == NetsisSlipType.ftSSip) {
            str3 = str3 + Line;
        }
        if (itemSlipHeader.getSlipType() == NetsisSlipType.ftAIrs || itemSlipHeader.getSlipType() == NetsisSlipType.ftSIrs) {
            for (int i3 = 0; i3 < 34; i3++) {
                str3 = str3.substring(0, str3.length() - 2);
            }
        }
        return str3 + StringUtils.dFormat(printSlipValues.getNetVolume()) + Line;
    }
    static String printWhTransferFooter(ItemSlipHeader itemSlipHeader, PrintSlipValues printSlipValues) {
        String str = (((((((((StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[0]).replace(".", ",") + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[1]).replace(".", ",") + Line) + StringUtils.formatDoubleFourDigits(printSlipValues.getUnitTotals()[2]).replace(".", ",") + Line) + StringUtils.dFormat(itemSlipHeader.getGrossTotal()) + Line) + printSlipValues.getLineSize() + Line) + Line) + itemSlipHeader.getCustomerCode() + Line) + Line) + Line) + Line;
        String sb = str +
                (itemSlipHeader.iseInvoiceCustomer() ? "Evet" : "Hay\u0131r") +
                Line;
        return (((((((sb + StringUtils.formatDoubleFourDigits(printSlipValues.getAmountTotal()).replace(".", ",") + Line) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(itemSlipHeader.getGeneralTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.dFormat(itemSlipHeader.getGeneralTotal()) + Line) + StringUtils.dFormat(itemSlipHeader.getCurrencyTotal()) + Line) + StringUtils.dFormat(itemSlipHeader.getVat()) + Line) + StringUtils.dFormat(itemSlipHeader.getGrossTotal()) + Line) + itemSlipHeader.getPaymentCode() + Line;
    }
    static void printInvoiceLineSerials(ItemSlipLine itemSlipLine, PrintSlipValues printSlipValues, PrintSlipModel printSlipModel, PrintProcess printProcess) {
        if (!ErpCreator.getInstance().getmBaseErp().getPrintLotDetail() || itemSlipLine.getKalemSeri() == null || itemSlipLine.getKalemSeri().size() == 0) {
            return;
        }
        for (ItemSlipSerialLine itemSlipSerialLine : itemSlipLine.getKalemSeri()) {
            printSlipValues.setDetailStr(printSlipValues.getDetailStr() + Line);
            for (int i2 = 2; i2 < 33; i2++) {
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + Line);
            }
            printSlipValues.setDetailStr(printSlipValues.getDetailStr() + itemSlipSerialLine.getSerial1() + " - " + itemSlipSerialLine.getAmount() + Line);
            for (int i3 = 34; i3 < 56; i3++) {
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + Line);
            }
            printSlipValues.setDetailStr(printSlipValues.getDetailStr() + "\r");
            printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + 1);
            checkSummaryAndFooter(printSlipValues, printSlipModel, printProcess);
        }
    }
    static String calculateLineAmountsByUnit(ItemSlipLine itemSlipLine, PrintSlipValues printSlipValues) {
        String str;
        double sTraGCMIK;
        double sTraGCMIK2;
        double brCevrim2;
        double d2;
        String str2;
        double cevrim;
        double sTraGCMIK3 = itemSlipLine.getSTraGCMIK();
        StringBuilder sb = new StringBuilder();
        String obr1 = itemSlipLine.getOBR1();
        String str3 = Line;
        if (obr1 == null) {
            str = Line;
        } else {
            str = StringUtils.formatDoubleFourDigits(sTraGCMIK3) + Line;
        }
        sb.append(str);
        String sb2 = sb.toString();
        int olcubr = itemSlipLine.getOlcubr();
        if (olcubr != 1) {
            sTraGCMIK = 0.0d;
            if (olcubr == 2) {
                if (itemSlipLine.getCEVRIM() == 0.0d && itemSlipLine.getBrCevrim1() == 0.0d) {
                    sTraGCMIK = convertByConfact(itemSlipLine.getStokKodu(), 2, sTraGCMIK3);
                } else {
                    sTraGCMIK = itemSlipLine.getSTraGCMIK() * (itemSlipLine.getCEVRIM() != 0.0d ? itemSlipLine.getCEVRIM() : itemSlipLine.getBrCevrim1());
                }
                sTraGCMIK2 = itemSlipLine.getSTraGCMIK();
                brCevrim2 = itemSlipLine.getBrCevrim2();
            } else {
                if (olcubr != 3) {
                    d2 = 0.0d;
                } else {
                    double sTraGCMIK4 = itemSlipLine.getSTraGCMIK() * itemSlipLine.getBrCevrim1();
                    if (itemSlipLine.getCEVRIM() == 0.0d && itemSlipLine.getBrCevrim2() == 0.0d) {
                        cevrim = convertByConfact(itemSlipLine.getStokKodu(), 3, sTraGCMIK3);
                    } else {
                        cevrim = (itemSlipLine.getCEVRIM() != 0.0d ? itemSlipLine.getCEVRIM() : itemSlipLine.getBrCevrim2()) * itemSlipLine.getSTraGCMIK();
                    }
                    double d3 = cevrim;
                    sTraGCMIK = sTraGCMIK4;
                    d2 = d3;
                }
                Log.i(TAG, itemSlipLine.getSTraFATIRSNO() + " Br1 Amount:" + sTraGCMIK3 + " Br2 Amount:" + sTraGCMIK + " Br3 Amount:" + d2);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                if (itemSlipLine.getOBR2() != null) {
                    str2 = Line;
                } else {
                    str2 = StringUtils.formatDoubleFourDigits(sTraGCMIK) + Line;
                }
                sb3.append(str2);
                String sb4 = sb3.toString();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(sb4);
                if (itemSlipLine.getOBR3() != null) {
                    str3 = StringUtils.formatDoubleFourDigits(d2) + Line;
                }
                sb5.append(str3);
                String sb6 = sb5.toString();
                double[] unitTotals = printSlipValues.getUnitTotals();
                unitTotals[0] = unitTotals[0] + sTraGCMIK3;
                double[] unitTotals2 = printSlipValues.getUnitTotals();
                unitTotals2[1] = unitTotals2[1] + sTraGCMIK;
                double[] unitTotals3 = printSlipValues.getUnitTotals();
                unitTotals3[2] = unitTotals3[2] + d2;
                return sb6;
            }
        } else {
            sTraGCMIK = itemSlipLine.getSTraGCMIK() * itemSlipLine.getBrCevrim1();
            sTraGCMIK2 = itemSlipLine.getSTraGCMIK();
            brCevrim2 = itemSlipLine.getBrCevrim2();
        }
        d2 = sTraGCMIK2 * brCevrim2;
        Log.i(TAG, itemSlipLine.getSTraFATIRSNO() + " Br1 Amount:" + sTraGCMIK3 + " Br2 Amount:" + sTraGCMIK + " Br3 Amount:" + d2);
        StringBuilder sb32 = new StringBuilder();
        sb32.append(sb2);
        if (itemSlipLine.getOBR2() != null) {
        }
        sb32.append(str2);
        String sb42 = sb32.toString();
        StringBuilder sb52 = new StringBuilder();
        sb52.append(sb42);
        if (itemSlipLine.getOBR3() != null) {
        }
        sb52.append(str3);
        String sb62 = sb52.toString();
        double[] unitTotals4 = printSlipValues.getUnitTotals();
        unitTotals4[0] = unitTotals4[0] + sTraGCMIK3;
        double[] unitTotals22 = printSlipValues.getUnitTotals();
        unitTotals22[1] = unitTotals22[1] + sTraGCMIK;
        double[] unitTotals32 = printSlipValues.getUnitTotals();
        unitTotals32[2] = unitTotals32[2] + d2;
        return sb62;
    }
    static double convertByConfact(String str, int i2, double d2) {
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{str, String.valueOf(i2)});
        if (table == null || table.size() <= 0) {
            return 0.0d;
        }
        return (d2 * ((ItemUnits) table.get(0)).convfact2) / ((ItemUnits) table.get(0)).convfact1;
    }
}
