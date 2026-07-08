package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class JsonpCharacterEscapes extends CharacterEscapes {
    private static final int[] asciiEscapes = standardAsciiEscapesForJSON();
    private static final SerializedString escapeFor2028 = new SerializedString("\\u2028");
    private static final SerializedString escapeFor2029 = new SerializedString("\\u2029");
    private static final JsonpCharacterEscapes sInstance = new JsonpCharacterEscapes();
    private static final long serialVersionUID = 1;
    public static JsonpCharacterEscapes instance() {
        return JsonpCharacterEscapes.sInstance;
    }
    public SerializableString getEscapeSequence(final int i2) {
        if (8232 == i2) {
            return JsonpCharacterEscapes.escapeFor2028;
        }
        if (8233 != i2) {
            return null;
        }
        return JsonpCharacterEscapes.escapeFor2029;
    }
    public int[] getEscapeCodesForAscii() {
        return JsonpCharacterEscapes.asciiEscapes;
    }
}
