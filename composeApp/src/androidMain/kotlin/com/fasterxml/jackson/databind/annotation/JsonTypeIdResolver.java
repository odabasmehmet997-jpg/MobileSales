package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
public @interface JsonTypeIdResolver {
    Class<? extends TypeIdResolver> value();
}
