package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;

public @interface JsonTypeResolver {
    Class<? extends TypeResolverBuilder<?>> value();
}
