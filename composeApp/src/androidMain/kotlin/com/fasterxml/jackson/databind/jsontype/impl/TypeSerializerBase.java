package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    public String getPropertyName() {
        return null;
    }

    protected void handleMissingId(final Object obj) {
    }

    protected TypeSerializerBase(final TypeIdResolver typeIdResolver, final BeanProperty beanProperty) {
        _idResolver = typeIdResolver;
        _property = beanProperty;
    }

    public WritableTypeId writeTypePrefix(final JsonGenerator jsonGenerator, final WritableTypeId writableTypeId) throws IOException {
        this._generateTypeId(writableTypeId);
        return jsonGenerator.writeTypePrefix(writableTypeId);
    }

    public WritableTypeId writeTypeSuffix(final JsonGenerator jsonGenerator, final WritableTypeId writableTypeId) throws IOException {
        return jsonGenerator.writeTypeSuffix(writableTypeId);
    }

    protected void _generateTypeId(final WritableTypeId writableTypeId) {
        final String strIdFromValueAndType;
        if (null == writableTypeId.f798id) {
            final Object obj = writableTypeId.forValue;
            final Class<?> cls = writableTypeId.forValueType;
            if (null == cls) {
                strIdFromValueAndType = this.idFromValue(obj);
            } else {
                strIdFromValueAndType = this.idFromValueAndType(obj, cls);
            }
            writableTypeId.f798id = strIdFromValueAndType;
        }
    }

    protected String idFromValue(final Object obj) {
        final String strIdFromValue = _idResolver.idFromValue(obj);
        if (null == strIdFromValue) {
            this.handleMissingId(obj);
        }
        return strIdFromValue;
    }

    protected String idFromValueAndType(final Object obj, final Class<?> cls) {
        final String strIdFromValueAndType = _idResolver.idFromValueAndType(obj, cls);
        if (null == strIdFromValueAndType) {
            this.handleMissingId(obj);
        }
        return strIdFromValueAndType;
    }
}
