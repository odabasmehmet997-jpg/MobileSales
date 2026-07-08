package com.proje.mobilesales.features.penetration.model.database;

import com.proje.mobilesales.core.interfaces.CharSequenceGet;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.penetration.model.PenetrationTypeEnum;

public final class Penetration implements CharSequenceGet {
    private String beginDate;
    private String endDate;
    private int logicalRef;
    private String pCode;
    private String pDefinition;
    private int ptype;
    private int status;
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i) {
        logicalRef = i;
    }
    public int getPtype() {
        return ptype;
    }
    public void setPtype(final int i) {
        ptype = i;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(final int i) {
        status = i;
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(final String str) {
        beginDate = str;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(final String str) {
        endDate = str;
    }
    public String getpCode() {
        return pCode;
    }
    public void setpCode(final String str) {
        pCode = str;
    }
    public String getpDefinition() {
        return pDefinition;
    }
    public void setpDefinition(final String str) {
        pDefinition = str;
    }
    public CharSequence getCharSequence() {
        return this.pDefinition + " - (" + ContextUtils.getStringResource(PenetrationTypeEnum.Companion.getPenetRationType(ptype).getResId()) + ')';
    }
}
