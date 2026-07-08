package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import okhttp3.HttpUrl;

public class JsonMappingException extends JsonProcessingException {
    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1;
    protected LinkedList<Reference> _path;
    protected transient Closeable _processor;
    public static class Reference implements Serializable {
        private static final long serialVersionUID = 2;
        protected String _desc;
        protected String _fieldName;
        protected transient Object _from;
        protected int _index;
        protected Reference() {
            _index = -1;
        }
        public Reference(final Object obj) {
            _index = -1;
            _from = obj;
        }

        public Reference(final Object obj, final String str) {
            _index = -1;
            _from = obj;
            if (null == str) {
                throw new NullPointerException("Cannot pass null fieldName");
            }
            _fieldName = str;
        }

        public Reference(final Object obj, final int i2) {
            _from = obj;
            _index = i2;
        }

        void setFieldName(final String str) {
            _fieldName = str;
        }

        void setIndex(final int i2) {
            _index = i2;
        }

        void setDescription(final String str) {
            _desc = str;
        }

        @JsonIgnore
        public Object getFrom() {
            return _from;
        }

        public String getFieldName() {
            return _fieldName;
        }

        public int getIndex() {
            return _index;
        }

        public String getDescription() {
            if (null == this._desc) {
                final StringBuilder sb = new StringBuilder();
                final Object obj = _from;
                if (null == obj) {
                    sb.append("UNKNOWN");
                } else {
                    Class<?> componentType = obj instanceof Class ? (Class) obj : obj.getClass();
                    int i2 = 0;
                    while (componentType.isArray()) {
                        componentType = componentType.getComponentType();
                        i2++;
                    }
                    sb.append(componentType.getName());
                    while (true) {
                        i2--;
                        if (0 > i2) {
                            break;
                        }
                        sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                    }
                }
                sb.append('[');
                if (null != this._fieldName) {
                    sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                    sb.append(_fieldName);
                    sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                } else {
                    final int i3 = _index;
                    if (0 <= i3) {
                        sb.append(i3);
                    } else {
                        sb.append('?');
                    }
                }
                sb.append(']');
                _desc = sb.toString();
            }
            return _desc;
        }

        public String toString() {
            return this.getDescription();
        }

