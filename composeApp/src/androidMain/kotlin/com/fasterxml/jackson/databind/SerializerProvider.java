package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider extends DatabindContext {
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
    protected ContextAttributes _attributes;
    protected final SerializationConfig _config;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final Class<?> _serializationView;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected final boolean _stdNullValueSerializer;
    protected JsonSerializer<Object> _unknownTypeSerializer;
    public abstract WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);
    public JsonGenerator getGenerator() {
        return null;
    }
    public abstract Object includeFilterInstance(BeanPropertyDefinition beanPropertyDefinition, Class<?> cls) throws JsonMappingException;
    public abstract boolean includeFilterSuppressNulls(Object obj) throws JsonMappingException;
    public abstract JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj) throws JsonMappingException;
    protected SerializerProvider() {
        _unknownTypeSerializer = SerializerProvider.DEFAULT_UNKNOWN_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = SerializerProvider.DEFAULT_NULL_KEY_SERIALIZER;
        _config = null;
        _serializerFactory = null;
        _serializerCache = new SerializerCache();
        _knownSerializers = null;
        _serializationView = null;
        _attributes = null;
        _stdNullValueSerializer = true;
    }
    protected SerializerProvider(final SerializerProvider serializerProvider, final SerializationConfig serializationConfig, final SerializerFactory serializerFactory) {
        _unknownTypeSerializer = SerializerProvider.DEFAULT_UNKNOWN_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        final JsonSerializer<Object> jsonSerializer = SerializerProvider.DEFAULT_NULL_KEY_SERIALIZER;
        _nullKeySerializer = jsonSerializer;
        _serializerFactory = serializerFactory;
        _config = serializationConfig;
        final SerializerCache serializerCache = serializerProvider._serializerCache;
        _serializerCache = serializerCache;
        _unknownTypeSerializer = serializerProvider._unknownTypeSerializer;
        _keySerializer = serializerProvider._keySerializer;
        final JsonSerializer<Object> jsonSerializer2 = serializerProvider._nullValueSerializer;
        _nullValueSerializer = jsonSerializer2;
        _nullKeySerializer = serializerProvider._nullKeySerializer;
        _stdNullValueSerializer = jsonSerializer2 == jsonSerializer;
        _serializationView = serializationConfig.getActiveView();
        _attributes = serializationConfig.getAttributes();
        _knownSerializers = serializerCache.getReadOnlyLookupMap();
    }
    protected SerializerProvider(final SerializerProvider serializerProvider) {
        _unknownTypeSerializer = SerializerProvider.DEFAULT_UNKNOWN_SERIALIZER;
        _nullValueSerializer = NullSerializer.instance;
        _nullKeySerializer = SerializerProvider.DEFAULT_NULL_KEY_SERIALIZER;
        _config = null;
        _serializationView = null;
        _serializerFactory = null;
        _knownSerializers = null;
        _serializerCache = new SerializerCache();
        _unknownTypeSerializer = serializerProvider._unknownTypeSerializer;
        _keySerializer = serializerProvider._keySerializer;
        _nullValueSerializer = serializerProvider._nullValueSerializer;
        _nullKeySerializer = serializerProvider._nullKeySerializer;
        _stdNullValueSerializer = serializerProvider._stdNullValueSerializer;
    }
    public void setDefaultKeySerializer(final JsonSerializer<Object> jsonSerializer) {
        if (null == jsonSerializer) {
            throw new IllegalArgumentException("Cannot pass null JsonSerializer");
        }
        _keySerializer = jsonSerializer;
    }
    public void setNullValueSerializer(final JsonSerializer<Object> jsonSerializer) {
        if (null == jsonSerializer) {
            throw new IllegalArgumentException("Cannot pass null JsonSerializer");
        }
        _nullValueSerializer = jsonSerializer;
    }
    public void setNullKeySerializer(final JsonSerializer<Object> jsonSerializer) {
        if (null == jsonSerializer) {
            throw new IllegalArgumentException("Cannot pass null JsonSerializer");
        }
        _nullKeySerializer = jsonSerializer;
    }
    public final SerializationConfig getConfig() {
        return _config;
    }
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return _config.getAnnotationIntrospector();
    }
    public final TypeFactory getTypeFactory() {
        return _config.getTypeFactory();
    }
    public JavaType constructSpecializedType(final JavaType javaType, final Class<?> cls) throws IllegalArgumentException {
        return javaType.hasRawClass(cls) ? javaType : this._config.getTypeFactory().constructSpecializedType(javaType, cls, true);
    }
    public final Class<?> getActiveView() {
        return _serializationView;
    }
    public final Class<?> getSerializationView() {
        return _serializationView;
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
    public final JsonInclude.Value getDefaultPropertyInclusion(final Class<?> cls) {
        return _config.getDefaultPropertyInclusion(cls);
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
    public SerializerProvider setAttribute(final Object obj, final Object obj2) {
        _attributes = _attributes.withPerCallAttribute(obj, obj2);
        return this;
    }
    public final boolean isEnabled(final SerializationFeature serializationFeature) {
        return _config.isEnabled(serializationFeature);
    }
    public final boolean hasSerializationFeatures(final int i2) {
        return _config.hasSerializationFeatures(i2);
    }
    public final FilterProvider getFilterProvider() {
        return _config.getFilterProvider();
    }
    public JsonSerializer<Object> findValueSerializer(final Class<?> cls, final BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(cls);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(cls))) {
            return this.getUnknownTypeSerializer(cls);
        }
        return (JsonSerializer<Object>) this.handleSecondaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
    }
    public JsonSerializer<Object> findValueSerializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        if (null == javaType) {
            this.reportMappingProblem("Null passed for `valueType` of `findValueSerializer()`");
        }
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(javaType);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(javaType))) {
            return this.getUnknownTypeSerializer(javaType.getRawClass());
        }
        return (JsonSerializer<Object>) this.handleSecondaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
    }
    public JsonSerializer<Object> findValueSerializer(final Class<?> cls) throws JsonMappingException {
        final JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(cls);
        if (null != jsonSerializerUntypedValueSerializer) {
            return jsonSerializerUntypedValueSerializer;
        }
        final JsonSerializer<Object> jsonSerializerUntypedValueSerializer2 = _serializerCache.untypedValueSerializer(cls);
        if (null != jsonSerializerUntypedValueSerializer2) {
            return jsonSerializerUntypedValueSerializer2;
        }
        final JsonSerializer<Object> jsonSerializerUntypedValueSerializer3 = _serializerCache.untypedValueSerializer(_config.constructType(cls));
        if (null != jsonSerializerUntypedValueSerializer3) {
            return jsonSerializerUntypedValueSerializer3;
        }
        final JsonSerializer<Object> jsonSerializer_createAndCacheUntypedSerializer = this._createAndCacheUntypedSerializer(cls);
        return null == jsonSerializer_createAndCacheUntypedSerializer ? this.getUnknownTypeSerializer(cls) : jsonSerializer_createAndCacheUntypedSerializer;
    }
    public JsonSerializer<Object> findValueSerializer(final JavaType javaType) throws JsonMappingException {
        final JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(javaType);
        if (null != jsonSerializerUntypedValueSerializer) {
            return jsonSerializerUntypedValueSerializer;
        }
        final JsonSerializer<Object> jsonSerializerUntypedValueSerializer2 = _serializerCache.untypedValueSerializer(javaType);
        if (null != jsonSerializerUntypedValueSerializer2) {
            return jsonSerializerUntypedValueSerializer2;
        }
        final JsonSerializer<Object> jsonSerializer_createAndCacheUntypedSerializer = this._createAndCacheUntypedSerializer(javaType);
        return null == jsonSerializer_createAndCacheUntypedSerializer ? this.getUnknownTypeSerializer(javaType.getRawClass()) : jsonSerializer_createAndCacheUntypedSerializer;
    }
    public JsonSerializer<Object> findPrimaryPropertySerializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(javaType);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(javaType))) {
            return this.getUnknownTypeSerializer(javaType.getRawClass());
        }
        return (JsonSerializer<Object>) this.handlePrimaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
    }
    public JsonSerializer<Object> findPrimaryPropertySerializer(final Class<?> cls, final BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(cls);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(cls))) {
            return this.getUnknownTypeSerializer(cls);
        }
        return (JsonSerializer<Object>) this.handlePrimaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
    }
    public JsonSerializer<Object> findContentValueSerializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(javaType);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(javaType))) {
            return this.getUnknownTypeSerializer(javaType.getRawClass());
        }
        return (JsonSerializer<Object>) this.handleSecondaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
    }
    public JsonSerializer<Object> findContentValueSerializer(final Class<?> cls, final BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(cls);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) && null == (jsonSerializerUntypedValueSerializer = _createAndCacheUntypedSerializer(cls))) {
            return this.getUnknownTypeSerializer(cls);
        }
        return (JsonSerializer<Object>) this.handleSecondaryContextualization(jsonSerializerUntypedValueSerializer, beanProperty);
   }
    public JsonSerializer<Object> findTypedValueSerializer(final Class<?> cls, final boolean z, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonSerializer<Object> jsonSerializerTypedValueSerializer = _knownSerializers.typedValueSerializer(cls);
        if (null != jsonSerializerTypedValueSerializer) {
            return jsonSerializerTypedValueSerializer;
        }
        final JsonSerializer<Object> jsonSerializerTypedValueSerializer2 = _serializerCache.typedValueSerializer(cls);
        if (null != jsonSerializerTypedValueSerializer2) {
            return jsonSerializerTypedValueSerializer2;
        }
        JsonSerializer<Object> jsonSerializerFindValueSerializer = this.findValueSerializer(cls, beanProperty);
        final SerializerFactory serializerFactory = _serializerFactory;
        final SerializationConfig serializationConfig = _config;
        final TypeSerializer typeSerializerCreateTypeSerializer = serializerFactory.createTypeSerializer(serializationConfig, serializationConfig.constructType(cls));
        if (null != typeSerializerCreateTypeSerializer) {
            jsonSerializerFindValueSerializer = new TypeWrappedSerializer(typeSerializerCreateTypeSerializer.forProperty(beanProperty), jsonSerializerFindValueSerializer);
        }
        if (z) {
            _serializerCache.addTypedSerializer(cls, jsonSerializerFindValueSerializer);
        }
        return jsonSerializerFindValueSerializer;
    }
    public JsonSerializer<Object> findTypedValueSerializer(final JavaType javaType, final boolean z, final BeanProperty beanProperty) throws JsonMappingException {
        final JsonSerializer<Object> jsonSerializerTypedValueSerializer = _knownSerializers.typedValueSerializer(javaType);
        if (null != jsonSerializerTypedValueSerializer) {
            return jsonSerializerTypedValueSerializer;
        }
        final JsonSerializer<Object> jsonSerializerTypedValueSerializer2 = _serializerCache.typedValueSerializer(javaType);
        if (null != jsonSerializerTypedValueSerializer2) {
            return jsonSerializerTypedValueSerializer2;
        }
        JsonSerializer<Object> jsonSerializerFindValueSerializer = this.findValueSerializer(javaType, beanProperty);
        final TypeSerializer typeSerializerCreateTypeSerializer = _serializerFactory.createTypeSerializer(_config, javaType);
        if (null != typeSerializerCreateTypeSerializer) {
            jsonSerializerFindValueSerializer = new TypeWrappedSerializer(typeSerializerCreateTypeSerializer.forProperty(beanProperty), jsonSerializerFindValueSerializer);
        }
        if (z) {
            _serializerCache.addTypedSerializer(javaType, jsonSerializerFindValueSerializer);
        }
        return jsonSerializerFindValueSerializer;
    }
    public TypeSerializer findTypeSerializer(final JavaType javaType) throws JsonMappingException {
        return _serializerFactory.createTypeSerializer(_config, javaType);
    }
    public JsonSerializer<Object> findKeySerializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        return this._handleContextualResolvable(_serializerFactory.createKeySerializer(this, javaType, _keySerializer), beanProperty);
    }
    public JsonSerializer<Object> findKeySerializer(final Class<?> cls, final BeanProperty beanProperty) throws JsonMappingException {
        return this.findKeySerializer(_config.constructType(cls), beanProperty);
    }
    public JsonSerializer<Object> getDefaultNullKeySerializer() {
        return _nullKeySerializer;
    }
    public JsonSerializer<Object> getDefaultNullValueSerializer() {
        return _nullValueSerializer;
    }
    public JsonSerializer<Object> findNullKeySerializer(final JavaType javaType, final BeanProperty beanProperty) throws JsonMappingException {
        return _nullKeySerializer;
    }
    public JsonSerializer<Object> findNullValueSerializer(final BeanProperty beanProperty) throws JsonMappingException {
        return _nullValueSerializer;
    }
    public JsonSerializer<Object> getUnknownTypeSerializer(final Class<?> cls) {
        if (Object.class == cls) {
            return _unknownTypeSerializer;
        }
        return new UnknownSerializer(cls);
    }
    public boolean isUnknownTypeSerializer(final JsonSerializer<?> jsonSerializer) {
        if (jsonSerializer == _unknownTypeSerializer || null == jsonSerializer) {
            return true;
        }
        return this.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS) && UnknownSerializer.class == jsonSerializer.getClass();
    }
    public JsonSerializer<?> handlePrimaryContextualization(final JsonSerializer<?> jsonSerializer, final BeanProperty beanProperty) throws JsonMappingException {
        return ((jsonSerializer==null) || !(jsonSerializer instanceof ContextualSerializer)) ? jsonSerializer : ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }
    public JsonSerializer<?> handleSecondaryContextualization(final JsonSerializer<?> jsonSerializer, final BeanProperty beanProperty) throws JsonMappingException {
        return ( jsonSerializer==null || !(jsonSerializer instanceof ContextualSerializer)) ? jsonSerializer : ((ContextualSerializer) jsonSerializer).createContextual(this, beanProperty);
    }
    public final void defaultSerializeValue(final Object obj, final JsonGenerator jsonGenerator) throws IOException {
        if (obj==null) {
            if (_stdNullValueSerializer) {
                jsonGenerator.writeNull();
                return;
            } else {
                _nullValueSerializer.serialize(null, jsonGenerator, this);
                return;
            }
        }
        this.findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
    }
    public final void defaultSerializeField(final String str, final Object obj, final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeFieldName(str);
        if (null == obj) {
            if (_stdNullValueSerializer) {
                jsonGenerator.writeNull();
                return;
            } else {
                _nullValueSerializer.serialize(null, jsonGenerator, this);
                return;
            }
        }
        this.findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
    }
    public final void defaultSerializeDateValue(final long j2, final JsonGenerator jsonGenerator) throws IOException {
        if (this.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j2);
        } else {
            jsonGenerator.writeString(this._dateFormat().format(new Date(j2)));
        }
    }
    public final void defaultSerializeDateValue(final Date date, final JsonGenerator jsonGenerator) throws IOException {
        if (this.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
        } else {
            jsonGenerator.writeString(this._dateFormat().format(date));
        }
    }
    public void defaultSerializeDateKey(final long j2, final JsonGenerator jsonGenerator) throws IOException {
        if (this.isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j2));
        } else {
            jsonGenerator.writeFieldName(this._dateFormat().format(new Date(j2)));
        }
    }
    public void defaultSerializeDateKey(final Date date, final JsonGenerator jsonGenerator) throws IOException {
        if (this.isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
        } else {
            jsonGenerator.writeFieldName(this._dateFormat().format(date));
        }
    }
    public final void defaultSerializeNull(final JsonGenerator jsonGenerator) throws IOException {
        if (_stdNullValueSerializer) {
            jsonGenerator.writeNull();
        } else {
            _nullValueSerializer.serialize(null, jsonGenerator, this);
        }
    }
    public void reportMappingProblem(final String str, final Object... objArr) throws JsonMappingException {
        throw this.mappingException(str, objArr);
    }
    public <T> T reportBadTypeDefinition(final BeanDescription beanDescription, final String str, final Object... objArr) throws JsonMappingException {
        final String strNameOf;
        if (null == beanDescription) {
            strNameOf = "N/A";
        } else {
            strNameOf = ClassUtil.nameOf(beanDescription.getBeanClass());
        }
        throw InvalidDefinitionException.from(this.getGenerator(), String.format("Invalid type definition for type %s: %s", strNameOf, this._format(str, objArr)), beanDescription, null);
    }
    public <T> T reportBadPropertyDefinition(final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition, final String str, final Object... objArr) throws JsonMappingException {
        final String str_quotedString;
        final String str_format = this._format(str, objArr);
        String strNameOf = "N/A";
        if (null == beanPropertyDefinition) {
            str_quotedString = "N/A";
        } else {
            str_quotedString = this._quotedString(beanPropertyDefinition.getName());
        }
        if (null != beanDescription) {
            strNameOf = ClassUtil.nameOf(beanDescription.getBeanClass());
        }
        throw InvalidDefinitionException.from(this.getGenerator(), String.format("Invalid definition for property %s (of type %s): %s", str_quotedString, strNameOf, str_format), beanDescription, beanPropertyDefinition);
    }
    public <T> T reportBadDefinition(final JavaType javaType, final String str) throws JsonMappingException {
        throw InvalidDefinitionException.from(this.getGenerator(), str, javaType);
    }
    public <T> T reportBadDefinition(final JavaType javaType, final String str, final Throwable th) throws JsonMappingException {
        final InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from(this.getGenerator(), str, javaType);
        invalidDefinitionExceptionFrom.initCause(th);
        throw invalidDefinitionExceptionFrom;
    }
    public <T> T reportBadDefinition(final Class<?> cls, final String str, final Throwable th) throws JsonMappingException {
        final InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from(this.getGenerator(), str, this.constructType(cls));
        invalidDefinitionExceptionFrom.initCause(th);
        throw invalidDefinitionExceptionFrom;
    }
    public void reportMappingProblem(final Throwable th, final String str, final Object... objArr) throws JsonMappingException {
        throw JsonMappingException.from(this.getGenerator(), this._format(str, objArr), th);
    }
    public JsonMappingException invalidTypeIdException(final JavaType javaType, final String str, final String str2) {
        return InvalidTypeIdException.from(null, this._colonConcat(String.format("Could not resolve type id '%s' as a subtype of %s", str, ClassUtil.getTypeDescription(javaType)), str2), javaType, str);
    }
    public JsonMappingException mappingException(final String str, final Object... objArr) {
        return JsonMappingException.from(this.getGenerator(), this._format(str, objArr));
    }
    protected JsonMappingException mappingException(final Throwable th, final String str, final Object... objArr) {
        return JsonMappingException.from(this.getGenerator(), this._format(str, objArr), th);
    }
    protected void _reportIncompatibleRootType(final Object obj, final JavaType javaType) throws IOException {
        if (javaType.isPrimitive() && ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            return;
        }
        this.reportBadDefinition(javaType, String.format("Incompatible types: declared root type (%s) vs %s", javaType, ClassUtil.classNameOf(obj)));
    }
    protected JsonSerializer<Object> _findExplicitUntypedSerializer(final Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializerUntypedValueSerializer = _knownSerializers.untypedValueSerializer(cls);
        if (null == jsonSerializerUntypedValueSerializer && null == (jsonSerializerUntypedValueSerializer = this._serializerCache.untypedValueSerializer(cls))) {
            jsonSerializerUntypedValueSerializer = this._createAndCacheUntypedSerializer(cls);
        }
        if (this.isUnknownTypeSerializer(jsonSerializerUntypedValueSerializer)) {
            return null;
        }
        return jsonSerializerUntypedValueSerializer;
    }
    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(final Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer_createUntypedSerializer;
        final JavaType javaTypeConstructType = _config.constructType(cls);
        try {
            jsonSerializer_createUntypedSerializer = this._createUntypedSerializer(javaTypeConstructType);
        } catch (final IllegalArgumentException e2) {
            this.reportMappingProblem(e2, ClassUtil.exceptionMessage(e2));
            jsonSerializer_createUntypedSerializer = null;
        }
        if (null != jsonSerializer_createUntypedSerializer) {
            _serializerCache.addAndResolveNonTypedSerializer(cls, javaTypeConstructType, jsonSerializer_createUntypedSerializer, this);
        }
        return jsonSerializer_createUntypedSerializer;
    }
    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(final JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer_createUntypedSerializer;
        try {
            jsonSerializer_createUntypedSerializer = this._createUntypedSerializer(javaType);
        } catch (final IllegalArgumentException e2) {
            this.reportMappingProblem(e2, ClassUtil.exceptionMessage(e2));
            jsonSerializer_createUntypedSerializer = null;
        }
        if (null != jsonSerializer_createUntypedSerializer) {
            _serializerCache.addAndResolveNonTypedSerializer(javaType, jsonSerializer_createUntypedSerializer, this);
        }
        return jsonSerializer_createUntypedSerializer;
    }
    protected JsonSerializer<Object> _createUntypedSerializer(final JavaType javaType) throws JsonMappingException {
        return _serializerFactory.createSerializer(this, javaType);
    }
    protected JsonSerializer<Object> _handleContextualResolvable(final JsonSerializer<?> jsonSerializer, final BeanProperty beanProperty) throws JsonMappingException {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return (JsonSerializer<Object>) this.handleSecondaryContextualization(jsonSerializer, beanProperty);
    }
    protected JsonSerializer<Object> _handleResolvable(final JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        if (jsonSerializer instanceof ResolvableSerializer) {
            ((ResolvableSerializer) jsonSerializer).resolve(this);
        }
        return (JsonSerializer<Object>) jsonSerializer;
    }
    protected final DateFormat _dateFormat() {
        final DateFormat dateFormat = _dateFormat;
        if (null != dateFormat) {
            return dateFormat;
        }
        final DateFormat dateFormat2 = (DateFormat) _config.getDateFormat().clone();
        _dateFormat = dateFormat2;
        return dateFormat2;
    }
}
