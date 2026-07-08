package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.sql.SqlTimeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class TypeAdapters {
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER;
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL;
    public static final TypeAdapter<BigInteger> BIG_INTEGER;
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Currency> CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<Number> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    private TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    static {
        TypeAdapter<Class> typeAdapterNullSafe = new TypeAdapter<Class>() {
            public void write(JsonWriter jsonWriter, Class cls) {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
            }
            public Class read(JsonReader in) {
                return null;
            }

            public Class read2(JsonReader jsonReader) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
        }.nullSafe();
        CLASS = typeAdapterNullSafe;
        CLASS_FACTORY = newFactory(Class.class, typeAdapterNullSafe);
        TypeAdapter<BitSet> typeAdapterNullSafe2 = new TypeAdapter<BitSet>() {
            public java.util.BitSet read2(com.google.gson.stream.JsonReader r7) {

                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.AnonymousClass2.read2(com.google.gson.stream.JsonReader):java.util.BitSet");
            }
            public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
                jsonWriter.beginArray();
                int length = bitSet.length();
                for (int i2 = 0; i2 < length; i2++) {
                    jsonWriter.value(bitSet.get(i2) ? 1L : 0L);
                }
                jsonWriter.endArray();
            }
            public BitSet read(JsonReader in) {
                return null;
            }
        }.nullSafe();
        BIT_SET = typeAdapterNullSafe2;
        BIT_SET_FACTORY = newFactory(BitSet.class, typeAdapterNullSafe2);
        TypeAdapter<Boolean> typeAdapter = new TypeAdapter<Boolean>() {
            public Boolean read2(JsonReader jsonReader) {
                JsonToken jsonTokenPeek = null;
                try {
                    jsonTokenPeek = jsonReader.peek();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (jsonTokenPeek == JsonToken.NULL) {
                    try {
                        jsonReader.nextNull();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
                if (jsonTokenPeek == JsonToken.STRING) {
                    try {
                        return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    return Boolean.valueOf(jsonReader.nextBoolean());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
                jsonWriter.value(bool);
            }
            public Boolean read(JsonReader in) {
                return null;
            }
        };
        BOOLEAN = typeAdapter;
        BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() {
            public Boolean read2(JsonReader jsonReader) {
                try {
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    return Boolean.valueOf(jsonReader.nextString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
                jsonWriter.value(bool == null ? "null" : bool.toString());
            }
            public Boolean read(JsonReader in) {
                return null;
            }
        };
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, typeAdapter);
        TypeAdapter<Number> typeAdapter2 = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
                try {
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    try {
                        return Byte.valueOf((byte) jsonReader.nextInt());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
            public Number read(JsonReader in) {
                return null;
            }
        };
        BYTE = typeAdapter2;
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, typeAdapter2);
        TypeAdapter<Number> typeAdapter3 = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
                try {
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    try {
                        return Short.valueOf((short) jsonReader.nextInt());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

          @Override
          public Number fromString(String s) {
            return null;
          }

          public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
            public Number read(JsonReader in) {
                return null;
            }
        };
        SHORT = typeAdapter3;
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, typeAdapter3);
        TypeAdapter<Number> typeAdapter4 = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
                try {
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    try {
                        return Integer.valueOf(jsonReader.nextInt());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

          @Override
          public Number fromString(String s) {
            return null;
          }

          public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
            public Number read(JsonReader in) {
                return null;
            }
        };
        INTEGER = typeAdapter4;
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, typeAdapter4);
        TypeAdapter<AtomicInteger> typeAdapterNullSafe3 = new TypeAdapter<AtomicInteger>() {
            public AtomicInteger read2(JsonReader jsonReader) {
                try {
                    return new AtomicInteger(jsonReader.nextInt());
                } catch (NumberFormatException | IOException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            public void write(JsonWriter jsonWriter, AtomicInteger atomicInteger) throws IOException {
                jsonWriter.value(atomicInteger.get());
            }
            public AtomicInteger read(JsonReader in) {
                return null;
            }
        }.nullSafe();
        ATOMIC_INTEGER = typeAdapterNullSafe3;
        ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, typeAdapterNullSafe3);
        TypeAdapter<AtomicBoolean> typeAdapterNullSafe4 = new TypeAdapter<AtomicBoolean>() {
            public AtomicBoolean read2(JsonReader jsonReader) {
                try {
                    return new AtomicBoolean(jsonReader.nextBoolean());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

          @Override
          public AtomicBoolean fromString(String s) {
            return null;
          }

          public void write(JsonWriter jsonWriter, AtomicBoolean atomicBoolean) throws IOException {
                jsonWriter.value(atomicBoolean.get());
            }

            public AtomicBoolean read(JsonReader in) {
                return null;
            }
        }.nullSafe();
        ATOMIC_BOOLEAN = typeAdapterNullSafe4;
        ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, typeAdapterNullSafe4);
        TypeAdapter<AtomicIntegerArray> typeAdapterNullSafe5 = new TypeAdapter<AtomicIntegerArray>() {
            public AtomicIntegerArray read2(JsonReader jsonReader) {
                ArrayList arrayList = new ArrayList();
                try {
                    jsonReader.beginArray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    try {
                        if (!jsonReader.hasNext()) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        try {
                            arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (NumberFormatException e2) {
                        throw new JsonSyntaxException(e2);
                    }
                }
                try {
                    jsonReader.endArray();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
                }
                return atomicIntegerArray;
            }
            public void write(JsonWriter jsonWriter, AtomicIntegerArray atomicIntegerArray) throws IOException {
                jsonWriter.beginArray();
                int length = atomicIntegerArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    jsonWriter.value(atomicIntegerArray.get(i2));
                }
                jsonWriter.endArray();
            }

            @Override
            public AtomicIntegerArray read(JsonReader in) throws IOException {
                return null;
            }
        }.nullSafe();
        ATOMIC_INTEGER_ARRAY = typeAdapterNullSafe5;
        ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, typeAdapterNullSafe5);
        LONG = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
              try {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              try {
                try {
                  return Long.valueOf(jsonReader.nextLong());
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }

            @Override
            public Number read(JsonReader in) throws IOException {
                return null;
            }
        };
        FLOAT = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
              try {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              try {
                return Float.valueOf((float) jsonReader.nextDouble());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }
          public Number read(JsonReader in) throws IOException {
            return null;
          }
        };
        DOUBLE = new TypeAdapter<Number>() {
            public Number read2(JsonReader jsonReader) {
              try {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              try {
                return Double.valueOf(jsonReader.nextDouble());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                jsonWriter.value(number);
            }

          @Override
          public Number read(JsonReader in) throws IOException {
            return null;
          }
        };
        TypeAdapter<Character> typeAdapter5 = new TypeAdapter<Character>() {
            public Character read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String strNextString = null;
                try {
                    strNextString = jsonReader.nextString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (strNextString.length() != 1) {
                    throw new JsonSyntaxException("Expecting character, got: " + strNextString);
                }
                return Character.valueOf(strNextString.charAt(0));
            }

            public void write(JsonWriter jsonWriter, Character ch) throws IOException {
                jsonWriter.value(ch == null ? null : String.valueOf(ch));
            }

          @Override
          public Character read(JsonReader in) throws IOException {
            return null;
          }
        };
        CHARACTER = typeAdapter5;
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, typeAdapter5);
        TypeAdapter<String> typeAdapter6 = new TypeAdapter<String>() {
            public String read2(JsonReader jsonReader) {
                JsonToken jsonTokenPeek = jsonReader.peek();
                if (jsonTokenPeek == JsonToken.NULL) {
                    try {
                        jsonReader.nextNull();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
                if (jsonTokenPeek == JsonToken.BOOLEAN) {
                    try {
                        return Boolean.toString(jsonReader.nextBoolean());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    return jsonReader.nextString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public void write(JsonWriter jsonWriter, String str) throws IOException {
                jsonWriter.value(str);
            }

          @Override
          public String read(JsonReader in) throws IOException {
            return "";
          }
        };
        STRING = typeAdapter6;
        BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
            public BigDecimal read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    try {
                        return new BigDecimal(jsonReader.nextString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
                jsonWriter.value(bigDecimal);
            }

          @Override
          public BigDecimal read(JsonReader in) throws IOException {
            return null;
          }
        };
        BIG_INTEGER = new TypeAdapter<BigInteger>() {
            public BigInteger read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    return new BigInteger(jsonReader.nextString());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
                jsonWriter.value(bigInteger);
            }

          @Override
          public BigInteger read(JsonReader in) throws IOException {
            return null;
          }
        };
        STRING_FACTORY = newFactory(String.class, typeAdapter6);
        TypeAdapter<StringBuilder> typeAdapter7 = new TypeAdapter<StringBuilder>() {
            public StringBuilder read2(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return new StringBuilder(jsonReader.nextString());
            }

          @Override
          public StringBuilder fromString(String s) {
            return null;
          }

          @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
                jsonWriter.value(sb == null ? null : sb.toString());
            }

          @Override
          public StringBuilder read(JsonReader in) throws IOException {
            return null;
          }
        };
        STRING_BUILDER = typeAdapter7;
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, typeAdapter7);
        TypeAdapter<StringBuffer> typeAdapter8 = new TypeAdapter<StringBuffer>() {
            public StringBuffer read2(JsonReader jsonReader) {
              try {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              try {
                return new StringBuffer(jsonReader.nextString());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }

          @Override
          public StringBuffer fromString(String s) {
            return null;
          }

          public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
                jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
            }

          @Override
          public StringBuffer read(JsonReader in) throws IOException {
            return null;
          }
        };
        STRING_BUFFER = typeAdapter8;
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, typeAdapter8);
        TypeAdapter<URL> typeAdapter9 = new TypeAdapter<URL>() {
            public URL read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String strNextString = jsonReader.nextString();
                if ("null".equals(strNextString)) {
                    return null;
                }
                return new URL(strNextString);
            }

          @Override
          public URL fromString(String s) {
            return null;
          }

          @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, URL url) throws IOException {
                jsonWriter.value(url == null ? null : url.toExternalForm());
            }

          @Override
          public URL read(JsonReader in) throws IOException {
            return null;
          }
        };
        URL = typeAdapter9;
        URL_FACTORY = newFactory(URL.class, typeAdapter9);
        TypeAdapter<URI> typeAdapter10 = new TypeAdapter<URI>() {
            public URI read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                try {
                    String strNextString = jsonReader.nextString();
                    if ("null".equals(strNextString)) {
                        return null;
                    }
                    return new URI(strNextString);
                } catch (URISyntaxException e2) {
                    throw new JsonIOException(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, URI uri) throws IOException {
                jsonWriter.value(uri == null ? null : uri.toASCIIString());
            }
        };
        URI = typeAdapter10;
        URI_FACTORY = newFactory(URI.class, typeAdapter10);
        TypeAdapter<InetAddress> typeAdapter11 = new TypeAdapter<InetAddress>() {
            public InetAddress read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return InetAddress.getByName(jsonReader.nextString());
            }
            public void write(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
                jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        INET_ADDRESS = typeAdapter11;
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, typeAdapter11);
        TypeAdapter<UUID> typeAdapter12 = new TypeAdapter<UUID>() {
            public UUID read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
              try {
                return UUID.fromString(jsonReader.nextString());
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }
            public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
                jsonWriter.value(uuid == null ? null : uuid.toString());
            }
        };
        UUID = typeAdapter12;
        UUID_FACTORY = newFactory(UUID.class, typeAdapter12);
        TypeAdapter<Currency> typeAdapterNullSafe6 = new TypeAdapter<Currency>() {
            public Currency read2(JsonReader jsonReader) {
                return Currency.getInstance(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Currency currency) throws IOException {
                jsonWriter.value(currency.getCurrencyCode());
            }

          @Override
          public Currency read(JsonReader in) throws IOException {
            return null;
          }
        }.nullSafe();
        CURRENCY = typeAdapterNullSafe6;
        CURRENCY_FACTORY = newFactory(Currency.class, typeAdapterNullSafe6);
        TypeAdapter<Calendar> typeAdapter13 = new TypeAdapter<Calendar>() {
            private static final String DAY_OF_MONTH = "dayOfMonth";
            private static final String HOUR_OF_DAY = "hourOfDay";
            private static final String MINUTE = "minute";
            private static final String MONTH = "month";
            private static final String SECOND = "second";
            private static final String YEAR = "year";
            public Calendar read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                jsonReader.beginObject();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (jsonReader.peek() != JsonToken.END_OBJECT) {
                    String strNextName = jsonReader.nextName();
                    int iNextInt = jsonReader.nextInt();
                    if (YEAR.equals(strNextName)) {
                        i2 = iNextInt;
                    } else if (MONTH.equals(strNextName)) {
                        i3 = iNextInt;
                    } else if (DAY_OF_MONTH.equals(strNextName)) {
                        i4 = iNextInt;
                    } else if (HOUR_OF_DAY.equals(strNextName)) {
                        i5 = iNextInt;
                    } else if (MINUTE.equals(strNextName)) {
                        i6 = iNextInt;
                    } else if (SECOND.equals(strNextName)) {
                        i7 = iNextInt;
                    }
                }
                jsonReader.endObject();
                return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
                if (calendar == null) {
                    jsonWriter.nullValue();
                    return;
                }
                jsonWriter.beginObject();
                jsonWriter.name(YEAR);
                jsonWriter.value(calendar.get(1));
                jsonWriter.name(MONTH);
                jsonWriter.value(calendar.get(2));
                jsonWriter.name(DAY_OF_MONTH);
                jsonWriter.value(calendar.get(5));
                jsonWriter.name(HOUR_OF_DAY);
                jsonWriter.value(calendar.get(11));
                jsonWriter.name(MINUTE);
                jsonWriter.value(calendar.get(12));
                jsonWriter.name(SECOND);
                jsonWriter.value(calendar.get(13));
                jsonWriter.endObject();
            }

          @Override
          public Calendar read(JsonReader in) throws IOException {
            return null;
          }
        };
        CALENDAR = typeAdapter13;
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, typeAdapter13);
        TypeAdapter<Locale> typeAdapter14 = new TypeAdapter<Locale>() {
            public Locale read2(JsonReader jsonReader) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
                String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                if (strNextToken2 == null && strNextToken3 == null) {
                    return new Locale(strNextToken);
                }
                if (strNextToken3 == null) {
                    return new Locale(strNextToken, strNextToken2);
                }
                return new Locale(strNextToken, strNextToken2, strNextToken3);
            }

            public void write(JsonWriter jsonWriter, Locale locale) throws IOException {
                jsonWriter.value(locale == null ? null : locale.toString());
            }

          @Override
          public Locale read(JsonReader in) throws IOException {
            return null;
          }
        };
        LOCALE = typeAdapter14;
        LOCALE_FACTORY = newFactory(Locale.class, typeAdapter14);
        TypeAdapter<JsonElement> typeAdapter15 = new TypeAdapter<JsonElement>() {
            public JsonElement read2(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    return ((JsonTreeReader) jsonReader).nextJsonElement();
                }
                switch (AnonymousClass34.SwitchMapcomgooglegsonstreamJsonToken[jsonReader.peek().ordinal()]) {
                    case 1:
                        try {
                            return new JsonPrimitive(new LazilyParsedNumber(jsonReader.nextString()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    case 2:
                        try {
                            return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    case 3:
                        try {
                            return new JsonPrimitive(jsonReader.nextString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    case 4:
                        try {
                            jsonReader.nextNull();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return JsonNull.INSTANCE;
                    case 5:
                        JsonArray jsonArray = new JsonArray();
                        try {
                            jsonReader.beginArray();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        while (true) {
                            try {
                                if (!jsonReader.hasNext()) break;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            jsonArray.add(read2(jsonReader));
                        }
                        try {
                            jsonReader.endArray();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return jsonArray;
                    case 6:
                        JsonObject jsonObject = new JsonObject();
                        try {
                            jsonReader.beginObject();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        while (true) {
                            try {
                                if (!jsonReader.hasNext()) break;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                jsonObject.add(jsonReader.nextName(), read2(jsonReader));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        try {
                            jsonReader.endObject();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return jsonObject;
                    default:
                        throw new IllegalArgumentException();
                }
            }
            public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
                if (jsonElement == null || jsonElement.isJsonNull()) {
                    jsonWriter.nullValue();
                    return;
                }
                if (jsonElement.isJsonPrimitive()) {
                    JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                    if (asJsonPrimitive.isNumber()) {
                        jsonWriter.value(asJsonPrimitive.getAsNumber());
                        return;
                    } else if (asJsonPrimitive.isBoolean()) {
                        jsonWriter.value(asJsonPrimitive.getAsBoolean());
                        return;
                    } else {
                        jsonWriter.value(asJsonPrimitive.getAsString());
                        return;
                    }
                }
                if (jsonElement.isJsonArray()) {
                    jsonWriter.beginArray();
                    Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                    while (it.hasNext()) {
                        write(jsonWriter, it.next());
                    }
                    jsonWriter.endArray();
                    return;
                }
                if (jsonElement.isJsonObject()) {
                    jsonWriter.beginObject();
                    for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                        jsonWriter.name(entry.getKey());
                        write(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
            }
            public JsonElement read(JsonReader in) throws IOException {
                return null;
            }
        };
        JSON_ELEMENT = typeAdapter15;
        JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, typeAdapter15);
        ENUM_FACTORY = new TypeAdapterFactory() {
            public <T> EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = rawType.getSuperclass();
                }
                return new EnumTypeAdapter(rawType);
            }
        };
    }
    static   class AnonymousClass34 {
        static final  int[] SwitchMapcomgooglegsonstreamJsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            SwitchMapcomgooglegsonstreamJsonToken = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomgooglegsonstreamJsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<String, T> nameToConstant = new HashMap();
        private final Map<T, String> constantToName = new HashMap();

        public EnumTypeAdapter(Class<T> cls) {
            try {
                for (final Field field : cls.getDeclaredFields()) {
                    if (field.isEnumConstant()) {
                        AccessController.doPrivileged(new PrivilegedAction<Void>() {
                            public Void run() {
                                field.setAccessible(true);
                                return null;
                            }
                        });
                        Enum r4 = (Enum) field.get(null);
                        String strName = r4.name();
                        SerializedName serializedName = field.getAnnotation(SerializedName.class);
                        if (serializedName != null) {
                            strName = serializedName.value();
                            for (String str : serializedName.alternate()) {
                                this.nameToConstant.put(str, (T) r4);
                            }
                        }
                        this.nameToConstant.put(strName, (T) r4);
                        this.constantToName.put((T) r4, strName);
                    }
                }
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
        public T read2(JsonReader jsonReader) {
            try {
                if (jsonReader.peek() == JsonToken.NULL) {
                    try {
                        jsonReader.nextNull();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                return this.nameToConstant.get(jsonReader.nextString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : this.constantToName.get(t));
        }

        public T read(JsonReader in) throws IOException {
            return null;
        }
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken2) {
                if (typeToken2.equals(typeToken)) {
                    return (EnumTypeAdapter) typeAdapter;
                }
                return null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() == cls) {
                    return (EnumTypeAdapter) typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return (EnumTypeAdapter) typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> EnumTypeAdapter create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return (EnumTypeAdapter) typeAdapter;
                }
                return null;
            }
            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(final Class<T1> cls, final TypeAdapter<T1> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T2> EnumTypeAdapter create(Gson gson, TypeToken<T2> typeToken) {
                final Class<? super T2> rawType = typeToken.getRawType();
                if (cls.isAssignableFrom(rawType)) {
                    return (SqlTimeTypeAdapter) new TypeAdapter<T1>() {
                        public void write(JsonWriter jsonWriter, T1 t1) throws IOException {
                            typeAdapter.write(jsonWriter, t1);
                        }
                        public T1 read(JsonReader in) {
                            return null;
                        }
                        public T1 read2(JsonReader jsonReader) {
                            T1 t1 = typeAdapter.read2(jsonReader);
                            if (t1 == null || rawType.isInstance(t1)) {
                                return t1;
                            }
                            throw new JsonSyntaxException("Expected a " + rawType.getName() + " but was " + t1.getClass().getName());
                        }
                    };
                }
                return null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
