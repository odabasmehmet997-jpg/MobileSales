package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;

public class AsArrayTypeDeserializer extends TypeDeserializerBase {
    private static final long serialVersionUID = 1;

    protected boolean _usesExternalId() {
        return false;
    }

    public AsArrayTypeDeserializer(final JavaType javaType, final TypeIdResolver typeIdResolver, final String str, final boolean z, final JavaType javaType2) {
        super(javaType, typeIdResolver, str, z, javaType2);
    }

    public AsArrayTypeDeserializer(final AsArrayTypeDeserializer asArrayTypeDeserializer, final BeanProperty beanProperty) {
        super(asArrayTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(final BeanProperty beanProperty) {
        return beanProperty == _property ? this : new AsArrayTypeDeserializer(this, beanProperty);
    }

    public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return JsonTypeInfo.EnumC1184As.WRAPPER_ARRAY;
    }

    public Object deserializeTypedFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserialize(jsonParser, deserializationContext);
    }
    public Object deserializeTypedFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserialize(jsonParser, deserializationContext);
    }
    public Object deserializeTypedFromScalar(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserialize(jsonParser, deserializationContext);
    }
    public Object deserializeTypedFromAny(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserialize(jsonParser, deserializationContext);
    }

    protected Object _deserialize(JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object typeId;
        if (jsonParser.canReadTypeId() && null != (typeId = jsonParser.getTypeId())) {
            return this._deserializeWithNativeTypeId(jsonParser, deserializationContext, typeId);
        }
        final boolean zIsExpectedStartArrayToken = jsonParser.isExpectedStartArrayToken();
        final String str_locateTypeId = this._locateTypeId(jsonParser, deserializationContext);
        final JsonDeserializer<Object> jsonDeserializer_findDeserializer = this._findDeserializer(deserializationContext, str_locateTypeId);
        if (_typeIdVisible && !this._usesExternalId() && jsonParser.hasToken(JsonToken.START_OBJECT)) {
            final TokenBuffer tokenBuffer = new TokenBuffer(null, false);
            tokenBuffer.writeStartObject();
            tokenBuffer.writeFieldName(_typePropertyName);
            tokenBuffer.writeString(str_locateTypeId);
            jsonParser.clearCurrentToken();
            jsonParser = JsonParserSequence.createFlattened(false, tokenBuffer.asParser(jsonParser), jsonParser);
            jsonParser.nextToken();
        }
        if (zIsExpectedStartArrayToken && JsonToken.END_ARRAY == jsonParser.currentToken()) {
            return jsonDeserializer_findDeserializer.getNullValue(deserializationContext);
        }
        final Object objDeserialize = jsonDeserializer_findDeserializer.deserialize(jsonParser, deserializationContext);
        if (zIsExpectedStartArrayToken) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken != jsonToken) {
                deserializationContext.reportWrongTokenException(this.baseType(), jsonToken, "expected closing END_ARRAY after type information and deserialized value");
            }
        }
        return objDeserialize;
    }

    protected String _locateTypeId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            if (null != this._defaultImpl) {
                return _idResolver.idFromBaseType();
            }
            deserializationContext.reportWrongTokenException(this.baseType(), JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + this.baseTypeName());
            return null;
        }
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        final JsonToken jsonToken = JsonToken.VALUE_STRING;
        if (jsonTokenNextToken == jsonToken) {
            final String text = jsonParser.getText();
            jsonParser.nextToken();
            return text;
        }
        deserializationContext.reportWrongTokenException(this.baseType(), jsonToken, "need JSON String that contains type id (for subtype of %s)", this.baseTypeName());
        return null;
    }
}
