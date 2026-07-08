package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

public final class SerializerFactoryConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private final Serializers[] _additionalKeySerializers;
    private final Serializers[] _additionalSerializers;
    private final BeanSerializerModifier[] _modifiers;
    private static final Serializers[] NO_SERIALIZERS = new Serializers[0];
    private static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];
    public SerializerFactoryConfig() {
        this(null, null, null);
    }
    private SerializerFactoryConfig(final Serializers[] serializersArr, final Serializers[] serializersArr2, final BeanSerializerModifier[] beanSerializerModifierArr) {
        _additionalSerializers = null == serializersArr ? SerializerFactoryConfig.NO_SERIALIZERS : serializersArr;
        _additionalKeySerializers = null == serializersArr2 ? SerializerFactoryConfig.NO_SERIALIZERS : serializersArr2;
        _modifiers = null == beanSerializerModifierArr ? SerializerFactoryConfig.NO_MODIFIERS : beanSerializerModifierArr;
    }
    public SerializerFactoryConfig withAdditionalSerializers(final Serializers serializers) {
        if (null == serializers) {
            throw new IllegalArgumentException("Cannot pass null Serializers");
        }
        return new SerializerFactoryConfig(ArrayBuilders.insertInListNoDup(_additionalSerializers, serializers), _additionalKeySerializers, _modifiers);
    }
    public SerializerFactoryConfig withAdditionalKeySerializers(final Serializers serializers) {
        if (null == serializers) {
            throw new IllegalArgumentException("Cannot pass null Serializers");
        }
        return new SerializerFactoryConfig(_additionalSerializers, ArrayBuilders.insertInListNoDup(_additionalKeySerializers, serializers), _modifiers);
    }
    public SerializerFactoryConfig withSerializerModifier(final BeanSerializerModifier beanSerializerModifier) {
        if (null == beanSerializerModifier) {
            throw new IllegalArgumentException("Cannot pass null modifier");
        }
        return new SerializerFactoryConfig(_additionalSerializers, _additionalKeySerializers, ArrayBuilders.insertInListNoDup(_modifiers, beanSerializerModifier));
    }
    public boolean hasSerializers() {
        return 0 < this._additionalSerializers.length;
    }
    public boolean hasKeySerializers() {
        return 0 < this._additionalKeySerializers.length;
    }
    public boolean hasSerializerModifiers() {
        return 0 < this._modifiers.length;
    }
    public Iterable<Serializers> serializers() {
        return new ArrayIterator(_additionalSerializers);
    }
    public Iterable<Serializers> keySerializers() {
        return new ArrayIterator(_additionalKeySerializers);
    }
    public Iterable<BeanSerializerModifier> serializerModifiers() {
        return new ArrayIterator(_modifiers);
    }
}
