package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;
public class UnrecognizedPropertyException extends PropertyBindingException {
    private static final long serialVersionUID = 1;

    public UnrecognizedPropertyException(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        super(jsonParser, str, jsonLocation, cls, str2, collection);
    }

    public UnrecognizedPropertyException(final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        super(str, jsonLocation, cls, str2, collection);
    }

    public static UnrecognizedPropertyException from(final JsonParser jsonParser, final Object obj, final String str, final Collection<Object> collection) {
        final Class<?> cls;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        final Class<?> cls2 = cls;
        final UnrecognizedPropertyException unrecognizedPropertyException = new UnrecognizedPropertyException(jsonParser, String.format("Unrecognized field \"%s\" (class %s), not marked as ignorable", str, cls2.getName()), jsonParser.getCurrentLocation(), cls2, str, collection);
        unrecognizedPropertyException.prependPath(obj, str);
        return unrecognizedPropertyException;
    }
}
