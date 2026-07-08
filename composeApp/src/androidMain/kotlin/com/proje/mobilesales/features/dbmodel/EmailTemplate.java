package com.proje.mobilesales.features.dbmodel;


public class EmailTemplate {
    private double cmDate;
    private String formConfig;
    private String formContent;
    private String formName;
    private int formType;
    private int f1238id;
    public int getId() {
        return this.f1238id;
    }
    public void setId(int i2) {
        this.f1238id = i2;
    }
    public String getFormName() {
        return this.formName;
    }
    public void setFormName(String str) {
        this.formName = str;
    }
    public int getFormType() {
        return this.formType;
    }
    public void setFormType(int i2) {
        this.formType = i2;
    }
    public String getFormContent() {
        return this.formContent;
    }
    public void setFormContent(String str) {
        this.formContent = str;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
    public String getFormConfig() {
        return this.formConfig;
    }
    public void setFormConfig(String str) {
        this.formConfig = str;
    }
}
