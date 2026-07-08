package com.fasterxml.jackson.core;

public class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123;
    protected JsonLocation _location;
    protected String getMessageSuffix() {
        return null;
    }
    public Object getProcessor() {
        return null;
    }
    protected JsonProcessingException(final String str, final JsonLocation jsonLocation, final Throwable th) {
        super(str, th);
        _location = jsonLocation;
    }
    protected JsonProcessingException(final String str) {
        super(str);
    }
    protected JsonProcessingException(final String str, final JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }
    protected JsonProcessingException(final String str, final Throwable th) {
        this(str, null, th);
    }
    protected JsonProcessingException(final Throwable th) {
        this(null, null, th);
    }
    public JsonLocation getLocation() {
        return _location;
    }
    public void clearLocation() {
        _location = null;
    }
    public String getOriginalMessage() {
        return super.getMessage();
    }
    public String getMessage() {
        String message = super.getMessage();
        if (null == message) {
            message = "N/A";
        }
        final JsonLocation location = this._location;
        final String messageSuffix = this.getMessageSuffix();
        if (null == location && null == messageSuffix) {
            return message;
        }
        final StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (null != messageSuffix) {
            sb.append(messageSuffix);
        }
        if (null != location) {
            sb.append('\n');
            sb.append(" at ");
            sb.append(location);
        }
        return sb.toString();
    }
    public String toString() {
        return this.getClass().getName() + ": " + this.getMessage();
    }
}
