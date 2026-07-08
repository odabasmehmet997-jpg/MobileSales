package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;
public abstract class StreamReadException extends JsonProcessingException {
    static final long serialVersionUID = 1;
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;
    public abstract StreamReadException withParser(JsonParser jsonParser);
    public abstract StreamReadException withRequestPayload(RequestPayload requestPayload);
    protected StreamReadException(final JsonParser jsonParser, final String str) {
        super(str, null == jsonParser ? null : jsonParser.getCurrentLocation());
        _processor = jsonParser;
    }
    protected StreamReadException(final JsonParser jsonParser, final String str, final Throwable th) {
        super(str, null == jsonParser ? null : jsonParser.getCurrentLocation(), th);
        _processor = jsonParser;
    }
    protected StreamReadException(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation) {
        super(str, jsonLocation, null);
        _processor = jsonParser;
    }
    protected StreamReadException(final String str, final JsonLocation jsonLocation, final Throwable th) {
        super(str);
        if (null != th) {
            this.initCause(th);
        }
        _location = jsonLocation;
    }
    public JsonParser getProcessor() {
        return _processor;
    }
    public RequestPayload getRequestPayload() {
        return _requestPayload;
    }
    public String getRequestPayloadAsString() {
        final RequestPayload requestPayload = _requestPayload;
        if (null != requestPayload) {
            return requestPayload.toString();
        }
        return null;
    }
    public String getMessage() {
        final String message = super.getMessage();
        if (null == this._requestPayload) {
            return message;
        }
        return message + "\nRequest payload : " + _requestPayload;
    }
}
