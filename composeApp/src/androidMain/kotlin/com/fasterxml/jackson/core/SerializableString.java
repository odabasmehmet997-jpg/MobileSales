package com.fasterxml.jackson.core;

public interface SerializableString {
    int appendQuoted(char[] cArr, int i2);
    int appendQuotedUTF8(byte[] bArr, int i2);
    int appendUnquoted(char[] cArr, int i2);
    int appendUnquotedUTF8(byte[] bArr, int i2);
    char[] asQuotedChars();
    byte[] asQuotedUTF8();
    byte[] asUnquotedUTF8();
    String getValue();
}
