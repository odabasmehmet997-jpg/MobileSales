package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.*;
import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.JacksonFeature;

import java.io.*;
import java.net.URL;

public class JsonFactory extends TokenStreamFactory {
    public static final char DEFAULT_QUOTE_CHAR = '\"';
    public static final String FORMAT_NAME_JSON = "JSON";
    private static final long serialVersionUID = 2;
    protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected int _maximumNonEscapedChar;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final char _quoteChar;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    public static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    public boolean canHandleBinaryNatively() {
        return false;
    }
    public boolean canUseCharArrays() {
        return true;
    }
    public int getFormatGeneratorFeatures() {
        return 0;
    }
    public int getFormatParserFeatures() {
        return 0;
    }
    public Class<? extends FormatFeature> getFormatReadFeatureType() {
        return null;
    }
    public Class<? extends FormatFeature> getFormatWriteFeatureType() {
        return null;
    }
    public boolean requiresCustomCodec() {
        return false;
    }
    public boolean requiresPropertyOrdering() {
        return false;
    }
    public enum Feature implements JacksonFeature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true),
        USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);

        private final boolean _defaultState;

        public static int collectDefaults() {
            int mask = 0;
            for (final Feature feature : Feature.values()) {
                if (feature.enabledByDefault()) {
                    mask |= feature.getMask();
                }
            }
            return mask;
        }

        Feature(final boolean z) {
            _defaultState = z;
        }

        @Override // com.fasterxml.jackson.core.util.JacksonFeature
        public boolean enabledByDefault() {
            return _defaultState;
        }

        public boolean enabledIn(final int i2) {
            return 0 != (i2 & getMask());
        }

        @Override // com.fasterxml.jackson.core.util.JacksonFeature
        public int getMask() {
            return 1 << this.ordinal();
        }
    }
    public JsonFactory() {
        this((ObjectCodec) null);
    }
    public JsonFactory(final ObjectCodec objectCodec) {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        _factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = objectCodec;
        _quoteChar = JsonFactory.DEFAULT_QUOTE_CHAR;
    }
    protected JsonFactory(final JsonFactory jsonFactory, final ObjectCodec objectCodec) {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        _factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = objectCodec;
        _factoryFeatures = jsonFactory._factoryFeatures;
        _parserFeatures = jsonFactory._parserFeatures;
        _generatorFeatures = jsonFactory._generatorFeatures;
        _inputDecorator = jsonFactory._inputDecorator;
        _outputDecorator = jsonFactory._outputDecorator;
        _characterEscapes = jsonFactory._characterEscapes;
        _rootValueSeparator = jsonFactory._rootValueSeparator;
        _maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
        _quoteChar = jsonFactory._quoteChar;
    }
    public JsonFactory(final JsonFactoryBuilder jsonFactoryBuilder) {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        _factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = null;
        _factoryFeatures = jsonFactoryBuilder._factoryFeatures;
        _parserFeatures = jsonFactoryBuilder._streamReadFeatures;
        _generatorFeatures = jsonFactoryBuilder._streamWriteFeatures;
        _inputDecorator = jsonFactoryBuilder._inputDecorator;
        _outputDecorator = jsonFactoryBuilder._outputDecorator;
        _characterEscapes = jsonFactoryBuilder._characterEscapes;
        _rootValueSeparator = jsonFactoryBuilder._rootValueSeparator;
        _maximumNonEscapedChar = jsonFactoryBuilder._maximumNonEscapedChar;
        _quoteChar = jsonFactoryBuilder._quoteChar;
    }
    protected JsonFactory(final TSFBuilder<?, ?> tSFBuilder, final boolean z) {
        _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        _byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        _factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        _parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        _generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        _objectCodec = null;
        _factoryFeatures = tSFBuilder._factoryFeatures;
        _parserFeatures = tSFBuilder._streamReadFeatures;
        _generatorFeatures = tSFBuilder._streamWriteFeatures;
        _inputDecorator = tSFBuilder._inputDecorator;
        _outputDecorator = tSFBuilder._outputDecorator;
        _characterEscapes = null;
        _rootValueSeparator = null;
        _maximumNonEscapedChar = 0;
        _quoteChar = JsonFactory.DEFAULT_QUOTE_CHAR;
    }
    public TSFBuilder<?, ?> rebuild() {
        this._requireJSONFactory("Factory implementation for format (%s) MUST override `rebuild()` method");
        return new JsonFactoryBuilder(this);
    }
    public static TSFBuilder<?, ?> builder() {
        return new JsonFactoryBuilder();
    }
    public JsonFactory copy() {
        this._checkInvalidCopy(JsonFactory.class);
        return new JsonFactory(this, null);
    }
    protected void _checkInvalidCopy(final Class<?> cls) {
        if (this.getClass() == cls) {
            return;
        }
        throw new IllegalStateException("Failed copy(): " + this.getClass().getName() + " (version: " + this.version() + ") does not override copy(); it has to");
    }
    protected Object readResolve() {
        return new JsonFactory(this, _objectCodec);
    }
    public boolean canParseAsync() {
        return this._isJSONFactory();
    }
    public boolean canUseSchema(final FormatSchema formatSchema) {
        final String formatName;
        return null != formatSchema && null != (formatName = getFormatName()) && formatName.equals(formatSchema.getSchemaType());
    }
    public String getFormatName() {
        if (JsonFactory.class == getClass()) {
            return JsonFactory.FORMAT_NAME_JSON;
        }
        return null;
    }
    public MatchStrength hasFormat(final InputAccessor inputAccessor) throws IOException {
        if (JsonFactory.class == getClass()) {
            return this.hasJSONFormat(inputAccessor);
        }
        return null;
    }
    protected MatchStrength hasJSONFormat(final InputAccessor inputAccessor) throws IOException {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }
    public Version version() {
        return PackageVersion.VERSION;
    }
    public final JsonFactory configure(final Feature feature, final boolean z) {
        return z ? this.enable(feature) : this.disable(feature);
    }
    public JsonFactory enable(final Feature feature) {
        _factoryFeatures = feature.getMask() | _factoryFeatures;
        return this;
    }
    public JsonFactory disable(final Feature feature) {
        _factoryFeatures = (~feature.getMask()) & _factoryFeatures;
        return this;
    }
    public final boolean isEnabled(final Feature feature) {
        return 0 != (feature.getMask() & this._factoryFeatures);
    }
    public final int getParserFeatures() {
        return _parserFeatures;
    }
    public final int getGeneratorFeatures() {
        return _generatorFeatures;
    }
    public final JsonFactory configure(final JsonParser.Feature feature, final boolean z) {
        return z ? this.enable(feature) : this.disable(feature);
    }
    public JsonFactory enable(final JsonParser.Feature feature) {
        _parserFeatures = feature.getMask() | _parserFeatures;
        return this;
    }
    public JsonFactory disable(final JsonParser.Feature feature) {
        _parserFeatures = (~feature.getMask()) & _parserFeatures;
        return this;
    }
    public final boolean isEnabled(final JsonParser.Feature feature) {
        return 0 != (feature.getMask() & this._parserFeatures);
    }
    public final boolean isEnabled(final StreamReadFeature streamReadFeature) {
        return 0 != (streamReadFeature.mappedFeature().getMask() & this._parserFeatures);
    }
    public InputDecorator getInputDecorator() {
        return _inputDecorator;
    }
    public JsonFactory setInputDecorator(final InputDecorator inputDecorator) {
        _inputDecorator = inputDecorator;
        return this;
    }
    public final JsonFactory configure(final JsonGenerator.Feature feature, final boolean z) {
        return z ? this.enable(feature) : this.disable(feature);
    }
    public JsonFactory enable(final JsonGenerator.Feature feature) {
        _generatorFeatures = feature.getMask() | _generatorFeatures;
        return this;
    }
    public JsonFactory disable(final JsonGenerator.Feature feature) {
        _generatorFeatures = (~feature.getMask()) & _generatorFeatures;
        return this;
    }
    public final boolean isEnabled(final JsonGenerator.Feature feature) {
        return 0 != (feature.getMask() & this._generatorFeatures);
    }
    public final boolean isEnabled(final StreamWriteFeature streamWriteFeature) {
        return 0 != (streamWriteFeature.mappedFeature().getMask() & this._generatorFeatures);
    }
    public CharacterEscapes getCharacterEscapes() {
        return _characterEscapes;
    }
    public JsonFactory setCharacterEscapes(final CharacterEscapes characterEscapes) {
        _characterEscapes = characterEscapes;
        return this;
    }
    public OutputDecorator getOutputDecorator() {
        return _outputDecorator;
    }
    public JsonFactory setOutputDecorator(final OutputDecorator outputDecorator) {
        _outputDecorator = outputDecorator;
        return this;
    }
    public JsonFactory setRootValueSeparator(final String str) {
        _rootValueSeparator = null == str ? null : new SerializedString(str);
        return this;
    }
    public String getRootValueSeparator() {
        final SerializableString serializableString = _rootValueSeparator;
        if (null == serializableString) {
            return null;
        }
        return serializableString.getValue();
    }
    public JsonFactory setCodec(final ObjectCodec objectCodec) {
        _objectCodec = objectCodec;
        return this;
    }
    public ObjectCodec getCodec() {
        return _objectCodec;
    }
    public JsonParser createParser(final File file) throws IOException {
        final IOContext iOContext_createContext = this._createContext(file, true);
        return this._createParser(this._decorate(new FileInputStream(file), iOContext_createContext), iOContext_createContext);
    }
    public JsonParser createParser(final URL url) throws IOException {
        final IOContext iOContext_createContext = this._createContext(url, true);
        return this._createParser(this._decorate(this._optimizedStreamFromURL(url), iOContext_createContext), iOContext_createContext);
    }
    public JsonParser createParser(final InputStream inputStream) throws IOException {
        final IOContext iOContext_createContext = this._createContext(inputStream, false);
        return this._createParser(this._decorate(inputStream, iOContext_createContext), iOContext_createContext);
    }
    public JsonParser createParser(final Reader reader) throws IOException {
        final IOContext iOContext_createContext = this._createContext(reader, false);
        return this._createParser(this._decorate(reader, iOContext_createContext), iOContext_createContext);
    }
    public JsonParser createParser(final byte[] bArr) throws IOException {
        final InputStream inputStreamDecorate;
        final IOContext iOContext_createContext = this._createContext(bArr, true);
        final InputDecorator inputDecorator = _inputDecorator;
        if (null != inputDecorator && null != (inputStreamDecorate = inputDecorator.decorate(iOContext_createContext, bArr, 0, bArr.length))) {
            return this._createParser(inputStreamDecorate, iOContext_createContext);
        }
        return this._createParser(bArr, 0, bArr.length, iOContext_createContext);
    }
    public JsonParser createParser(final byte[] bArr, final int i2, final int i3) throws IOException {
        final InputStream inputStreamDecorate;
        final IOContext iOContext_createContext = this._createContext(bArr, true);
        final InputDecorator inputDecorator = _inputDecorator;
        if (null != inputDecorator && null != (inputStreamDecorate = inputDecorator.decorate(iOContext_createContext, bArr, i2, i3))) {
            return this._createParser(inputStreamDecorate, iOContext_createContext);
        }
        return this._createParser(bArr, i2, i3, iOContext_createContext);
    }
    public JsonParser createParser(final String str) throws IOException {
        final int length = str.length();
        if (null != this._inputDecorator || 32768 < length || !this.canUseCharArrays()) {
            return this.createParser(new StringReader(str));
        }
        final IOContext iOContext_createContext = this._createContext(str, true);
        final char[] cArrAllocTokenBuffer = iOContext_createContext.allocTokenBuffer(length);
        str.getChars(0, length, cArrAllocTokenBuffer, 0);
        return this._createParser(cArrAllocTokenBuffer, 0, length, iOContext_createContext, true);
    }
    public JsonParser createParser(final char[] cArr) throws IOException {
        return this.createParser(cArr, 0, cArr.length);
    }
    public JsonParser createParser(final char[] cArr, final int i2, final int i3) throws IOException {
        if (null != this._inputDecorator) {
            return this.createParser(new CharArrayReader(cArr, i2, i3));
        }
        return this._createParser(cArr, i2, i3, this._createContext(cArr, true), false);
    }
    public JsonParser createParser(final DataInput dataInput) throws IOException {
        final IOContext iOContext_createContext = this._createContext(dataInput, false);
        return this._createParser(this._decorate(dataInput, iOContext_createContext), iOContext_createContext);
    }
    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        this._requireJSONFactory("Non-blocking source not (yet?) supported for this format (%s)");
        return new NonBlockingJsonParser(this._createNonBlockingContext(null), _parserFeatures, _byteSymbolCanonicalizer.makeChild(_factoryFeatures));
    }
    public JsonGenerator createGenerator(final OutputStream outputStream, final JsonEncoding jsonEncoding) throws IOException {
        final IOContext iOContext_createContext = this._createContext(outputStream, false);
        iOContext_createContext.setEncoding(jsonEncoding);
        if (JsonEncoding.UTF8 == jsonEncoding) {
            return this._createUTF8Generator(this._decorate(outputStream, iOContext_createContext), iOContext_createContext);
        }
        return this._createGenerator(this._decorate(this._createWriter(outputStream, jsonEncoding, iOContext_createContext), iOContext_createContext), iOContext_createContext);
    }
    public JsonGenerator createGenerator(final OutputStream outputStream) throws IOException {
        return this.createGenerator(outputStream, JsonEncoding.UTF8);
    }
    public JsonGenerator createGenerator(final Writer writer) throws IOException {
        final IOContext iOContext_createContext = this._createContext(writer, false);
        return this._createGenerator(this._decorate(writer, iOContext_createContext), iOContext_createContext);
    }
    public JsonGenerator createGenerator(final File file, final JsonEncoding jsonEncoding) throws IOException {
        final OutputStream fileOutputStream = new FileOutputStream(file);
        final IOContext iOContext_createContext = this._createContext(fileOutputStream, true);
        iOContext_createContext.setEncoding(jsonEncoding);
        if (JsonEncoding.UTF8 == jsonEncoding) {
            return this._createUTF8Generator(this._decorate(fileOutputStream, iOContext_createContext), iOContext_createContext);
        }
        return this._createGenerator(this._decorate(this._createWriter(fileOutputStream, jsonEncoding, iOContext_createContext), iOContext_createContext), iOContext_createContext);
    }
    public JsonGenerator createGenerator(final DataOutput dataOutput, final JsonEncoding jsonEncoding) throws IOException {
        return this.createGenerator(this._createDataOutputWrapper(dataOutput), jsonEncoding);
    }
    public JsonGenerator createGenerator(final DataOutput dataOutput) throws IOException {
        return this.createGenerator(this._createDataOutputWrapper(dataOutput), JsonEncoding.UTF8);
    }
    public JsonParser createJsonParser(final File file) throws IOException {
        return this.createParser(file);
    }
    public JsonParser createJsonParser(final URL url) throws IOException {
        return this.createParser(url);
    }
    public JsonParser createJsonParser(final InputStream inputStream) throws IOException {
        return this.createParser(inputStream);
    }
    public JsonParser createJsonParser(final Reader reader) throws IOException {
        return this.createParser(reader);
    }
    public JsonParser createJsonParser(final byte[] bArr) throws IOException {
        return this.createParser(bArr);
    }
    public JsonParser createJsonParser(final byte[] bArr, final int i2, final int i3) throws IOException {
        return this.createParser(bArr, i2, i3);
    }
    public JsonParser createJsonParser(final String str) throws IOException {
        return this.createParser(str);
    }
    public JsonGenerator createJsonGenerator(final OutputStream outputStream, final JsonEncoding jsonEncoding) throws IOException {
        return this.createGenerator(outputStream, jsonEncoding);
    }
    public JsonGenerator createJsonGenerator(final Writer writer) throws IOException {
        return this.createGenerator(writer);
    }
    public JsonGenerator createJsonGenerator(final OutputStream outputStream) throws IOException {
        return this.createGenerator(outputStream, JsonEncoding.UTF8);
    }
    protected JsonParser _createParser(final InputStream inputStream, final IOContext iOContext) throws IOException {
        return new ByteSourceJsonBootstrapper(iOContext, inputStream).constructParser(_parserFeatures, _objectCodec, _byteSymbolCanonicalizer, _rootCharSymbols, _factoryFeatures);
    }
    protected JsonParser _createParser(final Reader reader, final IOContext iOContext) throws IOException {
        return new ReaderBasedJsonParser(iOContext, _parserFeatures, reader, _objectCodec, _rootCharSymbols.makeChild(_factoryFeatures));
    }
    protected JsonParser _createParser(final char[] cArr, final int i2, final int i3, final IOContext iOContext, final boolean z) throws IOException {
        return new ReaderBasedJsonParser(iOContext, _parserFeatures, null, _objectCodec, _rootCharSymbols.makeChild(_factoryFeatures), cArr, i2, i2 + i3, z);
    }
    protected JsonParser _createParser(final byte[] bArr, final int i2, final int i3, final IOContext iOContext) throws IOException {
        return new ByteSourceJsonBootstrapper(iOContext, bArr, i2, i3).constructParser(_parserFeatures, _objectCodec, _byteSymbolCanonicalizer, _rootCharSymbols, _factoryFeatures);
    }
    protected JsonParser _createParser(final DataInput dataInput, final IOContext iOContext) throws IOException {
        this._requireJSONFactory("InputData source not (yet?) supported for this format (%s)");
        final int iSkipUTF8BOM = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        return new UTF8DataInputJsonParser(iOContext, _parserFeatures, dataInput, _objectCodec, _byteSymbolCanonicalizer.makeChild(_factoryFeatures), iSkipUTF8BOM);
    }
    protected JsonGenerator _createGenerator(final Writer writer, final IOContext iOContext) throws IOException {
        final WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(iOContext, _generatorFeatures, _objectCodec, writer, _quoteChar);
        final int i2 = _maximumNonEscapedChar;
        if (0 < i2) {
            writerBasedJsonGenerator.setHighestNonEscapedChar(i2);
        }
        final CharacterEscapes characterEscapes = _characterEscapes;
        if (null != characterEscapes) {
            writerBasedJsonGenerator.setCharacterEscapes(characterEscapes);
        }
        final SerializableString serializableString = _rootValueSeparator;
        if (serializableString != JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator;
    }
    protected JsonGenerator _createUTF8Generator(final OutputStream outputStream, final IOContext iOContext) throws IOException {
        final UTF8JsonGenerator uTF8JsonGenerator = new UTF8JsonGenerator(iOContext, _generatorFeatures, _objectCodec, outputStream, _quoteChar);
        final int i2 = _maximumNonEscapedChar;
        if (0 < i2) {
            uTF8JsonGenerator.setHighestNonEscapedChar(i2);
        }
        final CharacterEscapes characterEscapes = _characterEscapes;
        if (null != characterEscapes) {
            uTF8JsonGenerator.setCharacterEscapes(characterEscapes);
        }
        final SerializableString serializableString = _rootValueSeparator;
        if (serializableString != JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR) {
            uTF8JsonGenerator.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator;
    }
    protected Writer _createWriter(final OutputStream outputStream, final JsonEncoding jsonEncoding, final IOContext iOContext) throws IOException {
        if (JsonEncoding.UTF8 == jsonEncoding) {
            return new UTF8Writer(iOContext, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }
    protected final InputStream _decorate(final InputStream inputStream, final IOContext iOContext) throws IOException {
        final InputStream inputStreamDecorate;
        final InputDecorator inputDecorator = _inputDecorator;
        return (null == inputDecorator || null == (inputStreamDecorate = inputDecorator.decorate(iOContext, inputStream))) ? inputStream : inputStreamDecorate;
    }
    protected final Reader _decorate(final Reader reader, final IOContext iOContext) throws IOException {
        final Reader readerDecorate;
        final InputDecorator inputDecorator = _inputDecorator;
        return (null == inputDecorator || null == (readerDecorate = inputDecorator.decorate(iOContext, reader))) ? reader : readerDecorate;
    }
    protected final DataInput _decorate(final DataInput dataInput, final IOContext iOContext) throws IOException {
        final DataInput dataInputDecorate;
        final InputDecorator inputDecorator = _inputDecorator;
        return (null == inputDecorator || null == (dataInputDecorate = inputDecorator.decorate(iOContext, dataInput))) ? dataInput : dataInputDecorate;
    }
    protected final OutputStream _decorate(final OutputStream outputStream, final IOContext iOContext) throws IOException {
        final OutputStream outputStreamDecorate;
        final OutputDecorator outputDecorator = _outputDecorator;
        return (null == outputDecorator || null == (outputStreamDecorate = outputDecorator.decorate(iOContext, outputStream))) ? outputStream : outputStreamDecorate;
    }
    protected final Writer _decorate(final Writer writer, final IOContext iOContext) throws IOException {
        final Writer writerDecorate;
        final OutputDecorator outputDecorator = _outputDecorator;
        return (null == outputDecorator || null == (writerDecorate = outputDecorator.decorate(iOContext, writer))) ? writer : writerDecorate;
    }
    public BufferRecycler _getBufferRecycler() {
        if (Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.enabledIn(_factoryFeatures)) {
            return BufferRecyclers.getBufferRecycler();
        }
        return new BufferRecycler();
    }
    protected IOContext _createContext(final Object obj, final boolean z) {
        return new IOContext(this._getBufferRecycler(), obj, z);
    }
    protected IOContext _createNonBlockingContext(final Object obj) {
        return new IOContext(this._getBufferRecycler(), obj, false);
    }
    private final void _requireJSONFactory(final String str) {
        if (!this._isJSONFactory()) {
            throw new UnsupportedOperationException(String.format(str, this.getFormatName()));
        }
    }
    private final boolean _isJSONFactory() {
        return JsonFactory.FORMAT_NAME_JSON == getFormatName();
    }
}
