package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final JacksonFeatureSet<StreamReadCapability> _readCapabilities;
    protected final Class<?> _view;
    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;
    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;
    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);
    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;
    protected DeserializationContext(final DeserializerFactory deserializerFactory) {
        this(deserializerFactory, null);
    }
    protected DeserializationContext(final DeserializerFactory deserializerFactory, final DeserializerCache deserializerCache) {
        if (null == deserializerFactory) {
            throw new NullPointerException("Cannot pass null DeserializerFactory");
        }
        _factory = deserializerFactory;
        _cache = null == deserializerCache ? new DeserializerCache() : deserializerCache;
        _featureFlags = 0;
        _readCapabilities = null;
        _config = null;
        _injectableValues = null;
        _view = null;
        _attributes = null;
    }
    protected DeserializationContext(final DeserializationContext deserializationContext, final DeserializerFactory deserializerFactory) {
        _cache = deserializationContext._cache;
        _factory = deserializerFactory;
        _config = deserializationContext._config;
        _featureFlags = deserializationContext._featureFlags;
        _readCapabilities = deserializationContext._readCapabilities;
        _view = deserializationContext._view;
        _parser = deserializationContext._parser;
        _injectableValues = deserializationContext._injectableValues;
        _attributes = deserializationContext._attributes;
    }
    protected DeserializationContext(final DeserializationContext deserializationContext, final DeserializationConfig deserializationConfig, final JsonParser jsonParser, final InjectableValues injectableValues) {
        _cache = deserializationContext._cache;
        _factory = deserializationContext._factory;
        _readCapabilities = null == jsonParser ? null : jsonParser.getReadCapabilities();
        _config = deserializationConfig;
        _featureFlags = deserializationConfig.getDeserializationFeatures();
        _view = deserializationConfig.getActiveView();
        _parser = jsonParser;
        _injectableValues = injectableValues;
        _attributes = deserializationConfig.getAttributes();
    }
    protected DeserializationContext(final DeserializationContext deserializationContext, final DeserializationConfig deserializationConfig) {
        _cache = deserializationContext._cache;
        _factory = deserializationContext._factory;
        _readCapabilities = null;
        _config = deserializationConfig;
        _featureFlags = deserializationConfig.getDeserializationFeatures();
        _view = null;
        _parser = null;
        _injectableValues = null;
        _attributes = null;
    }
    protected DeserializationContext(final DeserializationContext deserializationContext) {
        _cache = new DeserializerCache();
        _factory = deserializationContext._factory;
        _config = deserializationContext._config;
        _featureFlags = deserializationContext._featureFlags;
        _readCapabilities = deserializationContext._readCapabilities;
        _view = deserializationContext._view;
        _injectableValues = null;
    }
    public DeserializationConfig getConfig() {
        return _config;
    }
    public final Class<?> getActiveView() {
        return _view;
    }
    public final boolean canOverrideAccessModifiers() {
        return _config.canOverrideAccessModifiers();
    }
    public final boolean isEnabled(final MapperFeature mapperFeature) {
        return _config.isEnabled(mapperFeature);
    }
    public final JsonFormat.Value getDefaultPropertyFormat(final Class<?> cls) {
        return _config.getDefaultPropertyFormat(cls);
    }
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return _config.getAnnotationIntrospector();
    }
    public final TypeFactory getTypeFactory() {
        return _config.getTypeFactory();
    }
    public JavaType constructSpecializedType(final JavaType javaType, final Class<?> cls) throws IllegalArgumentException {
        return javaType.hasRawClass(cls) ? javaType : this._config.getTypeFactory().constructSpecializedType(javaType, cls, false);
    }
    public Locale getLocale() {
        return _config.getLocale();
    }
    public TimeZone getTimeZone() {
        return _config.getTimeZone();
    }
    public Object getAttribute(final Object obj) {
        return _attributes.getAttribute(obj);
    }
    public DeserializationContext setAttribute(final Object obj, final Object obj2) {
        _attributes = _attributes.withPerCallAttribute(obj, obj2);
        return this;
    }
    public JavaType getContextualType() {
        final LinkedNode<JavaType> linkedNode = _currentType;
        if (null == linkedNode) {
            return null;
        }
        return linkedNode.value();
    }
    public DeserializerFactory getFactory() {
        return _factory;
    }
    public final boolean isEnabled(final DeserializationFeature deserializationFeature) {
        return 0 != (deserializationFeature.getMask() & this._featureFlags);
    }
    public final boolean isEnabled(final StreamReadCapability streamReadCapability) {
        return _readCapabilities.isEnabled(streamReadCapability);
    }
    public final int getDeserializationFeatures() {
        return _featureFlags;
    }
    public final boolean hasDeserializationFeatures(final int i2) {
        return (_featureFlags & i2) == i2;
    }
    public final boolean hasSomeOfFeatures(final int i2) {
        return 0 != (i2 & this._featureFlags);
    }
    public final JsonParser getParser() {
        return _parser;
    }
    public final Object findInjectableValue(final Object obj, final BeanProperty beanProperty, final Object obj2) throws JsonMappingException {
        if (null == this._injectableValues) {
            this.reportBadDefinition(ClassUtil.classOf(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", obj));
        }
        return _injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }
    public final Base64Variant getBase64Variant() {
        return _config.getBase64Variant();
    }
    public final JsonNodeFactory getNodeFactory() {
        return _config.getNodeFactory();
    }
    public CoercionAction findCoercionAction(final LogicalType logicalType, final Class<?> cls, final CoercionInputShape coercionInputShape) {
        return _config.findCoercionAction(logicalType, cls, coercionInputShape);
    }
    public CoercionAction findCoercionFromBlankString(final LogicalType logicalType, final Class<?> cls, final CoercionAction coercionAction) {
        return _config.findCoercionFromBlankString(logicalType, cls, coercionAction);
    }
    public boolean hasValueDeserializerFor(final JavaType javaType, final AtomicReference<Throwable> atomicReference) {
        try {
            return _cache.hasValueDeserializerFor(this, _factory, javaType);
        } catch (final JsonMappingException e2) {
            if (null == atomicReference) {
                return false;
            }
            atomicReference.set(e2);
            return false;
        } catch (final RuntimeException e3) {
            if (null == atomicReference) {
                throw e3;
            }
            atomicReference.set(e3);
            return false;
        }
    }
    public final JsonDeserializer<Object> findContextualValueDeserializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = _cache.findValueDeserializer(this, _factory, javaType);
        return null != jsonDeserializerFindValueDeserializer ? (JsonDeserializer<Object>) this.handleSecondaryContextualization((JsonDeserializer<?>) jsonDeserializerFindValueDeserializer, beanProperty, javaType) : jsonDeserializerFindValueDeserializer;
    }
    public final JsonDeserializer<Object> findNonContextualValueDeserializer(final JavaType javaType) throws JsonMappingException {
        return _cache.findValueDeserializer(this, _factory, javaType);
    }
    public final JsonDeserializer<Object> findRootValueDeserializer(final JavaType javaType) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = _cache.findValueDeserializer(this, _factory, javaType);
        if (null == jsonDeserializerFindValueDeserializer) {
            return null;
        }
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = this.handleSecondaryContextualization(jsonDeserializerFindValueDeserializer, null, javaType);
        final TypeDeserializer typeDeserializerFindTypeDeserializer = _factory.findTypeDeserializer(_config, javaType);
        return null != typeDeserializerFindTypeDeserializer ? new TypeWrappedDeserializer(typeDeserializerFindTypeDeserializer.forProperty(null), jsonDeserializerHandleSecondaryContextualization) : (JsonDeserializer<Object>) jsonDeserializerHandleSecondaryContextualization;
    }
    public final KeyDeserializer findKeyDeserializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        final KeyDeserializer keyDeserializerFindKeyDeserializer = _cache.findKeyDeserializer(this, _factory, javaType);
        return keyDeserializerFindKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) keyDeserializerFindKeyDeserializer).createContextual(this, beanProperty) : keyDeserializerFindKeyDeserializer;
    }
    public final JavaType constructType(final Class<?> cls) {
        if (null == cls) {
            return null;
        }
        return _config.constructType(cls);
    }
    public Class<?> findClass(final String str) throws ClassNotFoundException {
        return this.getTypeFactory().findClass(str);
    }
    public final ObjectBuffer leaseObjectBuffer() {
        final ObjectBuffer objectBuffer = _objectBuffer;
        if (null == objectBuffer) {
            return new ObjectBuffer();
        }
        _objectBuffer = null;
        return objectBuffer;
    }
    public final void returnObjectBuffer(final ObjectBuffer objectBuffer) {
        if (null == this._objectBuffer || objectBuffer.initialCapacity() >= _objectBuffer.initialCapacity()) {
            _objectBuffer = objectBuffer;
        }
    }
    public final ArrayBuilders getArrayBuilders() {
        if (null == this._arrayBuilders) {
            _arrayBuilders = new ArrayBuilders();
        }
        return _arrayBuilders;
    }
    public JsonDeserializer<?> handlePrimaryContextualization(final JsonDeserializer<?> jsonDeserializer, final BeanProperty beanProperty, final JavaType javaType) throws JsonMappingException {
        final boolean z = jsonDeserializer instanceof ContextualDeserializer;
        final JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            _currentType = new LinkedNode<>(javaType, _currentType);
            try {
                final JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                _currentType = _currentType.next();
            }
        }
        return jsonDeserializer2;
    }
    public JsonDeserializer<?> handleSecondaryContextualization(final JsonDeserializer<?> jsonDeserializer, final BeanProperty beanProperty, final JavaType javaType) throws JsonMappingException {
        final boolean z = jsonDeserializer instanceof ContextualDeserializer;
        final JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            _currentType = new LinkedNode<>(javaType, _currentType);
            try {
                final JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                _currentType = _currentType.next();
            }
        }
        return jsonDeserializer2;
    }
    public Date parseDate(final String str) throws IllegalArgumentException {
        try {
            return this._getDateFormat().parse(str);
        } catch (final ParseException e2) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, ClassUtil.exceptionMessage(e2)));
        }
    }
    public Calendar constructCalendar(final Date date) {
        final Calendar calendar = Calendar.getInstance(this.getTimeZone());
        calendar.setTime(date);
        return calendar;
    }
    public String extractScalarFromObject(final JsonParser jsonParser, final JsonDeserializer<?> jsonDeserializer, final Class<?> cls) throws IOException {
        return (String) this.handleUnexpectedToken(cls, jsonParser);
    }
    public <T> T readValue(final JsonParser jsonParser, final Class<T> cls) throws IOException {
        return this.readValue(jsonParser, this.getTypeFactory().constructType(cls));
    }
    public <T> T readValue(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        final JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = this.findRootValueDeserializer(javaType);
        if (null == jsonDeserializerFindRootValueDeserializer) {
            this.reportBadDefinition(javaType, "Could not find JsonDeserializer for type " + ClassUtil.getTypeDescription(javaType));
        }
        return (T) jsonDeserializerFindRootValueDeserializer.deserialize(jsonParser, this);
    }
    public <T> T readPropertyValue(final JsonParser jsonParser, final BeanProperty beanProperty, final Class<T> cls) throws IOException {
        return this.readPropertyValue(jsonParser, beanProperty, this.getTypeFactory().constructType(cls));
    }
    public <T> T readPropertyValue(final JsonParser jsonParser, final BeanProperty beanProperty, final JavaType javaType) throws IOException {
        final JsonDeserializer<Object> jsonDeserializerFindContextualValueDeserializer = this.findContextualValueDeserializer(javaType, beanProperty);
        if (null == jsonDeserializerFindContextualValueDeserializer) {
            return this.reportBadDefinition(javaType, String.format("Could not find JsonDeserializer for type %s (via property %s)", ClassUtil.getTypeDescription(javaType), ClassUtil.nameOf(beanProperty)));
        }
        return (T) jsonDeserializerFindContextualValueDeserializer.deserialize(jsonParser, this);
    }
    public JsonNode readTree(final JsonParser jsonParser) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
            return this.getNodeFactory().missingNode();
        }
        if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
            return this.getNodeFactory().m827nullNode();
        }
        return (JsonNode) this.findRootValueDeserializer(_config.constructType(JsonNode.class)).deserialize(jsonParser, this);
    }
    public boolean handleUnknownProperty(final JsonParser jsonParser, final JsonDeserializer<?> jsonDeserializer, final Object obj, final String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
        }
        if (!this.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            jsonParser.skipChildren();
            return true;
        }
        throw UnrecognizedPropertyException.from(_parser, obj, str, null == jsonDeserializer ? null : jsonDeserializer.getKnownPropertyNames());
    }
    public Object handleWeirdKey(final Class<?> cls, final String str, final String str2, final Object... objArr) throws IOException {
        final String str_format = this._format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleWeirdKey = problemHandlers.value().handleWeirdKey(this, cls, str, str_format);
            if (objHandleWeirdKey != DeserializationProblemHandler.NOT_HANDLED) {
                if (null == objHandleWeirdKey || cls.isInstance(objHandleWeirdKey)) {
                    return objHandleWeirdKey;
                }
                throw this.weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdKey)));
            }
        }
        throw this.weirdKeyException(cls, str, str_format);
    }
    public Object handleWeirdStringValue(final Class<?> cls, final String str, final String str2, final Object... objArr) throws IOException {
        final String str_format = this._format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleWeirdStringValue = problemHandlers.value().handleWeirdStringValue(this, cls, str, str_format);
            if (objHandleWeirdStringValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (this._isCompatible(cls, objHandleWeirdStringValue)) {
                    return objHandleWeirdStringValue;
                }
                throw this.weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdStringValue)));
            }
        }
        throw this.weirdStringException(str, cls, str_format);
    }
    public Object handleWeirdNumberValue(final Class<?> cls, final Number number, final String str, final Object... objArr) throws IOException {
        final String str_format = this._format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleWeirdNumberValue = problemHandlers.value().handleWeirdNumberValue(this, cls, number, str_format);
            if (objHandleWeirdNumberValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (this._isCompatible(cls, objHandleWeirdNumberValue)) {
                    return objHandleWeirdNumberValue;
                }
                throw this.weirdNumberException(number, cls, this._format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleWeirdNumberValue)));
            }
        }
        throw this.weirdNumberException(number, cls, str_format);
    }
    public Object handleWeirdNativeValue(final JavaType javaType, final Object obj, final JsonParser jsonParser) throws IOException {
        final Class<?> rawClass = javaType.getRawClass();
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleWeirdNativeValue = problemHandlers.value().handleWeirdNativeValue(this, javaType, obj, jsonParser);
            if (objHandleWeirdNativeValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (null == objHandleWeirdNativeValue || rawClass.isInstance(objHandleWeirdNativeValue)) {
                    return objHandleWeirdNativeValue;
                }
                throw JsonMappingException.from(jsonParser, this._format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", ClassUtil.getClassDescription(javaType), ClassUtil.getClassDescription(objHandleWeirdNativeValue)));
            }
        }
        throw this.weirdNativeValueException(obj, rawClass);
    }
    public Object handleMissingInstantiator(final Class<?> cls, final ValueInstantiator valueInstantiator, JsonParser jsonParser, final String str, final Object... objArr) throws IOException {
        if (null == jsonParser) {
            jsonParser = this._parser;
        }
        final String str_format = this._format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleMissingInstantiator = problemHandlers.value().handleMissingInstantiator(this, cls, valueInstantiator, jsonParser, str_format);
            if (objHandleMissingInstantiator != DeserializationProblemHandler.NOT_HANDLED) {
                if (this._isCompatible(cls, objHandleMissingInstantiator)) {
                    return objHandleMissingInstantiator;
                }
                this.reportBadDefinition(this.constructType(cls), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(objHandleMissingInstantiator)));
            }
        }
        if (null == valueInstantiator) {
            return this.reportBadDefinition(cls, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls), str_format));
        }
        if (!valueInstantiator.canInstantiate()) {
            return this.reportBadDefinition(cls, String.format("Cannot construct instance of %s (no Creators, like default constructor, exist): %s", ClassUtil.nameOf(cls), str_format));
        }
        return this.reportInputMismatch(cls, String.format("Cannot construct instance of %s (although at least one Creator exists): %s", ClassUtil.nameOf(cls), str_format));
    }
    public Object handleInstantiationProblem(final Class<?> cls, final Object obj, final Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleInstantiationProblem = problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            if (objHandleInstantiationProblem != DeserializationProblemHandler.NOT_HANDLED) {
                if (this._isCompatible(cls, objHandleInstantiationProblem)) {
                    return objHandleInstantiationProblem;
                }
                this.reportBadDefinition(this.constructType(cls), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.classNameOf(objHandleInstantiationProblem)));
            }
        }
        ClassUtil.throwIfIOE(th);
        if (!this.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        throw this.instantiationException(cls, th);
    }
    public Object handleUnexpectedToken(final Class<?> cls, final JsonParser jsonParser) throws IOException {
        return this.handleUnexpectedToken(this.constructType(cls), jsonParser.currentToken(), jsonParser, null);
    }
    public Object handleUnexpectedToken(final Class<?> cls, final JsonToken jsonToken, final JsonParser jsonParser, final String str, final Object... objArr) throws IOException {
        return this.handleUnexpectedToken(this.constructType(cls), jsonToken, jsonParser, str, objArr);
    }
    public Object handleUnexpectedToken(final JavaType javaType, final JsonParser jsonParser) throws IOException {
        return this.handleUnexpectedToken(javaType, jsonParser.currentToken(), jsonParser, null);
    }
    public Object handleUnexpectedToken(final JavaType javaType, final JsonToken jsonToken, final JsonParser jsonParser, final String str, final Object... objArr) throws IOException {
        String str_format = this._format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final Object objHandleUnexpectedToken = problemHandlers.value().handleUnexpectedToken(this, javaType, jsonToken, jsonParser, str_format);
            if (objHandleUnexpectedToken != DeserializationProblemHandler.NOT_HANDLED) {
                if (this._isCompatible(javaType.getRawClass(), objHandleUnexpectedToken)) {
                    return objHandleUnexpectedToken;
                }
                this.reportBadDefinition(javaType, String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", ClassUtil.getTypeDescription(javaType), ClassUtil.classNameOf(objHandleUnexpectedToken)));
            }
        }
        if (null == str_format) {
            final String typeDescription = ClassUtil.getTypeDescription(javaType);
            if (null == jsonToken) {
                str_format = String.format("Unexpected end-of-input when trying read value of type %s", typeDescription);
            } else {
                str_format = String.format("Cannot deserialize value of type %s from %s (token `JsonToken.%s`)", typeDescription, this._shapeForToken(jsonToken), jsonToken);
            }
        }
        if (null != jsonToken && jsonToken.isScalarValue()) {
            jsonParser.getText();
        }
        this.reportInputMismatch(javaType, str_format);
        return null;
    }
    public JavaType handleUnknownTypeId(final JavaType javaType, final String str, final TypeIdResolver typeIdResolver, final String str2) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final JavaType javaTypeHandleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(this, javaType, str, typeIdResolver, str2);
            if (null != javaTypeHandleUnknownTypeId) {
                if (javaTypeHandleUnknownTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleUnknownTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleUnknownTypeId;
                }
                throw this.invalidTypeIdException(javaType, str, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(javaTypeHandleUnknownTypeId));
            }
        }
        if (this.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            throw this.invalidTypeIdException(javaType, str, str2);
        }
        return null;
    }
    public JavaType handleMissingTypeId(final JavaType javaType, final TypeIdResolver typeIdResolver, final String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = _config.getProblemHandlers(); null != problemHandlers; problemHandlers = problemHandlers.next()) {
            final JavaType javaTypeHandleMissingTypeId = problemHandlers.value().handleMissingTypeId(this, javaType, typeIdResolver, str);
            if (null != javaTypeHandleMissingTypeId) {
                if (javaTypeHandleMissingTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleMissingTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleMissingTypeId;
                }
                throw this.invalidTypeIdException(javaType, null, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(javaTypeHandleMissingTypeId));
            }
        }
        throw this.missingTypeIdException(javaType, str);
    }
    public void handleBadMerge(final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (this.isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            return;
        }
        final JavaType javaTypeConstructType = this.constructType(jsonDeserializer.handledType());
        throw InvalidDefinitionException.from(this._parser, String.format("Invalid configuration: values of type %s cannot be merged", ClassUtil.getTypeDescription(javaTypeConstructType)), javaTypeConstructType);
    }
    protected boolean _isCompatible(final Class<?> cls, final Object obj) {
        if (null == obj || cls.isInstance(obj)) {
            return true;
        }
        return cls.isPrimitive() && ClassUtil.wrapperType(cls).isInstance(obj);
    }
    public void reportWrongTokenException(final JsonDeserializer<?> jsonDeserializer, final JsonToken jsonToken, final String str, final Object... objArr) throws JsonMappingException {
        throw this.wrongTokenException(this._parser, jsonDeserializer.handledType(), jsonToken, this._format(str, objArr));
    }
    public void reportWrongTokenException(final JavaType javaType, final JsonToken jsonToken, final String str, final Object... objArr) throws JsonMappingException {
        throw this.wrongTokenException(this._parser, javaType, jsonToken, this._format(str, objArr));
    }
    public void reportWrongTokenException(final Class<?> cls, final JsonToken jsonToken, final String str, final Object... objArr) throws JsonMappingException {
        throw this.wrongTokenException(this._parser, cls, jsonToken, this._format(str, objArr));
    }
    public <T> T reportUnresolvedObjectId(final ObjectIdReader objectIdReader, final Object obj) throws JsonMappingException {
        return this.reportInputMismatch(objectIdReader.idProperty, String.format("No Object Id found for an instance of %s, to assign to property '%s'", ClassUtil.classNameOf(obj), objectIdReader.propertyName), new Object[0]);
    }
    public <T> T reportInputMismatch(final JsonDeserializer<?> jsonDeserializer, final String str, final Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(this._parser, jsonDeserializer.handledType(), this._format(str, objArr));
    }
    public <T> T reportInputMismatch(final Class<?> cls, final String str, final Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(this._parser, cls, this._format(str, objArr));
    }
    public <T> T reportInputMismatch(final JavaType javaType, final String str, final Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(this._parser, javaType, this._format(str, objArr));
    }
    public <T> T reportInputMismatch(final BeanProperty beanProperty, final String str, final Object... objArr) throws JsonMappingException {
        final AnnotatedMember member;
        final MismatchedInputException mismatchedInputExceptionFrom = MismatchedInputException.from(this._parser, null == beanProperty ? null : beanProperty.getType(), this._format(str, objArr));
        if (null != beanProperty && null != (member = beanProperty.getMember())) {
            mismatchedInputExceptionFrom.prependPath(member.getDeclaringClass(), beanProperty.getName());
            throw mismatchedInputExceptionFrom;
        }
        throw mismatchedInputExceptionFrom;
    }
    public <T> T reportPropertyInputMismatch(final Class<?> cls, final String str, final String str2, final Object... objArr) throws JsonMappingException {
        final MismatchedInputException mismatchedInputExceptionFrom = MismatchedInputException.from(this._parser, cls, this._format(str2, objArr));
        if (null != str) {
            mismatchedInputExceptionFrom.prependPath(cls, str);
            throw mismatchedInputExceptionFrom;
        }
        throw mismatchedInputExceptionFrom;
    }
    public <T> T reportPropertyInputMismatch(final JavaType javaType, final String str, final String str2, final Object... objArr) throws JsonMappingException {
        return this.reportPropertyInputMismatch(javaType.getRawClass(), str, str2, objArr);
    }
    public <T> T reportBadCoercion(final JsonDeserializer<?> jsonDeserializer, final Class<?> cls, final Object obj, final String str, final Object... objArr) throws JsonMappingException {
        throw InvalidFormatException.from(this._parser, this._format(str, objArr), obj, cls);
    }
    public <T> T reportTrailingTokens(final Class<?> cls, final JsonParser jsonParser, final JsonToken jsonToken) throws JsonMappingException {
        throw MismatchedInputException.from(jsonParser, cls, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", jsonToken, ClassUtil.nameOf(cls)));
    }
    public void reportWrongTokenException(final JsonParser jsonParser, final JsonToken jsonToken, final String str, final Object... objArr) throws JsonMappingException {
        throw this.wrongTokenException(jsonParser, jsonToken, this._format(str, objArr));
    }
    public void reportUnknownProperty(final Object obj, final String str, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (this.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(_parser, obj, str, null == jsonDeserializer ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }
    public void reportMissingContent(final String str, final Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(this._parser, (JavaType) null, "No content to map due to end-of-input");
    }
    public <T> T reportBadTypeDefinition(final BeanDescription beanDescription, final String str, final Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(_parser, String.format("Invalid type definition for type %s: %s", ClassUtil.nameOf(beanDescription.getBeanClass()), this._format(str, objArr)), beanDescription, null);
    }
    public <T> T reportBadPropertyDefinition(final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition, final String str, final Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(_parser, String.format("Invalid definition for property %s (of type %s): %s", ClassUtil.nameOf(beanPropertyDefinition), ClassUtil.nameOf(beanDescription.getBeanClass()), this._format(str, objArr)), beanDescription, beanPropertyDefinition);
    }
    public <T> T reportBadDefinition(final JavaType javaType, final String str) throws JsonMappingException {
        throw InvalidDefinitionException.from(_parser, str, javaType);
    }
    public <T> T reportBadMerge(final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        this.handleBadMerge(jsonDeserializer);
        return null;
    }
    public JsonMappingException wrongTokenException(final JsonParser jsonParser, final JavaType javaType, final JsonToken jsonToken, final String str) {
        return MismatchedInputException.from(jsonParser, javaType, this._colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.currentToken(), jsonToken), str));
    }
    public JsonMappingException wrongTokenException(final JsonParser jsonParser, final Class<?> cls, final JsonToken jsonToken, final String str) {
        return MismatchedInputException.from(jsonParser, cls, this._colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.currentToken(), jsonToken), str));
    }
    public JsonMappingException wrongTokenException(final JsonParser jsonParser, final JsonToken jsonToken, final String str) {
        return this.wrongTokenException(jsonParser, (JavaType) null, jsonToken, str);
    }
    public JsonMappingException weirdKeyException(final Class<?> cls, final String str, final String str2) {
        return InvalidFormatException.from(_parser, String.format("Cannot deserialize Map key of type %s from String %s: %s", ClassUtil.nameOf(cls), this._quotedString(str), str2), str, cls);
    }
    public JsonMappingException weirdStringException(final String str, final Class<?> cls, final String str2) {
        return InvalidFormatException.from(_parser, String.format("Cannot deserialize value of type %s from String %s: %s", ClassUtil.nameOf(cls), this._quotedString(str), str2), str, cls);
    }
    public JsonMappingException weirdNumberException(final Number number, final Class<?> cls, final String str) {
        return InvalidFormatException.from(_parser, String.format("Cannot deserialize value of type %s from number %s: %s", ClassUtil.nameOf(cls), number, str), number, cls);
    }
    public JsonMappingException weirdNativeValueException(final Object obj, final Class<?> cls) {
        return InvalidFormatException.from(_parser, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", ClassUtil.nameOf(cls), ClassUtil.classNameOf(obj)), obj, cls);
    }
    public JsonMappingException instantiationException(final Class<?> cls, final Throwable th) {
        String strExceptionMessage;
        if (null == th) {
            strExceptionMessage = "N/A";
        } else {
            strExceptionMessage = ClassUtil.exceptionMessage(th);
            if (null == strExceptionMessage) {
                strExceptionMessage = ClassUtil.nameOf(th.getClass());
            }
        }
        return ValueInstantiationException.from(_parser, String.format("Cannot construct instance of %s, problem: %s", ClassUtil.nameOf(cls), strExceptionMessage), this.constructType(cls), th);
    }
    public JsonMappingException instantiationException(final Class<?> cls, final String str) {
        return ValueInstantiationException.from(_parser, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls), str), this.constructType(cls));
    }
    public JsonMappingException invalidTypeIdException(final JavaType javaType, final String str, final String str2) {
        return InvalidTypeIdException.from(_parser, this._colonConcat(String.format("Could not resolve type id '%s' as a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2), javaType, str);
    }
    public JsonMappingException missingTypeIdException(final JavaType javaType, final String str) {
        return InvalidTypeIdException.from(_parser, this._colonConcat(String.format("Could not resolve subtype of %s", javaType), str), javaType, null);
    }
    public JsonMappingException unknownTypeException(final JavaType javaType, final String str, final String str2) {
        return MismatchedInputException.from(_parser, javaType, this._colonConcat(String.format("Could not resolve type id '%s' into a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2));
    }
    public JsonMappingException endOfInputException(final Class<?> cls) {
        return MismatchedInputException.from(_parser, cls, "Unexpected end-of-input when trying to deserialize a " + cls.getName());
    }
    public void reportMappingException(final String str, final Object... objArr) throws JsonMappingException {
        throw JsonMappingException.from(this._parser, this._format(str, objArr));
    }
    public JsonMappingException mappingException(final String str) {
        return JsonMappingException.from(this._parser, str);
    }
    public JsonMappingException mappingException(final String str, final Object... objArr) {
        return JsonMappingException.from(this._parser, this._format(str, objArr));
    }
    public JsonMappingException mappingException(final Class<?> cls) {
        return this.mappingException(cls, _parser.currentToken());
    }
    public JsonMappingException mappingException(final Class<?> cls, final JsonToken jsonToken) {
        return JsonMappingException.from(_parser, String.format("Cannot deserialize instance of %s out of %s token", ClassUtil.nameOf(cls), jsonToken));
    }
    protected DateFormat getDateFormat() {
        return this._getDateFormat();
    }
    protected DateFormat _getDateFormat() {
        final DateFormat dateFormat = _dateFormat;
        if (null != dateFormat) {
            return dateFormat;
        }
        final DateFormat dateFormat2 = (DateFormat) _config.getDateFormat().clone();
        _dateFormat = dateFormat2;
        return dateFormat2;
    }
    enum C11871 {
        ;
        static final int[] SwitchMapcomfasterxmljacksoncoreJsonToken;

        static {
            final int[] iArr = new int[JsonToken.values().length];
            SwitchMapcomfasterxmljacksoncoreJsonToken = iArr;
            try {
                iArr[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.FIELD_NAME.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.START_ARRAY.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.END_ARRAY.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_TRUE.ordinal()] = 7;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 8;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 9;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 10;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_STRING.ordinal()] = 11;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NULL.ordinal()] = 12;
            } catch (final NoSuchFieldError unused12) {
            }
            try {
                C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.NOT_AVAILABLE.ordinal()] = 13;
            } catch (final NoSuchFieldError unused13) {
            }
        }
    }
    protected String _shapeForToken(final JsonToken jsonToken) {
        if (null != jsonToken) {
            switch (C11871.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonToken.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    return "Object value";
                case 4:
                case 5:
                    return "Array value";
                case 6:
                case 7:
                    return "Boolean value";
                case 8:
                    return "Embedded Object";
                case 9:
                    return "Floating-point value";
                case 10:
                    return "Integer value";
                case 11:
                    return "String value";
                case 12:
                    return "Null value";
                default:
                    return "[Unavailable value]";
            }
        }
        return "<end of input>";
    }
}
