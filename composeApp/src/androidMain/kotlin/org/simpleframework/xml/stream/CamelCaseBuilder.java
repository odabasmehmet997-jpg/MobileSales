package org.simpleframework.xml.stream;


record CamelCaseBuilder(boolean element, boolean attribute) implements Style {

    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(final String str) {
        if (null != str) {
            return new Attribute(str).process();
        }
        return null;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(final String str) {
        if (null != str) {
            return new Element(str).process();
        }
        return null;
    }

    private class Attribute extends Splitter {
        private boolean capital;

        private Attribute(final String str) {
            super(str);
        }

        @Override // org.simpleframework.xml.stream.Splitter
        protected void parse(final char[] cArr, final int i2, final int i3) {
            if (attribute || capital) {
                cArr[i2] = this.toUpper(cArr[i2]);
            }
            capital = true;
        }

        @Override // org.simpleframework.xml.stream.Splitter
        protected void commit(final char[] cArr, final int i2, final int i3) {
            builder.append(cArr, i2, i3);
        }
    }

    private class Element extends Attribute {
        private boolean capital;

        private Element(final String str) {
            super(str);
        }

        @Override // org.simpleframework.xml.stream.CamelCaseBuilder.Attribute, org.simpleframework.xml.stream.Splitter
        protected void parse(final char[] cArr, final int i2, final int i3) {
            if (element || capital) {
                cArr[i2] = this.toUpper(cArr[i2]);
            }
            capital = true;
        }
    }
}
