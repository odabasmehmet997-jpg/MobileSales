package com.fasterxml.jackson.core;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class JsonLocation implements Serializable {
    public static final int MAX_CONTENT_SNIPPET = 500;
    public static final JsonLocation f792NA = new JsonLocation(null, -1, -1, -1, -1);
    private static final long serialVersionUID = 1;
    protected final int _columnNr;
    protected final int _lineNr;
    final transient Object _sourceRef;
    protected final long _totalBytes;
    protected final long _totalChars;

    public JsonLocation(final Object obj, final long j2, final int i2, final int i3) {
        this(obj, -1L, j2, i2, i3);
    }

    public JsonLocation(final Object obj, final long j2, final long j3, final int i2, final int i3) {
        _sourceRef = obj;
        _totalBytes = j2;
        _totalChars = j3;
        _lineNr = i2;
        _columnNr = i3;
    }

    public Object getSourceRef() {
        return _sourceRef;
    }

    public int getLineNr() {
        return _lineNr;
    }

    public int getColumnNr() {
        return _columnNr;
    }

    public long getCharOffset() {
        return _totalChars;
    }

    public long getByteOffset() {
        return _totalBytes;
    }

    public String sourceDescription() {
        return this._appendSourceDesc(new StringBuilder(100)).toString();
    }

    public int hashCode() {
        final Object obj = _sourceRef;
        return ((((null == obj ? 1 : obj.hashCode()) ^ _lineNr) + _columnNr) ^ ((int) _totalChars)) + ((int) _totalBytes);
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || !(obj instanceof JsonLocation jsonLocation)) {
            return false;
        }
        final Object obj2 = _sourceRef;
        if (null == obj2) {
            if (null != jsonLocation._sourceRef) {
                return false;
            }
        } else if (!obj2.equals(jsonLocation._sourceRef)) {
            return false;
        }
        return _lineNr == jsonLocation._lineNr && _columnNr == jsonLocation._columnNr && _totalChars == jsonLocation._totalChars && this._totalBytes == jsonLocation._totalBytes;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        this._appendSourceDesc(sb);
        sb.append("; line: ");
        sb.append(_lineNr);
        sb.append(", column: ");
        sb.append(_columnNr);
        sb.append(']');
        return sb.toString();
    }

    protected StringBuilder _appendSourceDesc(final StringBuilder sb) {
        final int length;
        final int i_append;
        final Object obj = _sourceRef;
        if (null == obj) {
            sb.append("UNKNOWN");
            return sb;
        }
        final Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        String name = cls.getName();
        if (name.startsWith("java.")) {
            name = cls.getSimpleName();
        } else if (obj instanceof byte[]) {
            name = "byte[]";
        } else if (obj instanceof char[]) {
            name = "char[]";
        }
        sb.append('(');
        sb.append(name);
        sb.append(')');
        int length2 = 0;
        String str = " chars";
        if (obj instanceof CharSequence charSequence) {
            length = charSequence.length();
            i_append = this._append(sb, charSequence.subSequence(0, Math.min(length, 500)).toString());
        } else if (obj instanceof char[] cArr) {
            length = cArr.length;
            i_append = this._append(sb, new String(cArr, 0, Math.min(length, 500)));
        } else {
            if (obj instanceof byte[] bArr) {
                final int iMin = Math.min(bArr.length, 500);
                this._append(sb, new String(bArr, 0, iMin, StandardCharsets.UTF_8));
                length2 = bArr.length - iMin;
                str = " bytes";
            }
            if (0 < length2) {
                sb.append("[truncated ");
                sb.append(length2);
                sb.append(str);
                sb.append(']');
            }
            return sb;
        }
        length2 = length - i_append;
        if (0 < length2) {
        }
        return sb;
    }

    private int _append(final StringBuilder sb, final String str) {
        sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        sb.append(str);
        sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        return str.length();
    }
}
