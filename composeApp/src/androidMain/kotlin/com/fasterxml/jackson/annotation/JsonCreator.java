package com.fasterxml.jackson.annotation;

public @interface JsonCreator {

    enum Mode {
        DEFAULT,
        DELEGATING,
        PROPERTIES,
        DISABLED
    }

    Mode mode() default Mode.DEFAULT;
}
