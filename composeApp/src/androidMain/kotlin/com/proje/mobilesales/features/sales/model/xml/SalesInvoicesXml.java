package com.proje.mobilesales.features.sales.model.xml;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public record SalesInvoicesXml(String guid, List<SalesTransactionXml> salesTransactions) {
    public SalesInvoicesXml() {
        this(null, null, 3, null);
    }

    public static SalesInvoicesXml copydefault(SalesInvoicesXml salesInvoicesXml, String str, List<SalesTransactionXml> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = salesInvoicesXml.guid;
        }
        if ((i2 & 2) != 0) {
            list = salesInvoicesXml.salesTransactions;
        }
        return salesInvoicesXml.copy(str, list);
    }

    public String component1() {
        return this.guid;
    }

    public List<SalesTransactionXml> component2() {
        return this.salesTransactions;
    }

    public SalesInvoicesXml copy(String str, List<SalesTransactionXml> list) {
        Intrinsics.checkNotNullParameter(str, "guid");
        return new SalesInvoicesXml(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesInvoicesXml salesInvoicesXml)) {
            return false;
        }
        return Intrinsics.areEqual(this.guid, salesInvoicesXml.guid) && Intrinsics.areEqual(this.salesTransactions, salesInvoicesXml.salesTransactions);
    }

    public String toString() {
        return "SalesInvoicesXml(guid=" + this.guid + ", salesTransactions=" + this.salesTransactions + ')';
    }

    public SalesInvoicesXml {
        Intrinsics.checkNotNullParameter(guid, "guid");
    }

    public SalesInvoicesXml(String str, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : list);
    }
}
