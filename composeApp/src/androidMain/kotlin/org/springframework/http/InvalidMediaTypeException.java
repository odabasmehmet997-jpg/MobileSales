package org.springframework.http;

public class InvalidMediaTypeException extends IllegalArgumentException {
    private final String mediaType;
    public InvalidMediaTypeException(final String str, final String str2) {
        super("Invalid media type \"" + str + "\": " + str2);
        mediaType = str;
    }
    public String getMediaType() {
        return mediaType;
    }
}
