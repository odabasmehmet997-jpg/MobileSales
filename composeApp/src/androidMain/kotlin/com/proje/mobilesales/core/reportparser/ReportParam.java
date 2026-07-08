package com.proje.mobilesales.core.reportparser;

public class ReportParam {
    private String dataType;
    private String fieldLabel;
    private boolean hideFirstCol;
    private String keys;
    private String name;
    private String selectedItemValue;
    private String subSql;
    private String type;
    private String values;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(final String str) {
        dataType = str;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(final String str) {
        fieldLabel = str;
    }

    public boolean isHideFirstCol() {
        return hideFirstCol;
    }

    public void setHideFirstCol(final boolean z) {
        hideFirstCol = z;
    }

    public String getSubSql() {
        return subSql;
    }

    public void setSubSql(final String str) {
        subSql = str;
    }

    public String getType() {
        return type;
    }

    public void setType(final String str) {
        type = str;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(final String str) {
        keys = str;
    }

    public String getValues() {
        return values;
    }

    public void setValues(final String str) {
        values = str;
    }

    public String getSelectedItemValue() {
        return selectedItemValue;
    }

    public void setSelectedItemValue(final String str) {
        selectedItemValue = str;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public String getValuesForSql() {
        if ("Text".equals(this.type)) {
            return this.values;
        }
        if (null == this.selectedItemValue) {
            return "";
        }
        return this.selectedItemValue;
    }
}
