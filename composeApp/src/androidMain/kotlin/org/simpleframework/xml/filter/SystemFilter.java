package org.simpleframework.xml.filter;

public class SystemFilter implements Filter {
    private final Filter filter;

    public SystemFilter() {
        this(null);
    }

    public SystemFilter(final Filter filter) {
        this.filter = filter;
    }

    @Override // org.simpleframework.xml.filter.Filter
    public String replace(final String str) {
        final String property = System.getProperty(str);
        if (null != property) {
            return property;
        }
        final Filter filter = this.filter;
        if (null != filter) {
            return filter.replace(str);
        }
        return null;
    }
}
