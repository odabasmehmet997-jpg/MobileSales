package com.proje.mobilesales.core.annotation;

import com.proje.mobilesales.core.annotation.Column;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ColumnProperty {
    int alterVersion() default 1;
    String defaultValue() default "";
    boolean isAutoIncrement() default false;
    boolean isNotNull() default false;
    boolean isPrimaryKey() default false;
    boolean isUnique() default false;
    Column.ColumnValueTypes type() default Column.ColumnValueTypes.NVARCHAR;
    boolean useProperty() default true;
    boolean useUpdate() default false;
    boolean useWhereStatement() default true;
}