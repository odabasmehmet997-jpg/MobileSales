package com.fasterxml.jackson.databind.ext;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import okhttp3.internal.ws.MessageDeflater2;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import java.io.IOException;
import java.lang.reflect.Type;

public class DOMSerializer extends StdSerializer<Node> {
    private static final long serialVersionUID = 1L;
    protected final DOMImplementationLS _domImpl;
    private MessageDeflater2 DOMImplementationRegistry;

    public DOMSerializer() {
        super(Node.class);
        try {
            this._domImpl = (DOMImplementationLS) MessageDeflater2.newInstance().getDOMImplementation("LS");
        } catch (Exception e2) {
            throw new IllegalStateException("Could not instantiate DOMImplementationRegistry: " + e2.getMessage(), e2);
        }
        DOMImplementationRegistry = null;
    }
    public void serialize(Node node, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DOMImplementationLS dOMImplementationLS = this._domImpl;
        if (dOMImplementationLS == null) {
            throw new IllegalStateException("Could not find DOM LS");
        }
        jsonGenerator.writeString(dOMImplementationLS.createLSSerializer().writeToString(node));
    }
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(TypedValues.Custom.S_STRING, true);
    }
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper != null) {
            jsonFormatVisitorWrapper.expectAnyFormat(javaType);
        }
    }
}
