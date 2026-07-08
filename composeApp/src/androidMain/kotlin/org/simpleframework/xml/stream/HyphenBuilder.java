package org.simpleframework.xml.stream;


class HyphenBuilder implements Style {
    HyphenBuilder() {
    }

    
    public String getAttribute(final String str) {
        if (null != str) {
            return new Parser(str).process();
        }
        return null;
    }

    
    public String getElement(final String str) {
        if (null != str) {
            return new Parser(str).process();
        }
        return null;
    }

    private class Parser extends Splitter {
        private Parser(final String str) {
            super(str);
        }

        
        protected void parse(final char[] cArr, final int i2, final int i3) {
            cArr[i2] = this.toLower(cArr[i2]);
        }

        
        protected void commit(final char[] cArr, final int i2, final int i3) {
            builder.append(cArr, i2, i3);
            if (i2 + i3 < count) {
                builder.append('-');
            }
        }
    }
}
