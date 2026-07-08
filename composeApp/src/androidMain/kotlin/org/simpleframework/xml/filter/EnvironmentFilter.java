package org.simpleframework.xml.filter;

public class EnvironmentFilter implements Filter {
    private final Filter filter;

    public EnvironmentFilter() {
        this(null);
    }

    public EnvironmentFilter(final Filter filter) {
        this.filter = filter;
    }

    @Override // org.simpleframework.xml.filter.Filter
    public String replace(final String str) {
        final String str2 = System.getenv(str);
        if (null != str2) {
            return str2;
        }
        final Filter filter = this.filter;
        if (null != filter) {
            return filter.replace(str);
        }
        return null;
    }
}
