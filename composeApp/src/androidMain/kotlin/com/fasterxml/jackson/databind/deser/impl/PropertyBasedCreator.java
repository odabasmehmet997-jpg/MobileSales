package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;

import java.io.IOException;
import java.util.*;

public final class PropertyBasedCreator {
    private final SettableBeanProperty[] _allProperties;
    private final int _propertyCount;
    private final HashMap<String, SettableBeanProperty> _propertyLookup;
    private final ValueInstantiator _valueInstantiator;
    private PropertyBasedCreator(final DeserializationContext deserializationContext, final ValueInstantiator valueInstantiator, final SettableBeanProperty[] settableBeanPropertyArr, final boolean z, final boolean z2) {
        _valueInstantiator = valueInstantiator;
        if (z) {
            _propertyLookup = CaseInsensitiveMap.construct(deserializationContext.getConfig().getLocale());
        } else {
            _propertyLookup = new HashMap<>();
        }
        final int length = settableBeanPropertyArr.length;
        _propertyCount = length;
        _allProperties = new SettableBeanProperty[length];
        if (z2) {
            final DeserializationConfig config = deserializationContext.getConfig();
            for (final SettableBeanProperty settableBeanProperty : settableBeanPropertyArr) {
                if (!settableBeanProperty.isIgnorable()) {
                    final List<PropertyName> listFindAliases = settableBeanProperty.findAliases(config);
                    if (!listFindAliases.isEmpty()) {
                        final Iterator<PropertyName> it = listFindAliases.iterator();
                        while (it.hasNext()) {
                            _propertyLookup.put(it.next().getSimpleName(), settableBeanProperty);
                        }
                    }
                }
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            final SettableBeanProperty settableBeanProperty2 = settableBeanPropertyArr[i2];
            _allProperties[i2] = settableBeanProperty2;
            if (!settableBeanProperty2.isIgnorable()) {
                _propertyLookup.put(settableBeanProperty2.getName(), settableBeanProperty2);
            }
        }
    }
    public static PropertyBasedCreator construct(final DeserializationContext deserializationContext, final ValueInstantiator valueInstantiator, final SettableBeanProperty[] settableBeanPropertyArr, final BeanPropertyMap beanPropertyMap) throws JsonMappingException {
        final int length = settableBeanPropertyArr.length;
        final SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[length];
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty settableBeanPropertyWithValueDeserializer = settableBeanPropertyArr[i2];
            if (!settableBeanPropertyWithValueDeserializer.hasValueDeserializer() && !settableBeanPropertyWithValueDeserializer.isInjectionOnly()) {
                settableBeanPropertyWithValueDeserializer = settableBeanPropertyWithValueDeserializer.withValueDeserializer(deserializationContext.findContextualValueDeserializer(settableBeanPropertyWithValueDeserializer.getType(), settableBeanPropertyWithValueDeserializer));
            }
            settableBeanPropertyArr2[i2] = settableBeanPropertyWithValueDeserializer;
        }
        return new PropertyBasedCreator(deserializationContext, valueInstantiator, settableBeanPropertyArr2, beanPropertyMap.isCaseInsensitive(), true);
    }
    public static PropertyBasedCreator construct(final DeserializationContext deserializationContext, final ValueInstantiator valueInstantiator, final SettableBeanProperty[] settableBeanPropertyArr, final boolean z) throws JsonMappingException {
        final int length = settableBeanPropertyArr.length;
        final SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[length];
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty settableBeanPropertyWithValueDeserializer = settableBeanPropertyArr[i2];
            if (!settableBeanPropertyWithValueDeserializer.hasValueDeserializer()) {
                settableBeanPropertyWithValueDeserializer = settableBeanPropertyWithValueDeserializer.withValueDeserializer(deserializationContext.findContextualValueDeserializer(settableBeanPropertyWithValueDeserializer.getType(), settableBeanPropertyWithValueDeserializer));
            }
            settableBeanPropertyArr2[i2] = settableBeanPropertyWithValueDeserializer;
        }
        return new PropertyBasedCreator(deserializationContext, valueInstantiator, settableBeanPropertyArr2, z, false);
    }
    public Collection<SettableBeanProperty> properties() {
        return _propertyLookup.values();
    }
    public SettableBeanProperty findCreatorProperty(final String str) {
        return _propertyLookup.get(str);
    }
    public SettableBeanProperty findCreatorProperty(final int i2) {
        for (final SettableBeanProperty settableBeanProperty : _propertyLookup.values()) {
            if (settableBeanProperty.getPropertyIndex() == i2) {
                return settableBeanProperty;
            }
        }
        return null;
    }
    public PropertyValueBuffer startBuilding(final JsonParser jsonParser, final DeserializationContext deserializationContext, final ObjectIdReader objectIdReader) {
        return new PropertyValueBuffer(jsonParser, deserializationContext, _propertyCount, objectIdReader);
    }
    public Object build(final DeserializationContext deserializationContext, final PropertyValueBuffer propertyValueBuffer) throws IOException {
        Object objCreateFromObjectWith = _valueInstantiator.createFromObjectWith(deserializationContext, _allProperties, propertyValueBuffer);
        if (null != objCreateFromObjectWith) {
            objCreateFromObjectWith = propertyValueBuffer.handleIdValue(deserializationContext, objCreateFromObjectWith);
            for (PropertyValue propertyValueBuffered = propertyValueBuffer.buffered(); null != propertyValueBuffered; propertyValueBuffered = propertyValueBuffered.next) {
                propertyValueBuffered.assign(objCreateFromObjectWith);
            }
        }
        return objCreateFromObjectWith;
    }
    static class CaseInsensitiveMap extends HashMap<String, SettableBeanProperty> {
        private static final long serialVersionUID = 1;
        protected final Locale _locale;
        public CaseInsensitiveMap() {
            this(Locale.getDefault());
        }
        public CaseInsensitiveMap(final Locale locale) {
            _locale = locale;
        }
        public static CaseInsensitiveMap construct(final Locale locale) {
            return new CaseInsensitiveMap(locale);
        }
        public SettableBeanProperty get(final Object obj) {
            return super.get(((String) obj).toLowerCase(_locale));
        }
        public SettableBeanProperty put(final String str, final SettableBeanProperty settableBeanProperty) {
            return super.put((CaseInsensitiveMap) str.toLowerCase(_locale), (String) settableBeanProperty);
        }
    }
}
