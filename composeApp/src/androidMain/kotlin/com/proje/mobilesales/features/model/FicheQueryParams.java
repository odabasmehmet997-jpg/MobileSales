package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FicheQueryParams {
    private final int customerId;
    private final String ficheId;
    private final int ficheType;

    public FicheQueryParams() {
        this(null, 0, 0, 7, null);
    }

    public static FicheQueryParams copydefault(final FicheQueryParams ficheQueryParams, String str, int i2, int i3, final int i4, final Object obj) {
        if (0 != (i4 & 1)) {
            str = ficheQueryParams.ficheId;
        }
        if (0 != (i4 & 2)) {
            i2 = ficheQueryParams.customerId;
        }
        if (0 != (i4 & 4)) {
            i3 = ficheQueryParams.ficheType;
        }
        return ficheQueryParams.copy(str, i2, i3);
    }

    public String component1() {
        return ficheId;
    }

    public int component2() {
        return customerId;
    }

    public int component3() {
        return ficheType;
    }

    public FicheQueryParams copy(final String ficheId, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(ficheId, "ficheId");
        return new FicheQueryParams(ficheId, i2, i3);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FicheQueryParams ficheQueryParams)) {
            return false;
        }
        return Intrinsics.areEqual(ficheId, ficheQueryParams.ficheId) && customerId == ficheQueryParams.customerId && ficheType == ficheQueryParams.ficheType;
    }

    public int hashCode() {
        return (((ficheId.hashCode() * 31) + Integer.hashCode(customerId)) * 31) + Integer.hashCode(ficheType);
    }

    public String toString() {
        return "FicheQueryParams(ficheId=" + ficheId + ", customerId=" + customerId + ", ficheType=" + ficheType + ')';
    }

    public FicheQueryParams(final String ficheId, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(ficheId, "ficheId");
        this.ficheId = ficheId;
        customerId = i2;
        ficheType = i3;
    }

    public FicheQueryParams(final String str, final int i2, final int i3, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i4 & 1) ? "0" : str, 0 != (i4 & 2) ? 0 : i2, 0 != (i4 & 4) ? 0 : i3);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFicheId() {
        return ficheId;
    }

    public int getFicheType() {
        return ficheType;
    }
}
