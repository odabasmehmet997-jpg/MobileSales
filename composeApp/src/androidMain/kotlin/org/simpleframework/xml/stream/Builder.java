package org.simpleframework.xml.stream;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;


class Builder implements Style {
    private final Cache<String> attributes = new ConcurrentCache();
    private final Cache<String> elements = new ConcurrentCache();
    private final Style style;

    public Builder(final Style style) {
        this.style = style;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getAttribute(final String str) {
        final String fetch = attributes.fetch(str);
        if (null != fetch) {
            return fetch;
        }
        final String attribute = style.getAttribute(str);
        if (null != attribute) {
            attributes.cache(str, attribute);
        }
        return attribute;
    }

    @Override // org.simpleframework.xml.stream.Style
    public String getElement(final String str) {
        final String fetch = elements.fetch(str);
        if (null != fetch) {
            return fetch;
        }
        final String element = style.getElement(str);
        if (null != element) {
            elements.cache(str, element);
        }
        return element;
    }

    public void setAttribute(final String str, final String str2) {
        attributes.cache(str, str2);
    }

    public void setElement(final String str, final String str2) {
        elements.cache(str, str2);
    }
}
