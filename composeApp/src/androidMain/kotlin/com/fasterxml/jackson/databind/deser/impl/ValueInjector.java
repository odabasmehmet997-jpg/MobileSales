package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;

import java.io.IOException;

public class ValueInjector extends BeanProperty.Std {
    private static final long serialVersionUID = 1;
    protected final Object _valueId;
    public ValueInjector(final PropertyName propertyName, final JavaType javaType, final AnnotatedMember annotatedMember, final Object obj) {
        super(propertyName, javaType, null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
        _valueId = obj;
    }
    public ValueInjector(final PropertyName propertyName, final JavaType javaType, final Annotations annotations, final AnnotatedMember annotatedMember, final Object obj) {
        this(propertyName, javaType, annotatedMember, obj);
    }
    public Object findValue(final DeserializationContext deserializationContext, final Object obj) throws JsonMappingException {
        return deserializationContext.findInjectableValue(_valueId, this, obj);
    }
    public void inject(final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        _member.setValue(obj, this.findValue(deserializationContext, obj));
    }
}
