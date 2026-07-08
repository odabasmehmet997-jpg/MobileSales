package com.proje.mobilesales.features.reports.model.enums;

import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ReportTrCodeDesc {
    CASH_COLLECTION (1, R.string.str_cash_collection),
    CASH_PAYMENT (2, R.string.str_cash_payment),
    DEBT_NOTE (3, R.string.str_debt_note),
    CREDIT_NOTE (4, R.string.str_credit_note),
    REMITTANCE_OPERATION (5, R.string.str_remittance_operation),
    EXCHANGE_RATE_DIFFERENCE_OPERATION (6, R.string.str_exchange_rate_difference_operation),
    SPECIAL_OPERATION (12, R.string.str_special_operation),
    OPENING_SLIP (14, R.string.str_opening_slip),
    INCOMING_REMITTANCE (20, R.string.str_incoming_remittance),
    SENT_REMITTANCE (21, R.string.str_sent_remittance),
    FOREIGN_EXCHANGE_CERTIFICATE_PURCHASING (24, R.string.str_foreign_exchange_certificate_purchasing),
    FOREIGN_EXCHANGE_CERTIFICATE_SELLING (25, R.string.str_foreign_exchange_certificate_selling),
    GOODS_PURCHASE_INVOICE (31, R.string.str_goods_purchase_invoice),
    RETAIL_SALES_RETURN_INVOICE (32, R.string.str_retail_sales_return_invoice),
    WHOLESALE_SALES_RETURN_INVOICE (33, R.string.str_wholesale_sales_return_invoice),
    SERVICE_PURCHASE_RETURN_INVOICE (34, R.string.str_service_purchase_return_invoice),
    PURCHASE_PRO_FORMA_INVOICE (35, R.string.str_purchase_pro_forma_invoice),
    BUYING_RETURN_RECEIPT (36, R.string.str_buying_return_receipt),
    RETAIL_SALES_INVOICE (37, R.string.str_retail_sales_invoice),
    WHOLESALE_SALES_INVOICE (38, R.string.str_wholesale_sales_invoice),
    GIVEN_SERVICE_SALES_INVOICE (39, R.string.str_given_service_sales_invoice),
    GIVEN_PRO_FORMA_SALES_INVOICE (40, R.string.str_given_pro_forma_sales_invoice),
    DUE_DATE_DIFF_INVOICE_ISSUED (41, R.string.str_due_date_diff_invoice_issued),
    DUE_DATE_DIFF_INVOICE_RECEIVED (42, R.string.str_due_date_diff_invoice_received),
    PURCHASE_PRICE_DIFFERENCE_INVOICE (43, R.string.str_purchase_price_difference_invoice),
    SALES_PRICE_DIFFERENCE_INVOICE (44, R.string.str_sales_price_difference_invoice),
    RECEIVED_SELF_EMPLOYMENT_RECEIPT (46, R.string.str_received_self_employment_receipt),
    CUSTOMER_RECEIPT (56, R.string.str_customer_receipt),
    CHEQUE_ENTRY (61, R.string.str_cheque_entry),
    DEED_ENTRY (62, R.string.str_deed_entry),
    CHECK_ISSUED_ACCOUNT (63, R.string.str_check_issued_account),
    PROMISSORY_NOTE_ISSUED (64, R.string.str_promissory_note_issued),
    CREDIT_CARD_FICHE (70, R.string.str_credit_card_fiche),
    CREDIT_CARD_RETURN_SLIP (71, R.string.str_credit_card_return_slip),
    FIRM_CREDIT_CARD_FICHE (72, R.string.str_firm_credit_card_fiche),
    FIRM_CREDIT_CARD_RETURN_FICHE (73, R.string.str_firm_credit_card_return_fiche),
    PREPAYMENT_ORDER (81, R.string.str_prepayment_order),
    INVOICE_AMOUNT_ACCOUNT (99, R.string.str_invoice_amount_account),
    REPORT_EXTRACT_TRANSFER (999, R.string.str_report_extract_transfer);


    private static EnumEntries<ReportTrCodeDesc > ENTRIES;

    static {
        final ReportTrCodeDesc[] values = new ReportTrCodeDesc[0];
        final EnumEntries<ReportTrCodeDesc> ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    private final int id;
    private final int stringResId;

    ReportTrCodeDesc(final int i2, final int i3) {
        id = i2;
        stringResId = i3;
    }

    public static EnumEntries<ReportTrCodeDesc> getEntries() {
        return ReportTrCodeDesc.ENTRIES;
    }

    public final int getId() {
        return id;
    }

    public final int getStringResId() {
        return stringResId;
    }
}
