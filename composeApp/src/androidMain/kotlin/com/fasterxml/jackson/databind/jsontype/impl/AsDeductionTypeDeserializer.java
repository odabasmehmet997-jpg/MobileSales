package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.util.*;

public class AsDeductionTypeDeserializer extends AsPropertyTypeDeserializer {
    private static final long serialVersionUID = 1;
    private final Map<String, Integer> fieldBitIndex;
    private final Map<BitSet, String> subtypeFingerprints;

    public AsDeductionTypeDeserializer(final JavaType javaType, final TypeIdResolver typeIdResolver, final JavaType javaType2, final DeserializationConfig deserializationConfig, final Collection<NamedType> collection) {
        super(javaType, typeIdResolver, null, false, javaType2, null);
        fieldBitIndex = new HashMap();
        subtypeFingerprints = this.buildFingerprints(deserializationConfig, collection);
    }

    public AsDeductionTypeDeserializer(final AsDeductionTypeDeserializer asDeductionTypeDeserializer, final BeanProperty beanProperty) {
        super(asDeductionTypeDeserializer, beanProperty);
        fieldBitIndex = asDeductionTypeDeserializer.fieldBitIndex;
        subtypeFingerprints = asDeductionTypeDeserializer.subtypeFingerprints;
    }
     public TypeDeserializer forProperty(final BeanProperty beanProperty) {
        return beanProperty == _property ? this : new AsDeductionTypeDeserializer(this, beanProperty);
    }

    protected Map<BitSet, String> buildFingerprints(final DeserializationConfig deserializationConfig, final Collection<NamedType> collection) {
        final boolean zIsEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        final HashMap map = new HashMap();
        int i2 = 0;
        for (final NamedType namedType : collection) {
            final List<BeanPropertyDefinition> listFindProperties = deserializationConfig.introspect(deserializationConfig.getTypeFactory().constructType(namedType.getType())).findProperties();
            final BitSet bitSet = new BitSet(listFindProperties.size() + i2);
            final Iterator<BeanPropertyDefinition> it = listFindProperties.iterator();
            while (it.hasNext()) {
                String name = it.next().getName();
                if (zIsEnabled) {
                    name = name.toLowerCase();
                }
                Integer numValueOf = fieldBitIndex.get(name);
                if (null == numValueOf) {
                    numValueOf = Integer.valueOf(i2);
                    fieldBitIndex.put(name, Integer.valueOf(i2));
                    i2++;
                }
                bitSet.set(numValueOf.intValue());
            }
            final String str = (String) map.put(bitSet, namedType.getType().getName());
            if (null != str) {
                throw new IllegalStateException(String.format("Subtypes %s and %s have the same signature and cannot be uniquely deduced.", str, namedType.getType().getName()));
            }
        }
        return map;
    }
     public Object deserializeTypedFromObject(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            jsonTokenCurrentToken = jsonParser.nextToken();
        } else if (JsonToken.FIELD_NAME != jsonTokenCurrentToken) {
            return this._deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, null, "Unexpected input");
        }
        final LinkedList linkedList = new LinkedList(subtypeFingerprints.keySet());
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        final boolean zIsEnabled = deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            String strCurrentName = jsonParser.currentName();
            if (zIsEnabled) {
                strCurrentName = strCurrentName.toLowerCase();
            }
            tokenBuffer.copyCurrentStructure(jsonParser);
            final Integer num = fieldBitIndex.get(strCurrentName);
            if (null != num) {
                AsDeductionTypeDeserializer.prune(linkedList, num.intValue());
                if (1 == linkedList.size()) {
                    return this._deserializeTypedForId(jsonParser, deserializationContext, tokenBuffer, subtypeFingerprints.get(linkedList.get(0)));
                }
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        return this._deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, tokenBuffer, String.format("Cannot deduce unique subtype of %s (%d candidates match)", ClassUtil.getTypeDescription(_baseType), Integer.valueOf(linkedList.size())));
    }

    private static void prune(final List<BitSet> list, final int i2) {
        final Iterator<BitSet> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().get(i2)) {
                it.remove();
            }
        }
    }
}
