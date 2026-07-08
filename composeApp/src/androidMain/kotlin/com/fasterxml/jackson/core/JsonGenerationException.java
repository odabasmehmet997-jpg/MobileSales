package com.fasterxml.jackson.core;

public class JsonGenerationException extends JsonProcessingException {
    private static final long serialVersionUID = 123;
    protected transient JsonGenerator _processor;
    public JsonGenerationException(final Throwable th) {
        super(th);
    }
    public JsonGenerationException(final String str) {
        super(str, (JsonLocation) null);
    }
    public JsonGenerationException(final String str, final Throwable th) {
        super(str, null, th);
    }
    public JsonGenerationException(final Throwable th, final JsonGenerator jsonGenerator) {
        super(th);
        _processor = jsonGenerator;
    }
    public JsonGenerationException(final String str, final JsonGenerator jsonGenerator) {
        super(str, (JsonLocation) null);
        _processor = jsonGenerator;
    }
    public JsonGenerationException(final String str, final Throwable th, final JsonGenerator jsonGenerator) {
        super(str, null, th);
        _processor = jsonGenerator;
    }
    public JsonGenerationException withGenerator(final JsonGenerator jsonGenerator) {
        _processor = jsonGenerator;
        return this;
    }
    public JsonGenerator getProcessor() {
        return _processor;
    }
}
