package okio;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class ForwardingFileSystem2 extends Lambda  {
    final ForwardingFileSystem this0;
    ForwardingFileSystem2(ForwardingFileSystem forwardingFileSystem) {
        super(1);
        this.this0 = forwardingFileSystem;
    }
    public Path invoke(Path it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.this0.onPathResult(it, "listRecursively");
    }
}
