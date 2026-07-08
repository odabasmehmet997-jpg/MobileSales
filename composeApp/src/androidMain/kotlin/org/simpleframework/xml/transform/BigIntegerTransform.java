package org.simpleframework.xml.transform;

import java.math.BigInteger;


class BigIntegerTransform implements Transform<BigInteger> {
    BigIntegerTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public BigInteger read(final String str) {
        return new BigInteger(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final BigInteger bigInteger) {
        return bigInteger.toString();
    }
}
