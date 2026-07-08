package com.fasterxml.jackson.annotation;

public @interface JsonAlias {
    String[] value() default {};
}
