package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.http.HttpHeaders;

abstract class ParameterHandler<T> {
    abstract void apply(RequestBuilder requestBuilder, Object t) throws IOException;
    ParameterHandler() {
    }
    final ParameterHandler<Iterable<T>> iterable() {
        return new ParameterHandler<Iterable<T>>() {
            public void apply(RequestBuilder requestBuilder, Object iterable) throws IOException {
                if (iterable == null) {
                    return;
                }
                Iterator<T> it = ((Iterable<T>) iterable).iterator();
                while (it.hasNext()) {
                    ParameterHandler.this.apply(requestBuilder, it.next());
                }
            }
        };
    }
    final ParameterHandler<Object> array() {
        return new ParameterHandler<Object>() {
            void apply(RequestBuilder requestBuilder, Object obj) throws IOException {
                if (obj == null) {
                    return;
                }
                int length = Array.getLength(obj);
                for (int i2 = 0; i2 < length; i2++) {
                    ParameterHandler.this.apply(requestBuilder, Array.get(obj, i2));
                }
            }
        };
    }
    static final class RelativeUrl extends ParameterHandler<Object> {
        private final Method method;
        private final int p;

        RelativeUrl(Method method, int i2) {
            this.method = method;
            this.p = i2;
        }
        void apply(RequestBuilder requestBuilder, Object obj) {
            if (obj == null) {
                throw Utils.parameterError(this.method, this.p, "@Url parameter is null.");
            }
            requestBuilder.setRelativeUrl(obj);
        }
    }
    static final class Header<T> extends ParameterHandler<T> {
        private final String name;
        private final Converter<T, String> valueConverter;

