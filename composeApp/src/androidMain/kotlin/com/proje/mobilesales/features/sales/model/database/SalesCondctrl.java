package com.proje.mobilesales.features.sales.model.database;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class SalesCondctrl {
    private int ficheId;
    private int f1276id;
    private int type;
    public SalesCondctrl() {
        this(0, 0, 0, 7, null);
    }
    public static  SalesCondctrl copydefault(final SalesCondctrl salesCondctrl, int i2, int i3, int i4, final int i5, final Object obj) {
        if (0 != (i5 & 1)) {
            i2 = salesCondctrl.f1276id;
        }
        if (0 != (i5 & 2)) {
            i3 = salesCondctrl.type;
        }
        if (0 != (i5 & 4)) {
            i4 = salesCondctrl.ficheId;
        }
        return salesCondctrl.copy(i2, i3, i4);
    }
    public int component1() {
        return f1276id;
    }
    public int component2() {
        return type;
    }
    public int component3() {
        return ficheId;
    }
    public SalesCondctrl copy(final int i2, final int i3, final int i4) {
        return new SalesCondctrl(i2, i3, i4);
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesCondctrl salesCondctrl)) return false;
        return f1276id == salesCondctrl.f1276id && type == salesCondctrl.type && ficheId == salesCondctrl.ficheId;
    }
    public int hashCode() {
        return (((Integer.hashCode(f1276id) * 31) + Integer.hashCode(type)) * 31) + Integer.hashCode(ficheId);
    }
    public String toString() {
        return "SalesCondctrl(id=" + f1276id + ", type=" + type + ", ficheId=" + ficheId + ')';
    }
    public SalesCondctrl(final int i2, final int i3, final int i4) {
        f1276id = i2;
        type = i3;
        ficheId = i4;
    }
    public  SalesCondctrl(final int i2, final int i3, final int i4, final int i5, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i5 & 1) ? 0 : i2, 0 != (i5 & 2) ? 0 : i3, 0 != (i5 & 4) ? 0 : i4);
    }
    public int getId() {
        return f1276id;
    }
    public void setId(final int i2) {
        f1276id = i2;
    }
    public int getType() {
        return type;
    }
    public void setType(final int i2) {
        type = i2;
    }
    public int getFicheId() {
        return ficheId;
    }
    public void setFicheId(final int i2) {
        ficheId = i2;
    }
}
