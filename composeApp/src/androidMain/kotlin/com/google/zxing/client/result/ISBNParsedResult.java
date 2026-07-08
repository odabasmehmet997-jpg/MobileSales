package com.google.zxing.client.result;

public final class ISBNParsedResult extends ParsedResult {
    private final String isbn;

    public String getDisplayResult() {
        return this.isbn;
    }
}
