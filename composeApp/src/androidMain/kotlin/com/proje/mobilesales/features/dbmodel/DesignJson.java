package com.proje.mobilesales.features.dbmodel;


public class DesignJson {
    private String design;
    private String formConfig;
    private int f1234id;
    private String name;
    private int reportType;
    public int getId() {
        return this.f1234id;
    }
    public void setId(int i2) {
        this.f1234id = i2;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String str) {
        this.name = str;
    }
    public String getDesign() {
        return this.design;
    }
    public void setDesign(String str) {
        this.design = str;
    }
    public int getReportType() {
        return this.reportType;
    }
    public void setReportType(int i2) {
        this.reportType = i2;
    }
    public String getFormConfig() {
        return this.formConfig;
    }
    public void setFormConfig(String str) {
        this.formConfig = str;
    }
}
