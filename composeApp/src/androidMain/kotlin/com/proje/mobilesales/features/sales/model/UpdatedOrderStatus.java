package com.proje.mobilesales.features.sales.model;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.enums.UpdatedOrderFicheStatus;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class UpdatedOrderStatus {

    private String ficheNo;
    private int logicalRef;
    private int status;

    public UpdatedOrderStatus() {
        this(0, 0, null, 7, null);
    }

    public static /* synthetic */ UpdatedOrderStatus copydefault(UpdatedOrderStatus updatedOrderStatus, int r1, int r2, String str, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = updatedOrderStatus.status;
        }
        if ((r4 & 2) != 0) {
            r2 = updatedOrderStatus.logicalRef;
        }
        if ((r4 & 4) != 0) {
            str = updatedOrderStatus.ficheNo;
        }
        return updatedOrderStatus.copy(r1, r2, str);
    }

    public int component1() {
        return this.status;
    }

    public int component2() {
        return this.logicalRef;
    }

    public String component3() {
        return this.ficheNo;
    }

    public UpdatedOrderStatus copy(int r1, int r2, String ficheNo) {
        Intrinsics.checkNotNullParameter(ficheNo, "ficheNo");
        return new UpdatedOrderStatus(r1, r2, ficheNo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UpdatedOrderStatus updatedOrderStatus)) {
            return false;
        }
        return this.status == updatedOrderStatus.status && this.logicalRef == updatedOrderStatus.logicalRef && Intrinsics.areEqual(this.ficheNo, updatedOrderStatus.ficheNo);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.status) * 31) + Integer.hashCode(this.logicalRef)) * 31) + this.ficheNo.hashCode();
    }

    public String toString() {
        return "UpdatedOrderStatus(status=" + this.status + ", logicalRef=" + this.logicalRef + ", ficheNo=" + this.ficheNo + ')';
    }

    public UpdatedOrderStatus(int r2, int r3, String ficheNo) {
        Intrinsics.checkNotNullParameter(ficheNo, "ficheNo");
        this.status = r2;
        this.logicalRef = r3;
        this.ficheNo = ficheNo;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int r1) {
        this.status = r1;
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int r1) {
        this.logicalRef = r1;
    }

    public  UpdatedOrderStatus(int r2, int r3, String str, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this((r5 & 1) != 0 ? 0 : r2, (r5 & 2) != 0 ? 0 : r3, (r5 & 4) != 0 ? "" : str);
    }

    public String getFicheNo() {
        return this.ficheNo;
    }

    public void setFicheNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ficheNo = str;
    }

    public String getOrderFicheStatus() {
        return UpdatedOrderFicheStatus.Companion.getOrderStatus(this.status);
    }
}
