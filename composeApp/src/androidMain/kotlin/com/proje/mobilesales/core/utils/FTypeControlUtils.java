package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.enums.FType;
import com.proje.mobilesales.core.enums.FaturaIrsaliyeTuru;
import com.proje.mobilesales.features.activity.MainActivity;

public class FTypeControlUtils {
    public static FType getMainFType() {
        return MainActivity.fType;
    }
    public static void setMainFType(FType fType) {
        MainActivity.fType = fType;
    }
    public static FaturaIrsaliyeTuru getMainFatirsTuru() {
        return MainActivity.fatirsTuru;
    }
    public static void setMainFatirsTuru(FaturaIrsaliyeTuru faturaIrsaliyeTuru) {
        MainActivity.fatirsTuru = faturaIrsaliyeTuru;
    }
    public static boolean isMainFatIrsTuruNormal() {
        return MainActivity.fatirsTuru == FaturaIrsaliyeTuru.Normal;
    }
    public static boolean isMainFatIrsTuruIade() {
        return MainActivity.fatirsTuru == FaturaIrsaliyeTuru.Iade;
    }
    public static boolean isFTypeOrder(FType fType) {
        return fType == FType.siparis;
    }
    public static boolean isFTypeInvoiceOrRetailInvoiceOrDispatch(FType fType) {
        return fType == FType.fatura || fType == FType.irsaliye || fType == FType.perakendefatura;
    }
    public static boolean isFTypeCashReceipt(FType fType) {
        return fType == FType.nakit;
    }
    public static boolean isFTypeCreditReceipt(FType fType) {
        return fType == FType.kredikarti;
    }
    public static boolean isFTypeCashOrCreditReceipt(FType fType) {
        return fType == FType.nakit || fType == FType.kredikarti;
    }
    public static boolean isFTypeCaseReceipt(FType fType) {
        return fType == FType.nakitkasa;
    }
    public static boolean isFTypeCheckReceipt(FType fType) {
        return fType == FType.cek;
    }
    public static boolean isFTypeDeedReceipt(FType fType) {
        return fType == FType.senet;
    }
    public static boolean isFTypeCheckOrDeedReceipt(FType fType) {
        return fType == FType.cek || fType == FType.senet;
    }
    public static boolean isMainFTypeAll() {
        return MainActivity.fType == FType.all;
    }
    public static boolean isMainFTypeOrder() {
        return MainActivity.fType == FType.siparis;
    }
    public static boolean isMainFTypeDemand() {
        return MainActivity.fType == FType.talep;
    }
    public static boolean isMainFTypeOrderOrDemand() {
        return MainActivity.fType == FType.siparis || MainActivity.fType == FType.talep;
    }
    public static boolean isMainFTypeOneToOne() {
        return MainActivity.fType == FType.birebir;
    }
    public static boolean isMainFTypeInvoice() {
        return MainActivity.fType == FType.fatura;
    }
    public static boolean isMainFTypeCabin() {
        return MainActivity.fType == FType.cabin;
    }
    public static boolean isMainFTypeRetailInvoice() {
        return MainActivity.fType == FType.perakendefatura;
    }
    public static boolean isMainFTypeInvoiceOrRetailInvoice() {
        return MainActivity.fType == FType.fatura || MainActivity.fType == FType.perakendefatura;
    }
    public static boolean isMainFTypeDispatch() {
        return MainActivity.fType == FType.irsaliye;
    }
    public static boolean isMainFTypeDispatchOrOneToOne() {
        return MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.birebir;
    }
    public static boolean isMainFTypeDispatchOrDeliveryNoteOrOneToOne() {
        return MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.sevkirsaliyesi || MainActivity.fType == FType.birebir;
    }
    public static boolean isMainFTypeInvoiceOrRetailInvoiceOrDispatch() {
        return MainActivity.fType == FType.fatura || MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.perakendefatura;
    }
    public static boolean isMainFTypeInvoiceOrRetailInvoiceOrOneToOne() {
        return MainActivity.fType == FType.fatura || MainActivity.fType == FType.birebir || MainActivity.fType == FType.perakendefatura;
    }
    public static boolean isMainFTypeDispatchOrInvoiceOrRetailInvoiceOrOneToOne() {
        return MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.fatura || MainActivity.fType == FType.birebir || MainActivity.fType == FType.perakendefatura;
    }
    public static boolean isMainFTypeCashReceipt() {
        return MainActivity.fType == FType.nakit;
    }
    public static boolean isMainFTypeCreditReceipt() {
        return MainActivity.fType == FType.kredikarti;
    }
    public static boolean isMainFTypeCashOrCreditReceipt() {
        return MainActivity.fType == FType.nakit || MainActivity.fType == FType.kredikarti;
    }
    public static boolean isMainFTypeCaseReceipt() {
        return MainActivity.fType == FType.nakitkasa;
    }
    public static boolean isMainFTypeCheckReceipt() {
        return MainActivity.fType == FType.cek;
    }
    public static boolean isMainFTypeDeedReceipt() {
        return MainActivity.fType == FType.senet;
    }
    public static boolean isMainFTypeCheckOrDeedReceipt() {
        return MainActivity.fType == FType.cek || MainActivity.fType == FType.senet;
    }
    public static boolean isMainFTypeCashOrCreditOrCaseReceipt() {
        return MainActivity.fType == FType.nakit || MainActivity.fType == FType.kredikarti || MainActivity.fType == FType.nakitkasa;
    }
    public static boolean isMainFTypePenetration() {
        return MainActivity.fType == FType.penetrasyon;
    }
    public static boolean isMainFTypeToDo() {
        return MainActivity.fType == FType.Gorev;
    }
    public static boolean isMainFTypeVisit() {
        return MainActivity.fType == FType.ziyaret;
    }
    public static boolean isMainFTypeSalesFiche() {
        return MainActivity.fType == FType.siparis || MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.fatura || MainActivity.fType == FType.birebir || MainActivity.fType == FType.talep || MainActivity.fType == FType.perakendefatura || MainActivity.fType == FType.whtransfer;
    }
    public static boolean isMainFTypeDailyFicheCreateContextMenu() {
        return MainActivity.fType == FType.siparis || MainActivity.fType == FType.irsaliye || MainActivity.fType == FType.fatura || MainActivity.fType == FType.birebir || MainActivity.fType == FType.sevkirsaliyesi || MainActivity.fType == FType.perakendefatura || MainActivity.fType == FType.whtransfer;
    }
    public static boolean isMainFTypeWhTransfer() {
        return MainActivity.fType == FType.whtransfer;
    }
}
