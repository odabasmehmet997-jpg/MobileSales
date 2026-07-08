package org.simpleframework.xml.stream;


public class CamelCaseStyle implements Style {
    private final Builder builder;
    private final Style style;

    public CamelCaseStyle() {
        this(true, false);
    }

    public CamelCaseStyle(final boolean z) {
        this(z, false);
    }

    public CamelCaseStyle(final boolean z, final boolean z2) {
        final CamelCaseBuilder camelCaseBuilder = new CamelCaseBuilder(z, z2);
        style = camelCaseBuilder;
        builder = new Builder(camelCaseBuilder);
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(final String str) {
        return builder.getAttribute(str);
    }

    public void setAttribute(final String str, final String str2) {
        builder.setAttribute(str, str2);
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(final String str) {
        return builder.getElement(str);
    }

    public void setElement(final String str, final String str2) {
        builder.setElement(str, str2);
    }
}
