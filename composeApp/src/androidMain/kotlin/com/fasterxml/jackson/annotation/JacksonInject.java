package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface JacksonInject {
    OptBoolean useInput() default OptBoolean.DEFAULT;

    String value() default "";

    class Value implements Serializable {
        protected static final Value EMPTY = new Value(null, null);
        private static final long serialVersionUID = 1;
        protected final Object _id;
        protected final Boolean _useInput;

        private static boolean _empty(final Object obj, final Boolean bool) {
            return null == obj && null == bool;
        }

        protected Value(final Object obj, final Boolean bool) {
            _id = obj;
            _useInput = bool;
        }

        public Class<JacksonInject> valueFor() {
            return JacksonInject.class;
        }

        public static Value empty() {
            return Value.EMPTY;
        }

        public static Value construct(Object obj, final Boolean bool) {
            if ("".equals(obj)) {
                obj = null;
            }
            if (Value._empty(obj, bool)) {
                return Value.EMPTY;
            }
            return new Value(obj, bool);
        }

        public static Value from(final JacksonInject jacksonInject) {
            if (null == jacksonInject) {
                return Value.EMPTY;
            }
            return Value.construct(jacksonInject.value(), jacksonInject.useInput().asBoolean());
        }

        public static Value forId(final Object obj) {
            return Value.construct(obj, null);
        }

        public Value withId(final Object obj) {
            if (null == obj) {
                if (null == this._id) {
                    return this;
                }
            } else if (obj.equals(_id)) {
                return this;
            }
            return new Value(obj, _useInput);
        }

        public Value withUseInput(final Boolean bool) {
            if (null == bool) {
                if (null == this._useInput) {
                    return this;
                }
            } else if (bool.equals(_useInput)) {
                return this;
            }
            return new Value(_id, bool);
        }

        public Object getId() {
            return _id;
        }

        public Boolean getUseInput() {
            return _useInput;
        }

        public boolean hasId() {
            return null != this._id;
        }

        public boolean willUseInput(final boolean z) {
            final Boolean bool = _useInput;
            return null == bool ? z : bool.booleanValue();
        }

        public String toString() {
            return String.format("JacksonInject.Value(id=%s,useInput=%s)", _id, _useInput);
        }

        public int hashCode() {
            final Object obj = _id;
            final int iHashCode = null != obj ? 1 + obj.hashCode() : 1;
            final Boolean bool = _useInput;
            return null != bool ? iHashCode + bool.hashCode() : iHashCode;
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null != obj && obj.getClass() == this.getClass()) {
                final Value value = (Value) obj;
                if (OptBoolean.equals(_useInput, value._useInput)) {
                    final Object obj2 = _id;
                    if (null == obj2) {
                        return null == value._id;
                    }
                    return obj2.equals(value._id);
                }
            }
            return false;
        }
    }
}
