package okhttp3;

import okhttp3.internal.connection.RealCall;

import java.io.IOException;

public interface Callback {
    void onFailure(RealCall call, IOException iOException);

    void onResponse(RealCall call, Response response) throws IOException;
}
