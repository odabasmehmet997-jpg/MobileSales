package com.fasterxml.jackson.core.util;

import java.io.IOException;
import java.io.Serializable;

public class RequestPayload implements Serializable {
    private static final long serialVersionUID = 1;
    protected String _charset;
    protected byte[] _payloadAsBytes;
    protected CharSequence _payloadAsText;

    public RequestPayload(final byte[] bArr, final String str) {
        if (null == bArr) {
            throw new IllegalArgumentException();
        }
        _payloadAsBytes = bArr;
        _charset = (null == str || str.isEmpty()) ? "UTF-8" : str;
    }

    public RequestPayload(final CharSequence charSequence) {
        if (null == charSequence) {
            throw new IllegalArgumentException();
        }
        _payloadAsText = charSequence;
    }

    public Object getRawPayload() {
        final byte[] bArr = _payloadAsBytes;
        return null != bArr ? bArr : _payloadAsText;
    }

    public String toString() {
        final byte[] bArr = _payloadAsBytes;
        if (null != bArr) {
            try {
                return new String(bArr, _charset);
            } catch (final IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        return _payloadAsText.toString();
    }
}
