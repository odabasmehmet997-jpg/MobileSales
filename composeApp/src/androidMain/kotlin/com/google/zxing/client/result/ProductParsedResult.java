package com.google.zxing.client.result;

public final class ProductParsedResult extends ParsedResult {
    private final String productID;

    public ProductParsedResult(String productID) {
        this.productID = productID;
    }

    public String getDisplayResult() {
        return this.productID;
    }
}
