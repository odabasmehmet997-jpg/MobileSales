package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface JsonTypeInfo {


    enum EnumC1184As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY,
        EXISTING_PROPERTY
    }
    abstract class None {
    }

    Class<?> defaultImpl() default JsonTypeInfo.class;

    EnumC1184As include() default EnumC1184As.PROPERTY;

    String property() default "";

    EnumC1185Id use();

    boolean visible() default false;

    enum EnumC1185Id {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        DEDUCTION(null),
        CUSTOM(null);

        private final String _defaultPropertyName;

        EnumC1185Id(final String str) {
            _defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return _defaultPropertyName;
        }
    }
}
