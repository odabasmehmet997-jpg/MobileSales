package com.google.zxing.client.result;

public final class TextParsedResult extends ParsedResult {
    private final String text;

    public TextParsedResult(String text) {
        this.text = text;
    }

    public String getDisplayResult() {
        return this.text;
    }
}
