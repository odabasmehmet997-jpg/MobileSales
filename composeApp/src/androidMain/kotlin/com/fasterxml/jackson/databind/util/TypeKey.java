package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;

public class TypeKey {
    protected Class<?> _class;
    protected int _hashCode;
    protected boolean _isTyped;
    protected JavaType _type;
    public TypeKey() {
    }
    public TypeKey(final Class<?> cls, final boolean z) {
        _class = cls;
        _type = null;
        _isTyped = z;
        _hashCode = z ? TypeKey.typedHash(cls) : TypeKey.untypedHash(cls);
    }
    public TypeKey(final JavaType javaType, final boolean z) {
        _type = javaType;
        _class = null;
        _isTyped = z;
        _hashCode = z ? TypeKey.typedHash(javaType) : TypeKey.untypedHash(javaType);
    }
    public static int untypedHash(final Class<?> cls) {
        return cls.getName().hashCode();
    }
    public static int typedHash(final Class<?> cls) {
        return cls.getName().hashCode() + 1;
    }
    public static int untypedHash(final JavaType javaType) {
        return javaType.hashCode() - 1;
    }
    public static int typedHash(final JavaType javaType) {
        return javaType.hashCode() - 2;
    }
    public boolean isTyped() {
        return _isTyped;
    }
    public Class<?> getRawType() {
        return _class;
    }
    public JavaType getType() {
        return _type;
    }
    public final int hashCode() {
        return _hashCode;
    }
    public final String toString() {
        if (null != this._class) {
            return "{class: " + _class.getName() + ", typed? " + _isTyped + "}";
        }
        return "{type: " + _type + ", typed? " + _isTyped + "}";
    }
    public final boolean equals(final Object obj) {
        if (null == obj) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final TypeKey typeKey = (TypeKey) obj;
        if (typeKey._isTyped != _isTyped) {
            return false;
        }
        final Class<?> cls = _class;
        if (null != cls) {
            return typeKey._class == cls;
        }
        return _type.equals(typeKey._type);
    }
}
