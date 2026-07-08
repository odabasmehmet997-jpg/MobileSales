package org.simpleframework.xml.stream;


public class HyphenStyle implements Style {
    private final Builder builder;
    private final Style style;

    public HyphenStyle() {
        final HyphenBuilder hyphenBuilder = new HyphenBuilder();
        style = hyphenBuilder;
        builder = new Builder(hyphenBuilder);
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
