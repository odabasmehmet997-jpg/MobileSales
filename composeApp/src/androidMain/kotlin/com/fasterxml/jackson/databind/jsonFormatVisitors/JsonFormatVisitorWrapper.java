package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
 
public interface JsonFormatVisitorWrapper extends JsonFormatVisitorWithSerializerProvider {
    JsonAnyFormatVisitor expectAnyFormat(JavaType javaType) throws JsonMappingException;

    JsonArrayFormatVisitor expectArrayFormat(JavaType javaType) throws JsonMappingException;

    JsonBooleanFormatVisitor expectBooleanFormat(JavaType javaType) throws JsonMappingException;

    JsonIntegerFormatVisitor expectIntegerFormat(JavaType javaType) throws JsonMappingException;

    JsonMapFormatVisitor expectMapFormat(JavaType javaType) throws JsonMappingException;

    JsonNullFormatVisitor expectNullFormat(JavaType javaType) throws JsonMappingException;

    JsonNumberFormatVisitor expectNumberFormat(JavaType javaType) throws JsonMappingException;

    JsonObjectFormatVisitor expectObjectFormat(JavaType javaType) throws JsonMappingException;

    JsonStringFormatVisitor expectStringFormat(JavaType javaType) throws JsonMappingException;

    class Base implements JsonFormatVisitorWrapper {
        protected SerializerProvider _provider;

        
        public JsonAnyFormatVisitor expectAnyFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonArrayFormatVisitor expectArrayFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonBooleanFormatVisitor expectBooleanFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonIntegerFormatVisitor expectIntegerFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonMapFormatVisitor expectMapFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonNullFormatVisitor expectNullFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonNumberFormatVisitor expectNumberFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonObjectFormatVisitor expectObjectFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        
        public JsonStringFormatVisitor expectStringFormat(final JavaType javaType) throws JsonMappingException {
            return null;
        }

        public Base() {
        }

        public Base(final SerializerProvider serializerProvider) {
            _provider = serializerProvider;
        }

        public SerializerProvider getProvider() {
            return _provider;
        }

        public void setProvider(final SerializerProvider serializerProvider) {
            _provider = serializerProvider;
        }
    }
}
