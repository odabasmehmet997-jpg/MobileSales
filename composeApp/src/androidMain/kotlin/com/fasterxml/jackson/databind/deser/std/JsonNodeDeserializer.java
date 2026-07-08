package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
public class JsonNodeDeserializer extends JsonNodeDeserializer2<JsonNode> {
    private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }
    public boolean isCachable() {
        return super.isCachable();
    }

    public LogicalType logicalType() {
        return super.logicalType();
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return super.supportsUpdate(deserializationConfig);
    }

    protected JsonNodeDeserializer() {
        super(JsonNode.class, null);
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(final Class<?> cls) {
        if (ObjectNode.class == cls) {
            return ObjectDeserializer.getInstance();
        }
        if (ArrayNode.class == cls) {
            return ArrayDeserializer.getInstance();
        }
        return JsonNodeDeserializer.instance;
    }
    public JsonNode getNullValue(final DeserializationContext deserializationContext) {
        return deserializationContext.getNodeFactory().m827nullNode();
    }
   public JsonNode deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (1 == iCurrentTokenId) {
            return this.deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
        }
        if (3 == iCurrentTokenId) {
            return this.deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
        }
        return this.deserializeAny(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
    }

    static final class ObjectDeserializer extends JsonNodeDeserializer2<ObjectNode> {
        private static final ObjectDeserializer _instance = new ObjectDeserializer();
        private static final long serialVersionUID = 1;

        private ObjectDeserializer() {
            super(ObjectNode.class, Boolean.TRUE);
        }

        public static ObjectDeserializer getInstance() {
            return ObjectDeserializer._instance;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public ObjectNode deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedStartObjectToken()) {
                return this.deserializeObject(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            }
            if (jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return this.deserializeObjectAtName(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            }
            if (jsonParser.hasToken(JsonToken.END_OBJECT)) {
                return deserializationContext.getNodeFactory().objectNode();
            }
            return (ObjectNode) deserializationContext.handleUnexpectedToken(ObjectNode.class, jsonParser);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public ObjectNode deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final ObjectNode objectNode) throws IOException {
            if (jsonParser.isExpectedStartObjectToken() || jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return (ObjectNode) this.updateObject(jsonParser, deserializationContext, objectNode);
            }
            return (ObjectNode) deserializationContext.handleUnexpectedToken(ObjectNode.class, jsonParser);
        }
    }

    static final class ArrayDeserializer extends JsonNodeDeserializer2<ArrayNode> {
        private static final ArrayDeserializer _instance = new ArrayDeserializer();
        private static final long serialVersionUID = 1;

        private ArrayDeserializer() {
            super(ArrayNode.class, Boolean.TRUE);
        }

        public static ArrayDeserializer getInstance() {
            return ArrayDeserializer._instance;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public ArrayNode deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.isExpectedStartArrayToken()) {
                return this.deserializeArray(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
            }
            return (ArrayNode) deserializationContext.handleUnexpectedToken(ArrayNode.class, jsonParser);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public ArrayNode deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final ArrayNode arrayNode) throws IOException {
            if (jsonParser.isExpectedStartArrayToken()) {
                return (ArrayNode) this.updateArray(jsonParser, deserializationContext, arrayNode);
            }
            return (ArrayNode) deserializationContext.handleUnexpectedToken(ArrayNode.class, jsonParser);
        }
    }
}
