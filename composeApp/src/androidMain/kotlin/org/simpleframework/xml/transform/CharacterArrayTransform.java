package org.simpleframework.xml.transform;

import java.lang.reflect.Array;


class CharacterArrayTransform implements Transform {
    private final Class entry;

    public CharacterArrayTransform(final Class cls) {
        entry = cls;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Object read(final String str) throws Exception {
        final char[] charArray = str.toCharArray();
        return entry == Character.TYPE ? charArray : this.read(charArray, charArray.length);
    }

    private Object read(final char[] cArr, final int i2) throws Exception {
        final Object newInstance = Array.newInstance((Class<?>) entry, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            Array.set(newInstance, i3, Character.valueOf(cArr[i3]));
        }
        return newInstance;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Object obj) throws Exception {
        final int length = Array.getLength(obj);
        if (entry == Character.TYPE) {
            return new String((char[]) obj);
        }
        return this.write(obj, length);
    }

    private String write(final Object obj, final int i2) throws Exception {
        final StringBuilder sb = new StringBuilder(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            final Object obj2 = Array.get(obj, i3);
            if (null != obj2) {
                sb.append(obj2);
            }
        }
        return sb.toString();
    }
}
