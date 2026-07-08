package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
public class ObjectReader extends ObjectCodec implements Serializable {
    private static final long serialVersionUID = 2;
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    private final TokenFilter _filter;
    protected final InjectableValues _injectableValues;
    protected transient JavaType _jsonNodeType;
    protected final JsonFactory _parserFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final FormatSchema _schema = null;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;
    protected ObjectReader(final ObjectMapper objectMapper, final DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, null, null, null, null);
    }
    protected ObjectReader(final ObjectMapper objectMapper, final DeserializationConfig deserializationConfig, final JavaType javaType, final Object obj, final FormatSchema formatSchema, final InjectableValues injectableValues) {
        _config = deserializationConfig;
        _context = objectMapper._deserializationContext;
        _rootDeserializers = objectMapper._rootDeserializers;
        _parserFactory = objectMapper._jsonFactory;
        _valueType = javaType;
        _valueToUpdate = obj;
        _injectableValues = injectableValues;
        _unwrapRoot = deserializationConfig.useRootWrapping();
        _rootDeserializer = this._prefetchRootDeserializer(javaType);
        _dataFormatReaders = null;
        _filter = null;
    }
    protected ObjectReader(final ObjectReader objectReader, final DeserializationConfig deserializationConfig, final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final Object obj, final FormatSchema formatSchema, final InjectableValues injectableValues, final DataFormatReaders dataFormatReaders) {
        _config = deserializationConfig;
        _context = objectReader._context;
        _rootDeserializers = objectReader._rootDeserializers;
        _parserFactory = objectReader._parserFactory;
        _valueType = javaType;
        _rootDeserializer = jsonDeserializer;
        _valueToUpdate = obj;
        _injectableValues = injectableValues;
        _unwrapRoot = deserializationConfig.useRootWrapping();
        _dataFormatReaders = dataFormatReaders;
        _filter = objectReader._filter;
    }
    protected ObjectReader(final ObjectReader objectReader, final DeserializationConfig deserializationConfig) {
        _config = deserializationConfig;
        _context = objectReader._context;
        _rootDeserializers = objectReader._rootDeserializers;
        _parserFactory = objectReader._parserFactory;
        _valueType = objectReader._valueType;
        _rootDeserializer = objectReader._rootDeserializer;
        _valueToUpdate = objectReader._valueToUpdate;
        _injectableValues = objectReader._injectableValues;
        _unwrapRoot = deserializationConfig.useRootWrapping();
        _dataFormatReaders = objectReader._dataFormatReaders;
        _filter = objectReader._filter;
    }
    protected ObjectReader(final ObjectReader objectReader, final JsonFactory jsonFactory) {
        _config = objectReader._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonFactory.requiresPropertyOrdering());
        _context = objectReader._context;
        _rootDeserializers = objectReader._rootDeserializers;
        _parserFactory = jsonFactory;
        _valueType = objectReader._valueType;
        _rootDeserializer = objectReader._rootDeserializer;
        _valueToUpdate = objectReader._valueToUpdate;
        _injectableValues = objectReader._injectableValues;
        _unwrapRoot = objectReader._unwrapRoot;
        _dataFormatReaders = objectReader._dataFormatReaders;
        _filter = objectReader._filter;
    }
    protected ObjectReader(final ObjectReader objectReader, final TokenFilter tokenFilter) {
        _config = objectReader._config;
        _context = objectReader._context;
        _rootDeserializers = objectReader._rootDeserializers;
        _parserFactory = objectReader._parserFactory;
        _valueType = objectReader._valueType;
        _rootDeserializer = objectReader._rootDeserializer;
        _valueToUpdate = objectReader._valueToUpdate;
        _injectableValues = objectReader._injectableValues;
        _unwrapRoot = objectReader._unwrapRoot;
        _dataFormatReaders = objectReader._dataFormatReaders;
        _filter = tokenFilter;
    }
    public Version version() {
        return PackageVersion.VERSION;
    }
    protected ObjectReader _new(final ObjectReader objectReader, final JsonFactory jsonFactory) {
        return new ObjectReader(objectReader, jsonFactory);
    }
    protected ObjectReader _new(final ObjectReader objectReader, final DeserializationConfig deserializationConfig) {
        return new ObjectReader(objectReader, deserializationConfig);
    }
    protected ObjectReader _new(final ObjectReader objectReader, final DeserializationConfig deserializationConfig, final JavaType javaType, final JsonDeserializer<Object> jsonDeserializer, final Object obj, final FormatSchema formatSchema, final InjectableValues injectableValues, final DataFormatReaders dataFormatReaders) {
        return new ObjectReader(objectReader, deserializationConfig, javaType, jsonDeserializer, obj, formatSchema, injectableValues, dataFormatReaders);
    }
    protected <T> MappingIterator<T> _newIterator(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonDeserializer<?> jsonDeserializer, final boolean z) {
        return new MappingIterator<>(_valueType, jsonParser, deserializationContext, jsonDeserializer, z, _valueToUpdate);
    }
    protected JsonToken _initForReading(final DeserializationContext deserializationContext, final JsonParser jsonParser) throws IOException {
        _config.initialize(jsonParser, null);
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
            deserializationContext.reportInputMismatch(_valueType, "No content to map due to end-of-input");
        }
        return jsonTokenCurrentToken;
    }
    protected void _initForMultiRead(final DeserializationContext deserializationContext, final JsonParser jsonParser) throws IOException {
        _config.initialize(jsonParser, null);
    }
    public ObjectReader with(final DeserializationFeature deserializationFeature) {
        return this._with(_config.with(deserializationFeature));
    }
    public ObjectReader with(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        return this._with(_config.with(deserializationFeature, deserializationFeatureArr));
    }
    public ObjectReader withFeatures(final DeserializationFeature... deserializationFeatureArr) {
        return this._with(_config.withFeatures(deserializationFeatureArr));
    }
    public ObjectReader without(final DeserializationFeature deserializationFeature) {
        return this._with(_config.without(deserializationFeature));
    }
    public ObjectReader without(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        return this._with(_config.without(deserializationFeature, deserializationFeatureArr));
    }
    public ObjectReader withoutFeatures(final DeserializationFeature... deserializationFeatureArr) {
        return this._with(_config.withoutFeatures(deserializationFeatureArr));
    }
    public ObjectReader with(final JsonParser.Feature feature) {
        return this._with(_config.with(feature));
    }
    public ObjectReader withFeatures(final JsonParser.Feature... featureArr) {
        return this._with(_config.withFeatures(featureArr));
    }
    public ObjectReader without(final JsonParser.Feature feature) {
        return this._with(_config.without(feature));
    }
    public ObjectReader withoutFeatures(final JsonParser.Feature... featureArr) {
        return this._with(_config.withoutFeatures(featureArr));
    }
    public ObjectReader with(final StreamReadFeature streamReadFeature) {
        return this._with(_config.with(streamReadFeature.mappedFeature()));
    }
    public ObjectReader without(final StreamReadFeature streamReadFeature) {
        return this._with(_config.without(streamReadFeature.mappedFeature()));
    }
    public ObjectReader with(final FormatFeature formatFeature) {
        return this._with(_config.with(formatFeature));
    }
    public ObjectReader withFeatures(final FormatFeature... formatFeatureArr) {
        return this._with(_config.withFeatures(formatFeatureArr));
    }
    public ObjectReader without(final FormatFeature formatFeature) {
        return this._with(_config.without(formatFeature));
    }
    public ObjectReader withoutFeatures(final FormatFeature... formatFeatureArr) {
        return this._with(_config.withoutFeatures(formatFeatureArr));
    }
    public ObjectReader at(final String str) {
        this._assertNotNull("pointerExpr", str);
        return new ObjectReader(this, new JsonPointerBasedFilter(str));
    }
    public ObjectReader at(final JsonPointer jsonPointer) {
        this._assertNotNull("pointer", jsonPointer);
        return new ObjectReader(this, new JsonPointerBasedFilter(jsonPointer));
    }
    public ObjectReader with(final DeserializationConfig deserializationConfig) {
        return this._with(deserializationConfig);
    }
    public ObjectReader with(final InjectableValues injectableValues) {
        return _injectableValues == injectableValues ? this : this._new(this, _config, _valueType, _rootDeserializer, _valueToUpdate, null, injectableValues, _dataFormatReaders);
    }
    public ObjectReader with(final JsonNodeFactory jsonNodeFactory) {
        return this._with(_config.with(jsonNodeFactory));
    }
    public ObjectReader with(final JsonFactory jsonFactory) {
        if (jsonFactory == _parserFactory) {
            return this;
        }
        final ObjectReader objectReader_new = this._new(this, jsonFactory);
        if (null == jsonFactory.getCodec()) {
            jsonFactory.setCodec(objectReader_new);
        }
        return objectReader_new;
    }
    public ObjectReader withRootName(final String str) {
        return this._with(_config.withRootName(str));
    }
    public ObjectReader withRootName(final PropertyName propertyName) {
        return this._with(_config.withRootName(propertyName));
    }
    public ObjectReader withoutRootName() {
        return this._with(_config.withRootName(PropertyName.NO_NAME));
    }
    public ObjectReader with(final FormatSchema formatSchema) {
        if (null == formatSchema) {
            return this;
        }
        this._verifySchemaType(formatSchema);
        return this._new(this, _config, _valueType, _rootDeserializer, _valueToUpdate, formatSchema, _injectableValues, _dataFormatReaders);
    }
    public ObjectReader forType(final JavaType javaType) {
        if (null != javaType && javaType.equals(_valueType)) {
            return this;
        }
        final JsonDeserializer<Object> jsonDeserializer_prefetchRootDeserializer = this._prefetchRootDeserializer(javaType);
        DataFormatReaders dataFormatReadersWithType = _dataFormatReaders;
        if (null != dataFormatReadersWithType) {
            dataFormatReadersWithType = dataFormatReadersWithType.withType(javaType);
        }
        return this._new(this, _config, javaType, jsonDeserializer_prefetchRootDeserializer, _valueToUpdate, null, _injectableValues, dataFormatReadersWithType);
    }
    public ObjectReader forType(final Class<?> cls) {
        return this.forType(_config.constructType(cls));
    }
    public ObjectReader forType(final TypeReference<?> typeReference) {
        return this.forType(_config.getTypeFactory().constructType(typeReference.getType()));
    }
    public ObjectReader withType(final JavaType javaType) {
        return this.forType(javaType);
    }
    public ObjectReader withType(final Class<?> cls) {
        return this.forType(_config.constructType(cls));
    }
    public ObjectReader withType(final Type type) {
        return this.forType(_config.getTypeFactory().constructType(type));
    }
    public ObjectReader withType(final TypeReference<?> typeReference) {
        return this.forType(_config.getTypeFactory().constructType(typeReference.getType()));
    }
    public ObjectReader withValueToUpdate(final Object obj) {
        if (obj == _valueToUpdate) {
            return this;
        }
        if (null == obj) {
            return this._new(this, _config, _valueType, _rootDeserializer, null, null, _injectableValues, _dataFormatReaders);
        }
        JavaType javaTypeConstructType = _valueType;
        if (null == javaTypeConstructType) {
            javaTypeConstructType = _config.constructType(obj.getClass());
        }
        return this._new(this, _config, javaTypeConstructType, _rootDeserializer, obj, null, _injectableValues, _dataFormatReaders);
    }
    public ObjectReader withView(final Class<?> cls) {
        return this._with(_config.withView(cls));
    }
    public ObjectReader with(final Locale locale) {
        return this._with(_config.with(locale));
    }
    public ObjectReader with(final TimeZone timeZone) {
        return this._with(_config.with(timeZone));
    }
    public ObjectReader withHandler(final DeserializationProblemHandler deserializationProblemHandler) {
        return this._with(_config.withHandler(deserializationProblemHandler));
    }
    public ObjectReader with(final Base64Variant base64Variant) {
        return this._with(_config.with(base64Variant));
    }
    public ObjectReader withFormatDetection(final ObjectReader... objectReaderArr) {
        return this.withFormatDetection(new DataFormatReaders(objectReaderArr));
    }
    public ObjectReader withFormatDetection(final DataFormatReaders dataFormatReaders) {
        return this._new(this, _config, _valueType, _rootDeserializer, _valueToUpdate, null, _injectableValues, dataFormatReaders);
    }
    public ObjectReader with(final ContextAttributes contextAttributes) {
        return this._with(_config.with(contextAttributes));
    }
    public ObjectReader withAttributes(final Map<?, ?> map) {
        return this._with(_config.withAttributes(map));
    }
    public ObjectReader withAttribute(final Object obj, final Object obj2) {
        return this._with(_config.withAttribute(obj, obj2));
    }
    public ObjectReader withoutAttribute(final Object obj) {
        return this._with(_config.withoutAttribute(obj));
    }
    protected ObjectReader _with(final DeserializationConfig deserializationConfig) {
        if (deserializationConfig == _config) {
            return this;
        }
        final ObjectReader objectReader_new = this._new(this, deserializationConfig);
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        return null != dataFormatReaders ? objectReader_new.withFormatDetection(dataFormatReaders.with(deserializationConfig)) : objectReader_new;
    }
    public boolean isEnabled(final DeserializationFeature deserializationFeature) {
        return _config.isEnabled(deserializationFeature);
    }
    public boolean isEnabled(final MapperFeature mapperFeature) {
        return _config.isEnabled(mapperFeature);
    }
    public boolean isEnabled(final JsonParser.Feature feature) {
        return _config.isEnabled(feature, _parserFactory);
    }
    public boolean isEnabled(final StreamReadFeature streamReadFeature) {
        return _config.isEnabled(streamReadFeature.mappedFeature(), _parserFactory);
    }
    public DeserializationConfig getConfig() {
        return _config;
    }
    public JsonFactory getFactory() {
        return _parserFactory;
    }
    public TypeFactory getTypeFactory() {
        return _config.getTypeFactory();
    }
    public ContextAttributes getAttributes() {
        return _config.getAttributes();
    }
    public InjectableValues getInjectableValues() {
        return _injectableValues;
    }
    public JavaType getValueType() {
        return _valueType;
    }
    public JsonParser createParser(final File file) throws IOException {
        this._assertNotNull("src", file);
        return _config.initialize(_parserFactory.createParser(file), null);
    }
    public JsonParser createParser(final URL url) throws IOException {
        this._assertNotNull("src", url);
        return _config.initialize(_parserFactory.createParser(url), null);
    }
    public JsonParser createParser(final InputStream inputStream) throws IOException {
        this._assertNotNull("in", inputStream);
        return _config.initialize(_parserFactory.createParser(inputStream), null);
    }
    public JsonParser createParser(final Reader reader) throws IOException {
        this._assertNotNull("r", reader);
        return _config.initialize(_parserFactory.createParser(reader), null);
    }
    public JsonParser createParser(final byte[] bArr) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _config.initialize(_parserFactory.createParser(bArr), null);
    }
    public JsonParser createParser(final byte[] bArr, final int i2, final int i3) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _config.initialize(_parserFactory.createParser(bArr, i2, i3), null);
    }
    public JsonParser createParser(final String str) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return _config.initialize(_parserFactory.createParser(str), null);
    }
    public JsonParser createParser(final char[] cArr) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return _config.initialize(_parserFactory.createParser(cArr), null);
    }
    public JsonParser createParser(final char[] cArr, final int i2, final int i3) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return _config.initialize(_parserFactory.createParser(cArr, i2, i3), null);
    }
    public JsonParser createParser(final DataInput dataInput) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, dataInput);
        return _config.initialize(_parserFactory.createParser(dataInput), null);
    }
    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        return _config.initialize(_parserFactory.createNonBlockingByteArrayParser(), null);
    }
    public <T> T readValue(final JsonParser jsonParser) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._bind(jsonParser, _valueToUpdate);
    }
    public <T> T readValue(final JsonParser jsonParser, final Class<T> cls) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(cls).readValue(jsonParser);
    }
    public <T> T readValue(final JsonParser jsonParser, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(typeReference).readValue(jsonParser);
    }
    public <T> T readValue(final JsonParser jsonParser, final ResolvedType resolvedType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType((JavaType) resolvedType).readValue(jsonParser);
    }
    public <T> T readValue(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(javaType).readValue(jsonParser);
    }
    public <T> Iterator<T> readValues(final JsonParser jsonParser, final Class<T> cls) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(cls).readValues(jsonParser);
    }
    public <T> Iterator<T> readValues(final JsonParser jsonParser, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(typeReference).readValues(jsonParser);
    }
    public <T> Iterator<T> readValues(final JsonParser jsonParser, final ResolvedType resolvedType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.readValues(jsonParser, (JavaType) resolvedType);
    }
    public <T> Iterator<T> readValues(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return this.forType(javaType).readValues(jsonParser);
    }
    public JsonNode createArrayNode() {
        return _config.getNodeFactory().arrayNode();
    }
    public JsonNode createObjectNode() {
        return _config.getNodeFactory().objectNode();
    }
    public JsonNode missingNode() {
        return _config.getNodeFactory().missingNode();
    }
    public JsonNode nullNode() {
        return _config.getNodeFactory().m827nullNode();
    }
    public JsonParser treeAsTokens(final TreeNode treeNode) {
        this._assertNotNull("n", treeNode);
        return new TreeTraversingParser((JsonNode) treeNode, this.withValueToUpdate(null));
    }
    public <T extends TreeNode> T readTree(final JsonParser jsonParser) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._bindAsTreeOrNull(jsonParser);
    }
    public void writeTree(final JsonGenerator jsonGenerator, final TreeNode treeNode) {
        throw new UnsupportedOperationException();
    }
    public <T> T readValue(final InputStream inputStream) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return (T) this._detectBindAndClose(dataFormatReaders.findFormat(inputStream), false);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(inputStream), false));
    }
    public <T> T readValue(final InputStream inputStream, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(inputStream);
    }
    public <T> T readValue(final Reader reader) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(reader);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(reader), false));
    }
    public <T> T readValue(final Reader reader, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(reader);
    }
    public <T> T readValue(final String str) throws JsonProcessingException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(str);
        }
        try {
            return (T) this._bindAndClose(this._considerFilter(this.createParser(str), false));
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public <T> T readValue(final String str, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(str);
    }
    public <T> T readValue(final byte[] bArr) throws IOException {
        if (null != this._dataFormatReaders) {
            return (T) this._detectBindAndClose(bArr, 0, bArr.length);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(bArr), false));
    }
    public <T> T readValue(final byte[] bArr, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(bArr);
    }
    public <T> T readValue(final byte[] bArr, final int i2, final int i3) throws IOException {
        if (null != this._dataFormatReaders) {
            return (T) this._detectBindAndClose(bArr, i2, i3);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(bArr, i2, i3), false));
    }
    public <T> T readValue(final byte[] bArr, final int i2, final int i3, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(bArr, i2, i3);
    }
    public <T> T readValue(final File file) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return (T) this._detectBindAndClose(dataFormatReaders.findFormat(this._inputStream(file)), true);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(file), false));
    }
    public <T> T readValue(final File file, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(file);
    }
    public <T> T readValue(final URL url) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return (T) this._detectBindAndClose(dataFormatReaders.findFormat(this._inputStream(url)), true);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(url), false));
    }
    public <T> T readValue(final URL url, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(url);
    }
    public <T> T readValue(final JsonNode jsonNode) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, jsonNode);
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(jsonNode);
        }
        return (T) this._bindAndClose(this._considerFilter(this.treeAsTokens(jsonNode), false));
    }
    public <T> T readValue(final JsonNode jsonNode, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(jsonNode);
    }
    public <T> T readValue(final DataInput dataInput) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(dataInput);
        }
        return (T) this._bindAndClose(this._considerFilter(this.createParser(dataInput), false));
    }
    public <T> T readValue(final DataInput dataInput, final Class<T> cls) throws IOException {
        return this.forType(cls).readValue(dataInput);
    }
    public JsonNode readTree(final InputStream inputStream) throws IOException {
        if (null != this._dataFormatReaders) {
            return this._detectBindAndCloseAsTree(inputStream);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this.createParser(inputStream), false));
    }
    public JsonNode readTree(final Reader reader) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(reader);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this.createParser(reader), false));
    }
    public JsonNode readTree(final String str) throws JsonProcessingException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(str);
        }
        try {
            return this._bindAndCloseAsTree(this._considerFilter(this.createParser(str), false));
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public JsonNode readTree(final byte[] bArr) throws IOException {
        this._assertNotNull("json", bArr);
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(bArr);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this.createParser(bArr), false));
    }
    public JsonNode readTree(final byte[] bArr, final int i2, final int i3) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(bArr);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this.createParser(bArr, i2, i3), false));
    }
    public JsonNode readTree(final DataInput dataInput) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(dataInput);
        }
        return this._bindAndCloseAsTree(this._considerFilter(this.createParser(dataInput), false));
    }
    public <T> MappingIterator<T> readValues(final JsonParser jsonParser) throws IOException {
        this._assertNotNull("p", jsonParser);
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
        return this._newIterator(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), false);
    }
    public <T> MappingIterator<T> readValues(final InputStream inputStream) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return this._detectBindAndReadValues(dataFormatReaders.findFormat(inputStream), false);
        }
        return this._bindAndReadValues(this._considerFilter(this.createParser(inputStream), true));
    }
    public <T> MappingIterator<T> readValues(final Reader reader) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(reader);
        }
        final JsonParser jsonParser_considerFilter = this._considerFilter(this.createParser(reader), true);
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser_considerFilter);
        this._initForMultiRead(defaultDeserializationContextCreateDeserializationContext, jsonParser_considerFilter);
        jsonParser_considerFilter.nextToken();
        return this._newIterator(jsonParser_considerFilter, defaultDeserializationContextCreateDeserializationContext, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), true);
    }
    public <T> MappingIterator<T> readValues(final String str) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(str);
        }
        final JsonParser jsonParser_considerFilter = this._considerFilter(this.createParser(str), true);
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser_considerFilter);
        this._initForMultiRead(defaultDeserializationContextCreateDeserializationContext, jsonParser_considerFilter);
        jsonParser_considerFilter.nextToken();
        return this._newIterator(jsonParser_considerFilter, defaultDeserializationContextCreateDeserializationContext, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), true);
    }
    public <T> MappingIterator<T> readValues(final byte[] bArr, final int i2, final int i3) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return this._detectBindAndReadValues(dataFormatReaders.findFormat(bArr, i2, i3), false);
        }
        return this._bindAndReadValues(this._considerFilter(this.createParser(bArr, i2, i3), true));
    }
    public final <T> MappingIterator<T> readValues(final byte[] bArr) throws IOException {
        this._assertNotNull("src", bArr);
        return this.readValues(bArr, 0, bArr.length);
    }
    public <T> MappingIterator<T> readValues(final File file) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return this._detectBindAndReadValues(dataFormatReaders.findFormat(this._inputStream(file)), false);
        }
        return this._bindAndReadValues(this._considerFilter(this.createParser(file), true));
    }
    public <T> MappingIterator<T> readValues(final URL url) throws IOException {
        final DataFormatReaders dataFormatReaders = _dataFormatReaders;
        if (null != dataFormatReaders) {
            return this._detectBindAndReadValues(dataFormatReaders.findFormat(this._inputStream(url)), true);
        }
        return this._bindAndReadValues(this._considerFilter(this.createParser(url), true));
    }
    public <T> MappingIterator<T> readValues(final DataInput dataInput) throws IOException {
        if (null != this._dataFormatReaders) {
            this._reportUndetectableSource(dataInput);
        }
        return this._bindAndReadValues(this._considerFilter(this.createParser(dataInput), true));
    }
    public <T> T treeToValue(final TreeNode treeNode, final Class<T> cls) throws JsonProcessingException {
        this._assertNotNull("n", treeNode);
        try {
            return this.readValue(this.treeAsTokens(treeNode), cls);
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public void writeValue(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }
    protected Object _bind(final JsonParser jsonParser, Object obj) throws IOException {
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
        final JsonToken jsonToken_initForReading = this._initForReading(defaultDeserializationContextCreateDeserializationContext, jsonParser);
        if (JsonToken.VALUE_NULL == jsonToken_initForReading) {
            if (null == obj) {
                obj = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext).getNullValue(defaultDeserializationContextCreateDeserializationContext);
            }
        } else if (JsonToken.END_ARRAY != jsonToken_initForReading && JsonToken.END_OBJECT != jsonToken_initForReading) {
            obj = defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, _valueType, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), _valueToUpdate);
        }
        jsonParser.clearCurrentToken();
        if (_config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, _valueType);
        }
        return obj;
    }
    protected Object _bindAndClose(final JsonParser jsonParser) throws IOException {
        Object rootValue;
        try {
            final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
            final JsonToken jsonToken_initForReading = this._initForReading(defaultDeserializationContextCreateDeserializationContext, jsonParser);
            if (JsonToken.VALUE_NULL == jsonToken_initForReading) {
                rootValue = _valueToUpdate;
                if (null == rootValue) {
                    rootValue = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext).getNullValue(defaultDeserializationContextCreateDeserializationContext);
                }
            } else if (JsonToken.END_ARRAY == jsonToken_initForReading || JsonToken.END_OBJECT == jsonToken_initForReading) {
                rootValue = _valueToUpdate;
            } else {
                rootValue = defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, _valueType, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), _valueToUpdate);
            }
            if (_config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, _valueType);
            }
            if (null != jsonParser) {
                jsonParser.close();
            }
            return rootValue;
        } catch (final Throwable th) {
            try {
                throw th;
            } catch (final Throwable th2) {
                if (null != jsonParser) {
                    try {
                        jsonParser.close();
                    } catch (final Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
    protected final JsonNode _bindAndCloseAsTree(final JsonParser jsonParser) throws IOException {
        try {
            final JsonNode jsonNode_bindAsTree = this._bindAsTree(jsonParser);
            if (null != jsonParser) {
                jsonParser.close();
            }
            return jsonNode_bindAsTree;
        } catch (final Throwable th) {
            try {
                throw th;
            } catch (final Throwable th2) {
                if (null != jsonParser) {
                    try {
                        jsonParser.close();
                    } catch (final Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
    protected final JsonNode _bindAsTree(final JsonParser jsonParser) throws IOException {
        final JsonNode jsonNodeM827nullNode;
        _config.initialize(jsonParser);
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
            return _config.getNodeFactory().missingNode();
        }
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
        if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
            jsonNodeM827nullNode = _config.getNodeFactory().m827nullNode();
        } else {
            jsonNodeM827nullNode = (JsonNode) defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, this._jsonNodeType(), this._findTreeDeserializer(defaultDeserializationContextCreateDeserializationContext), null);
        }
        if (_config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._jsonNodeType());
        }
        return jsonNodeM827nullNode;
    }
    protected final JsonNode _bindAsTreeOrNull(final JsonParser jsonParser) throws IOException {
        final JsonNode jsonNodeM827nullNode;
        _config.initialize(jsonParser);
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
            return null;
        }
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
        if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
            jsonNodeM827nullNode = _config.getNodeFactory().m827nullNode();
        } else {
            jsonNodeM827nullNode = (JsonNode) defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, this._jsonNodeType(), this._findTreeDeserializer(defaultDeserializationContextCreateDeserializationContext), null);
        }
        if (_config.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._jsonNodeType());
        }
        return jsonNodeM827nullNode;
    }
    protected <T> MappingIterator<T> _bindAndReadValues(final JsonParser jsonParser) throws IOException {
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser);
        this._initForMultiRead(defaultDeserializationContextCreateDeserializationContext, jsonParser);
        jsonParser.nextToken();
        return this._newIterator(jsonParser, defaultDeserializationContextCreateDeserializationContext, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext), true);
    }
    protected JsonParser _considerFilter(final JsonParser jsonParser, final boolean z) {
        return (null == this._filter || jsonParser instanceof FilteringParserDelegate) ? jsonParser : new FilteringParserDelegate(jsonParser, _filter, TokenFilter.Inclusion.ONLY_INCLUDE_ALL, z);
    }
    protected final void _verifyNoTrailingTokens(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JavaType javaType) throws IOException {
        final Object obj;
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        if (null != jsonTokenNextToken) {
            Class<?> clsRawClass = ClassUtil.rawClass(javaType);
            if (null == clsRawClass && null != (obj = this._valueToUpdate)) {
                clsRawClass = obj.getClass();
            }
            deserializationContext.reportTrailingTokens(clsRawClass, jsonParser, jsonTokenNextToken);
        }
    }
    protected Object _detectBindAndClose(final byte[] bArr, final int i2, final int i3) throws IOException {
        final DataFormatReaders.Match matchFindFormat = _dataFormatReaders.findFormat(bArr, i2, i3);
        if (!matchFindFormat.hasMatch()) {
            this._reportUnkownFormat(_dataFormatReaders, matchFindFormat);
        }
        return matchFindFormat.getReader()._bindAndClose(matchFindFormat.createParserWithMatch());
    }
    protected Object _detectBindAndClose(final DataFormatReaders.Match match, final boolean z) throws IOException {
        if (!match.hasMatch()) {
            this._reportUnkownFormat(_dataFormatReaders, match);
        }
        final JsonParser jsonParserCreateParserWithMatch = match.createParserWithMatch();
        if (z) {
            jsonParserCreateParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndClose(jsonParserCreateParserWithMatch);
    }
    protected <T> MappingIterator<T> _detectBindAndReadValues(final DataFormatReaders.Match match, final boolean z) throws IOException {
        if (!match.hasMatch()) {
            this._reportUnkownFormat(_dataFormatReaders, match);
        }
        final JsonParser jsonParserCreateParserWithMatch = match.createParserWithMatch();
        if (z) {
            jsonParserCreateParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndReadValues(jsonParserCreateParserWithMatch);
    }
    protected JsonNode _detectBindAndCloseAsTree(final InputStream inputStream) throws IOException {
        final DataFormatReaders.Match matchFindFormat = _dataFormatReaders.findFormat(inputStream);
        if (!matchFindFormat.hasMatch()) {
            this._reportUnkownFormat(_dataFormatReaders, matchFindFormat);
        }
        final JsonParser jsonParserCreateParserWithMatch = matchFindFormat.createParserWithMatch();
        jsonParserCreateParserWithMatch.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return matchFindFormat.getReader()._bindAndCloseAsTree(jsonParserCreateParserWithMatch);
    }
    protected void _reportUnkownFormat(final DataFormatReaders dataFormatReaders, final DataFormatReaders.Match match) throws JsonProcessingException {
        throw new JsonParseException(null, "Cannot detect format from input, does not look like any of detectable formats " + dataFormatReaders.toString());
    }
    protected void _verifySchemaType(final FormatSchema formatSchema) {
        if (null == formatSchema || _parserFactory.canUseSchema(formatSchema)) {
            return;
        }
        throw new IllegalArgumentException("Cannot use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + _parserFactory.getFormatName());
    }
    protected DefaultDeserializationContext createDeserializationContext(final JsonParser jsonParser) {
        return _context.createInstance(_config, jsonParser, _injectableValues);
    }
    protected DefaultDeserializationContext createDummyDeserializationContext() {
        return _context.createDummyInstance(_config);
    }
    protected InputStream _inputStream(final URL url) throws IOException {
        return url.openStream();
    }
    protected InputStream _inputStream(final File file) throws IOException {
        return new FileInputStream(file);
    }
    protected void _reportUndetectableSource(final Object obj) throws JsonParseException {
        throw new JsonParseException(null, "Cannot use source of type " + obj.getClass().getName() + " with format auto-detection: must be byte- not char-based");
    }
    protected JsonDeserializer<Object> _findRootDeserializer(final DeserializationContext deserializationContext) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializer = _rootDeserializer;
        if (null != jsonDeserializer) {
            return jsonDeserializer;
        }
        final JavaType javaType = _valueType;
        if (null == javaType) {
            deserializationContext.reportBadDefinition((JavaType) null, "No value type configured for ObjectReader");
        }
        final JsonDeserializer<Object> jsonDeserializer2 = _rootDeserializers.get(javaType);
        if (null != jsonDeserializer2) {
            return jsonDeserializer2;
        }
        final JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (null == jsonDeserializerFindRootValueDeserializer) {
            deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
        }
        _rootDeserializers.put(javaType, jsonDeserializerFindRootValueDeserializer);
        return jsonDeserializerFindRootValueDeserializer;
    }
    protected JsonDeserializer<Object> _findTreeDeserializer(final DeserializationContext deserializationContext) throws JsonMappingException {
        final JavaType javaType_jsonNodeType = this._jsonNodeType();
        JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = _rootDeserializers.get(javaType_jsonNodeType);
        if (null == jsonDeserializerFindRootValueDeserializer) {
            jsonDeserializerFindRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType_jsonNodeType);
            if (null == jsonDeserializerFindRootValueDeserializer) {
                deserializationContext.reportBadDefinition(javaType_jsonNodeType, "Cannot find a deserializer for type " + javaType_jsonNodeType);
            }
            _rootDeserializers.put(javaType_jsonNodeType, jsonDeserializerFindRootValueDeserializer);
        }
        return jsonDeserializerFindRootValueDeserializer;
    }
    protected JsonDeserializer<Object> _prefetchRootDeserializer(final JavaType javaType) {
        if (null == javaType || !_config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = _rootDeserializers.get(javaType);
        if (null == jsonDeserializerFindRootValueDeserializer) {
            try {
                jsonDeserializerFindRootValueDeserializer = this.createDummyDeserializationContext().findRootValueDeserializer(javaType);
                if (null != jsonDeserializerFindRootValueDeserializer) {
                    _rootDeserializers.put(javaType, jsonDeserializerFindRootValueDeserializer);
                }
            } catch (final JsonProcessingException unused) {
            }
        }
        return jsonDeserializerFindRootValueDeserializer;
    }
    protected final JavaType _jsonNodeType() {
        final JavaType javaType = _jsonNodeType;
        if (null != javaType) {
            return javaType;
        }
        final JavaType javaTypeConstructType = this.getTypeFactory().constructType(JsonNode.class);
        _jsonNodeType = javaTypeConstructType;
        return javaTypeConstructType;
    }
    protected final void _assertNotNull(final String str, final Object obj) {
        if (null == obj) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }
}
