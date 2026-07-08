package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class SequenceWriter implements Closeable, Flushable {
    protected final boolean _cfgCloseCloseable;
    protected final boolean _cfgFlush;
    protected final boolean _closeGenerator;
    protected boolean _closed;
    protected final SerializationConfig _config;
    protected PropertySerializerMap _dynamicSerializers;
    protected final JsonGenerator _generator;
    protected boolean _openArray;
    protected final DefaultSerializerProvider _provider;
    protected final JsonSerializer<Object> _rootSerializer;
    protected final TypeSerializer _typeSerializer;
    public SequenceWriter(final DefaultSerializerProvider defaultSerializerProvider, final JsonGenerator jsonGenerator, final boolean z, final ObjectWriter.Prefetch prefetch) throws IOException {
        _provider = defaultSerializerProvider;
        _generator = jsonGenerator;
        _closeGenerator = z;
        _rootSerializer = prefetch.getValueSerializer();
        _typeSerializer = prefetch.getTypeSerializer();
        final SerializationConfig config = defaultSerializerProvider.getConfig();
        _config = config;
        _cfgFlush = config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
        _cfgCloseCloseable = config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE);
        _dynamicSerializers = PropertySerializerMap.emptyForRootValues();
    }
    public SequenceWriter init(final boolean z) throws IOException {
        if (z) {
            _generator.writeStartArray();
            _openArray = true;
        }
        return this;
    }
    public void flush() throws IOException {
        if (_closed) {
            return;
        }
        _generator.flush();
    }
    public void close() throws IOException {
        if (_closed) {
            return;
        }
        _closed = true;
        if (_openArray) {
            _openArray = false;
            _generator.writeEndArray();
        }
        if (_closeGenerator) {
            _generator.close();
        }
    }
}
