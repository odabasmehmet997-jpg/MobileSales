package org.springframework.core;

import java.util.concurrent.ConcurrentHashMap;


class CollectionFactoryJdkConcurrentHashMap extends ConcurrentHashMap {
    private CollectionFactoryJdkConcurrentHashMap(final int i2) {
        super(i2);
    }
}
