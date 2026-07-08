package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.Collections;
import java.util.Iterator;
 
public class SimpleBeanPropertyDefinition extends BeanPropertyDefinition {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final PropertyName _fullName;
    protected final JsonInclude.Value _inclusion;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;
    public boolean isExplicitlyIncluded() {
        return false;
    }
    public boolean isExplicitlyNamed() {
        return false;
    }
    protected SimpleBeanPropertyDefinition(final AnnotationIntrospector annotationIntrospector, final AnnotatedMember annotatedMember, final PropertyName propertyName, final PropertyMetadata propertyMetadata, final JsonInclude.Value value) {
        _annotationIntrospector = annotationIntrospector;
        _member = annotatedMember;
        _fullName = propertyName;
        _metadata = null == propertyMetadata ? PropertyMetadata.STD_OPTIONAL : propertyMetadata;
        _inclusion = value;
    }
    public static SimpleBeanPropertyDefinition construct(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final PropertyName propertyName) {
        return SimpleBeanPropertyDefinition.construct(mapperConfig, annotatedMember, propertyName, null, EMPTY_INCLUDE);
    }
    public static SimpleBeanPropertyDefinition construct(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final PropertyName propertyName, final PropertyMetadata propertyMetadata, final JsonInclude.Include include) {
        final JsonInclude.Value valueConstruct;
        if (null == include || JsonInclude.Include.USE_DEFAULTS == include) {
            valueConstruct = EMPTY_INCLUDE;
        } else {
            valueConstruct = JsonInclude.Value.construct(include, null);
        }
        return new SimpleBeanPropertyDefinition(mapperConfig.getAnnotationIntrospector(), annotatedMember, propertyName, propertyMetadata, valueConstruct);
    }
    public static SimpleBeanPropertyDefinition construct(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final PropertyName propertyName, final PropertyMetadata propertyMetadata, final JsonInclude.Value value) {
        return new SimpleBeanPropertyDefinition(mapperConfig.getAnnotationIntrospector(), annotatedMember, propertyName, propertyMetadata, value);
    }
    public String getName() {
        return _fullName.getSimpleName();
    }
    public PropertyName getFullName() {
        return _fullName;
    }
    public boolean hasName(final PropertyName propertyName) {
        return _fullName.equals(propertyName);
    }
    public PropertyName getWrapperName() {
        final AnnotatedMember annotatedMember;
        final AnnotationIntrospector annotationIntrospector = _annotationIntrospector;
        if (null == annotationIntrospector || null == (annotatedMember = this._member)) {
            return null;
        }
        return annotationIntrospector.findWrapperName(annotatedMember);
    }
    public PropertyMetadata getMetadata() {
        return _metadata;
    }
    public JavaType getPrimaryType() {
        final AnnotatedMember annotatedMember = _member;
        if (null == annotatedMember) {
            return TypeFactory.unknownType();
        }
        return annotatedMember.getType();
    }
    public Class<?> getRawPrimaryType() {
        final AnnotatedMember annotatedMember = _member;
        if (null == annotatedMember) {
            return Object.class;
        }
        return annotatedMember.getRawType();
    }
    public JsonInclude.Value findInclusion() {
        return _inclusion;
    }
    public boolean hasSetter() {
        return null != getSetter();
    }
    public boolean hasField() {
        return _member instanceof AnnotatedField;
    }
    public boolean hasConstructorParameter() {
        return _member instanceof AnnotatedParameter;
    }
    public AnnotatedMethod getGetter() {
        final AnnotatedMember annotatedMember = _member;
        if ((annotatedMember instanceof AnnotatedMethod) && 0 == ((AnnotatedMethod) annotatedMember).getParameterCount()) {
            return (AnnotatedMethod) _member;
        }
        return null;
    }
    public AnnotatedMethod getSetter() {
        final AnnotatedMember annotatedMember = _member;
        if ((annotatedMember instanceof AnnotatedMethod) && 1 == ((AnnotatedMethod) annotatedMember).getParameterCount()) {
            return (AnnotatedMethod) _member;
        }
        return null;
    }
    public AnnotatedField getField() {
        final AnnotatedMember annotatedMember = _member;
        if (annotatedMember instanceof AnnotatedField) {
            return (AnnotatedField) annotatedMember;
        }
        return null;
    }
    public AnnotatedParameter getConstructorParameter() {
        final AnnotatedMember annotatedMember = _member;
        if (annotatedMember instanceof AnnotatedParameter) {
            return (AnnotatedParameter) annotatedMember;
        }
        return null;
    }
    public Iterator<AnnotatedParameter> getConstructorParameters() {
        final AnnotatedParameter constructorParameter = this.getConstructorParameter();
        if (null == constructorParameter) {
            return ClassUtil.emptyIterator();
        }
        return Collections.singleton(constructorParameter).iterator();
    }
    public AnnotatedMember getPrimaryMember() {
        return _member;
    }
}
