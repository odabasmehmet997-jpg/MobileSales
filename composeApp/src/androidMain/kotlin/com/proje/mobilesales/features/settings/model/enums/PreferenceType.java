package com.proje.mobilesales.features.settings.model.enums;



public final class PreferenceType {
    public static final PreferenceType PRINTER = new PreferenceType("PRINTER", 0);
    public static final PreferenceType PRINT = new PreferenceType("PRINT", 1);
    public static final PreferenceType TRANSFER = new PreferenceType("TRANSFER", 2);
    public static final PreferenceType USER_SETTINGS = new PreferenceType("USER_SETTINGS", 3);
    public static final PreferenceType PRINTER_SERVICE = new PreferenceType("PRINTER_SERVICE", 4);


    public static PreferenceType[] values() {
        return null;
    }

    private PreferenceType(final String str, final int r2) {
    }

    public boolean ordinal() {
        return false;
    }
}
