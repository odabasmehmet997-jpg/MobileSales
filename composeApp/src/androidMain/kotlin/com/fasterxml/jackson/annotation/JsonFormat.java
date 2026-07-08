package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

public @interface JsonFormat {

    enum Feature {
        ACCEPT_SINGLE_VALUE_AS_ARRAY,
        ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        ACCEPT_CASE_INSENSITIVE_VALUES,
        WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
        WRITE_DATES_WITH_ZONE_ID,
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED,
        WRITE_SORTED_MAP_ENTRIES,
        ADJUST_DATES_TO_CONTEXT_TIME_ZONE
    }

    OptBoolean lenient() default OptBoolean.DEFAULT;

    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";

    Feature[] with() default {};

    Feature[] without() default {};

    enum Shape {
        ANY,
        NATURAL,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN,
        BINARY;

        public boolean isNumeric() {
            return Shape.NUMBER == this || Shape.NUMBER_INT == this || Shape.NUMBER_FLOAT == this;
        }

        public boolean isStructured() {
            return Shape.OBJECT == this || Shape.ARRAY == this;
        }
    }

    class Features {
        private static final Features EMPTY = new Features(0, 0);
        private final int _disabled;
        private final int _enabled;

        private Features(final int i2, final int i3) {
            _enabled = i2;
            _disabled = i3;
        }

        public static Features empty() {
            return Features.EMPTY;
        }

        public static Features construct(final JsonFormat jsonFormat) {
            return Features.construct(jsonFormat.with(), jsonFormat.without());
        }

        public static Features construct(final Feature[] featureArr, final Feature[] featureArr2) {
            int iOrdinal = 0;
            for (final Feature feature : featureArr) {
                iOrdinal |= 1 << feature.ordinal();
            }
            int iOrdinal2 = 0;
            for (final Feature feature2 : featureArr2) {
                iOrdinal2 |= 1 << feature2.ordinal();
            }
            return new Features(iOrdinal, iOrdinal2);
        }

        public Features withOverrides(final Features features) {
            if (null == features) {
                return this;
            }
            final int i2 = features._disabled;
            final int i3 = features._enabled;
            if (0 == i2 && 0 == i3) {
                return this;
            }
            final int i4 = _enabled;
            if (0 == i4 && 0 == this._disabled) {
                return features;
            }
            final int i5 = ((~i2) & i4) | i3;
            final int i6 = _disabled;
            final int i7 = i2 | ((~i3) & i6);
            return (i5 == i4 && i7 == i6) ? this : new Features(i5, i7);
        }

        public Features with(final Feature... featureArr) {
            int iOrdinal = _enabled;
            for (final Feature feature : featureArr) {
                iOrdinal |= 1 << feature.ordinal();
            }
            return iOrdinal == _enabled ? this : new Features(iOrdinal, _disabled);
        }

        public Features without(final Feature... featureArr) {
            int iOrdinal = _disabled;
            for (final Feature feature : featureArr) {
                iOrdinal |= 1 << feature.ordinal();
            }
            return iOrdinal == _disabled ? this : new Features(_enabled, iOrdinal);
        }

        public Boolean get(final Feature feature) {
            final int iOrdinal = 1 << feature.ordinal();
            if (0 != (this._disabled & iOrdinal)) {
                return Boolean.FALSE;
            }
            if (0 != (iOrdinal & this._enabled)) {
                return Boolean.TRUE;
            }
            return null;
        }

        public String toString() {
            if (this == Features.EMPTY) {
                return "EMPTY";
            }
            return String.format("(enabled=0x%x,disabled=0x%x)", Integer.valueOf(_enabled), Integer.valueOf(_disabled));
        }

