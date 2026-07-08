package com.fasterxml.jackson.databind.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.cfg.PackageVersion;

public class JsonMapper extends ObjectMapper {
    private static final long serialVersionUID = 1;

    public static class Builder extends MapperBuilder<JsonMapper, Builder> {
        public Builder(final JsonMapper jsonMapper) {
            super(jsonMapper);
        }
    }

    public JsonMapper() {
        this(new JsonFactory());
    }

    public JsonMapper(final JsonFactory jsonFactory) {
        super(jsonFactory);
    }

    protected JsonMapper(final JsonMapper jsonMapper) {
        super(jsonMapper);
    }

    public JsonMapper copy() {
        this._checkInvalidCopy(JsonMapper.class);
        return new JsonMapper(this);
    }

    public static Builder builder() {
        return new Builder(new JsonMapper());
    }

    public static Builder builder(final JsonFactory jsonFactory) {
        return new Builder(new JsonMapper(jsonFactory));
    }

    public Builder rebuild() {
        return new Builder(this.copy());
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public JsonFactory getFactory() {
        return _jsonFactory;
    }

    public boolean isEnabled(final JsonReadFeature jsonReadFeature) {
        return this.isEnabled(jsonReadFeature.mappedFeature());
    }

    public boolean isEnabled(final JsonWriteFeature jsonWriteFeature) {
        return this.isEnabled(jsonWriteFeature.mappedFeature());
    }
}
