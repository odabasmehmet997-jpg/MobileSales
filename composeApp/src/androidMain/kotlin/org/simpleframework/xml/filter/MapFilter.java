package org.simpleframework.xml.filter;

import java.util.Map;

public class MapFilter implements Filter {
    private final Filter filter;
    private final Map map;

    public MapFilter(final Map map) {
        this(map, null);
    }

    public MapFilter(final Map map, final Filter filter) {
        this.filter = filter;
        this.map = map;
    }

    @Override // org.simpleframework.xml.filter.Filter
    public String replace(final String str) {
        final Map map = this.map;
        final Object obj = null != map ? map.get(str) : null;
        if (null != obj) {
            return obj.toString();
        }
        final Filter filter = this.filter;
        if (null != filter) {
            return filter.replace(str);
        }
        return null;
    }
}
