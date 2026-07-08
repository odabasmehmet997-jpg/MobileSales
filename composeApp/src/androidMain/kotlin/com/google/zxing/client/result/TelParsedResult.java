package com.google.zxing.client.result;

public final class TelParsedResult extends ParsedResult {
    private final String number;
    private final String title;

    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        ParsedResult.maybeAppend(this.number, sb);
        ParsedResult.maybeAppend(this.title, sb);
        return sb.toString();
    }
}
