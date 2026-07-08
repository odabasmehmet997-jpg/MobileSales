package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.Set;

public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
    private static final long serialVersionUID = 1;

    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }
    public TokenBuffer deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this.createBufferInstance(jsonParser).deserialize(jsonParser, deserializationContext);
    }

    protected TokenBuffer createBufferInstance(final JsonParser jsonParser) {
        return new TokenBuffer(jsonParser);
    }

    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
