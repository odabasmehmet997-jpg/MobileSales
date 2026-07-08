package okhttp3;

import java.io.IOException;
import okio.Timeout;
import retrofit2.Response;
public interface Call<T> extends Cloneable, retrofit2.Call<T> {
  void cancel();
  Call<T> clone();
  default void enqueue(retrofit2.Callback<T> callback) {
  }
  void enqueue(Callback callback);
  Response<T> execute() throws IOException;
  boolean isCanceled();
  boolean isExecuted();
  Request request();
  Timeout timeout();
  public class Factory {
  }
}
