package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;

public class TokenFilter {
    public static final TokenFilter INCLUDE_ALL = new TokenFilter();
    public enum Inclusion {
        ONLY_INCLUDE_ALL,
        INCLUDE_ALL_AND_PATH,
        INCLUDE_NON_NULL
    }
    protected boolean _includeScalar() {
        return true;
    }
    public void filterFinishArray() {
    }
    public TokenFilter filterStartArray() {
        return this;
    }
    public TokenFilter filterStartObject() {
        return this;
    }
    public TokenFilter includeElement(final int i2) {
        return this;
    }
    public TokenFilter includeProperty(final String str) {
        return this;
    }
    public TokenFilter includeRootValue(final int i2) {
        return this;
    }
    protected TokenFilter() {
    }
    public boolean includeValue(final JsonParser jsonParser) throws IOException {
        return this._includeScalar();
    }
    public String toString() {
        if (this == TokenFilter.INCLUDE_ALL) {
            return "TokenFilter.INCLUDE_ALL";
        }
        return super.toString();
    }
}
