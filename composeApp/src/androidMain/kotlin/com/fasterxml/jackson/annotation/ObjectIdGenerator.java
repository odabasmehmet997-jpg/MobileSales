package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T> implements Serializable {
    public abstract boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator);
    public abstract ObjectIdGenerator<T> forScope(Class<?> cls);
    public abstract T generateId(Object obj);
    public abstract Class<?> getScope();
    public boolean isValidReferencePropertyName(final String str, final Object obj) {
        return false;
    }
    public abstract IdKey key(Object obj);
    public boolean maySerializeAsObject() {
        return false;
    }
    public abstract ObjectIdGenerator<T> newForSerialization(Object obj);
    public static final class IdKey implements Serializable {
        private static final long serialVersionUID = 1;
        private final int hashCode;
        public final Object key;
        public final Class<?> scope;
        public final Class<?> type;

        public IdKey(final Class<?> cls, final Class<?> cls2, final Object obj) {
            if (null == obj) {
                throw new IllegalArgumentException("Can not construct IdKey for null key");
            }
            type = cls;
            scope = cls2;
            key = obj;
            final int iHashCode = obj.hashCode() + cls.getName().hashCode();
            hashCode = null != cls2 ? iHashCode ^ cls2.getName().hashCode() : iHashCode;
        }
        public int hashCode() {
            return hashCode;
        }
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || IdKey.class != obj.getClass()) {
                return false;
            }
            final IdKey idKey = (IdKey) obj;
            return idKey.key.equals(key) && idKey.type == type && idKey.scope == scope;
        }
        public String toString() {
            final Object obj = key;
            final Class<?> cls = type;
            final String name = null == cls ? "NONE" : cls.getName();
            final Class<?> cls2 = scope;
            return String.format("[ObjectId: key=%s, type=%s, scope=%s]", obj, name, null != cls2 ? cls2.getName() : "NONE");
        }
    }
}
