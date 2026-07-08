package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter extends TokenFilter {
    protected final JsonPointer _pathToMatch;
    public TokenFilter filterStartArray() {
        return this;
    }
    public TokenFilter filterStartObject() {
        return this;
    }
    public JsonPointerBasedFilter(final String str) {
        this(JsonPointer.compile(str));
    }
    public JsonPointerBasedFilter(final JsonPointer jsonPointer) {
        _pathToMatch = jsonPointer;
    }
    public TokenFilter includeElement(final int i2) {
        final JsonPointer jsonPointerMatchElement = _pathToMatch.matchElement(i2);
        if (null == jsonPointerMatchElement) {
            return null;
        }
        if (jsonPointerMatchElement.matches()) {
            return INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(jsonPointerMatchElement);
    }
    public TokenFilter includeProperty(final String str) {
        final JsonPointer jsonPointerMatchProperty = _pathToMatch.matchProperty(str);
        if (null == jsonPointerMatchProperty) {
            return null;
        }
        if (jsonPointerMatchProperty.matches()) {
            return INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(jsonPointerMatchProperty);
    }
    protected boolean _includeScalar() {
        return _pathToMatch.matches();
    }
    public String toString() {
        return "[JsonPointerFilter at: " + _pathToMatch + "]";
    }
}
