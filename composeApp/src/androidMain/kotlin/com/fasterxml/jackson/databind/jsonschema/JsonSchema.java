package com.fasterxml.jackson.databind.jsonschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonSchema {
    private final ObjectNode schema;

    public JsonSchema(final ObjectNode objectNode) {
        schema = objectNode;
    }

    public String toString() {
        return schema.toString();
    }

    public int hashCode() {
        return schema.hashCode();
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || !(obj instanceof JsonSchema jsonSchema)) {
            return false;
        }
        final ObjectNode objectNode = schema;
        if (null == objectNode) {
            return null == jsonSchema.schema;
        }
        return objectNode.equals(jsonSchema.schema);
    }

    public static JsonNode getDefaultSchemaNode() {
        final ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("type", "any");
        return objectNode;
    }
}
