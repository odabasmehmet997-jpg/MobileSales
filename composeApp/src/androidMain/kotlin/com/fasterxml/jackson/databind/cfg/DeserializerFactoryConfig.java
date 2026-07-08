package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

public class DeserializerFactoryConfig implements Serializable {
    private static final long serialVersionUID = 1;
    protected final AbstractTypeResolver[] _abstractTypeResolvers;
    protected final Deserializers[] _additionalDeserializers;
    protected final KeyDeserializers[] _additionalKeyDeserializers;
    protected final BeanDeserializerModifier[] _modifiers;
    protected final ValueInstantiators[] _valueInstantiators;
    protected static final Deserializers[] NO_DESERIALIZERS = new Deserializers[0];
    protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
    protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
    protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
    protected static final KeyDeserializers[] DEFAULT_KEY_DESERIALIZERS = {new StdKeyDeserializers()};
    public DeserializerFactoryConfig() {
        this(null, null, null, null, null);
    }
    protected DeserializerFactoryConfig(final Deserializers[] deserializersArr, final KeyDeserializers[] keyDeserializersArr, final BeanDeserializerModifier[] beanDeserializerModifierArr, final AbstractTypeResolver[] abstractTypeResolverArr, final ValueInstantiators[] valueInstantiatorsArr) {
        _additionalDeserializers = null == deserializersArr ? DeserializerFactoryConfig.NO_DESERIALIZERS : deserializersArr;
        _additionalKeyDeserializers = null == keyDeserializersArr ? DeserializerFactoryConfig.DEFAULT_KEY_DESERIALIZERS : keyDeserializersArr;
        _modifiers = null == beanDeserializerModifierArr ? DeserializerFactoryConfig.NO_MODIFIERS : beanDeserializerModifierArr;
        _abstractTypeResolvers = null == abstractTypeResolverArr ? DeserializerFactoryConfig.NO_ABSTRACT_TYPE_RESOLVERS : abstractTypeResolverArr;
        _valueInstantiators = null == valueInstantiatorsArr ? DeserializerFactoryConfig.NO_VALUE_INSTANTIATORS : valueInstantiatorsArr;
    }
    public DeserializerFactoryConfig withAdditionalDeserializers(final Deserializers deserializers) {
        if (null == deserializers) {
            throw new IllegalArgumentException("Cannot pass null Deserializers");
        }
        return new DeserializerFactoryConfig(ArrayBuilders.insertInListNoDup(_additionalDeserializers, deserializers), _additionalKeyDeserializers, _modifiers, _abstractTypeResolvers, _valueInstantiators);
    }
    public DeserializerFactoryConfig withAdditionalKeyDeserializers(final KeyDeserializers keyDeserializers) {
        if (null == keyDeserializers) {
            throw new IllegalArgumentException("Cannot pass null KeyDeserializers");
        }
        return new DeserializerFactoryConfig(_additionalDeserializers, ArrayBuilders.insertInListNoDup(_additionalKeyDeserializers, keyDeserializers), _modifiers, _abstractTypeResolvers, _valueInstantiators);
    }
    public DeserializerFactoryConfig withDeserializerModifier(final BeanDeserializerModifier beanDeserializerModifier) {
        if (null == beanDeserializerModifier) {
            throw new IllegalArgumentException("Cannot pass null modifier");
        }
        return new DeserializerFactoryConfig(_additionalDeserializers, _additionalKeyDeserializers, ArrayBuilders.insertInListNoDup(_modifiers, beanDeserializerModifier), _abstractTypeResolvers, _valueInstantiators);
    }
    public DeserializerFactoryConfig withAbstractTypeResolver(final AbstractTypeResolver abstractTypeResolver) {
        if (null == abstractTypeResolver) {
            throw new IllegalArgumentException("Cannot pass null resolver");
        }
        return new DeserializerFactoryConfig(_additionalDeserializers, _additionalKeyDeserializers, _modifiers, ArrayBuilders.insertInListNoDup(_abstractTypeResolvers, abstractTypeResolver), _valueInstantiators);
    }
    public DeserializerFactoryConfig withValueInstantiators(final ValueInstantiators valueInstantiators) {
        if (null == valueInstantiators) {
            throw new IllegalArgumentException("Cannot pass null resolver");
        }
        return new DeserializerFactoryConfig(_additionalDeserializers, _additionalKeyDeserializers, _modifiers, _abstractTypeResolvers, ArrayBuilders.insertInListNoDup(_valueInstantiators, valueInstantiators));
    }
    public boolean hasDeserializers() {
        return 0 < this._additionalDeserializers.length;
    }
    public boolean hasKeyDeserializers() {
        return 0 < this._additionalKeyDeserializers.length;
    }
    public boolean hasDeserializerModifiers() {
        return 0 < this._modifiers.length;
    }
    public boolean hasAbstractTypeResolvers() {
        return 0 < this._abstractTypeResolvers.length;
    }
    public boolean hasValueInstantiators() {
        return 0 < this._valueInstantiators.length;
    }
    public Iterable<Deserializers> deserializers() {
        return new ArrayIterator(_additionalDeserializers);
    }
    public Iterable<KeyDeserializers> keyDeserializers() {
        return new ArrayIterator(_additionalKeyDeserializers);
    }
    public Iterable<BeanDeserializerModifier> deserializerModifiers() {
        return new ArrayIterator(_modifiers);
    }
    public Iterable<AbstractTypeResolver> abstractTypeResolvers() {
        return new ArrayIterator(_abstractTypeResolvers);
    }
    public Iterable<ValueInstantiators> valueInstantiators() {
        return new ArrayIterator(_valueInstantiators);
    }
}
