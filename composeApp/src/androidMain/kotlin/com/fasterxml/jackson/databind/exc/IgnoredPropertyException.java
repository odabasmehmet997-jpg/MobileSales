package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;

public class IgnoredPropertyException extends PropertyBindingException {
    private static final long serialVersionUID = 1;

    public IgnoredPropertyException(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        super(jsonParser, str, jsonLocation, cls, str2, collection);
    }

    @Deprecated
    public IgnoredPropertyException(final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        super(str, jsonLocation, cls, str2, collection);
    }

    public static IgnoredPropertyException from(final JsonParser jsonParser, final Object obj, final String str, final Collection<Object> collection) {
        final Class<?> cls;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        final Class<?> cls2 = cls;
        final IgnoredPropertyException ignoredPropertyException = new IgnoredPropertyException(jsonParser, String.format("Ignored field \"%s\" (class %s) encountered; mapper configured not to allow this", str, cls2.getName()), jsonParser.getCurrentLocation(), cls2, str, collection);
        ignoredPropertyException.prependPath(obj, str);
        return ignoredPropertyException;
    }
}
