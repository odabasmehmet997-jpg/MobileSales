package org.simpleframework.xml.core;

import java.util.Arrays;

class KeyBuilder {
    private final Label label;
    private enum KeyType {
        TEXT,
        ATTRIBUTE,
        ELEMENT
    }
    public KeyBuilder(final Label label) {
        this.label = label;
    }
    public Object getKey() throws Exception {
        if (label.isAttribute()) {
            return this.getKey(KeyType.ATTRIBUTE);
        }
        return this.getKey(KeyType.ELEMENT);
    }
    private Object getKey(final KeyType keyType) throws Exception {
        final String key = this.getKey(label.getPaths());
        return null == keyType ? key : new Key(keyType, key);
    }
    private String getKey(final String[] strArr) throws Exception {
        final StringBuilder sb = new StringBuilder();
        if (0 < strArr.length) {
            Arrays.sort(strArr);
            for (final String str : strArr) {
                sb.append(str);
                sb.append('>');
            }
        }
        return sb.toString();
    }
    private static class Key {
        private final KeyType type;
        private final String value;

        public Key(final KeyType keyType, final String str) throws Exception {
            value = str;
            type = keyType;
        }

        public boolean equals(final Object obj) {
            if (obj instanceof Key) {
                return this.equals((Key) obj);
            }
            return false;
        }

        public boolean equals(final Key key) {
            if (type == key.type) {
                return key.value.equals(value);
            }
            return false;
        }

        public int hashCode() {
            return value.hashCode();
        }

        public String toString() {
            return value;
        }
    }
}
