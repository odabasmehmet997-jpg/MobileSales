package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    private static final long serialVersionUID = 1;

    public AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    public AtomicBoolean deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.VALUE_TRUE == jsonTokenCurrentToken) {
            return new AtomicBoolean(true);
        }
        if (JsonToken.VALUE_FALSE == jsonTokenCurrentToken) {
            return new AtomicBoolean(false);
        }
        final Boolean bool_parseBoolean = this._parseBoolean(jsonParser, deserializationContext, AtomicBoolean.class);
        if (null == bool_parseBoolean) {
            return null;
        }
        return new AtomicBoolean(bool_parseBoolean.booleanValue());
    }

    public LogicalType logicalType() {
        return LogicalType.Boolean;
    }

    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return new AtomicBoolean(false);
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
