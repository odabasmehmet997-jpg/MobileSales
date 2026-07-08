package retrofit2;

import java.util.Objects;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.OkHttpCall;

public final class Response<T> {
    private final T body;
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;
    public static <T> Response<T> success(T t) {
        return success(t, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }
    public static <T> Response<T> success(int i2, T t) {
        if (i2 < 200 || i2 >= 300) {
            throw new IllegalArgumentException("code < 200 or >= 300: " + i2);
        }
        return success(t, new Response.Builder().code(i2).message("Response.success()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }
    public static <T> Response<T> success(T t, Headers headers) {
        Objects.requireNonNull(headers, "headers == null");
        return success(t, new Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).headers(headers).request(new Request.Builder().url("http://localhost/").build()).build());
    }
    public static <T> Response<T> success(T t, okhttp3.Response response) {
        Objects.requireNonNull(response, "rawResponse == null");
        if (!response.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        return new Response<>(response, t, null);
    }
    public static <T> Response<T> error(int i2, ResponseBody responseBody) {
        Objects.requireNonNull(responseBody, "body == null");
        if (i2 < 400) {
            throw new IllegalArgumentException("code < 400: " + i2);
        }
        return error(responseBody, new Response.Builder().body(new OkHttpCall.NoContentResponseBody(responseBody.contentType(), responseBody.contentLength())).code(i2).message("Response.error()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }
    public static <T> Response<T> error(ResponseBody responseBody, okhttp3.Response response) {
        Objects.requireNonNull(responseBody, "body == null");
        Objects.requireNonNull(response, "rawResponse == null");
        if (response.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new Response<>(response, null, responseBody);
    }
    private Response(okhttp3.Response response, T t, ResponseBody responseBody) {
        this.rawResponse = response;
        this.body = t;
        this.errorBody = responseBody;
    }
    public okhttp3.Response raw() {
        return this.rawResponse;
    }
    public int code() {
        return this.rawResponse.code();
    }
    public String message() {
        return this.rawResponse.message();
    }
    public Headers headers() {
        return this.rawResponse.headers();
    }
    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }
    public T body() {
        return this.body;
    }
    public ResponseBody errorBody() {
        return this.errorBody;
    }
    public String toString() {
        return this.rawResponse.toString();
    }
}
