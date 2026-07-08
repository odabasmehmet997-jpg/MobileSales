package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;

public class PropertyName implements Serializable {
    private static final String _NO_NAME = "";
    private static final String _USE_DEFAULT = "";
    private static final long serialVersionUID = 1;
    protected SerializableString _encodedSimple;
    protected final String _namespace;
    protected final String _simpleName;
    public static final PropertyName USE_DEFAULT = new PropertyName("", null);
    public static final PropertyName NO_NAME = new PropertyName("", null);
    public PropertyName(final String str) {
        this(str, null);
    }
    public PropertyName(final String str, final String str2) {
        _simpleName = ClassUtil.nonNullString(str);
        _namespace = str2;
    }
    protected Object readResolve() {
        final String str;
        return (null == this._namespace && (null == (str = this._simpleName) || "".equals(str))) ? PropertyName.USE_DEFAULT : this;
    }
    public static PropertyName construct(final String str) {
        if (null == str || str.isEmpty()) {
            return PropertyName.USE_DEFAULT;
        }
        return new PropertyName(InternCache.instance.intern(str), null);
    }
    public static PropertyName construct(String str, final String str2) {
        if (null == str) {
            str = "";
        }
        if (null == str2 && str.isEmpty()) {
            return PropertyName.USE_DEFAULT;
        }
        return new PropertyName(InternCache.instance.intern(str), str2);
    }
    public PropertyName internSimpleName() {
        final String strIntern;
        return (_simpleName.isEmpty() || (strIntern = InternCache.instance.intern(_simpleName)) == _simpleName) ? this : new PropertyName(strIntern, _namespace);
    }
    public PropertyName withSimpleName(String str) {
        if (null == str) {
            str = "";
        }
        return str.equals(_simpleName) ? this : new PropertyName(str, _namespace);
    }
    public PropertyName withNamespace(final String str) {
        if (null == str) {
            if (null == this._namespace) {
                return this;
            }
        } else if (str.equals(_namespace)) {
            return this;
        }
        return new PropertyName(_simpleName, str);
    }
    public String getSimpleName() {
        return _simpleName;
    }
    public SerializableString simpleAsEncoded(final MapperConfig<?> mapperConfig) {
        final SerializableString serializableStringCompileString;
        SerializableString serializableString = _encodedSimple;
        if (null == serializableString) {
            if (null == mapperConfig) {
                serializableStringCompileString = new SerializedString(_simpleName);
            } else {
                serializableStringCompileString = mapperConfig.compileString(_simpleName);
            }
            serializableString = serializableStringCompileString;
            _encodedSimple = serializableString;
        }
        return serializableString;
    }
    public String getNamespace() {
        return _namespace;
    }
    public boolean hasSimpleName() {
        return !_simpleName.isEmpty();
    }
    public boolean hasSimpleName(final String str) {
        return _simpleName.equals(str);
    }
    public boolean hasNamespace() {
        return null != this._namespace;
    }
    public boolean isEmpty() {
        return null == this._namespace && _simpleName.isEmpty();
    }
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        final PropertyName propertyName = (PropertyName) obj;
        final String str = _simpleName;
        if (null == str) {
            if (null != propertyName._simpleName) {
                return false;
            }
        } else if (!str.equals(propertyName._simpleName)) {
            return false;
        }
        final String str2 = _namespace;
        if (null == str2) {
            return null == propertyName._namespace;
        }
        return str2.equals(propertyName._namespace);
    }
    public int hashCode() {
        final String str = _namespace;
        if (null == str) {
            return _simpleName.hashCode();
        }
        return str.hashCode() ^ _simpleName.hashCode();
    }
    public String toString() {
        if (null == this._namespace) {
            return _simpleName;
        }
        return "{" + _namespace + "}" + _simpleName;
    }
}
