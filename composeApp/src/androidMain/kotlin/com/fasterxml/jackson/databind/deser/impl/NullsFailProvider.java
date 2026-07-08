package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.fasterxml.jackson.databind.util.AccessPattern;

import java.io.Serializable;

public class NullsFailProvider implements NullValueProvider, Serializable {
    private static final long serialVersionUID = 1;
    protected final PropertyName _name;
    protected final JavaType _type;

    protected NullsFailProvider(final PropertyName propertyName, final JavaType javaType) {
        _name = propertyName;
        _type = javaType;
    }

    public static NullsFailProvider constructForProperty(final BeanProperty beanProperty) {
        return NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType());
    }

    public static NullsFailProvider constructForProperty(final BeanProperty beanProperty, final JavaType javaType) {
        return new NullsFailProvider(beanProperty.getFullName(), javaType);
    }

    public static NullsFailProvider constructForRootValue(final JavaType javaType) {
        return new NullsFailProvider(null, javaType);
    }

    public AccessPattern getNullAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public Object getNullValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        throw InvalidNullException.from(deserializationContext, _name, _type);
    }
}
