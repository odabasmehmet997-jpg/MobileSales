package okhttp3.internal.http2;

import androidx.core.view.PointerIconCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

import java.io.IOException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public final class Huffman {
    public static final Huffman INSTANCE = new Huffman();
    private static final byte[] CODE_BIT_COUNTS;
    private static final int[] CODES = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, 4090, 8185, 21, 248, 2042, PointerIconCompat.TYPE_ZOOM_IN, PointerIconCompat.TYPE_ZOOM_OUT, 249, 2043, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, PointerIconCompat.TYPE_GRAB, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 252, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
    private static final Node root = new Node();

    static {
        final byte[] bArr = {13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
        CODE_BIT_COUNTS = bArr;
        final int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Huffman.INSTANCE.addCode(i2, Huffman.CODES[i2], Huffman.CODE_BIT_COUNTS[i2]);
        }
    }

    private Huffman() {
    }

    public void encode(final ByteString source, final BufferedSink sink) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        final int size = source.size();
        long j2 = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            final int i4 = i2 + 1;
            final int iAnd = Util.and(source.getByte(i2), 255);
            final int i5 = Huffman.CODES[iAnd];
            final byte b2 = Huffman.CODE_BIT_COUNTS[iAnd];
            j2 = (j2 << b2) | i5;
            i3 += b2;
            while (8 <= i3) {
                i3 -= 8;
                sink.writeByte((int) (j2 >> i3));
            }
            i2 = i4;
        }
        if (0 < i3) {
            sink.writeByte((int) ((j2 << (8 - i3)) | (255 >>> i3)));
        }
    }

    public int encodedLength(final ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long j2 = 0;
        for (int i2 = 0; i2 < bytes.size(); i2++) {
            j2 += Huffman.CODE_BIT_COUNTS[Util.and(bytes.getByte(i2), 255)];
        }
        return (int) ((j2 + 7) >> 3);
    }

    public void decode(final BufferedSource source, final long j2, final BufferedSink sink) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        Node node = Huffman.root;
        int iAnd = 0;
        long j3 = 0;
        int terminalBitCount = 0;
        while (j3 < j2) {
            j3++;
            iAnd = (iAnd << 8) | Util.and(source.readByte(), 255);
            terminalBitCount += 8;
            while (8 <= terminalBitCount) {
                final Node[] children = node.getChildren();
                checkNotNull(children);
                node = children[(iAnd >>> (terminalBitCount - 8)) & 255];
                checkNotNull(node);
                if (null == node.getChildren()) {
                    sink.writeByte(node.getSymbol());
                    terminalBitCount -= node.getTerminalBitCount();
                    node = Huffman.root;
                } else {
                    terminalBitCount -= 8;
                }
            }
        }
        while (0 < terminalBitCount) {
            final Node[] children2 = node.getChildren();
            checkNotNull(children2);
            final Node node2 = children2[(iAnd << (8 - terminalBitCount)) & 255];
            checkNotNull(node2);
            if (null != node2.getChildren() || node2.getTerminalBitCount() > terminalBitCount) {
                return;
            }
            sink.writeByte(node2.getSymbol());
            terminalBitCount -= node2.getTerminalBitCount();
            node = Huffman.root;
        }
    }

    private void addCode(final int i2, final int i3, int i4) {
        final Node node = new Node(i2, i4);
        Node node2 = Huffman.root;
        while (8 < i4) {
            i4 -= 8;
            final int i5 = (i3 >>> i4) & 255;
            final Node[] children = node2.getChildren();
            checkNotNull(children);
            Node node3 = children[i5];
            if (null == node3) {
                node3 = new Node();
                children[i5] = node3;
            }
            node2 = node3;
        }
        final int i6 = 8 - i4;
        final int i7 = (i3 << i6) & 255;
        final Node[] children2 = node2.getChildren();
        checkNotNull(children2);
        ArraysKt.fill(children2, node, i7, (1 << i6) + i7);
    }

    /* compiled from: Huffman.kt */
    private static final class Node {
        private final Node[] children;
        private final int symbol;
        private final int terminalBitCount;

        public Node() {
            children = new Node[256];
            symbol = 0;
            terminalBitCount = 0;
        }

        public Node(final int i2, final int i3) {
            children = null;
            symbol = i2;
            final int i4 = i3 & 7;
            terminalBitCount = 0 == i4 ? 8 : i4;
        }

        public Node[] getChildren() {
            return children;
        }

        public int getSymbol() {
            return symbol;
        }

        public int getTerminalBitCount() {
            return terminalBitCount;
        }
    }
}
