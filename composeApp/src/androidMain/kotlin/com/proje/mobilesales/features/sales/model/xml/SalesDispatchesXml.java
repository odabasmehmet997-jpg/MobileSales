package com.proje.mobilesales.features.sales.model.xml;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public record SalesDispatchesXml(String guid, List<SalesTransactionXml> salesTransactions) {
    public SalesDispatchesXml() {
        this(null, null, 3, null);
    }

    public static SalesDispatchesXml copydefault(SalesDispatchesXml salesDispatchesXml, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = salesDispatchesXml.guid;
        }
        if ((i2 & 2) != 0) {
            list = salesDispatchesXml.salesTransactions;
        }
        return salesDispatchesXml.copy(str, list);
    }

    public String component1() {
        return this.guid;
    }

    public List<SalesTransactionXml> component2() {
        return this.salesTransactions;
    }

    public SalesDispatchesXml copy(String str, List<SalesTransactionXml> list) {
        Intrinsics.checkNotNullParameter(str, "guid");
        return new SalesDispatchesXml(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesDispatchesXml salesDispatchesXml)) {
            return false;
        }
        return Intrinsics.areEqual(this.guid, salesDispatchesXml.guid)
                && Intrinsics.areEqual(this.salesTransactions, salesDispatchesXml.salesTransactions);
    }

    public String toString() {
        return "SalesDispatchesXml(guid=" + this.guid + ", salesTransactions=" + this.salesTransactions + ')';
    }

    public SalesDispatchesXml {
        Intrinsics.checkNotNullParameter(guid, "guid");
    }

    public SalesDispatchesXml(String str, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : list);
    }
}
