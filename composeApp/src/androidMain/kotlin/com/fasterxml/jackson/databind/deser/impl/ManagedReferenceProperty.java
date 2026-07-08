package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
public final class ManagedReferenceProperty extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1;
    private final SettableBeanProperty _backProperty;
    private final boolean _isContainer;
    private final String _referenceName;
    public ManagedReferenceProperty(final SettableBeanProperty settableBeanProperty, final String str, final SettableBeanProperty settableBeanProperty2, final boolean z) {
        super(settableBeanProperty);
        _referenceName = str;
        _backProperty = settableBeanProperty2;
        _isContainer = z;
    }
    protected SettableBeanProperty withDelegate(final SettableBeanProperty settableBeanProperty) {
        throw new IllegalStateException("Should never try to reset delegate");
    }
    public void fixAccess(final DeserializationConfig deserializationConfig) {
        delegate.fixAccess(deserializationConfig);
        _backProperty.fixAccess(deserializationConfig);
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        this.set(obj, delegate.deserialize(jsonParser, deserializationContext));
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return this.setAndReturn(obj, this.deserialize(jsonParser, deserializationContext));
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        this.setAndReturn(obj, obj2);
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        if (null != obj2) {
            if (_isContainer) {
                if (obj2 instanceof Object[]) {
                    for (final Object obj3 : (Object[]) obj2) {
                        if (null != obj3) {
                            try {
                                _backProperty.set(obj3, obj);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else if (obj2 instanceof Collection) {
                    for (final Object obj4 : (Collection) obj2) {
                        if (null != obj4) {
                            try {
                                _backProperty.set(obj4, obj);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else if (obj2 instanceof Map) {
                    for (final Object obj5 : ((Map) obj2).values()) {
                        if (null != obj5) {
                            try {
                                _backProperty.set(obj5, obj);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + _referenceName + "'");
                }
            } else {
                try {
                    _backProperty.set(obj2, obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            return delegate.setAndReturn(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public AnnotatedMethod getSetter() {
        return null;
    }
}
