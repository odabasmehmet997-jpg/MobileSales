package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
 
public abstract class JsonNode extends JsonSerializable.Base implements TreeNode, Iterable<JsonNode> {
    protected abstract JsonNode _at(JsonPointer jsonPointer);
    protected <T extends JsonNode> T _this() {
        return (T) this;
    }
    public boolean asBoolean(final boolean z) {
        return z;
    }
    public double asDouble(final double d2) {
        return d2;
    }
    public int asInt(final int i2) {
        return i2;
    }
    public long asLong(final long j2) {
        return j2;
    }
    public abstract String asText();
    public byte[] binaryValue() throws IOException {
        return null;
    }
    public boolean booleanValue() {
        return false;
    }
    public boolean canConvertToInt() {
        return false;
    }
    public boolean canConvertToLong() {
        return false;
    }
    public abstract <T extends JsonNode> T deepCopy();
    public double doubleValue() {
        return 0.0d;
    }
    public abstract boolean equals(Object obj);
    public abstract JsonNode findParent(String str);
    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);
    public abstract JsonNode findPath(String str);
    public abstract JsonNode findValue(String str);
    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);
    public abstract List<String> findValuesAsText(String str, List<String> list);
    public float floatValue() {
        return 0.0f;
    }
    public abstract JsonNode get(int i2);
    public JsonNode get(final String str) {
        return null;
    }
    public abstract JsonNodeType getNodeType();
    public int intValue() {
        return 0;
    }
    public boolean isArray() {
        return false;
    }
    public boolean isBigDecimal() {
        return false;
    }
    public boolean isBigInteger() {
        return false;
    }
    public boolean isDouble() {
        return false;
    }
    public boolean isFloat() {
        return false;
    }
    public boolean isFloatingPointNumber() {
        return false;
    }
    public boolean isInt() {
        return false;
    }
    public boolean isIntegralNumber() {
        return false;
    }
    public boolean isLong() {
        return false;
    }
    public boolean isMissingNode() {
        return false;
    }
    public boolean isObject() {
        return false;
    }
    public boolean isShort() {
        return false;
    }
    public long longValue() {
        return 0L;
    }
    public Number numberValue() {
        return null;
    }
    public abstract JsonNode path(int i2);
    public abstract JsonNode path(String str);
    public short shortValue() {
        return (short) 0;
    }
    public int size() {
        return 0;
    }
    public String textValue() {
        return null;
    }
    public abstract String toString();
    protected JsonNode() {
    }
    public boolean isEmpty() {
        return 0 == size();
    }
    enum C11881 {
        ;
        static final int[] SwitchMapcomfasterxmljacksondatabindnodeJsonNodeType;

        static {
            final int[] iArr = new int[JsonNodeType.values().length];
            SwitchMapcomfasterxmljacksondatabindnodeJsonNodeType = iArr;
            try {
                iArr[JsonNodeType.ARRAY.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11881.SwitchMapcomfasterxmljacksondatabindnodeJsonNodeType[JsonNodeType.OBJECT.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11881.SwitchMapcomfasterxmljacksondatabindnodeJsonNodeType[JsonNodeType.MISSING.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }
    public final boolean isValueNode() {
        final int i2 = C11881.SwitchMapcomfasterxmljacksondatabindnodeJsonNodeType[this.getNodeType().ordinal()];
        return 1 != i2 && 2 != i2 && 3 != i2;
    }
    public final boolean isContainerNode() {
        final JsonNodeType nodeType = this.getNodeType();
        return JsonNodeType.OBJECT == nodeType || JsonNodeType.ARRAY == nodeType;
    }
    public Iterator<String> fieldNames() {
        return ClassUtil.emptyIterator();
    }
    public final JsonNode at(final JsonPointer jsonPointer) {
        if (jsonPointer.matches()) {
            return this;
        }
        final JsonNode jsonNode_at = this._at(jsonPointer);
        if (null == jsonNode_at) {
            return MissingNode.getInstance();
        }
        return jsonNode_at.at(jsonPointer.tail());
    }
    public final JsonNode at(final String str) {
        return this.at(JsonPointer.compile(str));
    }
    public final boolean isPojo() {
        return JsonNodeType.POJO == getNodeType();
    }
    public final boolean isNumber() {
        return JsonNodeType.NUMBER == getNodeType();
    }
    public final boolean isTextual() {
        return JsonNodeType.STRING == getNodeType();
    }
    public final boolean isBoolean() {
        return JsonNodeType.BOOLEAN == getNodeType();
    }
    public final boolean isNull() {
        return JsonNodeType.NULL == getNodeType();
    }
    public final boolean isBinary() {
        return JsonNodeType.BINARY == getNodeType();
    }
    public boolean canConvertToExactIntegral() {
        return this.isIntegralNumber();
    }
    public BigDecimal decimalValue() {
        return BigDecimal.ZERO;
    }
    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }
    public String asText(final String str) {
        final String strAsText = this.asText();
        return null == strAsText ? str : strAsText;
    }
    public int asInt() {
        return this.asInt(0);
    }
    public long asLong() {
        return this.asLong(0L);
    }
    public double asDouble() {
        return this.asDouble(0.0d);
    }
    public boolean asBoolean() {
        return this.asBoolean(false);
    }
    public <T extends JsonNode> T require() throws IllegalArgumentException {
        return this._this();
    }
    public <T extends JsonNode> T requireNonNull() throws IllegalArgumentException {
        return this._this();
    }
    public JsonNode required(final String str) throws IllegalArgumentException {
        return this._reportRequiredViolation("Node of type `%s` has no fields", this.getClass().getName());
    }
    public JsonNode required(final int i2) throws IllegalArgumentException {
        return this._reportRequiredViolation("Node of type `%s` has no indexed values", this.getClass().getName());
    }
    public JsonNode requiredAt(final String str) throws IllegalArgumentException {
        return this.requiredAt(JsonPointer.compile(str));
    }
    public final JsonNode requiredAt(final JsonPointer jsonPointer) throws IllegalArgumentException {
        JsonNode jsonNode_at = this;
        for (JsonPointer jsonPointerTail = jsonPointer; !jsonPointerTail.matches(); jsonPointerTail = jsonPointerTail.tail()) {
            jsonNode_at = jsonNode_at._at(jsonPointerTail);
            if (null == jsonNode_at) {
                this._reportRequiredViolation("No node at '%s' (unmatched part: '%s')", jsonPointer, jsonPointerTail);
            }
        }
        return jsonNode_at;
    }
    public boolean has(final String str) {
        return null != get(str);
    }
    public boolean has(final int i2) {
        return null != get(i2);
    }
    public boolean hasNonNull(final String str) {
        final JsonNode jsonNode = this.get(str);
        return null != jsonNode && !jsonNode.isNull();
    }
    public boolean hasNonNull(final int i2) {
        final JsonNode jsonNode = this.get(i2);
        return null != jsonNode && !jsonNode.isNull();
    }
    public final Iterator<JsonNode> iterator() {
        return this.elements();
    }
    public Iterator<JsonNode> elements() {
        return ClassUtil.emptyIterator();
    }
    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return ClassUtil.emptyIterator();
    }
    public final List<JsonNode> findValues(final String str) {
        final List<JsonNode> listFindValues = this.findValues(str, null);
        return null == listFindValues ? Collections.emptyList() : listFindValues;
    }
    public final List<String> findValuesAsText(final String str) {
        final List<String> listFindValuesAsText = this.findValuesAsText(str, null);
        return null == listFindValuesAsText ? Collections.emptyList() : listFindValuesAsText;
    }
    public final List<JsonNode> findParents(final String str) {
        final List<JsonNode> listFindParents = this.findParents(str, null);
        return null == listFindParents ? Collections.emptyList() : listFindParents;
    }
    public <T extends JsonNode> T with(final String str) {
        throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + this.getClass().getName() + "), cannot call with() on it");
    }
    public <T extends JsonNode> T withArray(final String str) {
        throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + this.getClass().getName() + "), cannot call withArray() on it");
    }
    public boolean equals(final Comparator<JsonNode> comparator, final JsonNode jsonNode) {
        return 0 == comparator.compare(this, jsonNode);
    }
    public String toPrettyString() {
        return this.toString();
    }
    protected <T> T _reportRequiredViolation(final String str, final Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }
}
