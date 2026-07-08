package org.simpleframework.xml.transform;


class EmptyMatcher implements Matcher {
    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(final Class cls) throws Exception {
        return null;
    }

    EmptyMatcher() {
    }
}
