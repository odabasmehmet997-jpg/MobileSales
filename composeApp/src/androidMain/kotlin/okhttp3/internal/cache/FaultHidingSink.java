package okhttp3.internal.cache;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;
import java.io.EOFException;
import java.io.IOException;

public class FaultHidingSink extends ForwardingSink {
    private final Function1<IOException, Unit> onException;
    public final Function1<IOException, Unit> getOnException() {
        return this.onException;
    }
    private boolean hasErrors;
    public FaultHidingSink(final Sink delegate, final Function1<? super IOException, Unit> onException) {
        super(delegate);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(onException, "onException");
        this.onException = (Function1<IOException, Unit>) onException;
    }
    public void write(final Buffer source, final long j2) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (hasErrors) {
            source.skip(j2);
            return;
        }
        try {
            super.write(source, j2);
        } catch (final IOException e2) {
            hasErrors = true;
            onException.invoke(e2);
        }
    }
    public void commit() {
        super.commit();
    }
    public void flush() {
        if (hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (final IOException e2) {
            hasErrors = true;
            onException.invoke(e2);
        }
    }
    public void close() {
        if (hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (final IOException e2) {
            hasErrors = true;
            onException.invoke(e2);
        }
    }
}
