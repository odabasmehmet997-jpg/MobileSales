package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class MapperBuilder<M extends ObjectMapper, B extends MapperBuilder<M, B>> {
    protected final M _mapper;
    protected MapperBuilder(final M m) {
        _mapper = m;
    }
}
