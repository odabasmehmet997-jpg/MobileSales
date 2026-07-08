package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class SqlBlobSerializer extends StdScalarSerializer<Blob> {
    public boolean isEmpty(SerializerProvider serializerProvider, Blob blob) {
        return blob == null;
    }
    public SqlBlobSerializer() {
        super(Blob.class);
    }
    public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws SQLException, IOException {
        _writeValue(blob, jsonGenerator, serializerProvider);
    }
    public void serializeWithType(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(blob, JsonToken.VALUE_EMBEDDED_OBJECT));
        try {
            _writeValue(blob, jsonGenerator, serializerProvider);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }
    protected void _writeValue(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws SQLException, IOException {
        InputStream binaryStream;
        try {
            binaryStream = blob.getBinaryStream();
        } catch (SQLException e2) {
            serializerProvider.reportMappingProblem(e2, "Failed to access `java.sql.Blob` value to write as binary value");
            binaryStream = null;
        }
        jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), binaryStream, -1);
    }
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectArrayFormat(javaType);
    }
}
