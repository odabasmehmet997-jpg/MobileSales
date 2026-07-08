package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.LimitedCache;


class ExpressionBuilder {
    private final Cache<Expression> cache = new LimitedCache();
    private final Format format;
    private final Class type;

    public ExpressionBuilder(final Detail detail, final Support support) {
        format = support.getFormat();
        type = detail.getType();
    }

    public Expression build(final String str) throws Exception {
        final Expression fetch = cache.fetch(str);
        return null == fetch ? this.create(str) : fetch;
    }

    private Expression create(final String str) throws Exception {
        final PathParser pathParser = new PathParser(str, new ClassType(type), format);
        final Cache<Expression> cache = this.cache;
        if (null != cache) {
            cache.cache(str, pathParser);
        }
        return pathParser;
    }
}
