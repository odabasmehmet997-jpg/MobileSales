package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

public class MutableConfigOverride extends ConfigOverride implements Serializable {
    private static final long serialVersionUID = 1;
    public MutableConfigOverride() {
    }
    protected MutableConfigOverride(final MutableConfigOverride mutableConfigOverride) {
        super(mutableConfigOverride);
    }
    public MutableConfigOverride copy() {
        return new MutableConfigOverride(this);
    }
    public MutableConfigOverride setFormat(final JsonFormat.Value value) {
        _format = value;
        return this;
    }
    public MutableConfigOverride setInclude(final JsonInclude.Value value) {
        _include = value;
        return this;
    }
    public MutableConfigOverride setIncludeAsProperty(final JsonInclude.Value value) {
        _includeAsProperty = value;
        return this;
    }
    public MutableConfigOverride setIgnorals(final JsonIgnoreProperties.Value value) {
        _ignorals = value;
        return this;
    }
    public MutableConfigOverride setIsIgnoredType(final Boolean bool) {
        _isIgnoredType = bool;
        return this;
    }
    public MutableConfigOverride setSetterInfo(final JsonSetter.Value value) {
        _setterInfo = value;
        return this;
    }
    public MutableConfigOverride setVisibility(final JsonAutoDetect.Value value) {
        _visibility = value;
        return this;
    }
    public MutableConfigOverride setMergeable(final Boolean bool) {
        _mergeable = bool;
        return this;
    }
}
