package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnwrappedPropertyHandler {
    protected final List<SettableBeanProperty> _properties;
    public UnwrappedPropertyHandler() {
        _properties = new ArrayList();
    }
    protected UnwrappedPropertyHandler(final List<SettableBeanProperty> list) {
        _properties = list;
    }
    public void addProperty(final SettableBeanProperty settableBeanProperty) {
        _properties.add(settableBeanProperty);
    }
    public UnwrappedPropertyHandler renameAll(final NameTransformer nameTransformer) {
        JsonDeserializer<Object> jsonDeserializerUnwrappingDeserializer;
        final ArrayList arrayList = new ArrayList(_properties.size());
        for (final SettableBeanProperty settableBeanProperty : _properties) {
            SettableBeanProperty settableBeanPropertyWithSimpleName = settableBeanProperty.withSimpleName(nameTransformer.transform(settableBeanProperty.getName()));
            final JsonDeserializer<Object> valueDeserializer = settableBeanPropertyWithSimpleName.getValueDeserializer();
            if (null != valueDeserializer && (jsonDeserializerUnwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer)) != valueDeserializer) {
                settableBeanPropertyWithSimpleName = settableBeanPropertyWithSimpleName.withValueDeserializer(jsonDeserializerUnwrappingDeserializer);
            }
            arrayList.add(settableBeanPropertyWithSimpleName);
        }
        return new UnwrappedPropertyHandler(arrayList);
    }
    public Object processUnwrapped(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final TokenBuffer tokenBuffer) throws IOException {
        final int size = _properties.size();
        for (int i2 = 0; i2 < size; i2++) {
            final SettableBeanProperty settableBeanProperty = _properties.get(i2);
            final JsonParser jsonParserAsParser = tokenBuffer.asParser();
            jsonParserAsParser.nextToken();
            settableBeanProperty.deserializeAndSet(jsonParserAsParser, deserializationContext, obj);
        }
        return obj;
    }
}
