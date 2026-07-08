package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public @interface JsonIncludeProperties {
    String[] value() default {};

    class Value implements Serializable {
        protected static final Value ALL = new Value(null);
        private static final long serialVersionUID = 1;
        protected final Set<String> _included;

        protected Value(final Set<String> set) {
            _included = set;
        }

        public static Value from(final JsonIncludeProperties jsonIncludeProperties) {
            if (null == jsonIncludeProperties) {
                return Value.ALL;
            }
            return new Value(Value._asSet(jsonIncludeProperties.value()));
        }

        public static Value all() {
            return Value.ALL;
        }

        public Class<JsonIncludeProperties> valueFor() {
            return JsonIncludeProperties.class;
        }

        public Set<String> getIncluded() {
            return _included;
        }

        public Value withOverrides(final Value value) {
            final Set<String> included;
            if (null == value || null == (included = value.getIncluded())) {
                return this;
            }
            if (null == this._included) {
                return value;
            }
            final HashSet hashSet = new HashSet();
            for (final String str : included) {
                if (_included.contains(str)) {
                    hashSet.add(str);
                }
            }
            return new Value(hashSet);
        }

        public String toString() {
            return String.format("JsonIncludeProperties.Value(included=%s)", _included);
        }

        public int hashCode() {
            final Set<String> set = _included;
            if (null == set) {
                return 0;
            }
            return set.size();
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj) {
                return false;
            }
            return obj.getClass() == this.getClass() && Value._equals(_included, ((Value) obj)._included);
        }

        private static boolean _equals(final Set<String> set, final Set<String> set2) {
            if (null == set) {
                return null == set2;
            }
            return set.equals(set2);
        }

        private static Set<String> _asSet(final String[] strArr) {
            if (null == strArr || 0 == strArr.length) {
                return Collections.emptySet();
            }
            final HashSet hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
            return hashSet;
        }
    }
}
