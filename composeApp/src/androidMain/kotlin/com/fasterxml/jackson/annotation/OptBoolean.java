package com.fasterxml.jackson.annotation;

public enum OptBoolean {
    TRUE,
    FALSE,
    DEFAULT;

    public Boolean asBoolean() {
        if (OptBoolean.DEFAULT == this) {
            return null;
        }
        return OptBoolean.TRUE == this ? Boolean.TRUE : Boolean.FALSE;
    }
    public boolean asPrimitive() {
        return OptBoolean.TRUE == this;
    }
    public static OptBoolean fromBoolean(final Boolean bool) {
        if (null == bool) {
            return OptBoolean.DEFAULT;
        }
        return bool.booleanValue() ? OptBoolean.TRUE : OptBoolean.FALSE;
    }
    public static boolean equals(final Boolean bool, final Boolean bool2) {
        if (null == bool) {
            return null == bool2;
        }
        return bool.equals(bool2);
    }
}
