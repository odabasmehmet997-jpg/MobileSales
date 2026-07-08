package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;

public class PropertyMetadata implements Serializable {
    private static final long serialVersionUID = -1;
    protected Nulls _contentNulls;
    protected final String _defaultValue;
    protected final String _description;
    protected final Integer _index;
    protected final transient MergeInfo _mergeInfo;
    protected final Boolean _required;
    protected Nulls _valueNulls;
    public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null, null, null, null, null);
    public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null, null, null, null, null);
    public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null, null, null, null, null);
    public static final class MergeInfo {
        public final boolean fromDefaults;
        public final AnnotatedMember getter;

        private MergeInfo(final AnnotatedMember annotatedMember, final boolean z) {
            getter = annotatedMember;
            fromDefaults = z;
        }

        public static MergeInfo createForDefaults(final AnnotatedMember annotatedMember) {
            return new MergeInfo(annotatedMember, true);
        }

        public static MergeInfo createForTypeOverride(final AnnotatedMember annotatedMember) {
            return new MergeInfo(annotatedMember, false);
        }

        public static MergeInfo createForPropertyOverride(final AnnotatedMember annotatedMember) {
            return new MergeInfo(annotatedMember, false);
        }
    }
    protected PropertyMetadata(final Boolean bool, final String str, final Integer num, final String str2, final MergeInfo mergeInfo, final Nulls nulls, final Nulls nulls2) {
        _required = bool;
        _description = str;
        _index = num;
        _defaultValue = (null == str2 || str2.isEmpty()) ? null : str2;
        _mergeInfo = mergeInfo;
        _valueNulls = nulls;
        _contentNulls = nulls2;
    }
    public static PropertyMetadata construct(final Boolean bool, final String str, final Integer num, final String str2) {
        if (null != str || null != num || null != str2) {
            return new PropertyMetadata(bool, str, num, str2, null, null, null);
        }
        if (null == bool) {
            return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        }
        return bool.booleanValue() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
    }
    public static PropertyMetadata construct(final boolean z, final String str, final Integer num, final String str2) {
        if (null == str && null == num && null == str2) {
            return z ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
        }
        return new PropertyMetadata(Boolean.valueOf(z), str, num, str2, null, null, null);
    }
    protected Object readResolve() {
        if (null != this._description || null != this._index || null != this._defaultValue || null != this._mergeInfo || null != this._valueNulls || null != this._contentNulls) {
            return this;
        }
        final Boolean bool = _required;
        if (null == bool) {
            return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        }
        return bool.booleanValue() ? PropertyMetadata.STD_REQUIRED : PropertyMetadata.STD_OPTIONAL;
    }
    public PropertyMetadata withDescription(final String str) {
        return new PropertyMetadata(_required, str, _index, _defaultValue, _mergeInfo, _valueNulls, _contentNulls);
    }
    public PropertyMetadata withMergeInfo(final MergeInfo mergeInfo) {
        return new PropertyMetadata(_required, _description, _index, _defaultValue, mergeInfo, _valueNulls, _contentNulls);
    }
    public PropertyMetadata withNulls(final Nulls nulls, final Nulls nulls2) {
        return new PropertyMetadata(_required, _description, _index, _defaultValue, _mergeInfo, nulls, nulls2);
    }
    public PropertyMetadata withDefaultValue(String str) {
        if (null == str || str.isEmpty()) {
            if (null == _defaultValue) {
                return this;
            }
            str = null;
        } else if (str.equals(_defaultValue)) {
            return this;
        }
        return new PropertyMetadata(_required, _description, _index, str, _mergeInfo, _valueNulls, _contentNulls);
    }
    public PropertyMetadata withIndex(final Integer num) {
        return new PropertyMetadata(_required, _description, num, _defaultValue, _mergeInfo, _valueNulls, _contentNulls);
    }
    public PropertyMetadata withRequired(final Boolean bool) {
        if (null == bool) {
            if (null == _required) {
                return this;
            }
        } else if (bool.equals(_required)) {
            return this;
        }
        return new PropertyMetadata(bool, _description, _index, _defaultValue, _mergeInfo, _valueNulls, _contentNulls);
    }
    public String getDescription() {
        return _description;
    }
    public String getDefaultValue() {
        return _defaultValue;
    }
    public boolean hasDefaultValue() {
        return null != _defaultValue;
    }
    public boolean isRequired() {
        final Boolean bool = _required;
        return null != bool && bool;
    }
    public Boolean getRequired() {
        return _required;
    }
    public Integer getIndex() {
        return _index;
    }
    public boolean hasIndex() {
        return null != _index;
    }
    public MergeInfo getMergeInfo() {
        return _mergeInfo;
    }
    public Nulls getValueNulls() {
        return _valueNulls;
    }
    public Nulls getContentNulls() {
        return _contentNulls;
    }
}
