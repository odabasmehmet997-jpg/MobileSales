package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.*;

public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;
    protected final boolean _isIgnoreCase;

    
    protected static Class<Enum<?>> _enumClass(final Class<?> cls) {
        return (Class<Enum<?>>) cls;
    }

    protected EnumResolver(final Class<Enum<?>> cls, final Enum<?>[] enumArr, final HashMap<String, Enum<?>> map, final Enum<?> r4, final boolean z) {
        _enumClass = cls;
        _enums = enumArr;
        _enumsById = map;
        _defaultValue = r4;
        _isIgnoreCase = z;
    }

    public static EnumResolver constructFor(final DeserializationConfig deserializationConfig, final Class<?> cls) {
        return EnumResolver._constructFor(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructFor(final Class<?> cls, final AnnotationIntrospector annotationIntrospector, final boolean z) {
        final Class<Enum<?>> cls_enumClass = EnumResolver._enumClass(cls);
        final Enum<?>[] enumArr_enumConstants = EnumResolver._enumConstants(cls);
        final String[] strArrFindEnumValues = annotationIntrospector.findEnumValues(cls_enumClass, enumArr_enumConstants, new String[enumArr_enumConstants.length]);
        final String[][] strArr = new String[strArrFindEnumValues.length][];
        annotationIntrospector.findEnumAliases(cls_enumClass, enumArr_enumConstants, strArr);
        final HashMap map = new HashMap();
        final int length = enumArr_enumConstants.length;
        for (int i2 = 0; i2 < length; i2++) {
            final Enum<?> r7 = enumArr_enumConstants[i2];
            String strName = strArrFindEnumValues[i2];
            if (null == strName) {
                strName = r7.name();
            }
            map.put(strName, r7);
            final String[] strArr2 = strArr[i2];
            if (null != strArr2) {
                for (final String str : strArr2) {
                    if (!map.containsKey(str)) {
                        map.put(str, r7);
                    }
                }
            }
        }
        return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, EnumResolver._enumDefault(annotationIntrospector, cls_enumClass), z);
    }

    public static EnumResolver constructUsingToString(final DeserializationConfig deserializationConfig, final Class<?> cls) {
        return EnumResolver._constructUsingToString(cls, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructUsingToString(final Class<?> cls, final AnnotationIntrospector annotationIntrospector, final boolean z) {
        final Class<Enum<?>> cls_enumClass = EnumResolver._enumClass(cls);
        final Enum<?>[] enumArr_enumConstants = EnumResolver._enumConstants(cls);
        final HashMap map = new HashMap();
        final String[][] strArr = new String[enumArr_enumConstants.length][];
        annotationIntrospector.findEnumAliases(cls_enumClass, enumArr_enumConstants, strArr);
        int length = enumArr_enumConstants.length;
        while (true) {
            length--;
            if (0 <= length) {
                final Enum<?> r4 = enumArr_enumConstants[length];
                map.put(r4.toString(), r4);
                final String[] strArr2 = strArr[length];
                if (null != strArr2) {
                    for (final String str : strArr2) {
                        if (!map.containsKey(str)) {
                            map.put(str, r4);
                        }
                    }
                }
            } else {
                return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, EnumResolver._enumDefault(annotationIntrospector, cls_enumClass), z);
            }
        }
    }

    public static EnumResolver constructUsingMethod(final DeserializationConfig deserializationConfig, final Class<?> cls, final AnnotatedMember annotatedMember) {
        return EnumResolver._constructUsingMethod(cls, annotatedMember, deserializationConfig.getAnnotationIntrospector(), deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    protected static EnumResolver _constructUsingMethod(final Class<?> cls, final AnnotatedMember annotatedMember, final AnnotationIntrospector annotationIntrospector, final boolean z) {
        final Class<Enum<?>> cls_enumClass = EnumResolver._enumClass(cls);
        final Enum<?>[] enumArr_enumConstants = EnumResolver._enumConstants(cls);
        final HashMap map = new HashMap();
        int length = enumArr_enumConstants.length;
        while (true) {
            length--;
            if (0 <= length) {
                final Enum<?> r0 = enumArr_enumConstants[length];
                try {
                    final Object value = annotatedMember.getValue(r0);
                    if (null != value) {
                        map.put(value.toString(), r0);
                    }
                } catch (final Exception e2) {
                    throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r0 + ": " + e2.getMessage());
                }
            } else {
                return new EnumResolver(cls_enumClass, enumArr_enumConstants, map, EnumResolver._enumDefault(annotationIntrospector, cls_enumClass), z);
            }
        }
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(_enumsById);
    }

    protected static Enum<?>[] _enumConstants(final Class<?> cls) {
        final Enum<?>[] enumConstants = EnumResolver._enumClass(cls).getEnumConstants();
        if (null != enumConstants) {
            return enumConstants;
        }
        throw new IllegalArgumentException("No enum constants for class " + cls.getName());
    }

    protected static Enum<?> _enumDefault(final AnnotationIntrospector annotationIntrospector, final Class<?> cls) {
        if (null != annotationIntrospector) {
            return annotationIntrospector.findDefaultEnumValue(EnumResolver._enumClass(cls));
        }
        return null;
    }

    @Deprecated
    protected EnumResolver(final Class<Enum<?>> cls, final Enum<?>[] enumArr, final HashMap<String, Enum<?>> map, final Enum<?> r10) {
        this(cls, enumArr, map, r10, false);
    }

    @Deprecated
    public static EnumResolver constructFor(final Class<Enum<?>> cls, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafe(final Class<?> cls, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructFor(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(final Class<Enum<?>> cls, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructUsingToString(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingToString(final Class<?> cls, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructUsingToString(cls, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUsingToString(final Class<Enum<?>> cls) {
        return EnumResolver._constructUsingToString(cls, null, false);
    }

    @Deprecated
    public static EnumResolver constructUsingMethod(final Class<Enum<?>> cls, final AnnotatedMember annotatedMember, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    @Deprecated
    public static EnumResolver constructUnsafeUsingMethod(final Class<?> cls, final AnnotatedMember annotatedMember, final AnnotationIntrospector annotationIntrospector) {
        return EnumResolver._constructUsingMethod(cls, annotatedMember, annotationIntrospector, false);
    }

    public Enum<?> findEnum(final String str) {
        final Enum<?> r0 = _enumsById.get(str);
        return (null == r0 && _isIgnoreCase) ? this._findEnumCaseInsensitive(str) : r0;
    }

    protected Enum<?> _findEnumCaseInsensitive(final String str) {
        for (final Map.Entry<String, Enum<?>> entry : _enumsById.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Enum<?> getEnum(final int i2) {
        if (0 > i2) {
            return null;
        }
        final Enum<?>[] enumArr = _enums;
        if (i2 >= enumArr.length) {
            return null;
        }
        return enumArr[i2];
    }

    public Enum<?> getDefaultValue() {
        return _defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return _enums;
    }

    public List<Enum<?>> getEnums() {
        final ArrayList arrayList = new ArrayList(_enums.length);
        Collections.addAll(arrayList, _enums);
        return arrayList;
    }

    public Collection<String> getEnumIds() {
        return _enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return _enumClass;
    }

    public int lastValidIndex() {
        return _enums.length - 1;
    }
}
