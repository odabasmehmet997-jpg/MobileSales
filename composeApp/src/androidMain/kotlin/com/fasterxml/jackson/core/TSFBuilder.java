package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;

public abstract class TSFBuilder<F extends JsonFactory, B extends TSFBuilder<F, B>> {
    protected int _factoryFeatures;
    protected InputDecorator _inputDecorator;
    protected OutputDecorator _outputDecorator;
    protected int _streamReadFeatures;
    protected int _streamWriteFeatures;
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = JsonFactory.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected TSFBuilder() {
        _factoryFeatures = TSFBuilder.DEFAULT_FACTORY_FEATURE_FLAGS;
        _streamReadFeatures = TSFBuilder.DEFAULT_PARSER_FEATURE_FLAGS;
        _streamWriteFeatures = TSFBuilder.DEFAULT_GENERATOR_FEATURE_FLAGS;
        _inputDecorator = null;
        _outputDecorator = null;
    }
    protected TSFBuilder(final JsonFactory jsonFactory) {
        this(jsonFactory._factoryFeatures, jsonFactory._parserFeatures, jsonFactory._generatorFeatures);
    }
    protected TSFBuilder(final int i2, final int i3, final int i4) {
        _factoryFeatures = i2;
        _streamReadFeatures = i3;
        _streamWriteFeatures = i4;
    }
}
