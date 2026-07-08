package okhttp3.internal.http2;

import com.google.android.material.CR;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Settings {
    public static final int COUNT = 10;
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    private final int[] values = new int[10];
    private int set;

    public int getHeaderTableSize() {
        if (0 != (set & 2)) {
            return this.values[1];
        }
        return -1;
    }

    public int getInitialWindowSize() {
        if (0 != (set & 128)) {
            return this.values[7];
        }
        return 65535;
    }

    public void clear() {
        this.set = 0;
        ArraysKt.fill(this.values, 0, 0, 0);
    }

    public Settings set(int i2, int i3) {
        if (0 <= i2) {
            int[] iArr = this.values;
            if (i2 < iArr.length) {
                this.set = (1 << i2) | this.set;
                iArr[i2] = i3;
            }
        }
        return this;
    }

    public boolean isSet(int i2) {
        return 0 != ((1 << i2) & set);
    }

    public int get(int i2) {
        return this.values[i2];
    }

    public int size() {
        return Integer.bitCount(this.set);
    }

    public boolean getEnablePush(boolean z) {
        return 0 != (set & 4) ? 1 == values[2] : z;
    }

    public int getMaxConcurrentStreams() {
        if (0 != (set & 16)) {
            return this.values[4];
        }
        return Integer.MAX_VALUE;
    }

    public int getMaxFrameSize(int i2) {
        return 0 != (set & 32) ? this.values[5] : i2;
    }

    public int getMaxHeaderListSize(int i2) {
        return 0 != (set & 64) ? this.values[6] : i2;
    }

    public void merge(Settings other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int i2 = 0;
        while (10 > i2) {
            int i3 = i2 + 1;
            if (other.isSet(i2)) {
                set(i2, other.get(i2));
            }
            i2 = i3;
        }
    }
    public void merge(final CR.string settings) {
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
