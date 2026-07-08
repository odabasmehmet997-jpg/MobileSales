package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public interface Named {
    String getName();

    AnnotatedMethod getSetter();
}
