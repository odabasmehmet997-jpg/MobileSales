package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class DefaultPrettyPrinter implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable {
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    private static final long serialVersionUID = 1;
    protected Indenter _arrayIndenter;
    protected transient int _nesting;
    protected String _objectFieldValueSeparatorWithSpaces;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected Separators _separators;
    protected boolean _spacesInObjectEntries;

    public interface Indenter {
        boolean isInline();

        void writeIndentation(JsonGenerator jsonGenerator, int i2) throws IOException;
    }

    public static class NopIndenter implements Indenter, Serializable {
        public static final NopIndenter instance = new NopIndenter();
        public boolean isInline() {
            return true;
        }
        public void writeIndentation(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        }
    }

    public DefaultPrettyPrinter() {
        this(DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(final String str) {
        this(null == str ? null : new SerializedString(str));
    }

    public DefaultPrettyPrinter(final SerializableString serializableString) {
        _arrayIndenter = FixedSpaceIndenter.instance;
        _objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        _spacesInObjectEntries = true;
        _rootSeparator = serializableString;
        this.withSeparators(DEFAULT_SEPARATORS);
    }

    public DefaultPrettyPrinter(final DefaultPrettyPrinter defaultPrettyPrinter) {
        this(defaultPrettyPrinter, defaultPrettyPrinter._rootSeparator);
    }

    public DefaultPrettyPrinter(final DefaultPrettyPrinter defaultPrettyPrinter, final SerializableString serializableString) {
        _arrayIndenter = FixedSpaceIndenter.instance;
        _objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        _spacesInObjectEntries = true;
        _arrayIndenter = defaultPrettyPrinter._arrayIndenter;
        _objectIndenter = defaultPrettyPrinter._objectIndenter;
        _spacesInObjectEntries = defaultPrettyPrinter._spacesInObjectEntries;
        _nesting = defaultPrettyPrinter._nesting;
        _separators = defaultPrettyPrinter._separators;
        _objectFieldValueSeparatorWithSpaces = defaultPrettyPrinter._objectFieldValueSeparatorWithSpaces;
        _rootSeparator = serializableString;
    }

    public DefaultPrettyPrinter withRootSeparator(final SerializableString serializableString) {
        final SerializableString serializableString2 = _rootSeparator;
        return (Objects.equals(serializableString, serializableString2)) ? this : new DefaultPrettyPrinter(this, serializableString);
    }

    public DefaultPrettyPrinter withRootSeparator(final String str) {
        return this.withRootSeparator(null == str ? null : new SerializedString(str));
    }

    public void indentArraysWith(Indenter indenter) {
        if (null == indenter) {
            indenter = NopIndenter.instance;
        }
        _arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter) {
        if (null == indenter) {
            indenter = NopIndenter.instance;
        }
        _objectIndenter = indenter;
    }

    public DefaultPrettyPrinter withArrayIndenter(Indenter indenter) {
        if (null == indenter) {
            indenter = NopIndenter.instance;
        }
        if (_arrayIndenter == indenter) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._arrayIndenter = indenter;
        return defaultPrettyPrinter;
    }

    public DefaultPrettyPrinter withObjectIndenter(Indenter indenter) {
        if (null == indenter) {
            indenter = NopIndenter.instance;
        }
        if (_objectIndenter == indenter) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._objectIndenter = indenter;
        return defaultPrettyPrinter;
    }

    public DefaultPrettyPrinter withSpacesInObjectEntries() {
        return this._withSpaces(true);
    }

    public DefaultPrettyPrinter withoutSpacesInObjectEntries() {
        return this._withSpaces(false);
    }

    protected DefaultPrettyPrinter _withSpaces(final boolean z) {
        if (_spacesInObjectEntries == z) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._spacesInObjectEntries = z;
        return defaultPrettyPrinter;
    }

    public DefaultPrettyPrinter withSeparators(final Separators separators) {
        _separators = separators;
        _objectFieldValueSeparatorWithSpaces = " " + separators.objectFieldValueSeparator() + " ";
        return this;
    }

    public DefaultPrettyPrinter createInstance() {
        if (DefaultPrettyPrinter.class != getClass()) {
            throw new IllegalStateException("Failed `createInstance()`: " + this.getClass().getName() + " does not override method; it has to");
        }
        return new DefaultPrettyPrinter(this);
    }

    public void writeRootValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        final SerializableString serializableString = _rootSeparator;
        if (null != serializableString) {
            jsonGenerator.writeRaw(serializableString);
        }
    }

    public void writeStartObject(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw('{');
        if (_objectIndenter.isInline()) {
            return;
        }
        _nesting++;
    }
    public void beforeObjectEntries(final JsonGenerator jsonGenerator) throws IOException {
        _objectIndenter.writeIndentation(jsonGenerator, _nesting);
    }
    public void writeObjectFieldValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        if (_spacesInObjectEntries) {
            jsonGenerator.writeRaw(_objectFieldValueSeparatorWithSpaces);
        } else {
            jsonGenerator.writeRaw(_separators.objectFieldValueSeparator());
        }
    }
    public void writeObjectEntrySeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(_separators.objectEntrySeparator());
        _objectIndenter.writeIndentation(jsonGenerator, _nesting);
    }

    public void writeEndObject(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        if (!_objectIndenter.isInline()) {
            _nesting--;
        }
        if (0 < i2) {
            _objectIndenter.writeIndentation(jsonGenerator, _nesting);
        } else {
            jsonGenerator.writeRaw(' ');
        }
        jsonGenerator.writeRaw('}');
    }

    public void writeStartArray(final JsonGenerator jsonGenerator) throws IOException {
        if (!_arrayIndenter.isInline()) {
            _nesting++;
        }
        jsonGenerator.writeRaw('[');
    }

    
    public void beforeArrayValues(final JsonGenerator jsonGenerator) throws IOException {
        _arrayIndenter.writeIndentation(jsonGenerator, _nesting);
    }

    
    public void writeArrayValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(_separators.arrayValueSeparator());
        _arrayIndenter.writeIndentation(jsonGenerator, _nesting);
    }

    
    public void writeEndArray(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        if (!_arrayIndenter.isInline()) {
            _nesting--;
        }
        if (0 < i2) {
            _arrayIndenter.writeIndentation(jsonGenerator, _nesting);
        } else {
            jsonGenerator.writeRaw(' ');
        }
        jsonGenerator.writeRaw(']');
    }

    public static class FixedSpaceIndenter extends NopIndenter {
        public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();
        public boolean isInline() {
            return true;
        }

        public void writeIndentation(final JsonGenerator jsonGenerator, final int i2) throws IOException {
            jsonGenerator.writeRaw(' ');
        }
    }
}
