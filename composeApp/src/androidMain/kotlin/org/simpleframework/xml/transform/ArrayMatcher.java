package org.simpleframework.xml.transform;


class ArrayMatcher implements Matcher {
    private final Matcher primary;

    public ArrayMatcher(final Matcher matcher) {
        primary = matcher;
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(final Class cls) throws Exception {
        final Class<?> componentType = cls.getComponentType();
        if (componentType == Character.TYPE) {
            return new CharacterArrayTransform(componentType);
        }
        if (Character.class == componentType) {
            return new CharacterArrayTransform(componentType);
        }
        if (String.class == componentType) {
            return new StringArrayTransform();
        }
        return this.matchArray(componentType);
    }

    private Transform matchArray(final Class cls) throws Exception {
        final Transform match = primary.match(cls);
        if (null == match) {
            return null;
        }
        return new ArrayTransform(match, cls);
    }
}
