package com.proje.mobilesales.features.todo.model;

public final class TodoInfoModel {
    private String complateDate;
    public int logicalRef;
    private String note;
    private String readDate;
    public int status;
    public String getNote() {
        return this.note;
    }
    public void setNote(String str) {
        if (str == null) {
            str = "";
        }
        this.note = str;
    }
    public String getReadDate() {
        return this.readDate;
    }
    public void setReadDate(String str) {
        this.readDate = str;
    }
    public String getComplateDate() {
        return this.complateDate;
    }
    public void setComplateDate(String str) {
        this.complateDate = str;
    }
}
