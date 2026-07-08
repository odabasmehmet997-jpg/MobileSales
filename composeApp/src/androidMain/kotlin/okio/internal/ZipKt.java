package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.*;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;

public final class ZipKt {
    final class AnonymousClass1 extends Lambda {
        public final AnonymousClass1 INSTANCE = new AnonymousClass1();
        AnonymousClass1() {
            super(1);
        }
    }
    public static ZipFileSystem openZip(Path zipPath, FileSystem fileSystem, Function1<? super ZipEntry, Boolean> predicate) throws IOException {
        long size;
        long j2;
        BufferedSource bufferedSourceBuffer;
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        FileHandle fileHandleOpenReadOnly = fileSystem.openReadOnly(zipPath);
        try {
            size = fileHandleOpenReadOnly.size() - ((long) 22);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            fileHandleOpenReadOnly.close();
        }
        if (size < 0) {
            throw new IOException("not a zip: size=" + fileHandleOpenReadOnly.size());
        }
        long jMax = Math.max(size - 65536, 0L);
        do {
            BufferedSource bufferedSourceBuffer2 = Okio.buffer(fileHandleOpenReadOnly.source(size));
            try {
                if (bufferedSourceBuffer2.readIntLe() == 101010256) {
                    EocdRecord eocdRecord = readEocdRecord(bufferedSourceBuffer2);
                    String utf8 = bufferedSourceBuffer2.readUtf8(eocdRecord.getCommentByteCount());
                    bufferedSourceBuffer2.close();
                    long j3 = size - ((long) 20);
                    if (j3 > 0) {
                        BufferedSource bufferedSourceBuffer3 = Okio.buffer(fileHandleOpenReadOnly.source(j3));
                        if (bufferedSourceBuffer3.readIntLe() == 117853008) {
                            int intLe = bufferedSourceBuffer3.readIntLe();
                            long longLe = bufferedSourceBuffer3.readLongLe();
                            if (bufferedSourceBuffer3.readIntLe() != 1 || intLe != 0) {
                                throw new IOException("unsupported zip: spanned");
                            }
                            bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(longLe));
                            try {
                                int intLe2 = bufferedSourceBuffer.readIntLe();
                                if (intLe2 != 101075792) {
                                    throw new IOException("bad zip: expected " + getHex(101075792) + " but was " + getHex(intLe2));
                                }
                                eocdRecord = readZip64EocdRecord(bufferedSourceBuffer, eocdRecord);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(bufferedSourceBuffer, null);
                            } finally {
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(bufferedSourceBuffer3, null);
                    }
                    ArrayList arrayList = new ArrayList();
                    bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(eocdRecord.getCentralDirectoryOffset()));
                    try {
                        long entryCount = eocdRecord.getEntryCount();
                        for (j2 = 0; j2 < entryCount; j2++) {
                            ZipEntry entry = readEntry(bufferedSourceBuffer);
                            if (entry.getOffset() >= eocdRecord.getCentralDirectoryOffset()) {
                                throw new IOException("bad zip: local file header offset >= central directory offset");
                            }
                            if (predicate.invoke(entry).booleanValue()) {
                                arrayList.add(entry);
                            }
                        }
                        Unit unit3 = Unit.INSTANCE;
                        CloseableKt.closeFinally(bufferedSourceBuffer, null);
                        ZipFileSystem zipFileSystem = new ZipFileSystem(zipPath, fileSystem, buildIndex(arrayList), utf8);
                        CloseableKt.closeFinally(fileHandleOpenReadOnly, null);
                        return zipFileSystem;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } finally {
                            CloseableKt.closeFinally(bufferedSourceBuffer, th);
                        }
                    }
                }
                bufferedSourceBuffer2.close();
                size--;
            } finally {
                bufferedSourceBuffer2.close();
            }
        } while (size >= jMax);
        throw new IOException("not a zip: end of central directory signature not found");
    }
    private static Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        Path path = Path.Companion.get("/", false);
        Map<Path, ZipEntry> mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null)));
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, (t, t2) -> ComparisonsKt.compareValues((t).getCanonicalPath(), (t2).getCanonicalPath()))) {
            if (mapMutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry) == null) {
                while (true) {
                    Path pathParent = zipEntry.getCanonicalPath().parent();
                    if (pathParent == null) {
                        break;
                    }
                    ZipEntry zipEntry2 = mapMutableMapOf.get(pathParent);
                    if (zipEntry2 != null) {
                        zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                        break;
                    }
                    ZipEntry zipEntry3 = new ZipEntry(pathParent, true, null, 0L, 0L, 0L, 0, null, 0L, TypedValues.PositionType.TYPE_CURVE_FIT, null);
                    mapMutableMapOf.put(pathParent, zipEntry3);
                    zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                    zipEntry = zipEntry3;
                }
            }
        }
        return mapMutableMapOf;
    }
    public static ZipEntry readEntry(final BufferedSource bufferedSource) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        int intLe = bufferedSource.readIntLe();
        if (intLe != 33639248) {
            throw new IOException("bad zip: expected " + getHex(33639248) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(4L);
        short shortLe = bufferedSource.readShortLe();
        int i2 = shortLe & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i2));
        }
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        Long lDosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & 65535, bufferedSource.readShortLe() & 65535);
        long intLe2 = ((long) bufferedSource.readIntLe()) & 4294967295L;
        final RefLongRef refLongRef = new RefLongRef();
        refLongRef.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
        final RefLongRef refLongRef2 = new RefLongRef();
        refLongRef2.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        int shortLe4 = bufferedSource.readShortLe() & 65535;
        int shortLe5 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(8L);
        final RefLongRef refLongRef3 = new RefLongRef();
        refLongRef3.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
        String utf8 = bufferedSource.readUtf8(shortLe3);
        if (StringsKt.contains( utf8, (char) 0, false)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        long j2 = refLongRef2.element == 4294967295L ? 8 : 0L;
        long j3 = refLongRef.element == 4294967295L ? j2 +  8 : j2;
        if (refLongRef3.element == 4294967295L) {
            j3 +=  8;
        }
        final long j4 = j3;
        final RefBooleanRef refBooleanRef = new RefBooleanRef();
        readExtra(bufferedSource, shortLe4, new Function2<Integer, Long, Unit>() {
            @Override
            public Unit invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }

            public Unit invoke(Integer num, Long l) {
                try {
                    invoke(num.intValue(), l.longValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return Unit.INSTANCE;
            }
            public void invoke(int i3, long j5) throws IOException {
                if (i3 == 1) {
                    RefBooleanRef refBooleanRef2 = refBooleanRef;
                    if (refBooleanRef2.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    refBooleanRef2.element = true;
                    if (j5 < j4) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    RefLongRef refLongRef4 = refLongRef2;
                    long longLe = refLongRef4.element;
                    if (longLe == 4294967295L) {
                        longLe = bufferedSource.readLongLe();
                    }
                    refLongRef4.element = longLe;
                    RefLongRef refLongRef5 = refLongRef;
                    refLongRef5.element = refLongRef5.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                    RefLongRef refLongRef6 = refLongRef3;
                    refLongRef6.element = refLongRef6.element == 4294967295L ? bufferedSource.readLongLe() : 0L;
                }
            }
        });
        if (j4 > 0 && !refBooleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get("/", false).resolve(utf8), StringsKt.endsWith(utf8, "/", false), bufferedSource.readUtf8(shortLe5), intLe2, refLongRef.element, refLongRef2.element, shortLe2, lDosDateTimeToEpochMillis, refLongRef3.element);
    }
    private static EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        int shortLe = bufferedSource.readShortLe() & 65535;
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        long shortLe3 = bufferedSource.readShortLe() & 65535;
        if (shortLe3 != (bufferedSource.readShortLe() & 65535) || shortLe != 0 || shortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(shortLe3, 4294967295L & ((long) bufferedSource.readIntLe()), bufferedSource.readShortLe() & 65535);
    }
    private static EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        long longLe;
        try {
            bufferedSource.skip(12L);
            int intLe = bufferedSource.readIntLe();
            int intLe2 = bufferedSource.readIntLe();
            longLe = bufferedSource.readLongLe();
            if (longLe != bufferedSource.readLongLe() || intLe != 0 || intLe2 != 0) {
                throw new IOException("unsupported zip: spanned");
            }
            bufferedSource.skip(8L);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new EocdRecord(longLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }
    private static void readExtra(BufferedSource bufferedSource, int i2, Function2<Integer, Long, Unit> function2) throws IOException {
        long j2 = i2;
        while (j2 != 0) {
            if (j2 < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int shortLe = bufferedSource.readShortLe() & 65535;
            long shortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j3 = j2 -  4;
            if (j3 < shortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(shortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(shortLe), Long.valueOf(shortLe2));
            long size2 = (bufferedSource.getBuffer().size() + shortLe2) - size;
            if (size2 < 0) {
                throw new IOException("unsupported zip: too many bytes processed for " + shortLe);
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j2 = j3 - shortLe2;
        }
    }
    public static FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata basicMetadata) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
        FileMetadata orSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, basicMetadata);
        Intrinsics.checkNotNull(orSkipLocalHeader);
        return orSkipLocalHeader;
    }
    private static FileMetadata readOrSkipLocalHeader(final BufferedSource bufferedSource, FileMetadata fileMetadata) throws IOException {
        final RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : 0;
        final RefObjectRef refObjectRef2 = new RefObjectRef();
        final RefObjectRef refObjectRef3 = new RefObjectRef();
        int intLe = bufferedSource.readIntLe();
        if (intLe != 67324752) {
            throw new IOException("bad zip: expected " + getHex(67324752) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(2L);
        short shortLe = bufferedSource.readShortLe();
        int i2 = shortLe & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i2));
        }
        bufferedSource.skip(18L);
        long shortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(shortLe2);
        if (fileMetadata == null) {
            bufferedSource.skip(shortLe3);
            return null;
        }
        readExtra(bufferedSource, shortLe3, new Function2<Integer, Long, Unit>() {
            @Override
            public Unit invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }

            public Unit invoke(Integer num, Long l) {
                try {
                    invoke(num.intValue(), l.longValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return Unit.INSTANCE;
            }
            public void invoke(int i3, long j2) throws IOException {
                if (i3 == 21589) {
                    if (j2 < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte b2 = bufferedSource.readByte();
                    boolean z = (b2 & 1) == 1;
                    boolean z2 = (b2 & 2) == 2;
                    boolean z3 = (b2 & 4) == 4;
                    BufferedSource bufferedSource2 = bufferedSource;
                    long j3 = z ? 5L : 1L;
                    if (z2) {
                        j3 += 4;
                    }
                    if (z3) {
                        j3 += 4;
                    }
                    if (j2 < j3) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        refObjectRef.element = Long.valueOf(((long) bufferedSource2.readIntLe()) * 1000);
                    }
                    if (z2) {
                        refObjectRef2.element = Long.valueOf(((long) bufferedSource.readIntLe()) * 1000);
                    }
                    if (z3) {
                        refObjectRef3.element = Long.valueOf(((long) bufferedSource.readIntLe()) * 1000);
                    }
                }
            }
        });
        return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), null, fileMetadata.getSize(), (Long) refObjectRef3.element, (Long) refObjectRef.element, (Long) refObjectRef2.element, null, 128, null);
    }
    private static Long dosDateTimeToEpochMillis(int i2, int i3) {
        if (i3 == -1) {
            return null;
        }
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(14, 0);
            gregorianCalendar.set(((i2 >> 9) & 127) + 1980, ((i2 >> 5) & 15) - 1, i2 & 31, (i3 >> 11) & 31, (i3 >> 5) & 63, (i3 & 31) << 1);
            return Long.valueOf(gregorianCalendar.getTime().getTime());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getHex(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        String string = Integer.toString(i2, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
        sb.append(string);
        return sb.toString();
    }
}
