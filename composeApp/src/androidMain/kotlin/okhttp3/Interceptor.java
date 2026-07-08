package okhttp3;

import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public interface Interceptor {
    Response intercept(Chain chain) throws IOException;
    BaseDbSalesFiche Interceptor = null;
    BaseDbSalesFiche.Companion Companion = BaseDbSalesFiche.Companion;
    interface Chain {
        Call<T> call();
        int connectTimeoutMillis();
        Connection connection();
        Response proceed(Request request) throws IOException;
        int readTimeoutMillis();
        Request request();
        Chain withConnectTimeout(int i2, TimeUnit timeUnit);
        Chain withReadTimeout(int i2, TimeUnit timeUnit);
        Chain withWriteTimeout(int i2, TimeUnit timeUnit);
        int writeTimeoutMillis();
        long getReadTimeoutMillisokhttp();
        long getWriteTimeoutMillisokhttp();
    }
    final class Companion {
        static final Companion INSTANCE = new Companion();
        private Companion() {
        }
        public Interceptor invoke(Function1<? super Chain, Response> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return new Interceptor() {
                public Response intercept(final Chain it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return block.invoke(it);
                }
            };
        }
    }


}
