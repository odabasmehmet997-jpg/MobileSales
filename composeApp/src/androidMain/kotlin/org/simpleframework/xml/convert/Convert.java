package org.simpleframework.xml.convert;

public @interface Convert {
    Class<? extends Converter> value();
}
