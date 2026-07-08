package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer instance = new NullifyingDeserializer();
    private static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
            jsonParser.skipChildren();
            return null;
        }
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == jsonTokenNextToken || JsonToken.END_OBJECT == jsonTokenNextToken) {
                return null;
            }
            jsonParser.skipChildren();
        }
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId || 3 == iCurrentTokenId || 5 == iCurrentTokenId) {
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        return null;
    }
}
