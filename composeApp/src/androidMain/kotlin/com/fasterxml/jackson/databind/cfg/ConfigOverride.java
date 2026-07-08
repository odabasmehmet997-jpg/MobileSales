package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.*;

public abstract class ConfigOverride {
    protected JsonFormat.Value _format;
    protected JsonIgnoreProperties.Value _ignorals;
    protected JsonInclude.Value _include;
    protected JsonInclude.Value _includeAsProperty;
    protected Boolean _isIgnoredType;
    protected Boolean _mergeable;
    protected JsonSetter.Value _setterInfo;
    protected JsonAutoDetect.Value _visibility;
    protected ConfigOverride() {
    }
    protected ConfigOverride(final ConfigOverride configOverride) {
        _format = configOverride._format;
        _include = configOverride._include;
        _includeAsProperty = configOverride._includeAsProperty;
        _ignorals = configOverride._ignorals;
        _setterInfo = configOverride._setterInfo;
        _visibility = configOverride._visibility;
        _isIgnoredType = configOverride._isIgnoredType;
        _mergeable = configOverride._mergeable;
    }
    public static ConfigOverride empty() {
        return Empty.INSTANCE;
    }
    public JsonFormat.Value getFormat() {
        return _format;
    }
    public JsonInclude.Value getInclude() {
        return _include;
    }
    public JsonInclude.Value getIncludeAsProperty() {
        return _includeAsProperty;
    }
    public JsonIgnoreProperties.Value getIgnorals() {
        return _ignorals;
    }
    public Boolean getIsIgnoredType() {
        return _isIgnoredType;
    }
    public JsonSetter.Value getSetterInfo() {
        return _setterInfo;
    }
    public JsonAutoDetect.Value getVisibility() {
        return _visibility;
    }
    public Boolean getMergeable() {
        return _mergeable;
    }
    static final class Empty extends ConfigOverride {
        static final Empty INSTANCE = new Empty();

        private Empty() {
        }
    }
}
