package org.simpleframework.xml.transform;

import java.util.Currency;


class CurrencyTransform implements Transform<Currency> {
    CurrencyTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Currency read(final String str) {
        return Currency.getInstance(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Currency currency) {
        return currency.toString();
    }
}
