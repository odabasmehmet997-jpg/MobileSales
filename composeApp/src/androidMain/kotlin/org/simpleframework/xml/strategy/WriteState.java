package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;


class WriteState extends WeakCache<WriteGraph> {
    private final Contract contract;

    public WriteState(final Contract contract) {
        this.contract = contract;
    }

    public WriteGraph find(final Object obj) {
        final WriteGraph fetch = this.fetch(obj);
        if (null != fetch) {
            return fetch;
        }
        final WriteGraph writeGraph = new WriteGraph(contract);
        this.cache(obj, writeGraph);
        return writeGraph;
    }
}