        public int hashCode() {
            return _disabled + _enabled;
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || obj.getClass() != this.getClass()) {
                return false;
            }
            final Features features = (Features) obj;
            return features._enabled == _enabled && features._disabled == _disabled;
        }
    }

    class Value implements Serializable {
        private static final Value EMPTY = new Value();
        private static final long serialVersionUID = 1;
        private final Features _features;
        private final Boolean _lenient;
        private final Locale _locale;
        private final String _pattern;
        private final Shape _shape;
        private transient TimeZone _timezone;
        private final String _timezoneStr;

        public Value() {
            this("", Shape.ANY, "", "", Features.empty(), null);
        }

        public Value(final JsonFormat jsonFormat) {
            this(jsonFormat.pattern(), jsonFormat.shape(), jsonFormat.locale(), jsonFormat.timezone(), Features.construct(jsonFormat), jsonFormat.lenient().asBoolean());
        }

        public Value(final String str, final Shape shape, final String str2, final String str3, final Features features, final Boolean bool) {
            this(str, shape, (null == str2 || 0 == str2.length() || "##default".equals(str2)) ? null : new Locale(str2), (null == str3 || 0 == str3.length() || "##default".equals(str3)) ? null : str3, null, features, bool);
        }

        public Value(final String str, final Shape shape, final Locale locale, final TimeZone timeZone, final Features features, final Boolean bool) {
            _pattern = null == str ? "" : str;
            _shape = null == shape ? Shape.ANY : shape;
            _locale = locale;
            _timezone = timeZone;
            _timezoneStr = null;
            _features = null == features ? Features.empty() : features;
            _lenient = bool;
        }

        public Value(final String str, final Shape shape, final Locale locale, final String str2, final TimeZone timeZone, final Features features, final Boolean bool) {
            _pattern = null == str ? "" : str;
            _shape = null == shape ? Shape.ANY : shape;
            _locale = locale;
            _timezone = timeZone;
            _timezoneStr = str2;
            _features = null == features ? Features.empty() : features;
            _lenient = bool;
        }

        @Deprecated
        public Value(final String str, final Shape shape, final Locale locale, final String str2, final TimeZone timeZone, final Features features) {
            this(str, shape, locale, str2, timeZone, features, null);
        }

        @Deprecated
        public Value(final String str, final Shape shape, final String str2, final String str3, final Features features) {
            this(str, shape, str2, str3, features, null);
        }

        @Deprecated
        public Value(final String str, final Shape shape, final Locale locale, final TimeZone timeZone, final Features features) {
            this(str, shape, locale, timeZone, features, null);
        }

        public static final Value empty() {
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

        public static final Value from(final JsonFormat jsonFormat) {
            return null == jsonFormat ? Value.EMPTY : new Value(jsonFormat);
        }

        public final Value withOverrides(final Value value) {
            final Value value2;
            final Features featuresWithOverrides;
            final String str;
            final TimeZone timeZone;
            if (null == value || value == (value2 = Value.EMPTY) || value == this) {
                return this;
            }
            if (this == value2) {
                return value;
            }
            String str2 = value._pattern;
            if (null == str2 || str2.isEmpty()) {
                str2 = _pattern;
            }
            final String str3 = str2;
            Shape shape = value._shape;
            if (Shape.ANY == shape) {
                shape = _shape;
            }
            final Shape shape2 = shape;
            Locale locale = value._locale;
            if (null == locale) {
                locale = _locale;
            }
            final Locale locale2 = locale;
            final Features features = _features;
            if (null == features) {
                featuresWithOverrides = value._features;
            } else {
                featuresWithOverrides = features.withOverrides(value._features);
            }
            final Features features2 = featuresWithOverrides;
            Boolean bool = value._lenient;
            if (null == bool) {
                bool = _lenient;
            }
            final Boolean bool2 = bool;
            final String str4 = value._timezoneStr;
            if (null == str4 || str4.isEmpty()) {
                str = _timezoneStr;
                timeZone = _timezone;
            } else {
                timeZone = value._timezone;
                str = str4;
            }
            return new Value(str3, shape2, locale2, str, timeZone, features2, bool2);
        }

        public static Value forPattern(final String str) {
            return new Value(str, null, null, null, null, Features.empty(), null);
        }

        public static Value forShape(final Shape shape) {
            return new Value("", shape, null, null, null, Features.empty(), null);
        }

        public static Value forLeniency(final boolean z) {
            return new Value("", null, null, null, null, Features.empty(), Boolean.valueOf(z));
        }

        public Value withPattern(final String str) {
            return new Value(str, _shape, _locale, _timezoneStr, _timezone, _features, _lenient);
        }

        public Value withShape(final Shape shape) {
            return shape == _shape ? this : new Value(_pattern, shape, _locale, _timezoneStr, _timezone, _features, _lenient);
        }

        public Value withLocale(final Locale locale) {
            return new Value(_pattern, _shape, locale, _timezoneStr, _timezone, _features, _lenient);
        }

        public Value withTimeZone(final TimeZone timeZone) {
            return new Value(_pattern, _shape, _locale, null, timeZone, _features, _lenient);
        }

        public Value withLenient(final Boolean bool) {
            return bool == _lenient ? this : new Value(_pattern, _shape, _locale, _timezoneStr, _timezone, _features, bool);
        }

        public Value withFeature(final Feature feature) {
            final Features featuresWith = _features.with(feature);
            return featuresWith == _features ? this : new Value(_pattern, _shape, _locale, _timezoneStr, _timezone, featuresWith, _lenient);
        }

        public Value withoutFeature(final Feature feature) {
            final Features featuresWithout = _features.without(feature);
            return featuresWithout == _features ? this : new Value(_pattern, _shape, _locale, _timezoneStr, _timezone, featuresWithout, _lenient);
        }

        public Class<JsonFormat> valueFor() {
            return JsonFormat.class;
        }

        public String getPattern() {
            return _pattern;
        }

        public Shape getShape() {
            return _shape;
        }

        public Locale getLocale() {
            return _locale;
        }

        public Boolean getLenient() {
            return _lenient;
        }

        public boolean isLenient() {
            return Boolean.TRUE.equals(_lenient);
        }

        public String timeZoneAsString() {
            final TimeZone timeZone = _timezone;
            if (null != timeZone) {
                return timeZone.getID();
            }
            return _timezoneStr;
        }

        public TimeZone getTimeZone() {
            final TimeZone timeZone = _timezone;
            if (null != timeZone) {
                return timeZone;
            }
            final String str = _timezoneStr;
            if (null == str) {
                return null;
            }
            final TimeZone timeZone2 = TimeZone.getTimeZone(str);
            _timezone = timeZone2;
            return timeZone2;
        }

        public boolean hasShape() {
            return Shape.ANY != this._shape;
        }

        public boolean hasPattern() {
            final String str = _pattern;
            return null != str && 0 < str.length();
        }

        public boolean hasLocale() {
            return null != this._locale;
        }

        public boolean hasTimeZone() {
            final String str;
            return null != this._timezone || (null != (str = this._timezoneStr) && !str.isEmpty());
        }

        public boolean hasLenient() {
            return null != this._lenient;
        }

        public Boolean getFeature(final Feature feature) {
            return _features.get(feature);
        }

        public Features getFeatures() {
            return _features;
        }

        public String toString() {
            return String.format("JsonFormat.Value(pattern=%s,shape=%s,lenient=%s,locale=%s,timezone=%s,features=%s)", _pattern, _shape, _lenient, _locale, _timezoneStr, _features);
        }

        public int hashCode() {
            final String str = _timezoneStr;
            int iHashCode = null == str ? 1 : str.hashCode();
            final String str2 = _pattern;
            if (null != str2) {
                iHashCode ^= str2.hashCode();
            }
            int iHashCode2 = iHashCode + _shape.hashCode();
            final Boolean bool = _lenient;
            if (null != bool) {
                iHashCode2 ^= bool.hashCode();
            }
            final Locale locale = _locale;
            if (null != locale) {
                iHashCode2 += locale.hashCode();
            }
            return iHashCode2 ^ _features.hashCode();
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || obj.getClass() != this.getClass()) {
                return false;
            }
            final Value value = (Value) obj;
            if (_shape == value._shape && _features.equals(value._features)) {
                return Value._equal(_lenient, value._lenient) && Value._equal(_timezoneStr, value._timezoneStr) && Value._equal(_pattern, value._pattern) && Value._equal(_timezone, value._timezone) && Value._equal(_locale, value._locale);
            }
            return false;
        }

        private static <T> boolean _equal(final T t, final T t2) {
            if (null == t) {
                return null == t2;
            }
            if (null == t2) {
                return false;
            }
            return t.equals(t2);
        }
    }
}
