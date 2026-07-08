package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;

public final class ClCardFicheType  {
    private static final EnumEntries ENTRIES;
    private static final ClCardFicheType[] VALUES;
    private int type;
    public static final ClCardFicheType CASH_RECEIPT = new ClCardFicheType(1);
    public static final ClCardFicheType DEBT_DISCHARGING = new ClCardFicheType(2);
    public static final ClCardFicheType DEBIT_RECEIPT = new ClCardFicheType(3);
    public static final ClCardFicheType DUE_RECEIPT = new ClCardFicheType(4);
    public static final ClCardFicheType VIRMAN_FICHE = new ClCardFicheType(5);
    public static final ClCardFicheType EXCHANGE_DIFF_FICHE = new ClCardFicheType(6);
    public static final ClCardFicheType PRIVATE_FICHE = new ClCardFicheType(12);
    public static final ClCardFicheType OPENING_FICHE = new ClCardFicheType(14);
    public static final ClCardFicheType GIVEN_DELAY_INTEREST_INVOICE = new ClCardFicheType(41);
    public static final ClCardFicheType RECEIVING_DELAY_INTEREST_INVOICE = new ClCardFicheType(42);
    public static final ClCardFicheType CREDIT_CARD_RECEIPT = new ClCardFicheType(70);

    private static   ClCardFicheType[] values() {
        return new ClCardFicheType[]{CASH_RECEIPT, DEBT_DISCHARGING, DEBIT_RECEIPT, DUE_RECEIPT, VIRMAN_FICHE, EXCHANGE_DIFF_FICHE, PRIVATE_FICHE, OPENING_FICHE, GIVEN_DELAY_INTEREST_INVOICE, RECEIVING_DELAY_INTEREST_INVOICE, CREDIT_CARD_RECEIPT};
    }
    public static EnumEntries getEntries() {
        return ENTRIES;
    }
    private ClCardFicheType(int r3) {
        this.type = r3;
    }
    public   int getType() {
        return this.type;
    }
    public   void setType(int r1) {
        this.type = r1;
    }
    static {
        VALUES = values();
        ENTRIES = enumEntries();
    }
    private static EnumEntries enumEntries() {
        return null;
    }
}
