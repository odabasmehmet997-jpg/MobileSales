package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.Converter;


public @interface JsonSerialize {
    enum Inclusion {
        ALWAYS,
        NON_NULL,
        NON_DEFAULT,
        NON_EMPTY,
        DEFAULT_INCLUSION
    }
    enum Typing {
        DYNAMIC,
        STATIC,
        DEFAULT_TYPING
    }
    Class<?> as() default Void.class;
    Class<?> contentAs() default Void.class;
    Class<? extends Converter> contentConverter() default Converter.None.class;
    Class<? extends JsonSerializer> contentUsing() default JsonSerializer.None.class;
    Class<? extends Converter> converter() default Converter.None.class;
    Inclusion include() default Inclusion.DEFAULT_INCLUSION;
    Class<?> keyAs() default Void.class;
    Class<? extends JsonSerializer> keyUsing() default JsonSerializer.None.class;
    Class<? extends JsonSerializer> nullsUsing() default JsonSerializer.None.class;
    Typing typing() default Typing.DEFAULT_TYPING;
    Class<? extends JsonSerializer> using() default JsonSerializer.None.class;
}
