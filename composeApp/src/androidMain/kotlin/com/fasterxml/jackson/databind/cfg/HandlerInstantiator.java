package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Converter;

public abstract class HandlerInstantiator {
    public Converter<?, ?> converterInstance(final MapperConfig<?> mapperConfig, final Annotated annotated, final Class<?> cls) {
        return null;
    }
    public abstract JsonDeserializer<?> deserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<?> cls);
    public Object includeFilterInstance(final SerializationConfig serializationConfig, final BeanPropertyDefinition beanPropertyDefinition, final Class<?> cls) {
        return null;
    }
    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<?> cls);
    public PropertyNamingStrategy namingStrategyInstance(final MapperConfig<?> mapperConfig, final Annotated annotated, final Class<?> cls) {
        return null;
    }
    public ObjectIdGenerator<?> objectIdGeneratorInstance(final MapperConfig<?> mapperConfig, final Annotated annotated, final Class<?> cls) {
        return null;
    }
    public ObjectIdResolver resolverIdGeneratorInstance(final MapperConfig<?> mapperConfig, final Annotated annotated, final Class<?> cls) {
        return null;
    }
    public abstract JsonSerializer<?> serializerInstance(SerializationConfig serializationConfig, Annotated annotated, Class<?> cls);
    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls);
    public abstract TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<?> cls);
    public ValueInstantiator valueInstantiatorInstance(final MapperConfig<?> mapperConfig, final Annotated annotated, final Class<?> cls) {
        return null;
    }
    public VirtualBeanPropertyWriter virtualPropertyWriterInstance(final MapperConfig<?> mapperConfig, final Class<?> cls) {
        return null;
    }
}
