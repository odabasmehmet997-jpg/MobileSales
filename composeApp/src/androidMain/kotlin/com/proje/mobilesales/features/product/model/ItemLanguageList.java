package com.proje.mobilesales.features.product.model;

public final class ItemLanguageList {

    private int f1262id;
    private String languageDefinition;
    private String languageName;
    public int getId() {
        return f1262id;
    }
    public void setId(final int i) {
        f1262id = i;
    }
    public ItemLanguageList() {
    }
    public ItemLanguageList(final int i, final String str, final String str2) {
        f1262id = i;
        languageName = str2;
        languageDefinition = str;
    }
}
