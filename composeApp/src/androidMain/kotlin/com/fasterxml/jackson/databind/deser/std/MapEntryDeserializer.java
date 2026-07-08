package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class MapEntryDeserializer extends ContainerDeserializerBase<Map.Entry<Object, Object>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final KeyDeserializer _keyDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapEntryDeserializer(final JavaType javaType, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        super(javaType);
        if (2 != javaType.containedTypeCount()) {
            throw new IllegalArgumentException("Missing generic type information for " + javaType);
        }
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
    }

    protected MapEntryDeserializer(final MapEntryDeserializer mapEntryDeserializer) {
        super(mapEntryDeserializer);
        _keyDeserializer = mapEntryDeserializer._keyDeserializer;
        _valueDeserializer = mapEntryDeserializer._valueDeserializer;
        _valueTypeDeserializer = mapEntryDeserializer._valueTypeDeserializer;
    }

    protected MapEntryDeserializer(final MapEntryDeserializer mapEntryDeserializer, final KeyDeserializer keyDeserializer, final JsonDeserializer<Object> jsonDeserializer, final TypeDeserializer typeDeserializer) {
        super(mapEntryDeserializer);
        _keyDeserializer = keyDeserializer;
        _valueDeserializer = jsonDeserializer;
        _valueTypeDeserializer = typeDeserializer;
    }

    protected MapEntryDeserializer withResolved(final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        return (_keyDeserializer == keyDeserializer && _valueDeserializer == jsonDeserializer && _valueTypeDeserializer == typeDeserializer) ? this : new MapEntryDeserializer(this, keyDeserializer, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Map;
    }

    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializerCreateContextual;
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final KeyDeserializer keyDeserializer = _keyDeserializer;
        if (keyDeserializer==null) {
            keyDeserializerCreateContextual = deserializationContext.findKeyDeserializer(_containerType.containedType(0), beanProperty);
        } else {
            final boolean z = keyDeserializer instanceof ContextualKeyDeserializer;
            keyDeserializerCreateContextual = keyDeserializer;
            if (z) {
                keyDeserializerCreateContextual = ((ContextualKeyDeserializer) keyDeserializer).createContextual(deserializationContext, beanProperty);
            }
        }
        final JsonDeserializer<?> jsonDeserializerFindConvertingContentDeserializer = this.findConvertingContentDeserializer(deserializationContext, beanProperty, _valueDeserializer);
        final JavaType javaTypeContainedType = _containerType.containedType(1);
        if (null == jsonDeserializerFindConvertingContentDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(javaTypeContainedType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializerFindConvertingContentDeserializer, beanProperty, javaTypeContainedType);
        }
        TypeDeserializer typeDeserializerForProperty = _valueTypeDeserializer;
        if (null != typeDeserializerForProperty) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return this.withResolved(keyDeserializerCreateContextual, typeDeserializerForProperty, jsonDeserializerHandleSecondaryContextualization);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JavaType getContentType() {
        return _containerType.containedType(1);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return _valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Map.Entry<Object, Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        Object objDeserializeWithType;
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        } else if (JsonToken.FIELD_NAME != jsonTokenCurrentToken && JsonToken.END_OBJECT != jsonTokenCurrentToken) {
            if (JsonToken.START_ARRAY == jsonTokenCurrentToken) {
                return this._deserializeFromArray(jsonParser, deserializationContext);
            }
            return (Map.Entry) deserializationContext.handleUnexpectedToken(this.getValueType(deserializationContext), jsonParser);
        }
        if (JsonToken.FIELD_NAME != jsonTokenCurrentToken) {
            if (JsonToken.END_OBJECT == jsonTokenCurrentToken) {
                return deserializationContext.reportInputMismatch(this, "Cannot deserialize a Map.Entry out of empty JSON Object", new Object[0]);
            }
            return (Map.Entry) deserializationContext.handleUnexpectedToken(this.handledType(), jsonParser);
        }
        final KeyDeserializer keyDeserializer = _keyDeserializer;
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        final String strCurrentName = jsonParser.currentName();
        final Object objDeserializeKey = keyDeserializer.deserializeKey(strCurrentName, deserializationContext);
        try {
            if (JsonToken.VALUE_NULL == jsonParser.nextToken()) {
                objDeserializeWithType = jsonDeserializer.getNullValue(deserializationContext);
            } else if (null == typeDeserializer) {
                objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            }
        } catch (final Exception e2) {
            this.wrapAndThrow(deserializationContext, e2, Map.Entry.class, strCurrentName);
            objDeserializeWithType = null;
        }
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        if (JsonToken.END_OBJECT != jsonTokenNextToken) {
            if (JsonToken.FIELD_NAME == jsonTokenNextToken) {
                deserializationContext.reportInputMismatch(this, "Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '%s')", jsonParser.currentName());
            } else {
                deserializationContext.reportInputMismatch(this, "Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + jsonTokenNextToken);
            }
            return null;
        }
        return new AbstractMap.SimpleEntry(objDeserializeKey, objDeserializeWithType);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Map.Entry<Object, Object> deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Map.Entry<Object, Object> entry) throws IOException {
        throw new IllegalStateException("Cannot update Map.Entry values");
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
