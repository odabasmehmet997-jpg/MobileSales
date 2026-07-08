package com.proje.mobilesales.core.reportparser;



public class ReportAppearanceItemCaption {
    private boolean bold;
    private String fontName;
    private float fontSize;
    private String foreColor;
    private boolean italic;
    private boolean underline;

    public String getForeColor() {
        return foreColor;
    }

    public void setForeColor(final String str) {
        foreColor = str;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(final boolean z) {
        bold = z;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(final boolean z) {
        italic = z;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(final boolean z) {
        underline = z;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(final float f2) {
        fontSize = f2;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(final String str) {
        fontName = str;
    }
}
