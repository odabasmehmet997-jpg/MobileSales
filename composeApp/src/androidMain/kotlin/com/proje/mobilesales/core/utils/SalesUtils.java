package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesShort;
import kotlin.jvm.internal.Intrinsics;

public final class SalesUtils {
    public static final SalesUtils INSTANCE = new SalesUtils();
    private SalesUtils() {
    }
    public static boolean isSalesTypeFree(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.FREE;
    }
    public static boolean isSalesTypeOrder(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER;
    }
    public static boolean isSalesTypeDemand(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DEMAND;
    }
    public static boolean isSalesTypeOrderOrDemand(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER || salesType == SalesType.DEMAND;
    }
    public static boolean isSalesTypeOneToOne(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesTypeOnlyInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE;
    }
    public static boolean isSalesTypeOnlyReturnInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_INVOICE;
    }
    public static boolean isSalesTypeOnlyRetailInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETAIL_INVOICE;
    }
    public static boolean isSalesTypeOnlyRetailReturnInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETAIL_RETURN_INVOICE;
    }
    public static boolean isSalesTypeInvoiceOrRetailInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETAIL_INVOICE;
    }
    public static boolean isSalesTypeOrderOrInvoiceOrRetailInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.ORDER;
    }
    public static boolean isSalesTypeRetailInvoiceOrRetailReturnInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE;
    }
    public static boolean isSalesTypeReturnInvoiceOrRetailReturnInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE;
    }
    public static boolean isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE;
    }
    public static boolean isSalesTypeInvoiceOrRetailInvoiceOrOneToOne(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInvOrOneToOne(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesTypeInvoiceOrReturnInvoice(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE;
    }
    public static boolean isSalesTypeInvoiceOrDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.DISPATCH;
    }
    public static boolean isSalesTypeInvoiceOrRetailInvoiceOrDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.DISPATCH;
    }
    public static boolean isSalesTypeReturnInvoiceOrReturnDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETURN_DISPATCH;
    }
    public static boolean isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.RETURN_DISPATCH;
    }
    public static boolean m571xd12e7b8b(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesTypeDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DISPATCH;
    }
    public static boolean isSalesTypeReturnDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.RETURN_DISPATCH;
    }
    public static boolean isSalesTypeDispatchOrReturnDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH;
    }
    public static boolean isSalesTypeDispatchOrReturnDispatchOrOneToOne(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesTypeWhTransfer(SalesType salesType) {
        return salesType == SalesType.WHTRANSFER;
    }
    public static boolean isSalesTypeDemandOrWhTransfer(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DEMAND || salesType == SalesType.WHTRANSFER;
    }
    public static boolean isSalesTypeOrderOrDemandOrWhTransfer(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER || salesType == SalesType.DEMAND || salesType == SalesType.WHTRANSFER;
    }
    public static boolean isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatchOrOneToOne(SalesType salesType) {
        return false;
    }
    public static boolean m570x90f54721(SalesType salesType) {
        return false;
    }
    public boolean isSalesOrOrder(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return isSales(salesType) || isSalesTypeOrder(salesType);
    }
    public boolean isSales(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesOrderFicheTransferMenu(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER || salesType == SalesType.DEMAND || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ONE_TO_ONE_CHANGE;
    }
    public static boolean isSalesSeriCheckLocationTracking(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ONE_TO_ONE_CHANGE || salesType == SalesType.WHTRANSFER;
    }
    public static boolean isVaryantProductAlert(SalesDetail salesDetail, ErpType erpType) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        SalesType salesType = salesDetail.getmSalesType();
        return (salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH || salesType == SalesType.ORDER || salesType == SalesType.ONE_TO_ONE_CHANGE) && salesDetail.isHasVariant() && salesDetail.getVariant().getLogicalRef() == -1;
    }
    public static boolean showTransferOption(ErpType erpType, SalesShort salesShort) {
        Intrinsics.checkNotNullParameter(erpType, "erpType");
        Intrinsics.checkNotNullParameter(salesShort, "salesShort");
        if (erpType != ErpType.TIGER) {
            return !(salesShort.isCancel() || salesShort.isTransfer()) || salesShort.isEdit();
        }
        SalesType fromSalesType = SalesType.Companion.fromSalesType(salesShort.getType());
        if (fromSalesType == SalesType.INVOICE || fromSalesType == SalesType.RETURN_INVOICE || fromSalesType == SalesType.RETAIL_INVOICE || fromSalesType == SalesType.RETAIL_RETURN_INVOICE || fromSalesType == SalesType.DISPATCH || fromSalesType == SalesType.RETURN_DISPATCH || fromSalesType == SalesType.ORDER) {
            return !salesShort.isCancel() || salesShort.isEdit();
        } else return (!salesShort.isCancel() && !salesShort.isTransfer()) || salesShort.isEdit();
    }
    public static boolean isSalesTypeOrderOrInvoiceOrRetailInvoiceOrDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER || salesType == SalesType.INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.DISPATCH;
    }
    public static boolean isSalesTypeOrderOrInvoiceOrDispatch(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.ORDER || salesType == SalesType.INVOICE || salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.DISPATCH || salesType == SalesType.RETURN_DISPATCH;
    }
    public static boolean isSalesTypeDispatchOrWhTransfer(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return salesType == SalesType.DISPATCH || salesType == SalesType.WHTRANSFER;
    }
}
