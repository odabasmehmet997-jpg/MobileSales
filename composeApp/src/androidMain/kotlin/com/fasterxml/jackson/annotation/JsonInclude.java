package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface JsonInclude {

    enum Include {
        ALWAYS,
        NON_NULL,
        NON_ABSENT,
        NON_EMPTY,
        NON_DEFAULT,
        CUSTOM,
        USE_DEFAULTS
    }

    Include content() default Include.ALWAYS;

    Class<?> contentFilter() default Void.class;

    Include value() default Include.ALWAYS;

    Class<?> valueFilter() default Void.class;

    class Value implements Serializable {
        protected static final Value EMPTY;
        private static final long serialVersionUID = 1;
        protected final Class<?> _contentFilter;
        protected final Include _contentInclusion;
        protected final Class<?> _valueFilter;
        protected final Include _valueInclusion;

        static {
            final Include include = Include.USE_DEFAULTS;
            EMPTY = new Value(include, include, null, null);
        }

        public Value(final JsonInclude jsonInclude) {
            this(jsonInclude.value(), jsonInclude.content(), jsonInclude.valueFilter(), jsonInclude.contentFilter());
        }

        protected Value(final Include include, final Include include2, final Class<?> cls, final Class<?> cls2) {
            _valueInclusion = null == include ? Include.USE_DEFAULTS : include;
            _contentInclusion = null == include2 ? Include.USE_DEFAULTS : include2;
            _valueFilter = Void.class == cls ? null : cls;
            _contentFilter = Void.class == cls2 ? null : cls2;
        }

        public static Value empty() {
            return Value.EMPTY;
        }

        public static Value merge(final Value value, final Value value2) {
            return null == value ? value2 : value.withOverrides(value2);
        }

        public static Value mergeAll(final Value... valueArr) {
            Value value = null;
            for (Value valueWithOverrides : valueArr) {
                if (null != valueWithOverrides) {
                    if (null != value) {
                        valueWithOverrides = value.withOverrides(valueWithOverrides);
                    }
                    value = valueWithOverrides;
                }
            }
            return value;
        }

        protected Object readResolve() {
            final Include include = _valueInclusion;
            final Include include2 = Include.USE_DEFAULTS;
            return (include == include2 && _contentInclusion == include2 && null == this._valueFilter && null == this._contentFilter) ? Value.EMPTY : this;
        }

        public Value withOverrides(final Value value) {
            if (null != value && value != Value.EMPTY) {
                final Include include = value._valueInclusion;
                final Include include2 = value._contentInclusion;
                final Class<?> cls = value._valueFilter;
                final Class<?> cls2 = value._contentFilter;
                final Include include3 = _valueInclusion;
                final boolean z = include != include3 && Include.USE_DEFAULTS != include;
                final Include include4 = _contentInclusion;
                final boolean z2 = include2 != include4 && Include.USE_DEFAULTS != include2;
                final Class<?> cls3 = _valueFilter;
                final boolean z3 = cls != cls3 || cls2 != cls3;
                if (z) {
                    if (z2) {
                        return new Value(include, include2, cls, cls2);
                    }
                    return new Value(include, include4, cls, cls2);
                }
                if (z2) {
                    return new Value(include3, include2, cls, cls2);
                }
                if (z3) {
                    return new Value(include3, include4, cls, cls2);
                }
            }
            return this;
        }

        public static Value construct(final Include include, final Include include2) {
            final Include include3 = Include.USE_DEFAULTS;
            if ((include == include3 || null == include) && (include2 == include3 || null == include2)) {
                return Value.EMPTY;
            }
            return new Value(include, include2, null, null);
        }

        public static Value construct(final Include include, final Include include2, Class<?> cls, Class<?> cls2) {
            if (Void.class == cls) {
                cls = null;
            }
            if (Void.class == cls2) {
                cls2 = null;
            }
            final Include include3 = Include.USE_DEFAULTS;
            if ((include == include3 || null == include) && ((include2 == include3 || null == include2) && null == cls && null == cls2)) {
                return Value.EMPTY;
            }
            return new Value(include, include2, cls, cls2);
        }

        public static Value from(final JsonInclude jsonInclude) {
            if (null == jsonInclude) {
                return Value.EMPTY;
            }
            final Include includeValue = jsonInclude.value();
            final Include includeContent = jsonInclude.content();
            final Include include = Include.USE_DEFAULTS;
            if (includeValue == include && includeContent == include) {
                return Value.EMPTY;
            }
            Class<?> clsValueFilter = jsonInclude.valueFilter();
            if (Void.class == clsValueFilter) {
                clsValueFilter = null;
            }
            final Class<?> clsContentFilter = jsonInclude.contentFilter();
            return new Value(includeValue, includeContent, clsValueFilter, Void.class != clsContentFilter ? clsContentFilter : null);
        }

        public Value withValueInclusion(final Include include) {
            return include == _valueInclusion ? this : new Value(include, _contentInclusion, _valueFilter, _contentFilter);
        }

        public Value withValueFilter(Class<?> cls) {
            final Include include;
            if (null == cls || Void.class == cls) {
                include = Include.USE_DEFAULTS;
                cls = null;
            } else {
                include = Include.CUSTOM;
            }
            return Value.construct(include, _contentInclusion, cls, _contentFilter);
        }

        public Value withContentFilter(Class<?> cls) {
            final Include include;
            if (null == cls || Void.class == cls) {
                include = Include.USE_DEFAULTS;
                cls = null;
            } else {
                include = Include.CUSTOM;
            }
            return Value.construct(_valueInclusion, include, _valueFilter, cls);
        }

        public Value withContentInclusion(final Include include) {
            return include == _contentInclusion ? this : new Value(_valueInclusion, include, _valueFilter, _contentFilter);
        }

        public Class<JsonInclude> valueFor() {
            return JsonInclude.class;
        }

        public Include getValueInclusion() {
            return _valueInclusion;
        }

        public Include getContentInclusion() {
            return _contentInclusion;
        }

        public Class<?> getValueFilter() {
            return _valueFilter;
        }

        public Class<?> getContentFilter() {
            return _contentFilter;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder(80);
            sb.append("JsonInclude.Value(value=");
            sb.append(_valueInclusion);
            sb.append(",content=");
            sb.append(_contentInclusion);
            if (null != this._valueFilter) {
                sb.append(",valueFilter=");
                sb.append(_valueFilter.getName());
                sb.append(".class");
            }
            if (null != this._contentFilter) {
                sb.append(",contentFilter=");
                sb.append(_contentFilter.getName());
                sb.append(".class");
            }
            sb.append(')');
            return sb.toString();
        }

        public int hashCode() {
            return (_valueInclusion.hashCode() << 2) + _contentInclusion.hashCode();
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || obj.getClass() != this.getClass()) {
                return false;
            }
            final Value value = (Value) obj;
            return value._valueInclusion == _valueInclusion && value._contentInclusion == _contentInclusion && value._valueFilter == _valueFilter && value._contentFilter == _contentFilter;
        }
    }
}
