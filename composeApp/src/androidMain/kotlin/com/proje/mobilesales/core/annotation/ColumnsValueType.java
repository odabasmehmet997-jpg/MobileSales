package com.proje.mobilesales.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)

public @interface ColumnsValueType {
     int BLOB = 6;
     int BOOLEAN = 4;
     int DATE = 5;
     int DOUBLE = 2;
     int FLOAT = 3;
     int INT = 0;
     int NULL = 7;
     int STRING = 1;
}
