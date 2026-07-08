package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.SalesType;
import kotlin.jvm.internal.Intrinsics;

public record SerialLotListQueryParam(int whNr, int itemRef, SalesType salesType, String searchKeyword,
                                      boolean forBarcode) {
    public static  SerialLotListQueryParam copydefault(final SerialLotListQueryParam serialLotListQueryParam, int i2, int i3, SalesType salesType, String str, boolean z, final int i4, final Object obj) {
        if (0 != (i4 & 1)) {
            i2 = serialLotListQueryParam.whNr;
        }
        if (0 != (i4 & 2)) {
            i3 = serialLotListQueryParam.itemRef;
        }
        final int i5 = i3;
        if (0 != (i4 & 4)) {
            salesType = serialLotListQueryParam.salesType;
        }
        final SalesType salesType2 = salesType;
        if (0 != (i4 & 8)) {
            str = serialLotListQueryParam.searchKeyword;
        }
        final String str2 = str;
        if (0 != (i4 & 16)) {
            z = serialLotListQueryParam.forBarcode;
        }
        return serialLotListQueryParam.copy(i2, i5, salesType2, str2, z);
    }

    public int component1() {
        return whNr;
    }

    public int component2() {
        return itemRef;
    }

    public SalesType component3() {
        return salesType;
    }

    public String component4() {
        return searchKeyword;
    }

    public boolean component5() {
        return forBarcode;
    }

    public SerialLotListQueryParam copy(final int i2, final int i3, final SalesType salesType, final String searchKeyword, final boolean z) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(searchKeyword, "searchKeyword");
        return new SerialLotListQueryParam(i2, i3, salesType, searchKeyword, z);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerialLotListQueryParam serialLotListQueryParam)) {
            return false;
        }
        return whNr == serialLotListQueryParam.whNr && itemRef == serialLotListQueryParam.itemRef && salesType == serialLotListQueryParam.salesType && Intrinsics.areEqual(searchKeyword, serialLotListQueryParam.searchKeyword) && forBarcode == serialLotListQueryParam.forBarcode;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(whNr) * 31) + Integer.hashCode(itemRef)) * 31) + salesType.hashCode()) * 31) + searchKeyword.hashCode()) * 31) + Boolean.hashCode(forBarcode);
    }

    public String toString() {
        return "SerialLotListQueryParam(whNr=" + whNr + ", itemRef=" + itemRef + ", salesType=" + salesType + ", searchKeyword=" + searchKeyword + ", forBarcode=" + forBarcode + ')';
    }

    public SerialLotListQueryParam {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(searchKeyword, "searchKeyword");
    }
}
