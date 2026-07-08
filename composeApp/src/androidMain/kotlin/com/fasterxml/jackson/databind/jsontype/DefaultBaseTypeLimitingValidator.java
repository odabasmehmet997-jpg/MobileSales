package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import java.io.Closeable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DefaultBaseTypeLimitingValidator extends PolymorphicTypeValidator {
    private static final long serialVersionUID = 1;

    protected boolean isSafeSubType(final MapperConfig<?> mapperConfig, final JavaType javaType, final JavaType javaType2) {
        return true;
    }
    public PolymorphicTypeValidator.Validity validateBaseType(final MapperConfig<?> mapperConfig, final JavaType javaType) {
        if (this.isUnsafeBaseType(mapperConfig, javaType)) {
            return PolymorphicTypeValidator.Validity.DENIED;
        }
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    public PolymorphicTypeValidator.Validity validateSubClassName(final MapperConfig<?> mapperConfig, final JavaType javaType, final String str) {
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    public PolymorphicTypeValidator.Validity validateSubType(final MapperConfig<?> mapperConfig, final JavaType javaType, final JavaType javaType2) {
        return this.isSafeSubType(mapperConfig, javaType, javaType2) ? PolymorphicTypeValidator.Validity.ALLOWED : PolymorphicTypeValidator.Validity.DENIED;
    }

    protected boolean isUnsafeBaseType(final MapperConfig<?> mapperConfig, final JavaType javaType) {
        return UnsafeBaseTypes.instance.isUnsafeBaseType(javaType.getRawClass());
    }

    private static final class UnsafeBaseTypes {
        public static final UnsafeBaseTypes instance = new UnsafeBaseTypes();
        private final Set<String> UNSAFE;

        private UnsafeBaseTypes() {
            final HashSet hashSet = new HashSet();
            UNSAFE = hashSet;
            hashSet.add(Object.class.getName());
            hashSet.add(Closeable.class.getName());
            hashSet.add(Serializable.class.getName());
            hashSet.add(AutoCloseable.class.getName());
            hashSet.add(Cloneable.class.getName());
            hashSet.add("java.util.logging.Handler");
            hashSet.add("javax.naming.Referenceable");
            hashSet.add("javax.sql.DataSource");
        }

        public boolean isUnsafeBaseType(final Class<?> cls) {
            return UNSAFE.contains(cls.getName());
        }
    }
}
