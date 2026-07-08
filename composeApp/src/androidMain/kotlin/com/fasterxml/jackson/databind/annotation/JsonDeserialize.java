package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.util.Converter;

public @interface JsonDeserialize {
    Class<?> as() default Void.class;
    Class<?> builder() default Void.class;
    Class<?> contentAs() default Void.class;
    Class<? extends Converter> contentConverter() default Converter.None.class;
    Class<? extends JsonDeserializer> contentUsing() default JsonDeserializer.None.class;
    Class<? extends Converter> converter() default Converter.None.class;
    Class<?> keyAs() default Void.class;
    Class<? extends KeyDeserializer> keyUsing() default KeyDeserializer.None.class;
    Class<? extends JsonDeserializer> using() default JsonDeserializer.None.class;
}
