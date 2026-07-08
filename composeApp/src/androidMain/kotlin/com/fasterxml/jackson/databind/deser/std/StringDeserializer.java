package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.Set;

public class StringDeserializer extends StdScalarDeserializer<String> {
    public static final StringDeserializer instance = new StringDeserializer();
    private static final long serialVersionUID = 1;

    public boolean isCachable() {
        return true;
    }

    public StringDeserializer() {
        super(String.class);
    }

    public LogicalType logicalType() {
        return LogicalType.Textual;
    }

    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return "";
    }

    public String deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String valueAsString;
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return jsonParser.getText();
        }
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_ARRAY == jsonTokenCurrentToken) {
            return this._deserializeFromArray(jsonParser, deserializationContext);
        }
        if (JsonToken.VALUE_EMBEDDED_OBJECT == jsonTokenCurrentToken) {
            final Object embeddedObject = jsonParser.getEmbeddedObject();
            if (null == embeddedObject) {
                return null;
            }
            if (embeddedObject instanceof byte[]) {
                return deserializationContext.getBase64Variant().encode((byte[]) embeddedObject, false);
            }
            return embeddedObject.toString();
        }
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            return deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass);
        }
        return (!jsonTokenCurrentToken.isScalarValue() || null == (valueAsString = jsonParser.getValueAsString())) ? (String) deserializationContext.handleUnexpectedToken(_valueClass, jsonParser) : valueAsString;
    }
    public String deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(jsonParser, deserializationContext);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
