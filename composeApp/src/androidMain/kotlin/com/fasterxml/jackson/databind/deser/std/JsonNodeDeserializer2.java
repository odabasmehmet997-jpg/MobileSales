package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

abstract class JsonNodeDeserializer2<T extends JsonNode> extends StdDeserializer<T> {
    protected final Boolean _supportsUpdates;
    public boolean isCachable() {
        return true;
    }

    protected JsonNodeDeserializer2(final Class<T> cls, final Boolean bool) {
        super(cls);
        _supportsUpdates = bool;
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return _supportsUpdates;
    }

    protected void _handleDuplicateField(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory, final String str, final ObjectNode objectNode, final JsonNode jsonNode, final JsonNode jsonNode2) throws JsonProcessingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            if (jsonNode.isArray()) {
                ((ArrayNode) jsonNode).add(jsonNode2);
                objectNode.replace(str, jsonNode);
            } else {
                final ArrayNode arrayNode = jsonNodeFactory.arrayNode();
                arrayNode.add(jsonNode);
                arrayNode.add(jsonNode2);
                objectNode.replace(str, arrayNode);
            }
        }
    }

    protected final ObjectNode deserializeObject(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        final ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strNextFieldName = jsonParser.nextFieldName();
        while (null != strNextFieldName) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == jsonTokenNextToken) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            final int iM307id = jsonTokenNextToken.m307id();
            if (1 == iM307id) {
                jsonNodeDeserializeObject = this.deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (3 == iM307id) {
                jsonNodeDeserializeObject = this.deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (6 == iM307id) {
                jsonNodeDeserializeObject = jsonNodeFactory.m834textNode(jsonParser.getText());
            } else if (7 == iM307id) {
                jsonNodeDeserializeObject = this._fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                switch (iM307id) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.m826booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.m826booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.m827nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = this._fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = this.deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            }
            final JsonNode jsonNode = jsonNodeDeserializeObject;
            final JsonNode jsonNodeReplace = objectNode.replace(strNextFieldName, jsonNode);
            if (null != jsonNodeReplace) {
                this._handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strNextFieldName, objectNode, jsonNodeReplace, jsonNode);
            }
            strNextFieldName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    protected final ObjectNode deserializeObjectAtName(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonNode jsonNodeDeserializeObject;
        final ObjectNode objectNode = jsonNodeFactory.objectNode();
        String strCurrentName = jsonParser.currentName();
        while (null != strCurrentName) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == jsonTokenNextToken) {
                jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
            }
            final int iM307id = jsonTokenNextToken.m307id();
            if (1 == iM307id) {
                jsonNodeDeserializeObject = this.deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (3 == iM307id) {
                jsonNodeDeserializeObject = this.deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (6 == iM307id) {
                jsonNodeDeserializeObject = jsonNodeFactory.m834textNode(jsonParser.getText());
            } else if (7 == iM307id) {
                jsonNodeDeserializeObject = this._fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                switch (iM307id) {
                    case 9:
                        jsonNodeDeserializeObject = jsonNodeFactory.m826booleanNode(true);
                        break;
                    case 10:
                        jsonNodeDeserializeObject = jsonNodeFactory.m826booleanNode(false);
                        break;
                    case 11:
                        jsonNodeDeserializeObject = jsonNodeFactory.m827nullNode();
                        break;
                    case 12:
                        jsonNodeDeserializeObject = this._fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                    default:
                        jsonNodeDeserializeObject = this.deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
                        break;
                }
            }
            final JsonNode jsonNode = jsonNodeDeserializeObject;
            final JsonNode jsonNodeReplace = objectNode.replace(strCurrentName, jsonNode);
            if (null != jsonNodeReplace) {
                this._handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, strCurrentName, objectNode, jsonNodeReplace, jsonNode);
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    /*  WARN: Removed duplicated region for block: B:27:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final JsonNode updateObject(final JsonParser jsonParser, final DeserializationContext deserializationContext, final ObjectNode objectNode) throws IOException {
        String strCurrentName;
        JsonNode jsonNodeDeserializeObject;
        if (jsonParser.isExpectedStartObjectToken()) {
            strCurrentName = jsonParser.nextFieldName();
        } else {
            if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return this.deserialize(jsonParser, deserializationContext);
            }
            strCurrentName = jsonParser.currentName();
        }
        while (null != strCurrentName) {
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            final JsonNode jsonNode = objectNode.get(strCurrentName);
            if (null != jsonNode) {
                if (jsonNode instanceof ObjectNode) {
                    if (JsonToken.START_OBJECT == jsonTokenNextToken) {
                        final JsonNode jsonNodeUpdateObject = this.updateObject(jsonParser, deserializationContext, (ObjectNode) jsonNode);
                        if (jsonNodeUpdateObject != jsonNode) {
                            objectNode.set(strCurrentName, jsonNodeUpdateObject);
                        }
                    }
                } else if ((jsonNode instanceof ArrayNode) && JsonToken.START_ARRAY == jsonTokenNextToken) {
                    final JsonNode jsonNodeUpdateArray = this.updateArray(jsonParser, deserializationContext, (ArrayNode) jsonNode);
                    if (jsonNodeUpdateArray != jsonNode) {
                        objectNode.set(strCurrentName, jsonNodeUpdateArray);
                    }
                }
            } else {
                if (null == jsonTokenNextToken) {
                    jsonTokenNextToken = JsonToken.NOT_AVAILABLE;
                }
                final JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
                final int iM307id = jsonTokenNextToken.m307id();
                if (1 == iM307id) {
                    jsonNodeDeserializeObject = this.deserializeObject(jsonParser, deserializationContext, nodeFactory);
                } else if (3 == iM307id) {
                    jsonNodeDeserializeObject = this.deserializeArray(jsonParser, deserializationContext, nodeFactory);
                } else if (6 == iM307id) {
                    jsonNodeDeserializeObject = nodeFactory.m834textNode(jsonParser.getText());
                } else if (7 == iM307id) {
                    jsonNodeDeserializeObject = this._fromInt(jsonParser, deserializationContext, nodeFactory);
                } else {
                    switch (iM307id) {
                        case 9:
                            jsonNodeDeserializeObject = nodeFactory.m826booleanNode(true);
                            break;
                        case 10:
                            jsonNodeDeserializeObject = nodeFactory.m826booleanNode(false);
                            break;
                        case 11:
                            jsonNodeDeserializeObject = nodeFactory.m827nullNode();
                            break;
                        case 12:
                            jsonNodeDeserializeObject = this._fromEmbedded(jsonParser, deserializationContext, nodeFactory);
                            break;
                        default:
                            jsonNodeDeserializeObject = this.deserializeAny(jsonParser, deserializationContext, nodeFactory);
                            break;
                    }
                }
                objectNode.set(strCurrentName, jsonNodeDeserializeObject);
            }
            strCurrentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    protected final ArrayNode deserializeArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        final ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            final JsonToken jsonTokenNextToken = jsonParser.nextToken();
            if (null == jsonTokenNextToken) {
                return arrayNode;
            }
            switch (jsonTokenNextToken.m307id()) {
                case 1:
                    arrayNode.add(this.deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(this.deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 3:
                    arrayNode.add(this.deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(jsonNodeFactory.m834textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(this._fromInt(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
                case 9:
                    arrayNode.add(jsonNodeFactory.m826booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(jsonNodeFactory.m826booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(jsonNodeFactory.m827nullNode());
                    break;
                case 12:
                    arrayNode.add(this._fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode updateArray(final JsonParser jsonParser, final DeserializationContext deserializationContext, final ArrayNode arrayNode) throws IOException {
        final JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (true) {
            switch (jsonParser.nextToken().m307id()) {
                case 1:
                    arrayNode.add(this.deserializeObject(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 2:
                case 5:
                case 8:
                default:
                    arrayNode.add(this.deserializeAny(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 3:
                    arrayNode.add(this.deserializeArray(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 4:
                    return arrayNode;
                case 6:
                    arrayNode.add(nodeFactory.m834textNode(jsonParser.getText()));
                    break;
                case 7:
                    arrayNode.add(this._fromInt(jsonParser, deserializationContext, nodeFactory));
                    break;
                case 9:
                    arrayNode.add(nodeFactory.m826booleanNode(true));
                    break;
                case 10:
                    arrayNode.add(nodeFactory.m826booleanNode(false));
                    break;
                case 11:
                    arrayNode.add(nodeFactory.m827nullNode());
                    break;
                case 12:
                    arrayNode.add(this._fromEmbedded(jsonParser, deserializationContext, nodeFactory));
                    break;
            }
        }
    }

    protected final JsonNode deserializeAny(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        final int iCurrentTokenId = jsonParser.currentTokenId();
        if (2 == iCurrentTokenId) {
            return jsonNodeFactory.objectNode();
        }
        switch (iCurrentTokenId) {
            case 5:
                return this.deserializeObjectAtName(jsonParser, deserializationContext, jsonNodeFactory);
            case 6:
                return jsonNodeFactory.m834textNode(jsonParser.getText());
            case 7:
                return this._fromInt(jsonParser, deserializationContext, jsonNodeFactory);
            case 8:
                return this._fromFloat(jsonParser, deserializationContext, jsonNodeFactory);
            case 9:
                return jsonNodeFactory.m826booleanNode(true);
            case 10:
                return jsonNodeFactory.m826booleanNode(false);
            case 11:
                return jsonNodeFactory.m827nullNode();
            case 12:
                return this._fromEmbedded(jsonParser, deserializationContext, jsonNodeFactory);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(this.handledType(), jsonParser);
        }
    }

    protected final JsonNode _fromInt(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        final JsonParser.NumberType numberType;
        final int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if (0 != (StdDeserializer.F_MASK_INT_COERCIONS & deserializationFeatures)) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.LONG;
            } else {
                numberType = jsonParser.getNumberType();
            }
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (JsonParser.NumberType.INT == numberType) {
            return jsonNodeFactory.m831numberNode(jsonParser.getIntValue());
        }
        if (JsonParser.NumberType.LONG == numberType) {
            return jsonNodeFactory.m832numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromFloat(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        final JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (JsonParser.NumberType.BIG_DECIMAL == numberType) {
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (jsonParser.isNaN()) {
                return jsonNodeFactory.m829numberNode(jsonParser.getDoubleValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
        }
        if (JsonParser.NumberType.FLOAT == numberType) {
            return jsonNodeFactory.m830numberNode(jsonParser.getFloatValue());
        }
        return jsonNodeFactory.m829numberNode(jsonParser.getDoubleValue());
    }

    protected final JsonNode _fromEmbedded(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JsonNodeFactory jsonNodeFactory) throws IOException {
        final Object embeddedObject = jsonParser.getEmbeddedObject();
        if (null == embeddedObject) {
            return jsonNodeFactory.m827nullNode();
        }
        if (byte[].class == embeddedObject.getClass()) {
            return jsonNodeFactory.m824binaryNode((byte[]) embeddedObject);
        }
        if (embeddedObject instanceof RawValue) {
            return jsonNodeFactory.rawValueNode((RawValue) embeddedObject);
        }
        if (embeddedObject instanceof JsonNode) {
            return (JsonNode) embeddedObject;
        }
        return jsonNodeFactory.pojoNode(embeddedObject);
    }
}
