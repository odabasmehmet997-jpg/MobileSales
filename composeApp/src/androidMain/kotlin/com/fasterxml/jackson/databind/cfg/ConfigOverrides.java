package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ConfigOverrides implements Serializable {
    private static final long serialVersionUID = 1;
    protected JsonInclude.Value _defaultInclusion;
    protected Boolean _defaultLeniency;
    protected Boolean _defaultMergeable;
    protected JsonSetter.Value _defaultSetterInfo;
    protected Map<Class<?>, MutableConfigOverride> _overrides;
    protected VisibilityChecker<?> _visibilityChecker;
    public ConfigOverrides() {
        this(null, JsonInclude.Value.empty(), JsonSetter.Value.empty(), VisibilityChecker.Std.defaultInstance(), null, null);
    }
    protected ConfigOverrides(final Map<Class<?>, MutableConfigOverride> map, final JsonInclude.Value value, final JsonSetter.Value value2, final VisibilityChecker<?> visibilityChecker, final Boolean bool, final Boolean bool2) {
        _overrides = map;
        _defaultInclusion = value;
        _defaultSetterInfo = value2;
        _visibilityChecker = visibilityChecker;
        _defaultMergeable = bool;
        _defaultLeniency = bool2;
    }
    protected ConfigOverrides(final Map<Class<?>, MutableConfigOverride> map, final JsonInclude.Value value, final JsonSetter.Value value2, final VisibilityChecker<?> visibilityChecker, final Boolean bool) {
        this(map, value, value2, visibilityChecker, bool, null);
    }
    public ConfigOverrides copy() {
        final Map<Class<?>, MutableConfigOverride> map_newMap;
        if (null == this._overrides) {
            map_newMap = null;
        } else {
            map_newMap = this._newMap();
            for (final Map.Entry<Class<?>, MutableConfigOverride> entry : _overrides.entrySet()) {
                map_newMap.put(entry.getKey(), entry.getValue().copy());
            }
        }
        return new ConfigOverrides(map_newMap, _defaultInclusion, _defaultSetterInfo, _visibilityChecker, _defaultMergeable, _defaultLeniency);
    }
    public ConfigOverride findOverride(final Class<?> cls) {
        final Map<Class<?>, MutableConfigOverride> map = _overrides;
        if (null == map) {
            return null;
        }
        return map.get(cls);
    }
    public MutableConfigOverride findOrCreateOverride(final Class<?> cls) {
        if (null == this._overrides) {
            _overrides = this._newMap();
        }
        final MutableConfigOverride mutableConfigOverride = _overrides.get(cls);
        if (null != mutableConfigOverride) {
            return mutableConfigOverride;
        }
        final MutableConfigOverride mutableConfigOverride2 = new MutableConfigOverride();
        _overrides.put(cls, mutableConfigOverride2);
        return mutableConfigOverride2;
    }
    public JsonFormat.Value findFormatDefaults(final Class<?> cls) {
        final MutableConfigOverride mutableConfigOverride;
        final JsonFormat.Value format;
        final Map<Class<?>, MutableConfigOverride> map = _overrides;
        if (null != map && null != (mutableConfigOverride = map.get(cls)) && null != (format = mutableConfigOverride.getFormat())) {
            return !format.hasLenient() ? format.withLenient(_defaultLeniency) : format;
        }
        final Boolean bool = _defaultLeniency;
        if (null == bool) {
            return JsonFormat.Value.empty();
        }
        return JsonFormat.Value.forLeniency(bool.booleanValue());
    }
    public JsonInclude.Value getDefaultInclusion() {
        return _defaultInclusion;
    }
    public JsonSetter.Value getDefaultSetterInfo() {
        return _defaultSetterInfo;
    }
    public Boolean getDefaultMergeable() {
        return _defaultMergeable;
    }
    public Boolean getDefaultLeniency() {
        return _defaultLeniency;
    }
    public VisibilityChecker<?> getDefaultVisibility() {
        return _visibilityChecker;
    }
    public void setDefaultInclusion(final JsonInclude.Value value) {
        _defaultInclusion = value;
    }
    public void setDefaultSetterInfo(final JsonSetter.Value value) {
        _defaultSetterInfo = value;
    }
    public void setDefaultMergeable(final Boolean bool) {
        _defaultMergeable = bool;
    }
    public void setDefaultLeniency(final Boolean bool) {
        _defaultLeniency = bool;
    }
    public void setDefaultVisibility(final VisibilityChecker<?> visibilityChecker) {
        _visibilityChecker = visibilityChecker;
    }
    protected Map<Class<?>, MutableConfigOverride> _newMap() {
        return new HashMap();
    }
}
