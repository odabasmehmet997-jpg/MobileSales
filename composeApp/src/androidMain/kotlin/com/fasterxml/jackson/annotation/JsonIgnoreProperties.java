package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
public @interface JsonIgnoreProperties {
    boolean allowGetters() default false;

    boolean allowSetters() default false;

    boolean ignoreUnknown() default false;

    String[] value() default {};

    class Value implements Serializable {
        protected static final Value EMPTY = new Value(Collections.emptySet(), false, false, false, true);
        private static final long serialVersionUID = 1;
        protected final boolean _allowGetters;
        protected final boolean _allowSetters;
        protected final boolean _ignoreUnknown;
        protected final Set<String> _ignored;
        protected final boolean _merge;

        protected Value(final Set<String> set, final boolean z, final boolean z2, final boolean z3, final boolean z4) {
            if (null == set) {
                _ignored = Collections.emptySet();
            } else {
                _ignored = set;
            }
            _ignoreUnknown = z;
            _allowGetters = z2;
            _allowSetters = z3;
            _merge = z4;
        }

        public static Value from(final JsonIgnoreProperties jsonIgnoreProperties) {
            if (null == jsonIgnoreProperties) {
                return Value.EMPTY;
            }
            return Value.construct(Value._asSet(jsonIgnoreProperties.value()), jsonIgnoreProperties.ignoreUnknown(), jsonIgnoreProperties.allowGetters(), jsonIgnoreProperties.allowSetters(), false);
        }

        public static Value construct(final Set<String> set, final boolean z, final boolean z2, final boolean z3, final boolean z4) {
            if (Value._empty(set, z, z2, z3, z4)) {
                return Value.EMPTY;
            }
            return new Value(set, z, z2, z3, z4);
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

        public static Value forIgnoredProperties(final Set<String> set) {
            return Value.EMPTY.withIgnored(set);
        }

        public static Value forIgnoredProperties(final String... strArr) {
            if (0 == strArr.length) {
                return Value.EMPTY;
            }
            return Value.EMPTY.withIgnored(Value._asSet(strArr));
        }

        public static Value forIgnoreUnknown(final boolean z) {
            if (z) {
                return Value.EMPTY.withIgnoreUnknown();
            }
            return Value.EMPTY.withoutIgnoreUnknown();
        }

        public Value withOverrides(final Value value) {
            if (null == value || value == Value.EMPTY) {
                return this;
            }
            if (!value._merge) {
                return value;
            }
            if (Value._equals(this, value)) {
                return this;
            }
            return Value.construct(Value._merge(_ignored, value._ignored), _ignoreUnknown || value._ignoreUnknown, _allowGetters || value._allowGetters, _allowSetters || value._allowSetters, true);
        }

        public Value withIgnored(final Set<String> set) {
            return Value.construct(set, _ignoreUnknown, _allowGetters, _allowSetters, _merge);
        }

        public Value withIgnored(final String... strArr) {
            return Value.construct(Value._asSet(strArr), _ignoreUnknown, _allowGetters, _allowSetters, _merge);
        }

        public Value withoutIgnored() {
            return Value.construct(null, _ignoreUnknown, _allowGetters, _allowSetters, _merge);
        }

        public Value withIgnoreUnknown() {
            return _ignoreUnknown ? this : Value.construct(_ignored, true, _allowGetters, _allowSetters, _merge);
        }

        public Value withoutIgnoreUnknown() {
            return !_ignoreUnknown ? this : Value.construct(_ignored, false, _allowGetters, _allowSetters, _merge);
        }

        public Value withAllowGetters() {
            return _allowGetters ? this : Value.construct(_ignored, _ignoreUnknown, true, _allowSetters, _merge);
        }

        public Value withoutAllowGetters() {
            return !_allowGetters ? this : Value.construct(_ignored, _ignoreUnknown, false, _allowSetters, _merge);
        }

        public Value withAllowSetters() {
            return _allowSetters ? this : Value.construct(_ignored, _ignoreUnknown, _allowGetters, true, _merge);
        }

        public Value withoutAllowSetters() {
            return !_allowSetters ? this : Value.construct(_ignored, _ignoreUnknown, _allowGetters, false, _merge);
        }

        public Value withMerge() {
            return _merge ? this : Value.construct(_ignored, _ignoreUnknown, _allowGetters, _allowSetters, true);
        }

        public Value withoutMerge() {
            return !_merge ? this : Value.construct(_ignored, _ignoreUnknown, _allowGetters, _allowSetters, false);
        }

        public Class<JsonIgnoreProperties> valueFor() {
            return JsonIgnoreProperties.class;
        }

        protected Object readResolve() {
            return Value._empty(_ignored, _ignoreUnknown, _allowGetters, _allowSetters, _merge) ? Value.EMPTY : this;
        }

        public Set<String> getIgnored() {
            return _ignored;
        }

        public Set<String> findIgnoredForSerialization() {
            if (_allowGetters) {
                return Collections.emptySet();
            }
            return _ignored;
        }

        public Set<String> findIgnoredForDeserialization() {
            if (_allowSetters) {
                return Collections.emptySet();
            }
            return _ignored;
        }

        public boolean getIgnoreUnknown() {
            return _ignoreUnknown;
        }

        public boolean getAllowGetters() {
            return _allowGetters;
        }

        public boolean getAllowSetters() {
            return _allowSetters;
        }

        public boolean getMerge() {
            return _merge;
        }

        public String toString() {
            return String.format("JsonIgnoreProperties.Value(ignored=%s,ignoreUnknown=%s,allowGetters=%s,allowSetters=%s,merge=%s)", _ignored, Boolean.valueOf(_ignoreUnknown), Boolean.valueOf(_allowGetters), Boolean.valueOf(_allowSetters), Boolean.valueOf(_merge));
        }

        public int hashCode() {
            return _ignored.size() + (_ignoreUnknown ? 1 : -3) + (_allowGetters ? 3 : -7) + (_allowSetters ? 7 : -11) + (_merge ? 11 : -13);
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj) {
                return false;
            }
            return obj.getClass() == this.getClass() && Value._equals(this, (Value) obj);
        }

        private static boolean _equals(final Value value, final Value value2) {
            return value._ignoreUnknown == value2._ignoreUnknown && value._merge == value2._merge && value._allowGetters == value2._allowGetters && value._allowSetters == value2._allowSetters && value._ignored.equals(value2._ignored);
        }

        private static Set<String> _asSet(final String[] strArr) {
            if (null == strArr || 0 == strArr.length) {
                return Collections.emptySet();
            }
            final HashSet hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
            return hashSet;
        }

        private static Set<String> _merge(final Set<String> set, final Set<String> set2) {
            if (set.isEmpty()) {
                return set2;
            }
            if (set2.isEmpty()) {
                return set;
            }
            final HashSet hashSet = new HashSet(set.size() + set2.size());
            hashSet.addAll(set);
            hashSet.addAll(set2);
            return hashSet;
        }

        private static boolean _empty(final Set<String> set, final boolean z, final boolean z2, final boolean z3, final boolean z4) {
            final Value value = Value.EMPTY;
            if (z == value._ignoreUnknown && z2 == value._allowGetters && z3 == value._allowSetters && z4 == value._merge) {
                return null == set || 0 == set.size();
            }
            return false;
        }
    }
}
