package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateFilter implements Filter {
    private final Context context;
    private final Filter filter;
    public TemplateFilter(final Context context, final Filter filter) {
        this.context = context;
        this.filter = filter;
    }
    public String replace(final String str) {
        final Object attribute = context.getAttribute(str);
        if (attribute==null) {
            return filter.replace(str);
        }
        return attribute.toString();
    }
}
