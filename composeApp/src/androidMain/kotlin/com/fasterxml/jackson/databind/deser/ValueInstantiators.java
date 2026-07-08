package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;

public interface ValueInstantiators {
    class Base implements ValueInstantiators {
        public ValueInstantiator findValueInstantiator(final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final ValueInstantiator valueInstantiator) {
            return valueInstantiator;
        }
    }
    default ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
        return new Base().findValueInstantiator(deserializationConfig, beanDescription, valueInstantiator);
    }
}
