package com.fasterxml.jackson.databind.annotation;

public @interface JsonPOJOBuilder {
    String buildMethodName() default "build";
    String withPrefix() default "with";
    class Value {
        public String buildMethodName() {
            return "";
        }
    }
}
