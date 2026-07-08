package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.*;

public interface Deserializers {
    abstract class Base implements Deserializers {
        
        public JsonDeserializer<?> findArrayDeserializer(final ArrayType arrayType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findBeanDeserializer(final JavaType javaType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findCollectionDeserializer(final CollectionType collectionType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findCollectionLikeDeserializer(final CollectionLikeType collectionLikeType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findEnumDeserializer(final Class<?> cls, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findMapDeserializer(final MapType mapType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findMapLikeDeserializer(final MapLikeType mapLikeType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findReferenceDeserializer(final ReferenceType referenceType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
            return null;
        }

        
        public JsonDeserializer<?> findTreeNodeDeserializer(final Class<? extends JsonNode> cls, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
            return null;
        }

        public boolean hasDeserializerFor(final DeserializationConfig deserializationConfig, final Class<?> cls) {
            return false;
        }
    }
    JsonDeserializer<?> findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException;
    JsonDeserializer<?> findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException;
    JsonDeserializer<?> findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findReferenceDeserializer(ReferenceType referenceType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException;
    JsonDeserializer<?> findTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException;
}
