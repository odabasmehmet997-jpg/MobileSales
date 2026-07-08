package com.proje.mobilesales.core.printutil;

import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintBaseModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintCheckAndPNotesModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintMixedReceiptModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSafeDepositModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;

public class NPrintFicheProcess {
    private static final String TAG = "NPrintFicheProcess";
    public static String[] PrintFiche(int i2, int i3, boolean z, boolean z2, String str, int i4, PrintBaseModel printBaseModel) {
        if (i2 == FicheType.INVOICE.getmValue()) {
            return NPrintSlipFiche.PrintSlipFiche(i3, z, z2, str, i4, (PrintSlipModel) printBaseModel);
        }
        if (i2 == FicheType.ORDER.getmValue()) {
            return NPrintSlipFiche.PrintSlipFiche(i3, z, z2, str, i4, (PrintSlipModel) printBaseModel);
        }
        if (i2 == FicheType.DISPATCH.getmValue()) {
            return NPrintSlipFiche.PrintSlipFiche(i3, z, z2, str, i4, (PrintSlipModel) printBaseModel);
        }
        if (i2 == FicheType.CASH.getmValue()) {
            return NPrintMixedReceiptFiche.PrintCashReceiptFiche(i3, z, z2, str, i4, (PrintMixedReceiptModel) printBaseModel);
        }
        if (i2 == FicheType.CREDIT_CART.getmValue()) {
            return NPrintMixedReceiptFiche.PrintCreditReceiptFiche(i3, z, z2, str, i4, (PrintMixedReceiptModel) printBaseModel);
        }
        if (i2 == FicheType.CHEQUE.getmValue() || i2 == FicheType.DEED.getmValue()) {
            return NPrintChequeDeedFiche.PrintChequeDeedFiche(i3, z, z2, str, i4, (PrintCheckAndPNotesModel) printBaseModel);
        }
        if (i2 == FicheType.CASE_CASH.getmValue()) {
            return NPrintSafeDepositFiche.PrintSafeDepositFiche(i3, z, z2, str, i4, (PrintSafeDepositModel) printBaseModel);
        }
        if (i2 == FicheType.WHTRANSFER.getmValue()) {
            return NPrintSlipFiche.PrintSlipFiche(i3, z, z2, str, i4, (PrintSlipModel) printBaseModel);
        }
        return null;
    }
}
