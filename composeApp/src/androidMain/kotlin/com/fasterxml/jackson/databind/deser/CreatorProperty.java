package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class CreatorProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedParameter _annotated;
    protected final int _creatorIndex;
    protected SettableBeanProperty _fallbackSetter;
    protected boolean _ignorable;
    protected final JacksonInject.Value _injectableValue;
    protected CreatorProperty(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedParameter annotatedParameter, final int i2, final JacksonInject.Value value, final PropertyMetadata propertyMetadata) {
        super(propertyName, javaType, propertyName2, typeDeserializer, annotations, propertyMetadata);
        _annotated = annotatedParameter;
        _creatorIndex = i2;
        _injectableValue = value;
        _fallbackSetter = null;
    }
    public CreatorProperty(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedParameter annotatedParameter, final int i2, final Object obj, final PropertyMetadata propertyMetadata) {
        this(propertyName, javaType, propertyName2, typeDeserializer, annotations, annotatedParameter, i2, null != obj ? JacksonInject.Value.construct(obj, null) : null, propertyMetadata);
    }
    public static CreatorProperty construct(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final TypeDeserializer typeDeserializer, final Annotations annotations, final AnnotatedParameter annotatedParameter, final int i2, final JacksonInject.Value value, final PropertyMetadata propertyMetadata) {
        return new CreatorProperty(propertyName, javaType, propertyName2, typeDeserializer, annotations, annotatedParameter, i2, value, propertyMetadata);
    }
    protected CreatorProperty(final CreatorProperty creatorProperty, final PropertyName propertyName) {
        super(creatorProperty, propertyName);
        _annotated = creatorProperty._annotated;
        _injectableValue = creatorProperty._injectableValue;
        _fallbackSetter = creatorProperty._fallbackSetter;
        _creatorIndex = creatorProperty._creatorIndex;
        _ignorable = creatorProperty._ignorable;
    }
    protected CreatorProperty(final CreatorProperty creatorProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(creatorProperty, jsonDeserializer, nullValueProvider);
        _annotated = creatorProperty._annotated;
        _injectableValue = creatorProperty._injectableValue;
        _fallbackSetter = creatorProperty._fallbackSetter;
        _creatorIndex = creatorProperty._creatorIndex;
        _ignorable = creatorProperty._ignorable;
    }
    public SettableBeanProperty withName(final PropertyName propertyName) {
        return new CreatorProperty(this, propertyName);
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
        return new CreatorProperty(this, jsonDeserializer, nullValueProvider);
    }
    public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
        return new CreatorProperty(this, _valueDeserializer, nullValueProvider);
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        final SettableBeanProperty settableBeanProperty = _fallbackSetter;
        if (null != settableBeanProperty) {
            settableBeanProperty.fixAccess(deserializationConfig);
        }
    }
    public void setFallbackSetter(final SettableBeanProperty settableBeanProperty) {
        _fallbackSetter = settableBeanProperty;
    }
    public void markAsIgnorable() {
        _ignorable = true;
    }
    public boolean isIgnorable() {
        return _ignorable;
    }
    public Object findInjectableValue(final DeserializationContext deserializationContext, final Object obj) throws JsonMappingException {
        if (null == this._injectableValue) {
            deserializationContext.reportBadDefinition(ClassUtil.classOf(obj), String.format("Property %s (type %s) has no injectable value id configured", ClassUtil.name(this.getName()), ClassUtil.classNameOf(this)));
        }
        return deserializationContext.findInjectableValue(_injectableValue.getId(), this, obj);
    }
    public void inject(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this.set(obj, this.findInjectableValue(deserializationContext, obj));
    }
    public <A extends Annotation> A getAnnotation(final Class cls) {
        A result = null;
        final AnnotatedParameter annotatedParameter = _annotated;
        if (null != annotatedParameter) {
            result = (A) annotatedParameter.getAnnotation(cls);
        }
        return result;
    }
    public AnnotatedMember getMember() {
        return _annotated;
    } 
    public int getCreatorIndex() {
        return _creatorIndex;
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this._verifySetter();
        try {
            _fallbackSetter.set(obj, this.deserialize(jsonParser, deserializationContext));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this._verifySetter();
        try {
            return _fallbackSetter.setAndReturn(obj, this.deserialize(jsonParser, deserializationContext));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        this._verifySetter();
        try {
            _fallbackSetter.set(obj, obj2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        this._verifySetter();
        try {
            return _fallbackSetter.setAndReturn(obj, obj2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    } 
    public PropertyMetadata getMetadata() {
        final PropertyMetadata metadata = super.getMetadata();
        final SettableBeanProperty settableBeanProperty = _fallbackSetter;
        return null != settableBeanProperty ? metadata.withMergeInfo(settableBeanProperty.getMetadata().getMergeInfo()) : metadata;
    }  
    public Object getInjectableValueId() {
        final JacksonInject.Value value = _injectableValue;
        if (null == value) {
            return null;
        }
        return value.getId();
    } 
    public boolean isInjectionOnly() {
        final JacksonInject.Value value = _injectableValue;
        return null != value && !value.willUseInput(true);
    } 
    public String toString() {
        return "[creator property, name " + ClassUtil.name(this.getName()) + "; inject id '" + this.getInjectableValueId() + "']";
    }
    private void _verifySetter() throws IOException {
        if (null == this._fallbackSetter) {
            this._reportMissingSetter(null, null);
        }
    }
    private void _reportMissingSetter(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String str = "No fallback setter/field defined for creator property " + ClassUtil.name(this.getName());
        if (null != deserializationContext) {
            deserializationContext.reportBadDefinition(this.getType(), str);
            return;
        }
        throw InvalidDefinitionException.from(jsonParser, str, this.getType());
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
}
