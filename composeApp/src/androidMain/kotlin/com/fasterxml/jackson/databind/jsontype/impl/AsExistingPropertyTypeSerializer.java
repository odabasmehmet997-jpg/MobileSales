package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExistingPropertyTypeSerializer extends AsPropertyTypeSerializer {
    public AsExistingPropertyTypeSerializer(final TypeIdResolver typeIdResolver, final BeanProperty beanProperty, final String str) {
        super(typeIdResolver, beanProperty, str);
    }
     public AsExistingPropertyTypeSerializer forProperty(final BeanProperty beanProperty) {
        return _property == beanProperty ? this : new AsExistingPropertyTypeSerializer(_idResolver, beanProperty, _typePropertyName);
    }
     public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return JsonTypeInfo.EnumC1184As.EXISTING_PROPERTY;
    }
}
