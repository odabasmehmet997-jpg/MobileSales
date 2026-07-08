package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;


public abstract class MergingSettableBeanProperty extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMember _accessor;
    protected MergingSettableBeanProperty(final SettableBeanProperty settableBeanProperty, final AnnotatedMember annotatedMember) {
        super(settableBeanProperty);
        _accessor = annotatedMember;
    }
    protected MergingSettableBeanProperty(final MergingSettableBeanProperty mergingSettableBeanProperty, final SettableBeanProperty settableBeanProperty) {
        super(settableBeanProperty);
        _accessor = mergingSettableBeanProperty._accessor;
    }
    public static MergingSettableBeanProperty construct(final SettableBeanProperty settableBeanProperty, final AnnotatedMember annotatedMember) {
        return new MergingSettableBeanProperty(settableBeanProperty, annotatedMember) {
            public AnnotatedMethod getSetter() {
                return null;
            }
        };
    }
    protected SettableBeanProperty withDelegate(final SettableBeanProperty settableBeanProperty) {
        return new MergingSettableBeanProperty(settableBeanProperty, _accessor) {
            public AnnotatedMethod getSetter() {
                return null;
            }
        };
    }
    public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final Object objDeserializeWith;
        final Object value = _accessor.getValue(obj);
        if (null == value) {
            objDeserializeWith = delegate.deserialize(jsonParser, deserializationContext);
        } else {
            objDeserializeWith = delegate.deserializeWith(jsonParser, deserializationContext, value);
        }
        if (objDeserializeWith != value) {
            try {
                delegate.set(obj, objDeserializeWith);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final Object objDeserializeWith;
        final Object value = _accessor.getValue(obj);
        if (null == value) {
            objDeserializeWith = delegate.deserialize(jsonParser, deserializationContext);
        } else {
            objDeserializeWith = delegate.deserializeWith(jsonParser, deserializationContext, value);
        }
        try {
            return (objDeserializeWith == value || null == objDeserializeWith) ? obj : delegate.setAndReturn(obj, objDeserializeWith);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public void set(final Object obj, final Object obj2) throws IOException {
        if (null != obj2) {
            try {
                delegate.set(obj, obj2);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
        try {
            return null != obj2 ? delegate.setAndReturn(obj, obj2) : obj;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
