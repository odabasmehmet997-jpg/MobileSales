package com.fasterxml.jackson.annotation;


public @interface JsonSubTypes {

    @interface Type {
        String name() default "";

        String[] names() default {};

        Class<?> value();
    }

    Type[] value();
}
