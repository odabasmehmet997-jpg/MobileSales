package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;

public class JsonLocationInstantiator extends ValueInstantiator.Base {
    private static final long serialVersionUID = 1;
    public boolean canCreateFromObjectWith() {
        return true;
    }

    public JsonLocationInstantiator() {
        super(JsonLocation.class);
    }

    public SettableBeanProperty[] getFromObjectArguments(final DeserializationConfig deserializationConfig) {
        final JavaType javaTypeConstructType = deserializationConfig.constructType(Integer.TYPE);
        final JavaType javaTypeConstructType2 = deserializationConfig.constructType(Long.TYPE);
        return new SettableBeanProperty[]{JsonLocationInstantiator.creatorProp("sourceRef", deserializationConfig.constructType(Object.class), 0), JsonLocationInstantiator.creatorProp("byteOffset", javaTypeConstructType2, 1), JsonLocationInstantiator.creatorProp("charOffset", javaTypeConstructType2, 2), JsonLocationInstantiator.creatorProp("lineNr", javaTypeConstructType, 3), JsonLocationInstantiator.creatorProp("columnNr", javaTypeConstructType, 4)};
    }

    private static CreatorProperty creatorProp(final String str, final JavaType javaType, final int i2) {
        return CreatorProperty.construct(PropertyName.construct(str), javaType, null, null, null, null, i2, null, PropertyMetadata.STD_REQUIRED);
    }

    @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
    public Object createFromObjectWith(final DeserializationContext deserializationContext, final Object[] objArr) {
        return new JsonLocation(objArr[0], JsonLocationInstantiator._long(objArr[1]), JsonLocationInstantiator._long(objArr[2]), JsonLocationInstantiator._int(objArr[3]), JsonLocationInstantiator._int(objArr[4]));
    }

    private static final long _long(final Object obj) {
        if (null == obj) {
            return 0L;
        }
        return ((Number) obj).longValue();
    }

    private static final int _int(final Object obj) {
        if (null == obj) {
            return 0;
        }
        return ((Number) obj).intValue();
    }
}
