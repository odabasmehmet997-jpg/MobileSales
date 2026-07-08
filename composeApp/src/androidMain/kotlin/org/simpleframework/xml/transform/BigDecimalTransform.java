package org.simpleframework.xml.transform;

import java.math.BigDecimal;


class BigDecimalTransform implements Transform<BigDecimal> {
    BigDecimalTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public BigDecimal read(final String str) {
        return new BigDecimal(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
