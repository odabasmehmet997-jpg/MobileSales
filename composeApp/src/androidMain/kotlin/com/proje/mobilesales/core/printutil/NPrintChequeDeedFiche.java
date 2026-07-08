package com.proje.mobilesales.core.printutil;

import android.util.Log;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeed;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedType;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintCheckAndPNotesModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintExtraInfo;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipValues;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFicheDetail;
import java.util.function.ToDoubleFunction;

public class NPrintChequeDeedFiche {
    private static final String Line = "\n\t";
    private static final String TAG = "NPrintChequeDeedFiche";
    private static boolean isModelLinesEmpty;
    public static String[] PrintChequeDeedFiche(int i2, boolean z, boolean z2, String str, int i3, PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        PrintSlipValues printSlipValues = new PrintSlipValues(z, z2, i3, i2);
        PrintProcess printProcess = new PrintProcess();
        printProcess.SprintIniFileName = str;
        isModelLinesEmpty = isModelLinesEmpty(printCheckAndPNotesModel);
        try {
            printSlipValues.setHeaderStr(printFicheHeader(printCheckAndPNotesModel));
            printSlipValues.setLineSize(isModelLinesEmpty ? 0 : printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().size());
            if (i2 <= 0) {
                i2 = 1;
            }
            printSlipValues.setReturnValues(new String[(printSlipValues.getLineSize() / i2) + 1]);
            printSlipValues.setDetailStr("");
            for (int i4 = 0; i4 < printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().size(); i4++) {
                NetsisChequeAndDeed netsisChequeAndDeed = printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().get(i4);
                printSlipValues.setDetailStr(printSlipValues.getDetailStr() + printFicheLine(netsisChequeAndDeed, printCheckAndPNotesModel.getChequeDeed().getDetails().get(i4), printCheckAndPNotesModel));
                printSlipValues.setPrintLineCount(printSlipValues.getPrintLineCount() + 1);
                printSlipValues.setPageTotal(printSlipValues.getPageTotal() + netsisChequeAndDeed.getTutar());
                checkSummaryAndFooter(printSlipValues, printCheckAndPNotesModel, printProcess);
            }
        } catch (Exception e2) {
            Log.d(TAG, "PrintMixedReceiptFiche: ", e2);
            printSlipValues.getReturnValues()[0] = e2.getMessage();
        }
        return printSlipValues.getReturnValues();
    }
    static String printFicheHeader(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        String str;
        NetsisChequeAndDeedMain netsisChequeAndDeedMain = printCheckAndPNotesModel.getNetsisChequeAndDeedMain();
        ChequeDeedFiche chequeDeed = printCheckAndPNotesModel.getChequeDeed();
        Cari customer = printCheckAndPNotesModel.getCustomer();
        PrintExtraInfo printExtraInfo = getPrintExtraInfo(printCheckAndPNotesModel);
        String generalTotal = getGeneralTotal(printCheckAndPNotesModel);
        String customerInCharge = ErpCreator.getInstance().getmBaseErp().getCustomerInCharge(chequeDeed.getClCode());
        double debit = customer.getCariTemelBilgi().getDebit() - customer.getCariTemelBilgi().getCredit();
        String str2 = (netsisChequeAndDeedMain.getBordroNo() + Line) + printExtraInfo.getBranchCode() + Line;
        if (chequeDeed.getgDate() != null) {
            str = str2 + DateAndTimeUtils.convertDateY(DateAndTimeUtils.convertSqlDate(chequeDeed.getgDate())) + Line;
        } else {
            str = str2 + Line;
        }
        return ((((((((((((((((((((((((((((((str + StringUtils.checkNullValueString(chequeDeed.getSpecode().getDefinition()) + Line) + Line) + chequeDeed.getExplanation1() + Line) + chequeDeed.getExplanation2() + Line) + chequeDeed.getExplanation3() + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getDefinition()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getSpecode()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_adres()) + Line) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCity()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getCari_ilce()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelnrs1()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTelNrs2()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getFaxNr()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffCode()) + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getTaxOffice()) + Line) + customerInCharge + Line) + StringUtils.checkNullValueString(customer.getCariTemelBilgi().getEmailAddr()) + Line) + StringUtils.dFormat(debit) + Line) + StringUtils.checkNullValueString(DateAndTimeUtils.getTimeOnly(chequeDeed.getgDate())) + Line) + Line) + Line) + generalTotal + Line) + StringUtils.convertTotal2Text(generalTotal, "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + StringUtils.checkNullValueString(printExtraInfo.getPlasiyerName()) + Line) + StringUtils.checkNullValueString(customer.getCariEkBilgi().getTckimlikno()) + Line) + Line) + getPlasiyerCode(printCheckAndPNotesModel) + Line) + getProjectCode(printCheckAndPNotesModel) + Line;
    }
    static String printFicheLine(NetsisChequeAndDeed netsisChequeAndDeed, ChequeDeedFicheDetail chequeDeedFicheDetail, PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        String printDeedLine;
        String str = ((((((StringUtils.checkNullValueString(chequeDeedFicheDetail.getFicheNo()) + Line) + DateAndTimeUtils.convertDateY(netsisChequeAndDeed.getvADETRH()) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getSpecode()) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getBankBranchName().toString()) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getPayWhere().toString()) + Line) + StringUtils.checkNullValueString(netsisChequeAndDeed.getcEKSERI()) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getDebited().toString()) + Line;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getTip() == NetsisChequeAndDeedType.csMCEK) {
            printDeedLine = printChequeLine(netsisChequeAndDeed, chequeDeedFicheDetail, printCheckAndPNotesModel);
        } else {
            printDeedLine = printDeedLine(netsisChequeAndDeed, chequeDeedFicheDetail, printCheckAndPNotesModel);
        }
        sb.append(printDeedLine);
        return sb.toString() + '\r';
    }
    static String printDeedLine(NetsisChequeAndDeed netsisChequeAndDeed, ChequeDeedFicheDetail chequeDeedFicheDetail, PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        return ((((StringUtils.dFormat(netsisChequeAndDeed.getTutar()) + Line) + DateAndTimeUtils.convertDateY(DateAndTimeUtils.convertSqlDate(printCheckAndPNotesModel.getChequeDeed().getgDate())) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getInCharge().toString()) + Line) + StringUtils.checkNullValueString(StringUtils.convertIntToString((int) StringUtils.convertStringToDouble(chequeDeedFicheDetail.getPul().toString()))) + Line) + getProjectCode(printCheckAndPNotesModel) + Line;
    }
    static String printChequeLine(NetsisChequeAndDeed netsisChequeAndDeed, ChequeDeedFicheDetail chequeDeedFicheDetail, PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        PrintExtraInfo printExtraInfo = getPrintExtraInfo(printCheckAndPNotesModel);
        return ((((getProjectCode(printCheckAndPNotesModel) + Line) + StringUtils.dFormat(netsisChequeAndDeed.getTutar()) + Line) + StringUtils.checkNullValueString(chequeDeedFicheDetail.getBankName().toString()) + Line) + printExtraInfo.getBranchCode() + Line) + StringUtils.checkNullValueString(netsisChequeAndDeed.getcNUMARA()) + Line;
    }
    static void checkSummaryAndFooter(PrintSlipValues printSlipValues, PrintCheckAndPNotesModel printCheckAndPNotesModel, PrintProcess printProcess) {
        if (printSlipValues.getPrintLineCount() == printSlipValues.getLineSize() || printSlipValues.getPrintLineCount() == printSlipValues.getDetailRowCount() * printSlipValues.getSayi()) {
            if (printSlipValues.getPageTotal() < 0.0d) {
                printSlipValues.setPageTotal(0.0d);
            }
            printSlipValues.setNakliyeYekun(printSlipValues.getNakliyeYekun() + printSlipValues.getPageTotal());
            printSlipValues.setSummaryStr("");
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getPageTotal()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getPageTotal()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + getPlasiyerCode(printCheckAndPNotesModel) + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + printCheckAndPNotesModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line);
            printSlipValues.setSummaryStr(printSlipValues.getSummaryStr() + Line);
            printSlipValues.setFooterStr(printFooter(printCheckAndPNotesModel, printSlipValues));
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
    static String printFooter(PrintCheckAndPNotesModel printCheckAndPNotesModel, PrintSlipValues printSlipValues) {
        return ((((StringUtils.dFormat(printSlipValues.getNakliyeYekun()) + Line) + StringUtils.convertTotal2Text(StringUtils.dFormat(printSlipValues.getNakliyeYekun()), "TL", "KR", ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()) + Line) + getPlasiyerCode(printCheckAndPNotesModel) + Line) + printCheckAndPNotesModel.getPrintExtraInfo().get(0).getPlasiyerName() + Line) + Line;
    }
    static boolean isModelLinesEmpty(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        return printCheckAndPNotesModel.getNetsisChequeAndDeedMain() == null || printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar() == null || printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().size() == 0;
    }
    static String getGeneralTotal(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        return isModelLinesEmpty ? "" : StringUtils.dFormat(printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().stream().mapToDouble(new ToDoubleFunction() { // from class: com.proje.mobilesales.core.printutil.NPrintChequeDeedFicheExternalSyntheticLambda0
            @Override // java.util.function.ToDoubleFunction
            public double applyAsDouble(Object obj) {
                return ((NetsisChequeAndDeed) obj).getTutar();
            }
        }).sum());
    }
    static String getPlasiyerCode(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        return isModelLinesEmpty(printCheckAndPNotesModel) ? "" : StringUtils.checkNullValueString(printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().get(0).getPlasiyerKodu());
    }
    static String getProjectCode(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        return isModelLinesEmpty(printCheckAndPNotesModel) ? "" : StringUtils.checkNullValueString(printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar().get(0).getProjeKodu());
    }
    static PrintExtraInfo getPrintExtraInfo(PrintCheckAndPNotesModel printCheckAndPNotesModel) {
        if (printCheckAndPNotesModel == null || printCheckAndPNotesModel.getPrintExtraInfo() == null || printCheckAndPNotesModel.getPrintExtraInfo().size() == 0) {
            PrintExtraInfo printExtraInfo = new PrintExtraInfo();
            printExtraInfo.setPlasiyerName(ErpCreator.getInstance().getmBaseErp().getUser().getName());
            printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(ErpCreator.getInstance().getmBaseErp().getUser().getFirmNr()));
            printExtraInfo.setBranchCode(ErpCreator.getInstance().getmBaseErp().getUser().getPeridodNrInt());
            return printExtraInfo;
        }
        return printCheckAndPNotesModel.getPrintExtraInfo().get(0);
    }
}
