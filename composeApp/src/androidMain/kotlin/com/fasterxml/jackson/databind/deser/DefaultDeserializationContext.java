package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.util.*;

public abstract class DefaultDeserializationContext extends DeserializationContext {
    private static final long serialVersionUID = 1;
    private List<ObjectIdResolver> _objectIdResolvers;
    protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;
    public abstract DefaultDeserializationContext createDummyInstance(DeserializationConfig deserializationConfig);
    public abstract DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues);
    public abstract DefaultDeserializationContext with(DeserializerFactory deserializerFactory);
    protected DefaultDeserializationContext(final DeserializerFactory deserializerFactory, final DeserializerCache deserializerCache) {
        super(deserializerFactory, deserializerCache);
    }
    protected DefaultDeserializationContext(final DefaultDeserializationContext defaultDeserializationContext, final DeserializationConfig deserializationConfig, final JsonParser jsonParser, final InjectableValues injectableValues) {
        super(defaultDeserializationContext, deserializationConfig, jsonParser, injectableValues);
    }
    protected DefaultDeserializationContext(final DefaultDeserializationContext defaultDeserializationContext, final DeserializationConfig deserializationConfig) {
        super(defaultDeserializationContext, deserializationConfig);
    }
    protected DefaultDeserializationContext(final DefaultDeserializationContext defaultDeserializationContext, final DeserializerFactory deserializerFactory) {
        super(defaultDeserializationContext, deserializerFactory);
    }
    protected DefaultDeserializationContext(final DefaultDeserializationContext defaultDeserializationContext) {
        super(defaultDeserializationContext);
    }
    public DefaultDeserializationContext copy() {
        throw new IllegalStateException("DefaultDeserializationContext sub-class not overriding copy()");
    }
    public ReadableObjectId findObjectId(final Object obj, final ObjectIdGenerator<?> objectIdGenerator, final ObjectIdResolver objectIdResolver) {
        ObjectIdResolver objectIdResolverNewForDeserialization = null;
        if (null == obj) {
            return null;
        }
        final ObjectIdGenerator.IdKey idKeyKey = objectIdGenerator.key(obj);
        final LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> linkedHashMap = _objectIds;
        if (null == linkedHashMap) {
            _objectIds = new LinkedHashMap<>();
        } else {
            final ReadableObjectId readableObjectId = linkedHashMap.get(idKeyKey);
            if (null != readableObjectId) {
                return readableObjectId;
            }
        }
        final List<ObjectIdResolver> list = _objectIdResolvers;
        if (null == list) {
            _objectIdResolvers = new ArrayList(8);
        } else {
            final Iterator<ObjectIdResolver> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                final ObjectIdResolver next = it.next();
                if (next.canUseFor(objectIdResolver)) {
                    objectIdResolverNewForDeserialization = next;
                    break;
                }
            }
        }
        if (null == objectIdResolverNewForDeserialization) {
            objectIdResolverNewForDeserialization = objectIdResolver.newForDeserialization(this);
            _objectIdResolvers.add(objectIdResolverNewForDeserialization);
        }
        final ReadableObjectId readableObjectIdCreateReadableObjectId = this.createReadableObjectId(idKeyKey);
        readableObjectIdCreateReadableObjectId.setResolver(objectIdResolverNewForDeserialization);
        _objectIds.put(idKeyKey, readableObjectIdCreateReadableObjectId);
        return readableObjectIdCreateReadableObjectId;
    }
    protected ReadableObjectId createReadableObjectId(final ObjectIdGenerator.IdKey idKey) {
        return new ReadableObjectId(idKey);
    }
    public void checkUnresolvedObjectId() throws UnresolvedForwardReference {
        if (null != this._objectIds && this.isEnabled(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS)) {
            final Iterator<Map.Entry<ObjectIdGenerator.IdKey, ReadableObjectId>> it = _objectIds.entrySet().iterator();
            UnresolvedForwardReference unresolvedForwardReference = null;
            while (it.hasNext()) {
                final ReadableObjectId value = it.next().getValue();
                if (value.hasReferringProperties() && !this.tryToResolveUnresolvedObjectId(value)) {
                    if (null == unresolvedForwardReference) {
                        unresolvedForwardReference = new UnresolvedForwardReference(this.getParser(), "Unresolved forward references for: ");
                    }
                    final Object obj = value.getKey().key;
                    final Iterator<ReadableObjectId.Referring> itReferringProperties = (Iterator<ReadableObjectId.Referring>) value.referringProperties();
                    while (itReferringProperties.hasNext()) {
                        final ReadableObjectId.Referring next = itReferringProperties.next();
                        unresolvedForwardReference.addUnresolvedId(obj, next.getBeanType(), next.getLocation());
                    }
                }
            }
            if (null != unresolvedForwardReference) {
                throw unresolvedForwardReference;
            }
        }
    }
    protected boolean tryToResolveUnresolvedObjectId(final ReadableObjectId readableObjectId) {
        return readableObjectId.tryToResolveUnresolved(this);
    }
    public JsonDeserializer<Object> deserializerInstance(final Annotated annotated, final Object obj) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializer;
        if (null == obj) {
            return null;
        }
        if (obj instanceof JsonDeserializer) {
            jsonDeserializer = (JsonDeserializer) obj;
        } else {
            if (!(obj instanceof Class)) {
                throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
            }
            final Class<?> cls = (Class) obj;
            if (JsonDeserializer.None.class == cls || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (!JsonDeserializer.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
            }
            final HandlerInstantiator handlerInstantiator = _config.getHandlerInstantiator();
            final JsonDeserializer<?> jsonDeserializerDeserializerInstance = null != handlerInstantiator ? handlerInstantiator.deserializerInstance(_config, annotated, cls) : null;
            try {
                jsonDeserializer = null == jsonDeserializerDeserializerInstance ? (JsonDeserializer) ClassUtil.createInstance(cls, _config.canOverrideAccessModifiers()) : jsonDeserializerDeserializerInstance;
            } catch (final NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if (jsonDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) jsonDeserializer).resolve(this);
        }
        return (JsonDeserializer<Object>) jsonDeserializer;
    }
    public final KeyDeserializer keyDeserializerInstance(final Annotated annotated, final Object obj) throws JsonMappingException {
        final KeyDeserializer keyDeserializer;
        if (null == obj) {
            return null;
        }
        if (obj instanceof KeyDeserializer) {
            keyDeserializer = (KeyDeserializer) obj;
        } else {
            if (!(obj instanceof Class)) {
                throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
            }
            final Class<?> cls = (Class) obj;
            if (KeyDeserializer.None.class == cls || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (!KeyDeserializer.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<KeyDeserializer>");
            }
            final HandlerInstantiator handlerInstantiator = _config.getHandlerInstantiator();
            final KeyDeserializer keyDeserializerKeyDeserializerInstance = null != handlerInstantiator ? handlerInstantiator.keyDeserializerInstance(_config, annotated, cls) : null;
            try {
                keyDeserializer = null == keyDeserializerKeyDeserializerInstance ? (KeyDeserializer) ClassUtil.createInstance(cls, _config.canOverrideAccessModifiers()) : keyDeserializerKeyDeserializerInstance;
            } catch (final NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializer).resolve(this);
        }
        return keyDeserializer;
    }
    public Object readRootValue(final JsonParser jsonParser, final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final Object obj) throws IOException {
        if (_config.useRootWrapping()) {
            return this._unwrapAndDeserialize(jsonParser, javaType, jsonDeserializer, obj);
        }
        if (null == obj) {
            return jsonDeserializer.deserialize(jsonParser, this);
        }
        return jsonDeserializer.deserialize(jsonParser, this, obj);
    }
    protected Object _unwrapAndDeserialize(final JsonParser jsonParser, final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final Object obj) throws IOException {
        final Object objDeserialize;
        final String simpleName = _config.findRootName(javaType).getSimpleName();
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        final JsonToken jsonToken = JsonToken.START_OBJECT;
        if (jsonTokenCurrentToken != jsonToken) {
            this.reportWrongTokenException(javaType, jsonToken, "Current token not START_OBJECT (needed to unwrap root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        final JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonTokenNextToken != jsonToken2) {
            this.reportWrongTokenException(javaType, jsonToken2, "Current token not FIELD_NAME (to contain expected root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        final String strCurrentName = jsonParser.currentName();
        if (!simpleName.equals(strCurrentName)) {
            this.reportPropertyInputMismatch(javaType, strCurrentName, "Root name (%s) does not match expected (%s) for type %s", ClassUtil.name(strCurrentName), ClassUtil.name(simpleName), ClassUtil.getTypeDescription(javaType));
        }
        jsonParser.nextToken();
        if (null == obj) {
            objDeserialize = jsonDeserializer.deserialize(jsonParser, this);
        } else {
            objDeserialize = jsonDeserializer.deserialize(jsonParser, this, obj);
        }
        final JsonToken jsonTokenNextToken2 = jsonParser.nextToken();
        final JsonToken jsonToken3 = JsonToken.END_OBJECT;
        if (jsonTokenNextToken2 != jsonToken3) {
            this.reportWrongTokenException(javaType, jsonToken3, "Current token not END_OBJECT (to match wrapper object with root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        return objDeserialize;
    }
    public static final class Impl extends DefaultDeserializationContext {
        private static final long serialVersionUID = 1;

        public Impl(final DeserializerFactory deserializerFactory) {
            super(deserializerFactory, null);
        }

        private Impl(final Impl impl, final DeserializationConfig deserializationConfig, final JsonParser jsonParser, final InjectableValues injectableValues) {
            super(impl, deserializationConfig, jsonParser, injectableValues);
        }
        private Impl(final Impl impl) {
            super(impl);
        }
        private Impl(final Impl impl, final DeserializerFactory deserializerFactory) {
            super(impl, deserializerFactory);
        }
        private Impl(final Impl impl, final DeserializationConfig deserializationConfig) {
            super(impl, deserializationConfig);
        }
        public DefaultDeserializationContext copy() {
            ClassUtil.verifyMustOverride(Impl.class, this, "copy");
            return new Impl(this);
        }
        public DefaultDeserializationContext createInstance(final DeserializationConfig deserializationConfig, final JsonParser jsonParser, final InjectableValues injectableValues) {
            return new Impl(this, deserializationConfig, jsonParser, injectableValues);
        }
        public DefaultDeserializationContext createDummyInstance(final DeserializationConfig deserializationConfig) {
            return new Impl(this, deserializationConfig);
        }
        public DefaultDeserializationContext with(final DeserializerFactory deserializerFactory) {
            return new Impl(this, deserializerFactory);
        }
    }
}
