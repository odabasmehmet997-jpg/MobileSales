package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;

public class JsonFactoryBuilder extends TSFBuilder<JsonFactory, JsonFactoryBuilder> {
    protected CharacterEscapes _characterEscapes;
    protected int _maximumNonEscapedChar;
    protected char _quoteChar;
    protected SerializableString _rootValueSeparator;
    public JsonFactoryBuilder() {
        _quoteChar = JsonFactory.DEFAULT_QUOTE_CHAR;
        _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        _maximumNonEscapedChar = 0;
    }
    public JsonFactoryBuilder(final JsonFactory jsonFactory) {
        super(jsonFactory);
        _quoteChar = JsonFactory.DEFAULT_QUOTE_CHAR;
        _characterEscapes = jsonFactory.getCharacterEscapes();
        _rootValueSeparator = jsonFactory._rootValueSeparator;
        _maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
    }
}
