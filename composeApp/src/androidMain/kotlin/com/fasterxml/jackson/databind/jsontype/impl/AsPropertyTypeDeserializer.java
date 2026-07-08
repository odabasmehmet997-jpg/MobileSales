package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonTypeInfo.EnumC1184As _inclusion;
    protected final String _msgForMissingId;

    public AsPropertyTypeDeserializer(final JavaType javaType, final TypeIdResolver typeIdResolver, final String str, final boolean z, final JavaType javaType2) {
        this(javaType, typeIdResolver, str, z, javaType2, JsonTypeInfo.EnumC1184As.PROPERTY);
    }

    public AsPropertyTypeDeserializer(final JavaType javaType, final TypeIdResolver typeIdResolver, final String str, final boolean z, final JavaType javaType2, final JsonTypeInfo.EnumC1184As enumC1184As) {
        final String str2;
        super(javaType, typeIdResolver, str, z, javaType2);
        final BeanProperty beanProperty = _property;
        if (null == beanProperty) {
            str2 = String.format("missing type id property '%s'", _typePropertyName);
        } else {
            str2 = String.format("missing type id property '%s' (for POJO property '%s')", _typePropertyName, beanProperty.getName());
        }
        _msgForMissingId = str2;
        _inclusion = enumC1184As;
    }

    public AsPropertyTypeDeserializer(final AsPropertyTypeDeserializer asPropertyTypeDeserializer, final BeanProperty beanProperty) {
        final String str;
        super(asPropertyTypeDeserializer, beanProperty);
        final BeanProperty beanProperty2 = _property;
        if (null == beanProperty2) {
            str = String.format("missing type id property '%s'", _typePropertyName);
        } else {
            str = String.format("missing type id property '%s' (for POJO property '%s')", _typePropertyName, beanProperty2.getName());
        }
        _msgForMissingId = str;
        _inclusion = asPropertyTypeDeserializer._inclusion;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer, com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase, com.fasterxml.jackson.databind.jsontype.TypeDeserializer
    public TypeDeserializer forProperty(final BeanProperty beanProperty) {
        return beanProperty == _property ? this : new AsPropertyTypeDeserializer(this, beanProperty);
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer, com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase, com.fasterxml.jackson.databind.jsontype.TypeDeserializer
    public JsonTypeInfo.EnumC1184As getTypeInclusion() {
        return _inclusion;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer, com.fasterxml.jackson.databind.jsontype.TypeDeserializer
    public Object deserializeTypedFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object typeId;
        if (jsonParser.canReadTypeId() && null != (typeId = jsonParser.getTypeId())) {
            return this._deserializeWithNativeTypeId(jsonParser, deserializationContext, typeId);
        }
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        TokenBuffer tokenBuffer = null;
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        } else if (JsonToken.FIELD_NAME != jsonTokenCurrentToken) {
            return this._deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null, _msgForMissingId);
        }
        final boolean zIsEnabled = deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            if (strCurrentName.equals(_typePropertyName) || (zIsEnabled && strCurrentName.equalsIgnoreCase(_typePropertyName))) {
                return this._deserializeTypedForId(jsonParser, deserializationContext, tokenBuffer, jsonParser.getText());
            }
            if (null == tokenBuffer) {
                tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
            }
            tokenBuffer.writeFieldName(strCurrentName);
            tokenBuffer.copyCurrentStructure(jsonParser);
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        return this._deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer, _msgForMissingId);
    }

    protected Object _deserializeTypedForId(JsonParser jsonParser, final DeserializationContext deserializationContext, TokenBuffer tokenBuffer, final String str) throws IOException {
        final JsonDeserializer<Object> jsonDeserializer_findDeserializer = this._findDeserializer(deserializationContext, str);
        if (_typeIdVisible) {
            if (null == tokenBuffer) {
                tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
            }
            tokenBuffer.writeFieldName(jsonParser.currentName());
            tokenBuffer.writeString(str);
        }
        if (null != tokenBuffer) {
            jsonParser.clearCurrentToken();
            jsonParser = JsonParserSequence.createFlattened(false, tokenBuffer.asParser(jsonParser), jsonParser);
        }
        jsonParser.nextToken();
        return jsonDeserializer_findDeserializer.deserialize(jsonParser, deserializationContext);
    }

    @Deprecated
    protected Object _deserializeTypedUsingDefaultImpl(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TokenBuffer tokenBuffer) throws IOException {
        return this._deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer, null);
    }

    protected Object _deserializeTypedUsingDefaultImpl(JsonParser jsonParser, final DeserializationContext deserializationContext, final TokenBuffer tokenBuffer, final String str) throws IOException {
        if (!this.hasDefaultImpl()) {
            final Object objDeserializeIfNatural = deserializeIfNatural(jsonParser, deserializationContext, _baseType);
            if (null != objDeserializeIfNatural) {
                return objDeserializeIfNatural;
            }
            if (jsonParser.isExpectedStartArrayToken()) {
                return super.deserializeTypedFromAny(jsonParser, deserializationContext);
            }
            if (jsonParser.hasToken(JsonToken.VALUE_STRING) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().trim().isEmpty()) {
                return null;
            }
        }
        JsonDeserializer<Object> jsonDeserializer_findDefaultImplDeserializer = this._findDefaultImplDeserializer(deserializationContext);
        if (null == jsonDeserializer_findDefaultImplDeserializer) {
            final JavaType javaType_handleMissingTypeId = this._handleMissingTypeId(deserializationContext, str);
            if (null == javaType_handleMissingTypeId) {
                return null;
            }
            jsonDeserializer_findDefaultImplDeserializer = deserializationContext.findContextualValueDeserializer(javaType_handleMissingTypeId, _property);
        }
        if (null != tokenBuffer) {
            tokenBuffer.writeEndObject();
            jsonParser = tokenBuffer.asParser(jsonParser);
            jsonParser.nextToken();
        }
        return jsonDeserializer_findDefaultImplDeserializer.deserialize(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.jsontype.impl.AsArrayTypeDeserializer, com.fasterxml.jackson.databind.jsontype.TypeDeserializer
    public Object deserializeTypedFromAny(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY)) {
            return deserializeTypedFromArray(jsonParser, deserializationContext);
        }
        return this.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
}
