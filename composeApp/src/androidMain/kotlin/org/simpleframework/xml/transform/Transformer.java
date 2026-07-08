package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;


public class Transformer {
    private final Cache<Transform> cache = new ConcurrentCache();
    private final Cache<Object> error = new ConcurrentCache();
    private final Matcher matcher;

    public Transformer(final Matcher matcher) {
        this.matcher = new DefaultMatcher(matcher);
    }

    public Object read(final String str, final Class cls) throws Exception {
        final Transform lookup = this.lookup(cls);
        if (null == lookup) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return lookup.read(str);
    }

    public String write(final Object obj, final Class cls) throws Exception {
        final Transform lookup = this.lookup(cls);
        if (null == lookup) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return lookup.write(obj);
    }

    public boolean valid(final Class cls) throws Exception {
        return null != lookup(cls);
    }

    private Transform lookup(final Class cls) throws Exception {
        if (error.contains(cls)) {
            return null;
        }
        final Transform fetch = cache.fetch(cls);
        return null != fetch ? fetch : this.match(cls);
    }

    private Transform match(final Class cls) throws Exception {
        final Transform match = matcher.match(cls);
        if (null != match) {
            cache.cache(cls, match);
        } else {
            error.cache(cls, this);
        }
        return match;
    }
}
