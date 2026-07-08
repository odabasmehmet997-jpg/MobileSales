package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.RequestPayload;
 
public class InputCoercionException extends StreamReadException {
    private static final long serialVersionUID = 1;
    protected final JsonToken _inputType;
    protected final Class<?> _targetType;
    public InputCoercionException(final JsonParser jsonParser, final String str, final JsonToken jsonToken, final Class<?> cls) {
        super(jsonParser, str);
        _inputType = jsonToken;
        _targetType = cls;
    }
    public InputCoercionException withParser(final JsonParser jsonParser) {
        _processor = jsonParser;
        return this;
    }
    public InputCoercionException withRequestPayload(final RequestPayload requestPayload) {
        _requestPayload = requestPayload;
        return this;
    }
    public JsonToken getInputType() {
        return _inputType;
    }
    public Class<?> getTargetType() {
        return _targetType;
    }
}
