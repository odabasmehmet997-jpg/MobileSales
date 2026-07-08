package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.*;

import static java.lang.Math.toIntExact;

/**
 * Type adapters for {@code java.time} types.
 *
 * <p>These adapters mimic what {@link ReflectiveTypeAdapterFactory} would produce for the same
 * types. That is by no means a natural encoding, given that many of the types have standard ISO
 * representations. If Gson had added support for the types at the same time they appeared (in Java
 * 8, released in 2014), it would surely have used those representations. Unfortunately, in the
 * intervening time, people have been using the reflective representations, and changing that would
 * potentially be incompatible. Meanwhile, depending on the details of private fields in JDK classes
 * is obviously fragile, and it also needs special {@code --add-opens} configuration with more
 * recent JDK versions. So here we freeze the representation that was current with JDK 21, in a way
 * that does not use reflection.
 */
@IgnoreJRERequirement // Protected by a reflective check
final class JavaTimeTypeAdapters implements TypeAdapters.FactorySupplier {

  
  public TypeAdapterFactory get() {
    return JavaTimeTypeAdapters.JAVA_TIME_FACTORY;
  }

  private static final TypeAdapter<Duration> DURATION =
      new TypeAdapters.IntegerFieldsTypeAdapter<Duration>("seconds", "nanos") {

        Duration create(final long[] values) {
          return Duration.ofSeconds(values[0], values[1]);
        }


        @SuppressWarnings("JavaDurationGetSecondsGetNano")
        long[] integerValues(final Duration duration) {
          return new long[] {duration.getSeconds(), duration.getNano()};
        }
      };

  private static final TypeAdapter<Instant> INSTANT =
      new TypeAdapters.IntegerFieldsTypeAdapter<Instant>("seconds", "nanos") {

        Instant create(final long[] values) {
          return Instant.ofEpochSecond(values[0], values[1]);
        }


        @SuppressWarnings("JavaInstantGetSecondsGetNano")
        long[] integerValues(final Instant instant) {
          return new long[] {instant.getEpochSecond(), instant.getNano()};
        }
      };

  private static final TypeAdapter<LocalDate> LOCAL_DATE =
      new TypeAdapters.IntegerFieldsTypeAdapter<LocalDate>("year", "month", "day") {

        LocalDate create(final long[] values) {
          return LocalDate.of(toIntExact(values[0]), toIntExact(values[1]), toIntExact(values[2]));
        }


        long[] integerValues(final LocalDate localDate) {
          return new long[] {
            localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()
          };
        }
      };

  public static final TypeAdapter<LocalTime> LOCAL_TIME =
      new TypeAdapters.IntegerFieldsTypeAdapter<LocalTime>("hour", "minute", "second", "nano") {

        LocalTime create(final long[] values) {
          return LocalTime.of(
              toIntExact(values[0]),
              toIntExact(values[1]),
              toIntExact(values[2]),
              toIntExact(values[3]));
        }


        long[] integerValues(final LocalTime localTime) {
          return new long[] {
            localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNano()
          };
        }
      };

  private static TypeAdapter<LocalDateTime> localDateTime(final Gson gson) {
    final TypeAdapter<LocalDate> localDateAdapter = gson.getAdapter(LocalDate.class);
    final TypeAdapter<LocalTime> localTimeAdapter = gson.getAdapter(LocalTime.class);
    return new TypeAdapter<LocalDateTime>() {

      public LocalDateTime read(final JsonReader in) throws IOException {
        LocalDate localDate = null;
        LocalTime localTime = null;
        in.beginObject();
        while (JsonToken.END_OBJECT != in.peek()) {
          final String name = in.nextName();
          switch (name) {
            case "date":
              localDate = localDateAdapter.read(in);
              break;
            case "time":
              localTime = localTimeAdapter.read(in);
              break;
            default:
              // Ignore other fields.
              in.skipValue();
          }
        }
        in.endObject();
        return LocalDateTime.of(
                JavaTimeTypeAdapters.requireNonNullField(localDate, "date", in), JavaTimeTypeAdapters.requireNonNullField(localTime, "time", in));
      }


      public void write(final JsonWriter out, final LocalDateTime value) throws IOException {
        out.beginObject();
        out.name("date");
        localDateAdapter.write(out, value.toLocalDate());
        out.name("time");
        localTimeAdapter.write(out, value.toLocalTime());
        out.endObject();
      }
    }.nullSafe();
  }

  private static final TypeAdapter<MonthDay> MONTH_DAY =
      new TypeAdapters.IntegerFieldsTypeAdapter<MonthDay>("month", "day") {

        MonthDay create(final long[] values) {
          return MonthDay.of(toIntExact(values[0]), toIntExact(values[1]));
        }


        long[] integerValues(final MonthDay monthDay) {
          return new long[] {monthDay.getMonthValue(), monthDay.getDayOfMonth()};
        }
      };

