package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.util.Collection;

public abstract class SubtypeResolver {
    public SubtypeResolver copy() {
        return this;
    }

    public abstract void registerSubtypes(Collection<Class<?>> collection);

    public abstract void registerSubtypes(NamedType... namedTypeArr);

    public abstract void registerSubtypes(Class<?>... clsArr);

    public Collection<NamedType> collectAndResolveSubtypesByClass(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final JavaType javaType) {
        return this.collectAndResolveSubtypes(annotatedMember, mapperConfig, mapperConfig.getAnnotationIntrospector(), javaType);
    }

    public Collection<NamedType> collectAndResolveSubtypesByClass(final MapperConfig<?> mapperConfig, final AnnotatedClass annotatedClass) {
        return this.collectAndResolveSubtypes(annotatedClass, mapperConfig, mapperConfig.getAnnotationIntrospector());
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final JavaType javaType) {
        return this.collectAndResolveSubtypes(annotatedMember, mapperConfig, mapperConfig.getAnnotationIntrospector(), javaType);
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(final MapperConfig<?> mapperConfig, final AnnotatedClass annotatedClass) {
        return this.collectAndResolveSubtypes(annotatedClass, mapperConfig, mapperConfig.getAnnotationIntrospector());
    }

    public Collection<NamedType> collectAndResolveSubtypes(final AnnotatedMember annotatedMember, final MapperConfig<?> mapperConfig, final AnnotationIntrospector annotationIntrospector, final JavaType javaType) {
        return this.collectAndResolveSubtypesByClass(mapperConfig, annotatedMember, javaType);
    }

    public Collection<NamedType> collectAndResolveSubtypes(final AnnotatedClass annotatedClass, final MapperConfig<?> mapperConfig, final AnnotationIntrospector annotationIntrospector) {
        return this.collectAndResolveSubtypesByClass(mapperConfig, annotatedClass);
    }
}
