package org.simpleframework.xml.stream;


class IdentityStyle implements Style {
    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(final String str) {
        return str;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(final String str) {
        return str;
    }

    IdentityStyle() {
    }
}
