package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;

public class DupDetector {
    protected String _firstName;
    protected String _secondName;
    protected HashSet<String> _seen;
    protected final Object _source;
    private DupDetector(final Object obj) {
        _source = obj;
    }
    public static DupDetector rootDetector(final JsonParser jsonParser) {
        return new DupDetector(jsonParser);
    }
    public static DupDetector rootDetector(final JsonGenerator jsonGenerator) {
        return new DupDetector(jsonGenerator);
    }
    public DupDetector child() {
        return new DupDetector(_source);
    }
    public void reset() {
        _firstName = null;
        _secondName = null;
        _seen = null;
    }
    public Object getSource() {
        return _source;
    }
    public boolean isDup(final String str) throws JsonParseException {
        final String str2 = _firstName;
        if (null == str2) {
            _firstName = str;
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        final String str3 = _secondName;
        if (null == str3) {
            _secondName = str;
            return false;
        }
        if (str.equals(str3)) {
            return true;
        }
        if (null == this._seen) {
            final HashSet<String> hashSet = new HashSet<>(16);
            _seen = hashSet;
            hashSet.add(_firstName);
            _seen.add(_secondName);
        }
        return !_seen.add(str);
    }
}
