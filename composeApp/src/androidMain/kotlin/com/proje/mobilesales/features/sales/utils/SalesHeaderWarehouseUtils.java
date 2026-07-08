package com.proje.mobilesales.features.sales.utils;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.WhTransfer;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.repository.SalesNewRepository;
import com.proje.mobilesales.features.sales.view.newadd.SalesNewViewModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class SalesHeaderWarehouseUtils {
    public static final SalesHeaderWarehouseUtils INSTANCE = new SalesHeaderWarehouseUtils();
    private static final SalesNewRepository repository;
    private static final SalesNewViewModel viewModel;
    private SalesHeaderWarehouseUtils() {
    }
    static {
        SalesNewRepository salesNewRepository = new SalesNewRepository();
        repository = salesNewRepository;
        viewModel = new SalesNewViewModel(salesNewRepository);
    }
    public static String getWarehouseName(String entityType, Sales sales) {
        List table;
        String wareHouseName;
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        int hashCode = entityType.hashCode();
        if (hashCode == -1862949380) {
            if (entityType.equals("WhTransfer")) {
                ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
                Intrinsics.checkNotNull(sales);
                table = logoSqlHelper.getTable(WhTransfer.class, "LOGICALREF=?", new String[]{String.valueOf(sales.getLogicalRef())});
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.WhTransfer>");
            }
            table = CollectionsKt.emptyList();
        } else if (hashCode == -670115059) {
            if (entityType.equals("Invoice")) {
                ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
                Intrinsics.checkNotNull(sales);
                table = logoSqlHelper2.getTable(Invoice.class, "LOGICALREF=?", new String[]{String.valueOf(sales.getLogicalRef())});
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
            }
            table = CollectionsKt.emptyList();
        } else {
            if (hashCode == 76453678 && entityType.equals("Order")) {
                ISqlHelper logoSqlHelper3 = viewModel.getBaseErp().getLogoSqlHelper();
                Intrinsics.checkNotNull(sales);
                table = logoSqlHelper3.getTable(Order.class, "LOGICALREF=?", new String[]{String.valueOf(sales.getLogicalRef())});
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
            }
            table = CollectionsKt.emptyList();
        }
        if (table.isEmpty()) {
            return "";
        }
        int hashCode2 = entityType.hashCode();
        if (hashCode2 != -1862949380) {
            if (hashCode2 != -670115059) {
                if (hashCode2 != 76453678 || !entityType.equals("Order")) {
                    return "";
                }
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
                wareHouseName = ((Order) table.get(0)).getWareHouseName();
                if (wareHouseName == null) {
                    return "";
                }
            } else {
                if (!entityType.equals("Invoice")) {
                    return "";
                }
                Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
                wareHouseName = ((Invoice) table.get(0)).getWareHouseName();
                if (wareHouseName == null) {
                    return "";
                }
            }
        } else {
            if (!entityType.equals("WhTransfer")) {
                return "";
            }
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.WhTransfer>");
            wareHouseName = ((WhTransfer) table.get(0)).getWareHouseName();
            if (wareHouseName == null) {
                return "";
            }
        }
        return wareHouseName;
    }
    public static String getSourceWarehouseName(String entityType, Sales sales) {
        List emptyList;
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        if (Intrinsics.areEqual(entityType, "Order")) {
            ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            Intrinsics.checkNotNull(sales);
            emptyList = logoSqlHelper.getTable(Order.class, "LOGICALREF=?", new String[]{String.valueOf(sales.getLogicalRef())});
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        if (emptyList.isEmpty()) {
            return "";
        }
        Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Order>");
        String sourceWareHouseName = ((Order) emptyList.get(0)).getSourceWareHouseName();
        return sourceWareHouseName == null ? "" : sourceWareHouseName;
    }
    public static String getReturnWarehouseName(String entityType, Sales sales) {
        List emptyList;
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        if (Intrinsics.areEqual(entityType, "Invoice")) {
            ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            Intrinsics.checkNotNull(sales);
            emptyList = logoSqlHelper.getTable(Invoice.class, "LOGICALREF=?", new String[]{String.valueOf(sales.getLogicalRef())});
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        if (emptyList.isEmpty()) {
            return "";
        }
        Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.Invoice>");
        String returnWareHouseName = ((Invoice) emptyList.get(0)).getReturnWareHouseName();
        return returnWareHouseName == null ? "" : returnWareHouseName;
    }
}
