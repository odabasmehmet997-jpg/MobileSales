package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public @interface JsonSetter {
    Nulls contentNulls() default Nulls.DEFAULT;

    Nulls nulls() default Nulls.DEFAULT;

    String value() default "";

    class Value implements Serializable {
        protected static final Value EMPTY;
        private static final long serialVersionUID = 1;
        private final Nulls _contentNulls;
        private final Nulls _nulls;

        static {
            final Nulls nulls = Nulls.DEFAULT;
            EMPTY = new Value(nulls, nulls);
        }

        protected Value(final Nulls nulls, final Nulls nulls2) {
            _nulls = nulls;
            _contentNulls = nulls2;
        }

        public Class<JsonSetter> valueFor() {
            return JsonSetter.class;
        }

        protected Object readResolve() {
            return Value._empty(_nulls, _contentNulls) ? Value.EMPTY : this;
        }

        public static Value from(final JsonSetter jsonSetter) {
            if (null == jsonSetter) {
                return Value.EMPTY;
            }
            return Value.construct(jsonSetter.nulls(), jsonSetter.contentNulls());
        }

        public static Value construct(Nulls nulls, Nulls nulls2) {
            if (null == nulls) {
                nulls = Nulls.DEFAULT;
            }
            if (null == nulls2) {
                nulls2 = Nulls.DEFAULT;
            }
            if (Value._empty(nulls, nulls2)) {
                return Value.EMPTY;
            }
            return new Value(nulls, nulls2);
        }

        public static Value empty() {
            return Value.EMPTY;
        }

        public static Value merge(final Value value, final Value value2) {
            return null == value ? value2 : value.withOverrides(value2);
        }

        public static Value forValueNulls(final Nulls nulls) {
            return Value.construct(nulls, Nulls.DEFAULT);
        }

        public static Value forValueNulls(final Nulls nulls, final Nulls nulls2) {
            return Value.construct(nulls, nulls2);
        }

        public static Value forContentNulls(final Nulls nulls) {
            return Value.construct(Nulls.DEFAULT, nulls);
        }

        public Value withOverrides(final Value value) {
            if (null == value || value == Value.EMPTY) {
                return this;
            }
            Nulls nulls = value._nulls;
            Nulls nulls2 = value._contentNulls;
            final Nulls nulls3 = Nulls.DEFAULT;
            if (nulls == nulls3) {
                nulls = _nulls;
            }
            if (nulls2 == nulls3) {
                nulls2 = _contentNulls;
            }
            return (nulls == _nulls && nulls2 == _contentNulls) ? this : Value.construct(nulls, nulls2);
        }

        public Value withValueNulls(Nulls nulls) {
            if (null == nulls) {
                nulls = Nulls.DEFAULT;
            }
            return nulls == _nulls ? this : Value.construct(nulls, _contentNulls);
        }

        public Value withValueNulls(Nulls nulls, Nulls nulls2) {
            if (null == nulls) {
                nulls = Nulls.DEFAULT;
            }
            if (null == nulls2) {
                nulls2 = Nulls.DEFAULT;
            }
            return (nulls == _nulls && nulls2 == _contentNulls) ? this : Value.construct(nulls, nulls2);
        }

        public Value withContentNulls(Nulls nulls) {
            if (null == nulls) {
                nulls = Nulls.DEFAULT;
            }
            return nulls == _contentNulls ? this : Value.construct(_nulls, nulls);
        }

        public Nulls getValueNulls() {
            return _nulls;
        }

        public Nulls getContentNulls() {
            return _contentNulls;
        }

        public Nulls nonDefaultValueNulls() {
            final Nulls nulls = _nulls;
            if (Nulls.DEFAULT == nulls) {
                return null;
            }
            return nulls;
        }

        public Nulls nonDefaultContentNulls() {
            final Nulls nulls = _contentNulls;
            if (Nulls.DEFAULT == nulls) {
                return null;
            }
            return nulls;
        }

        public String toString() {
            return String.format("JsonSetter.Value(valueNulls=%s,contentNulls=%s)", _nulls, _contentNulls);
        }

        public int hashCode() {
            return _nulls.ordinal() + (_contentNulls.ordinal() << 2);
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || obj.getClass() != this.getClass()) {
                return false;
            }
            final Value value = (Value) obj;
            return value._nulls == _nulls && value._contentNulls == _contentNulls;
        }

        private static boolean _empty(final Nulls nulls, final Nulls nulls2) {
            final Nulls nulls3 = Nulls.DEFAULT;
            return nulls == nulls3 && nulls2 == nulls3;
        }
    }
}
