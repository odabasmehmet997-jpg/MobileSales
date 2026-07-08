package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;

public class MappingJsonFactory extends JsonFactory {
    private static final long serialVersionUID = -1;
    public MappingJsonFactory() {
        this(null);
    }
    public MappingJsonFactory(final ObjectMapper objectMapper) {
        super(objectMapper);
        if (null == objectMapper) {
            this.setCodec(new ObjectMapper(this));
        }
    }
    public MappingJsonFactory(final JsonFactory jsonFactory, final ObjectMapper objectMapper) {
        super(jsonFactory, objectMapper);
        if (null == objectMapper) {
            this.setCodec(new ObjectMapper(this));
        }
    }
    public final ObjectMapper getCodec() {
        return (ObjectMapper) _objectCodec;
    }
    public JsonFactory copy() {
        this._checkInvalidCopy(MappingJsonFactory.class);
        return new MappingJsonFactory(this, null);
    }
    public String getFormatName() {
        return FORMAT_NAME_JSON;
    }
     public MatchStrength hasFormat(final InputAccessor inputAccessor) throws IOException {
        if (MappingJsonFactory.class == getClass()) {
            return this.hasJSONFormat(inputAccessor);
        }
        return null;
    }
}
