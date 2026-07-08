package com.google.zxing.client.result;

import java.util.Map;

public final class ExpandedProductParsedResult extends ParsedResult {
    private final String bestBeforeDate;
    private final String expirationDate;
    private final String lotNumber;
    private final String price;
    private final String priceCurrency;
    private final String priceIncrement;
    private final String productID;
    private final String productionDate;
    private final String rawText;
    private final String sscc;
    private final Map<String, String> uncommonAIs;
    private final String weight;
    private final String weightIncrement;
    private final String weightType;

    public boolean equals(Object obj) {
        if (!(obj instanceof final ExpandedProductParsedResult expandedProductParsedResult)) {
            return false;
        }
        return equalsOrNull(this.productID, expandedProductParsedResult.productID) && equalsOrNull(this.sscc, expandedProductParsedResult.sscc) && equalsOrNull(this.lotNumber, expandedProductParsedResult.lotNumber) && equalsOrNull(this.productionDate, expandedProductParsedResult.productionDate) && equalsOrNull(this.bestBeforeDate, expandedProductParsedResult.bestBeforeDate) && equalsOrNull(this.expirationDate, expandedProductParsedResult.expirationDate) && equalsOrNull(this.weight, expandedProductParsedResult.weight) && equalsOrNull(this.weightType, expandedProductParsedResult.weightType) && equalsOrNull(this.weightIncrement, expandedProductParsedResult.weightIncrement) && equalsOrNull(this.price, expandedProductParsedResult.price) && equalsOrNull(this.priceIncrement, expandedProductParsedResult.priceIncrement) && equalsOrNull(this.priceCurrency, expandedProductParsedResult.priceCurrency) && equalsOrNull(this.uncommonAIs, expandedProductParsedResult.uncommonAIs);
    }

    private static boolean equalsOrNull(Object obj, Object obj2) {
        if (null == obj) {
            return null == obj2;
        }
        return obj.equals(obj2);
    }

    public int hashCode() {
        return (((((((((((hashNotNull(this.productID) ^ hashNotNull(this.sscc)) ^ hashNotNull(this.lotNumber)) ^ hashNotNull(this.productionDate)) ^ hashNotNull(this.bestBeforeDate)) ^ hashNotNull(this.expirationDate)) ^ hashNotNull(this.weight)) ^ hashNotNull(this.weightType)) ^ hashNotNull(this.weightIncrement)) ^ hashNotNull(this.price)) ^ hashNotNull(this.priceIncrement)) ^ hashNotNull(this.priceCurrency)) ^ hashNotNull(this.uncommonAIs);
    }

    private static int hashNotNull(Object obj) {
        if (null == obj) {
            return 0;
        }
        return obj.hashCode();
    }

    public String getDisplayResult() {
        return String.valueOf(this.rawText);
    }
}