        Object writeReplace() {
            this.getDescription();
            return this;
        }
    }
    public JsonMappingException(final String str) {
        super(str);
    }
    public JsonMappingException(final String str, final Throwable th) {
        super(str, th);
    }
    public JsonMappingException(final String str, final JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }
    public JsonMappingException(final String str, final JsonLocation jsonLocation, final Throwable th) {
        super(str, jsonLocation, th);
    }
    public JsonMappingException(final Closeable closeable, final String str) {
        super(str);
        _processor = closeable;
        if (closeable instanceof JsonParser) {
            _location = ((JsonParser) closeable).getTokenLocation();
        }
    }
    public JsonMappingException(final Closeable closeable, final String str, final Throwable th) {
        super(str, th);
        _processor = closeable;
        if (th instanceof JsonProcessingException) {
            _location = ((JsonProcessingException) th).getLocation();
        } else if (closeable instanceof JsonParser) {
            _location = ((JsonParser) closeable).getTokenLocation();
        }
    }
    public JsonMappingException(final Closeable closeable, final String str, final JsonLocation jsonLocation) {
        super(str, jsonLocation);
        _processor = closeable;
    }
    public static JsonMappingException from(final JsonParser jsonParser, final String str) {
        return new JsonMappingException(jsonParser, str);
    }
    public static JsonMappingException from(final JsonParser jsonParser, final String str, final Throwable th) {
        return new JsonMappingException(jsonParser, str, th);
    }
    public static JsonMappingException from(final JsonGenerator jsonGenerator, final String str) {
        return new JsonMappingException(jsonGenerator, str, (Throwable) null);
    }
    public static JsonMappingException from(final JsonGenerator jsonGenerator, final String str, final Throwable th) {
        return new JsonMappingException(jsonGenerator, str, th);
    }
    public static JsonMappingException from(final DeserializationContext deserializationContext, final String str) {
        return new JsonMappingException(deserializationContext.getParser(), str);
    }
    public static JsonMappingException from(final DeserializationContext deserializationContext, final String str, final Throwable th) {
        return new JsonMappingException(deserializationContext.getParser(), str, th);
    }
    public static JsonMappingException from(final SerializerProvider serializerProvider, final String str) {
        return new JsonMappingException(serializerProvider.getGenerator(), str);
    }
    public static JsonMappingException from(final SerializerProvider serializerProvider, final String str, final Throwable th) {
        return new JsonMappingException(serializerProvider.getGenerator(), str, th);
    }
    public static JsonMappingException fromUnexpectedIOE(final IOException iOException) {
        return new JsonMappingException(null, String.format("Unexpected IOException (of type %s): %s", iOException.getClass().getName(), ClassUtil.exceptionMessage(iOException)));
    }
    public static JsonMappingException wrapWithPath(final Throwable th, final Object obj, final String str) {
        return JsonMappingException.wrapWithPath(th, new Reference(obj, str));
    }
    public static JsonMappingException wrapWithPath(final Throwable th, final Object obj, final int i2) {
        return JsonMappingException.wrapWithPath(th, new Reference(obj, i2));
    }
    public static JsonMappingException wrapWithPath(final Throwable th, final Reference reference) {
        JsonMappingException jsonMappingException = null;
        if (th instanceof JsonMappingException) {
            jsonMappingException = (JsonMappingException) th;
        } else {
            String strExceptionMessage = ClassUtil.exceptionMessage(th);
            if (null == strExceptionMessage || strExceptionMessage.isEmpty()) {
                strExceptionMessage = "(was " + th.getClass().getName() + ")";
            }
            if (th instanceof JsonProcessingException) {
                final Object processor = ((JsonProcessingException) th).getProcessor();
                final Closeable closeable = processor instanceof Closeable ? (Closeable) processor : null;
                jsonMappingException = new JsonMappingException(closeable, strExceptionMessage, th);
            }
        }
        jsonMappingException.prependPath(reference);
        return jsonMappingException;
    }
    public List<Reference> getPath() {
        final LinkedList<Reference> linkedList = _path;
        if (null == linkedList) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(linkedList);
    }
    public String getPathReference() {
        return this.getPathReference(new StringBuilder()).toString();
    }
    public StringBuilder getPathReference(final StringBuilder sb) {
        this._appendPathDesc(sb);
        return sb;
    }
    public void prependPath(final Object obj, final String str) {
        this.prependPath(new Reference(obj, str));
    }
    public void prependPath(final Object obj, final int i2) {
        this.prependPath(new Reference(obj, i2));
    }
    public void prependPath(final Reference reference) {
        if (null == this._path) {
            _path = new LinkedList<>();
        }
        if (1000 > this._path.size()) {
            _path.addFirst(reference);
        }
    }
    public Object getProcessor() {
        return _processor;
    }
    public String getLocalizedMessage() {
        return this._buildMessage();
    }
    public String getMessage() {
        return this._buildMessage();
    }
    protected String _buildMessage() {
        final String message = super.getMessage();
        if (null == this._path) {
            return message;
        }
        final StringBuilder sb = null == message ? new StringBuilder() : new StringBuilder(message);
        sb.append(" (through reference chain: ");
        final StringBuilder pathReference = this.getPathReference(sb);
        pathReference.append(')');
        return pathReference.toString();
    }
    public String toString() {
        return this.getClass().getName() + ": " + this.getMessage();
    }
    protected void _appendPathDesc(final StringBuilder sb) {
        final LinkedList<Reference> linkedList = _path;
        if (null == linkedList) {
            return;
        }
        final Iterator<Reference> it = linkedList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append("->");
            }
        }
    }
}
