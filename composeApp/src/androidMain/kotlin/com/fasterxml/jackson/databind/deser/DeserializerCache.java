package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.*;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.LRUMap;

import java.io.Serializable;
import java.util.HashMap;

public final class DeserializerCache implements Serializable {
    private static final long serialVersionUID = 1;
    private final LRUMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers;
    private final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers;
    public DeserializerCache() {
        this(2000);
    }
    public DeserializerCache(final int i2) {
        _incompleteDeserializers = new HashMap<>(8);
        _cachedDeserializers = new LRUMap<>(Math.min(64, i2 >> 2), i2);
    }
    Object writeReplace() {
        _incompleteDeserializers.clear();
        return this;
    }
    public int cachedDeserializersCount() {
        return _cachedDeserializers.size();
    }
    public void flushCachedDeserializers() {
        _cachedDeserializers.clear();
    }
    public JsonDeserializer<Object> findValueDeserializer(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = this._findCachedDeserializer(javaType);
        if (null != jsonDeserializer_findCachedDeserializer) {
            return jsonDeserializer_findCachedDeserializer;
        }
        final JsonDeserializer<Object> jsonDeserializer_createAndCacheValueDeserializer = this._createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        return null == jsonDeserializer_createAndCacheValueDeserializer ? this._handleUnknownValueDeserializer(deserializationContext, javaType) : jsonDeserializer_createAndCacheValueDeserializer;
    }
    public KeyDeserializer findKeyDeserializer(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType) throws JsonMappingException {
        final KeyDeserializer keyDeserializerCreateKeyDeserializer = deserializerFactory.createKeyDeserializer(deserializationContext, javaType);
        if (0 == keyDeserializerCreateKeyDeserializer) {
            return this._handleUnknownKeyDeserializer(deserializationContext, javaType);
        }
        if (keyDeserializerCreateKeyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializerCreateKeyDeserializer).resolve(deserializationContext);
        }
        return keyDeserializerCreateKeyDeserializer;
    }
    public boolean hasValueDeserializerFor(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = this._findCachedDeserializer(javaType);
        if (null == jsonDeserializer_findCachedDeserializer) {
            jsonDeserializer_findCachedDeserializer = this._createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        }
        return null != jsonDeserializer_findCachedDeserializer;
    }
    private JsonDeserializer<Object> _findCachedDeserializer(final JavaType javaType) {
        if (null == javaType) {
            throw new IllegalArgumentException("Null JavaType passed");
        }
        if (this._hasCustomHandlers(javaType)) {
            return null;
        }
        return _cachedDeserializers.get(javaType);
    }
    private JsonDeserializer<Object> _createAndCacheValueDeserializer(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializer;
        synchronized (_incompleteDeserializers) {
            try {
                final JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = this._findCachedDeserializer(javaType);
                if (null != jsonDeserializer_findCachedDeserializer) {
                    return jsonDeserializer_findCachedDeserializer;
                }
                final int size = _incompleteDeserializers.size();
                if (0 < size && null != (jsonDeserializer = this._incompleteDeserializers.get(javaType))) {
                    return jsonDeserializer;
                }
                try {
                    return this._createAndCache2(deserializationContext, deserializerFactory, javaType);
                } finally {
                    if (0 == size && 0 < this._incompleteDeserializers.size()) {
                        _incompleteDeserializers.clear();
                    }
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    private JsonDeserializer<Object> _createAndCache2(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType) throws JsonMappingException {
        try {
            final JsonDeserializer<Object> jsonDeserializer_createDeserializer = this._createDeserializer(deserializationContext, deserializerFactory, javaType);
            if (false != jsonDeserializer_createDeserializer) {
                final boolean z = !this._hasCustomHandlers(javaType) && jsonDeserializer_createDeserializer.isCachable();
                if (jsonDeserializer_createDeserializer instanceof ResolvableDeserializer) {
                    _incompleteDeserializers.put(javaType, jsonDeserializer_createDeserializer);
                    ((ResolvableDeserializer) jsonDeserializer_createDeserializer).resolve(deserializationContext);
                    _incompleteDeserializers.remove(javaType);
                }
                if (z) {
                    _cachedDeserializers.put(javaType, jsonDeserializer_createDeserializer);
                }
                return jsonDeserializer_createDeserializer;
            } else {
                return null;
            }
        } catch (final IllegalArgumentException e2) {
            throw JsonMappingException.from(deserializationContext, ClassUtil.exceptionMessage(e2), e2);
        }
    }
    private JsonDeserializer<Object> _createDeserializer(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isAbstract() || javaType.isMapLikeType() || javaType.isCollectionLikeType()) {
            javaType = deserializerFactory.mapAbstractType(config, javaType);
        }
        BeanDescription beanDescriptionIntrospect = config.introspect(javaType);
        final JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = this.findDeserializerFromAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo());
        if (null != jsonDeserializerFindDeserializerFromAnnotation) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        final JavaType javaTypeModifyTypeByAnnotation = this.modifyTypeByAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo(), javaType);
        if (javaTypeModifyTypeByAnnotation != javaType) {
            beanDescriptionIntrospect = config.introspect(javaTypeModifyTypeByAnnotation);
            javaType = javaTypeModifyTypeByAnnotation;
        }
        final Class<?> clsFindPOJOBuilder = beanDescriptionIntrospect.findPOJOBuilder();
        if (null != clsFindPOJOBuilder) {
            return deserializerFactory.createBuilderBasedDeserializer(deserializationContext, javaType, beanDescriptionIntrospect, clsFindPOJOBuilder);
        }
        final Converter<Object, Object> converterFindDeserializationConverter = beanDescriptionIntrospect.findDeserializationConverter();
        if (null == converterFindDeserializationConverter) {
            return (JsonDeserializer<Object>) this._createDeserializer2(deserializationContext, deserializerFactory, javaType, beanDescriptionIntrospect);
        }
        final JavaType inputType = converterFindDeserializationConverter.getInputType(deserializationContext.getTypeFactory());
        if (!inputType.hasRawClass(javaType.getRawClass())) {
            beanDescriptionIntrospect = config.introspect(inputType);
        }
        return new StdDelegatingDeserializer(converterFindDeserializationConverter, inputType, this._createDeserializer2(deserializationContext, deserializerFactory, inputType, beanDescriptionIntrospect));
    }
    private JsonDeserializer<?> _createDeserializer2(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isEnumType()) {
            return deserializerFactory.createEnumDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return deserializerFactory.createArrayDeserializer(deserializationContext, (ArrayType) javaType, beanDescription);
            }
            if (javaType.isMapLikeType() && JsonFormat.Shape.OBJECT != beanDescription.findExpectedFormat(null).getShape()) {
                final MapLikeType mapLikeType = (MapLikeType) javaType;
                if (mapLikeType instanceof MapType) {
                    return deserializerFactory.createMapDeserializer(deserializationContext, (MapType) mapLikeType, beanDescription);
                }
                return deserializerFactory.createMapLikeDeserializer(deserializationContext, mapLikeType, beanDescription);
            }
            if (javaType.isCollectionLikeType() && JsonFormat.Shape.OBJECT != beanDescription.findExpectedFormat(null).getShape()) {
                final CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                if (collectionLikeType instanceof CollectionType) {
                    return deserializerFactory.createCollectionDeserializer(deserializationContext, (CollectionType) collectionLikeType, beanDescription);
                }
                return deserializerFactory.createCollectionLikeDeserializer(deserializationContext, collectionLikeType, beanDescription);
            }
        }
        if (javaType.isReferenceType()) {
            return deserializerFactory.createReferenceDeserializer(deserializationContext, (ReferenceType) javaType, beanDescription);
        }
        if (JsonNode.class.isAssignableFrom(javaType.getRawClass())) {
            return deserializerFactory.createTreeDeserializer(config, javaType, beanDescription);
        }
        return deserializerFactory.createBeanDeserializer(deserializationContext, javaType, beanDescription);
    }
    private JsonDeserializer<Object> findDeserializerFromAnnotation(final DeserializationContext deserializationContext, final Annotated annotated) throws JsonMappingException {
        final Object objFindDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (null == objFindDeserializer) {
            return null;
        }
        return this.findConvertingDeserializer(deserializationContext, annotated, deserializationContext.deserializerInstance(annotated, objFindDeserializer));
    }
    private JsonDeserializer<Object> findConvertingDeserializer(final DeserializationContext deserializationContext, final Annotated annotated, final JsonDeserializer<Object> jsonDeserializer) throws JsonMappingException {
        final Converter<Object, Object> converterFindConverter = this.findConverter(deserializationContext, annotated);
        return null == converterFindConverter ? jsonDeserializer : new StdDelegatingDeserializer(converterFindConverter, converterFindConverter.getInputType(deserializationContext.getTypeFactory()), jsonDeserializer);
    }
    private Converter<Object, Object> findConverter(final DeserializationContext deserializationContext, final Annotated annotated) throws JsonMappingException {
        final Object objFindDeserializationConverter = deserializationContext.getAnnotationIntrospector().findDeserializationConverter(annotated);
        if (null == objFindDeserializationConverter) {
            return null;
        }
        return deserializationContext.converterInstance(annotated, objFindDeserializationConverter);
    }
    private JavaType modifyTypeByAnnotation(final DeserializationContext deserializationContext, final Annotated annotated, JavaType javaType) throws JsonMappingException {
        final Object objFindContentDeserializer;
        final JsonDeserializer<Object> jsonDeserializerDeserializerInstance;
        final JavaType keyType;
        final Object objFindKeyDeserializer;
        final KeyDeserializer keyDeserializerKeyDeserializerInstance;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector) {
            return javaType;
        }
        if (javaType.isMapLikeType() && null != (keyType = javaType.getKeyType()) && null == keyType.getValueHandler() && null != (objFindKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) && null != (keyDeserializerKeyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, objFindKeyDeserializer))) {
            javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerKeyDeserializerInstance);
        }
        final JavaType contentType = javaType.getContentType();
        if (null != contentType && null == contentType.getValueHandler() && null != (objFindContentDeserializer = annotationIntrospector.findContentDeserializer(annotated))) {
            if (objFindContentDeserializer instanceof JsonDeserializer) {
                jsonDeserializerDeserializerInstance = (JsonDeserializer) objFindContentDeserializer;
            } else {
                final Class<?> cls_verifyAsClass = this._verifyAsClass(objFindContentDeserializer, "findContentDeserializer", JsonDeserializer.None.class);
                jsonDeserializerDeserializerInstance = null != cls_verifyAsClass ? deserializationContext.deserializerInstance(annotated, cls_verifyAsClass) : null;
            }
            if (null != jsonDeserializerDeserializerInstance) {
                javaType = javaType.withContentValueHandler(jsonDeserializerDeserializerInstance);
            }
        }
        return annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotated, javaType);
    }
    private boolean _hasCustomHandlers(final JavaType javaType) {
        if (!javaType.isContainerType()) {
            return false;
        }
        final JavaType contentType = javaType.getContentType();
        if (null == contentType || (null == contentType.getValueHandler() && null == contentType.getTypeHandler())) {
            return javaType.isMapLikeType() && null != javaType.getKeyType().getValueHandler();
        }
        return true;
    }
    private Class<?> _verifyAsClass(final Object obj, final String str, final Class<?> cls) {
        if (null == obj) {
            return null;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector." + str + "() returned value of type " + obj.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
        }
        final Class<?> cls2 = (Class) obj;
        if (cls2 == cls || ClassUtil.isBogusClass(cls2)) {
            return null;
        }
        return cls2;
    }
    private JsonDeserializer<Object> _handleUnknownValueDeserializer(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        if (!ClassUtil.isConcrete(javaType.getRawClass())) {
            return deserializationContext.reportBadDefinition(javaType, "Cannot find a Value deserializer for abstract type " + javaType);
        }
        return deserializationContext.reportBadDefinition(javaType, "Cannot find a Value deserializer for type " + javaType);
    }
    private KeyDeserializer _handleUnknownKeyDeserializer(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        return deserializationContext.reportBadDefinition(javaType, "Cannot find a (Map) Key deserializer for type " + javaType);
    }
}
