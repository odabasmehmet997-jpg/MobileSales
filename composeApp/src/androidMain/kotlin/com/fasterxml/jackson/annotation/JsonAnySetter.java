package com.fasterxml.jackson.annotation;

public @interface JsonAnySetter {
    boolean enabled() default true;
}
