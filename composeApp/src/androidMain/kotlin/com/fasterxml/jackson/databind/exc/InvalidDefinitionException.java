package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public class InvalidDefinitionException extends JsonMappingException {
    protected transient BeanDescription _beanDesc;
    protected transient BeanPropertyDefinition _property;
    protected final JavaType _type;

    protected InvalidDefinitionException(final JsonParser jsonParser, final String str, final JavaType javaType) {
        super(jsonParser, str);
        _type = javaType;
        _beanDesc = null;
        _property = null;
    }

    protected InvalidDefinitionException(final JsonGenerator jsonGenerator, final String str, final JavaType javaType) {
        super(jsonGenerator, str);
        _type = javaType;
        _beanDesc = null;
        _property = null;
    }

    protected InvalidDefinitionException(final JsonParser jsonParser, final String str, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition) {
        super(jsonParser, str);
        _type = null == beanDescription ? null : beanDescription.getType();
        _beanDesc = beanDescription;
        _property = beanPropertyDefinition;
    }

    protected InvalidDefinitionException(final JsonGenerator jsonGenerator, final String str, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition) {
        super(jsonGenerator, str);
        _type = null == beanDescription ? null : beanDescription.getType();
        _beanDesc = beanDescription;
        _property = beanPropertyDefinition;
    }

    public static InvalidDefinitionException from(final JsonParser jsonParser, final String str, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition) {
        return new InvalidDefinitionException(jsonParser, str, beanDescription, beanPropertyDefinition);
    }

    public static InvalidDefinitionException from(final JsonParser jsonParser, final String str, final JavaType javaType) {
        return new InvalidDefinitionException(jsonParser, str, javaType);
    }

    public static InvalidDefinitionException from(final JsonGenerator jsonGenerator, final String str, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition) {
        return new InvalidDefinitionException(jsonGenerator, str, beanDescription, beanPropertyDefinition);
    }

    public static InvalidDefinitionException from(final JsonGenerator jsonGenerator, final String str, final JavaType javaType) {
        return new InvalidDefinitionException(jsonGenerator, str, javaType);
    }

    public JavaType getType() {
        return _type;
    }

    public BeanDescription getBeanDescription() {
        return _beanDesc;
    }

    public BeanPropertyDefinition getProperty() {
        return _property;
    }
}
