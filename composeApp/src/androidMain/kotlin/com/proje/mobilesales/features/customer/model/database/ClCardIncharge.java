package com.proje.mobilesales.features.customer.model.database;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ClCardIncharge {
    private int active;
    private String clCode;
    private double cmDate;
    private String createdDate;
    private String definition;
    private String eMail;
    private String inCharge;
    private String processType;
    private final int recId;
    private String tel;
    private String telCode;
    public ClCardIncharge() {
        this(0, null, null, null, null, null, null, null, 0.0d, 0, null, 2047, null);
    }
    private int component1() {
        return this.recId;
    }
    public int component10() {
        return this.active;
    }
    public String component11() {
        return this.processType;
    }
    public String component2() {
        return this.inCharge;
    }
    public String component3() {
        return this.definition;
    }
    public String component4() {
        return this.clCode;
    }
    public String component5() {
        return this.eMail;
    }
    public String component6() {
        return this.tel;
    }
    public String component7() {
        return this.telCode;
    }
    public String component8() {
        return this.createdDate;
    }
    public double component9() {
        return this.cmDate;
    }
    public ClCardIncharge copy(int i2, String inCharge, String definition, String clCode, String eMail, String tel, String telCode, String createdDate, double d2, int i3, String processType) {
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        Intrinsics.checkNotNullParameter(eMail, "eMail");
        Intrinsics.checkNotNullParameter(tel, "tel");
        Intrinsics.checkNotNullParameter(telCode, "telCode");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(processType, "processType");
        return new ClCardIncharge(i2, inCharge, definition, clCode, eMail, tel, telCode, createdDate, d2, i3, processType);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClCardIncharge clCardIncharge)) {
            return false;
        }
        return this.recId == clCardIncharge.recId && Intrinsics.areEqual(this.inCharge, clCardIncharge.inCharge) && Intrinsics.areEqual(this.definition, clCardIncharge.definition) && Intrinsics.areEqual(this.clCode, clCardIncharge.clCode) && Intrinsics.areEqual(this.eMail, clCardIncharge.eMail) && Intrinsics.areEqual(this.tel, clCardIncharge.tel) && Intrinsics.areEqual(this.telCode, clCardIncharge.telCode) && Intrinsics.areEqual(this.createdDate, clCardIncharge.createdDate) && Double.compare(this.cmDate, clCardIncharge.cmDate) == 0 && this.active == clCardIncharge.active && Intrinsics.areEqual(this.processType, clCardIncharge.processType);
    }

    public int hashCode() {
        return (((((((((((((((((((Integer.hashCode(this.recId) * 31) + this.inCharge.hashCode()) * 31) + this.definition.hashCode()) * 31) + this.clCode.hashCode()) * 31) + this.eMail.hashCode()) * 31) + this.tel.hashCode()) * 31) + this.telCode.hashCode()) * 31) + this.createdDate.hashCode()) * 31) + Double.hashCode(this.cmDate)) * 31) + Integer.hashCode(this.active)) * 31) + this.processType.hashCode();
    }

    public String toString() {
        return "ClCardIncharge(recId=" + this.recId + ", inCharge=" + this.inCharge + ", definition=" + this.definition + ", clCode=" + this.clCode + ", eMail=" + this.eMail + ", tel=" + this.tel + ", telCode=" + this.telCode + ", createdDate=" + this.createdDate + ", cmDate=" + this.cmDate + ", active=" + this.active + ", processType=" + this.processType + ')';
    }

    public ClCardIncharge(int i2, String inCharge, String definition, String clCode, String eMail, String tel, String telCode, String createdDate, double d2, int i3, String processType) {
        Intrinsics.checkNotNullParameter(inCharge, "inCharge");
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        Intrinsics.checkNotNullParameter(eMail, "eMail");
        Intrinsics.checkNotNullParameter(tel, "tel");
        Intrinsics.checkNotNullParameter(telCode, "telCode");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(processType, "processType");
        this.recId = i2;
        this.inCharge = inCharge;
        this.definition = definition;
        this.clCode = clCode;
        this.eMail = eMail;
        this.tel = tel;
        this.telCode = telCode;
        this.createdDate = createdDate;
        this.cmDate = d2;
        this.active = i3;
        this.processType = processType;
    }
    public ClCardIncharge(int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, double d2, int i3, String str8, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i2, (i4 & 2) != 0 ? "" : str, (i4 & 4) != 0 ? "" : str2, (i4 & 8) != 0 ? "" : str3, (i4 & 16) != 0 ? "" : str4, (i4 & 32) != 0 ? "" : str5, (i4 & 64) != 0 ? "" : str6, (i4 & 128) != 0 ? "" : str7, (i4 & 256) != 0 ? 0.0d : d2, (i4 & 512) == 0 ? i3 : 0, (i4 & 1024) == 0 ? str8 : "");
    }

    public String getInCharge() {
        return this.inCharge;
    }

    public void setInCharge(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.inCharge = str;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.definition = str;
    }

    public String getClCode() {
        return this.clCode;
    }

    public void setClCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clCode = str;
    }

    public String getEMail() {
        return this.eMail;
    }

    public void setEMail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.eMail = str;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tel = str;
    }

    public String getTelCode() {
        return this.telCode;
    }

    public void setTelCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.telCode = str;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.createdDate = str;
    }

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d2) {
        this.cmDate = d2;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int i2) {
        this.active = i2;
    }

    public String getProcessType() {
        return this.processType;
    }

    public void setProcessType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.processType = str;
    }
    public String getTelNumber() {
        if (this.telCode.length() == 0) {
            return this.tel;
        }
        return this.telCode + " - " + this.tel;
    }
    public void convertDbType(CustomerNew customerNew) {
        Intrinsics.checkNotNullParameter(customerNew, "customerNew");
        this.clCode = String.valueOf(customerNew.getCode());
        this.inCharge = String.valueOf(customerNew.getInCharge());
        this.definition = String.valueOf(customerNew.getInChargeDefinition());
        this.eMail = String.valueOf(customerNew.getInChargeEmail());
        this.tel = String.valueOf(customerNew.getInChargeTel());
        this.telCode = String.valueOf(customerNew.getInChargeTelCode());
    }
}
