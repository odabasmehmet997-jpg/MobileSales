package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDeserializer extends StdScalarDeserializer<AtomicInteger> {
    private static final long serialVersionUID = 1;

    public AtomicIntegerDeserializer() {
        super(AtomicInteger.class);
    }
    public AtomicInteger deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedNumberIntToken()) {
            return new AtomicInteger(jsonParser.getIntValue());
        }
        final Integer num_parseInteger = this._parseInteger(jsonParser, deserializationContext, AtomicInteger.class);
        if (null == num_parseInteger) {
            return null;
        }
        return new AtomicInteger(num_parseInteger.intValue());
    }
    public LogicalType logicalType() {
        return LogicalType.Integer;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return new AtomicInteger();
    }

    @Override
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
