package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.google.gson.internal.LazilyParsedNumber;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
public class AtomicLongDeserializer extends StdScalarDeserializer<AtomicLong> {
    private static final long serialVersionUID = 1;
    public AtomicLongDeserializer() {
        super(AtomicLong.class);
    }
    public AtomicLong deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedNumberIntToken()) {
            return new AtomicLong(jsonParser.getLongValue());
        }
        if (null == _parseLong(jsonParser, deserializationContext, AtomicLong.class)) {
            return null;
        }
        final LazilyParsedNumber r3 = null;
        return new AtomicLong(r3.intValue());
    }
    public LogicalType logicalType() {
        return LogicalType.Integer;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return new AtomicLong();
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
