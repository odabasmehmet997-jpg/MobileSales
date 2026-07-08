package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsArrayTypeSerializer extends TypeSerializerBase {
    public AsArrayTypeSerializer(final TypeIdResolver typeIdResolver, final BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }
    public AsArrayTypeSerializer forProperty(final BeanProperty beanProperty) {
        return _property == beanProperty ? this : new AsArrayTypeSerializer(_idResolver, beanProperty);
    }

    public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return JsonTypeInfo.EnumC1184As.WRAPPER_ARRAY;
    }
}
