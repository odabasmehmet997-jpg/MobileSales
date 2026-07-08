package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.util.*;

public class ExternalTypeHandler {
    private final JavaType _beanType;
    private final Map<String, Object> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;
    protected ExternalTypeHandler(final JavaType javaType, final ExtTypedProperty[] extTypedPropertyArr, final Map<String, Object> map, final String[] strArr, final TokenBuffer[] tokenBufferArr) {
        _beanType = javaType;
        _properties = extTypedPropertyArr;
        _nameToPropertyIndex = map;
        _typeIds = strArr;
        _tokens = tokenBufferArr;
    }
    protected ExternalTypeHandler(final ExternalTypeHandler externalTypeHandler) {
        _beanType = externalTypeHandler._beanType;
        final ExtTypedProperty[] extTypedPropertyArr = externalTypeHandler._properties;
        _properties = extTypedPropertyArr;
        _nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        final int length = extTypedPropertyArr.length;
        _typeIds = new String[length];
        _tokens = new TokenBuffer[length];
    }
    public static Builder builder(final JavaType javaType) {
        return new Builder(javaType);
    }
    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }
    public boolean handleTypePropertyValue(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str, final Object obj) throws IOException {
        final Object obj2 = _nameToPropertyIndex.get(str);
        boolean z = false;
        if (null == obj2) {
            return false;
        }
        final String text = jsonParser.getText();
        if (obj2 instanceof List) {
            final Iterator it = ((List) obj2).iterator();
            while (it.hasNext()) {
                if (this._handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, ((Integer) it.next()).intValue())) {
                    z = true;
                }
            }
            return z;
        }
        return this._handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, ((Integer) obj2).intValue());
    }
    private boolean _handleTypePropertyValue(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str, final Object obj, final String str2, final int i2) throws IOException {
        if (!_properties[i2].hasTypePropertyName(str)) {
            return false;
        }
        if (null != obj && null != this._tokens[i2]) {
            this._deserializeAndSet(jsonParser, deserializationContext, obj, i2, str2);
            _tokens[i2] = null;
            return true;
        }
        _typeIds[i2] = str2;
        return true;
    }
    public boolean handlePropertyValue(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str, final Object obj) throws IOException {
        final Object obj2 = _nameToPropertyIndex.get(str);
        if (null == obj2) {
            return false;
        }
        if (obj2 instanceof List) {
            final Iterator it = ((List) obj2).iterator();
            final Integer num = (Integer) it.next();
            if (_properties[num.intValue()].hasTypePropertyName(str)) {
                final String text = jsonParser.getText();
                jsonParser.skipChildren();
                _typeIds[num.intValue()] = text;
                while (it.hasNext()) {
                    _typeIds[((Integer) it.next()).intValue()] = text;
                }
            } else {
                final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
                tokenBuffer.copyCurrentStructure(jsonParser);
                _tokens[num.intValue()] = tokenBuffer;
                while (it.hasNext()) {
                    _tokens[((Integer) it.next()).intValue()] = tokenBuffer;
                }
            }
            return true;
        }
        final int iIntValue = ((Integer) obj2).intValue();
        if (_properties[iIntValue].hasTypePropertyName(str)) {
            _typeIds[iIntValue] = jsonParser.getValueAsString();
            jsonParser.skipChildren();
            if (null != obj && null != this._tokens[iIntValue]) {
                final String[] strArr = _typeIds;
                final String str2 = strArr[iIntValue];
                strArr[iIntValue] = null;
                this._deserializeAndSet(jsonParser, deserializationContext, obj, iIntValue, str2);
                _tokens[iIntValue] = null;
            }
        } else {
            final TokenBuffer tokenBuffer2 = new TokenBuffer(jsonParser, deserializationContext);
            tokenBuffer2.copyCurrentStructure(jsonParser);
            _tokens[iIntValue] = tokenBuffer2;
            if (null != obj && null != this._typeIds[iIntValue]) {
            }
        }
        return true;
    }
    public Object complete(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final int length = _properties.length;
        for (int i2 = 0; i2 < length; i2++) {
            String defaultTypeId = _typeIds[i2];
            final ExtTypedProperty extTypedProperty = _properties[i2];
            if (null == defaultTypeId) {
                final TokenBuffer tokenBuffer = _tokens[i2];
                if (null != tokenBuffer) {
                    if (tokenBuffer.firstToken().isScalarValue()) {
                        final JsonParser jsonParserAsParser = tokenBuffer.asParser(jsonParser);
                        jsonParserAsParser.nextToken();
                        final SettableBeanProperty property = extTypedProperty.getProperty();
                        final Object objDeserializeIfNatural = TypeDeserializer.deserializeIfNatural(jsonParserAsParser, deserializationContext, property.getType());
                        if (null != objDeserializeIfNatural) {
                            property.set(obj, objDeserializeIfNatural);
                        }
                    }
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(_beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s' (and no 'defaultImpl' specified)", extTypedProperty.getTypePropertyName());
                    } else {
                        defaultTypeId = extTypedProperty.getDefaultTypeId();
                        if (null == defaultTypeId) {
                            deserializationContext.reportPropertyInputMismatch(_beanType, extTypedProperty.getProperty().getName(), "Invalid default type id for property '%s': `null` returned by TypeIdResolver", extTypedProperty.getTypePropertyName());
                        }
                    }
                }
            } else if (null == this._tokens[i2]) {
                final SettableBeanProperty property2 = extTypedProperty.getProperty();
                if (property2.isRequired() || deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    deserializationContext.reportPropertyInputMismatch(obj.getClass(), property2.getName(), "Missing property '%s' for external type id '%s'", property2.getName(), extTypedProperty.getTypePropertyName());
                }
                return obj;
            }
            this._deserializeAndSet(jsonParser, deserializationContext, obj, i2, defaultTypeId);
        }
        return obj;
    }
    public Object complete(final JsonParser jsonParser, final DeserializationContext deserializationContext, final PropertyValueBuffer propertyValueBuffer, final PropertyBasedCreator propertyBasedCreator) throws IOException {
        String defaultTypeId = "";
        final int length = _properties.length;
        final Object[] objArr = new Object[length];
        for (int i2 = 0; i2 < length; i2++) {
            final String str = _typeIds[i2];
            final ExtTypedProperty extTypedProperty = _properties[i2];
            if (null == str) {
                final TokenBuffer tokenBuffer = _tokens[i2];
                if (null != tokenBuffer && JsonToken.VALUE_NULL != tokenBuffer.firstToken()) {
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(_beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s'", extTypedProperty.getTypePropertyName());
                        defaultTypeId = str;
                    } else {
                        defaultTypeId = extTypedProperty.getDefaultTypeId();
                    }
                }
            } else {
                defaultTypeId = str;
                if (null == this._tokens[i2]) {
                    final SettableBeanProperty property = extTypedProperty.getProperty();
                    if (!property.isRequired()) {
                        defaultTypeId = str;
                        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                            deserializationContext.reportPropertyInputMismatch(_beanType, property.getName(), "Missing property '%s' for external type id '%s'", property.getName(), _properties[i2].getTypePropertyName());
                            defaultTypeId = str;
                        }
                    }
                }
            }
            if (null != this._tokens[i2]) {
                objArr[i2] = this._deserialize(jsonParser, deserializationContext, i2, defaultTypeId);
            }
            final SettableBeanProperty property2 = extTypedProperty.getProperty();
            if (0 <= property2.getCreatorIndex()) {
                propertyValueBuffer.assignParameter(property2, objArr[i2]);
                final SettableBeanProperty typeProperty = extTypedProperty.getTypeProperty();
                if (null != typeProperty && 0 <= typeProperty.getCreatorIndex()) {
                    Object obj = defaultTypeId;
                    if (!typeProperty.getType().hasRawClass(String.class)) {
                        final TokenBuffer tokenBuffer2 = new TokenBuffer(jsonParser, deserializationContext);
                        tokenBuffer2.writeString(defaultTypeId);
                        final Object objDeserialize = typeProperty.getValueDeserializer().deserialize(tokenBuffer2.asParserOnFirstToken(), deserializationContext);
                        tokenBuffer2.close();
                        obj = objDeserialize;
                    }
                    propertyValueBuffer.assignParameter(typeProperty, obj);
                }
            }
        }
        final Object objBuild = propertyBasedCreator.build(deserializationContext, propertyValueBuffer);
        for (int i3 = 0; i3 < length; i3++) {
            final SettableBeanProperty property3 = _properties[i3].getProperty();
            if (0 > property3.getCreatorIndex()) {
                property3.set(objBuild, objArr[i3]);
            }
        }
        return objBuild;
    }
    protected final Object _deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext, final int i2, final String str) throws IOException {
        final JsonParser jsonParserAsParser = _tokens[i2].asParser(jsonParser);
        if (JsonToken.VALUE_NULL == jsonParserAsParser.nextToken()) {
            return null;
        }
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        tokenBuffer.copyCurrentStructure(jsonParserAsParser);
        tokenBuffer.writeEndArray();
        final JsonParser jsonParserAsParser2 = tokenBuffer.asParser(jsonParser);
        jsonParserAsParser2.nextToken();
        return _properties[i2].getProperty().deserialize(jsonParserAsParser2, deserializationContext);
    }
    protected final void _deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final int i2, final String str) throws IOException {
        if (null == str) {
            deserializationContext.reportInputMismatch(_beanType, "Internal error in external Type Id handling: `null` type id passed");
        }
        final JsonParser jsonParserAsParser = _tokens[i2].asParser(jsonParser);
        if (JsonToken.VALUE_NULL == jsonParserAsParser.nextToken()) {
            _properties[i2].getProperty().set(obj, null);
            return;
        }
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(str);
        tokenBuffer.copyCurrentStructure(jsonParserAsParser);
        tokenBuffer.writeEndArray();
        final JsonParser jsonParserAsParser2 = tokenBuffer.asParser(jsonParser);
        jsonParserAsParser2.nextToken();
        _properties[i2].getProperty().deserializeAndSet(jsonParserAsParser2, deserializationContext, obj);
    }
    public static class Builder {
        private final JavaType _beanType;
        private final List<ExtTypedProperty> _properties = new ArrayList();
        private final Map<String, Object> _nameToPropertyIndex = new HashMap();

        protected Builder(final JavaType javaType) {
            _beanType = javaType;
        }

        public void addExternal(final SettableBeanProperty settableBeanProperty, final TypeDeserializer typeDeserializer) {
            final Integer numValueOf = Integer.valueOf(_properties.size());
            _properties.add(new ExtTypedProperty(settableBeanProperty, typeDeserializer));
            this._addPropertyIndex(settableBeanProperty.getName(), numValueOf);
            this._addPropertyIndex(typeDeserializer.getPropertyName(), numValueOf);
        }

        private void _addPropertyIndex(final String str, final Integer num) {
            final Object obj = _nameToPropertyIndex.get(str);
            if (null == obj) {
                _nameToPropertyIndex.put(str, num);
                return;
            }
            if (obj instanceof List) {
                ((List) obj).add(num);
                return;
            }
            final LinkedList linkedList = new LinkedList();
            linkedList.add(obj);
            linkedList.add(num);
            _nameToPropertyIndex.put(str, linkedList);
        }

        public ExternalTypeHandler build(final BeanPropertyMap beanPropertyMap) {
            final int size = _properties.size();
            final ExtTypedProperty[] extTypedPropertyArr = new ExtTypedProperty[size];
            for (int i2 = 0; i2 < size; i2++) {
                final ExtTypedProperty extTypedProperty = _properties.get(i2);
                final SettableBeanProperty settableBeanPropertyFind = beanPropertyMap.find(extTypedProperty.getTypePropertyName());
                if (null != settableBeanPropertyFind) {
                    extTypedProperty.linkTypeProperty(settableBeanPropertyFind);
                }
                extTypedPropertyArr[i2] = extTypedProperty;
            }
            return new ExternalTypeHandler(_beanType, extTypedPropertyArr, _nameToPropertyIndex, null, null);
        }
    }
    private static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private SettableBeanProperty _typeProperty;
        private final String _typePropertyName;

        public ExtTypedProperty(final SettableBeanProperty settableBeanProperty, final TypeDeserializer typeDeserializer) {
            _property = settableBeanProperty;
            _typeDeserializer = typeDeserializer;
            _typePropertyName = typeDeserializer.getPropertyName();
        }

        public void linkTypeProperty(final SettableBeanProperty settableBeanProperty) {
            _typeProperty = settableBeanProperty;
        }

        public boolean hasTypePropertyName(final String str) {
            return str.equals(_typePropertyName);
        }

        public boolean hasDefaultType() {
            return _typeDeserializer.hasDefaultImpl();
        }

        public String getDefaultTypeId() {
            final Class<?> defaultImpl = _typeDeserializer.getDefaultImpl();
            if (null == defaultImpl) {
                return null;
            }
            return _typeDeserializer.getTypeIdResolver().idFromValueAndType(null, defaultImpl);
        }

        public String getTypePropertyName() {
            return _typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return _property;
        }

        public SettableBeanProperty getTypeProperty() {
            return _typeProperty;
        }
    }
}
