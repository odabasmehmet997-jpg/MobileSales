package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoreXMLSerializersXMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> implements ContextualSerializer {
    static final CoreXMLSerializersXMLGregorianCalendarSerializer instance = new CoreXMLSerializersXMLGregorianCalendarSerializer();
    final JsonSerializer<Object> _delegate;
    public CoreXMLSerializersXMLGregorianCalendarSerializer() {
        this(CalendarSerializer.instance);
    }
    protected CoreXMLSerializersXMLGregorianCalendarSerializer(JsonSerializer<?> jsonSerializer) {
        super(XMLGregorianCalendar.class);
        this._delegate = (JsonSerializer<Object>) jsonSerializer;
    }
    public JsonSerializer<?> getDelegatee() {
        return this._delegate;
    }
    public boolean isEmpty(SerializerProvider serializerProvider, XMLGregorianCalendar xMLGregorianCalendar) {
        return this._delegate.isEmpty(serializerProvider, _convert(xMLGregorianCalendar));
    }
    public void serialize(XMLGregorianCalendar xMLGregorianCalendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        this._delegate.serialize(_convert(xMLGregorianCalendar), jsonGenerator, serializerProvider);
    }
    public void serializeWithType(XMLGregorianCalendar xMLGregorianCalendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        this._delegate.serializeWithType(_convert(xMLGregorianCalendar), jsonGenerator, serializerProvider, typeSerializer);
    }
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        this._delegate.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, null);
    }
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializerHandlePrimaryContextualization = serializerProvider.handlePrimaryContextualization(this._delegate, beanProperty);
        return jsonSerializerHandlePrimaryContextualization != this._delegate ? new CoreXMLSerializersXMLGregorianCalendarSerializer(jsonSerializerHandlePrimaryContextualization) : this;
    }
    protected Calendar _convert(XMLGregorianCalendar xMLGregorianCalendar) {
        if (xMLGregorianCalendar == null) {
            return null;
        }
        return xMLGregorianCalendar.toGregorianCalendar();
    }
}
