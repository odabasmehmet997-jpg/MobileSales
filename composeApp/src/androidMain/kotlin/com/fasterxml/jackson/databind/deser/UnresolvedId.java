package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.util.ClassUtil;
public class UnresolvedId {
    private final Object _id;
    private final JsonLocation _location;
    private final Class<?> _type;
    public UnresolvedId(final Object obj, final Class<?> cls, final JsonLocation jsonLocation) {
        _id = obj;
        _type = cls;
        _location = jsonLocation;
    }
    public String toString() {
        return String.format("Object id [%s] (for %s) at %s", _id, ClassUtil.nameOf(_type), _location);
    }
}
