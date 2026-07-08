package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;
 
public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_GENERATOR_FEATURES = JsonGenerator.Feature.collectDefaults();
    protected int _appendAt;
    protected boolean _closed;
    protected Segment _first;
    protected boolean _forceBigDecimal;
    protected int _generatorFeatures;
    protected boolean _hasNativeId;
    protected boolean _hasNativeObjectIds;
    protected boolean _hasNativeTypeIds;
    protected Segment _last;
    protected boolean _mayHaveNativeIds;
    protected ObjectCodec _objectCodec;
    protected Object _objectId;
    protected JsonStreamContext _parentContext;
    protected Object _typeId;
    protected JsonWriteContext _writeContext;
    public boolean canWriteBinaryNatively() {
        return true;
    }
    public void flush() throws IOException {
    }
    public TokenBuffer(final ObjectCodec objectCodec, final boolean z) {
        _hasNativeId = false;
        _objectCodec = objectCodec;
        _generatorFeatures = TokenBuffer.DEFAULT_GENERATOR_FEATURES;
        _writeContext = JsonWriteContext.createRootContext(null);
        final Segment segment = new Segment();
        _last = segment;
        _first = segment;
        _appendAt = 0;
        _hasNativeTypeIds = z;
        _hasNativeObjectIds = z;
        _mayHaveNativeIds = z || z;
    }
    public TokenBuffer(final JsonParser jsonParser) {
        this(jsonParser, null);
    }
    public TokenBuffer(final JsonParser jsonParser, final DeserializationContext deserializationContext) {
        _hasNativeId = false;
        _objectCodec = jsonParser.getCodec();
        _parentContext = jsonParser.getParsingContext();
        _generatorFeatures = TokenBuffer.DEFAULT_GENERATOR_FEATURES;
        _writeContext = JsonWriteContext.createRootContext(null);
        final Segment segment = new Segment();
        _last = segment;
        _first = segment;
        _appendAt = 0;
        _hasNativeTypeIds = jsonParser.canReadTypeId();
        final boolean zCanReadObjectId = jsonParser.canReadObjectId();
        _hasNativeObjectIds = zCanReadObjectId;
        _mayHaveNativeIds = _hasNativeTypeIds || zCanReadObjectId;
        _forceBigDecimal = null != deserializationContext && deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }
    public static TokenBuffer asCopyOfValue(final JsonParser jsonParser) throws IOException {
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.copyCurrentStructure(jsonParser);
        return tokenBuffer;
    }
    public TokenBuffer forceUseOfBigDecimal(final boolean z) {
        _forceBigDecimal = z;
        return this;
    }
    public JsonParser asParser() {
        return this.asParser(_objectCodec);
    }
    public JsonParser asParserOnFirstToken() throws IOException {
        final JsonParser jsonParserAsParser = this.asParser(_objectCodec);
        jsonParserAsParser.nextToken();
        return jsonParserAsParser;
    }
    public JsonParser asParser(final ObjectCodec objectCodec) {
        return new Parser(_first, objectCodec, _hasNativeTypeIds, _hasNativeObjectIds, _parentContext);
    }
    public JsonParser asParser(final JsonParser jsonParser) {
        final Parser parser = new Parser(_first, jsonParser.getCodec(), _hasNativeTypeIds, _hasNativeObjectIds, _parentContext);
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }
    public JsonToken firstToken() {
        return _first.type(0);
    }
    public TokenBuffer append(final TokenBuffer tokenBuffer) throws IOException {
        if (!_hasNativeTypeIds) {
            _hasNativeTypeIds = tokenBuffer.canWriteTypeId();
        }
        if (!_hasNativeObjectIds) {
            _hasNativeObjectIds = tokenBuffer.canWriteObjectId();
        }
        _mayHaveNativeIds = _hasNativeTypeIds || _hasNativeObjectIds;
        final JsonParser jsonParserAsParser = tokenBuffer.asParser();
        while (null != jsonParserAsParser.nextToken()) {
            this.copyCurrentStructure(jsonParserAsParser);
        }
        return this;
    }
    public void serialize(final JsonGenerator jsonGenerator) throws IOException {
        Segment next = _first;
        final boolean z = _mayHaveNativeIds;
        boolean z2 = z && next.hasIds();
        int i2 = -1;
        while (true) {
            i2++;
            if (16 <= i2) {
                next = next.next();
                if (null == next) {
                    return;
                }
                z2 = z && next.hasIds();
                i2 = 0;
            }
            final JsonToken jsonTokenType = next.type(i2);
            if (null == jsonTokenType) {
                return;
            }
            if (z2) {
                final Object objFindObjectId = next.findObjectId(i2);
                if (null != objFindObjectId) {
                    jsonGenerator.writeObjectId(objFindObjectId);
                }
                final Object objFindTypeId = next.findTypeId(i2);
                if (null != objFindTypeId) {
                    jsonGenerator.writeTypeId(objFindTypeId);
                }
            }
            switch (C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonTokenType.ordinal()]) {
                case 1:
                    jsonGenerator.writeStartObject();
                    break;
                case 2:
                    jsonGenerator.writeEndObject();
                    break;
                case 3:
                    jsonGenerator.writeStartArray();
                    break;
                case 4:
                    jsonGenerator.writeEndArray();
                    break;
                case 5:
                    final Object obj = next.get(i2);
                    if (obj instanceof SerializableString) {
                        jsonGenerator.writeFieldName((SerializableString) obj);
                        break;
                    } else {
                        jsonGenerator.writeFieldName((String) obj);
                        break;
                    }
                case 6:
                    final Object obj2 = next.get(i2);
                    if (obj2 instanceof SerializableString) {
                        jsonGenerator.writeString((SerializableString) obj2);
                        break;
                    } else {
                        jsonGenerator.writeString((String) obj2);
                        break;
                    }
                case 7:
                    final Object obj3 = next.get(i2);
                    if (obj3 instanceof Integer) {
                        jsonGenerator.writeNumber(((Integer) obj3).intValue());
                        break;
                    } else if (obj3 instanceof BigInteger) {
                        jsonGenerator.writeNumber((BigInteger) obj3);
                        break;
                    } else if (obj3 instanceof Long) {
                        jsonGenerator.writeNumber(((Long) obj3).longValue());
                        break;
                    } else if (obj3 instanceof Short) {
                        jsonGenerator.writeNumber(((Short) obj3).shortValue());
                        break;
                    } else {
                        jsonGenerator.writeNumber(((Number) obj3).intValue());
                        break;
                    }
                case 8:
                    final Object obj4 = next.get(i2);
                    if (obj4 instanceof Double) {
                        jsonGenerator.writeNumber(((Double) obj4).doubleValue());
                        break;
                    } else if (obj4 instanceof BigDecimal) {
                        jsonGenerator.writeNumber((BigDecimal) obj4);
                        break;
                    } else if (obj4 instanceof Float) {
                        jsonGenerator.writeNumber(((Float) obj4).floatValue());
                        break;
                    } else if (null == obj4) {
                        jsonGenerator.writeNull();
                        break;
                    } else if (obj4 instanceof String) {
                        jsonGenerator.writeNumber((String) obj4);
                        break;
                    } else {
                        throw new JsonGenerationException(String.format("Unrecognized value type for VALUE_NUMBER_FLOAT: %s, cannot serialize", obj4.getClass().getName()), jsonGenerator);
                    }
                case 9:
                    jsonGenerator.writeBoolean(true);
                    break;
                case 10:
                    jsonGenerator.writeBoolean(false);
                    break;
                case 11:
                    jsonGenerator.writeNull();
                    break;
                case 12:
                    final Object obj5 = next.get(i2);
                    if (obj5 instanceof RawValue) {
                        ((RawValue) obj5).serialize(jsonGenerator);
                        break;
                    } else if (obj5 instanceof JsonSerializable) {
                        jsonGenerator.writeObject(obj5);
                        break;
                    } else {
                        jsonGenerator.writeEmbeddedObject(obj5);
                        break;
                    }
                default:
                    throw new RuntimeException("Internal error: should never end up through this code path");
            }
        }
    }
    public TokenBuffer deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        JsonToken jsonTokenNextToken;
        if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
            this.copyCurrentStructure(jsonParser);
            return this;
        }
        this.writeStartObject();
        do {
            this.copyCurrentStructure(jsonParser);
            jsonTokenNextToken = jsonParser.nextToken();
        } while (JsonToken.FIELD_NAME == jsonTokenNextToken);
        final JsonToken jsonToken = JsonToken.END_OBJECT;
        if (jsonTokenNextToken != jsonToken) {
            deserializationContext.reportWrongTokenException(TokenBuffer.class, jsonToken, "Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + jsonTokenNextToken);
        }
        this.writeEndObject();
        return this;
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        final JsonParser jsonParserAsParser = this.asParser();
        int i2 = 0;
        final boolean z = _hasNativeTypeIds || _hasNativeObjectIds;
        while (true) {
            try {
                final JsonToken jsonTokenNextToken = jsonParserAsParser.nextToken();
                if (null == jsonTokenNextToken) {
                    break;
                }
                if (z) {
                    this._appendNativeIds(sb);
                }
                if (100 > i2) {
                    if (0 < i2) {
                        sb.append(", ");
                    }
                    sb.append(jsonTokenNextToken);
                    if (JsonToken.FIELD_NAME == jsonTokenNextToken) {
                        sb.append('(');
                        sb.append(jsonParserAsParser.currentName());
                        sb.append(')');
                    }
                }
                i2++;
            } catch (final IOException e2) {
                throw new IllegalStateException(e2);
            }
        }
        if (100 <= i2) {
            sb.append(" ... (truncated ");
            sb.append(i2 - 100);
            sb.append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }
    private final void _appendNativeIds(final StringBuilder sb) {
        final Object objFindObjectId = _last.findObjectId(_appendAt - 1);
        if (null != objFindObjectId) {
            sb.append("[objectId=");
            sb.append(objFindObjectId);
            sb.append(']');
        }
        final Object objFindTypeId = _last.findTypeId(_appendAt - 1);
        if (null != objFindTypeId) {
            sb.append("[typeId=");
            sb.append(objFindTypeId);
            sb.append(']');
        }
    }
    public JsonGenerator disable(final JsonGenerator.Feature feature) {
        _generatorFeatures = (~feature.getMask()) & _generatorFeatures;
        return this;
    }
    public boolean isEnabled(final JsonGenerator.Feature feature) {
        return 0 != (feature.getMask() & this._generatorFeatures);
    }
    public int getFeatureMask() {
        return _generatorFeatures;
    }
    public JsonGenerator setFeatureMask(final int i2) {
        _generatorFeatures = i2;
        return this;
    }
    public JsonGenerator overrideStdFeatures(final int i2, final int i3) {
        _generatorFeatures = (i2 & i3) | (this._generatorFeatures & (~i3));
        return this;
    }
    public final JsonWriteContext getOutputContext() {
        return _writeContext;
    }
    public void close() throws IOException {
        _closed = true;
    }
    public final void writeStartArray() throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_ARRAY);
        _writeContext = _writeContext.createChildArrayContext();
    }
    public void writeStartArray(final Object obj) throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_ARRAY);
        _writeContext = _writeContext.createChildArrayContext(obj);
    }
    public void writeStartArray(final Object obj, final int i2) throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_ARRAY);
        _writeContext = _writeContext.createChildArrayContext(obj);
    }
    public final void writeEndArray() throws IOException {
        this._appendEndMarker(JsonToken.END_ARRAY);
        final JsonWriteContext parent = _writeContext.getParent();
        if (null != parent) {
            _writeContext = parent;
        }
    }
    public final void writeStartObject() throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_OBJECT);
        _writeContext = _writeContext.createChildObjectContext();
    }
    public void writeStartObject(final Object obj) throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_OBJECT);
        _writeContext = _writeContext.createChildObjectContext(obj);
    }
    public void writeStartObject(final Object obj, final int i2) throws IOException {
        _writeContext.writeValue();
        this._appendStartMarker(JsonToken.START_OBJECT);
        _writeContext = _writeContext.createChildObjectContext(obj);
    }
    public final void writeEndObject() throws IOException {
        this._appendEndMarker(JsonToken.END_OBJECT);
        final JsonWriteContext parent = _writeContext.getParent();
        if (null != parent) {
            _writeContext = parent;
        }
    }
    public final void writeFieldName(final String str) throws IOException {
        _writeContext.writeFieldName(str);
        this._appendFieldName(str);
    }
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        _writeContext.writeFieldName(serializableString.getValue());
        this._appendFieldName(serializableString);
    }
    public void writeString(final String str) throws IOException {
        if (null == str) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_STRING, str);
        }
    }
    public void writeString(final char[] cArr, final int i2, final int i3) throws IOException {
        this.writeString(new String(cArr, i2, i3));
    }
    public void writeString(final SerializableString serializableString) throws IOException {
        if (null == serializableString) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_STRING, serializableString);
        }
    }
    public void writeRaw(final String str) throws IOException {
        this._reportUnsupportedOperation();
    }
    public void writeRaw(final SerializableString serializableString) throws IOException {
        this._reportUnsupportedOperation();
    }
    public void writeRaw(final char[] cArr, final int i2, final int i3) throws IOException {
        this._reportUnsupportedOperation();
    }
    public void writeRaw(final char c2) throws IOException {
        this._reportUnsupportedOperation();
    }
    public void writeRawValue(final String str) throws IOException {
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(str));
    }
    public void writeNumber(final short s) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, Short.valueOf(s));
    }
    public void writeNumber(final int i2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i2));
    }
    public void writeNumber(final long j2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j2));
    }
    public void writeNumber(final double d2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d2));
    }
    public void writeNumber(final float f2) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f2));
    }
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        if (null == bigDecimal) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        if (null == bigInteger) {
            this.writeNull();
        } else {
            this._appendValue(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }
    public void writeNumber(final String str) throws IOException {
        this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, str);
    }
    public void writeBoolean(final boolean z) throws IOException {
        this._appendValue(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }
    public void writeNull() throws IOException {
        this._appendValue(JsonToken.VALUE_NULL);
    }
    public void writeObject(final Object obj) throws IOException {
        if (null == obj) {
            this.writeNull();
            return;
        }
        if (byte[].class == obj.getClass() || (obj instanceof RawValue)) {
            this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
            return;
        }
        final ObjectCodec objectCodec = _objectCodec;
        if (null == objectCodec) {
            this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
        } else {
            objectCodec.writeValue(this, obj);
        }
    }
    public void writeBinary(final Base64Variant base64Variant, final byte[] bArr, final int i2, final int i3) throws IOException {
        final byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        this.writeObject(bArr2);
    }
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final int i2) {
        throw new UnsupportedOperationException();
    }
    public boolean canWriteTypeId() {
        return _hasNativeTypeIds;
    }
    public boolean canWriteObjectId() {
        return _hasNativeObjectIds;
    }
    public void writeTypeId(final Object obj) {
        _typeId = obj;
        _hasNativeId = true;
    }
    public void writeObjectId(final Object obj) {
        _objectId = obj;
        _hasNativeId = true;
    }
    public void writeEmbeddedObject(final Object obj) throws IOException {
        this._appendValue(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }
    enum C12271 {
        ;
        static final int[] SwitchMapcomfasterxmljacksoncoreJsonParserNumberType;
        static final int[] SwitchMapcomfasterxmljacksoncoreJsonToken;

        static {
            final int[] iArr = new int[JsonParser.NumberType.values().length];
            SwitchMapcomfasterxmljacksoncoreJsonParserNumberType = iArr;
            try {
                iArr[JsonParser.NumberType.INT.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[JsonParser.NumberType.BIG_INTEGER.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[JsonParser.NumberType.BIG_DECIMAL.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[JsonParser.NumberType.FLOAT.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[JsonParser.NumberType.LONG.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
            final int[] iArr2 = new int[JsonToken.values().length];
            SwitchMapcomfasterxmljacksoncoreJsonToken = iArr2;
            try {
                iArr2[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (final NoSuchFieldError unused7) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (final NoSuchFieldError unused8) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (final NoSuchFieldError unused9) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (final NoSuchFieldError unused10) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (final NoSuchFieldError unused11) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (final NoSuchFieldError unused12) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (final NoSuchFieldError unused13) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (final NoSuchFieldError unused14) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (final NoSuchFieldError unused15) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (final NoSuchFieldError unused16) {
            }
            try {
                C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
            } catch (final NoSuchFieldError unused17) {
            }
        }
    }
    public void copyCurrentStructure(final JsonParser jsonParser) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            if (_mayHaveNativeIds) {
                this._checkNativeIds(jsonParser);
            }
            this.writeFieldName(jsonParser.currentName());
            jsonTokenCurrentToken = jsonParser.nextToken();
        } else if (null == jsonTokenCurrentToken) {
            throw new IllegalStateException("No token available from argument `JsonParser`");
        }
        final int i2 = C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonTokenCurrentToken.ordinal()];
        if (1 == i2) {
            if (_mayHaveNativeIds) {
                this._checkNativeIds(jsonParser);
            }
            this.writeStartObject();
            this._copyBufferContents(jsonParser);
            return;
        }
        if (2 == i2) {
            this.writeEndObject();
            return;
        }
        if (3 != i2) {
            if (4 == i2) {
                this.writeEndArray();
                return;
            } else {
                this._copyBufferValue(jsonParser, jsonTokenCurrentToken);
                return;
            }
        }
        if (_mayHaveNativeIds) {
            this._checkNativeIds(jsonParser);
        }
        this.writeStartArray();
        this._copyBufferContents(jsonParser);
    }
    protected void _copyBufferContents(final JsonParser jsonParser) throws IOException {
        int i2 = 1;
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == jsonTokenNextToken) {
                return;
            }
            final int i3 = C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonTokenNextToken.ordinal()];
            if (1 == i3) {
                if (_mayHaveNativeIds) {
                    this._checkNativeIds(jsonParser);
                }
                this.writeStartObject();
            } else if (2 == i3) {
                this.writeEndObject();
                i2--;
                if (0 == i2) {
                    return;
                }
            } else if (3 == i3) {
                if (_mayHaveNativeIds) {
                    this._checkNativeIds(jsonParser);
                }
                this.writeStartArray();
            } else if (4 == i3) {
                this.writeEndArray();
                i2--;
                if (0 == i2) {
                    return;
                }
            } else if (5 == i3) {
                if (_mayHaveNativeIds) {
                    this._checkNativeIds(jsonParser);
                }
                this.writeFieldName(jsonParser.currentName());
            } else {
                this._copyBufferValue(jsonParser, jsonTokenNextToken);
            }
            i2++;
        }
    }
    private void _copyBufferValue(final JsonParser jsonParser, final JsonToken jsonToken) throws IOException {
        if (_mayHaveNativeIds) {
            this._checkNativeIds(jsonParser);
        }
        switch (C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonToken.ordinal()]) {
            case 6:
                if (jsonParser.hasTextCharacters()) {
                    this.writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    this.writeString(jsonParser.getText());
                    return;
                }
            case 7:
                final int i2 = C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[jsonParser.getNumberType().ordinal()];
                if (1 == i2) {
                    this.writeNumber(jsonParser.getIntValue());
                    return;
                } else if (2 == i2) {
                    this.writeNumber(jsonParser.getBigIntegerValue());
                    return;
                } else {
                    this.writeNumber(jsonParser.getLongValue());
                    return;
                }
            case 8:
                if (_forceBigDecimal) {
                    this.writeNumber(jsonParser.getDecimalValue());
                    return;
                } else {
                    this._appendValue(JsonToken.VALUE_NUMBER_FLOAT, jsonParser.getNumberValueExact());
                    return;
                }
            case 9:
                this.writeBoolean(true);
                return;
            case 10:
                this.writeBoolean(false);
                return;
            case 11:
                this.writeNull();
                return;
            case 12:
                this.writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                throw new RuntimeException("Internal error: unexpected token: " + jsonToken);
        }
    }
    private final void _checkNativeIds(final JsonParser jsonParser) throws IOException {
        final Object typeId = jsonParser.getTypeId();
        _typeId = typeId;
        if (null != typeId) {
            _hasNativeId = true;
        }
        final Object objectId = jsonParser.getObjectId();
        _objectId = objectId;
        if (null != objectId) {
            _hasNativeId = true;
        }
    }
    protected final void _appendValue(final JsonToken jsonToken) {
        final Segment segmentAppend;
        _writeContext.writeValue();
        if (_hasNativeId) {
            segmentAppend = _last.append(_appendAt, jsonToken, _objectId, _typeId);
        } else {
            segmentAppend = _last.append(_appendAt, jsonToken);
        }
        if (null == segmentAppend) {
            _appendAt++;
        } else {
            _last = segmentAppend;
            _appendAt = 1;
        }
    }
    protected final void _appendValue(final JsonToken jsonToken, final Object obj) {
        final Segment segmentAppend;
        _writeContext.writeValue();
        if (_hasNativeId) {
            segmentAppend = _last.append(_appendAt, jsonToken, obj, _objectId, _typeId);
        } else {
            segmentAppend = _last.append(_appendAt, jsonToken, obj);
        }
        if (null == segmentAppend) {
            _appendAt++;
        } else {
            _last = segmentAppend;
            _appendAt = 1;
        }
    }
    protected final void _appendFieldName(final Object obj) {
        final Segment segmentAppend;
        if (_hasNativeId) {
            segmentAppend = _last.append(_appendAt, JsonToken.FIELD_NAME, obj, _objectId, _typeId);
        } else {
            segmentAppend = _last.append(_appendAt, JsonToken.FIELD_NAME, obj);
        }
        if (null == segmentAppend) {
            _appendAt++;
        } else {
            _last = segmentAppend;
            _appendAt = 1;
        }
    }
    protected final void _appendStartMarker(final JsonToken jsonToken) {
        final Segment segmentAppend;
        if (_hasNativeId) {
            segmentAppend = _last.append(_appendAt, jsonToken, _objectId, _typeId);
        } else {
            segmentAppend = _last.append(_appendAt, jsonToken);
        }
        if (null == segmentAppend) {
            _appendAt++;
        } else {
            _last = segmentAppend;
            _appendAt = 1;
        }
    }
    protected final void _appendEndMarker(final JsonToken jsonToken) {
        final Segment segmentAppend = _last.append(_appendAt, jsonToken);
        if (null == segmentAppend) {
            _appendAt++;
        } else {
            _last = segmentAppend;
            _appendAt = 1;
        }
    }
    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }
    protected static final class Parser extends ParserMinimalBase {
        protected ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected final boolean _hasNativeIds;
        protected final boolean _hasNativeObjectIds;
        protected final boolean _hasNativeTypeIds;
        protected JsonLocation _location;
        protected TokenBufferReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr; 
        public int getTextOffset() {
            return 0;
        }
 
        public boolean hasTextCharacters() {
            return false;
        }

        public Parser(final Segment segment, final ObjectCodec objectCodec, final boolean z, final boolean z2, final JsonStreamContext jsonStreamContext) {
            super(0);
            _location = null;
            _segment = segment;
            _segmentPtr = -1;
            _codec = objectCodec;
            _parsingContext = TokenBufferReadContext.createRootContext(jsonStreamContext);
            _hasNativeTypeIds = z;
            _hasNativeObjectIds = z2;
            _hasNativeIds = z || z2;
        }

        public void setLocation(final JsonLocation jsonLocation) {
            _location = jsonLocation;
        }
 
        public ObjectCodec getCodec() {
            return _codec;
        }
 
        public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
            return DEFAULT_READ_CAPABILITIES;
        }

        public void close() throws IOException {
            if (_closed) {
                return;
            }
            _closed = true;
        }
        public JsonToken nextToken() throws IOException {
            final Segment segment;
            if (_closed || null == (segment = this._segment)) {
                return null;
            }
            final int i2 = _segmentPtr + 1;
            _segmentPtr = i2;
            if (16 <= i2) {
                _segmentPtr = 0;
                final Segment next = segment.next();
                _segment = next;
                if (null == next) {
                    return null;
                }
            }
            final JsonToken jsonTokenType = _segment.type(_segmentPtr);
            _currToken = jsonTokenType;
            if (JsonToken.FIELD_NAME == jsonTokenType) {
                final Object obj_currentObject = this._currentObject();
                _parsingContext.setCurrentName(obj_currentObject instanceof String ? (String) obj_currentObject : obj_currentObject.toString());
            } else if (JsonToken.START_OBJECT == jsonTokenType) {
                _parsingContext = _parsingContext.createChildObjectContext();
            } else if (JsonToken.START_ARRAY == jsonTokenType) {
                _parsingContext = _parsingContext.createChildArrayContext();
            } else if (JsonToken.END_OBJECT == jsonTokenType || JsonToken.END_ARRAY == jsonTokenType) {
                _parsingContext = _parsingContext.parentOrCopy();
            } else {
                _parsingContext.updateForValue();
            }
            return _currToken;
        }

        
        public String nextFieldName() throws IOException {
            final Segment segment;
            if (_closed || null == (segment = this._segment)) {
                return null;
            }
            final int i2 = _segmentPtr + 1;
            if (16 > i2) {
                final JsonToken jsonTokenType = segment.type(i2);
                final JsonToken jsonToken = JsonToken.FIELD_NAME;
                if (jsonTokenType == jsonToken) {
                    _segmentPtr = i2;
                    _currToken = jsonToken;
                    final Object obj = _segment.get(i2);
                    final String string = obj instanceof String ? (String) obj : obj.toString();
                    _parsingContext.setCurrentName(string);
                    return string;
                }
            }
            if (JsonToken.FIELD_NAME == nextToken()) {
                return this.currentName();
            }
            return null;
        }

        
        public JsonStreamContext getParsingContext() {
            return _parsingContext;
        }

        
        public JsonLocation getTokenLocation() {
            return this.getCurrentLocation();
        }

        
        public JsonLocation getCurrentLocation() {
            final JsonLocation jsonLocation = _location;
            return null == jsonLocation ? JsonLocation.f792NA : jsonLocation;
        }

        
        public String currentName() {
            final JsonToken jsonToken = _currToken;
            if (JsonToken.START_OBJECT == jsonToken || JsonToken.START_ARRAY == jsonToken) {
                return _parsingContext.getParent().getCurrentName();
            }
            return _parsingContext.getCurrentName();
        }

        @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
        public String getCurrentName() {
            return this.currentName();
        }

        @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
        public String getText() {
            final JsonToken jsonToken = _currToken;
            if (JsonToken.VALUE_STRING == jsonToken || JsonToken.FIELD_NAME == jsonToken) {
                final Object obj_currentObject = this._currentObject();
                if (obj_currentObject instanceof String) {
                    return (String) obj_currentObject;
                }
                return ClassUtil.nullOrToString(obj_currentObject);
            }
            if (null == jsonToken) {
                return null;
            }
            final int i2 = C12271.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonToken.ordinal()];
            if (7 == i2 || 8 == i2) {
                return ClassUtil.nullOrToString(this._currentObject());
            }
            return _currToken.asString();
        }

        
        public char[] getTextCharacters() {
            final String text = this.getText();
            if (null == text) {
                return null;
            }
            return text.toCharArray();
        }

        
        public int getTextLength() {
            final String text = this.getText();
            if (null == text) {
                return 0;
            }
            return text.length();
        }
 
        public boolean isNaN() {
            if (JsonToken.VALUE_NUMBER_FLOAT != this._currToken) {
                return false;
            }
            final Object obj_currentObject = this._currentObject();
            if (obj_currentObject instanceof Double d2) {
                return d2.isNaN() || d2.isInfinite();
            }
            if (!(obj_currentObject instanceof Float f2)) return false;
            return f2.isNaN() || f2.isInfinite();
        }
 
        public BigInteger getBigIntegerValue() throws IOException {
            final Number numberValue = this.getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (NumberType.BIG_DECIMAL == getNumberType()) {
                return ((BigDecimal) numberValue).toBigInteger();
            }
            return BigInteger.valueOf(numberValue.longValue());
        }
 
        public BigDecimal getDecimalValue() throws IOException {
            final Number numberValue = this.getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            final int i2 = C12271.SwitchMapcomfasterxmljacksoncoreJsonParserNumberType[this.getNumberType().ordinal()];
            if (1 != i2) {
                if (2 == i2) {
                    return new BigDecimal((BigInteger) numberValue);
                }
                if (5 != i2) {
                    return BigDecimal.valueOf(numberValue.doubleValue());
                }
            }
            return BigDecimal.valueOf(numberValue.longValue());
        }

        
        public double getDoubleValue() throws IOException {
            return this.getNumberValue().doubleValue();
        }

        
        public float getFloatValue() throws IOException {
            return this.getNumberValue().floatValue();
        }

        
        public int getIntValue() throws IOException {
            final Number numberValue = JsonToken.VALUE_NUMBER_INT == this._currToken ? (Number) this._currentObject() : this.getNumberValue();
            if ((numberValue instanceof Integer) || this._smallerThanInt(numberValue)) {
                return numberValue.intValue();
            }
            return this._convertNumberToInt(numberValue);
        }

        
        public long getLongValue() throws IOException {
            final Number numberValue = JsonToken.VALUE_NUMBER_INT == this._currToken ? (Number) this._currentObject() : this.getNumberValue();
            if ((numberValue instanceof Long) || this._smallerThanLong(numberValue)) {
                return numberValue.longValue();
            }
            return this._convertNumberToLong(numberValue);
        }

        
        public JsonParser.NumberType getNumberType() throws IOException {
            final Number numberValue = this.getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (numberValue instanceof Short) {
                return JsonParser.NumberType.INT;
            }
            return null;
        }

        
        public Number getNumberValue() throws IOException {
            this._checkIsNumber();
            final Object obj_currentObject = this._currentObject();
            if (obj_currentObject instanceof Number) {
                return (Number) obj_currentObject;
            }
            if (obj_currentObject instanceof String str) {
                if (0 <= str.indexOf(46)) {
                    return Double.valueOf(Double.parseDouble(str));
                }
                return Long.valueOf(Long.parseLong(str));
            }
            if (null == obj_currentObject) {
                return null;
            }
            throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + obj_currentObject.getClass().getName());
        }

        private boolean _smallerThanInt(final Number number) {
            return (number instanceof Short) || (number instanceof Byte);
        }

        private boolean _smallerThanLong(final Number number) {
            return (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
        }

        protected int _convertNumberToInt(final Number number) throws IOException {
            if (number instanceof Long) {
                final long jLongValue = number.longValue();
                final int i2 = (int) jLongValue;
                if (i2 != jLongValue) {
                    this.reportOverflowInt();
                }
                return i2;
            }
            if (number instanceof BigInteger bigInteger) {
                if (0 < ParserMinimalBase.BI_MIN_INT.compareTo(bigInteger) || 0 > ParserMinimalBase.BI_MAX_INT.compareTo(bigInteger)) {
                    this.reportOverflowInt();
                }
            } else {
                if ((number instanceof Double) || (number instanceof Float)) {
                    final double dDoubleValue = number.doubleValue();
                    if (-2.147483648E9d > dDoubleValue || 2.147483647E9d < dDoubleValue) {
                        this.reportOverflowInt();
                    }
                    return (int) dDoubleValue;
                }
                if (number instanceof BigDecimal bigDecimal) {
                    if (0 < ParserMinimalBase.BD_MIN_INT.compareTo(bigDecimal) || 0 > ParserMinimalBase.BD_MAX_INT.compareTo(bigDecimal)) {
                        this.reportOverflowInt();
                    }
                } else {
                    this._throwInternal();
                }
            }
            return number.intValue();
        }

        protected long _convertNumberToLong(final Number number) throws IOException {
            if (number instanceof BigInteger bigInteger) {
                if (0 < ParserMinimalBase.BI_MIN_LONG.compareTo(bigInteger) || 0 > ParserMinimalBase.BI_MAX_LONG.compareTo(bigInteger)) {
                    this.reportOverflowLong();
                }
            } else {
                if ((number instanceof Double) || (number instanceof Float)) {
                    final double dDoubleValue = number.doubleValue();
                    if (-9.223372036854776E18d > dDoubleValue || 9.223372036854776E18d < dDoubleValue) {
                        this.reportOverflowLong();
                    }
                    return (long) dDoubleValue;
                }
                if (number instanceof BigDecimal bigDecimal) {
                    if (0 < ParserMinimalBase.BD_MIN_LONG.compareTo(bigDecimal) || 0 > ParserMinimalBase.BD_MAX_LONG.compareTo(bigDecimal)) {
                        this.reportOverflowLong();
                    }
                } else {
                    this._throwInternal();
                }
            }
            return number.longValue();
        }

        private void reportOverflowLong() {

        }


        public Object getEmbeddedObject() {
            if (JsonToken.VALUE_EMBEDDED_OBJECT == this._currToken) {
                return this._currentObject();
            }
            return null;
        }

        
        public byte[] getBinaryValue(final Base64Variant base64Variant) throws IOException {
            if (JsonToken.VALUE_EMBEDDED_OBJECT == this._currToken) {
                final Object obj_currentObject = this._currentObject();
                if (obj_currentObject instanceof byte[]) {
                    return (byte[]) obj_currentObject;
                }
            }
            if (JsonToken.VALUE_STRING != this._currToken) {
                throw this._constructError("Current token (" + _currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), cannot access as binary");
            }
            final String text = this.getText();
            if (null == text) {
                return null;
            }
            ByteArrayBuilder byteArrayBuilder = _byteBuilder;
            if (null == byteArrayBuilder) {
                byteArrayBuilder = new ByteArrayBuilder(100);
                _byteBuilder = byteArrayBuilder;
            } else {
                byteArrayBuilder.reset();
            }
            this._decodeBase64(text, byteArrayBuilder, base64Variant);
            return byteArrayBuilder.toByteArray();
        }

        
        public int readBinaryValue(final Base64Variant base64Variant, final OutputStream outputStream) throws IOException {
            final byte[] binaryValue = this.getBinaryValue(base64Variant);
            if (null == binaryValue) {
                return 0;
            }
            outputStream.write(binaryValue, 0, binaryValue.length);
            return binaryValue.length;
        }

        
        public boolean canReadObjectId() {
            return _hasNativeObjectIds;
        }

        
        public boolean canReadTypeId() {
            return _hasNativeTypeIds;
        }

        
        public Object getTypeId() {
            return _segment.findTypeId(_segmentPtr);
        }

        
        public Object getObjectId() {
            return _segment.findObjectId(_segmentPtr);
        }

        protected Object _currentObject() {
            return _segment.get(_segmentPtr);
        }

        protected void _checkIsNumber() throws JsonParseException {
            final JsonToken jsonToken = _currToken;
            if (null == jsonToken || !jsonToken.isNumeric()) {
                throw this._constructError("Current token (" + _currToken + ") not numeric, cannot use numeric value accessors");
            }
        }

        @Override // com.fasterxml.jackson.core.base.ParserMinimalBase
        protected void _handleEOF() throws JsonParseException {
            this._throwInternal();
        }
    }
    protected static final class Segment {
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX;
        protected TreeMap<Integer, Object> _nativeIds;
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        private int _objectIdIndex(final int i2) {
            return i2 + i2 + 1;
        }

        private int _typeIdIndex(final int i2) {
            return i2 + i2;
        }

        static {
            final JsonToken[] jsonTokenArr = new JsonToken[16];
            TOKEN_TYPES_BY_INDEX = jsonTokenArr;
            final JsonToken[] jsonTokenArrValues = JsonToken.values();
            System.arraycopy(jsonTokenArrValues, 1, jsonTokenArr, 1, Math.min(15, jsonTokenArrValues.length - 1));
        }

        public JsonToken type(final int i2) {
            long j2 = _tokenTypes;
            if (0 < i2) {
                j2 >>= i2 << 2;
            }
            return Segment.TOKEN_TYPES_BY_INDEX[((int) j2) & 15];
        }

        public Object get(final int i2) {
            return _tokens[i2];
        }

        public Segment next() {
            return _next;
        }

        public boolean hasIds() {
            return null != this._nativeIds;
        }

        public Segment append(final int i2, final JsonToken jsonToken) {
            if (16 > i2) {
                this.set(i2, jsonToken);
                return null;
            }
            final Segment segment = new Segment();
            _next = segment;
            segment.set(0, jsonToken);
            return _next;
        }

        public Segment append(final int i2, final JsonToken jsonToken, final Object obj, final Object obj2) {
            if (16 > i2) {
                this.set(i2, jsonToken, obj, obj2);
                return null;
            }
            final Segment segment = new Segment();
            _next = segment;
            segment.set(0, jsonToken, obj, obj2);
            return _next;
        }

        public Segment append(final int i2, final JsonToken jsonToken, final Object obj) {
            if (16 > i2) {
                this.set(i2, jsonToken, obj);
                return null;
            }
            final Segment segment = new Segment();
            _next = segment;
            segment.set(0, jsonToken, obj);
            return _next;
        }

        public Segment append(final int i2, final JsonToken jsonToken, final Object obj, final Object obj2, final Object obj3) {
            if (16 > i2) {
                this.set(i2, jsonToken, obj, obj2, obj3);
                return null;
            }
            final Segment segment = new Segment();
            _next = segment;
            segment.set(0, jsonToken, obj, obj2, obj3);
            return _next;
        }

        private void set(final int i2, final JsonToken jsonToken) {
            long jOrdinal = jsonToken.ordinal();
            if (0 < i2) {
                jOrdinal <<= i2 << 2;
            }
            _tokenTypes |= jOrdinal;
        }

        private void set(final int i2, final JsonToken jsonToken, final Object obj, final Object obj2) {
            long jOrdinal = jsonToken.ordinal();
            if (0 < i2) {
                jOrdinal <<= i2 << 2;
            }
            _tokenTypes = jOrdinal | _tokenTypes;
            this.assignNativeIds(i2, obj, obj2);
        }

        private void set(final int i2, final JsonToken jsonToken, final Object obj) {
            _tokens[i2] = obj;
            long jOrdinal = jsonToken.ordinal();
            if (0 < i2) {
                jOrdinal <<= i2 << 2;
            }
            _tokenTypes |= jOrdinal;
        }

        private void set(final int i2, final JsonToken jsonToken, final Object obj, final Object obj2, final Object obj3) {
            _tokens[i2] = obj;
            long jOrdinal = jsonToken.ordinal();
            if (0 < i2) {
                jOrdinal <<= i2 << 2;
            }
            _tokenTypes = jOrdinal | _tokenTypes;
            this.assignNativeIds(i2, obj2, obj3);
        }

        private void assignNativeIds(final int i2, final Object obj, final Object obj2) {
            if (null == this._nativeIds) {
                _nativeIds = new TreeMap<>();
            }
            if (null != obj) {
                _nativeIds.put(Integer.valueOf(this._objectIdIndex(i2)), obj);
            }
            if (null != obj2) {
                _nativeIds.put(Integer.valueOf(this._typeIdIndex(i2)), obj2);
            }
        }

        Object findObjectId(final int i2) {
            final TreeMap<Integer, Object> treeMap = _nativeIds;
            if (null == treeMap) {
                return null;
            }
            return treeMap.get(Integer.valueOf(this._objectIdIndex(i2)));
        }

        Object findTypeId(final int i2) {
            final TreeMap<Integer, Object> treeMap = _nativeIds;
            if (null == treeMap) {
                return null;
            }
            return treeMap.get(Integer.valueOf(this._typeIdIndex(i2)));
        }
    }
}
