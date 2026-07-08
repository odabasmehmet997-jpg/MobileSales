package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class EmailTemplateType extends Enum<EmailTemplateType> {
    private static final  EnumEntries ENTRIES;
    private static final  EmailTemplateType[] VALUES;
    private int mResId;
    private int mValue;
    public static final EmailTemplateType Unknown = new EmailTemplateType("Unknown", 0, 0, 0);
    public static final EmailTemplateType Order = new EmailTemplateType("Order", 1, 1, R.string.str_order);
    public static final EmailTemplateType Dispatch = new EmailTemplateType("Dispatch", 2, 2, R.string.str_dispatch);
    public static final EmailTemplateType Invoice = new EmailTemplateType("Invoice", 3, 3, R.string.str_invoice);
    public static final EmailTemplateType CashCollection = new EmailTemplateType("CashCollection", 4, 4, R.string.str_statement_cash_collection);
    public static final EmailTemplateType CreditCollection = new EmailTemplateType("CreditCollection", 5, 5, R.string.str_statement_credit_card_fiche);
    public static final EmailTemplateType CheckCollection = new EmailTemplateType("CheckCollection", 6, 6, R.string.str_statement_check_collection);
    public static final EmailTemplateType BillCollection = new EmailTemplateType("BillCollection", 7, 7, R.string.str_statement_payroll_note);
    public static final EmailTemplateType CaseCollection = new EmailTemplateType("CaseCollection", 8, 8, R.string.str_statement_safe_deposit);

    private static EmailTemplateType[] values() {
        return new EmailTemplateType[]{Unknown, Order, Dispatch, Invoice, CashCollection, CreditCollection, CheckCollection, BillCollection, CaseCollection};
    }

    public static EnumEntries<EmailTemplateType> getEntries() {
        return ENTRIES;
    }

    public static EmailTemplateType valueOf(String str) {
        return Enum.valueOf(EmailTemplateType.class, str);
    }

    public static EmailTemplateType[] values() {
        return VALUES.clone();
    }

    private EmailTemplateType(String str, @StringRes int i2, int i3, int i4) {
        super(str,i2);
        this.mValue = i3;
        setmResId(i4);
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        EmailTemplateType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmValue() {
        return this.mValue;
    }

    public void setmValue(int i2) {
        this.mValue = i2;
    }

    public int getmResId() {
        return this.mResId;
    }

    private void setmResId(int i2) {
        this.mResId = i2;
    }

    public  int ordinal() {
        return 0;
    }
}
