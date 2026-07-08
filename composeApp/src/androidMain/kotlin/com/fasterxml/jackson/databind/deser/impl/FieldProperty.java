package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
 
public final class FieldProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    private final AnnotatedField _annotated;
    private final transient Field _field;
    private final boolean _skipNulls;
    public FieldProperty(final BeanPropertyDefinition beanPropertyDefinition, final JavaType javaType, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedField annotatedField) {
        super(beanPropertyDefinition, javaType, typeDeserializer, annotations);
        _annotated = annotatedField;
        _field = annotatedField.getAnnotated();
        _skipNulls = NullsConstantProvider.isSkipper(_nullProvider);
    }
    private FieldProperty(final FieldProperty fieldProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(fieldProperty, jsonDeserializer, nullValueProvider);
        _annotated = fieldProperty._annotated;
        _field = fieldProperty._field;
        _skipNulls = NullsConstantProvider.isSkipper(nullValueProvider);
    }
    private FieldProperty(final FieldProperty fieldProperty, final PropertyName propertyName) {
        super(fieldProperty, propertyName);
        _annotated = fieldProperty._annotated;
        _field = fieldProperty._field;
        _skipNulls = fieldProperty._skipNulls;
    }
    private FieldProperty(final FieldProperty fieldProperty) {
        super(fieldProperty);
        final AnnotatedField annotatedField = fieldProperty._annotated;
        _annotated = annotatedField;
        final Field annotated = annotatedField.getAnnotated();
        if (null == annotated) {
            throw new IllegalArgumentException("Missing field (broken JDK (de)serialization?)");
        }
        _field = annotated;
        _skipNulls = fieldProperty._skipNulls;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new FieldProperty(this, propertyName);
    }
    public SettableBeanProperty withValueDeserializer(final JsonDeserializer<?> jsonDeserializer) {
        final JsonDeserializer<?> jsonDeserializer2 = _valueDeserializer;
        if (jsonDeserializer2 == jsonDeserializer) {
            return this;
        }
        NullValueProvider nullValueProvider = _nullProvider;
        if (jsonDeserializer2 == nullValueProvider) {
            nullValueProvider = jsonDeserializer;
        }
        return new FieldProperty(this, jsonDeserializer, nullValueProvider);
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new FieldProperty(this, _valueDeserializer, nullValueProvider);
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        ClassUtil.checkAndFixAccess(_field, deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }
    public <A extends Annotation> A getAnnotation(final Class cls) {
        final AnnotatedField annotatedField = _annotated;
        if (null == annotatedField) {
            return null;
        }
        return (A) annotatedField.getAnnotation(cls);
    }
    public AnnotatedMember getMember() {
        return _annotated;
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException {
        final Object objDeserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (_skipNulls) {
                return;
            } else {
                objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
            }
        } else {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null == typeDeserializer) {
                final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                if (null != objDeserialize) {
                    objDeserializeWithType = objDeserialize;
                } else if (_skipNulls) {
                    return;
                } else {
                    objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                }
            } else {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            _field.set(obj, objDeserializeWithType);
        } catch (final Exception e2) {
            this._throwAsIOE(jsonParser, e2, objDeserializeWithType);
        }
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IllegalAccessException, IOException, IllegalArgumentException {
        final Object objDeserializeWithType;
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            if (_skipNulls) {
                return obj;
            }
            objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
        } else {
            final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
            if (null == typeDeserializer) {
                final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
                if (null != objDeserialize) {
                    objDeserializeWithType = objDeserialize;
                } else {
                    if (_skipNulls) {
                        return obj;
                    }
                    objDeserializeWithType = _nullProvider.getNullValue(deserializationContext);
                }
            } else {
                objDeserializeWithType = _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        }
        try {
            _field.set(obj, objDeserializeWithType);
        } catch (final Exception e2) {
            this._throwAsIOE(jsonParser, e2, objDeserializeWithType);
        }
        return obj;
    }
    public void set(final Object obj, final Object obj2) throws IllegalAccessException, IOException, IllegalArgumentException {
        try {
            _field.set(obj, obj2);
        } catch (final Exception e2) {
            this._throwAsIOE(e2, obj2);
        }
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IllegalAccessException, IOException, IllegalArgumentException {
        try {
            _field.set(obj, obj2);
        } catch (final Exception e2) {
            this._throwAsIOE(e2, obj2);
        }
        return obj;
    }
    Object readResolve() {
        return new FieldProperty(this);
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
}
