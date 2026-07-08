package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.BeanProperty;
import java.io.IOException;

public abstract class TypeSerializer {
    public abstract TypeSerializer forProperty(BeanProperty beanProperty);

    public abstract String getPropertyName();

    public abstract JsonTypeInfo.EnumC1184As getTypeInclusion();

    public abstract WritableTypeId writeTypePrefix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException;

    public abstract WritableTypeId writeTypeSuffix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException;
 
    enum C12101 {
        ;
        static final int[] SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs;

        static {
            final int[] iArr = new int[JsonTypeInfo.EnumC1184As.values().length];
            SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs = iArr;
            try {
                iArr[JsonTypeInfo.EnumC1184As.EXISTING_PROPERTY.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12101.SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs[JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C12101.SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs[JsonTypeInfo.EnumC1184As.PROPERTY.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C12101.SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs[JsonTypeInfo.EnumC1184As.WRAPPER_ARRAY.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C12101.SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs[JsonTypeInfo.EnumC1184As.WRAPPER_OBJECT.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
        }
    }

    public WritableTypeId typeId(final Object obj, final JsonToken jsonToken) {
        final WritableTypeId writableTypeId = new WritableTypeId(obj, jsonToken);
        final int i2 = C12101.SwitchMapcomfasterxmljacksonannotationJsonTypeInfoAs[this.getTypeInclusion().ordinal()];
        if (1 == i2) {
            writableTypeId.include = WritableTypeId.Inclusion.PAYLOAD_PROPERTY;
            writableTypeId.asProperty = this.getPropertyName();
        } else if (2 == i2) {
            writableTypeId.include = WritableTypeId.Inclusion.PARENT_PROPERTY;
            writableTypeId.asProperty = this.getPropertyName();
        } else if (3 == i2) {
            writableTypeId.include = WritableTypeId.Inclusion.METADATA_PROPERTY;
            writableTypeId.asProperty = this.getPropertyName();
        } else if (4 == i2) {
            writableTypeId.include = WritableTypeId.Inclusion.WRAPPER_ARRAY;
        } else if (5 == i2) {
            writableTypeId.include = WritableTypeId.Inclusion.WRAPPER_OBJECT;
        } else {
            VersionUtil.throwInternal();
        }
        return writableTypeId;
    }

    public WritableTypeId typeId(final Object obj, final JsonToken jsonToken, final Object obj2) {
        final WritableTypeId writableTypeIdTypeId = this.typeId(obj, jsonToken);
        writableTypeIdTypeId.f798id = obj2;
        return writableTypeIdTypeId;
    }

    public WritableTypeId typeId(final Object obj, final Class<?> cls, final JsonToken jsonToken) {
        final WritableTypeId writableTypeIdTypeId = this.typeId(obj, jsonToken);
        writableTypeIdTypeId.forValueType = cls;
        return writableTypeIdTypeId;
    }
}
