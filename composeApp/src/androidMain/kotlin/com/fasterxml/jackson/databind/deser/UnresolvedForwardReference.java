package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnresolvedForwardReference extends JsonMappingException {
    private static final long serialVersionUID = 1;
    private ReadableObjectId _roid;
    private List<UnresolvedId> _unresolvedIds;
    public UnresolvedForwardReference(final JsonParser jsonParser, final String str, final JsonLocation jsonLocation, final ReadableObjectId readableObjectId) {
        super(jsonParser, str, jsonLocation);
        _roid = readableObjectId;
    }
    public UnresolvedForwardReference(final JsonParser jsonParser, final String str) {
        super(jsonParser, str);
        _unresolvedIds = new ArrayList();
    }
    public UnresolvedForwardReference(final String str, final JsonLocation jsonLocation, final ReadableObjectId readableObjectId) {
        super(str, jsonLocation);
        _roid = readableObjectId;
    }
    public UnresolvedForwardReference(final String str) {
        super(str);
        _unresolvedIds = new ArrayList();
    }
    public ReadableObjectId getRoid() {
        return _roid;
    }
    public Object getUnresolvedId() {
        return _roid.getKey().key;
    }
    public void addUnresolvedId(final Object obj, final Class<?> cls, final JsonLocation jsonLocation) {
        _unresolvedIds.add(new UnresolvedId(obj, cls, jsonLocation));
    }
    public List<UnresolvedId> getUnresolvedIds() {
        return _unresolvedIds;
    }
    public String getMessage() {
        final String message = super.getMessage();
        if (null == this._unresolvedIds) {
            return message;
        }
        final StringBuilder sb = new StringBuilder(message);
        final Iterator<UnresolvedId> it = _unresolvedIds.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('.');
        return sb.toString();
    }
}
