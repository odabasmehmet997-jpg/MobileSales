package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
public @interface JsonNaming {
    Class<? extends PropertyNamingStrategy> value() default PropertyNamingStrategy.class;
}
