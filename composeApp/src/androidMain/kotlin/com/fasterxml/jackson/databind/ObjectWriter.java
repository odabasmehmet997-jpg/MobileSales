package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.*;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectWriter implements Serializable {
    public static final PrettyPrinter NULL_PRETTY_PRINTER = null;
    private static final long serialVersionUID = 1;
    protected final SerializationConfig _config;
    protected final JsonFactory _generatorFactory;
    protected final GeneratorSettings _generatorSettings;
    protected final Prefetch _prefetch;
    protected final SerializerFactory _serializerFactory;
    protected final DefaultSerializerProvider _serializerProvider;
    protected ObjectWriter(final ObjectMapper objectMapper, final SerializationConfig serializationConfig, final JavaType javaType, final PrettyPrinter prettyPrinter) {
        _config = serializationConfig;
        _serializerProvider = objectMapper._serializerProvider;
        _serializerFactory = objectMapper._serializerFactory;
        _generatorFactory = objectMapper._jsonFactory;
        _generatorSettings = null == prettyPrinter ? GeneratorSettings.empty : new GeneratorSettings(prettyPrinter, null, null, null);
        if (null == javaType) {
            _prefetch = Prefetch.empty;
        } else if (javaType.hasRawClass(Object.class)) {
            _prefetch = Prefetch.empty.forRootType(this, javaType);
        } else {
            _prefetch = Prefetch.empty.forRootType(this, javaType.withStaticTyping());
        }
    }
    protected ObjectWriter(final ObjectMapper objectMapper, final SerializationConfig serializationConfig) {
        _config = serializationConfig;
        _serializerProvider = objectMapper._serializerProvider;
        _serializerFactory = objectMapper._serializerFactory;
        _generatorFactory = objectMapper._jsonFactory;
        _generatorSettings = GeneratorSettings.empty;
        _prefetch = Prefetch.empty;
    }
    protected ObjectWriter(final ObjectMapper objectMapper, final SerializationConfig serializationConfig, final FormatSchema formatSchema) {
        _config = serializationConfig;
        _serializerProvider = objectMapper._serializerProvider;
        _serializerFactory = objectMapper._serializerFactory;
        _generatorFactory = objectMapper._jsonFactory;
        _generatorSettings = null == formatSchema ? GeneratorSettings.empty : new GeneratorSettings(null, formatSchema, null, null);
        _prefetch = Prefetch.empty;
    }
    protected ObjectWriter(final ObjectWriter objectWriter, final SerializationConfig serializationConfig, final GeneratorSettings generatorSettings, final Prefetch prefetch) {
        _config = serializationConfig;
        _serializerProvider = objectWriter._serializerProvider;
        _serializerFactory = objectWriter._serializerFactory;
        _generatorFactory = objectWriter._generatorFactory;
        _generatorSettings = generatorSettings;
        _prefetch = prefetch;
    }
    protected ObjectWriter(final ObjectWriter objectWriter, final SerializationConfig serializationConfig) {
        _config = serializationConfig;
        _serializerProvider = objectWriter._serializerProvider;
        _serializerFactory = objectWriter._serializerFactory;
        _generatorFactory = objectWriter._generatorFactory;
        _generatorSettings = objectWriter._generatorSettings;
        _prefetch = objectWriter._prefetch;
    }
    protected ObjectWriter(final ObjectWriter objectWriter, final JsonFactory jsonFactory) {
        _config = objectWriter._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonFactory.requiresPropertyOrdering());
        _serializerProvider = objectWriter._serializerProvider;
        _serializerFactory = objectWriter._serializerFactory;
        _generatorFactory = jsonFactory;
        _generatorSettings = objectWriter._generatorSettings;
        _prefetch = objectWriter._prefetch;
    }
    public Version version() {
        return PackageVersion.VERSION;
    }
    protected ObjectWriter _new(final ObjectWriter objectWriter, final JsonFactory jsonFactory) {
        return new ObjectWriter(objectWriter, jsonFactory);
    }
    protected ObjectWriter _new(final ObjectWriter objectWriter, final SerializationConfig serializationConfig) {
        return serializationConfig == _config ? this : new ObjectWriter(objectWriter, serializationConfig);
    }
    protected ObjectWriter _new(final GeneratorSettings generatorSettings, final Prefetch prefetch) {
        return (_generatorSettings == generatorSettings && _prefetch == prefetch) ? this : new ObjectWriter(this, _config, generatorSettings, prefetch);
    }
    protected SequenceWriter _newSequenceWriter(final boolean z, final JsonGenerator jsonGenerator, final boolean z2) throws IOException {
        return new SequenceWriter(this._serializerProvider(), this._configureGenerator(jsonGenerator), z2, _prefetch).init(z);
    }
    public ObjectWriter with(final SerializationFeature serializationFeature) {
        return this._new(this, _config.with(serializationFeature));
    }
    public ObjectWriter with(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        return this._new(this, _config.with(serializationFeature, serializationFeatureArr));
    }
    public ObjectWriter withFeatures(final SerializationFeature... serializationFeatureArr) {
        return this._new(this, _config.withFeatures(serializationFeatureArr));
    }
    public ObjectWriter without(final SerializationFeature serializationFeature) {
        return this._new(this, _config.without(serializationFeature));
    }
    public ObjectWriter without(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        return this._new(this, _config.without(serializationFeature, serializationFeatureArr));
    }
    public ObjectWriter withoutFeatures(final SerializationFeature... serializationFeatureArr) {
        return this._new(this, _config.withoutFeatures(serializationFeatureArr));
    }
    public ObjectWriter with(final JsonGenerator.Feature feature) {
        return this._new(this, _config.with(feature));
    }
    public ObjectWriter withFeatures(final JsonGenerator.Feature... featureArr) {
        return this._new(this, _config.withFeatures(featureArr));
    }
    public ObjectWriter without(final JsonGenerator.Feature feature) {
        return this._new(this, _config.without(feature));
    }
    public ObjectWriter withoutFeatures(final JsonGenerator.Feature... featureArr) {
        return this._new(this, _config.withoutFeatures(featureArr));
    }
    public ObjectWriter with(final StreamWriteFeature streamWriteFeature) {
        return this._new(this, _config.with(streamWriteFeature.mappedFeature()));
    }
    public ObjectWriter without(final StreamWriteFeature streamWriteFeature) {
        return this._new(this, _config.without(streamWriteFeature.mappedFeature()));
    }
    public ObjectWriter with(final FormatFeature formatFeature) {
        return this._new(this, _config.with(formatFeature));
    }
    public ObjectWriter withFeatures(final FormatFeature... formatFeatureArr) {
        return this._new(this, _config.withFeatures(formatFeatureArr));
    }
    public ObjectWriter without(final FormatFeature formatFeature) {
        return this._new(this, _config.without(formatFeature));
    }
    public ObjectWriter withoutFeatures(final FormatFeature... formatFeatureArr) {
        return this._new(this, _config.withoutFeatures(formatFeatureArr));
    }
    public ObjectWriter forType(final JavaType javaType) {
        return this._new(_generatorSettings, _prefetch.forRootType(this, javaType));
    }
    public ObjectWriter forType(final Class<?> cls) {
        return this.forType(_config.constructType(cls));
    }
    public ObjectWriter forType(final TypeReference<?> typeReference) {
        return this.forType(_config.getTypeFactory().constructType(typeReference.getType()));
    }
    public ObjectWriter withType(final JavaType javaType) {
        return this.forType(javaType);
    }
    public ObjectWriter withType(final Class<?> cls) {
        return this.forType(cls);
    }
    public ObjectWriter withType(final TypeReference<?> typeReference) {
        return this.forType(typeReference);
    }
    public ObjectWriter with(final DateFormat dateFormat) {
        return this._new(this, _config.with(dateFormat));
    }
    public ObjectWriter withDefaultPrettyPrinter() {
        return this.with(_config.getDefaultPrettyPrinter());
    }
    public ObjectWriter with(final FilterProvider filterProvider) {
        return filterProvider == _config.getFilterProvider() ? this : this._new(this, _config.withFilters(filterProvider));
    }
    public ObjectWriter with(final PrettyPrinter prettyPrinter) {
        return this._new(_generatorSettings.with(prettyPrinter), _prefetch);
    }
    public ObjectWriter withRootName(final String str) {
        return this._new(this, _config.withRootName(str));
    }
    public ObjectWriter withRootName(final PropertyName propertyName) {
        return this._new(this, _config.withRootName(propertyName));
    }
    public ObjectWriter withoutRootName() {
        return this._new(this, _config.withRootName(PropertyName.NO_NAME));
    }
    public ObjectWriter with(final FormatSchema formatSchema) {
        this._verifySchemaType(formatSchema);
        return this._new(_generatorSettings.with(formatSchema), _prefetch);
    }
    public ObjectWriter withSchema(final FormatSchema formatSchema) {
        return this.with(formatSchema);
    }
    public ObjectWriter withView(final Class<?> cls) {
        return this._new(this, (SerializationConfig) _config.withView(cls));
    }
    public ObjectWriter with(final Locale locale) {
        return this._new(this, _config.with(locale));
    }
    public ObjectWriter with(final TimeZone timeZone) {
        return this._new(this, _config.with(timeZone));
    }
    public ObjectWriter with(final Base64Variant base64Variant) {
        return this._new(this, _config.with(base64Variant));
    }
    public ObjectWriter with(final CharacterEscapes characterEscapes) {
        return this._new(_generatorSettings.with(characterEscapes), _prefetch);
    }
    public ObjectWriter with(final JsonFactory jsonFactory) {
        return jsonFactory == _generatorFactory ? this : this._new(this, jsonFactory);
    }
    public ObjectWriter with(final ContextAttributes contextAttributes) {
        return this._new(this, _config.with(contextAttributes));
    }
    public ObjectWriter withAttributes(final Map<?, ?> map) {
        return this._new(this, _config.withAttributes(map));
    }
    public ObjectWriter withAttribute(final Object obj, final Object obj2) {
        return this._new(this, _config.withAttribute(obj, obj2));
    }
    public ObjectWriter withoutAttribute(final Object obj) {
        return this._new(this, _config.withoutAttribute(obj));
    }
    public ObjectWriter withRootValueSeparator(final String str) {
        return this._new(_generatorSettings.withRootValueSeparator(str), _prefetch);
    }
    public ObjectWriter withRootValueSeparator(final SerializableString serializableString) {
        return this._new(_generatorSettings.withRootValueSeparator(serializableString), _prefetch);
    }
    public JsonGenerator createGenerator(final OutputStream outputStream) throws IOException {
        this._assertNotNull("out", outputStream);
        return this._configureGenerator(_generatorFactory.createGenerator(outputStream, JsonEncoding.UTF8));
    }
    public JsonGenerator createGenerator(final OutputStream outputStream, final JsonEncoding jsonEncoding) throws IOException {
        this._assertNotNull("out", outputStream);
        return this._configureGenerator(_generatorFactory.createGenerator(outputStream, jsonEncoding));
    }
    public JsonGenerator createGenerator(final Writer writer) throws IOException {
        this._assertNotNull("w", writer);
        return this._configureGenerator(_generatorFactory.createGenerator(writer));
    }
    public JsonGenerator createGenerator(final File file, final JsonEncoding jsonEncoding) throws IOException {
        this._assertNotNull("outputFile", file);
        return this._configureGenerator(_generatorFactory.createGenerator(file, jsonEncoding));
    }
    public JsonGenerator createGenerator(final DataOutput dataOutput) throws IOException {
        this._assertNotNull("out", dataOutput);
        return this._configureGenerator(_generatorFactory.createGenerator(dataOutput));
    }
    public SequenceWriter writeValues(final File file) throws IOException {
        return this._newSequenceWriter(false, this.createGenerator(file, JsonEncoding.UTF8), true);
    }
    public SequenceWriter writeValues(final JsonGenerator jsonGenerator) throws IOException {
        this._assertNotNull("g", jsonGenerator);
        return this._newSequenceWriter(false, this._configureGenerator(jsonGenerator), false);
    }
    public SequenceWriter writeValues(final Writer writer) throws IOException {
        return this._newSequenceWriter(false, this.createGenerator(writer), true);
    }
    public SequenceWriter writeValues(final OutputStream outputStream) throws IOException {
        return this._newSequenceWriter(false, this.createGenerator(outputStream, JsonEncoding.UTF8), true);
    }
    public SequenceWriter writeValues(final DataOutput dataOutput) throws IOException {
        return this._newSequenceWriter(false, this.createGenerator(dataOutput), true);
    }
    public SequenceWriter writeValuesAsArray(final File file) throws IOException {
        return this._newSequenceWriter(true, this.createGenerator(file, JsonEncoding.UTF8), true);
    }
    public SequenceWriter writeValuesAsArray(final JsonGenerator jsonGenerator) throws IOException {
        this._assertNotNull("gen", jsonGenerator);
        return this._newSequenceWriter(true, jsonGenerator, false);
    }
    public SequenceWriter writeValuesAsArray(final Writer writer) throws IOException {
        return this._newSequenceWriter(true, this.createGenerator(writer), true);
    }
    public SequenceWriter writeValuesAsArray(final OutputStream outputStream) throws IOException {
        return this._newSequenceWriter(true, this.createGenerator(outputStream, JsonEncoding.UTF8), true);
    }
    public SequenceWriter writeValuesAsArray(final DataOutput dataOutput) throws IOException {
        return this._newSequenceWriter(true, this.createGenerator(dataOutput), true);
    }
    public boolean isEnabled(final SerializationFeature serializationFeature) {
        return _config.isEnabled(serializationFeature);
    }
    public boolean isEnabled(final MapperFeature mapperFeature) {
        return _config.isEnabled(mapperFeature);
    }
    public boolean isEnabled(final JsonParser.Feature feature) {
        return _generatorFactory.isEnabled(feature);
    }
    public boolean isEnabled(final JsonGenerator.Feature feature) {
        return _generatorFactory.isEnabled(feature);
    }
    public boolean isEnabled(final StreamWriteFeature streamWriteFeature) {
        return _generatorFactory.isEnabled(streamWriteFeature);
    }
    public SerializationConfig getConfig() {
        return _config;
    }
    public JsonFactory getFactory() {
        return _generatorFactory;
    }
    public TypeFactory getTypeFactory() {
        return _config.getTypeFactory();
    }
    public boolean hasPrefetchedSerializer() {
        return _prefetch.hasSerializer();
    }
    public ContextAttributes getAttributes() {
        return _config.getAttributes();
    }
    public void writeValue(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        this._assertNotNull("g", jsonGenerator);
        this._configureGenerator(jsonGenerator);
        if (_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable closeable)) {
            try {
                _prefetch.serialize(jsonGenerator, obj, this._serializerProvider());
                if (_config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                    jsonGenerator.flush();
                }
                closeable.close();
                return;
            } catch (final Exception e2) {
                ClassUtil.closeOnFailAndThrowAsIOE(null, closeable, e2);
                return;
            }
        }
        _prefetch.serialize(jsonGenerator, obj, this._serializerProvider());
        if (_config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }
    public void writeValue(final File file, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(file, JsonEncoding.UTF8), obj);
    }
    public void writeValue(final OutputStream outputStream, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }
    public void writeValue(final Writer writer, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(writer), obj);
    }
    public void writeValue(final DataOutput dataOutput, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(dataOutput), obj);
    }
    public String writeValueAsString(final Object obj) throws JsonProcessingException {
        final SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(_generatorFactory._getBufferRecycler());
        try {
            this._writeValueAndClose(this.createGenerator(segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public byte[] writeValueAsBytes(final Object obj) throws JsonProcessingException {
        final ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(_generatorFactory._getBufferRecycler());
        try {
            this._writeValueAndClose(this.createGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
            final byte[] byteArray = byteArrayBuilder.toByteArray();
            byteArrayBuilder.release();
            return byteArray;
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public void acceptJsonFormatVisitor(final JavaType javaType, final JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        this._assertNotNull("type", javaType);
        this._assertNotNull("visitor", jsonFormatVisitorWrapper);
        this._serializerProvider().acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
    }
    public void acceptJsonFormatVisitor(final Class<?> cls, final JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        this._assertNotNull("type", cls);
        this._assertNotNull("visitor", jsonFormatVisitorWrapper);
        this.acceptJsonFormatVisitor(_config.constructType(cls), jsonFormatVisitorWrapper);
    }
    public boolean canSerialize(final Class<?> cls) {
        this._assertNotNull("type", cls);
        return this._serializerProvider().hasSerializerFor(cls, null);
    }
    public boolean canSerialize(final Class<?> cls, final AtomicReference<Throwable> atomicReference) {
        this._assertNotNull("type", cls);
        return this._serializerProvider().hasSerializerFor(cls, atomicReference);
    }
    protected DefaultSerializerProvider _serializerProvider() {
        return _serializerProvider.createInstance(_config, _serializerFactory);
    }
    protected void _verifySchemaType(final FormatSchema formatSchema) {
        if (null == formatSchema || _generatorFactory.canUseSchema(formatSchema)) {
            return;
        }
        throw new IllegalArgumentException("Cannot use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + _generatorFactory.getFormatName());
    }
    protected final void _writeValueAndClose(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        if (_config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            this._writeCloseable(jsonGenerator, obj);
            return;
        }
        try {
            _prefetch.serialize(jsonGenerator, obj, this._serializerProvider());
            jsonGenerator.close();
        } catch (final Exception e2) {
            ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, e2);
        }
    }
    private void _writeCloseable(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        Closeable closeable = (Closeable) obj;
        Exception e;
        try {
            _prefetch.serialize(jsonGenerator, obj, this._serializerProvider());
            try {
                closeable.close();
                jsonGenerator.close();
            } catch (final Exception e2) {
                e = e2;
                closeable = null;
                ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, closeable, e);
            }
        } catch (final Exception e3) {
            e = e3;
        }
    }
    protected final JsonGenerator _configureGenerator(final JsonGenerator jsonGenerator) {
        _config.initialize(jsonGenerator);
        _generatorSettings.initialize(jsonGenerator);
        return jsonGenerator;
    }
    protected final void _assertNotNull(final String str, final Object obj) {
        if (null == obj) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }
    public static final class GeneratorSettings implements Serializable {
        public static final GeneratorSettings empty = new GeneratorSettings(null, null, null, null);
        private static final long serialVersionUID = 1;
        public final CharacterEscapes characterEscapes;
        public final PrettyPrinter prettyPrinter;
        public final SerializableString rootValueSeparator;
        public final FormatSchema schema = null;
        private PrettyPrinter NULL_PRETTY_PRINTER;

        public GeneratorSettings(final PrettyPrinter prettyPrinter, final FormatSchema formatSchema, final CharacterEscapes characterEscapes, final SerializableString serializableString) {
            this.prettyPrinter = prettyPrinter;
            this.characterEscapes = characterEscapes;
            rootValueSeparator = serializableString;
        }

        public GeneratorSettings with(PrettyPrinter prettyPrinter) {
            if (null == prettyPrinter) {
                prettyPrinter = NULL_PRETTY_PRINTER;
            }
            return prettyPrinter == this.prettyPrinter ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, rootValueSeparator);
        }

        public GeneratorSettings with(final FormatSchema formatSchema) {
            return null == formatSchema ? this : new GeneratorSettings(prettyPrinter, formatSchema, characterEscapes, rootValueSeparator);
        }

        public GeneratorSettings with(final CharacterEscapes characterEscapes) {
            return this.characterEscapes == characterEscapes ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, rootValueSeparator);
        }

        public GeneratorSettings withRootValueSeparator(final String str) {
            return null == str ? null == this.rootValueSeparator ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, null) : str.equals(this._rootValueSeparatorAsString()) ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, new SerializedString(str));
        }

        public GeneratorSettings withRootValueSeparator(final SerializableString serializableString) {
            return null == serializableString ? null == this.rootValueSeparator ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, null) : serializableString.equals(rootValueSeparator) ? this : new GeneratorSettings(prettyPrinter, null, characterEscapes, serializableString);
        }

        private String _rootValueSeparatorAsString() {
            final SerializableString serializableString = rootValueSeparator;
            if (null == serializableString) {
                return null;
            }
            return serializableString.getValue();
        }

        public void initialize(final JsonGenerator jsonGenerator) {
            PrettyPrinter prettyPrinter = this.prettyPrinter;
            if (null != prettyPrinter) {
                if (prettyPrinter == NULL_PRETTY_PRINTER) {
                    jsonGenerator.setPrettyPrinter(null);
                } else {
                    if (prettyPrinter instanceof Instantiatable) {
                        prettyPrinter = (PrettyPrinter) ((Instantiatable) prettyPrinter).createInstance();
                    }
                    jsonGenerator.setPrettyPrinter(prettyPrinter);
                }
            }
            final CharacterEscapes characterEscapes = this.characterEscapes;
            if (null != characterEscapes) {
                jsonGenerator.setCharacterEscapes(characterEscapes);
            }
            final SerializableString serializableString = rootValueSeparator;
            if (null != serializableString) {
                jsonGenerator.setRootValueSeparator(serializableString);
            }
        }
    }
    public static final class Prefetch implements Serializable {
        public static final Prefetch empty = new Prefetch(null, null, null);
        private static final long serialVersionUID = 1;
        private final JavaType rootType;
        private final TypeSerializer typeSerializer;
        private final JsonSerializer<Object> valueSerializer;

        private Prefetch(final JavaType javaType, final JsonSerializer<Object> jsonSerializer, final TypeSerializer typeSerializer) {
            rootType = javaType;
            valueSerializer = jsonSerializer;
            this.typeSerializer = typeSerializer;
        }

        public Prefetch forRootType(final ObjectWriter objectWriter, final JavaType javaType) {
            if (null == javaType) {
                return (null == this.rootType || null == this.valueSerializer) ? this : new Prefetch(null, null, null);
            }
            if (javaType.equals(rootType)) {
                return this;
            }
            if (javaType.isJavaLangObject()) {
                try {
                    return new Prefetch(null, null, objectWriter._serializerProvider().findTypeSerializer(javaType));
                } catch (final JsonMappingException e2) {
                    throw new RuntimeJsonMappingException(e2);
                }
            }
            if (objectWriter.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH)) {
                try {
                    final JsonSerializer<Object> jsonSerializerFindTypedValueSerializer = objectWriter._serializerProvider().findTypedValueSerializer(javaType, true, null);
                    if (jsonSerializerFindTypedValueSerializer instanceof TypeWrappedSerializer) {
                        return new Prefetch(javaType, null, ((TypeWrappedSerializer) jsonSerializerFindTypedValueSerializer).typeSerializer());
                    }
                    return new Prefetch(javaType, jsonSerializerFindTypedValueSerializer, null);
                } catch (final JsonMappingException unused) {
                }
            }
            return new Prefetch(javaType, null, typeSerializer);
        }

        public JsonSerializer<Object> getValueSerializer() {
            return valueSerializer;
        }

        public TypeSerializer getTypeSerializer() {
            return typeSerializer;
        }

        public boolean hasSerializer() {
            return null != this.valueSerializer || null != this.typeSerializer;
        }

        public void serialize(final JsonGenerator jsonGenerator, final Object obj, final DefaultSerializerProvider defaultSerializerProvider) throws IOException {
            final TypeSerializer typeSerializer = this.typeSerializer;
            if (null != typeSerializer) {
                defaultSerializerProvider.serializePolymorphic(jsonGenerator, obj, rootType, valueSerializer, typeSerializer);
                return;
            }
            final JsonSerializer<Object> jsonSerializer = valueSerializer;
            if (null != jsonSerializer) {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj, rootType, jsonSerializer);
                return;
            }
            final JavaType javaType = rootType;
            if (null != javaType) {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj, javaType);
            } else {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj);
            }
        }
    }
}