  private static TypeAdapter<OffsetDateTime> offsetDateTime(final Gson gson) {
    final TypeAdapter<LocalDateTime> localDateTimeAdapter = JavaTimeTypeAdapters.localDateTime(gson);
    final TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
    return new TypeAdapter<OffsetDateTime>() {

      public OffsetDateTime read(final JsonReader in) throws IOException {
        in.beginObject();
        LocalDateTime localDateTime = null;
        ZoneOffset zoneOffset = null;
        while (JsonToken.END_OBJECT != in.peek()) {
          final String name = in.nextName();
          switch (name) {
            case "dateTime":
              localDateTime = localDateTimeAdapter.read(in);
              break;
            case "offset":
              zoneOffset = zoneOffsetAdapter.read(in);
              break;
            default:
              // Ignore other fields.
              in.skipValue();
          }
        }
        in.endObject();
        return OffsetDateTime.of(
                JavaTimeTypeAdapters.requireNonNullField(localDateTime, "dateTime", in),
                JavaTimeTypeAdapters.requireNonNullField(zoneOffset, "offset", in));
      }


      public void write(final JsonWriter out, final OffsetDateTime value) throws IOException {
        out.beginObject();
        out.name("dateTime");
        localDateTimeAdapter.write(out, value.toLocalDateTime());
        out.name("offset");
        zoneOffsetAdapter.write(out, value.getOffset());
        out.endObject();
      }
    }.nullSafe();
  }

  private static TypeAdapter<OffsetTime> offsetTime(final Gson gson) {
    final TypeAdapter<LocalTime> localTimeAdapter = gson.getAdapter(LocalTime.class);
    final TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
    return new TypeAdapter<OffsetTime>() {

      public OffsetTime read(final JsonReader in) throws IOException {
        in.beginObject();
        LocalTime localTime = null;
        ZoneOffset zoneOffset = null;
        while (JsonToken.END_OBJECT != in.peek()) {
          final String name = in.nextName();
          switch (name) {
            case "time":
              localTime = localTimeAdapter.read(in);
              break;
            case "offset":
              zoneOffset = zoneOffsetAdapter.read(in);
              break;
            default:
              // Ignore other fields.
              in.skipValue();
          }
        }
        in.endObject();
        return OffsetTime.of(
                JavaTimeTypeAdapters.requireNonNullField(localTime, "time", in),
                JavaTimeTypeAdapters.requireNonNullField(zoneOffset, "offset", in));
      }


      public void write(final JsonWriter out, final OffsetTime value) throws IOException {
        out.beginObject();
        out.name("time");
        localTimeAdapter.write(out, value.toLocalTime());
        out.name("offset");
        zoneOffsetAdapter.write(out, value.getOffset());
        out.endObject();
      }
    }.nullSafe();
  }

  private static final TypeAdapter<Period> PERIOD =
      new TypeAdapters.IntegerFieldsTypeAdapter<Period>("years", "months", "days") {

        Period create(final long[] values) {
          return Period.of(toIntExact(values[0]), toIntExact(values[1]), toIntExact(values[2]));
        }


        long[] integerValues(final Period period) {
          return new long[] {period.getYears(), period.getMonths(), period.getDays()};
        }
      };

  private static final TypeAdapter<Year> YEAR =
      new TypeAdapters.IntegerFieldsTypeAdapter<Year>("year") {

        Year create(final long[] values) {
          return Year.of(toIntExact(values[0]));
        }


        long[] integerValues(final Year year) {
          return new long[] {year.getValue()};
        }
      };

  private static final TypeAdapter<YearMonth> YEAR_MONTH =
      new TypeAdapters.IntegerFieldsTypeAdapter<YearMonth>("year", "month") {
        
        YearMonth create(long[] values) {
          return YearMonth.of(toIntExact(values[0]), toIntExact(values[1]));
        }

        
        long[] integerValues(YearMonth yearMonth) {
          return new long[] {yearMonth.getYear(), yearMonth.getMonthValue()};
        }
      };

  // A ZoneId is either a ZoneOffset or a ZoneRegion, where ZoneOffset is public and ZoneRegion is
  // not. For compatibility with reflection-based serialization, we need to write the "id" field of
  // ZoneRegion if we have a ZoneRegion, and we need to write the "totalSeconds" field of ZoneOffset
  // if we have a ZoneOffset. When reading, we need to construct the the appropriate thing depending
  // on which of those two fields we see.
  private static final TypeAdapter<ZoneId> ZONE_ID =
      new TypeAdapter<ZoneId>() {
        
        public ZoneId read(JsonReader in) throws IOException {
          in.beginObject();
          String id = null;
          Integer totalSeconds = null;
          while (JsonToken.END_OBJECT != in.peek()) {
            String name = in.nextName();
            switch (name) {
              case "id":
                id = in.nextString();
                break;
              case "totalSeconds":
                totalSeconds = in.nextInt();
                break;
              default:
                // Ignore other fields.
                in.skipValue();
            }
          }
          in.endObject();
          if (null != id) {
            return ZoneId.of(id);
          } else if (null != totalSeconds) {
            return ZoneOffset.ofTotalSeconds(totalSeconds);
          } else {
            throw new JsonSyntaxException(
                "Missing id or totalSeconds field; at path " + in.getPreviousPath());
          }
        }

        
        public void write(JsonWriter out, ZoneId value) throws IOException {
          if (value instanceof ZoneOffset) {
            out.beginObject();
            out.name("totalSeconds");
            out.value(((ZoneOffset) value).getTotalSeconds());
            out.endObject();
          } else {
            out.beginObject();
            out.name("id");
            out.value(value.getId());
            out.endObject();
          }
        }
      }.nullSafe();

