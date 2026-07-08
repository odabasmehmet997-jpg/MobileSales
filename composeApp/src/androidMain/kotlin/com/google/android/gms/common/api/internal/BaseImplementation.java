package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

public class BaseImplementation {

    public interface ResultHolder<R> {
        void setFailedResult(Status status);
        void setResult(R r);
    }
    public static abstract class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R> {

        private final Api<?> api;
        private final Api.AnyClientKey<A> clientKey;
        private void setFailedResult(RemoteException remoteException) {
            setFailedResult(new Status(8, remoteException.getLocalizedMessage(), null));
        }
        protected abstract void doExecute(A a2) throws RemoteException;
        public final Api<?> getApi() {
            return this.api;
        }
        public final Api.AnyClientKey<A> getClientKey() {
            return this.clientKey;
        }
        protected void onSetFailedResult(R r) {
        }
        public final void run(A a2) throws DeadObjectException {
            try {
                doExecute(a2);
            } catch (DeadObjectException e2) {
                setFailedResult(e2);
                throw e2;
            } catch (RemoteException e3) {
                setFailedResult(e3);
            }
        }
        protected ApiMethodImpl(Api<?> api, GoogleApiClient googleApiClient) {
            super(Preconditions.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
            Preconditions.checkNotNull(api, "Api must not be null");
            this.clientKey = api.zab();
            this.api = api;
        }
        public final void setFailedResult(Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            R rCreateFailedResult = createFailedResult(status);
            setResult(rCreateFailedResult);
            onSetFailedResult(rCreateFailedResult);
        }
    }
}
