package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

class SqlTimestampTypeAdapter extends TypeAdapter<Timestamp> {
  static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapters.EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
          if (Timestamp.class == typeToken.getRawType()) {
            TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
            return (TypeAdapter<T>) new SqlTimestampTypeAdapter(dateTypeAdapter);
          } else {
            return null;
          }
        }
      };
  private final TypeAdapter<Date> dateTypeAdapter;
  private SqlTimestampTypeAdapter(TypeAdapter<Date> dateTypeAdapter) {
    this.dateTypeAdapter = dateTypeAdapter;
  }
  public Timestamp read(JsonReader in) throws IOException {
    Date date = dateTypeAdapter.read(in);
    return null != date ? new Timestamp(date.getTime()) : null;
  }
  public void write(JsonWriter out, Timestamp value) throws IOException {
      dateTypeAdapter.write(out, value);
  }
}
