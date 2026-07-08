package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
    private static final long serialVersionUID = 1;
    protected boolean _usesExternalId() {
        return true;
    }

    public AsExternalTypeDeserializer(final JavaType javaType, final TypeIdResolver typeIdResolver, final String str, final boolean z, final JavaType javaType2) {
        super(javaType, typeIdResolver, str, z, javaType2);
    }

    public AsExternalTypeDeserializer(final AsExternalTypeDeserializer asExternalTypeDeserializer, final BeanProperty beanProperty) {
        super(asExternalTypeDeserializer, beanProperty);
    }
     public TypeDeserializer forProperty(final BeanProperty beanProperty) {
        return beanProperty == _property ? this : new AsExternalTypeDeserializer(this, beanProperty);
    }
     public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY;
    }
}
