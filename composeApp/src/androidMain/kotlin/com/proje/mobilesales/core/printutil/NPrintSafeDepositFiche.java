package com.proje.mobilesales.core.printutil;

import android.util.Log;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintExtraInfo;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSafeDepositModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipValues;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;

public class NPrintSafeDepositFiche {
    private static final String Line = "\n\t";
    private static final String TAG = "NPrintSafeDepositFiche";
    private static boolean isModelLinesEmpty;
    public static String[] PrintSafeDepositFiche(int i2, boolean z, boolean z2, String str, int i3, PrintSafeDepositModel printSafeDepositModel) {
        PrintSlipValues printSlipValues = new PrintSlipValues(z, z2, i3, i2);
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        isModelLinesEmpty = isModelLinesEmpty(printSafeDepositModel);
        try {
            printSlipValues.setHeaderStr(printFicheHeader(printSafeDepositModel));
            printSlipValues.setLineSize(!isModelLinesEmpty ? 1 : 0);
            if (i2 <= 0) {
                i2 = 1;
            }
            printSlipValues.setReturnValues(new String[(printSlipValues.getLineSize() / i2) + 1]);
            printSlipValues.setDetailStr("");
            printSlipValues.setDetailStr(printFicheLine(printSafeDepositModel));
            printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + 1);
            printSlipValues.setPageTotal(printSlipValues.getPageTotal() + printSafeDepositModel.getSafeDeposit().getTutar());
            checkSummaryAndFooter(printSlipValues, printSafeDepositModel, printProcess);
        } catch (Exception e2) {
            Log.d(TAG, "PrintMixedReceiptFiche: ", e2);
            printSlipValues.getReturnValues()[0] = e2.getMessage();
        }
        return printSlipValues.getReturnValues();
    }
    static String printFicheHeader(PrintSafeDepositModel printSafeDepositModel) {
        String str;
        SafeDeposit safeDeposit = printSafeDepositModel.getSafeDeposit();
        CaseFiche caseFiche = printSafeDepositModel.getCaseFiche();
        Cari customer = printSafeDepositModel.getCustomer();
        PrintExtraInfo printExtraInfo = getPrintExtraInfo(printSafeDepositModel);
        String generalTotal = getGeneralTotal(printSafeDepositModel);
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(caseFiche.getClCode());
        double debit = customer.getCariTemelBilgi().getDebit() - customer.getCariTemelBilgi().getCredit();
        String str2 = (safeDeposit.getFisno() + Line) + printExtraInfo.getBranchCode() + Line;
        if (caseFiche.getGDate() != null) {
            str = str2 + DateAndTimeUtils.convertDateY(DateAndTimeUtils.convertSqlDate(caseFiche.getGDate())) + Line;
        } else {
            str = str2 + Line;
        }
        return ((((((((((((((((((((((((((str + StringUtils.checkNullValueString(caseFiche.getSpecode().getDefinition()) + Line) + StringUtils.checkNullValueString(safeDeposit.getAciklama()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getDefinition()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getSpecode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_adres()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCity()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_ilce()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelnrs1()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelNrs2()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getFaxNr()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffice()) + Line) + customerInCharge + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getEmailAddr()) + Line) + StringUtils.dFormat(debit) + Line) + StringUtils.checkNullValueString(DateAndTimeUtils.getTimeOnly(caseFiche.getGDate())) + Line) + Line) + Line) + generalTotal + Line) + StringUtils.convertTotal2Text(generalTotal, "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.checkNullValueString(printExtraInfo.getPlasiyerName()) + Line) + StringUtils.checkNullValueString(customer.getCariEkBilgi().getTckimlikno()) + Line) + Line) + getPlasiyerCode(printSafeDepositModel) + Line) + getProjectCode(printSafeDepositModel) + Line;
    }
    static String printFicheLine(PrintSafeDepositModel printSafeDepositModel) {
        return (((((StringUtils.checkNullValueString(printSafeDepositModel.getCaseFiche().getSpecode().getDefinition()) + Line) + StringUtils.dFormat(printSafeDepositModel.getSafeDeposit().getTutar()) + Line) + StringUtils.checkNullValueString(printSafeDepositModel.getCaseFiche().getDocNo().toString()) + Line) + StringUtils.checkNullValueString(printSafeDepositModel.getSafeDeposit().getAciklama()) + Line) + getProjectCode(printSafeDepositModel) + Line) + '\r';
    }
    static void checkSummaryAndFooter(PrintSlipValues printSlipValues, PrintSafeDepositModel printSafeDepositModel, PrintProcess printProcess) {
        if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize() || printSlipValues.getPrintLineCount() == printSlipValues.getDetailRowCount() * printSlipValues.getSayi()) {
            if (printSlipValues.getPageTotal() < 0.0d) {
                printSlipValues.setPageTotal(0.0d);
            }
            printSlipValues.setNakliyeYekun(printSlipValues.getNakliyeYekun() + printSlipValues.getPageTotal());
            printSlipValues.setSummaryStr("");
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getPageTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + getPlasiyerCode(printSafeDepositModel) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printSafeDepositModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + Line);
            printSlipValues.setFooterStr(printFooter(printSafeDepositModel, printSlipValues));
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
    static String printFooter(PrintSafeDepositModel printSafeDepositModel, PrintSlipValues printSlipValues) {
        return ((((StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getNakliyeYekun()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + getPlasiyerCode(printSafeDepositModel) + Line) + printSafeDepositModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line) + Line;
    }
    static boolean isModelLinesEmpty(PrintSafeDepositModel printSafeDepositModel) {
        return printSafeDepositModel.getSafeDeposit() == null;
    }
    static String getGeneralTotal(PrintSafeDepositModel printSafeDepositModel) {
        return isModelLinesEmpty ? "" : StringUtils.dFormat(printSafeDepositModel.getSafeDeposit().getTutar());
    }
    static String getPlasiyerCode(PrintSafeDepositModel printSafeDepositModel) {
        return isModelLinesEmpty ? "" : StringUtils.checkNullValueString(printSafeDepositModel.getSafeDeposit().getPlasiyerKodu());
    }
    static String getProjectCode(PrintSafeDepositModel printSafeDepositModel) {
        return isModelLinesEmpty ? "" : StringUtils.checkNullValueString(printSafeDepositModel.getSafeDeposit().getProjeKodu());
    }
    static PrintExtraInfo getPrintExtraInfo(PrintSafeDepositModel printSafeDepositModel) {
        if (printSafeDepositModel.getPrintExtraInfo() == null || printSafeDepositModel.getPrintExtraInfo().size() == 0) {
            PrintExtraInfo printExtraInfo = new PrintExtraInfo();
            printExtraInfo.setPlasiyerName(ErpCreator.getInstance().getmBaseErp().getUser().getName());
            printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr()));
            printExtraInfo.setBranchCode(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt());
            return printExtraInfo;
        }
        return printSafeDepositModel.getPrintExtraInfo().get(0);
    }
}
