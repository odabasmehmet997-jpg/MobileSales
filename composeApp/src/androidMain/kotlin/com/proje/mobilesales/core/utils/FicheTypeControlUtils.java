package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.enums.FicheType;

public class FicheTypeControlUtils {
    public static boolean isFicheTypeFree(FicheType ficheType) {
        return ficheType == FicheType.FREE;
    }
    public static boolean isFicheTypeOrder(FicheType ficheType) {
        return ficheType == FicheType.ORDER;
    }
    public static boolean isFicheTypeDemand(FicheType ficheType) {
        return ficheType == FicheType.DEMAND;
    }
    public static boolean isFicheTypeOrderOrDemand(FicheType ficheType) {
        return ficheType == FicheType.ORDER || ficheType == FicheType.DEMAND;
    }
    public static boolean isFicheTypeOneToOne(FicheType ficheType) {
        return ficheType == FicheType.ONE_TO_ONE;
    }
    public static boolean isFicheTypeOnlyInvoice(FicheType ficheType) {
        return ficheType == FicheType.INVOICE;
    }
    public static boolean isFicheTypeOnlyRetailInvoice(FicheType ficheType) {
        return ficheType == FicheType.RETAILINVOICE;
    }
    public static boolean isFicheTypeInvoiceOrRetailInvoice(FicheType ficheType) {
        return ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE;
    }
    public static boolean isFicheTypeInvoiceOrRetailInvoiceOrOneToOne(FicheType ficheType) {
        return ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE || ficheType == FicheType.ONE_TO_ONE;
    }
    public static boolean isFicheTypeInvoiceOrDispatch(FicheType ficheType) {
        return ficheType == FicheType.INVOICE || ficheType == FicheType.DISPATCH;
    }
    public static boolean isFicheTypeInvoiceOrRetailInvoiceOrDispatch(FicheType ficheType) {
        return ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE || ficheType == FicheType.DISPATCH;
    }
    public static boolean isFicheTypeDispatch(FicheType ficheType) {
        return ficheType == FicheType.DISPATCH;
    }
    public static boolean isFicheTypeDispatchOrOneToOne(FicheType ficheType) {
        return ficheType == FicheType.DISPATCH || ficheType == FicheType.ONE_TO_ONE;
    }
    public static boolean isFicheTypeCashReceipt(FicheType ficheType) {
        return ficheType == FicheType.CASH;
    }
    public static boolean isFicheTypeCreditReceipt(FicheType ficheType) {
        return ficheType == FicheType.CREDIT_CART;
    }
    public static boolean isFicheTypeCashOrCreditReceipt(FicheType ficheType) {
        return ficheType == FicheType.CASH || ficheType == FicheType.CREDIT_CART;
    }
    public static boolean isFicheTypeCaseReceipt(FicheType ficheType) {
        return ficheType == FicheType.CASE_CASH;
    }
    public static boolean isFicheTypeCheckReceipt(FicheType ficheType) {
        return ficheType == FicheType.CHEQUE;
    }
    public static boolean isFicheTypeDeedReceipt(FicheType ficheType) {
        return ficheType == FicheType.DEED;
    }
    public static boolean isFicheTypeCheckOrDeedReceipt(FicheType ficheType) {
        return ficheType == FicheType.CHEQUE || ficheType == FicheType.DEED;
    }
    public static boolean isFicheTypeDeliveryNote(FicheType ficheType) {
        return ficheType == FicheType.DELIVERY_NOTE;
    }
    public static boolean isFicheTypeInvoiceOrRetailInvoiceOrDispatchOrOrder(FicheType ficheType) {
        return ficheType == FicheType.INVOICE || ficheType == FicheType.RETAILINVOICE || ficheType == FicheType.DISPATCH || ficheType == FicheType.ORDER;
    }
    public static boolean isFicheTypeWhTransfer(FicheType ficheType) {
        return ficheType == FicheType.WHTRANSFER;
    }
}
