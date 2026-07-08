package com.proje.mobilesales.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface Tables {
    int alterVersion() default 1;
    boolean appTable() default false;
    String name() default "";
    boolean useNetsisUpdate() default false;
    boolean useUpdate() default false;
}
