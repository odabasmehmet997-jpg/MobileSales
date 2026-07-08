package com.proje.mobilesales.core.printutil;

import android.util.Log;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintExtraInfo;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintMixedReceiptModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipValues;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceipt;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import java.util.function.ToDoubleFunction;

public class NPrintMixedReceiptFiche {
    private static final String Line = "\n\t";
    private static final String TAG = "NPrintMixedReceiptFiche";
    private static boolean isModelLinesEmpty;
    public static String[] PrintCreditReceiptFiche(int i2, boolean z, boolean z2, String str, int i3, PrintMixedReceiptModel printMixedReceiptModel) {
        PrintSlipValues printSlipValues = new PrintSlipValues(z, z2, i3, i2);
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        isModelLinesEmpty = isModelLinesEmpty(printMixedReceiptModel);
        try {
            printSlipValues.setHeaderStr(printCreditReceiptHeader(printMixedReceiptModel));
            printSlipValues.setLineSize(isModelLinesEmpty ? 0 : printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().size());
            if (i2 <= 0) {
                i2 = 1;
            }
            printSlipValues.setReturnValues(new String[(printSlipValues.getLineSize() / i2) + 1]);
            printSlipValues.setDetailStr("");
            for (int i4 = 0; i4 < printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().size(); i4++) {
                MixedReceipt mixedReceipt = printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(i4);
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + printCreditReciptLine(mixedReceipt, printMixedReceiptModel));
                printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + 1);
                printSlipValues.setPageTotal(printSlipValues.getPageTotal() + mixedReceipt.getPrice());
                checkSummaryAndFooterForCredit(printSlipValues, printMixedReceiptModel, printProcess);
            }
        } catch (Exception e2) {
            Log.d(TAG, "PrintMixedReceiptFiche: ", e2);
            printSlipValues.getReturnValues()[0] = e2.getMessage();
        }
        return printSlipValues.getReturnValues();
    }
    static String printCreditReceiptHeader(PrintMixedReceiptModel printMixedReceiptModel) {
        String str;
        MixedReceiptsMain mixedReceiptsMain = printMixedReceiptModel.getMixedReceiptsMain();
        Cari customer = printMixedReceiptModel.getCustomer();
        PrintExtraInfo printExtraInfo = getPrintExtraInfo(printMixedReceiptModel);
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(mixedReceiptsMain.getCariKod());
        double debit = customer.getCariTemelBilgi().getDebit() - customer.getCariTemelBilgi().getCredit();
        String generalTotal = getGeneralTotal(printMixedReceiptModel);
        String str2 = (mixedReceiptsMain.getBelgeNo() + Line) + printExtraInfo.getBranchCode() + Line;
        if (mixedReceiptsMain.getIslemTarihi() != null) {
            str = str2 + DateAndTimeUtils.convertDateY(mixedReceiptsMain.getIslemTarihi()) + Line;
        } else {
            str = str2 + Line;
        }
        return ((((((((((((((((((((((((((((((((str + StringUtils.checkNullValueString(mixedReceiptsMain.getTime()) + Line) + StringUtils.checkNullValueString(mixedReceiptsMain.getSpeCode()) + Line) + Line) + getExplanation(printMixedReceiptModel) + Line) + getInstallmentCount(printMixedReceiptModel) + Line) + Line) + StringUtils.checkNullValueString(mixedReceiptsMain.getBankCode()) + Line) + StringUtils.checkNullValueString(mixedReceiptsMain.getBankDescription()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getDefinition()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getSpecode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_adres()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCity()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_ilce()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelnrs1()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelNrs2()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getFaxNr()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffice()) + Line) + customerInCharge + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getEmailAddr()) + Line) + StringUtils.dFormat(debit) + Line) + StringUtils.checkNullValueString(mixedReceiptsMain.getTime()) + Line) + Line) + Line) + generalTotal + Line) + StringUtils.convertTotal2Text(generalTotal, "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.checkNullValueString(printExtraInfo.getPlasiyerName()) + Line) + StringUtils.checkNullValueString(customer.getCariEkBilgi().getTckimlikno()) + Line) + Line) + getPlasiyerCode(printMixedReceiptModel) + Line) + getProjectCode(printMixedReceiptModel) + Line;
    }
    static String printCreditReciptLine(MixedReceipt mixedReceipt, PrintMixedReceiptModel printMixedReceiptModel) {
        return (((((((StringUtils.checkNullValueString(printMixedReceiptModel.getMixedReceiptsMain().getSpeCode()) + Line) + StringUtils.dFormat(mixedReceipt.getPrice()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getProjectCode()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getDocNo()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getRefCode()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getContractCode()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getExplanation()) + Line) + '\r';
    }
    static void checkSummaryAndFooterForCredit(PrintSlipValues printSlipValues, PrintMixedReceiptModel printMixedReceiptModel, PrintProcess printProcess) {
        if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize() || printSlipValues.getPrintLineCount() == printSlipValues.getDetailRowCount() * printSlipValues.getSayi()) {
            if (printSlipValues.getPageTotal() < 0.0d) {
                printSlipValues.setPageTotal(0.0d);
            }
            printSlipValues.setNakliyeYekun(printSlipValues.getNakliyeYekun() + printSlipValues.getPageTotal());
            printSlipValues.setSummaryStr("");
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getPageTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getPlasiyerCode() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printMixedReceiptModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + Line);
            printSlipValues.setFooterStr(printFooterForCredit(printMixedReceiptModel, printSlipValues));
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
    static String printFooterForCredit(PrintMixedReceiptModel printMixedReceiptModel, PrintSlipValues printSlipValues) {
        return ((((StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getNakliyeYekun()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getPlasiyerCode() + Line) + printMixedReceiptModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line) + Line;
    }
    public static String[] PrintCashReceiptFiche(int i2, boolean z, boolean z2, String str, int i3, PrintMixedReceiptModel printMixedReceiptModel) {
        PrintSlipValues printSlipValues = new PrintSlipValues(z, z2, i3, i2);
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        isModelLinesEmpty = isModelLinesEmpty(printMixedReceiptModel);
        try {
            printSlipValues.setHeaderStr(printCashReceiptHeader(printMixedReceiptModel));
            printSlipValues.setLineSize(isModelLinesEmpty ? 0 : printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().size());
            if (i2 <= 0) {
                i2 = 1;
            }
            printSlipValues.setReturnValues(new String[(printSlipValues.getLineSize() / i2) + 1]);
            printSlipValues.setDetailStr("");
            for (int i4 = 0; i4 < printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().size(); i4++) {
                MixedReceipt mixedReceipt = printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(i4);
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + printCashReciptLine(mixedReceipt, printMixedReceiptModel));
                printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + 1);
                printSlipValues.setPageTotal(printSlipValues.getPageTotal() + mixedReceipt.getPrice());
                checkSummaryAndFooterForCash(printSlipValues, printMixedReceiptModel, printProcess);
            }
        } catch (Exception e2) {
            Log.d(TAG, "PrintMixedReceiptFiche: ", e2);
            printSlipValues.getReturnValues()[0] = e2.getMessage();
        }
        return printSlipValues.getReturnValues();
    }
    static String printCashReceiptHeader(PrintMixedReceiptModel printMixedReceiptModel) {
        String str;
        MixedReceiptsMain mixedReceiptsMain = printMixedReceiptModel.getMixedReceiptsMain();
        Cari customer = printMixedReceiptModel.getCustomer();
        PrintExtraInfo printExtraInfo = getPrintExtraInfo(printMixedReceiptModel);
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(mixedReceiptsMain.getCariKod());
        double debit = customer.getCariTemelBilgi().getDebit() - customer.getCariTemelBilgi().getCredit();
        String generalTotal = getGeneralTotal(printMixedReceiptModel);
        String str2 = (mixedReceiptsMain.getBelgeNo() + Line) + printExtraInfo.getBranchCode() + Line;
        if (mixedReceiptsMain.getIslemTarihi() != null) {
            str = str2 + DateAndTimeUtils.convertDateY(mixedReceiptsMain.getIslemTarihi()) + Line;
        } else {
            str = str2 + Line;
        }
        return ((((((((((((((((((((((((((str + StringUtils.checkNullValueString(mixedReceiptsMain.getSpeCode()) + Line) + getExplanation(printMixedReceiptModel) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getDefinition()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getSpecode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_adres()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCity()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_ilce()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelnrs1()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelNrs2()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getFaxNr()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffice()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffCode()) + Line) + customerInCharge + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getEmailAddr()) + Line) + StringUtils.dFormat(debit) + Line) + StringUtils.checkNullValueString(mixedReceiptsMain.getTime()) + Line) + Line) + Line) + generalTotal + Line) + StringUtils.convertTotal2Text(generalTotal, "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.checkNullValueString(printExtraInfo.getPlasiyerName()) + Line) + StringUtils.checkNullValueString(customer.getCariEkBilgi().getTckimlikno()) + Line) + Line) + getPlasiyerCode(printMixedReceiptModel) + Line) + getProjectCode(printMixedReceiptModel) + Line;
    }
    static String printCashReciptLine(MixedReceipt mixedReceipt, PrintMixedReceiptModel printMixedReceiptModel) {
        return (((((StringUtils.checkNullValueString(printMixedReceiptModel.getMixedReceiptsMain().getSpeCode()) + Line) + StringUtils.dFormat(mixedReceipt.getPrice()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getDocNo()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getExplanation()) + Line) + StringUtils.checkNullValueString(mixedReceipt.getProjectCode()) + Line) + '\r';
    }
    static void checkSummaryAndFooterForCash(PrintSlipValues printSlipValues, PrintMixedReceiptModel printMixedReceiptModel, PrintProcess printProcess) {
        if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize() || printSlipValues.getPrintLineCount() == printSlipValues.getDetailRowCount() * printSlipValues.getSayi()) {
            if (printSlipValues.getPageTotal() < 0.0d) {
                printSlipValues.setPageTotal(0.0d);
            }
            printSlipValues.setNakliyeYekun(printSlipValues.getNakliyeYekun() + printSlipValues.getPageTotal());
            printSlipValues.setSummaryStr("");
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getPageTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getPlasiyerCode() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printMixedReceiptModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + Line);
            printSlipValues.setFooterStr(printFooterForCash(printMixedReceiptModel, printSlipValues));
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
    static String printFooterForCash(PrintMixedReceiptModel printMixedReceiptModel, PrintSlipValues printSlipValues) {
        return ((((StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getNakliyeYekun()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getPlasiyerCode() + Line) + printMixedReceiptModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line) + Line;
    }
    static PrintExtraInfo getPrintExtraInfo(PrintMixedReceiptModel printMixedReceiptModel) {
        if (printMixedReceiptModel.getPrintExtraInfo() == null || printMixedReceiptModel.getPrintExtraInfo().size() == 0) {
            PrintExtraInfo printExtraInfo = new PrintExtraInfo();
            printExtraInfo.setPlasiyerName(ErpCreator.getInstance().getmBaseErp().getUser().getName());
            printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr()));
            printExtraInfo.setBranchCode(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt());
            return printExtraInfo;
        }
        return printMixedReceiptModel.getPrintExtraInfo().get(0);
    }
    static String getExplanation(PrintMixedReceiptModel printMixedReceiptModel) {
        return isModelLinesEmpty ? "" : StringUtils.checkNullValueString(printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getExplanation());
    }
    static String getInstallmentCount(PrintMixedReceiptModel printMixedReceiptModel) {
        return isModelLinesEmpty ? "" : StringUtils.convertIntToString(printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getInstalmentCount());
    }
    static boolean isModelLinesEmpty(PrintMixedReceiptModel printMixedReceiptModel) {
        return printMixedReceiptModel.getMixedReceiptsMain() == null || printMixedReceiptModel.getMixedReceiptsMain().getTahsilats() == null || printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().size() == 0;
    }
    static String getGeneralTotal(PrintMixedReceiptModel printMixedReceiptModel) {
        return isModelLinesEmpty ? "" : StringUtils.dFormat(printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.core.printutil.NPrintMixedReceiptFicheExternalSyntheticLambda0
            @Override // java.util.function.ToDoubleFunction
            public double applyAsDouble(Object obj) {
                return ((MixedReceipt) obj).getPrice();
            }
        }).sum());
    }
    static String getPlasiyerCode(PrintMixedReceiptModel printMixedReceiptModel) {
        return isModelLinesEmpty ? "" : StringUtils.checkNullValueString(printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getPlasiyerCode());
    }
    static String getProjectCode(PrintMixedReceiptModel printMixedReceiptModel) {
        return isModelLinesEmpty ? "" : StringUtils.checkNullValueString(printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().get(0).getProjectCode());
    }
}
