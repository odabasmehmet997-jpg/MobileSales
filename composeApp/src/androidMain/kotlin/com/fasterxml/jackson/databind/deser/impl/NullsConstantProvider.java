package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.Serializable;

public class NullsConstantProvider implements NullValueProvider, Serializable {
    private static final long serialVersionUID = 1;
    protected final AccessPattern _access;
    protected final Object _nullValue;
    private static final NullsConstantProvider SKIPPER = new NullsConstantProvider(null);
    private static final NullsConstantProvider NULLER = new NullsConstantProvider(null);
    protected NullsConstantProvider(final Object obj) {
        _nullValue = obj;
        _access = null == obj ? AccessPattern.ALWAYS_NULL : AccessPattern.CONSTANT;
    }
    public static NullsConstantProvider skipper() {
        return NullsConstantProvider.SKIPPER;
    }
    public static NullsConstantProvider nuller() {
        return NullsConstantProvider.NULLER;
    }
    public static NullsConstantProvider forValue(final Object obj) {
        if (null == obj) {
            return NullsConstantProvider.NULLER;
        }
        return new NullsConstantProvider(obj);
    }
    public static boolean isSkipper(final NullValueProvider nullValueProvider) {
        return nullValueProvider == NullsConstantProvider.SKIPPER;
    }
    public static boolean isNuller(final NullValueProvider nullValueProvider) {
        return nullValueProvider == NullsConstantProvider.NULLER;
    }
    public AccessPattern getNullAccessPattern() {
        return _access;
    }
    public Object getNullValue(final DeserializationContext deserializationContext) {
        return _nullValue;
    }
}
