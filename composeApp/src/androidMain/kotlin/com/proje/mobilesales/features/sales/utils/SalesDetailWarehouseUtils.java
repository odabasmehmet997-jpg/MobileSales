package com.proje.mobilesales.features.sales.utils;

import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.dbmodel.InvoiceDetail;
import com.proje.mobilesales.features.dbmodel.OrderDetail;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.repository.SalesDetailRepository;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailViewModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class SalesDetailWarehouseUtils {
    public static final SalesDetailWarehouseUtils INSTANCE = new SalesDetailWarehouseUtils();
    private static final SalesDetailRepository repository;
    private static final SalesDetailViewModel viewModel;
    private SalesDetailWarehouseUtils() {
    }
    static {
        SalesDetailRepository salesDetailRepository = new SalesDetailRepository();
        repository = salesDetailRepository;
        viewModel = new SalesDetailViewModel(salesDetailRepository);
    }
    public static String getDetailWarehouseName(String entityType, SalesDetail salesDetail) {
        List emptyList;
        String warehouseName;
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        if (Intrinsics.areEqual(entityType, "InvoiceDetail")) {
            ISqlHelper logoSqlHelper = viewModel.getBaseErp().getLogoSqlHelper();
            Intrinsics.checkNotNull(salesDetail);
            emptyList = logoSqlHelper.getTable(InvoiceDetail.class, "LOGICALREF=?", new String[]{String.valueOf(salesDetail.getLogicalRef())});
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.InvoiceDetail>");
        } else if (Intrinsics.areEqual(entityType, "OrderDetail")) {
            ISqlHelper logoSqlHelper2 = viewModel.getBaseErp().getLogoSqlHelper();
            Intrinsics.checkNotNull(salesDetail);
            emptyList = logoSqlHelper2.getTable(OrderDetail.class, "LOGICALREF=?", new String[]{String.valueOf(salesDetail.getLogicalRef())});
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.OrderDetail>");
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        if (emptyList.isEmpty()) {
            return "";
        }
        if (Intrinsics.areEqual(entityType, "InvoiceDetail")) {
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.InvoiceDetail>");
            warehouseName = ((InvoiceDetail) emptyList.get(0)).getWarehouseName();
            if (warehouseName == null) {
                return "";
            }
        } else {
            if (!Intrinsics.areEqual(entityType, "OrderDetail")) {
                return "";
            }
            Intrinsics.checkNotNull(emptyList, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.dbmodel.OrderDetail>");
            warehouseName = ((OrderDetail) emptyList.get(0)).getWarehouseName();
            if (warehouseName == null) {
                return "";
            }
        }
        return warehouseName;
    }
}
