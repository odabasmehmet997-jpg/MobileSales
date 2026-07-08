package org.simpleframework.xml.transform;


class DefaultMatcher implements Matcher {
    private final Matcher matcher;
    private final Matcher primitive = new PrimitiveMatcher();
    private final Matcher stock = new PackageMatcher();
    private final Matcher array = new ArrayMatcher(this);

    public DefaultMatcher(final Matcher matcher) {
        this.matcher = matcher;
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(final Class cls) throws Exception {
        final Transform match = matcher.match(cls);
        return null != match ? match : this.matchType(cls);
    }

    private Transform matchType(final Class cls) throws Exception {
        if (cls.isArray()) {
            return array.match(cls);
        }
        if (cls.isPrimitive()) {
            return primitive.match(cls);
        }
        return stock.match(cls);
    }
}
