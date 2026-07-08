package com.fasterxml.jackson.annotation;


public @interface JsonRootName {
    String namespace() default "";
    String value();
}
