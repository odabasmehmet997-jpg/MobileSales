package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import java.io.Serializable;
import java.util.*;

public final class EnumValues implements Serializable {
    private static final long serialVersionUID = 1;
    private transient EnumMap<?, SerializableString> _asMap;
    private final Class<Enum<?>> _enumClass;
    private final SerializableString[] _textual;
    private final Enum<?>[] _values;

    private EnumValues(final Class<Enum<?>> cls, final SerializableString[] serializableStringArr) {
        _enumClass = cls;
        _values = cls.getEnumConstants();
        _textual = serializableStringArr;
    }

    public static EnumValues construct(final SerializationConfig serializationConfig, final Class<Enum<?>> cls) {
        if (serializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            return EnumValues.constructFromToString(serializationConfig, cls);
        }
        return EnumValues.constructFromName(serializationConfig, cls);
    }

    public static EnumValues constructFromName(final MapperConfig<?> mapperConfig, final Class<Enum<?>> cls) {
        final Class<? extends Enum<?>> clsFindEnumType = ClassUtil.findEnumType(cls);
        final Enum<?>[] enumArr = clsFindEnumType.getEnumConstants();
        if (null == enumArr) {
            throw new IllegalArgumentException("Cannot determine enum constants for Class " + cls.getName());
        }
        final String[] strArrFindEnumValues = mapperConfig.getAnnotationIntrospector().findEnumValues(clsFindEnumType, enumArr, new String[enumArr.length]);
        final SerializableString[] serializableStringArr = new SerializableString[enumArr.length];
        final int length = enumArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            final Enum<?> r5 = enumArr[i2];
            String strName = strArrFindEnumValues[i2];
            if (null == strName) {
                strName = r5.name();
            }
            serializableStringArr[r5.ordinal()] = mapperConfig.compileString(strName);
        }
        return EnumValues.construct(cls, serializableStringArr);
    }

    public static EnumValues constructFromToString(final MapperConfig<?> mapperConfig, final Class<Enum<?>> cls) {
        final Enum[] enumArr = ClassUtil.findEnumType(cls).getEnumConstants();
        if (null == enumArr) {
            throw new IllegalArgumentException("Cannot determine enum constants for Class " + cls.getName());
        }
        final ArrayList arrayList = new ArrayList(enumArr.length);
        for (final Enum r0 : enumArr) {
            arrayList.add(r0.toString());
        }
        return EnumValues.construct(mapperConfig, cls, arrayList);
    }

    public static EnumValues construct(final MapperConfig<?> mapperConfig, final Class<Enum<?>> cls, final List<String> list) {
        final int size = list.size();
        final SerializableString[] serializableStringArr = new SerializableString[size];
        for (int i2 = 0; i2 < size; i2++) {
            serializableStringArr[i2] = mapperConfig.compileString(list.get(i2));
        }
        return EnumValues.construct(cls, serializableStringArr);
    }

    public static EnumValues construct(final Class<Enum<?>> cls, final SerializableString[] serializableStringArr) {
        return new EnumValues(cls, serializableStringArr);
    }

    public SerializableString serializedValueFor(final Enum<?> r2) {
        return _textual[r2.ordinal()];
    }

    public Collection<SerializableString> values() {
        return Arrays.asList(_textual);
    }

    public List<Enum<?>> enums() {
        return Arrays.asList(_values);
    }

    public EnumMap<?, SerializableString> internalMap() {
        final EnumMap<?, SerializableString> enumMap = _asMap;
        if (null != enumMap) {
            return enumMap;
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (final Enum<?> r4 : _values) {
            linkedHashMap.put(r4, _textual[r4.ordinal()]);
        }
        return new EnumMap<>(linkedHashMap);
    }

    public Class<Enum<?>> getEnumClass() {
        return _enumClass;
    }
}