  private static TypeAdapter<ZonedDateTime> zonedDateTime(Gson gson) {
    TypeAdapter<LocalDateTime> localDateTimeAdapter = localDateTime(gson);
    TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
    TypeAdapter<ZoneId> zoneIdAdapter = gson.getAdapter(ZoneId.class);
    return new TypeAdapter<ZonedDateTime>() {
      
      public ZonedDateTime read(JsonReader in) throws IOException {
        in.beginObject();
        LocalDateTime localDateTime = null;
        ZoneOffset zoneOffset = null;
        ZoneId zoneId = null;
        while (JsonToken.END_OBJECT != in.peek()) {
          String name = in.nextName();
          switch (name) {
            case "dateTime":
              localDateTime = localDateTimeAdapter.read(in);
              break;
            case "offset":
              zoneOffset = zoneOffsetAdapter.read(in);
              break;
            case "zone":
              zoneId = zoneIdAdapter.read(in);
              break;
            default:
              // Ignore other fields.
              in.skipValue();
          }
        }
        in.endObject();
        return ZonedDateTime.ofInstant(
                requireNonNullField(localDateTime, "dateTime", in),
                requireNonNullField(zoneOffset, "offset", in),
                requireNonNullField(zoneId, "zone", in));
      }

      
      public void write(JsonWriter out, ZonedDateTime value) throws IOException {
        if (null == value) {
          out.nullValue();
          return;
        }
        out.beginObject();
        out.name("dateTime");
        localDateTimeAdapter.write(out, value.toLocalDateTime());
        out.name("offset");
        zoneOffsetAdapter.write(out, value.getOffset());
        out.name("zone");
        zoneIdAdapter.write(out, value.getZone());
        out.endObject();
      }
    }.nullSafe();
  }

  static final TypeAdapterFactory JAVA_TIME_FACTORY =
      new TypeAdapterFactory() {
        
        public <T> TypeAdapters.EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
          Class<? super T> rawType = typeToken.getRawType();
          if (!rawType.getName().startsWith("java.time.")) {
            // Immediately return null so we don't load all these classes when nobody's doing
            // anything with java.time.
            return null;
          }
          TypeAdapter<?> adapter = null;
          if (Duration.class == rawType) {
            adapter = DURATION;
          } else if (Instant.class == rawType) {
            adapter = INSTANT;
          } else if (LocalDate.class == rawType) {
            adapter = LOCAL_DATE;
          } else if (LocalTime.class == rawType) {
            adapter = LOCAL_TIME;
          } else if (LocalDateTime.class == rawType) {
            adapter = localDateTime(gson);
          } else if (MonthDay.class == rawType) {
            adapter = MONTH_DAY;
          } else if (OffsetDateTime.class == rawType) {
            adapter = offsetDateTime(gson);
          } else if (OffsetTime.class == rawType) {
            adapter = offsetTime(gson);
          } else if (Period.class == rawType) {
            adapter = PERIOD;
          } else if (Year.class == rawType) {
            adapter = YEAR;
          } else if (YearMonth.class == rawType) {
            adapter = YEAR_MONTH;
          } else if (ZoneId.class == rawType || ZoneOffset.class == rawType) {
            // We don't check ZoneId.class.isAssignableFrom(rawType) because we don't want to match
            // the non-public class ZoneRegion in the runtime type check in
            // TypeAdapterRuntimeTypeWrapper.write. If we did, then our ZONE_ID would take
            // precedence over a ZoneId adapter that the user might have registered. (This exact
            // situation showed up in a Google-internal test.)
            adapter = ZONE_ID;
          } else if (ZonedDateTime.class == rawType) {
            adapter = zonedDateTime(gson);
          }
          @SuppressWarnings("unchecked") TypeAdapter<T> result = (TypeAdapter<T>) adapter;
          return result;
        }
      };

  private static <T> T requireNonNullField(T field, String fieldName, JsonReader reader) {
    if (null == field) {
      throw new JsonSyntaxException(
          "Missing " + fieldName + " field; at path " + reader.getPreviousPath());
    }
    return field;
  }
}
