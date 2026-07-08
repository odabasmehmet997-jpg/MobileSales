package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.base.BaseSelectResult;
 public interface PrintQuerable<T extends BaseSelectResult> {
    T getPrintCaseDetail(int i2);

    T getPrintCaseHeader(int i2);

    T getPrintCashDetail(int i2);

    T getPrintCashHeader(int i2);

    T getPrintChequeDetail(int i2);

    T getPrintChequeHeader(int i2);

    T getPrintCreditCardDetail(int i2);

    T getPrintCreditCardHeader(int i2);

    T getPrintDispatchDetail(int i2, int i3);

    T getPrintDispatchDiscount(int i2);

    T getPrintDispatchHeader(int i2);

    T getPrintInvoiceDetail(int i2, int i3);

    T getPrintInvoiceDiscount(int i2);

    T getPrintInvoiceHeader(int i2);

    T getPrintItemStock(int i2, int i3);

    T getPrintOrderDetail(int i2, int i3);

    T getPrintOrderDiscount(int i2);

    T getPrintOrderHeader(int i2);

    T getPrintWhTransferDetail(int i2, int i3);

    T getPrintWhTransferHeader(int i2);
}
