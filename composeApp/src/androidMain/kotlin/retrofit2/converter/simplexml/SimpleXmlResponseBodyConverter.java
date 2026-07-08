package retrofit2.converter.simplexml;

import java.io.IOException;
import okhttp3.ResponseBody;
import org.simpleframework.xml.Serializer;
import retrofit2.Converter;

final class SimpleXmlResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Class<T> cls;
    private final Serializer serializer;
    private final boolean strict;
    SimpleXmlResponseBodyConverter(Class<T> cls, Serializer serializer, boolean z) {
        this.cls = cls;
        this.serializer = serializer;
        this.strict = z;
    }
    public T convert(ResponseBody responseBody) throws IOException, RuntimeException {
        try (responseBody) {
            try {
                T t = (T) this.serializer.read((Class) this.cls, responseBody.charStream(), this.strict);
                if (t != null) {
                    return t;
                }
                throw new IllegalStateException("Could not deserialize body as " + this.cls);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
