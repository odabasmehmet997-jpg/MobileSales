package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class StdKeyDeserializers implements KeyDeserializers, Serializable {
    private static final long serialVersionUID = 1;
    public static KeyDeserializer constructEnumKeyDeserializer(final EnumResolver enumResolver) {
        return new StdKeyDeserializer.EnumKD(enumResolver, null);
    }
    public static KeyDeserializer constructEnumKeyDeserializer(final EnumResolver enumResolver, final AnnotatedMethod annotatedMethod) {
        return new StdKeyDeserializer.EnumKD(enumResolver, annotatedMethod);
    }
    public static KeyDeserializer constructDelegatingKeyDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType, final JsonDeserializer<?> jsonDeserializer) {
        return new StdKeyDeserializer.DelegatingKD(javaType.getRawClass(), jsonDeserializer);
    }
    public static KeyDeserializer findStringBasedKeyDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType) {
        final BeanDescription beanDescriptionIntrospect = deserializationConfig.introspect(javaType);
        final Constructor<?> constructorFindSingleArgConstructor = beanDescriptionIntrospect.findSingleArgConstructor(String.class);
        if (null != constructorFindSingleArgConstructor) {
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(constructorFindSingleArgConstructor, deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return new StdKeyDeserializer.StringCtorKeyDeserializer(constructorFindSingleArgConstructor);
        }
        final Method methodFindFactoryMethod = beanDescriptionIntrospect.findFactoryMethod(String.class);
        if (null == methodFindFactoryMethod) {
            return null;
        }
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(methodFindFactoryMethod, deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new StdKeyDeserializer.StringFactoryKeyDeserializer(methodFindFactoryMethod);
    }
    public KeyDeserializer findKeyDeserializer(final JavaType javaType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass.isPrimitive()) {
            rawClass = ClassUtil.wrapperType(rawClass);
        }
        return StdKeyDeserializer.forType(rawClass);
    }
}
