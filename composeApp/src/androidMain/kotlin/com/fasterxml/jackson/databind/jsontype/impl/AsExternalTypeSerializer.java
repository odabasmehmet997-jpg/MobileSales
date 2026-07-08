package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(final TypeIdResolver typeIdResolver, final BeanProperty beanProperty, final String str) {
        super(typeIdResolver, beanProperty);
        _typePropertyName = str;
    }
    public AsExternalTypeSerializer forProperty(final BeanProperty beanProperty) {
        return _property == beanProperty ? this : new AsExternalTypeSerializer(_idResolver, beanProperty, _typePropertyName);
    }

    public String getPropertyName() {
        return _typePropertyName;
    }

    public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY;
    }
}
