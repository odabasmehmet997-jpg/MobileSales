package okhttp3.internal.http2;

import androidx.webkit.ProxyConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.*;

import java.io.IOException;
import java.util.*;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Hpack {
    public static final Hpack INSTANCE;
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
    private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
    private static final Header[] STATIC_HEADER_TABLE;

    static {
        final Hpack hpack = new Hpack();
        INSTANCE = hpack;
        final Header header = new Header(Header.TARGET_AUTHORITY, "");
        final ByteString byteString = Header.TARGET_METHOD;
        final Header header2 = new Header(byteString, "GET");
        final Header header3 = new Header(byteString, "POST");
        final ByteString byteString2 = Header.TARGET_PATH;
        final Header header4 = new Header(byteString2, "/");
        final Header header5 = new Header(byteString2, "/index.html");
        final ByteString byteString3 = Header.TARGET_SCHEME;
        final Header header6 = new Header(byteString3, ProxyConfig.MATCH_HTTP);
        final Header header7 = new Header(byteString3, ProxyConfig.MATCH_HTTPS);
        final ByteString byteString4 = Header.RESPONSE_STATUS;
        STATIC_HEADER_TABLE = new Header[]{header, header2, header3, header4, header5, header6, header7, new Header(byteString4, "200"), new Header(byteString4, "204"), new Header(byteString4, "206"), new Header(byteString4, "304"), new Header(byteString4, "400"), new Header(byteString4, "404"), new Header(byteString4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header(FirebaseAnalytics.Param.LOCATION, ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        NAME_TO_FIRST_INDEX = hpack.nameToFirstIndex();
    }

    private Hpack() {
    }

    public Header[] getSTATIC_HEADER_TABLE() {
        return Hpack.STATIC_HEADER_TABLE;
    }

    public Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
        return Hpack.NAME_TO_FIRST_INDEX;
    }

    private Map<ByteString, Integer> nameToFirstIndex() {
        final Header[] headerArr = Hpack.STATIC_HEADER_TABLE;
        final LinkedHashMap linkedHashMap = new LinkedHashMap(headerArr.length);
        final int length = headerArr.length;
        int i2 = 0;
        while (i2 < length) {
            final int i3 = i2 + 1;
            final Header[] headerArr2 = Hpack.STATIC_HEADER_TABLE;
            if (!linkedHashMap.containsKey(headerArr2[i2].name)) {
                linkedHashMap.put(headerArr2[i2].name, Integer.valueOf(i2));
            }
            i2 = i3;
        }
        final Map<ByteString, Integer> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap(result)");
        return mapUnmodifiableMap;
    }

    public ByteString checkLowercase(final ByteString name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        final int size = name.size();
        int i2 = 0;
        while (i2 < size) {
            final int i3 = i2 + 1;
            final byte b2 = name.getByte(i2);
            if (65 <= b2 && 90 >= b2) {
                throw new IOException(stringPlus("PROTOCOL_ERROR response malformed: mixed case name: ", name.utf8()));
            }
            i2 = i3;
        }
        return name;
    }

    public static final class Reader {
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private final BufferedSource source;
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public int headerCount;
        private int maxDynamicTableByteCount;
        private int nextHeaderIndex;

        public Reader(final Source source, final int i2) {
            this(source, i2, 0, 4, null);
            Intrinsics.checkNotNullParameter(source, "source");
        }

        public Reader(final Source source, final int i2, final int i3) {
            Intrinsics.checkNotNullParameter(source, "source");
            headerTableSizeSetting = i2;
            maxDynamicTableByteCount = i3;
            headerList = new ArrayList();
            this.source = Okio.buffer(source);
            dynamicTable = new Header[8];
            nextHeaderIndex = r2.length - 1;
        }

        public Reader(final Source source, final int i2, final int i3, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
            this(source, i2, 0 != (i4 & 4) ? i2 : i3);
        }

        public List<Header> getAndResetHeaderList() {
            final List<Header> list = CollectionsKt.toList(headerList);
            headerList.clear();
            return list;
        }

        public int maxDynamicTableByteCount() {
            return maxDynamicTableByteCount;
        }

        private void adjustDynamicTableByteCount() {
            final int i2 = maxDynamicTableByteCount;
            final int i3 = dynamicTableByteCount;
            if (i2 < i3) {
                if (0 == i2) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(i3 - i2);
                }
            }
        }

        private void clearDynamicTable() {
            ArraysKt.fill(dynamicTable, (Object) null, 0, 0);
            nextHeaderIndex = dynamicTable.length - 1;
            headerCount = 0;
            dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i2) {
            int i3;
            int i4 = 0;
            if (0 < i2) {
                int length = dynamicTable.length;
                while (true) {
                    length--;
                    i3 = nextHeaderIndex;
                    if (length < i3 || 0 >= i2) {
                        break;
                    }
                    final Header header = dynamicTable[length];
                    checkNotNull(header);
                    final int i5 = header.hpackSize;
                    i2 -= i5;
                    dynamicTableByteCount -= i5;
                    headerCount--;
                    i4++;
                }
                final Header[] headerArr = dynamicTable;
                System.arraycopy(headerArr, i3 + 1, headerArr, i3 + 1 + i4, headerCount);
                nextHeaderIndex += i4;
            }
            return i4;
        }

        public void readHeaders() throws IOException {
            while (!source.exhausted()) {
                final int iAnd = Util.and(source.readByte(), 255);
                if (128 == iAnd) {
                    throw new IOException("index == 0");
                }
                if (128 == (iAnd & 128)) {
                    this.readIndexedHeader(this.readInt(iAnd, 127) - 1);
                } else if (64 == iAnd) {
                    this.readLiteralHeaderWithIncrementalIndexingNewName();
                } else if (64 == (iAnd & 64)) {
                    this.readLiteralHeaderWithIncrementalIndexingIndexedName(this.readInt(iAnd, 63) - 1);
                } else if (32 == (iAnd & 32)) {
                    final int i2 = this.readInt(iAnd, 31);
                    maxDynamicTableByteCount = i2;
                    if (0 > i2 || i2 > headerTableSizeSetting) {
                        throw new IOException(stringPlus("Invalid dynamic table size update ", Integer.valueOf(maxDynamicTableByteCount)));
                    }
                    this.adjustDynamicTableByteCount();
                } else if (16 == iAnd || 0 == iAnd) {
                    this.readLiteralHeaderWithoutIndexingNewName();
                } else {
                    this.readLiteralHeaderWithoutIndexingIndexedName(this.readInt(iAnd, 15) - 1);
                }
            }
        }

        private void readIndexedHeader(final int i2) throws IOException {
            if (this.isStaticHeader(i2)) {
                headerList.add(INSTANCE.getSTATIC_HEADER_TABLE()[i2]);
                return;
            }
            final int iDynamicTableIndex = this.dynamicTableIndex(i2 - INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (0 <= iDynamicTableIndex) {
                final Header[] headerArr = dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    final List<Header> list = headerList;
                    final Header header = headerArr[iDynamicTableIndex];
                    checkNotNull(header);
                    list.add(header);
                    return;
                }
            }
            throw new IOException(stringPlus("Header index too large ", Integer.valueOf(i2 + 1)));
        }

        private int dynamicTableIndex(final int i2) {
            return nextHeaderIndex + 1 + i2;
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(final int i2) throws IOException {
            headerList.add(new Header(this.getName(i2), this.readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            headerList.add(new Header(INSTANCE.checkLowercase(this.readByteString()), this.readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(final int i2) throws IOException {
            this.insertIntoDynamicTable(-1, new Header(this.getName(i2), this.readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            this.insertIntoDynamicTable(-1, new Header(INSTANCE.checkLowercase(this.readByteString()), this.readByteString()));
        }

        private ByteString getName(final int i2) throws IOException {
            if (this.isStaticHeader(i2)) {
                return INSTANCE.getSTATIC_HEADER_TABLE()[i2].name;
            }
            final int iDynamicTableIndex = this.dynamicTableIndex(i2 - INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (0 <= iDynamicTableIndex) {
                final Header[] headerArr = dynamicTable;
                if (iDynamicTableIndex < headerArr.length) {
                    final Header header = headerArr[iDynamicTableIndex];
                    checkNotNull(header);
                    return header.name;
                }
            }
            throw new IOException(stringPlus("Header index too large ", Integer.valueOf(i2 + 1)));
        }

        private boolean isStaticHeader(final int i2) {
            return 0 <= i2 && i2 <= INSTANCE.getSTATIC_HEADER_TABLE().length - 1;
        }

        private void insertIntoDynamicTable(final int i2, final Header header) {
            headerList.add(header);
            int i3 = header.hpackSize;
            if (-1 != i2) {
                final Header header2 = dynamicTable[this.dynamicTableIndex(i2)];
                checkNotNull(header2);
                i3 -= header2.hpackSize;
            }
            final int i4 = maxDynamicTableByteCount;
            if (i3 > i4) {
                this.clearDynamicTable();
                return;
            }
            final int iEvictToRecoverBytes = this.evictToRecoverBytes((dynamicTableByteCount + i3) - i4);
            if (-1 == i2) {
                final int i5 = headerCount + 1;
                final Header[] headerArr = dynamicTable;
                if (i5 > headerArr.length) {
                    final Header[] headerArr2 = new Header[headerArr.length * 2];
                    System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                    nextHeaderIndex = dynamicTable.length - 1;
                    dynamicTable = headerArr2;
                }
                final int i6 = nextHeaderIndex;
                nextHeaderIndex = i6 - 1;
                dynamicTable[i6] = header;
                headerCount++;
            } else {
                dynamicTable[i2 + this.dynamicTableIndex(i2) + iEvictToRecoverBytes] = header;
            }
            dynamicTableByteCount += i3;
        }

        private int readByte() throws IOException {
            return Util.and(source.readByte(), 255);
        }

        public int readInt(final int i2, int i3) throws IOException {
            final int i4 = i2 & i3;
            if (i4 < i3) {
                return i4;
            }
            int i5 = 0;
            while (true) {
                final int i6 = this.readByte();
                if (0 == (i6 & 128)) {
                    return i3 + (i6 << i5);
                }
                i3 += (i6 & 127) << i5;
                i5 += 7;
            }
        }

        public ByteString readByteString() throws IOException {
            final int i2 = this.readByte();
            final boolean z = 128 == (i2 & 128);
            final long j2 = this.readInt(i2, 127);
            if (z) {
                final Buffer buffer = new Buffer();
                Huffman.INSTANCE.decode(source, j2, buffer);
                return buffer.readByteString();
            }
            return source.readByteString(j2);
        }
    }

    /* compiled from: Hpack.kt */
    public static final class Writer {
        private final Buffer out;
        private final boolean useCompression;
        public Header[] dynamicTable;
        public int dynamicTableByteCount;
        public int headerCount;
        public int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;
        private int nextHeaderIndex;
        private int smallestHeaderTableSizeSetting;

        /*  WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Writer(final int i2, final Buffer out) {
            this(i2, false, out, 2, null);
            Intrinsics.checkNotNullParameter(out, "out");
        }

        /*  WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Writer(final Buffer out) {
            this(0, false, out, 3, null);
            Intrinsics.checkNotNullParameter(out, "out");
        }

        public Writer(final int i2, final boolean z, final Buffer out) {
            Intrinsics.checkNotNullParameter(out, "out");
            headerTableSizeSetting = i2;
            useCompression = z;
            this.out = out;
            smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            maxDynamicTableByteCount = i2;
            dynamicTable = new Header[8];
            nextHeaderIndex = r2.length - 1;
        }

        public Writer(final int i2, final boolean z, final Buffer buffer, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
            this(0 != (i3 & 1) ? 4096 : i2, 0 != (i3 & 2) || z, buffer);
        }

        private void clearDynamicTable() {
            ArraysKt.fill(dynamicTable, (Object) null, 0, 0, 6, (Object) null);
            nextHeaderIndex = dynamicTable.length - 1;
            headerCount = 0;
            dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i2) {
            int i3;
            int i4 = 0;
            if (0 < i2) {
                int length = dynamicTable.length;
                while (true) {
                    length--;
                    i3 = nextHeaderIndex;
                    if (length < i3 || 0 >= i2) {
                        break;
                    }
                    final Header header = dynamicTable[length];
                    checkNotNull(header);
                    i2 -= header.hpackSize;
                    final int i5 = dynamicTableByteCount;
                    final Header header2 = dynamicTable[length];
                    checkNotNull(header2);
                    dynamicTableByteCount = i5 - header2.hpackSize;
                    headerCount--;
                    i4++;
                }
                final Header[] headerArr = dynamicTable;
                System.arraycopy(headerArr, i3 + 1, headerArr, i3 + 1 + i4, headerCount);
                final Header[] headerArr2 = dynamicTable;
                final int i6 = nextHeaderIndex;
                Arrays.fill(headerArr2, i6 + 1, i6 + 1 + i4, null);
                nextHeaderIndex += i4;
            }
            return i4;
        }

        private void insertIntoDynamicTable(final Header header) {
            final int i2 = header.hpackSize;
            final int i3 = maxDynamicTableByteCount;
            if (i2 > i3) {
                this.clearDynamicTable();
                return;
            }
            this.evictToRecoverBytes((dynamicTableByteCount + i2) - i3);
            final int i4 = headerCount + 1;
            final Header[] headerArr = dynamicTable;
            if (i4 > headerArr.length) {
                final Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                nextHeaderIndex = dynamicTable.length - 1;
                dynamicTable = headerArr2;
            }
            final int i5 = nextHeaderIndex;
            nextHeaderIndex = i5 - 1;
            dynamicTable[i5] = header;
            headerCount++;
            dynamicTableByteCount += i2;
        }

        /*  WARN: Removed duplicated region for block: B:22:0x0079  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void writeHeaders(final List<Header> headerBlock) throws IOException {
            int length;
            int length2;
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (emitDynamicTableSizeUpdate) {
                final int i2 = smallestHeaderTableSizeSetting;
                if (i2 < maxDynamicTableByteCount) {
                    this.writeInt(i2, 31, 32);
                }
                emitDynamicTableSizeUpdate = false;
                smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                this.writeInt(maxDynamicTableByteCount, 31, 32);
            }
            final int size = headerBlock.size();
            int i3 = 0;
            while (i3 < size) {
                final int i4 = i3 + 1;
                final Header header = headerBlock.get(i3);
                final ByteString asciiLowercase = header.name.toAsciiLowercase();
                final ByteString byteString = header.value;
                final Hpack hpack = INSTANCE;
                final Integer num = hpack.getNAME_TO_FIRST_INDEX().get(asciiLowercase);
                if (null != num) {
                    final int iIntValue = num.intValue();
                    length2 = iIntValue + 1;
                    if (2 > length2 || 8 <= length2) {
                        length = length2;
                        length2 = -1;
                    } else if (areEqual(hpack.getSTATIC_HEADER_TABLE()[iIntValue].value, byteString)) {
                        length = length2;
                    } else if (areEqual(hpack.getSTATIC_HEADER_TABLE()[length2].value, byteString)) {
                        length = length2;
                        length2 = iIntValue + 2;
                    }
                } else {
                    length = -1;
                    length2 = -1;
                }
                if (-1 == length2) {
                    int i5 = nextHeaderIndex + 1;
                    final int length3 = dynamicTable.length;
                    while (true) {
                        if (i5 >= length3) {
                            break;
                        }
                        final int i6 = i5 + 1;
                        final Header header2 = dynamicTable[i5];
                        checkNotNull(header2);
                        if (areEqual(header2.name, asciiLowercase)) {
                            final Header header3 = dynamicTable[i5];
                            checkNotNull(header3);
                            if (areEqual(header3.value, byteString)) {
                                length2 = INSTANCE.getSTATIC_HEADER_TABLE().length + (i5 - nextHeaderIndex);
                                break;
                            } else if (-1 == length) {
                                length = INSTANCE.getSTATIC_HEADER_TABLE().length + (i5 - nextHeaderIndex);
                            }
                        }
                        i5 = i6;
                    }
                }
                if (-1 != length2) {
                    this.writeInt(length2, 127, 128);
                } else if (-1 == length) {
                    out.writeByte(64);
                    this.writeByteString(asciiLowercase);
                    this.writeByteString(byteString);
                    this.insertIntoDynamicTable(header);
                } else if (asciiLowercase.startsWith(Header.PSEUDO_PREFIX) && !areEqual(Header.TARGET_AUTHORITY, asciiLowercase)) {
                    this.writeInt(length, 15, 0);
                    this.writeByteString(byteString);
                } else {
                    this.writeInt(length, 63, 64);
                    this.writeByteString(byteString);
                    this.insertIntoDynamicTable(header);
                }
                i3 = i4;
            }
        }

        public void writeInt(final int i2, final int i3, final int i4) {
            if (i2 < i3) {
                out.writeByte(i2 | i4);
                return;
            }
            out.writeByte(i4 | i3);
            int i5 = i2 - i3;
            while (128 <= i5) {
                out.writeByte(128 | (i5 & 127));
                i5 >>>= 7;
            }
            out.writeByte(i5);
        }

        public void writeByteString(final ByteString data) throws IOException {
            Intrinsics.checkNotNullParameter(data, "data");
            if (useCompression) {
                final Huffman huffman = Huffman.INSTANCE;
                if (huffman.encodedLength(data) < data.size()) {
                    final Buffer buffer = new Buffer();
                    huffman.encode(data, buffer);
                    final ByteString byteString = buffer.readByteString();
                    this.writeInt(byteString.size(), 127, 128);
                    out.write(byteString);
                    return;
                }
            }
            this.writeInt(data.size(), 127, 0);
            out.write(data);
        }

        public void resizeHeaderTable(final int i2) {
            headerTableSizeSetting = i2;
            final int iMin = Math.min(i2, 16384);
            final int i3 = maxDynamicTableByteCount;
            if (i3 == iMin) {
                return;
            }
            if (iMin < i3) {
                smallestHeaderTableSizeSetting = Math.min(smallestHeaderTableSizeSetting, iMin);
            }
            emitDynamicTableSizeUpdate = true;
            maxDynamicTableByteCount = iMin;
            this.adjustDynamicTableByteCount();
        }

        private void adjustDynamicTableByteCount() {
            final int i2 = maxDynamicTableByteCount;
            final int i3 = dynamicTableByteCount;
            if (i2 < i3) {
                if (0 == i2) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(i3 - i2);
                }
            }
        }
    }
}
