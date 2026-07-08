package com.proje.mobilesales.core.reportparser;

import com.proje.mobilesales.features.reports.model.enums.ReportLayoutItemType;
import java.util.List;



public class ReportLayoutItem {
    private List<ReportLayoutItem> childItems;
    private String customizationFormText;
    private int formColumnIndex;
    private int formHeight;
    private int formRowIndex;
    private String formText;
    private boolean formTextVisible;
    private float formTextWeight;
    private float formValueWeight;
    private int formWidth;
    private int formX;
    private int formY;
    private ReportLayoutItemType itemType;
    private String name;
    private String parentName;
    private boolean showInCustomizationForm;
    private ReportAppearanceItemCaption textAppearanceItemCaption;
    private int textHeight;
    private int textWidth;
    private int totalRows;
    private ReportAppearanceItemCaption valueAppearanceItemCaption;
    private float weight;

    public String getFormText() {
        return formText;
    }

    public void setFormText(final String str) {
        formText = str;
    }

    public String getCustomizationFormText() {
        return customizationFormText;
    }

    public void setCustomizationFormText(final String str) {
        customizationFormText = str;
    }

    public int getFormX() {
        return formX;
    }

    public void setFormX(final int i2) {
        formX = i2;
    }

    public int getFormY() {
        return formY;
    }

    public void setFormY(final int i2) {
        formY = i2;
    }

    public int getFormWidth() {
        return formWidth;
    }

    public void setFormWidth(final int i2) {
        formWidth = i2;
    }

    public int getFormHeight() {
        return formHeight;
    }

    public void setFormHeight(final int i2) {
        formHeight = i2;
    }

    public int getFormRowIndex() {
        return formRowIndex;
    }

    public void setFormRowIndex(final int i2) {
        formRowIndex = i2;
    }

    public int getFormColumnIndex() {
        return formColumnIndex;
    }

    public void setFormColumnIndex(final int i2) {
        formColumnIndex = i2;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(final String str) {
        parentName = str;
    }

    public boolean isFormTextVisible() {
        return formTextVisible;
    }

    public void setFormTextVisible(final boolean z) {
        formTextVisible = z;
    }

    public boolean isShowInCustomizationForm() {
        return showInCustomizationForm;
    }

    public void setShowInCustomizationForm(final boolean z) {
        showInCustomizationForm = z;
    }

    public List<ReportLayoutItem> getChildItems() {
        return childItems;
    }

    public void setChildItems(final List<ReportLayoutItem> list) {
        childItems = list;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public ReportLayoutItemType getItemType() {
        return itemType;
    }

    public void setItemType(final ReportLayoutItemType reportLayoutItemType) {
        itemType = reportLayoutItemType;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(final int i2) {
        totalRows = i2;
    }

    public float getFormValueWeight() {
        return formValueWeight;
    }

    public void setFormValueWeight(final float f2) {
        formValueWeight = f2;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float f2) {
        weight = f2;
    }

    public int getTextWidth() {
        return textWidth;
    }

    public void setTextWidth(final int i2) {
        textWidth = i2;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public void setTextHeight(final int i2) {
        textHeight = i2;
    }

    public float getFormTextWeight() {
        return formTextWeight;
    }

    public void setFormTextWeight(final float f2) {
        formTextWeight = f2;
    }

    public ReportAppearanceItemCaption getTextAppearanceItemCaption() {
        return textAppearanceItemCaption;
    }

    public void setTextAppearanceItemCaption(final ReportAppearanceItemCaption reportAppearanceItemCaption) {
        textAppearanceItemCaption = reportAppearanceItemCaption;
    }

    public ReportAppearanceItemCaption getValueAppearanceItemCaption() {
        return valueAppearanceItemCaption;
    }

    public void setValueAppearanceItemCaption(final ReportAppearanceItemCaption reportAppearanceItemCaption) {
        valueAppearanceItemCaption = reportAppearanceItemCaption;
    }
}
