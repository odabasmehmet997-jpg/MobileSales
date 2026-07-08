package org.simpleframework.xml.transform;

import java.lang.reflect.Array;


class ArrayTransform implements Transform {
    private final Transform delegate;
    private final Class entry;
    private final StringArrayTransform split = new StringArrayTransform();

    public ArrayTransform(final Transform transform, final Class cls) {
        delegate = transform;
        entry = cls;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Object read(final String str) throws Exception {
        final String[] read = split.read(str);
        return this.read(read, read.length);
    }

    private Object read(final String[] strArr, final int i2) throws Exception {
        final Object newInstance = Array.newInstance((Class<?>) entry, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            final Object read = delegate.read(strArr[i3]);
            if (null != read) {
                Array.set(newInstance, i3, read);
            }
        }
        return newInstance;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Object obj) throws Exception {
        return this.write(obj, Array.getLength(obj));
    }

    private String write(final Object obj, final int i2) throws Exception {
        final String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            final Object obj2 = Array.get(obj, i3);
            if (null != obj2) {
                strArr[i3] = delegate.write(obj2);
            }
        }
        return split.write(strArr);
    }
}
