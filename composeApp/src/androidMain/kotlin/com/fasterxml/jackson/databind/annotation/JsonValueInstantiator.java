package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.databind.deser.ValueInstantiator;

public @interface JsonValueInstantiator {
    Class<? extends ValueInstantiator> value();
}
