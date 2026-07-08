package com.proje.mobilesales.features.sales.model.xml;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public record SalesOrdersXml(String guid, List<SalesTransactionXml> salesTransactions) {

    public SalesOrdersXml() {
        this(null, null, 3, null);
    }

    public static SalesOrdersXml copydefault(SalesOrdersXml salesOrdersXml, String str, List<SalesTransactionXml> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = salesOrdersXml.guid;
        }
        if ((i2 & 2) != 0) {
            list = salesOrdersXml.salesTransactions;
        }
        return salesOrdersXml.copy(str, list);
    }

    public String component1() {
        return this.guid;
    }

    public List<SalesTransactionXml> component2() {
        return this.salesTransactions;
    }

    public SalesOrdersXml copy(String str, List<SalesTransactionXml> list) {
        Intrinsics.checkNotNullParameter(str, "guid");
        return new SalesOrdersXml(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesOrdersXml salesOrdersXml)) {
            return false;
        }
        return Intrinsics.areEqual(this.guid, salesOrdersXml.guid) && Intrinsics.areEqual(this.salesTransactions, salesOrdersXml.salesTransactions);
    }

    public int hashCode() {
        String str = this.guid;
        int i2 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        List<SalesTransactionXml> list = this.salesTransactions;
        if (list != null) {
            i2 = list.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        return "SalesOrdersXml(guid=" + this.guid + ", salesTransactions=" + this.salesTransactions + ')';
    }

    public SalesOrdersXml(String str, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : list);
    }
}
