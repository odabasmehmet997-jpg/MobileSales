package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BeanDescription {
    protected final JavaType _type;
    public abstract AnnotatedMember findAnyGetter();
    public abstract AnnotatedMember findAnySetterAccessor();
    public abstract List<BeanPropertyDefinition> findBackReferences();
    public abstract AnnotatedConstructor findDefaultConstructor();
    public abstract Class<?>[] findDefaultViews();
    public abstract Converter<Object, Object> findDeserializationConverter();
    public abstract JsonFormat.Value findExpectedFormat(JsonFormat.Value value);
    public abstract Method findFactoryMethod(Class<?>... clsArr);
    public abstract Map<Object, AnnotatedMember> findInjectables();
    public abstract AnnotatedMember findJsonKeyAccessor();
    public abstract AnnotatedMember findJsonValueAccessor();
    public abstract AnnotatedMethod findJsonValueMethod();
    public abstract AnnotatedMethod findMethod(String str, Class<?>[] clsArr);
    public abstract Class<?> findPOJOBuilder();
    public abstract JsonPOJOBuilder findPOJOBuilderConfig();
    public abstract List<BeanPropertyDefinition> findProperties();
    public abstract JsonInclude.Value findPropertyInclusion(JsonInclude.Value value);
    public abstract Converter<Object, Object> findSerializationConverter();
    public abstract Constructor<?> findSingleArgConstructor(Class<?>... clsArr);
    public abstract Annotations getClassAnnotations();
    public abstract AnnotatedClass getClassInfo();
    public abstract List<AnnotatedConstructor> getConstructors();
    public abstract List<AnnotatedMethod> getFactoryMethods();
    public abstract Set<String> getIgnoredPropertyNames();
    public abstract ObjectIdInfo getObjectIdInfo();
    public abstract boolean hasKnownClassAnnotations();
    public abstract Object instantiateBean(boolean z);
    protected BeanDescription(final JavaType javaType) {
        _type = javaType;
    }
    public JavaType getType() {
        return _type;
    }
    public Class<?> getBeanClass() {
        return _type.getRawClass();
    }
    public boolean isNonStaticInnerClass() {
        return this.getClassInfo().isNonStaticInnerClass();
    }
}
