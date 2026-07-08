package com.google.android.datatransport.cct.internal;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.IOException;
import java.io.Reader;
public abstract class LogResponse {
    public abstract long getNextRequestWaitMillis();
    static LogResponse create(long j2) {
        return new AutoValue_LogResponse(j2);
    }
    public static LogResponse fromJson(Reader reader) throws IOException {
        try (JsonReader jsonReader = new JsonReader(reader)) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (!"nextRequestWaitMillis".equals(jsonReader.nextName())) {
                    jsonReader.skipValue();
                } else if (JsonToken.STRING == jsonReader.peek()) {
                    return create(Long.parseLong(jsonReader.nextString()));
                } else {
                    LogResponse create = create(jsonReader.nextLong());
                    jsonReader.close();
                    return create;
                }
            }
            throw new IOException("Response is missing nextRequestWaitMillis field.");
        }
    }
}
