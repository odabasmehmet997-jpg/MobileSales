package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import java.io.IOException;
import java.io.Serializable;

public class MinimalPrettyPrinter implements PrettyPrinter, Serializable {
    private static final long serialVersionUID = 1;
    protected String _rootValueSeparator;
    protected Separators _separators;
    public void beforeArrayValues(final JsonGenerator jsonGenerator) throws IOException {
    }
    public void beforeObjectEntries(final JsonGenerator jsonGenerator) throws IOException {
    }

    public MinimalPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR.toString());
    }

    public MinimalPrettyPrinter(final String str) {
        _rootValueSeparator = str;
        _separators = DEFAULT_SEPARATORS;
    }

    public void setRootValueSeparator(final String str) {
        _rootValueSeparator = str;
    }

    public MinimalPrettyPrinter setSeparators(final Separators separators) {
        _separators = separators;
        return this;
    }
    public void writeRootValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        final String str = _rootValueSeparator;
        if (null != str) {
            jsonGenerator.writeRaw(str);
        }
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartObject(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw('{');
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectFieldValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(_separators.objectFieldValueSeparator());
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectEntrySeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(_separators.objectEntrySeparator());
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndObject(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        jsonGenerator.writeRaw('}');
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartArray(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw('[');
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeArrayValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(_separators.arrayValueSeparator());
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndArray(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        jsonGenerator.writeRaw(']');
    }
}
