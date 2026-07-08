package com.proje.mobilesales.features.sales.model.xml;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public record SalesXml(String guid, List<SalesTransactionXml> salesTransactions) {
    public static SalesXml copydefault(SalesXml salesXml, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = salesXml.guid;
        }
        if ((i2 & 2) != 0) {
            list = salesXml.salesTransactions;
        }
        return salesXml.copy(str, list);
    }

    public String component1() {
        return this.guid;
    }

    public List<SalesTransactionXml> component2() {
        return this.salesTransactions;
    }

    public SalesXml copy(String guid, List<SalesTransactionXml> salesTransactions) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        Intrinsics.checkNotNullParameter(salesTransactions, "salesTransactions");
        return new SalesXml(guid, salesTransactions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesXml salesXml)) {
            return false;
        }
        return Intrinsics.areEqual(this.guid, salesXml.guid) && Intrinsics.areEqual(this.salesTransactions, salesXml.salesTransactions);
    }

    public String toString() {
        return "SalesXml(guid=" + this.guid + ", salesTransactions=" + this.salesTransactions + ')';
    }

    public SalesXml {
        Intrinsics.checkNotNullParameter(guid, "guid");
        Intrinsics.checkNotNullParameter(salesTransactions, "salesTransactions");
    }
}
