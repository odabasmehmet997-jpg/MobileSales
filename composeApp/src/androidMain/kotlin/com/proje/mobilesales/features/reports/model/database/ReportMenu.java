package com.proje.mobilesales.features.reports.model.database;

public final class ReportMenu {

    private int id;
    private int keyId;
    private String repName;
    private int type;
    public int getId() {
        return id;
    }
    public void setId(final int id) {
        this.id = id;
    }
    public int getKeyId() {
        return keyId;
    }
    public void setKeyId(final int keyId) {
        this.keyId = keyId;
    }
    public String getRepName() {
        return repName;
    }
    public void setRepName(final String repName) {
        this.repName = repName;
    }
    public int getType() {
        return type;
    }
    public void setType(final int type) {
        this.type = type;
    }
}
