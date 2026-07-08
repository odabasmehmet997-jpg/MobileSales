package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;

public interface KeyDeserializers {
    KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException;
}
