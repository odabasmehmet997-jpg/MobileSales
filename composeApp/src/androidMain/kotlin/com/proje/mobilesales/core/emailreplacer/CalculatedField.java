package com.proje.mobilesales.core.emailreplacer;

public class CalculatedField {

    private int fieldNumber;
    private String formula;
    private String name;

    private String orgFormula;

    private String section;

    private String visibleName;

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public void setVisibleName(final String str) {
        visibleName = str;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String str) {
        formula = str;
    }

    public String getSection() {
        return section;
    }

    public void setSection(final String str) {
        section = str;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(final int i2) {
        fieldNumber = i2;
    }

    public String getOrgFormula() {
        return orgFormula;
    }

    public void setOrgFormula(final String str) {
        orgFormula = str;
    }
}
