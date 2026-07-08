package com.proje.mobilesales.core.netsis.sendmodel.edocument;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.edocument.EDocumentType;

public class DraftEDocumentParam {

    @SerializedName("DizaynKontrol")
    @Expose
    private boolean designControl;

    @SerializedName("BelgeNo")
    @Expose
    private String documentNo;

    @SerializedName("Tip")
    @Expose
    private EDocumentType type;

    public String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String str) {
        this.documentNo = str;
    }

    public EDocumentType getType() {
        return this.type;
    }

    public void setType(EDocumentType eDocumentType) {
        this.type = eDocumentType;
    }

    public boolean isDesignControl() {
        return this.designControl;
    }

    public void setDesignControl(boolean z) {
        this.designControl = z;
    }
}
