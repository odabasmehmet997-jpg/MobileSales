package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;

import java.io.IOException;
import java.util.*;

public class UntypedObjectDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer, ContextualDeserializer {
    protected static final Object[] NO_OBJECTS = new Object[0];
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _listDeserializer;
    protected JavaType _listType;
    protected JsonDeserializer<Object> _mapDeserializer;
    protected JavaType _mapType;
    protected final boolean _nonMerging;
    protected JsonDeserializer<Object> _numberDeserializer;
    protected JsonDeserializer<Object> _stringDeserializer;

    public boolean isCachable() {
        return true;
    }

    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return null;
    }

    public UntypedObjectDeserializer() {
        this(null, null);
    }

    public UntypedObjectDeserializer(final JavaType javaType, final JavaType javaType2) {
        super(Object.class);
        _listType = javaType;
        _mapType = javaType2;
        _nonMerging = false;
    }

    public UntypedObjectDeserializer(final UntypedObjectDeserializer untypedObjectDeserializer, final JsonDeserializer<?> jsonDeserializer, final JsonDeserializer<?> jsonDeserializer2, final JsonDeserializer<?> jsonDeserializer3, final JsonDeserializer<?> jsonDeserializer4) {
        super(Object.class);
        _mapDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
        _listDeserializer = (JsonDeserializer<Object>) jsonDeserializer2;
        _stringDeserializer = (JsonDeserializer<Object>) jsonDeserializer3;
        _numberDeserializer = (JsonDeserializer<Object>) jsonDeserializer4;
        _listType = untypedObjectDeserializer._listType;
        _mapType = untypedObjectDeserializer._mapType;
        _nonMerging = untypedObjectDeserializer._nonMerging;
    }

    protected UntypedObjectDeserializer(final UntypedObjectDeserializer untypedObjectDeserializer, final boolean z) {
        super(Object.class);
        _mapDeserializer = untypedObjectDeserializer._mapDeserializer;
        _listDeserializer = untypedObjectDeserializer._listDeserializer;
        _stringDeserializer = untypedObjectDeserializer._stringDeserializer;
        _numberDeserializer = untypedObjectDeserializer._numberDeserializer;
        _listType = untypedObjectDeserializer._listType;
        _mapType = untypedObjectDeserializer._mapType;
        _nonMerging = z;
    }
    public void resolve(final DeserializationContext deserializationContext) throws JsonMappingException {
        final JavaType javaTypeConstructType = deserializationContext.constructType(Object.class);
        final JavaType javaTypeConstructType2 = deserializationContext.constructType(String.class);
        final TypeFactory typeFactory = deserializationContext.getTypeFactory();
        final JavaType javaType = _listType;
        if (null == javaType) {
            _listDeserializer = this._clearIfStdImpl(this._findCustomDeser(deserializationContext, typeFactory.constructCollectionType(List.class, javaTypeConstructType)));
        } else {
            _listDeserializer = this._findCustomDeser(deserializationContext, javaType);
        }
        final JavaType javaType2 = _mapType;
        if (null == javaType2) {
            _mapDeserializer = this._clearIfStdImpl(this._findCustomDeser(deserializationContext, typeFactory.constructMapType(Map.class, javaTypeConstructType2, javaTypeConstructType)));
        } else {
            _mapDeserializer = this._findCustomDeser(deserializationContext, javaType2);
        }
        _stringDeserializer = this._clearIfStdImpl(this._findCustomDeser(deserializationContext, javaTypeConstructType2));
        _numberDeserializer = this._clearIfStdImpl(this._findCustomDeser(deserializationContext, typeFactory.constructType(Number.class)));
        final JavaType javaTypeUnknownType = TypeFactory.unknownType();
        _mapDeserializer = (JsonDeserializer<Object>) deserializationContext.handleSecondaryContextualization(_mapDeserializer, null, javaTypeUnknownType);
        _listDeserializer = (JsonDeserializer<Object>) deserializationContext.handleSecondaryContextualization(_listDeserializer, null, javaTypeUnknownType);
        _stringDeserializer = (JsonDeserializer<Object>) deserializationContext.handleSecondaryContextualization(_stringDeserializer, null, javaTypeUnknownType);
        _numberDeserializer = (JsonDeserializer<Object>) deserializationContext.handleSecondaryContextualization(_numberDeserializer, null, javaTypeUnknownType);
    }

    protected JsonDeserializer<Object> _findCustomDeser(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        return deserializationContext.findNonContextualValueDeserializer(javaType);
    }

    protected JsonDeserializer<Object> _clearIfStdImpl(final JsonDeserializer<Object> jsonDeserializer) {
        if (ClassUtil.isJacksonStdImpl(jsonDeserializer)) {
            return null;
        }
        return jsonDeserializer;
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final boolean z = null == beanProperty && Boolean.FALSE.equals(deserializationContext.getConfig().getDefaultMergeable(Object.class));
        if (null == this._stringDeserializer && null == this._numberDeserializer && null == this._mapDeserializer && null == this._listDeserializer && UntypedObjectDeserializer.class == getClass()) {
            return Vanilla.instance(z);
        }
        return z != _nonMerging ? new UntypedObjectDeserializer(this, z) : this;
    }

    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.currentTokenId()) {
            case 1:
            case 2:
            case 5:
                final JsonDeserializer<Object> jsonDeserializer = _mapDeserializer;
                if (null != jsonDeserializer) {
                    return jsonDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return this.mapObject(jsonParser, deserializationContext);
            case 3:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return this.mapArrayToArray(jsonParser, deserializationContext);
                }
                final JsonDeserializer<Object> jsonDeserializer2 = _listDeserializer;
                if (null != jsonDeserializer2) {
                    return jsonDeserializer2.deserialize(jsonParser, deserializationContext);
                }
                return this.mapArray(jsonParser, deserializationContext);
            case 4:
            default:
                return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            case 6:
                final JsonDeserializer<Object> jsonDeserializer3 = _stringDeserializer;
                if (null != jsonDeserializer3) {
                    return jsonDeserializer3.deserialize(jsonParser, deserializationContext);
                }
                return jsonParser.getText();
            case 7:
                final JsonDeserializer<Object> jsonDeserializer4 = _numberDeserializer;
                if (null != jsonDeserializer4) {
                    return jsonDeserializer4.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return this._coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                final JsonDeserializer<Object> jsonDeserializer5 = _numberDeserializer;
                if (null != jsonDeserializer5) {
                    return jsonDeserializer5.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 != iCurrentTokenId && 3 != iCurrentTokenId) {
            switch (iCurrentTokenId) {
                case 5:
                    break;
                case 6:
                    final JsonDeserializer<Object> jsonDeserializer = _stringDeserializer;
                    if (null != jsonDeserializer) {
                        return jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                    return jsonParser.getText();
                case 7:
                    final JsonDeserializer<Object> jsonDeserializer2 = _numberDeserializer;
                    if (null != jsonDeserializer2) {
                        return jsonDeserializer2.deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        return this._coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    final JsonDeserializer<Object> jsonDeserializer3 = _numberDeserializer;
                    if (null != jsonDeserializer3) {
                        return jsonDeserializer3.deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            }
        }
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        if (_nonMerging) {
            return this.deserialize(jsonParser, deserializationContext);
        }
        switch (jsonParser.currentTokenId()) {
            case 1:
            case 2:
            case 5:
                final JsonDeserializer<Object> jsonDeserializer = _mapDeserializer;
                if (null == jsonDeserializer) {
                    if (!(obj instanceof Map)) {
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case 3:
                final JsonDeserializer<Object> jsonDeserializer2 = _listDeserializer;
                if (null == jsonDeserializer2) {
                    if (!(obj instanceof Collection)) {
                        if (!deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case 6:
                final JsonDeserializer<Object> jsonDeserializer3 = _stringDeserializer;
                if (null == jsonDeserializer3) {
                    break;
                } else {
                    break;
                }
            case 7:
                final JsonDeserializer<Object> jsonDeserializer4 = _numberDeserializer;
                if (null == jsonDeserializer4) {
                    if (!deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            case 8:
                final JsonDeserializer<Object> jsonDeserializer5 = _numberDeserializer;
                if (null == jsonDeserializer5) {
                    if (!deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        break;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
        }
        return this.deserialize(jsonParser, deserializationContext);
    }

    protected Object mapArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        final JsonToken jsonToken = JsonToken.END_ARRAY;
        int i2 = 2;
        if (jsonTokenNextToken == jsonToken) {
            return new ArrayList(2);
        }
        final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == jsonToken) {
            final ArrayList arrayList = new ArrayList(2);
            arrayList.add(objDeserialize);
            return arrayList;
        }
        final Object objDeserialize2 = this.deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == jsonToken) {
            final ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(objDeserialize);
            arrayList2.add(objDeserialize2);
            return arrayList2;
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
        objArrResetAndStart[0] = objDeserialize;
        objArrResetAndStart[1] = objDeserialize2;
        int i3 = 2;
        while (true) {
            final Object objDeserialize3 = this.deserialize(jsonParser, deserializationContext);
            i2++;
            if (i3 >= objArrResetAndStart.length) {
                objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                i3 = 0;
            }
            final int i4 = i3 + 1;
            objArrResetAndStart[i3] = objDeserialize3;
            if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                final ArrayList arrayList3 = new ArrayList(i2);
                objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i4, arrayList3);
                return arrayList3;
            }
            i3 = i4;
        }
    }

    protected Object mapArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Collection<Object> collection) throws IOException {
        while (JsonToken.END_ARRAY != jsonParser.nextToken()) {
            collection.add(this.deserialize(jsonParser, deserializationContext));
        }
        return collection;
    }

    protected Object mapObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String strCurrentName;
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            strCurrentName = jsonParser.nextFieldName();
        } else if (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            strCurrentName = jsonParser.currentName();
        } else {
            if (JsonToken.END_OBJECT != jsonTokenCurrentToken) {
                return deserializationContext.handleUnexpectedToken(this.handledType(), jsonParser);
            }
            strCurrentName = null;
        }
        final String str = strCurrentName;
        if (null == str) {
            return new LinkedHashMap(2);
        }
        jsonParser.nextToken();
        final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
        final String strNextFieldName = jsonParser.nextFieldName();
        if (null == strNextFieldName) {
            final LinkedHashMap linkedHashMap = new LinkedHashMap(2);
            linkedHashMap.put(str, objDeserialize);
            return linkedHashMap;
        }
        jsonParser.nextToken();
        final Object objDeserialize2 = this.deserialize(jsonParser, deserializationContext);
        String strNextFieldName2 = jsonParser.nextFieldName();
        if (null == strNextFieldName2) {
            final LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(str, objDeserialize);
            return null != linkedHashMap2.put(strNextFieldName, objDeserialize2) ? this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap2, str, objDeserialize, objDeserialize2, strNextFieldName2) : linkedHashMap2;
        }
        final LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(str, objDeserialize);
        if (null != linkedHashMap3.put(strNextFieldName, objDeserialize2)) {
            return this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, str, objDeserialize, objDeserialize2, strNextFieldName2);
        }
        do {
            jsonParser.nextToken();
            final Object objDeserialize3 = this.deserialize(jsonParser, deserializationContext);
            final Object objPut = linkedHashMap3.put(strNextFieldName2, objDeserialize3);
            if (null != objPut) {
                return this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, strNextFieldName2, objPut, objDeserialize3, jsonParser.nextFieldName());
            }
            strNextFieldName2 = jsonParser.nextFieldName();
        } while (null != strNextFieldName2);
        return linkedHashMap3;
    }

    protected Object _mapObjectWithDups(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<String, Object> map, final String str, final Object obj, final Object obj2, String str2) throws IOException {
        final boolean zIsEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
        if (zIsEnabled) {
            this._squashDups(map, str, obj, obj2);
        }
        while (null != str2) {
            jsonParser.nextToken();
            final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
            final Object objPut = map.put(str2, objDeserialize);
            if (null != objPut && zIsEnabled) {
                this._squashDups(map, str, objPut, objDeserialize);
            }
            str2 = jsonParser.nextFieldName();
        }
        return map;
    }

    private void _squashDups(final Map<String, Object> map, final String str, final Object obj, final Object obj2) {
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            map.put(str, obj);
        } else {
            final ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            arrayList.add(obj2);
            map.put(str, arrayList);
        }
    }

    protected Object[] mapArrayToArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
            return UntypedObjectDeserializer.NO_OBJECTS;
        }
        final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
        int i2 = 0;
        while (true) {
            final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
            if (i2 >= objArrResetAndStart.length) {
                objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                i2 = 0;
            }
            final int i3 = i2 + 1;
            objArrResetAndStart[i2] = objDeserialize;
            if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                return objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i3);
            }
            i2 = i3;
        }
    }

    protected Object mapObject(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<Object, Object> map) throws IOException {
        Object objDeserialize;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
            return map;
        }
        String strCurrentName = jsonParser.currentName();
        do {
            jsonParser.nextToken();
            final Object obj = map.get(strCurrentName);
            if (null != obj) {
                objDeserialize = this.deserialize(jsonParser, deserializationContext, obj);
            } else {
                objDeserialize = this.deserialize(jsonParser, deserializationContext);
            }
            if (objDeserialize != obj) {
                map.put(strCurrentName, objDeserialize);
            }
            strCurrentName = jsonParser.nextFieldName();
        } while (null != strCurrentName);
        return map;
    }
    public static class Vanilla extends StdDeserializer<Object> {
        private static final long serialVersionUID = 1;
        public static final Vanilla std = new Vanilla();
        protected final boolean _nonMerging;

        public Vanilla() {
            this(false);
        }

        protected Vanilla(final boolean z) {
            super(Object.class);
            _nonMerging = z;
        }

        public static Vanilla instance(final boolean z) {
            if (z) {
                return new Vanilla(true);
            }
            return Vanilla.std;
        }
        public LogicalType logicalType() {
            return LogicalType.Untyped;
        }
        public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
            if (_nonMerging) {
                return Boolean.FALSE;
            }
            return null;
        }
        public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            switch (jsonParser.currentTokenId()) {
                case 1:
                    if (JsonToken.END_OBJECT == jsonParser.nextToken()) {
                        return new LinkedHashMap(2);
                    }
                    break;
                case 2:
                    return new LinkedHashMap(2);
                case 3:
                    if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            return NO_OBJECTS;
                        }
                        return new ArrayList(2);
                    }
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                        return this.mapArrayToArray(jsonParser, deserializationContext);
                    }
                    return this.mapArray(jsonParser, deserializationContext);
                case 4:
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                case 5:
                    break;
                case 6:
                    return jsonParser.getText();
                case 7:
                    if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        return this._coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
            }
            return this.mapObject(jsonParser, deserializationContext);
        }
        public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 != iCurrentTokenId && 3 != iCurrentTokenId) {
                switch (iCurrentTokenId) {
                    case 5:
                        break;
                    case 6:
                        return jsonParser.getText();
                    case 7:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                            return jsonParser.getBigIntegerValue();
                        }
                        return jsonParser.getNumberValue();
                    case 8:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return jsonParser.getDecimalValue();
                        }
                        return jsonParser.getNumberValue();
                    case 9:
                        return Boolean.TRUE;
                    case 10:
                        return Boolean.FALSE;
                    case 11:
                        return null;
                    case 12:
                        return jsonParser.getEmbeddedObject();
                    default:
                        return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                }
            }
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
            Object objDeserialize;
            if (_nonMerging) {
                return this.deserialize(jsonParser, deserializationContext);
            }
            final int iCurrentTokenId = jsonParser.currentTokenId();
            if (1 != iCurrentTokenId) {
                if (2 != iCurrentTokenId) {
                    if (3 == iCurrentTokenId) {
                        if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                            return obj;
                        }
                        if (obj instanceof Collection collection) {
                            do {
                                collection.add(this.deserialize(jsonParser, deserializationContext));
                            } while (JsonToken.END_ARRAY != jsonParser.nextToken());
                        }
                        return this.deserialize(jsonParser, deserializationContext);
                    }
                    if (4 != iCurrentTokenId) {
                        if (5 == iCurrentTokenId) {
                        }
                        return this.deserialize(jsonParser, deserializationContext);
                    }
                }
                return obj;
            }
            if (JsonToken.END_OBJECT == jsonParser.nextToken()) {
                return obj;
            }
            if (obj instanceof Map map) {
                String strCurrentName = jsonParser.currentName();
                do {
                    jsonParser.nextToken();
                    final Object obj2 = map.get(strCurrentName);
                    if (null != obj2) {
                        objDeserialize = this.deserialize(jsonParser, deserializationContext, obj2);
                    } else {
                        objDeserialize = this.deserialize(jsonParser, deserializationContext);
                    }
                    if (objDeserialize != obj2) {
                        map.put(strCurrentName, objDeserialize);
                    }
                    strCurrentName = jsonParser.nextFieldName();
                } while (null != strCurrentName);
                return obj;
            }
            return this.deserialize(jsonParser, deserializationContext);
        }

        protected Object mapArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            int i2 = 2;
            if (jsonTokenNextToken == jsonToken) {
                final ArrayList arrayList = new ArrayList(2);
                arrayList.add(objDeserialize);
                return arrayList;
            }
            final Object objDeserialize2 = this.deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == jsonToken) {
                final ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(objDeserialize);
                arrayList2.add(objDeserialize2);
                return arrayList2;
            }
            final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
            objArrResetAndStart[0] = objDeserialize;
            objArrResetAndStart[1] = objDeserialize2;
            int i3 = 2;
            while (true) {
                final Object objDeserialize3 = this.deserialize(jsonParser, deserializationContext);
                i2++;
                if (i3 >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    i3 = 0;
                }
                final int i4 = i3 + 1;
                objArrResetAndStart[i3] = objDeserialize3;
                if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                    final ArrayList arrayList3 = new ArrayList(i2);
                    objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i4, arrayList3);
                    return arrayList3;
                }
                i3 = i4;
            }
        }

        protected Object[] mapArrayToArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final ObjectBuffer objectBufferLeaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] objArrResetAndStart = objectBufferLeaseObjectBuffer.resetAndStart();
            int i2 = 0;
            while (true) {
                final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
                if (i2 >= objArrResetAndStart.length) {
                    objArrResetAndStart = objectBufferLeaseObjectBuffer.appendCompletedChunk(objArrResetAndStart);
                    i2 = 0;
                }
                final int i3 = i2 + 1;
                objArrResetAndStart[i2] = objDeserialize;
                if (JsonToken.END_ARRAY == jsonParser.nextToken()) {
                    return objectBufferLeaseObjectBuffer.completeAndClearBuffer(objArrResetAndStart, i3);
                }
                i2 = i3;
            }
        }

        protected Object mapObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            final String text = jsonParser.getText();
            jsonParser.nextToken();
            final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
            final String strNextFieldName = jsonParser.nextFieldName();
            if (null == strNextFieldName) {
                final LinkedHashMap linkedHashMap = new LinkedHashMap(2);
                linkedHashMap.put(text, objDeserialize);
                return linkedHashMap;
            }
            jsonParser.nextToken();
            final Object objDeserialize2 = this.deserialize(jsonParser, deserializationContext);
            final String strNextFieldName2 = jsonParser.nextFieldName();
            if (null == strNextFieldName2) {
                final LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
                linkedHashMap2.put(text, objDeserialize);
                return null != linkedHashMap2.put(strNextFieldName, objDeserialize2) ? this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap2, text, objDeserialize, objDeserialize2, strNextFieldName2) : linkedHashMap2;
            }
            final LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put(text, objDeserialize);
            if (null != linkedHashMap3.put(strNextFieldName, objDeserialize2)) {
                return this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, text, objDeserialize, objDeserialize2, strNextFieldName2);
            }
            String strNextFieldName3 = strNextFieldName2;
            do {
                jsonParser.nextToken();
                final Object objDeserialize3 = this.deserialize(jsonParser, deserializationContext);
                final Object objPut = linkedHashMap3.put(strNextFieldName3, objDeserialize3);
                if (null != objPut) {
                    return this._mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, strNextFieldName3, objPut, objDeserialize3, jsonParser.nextFieldName());
                }
                strNextFieldName3 = jsonParser.nextFieldName();
            } while (null != strNextFieldName3);
            return linkedHashMap3;
        }

        protected Object _mapObjectWithDups(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map<String, Object> map, final String str, final Object obj, final Object obj2, String str2) throws IOException {
            final boolean zIsEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
            if (zIsEnabled) {
                this._squashDups(map, str, obj, obj2);
            }
            while (null != str2) {
                jsonParser.nextToken();
                final Object objDeserialize = this.deserialize(jsonParser, deserializationContext);
                final Object objPut = map.put(str2, objDeserialize);
                if (null != objPut && zIsEnabled) {
                    this._squashDups(map, str2, objPut, objDeserialize);
                }
                str2 = jsonParser.nextFieldName();
            }
            return map;
        }

        private void _squashDups(final Map<String, Object> map, final String str, final Object obj, final Object obj2) {
            if (obj instanceof List) {
                ((List) obj).add(obj2);
                map.put(str, obj);
            } else {
                final ArrayList arrayList = new ArrayList();
                arrayList.add(obj);
                arrayList.add(obj2);
                map.put(str, arrayList);
            }
        }
    }
}
