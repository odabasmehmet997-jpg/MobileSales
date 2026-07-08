package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;
    public abstract void assign(Object obj) throws IOException;
    protected PropertyValue(final PropertyValue propertyValue, final Object obj) {
        next = propertyValue;
        value = obj;
    }
    static final class Regular extends PropertyValue {
        final SettableBeanProperty _property;

        public Regular(final PropertyValue propertyValue, final Object obj, final SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            _property = settableBeanProperty;
        }
        public void assign(final Object obj) throws IOException {
            try {
                _property.set(obj, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static final class Any extends PropertyValue {
        final SettableAnyProperty _property;
        final String _propertyName;

        public Any(final PropertyValue propertyValue, final Object obj, final SettableAnyProperty settableAnyProperty, final String str) {
            super(propertyValue, obj);
            _property = settableAnyProperty;
            _propertyName = str;
        }
        public void assign(final Object obj) throws IOException {
            _property.set(obj, _propertyName, value);
        }
    }
    static final class Map extends PropertyValue {
        final Object _key;

        public Map(final PropertyValue propertyValue, final Object obj, final Object obj2) {
            super(propertyValue, obj);
            _key = obj2;
        }
        public void assign(final Object obj) throws IOException {
            ((java.util.Map) obj).put(_key, value);
        }
    }
}
