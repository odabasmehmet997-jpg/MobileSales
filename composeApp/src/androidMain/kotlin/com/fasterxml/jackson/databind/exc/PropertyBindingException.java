package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class PropertyBindingException extends MismatchedInputException {
    private static final int MAX_DESC_LENGTH = 1000;
    protected transient String _propertiesAsString;
    protected final Collection<Object> _propertyIds;
    protected final String _propertyName;
    protected final Class<?> _referringClass;

    protected PropertyBindingException(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        super(jsonParser, str, jsonLocation);
        _referringClass = cls;
        _propertyName = str2;
        _propertyIds = collection;
    }

    @Deprecated
    protected PropertyBindingException(final String str, final JsonLocation jsonLocation, final Class<?> cls, final String str2, final Collection<Object> collection) {
        this(null, str, jsonLocation, cls, str2, collection);
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException
    public String getMessageSuffix() {
        final String str = _propertiesAsString;
        if (null != str || null == this._propertyIds) {
            return str;
        }
        final StringBuilder sb = new StringBuilder(100);
        final int size = _propertyIds.size();
        if (1 == size) {
            sb.append(" (one known property: \"");
            sb.append(_propertyIds.iterator().next());
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        } else {
            sb.append(" (");
            sb.append(size);
            sb.append(" known properties: ");
            final Iterator<Object> it = _propertyIds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                sb.append(it.next());
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                if (1000 < sb.length()) {
                    sb.append(" [truncated]");
                    break;
                }
                if (it.hasNext()) {
                    sb.append(", ");
                }
            }
        }
        sb.append("])");
        final String string = sb.toString();
        _propertiesAsString = string;
        return string;
    }

    public Class<?> getReferringClass() {
        return _referringClass;
    }

    public String getPropertyName() {
        return _propertyName;
    }

    public Collection<Object> getKnownPropertyIds() {
        final Collection<Object> collection = _propertyIds;
        if (null == collection) {
            return null;
        }
        return Collections.unmodifiableCollection(collection);
    }
}
