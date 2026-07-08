package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserSequence extends JsonParserDelegate {
    protected final boolean _checkForExistingToken;
    protected boolean _hasToken;
    protected int _nextParserIndex;
    protected final JsonParser[] _parsers;
    protected JsonParserSequence(final boolean z, final JsonParser[] jsonParserArr) {
        super(jsonParserArr[0]);
        boolean z2 = false;
        _checkForExistingToken = z;
        if (z && delegate.hasCurrentToken()) {
            z2 = true;
        }
        _hasToken = z2;
        _parsers = jsonParserArr;
        _nextParserIndex = 1;
    }

    public static JsonParserSequence createFlattened(final boolean z, final JsonParser jsonParser, final JsonParser jsonParser2) {
        final boolean z2 = jsonParser instanceof JsonParserSequence;
        if (!z2 && !(jsonParser2 instanceof JsonParserSequence)) {
            return new JsonParserSequence(z, new JsonParser[]{jsonParser, jsonParser2});
        }
        final ArrayList arrayList = new ArrayList();
        if (z2) {
            ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser);
        }
        if (jsonParser2 instanceof JsonParserSequence) {
            ((JsonParserSequence) jsonParser2).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser2);
        }
        return new JsonParserSequence(z, (JsonParser[]) arrayList.toArray(new JsonParser[arrayList.size()]));
    }

    protected void addFlattenedActiveParsers(final List<JsonParser> list) {
        final int length = _parsers.length;
        for (int i2 = _nextParserIndex - 1; i2 < length; i2++) {
            final JsonParser jsonParser = _parsers[i2];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(list);
            } else {
                list.add(jsonParser);
            }
        }
    }
    public void close() throws IOException {
        do {
            delegate.close();
        } while (this.switchToNext());
    }

    public JsonToken nextToken() throws IOException {
        final JsonParser jsonParser = delegate;
        if (null == jsonParser) {
            return null;
        }
        if (_hasToken) {
            _hasToken = false;
            return jsonParser.currentToken();
        }
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        return null == jsonTokenNextToken ? this.switchAndReturnNext() : jsonTokenNextToken;
    }
    public JsonParser skipChildren() throws IOException {
        if (JsonToken.START_OBJECT != this.delegate.currentToken() && JsonToken.START_ARRAY != this.delegate.currentToken()) {
            return this;
        }
        int i2 = 1;
        while (true) {
            final JsonToken jsonTokenNextToken = this.nextToken();
            if (null == jsonTokenNextToken) {
                return this;
            }
            if (jsonTokenNextToken.isStructStart()) {
                i2++;
            } else if (jsonTokenNextToken.isStructEnd() && 0 == i2 - 1) {
                return this;
            }
        }
    }

    protected boolean switchToNext() {
        final int i2 = _nextParserIndex;
        final JsonParser[] jsonParserArr = _parsers;
        if (i2 >= jsonParserArr.length) {
            return false;
        }
        _nextParserIndex = i2 + 1;
        delegate = jsonParserArr[i2];
        return true;
    }

    protected JsonToken switchAndReturnNext() throws IOException {
        JsonToken jsonTokenNextToken;
        do {
            final int i2 = _nextParserIndex;
            final JsonParser[] jsonParserArr = _parsers;
            if (i2 >= jsonParserArr.length) {
                return null;
            }
            _nextParserIndex = i2 + 1;
            final JsonParser jsonParser = jsonParserArr[i2];
            delegate = jsonParser;
            if (_checkForExistingToken && jsonParser.hasCurrentToken()) {
                return delegate.getCurrentToken();
            }
            jsonTokenNextToken = delegate.nextToken();
        } while (null == jsonTokenNextToken);
        return jsonTokenNextToken;
    }
}