        Header(String str, Converter<T, String> converter) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            String strConvert;
            if (t == null || (strConvert = this.valueConverter.convert((T) t)) == null) {
                return;
            }
            requestBuilder.addHeader(this.name, strConvert);
        }
    }
    static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Method method;
        private final String name;
        private final int p;
        private final Converter<T, String> valueConverter;

        Path(Method method, int i2, String str, Converter<T, String> converter, boolean z) {
            this.method = method;
            this.p = i2;
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            if (t == null) {
                throw Utils.parameterError(this.method, this.p, "Path parameter \"" + this.name + "\" value must not be null.");
            }
            requestBuilder.addPathParam(this.name, this.valueConverter.convert((T) t), this.encoded);
        }
    }
    static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;
        Query(String str, Converter<T, String> converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            String strConvert;
            if (t == null || (strConvert = this.valueConverter.convert((T) t)) == null) {
                return;
            }
            requestBuilder.addQueryParam(this.name, strConvert, this.encoded);
        }
    }
    static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;
        QueryName(Converter<T, String> converter, boolean z) {
            this.nameConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            if (t == null) {
                return;
            }
            requestBuilder.addQueryParam(this.nameConverter.convert((T) t), null, this.encoded);
        }
    }
    static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;
        QueryMap(Method method, int i2, Converter<T, String> converter, boolean z) {
            this.method = method;
            this.p = i2;
            this.valueConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw Utils.parameterError(this.method, this.p, "Query map was null");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map contained null value for key '" + key + "'.");
                }
                String strConvert = this.valueConverter.convert(value);
                if (strConvert == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + key + "'.");
                }
                requestBuilder.addQueryParam(key, strConvert, this.encoded);
            }
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
        }
    }
    static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;
        HeaderMap(Method method, int i2, Converter<T, String> converter) {
            this.method = method;
            this.p = i2;
            this.valueConverter = converter;
        }
        void apply(RequestBuilder requestBuilder, Map<String, T> map) throws IOException {
            if (map == null) {
                throw Utils.parameterError(this.method, this.p, "Header map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw Utils.parameterError(this.method, this.p, "Header map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw Utils.parameterError(this.method, this.p, "Header map contained null value for key '" + key + "'.");
                }
                requestBuilder.addHeader(key, this.valueConverter.convert((T) value));
            }
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
        }
    }
    static final class Headers extends ParameterHandler<okhttp3.Headers> {
        private final Method method;
        private final int p;

        Headers(Method method, int i2) {
            this.method = method;
            this.p = i2;
        }
        public void apply(RequestBuilder requestBuilder, Object headers) {
            if (headers == null) {
                throw Utils.parameterError(this.method, this.p, "Headers parameter must not be null.");
            }
            requestBuilder.addHeaders((okhttp3.Headers) headers);
        }
    }
    static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;
        Field(String str, Converter<T, String> converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            String strConvert;
            if (t == null || (strConvert = this.valueConverter.convert((T) t)) == null) {
                return;
            }
            requestBuilder.addFormField(this.name, strConvert, this.encoded);
        }
    }
    static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter<T, String> valueConverter;

        FieldMap(Method method, int i2, Converter<T, String> converter, boolean z) {
            this.method = method;
            this.p = i2;
            this.valueConverter = converter;
            this.encoded = z;
        }
        void apply(RequestBuilder requestBuilder, Object map) throws IOException {
            if (map == null) {
                throw Utils.parameterError(this.method, this.p, "Field map was null.");
            }

            for (Map.Entry<String, T> entry : ((Map<String, T>) map).entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map contained null value for key '" + key + "'.");
                }
                String strConvert = this.valueConverter.convert(value);
                if (strConvert == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + key + "'.");
                }
                requestBuilder.addFormField(key, strConvert, this.encoded);
            }
        }
    }
    static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final okhttp3.Headers headers;
        private final Method method;
        private final int p;
        Part(Method method, int p, okhttp3.Headers headers, Converter<T, RequestBody> converter) {
            this.method = method;
            this.p = p;
            this.headers = headers;
            this.converter = converter;
        }
        void apply(RequestBuilder requestBuilder, Object t) {
            if (t == null) {
                return;
            }
            try {
                requestBuilder.addPart(this.headers, this.converter.convert((T) t));
            } catch (IOException e2) {
                throw Utils.parameterError(this.method, this.p, "Unable to convert " + t + " to RequestBody", e2);
            }
        }
    }
    static final class RawPart extends ParameterHandler<MultipartBody.Part> {
        static final RawPart INSTANCE = new RawPart();
        void apply(RequestBuilder requestBuilder, Object t) throws IOException {
            if (t == null) {
                return;
            }
            requestBuilder.addPart((MultipartBody.Part) t);
        }

        private RawPart() {
        }
        void apply(RequestBuilder requestBuilder, MultipartBody.Part part) {
            if (part == null) {
                return;
            }
            requestBuilder.addPart(part);
        }
    }
    static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;
        private final int p;
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;
        PartMap(Method method, int p, Converter<T, RequestBody> converter, String transferEncoding) {
            this.method = method;
            this.p = p;
            this.valueConverter = converter;
            this.transferEncoding = transferEncoding;
        }
        void apply(RequestBuilder requestBuilder, Object map) throws IOException {
            if (map == null) {
                throw Utils.parameterError(this.method, this.p, "Part map was null.");
            }
            for (Map.Entry<String, T> entry : ((Map<String, T>) map).entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw Utils.parameterError(this.method, this.p, "Part map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw Utils.parameterError(this.method, this.p, "Part map contained null value for key '" + key + "'.");
                }
                requestBuilder.addPart(okhttp3.Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + key + "\"", "Content-Transfer-Encoding", this.transferEncoding), this.valueConverter.convert(value));
            }
        }
    }
    static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Method method;
        private final int p;
        Body(Method method, int p, Converter<T, RequestBody> converter) {
            this.method = method;
            this.p = p;
            this.converter = converter;
        }
        void apply(RequestBuilder requestBuilder, Object t) {
            if (t == null) {
                throw Utils.parameterError(this.method, this.p, "Body parameter value must not be null.");
            }
            try {
                requestBuilder.setBody(this.converter.convert((T) t));
            } catch (IOException e2) {
                throw Utils.parameterError(this.method, e2, this.p, "Unable to convert " + t + " to RequestBody");
            }
        }
    }
    static final class Tag<T> extends ParameterHandler<T> {
        final Class<T> cls;
        Tag(Class<T> cls) {
            this.cls = cls;
        }
        void apply(RequestBuilder requestBuilder, Object t) {
            requestBuilder.addTag(this.cls, t);
        }
    }
}
