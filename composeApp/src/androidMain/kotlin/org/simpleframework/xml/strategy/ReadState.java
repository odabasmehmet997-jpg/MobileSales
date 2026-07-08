package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;


class ReadState extends WeakCache<ReadGraph> {
    private final Contract contract;
    private final Loader loader = new Loader();

    public ReadState(final Contract contract) {
        this.contract = contract;
    }

    public ReadGraph find(final Object obj) throws Exception {
        final ReadGraph fetch = this.fetch(obj);
        return null != fetch ? fetch : this.create(obj);
    }

    private ReadGraph create(final Object obj) throws Exception {
        final ReadGraph fetch = this.fetch(obj);
        if (null != fetch) {
            return fetch;
        }
        final ReadGraph readGraph = new ReadGraph(contract, loader);
        this.cache(obj, readGraph);
        return readGraph;
    }
}
