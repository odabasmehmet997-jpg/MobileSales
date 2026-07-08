package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGeneratorsPropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer, Serializable {
    private static final long serialVersionUID = 1;
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final Map<String, SettableBeanProperty> _backRefProperties;
    protected final JavaType _baseType;
    protected final ObjectIdReader _objectIdReader;
    protected transient Map<String, SettableBeanProperty> _properties;
    public boolean isCachable() {
        return true;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return null;
    }
    public AbstractDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final Map<String, SettableBeanProperty> map, final Map<String, SettableBeanProperty> map2) {
        final JavaType type = beanDescription.getType();
        _baseType = type;
        _objectIdReader = beanDeserializerBuilder.getObjectIdReader();
        _backRefProperties = map;
        _properties = map2;
        final Class<?> rawClass = type.getRawClass();
        _acceptString = rawClass.isAssignableFrom(String.class);
        boolean z = true;
        _acceptBoolean = rawClass == Boolean.TYPE || rawClass.isAssignableFrom(Boolean.class);
        _acceptInt = rawClass == Integer.TYPE || rawClass.isAssignableFrom(Integer.class);
        if (rawClass != Double.TYPE && !rawClass.isAssignableFrom(Double.class)) {
            z = false;
        }
        _acceptDouble = z;
    }
    public AbstractDeserializer(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final Map<String, SettableBeanProperty> map) {
        this(beanDeserializerBuilder, beanDescription, map, null);
    }
    protected AbstractDeserializer(final BeanDescription beanDescription) {
        final JavaType type = beanDescription.getType();
        _baseType = type;
        _objectIdReader = null;
        _backRefProperties = null;
        final Class<?> rawClass = type.getRawClass();
        _acceptString = rawClass.isAssignableFrom(String.class);
        boolean z = true;
        _acceptBoolean = rawClass == Boolean.TYPE || rawClass.isAssignableFrom(Boolean.class);
        _acceptInt = rawClass == Integer.TYPE || rawClass.isAssignableFrom(Integer.class);
        if (rawClass != Double.TYPE && !rawClass.isAssignableFrom(Double.class)) {
            z = false;
        }
        _acceptDouble = z;
    }
    protected AbstractDeserializer(final AbstractDeserializer abstractDeserializer, final ObjectIdReader objectIdReader, final Map<String, SettableBeanProperty> map) {
        _baseType = abstractDeserializer._baseType;
        _backRefProperties = abstractDeserializer._backRefProperties;
        _acceptString = abstractDeserializer._acceptString;
        _acceptBoolean = abstractDeserializer._acceptBoolean;
        _acceptInt = abstractDeserializer._acceptInt;
        _acceptDouble = abstractDeserializer._acceptDouble;
        _objectIdReader = objectIdReader;
        _properties = map;
    }
    public static AbstractDeserializer constructForNonPOJO(final BeanDescription beanDescription) {
        return new AbstractDeserializer(beanDescription);
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final AnnotatedMember member;
        final ObjectIdInfo objectIdInfoFindObjectIdInfo;
        final ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance;
        final SettableBeanProperty settableBeanProperty;
        final JavaType javaType;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == beanProperty || null == annotationIntrospector || null == (member = beanProperty.getMember()) || null == (objectIdInfoFindObjectIdInfo = annotationIntrospector.findObjectIdInfo(member))) {
            return null == this._properties ? this : new AbstractDeserializer(this, _objectIdReader, null);
        }
        ObjectIdResolver objectIdResolverObjectIdResolverInstance = deserializationContext.objectIdResolverInstance(member, objectIdInfoFindObjectIdInfo);
        final ObjectIdInfo objectIdInfoFindObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, objectIdInfoFindObjectIdInfo);
        final Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfoFindObjectReferenceInfo.getGeneratorType();
        if (ObjectIdGeneratorsPropertyGenerator.class == generatorType) {
            final PropertyName propertyName = objectIdInfoFindObjectReferenceInfo.getPropertyName();
            final Map<String, SettableBeanProperty> map = _properties;
            final SettableBeanProperty settableBeanProperty2 = null == map ? null : map.get(propertyName.getSimpleName());
            if (null == settableBeanProperty2) {
                deserializationContext.reportBadDefinition(_baseType, String.format("Invalid Object Id definition for %s: cannot find property with name %s", ClassUtil.nameOf(this.handledType()), ClassUtil.name(propertyName)));
            }
            final JavaType type = settableBeanProperty2.getType();
            objectIdGeneratorObjectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfoFindObjectReferenceInfo.getScope());
            javaType = type;
            settableBeanProperty = settableBeanProperty2;
        } else {
            objectIdResolverObjectIdResolverInstance = deserializationContext.objectIdResolverInstance(member, objectIdInfoFindObjectReferenceInfo);
            final JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), ObjectIdGenerator.class)[0];
            objectIdGeneratorObjectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(member, objectIdInfoFindObjectReferenceInfo);
            settableBeanProperty = null;
            javaType = javaType2;
        }
        return new AbstractDeserializer(this, ObjectIdReader.construct(javaType, objectIdInfoFindObjectReferenceInfo.getPropertyName(), objectIdGeneratorObjectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty, objectIdResolverObjectIdResolverInstance), null);
    }
    public Class<?> handledType() {
        return _baseType.getRawClass();
    }
    public LogicalType logicalType() {
        return LogicalType.POJO;
    }
    public ObjectIdReader getObjectIdReader() {
        return _objectIdReader;
    }
    public SettableBeanProperty findBackReference(final String str) {
        final Map<String, SettableBeanProperty> map = _backRefProperties;
        if (null == map) {
            return null;
        }
        return map.get(str);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        JsonToken jsonTokenCurrentToken;
        if (null != this._objectIdReader && null != (jsonTokenCurrentToken = jsonParser.currentToken())) {
            if (jsonTokenCurrentToken.isScalarValue()) {
                return this._deserializeFromObjectId(jsonParser, deserializationContext);
            }
            if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
                jsonTokenCurrentToken = jsonParser.nextToken();
            }
            if (JsonToken.FIELD_NAME == jsonTokenCurrentToken && _objectIdReader.maySerializeAsObject() && _objectIdReader.isValidReferencePropertyName(jsonParser.currentName(), jsonParser)) {
                return this._deserializeFromObjectId(jsonParser, deserializationContext);
            }
        }
        final Object obj_deserializeIfNatural = this._deserializeIfNatural(jsonParser, deserializationContext);
        return null != obj_deserializeIfNatural ? obj_deserializeIfNatural : typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.handleMissingInstantiator(_baseType.getRawClass(), new ValueInstantiator.Base(_baseType), jsonParser, "abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information");
    }
    protected Object _deserializeIfNatural(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.currentTokenId()) {
            case 6:
                if (_acceptString) {
                    return jsonParser.getText();
                }
                return null;
            case 7:
                if (_acceptInt) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                return null;
            case 8:
                if (_acceptDouble) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                return null;
            case 9:
                if (_acceptBoolean) {
                    return Boolean.TRUE;
                }
                return null;
            case 10:
                if (_acceptBoolean) {
                    return Boolean.FALSE;
                }
                return null;
            default:
                return null;
        }
    }
    protected Object _deserializeFromObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object objectReference = _objectIdReader.readObjectReference(jsonParser, deserializationContext);
        final ObjectIdReader objectIdReader = _objectIdReader;
        final ReadableObjectId readableObjectIdFindObjectId = deserializationContext.findObjectId(objectReference, objectIdReader.generator, objectIdReader.resolver);
        final Object objResolve = readableObjectIdFindObjectId.resolve();
        if (null != objResolve) {
            return objResolve;
        }
        throw new UnresolvedForwardReference(jsonParser, "Could not resolve Object Id [" + objectReference + "] -- unresolved forward-reference?", jsonParser.getCurrentLocation(), readableObjectIdFindObjectId);
    }
}
