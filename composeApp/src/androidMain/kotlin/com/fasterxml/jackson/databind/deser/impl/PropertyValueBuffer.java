package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;
import java.util.BitSet;

public class PropertyValueBuffer {
    protected PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    protected Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    protected int _paramsNeeded;
    protected int _paramsSeen;
    protected final BitSet _paramsSeenBig;
    protected final JsonParser _parser;
    public PropertyValueBuffer(final JsonParser jsonParser, final DeserializationContext deserializationContext, final int i2, final ObjectIdReader objectIdReader) {
        _parser = jsonParser;
        _context = deserializationContext;
        _paramsNeeded = i2;
        _objectIdReader = objectIdReader;
        _creatorParameters = new Object[i2];
        if (32 > i2) {
            _paramsSeenBig = null;
        } else {
            _paramsSeenBig = new BitSet();
        }
    }
    public Object[] getParameters(final SettableBeanProperty[] settableBeanPropertyArr) throws JsonMappingException {
        if (0 < this._paramsNeeded) {
            if (null == this._paramsSeenBig) {
                int i2 = _paramsSeen;
                final int length = _creatorParameters.length;
                int i3 = 0;
                while (i3 < length) {
                    if (0 == (i2 & 1)) {
                        _creatorParameters[i3] = this._findMissing(settableBeanPropertyArr[i3]);
                    }
                    i3++;
                    i2 >>= 1;
                }
            } else {
                final int length2 = _creatorParameters.length;
                int i4 = 0;
                while (true) {
                    final int iNextClearBit = _paramsSeenBig.nextClearBit(i4);
                    if (iNextClearBit >= length2) {
                        break;
                    }
                    _creatorParameters[iNextClearBit] = this._findMissing(settableBeanPropertyArr[iNextClearBit]);
                    i4 = iNextClearBit + 1;
                }
            }
        }
        if (_context.isEnabled(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)) {
            for (int i5 = 0; i5 < settableBeanPropertyArr.length; i5++) {
                if (null == this._creatorParameters[i5]) {
                    final SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i5];
                    _context.reportInputMismatch(settableBeanProperty, "Null value for creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES` enabled", settableBeanProperty.getName(), Integer.valueOf(settableBeanPropertyArr[i5].getCreatorIndex()));
                }
            }
        }
        return _creatorParameters;
    }
    protected Object _findMissing(final SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        if (null != settableBeanProperty.getInjectableValueId()) {
            return _context.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, null);
        }
        if (settableBeanProperty.isRequired()) {
            _context.reportInputMismatch(settableBeanProperty, "Missing required creator property '%s' (index %d)", settableBeanProperty.getName(), Integer.valueOf(settableBeanProperty.getCreatorIndex()));
        }
        if (_context.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)) {
            _context.reportInputMismatch(settableBeanProperty, "Missing creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES` enabled", settableBeanProperty.getName(), Integer.valueOf(settableBeanProperty.getCreatorIndex()));
        }
        try {
            final Object nullValue = settableBeanProperty.getNullValueProvider().getNullValue(_context);
            return null != nullValue ? nullValue : settableBeanProperty.getValueDeserializer().getNullValue(_context);
        } catch (final JsonMappingException e2) {
            final AnnotatedMember member = settableBeanProperty.getMember();
            if (null != member) {
                e2.prependPath(member.getDeclaringClass(), settableBeanProperty.getName());
            }
            throw e2;
        }
    }
    public boolean readIdProperty(final String str) throws IOException {
        final ObjectIdReader objectIdReader = _objectIdReader;
        if (null == objectIdReader || !str.equals(objectIdReader.propertyName.getSimpleName())) {
            return false;
        }
        _idValue = _objectIdReader.readObjectReference(_parser, _context);
        return true;
    }
    public Object handleIdValue(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final ObjectIdReader objectIdReader = _objectIdReader;
        if (null != objectIdReader) {
            final Object obj2 = _idValue;
            if (null != obj2) {
                deserializationContext.findObjectId(obj2, objectIdReader.generator, objectIdReader.resolver).bindItem(obj);
                final SettableBeanProperty settableBeanProperty = _objectIdReader.idProperty;
                if (null != settableBeanProperty) {
                    return settableBeanProperty.setAndReturn(obj, _idValue);
                }
            } else {
                deserializationContext.reportUnresolvedObjectId(objectIdReader, obj);
            }
        }
        return obj;
    }
    protected PropertyValue buffered() {
        return _buffered;
    }
    public boolean assignParameter(final SettableBeanProperty settableBeanProperty, final Object obj) {
        final int creatorIndex = settableBeanProperty.getCreatorIndex();
        _creatorParameters[creatorIndex] = obj;
        final BitSet bitSet = _paramsSeenBig;
        if (null == bitSet) {
            final int i2 = _paramsSeen;
            final int i3 = (1 << creatorIndex) | i2;
            if (i2 != i3) {
                _paramsSeen = i3;
                final int i4 = _paramsNeeded - 1;
                _paramsNeeded = i4;
                if (0 >= i4) {
                    return null == this._objectIdReader || null != this._idValue;
                }
            }
        } else if (!bitSet.get(creatorIndex)) {
            _paramsSeenBig.set(creatorIndex);
            _paramsNeeded--;
        }
        return false;
    }
    public void bufferProperty(final SettableBeanProperty settableBeanProperty, final Object obj) {
        _buffered = new PropertyValue.Regular(_buffered, obj, settableBeanProperty);
    }
    public void bufferAnyProperty(final SettableAnyProperty settableAnyProperty, final String str, final Object obj) {
        _buffered = new PropertyValue.Any(_buffered, obj, settableAnyProperty, str);
    }
    public void bufferMapProperty(final Object obj, final Object obj2) {
        _buffered = new PropertyValue.Map(_buffered, obj2, obj);
    }
}
